/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http;

import com.google.api.client.http.UrlEncodedParser;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.escape.CharEscapers;
import com.google.api.client.util.escape.Escaper;
import com.google.api.client.util.escape.PercentEscaper;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class GenericUrl
extends GenericData {
    private static final Escaper URI_FRAGMENT_ESCAPER = new PercentEscaper("=&-_.!~*'()@:$,;/?:", false);
    private String scheme;
    private String host;
    private String userInfo;
    private int port = -1;
    private List<String> pathParts;
    private String fragment;

    public GenericUrl() {
    }

    public GenericUrl(String encodedUrl) {
        this(GenericUrl.parseURL(encodedUrl));
    }

    public GenericUrl(URI uri) {
        this(uri.getScheme(), uri.getHost(), uri.getPort(), uri.getRawPath(), uri.getRawFragment(), uri.getRawQuery(), uri.getRawUserInfo());
    }

    public GenericUrl(URL url) {
        this(url.getProtocol(), url.getHost(), url.getPort(), url.getPath(), url.getRef(), url.getQuery(), url.getUserInfo());
    }

    private GenericUrl(String scheme, String host, int port, String path, String fragment, String query, String userInfo) {
        this.scheme = scheme.toLowerCase();
        this.host = host;
        this.port = port;
        this.pathParts = GenericUrl.toPathParts(path);
        String string2 = this.fragment = fragment != null ? CharEscapers.decodeUri(fragment) : null;
        if (query != null) {
            UrlEncodedParser.parse(query, (Object)this);
        }
        this.userInfo = userInfo != null ? CharEscapers.decodeUri(userInfo) : null;
    }

    @Override
    public int hashCode() {
        return this.build().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || !(obj instanceof GenericUrl)) {
            return false;
        }
        GenericUrl other = (GenericUrl)obj;
        return this.build().equals(other.toString());
    }

    @Override
    public String toString() {
        return this.build();
    }

    @Override
    public GenericUrl clone() {
        GenericUrl result2 = (GenericUrl)super.clone();
        if (this.pathParts != null) {
            result2.pathParts = new ArrayList<String>(this.pathParts);
        }
        return result2;
    }

    @Override
    public GenericUrl set(String fieldName, Object value) {
        return (GenericUrl)super.set(fieldName, value);
    }

    public final String getScheme() {
        return this.scheme;
    }

    public final void setScheme(String scheme) {
        this.scheme = Preconditions.checkNotNull(scheme);
    }

    public String getHost() {
        return this.host;
    }

    public final void setHost(String host) {
        this.host = Preconditions.checkNotNull(host);
    }

    public final String getUserInfo() {
        return this.userInfo;
    }

    public final void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public int getPort() {
        return this.port;
    }

    public final void setPort(int port) {
        Preconditions.checkArgument(port >= -1, "expected port >= -1");
        this.port = port;
    }

    public List<String> getPathParts() {
        return this.pathParts;
    }

    public void setPathParts(List<String> pathParts) {
        this.pathParts = pathParts;
    }

    public String getFragment() {
        return this.fragment;
    }

    public final void setFragment(String fragment) {
        this.fragment = fragment;
    }

    public final String build() {
        return this.buildAuthority() + this.buildRelativeUrl();
    }

    public final String buildAuthority() {
        StringBuilder buf = new StringBuilder();
        buf.append(Preconditions.checkNotNull(this.scheme));
        buf.append("://");
        if (this.userInfo != null) {
            buf.append(CharEscapers.escapeUriUserInfo(this.userInfo)).append('@');
        }
        buf.append(Preconditions.checkNotNull(this.host));
        int port = this.port;
        if (port != -1) {
            buf.append(':').append(port);
        }
        return buf.toString();
    }

    public final String buildRelativeUrl() {
        StringBuilder buf = new StringBuilder();
        if (this.pathParts != null) {
            this.appendRawPathFromParts(buf);
        }
        GenericUrl.addQueryParams(this.entrySet(), buf);
        String fragment = this.fragment;
        if (fragment != null) {
            buf.append('#').append(URI_FRAGMENT_ESCAPER.escape(fragment));
        }
        return buf.toString();
    }

    public final URI toURI() {
        return GenericUrl.toURI(this.build());
    }

    public final URL toURL() {
        return GenericUrl.parseURL(this.build());
    }

    public final URL toURL(String relativeUrl) {
        try {
            URL url = this.toURL();
            return new URL(url, relativeUrl);
        }
        catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Object getFirst(String name) {
        Object value = this.get(name);
        if (value instanceof Collection) {
            Collection collectionValue = (Collection)value;
            Iterator iterator2 = collectionValue.iterator();
            return iterator2.hasNext() ? iterator2.next() : null;
        }
        return value;
    }

    public Collection<Object> getAll(String name) {
        Object value = this.get(name);
        if (value == null) {
            return Collections.emptySet();
        }
        if (value instanceof Collection) {
            Collection collectionValue = (Collection)value;
            return Collections.unmodifiableCollection(collectionValue);
        }
        return Collections.singleton(value);
    }

    public String getRawPath() {
        List<String> pathParts = this.pathParts;
        if (pathParts == null) {
            return null;
        }
        StringBuilder buf = new StringBuilder();
        this.appendRawPathFromParts(buf);
        return buf.toString();
    }

    public void setRawPath(String encodedPath) {
        this.pathParts = GenericUrl.toPathParts(encodedPath);
    }

    public void appendRawPath(String encodedPath) {
        if (encodedPath != null && encodedPath.length() != 0) {
            List<String> appendedPathParts = GenericUrl.toPathParts(encodedPath);
            if (this.pathParts == null || this.pathParts.isEmpty()) {
                this.pathParts = appendedPathParts;
            } else {
                int size2 = this.pathParts.size();
                this.pathParts.set(size2 - 1, this.pathParts.get(size2 - 1) + appendedPathParts.get(0));
                this.pathParts.addAll(appendedPathParts.subList(1, appendedPathParts.size()));
            }
        }
    }

    public static List<String> toPathParts(String encodedPath) {
        if (encodedPath == null || encodedPath.length() == 0) {
            return null;
        }
        ArrayList<String> result2 = new ArrayList<String>();
        int cur = 0;
        boolean notDone = true;
        while (notDone) {
            int slash = encodedPath.indexOf(47, cur);
            notDone = slash != -1;
            String sub = notDone ? encodedPath.substring(cur, slash) : encodedPath.substring(cur);
            result2.add(CharEscapers.decodeUri(sub));
            cur = slash + 1;
        }
        return result2;
    }

    private void appendRawPathFromParts(StringBuilder buf) {
        int size2 = this.pathParts.size();
        for (int i = 0; i < size2; ++i) {
            String pathPart = this.pathParts.get(i);
            if (i != 0) {
                buf.append('/');
            }
            if (pathPart.length() == 0) continue;
            buf.append(CharEscapers.escapeUriPath(pathPart));
        }
    }

    static void addQueryParams(Set<Map.Entry<String, Object>> entrySet, StringBuilder buf) {
        boolean first = true;
        for (Map.Entry<String, Object> nameValueEntry : entrySet) {
            Object value = nameValueEntry.getValue();
            if (value == null) continue;
            String name = CharEscapers.escapeUriQuery(nameValueEntry.getKey());
            if (value instanceof Collection) {
                Collection collectionValue = (Collection)value;
                for (Object repeatedValue : collectionValue) {
                    first = GenericUrl.appendParam(first, buf, name, repeatedValue);
                }
                continue;
            }
            first = GenericUrl.appendParam(first, buf, name, value);
        }
    }

    private static boolean appendParam(boolean first, StringBuilder buf, String name, Object value) {
        if (first) {
            first = false;
            buf.append('?');
        } else {
            buf.append('&');
        }
        buf.append(name);
        String stringValue = CharEscapers.escapeUriQuery(value.toString());
        if (stringValue.length() != 0) {
            buf.append('=').append(stringValue);
        }
        return first;
    }

    private static URI toURI(String encodedUrl) {
        try {
            return new URI(encodedUrl);
        }
        catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static URL parseURL(String encodedUrl) {
        try {
            return new URL(encodedUrl);
        }
        catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

