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

public class FolderName
extends ParentName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("folders/{folder}");
    private volatile Map<String, String> fieldValuesMap;
    private final String folder;

    public String getFolder() {
        return this.folder;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    private FolderName(Builder builder) {
        this.folder = Preconditions.checkNotNull(builder.getFolder());
    }

    public static FolderName of(String folder) {
        return FolderName.newBuilder().setFolder(folder).build();
    }

    public static String format(String folder) {
        return FolderName.newBuilder().setFolder(folder).build().toString();
    }

    public static FolderName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "FolderName.parse: formattedString not in valid format");
        return FolderName.of(matchMap.get("folder"));
    }

    public static List<FolderName> parseList(List<String> formattedStrings) {
        ArrayList<FolderName> list2 = new ArrayList<FolderName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(FolderName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<FolderName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (FolderName value : values) {
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
            FolderName folderName = this;
            synchronized (folderName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("folder", this.folder);
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
        return PATH_TEMPLATE.instantiate("folder", this.folder);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof FolderName) {
            FolderName that = (FolderName)o;
            return this.folder.equals(that.folder);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.folder.hashCode();
    }

    public static class Builder {
        private String folder;

        public String getFolder() {
            return this.folder;
        }

        public Builder setFolder(String folder) {
            this.folder = folder;
            return this;
        }

        private Builder() {
        }

        private Builder(FolderName folderName) {
            this.folder = folderName.folder;
        }

        public FolderName build() {
            return new FolderName(this);
        }
    }
}

