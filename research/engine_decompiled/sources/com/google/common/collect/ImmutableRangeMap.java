/*
 * Decompiled with CFR 0.152.
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Cut;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.RangeSet;
import com.google.common.collect.RegularImmutableSortedSet;
import com.google.common.collect.SortedLists;
import com.google.common.collect.TreeRangeMap;
import com.google.common.collect.TreeRangeSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

@Beta
@GwtIncompatible
public class ImmutableRangeMap<K extends Comparable<?>, V>
implements RangeMap<K, V>,
Serializable {
    private static final ImmutableRangeMap<Comparable<?>, Object> EMPTY = new ImmutableRangeMap(ImmutableList.of(), ImmutableList.of());
    private final transient ImmutableList<Range<K>> ranges;
    private final transient ImmutableList<V> values;
    private static final long serialVersionUID = 0L;

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> of() {
        return EMPTY;
    }

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> of(Range<K> range2, V value) {
        return new ImmutableRangeMap<K, V>(ImmutableList.of(range2), ImmutableList.of(value));
    }

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> copyOf(RangeMap<K, ? extends V> rangeMap) {
        if (rangeMap instanceof ImmutableRangeMap) {
            return (ImmutableRangeMap)rangeMap;
        }
        Map<Range<K>, V> map2 = rangeMap.asMapOfRanges();
        ImmutableList.Builder rangesBuilder = new ImmutableList.Builder(map2.size());
        ImmutableList.Builder valuesBuilder = new ImmutableList.Builder(map2.size());
        for (Map.Entry<Range<K>, V> entry : map2.entrySet()) {
            rangesBuilder.add(entry.getKey());
            valuesBuilder.add(entry.getValue());
        }
        return new ImmutableRangeMap<K, V>(rangesBuilder.build(), valuesBuilder.build());
    }

    public static <K extends Comparable<?>, V> Builder<K, V> builder() {
        return new Builder();
    }

    ImmutableRangeMap(ImmutableList<Range<K>> ranges, ImmutableList<V> values) {
        this.ranges = ranges;
        this.values = values;
    }

    @Override
    @Nullable
    public V get(K key) {
        int index = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), Cut.belowValue(key), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (index == -1) {
            return null;
        }
        Range range2 = (Range)this.ranges.get(index);
        return range2.contains(key) ? (V)this.values.get(index) : null;
    }

    @Override
    @Nullable
    public Map.Entry<Range<K>, V> getEntry(K key) {
        int index = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), Cut.belowValue(key), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (index == -1) {
            return null;
        }
        Range range2 = (Range)this.ranges.get(index);
        return range2.contains(key) ? Maps.immutableEntry(range2, this.values.get(index)) : null;
    }

    @Override
    public Range<K> span() {
        if (this.ranges.isEmpty()) {
            throw new NoSuchElementException();
        }
        Range firstRange = (Range)this.ranges.get(0);
        Range lastRange = (Range)this.ranges.get(this.ranges.size() - 1);
        return Range.create(firstRange.lowerBound, lastRange.upperBound);
    }

    @Override
    @Deprecated
    public void put(Range<K> range2, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void putAll(RangeMap<K, V> rangeMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void remove(Range<K> range2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ImmutableMap<Range<K>, V> asMapOfRanges() {
        if (this.ranges.isEmpty()) {
            return ImmutableMap.of();
        }
        RegularImmutableSortedSet rangeSet = new RegularImmutableSortedSet(this.ranges, Range.RANGE_LEX_ORDERING);
        return new ImmutableSortedMap(rangeSet, this.values);
    }

    @Override
    public ImmutableMap<Range<K>, V> asDescendingMapOfRanges() {
        if (this.ranges.isEmpty()) {
            return ImmutableMap.of();
        }
        RegularImmutableSortedSet<Range<K>> rangeSet = new RegularImmutableSortedSet<Range<K>>(this.ranges.reverse(), Range.RANGE_LEX_ORDERING.reverse());
        return new ImmutableSortedMap<Range<K>, V>(rangeSet, this.values.reverse());
    }

    @Override
    public ImmutableRangeMap<K, V> subRangeMap(final Range<K> range2) {
        int upperIndex;
        if (Preconditions.checkNotNull(range2).isEmpty()) {
            return ImmutableRangeMap.of();
        }
        if (this.ranges.isEmpty() || range2.encloses(this.span())) {
            return this;
        }
        int lowerIndex = SortedLists.binarySearch(this.ranges, Range.upperBoundFn(), range2.lowerBound, SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        if (lowerIndex >= (upperIndex = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), range2.upperBound, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER))) {
            return ImmutableRangeMap.of();
        }
        final int off = lowerIndex;
        final int len = upperIndex - lowerIndex;
        ImmutableList subRanges = new ImmutableList<Range<K>>(){

            @Override
            public int size() {
                return len;
            }

            @Override
            public Range<K> get(int index) {
                Preconditions.checkElementIndex(index, len);
                if (index == 0 || index == len - 1) {
                    return ((Range)ImmutableRangeMap.this.ranges.get(index + off)).intersection(range2);
                }
                return (Range)ImmutableRangeMap.this.ranges.get(index + off);
            }

            @Override
            boolean isPartialView() {
                return true;
            }
        };
        final ImmutableRangeMap outer = this;
        return new ImmutableRangeMap<K, V>(subRanges, (ImmutableList)this.values.subList(lowerIndex, upperIndex)){

            @Override
            public ImmutableRangeMap<K, V> subRangeMap(Range<K> subRange) {
                if (range2.isConnected(subRange)) {
                    return outer.subRangeMap(subRange.intersection(range2));
                }
                return ImmutableRangeMap.of();
            }
        };
    }

    @Override
    public int hashCode() {
        return ((ImmutableMap)this.asMapOfRanges()).hashCode();
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (o instanceof RangeMap) {
            RangeMap rangeMap = (RangeMap)o;
            return ((ImmutableMap)this.asMapOfRanges()).equals(rangeMap.asMapOfRanges());
        }
        return false;
    }

    @Override
    public String toString() {
        return ((ImmutableMap)this.asMapOfRanges()).toString();
    }

    Object writeReplace() {
        return new SerializedForm(this.asMapOfRanges());
    }

    private static class SerializedForm<K extends Comparable<?>, V>
    implements Serializable {
        private final ImmutableMap<Range<K>, V> mapOfRanges;
        private static final long serialVersionUID = 0L;

        SerializedForm(ImmutableMap<Range<K>, V> mapOfRanges) {
            this.mapOfRanges = mapOfRanges;
        }

        Object readResolve() {
            if (this.mapOfRanges.isEmpty()) {
                return ImmutableRangeMap.of();
            }
            return this.createRangeMap();
        }

        Object createRangeMap() {
            Builder builder = new Builder();
            for (Map.Entry entry : this.mapOfRanges.entrySet()) {
                builder.put((Range)entry.getKey(), entry.getValue());
            }
            return builder.build();
        }
    }

    public static final class Builder<K extends Comparable<?>, V> {
        private final RangeSet<K> keyRanges = TreeRangeSet.create();
        private final RangeMap<K, V> rangeMap = TreeRangeMap.create();

        @CanIgnoreReturnValue
        public Builder<K, V> put(Range<K> range2, V value) {
            Preconditions.checkNotNull(range2);
            Preconditions.checkNotNull(value);
            Preconditions.checkArgument(!range2.isEmpty(), "Range must not be empty, but was %s", range2);
            if (!this.keyRanges.complement().encloses(range2)) {
                for (Map.Entry<Range<K>, V> entry : this.rangeMap.asMapOfRanges().entrySet()) {
                    Range<K> key = entry.getKey();
                    if (!key.isConnected(range2) || key.intersection(range2).isEmpty()) continue;
                    throw new IllegalArgumentException("Overlapping ranges: range " + range2 + " overlaps with entry " + entry);
                }
            }
            this.keyRanges.add(range2);
            this.rangeMap.put(range2, value);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(RangeMap<K, ? extends V> rangeMap) {
            for (Map.Entry<Range<K>, V> entry : rangeMap.asMapOfRanges().entrySet()) {
                this.put(entry.getKey(), entry.getValue());
            }
            return this;
        }

        public ImmutableRangeMap<K, V> build() {
            Map<Range<K>, V> map2 = this.rangeMap.asMapOfRanges();
            ImmutableList.Builder rangesBuilder = new ImmutableList.Builder(map2.size());
            ImmutableList.Builder valuesBuilder = new ImmutableList.Builder(map2.size());
            for (Map.Entry<Range<K>, V> entry : map2.entrySet()) {
                rangesBuilder.add(entry.getKey());
                valuesBuilder.add(entry.getValue());
            }
            return new ImmutableRangeMap(rangesBuilder.build(), valuesBuilder.build());
        }
    }
}

