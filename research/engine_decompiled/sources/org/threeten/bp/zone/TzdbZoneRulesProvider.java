/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.zone;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StreamCorruptedException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.zone.Ser;
import org.threeten.bp.zone.ZoneRules;
import org.threeten.bp.zone.ZoneRulesException;
import org.threeten.bp.zone.ZoneRulesProvider;

public final class TzdbZoneRulesProvider
extends ZoneRulesProvider {
    private List<String> regionIds;
    private final ConcurrentNavigableMap<String, Version> versions = new ConcurrentSkipListMap<String, Version>();
    private Set<String> loadedUrls = new CopyOnWriteArraySet<String>();

    public TzdbZoneRulesProvider() {
        if (!this.load(ZoneRulesProvider.class.getClassLoader())) {
            throw new ZoneRulesException("No time-zone rules found for 'TZDB'");
        }
    }

    public TzdbZoneRulesProvider(URL url) {
        try {
            if (!this.load(url)) {
                throw new ZoneRulesException("No time-zone rules found: " + url);
            }
        }
        catch (Exception ex) {
            throw new ZoneRulesException("Unable to load TZDB time-zone rules: " + url, ex);
        }
    }

    public TzdbZoneRulesProvider(InputStream stream) {
        try {
            this.load(stream);
        }
        catch (Exception ex) {
            throw new ZoneRulesException("Unable to load TZDB time-zone rules", ex);
        }
    }

    @Override
    protected Set<String> provideZoneIds() {
        return new HashSet<String>(this.regionIds);
    }

    @Override
    protected ZoneRules provideRules(String zoneId, boolean forCaching) {
        Jdk8Methods.requireNonNull(zoneId, "zoneId");
        ZoneRules rules = ((Version)this.versions.lastEntry().getValue()).getRules(zoneId);
        if (rules == null) {
            throw new ZoneRulesException("Unknown time-zone ID: " + zoneId);
        }
        return rules;
    }

    @Override
    protected NavigableMap<String, ZoneRules> provideVersions(String zoneId) {
        TreeMap<String, ZoneRules> map2 = new TreeMap<String, ZoneRules>();
        for (Version version : this.versions.values()) {
            ZoneRules rules = version.getRules(zoneId);
            if (rules == null) continue;
            map2.put(version.versionId, rules);
        }
        return map2;
    }

    private boolean load(ClassLoader classLoader) {
        boolean updated2 = false;
        URL url = null;
        try {
            Enumeration<URL> en = classLoader.getResources("org/threeten/bp/TZDB.dat");
            while (en.hasMoreElements()) {
                url = en.nextElement();
                updated2 |= this.load(url);
            }
        }
        catch (Exception ex) {
            throw new ZoneRulesException("Unable to load TZDB time-zone rules: " + url, ex);
        }
        return updated2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean load(URL url) throws ClassNotFoundException, IOException, ZoneRulesException {
        boolean updated2 = false;
        if (this.loadedUrls.add(url.toExternalForm())) {
            InputStream in = null;
            try {
                in = url.openStream();
                updated2 |= this.load(in);
            }
            finally {
                if (in != null) {
                    in.close();
                }
            }
        }
        return updated2;
    }

    private boolean load(InputStream in) throws IOException, StreamCorruptedException {
        boolean updated2 = false;
        Iterable<Version> loadedVersions = this.loadData(in);
        for (Version loadedVersion : loadedVersions) {
            Version existing = this.versions.putIfAbsent(loadedVersion.versionId, loadedVersion);
            if (existing != null && !existing.versionId.equals(loadedVersion.versionId)) {
                throw new ZoneRulesException("Data already loaded for TZDB time-zone rules version: " + loadedVersion.versionId);
            }
            updated2 = true;
        }
        return updated2;
    }

    private Iterable<Version> loadData(InputStream in) throws IOException, StreamCorruptedException {
        DataInputStream dis = new DataInputStream(in);
        if (dis.readByte() != 1) {
            throw new StreamCorruptedException("File format not recognised");
        }
        String groupId = dis.readUTF();
        if (!"TZDB".equals(groupId)) {
            throw new StreamCorruptedException("File format not recognised");
        }
        int versionCount = dis.readShort();
        String[] versionArray = new String[versionCount];
        for (int i = 0; i < versionCount; ++i) {
            versionArray[i] = dis.readUTF();
        }
        int regionCount = dis.readShort();
        String[] regionArray = new String[regionCount];
        for (int i = 0; i < regionCount; ++i) {
            regionArray[i] = dis.readUTF();
        }
        this.regionIds = Arrays.asList(regionArray);
        int ruleCount = dis.readShort();
        Object[] ruleArray = new Object[ruleCount];
        for (int i = 0; i < ruleCount; ++i) {
            byte[] bytes2 = new byte[dis.readShort()];
            dis.readFully(bytes2);
            ruleArray[i] = bytes2;
        }
        AtomicReferenceArray<Object> ruleData = new AtomicReferenceArray<Object>(ruleArray);
        HashSet<Version> versionSet = new HashSet<Version>(versionCount);
        for (int i = 0; i < versionCount; ++i) {
            int versionRegionCount = dis.readShort();
            String[] versionRegionArray = new String[versionRegionCount];
            short[] versionRulesArray = new short[versionRegionCount];
            for (int j = 0; j < versionRegionCount; ++j) {
                versionRegionArray[j] = regionArray[dis.readShort()];
                versionRulesArray[j] = dis.readShort();
            }
            versionSet.add(new Version(versionArray[i], versionRegionArray, versionRulesArray, ruleData));
        }
        return versionSet;
    }

    public String toString() {
        return "TZDB";
    }

    static class Version {
        private final String versionId;
        private final String[] regionArray;
        private final short[] ruleIndices;
        private final AtomicReferenceArray<Object> ruleData;

        Version(String versionId, String[] regionIds, short[] ruleIndices, AtomicReferenceArray<Object> ruleData) {
            this.ruleData = ruleData;
            this.versionId = versionId;
            this.regionArray = regionIds;
            this.ruleIndices = ruleIndices;
        }

        ZoneRules getRules(String regionId) {
            int regionIndex = Arrays.binarySearch(this.regionArray, regionId);
            if (regionIndex < 0) {
                return null;
            }
            try {
                return this.createRule(this.ruleIndices[regionIndex]);
            }
            catch (Exception ex) {
                throw new ZoneRulesException("Invalid binary time-zone data: TZDB:" + regionId + ", version: " + this.versionId, ex);
            }
        }

        ZoneRules createRule(short index) throws Exception {
            Object obj = this.ruleData.get(index);
            if (obj instanceof byte[]) {
                byte[] bytes2 = (byte[])obj;
                DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes2));
                obj = Ser.read(dis);
                this.ruleData.set(index, obj);
            }
            return (ZoneRules)obj;
        }

        public String toString() {
            return this.versionId;
        }
    }
}

