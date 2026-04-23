/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.EnumSet;
import java.util.Locale;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import scala.None$;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.JavaConverters$;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.io.JavaToolsPlatformArchive$;
import scala.reflect.io.ZipArchive;
import scala.runtime.Nothing$;
import scala.runtime.Null$;

@ScalaSignature(bytes="\u0006\u0001Y3A!\u0001\u0002\u0003\u0013\tA\"*\u0019<b)>|Gn\u001d)mCR4wN]7Be\u000eD\u0017N^3\u000b\u0005\r!\u0011AA5p\u0015\t)a!A\u0004sK\u001adWm\u0019;\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\u0015A\u00111\u0002D\u0007\u0002\u0005%\u0011QB\u0001\u0002\u000b5&\u0004\u0018I]2iSZ,\u0007\"B\b\u0001\t\u0003\u0001\u0012A\u0002\u001fj]&$h\bF\u0001\u0012!\tY\u0001\u0001C\u0003\u0014\u0001\u0011\u0005A#\u0001\u0005ji\u0016\u0014\u0018\r^8s+\u0005)\u0002c\u0001\f\u001b;9\u0011q\u0003G\u0007\u0002\r%\u0011\u0011DB\u0001\ba\u0006\u001c7.Y4f\u0013\tYBD\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\tIb\u0001\u0005\u0002\u001f?5\t\u0001!\u0003\u0002!\u0019\t)QI\u001c;ss\")!\u0005\u0001C\u0001G\u0005!a.Y7f+\u0005!\u0003CA\u0013+\u001b\u00051#BA\u0014)\u0003\u0011a\u0017M\\4\u000b\u0003%\nAA[1wC&\u00111F\n\u0002\u0007'R\u0014\u0018N\\4\t\u000b5\u0002A\u0011A\u0012\u0002\tA\fG\u000f\u001b\u0005\u0006_\u0001!\t\u0001M\u0001\u0006S:\u0004X\u000f^\u000b\u0002cA\u0011qCM\u0005\u0003g\u0019\u0011qAT8uQ&tw\rC\u00036\u0001\u0011\u0005a'\u0001\u0007mCN$Xj\u001c3jM&,G-F\u00018!\t9\u0002(\u0003\u0002:\r\t!Aj\u001c8h\u0011\u0015Y\u0004\u0001\"\u0011=\u0003)\u0019\u0018N_3PaRLwN\\\u000b\u0002{9\u0011qCP\u0005\u0003\u007f\u0019\tAAT8oK\")\u0011\t\u0001C!\u0005\u0006A1-\u00198FcV\fG\u000e\u0006\u0002D\rB\u0011q\u0003R\u0005\u0003\u000b\u001a\u0011qAQ8pY\u0016\fg\u000eC\u0003H\u0001\u0002\u0007\u0001*A\u0003pi\",'\u000f\u0005\u0002\u0018\u0013&\u0011!J\u0002\u0002\u0004\u0003:L\b\"\u0002'\u0001\t\u0003j\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u00039\u0003\"aF(\n\u0005A3!aA%oi\")!\u000b\u0001C!'\u00061Q-];bYN$\"a\u0011+\t\u000bU\u000b\u0006\u0019\u0001%\u0002\tQD\u0017\r\u001e")
public final class JavaToolsPlatformArchive
extends ZipArchive {
    @Override
    public Iterator<ZipArchive.Entry> iterator() {
        StandardJavaFileManager fileManager = ToolProvider.getSystemJavaCompiler().getStandardFileManager(new DiagnosticCollector(), Locale.getDefault(), Charset.defaultCharset());
        ZipArchive.DirEntry root = new ZipArchive.DirEntry(this, "/");
        Tuple2[] tuple2Array = new Tuple2[1];
        String string2 = Predef$.MODULE$.ArrowAssoc("/");
        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[0] = new Tuple2<String, ZipArchive.DirEntry>(string2, root);
        HashMap dirs2 = (HashMap)HashMap$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array));
        Iterable<JavaFileObject> files2 = fileManager.list(StandardLocation.PLATFORM_CLASS_PATH, "", EnumSet.of(JavaFileObject.Kind.CLASS), true);
        JavaConverters$.MODULE$.asScalaIteratorConverter(files2.iterator()).asScala().foreach(new Serializable(this, fileManager, root, dirs2){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ JavaToolsPlatformArchive $outer;
            private final StandardJavaFileManager fileManager$1;
            private final ZipArchive.DirEntry root$1;
            private final HashMap dirs$3;

            public final void apply(JavaFileObject f) {
                Tuple2<String, String> tuple2;
                String binaryName = this.fileManager$1.inferBinaryName(StandardLocation.PLATFORM_CLASS_PATH, f);
                String relativePath = new StringBuilder().append((Object)binaryName.replace('.', '/')).append((Object)".class").toString();
                int n = relativePath.lastIndexOf(47);
                switch (n) {
                    default: {
                        tuple2 = new Tuple2<String, String>(relativePath.substring(0, n + 1), relativePath.substring(n + 1));
                        break;
                    }
                    case -1: {
                        tuple2 = new Tuple2<String, String>("", binaryName);
                    }
                }
                Tuple2<String, String> tuple22 = tuple2;
                Tuple2<T1, T2> tuple23 = new Tuple2<T1, T2>(tuple22._1(), tuple22._2());
                String packNameDotted = (String)tuple23._1();
                String simpleName = (String)tuple23._2();
                ZipArchive.DirEntry dir = packNameDotted.isEmpty() ? this.root$1 : this.$outer.ensureDir(this.dirs$3, packNameDotted, null);
                public class Scala_reflect_io_JavaToolsPlatformArchive$$anonfun$iterator$2$FileEntry$4
                extends ZipArchive.Entry {
                    public final /* synthetic */ $anonfun$iterator$2 $outer;
                    private final JavaFileObject f$1;

                    public Null$ getArchive() {
                        return null;
                    }

                    public long lastModified() {
                        return 0L;
                    }

                    public InputStream input() {
                        return this.f$1.openInputStream();
                    }

                    public None$ sizeOption() {
                        return None$.MODULE$;
                    }

                    public /* synthetic */ $anonfun$iterator$2 scala$reflect$io$JavaToolsPlatformArchive$$anonfun$FileEntry$$$outer() {
                        return this.$outer;
                    }

                    public Scala_reflect_io_JavaToolsPlatformArchive$$anonfun$iterator$2$FileEntry$4($anonfun$iterator$2 $outer, String relativePath$1, JavaFileObject f$1) {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.f$1 = f$1;
                        super($outer.$outer, relativePath$1);
                    }
                }
                Scala_reflect_io_JavaToolsPlatformArchive$$anonfun$iterator$2$FileEntry$4 entry = new Scala_reflect_io_JavaToolsPlatformArchive$$anonfun$iterator$2$FileEntry$4(this, relativePath, f);
                dir.entries().update(simpleName, entry);
            }

            public /* synthetic */ JavaToolsPlatformArchive scala$reflect$io$JavaToolsPlatformArchive$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.fileManager$1 = fileManager$1;
                this.root$1 = root$1;
                this.dirs$3 = dirs$3;
            }
        });
        return root.iterator();
    }

    @Override
    public String name() {
        return this.file().getName();
    }

    @Override
    public String path() {
        return this.file().getPath();
    }

    public Nothing$ input() {
        return this.unsupported();
    }

    @Override
    public long lastModified() {
        return this.file().lastModified();
    }

    public None$ sizeOption() {
        return None$.MODULE$;
    }

    @Override
    public boolean canEqual(Object other) {
        return other instanceof JavaToolsPlatformArchive;
    }

    public int hashCode() {
        return this.file().hashCode();
    }

    @Override
    public boolean equals(Object that) {
        boolean bl = that instanceof JavaToolsPlatformArchive;
        return bl;
    }

    public JavaToolsPlatformArchive() {
        super(new File(System.getProperty("java.home")));
    }
}

