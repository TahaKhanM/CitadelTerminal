/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.X509TrustManager;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class SecurityUtils {
    public static KeyStore getDefaultKeyStore() throws KeyStoreException {
        return KeyStore.getInstance(KeyStore.getDefaultType());
    }

    public static KeyStore getJavaKeyStore() throws KeyStoreException {
        return KeyStore.getInstance("JKS");
    }

    public static KeyStore getPkcs12KeyStore() throws KeyStoreException {
        return KeyStore.getInstance("PKCS12");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void loadKeyStore(KeyStore keyStore, InputStream keyStream, String storePass) throws IOException, GeneralSecurityException {
        try {
            keyStore.load(keyStream, storePass.toCharArray());
        }
        finally {
            keyStream.close();
        }
    }

    public static PrivateKey getPrivateKey(KeyStore keyStore, String alias, String keyPass) throws GeneralSecurityException {
        return (PrivateKey)keyStore.getKey(alias, keyPass.toCharArray());
    }

    public static PrivateKey loadPrivateKeyFromKeyStore(KeyStore keyStore, InputStream keyStream, String storePass, String alias, String keyPass) throws IOException, GeneralSecurityException {
        SecurityUtils.loadKeyStore(keyStore, keyStream, storePass);
        return SecurityUtils.getPrivateKey(keyStore, alias, keyPass);
    }

    public static KeyFactory getRsaKeyFactory() throws NoSuchAlgorithmException {
        return KeyFactory.getInstance("RSA");
    }

    public static Signature getSha1WithRsaSignatureAlgorithm() throws NoSuchAlgorithmException {
        return Signature.getInstance("SHA1withRSA");
    }

    public static Signature getSha256WithRsaSignatureAlgorithm() throws NoSuchAlgorithmException {
        return Signature.getInstance("SHA256withRSA");
    }

    public static byte[] sign(Signature signatureAlgorithm, PrivateKey privateKey, byte[] contentBytes) throws InvalidKeyException, SignatureException {
        signatureAlgorithm.initSign(privateKey);
        signatureAlgorithm.update(contentBytes);
        return signatureAlgorithm.sign();
    }

    public static boolean verify(Signature signatureAlgorithm, PublicKey publicKey, byte[] signatureBytes, byte[] contentBytes) throws InvalidKeyException, SignatureException {
        signatureAlgorithm.initVerify(publicKey);
        signatureAlgorithm.update(contentBytes);
        try {
            return signatureAlgorithm.verify(signatureBytes);
        }
        catch (SignatureException e) {
            return false;
        }
    }

    public static X509Certificate verify(Signature signatureAlgorithm, X509TrustManager trustManager, List<String> certChainBase64, byte[] signatureBytes, byte[] contentBytes) throws InvalidKeyException, SignatureException {
        CertificateFactory certificateFactory;
        try {
            certificateFactory = SecurityUtils.getX509CertificateFactory();
        }
        catch (CertificateException e) {
            return null;
        }
        X509Certificate[] certificates = new X509Certificate[certChainBase64.size()];
        int currentCert = 0;
        for (String certBase64 : certChainBase64) {
            byte[] certDer = Base64.decodeBase64(certBase64);
            ByteArrayInputStream bis = new ByteArrayInputStream(certDer);
            try {
                Certificate cert = certificateFactory.generateCertificate(bis);
                if (!(cert instanceof X509Certificate)) {
                    return null;
                }
                certificates[currentCert++] = (X509Certificate)cert;
            }
            catch (CertificateException e) {
                return null;
            }
        }
        try {
            trustManager.checkServerTrusted(certificates, "RSA");
        }
        catch (CertificateException e) {
            return null;
        }
        PublicKey pubKey = certificates[0].getPublicKey();
        if (SecurityUtils.verify(signatureAlgorithm, pubKey, signatureBytes, contentBytes)) {
            return certificates[0];
        }
        return null;
    }

    public static CertificateFactory getX509CertificateFactory() throws CertificateException {
        return CertificateFactory.getInstance("X.509");
    }

    public static void loadKeyStoreFromCertificates(KeyStore keyStore, CertificateFactory certificateFactory, InputStream certificateStream) throws GeneralSecurityException {
        int i = 0;
        for (Certificate certificate : certificateFactory.generateCertificates(certificateStream)) {
            keyStore.setCertificateEntry(String.valueOf(i), certificate);
            ++i;
        }
    }

    private SecurityUtils() {
    }
}

