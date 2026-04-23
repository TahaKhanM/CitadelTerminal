/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.conn.ssl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.auth.x500.X500Principal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.ssl.SubjectName;
import org.apache.http.conn.util.DomainType;
import org.apache.http.conn.util.InetAddressUtils;
import org.apache.http.conn.util.PublicSuffixMatcher;

@Contract(threading=ThreadingBehavior.IMMUTABLE_CONDITIONAL)
public final class DefaultHostnameVerifier
implements HostnameVerifier {
    private final Log log = LogFactory.getLog(this.getClass());
    private final PublicSuffixMatcher publicSuffixMatcher;

    public DefaultHostnameVerifier(PublicSuffixMatcher publicSuffixMatcher) {
        this.publicSuffixMatcher = publicSuffixMatcher;
    }

    public DefaultHostnameVerifier() {
        this(null);
    }

    @Override
    public boolean verify(String host, SSLSession session) {
        try {
            Certificate[] certs = session.getPeerCertificates();
            X509Certificate x509 = (X509Certificate)certs[0];
            this.verify(host, x509);
            return true;
        }
        catch (SSLException ex) {
            if (this.log.isDebugEnabled()) {
                this.log.debug(ex.getMessage(), ex);
            }
            return false;
        }
    }

    public void verify(String host, X509Certificate cert) throws SSLException {
        HostNameType hostType = DefaultHostnameVerifier.determineHostFormat(host);
        List<SubjectName> subjectAlts = DefaultHostnameVerifier.getSubjectAltNames(cert);
        if (subjectAlts != null && !subjectAlts.isEmpty()) {
            switch (hostType) {
                case IPv4: {
                    DefaultHostnameVerifier.matchIPAddress(host, subjectAlts);
                    break;
                }
                case IPv6: {
                    DefaultHostnameVerifier.matchIPv6Address(host, subjectAlts);
                    break;
                }
                default: {
                    DefaultHostnameVerifier.matchDNSName(host, subjectAlts, this.publicSuffixMatcher);
                    break;
                }
            }
        } else {
            X500Principal subjectPrincipal = cert.getSubjectX500Principal();
            String cn = DefaultHostnameVerifier.extractCN(subjectPrincipal.getName("RFC2253"));
            if (cn == null) {
                throw new SSLException("Certificate subject for <" + host + "> doesn't contain " + "a common name and does not have alternative names");
            }
            DefaultHostnameVerifier.matchCN(host, cn, this.publicSuffixMatcher);
        }
    }

    static void matchIPAddress(String host, List<SubjectName> subjectAlts) throws SSLException {
        for (int i = 0; i < subjectAlts.size(); ++i) {
            SubjectName subjectAlt = subjectAlts.get(i);
            if (subjectAlt.getType() != 7 || !host.equals(subjectAlt.getValue())) continue;
            return;
        }
        throw new SSLPeerUnverifiedException("Certificate for <" + host + "> doesn't match any " + "of the subject alternative names: " + subjectAlts);
    }

    static void matchIPv6Address(String host, List<SubjectName> subjectAlts) throws SSLException {
        String normalisedHost = DefaultHostnameVerifier.normaliseAddress(host);
        for (int i = 0; i < subjectAlts.size(); ++i) {
            String normalizedSubjectAlt;
            SubjectName subjectAlt = subjectAlts.get(i);
            if (subjectAlt.getType() != 7 || !normalisedHost.equals(normalizedSubjectAlt = DefaultHostnameVerifier.normaliseAddress(subjectAlt.getValue()))) continue;
            return;
        }
        throw new SSLPeerUnverifiedException("Certificate for <" + host + "> doesn't match any " + "of the subject alternative names: " + subjectAlts);
    }

    static void matchDNSName(String host, List<SubjectName> subjectAlts, PublicSuffixMatcher publicSuffixMatcher) throws SSLException {
        String normalizedHost = host.toLowerCase(Locale.ROOT);
        for (int i = 0; i < subjectAlts.size(); ++i) {
            String normalizedSubjectAlt;
            SubjectName subjectAlt = subjectAlts.get(i);
            if (subjectAlt.getType() != 2 || !DefaultHostnameVerifier.matchIdentityStrict(normalizedHost, normalizedSubjectAlt = subjectAlt.getValue().toLowerCase(Locale.ROOT), publicSuffixMatcher)) continue;
            return;
        }
        throw new SSLPeerUnverifiedException("Certificate for <" + host + "> doesn't match any " + "of the subject alternative names: " + subjectAlts);
    }

    static void matchCN(String host, String cn, PublicSuffixMatcher publicSuffixMatcher) throws SSLException {
        String normalizedCn;
        String normalizedHost = host.toLowerCase(Locale.ROOT);
        if (!DefaultHostnameVerifier.matchIdentityStrict(normalizedHost, normalizedCn = cn.toLowerCase(Locale.ROOT), publicSuffixMatcher)) {
            throw new SSLPeerUnverifiedException("Certificate for <" + host + "> doesn't match " + "common name of the certificate subject: " + cn);
        }
    }

    static boolean matchDomainRoot(String host, String domainRoot) {
        if (domainRoot == null) {
            return false;
        }
        return host.endsWith(domainRoot) && (host.length() == domainRoot.length() || host.charAt(host.length() - domainRoot.length() - 1) == '.');
    }

    private static boolean matchIdentity(String host, String identity, PublicSuffixMatcher publicSuffixMatcher, boolean strict) {
        if (publicSuffixMatcher != null && host.contains(".") && !DefaultHostnameVerifier.matchDomainRoot(host, publicSuffixMatcher.getDomainRoot(identity, DomainType.ICANN))) {
            return false;
        }
        int asteriskIdx = identity.indexOf(42);
        if (asteriskIdx != -1) {
            String remainder;
            String prefix = identity.substring(0, asteriskIdx);
            String suffix = identity.substring(asteriskIdx + 1);
            if (!prefix.isEmpty() && !host.startsWith(prefix)) {
                return false;
            }
            if (!suffix.isEmpty() && !host.endsWith(suffix)) {
                return false;
            }
            return !strict || !(remainder = host.substring(prefix.length(), host.length() - suffix.length())).contains(".");
        }
        return host.equalsIgnoreCase(identity);
    }

    static boolean matchIdentity(String host, String identity, PublicSuffixMatcher publicSuffixMatcher) {
        return DefaultHostnameVerifier.matchIdentity(host, identity, publicSuffixMatcher, false);
    }

    static boolean matchIdentity(String host, String identity) {
        return DefaultHostnameVerifier.matchIdentity(host, identity, null, false);
    }

    static boolean matchIdentityStrict(String host, String identity, PublicSuffixMatcher publicSuffixMatcher) {
        return DefaultHostnameVerifier.matchIdentity(host, identity, publicSuffixMatcher, true);
    }

    static boolean matchIdentityStrict(String host, String identity) {
        return DefaultHostnameVerifier.matchIdentity(host, identity, null, true);
    }

    static String extractCN(String subjectPrincipal) throws SSLException {
        if (subjectPrincipal == null) {
            return null;
        }
        try {
            LdapName subjectDN = new LdapName(subjectPrincipal);
            List<Rdn> rdns = subjectDN.getRdns();
            for (int i = rdns.size() - 1; i >= 0; --i) {
                Rdn rds = rdns.get(i);
                Attributes attributes = rds.toAttributes();
                Attribute cn = attributes.get("cn");
                if (cn == null) continue;
                try {
                    Object value = cn.get();
                    if (value == null) continue;
                    return value.toString();
                }
                catch (NoSuchElementException ignore) {
                    continue;
                }
                catch (NamingException ignore) {
                    // empty catch block
                }
            }
            return null;
        }
        catch (InvalidNameException e) {
            throw new SSLException(subjectPrincipal + " is not a valid X500 distinguished name");
        }
    }

    static HostNameType determineHostFormat(String host) {
        if (InetAddressUtils.isIPv4Address(host)) {
            return HostNameType.IPv4;
        }
        String s2 = host;
        if (s2.startsWith("[") && s2.endsWith("]")) {
            s2 = host.substring(1, host.length() - 1);
        }
        if (InetAddressUtils.isIPv6Address(s2)) {
            return HostNameType.IPv6;
        }
        return HostNameType.DNS;
    }

    static List<SubjectName> getSubjectAltNames(X509Certificate cert) {
        try {
            Collection<List<?>> entries = cert.getSubjectAlternativeNames();
            if (entries == null) {
                return Collections.emptyList();
            }
            ArrayList<SubjectName> result2 = new ArrayList<SubjectName>();
            for (List<?> entry : entries) {
                Integer type = entry.size() >= 2 ? (Integer)entry.get(0) : null;
                if (type == null) continue;
                String s2 = (String)entry.get(1);
                result2.add(new SubjectName(s2, type));
            }
            return result2;
        }
        catch (CertificateParsingException ignore) {
            return Collections.emptyList();
        }
    }

    static String normaliseAddress(String hostname) {
        if (hostname == null) {
            return hostname;
        }
        try {
            InetAddress inetAddress = InetAddress.getByName(hostname);
            return inetAddress.getHostAddress();
        }
        catch (UnknownHostException unexpected) {
            return hostname;
        }
    }

    static enum HostNameType {
        IPv4(7),
        IPv6(7),
        DNS(2);

        final int subjectType;

        private HostNameType(int subjectType) {
            this.subjectType = subjectType;
        }
    }
}

