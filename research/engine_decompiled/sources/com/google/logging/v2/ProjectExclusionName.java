/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.pathtemplate.PathTemplate;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.logging.v2.ExclusionName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectExclusionName
extends ExclusionName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/exclusions/{exclusion}");
    private volatile Map<String, String> fieldValuesMap;
    private final String project;
    private final String exclusion;

    public String getProject() {
        return this.project;
    }

    public String getExclusion() {
        return this.exclusion;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    private ProjectExclusionName(Builder builder) {
        this.project = Preconditions.checkNotNull(builder.getProject());
        this.exclusion = Preconditions.checkNotNull(builder.getExclusion());
    }

    public static ProjectExclusionName of(String project, String exclusion) {
        return ProjectExclusionName.newBuilder().setProject(project).setExclusion(exclusion).build();
    }

    public static String format(String project, String exclusion) {
        return ProjectExclusionName.newBuilder().setProject(project).setExclusion(exclusion).build().toString();
    }

    public static ProjectExclusionName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "ProjectExclusionName.parse: formattedString not in valid format");
        return ProjectExclusionName.of(matchMap.get("project"), matchMap.get("exclusion"));
    }

    public static List<ProjectExclusionName> parseList(List<String> formattedStrings) {
        ArrayList<ProjectExclusionName> list2 = new ArrayList<ProjectExclusionName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(ProjectExclusionName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<ProjectExclusionName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (ProjectExclusionName value : values) {
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
            ProjectExclusionName projectExclusionName = this;
            synchronized (projectExclusionName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("project", this.project);
                    fieldMapBuilder.put("exclusion", this.exclusion);
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
        return PATH_TEMPLATE.instantiate("project", this.project, "exclusion", this.exclusion);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ProjectExclusionName) {
            ProjectExclusionName that = (ProjectExclusionName)o;
            return this.project.equals(that.project) && this.exclusion.equals(that.exclusion);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.project.hashCode();
        h *= 1000003;
        return h ^= this.exclusion.hashCode();
    }

    public static class Builder {
        private String project;
        private String exclusion;

        public String getProject() {
            return this.project;
        }

        public String getExclusion() {
            return this.exclusion;
        }

        public Builder setProject(String project) {
            this.project = project;
            return this;
        }

        public Builder setExclusion(String exclusion) {
            this.exclusion = exclusion;
            return this;
        }

        private Builder() {
        }

        private Builder(ProjectExclusionName projectExclusionName) {
            this.project = projectExclusionName.project;
            this.exclusion = projectExclusionName.exclusion;
        }

        public ProjectExclusionName build() {
            return new ProjectExclusionName(this);
        }
    }
}

