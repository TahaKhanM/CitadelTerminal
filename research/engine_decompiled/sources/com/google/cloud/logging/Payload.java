/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.cloud.logging.Structs;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.logging.v2.LogEntry;
import com.google.protobuf.Any;
import com.google.protobuf.Struct;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public abstract class Payload<T>
implements Serializable {
    private static final long serialVersionUID = -3802834715329130521L;
    private final Type type;
    private final T data;

    private Payload(Type type, T data) {
        this.type = Preconditions.checkNotNull(type);
        this.data = Preconditions.checkNotNull(data);
    }

    public Type getType() {
        return this.type;
    }

    public T getData() {
        return this.data;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof Payload)) {
            return false;
        }
        Payload other = (Payload)obj;
        return Objects.equals((Object)this.type, (Object)other.type) && Objects.equals(this.data, other.data);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.type, this.data});
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("type", (Object)this.type).add("data", this.data).toString();
    }

    abstract LogEntry.Builder toPb();

    static <T extends Payload<?>> T fromPb(LogEntry entryPb) {
        switch (entryPb.getPayloadCase()) {
            case TEXT_PAYLOAD: {
                return (T)StringPayload.fromPb(entryPb);
            }
            case JSON_PAYLOAD: {
                return (T)JsonPayload.fromPb(entryPb);
            }
            case PROTO_PAYLOAD: {
                return (T)ProtoPayload.fromPb(entryPb);
            }
            case PAYLOAD_NOT_SET: {
                return null;
            }
        }
        throw new IllegalArgumentException("Unrecognized log entry payload");
    }

    public static final class ProtoPayload
    extends Payload<Any> {
        private static final long serialVersionUID = 155951112369716872L;

        ProtoPayload(Any data) {
            super(Type.PROTO, data);
        }

        @Override
        LogEntry.Builder toPb() {
            return LogEntry.newBuilder().setProtoPayload((Any)this.getData());
        }

        public static ProtoPayload of(Any data) {
            return new ProtoPayload(data);
        }

        static ProtoPayload fromPb(LogEntry entryPb) {
            return ProtoPayload.of(entryPb.getProtoPayload());
        }
    }

    public static final class JsonPayload
    extends Payload<Struct> {
        private static final long serialVersionUID = 5747721918608143350L;

        JsonPayload(Struct jsonData) {
            super(Type.JSON, jsonData);
        }

        public Map<String, Object> getDataAsMap() {
            return Structs.asMap((Struct)this.getData());
        }

        @Override
        LogEntry.Builder toPb() {
            return LogEntry.newBuilder().setJsonPayload((Struct)this.getData());
        }

        public static JsonPayload of(Map<String, ?> data) {
            return new JsonPayload(Structs.newStruct(data));
        }

        public static JsonPayload of(Struct data) {
            return new JsonPayload(data);
        }

        static JsonPayload fromPb(LogEntry entryPb) {
            return JsonPayload.of(entryPb.getJsonPayload());
        }
    }

    public static final class StringPayload
    extends Payload<String> {
        private static final long serialVersionUID = 646595882175676029L;

        StringPayload(String data) {
            super(Type.STRING, data);
        }

        @Override
        LogEntry.Builder toPb() {
            return LogEntry.newBuilder().setTextPayload((String)this.getData());
        }

        public static StringPayload of(String data) {
            return new StringPayload(data);
        }

        static StringPayload fromPb(LogEntry entryPb) {
            return StringPayload.of(entryPb.getTextPayload());
        }
    }

    public static enum Type {
        STRING,
        JSON,
        PROTO;

    }
}

