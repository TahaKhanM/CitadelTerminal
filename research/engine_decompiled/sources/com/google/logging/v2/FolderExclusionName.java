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

public class FolderExclusionName
extends ExclusionName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("folders/{folder}/exclusions/{exclusion}");
    private volatile Map<String, String> fieldValuesMap;
    private final String folder;
    private final String exclusion;

    public String getFolder() {
        return this.folder;
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

    private FolderExclusionName(Builder builder) {
        this.folder = Preconditions.checkNotNull(builder.getFolder());
        this.exclusion = Preconditions.checkNotNull(builder.getExclusion());
    }

    public static FolderExclusionName of(String folder, String exclusion) {
        return FolderExclusionName.newBuilder().setFolder(folder).setExclusion(exclusion).build();
    }

    public static String format(String folder, String exclusion) {
        return FolderExclusionName.newBuilder().setFolder(folder).setExclusion(exclusion).build().toString();
    }

    public static FolderExclusionName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "FolderExclusionName.parse: formattedString not in valid format");
        return FolderExclusionName.of(matchMap.get("folder"), matchMap.get("exclusion"));
    }

    public static List<FolderExclusionName> parseList(List<String> formattedStrings) {
        ArrayList<FolderExclusionName> list2 = new ArrayList<FolderExclusionName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(FolderExclusionName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<FolderExclusionName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (FolderExclusionName value : values) {
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
            FolderExclusionName folderExclusionName = this;
            synchronized (folderExclusionName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("folder", this.folder);
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
        return PATH_TEMPLATE.instantiate("folder", this.folder, "exclusion", this.exclusion);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof FolderExclusionName) {
            FolderExclusionName that = (FolderExclusionName)o;
            return this.folder.equals(that.folder) && this.exclusion.equals(that.exclusion);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.folder.hashCode();
        h *= 1000003;
        return h ^= this.exclusion.hashCode();
    }

    public static class Builder {
        private String folder;
        private String exclusion;

        public String getFolder() {
            return this.folder;
        }

        public String getExclusion() {
            return this.exclusion;
        }

        public Builder setFolder(String folder) {
            this.folder = folder;
            return this;
        }

        public Builder setExclusion(String exclusion) {
            this.exclusion = exclusion;
            return this;
        }

        private Builder() {
        }

        private Builder(FolderExclusionName folderExclusionName) {
            this.folder = folderExclusionName.folder;
            this.exclusion = folderExclusionName.exclusion;
        }

        public FolderExclusionName build() {
            return new FolderExclusionName(this);
        }
    }
}

