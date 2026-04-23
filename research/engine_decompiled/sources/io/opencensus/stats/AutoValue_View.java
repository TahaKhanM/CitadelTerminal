/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.Aggregation;
import io.opencensus.stats.Measure;
import io.opencensus.stats.View;
import io.opencensus.tags.TagKey;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_View
extends View {
    private final View.Name name;
    private final String description;
    private final Measure measure;
    private final Aggregation aggregation;
    private final List<TagKey> columns;
    private final View.AggregationWindow window;

    AutoValue_View(View.Name name, String description, Measure measure, Aggregation aggregation, List<TagKey> columns, View.AggregationWindow window) {
        if (name == null) {
            throw new NullPointerException("Null name");
        }
        this.name = name;
        if (description == null) {
            throw new NullPointerException("Null description");
        }
        this.description = description;
        if (measure == null) {
            throw new NullPointerException("Null measure");
        }
        this.measure = measure;
        if (aggregation == null) {
            throw new NullPointerException("Null aggregation");
        }
        this.aggregation = aggregation;
        if (columns == null) {
            throw new NullPointerException("Null columns");
        }
        this.columns = columns;
        if (window == null) {
            throw new NullPointerException("Null window");
        }
        this.window = window;
    }

    @Override
    public View.Name getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Measure getMeasure() {
        return this.measure;
    }

    @Override
    public Aggregation getAggregation() {
        return this.aggregation;
    }

    @Override
    public List<TagKey> getColumns() {
        return this.columns;
    }

    @Override
    public View.AggregationWindow getWindow() {
        return this.window;
    }

    public String toString() {
        return "View{name=" + this.name + ", description=" + this.description + ", measure=" + this.measure + ", aggregation=" + this.aggregation + ", columns=" + this.columns + ", window=" + this.window + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof View) {
            View that = (View)o;
            return this.name.equals(that.getName()) && this.description.equals(that.getDescription()) && this.measure.equals(that.getMeasure()) && this.aggregation.equals(that.getAggregation()) && this.columns.equals(that.getColumns()) && this.window.equals(that.getWindow());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.name.hashCode();
        h *= 1000003;
        h ^= this.description.hashCode();
        h *= 1000003;
        h ^= this.measure.hashCode();
        h *= 1000003;
        h ^= this.aggregation.hashCode();
        h *= 1000003;
        h ^= this.columns.hashCode();
        h *= 1000003;
        return h ^= this.window.hashCode();
    }
}

