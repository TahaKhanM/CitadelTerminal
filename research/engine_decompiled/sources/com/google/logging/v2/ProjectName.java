/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.pathtemplate.PathTemplate;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.logging.v2.ParentName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectName
extends ParentName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}");
    private volatile Map<String, String> fieldValuesMap;
    private final String project;

    public String getProject() {
        return this.project;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    private ProjectName(Builder builder) {
        this.project = Preconditions.checkNotNull(builder.getProject());
    }

    public static ProjectName of(String project) {
        return ProjectName.newBuilder().setProject(project).build();
    }

    public static String format(String project) {
        return ProjectName.newBuilder().setProject(project).build().toString();
    }

    public static ProjectName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "ProjectName.parse: formattedString not in valid format");
        return ProjectName.of(matchMap.get("project"));
    }

    public static List<ProjectName> parseList(List<String> formattedStrings) {
        ArrayList<ProjectName> list2 = new ArrayList<ProjectName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(ProjectName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<ProjectName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (ProjectName value : values) {
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
            ProjectName projectName = this;
            synchronized (projectName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("project", this.project);
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
        return PATH_TEMPLATE.instantiate("project", this.project);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ProjectName) {
            ProjectName that = (ProjectName)o;
            return this.project.equals(that.project);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.project.hashCode();
    }

    public static class Builder {
        private String project;

        public String getProject() {
            return this.project;
        }

        public Builder setProject(String project) {
            this.project = project;
            return this;
        }

        private Builder() {
        }

        private Builder(ProjectName projectName) {
            this.project = projectName.project;
        }

        public ProjectName build() {
            return new ProjectName(this);
        }
    }
}

