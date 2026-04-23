/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.AggregationData;
import io.opencensus.stats.View;
import io.opencensus.stats.ViewData;
import io.opencensus.tags.TagValue;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_ViewData
extends ViewData {
    private final View view;
    private final Map<List<TagValue>, AggregationData> aggregationMap;
    private final ViewData.AggregationWindowData windowData;

    AutoValue_ViewData(View view, Map<List<TagValue>, AggregationData> aggregationMap, ViewData.AggregationWindowData windowData) {
        if (view == null) {
            throw new NullPointerException("Null view");
        }
        this.view = view;
        if (aggregationMap == null) {
            throw new NullPointerException("Null aggregationMap");
        }
        this.aggregationMap = aggregationMap;
        if (windowData == null) {
            throw new NullPointerException("Null windowData");
        }
        this.windowData = windowData;
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    public Map<List<TagValue>, AggregationData> getAggregationMap() {
        return this.aggregationMap;
    }

    @Override
    public ViewData.AggregationWindowData getWindowData() {
        return this.windowData;
    }

    public String toString() {
        return "ViewData{view=" + this.view + ", aggregationMap=" + this.aggregationMap + ", windowData=" + this.windowData + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ViewData) {
            ViewData that = (ViewData)o;
            return this.view.equals(that.getView()) && this.aggregationMap.equals(that.getAggregationMap()) && this.windowData.equals(that.getWindowData());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.view.hashCode();
        h *= 1000003;
        h ^= this.aggregationMap.hashCode();
        h *= 1000003;
        return h ^= this.windowData.hashCode();
    }
}

