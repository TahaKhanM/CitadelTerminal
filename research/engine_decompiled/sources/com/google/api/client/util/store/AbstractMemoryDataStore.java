/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util.store;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Maps;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.AbstractDataStore;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.DataStoreUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AbstractMemoryDataStore<V extends Serializable>
extends AbstractDataStore<V> {
    private final Lock lock = new ReentrantLock();
    protected HashMap<String, byte[]> keyValueMap = Maps.newHashMap();

    protected AbstractMemoryDataStore(DataStoreFactory dataStoreFactory, String id) {
        super(dataStoreFactory, id);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final Set<String> keySet() throws IOException {
        this.lock.lock();
        try {
            Set<String> set = Collections.unmodifiableSet(this.keyValueMap.keySet());
            return set;
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final Collection<V> values() throws IOException {
        this.lock.lock();
        try {
            ArrayList result2 = Lists.newArrayList();
            for (byte[] bytes2 : this.keyValueMap.values()) {
                result2.add(IOUtils.deserialize(bytes2));
            }
            List list2 = Collections.unmodifiableList(result2);
            return list2;
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final V get(String key) throws IOException {
        if (key == null) {
            return null;
        }
        this.lock.lock();
        try {
            Object s2 = IOUtils.deserialize(this.keyValueMap.get(key));
            return (V)s2;
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final DataStore<V> set(String key, V value) throws IOException {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        this.lock.lock();
        try {
            this.keyValueMap.put(key, IOUtils.serialize(value));
            this.save();
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DataStore<V> delete(String key) throws IOException {
        if (key == null) {
            return this;
        }
        this.lock.lock();
        try {
            this.keyValueMap.remove(key);
            this.save();
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final DataStore<V> clear() throws IOException {
        this.lock.lock();
        try {
            this.keyValueMap.clear();
            this.save();
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean containsKey(String key) throws IOException {
        if (key == null) {
            return false;
        }
        this.lock.lock();
        try {
            boolean bl = this.keyValueMap.containsKey(key);
            return bl;
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean containsValue(V value) throws IOException {
        if (value == null) {
            return false;
        }
        this.lock.lock();
        try {
            byte[] serialized = IOUtils.serialize(value);
            for (byte[] bytes2 : this.keyValueMap.values()) {
                if (!Arrays.equals(serialized, bytes2)) continue;
                boolean bl = true;
                return bl;
            }
            boolean bl = false;
            return bl;
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isEmpty() throws IOException {
        this.lock.lock();
        try {
            boolean bl = this.keyValueMap.isEmpty();
            return bl;
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int size() throws IOException {
        this.lock.lock();
        try {
            int n = this.keyValueMap.size();
            return n;
        }
        finally {
            this.lock.unlock();
        }
    }

    public void save() throws IOException {
    }

    public String toString() {
        return DataStoreUtils.toString(this);
    }
}

