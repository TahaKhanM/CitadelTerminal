/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.ProxyDetector;
import io.grpc.internal.ProxyParameters;
import java.io.IOException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

class ProxyDetectorImpl
implements ProxyDetector {
    private static final Logger log = Logger.getLogger(ProxyDetectorImpl.class.getName());
    private static final AuthenticationProvider DEFAULT_AUTHENTICATOR = new AuthenticationProvider(){

        @Override
        public PasswordAuthentication requestPasswordAuthentication(String host, InetAddress addr, int port, String protocol, String prompt, String scheme) {
            URL url = null;
            try {
                url = new URL(protocol, host, port, "");
            }
            catch (MalformedURLException e) {
                log.log(Level.WARNING, String.format("failed to create URL for Authenticator: %s %s", protocol, host));
            }
            return Authenticator.requestPasswordAuthentication(host, addr, port, protocol, prompt, scheme, url, Authenticator.RequestorType.PROXY);
        }
    };
    private static final Supplier<ProxySelector> DEFAULT_PROXY_SELECTOR = new Supplier<ProxySelector>(){

        @Override
        public ProxySelector get() {
            return ProxySelector.getDefault();
        }
    };
    @Deprecated
    private static final String GRPC_PROXY_ENV_VAR = "GRPC_PROXY_EXP";
    private final Supplier<ProxySelector> proxySelector;
    private final AuthenticationProvider authenticationProvider;
    private final ProxyParameters override;
    static final String PROXY_SCHEME = "https";

    public ProxyDetectorImpl() {
        this(DEFAULT_PROXY_SELECTOR, DEFAULT_AUTHENTICATOR, System.getenv(GRPC_PROXY_ENV_VAR));
    }

    @VisibleForTesting
    ProxyDetectorImpl(Supplier<ProxySelector> proxySelector, AuthenticationProvider authenticationProvider, @Nullable String proxyEnvString) {
        this.proxySelector = Preconditions.checkNotNull(proxySelector);
        this.authenticationProvider = Preconditions.checkNotNull(authenticationProvider);
        this.override = proxyEnvString != null ? new ProxyParameters(ProxyDetectorImpl.overrideProxy(proxyEnvString), null, null) : null;
    }

    @Override
    @Nullable
    public ProxyParameters proxyFor(SocketAddress targetServerAddress) throws IOException {
        if (this.override != null) {
            return this.override;
        }
        if (!(targetServerAddress instanceof InetSocketAddress)) {
            return null;
        }
        return this.detectProxy((InetSocketAddress)targetServerAddress);
    }

    private ProxyParameters detectProxy(InetSocketAddress targetAddr) throws IOException {
        InetSocketAddress resolvedProxyAddr;
        Proxy proxy;
        URI uri;
        String host;
        try {
            host = GrpcUtil.getHost(targetAddr);
        }
        catch (Throwable t) {
            log.log(Level.WARNING, "Failed to get host for proxy lookup, proceeding without proxy", t);
            return null;
        }
        try {
            uri = new URI(PROXY_SCHEME, null, host, targetAddr.getPort(), null, null, null);
        }
        catch (URISyntaxException e) {
            log.log(Level.WARNING, "Failed to construct URI for proxy lookup, proceeding without proxy", e);
            return null;
        }
        List<Proxy> proxies = this.proxySelector.get().select(uri);
        if (proxies.size() > 1) {
            log.warning("More than 1 proxy detected, gRPC will select the first one");
        }
        if ((proxy = proxies.get(0)).type() == Proxy.Type.DIRECT) {
            return null;
        }
        InetSocketAddress proxyAddr = (InetSocketAddress)proxy.address();
        String promptString = "";
        PasswordAuthentication auth = this.authenticationProvider.requestPasswordAuthentication(GrpcUtil.getHost(proxyAddr), proxyAddr.getAddress(), proxyAddr.getPort(), PROXY_SCHEME, promptString, null);
        if (proxyAddr.isUnresolved()) {
            InetAddress resolvedAddress = InetAddress.getByName(proxyAddr.getHostName());
            resolvedProxyAddr = new InetSocketAddress(resolvedAddress, proxyAddr.getPort());
        } else {
            resolvedProxyAddr = proxyAddr;
        }
        if (auth == null) {
            return new ProxyParameters(resolvedProxyAddr, null, null);
        }
        return new ProxyParameters(resolvedProxyAddr, auth.getUserName(), new String(auth.getPassword()));
    }

    private static InetSocketAddress overrideProxy(String proxyHostPort) {
        if (proxyHostPort == null) {
            return null;
        }
        String[] parts = proxyHostPort.split(":", 2);
        int port = 80;
        if (parts.length > 1) {
            port = Integer.parseInt(parts[1]);
        }
        log.warning("Detected GRPC_PROXY_EXP and will honor it, but this feature will be removed in a future release. Use the JVM flags \"-Dhttps.proxyHost=HOST -Dhttps.proxyPort=PORT\" to set the https proxy for this JVM.");
        return new InetSocketAddress(parts[0], port);
    }

    static interface AuthenticationProvider {
        public PasswordAuthentication requestPasswordAuthentication(String var1, InetAddress var2, int var3, String var4, String var5, String var6);
    }
}

