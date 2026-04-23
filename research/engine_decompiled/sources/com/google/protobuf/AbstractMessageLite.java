/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.MessageLite;
import com.google.protobuf.PrimitiveNonBoxingCollection;
import com.google.protobuf.UninitializedMessageException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractMessageLite<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>>
implements MessageLite {
    protected int memoizedHashCode = 0;

    @Override
    public ByteString toByteString() {
        try {
            ByteString.CodedBuilder out = ByteString.newCodedBuilder(this.getSerializedSize());
            this.writeTo(out.getCodedOutput());
            return out.build();
        }
        catch (IOException e) {
            throw new RuntimeException(this.getSerializingExceptionMessage("ByteString"), e);
        }
    }

    @Override
    public byte[] toByteArray() {
        try {
            byte[] result2 = new byte[this.getSerializedSize()];
            CodedOutputStream output = CodedOutputStream.newInstance(result2);
            this.writeTo(output);
            output.checkNoSpaceLeft();
            return result2;
        }
        catch (IOException e) {
            throw new RuntimeException(this.getSerializingExceptionMessage("byte array"), e);
        }
    }

    @Override
    public void writeTo(OutputStream output) throws IOException {
        int bufferSize = CodedOutputStream.computePreferredBufferSize(this.getSerializedSize());
        CodedOutputStream codedOutput = CodedOutputStream.newInstance(output, bufferSize);
        this.writeTo(codedOutput);
        codedOutput.flush();
    }

    @Override
    public void writeDelimitedTo(OutputStream output) throws IOException {
        int serialized = this.getSerializedSize();
        int bufferSize = CodedOutputStream.computePreferredBufferSize(CodedOutputStream.computeRawVarint32Size(serialized) + serialized);
        CodedOutputStream codedOutput = CodedOutputStream.newInstance(output, bufferSize);
        codedOutput.writeRawVarint32(serialized);
        this.writeTo(codedOutput);
        codedOutput.flush();
    }

    int getMemoizedSerializedSize() {
        throw new UnsupportedOperationException();
    }

    void setMemoizedSerializedSize(int size2) {
        throw new UnsupportedOperationException();
    }

    UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException(this);
    }

    private String getSerializingExceptionMessage(String target) {
        return "Serializing " + this.getClass().getName() + " to a " + target + " threw an IOException (should never happen).";
    }

    protected static void checkByteStringIsUtf8(ByteString byteString) throws IllegalArgumentException {
        if (!byteString.isValidUtf8()) {
            throw new IllegalArgumentException("Byte string is not UTF-8.");
        }
    }

    @Deprecated
    protected static <T> void addAll(Iterable<T> values, Collection<? super T> list2) {
        Builder.addAll(values, (List)list2);
    }

    protected static <T> void addAll(Iterable<T> values, List<? super T> list2) {
        Builder.addAll(values, list2);
    }

    public static abstract class Builder<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>>
    implements MessageLite.Builder {
        public abstract BuilderType clone();

        public BuilderType mergeFrom(CodedInputStream input2) throws IOException {
            return (BuilderType)this.mergeFrom(input2, ExtensionRegistryLite.getEmptyRegistry());
        }

        public abstract BuilderType mergeFrom(CodedInputStream var1, ExtensionRegistryLite var2) throws IOException;

        public BuilderType mergeFrom(ByteString data) throws InvalidProtocolBufferException {
            try {
                CodedInputStream input2 = data.newCodedInput();
                this.mergeFrom(input2);
                input2.checkLastTagWas(0);
                return (BuilderType)this;
            }
            catch (InvalidProtocolBufferException e) {
                throw e;
            }
            catch (IOException e) {
                throw new RuntimeException(this.getReadingExceptionMessage("ByteString"), e);
            }
        }

        public BuilderType mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            try {
                CodedInputStream input2 = data.newCodedInput();
                this.mergeFrom(input2, extensionRegistry);
                input2.checkLastTagWas(0);
                return (BuilderType)this;
            }
            catch (InvalidProtocolBufferException e) {
                throw e;
            }
            catch (IOException e) {
                throw new RuntimeException(this.getReadingExceptionMessage("ByteString"), e);
            }
        }

        public BuilderType mergeFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BuilderType)this.mergeFrom(data, 0, data.length);
        }

        public BuilderType mergeFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
            try {
                CodedInputStream input2 = CodedInputStream.newInstance(data, off, len);
                this.mergeFrom(input2);
                input2.checkLastTagWas(0);
                return (BuilderType)this;
            }
            catch (InvalidProtocolBufferException e) {
                throw e;
            }
            catch (IOException e) {
                throw new RuntimeException(this.getReadingExceptionMessage("byte array"), e);
            }
        }

        public BuilderType mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BuilderType)this.mergeFrom(data, 0, data.length, extensionRegistry);
        }

        public BuilderType mergeFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            try {
                CodedInputStream input2 = CodedInputStream.newInstance(data, off, len);
                this.mergeFrom(input2, extensionRegistry);
                input2.checkLastTagWas(0);
                return (BuilderType)this;
            }
            catch (InvalidProtocolBufferException e) {
                throw e;
            }
            catch (IOException e) {
                throw new RuntimeException(this.getReadingExceptionMessage("byte array"), e);
            }
        }

        public BuilderType mergeFrom(InputStream input2) throws IOException {
            CodedInputStream codedInput = CodedInputStream.newInstance(input2);
            this.mergeFrom(codedInput);
            codedInput.checkLastTagWas(0);
            return (BuilderType)this;
        }

        public BuilderType mergeFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            CodedInputStream codedInput = CodedInputStream.newInstance(input2);
            this.mergeFrom(codedInput, extensionRegistry);
            codedInput.checkLastTagWas(0);
            return (BuilderType)this;
        }

        @Override
        public boolean mergeDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            int firstByte = input2.read();
            if (firstByte == -1) {
                return false;
            }
            int size2 = CodedInputStream.readRawVarint32(firstByte, input2);
            LimitedInputStream limitedInput = new LimitedInputStream(input2, size2);
            this.mergeFrom(limitedInput, extensionRegistry);
            return true;
        }

        @Override
        public boolean mergeDelimitedFrom(InputStream input2) throws IOException {
            return this.mergeDelimitedFrom(input2, ExtensionRegistryLite.getEmptyRegistry());
        }

        public BuilderType mergeFrom(MessageLite other) {
            if (!this.getDefaultInstanceForType().getClass().isInstance(other)) {
                throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
            }
            return this.internalMergeFrom((AbstractMessageLite)other);
        }

        protected abstract BuilderType internalMergeFrom(MessageType var1);

        private String getReadingExceptionMessage(String target) {
            return "Reading " + this.getClass().getName() + " from a " + target + " threw an IOException (should never happen).";
        }

        private static <T> void addAllCheckingNulls(Iterable<T> values, List<? super T> list2) {
            if (list2 instanceof ArrayList && values instanceof Collection) {
                ((ArrayList)list2).ensureCapacity(list2.size() + ((Collection)values).size());
            }
            int begin = list2.size();
            for (T value : values) {
                if (value == null) {
                    String message = "Element at index " + (list2.size() - begin) + " is null.";
                    for (int i = list2.size() - 1; i >= begin; --i) {
                        list2.remove(i);
                    }
                    throw new NullPointerException(message);
                }
                list2.add(value);
            }
        }

        protected static UninitializedMessageException newUninitializedMessageException(MessageLite message) {
            return new UninitializedMessageException(message);
        }

        @Deprecated
        protected static <T> void addAll(Iterable<T> values, Collection<? super T> list2) {
            Builder.addAll(values, (List)list2);
        }

        protected static <T> void addAll(Iterable<T> values, List<? super T> list2) {
            Internal.checkNotNull(values);
            if (values instanceof LazyStringList) {
                List<?> lazyValues = ((LazyStringList)values).getUnderlyingElements();
                LazyStringList lazyList = (LazyStringList)list2;
                int begin = list2.size();
                for (Object value : lazyValues) {
                    if (value == null) {
                        String message = "Element at index " + (lazyList.size() - begin) + " is null.";
                        for (int i = lazyList.size() - 1; i >= begin; --i) {
                            lazyList.remove(i);
                        }
                        throw new NullPointerException(message);
                    }
                    if (value instanceof ByteString) {
                        lazyList.add((ByteString)value);
                        continue;
                    }
                    lazyList.add((String)value);
                }
            } else if (values instanceof PrimitiveNonBoxingCollection) {
                list2.addAll((Collection)values);
            } else {
                Builder.addAllCheckingNulls(values, list2);
            }
        }

        static final class LimitedInputStream
        extends FilterInputStream {
            private int limit;

            LimitedInputStream(InputStream in, int limit) {
                super(in);
                this.limit = limit;
            }

            @Override
            public int available() throws IOException {
                return Math.min(super.available(), this.limit);
            }

            @Override
            public int read() throws IOException {
                if (this.limit <= 0) {
                    return -1;
                }
                int result2 = super.read();
                if (result2 >= 0) {
                    --this.limit;
                }
                return result2;
            }

            @Override
            public int read(byte[] b, int off, int len) throws IOException {
                if (this.limit <= 0) {
                    return -1;
                }
                int result2 = super.read(b, off, len = Math.min(len, this.limit));
                if (result2 >= 0) {
                    this.limit -= result2;
                }
                return result2;
            }

            @Override
            public long skip(long n) throws IOException {
                long result2 = super.skip(Math.min(n, (long)this.limit));
                if (result2 >= 0L) {
                    this.limit = (int)((long)this.limit - result2);
                }
                return result2;
            }
        }
    }
}

