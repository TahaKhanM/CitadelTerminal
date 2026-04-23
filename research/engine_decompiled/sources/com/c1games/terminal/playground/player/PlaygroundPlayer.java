/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.player;

import com.c1games.terminal.Terminal;
import com.c1games.terminal.game.GameMain;
import com.c1games.terminal.game.player.Player;
import com.c1games.terminal.game.player.PlayerManager;
import com.c1games.terminal.game.player.PlayerMove;
import com.c1games.terminal.game.player.PlayerType;
import com.c1games.terminal.playground.player.PlaygroundPlayerBean;
import com.c1games.terminal.playground.scheduler.PausableProcess;
import com.c1games.terminal.playground.scheduler.PausableProcessTask;
import com.c1games.terminal.playground.serializer.LazySer;
import com.c1games.terminal.util.DockerAlgo;
import com.c1games.terminal.util.MultiStringReader;
import com.c1games.terminal.util.ProcessUtil;
import com.c1games.terminal.util.TerminatedStringReader;
import com.c1games.terminal.util.TransferThreadOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermissions;
import java.time.Duration;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class PlaygroundPlayer
implements Player {
    private PlaygroundPlayerBean playerBean;
    private Path directory;
    private final String bossName = "boss";
    private final String bossPassword = "7a9d24e467a8bec732570ac057483eff7bdfaed54b87eaee";
    private PausableProcess pausableProcess;
    private MultiStringReader readFromProcess;
    private BufferedReader errReader;
    private Writer writeToProcess;
    private final Queue<String> toSendToAlgo = new ArrayDeque<String>();
    private File errFile;
    private DockerAlgo dockerAlgo;
    private int totalErrLines = 0;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public PlaygroundPlayer(PlaygroundPlayerBean playerBean, byte[] zipData) {
        this.playerBean = playerBean;
        try {
            this.directory = Files.createTempDirectory("algo", new FileAttribute[0]);
            String zipFile = this.directory.toAbsolutePath().toString() + "/algo.zip";
            try (FileOutputStream stream = new FileOutputStream(zipFile);){
                stream.write(zipData);
            }
            try {
                ZipFile zip2 = new ZipFile(zipFile);
                if (zip2.isEncrypted()) {
                    zip2.setPassword("7a9d24e467a8bec732570ac057483eff7bdfaed54b87eaee");
                }
                zip2.extractAll(this.directory.toAbsolutePath().toString());
            }
            catch (ZipException e) {
                System.out.println("Zip fail");
                e.printStackTrace();
            }
        }
        catch (IOException | IllegalStateException e) {
            playerBean.crashed = true;
            System.err.println("Error initializing zipped algo");
            e.printStackTrace();
        }
        File script = null;
        File curr = this.directory.toFile();
        curr.deleteOnExit();
        do {
            if (curr.toPath().resolve("run.sh").toFile().exists()) {
                script = curr.toPath().resolve("run.sh").toFile();
                continue;
            }
            List traceableInto = Arrays.stream(curr.listFiles()).filter(File::isDirectory).filter(file -> !playerBean.serverConfig.findAlgoEntrypointExclude.contains(file.getName())).collect(Collectors.toList());
            if (traceableInto.size() == 1) {
                curr = (File)traceableInto.get(0);
                continue;
            }
            playerBean.crashed = true;
            System.err.println("Cannot find run.sh script in decompressed algo folder");
            return;
        } while (script == null);
        try {
            Files.setPosixFilePermissions(script.toPath(), PosixFilePermissions.fromString("rwxrwxrwx"));
        }
        catch (IOException e) {
            System.err.println("Cannot give permissions to run.sh script in decompressed algo folder");
            e.printStackTrace();
        }
        if (!playerBean.crashed) {
            block33: {
                String command = script.getAbsolutePath();
                try {
                    Runtime.getRuntime().exec("chmod u+x " + command);
                }
                catch (IOException e) {
                    playerBean.crashed = true;
                    System.err.println("Error giving script permission to run");
                    e.printStackTrace();
                    return;
                }
                playerBean.commandPath = Paths.get(command, new String[0]);
                playerBean.name = PlayerManager.getNameFromCommand(command);
                if (!Terminal.safeMode) {
                    block32: {
                        String dir = PlayerManager.getParentDirectory(playerBean.commandPath.toString());
                        Random random = new Random();
                        Path errorFilePath = Paths.get(dir + "errorFile" + playerBean.playerNum + GameMain.getTimeNow() + "-" + System.currentTimeMillis() + "-" + random.nextInt() + ".txt", new String[0]);
                        this.errFile = new File(errorFilePath.toString());
                        try {
                            if (this.errFile.exists()) {
                                this.errFile.delete();
                                this.errFile.createNewFile();
                            } else {
                                this.errFile.createNewFile();
                            }
                        }
                        catch (IOException e) {
                            if (!playerBean.printSystemErrors) break block32;
                            System.err.println("Couldn't create error reroute file");
                            e.printStackTrace();
                        }
                    }
                    ProcessBuilder pb = new ProcessBuilder(new String[0]).command(playerBean.commandPath.toString()).redirectError(this.errFile);
                    try {
                        playerBean.process = pb.start();
                        this.pausableProcess = new PausableProcess(playerBean);
                    }
                    catch (IOException e) {
                        if (playerBean.printSystemErrors) {
                            System.err.println("Couldn't create algo process");
                            e.printStackTrace();
                        }
                        break block33;
                    }
                }
                try {
                    this.dockerAlgo = new DockerAlgo(command, playerBean.playerNum - 1);
                    if (!this.dockerAlgo.setup()) {
                        throw new IOException();
                    }
                    playerBean.process = this.dockerAlgo.run();
                    this.pausableProcess = new PausableProcess(playerBean);
                    this.errFile = this.dockerAlgo.getErrFile();
                }
                catch (IOException e) {
                    if (!playerBean.printSystemErrors) break block33;
                    System.err.println("Couldn't create algo process");
                    e.printStackTrace();
                }
            }
            if (!playerBean.crashed && playerBean.process.isAlive()) {
                this.readFromProcess = new MultiStringReader(new TerminatedStringReader(new BufferedInputStream(playerBean.process.getInputStream()), '\n'));
                this.writeToProcess = new BufferedWriter(new OutputStreamWriter(new TransferThreadOutputStream(playerBean.process.getOutputStream(), false)));
                try {
                    this.errReader = new BufferedReader(new FileReader(this.errFile));
                }
                catch (IOException e) {
                    System.out.println("Couldnt read error file");
                    e.printStackTrace();
                }
            } else {
                System.err.println("process has error before detecting child process uh oh");
                playerBean.crashed = true;
                this.readFromProcess = new MultiStringReader(new TerminatedStringReader(new InputStream(){

                    @Override
                    public int available() throws IOException {
                        return 0;
                    }

                    @Override
                    public int read() throws IOException {
                        System.err.println("Reading from fake input stream");
                        return -559038737;
                    }
                }, '\n'));
                this.errReader = new BufferedReader(new InputStreamReader(new InputStream(){

                    @Override
                    public int available() throws IOException {
                        return 0;
                    }

                    @Override
                    public int read() throws IOException {
                        System.err.println("Reading from fake input stream");
                        return -559038737;
                    }
                }));
                this.writeToProcess = new OutputStreamWriter(new OutputStream(){

                    @Override
                    public void write(int b) throws IOException {
                    }
                });
            }
            this.playerBean.player = this;
        }
    }

    public void receiveErrorNonBlocking() {
        block15: {
            try {
                if (!this.playerBean.closed) {
                    ArrayList<Object> lines2 = new ArrayList<Object>();
                    while (this.errReader.ready()) {
                        ++this.totalErrLines;
                        if (this.totalErrLines == 20000) {
                            System.err.println("PlaygroundAlgo " + this.playerBean.playerNum + " : Algo tried to print a line > 2000 chars ignoring all err print");
                            lines2.add("PlaygroundAlgo " + this.playerBean.playerNum + " : Algo tried to print > 20000 lines ignoring all err print");
                            continue;
                        }
                        if (this.totalErrLines > 20000) {
                            this.errReader.skip(1000000000L);
                            continue;
                        }
                        this.errReader.mark(2050);
                        int charsRead = 0;
                        while (this.errReader.ready()) {
                            ++charsRead;
                            if (this.errReader.read() == 10) {
                                this.errReader.reset();
                                break;
                            }
                            if (charsRead <= 2000) continue;
                            System.err.println("PlaygroundAlgo " + this.playerBean.playerNum + " : Algo tried to print a line > 2000 chars ignoring all err print");
                            lines2.add("PlaygroundAlgo " + this.playerBean.playerNum + " : Algo tried to print a line > 2000 chars ignoring all err print");
                            this.totalErrLines = 10000000;
                            break;
                        }
                        lines2.add(this.errReader.readLine());
                    }
                    StringBuilder combinedLine = new StringBuilder();
                    if (this.playerBean.errorToClient != null) {
                        if (lines2.size() > 0) {
                            for (int i = 0; i < lines2.size(); ++i) {
                                if (i == lines2.size() - 1) {
                                    combinedLine.append((String)lines2.get(i));
                                    continue;
                                }
                                combinedLine.append((String)lines2.get(i) + "\n");
                            }
                            this.playerBean.errorToClient.send(LazySer.unserialized(combinedLine.toString()));
                        }
                    } else {
                        System.err.println("Trying to send error when client is null");
                    }
                    if (this.playerBean.printPlayerErrors && lines2.size() > 0) {
                        for (String string2 : lines2) {
                            System.out.println("PlaygroundAlgo " + this.playerBean.playerNum + " : " + string2);
                        }
                    }
                }
            }
            catch (IOException e) {
                if (this.playerBean.closed) break block15;
                System.out.println("PuasableAlgo stderr read error catched");
                e.printStackTrace();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void send(String message) {
        if (!this.checkCrashed()) {
            Queue<String> queue = this.toSendToAlgo;
            synchronized (queue) {
                this.toSendToAlgo.add(message);
            }
        }
        if (this.playerBean.stateToClient != null) {
            this.playerBean.stateToClient.send(LazySer.serialized(message));
        }
    }

    @Override
    public void finishInitialization(Duration timeout) {
        try {
            while (!this.checkCrashed() && !this.playerBean.closed && this.readFromProcess.inner.receive(timeout.toMillis()) != null) {
            }
        }
        catch (IOException e) {
            if (this.playerBean.printSystemErrors) {
                System.err.println("Error in flushing initial standard output");
                e.printStackTrace();
            }
            this.playerBean.crashed = true;
        }
        try {
            this.pausableProcess.pause();
        }
        catch (IOException e) {
            System.err.println("IOException in pausing algo in finishInitialization:");
            e.printStackTrace();
        }
    }

    @Override
    public CompletableFuture<PlayerMove> makeMove(Duration timeout) {
        CompletableFuture<PlayerMove> ret = this.playerBean.scheduler.submit(this.pausableProcess, this.takeTurn(), timeout);
        this.receiveErrorNonBlocking();
        return ret;
    }

    public PausableProcessTask<PlayerMove> takeTurn() {
        return (algo, timeout) -> {
            long endTime;
            this.receiveErrorNonBlocking();
            Queue<String> queue = this.toSendToAlgo;
            synchronized (queue) {
                Object state;
                while (!this.checkCrashed() && (state = this.toSendToAlgo.poll()) != null) {
                    try {
                        if (((String)state).length() != 1 && ((String)state).charAt(((String)state).length() - 1) != '\n') {
                            System.out.println("debug: auto-appending newline to message to algo");
                            state = (String)state + "\n";
                        }
                        if (this.checkCrashed()) continue;
                        this.writeToProcess.write((String)state);
                    }
                    catch (IOException e) {
                        this.playerBean.crashed = true;
                        if (this.playerBean.printSystemErrors) {
                            e.printStackTrace();
                        }
                        return Optional.empty();
                    }
                }
            }
            this.writeToProcess.flush();
            String[] parts = new String[2];
            long startTime = System.currentTimeMillis();
            long l = endTime = System.currentTimeMillis() + timeout.toMillis() > 0L ? System.currentTimeMillis() + timeout.toMillis() : Long.MAX_VALUE;
            if (!this.checkCrashed() && this.readFromProcess.tryRead(parts, endTime, this, () -> {})) {
                return Optional.of(PlayerMove.success(parts[0], parts[1], System.currentTimeMillis() - startTime));
            }
            return Optional.empty();
        };
    }

    @Override
    public PlayerType type() {
        return PlayerType.ALGO;
    }

    @Override
    public void close() {
        this.receiveErrorNonBlocking();
        this.playerBean.closed = true;
        String isIntellij = System.getenv("IS_INTELLIJ");
        if (isIntellij != null && isIntellij.toLowerCase().equals("true")) {
            System.out.println("Returning from close because this is IntelliJ and closing stuff here causes crashes.Please clean directory yourself: " + String.valueOf(this.directory.toAbsolutePath()));
            return;
        }
        try {
            this.readFromProcess.inner.close();
            this.errFile.delete();
            this.errReader.close();
            this.writeToProcess.close();
            ProcessUtil.killRecursive(this.playerBean.process.toHandle());
        }
        catch (Exception e) {
            System.out.println("Error in trying to close in algo com.c1games.terminal.playground.player");
            e.printStackTrace();
        }
        try {
            System.out.println("Running: rm -rf " + String.valueOf(this.directory.toAbsolutePath()));
            Runtime.getRuntime().exec("rm -rf " + String.valueOf(this.directory.toAbsolutePath()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gameEnd() {
        this.receiveErrorNonBlocking();
    }

    @Override
    public boolean checkCrashed() {
        return this.playerBean.crashed || !this.playerBean.process.isAlive();
    }

    @Override
    public String name() {
        return this.playerBean.name;
    }

    private static void printProcessTree(ProcessHandle proc, int depth, StringBuilder buffer) {
        for (int i = 0; i < depth; ++i) {
            buffer.append("-");
        }
        buffer.append(proc.pid());
        buffer.append('\n');
        proc.children().forEach(child -> PlaygroundPlayer.printProcessTree(child, depth + 1, buffer));
    }
}

