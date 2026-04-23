/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Console$;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Predef$;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.Iterator;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Map;
import scala.collection.mutable.StringBuilder;
import scala.math.Ordering;
import scala.math.Ordering$Int$;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Origins$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\t}f!B\u0001\u0003\u0003\u0003Y!aB(sS\u001eLgn\u001d\u0006\u0003\u0007\u0011\tA!\u001e;jY*\u0011QAB\u0001\tS:$XM\u001d8bY*\u0011q\u0001C\u0001\be\u00164G.Z2u\u0015\u0005I\u0011!B:dC2\f7\u0001A\n\u0003\u00011\u0001\"!\u0004\b\u000e\u0003!I!a\u0004\u0005\u0003\r\u0005s\u0017PU3g\u0011\u0015\t\u0002\u0001\"\u0001\u0013\u0003\u0019a\u0014N\\5u}Q\t1\u0003\u0005\u0002\u0015\u00015\t!\u0001B\u0003\u0017\u0001\t\u0005qCA\u0002SKB\f\"\u0001G\u000e\u0011\u00055I\u0012B\u0001\u000e\t\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0004\u000f\n\u0005uA!aA!os\u0016!q\u0004\u0001\u0001!\u0005)\u0019F/Y2l'2L7-\u001a\t\u0004\u001b\u0005\u001a\u0013B\u0001\u0012\t\u0005\u0015\t%O]1z!\t!\u0013&D\u0001&\u0015\t1s%\u0001\u0003mC:<'\"\u0001\u0015\u0002\t)\fg/Y\u0005\u0003U\u0015\u0012\u0011c\u0015;bG.$&/Y2f\u000b2,W.\u001a8u\u0011\u0015a\u0003A\"\u0001.\u0003\r!\u0018mZ\u000b\u0002]A\u0011qF\r\b\u0003\u001bAJ!!\r\u0005\u0002\rA\u0013X\rZ3g\u0013\t\u0019DG\u0001\u0004TiJLgn\u001a\u0006\u0003c!AQA\u000e\u0001\u0007\u0002]\n\u0001\"[:DkR|gM\u001a\u000b\u0003qm\u0002\"!D\u001d\n\u0005iB!a\u0002\"p_2,\u0017M\u001c\u0005\u0006yU\u0002\raI\u0001\u0003K2DQA\u0010\u0001\u0007\u0002}\naA\\3x%\u0016\u0004HC\u0001!C!\t\tU#D\u0001\u0001\u0011\u0015\u0019U\b1\u0001E\u0003\tA8\u000f\u0005\u0002B=!)a\t\u0001D\u0001\u000f\u0006I!/\u001a9TiJLgn\u001a\u000b\u0003]!CQ!S#A\u0002\u0001\u000b1A]3q\u0011\u001dY\u0005A1A\u0005\n1\u000bqa\u001c:jO&t7/F\u0001N!\u0011q5\u000bQ+\u000e\u0003=S!\u0001U)\u0002\u000f5,H/\u00192mK*\u0011!\u000bC\u0001\u000bG>dG.Z2uS>t\u0017B\u0001+P\u0005\ri\u0015\r\u001d\t\u0003\u001bYK!a\u0016\u0005\u0003\u0007%sG\u000f\u0003\u0004Z\u0001\u0001\u0006I!T\u0001\t_JLw-\u001b8tA!)1\f\u0001C\u00059\u0006\u0019\u0011\r\u001a3\u0015\u0005u\u0003\u0007CA\u0007_\u0013\ty\u0006B\u0001\u0003V]&$\b\"B\"[\u0001\u0004\u0001\u0005\"\u00022\u0001\t\u0013\u0019\u0017!\u0002;pi\u0006dW#\u00013\u0011\u00055)\u0017B\u00014\t\u0005\u0011auN\\4\t\u000b!\u0004A\u0011A5\u0002\u0013I,\u0017\rZ*uC\u000e\\G#\u0001\u0011\t\u000b-\u0004A\u0011\u00017\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u00055\u0004HC\u00018s!\ty\u0007\u000f\u0004\u0001\u0005\u000bET'\u0019A\f\u0003\u0003QCaa\u001d6\u0005\u0002\u0004!\u0018\u0001\u00022pIf\u00042!D;o\u0013\t1\bB\u0001\u0005=Eft\u0017-\\3?\u0011\u0015A\b\u0001\"\u0001z\u0003\u0015\u0019G.Z1s)\u0005i\u0006\"B>\u0001\t\u0003I\u0018\u0001B:i_^DQ! \u0001\u0005\u0002e\fQ\u0001];sO\u0016<aa \u0002\t\u0002\u0005\u0005\u0011aB(sS\u001eLgn\u001d\t\u0004)\u0005\raAB\u0001\u0003\u0011\u0003\t)aE\u0002\u0002\u00041Aq!EA\u0002\t\u0003\tI\u0001\u0006\u0002\u0002\u0002!Q\u0011QBA\u0002\u0005\u0004%I!a\u0004\u0002\u0011\r|WO\u001c;feN,\"!!\u0005\u0011\u000b9\u000b\u0019BL\n\n\u0007\u0005UqJA\u0004ICNDW*\u00199\t\u0013\u0005e\u00111\u0001Q\u0001\n\u0005E\u0011!C2pk:$XM]:!\u0011)\ti\"a\u0001C\u0002\u0013%\u0011qD\u0001\ni\"L7o\u00117bgN,\"!!\t\u0011\u0007\u0011\n\u0019#\u0003\u00024K!I\u0011qEA\u0002A\u0003%\u0011\u0011E\u0001\u000bi\"L7o\u00117bgN\u0004caBA\u0016\u0003\u0007\u0001\u0015Q\u0006\u0002\t\u001fJLw-\u001b8JIN9\u0011\u0011\u0006\u0007\u00020\u0005U\u0002cA\u0007\u00022%\u0019\u00111\u0007\u0005\u0003\u000fA\u0013x\u000eZ;diB\u0019Q\"a\u000e\n\u0007\u0005e\u0002B\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0003\u0006\u0002>\u0005%\"Q3A\u0005\u00025\n\u0011b\u00197bgNt\u0015-\\3\t\u0015\u0005\u0005\u0013\u0011\u0006B\tB\u0003%a&\u0001\u0006dY\u0006\u001c8OT1nK\u0002B!\"!\u0012\u0002*\tU\r\u0011\"\u0001.\u0003)iW\r\u001e5pI:\u000bW.\u001a\u0005\u000b\u0003\u0013\nIC!E!\u0002\u0013q\u0013aC7fi\"|GMT1nK\u0002Bq!EA\u0015\t\u0003\ti\u0005\u0006\u0004\u0002P\u0005M\u0013Q\u000b\t\u0005\u0003#\nI#\u0004\u0002\u0002\u0004!9\u0011QHA&\u0001\u0004q\u0003bBA#\u0003\u0017\u0002\rA\f\u0005\t\u00033\nI\u0003\"\u0001\u0002\\\u00059Q.\u0019;dQ\u0016\u001cHc\u0001\u001d\u0002^!1A(a\u0016A\u0002\rB!\"!\u0019\u0002*\u0005\u0005I\u0011AA2\u0003\u0011\u0019w\u000e]=\u0015\r\u0005=\u0013QMA4\u0011%\ti$a\u0018\u0011\u0002\u0003\u0007a\u0006C\u0005\u0002F\u0005}\u0003\u0013!a\u0001]!Q\u00111NA\u0015#\u0003%\t!!\u001c\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011q\u000e\u0016\u0004]\u0005E4FAA:!\u0011\t)(a \u000e\u0005\u0005]$\u0002BA=\u0003w\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005u\u0004\"\u0001\u0006b]:|G/\u0019;j_:LA!!!\u0002x\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\t\u0015\u0005\u0015\u0015\u0011FI\u0001\n\u0003\ti'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\t\u0015\u0005%\u0015\u0011FA\u0001\n\u0003\ny\"A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e\u001f\u0005\u000b\u0003\u001b\u000bI#!A\u0005\u0002\u0005=\u0015\u0001\u00049s_\u0012,8\r^!sSRLX#A+\t\u0015\u0005M\u0015\u0011FA\u0001\n\u0003\t)*\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007m\t9\nC\u0005\u0002\u001a\u0006E\u0015\u0011!a\u0001+\u0006\u0019\u0001\u0010J\u0019\t\u0015\u0005u\u0015\u0011FA\u0001\n\u0003\ny*A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\t\u000bE\u0003\u0002$\u0006\u00156$D\u0001R\u0013\r\t9+\u0015\u0002\t\u0013R,'/\u0019;pe\"Q\u00111VA\u0015\u0003\u0003%\t!!,\u0002\u0011\r\fg.R9vC2$2\u0001OAX\u0011%\tI*!+\u0002\u0002\u0003\u00071\u0004\u0003\u0006\u00024\u0006%\u0012\u0011!C!\u0003k\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002+\"Q\u0011\u0011XA\u0015\u0003\u0003%\t%a/\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\t\t\u0015\u0005}\u0016\u0011FA\u0001\n\u0003\n\t-\u0001\u0004fcV\fGn\u001d\u000b\u0004q\u0005\r\u0007\"CAM\u0003{\u000b\t\u00111\u0001\u001c\u000f)\t9-a\u0001\u0002\u0002#\u0005\u0011\u0011Z\u0001\t\u001fJLw-\u001b8JIB!\u0011\u0011KAf\r)\tY#a\u0001\u0002\u0002#\u0005\u0011QZ\n\u0007\u0003\u0017\fy-!\u000e\u0011\u0011\u0005E\u0017q\u001b\u0018/\u0003\u001fj!!a5\u000b\u0007\u0005U\u0007\"A\u0004sk:$\u0018.\\3\n\t\u0005e\u00171\u001b\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004bB\t\u0002L\u0012\u0005\u0011Q\u001c\u000b\u0003\u0003\u0013D!\"!/\u0002L\u0006\u0005IQIA^\u0011%Y\u00171ZA\u0001\n\u0003\u000b\u0019\u000f\u0006\u0004\u0002P\u0005\u0015\u0018q\u001d\u0005\b\u0003{\t\t\u000f1\u0001/\u0011\u001d\t)%!9A\u00029B!\"a;\u0002L\u0006\u0005I\u0011QAw\u0003\u001d)h.\u00199qYf$B!a<\u0002|B)Q\"!=\u0002v&\u0019\u00111\u001f\u0005\u0003\r=\u0003H/[8o!\u0015i\u0011q\u001f\u0018/\u0013\r\tI\u0010\u0003\u0002\u0007)V\u0004H.\u001a\u001a\t\u0015\u0005u\u0018\u0011^A\u0001\u0002\u0004\ty%A\u0002yIAB!B!\u0001\u0002L\u0006\u0005I\u0011\u0002B\u0002\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0005\t\u0015\u0001c\u0001\u0013\u0003\b%\u0019!\u0011B\u0013\u0003\r=\u0013'.Z2u\u0011!\u0011i!a\u0001\u0005\u0002\t=\u0011A\u00027p_.,\b\u000fF\u0003\u0014\u0005#\u0011\u0019\u0002\u0003\u0004-\u0005\u0017\u0001\rA\f\u0005\t\u0005+\u0011Y\u00011\u0001\u0003\u0018\u00051qN]#mg\u0016\u0004R!\u0004B\r]MI1Aa\u0007\t\u0005%1UO\\2uS>t\u0017\u0007\u0003\u0005\u0003 \u0005\rA\u0011\u0001B\u0011\u0003!\u0011XmZ5ti\u0016\u0014HcA\n\u0003$!9!Q\u0005B\u000f\u0001\u0004\u0019\u0012!\u0001=\t\u0011\t%\u00121\u0001C\u0005\u0005W\t\u0011\u0002\u001d:f\u0007V$xN\u001a4\u0015\u0007a\u0012i\u0003\u0003\u0004=\u0005O\u0001\ra\t\u0005\t\u0005c\t\u0019\u0001\"\u0003\u00034\u0005Qa-\u001b8e\u0007V$xN\u001a4\u0015\u0005\u0005=\u0003bB6\u0002\u0004\u0011\u0005!q\u0007\u000b\u0004'\te\u0002B\u0002\u0017\u00036\u0001\u0007a\u0006C\u0004l\u0003\u0007!\tA!\u0010\u0015\u000bM\u0011yD!\u0011\t\r1\u0012Y\u00041\u0001/\u0011\u001d\u0011\u0019Ea\u000fA\u0002U\u000baA\u001a:b[\u0016\u001cha\u0002B$\u0003\u0007\u0001!\u0011\n\u0002\b\u001f:,G*\u001b8f'\r\u0011)e\u0005\u0005\nY\t\u0015#Q1A\u0005\u00025B!Ba\u0014\u0003F\t\u0005\t\u0015!\u0003/\u0003\u0011!\u0018m\u001a\u0011\t\u0017\tM#Q\tB\u0001B\u0003%\u0011qJ\u0001\u0003S\u0012Dq!\u0005B#\t\u0003\u00119\u0006\u0006\u0004\u0003Z\tm#Q\f\t\u0005\u0003#\u0012)\u0005\u0003\u0004-\u0005+\u0002\rA\f\u0005\t\u0005'\u0012)\u00061\u0001\u0002P\u0015)aC!\u0012\u0001G!9aG!\u0012\u0005\u0002\t\rDc\u0001\u001d\u0003f!1AH!\u0019A\u0002\rBqA\u0010B#\t\u0003\u0011I\u0007\u0006\u0003\u0003l\t=\u0004\u0003\u0002B7\u0005?j!A!\u0012\t\u000f\r\u00139\u00071\u0001\u0003rA\u0019!Q\u000e\u0010\t\u000f\u0019\u0013)\u0005\"\u0001\u0003vQ!\u0011\u0011\u0005B<\u0011\u001dI%1\u000fa\u0001\u0005W2qAa\u001f\u0002\u0004\u0001\u0011iHA\u0005Nk2$\u0018\u000eT5oKN\u0019!\u0011P\n\t\u00131\u0012IH!b\u0001\n\u0003i\u0003B\u0003B(\u0005s\u0012\t\u0011)A\u0005]!Y!1\u000bB=\u0005\u0003\u0005\u000b\u0011BA(\u0011)\u00119I!\u001f\u0003\u0002\u0003\u0006I!V\u0001\t]VlG*\u001b8fg\"9\u0011C!\u001f\u0005\u0002\t-E\u0003\u0003BG\u0005\u001f\u0013\tJa%\u0011\t\u0005E#\u0011\u0010\u0005\u0007Y\t%\u0005\u0019\u0001\u0018\t\u0011\tM#\u0011\u0012a\u0001\u0003\u001fBqAa\"\u0003\n\u0002\u0007Q+\u0002\u0004\u0017\u0005s\u0002!q\u0013\t\u0006\u00053\u0013yj\t\b\u0004\u001b\tm\u0015b\u0001BO\u0011\u00059\u0001/Y2lC\u001e,\u0017\u0002\u0002BQ\u0005G\u0013A\u0001T5ti*\u0019!Q\u0014\u0005\t\u000fY\u0012I\b\"\u0001\u0003(R\u0019\u0001H!+\t\rq\u0012)\u000b1\u0001$\u0011\u001dq$\u0011\u0010C\u0001\u0005[#BAa,\u00034B!!\u0011\u0017BK\u001b\t\u0011I\bC\u0004D\u0005W\u0003\rA!.\u0011\u0007\tEf\u0004C\u0004G\u0005s\"\tA!/\u0015\u00079\u0012Y\fC\u0004J\u0005o\u0003\rAa,\t\r!\u0014I\b\"\u0011j\u0001")
public abstract class Origins {
    private final Map<Object, Object> origins = new HashMap().withDefaultValue(BoxesRunTime.boxToInteger(0));

    public static Origins register(Origins origins) {
        return Origins$.MODULE$.register(origins);
    }

    public static Origins lookup(String string2, Function1<String, Origins> function1) {
        return Origins$.MODULE$.lookup(string2, function1);
    }

    public abstract String tag();

    public abstract boolean isCutoff(StackTraceElement var1);

    public abstract Object newRep(StackTraceElement[] var1);

    public abstract String repString(Object var1);

    private Map<Object, Object> origins() {
        return this.origins;
    }

    private void add(Object xs) {
        this.origins().update(xs, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.origins().apply(xs)) + 1));
    }

    private long total() {
        return BoxesRunTime.unboxToLong(this.origins().values().foldLeft(BoxesRunTime.boxToLong(0L), new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final long apply(long x$1, int x$2) {
                return x$1 + (long)x$2;
            }

            public long apply$mcJJI$sp(long x$1, int x$2) {
                return x$1 + (long)x$2;
            }
        }));
    }

    public StackTraceElement[] readStack() {
        return (StackTraceElement[])Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])Thread.currentThread().getStackTrace()).dropWhile(new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Origins $outer;

            public final boolean apply(StackTraceElement x) {
                return !this.$outer.isCutoff(x);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        })).dropWhile(new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Origins $outer;

            public final boolean apply(StackTraceElement el) {
                return this.$outer.isCutoff(el);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        })).drop(1);
    }

    public <T> T apply(Function0<T> body2) {
        this.add(this.newRep(this.readStack()));
        return body2.apply();
    }

    public void clear() {
        this.origins().clear();
    }

    public void show() {
        Predef$ predef$ = Predef$.MODULE$;
        String string2 = new StringOps("\n>> Origins tag '%s' logged %s calls from %s distinguished sources.\n").format(Predef$.MODULE$.genericWrapArray(new Object[]{this.tag(), BoxesRunTime.boxToLong(this.total()), BoxesRunTime.boxToInteger(this.origins().keys().size())}));
        Predef$ predef$2 = Predef$.MODULE$;
        Console$.MODULE$.println(string2);
        List list2 = (List)this.origins().toList().sortBy((Function1)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final int apply(Tuple2<Object, Object> x$3) {
                return -x$3._2$mcI$sp();
            }
        }), (Ordering)Ordering$Int$.MODULE$);
        while (!((AbstractIterable)list2).isEmpty()) {
            Tuple2 tuple2 = (Tuple2)((AbstractIterable)list2).head();
            if (tuple2 != null) {
                Predef$ predef$3 = Predef$.MODULE$;
                String string3 = new StringOps("%7s %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(tuple2._2$mcI$sp()), this.repString(tuple2._1())}));
                Predef$ predef$4 = Predef$.MODULE$;
                Console$.MODULE$.println(string3);
                list2 = (List)list2.tail();
                continue;
            }
            throw new MatchError(tuple2);
        }
    }

    public void purge() {
        this.show();
        this.clear();
    }

    public static class OneLine
    extends Origins {
        private final String tag;
        private final OriginId id;

        @Override
        public String tag() {
            return this.tag;
        }

        @Override
        public boolean isCutoff(StackTraceElement el) {
            return this.id.matches(el);
        }

        @Override
        public StackTraceElement newRep(StackTraceElement[] xs) {
            return xs == null || xs.length == 0 ? null : xs[0];
        }

        public String repString(StackTraceElement rep) {
            return new StringBuilder().append((Object)"  ").append(rep).toString();
        }

        public OneLine(String tag, OriginId id) {
            this.tag = tag;
            this.id = id;
        }
    }

    public static class OriginId
    implements Product,
    Serializable {
        private final String className;
        private final String methodName;

        public String className() {
            return this.className;
        }

        public String methodName() {
            return this.methodName;
        }

        public boolean matches(StackTraceElement el) {
            String string2 = this.methodName();
            String string3 = el.getMethodName();
            return !(string2 != null ? !string2.equals(string3) : string3 != null) && this.className().startsWith(el.getClassName());
        }

        public OriginId copy(String className, String methodName) {
            return new OriginId(className, methodName);
        }

        public String copy$default$1() {
            return this.className();
        }

        public String copy$default$2() {
            return this.methodName();
        }

        @Override
        public String productPrefix() {
            return "OriginId";
        }

        @Override
        public int productArity() {
            return 2;
        }

        @Override
        public Object productElement(int x$1) {
            String string2;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 1: {
                    string2 = this.methodName();
                    break;
                }
                case 0: {
                    string2 = this.className();
                }
            }
            return string2;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof OriginId;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$._hashCode(this);
        }

        public String toString() {
            return ScalaRunTime$.MODULE$._toString(this);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            if (!(x$1 instanceof OriginId)) return false;
            boolean bl = true;
            if (!bl) return false;
            OriginId originId = (OriginId)x$1;
            String string2 = this.className();
            String string3 = originId.className();
            if (string2 == null) {
                if (string3 != null) {
                    return false;
                }
            } else if (!string2.equals(string3)) return false;
            String string4 = this.methodName();
            String string5 = originId.methodName();
            if (string4 == null) {
                if (string5 != null) {
                    return false;
                }
            } else if (!string4.equals(string5)) return false;
            if (!originId.canEqual(this)) return false;
            return true;
        }

        public OriginId(String className, String methodName) {
            this.className = className;
            this.methodName = methodName;
            Product$class.$init$(this);
        }
    }

    public static class MultiLine
    extends Origins {
        private final String tag;
        private final OriginId id;
        private final int numLines;

        @Override
        public String tag() {
            return this.tag;
        }

        @Override
        public boolean isCutoff(StackTraceElement el) {
            return this.id.matches(el);
        }

        @Override
        public List<StackTraceElement> newRep(StackTraceElement[] xs) {
            return Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])xs).take(this.numLines)).toList();
        }

        public String repString(List<StackTraceElement> rep) {
            return ((TraversableOnce)rep.map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply(StackTraceElement x$5) {
                    return new StringBuilder().append((Object)"\n  ").append(x$5).toString();
                }
            }, List$.MODULE$.canBuildFrom())).mkString();
        }

        @Override
        public StackTraceElement[] readStack() {
            return (StackTraceElement[])Predef$.MODULE$.refArrayOps((Object[])super.readStack()).drop(1);
        }

        public MultiLine(String tag, OriginId id, int numLines) {
            this.tag = tag;
            this.id = id;
            this.numLines = numLines;
        }
    }
}

