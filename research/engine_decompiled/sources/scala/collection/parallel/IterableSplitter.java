/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.GenIterable;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.IterableLike;
import scala.collection.Iterator;
import scala.collection.Iterator$class;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.DelegatedSignalling$class;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AugmentedIterableIterator;
import scala.collection.parallel.AugmentedIterableIterator$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter$class;
import scala.collection.parallel.ParIterable;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.RemainsIterator$class;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.Splitter;
import scala.collection.parallel.immutable.package$;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.RichInt$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\tEh\u0001C\u0001\u0003!\u0003\r\t!C\u001f\u0003!%#XM]1cY\u0016\u001c\u0006\u000f\\5ui\u0016\u0014(BA\u0002\u0005\u0003!\u0001\u0018M]1mY\u0016d'BA\u0003\u0007\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u000f\u0005)1oY1mC\u000e\u0001QC\u0001\u0006\u0016'\u0019\u00011b\u0004\u0010\"OA\u0011A\"D\u0007\u0002\r%\u0011aB\u0002\u0002\u0007\u0003:L(+\u001a4\u0011\u0007A\t2#D\u0001\u0003\u0013\t\u0011\"AA\rBk\u001elWM\u001c;fI&#XM]1cY\u0016LE/\u001a:bi>\u0014\bC\u0001\u000b\u0016\u0019\u0001!aA\u0006\u0001\u0005\u0006\u00049\"!\u0001+\u0012\u0005aY\u0002C\u0001\u0007\u001a\u0013\tQbAA\u0004O_RD\u0017N\\4\u0011\u00051a\u0012BA\u000f\u0007\u0005\r\te.\u001f\t\u0004!}\u0019\u0012B\u0001\u0011\u0003\u0005!\u0019\u0006\u000f\\5ui\u0016\u0014\bC\u0001\u0012&\u001b\u0005\u0019#B\u0001\u0013\u0005\u0003\u001d9WM\\3sS\u000eL!AJ\u0012\u0003\u0015MKwM\\1mY&tw\r\u0005\u0002#Q%\u0011\u0011f\t\u0002\u0014\t\u0016dWmZ1uK\u0012\u001c\u0016n\u001a8bY2Lgn\u001a\u0005\u0006W\u0001!\t\u0001L\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00035\u0002\"\u0001\u0004\u0018\n\u0005=2!\u0001B+oSRDq!\r\u0001A\u0002\u0013\u0005!'\u0001\btS\u001et\u0017\r\u001c#fY\u0016<\u0017\r^3\u0016\u0003\u0005Bq\u0001\u000e\u0001A\u0002\u0013\u0005Q'\u0001\ntS\u001et\u0017\r\u001c#fY\u0016<\u0017\r^3`I\u0015\fHCA\u00177\u0011\u001d94'!AA\u0002\u0005\n1\u0001\u001f\u00132\u0011\u0019I\u0004\u0001)Q\u0005C\u0005y1/[4oC2$U\r\\3hCR,\u0007\u0005C\u0003<\u0001\u0019\u0005A(A\u0002ekB,\u0012!\u0010\t\u0004!\u0001\u0019\u0002\"B \u0001\r\u0003\u0001\u0015!B:qY&$X#A!\u0011\u0007\t+UH\u0004\u0002\r\u0007&\u0011AIB\u0001\ba\u0006\u001c7.Y4f\u0013\t1uIA\u0002TKFT!\u0001\u0012\u0004\t\u000b%\u0003A\u0011\u0001!\u0002'M\u0004H.\u001b;XSRD7+[4oC2d\u0017N\\4\t\u000b-\u0003A\u0011\u0001'\u0002%MDw.\u001e7e'Bd\u0017\u000e\u001e$veRDWM]\u000b\u0003\u001b^#2AT)Z!\taq*\u0003\u0002Q\r\t9!i\\8mK\u0006t\u0007\"\u0002*K\u0001\u0004\u0019\u0016\u0001B2pY2\u00042\u0001\u0005+W\u0013\t)&AA\u0006QCJLE/\u001a:bE2,\u0007C\u0001\u000bX\t\u0015A&J1\u0001\u0018\u0005\u0005\u0019\u0006\"\u0002.K\u0001\u0004Y\u0016\u0001\u00059be\u0006dG.\u001a7jg6dUM^3m!\taA,\u0003\u0002^\r\t\u0019\u0011J\u001c;\t\u000b}\u0003a\u0011\u00011\u0002\u0013I,W.Y5oS:<W#A.\t\u000b\t\u0004A\u0011C2\u0002\u0017\t,\u0018\u000e\u001c3TiJLgn\u001a\u000b\u0003I.\u0004\"!\u001a5\u000f\u000511\u0017BA4\u0007\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011N\u001b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u001d4\u0001\"\u00027b\u0001\u0004i\u0017aB2m_N,(/\u001a\t\u0005\u00199\u0004X&\u0003\u0002p\r\tIa)\u001e8di&|g.\r\t\u0005\u00199$W\u0006\u0003\u0004s\u0001\u0011\u0005!a]\u0001\u0011I\u0016\u0014WoZ%oM>\u0014X.\u0019;j_:,\u0012\u0001\u001e\t\u0003kjl\u0011A\u001e\u0006\u0003ob\fA\u0001\\1oO*\t\u00110\u0001\u0003kCZ\f\u0017BA5w\r\u0011a\b\u0001A?\u0003\u000bQ\u000b7.\u001a8\u0014\u0007m\\Q\b\u0003\u0005\u0000w\n\u0005\t\u0015!\u0003\\\u0003\u0015!\u0018m[3o\u0011\u001d\t\u0019a\u001fC\u0001\u0003\u000b\ta\u0001P5oSRtD\u0003BA\u0004\u0003\u0017\u00012!!\u0003|\u001b\u0005\u0001\u0001BB@\u0002\u0002\u0001\u00071\fC\u0004`w\u0002\u0007I\u0011\u00011\t\u0013\u0005E1\u00101A\u0005\u0002\u0005M\u0011!\u0004:f[\u0006Lg.\u001b8h?\u0012*\u0017\u000fF\u0002.\u0003+A\u0001bNA\b\u0003\u0003\u0005\ra\u0017\u0005\b\u00033Y\b\u0015)\u0003\\\u0003)\u0011X-\\1j]&tw\r\t\u0005\b\u0003;YH\u0011AA\u0010\u0003\u001dA\u0017m\u001d(fqR,\u0012A\u0014\u0005\b\u0003GYH\u0011AA\u0013\u0003\u0011qW\r\u001f;\u0015\u0003MAQaO>\u0005\u0002qBQaP>\u0005\u0002\u0001C\u0001\"!\f|A\u0013E\u0011qF\u0001\bi\u0006\\WmU3r+\u0011\t\t$!\u0010\u0015\t\u0005M\u0012Q\n\u000b\u0005\u0003k\t\u0019\u0005\u0005\u0004\u00028\u0005e\u00121H\u0007\u0002\t%\u0011a\t\u0002\t\u0004)\u0005uB\u0001CA \u0003W\u0011\r!!\u0011\u0003\u0005AK\u0015C\u0001\r>\u0011!\t)%a\u000bA\u0002\u0005\u001d\u0013!\u0002;bW\u0016\u0014\b\u0003\u0003\u0007\u0002J\u0005m2,a\u000f\n\u0007\u0005-cAA\u0005Gk:\u001cG/[8oe!A\u0011qJA\u0016\u0001\u0004\t\t&\u0001\u0002tcB!!)RA\u001e\u0011!\t)\u0006\u0001C\u0001\t\u0005]\u0013\u0001\u00038foR\u000b7.\u001a8\u0015\t\u0005\u001d\u0011\u0011\f\u0005\b\u00037\n\u0019\u00061\u0001\\\u0003\u0015)h\u000e^5m\u0011!\ty\u0006\u0001C\u0001\t\u0005\u0005\u0014\u0001\u00058foNc\u0017nY3J]R,'O\\1m+\u0011\t\u0019'a\u001a\u0015\r\u0005\u0015\u0014QNA9!\r!\u0012q\r\u0003\t\u0003S\niF1\u0001\u0002l\t\tQ+E\u0002\u0019\u0003\u000fA\u0001\"a\u001c\u0002^\u0001\u0007\u0011QM\u0001\u0003SRDq!a\u001d\u0002^\u0001\u00071,A\u0003ge>l\u0017\u0007C\u0004\u0002x\u0001!\t%!\u001f\u0002\tQ\f7.\u001a\u000b\u0004{\u0005m\u0004bBA?\u0003k\u0002\raW\u0001\u0002]\"9\u0011\u0011\u0011\u0001\u0005B\u0005\r\u0015!B:mS\u000e,G#B\u001f\u0002\u0006\u0006\u001d\u0005bBA:\u0003\u007f\u0002\ra\u0017\u0005\b\u0003\u0013\u000by\b1\u0001\\\u0003\u0019)h\u000e^5mc\u00191\u0011Q\u0012\u0001\u0001\u0003\u001f\u0013a!T1qa\u0016$W\u0003BAI\u0003/\u001bR!a#\f\u0003'\u0003B\u0001\u0005\u0001\u0002\u0016B\u0019A#a&\u0005\ra\u000bYI1\u0001\u0018\u0011-\tY*a#\u0003\u0002\u0003\u0006I!!(\u0002\u0003\u0019\u0004R\u0001\u00048\u0014\u0003+C\u0001\"a\u0001\u0002\f\u0012\u0005\u0011\u0011\u0015\u000b\u0005\u0003G\u000b)\u000b\u0005\u0004\u0002\n\u0005-\u0015Q\u0013\u0005\t\u00037\u000by\n1\u0001\u0002\u001e\"A\u0011QDAF\t\u0003\ty\u0002\u0003\u0005\u0002$\u0005-E\u0011AAV)\t\t)\n\u0003\u0004`\u0003\u0017#\t\u0001\u0019\u0005\bw\u0005-E\u0011AAY+\t\t\u0019\nC\u0004@\u0003\u0017#\t!!.\u0016\u0005\u0005]\u0006\u0003\u0002\"F\u0003'Cq!a/\u0001\t\u0003\ni,A\u0002nCB,B!a0\u0002FR!\u0011\u0011YAd!\u0019\tI!a#\u0002DB\u0019A#!2\u0005\ra\u000bIL1\u0001\u0018\u0011!\tY*!/A\u0002\u0005%\u0007#\u0002\u0007o'\u0005\rgABAg\u0001\u0001\tyM\u0001\u0005BaB,g\u000eZ3e+\u0019\t\t.a6\u0002dN)\u00111Z\u0006\u0002TB!\u0001\u0003AAk!\r!\u0012q\u001b\u0003\t\u0003S\nYM1\u0001\u0002ZF\u00111c\u0007\u0005\f\u0003;\fYM!b\u0001\n#\ty.\u0001\u0003uQ\u0006$XCAAq!\r!\u00121\u001d\u0003\t\u0003\u007f\tYM1\u0001\u0002fF\u0019\u0001$a5\t\u0017\u0005%\u00181\u001aB\u0001B\u0003%\u0011\u0011]\u0001\u0006i\"\fG\u000f\t\u0005\t\u0003\u0007\tY\r\"\u0001\u0002nR!\u0011q^Ay!!\tI!a3\u0002V\u0006\u0005\b\u0002CAo\u0003W\u0004\r!!9\t\u0015\u0005U\u00181\u001aa\u0001\n#\t90\u0001\u0003dkJ\u0014XCAAj\u0011)\tY0a3A\u0002\u0013E\u0011Q`\u0001\tGV\u0014(o\u0018\u0013fcR\u0019Q&a@\t\u0013]\nI0!AA\u0002\u0005M\u0007\"\u0003B\u0002\u0003\u0017\u0004\u000b\u0015BAj\u0003\u0015\u0019WO\u001d:!\u0011!\ti\"a3\u0005\u0002\u0005}\u0001\u0002CA\u0012\u0003\u0017$\tA!\u0003\u0015\u0005\u0005U\u0007BB0\u0002L\u0012\u0005\u0001\r\u0003\u0005\u0003\u0010\u0005-G\u0011CA\u0010\u000351\u0017N]:u\u001d>tW)\u001c9us\"91(a3\u0005\u0002\u0005]\bbB \u0002L\u0012\u0005!QC\u000b\u0003\u0005/\u0001BAQ#\u0002T\"9!1\u0004\u0001\u0005\u0002\tu\u0011!E1qa\u0016tG\rU1s\u0013R,'/\u00192mKV1!q\u0004B\u0013\u0005S!BA!\t\u00030AA\u0011\u0011BAf\u0005G\u00119\u0003E\u0002\u0015\u0005K!\u0001\"!\u001b\u0003\u001a\t\u0007\u0011\u0011\u001c\t\u0004)\t%B\u0001CA \u00053\u0011\rAa\u000b\u0012\u0007a\u0011i\u0003\u0005\u0003\u0011\u0001\t\r\u0002\u0002CAo\u00053\u0001\rAa\n\u0007\r\tM\u0002\u0001\u0001B\u001b\u0005\u0019Q\u0016\u000e\u001d9fIV!!q\u0007B\"'\u0015\u0011\td\u0003B\u001d!\u0011\u0001\u0002Aa\u000f\u0011\r1\u0011id\u0005B!\u0013\r\u0011yD\u0002\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0007Q\u0011\u0019\u0005\u0002\u0004Y\u0005c\u0011\ra\u0006\u0005\f\u0003;\u0014\tD!b\u0001\n#\u00119%\u0006\u0002\u0003JA)\u0001Ca\u0013\u0003B%\u0019!Q\n\u0002\u0003\u0017M+\u0017o\u00159mSR$XM\u001d\u0005\f\u0003S\u0014\tD!A!\u0002\u0013\u0011I\u0005\u0003\u0005\u0002\u0004\tEB\u0011\u0001B*)\u0011\u0011)Fa\u0016\u0011\r\u0005%!\u0011\u0007B!\u0011!\tiN!\u0015A\u0002\t%\u0003\u0002CA\u000f\u0005c!\t!a\b\t\u0011\u0005\r\"\u0011\u0007C\u0001\u0005;\"\"Aa\u000f\t\r}\u0013\t\u0004\"\u0001a\u0011\u001dY$\u0011\u0007C\u0001\u0005G*\"A!\u000f\t\u000f}\u0012\t\u0004\"\u0001\u0003hU\u0011!\u0011\u000e\t\u0005\u0005\u0016\u0013I\u0004C\u0004\u0003n\u0001!\tAa\u001c\u0002\u0013iL\u0007\u000fU1s'\u0016\fX\u0003\u0002B9\u0005o\"BAa\u001d\u0003zA1\u0011\u0011\u0002B\u0019\u0005k\u00022\u0001\u0006B<\t\u0019A&1\u000eb\u0001/!A\u0011Q\u001cB6\u0001\u0004\u0011Y\bE\u0003\u0011\u0005\u0017\u0012)H\u0002\u0004\u0003\u0000\u0001\u0001!\u0011\u0011\u0002\n5&\u0004\b/\u001a3BY2,bAa!\u0003\f\n=5#\u0002B?\u0017\t\u0015\u0005\u0003\u0002\t\u0001\u0005\u000f\u0003r\u0001\u0004B\u001f\u0005\u0013\u0013i\tE\u0002\u0015\u0005\u0017#\u0001\"!\u001b\u0003~\t\u0007\u0011\u0011\u001c\t\u0004)\t=EA\u0002-\u0003~\t\u0007q\u0003C\u0006\u0002^\nu$Q1A\u0005\u0012\tMUC\u0001BK!\u0015\u0001\"1\nBG\u0011-\tIO! \u0003\u0002\u0003\u0006IA!&\t\u0017\tm%Q\u0010BC\u0002\u0013E!QT\u0001\ti\"L7/\u001a7f[V\u0011!\u0011\u0012\u0005\f\u0005C\u0013iH!A!\u0002\u0013\u0011I)A\u0005uQ&\u001cX\r\\3nA!Y!Q\u0015B?\u0005\u000b\u0007I\u0011\u0003BT\u0003!!\b.\u0019;fY\u0016lWC\u0001BG\u0011-\u0011YK! \u0003\u0002\u0003\u0006IA!$\u0002\u0013QD\u0017\r^3mK6\u0004\u0003\u0002CA\u0002\u0005{\"\tAa,\u0015\u0011\tE&1\u0017B[\u0005o\u0003\u0002\"!\u0003\u0003~\t%%Q\u0012\u0005\t\u0003;\u0014i\u000b1\u0001\u0003\u0016\"A!1\u0014BW\u0001\u0004\u0011I\t\u0003\u0005\u0003&\n5\u0006\u0019\u0001BG\u0011!\tiB! \u0005\u0002\u0005}\u0001\u0002CA\u0012\u0005{\"\tA!0\u0015\u0005\t\u001d\u0005BB0\u0003~\u0011\u0005\u0001\rC\u0004<\u0005{\"\tAa1\u0016\u0005\t\u0015\u0005bB \u0003~\u0011\u0005!qY\u000b\u0003\u0005\u0013\u0004BAQ#\u0003\u0006\"9!Q\u001a\u0001\u0005\u0002\t=\u0017\u0001\u0004>ja\u0006cG\u000eU1s'\u0016\fX\u0003\u0003Bi\u0005G\u00149Na7\u0015\u0011\tM'Q\u001dBu\u0005[\u0004\u0002\"!\u0003\u0003~\tU'\u0011\u001c\t\u0004)\t]G\u0001CA5\u0005\u0017\u0014\r!!7\u0011\u0007Q\u0011Y\u000e\u0002\u0005\u0003^\n-'\u0019\u0001Bp\u0005\u0005\u0011\u0016c\u0001Bq7A\u0019ACa9\u0005\ra\u0013YM1\u0001\u0018\u0011!\tiNa3A\u0002\t\u001d\b#\u0002\t\u0003L\t\u0005\b\u0002\u0003Bv\u0005\u0017\u0004\rA!6\u0002\u0011QD\u0017n]#mK6D\u0001Ba<\u0003L\u0002\u0007!\u0011\\\u0001\ti\"\fG/\u00127f[\u0002")
public interface IterableSplitter<T>
extends AugmentedIterableIterator<T>,
Splitter<T>,
DelegatedSignalling {
    @Override
    public Signalling signalDelegate();

    @Override
    @TraitSetter
    public void signalDelegate_$eq(Signalling var1);

    public IterableSplitter<T> dup();

    @Override
    public Seq<IterableSplitter<T>> split();

    public Seq<IterableSplitter<T>> splitWithSignalling();

    public <S> boolean shouldSplitFurther(ParIterable<S> var1, int var2);

    @Override
    public int remaining();

    public String buildString(Function1<Function1<String, BoxedUnit>, BoxedUnit> var1);

    public String debugInformation();

    public Taken newTaken(int var1);

    public <U extends Taken> U newSliceInternal(U var1, int var2);

    @Override
    public IterableSplitter<T> take(int var1);

    @Override
    public IterableSplitter<T> slice(int var1, int var2);

    @Override
    public <S> Mapped<S> map(Function1<T, S> var1);

    public <U, PI extends IterableSplitter<U>> Appended<U, PI> appendParIterable(PI var1);

    public <S> Zipped<S> zipParSeq(SeqSplitter<S> var1);

    public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> var1, U var2, R var3);

    public class Taken
    implements IterableSplitter<T> {
        private final int taken;
        private int remaining;
        public final /* synthetic */ IterableSplitter $outer;
        private Signalling signalDelegate;

        @Override
        public Signalling signalDelegate() {
            return this.signalDelegate;
        }

        @Override
        @TraitSetter
        public void signalDelegate_$eq(Signalling x$1) {
            this.signalDelegate = x$1;
        }

        @Override
        public Seq<IterableSplitter<T>> splitWithSignalling() {
            return IterableSplitter$class.splitWithSignalling(this);
        }

        @Override
        public <S> boolean shouldSplitFurther(ParIterable<S> coll, int parallelismLevel) {
            return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
        }

        @Override
        public String buildString(Function1<Function1<String, BoxedUnit>, BoxedUnit> closure) {
            return IterableSplitter$class.buildString(this, closure);
        }

        @Override
        public String debugInformation() {
            return IterableSplitter$class.debugInformation(this);
        }

        @Override
        public Taken newTaken(int until2) {
            return IterableSplitter$class.newTaken(this, until2);
        }

        @Override
        public <U extends Taken> U newSliceInternal(U it, int from1) {
            return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
        }

        @Override
        public IterableSplitter<T> take(int n) {
            return IterableSplitter$class.take(this, n);
        }

        @Override
        public IterableSplitter<T> slice(int from1, int until1) {
            return IterableSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> Mapped<S> map(Function1<T, S> f) {
            return IterableSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends IterableSplitter<U>> Appended<U, PI> appendParIterable(PI that) {
            return IterableSplitter$class.appendParIterable(this, that);
        }

        @Override
        public <S> Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return IterableSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
        }

        @Override
        public boolean isAborted() {
            return DelegatedSignalling$class.isAborted(this);
        }

        @Override
        public void abort() {
            DelegatedSignalling$class.abort(this);
        }

        @Override
        public int indexFlag() {
            return DelegatedSignalling$class.indexFlag(this);
        }

        @Override
        public void setIndexFlag(int f) {
            DelegatedSignalling$class.setIndexFlag(this, f);
        }

        @Override
        public void setIndexFlagIfGreater(int f) {
            DelegatedSignalling$class.setIndexFlagIfGreater(this, f);
        }

        @Override
        public void setIndexFlagIfLesser(int f) {
            DelegatedSignalling$class.setIndexFlagIfLesser(this, f);
        }

        @Override
        public int tag() {
            return DelegatedSignalling$class.tag(this);
        }

        @Override
        public int count(Function1<T, Object> p) {
            return AugmentedIterableIterator$class.count(this, p);
        }

        @Override
        public <U> U reduce(Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduce(this, op);
        }

        @Override
        public <U> U fold(U z, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.fold(this, z, op);
        }

        @Override
        public <U> U sum(Numeric<U> num) {
            return (U)AugmentedIterableIterator$class.sum(this, num);
        }

        @Override
        public <U> U product(Numeric<U> num) {
            return (U)AugmentedIterableIterator$class.product(this, num);
        }

        @Override
        public <U> T min(Ordering<U> ord) {
            return AugmentedIterableIterator$class.min(this, ord);
        }

        @Override
        public <U> T max(Ordering<U> ord) {
            return AugmentedIterableIterator$class.max(this, ord);
        }

        @Override
        public <U> void copyToArray(Object array, int from2, int len) {
            AugmentedIterableIterator$class.copyToArray(this, array, from2, len);
        }

        @Override
        public <U> U reduceLeft(int howmany, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
        }

        @Override
        public <S, That> Combiner<S, That> map2combiner(Function1<T, S> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.map2combiner(this, f, cb);
        }

        @Override
        public <S, That> Combiner<S, That> collect2combiner(PartialFunction<T, S> pf, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
        }

        @Override
        public <S, That> Combiner<S, That> flatmap2combiner(Function1<T, GenTraversableOnce<S>> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
        }

        @Override
        public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Bld b) {
            return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
        }

        @Override
        public <U, This> Combiner<U, This> filter2combiner(Function1<T, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Combiner<U, This> filterNot2combiner(Function1<T, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<T, Object> pred, Combiner<U, This> btrue, Combiner<U, This> bfalse) {
            return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
        }

        @Override
        public <U, This> Combiner<U, This> take2combiner(int n, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.take2combiner(this, n, cb);
        }

        @Override
        public <U, This> Combiner<U, This> drop2combiner(int n, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
        }

        @Override
        public <U, This> Combiner<U, This> slice2combiner(int from2, int until2, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.slice2combiner(this, from2, until2, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<T, Object> p, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<T, Object> p, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
        }

        @Override
        public <U, A> void scanToArray(U z, Function2<U, U, U> op, Object array, int from2) {
            AugmentedIterableIterator$class.scanToArray(this, z, op, array, from2);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(int howmany, U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator<S> otherpit, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator<S> that, U thiselem, S thatelem, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
        }

        @Override
        public boolean isRemainingCheap() {
            return RemainsIterator$class.isRemainingCheap(this);
        }

        @Override
        public Iterator<T> seq() {
            return Iterator$class.seq(this);
        }

        @Override
        public boolean isEmpty() {
            return Iterator$class.isEmpty(this);
        }

        @Override
        public boolean isTraversableAgain() {
            return Iterator$class.isTraversableAgain(this);
        }

        @Override
        public boolean hasDefiniteSize() {
            return Iterator$class.hasDefiniteSize(this);
        }

        @Override
        public Iterator<T> drop(int n) {
            return Iterator$class.drop(this, n);
        }

        @Override
        public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
            return Iterator$class.$plus$plus(this, that);
        }

        @Override
        public <B> Iterator<B> flatMap(Function1<T, GenTraversableOnce<B>> f) {
            return Iterator$class.flatMap(this, f);
        }

        @Override
        public Iterator<T> filter(Function1<T, Object> p) {
            return Iterator$class.filter(this, p);
        }

        @Override
        public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<T, B, Object> p) {
            return Iterator$class.corresponds(this, that, p);
        }

        @Override
        public Iterator<T> withFilter(Function1<T, Object> p) {
            return Iterator$class.withFilter(this, p);
        }

        @Override
        public Iterator<T> filterNot(Function1<T, Object> p) {
            return Iterator$class.filterNot(this, p);
        }

        @Override
        public <B> Iterator<B> collect(PartialFunction<T, B> pf) {
            return Iterator$class.collect(this, pf);
        }

        @Override
        public <B> Iterator<B> scanLeft(B z, Function2<B, T, B> op) {
            return Iterator$class.scanLeft(this, z, op);
        }

        @Override
        public <B> Iterator<B> scanRight(B z, Function2<T, B, B> op) {
            return Iterator$class.scanRight(this, z, op);
        }

        @Override
        public Iterator<T> takeWhile(Function1<T, Object> p) {
            return Iterator$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<Iterator<T>, Iterator<T>> partition(Function1<T, Object> p) {
            return Iterator$class.partition(this, p);
        }

        @Override
        public Tuple2<Iterator<T>, Iterator<T>> span(Function1<T, Object> p) {
            return Iterator$class.span(this, p);
        }

        @Override
        public Iterator<T> dropWhile(Function1<T, Object> p) {
            return Iterator$class.dropWhile(this, p);
        }

        @Override
        public <B> Iterator<Tuple2<T, B>> zip(Iterator<B> that) {
            return Iterator$class.zip(this, that);
        }

        @Override
        public <A1> Iterator<A1> padTo(int len, A1 elem) {
            return Iterator$class.padTo(this, len, elem);
        }

        @Override
        public Iterator<Tuple2<T, Object>> zipWithIndex() {
            return Iterator$class.zipWithIndex(this);
        }

        @Override
        public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
            return Iterator$class.zipAll(this, that, thisElem, thatElem);
        }

        @Override
        public <U> void foreach(Function1<T, U> f) {
            Iterator$class.foreach(this, f);
        }

        @Override
        public boolean forall(Function1<T, Object> p) {
            return Iterator$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<T, Object> p) {
            return Iterator$class.exists(this, p);
        }

        @Override
        public boolean contains(Object elem) {
            return Iterator$class.contains(this, elem);
        }

        @Override
        public Option<T> find(Function1<T, Object> p) {
            return Iterator$class.find(this, p);
        }

        @Override
        public int indexWhere(Function1<T, Object> p) {
            return Iterator$class.indexWhere(this, p);
        }

        @Override
        public <B> int indexOf(B elem) {
            return Iterator$class.indexOf(this, elem);
        }

        @Override
        public BufferedIterator<T> buffered() {
            return Iterator$class.buffered(this);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> grouped(int size2) {
            return Iterator$class.grouped(this, size2);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
            return Iterator$class.sliding(this, size2, step);
        }

        @Override
        public int length() {
            return Iterator$class.length(this);
        }

        @Override
        public Tuple2<Iterator<T>, Iterator<T>> duplicate() {
            return Iterator$class.duplicate(this);
        }

        @Override
        public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
            return Iterator$class.patch(this, from2, patchElems, replaced);
        }

        @Override
        public boolean sameElements(Iterator<?> that) {
            return Iterator$class.sameElements(this, that);
        }

        @Override
        public Traversable<T> toTraversable() {
            return Iterator$class.toTraversable(this);
        }

        @Override
        public Iterator<T> toIterator() {
            return Iterator$class.toIterator(this);
        }

        @Override
        public Stream<T> toStream() {
            return Iterator$class.toStream(this);
        }

        @Override
        public String toString() {
            return Iterator$class.toString(this);
        }

        @Override
        public <B> int sliding$default$2() {
            return Iterator$class.sliding$default$2(this);
        }

        @Override
        public List<T> reversed() {
            return TraversableOnce$class.reversed(this);
        }

        @Override
        public int size() {
            return TraversableOnce$class.size(this);
        }

        @Override
        public boolean nonEmpty() {
            return TraversableOnce$class.nonEmpty(this);
        }

        @Override
        public <B> Option<B> collectFirst(PartialFunction<T, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, T, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<T, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, T, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<T, B, B> op) {
            return (B)TraversableOnce$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, T, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<T, B, B> op) {
            return (B)TraversableOnce$class.reduceRight(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, T, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<T, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <B> B aggregate(Function0<B> z, Function2<B, T, B> seqop, Function2<B, B, B> combop) {
            return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public <B> T maxBy(Function1<T, B> f, Ordering<B> cmp) {
            return TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public <B> T minBy(Function1<T, B> f, Ordering<B> cmp) {
            return TraversableOnce$class.minBy(this, f, cmp);
        }

        @Override
        public <B> void copyToBuffer(Buffer<B> dest) {
            TraversableOnce$class.copyToBuffer(this, dest);
        }

        @Override
        public <B> void copyToArray(Object xs, int start) {
            TraversableOnce$class.copyToArray(this, xs, start);
        }

        @Override
        public <B> void copyToArray(Object xs) {
            TraversableOnce$class.copyToArray(this, xs);
        }

        @Override
        public <B> Object toArray(ClassTag<B> evidence$1) {
            return TraversableOnce$class.toArray(this, evidence$1);
        }

        @Override
        public List<T> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Iterable<T> toIterable() {
            return TraversableOnce$class.toIterable(this);
        }

        @Override
        public Seq<T> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<T> toIndexedSeq() {
            return TraversableOnce$class.toIndexedSeq(this);
        }

        @Override
        public <B> Buffer<B> toBuffer() {
            return TraversableOnce$class.toBuffer(this);
        }

        @Override
        public <B> Set<B> toSet() {
            return TraversableOnce$class.toSet(this);
        }

        @Override
        public Vector<T> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, T, Col> cbf) {
            return (Col)TraversableOnce$class.to(this, cbf);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<T, Tuple2<T, U>> ev) {
            return TraversableOnce$class.toMap(this, ev);
        }

        @Override
        public String mkString(String start, String sep, String end) {
            return TraversableOnce$class.mkString(this, start, sep, end);
        }

        @Override
        public String mkString(String sep) {
            return TraversableOnce$class.mkString(this, sep);
        }

        @Override
        public String mkString() {
            return TraversableOnce$class.mkString(this);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
            return TraversableOnce$class.addString(this, b, start, sep, end);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String sep) {
            return TraversableOnce$class.addString(this, b, sep);
        }

        @Override
        public StringBuilder addString(StringBuilder b) {
            return TraversableOnce$class.addString(this, b);
        }

        @Override
        public int remaining() {
            return this.remaining;
        }

        public void remaining_$eq(int x$1) {
            this.remaining = x$1;
        }

        @Override
        public boolean hasNext() {
            return this.remaining() > 0;
        }

        @Override
        public T next() {
            this.remaining_$eq(this.remaining() - 1);
            return this.scala$collection$parallel$IterableSplitter$Taken$$$outer().next();
        }

        @Override
        public IterableSplitter<T> dup() {
            return this.scala$collection$parallel$IterableSplitter$Taken$$$outer().dup().take(this.taken);
        }

        @Override
        public Seq<IterableSplitter<T>> split() {
            return this.takeSeq(this.scala$collection$parallel$IterableSplitter$Taken$$$outer().split(), (Function2)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final IterableSplitter<T> apply(IterableSplitter<T> p, int n) {
                    return p.take(n);
                }
            }));
        }

        public <PI extends IterableSplitter<T>> Seq<PI> takeSeq(Seq<PI> sq, Function2<PI, Object, PI> taker) {
            Seq sizes = sq.scanLeft(BoxesRunTime.boxToInteger(0), new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply(int x$3, PI x$4) {
                    return x$3 + x$4.remaining();
                }
            }, Seq$.MODULE$.canBuildFrom());
            Seq shortened = ((TraversableLike)sq.zip(((IterableLike)sizes.init()).zip((GenIterable)sizes.tail(), Seq$.MODULE$.canBuildFrom()), Seq$.MODULE$.canBuildFrom())).withFilter(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Tuple2<PI, Tuple2<Object, Object>> check$ifrefutable$1) {
                    boolean bl = check$ifrefutable$1 != null && check$ifrefutable$1._2() != null;
                    return bl;
                }
            }).map(new Serializable(this, taker){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Taken $outer;
                private final Function2 taker$1;

                public final PI apply(Tuple2<PI, Tuple2<Object, Object>> x$5) {
                    if (x$5 != null && x$5._2() != null) {
                        return (PI)(x$5._2()._2$mcI$sp() < this.$outer.remaining() ? (IterableSplitter)x$5._1() : (IterableSplitter)this.taker$1.apply(x$5._1(), BoxesRunTime.boxToInteger(this.$outer.remaining() - x$5._2()._1$mcI$sp())));
                    }
                    throw new MatchError(x$5);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.taker$1 = taker$1;
                }
            }, Seq$.MODULE$.canBuildFrom());
            return (Seq)shortened.filter(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(PI x$6) {
                    return x$6.remaining() > 0;
                }
            });
        }

        public /* synthetic */ IterableSplitter scala$collection$parallel$IterableSplitter$Taken$$$outer() {
            return this.$outer;
        }

        public Taken(IterableSplitter<T> $outer, int taken) {
            this.taken = taken;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            TraversableOnce$class.$init$(this);
            Iterator$class.$init$(this);
            RemainsIterator$class.$init$(this);
            AugmentedIterableIterator$class.$init$(this);
            DelegatedSignalling$class.$init$(this);
            IterableSplitter$class.$init$(this);
            Predef$ predef$ = Predef$.MODULE$;
            this.remaining = RichInt$.MODULE$.min$extension(taken, $outer.remaining());
        }
    }

    public class Mapped<S>
    implements IterableSplitter<S> {
        public final Function1<T, S> scala$collection$parallel$IterableSplitter$Mapped$$f;
        public final /* synthetic */ IterableSplitter $outer;
        private Signalling signalDelegate;

        @Override
        public Signalling signalDelegate() {
            return this.signalDelegate;
        }

        @Override
        @TraitSetter
        public void signalDelegate_$eq(Signalling x$1) {
            this.signalDelegate = x$1;
        }

        @Override
        public Seq<IterableSplitter<S>> splitWithSignalling() {
            return IterableSplitter$class.splitWithSignalling(this);
        }

        @Override
        public <S> boolean shouldSplitFurther(ParIterable<S> coll, int parallelismLevel) {
            return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
        }

        @Override
        public String buildString(Function1<Function1<String, BoxedUnit>, BoxedUnit> closure) {
            return IterableSplitter$class.buildString(this, closure);
        }

        @Override
        public String debugInformation() {
            return IterableSplitter$class.debugInformation(this);
        }

        @Override
        public Taken newTaken(int until2) {
            return IterableSplitter$class.newTaken(this, until2);
        }

        @Override
        public <U extends Taken> U newSliceInternal(U it, int from1) {
            return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
        }

        @Override
        public IterableSplitter<S> take(int n) {
            return IterableSplitter$class.take(this, n);
        }

        @Override
        public IterableSplitter<S> slice(int from1, int until1) {
            return IterableSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> Mapped<S> map(Function1<S, S> f) {
            return IterableSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends IterableSplitter<U>> Appended<U, PI> appendParIterable(PI that) {
            return IterableSplitter$class.appendParIterable(this, that);
        }

        @Override
        public <S> Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return IterableSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
        }

        @Override
        public boolean isAborted() {
            return DelegatedSignalling$class.isAborted(this);
        }

        @Override
        public void abort() {
            DelegatedSignalling$class.abort(this);
        }

        @Override
        public int indexFlag() {
            return DelegatedSignalling$class.indexFlag(this);
        }

        @Override
        public void setIndexFlag(int f) {
            DelegatedSignalling$class.setIndexFlag(this, f);
        }

        @Override
        public void setIndexFlagIfGreater(int f) {
            DelegatedSignalling$class.setIndexFlagIfGreater(this, f);
        }

        @Override
        public void setIndexFlagIfLesser(int f) {
            DelegatedSignalling$class.setIndexFlagIfLesser(this, f);
        }

        @Override
        public int tag() {
            return DelegatedSignalling$class.tag(this);
        }

        @Override
        public int count(Function1<S, Object> p) {
            return AugmentedIterableIterator$class.count(this, p);
        }

        @Override
        public <U> U reduce(Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduce(this, op);
        }

        @Override
        public <U> U fold(U z, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.fold(this, z, op);
        }

        @Override
        public <U> U sum(Numeric<U> num) {
            return (U)AugmentedIterableIterator$class.sum(this, num);
        }

        @Override
        public <U> U product(Numeric<U> num) {
            return (U)AugmentedIterableIterator$class.product(this, num);
        }

        @Override
        public <U> S min(Ordering<U> ord) {
            return (S)AugmentedIterableIterator$class.min(this, ord);
        }

        @Override
        public <U> S max(Ordering<U> ord) {
            return (S)AugmentedIterableIterator$class.max(this, ord);
        }

        @Override
        public <U> void copyToArray(Object array, int from2, int len) {
            AugmentedIterableIterator$class.copyToArray(this, array, from2, len);
        }

        @Override
        public <U> U reduceLeft(int howmany, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
        }

        @Override
        public <S, That> Combiner<S, That> map2combiner(Function1<S, S> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.map2combiner(this, f, cb);
        }

        @Override
        public <S, That> Combiner<S, That> collect2combiner(PartialFunction<S, S> pf, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
        }

        @Override
        public <S, That> Combiner<S, That> flatmap2combiner(Function1<S, GenTraversableOnce<S>> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
        }

        @Override
        public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Bld b) {
            return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
        }

        @Override
        public <U, This> Combiner<U, This> filter2combiner(Function1<S, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Combiner<U, This> filterNot2combiner(Function1<S, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<S, Object> pred, Combiner<U, This> btrue, Combiner<U, This> bfalse) {
            return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
        }

        @Override
        public <U, This> Combiner<U, This> take2combiner(int n, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.take2combiner(this, n, cb);
        }

        @Override
        public <U, This> Combiner<U, This> drop2combiner(int n, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
        }

        @Override
        public <U, This> Combiner<U, This> slice2combiner(int from2, int until2, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.slice2combiner(this, from2, until2, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<S, Object> p, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<S, Object> p, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
        }

        @Override
        public <U, A> void scanToArray(U z, Function2<U, U, U> op, Object array, int from2) {
            AugmentedIterableIterator$class.scanToArray(this, z, op, array, from2);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(int howmany, U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator<S> otherpit, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator<S> that, U thiselem, S thatelem, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
        }

        @Override
        public boolean isRemainingCheap() {
            return RemainsIterator$class.isRemainingCheap(this);
        }

        @Override
        public Iterator<S> seq() {
            return Iterator$class.seq(this);
        }

        @Override
        public boolean isEmpty() {
            return Iterator$class.isEmpty(this);
        }

        @Override
        public boolean isTraversableAgain() {
            return Iterator$class.isTraversableAgain(this);
        }

        @Override
        public boolean hasDefiniteSize() {
            return Iterator$class.hasDefiniteSize(this);
        }

        @Override
        public Iterator<S> drop(int n) {
            return Iterator$class.drop(this, n);
        }

        @Override
        public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
            return Iterator$class.$plus$plus(this, that);
        }

        @Override
        public <B> Iterator<B> flatMap(Function1<S, GenTraversableOnce<B>> f) {
            return Iterator$class.flatMap(this, f);
        }

        @Override
        public Iterator<S> filter(Function1<S, Object> p) {
            return Iterator$class.filter(this, p);
        }

        @Override
        public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<S, B, Object> p) {
            return Iterator$class.corresponds(this, that, p);
        }

        @Override
        public Iterator<S> withFilter(Function1<S, Object> p) {
            return Iterator$class.withFilter(this, p);
        }

        @Override
        public Iterator<S> filterNot(Function1<S, Object> p) {
            return Iterator$class.filterNot(this, p);
        }

        @Override
        public <B> Iterator<B> collect(PartialFunction<S, B> pf) {
            return Iterator$class.collect(this, pf);
        }

        @Override
        public <B> Iterator<B> scanLeft(B z, Function2<B, S, B> op) {
            return Iterator$class.scanLeft(this, z, op);
        }

        @Override
        public <B> Iterator<B> scanRight(B z, Function2<S, B, B> op) {
            return Iterator$class.scanRight(this, z, op);
        }

        @Override
        public Iterator<S> takeWhile(Function1<S, Object> p) {
            return Iterator$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<Iterator<S>, Iterator<S>> partition(Function1<S, Object> p) {
            return Iterator$class.partition(this, p);
        }

        @Override
        public Tuple2<Iterator<S>, Iterator<S>> span(Function1<S, Object> p) {
            return Iterator$class.span(this, p);
        }

        @Override
        public Iterator<S> dropWhile(Function1<S, Object> p) {
            return Iterator$class.dropWhile(this, p);
        }

        @Override
        public <B> Iterator<Tuple2<S, B>> zip(Iterator<B> that) {
            return Iterator$class.zip(this, that);
        }

        @Override
        public <A1> Iterator<A1> padTo(int len, A1 elem) {
            return Iterator$class.padTo(this, len, elem);
        }

        @Override
        public Iterator<Tuple2<S, Object>> zipWithIndex() {
            return Iterator$class.zipWithIndex(this);
        }

        @Override
        public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
            return Iterator$class.zipAll(this, that, thisElem, thatElem);
        }

        @Override
        public <U> void foreach(Function1<S, U> f) {
            Iterator$class.foreach(this, f);
        }

        @Override
        public boolean forall(Function1<S, Object> p) {
            return Iterator$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<S, Object> p) {
            return Iterator$class.exists(this, p);
        }

        @Override
        public boolean contains(Object elem) {
            return Iterator$class.contains(this, elem);
        }

        @Override
        public Option<S> find(Function1<S, Object> p) {
            return Iterator$class.find(this, p);
        }

        @Override
        public int indexWhere(Function1<S, Object> p) {
            return Iterator$class.indexWhere(this, p);
        }

        @Override
        public <B> int indexOf(B elem) {
            return Iterator$class.indexOf(this, elem);
        }

        @Override
        public BufferedIterator<S> buffered() {
            return Iterator$class.buffered(this);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> grouped(int size2) {
            return Iterator$class.grouped(this, size2);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
            return Iterator$class.sliding(this, size2, step);
        }

        @Override
        public int length() {
            return Iterator$class.length(this);
        }

        @Override
        public Tuple2<Iterator<S>, Iterator<S>> duplicate() {
            return Iterator$class.duplicate(this);
        }

        @Override
        public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
            return Iterator$class.patch(this, from2, patchElems, replaced);
        }

        @Override
        public boolean sameElements(Iterator<?> that) {
            return Iterator$class.sameElements(this, that);
        }

        @Override
        public Traversable<S> toTraversable() {
            return Iterator$class.toTraversable(this);
        }

        @Override
        public Iterator<S> toIterator() {
            return Iterator$class.toIterator(this);
        }

        @Override
        public Stream<S> toStream() {
            return Iterator$class.toStream(this);
        }

        @Override
        public String toString() {
            return Iterator$class.toString(this);
        }

        @Override
        public <B> int sliding$default$2() {
            return Iterator$class.sliding$default$2(this);
        }

        @Override
        public List<S> reversed() {
            return TraversableOnce$class.reversed(this);
        }

        @Override
        public int size() {
            return TraversableOnce$class.size(this);
        }

        @Override
        public boolean nonEmpty() {
            return TraversableOnce$class.nonEmpty(this);
        }

        @Override
        public <B> Option<B> collectFirst(PartialFunction<S, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, S, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<S, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, S, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<S, B, B> op) {
            return (B)TraversableOnce$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, S, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<S, B, B> op) {
            return (B)TraversableOnce$class.reduceRight(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, S, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<S, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <B> B aggregate(Function0<B> z, Function2<B, S, B> seqop, Function2<B, B, B> combop) {
            return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public <B> S maxBy(Function1<S, B> f, Ordering<B> cmp) {
            return (S)TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public <B> S minBy(Function1<S, B> f, Ordering<B> cmp) {
            return (S)TraversableOnce$class.minBy(this, f, cmp);
        }

        @Override
        public <B> void copyToBuffer(Buffer<B> dest) {
            TraversableOnce$class.copyToBuffer(this, dest);
        }

        @Override
        public <B> void copyToArray(Object xs, int start) {
            TraversableOnce$class.copyToArray(this, xs, start);
        }

        @Override
        public <B> void copyToArray(Object xs) {
            TraversableOnce$class.copyToArray(this, xs);
        }

        @Override
        public <B> Object toArray(ClassTag<B> evidence$1) {
            return TraversableOnce$class.toArray(this, evidence$1);
        }

        @Override
        public List<S> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Iterable<S> toIterable() {
            return TraversableOnce$class.toIterable(this);
        }

        @Override
        public Seq<S> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<S> toIndexedSeq() {
            return TraversableOnce$class.toIndexedSeq(this);
        }

        @Override
        public <B> Buffer<B> toBuffer() {
            return TraversableOnce$class.toBuffer(this);
        }

        @Override
        public <B> Set<B> toSet() {
            return TraversableOnce$class.toSet(this);
        }

        @Override
        public Vector<S> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, S, Col> cbf) {
            return (Col)TraversableOnce$class.to(this, cbf);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<S, Tuple2<T, U>> ev) {
            return TraversableOnce$class.toMap(this, ev);
        }

        @Override
        public String mkString(String start, String sep, String end) {
            return TraversableOnce$class.mkString(this, start, sep, end);
        }

        @Override
        public String mkString(String sep) {
            return TraversableOnce$class.mkString(this, sep);
        }

        @Override
        public String mkString() {
            return TraversableOnce$class.mkString(this);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
            return TraversableOnce$class.addString(this, b, start, sep, end);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String sep) {
            return TraversableOnce$class.addString(this, b, sep);
        }

        @Override
        public StringBuilder addString(StringBuilder b) {
            return TraversableOnce$class.addString(this, b);
        }

        @Override
        public boolean hasNext() {
            return this.scala$collection$parallel$IterableSplitter$Mapped$$$outer().hasNext();
        }

        @Override
        public S next() {
            return this.scala$collection$parallel$IterableSplitter$Mapped$$f.apply(this.scala$collection$parallel$IterableSplitter$Mapped$$$outer().next());
        }

        @Override
        public int remaining() {
            return this.scala$collection$parallel$IterableSplitter$Mapped$$$outer().remaining();
        }

        @Override
        public IterableSplitter<S> dup() {
            return this.scala$collection$parallel$IterableSplitter$Mapped$$$outer().dup().map(this.scala$collection$parallel$IterableSplitter$Mapped$$f);
        }

        @Override
        public Seq<IterableSplitter<S>> split() {
            return this.scala$collection$parallel$IterableSplitter$Mapped$$$outer().split().map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Mapped $outer;

                public final Mapped<S> apply(IterableSplitter<T> x$7) {
                    return x$7.map(this.$outer.scala$collection$parallel$IterableSplitter$Mapped$$f);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        public /* synthetic */ IterableSplitter scala$collection$parallel$IterableSplitter$Mapped$$$outer() {
            return this.$outer;
        }

        public Mapped(IterableSplitter<T> $outer, Function1<T, S> f) {
            this.scala$collection$parallel$IterableSplitter$Mapped$$f = f;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            TraversableOnce$class.$init$(this);
            Iterator$class.$init$(this);
            RemainsIterator$class.$init$(this);
            AugmentedIterableIterator$class.$init$(this);
            DelegatedSignalling$class.$init$(this);
            IterableSplitter$class.$init$(this);
            this.signalDelegate_$eq($outer.signalDelegate());
        }
    }

    public class Zipped<S>
    implements IterableSplitter<Tuple2<T, S>> {
        private final SeqSplitter<S> that;
        public final /* synthetic */ IterableSplitter $outer;
        private Signalling signalDelegate;

        @Override
        public Signalling signalDelegate() {
            return this.signalDelegate;
        }

        @Override
        @TraitSetter
        public void signalDelegate_$eq(Signalling x$1) {
            this.signalDelegate = x$1;
        }

        @Override
        public Seq<IterableSplitter<Tuple2<T, S>>> splitWithSignalling() {
            return IterableSplitter$class.splitWithSignalling(this);
        }

        @Override
        public <S> boolean shouldSplitFurther(ParIterable<S> coll, int parallelismLevel) {
            return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
        }

        @Override
        public String buildString(Function1<Function1<String, BoxedUnit>, BoxedUnit> closure) {
            return IterableSplitter$class.buildString(this, closure);
        }

        @Override
        public String debugInformation() {
            return IterableSplitter$class.debugInformation(this);
        }

        @Override
        public Taken newTaken(int until2) {
            return IterableSplitter$class.newTaken(this, until2);
        }

        @Override
        public <U extends Taken> U newSliceInternal(U it, int from1) {
            return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
        }

        @Override
        public IterableSplitter<Tuple2<T, S>> take(int n) {
            return IterableSplitter$class.take(this, n);
        }

        @Override
        public IterableSplitter<Tuple2<T, S>> slice(int from1, int until1) {
            return IterableSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> Mapped<S> map(Function1<Tuple2<T, S>, S> f) {
            return IterableSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends IterableSplitter<U>> Appended<U, PI> appendParIterable(PI that) {
            return IterableSplitter$class.appendParIterable(this, that);
        }

        @Override
        public <S> Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return IterableSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
        }

        @Override
        public boolean isAborted() {
            return DelegatedSignalling$class.isAborted(this);
        }

        @Override
        public void abort() {
            DelegatedSignalling$class.abort(this);
        }

        @Override
        public int indexFlag() {
            return DelegatedSignalling$class.indexFlag(this);
        }

        @Override
        public void setIndexFlag(int f) {
            DelegatedSignalling$class.setIndexFlag(this, f);
        }

        @Override
        public void setIndexFlagIfGreater(int f) {
            DelegatedSignalling$class.setIndexFlagIfGreater(this, f);
        }

        @Override
        public void setIndexFlagIfLesser(int f) {
            DelegatedSignalling$class.setIndexFlagIfLesser(this, f);
        }

        @Override
        public int tag() {
            return DelegatedSignalling$class.tag(this);
        }

        @Override
        public int count(Function1<Tuple2<T, S>, Object> p) {
            return AugmentedIterableIterator$class.count(this, p);
        }

        @Override
        public <U> U reduce(Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduce(this, op);
        }

        @Override
        public <U> U fold(U z, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.fold(this, z, op);
        }

        @Override
        public <U> U sum(Numeric<U> num) {
            return (U)AugmentedIterableIterator$class.sum(this, num);
        }

        @Override
        public <U> U product(Numeric<U> num) {
            return (U)AugmentedIterableIterator$class.product(this, num);
        }

        @Override
        public Object min(Ordering ord) {
            return AugmentedIterableIterator$class.min(this, ord);
        }

        @Override
        public Object max(Ordering ord) {
            return AugmentedIterableIterator$class.max(this, ord);
        }

        @Override
        public <U> void copyToArray(Object array, int from2, int len) {
            AugmentedIterableIterator$class.copyToArray(this, array, from2, len);
        }

        @Override
        public <U> U reduceLeft(int howmany, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
        }

        @Override
        public <S, That> Combiner<S, That> map2combiner(Function1<Tuple2<T, S>, S> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.map2combiner(this, f, cb);
        }

        @Override
        public <S, That> Combiner<S, That> collect2combiner(PartialFunction<Tuple2<T, S>, S> pf, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
        }

        @Override
        public <S, That> Combiner<S, That> flatmap2combiner(Function1<Tuple2<T, S>, GenTraversableOnce<S>> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
        }

        @Override
        public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Bld b) {
            return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
        }

        @Override
        public <U, This> Combiner<U, This> filter2combiner(Function1<Tuple2<T, S>, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Combiner<U, This> filterNot2combiner(Function1<Tuple2<T, S>, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<Tuple2<T, S>, Object> pred, Combiner<U, This> btrue, Combiner<U, This> bfalse) {
            return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
        }

        @Override
        public <U, This> Combiner<U, This> take2combiner(int n, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.take2combiner(this, n, cb);
        }

        @Override
        public <U, This> Combiner<U, This> drop2combiner(int n, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
        }

        @Override
        public <U, This> Combiner<U, This> slice2combiner(int from2, int until2, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.slice2combiner(this, from2, until2, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<Tuple2<T, S>, Object> p, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<Tuple2<T, S>, Object> p, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
        }

        @Override
        public <U, A> void scanToArray(U z, Function2<U, U, U> op, Object array, int from2) {
            AugmentedIterableIterator$class.scanToArray(this, z, op, array, from2);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(int howmany, U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator<S> otherpit, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator<S> that, U thiselem, S thatelem, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
        }

        @Override
        public boolean isRemainingCheap() {
            return RemainsIterator$class.isRemainingCheap(this);
        }

        @Override
        public Iterator<Tuple2<T, S>> seq() {
            return Iterator$class.seq(this);
        }

        @Override
        public boolean isEmpty() {
            return Iterator$class.isEmpty(this);
        }

        @Override
        public boolean isTraversableAgain() {
            return Iterator$class.isTraversableAgain(this);
        }

        @Override
        public boolean hasDefiniteSize() {
            return Iterator$class.hasDefiniteSize(this);
        }

        @Override
        public Iterator<Tuple2<T, S>> drop(int n) {
            return Iterator$class.drop(this, n);
        }

        @Override
        public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
            return Iterator$class.$plus$plus(this, that);
        }

        @Override
        public <B> Iterator<B> flatMap(Function1<Tuple2<T, S>, GenTraversableOnce<B>> f) {
            return Iterator$class.flatMap(this, f);
        }

        @Override
        public Iterator<Tuple2<T, S>> filter(Function1<Tuple2<T, S>, Object> p) {
            return Iterator$class.filter(this, p);
        }

        @Override
        public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<Tuple2<T, S>, B, Object> p) {
            return Iterator$class.corresponds(this, that, p);
        }

        @Override
        public Iterator<Tuple2<T, S>> withFilter(Function1<Tuple2<T, S>, Object> p) {
            return Iterator$class.withFilter(this, p);
        }

        @Override
        public Iterator<Tuple2<T, S>> filterNot(Function1<Tuple2<T, S>, Object> p) {
            return Iterator$class.filterNot(this, p);
        }

        @Override
        public <B> Iterator<B> collect(PartialFunction<Tuple2<T, S>, B> pf) {
            return Iterator$class.collect(this, pf);
        }

        @Override
        public <B> Iterator<B> scanLeft(B z, Function2<B, Tuple2<T, S>, B> op) {
            return Iterator$class.scanLeft(this, z, op);
        }

        @Override
        public <B> Iterator<B> scanRight(B z, Function2<Tuple2<T, S>, B, B> op) {
            return Iterator$class.scanRight(this, z, op);
        }

        @Override
        public Iterator<Tuple2<T, S>> takeWhile(Function1<Tuple2<T, S>, Object> p) {
            return Iterator$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<Iterator<Tuple2<T, S>>, Iterator<Tuple2<T, S>>> partition(Function1<Tuple2<T, S>, Object> p) {
            return Iterator$class.partition(this, p);
        }

        @Override
        public Tuple2<Iterator<Tuple2<T, S>>, Iterator<Tuple2<T, S>>> span(Function1<Tuple2<T, S>, Object> p) {
            return Iterator$class.span(this, p);
        }

        @Override
        public Iterator<Tuple2<T, S>> dropWhile(Function1<Tuple2<T, S>, Object> p) {
            return Iterator$class.dropWhile(this, p);
        }

        @Override
        public <B> Iterator<Tuple2<Tuple2<T, S>, B>> zip(Iterator<B> that) {
            return Iterator$class.zip(this, that);
        }

        @Override
        public <A1> Iterator<A1> padTo(int len, A1 elem) {
            return Iterator$class.padTo(this, len, elem);
        }

        @Override
        public Iterator<Tuple2<Tuple2<T, S>, Object>> zipWithIndex() {
            return Iterator$class.zipWithIndex(this);
        }

        @Override
        public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
            return Iterator$class.zipAll(this, that, thisElem, thatElem);
        }

        @Override
        public <U> void foreach(Function1<Tuple2<T, S>, U> f) {
            Iterator$class.foreach(this, f);
        }

        @Override
        public boolean forall(Function1<Tuple2<T, S>, Object> p) {
            return Iterator$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<Tuple2<T, S>, Object> p) {
            return Iterator$class.exists(this, p);
        }

        @Override
        public boolean contains(Object elem) {
            return Iterator$class.contains(this, elem);
        }

        @Override
        public Option<Tuple2<T, S>> find(Function1<Tuple2<T, S>, Object> p) {
            return Iterator$class.find(this, p);
        }

        @Override
        public int indexWhere(Function1<Tuple2<T, S>, Object> p) {
            return Iterator$class.indexWhere(this, p);
        }

        @Override
        public <B> int indexOf(B elem) {
            return Iterator$class.indexOf(this, elem);
        }

        @Override
        public BufferedIterator<Tuple2<T, S>> buffered() {
            return Iterator$class.buffered(this);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> grouped(int size2) {
            return Iterator$class.grouped(this, size2);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
            return Iterator$class.sliding(this, size2, step);
        }

        @Override
        public int length() {
            return Iterator$class.length(this);
        }

        @Override
        public Tuple2<Iterator<Tuple2<T, S>>, Iterator<Tuple2<T, S>>> duplicate() {
            return Iterator$class.duplicate(this);
        }

        @Override
        public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
            return Iterator$class.patch(this, from2, patchElems, replaced);
        }

        @Override
        public boolean sameElements(Iterator<?> that) {
            return Iterator$class.sameElements(this, that);
        }

        @Override
        public Traversable<Tuple2<T, S>> toTraversable() {
            return Iterator$class.toTraversable(this);
        }

        @Override
        public Iterator<Tuple2<T, S>> toIterator() {
            return Iterator$class.toIterator(this);
        }

        @Override
        public Stream<Tuple2<T, S>> toStream() {
            return Iterator$class.toStream(this);
        }

        @Override
        public String toString() {
            return Iterator$class.toString(this);
        }

        @Override
        public <B> int sliding$default$2() {
            return Iterator$class.sliding$default$2(this);
        }

        @Override
        public List<Tuple2<T, S>> reversed() {
            return TraversableOnce$class.reversed(this);
        }

        @Override
        public int size() {
            return TraversableOnce$class.size(this);
        }

        @Override
        public boolean nonEmpty() {
            return TraversableOnce$class.nonEmpty(this);
        }

        @Override
        public <B> Option<B> collectFirst(PartialFunction<Tuple2<T, S>, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, Tuple2<T, S>, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<Tuple2<T, S>, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, Tuple2<T, S>, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<Tuple2<T, S>, B, B> op) {
            return (B)TraversableOnce$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, Tuple2<T, S>, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<Tuple2<T, S>, B, B> op) {
            return (B)TraversableOnce$class.reduceRight(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, Tuple2<T, S>, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<Tuple2<T, S>, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <B> B aggregate(Function0<B> z, Function2<B, Tuple2<T, S>, B> seqop, Function2<B, B, B> combop) {
            return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public Object maxBy(Function1 f, Ordering cmp) {
            return TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public Object minBy(Function1 f, Ordering cmp) {
            return TraversableOnce$class.minBy(this, f, cmp);
        }

        @Override
        public <B> void copyToBuffer(Buffer<B> dest) {
            TraversableOnce$class.copyToBuffer(this, dest);
        }

        @Override
        public <B> void copyToArray(Object xs, int start) {
            TraversableOnce$class.copyToArray(this, xs, start);
        }

        @Override
        public <B> void copyToArray(Object xs) {
            TraversableOnce$class.copyToArray(this, xs);
        }

        @Override
        public <B> Object toArray(ClassTag<B> evidence$1) {
            return TraversableOnce$class.toArray(this, evidence$1);
        }

        @Override
        public List<Tuple2<T, S>> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Iterable<Tuple2<T, S>> toIterable() {
            return TraversableOnce$class.toIterable(this);
        }

        @Override
        public Seq<Tuple2<T, S>> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<Tuple2<T, S>> toIndexedSeq() {
            return TraversableOnce$class.toIndexedSeq(this);
        }

        @Override
        public <B> Buffer<B> toBuffer() {
            return TraversableOnce$class.toBuffer(this);
        }

        @Override
        public <B> Set<B> toSet() {
            return TraversableOnce$class.toSet(this);
        }

        @Override
        public Vector<Tuple2<T, S>> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<T, S>, Col> cbf) {
            return (Col)TraversableOnce$class.to(this, cbf);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Tuple2<T, S>, Tuple2<T, U>> ev) {
            return TraversableOnce$class.toMap(this, ev);
        }

        @Override
        public String mkString(String start, String sep, String end) {
            return TraversableOnce$class.mkString(this, start, sep, end);
        }

        @Override
        public String mkString(String sep) {
            return TraversableOnce$class.mkString(this, sep);
        }

        @Override
        public String mkString() {
            return TraversableOnce$class.mkString(this);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
            return TraversableOnce$class.addString(this, b, start, sep, end);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String sep) {
            return TraversableOnce$class.addString(this, b, sep);
        }

        @Override
        public StringBuilder addString(StringBuilder b) {
            return TraversableOnce$class.addString(this, b);
        }

        public SeqSplitter<S> that() {
            return this.that;
        }

        @Override
        public boolean hasNext() {
            return this.scala$collection$parallel$IterableSplitter$Zipped$$$outer().hasNext() && this.that().hasNext();
        }

        @Override
        public Tuple2<T, S> next() {
            return new Tuple2(this.scala$collection$parallel$IterableSplitter$Zipped$$$outer().next(), this.that().next());
        }

        @Override
        public int remaining() {
            int n = this.scala$collection$parallel$IterableSplitter$Zipped$$$outer().remaining();
            Predef$ predef$ = Predef$.MODULE$;
            return RichInt$.MODULE$.min$extension(n, this.that().remaining());
        }

        @Override
        public IterableSplitter<Tuple2<T, S>> dup() {
            return this.scala$collection$parallel$IterableSplitter$Zipped$$$outer().dup().zipParSeq(this.that());
        }

        @Override
        public Seq<IterableSplitter<Tuple2<T, S>>> split() {
            Seq selfs = this.scala$collection$parallel$IterableSplitter$Zipped$$$outer().split();
            Seq<Object> sizes = selfs.map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply(IterableSplitter<T> x$8) {
                    return x$8.remaining();
                }
            }, Seq$.MODULE$.canBuildFrom());
            Seq<SeqSplitter<S>> thats = this.that().psplit(sizes);
            return ((TraversableLike)selfs.zip(thats, Seq$.MODULE$.canBuildFrom())).map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final Zipped<S> apply(Tuple2<IterableSplitter<T>, SeqSplitter<S>> p) {
                    return p._1().zipParSeq(p._2());
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        public /* synthetic */ IterableSplitter scala$collection$parallel$IterableSplitter$Zipped$$$outer() {
            return this.$outer;
        }

        public Zipped(IterableSplitter<T> $outer, SeqSplitter<S> that) {
            this.that = that;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            TraversableOnce$class.$init$(this);
            Iterator$class.$init$(this);
            RemainsIterator$class.$init$(this);
            AugmentedIterableIterator$class.$init$(this);
            DelegatedSignalling$class.$init$(this);
            IterableSplitter$class.$init$(this);
            this.signalDelegate_$eq($outer.signalDelegate());
        }
    }

    public class Appended<U, PI extends IterableSplitter<U>>
    implements IterableSplitter<U> {
        private final PI that;
        private IterableSplitter<U> curr;
        public final /* synthetic */ IterableSplitter $outer;
        private Signalling signalDelegate;

        @Override
        public Signalling signalDelegate() {
            return this.signalDelegate;
        }

        @Override
        @TraitSetter
        public void signalDelegate_$eq(Signalling x$1) {
            this.signalDelegate = x$1;
        }

        @Override
        public Seq<IterableSplitter<U>> splitWithSignalling() {
            return IterableSplitter$class.splitWithSignalling(this);
        }

        @Override
        public <S> boolean shouldSplitFurther(ParIterable<S> coll, int parallelismLevel) {
            return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
        }

        @Override
        public String buildString(Function1<Function1<String, BoxedUnit>, BoxedUnit> closure) {
            return IterableSplitter$class.buildString(this, closure);
        }

        @Override
        public String debugInformation() {
            return IterableSplitter$class.debugInformation(this);
        }

        @Override
        public Taken newTaken(int until2) {
            return IterableSplitter$class.newTaken(this, until2);
        }

        @Override
        public <U extends Taken> U newSliceInternal(U it, int from1) {
            return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
        }

        @Override
        public IterableSplitter<U> take(int n) {
            return IterableSplitter$class.take(this, n);
        }

        @Override
        public IterableSplitter<U> slice(int from1, int until1) {
            return IterableSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> Mapped<S> map(Function1<U, S> f) {
            return IterableSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends IterableSplitter<U>> Appended<U, PI> appendParIterable(PI that) {
            return IterableSplitter$class.appendParIterable(this, that);
        }

        @Override
        public <S> Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return IterableSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
        }

        @Override
        public boolean isAborted() {
            return DelegatedSignalling$class.isAborted(this);
        }

        @Override
        public void abort() {
            DelegatedSignalling$class.abort(this);
        }

        @Override
        public int indexFlag() {
            return DelegatedSignalling$class.indexFlag(this);
        }

        @Override
        public void setIndexFlag(int f) {
            DelegatedSignalling$class.setIndexFlag(this, f);
        }

        @Override
        public void setIndexFlagIfGreater(int f) {
            DelegatedSignalling$class.setIndexFlagIfGreater(this, f);
        }

        @Override
        public void setIndexFlagIfLesser(int f) {
            DelegatedSignalling$class.setIndexFlagIfLesser(this, f);
        }

        @Override
        public int tag() {
            return DelegatedSignalling$class.tag(this);
        }

        @Override
        public int count(Function1<U, Object> p) {
            return AugmentedIterableIterator$class.count(this, p);
        }

        @Override
        public <U> U reduce(Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduce(this, op);
        }

        @Override
        public <U> U fold(U z, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.fold(this, z, op);
        }

        @Override
        public <U> U sum(Numeric<U> num) {
            return (U)AugmentedIterableIterator$class.sum(this, num);
        }

        @Override
        public <U> U product(Numeric<U> num) {
            return (U)AugmentedIterableIterator$class.product(this, num);
        }

        @Override
        public <U> U min(Ordering<U> ord) {
            return (U)AugmentedIterableIterator$class.min(this, ord);
        }

        @Override
        public <U> U max(Ordering<U> ord) {
            return (U)AugmentedIterableIterator$class.max(this, ord);
        }

        @Override
        public <U> void copyToArray(Object array, int from2, int len) {
            AugmentedIterableIterator$class.copyToArray(this, array, from2, len);
        }

        @Override
        public <U> U reduceLeft(int howmany, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
        }

        @Override
        public <S, That> Combiner<S, That> map2combiner(Function1<U, S> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.map2combiner(this, f, cb);
        }

        @Override
        public <S, That> Combiner<S, That> collect2combiner(PartialFunction<U, S> pf, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
        }

        @Override
        public <S, That> Combiner<S, That> flatmap2combiner(Function1<U, GenTraversableOnce<S>> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
        }

        @Override
        public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Bld b) {
            return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
        }

        @Override
        public <U, This> Combiner<U, This> filter2combiner(Function1<U, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Combiner<U, This> filterNot2combiner(Function1<U, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<U, Object> pred, Combiner<U, This> btrue, Combiner<U, This> bfalse) {
            return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
        }

        @Override
        public <U, This> Combiner<U, This> take2combiner(int n, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.take2combiner(this, n, cb);
        }

        @Override
        public <U, This> Combiner<U, This> drop2combiner(int n, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
        }

        @Override
        public <U, This> Combiner<U, This> slice2combiner(int from2, int until2, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.slice2combiner(this, from2, until2, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<U, Object> p, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<U, Object> p, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
        }

        @Override
        public <U, A> void scanToArray(U z, Function2<U, U, U> op, Object array, int from2) {
            AugmentedIterableIterator$class.scanToArray(this, z, op, array, from2);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(int howmany, U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator<S> otherpit, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator<S> that, U thiselem, S thatelem, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
        }

        @Override
        public boolean isRemainingCheap() {
            return RemainsIterator$class.isRemainingCheap(this);
        }

        @Override
        public Iterator<U> seq() {
            return Iterator$class.seq(this);
        }

        @Override
        public boolean isEmpty() {
            return Iterator$class.isEmpty(this);
        }

        @Override
        public boolean isTraversableAgain() {
            return Iterator$class.isTraversableAgain(this);
        }

        @Override
        public boolean hasDefiniteSize() {
            return Iterator$class.hasDefiniteSize(this);
        }

        @Override
        public Iterator<U> drop(int n) {
            return Iterator$class.drop(this, n);
        }

        @Override
        public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
            return Iterator$class.$plus$plus(this, that);
        }

        @Override
        public <B> Iterator<B> flatMap(Function1<U, GenTraversableOnce<B>> f) {
            return Iterator$class.flatMap(this, f);
        }

        @Override
        public Iterator<U> filter(Function1<U, Object> p) {
            return Iterator$class.filter(this, p);
        }

        @Override
        public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<U, B, Object> p) {
            return Iterator$class.corresponds(this, that, p);
        }

        @Override
        public Iterator<U> withFilter(Function1<U, Object> p) {
            return Iterator$class.withFilter(this, p);
        }

        @Override
        public Iterator<U> filterNot(Function1<U, Object> p) {
            return Iterator$class.filterNot(this, p);
        }

        @Override
        public <B> Iterator<B> collect(PartialFunction<U, B> pf) {
            return Iterator$class.collect(this, pf);
        }

        @Override
        public <B> Iterator<B> scanLeft(B z, Function2<B, U, B> op) {
            return Iterator$class.scanLeft(this, z, op);
        }

        @Override
        public <B> Iterator<B> scanRight(B z, Function2<U, B, B> op) {
            return Iterator$class.scanRight(this, z, op);
        }

        @Override
        public Iterator<U> takeWhile(Function1<U, Object> p) {
            return Iterator$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<Iterator<U>, Iterator<U>> partition(Function1<U, Object> p) {
            return Iterator$class.partition(this, p);
        }

        @Override
        public Tuple2<Iterator<U>, Iterator<U>> span(Function1<U, Object> p) {
            return Iterator$class.span(this, p);
        }

        @Override
        public Iterator<U> dropWhile(Function1<U, Object> p) {
            return Iterator$class.dropWhile(this, p);
        }

        @Override
        public <B> Iterator<Tuple2<U, B>> zip(Iterator<B> that) {
            return Iterator$class.zip(this, that);
        }

        @Override
        public <A1> Iterator<A1> padTo(int len, A1 elem) {
            return Iterator$class.padTo(this, len, elem);
        }

        @Override
        public Iterator<Tuple2<U, Object>> zipWithIndex() {
            return Iterator$class.zipWithIndex(this);
        }

        @Override
        public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
            return Iterator$class.zipAll(this, that, thisElem, thatElem);
        }

        @Override
        public <U> void foreach(Function1<U, U> f) {
            Iterator$class.foreach(this, f);
        }

        @Override
        public boolean forall(Function1<U, Object> p) {
            return Iterator$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<U, Object> p) {
            return Iterator$class.exists(this, p);
        }

        @Override
        public boolean contains(Object elem) {
            return Iterator$class.contains(this, elem);
        }

        @Override
        public Option<U> find(Function1<U, Object> p) {
            return Iterator$class.find(this, p);
        }

        @Override
        public int indexWhere(Function1<U, Object> p) {
            return Iterator$class.indexWhere(this, p);
        }

        @Override
        public <B> int indexOf(B elem) {
            return Iterator$class.indexOf(this, elem);
        }

        @Override
        public BufferedIterator<U> buffered() {
            return Iterator$class.buffered(this);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> grouped(int size2) {
            return Iterator$class.grouped(this, size2);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
            return Iterator$class.sliding(this, size2, step);
        }

        @Override
        public int length() {
            return Iterator$class.length(this);
        }

        @Override
        public Tuple2<Iterator<U>, Iterator<U>> duplicate() {
            return Iterator$class.duplicate(this);
        }

        @Override
        public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
            return Iterator$class.patch(this, from2, patchElems, replaced);
        }

        @Override
        public boolean sameElements(Iterator<?> that) {
            return Iterator$class.sameElements(this, that);
        }

        @Override
        public Traversable<U> toTraversable() {
            return Iterator$class.toTraversable(this);
        }

        @Override
        public Iterator<U> toIterator() {
            return Iterator$class.toIterator(this);
        }

        @Override
        public Stream<U> toStream() {
            return Iterator$class.toStream(this);
        }

        @Override
        public String toString() {
            return Iterator$class.toString(this);
        }

        @Override
        public <B> int sliding$default$2() {
            return Iterator$class.sliding$default$2(this);
        }

        @Override
        public List<U> reversed() {
            return TraversableOnce$class.reversed(this);
        }

        @Override
        public int size() {
            return TraversableOnce$class.size(this);
        }

        @Override
        public boolean nonEmpty() {
            return TraversableOnce$class.nonEmpty(this);
        }

        @Override
        public <B> Option<B> collectFirst(PartialFunction<U, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, U, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<U, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, U, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<U, B, B> op) {
            return (B)TraversableOnce$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, U, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<U, B, B> op) {
            return (B)TraversableOnce$class.reduceRight(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, U, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<U, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <B> B aggregate(Function0<B> z, Function2<B, U, B> seqop, Function2<B, B, B> combop) {
            return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public <B> U maxBy(Function1<U, B> f, Ordering<B> cmp) {
            return (U)TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public <B> U minBy(Function1<U, B> f, Ordering<B> cmp) {
            return (U)TraversableOnce$class.minBy(this, f, cmp);
        }

        @Override
        public <B> void copyToBuffer(Buffer<B> dest) {
            TraversableOnce$class.copyToBuffer(this, dest);
        }

        @Override
        public <B> void copyToArray(Object xs, int start) {
            TraversableOnce$class.copyToArray(this, xs, start);
        }

        @Override
        public <B> void copyToArray(Object xs) {
            TraversableOnce$class.copyToArray(this, xs);
        }

        @Override
        public <B> Object toArray(ClassTag<B> evidence$1) {
            return TraversableOnce$class.toArray(this, evidence$1);
        }

        @Override
        public List<U> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Iterable<U> toIterable() {
            return TraversableOnce$class.toIterable(this);
        }

        @Override
        public Seq<U> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<U> toIndexedSeq() {
            return TraversableOnce$class.toIndexedSeq(this);
        }

        @Override
        public <B> Buffer<B> toBuffer() {
            return TraversableOnce$class.toBuffer(this);
        }

        @Override
        public <B> Set<B> toSet() {
            return TraversableOnce$class.toSet(this);
        }

        @Override
        public Vector<U> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, U, Col> cbf) {
            return (Col)TraversableOnce$class.to(this, cbf);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<U, Tuple2<T, U>> ev) {
            return TraversableOnce$class.toMap(this, ev);
        }

        @Override
        public String mkString(String start, String sep, String end) {
            return TraversableOnce$class.mkString(this, start, sep, end);
        }

        @Override
        public String mkString(String sep) {
            return TraversableOnce$class.mkString(this, sep);
        }

        @Override
        public String mkString() {
            return TraversableOnce$class.mkString(this);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
            return TraversableOnce$class.addString(this, b, start, sep, end);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String sep) {
            return TraversableOnce$class.addString(this, b, sep);
        }

        @Override
        public StringBuilder addString(StringBuilder b) {
            return TraversableOnce$class.addString(this, b);
        }

        public PI that() {
            return this.that;
        }

        public IterableSplitter<U> curr() {
            return this.curr;
        }

        public void curr_$eq(IterableSplitter<U> x$1) {
            this.curr = x$1;
        }

        @Override
        public boolean hasNext() {
            boolean bl;
            if (this.curr().hasNext()) {
                bl = true;
            } else if (this.curr() == this.scala$collection$parallel$IterableSplitter$Appended$$$outer()) {
                this.curr_$eq((IterableSplitter<U>)this.that());
                bl = this.curr().hasNext();
            } else {
                bl = false;
            }
            return bl;
        }

        @Override
        public U next() {
            Object a;
            if (this.curr() == this.scala$collection$parallel$IterableSplitter$Appended$$$outer()) {
                this.hasNext();
                a = this.curr().next();
            } else {
                a = this.curr().next();
            }
            return (U)a;
        }

        @Override
        public int remaining() {
            return this.curr() == this.scala$collection$parallel$IterableSplitter$Appended$$$outer() ? this.curr().remaining() + this.that().remaining() : this.curr().remaining();
        }

        public boolean firstNonEmpty() {
            return this.curr() == this.scala$collection$parallel$IterableSplitter$Appended$$$outer() && this.curr().hasNext();
        }

        @Override
        public IterableSplitter<U> dup() {
            return this.scala$collection$parallel$IterableSplitter$Appended$$$outer().dup().appendParIterable(this.that());
        }

        @Override
        public Seq<IterableSplitter<U>> split() {
            return this.firstNonEmpty() ? (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new IterableSplitter[]{this.curr(), this.that()})) : this.curr().split();
        }

        public /* synthetic */ IterableSplitter scala$collection$parallel$IterableSplitter$Appended$$$outer() {
            return this.$outer;
        }

        public Appended(IterableSplitter<T> $outer, PI that) {
            this.that = that;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            TraversableOnce$class.$init$(this);
            Iterator$class.$init$(this);
            RemainsIterator$class.$init$(this);
            AugmentedIterableIterator$class.$init$(this);
            DelegatedSignalling$class.$init$(this);
            IterableSplitter$class.$init$(this);
            this.signalDelegate_$eq($outer.signalDelegate());
            this.curr = $outer;
        }
    }

    public class ZippedAll<U, S>
    implements IterableSplitter<Tuple2<U, S>> {
        private final SeqSplitter<S> that;
        private final U thiselem;
        private final S thatelem;
        public final /* synthetic */ IterableSplitter $outer;
        private Signalling signalDelegate;

        @Override
        public Signalling signalDelegate() {
            return this.signalDelegate;
        }

        @Override
        @TraitSetter
        public void signalDelegate_$eq(Signalling x$1) {
            this.signalDelegate = x$1;
        }

        @Override
        public Seq<IterableSplitter<Tuple2<U, S>>> splitWithSignalling() {
            return IterableSplitter$class.splitWithSignalling(this);
        }

        @Override
        public <S> boolean shouldSplitFurther(ParIterable<S> coll, int parallelismLevel) {
            return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
        }

        @Override
        public String buildString(Function1<Function1<String, BoxedUnit>, BoxedUnit> closure) {
            return IterableSplitter$class.buildString(this, closure);
        }

        @Override
        public String debugInformation() {
            return IterableSplitter$class.debugInformation(this);
        }

        @Override
        public Taken newTaken(int until2) {
            return IterableSplitter$class.newTaken(this, until2);
        }

        @Override
        public <U extends Taken> U newSliceInternal(U it, int from1) {
            return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
        }

        @Override
        public IterableSplitter<Tuple2<U, S>> take(int n) {
            return IterableSplitter$class.take(this, n);
        }

        @Override
        public IterableSplitter<Tuple2<U, S>> slice(int from1, int until1) {
            return IterableSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> Mapped<S> map(Function1<Tuple2<U, S>, S> f) {
            return IterableSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends IterableSplitter<U>> Appended<U, PI> appendParIterable(PI that) {
            return IterableSplitter$class.appendParIterable(this, that);
        }

        @Override
        public <S> Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return IterableSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
        }

        @Override
        public boolean isAborted() {
            return DelegatedSignalling$class.isAborted(this);
        }

        @Override
        public void abort() {
            DelegatedSignalling$class.abort(this);
        }

        @Override
        public int indexFlag() {
            return DelegatedSignalling$class.indexFlag(this);
        }

        @Override
        public void setIndexFlag(int f) {
            DelegatedSignalling$class.setIndexFlag(this, f);
        }

        @Override
        public void setIndexFlagIfGreater(int f) {
            DelegatedSignalling$class.setIndexFlagIfGreater(this, f);
        }

        @Override
        public void setIndexFlagIfLesser(int f) {
            DelegatedSignalling$class.setIndexFlagIfLesser(this, f);
        }

        @Override
        public int tag() {
            return DelegatedSignalling$class.tag(this);
        }

        @Override
        public int count(Function1<Tuple2<U, S>, Object> p) {
            return AugmentedIterableIterator$class.count(this, p);
        }

        @Override
        public <U> U reduce(Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduce(this, op);
        }

        @Override
        public <U> U fold(U z, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.fold(this, z, op);
        }

        @Override
        public <U> U sum(Numeric<U> num) {
            return (U)AugmentedIterableIterator$class.sum(this, num);
        }

        @Override
        public <U> U product(Numeric<U> num) {
            return (U)AugmentedIterableIterator$class.product(this, num);
        }

        @Override
        public Object min(Ordering ord) {
            return AugmentedIterableIterator$class.min(this, ord);
        }

        @Override
        public Object max(Ordering ord) {
            return AugmentedIterableIterator$class.max(this, ord);
        }

        @Override
        public <U> void copyToArray(Object array, int from2, int len) {
            AugmentedIterableIterator$class.copyToArray(this, array, from2, len);
        }

        @Override
        public <U> U reduceLeft(int howmany, Function2<U, U, U> op) {
            return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
        }

        @Override
        public <S, That> Combiner<S, That> map2combiner(Function1<Tuple2<U, S>, S> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.map2combiner(this, f, cb);
        }

        @Override
        public <S, That> Combiner<S, That> collect2combiner(PartialFunction<Tuple2<U, S>, S> pf, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
        }

        @Override
        public <S, That> Combiner<S, That> flatmap2combiner(Function1<Tuple2<U, S>, GenTraversableOnce<S>> f, Combiner<S, That> cb) {
            return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
        }

        @Override
        public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Bld b) {
            return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
        }

        @Override
        public <U, This> Combiner<U, This> filter2combiner(Function1<Tuple2<U, S>, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Combiner<U, This> filterNot2combiner(Function1<Tuple2<U, S>, Object> pred, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<Tuple2<U, S>, Object> pred, Combiner<U, This> btrue, Combiner<U, This> bfalse) {
            return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
        }

        @Override
        public <U, This> Combiner<U, This> take2combiner(int n, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.take2combiner(this, n, cb);
        }

        @Override
        public <U, This> Combiner<U, This> drop2combiner(int n, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
        }

        @Override
        public <U, This> Combiner<U, This> slice2combiner(int from2, int until2, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.slice2combiner(this, from2, until2, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<Tuple2<U, S>, Object> p, Combiner<U, This> cb) {
            return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
        }

        @Override
        public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<Tuple2<U, S>, Object> p, Combiner<U, This> before, Combiner<U, This> after) {
            return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
        }

        @Override
        public <U, A> void scanToArray(U z, Function2<U, U, U> op, Object array, int from2) {
            AugmentedIterableIterator$class.scanToArray(this, z, op, array, from2);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
        }

        @Override
        public <U, That> Combiner<U, That> scanToCombiner(int howmany, U startValue, Function2<U, U, U> op, Combiner<U, That> cb) {
            return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator<S> otherpit, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
        }

        @Override
        public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator<S> that, U thiselem, S thatelem, Combiner<Tuple2<U, S>, That> cb) {
            return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
        }

        @Override
        public boolean isRemainingCheap() {
            return RemainsIterator$class.isRemainingCheap(this);
        }

        @Override
        public Iterator<Tuple2<U, S>> seq() {
            return Iterator$class.seq(this);
        }

        @Override
        public boolean isEmpty() {
            return Iterator$class.isEmpty(this);
        }

        @Override
        public boolean isTraversableAgain() {
            return Iterator$class.isTraversableAgain(this);
        }

        @Override
        public boolean hasDefiniteSize() {
            return Iterator$class.hasDefiniteSize(this);
        }

        @Override
        public Iterator<Tuple2<U, S>> drop(int n) {
            return Iterator$class.drop(this, n);
        }

        @Override
        public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
            return Iterator$class.$plus$plus(this, that);
        }

        @Override
        public <B> Iterator<B> flatMap(Function1<Tuple2<U, S>, GenTraversableOnce<B>> f) {
            return Iterator$class.flatMap(this, f);
        }

        @Override
        public Iterator<Tuple2<U, S>> filter(Function1<Tuple2<U, S>, Object> p) {
            return Iterator$class.filter(this, p);
        }

        @Override
        public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<Tuple2<U, S>, B, Object> p) {
            return Iterator$class.corresponds(this, that, p);
        }

        @Override
        public Iterator<Tuple2<U, S>> withFilter(Function1<Tuple2<U, S>, Object> p) {
            return Iterator$class.withFilter(this, p);
        }

        @Override
        public Iterator<Tuple2<U, S>> filterNot(Function1<Tuple2<U, S>, Object> p) {
            return Iterator$class.filterNot(this, p);
        }

        @Override
        public <B> Iterator<B> collect(PartialFunction<Tuple2<U, S>, B> pf) {
            return Iterator$class.collect(this, pf);
        }

        @Override
        public <B> Iterator<B> scanLeft(B z, Function2<B, Tuple2<U, S>, B> op) {
            return Iterator$class.scanLeft(this, z, op);
        }

        @Override
        public <B> Iterator<B> scanRight(B z, Function2<Tuple2<U, S>, B, B> op) {
            return Iterator$class.scanRight(this, z, op);
        }

        @Override
        public Iterator<Tuple2<U, S>> takeWhile(Function1<Tuple2<U, S>, Object> p) {
            return Iterator$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<Iterator<Tuple2<U, S>>, Iterator<Tuple2<U, S>>> partition(Function1<Tuple2<U, S>, Object> p) {
            return Iterator$class.partition(this, p);
        }

        @Override
        public Tuple2<Iterator<Tuple2<U, S>>, Iterator<Tuple2<U, S>>> span(Function1<Tuple2<U, S>, Object> p) {
            return Iterator$class.span(this, p);
        }

        @Override
        public Iterator<Tuple2<U, S>> dropWhile(Function1<Tuple2<U, S>, Object> p) {
            return Iterator$class.dropWhile(this, p);
        }

        @Override
        public <B> Iterator<Tuple2<Tuple2<U, S>, B>> zip(Iterator<B> that) {
            return Iterator$class.zip(this, that);
        }

        @Override
        public <A1> Iterator<A1> padTo(int len, A1 elem) {
            return Iterator$class.padTo(this, len, elem);
        }

        @Override
        public Iterator<Tuple2<Tuple2<U, S>, Object>> zipWithIndex() {
            return Iterator$class.zipWithIndex(this);
        }

        @Override
        public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
            return Iterator$class.zipAll(this, that, thisElem, thatElem);
        }

        @Override
        public <U> void foreach(Function1<Tuple2<U, S>, U> f) {
            Iterator$class.foreach(this, f);
        }

        @Override
        public boolean forall(Function1<Tuple2<U, S>, Object> p) {
            return Iterator$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<Tuple2<U, S>, Object> p) {
            return Iterator$class.exists(this, p);
        }

        @Override
        public boolean contains(Object elem) {
            return Iterator$class.contains(this, elem);
        }

        @Override
        public Option<Tuple2<U, S>> find(Function1<Tuple2<U, S>, Object> p) {
            return Iterator$class.find(this, p);
        }

        @Override
        public int indexWhere(Function1<Tuple2<U, S>, Object> p) {
            return Iterator$class.indexWhere(this, p);
        }

        @Override
        public <B> int indexOf(B elem) {
            return Iterator$class.indexOf(this, elem);
        }

        @Override
        public BufferedIterator<Tuple2<U, S>> buffered() {
            return Iterator$class.buffered(this);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> grouped(int size2) {
            return Iterator$class.grouped(this, size2);
        }

        @Override
        public <B> Iterator.GroupedIterator<B> sliding(int size2, int step) {
            return Iterator$class.sliding(this, size2, step);
        }

        @Override
        public int length() {
            return Iterator$class.length(this);
        }

        @Override
        public Tuple2<Iterator<Tuple2<U, S>>, Iterator<Tuple2<U, S>>> duplicate() {
            return Iterator$class.duplicate(this);
        }

        @Override
        public <B> Iterator<B> patch(int from2, Iterator<B> patchElems, int replaced) {
            return Iterator$class.patch(this, from2, patchElems, replaced);
        }

        @Override
        public boolean sameElements(Iterator<?> that) {
            return Iterator$class.sameElements(this, that);
        }

        @Override
        public Traversable<Tuple2<U, S>> toTraversable() {
            return Iterator$class.toTraversable(this);
        }

        @Override
        public Iterator<Tuple2<U, S>> toIterator() {
            return Iterator$class.toIterator(this);
        }

        @Override
        public Stream<Tuple2<U, S>> toStream() {
            return Iterator$class.toStream(this);
        }

        @Override
        public String toString() {
            return Iterator$class.toString(this);
        }

        @Override
        public <B> int sliding$default$2() {
            return Iterator$class.sliding$default$2(this);
        }

        @Override
        public List<Tuple2<U, S>> reversed() {
            return TraversableOnce$class.reversed(this);
        }

        @Override
        public int size() {
            return TraversableOnce$class.size(this);
        }

        @Override
        public boolean nonEmpty() {
            return TraversableOnce$class.nonEmpty(this);
        }

        @Override
        public <B> Option<B> collectFirst(PartialFunction<Tuple2<U, S>, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, Tuple2<U, S>, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<Tuple2<U, S>, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, Tuple2<U, S>, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<Tuple2<U, S>, B, B> op) {
            return (B)TraversableOnce$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, Tuple2<U, S>, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<Tuple2<U, S>, B, B> op) {
            return (B)TraversableOnce$class.reduceRight(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, Tuple2<U, S>, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<Tuple2<U, S>, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <B> B aggregate(Function0<B> z, Function2<B, Tuple2<U, S>, B> seqop, Function2<B, B, B> combop) {
            return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public Object maxBy(Function1 f, Ordering cmp) {
            return TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public Object minBy(Function1 f, Ordering cmp) {
            return TraversableOnce$class.minBy(this, f, cmp);
        }

        @Override
        public <B> void copyToBuffer(Buffer<B> dest) {
            TraversableOnce$class.copyToBuffer(this, dest);
        }

        @Override
        public <B> void copyToArray(Object xs, int start) {
            TraversableOnce$class.copyToArray(this, xs, start);
        }

        @Override
        public <B> void copyToArray(Object xs) {
            TraversableOnce$class.copyToArray(this, xs);
        }

        @Override
        public <B> Object toArray(ClassTag<B> evidence$1) {
            return TraversableOnce$class.toArray(this, evidence$1);
        }

        @Override
        public List<Tuple2<U, S>> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Iterable<Tuple2<U, S>> toIterable() {
            return TraversableOnce$class.toIterable(this);
        }

        @Override
        public Seq<Tuple2<U, S>> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<Tuple2<U, S>> toIndexedSeq() {
            return TraversableOnce$class.toIndexedSeq(this);
        }

        @Override
        public <B> Buffer<B> toBuffer() {
            return TraversableOnce$class.toBuffer(this);
        }

        @Override
        public <B> Set<B> toSet() {
            return TraversableOnce$class.toSet(this);
        }

        @Override
        public Vector<Tuple2<U, S>> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, Tuple2<U, S>, Col> cbf) {
            return (Col)TraversableOnce$class.to(this, cbf);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<Tuple2<U, S>, Tuple2<T, U>> ev) {
            return TraversableOnce$class.toMap(this, ev);
        }

        @Override
        public String mkString(String start, String sep, String end) {
            return TraversableOnce$class.mkString(this, start, sep, end);
        }

        @Override
        public String mkString(String sep) {
            return TraversableOnce$class.mkString(this, sep);
        }

        @Override
        public String mkString() {
            return TraversableOnce$class.mkString(this);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
            return TraversableOnce$class.addString(this, b, start, sep, end);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String sep) {
            return TraversableOnce$class.addString(this, b, sep);
        }

        @Override
        public StringBuilder addString(StringBuilder b) {
            return TraversableOnce$class.addString(this, b);
        }

        public SeqSplitter<S> that() {
            return this.that;
        }

        public U thiselem() {
            return this.thiselem;
        }

        public S thatelem() {
            return this.thatelem;
        }

        @Override
        public boolean hasNext() {
            return this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().hasNext() || this.that().hasNext();
        }

        @Override
        public Tuple2<U, S> next() {
            return this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().hasNext() ? (this.that().hasNext() ? new Tuple2(this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().next(), this.that().next()) : new Tuple2(this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().next(), this.thatelem())) : new Tuple2(this.thiselem(), this.that().next());
        }

        @Override
        public int remaining() {
            int n = this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().remaining();
            Predef$ predef$ = Predef$.MODULE$;
            return RichInt$.MODULE$.max$extension(n, this.that().remaining());
        }

        @Override
        public IterableSplitter<Tuple2<U, S>> dup() {
            return this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().dup().zipAllParSeq(this.that(), this.thiselem(), this.thatelem());
        }

        @Override
        public Seq<IterableSplitter<Tuple2<U, S>>> split() {
            int thatrem;
            int selfrem = this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().remaining();
            Appended thisit = selfrem < (thatrem = this.that().remaining()) ? this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().appendParIterable(package$.MODULE$.repetition(this.thiselem(), thatrem - selfrem).splitter()) : this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer();
            SeqSplitter<S> thatit = selfrem > thatrem ? this.that().appendParSeq(package$.MODULE$.repetition(this.thatelem(), selfrem - thatrem).splitter()) : this.that();
            Zipped<S> zipped = thisit.zipParSeq(thatit);
            return zipped.split();
        }

        public /* synthetic */ IterableSplitter scala$collection$parallel$IterableSplitter$ZippedAll$$$outer() {
            return this.$outer;
        }

        public ZippedAll(IterableSplitter<T> $outer, SeqSplitter<S> that, U thiselem, S thatelem) {
            this.that = that;
            this.thiselem = thiselem;
            this.thatelem = thatelem;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            TraversableOnce$class.$init$(this);
            Iterator$class.$init$(this);
            RemainsIterator$class.$init$(this);
            AugmentedIterableIterator$class.$init$(this);
            DelegatedSignalling$class.$init$(this);
            IterableSplitter$class.$init$(this);
            this.signalDelegate_$eq($outer.signalDelegate());
        }
    }
}

