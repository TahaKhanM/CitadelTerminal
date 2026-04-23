/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import java.util.NoSuchElementException;
import scala.Function1;
import scala.Function2;
import scala.Serializable;
import scala.collection.AbstractIterator;
import scala.collection.AbstractSeq;
import scala.collection.AbstractSet;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.generic.TraversableForwarder$class;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.ListSet$;
import scala.collection.immutable.ListSet$EmptyListSet$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Set$class;
import scala.collection.immutable.Traversable$class;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.ListBuffer;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\tmr!B\u0001\u0003\u0011\u0003I\u0011a\u0002'jgR\u001cV\r\u001e\u0006\u0003\u0007\u0011\t\u0011\"[7nkR\f'\r\\3\u000b\u0005\u00151\u0011AC2pY2,7\r^5p]*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u0005)YQ\"\u0001\u0002\u0007\u000b1\u0011\u0001\u0012A\u0007\u0003\u000f1K7\u000f^*fiN\u00191B\u0004\u001a\u0011\u0007=\u0011B#D\u0001\u0011\u0015\t\tB!A\u0004hK:,'/[2\n\u0005M\u0001\"aE%n[V$\u0018M\u00197f'\u0016$h)Y2u_JL\bC\u0001\u0006\u0016\r\u0015a!\u0001\u0001\f2+\t9bd\u0005\u0004\u00161!ZcF\r\t\u00043iaR\"\u0001\u0003\n\u0005m!!aC!cgR\u0014\u0018m\u0019;TKR\u0004\"!\b\u0010\r\u0001\u0011)q$\u0006b\u0001A\t\t\u0011)\u0005\u0002\"KA\u0011!eI\u0007\u0002\r%\u0011AE\u0002\u0002\b\u001d>$\b.\u001b8h!\t\u0011c%\u0003\u0002(\r\t\u0019\u0011I\\=\u0011\u0007)IC$\u0003\u0002+\u0005\t\u00191+\u001a;\u0011\t=aC\u0004F\u0005\u0003[A\u0011!cR3oKJL7mU3u)\u0016l\u0007\u000f\\1uKB!\u0011d\f\u000f2\u0013\t\u0001DAA\u0004TKRd\u0015n[3\u0011\u0007))B\u0004\u0005\u0002#g%\u0011AG\u0002\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\u0006mU!\taN\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003EBQ!O\u000b\u0005Bi\n\u0011bY8na\u0006t\u0017n\u001c8\u0016\u0003m\u00022a\u0004\u001f\u0015\u0013\ti\u0004C\u0001\tHK:,'/[2D_6\u0004\u0018M\\5p]\")q(\u0006C!\u0001\u0006!1/\u001b>f+\u0005\t\u0005C\u0001\u0012C\u0013\t\u0019eAA\u0002J]RDQ!R\u000b\u0005B\u0019\u000bq![:F[B$\u00180F\u0001H!\t\u0011\u0003*\u0003\u0002J\r\t9!i\\8mK\u0006t\u0007\"B&\u0016\t\u0003a\u0015\u0001C2p]R\f\u0017N\\:\u0015\u0005\u001dk\u0005\"\u0002(K\u0001\u0004a\u0012\u0001B3mK6DQ\u0001U\u000b\u0005\u0002E\u000bQ\u0001\n9mkN$\"!\r*\t\u000b9{\u0005\u0019\u0001\u000f\t\u000bQ+B\u0011A+\u0002\r\u0011j\u0017N\\;t)\t\td\u000bC\u0003O'\u0002\u0007A\u0004C\u0003Y+\u0011\u0005\u0013,\u0001\u0006%a2,8\u000f\n9mkN$\"!\r.\t\u000bm;\u0006\u0019\u0001/\u0002\u0005a\u001c\bcA\r^9%\u0011a\f\u0002\u0002\u0013\u000f\u0016tGK]1wKJ\u001c\u0018M\u00197f\u001f:\u001cW\rC\u0003a+\u0011\u0005\u0011-\u0001\u0005ji\u0016\u0014\u0018\r^8s+\u0005\u0011\u0007cA\rd9%\u0011A\r\u0002\u0002\t\u0013R,'/\u0019;pe\")a-\u0006C!O\u0006!\u0001.Z1e+\u0005a\u0002\"B5\u0016\t\u0003R\u0017\u0001\u0002;bS2,\u0012!\r\u0005\u0006YV!\t%\\\u0001\rgR\u0014\u0018N\\4Qe\u00164\u0017\u000e_\u000b\u0002]B\u0011q\u000e^\u0007\u0002a*\u0011\u0011O]\u0001\u0005Y\u0006twMC\u0001t\u0003\u0011Q\u0017M^1\n\u0005U\u0004(AB*ue&twM\u0002\u0003x+!A(\u0001\u0002(pI\u0016\u001c2A^\u00193\u0011!1gO!b\u0001\n\u0003:\u0007\u0002C>w\u0005\u0003\u0005\u000b\u0011\u0002\u000f\u0002\u000b!,\u0017\r\u001a\u0011\t\u000bY2H\u0011A?\u0015\u0007y\f\t\u0001\u0005\u0002\u0000m6\tQ\u0003C\u0003gy\u0002\u0007A\u0004C\u0003@m\u0012\u0005\u0003\tC\u0004\u0002\bY$I!!\u0003\u0002\u0019ML'0Z%oi\u0016\u0014h.\u00197\u0015\u000b\u0005\u000bY!a\u0004\t\u000f\u00055\u0011Q\u0001a\u0001c\u0005\ta\u000eC\u0004\u0002\u0012\u0005\u0015\u0001\u0019A!\u0002\u0007\u0005\u001c7\r\u000b\u0003\u0002\u0006\u0005U\u0001\u0003BA\f\u0003;i!!!\u0007\u000b\u0007\u0005ma!\u0001\u0006b]:|G/\u0019;j_:LA!a\b\u0002\u001a\t9A/Y5me\u0016\u001c\u0007\"B#w\t\u00032\u0005BB&w\t\u0003\n)\u0003F\u0002H\u0003OAq!!\u000b\u0002$\u0001\u0007A$A\u0001f\u0011\u001d\tiC\u001eC\u0005\u0003_\t\u0001cY8oi\u0006Lgn]%oi\u0016\u0014h.\u00197\u0015\u000b\u001d\u000b\t$a\r\t\u000f\u00055\u00111\u0006a\u0001c!9\u0011\u0011FA\u0016\u0001\u0004a\u0002\u0006BA\u0016\u0003+Aa\u0001\u0015<\u0005B\u0005eBcA\u0019\u0002<!9\u0011\u0011FA\u001c\u0001\u0004a\u0002B\u0002+w\t\u0003\ny\u0004F\u00022\u0003\u0003Bq!!\u000b\u0002>\u0001\u0007A\u0004C\u0003jm\u0012\u0005#\u000eC\u0006\u0002HY\u0014\t\u0011!A\u0005BUQ\u0017aM:dC2\fGeY8mY\u0016\u001cG/[8oI%lW.\u001e;bE2,G\u0005T5tiN+G\u000f\n\u0013v]\u000eDWmY6fI~{W\u000f^3s\u0011\u001d\tY%\u0006C!\u0003\u001b\nQ\u0001^8TKR,B!a\u0014\u0002VU\u0011\u0011\u0011\u000b\t\u0005\u0015%\n\u0019\u0006E\u0002\u001e\u0003+\"\u0001\"a\u0016\u0002J\t\u0007\u0011\u0011\f\u0002\u0002\u0005F\u0011A$\n\u0005\r\u0003;*\"\u0011!A\u0001\n\u0003)\u0012qL\u00014g\u000e\fG.\u0019\u0013d_2dWm\u0019;j_:$\u0013.\\7vi\u0006\u0014G.\u001a\u0013MSN$8+\u001a;%IUt7\r[3dW\u0016$w\f\n9mkN$2!MA1\u0011\u001d\tI#a\u0017A\u0002qA1\"a\u0012\u0016\u0005\u0003\u0005\t\u0011\"\u0001\u0016U\":Q#a\u001a\u0002n\u0005E\u0004c\u0001\u0012\u0002j%\u0019\u00111\u000e\u0004\u0003+\u0011,\u0007O]3dCR,G-\u00138iKJLG/\u00198dK\u0006\u0012\u0011qN\u0001R)\",\u0007e]3nC:$\u0018nY:!_\u001a\u0004\u0013.\\7vi\u0006\u0014G.\u001a\u0011d_2dWm\u0019;j_:\u001c\b%\\1lKN\u0004\u0013N\u001c5fe&$\u0018N\\4!MJ|W\u000e\t'jgR\u001cV\r\u001e\u0011feJ|'/\f9s_:,g&\t\u0002\u0002t\u00051!GL\u00192]ABaAN\u0006\u0005\u0002\u0005]D#A\u0005\t\u000f\u0005m4\u0002b\u0001\u0002~\u0005a1-\u00198Ck&dGM\u0012:p[V!\u0011qPAI+\t\t\t\tE\u0005\u0010\u0003\u0007\u000b9)a$\u0002\u0014&\u0019\u0011Q\u0011\t\u0003\u0019\r\u000bgNQ;jY\u00124%o\\7\u0011\t\u0005%\u00151R\u0007\u0002\u0017%\u0019\u0011Q\u0012\u001f\u0003\t\r{G\u000e\u001c\t\u0004;\u0005EEAB\u0010\u0002z\t\u0007\u0001\u0005\u0005\u0003\u000b+\u0005=\u0005bBAL\u0017\u0011\u0005\u0013\u0011T\u0001\u000b]\u0016<()^5mI\u0016\u0014X\u0003BAN\u0003W+\"!!(\u0011\u0011\u0005}\u0015QUAU\u0003[k!!!)\u000b\u0007\u0005\rF!A\u0004nkR\f'\r\\3\n\t\u0005\u001d\u0016\u0011\u0015\u0002\b\u0005VLG\u000eZ3s!\ri\u00121\u0016\u0003\u0007?\u0005U%\u0019\u0001\u0011\u0011\t))\u0012\u0011V\u0004\b\u0003c[\u0001\u0012BAZ\u00031)U\u000e\u001d;z\u0019&\u001cHoU3u!\u0011\tI)!.\u0007\u000f\u0005]6\u0002#\u0003\u0002:\naQ)\u001c9us2K7\u000f^*fiN!\u0011QWA^!\rQQ#\n\u0005\bm\u0005UF\u0011AA`)\t\t\u0019\f\u0003\u0006\u0002D\u0006U\u0016\u0011!C\u0005\u0003\u000b\f1B]3bIJ+7o\u001c7wKR\u0011\u0011q\u0019\t\u0004_\u0006%\u0017bAAfa\n1qJ\u00196fGRD\u0001\"a4\f\t\u0003!\u0011\u0011[\u0001\u000eK6\u0004H/_%ogR\fgnY3\u0016\u0005\u0005mfABAk\u0017\u0001\t9N\u0001\bMSN$8+\u001a;Ck&dG-\u001a:\u0016\t\u0005e\u0017Q]\n\u0007\u0003'\fY.!9\u0011\u0007\t\ni.C\u0002\u0002`\u001a\u0011a!\u00118z%\u00164\u0007\u0003CAP\u0003K\u000b\u0019/!;\u0011\u0007u\t)\u000fB\u0004\u0002h\u0006M'\u0019\u0001\u0011\u0003\t\u0015cW-\u001c\t\u0005\u0015U\t\u0019\u000fC\u0006\u0002n\u0006M'\u0011!Q\u0001\n\u0005%\u0018aB5oSRL\u0017\r\u001c\u0005\bm\u0005MG\u0011AAy)\u0011\t\u00190!>\u0011\r\u0005%\u00151[Ar\u0011!\ti/a<A\u0002\u0005%\bb\u0002\u001c\u0002T\u0012\u0005\u0011\u0011 \u000b\u0003\u0003gD!\"!@\u0002T\n\u0007I\u0011CA\u0000\u0003\u0015)G.Z7t+\t\u0011\t\u0001\u0005\u0004\u0002 \n\r\u00111]\u0005\u0005\u0005\u000b\t\tK\u0001\u0006MSN$()\u001e4gKJD\u0011B!\u0003\u0002T\u0002\u0006IA!\u0001\u0002\r\u0015dW-\\:!\u0011)\u0011i!a5C\u0002\u0013E!qB\u0001\u0005g\u0016,g.\u0006\u0002\u0003\u0012A1\u0011q\u0014B\n\u0003GLAA!\u0006\u0002\"\n9\u0001*Y:i'\u0016$\b\"\u0003B\r\u0003'\u0004\u000b\u0011\u0002B\t\u0003\u0015\u0019X-\u001a8!\u0011!\u0011i\"a5\u0005\u0002\t}\u0011\u0001\u0003\u0013qYV\u001cH%Z9\u0015\t\t\u0005\"1E\u0007\u0003\u0003'D\u0001B!\n\u0003\u001c\u0001\u0007\u00111]\u0001\u0002q\"A!\u0011FAj\t\u0003\u0011Y#A\u0003dY\u0016\f'\u000f\u0006\u0002\u0003.A\u0019!Ea\f\n\u0007\tEbA\u0001\u0003V]&$\b\u0002\u0003B\u001b\u0003'$\tAa\u000e\u0002\rI,7/\u001e7u)\t\tI\u000fC\u0005\u0002D.\t\t\u0011\"\u0003\u0002F\u0002")
public class ListSet<A>
extends AbstractSet<A>
implements Set<A>,
Serializable {
    public static <A> CanBuildFrom<ListSet<?>, A, ListSet<A>> canBuildFrom() {
        return ListSet$.MODULE$.canBuildFrom();
    }

    public static <A> Object setCanBuildFrom() {
        return ListSet$.MODULE$.setCanBuildFrom();
    }

    @Override
    public Set<A> seq() {
        return Set$class.seq(this);
    }

    @Override
    public Combiner<A, ParSet<A>> parCombiner() {
        return Set$class.parCombiner(this);
    }

    @Override
    public GenericCompanion<ListSet> companion() {
        return ListSet$.MODULE$;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean contains(A elem) {
        return false;
    }

    @Override
    public ListSet<A> $plus(A elem) {
        return new Node(this, elem);
    }

    @Override
    public ListSet<A> $minus(A elem) {
        return this;
    }

    @Override
    public ListSet<A> $plus$plus(GenTraversableOnce<A> xs) {
        return xs.isEmpty() ? this : ((ListSetBuilder)new ListSetBuilder<A>(this).$plus$plus$eq(xs.seq())).result();
    }

    public ListSet<A> scala$collection$immutable$ListSet$$unchecked_$plus(A e) {
        return new Node(this, e);
    }

    public ListSet<A> scala$collection$immutable$ListSet$$unchecked_outer() {
        throw new NoSuchElementException("Empty ListSet has no outer pointer");
    }

    @Override
    public Iterator<A> iterator() {
        return new AbstractIterator<A>(this){
            private ListSet<A> that;

            private ListSet<A> that() {
                return this.that;
            }

            private void that_$eq(ListSet<A> x$1) {
                this.that = x$1;
            }

            public boolean hasNext() {
                return this.that().nonEmpty();
            }

            /*
             * WARNING - void declaration
             */
            public A next() {
                Nothing$ nothing$;
                if (this.hasNext()) {
                    void var1_1;
                    A res = this.that().head();
                    this.that_$eq((ListSet<A>)this.that().tail());
                    nothing$ = var1_1;
                } else {
                    nothing$ = Iterator$.MODULE$.empty().next();
                }
                return (A)nothing$;
            }
            {
                this.that = $outer;
            }
        };
    }

    @Override
    public A head() {
        throw new NoSuchElementException("Set has no elements");
    }

    @Override
    public ListSet<A> tail() {
        throw new NoSuchElementException("Next of an empty set");
    }

    @Override
    public String stringPrefix() {
        return "ListSet";
    }

    @Override
    public <B> Set<B> toSet() {
        return this;
    }

    public ListSet() {
        Traversable$class.$init$(this);
        Iterable$class.$init$(this);
        Set$class.$init$(this);
    }

    public class Node
    extends ListSet<A> {
        private final A head;
        public final /* synthetic */ ListSet $outer;

        @Override
        public A head() {
            return this.head;
        }

        @Override
        public ListSet<A> scala$collection$immutable$ListSet$$unchecked_outer() {
            return this.scala$collection$immutable$ListSet$Node$$$outer();
        }

        @Override
        public int size() {
            return this.sizeInternal(this, 0);
        }

        private int sizeInternal(ListSet<A> n, int acc) {
            while (!n.isEmpty()) {
                ++acc;
                n = n.scala$collection$immutable$ListSet$$unchecked_outer();
            }
            return acc;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(A e) {
            return this.containsInternal(this, e);
        }

        private boolean containsInternal(ListSet<A> n, A e) {
            while (true) {
                block5: {
                    boolean bl;
                    block4: {
                        block3: {
                            if (!n.isEmpty()) break block3;
                            bl = false;
                            break block4;
                        }
                        Object a = n.head();
                        if (!(a == e ? true : (a == null ? false : (a instanceof Number ? BoxesRunTime.equalsNumObject((Number)a, e) : (a instanceof Character ? BoxesRunTime.equalsCharObject((Character)a, e) : a.equals(e)))))) break block5;
                        bl = true;
                    }
                    return bl;
                }
                n = n.scala$collection$immutable$ListSet$$unchecked_outer();
            }
        }

        @Override
        public ListSet<A> $plus(A e) {
            return this.contains(e) ? this : new Node(this, e);
        }

        @Override
        public ListSet<A> $minus(A e) {
            ListSet listSet;
            Object a = this.head();
            if (e == a ? true : (e == null ? false : (e instanceof Number ? BoxesRunTime.equalsNumObject((Number)e, a) : (e instanceof Character ? BoxesRunTime.equalsCharObject((Character)e, a) : e.equals(a))))) {
                listSet = this.scala$collection$immutable$ListSet$Node$$$outer();
            } else {
                Object tail = this.scala$collection$immutable$ListSet$Node$$$outer().$minus(e);
                listSet = new Node(tail, this.head());
            }
            return listSet;
        }

        @Override
        public ListSet<A> tail() {
            return this.scala$collection$immutable$ListSet$Node$$$outer();
        }

        public /* synthetic */ ListSet scala$collection$immutable$ListSet$Node$$$outer() {
            return this.$outer;
        }

        public Node(ListSet<A> $outer, A head2) {
            this.head = head2;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static class ListSetBuilder<Elem>
    implements Builder<Elem, ListSet<Elem>> {
        private final ListBuffer<Elem> elems;
        private final HashSet<Elem> seen;

        @Override
        public void sizeHint(int size2) {
            Builder$class.sizeHint((Builder)this, size2);
        }

        @Override
        public void sizeHint(TraversableLike<?, ?> coll) {
            Builder$class.sizeHint((Builder)this, coll);
        }

        @Override
        public void sizeHint(TraversableLike<?, ?> coll, int delta) {
            Builder$class.sizeHint(this, coll, delta);
        }

        @Override
        public void sizeHintBounded(int size2, TraversableLike<?, ?> boundingColl) {
            Builder$class.sizeHintBounded(this, size2, boundingColl);
        }

        @Override
        public <NewTo> Builder<Elem, NewTo> mapResult(Function1<ListSet<Elem>, NewTo> f) {
            return Builder$class.mapResult(this, f);
        }

        @Override
        public Growable<Elem> $plus$eq(Elem elem1, Elem elem2, Seq<Elem> elems) {
            return Growable$class.$plus$eq(this, elem1, elem2, elems);
        }

        @Override
        public Growable<Elem> $plus$plus$eq(TraversableOnce<Elem> xs) {
            return Growable$class.$plus$plus$eq(this, xs);
        }

        public ListBuffer<Elem> elems() {
            return this.elems;
        }

        public HashSet<Elem> seen() {
            return this.seen;
        }

        @Override
        public ListSetBuilder<Elem> $plus$eq(Elem x) {
            Object object;
            if (this.seen().apply(x)) {
                object = BoxedUnit.UNIT;
            } else {
                this.elems().$plus$eq((Object)x);
                object = this.seen().$plus$eq((Object)x);
            }
            return this;
        }

        @Override
        public void clear() {
            this.elems().clear();
            this.seen().clear();
        }

        @Override
        public ListSet<Elem> result() {
            ListSet$ listSet$ = ListSet$.MODULE$;
            return (ListSet)TraversableForwarder$class.foldLeft(this.elems(), ListSet$EmptyListSet$.MODULE$, (Function2)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final ListSet<Elem> apply(ListSet<Elem> x$1, Elem x$2) {
                    return x$1.scala$collection$immutable$ListSet$$unchecked_$plus(x$2);
                }
            }));
        }

        public ListSetBuilder(ListSet<Elem> initial) {
            Growable$class.$init$(this);
            Builder$class.$init$(this);
            this.elems = (ListBuffer)((AbstractSeq)((Object)new ListBuffer().$plus$plus$eq((TraversableOnce)initial))).reverse();
            this.seen = (HashSet)new HashSet<Elem>().$plus$plus$eq(initial);
        }

        public ListSetBuilder() {
            this((ListSet)ListSet$.MODULE$.empty());
        }
    }
}

