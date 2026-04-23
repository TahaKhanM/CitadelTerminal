/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.auth;

import com.google.auth.Credentials;
import com.google.common.base.Preconditions;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ClientInterceptors;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.StatusException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

@Deprecated
public final class ClientAuthInterceptor
implements ClientInterceptor {
    private final Credentials credentials;
    private Metadata cached;
    private Map<String, List<String>> lastMetadata;

    public ClientAuthInterceptor(Credentials credentials, Executor executor) {
        this.credentials = Preconditions.checkNotNull(credentials, "credentials");
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(final MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, final Channel next2) {
        return new ClientInterceptors.CheckedForwardingClientCall<ReqT, RespT>(next2.newCall(method, callOptions)){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            protected void checkedStart(ClientCall.Listener<RespT> responseListener, Metadata headers) throws StatusException {
                Metadata cachedSaved;
                URI uri = ClientAuthInterceptor.this.serviceUri(next2, method);
                ClientAuthInterceptor clientAuthInterceptor = ClientAuthInterceptor.this;
                synchronized (clientAuthInterceptor) {
                    Map latestMetadata = ClientAuthInterceptor.this.getRequestMetadata(uri);
                    if (ClientAuthInterceptor.this.lastMetadata == null || ClientAuthInterceptor.this.lastMetadata != latestMetadata) {
                        ClientAuthInterceptor.this.lastMetadata = latestMetadata;
                        ClientAuthInterceptor.this.cached = ClientAuthInterceptor.toHeaders(ClientAuthInterceptor.this.lastMetadata);
                    }
                    cachedSaved = ClientAuthInterceptor.this.cached;
                }
                headers.merge(cachedSaved);
                this.delegate().start(responseListener, headers);
            }
        };
    }

    private URI serviceUri(Channel channel, MethodDescriptor<?, ?> method) throws StatusException {
        URI uri;
        String authority = channel.authority();
        if (authority == null) {
            throw Status.UNAUTHENTICATED.withDescription("Channel has no authority").asException();
        }
        String scheme = "https";
        int defaultPort = 443;
        String path = "/" + MethodDescriptor.extractFullServiceName(method.getFullMethodName());
        try {
            uri = new URI("https", authority, path, null, null);
        }
        catch (URISyntaxException e) {
            throw Status.UNAUTHENTICATED.withDescription("Unable to construct service URI for auth").withCause(e).asException();
        }
        if (uri.getPort() == 443) {
            uri = this.removePort(uri);
        }
        return uri;
    }

    private URI removePort(URI uri) throws StatusException {
        try {
            return new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), -1, uri.getPath(), uri.getQuery(), uri.getFragment());
        }
        catch (URISyntaxException e) {
            throw Status.UNAUTHENTICATED.withDescription("Unable to construct service URI after removing port").withCause(e).asException();
        }
    }

    private Map<String, List<String>> getRequestMetadata(URI uri) throws StatusException {
        try {
            return this.credentials.getRequestMetadata(uri);
        }
        catch (IOException e) {
            throw Status.UNAUTHENTICATED.withDescription("Unable to get request metadata").withCause(e).asException();
        }
    }

    private static final Metadata toHeaders(Map<String, List<String>> metadata) {
        Metadata headers = new Metadata();
        if (metadata != null) {
            for (String key : metadata.keySet()) {
                Metadata.Key<String> headerKey = Metadata.Key.of(key, Metadata.ASCII_STRING_MARSHALLER);
                for (String value : metadata.get(key)) {
                    headers.put(headerKey, value);
                }
            }
        }
        return headers;
    }
}

