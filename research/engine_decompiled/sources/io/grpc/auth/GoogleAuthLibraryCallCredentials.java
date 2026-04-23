/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.auth;

import com.google.auth.Credentials;
import com.google.auth.RequestMetadataCallback;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.Status;
import io.grpc.StatusException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.PrivateKey;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

final class GoogleAuthLibraryCallCredentials
implements CallCredentials {
    private static final Logger log = Logger.getLogger(GoogleAuthLibraryCallCredentials.class.getName());
    private static final JwtHelper jwtHelper = GoogleAuthLibraryCallCredentials.createJwtHelperOrNull(GoogleAuthLibraryCallCredentials.class.getClassLoader());
    private static final Class<? extends Credentials> googleCredentialsClass = GoogleAuthLibraryCallCredentials.loadGoogleCredentialsClass();
    private final boolean requirePrivacy;
    @VisibleForTesting
    final Credentials creds;
    private Metadata lastHeaders;
    private Map<String, List<String>> lastMetadata;

    public GoogleAuthLibraryCallCredentials(Credentials creds) {
        this(creds, jwtHelper);
    }

    @VisibleForTesting
    GoogleAuthLibraryCallCredentials(Credentials creds, JwtHelper jwtHelper) {
        Preconditions.checkNotNull(creds, "creds");
        boolean requirePrivacy = false;
        if (googleCredentialsClass != null) {
            requirePrivacy = googleCredentialsClass.isInstance(creds);
        }
        if (jwtHelper != null) {
            creds = jwtHelper.tryServiceAccountToJwt(creds);
        }
        this.requirePrivacy = requirePrivacy;
        this.creds = creds;
    }

    @Override
    public void thisUsesUnstableApi() {
    }

    @Override
    public void applyRequestMetadata(MethodDescriptor<?, ?> method, Attributes attrs, Executor appExecutor, final CallCredentials.MetadataApplier applier) {
        URI uri;
        SecurityLevel security = (SecurityLevel)((Object)attrs.get(ATTR_SECURITY_LEVEL));
        if (security == null) {
            security = SecurityLevel.NONE;
        }
        if (this.requirePrivacy && security != SecurityLevel.PRIVACY_AND_INTEGRITY) {
            applier.fail(Status.UNAUTHENTICATED.withDescription("Credentials require channel with PRIVACY_AND_INTEGRITY security level. Observed security level: " + (Object)((Object)security)));
            return;
        }
        String authority = (String)Preconditions.checkNotNull(attrs.get(ATTR_AUTHORITY), "authority");
        try {
            uri = GoogleAuthLibraryCallCredentials.serviceUri(authority, method);
        }
        catch (StatusException e) {
            applier.fail(e.getStatus());
            return;
        }
        this.creds.getRequestMetadata(uri, appExecutor, new RequestMetadataCallback(){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void onSuccess(Map<String, List<String>> metadata) {
                Metadata headers;
                try {
                    GoogleAuthLibraryCallCredentials googleAuthLibraryCallCredentials = GoogleAuthLibraryCallCredentials.this;
                    synchronized (googleAuthLibraryCallCredentials) {
                        if (GoogleAuthLibraryCallCredentials.this.lastMetadata == null || GoogleAuthLibraryCallCredentials.this.lastMetadata != metadata) {
                            GoogleAuthLibraryCallCredentials.this.lastHeaders = GoogleAuthLibraryCallCredentials.toHeaders(metadata);
                            GoogleAuthLibraryCallCredentials.this.lastMetadata = metadata;
                        }
                        headers = GoogleAuthLibraryCallCredentials.this.lastHeaders;
                    }
                }
                catch (Throwable t) {
                    applier.fail(Status.UNAUTHENTICATED.withDescription("Failed to convert credential metadata").withCause(t));
                    return;
                }
                applier.apply(headers);
            }

            @Override
            public void onFailure(Throwable e) {
                if (e instanceof IOException) {
                    applier.fail(Status.UNAVAILABLE.withDescription("Credentials failed to obtain metadata").withCause(e));
                } else {
                    applier.fail(Status.UNAUTHENTICATED.withDescription("Failed computing credential metadata").withCause(e));
                }
            }
        });
    }

    private static URI serviceUri(String authority, MethodDescriptor<?, ?> method) throws StatusException {
        URI uri;
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
            uri = GoogleAuthLibraryCallCredentials.removePort(uri);
        }
        return uri;
    }

    private static URI removePort(URI uri) throws StatusException {
        try {
            return new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), -1, uri.getPath(), uri.getQuery(), uri.getFragment());
        }
        catch (URISyntaxException e) {
            throw Status.UNAUTHENTICATED.withDescription("Unable to construct service URI after removing port").withCause(e).asException();
        }
    }

    private static Metadata toHeaders(@Nullable Map<String, List<String>> metadata) {
        Metadata headers = new Metadata();
        if (metadata != null) {
            for (String key : metadata.keySet()) {
                Metadata.Key<Object> headerKey;
                if (key.endsWith("-bin")) {
                    headerKey = Metadata.Key.of(key, Metadata.BINARY_BYTE_MARSHALLER);
                    for (String value : metadata.get(key)) {
                        headers.put(headerKey, BaseEncoding.base64().decode(value));
                    }
                    continue;
                }
                headerKey = Metadata.Key.of(key, Metadata.ASCII_STRING_MARSHALLER);
                for (String value : metadata.get(key)) {
                    headers.put(headerKey, value);
                }
            }
        }
        return headers;
    }

    @Nullable
    @VisibleForTesting
    static JwtHelper createJwtHelperOrNull(ClassLoader loader) {
        ReflectiveOperationException caughtException;
        Class<?> rawServiceAccountClass;
        try {
            rawServiceAccountClass = Class.forName("com.google.auth.oauth2.ServiceAccountCredentials", false, loader);
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
        try {
            return new JwtHelper(rawServiceAccountClass, loader);
        }
        catch (ClassNotFoundException ex) {
            caughtException = ex;
        }
        catch (NoSuchMethodException ex) {
            caughtException = ex;
        }
        if (caughtException != null) {
            log.log(Level.WARNING, "Failed to create JWT helper. This is unexpected", caughtException);
        }
        return null;
    }

    @Nullable
    private static Class<? extends Credentials> loadGoogleCredentialsClass() {
        Class<?> rawGoogleCredentialsClass;
        try {
            rawGoogleCredentialsClass = Class.forName("com.google.auth.oauth2.GoogleCredentials");
        }
        catch (ClassNotFoundException ex) {
            log.log(Level.FINE, "Failed to load GoogleCredentials", ex);
            return null;
        }
        return rawGoogleCredentialsClass.asSubclass(Credentials.class);
    }

    @VisibleForTesting
    static class JwtHelper {
        private final Class<? extends Credentials> serviceAccountClass;
        private final Constructor<? extends Credentials> jwtConstructor;
        private final Method getScopes;
        private final Method getClientId;
        private final Method getClientEmail;
        private final Method getPrivateKey;
        private final Method getPrivateKeyId;

        public JwtHelper(Class<?> rawServiceAccountClass, ClassLoader loader) throws ClassNotFoundException, NoSuchMethodException {
            this.serviceAccountClass = rawServiceAccountClass.asSubclass(Credentials.class);
            this.getScopes = this.serviceAccountClass.getMethod("getScopes", new Class[0]);
            this.getClientId = this.serviceAccountClass.getMethod("getClientId", new Class[0]);
            this.getClientEmail = this.serviceAccountClass.getMethod("getClientEmail", new Class[0]);
            this.getPrivateKey = this.serviceAccountClass.getMethod("getPrivateKey", new Class[0]);
            this.getPrivateKeyId = this.serviceAccountClass.getMethod("getPrivateKeyId", new Class[0]);
            Class<Credentials> jwtClass = Class.forName("com.google.auth.oauth2.ServiceAccountJwtAccessCredentials", false, loader).asSubclass(Credentials.class);
            this.jwtConstructor = jwtClass.getConstructor(String.class, String.class, PrivateKey.class, String.class);
        }

        public Credentials tryServiceAccountToJwt(Credentials creds) {
            ReflectiveOperationException caughtException;
            if (!this.serviceAccountClass.isInstance(creds)) {
                return creds;
            }
            try {
                creds = this.serviceAccountClass.cast(creds);
                Collection scopes = (Collection)this.getScopes.invoke((Object)creds, new Object[0]);
                if (scopes.size() != 0) {
                    return creds;
                }
                return this.jwtConstructor.newInstance(this.getClientId.invoke((Object)creds, new Object[0]), this.getClientEmail.invoke((Object)creds, new Object[0]), this.getPrivateKey.invoke((Object)creds, new Object[0]), this.getPrivateKeyId.invoke((Object)creds, new Object[0]));
            }
            catch (IllegalAccessException ex) {
                caughtException = ex;
            }
            catch (InvocationTargetException ex) {
                caughtException = ex;
            }
            catch (InstantiationException ex) {
                caughtException = ex;
            }
            if (caughtException != null) {
                log.log(Level.WARNING, "Failed converting service account credential to JWT. This is unexpected", caughtException);
            }
            return creds;
        }
    }
}

