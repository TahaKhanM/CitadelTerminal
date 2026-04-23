/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import java.io.InputStream;
import java.net.URL;
import scala.Function0;
import scala.Option;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.HasClassPath;
import scala.reflect.internal.util.ScalaClassLoader$class;

@ScalaSignature(bytes="\u0006\u0001\u00055haB\u0001\u0003!\u0003\r\ta\u0003\u0002\u0011'\u000e\fG.Y\"mCN\u001cHj\\1eKJT!a\u0001\u0003\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u000b\u0019\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u000f!\tqA]3gY\u0016\u001cGOC\u0001\n\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0007\u0011\u00055\u0011R\"\u0001\b\u000b\u0005=\u0001\u0012\u0001\u00027b]\u001eT\u0011!E\u0001\u0005U\u00064\u0018-\u0003\u0002\u0014\u001d\tY1\t\\1tg2{\u0017\rZ3s\u0011\u0015)\u0002\u0001\"\u0001\u0017\u0003\u0019!\u0013N\\5uIQ\tq\u0003\u0005\u0002\u001935\t\u0001\"\u0003\u0002\u001b\u0011\t!QK\\5u\u0011\u0015a\u0002\u0001\"\u0001\u001e\u0003%\t7oQ8oi\u0016DH/\u0006\u0002\u001fCQ\u0011qD\u000b\t\u0003A\u0005b\u0001\u0001B\u0003#7\t\u00071EA\u0001U#\t!s\u0005\u0005\u0002\u0019K%\u0011a\u0005\u0003\u0002\b\u001d>$\b.\u001b8h!\tA\u0002&\u0003\u0002*\u0011\t\u0019\u0011I\\=\t\r-ZB\u00111\u0001-\u0003\u0019\t7\r^5p]B\u0019\u0001$L\u0010\n\u00059B!\u0001\u0003\u001fcs:\fW.\u001a \t\u000bA\u0002A\u0011\u0001\f\u0002\u0019M,G/Q:D_:$X\r\u001f;\t\u000bI\u0002A\u0011A\u001a\u0002\u001dQ\u0014\u0018\u0010V8M_\u0006$7\t\\1tgV\u0011A\u0007\u0011\u000b\u0003k\u0015\u00032\u0001\u0007\u001c9\u0013\t9\u0004B\u0001\u0004PaRLwN\u001c\t\u0004sqzdB\u0001\r;\u0013\tY\u0004\"\u0001\u0004Qe\u0016$WMZ\u0005\u0003{y\u0012Qa\u00117bgNT!a\u000f\u0005\u0011\u0005\u0001\u0002E!\u0002\u00122\u0005\u0004\t\u0015C\u0001\u0013C!\tA2)\u0003\u0002E\u0011\t1\u0011I\\=SK\u001aDQAR\u0019A\u0002\u001d\u000bA\u0001]1uQB\u0011\u0011\bS\u0005\u0003\u0013z\u0012aa\u0015;sS:<\u0007\"B&\u0001\t\u0003a\u0015\u0001\u0006;ssR{\u0017J\\5uS\u0006d\u0017N_3DY\u0006\u001c8/\u0006\u0002N#R\u0011aJ\u0015\t\u00041Yz\u0005cA\u001d=!B\u0011\u0001%\u0015\u0003\u0006E)\u0013\r!\u0011\u0005\u0006\r*\u0003\ra\u0012\u0005\u0006)\u0002!I!V\u0001\tiJL8\t\\1tgV\u0011aK\u0017\u000b\u0004/nc\u0006c\u0001\r71B\u0019\u0011\bP-\u0011\u0005\u0001RF!\u0002\u0012T\u0005\u0004\t\u0005\"\u0002$T\u0001\u00049\u0005\"B/T\u0001\u0004q\u0016AC5oSRL\u0017\r\\5{KB\u0011\u0001dX\u0005\u0003A\"\u0011qAQ8pY\u0016\fg\u000eC\u0003c\u0001\u0011\u00051-\u0001\u0004de\u0016\fG/\u001a\u000b\u0003\u0005\u0012DQAR1A\u0002\u001dCQA\u001a\u0001\u0005\u0002\u001d\f!b\u00197bgN\u0014\u0015\u0010^3t)\tAg\u000eE\u0002\u0019S.L!A\u001b\u0005\u0003\u000b\u0005\u0013(/Y=\u0011\u0005aa\u0017BA7\t\u0005\u0011\u0011\u0015\u0010^3\t\u000b=,\u0007\u0019A$\u0002\u0013\rd\u0017m]:OC6,\u0007\"B9\u0001\t\u0003\u0011\u0018!D2mCN\u001c\u0018i]*ue\u0016\fW\u000e\u0006\u0002tsB\u0011Ao^\u0007\u0002k*\u0011a\u000fE\u0001\u0003S>L!\u0001_;\u0003\u0017%s\u0007/\u001e;TiJ,\u0017-\u001c\u0005\u0006_B\u0004\ra\u0012\u0005\u0006w\u0002!\t\u0001`\u0001\u0004eVtGcA\f~\u007f\")aP\u001fa\u0001\u000f\u0006QqN\u00196fGRt\u0015-\\3\t\u000f\u0005\u0005!\u00101\u0001\u0002\u0004\u0005I\u0011M]4v[\u0016tGo\u001d\t\u0006\u0003\u000b\tYa\u0012\b\u00041\u0005\u001d\u0011bAA\u0005\u0011\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\u0007\u0003\u001f\u00111aU3r\u0015\r\tI\u0001C\u0004\b\u0003'\u0011\u0001\u0012AA\u000b\u0003A\u00196-\u00197b\u00072\f7o\u001d'pC\u0012,'\u000f\u0005\u0003\u0002\u0018\u0005eQ\"\u0001\u0002\u0007\r\u0005\u0011\u0001\u0012AA\u000e'\r\tIB\u0011\u0005\t\u0003?\tI\u0002\"\u0001\u0002\"\u00051A(\u001b8jiz\"\"!!\u0006\t\u0011\u0005\u0015\u0012\u0011\u0004C\u0002\u0003O\tQ!\u00199qYf$B!!\u000b\u0002,A\u0019\u0011q\u0003\u0001\t\u000f\u00055\u00121\u0005a\u0001\u0019\u0005\u00111\r\u001c\u0005\t\u0003c\tI\u0002\"\u0001\u00024\u0005i1m\u001c8uKb$Hj\\1eKJ,\"!!\u000b\t\u0011\u0005]\u0012\u0011\u0004C\u0001\u0003g\t\u0011\"\u00199q\u0019>\fG-\u001a:\t\u0011\u0005m\u0012\u0011\u0004C\u0001\u0003{\t!b]3u\u0007>tG/\u001a=u)\r9\u0012q\b\u0005\b\u0003[\tI\u00041\u0001\r\u0011!\t\u0019%!\u0007\u0005\u0002\u0005\u0015\u0013aE:bm&twmQ8oi\u0016DH\u000fT8bI\u0016\u0014X\u0003BA$\u0003\u0017\"B!!\u0013\u0002NA\u0019\u0001%a\u0013\u0005\r\t\n\tE1\u0001$\u0011%\ty%!\u0011\u0005\u0002\u0004\t\t&\u0001\u0003c_\u0012L\b\u0003\u0002\r.\u0003\u00132q!!\u0016\u0002\u001a\u0001\t9F\u0001\bV%2\u001bE.Y:t\u0019>\fG-\u001a:\u0014\u0011\u0005M\u0013\u0011LA\u0015\u0003G\u0002B!a\u0017\u0002b5\u0011\u0011Q\f\u0006\u0004\u0003?\u0002\u0012a\u00018fi&!\u0011QKA/!\u0011\t9\"!\u001a\n\u0007\u0005\u001d$A\u0001\u0007ICN\u001cE.Y:t!\u0006$\b\u000eC\u0006\u0002l\u0005M#\u0011!Q\u0001\n\u00055\u0014\u0001B;sYN\u0004b!!\u0002\u0002\f\u0005=\u0004\u0003BA.\u0003cJA!a\u001d\u0002^\t\u0019QK\u0015'\t\u0015\u0005]\u00141\u000bB\u0001B\u0003%A\"\u0001\u0004qCJ,g\u000e\u001e\u0005\t\u0003?\t\u0019\u0006\"\u0001\u0002|Q1\u0011QPAA\u0003\u0007\u0003B!a \u0002T5\u0011\u0011\u0011\u0004\u0005\t\u0003W\nI\b1\u0001\u0002n!9\u0011qOA=\u0001\u0004a\u0001BCAD\u0003'\u0002\r\u0011\"\u0003\u0002\n\u0006y1\r\\1tg2|\u0017\rZ3s+Jc5/\u0006\u0002\u0002n!Q\u0011QRA*\u0001\u0004%I!a$\u0002'\rd\u0017m]:m_\u0006$WM]+S\u0019N|F%Z9\u0015\u0007]\t\t\n\u0003\u0006\u0002\u0014\u0006-\u0015\u0011!a\u0001\u0003[\n1\u0001\u001f\u00132\u0011%\t9*a\u0015!B\u0013\ti'\u0001\tdY\u0006\u001c8\u000f\\8bI\u0016\u0014XK\u0015'tA!A\u00111TA*\t\u0003\tI)A\u0007dY\u0006\u001c8\u000fU1uQV\u0013Fj\u001d\u0005\t\u0003?\u000b\u0019\u0006\"\u0011\u0002\"\u00061\u0011\r\u001a3V%2#2aFAR\u0011!\t)+!(A\u0002\u0005=\u0014aA;sY\"A\u0011\u0011VA\r\t\u0003\tY+\u0001\u0005ge>lWK\u0015't)\u0019\ti(!,\u00020\"A\u00111NAT\u0001\u0004\ti\u0007C\u0005\u0002x\u0005\u001d\u0006\u0013!a\u0001\u0019!A\u00111WA\r\t\u0003\t),A\u0006dY\u0006\u001c8/\u0012=jgR\u001cH#\u00020\u00028\u0006e\u0006\u0002CA6\u0003c\u0003\r!!\u001c\t\u000f\u0005m\u0016\u0011\u0017a\u0001\u000f\u0006!a.Y7f\u0011!\ty,!\u0007\u0005\u0002\u0005\u0005\u0017!D8sS\u001eLgn\u00144DY\u0006\u001c8\u000f\u0006\u0003\u0002D\u0006\u0015\u0007\u0003\u0002\r7\u0003_B\u0001\"a2\u0002>\u0002\u0007\u0011\u0011Z\u0001\u0002qB\"\u00111ZAh!\u0011ID(!4\u0011\u0007\u0001\ny\rB\u0006\u0002R\u0006\u0015\u0017\u0011!A\u0001\u0006\u0003\u0019#aA0%c!Q\u0011Q[A\r#\u0003%\t!a6\u0002%\u0019\u0014x.\\+S\u0019N$C-\u001a4bk2$HEM\u000b\u0003\u00033T3\u0001DAnW\t\ti\u000e\u0005\u0003\u0002`\u0006%XBAAq\u0015\u0011\t\u0019/!:\u0002\u0013Ut7\r[3dW\u0016$'bAAt\u0011\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005-\u0018\u0011\u001d\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007")
public interface ScalaClassLoader {
    public <T> T asContext(Function0<T> var1);

    public void setAsContext();

    public <T> Option<Class<T>> tryToLoadClass(String var1);

    public <T> Option<Class<T>> tryToInitializeClass(String var1);

    public Object create(String var1);

    public byte[] classBytes(String var1);

    public InputStream classAsStream(String var1);

    public void run(String var1, Seq<String> var2);

    public static class URLClassLoader
    extends java.net.URLClassLoader
    implements ScalaClassLoader,
    HasClassPath {
        private Seq<URL> classloaderURLs;

        @Override
        public <T> T asContext(Function0<T> action) {
            return (T)ScalaClassLoader$class.asContext(this, action);
        }

        @Override
        public void setAsContext() {
            ScalaClassLoader$class.setAsContext(this);
        }

        @Override
        public <T> Option<Class<T>> tryToLoadClass(String path) {
            return ScalaClassLoader$class.tryToLoadClass(this, path);
        }

        @Override
        public <T> Option<Class<T>> tryToInitializeClass(String path) {
            return ScalaClassLoader$class.tryToInitializeClass(this, path);
        }

        @Override
        public Object create(String path) {
            return ScalaClassLoader$class.create(this, path);
        }

        @Override
        public byte[] classBytes(String className) {
            return ScalaClassLoader$class.classBytes(this, className);
        }

        @Override
        public InputStream classAsStream(String className) {
            return ScalaClassLoader$class.classAsStream(this, className);
        }

        @Override
        public void run(String objectName, Seq<String> arguments) {
            ScalaClassLoader$class.run(this, objectName, arguments);
        }

        private Seq<URL> classloaderURLs() {
            return this.classloaderURLs;
        }

        private void classloaderURLs_$eq(Seq<URL> x$1) {
            this.classloaderURLs = x$1;
        }

        @Override
        public Seq<URL> classPathURLs() {
            return this.classloaderURLs();
        }

        @Override
        public void addURL(URL url) {
            this.classloaderURLs_$eq(this.classloaderURLs().$colon$plus(url, Seq$.MODULE$.canBuildFrom()));
            super.addURL(url);
        }

        public URLClassLoader(Seq<URL> urls, ClassLoader parent) {
            super((URL[])urls.toArray(ClassTag$.MODULE$.apply(URL.class)), parent);
            ScalaClassLoader$class.$init$(this);
            this.classloaderURLs = urls;
        }
    }
}

