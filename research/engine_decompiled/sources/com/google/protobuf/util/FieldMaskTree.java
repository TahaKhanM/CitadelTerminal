/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf.util;

import com.google.protobuf.Descriptors;
import com.google.protobuf.FieldMask;
import com.google.protobuf.Message;
import com.google.protobuf.util.FieldMaskUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

final class FieldMaskTree {
    private static final Logger logger = Logger.getLogger(FieldMaskTree.class.getName());
    private static final String FIELD_PATH_SEPARATOR_REGEX = "\\.";
    private final Node root = new Node();

    FieldMaskTree() {
    }

    FieldMaskTree(FieldMask mask) {
        this.mergeFromFieldMask(mask);
    }

    public String toString() {
        return FieldMaskUtil.toString(this.toFieldMask());
    }

    FieldMaskTree addFieldPath(String path) {
        String[] parts = path.split(FIELD_PATH_SEPARATOR_REGEX);
        if (parts.length == 0) {
            return this;
        }
        Node node = this.root;
        boolean createNewBranch = false;
        for (String part : parts) {
            if (!createNewBranch && node != this.root && node.children.isEmpty()) {
                return this;
            }
            if (node.children.containsKey(part)) {
                node = (Node)node.children.get(part);
                continue;
            }
            createNewBranch = true;
            Node tmp = new Node();
            node.children.put(part, tmp);
            node = tmp;
        }
        node.children.clear();
        return this;
    }

    FieldMaskTree mergeFromFieldMask(FieldMask mask) {
        for (String path : mask.getPathsList()) {
            this.addFieldPath(path);
        }
        return this;
    }

    FieldMask toFieldMask() {
        if (this.root.children.isEmpty()) {
            return FieldMask.getDefaultInstance();
        }
        ArrayList<String> paths = new ArrayList<String>();
        this.getFieldPaths(this.root, "", paths);
        return FieldMask.newBuilder().addAllPaths(paths).build();
    }

    private void getFieldPaths(Node node, String path, List<String> paths) {
        if (node.children.isEmpty()) {
            paths.add(path);
            return;
        }
        for (Map.Entry<String, Node> entry : node.children.entrySet()) {
            String childPath = path.isEmpty() ? entry.getKey() : path + "." + entry.getKey();
            this.getFieldPaths(entry.getValue(), childPath, paths);
        }
    }

    void intersectFieldPath(String path, FieldMaskTree output) {
        if (this.root.children.isEmpty()) {
            return;
        }
        String[] parts = path.split(FIELD_PATH_SEPARATOR_REGEX);
        if (parts.length == 0) {
            return;
        }
        Node node = this.root;
        for (String part : parts) {
            if (node != this.root && node.children.isEmpty()) {
                output.addFieldPath(path);
                return;
            }
            if (!node.children.containsKey(part)) {
                return;
            }
            node = (Node)node.children.get(part);
        }
        ArrayList<String> paths = new ArrayList<String>();
        this.getFieldPaths(node, path, paths);
        for (String value : paths) {
            output.addFieldPath(value);
        }
    }

    void merge(Message source, Message.Builder destination, FieldMaskUtil.MergeOptions options) {
        if (source.getDescriptorForType() != destination.getDescriptorForType()) {
            throw new IllegalArgumentException("Cannot merge messages of different types.");
        }
        if (this.root.children.isEmpty()) {
            return;
        }
        this.merge(this.root, "", source, destination, options);
    }

    private void merge(Node node, String path, Message source, Message.Builder destination, FieldMaskUtil.MergeOptions options) {
        if (source.getDescriptorForType() != destination.getDescriptorForType()) {
            throw new IllegalArgumentException(String.format("source (%s) and destination (%s) descriptor must be equal", source.getDescriptorForType(), destination.getDescriptorForType()));
        }
        Descriptors.Descriptor descriptor = source.getDescriptorForType();
        for (Map.Entry<String, Node> entry : node.children.entrySet()) {
            Descriptors.FieldDescriptor field2 = descriptor.findFieldByName(entry.getKey());
            if (field2 == null) {
                logger.warning("Cannot find field \"" + entry.getKey() + "\" in message type " + descriptor.getFullName());
                continue;
            }
            if (!entry.getValue().children.isEmpty()) {
                if (field2.isRepeated() || field2.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    logger.warning("Field \"" + field2.getFullName() + "\" is not a singluar message field and cannot have sub-fields.");
                    continue;
                }
                if (!source.hasField(field2) && !destination.hasField(field2)) continue;
                String childPath = path.isEmpty() ? entry.getKey() : path + "." + entry.getKey();
                this.merge(entry.getValue(), childPath, (Message)source.getField(field2), destination.getFieldBuilder(field2), options);
                continue;
            }
            if (field2.isRepeated()) {
                if (options.replaceRepeatedFields()) {
                    destination.setField(field2, source.getField(field2));
                    continue;
                }
                for (Object element : (List)source.getField(field2)) {
                    destination.addRepeatedField(field2, element);
                }
                continue;
            }
            if (field2.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (options.replaceMessageFields()) {
                    if (!source.hasField(field2)) {
                        destination.clearField(field2);
                        continue;
                    }
                    destination.setField(field2, source.getField(field2));
                    continue;
                }
                if (!source.hasField(field2)) continue;
                destination.getFieldBuilder(field2).mergeFrom((Message)source.getField(field2));
                continue;
            }
            if (source.hasField(field2) || !options.replacePrimitiveFields()) {
                destination.setField(field2, source.getField(field2));
                continue;
            }
            destination.clearField(field2);
        }
    }

    private static final class Node {
        final SortedMap<String, Node> children = new TreeMap<String, Node>();

        private Node() {
        }
    }
}

