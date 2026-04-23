/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.Collections2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class Lists {
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList();
    }

    public static <E> ArrayList<E> newArrayListWithCapacity(int initialArraySize) {
        return new ArrayList(initialArraySize);
    }

    public static <E> ArrayList<E> newArrayList(Iterable<? extends E> elements) {
        return elements instanceof Collection ? new ArrayList<E>(Collections2.cast(elements)) : Lists.newArrayList(elements.iterator());
    }

    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements) {
        ArrayList<E> list2 = Lists.newArrayList();
        while (elements.hasNext()) {
            list2.add(elements.next());
        }
        return list2;
    }

    private Lists() {
    }
}

