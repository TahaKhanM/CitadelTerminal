/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.util;

import com.c1games.terminal.Terminal;
import com.c1games.terminal.game.GameMain;
import com.c1games.terminal.game.player.PlayerManager;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class DockerAlgo {
    private final String folderPath;
    private final String runPath;
    private final String imageName;
    private final String tagName;
    private final String containerName;
    private final int playerIndex;
    private Random random;
    private File errFile;
    private int totalRebuildAttemptCount = 0;

    public DockerAlgo(String command, int playerIndex) {
        this.folderPath = PlayerManager.getParentDirectory(command);
        this.runPath = command;
        this.playerIndex = playerIndex;
        this.random = new Random();
        this.tagName = "a_" + (playerIndex + 1) + "_" + System.getenv("TASK_ID") + "_" + System.getenv("WORKER_NAME") + "_" + String.valueOf(LocalDate.now(ZoneId.of("UTC"))) + "_" + System.currentTimeMillis() + "_" + this.random.nextInt();
        this.imageName = "algos:" + this.tagName;
        this.containerName = this.tagName + "_container";
    }

    public static void setupBase() {
        try {
            String isIntellij = System.getenv("IS_INTELLIJ");
            if (isIntellij != null && isIntellij.toLowerCase().equals("true")) {
                System.out.println("Not cleaning up ALL because this is intellij");
            }
            Path directory = Files.createTempDirectory("base", new FileAttribute[0]);
            URL inputUrl = DockerAlgo.class.getResource("/DockerfileBase");
            File dest = new File(String.valueOf(directory.toAbsolutePath()) + "/Dockerfile");
            System.out.println("file: " + inputUrl.getPath());
            FileUtils.copyURLToFile(inputUrl, dest);
            System.out.println("path to save bf: " + dest.getAbsolutePath());
            inputUrl = DockerAlgo.class.getResource("/create_base_img.sh");
            dest = new File(String.valueOf(directory.toAbsolutePath()) + "/create_base_img.sh");
            System.out.println("file: " + inputUrl.getPath());
            FileUtils.copyURLToFile(inputUrl, dest);
            System.out.println("path to save bf: " + dest.getAbsolutePath());
            Runtime.getRuntime().exec("chmod +x " + String.valueOf(directory.toAbsolutePath()) + "/create_base_img.sh").waitFor();
            ProcessBuilder pb = new ProcessBuilder(new String[0]).command(String.valueOf(directory.toAbsolutePath()) + "/create_base_img.sh");
            pb.inheritIO();
            pb.start().waitFor();
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.err.println("Couldn't make base image");
        }
    }

    public boolean setup() {
        Process process;
        try {
            Runtime.getRuntime().exec("chmod u+x " + this.runPath);
        }
        catch (IOException e) {
            System.err.println("Could not give run.sh permissions");
            e.printStackTrace();
            return false;
        }
        URL inputUrl = this.getClass().getResource("/Dockerfile");
        File dest = new File(this.folderPath + "/Dockerfile");
        System.out.println("file: " + inputUrl.getPath());
        System.out.println("path to save df: " + dest.getAbsolutePath());
        try {
            FileUtils.copyURLToFile(inputUrl, dest);
        }
        catch (IOException e) {
            System.err.println("Could not load dockerfile");
            e.printStackTrace();
            return false;
        }
        String command = "docker build --tag " + this.imageName + " " + this.folderPath;
        ArrayList<String> commands = new ArrayList<String>();
        commands.addAll(Arrays.asList(command.split("\\s")));
        ProcessBuilder pb = new ProcessBuilder(new String[0]).command(commands);
        System.out.println("Run: " + pb.command().toString());
        pb.inheritIO();
        try {
            process = pb.start();
        }
        catch (IOException e) {
            System.err.println("Could not build docker image");
            e.printStackTrace();
            return false;
        }
        try {
            if (process.waitFor() != 0) {
                System.err.println("Could not build docker image");
                return false;
            }
        }
        catch (InterruptedException e) {
            System.err.println("Could not build docker image");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean checkDockerImages(String tag) {
        Process process;
        String command = "docker images";
        ArrayList<String> commands = new ArrayList<String>();
        commands.addAll(Arrays.asList(command.split("\\s")));
        ProcessBuilder pb = new ProcessBuilder(new String[0]).command(commands);
        pb.redirectErrorStream(true);
        System.out.println("Run: " + pb.command().toString());
        boolean ret = false;
        String output = "";
        try {
            process = pb.start();
            output = IOUtils.toString(process.getInputStream(), "UTF-8");
            System.out.println("checkDockerImages:\n" + output);
        }
        catch (IOException e) {
            System.err.println("Could not run docker images");
            e.printStackTrace();
            return false;
        }
        try {
            if (process.waitFor() != 0) {
                System.err.println("Issue running docker images, non 0 error code");
                return false;
            }
        }
        catch (InterruptedException e) {
            System.err.println("Could not run docker images");
            e.printStackTrace();
            return false;
        }
        ret = output.contains(tag);
        return ret;
    }

    public Process run() throws IOException {
        while (!this.checkDockerImages(this.tagName) && this.totalRebuildAttemptCount <= Terminal.maxBuildAttempts) {
            ++this.totalRebuildAttemptCount;
            System.out.println("Couldnt find image! Waiting, then checking again then rebuilding... " + this.totalRebuildAttemptCount + " " + this.imageName);
            try {
                Thread.sleep(Terminal.waitBeforeRunMilli);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.checkDockerImages(this.tagName)) continue;
            System.out.println("Couldnt find image, rebuilding...");
            this.setup();
        }
        System.out.println("Found, or giving up on find image:" + this.imageName + " . rebuild count:" + this.totalRebuildAttemptCount + " Now running algo...");
        String errorFilePath = this.folderPath + "errorFile" + (this.playerIndex + 1) + GameMain.getTimeNow() + "-" + System.currentTimeMillis() + "-" + this.random.nextInt() + ".txt";
        this.errFile = new File(errorFilePath);
        if (this.errFile.exists()) {
            this.errFile.delete();
            this.errFile.createNewFile();
        } else {
            this.errFile.createNewFile();
        }
        String cpu = System.getenv("TERMINAL_CPU_LIMIT");
        boolean limitCpu = false;
        if (cpu != null) {
            limitCpu = true;
        }
        Object cpuString = limitCpu ? " --cpus=" + cpu : "";
        String memory = System.getenv("TERMINAL_MEMORY_LIMIT");
        boolean limitMemory = false;
        if (memory != null) {
            limitMemory = true;
        }
        Object memoryString = limitMemory ? " --memory=" + memory : "";
        String command = "docker run" + (String)cpuString + (String)memoryString + " --network none --name " + this.containerName + " --label task.id=" + System.getenv("TASK_ID") + " --label worker.name=" + System.getenv("WORKER_NAME") + " -i " + this.imageName;
        System.out.println("Command: " + command);
        ArrayList<String> commands = new ArrayList<String>();
        commands.addAll(Arrays.asList(command.split("\\s")));
        ProcessBuilder pb = new ProcessBuilder(new String[0]).command(commands).redirectError(this.errFile);
        System.out.println("Run: " + pb.command().toString());
        Process process = pb.start();
        return process;
    }

    public File getErrFile() {
        return this.errFile;
    }

    public static void main(String[] args) {
        DockerAlgo test = new DockerAlgo("/Users/junaid/Documents/C1GamesStarterKit/python-algo/run.sh", 1);
        test.setupBase();
    }
}

