/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util.store;

import com.google.api.client.util.store.AbstractDataStoreFactory;
import com.google.api.client.util.store.AbstractMemoryDataStore;
import com.google.api.client.util.store.DataStore;
import java.io.IOException;
import java.io.Serializable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MemoryDataStoreFactory
extends AbstractDataStoreFactory {
    @Override
    protected <V extends Serializable> DataStore<V> createDataStore(String id) throws IOException {
        return new MemoryDataStore(this, id);
    }

    public static MemoryDataStoreFactory getDefaultInstance() {
        return InstanceHolder.INSTANCE;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class MemoryDataStore<V extends Serializable>
    extends AbstractMemoryDataStore<V> {
        MemoryDataStore(MemoryDataStoreFactory dataStore, String id) {
            super(dataStore, id);
        }

        @Override
        public MemoryDataStoreFactory getDataStoreFactory() {
            return (MemoryDataStoreFactory)super.getDataStoreFactory();
        }
    }

    static class InstanceHolder {
        static final MemoryDataStoreFactory INSTANCE = new MemoryDataStoreFactory();

        InstanceHolder() {
        }
    }
}

