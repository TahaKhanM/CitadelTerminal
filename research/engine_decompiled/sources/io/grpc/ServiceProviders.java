/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import com.google.common.annotations.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

final class ServiceProviders {
    private ServiceProviders() {
    }

    public static <T> T load(Class<T> klass, Iterable<Class<?>> hardcoded, ClassLoader cl, PriorityAccessor<T> priorityAccessor) {
        List<T> candidates = ServiceProviders.loadAll(klass, hardcoded, cl, priorityAccessor);
        if (candidates.isEmpty()) {
            return null;
        }
        return candidates.get(0);
    }

    public static <T> List<T> loadAll(Class<T> klass, Iterable<Class<?>> hardcoded, ClassLoader cl, final PriorityAccessor<T> priorityAccessor) {
        Iterable<T> candidates = ServiceProviders.isAndroid(cl) ? ServiceProviders.getCandidatesViaHardCoded(klass, hardcoded) : ServiceProviders.getCandidatesViaServiceLoader(klass, cl);
        ArrayList<T> list2 = new ArrayList<T>();
        for (T current : candidates) {
            if (!priorityAccessor.isAvailable(current)) continue;
            list2.add(current);
        }
        Collections.sort(list2, Collections.reverseOrder(new Comparator<T>(){

            @Override
            public int compare(T f1, T f2) {
                return priorityAccessor.getPriority(f1) - priorityAccessor.getPriority(f2);
            }
        }));
        return Collections.unmodifiableList(list2);
    }

    static boolean isAndroid(ClassLoader cl) {
        try {
            Class.forName("android.app.Application", false, cl);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @VisibleForTesting
    public static <T> Iterable<T> getCandidatesViaServiceLoader(Class<T> klass, ClassLoader cl) {
        ServiceLoader<T> i = ServiceLoader.load(klass, cl);
        if (!i.iterator().hasNext()) {
            i = ServiceLoader.load(klass);
        }
        return i;
    }

    @VisibleForTesting
    static <T> Iterable<T> getCandidatesViaHardCoded(Class<T> klass, Iterable<Class<?>> hardcoded) {
        ArrayList<T> list2 = new ArrayList<T>();
        for (Class<?> candidate : hardcoded) {
            list2.add(ServiceProviders.create(klass, candidate));
        }
        return list2;
    }

    @VisibleForTesting
    static <T> T create(Class<T> klass, Class<?> rawClass) {
        try {
            return rawClass.asSubclass(klass).getConstructor(new Class[0]).newInstance(new Object[0]);
        }
        catch (Throwable t) {
            throw new ServiceConfigurationError(String.format("Provider %s could not be instantiated %s", rawClass.getName(), t), t);
        }
    }

    public static interface PriorityAccessor<T> {
        public boolean isAvailable(T var1);

        public int getPriority(T var1);
    }
}

