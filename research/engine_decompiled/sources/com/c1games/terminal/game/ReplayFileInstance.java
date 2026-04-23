/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

public class ReplayFileInstance {
    boolean active = false;
    String replayTotal = "";
    Path filePath;

    public ReplayFileInstance(String folderPath, String fileName, String startString) {
        try {
            File f = new File(folderPath);
            if (!f.exists() || !f.isDirectory()) {
                new File(folderPath).mkdirs();
            }
            this.filePath = Paths.get(folderPath + fileName, new String[0]);
            ArrayList<String> lines2 = new ArrayList<String>();
            lines2.add(startString);
            Files.write(this.filePath, lines2, Charset.defaultCharset(), StandardOpenOption.CREATE);
            this.active = true;
            System.out.println("Saving replay: " + String.valueOf(this.filePath.toAbsolutePath()));
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Cannot create replay file: " + folderPath + fileName);
            this.active = false;
        }
    }

    public void addToFile(String toAdd) {
        if (this.active) {
            try {
                ArrayList<String> lines2 = new ArrayList<String>();
                lines2.add(toAdd);
                Files.write(this.filePath, lines2, Charset.defaultCharset(), StandardOpenOption.APPEND);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void removeReplaysFolder(String path) {
        try {
            FileUtils.deleteDirectory(new File(path));
        }
        catch (IOException e) {
            System.err.println("Couldn't delete replays folder!");
            e.printStackTrace();
        }
    }
}

