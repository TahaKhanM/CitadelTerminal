/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Option;
import scala.Predef$;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.AbstractSeq;
import scala.collection.AbstractTraversable;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.GenSeqLike$class;
import scala.collection.GenTraversable;
import scala.collection.Iterable;
import scala.collection.IterableLike;
import scala.collection.Iterator;
import scala.collection.LinearSeqOptimized;
import scala.collection.Seq;
import scala.collection.SeqLike;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.Growable$class;
import scala.collection.generic.IterableForwarder$class;
import scala.collection.generic.SeqForwarder;
import scala.collection.generic.SeqForwarder$class;
import scala.collection.generic.TraversableForwarder$class;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.ListSerializeEnd$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.mutable.AbstractBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.BufferLike$class;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.ListBuffer$;
import scala.collection.mutable.StringBuilder;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.RichInt$;

@ScalaSignature(bytes="\u0006\u0001\t-d\u0001B\u0001\u0003\u0005%\u0011!\u0002T5ti\n+hMZ3s\u0015\t\u0019A!A\u0004nkR\f'\r\\3\u000b\u0005\u00151\u0011AC2pY2,7\r^5p]*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005)\t2\u0003\u0003\u0001\f7y)\u0013FM\u001b\u0011\u00071iq\"D\u0001\u0003\u0013\tq!A\u0001\bBEN$(/Y2u\u0005V4g-\u001a:\u0011\u0005A\tB\u0002\u0001\u0003\u0006%\u0001\u0011\ra\u0005\u0002\u0002\u0003F\u0011A\u0003\u0007\t\u0003+Yi\u0011AB\u0005\u0003/\u0019\u0011qAT8uQ&tw\r\u0005\u0002\u00163%\u0011!D\u0002\u0002\u0004\u0003:L\bc\u0001\u0007\u001d\u001f%\u0011QD\u0001\u0002\u0007\u0005V4g-\u001a:\u0011\t}\u0011s\u0002J\u0007\u0002A)\u0011\u0011\u0005B\u0001\bO\u0016tWM]5d\u0013\t\u0019\u0003E\u0001\u000eHK:,'/[2Ue\u00064XM]:bE2,G+Z7qY\u0006$X\r\u0005\u0002\r\u0001A!ABJ\b)\u0013\t9#A\u0001\u0006Ck\u001a4WM\u001d'jW\u0016\u00042\u0001\u0004\u0001\u0010!\u0011a!f\u0004\u0017\n\u0005-\u0012!a\u0002\"vS2$WM\u001d\t\u0004[AzQ\"\u0001\u0018\u000b\u0005=\"\u0011!C5n[V$\u0018M\u00197f\u0013\t\tdF\u0001\u0003MSN$\bcA\u00104\u001f%\u0011A\u0007\t\u0002\r'\u0016\fhi\u001c:xCJ$WM\u001d\t\u0003mmj\u0011a\u000e\u0006\u0003qe\n!![8\u000b\u0003i\nAA[1wC&\u0011Ah\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\u0006}\u0001!\taP\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003!BQ!\u0011\u0001\u0005B\t\u000b\u0011bY8na\u0006t\u0017n\u001c8\u0016\u0003\r\u00032a\b#%\u0013\t)\u0005E\u0001\tHK:,'/[2D_6\u0004\u0018M\\5p]\"9q\t\u0001a\u0001\n\u0013A\u0015!B:uCJ$X#\u0001\u0017\t\u000f)\u0003\u0001\u0019!C\u0005\u0017\u0006I1\u000f^1si~#S-\u001d\u000b\u0003\u0019>\u0003\"!F'\n\u000593!\u0001B+oSRDq\u0001U%\u0002\u0002\u0003\u0007A&A\u0002yIEBaA\u0015\u0001!B\u0013a\u0013AB:uCJ$\b\u0005C\u0005U\u0001\u0001\u0007\t\u0019!C\u0005+\u0006)A.Y:uaU\ta\u000bE\u0002./>I!\u0001\u0017\u0018\u0003\u0019\u0011\u001aw\u000e\\8oI\r|Gn\u001c8\t\u0013i\u0003\u0001\u0019!a\u0001\n\u0013Y\u0016!\u00037bgR\u0004t\fJ3r)\taE\fC\u0004Q3\u0006\u0005\t\u0019\u0001,\t\ry\u0003\u0001\u0015)\u0003W\u0003\u0019a\u0017m\u001d;1A!9\u0001\r\u0001a\u0001\n\u0013\t\u0017\u0001C3ya>\u0014H/\u001a3\u0016\u0003\t\u0004\"!F2\n\u0005\u00114!a\u0002\"p_2,\u0017M\u001c\u0005\bM\u0002\u0001\r\u0011\"\u0003h\u00031)\u0007\u0010]8si\u0016$w\fJ3r)\ta\u0005\u000eC\u0004QK\u0006\u0005\t\u0019\u00012\t\r)\u0004\u0001\u0015)\u0003c\u0003%)\u0007\u0010]8si\u0016$\u0007\u0005C\u0004m\u0001\u0001\u0007I\u0011B7\u0002\u00071,g.F\u0001o!\t)r.\u0003\u0002q\r\t\u0019\u0011J\u001c;\t\u000fI\u0004\u0001\u0019!C\u0005g\u00069A.\u001a8`I\u0015\fHC\u0001'u\u0011\u001d\u0001\u0016/!AA\u00029DaA\u001e\u0001!B\u0013q\u0017\u0001\u00027f]\u0002BQ\u0001\u001f\u0001\u0005\u0012!\u000b!\"\u001e8eKJd\u00170\u001b8h\u0011\u0015Q\b\u0001\"\u0003|\u0003-9(/\u001b;f\u001f\nTWm\u0019;\u0015\u00051c\b\"B?z\u0001\u0004q\u0018aA8viB\u0011ag`\u0005\u0004\u0003\u00039$AE(cU\u0016\u001cGoT;uaV$8\u000b\u001e:fC6Dq!!\u0002\u0001\t\u0013\t9!\u0001\u0006sK\u0006$wJ\u00196fGR$2\u0001TA\u0005\u0011!\tY!a\u0001A\u0002\u00055\u0011AA5o!\r1\u0014qB\u0005\u0004\u0003#9$!E(cU\u0016\u001cG/\u00138qkR\u001cFO]3b[\"1\u0011Q\u0003\u0001\u0005B5\fa\u0001\\3oORD\u0007BBA\r\u0001\u0011\u0005S.\u0001\u0003tSj,\u0007bBA\u000f\u0001\u0011\u0005\u0013qD\u0001\u0006CB\u0004H.\u001f\u000b\u0004\u001f\u0005\u0005\u0002bBA\u0012\u00037\u0001\rA\\\u0001\u0002]\"9\u0011q\u0005\u0001\u0005\u0002\u0005%\u0012AB;qI\u0006$X\rF\u0003M\u0003W\ti\u0003C\u0004\u0002$\u0005\u0015\u0002\u0019\u00018\t\u000f\u0005=\u0012Q\u0005a\u0001\u001f\u0005\t\u0001\u0010C\u0004\u00024\u0001!\t!!\u000e\u0002\u0011\u0011\u0002H.^:%KF$B!a\u000e\u0002:5\t\u0001\u0001C\u0004\u00020\u0005E\u0002\u0019A\b\t\u000f\u0005u\u0002\u0001\"\u0011\u0002@\u0005iA\u0005\u001d7vg\u0012\u0002H.^:%KF$B!a\u000e\u0002B!A\u00111IA\u001e\u0001\u0004\t)%\u0001\u0002ygB)\u0011qIA%\u001f5\tA!C\u0002\u0002L\u0011\u0011q\u0002\u0016:bm\u0016\u00148/\u00192mK>s7-\u001a\u0005\b\u0003\u001f\u0002A\u0011IA)\u0003M!\u0003\u000f\\;tIAdWo\u001d\u0013fc\u0012\u001aw\u000e\\8o)\u0011\t9$a\u0015\t\u0011\u0005\r\u0013Q\na\u0001\u0003\u000bBq!a\u0016\u0001\t\u0003\tI&A\u0003dY\u0016\f'\u000fF\u0001M\u0011\u001d\ti\u0006\u0001C\u0001\u0003?\na\u0002\n9mkN$S-\u001d\u0013d_2|g\u000e\u0006\u0003\u00028\u0005\u0005\u0004bBA\u0018\u00037\u0002\ra\u0004\u0005\b\u0003K\u0002A\u0011AA4\u0003%Ign]3si\u0006cG\u000eF\u0003M\u0003S\nY\u0007C\u0004\u0002$\u0005\r\u0004\u0019\u00018\t\u0011\u00055\u00141\ra\u0001\u0003_\n1a]3r!\u0015\t9%!\u001d\u0010\u0013\r\t\u0019\b\u0002\u0002\f)J\fg/\u001a:tC\ndW\rC\u0004\u0002x\u0001!I!!\u001f\u0002\u001dI,G-^2f\u0019\u0016tw\r\u001e5CsR\u0019A*a\u001f\t\u000f\u0005u\u0014Q\u000fa\u0001]\u0006\u0019a.^7\t\u000f\u0005\u0005\u0005\u0001\"\u0011\u0002\u0004\u00061!/Z7pm\u0016$R\u0001TAC\u0003\u000fCq!a\t\u0002\u0000\u0001\u0007a\u000eC\u0004\u0002\n\u0006}\u0004\u0019\u00018\u0002\u000b\r|WO\u001c;)\u0011\u0005}\u0014QRAM\u0003;\u0003B!a$\u0002\u00166\u0011\u0011\u0011\u0013\u0006\u0004\u0003'3\u0011AC1o]>$\u0018\r^5p]&!\u0011qSAI\u0005%i\u0017n\u001a:bi&|g.\t\u0002\u0002\u001c\u0006I\u0014J\u001c<bY&$\u0007%\u001b8qkR\u0004c/\u00197vKN\u0004s/\u001b7mA\t,\u0007E]3kK\u000e$X\r\u001a\u0011j]\u00022W\u000f^;sK\u0002\u0012X\r\\3bg\u0016\u001ch&\t\u0002\u0002 \u0006!!GL\u00192\u0011\u001d\t\u0019\u000b\u0001C\u0001\u0003K\u000baA]3tk2$H#\u0001\u0017\t\r\u0005%\u0006\u0001\"\u0011I\u0003\u0019!x\u000eT5ti\"9\u0011Q\u0016\u0001\u0005\u0002\u0005=\u0016!\u00049sKB,g\u000e\u001a+p\u0019&\u001cH\u000fF\u0002-\u0003cCq!a\u0011\u0002,\u0002\u0007A\u0006C\u0004\u0002\u0002\u0002!\t!!.\u0015\u0007=\t9\fC\u0004\u0002$\u0005M\u0006\u0019\u00018\t\u000f\u0005m\u0006\u0001\"\u0011\u0002>\u0006IA%\\5okN$S-\u001d\u000b\u0005\u0003o\ty\fC\u0004\u0002B\u0006e\u0006\u0019A\b\u0002\t\u0015dW-\u001c\u0005\b\u0003\u000b\u0004A\u0011IAd\u0003!IG/\u001a:bi>\u0014XCAAe!\u0015\t9%a3\u0010\u0013\r\ti\r\u0002\u0002\t\u0013R,'/\u0019;pe\"1\u0011\u0011\u001b\u0001\u0005B!\u000b\u0001B]3bI>sG.\u001f\u0015\t\u0003\u001f\f).a7\u0002`B\u0019Q#a6\n\u0007\u0005egA\u0001\u0006eKB\u0014XmY1uK\u0012\f#!!8\u0002CRCW\r\t:fgVdG\u000fI8gAQD\u0017n\u001d\u0011nKRDw\u000e\u001a\u0011xS2d\u0007e\u00195b]\u001e,\u0007%\u00197p]\u001e\u0004s/\u001b;iAQD\u0017n\u001d\u0011ck\u001a4WM\u001d\u0017!o\"L7\r\u001b\u0011jg\u0002zg\r^3oA9|G\u000fI<iCR<3\u000fI3ya\u0016\u001cG/\u001a3/C\t\t\t/\u0001\u00043]E\nd\u0006\r\u0005\b\u0003K\u0004A\u0011BA-\u0003\u0011\u0019w\u000e]=\t\u000f\u0005%\b\u0001\"\u0011\u0002l\u00061Q-];bYN$2AYAw\u0011\u001d\ty/a:A\u0002a\tA\u0001\u001e5bi\"1\u00111\u001f\u0001\u0005B}\nQa\u00197p]\u0016Dq!a>\u0001\t\u0003\nI0\u0001\u0007tiJLgn\u001a)sK\u001aL\u00070\u0006\u0002\u0002|B!\u0011Q B\u0002\u001d\r)\u0012q`\u0005\u0004\u0005\u00031\u0011A\u0002)sK\u0012,g-\u0003\u0003\u0003\u0006\t\u001d!AB*ue&twMC\u0002\u0003\u0002\u0019As\u0001\u0001B\u0006\u0005#\u0011\u0019\u0002E\u0002\u0016\u0005\u001bI1Aa\u0004\u0007\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XM\b\u00050eRx\t;37G\u0010\u001d\u00119B\u0001E\u0001\u00053\t!\u0002T5ti\n+hMZ3s!\ra!1\u0004\u0004\u0007\u0003\tA\tA!\b\u0014\r\tm!q\u0004B\u0013!\u0011y\"\u0011\u0005\u0013\n\u0007\t\r\u0002E\u0001\u0006TKF4\u0015m\u0019;pef\u00042!\u0006B\u0014\u0013\tad\u0001C\u0004?\u00057!\tAa\u000b\u0015\u0005\te\u0001\u0002\u0003B\u0018\u00057!\u0019A!\r\u0002\u0019\r\fgNQ;jY\u00124%o\\7\u0016\t\tM\"QI\u000b\u0003\u0005k\u0001\u0012b\bB\u001c\u0005w\u0011\u0019Ea\u0012\n\u0007\te\u0002E\u0001\u0007DC:\u0014U/\u001b7e\rJ|W\u000e\u0005\u0003\u0003>\t}RB\u0001B\u000e\u0013\r\u0011\t\u0005\u0012\u0002\u0005\u0007>dG\u000eE\u0002\u0011\u0005\u000b\"aA\u0005B\u0017\u0005\u0004\u0019\u0002\u0003\u0002\u0007\u0001\u0005\u0007B\u0001Ba\u0013\u0003\u001c\u0011\u0005!QJ\u0001\u000b]\u0016<()^5mI\u0016\u0014X\u0003\u0002B(\u0005+*\"A!\u0015\u0011\r1Q#1\u000bB,!\r\u0001\"Q\u000b\u0003\u0007%\t%#\u0019A\n\u0011\t1\u0001!1\u000b\u0005\u000b\u00057\u0012Y\"!A\u0005\n\tu\u0013a\u0003:fC\u0012\u0014Vm]8mm\u0016$\"Aa\u0018\u0011\t\t\u0005$qM\u0007\u0003\u0005GR1A!\u001a:\u0003\u0011a\u0017M\\4\n\t\t%$1\r\u0002\u0007\u001f\nTWm\u0019;")
public final class ListBuffer<A>
extends AbstractBuffer<A>
implements Builder<A, List<A>>,
SeqForwarder<A>,
Serializable {
    public static final long serialVersionUID = 3419063961353022662L;
    private List<A> scala$collection$mutable$ListBuffer$$start;
    private $colon$colon<A> last0;
    private boolean exported;
    private int len;

    public static <A> CanBuildFrom<ListBuffer<?>, A, ListBuffer<A>> canBuildFrom() {
        return ListBuffer$.MODULE$.canBuildFrom();
    }

    public static Some unapplySeq(Seq seq) {
        return ListBuffer$.MODULE$.unapplySeq(seq);
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return ListBuffer$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return ListBuffer$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return ListBuffer$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return ListBuffer$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return ListBuffer$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return ListBuffer$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return ListBuffer$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return ListBuffer$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return ListBuffer$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return ListBuffer$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return ListBuffer$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return ListBuffer$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return ListBuffer$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return ListBuffer$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return ListBuffer$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return ListBuffer$.MODULE$.empty();
    }

    @Override
    public int lengthCompare(int len) {
        return SeqForwarder$class.lengthCompare(this, len);
    }

    @Override
    public boolean isDefinedAt(int x) {
        return SeqForwarder$class.isDefinedAt(this, x);
    }

    @Override
    public int segmentLength(Function1<A, Object> p, int from2) {
        return SeqForwarder$class.segmentLength(this, p, from2);
    }

    @Override
    public int prefixLength(Function1<A, Object> p) {
        return SeqForwarder$class.prefixLength(this, p);
    }

    @Override
    public int indexWhere(Function1<A, Object> p) {
        return SeqForwarder$class.indexWhere(this, p);
    }

    @Override
    public int indexWhere(Function1<A, Object> p, int from2) {
        return SeqForwarder$class.indexWhere(this, p, from2);
    }

    @Override
    public <B> int indexOf(B elem) {
        return SeqForwarder$class.indexOf(this, elem);
    }

    @Override
    public <B> int indexOf(B elem, int from2) {
        return SeqForwarder$class.indexOf(this, elem, from2);
    }

    @Override
    public <B> int lastIndexOf(B elem) {
        return SeqForwarder$class.lastIndexOf(this, elem);
    }

    @Override
    public <B> int lastIndexOf(B elem, int end) {
        return SeqForwarder$class.lastIndexOf(this, elem, end);
    }

    @Override
    public int lastIndexWhere(Function1<A, Object> p) {
        return SeqForwarder$class.lastIndexWhere(this, p);
    }

    @Override
    public int lastIndexWhere(Function1<A, Object> p, int end) {
        return SeqForwarder$class.lastIndexWhere(this, p, end);
    }

    @Override
    public Iterator<A> reverseIterator() {
        return SeqForwarder$class.reverseIterator(this);
    }

    @Override
    public <B> boolean startsWith(GenSeq<B> that, int offset) {
        return SeqForwarder$class.startsWith(this, that, offset);
    }

    @Override
    public <B> boolean startsWith(GenSeq<B> that) {
        return SeqForwarder$class.startsWith(this, that);
    }

    @Override
    public <B> boolean endsWith(GenSeq<B> that) {
        return SeqForwarder$class.endsWith(this, that);
    }

    @Override
    public <B> int indexOfSlice(GenSeq<B> that) {
        return SeqForwarder$class.indexOfSlice(this, that);
    }

    @Override
    public <B> int indexOfSlice(GenSeq<B> that, int from2) {
        return SeqForwarder$class.indexOfSlice(this, that, from2);
    }

    @Override
    public <B> int lastIndexOfSlice(GenSeq<B> that) {
        return SeqForwarder$class.lastIndexOfSlice(this, that);
    }

    @Override
    public <B> int lastIndexOfSlice(GenSeq<B> that, int end) {
        return SeqForwarder$class.lastIndexOfSlice(this, that, end);
    }

    @Override
    public <B> boolean containsSlice(GenSeq<B> that) {
        return SeqForwarder$class.containsSlice(this, that);
    }

    @Override
    public <A1> boolean contains(A1 elem) {
        return SeqForwarder$class.contains(this, elem);
    }

    @Override
    public <B> boolean corresponds(GenSeq<B> that, Function2<A, B, Object> p) {
        return SeqForwarder$class.corresponds(this, that, p);
    }

    @Override
    public Range indices() {
        return SeqForwarder$class.indices(this);
    }

    @Override
    public <B> boolean sameElements(GenIterable<B> that) {
        return IterableForwarder$class.sameElements(this, that);
    }

    @Override
    public <U> void foreach(Function1<A, U> f) {
        TraversableForwarder$class.foreach(this, f);
    }

    @Override
    public boolean isEmpty() {
        return TraversableForwarder$class.isEmpty(this);
    }

    @Override
    public boolean nonEmpty() {
        return TraversableForwarder$class.nonEmpty(this);
    }

    @Override
    public boolean hasDefiniteSize() {
        return TraversableForwarder$class.hasDefiniteSize(this);
    }

    @Override
    public boolean forall(Function1<A, Object> p) {
        return TraversableForwarder$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<A, Object> p) {
        return TraversableForwarder$class.exists(this, p);
    }

    @Override
    public int count(Function1<A, Object> p) {
        return TraversableForwarder$class.count(this, p);
    }

    @Override
    public Option<A> find(Function1<A, Object> p) {
        return TraversableForwarder$class.find(this, p);
    }

    @Override
    public <B> B foldLeft(B z, Function2<B, A, B> op) {
        return (B)TraversableForwarder$class.foldLeft(this, z, op);
    }

    @Override
    public <B> B $div$colon(B z, Function2<B, A, B> op) {
        return (B)TraversableForwarder$class.$div$colon(this, z, op);
    }

    @Override
    public <B> B foldRight(B z, Function2<A, B, B> op) {
        return (B)TraversableForwarder$class.foldRight(this, z, op);
    }

    @Override
    public <B> B $colon$bslash(B z, Function2<A, B, B> op) {
        return (B)TraversableForwarder$class.$colon$bslash(this, z, op);
    }

    @Override
    public <B> B reduceLeft(Function2<B, A, B> op) {
        return (B)TraversableForwarder$class.reduceLeft(this, op);
    }

    @Override
    public <B> Option<B> reduceLeftOption(Function2<B, A, B> op) {
        return TraversableForwarder$class.reduceLeftOption(this, op);
    }

    @Override
    public <B> B reduceRight(Function2<A, B, B> op) {
        return (B)TraversableForwarder$class.reduceRight(this, op);
    }

    @Override
    public <B> Option<B> reduceRightOption(Function2<A, B, B> op) {
        return TraversableForwarder$class.reduceRightOption(this, op);
    }

    @Override
    public <B> B sum(Numeric<B> num) {
        return (B)TraversableForwarder$class.sum(this, num);
    }

    @Override
    public <B> B product(Numeric<B> num) {
        return (B)TraversableForwarder$class.product(this, num);
    }

    @Override
    public <B> A min(Ordering<B> cmp) {
        return (A)TraversableForwarder$class.min(this, cmp);
    }

    @Override
    public <B> A max(Ordering<B> cmp) {
        return (A)TraversableForwarder$class.max(this, cmp);
    }

    @Override
    public A head() {
        return (A)TraversableForwarder$class.head(this);
    }

    @Override
    public Option<A> headOption() {
        return TraversableForwarder$class.headOption(this);
    }

    @Override
    public A last() {
        return (A)TraversableForwarder$class.last(this);
    }

    @Override
    public Option<A> lastOption() {
        return TraversableForwarder$class.lastOption(this);
    }

    @Override
    public <B> void copyToBuffer(Buffer<B> dest) {
        TraversableForwarder$class.copyToBuffer(this, dest);
    }

    @Override
    public <B> void copyToArray(Object xs, int start, int len) {
        TraversableForwarder$class.copyToArray(this, xs, start, len);
    }

    @Override
    public <B> void copyToArray(Object xs, int start) {
        TraversableForwarder$class.copyToArray(this, xs, start);
    }

    @Override
    public <B> void copyToArray(Object xs) {
        TraversableForwarder$class.copyToArray(this, xs);
    }

    @Override
    public <B> Object toArray(ClassTag<B> evidence$1) {
        return TraversableForwarder$class.toArray(this, evidence$1);
    }

    @Override
    public Iterable<A> toIterable() {
        return TraversableForwarder$class.toIterable(this);
    }

    @Override
    public Seq<A> toSeq() {
        return TraversableForwarder$class.toSeq(this);
    }

    @Override
    public IndexedSeq<A> toIndexedSeq() {
        return TraversableForwarder$class.toIndexedSeq(this);
    }

    @Override
    public <B> Buffer<B> toBuffer() {
        return TraversableForwarder$class.toBuffer(this);
    }

    @Override
    public Stream<A> toStream() {
        return TraversableForwarder$class.toStream(this);
    }

    @Override
    public <B> Set<B> toSet() {
        return TraversableForwarder$class.toSet(this);
    }

    @Override
    public <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> ev) {
        return TraversableForwarder$class.toMap(this, ev);
    }

    @Override
    public String mkString(String start, String sep, String end) {
        return TraversableForwarder$class.mkString(this, start, sep, end);
    }

    @Override
    public String mkString(String sep) {
        return TraversableForwarder$class.mkString(this, sep);
    }

    @Override
    public String mkString() {
        return TraversableForwarder$class.mkString(this);
    }

    @Override
    public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
        return TraversableForwarder$class.addString(this, b, start, sep, end);
    }

    @Override
    public StringBuilder addString(StringBuilder b, String sep) {
        return TraversableForwarder$class.addString(this, b, sep);
    }

    @Override
    public StringBuilder addString(StringBuilder b) {
        return TraversableForwarder$class.addString(this, b);
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
    public <NewTo> Builder<A, NewTo> mapResult(Function1<List<A>, NewTo> f) {
        return Builder$class.mapResult(this, f);
    }

    @Override
    public GenericCompanion<ListBuffer> companion() {
        return ListBuffer$.MODULE$;
    }

    public List<A> scala$collection$mutable$ListBuffer$$start() {
        return this.scala$collection$mutable$ListBuffer$$start;
    }

    private void scala$collection$mutable$ListBuffer$$start_$eq(List<A> x$1) {
        this.scala$collection$mutable$ListBuffer$$start = x$1;
    }

    private $colon$colon<A> last0() {
        return this.last0;
    }

    private void last0_$eq($colon$colon<A> x$1) {
        this.last0 = x$1;
    }

    private boolean exported() {
        return this.exported;
    }

    private void exported_$eq(boolean x$1) {
        this.exported = x$1;
    }

    private int len() {
        return this.len;
    }

    private void len_$eq(int x$1) {
        this.len = x$1;
    }

    @Override
    public List<A> underlying() {
        return this.scala$collection$mutable$ListBuffer$$start();
    }

    private void writeObject(ObjectOutputStream out) {
        List<A> xs = this.scala$collection$mutable$ListBuffer$$start();
        while (true) {
            List list2;
            if (xs.isEmpty()) {
                out.writeObject(ListSerializeEnd$.MODULE$);
                out.writeBoolean(this.exported());
                out.writeInt(this.len());
                return;
            }
            out.writeObject(list2.head());
            list2 = (List)list2.tail();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void readObject(ObjectInputStream in) {
        Serializable serializable;
        Object elem;
        Object object = elem = in.readObject();
        ListSerializeEnd$ listSerializeEnd$ = ListSerializeEnd$.MODULE$;
        if (object != null && object.equals(listSerializeEnd$)) {
            this.scala$collection$mutable$ListBuffer$$start_$eq(Nil$.MODULE$);
            this.last0_$eq(null);
            serializable = BoxedUnit.UNIT;
        } else {
            $colon$colon<Nothing$> current = new $colon$colon<Nothing$>((Nothing$)elem, Nil$.MODULE$);
            this.scala$collection$mutable$ListBuffer$$start_$eq(current);
            elem = in.readObject();
            while (true) {
                $colon$colon<Nothing$> $colon$colon;
                Object object2;
                Object object3 = elem;
                ListSerializeEnd$ listSerializeEnd$2 = ListSerializeEnd$.MODULE$;
                if (object3 != null && object3.equals(listSerializeEnd$2)) {
                    this.last0_$eq(current);
                    serializable = this.scala$collection$mutable$ListBuffer$$start();
                    break;
                }
                $colon$colon<Nothing$> list2 = new $colon$colon<Nothing$>((Nothing$)object2, Nil$.MODULE$);
                $colon$colon.tl_$eq(list2);
                $colon$colon = list2;
                object2 = in.readObject();
            }
        }
        this.exported_$eq(in.readBoolean());
        this.len_$eq(in.readInt());
    }

    @Override
    public int length() {
        return this.len();
    }

    @Override
    public int size() {
        return this.length();
    }

    @Override
    public A apply(int n) {
        if (n < 0 || n >= this.len()) {
            throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
        }
        return (A)SeqForwarder$class.apply(this, n);
    }

    @Override
    public void update(int n, A x) {
        if (n < 0 || n >= this.len()) {
            throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
        }
        if (this.exported()) {
            this.copy();
        }
        if (n == 0) {
            $colon$colon<A> newElem = new $colon$colon<A>(x, (List)this.scala$collection$mutable$ListBuffer$$start().tail());
            if (this.last0() == this.scala$collection$mutable$ListBuffer$$start()) {
                this.last0_$eq(newElem);
            }
            this.scala$collection$mutable$ListBuffer$$start_$eq(newElem);
        } else {
            List cursor = this.scala$collection$mutable$ListBuffer$$start();
            for (int i = 1; i < n; ++i) {
                cursor = (List)cursor.tail();
            }
            $colon$colon<A> newElem = new $colon$colon<A>(x, (List)((TraversableLike)cursor.tail()).tail());
            if (this.last0() == cursor.tail()) {
                this.last0_$eq(newElem);
            }
            (($colon$colon)cursor).tl_$eq(newElem);
        }
    }

    @Override
    public ListBuffer<A> $plus$eq(A x) {
        if (this.exported()) {
            this.copy();
        }
        if (this.isEmpty()) {
            this.last0_$eq(new $colon$colon<Nothing$>((Nothing$)x, Nil$.MODULE$));
            this.scala$collection$mutable$ListBuffer$$start_$eq(this.last0());
        } else {
            $colon$colon<A> last1 = this.last0();
            this.last0_$eq(new $colon$colon<Nothing$>((Nothing$)x, Nil$.MODULE$));
            last1.tl_$eq(this.last0());
        }
        this.len_$eq(this.len() + 1);
        return this;
    }

    @Override
    public ListBuffer<A> $plus$plus$eq(TraversableOnce<A> xs) {
        while (xs instanceof Object && xs == this) {
            xs = (TraversableOnce)this.take(this.size());
        }
        return (ListBuffer)Growable$class.$plus$plus$eq(this, xs);
    }

    @Override
    public ListBuffer<A> $plus$plus$eq$colon(TraversableOnce<A> xs) {
        while (xs == this) {
            xs = (TraversableOnce)this.take(this.size());
        }
        return (ListBuffer)BufferLike$class.$plus$plus$eq$colon(this, xs);
    }

    @Override
    public void clear() {
        this.scala$collection$mutable$ListBuffer$$start_$eq(Nil$.MODULE$);
        this.last0_$eq(null);
        this.exported_$eq(false);
        this.len_$eq(0);
    }

    @Override
    public ListBuffer<A> $plus$eq$colon(A x) {
        if (this.exported()) {
            this.copy();
        }
        $colon$colon<A> newElem = new $colon$colon<A>(x, this.scala$collection$mutable$ListBuffer$$start());
        if (this.isEmpty()) {
            this.last0_$eq(newElem);
        }
        this.scala$collection$mutable$ListBuffer$$start_$eq(newElem);
        this.len_$eq(this.len() + 1);
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void insertAll(int n, Traversable<A> seq) {
        if (n < 0 || n > this.len()) {
            throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
        }
        if (this.exported()) {
            this.copy();
        }
        Object elems = seq.toList().reverse();
        this.len_$eq(this.len() + ((List)elems).length());
        if (n == 0) {
            while (!((List)elems).isEmpty()) {
                $colon$colon newElem = new $colon$colon(((List)elems).head(), this.scala$collection$mutable$ListBuffer$$start());
                if (this.scala$collection$mutable$ListBuffer$$start().isEmpty()) {
                    this.last0_$eq(newElem);
                }
                this.scala$collection$mutable$ListBuffer$$start_$eq(newElem);
                elems = (List)((AbstractTraversable)elems).tail();
            }
            return;
        } else {
            List cursor = this.scala$collection$mutable$ListBuffer$$start();
            for (int i = 1; i < n; cursor = (List)cursor.tail(), ++i) {
            }
            while (!((List)elems).isEmpty()) {
                $colon$colon newElem = new $colon$colon(((List)elems).head(), (List)cursor.tail());
                if (((SeqLike)cursor.tail()).isEmpty()) {
                    this.last0_$eq(newElem);
                }
                (($colon$colon)cursor).tl_$eq(newElem);
                elems = (List)((AbstractTraversable)elems).tail();
            }
        }
    }

    private void reduceLengthBy(int num) {
        this.len_$eq(this.len() - num);
        if (this.len() <= 0) {
            this.last0_$eq(null);
        }
    }

    @Override
    public void remove(int n, int count2) {
        if (n >= this.len()) {
            return;
        }
        if (count2 < 0) {
            throw new IllegalArgumentException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"removing negative number (", ") of elements"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(count2)})));
        }
        if (this.exported()) {
            this.copy();
        }
        Predef$ predef$ = Predef$.MODULE$;
        int n1 = RichInt$.MODULE$.max$extension(n, 0);
        Predef$ predef$2 = Predef$.MODULE$;
        int count1 = RichInt$.MODULE$.min$extension(count2, this.len() - n1);
        if (n1 == 0) {
            for (int c = count1; c > 0; --c) {
                this.scala$collection$mutable$ListBuffer$$start_$eq((List)this.scala$collection$mutable$ListBuffer$$start().tail());
            }
        } else {
            List cursor = this.scala$collection$mutable$ListBuffer$$start();
            for (int i = 1; i < n1; ++i) {
                cursor = (List)cursor.tail();
            }
            for (int c = count1; c > 0; --c) {
                if (this.last0() == cursor.tail()) {
                    this.last0_$eq(($colon$colon)cursor);
                }
                (($colon$colon)cursor).tl_$eq((List)((TraversableLike)cursor.tail()).tail());
            }
        }
        this.reduceLengthBy(count1);
    }

    @Override
    public List<A> result() {
        return this.toList();
    }

    @Override
    public List<A> toList() {
        this.exported_$eq(!this.isEmpty());
        return this.scala$collection$mutable$ListBuffer$$start();
    }

    public List<A> prependToList(List<A> xs) {
        List<A> list2;
        if (this.isEmpty()) {
            list2 = xs;
        } else {
            if (this.exported()) {
                this.copy();
            }
            this.last0().tl_$eq(xs);
            list2 = this.toList();
        }
        return list2;
    }

    @Override
    public A remove(int n) {
        if (n < 0 || n >= this.len()) {
            throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
        }
        if (this.exported()) {
            this.copy();
        }
        A old = this.scala$collection$mutable$ListBuffer$$start().head();
        if (n == 0) {
            this.scala$collection$mutable$ListBuffer$$start_$eq((List)this.scala$collection$mutable$ListBuffer$$start().tail());
        } else {
            List cursor = this.scala$collection$mutable$ListBuffer$$start();
            for (int i = 1; i < n; ++i) {
                cursor = (List)cursor.tail();
            }
            old = ((IterableLike)cursor.tail()).head();
            if (this.last0() == cursor.tail()) {
                this.last0_$eq(($colon$colon)cursor);
            }
            (($colon$colon)cursor).tl_$eq((List)((TraversableLike)cursor.tail()).tail());
        }
        this.reduceLengthBy(1);
        return old;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public ListBuffer<A> $minus$eq(A elem) {
        if (this.exported()) {
            this.copy();
        }
        if (this.isEmpty()) return this;
        A a = this.scala$collection$mutable$ListBuffer$$start().head();
        if (a == elem ? true : (a == null ? false : (a instanceof Number ? BoxesRunTime.equalsNumObject((Number)a, elem) : (a instanceof Character ? BoxesRunTime.equalsCharObject((Character)a, elem) : a.equals(elem))))) {
            this.scala$collection$mutable$ListBuffer$$start_$eq((List)this.scala$collection$mutable$ListBuffer$$start().tail());
            this.reduceLengthBy(1);
            return this;
        }
        List cursor = this.scala$collection$mutable$ListBuffer$$start();
        while (true) {
            Object a2;
            if (((SeqLike)cursor.tail()).isEmpty() || ((a2 = ((IterableLike)cursor.tail()).head()) == elem ? true : (a2 == null ? false : (a2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)a2, elem) : (a2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)a2, elem) : a2.equals(elem)))))) {
                if (((SeqLike)cursor.tail()).isEmpty()) return this;
                $colon$colon z = ($colon$colon)cursor;
                List list2 = z.tl();
                $colon$colon<A> $colon$colon = this.last0();
                if (!(list2 != null ? !((Object)list2).equals($colon$colon) : $colon$colon != null)) {
                    this.last0_$eq(z);
                }
                z.tl_$eq((List)((TraversableLike)cursor.tail()).tail());
                this.reduceLengthBy(1);
                return this;
            }
            cursor = (List)cursor.tail();
        }
    }

    @Override
    public Iterator<A> iterator() {
        return new AbstractIterator<A>(this){
            private List<A> cursor;

            private List<A> cursor() {
                return this.cursor;
            }

            private void cursor_$eq(List<A> x$1) {
                this.cursor = x$1;
            }

            public boolean hasNext() {
                return this.cursor() != Nil$.MODULE$;
            }

            /*
             * WARNING - void declaration
             */
            public A next() {
                if (this.hasNext()) {
                    void var1_1;
                    A ans = this.cursor().head();
                    this.cursor_$eq((List)this.cursor().tail());
                    return var1_1;
                }
                throw new NoSuchElementException("next on empty Iterator");
            }
            {
                this.cursor = $outer.isEmpty() ? Nil$.MODULE$ : $outer.scala$collection$mutable$ListBuffer$$start();
            }
        };
    }

    @Override
    public List<A> readOnly() {
        return this.scala$collection$mutable$ListBuffer$$start();
    }

    private void copy() {
        if (this.isEmpty()) {
            return;
        }
        LinearSeqOptimized limit = this.last0().tail();
        this.clear();
        for (List cursor = this.scala$collection$mutable$ListBuffer$$start(); cursor != limit; cursor = (List)cursor.tail()) {
            this.$plus$eq((Object)cursor.head());
        }
    }

    @Override
    public boolean equals(Object that) {
        boolean bl;
        if (that instanceof ListBuffer) {
            ListBuffer listBuffer = (ListBuffer)that;
            bl = ((AbstractSeq)this.readOnly()).equals(listBuffer.readOnly());
        } else {
            bl = GenSeqLike$class.equals(this, that);
        }
        return bl;
    }

    @Override
    public ListBuffer<A> clone() {
        return new ListBuffer<A>().$plus$plus$eq((TraversableOnce)this);
    }

    @Override
    public String stringPrefix() {
        return "ListBuffer";
    }

    public ListBuffer() {
        Builder$class.$init$(this);
        TraversableForwarder$class.$init$(this);
        IterableForwarder$class.$init$(this);
        SeqForwarder$class.$init$(this);
        this.scala$collection$mutable$ListBuffer$$start = Nil$.MODULE$;
        this.exported = false;
        this.len = 0;
    }
}

