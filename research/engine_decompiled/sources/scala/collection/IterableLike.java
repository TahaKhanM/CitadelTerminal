/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Equals;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Tuple2;
import scala.collection.GenIterable;
import scala.collection.GenIterableLike;
import scala.collection.Iterable;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.TraversableLike;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.Stream;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\t\u0015c!C\u0001\u0003!\u0003\r\ta\u0002B\"\u00051IE/\u001a:bE2,G*[6f\u0015\t\u0019A!\u0001\u0006d_2dWm\u0019;j_:T\u0011!B\u0001\u0006g\u000e\fG.Y\u0002\u0001+\rAa#H\n\u0006\u0001%i\u0001c\b\t\u0003\u0015-i\u0011\u0001B\u0005\u0003\u0019\u0011\u00111!\u00118z!\tQa\"\u0003\u0002\u0010\t\t1Q)];bYN\u0004B!\u0005\n\u001595\t!!\u0003\u0002\u0014\u0005\tyAK]1wKJ\u001c\u0018M\u00197f\u0019&\\W\r\u0005\u0002\u0016-1\u0001AAB\f\u0001\t\u000b\u0007\u0001DA\u0001B#\tI\u0012\u0002\u0005\u0002\u000b5%\u00111\u0004\u0002\u0002\b\u001d>$\b.\u001b8h!\t)R\u0004\u0002\u0004\u001f\u0001\u0011\u0015\r\u0001\u0007\u0002\u0005%\u0016\u0004(\u000f\u0005\u0003\u0012AQa\u0012BA\u0011\u0003\u0005=9UM\\%uKJ\f'\r\\3MS.,\u0007\"B\u0012\u0001\t\u0003!\u0013A\u0002\u0013j]&$H\u0005F\u0001&!\tQa%\u0003\u0002(\t\t!QK\\5u\u0011\u0019I\u0003\u0001)C)U\u0005qA\u000f[5t\u0007>dG.Z2uS>tW#A\u0016\u0011\u0007EaC#\u0003\u0002.\u0005\tA\u0011\n^3sC\ndW\r\u0003\u00040\u0001\u0001&\t\u0006M\u0001\ri>\u001cu\u000e\u001c7fGRLwN\u001c\u000b\u0003WEBQA\r\u0018A\u0002q\tAA]3qe\")A\u0007\u0001D\u0001k\u0005A\u0011\u000e^3sCR|'/F\u00017!\r\tr\u0007F\u0005\u0003q\t\u0011\u0001\"\u0013;fe\u0006$xN\u001d\u0005\u0006u\u0001!\taO\u0001\bM>\u0014X-Y2i+\ta4\t\u0006\u0002&{!)a(\u000fa\u0001\u007f\u0005\ta\r\u0005\u0003\u000b\u0001R\u0011\u0015BA!\u0005\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002\u0016\u0007\u0012)A)\u000fb\u00011\t\tQ\u000bC\u0003G\u0001\u0011\u0005s)\u0001\u0004g_J\fG\u000e\u001c\u000b\u0003\u0011.\u0003\"AC%\n\u0005)#!a\u0002\"p_2,\u0017M\u001c\u0005\u0006\u0019\u0016\u0003\r!T\u0001\u0002aB!!\u0002\u0011\u000bI\u0011\u0015y\u0005\u0001\"\u0011Q\u0003\u0019)\u00070[:ugR\u0011\u0001*\u0015\u0005\u0006\u0019:\u0003\r!\u0014\u0005\u0006'\u0002!\t\u0005V\u0001\u0005M&tG\r\u0006\u0002V1B\u0019!B\u0016\u000b\n\u0005]#!AB(qi&|g\u000eC\u0003M%\u0002\u0007Q\nC\u0003[\u0001\u0011\u00053,A\u0004jg\u0016k\u0007\u000f^=\u0016\u0003!CQ!\u0018\u0001\u0005By\u000b\u0011BZ8mIJKw\r\u001b;\u0016\u0005}\u0013GC\u00011j)\t\tG\r\u0005\u0002\u0016E\u0012)1\r\u0018b\u00011\t\t!\tC\u0003f9\u0002\u0007a-\u0001\u0002paB)!b\u001a\u000bbC&\u0011\u0001\u000e\u0002\u0002\n\rVt7\r^5p]JBQA\u001b/A\u0002\u0005\f\u0011A\u001f\u0005\u0006Y\u0002!\t%\\\u0001\fe\u0016$WoY3SS\u001eDG/\u0006\u0002oaR\u0011qN\u001d\t\u0003+A$QaY6C\u0002E\f\"\u0001F\u0005\t\u000b\u0015\\\u0007\u0019A:\u0011\u000b)9Gc\\8\t\u000bU\u0004A\u0011\t\u0016\u0002\u0015Q|\u0017\n^3sC\ndW\rC\u0003x\u0001\u0011\u0005S'\u0001\u0006u_&#XM]1u_JDCA^=}}B\u0011!B_\u0005\u0003w\u0012\u0011A\u0003Z3qe\u0016\u001c\u0017\r^3e\u001fZ,'O]5eS:<\u0017%A?\u0002;R|\u0017\n^3sCR|'\u000fI:i_VdG\rI:uCf\u00043m\u001c8tSN$XM\u001c;!o&$\b\u000eI5uKJ\fGo\u001c:!M>\u0014\b%\u00197mA%#XM]1cY\u0016\u001c(\bI8wKJ\u0014\u0018\u000eZ3!SR,'/\u0019;pe\u0002Jgn\u001d;fC\u0012t\u0013%A@\u0002\rIr\u0013'\r\u00181\u0011\u001d\t\u0019\u0001\u0001C!\u0003\u000b\tA\u0001[3bIV\tA\u0003C\u0004\u0002\n\u0001!\t%a\u0003\u0002\u000bMd\u0017nY3\u0015\u000bq\ti!a\u0006\t\u0011\u0005=\u0011q\u0001a\u0001\u0003#\tAA\u001a:p[B\u0019!\"a\u0005\n\u0007\u0005UAAA\u0002J]RD\u0001\"!\u0007\u0002\b\u0001\u0007\u0011\u0011C\u0001\u0006k:$\u0018\u000e\u001c\u0005\b\u0003;\u0001A\u0011IA\u0010\u0003\u0011!\u0018m[3\u0015\u0007q\t\t\u0003\u0003\u0005\u0002$\u0005m\u0001\u0019AA\t\u0003\u0005q\u0007bBA\u0014\u0001\u0011\u0005\u0013\u0011F\u0001\u0005IJ|\u0007\u000fF\u0002\u001d\u0003WA\u0001\"a\t\u0002&\u0001\u0007\u0011\u0011\u0003\u0005\b\u0003_\u0001A\u0011IA\u0019\u0003%!\u0018m[3XQ&dW\rF\u0002\u001d\u0003gAa\u0001TA\u0017\u0001\u0004i\u0005bBA\u001c\u0001\u0011\u0005\u0011\u0011H\u0001\bOJ|W\u000f]3e)\u0011\tY$!\u0010\u0011\u0007E9D\u0004\u0003\u0005\u0002@\u0005U\u0002\u0019AA\t\u0003\u0011\u0019\u0018N_3\t\u000f\u0005\r\u0003\u0001\"\u0001\u0002F\u000591\u000f\\5eS:<G\u0003BA\u001e\u0003\u000fB\u0001\"a\u0010\u0002B\u0001\u0007\u0011\u0011\u0003\u0005\b\u0003\u0007\u0002A\u0011AA&)\u0019\tY$!\u0014\u0002P!A\u0011qHA%\u0001\u0004\t\t\u0002\u0003\u0005\u0002R\u0005%\u0003\u0019AA\t\u0003\u0011\u0019H/\u001a9\t\u000f\u0005U\u0003\u0001\"\u0001\u0002X\u0005IA/Y6f%&<\u0007\u000e\u001e\u000b\u00049\u0005e\u0003\u0002CA\u0012\u0003'\u0002\r!!\u0005\t\u000f\u0005u\u0003\u0001\"\u0001\u0002`\u0005IAM]8q%&<\u0007\u000e\u001e\u000b\u00049\u0005\u0005\u0004\u0002CA\u0012\u00037\u0002\r!!\u0005\t\u000f\u0005\u0015\u0004\u0001\"\u0011\u0002h\u0005Y1m\u001c9z)>\f%O]1z+\u0011\tI'a\u001e\u0015\u000f\u0015\nY'!\u001f\u0002~!A\u0011QNA2\u0001\u0004\ty'\u0001\u0002ygB)!\"!\u001d\u0002v%\u0019\u00111\u000f\u0003\u0003\u000b\u0005\u0013(/Y=\u0011\u0007U\t9\b\u0002\u0004d\u0003G\u0012\r!\u001d\u0005\t\u0003w\n\u0019\u00071\u0001\u0002\u0012\u0005)1\u000f^1si\"A\u0011qPA2\u0001\u0004\t\t\"A\u0002mK:Dq!a!\u0001\t\u0003\t))A\u0002{SB,\u0002\"a\"\u0002*\u0006=\u0016Q\u0012\u000b\u0005\u0003\u0013\u000b\t\f\u0006\u0003\u0002\f\u0006E\u0005cA\u000b\u0002\u000e\u00129\u0011qRAA\u0005\u0004A\"\u0001\u0002+iCRD\u0001\"a%\u0002\u0002\u0002\u000f\u0011QS\u0001\u0003E\u001a\u0004\u0012\"a&\u0002\u001er\t\t+a#\u000e\u0005\u0005e%bAAN\u0005\u00059q-\u001a8fe&\u001c\u0017\u0002BAP\u00033\u0013AbQ1o\u0005VLG\u000e\u001a$s_6\u0004rACAR\u0003O\u000bi+C\u0002\u0002&\u0012\u0011a\u0001V;qY\u0016\u0014\u0004cA\u000b\u0002*\u00129\u00111VAA\u0005\u0004\t(AA!2!\r)\u0012q\u0016\u0003\u0007G\u0006\u0005%\u0019\u0001\r\t\u0011\u0005M\u0016\u0011\u0011a\u0001\u0003k\u000bA\u0001\u001e5biB)\u0011#a.\u0002.&\u0019\u0011\u0011\u0018\u0002\u0003\u0017\u001d+g.\u0013;fe\u0006\u0014G.\u001a\u0005\b\u0003{\u0003A\u0011AA`\u0003\u0019Q\u0018\u000e]!mYVA\u0011\u0011YAk\u0003#\f9\r\u0006\u0005\u0002D\u0006]\u00171\\Ap)\u0011\t)-!3\u0011\u0007U\t9\rB\u0004\u0002\u0010\u0006m&\u0019\u0001\r\t\u0011\u0005M\u00151\u0018a\u0002\u0003\u0017\u0004\u0012\"a&\u0002\u001er\ti-!2\u0011\u000f)\t\u0019+a4\u0002TB\u0019Q#!5\u0005\u000f\u0005-\u00161\u0018b\u0001cB\u0019Q#!6\u0005\r\r\fYL1\u0001\u0019\u0011!\t\u0019,a/A\u0002\u0005e\u0007#B\t\u00028\u0006M\u0007\u0002CAo\u0003w\u0003\r!a4\u0002\u0011QD\u0017n]#mK6D\u0001\"!9\u0002<\u0002\u0007\u00111[\u0001\ti\"\fG/\u00127f[\"9\u0011Q\u001d\u0001\u0005\u0002\u0005\u001d\u0018\u0001\u0004>ja^KG\u000f[%oI\u0016DXCBAu\u0003o\fi\u000f\u0006\u0003\u0002l\u0006=\bcA\u000b\u0002n\u00129\u0011qRAr\u0005\u0004A\u0002\u0002CAJ\u0003G\u0004\u001d!!=\u0011\u0013\u0005]\u0015Q\u0014\u000f\u0002t\u0006-\bc\u0002\u0006\u0002$\u0006U\u0018\u0011\u0003\t\u0004+\u0005]HaBAV\u0003G\u0014\r!\u001d\u0005\b\u0003w\u0004A\u0011AA\u007f\u00031\u0019\u0018-\\3FY\u0016lWM\u001c;t+\u0011\tyPa\u0002\u0015\u0007!\u0013\t\u0001\u0003\u0005\u00024\u0006e\b\u0019\u0001B\u0002!\u0015\t\u0012q\u0017B\u0003!\r)\"q\u0001\u0003\u0007G\u0006e(\u0019A9\t\u000f\t-\u0001\u0001\"\u0011\u0003\u000e\u0005AAo\\*ue\u0016\fW.\u0006\u0002\u0003\u0010A)!\u0011\u0003B\f)5\u0011!1\u0003\u0006\u0004\u0005+\u0011\u0011!C5n[V$\u0018M\u00197f\u0013\u0011\u0011IBa\u0005\u0003\rM#(/Z1n\u0011\u001d\u0011i\u0002\u0001C!\u0005?\t\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004\u0011\n\u0005\u0002bBAZ\u00057\u0001\r!\u0003\u0005\b\u0005K\u0001A\u0011\tB\u0014\u0003\u00111\u0018.Z<\u0016\u0005\t%\"C\u0002B\u0016\u0005_\u0011)DB\u0004\u0003.\t\r\u0002A!\u000b\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0007)\u0011\t$C\u0002\u00034\u0011\u0011a!\u00118z%\u00164\u0007#B\t\u00038Qa\u0012b\u0001B\u001d\u0005\ta\u0011\n^3sC\ndWMV5fo\"9!Q\u0005\u0001\u0005B\tuBC\u0002B\u001b\u0005\u007f\u0011\t\u0005\u0003\u0005\u0002\u0010\tm\u0002\u0019AA\t\u0011!\tIBa\u000fA\u0002\u0005E\u0001\u0003B\t\u0001)q\u0001")
public interface IterableLike<A, Repr>
extends Equals,
TraversableLike<A, Repr>,
GenIterableLike<A, Repr> {
    @Override
    public Iterable<A> thisCollection();

    @Override
    public Iterable<A> toCollection(Repr var1);

    @Override
    public Iterator<A> iterator();

    @Override
    public <U> void foreach(Function1<A, U> var1);

    @Override
    public boolean forall(Function1<A, Object> var1);

    @Override
    public boolean exists(Function1<A, Object> var1);

    @Override
    public Option<A> find(Function1<A, Object> var1);

    @Override
    public boolean isEmpty();

    @Override
    public <B> B foldRight(B var1, Function2<A, B, B> var2);

    @Override
    public <B> B reduceRight(Function2<A, B, B> var1);

    @Override
    public Iterable<A> toIterable();

    @Override
    public Iterator<A> toIterator();

    @Override
    public A head();

    @Override
    public Repr slice(int var1, int var2);

    @Override
    public Repr take(int var1);

    @Override
    public Repr drop(int var1);

    @Override
    public Repr takeWhile(Function1<A, Object> var1);

    public Iterator<Repr> grouped(int var1);

    public Iterator<Repr> sliding(int var1);

    public Iterator<Repr> sliding(int var1, int var2);

    public Repr takeRight(int var1);

    public Repr dropRight(int var1);

    @Override
    public <B> void copyToArray(Object var1, int var2, int var3);

    @Override
    public <A1, B, That> That zip(GenIterable<B> var1, CanBuildFrom<Repr, Tuple2<A1, B>, That> var2);

    @Override
    public <B, A1, That> That zipAll(GenIterable<B> var1, A1 var2, B var3, CanBuildFrom<Repr, Tuple2<A1, B>, That> var4);

    @Override
    public <A1, That> That zipWithIndex(CanBuildFrom<Repr, Tuple2<A1, Object>, That> var1);

    @Override
    public <B> boolean sameElements(GenIterable<B> var1);

    @Override
    public Stream<A> toStream();

    @Override
    public boolean canEqual(Object var1);

    @Override
    public Object view();

    @Override
    public IterableView<A, Repr> view(int var1, int var2);
}

