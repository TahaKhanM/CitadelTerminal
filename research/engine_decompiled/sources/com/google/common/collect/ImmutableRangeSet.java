/*
 * Decompiled with CFR 0.152.
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.AbstractRangeSet;
import com.google.common.collect.BoundType;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.Cut;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Ordering;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.RegularImmutableSortedSet;
import com.google.common.collect.SortedLists;
import com.google.common.collect.TreeRangeSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

@Beta
@GwtIncompatible
public final class ImmutableRangeSet<C extends Comparable>
extends AbstractRangeSet<C>
implements Serializable {
    private static final ImmutableRangeSet<Comparable<?>> EMPTY = new ImmutableRangeSet(ImmutableList.of());
    private static final ImmutableRangeSet<Comparable<?>> ALL = new ImmutableRangeSet(ImmutableList.of(Range.all()));
    private final transient ImmutableList<Range<C>> ranges;
    @LazyInit
    private transient ImmutableRangeSet<C> complement;

    public static <C extends Comparable> ImmutableRangeSet<C> of() {
        return EMPTY;
    }

    static <C extends Comparable> ImmutableRangeSet<C> all() {
        return ALL;
    }

    public static <C extends Comparable> ImmutableRangeSet<C> of(Range<C> range2) {
        Preconditions.checkNotNull(range2);
        if (range2.isEmpty()) {
            return ImmutableRangeSet.of();
        }
        if (range2.equals(Range.all())) {
            return ImmutableRangeSet.all();
        }
        return new ImmutableRangeSet<C>(ImmutableList.of(range2));
    }

    public static <C extends Comparable> ImmutableRangeSet<C> copyOf(RangeSet<C> rangeSet) {
        ImmutableRangeSet immutableRangeSet;
        Preconditions.checkNotNull(rangeSet);
        if (rangeSet.isEmpty()) {
            return ImmutableRangeSet.of();
        }
        if (rangeSet.encloses(Range.all())) {
            return ImmutableRangeSet.all();
        }
        if (rangeSet instanceof ImmutableRangeSet && !(immutableRangeSet = (ImmutableRangeSet)rangeSet).isPartialView()) {
            return immutableRangeSet;
        }
        return new ImmutableRangeSet<C>(ImmutableList.copyOf(rangeSet.asRanges()));
    }

    ImmutableRangeSet(ImmutableList<Range<C>> ranges) {
        this.ranges = ranges;
    }

    private ImmutableRangeSet(ImmutableList<Range<C>> ranges, ImmutableRangeSet<C> complement) {
        this.ranges = ranges;
        this.complement = complement;
    }

    @Override
    public boolean intersects(Range<C> otherRange) {
        int ceilingIndex = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), otherRange.lowerBound, Ordering.natural(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        if (ceilingIndex < this.ranges.size() && ((Range)this.ranges.get(ceilingIndex)).isConnected(otherRange) && !((Range)this.ranges.get(ceilingIndex)).intersection(otherRange).isEmpty()) {
            return true;
        }
        return ceilingIndex > 0 && ((Range)this.ranges.get(ceilingIndex - 1)).isConnected(otherRange) && !((Range)this.ranges.get(ceilingIndex - 1)).intersection(otherRange).isEmpty();
    }

    @Override
    public boolean encloses(Range<C> otherRange) {
        int index = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), otherRange.lowerBound, Ordering.natural(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        return index != -1 && ((Range)this.ranges.get(index)).encloses(otherRange);
    }

    @Override
    public Range<C> rangeContaining(C value) {
        int index = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), Cut.belowValue(value), Ordering.natural(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (index != -1) {
            Range range2 = (Range)this.ranges.get(index);
            return range2.contains(value) ? range2 : null;
        }
        return null;
    }

    @Override
    public Range<C> span() {
        if (this.ranges.isEmpty()) {
            throw new NoSuchElementException();
        }
        return Range.create(((Range)this.ranges.get((int)0)).lowerBound, ((Range)this.ranges.get((int)(this.ranges.size() - 1))).upperBound);
    }

    @Override
    public boolean isEmpty() {
        return this.ranges.isEmpty();
    }

    @Override
    @Deprecated
    public void add(Range<C> range2) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void addAll(RangeSet<C> other) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void remove(Range<C> range2) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void removeAll(RangeSet<C> other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ImmutableSet<Range<C>> asRanges() {
        if (this.ranges.isEmpty()) {
            return ImmutableSet.of();
        }
        return new RegularImmutableSortedSet<Range<C>>(this.ranges, Range.RANGE_LEX_ORDERING);
    }

    @Override
    public ImmutableSet<Range<C>> asDescendingSetOfRanges() {
        if (this.ranges.isEmpty()) {
            return ImmutableSet.of();
        }
        return new RegularImmutableSortedSet<Range<C>>(this.ranges.reverse(), Range.RANGE_LEX_ORDERING.reverse());
    }

    @Override
    public ImmutableRangeSet<C> complement() {
        ImmutableRangeSet<C> result2 = this.complement;
        if (result2 != null) {
            return result2;
        }
        if (this.ranges.isEmpty()) {
            this.complement = ImmutableRangeSet.all();
            return this.complement;
        }
        if (this.ranges.size() == 1 && ((Range)this.ranges.get(0)).equals(Range.all())) {
            this.complement = ImmutableRangeSet.of();
            return this.complement;
        }
        ComplementRanges complementRanges = new ComplementRanges();
        result2 = this.complement = new ImmutableRangeSet<C>(complementRanges, this);
        return result2;
    }

    private ImmutableList<Range<C>> intersectRanges(final Range<C> range2) {
        if (this.ranges.isEmpty() || range2.isEmpty()) {
            return ImmutableList.of();
        }
        if (range2.encloses(this.span())) {
            return this.ranges;
        }
        final int fromIndex = range2.hasLowerBound() ? SortedLists.binarySearch(this.ranges, Range.upperBoundFn(), range2.lowerBound, SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER) : 0;
        int toIndex = range2.hasUpperBound() ? SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), range2.upperBound, SortedLists.KeyPresentBehavior.FIRST_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER) : this.ranges.size();
        final int length = toIndex - fromIndex;
        if (length == 0) {
            return ImmutableList.of();
        }
        return new ImmutableList<Range<C>>(){

            @Override
            public int size() {
                return length;
            }

            @Override
            public Range<C> get(int index) {
                Preconditions.checkElementIndex(index, length);
                if (index == 0 || index == length - 1) {
                    return ((Range)ImmutableRangeSet.this.ranges.get(index + fromIndex)).intersection(range2);
                }
                return (Range)ImmutableRangeSet.this.ranges.get(index + fromIndex);
            }

            @Override
            boolean isPartialView() {
                return true;
            }
        };
    }

    @Override
    public ImmutableRangeSet<C> subRangeSet(Range<C> range2) {
        if (!this.isEmpty()) {
            Range<C> span2 = this.span();
            if (range2.encloses(span2)) {
                return this;
            }
            if (range2.isConnected(span2)) {
                return new ImmutableRangeSet<C>(this.intersectRanges(range2));
            }
        }
        return ImmutableRangeSet.of();
    }

    public ImmutableSortedSet<C> asSet(DiscreteDomain<C> domain) {
        Preconditions.checkNotNull(domain);
        if (this.isEmpty()) {
            return ImmutableSortedSet.of();
        }
        Range<C> span2 = this.span().canonical(domain);
        if (!span2.hasLowerBound()) {
            throw new IllegalArgumentException("Neither the DiscreteDomain nor this range set are bounded below");
        }
        if (!span2.hasUpperBound()) {
            try {
                domain.maxValue();
            }
            catch (NoSuchElementException e) {
                throw new IllegalArgumentException("Neither the DiscreteDomain nor this range set are bounded above");
            }
        }
        return new AsSet(domain);
    }

    boolean isPartialView() {
        return this.ranges.isPartialView();
    }

    public static <C extends Comparable<?>> Builder<C> builder() {
        return new Builder();
    }

    Object writeReplace() {
        return new SerializedForm<C>(this.ranges);
    }

    private static final class SerializedForm<C extends Comparable>
    implements Serializable {
        private final ImmutableList<Range<C>> ranges;

        SerializedForm(ImmutableList<Range<C>> ranges) {
            this.ranges = ranges;
        }

        Object readResolve() {
            if (this.ranges.isEmpty()) {
                return ImmutableRangeSet.of();
            }
            if (this.ranges.equals(ImmutableList.of(Range.all()))) {
                return ImmutableRangeSet.all();
            }
            return new ImmutableRangeSet<C>(this.ranges);
        }
    }

    public static class Builder<C extends Comparable<?>> {
        private final RangeSet<C> rangeSet = TreeRangeSet.create();

        @CanIgnoreReturnValue
        public Builder<C> add(Range<C> range2) {
            if (range2.isEmpty()) {
                throw new IllegalArgumentException("range must not be empty, but was " + range2);
            }
            if (!this.rangeSet.complement().encloses(range2)) {
                for (Range<C> currentRange : this.rangeSet.asRanges()) {
                    Preconditions.checkArgument(!currentRange.isConnected(range2) || currentRange.intersection(range2).isEmpty(), "Ranges may not overlap, but received %s and %s", currentRange, range2);
                }
                throw new AssertionError((Object)"should have thrown an IAE above");
            }
            this.rangeSet.add(range2);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<C> addAll(RangeSet<C> ranges) {
            for (Range<C> range2 : ranges.asRanges()) {
                this.add(range2);
            }
            return this;
        }

        public ImmutableRangeSet<C> build() {
            return ImmutableRangeSet.copyOf(this.rangeSet);
        }
    }

    private static class AsSetSerializedForm<C extends Comparable>
    implements Serializable {
        private final ImmutableList<Range<C>> ranges;
        private final DiscreteDomain<C> domain;

        AsSetSerializedForm(ImmutableList<Range<C>> ranges, DiscreteDomain<C> domain) {
            this.ranges = ranges;
            this.domain = domain;
        }

        Object readResolve() {
            return new ImmutableRangeSet<C>(this.ranges).asSet(this.domain);
        }
    }

    private final class AsSet
    extends ImmutableSortedSet<C> {
        private final DiscreteDomain<C> domain;
        private transient Integer size;

        AsSet(DiscreteDomain<C> domain) {
            super(Ordering.natural());
            this.domain = domain;
        }

        @Override
        public int size() {
            Integer result2 = this.size;
            if (result2 == null) {
                Range range2;
                long total2 = 0L;
                Iterator i$ = ImmutableRangeSet.this.ranges.iterator();
                while (i$.hasNext() && (total2 += (long)ContiguousSet.create(range2 = (Range)i$.next(), this.domain).size()) < Integer.MAX_VALUE) {
                }
                result2 = this.size = Integer.valueOf(Ints.saturatedCast(total2));
            }
            return result2;
        }

        @Override
        public UnmodifiableIterator<C> iterator() {
            return new AbstractIterator<C>(){
                final Iterator<Range<C>> rangeItr;
                Iterator<C> elemItr;
                {
                    this.rangeItr = ImmutableRangeSet.this.ranges.iterator();
                    this.elemItr = Iterators.emptyIterator();
                }

                @Override
                protected C computeNext() {
                    while (!this.elemItr.hasNext()) {
                        if (this.rangeItr.hasNext()) {
                            this.elemItr = ContiguousSet.create(this.rangeItr.next(), AsSet.this.domain).iterator();
                            continue;
                        }
                        return (Comparable)this.endOfData();
                    }
                    return (Comparable)this.elemItr.next();
                }
            };
        }

        @Override
        @GwtIncompatible(value="NavigableSet")
        public UnmodifiableIterator<C> descendingIterator() {
            return new AbstractIterator<C>(){
                final Iterator<Range<C>> rangeItr;
                Iterator<C> elemItr;
                {
                    this.rangeItr = ImmutableRangeSet.this.ranges.reverse().iterator();
                    this.elemItr = Iterators.emptyIterator();
                }

                @Override
                protected C computeNext() {
                    while (!this.elemItr.hasNext()) {
                        if (this.rangeItr.hasNext()) {
                            this.elemItr = ContiguousSet.create(this.rangeItr.next(), AsSet.this.domain).descendingIterator();
                            continue;
                        }
                        return (Comparable)this.endOfData();
                    }
                    return (Comparable)this.elemItr.next();
                }
            };
        }

        ImmutableSortedSet<C> subSet(Range<C> range2) {
            return ((ImmutableRangeSet)ImmutableRangeSet.this.subRangeSet(range2)).asSet(this.domain);
        }

        @Override
        ImmutableSortedSet<C> headSetImpl(C toElement, boolean inclusive2) {
            return this.subSet(Range.upTo(toElement, BoundType.forBoolean(inclusive2)));
        }

        @Override
        ImmutableSortedSet<C> subSetImpl(C fromElement, boolean fromInclusive, C toElement, boolean toInclusive) {
            if (!fromInclusive && !toInclusive && Range.compareOrThrow(fromElement, toElement) == 0) {
                return ImmutableSortedSet.of();
            }
            return this.subSet(Range.range(fromElement, BoundType.forBoolean(fromInclusive), toElement, BoundType.forBoolean(toInclusive)));
        }

        @Override
        ImmutableSortedSet<C> tailSetImpl(C fromElement, boolean inclusive2) {
            return this.subSet(Range.downTo(fromElement, BoundType.forBoolean(inclusive2)));
        }

        @Override
        public boolean contains(@Nullable Object o) {
            if (o == null) {
                return false;
            }
            try {
                Comparable c = (Comparable)o;
                return ImmutableRangeSet.this.contains(c);
            }
            catch (ClassCastException e) {
                return false;
            }
        }

        @Override
        int indexOf(Object target) {
            if (this.contains(target)) {
                Comparable c = (Comparable)target;
                long total2 = 0L;
                for (Range range2 : ImmutableRangeSet.this.ranges) {
                    if (range2.contains(c)) {
                        return Ints.saturatedCast(total2 + (long)ContiguousSet.create(range2, this.domain).indexOf(c));
                    }
                    total2 += (long)ContiguousSet.create(range2, this.domain).size();
                }
                throw new AssertionError((Object)"impossible");
            }
            return -1;
        }

        @Override
        boolean isPartialView() {
            return ImmutableRangeSet.this.ranges.isPartialView();
        }

        @Override
        public String toString() {
            return ImmutableRangeSet.this.ranges.toString();
        }

        @Override
        Object writeReplace() {
            return new AsSetSerializedForm(ImmutableRangeSet.this.ranges, this.domain);
        }
    }

    private final class ComplementRanges
    extends ImmutableList<Range<C>> {
        private final boolean positiveBoundedBelow;
        private final boolean positiveBoundedAbove;
        private final int size;

        ComplementRanges() {
            this.positiveBoundedBelow = ((Range)ImmutableRangeSet.this.ranges.get(0)).hasLowerBound();
            this.positiveBoundedAbove = ((Range)Iterables.getLast(ImmutableRangeSet.this.ranges)).hasUpperBound();
            int size2 = ImmutableRangeSet.this.ranges.size() - 1;
            if (this.positiveBoundedBelow) {
                ++size2;
            }
            if (this.positiveBoundedAbove) {
                ++size2;
            }
            this.size = size2;
        }

        @Override
        public int size() {
            return this.size;
        }

        @Override
        public Range<C> get(int index) {
            Preconditions.checkElementIndex(index, this.size);
            Cut lowerBound = this.positiveBoundedBelow ? (index == 0 ? Cut.belowAll() : ((Range)((ImmutableRangeSet)ImmutableRangeSet.this).ranges.get((int)(index - 1))).upperBound) : ((Range)((ImmutableRangeSet)ImmutableRangeSet.this).ranges.get((int)index)).upperBound;
            Cut upperBound = this.positiveBoundedAbove && index == this.size - 1 ? Cut.aboveAll() : ((Range)((ImmutableRangeSet)ImmutableRangeSet.this).ranges.get((int)(index + (this.positiveBoundedBelow ? 0 : 1)))).lowerBound;
            return Range.create(lowerBound, upperBound);
        }

        @Override
        boolean isPartialView() {
            return true;
        }
    }
}

