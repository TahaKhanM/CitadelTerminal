/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.protobuf.lite;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import io.grpc.ExperimentalApi;
import io.grpc.KnownLength;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.protobuf.lite.ProtoInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

@ExperimentalApi(value="Experimental until Lite is stable in protobuf")
public class ProtoLiteUtils {
    private static volatile ExtensionRegistryLite globalRegistry = ExtensionRegistryLite.getEmptyRegistry();
    private static final int BUF_SIZE = 8192;
    @VisibleForTesting
    static final int DEFAULT_MAX_MESSAGE_SIZE = 0x400000;
    private static final ThreadLocal<Reference<byte[]>> bufs = new ThreadLocal<Reference<byte[]>>(){

        @Override
        protected Reference<byte[]> initialValue() {
            return new WeakReference<byte[]>(new byte[4096]);
        }
    };

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1787")
    public static void setExtensionRegistry(ExtensionRegistryLite newRegistry) {
        globalRegistry = Preconditions.checkNotNull(newRegistry, "newRegistry");
    }

    public static <T extends MessageLite> MethodDescriptor.Marshaller<T> marshaller(final T defaultInstance) {
        final Parser<? extends MessageLite> parser = defaultInstance.getParserForType();
        return new MethodDescriptor.PrototypeMarshaller<T>(){

            @Override
            public Class<T> getMessageClass() {
                return defaultInstance.getClass();
            }

            @Override
            public T getMessagePrototype() {
                return defaultInstance;
            }

            @Override
            public InputStream stream(T value) {
                return new ProtoInputStream((MessageLite)value, parser);
            }

            @Override
            public T parse(InputStream stream) {
                ProtoInputStream protoStream;
                if (stream instanceof ProtoInputStream && (protoStream = (ProtoInputStream)stream).parser() == parser) {
                    try {
                        MessageLite message = ((ProtoInputStream)stream).message();
                        return message;
                    }
                    catch (IllegalStateException message) {
                        // empty catch block
                    }
                }
                CodedInputStream cis = null;
                try {
                    if (stream instanceof KnownLength) {
                        int size2 = stream.available();
                        if (size2 > 0 && size2 <= 0x400000) {
                            int position;
                            int remaining;
                            int count2;
                            byte[] buf = (byte[])((Reference)bufs.get()).get();
                            if (buf == null || buf.length < size2) {
                                buf = new byte[size2];
                                bufs.set(new WeakReference<byte[]>(buf));
                            }
                            for (remaining = size2; remaining > 0 && (count2 = stream.read(buf, position = size2 - remaining, remaining)) != -1; remaining -= count2) {
                            }
                            if (remaining != 0) {
                                position = size2 - remaining;
                                throw new RuntimeException("size inaccurate: " + size2 + " != " + position);
                            }
                            cis = CodedInputStream.newInstance(buf, 0, size2);
                        } else if (size2 == 0) {
                            return defaultInstance;
                        }
                    }
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (cis == null) {
                    cis = CodedInputStream.newInstance(stream);
                }
                cis.setSizeLimit(Integer.MAX_VALUE);
                try {
                    return this.parseFrom(cis);
                }
                catch (InvalidProtocolBufferException ipbe) {
                    throw Status.INTERNAL.withDescription("Invalid protobuf byte sequence").withCause(ipbe).asRuntimeException();
                }
            }

            private T parseFrom(CodedInputStream stream) throws InvalidProtocolBufferException {
                MessageLite message = (MessageLite)parser.parseFrom(stream, globalRegistry);
                try {
                    stream.checkLastTagWas(0);
                    return message;
                }
                catch (InvalidProtocolBufferException e) {
                    e.setUnfinishedMessage(message);
                    throw e;
                }
            }
        };
    }

    public static <T extends MessageLite> Metadata.BinaryMarshaller<T> metadataMarshaller(final T instance) {
        return new Metadata.BinaryMarshaller<T>(){

            @Override
            public byte[] toBytes(T value) {
                return value.toByteArray();
            }

            @Override
            public T parseBytes(byte[] serialized) {
                try {
                    return instance.getParserForType().parseFrom(serialized, globalRegistry);
                }
                catch (InvalidProtocolBufferException ipbe) {
                    throw new IllegalArgumentException(ipbe);
                }
            }
        };
    }

    static long copy(InputStream from2, OutputStream to2) throws IOException {
        int r;
        Preconditions.checkNotNull(from2);
        Preconditions.checkNotNull(to2);
        byte[] buf = new byte[8192];
        long total2 = 0L;
        while ((r = from2.read(buf)) != -1) {
            to2.write(buf, 0, r);
            total2 += (long)r;
        }
        return total2;
    }

    private ProtoLiteUtils() {
    }
}

