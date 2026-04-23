/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf;

import com.google.protobuf.AbstractProtobufList;
import java.util.ArrayList;
import java.util.List;

final class ProtobufArrayList<E>
extends AbstractProtobufList<E> {
    private static final ProtobufArrayList<Object> EMPTY_LIST = new ProtobufArrayList();
    private final List<E> list;

    public static <E> ProtobufArrayList<E> emptyList() {
        return EMPTY_LIST;
    }

    ProtobufArrayList() {
        this(new ArrayList(10));
    }

    private ProtobufArrayList(List<E> list2) {
        this.list = list2;
    }

    @Override
    public ProtobufArrayList<E> mutableCopyWithCapacity(int capacity) {
        if (capacity < this.size()) {
            throw new IllegalArgumentException();
        }
        ArrayList<E> newList = new ArrayList<E>(capacity);
        newList.addAll(this.list);
        return new ProtobufArrayList(newList);
    }

    @Override
    public void add(int index, E element) {
        this.ensureIsMutable();
        this.list.add(index, element);
        ++this.modCount;
    }

    @Override
    public E get(int index) {
        return this.list.get(index);
    }

    @Override
    public E remove(int index) {
        this.ensureIsMutable();
        E toReturn = this.list.remove(index);
        ++this.modCount;
        return toReturn;
    }

    @Override
    public E set(int index, E element) {
        this.ensureIsMutable();
        E toReturn = this.list.set(index, element);
        ++this.modCount;
        return toReturn;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    static {
        EMPTY_LIST.makeImmutable();
    }
}

