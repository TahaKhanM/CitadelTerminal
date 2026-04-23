/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.io.BufferedSource;
import scala.io.Codec;
import scala.package$;
import scala.reflect.ScalaSignature;
import scala.reflect.io.Directory;
import scala.reflect.io.File$;
import scala.reflect.io.Path;
import scala.reflect.io.Streamable;
import scala.reflect.io.Streamable$Bytes$class;
import scala.reflect.io.Streamable$Chars$class;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001\u0005\u001dx!B\u0001\u0003\u0011\u0003I\u0011\u0001\u0002$jY\u0016T!a\u0001\u0003\u0002\u0005%|'BA\u0003\u0007\u0003\u001d\u0011XM\u001a7fGRT\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001!\tQ1\"D\u0001\u0003\r\u0015a!\u0001#\u0001\u000e\u0005\u00111\u0015\u000e\\3\u0014\u0005-q\u0001CA\b\u0011\u001b\u00051\u0011BA\t\u0007\u0005\u0019\te.\u001f*fM\")1c\u0003C\u0001)\u00051A(\u001b8jiz\"\u0012!\u0003\u0005\u0006--!\taF\u0001\u000ea\u0006$\bnU3qCJ\fGo\u001c:\u0016\u0003a\u0001\"!\u0007\u0010\u000e\u0003iQ!a\u0007\u000f\u0002\t1\fgn\u001a\u0006\u0002;\u0005!!.\u0019<b\u0013\ty\"D\u0001\u0004TiJLgn\u001a\u0005\u0006C-!\taF\u0001\ng\u0016\u0004\u0018M]1u_JDQaI\u0006\u0005\u0002\u0011\nQ!\u00199qYf$2!JA_)\r1\u00131\u0018\t\u0003\u0015\u001d2A\u0001\u0004\u0002\u0001QM\u0019q%\u000b\u0017\u0011\u0005)Q\u0013BA\u0016\u0003\u0005\u0011\u0001\u0016\r\u001e5\u0011\u00055\u0002dB\u0001\u0006/\u0013\ty#!\u0001\u0006TiJ,\u0017-\\1cY\u0016L!!\r\u001a\u0003\u000b\rC\u0017M]:\u000b\u0005=\u0012\u0001\"\u0003\u001b(\u0005\u0003\u0005\u000b\u0011B\u001b:\u0003\u0015Qg-\u001b7f!\t1\u0004(D\u00018\u0015\t\u0019A$\u0003\u0002\ro%\u0011AG\u000b\u0005\tw\u001d\u0012\t\u0011)A\u0006y\u0005\u00012m\u001c8tiJ,8\r^8s\u0007>$Wm\u0019\t\u0003{}j\u0011A\u0010\u0006\u0003\u0007\u0019I!\u0001\u0011 \u0003\u000b\r{G-Z2\t\u000bM9C\u0011\u0001\"\u0015\u0005\r+EC\u0001\u0014E\u0011\u0015Y\u0014\tq\u0001=\u0011\u0015!\u0014\t1\u00016\u0011\u001d9uE1A\u0005B!\u000bQb\u0019:fCRLwN\\\"pI\u0016\u001cW#\u0001\u001f\t\r);\u0003\u0015!\u0003=\u00039\u0019'/Z1uS>t7i\u001c3fG\u0002BQ\u0001T\u0014\u0005B5\u000bA\"\u00193e\u000bb$XM\\:j_:$\"A\n(\t\u000b=[\u0005\u0019\u0001)\u0002\u0007\u0015DH\u000f\u0005\u0002R):\u0011qBU\u0005\u0003'\u001a\ta\u0001\u0015:fI\u00164\u0017BA\u0010V\u0015\t\u0019f\u0001C\u0003XO\u0011\u0005\u0003,\u0001\u0006u_\u0006\u00137o\u001c7vi\u0016,\u0012A\n\u0005\u00065\u001e\"\teW\u0001\fi>$\u0015N]3di>\u0014\u00180F\u0001]!\tQQ,\u0003\u0002_\u0005\tIA)\u001b:fGR|'/\u001f\u0005\u0006A\u001e\"\t\u0005W\u0001\u0007i>4\u0015\u000e\\3\t\u000b\t<C\u0011\t-\u0002\u00139|'/\\1mSj,\u0007\"\u00023(\t\u0003*\u0017A\u00027f]\u001e$\b.F\u0001g!\tyq-\u0003\u0002i\r\t!Aj\u001c8h\u0011\u0015Qw\u0005\"\u0011l\u0003)9\u0018\r\\6GS2$XM\u001d\u000b\u0003YN\u00042!\u001c9*\u001d\tya.\u0003\u0002p\r\u00059\u0001/Y2lC\u001e,\u0017BA9s\u0005!IE/\u001a:bi>\u0014(BA8\u0007\u0011\u0015!\u0018\u000e1\u0001v\u0003\u0011\u0019wN\u001c3\u0011\t=1\u0018\u0006_\u0005\u0003o\u001a\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005=I\u0018B\u0001>\u0007\u0005\u001d\u0011un\u001c7fC:DQ\u0001`\u0014\u0005\u0002u\f1\"\u001b8qkR\u001cFO]3b[R\ta\u0010\u0005\u00027\u007f&\u0019\u0011\u0011A\u001c\u0003\u001f\u0019KG.Z%oaV$8\u000b\u001e:fC6Dq!!\u0002(\t\u0003\t9!\u0001\u0007pkR\u0004X\u000f^*ue\u0016\fW\u000e\u0006\u0003\u0002\n\u0005=\u0001c\u0001\u001c\u0002\f%\u0019\u0011QB\u001c\u0003!\u0019KG.Z(viB,Ho\u0015;sK\u0006l\u0007\"CA\t\u0003\u0007\u0001\n\u00111\u0001y\u0003\u0019\t\u0007\u000f]3oI\"9\u0011QC\u0014\u0005\u0002\u0005]\u0011A\u00042vM\u001a,'/\u001a3PkR\u0004X\u000f\u001e\u000b\u0005\u00033\ty\u0002E\u00027\u00037I1!!\b8\u0005Q\u0011UO\u001a4fe\u0016$w*\u001e;qkR\u001cFO]3b[\"I\u0011\u0011CA\n!\u0003\u0005\r\u0001\u001f\u0005\b\u0003G9C\u0011AA\u0013\u0003\u00199(/\u001b;feR1\u0011qEA\u0017\u0003_\u00012ANA\u0015\u0013\r\tYc\u000e\u0002\u0013\u001fV$\b/\u001e;TiJ,\u0017-\\,sSR,'\u000fC\u0004\u0002\u0012\u0005\u0005\u0002\u0019\u0001=\t\u000f\u0005E\u0012\u0011\u0005a\u0001y\u0005)1m\u001c3fG\"9\u0011QG\u0014\u0005\u0002\u0005]\u0012A\u00042vM\u001a,'/\u001a3Xe&$XM\u001d\u000b\u0003\u0003s\u00012ANA\u001e\u0013\r\tid\u000e\u0002\u000f\u0005V4g-\u001a:fI^\u0013\u0018\u000e^3s\u0011\u001d\t)d\nC\u0001\u0003\u0003\"B!!\u000f\u0002D!9\u0011\u0011CA \u0001\u0004A\bbBA\u001bO\u0011\u0005\u0011q\t\u000b\u0007\u0003s\tI%a\u0013\t\u000f\u0005E\u0011Q\ta\u0001q\"9\u0011\u0011GA#\u0001\u0004a\u0004bBA(O\u0011\u0005\u0011\u0011K\u0001\faJLg\u000e^,sSR,'\u000f\u0006\u0002\u0002TA\u0019a'!\u0016\n\u0007\u0005]sGA\u0006Qe&tGo\u0016:ji\u0016\u0014\bbBA.O\u0011\u0005\u0011QL\u0001\toJLG/Z!mYR!\u0011qLA3!\ry\u0011\u0011M\u0005\u0004\u0003G2!\u0001B+oSRD\u0001\"a\u001a\u0002Z\u0001\u0007\u0011\u0011N\u0001\bgR\u0014\u0018N\\4t!\u0011y\u00111\u000e)\n\u0007\u00055dA\u0001\u0006=e\u0016\u0004X-\u0019;fIzBq!!\u001d(\t\u0003\t\u0019(A\u0005baB,g\u000eZ!mYR!\u0011qLA;\u0011!\t9'a\u001cA\u0002\u0005%\u0004bBA=O\u0011\u0005\u00111P\u0001\u000baJLg\u000e\u001e7o\u00032dG\u0003BA0\u0003{B\u0001\"a\u001a\u0002x\u0001\u0007\u0011\u0011\u000e\u0005\b\u0003\u0003;C\u0011AAB\u0003%\u0019\u0018MZ3TYV\u0014\b\u000f\u0006\u0002\u0002\u0006B!q\"a\"Q\u0013\r\tII\u0002\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\u00055u\u0005\"\u0001\u0002\u0010\u0006i1/\u001a;Fq\u0016\u001cW\u000f^1cY\u0016$R\u0001_AI\u0003+Cq!a%\u0002\f\u0002\u0007\u00010\u0001\u0006fq\u0016\u001cW\u000f^1cY\u0016D\u0011\"a&\u0002\fB\u0005\t\u0019\u0001=\u0002\u0013=<h.\u001a:P]2L\b\"CANOE\u0005I\u0011AAO\u0003YyW\u000f\u001e9viN#(/Z1nI\u0011,g-Y;mi\u0012\nTCAAPU\rA\u0018\u0011U\u0016\u0003\u0003G\u0003B!!*\u000206\u0011\u0011q\u0015\u0006\u0005\u0003S\u000bY+A\u0005v]\u000eDWmY6fI*\u0019\u0011Q\u0016\u0004\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u00022\u0006\u001d&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\"I\u0011QW\u0014\u0012\u0002\u0013\u0005\u0011QT\u0001\u0019EV4g-\u001a:fI>+H\u000f];uI\u0011,g-Y;mi\u0012\n\u0004\"CA]OE\u0005I\u0011AAO\u0003]\u0019X\r^#yK\u000e,H/\u00192mK\u0012\"WMZ1vYR$#\u0007\u0003\u0004\u00022\t\u0002\u001d\u0001\u0010\u0005\u0007\u0003\u007f\u0013\u0003\u0019A\u0015\u0002\tA\fG\u000f\u001b\u0005\b\u0003\u0007\\A\u0011AAc\u0003!i\u0017m[3UK6\u0004Hc\u0002\u0014\u0002H\u0006-\u0017q\u001a\u0005\n\u0003\u0013\f\t\r%AA\u0002A\u000ba\u0001\u001d:fM&D\b\"CAg\u0003\u0003\u0004\n\u00111\u0001Q\u0003\u0019\u0019XO\u001a4jq\"I\u0011\u0011[Aa!\u0003\u0005\r!N\u0001\u0004I&\u0014\b\"CAk\u0017E\u0005I\u0011AAl\u0003Ii\u0017m[3UK6\u0004H\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005e'f\u0001)\u0002\"\"I\u0011Q\\\u0006\u0012\u0002\u0013\u0005\u0011q[\u0001\u0013[\u0006\\W\rV3na\u0012\"WMZ1vYR$#\u0007C\u0005\u0002b.\t\n\u0011\"\u0001\u0002d\u0006\u0011R.Y6f)\u0016l\u0007\u000f\n3fM\u0006,H\u000e\u001e\u00134+\t\t)OK\u00026\u0003C\u0003")
public class File
extends Path
implements Streamable.Chars {
    private final Codec creationCodec;

    public static java.io.File makeTemp$default$3() {
        return File$.MODULE$.makeTemp$default$3();
    }

    public static String makeTemp$default$2() {
        return File$.MODULE$.makeTemp$default$2();
    }

    public static String makeTemp$default$1() {
        return File$.MODULE$.makeTemp$default$1();
    }

    public static File makeTemp(String string2, String string3, java.io.File file) {
        return File$.MODULE$.makeTemp(string2, string3, file);
    }

    public static File apply(Path path, Codec codec) {
        return File$.MODULE$.apply(path, codec);
    }

    public static String pathSeparator() {
        return File$.MODULE$.pathSeparator();
    }

    @Override
    public BufferedSource chars(Codec codec) {
        return Streamable$Chars$class.chars(this, codec);
    }

    @Override
    public Iterator<String> lines() {
        return Streamable$Chars$class.lines(this);
    }

    @Override
    public Iterator<String> lines(Codec codec) {
        return Streamable$Chars$class.lines(this, codec);
    }

    @Override
    public InputStreamReader reader(Codec codec) {
        return Streamable$Chars$class.reader(this, codec);
    }

    @Override
    public BufferedReader bufferedReader() {
        return Streamable$Chars$class.bufferedReader(this);
    }

    @Override
    public BufferedReader bufferedReader(Codec codec) {
        return Streamable$Chars$class.bufferedReader(this, codec);
    }

    @Override
    public <T> T applyReader(Function1<BufferedReader, T> f) {
        return (T)Streamable$Chars$class.applyReader(this, f);
    }

    @Override
    public String slurp() {
        return Streamable$Chars$class.slurp(this);
    }

    @Override
    public String slurp(Codec codec) {
        return Streamable$Chars$class.slurp(this, codec);
    }

    @Override
    public BufferedInputStream bufferedInput() {
        return Streamable$Bytes$class.bufferedInput(this);
    }

    @Override
    public Iterator<Object> bytes() {
        return Streamable$Bytes$class.bytes(this);
    }

    @Override
    public Iterator<Object> bytesAsInts() {
        return Streamable$Bytes$class.bytesAsInts(this);
    }

    @Override
    public byte[] toByteArray() {
        return Streamable$Bytes$class.toByteArray(this);
    }

    @Override
    public Codec creationCodec() {
        return this.creationCodec;
    }

    @Override
    public File addExtension(String ext) {
        return super.addExtension(ext).toFile();
    }

    @Override
    public File toAbsolute() {
        return this.isAbsolute() ? this : super.toAbsolute().toFile();
    }

    @Override
    public Directory toDirectory() {
        return new Directory(super.jfile());
    }

    @Override
    public File toFile() {
        return this;
    }

    @Override
    public File normalize() {
        return super.normalize().toFile();
    }

    @Override
    public long length() {
        return super.length();
    }

    @Override
    public Iterator<Path> walkFilter(Function1<Path, Object> cond) {
        return BoxesRunTime.unboxToBoolean(cond.apply(this)) ? package$.MODULE$.Iterator().single(this) : package$.MODULE$.Iterator().empty();
    }

    @Override
    public FileInputStream inputStream() {
        return new FileInputStream(super.jfile());
    }

    public FileOutputStream outputStream(boolean append2) {
        return new FileOutputStream(super.jfile(), append2);
    }

    public boolean outputStream$default$1() {
        return false;
    }

    public BufferedOutputStream bufferedOutput(boolean append2) {
        return new BufferedOutputStream(this.outputStream(append2));
    }

    public boolean bufferedOutput$default$1() {
        return false;
    }

    public OutputStreamWriter writer(boolean append2, Codec codec) {
        return new OutputStreamWriter((OutputStream)this.outputStream(append2), codec.charSet());
    }

    public BufferedWriter bufferedWriter() {
        return this.bufferedWriter(false);
    }

    public BufferedWriter bufferedWriter(boolean append2) {
        return this.bufferedWriter(append2, this.creationCodec());
    }

    public BufferedWriter bufferedWriter(boolean append2, Codec codec) {
        return new BufferedWriter(this.writer(append2, codec));
    }

    public PrintWriter printWriter() {
        return new PrintWriter((Writer)this.bufferedWriter(), true);
    }

    /*
     * WARNING - void declaration
     */
    public void writeAll(Seq<String> strings) {
        BufferedWriter out = this.bufferedWriter();
        try {
            strings.foreach(new Serializable(this, out){
                public static final long serialVersionUID = 0L;
                private final BufferedWriter out$1;

                public final void apply(String x$1) {
                    this.out$1.write(x$1);
                }
                {
                    this.out$1 = out$1;
                }
            });
        }
        catch (Throwable throwable) {
            void var2_2;
            var2_2.close();
            throw throwable;
        }
        out.close();
    }

    /*
     * WARNING - void declaration
     */
    public void appendAll(Seq<String> strings) {
        BufferedWriter out = this.bufferedWriter(true);
        try {
            strings.foreach(new Serializable(this, out){
                public static final long serialVersionUID = 0L;
                private final BufferedWriter out$2;

                public final void apply(String x$2) {
                    this.out$2.write(x$2);
                }
                {
                    this.out$2 = out$2;
                }
            });
        }
        catch (Throwable throwable) {
            void var2_2;
            var2_2.close();
            throw throwable;
        }
        out.close();
    }

    /*
     * WARNING - void declaration
     */
    public void printlnAll(Seq<String> strings) {
        PrintWriter out = this.printWriter();
        try {
            strings.foreach(new Serializable(this, out){
                public static final long serialVersionUID = 0L;
                private final PrintWriter out$3;

                public final void apply(String x$3) {
                    this.out$3.println(x$3);
                }
                {
                    this.out$3 = out$3;
                }
            });
            out.close();
            return;
        }
        catch (Throwable throwable) {
            void var2_2;
            var2_2.close();
            throw throwable;
        }
    }

    public Option<String> safeSlurp() {
        Option option;
        try {
            option = new Some<String>(this.slurp());
        }
        catch (IOException iOException) {
            option = None$.MODULE$;
        }
        return option;
    }

    public boolean setExecutable(boolean executable, boolean ownerOnly) {
        boolean bl;
        Method method;
        try {
            method = java.io.File.class.getMethod("setExecutable", Boolean.TYPE, Boolean.TYPE);
        }
        catch (NoSuchMethodException noSuchMethodException) {
            return false;
        }
        try {
            bl = (Boolean)method.invoke((Object)super.jfile(), Predef$.MODULE$.boolean2Boolean(executable), Predef$.MODULE$.boolean2Boolean(ownerOnly));
        }
        catch (Exception exception) {
            bl = false;
        }
        return bl;
    }

    public boolean setExecutable$default$2() {
        return true;
    }

    public File(java.io.File jfile, Codec constructorCodec) {
        super(jfile);
        Streamable$Bytes$class.$init$(this);
        Streamable$Chars$class.$init$(this);
        this.creationCodec = constructorCodec;
    }
}

