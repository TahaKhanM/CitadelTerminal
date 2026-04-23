/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  sun.security.x509.CertificateIssuerName
 */
package io.grpc.netty.shaded.io.netty.handler.ssl.util;

import io.grpc.netty.shaded.io.netty.handler.ssl.util.SelfSignedCertificate;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.Date;
import sun.security.x509.AlgorithmId;
import sun.security.x509.CertificateAlgorithmId;
import sun.security.x509.CertificateIssuerName;
import sun.security.x509.CertificateSerialNumber;
import sun.security.x509.CertificateSubjectName;
import sun.security.x509.CertificateValidity;
import sun.security.x509.CertificateVersion;
import sun.security.x509.CertificateX509Key;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

final class OpenJdkSelfSignedCertGenerator {
    static String[] generate(String fqdn, KeyPair keypair, SecureRandom random, Date notBefore, Date notAfter) throws Exception {
        PrivateKey key = keypair.getPrivate();
        X509CertInfo info2 = new X509CertInfo();
        X500Name owner2 = new X500Name("CN=" + fqdn);
        info2.set("version", new CertificateVersion(2));
        info2.set("serialNumber", new CertificateSerialNumber(new BigInteger(64, random)));
        try {
            info2.set("subject", new CertificateSubjectName(owner2));
        }
        catch (CertificateException ignore) {
            info2.set("subject", owner2);
        }
        try {
            info2.set("issuer", new CertificateIssuerName(owner2));
        }
        catch (CertificateException ignore) {
            info2.set("issuer", owner2);
        }
        info2.set("validity", new CertificateValidity(notBefore, notAfter));
        info2.set("key", new CertificateX509Key(keypair.getPublic()));
        info2.set("algorithmID", new CertificateAlgorithmId(new AlgorithmId(AlgorithmId.sha1WithRSAEncryption_oid)));
        X509CertImpl cert = new X509CertImpl(info2);
        cert.sign(key, "SHA1withRSA");
        info2.set("algorithmID.algorithm", cert.get("x509.algorithm"));
        cert = new X509CertImpl(info2);
        cert.sign(key, "SHA1withRSA");
        cert.verify(keypair.getPublic());
        return SelfSignedCertificate.newSelfSignedCertificate(fqdn, key, cert);
    }

    private OpenJdkSelfSignedCertGenerator() {
    }
}

