/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import java.io.File;
import java.io.Reader;
import scala.Function3;
import scala.Predef$;
import scala.Serializable;
import scala.collection.AbstractSeq;
import scala.collection.IndexedSeq;
import scala.collection.IndexedSeq$class;
import scala.collection.IndexedSeqLike$class;
import scala.collection.Iterator;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.Iterable;
import scala.collection.immutable.Page;
import scala.collection.immutable.PagedSeq$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.io.Source;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;

@ScalaSignature(bytes="\u0006\u0001\u0005Ux!B\u0001\u0003\u0011\u0003I\u0011\u0001\u0003)bO\u0016$7+Z9\u000b\u0005\r!\u0011!C5n[V$\u0018M\u00197f\u0015\t)a!\u0001\u0006d_2dWm\u0019;j_:T\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001!\tQ1\"D\u0001\u0003\r\u0015a!\u0001#\u0001\u000e\u0005!\u0001\u0016mZ3e'\u0016\f8CA\u0006\u000f!\ty\u0001#D\u0001\u0007\u0013\t\tbA\u0001\u0004B]f\u0014VM\u001a\u0005\u0006'-!\t\u0001F\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003%AqAF\u0006C\u0002\u0013\u0015q#A\bV]\u0012,G/\u001a:nS:,G-\u00128e+\u0005Ar\"A\r\u001e\t}|\u0000\u0000@\u0005\u00077-\u0001\u000bQ\u0002\r\u0002!UsG-\u001a;fe6Lg.\u001a3F]\u0012\u0004\u0003\"B\u000f\f\t\u0003q\u0012\u0001\u00044s_6LE/\u001a:bi>\u0014XcA\u0010\u0002NQ\u0019\u0001%!\u0016\u0015\u0007\u0005\ny\u0005\u0005\u0003\u000bE\u0005-c\u0001\u0002\u0007\u0003\u0001\r*\"\u0001J\u0016\u0014\u0007\t*C\u0007E\u0002'O%j\u0011\u0001B\u0005\u0003Q\u0011\u00111\"\u00112tiJ\f7\r^*fcB\u0011!f\u000b\u0007\u0001\t\u0015a#E1\u0001.\u0005\u0005!\u0016C\u0001\u00182!\tyq&\u0003\u00021\r\t9aj\u001c;iS:<\u0007CA\b3\u0013\t\u0019dAA\u0002B]f\u00042AJ\u001b*\u0013\t1DA\u0001\u0006J]\u0012,\u00070\u001a3TKFD\u0001\u0002\u000f\u0012\u0003\u0002\u0003\u0006I!O\u0001\u0005[>\u0014X\r\u0005\u0004\u0010uqzthP\u0005\u0003w\u0019\u0011\u0011BR;oGRLwN\\\u001a\u0011\u0007=i\u0014&\u0003\u0002?\r\t)\u0011I\u001d:bsB\u0011q\u0002Q\u0005\u0003\u0003\u001a\u00111!\u00138u\u0011!\u0019%E!A!\u0002\u0013!\u0015A\u00024jeN$\u0018\u0007E\u0002\u000b\u000b&J!A\u0012\u0002\u0003\tA\u000bw-\u001a\u0005\t\u0011\n\u0012\t\u0011)A\u0005\u007f\u0005)1\u000f^1si\"A!J\tB\u0001B\u0003%q(A\u0002f]\u0012D\u0001\u0002\u0014\u0012\u0003\u0004\u0003\u0006Y!T\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004c\u0001(RS5\tqJ\u0003\u0002Q\r\u00059!/\u001a4mK\u000e$\u0018B\u0001*P\u0005!\u0019E.Y:t)\u0006<\u0007\"B\n#\t#!F#B+Y3j[FC\u0001,X!\rQ!%\u000b\u0005\u0006\u0019N\u0003\u001d!\u0014\u0005\u0006qM\u0003\r!\u000f\u0005\u0006\u0007N\u0003\r\u0001\u0012\u0005\u0006\u0011N\u0003\ra\u0010\u0005\u0006\u0015N\u0003\ra\u0010\u0005\u0006'\t\"\t!\u0018\u000b\u0003=\u0006$\"AV0\t\u000f\u0001d\u0016\u0011!a\u0002\u001b\u0006QQM^5eK:\u001cW\r\n\u001b\t\u000bab\u0006\u0019A\u001d\t\u000f\r\u0014\u0003\u0019!C\u0005I\u000691-\u001e:sK:$X#\u0001#\t\u000f\u0019\u0014\u0003\u0019!C\u0005O\u0006Y1-\u001e:sK:$x\fJ3r)\tA7\u000e\u0005\u0002\u0010S&\u0011!N\u0002\u0002\u0005+:LG\u000fC\u0004mK\u0006\u0005\t\u0019\u0001#\u0002\u0007a$\u0013\u0007\u0003\u0004oE\u0001\u0006K\u0001R\u0001\tGV\u0014(/\u001a8uA!)\u0001O\tC\u0005I\u00061A.\u0019;fgRDQA\u001d\u0012\u0005\nM\fq!\u00193e\u001b>\u0014X\rF\u0001E\u0011\u0015)(\u0005\"\u0003w\u0003\u0011\u0001\u0018mZ3\u0015\u0005\u0011;\b\"\u0002=u\u0001\u0004y\u0014\u0001C1cg&tG-\u001a=\t\u000bi\u0014C\u0011A>\u0002\r1,gn\u001a;i+\u0005y\u0004\"B?#\t\u0003q\u0018!B1qa2LHCA\u0015\u0000\u0011\u0019\t\t\u0001 a\u0001\u007f\u0005)\u0011N\u001c3fq\"9\u0011Q\u0001\u0012\u0005B\u0005\u001d\u0011aC5t\t\u00164\u0017N\\3e\u0003R$B!!\u0003\u0002\u0010A\u0019q\"a\u0003\n\u0007\u00055aAA\u0004C_>dW-\u00198\t\u000f\u0005\u0005\u00111\u0001a\u0001\u007f!9\u00111\u0003\u0012\u0005B\u0005U\u0011!B:mS\u000e,G#\u0002,\u0002\u0018\u0005m\u0001bBA\r\u0003#\u0001\raP\u0001\u0007?N$\u0018M\u001d;\t\u000f\u0005u\u0011\u0011\u0003a\u0001\u007f\u0005!q,\u001a8e\u0011\u001d\t\u0019B\tC\u0001\u0003C!2AVA\u0012\u0011\u0019A\u0015q\u0004a\u0001\u007f!9\u0011q\u0005\u0012\u0005B\u0005%\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005-\u0002\u0003BA\u0017\u0003oi!!a\f\u000b\t\u0005E\u00121G\u0001\u0005Y\u0006twM\u0003\u0002\u00026\u0005!!.\u0019<b\u0013\u0011\tI$a\f\u0003\rM#(/\u001b8hQ\u001d\u0011\u0013QHA\"\u0003\u000f\u00022aDA \u0013\r\t\tE\u0002\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017EAA#\u0003}\"\u0006.[:!G2\f7o\u001d\u0011xS2d\u0007EY3![>4X\r\u001a\u0011u_\u0002\"\b.\u001a\u0011tG\u0006d\u0017-\f9beN,'/L2p[\nLg.\u0019;peN\u0004Sn\u001c3vY\u0016\f#!!\u0013\u0002\rIr\u0013'\r\u00189!\rQ\u0013Q\n\u0003\u0006Yq\u0011\r!\f\u0005\n\u0003#b\u0012\u0011!a\u0002\u0003'\n!\"\u001a<jI\u0016t7-\u001a\u00132!\u0011q\u0015+a\u0013\t\u000f\u0005]C\u00041\u0001\u0002Z\u000511o\\;sG\u0016\u0004RAJA.\u0003\u0017J1!!\u0018\u0005\u0005!IE/\u001a:bi>\u0014\bbBA1\u0017\u0011\u0005\u00111M\u0001\rMJ|W.\u0013;fe\u0006\u0014G.Z\u000b\u0005\u0003K\ni\u0007\u0006\u0003\u0002h\u0005UD\u0003BA5\u0003_\u0002BA\u0003\u0012\u0002lA\u0019!&!\u001c\u0005\r1\nyF1\u0001.\u0011)\t\t(a\u0018\u0002\u0002\u0003\u000f\u00111O\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004\u0003\u0002(R\u0003WB\u0001\"a\u0016\u0002`\u0001\u0007\u0011q\u000f\t\u0006\u0015\u0005e\u00141N\u0005\u0004\u0003w\u0012!\u0001C%uKJ\f'\r\\3\t\u000f\u0005}4\u0002\"\u0001\u0002\u0002\u0006YaM]8n'R\u0014\u0018N\\4t)\u0011\t\u0019)a#\u0011\t)\u0011\u0013Q\u0011\t\u0004\u001f\u0005\u001d\u0015bAAE\r\t!1\t[1s\u0011!\t9&! A\u0002\u00055\u0005#\u0002\u0014\u0002\\\u0005=\u0005\u0003BAI\u0003/s1aDAJ\u0013\r\t)JB\u0001\u0007!J,G-\u001a4\n\t\u0005e\u0012\u0011\u0014\u0006\u0004\u0003+3\u0001bBA@\u0017\u0011\u0005\u0011Q\u0014\u000b\u0005\u0003\u0007\u000by\n\u0003\u0005\u0002X\u0005m\u0005\u0019AAQ!\u0015Q\u0011\u0011PAH\u0011\u001d\t)k\u0003C\u0001\u0003O\u000b\u0011B\u001a:p[2Kg.Z:\u0015\t\u0005\r\u0015\u0011\u0016\u0005\t\u0003/\n\u0019\u000b1\u0001\u0002\u000e\"9\u0011QU\u0006\u0005\u0002\u00055F\u0003BAB\u0003_C\u0001\"a\u0016\u0002,\u0002\u0007\u0011\u0011\u0015\u0005\b\u0003g[A\u0011AA[\u0003)1'o\\7SK\u0006$WM\u001d\u000b\u0005\u0003\u0007\u000b9\f\u0003\u0005\u0002X\u0005E\u0006\u0019AA]!\u0011\tY,!1\u000e\u0005\u0005u&\u0002BA`\u0003g\t!![8\n\t\u0005\r\u0017Q\u0018\u0002\u0007%\u0016\fG-\u001a:\t\u000f\u0005\u001d7\u0002\"\u0001\u0002J\u0006AaM]8n\r&dW\r\u0006\u0003\u0002\u0004\u0006-\u0007\u0002CA,\u0003\u000b\u0004\r!!4\u0011\t\u0005m\u0016qZ\u0005\u0005\u0003#\fiL\u0001\u0003GS2,\u0007bBAd\u0017\u0011\u0005\u0011Q\u001b\u000b\u0005\u0003\u0007\u000b9\u000e\u0003\u0005\u0002X\u0005M\u0007\u0019AAH\u0011\u001d\tYn\u0003C\u0001\u0003;\f!B\u001a:p[N{WO]2f)\u0011\t\u0019)a8\t\u0011\u0005]\u0013\u0011\u001ca\u0001\u0003C\u0004B!a9\u0002h6\u0011\u0011Q\u001d\u0006\u0004\u0003\u007f3\u0011\u0002BAu\u0003K\u0014aaU8ve\u000e,\u0007fB\u0006\u0002>\u00055\u0018qI\u0011\u0003\u0003_\f\u0001\t\u00165jg\u0002z'M[3di\u0002:\u0018\u000e\u001c7!E\u0016\u0004Sn\u001c<fI\u0002\"x\u000e\t;iK\u0002\u001a8-\u00197b[A\f'o]3s[\r|WNY5oCR|'o\u001d\u0011n_\u0012,H.\u001a\u0015\b\u0017\u0005u\u00121IA$Q\u001d\u0001\u0011QHAw\u0003\u000f\u0002")
public class PagedSeq<T>
extends AbstractSeq<T>
implements IndexedSeq<T> {
    private final Function3<Object, Object, Object, Object> more;
    private final Page<T> first1;
    private final int start;
    private final int end;
    private final ClassTag<T> evidence$3;
    private Page<T> current;

    public static PagedSeq<Object> fromSource(Source source) {
        return PagedSeq$.MODULE$.fromSource(source);
    }

    public static PagedSeq<Object> fromFile(String string2) {
        return PagedSeq$.MODULE$.fromFile(string2);
    }

    public static PagedSeq<Object> fromFile(File file) {
        return PagedSeq$.MODULE$.fromFile(file);
    }

    public static PagedSeq<Object> fromReader(Reader reader) {
        return PagedSeq$.MODULE$.fromReader(reader);
    }

    public static PagedSeq<Object> fromLines(Iterable<String> iterable) {
        return PagedSeq$.MODULE$.fromLines(iterable);
    }

    public static PagedSeq<Object> fromLines(Iterator<String> iterator2) {
        return PagedSeq$.MODULE$.fromLines(iterator2);
    }

    public static PagedSeq<Object> fromStrings(Iterable<String> iterable) {
        return PagedSeq$.MODULE$.fromStrings(iterable);
    }

    public static PagedSeq<Object> fromStrings(Iterator<String> iterator2) {
        return PagedSeq$.MODULE$.fromStrings(iterator2);
    }

    public static <T> PagedSeq<T> fromIterable(Iterable<T> iterable, ClassTag<T> classTag) {
        return PagedSeq$.MODULE$.fromIterable(iterable, classTag);
    }

    public static <T> PagedSeq<T> fromIterator(Iterator<T> iterator2, ClassTag<T> classTag) {
        return PagedSeq$.MODULE$.fromIterator(iterator2, classTag);
    }

    public static int UndeterminedEnd() {
        return PagedSeq$.MODULE$.UndeterminedEnd();
    }

    @Override
    public GenericCompanion<IndexedSeq> companion() {
        return IndexedSeq$class.companion(this);
    }

    @Override
    public IndexedSeq<T> seq() {
        return IndexedSeq$class.seq(this);
    }

    @Override
    public int hashCode() {
        return IndexedSeqLike$class.hashCode(this);
    }

    @Override
    public IndexedSeq<T> thisCollection() {
        return IndexedSeqLike$class.thisCollection(this);
    }

    @Override
    public IndexedSeq toCollection(Object repr) {
        return IndexedSeqLike$class.toCollection(this, repr);
    }

    @Override
    public Iterator<T> iterator() {
        return IndexedSeqLike$class.iterator(this);
    }

    @Override
    public <A1> Buffer<A1> toBuffer() {
        return IndexedSeqLike$class.toBuffer(this);
    }

    private Page<T> current() {
        return this.current;
    }

    private void current_$eq(Page<T> x$1) {
        this.current = x$1;
    }

    private Page<T> latest() {
        return this.first1.latest();
    }

    private Page<T> addMore() {
        return this.latest().addMore(this.more);
    }

    private Page<T> page(int absindex) {
        if (absindex < this.current().start()) {
            this.current_$eq(this.first1);
        }
        while (absindex >= this.current().end() && this.current().next() != null) {
            this.current_$eq(this.current().next());
        }
        while (absindex >= this.current().end() && !this.current().isLast()) {
            this.current_$eq(this.addMore());
        }
        return this.current();
    }

    @Override
    public int length() {
        while (!this.latest().isLast() && this.latest().end() < this.end) {
            this.addMore();
        }
        int n = this.latest().end();
        Predef$ predef$ = Predef$.MODULE$;
        return RichInt$.MODULE$.min$extension(n, this.end) - this.start;
    }

    @Override
    public T apply(int index) {
        if (this.isDefinedAt(index)) {
            return this.page(index + this.start).apply(index + this.start);
        }
        throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(index)).toString());
    }

    @Override
    public boolean isDefinedAt(int index) {
        int absidx;
        return index >= 0 && index < this.end - this.start && (absidx = index + this.start) >= 0 && absidx < this.page(absidx).end();
    }

    @Override
    public PagedSeq<T> slice(int _start, int _end) {
        this.page(this.start);
        int s2 = this.start + _start;
        int e = _end == Integer.MAX_VALUE ? _end : this.start + _end;
        Page<T> f = this.first1;
        while (f.end() <= s2 && !f.isLast()) {
            if (f.next() == null) {
                f = f.addMore(this.more);
                continue;
            }
            f = f.next();
        }
        return new PagedSeq<T>(this.more, f, s2, e, this.evidence$3);
    }

    public PagedSeq<T> slice(int start) {
        return this.slice(start, Integer.MAX_VALUE);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        this.iterator().foreach(new Serializable(this, buf){
            public static final long serialVersionUID = 0L;
            private final StringBuilder buf$1;

            public final StringBuilder apply(T ch) {
                return this.buf$1.append(ch);
            }
            {
                this.buf$1 = buf$1;
            }
        });
        return buf.toString();
    }

    public PagedSeq(Function3<Object, Object, Object, Object> more, Page<T> first1, int start, int end, ClassTag<T> evidence$3) {
        this.more = more;
        this.first1 = first1;
        this.start = start;
        this.end = end;
        this.evidence$3 = evidence$3;
        IndexedSeqLike$class.$init$(this);
        IndexedSeq$class.$init$(this);
        this.current = first1;
    }

    public PagedSeq(Function3<Object, Object, Object, Object> more, ClassTag<T> evidence$4) {
        this(more, new Page<T>(0, evidence$4), 0, Integer.MAX_VALUE, evidence$4);
    }
}

