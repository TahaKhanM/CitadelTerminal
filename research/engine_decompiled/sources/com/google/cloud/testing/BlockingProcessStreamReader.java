/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.testing;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class BlockingProcessStreamReader
extends Thread {
    private static final int LOG_LENGTH_LIMIT = 50000;
    private final BufferedReader errorReader;
    private final Logger logger;
    private StringBuilder currentLog;
    private Level currentLogLevel;
    private boolean collectionMode;
    private final String emulatorTag;
    private final Pattern logLinePattern;

    private BlockingProcessStreamReader(String emulator, InputStream stream, String blockUntil, Logger logger) throws IOException {
        super("blocking-process-stream-reader");
        this.setDaemon(true);
        this.errorReader = new BufferedReader(new InputStreamReader(stream));
        this.logger = logger;
        this.emulatorTag = "[" + emulator + "]";
        this.logLinePattern = Pattern.compile("(\\[" + emulator + "\\]\\s)?(\\w+):.*");
        if (!Strings.isNullOrEmpty(blockUntil)) {
            String line;
            while ((line = this.errorReader.readLine()) != null && !line.contains(blockUntil)) {
            }
        }
    }

    @Override
    public void run() {
        String previousLine = "";
        String nextLine = "";
        try {
            while (true) {
                previousLine = nextLine;
                nextLine = this.errorReader.readLine();
                if (nextLine != null) {
                    this.processLogLine(previousLine, nextLine);
                    continue;
                }
                break;
            }
        }
        catch (IOException e) {
            e.printStackTrace(System.err);
        }
        this.processLogLine(previousLine, MoreObjects.firstNonNull(nextLine, ""));
        this.writeLog();
    }

    private void processLogLine(String previousLine, String nextLine) {
        Level nextLogLevel = this.getLevel(nextLine);
        if (nextLogLevel != null) {
            this.writeLog();
            this.currentLog = new StringBuilder();
            this.currentLogLevel = nextLogLevel;
            this.collectionMode = true;
        } else if (this.collectionMode) {
            if (this.currentLog.length() > 50000) {
                this.collectionMode = false;
            } else if (this.currentLog.length() == 0) {
                this.currentLog.append(this.emulatorTag);
                this.currentLog.append(previousLine.split(":", 2)[1]);
                this.currentLog.append(System.getProperty("line.separator"));
            } else {
                if (!previousLine.startsWith(this.emulatorTag)) {
                    this.currentLog.append(this.emulatorTag);
                    this.currentLog.append(' ');
                }
                this.currentLog.append(previousLine);
                this.currentLog.append(System.getProperty("line.separator"));
            }
        }
    }

    private void writeLog() {
        if (this.currentLogLevel != null && this.currentLog != null && this.currentLog.length() != 0) {
            this.logger.log(this.currentLogLevel, this.currentLog.toString().trim());
        }
    }

    private Level getLevel(String line) {
        try {
            Matcher matcher = this.logLinePattern.matcher(line);
            if (matcher.matches()) {
                return Level.parse(matcher.group(2));
            }
            return null;
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

    static BlockingProcessStreamReader start(String emulator, InputStream stream, String blockUntil, Logger logger) throws IOException {
        BlockingProcessStreamReader thread = new BlockingProcessStreamReader(emulator, stream, blockUntil, logger);
        thread.start();
        return thread;
    }
}

