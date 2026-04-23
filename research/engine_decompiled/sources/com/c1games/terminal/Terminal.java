/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal;

import com.c1games.terminal.game.GameMain;
import com.c1games.terminal.game.ReplayFileInstance;
import com.c1games.terminal.playground.ClientGreeter;
import com.c1games.terminal.playground.ServerConfig;
import com.c1games.terminal.playground.serializer.LazySerializer;
import com.c1games.terminal.util.DockerAlgo;
import com.c1games.terminal.util.ManagerDownloader;
import com.c1games.terminal.util.MultiOutputStream;
import com.c1games.terminal.util.OnlineMatchMaker;
import com.c1games.terminal.util.ThreadLocalOutput;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Optional;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import towersocket.TowerSocket;
import towersocket.TowerSocketFactory;
import towersocket.serializers.SimpleJsonSerializer;

public class Terminal {
    private static BufferedWriter specialErrorWriter;
    private static boolean saveSpecialErrors;
    public static OnlineMatchMaker onlineMatchMaker;
    public static boolean safeMode;
    public static ManagerDownloader managerDownloader;
    public static int maxBuildAttempts;
    public static int waitBeforeRunMilli;
    private static String OS;
    private static boolean isWindows;
    public static ServerConfig config;

    public static void writeToSpecialWriterln(String s2) {
        if (!saveSpecialErrors) {
            return;
        }
        try {
            if (specialErrorWriter != null) {
                specialErrorWriter.write(s2 + "\n");
                specialErrorWriter.flush();
            } else {
                System.err.println("Trying to write to nonexistent special error writer: " + s2);
            }
        }
        catch (IOException e) {
            System.err.println("Error in special error writer");
            e.printStackTrace();
        }
    }

    public static String getOSName() {
        if (OS == null) {
            OS = System.getProperty("os.name");
            isWindows = OS.toLowerCase().contains("win");
        }
        return OS;
    }

    public static boolean getWindows() {
        if (OS == null) {
            Terminal.getOSName();
            return isWindows;
        }
        return isWindows;
    }

    public static void main(String[] args) {
        Path serverConfigPath;
        String sleepTime;
        String rebuild;
        if (args.length < 1) {
            System.err.println("Incorrect arguments. Need mode argument either work or play");
            return;
        }
        String safe = System.getenv("TERMINAL_SAFE_MODE");
        if (safe != null) {
            safeMode = safe.trim().toLowerCase().equals("true");
        }
        if (safeMode) {
            DockerAlgo.setupBase();
        }
        if ((rebuild = System.getenv("TERMINAL_MAX_REBUILDS")) != null) {
            try {
                maxBuildAttempts = Integer.parseInt(rebuild);
            }
            catch (NumberFormatException e) {
                System.err.println("Couldnt parse TERMINAL_MAX_REBUILDS as int, using default: " + maxBuildAttempts);
            }
        }
        if ((sleepTime = System.getenv("TERMINAL_RUN_SLEEP")) != null) {
            try {
                waitBeforeRunMilli = Integer.parseInt(sleepTime);
            }
            catch (NumberFormatException e) {
                System.err.println("Couldnt parse TERMINAL_RUN_SLEEP as int, using default: " + waitBeforeRunMilli);
            }
        }
        System.out.println("Starting Engine: August_17_2020");
        if (safeMode) {
            System.out.println("In safe mode.");
        }
        onlineMatchMaker = new OnlineMatchMaker();
        String mode = args[0];
        String[] args2 = Arrays.copyOfRange(args, 1, args.length);
        if (saveSpecialErrors) {
            try {
                String dir = "specialErrors.txt";
                File specialErrorFile = new File(dir);
                System.out.println("special error path: " + specialErrorFile.getAbsolutePath());
                if (specialErrorFile.exists()) {
                    specialErrorFile.delete();
                    specialErrorFile.createNewFile();
                } else {
                    specialErrorFile.createNewFile();
                }
                specialErrorWriter = new BufferedWriter(new FileWriter(specialErrorFile));
            }
            catch (IOException e) {
                System.err.println("Couldnt setup special file");
                e.printStackTrace();
            }
        }
        int retrys = 0;
        if (mode.contains("work")) {
            for (int i = 0; i < 1; ++i) {
                try {
                    System.out.println("Starting single worker game.");
                    GameMain.main(args2);
                    continue;
                }
                catch (Exception e) {
                    System.err.println("Error in game: ");
                    e.printStackTrace();
                    if (!safeMode) continue;
                    if (++retrys < maxBuildAttempts) {
                        --i;
                    }
                    System.out.println("Removing files in: " + GameMain.replayPath);
                    if (GameMain.replayPath == null) continue;
                    ReplayFileInstance.removeReplaysFolder(GameMain.replayPath);
                }
            }
            System.exit(0);
            return;
        }
        args = args2;
        System.out.println("Starting Playground");
        try {
            Path gameConfigPath = Paths.get(args[0], new String[0]);
            serverConfigPath = Paths.get(args[1], new String[0]);
        }
        catch (Exception e) {
            System.err.println("usage: java -jar playground_worker.jar game_config_path server_config_path");
            return;
        }
        try {
            config = new ServerConfig(serverConfigPath.toString());
        }
        catch (NoSuchFileException e) {
            System.err.println("cannot find file: " + String.valueOf(serverConfigPath.toAbsolutePath()));
            return;
        }
        catch (Exception e) {
            System.err.println("invalid server config file");
            e.printStackTrace();
            return;
        }
        if (Terminal.config.overrideAlgoPath != null) {
            System.out.println("Overriding algo path to: " + Terminal.config.overrideAlgoPath);
            System.out.println("(Users will not be able to, nor be expected to, upload their own algos.)");
        }
        if (Terminal.config.redirectOut) {
            System.out.println("Redirecting output to file out.txt");
            try {
                FileOutputStream fout = new FileOutputStream(new File("out.txt"));
                MultiOutputStream multiOut = new MultiOutputStream(System.out, fout);
                PrintStream newOut = new PrintStream(multiOut);
                System.setOut(newOut);
                System.setErr(newOut);
                System.out.println("Set multiout to out");
                System.err.println("Set multiout to err");
            }
            catch (IOException e) {
                System.err.println("Failed to redirect stderr/stdout");
                e.printStackTrace();
                return;
            }
        }
        if (Terminal.config.algoLocalOutput) {
            System.out.println("Activating algo local output (warning: notable performance cost)");
            ThreadLocalOutput.activate();
        }
        SSLContext ssl = null;
        if (Terminal.config.sslKeystore != null) {
            File keystoreFile = new File(Terminal.config.sslKeystore);
            try {
                System.out.println("Creating SSL context");
                ssl = SSLContext.getInstance("TLS");
                char[] password = Terminal.config.sslPassword.toCharArray();
                KeyStore ks = KeyStore.getInstance("JKS");
                try (FileInputStream in = new FileInputStream(keystoreFile);){
                    ks.load(in, password);
                }
                KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
                kmf.init(ks, password);
                TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
                tmf.init(ks);
                ssl.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            }
            catch (IOException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException e) {
                System.err.println("SSL context creation exception");
                e.printStackTrace();
                return;
            }
        }
        managerDownloader = new ManagerDownloader();
        LazySerializer serializer = new LazySerializer(new SimpleJsonSerializer());
        TowerSocket socket = TowerSocketFactory.create(serializer);
        int port = Terminal.config.overridePort.orElse(3047);
        try {
            ClientGreeter greeter = new ClientGreeter(config);
            socket.openPort(port, greeter, Optional.ofNullable(ssl));
            System.out.println("Bound to port T1");
        }
        catch (Exception e) {
            System.err.println("Failed to open port");
            e.printStackTrace();
            System.exit(1);
        }
    }

    static {
        saveSpecialErrors = false;
        maxBuildAttempts = 10;
        waitBeforeRunMilli = 0;
        OS = null;
        isWindows = false;
    }
}

