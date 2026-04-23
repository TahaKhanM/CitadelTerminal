/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.GaxProperties;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import java.util.Map;

@BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
public class ApiClientHeaderProvider
implements HeaderProvider,
Serializable {
    private static final long serialVersionUID = -8876627296793342119L;
    private final Map<String, String> headers;

    protected ApiClientHeaderProvider(Builder builder) {
        ImmutableMap.Builder<String, String> headersBuilder = ImmutableMap.builder();
        if (builder.getApiClientHeaderKey() != null) {
            StringBuilder apiClientHeaderValue = new StringBuilder();
            ApiClientHeaderProvider.appendToken(apiClientHeaderValue, builder.getJvmToken());
            ApiClientHeaderProvider.appendToken(apiClientHeaderValue, builder.getClientLibToken());
            ApiClientHeaderProvider.appendToken(apiClientHeaderValue, builder.getGeneratedLibToken());
            ApiClientHeaderProvider.appendToken(apiClientHeaderValue, builder.getGeneratedRuntimeToken());
            ApiClientHeaderProvider.appendToken(apiClientHeaderValue, builder.getTransportToken());
            if (apiClientHeaderValue.length() > 0) {
                headersBuilder.put(builder.getApiClientHeaderKey(), apiClientHeaderValue.toString());
            }
        }
        if (builder.getResourceHeaderKey() != null && builder.getResourceToken() != null) {
            headersBuilder.put(builder.getResourceHeaderKey(), builder.getResourceToken());
        }
        this.headers = headersBuilder.build();
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    protected static void appendToken(StringBuilder sb, String token) {
        if (token != null) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(token);
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static String getDefaultApiClientHeaderKey() {
        return "x-goog-api-client";
    }

    public static String getDefaultResourceHeaderKey() {
        return "google-cloud-resource-prefix";
    }

    public static class Builder {
        private String apiClientHeaderKey = ApiClientHeaderProvider.getDefaultApiClientHeaderKey();
        private String jvmToken;
        private String clientLibToken;
        private String generatedLibToken;
        private String generatedRuntimeToken;
        private String transportToken;
        private String resourceHeaderKey;
        private String resourceToken;

        protected Builder() {
            this.setJvmToken(GaxProperties.getJavaVersion());
            this.clientLibToken = null;
            this.generatedLibToken = null;
            this.setClientRuntimeToken(GaxProperties.getGaxVersion());
            this.transportToken = null;
            this.resourceHeaderKey = ApiClientHeaderProvider.getDefaultResourceHeaderKey();
            this.resourceToken = null;
        }

        public String getApiClientHeaderKey() {
            return this.apiClientHeaderKey;
        }

        public Builder setApiClientHeaderKey(String apiClientHeaderKey) {
            this.apiClientHeaderKey = apiClientHeaderKey;
            return this;
        }

        public String getJvmToken() {
            return this.jvmToken;
        }

        public Builder setJvmToken(String version) {
            this.jvmToken = this.constructToken("gl-java", version);
            return this;
        }

        public String getClientLibToken() {
            return this.clientLibToken;
        }

        public Builder setClientLibToken(String name, String version) {
            this.clientLibToken = this.constructToken(name, version);
            return this;
        }

        public String getGeneratedLibToken() {
            return this.generatedLibToken;
        }

        public Builder setGeneratedLibToken(String name, String version) {
            this.generatedLibToken = this.constructToken(name, version);
            return this;
        }

        public String getGeneratedRuntimeToken() {
            return this.generatedRuntimeToken;
        }

        public Builder setClientRuntimeToken(String version) {
            this.generatedRuntimeToken = this.constructToken("gax", version);
            return this;
        }

        public String getTransportToken() {
            return this.transportToken;
        }

        public Builder setTransportToken(String name, String version) {
            this.transportToken = this.constructToken(name, version);
            return this;
        }

        public String getResourceHeaderKey() {
            return this.resourceHeaderKey;
        }

        public Builder setResourceHeaderKey(String resourceHeaderKey) {
            this.resourceHeaderKey = resourceHeaderKey;
            return this;
        }

        public String getResourceToken() {
            return this.resourceToken;
        }

        public Builder setResourceToken(String resourceToken) {
            this.resourceToken = resourceToken;
            return this;
        }

        private String constructToken(String name, String version) {
            if (version == null) {
                return null;
            }
            if (name == null) {
                throw new IllegalArgumentException("Token name cannot be null");
            }
            return name + '/' + version;
        }

        public ApiClientHeaderProvider build() {
            return new ApiClientHeaderProvider(this);
        }
    }
}

