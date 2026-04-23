/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.StatsCollectionState;
import io.opencensus.stats.StatsRecorder;
import io.opencensus.stats.ViewManager;

public abstract class StatsComponent {
    public abstract ViewManager getViewManager();

    public abstract StatsRecorder getStatsRecorder();

    public abstract StatsCollectionState getState();

    @Deprecated
    public abstract void setState(StatsCollectionState var1);
}

