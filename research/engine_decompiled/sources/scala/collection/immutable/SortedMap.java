/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.Option;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.Seq;
import scala.collection.SortedMap;
import scala.collection.SortedMapLike;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Set$;
import scala.collection.immutable.Set$class;
import scala.collection.immutable.SortedSet;
import scala.collection.immutable.SortedSet$;
import scala.collection.immutable.SortedSet$class;
import scala.collection.immutable.Traversable$class;
import scala.collection.mutable.Builder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSet;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\tma\u0001C\u0001\u0003!\u0003\r\t!C\u0014\u0003\u0013M{'\u000f^3e\u001b\u0006\u0004(BA\u0002\u0005\u0003%IW.\\;uC\ndWM\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001!F\u0002\u000b+}\u0019b\u0001A\u0006\u0010C\u0011B\u0003C\u0001\u0007\u000e\u001b\u00051\u0011B\u0001\b\u0007\u0005\u0019\te.\u001f*fMB!\u0001#E\n\u001f\u001b\u0005\u0011\u0011B\u0001\n\u0003\u0005\ri\u0015\r\u001d\t\u0003)Ua\u0001\u0001B\u0003\u0017\u0001\t\u0007qCA\u0001B#\tA2\u0004\u0005\u0002\r3%\u0011!D\u0002\u0002\b\u001d>$\b.\u001b8h!\taA$\u0003\u0002\u001e\r\t\u0019\u0011I\\=\u0011\u0005QyBA\u0002\u0011\u0001\t\u000b\u0007qCA\u0001C!\u0011\u00113e\u0005\u0010\u000e\u0003\u0011I!!\u0001\u0003\u0011\u000bA)3CH\u0014\n\u0005\u0019\u0012!aB'ba2K7.\u001a\t\u0005!\u0001\u0019b\u0004E\u0003#SMqr%\u0003\u0002+\t\ti1k\u001c:uK\u0012l\u0015\r\u001d'jW\u0016DQ\u0001\f\u0001\u0005\u00025\na\u0001J5oSR$C#\u0001\u0018\u0011\u00051y\u0013B\u0001\u0019\u0007\u0005\u0011)f.\u001b;\t\rI\u0002\u0001\u0015\"\u00154\u0003)qWm\u001e\"vS2$WM]\u000b\u0002iA!Q\u0007\u000f\u001e(\u001b\u00051$BA\u001c\u0005\u0003\u001diW\u000f^1cY\u0016L!!\u000f\u001c\u0003\u000f\t+\u0018\u000e\u001c3feB!AbO\n\u001f\u0013\tadA\u0001\u0004UkBdWM\r\u0005\u0006}\u0001!\teP\u0001\u0006K6\u0004H/_\u000b\u0002O!)\u0011\t\u0001C!\u0005\u00069Q\u000f\u001d3bi\u0016$WCA\"G)\r!\u0015j\u0013\t\u0005!\u0001\u0019R\t\u0005\u0002\u0015\r\u0012)q\t\u0011b\u0001\u0011\n\u0011!)M\t\u0003=mAQA\u0013!A\u0002M\t1a[3z\u0011\u0015a\u0005\t1\u0001F\u0003\u00151\u0018\r\\;f\u0011\u0015q\u0005\u0001\"\u0011P\u0003\u0019YW-_*fiV\t\u0001\u000bE\u0002\u0011#NI!A\u0015\u0002\u0003\u0013M{'\u000f^3e'\u0016$h\u0001\u0002+\u0001\u0011U\u00131\u0003R3gCVdGoS3z'>\u0014H/\u001a3TKR\u001c2a\u0015,Q!\t9\u0006,D\u0001\u0001\u0013\t!\u0016\u0006C\u0003['\u0012\u00051,\u0001\u0004=S:LGO\u0010\u000b\u00029B\u0011qk\u0015\u0005\u0006=N#\teX\u0001\u0006IAdWo\u001d\u000b\u0003!\u0002DQ!Y/A\u0002M\tA!\u001a7f[\")1m\u0015C!I\u00061A%\\5okN$\"\u0001U3\t\u000b\u0005\u0014\u0007\u0019A\n\t\u000b\u001d\u001cF\u0011\t5\u0002\u0013I\fgnZ3J[BdGc\u0001)j]\")!N\u001aa\u0001W\u0006!aM]8n!\raAnE\u0005\u0003[\u001a\u0011aa\u00149uS>t\u0007\"B8g\u0001\u0004Y\u0017!B;oi&d\u0007\"B9T\t\u0003\u0012\u0018!\u0002;p'\u0016$XCA:y+\u0005!\bc\u0001\tvo&\u0011aO\u0001\u0002\u0004'\u0016$\bC\u0001\u000by\t\u0015I\bO1\u0001{\u0005\u0005\u0019\u0015CA\n\u001c\u0011\u0015q\u0006\u0001\"\u0001}+\ri\u0018\u0011\u0001\u000b\u0004}\u0006\r\u0001\u0003\u0002\t\u0001'}\u00042\u0001FA\u0001\t\u001595P1\u0001I\u0011\u001d\t)a\u001fa\u0001\u0003\u000f\t!a\u001b<\u0011\t1Y4c \u0005\u0007=\u0002!\t%a\u0003\u0016\t\u00055\u00111\u0003\u000b\t\u0003\u001f\t)\"a\u0007\u0002 A)\u0001\u0003A\n\u0002\u0012A\u0019A#a\u0005\u0005\r\u001d\u000bIA1\u0001I\u0011!\t9\"!\u0003A\u0002\u0005e\u0011!B3mK6\f\u0004#\u0002\u0007<'\u0005E\u0001\u0002CA\u000f\u0003\u0013\u0001\r!!\u0007\u0002\u000b\u0015dW-\u001c\u001a\t\u0011\u0005\u0005\u0012\u0011\u0002a\u0001\u0003G\tQ!\u001a7f[N\u0004R\u0001DA\u0013\u00033I1!a\n\u0007\u0005)a$/\u001a9fCR,GM\u0010\u0005\b\u0003W\u0001A\u0011IA\u0017\u0003)!\u0003\u000f\\;tIAdWo]\u000b\u0005\u0003_\t)\u0004\u0006\u0003\u00022\u0005]\u0002#\u0002\t\u0001'\u0005M\u0002c\u0001\u000b\u00026\u00111q)!\u000bC\u0002!C\u0001\"!\u000f\u0002*\u0001\u0007\u00111H\u0001\u0003qN\u0004RAIA\u001f\u0003\u0003J1!a\u0010\u0005\u0005I9UM\u001c+sCZ,'o]1cY\u0016|enY3\u0011\u000b1Y4#a\r\t\u000f\u0005\u0015\u0003\u0001\"\u0011\u0002H\u0005Qa-\u001b7uKJ\\U-_:\u0015\u0007\u001d\nI\u0005\u0003\u0005\u0002L\u0005\r\u0003\u0019AA'\u0003\u0005\u0001\bC\u0002\u0007\u0002PM\t\u0019&C\u0002\u0002R\u0019\u0011\u0011BR;oGRLwN\\\u0019\u0011\u00071\t)&C\u0002\u0002X\u0019\u0011qAQ8pY\u0016\fg\u000eC\u0004\u0002\\\u0001!\t%!\u0018\u0002\u00135\f\u0007OV1mk\u0016\u001cX\u0003BA0\u0003K\"B!!\u0019\u0002hA)\u0001\u0003A\n\u0002dA\u0019A#!\u001a\u0005\re\fIF1\u0001\u0018\u0011!\tI'!\u0017A\u0002\u0005-\u0014!\u00014\u0011\r1\tyEHA2\u000f\u001d\tyG\u0001E\u0001\u0003c\n\u0011bU8si\u0016$W*\u00199\u0011\u0007A\t\u0019H\u0002\u0004\u0002\u0005!\u0005\u0011QO\n\u0005\u0003g\n9\b\u0005\u0004\u0002z\u0005}\u00141Q\u0007\u0003\u0003wR1!! \u0005\u0003\u001d9WM\\3sS\u000eLA!!!\u0002|\tI\u0012*\\7vi\u0006\u0014G.Z*peR,G-T1q\r\u0006\u001cGo\u001c:z!\t\u0001\u0002\u0001C\u0004[\u0003g\"\t!a\"\u0015\u0005\u0005E\u0004\u0002CAF\u0003g\"\u0019!!$\u0002\u0019\r\fgNQ;jY\u00124%o\\7\u0016\r\u0005=\u0015qUAV)\u0011\t\t*a,\u0011\u0015\u0005e\u00141SAL\u0003G\u000bi+\u0003\u0003\u0002\u0016\u0006m$\u0001D\"b]\n+\u0018\u000e\u001c3Ge>l\u0007\u0003BAM\u00037k!!a\u001d\n\t\u0005u\u0015q\u0014\u0002\u0005\u0007>dG.\u0003\u0003\u0002\"\u0006m$\u0001E*peR,G-T1q\r\u0006\u001cGo\u001c:z!\u0019a1(!*\u0002*B\u0019A#a*\u0005\rY\tII1\u0001\u0018!\r!\u00121\u0016\u0003\u0007A\u0005%%\u0019A\f\u0011\rA\u0001\u0011QUAU\u0011!\t\t,!#A\u0004\u0005M\u0016aA8sIB1\u0011QWA^\u0003Ks1\u0001DA\\\u0013\r\tILB\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\ti,a0\u0003\u0011=\u0013H-\u001a:j]\u001eT1!!/\u0007\u0011\u001dq\u00141\u000fC\u0001\u0003\u0007,b!!2\u0002L\u0006=G\u0003BAd\u0003#\u0004b\u0001\u0005\u0001\u0002J\u00065\u0007c\u0001\u000b\u0002L\u00121a#!1C\u0002]\u00012\u0001FAh\t\u0019\u0001\u0013\u0011\u0019b\u0001/!A\u0011\u0011WAa\u0001\b\t\u0019\u000e\u0005\u0004\u00026\u0006m\u0016\u0011\u001a\u0004\u000e\u0003/\f\u0019\b%A\u0002\u0002\u0011\tIN!\u0007\u0003\u000f\u0011+g-Y;miV1\u00111\\Aq\u0003K\u001cr!!6\f\u0003;\f9\u000f\u0005\u0004\u0011\u0001\u0005}\u00171\u001d\t\u0004)\u0005\u0005HA\u0002\f\u0002V\n\u0007q\u0003E\u0002\u0015\u0003K$q\u0001IAk\t\u000b\u0007q\u0003\u0005\u0005\u0002j\u0006m\u0018q\\Ar\u001d\u0011\tY/!?\u000f\t\u00055\u0018q\u001f\b\u0005\u0003_\f)0\u0004\u0002\u0002r*\u0019\u00111\u001f\u0005\u0002\rq\u0012xn\u001c;?\u0013\u00059\u0011BA\u0003\u0007\u0013\r\ty\u0007B\u0005\u0005\u0003/\fiPC\u0002\u0002p\u0011Aa\u0001LAk\t\u0003i\u0003b\u00020\u0002V\u0012\u0005#1A\u000b\u0005\u0005\u000b\u0011Y\u0001\u0006\u0003\u0003\b\t=\u0001C\u0002\t\u0001\u0003?\u0014I\u0001E\u0002\u0015\u0005\u0017!qa\u0012B\u0001\u0005\u0004\u0011i!E\u0002\u0002dnA\u0001\"!\u0002\u0003\u0002\u0001\u0007!\u0011\u0003\t\u0007\u0019m\nyN!\u0003\t\u000f\r\f)\u000e\"\u0011\u0003\u0016Q!\u0011Q\u001cB\f\u0011\u001dQ%1\u0003a\u0001\u0003?\u0004\u0002\"!'\u0002V\u0006}\u00171\u001d")
public interface SortedMap<A, B>
extends Map<A, B>,
scala.collection.SortedMap<A, B> {
    @Override
    public Builder<Tuple2<A, B>, SortedMap<A, B>> newBuilder();

    @Override
    public SortedMap<A, B> empty();

    @Override
    public <B1> SortedMap<A, B1> updated(A var1, B1 var2);

    @Override
    public SortedSet<A> keySet();

    @Override
    public <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> var1);

    @Override
    public <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> var1, Tuple2<A, B1> var2, Seq<Tuple2<A, B1>> var3);

    @Override
    public <B1> SortedMap<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> var1);

    @Override
    public SortedMap<A, B> filterKeys(Function1<A, Object> var1);

    @Override
    public <C> SortedMap<A, C> mapValues(Function1<B, C> var1);

    public static interface Default<A, B>
    extends SortedMap<A, B>,
    SortedMap.Default<A, B> {
        @Override
        public <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> var1);

        @Override
        public SortedMap<A, B> $minus(A var1);
    }

    public class DefaultKeySortedSet
    extends SortedMapLike.DefaultKeySortedSet
    implements SortedSet<A> {
        @Override
        public SortedSet<A> empty() {
            return SortedSet$class.empty(this);
        }

        @Override
        public GenericCompanion<Set> companion() {
            return Set$class.companion(this);
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
        public SortedSet<A> $plus(A elem) {
            return this.apply(elem) ? this : (SortedSet)SortedSet$.MODULE$.apply(Nil$.MODULE$, this.ordering()).$plus$plus(this).$plus(elem);
        }

        @Override
        public SortedSet<A> $minus(A elem) {
            return this.apply(elem) ? (SortedSet)SortedSet$.MODULE$.apply(Nil$.MODULE$, this.ordering()).$plus$plus(this).$minus(elem) : this;
        }

        @Override
        public SortedSet<A> rangeImpl(Option<A> from2, Option<A> until2) {
            SortedMap map2 = (SortedMap)this.scala$collection$immutable$SortedMap$DefaultKeySortedSet$$$outer().rangeImpl(from2, until2);
            return new DefaultKeySortedSet(map2);
        }

        @Override
        public <C> Set<C> toSet() {
            Builder sb = Set$.MODULE$.newBuilder();
            this.foreach(new Serializable(this, sb){
                public static final long serialVersionUID = 0L;
                private final Builder sb$1;

                public final Builder<C, Set<C>> apply(A x$1) {
                    return this.sb$1.$plus$eq(x$1);
                }
                {
                    this.sb$1 = sb$1;
                }
            });
            return (Set)sb.result();
        }

        public /* synthetic */ SortedMap scala$collection$immutable$SortedMap$DefaultKeySortedSet$$$outer() {
            return (SortedMap)this.$outer;
        }

        public DefaultKeySortedSet(SortedMap<A, B> $outer) {
            super($outer);
            Traversable$class.$init$(this);
            Iterable$class.$init$(this);
            Set$class.$init$(this);
            SortedSet$class.$init$(this);
        }
    }
}

