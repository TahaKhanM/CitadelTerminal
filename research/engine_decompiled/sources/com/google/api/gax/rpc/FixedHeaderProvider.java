/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.AutoValue_FixedHeaderProvider;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import javax.annotation.Nullable;

@BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
public abstract class FixedHeaderProvider
implements HeaderProvider,
Serializable {
    private static final long serialVersionUID = -4881534091594970538L;

    @Override
    @Nullable
    public abstract Map<String, String> getHeaders();

    public static FixedHeaderProvider create(Map<String, String> headers) {
        FixedHeaderProvider.checkKeys(headers.keySet());
        return new AutoValue_FixedHeaderProvider(ImmutableMap.copyOf(headers));
    }

    public static FixedHeaderProvider create(String ... keyValuePairs) {
        if (keyValuePairs.length % 2 != 0) {
            throw new IllegalArgumentException("The keyValuePairs var-arg parameter must contain an even number of elements");
        }
        ImmutableMap.Builder<String, String> headersBuilder = ImmutableMap.builder();
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            headersBuilder.put(keyValuePairs[i], keyValuePairs[i + 1]);
        }
        ImmutableMap<String, String> headers = headersBuilder.build();
        FixedHeaderProvider.checkKeys(headers.keySet());
        return new AutoValue_FixedHeaderProvider(headers);
    }

    private static void checkKeys(Collection<String> keys) {
        HashSet<String> caseInsensitiveKeys = new HashSet<String>();
        for (String key : keys) {
            if (caseInsensitiveKeys.add(key.toLowerCase())) continue;
            throw new IllegalArgumentException("The header key '" + key + "' is not case insensitively unique");
        }
    }
}

