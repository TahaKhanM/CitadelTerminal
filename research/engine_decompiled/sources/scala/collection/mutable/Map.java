/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.generic.Shrinkable;
import scala.collection.generic.Shrinkable$class;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.Cloneable$class;
import scala.collection.mutable.Iterable;
import scala.collection.mutable.Iterable$class;
import scala.collection.mutable.Map$class;
import scala.collection.mutable.MapLike;
import scala.collection.mutable.MapLike$class;
import scala.collection.mutable.Traversable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\u0005=daB\u0001\u0003!\u0003\r\t!\u0003\u0002\u0004\u001b\u0006\u0004(BA\u0002\u0005\u0003\u001diW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001)2A\u0003\r#'\u0015\u00011b\u0004\u0013(!\taQ\"D\u0001\u0007\u0013\tqaA\u0001\u0004B]f\u0014VM\u001a\t\u0004!E\u0019R\"\u0001\u0002\n\u0005I\u0011!\u0001C%uKJ\f'\r\\3\u0011\t1!b#I\u0005\u0003+\u0019\u0011a\u0001V;qY\u0016\u0014\u0004CA\f\u0019\u0019\u0001!Q!\u0007\u0001C\u0002i\u0011\u0011!Q\t\u00037y\u0001\"\u0001\u0004\u000f\n\u0005u1!a\u0002(pi\"Lgn\u001a\t\u0003\u0019}I!\u0001\t\u0004\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u0018E\u0011)1\u0005\u0001b\u00015\t\t!\t\u0005\u0003&MY\tS\"\u0001\u0003\n\u0005\u0005!\u0001#\u0002\t)-\u0005R\u0013BA\u0015\u0003\u0005\u001di\u0015\r\u001d'jW\u0016\u0004B\u0001\u0005\u0001\u0017C!)A\u0006\u0001C\u0001[\u00051A%\u001b8ji\u0012\"\u0012A\f\t\u0003\u0019=J!\u0001\r\u0004\u0003\tUs\u0017\u000e\u001e\u0005\u0006e\u0001!\teM\u0001\u0006K6\u0004H/_\u000b\u0002U!)Q\u0007\u0001C!g\u0005\u00191/Z9\t\u000b]\u0002A\u0011\u0001\u001d\u0002\u0017]LG\u000f\u001b#fM\u0006,H\u000e\u001e\u000b\u0003UeBQA\u000f\u001cA\u0002m\n\u0011\u0001\u001a\t\u0005\u0019q2\u0012%\u0003\u0002>\r\tIa)\u001e8di&|g.\r\u0005\u0006\u007f\u0001!\t\u0001Q\u0001\u0011o&$\b\u000eR3gCVdGOV1mk\u0016$\"AK!\t\u000bir\u0004\u0019A\u0011\b\u000b\r\u0013\u0001\u0012\u0001#\u0002\u00075\u000b\u0007\u000f\u0005\u0002\u0011\u000b\u001a)\u0011A\u0001E\u0001\rN\u0011Qi\u0012\t\u0004\u0011.kU\"A%\u000b\u0005)#\u0011aB4f]\u0016\u0014\u0018nY\u0005\u0003\u0019&\u0013\u0011#T;uC\ndW-T1q\r\u0006\u001cGo\u001c:z!\t\u0001\u0002\u0001C\u0003P\u000b\u0012\u0005\u0001+\u0001\u0004=S:LGO\u0010\u000b\u0002\t\")!+\u0012C\u0002'\u0006a1-\u00198Ck&dGM\u0012:p[V\u0019A\u000b\u00192\u0016\u0003U\u0003R\u0001\u0013,Y=\u000eL!aV%\u0003\u0019\r\u000bgNQ;jY\u00124%o\\7\u0011\u0005eSV\"A#\n\u0005mc&\u0001B\"pY2L!!X%\u0003\u001b\u001d+g.T1q\r\u0006\u001cGo\u001c:z!\u0011aAcX1\u0011\u0005]\u0001G!B\rR\u0005\u0004Q\u0002CA\fc\t\u0015\u0019\u0013K1\u0001\u001b!\u0011\u0001\u0002aX1\t\u000bI*E\u0011A3\u0016\u0007\u0019L7.F\u0001h!\u0011\u0001\u0002\u0001\u001b6\u0011\u0005]IG!B\re\u0005\u0004Q\u0002CA\fl\t\u0015\u0019CM1\u0001\u001b\r\u0011iW\t\u00018\u0003\u0017]KG\u000f\u001b#fM\u0006,H\u000e^\u000b\u0004_v|8\u0003\u00027q\u0003\u0003\u0001B!\u001d>}}:\u0011!/\u001f\b\u0003gbt!\u0001^<\u000e\u0003UT!A\u001e\u0005\u0002\rq\u0012xn\u001c;?\u0013\u00059\u0011BA\u0003\u0007\u0013\t\u0019E!\u0003\u0002nw*\u00111\t\u0002\t\u0003/u$Q!\u00077C\u0002i\u0001\"aF@\u0005\u000b\rb'\u0019\u0001\u000e\u0011\tA\u0001AP \u0005\u000b\u0003\u000ba'\u0011!Q\u0001\n\u0005\u0005\u0011AC;oI\u0016\u0014H._5oO\"I!\b\u001cB\u0001B\u0003%\u0011\u0011\u0002\t\u0005\u0019qbh\u0010\u0003\u0004PY\u0012\u0005\u0011Q\u0002\u000b\u0007\u0003\u001f\t\t\"a\u0005\u0011\tecGP \u0005\t\u0003\u000b\tY\u00011\u0001\u0002\u0002!9!(a\u0003A\u0002\u0005%\u0001bBA\fY\u0012\u0005\u0013\u0011D\u0001\tIAdWo\u001d\u0013fcR!\u00111DA\u000f\u001b\u0005a\u0007\u0002CA\u0010\u0003+\u0001\r!!\t\u0002\u0005-4\b\u0003\u0002\u0007\u0015yzDq!!\nm\t\u0003\t9#A\u0005%[&tWo\u001d\u0013fcR!\u00111DA\u0015\u0011\u001d\tY#a\tA\u0002q\f1a[3z\u0011\u0019\u0011D\u000e\"\u0011\u00020U\u0011\u0011q\u0002\u0005\b\u0003gaG\u0011IA\u001b\u0003\u001d)\b\u000fZ1uK\u0012,B!a\u000e\u0002>Q1\u0011\u0011HA\"\u0003\u000b\u0002R!\u00177}\u0003w\u00012aFA\u001f\t!\ty$!\rC\u0002\u0005\u0005#A\u0001\"2#\tqh\u0004C\u0004\u0002,\u0005E\u0002\u0019\u0001?\t\u0011\u0005\u001d\u0013\u0011\u0007a\u0001\u0003w\tQA^1mk\u0016Dq!a\u0013m\t\u0003\ni%A\u0003%a2,8/\u0006\u0003\u0002P\u0005UC\u0003BA)\u0003/\u0002R!\u00177}\u0003'\u00022aFA+\t!\ty$!\u0013C\u0002\u0005\u0005\u0003\u0002CA\u0010\u0003\u0013\u0002\r!!\u0017\u0011\u000b1!B0a\u0015\t\u000f\u0005uC\u000e\"\u0011\u0002`\u00051A%\\5okN$B!a\u0004\u0002b!9\u00111FA.\u0001\u0004a\bBB\u001cm\t\u0003\n)\u0007\u0006\u0003\u0002\u0002\u0005\u001d\u0004b\u0002\u001e\u0002d\u0001\u0007\u0011\u0011\u0002\u0005\u0007\u007f1$\t%a\u001b\u0015\t\u0005\u0005\u0011Q\u000e\u0005\u0007u\u0005%\u0004\u0019\u0001@")
public interface Map<A, B>
extends Iterable<Tuple2<A, B>>,
scala.collection.Map<A, B>,
MapLike<A, B, Map<A, B>> {
    @Override
    public Map<A, B> empty();

    @Override
    public Map<A, B> seq();

    public Map<A, B> withDefault(Function1<A, B> var1);

    public Map<A, B> withDefaultValue(B var1);

    public static class WithDefault<A, B>
    extends Map.WithDefault<A, B>
    implements Map<A, B> {
        private final Map<A, B> underlying;
        private final Function1<A, B> d;

        @Override
        public Map<A, B> seq() {
            return Map$class.seq(this);
        }

        @Override
        public Builder<Tuple2<A, B>, Map<A, B>> newBuilder() {
            return MapLike$class.newBuilder(this);
        }

        @Override
        public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
            return MapLike$class.parCombiner(this);
        }

        @Override
        public Option<B> put(A key, B value) {
            return MapLike$class.put(this, key, value);
        }

        @Override
        public void update(A key, B value) {
            MapLike$class.update(this, key, value);
        }

        @Override
        public <B1> Map<A, B1> $plus(Tuple2<A, B1> elem1, Tuple2<A, B1> elem2, Seq<Tuple2<A, B1>> elems) {
            return MapLike$class.$plus(this, elem1, elem2, elems);
        }

        @Override
        public <B1> Map<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> xs) {
            return MapLike$class.$plus$plus(this, xs);
        }

        @Override
        public Option<B> remove(A key) {
            return MapLike$class.remove(this, key);
        }

        @Override
        public void clear() {
            MapLike$class.clear(this);
        }

        @Override
        public B getOrElseUpdate(A key, Function0<B> op) {
            return (B)MapLike$class.getOrElseUpdate(this, key, op);
        }

        @Override
        public MapLike<A, B, Map<A, B>> transform(Function2<A, B, B> f) {
            return MapLike$class.transform(this, f);
        }

        @Override
        public MapLike<A, B, Map<A, B>> retain(Function2<A, B, Object> p) {
            return MapLike$class.retain(this, p);
        }

        @Override
        public Map<A, B> clone() {
            return MapLike$class.clone(this);
        }

        @Override
        public Map<A, B> result() {
            return MapLike$class.result(this);
        }

        @Override
        public Map<A, B> $minus(A elem1, A elem2, Seq<A> elems) {
            return MapLike$class.$minus(this, elem1, elem2, elems);
        }

        @Override
        public Map<A, B> $minus$minus(GenTraversableOnce<A> xs) {
            return MapLike$class.$minus$minus(this, xs);
        }

        @Override
        public /* synthetic */ Object scala$collection$mutable$Cloneable$$super$clone() {
            return super.clone();
        }

        @Override
        public Shrinkable<A> $minus$eq(A elem1, A elem2, Seq<A> elems) {
            return Shrinkable$class.$minus$eq(this, elem1, elem2, elems);
        }

        @Override
        public Shrinkable<A> $minus$minus$eq(TraversableOnce<A> xs) {
            return Shrinkable$class.$minus$minus$eq(this, xs);
        }

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
        public <NewTo> Builder<Tuple2<A, B>, NewTo> mapResult(Function1<Map<A, B>, NewTo> f) {
            return Builder$class.mapResult(this, f);
        }

        @Override
        public Growable $plus$eq(Object elem1, Object elem2, Seq elems) {
            return Growable$class.$plus$eq(this, elem1, elem2, elems);
        }

        @Override
        public Growable<Tuple2<A, B>> $plus$plus$eq(TraversableOnce<Tuple2<A, B>> xs) {
            return Growable$class.$plus$plus$eq(this, xs);
        }

        @Override
        public GenericCompanion<Iterable> companion() {
            return Iterable$class.companion(this);
        }

        @Override
        public WithDefault<A, B> $plus$eq(Tuple2<A, B> kv) {
            this.underlying.$plus$eq(kv);
            return this;
        }

        public WithDefault<A, B> $minus$eq(A key) {
            this.underlying.$minus$eq(key);
            return this;
        }

        @Override
        public WithDefault<A, B> empty() {
            return new WithDefault<A, B>(this.underlying.empty(), this.d);
        }

        @Override
        public <B1> WithDefault<A, B1> updated(A key, B1 value) {
            return new WithDefault<A, B1>(this.underlying.updated(key, value), this.d);
        }

        @Override
        public <B1> WithDefault<A, B1> $plus(Tuple2<A, B1> kv) {
            return this.updated((Object)kv._1(), (Object)kv._2());
        }

        @Override
        public WithDefault<A, B> $minus(A key) {
            return new WithDefault<A, B>(this.underlying.$minus(key), this.d);
        }

        @Override
        public Map<A, B> withDefault(Function1<A, B> d) {
            return new WithDefault<A, B>(this.underlying, d);
        }

        @Override
        public Map<A, B> withDefaultValue(B d) {
            return new WithDefault<A, B>(this.underlying, new Serializable(this, d){
                public static final long serialVersionUID = 0L;
                private final Object d$1;

                public final B apply(A x) {
                    return (B)this.d$1;
                }
                {
                    this.d$1 = d$1;
                }
            });
        }

        public WithDefault(Map<A, B> underlying, Function1<A, B> d) {
            this.underlying = underlying;
            this.d = d;
            super(underlying, d);
            Traversable$class.$init$(this);
            Iterable$class.$init$(this);
            Growable$class.$init$(this);
            Builder$class.$init$(this);
            Shrinkable$class.$init$(this);
            Cloneable$class.$init$(this);
            MapLike$class.$init$(this);
            Map$class.$init$(this);
        }
    }
}

