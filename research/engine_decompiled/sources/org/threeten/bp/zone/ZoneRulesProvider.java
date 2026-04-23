/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.zone;

import java.util.HashSet;
import java.util.NavigableMap;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.zone.ZoneRules;
import org.threeten.bp.zone.ZoneRulesException;

public abstract class ZoneRulesProvider {
    private static final CopyOnWriteArrayList<ZoneRulesProvider> PROVIDERS = new CopyOnWriteArrayList();
    private static final ConcurrentMap<String, ZoneRulesProvider> ZONES = new ConcurrentHashMap<String, ZoneRulesProvider>(512, 0.75f, 2);

    public static Set<String> getAvailableZoneIds() {
        return new HashSet<String>(ZONES.keySet());
    }

    public static ZoneRules getRules(String zoneId, boolean forCaching) {
        Jdk8Methods.requireNonNull(zoneId, "zoneId");
        return ZoneRulesProvider.getProvider(zoneId).provideRules(zoneId, forCaching);
    }

    public static NavigableMap<String, ZoneRules> getVersions(String zoneId) {
        Jdk8Methods.requireNonNull(zoneId, "zoneId");
        return ZoneRulesProvider.getProvider(zoneId).provideVersions(zoneId);
    }

    private static ZoneRulesProvider getProvider(String zoneId) {
        ZoneRulesProvider provider = (ZoneRulesProvider)ZONES.get(zoneId);
        if (provider == null) {
            if (ZONES.isEmpty()) {
                throw new ZoneRulesException("No time-zone data files registered");
            }
            throw new ZoneRulesException("Unknown time-zone ID: " + zoneId);
        }
        return provider;
    }

    public static void registerProvider(ZoneRulesProvider provider) {
        Jdk8Methods.requireNonNull(provider, "provider");
        ZoneRulesProvider.registerProvider0(provider);
        PROVIDERS.add(provider);
    }

    private static void registerProvider0(ZoneRulesProvider provider) {
        for (String zoneId : provider.provideZoneIds()) {
            Jdk8Methods.requireNonNull(zoneId, "zoneId");
            ZoneRulesProvider old = ZONES.putIfAbsent(zoneId, provider);
            if (old == null) continue;
            throw new ZoneRulesException("Unable to register zone as one already registered with that ID: " + zoneId + ", currently loading from provider: " + provider);
        }
    }

    public static boolean refresh() {
        boolean changed = false;
        for (ZoneRulesProvider provider : PROVIDERS) {
            changed |= provider.provideRefresh();
        }
        return changed;
    }

    protected ZoneRulesProvider() {
    }

    protected abstract Set<String> provideZoneIds();

    protected abstract ZoneRules provideRules(String var1, boolean var2);

    protected abstract NavigableMap<String, ZoneRules> provideVersions(String var1);

    protected boolean provideRefresh() {
        return false;
    }

    static {
        ServiceLoader<ZoneRulesProvider> loader = ServiceLoader.load(ZoneRulesProvider.class, ZoneRulesProvider.class.getClassLoader());
        for (ZoneRulesProvider provider : loader) {
            try {
                ZoneRulesProvider.registerProvider0(provider);
            }
            catch (ServiceConfigurationError ex) {
                if (ex.getCause() instanceof SecurityException) continue;
                throw ex;
            }
        }
    }
}

