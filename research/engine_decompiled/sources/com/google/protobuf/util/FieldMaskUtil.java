/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf.util;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.primitives.Ints;
import com.google.protobuf.Descriptors;
import com.google.protobuf.FieldMask;
import com.google.protobuf.Internal;
import com.google.protobuf.Message;
import com.google.protobuf.util.FieldMaskTree;
import java.util.ArrayList;
import java.util.Arrays;

public class FieldMaskUtil {
    private static final String FIELD_PATH_SEPARATOR = ",";
    private static final String FIELD_PATH_SEPARATOR_REGEX = ",";
    private static final String FIELD_SEPARATOR_REGEX = "\\.";

    private FieldMaskUtil() {
    }

    public static String toString(FieldMask fieldMask) {
        StringBuilder result2 = new StringBuilder();
        boolean first = true;
        for (String value : fieldMask.getPathsList()) {
            if (value.isEmpty()) continue;
            if (first) {
                first = false;
            } else {
                result2.append(",");
            }
            result2.append(value);
        }
        return result2.toString();
    }

    public static FieldMask fromString(String value) {
        return FieldMaskUtil.fromStringList(null, Arrays.asList(value.split(",")));
    }

    public static FieldMask fromString(Class<? extends Message> type, String value) {
        return FieldMaskUtil.fromStringList(type, Arrays.asList(value.split(",")));
    }

    public static FieldMask fromStringList(Class<? extends Message> type, Iterable<String> paths) {
        FieldMask.Builder builder = FieldMask.newBuilder();
        for (String path : paths) {
            if (path.isEmpty()) continue;
            if (type != null && !FieldMaskUtil.isValid(type, path)) {
                throw new IllegalArgumentException(path + " is not a valid path for " + type);
            }
            builder.addPaths(path);
        }
        return builder.build();
    }

    public static FieldMask fromFieldNumbers(Class<? extends Message> type, int ... fieldNumbers) {
        return FieldMaskUtil.fromFieldNumbers(type, Ints.asList(fieldNumbers));
    }

    public static FieldMask fromFieldNumbers(Class<? extends Message> type, Iterable<Integer> fieldNumbers) {
        Descriptors.Descriptor descriptor = Internal.getDefaultInstance(type).getDescriptorForType();
        FieldMask.Builder builder = FieldMask.newBuilder();
        for (Integer fieldNumber : fieldNumbers) {
            Descriptors.FieldDescriptor field2 = descriptor.findFieldByNumber(fieldNumber);
            Preconditions.checkArgument(field2 != null, String.format("%s is not a valid field number for %s.", fieldNumber, type));
            builder.addPaths(field2.getName());
        }
        return builder.build();
    }

    public static String toJsonString(FieldMask fieldMask) {
        ArrayList<String> paths = new ArrayList<String>(fieldMask.getPathsCount());
        for (String path : fieldMask.getPathsList()) {
            if (path.isEmpty()) continue;
            paths.add(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, path));
        }
        return Joiner.on(",").join(paths);
    }

    public static FieldMask fromJsonString(String value) {
        Iterable<String> paths = Splitter.on(",").split(value);
        FieldMask.Builder builder = FieldMask.newBuilder();
        for (String path : paths) {
            if (path.isEmpty()) continue;
            builder.addPaths(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, path));
        }
        return builder.build();
    }

    public static boolean isValid(Class<? extends Message> type, FieldMask fieldMask) {
        Descriptors.Descriptor descriptor = Internal.getDefaultInstance(type).getDescriptorForType();
        return FieldMaskUtil.isValid(descriptor, fieldMask);
    }

    public static boolean isValid(Descriptors.Descriptor descriptor, FieldMask fieldMask) {
        for (String path : fieldMask.getPathsList()) {
            if (FieldMaskUtil.isValid(descriptor, path)) continue;
            return false;
        }
        return true;
    }

    public static boolean isValid(Class<? extends Message> type, String path) {
        Descriptors.Descriptor descriptor = Internal.getDefaultInstance(type).getDescriptorForType();
        return FieldMaskUtil.isValid(descriptor, path);
    }

    public static boolean isValid(Descriptors.Descriptor descriptor, String path) {
        String[] parts = path.split(FIELD_SEPARATOR_REGEX);
        if (parts.length == 0) {
            return false;
        }
        for (String name : parts) {
            if (descriptor == null) {
                return false;
            }
            Descriptors.FieldDescriptor field2 = descriptor.findFieldByName(name);
            if (field2 == null) {
                return false;
            }
            descriptor = !field2.isRepeated() && field2.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? field2.getMessageType() : null;
        }
        return true;
    }

    public static FieldMask normalize(FieldMask mask) {
        return new FieldMaskTree(mask).toFieldMask();
    }

    public static FieldMask union(FieldMask firstMask, FieldMask secondMask, FieldMask ... otherMasks) {
        FieldMaskTree maskTree = new FieldMaskTree(firstMask).mergeFromFieldMask(secondMask);
        for (FieldMask mask : otherMasks) {
            maskTree.mergeFromFieldMask(mask);
        }
        return maskTree.toFieldMask();
    }

    public static FieldMask intersection(FieldMask mask1, FieldMask mask2) {
        FieldMaskTree tree = new FieldMaskTree(mask1);
        FieldMaskTree result2 = new FieldMaskTree();
        for (String path : mask2.getPathsList()) {
            tree.intersectFieldPath(path, result2);
        }
        return result2.toFieldMask();
    }

    public static void merge(FieldMask mask, Message source, Message.Builder destination, MergeOptions options) {
        new FieldMaskTree(mask).merge(source, destination, options);
    }

    public static void merge(FieldMask mask, Message source, Message.Builder destination) {
        FieldMaskUtil.merge(mask, source, destination, new MergeOptions());
    }

    public static final class MergeOptions {
        private boolean replaceMessageFields = false;
        private boolean replaceRepeatedFields = false;
        private boolean replacePrimitiveFields = false;

        public boolean replaceMessageFields() {
            return this.replaceMessageFields;
        }

        public boolean replaceRepeatedFields() {
            return this.replaceRepeatedFields;
        }

        public boolean replacePrimitiveFields() {
            return this.replacePrimitiveFields;
        }

        public MergeOptions setReplaceMessageFields(boolean value) {
            this.replaceMessageFields = value;
            return this;
        }

        public MergeOptions setReplaceRepeatedFields(boolean value) {
            this.replaceRepeatedFields = value;
            return this;
        }

        public MergeOptions setReplacePrimitiveFields(boolean value) {
            this.replacePrimitiveFields = value;
            return this;
        }
    }
}

