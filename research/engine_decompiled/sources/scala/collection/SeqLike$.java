/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.IndexedSeq;
import scala.collection.IndexedSeq$class;
import scala.collection.IndexedSeqLike$class;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.generic.GenericCompanion;
import scala.collection.mutable.Buffer;
import scala.math.package$;
import scala.runtime.BoxesRunTime;

public final class SeqLike$ {
    public static final SeqLike$ MODULE$;

    static {
        new SeqLike$();
    }

    private <B> IndexedSeq<B> kmpOptimizeWord(Seq<B> W, int n0, int n1, boolean forward) {
        IndexedSeq<Object> indexedSeq;
        if (W instanceof IndexedSeq) {
            IndexedSeq indexedSeq2 = (IndexedSeq)W;
            indexedSeq = forward && n0 == 0 && n1 == W.length() ? indexedSeq2 : (forward ? new IndexedSeq<B>(n0, n1, indexedSeq2){
                private final int length;
                private final int n0$1;
                private final IndexedSeq x2$1;

                public GenericCompanion<IndexedSeq> companion() {
                    return IndexedSeq$class.companion(this);
                }

                public IndexedSeq<B> seq() {
                    return IndexedSeq$class.seq(this);
                }

                public int hashCode() {
                    return IndexedSeqLike$class.hashCode(this);
                }

                public IndexedSeq<B> thisCollection() {
                    return IndexedSeqLike$class.thisCollection(this);
                }

                public IndexedSeq toCollection(Object repr) {
                    return IndexedSeqLike$class.toCollection(this, repr);
                }

                public Iterator<B> iterator() {
                    return IndexedSeqLike$class.iterator(this);
                }

                public <A1> Buffer<A1> toBuffer() {
                    return IndexedSeqLike$class.toBuffer(this);
                }

                public int length() {
                    return this.length;
                }

                public B apply(int x) {
                    return (B)this.x2$1.apply(this.n0$1 + x);
                }
                {
                    this.n0$1 = n0$1;
                    this.x2$1 = x2$1;
                    IndexedSeqLike$class.$init$(this);
                    IndexedSeq$class.$init$(this);
                    this.length = n1$1 - n0$1;
                }
            } : new IndexedSeq<B>(n0, n1, indexedSeq2){
                private final int n0$1;
                private final int n1$1;
                private final IndexedSeq x2$1;

                public GenericCompanion<IndexedSeq> companion() {
                    return IndexedSeq$class.companion(this);
                }

                public IndexedSeq<B> seq() {
                    return IndexedSeq$class.seq(this);
                }

                public int hashCode() {
                    return IndexedSeqLike$class.hashCode(this);
                }

                public IndexedSeq<B> thisCollection() {
                    return IndexedSeqLike$class.thisCollection(this);
                }

                public IndexedSeq toCollection(Object repr) {
                    return IndexedSeqLike$class.toCollection(this, repr);
                }

                public Iterator<B> iterator() {
                    return IndexedSeqLike$class.iterator(this);
                }

                public <A1> Buffer<A1> toBuffer() {
                    return IndexedSeqLike$class.toBuffer(this);
                }

                public int length() {
                    return this.n1$1 - this.n0$1;
                }

                public B apply(int x) {
                    return (B)this.x2$1.apply(this.n1$1 - 1 - x);
                }
                {
                    this.n0$1 = n0$1;
                    this.n1$1 = n1$1;
                    this.x2$1 = x2$1;
                    IndexedSeqLike$class.$init$(this);
                    IndexedSeq$class.$init$(this);
                }
            });
        } else {
            indexedSeq = new IndexedSeq<B>(W, n0, n1, forward){
                private final Object[] Warr;
                private final int delta;
                private final int done;
                private final Iterator<B> wit;
                private int i;
                private final int length;

                public GenericCompanion<IndexedSeq> companion() {
                    return IndexedSeq$class.companion(this);
                }

                public IndexedSeq<B> seq() {
                    return IndexedSeq$class.seq(this);
                }

                public int hashCode() {
                    return IndexedSeqLike$class.hashCode(this);
                }

                public IndexedSeq<B> thisCollection() {
                    return IndexedSeqLike$class.thisCollection(this);
                }

                public IndexedSeq toCollection(Object repr) {
                    return IndexedSeqLike$class.toCollection(this, repr);
                }

                public Iterator<B> iterator() {
                    return IndexedSeqLike$class.iterator(this);
                }

                public <A1> Buffer<A1> toBuffer() {
                    return IndexedSeqLike$class.toBuffer(this);
                }

                public Iterator<B> wit() {
                    return this.wit;
                }

                public int i() {
                    return this.i;
                }

                public void i_$eq(int x$1) {
                    this.i = x$1;
                }

                public int length() {
                    return this.length;
                }

                public B apply(int x) {
                    return (B)this.Warr[x];
                }
                {
                    IndexedSeqLike$class.$init$(this);
                    IndexedSeq$class.$init$(this);
                    this.Warr = new Object[n1$1 - n0$1];
                    this.delta = forward$1 ? 1 : -1;
                    this.done = forward$1 ? n1$1 - n0$1 : -1;
                    this.wit = W$1.iterator().drop(n0$1);
                    int n = this.i = forward$1 ? 0 : n1$1 - n0$1 - 1;
                    while (this.i() != this.done) {
                        this.Warr[this.i()] = this.wit().next();
                        this.i_$eq(this.i() + this.delta);
                    }
                    this.length = n1$1 - n0$1;
                }
            };
        }
        return indexedSeq;
    }

    private <B> int[] kmpJumpTable(IndexedSeq<B> Wopt, int wlen) {
        int[] arr = new int[wlen];
        int pos = 2;
        int cnd = 0;
        arr[0] = -1;
        arr[1] = 0;
        while (pos < wlen) {
            Object r = Wopt.apply(cnd);
            Object r2 = Wopt.apply(pos - 1);
            if (r2 == r ? true : (r2 == null ? false : (r2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)r2, r) : (r2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)r2, r) : r2.equals(r))))) {
                arr[pos] = cnd + 1;
                ++pos;
                ++cnd;
                continue;
            }
            if (cnd > 0) {
                cnd = arr[cnd];
                continue;
            }
            arr[pos] = 0;
            ++pos;
        }
        return arr;
    }

    public <B> int scala$collection$SeqLike$$kmpSearch(Seq<B> S, int m0, int m1, Seq<B> W, int n0, int n1, boolean forward) {
        int n;
        if (n1 == n0 + 1) {
            n = forward ? this.clipR$1(S.indexOf(W.apply(n0), m0), m1) : this.clipL$1(S.lastIndexOf(W.apply(n0), m1 - 1), m0 - 1);
        } else if (m1 - m0 == n1 - n0) {
            Object Repr = S.view().slice(m0, m1);
            Object Repr2 = W.view().slice(n0, n1);
            n = !(Repr != null ? !Repr.equals(Repr2) : Repr2 != null) ? m0 : -1;
        } else {
            int n2;
            if (S instanceof IndexedSeq) {
                int delta;
                IndexedSeq<B> Wopt = this.kmpOptimizeWord(W, n0, n1, forward);
                int[] T = this.kmpJumpTable(Wopt, n1 - n0);
                int i = 0;
                int m = 0;
                int zero = forward ? m0 : m1 - 1;
                int n3 = delta = forward ? 1 : -1;
                while (i + m < m1 - m0) {
                    Object r = S.apply(zero + delta * (i + m));
                    Object r2 = Wopt.apply(i);
                    if (r2 == r ? true : (r2 == null ? false : (r2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)r2, r) : (r2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)r2, r) : r2.equals(r))))) {
                        if (++i != n1 - n0) continue;
                        return forward ? m + m0 : m1 - m - i;
                    }
                    int ti = T[i];
                    m += i - ti;
                    if (i <= 0) continue;
                    i = ti;
                }
                n2 = -1;
            } else {
                Iterator iter2 = S.iterator().drop(m0);
                IndexedSeq<B> Wopt = this.kmpOptimizeWord(W, n0, n1, true);
                int[] T = this.kmpJumpTable(Wopt, n1 - n0);
                Object[] cache = new Object[n1 - n0];
                int largest = 0;
                int i = 0;
                int m = 0;
                int answer = -1;
                while (m + m0 + n1 - n0 <= m1) {
                    while (i + m >= largest) {
                        cache[largest % (n1 - n0)] = iter2.next();
                        ++largest;
                    }
                    Object object = cache[(i + m) % (n1 - n0)];
                    Object r = Wopt.apply(i);
                    if (r == object ? true : (r == null ? false : (r instanceof Number ? BoxesRunTime.equalsNumObject((Number)r, object) : (r instanceof Character ? BoxesRunTime.equalsCharObject((Character)r, object) : r.equals(object))))) {
                        if (++i != n1 - n0) continue;
                        if (forward) {
                            return m + m0;
                        }
                        answer = m + m0;
                        int ti = T[--i];
                        m += i - ti;
                        if (i <= 0) continue;
                        i = ti;
                        continue;
                    }
                    int ti = T[i];
                    m += i - ti;
                    if (i <= 0) continue;
                    i = ti;
                }
                n2 = answer;
            }
            n = n2;
        }
        return n;
    }

    public <B> int indexOf(Seq<B> source, int sourceOffset, int sourceCount, Seq<B> target, int targetOffset, int targetCount, int fromIndex) {
        int ans;
        int slen = source.length();
        int clippedFrom = package$.MODULE$.max(0, fromIndex);
        int s0 = package$.MODULE$.min(slen, sourceOffset + clippedFrom);
        int s1 = package$.MODULE$.min(slen, s0 + sourceCount);
        int tlen = target.length();
        int t0 = package$.MODULE$.min(tlen, targetOffset);
        int t1 = package$.MODULE$.min(tlen, t0 + targetCount);
        return clippedFrom > slen - sourceOffset ? -1 : (t1 - t0 < 1 ? s0 : (s1 - s0 < t1 - t0 ? -1 : ((ans = this.scala$collection$SeqLike$$kmpSearch(source, s0, s1, target, t0, t1, true)) < 0 ? ans : ans - package$.MODULE$.min(slen, sourceOffset))));
    }

    public <B> int lastIndexOf(Seq<B> source, int sourceOffset, int sourceCount, Seq<B> target, int targetOffset, int targetCount, int fromIndex) {
        int ans;
        int slen = source.length();
        int tlen = target.length();
        int s0 = package$.MODULE$.min(slen, sourceOffset);
        int s1 = package$.MODULE$.min(slen, s0 + sourceCount);
        int clippedFrom = package$.MODULE$.min(s1 - s0, fromIndex);
        int t0 = package$.MODULE$.min(tlen, targetOffset);
        int t1 = package$.MODULE$.min(tlen, t0 + targetCount);
        int fixed_s1 = package$.MODULE$.min(s1, s0 + clippedFrom + (t1 - t0) - 1);
        return clippedFrom < 0 ? -1 : (t1 - t0 < 1 ? s0 + clippedFrom : (fixed_s1 - s0 < t1 - t0 ? -1 : ((ans = this.scala$collection$SeqLike$$kmpSearch(source, s0, fixed_s1, target, t0, t1, false)) < 0 ? ans : ans - s0)));
    }

    private final int clipR$1(int x, int y) {
        return x < y ? x : -1;
    }

    private final int clipL$1(int x, int y) {
        return x > y ? x : -1;
    }

    private SeqLike$() {
        MODULE$ = this;
    }
}

