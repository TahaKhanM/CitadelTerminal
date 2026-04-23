/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util.store;

import com.google.api.client.util.Maps;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractDataStoreFactory
implements DataStoreFactory {
    private final Lock lock = new ReentrantLock();
    private final Map<String, DataStore<? extends Serializable>> dataStoreMap = Maps.newHashMap();
    private static final Pattern ID_PATTERN = Pattern.compile("\\w{1,30}");

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final <V extends Serializable> DataStore<V> getDataStore(String id) throws IOException {
        Preconditions.checkArgument(ID_PATTERN.matcher(id).matches(), "%s does not match pattern %s", id, ID_PATTERN);
        this.lock.lock();
        try {
            DataStore<Serializable> dataStore = this.dataStoreMap.get(id);
            if (dataStore == null) {
                dataStore = this.createDataStore(id);
                this.dataStoreMap.put(id, dataStore);
            }
            DataStore<? extends Serializable> dataStore2 = dataStore;
            return dataStore2;
        }
        finally {
            this.lock.unlock();
        }
    }

    protected abstract <V extends Serializable> DataStore<V> createDataStore(String var1) throws IOException;
}

