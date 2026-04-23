/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.scheduler;

import com.c1games.terminal.playground.player.PlaygroundPlayerBean;
import com.c1games.terminal.util.ProcessUtil;
import java.io.IOException;

public class PausableProcess {
    PlaygroundPlayerBean playerBean;
    private final Object pauseMonitor = new Object();
    boolean paused = false;

    public PausableProcess(PlaygroundPlayerBean playerBean) {
        this.playerBean = playerBean;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void pause() throws IOException {
        Object object = this.pauseMonitor;
        synchronized (object) {
            if (this.playerBean.slottingEnabled) {
                if (!this.paused) {
                    this.paused = true;
                    ProcessUtil.signalRecursive(this.playerBean.process.toHandle(), "STOP", true);
                } else {
                    System.err.println("warning: attempted to pause algo when already paused");
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void resume() throws IOException {
        Object object = this.pauseMonitor;
        synchronized (object) {
            if (this.playerBean.slottingEnabled) {
                if (this.paused) {
                    this.paused = false;
                    ProcessUtil.signalRecursive(this.playerBean.process.toHandle(), "CONT", false);
                } else {
                    System.err.println("warning: attempted to resume algo when already not paused");
                }
            }
        }
    }
}

