/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.opencensus.common.Functions;
import io.opencensus.common.Timestamp;
import io.opencensus.stats.Measure;
import io.opencensus.stats.MeasureMap;
import io.opencensus.stats.StatsCollectionState;
import io.opencensus.stats.StatsComponent;
import io.opencensus.stats.StatsRecorder;
import io.opencensus.stats.View;
import io.opencensus.stats.ViewData;
import io.opencensus.stats.ViewManager;
import io.opencensus.tags.TagContext;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

final class NoopStats {
    private NoopStats() {
    }

    static StatsComponent newNoopStatsComponent() {
        return new NoopStatsComponent();
    }

    static StatsRecorder getNoopStatsRecorder() {
        return NoopStatsRecorder.INSTANCE;
    }

    static MeasureMap getNoopMeasureMap() {
        return NoopMeasureMap.INSTANCE;
    }

    static ViewManager newNoopViewManager() {
        return new NoopViewManager();
    }

    @ThreadSafe
    private static final class NoopViewManager
    extends ViewManager {
        private static final Timestamp ZERO_TIMESTAMP = Timestamp.create(0L, 0);
        @GuardedBy(value="registeredViews")
        private final Map<View.Name, View> registeredViews = Maps.newHashMap();
        @Nullable
        private volatile Set<View> exportedViews;

        private NoopViewManager() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void registerView(View newView) {
            Preconditions.checkNotNull(newView, "newView");
            Map<View.Name, View> map2 = this.registeredViews;
            synchronized (map2) {
                this.exportedViews = null;
                View existing = this.registeredViews.get(newView.getName());
                Preconditions.checkArgument(existing == null || newView.equals(existing), "A different view with the same name already exists.");
                if (existing == null) {
                    this.registeredViews.put(newView.getName(), newView);
                }
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        @Nullable
        public ViewData getView(View.Name name) {
            Preconditions.checkNotNull(name, "name");
            Map<View.Name, View> map2 = this.registeredViews;
            synchronized (map2) {
                View view = this.registeredViews.get(name);
                if (view == null) {
                    return null;
                }
                return ViewData.create(view, Collections.emptyMap(), view.getWindow().match(Functions.returnConstant(ViewData.AggregationWindowData.CumulativeData.create(ZERO_TIMESTAMP, ZERO_TIMESTAMP)), Functions.returnConstant(ViewData.AggregationWindowData.IntervalData.create(ZERO_TIMESTAMP)), Functions.throwAssertionError()));
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Set<View> getAllExportedViews() {
            Set<View> views = this.exportedViews;
            if (views == null) {
                Map<View.Name, View> map2 = this.registeredViews;
                synchronized (map2) {
                    this.exportedViews = views = NoopViewManager.filterExportedViews(this.registeredViews.values());
                }
            }
            return views;
        }

        private static Set<View> filterExportedViews(Collection<View> allViews) {
            HashSet<View> views = Sets.newHashSet();
            for (View view : allViews) {
                if (!(view.getWindow() instanceof View.AggregationWindow.Cumulative)) continue;
                views.add(view);
            }
            return Collections.unmodifiableSet(views);
        }
    }

    @Immutable
    private static final class NoopMeasureMap
    extends MeasureMap {
        static final MeasureMap INSTANCE = new NoopMeasureMap();

        private NoopMeasureMap() {
        }

        @Override
        public MeasureMap put(Measure.MeasureDouble measure, double value) {
            return this;
        }

        @Override
        public MeasureMap put(Measure.MeasureLong measure, long value) {
            return this;
        }

        @Override
        public void record() {
        }

        @Override
        public void record(TagContext tags) {
            Preconditions.checkNotNull(tags, "tags");
        }
    }

    @Immutable
    private static final class NoopStatsRecorder
    extends StatsRecorder {
        static final StatsRecorder INSTANCE = new NoopStatsRecorder();

        private NoopStatsRecorder() {
        }

        @Override
        public MeasureMap newMeasureMap() {
            return NoopStats.getNoopMeasureMap();
        }
    }

    @ThreadSafe
    private static final class NoopStatsComponent
    extends StatsComponent {
        private final ViewManager viewManager = NoopStats.newNoopViewManager();
        private volatile boolean isRead;

        private NoopStatsComponent() {
        }

        @Override
        public ViewManager getViewManager() {
            return this.viewManager;
        }

        @Override
        public StatsRecorder getStatsRecorder() {
            return NoopStats.getNoopStatsRecorder();
        }

        @Override
        public StatsCollectionState getState() {
            this.isRead = true;
            return StatsCollectionState.DISABLED;
        }

        @Override
        @Deprecated
        public void setState(StatsCollectionState state) {
            Preconditions.checkNotNull(state, "state");
            Preconditions.checkState(!this.isRead, "State was already read, cannot set state.");
        }
    }
}

