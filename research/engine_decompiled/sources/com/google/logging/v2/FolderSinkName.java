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

public class FolderSinkName
extends SinkName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("folders/{folder}/sinks/{sink}");
    private volatile Map<String, String> fieldValuesMap;
    private final String folder;
    private final String sink;

    public String getFolder() {
        return this.folder;
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

    private FolderSinkName(Builder builder) {
        this.folder = Preconditions.checkNotNull(builder.getFolder());
        this.sink = Preconditions.checkNotNull(builder.getSink());
    }

    public static FolderSinkName of(String folder, String sink) {
        return FolderSinkName.newBuilder().setFolder(folder).setSink(sink).build();
    }

    public static String format(String folder, String sink) {
        return FolderSinkName.newBuilder().setFolder(folder).setSink(sink).build().toString();
    }

    public static FolderSinkName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "FolderSinkName.parse: formattedString not in valid format");
        return FolderSinkName.of(matchMap.get("folder"), matchMap.get("sink"));
    }

    public static List<FolderSinkName> parseList(List<String> formattedStrings) {
        ArrayList<FolderSinkName> list2 = new ArrayList<FolderSinkName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(FolderSinkName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<FolderSinkName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (FolderSinkName value : values) {
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
            FolderSinkName folderSinkName = this;
            synchronized (folderSinkName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("folder", this.folder);
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
        return PATH_TEMPLATE.instantiate("folder", this.folder, "sink", this.sink);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof FolderSinkName) {
            FolderSinkName that = (FolderSinkName)o;
            return this.folder.equals(that.folder) && this.sink.equals(that.sink);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.folder.hashCode();
        h *= 1000003;
        return h ^= this.sink.hashCode();
    }

    public static class Builder {
        private String folder;
        private String sink;

        public String getFolder() {
            return this.folder;
        }

        public String getSink() {
            return this.sink;
        }

        public Builder setFolder(String folder) {
            this.folder = folder;
            return this;
        }

        public Builder setSink(String sink) {
            this.sink = sink;
            return this;
        }

        private Builder() {
        }

        private Builder(FolderSinkName folderSinkName) {
            this.folder = folderSinkName.folder;
            this.sink = folderSinkName.sink;
        }

        public FolderSinkName build() {
            return new FolderSinkName(this);
        }
    }
}

