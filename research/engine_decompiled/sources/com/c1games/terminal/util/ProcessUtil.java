/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.util;

import com.c1games.terminal.Terminal;
import java.io.IOException;

public class ProcessUtil {
    public static void killRecursive(ProcessHandle process) {
        if (!Terminal.getWindows()) {
            ProcessUtil.freezeProcessTree(process, 0);
        }
        ProcessUtil.killProcessTreeWinddown(process, 0);
    }

    private static void freezeProcessTree(ProcessHandle process, int depth) {
        try {
            int status = Runtime.getRuntime().exec("kill -STOP " + process.pid()).waitFor();
            if (status != 0) {
                System.out.print(" !status=" + status);
            }
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        process.children().forEach(child -> ProcessUtil.freezeProcessTree(child, depth + 1));
    }

    private static void killProcessTreeWinddown(ProcessHandle process, int depth) {
        process.children().forEach(child -> ProcessUtil.killProcessTreeWinddown(child, depth + 1));
        process.destroyForcibly();
    }

    public static void trySendSignalToProcess(ProcessHandle process, String signal) {
        try {
            Runtime.getRuntime().exec("kill -" + signal + " " + process.pid());
        }
        catch (IOException e) {
            System.err.println("IOException sending signal to process:");
            e.printStackTrace();
        }
    }

    public static void signalRecursive(ProcessHandle process, String signal, boolean rootFirst) {
        if (rootFirst) {
            ProcessUtil.trySendSignalToProcess(process, signal);
        }
        process.children().forEach(p -> ProcessUtil.signalRecursive(p, signal, rootFirst));
        if (!rootFirst) {
            ProcessUtil.trySendSignalToProcess(process, signal);
        }
    }
}

