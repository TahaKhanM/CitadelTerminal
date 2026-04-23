/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.util;

import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.Payload;
import com.google.cloud.logging.Severity;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class StackdriverOutput
extends OutputStream {
    private final OutputStream out;
    private final String logName;
    private final Queue<LogEntry> submitter;
    private StringBuilder lineBuffer;

    public static boolean overrideStdOutErr() {
        if (System.getenv("GOOGLE_APPLICATION_CREDENTIALS") != null && System.getenv("STACKDRIVER_LOG_NAME") != null) {
            PrintStream originalOut = System.out;
            PrintStream originalErr = System.err;
            Logging logging = (Logging)LoggingOptions.getDefaultInstance().getService();
            String logName = System.getenv("STACKDRIVER_LOG_NAME");
            LinkedBlockingQueue<LogEntry> queue = new LinkedBlockingQueue<LogEntry>();
            new Thread(() -> {
                try {
                    while (true) {
                        LogEntry curr;
                        ArrayDeque<LogEntry> batch = new ArrayDeque<LogEntry>();
                        batch.add((LogEntry)queue.take());
                        while ((curr = (LogEntry)queue.poll()) != null) {
                            batch.add(curr);
                        }
                        logging.write(batch, new Logging.WriteOption[0]);
                    }
                }
                catch (Exception e) {
                    originalErr.println("Error in stackdriver submission thread:");
                    e.printStackTrace(originalErr);
                    return;
                }
            }, "stackdriver uploading thread").start();
            System.setOut(new PrintStream(new StackdriverOutput(originalOut, logName, queue)));
            System.setErr(new PrintStream(new StackdriverOutput(originalErr, logName, queue)));
            return true;
        }
        return false;
    }

    private StackdriverOutput(OutputStream out, String logName, Queue<LogEntry> submitter) {
        this.out = out;
        this.logName = logName;
        this.submitter = submitter;
        this.lineBuffer = new StringBuilder();
    }

    @Override
    public void write(int b) throws IOException {
        this.out.write(b);
        if (b == 10) {
            String line = this.lineBuffer.toString();
            this.lineBuffer = new StringBuilder();
            LogEntry entry = LogEntry.newBuilder(Payload.StringPayload.of(line)).setSeverity(Severity.ERROR).setLogName(this.logName).setResource(MonitoredResource.newBuilder("global").build()).build();
            this.submitter.add(entry);
        } else {
            this.lineBuffer.append((char)b);
        }
    }
}

