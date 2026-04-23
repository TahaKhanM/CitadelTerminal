/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util.store;

import com.google.api.client.util.store.DataStore;
import java.io.IOException;
import java.io.Serializable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface DataStoreFactory {
    public <V extends Serializable> DataStore<V> getDataStore(String var1) throws IOException;
}

