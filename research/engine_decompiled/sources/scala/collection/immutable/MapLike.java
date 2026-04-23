/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.Function2;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.MapLike;
import scala.collection.Seq;
import scala.collection.SetLike;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Set$;
import scala.collection.immutable.Set$class;
import scala.collection.immutable.Traversable$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParMap;
import scala.collection.parallel.immutable.ParSet;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\u0005Ee\u0001C\u0001\u0003!\u0003\r\t!C\u0014\u0003\u000f5\u000b\u0007\u000fT5lK*\u00111\u0001B\u0001\nS6lW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001)BA\u0003\u000b\u001fCM!\u0001aC\b-!\taQ\"D\u0001\u0007\u0013\tqaA\u0001\u0004B]f\u0014VM\u001a\t\u0006!E\u0011R\u0004I\u0007\u0002\t%\u0011\u0011\u0001\u0002\t\u0003'Qa\u0001\u0001B\u0003\u0016\u0001\t\u0007aCA\u0001B#\t9\"\u0004\u0005\u0002\r1%\u0011\u0011D\u0002\u0002\b\u001d>$\b.\u001b8h!\ta1$\u0003\u0002\u001d\r\t\u0019\u0011I\\=\u0011\u0005MqBAB\u0010\u0001\t\u000b\u0007aCA\u0001C!\t\u0019\u0012\u0005\u0002\u0004#\u0001\u0011\u0015\ra\t\u0002\u0005)\"L7/\u0005\u0002\u0018II\u0019QeJ\u0015\u0007\t\u0019\u0002\u0001\u0001\n\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0006Q\u0001\u0011R\u0004I\u0007\u0002\u0005A!\u0001F\u000b\n\u001e\u0013\tY#AA\u0002NCB\u0004B\u0001E\u00170e%\u0011a\u0006\u0002\u0002\u000f!\u0006\u0014\u0018\r\u001c7fY&T\u0018M\u00197f!\u0011a\u0001GE\u000f\n\u0005E2!A\u0002+va2,'\u0007\u0005\u00034oIiR\"\u0001\u001b\u000b\u0005\r)$B\u0001\u001c\u0005\u0003!\u0001\u0018M]1mY\u0016d\u0017B\u0001\u001d5\u0005\u0019\u0001\u0016M]'ba\")!\b\u0001C\u0001w\u00051A%\u001b8ji\u0012\"\u0012\u0001\u0010\t\u0003\u0019uJ!A\u0010\u0004\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0001\u0002\u0001K\u0011K!\u0002\u0017A\f'oQ8nE&tWM]\u000b\u0002\u0005B!1\tR\u00183\u001b\u0005)\u0014BA#6\u0005!\u0019u.\u001c2j]\u0016\u0014\b\"B$\u0001\t\u0003B\u0015aB;qI\u0006$X\rZ\u000b\u0003\u00132#2AS(R!\u0011A#FE&\u0011\u0005MaE!B'G\u0005\u0004q%A\u0001\"2#\ti\"\u0004C\u0003Q\r\u0002\u0007!#A\u0002lKfDQA\u0015$A\u0002-\u000bQA^1mk\u0016DQ\u0001\u0016\u0001\u0007\u0002U\u000bQ\u0001\n9mkN,\"AV-\u0015\u0005]S\u0006\u0003\u0002\u0015+%a\u0003\"aE-\u0005\u000b5\u001b&\u0019\u0001(\t\u000bm\u001b\u0006\u0019\u0001/\u0002\u0005-4\b\u0003\u0002\u00071%aCQ\u0001\u0016\u0001\u0005By+\"a\u00182\u0015\t\u0001\u001cg\r\u001b\t\u0005Q)\u0012\u0012\r\u0005\u0002\u0014E\u0012)Q*\u0018b\u0001\u001d\")A-\u0018a\u0001K\u0006)Q\r\\3ncA!A\u0002\r\nb\u0011\u00159W\f1\u0001f\u0003\u0015)G.Z73\u0011\u0015IW\f1\u0001k\u0003\u0015)G.Z7t!\ra1.Z\u0005\u0003Y\u001a\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?\u0011\u0015q\u0007\u0001\"\u0011p\u0003)!\u0003\u000f\\;tIAdWo]\u000b\u0003aN$\"!\u001d;\u0011\t!R#C\u001d\t\u0003'M$Q!T7C\u00029CQ!^7A\u0002Y\f!\u0001_:\u0011\u0007A9\u00180\u0003\u0002y\t\t\u0011r)\u001a8Ue\u00064XM]:bE2,wJ\\2f!\u0011a\u0001G\u0005:\t\u000bm\u0004A\u0011\t?\u0002\u0015\u0019LG\u000e^3s\u0017\u0016L8\u000f\u0006\u0002*{\")aP\u001fa\u0001\u007f\u0006\t\u0001\u000f\u0005\u0004\r\u0003\u0003\u0011\u0012QA\u0005\u0004\u0003\u00071!!\u0003$v]\u000e$\u0018n\u001c82!\ra\u0011qA\u0005\u0004\u0003\u00131!a\u0002\"p_2,\u0017M\u001c\u0005\b\u0003\u001b\u0001A\u0011IA\b\u0003%i\u0017\r\u001d,bYV,7/\u0006\u0003\u0002\u0012\u0005]A\u0003BA\n\u00037\u0001R\u0001\u000b\u0016\u0013\u0003+\u00012aEA\f\t\u001d\tI\"a\u0003C\u0002Y\u0011\u0011a\u0011\u0005\t\u0003;\tY\u00011\u0001\u0002 \u0005\ta\r\u0005\u0004\r\u0003\u0003i\u0012Q\u0003\u0005\b\u0003G\u0001A\u0011IA\u0013\u0003\u0019YW-_*fiV\u0011\u0011q\u0005\t\u0005Q\u0005%\"#C\u0002\u0002,\t\u00111aU3u\r\u0019\ty\u0003\u0001\u0005\u00022\t1\u0012*\\7vi\u0006\u0014G.\u001a#fM\u0006,H\u000e^&fsN+Go\u0005\u0004\u0002.\u0005M\u0012q\u0005\t\u0005\u0003k\t9$D\u0001\u0001\u0013\r\tI$\u0005\u0002\u000e\t\u00164\u0017-\u001e7u\u0017\u0016L8+\u001a;\t\u0011\u0005u\u0012Q\u0006C\u0001\u0003\u007f\ta\u0001P5oSRtDCAA!!\u0011\t)$!\f\t\u000fQ\u000bi\u0003\"\u0011\u0002FQ!\u0011qEA$\u0011\u001d\tI%a\u0011A\u0002I\tA!\u001a7f[\"A\u0011QJA\u0017\t\u0003\ny%\u0001\u0004%[&tWo\u001d\u000b\u0005\u0003O\t\t\u0006C\u0004\u0002J\u0005-\u0003\u0019\u0001\n\t\u0011\u0005U\u0013Q\u0006C!\u0003/\nQ\u0001^8TKR,B!!\u0017\u0002`U\u0011\u00111\f\t\u0006Q\u0005%\u0012Q\f\t\u0004'\u0005}CaB\u0010\u0002T\t\u0007\u0011\u0011M\t\u0003%iAq!!\u001a\u0001\t\u0003\t9'A\u0005ue\u0006t7OZ8s[V1\u0011\u0011NAD\u0003_\"B!a\u001b\u0002\nR!\u0011QNA:!\r\u0019\u0012q\u000e\u0003\b\u0003c\n\u0019G1\u0001\u0017\u0005\u0011!\u0006.\u0019;\t\u0011\u0005U\u00141\ra\u0002\u0003o\n!A\u00194\u0011\u0013\u0005e\u0014q\u0010\u0011\u0002\u0004\u00065TBAA>\u0015\r\ti\bB\u0001\bO\u0016tWM]5d\u0013\u0011\t\t)a\u001f\u0003\u0019\r\u000bgNQ;jY\u00124%o\\7\u0011\u000b1\u0001$#!\"\u0011\u0007M\t9\tB\u0004\u0002\u001a\u0005\r$\u0019\u0001\f\t\u0011\u0005u\u00111\ra\u0001\u0003\u0017\u0003r\u0001DAG%u\t))C\u0002\u0002\u0010\u001a\u0011\u0011BR;oGRLwN\u001c\u001a")
public interface MapLike<A, B, This extends MapLike<A, B, This> & Map<A, B>>
extends scala.collection.MapLike<A, B, This> {
    @Override
    public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner();

    @Override
    public <B1> Map<A, B1> updated(A var1, B1 var2);

    @Override
    public <B1> Map<A, B1> $plus(Tuple2<A, B1> var1);

    @Override
    public <B1> Map<A, B1> $plus(Tuple2<A, B1> var1, Tuple2<A, B1> var2, Seq<Tuple2<A, B1>> var3);

    @Override
    public <B1> Map<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> var1);

    @Override
    public Map<A, B> filterKeys(Function1<A, Object> var1);

    @Override
    public <C> Map<A, C> mapValues(Function1<B, C> var1);

    @Override
    public Set<A> keySet();

    public <C, That> That transform(Function2<A, B, C> var1, CanBuildFrom<This, Tuple2<A, C>, That> var2);

    public class ImmutableDefaultKeySet
    extends MapLike.DefaultKeySet
    implements Set<A> {
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
        public Set<A> $plus(A elem) {
            return this.apply(elem) ? this : (Set)((SetLike)Set$.MODULE$.apply(Nil$.MODULE$)).$plus$plus(this).$plus(elem);
        }

        @Override
        public Set<A> $minus(A elem) {
            return this.apply(elem) ? (Set)((SetLike)Set$.MODULE$.apply(Nil$.MODULE$)).$plus$plus(this).$minus(elem) : this;
        }

        @Override
        public <B> Set<B> toSet() {
            return this;
        }

        public /* synthetic */ MapLike scala$collection$immutable$MapLike$ImmutableDefaultKeySet$$$outer() {
            return (MapLike)this.$outer;
        }

        public ImmutableDefaultKeySet(MapLike<A, B, This> $outer) {
            Traversable$class.$init$(this);
            Iterable$class.$init$(this);
            Set$class.$init$(this);
        }
    }
}

