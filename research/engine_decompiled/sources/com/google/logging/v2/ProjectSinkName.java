/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.pathtemplate.PathTemplate;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.logging.v2.SinkName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectSinkName
extends SinkName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/sinks/{sink}");
    private volatile Map<String, String> fieldValuesMap;
    private final String project;
    private final String sink;

    public String getProject() {
        return this.project;
    }

    public String getSink() {
        return this.sink;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    private ProjectSinkName(Builder builder) {
        this.project = Preconditions.checkNotNull(builder.getProject());
        this.sink = Preconditions.checkNotNull(builder.getSink());
    }

    public static ProjectSinkName of(String project, String sink) {
        return ProjectSinkName.newBuilder().setProject(project).setSink(sink).build();
    }

    public static String format(String project, String sink) {
        return ProjectSinkName.newBuilder().setProject(project).setSink(sink).build().toString();
    }

    public static ProjectSinkName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "ProjectSinkName.parse: formattedString not in valid format");
        return ProjectSinkName.of(matchMap.get("project"), matchMap.get("sink"));
    }

    public static List<ProjectSinkName> parseList(List<String> formattedStrings) {
        ArrayList<ProjectSinkName> list2 = new ArrayList<ProjectSinkName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(ProjectSinkName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<ProjectSinkName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (ProjectSinkName value : values) {
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
            ProjectSinkName projectSinkName = this;
            synchronized (projectSinkName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("project", this.project);
                    fieldMapBuilder.put("sink", this.sink);
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
        return PATH_TEMPLATE.instantiate("project", this.project, "sink", this.sink);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ProjectSinkName) {
            ProjectSinkName that = (ProjectSinkName)o;
            return this.project.equals(that.project) && this.sink.equals(that.sink);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.project.hashCode();
        h *= 1000003;
        return h ^= this.sink.hashCode();
    }

    public static class Builder {
        private String project;
        private String sink;

        public String getProject() {
            return this.project;
        }

        public String getSink() {
            return this.sink;
        }

        public Builder setProject(String project) {
            this.project = project;
            return this;
        }

        public Builder setSink(String sink) {
            this.sink = sink;
            return this;
        }

        private Builder() {
        }

        private Builder(ProjectSinkName projectSinkName) {
            this.project = projectSinkName.project;
            this.sink = projectSinkName.sink;
        }

        public ProjectSinkName build() {
            return new ProjectSinkName(this);
        }
    }
}

