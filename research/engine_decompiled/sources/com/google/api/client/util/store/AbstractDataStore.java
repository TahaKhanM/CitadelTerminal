/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util.store;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import java.io.IOException;
import java.io.Serializable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractDataStore<V extends Serializable>
implements DataStore<V> {
    private final DataStoreFactory dataStoreFactory;
    private final String id;

    protected AbstractDataStore(DataStoreFactory dataStoreFactory, String id) {
        this.dataStoreFactory = Preconditions.checkNotNull(dataStoreFactory);
        this.id = Preconditions.checkNotNull(id);
    }

    @Override
    public DataStoreFactory getDataStoreFactory() {
        return this.dataStoreFactory;
    }

    @Override
    public final String getId() {
        return this.id;
    }

    @Override
    public boolean containsKey(String key) throws IOException {
        return this.get(key) != null;
    }

    @Override
    public boolean containsValue(V value) throws IOException {
        return this.values().contains(value);
    }

    @Override
    public boolean isEmpty() throws IOException {
        return this.size() == 0;
    }

    @Override
    public int size() throws IOException {
        return this.keySet().size();
    }
}

