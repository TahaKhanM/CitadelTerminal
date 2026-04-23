/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.MetricName;
import com.google.logging.v2.ProjectMetricName;
import com.google.logging.v2.UntypedMetricName;

public class MetricNames {
    private MetricNames() {
    }

    public static MetricName parse(String resourceNameString) {
        if (ProjectMetricName.isParsableFrom(resourceNameString)) {
            return ProjectMetricName.parse(resourceNameString);
        }
        return UntypedMetricName.parse(resourceNameString);
    }
}

