/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util.store;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Maps;
import com.google.api.client.util.Throwables;
import com.google.api.client.util.store.AbstractDataStoreFactory;
import com.google.api.client.util.store.AbstractMemoryDataStore;
import com.google.api.client.util.store.DataStore;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Logger;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FileDataStoreFactory
extends AbstractDataStoreFactory {
    private static final Logger LOGGER = Logger.getLogger(FileDataStoreFactory.class.getName());
    private final File dataDirectory;

    public FileDataStoreFactory(File dataDirectory) throws IOException {
        this.dataDirectory = dataDirectory = dataDirectory.getCanonicalFile();
        if (IOUtils.isSymbolicLink(dataDirectory)) {
            throw new IOException("unable to use a symbolic link: " + dataDirectory);
        }
        if (!dataDirectory.exists() && !dataDirectory.mkdirs()) {
            throw new IOException("unable to create directory: " + dataDirectory);
        }
        FileDataStoreFactory.setPermissionsToOwnerOnly(dataDirectory);
    }

    public final File getDataDirectory() {
        return this.dataDirectory;
    }

    @Override
    protected <V extends Serializable> DataStore<V> createDataStore(String id) throws IOException {
        return new FileDataStore(this, this.dataDirectory, id);
    }

    static void setPermissionsToOwnerOnly(File file) throws IOException {
        try {
            Method setReadable = File.class.getMethod("setReadable", Boolean.TYPE, Boolean.TYPE);
            Method setWritable = File.class.getMethod("setWritable", Boolean.TYPE, Boolean.TYPE);
            Method setExecutable = File.class.getMethod("setExecutable", Boolean.TYPE, Boolean.TYPE);
            if (!(((Boolean)setReadable.invoke((Object)file, false, false)).booleanValue() && ((Boolean)setWritable.invoke((Object)file, false, false)).booleanValue() && ((Boolean)setExecutable.invoke((Object)file, false, false)).booleanValue())) {
                LOGGER.warning("unable to change permissions for everybody: " + file);
            }
            if (!(((Boolean)setReadable.invoke((Object)file, true, true)).booleanValue() && ((Boolean)setWritable.invoke((Object)file, true, true)).booleanValue() && ((Boolean)setExecutable.invoke((Object)file, true, true)).booleanValue())) {
                LOGGER.warning("unable to change permissions for owner: " + file);
            }
        }
        catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();
            Throwables.propagateIfPossible(cause, IOException.class);
            throw new RuntimeException(cause);
        }
        catch (NoSuchMethodException exception) {
            LOGGER.warning("Unable to set permissions for " + file + ", likely because you are running a version of Java prior to 1.6");
        }
        catch (SecurityException securityException) {
        }
        catch (IllegalAccessException illegalAccessException) {
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class FileDataStore<V extends Serializable>
    extends AbstractMemoryDataStore<V> {
        private final File dataFile;

        FileDataStore(FileDataStoreFactory dataStore, File dataDirectory, String id) throws IOException {
            super(dataStore, id);
            this.dataFile = new File(dataDirectory, id);
            if (IOUtils.isSymbolicLink(this.dataFile)) {
                throw new IOException("unable to use a symbolic link: " + this.dataFile);
            }
            if (this.dataFile.createNewFile()) {
                this.keyValueMap = Maps.newHashMap();
                this.save();
            } else {
                this.keyValueMap = (HashMap)IOUtils.deserialize(new FileInputStream(this.dataFile));
            }
        }

        @Override
        public void save() throws IOException {
            IOUtils.serialize(this.keyValueMap, new FileOutputStream(this.dataFile));
        }

        @Override
        public FileDataStoreFactory getDataStoreFactory() {
            return (FileDataStoreFactory)super.getDataStoreFactory();
        }
    }
}

