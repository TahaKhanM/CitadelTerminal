/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.api.core.ApiFunction;
import com.google.cloud.StringEnumType;
import com.google.cloud.StringEnumValue;
import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.logging.type.HttpRequest;
import java.io.Serializable;
import java.util.Objects;
import org.threeten.bp.Duration;

public final class HttpRequest
implements Serializable {
    private static final long serialVersionUID = -274998005454709817L;
    private final RequestMethod requestMethod;
    private final String requestUrl;
    private final Long requestSize;
    private final Integer status;
    private final Long responseSize;
    private final String userAgent;
    private final String remoteIp;
    private final String serverIp;
    private final String referer;
    private final boolean cacheLookup;
    private final boolean cacheHit;
    private final boolean cacheValidatedWithOriginServer;
    private final Long cacheFillBytes;
    private final Duration latency;

    HttpRequest(Builder builder) {
        this.requestMethod = builder.requestMethod;
        this.requestUrl = builder.requestUrl;
        this.requestSize = builder.requestSize;
        this.status = builder.status;
        this.responseSize = builder.responseSize;
        this.userAgent = builder.userAgent;
        this.remoteIp = builder.remoteIp;
        this.serverIp = builder.serverIp;
        this.referer = builder.referer;
        this.cacheLookup = builder.cacheLookup;
        this.cacheHit = builder.cacheHit;
        this.cacheValidatedWithOriginServer = builder.cacheValidatedWithOriginServer;
        this.cacheFillBytes = builder.cacheFillBytes;
        this.latency = builder.latency;
    }

    public RequestMethod getRequestMethod() {
        return this.requestMethod;
    }

    public String getRequestUrl() {
        return this.requestUrl;
    }

    public Long getRequestSize() {
        return this.requestSize;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Long getResponseSize() {
        return this.responseSize;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public String getRemoteIp() {
        return this.remoteIp;
    }

    public String getServerIp() {
        return this.serverIp;
    }

    public String getReferer() {
        return this.referer;
    }

    public boolean cacheLookup() {
        return this.cacheLookup;
    }

    public boolean cacheHit() {
        return this.cacheHit;
    }

    public boolean cacheValidatedWithOriginServer() {
        return this.cacheValidatedWithOriginServer;
    }

    public Long getCacheFillBytes() {
        return this.cacheFillBytes;
    }

    public Duration getLatency() {
        return this.latency;
    }

    public int hashCode() {
        return Objects.hash(this.requestMethod, this.requestUrl, this.requestSize, this.status, this.responseSize, this.userAgent, this.serverIp, this.cacheLookup, this.cacheFillBytes, this.remoteIp, this.referer, this.cacheHit, this.cacheValidatedWithOriginServer, this.latency);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("requestMethod", this.requestMethod).add("requestUrl", this.requestUrl).add("requestSize", this.requestSize).add("status", this.status).add("responseSize", this.responseSize).add("userAgent", this.userAgent).add("remoteIp", this.remoteIp).add("serverIp", this.serverIp).add("referer", this.referer).add("cacheLookup", this.cacheLookup).add("cacheHit", this.cacheHit).add("cacheValidatedWithOriginServer", this.cacheValidatedWithOriginServer).add("cacheFillBytes", this.cacheFillBytes).add("latency", this.latency).toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HttpRequest)) {
            return false;
        }
        HttpRequest other = (HttpRequest)obj;
        return Objects.equals(this.requestMethod, other.requestMethod) && Objects.equals(this.requestUrl, other.requestUrl) && Objects.equals(this.requestSize, other.requestSize) && Objects.equals(this.status, other.status) && Objects.equals(this.responseSize, other.responseSize) && Objects.equals(this.userAgent, other.userAgent) && Objects.equals(this.remoteIp, other.remoteIp) && Objects.equals(this.serverIp, other.serverIp) && Objects.equals(this.referer, other.referer) && Objects.equals(this.latency, other.latency) && this.cacheLookup == other.cacheLookup && this.cacheHit == other.cacheHit && this.cacheValidatedWithOriginServer == other.cacheValidatedWithOriginServer && Objects.equals(this.cacheFillBytes, other.cacheFillBytes);
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    com.google.logging.type.HttpRequest toPb() {
        HttpRequest.Builder builder = com.google.logging.type.HttpRequest.newBuilder();
        if (this.requestMethod != null) {
            builder.setRequestMethod(this.requestMethod.name());
        }
        if (this.requestUrl != null) {
            builder.setRequestUrl(this.requestUrl);
        }
        if (this.requestSize != null) {
            builder.setRequestSize(this.requestSize);
        }
        if (this.status != null) {
            builder.setStatus(this.status);
        }
        if (this.responseSize != null) {
            builder.setResponseSize(this.responseSize);
        }
        if (this.userAgent != null) {
            builder.setUserAgent(this.userAgent);
        }
        if (this.remoteIp != null) {
            builder.setRemoteIp(this.remoteIp);
        }
        if (this.serverIp != null) {
            builder.setServerIp(this.serverIp);
        }
        if (this.referer != null) {
            builder.setReferer(this.referer);
        }
        builder.setCacheLookup(this.cacheLookup);
        builder.setCacheHit(this.cacheHit);
        builder.setCacheValidatedWithOriginServer(this.cacheValidatedWithOriginServer);
        if (this.cacheFillBytes != null) {
            builder.setCacheFillBytes(this.cacheFillBytes);
        }
        if (this.latency != null) {
            builder.setLatency(com.google.protobuf.Duration.newBuilder().setSeconds(this.latency.getSeconds()).setNanos(this.latency.getNano()).build());
        }
        return builder.build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    static HttpRequest fromPb(com.google.logging.type.HttpRequest requestPb) {
        Builder builder = HttpRequest.newBuilder();
        if (!Strings.isNullOrEmpty(requestPb.getRequestMethod())) {
            builder.setRequestMethod(RequestMethod.valueOf(requestPb.getRequestMethod()));
        }
        if (!Strings.isNullOrEmpty(requestPb.getRequestUrl())) {
            builder.setRequestUrl(requestPb.getRequestUrl());
        }
        if (requestPb.getRequestSize() != 0L) {
            builder.setRequestSize(requestPb.getRequestSize());
        }
        if ((long)requestPb.getStatus() != 0L) {
            builder.setStatus(requestPb.getStatus());
        }
        if (requestPb.getResponseSize() != 0L) {
            builder.setResponseSize(requestPb.getResponseSize());
        }
        if (!Strings.isNullOrEmpty(requestPb.getUserAgent())) {
            builder.setUserAgent(requestPb.getUserAgent());
        }
        if (!Strings.isNullOrEmpty(requestPb.getServerIp())) {
            builder.setServerIp(requestPb.getServerIp());
        }
        if (!Strings.isNullOrEmpty(requestPb.getRemoteIp())) {
            builder.setRemoteIp(requestPb.getRemoteIp());
        }
        if (!Strings.isNullOrEmpty(requestPb.getReferer())) {
            builder.setReferer(requestPb.getReferer());
        }
        builder.setCacheLookup(requestPb.getCacheLookup());
        builder.setCacheHit(requestPb.getCacheHit());
        builder.setCacheValidatedWithOriginServer(requestPb.getCacheValidatedWithOriginServer());
        if (requestPb.getCacheFillBytes() != 0L) {
            builder.setCacheFillBytes(requestPb.getCacheFillBytes());
        }
        if (requestPb.hasLatency()) {
            builder.setLatency(Duration.ofSeconds(requestPb.getLatency().getSeconds(), requestPb.getLatency().getNanos()));
        }
        return builder.build();
    }

    public static final class Builder {
        private RequestMethod requestMethod;
        private String requestUrl;
        private Long requestSize;
        private Integer status;
        private Long responseSize;
        private String userAgent;
        private String remoteIp;
        private String serverIp;
        private String referer;
        private boolean cacheLookup;
        private boolean cacheHit;
        private boolean cacheValidatedWithOriginServer;
        private Long cacheFillBytes;
        private Duration latency;

        Builder() {
        }

        Builder(HttpRequest request) {
            this.requestMethod = request.requestMethod;
            this.requestUrl = request.requestUrl;
            this.requestSize = request.requestSize;
            this.status = request.status;
            this.responseSize = request.responseSize;
            this.userAgent = request.userAgent;
            this.remoteIp = request.remoteIp;
            this.serverIp = request.serverIp;
            this.referer = request.referer;
            this.cacheLookup = request.cacheLookup;
            this.cacheHit = request.cacheHit;
            this.cacheValidatedWithOriginServer = request.cacheValidatedWithOriginServer;
            this.cacheFillBytes = request.cacheFillBytes;
            this.latency = request.latency;
        }

        public Builder setRequestMethod(RequestMethod requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public Builder setRequestUrl(String requestUrl) {
            this.requestUrl = requestUrl;
            return this;
        }

        public Builder setRequestSize(long requestSize) {
            this.requestSize = requestSize;
            return this;
        }

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder setResponseSize(long responseSize) {
            this.responseSize = responseSize;
            return this;
        }

        public Builder setUserAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public Builder setRemoteIp(String remoteIp) {
            this.remoteIp = remoteIp;
            return this;
        }

        public Builder setServerIp(String serverIp) {
            this.serverIp = serverIp;
            return this;
        }

        public Builder setReferer(String referer) {
            this.referer = referer;
            return this;
        }

        public Builder setCacheLookup(boolean cacheLookup) {
            this.cacheLookup = cacheLookup;
            return this;
        }

        public Builder setCacheHit(boolean cacheHit) {
            this.cacheHit = cacheHit;
            return this;
        }

        public Builder setCacheValidatedWithOriginServer(boolean cacheValidatedWithOriginServer) {
            this.cacheValidatedWithOriginServer = cacheValidatedWithOriginServer;
            return this;
        }

        public Builder setCacheFillBytes(long cacheFillBytes) {
            this.cacheFillBytes = cacheFillBytes;
            return this;
        }

        public Builder setLatency(Duration latency) {
            this.latency = latency;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }

    public static final class RequestMethod
    extends StringEnumValue {
        private static final long serialVersionUID = 2403969065179486996L;
        private static final ApiFunction<String, RequestMethod> CONSTRUCTOR = new ApiFunction<String, RequestMethod>(){

            @Override
            public RequestMethod apply(String constant) {
                return new RequestMethod(constant);
            }
        };
        private static final StringEnumType<RequestMethod> type = new StringEnumType<RequestMethod>(RequestMethod.class, CONSTRUCTOR);
        public static final RequestMethod GET = type.createAndRegister("GET");
        public static final RequestMethod HEAD = type.createAndRegister("HEAD");
        public static final RequestMethod PUT = type.createAndRegister("PUT");
        public static final RequestMethod POST = type.createAndRegister("POST");

        private RequestMethod(String constant) {
            super(constant);
        }

        public static RequestMethod valueOfStrict(String constant) {
            return type.valueOfStrict(constant);
        }

        public static RequestMethod valueOf(String constant) {
            return type.valueOf(constant);
        }

        public static RequestMethod[] values() {
            return type.values();
        }
    }
}

