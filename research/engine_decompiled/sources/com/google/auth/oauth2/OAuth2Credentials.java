/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth.oauth2;

import com.google.api.client.util.Clock;
import com.google.auth.Credentials;
import com.google.auth.RequestMetadataCallback;
import com.google.auth.oauth2.AccessToken;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.concurrent.Executor;

public class OAuth2Credentials
extends Credentials {
    private static final long serialVersionUID = 4556936364828217687L;
    private static final long MINIMUM_TOKEN_MILLISECONDS = 300000L;
    private final Object lock = new byte[0];
    private Map<String, List<String>> requestMetadata;
    private AccessToken temporaryAccess;
    private transient List<CredentialsChangedListener> changeListeners;
    @VisibleForTesting
    transient Clock clock = Clock.SYSTEM;

    @Deprecated
    public static OAuth2Credentials of(AccessToken accessToken) {
        return OAuth2Credentials.create(accessToken);
    }

    public static OAuth2Credentials create(AccessToken accessToken) {
        return OAuth2Credentials.newBuilder().setAccessToken(accessToken).build();
    }

    protected OAuth2Credentials() {
        this(null);
    }

    @Deprecated
    public OAuth2Credentials(AccessToken accessToken) {
        if (accessToken != null) {
            this.useAccessToken(accessToken);
        }
    }

    @Override
    public String getAuthenticationType() {
        return "OAuth2";
    }

    @Override
    public boolean hasRequestMetadata() {
        return true;
    }

    @Override
    public boolean hasRequestMetadataOnly() {
        return true;
    }

    public final AccessToken getAccessToken() {
        return this.temporaryAccess;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void getRequestMetadata(URI uri, Executor executor, RequestMetadataCallback callback) {
        Map<String, List<String>> metadata;
        Object object = this.lock;
        synchronized (object) {
            if (this.shouldRefresh()) {
                super.getRequestMetadata(uri, executor, callback);
                return;
            }
            metadata = Preconditions.checkNotNull(this.requestMetadata, "cached requestMetadata");
        }
        callback.onSuccess(metadata);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Map<String, List<String>> getRequestMetadata(URI uri) throws IOException {
        Object object = this.lock;
        synchronized (object) {
            if (this.shouldRefresh()) {
                this.refresh();
            }
            return Preconditions.checkNotNull(this.requestMetadata, "requestMetadata");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void refresh() throws IOException {
        Object object = this.lock;
        synchronized (object) {
            this.requestMetadata = null;
            this.temporaryAccess = null;
            this.useAccessToken(Preconditions.checkNotNull(this.refreshAccessToken(), "new access token"));
            if (this.changeListeners != null) {
                for (CredentialsChangedListener listener : this.changeListeners) {
                    listener.onChanged(this);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void refreshIfExpired() throws IOException {
        Object object = this.lock;
        synchronized (object) {
            if (this.shouldRefresh()) {
                this.refresh();
            }
        }
    }

    private void useAccessToken(AccessToken token) {
        this.temporaryAccess = token;
        this.requestMetadata = Collections.singletonMap("Authorization", Collections.singletonList("Bearer " + token.getTokenValue()));
    }

    private boolean shouldRefresh() {
        Long expiresIn = this.getExpiresInMilliseconds();
        return this.requestMetadata == null || expiresIn != null && expiresIn <= 300000L;
    }

    public AccessToken refreshAccessToken() throws IOException {
        throw new IllegalStateException("OAuth2Credentials instance does not support refreshing the access token. An instance with a new access token should be used, or a derived type that supports refreshing.");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void addChangeListener(CredentialsChangedListener listener) {
        Object object = this.lock;
        synchronized (object) {
            if (this.changeListeners == null) {
                this.changeListeners = new ArrayList<CredentialsChangedListener>();
            }
            this.changeListeners.add(listener);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void removeChangeListener(CredentialsChangedListener listener) {
        Object object = this.lock;
        synchronized (object) {
            if (this.changeListeners != null) {
                this.changeListeners.remove(listener);
            }
        }
    }

    private Long getExpiresInMilliseconds() {
        if (this.temporaryAccess == null) {
            return null;
        }
        Date expirationTime = this.temporaryAccess.getExpirationTime();
        if (expirationTime == null) {
            return null;
        }
        return expirationTime.getTime() - this.clock.currentTimeMillis();
    }

    public int hashCode() {
        return Objects.hash(this.requestMetadata, this.temporaryAccess);
    }

    protected Map<String, List<String>> getRequestMetadataInternal() {
        return this.requestMetadata;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("requestMetadata", this.requestMetadata).add("temporaryAccess", this.temporaryAccess).toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof OAuth2Credentials)) {
            return false;
        }
        OAuth2Credentials other = (OAuth2Credentials)obj;
        return Objects.equals(this.requestMetadata, other.requestMetadata) && Objects.equals(this.temporaryAccess, other.temporaryAccess);
    }

    private void readObject(ObjectInputStream input2) throws IOException, ClassNotFoundException {
        input2.defaultReadObject();
        this.clock = Clock.SYSTEM;
    }

    protected static <T> T newInstance(String className) throws IOException, ClassNotFoundException {
        try {
            return (T)Class.forName(className).newInstance();
        }
        catch (IllegalAccessException | InstantiationException e) {
            throw new IOException(e);
        }
    }

    protected static <T> T getFromServiceLoader(Class<? extends T> clazz, T defaultInstance) {
        return Iterables.getFirst(ServiceLoader.load(clazz), defaultInstance);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private AccessToken accessToken;

        protected Builder() {
        }

        protected Builder(OAuth2Credentials credentials) {
            this.accessToken = credentials.getAccessToken();
        }

        public Builder setAccessToken(AccessToken token) {
            this.accessToken = token;
            return this;
        }

        public AccessToken getAccessToken() {
            return this.accessToken;
        }

        public OAuth2Credentials build() {
            return new OAuth2Credentials(this.accessToken);
        }
    }

    public static interface CredentialsChangedListener {
        public void onChanged(OAuth2Credentials var1) throws IOException;
    }
}

