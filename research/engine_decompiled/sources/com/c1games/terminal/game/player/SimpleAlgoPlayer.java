/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.player;

import com.c1games.terminal.Terminal;
import com.c1games.terminal.game.GameMain;
import com.c1games.terminal.game.player.Player;
import com.c1games.terminal.game.player.PlayerManager;
import com.c1games.terminal.game.player.PlayerMove;
import com.c1games.terminal.game.player.PlayerType;
import com.c1games.terminal.util.DockerAlgo;
import com.c1games.terminal.util.MultiStringReader;
import com.c1games.terminal.util.TerminatedStringReader;
import com.c1games.terminal.util.TransferThreadOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleAlgoPlayer
implements Player {
    private final boolean printIOErrors;
    private final ExecutorService moveMaker;
    private TerminatedStringReader errorReader;
    private boolean printStdErr;
    private MultiStringReader readFromProcess;
    private Writer writeToProcess;
    private Process process;
    private Thread errorThread;
    private boolean crashed;
    private boolean closed;
    private String name;
    private String errorFilePath;
    private File errFile;
    private BufferedReader errReader;
    public String lastFrameSent;
    private int playerIndex;
    private DockerAlgo dockerAlgo;
    private int errlineCount;

    public SimpleAlgoPlayer(String command, boolean printIOErrors, boolean printStdErr, int playerIndex) {
        block12: {
            this.errorThread = null;
            this.crashed = false;
            this.closed = false;
            this.lastFrameSent = "";
            this.errlineCount = 0;
            this.name = PlayerManager.getNameFromCommand(command);
            this.printIOErrors = printIOErrors;
            this.moveMaker = Executors.newSingleThreadExecutor();
            this.printStdErr = printStdErr;
            this.playerIndex = playerIndex;
            try {
                if (!Terminal.safeMode) {
                    String dir = PlayerManager.getParentDirectory(command);
                    Random random = new Random();
                    this.errorFilePath = dir + "errorFile" + (playerIndex + 1) + GameMain.getTimeNow() + "-" + System.currentTimeMillis() + "-" + random.nextInt() + ".txt";
                    this.errFile = new File(this.errorFilePath);
                    if (this.errFile.exists()) {
                        this.errFile.delete();
                        this.errFile.createNewFile();
                    } else {
                        this.errFile.createNewFile();
                    }
                    if (Terminal.getWindows()) {
                        ProcessBuilder pb = new ProcessBuilder(new String[0]).command("powershell", command).redirectError(this.errFile);
                        this.process = pb.start();
                    } else {
                        Runtime.getRuntime().exec("chmod u+x " + command);
                        System.out.println("Command: " + command);
                        ArrayList<String> commands = new ArrayList<String>();
                        commands.addAll(Arrays.asList(command.split("\\s")));
                        ProcessBuilder pb = new ProcessBuilder(new String[0]).command(commands).redirectError(this.errFile);
                        System.out.println("Running: " + pb.command().toString());
                        this.process = pb.start();
                        System.out.println("Starting algo!");
                    }
                } else {
                    this.dockerAlgo = new DockerAlgo(command, playerIndex);
                    if (this.dockerAlgo.setup()) {
                        try {
                            Thread.sleep(Terminal.waitBeforeRunMilli);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.process = this.dockerAlgo.run();
                        this.errFile = this.dockerAlgo.getErrFile();
                    } else {
                        throw new IOException();
                    }
                }
                this.errReader = new BufferedReader(new FileReader(this.errFile));
                this.readFromProcess = new MultiStringReader(new TerminatedStringReader(new BufferedInputStream(this.process.getInputStream()), '\n'));
                this.writeToProcess = new BufferedWriter(new OutputStreamWriter(new TransferThreadOutputStream(this.process.getOutputStream(), false)), 10000);
            }
            catch (IOException e) {
                this.crashed = true;
                if (!printIOErrors) break block12;
                System.err.println("Error on simple algo process creation");
                e.printStackTrace();
            }
        }
    }

    public void receiveErrorNonBlocking() {
        block9: {
            try {
                if (!this.closed) {
                    while (this.errReader.ready()) {
                        if (this.errlineCount > 20000) {
                            this.errReader.skip(1000000000L);
                            continue;
                        }
                        ++this.errlineCount;
                        this.errReader.mark(2050);
                        int charsRead = 0;
                        while (this.errReader.ready()) {
                            ++charsRead;
                            if (this.errReader.read() == 10) {
                                this.errReader.reset();
                                break;
                            }
                            if (charsRead <= 2000) continue;
                            System.err.println("SAPlayer " + (this.playerIndex + 1) + " " + this.name() + ": Algo tried to print a line > 2000 chars ignoring all err print");
                            this.errlineCount = 1000000;
                            break;
                        }
                        if (this.errlineCount > 20000) continue;
                        String line = this.errReader.readLine();
                        if (this.errlineCount == 20000) {
                            System.out.println("SAPlayer " + (this.playerIndex + 1) + " " + this.name() + ": Printed > 20K lines now skipping all error output");
                        }
                        if (this.printStdErr) {
                            System.out.println("SAPlayer " + (this.playerIndex + 1) + " " + this.name() + ": " + line);
                        }
                        if (this.errlineCount >= 10 || !line.contains("Unable to find image") || !line.contains("locally")) continue;
                        System.err.println("Image seems to be broken throwing error to try and restart");
                        throw new RuntimeException();
                    }
                }
            }
            catch (IOException e) {
                if (this.closed) break block9;
                System.out.println("SimpleAlgoPlayer stderr read error catched");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void finishInitialization(Duration timeout) {
        try {
            if (!this.checkCrashed() && !this.closed) {
                while (this.readFromProcess.inner.receive(timeout.toMillis()) != null) {
                }
            }
        }
        catch (IOException e) {
            if (this.printIOErrors) {
                System.err.println("Error in flushing initial standard output");
                e.printStackTrace();
            }
            this.crashed = true;
        }
    }

    @Override
    public void send(String message) {
        block3: {
            try {
                if (!this.checkCrashed() && !this.closed) {
                    this.lastFrameSent = message;
                    this.writeToProcess.write(message);
                    this.writeToProcess.flush();
                }
            }
            catch (IOException e) {
                this.crashed = true;
                if (!this.printIOErrors) break block3;
                System.out.println("Error in trying to send state string to algo com.c1games.terminal.playground.player");
                e.printStackTrace();
            }
        }
    }

    @Override
    public CompletableFuture<PlayerMove> makeMove(Duration timeout) {
        CompletableFuture<PlayerMove> future = new CompletableFuture<PlayerMove>();
        this.moveMaker.submit(() -> {
            long runUntil;
            String[] parts = new String[2];
            long startTime = System.currentTimeMillis();
            try {
                runUntil = Math.addExact(System.currentTimeMillis(), timeout.toMillis());
            }
            catch (ArithmeticException e) {
                runUntil = Long.MAX_VALUE;
            }
            try {
                if (!this.checkCrashed() && this.readFromProcess.tryRead(parts, runUntil, this, () -> {})) {
                    future.complete(PlayerMove.success(parts[0], parts[1], System.currentTimeMillis() - startTime));
                } else {
                    future.completeExceptionally(new NoSuchElementException());
                }
            }
            catch (IOException e) {
                future.completeExceptionally(e);
            }
        });
        this.receiveErrorNonBlocking();
        return future;
    }

    @Override
    public PlayerType type() {
        return PlayerType.ALGO;
    }

    @Override
    public void close() {
        System.out.println("Closing stream");
        this.closed = true;
        try {
            this.readFromProcess.inner.close();
            this.writeToProcess.close();
            this.errReader.close();
        }
        catch (Exception e) {
            System.out.println("Error in trying to close in algo com.c1games.terminal.playground.player");
            e.printStackTrace();
        }
        this.errFile.delete();
    }

    @Override
    public void gameEnd() {
        this.receiveErrorNonBlocking();
    }

    @Override
    public boolean checkCrashed() {
        boolean crash;
        boolean bl = crash = this.crashed || !this.process.isAlive();
        if (crash) {
            System.out.println("Algo Crashed. Crash: " + this.crashed + " !processIsAlive: " + String.valueOf(this.process != null ? Boolean.valueOf(!this.process.isAlive()) : "null"));
        }
        return crash;
    }

    @Override
    public String name() {
        return this.name;
    }
}

