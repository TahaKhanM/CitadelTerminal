/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth.oauth2;

import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

class AppEngineCredentials
extends GoogleCredentials
implements ServiceAccountSigner {
    private static final long serialVersionUID = -493219027336622194L;
    static final String APP_IDENTITY_SERVICE_FACTORY_CLASS = "com.google.appengine.api.appidentity.AppIdentityServiceFactory";
    static final String APP_IDENTITY_SERVICE_CLASS = "com.google.appengine.api.appidentity.AppIdentityService";
    static final String GET_ACCESS_TOKEN_RESULT_CLASS = "com.google.appengine.api.appidentity.AppIdentityService$GetAccessTokenResult";
    static final String SIGNING_RESULT_CLASS = "com.google.appengine.api.appidentity.AppIdentityService$SigningResult";
    private static final String GET_APP_IDENTITY_SERVICE_METHOD = "getAppIdentityService";
    private static final String GET_ACCESS_TOKEN_RESULT_METHOD = "getAccessToken";
    private static final String GET_ACCESS_TOKEN_METHOD = "getAccessToken";
    private static final String GET_EXPIRATION_TIME_METHOD = "getExpirationTime";
    private static final String GET_SERVICE_ACCOUNT_NAME_METHOD = "getServiceAccountName";
    private static final String SIGN_FOR_APP_METHOD = "signForApp";
    private static final String GET_SIGNATURE_METHOD = "getSignature";
    private final Collection<String> scopes;
    private final boolean scopesRequired;
    private transient Object appIdentityService;
    private transient Method getAccessToken;
    private transient Method getAccessTokenResult;
    private transient Method getExpirationTime;
    private transient Method signForApp;
    private transient Method getSignature;
    private transient String account;

    AppEngineCredentials(Collection<String> scopes) throws IOException {
        this.scopes = scopes == null ? ImmutableSet.of() : ImmutableList.copyOf(scopes);
        this.scopesRequired = this.scopes.isEmpty();
        this.init();
    }

    AppEngineCredentials(Collection<String> scopes, AppEngineCredentials unscoped) {
        this.appIdentityService = unscoped.appIdentityService;
        this.getAccessToken = unscoped.getAccessToken;
        this.getAccessTokenResult = unscoped.getAccessTokenResult;
        this.getExpirationTime = unscoped.getExpirationTime;
        this.scopes = scopes == null ? ImmutableSet.of() : ImmutableList.copyOf(scopes);
        this.scopesRequired = this.scopes.isEmpty();
    }

    private void init() throws IOException {
        try {
            Class<?> factoryClass = this.forName(APP_IDENTITY_SERVICE_FACTORY_CLASS);
            Method method = factoryClass.getMethod(GET_APP_IDENTITY_SERVICE_METHOD, new Class[0]);
            this.appIdentityService = method.invoke(null, new Object[0]);
            Class<?> serviceClass = this.forName(APP_IDENTITY_SERVICE_CLASS);
            Class<?> tokenResultClass = this.forName(GET_ACCESS_TOKEN_RESULT_CLASS);
            this.getAccessTokenResult = serviceClass.getMethod("getAccessToken", Iterable.class);
            this.getAccessToken = tokenResultClass.getMethod("getAccessToken", new Class[0]);
            this.getExpirationTime = tokenResultClass.getMethod(GET_EXPIRATION_TIME_METHOD, new Class[0]);
            this.account = (String)serviceClass.getMethod(GET_SERVICE_ACCOUNT_NAME_METHOD, new Class[0]).invoke(this.appIdentityService, new Object[0]);
            this.signForApp = serviceClass.getMethod(SIGN_FOR_APP_METHOD, byte[].class);
            Class<?> signingResultClass = this.forName(SIGNING_RESULT_CLASS);
            this.getSignature = signingResultClass.getMethod(GET_SIGNATURE_METHOD, new Class[0]);
        }
        catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            throw new IOException("Application Default Credentials failed to create the Google App Engine service account credentials. Check that the App Engine SDK is deployed.", ex);
        }
    }

    @Override
    public AccessToken refreshAccessToken() throws IOException {
        if (this.createScopedRequired()) {
            throw new IOException("AppEngineCredentials requires createScoped call before use.");
        }
        try {
            Object accessTokenResult = this.getAccessTokenResult.invoke(this.appIdentityService, this.scopes);
            String accessToken = (String)this.getAccessToken.invoke(accessTokenResult, new Object[0]);
            Date expirationTime = (Date)this.getExpirationTime.invoke(accessTokenResult, new Object[0]);
            return new AccessToken(accessToken, expirationTime);
        }
        catch (Exception e) {
            throw new IOException("Could not get the access token.", e);
        }
    }

    @Override
    public boolean createScopedRequired() {
        return this.scopesRequired;
    }

    @Override
    public GoogleCredentials createScoped(Collection<String> scopes) {
        return new AppEngineCredentials(scopes, this);
    }

    @Override
    public String getAccount() {
        return this.account;
    }

    @Override
    public byte[] sign(byte[] toSign) {
        try {
            Object signingResult = this.signForApp.invoke(this.appIdentityService, new Object[]{toSign});
            return (byte[])this.getSignature.invoke(signingResult, new Object[0]);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new ServiceAccountSigner.SigningException("Failed to sign the provided bytes", ex);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scopes, this.scopesRequired);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("scopes", this.scopes).add("scopesRequired", this.scopesRequired).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AppEngineCredentials)) {
            return false;
        }
        AppEngineCredentials other = (AppEngineCredentials)obj;
        return this.scopesRequired == other.scopesRequired && Objects.equals(this.scopes, other.scopes);
    }

    private void readObject(ObjectInputStream input2) throws IOException, ClassNotFoundException {
        input2.defaultReadObject();
        this.init();
    }

    Class<?> forName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}

