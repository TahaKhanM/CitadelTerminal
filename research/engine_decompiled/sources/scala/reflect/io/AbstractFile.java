/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterable$class;
import scala.collection.IterableLike$class;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.Traversable$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.StringOps;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;
import scala.reflect.io.AbstractFile$;
import scala.reflect.io.File;
import scala.reflect.io.IOStats$;
import scala.reflect.io.Path;
import scala.reflect.io.Path$;
import scala.reflect.io.PlainFile;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u0005ex!B\u0001\u0003\u0011\u0003I\u0011\u0001D!cgR\u0014\u0018m\u0019;GS2,'BA\u0002\u0005\u0003\tIwN\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011!bC\u0007\u0002\u0005\u0019)AB\u0001E\u0001\u001b\ta\u0011IY:ue\u0006\u001cGOR5mKN\u00111B\u0004\t\u0003\u001fAi\u0011AB\u0005\u0003#\u0019\u0011a!\u00118z%\u00164\u0007\"B\n\f\t\u0003!\u0012A\u0002\u001fj]&$h\bF\u0001\n\u0011\u001512\u0002\"\u0001\u0018\u0003\u001d9W\r\u001e$jY\u0016$2\u0001GAa!\tQ\u0011DB\u0003\r\u0005\u0005\u0005!dE\u0002\u001a\u001dm\u00012\u0001H\u0010\u0019\u001d\tyQ$\u0003\u0002\u001f\r\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0011\"\u0005!IE/\u001a:bE2,'B\u0001\u0010\u0007\u0011\u0015\u0019\u0012\u0004\"\u0001$)\u0005A\u0002\"B\u0013\u001a\r\u00031\u0013\u0001\u00028b[\u0016,\u0012a\n\t\u0003Q-r!aD\u0015\n\u0005)2\u0011A\u0002)sK\u0012,g-\u0003\u0002-[\t11\u000b\u001e:j]\u001eT!A\u000b\u0004\t\u000b=Jb\u0011\u0001\u0014\u0002\tA\fG\u000f\u001b\u0005\u0006ce!\tAJ\u0001\u000eG\u0006twN\\5dC2\u0004\u0016\r\u001e5\t\u000bMJB\u0011\u0001\u001b\u0002\u0019!\f7/\u0012=uK:\u001c\u0018n\u001c8\u0015\u0005UB\u0004CA\b7\u0013\t9dAA\u0004C_>dW-\u00198\t\u000be\u0012\u0004\u0019A\u0014\u0002\u000b=$\b.\u001a:\t\u0011mJ\u0002R1A\u0005\n\u0019\n\u0011\"\u001a=uK:\u001c\u0018n\u001c8\t\u0011uJ\u0002\u0012!Q!\n\u001d\n!\"\u001a=uK:\u001c\u0018n\u001c8!\u0011\u0015y\u0014D\"\u0001A\u0003!\t'm]8mkR,W#\u0001\r\t\u000b\tKb\u0011\u0001!\u0002\u0013\r|g\u000e^1j]\u0016\u0014\b\"\u0002#\u001a\r\u0003)\u0015\u0001\u00024jY\u0016,\u0012A\u0012\t\u0003\u000f.k\u0011\u0001\u0013\u0006\u0003\u0007%S\u0011AS\u0001\u0005U\u00064\u0018-\u0003\u0002M\u0011\n!a)\u001b7f\u0011\u0015q\u0015\u0004\"\u0001P\u0003A)h\u000eZ3sYfLgnZ*pkJ\u001cW-F\u0001Q!\ry\u0011\u000bG\u0005\u0003%\u001a\u0011aa\u00149uS>t\u0007\"\u0002+\u001a\t\u0003)\u0016AB3ySN$8/F\u00016\u0011\u00159\u0016\u0004\"\u0001V\u0003AI7o\u00117bgN\u001cuN\u001c;bS:,'\u000fC\u0003Z3\u0019\u0005!,\u0001\u0004de\u0016\fG/\u001a\u000b\u00027B\u0011q\u0002X\u0005\u0003;\u001a\u0011A!\u00168ji\")q,\u0007D\u00015\u00061A-\u001a7fi\u0016DQ!Y\r\u0007\u0002U\u000b1\"[:ESJ,7\r^8ss\")1-\u0007C\u0001+\u0006I\u0011n\u001d,jeR,\u0018\r\u001c\u0005\u0006Kf1\tAZ\u0001\rY\u0006\u001cH/T8eS\u001aLW\rZ\u000b\u0002OB\u0011q\u0002[\u0005\u0003S\u001a\u0011A\u0001T8oO\")1.\u0007D\u0001Y\u0006)\u0011N\u001c9viV\tQ\u000e\u0005\u0002H]&\u0011q\u000e\u0013\u0002\f\u0013:\u0004X\u000f^*ue\u0016\fW\u000eC\u0003r3\u0019\u0005!/\u0001\u0004pkR\u0004X\u000f^\u000b\u0002gB\u0011q\t^\u0005\u0003k\"\u0013AbT;uaV$8\u000b\u001e:fC6DQa^\r\u0005\u0002a\faBY;gM\u0016\u0014X\rZ(viB,H/F\u0001z!\t9%0\u0003\u0002|\u0011\n!\")\u001e4gKJ,GmT;uaV$8\u000b\u001e:fC6DQ!`\r\u0005\u0002y\f!b]5{K>\u0003H/[8o+\u0005y\b\u0003B\bR\u0003\u0003\u00012aDA\u0002\u0013\r\t)A\u0002\u0002\u0004\u0013:$\bbBA\u00053\u0011\u0005\u00111B\u0001\u0006i>,&\u000bT\u000b\u0003\u0003\u001b\u0001B!a\u0004\u0002\u00165\u0011\u0011\u0011\u0003\u0006\u0004\u0003'I\u0015a\u00018fi&!\u0011qCA\t\u0005\r)&\u000b\u0014\u0005\b\u00037IB\u0011AA\u000f\u0003-!xn\u00115be\u0006\u0013(/Y=\u0016\u0005\u0005}\u0001#B\b\u0002\"\u0005\u0015\u0012bAA\u0012\r\t)\u0011I\u001d:bsB\u0019q\"a\n\n\u0007\u0005%bA\u0001\u0003DQ\u0006\u0014\bFBA\r\u0003[\tI\u0004E\u0003\u0010\u0003_\t\u0019$C\u0002\u00022\u0019\u0011a\u0001\u001e5s_^\u001c\bcA$\u00026%\u0019\u0011q\u0007%\u0003\u0017%{U\t_2faRLwN\\\u0012\u0003\u0003gAq!!\u0010\u001a\t\u0003\ty$A\u0006u_\nKH/Z!se\u0006LXCAA!!\u0015y\u0011\u0011EA\"!\ry\u0011QI\u0005\u0004\u0003\u000f2!\u0001\u0002\"zi\u0016Dc!a\u000f\u0002.\u0005e\u0002bBA'3\u0019\u0005\u0011qJ\u0001\tSR,'/\u0019;peV\u0011\u0011\u0011\u000b\t\u00059\u0005M\u0003$C\u0002\u0002V\u0005\u0012\u0001\"\u0013;fe\u0006$xN\u001d\u0005\b\u00033Jb\u0011AA.\u0003)awn\\6va:\u000bW.\u001a\u000b\u00061\u0005u\u0013q\f\u0005\u0007K\u0005]\u0003\u0019A\u0014\t\u000f\u0005\u0005\u0014q\u000ba\u0001k\u0005IA-\u001b:fGR|'/\u001f\u0005\b\u0003KJb\u0011AA4\u0003Mawn\\6va:\u000bW.Z+oG\",7m[3e)\u0015A\u0012\u0011NA6\u0011\u0019)\u00131\ra\u0001O!9\u0011\u0011MA2\u0001\u0004)\u0004bBA83\u0011\u0005\u0011\u0011O\u0001\u0014Y>|7.\u001e9QCRDWK\\2iK\u000e\\W\r\u001a\u000b\u00061\u0005M\u0014Q\u000f\u0005\u0007_\u00055\u0004\u0019A\u0014\t\u000f\u0005\u0005\u0014Q\u000ea\u0001k!9\u0011\u0011P\r\u0005\n\u0005m\u0014A\u00027p_.,\b\u000fF\u0004\u0019\u0003{\n))!#\t\u000fY\t9\b1\u0001\u0002\u0000A9q\"!!\u0019OUB\u0012bAAB\r\tIa)\u001e8di&|gn\r\u0005\b\u0003\u000f\u000b9\b1\u0001(\u0003\u0015\u0001\u0018\r\u001e51\u0011\u001d\t\t'a\u001eA\u0002UBq!!$\u001a\t\u0013\ty)A\fgS2,wJ]*vE\u0012L'/Z2u_JLh*Y7fIR)\u0001$!%\u0002\u0014\"1Q%a#A\u0002\u001dBq!!&\u0002\f\u0002\u0007Q'A\u0003jg\u0012K'\u000fC\u0004\u0002\u001af!\t!a'\u0002\u0013\u0019LG.\u001a(b[\u0016$Gc\u0001\r\u0002\u001e\"1Q%a&A\u0002\u001dBq!!)\u001a\t\u0003\t\u0019+A\ttk\n$\u0017N]3di>\u0014\u0018PT1nK\u0012$2\u0001GAS\u0011\u0019)\u0013q\u0014a\u0001O!9\u0011\u0011V\r\u0005\u0012\u0005-\u0016aC;ogV\u0004\bo\u001c:uK\u0012$\"!!,\u0011\u0007=\ty+C\u0002\u00022\u001a\u0011qAT8uQ&tw\rC\u0004\u0002*f!\t\"!.\u0015\t\u00055\u0016q\u0017\u0005\b\u0003s\u000b\u0019\f1\u0001(\u0003\ri7o\u001a\u0005\b\u0003{KB\u0011IA`\u0003!!xn\u0015;sS:<G#A\u0014\t\u000b=*\u0002\u0019A\u0014\t\rYYA\u0011AAc)\rA\u0012q\u0019\u0005\b_\u0005\r\u0007\u0019AAe!\rQ\u00111Z\u0005\u0004\u0003\u001b\u0014!\u0001\u0002)bi\"DaAF\u0006\u0005\u0002\u0005EGc\u0001\r\u0002T\"9A)a4A\u0002\u0005U\u0007c\u0001\u0006\u0002X&\u0011AJ\u0001\u0005\b\u00037\\A\u0011AAo\u000319W\r\u001e#je\u0016\u001cGo\u001c:z)\rA\u0012q\u001c\u0005\b_\u0005e\u0007\u0019AAe\u0011\u001d\tYn\u0003C\u0001\u0003G$2\u0001GAs\u0011\u001d!\u0015\u0011\u001da\u0001\u0003+Dq!!;\f\t\u0003\tY/\u0001\u0004hKR,&\u000b\u0014\u000b\u00041\u00055\b\u0002CAx\u0003O\u0004\r!!\u0004\u0002\u0007U\u0014H\u000eC\u0004\u0002t.!\t!!>\u0002\u0019\u001d,GOU3t_V\u00148-Z:\u0015\u0007a\t9\u0010\u0003\u0005\u0002p\u0006E\b\u0019AA\u0007\u0001")
public abstract class AbstractFile
implements Iterable<AbstractFile> {
    private String extension;
    private volatile boolean bitmap$0;

    public static AbstractFile getResources(URL uRL) {
        return AbstractFile$.MODULE$.getResources(uRL);
    }

    public static AbstractFile getURL(URL uRL) {
        return AbstractFile$.MODULE$.getURL(uRL);
    }

    public static AbstractFile getDirectory(File file) {
        return AbstractFile$.MODULE$.getDirectory(file);
    }

    public static AbstractFile getDirectory(Path path) {
        return AbstractFile$.MODULE$.getDirectory(path);
    }

    public static AbstractFile getFile(File file) {
        return AbstractFile$.MODULE$.getFile(file);
    }

    public static AbstractFile getFile(Path path) {
        return AbstractFile$.MODULE$.getFile(path);
    }

    public static AbstractFile getFile(String string2) {
        return AbstractFile$.MODULE$.getFile(string2);
    }

    private String extension$lzycompute() {
        AbstractFile abstractFile = this;
        synchronized (abstractFile) {
            if (!this.bitmap$0) {
                this.extension = Path$.MODULE$.extension(this.name());
                this.bitmap$0 = true;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.extension;
        }
    }

    @Override
    public GenericCompanion<Iterable> companion() {
        return Iterable$class.companion(this);
    }

    @Override
    public Iterable<AbstractFile> seq() {
        return Iterable$class.seq(this);
    }

    @Override
    public Iterable<AbstractFile> thisCollection() {
        return IterableLike$class.thisCollection(this);
    }

    @Override
    public Iterable toCollection(Object repr) {
        return IterableLike$class.toCollection(this, repr);
    }

    @Override
    public <U> void foreach(Function1<AbstractFile, U> f) {
        IterableLike$class.foreach(this, f);
    }

    @Override
    public boolean forall(Function1<AbstractFile, Object> p) {
        return IterableLike$class.forall(this, p);
    }

    @Override
    public boolean exists(Function1<AbstractFile, Object> p) {
        return IterableLike$class.exists(this, p);
    }

    @Override
    public Option<AbstractFile> find(Function1<AbstractFile, Object> p) {
        return IterableLike$class.find(this, p);
    }

    @Override
    public boolean isEmpty() {
        return IterableLike$class.isEmpty(this);
    }

    @Override
    public <B> B foldRight(B z, Function2<AbstractFile, B, B> op) {
        return (B)IterableLike$class.foldRight(this, z, op);
    }

    @Override
    public <B> B reduceRight(Function2<AbstractFile, B, B> op) {
        return (B)IterableLike$class.reduceRight(this, op);
    }

    @Override
    public Iterable<AbstractFile> toIterable() {
        return IterableLike$class.toIterable(this);
    }

    @Override
    public Iterator<AbstractFile> toIterator() {
        return IterableLike$class.toIterator(this);
    }

    @Override
    public Object head() {
        return IterableLike$class.head(this);
    }

    @Override
    public Object slice(int from2, int until2) {
        return IterableLike$class.slice(this, from2, until2);
    }

    @Override
    public Object take(int n) {
        return IterableLike$class.take(this, n);
    }

    @Override
    public Object drop(int n) {
        return IterableLike$class.drop(this, n);
    }

    @Override
    public Object takeWhile(Function1 p) {
        return IterableLike$class.takeWhile(this, p);
    }

    @Override
    public Iterator<Iterable<AbstractFile>> grouped(int size2) {
        return IterableLike$class.grouped(this, size2);
    }

    @Override
    public Iterator<Iterable<AbstractFile>> sliding(int size2) {
        return IterableLike$class.sliding(this, size2);
    }

    @Override
    public Iterator<Iterable<AbstractFile>> sliding(int size2, int step) {
        return IterableLike$class.sliding(this, size2, step);
    }

    @Override
    public Object takeRight(int n) {
        return IterableLike$class.takeRight(this, n);
    }

    @Override
    public Object dropRight(int n) {
        return IterableLike$class.dropRight(this, n);
    }

    @Override
    public <B> void copyToArray(Object xs, int start, int len) {
        IterableLike$class.copyToArray(this, xs, start, len);
    }

    @Override
    public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<Iterable<AbstractFile>, Tuple2<A1, B>, That> bf) {
        return (That)IterableLike$class.zip(this, that, bf);
    }

    @Override
    public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<Iterable<AbstractFile>, Tuple2<A1, B>, That> bf) {
        return (That)IterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
    }

    @Override
    public <A1, That> That zipWithIndex(CanBuildFrom<Iterable<AbstractFile>, Tuple2<A1, Object>, That> bf) {
        return (That)IterableLike$class.zipWithIndex(this, bf);
    }

    @Override
    public <B> boolean sameElements(GenIterable<B> that) {
        return IterableLike$class.sameElements(this, that);
    }

    @Override
    public Stream<AbstractFile> toStream() {
        return IterableLike$class.toStream(this);
    }

    @Override
    public boolean canEqual(Object that) {
        return IterableLike$class.canEqual(this, that);
    }

    @Override
    public Object view() {
        return IterableLike$class.view(this);
    }

    @Override
    public IterableView<AbstractFile, Iterable<AbstractFile>> view(int from2, int until2) {
        return IterableLike$class.view(this, from2, until2);
    }

    @Override
    public Builder<AbstractFile, Iterable<AbstractFile>> newBuilder() {
        return GenericTraversableTemplate$class.newBuilder(this);
    }

    @Override
    public <B> Builder<B, Iterable<B>> genericBuilder() {
        return GenericTraversableTemplate$class.genericBuilder(this);
    }

    @Override
    public <A1, A2> Tuple2<Iterable<A1>, Iterable<A2>> unzip(Function1<AbstractFile, Tuple2<A1, A2>> asPair) {
        return GenericTraversableTemplate$class.unzip(this, asPair);
    }

    @Override
    public <A1, A2, A3> Tuple3<Iterable<A1>, Iterable<A2>, Iterable<A3>> unzip3(Function1<AbstractFile, Tuple3<A1, A2, A3>> asTriple) {
        return GenericTraversableTemplate$class.unzip3(this, asTriple);
    }

    @Override
    public GenTraversable flatten(Function1 asTraversable) {
        return GenericTraversableTemplate$class.flatten(this, asTraversable);
    }

    @Override
    public GenTraversable transpose(Function1 asTraversable) {
        return GenericTraversableTemplate$class.transpose(this, asTraversable);
    }

    @Override
    public Object repr() {
        return TraversableLike$class.repr(this);
    }

    @Override
    public final boolean isTraversableAgain() {
        return TraversableLike$class.isTraversableAgain(this);
    }

    @Override
    public Combiner<AbstractFile, ParIterable<AbstractFile>> parCombiner() {
        return TraversableLike$class.parCombiner(this);
    }

    @Override
    public boolean hasDefiniteSize() {
        return TraversableLike$class.hasDefiniteSize(this);
    }

    @Override
    public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<Iterable<AbstractFile>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus(this, that, bf);
    }

    @Override
    public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<Iterable<AbstractFile>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
    }

    @Override
    public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<Iterable<AbstractFile>, B, That> bf) {
        return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
    }

    @Override
    public <B, That> That map(Function1<AbstractFile, B> f, CanBuildFrom<Iterable<AbstractFile>, B, That> bf) {
        return (That)TraversableLike$class.map(this, f, bf);
    }

    @Override
    public <B, That> That flatMap(Function1<AbstractFile, GenTraversableOnce<B>> f, CanBuildFrom<Iterable<AbstractFile>, B, That> bf) {
        return (That)TraversableLike$class.flatMap(this, f, bf);
    }

    @Override
    public Object filter(Function1 p) {
        return TraversableLike$class.filter(this, p);
    }

    @Override
    public Object filterNot(Function1 p) {
        return TraversableLike$class.filterNot(this, p);
    }

    @Override
    public <B, That> That collect(PartialFunction<AbstractFile, B> pf, CanBuildFrom<Iterable<AbstractFile>, B, That> bf) {
        return (That)TraversableLike$class.collect(this, pf, bf);
    }

    @Override
    public Tuple2<Iterable<AbstractFile>, Iterable<AbstractFile>> partition(Function1<AbstractFile, Object> p) {
        return TraversableLike$class.partition(this, p);
    }

    @Override
    public <K> Map<K, Iterable<AbstractFile>> groupBy(Function1<AbstractFile, K> f) {
        return TraversableLike$class.groupBy(this, f);
    }

    @Override
    public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<Iterable<AbstractFile>, B, That> cbf) {
        return (That)TraversableLike$class.scan(this, z, op, cbf);
    }

    @Override
    public <B, That> That scanLeft(B z, Function2<B, AbstractFile, B> op, CanBuildFrom<Iterable<AbstractFile>, B, That> bf) {
        return (That)TraversableLike$class.scanLeft(this, z, op, bf);
    }

    @Override
    public <B, That> That scanRight(B z, Function2<AbstractFile, B, B> op, CanBuildFrom<Iterable<AbstractFile>, B, That> bf) {
        return (That)TraversableLike$class.scanRight(this, z, op, bf);
    }

    @Override
    public Option<AbstractFile> headOption() {
        return TraversableLike$class.headOption(this);
    }

    @Override
    public Object tail() {
        return TraversableLike$class.tail(this);
    }

    @Override
    public Object last() {
        return TraversableLike$class.last(this);
    }

    @Override
    public Option<AbstractFile> lastOption() {
        return TraversableLike$class.lastOption(this);
    }

    @Override
    public Object init() {
        return TraversableLike$class.init(this);
    }

    @Override
    public Object sliceWithKnownDelta(int from2, int until2, int delta) {
        return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
    }

    @Override
    public Object sliceWithKnownBound(int from2, int until2) {
        return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
    }

    @Override
    public Object dropWhile(Function1 p) {
        return TraversableLike$class.dropWhile(this, p);
    }

    @Override
    public Tuple2<Iterable<AbstractFile>, Iterable<AbstractFile>> span(Function1<AbstractFile, Object> p) {
        return TraversableLike$class.span(this, p);
    }

    @Override
    public Tuple2<Iterable<AbstractFile>, Iterable<AbstractFile>> splitAt(int n) {
        return TraversableLike$class.splitAt(this, n);
    }

    @Override
    public Iterator<Iterable<AbstractFile>> tails() {
        return TraversableLike$class.tails(this);
    }

    @Override
    public Iterator<Iterable<AbstractFile>> inits() {
        return TraversableLike$class.inits(this);
    }

    @Override
    public Traversable<AbstractFile> toTraversable() {
        return TraversableLike$class.toTraversable(this);
    }

    @Override
    public <Col> Col to(CanBuildFrom<Nothing$, AbstractFile, Col> cbf) {
        return (Col)TraversableLike$class.to(this, cbf);
    }

    @Override
    public String stringPrefix() {
        return TraversableLike$class.stringPrefix(this);
    }

    @Override
    public FilterMonadic<AbstractFile, Iterable<AbstractFile>> withFilter(Function1<AbstractFile, Object> p) {
        return TraversableLike$class.withFilter(this, p);
    }

    @Override
    public Parallel par() {
        return Parallelizable$class.par(this);
    }

    @Override
    public List<AbstractFile> reversed() {
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
    public int count(Function1<AbstractFile, Object> p) {
        return TraversableOnce$class.count(this, p);
    }

    @Override
    public <B> Option<B> collectFirst(PartialFunction<AbstractFile, B> pf) {
        return TraversableOnce$class.collectFirst(this, pf);
    }

    @Override
    public <B> B $div$colon(B z, Function2<B, AbstractFile, B> op) {
        return (B)TraversableOnce$class.$div$colon(this, z, op);
    }

    @Override
    public <B> B $colon$bslash(B z, Function2<AbstractFile, B, B> op) {
        return (B)TraversableOnce$class.$colon$bslash(this, z, op);
    }

    @Override
    public <B> B foldLeft(B z, Function2<B, AbstractFile, B> op) {
        return (B)TraversableOnce$class.foldLeft(this, z, op);
    }

    @Override
    public <B> B reduceLeft(Function2<B, AbstractFile, B> op) {
        return (B)TraversableOnce$class.reduceLeft(this, op);
    }

    @Override
    public <B> Option<B> reduceLeftOption(Function2<B, AbstractFile, B> op) {
        return TraversableOnce$class.reduceLeftOption(this, op);
    }

    @Override
    public <B> Option<B> reduceRightOption(Function2<AbstractFile, B, B> op) {
        return TraversableOnce$class.reduceRightOption(this, op);
    }

    @Override
    public <A1> A1 reduce(Function2<A1, A1, A1> op) {
        return (A1)TraversableOnce$class.reduce(this, op);
    }

    @Override
    public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
        return TraversableOnce$class.reduceOption(this, op);
    }

    @Override
    public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
        return (A1)TraversableOnce$class.fold(this, z, op);
    }

    @Override
    public <B> B aggregate(Function0<B> z, Function2<B, AbstractFile, B> seqop, Function2<B, B, B> combop) {
        return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
    }

    @Override
    public <B> B sum(Numeric<B> num) {
        return (B)TraversableOnce$class.sum(this, num);
    }

    @Override
    public <B> B product(Numeric<B> num) {
        return (B)TraversableOnce$class.product(this, num);
    }

    @Override
    public Object min(Ordering cmp) {
        return TraversableOnce$class.min(this, cmp);
    }

    @Override
    public Object max(Ordering cmp) {
        return TraversableOnce$class.max(this, cmp);
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
    public List<AbstractFile> toList() {
        return TraversableOnce$class.toList(this);
    }

    @Override
    public Seq<AbstractFile> toSeq() {
        return TraversableOnce$class.toSeq(this);
    }

    @Override
    public IndexedSeq<AbstractFile> toIndexedSeq() {
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
    public Vector<AbstractFile> toVector() {
        return TraversableOnce$class.toVector(this);
    }

    @Override
    public <T, U> Map<T, U> toMap(Predef$.less.colon.less<AbstractFile, Tuple2<T, U>> ev) {
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

    public abstract String name();

    public abstract String path();

    public String canonicalPath() {
        return this.file() == null ? this.path() : this.file().getCanonicalPath();
    }

    public boolean hasExtension(String other) {
        String string2 = this.extension();
        String string3 = other.toLowerCase();
        return !(string2 != null ? !string2.equals(string3) : string3 != null);
    }

    private String extension() {
        return this.bitmap$0 ? this.extension : this.extension$lzycompute();
    }

    public abstract AbstractFile absolute();

    public abstract AbstractFile container();

    public abstract java.io.File file();

    public Option<AbstractFile> underlyingSource() {
        return None$.MODULE$;
    }

    public boolean exists() {
        if (Statistics$.MODULE$.canEnable()) {
            Statistics.Counter counter = IOStats$.MODULE$.fileExistsCount();
            if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                counter.value_$eq(counter.value() + 1);
            }
        }
        return this.file() == null || this.file().exists();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isClassContainer() {
        if (this.isDirectory()) return true;
        if (this.file() == null) return false;
        String string2 = this.extension();
        if (string2 != null) {
            if (string2.equals("jar")) return true;
        }
        String string3 = this.extension();
        if (string3 == null) return false;
        if (!string3.equals("zip")) return false;
        return true;
    }

    public abstract void create();

    public abstract void delete();

    public abstract boolean isDirectory();

    public boolean isVirtual() {
        return false;
    }

    public abstract long lastModified();

    public abstract InputStream input();

    public abstract OutputStream output();

    public BufferedOutputStream bufferedOutput() {
        return new BufferedOutputStream(this.output());
    }

    public Option<Object> sizeOption() {
        return None$.MODULE$;
    }

    public URL toURL() {
        return this.file() == null ? null : this.file().toURI().toURL();
    }

    public char[] toCharArray() throws IOException {
        return new String(this.toByteArray()).toCharArray();
    }

    public byte[] toByteArray() throws IOException {
        Option<Object> option;
        block6: {
            byte[] byArray;
            block5: {
                InputStream in;
                block4: {
                    int res;
                    int rest;
                    in = this.input();
                    option = this.sizeOption();
                    if (!(option instanceof Some)) break block4;
                    Some some = (Some)option;
                    byte[] arr = new byte[rest];
                    for (rest = BoxesRunTime.unboxToInt(some.x()); rest > 0; rest -= res) {
                        res = in.read(arr, arr.length - rest, rest);
                        if (res != -1) continue;
                        throw new IOException("read error");
                    }
                    in.close();
                    byArray = arr;
                    break block5;
                }
                if (!None$.MODULE$.equals(option)) break block6;
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int c = in.read();
                while (c != -1) {
                    out.write(c);
                    c = in.read();
                }
                in.close();
                byArray = out.toByteArray();
            }
            return byArray;
        }
        throw new MatchError(option);
    }

    @Override
    public abstract Iterator<AbstractFile> iterator();

    public abstract AbstractFile lookupName(String var1, boolean var2);

    public abstract AbstractFile lookupNameUnchecked(String var1, boolean var2);

    public AbstractFile lookupPathUnchecked(String path, boolean directory) {
        return this.lookup((Function3<AbstractFile, String, Object, AbstractFile>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final AbstractFile apply(AbstractFile f, String p, boolean dir) {
                return f.lookupNameUnchecked(p, dir);
            }
        }), path, directory);
    }

    /*
     * Unable to fully structure code
     */
    private AbstractFile lookup(Function3<AbstractFile, String, Object, AbstractFile> getFile, String path0, boolean directory) {
        separator = java.io.File.separatorChar;
        var4_5 = Predef$.MODULE$;
        if (BoxesRunTime.unboxToChar(new StringOps(path0).last()) == separator) {
            var5_6 = Predef$.MODULE$;
            v0 = (String)new StringOps(path0).dropRight(1);
        } else {
            v0 = path0;
        }
        path = v0;
        length = path.length();
        if (length <= 0) ** GOTO lbl-1000
        var6_9 = Predef$.MODULE$;
        if (BoxesRunTime.unboxToChar(new StringOps(path).last()) != separator) {
            v1 = true;
        } else lbl-1000:
        // 2 sources

        {
            v1 = false;
        }
        var9_10 = new Serializable(this, path){
            public static final long serialVersionUID = 0L;
            public final String path$1;

            public final String apply() {
                return this.path$1;
            }
            {
                this.path$1 = path$1;
            }
        };
        var8_11 = v1;
        var7_12 = Predef$.MODULE$;
        if (!var8_11) {
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)var9_10.path$1).toString());
        }
        file = this;
        start = IntRef.create(0);
        while (true) {
            var13_17 = new Serializable(this, directory, path, start, index){
                public static final long serialVersionUID = 0L;
                public final boolean directory$1;
                public final String path$1;
                public final IntRef start$1;
                public final int index$1;

                public final Tuple4<String, Object, Object, Object> apply() {
                    return new Tuple4<String, Object, Object, Object>(this.path$1, BoxesRunTime.boxToBoolean(this.directory$1), BoxesRunTime.boxToInteger(this.start$1.elem), BoxesRunTime.boxToInteger(this.index$1));
                }
                {
                    this.directory$1 = directory$1;
                    this.path$1 = path$1;
                    this.start$1 = start$1;
                    this.index$1 = index$1;
                }
            };
            index = path.indexOf(separator, start.elem);
            var12_16 = index < 0 || start.elem < index;
            var11_15 = Predef$.MODULE$;
            if (!var12_16) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(new Tuple4<String, Boolean, Integer, Integer>(var13_17.path$1, BoxesRunTime.boxToBoolean(var13_17.directory$1), BoxesRunTime.boxToInteger(var13_17.start$1.elem), BoxesRunTime.boxToInteger(var13_17.index$1))).toString());
            }
            name = path.substring(start.elem, index < 0 ? length : index);
            if ((file = getFile.apply(file, name, index < 0 ? BoxesRunTime.boxToBoolean(directory) : BoxesRunTime.boxToBoolean(true))) == null || index < 0) {
                return file;
            }
            start.elem = index + 1;
        }
    }

    private AbstractFile fileOrSubdirectoryNamed(String name, boolean isDir) {
        AbstractFile abstractFile;
        AbstractFile lookup2 = this.lookupName(name, isDir);
        if (lookup2 == null) {
            java.io.File jfile = new java.io.File(this.file(), name);
            boolean bl = isDir ? jfile.mkdirs() : jfile.createNewFile();
            abstractFile = new PlainFile(Path$.MODULE$.jfile2path(jfile));
        } else {
            abstractFile = lookup2;
        }
        return abstractFile;
    }

    public AbstractFile fileNamed(String name) {
        boolean bl = this.isDirectory();
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            Predef$ predef$2 = Predef$.MODULE$;
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringOps("Tried to find '%s' in '%s' but it is not a directory").format(Predef$.MODULE$.genericWrapArray(new Object[]{name, this.path()}))).toString());
        }
        return this.fileOrSubdirectoryNamed(name, false);
    }

    public AbstractFile subdirectoryNamed(String name) {
        boolean bl = this.isDirectory();
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            Predef$ predef$2 = Predef$.MODULE$;
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringOps("Tried to find '%s' in '%s' but it is not a directory").format(Predef$.MODULE$.genericWrapArray(new Object[]{name, this.path()}))).toString());
        }
        return this.fileOrSubdirectoryNamed(name, true);
    }

    public Nothing$ unsupported() {
        return this.unsupported(null);
    }

    public Nothing$ unsupported(String msg) {
        throw new UnsupportedOperationException(msg);
    }

    @Override
    public String toString() {
        return this.path();
    }

    public AbstractFile() {
        TraversableOnce$class.$init$(this);
        Parallelizable$class.$init$(this);
        TraversableLike$class.$init$(this);
        GenericTraversableTemplate$class.$init$(this);
        GenTraversable$class.$init$(this);
        Traversable$class.$init$(this);
        GenIterable$class.$init$(this);
        IterableLike$class.$init$(this);
        Iterable$class.$init$(this);
    }
}

