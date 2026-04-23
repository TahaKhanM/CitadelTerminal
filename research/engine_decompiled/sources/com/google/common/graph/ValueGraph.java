/*
 * Decompiled with CFR 0.152.
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.graph.Graph;
import javax.annotation.Nullable;

@Beta
public interface ValueGraph<N, V>
extends Graph<N> {
    public V edgeValue(Object var1, Object var2);

    public V edgeValueOrDefault(Object var1, Object var2, @Nullable V var3);

    @Override
    public boolean equals(@Nullable Object var1);

    @Override
    public int hashCode();
}

