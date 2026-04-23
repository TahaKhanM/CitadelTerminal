/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.gax.rpc.FixedHeaderProvider;
import java.util.Map;
import javax.annotation.Nullable;

final class AutoValue_FixedHeaderProvider
extends FixedHeaderProvider {
    private final Map<String, String> headers;
    private static final long serialVersionUID = -4881534091594970538L;

    AutoValue_FixedHeaderProvider(@Nullable Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    @Nullable
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public String toString() {
        return "FixedHeaderProvider{headers=" + this.headers + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof FixedHeaderProvider) {
            FixedHeaderProvider that = (FixedHeaderProvider)o;
            return this.headers == null ? that.getHeaders() == null : this.headers.equals(that.getHeaders());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.headers == null ? 0 : this.headers.hashCode();
    }
}

