/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.CallCredentials;
import io.grpc.ClientStreamTracer;
import io.grpc.Deadline;
import io.grpc.ExperimentalApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@CheckReturnValue
@Immutable
public final class CallOptions {
    public static final CallOptions DEFAULT = new CallOptions();
    private Deadline deadline;
    private Executor executor;
    @Nullable
    private String authority;
    @Nullable
    private CallCredentials credentials;
    @Nullable
    private String compressorName;
    private Object[][] customOptions = new Object[0][2];
    private List<ClientStreamTracer.Factory> streamTracerFactories = Collections.emptyList();
    private boolean waitForReady;
    @Nullable
    private Integer maxInboundMessageSize;
    @Nullable
    private Integer maxOutboundMessageSize;

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1767")
    public CallOptions withAuthority(@Nullable String authority) {
        CallOptions newOptions = new CallOptions(this);
        newOptions.authority = authority;
        return newOptions;
    }

    public CallOptions withCallCredentials(@Nullable CallCredentials credentials) {
        CallOptions newOptions = new CallOptions(this);
        newOptions.credentials = credentials;
        return newOptions;
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1704")
    public CallOptions withCompression(@Nullable String compressorName) {
        CallOptions newOptions = new CallOptions(this);
        newOptions.compressorName = compressorName;
        return newOptions;
    }

    public CallOptions withDeadline(@Nullable Deadline deadline) {
        CallOptions newOptions = new CallOptions(this);
        newOptions.deadline = deadline;
        return newOptions;
    }

    public CallOptions withDeadlineAfter(long duration, TimeUnit unit) {
        return this.withDeadline(Deadline.after(duration, unit));
    }

    @Nullable
    public Deadline getDeadline() {
        return this.deadline;
    }

    public CallOptions withWaitForReady() {
        CallOptions newOptions = new CallOptions(this);
        newOptions.waitForReady = true;
        return newOptions;
    }

    public CallOptions withoutWaitForReady() {
        CallOptions newOptions = new CallOptions(this);
        newOptions.waitForReady = false;
        return newOptions;
    }

    @Nullable
    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1704")
    public String getCompressor() {
        return this.compressorName;
    }

    @Nullable
    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1767")
    public String getAuthority() {
        return this.authority;
    }

    @Nullable
    public CallCredentials getCredentials() {
        return this.credentials;
    }

    public CallOptions withExecutor(Executor executor) {
        CallOptions newOptions = new CallOptions(this);
        newOptions.executor = executor;
        return newOptions;
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/2861")
    public CallOptions withStreamTracerFactory(ClientStreamTracer.Factory factory) {
        CallOptions newOptions = new CallOptions(this);
        ArrayList<ClientStreamTracer.Factory> newList = new ArrayList<ClientStreamTracer.Factory>(this.streamTracerFactories.size() + 1);
        newList.addAll(this.streamTracerFactories);
        newList.add(factory);
        newOptions.streamTracerFactories = Collections.unmodifiableList(newList);
        return newOptions;
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/2861")
    public List<ClientStreamTracer.Factory> getStreamTracerFactories() {
        return this.streamTracerFactories;
    }

    public <T> CallOptions withOption(Key<T> key, T value) {
        Preconditions.checkNotNull(key, "key");
        Preconditions.checkNotNull(value, "value");
        CallOptions newOptions = new CallOptions(this);
        int existingIdx = -1;
        for (int i = 0; i < this.customOptions.length; ++i) {
            if (!key.equals(this.customOptions[i][0])) continue;
            existingIdx = i;
            break;
        }
        newOptions.customOptions = new Object[this.customOptions.length + (existingIdx == -1 ? 1 : 0)][2];
        System.arraycopy(this.customOptions, 0, newOptions.customOptions, 0, this.customOptions.length);
        if (existingIdx == -1) {
            newOptions.customOptions[this.customOptions.length] = new Object[]{key, value};
        } else {
            newOptions.customOptions[existingIdx][1] = value;
        }
        return newOptions;
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1869")
    public <T> T getOption(Key<T> key) {
        Preconditions.checkNotNull(key, "key");
        for (int i = 0; i < this.customOptions.length; ++i) {
            if (!key.equals(this.customOptions[i][0])) continue;
            return (T)this.customOptions[i][1];
        }
        return (T)((Key)key).defaultValue;
    }

    @Nullable
    public Executor getExecutor() {
        return this.executor;
    }

    private CallOptions() {
    }

    public boolean isWaitForReady() {
        return this.waitForReady;
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/2563")
    public CallOptions withMaxInboundMessageSize(int maxSize) {
        Preconditions.checkArgument(maxSize >= 0, "invalid maxsize %s", maxSize);
        CallOptions newOptions = new CallOptions(this);
        newOptions.maxInboundMessageSize = maxSize;
        return newOptions;
    }

    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/2563")
    public CallOptions withMaxOutboundMessageSize(int maxSize) {
        Preconditions.checkArgument(maxSize >= 0, "invalid maxsize %s", maxSize);
        CallOptions newOptions = new CallOptions(this);
        newOptions.maxOutboundMessageSize = maxSize;
        return newOptions;
    }

    @Nullable
    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/2563")
    public Integer getMaxInboundMessageSize() {
        return this.maxInboundMessageSize;
    }

    @Nullable
    @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/2563")
    public Integer getMaxOutboundMessageSize() {
        return this.maxOutboundMessageSize;
    }

    private CallOptions(CallOptions other) {
        this.deadline = other.deadline;
        this.authority = other.authority;
        this.credentials = other.credentials;
        this.executor = other.executor;
        this.compressorName = other.compressorName;
        this.customOptions = other.customOptions;
        this.waitForReady = other.waitForReady;
        this.maxInboundMessageSize = other.maxInboundMessageSize;
        this.maxOutboundMessageSize = other.maxOutboundMessageSize;
        this.streamTracerFactories = other.streamTracerFactories;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("deadline", this.deadline).add("authority", this.authority).add("callCredentials", this.credentials).add("executor", this.executor != null ? this.executor.getClass() : null).add("compressorName", this.compressorName).add("customOptions", Arrays.deepToString((Object[])this.customOptions)).add("waitForReady", this.isWaitForReady()).add("maxInboundMessageSize", this.maxInboundMessageSize).add("maxOutboundMessageSize", this.maxOutboundMessageSize).add("streamTracerFactories", this.streamTracerFactories).toString();
    }

    public static final class Key<T> {
        private final String debugString;
        private final T defaultValue;

        private Key(String debugString, T defaultValue) {
            this.debugString = debugString;
            this.defaultValue = defaultValue;
        }

        public T getDefault() {
            return this.defaultValue;
        }

        public String toString() {
            return this.debugString;
        }

        @Deprecated
        @ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1869")
        public static <T> Key<T> of(String debugString, T defaultValue) {
            Preconditions.checkNotNull(debugString, "debugString");
            return new Key<T>(debugString, defaultValue);
        }

        public static <T> Key<T> create(String debugString) {
            Preconditions.checkNotNull(debugString, "debugString");
            return new Key<Object>(debugString, null);
        }

        public static <T> Key<T> createWithDefault(String debugString, T defaultValue) {
            Preconditions.checkNotNull(debugString, "debugString");
            return new Key<T>(debugString, defaultValue);
        }
    }
}

