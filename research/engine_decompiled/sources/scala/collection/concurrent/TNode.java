/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.concurrent;

import scala.Predef$;
import scala.Tuple2;
import scala.collection.concurrent.KVNode;
import scala.collection.concurrent.MainNode;
import scala.collection.concurrent.SNode;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001\u00014Q!\u0001\u0002\u0003\t!\u0011Q\u0001\u0016(pI\u0016T!a\u0001\u0003\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006,2!\u0003\t\u001d'\r\u0001!B\b\t\u0005\u00171q1$D\u0001\u0003\u0013\ti!A\u0001\u0005NC&tgj\u001c3f!\ty\u0001\u0003\u0004\u0001\u0005\u000bE\u0001!\u0019A\n\u0003\u0003-\u001b\u0001!\u0005\u0002\u00151A\u0011QCF\u0007\u0002\r%\u0011qC\u0002\u0002\b\u001d>$\b.\u001b8h!\t)\u0012$\u0003\u0002\u001b\r\t\u0019\u0011I\\=\u0011\u0005=aB!B\u000f\u0001\u0005\u0004\u0019\"!\u0001,\u0011\t-ybbG\u0005\u0003A\t\u0011aa\u0013,O_\u0012,\u0007\u0002\u0003\u0012\u0001\u0005\u000b\u0007IQA\u0012\u0002\u0003-,\u0012A\u0004\u0005\tK\u0001\u0011\t\u0011)A\u0007\u001d\u0005\u00111\u000e\t\u0005\tO\u0001\u0011)\u0019!C\u0003Q\u0005\ta/F\u0001\u001c\u0011!Q\u0003A!A!\u0002\u001bY\u0012A\u0001<!\u0011!a\u0003A!b\u0001\n\u000bi\u0013A\u00015d+\u0005q\u0003CA\u000b0\u0013\t\u0001dAA\u0002J]RD\u0001B\r\u0001\u0003\u0002\u0003\u0006iAL\u0001\u0004Q\u000e\u0004\u0003\"\u0002\u001b\u0001\t\u0003)\u0014A\u0002\u001fj]&$h\b\u0006\u00037oaJ\u0004\u0003B\u0006\u0001\u001dmAQAI\u001aA\u00029AQaJ\u001aA\u0002mAQ\u0001L\u001aA\u00029BQa\u000f\u0001\u0005\u0006q\nAaY8qsV\ta\u0007C\u0003?\u0001\u0011\u0015A(\u0001\u0006d_BLHk\\7cK\u0012DQ\u0001\u0011\u0001\u0005\u0006\u0005\u000bAbY8qsVsGo\\7cK\u0012,\u0012A\u0011\t\u0005\u0017\rs1$\u0003\u0002E\u0005\t)1KT8eK\")a\t\u0001C\u0003\u000f\u000611N\u001e)bSJ,\u0012\u0001\u0013\t\u0005+%s1$\u0003\u0002K\r\t1A+\u001e9mKJBQ\u0001\u0014\u0001\u0005\u00065\u000b!bY1dQ\u0016$7+\u001b>f)\tqc\nC\u0003P\u0017\u0002\u0007\u0001+\u0001\u0002diB\u0011Q#U\u0005\u0003%\u001a\u0011a!\u00118z%\u00164\u0007\"\u0002+\u0001\t\u000b)\u0016AB:ue&tw\r\u0006\u0002W=B\u0011q\u000bX\u0007\u00021*\u0011\u0011LW\u0001\u0005Y\u0006twMC\u0001\\\u0003\u0011Q\u0017M^1\n\u0005uC&AB*ue&tw\rC\u0003`'\u0002\u0007a&A\u0002mKZ\u0004")
public final class TNode<K, V>
extends MainNode<K, V>
implements KVNode<K, V> {
    private final K k;
    private final V v;
    private final int hc;

    public final K k() {
        return this.k;
    }

    public final V v() {
        return this.v;
    }

    public final int hc() {
        return this.hc;
    }

    public final TNode<K, V> copy() {
        return new TNode<K, V>(this.k(), this.v(), this.hc());
    }

    public final TNode<K, V> copyTombed() {
        return new TNode<K, V>(this.k(), this.v(), this.hc());
    }

    public final SNode<K, V> copyUntombed() {
        return new SNode<K, V>(this.k(), this.v(), this.hc());
    }

    @Override
    public final Tuple2<K, V> kvPair() {
        return new Tuple2<K, V>(this.k(), this.v());
    }

    @Override
    public final int cachedSize(Object ct) {
        return 1;
    }

    @Override
    public final String string(int lev) {
        Predef$ predef$ = Predef$.MODULE$;
        Predef$ predef$2 = Predef$.MODULE$;
        return new StringBuilder().append((Object)new StringOps("  ").$times(lev)).append((Object)new StringOps("TNode(%s, %s, %x, !)").format(Predef$.MODULE$.genericWrapArray(new Object[]{this.k(), this.v(), BoxesRunTime.boxToInteger(this.hc())}))).toString();
    }

    public TNode(K k, V v, int hc) {
        this.k = k;
        this.v = v;
        this.hc = hc;
    }
}

