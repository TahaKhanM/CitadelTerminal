/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util.store;

import com.google.api.client.util.store.DataStoreFactory;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface DataStore<V extends Serializable> {
    public DataStoreFactory getDataStoreFactory();

    public String getId();

    public int size() throws IOException;

    public boolean isEmpty() throws IOException;

    public boolean containsKey(String var1) throws IOException;

    public boolean containsValue(V var1) throws IOException;

    public Set<String> keySet() throws IOException;

    public Collection<V> values() throws IOException;

    public V get(String var1) throws IOException;

    public DataStore<V> set(String var1, V var2) throws IOException;

    public DataStore<V> clear() throws IOException;

    public DataStore<V> delete(String var1) throws IOException;
}

