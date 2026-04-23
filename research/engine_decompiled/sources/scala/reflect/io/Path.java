/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URL;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.LinearSeqOptimized;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.io.Codec$;
import scala.package$;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;
import scala.reflect.io.Directory;
import scala.reflect.io.Directory$;
import scala.reflect.io.File;
import scala.reflect.io.IOStats$;
import scala.reflect.io.Path$;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001\tmv!B\u0001\u0003\u0011\u0003I\u0011\u0001\u0002)bi\"T!a\u0001\u0003\u0002\u0005%|'BA\u0003\u0007\u0003\u001d\u0011XM\u001a7fGRT\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001!\tQ1\"D\u0001\u0003\r\u0015a!\u0001#\u0001\u000e\u0005\u0011\u0001\u0016\r\u001e5\u0014\u0005-q\u0001CA\b\u0011\u001b\u00051\u0011BA\t\u0007\u0005\u0019\te.\u001f*fM\")1c\u0003C\u0001)\u00051A(\u001b8jiz\"\u0012!\u0003\u0005\u0006--!\taF\u0001\u0014SN,\u0005\u0010^3og&|gNS1s\u001fJT\u0016\u000e\u001d\u000b\u00031m\u0001\"aD\r\n\u0005i1!a\u0002\"p_2,\u0017M\u001c\u0005\u00069U\u0001\r!H\u0001\u0006U\u001aLG.\u001a\t\u0003=\tj\u0011a\b\u0006\u0003\u0007\u0001R\u0011!I\u0001\u0005U\u00064\u0018-\u0003\u0002$?\t!a)\u001b7f\u0011\u001512\u0002\"\u0001&)\tAb\u0005C\u0003(I\u0001\u0007\u0001&\u0001\u0003oC6,\u0007CA\u0015-\u001d\ty!&\u0003\u0002,\r\u00051\u0001K]3eK\u001aL!!\f\u0018\u0003\rM#(/\u001b8h\u0015\tYc\u0001C\u00031\u0017\u0011\u0005\u0011'A\u0005fqR,gn]5p]R\u0011\u0001F\r\u0005\u0006O=\u0002\r\u0001\u000b\u0005\u0006i-!\u0019!N\u0001\fgR\u0014\u0018N\\43a\u0006$\b\u000eF\u00027\u0005_\u0002\"AC\u001c\u0007\t1\u0011\u0001\u0001O\n\u0003o9A\u0001\u0002H\u001c\u0003\u0006\u0004%\tAO\u000b\u0002;!AAh\u000eB\u0001B\u0003%Q$\u0001\u0004kM&dW\r\t\u0005\u0007']\"\tA\u0001 \u0015\u0005Yz\u0004\"\u0002\u000f>\u0001\u0004i\u0002bB!8\u0005\u0004%\tAQ\u0001\ng\u0016\u0004\u0018M]1u_J,\u0012a\u0011\t\u0003\u001f\u0011K!!\u0012\u0004\u0003\t\rC\u0017M\u001d\u0005\u0007\u000f^\u0002\u000b\u0011B\"\u0002\u0015M,\u0007/\u0019:bi>\u0014\b\u0005C\u0004Jo\t\u0007I\u0011\u0001&\u0002\u0019M,\u0007/\u0019:bi>\u00148\u000b\u001e:\u0016\u0003-\u0003\"\u0001T(\u000e\u00035S!A\u0014\u0011\u0002\t1\fgnZ\u0005\u0003[5Ca!U\u001c!\u0002\u0013Y\u0015!D:fa\u0006\u0014\u0018\r^8s'R\u0014\b\u0005C\u0003To\u0011\u0005A+\u0001\u0004u_\u001aKG.Z\u000b\u0002+B\u0011!BV\u0005\u0003G\tAQ\u0001W\u001c\u0005\u0002e\u000b1\u0002^8ESJ,7\r^8ssV\t!\f\u0005\u0002\u000b7&\u0011AL\u0001\u0002\n\t&\u0014Xm\u0019;pefDQAX\u001c\u0005\u0002}\u000b!\u0002^8BEN|G.\u001e;f+\u00051\u0004\"B18\t\u0003y\u0016a\u0003;p\u0007\u0006twN\\5dC2DQaY\u001c\u0005\u0002\u0011\fQ\u0001^8V%&+\u0012!\u001a\t\u0003M&l\u0011a\u001a\u0006\u0003Q\u0002\n1A\\3u\u0013\tQwMA\u0002V%&CQ\u0001\\\u001c\u0005\u00025\fQ\u0001^8V%2+\u0012A\u001c\t\u0003M>L!\u0001]4\u0003\u0007U\u0013F\nC\u0003so\u0011\u00051/\u0001\nu_\u0006\u00137o\u001c7vi\u0016<\u0016\u000e\u001e5S_>$HC\u0001\u001cu\u0011\u0015)\u0018\u000f1\u00017\u0003\u0011\u0011xn\u001c;\t\u000b]<D\u0011\u0001=\u0002\t\u0011\"\u0017N\u001e\u000b\u0003meDQA\u001f<A\u0002Y\nQa\u00195jY\u0012DQa^\u001c\u0005\u0002q$\"AW?\t\u000bi\\\b\u0019\u0001.\t\u000b]<D\u0011A@\u0015\u0007U\u000b\t\u0001C\u0003{}\u0002\u0007Q\u000bC\u0004\u0002\u0006]\"\t!a\u0002\u0002\u0015]\fGn\u001b$jYR,'\u000f\u0006\u0003\u0002\n\u0005]\u0001#BA\u0006\u0003#1dbA\b\u0002\u000e%\u0019\u0011q\u0002\u0004\u0002\u000fA\f7m[1hK&!\u00111CA\u000b\u0005!IE/\u001a:bi>\u0014(bAA\b\r!A\u0011\u0011DA\u0002\u0001\u0004\tY\"\u0001\u0003d_:$\u0007#B\b\u0002\u001eYB\u0012bAA\u0010\r\tIa)\u001e8di&|g.\r\u0005\b\u0003G9D\u0011AA\u0013\u0003\u00119\u0018\r\\6\u0016\u0005\u0005%\u0001BB\u00148\t\u0003\tI#F\u0001)\u0011\u001d\tic\u000eC\u0001\u0003S\tA\u0001]1uQ\"1\u0011\u0011G\u001c\u0005\u0002}\u000b\u0011B\\8s[\u0006d\u0017N_3\t\u000f\u0005Ur\u0007\"\u0001\u00028\u00059!/Z:pYZ,Gc\u0001\u001c\u0002:!9\u00111HA\u001a\u0001\u00041\u0014!B8uQ\u0016\u0014\bbBA o\u0011\u0005\u0011\u0011I\u0001\u000be\u0016d\u0017\r^5wSj,Gc\u0001\u001c\u0002D!9\u00111HA\u001f\u0001\u00041\u0004bBA$o\u0011\u0005\u0011\u0011J\u0001\tg\u0016<W.\u001a8ugV\u0011\u00111\n\t\u0006\u0003\u0017\ti\u0005K\u0005\u0005\u0003\u001f\n)B\u0001\u0003MSN$\bBBA*o\u0011\u0005\u0011,\u0001\u0004qCJ,g\u000e\u001e\u0005\b\u0003/:D\u0011AA-\u0003\u001d\u0001\u0018M]3oiN,\"!a\u0017\u0011\u000b\u0005-\u0011Q\n.\t\rA:D\u0011AA\u0015\u0011\u001d\t\tg\u000eC\u0001\u0003G\nA\u0002[1t\u000bb$XM\\:j_:$R\u0001GA3\u0003SBq!a\u001a\u0002`\u0001\u0007\u0001&A\u0002fqRD\u0001\"a\u001b\u0002`\u0001\u0007\u0011QN\u0001\u0005Kb$8\u000f\u0005\u0003\u0010\u0003_B\u0013bAA9\r\tQAH]3qK\u0006$X\r\u001a \t\u000f\u0005Ut\u0007\"\u0001\u0002*\u0005q1\u000f\u001e:ja\u0016CH/\u001a8tS>t\u0007bBA=o\u0011\u0005\u00111P\u0001\rC\u0012$W\t\u001f;f]NLwN\u001c\u000b\u0004m\u0005u\u0004bBA4\u0003o\u0002\r\u0001\u000b\u0005\b\u0003\u0003;D\u0011AAB\u0003=\u0019\u0007.\u00198hK\u0016CH/\u001a8tS>tGc\u0001\u001c\u0002\u0006\"9\u0011qMA@\u0001\u0004A\u0003bBAEo\u0011\u0005\u00111R\u0001\u0007S\u001a4\u0015\u000e\\3\u0016\t\u00055\u0015\u0011\u0014\u000b\u0005\u0003\u001f\u000bY\u000bE\u0003\u0010\u0003#\u000b)*C\u0002\u0002\u0014\u001a\u0011aa\u00149uS>t\u0007\u0003BAL\u00033c\u0001\u0001\u0002\u0005\u0002\u001c\u0006\u001d%\u0019AAO\u0005\u0005!\u0016\u0003BAP\u0003K\u00032aDAQ\u0013\r\t\u0019K\u0002\u0002\b\u001d>$\b.\u001b8h!\ry\u0011qU\u0005\u0004\u0003S3!aA!os\"A\u0011QVAD\u0001\u0004\ty+A\u0001g!\u0019y\u0011QD+\u0002\u0016\"9\u00111W\u001c\u0005\u0002\u0005U\u0016aC5g\t&\u0014Xm\u0019;pef,B!a.\u0002>R!\u0011\u0011XA`!\u0015y\u0011\u0011SA^!\u0011\t9*!0\u0005\u0011\u0005m\u0015\u0011\u0017b\u0001\u0003;C\u0001\"!,\u00022\u0002\u0007\u0011\u0011\u0019\t\u0007\u001f\u0005u!,a/\t\u000f\u0005\u0015w\u0007\"\u0001\u0002H\u000691-\u00198SK\u0006$W#\u0001\r\t\u000f\u0005-w\u0007\"\u0001\u0002H\u0006A1-\u00198Xe&$X\rC\u0004\u0002P^\"\t!a2\u0002\r\u0015D\u0018n\u001d;t\u0011\u001d\t\u0019n\u000eC\u0001\u0003\u000f\fa![:GS2,\u0007bBAlo\u0011\u0005\u0011qY\u0001\fSN$\u0015N]3di>\u0014\u0018\u0010C\u0004\u0002\\^\"\t!a2\u0002\u0015%\u001c\u0018IY:pYV$X\rC\u0004\u0002`^\"\t!a2\u0002\u000f%\u001cX)\u001c9us\"9\u00111]\u001c\u0005\u0002\u0005\u0015\u0018\u0001\u00047bgRlu\u000eZ5gS\u0016$WCAAt!\ry\u0011\u0011^\u0005\u0004\u0003W4!\u0001\u0002'p]\u001eDq!a<8\t\u0003\t)/\u0001\u0004mK:<G\u000f\u001b\u0005\b\u0003g<D\u0011AA{\u0003!)g\u000eZ:XSRDGc\u0001\r\u0002x\"9\u00111HAy\u0001\u00041\u0004bBA~o\u0011\u0005\u0011Q`\u0001\u0007SN\u001c\u0016-\\3\u0015\u0007a\ty\u0010C\u0004\u0002<\u0005e\b\u0019\u0001\u001c\t\u000f\t\rq\u0007\"\u0001\u0003\u0006\u0005I\u0011n\u001d$sKNDWM\u001d\u000b\u00041\t\u001d\u0001bBA\u001e\u0005\u0003\u0001\rA\u000e\u0005\b\u0005\u00179D\u0011\u0001B\u0007\u0003=\u0019'/Z1uK\u0012K'/Z2u_JLH#\u0002.\u0003\u0010\tM\u0001\"\u0003B\t\u0005\u0013\u0001\n\u00111\u0001\u0019\u0003\u00151wN]2f\u0011%\u0011)B!\u0003\u0011\u0002\u0003\u0007\u0001$\u0001\u0007gC&d\u0017JZ#ySN$8\u000fC\u0004\u0003\u001a]\"\tAa\u0007\u0002\u0015\r\u0014X-\u0019;f\r&dW\rF\u0002V\u0005;A\u0011B!\u0006\u0003\u0018A\u0005\t\u0019\u0001\r\t\u000f\t\u0005r\u0007\"\u0001\u0003$\u00051A-\u001a7fi\u0016$\u0012\u0001\u0007\u0005\b\u0005O9D\u0011\u0001B\u0012\u0003E!W\r\\3uKJ+7-\u001e:tSZ,G.\u001f\u0005\b\u0005O9D\u0011\u0002B\u0016)\rA\"Q\u0006\u0005\b\u0003[\u0013I\u00031\u0001\u001e\u0011\u001d\u0011\td\u000eC\u0001\u0005G\t\u0001\u0002\u001e:v]\u000e\fG/\u001a\u0005\b\u0005k9D\u0011\tB\u001c\u0003!!xn\u0015;sS:<G#\u0001\u0015\t\u000f\tmr\u0007\"\u0011\u0003>\u00051Q-];bYN$2\u0001\u0007B \u0011!\tYD!\u000fA\u0002\u0005\u0015\u0006b\u0002B\"o\u0011\u0005#QI\u0001\tQ\u0006\u001c\bnQ8eKR\u0011!q\t\t\u0004\u001f\t%\u0013b\u0001B&\r\t\u0019\u0011J\u001c;\t\u0013\t=s'%A\u0005\u0002\tE\u0013!G2sK\u0006$X\rR5sK\u000e$xN]=%I\u00164\u0017-\u001e7uIE*\"Aa\u0015+\u0007a\u0011)f\u000b\u0002\u0003XA!!\u0011\fB2\u001b\t\u0011YF\u0003\u0003\u0003^\t}\u0013!C;oG\",7m[3e\u0015\r\u0011\tGB\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002B3\u00057\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0011%\u0011IgNI\u0001\n\u0003\u0011\t&A\rde\u0016\fG/\u001a#je\u0016\u001cGo\u001c:zI\u0011,g-Y;mi\u0012\u0012\u0004\"\u0003B7oE\u0005I\u0011\u0001B)\u0003Q\u0019'/Z1uK\u001aKG.\u001a\u0013eK\u001a\fW\u000f\u001c;%c!1!\u0011O\u001aA\u0002!\n\u0011a\u001d\u0005\b\u0005kZA1\u0001B<\u0003)Qg-\u001b7feA\fG\u000f\u001b\u000b\u0004m\te\u0004B\u0002\u000f\u0003t\u0001\u0007Q\u0004C\u0004\u0003~-!\tAa \u0002\u0011=tG.\u001f#jeN$BA!!\u0003\u0004B)\u00111BA\t5\"A!Q\u0011B>\u0001\u0004\tI!\u0001\u0002yg\"9!QP\u0006\u0005\u0002\t%E\u0003BA.\u0005\u0017C\u0001B!\"\u0003\b\u0002\u0007!Q\u0012\t\u0006\u0003\u0017\tiE\u000e\u0005\b\u0005#[A\u0011\u0001BJ\u0003%yg\u000e\\=GS2,7\u000f\u0006\u0003\u0003\u0016\n]\u0005#BA\u0006\u0003#)\u0006\u0002\u0003BC\u0005\u001f\u0003\r!!\u0003\t\u000f\tm5\u0002\"\u0001\u0003\u001e\u0006)!o\\8ugV\u0011!Q\u0012\u0005\b\u0005C[A\u0011\u0001BR\u0003\u0015\t\u0007\u000f\u001d7z)\r1$Q\u0015\u0005\b\u0003[\u0011y\n1\u0001)\u0011\u001d\u0011\tk\u0003C\u0001\u0005S#2A\u000eBV\u0011\u0019a\"q\u0015a\u0001;!A!qV\u0006\u0005\u0002\t\tI#\u0001\u0007sC:$w.\u001c)sK\u001aL\u0007\u0010\u0003\u0005\u00034.!\tA\u0001B[\u0003\u00111\u0017-\u001b7\u0015\t\u0005}%q\u0017\u0005\b\u0005s\u0013\t\f1\u0001)\u0003\ri7o\u001a")
public class Path {
    private final java.io.File jfile;
    private final char separator;
    private final String separatorStr;

    public static Path apply(java.io.File file) {
        return Path$.MODULE$.apply(file);
    }

    public static Path apply(String string2) {
        return Path$.MODULE$.apply(string2);
    }

    public static List<Path> roots() {
        return Path$.MODULE$.roots();
    }

    public static Iterator<File> onlyFiles(Iterator<Path> iterator2) {
        return Path$.MODULE$.onlyFiles(iterator2);
    }

    public static List<Directory> onlyDirs(List<Path> list2) {
        return Path$.MODULE$.onlyDirs(list2);
    }

    public static Iterator<Directory> onlyDirs(Iterator<Path> iterator2) {
        return Path$.MODULE$.onlyDirs(iterator2);
    }

    public static Path jfile2path(java.io.File file) {
        return Path$.MODULE$.jfile2path(file);
    }

    public static Path string2path(String string2) {
        return Path$.MODULE$.string2path(string2);
    }

    public static boolean isExtensionJarOrZip(String string2) {
        return Path$.MODULE$.isExtensionJarOrZip(string2);
    }

    public static boolean isExtensionJarOrZip(java.io.File file) {
        return Path$.MODULE$.isExtensionJarOrZip(file);
    }

    public java.io.File jfile() {
        return this.jfile;
    }

    public char separator() {
        return this.separator;
    }

    public String separatorStr() {
        return this.separatorStr;
    }

    public File toFile() {
        return new File(this.jfile(), Codec$.MODULE$.fallbackSystemCodec());
    }

    public Directory toDirectory() {
        return new Directory(this.jfile());
    }

    public Path toAbsolute() {
        return this.isAbsolute() ? this : Path$.MODULE$.apply(this.jfile().getAbsolutePath());
    }

    public Path toCanonical() {
        return Path$.MODULE$.apply(this.jfile().getCanonicalPath());
    }

    public URI toURI() {
        return this.jfile().toURI();
    }

    public URL toURL() {
        return this.toURI().toURL();
    }

    public Path toAbsoluteWithRoot(Path root) {
        return this.isAbsolute() ? this : root.toAbsolute().$div(this);
    }

    public Path $div(Path child) {
        return this.isEmpty() ? child : new Path(new java.io.File(this.jfile(), child.path()));
    }

    public Directory $div(Directory child) {
        return this.$div((Path)child).toDirectory();
    }

    public File $div(File child) {
        return this.$div((Path)child).toFile();
    }

    public Iterator<Path> walkFilter(Function1<Path, Object> cond) {
        return this.isFile() ? this.toFile().walkFilter(cond) : (this.isDirectory() ? this.toDirectory().walkFilter(cond) : package$.MODULE$.Iterator().empty());
    }

    public Iterator<Path> walk() {
        return this.walkFilter((Function1<Path, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Path x$7) {
                return true;
            }
        }));
    }

    public String name() {
        return this.jfile().getName();
    }

    public String path() {
        return this.jfile().getPath();
    }

    public Path normalize() {
        return Path$.MODULE$.apply(this.jfile().getAbsolutePath());
    }

    public Path resolve(Path other) {
        return other.isAbsolute() || this.isEmpty() ? other : this.$div(other);
    }

    public Path relativize(Path other) {
        boolean bl = this.isAbsolute() == other.isAbsolute();
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringBuilder().append((Object)"Paths not of same type: ").append(this).append((Object)", ").append(other).toString()).toString());
        }
        String string2 = this.createRelativePath$1(this.segments(), other.segments());
        return Path$.MODULE$.apply(new java.io.File(string2));
    }

    public List<String> segments() {
        String string2 = this.path();
        Predef$ predef$ = Predef$.MODULE$;
        return (List)Predef$.MODULE$.refArrayOps((Object[])new StringOps(string2).split(this.separator())).toList().filterNot((Function1)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(String x$8) {
                return x$8.length() == 0;
            }
        }));
    }

    /*
     * Unable to fully structure code
     */
    public Directory parent() {
        block3: {
            block2: {
                var1_1 = this.path();
                var2_2 = "".equals(var1_1) != false ? true : ".".equals(var1_1) != false;
                if (!var2_2) break block2;
                var5_3 = Directory$.MODULE$.apply(Path$.MODULE$.string2path(".."));
                break block3;
            }
            if (!this.segments().nonEmpty()) ** GOTO lbl-1000
            v0 = this.segments().last();
            if (v0 != null && v0.equals("..")) {
                v1 = Path$.MODULE$.string2path(this.path()).$div(Path$.MODULE$.string2path("..")).toDirectory();
            } else lbl-1000:
            // 2 sources

            {
                var4_5 = (var3_4 = this.jfile().getParent()) == null ? (this.isAbsolute() != false ? this.toDirectory() : Directory$.MODULE$.apply(Path$.MODULE$.string2path("."))) : Directory$.MODULE$.apply(Path$.MODULE$.string2path(var3_4));
                v1 = var4_5;
            }
            var5_3 = v1;
        }
        return var5_3;
    }

    public List<Directory> parents() {
        Directory p = this.parent();
        return p.isSame(this) ? Nil$.MODULE$ : p.parents().$colon$colon(p);
    }

    public String extension() {
        int i;
        for (i = this.name().length() - 1; i >= 0 && this.name().charAt(i) != '.'; --i) {
        }
        return i < 0 ? "" : this.name().substring(i + 1);
    }

    public boolean hasExtension(String ext, Seq<String> exts) {
        String lower = this.extension().toLowerCase();
        String string2 = ext.toLowerCase();
        return !(string2 == null ? lower != null : !string2.equals(lower)) || exts.exists((Function1<String, Object>)((Object)new Serializable(this, lower){
            public static final long serialVersionUID = 0L;
            private final String lower$1;

            public final boolean apply(String x$10) {
                String string2 = x$10.toLowerCase();
                String string3 = this.lower$1;
                return !(string2 != null ? !string2.equals(string3) : string3 != null);
            }
            {
                this.lower$1 = lower$1;
            }
        }));
    }

    public String stripExtension() {
        String string2 = this.name();
        Predef$ predef$ = Predef$.MODULE$;
        return new StringOps(string2).stripSuffix(new StringBuilder().append((Object)".").append((Object)this.extension()).toString());
    }

    public Path addExtension(String ext) {
        return Path$.MODULE$.apply(new StringBuilder().append((Object)this.path()).append((Object)".").append((Object)ext).toString());
    }

    public Path changeExtension(String ext) {
        Path path;
        String string2 = this.extension();
        if (string2 != null && string2.equals("")) {
            path = this.addExtension(ext);
        } else {
            String string3 = this.path();
            Predef$ predef$ = Predef$.MODULE$;
            path = Path$.MODULE$.apply(new StringBuilder().append((Object)new StringOps(string3).stripSuffix(this.extension())).append((Object)ext).toString());
        }
        return path;
    }

    public <T> Option<T> ifFile(Function1<File, T> f) {
        return this.isFile() ? new Some<T>(f.apply(this.toFile())) : None$.MODULE$;
    }

    public <T> Option<T> ifDirectory(Function1<Directory, T> f) {
        return this.isDirectory() ? new Some<T>(f.apply(this.toDirectory())) : None$.MODULE$;
    }

    public boolean canRead() {
        return this.jfile().canRead();
    }

    public boolean canWrite() {
        return this.jfile().canWrite();
    }

    public boolean exists() {
        boolean bl;
        if (Statistics$.MODULE$.canEnable()) {
            Statistics.Counter counter = IOStats$.MODULE$.fileExistsCount();
            if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                counter.value_$eq(counter.value() + 1);
            }
        }
        try {
            bl = this.jfile().exists();
        }
        catch (SecurityException securityException) {
            bl = false;
        }
        return bl;
    }

    public boolean isFile() {
        boolean bl;
        if (Statistics$.MODULE$.canEnable()) {
            Statistics.Counter counter = IOStats$.MODULE$.fileIsFileCount();
            if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                counter.value_$eq(counter.value() + 1);
            }
        }
        try {
            bl = this.jfile().isFile();
        }
        catch (SecurityException securityException) {
            bl = false;
        }
        return bl;
    }

    public boolean isDirectory() {
        boolean bl;
        if (Statistics$.MODULE$.canEnable()) {
            Statistics.Counter counter = IOStats$.MODULE$.fileIsDirectoryCount();
            if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                counter.value_$eq(counter.value() + 1);
            }
        }
        try {
            bl = this.jfile().isDirectory();
        }
        catch (SecurityException securityException) {
            String string2 = this.jfile().getPath();
            bl = string2 != null && string2.equals(".");
        }
        return bl;
    }

    public boolean isAbsolute() {
        return this.jfile().isAbsolute();
    }

    public boolean isEmpty() {
        return this.path().length() == 0;
    }

    public long lastModified() {
        return this.jfile().lastModified();
    }

    public long length() {
        return this.jfile().length();
    }

    public boolean endsWith(Path other) {
        return this.segments().endsWith(other.segments());
    }

    public boolean isSame(Path other) {
        Path path = this.toCanonical();
        Path path2 = other.toCanonical();
        return !(path != null ? !((Object)path).equals(path2) : path2 != null);
    }

    public boolean isFresher(Path other) {
        return this.lastModified() > other.lastModified();
    }

    public Directory createDirectory(boolean force, boolean failIfExists) {
        boolean res;
        boolean bl = res = force ? this.jfile().mkdirs() : this.jfile().mkdir();
        if (!res && failIfExists && this.exists()) {
            Predef$ predef$ = Predef$.MODULE$;
            throw Path$.MODULE$.fail(new StringOps("Directory '%s' already exists.").format(Predef$.MODULE$.genericWrapArray(new Object[]{this.name()})));
        }
        return this.isDirectory() ? this.toDirectory() : new Directory(this.jfile());
    }

    public boolean createDirectory$default$1() {
        return true;
    }

    public boolean createDirectory$default$2() {
        return false;
    }

    public File createFile(boolean failIfExists) {
        boolean res = this.jfile().createNewFile();
        if (!res && failIfExists && this.exists()) {
            Predef$ predef$ = Predef$.MODULE$;
            throw Path$.MODULE$.fail(new StringOps("File '%s' already exists.").format(Predef$.MODULE$.genericWrapArray(new Object[]{this.name()})));
        }
        return this.isFile() ? this.toFile() : new File(this.jfile(), Codec$.MODULE$.fallbackSystemCodec());
    }

    public boolean createFile$default$1() {
        return false;
    }

    public boolean delete() {
        return this.jfile().delete();
    }

    public boolean deleteRecursively() {
        return this.scala$reflect$io$Path$$deleteRecursively(this.jfile());
    }

    public boolean scala$reflect$io$Path$$deleteRecursively(java.io.File f) {
        if (f.isDirectory()) {
            java.io.File[] fileArray = f.listFiles();
            if (fileArray == null) {
            } else {
                Predef$.MODULE$.refArrayOps((Object[])fileArray).foreach(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Path $outer;

                    public final boolean apply(java.io.File f) {
                        return this.$outer.scala$reflect$io$Path$$deleteRecursively(f);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }
        }
        return f.delete();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean truncate() {
        if (!this.isFile()) return false;
        RandomAccessFile raf = new RandomAccessFile(this.jfile(), "rw");
        raf.setLength(0L);
        raf.close();
        if (this.length() != 0L) return false;
        return true;
    }

    public String toString() {
        return this.path();
    }

    public boolean equals(Object other) {
        boolean bl;
        if (other instanceof Path) {
            Path path = (Path)other;
            String string2 = this.path();
            String string3 = path.path();
            bl = !(string2 != null ? !string2.equals(string3) : string3 != null);
        } else {
            bl = false;
        }
        return bl;
    }

    public int hashCode() {
        return this.path().hashCode();
    }

    private final String createRelativePath$1(List baseSegs, List otherSegs) {
        Tuple2<List, List> tuple2;
        while ((tuple2 = new Tuple2<List, List>(baseSegs, otherSegs))._1() instanceof $colon$colon) {
            $colon$colon $colon$colon = ($colon$colon)tuple2._1();
            if (!(tuple2._2() instanceof $colon$colon)) break;
            $colon$colon $colon$colon2 = ($colon$colon)tuple2._2();
            Object b = $colon$colon2.head();
            Object b2 = $colon$colon.head();
            boolean bl = b2 != b ? (b2 != null ? (!(b2 instanceof Number) ? (!(b2 instanceof Character) ? b2.equals(b) : BoxesRunTime.equalsCharObject((Character)b2, b)) : BoxesRunTime.equalsNumObject((Number)b2, b)) : false) : true;
            if (!bl) break;
            otherSegs = $colon$colon2.tl$1();
            baseSegs = $colon$colon.tl$1();
        }
        String string2 = new StringBuilder().append((Object)"..").append(BoxesRunTime.boxToCharacter(this.separator())).toString();
        Predef$ predef$ = Predef$.MODULE$;
        return new StringBuilder().append((Object)new StringOps(string2).$times(((LinearSeqOptimized)tuple2._1()).length())).append((Object)((TraversableOnce)tuple2._2()).mkString(this.separatorStr())).toString();
    }

    public Path(java.io.File jfile) {
        this.jfile = jfile;
        this.separator = java.io.File.separatorChar;
        this.separatorStr = java.io.File.separator;
    }
}

