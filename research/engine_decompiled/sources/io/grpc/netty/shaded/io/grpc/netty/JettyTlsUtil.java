/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

final class JettyTlsUtil {
    private static Throwable jettyAlpnUnavailabilityCause;
    private static Throwable jettyNpnUnavailabilityCause;

    private JettyTlsUtil() {
    }

    static synchronized boolean isJettyAlpnConfigured() {
        try {
            Class.forName("org.eclipse.jetty.alpn.ALPN", true, null);
            return true;
        }
        catch (ClassNotFoundException e) {
            jettyAlpnUnavailabilityCause = e;
            return false;
        }
    }

    static synchronized Throwable getJettyAlpnUnavailabilityCause() {
        if (jettyAlpnUnavailabilityCause == null) {
            boolean bl = JettyTlsUtil.isJettyAlpnConfigured();
        }
        return jettyAlpnUnavailabilityCause;
    }

    static synchronized boolean isJettyNpnConfigured() {
        try {
            Class.forName("org.eclipse.jetty.npn.NextProtoNego", true, null);
            return true;
        }
        catch (ClassNotFoundException e) {
            jettyNpnUnavailabilityCause = e;
            return false;
        }
    }

    static synchronized Throwable getJettyNpnUnavailabilityCause() {
        if (jettyNpnUnavailabilityCause == null) {
            boolean bl = JettyTlsUtil.isJettyNpnConfigured();
        }
        return jettyNpnUnavailabilityCause;
    }

    static boolean isJava9AlpnAvailable() {
        return JettyTlsUtil.getJava9AlpnUnavailabilityCause() == null;
    }

    static Throwable getJava9AlpnUnavailabilityCause() {
        return Java9AlpnUnavailabilityCauseHolder.cause;
    }

    private static class Java9AlpnUnavailabilityCauseHolder {
        static final Throwable cause = Java9AlpnUnavailabilityCauseHolder.checkAlpnAvailability();

        private Java9AlpnUnavailabilityCauseHolder() {
        }

        static Throwable checkAlpnAvailability() {
            try {
                SSLContext context = SSLContext.getInstance("TLS");
                context.init(null, null, null);
                SSLEngine engine = context.createSSLEngine();
                Method getApplicationProtocol = AccessController.doPrivileged(new PrivilegedExceptionAction<Method>(){

                    @Override
                    public Method run() throws Exception {
                        return SSLEngine.class.getMethod("getApplicationProtocol", new Class[0]);
                    }
                });
                getApplicationProtocol.invoke((Object)engine, new Object[0]);
                return null;
            }
            catch (Throwable t) {
                return t;
            }
        }
    }
}

