/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Predef$;
import scala.Serializable;
import scala.collection.GenTraversable;
import scala.collection.IndexedSeq;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory$;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.NumericRange$;
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Numeric$IntIsIntegral$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\t\u0005e!B\u0001\u0003\u0003\u0003I!!F$f]R\u0013\u0018M^3sg\u0006\u0014G.\u001a$bGR|'/\u001f\u0006\u0003\u0007\u0011\tqaZ3oKJL7M\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001!\u0006\u0002\u000b#M\u0011\u0001a\u0003\t\u0004\u00195yQ\"\u0001\u0002\n\u00059\u0011!\u0001E$f]\u0016\u0014\u0018nY\"p[B\fg.[8o!\t\u0001\u0012\u0003\u0004\u0001\u0005\u000bI\u0001!\u0019A\n\u0003\u0005\r\u001bUC\u0001\u000b\"#\t)\u0012\u0004\u0005\u0002\u0017/5\ta!\u0003\u0002\u0019\r\t9aj\u001c;iS:<'c\u0001\u000e\u001dO\u0019!1\u0004\u0001\u0001\u001a\u00051a$/\u001a4j]\u0016lWM\u001c;?!\rib\u0004I\u0007\u0002\t%\u0011q\u0004\u0002\u0002\u000f\u000f\u0016tGK]1wKJ\u001c\u0018M\u00197f!\t\u0001\u0012\u0005B\u0003##\t\u00071EA\u0001Y#\t)B\u0005\u0005\u0002\u0017K%\u0011aE\u0002\u0002\u0004\u0003:L\b\u0003\u0002\u0007)A=I!!\u000b\u0002\u00035\u001d+g.\u001a:jGR\u0013\u0018M^3sg\u0006\u0014G.\u001a+f[Bd\u0017\r^3\t\u000b-\u0002A\u0011\u0001\u0017\u0002\rqJg.\u001b;?)\u0005i\u0003c\u0001\u0007\u0001\u001f!1q\u0006\u0001Q\u0001\nA\n1CU3vg\u0006\u0014G.Z\"C\r&s7\u000f^1oG\u0016\u00042!\r\u001a\u0016\u001b\u0005\u0001a\u0001B\u001a\u0001\u0001Q\u00121cR3oKJL7mQ1o\u0005VLG\u000e\u001a$s_6,\"!\u000e\"\u0014\u0007I2\u0014\b\u0005\u0002\u0017o%\u0011\u0001H\u0002\u0002\u0007\u0003:L(+\u001a4\u0011\u000b1QD(\u0011#\n\u0005m\u0012!\u0001D\"b]\n+\u0018\u000e\u001c3Ge>l\u0007GA\u001f@!\r\u0001\u0012C\u0010\t\u0003!}\"\u0011\u0002\u0011\u001a\u0002\u0002\u0003\u0005)\u0011A\u0012\u0003\u0007}#\u0013\u0007\u0005\u0002\u0011\u0005\u0012)1I\rb\u0001G\t\t\u0011\tE\u0002\u0011#\u0005CQa\u000b\u001a\u0005\u0002\u0019#\u0012a\u0012\t\u0004cI\n\u0005\"B%3\t\u0003Q\u0015!B1qa2LHCA&R!\u0011au*\u0011#\u000e\u00035S!A\u0014\u0003\u0002\u000f5,H/\u00192mK&\u0011\u0001+\u0014\u0002\b\u0005VLG\u000eZ3s\u0011\u0015\u0011\u0006\n1\u0001T\u0003\u00111'o\\7\u0011\u0005E\"\u0016BA+\u000e\u0005\u0011\u0019u\u000e\u001c7\t\u000b%\u0013D\u0011A,\u0015\u0003-CQ!\u0017\u0001\u0005\u0002i\u000b1BU3vg\u0006\u0014G.Z\"C\rV\t\u0001\u0007C\u0003]\u0001\u0011\u0005Q,\u0001\u0004d_:\u001c\u0017\r^\u000b\u0003=\u0006$\"a\u00182\u0011\u0007A\t\u0002\r\u0005\u0002\u0011C\u0012)1i\u0017b\u0001G!)1m\u0017a\u0001I\u0006\u0019\u0001p]:\u0011\u0007Y)w-\u0003\u0002g\r\tQAH]3qK\u0006$X\r\u001a \u0011\u0007uA\u0007-\u0003\u0002j\t\tYAK]1wKJ\u001c\u0018M\u00197f\u0011\u0015Y\u0007\u0001\"\u0001m\u0003\u00111\u0017\u000e\u001c7\u0016\u00055\fHC\u00018x)\ty'\u000fE\u0002\u0011#A\u0004\"\u0001E9\u0005\u000b\rS'\u0019A\u0012\t\rMTG\u00111\u0001u\u0003\u0011)G.Z7\u0011\u0007Y)\b/\u0003\u0002w\r\tAAHY=oC6,g\bC\u0003yU\u0002\u0007\u00110A\u0001o!\t1\"0\u0003\u0002|\r\t\u0019\u0011J\u001c;\t\u000b-\u0004A\u0011A?\u0016\u0007y\f9\u0001F\u0003\u0000\u0003\u001b\t\t\u0002\u0006\u0003\u0002\u0002\u0005%\u0001\u0003\u0002\t\u0012\u0003\u0007\u0001B\u0001E\t\u0002\u0006A\u0019\u0001#a\u0002\u0005\u000b\rc(\u0019A\u0012\t\u000fMdH\u00111\u0001\u0002\fA!a#^A\u0003\u0011\u0019\ty\u0001 a\u0001s\u0006\u0011a.\r\u0005\u0007\u0003'a\b\u0019A=\u0002\u00059\u0014\u0004BB6\u0001\t\u0003\t9\"\u0006\u0003\u0002\u001a\u0005\u0015B\u0003CA\u000e\u0003W\ti#a\f\u0015\t\u0005u\u0011q\u0005\t\u0005!E\ty\u0002\u0005\u0003\u0011#\u0005\u0005\u0002\u0003\u0002\t\u0012\u0003G\u00012\u0001EA\u0013\t\u0019\u0019\u0015Q\u0003b\u0001G!A1/!\u0006\u0005\u0002\u0004\tI\u0003\u0005\u0003\u0017k\u0006\r\u0002bBA\b\u0003+\u0001\r!\u001f\u0005\b\u0003'\t)\u00021\u0001z\u0011\u001d\t\t$!\u0006A\u0002e\f!A\\\u001a\t\r-\u0004A\u0011AA\u001b+\u0011\t9$!\u0012\u0015\u0015\u0005e\u00121JA'\u0003\u001f\n\t\u0006\u0006\u0003\u0002<\u0005\u001d\u0003\u0003\u0002\t\u0012\u0003{\u0001B\u0001E\t\u0002@A!\u0001#EA!!\u0011\u0001\u0012#a\u0011\u0011\u0007A\t)\u0005\u0002\u0004D\u0003g\u0011\ra\t\u0005\tg\u0006MB\u00111\u0001\u0002JA!a#^A\"\u0011\u001d\ty!a\rA\u0002eDq!a\u0005\u00024\u0001\u0007\u0011\u0010C\u0004\u00022\u0005M\u0002\u0019A=\t\u000f\u0005M\u00131\u0007a\u0001s\u0006\u0011a\u000e\u000e\u0005\u0007W\u0002!\t!a\u0016\u0016\t\u0005e\u0013\u0011\u000e\u000b\r\u00037\ny'!\u001d\u0002t\u0005U\u0014q\u000f\u000b\u0005\u0003;\nY\u0007\u0005\u0003\u0011#\u0005}\u0003\u0003\u0002\t\u0012\u0003C\u0002B\u0001E\t\u0002dA!\u0001#EA3!\u0011\u0001\u0012#a\u001a\u0011\u0007A\tI\u0007\u0002\u0004D\u0003+\u0012\ra\t\u0005\tg\u0006UC\u00111\u0001\u0002nA!a#^A4\u0011\u001d\ty!!\u0016A\u0002eDq!a\u0005\u0002V\u0001\u0007\u0011\u0010C\u0004\u00022\u0005U\u0003\u0019A=\t\u000f\u0005M\u0013Q\u000ba\u0001s\"9\u0011\u0011PA+\u0001\u0004I\u0018A\u000186\u0011\u001d\ti\b\u0001C\u0001\u0003\u007f\n\u0001\u0002^1ck2\fG/Z\u000b\u0005\u0003\u0003\u000bI\t\u0006\u0003\u0002\u0004\u0006UE\u0003BAC\u0003\u0017\u0003B\u0001E\t\u0002\bB\u0019\u0001#!#\u0005\r\r\u000bYH1\u0001$\u0011!\ti)a\u001fA\u0002\u0005=\u0015!\u00014\u0011\rY\t\t*_AD\u0013\r\t\u0019J\u0002\u0002\n\rVt7\r^5p]FBa\u0001_A>\u0001\u0004I\bbBA?\u0001\u0011\u0005\u0011\u0011T\u000b\u0005\u00037\u000b)\u000b\u0006\u0004\u0002\u001e\u0006=\u0016\u0011\u0017\u000b\u0005\u0003?\u000b9\u000b\u0005\u0003\u0011#\u0005\u0005\u0006\u0003\u0002\t\u0012\u0003G\u00032\u0001EAS\t\u0019\u0019\u0015q\u0013b\u0001G!A\u0011QRAL\u0001\u0004\tI\u000bE\u0004\u0017\u0003WK\u00180a)\n\u0007\u00055fAA\u0005Gk:\u001cG/[8oe!9\u0011qBAL\u0001\u0004I\bbBA\n\u0003/\u0003\r!\u001f\u0005\b\u0003{\u0002A\u0011AA[+\u0011\t9,a1\u0015\u0011\u0005e\u0016QZAh\u0003#$B!a/\u0002FB!\u0001#EA_!\u0011\u0001\u0012#a0\u0011\tA\t\u0012\u0011\u0019\t\u0004!\u0005\rGAB\"\u00024\n\u00071\u0005\u0003\u0005\u0002\u000e\u0006M\u0006\u0019AAd!!1\u0012\u0011Z=zs\u0006\u0005\u0017bAAf\r\tIa)\u001e8di&|gn\r\u0005\b\u0003\u001f\t\u0019\f1\u0001z\u0011\u001d\t\u0019\"a-A\u0002eDq!!\r\u00024\u0002\u0007\u0011\u0010C\u0004\u0002~\u0001!\t!!6\u0016\t\u0005]\u0017Q\u001d\u000b\u000b\u00033\fy/!=\u0002t\u0006UH\u0003BAn\u0003O\u0004B\u0001E\t\u0002^B!\u0001#EAp!\u0011\u0001\u0012#!9\u0011\tA\t\u00121\u001d\t\u0004!\u0005\u0015HAB\"\u0002T\n\u00071\u0005\u0003\u0005\u0002\u000e\u0006M\u0007\u0019AAu!%1\u00121^=zsf\f\u0019/C\u0002\u0002n\u001a\u0011\u0011BR;oGRLwN\u001c\u001b\t\u000f\u0005=\u00111\u001ba\u0001s\"9\u00111CAj\u0001\u0004I\bbBA\u0019\u0003'\u0004\r!\u001f\u0005\b\u0003'\n\u0019\u000e1\u0001z\u0011\u001d\ti\b\u0001C\u0001\u0003s,B!a?\u0003\fQa\u0011Q B\u000b\u0005/\u0011IBa\u0007\u0003\u001eQ!\u0011q B\u0007!\u0011\u0001\u0012C!\u0001\u0011\tA\t\"1\u0001\t\u0005!E\u0011)\u0001\u0005\u0003\u0011#\t\u001d\u0001\u0003\u0002\t\u0012\u0005\u0013\u00012\u0001\u0005B\u0006\t\u0019\u0019\u0015q\u001fb\u0001G!A\u0011QRA|\u0001\u0004\u0011y\u0001\u0005\u0006\u0017\u0005#I\u00180_=z\u0005\u0013I1Aa\u0005\u0007\u0005%1UO\\2uS>tW\u0007C\u0004\u0002\u0010\u0005]\b\u0019A=\t\u000f\u0005M\u0011q\u001fa\u0001s\"9\u0011\u0011GA|\u0001\u0004I\bbBA*\u0003o\u0004\r!\u001f\u0005\b\u0003s\n9\u00101\u0001z\u0011\u001d\u0011\t\u0003\u0001C\u0001\u0005G\tQA]1oO\u0016,BA!\n\u0003.Q1!q\u0005B\"\u0005\u000f\"BA!\u000b\u00032A!\u0001#\u0005B\u0016!\r\u0001\"Q\u0006\u0003\b\u0005_\u0011yB1\u0001$\u0005\u0005!\u0006B\u0003B\u001a\u0005?\t\t\u0011q\u0001\u00036\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\r\t]\"Q\bB\u0016\u001d\r1\"\u0011H\u0005\u0004\u0005w1\u0011a\u00029bG.\fw-Z\u0005\u0005\u0005\u007f\u0011\tE\u0001\u0005J]R,wM]1m\u0015\r\u0011YD\u0002\u0005\t\u0005\u000b\u0012y\u00021\u0001\u0003,\u0005)1\u000f^1si\"A!\u0011\nB\u0010\u0001\u0004\u0011Y#A\u0002f]\u0012DqA!\t\u0001\t\u0003\u0011i%\u0006\u0003\u0003P\t]C\u0003\u0003B)\u0005?\u0012\tGa\u0019\u0015\t\tM#\u0011\f\t\u0005!E\u0011)\u0006E\u0002\u0011\u0005/\"qAa\f\u0003L\t\u00071\u0005\u0003\u0006\u0003\\\t-\u0013\u0011!a\u0002\u0005;\n!\"\u001a<jI\u0016t7-\u001a\u00133!\u0019\u00119D!\u0010\u0003V!A!Q\tB&\u0001\u0004\u0011)\u0006\u0003\u0005\u0003J\t-\u0003\u0019\u0001B+\u0011!\u0011)Ga\u0013A\u0002\tU\u0013\u0001B:uKBDqA!\u001b\u0001\t\u0003\u0011Y'A\u0004ji\u0016\u0014\u0018\r^3\u0016\t\t5$Q\u000f\u000b\u0007\u0005_\u0012YH! \u0015\t\tE$q\u000f\t\u0005!E\u0011\u0019\bE\u0002\u0011\u0005k\"aa\u0011B4\u0005\u0004\u0019\u0003\u0002CAG\u0005O\u0002\rA!\u001f\u0011\u000fY\t\tJa\u001d\u0003t!A!Q\tB4\u0001\u0004\u0011\u0019\bC\u0004\u0003\u0000\t\u001d\u0004\u0019A=\u0002\u00071,g\u000e")
public abstract class GenTraversableFactory<CC extends GenTraversable<Object>>
extends GenericCompanion<CC> {
    private final GenericCanBuildFrom<Nothing$> ReusableCBFInstance = new GenericCanBuildFrom<Nothing$>(this){
        private final /* synthetic */ GenTraversableFactory $outer;

        public Builder<Nothing$, CC> apply() {
            return this.$outer.newBuilder();
        }
        {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    };

    public GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return this.ReusableCBFInstance;
    }

    public <A> CC concat(Seq<Traversable<A>> xss) {
        Builder b = this.newBuilder();
        if (xss.forall((Function1<Traversable<A>, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Traversable<A> x$1) {
                return x$1 instanceof IndexedSeq;
            }
        }))) {
            b.sizeHint(BoxesRunTime.unboxToInt(((TraversableOnce)xss.map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply(Traversable<A> x$2) {
                    return x$2.size();
                }
            }, Seq$.MODULE$.canBuildFrom())).sum(Numeric$IntIsIntegral$.MODULE$)));
        }
        xss.seq().foreach(new Serializable(this, b){
            public static final long serialVersionUID = 0L;
            private final Builder b$1;

            public final Builder<A, CC> apply(Traversable<A> xs) {
                return (Builder)this.b$1.$plus$plus$eq(xs);
            }
            {
                this.b$1 = b$1;
            }
        });
        return (CC)((GenTraversable)b.result());
    }

    public <A> CC fill(int n, Function0<A> elem) {
        Builder b = this.newBuilder();
        b.sizeHint(n);
        for (int i = 0; i < n; ++i) {
            b.$plus$eq(elem.apply());
        }
        return (CC)((GenTraversable)b.result());
    }

    public <A> CC fill(int n1, int n2, Function0<A> elem) {
        return this.tabulate(n1, (Function1<Object, A>)((Object)new Serializable(this, n2, elem){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ GenTraversableFactory $outer;
            private final int n2$4;
            private final Function0 elem$4;

            public final CC apply(int x$3) {
                return this.$outer.fill(this.n2$4, this.elem$4);
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.n2$4 = n2$4;
                this.elem$4 = var3_3;
            }
        }));
    }

    public <A> CC fill(int n1, int n2, int n3, Function0<A> elem) {
        return this.tabulate(n1, (Function1<Object, A>)((Object)new Serializable(this, n2, n3, elem){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ GenTraversableFactory $outer;
            private final int n2$3;
            private final int n3$3;
            private final Function0 elem$3;

            public final CC apply(int x$4) {
                return this.$outer.fill(this.n2$3, this.n3$3, this.elem$3);
            }
            {
                void var4_4;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.n2$3 = n2$3;
                this.n3$3 = n3$3;
                this.elem$3 = var4_4;
            }
        }));
    }

    public <A> CC fill(int n1, int n2, int n3, int n4, Function0<A> elem) {
        return this.tabulate(n1, (Function1<Object, A>)((Object)new Serializable(this, n2, n3, n4, elem){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ GenTraversableFactory $outer;
            private final int n2$2;
            private final int n3$2;
            private final int n4$2;
            private final Function0 elem$2;

            public final CC apply(int x$5) {
                return this.$outer.fill(this.n2$2, this.n3$2, this.n4$2, this.elem$2);
            }
            {
                void var5_5;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.n2$2 = n2$2;
                this.n3$2 = n3$2;
                this.n4$2 = n4$2;
                this.elem$2 = var5_5;
            }
        }));
    }

    public <A> CC fill(int n1, int n2, int n3, int n4, int n5, Function0<A> elem) {
        return this.tabulate(n1, (Function1<Object, A>)((Object)new Serializable(this, n2, n3, n4, n5, elem){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ GenTraversableFactory $outer;
            private final int n2$1;
            private final int n3$1;
            private final int n4$1;
            private final int n5$1;
            private final Function0 elem$1;

            public final CC apply(int x$6) {
                return this.$outer.fill(this.n2$1, this.n3$1, this.n4$1, this.n5$1, this.elem$1);
            }
            {
                void var6_6;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.n2$1 = n2$1;
                this.n3$1 = n3$1;
                this.n4$1 = n4$1;
                this.n5$1 = n5$1;
                this.elem$1 = var6_6;
            }
        }));
    }

    public <A> CC tabulate(int n, Function1<Object, A> f) {
        Builder b = this.newBuilder();
        b.sizeHint(n);
        for (int i = 0; i < n; ++i) {
            b.$plus$eq(f.apply(BoxesRunTime.boxToInteger(i)));
        }
        return (CC)((GenTraversable)b.result());
    }

    public <A> CC tabulate(int n1, int n2, Function2<Object, Object, A> f) {
        return this.tabulate(n1, (Function1<Object, A>)((Object)new Serializable(this, n2, f){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ GenTraversableFactory $outer;
            private final int n2$8;
            public final Function2 f$4;

            public final CC apply(int i1) {
                return this.$outer.tabulate(this.n2$8, new Serializable(this, i1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ $anonfun$tabulate$1 $outer;
                    private final int i1$1;

                    public final A apply(int x$7) {
                        return (A)this.$outer.f$4.apply(BoxesRunTime.boxToInteger(this.i1$1), BoxesRunTime.boxToInteger(x$7));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.i1$1 = i1$1;
                    }
                });
            }
            {
                void var3_3;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.n2$8 = n2$8;
                this.f$4 = var3_3;
            }
        }));
    }

    public <A> CC tabulate(int n1, int n2, int n3, Function3<Object, Object, Object, A> f) {
        return this.tabulate(n1, (Function1<Object, A>)((Object)new Serializable(this, n2, n3, f){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ GenTraversableFactory $outer;
            private final int n2$7;
            private final int n3$6;
            public final Function3 f$3;

            public final CC apply(int i1) {
                return this.$outer.tabulate(this.n2$7, this.n3$6, new Serializable(this, i1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ $anonfun$tabulate$2 $outer;
                    private final int i1$2;

                    public final A apply(int x$8, int x$9) {
                        return (A)this.$outer.f$3.apply(BoxesRunTime.boxToInteger(this.i1$2), BoxesRunTime.boxToInteger(x$8), BoxesRunTime.boxToInteger(x$9));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.i1$2 = i1$2;
                    }
                });
            }
            {
                void var4_4;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.n2$7 = n2$7;
                this.n3$6 = n3$6;
                this.f$3 = var4_4;
            }
        }));
    }

    public <A> CC tabulate(int n1, int n2, int n3, int n4, Function4<Object, Object, Object, Object, A> f) {
        return this.tabulate(n1, (Function1<Object, A>)((Object)new Serializable(this, n2, n3, n4, f){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ GenTraversableFactory $outer;
            private final int n2$6;
            private final int n3$5;
            private final int n4$4;
            public final Function4 f$2;

            public final CC apply(int i1) {
                return this.$outer.tabulate(this.n2$6, this.n3$5, this.n4$4, new Serializable(this, i1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ $anonfun$tabulate$3 $outer;
                    private final int i1$3;

                    public final A apply(int x$10, int x$11, int x$12) {
                        return (A)this.$outer.f$2.apply(BoxesRunTime.boxToInteger(this.i1$3), BoxesRunTime.boxToInteger(x$10), BoxesRunTime.boxToInteger(x$11), BoxesRunTime.boxToInteger(x$12));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.i1$3 = i1$3;
                    }
                });
            }
            {
                void var5_5;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.n2$6 = n2$6;
                this.n3$5 = n3$5;
                this.n4$4 = n4$4;
                this.f$2 = var5_5;
            }
        }));
    }

    public <A> CC tabulate(int n1, int n2, int n3, int n4, int n5, Function5<Object, Object, Object, Object, Object, A> f) {
        return this.tabulate(n1, (Function1<Object, A>)((Object)new Serializable(this, n2, n3, n4, n5, f){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ GenTraversableFactory $outer;
            private final int n2$5;
            private final int n3$4;
            private final int n4$3;
            private final int n5$2;
            public final Function5 f$1;

            public final CC apply(int i1) {
                return this.$outer.tabulate(this.n2$5, this.n3$4, this.n4$3, this.n5$2, new Serializable(this, i1){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ $anonfun$tabulate$4 $outer;
                    private final int i1$4;

                    public final A apply(int x$13, int x$14, int x$15, int x$16) {
                        return (A)this.$outer.f$1.apply(BoxesRunTime.boxToInteger(this.i1$4), BoxesRunTime.boxToInteger(x$13), BoxesRunTime.boxToInteger(x$14), BoxesRunTime.boxToInteger(x$15), BoxesRunTime.boxToInteger(x$16));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.i1$4 = i1$4;
                    }
                });
            }
            {
                void var6_6;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.n2$5 = n2$5;
                this.n3$4 = n3$4;
                this.n4$3 = n4$3;
                this.n5$2 = n5$2;
                this.f$1 = var6_6;
            }
        }));
    }

    public <T> CC range(T start, T end, Integral<T> evidence$1) {
        Predef$ predef$ = Predef$.MODULE$;
        return this.range(start, end, ((Numeric)evidence$1).one(), evidence$1);
    }

    public <T> CC range(T start, T end, T step, Integral<T> evidence$2) {
        Predef$ predef$ = Predef$.MODULE$;
        Integral<T> num = evidence$2;
        Object t = num.zero();
        if (step == t ? true : (step == null ? false : (step instanceof Number ? BoxesRunTime.equalsNumObject((Number)step, t) : (step instanceof Character ? BoxesRunTime.equalsCharObject((Character)step, t) : step.equals(t))))) {
            throw new IllegalArgumentException("zero step");
        }
        Builder b = this.newBuilder();
        b.sizeHint(NumericRange$.MODULE$.count(start, end, step, false, evidence$2));
        T i = start;
        while (num.mkOrderingOps(step).$less(num.zero()) ? num.mkOrderingOps(end).$less(i) : num.mkOrderingOps(i).$less(end)) {
            b.$plus$eq(i);
            i = num.mkNumericOps(i).$plus(step);
        }
        return (CC)((GenTraversable)b.result());
    }

    public <A> CC iterate(A start, int len, Function1<A, A> f) {
        Builder b = this.newBuilder();
        if (len > 0) {
            b.sizeHint(len);
            A acc = start;
            b.$plus$eq(start);
            for (int i = 1; i < len; ++i) {
                acc = f.apply(acc);
                b.$plus$eq(acc);
            }
        }
        return (CC)((GenTraversable)b.result());
    }

    public class GenericCanBuildFrom<A>
    implements CanBuildFrom<CC, A, CC> {
        @Override
        public Builder<A, CC> apply(CC from2) {
            return from2.genericBuilder();
        }

        @Override
        public Builder<A, CC> apply() {
            return this.scala$collection$generic$GenTraversableFactory$GenericCanBuildFrom$$$outer().newBuilder();
        }

        public /* synthetic */ GenTraversableFactory scala$collection$generic$GenTraversableFactory$GenericCanBuildFrom$$$outer() {
            return GenTraversableFactory.this;
        }

        public GenericCanBuildFrom() {
            if (GenTraversableFactory.this == null) {
                throw null;
            }
        }
    }
}

