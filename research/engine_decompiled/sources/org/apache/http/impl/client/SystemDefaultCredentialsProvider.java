/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.impl.client;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.SAFE)
public class SystemDefaultCredentialsProvider
implements CredentialsProvider {
    private static final Map<String, String> SCHEME_MAP = new ConcurrentHashMap<String, String>();
    private final BasicCredentialsProvider internal = new BasicCredentialsProvider();

    private static String translateScheme(String key) {
        if (key == null) {
            return null;
        }
        String s2 = SCHEME_MAP.get(key);
        return s2 != null ? s2 : key;
    }

    @Override
    public void setCredentials(AuthScope authscope, Credentials credentials) {
        this.internal.setCredentials(authscope, credentials);
    }

    private static PasswordAuthentication getSystemCreds(AuthScope authscope, Authenticator.RequestorType requestorType) {
        String hostname = authscope.getHost();
        int port = authscope.getPort();
        HttpHost origin = authscope.getOrigin();
        String protocol = origin != null ? origin.getSchemeName() : (port == 443 ? "https" : "http");
        return Authenticator.requestPasswordAuthentication(hostname, null, port, protocol, null, SystemDefaultCredentialsProvider.translateScheme(authscope.getScheme()), null, requestorType);
    }

    @Override
    public Credentials getCredentials(AuthScope authscope) {
        Args.notNull(authscope, "Auth scope");
        Credentials localcreds = this.internal.getCredentials(authscope);
        if (localcreds != null) {
            return localcreds;
        }
        String host = authscope.getHost();
        if (host != null) {
            String proxyPort;
            String proxyHost;
            PasswordAuthentication systemcreds = SystemDefaultCredentialsProvider.getSystemCreds(authscope, Authenticator.RequestorType.SERVER);
            if (systemcreds == null) {
                systemcreds = SystemDefaultCredentialsProvider.getSystemCreds(authscope, Authenticator.RequestorType.PROXY);
            }
            if (systemcreds == null && (proxyHost = System.getProperty("http.proxyHost")) != null && (proxyPort = System.getProperty("http.proxyPort")) != null) {
                try {
                    String proxyUser;
                    AuthScope systemScope = new AuthScope(proxyHost, Integer.parseInt(proxyPort));
                    if (authscope.match(systemScope) >= 0 && (proxyUser = System.getProperty("http.proxyUser")) != null) {
                        String proxyPassword = System.getProperty("http.proxyPassword");
                        systemcreds = new PasswordAuthentication(proxyUser, proxyPassword != null ? proxyPassword.toCharArray() : new char[]{});
                    }
                }
                catch (NumberFormatException ex) {
                    // empty catch block
                }
            }
            if (systemcreds != null) {
                String domain = System.getProperty("http.auth.ntlm.domain");
                if (domain != null) {
                    return new NTCredentials(systemcreds.getUserName(), new String(systemcreds.getPassword()), null, domain);
                }
                if ("NTLM".equalsIgnoreCase(authscope.getScheme())) {
                    return new NTCredentials(systemcreds.getUserName(), new String(systemcreds.getPassword()), null, null);
                }
                return new UsernamePasswordCredentials(systemcreds.getUserName(), new String(systemcreds.getPassword()));
            }
        }
        return null;
    }

    @Override
    public void clear() {
        this.internal.clear();
    }

    static {
        SCHEME_MAP.put("Basic".toUpperCase(Locale.ROOT), "Basic");
        SCHEME_MAP.put("Digest".toUpperCase(Locale.ROOT), "Digest");
        SCHEME_MAP.put("NTLM".toUpperCase(Locale.ROOT), "NTLM");
        SCHEME_MAP.put("Negotiate".toUpperCase(Locale.ROOT), "SPNEGO");
        SCHEME_MAP.put("Kerberos".toUpperCase(Locale.ROOT), "Kerberos");
    }
}

