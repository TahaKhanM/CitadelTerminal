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
import scala.Tuple3;
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
import scala.collection.parallel.AugmentedIterableIterator$class;
import scala.collection.parallel.AugmentedSeqIterator;
import scala.collection.parallel.AugmentedSeqIterator$class;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.IterableSplitter$class;
import scala.collection.parallel.ParIterable;
import scala.collection.parallel.PreciseSplitter;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.RemainsIterator$class;
import scala.collection.parallel.SeqSplitter$class;
import scala.collection.parallel.immutable.package$;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\tMf\u0001C\u0001\u0003!\u0003\r\t!C\u0017\u0003\u0017M+\u0017o\u00159mSR$XM\u001d\u0006\u0003\u0007\u0011\t\u0001\u0002]1sC2dW\r\u001c\u0006\u0003\u000b\u0019\t!bY8mY\u0016\u001cG/[8o\u0015\u00059\u0011!B:dC2\f7\u0001A\u000b\u0003\u0015U\u0019R\u0001A\u0006\u0010=\u0005\u0002\"\u0001D\u0007\u000e\u0003\u0019I!A\u0004\u0004\u0003\r\u0005s\u0017PU3g!\r\u0001\u0012cE\u0007\u0002\u0005%\u0011!C\u0001\u0002\u0011\u0013R,'/\u00192mKN\u0003H.\u001b;uKJ\u0004\"\u0001F\u000b\r\u0001\u00111a\u0003\u0001CC\u0002]\u0011\u0011\u0001V\t\u00031m\u0001\"\u0001D\r\n\u0005i1!a\u0002(pi\"Lgn\u001a\t\u0003\u0019qI!!\b\u0004\u0003\u0007\u0005s\u0017\u0010E\u0002\u0011?MI!\u0001\t\u0002\u0003)\u0005+x-\\3oi\u0016$7+Z9Ji\u0016\u0014\u0018\r^8s!\r\u0001\"eE\u0005\u0003G\t\u0011q\u0002\u0015:fG&\u001cXm\u00159mSR$XM\u001d\u0005\u0006K\u0001!\tAJ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u001d\u0002\"\u0001\u0004\u0015\n\u0005%2!\u0001B+oSRDQa\u000b\u0001\u0007\u00021\n1\u0001Z;q+\u0005i\u0003c\u0001\t\u0001'!)q\u0006\u0001D\u0001a\u0005)1\u000f\u001d7jiV\t\u0011\u0007E\u00023k5r!\u0001D\u001a\n\u0005Q2\u0011a\u00029bG.\fw-Z\u0005\u0003m]\u00121aU3r\u0015\t!d\u0001C\u0003:\u0001\u0019\u0005!(\u0001\u0004qgBd\u0017\u000e\u001e\u000b\u0003cmBQ\u0001\u0010\u001dA\u0002u\nQa]5{KN\u00042\u0001\u0004 A\u0013\tydA\u0001\u0006=e\u0016\u0004X-\u0019;fIz\u0002\"\u0001D!\n\u0005\t3!aA%oi\")A\t\u0001C!a\u0005\u00192\u000f\u001d7ji^KG\u000f[*jO:\fG\u000e\\5oO\")a\t\u0001C\u0001\u000f\u0006!\u0002o\u001d9mSR<\u0016\u000e\u001e5TS\u001et\u0017\r\u001c7j]\u001e$\"!\r%\t\u000bq*\u0005\u0019A\u001f\t\u000b)\u0003a\u0011A&\u0002\u0013I,W.Y5oS:<W#\u0001!\u0007\t5\u0003\u0001A\u0014\u0002\u0006)\u0006\\WM\\\n\u0004\u0019>k\u0003C\u0001)R\u001b\u0005\u0001\u0011BA'\u0012\u0011!\u0019FJ!A!\u0002\u0013\u0001\u0015A\u0001;l\u0011\u0015)F\n\"\u0001W\u0003\u0019a\u0014N\\5u}Q\u0011q\u000b\u0017\t\u0003!2CQa\u0015+A\u0002\u0001CQa\u000b'\u0005B1BQa\f'\u0005BABQ!\u000f'\u0005\u0002q#\"!M/\t\u000bqZ\u0006\u0019A\u001f\t\r}\u0003A\u0011\t\u0003a\u0003!qWm\u001e+bW\u0016tGCA,b\u0011\u0015\u0011g\f1\u0001A\u0003\u0015)h\u000e^5m\u0011\u0015!\u0007\u0001\"\u0011f\u0003\u0011!\u0018m[3\u0015\u000552\u0007\"B4d\u0001\u0004\u0001\u0015!\u00018\t\u000b%\u0004A\u0011\t6\u0002\u000bMd\u0017nY3\u0015\u00075ZW\u000eC\u0003mQ\u0002\u0007\u0001)A\u0003ge>l\u0017\u0007C\u0003oQ\u0002\u0007\u0001)\u0001\u0004v]RLG.\r\u0004\u0005a\u0002\u0001\u0011O\u0001\u0004NCB\u0004X\rZ\u000b\u0003eZ\u001c2a\\:y!\r\u0001F/^\u0005\u0003aF\u0001\"\u0001\u0006<\u0005\u000b]|'\u0019A\f\u0003\u0003M\u00032\u0001\u0005\u0001v\u0011!QxN!A!\u0002\u0013Y\u0018!\u00014\u0011\t1a8#^\u0005\u0003{\u001a\u0011\u0011BR;oGRLwN\\\u0019\t\u000bU{G\u0011A@\u0015\t\u0005\u0005\u00111\u0001\t\u0004!>,\b\"\u0002>\u007f\u0001\u0004Y\bBB\u0016p\t\u0003\n9!F\u0001y\u0011\u0019ys\u000e\"\u0011\u0002\fU\u0011\u0011Q\u0002\t\u0004eUB\bBB\u001dp\t\u0003\t\t\u0002\u0006\u0003\u0002\u000e\u0005M\u0001B\u0002\u001f\u0002\u0010\u0001\u0007Q\bC\u0004\u0002\u0018\u0001!\t%!\u0007\u0002\u00075\f\u0007/\u0006\u0003\u0002\u001c\u0005\u0005B\u0003BA\u000f\u0003G\u0001B\u0001U8\u0002 A\u0019A#!\t\u0005\r]\f)B1\u0001\u0018\u0011\u001dQ\u0018Q\u0003a\u0001\u0003K\u0001R\u0001\u0004?\u0014\u0003?1a!!\u000b\u0001\u0001\u0005-\"\u0001C!qa\u0016tG-\u001a3\u0016\r\u00055\u0012QGA\u001f'\u0019\t9#a\f\u0002DA9\u0001+!\r\u00024\u0005m\u0012bAA\u0015#A\u0019A#!\u000e\u0005\u0011\u0005]\u0012q\u0005b\u0001\u0003s\u0011\u0011!V\t\u0003'm\u00012\u0001FA\u001f\t!\ty$a\nC\u0002\u0005\u0005#A\u0001)J#\rA\u00121\t\t\u0005!\u0001\t\u0019\u0004C\u0007\u0002H\u0005\u001d\"\u0011!Q\u0001\n\u0005m\u0012\u0011J\u0001\u0003SRLA!a\u0013\u00022\u0005!A\u000f[1u\u0011\u001d)\u0016q\u0005C\u0001\u0003\u001f\"B!!\u0015\u0002TA9\u0001+a\n\u00024\u0005m\u0002\u0002CA$\u0003\u001b\u0002\r!a\u000f\t\u000f-\n9\u0003\"\u0011\u0002XU\u0011\u00111\t\u0005\b_\u0005\u001dB\u0011IA.+\t\ti\u0006\u0005\u00033k\u0005\r\u0003bB\u001d\u0002(\u0011\u0005\u0011\u0011\r\u000b\u0005\u0003;\n\u0019\u0007\u0003\u0004=\u0003?\u0002\r!\u0010\u0005\b\u0003O\u0002A\u0011AA5\u00031\t\u0007\u000f]3oIB\u000b'oU3r+\u0019\tY'!\u001d\u0002vQ!\u0011QNA>!\u001d\u0001\u0016qEA8\u0003g\u00022\u0001FA9\t!\t9$!\u001aC\u0002\u0005e\u0002c\u0001\u000b\u0002v\u0011A\u0011qHA3\u0005\u0004\t9(E\u0002\u0019\u0003s\u0002B\u0001\u0005\u0001\u0002p!A\u00111JA3\u0001\u0004\t\u0019H\u0002\u0004\u0002\u0000\u0001\u0001\u0011\u0011\u0011\u0002\u00075&\u0004\b/\u001a3\u0016\t\u0005\r\u00151R\n\u0007\u0003{\n))!$\u0011\u000bA\u000b9)!#\n\u0007\u0005}\u0014\u0003E\u0002\u0015\u0003\u0017#aa^A?\u0005\u00049\u0002\u0003\u0002\t\u0001\u0003\u001f\u0003b\u0001DAI'\u0005%\u0015bAAJ\r\t1A+\u001e9mKJBQ\"a&\u0002~\t\u0005\t\u0015!\u0003\u0002\u001a\u0006m\u0015A\u0001;j!\u0011\u0001\u0002!!#\n\t\u0005-\u0013q\u0011\u0005\b+\u0006uD\u0011AAP)\u0011\t\t+a)\u0011\u000bA\u000bi(!#\t\u0011\u0005]\u0015Q\u0014a\u0001\u00033CqaKA?\t\u0003\n9+\u0006\u0002\u0002\u000e\"9q&! \u0005B\u0005-VCAAW!\u0011\u0011T'!$\t\u000fe\ni\b\"\u0001\u00022R!\u0011QVAZ\u0011\u001d\t),a,A\u0002u\n1a\u001d>t\u0011\u001d\tI\f\u0001C!\u0003w\u000b\u0011B_5q!\u0006\u00148+Z9\u0016\t\u0005u\u00161\u0019\u000b\u0005\u0003\u007f\u000b)\rE\u0003Q\u0003{\n\t\rE\u0002\u0015\u0003\u0007$aa^A\\\u0005\u00049\u0002\u0002CA&\u0003o\u0003\r!a2\u0011\tA\u0001\u0011\u0011\u0019\u0004\u0007\u0003\u0017\u0004\u0001!!4\u0003\u0013iK\u0007\u000f]3e\u00032dWCBAh\u0003/\fYn\u0005\u0004\u0002J\u0006E\u0017Q\u001c\t\b!\u0006M\u0017Q[Am\u0013\r\tY-\u0005\t\u0004)\u0005]G\u0001CA\u001c\u0003\u0013\u0014\r!!\u000f\u0011\u0007Q\tY\u000e\u0002\u0004x\u0003\u0013\u0014\ra\u0006\t\u0005!\u0001\ty\u000eE\u0004\r\u0003#\u000b).!7\t\u001b\u0005]\u0015\u0011\u001aB\u0001B\u0003%\u00111]As!\u0011\u0001\u0002!!7\n\t\u0005-\u00131\u001b\u0005\u000e\u0003S\fIM!A!\u0002\u0013\t).a;\u0002\u000bQD\u0017n]3\n\t\u00055\u00181[\u0001\ti\"L7/\u001a7f[\"i\u0011\u0011_Ae\u0005\u0003\u0005\u000b\u0011BAm\u0003g\fQ\u0001\u001e5bi\u0016LA!!>\u0002T\u0006AA\u000f[1uK2,W\u000eC\u0004V\u0003\u0013$\t!!?\u0015\u0011\u0005m\u0018Q`A\u0000\u0005\u0003\u0001r\u0001UAe\u0003+\fI\u000e\u0003\u0005\u0002\u0018\u0006]\b\u0019AAr\u0011!\tI/a>A\u0002\u0005U\u0007\u0002CAy\u0003o\u0004\r!!7\t\u000f-\nI\r\"\u0011\u0003\u0006U\u0011\u0011Q\u001c\u0005\t\u0005\u0013\tI\r\"\u0003\u0003\f\u00059\u0001/\u0019;dQ\u0016lWC\u0001B\u0007!\u001da\u0011\u0011\u0013B\b\u0003G\u0004B\u0001\u0005\u0001\u0002V\"9q&!3\u0005B\tMQC\u0001B\u000b!\u0011\u0011T'!8\t\u000fe\nI\r\"\u0001\u0003\u001aQ!!Q\u0003B\u000e\u0011\u0019a$q\u0003a\u0001{!9!q\u0004\u0001\u0005B\t\u0005\u0012\u0001\u0004>ja\u0006cG\u000eU1s'\u0016\fX\u0003\u0003B\u0012\u0005k\u0011IC!\f\u0015\u0011\t\u0015\"q\u0007B\u001e\u0005\u007f\u0001r\u0001UAe\u0005O\u0011Y\u0003E\u0002\u0015\u0005S!\u0001\"a\u000e\u0003\u001e\t\u0007\u0011\u0011\b\t\u0004)\t5B\u0001\u0003B\u0018\u0005;\u0011\rA!\r\u0003\u0003I\u000b2Aa\r\u001c!\r!\"Q\u0007\u0003\u0007o\nu!\u0019A\f\t\u0011\u0005-#Q\u0004a\u0001\u0005s\u0001B\u0001\u0005\u0001\u00034!A!Q\bB\u000f\u0001\u0004\u00119#\u0001\u0005uQ&\u001cX\t\\3n\u0011!\u0011\tE!\bA\u0002\t-\u0012\u0001\u0003;iCR,E.Z7\t\r\t\u0015\u0003\u0001\"\u0001-\u0003\u001d\u0011XM^3sg\u00164aA!\u0013\u0001\u0001\t-#a\u0002)bi\u000eDW\rZ\u000b\u0005\u0005\u001b\u0012\u0019fE\u0003\u0003H-\u0011y\u0005\u0005\u0003\u0011\u0001\tE\u0003c\u0001\u000b\u0003T\u0011A\u0011q\u0007B$\u0005\u0004\tI\u0004\u0003\u0006\u0003X\t\u001d#\u0011!Q\u0001\n\u0001\u000bAA\u001a:p[\"Y!1\fB$\u0005\u0003\u0005\u000b\u0011\u0002B(\u0003\u0015\u0001\u0018\r^2i\u0011)\u0011yFa\u0012\u0003\u0002\u0003\u0006I\u0001Q\u0001\te\u0016\u0004H.Y2fI\"9QKa\u0012\u0005\u0002\t\rD\u0003\u0003B3\u0005O\u0012IGa\u001b\u0011\u000bA\u00139E!\u0015\t\u000f\t]#\u0011\ra\u0001\u0001\"A!1\fB1\u0001\u0004\u0011y\u0005C\u0004\u0003`\t\u0005\u0004\u0019\u0001!\t\u0013\t=$q\tQ\u0001\n\tE\u0014\u0001\u0002;sS>\u0004rAa\u001d\u0002(\tES\u0006E\u0004.\u0003O\u0011\tFa\u0014\t\u0011\t]$q\tC\u0001\u0005s\nq\u0001[1t\u001d\u0016DH/\u0006\u0002\u0003|A\u0019AB! \n\u0007\t}dAA\u0004C_>dW-\u00198\t\u0011\t\r%q\tC\u0001\u0005\u000b\u000bAA\\3yiR\u0011!\u0011\u000b\u0005\u0007\u0015\n\u001dC\u0011A&\t\u000f-\u00129\u0005\"\u0001\u0003\fV\u0011!Q\u0012\t\u0006[\t\u001d#\u0011\u000b\u0005\b_\t\u001dC\u0011\u0001BI+\t\u0011\u0019\n\u0005\u00033k\t=\u0003bB\u001d\u0003H\u0011\u0005!q\u0013\u000b\u0005\u0005'\u0013I\n\u0003\u0004=\u0005+\u0003\r!\u0010\u0005\b\u0005;\u0003A\u0011\u0001BP\u0003-\u0001\u0018\r^2i!\u0006\u00148+Z9\u0016\t\t\u0005&q\u0015\u000b\t\u0005G\u0013IKa+\u00032B)\u0001Ka\u0012\u0003&B\u0019ACa*\u0005\u0011\u0005]\"1\u0014b\u0001\u0003sAqAa\u0016\u0003\u001c\u0002\u0007\u0001\t\u0003\u0005\u0003.\nm\u0005\u0019\u0001BX\u0003)\u0001\u0018\r^2i\u000b2,Wn\u001d\t\u0005!\u0001\u0011)\u000bC\u0004\u0003`\tm\u0005\u0019\u0001!")
public interface SeqSplitter<T>
extends IterableSplitter<T>,
AugmentedSeqIterator<T>,
PreciseSplitter<T> {
    @Override
    public SeqSplitter<T> dup();

    @Override
    public Seq<SeqSplitter<T>> split();

    @Override
    public Seq<SeqSplitter<T>> psplit(Seq<Object> var1);

    @Override
    public Seq<SeqSplitter<T>> splitWithSignalling();

    public Seq<SeqSplitter<T>> psplitWithSignalling(Seq<Object> var1);

    @Override
    public int remaining();

    @Override
    public Taken newTaken(int var1);

    @Override
    public SeqSplitter<T> take(int var1);

    @Override
    public SeqSplitter<T> slice(int var1, int var2);

    @Override
    public <S> Mapped<S> map(Function1<T, S> var1);

    public <U, PI extends SeqSplitter<U>> Appended<U, PI> appendParSeq(PI var1);

    @Override
    public <S> Zipped<S> zipParSeq(SeqSplitter<S> var1);

    @Override
    public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> var1, U var2, R var3);

    public SeqSplitter<T> reverse();

    public <U> Patched<U> patchParSeq(int var1, SeqSplitter<U> var2, int var3);

    public class Taken
    extends IterableSplitter.Taken
    implements SeqSplitter<T> {
        @Override
        public Seq<SeqSplitter<T>> splitWithSignalling() {
            return SeqSplitter$class.splitWithSignalling(this);
        }

        @Override
        public Seq<SeqSplitter<T>> psplitWithSignalling(Seq<Object> sizes) {
            return SeqSplitter$class.psplitWithSignalling(this, sizes);
        }

        @Override
        public Taken newTaken(int until2) {
            return SeqSplitter$class.newTaken(this, until2);
        }

        @Override
        public SeqSplitter<T> take(int n) {
            return SeqSplitter$class.take(this, n);
        }

        @Override
        public SeqSplitter<T> slice(int from1, int until1) {
            return SeqSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> Mapped<S> map(Function1<T, S> f) {
            return SeqSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends SeqSplitter<U>> Appended<U, PI> appendParSeq(PI that) {
            return SeqSplitter$class.appendParSeq(this, that);
        }

        @Override
        public <S> Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return SeqSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
        }

        @Override
        public SeqSplitter<T> reverse() {
            return SeqSplitter$class.reverse(this);
        }

        @Override
        public <U> Patched<U> patchParSeq(int from2, SeqSplitter<U> patchElems, int replaced) {
            return SeqSplitter$class.patchParSeq(this, from2, patchElems, replaced);
        }

        @Override
        public int prefixLength(Function1<T, Object> pred) {
            return AugmentedSeqIterator$class.prefixLength(this, pred);
        }

        @Override
        public int indexWhere(Function1<T, Object> pred) {
            return AugmentedSeqIterator$class.indexWhere(this, pred);
        }

        @Override
        public int lastIndexWhere(Function1<T, Object> pred) {
            return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
        }

        @Override
        public <S> boolean corresponds(Function2<T, S, Object> corr, Iterator<S> that) {
            return AugmentedSeqIterator$class.corresponds(this, corr, that);
        }

        @Override
        public <U, This> Combiner<U, This> reverse2combiner(Combiner<U, This> cb) {
            return AugmentedSeqIterator$class.reverse2combiner(this, cb);
        }

        @Override
        public <S, That> Combiner<S, That> reverseMap2combiner(Function1<T, S> f, Combiner<S, That> cb) {
            return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
        }

        @Override
        public <U, That> Combiner<U, That> updated2combiner(int index, U elem, Combiner<U, That> cb) {
            return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
        }

        @Override
        public SeqSplitter<T> dup() {
            return (SeqSplitter)super.dup();
        }

        @Override
        public Seq<SeqSplitter<T>> split() {
            return super.split();
        }

        @Override
        public Seq<SeqSplitter<T>> psplit(Seq<Object> sizes) {
            return this.takeSeq(this.scala$collection$parallel$SeqSplitter$Taken$$$outer().psplit(sizes), new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final SeqSplitter<T> apply(SeqSplitter<T> p, int n) {
                    return p.take(n);
                }
            });
        }

        public /* synthetic */ SeqSplitter scala$collection$parallel$SeqSplitter$Taken$$$outer() {
            return (SeqSplitter)this.$outer;
        }

        public Taken(SeqSplitter<T> $outer, int tk) {
            super($outer, tk);
            AugmentedSeqIterator$class.$init$(this);
            SeqSplitter$class.$init$(this);
        }
    }

    public class Mapped<S>
    extends IterableSplitter.Mapped<S>
    implements SeqSplitter<S> {
        public final Function1<T, S> scala$collection$parallel$SeqSplitter$Mapped$$f;

        @Override
        public Seq<SeqSplitter<S>> splitWithSignalling() {
            return SeqSplitter$class.splitWithSignalling(this);
        }

        @Override
        public Seq<SeqSplitter<S>> psplitWithSignalling(Seq<Object> sizes) {
            return SeqSplitter$class.psplitWithSignalling(this, sizes);
        }

        @Override
        public Taken newTaken(int until2) {
            return SeqSplitter$class.newTaken(this, until2);
        }

        @Override
        public SeqSplitter<S> take(int n) {
            return SeqSplitter$class.take(this, n);
        }

        @Override
        public SeqSplitter<S> slice(int from1, int until1) {
            return SeqSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> Mapped<S> map(Function1<S, S> f) {
            return SeqSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends SeqSplitter<U>> Appended<U, PI> appendParSeq(PI that) {
            return SeqSplitter$class.appendParSeq(this, that);
        }

        @Override
        public <S> Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return SeqSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
        }

        @Override
        public SeqSplitter<S> reverse() {
            return SeqSplitter$class.reverse(this);
        }

        @Override
        public <U> Patched<U> patchParSeq(int from2, SeqSplitter<U> patchElems, int replaced) {
            return SeqSplitter$class.patchParSeq(this, from2, patchElems, replaced);
        }

        @Override
        public int prefixLength(Function1<S, Object> pred) {
            return AugmentedSeqIterator$class.prefixLength(this, pred);
        }

        @Override
        public int indexWhere(Function1<S, Object> pred) {
            return AugmentedSeqIterator$class.indexWhere(this, pred);
        }

        @Override
        public int lastIndexWhere(Function1<S, Object> pred) {
            return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
        }

        @Override
        public <S> boolean corresponds(Function2<S, S, Object> corr, Iterator<S> that) {
            return AugmentedSeqIterator$class.corresponds(this, corr, that);
        }

        @Override
        public <U, This> Combiner<U, This> reverse2combiner(Combiner<U, This> cb) {
            return AugmentedSeqIterator$class.reverse2combiner(this, cb);
        }

        @Override
        public <S, That> Combiner<S, That> reverseMap2combiner(Function1<S, S> f, Combiner<S, That> cb) {
            return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
        }

        @Override
        public <U, That> Combiner<U, That> updated2combiner(int index, U elem, Combiner<U, That> cb) {
            return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
        }

        @Override
        public SeqSplitter<S> dup() {
            return (SeqSplitter)super.dup();
        }

        @Override
        public Seq<SeqSplitter<S>> split() {
            return super.split();
        }

        @Override
        public Seq<SeqSplitter<S>> psplit(Seq<Object> sizes) {
            return this.scala$collection$parallel$SeqSplitter$Mapped$$$outer().psplit(sizes).map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Mapped $outer;

                public final Mapped<S> apply(SeqSplitter<T> x$11) {
                    return x$11.map(this.$outer.scala$collection$parallel$SeqSplitter$Mapped$$f);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        public /* synthetic */ SeqSplitter scala$collection$parallel$SeqSplitter$Mapped$$$outer() {
            return (SeqSplitter)this.$outer;
        }

        public Mapped(SeqSplitter<T> $outer, Function1<T, S> f) {
            this.scala$collection$parallel$SeqSplitter$Mapped$$f = f;
            super($outer, f);
            AugmentedSeqIterator$class.$init$(this);
            SeqSplitter$class.$init$(this);
        }
    }

    public class Zipped<S>
    extends IterableSplitter.Zipped<S>
    implements SeqSplitter<Tuple2<T, S>> {
        @Override
        public Seq<SeqSplitter<Tuple2<T, S>>> splitWithSignalling() {
            return SeqSplitter$class.splitWithSignalling(this);
        }

        @Override
        public Seq<SeqSplitter<Tuple2<T, S>>> psplitWithSignalling(Seq<Object> sizes) {
            return SeqSplitter$class.psplitWithSignalling(this, sizes);
        }

        @Override
        public Taken newTaken(int until2) {
            return SeqSplitter$class.newTaken(this, until2);
        }

        @Override
        public SeqSplitter<Tuple2<T, S>> take(int n) {
            return SeqSplitter$class.take(this, n);
        }

        @Override
        public SeqSplitter<Tuple2<T, S>> slice(int from1, int until1) {
            return SeqSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> Mapped<S> map(Function1<Tuple2<T, S>, S> f) {
            return SeqSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends SeqSplitter<U>> Appended<U, PI> appendParSeq(PI that) {
            return SeqSplitter$class.appendParSeq(this, that);
        }

        @Override
        public <S> Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return SeqSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
        }

        @Override
        public SeqSplitter<Tuple2<T, S>> reverse() {
            return SeqSplitter$class.reverse(this);
        }

        @Override
        public <U> Patched<U> patchParSeq(int from2, SeqSplitter<U> patchElems, int replaced) {
            return SeqSplitter$class.patchParSeq(this, from2, patchElems, replaced);
        }

        @Override
        public int prefixLength(Function1<Tuple2<T, S>, Object> pred) {
            return AugmentedSeqIterator$class.prefixLength(this, pred);
        }

        @Override
        public int indexWhere(Function1<Tuple2<T, S>, Object> pred) {
            return AugmentedSeqIterator$class.indexWhere(this, pred);
        }

        @Override
        public int lastIndexWhere(Function1<Tuple2<T, S>, Object> pred) {
            return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
        }

        @Override
        public <S> boolean corresponds(Function2<Tuple2<T, S>, S, Object> corr, Iterator<S> that) {
            return AugmentedSeqIterator$class.corresponds(this, corr, that);
        }

        @Override
        public <U, This> Combiner<U, This> reverse2combiner(Combiner<U, This> cb) {
            return AugmentedSeqIterator$class.reverse2combiner(this, cb);
        }

        @Override
        public <S, That> Combiner<S, That> reverseMap2combiner(Function1<Tuple2<T, S>, S> f, Combiner<S, That> cb) {
            return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
        }

        @Override
        public <U, That> Combiner<U, That> updated2combiner(int index, U elem, Combiner<U, That> cb) {
            return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
        }

        @Override
        public SeqSplitter<Tuple2<T, S>> dup() {
            return (SeqSplitter)super.dup();
        }

        @Override
        public Seq<SeqSplitter<Tuple2<T, S>>> split() {
            return super.split();
        }

        @Override
        public Seq<SeqSplitter<Tuple2<T, S>>> psplit(Seq<Object> szs) {
            return ((TraversableLike)this.scala$collection$parallel$SeqSplitter$Zipped$$$outer().psplit(szs).zip(this.that().psplit(szs), Seq$.MODULE$.canBuildFrom())).map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final Zipped<S> apply(Tuple2<SeqSplitter<T>, SeqSplitter<S>> p) {
                    return p._1().zipParSeq(p._2());
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        public /* synthetic */ SeqSplitter scala$collection$parallel$SeqSplitter$Zipped$$$outer() {
            return (SeqSplitter)this.$outer;
        }

        public Zipped(SeqSplitter<T> $outer, SeqSplitter<S> ti) {
            super($outer, ti);
            AugmentedSeqIterator$class.$init$(this);
            SeqSplitter$class.$init$(this);
        }
    }

    public class Patched<U>
    implements SeqSplitter<U> {
        private final int from;
        private final SeqSplitter<U> patch;
        private final int replaced;
        private final Appended<U, SeqSplitter<T>> trio;
        public final /* synthetic */ SeqSplitter $outer;
        private Signalling signalDelegate;

        @Override
        public Seq<SeqSplitter<U>> splitWithSignalling() {
            return SeqSplitter$class.splitWithSignalling(this);
        }

        @Override
        public Seq<SeqSplitter<U>> psplitWithSignalling(Seq<Object> sizes) {
            return SeqSplitter$class.psplitWithSignalling(this, sizes);
        }

        @Override
        public Taken newTaken(int until2) {
            return SeqSplitter$class.newTaken(this, until2);
        }

        @Override
        public SeqSplitter<U> take(int n) {
            return SeqSplitter$class.take(this, n);
        }

        @Override
        public SeqSplitter<U> slice(int from1, int until1) {
            return SeqSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> Mapped<S> map(Function1<U, S> f) {
            return SeqSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends SeqSplitter<U>> Appended<U, PI> appendParSeq(PI that) {
            return SeqSplitter$class.appendParSeq(this, that);
        }

        @Override
        public <S> Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return SeqSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
        }

        @Override
        public SeqSplitter<U> reverse() {
            return SeqSplitter$class.reverse(this);
        }

        @Override
        public <U> Patched<U> patchParSeq(int from2, SeqSplitter<U> patchElems, int replaced) {
            return SeqSplitter$class.patchParSeq(this, from2, patchElems, replaced);
        }

        @Override
        public int prefixLength(Function1<U, Object> pred) {
            return AugmentedSeqIterator$class.prefixLength(this, pred);
        }

        @Override
        public int indexWhere(Function1<U, Object> pred) {
            return AugmentedSeqIterator$class.indexWhere(this, pred);
        }

        @Override
        public int lastIndexWhere(Function1<U, Object> pred) {
            return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
        }

        @Override
        public <S> boolean corresponds(Function2<U, S, Object> corr, Iterator<S> that) {
            return AugmentedSeqIterator$class.corresponds(this, corr, that);
        }

        @Override
        public <U, This> Combiner<U, This> reverse2combiner(Combiner<U, This> cb) {
            return AugmentedSeqIterator$class.reverse2combiner(this, cb);
        }

        @Override
        public <S, That> Combiner<S, That> reverseMap2combiner(Function1<U, S> f, Combiner<S, That> cb) {
            return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
        }

        @Override
        public <U, That> Combiner<U, That> updated2combiner(int index, U elem, Combiner<U, That> cb) {
            return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
        }

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
        public <U extends IterableSplitter.Taken> U newSliceInternal(U it, int from1) {
            return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
        }

        @Override
        public <U, PI extends IterableSplitter<U>> IterableSplitter.Appended<U, PI> appendParIterable(PI that) {
            return IterableSplitter$class.appendParIterable(this, that);
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

        @Override
        public boolean hasNext() {
            return this.trio.hasNext();
        }

        @Override
        public U next() {
            return this.trio.next();
        }

        @Override
        public int remaining() {
            return this.trio.remaining();
        }

        @Override
        public Patched<U> dup() {
            return this.scala$collection$parallel$SeqSplitter$Patched$$$outer().dup().patchParSeq(this.from, this.patch, this.replaced);
        }

        @Override
        public Seq<SeqSplitter<U>> split() {
            return this.trio.split();
        }

        @Override
        public Seq<SeqSplitter<U>> psplit(Seq<Object> sizes) {
            return this.trio.psplit(sizes);
        }

        public /* synthetic */ SeqSplitter scala$collection$parallel$SeqSplitter$Patched$$$outer() {
            return this.$outer;
        }

        public Patched(SeqSplitter<T> $outer, int from2, SeqSplitter<U> patch2, int replaced) {
            this.from = from2;
            this.patch = patch2;
            this.replaced = replaced;
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
            AugmentedSeqIterator$class.$init$(this);
            SeqSplitter$class.$init$(this);
            this.signalDelegate_$eq($outer.signalDelegate());
            Seq pits = $outer.psplit(Predef$.MODULE$.wrapIntArray(new int[]{from2, replaced, $outer.remaining() - from2 - replaced}));
            this.trio = ((SeqSplitter)pits.apply(false)).appendParSeq(patch2).appendParSeq((SeqSplitter)pits.apply(2));
        }
    }

    public class Appended<U, PI extends SeqSplitter<U>>
    extends IterableSplitter.Appended<U, PI>
    implements SeqSplitter<U> {
        @Override
        public Seq<SeqSplitter<U>> splitWithSignalling() {
            return SeqSplitter$class.splitWithSignalling(this);
        }

        @Override
        public Seq<SeqSplitter<U>> psplitWithSignalling(Seq<Object> sizes) {
            return SeqSplitter$class.psplitWithSignalling(this, sizes);
        }

        @Override
        public Taken newTaken(int until2) {
            return SeqSplitter$class.newTaken(this, until2);
        }

        @Override
        public SeqSplitter<U> take(int n) {
            return SeqSplitter$class.take(this, n);
        }

        @Override
        public SeqSplitter<U> slice(int from1, int until1) {
            return SeqSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> Mapped<S> map(Function1<U, S> f) {
            return SeqSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends SeqSplitter<U>> Appended<U, PI> appendParSeq(PI that) {
            return SeqSplitter$class.appendParSeq(this, that);
        }

        @Override
        public <S> Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return SeqSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
        }

        @Override
        public SeqSplitter<U> reverse() {
            return SeqSplitter$class.reverse(this);
        }

        @Override
        public <U> Patched<U> patchParSeq(int from2, SeqSplitter<U> patchElems, int replaced) {
            return SeqSplitter$class.patchParSeq(this, from2, patchElems, replaced);
        }

        @Override
        public int prefixLength(Function1<U, Object> pred) {
            return AugmentedSeqIterator$class.prefixLength(this, pred);
        }

        @Override
        public int indexWhere(Function1<U, Object> pred) {
            return AugmentedSeqIterator$class.indexWhere(this, pred);
        }

        @Override
        public int lastIndexWhere(Function1<U, Object> pred) {
            return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
        }

        @Override
        public <S> boolean corresponds(Function2<U, S, Object> corr, Iterator<S> that) {
            return AugmentedSeqIterator$class.corresponds(this, corr, that);
        }

        @Override
        public <U, This> Combiner<U, This> reverse2combiner(Combiner<U, This> cb) {
            return AugmentedSeqIterator$class.reverse2combiner(this, cb);
        }

        @Override
        public <S, That> Combiner<S, That> reverseMap2combiner(Function1<U, S> f, Combiner<S, That> cb) {
            return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
        }

        @Override
        public <U, That> Combiner<U, That> updated2combiner(int index, U elem, Combiner<U, That> cb) {
            return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
        }

        @Override
        public SeqSplitter<U> dup() {
            return (SeqSplitter)super.dup();
        }

        @Override
        public Seq<SeqSplitter<U>> split() {
            return super.split();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public Seq<SeqSplitter<U>> psplit(Seq<Object> sizes) {
            Seq<SeqSplitter<Object>> seq;
            if (this.firstNonEmpty()) {
                int selfrem = this.scala$collection$parallel$SeqSplitter$Appended$$$outer().remaining();
                BooleanRef appendMiddle = BooleanRef.create(false);
                Seq szcum = sizes.scanLeft(BoxesRunTime.boxToInteger(0), new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final int apply(int x$12, int x$13) {
                        return x$12 + x$13;
                    }

                    public int apply$mcIII$sp(int x$12, int x$13) {
                        return x$12 + x$13;
                    }
                }, Seq$.MODULE$.canBuildFrom());
                Seq splitsizes = ((TraversableLike)sizes.zip(((IterableLike)szcum.init()).zip((GenIterable)szcum.tail(), Seq$.MODULE$.canBuildFrom()), Seq$.MODULE$.canBuildFrom())).flatMap(new Serializable(this, selfrem, appendMiddle){
                    public static final long serialVersionUID = 0L;
                    private final int selfrem$1;
                    private final BooleanRef appendMiddle$1;

                    public final Seq<Object> apply(Tuple2<Object, Tuple2<Object, Object>> t) {
                        if (t != null && t._2() != null) {
                            Seq seq;
                            Tuple3<Integer, Integer, Integer> tuple3 = new Tuple3<Integer, Integer, Integer>(BoxesRunTime.boxToInteger(t._1$mcI$sp()), BoxesRunTime.boxToInteger(t._2()._1$mcI$sp()), BoxesRunTime.boxToInteger(t._2()._2$mcI$sp()));
                            int sz = BoxesRunTime.unboxToInt(tuple3._1());
                            int from2 = BoxesRunTime.unboxToInt(tuple3._2());
                            int until2 = BoxesRunTime.unboxToInt(tuple3._3());
                            if (from2 < this.selfrem$1 && until2 > this.selfrem$1) {
                                this.appendMiddle$1.elem = true;
                                seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapIntArray(new int[]{this.selfrem$1 - from2, until2 - this.selfrem$1}));
                            } else {
                                seq = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapIntArray(new int[]{sz}));
                            }
                            return seq;
                        }
                        throw new MatchError(t);
                    }
                    {
                        void var3_3;
                        this.selfrem$1 = selfrem$1;
                        this.appendMiddle$1 = var3_3;
                    }
                }, Seq$.MODULE$.canBuildFrom());
                Tuple2 tuple2 = ((TraversableLike)splitsizes.zip((GenIterable)szcum.init(), Seq$.MODULE$.canBuildFrom())).span(new Serializable(this, selfrem){
                    public static final long serialVersionUID = 0L;
                    private final int selfrem$1;

                    public final boolean apply(Tuple2<Object, Object> x$15) {
                        return x$15._2$mcI$sp() < this.selfrem$1;
                    }
                    {
                        this.selfrem$1 = selfrem$1;
                    }
                });
                if (tuple2 == null) throw new MatchError(tuple2);
                Tuple2 tuple22 = new Tuple2(tuple2._1(), tuple2._2());
                Seq selfszfrom = (Seq)tuple22._1();
                Seq thatszfrom = (Seq)tuple22._2();
                Tuple2 tuple23 = new Tuple2(selfszfrom.map(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final int apply(Tuple2<Object, Object> x$17) {
                        return x$17._1$mcI$sp();
                    }
                }, Seq$.MODULE$.canBuildFrom()), thatszfrom.map(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final int apply(Tuple2<Object, Object> x$18) {
                        return x$18._1$mcI$sp();
                    }
                }, Seq$.MODULE$.canBuildFrom()));
                Tuple2 tuple24 = new Tuple2(tuple23._1(), tuple23._2());
                Seq<Object> selfsizes = tuple24._1();
                Seq<Object> thatsizes = tuple24._2();
                Seq selfs = this.scala$collection$parallel$SeqSplitter$Appended$$$outer().psplit(selfsizes);
                Seq thats = ((SeqSplitter)this.that()).psplit(thatsizes);
                seq = appendMiddle.elem ? ((TraversableLike)((TraversableLike)selfs.init()).$plus$plus(Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Appended[]{((SeqSplitter)selfs.last()).appendParSeq((SeqSplitter)thats.head())})), Seq$.MODULE$.canBuildFrom())).$plus$plus((GenTraversableOnce)thats.tail(), Seq$.MODULE$.canBuildFrom()) : selfs.$plus$plus(thats, Seq$.MODULE$.canBuildFrom());
                return seq;
            } else {
                seq = ((SeqSplitter)this.curr()).psplit(sizes);
            }
            return seq;
        }

        public /* synthetic */ SeqSplitter scala$collection$parallel$SeqSplitter$Appended$$$outer() {
            return (SeqSplitter)this.$outer;
        }

        public Appended(SeqSplitter<T> $outer, PI it) {
            super($outer, it);
            AugmentedSeqIterator$class.$init$(this);
            SeqSplitter$class.$init$(this);
        }
    }

    public class ZippedAll<U, S>
    extends IterableSplitter.ZippedAll<U, S>
    implements SeqSplitter<Tuple2<U, S>> {
        @Override
        public Seq<SeqSplitter<Tuple2<U, S>>> splitWithSignalling() {
            return SeqSplitter$class.splitWithSignalling(this);
        }

        @Override
        public Seq<SeqSplitter<Tuple2<U, S>>> psplitWithSignalling(Seq<Object> sizes) {
            return SeqSplitter$class.psplitWithSignalling(this, sizes);
        }

        @Override
        public Taken newTaken(int until2) {
            return SeqSplitter$class.newTaken(this, until2);
        }

        @Override
        public SeqSplitter<Tuple2<U, S>> take(int n) {
            return SeqSplitter$class.take(this, n);
        }

        @Override
        public SeqSplitter<Tuple2<U, S>> slice(int from1, int until1) {
            return SeqSplitter$class.slice(this, from1, until1);
        }

        @Override
        public <S> Mapped<S> map(Function1<Tuple2<U, S>, S> f) {
            return SeqSplitter$class.map(this, f);
        }

        @Override
        public <U, PI extends SeqSplitter<U>> Appended<U, PI> appendParSeq(PI that) {
            return SeqSplitter$class.appendParSeq(this, that);
        }

        @Override
        public <S> Zipped<S> zipParSeq(SeqSplitter<S> that) {
            return SeqSplitter$class.zipParSeq(this, that);
        }

        @Override
        public <S, U, R> ZippedAll<U, R> zipAllParSeq(SeqSplitter<S> that, U thisElem, R thatElem) {
            return SeqSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
        }

        @Override
        public SeqSplitter<Tuple2<U, S>> reverse() {
            return SeqSplitter$class.reverse(this);
        }

        @Override
        public <U> Patched<U> patchParSeq(int from2, SeqSplitter<U> patchElems, int replaced) {
            return SeqSplitter$class.patchParSeq(this, from2, patchElems, replaced);
        }

        @Override
        public int prefixLength(Function1<Tuple2<U, S>, Object> pred) {
            return AugmentedSeqIterator$class.prefixLength(this, pred);
        }

        @Override
        public int indexWhere(Function1<Tuple2<U, S>, Object> pred) {
            return AugmentedSeqIterator$class.indexWhere(this, pred);
        }

        @Override
        public int lastIndexWhere(Function1<Tuple2<U, S>, Object> pred) {
            return AugmentedSeqIterator$class.lastIndexWhere(this, pred);
        }

        @Override
        public <S> boolean corresponds(Function2<Tuple2<U, S>, S, Object> corr, Iterator<S> that) {
            return AugmentedSeqIterator$class.corresponds(this, corr, that);
        }

        @Override
        public <U, This> Combiner<U, This> reverse2combiner(Combiner<U, This> cb) {
            return AugmentedSeqIterator$class.reverse2combiner(this, cb);
        }

        @Override
        public <S, That> Combiner<S, That> reverseMap2combiner(Function1<Tuple2<U, S>, S> f, Combiner<S, That> cb) {
            return AugmentedSeqIterator$class.reverseMap2combiner(this, f, cb);
        }

        @Override
        public <U, That> Combiner<U, That> updated2combiner(int index, U elem, Combiner<U, That> cb) {
            return AugmentedSeqIterator$class.updated2combiner(this, index, elem, cb);
        }

        @Override
        public SeqSplitter<Tuple2<U, S>> dup() {
            return (SeqSplitter)super.dup();
        }

        private Tuple2<SeqSplitter<U>, SeqSplitter<S>> patchem() {
            int thatrem;
            int selfrem = this.scala$collection$parallel$SeqSplitter$ZippedAll$$$outer().remaining();
            Appended thisit = selfrem < (thatrem = this.that().remaining()) ? this.scala$collection$parallel$SeqSplitter$ZippedAll$$$outer().appendParSeq(package$.MODULE$.repetition(this.thiselem(), thatrem - selfrem).splitter()) : this.scala$collection$parallel$SeqSplitter$ZippedAll$$$outer();
            SeqSplitter thatit = selfrem > thatrem ? this.that().appendParSeq(package$.MODULE$.repetition(this.thatelem(), selfrem - thatrem).splitter()) : this.that();
            return new Tuple2<SeqSplitter<U>, SeqSplitter<S>>(thisit, thatit);
        }

        @Override
        public Seq<SeqSplitter<Tuple2<U, S>>> split() {
            Tuple2<SeqSplitter<U>, SeqSplitter<S>> tuple2 = this.patchem();
            if (tuple2 != null) {
                Tuple2<SeqSplitter<U>, SeqSplitter<S>> tuple22 = new Tuple2<SeqSplitter<U>, SeqSplitter<S>>(tuple2._1(), tuple2._2());
                SeqSplitter<U> thisit = tuple22._1();
                SeqSplitter<S> thatit = tuple22._2();
                Zipped<S> zipped = thisit.zipParSeq(thatit);
                return zipped.split();
            }
            throw new MatchError(tuple2);
        }

        @Override
        public Seq<SeqSplitter<Tuple2<U, S>>> psplit(Seq<Object> sizes) {
            Tuple2<SeqSplitter<U>, SeqSplitter<S>> tuple2 = this.patchem();
            if (tuple2 != null) {
                Tuple2<SeqSplitter<U>, SeqSplitter<S>> tuple22 = new Tuple2<SeqSplitter<U>, SeqSplitter<S>>(tuple2._1(), tuple2._2());
                SeqSplitter<U> thisit = tuple22._1();
                SeqSplitter<S> thatit = tuple22._2();
                Zipped<S> zipped = thisit.zipParSeq(thatit);
                return zipped.psplit(sizes);
            }
            throw new MatchError(tuple2);
        }

        public /* synthetic */ SeqSplitter scala$collection$parallel$SeqSplitter$ZippedAll$$$outer() {
            return (SeqSplitter)this.$outer;
        }

        public ZippedAll(SeqSplitter<T> $outer, SeqSplitter<S> ti, U thise, S thate) {
            super($outer, ti, thise, thate);
            AugmentedSeqIterator$class.$init$(this);
            SeqSplitter$class.$init$(this);
        }
    }
}

