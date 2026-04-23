/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.InternalApi;
import com.google.cloud.RestorableState;
import com.google.cloud.ServiceOptions;
import com.google.cloud.WriteChannel;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class BaseWriteChannel<ServiceOptionsT extends ServiceOptions<?, ServiceOptionsT>, EntityT extends Serializable>
implements WriteChannel {
    private static final int MIN_CHUNK_SIZE = 262144;
    private static final int DEFAULT_CHUNK_SIZE = 0x200000;
    private final ServiceOptionsT options;
    private final EntityT entity;
    private final String uploadId;
    private long position;
    private byte[] buffer = new byte[0];
    private int limit;
    private boolean isOpen = true;
    private int chunkSize = this.getDefaultChunkSize();

    protected int getMinChunkSize() {
        return 262144;
    }

    protected int getDefaultChunkSize() {
        return 0x200000;
    }

    protected abstract void flushBuffer(int var1, boolean var2);

    protected ServiceOptionsT getOptions() {
        return this.options;
    }

    protected EntityT getEntity() {
        return this.entity;
    }

    protected String getUploadId() {
        return this.uploadId;
    }

    protected long getPosition() {
        return this.position;
    }

    protected byte[] getBuffer() {
        return this.buffer;
    }

    protected int getLimit() {
        return this.limit;
    }

    protected int getChunkSize() {
        return this.chunkSize;
    }

    @Override
    public final void setChunkSize(int chunkSize) {
        int minSize = this.getMinChunkSize();
        this.chunkSize = Math.max(minSize, (chunkSize + minSize - 1) / minSize * minSize);
    }

    @InternalApi(value="This class should only be extended within google-cloud-java")
    protected BaseWriteChannel(ServiceOptionsT options, EntityT entity, String uploadId) {
        this.options = options;
        this.entity = entity;
        this.uploadId = uploadId;
    }

    private void flush() {
        if (this.limit >= this.chunkSize) {
            int length = this.limit - this.limit % this.getMinChunkSize();
            this.flushBuffer(length, false);
            this.position += (long)length;
            this.limit -= length;
            byte[] temp = new byte[this.chunkSize];
            System.arraycopy(this.buffer, length, temp, 0, this.limit);
            this.buffer = temp;
        }
    }

    private void validateOpen() throws ClosedChannelException {
        if (!this.isOpen) {
            throw new ClosedChannelException();
        }
    }

    @Override
    public final int write(ByteBuffer byteBuffer) throws IOException {
        this.validateOpen();
        int toWrite = byteBuffer.remaining();
        int spaceInBuffer = this.buffer.length - this.limit;
        if (spaceInBuffer >= toWrite) {
            byteBuffer.get(this.buffer, this.limit, toWrite);
        } else {
            this.buffer = Arrays.copyOf(this.buffer, Math.max(this.chunkSize, this.buffer.length + toWrite - spaceInBuffer));
            byteBuffer.get(this.buffer, this.limit, toWrite);
        }
        this.limit += toWrite;
        this.flush();
        return toWrite;
    }

    @Override
    public boolean isOpen() {
        return this.isOpen;
    }

    @Override
    public final void close() throws IOException {
        if (this.isOpen) {
            this.flushBuffer(this.limit, true);
            this.position += (long)this.buffer.length;
            this.isOpen = false;
            this.buffer = null;
        }
    }

    protected abstract BaseState.Builder<ServiceOptionsT, EntityT> stateBuilder();

    @Override
    public RestorableState<WriteChannel> capture() {
        byte[] bufferToSave = null;
        if (this.isOpen) {
            bufferToSave = Arrays.copyOf(this.buffer, this.limit);
        }
        return this.stateBuilder().setPosition(this.position).setBuffer(bufferToSave).setIsOpen(this.isOpen).setChunkSize(this.chunkSize).build();
    }

    protected void restore(BaseState state) {
        if (state.buffer != null) {
            this.buffer = (byte[])state.buffer.clone();
            this.limit = state.buffer.length;
        }
        this.position = state.position;
        this.isOpen = state.isOpen;
        this.chunkSize = state.chunkSize;
    }

    protected static abstract class BaseState<ServiceOptionsT extends ServiceOptions<?, ServiceOptionsT>, EntityT extends Serializable>
    implements RestorableState<WriteChannel>,
    Serializable {
        private static final long serialVersionUID = 8541062465055125619L;
        protected final ServiceOptionsT serviceOptions;
        protected final EntityT entity;
        protected final String uploadId;
        protected final long position;
        protected final byte[] buffer;
        protected final boolean isOpen;
        protected final int chunkSize;

        @InternalApi(value="This class should only be extended within google-cloud-java")
        protected BaseState(Builder<ServiceOptionsT, EntityT> builder) {
            this.serviceOptions = ((Builder)builder).serviceOptions;
            this.entity = ((Builder)builder).entity;
            this.uploadId = ((Builder)builder).uploadId;
            this.position = ((Builder)builder).position;
            this.buffer = ((Builder)builder).buffer;
            this.isOpen = ((Builder)builder).isOpen;
            this.chunkSize = ((Builder)builder).chunkSize;
        }

        public int hashCode() {
            return Objects.hash(this.serviceOptions, this.entity, this.uploadId, this.position, this.isOpen, this.chunkSize, Arrays.hashCode(this.buffer));
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof BaseState)) {
                return false;
            }
            BaseState other = (BaseState)obj;
            return Objects.equals(this.serviceOptions, other.serviceOptions) && Objects.equals(this.entity, other.entity) && Objects.equals(this.uploadId, other.uploadId) && Objects.deepEquals(this.buffer, other.buffer) && this.position == other.position && this.isOpen == other.isOpen && this.chunkSize == other.chunkSize;
        }

        protected List<ValueHolder> toStringHelper() {
            ArrayList<ValueHolder> valueList = new ArrayList<ValueHolder>();
            valueList.add(ValueHolder.create("entity", this.entity));
            valueList.add(ValueHolder.create("uploadId", this.uploadId));
            valueList.add(ValueHolder.create("position", String.valueOf(this.position)));
            valueList.add(ValueHolder.create("isOpen", String.valueOf(this.isOpen)));
            return valueList;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(this.getClass().getSimpleName()).append('{');
            String nextSeparator = "";
            for (ValueHolder valueHolder : this.toStringHelper()) {
                builder.append(nextSeparator).append(valueHolder);
                nextSeparator = ", ";
            }
            builder.append('}');
            return builder.toString();
        }

        protected static final class ValueHolder {
            final String name;
            final Object value;

            private ValueHolder(String name, Object value) {
                this.name = name;
                this.value = value;
            }

            public static ValueHolder create(String name, Object value) {
                return new ValueHolder(name, value);
            }

            public String toString() {
                String result2 = this.name + "=";
                if (this.value != null && this.value.getClass().isArray()) {
                    Object[] objectArray = new Object[]{this.value};
                    String arrayString2 = Arrays.deepToString(objectArray);
                    result2 = result2 + arrayString2.substring(1, arrayString2.length() - 1);
                } else {
                    result2 = result2 + this.value;
                }
                return result2;
            }
        }

        public static abstract class Builder<ServiceOptionsT extends ServiceOptions<?, ServiceOptionsT>, EntityT extends Serializable> {
            private final ServiceOptionsT serviceOptions;
            private final EntityT entity;
            private final String uploadId;
            private long position;
            private byte[] buffer;
            private boolean isOpen;
            private int chunkSize;

            @InternalApi(value="This class should only be extended within google-cloud-java")
            protected Builder(ServiceOptionsT options, EntityT entity, String uploadId) {
                this.serviceOptions = options;
                this.entity = entity;
                this.uploadId = uploadId;
            }

            public Builder<ServiceOptionsT, EntityT> setPosition(long position) {
                this.position = position;
                return this;
            }

            public Builder<ServiceOptionsT, EntityT> setBuffer(byte[] buffer) {
                this.buffer = buffer;
                return this;
            }

            public Builder<ServiceOptionsT, EntityT> setIsOpen(boolean isOpen) {
                this.isOpen = isOpen;
                return this;
            }

            public Builder<ServiceOptionsT, EntityT> setChunkSize(int chunkSize) {
                this.chunkSize = chunkSize;
                return this;
            }

            public abstract RestorableState<WriteChannel> build();
        }
    }
}

