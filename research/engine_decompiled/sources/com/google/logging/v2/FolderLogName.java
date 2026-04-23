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

public class FolderLogName
extends LogName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("folders/{folder}/logs/{log}");
    private volatile Map<String, String> fieldValuesMap;
    private final String folder;
    private final String log;

    public String getFolder() {
        return this.folder;
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

    private FolderLogName(Builder builder) {
        this.folder = Preconditions.checkNotNull(builder.getFolder());
        this.log = Preconditions.checkNotNull(builder.getLog());
    }

    public static FolderLogName of(String folder, String log) {
        return FolderLogName.newBuilder().setFolder(folder).setLog(log).build();
    }

    public static String format(String folder, String log) {
        return FolderLogName.newBuilder().setFolder(folder).setLog(log).build().toString();
    }

    public static FolderLogName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "FolderLogName.parse: formattedString not in valid format");
        return FolderLogName.of(matchMap.get("folder"), matchMap.get("log"));
    }

    public static List<FolderLogName> parseList(List<String> formattedStrings) {
        ArrayList<FolderLogName> list2 = new ArrayList<FolderLogName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(FolderLogName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<FolderLogName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (FolderLogName value : values) {
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
            FolderLogName folderLogName = this;
            synchronized (folderLogName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("folder", this.folder);
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
        return PATH_TEMPLATE.instantiate("folder", this.folder, "log", this.log);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof FolderLogName) {
            FolderLogName that = (FolderLogName)o;
            return this.folder.equals(that.folder) && this.log.equals(that.log);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.folder.hashCode();
        h *= 1000003;
        return h ^= this.log.hashCode();
    }

    public static class Builder {
        private String folder;
        private String log;

        public String getFolder() {
            return this.folder;
        }

        public String getLog() {
            return this.log;
        }

        public Builder setFolder(String folder) {
            this.folder = folder;
            return this;
        }

        public Builder setLog(String log) {
            this.log = log;
            return this;
        }

        private Builder() {
        }

        private Builder(FolderLogName folderLogName) {
            this.folder = folderLogName.folder;
            this.log = folderLogName.log;
        }

        public FolderLogName build() {
            return new FolderLogName(this);
        }
    }
}

