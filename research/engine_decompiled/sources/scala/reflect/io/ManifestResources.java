/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import scala.Function1;
import scala.None$;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.convert.WrapAsScala$;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.ManifestResources$;
import scala.reflect.io.ZipArchive;

@ScalaSignature(bytes="\u0006\u0001\t4A!\u0001\u0002\u0003\u0013\t\tR*\u00198jM\u0016\u001cHOU3t_V\u00148-Z:\u000b\u0005\r!\u0011AA5p\u0015\t)a!A\u0004sK\u001adWm\u0019;\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\u0015A\u00111\u0002D\u0007\u0002\u0005%\u0011QB\u0001\u0002\u000b5&\u0004\u0018I]2iSZ,\u0007\u0002C\b\u0001\u0005\u000b\u0007I\u0011\u0001\t\u0002\u0007U\u0014H.F\u0001\u0012!\t\u0011r#D\u0001\u0014\u0015\t!R#A\u0002oKRT\u0011AF\u0001\u0005U\u00064\u0018-\u0003\u0002\u0019'\t\u0019QK\u0015'\t\u0011i\u0001!\u0011!Q\u0001\nE\tA!\u001e:mA!)A\u0004\u0001C\u0001;\u00051A(\u001b8jiz\"\"AH\u0010\u0011\u0005-\u0001\u0001\"B\b\u001c\u0001\u0004\t\u0002\"B\u0011\u0001\t\u0003\u0011\u0013\u0001C5uKJ\fGo\u001c:\u0016\u0003\r\u00022\u0001\n\u0015,\u001d\t)c%D\u0001\u0007\u0013\t9c!A\u0004qC\u000e\\\u0017mZ3\n\u0005%R#\u0001C%uKJ\fGo\u001c:\u000b\u0005\u001d2\u0001CA\u0006-\u0013\ti#A\u0001\u0007BEN$(/Y2u\r&dW\rC\u00030\u0001\u0011\u0005\u0001'\u0001\u0003oC6,W#A\u0019\u0011\u0005I*dBA\u00134\u0013\t!d!\u0001\u0004Qe\u0016$WMZ\u0005\u0003m]\u0012aa\u0015;sS:<'B\u0001\u001b\u0007\u0011\u0015I\u0004\u0001\"\u00011\u0003\u0011\u0001\u0018\r\u001e5\t\u000bm\u0002A\u0011\u0001\u001f\u0002\u000b%t\u0007/\u001e;\u0016\u0003u\u0002\"A\u0010!\u000e\u0003}R!aA\u000b\n\u0005\u0005{$aC%oaV$8\u000b\u001e:fC6DQa\u0011\u0001\u0005\u0002\u0011\u000bA\u0002\\1ti6{G-\u001b4jK\u0012,\u0012!\u0012\t\u0003K\u0019K!a\u0012\u0004\u0003\t1{gn\u001a\u0005\u0006\u0013\u0002!\tES\u0001\tG\u0006tW)];bYR\u00111J\u0014\t\u0003K1K!!\u0014\u0004\u0003\u000f\t{w\u000e\\3b]\")q\n\u0013a\u0001!\u0006)q\u000e\u001e5feB\u0011Q%U\u0005\u0003%\u001a\u00111!\u00118z\u0011\u0015!\u0006\u0001\"\u0011V\u0003!A\u0017m\u001d5D_\u0012,G#\u0001,\u0011\u0005\u0015:\u0016B\u0001-\u0007\u0005\rIe\u000e\u001e\u0005\u00065\u0002!\teW\u0001\u0007KF,\u0018\r\\:\u0015\u0005-c\u0006\"B/Z\u0001\u0004\u0001\u0016\u0001\u0002;iCRDQa\u0018\u0001\u0005\n\u0001\f1C]3t_V\u00148-Z%oaV$8\u000b\u001e:fC6$\"!P1\t\u000ber\u0006\u0019A\u0019")
public final class ManifestResources
extends ZipArchive {
    private final URL url;

    public URL url() {
        return this.url;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public Iterator<AbstractFile> iterator() {
        Iterator<AbstractFile> iterator2;
        ZipArchive.DirEntry root = new ZipArchive.DirEntry(this, "/");
        Tuple2[] tuple2Array = new Tuple2[1];
        String string2 = Predef$.MODULE$.ArrowAssoc("/");
        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[0] = new Tuple2<String, ZipArchive.DirEntry>(string2, root);
        HashMap dirs2 = (HashMap)HashMap$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array));
        Manifest manifest = new Manifest(this.input());
        Iterator<ZipEntry> iter2 = WrapAsScala$.MODULE$.asScalaIterator(manifest.getEntries().keySet().iterator()).filter((Function1<String, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(String x$2) {
                return x$2.endsWith(".class");
            }
        })).map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final ZipEntry apply(String x$3) {
                return new ZipEntry(x$3);
            }
        });
        iter2.foreach(new Serializable(this, dirs2){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ ManifestResources $outer;
            private final HashMap dirs$2;

            public final void apply(ZipEntry zipEntry) {
                ZipArchive.DirEntry dir = this.$outer.getDir(this.dirs$2, zipEntry);
                if (!zipEntry.isDirectory()) {
                    public class Scala_reflect_io_ManifestResources$$anonfun$iterator$1$FileEntry$3
                    extends ZipArchive.Entry {
                        public final /* synthetic */ $anonfun$iterator$1 $outer;
                        private final ZipEntry zipEntry$3;

                        public long lastModified() {
                            return this.zipEntry$3.getTime();
                        }

                        public InputStream input() {
                            String string2 = this.path();
                            ManifestResources manifestResources = this.scala$reflect$io$ManifestResources$$anonfun$FileEntry$$$outer().$outer;
                            return new FilterInputStream(manifestResources, string2){
                                private final String path$1;

                                public int read() {
                                    if (this.in == null) {
                                        this.in = Thread.currentThread().getContextClassLoader().getResourceAsStream(this.path$1);
                                    }
                                    if (this.in == null) {
                                        throw new RuntimeException(new StringBuilder().append((Object)this.path$1).append((Object)" not found").toString());
                                    }
                                    return super.read();
                                }

                                public void close() {
                                    super.close();
                                    this.in = null;
                                }
                                {
                                    this.path$1 = path$1;
                                    super(null);
                                }
                            };
                        }

                        public None$ sizeOption() {
                            return None$.MODULE$;
                        }

                        public /* synthetic */ $anonfun$iterator$1 scala$reflect$io$ManifestResources$$anonfun$FileEntry$$$outer() {
                            return this.$outer;
                        }

                        public Scala_reflect_io_ManifestResources$$anonfun$iterator$1$FileEntry$3($anonfun$iterator$1 $outer, ZipEntry zipEntry$3) {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.zipEntry$3 = zipEntry$3;
                            super($outer.$outer, zipEntry$3.getName());
                        }
                    }
                    Scala_reflect_io_ManifestResources$$anonfun$iterator$1$FileEntry$3 f = new Scala_reflect_io_ManifestResources$$anonfun$iterator$1$FileEntry$3(this, zipEntry);
                    dir.entries().update(f.name(), f);
                }
            }

            public /* synthetic */ ManifestResources scala$reflect$io$ManifestResources$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.dirs$2 = dirs$2;
            }
        });
        try {
            iterator2 = root.iterator();
            dirs2.clear();
        }
        catch (Throwable throwable) {
            void var6_4;
            var6_4.clear();
            throw throwable;
        }
        return iterator2;
    }

    @Override
    public String name() {
        return this.path();
    }

    @Override
    public String path() {
        String s2 = this.url().getPath();
        int n = s2.lastIndexOf(33);
        return s2.substring(0, n);
    }

    @Override
    public InputStream input() {
        return this.url().openStream();
    }

    @Override
    public long lastModified() {
        long l;
        try {
            l = this.url().openConnection().getLastModified();
        }
        catch (IOException iOException) {
            l = 0L;
        }
        return l;
    }

    @Override
    public boolean canEqual(Object other) {
        return other instanceof ManifestResources;
    }

    public int hashCode() {
        return this.url().hashCode();
    }

    @Override
    public boolean equals(Object that) {
        boolean bl;
        if (that instanceof ManifestResources) {
            ManifestResources manifestResources = (ManifestResources)that;
            URL uRL = this.url();
            URL uRL2 = manifestResources.url();
            bl = !(uRL != null ? !((Object)uRL).equals(uRL2) : uRL2 != null);
        } else {
            bl = false;
        }
        return bl;
    }

    public InputStream scala$reflect$io$ManifestResources$$resourceInputStream(String path) {
        return new /* invalid duplicate definition of identical inner class */;
    }

    public ManifestResources(URL url) {
        this.url = url;
        super(null);
    }
}

