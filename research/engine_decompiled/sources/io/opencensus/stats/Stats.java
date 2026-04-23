/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import com.google.common.annotations.VisibleForTesting;
import io.opencensus.internal.Provider;
import io.opencensus.stats.NoopStats;
import io.opencensus.stats.StatsCollectionState;
import io.opencensus.stats.StatsComponent;
import io.opencensus.stats.StatsRecorder;
import io.opencensus.stats.ViewManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class Stats {
    private static final Logger logger = Logger.getLogger(Stats.class.getName());
    private static final StatsComponent statsComponent = Stats.loadStatsComponent(StatsComponent.class.getClassLoader());

    public static StatsRecorder getStatsRecorder() {
        return statsComponent.getStatsRecorder();
    }

    public static ViewManager getViewManager() {
        return statsComponent.getViewManager();
    }

    public static StatsCollectionState getState() {
        return statsComponent.getState();
    }

    @Deprecated
    public static void setState(StatsCollectionState state) {
        statsComponent.setState(state);
    }

    @VisibleForTesting
    static StatsComponent loadStatsComponent(@Nullable ClassLoader classLoader) {
        try {
            return Provider.createInstance(Class.forName("io.opencensus.impl.stats.StatsComponentImpl", true, classLoader), StatsComponent.class);
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Couldn't load full implementation for StatsComponent, now trying to load lite implementation.", e);
            try {
                return Provider.createInstance(Class.forName("io.opencensus.impllite.stats.StatsComponentImplLite", true, classLoader), StatsComponent.class);
            }
            catch (ClassNotFoundException e2) {
                logger.log(Level.FINE, "Couldn't load lite implementation for StatsComponent, now using default implementation for StatsComponent.", e2);
                return NoopStats.newNoopStatsComponent();
            }
        }
    }

    private Stats() {
    }
}

