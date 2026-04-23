/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import com.google.common.base.Preconditions;
import io.opencensus.stats.AutoValue_BucketBoundaries;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class BucketBoundaries {
    public static final BucketBoundaries create(List<Double> bucketBoundaries) {
        Preconditions.checkNotNull(bucketBoundaries, "bucketBoundaries list should not be null.");
        ArrayList<Double> bucketBoundariesCopy = new ArrayList<Double>(bucketBoundaries);
        if (bucketBoundariesCopy.size() > 1) {
            double lower = (Double)bucketBoundariesCopy.get(0);
            for (int i = 1; i < bucketBoundariesCopy.size(); ++i) {
                double next2 = (Double)bucketBoundariesCopy.get(i);
                Preconditions.checkArgument(lower < next2, "Bucket boundaries not sorted.");
                lower = next2;
            }
        }
        return new AutoValue_BucketBoundaries(Collections.unmodifiableList(bucketBoundariesCopy));
    }

    public abstract List<Double> getBoundaries();
}

