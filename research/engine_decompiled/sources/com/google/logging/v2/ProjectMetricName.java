/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.pathtemplate.PathTemplate;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.logging.v2.MetricName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectMetricName
extends MetricName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/metrics/{metric}");
    private volatile Map<String, String> fieldValuesMap;
    private final String project;
    private final String metric;

    public String getProject() {
        return this.project;
    }

    public String getMetric() {
        return this.metric;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    private ProjectMetricName(Builder builder) {
        this.project = Preconditions.checkNotNull(builder.getProject());
        this.metric = Preconditions.checkNotNull(builder.getMetric());
    }

    public static ProjectMetricName of(String project, String metric) {
        return ProjectMetricName.newBuilder().setProject(project).setMetric(metric).build();
    }

    public static String format(String project, String metric) {
        return ProjectMetricName.newBuilder().setProject(project).setMetric(metric).build().toString();
    }

    public static ProjectMetricName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "ProjectMetricName.parse: formattedString not in valid format");
        return ProjectMetricName.of(matchMap.get("project"), matchMap.get("metric"));
    }

    public static List<ProjectMetricName> parseList(List<String> formattedStrings) {
        ArrayList<ProjectMetricName> list2 = new ArrayList<ProjectMetricName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(ProjectMetricName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<ProjectMetricName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (ProjectMetricName value : values) {
            if (value == null) {
                list2.add("");
                continue;
            }
            list2.add(value.toString());
        }
        return list2;
    }

    public static boolean isParsableFrom(String formattedString) {
        return PATH_TEMPLATE.matches(formattedString);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Map<String, String> getFieldValuesMap() {
        if (this.fieldValuesMap == null) {
            ProjectMetricName projectMetricName = this;
            synchronized (projectMetricName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("project", this.project);
                    fieldMapBuilder.put("metric", this.metric);
                    this.fieldValuesMap = fieldMapBuilder.build();
                }
            }
        }
        return this.fieldValuesMap;
    }

    @Override
    public String getFieldValue(String fieldName) {
        return this.getFieldValuesMap().get(fieldName);
    }

    public String toString() {
        return PATH_TEMPLATE.instantiate("project", this.project, "metric", this.metric);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ProjectMetricName) {
            ProjectMetricName that = (ProjectMetricName)o;
            return this.project.equals(that.project) && this.metric.equals(that.metric);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.project.hashCode();
        h *= 1000003;
        return h ^= this.metric.hashCode();
    }

    public static class Builder {
        private String project;
        private String metric;

        public String getProject() {
            return this.project;
        }

        public String getMetric() {
            return this.metric;
        }

        public Builder setProject(String project) {
            this.project = project;
            return this;
        }

        public Builder setMetric(String metric) {
            this.metric = metric;
            return this;
        }

        private Builder() {
        }

        private Builder(ProjectMetricName projectMetricName) {
            this.project = projectMetricName.project;
            this.metric = projectMetricName.metric;
        }

        public ProjectMetricName build() {
            return new ProjectMetricName(this);
        }
    }
}

