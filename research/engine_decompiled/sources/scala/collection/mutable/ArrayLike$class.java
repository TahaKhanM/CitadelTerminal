/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.IndexedSeq;
import scala.collection.IndexedSeq$class;
import scala.collection.IndexedSeqLike$class;
import scala.collection.Iterator;
import scala.collection.generic.GenericCompanion;
import scala.collection.mutable.ArrayLike;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.WrappedArray$;

public abstract class ArrayLike$class {
    public static IndexedSeq deep(ArrayLike $this) {
        return new IndexedSeq<Object>($this){
            private final /* synthetic */ ArrayLike $outer;

            public GenericCompanion<IndexedSeq> companion() {
                return IndexedSeq$class.companion(this);
            }

            public IndexedSeq<Object> seq() {
                return IndexedSeq$class.seq(this);
            }

            public int hashCode() {
                return IndexedSeqLike$class.hashCode(this);
            }

            public IndexedSeq<Object> thisCollection() {
                return IndexedSeqLike$class.thisCollection(this);
            }

            public IndexedSeq toCollection(Object repr) {
                return IndexedSeqLike$class.toCollection(this, repr);
            }

            public Iterator<Object> iterator() {
                return IndexedSeqLike$class.iterator(this);
            }

            public <A1> Buffer<A1> toBuffer() {
                return IndexedSeqLike$class.toBuffer(this);
            }

            public int length() {
                return this.$outer.length();
            }

            public Object apply(int idx) {
                A a = this.$outer.apply(idx);
                Object object = a instanceof Object && a.getClass().isArray() ? WrappedArray$.MODULE$.make(a).deep() : a;
                return object;
            }

            public String stringPrefix() {
                return "Array";
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                IndexedSeqLike$class.$init$(this);
                IndexedSeq$class.$init$(this);
            }
        };
    }

    public static void $init$(ArrayLike $this) {
    }
}

