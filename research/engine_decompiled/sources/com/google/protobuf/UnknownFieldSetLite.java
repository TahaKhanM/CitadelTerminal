/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteToString;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.util.Arrays;

public final class UnknownFieldSetLite {
    private static final int MIN_CAPACITY = 8;
    private static final UnknownFieldSetLite DEFAULT_INSTANCE = new UnknownFieldSetLite(0, new int[0], new Object[0], false);
    private int count;
    private int[] tags;
    private Object[] objects;
    private int memoizedSerializedSize = -1;
    private boolean isMutable;

    public static UnknownFieldSetLite getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    static UnknownFieldSetLite newInstance() {
        return new UnknownFieldSetLite();
    }

    static UnknownFieldSetLite mutableCopyOf(UnknownFieldSetLite first, UnknownFieldSetLite second) {
        int count2 = first.count + second.count;
        int[] tags = Arrays.copyOf(first.tags, count2);
        System.arraycopy(second.tags, 0, tags, first.count, second.count);
        Object[] objects = Arrays.copyOf(first.objects, count2);
        System.arraycopy(second.objects, 0, objects, first.count, second.count);
        return new UnknownFieldSetLite(count2, tags, objects, true);
    }

    private UnknownFieldSetLite() {
        this(0, new int[8], new Object[8], true);
    }

    private UnknownFieldSetLite(int count2, int[] tags, Object[] objects, boolean isMutable) {
        this.count = count2;
        this.tags = tags;
        this.objects = objects;
        this.isMutable = isMutable;
    }

    public void makeImmutable() {
        this.isMutable = false;
    }

    void checkMutable() {
        if (!this.isMutable) {
            throw new UnsupportedOperationException();
        }
    }

    public void writeTo(CodedOutputStream output) throws IOException {
        block7: for (int i = 0; i < this.count; ++i) {
            int tag = this.tags[i];
            int fieldNumber = WireFormat.getTagFieldNumber(tag);
            switch (WireFormat.getTagWireType(tag)) {
                case 0: {
                    output.writeUInt64(fieldNumber, (Long)this.objects[i]);
                    continue block7;
                }
                case 5: {
                    output.writeFixed32(fieldNumber, (Integer)this.objects[i]);
                    continue block7;
                }
                case 1: {
                    output.writeFixed64(fieldNumber, (Long)this.objects[i]);
                    continue block7;
                }
                case 2: {
                    output.writeBytes(fieldNumber, (ByteString)this.objects[i]);
                    continue block7;
                }
                case 3: {
                    output.writeTag(fieldNumber, 3);
                    ((UnknownFieldSetLite)this.objects[i]).writeTo(output);
                    output.writeTag(fieldNumber, 4);
                    continue block7;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
        }
    }

    public void writeAsMessageSetTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.count; ++i) {
            int fieldNumber = WireFormat.getTagFieldNumber(this.tags[i]);
            output.writeRawMessageSetExtension(fieldNumber, (ByteString)this.objects[i]);
        }
    }

    public int getSerializedSizeAsMessageSet() {
        int size2 = this.memoizedSerializedSize;
        if (size2 != -1) {
            return size2;
        }
        size2 = 0;
        for (int i = 0; i < this.count; ++i) {
            int tag = this.tags[i];
            int fieldNumber = WireFormat.getTagFieldNumber(tag);
            size2 += CodedOutputStream.computeRawMessageSetExtensionSize(fieldNumber, (ByteString)this.objects[i]);
        }
        this.memoizedSerializedSize = size2;
        return size2;
    }

    public int getSerializedSize() {
        int size2 = this.memoizedSerializedSize;
        if (size2 != -1) {
            return size2;
        }
        size2 = 0;
        block7: for (int i = 0; i < this.count; ++i) {
            int tag = this.tags[i];
            int fieldNumber = WireFormat.getTagFieldNumber(tag);
            switch (WireFormat.getTagWireType(tag)) {
                case 0: {
                    size2 += CodedOutputStream.computeUInt64Size(fieldNumber, (Long)this.objects[i]);
                    continue block7;
                }
                case 5: {
                    size2 += CodedOutputStream.computeFixed32Size(fieldNumber, (Integer)this.objects[i]);
                    continue block7;
                }
                case 1: {
                    size2 += CodedOutputStream.computeFixed64Size(fieldNumber, (Long)this.objects[i]);
                    continue block7;
                }
                case 2: {
                    size2 += CodedOutputStream.computeBytesSize(fieldNumber, (ByteString)this.objects[i]);
                    continue block7;
                }
                case 3: {
                    size2 += CodedOutputStream.computeTagSize(fieldNumber) * 2 + ((UnknownFieldSetLite)this.objects[i]).getSerializedSize();
                    continue block7;
                }
                default: {
                    throw new IllegalStateException(InvalidProtocolBufferException.invalidWireType());
                }
            }
        }
        this.memoizedSerializedSize = size2;
        return size2;
    }

    private static boolean equals(int[] tags1, int[] tags2, int count2) {
        for (int i = 0; i < count2; ++i) {
            if (tags1[i] == tags2[i]) continue;
            return false;
        }
        return true;
    }

    private static boolean equals(Object[] objects1, Object[] objects2, int count2) {
        for (int i = 0; i < count2; ++i) {
            if (objects1[i].equals(objects2[i])) continue;
            return false;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UnknownFieldSetLite)) {
            return false;
        }
        UnknownFieldSetLite other = (UnknownFieldSetLite)obj;
        return this.count == other.count && UnknownFieldSetLite.equals(this.tags, other.tags, this.count) && UnknownFieldSetLite.equals(this.objects, other.objects, this.count);
    }

    private static int hashCode(int[] tags, int count2) {
        int hashCode = 17;
        for (int i = 0; i < count2; ++i) {
            hashCode = 31 * hashCode + tags[i];
        }
        return hashCode;
    }

    private static int hashCode(Object[] objects, int count2) {
        int hashCode = 17;
        for (int i = 0; i < count2; ++i) {
            hashCode = 31 * hashCode + objects[i].hashCode();
        }
        return hashCode;
    }

    public int hashCode() {
        int hashCode = 17;
        hashCode = 31 * hashCode + this.count;
        hashCode = 31 * hashCode + UnknownFieldSetLite.hashCode(this.tags, this.count);
        hashCode = 31 * hashCode + UnknownFieldSetLite.hashCode(this.objects, this.count);
        return hashCode;
    }

    final void printWithIndent(StringBuilder buffer, int indent) {
        for (int i = 0; i < this.count; ++i) {
            int fieldNumber = WireFormat.getTagFieldNumber(this.tags[i]);
            MessageLiteToString.printField(buffer, indent, String.valueOf(fieldNumber), this.objects[i]);
        }
    }

    void storeField(int tag, Object value) {
        this.checkMutable();
        this.ensureCapacity();
        this.tags[this.count] = tag;
        this.objects[this.count] = value;
        ++this.count;
    }

    private void ensureCapacity() {
        if (this.count == this.tags.length) {
            int increment = this.count < 4 ? 8 : this.count >> 1;
            int newLength = this.count + increment;
            this.tags = Arrays.copyOf(this.tags, newLength);
            this.objects = Arrays.copyOf(this.objects, newLength);
        }
    }

    boolean mergeFieldFrom(int tag, CodedInputStream input2) throws IOException {
        this.checkMutable();
        int fieldNumber = WireFormat.getTagFieldNumber(tag);
        switch (WireFormat.getTagWireType(tag)) {
            case 0: {
                this.storeField(tag, input2.readInt64());
                return true;
            }
            case 5: {
                this.storeField(tag, input2.readFixed32());
                return true;
            }
            case 1: {
                this.storeField(tag, input2.readFixed64());
                return true;
            }
            case 2: {
                this.storeField(tag, input2.readBytes());
                return true;
            }
            case 3: {
                UnknownFieldSetLite subFieldSet = new UnknownFieldSetLite();
                subFieldSet.mergeFrom(input2);
                input2.checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
                this.storeField(tag, subFieldSet);
                return true;
            }
            case 4: {
                return false;
            }
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    UnknownFieldSetLite mergeVarintField(int fieldNumber, int value) {
        this.checkMutable();
        if (fieldNumber == 0) {
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }
        this.storeField(WireFormat.makeTag(fieldNumber, 0), value);
        return this;
    }

    UnknownFieldSetLite mergeLengthDelimitedField(int fieldNumber, ByteString value) {
        this.checkMutable();
        if (fieldNumber == 0) {
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }
        this.storeField(WireFormat.makeTag(fieldNumber, 2), value);
        return this;
    }

    private UnknownFieldSetLite mergeFrom(CodedInputStream input2) throws IOException {
        int tag;
        while ((tag = input2.readTag()) != 0 && this.mergeFieldFrom(tag, input2)) {
        }
        return this;
    }
}

