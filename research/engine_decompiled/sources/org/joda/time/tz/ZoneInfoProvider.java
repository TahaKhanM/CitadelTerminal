/*
 * Decompiled with CFR 0.152.
 */
package org.joda.time.tz;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.DateTimeZone;
import org.joda.time.tz.DateTimeZoneBuilder;
import org.joda.time.tz.Provider;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ZoneInfoProvider
implements Provider {
    private final File iFileDir;
    private final String iResourcePath;
    private final ClassLoader iLoader;
    private final Map<String, Object> iZoneInfoMap;
    private final Set<String> iZoneInfoKeys;

    public ZoneInfoProvider(File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("No file directory provided");
        }
        if (!file.exists()) {
            throw new IOException("File directory doesn't exist: " + file);
        }
        if (!file.isDirectory()) {
            throw new IOException("File doesn't refer to a directory: " + file);
        }
        this.iFileDir = file;
        this.iResourcePath = null;
        this.iLoader = null;
        this.iZoneInfoMap = ZoneInfoProvider.loadZoneInfoMap(this.openResource("ZoneInfoMap"));
        this.iZoneInfoKeys = Collections.unmodifiableSortedSet(new TreeSet<String>(this.iZoneInfoMap.keySet()));
    }

    public ZoneInfoProvider(String string2) throws IOException {
        this(string2, null, false);
    }

    public ZoneInfoProvider(String string2, ClassLoader classLoader) throws IOException {
        this(string2, classLoader, true);
    }

    private ZoneInfoProvider(String string2, ClassLoader classLoader, boolean bl) throws IOException {
        if (string2 == null) {
            throw new IllegalArgumentException("No resource path provided");
        }
        if (!string2.endsWith("/")) {
            string2 = string2 + '/';
        }
        this.iFileDir = null;
        this.iResourcePath = string2;
        if (classLoader == null && !bl) {
            classLoader = this.getClass().getClassLoader();
        }
        this.iLoader = classLoader;
        this.iZoneInfoMap = ZoneInfoProvider.loadZoneInfoMap(this.openResource("ZoneInfoMap"));
        this.iZoneInfoKeys = Collections.unmodifiableSortedSet(new TreeSet<String>(this.iZoneInfoMap.keySet()));
    }

    @Override
    public DateTimeZone getZone(String string2) {
        if (string2 == null) {
            return null;
        }
        Object object = this.iZoneInfoMap.get(string2);
        if (object == null) {
            return null;
        }
        if (object instanceof SoftReference) {
            SoftReference softReference = (SoftReference)object;
            DateTimeZone dateTimeZone = (DateTimeZone)softReference.get();
            if (dateTimeZone != null) {
                return dateTimeZone;
            }
            return this.loadZoneData(string2);
        }
        if (string2.equals(object)) {
            return this.loadZoneData(string2);
        }
        return this.getZone((String)object);
    }

    @Override
    public Set<String> getAvailableIDs() {
        return this.iZoneInfoKeys;
    }

    protected void uncaughtException(Exception exception) {
        exception.printStackTrace();
    }

    private InputStream openResource(String string2) throws IOException {
        InputStream inputStream;
        if (this.iFileDir != null) {
            inputStream = new FileInputStream(new File(this.iFileDir, string2));
        } else {
            String string3 = this.iResourcePath.concat(string2);
            inputStream = this.iLoader != null ? this.iLoader.getResourceAsStream(string3) : ClassLoader.getSystemResourceAsStream(string3);
            if (inputStream == null) {
                StringBuilder stringBuilder = new StringBuilder(40).append("Resource not found: \"").append(string3).append("\" ClassLoader: ").append(this.iLoader != null ? this.iLoader.toString() : "system");
                throw new IOException(stringBuilder.toString());
            }
        }
        return inputStream;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private DateTimeZone loadZoneData(String string2) {
        InputStream inputStream = null;
        try {
            inputStream = this.openResource(string2);
            DateTimeZone dateTimeZone = DateTimeZoneBuilder.readFrom(inputStream, string2);
            this.iZoneInfoMap.put(string2, new SoftReference<DateTimeZone>(dateTimeZone));
            DateTimeZone dateTimeZone2 = dateTimeZone;
            return dateTimeZone2;
        }
        catch (IOException iOException) {
            this.uncaughtException(iOException);
            this.iZoneInfoMap.remove(string2);
            DateTimeZone dateTimeZone = null;
            return dateTimeZone;
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (IOException iOException) {}
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static Map<String, Object> loadZoneInfoMap(InputStream inputStream) throws IOException {
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<String, Object>();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        try {
            ZoneInfoProvider.readZoneInfoMap(dataInputStream, concurrentHashMap);
        }
        finally {
            try {
                dataInputStream.close();
            }
            catch (IOException iOException) {}
        }
        concurrentHashMap.put("UTC", new SoftReference<DateTimeZone>(DateTimeZone.UTC));
        return concurrentHashMap;
    }

    private static void readZoneInfoMap(DataInputStream dataInputStream, Map<String, Object> map2) throws IOException {
        int n;
        int n2 = dataInputStream.readUnsignedShort();
        String[] stringArray = new String[n2];
        for (n = 0; n < n2; ++n) {
            stringArray[n] = dataInputStream.readUTF().intern();
        }
        n2 = dataInputStream.readUnsignedShort();
        for (n = 0; n < n2; ++n) {
            try {
                map2.put(stringArray[dataInputStream.readUnsignedShort()], stringArray[dataInputStream.readUnsignedShort()]);
                continue;
            }
            catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                throw new IOException("Corrupt zone info map");
            }
        }
    }
}

