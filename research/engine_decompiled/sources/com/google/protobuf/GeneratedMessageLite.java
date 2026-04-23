/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.BooleanArrayList;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.DoubleArrayList;
import com.google.protobuf.ExtensionLite;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldSet;
import com.google.protobuf.FloatArrayList;
import com.google.protobuf.IntArrayList;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LongArrayList;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.MessageLiteToString;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtobufArrayList;
import com.google.protobuf.UnknownFieldSetLite;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class GeneratedMessageLite<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>>
extends AbstractMessageLite<MessageType, BuilderType> {
    static final boolean ENABLE_EXPERIMENTAL_RUNTIME_AT_BUILD_TIME = false;
    protected UnknownFieldSetLite unknownFields = UnknownFieldSetLite.getDefaultInstance();
    protected int memoizedSerializedSize = -1;

    public final Parser<MessageType> getParserForType() {
        return (Parser)this.dynamicMethod(MethodToInvoke.GET_PARSER);
    }

    public final MessageType getDefaultInstanceForType() {
        return (MessageType)((GeneratedMessageLite)this.dynamicMethod(MethodToInvoke.GET_DEFAULT_INSTANCE));
    }

    public final BuilderType newBuilderForType() {
        return (BuilderType)((Builder)this.dynamicMethod(MethodToInvoke.NEW_BUILDER));
    }

    public String toString() {
        return MessageLiteToString.toString(this, super.toString());
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        HashCodeVisitor visitor = new HashCodeVisitor();
        this.visit(visitor, this);
        this.memoizedHashCode = visitor.hashCode;
        return this.memoizedHashCode;
    }

    int hashCode(HashCodeVisitor visitor) {
        if (this.memoizedHashCode == 0) {
            int inProgressHashCode = visitor.hashCode;
            visitor.hashCode = 0;
            this.visit(visitor, this);
            this.memoizedHashCode = visitor.hashCode;
            visitor.hashCode = inProgressHashCode;
        }
        return this.memoizedHashCode;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!this.getDefaultInstanceForType().getClass().isInstance(other)) {
            return false;
        }
        try {
            this.visit(EqualsVisitor.INSTANCE, (GeneratedMessageLite)other);
        }
        catch (EqualsVisitor.NotEqualsException e) {
            return false;
        }
        return true;
    }

    boolean equals(EqualsVisitor visitor, MessageLite other) {
        if (this == other) {
            return true;
        }
        if (!this.getDefaultInstanceForType().getClass().isInstance(other)) {
            return false;
        }
        this.visit(visitor, (GeneratedMessageLite)other);
        return true;
    }

    private final void ensureUnknownFieldsInitialized() {
        if (this.unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
            this.unknownFields = UnknownFieldSetLite.newInstance();
        }
    }

    protected boolean parseUnknownField(int tag, CodedInputStream input2) throws IOException {
        if (WireFormat.getTagWireType(tag) == 4) {
            return false;
        }
        this.ensureUnknownFieldsInitialized();
        return this.unknownFields.mergeFieldFrom(tag, input2);
    }

    protected void mergeVarintField(int tag, int value) {
        this.ensureUnknownFieldsInitialized();
        this.unknownFields.mergeVarintField(tag, value);
    }

    protected void mergeLengthDelimitedField(int fieldNumber, ByteString value) {
        this.ensureUnknownFieldsInitialized();
        this.unknownFields.mergeLengthDelimitedField(fieldNumber, value);
    }

    protected void makeImmutable() {
        this.dynamicMethod(MethodToInvoke.MAKE_IMMUTABLE);
        this.unknownFields.makeImmutable();
    }

    protected final <MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> BuilderType createBuilder() {
        return (BuilderType)((Builder)this.dynamicMethod(MethodToInvoke.NEW_BUILDER));
    }

    protected final <MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> BuilderType createBuilder(MessageType prototype) {
        return ((Builder)this.createBuilder()).mergeFrom(prototype);
    }

    @Override
    public final boolean isInitialized() {
        return GeneratedMessageLite.isInitialized(this, Boolean.TRUE);
    }

    public final BuilderType toBuilder() {
        Builder builder = (Builder)this.dynamicMethod(MethodToInvoke.NEW_BUILDER);
        builder.mergeFrom(this);
        return (BuilderType)builder;
    }

    protected abstract Object dynamicMethod(MethodToInvoke var1, Object var2, Object var3);

    protected Object dynamicMethod(MethodToInvoke method, Object arg0) {
        return this.dynamicMethod(method, arg0, null);
    }

    protected Object dynamicMethod(MethodToInvoke method) {
        return this.dynamicMethod(method, null, null);
    }

    void visit(Visitor visitor, MessageType other) {
        this.dynamicMethod(MethodToInvoke.VISIT, visitor, other);
        this.unknownFields = visitor.visitUnknownFields(this.unknownFields, ((GeneratedMessageLite)other).unknownFields);
    }

    @Override
    int getMemoizedSerializedSize() {
        return this.memoizedSerializedSize;
    }

    @Override
    void setMemoizedSerializedSize(int size2) {
        this.memoizedSerializedSize = size2;
    }

    protected final void mergeUnknownFields(UnknownFieldSetLite unknownFields) {
        this.unknownFields = UnknownFieldSetLite.mutableCopyOf(this.unknownFields, unknownFields);
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newSingularGeneratedExtension(ContainingType containingTypeDefaultInstance, Type defaultValue, MessageLite messageDefaultInstance, Internal.EnumLiteMap<?> enumTypeMap, int number, WireFormat.FieldType type, Class singularType) {
        return new GeneratedExtension<ContainingType, Type>(containingTypeDefaultInstance, defaultValue, messageDefaultInstance, new ExtensionDescriptor(enumTypeMap, number, type, false, false), singularType);
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newRepeatedGeneratedExtension(ContainingType containingTypeDefaultInstance, MessageLite messageDefaultInstance, Internal.EnumLiteMap<?> enumTypeMap, int number, WireFormat.FieldType type, boolean isPacked, Class singularType) {
        List emptyList = Collections.emptyList();
        return new GeneratedExtension(containingTypeDefaultInstance, emptyList, messageDefaultInstance, new ExtensionDescriptor(enumTypeMap, number, type, true, isPacked), singularType);
    }

    static Method getMethodOrDie(Class clazz, String name, Class ... params2) {
        try {
            return clazz.getMethod(name, params2);
        }
        catch (NoSuchMethodException e) {
            throw new RuntimeException("Generated message class \"" + clazz.getName() + "\" missing method \"" + name + "\".", e);
        }
    }

    static Object invokeOrDie(Method method, Object object, Object ... params2) {
        try {
            return method.invoke(object, params2);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        }
        catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException)cause;
            }
            if (cause instanceof Error) {
                throw (Error)cause;
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    private static <MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>, T> GeneratedExtension<MessageType, T> checkIsLite(ExtensionLite<MessageType, T> extension2) {
        if (!extension2.isLite()) {
            throw new IllegalArgumentException("Expected a lite extension.");
        }
        return (GeneratedExtension)extension2;
    }

    protected static final <T extends GeneratedMessageLite<T, ?>> boolean isInitialized(T message, boolean shouldMemoize) {
        boolean isInitialized;
        byte memoizedIsInitialized = (Byte)message.dynamicMethod(MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED);
        if (memoizedIsInitialized == 1) {
            return true;
        }
        if (memoizedIsInitialized == 0) {
            return false;
        }
        boolean bl = isInitialized = message.dynamicMethod(MethodToInvoke.IS_INITIALIZED, Boolean.FALSE) != null;
        if (shouldMemoize) {
            message.dynamicMethod(MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED, isInitialized ? message : null);
        }
        return isInitialized;
    }

    protected static Internal.IntList emptyIntList() {
        return IntArrayList.emptyList();
    }

    protected static Internal.IntList mutableCopy(Internal.IntList list2) {
        int size2 = list2.size();
        return list2.mutableCopyWithCapacity(size2 == 0 ? 10 : size2 * 2);
    }

    protected static Internal.LongList emptyLongList() {
        return LongArrayList.emptyList();
    }

    protected static Internal.LongList mutableCopy(Internal.LongList list2) {
        int size2 = list2.size();
        return list2.mutableCopyWithCapacity(size2 == 0 ? 10 : size2 * 2);
    }

    protected static Internal.FloatList emptyFloatList() {
        return FloatArrayList.emptyList();
    }

    protected static Internal.FloatList mutableCopy(Internal.FloatList list2) {
        int size2 = list2.size();
        return list2.mutableCopyWithCapacity(size2 == 0 ? 10 : size2 * 2);
    }

    protected static Internal.DoubleList emptyDoubleList() {
        return DoubleArrayList.emptyList();
    }

    protected static Internal.DoubleList mutableCopy(Internal.DoubleList list2) {
        int size2 = list2.size();
        return list2.mutableCopyWithCapacity(size2 == 0 ? 10 : size2 * 2);
    }

    protected static Internal.BooleanList emptyBooleanList() {
        return BooleanArrayList.emptyList();
    }

    protected static Internal.BooleanList mutableCopy(Internal.BooleanList list2) {
        int size2 = list2.size();
        return list2.mutableCopyWithCapacity(size2 == 0 ? 10 : size2 * 2);
    }

    protected static <E> Internal.ProtobufList<E> emptyProtobufList() {
        return ProtobufArrayList.emptyList();
    }

    protected static <E> Internal.ProtobufList<E> mutableCopy(Internal.ProtobufList<E> list2) {
        int size2 = list2.size();
        return list2.mutableCopyWithCapacity(size2 == 0 ? 10 : size2 * 2);
    }

    static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T instance, CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        GeneratedMessageLite result2 = (GeneratedMessageLite)instance.dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
        try {
            result2.dynamicMethod(MethodToInvoke.MERGE_FROM_STREAM, input2, extensionRegistry);
            result2.makeImmutable();
        }
        catch (RuntimeException e) {
            if (e.getCause() instanceof InvalidProtocolBufferException) {
                throw (InvalidProtocolBufferException)e.getCause();
            }
            throw e;
        }
        return (T)result2;
    }

    static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T instance, byte[] input2) throws InvalidProtocolBufferException {
        return GeneratedMessageLite.parsePartialFrom(instance, input2, ExtensionRegistryLite.getEmptyRegistry());
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T defaultInstance, CodedInputStream input2) throws InvalidProtocolBufferException {
        return GeneratedMessageLite.parsePartialFrom(defaultInstance, input2, ExtensionRegistryLite.getEmptyRegistry());
    }

    private static <T extends GeneratedMessageLite<T, ?>> T checkMessageInitialized(T message) throws InvalidProtocolBufferException {
        if (message != null && !message.isInitialized()) {
            throw message.newUninitializedMessageException().asInvalidProtocolBufferException().setUnfinishedMessage(message);
        }
        return message;
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return GeneratedMessageLite.checkMessageInitialized(GeneratedMessageLite.parseFrom(defaultInstance, CodedInputStream.newInstance(data), extensionRegistry));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, ByteBuffer data) throws InvalidProtocolBufferException {
        return GeneratedMessageLite.parseFrom(defaultInstance, data, ExtensionRegistryLite.getEmptyRegistry());
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, ByteString data) throws InvalidProtocolBufferException {
        return GeneratedMessageLite.checkMessageInitialized(GeneratedMessageLite.parseFrom(defaultInstance, data, ExtensionRegistryLite.getEmptyRegistry()));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return GeneratedMessageLite.checkMessageInitialized(GeneratedMessageLite.parsePartialFrom(defaultInstance, data, extensionRegistry));
    }

    private static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T defaultInstance, ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        CodedInputStream input2 = data.newCodedInput();
        T message = GeneratedMessageLite.parsePartialFrom(defaultInstance, input2, extensionRegistry);
        try {
            input2.checkLastTagWas(0);
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(message);
        }
        return message;
    }

    private static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T defaultInstance, byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        CodedInputStream input2 = CodedInputStream.newInstance(data);
        T message = GeneratedMessageLite.parsePartialFrom(defaultInstance, input2, extensionRegistry);
        try {
            input2.checkLastTagWas(0);
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(message);
        }
        return message;
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, byte[] data) throws InvalidProtocolBufferException {
        return GeneratedMessageLite.checkMessageInitialized(GeneratedMessageLite.parsePartialFrom(defaultInstance, data));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return GeneratedMessageLite.checkMessageInitialized(GeneratedMessageLite.parsePartialFrom(defaultInstance, data, extensionRegistry));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, InputStream input2) throws InvalidProtocolBufferException {
        return GeneratedMessageLite.checkMessageInitialized(GeneratedMessageLite.parsePartialFrom(defaultInstance, CodedInputStream.newInstance(input2), ExtensionRegistryLite.getEmptyRegistry()));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, InputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return GeneratedMessageLite.checkMessageInitialized(GeneratedMessageLite.parsePartialFrom(defaultInstance, CodedInputStream.newInstance(input2), extensionRegistry));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, CodedInputStream input2) throws InvalidProtocolBufferException {
        return GeneratedMessageLite.parseFrom(defaultInstance, input2, ExtensionRegistryLite.getEmptyRegistry());
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return GeneratedMessageLite.checkMessageInitialized(GeneratedMessageLite.parsePartialFrom(defaultInstance, input2, extensionRegistry));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseDelimitedFrom(T defaultInstance, InputStream input2) throws InvalidProtocolBufferException {
        return GeneratedMessageLite.checkMessageInitialized(GeneratedMessageLite.parsePartialDelimitedFrom(defaultInstance, input2, ExtensionRegistryLite.getEmptyRegistry()));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parseDelimitedFrom(T defaultInstance, InputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return GeneratedMessageLite.checkMessageInitialized(GeneratedMessageLite.parsePartialDelimitedFrom(defaultInstance, input2, extensionRegistry));
    }

    private static <T extends GeneratedMessageLite<T, ?>> T parsePartialDelimitedFrom(T defaultInstance, InputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        int size2;
        try {
            int firstByte = input2.read();
            if (firstByte == -1) {
                return null;
            }
            size2 = CodedInputStream.readRawVarint32(firstByte, input2);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e.getMessage());
        }
        AbstractMessageLite.Builder.LimitedInputStream limitedInput = new AbstractMessageLite.Builder.LimitedInputStream(input2, size2);
        CodedInputStream codedInput = CodedInputStream.newInstance(limitedInput);
        T message = GeneratedMessageLite.parsePartialFrom(defaultInstance, codedInput, extensionRegistry);
        try {
            codedInput.checkLastTagWas(0);
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(message);
        }
        return message;
    }

    protected static class MergeFromVisitor
    implements Visitor {
        public static final MergeFromVisitor INSTANCE = new MergeFromVisitor();

        private MergeFromVisitor() {
        }

        @Override
        public boolean visitBoolean(boolean minePresent, boolean mine, boolean otherPresent, boolean other) {
            return otherPresent ? other : mine;
        }

        @Override
        public int visitInt(boolean minePresent, int mine, boolean otherPresent, int other) {
            return otherPresent ? other : mine;
        }

        @Override
        public double visitDouble(boolean minePresent, double mine, boolean otherPresent, double other) {
            return otherPresent ? other : mine;
        }

        @Override
        public float visitFloat(boolean minePresent, float mine, boolean otherPresent, float other) {
            return otherPresent ? other : mine;
        }

        @Override
        public long visitLong(boolean minePresent, long mine, boolean otherPresent, long other) {
            return otherPresent ? other : mine;
        }

        @Override
        public String visitString(boolean minePresent, String mine, boolean otherPresent, String other) {
            return otherPresent ? other : mine;
        }

        @Override
        public ByteString visitByteString(boolean minePresent, ByteString mine, boolean otherPresent, ByteString other) {
            return otherPresent ? other : mine;
        }

        @Override
        public Object visitOneofBoolean(boolean minePresent, Object mine, Object other) {
            return other;
        }

        @Override
        public Object visitOneofInt(boolean minePresent, Object mine, Object other) {
            return other;
        }

        @Override
        public Object visitOneofDouble(boolean minePresent, Object mine, Object other) {
            return other;
        }

        @Override
        public Object visitOneofFloat(boolean minePresent, Object mine, Object other) {
            return other;
        }

        @Override
        public Object visitOneofLong(boolean minePresent, Object mine, Object other) {
            return other;
        }

        @Override
        public Object visitOneofString(boolean minePresent, Object mine, Object other) {
            return other;
        }

        @Override
        public Object visitOneofByteString(boolean minePresent, Object mine, Object other) {
            return other;
        }

        @Override
        public Object visitOneofMessage(boolean minePresent, Object mine, Object other) {
            if (minePresent) {
                return this.visitMessage((MessageLite)mine, (MessageLite)other);
            }
            return other;
        }

        @Override
        public void visitOneofNotSet(boolean minePresent) {
        }

        @Override
        public <T extends MessageLite> T visitMessage(T mine, T other) {
            if (mine != null && other != null) {
                return (T)mine.toBuilder().mergeFrom(other).build();
            }
            return mine != null ? mine : other;
        }

        @Override
        public <T> Internal.ProtobufList<T> visitList(Internal.ProtobufList<T> mine, Internal.ProtobufList<T> other) {
            int size2 = mine.size();
            int otherSize = other.size();
            if (size2 > 0 && otherSize > 0) {
                if (!mine.isModifiable()) {
                    mine = mine.mutableCopyWithCapacity(size2 + otherSize);
                }
                mine.addAll(other);
            }
            return size2 > 0 ? mine : other;
        }

        @Override
        public Internal.BooleanList visitBooleanList(Internal.BooleanList mine, Internal.BooleanList other) {
            int size2 = mine.size();
            int otherSize = other.size();
            if (size2 > 0 && otherSize > 0) {
                if (!mine.isModifiable()) {
                    mine = mine.mutableCopyWithCapacity(size2 + otherSize);
                }
                mine.addAll(other);
            }
            return size2 > 0 ? mine : other;
        }

        @Override
        public Internal.IntList visitIntList(Internal.IntList mine, Internal.IntList other) {
            int size2 = mine.size();
            int otherSize = other.size();
            if (size2 > 0 && otherSize > 0) {
                if (!mine.isModifiable()) {
                    mine = mine.mutableCopyWithCapacity(size2 + otherSize);
                }
                mine.addAll(other);
            }
            return size2 > 0 ? mine : other;
        }

        @Override
        public Internal.DoubleList visitDoubleList(Internal.DoubleList mine, Internal.DoubleList other) {
            int size2 = mine.size();
            int otherSize = other.size();
            if (size2 > 0 && otherSize > 0) {
                if (!mine.isModifiable()) {
                    mine = mine.mutableCopyWithCapacity(size2 + otherSize);
                }
                mine.addAll(other);
            }
            return size2 > 0 ? mine : other;
        }

        @Override
        public Internal.FloatList visitFloatList(Internal.FloatList mine, Internal.FloatList other) {
            int size2 = mine.size();
            int otherSize = other.size();
            if (size2 > 0 && otherSize > 0) {
                if (!mine.isModifiable()) {
                    mine = mine.mutableCopyWithCapacity(size2 + otherSize);
                }
                mine.addAll(other);
            }
            return size2 > 0 ? mine : other;
        }

        @Override
        public Internal.LongList visitLongList(Internal.LongList mine, Internal.LongList other) {
            int size2 = mine.size();
            int otherSize = other.size();
            if (size2 > 0 && otherSize > 0) {
                if (!mine.isModifiable()) {
                    mine = mine.mutableCopyWithCapacity(size2 + otherSize);
                }
                mine.addAll(other);
            }
            return size2 > 0 ? mine : other;
        }

        @Override
        public FieldSet<ExtensionDescriptor> visitExtensions(FieldSet<ExtensionDescriptor> mine, FieldSet<ExtensionDescriptor> other) {
            if (((FieldSet)mine).isImmutable()) {
                mine = ((FieldSet)mine).clone();
            }
            ((FieldSet)mine).mergeFrom(other);
            return mine;
        }

        @Override
        public UnknownFieldSetLite visitUnknownFields(UnknownFieldSetLite mine, UnknownFieldSetLite other) {
            return other == UnknownFieldSetLite.getDefaultInstance() ? mine : UnknownFieldSetLite.mutableCopyOf(mine, other);
        }

        @Override
        public <K, V> MapFieldLite<K, V> visitMap(MapFieldLite<K, V> mine, MapFieldLite<K, V> other) {
            if (!other.isEmpty()) {
                if (!mine.isMutable()) {
                    mine = mine.mutableCopy();
                }
                mine.mergeFrom(other);
            }
            return mine;
        }
    }

    static class HashCodeVisitor
    implements Visitor {
        int hashCode = 0;

        HashCodeVisitor() {
        }

        @Override
        public boolean visitBoolean(boolean minePresent, boolean mine, boolean otherPresent, boolean other) {
            this.hashCode = 53 * this.hashCode + Internal.hashBoolean(mine);
            return mine;
        }

        @Override
        public int visitInt(boolean minePresent, int mine, boolean otherPresent, int other) {
            this.hashCode = 53 * this.hashCode + mine;
            return mine;
        }

        @Override
        public double visitDouble(boolean minePresent, double mine, boolean otherPresent, double other) {
            this.hashCode = 53 * this.hashCode + Internal.hashLong(Double.doubleToLongBits(mine));
            return mine;
        }

        @Override
        public float visitFloat(boolean minePresent, float mine, boolean otherPresent, float other) {
            this.hashCode = 53 * this.hashCode + Float.floatToIntBits(mine);
            return mine;
        }

        @Override
        public long visitLong(boolean minePresent, long mine, boolean otherPresent, long other) {
            this.hashCode = 53 * this.hashCode + Internal.hashLong(mine);
            return mine;
        }

        @Override
        public String visitString(boolean minePresent, String mine, boolean otherPresent, String other) {
            this.hashCode = 53 * this.hashCode + mine.hashCode();
            return mine;
        }

        @Override
        public ByteString visitByteString(boolean minePresent, ByteString mine, boolean otherPresent, ByteString other) {
            this.hashCode = 53 * this.hashCode + mine.hashCode();
            return mine;
        }

        @Override
        public Object visitOneofBoolean(boolean minePresent, Object mine, Object other) {
            this.hashCode = 53 * this.hashCode + Internal.hashBoolean((Boolean)mine);
            return mine;
        }

        @Override
        public Object visitOneofInt(boolean minePresent, Object mine, Object other) {
            this.hashCode = 53 * this.hashCode + (Integer)mine;
            return mine;
        }

        @Override
        public Object visitOneofDouble(boolean minePresent, Object mine, Object other) {
            this.hashCode = 53 * this.hashCode + Internal.hashLong(Double.doubleToLongBits((Double)mine));
            return mine;
        }

        @Override
        public Object visitOneofFloat(boolean minePresent, Object mine, Object other) {
            this.hashCode = 53 * this.hashCode + Float.floatToIntBits(((Float)mine).floatValue());
            return mine;
        }

        @Override
        public Object visitOneofLong(boolean minePresent, Object mine, Object other) {
            this.hashCode = 53 * this.hashCode + Internal.hashLong((Long)mine);
            return mine;
        }

        @Override
        public Object visitOneofString(boolean minePresent, Object mine, Object other) {
            this.hashCode = 53 * this.hashCode + mine.hashCode();
            return mine;
        }

        @Override
        public Object visitOneofByteString(boolean minePresent, Object mine, Object other) {
            this.hashCode = 53 * this.hashCode + mine.hashCode();
            return mine;
        }

        @Override
        public Object visitOneofMessage(boolean minePresent, Object mine, Object other) {
            return this.visitMessage((MessageLite)mine, (MessageLite)other);
        }

        @Override
        public void visitOneofNotSet(boolean minePresent) {
            if (minePresent) {
                throw new IllegalStateException();
            }
        }

        @Override
        public <T extends MessageLite> T visitMessage(T mine, T other) {
            int protoHash = mine != null ? (mine instanceof GeneratedMessageLite ? ((GeneratedMessageLite)mine).hashCode(this) : mine.hashCode()) : 37;
            this.hashCode = 53 * this.hashCode + protoHash;
            return mine;
        }

        @Override
        public <T> Internal.ProtobufList<T> visitList(Internal.ProtobufList<T> mine, Internal.ProtobufList<T> other) {
            this.hashCode = 53 * this.hashCode + mine.hashCode();
            return mine;
        }

        @Override
        public Internal.BooleanList visitBooleanList(Internal.BooleanList mine, Internal.BooleanList other) {
            this.hashCode = 53 * this.hashCode + mine.hashCode();
            return mine;
        }

        @Override
        public Internal.IntList visitIntList(Internal.IntList mine, Internal.IntList other) {
            this.hashCode = 53 * this.hashCode + mine.hashCode();
            return mine;
        }

        @Override
        public Internal.DoubleList visitDoubleList(Internal.DoubleList mine, Internal.DoubleList other) {
            this.hashCode = 53 * this.hashCode + mine.hashCode();
            return mine;
        }

        @Override
        public Internal.FloatList visitFloatList(Internal.FloatList mine, Internal.FloatList other) {
            this.hashCode = 53 * this.hashCode + mine.hashCode();
            return mine;
        }

        @Override
        public Internal.LongList visitLongList(Internal.LongList mine, Internal.LongList other) {
            this.hashCode = 53 * this.hashCode + mine.hashCode();
            return mine;
        }

        @Override
        public FieldSet<ExtensionDescriptor> visitExtensions(FieldSet<ExtensionDescriptor> mine, FieldSet<ExtensionDescriptor> other) {
            this.hashCode = 53 * this.hashCode + mine.hashCode();
            return mine;
        }

        @Override
        public UnknownFieldSetLite visitUnknownFields(UnknownFieldSetLite mine, UnknownFieldSetLite other) {
            this.hashCode = 53 * this.hashCode + mine.hashCode();
            return mine;
        }

        @Override
        public <K, V> MapFieldLite<K, V> visitMap(MapFieldLite<K, V> mine, MapFieldLite<K, V> other) {
            this.hashCode = 53 * this.hashCode + mine.hashCode();
            return mine;
        }
    }

    static class EqualsVisitor
    implements Visitor {
        static final EqualsVisitor INSTANCE = new EqualsVisitor();
        static final NotEqualsException NOT_EQUALS = new NotEqualsException();

        private EqualsVisitor() {
        }

        @Override
        public boolean visitBoolean(boolean minePresent, boolean mine, boolean otherPresent, boolean other) {
            if (minePresent != otherPresent || mine != other) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public int visitInt(boolean minePresent, int mine, boolean otherPresent, int other) {
            if (minePresent != otherPresent || mine != other) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public double visitDouble(boolean minePresent, double mine, boolean otherPresent, double other) {
            if (minePresent != otherPresent || mine != other) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public float visitFloat(boolean minePresent, float mine, boolean otherPresent, float other) {
            if (minePresent != otherPresent || mine != other) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public long visitLong(boolean minePresent, long mine, boolean otherPresent, long other) {
            if (minePresent != otherPresent || mine != other) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public String visitString(boolean minePresent, String mine, boolean otherPresent, String other) {
            if (minePresent != otherPresent || !mine.equals(other)) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public ByteString visitByteString(boolean minePresent, ByteString mine, boolean otherPresent, ByteString other) {
            if (minePresent != otherPresent || !mine.equals(other)) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public Object visitOneofBoolean(boolean minePresent, Object mine, Object other) {
            if (minePresent && mine.equals(other)) {
                return mine;
            }
            throw NOT_EQUALS;
        }

        @Override
        public Object visitOneofInt(boolean minePresent, Object mine, Object other) {
            if (minePresent && mine.equals(other)) {
                return mine;
            }
            throw NOT_EQUALS;
        }

        @Override
        public Object visitOneofDouble(boolean minePresent, Object mine, Object other) {
            if (minePresent && mine.equals(other)) {
                return mine;
            }
            throw NOT_EQUALS;
        }

        @Override
        public Object visitOneofFloat(boolean minePresent, Object mine, Object other) {
            if (minePresent && mine.equals(other)) {
                return mine;
            }
            throw NOT_EQUALS;
        }

        @Override
        public Object visitOneofLong(boolean minePresent, Object mine, Object other) {
            if (minePresent && mine.equals(other)) {
                return mine;
            }
            throw NOT_EQUALS;
        }

        @Override
        public Object visitOneofString(boolean minePresent, Object mine, Object other) {
            if (minePresent && mine.equals(other)) {
                return mine;
            }
            throw NOT_EQUALS;
        }

        @Override
        public Object visitOneofByteString(boolean minePresent, Object mine, Object other) {
            if (minePresent && mine.equals(other)) {
                return mine;
            }
            throw NOT_EQUALS;
        }

        @Override
        public Object visitOneofMessage(boolean minePresent, Object mine, Object other) {
            if (minePresent && ((GeneratedMessageLite)mine).equals(this, (MessageLite)other)) {
                return mine;
            }
            throw NOT_EQUALS;
        }

        @Override
        public void visitOneofNotSet(boolean minePresent) {
            if (minePresent) {
                throw NOT_EQUALS;
            }
        }

        @Override
        public <T extends MessageLite> T visitMessage(T mine, T other) {
            if (mine == null && other == null) {
                return null;
            }
            if (mine == null || other == null) {
                throw NOT_EQUALS;
            }
            ((GeneratedMessageLite)mine).equals(this, other);
            return mine;
        }

        @Override
        public <T> Internal.ProtobufList<T> visitList(Internal.ProtobufList<T> mine, Internal.ProtobufList<T> other) {
            if (!mine.equals(other)) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public Internal.BooleanList visitBooleanList(Internal.BooleanList mine, Internal.BooleanList other) {
            if (!mine.equals(other)) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public Internal.IntList visitIntList(Internal.IntList mine, Internal.IntList other) {
            if (!mine.equals(other)) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public Internal.DoubleList visitDoubleList(Internal.DoubleList mine, Internal.DoubleList other) {
            if (!mine.equals(other)) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public Internal.FloatList visitFloatList(Internal.FloatList mine, Internal.FloatList other) {
            if (!mine.equals(other)) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public Internal.LongList visitLongList(Internal.LongList mine, Internal.LongList other) {
            if (!mine.equals(other)) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public FieldSet<ExtensionDescriptor> visitExtensions(FieldSet<ExtensionDescriptor> mine, FieldSet<ExtensionDescriptor> other) {
            if (!mine.equals(other)) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public UnknownFieldSetLite visitUnknownFields(UnknownFieldSetLite mine, UnknownFieldSetLite other) {
            if (!mine.equals(other)) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        @Override
        public <K, V> MapFieldLite<K, V> visitMap(MapFieldLite<K, V> mine, MapFieldLite<K, V> other) {
            if (!mine.equals(other)) {
                throw NOT_EQUALS;
            }
            return mine;
        }

        static final class NotEqualsException
        extends RuntimeException {
            NotEqualsException() {
            }
        }
    }

    protected static interface Visitor {
        public boolean visitBoolean(boolean var1, boolean var2, boolean var3, boolean var4);

        public int visitInt(boolean var1, int var2, boolean var3, int var4);

        public double visitDouble(boolean var1, double var2, boolean var4, double var5);

        public float visitFloat(boolean var1, float var2, boolean var3, float var4);

        public long visitLong(boolean var1, long var2, boolean var4, long var5);

        public String visitString(boolean var1, String var2, boolean var3, String var4);

        public ByteString visitByteString(boolean var1, ByteString var2, boolean var3, ByteString var4);

        public Object visitOneofBoolean(boolean var1, Object var2, Object var3);

        public Object visitOneofInt(boolean var1, Object var2, Object var3);

        public Object visitOneofDouble(boolean var1, Object var2, Object var3);

        public Object visitOneofFloat(boolean var1, Object var2, Object var3);

        public Object visitOneofLong(boolean var1, Object var2, Object var3);

        public Object visitOneofString(boolean var1, Object var2, Object var3);

        public Object visitOneofByteString(boolean var1, Object var2, Object var3);

        public Object visitOneofMessage(boolean var1, Object var2, Object var3);

        public void visitOneofNotSet(boolean var1);

        public <T extends MessageLite> T visitMessage(T var1, T var2);

        public <T> Internal.ProtobufList<T> visitList(Internal.ProtobufList<T> var1, Internal.ProtobufList<T> var2);

        public Internal.BooleanList visitBooleanList(Internal.BooleanList var1, Internal.BooleanList var2);

        public Internal.IntList visitIntList(Internal.IntList var1, Internal.IntList var2);

        public Internal.DoubleList visitDoubleList(Internal.DoubleList var1, Internal.DoubleList var2);

        public Internal.FloatList visitFloatList(Internal.FloatList var1, Internal.FloatList var2);

        public Internal.LongList visitLongList(Internal.LongList var1, Internal.LongList var2);

        public FieldSet<ExtensionDescriptor> visitExtensions(FieldSet<ExtensionDescriptor> var1, FieldSet<ExtensionDescriptor> var2);

        public UnknownFieldSetLite visitUnknownFields(UnknownFieldSetLite var1, UnknownFieldSetLite var2);

        public <K, V> MapFieldLite<K, V> visitMap(MapFieldLite<K, V> var1, MapFieldLite<K, V> var2);
    }

    protected static class DefaultInstanceBasedParser<T extends GeneratedMessageLite<T, ?>>
    extends AbstractParser<T> {
        private T defaultInstance;

        public DefaultInstanceBasedParser(T defaultInstance) {
            this.defaultInstance = defaultInstance;
        }

        @Override
        public T parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return GeneratedMessageLite.parsePartialFrom(this.defaultInstance, input2, extensionRegistry);
        }

        @Override
        public T parsePartialFrom(byte[] input2) throws InvalidProtocolBufferException {
            return GeneratedMessageLite.parsePartialFrom(this.defaultInstance, input2);
        }
    }

    protected static final class SerializedForm
    implements Serializable {
        private static final long serialVersionUID = 0L;
        private final String messageClassName;
        private final byte[] asBytes;

        public static SerializedForm of(MessageLite message) {
            return new SerializedForm(message);
        }

        SerializedForm(MessageLite regularForm) {
            this.messageClassName = regularForm.getClass().getName();
            this.asBytes = regularForm.toByteArray();
        }

        protected Object readResolve() throws ObjectStreamException {
            try {
                Class<?> messageClass = Class.forName(this.messageClassName);
                Field defaultInstanceField = messageClass.getDeclaredField("DEFAULT_INSTANCE");
                defaultInstanceField.setAccessible(true);
                MessageLite defaultInstance = (MessageLite)defaultInstanceField.get(null);
                return defaultInstance.newBuilderForType().mergeFrom(this.asBytes).buildPartial();
            }
            catch (ClassNotFoundException e) {
                throw new RuntimeException("Unable to find proto buffer class: " + this.messageClassName, e);
            }
            catch (NoSuchFieldException e) {
                return this.readResolveFallback();
            }
            catch (SecurityException e) {
                throw new RuntimeException("Unable to call DEFAULT_INSTANCE in " + this.messageClassName, e);
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to call parsePartialFrom", e);
            }
            catch (InvalidProtocolBufferException e) {
                throw new RuntimeException("Unable to understand proto buffer", e);
            }
        }

        @Deprecated
        private Object readResolveFallback() throws ObjectStreamException {
            try {
                Class<?> messageClass = Class.forName(this.messageClassName);
                Field defaultInstanceField = messageClass.getDeclaredField("defaultInstance");
                defaultInstanceField.setAccessible(true);
                MessageLite defaultInstance = (MessageLite)defaultInstanceField.get(null);
                return defaultInstance.newBuilderForType().mergeFrom(this.asBytes).buildPartial();
            }
            catch (ClassNotFoundException e) {
                throw new RuntimeException("Unable to find proto buffer class: " + this.messageClassName, e);
            }
            catch (NoSuchFieldException e) {
                throw new RuntimeException("Unable to find defaultInstance in " + this.messageClassName, e);
            }
            catch (SecurityException e) {
                throw new RuntimeException("Unable to call defaultInstance in " + this.messageClassName, e);
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to call parsePartialFrom", e);
            }
            catch (InvalidProtocolBufferException e) {
                throw new RuntimeException("Unable to understand proto buffer", e);
            }
        }
    }

    public static class GeneratedExtension<ContainingType extends MessageLite, Type>
    extends ExtensionLite<ContainingType, Type> {
        final ContainingType containingTypeDefaultInstance;
        final Type defaultValue;
        final MessageLite messageDefaultInstance;
        final ExtensionDescriptor descriptor;

        GeneratedExtension(ContainingType containingTypeDefaultInstance, Type defaultValue, MessageLite messageDefaultInstance, ExtensionDescriptor descriptor, Class singularType) {
            if (containingTypeDefaultInstance == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            }
            if (descriptor.getLiteType() == WireFormat.FieldType.MESSAGE && messageDefaultInstance == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            }
            this.containingTypeDefaultInstance = containingTypeDefaultInstance;
            this.defaultValue = defaultValue;
            this.messageDefaultInstance = messageDefaultInstance;
            this.descriptor = descriptor;
        }

        public ContainingType getContainingTypeDefaultInstance() {
            return this.containingTypeDefaultInstance;
        }

        @Override
        public int getNumber() {
            return this.descriptor.getNumber();
        }

        @Override
        public MessageLite getMessageDefaultInstance() {
            return this.messageDefaultInstance;
        }

        Object fromFieldSetType(Object value) {
            if (this.descriptor.isRepeated()) {
                if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
                    ArrayList<Object> result2 = new ArrayList<Object>();
                    for (Object element : (List)value) {
                        result2.add(this.singularFromFieldSetType(element));
                    }
                    return result2;
                }
                return value;
            }
            return this.singularFromFieldSetType(value);
        }

        Object singularFromFieldSetType(Object value) {
            if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
                return this.descriptor.enumTypeMap.findValueByNumber((Integer)value);
            }
            return value;
        }

        Object toFieldSetType(Object value) {
            if (this.descriptor.isRepeated()) {
                if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
                    ArrayList<Object> result2 = new ArrayList<Object>();
                    for (Object element : (List)value) {
                        result2.add(this.singularToFieldSetType(element));
                    }
                    return result2;
                }
                return value;
            }
            return this.singularToFieldSetType(value);
        }

        Object singularToFieldSetType(Object value) {
            if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
                return ((Internal.EnumLite)value).getNumber();
            }
            return value;
        }

        @Override
        public WireFormat.FieldType getLiteType() {
            return this.descriptor.getLiteType();
        }

        @Override
        public boolean isRepeated() {
            return this.descriptor.isRepeated;
        }

        @Override
        public Type getDefaultValue() {
            return this.defaultValue;
        }
    }

    static final class ExtensionDescriptor
    implements FieldSet.FieldDescriptorLite<ExtensionDescriptor> {
        final Internal.EnumLiteMap<?> enumTypeMap;
        final int number;
        final WireFormat.FieldType type;
        final boolean isRepeated;
        final boolean isPacked;

        ExtensionDescriptor(Internal.EnumLiteMap<?> enumTypeMap, int number, WireFormat.FieldType type, boolean isRepeated, boolean isPacked) {
            this.enumTypeMap = enumTypeMap;
            this.number = number;
            this.type = type;
            this.isRepeated = isRepeated;
            this.isPacked = isPacked;
        }

        @Override
        public int getNumber() {
            return this.number;
        }

        @Override
        public WireFormat.FieldType getLiteType() {
            return this.type;
        }

        @Override
        public WireFormat.JavaType getLiteJavaType() {
            return this.type.getJavaType();
        }

        @Override
        public boolean isRepeated() {
            return this.isRepeated;
        }

        @Override
        public boolean isPacked() {
            return this.isPacked;
        }

        @Override
        public Internal.EnumLiteMap<?> getEnumType() {
            return this.enumTypeMap;
        }

        @Override
        public MessageLite.Builder internalMergeFrom(MessageLite.Builder to2, MessageLite from2) {
            return ((Builder)to2).mergeFrom((GeneratedMessageLite)from2);
        }

        @Override
        public int compareTo(ExtensionDescriptor other) {
            return this.number - other.number;
        }
    }

    public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>>
    extends Builder<MessageType, BuilderType>
    implements ExtendableMessageOrBuilder<MessageType, BuilderType> {
        protected ExtendableBuilder(MessageType defaultInstance) {
            super(defaultInstance);
        }

        void internalSetExtensionSet(FieldSet<ExtensionDescriptor> extensions) {
            this.copyOnWrite();
            ((ExtendableMessage)this.instance).extensions = extensions;
        }

        @Override
        protected void copyOnWrite() {
            if (!this.isBuilt) {
                return;
            }
            super.copyOnWrite();
            ((ExtendableMessage)this.instance).extensions = ((ExtendableMessage)this.instance).extensions.clone();
        }

        private FieldSet<ExtensionDescriptor> ensureExtensionsAreMutable() {
            FieldSet<ExtensionDescriptor> extensions = ((ExtendableMessage)this.instance).extensions;
            if (extensions.isImmutable()) {
                ((ExtendableMessage)this.instance).extensions = extensions = extensions.clone();
            }
            return extensions;
        }

        @Override
        public final MessageType buildPartial() {
            if (this.isBuilt) {
                return (MessageType)((ExtendableMessage)this.instance);
            }
            ((ExtendableMessage)this.instance).extensions.makeImmutable();
            return (MessageType)((ExtendableMessage)super.buildPartial());
        }

        private void verifyExtensionContainingType(GeneratedExtension<MessageType, ?> extension2) {
            if (extension2.getContainingTypeDefaultInstance() != this.getDefaultInstanceForType()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }

        @Override
        public final <Type> boolean hasExtension(ExtensionLite<MessageType, Type> extension2) {
            return ((ExtendableMessage)this.instance).hasExtension(extension2);
        }

        @Override
        public final <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> extension2) {
            return ((ExtendableMessage)this.instance).getExtensionCount(extension2);
        }

        @Override
        public final <Type> Type getExtension(ExtensionLite<MessageType, Type> extension2) {
            return ((ExtendableMessage)this.instance).getExtension(extension2);
        }

        @Override
        public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> extension2, int index) {
            return ((ExtendableMessage)this.instance).getExtension(extension2, index);
        }

        public final <Type> BuilderType setExtension(ExtensionLite<MessageType, Type> extension2, Type value) {
            GeneratedExtension extensionLite = GeneratedMessageLite.checkIsLite(extension2);
            this.verifyExtensionContainingType(extensionLite);
            this.copyOnWrite();
            this.ensureExtensionsAreMutable().setField(extensionLite.descriptor, extensionLite.toFieldSetType(value));
            return (BuilderType)this;
        }

        public final <Type> BuilderType setExtension(ExtensionLite<MessageType, List<Type>> extension2, int index, Type value) {
            GeneratedExtension extensionLite = GeneratedMessageLite.checkIsLite(extension2);
            this.verifyExtensionContainingType(extensionLite);
            this.copyOnWrite();
            this.ensureExtensionsAreMutable().setRepeatedField(extensionLite.descriptor, index, extensionLite.singularToFieldSetType(value));
            return (BuilderType)this;
        }

        public final <Type> BuilderType addExtension(ExtensionLite<MessageType, List<Type>> extension2, Type value) {
            GeneratedExtension extensionLite = GeneratedMessageLite.checkIsLite(extension2);
            this.verifyExtensionContainingType(extensionLite);
            this.copyOnWrite();
            this.ensureExtensionsAreMutable().addRepeatedField(extensionLite.descriptor, extensionLite.singularToFieldSetType(value));
            return (BuilderType)this;
        }

        public final <Type> BuilderType clearExtension(ExtensionLite<MessageType, ?> extension2) {
            GeneratedExtension extensionLite = GeneratedMessageLite.checkIsLite(extension2);
            this.verifyExtensionContainingType(extensionLite);
            this.copyOnWrite();
            this.ensureExtensionsAreMutable().clearField(extensionLite.descriptor);
            return (BuilderType)this;
        }
    }

    public static abstract class ExtendableMessage<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>>
    extends GeneratedMessageLite<MessageType, BuilderType>
    implements ExtendableMessageOrBuilder<MessageType, BuilderType> {
        protected FieldSet<ExtensionDescriptor> extensions = FieldSet.emptySet();

        protected final void mergeExtensionFields(MessageType other) {
            if (this.extensions.isImmutable()) {
                this.extensions = this.extensions.clone();
            }
            this.extensions.mergeFrom(((ExtendableMessage)other).extensions);
        }

        @Override
        final void visit(Visitor visitor, MessageType other) {
            super.visit(visitor, other);
            this.extensions = visitor.visitExtensions(this.extensions, ((ExtendableMessage)other).extensions);
        }

        protected <MessageType extends MessageLite> boolean parseUnknownField(MessageType defaultInstance, CodedInputStream input2, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
            int fieldNumber = WireFormat.getTagFieldNumber(tag);
            GeneratedExtension<MessageType, ?> extension2 = extensionRegistry.findLiteExtensionByNumber(defaultInstance, fieldNumber);
            return this.parseExtension(input2, extensionRegistry, extension2, tag, fieldNumber);
        }

        private boolean parseExtension(CodedInputStream input2, ExtensionRegistryLite extensionRegistry, GeneratedExtension<?, ?> extension2, int tag, int fieldNumber) throws IOException {
            int wireType = WireFormat.getTagWireType(tag);
            boolean unknown = false;
            boolean packed = false;
            if (extension2 == null) {
                unknown = true;
            } else if (wireType == FieldSet.getWireFormatForFieldType(extension2.descriptor.getLiteType(), false)) {
                packed = false;
            } else if (extension2.descriptor.isRepeated && extension2.descriptor.type.isPackable() && wireType == FieldSet.getWireFormatForFieldType(extension2.descriptor.getLiteType(), true)) {
                packed = true;
            } else {
                unknown = true;
            }
            if (unknown) {
                return this.parseUnknownField(tag, input2);
            }
            this.ensureExtensionsAreMutable();
            if (packed) {
                int length = input2.readRawVarint32();
                int limit = input2.pushLimit(length);
                if (extension2.descriptor.getLiteType() == WireFormat.FieldType.ENUM) {
                    while (input2.getBytesUntilLimit() > 0) {
                        int rawValue = input2.readEnum();
                        Object value = extension2.descriptor.getEnumType().findValueByNumber(rawValue);
                        if (value == null) {
                            return true;
                        }
                        this.extensions.addRepeatedField(extension2.descriptor, extension2.singularToFieldSetType(value));
                    }
                } else {
                    while (input2.getBytesUntilLimit() > 0) {
                        Object value = FieldSet.readPrimitiveField(input2, extension2.descriptor.getLiteType(), false);
                        this.extensions.addRepeatedField(extension2.descriptor, value);
                    }
                }
                input2.popLimit(limit);
            } else {
                Object value;
                switch (extension2.descriptor.getLiteJavaType()) {
                    case MESSAGE: {
                        MessageLite existingValue;
                        MessageLite.Builder subBuilder = null;
                        if (!extension2.descriptor.isRepeated() && (existingValue = (MessageLite)this.extensions.getField(extension2.descriptor)) != null) {
                            subBuilder = existingValue.toBuilder();
                        }
                        if (subBuilder == null) {
                            subBuilder = extension2.getMessageDefaultInstance().newBuilderForType();
                        }
                        if (extension2.descriptor.getLiteType() == WireFormat.FieldType.GROUP) {
                            input2.readGroup(extension2.getNumber(), subBuilder, extensionRegistry);
                        } else {
                            input2.readMessage(subBuilder, extensionRegistry);
                        }
                        value = subBuilder.build();
                        break;
                    }
                    case ENUM: {
                        int rawValue = input2.readEnum();
                        value = extension2.descriptor.getEnumType().findValueByNumber(rawValue);
                        if (value != null) break;
                        this.mergeVarintField(fieldNumber, rawValue);
                        return true;
                    }
                    default: {
                        value = FieldSet.readPrimitiveField(input2, extension2.descriptor.getLiteType(), false);
                    }
                }
                if (extension2.descriptor.isRepeated()) {
                    this.extensions.addRepeatedField(extension2.descriptor, extension2.singularToFieldSetType(value));
                } else {
                    this.extensions.setField(extension2.descriptor, extension2.singularToFieldSetType(value));
                }
            }
            return true;
        }

        protected <MessageType extends MessageLite> boolean parseUnknownFieldAsMessageSet(MessageType defaultInstance, CodedInputStream input2, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
            if (tag == WireFormat.MESSAGE_SET_ITEM_TAG) {
                this.mergeMessageSetExtensionFromCodedStream(defaultInstance, input2, extensionRegistry);
                return true;
            }
            int wireType = WireFormat.getTagWireType(tag);
            if (wireType == 2) {
                return this.parseUnknownField(defaultInstance, input2, extensionRegistry, tag);
            }
            return input2.skipField(tag);
        }

        private <MessageType extends MessageLite> void mergeMessageSetExtensionFromCodedStream(MessageType defaultInstance, CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            int tag;
            int typeId = 0;
            ByteString rawBytes = null;
            GeneratedExtension<MessageType, ?> extension2 = null;
            while ((tag = input2.readTag()) != 0) {
                if (tag == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                    typeId = input2.readUInt32();
                    if (typeId == 0) continue;
                    extension2 = extensionRegistry.findLiteExtensionByNumber(defaultInstance, typeId);
                    continue;
                }
                if (tag == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                    if (typeId != 0 && extension2 != null) {
                        this.eagerlyMergeMessageSetExtension(input2, extension2, extensionRegistry, typeId);
                        rawBytes = null;
                        continue;
                    }
                    rawBytes = input2.readBytes();
                    continue;
                }
                if (input2.skipField(tag)) continue;
                break;
            }
            input2.checkLastTagWas(WireFormat.MESSAGE_SET_ITEM_END_TAG);
            if (rawBytes != null && typeId != 0) {
                if (extension2 != null) {
                    this.mergeMessageSetExtensionFromBytes(rawBytes, extensionRegistry, extension2);
                } else if (rawBytes != null) {
                    this.mergeLengthDelimitedField(typeId, rawBytes);
                }
            }
        }

        private void eagerlyMergeMessageSetExtension(CodedInputStream input2, GeneratedExtension<?, ?> extension2, ExtensionRegistryLite extensionRegistry, int typeId) throws IOException {
            int fieldNumber = typeId;
            int tag = WireFormat.makeTag(typeId, 2);
            this.parseExtension(input2, extensionRegistry, extension2, tag, fieldNumber);
        }

        private void mergeMessageSetExtensionFromBytes(ByteString rawBytes, ExtensionRegistryLite extensionRegistry, GeneratedExtension<?, ?> extension2) throws IOException {
            MessageLite.Builder subBuilder = null;
            MessageLite existingValue = (MessageLite)this.extensions.getField(extension2.descriptor);
            if (existingValue != null) {
                subBuilder = existingValue.toBuilder();
            }
            if (subBuilder == null) {
                subBuilder = extension2.getMessageDefaultInstance().newBuilderForType();
            }
            subBuilder.mergeFrom(rawBytes, extensionRegistry);
            MessageLite value = subBuilder.build();
            this.ensureExtensionsAreMutable().setField(extension2.descriptor, extension2.singularToFieldSetType(value));
        }

        private FieldSet<ExtensionDescriptor> ensureExtensionsAreMutable() {
            if (this.extensions.isImmutable()) {
                this.extensions = this.extensions.clone();
            }
            return this.extensions;
        }

        private void verifyExtensionContainingType(GeneratedExtension<MessageType, ?> extension2) {
            if (extension2.getContainingTypeDefaultInstance() != this.getDefaultInstanceForType()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }

        @Override
        public final <Type> boolean hasExtension(ExtensionLite<MessageType, Type> extension2) {
            GeneratedExtension extensionLite = GeneratedMessageLite.checkIsLite(extension2);
            this.verifyExtensionContainingType(extensionLite);
            return this.extensions.hasField(extensionLite.descriptor);
        }

        @Override
        public final <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> extension2) {
            GeneratedExtension extensionLite = GeneratedMessageLite.checkIsLite(extension2);
            this.verifyExtensionContainingType(extensionLite);
            return this.extensions.getRepeatedFieldCount(extensionLite.descriptor);
        }

        @Override
        public final <Type> Type getExtension(ExtensionLite<MessageType, Type> extension2) {
            GeneratedExtension extensionLite = GeneratedMessageLite.checkIsLite(extension2);
            this.verifyExtensionContainingType(extensionLite);
            Object value = this.extensions.getField(extensionLite.descriptor);
            if (value == null) {
                return extensionLite.defaultValue;
            }
            return (Type)extensionLite.fromFieldSetType(value);
        }

        @Override
        public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> extension2, int index) {
            GeneratedExtension extensionLite = GeneratedMessageLite.checkIsLite(extension2);
            this.verifyExtensionContainingType(extensionLite);
            return (Type)extensionLite.singularFromFieldSetType(this.extensions.getRepeatedField(extensionLite.descriptor, index));
        }

        protected boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        @Override
        protected final void makeImmutable() {
            super.makeImmutable();
            this.extensions.makeImmutable();
        }

        protected ExtensionWriter newExtensionWriter() {
            return new ExtensionWriter(false);
        }

        protected ExtensionWriter newMessageSetExtensionWriter() {
            return new ExtensionWriter(true);
        }

        protected int extensionsSerializedSize() {
            return this.extensions.getSerializedSize();
        }

        protected int extensionsSerializedSizeAsMessageSet() {
            return this.extensions.getMessageSetSerializedSize();
        }

        protected class ExtensionWriter {
            private final Iterator<Map.Entry<ExtensionDescriptor, Object>> iter;
            private Map.Entry<ExtensionDescriptor, Object> next;
            private final boolean messageSetWireFormat;

            private ExtensionWriter(boolean messageSetWireFormat) {
                this.iter = ExtendableMessage.this.extensions.iterator();
                if (this.iter.hasNext()) {
                    this.next = this.iter.next();
                }
                this.messageSetWireFormat = messageSetWireFormat;
            }

            public void writeUntil(int end, CodedOutputStream output) throws IOException {
                while (this.next != null && this.next.getKey().getNumber() < end) {
                    ExtensionDescriptor extension2 = this.next.getKey();
                    if (this.messageSetWireFormat && extension2.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !extension2.isRepeated()) {
                        output.writeMessageSetExtension(extension2.getNumber(), (MessageLite)this.next.getValue());
                    } else {
                        FieldSet.writeField(extension2, this.next.getValue(), output);
                    }
                    if (this.iter.hasNext()) {
                        this.next = this.iter.next();
                        continue;
                    }
                    this.next = null;
                }
            }
        }
    }

    public static interface ExtendableMessageOrBuilder<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>>
    extends MessageLiteOrBuilder {
        public <Type> boolean hasExtension(ExtensionLite<MessageType, Type> var1);

        public <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> var1);

        public <Type> Type getExtension(ExtensionLite<MessageType, Type> var1);

        public <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> var1, int var2);
    }

    public static abstract class Builder<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>>
    extends AbstractMessageLite.Builder<MessageType, BuilderType> {
        private final MessageType defaultInstance;
        protected MessageType instance;
        protected boolean isBuilt;

        protected Builder(MessageType defaultInstance) {
            this.defaultInstance = defaultInstance;
            this.instance = (GeneratedMessageLite)((GeneratedMessageLite)defaultInstance).dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
            this.isBuilt = false;
        }

        protected void copyOnWrite() {
            if (this.isBuilt) {
                GeneratedMessageLite newInstance = (GeneratedMessageLite)((GeneratedMessageLite)this.instance).dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
                this.mergeFromInstance(newInstance, this.instance);
                this.instance = newInstance;
                this.isBuilt = false;
            }
        }

        @Override
        public final boolean isInitialized() {
            return GeneratedMessageLite.isInitialized(this.instance, false);
        }

        public final BuilderType clear() {
            this.instance = (GeneratedMessageLite)((GeneratedMessageLite)this.instance).dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
            return (BuilderType)this;
        }

        @Override
        public BuilderType clone() {
            MessageLite.Builder builder = ((GeneratedMessageLite)this.getDefaultInstanceForType()).newBuilderForType();
            ((Builder)builder).mergeFrom((MessageType)this.buildPartial());
            return (BuilderType)builder;
        }

        public MessageType buildPartial() {
            if (this.isBuilt) {
                return this.instance;
            }
            ((GeneratedMessageLite)this.instance).makeImmutable();
            this.isBuilt = true;
            return this.instance;
        }

        public final MessageType build() {
            MessageLite result2 = this.buildPartial();
            if (!((GeneratedMessageLite)result2).isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return (MessageType)result2;
        }

        @Override
        protected BuilderType internalMergeFrom(MessageType message) {
            return this.mergeFrom(message);
        }

        public BuilderType mergeFrom(MessageType message) {
            this.copyOnWrite();
            this.mergeFromInstance(this.instance, message);
            return (BuilderType)this;
        }

        private void mergeFromInstance(MessageType dest, MessageType src) {
            ((GeneratedMessageLite)dest).visit(MergeFromVisitor.INSTANCE, src);
        }

        public MessageType getDefaultInstanceForType() {
            return this.defaultInstance;
        }

        @Override
        public BuilderType mergeFrom(byte[] input2, int offset, int length) throws InvalidProtocolBufferException {
            return (BuilderType)((Builder)super.mergeFrom(input2, offset, length));
        }

        @Override
        public BuilderType mergeFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            this.copyOnWrite();
            try {
                ((GeneratedMessageLite)this.instance).dynamicMethod(MethodToInvoke.MERGE_FROM_STREAM, input2, extensionRegistry);
            }
            catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw (IOException)e.getCause();
                }
                throw e;
            }
            return (BuilderType)this;
        }
    }

    public static enum MethodToInvoke {
        IS_INITIALIZED,
        VISIT,
        MERGE_FROM_STREAM,
        MAKE_IMMUTABLE,
        GET_MEMOIZED_IS_INITIALIZED,
        SET_MEMOIZED_IS_INITIALIZED,
        NEW_MUTABLE_INSTANCE,
        NEW_BUILDER,
        GET_DEFAULT_INSTANCE,
        GET_PARSER;

    }
}

