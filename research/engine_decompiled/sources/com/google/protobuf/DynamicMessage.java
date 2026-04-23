/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class DynamicMessage
extends AbstractMessage {
    private final Descriptors.Descriptor type;
    private final FieldSet<Descriptors.FieldDescriptor> fields;
    private final Descriptors.FieldDescriptor[] oneofCases;
    private final UnknownFieldSet unknownFields;
    private int memoizedSize = -1;

    DynamicMessage(Descriptors.Descriptor type, FieldSet<Descriptors.FieldDescriptor> fields, Descriptors.FieldDescriptor[] oneofCases, UnknownFieldSet unknownFields) {
        this.type = type;
        this.fields = fields;
        this.oneofCases = oneofCases;
        this.unknownFields = unknownFields;
    }

    public static DynamicMessage getDefaultInstance(Descriptors.Descriptor type) {
        int oneofDeclCount = type.toProto().getOneofDeclCount();
        Descriptors.FieldDescriptor[] oneofCases = new Descriptors.FieldDescriptor[oneofDeclCount];
        return new DynamicMessage(type, FieldSet.emptySet(), oneofCases, UnknownFieldSet.getDefaultInstance());
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, CodedInputStream input2) throws IOException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(input2)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, CodedInputStream input2, ExtensionRegistry extensionRegistry) throws IOException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(input2, (ExtensionRegistryLite)extensionRegistry)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, ByteString data) throws InvalidProtocolBufferException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(data)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, ByteString data, ExtensionRegistry extensionRegistry) throws InvalidProtocolBufferException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(data, (ExtensionRegistryLite)extensionRegistry)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, byte[] data) throws InvalidProtocolBufferException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(data)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, byte[] data, ExtensionRegistry extensionRegistry) throws InvalidProtocolBufferException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(data, (ExtensionRegistryLite)extensionRegistry)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, InputStream input2) throws IOException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(input2)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, InputStream input2, ExtensionRegistry extensionRegistry) throws IOException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(input2, (ExtensionRegistryLite)extensionRegistry)).buildParsed();
    }

    public static Builder newBuilder(Descriptors.Descriptor type) {
        return new Builder(type);
    }

    public static Builder newBuilder(Message prototype) {
        return new Builder(prototype.getDescriptorForType()).mergeFrom(prototype);
    }

    @Override
    public Descriptors.Descriptor getDescriptorForType() {
        return this.type;
    }

    @Override
    public DynamicMessage getDefaultInstanceForType() {
        return DynamicMessage.getDefaultInstance(this.type);
    }

    @Override
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        return this.fields.getAllFields();
    }

    @Override
    public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
        this.verifyOneofContainingType(oneof);
        Descriptors.FieldDescriptor field2 = this.oneofCases[oneof.getIndex()];
        return field2 != null;
    }

    @Override
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
        this.verifyOneofContainingType(oneof);
        return this.oneofCases[oneof.getIndex()];
    }

    @Override
    public boolean hasField(Descriptors.FieldDescriptor field2) {
        this.verifyContainingType(field2);
        return this.fields.hasField(field2);
    }

    @Override
    public Object getField(Descriptors.FieldDescriptor field2) {
        this.verifyContainingType(field2);
        Object result2 = this.fields.getField(field2);
        if (result2 == null) {
            result2 = field2.isRepeated() ? Collections.emptyList() : (field2.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(field2.getMessageType()) : field2.getDefaultValue());
        }
        return result2;
    }

    @Override
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor field2) {
        this.verifyContainingType(field2);
        return this.fields.getRepeatedFieldCount(field2);
    }

    @Override
    public Object getRepeatedField(Descriptors.FieldDescriptor field2, int index) {
        this.verifyContainingType(field2);
        return this.fields.getRepeatedField(field2, index);
    }

    @Override
    public UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    static boolean isInitialized(Descriptors.Descriptor type, FieldSet<Descriptors.FieldDescriptor> fields) {
        for (Descriptors.FieldDescriptor field2 : type.getFields()) {
            if (!field2.isRequired() || fields.hasField(field2)) continue;
            return false;
        }
        return fields.isInitialized();
    }

    @Override
    public boolean isInitialized() {
        return DynamicMessage.isInitialized(this.type, this.fields);
    }

    @Override
    public void writeTo(CodedOutputStream output) throws IOException {
        if (this.type.getOptions().getMessageSetWireFormat()) {
            this.fields.writeMessageSetTo(output);
            this.unknownFields.writeAsMessageSetTo(output);
        } else {
            this.fields.writeTo(output);
            this.unknownFields.writeTo(output);
        }
    }

    @Override
    public int getSerializedSize() {
        int size2 = this.memoizedSize;
        if (size2 != -1) {
            return size2;
        }
        if (this.type.getOptions().getMessageSetWireFormat()) {
            size2 = this.fields.getMessageSetSerializedSize();
            size2 += this.unknownFields.getSerializedSizeAsMessageSet();
        } else {
            size2 = this.fields.getSerializedSize();
            size2 += this.unknownFields.getSerializedSize();
        }
        this.memoizedSize = size2;
        return size2;
    }

    @Override
    public Builder newBuilderForType() {
        return new Builder(this.type);
    }

    @Override
    public Builder toBuilder() {
        return this.newBuilderForType().mergeFrom(this);
    }

    public Parser<DynamicMessage> getParserForType() {
        return new AbstractParser<DynamicMessage>(){

            @Override
            public DynamicMessage parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builder = DynamicMessage.newBuilder(DynamicMessage.this.type);
                try {
                    builder.mergeFrom(input2, extensionRegistry);
                }
                catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builder.buildPartial());
                }
                catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(builder.buildPartial());
                }
                return builder.buildPartial();
            }
        };
    }

    private void verifyContainingType(Descriptors.FieldDescriptor field2) {
        if (field2.getContainingType() != this.type) {
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
        }
    }

    private void verifyOneofContainingType(Descriptors.OneofDescriptor oneof) {
        if (oneof.getContainingType() != this.type) {
            throw new IllegalArgumentException("OneofDescriptor does not match message type.");
        }
    }

    public static final class Builder
    extends AbstractMessage.Builder<Builder> {
        private final Descriptors.Descriptor type;
        private FieldSet<Descriptors.FieldDescriptor> fields;
        private final Descriptors.FieldDescriptor[] oneofCases;
        private UnknownFieldSet unknownFields;

        private Builder(Descriptors.Descriptor type) {
            this.type = type;
            this.fields = FieldSet.newFieldSet();
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            this.oneofCases = new Descriptors.FieldDescriptor[type.toProto().getOneofDeclCount()];
            if (type.getOptions().getMapEntry()) {
                this.populateMapEntry();
            }
        }

        private void populateMapEntry() {
            for (Descriptors.FieldDescriptor field2 : this.type.getFields()) {
                if (field2.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    this.fields.setField(field2, DynamicMessage.getDefaultInstance(field2.getMessageType()));
                    continue;
                }
                this.fields.setField(field2, field2.getDefaultValue());
            }
        }

        @Override
        public Builder clear() {
            if (this.fields.isImmutable()) {
                this.fields = FieldSet.newFieldSet();
            } else {
                this.fields.clear();
            }
            if (this.type.getOptions().getMapEntry()) {
                this.populateMapEntry();
            }
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            return this;
        }

        @Override
        public Builder mergeFrom(Message other) {
            if (other instanceof DynamicMessage) {
                DynamicMessage otherDynamicMessage = (DynamicMessage)other;
                if (otherDynamicMessage.type != this.type) {
                    throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
                }
                this.ensureIsMutable();
                this.fields.mergeFrom(otherDynamicMessage.fields);
                this.mergeUnknownFields(otherDynamicMessage.unknownFields);
                for (int i = 0; i < this.oneofCases.length; ++i) {
                    if (this.oneofCases[i] == null) {
                        this.oneofCases[i] = otherDynamicMessage.oneofCases[i];
                        continue;
                    }
                    if (otherDynamicMessage.oneofCases[i] == null || this.oneofCases[i] == otherDynamicMessage.oneofCases[i]) continue;
                    this.fields.clearField(this.oneofCases[i]);
                    this.oneofCases[i] = otherDynamicMessage.oneofCases[i];
                }
                return this;
            }
            return (Builder)super.mergeFrom(other);
        }

        @Override
        public DynamicMessage build() {
            if (!this.isInitialized()) {
                throw Builder.newUninitializedMessageException(new DynamicMessage(this.type, this.fields, Arrays.copyOf(this.oneofCases, this.oneofCases.length), this.unknownFields));
            }
            return this.buildPartial();
        }

        private DynamicMessage buildParsed() throws InvalidProtocolBufferException {
            if (!this.isInitialized()) {
                throw Builder.newUninitializedMessageException(new DynamicMessage(this.type, this.fields, Arrays.copyOf(this.oneofCases, this.oneofCases.length), this.unknownFields)).asInvalidProtocolBufferException();
            }
            return this.buildPartial();
        }

        @Override
        public DynamicMessage buildPartial() {
            this.fields.makeImmutable();
            DynamicMessage result2 = new DynamicMessage(this.type, this.fields, Arrays.copyOf(this.oneofCases, this.oneofCases.length), this.unknownFields);
            return result2;
        }

        @Override
        public Builder clone() {
            Builder result2 = new Builder(this.type);
            result2.fields.mergeFrom(this.fields);
            result2.mergeUnknownFields(this.unknownFields);
            System.arraycopy(this.oneofCases, 0, result2.oneofCases, 0, this.oneofCases.length);
            return result2;
        }

        @Override
        public boolean isInitialized() {
            return DynamicMessage.isInitialized(this.type, this.fields);
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return this.type;
        }

        @Override
        public DynamicMessage getDefaultInstanceForType() {
            return DynamicMessage.getDefaultInstance(this.type);
        }

        @Override
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            return this.fields.getAllFields();
        }

        @Override
        public Builder newBuilderForField(Descriptors.FieldDescriptor field2) {
            this.verifyContainingType(field2);
            if (field2.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                throw new IllegalArgumentException("newBuilderForField is only valid for fields with message type.");
            }
            return new Builder(field2.getMessageType());
        }

        @Override
        public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
            this.verifyOneofContainingType(oneof);
            Descriptors.FieldDescriptor field2 = this.oneofCases[oneof.getIndex()];
            return field2 != null;
        }

        @Override
        public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
            this.verifyOneofContainingType(oneof);
            return this.oneofCases[oneof.getIndex()];
        }

        @Override
        public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
            this.verifyOneofContainingType(oneof);
            Descriptors.FieldDescriptor field2 = this.oneofCases[oneof.getIndex()];
            if (field2 != null) {
                this.clearField(field2);
            }
            return this;
        }

        @Override
        public boolean hasField(Descriptors.FieldDescriptor field2) {
            this.verifyContainingType(field2);
            return this.fields.hasField(field2);
        }

        @Override
        public Object getField(Descriptors.FieldDescriptor field2) {
            this.verifyContainingType(field2);
            Object result2 = this.fields.getField(field2);
            if (result2 == null) {
                result2 = field2.isRepeated() ? Collections.emptyList() : (field2.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(field2.getMessageType()) : field2.getDefaultValue());
            }
            return result2;
        }

        @Override
        public Builder setField(Descriptors.FieldDescriptor field2, Object value) {
            Descriptors.OneofDescriptor oneofDescriptor;
            this.verifyContainingType(field2);
            this.ensureIsMutable();
            if (field2.getType() == Descriptors.FieldDescriptor.Type.ENUM) {
                this.ensureEnumValueDescriptor(field2, value);
            }
            if ((oneofDescriptor = field2.getContainingOneof()) != null) {
                int index = oneofDescriptor.getIndex();
                Descriptors.FieldDescriptor oldField = this.oneofCases[index];
                if (oldField != null && oldField != field2) {
                    this.fields.clearField(oldField);
                }
                this.oneofCases[index] = field2;
            } else if (field2.getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO3 && !field2.isRepeated() && field2.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE && value.equals(field2.getDefaultValue())) {
                this.fields.clearField(field2);
                return this;
            }
            this.fields.setField(field2, value);
            return this;
        }

        @Override
        public Builder clearField(Descriptors.FieldDescriptor field2) {
            int index;
            this.verifyContainingType(field2);
            this.ensureIsMutable();
            Descriptors.OneofDescriptor oneofDescriptor = field2.getContainingOneof();
            if (oneofDescriptor != null && this.oneofCases[index = oneofDescriptor.getIndex()] == field2) {
                this.oneofCases[index] = null;
            }
            this.fields.clearField(field2);
            return this;
        }

        @Override
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor field2) {
            this.verifyContainingType(field2);
            return this.fields.getRepeatedFieldCount(field2);
        }

        @Override
        public Object getRepeatedField(Descriptors.FieldDescriptor field2, int index) {
            this.verifyContainingType(field2);
            return this.fields.getRepeatedField(field2, index);
        }

        @Override
        public Builder setRepeatedField(Descriptors.FieldDescriptor field2, int index, Object value) {
            this.verifyContainingType(field2);
            this.ensureIsMutable();
            this.fields.setRepeatedField(field2, index, value);
            return this;
        }

        @Override
        public Builder addRepeatedField(Descriptors.FieldDescriptor field2, Object value) {
            this.verifyContainingType(field2);
            this.ensureIsMutable();
            this.fields.addRepeatedField(field2, value);
            return this;
        }

        @Override
        public UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public Builder setUnknownFields(UnknownFieldSet unknownFields) {
            if (this.getDescriptorForType().getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO3 && CodedInputStream.getProto3DiscardUnknownFieldsDefault()) {
                return this;
            }
            this.unknownFields = unknownFields;
            return this;
        }

        @Override
        public Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
            if (this.getDescriptorForType().getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO3 && CodedInputStream.getProto3DiscardUnknownFieldsDefault()) {
                return this;
            }
            this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFields).build();
            return this;
        }

        private void verifyContainingType(Descriptors.FieldDescriptor field2) {
            if (field2.getContainingType() != this.type) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        private void verifyOneofContainingType(Descriptors.OneofDescriptor oneof) {
            if (oneof.getContainingType() != this.type) {
                throw new IllegalArgumentException("OneofDescriptor does not match message type.");
            }
        }

        private void ensureSingularEnumValueDescriptor(Descriptors.FieldDescriptor field2, Object value) {
            Internal.checkNotNull(value);
            if (!(value instanceof Descriptors.EnumValueDescriptor)) {
                throw new IllegalArgumentException("DynamicMessage should use EnumValueDescriptor to set Enum Value.");
            }
        }

        private void ensureEnumValueDescriptor(Descriptors.FieldDescriptor field2, Object value) {
            if (field2.isRepeated()) {
                for (Object item : (List)value) {
                    this.ensureSingularEnumValueDescriptor(field2, item);
                }
            } else {
                this.ensureSingularEnumValueDescriptor(field2, value);
            }
        }

        private void ensureIsMutable() {
            if (this.fields.isImmutable()) {
                this.fields = this.fields.clone();
            }
        }

        @Override
        public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor field2) {
            throw new UnsupportedOperationException("getFieldBuilder() called on a dynamic message type.");
        }

        @Override
        public Message.Builder getRepeatedFieldBuilder(Descriptors.FieldDescriptor field2, int index) {
            throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a dynamic message type.");
        }
    }
}

