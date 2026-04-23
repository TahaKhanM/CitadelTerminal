/*
 * Decompiled with CFR 0.152.
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Constraint;
import com.google.common.collect.Constraints;
import com.google.common.collect.ForwardingCollection;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ForwardingMapEntry;
import com.google.common.collect.ForwardingMultimap;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.MapConstraint;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.collect.TransformedIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Deprecated
@Beta
@GwtCompatible
public final class MapConstraints {
    private MapConstraints() {
    }

    public static <K, V> Map<K, V> constrainedMap(Map<K, V> map2, MapConstraint<? super K, ? super V> constraint) {
        return new ConstrainedMap<K, V>(map2, constraint);
    }

    public static <K, V> ListMultimap<K, V> constrainedListMultimap(ListMultimap<K, V> multimap, MapConstraint<? super K, ? super V> constraint) {
        return new ConstrainedListMultimap<K, V>(multimap, constraint);
    }

    private static <K, V> Map.Entry<K, V> constrainedEntry(final Map.Entry<K, V> entry, final MapConstraint<? super K, ? super V> constraint) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkNotNull(constraint);
        return new ForwardingMapEntry<K, V>(){

            @Override
            protected Map.Entry<K, V> delegate() {
                return entry;
            }

            @Override
            public V setValue(V value) {
                constraint.checkKeyValue(this.getKey(), value);
                return entry.setValue(value);
            }
        };
    }

    private static <K, V> Map.Entry<K, Collection<V>> constrainedAsMapEntry(final Map.Entry<K, Collection<V>> entry, final MapConstraint<? super K, ? super V> constraint) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkNotNull(constraint);
        return new ForwardingMapEntry<K, Collection<V>>(){

            @Override
            protected Map.Entry<K, Collection<V>> delegate() {
                return entry;
            }

            @Override
            public Collection<V> getValue() {
                return Constraints.constrainedTypePreservingCollection((Collection)entry.getValue(), new Constraint<V>(){

                    @Override
                    public V checkElement(V value) {
                        constraint.checkKeyValue(this.getKey(), value);
                        return value;
                    }
                });
            }
        };
    }

    private static <K, V> Set<Map.Entry<K, Collection<V>>> constrainedAsMapEntries(Set<Map.Entry<K, Collection<V>>> entries, MapConstraint<? super K, ? super V> constraint) {
        return new ConstrainedAsMapEntries<K, V>(entries, constraint);
    }

    private static <K, V> Collection<Map.Entry<K, V>> constrainedEntries(Collection<Map.Entry<K, V>> entries, MapConstraint<? super K, ? super V> constraint) {
        if (entries instanceof Set) {
            return MapConstraints.constrainedEntrySet((Set)entries, constraint);
        }
        return new ConstrainedEntries<K, V>(entries, constraint);
    }

    private static <K, V> Set<Map.Entry<K, V>> constrainedEntrySet(Set<Map.Entry<K, V>> entries, MapConstraint<? super K, ? super V> constraint) {
        return new ConstrainedEntrySet<K, V>(entries, constraint);
    }

    private static <K, V> Collection<V> checkValues(K key, Iterable<? extends V> values, MapConstraint<? super K, ? super V> constraint) {
        ArrayList copy2 = Lists.newArrayList(values);
        for (Object value : copy2) {
            constraint.checkKeyValue(key, value);
        }
        return copy2;
    }

    private static <K, V> Map<K, V> checkMap(Map<? extends K, ? extends V> map2, MapConstraint<? super K, ? super V> constraint) {
        LinkedHashMap<K, V> copy2 = new LinkedHashMap<K, V>(map2);
        for (Map.Entry entry : copy2.entrySet()) {
            constraint.checkKeyValue(entry.getKey(), entry.getValue());
        }
        return copy2;
    }

    private static class ConstrainedListMultimap<K, V>
    extends ConstrainedMultimap<K, V>
    implements ListMultimap<K, V> {
        ConstrainedListMultimap(ListMultimap<K, V> delegate, MapConstraint<? super K, ? super V> constraint) {
            super(delegate, constraint);
        }

        @Override
        public List<V> get(K key) {
            return (List)super.get(key);
        }

        @Override
        public List<V> removeAll(Object key) {
            return (List)super.removeAll(key);
        }

        @Override
        public List<V> replaceValues(K key, Iterable<? extends V> values) {
            return (List)super.replaceValues(key, values);
        }
    }

    static class ConstrainedAsMapEntries<K, V>
    extends ForwardingSet<Map.Entry<K, Collection<V>>> {
        private final MapConstraint<? super K, ? super V> constraint;
        private final Set<Map.Entry<K, Collection<V>>> entries;

        ConstrainedAsMapEntries(Set<Map.Entry<K, Collection<V>>> entries, MapConstraint<? super K, ? super V> constraint) {
            this.entries = entries;
            this.constraint = constraint;
        }

        @Override
        protected Set<Map.Entry<K, Collection<V>>> delegate() {
            return this.entries;
        }

        @Override
        public Iterator<Map.Entry<K, Collection<V>>> iterator() {
            return new TransformedIterator<Map.Entry<K, Collection<V>>, Map.Entry<K, Collection<V>>>(this.entries.iterator()){

                @Override
                Map.Entry<K, Collection<V>> transform(Map.Entry<K, Collection<V>> from2) {
                    return MapConstraints.constrainedAsMapEntry(from2, ConstrainedAsMapEntries.this.constraint);
                }
            };
        }

        @Override
        public Object[] toArray() {
            return this.standardToArray();
        }

        @Override
        public <T> T[] toArray(T[] array) {
            return this.standardToArray(array);
        }

        @Override
        public boolean contains(Object o) {
            return Maps.containsEntryImpl(this.delegate(), o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return this.standardContainsAll(c);
        }

        @Override
        public boolean equals(@Nullable Object object) {
            return this.standardEquals(object);
        }

        @Override
        public int hashCode() {
            return this.standardHashCode();
        }

        @Override
        public boolean remove(Object o) {
            return Maps.removeEntryImpl(this.delegate(), o);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return this.standardRemoveAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return this.standardRetainAll(c);
        }
    }

    static class ConstrainedEntrySet<K, V>
    extends ConstrainedEntries<K, V>
    implements Set<Map.Entry<K, V>> {
        ConstrainedEntrySet(Set<Map.Entry<K, V>> entries, MapConstraint<? super K, ? super V> constraint) {
            super(entries, constraint);
        }

        @Override
        public boolean equals(@Nullable Object object) {
            return Sets.equalsImpl(this, object);
        }

        @Override
        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }
    }

    private static class ConstrainedEntries<K, V>
    extends ForwardingCollection<Map.Entry<K, V>> {
        final MapConstraint<? super K, ? super V> constraint;
        final Collection<Map.Entry<K, V>> entries;

        ConstrainedEntries(Collection<Map.Entry<K, V>> entries, MapConstraint<? super K, ? super V> constraint) {
            this.entries = entries;
            this.constraint = constraint;
        }

        @Override
        protected Collection<Map.Entry<K, V>> delegate() {
            return this.entries;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new TransformedIterator<Map.Entry<K, V>, Map.Entry<K, V>>(this.entries.iterator()){

                @Override
                Map.Entry<K, V> transform(Map.Entry<K, V> from2) {
                    return MapConstraints.constrainedEntry(from2, ConstrainedEntries.this.constraint);
                }
            };
        }

        @Override
        public Object[] toArray() {
            return this.standardToArray();
        }

        @Override
        public <T> T[] toArray(T[] array) {
            return this.standardToArray(array);
        }

        @Override
        public boolean contains(Object o) {
            return Maps.containsEntryImpl(this.delegate(), o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return this.standardContainsAll(c);
        }

        @Override
        public boolean remove(Object o) {
            return Maps.removeEntryImpl(this.delegate(), o);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return this.standardRemoveAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return this.standardRetainAll(c);
        }
    }

    private static class ConstrainedAsMapValues<K, V>
    extends ForwardingCollection<Collection<V>> {
        final Collection<Collection<V>> delegate;
        final Set<Map.Entry<K, Collection<V>>> entrySet;

        ConstrainedAsMapValues(Collection<Collection<V>> delegate, Set<Map.Entry<K, Collection<V>>> entrySet) {
            this.delegate = delegate;
            this.entrySet = entrySet;
        }

        @Override
        protected Collection<Collection<V>> delegate() {
            return this.delegate;
        }

        @Override
        public Iterator<Collection<V>> iterator() {
            final Iterator<Map.Entry<K, Collection<V>>> iterator2 = this.entrySet.iterator();
            return new Iterator<Collection<V>>(){

                @Override
                public boolean hasNext() {
                    return iterator2.hasNext();
                }

                @Override
                public Collection<V> next() {
                    return (Collection)((Map.Entry)iterator2.next()).getValue();
                }

                @Override
                public void remove() {
                    iterator2.remove();
                }
            };
        }

        @Override
        public Object[] toArray() {
            return this.standardToArray();
        }

        @Override
        public <T> T[] toArray(T[] array) {
            return this.standardToArray(array);
        }

        @Override
        public boolean contains(Object o) {
            return this.standardContains(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return this.standardContainsAll(c);
        }

        @Override
        public boolean remove(Object o) {
            return this.standardRemove(o);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return this.standardRemoveAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return this.standardRetainAll(c);
        }
    }

    private static class ConstrainedMultimap<K, V>
    extends ForwardingMultimap<K, V>
    implements Serializable {
        final MapConstraint<? super K, ? super V> constraint;
        final Multimap<K, V> delegate;
        transient Collection<Map.Entry<K, V>> entries;
        transient Map<K, Collection<V>> asMap;

        public ConstrainedMultimap(Multimap<K, V> delegate, MapConstraint<? super K, ? super V> constraint) {
            this.delegate = Preconditions.checkNotNull(delegate);
            this.constraint = Preconditions.checkNotNull(constraint);
        }

        @Override
        protected Multimap<K, V> delegate() {
            return this.delegate;
        }

        @Override
        public Map<K, Collection<V>> asMap() {
            class AsMap
            extends ForwardingMap<K, Collection<V>> {
                Set<Map.Entry<K, Collection<V>>> entrySet;
                Collection<Collection<V>> values;

                AsMap() {
                }

                @Override
                protected Map<K, Collection<V>> delegate() {
                    return asMapDelegate;
                }

                @Override
                public Set<Map.Entry<K, Collection<V>>> entrySet() {
                    Set result2 = this.entrySet;
                    if (result2 == null) {
                        this.entrySet = result2 = MapConstraints.constrainedAsMapEntries(asMapDelegate.entrySet(), ConstrainedMultimap.this.constraint);
                    }
                    return result2;
                }

                @Override
                public Collection<V> get(Object key) {
                    try {
                        Collection collection = ConstrainedMultimap.this.get(key);
                        return collection.isEmpty() ? null : collection;
                    }
                    catch (ClassCastException e) {
                        return null;
                    }
                }

                @Override
                public Collection<Collection<V>> values() {
                    Collection result2 = this.values;
                    if (result2 == null) {
                        this.values = result2 = new ConstrainedAsMapValues(this.delegate().values(), this.entrySet());
                    }
                    return result2;
                }

                @Override
                public boolean containsValue(Object o) {
                    return this.values().contains(o);
                }
            }
            AsMap result2 = this.asMap;
            if (result2 == null) {
                final Map<K, Collection<V>> asMapDelegate = this.delegate.asMap();
                this.asMap = result2 = new AsMap();
            }
            return result2;
        }

        @Override
        public Collection<Map.Entry<K, V>> entries() {
            Collection result2 = this.entries;
            if (result2 == null) {
                this.entries = result2 = MapConstraints.constrainedEntries(this.delegate.entries(), this.constraint);
            }
            return result2;
        }

        @Override
        public Collection<V> get(final K key) {
            return Constraints.constrainedTypePreservingCollection(this.delegate.get(key), new Constraint<V>(){

                @Override
                public V checkElement(V value) {
                    ConstrainedMultimap.this.constraint.checkKeyValue(key, value);
                    return value;
                }
            });
        }

        @Override
        public boolean put(K key, V value) {
            this.constraint.checkKeyValue(key, value);
            return this.delegate.put(key, value);
        }

        @Override
        public boolean putAll(K key, Iterable<? extends V> values) {
            return this.delegate.putAll(key, MapConstraints.checkValues(key, values, this.constraint));
        }

        @Override
        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            boolean changed = false;
            for (Map.Entry<K, V> entry : multimap.entries()) {
                changed |= this.put(entry.getKey(), entry.getValue());
            }
            return changed;
        }

        @Override
        public Collection<V> replaceValues(K key, Iterable<? extends V> values) {
            return this.delegate.replaceValues(key, MapConstraints.checkValues(key, values, this.constraint));
        }
    }

    static class ConstrainedMap<K, V>
    extends ForwardingMap<K, V> {
        private final Map<K, V> delegate;
        final MapConstraint<? super K, ? super V> constraint;
        private transient Set<Map.Entry<K, V>> entrySet;

        ConstrainedMap(Map<K, V> delegate, MapConstraint<? super K, ? super V> constraint) {
            this.delegate = Preconditions.checkNotNull(delegate);
            this.constraint = Preconditions.checkNotNull(constraint);
        }

        @Override
        protected Map<K, V> delegate() {
            return this.delegate;
        }

        @Override
        public Set<Map.Entry<K, V>> entrySet() {
            Set result2 = this.entrySet;
            if (result2 == null) {
                this.entrySet = result2 = MapConstraints.constrainedEntrySet(this.delegate.entrySet(), this.constraint);
            }
            return result2;
        }

        @Override
        @CanIgnoreReturnValue
        public V put(K key, V value) {
            this.constraint.checkKeyValue(key, value);
            return this.delegate.put(key, value);
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> map2) {
            this.delegate.putAll(MapConstraints.checkMap(map2, this.constraint));
        }
    }
}

