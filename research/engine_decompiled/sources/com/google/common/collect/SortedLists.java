/*
 * Decompiled with CFR 0.152.
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import javax.annotation.Nullable;

@GwtCompatible
@Beta
final class SortedLists {
    private SortedLists() {
    }

    public static <E extends Comparable> int binarySearch(List<? extends E> list2, E e, KeyPresentBehavior presentBehavior, KeyAbsentBehavior absentBehavior) {
        Preconditions.checkNotNull(e);
        return SortedLists.binarySearch(list2, e, Ordering.natural(), presentBehavior, absentBehavior);
    }

    public static <E, K extends Comparable> int binarySearch(List<E> list2, Function<? super E, K> keyFunction, @Nullable K key, KeyPresentBehavior presentBehavior, KeyAbsentBehavior absentBehavior) {
        return SortedLists.binarySearch(list2, keyFunction, key, Ordering.natural(), presentBehavior, absentBehavior);
    }

    public static <E, K> int binarySearch(List<E> list2, Function<? super E, K> keyFunction, @Nullable K key, Comparator<? super K> keyComparator, KeyPresentBehavior presentBehavior, KeyAbsentBehavior absentBehavior) {
        return SortedLists.binarySearch(Lists.transform(list2, keyFunction), key, keyComparator, presentBehavior, absentBehavior);
    }

    public static <E> int binarySearch(List<? extends E> list2, @Nullable E key, Comparator<? super E> comparator, KeyPresentBehavior presentBehavior, KeyAbsentBehavior absentBehavior) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(list2);
        Preconditions.checkNotNull(presentBehavior);
        Preconditions.checkNotNull(absentBehavior);
        if (!(list2 instanceof RandomAccess)) {
            list2 = Lists.newArrayList(list2);
        }
        int lower = 0;
        int upper = list2.size() - 1;
        while (lower <= upper) {
            int middle = lower + upper >>> 1;
            int c = comparator.compare(key, list2.get(middle));
            if (c < 0) {
                upper = middle - 1;
                continue;
            }
            if (c > 0) {
                lower = middle + 1;
                continue;
            }
            return lower + presentBehavior.resultIndex(comparator, key, list2.subList(lower, upper + 1), middle - lower);
        }
        return absentBehavior.resultIndex(lower);
    }

    public static enum KeyAbsentBehavior {
        NEXT_LOWER{

            @Override
            int resultIndex(int higherIndex) {
                return higherIndex - 1;
            }
        }
        ,
        NEXT_HIGHER{

            @Override
            public int resultIndex(int higherIndex) {
                return higherIndex;
            }
        }
        ,
        INVERTED_INSERTION_INDEX{

            @Override
            public int resultIndex(int higherIndex) {
                return ~higherIndex;
            }
        };


        abstract int resultIndex(int var1);
    }

    public static enum KeyPresentBehavior {
        ANY_PRESENT{

            @Override
            <E> int resultIndex(Comparator<? super E> comparator, E key, List<? extends E> list2, int foundIndex) {
                return foundIndex;
            }
        }
        ,
        LAST_PRESENT{

            @Override
            <E> int resultIndex(Comparator<? super E> comparator, E key, List<? extends E> list2, int foundIndex) {
                int lower = foundIndex;
                int upper = list2.size() - 1;
                while (lower < upper) {
                    int middle = lower + upper + 1 >>> 1;
                    int c = comparator.compare(list2.get(middle), key);
                    if (c > 0) {
                        upper = middle - 1;
                        continue;
                    }
                    lower = middle;
                }
                return lower;
            }
        }
        ,
        FIRST_PRESENT{

            @Override
            <E> int resultIndex(Comparator<? super E> comparator, E key, List<? extends E> list2, int foundIndex) {
                int lower = 0;
                int upper = foundIndex;
                while (lower < upper) {
                    int middle = lower + upper >>> 1;
                    int c = comparator.compare(list2.get(middle), key);
                    if (c < 0) {
                        lower = middle + 1;
                        continue;
                    }
                    upper = middle;
                }
                return lower;
            }
        }
        ,
        FIRST_AFTER{

            @Override
            public <E> int resultIndex(Comparator<? super E> comparator, E key, List<? extends E> list2, int foundIndex) {
                return LAST_PRESENT.resultIndex(comparator, key, list2, foundIndex) + 1;
            }
        }
        ,
        LAST_BEFORE{

            @Override
            public <E> int resultIndex(Comparator<? super E> comparator, E key, List<? extends E> list2, int foundIndex) {
                return FIRST_PRESENT.resultIndex(comparator, key, list2, foundIndex) - 1;
            }
        };


        abstract <E> int resultIndex(Comparator<? super E> var1, E var2, List<? extends E> var3, int var4);
    }
}

