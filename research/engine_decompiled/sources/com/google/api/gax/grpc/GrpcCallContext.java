/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.grpc.CallOptionsUtil;
import com.google.api.gax.grpc.GrpcStatusCode;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.DeadlineExceededException;
import com.google.api.gax.rpc.TransportChannel;
import com.google.api.gax.rpc.internal.Headers;
import com.google.auth.Credentials;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.Deadline;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.auth.MoreCallCredentials;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import org.threeten.bp.Duration;

@BetaApi(value="Reference ApiCallContext instead - this class is likely to experience breaking changes")
@InternalExtensionOnly
public final class GrpcCallContext
implements ApiCallContext {
    private final Channel channel;
    private final CallOptions callOptions;
    @Nullable
    private final Duration streamWaitTimeout;
    @Nullable
    private final Duration streamIdleTimeout;
    @Nullable
    private final Integer channelAffinity;
    private final ImmutableMap<String, List<String>> extraHeaders;

    public static GrpcCallContext createDefault() {
        return new GrpcCallContext(null, CallOptions.DEFAULT, null, null, null, ImmutableMap.of());
    }

    public static GrpcCallContext of(Channel channel, CallOptions callOptions) {
        return new GrpcCallContext(channel, callOptions, null, null, null, ImmutableMap.of());
    }

    private GrpcCallContext(Channel channel, CallOptions callOptions, @Nullable Duration streamWaitTimeout, @Nullable Duration streamIdleTimeout, @Nullable Integer channelAffinity, ImmutableMap<String, List<String>> extraHeaders) {
        this.channel = channel;
        this.callOptions = Preconditions.checkNotNull(callOptions);
        this.streamWaitTimeout = streamWaitTimeout;
        this.streamIdleTimeout = streamIdleTimeout;
        this.channelAffinity = channelAffinity;
        this.extraHeaders = Preconditions.checkNotNull(extraHeaders);
    }

    @Override
    public GrpcCallContext nullToSelf(ApiCallContext inputContext) {
        GrpcCallContext grpcCallContext;
        if (inputContext == null) {
            grpcCallContext = this;
        } else {
            if (!(inputContext instanceof GrpcCallContext)) {
                throw new IllegalArgumentException("context must be an instance of GrpcCallContext, but found " + inputContext.getClass().getName());
            }
            grpcCallContext = (GrpcCallContext)inputContext;
        }
        return grpcCallContext;
    }

    @Override
    public GrpcCallContext withCredentials(Credentials newCredentials) {
        Preconditions.checkNotNull(newCredentials);
        CallCredentials callCredentials = MoreCallCredentials.from(newCredentials);
        return this.withCallOptions(this.callOptions.withCallCredentials(callCredentials));
    }

    @Override
    public GrpcCallContext withTransportChannel(TransportChannel inputChannel) {
        Preconditions.checkNotNull(inputChannel);
        if (!(inputChannel instanceof GrpcTransportChannel)) {
            throw new IllegalArgumentException("Expected GrpcTransportChannel, got " + inputChannel.getClass().getName());
        }
        GrpcTransportChannel transportChannel = (GrpcTransportChannel)inputChannel;
        return this.withChannel(transportChannel.getChannel());
    }

    @Override
    public GrpcCallContext withTimeout(Duration rpcTimeout) {
        if (rpcTimeout == null) {
            return this.withCallOptions(this.callOptions.withDeadline(null));
        }
        if (rpcTimeout.isZero() || rpcTimeout.isNegative()) {
            throw new DeadlineExceededException("Invalid timeout: <= 0 s", null, GrpcStatusCode.of(Status.Code.DEADLINE_EXCEEDED), false);
        }
        CallOptions oldOptions = this.callOptions;
        CallOptions newOptions = oldOptions.withDeadlineAfter(rpcTimeout.toMillis(), TimeUnit.MILLISECONDS);
        GrpcCallContext nextContext = this.withCallOptions(newOptions);
        if (oldOptions.getDeadline() == null) {
            return nextContext;
        }
        if (oldOptions.getDeadline().isBefore(newOptions.getDeadline())) {
            return this;
        }
        return nextContext;
    }

    @Override
    public GrpcCallContext withStreamWaitTimeout(@Nullable Duration streamWaitTimeout) {
        if (streamWaitTimeout != null) {
            Preconditions.checkArgument(streamWaitTimeout.compareTo(Duration.ZERO) >= 0, "Invalid timeout: < 0 s");
        }
        return new GrpcCallContext(this.channel, this.callOptions, streamWaitTimeout, this.streamIdleTimeout, this.channelAffinity, this.extraHeaders);
    }

    @Override
    public GrpcCallContext withStreamIdleTimeout(@Nullable Duration streamIdleTimeout) {
        if (streamIdleTimeout != null) {
            Preconditions.checkArgument(streamIdleTimeout.compareTo(Duration.ZERO) >= 0, "Invalid timeout: < 0 s");
        }
        return new GrpcCallContext(this.channel, this.callOptions, this.streamWaitTimeout, streamIdleTimeout, this.channelAffinity, this.extraHeaders);
    }

    @BetaApi(value="The surface for channel affinity is not stable yet and may change in the future.")
    public GrpcCallContext withChannelAffinity(@Nullable Integer affinity) {
        return new GrpcCallContext(this.channel, this.callOptions, this.streamWaitTimeout, this.streamIdleTimeout, affinity, this.extraHeaders);
    }

    @Override
    @BetaApi(value="The surface for extra headers is not stable yet and may change in the future.")
    public GrpcCallContext withExtraHeaders(Map<String, List<String>> extraHeaders) {
        Preconditions.checkNotNull(extraHeaders);
        ImmutableMap<String, List<String>> newExtraHeaders = Headers.mergeHeaders(this.extraHeaders, extraHeaders);
        return new GrpcCallContext(this.channel, this.callOptions, this.streamWaitTimeout, this.streamIdleTimeout, this.channelAffinity, newExtraHeaders);
    }

    @Override
    public ApiCallContext merge(ApiCallContext inputCallContext) {
        Integer newChannelAffinity;
        Duration newStreamIdleTimeout;
        Duration newStreamWaitTimeout;
        CallCredentials newCallCredentials;
        Deadline newDeadline;
        if (inputCallContext == null) {
            return this;
        }
        if (!(inputCallContext instanceof GrpcCallContext)) {
            throw new IllegalArgumentException("context must be an instance of GrpcCallContext, but found " + inputCallContext.getClass().getName());
        }
        GrpcCallContext grpcCallContext = (GrpcCallContext)inputCallContext;
        Channel newChannel = grpcCallContext.channel;
        if (newChannel == null) {
            newChannel = this.channel;
        }
        if ((newDeadline = grpcCallContext.callOptions.getDeadline()) == null) {
            newDeadline = this.callOptions.getDeadline();
        }
        if ((newCallCredentials = grpcCallContext.callOptions.getCredentials()) == null) {
            newCallCredentials = this.callOptions.getCredentials();
        }
        if ((newStreamWaitTimeout = grpcCallContext.streamWaitTimeout) == null) {
            newStreamWaitTimeout = this.streamWaitTimeout;
        }
        if ((newStreamIdleTimeout = grpcCallContext.streamIdleTimeout) == null) {
            newStreamIdleTimeout = this.streamIdleTimeout;
        }
        if ((newChannelAffinity = grpcCallContext.channelAffinity) == null) {
            newChannelAffinity = this.channelAffinity;
        }
        ImmutableMap<String, List<String>> newExtraHeaders = Headers.mergeHeaders(this.extraHeaders, grpcCallContext.extraHeaders);
        CallOptions newCallOptions = grpcCallContext.callOptions.withCallCredentials(newCallCredentials).withDeadline(newDeadline);
        return new GrpcCallContext(newChannel, newCallOptions, newStreamWaitTimeout, newStreamIdleTimeout, newChannelAffinity, newExtraHeaders);
    }

    public Channel getChannel() {
        return this.channel;
    }

    public CallOptions getCallOptions() {
        return this.callOptions;
    }

    @Override
    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    @Nullable
    public Duration getStreamWaitTimeout() {
        return this.streamWaitTimeout;
    }

    @Override
    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    @Nullable
    public Duration getStreamIdleTimeout() {
        return this.streamIdleTimeout;
    }

    @BetaApi(value="The surface for channel affinity is not stable yet and may change in the future.")
    @Nullable
    public Integer getChannelAffinity() {
        return this.channelAffinity;
    }

    @Override
    @BetaApi(value="The surface for extra headers is not stable yet and may change in the future.")
    public Map<String, List<String>> getExtraHeaders() {
        return this.extraHeaders;
    }

    public GrpcCallContext withChannel(Channel newChannel) {
        return new GrpcCallContext(newChannel, this.callOptions, this.streamWaitTimeout, this.streamIdleTimeout, this.channelAffinity, this.extraHeaders);
    }

    public GrpcCallContext withCallOptions(CallOptions newCallOptions) {
        return new GrpcCallContext(this.channel, newCallOptions, this.streamWaitTimeout, this.streamIdleTimeout, this.channelAffinity, this.extraHeaders);
    }

    public GrpcCallContext withRequestParamsDynamicHeaderOption(String requestParams) {
        CallOptions newCallOptions = CallOptionsUtil.putRequestParamsDynamicHeaderOption(this.callOptions, requestParams);
        return this.withCallOptions(newCallOptions);
    }

    public int hashCode() {
        return Objects.hash(this.channel, this.callOptions, this.streamWaitTimeout, this.streamIdleTimeout, this.channelAffinity, this.extraHeaders);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        GrpcCallContext that = (GrpcCallContext)o;
        return Objects.equals(this.channel, that.channel) && Objects.equals(this.callOptions, that.callOptions) && Objects.equals(this.streamWaitTimeout, that.streamWaitTimeout) && Objects.equals(this.streamIdleTimeout, that.streamIdleTimeout) && Objects.equals(this.channelAffinity, that.channelAffinity) && Objects.equals(this.extraHeaders, that.extraHeaders);
    }

    Metadata getMetadata() {
        Metadata metadata = new Metadata();
        for (Map.Entry header : this.extraHeaders.entrySet()) {
            String headerKey = (String)header.getKey();
            for (String headerValue : (List)header.getValue()) {
                metadata.put(Metadata.Key.of(headerKey, Metadata.ASCII_STRING_MARSHALLER), headerValue);
            }
        }
        return metadata;
    }

    @InternalApi(value="for testing")
    Deadline getDeadline() {
        return this.callOptions.getDeadline();
    }
}

