/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc.internal;

import com.google.api.core.InternalApi;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@InternalApi
public class Headers {
    public static ImmutableMap<String, List<String>> mergeHeaders(Map<String, List<String>> oldHeaders, Map<String, List<String>> newHeaders) {
        String key;
        ImmutableMap.Builder<String, ImmutableCollection> headersBuilder = ImmutableMap.builder();
        for (Map.Entry<String, List<String>> entry : oldHeaders.entrySet()) {
            key = entry.getKey();
            List<String> oldValue = entry.getValue();
            List<String> newValue = newHeaders.get(key);
            ImmutableList.Builder mergedValueBuilder = ImmutableList.builder();
            mergedValueBuilder.addAll(oldValue);
            if (newValue != null) {
                mergedValueBuilder.addAll(newValue);
            }
            headersBuilder.put(key, mergedValueBuilder.build());
        }
        for (Map.Entry<String, List<String>> entry : newHeaders.entrySet()) {
            key = entry.getKey();
            if (oldHeaders.containsKey(key)) continue;
            headersBuilder.put(key, ImmutableList.copyOf((Collection)entry.getValue()));
        }
        return headersBuilder.build();
    }
}

