/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.pathtemplate.PathTemplate;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.logging.v2.LogName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectLogName
extends LogName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/logs/{log}");
    private volatile Map<String, String> fieldValuesMap;
    private final String project;
    private final String log;

    public String getProject() {
        return this.project;
    }

    public String getLog() {
        return this.log;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    private ProjectLogName(Builder builder) {
        this.project = Preconditions.checkNotNull(builder.getProject());
        this.log = Preconditions.checkNotNull(builder.getLog());
    }

    public static ProjectLogName of(String project, String log) {
        return ProjectLogName.newBuilder().setProject(project).setLog(log).build();
    }

    public static String format(String project, String log) {
        return ProjectLogName.newBuilder().setProject(project).setLog(log).build().toString();
    }

    public static ProjectLogName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "ProjectLogName.parse: formattedString not in valid format");
        return ProjectLogName.of(matchMap.get("project"), matchMap.get("log"));
    }

    public static List<ProjectLogName> parseList(List<String> formattedStrings) {
        ArrayList<ProjectLogName> list2 = new ArrayList<ProjectLogName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(ProjectLogName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<ProjectLogName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (ProjectLogName value : values) {
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
            ProjectLogName projectLogName = this;
            synchronized (projectLogName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("project", this.project);
                    fieldMapBuilder.put("log", this.log);
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
        return PATH_TEMPLATE.instantiate("project", this.project, "log", this.log);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ProjectLogName) {
            ProjectLogName that = (ProjectLogName)o;
            return this.project.equals(that.project) && this.log.equals(that.log);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.project.hashCode();
        h *= 1000003;
        return h ^= this.log.hashCode();
    }

    public static class Builder {
        private String project;
        private String log;

        public String getProject() {
            return this.project;
        }

        public String getLog() {
            return this.log;
        }

        public Builder setProject(String project) {
            this.project = project;
            return this;
        }

        public Builder setLog(String log) {
            this.log = log;
            return this;
        }

        private Builder() {
        }

        private Builder(ProjectLogName projectLogName) {
            this.project = projectLogName.project;
            this.log = projectLogName.log;
        }

        public ProjectLogName build() {
            return new ProjectLogName(this);
        }
    }
}

