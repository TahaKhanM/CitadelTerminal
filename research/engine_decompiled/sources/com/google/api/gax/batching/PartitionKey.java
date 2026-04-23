/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.core.BetaApi;
import com.google.common.collect.ImmutableList;

@BetaApi(value="The surface for batching is not stable yet and may change in the future.")
public final class PartitionKey {
    private final ImmutableList<Object> keys;
    private final int hash;

    public PartitionKey(Object ... keys) {
        this.keys = ImmutableList.copyOf(keys);
        int hash = 1;
        for (Object obj : keys) {
            hash = hash * 31 + obj.hashCode();
        }
        this.hash = hash;
    }

    public int hashCode() {
        return this.hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PartitionKey)) {
            return false;
        }
        PartitionKey other = (PartitionKey)obj;
        if (this.keys.size() != other.keys.size()) {
            return false;
        }
        for (int i = 0; i < this.keys.size(); ++i) {
            if (this.keys.get(i).equals(other.keys.get(i))) continue;
            return false;
        }
        return true;
    }
}

