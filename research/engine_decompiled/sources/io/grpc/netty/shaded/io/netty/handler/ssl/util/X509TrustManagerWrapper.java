/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.handler.ssl.util;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

final class X509TrustManagerWrapper
extends X509ExtendedTrustManager {
    private final X509TrustManager delegate;

    X509TrustManagerWrapper(X509TrustManager delegate) {
        this.delegate = ObjectUtil.checkNotNull(delegate, "delegate");
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain2, String s2) throws CertificateException {
        this.delegate.checkClientTrusted(chain2, s2);
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain2, String s2, Socket socket) throws CertificateException {
        this.delegate.checkClientTrusted(chain2, s2);
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain2, String s2, SSLEngine sslEngine) throws CertificateException {
        this.delegate.checkClientTrusted(chain2, s2);
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain2, String s2) throws CertificateException {
        this.delegate.checkServerTrusted(chain2, s2);
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain2, String s2, Socket socket) throws CertificateException {
        this.delegate.checkServerTrusted(chain2, s2);
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain2, String s2, SSLEngine sslEngine) throws CertificateException {
        this.delegate.checkServerTrusted(chain2, s2);
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return this.delegate.getAcceptedIssuers();
    }
}

