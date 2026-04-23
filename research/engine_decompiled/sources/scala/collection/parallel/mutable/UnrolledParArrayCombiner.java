/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.Array$;
import scala.MatchError;
import scala.Option;
import scala.Predef$;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.UnrolledBuffer;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.Task;
import scala.collection.parallel.Task$class;
import scala.collection.parallel.mutable.DoublingUnrolledBuffer;
import scala.collection.parallel.mutable.ParArray;
import scala.collection.parallel.package$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u0005=daB\u0001\u0003!\u0003\r\ta\u0003\u0002\u0019+:\u0014x\u000e\u001c7fIB\u000b'/\u0011:sCf\u001cu.\u001c2j]\u0016\u0014(BA\u0002\u0005\u0003\u001diW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0011A\f'/\u00197mK2T!a\u0002\u0005\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\n\u0003\u0015\u00198-\u00197b\u0007\u0001)\"\u0001D\f\u0014\u0007\u0001i\u0011\u0003\u0005\u0002\u000f\u001f5\t\u0001\"\u0003\u0002\u0011\u0011\t1\u0011I\\=SK\u001a\u0004BAE\n\u0016A5\tA!\u0003\u0002\u0015\t\tA1i\\7cS:,'\u000f\u0005\u0002\u0017/1\u0001A!\u0002\r\u0001\u0005\u0004I\"!\u0001+\u0012\u0005ii\u0002C\u0001\b\u001c\u0013\ta\u0002BA\u0004O_RD\u0017N\\4\u0011\u00059q\u0012BA\u0010\t\u0005\r\te.\u001f\t\u0004C\t*R\"\u0001\u0002\n\u0005\r\u0012!\u0001\u0003)be\u0006\u0013(/Y=\t\u000b\u0015\u0002A\u0011\u0001\u0014\u0002\r\u0011Jg.\u001b;%)\u00059\u0003C\u0001\b)\u0013\tI\u0003B\u0001\u0003V]&$\bbB\u0016\u0001\u0005\u0004%\t\u0001L\u0001\u0005EV4g-F\u0001.!\r\tc&H\u0005\u0003_\t\u0011a\u0003R8vE2LgnZ+oe>dG.\u001a3Ck\u001a4WM\u001d\u0005\u0007c\u0001\u0001\u000b\u0011B\u0017\u0002\u000b\t,hM\u001a\u0011\t\u000bM\u0002A\u0011\u0001\u001b\u0002\u0011\u0011\u0002H.^:%KF$\"!\u000e\u001c\u000e\u0003\u0001AQa\u000e\u001aA\u0002U\tA!\u001a7f[\")\u0011\b\u0001C\u0001u\u00051!/Z:vYR$\u0012\u0001\t\u0005\u0006y\u0001!\tAJ\u0001\u0006G2,\u0017M\u001d\u0005\u0006}\u0001!\teP\u0001\tg&TX\rS5oiR\u0011q\u0005\u0011\u0005\u0006\u0003v\u0002\rAQ\u0001\u0003gj\u0004\"AD\"\n\u0005\u0011C!aA%oi\")a\t\u0001C\u0001\u000f\u000691m\\7cS:,Wc\u0001%L\u001fR\u0011\u0011J\u0015\t\u0005%MQe\n\u0005\u0002\u0017\u0017\u0012)A*\u0012b\u0001\u001b\n\ta*\u0005\u0002\u001b+A\u0011ac\u0014\u0003\u0006!\u0016\u0013\r!\u0015\u0002\u0006\u001d\u0016<Hk\\\t\u0003AuAQaU#A\u0002%\u000bQa\u001c;iKJDQ!\u0016\u0001\u0005\u0002Y\u000bAa]5{KV\t!I\u0002\u0003Y\u0001\u0001I&aE\"paf,fN]8mY\u0016$Gk\\!se\u0006L8cA,\u000e5B!!cW\u0014^\u0013\taFA\u0001\u0003UCN\\\u0007CA\u001bX\u0011!yvK!A!\u0002\u0013\u0001\u0017!B1se\u0006L\bc\u0001\bb;%\u0011!\r\u0003\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\tI^\u0013\t\u0011)A\u0005\u0005\u00061qN\u001a4tKRD\u0001BZ,\u0003\u0002\u0003\u0006IAQ\u0001\bQ><X.\u00198z\u0011\u0015Aw\u000b\"\u0001j\u0003\u0019a\u0014N\\5u}Q!QL[6m\u0011\u0015yv\r1\u0001a\u0011\u0015!w\r1\u0001C\u0011\u00151w\r1\u0001C\u0011\u001dIt\u000b1A\u0005\u00029,\u0012a\n\u0005\ba^\u0003\r\u0011\"\u0001r\u0003)\u0011Xm];mi~#S-\u001d\u000b\u0003OIDqa]8\u0002\u0002\u0003\u0007q%A\u0002yIEBa!^,!B\u00139\u0013a\u0002:fgVdG\u000f\t\u0005\u0006o^#\t\u0001_\u0001\u0005Y\u0016\fg\r\u0006\u0002(s\")!P\u001ea\u0001w\u0006!\u0001O]3w!\rqApJ\u0005\u0003{\"\u0011aa\u00149uS>t\u0007BB@X\t\u0013\t\t!A\u0005gS:$7\u000b^1siR!\u00111AA\u000e!\u0019q\u0011QAA\u0005\u0005&\u0019\u0011q\u0001\u0005\u0003\rQ+\b\u000f\\33!\u0015\tY!!\u0006\u001e\u001d\u0011\ti!!\u0005\u000e\u0005\u0005=!BA\u0002\u0007\u0013\u0011\t\u0019\"a\u0004\u0002\u001dUs'o\u001c7mK\u0012\u0014UO\u001a4fe&!\u0011qCA\r\u0005!)fN]8mY\u0016$'\u0002BA\n\u0003\u001fAa!!\b\u007f\u0001\u0004\u0011\u0015a\u00019pg\"9\u0011\u0011E,\u0005\u0002\u0005\r\u0012!B:qY&$XCAA\u0013!\u0015\t9#!\f^\u001b\t\tICC\u0002\u0002,\u0019\t\u0011\"[7nkR\f'\r\\3\n\t\u0005=\u0012\u0011\u0006\u0002\u0005\u0019&\u001cH\u000fC\u0004\u00024]#\t!!\u000e\u0002%MDw.\u001e7e'Bd\u0017\u000e\u001e$veRDWM]\u000b\u0003\u0003o\u00012ADA\u001d\u0013\r\tY\u0004\u0003\u0002\b\u0005>|G.Z1o\u0011\u001d\tyd\u0016C!\u0003\u0003\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003\u0007\u0002B!!\u0012\u0002P5\u0011\u0011q\t\u0006\u0005\u0003\u0013\nY%\u0001\u0003mC:<'BAA'\u0003\u0011Q\u0017M^1\n\t\u0005E\u0013q\t\u0002\u0007'R\u0014\u0018N\\4\b\u000f\u0005U#\u0001#\u0001\u0002X\u0005ARK\u001c:pY2,G\rU1s\u0003J\u0014\u0018-_\"p[\nLg.\u001a:\u0011\u0007\u0005\nIF\u0002\u0004\u0002\u0005!\u0005\u00111L\n\u0004\u00033j\u0001b\u00025\u0002Z\u0011\u0005\u0011q\f\u000b\u0003\u0003/B\u0001\"a\u0019\u0002Z\u0011\u0005\u0011QM\u0001\u0006CB\u0004H._\u000b\u0005\u0003O\ni\u0007\u0006\u0002\u0002jA!\u0011\u0005AA6!\r1\u0012Q\u000e\u0003\u00071\u0005\u0005$\u0019A\r")
public interface UnrolledParArrayCombiner<T>
extends Combiner<T, ParArray<T>> {
    public void scala$collection$parallel$mutable$UnrolledParArrayCombiner$_setter_$buff_$eq(DoublingUnrolledBuffer var1);

    public DoublingUnrolledBuffer<Object> buff();

    @Override
    public UnrolledParArrayCombiner<T> $plus$eq(T var1);

    @Override
    public ParArray<T> result();

    @Override
    public void clear();

    @Override
    public void sizeHint(int var1);

    @Override
    public <N extends T, NewTo> Combiner<N, NewTo> combine(Combiner<N, NewTo> var1);

    @Override
    public int size();

    public class CopyUnrolledToArray
    implements Task<BoxedUnit, CopyUnrolledToArray> {
        private final Object[] array;
        private final int offset;
        private final int howmany;
        private BoxedUnit result;
        public final /* synthetic */ UnrolledParArrayCombiner $outer;
        private volatile Throwable throwable;

        @Override
        public Throwable throwable() {
            return this.throwable;
        }

        @Override
        @TraitSetter
        public void throwable_$eq(Throwable x$1) {
            this.throwable = x$1;
        }

        @Override
        public Object repr() {
            return Task$class.repr(this);
        }

        @Override
        public void merge(Object that) {
            Task$class.merge(this, that);
        }

        @Override
        public void forwardThrowable() {
            Task$class.forwardThrowable(this);
        }

        @Override
        public void tryLeaf(Option<BoxedUnit> lastres) {
            Task$class.tryLeaf(this, lastres);
        }

        @Override
        public void tryMerge(Object t) {
            Task$class.tryMerge(this, t);
        }

        @Override
        public void mergeThrowables(Task<?, ?> that) {
            Task$class.mergeThrowables(this, that);
        }

        @Override
        public void signalAbort() {
            Task$class.signalAbort(this);
        }

        @Override
        public void result() {
        }

        @Override
        public void result_$eq(BoxedUnit x$1) {
            this.result = x$1;
        }

        @Override
        public void leaf(Option<BoxedUnit> prev) {
            if (this.howmany > 0) {
                int totalleft = this.howmany;
                Tuple2<UnrolledBuffer.Unrolled<Object>, Object> tuple2 = this.findStart(this.offset);
                if (tuple2 != null) {
                    Tuple2<UnrolledBuffer.Unrolled<Object>, Integer> tuple22 = new Tuple2<UnrolledBuffer.Unrolled<Object>, Integer>(tuple2._1(), BoxesRunTime.boxToInteger(tuple2._2$mcI$sp()));
                    UnrolledBuffer.Unrolled<Object> startnode = tuple22._1();
                    int startpos = tuple22._2$mcI$sp();
                    UnrolledBuffer.Unrolled<Object> curr = startnode;
                    int pos = startpos;
                    int arroffset = this.offset;
                    while (totalleft > 0) {
                        int lefthere = scala.math.package$.MODULE$.min(totalleft, curr.size() - pos);
                        Array$.MODULE$.copy(curr.array(), pos, this.array, arroffset, lefthere);
                        totalleft -= lefthere;
                        arroffset += lefthere;
                        pos = 0;
                        curr = curr.next();
                    }
                } else {
                    throw new MatchError(tuple2);
                }
            }
        }

        private Tuple2<UnrolledBuffer.Unrolled<Object>, Object> findStart(int pos) {
            int left = pos;
            UnrolledBuffer.Unrolled node = this.scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer().buff().headPtr();
            while (left - node.size() >= 0) {
                left -= node.size();
                node = node.next();
            }
            return new Tuple2<UnrolledBuffer.Unrolled<Object>, Object>(node, BoxesRunTime.boxToInteger(left));
        }

        public List<CopyUnrolledToArray> split() {
            int fp = this.howmany / 2;
            return List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new CopyUnrolledToArray[]{new CopyUnrolledToArray(this.scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer(), this.array, this.offset, fp), new CopyUnrolledToArray(this.scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer(), this.array, this.offset + fp, this.howmany - fp)}));
        }

        @Override
        public boolean shouldSplitFurther() {
            return this.howmany > package$.MODULE$.thresholdFromSize(this.scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer().size(), this.scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer().combinerTaskSupport().parallelismLevel());
        }

        public String toString() {
            return new StringBuilder().append((Object)"CopyUnrolledToArray(").append(BoxesRunTime.boxToInteger(this.offset)).append((Object)", ").append(BoxesRunTime.boxToInteger(this.howmany)).append((Object)")").toString();
        }

        public /* synthetic */ UnrolledParArrayCombiner scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer() {
            return this.$outer;
        }

        public CopyUnrolledToArray(UnrolledParArrayCombiner<T> $outer, Object[] array, int offset, int howmany) {
            this.array = array;
            this.offset = offset;
            this.howmany = howmany;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Task$class.$init$(this);
            this.result = BoxedUnit.UNIT;
        }
    }
}

