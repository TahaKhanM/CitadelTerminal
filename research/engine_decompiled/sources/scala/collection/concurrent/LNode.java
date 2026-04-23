/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.concurrent;

import scala.MatchError;
import scala.Option;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Tuple2;
import scala.collection.concurrent.MainNode;
import scala.collection.concurrent.TNode;
import scala.collection.concurrent.TrieMap;
import scala.collection.immutable.ListMap;
import scala.collection.immutable.ListMap$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001)4Q!\u0001\u0002\u0003\t!\u0011Q\u0001\u0014(pI\u0016T!a\u0001\u0003\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006,2!\u0003\t\u001d'\t\u0001!\u0002\u0005\u0003\f\u00199YR\"\u0001\u0002\n\u00055\u0011!\u0001C'bS:tu\u000eZ3\u0011\u0005=\u0001B\u0002\u0001\u0003\u0006#\u0001\u0011\ra\u0005\u0002\u0002\u0017\u000e\u0001\u0011C\u0001\u000b\u0019!\t)b#D\u0001\u0007\u0013\t9bAA\u0004O_RD\u0017N\\4\u0011\u0005UI\u0012B\u0001\u000e\u0007\u0005\r\te.\u001f\t\u0003\u001fq!Q!\b\u0001C\u0002M\u0011\u0011A\u0016\u0005\t?\u0001\u0011)\u0019!C\u0003A\u00059A.[:u[\u0006\u0004X#A\u0011\u0011\t\t*cbG\u0007\u0002G)\u0011A\u0005B\u0001\nS6lW\u000f^1cY\u0016L!AJ\u0012\u0003\u000f1K7\u000f^'ba\"A\u0001\u0006\u0001B\u0001B\u00035\u0011%\u0001\u0005mSN$X.\u00199!\u0011\u0015Q\u0003\u0001\"\u0001,\u0003\u0019a\u0014N\\5u}Q\u0011A&\f\t\u0005\u0017\u0001q1\u0004C\u0003 S\u0001\u0007\u0011\u0005C\u0003+\u0001\u0011\u0005q\u0006F\u0002-aIBQ!\r\u0018A\u00029\t\u0011a\u001b\u0005\u0006g9\u0002\raG\u0001\u0002m\")!\u0006\u0001C\u0001kQ)AF\u000e\u001d;y!)q\u0007\u000ea\u0001\u001d\u0005\u00111.\r\u0005\u0006sQ\u0002\raG\u0001\u0003mFBQa\u000f\u001bA\u00029\t!a\u001b\u001a\t\u000bu\"\u0004\u0019A\u000e\u0002\u0005Y\u0014\u0004\"B \u0001\t\u0003\u0001\u0015\u0001C5og\u0016\u0014H/\u001a3\u0015\u00071\n%\tC\u00032}\u0001\u0007a\u0002C\u00034}\u0001\u00071\u0004C\u0003E\u0001\u0011\u0005Q)A\u0004sK6|g/\u001a3\u0015\u0007)1u\tC\u00032\u0007\u0002\u0007a\u0002C\u0003I\u0007\u0002\u0007\u0011*\u0001\u0002diB!1B\u0013\b\u001c\u0013\tY%AA\u0004Ue&,W*\u00199\t\u000b5\u0003A\u0011\u0001(\u0002\u0007\u001d,G\u000f\u0006\u0002P%B\u0019Q\u0003U\u000e\n\u0005E3!AB(qi&|g\u000eC\u00032\u0019\u0002\u0007a\u0002C\u0003U\u0001\u0011\u0005Q+\u0001\u0006dC\u000eDW\rZ*ju\u0016$\"AV-\u0011\u0005U9\u0016B\u0001-\u0007\u0005\rIe\u000e\u001e\u0005\u0006\u0011N\u0003\rA\u0017\t\u0003+mK!\u0001\u0018\u0004\u0003\r\u0005s\u0017PU3g\u0011\u0015q\u0006\u0001\"\u0001`\u0003\u0019\u0019HO]5oOR\u0011\u0001\r\u001b\t\u0003C\u001al\u0011A\u0019\u0006\u0003G\u0012\fA\u0001\\1oO*\tQ-\u0001\u0003kCZ\f\u0017BA4c\u0005\u0019\u0019FO]5oO\")\u0011.\u0018a\u0001-\u0006\u0019A.\u001a<")
public final class LNode<K, V>
extends MainNode<K, V> {
    private final ListMap<K, V> listmap;

    public final ListMap<K, V> listmap() {
        return this.listmap;
    }

    public LNode<K, V> inserted(K k, V v) {
        return new LNode<K, V>(this.listmap().$plus(new Tuple2<K, V>(k, v)));
    }

    public MainNode<K, V> removed(K k, TrieMap<K, V> ct) {
        Tuple2 tuple2;
        block4: {
            MainNode mainNode;
            block3: {
                Object updmap;
                block2: {
                    updmap = this.listmap().$minus((Object)k);
                    if (((ListMap)updmap).size() <= 1) break block2;
                    mainNode = new LNode<K, V>(updmap);
                    break block3;
                }
                tuple2 = ((ListMap)updmap).iterator().next();
                if (tuple2 == null) break block4;
                Tuple2 tuple22 = new Tuple2(tuple2._1(), tuple2._2());
                Object k2 = tuple22._1();
                Object v = tuple22._2();
                mainNode = new TNode(k2, v, ct.computeHash(k2));
            }
            return mainNode;
        }
        throw new MatchError(tuple2);
    }

    public Option<V> get(K k) {
        return this.listmap().get(k);
    }

    @Override
    public int cachedSize(Object ct) {
        return this.listmap().size();
    }

    @Override
    public String string(int lev) {
        Predef$ predef$ = Predef$.MODULE$;
        Predef$ predef$2 = Predef$.MODULE$;
        return new StringBuilder().append((Object)new StringOps(" ").$times(lev)).append((Object)new StringOps("LNode(%s)").format(Predef$.MODULE$.genericWrapArray(new Object[]{this.listmap().mkString(", ")}))).toString();
    }

    public LNode(ListMap<K, V> listmap) {
        this.listmap = listmap;
    }

    public LNode(K k, V v) {
        Tuple2[] tuple2Array = new Tuple2[1];
        K k2 = Predef$.MODULE$.ArrowAssoc(k);
        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[0] = new Tuple2<K, V>(k2, v);
        this((ListMap)ListMap$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array)));
    }

    public LNode(K k1, V v1, K k2, V v2) {
        Tuple2[] tuple2Array = new Tuple2[2];
        K k = Predef$.MODULE$.ArrowAssoc(k1);
        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[0] = new Tuple2<K, V>(k, v1);
        K k3 = Predef$.MODULE$.ArrowAssoc(k2);
        Predef$ArrowAssoc$ predef$ArrowAssoc$2 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[1] = new Tuple2<K, V>(k3, v2);
        this((ListMap)ListMap$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array)));
    }
}

