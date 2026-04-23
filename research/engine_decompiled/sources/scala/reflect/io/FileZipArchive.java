/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap$;
import scala.collection.mutable.StringBuilder;
import scala.io.Codec$;
import scala.reflect.ScalaSignature;
import scala.reflect.io.File$;
import scala.reflect.io.FileZipArchive;
import scala.reflect.io.Path$;
import scala.reflect.io.ZipArchive;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001\u0005\u0015a\u0001B\u0001\u0003\u0005%\u0011aBR5mKjK\u0007/\u0011:dQ&4XM\u0003\u0002\u0004\t\u0005\u0011\u0011n\u001c\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-aQ\"\u0001\u0002\n\u00055\u0011!A\u0003.ja\u0006\u00138\r[5wK\"Iq\u0002\u0001B\u0001B\u0003%\u0001cF\u0001\u0005M&dW\r\u0005\u0002\u0012+5\t!C\u0003\u0002\u0004')\tA#\u0001\u0003kCZ\f\u0017B\u0001\f\u0013\u0005\u00111\u0015\u000e\\3\n\u0005=a\u0001\"B\r\u0001\t\u0003Q\u0012A\u0002\u001fj]&$h\b\u0006\u0002\u001c9A\u00111\u0002\u0001\u0005\u0006\u001fa\u0001\r\u0001\u0005\u0005\u000b=\u0001\u0001\n\u0011cb!\n\u0013y\u0012a\u0001=%cU\t\u0001\u0005\u0005\u0003\"E\u0011BS\"\u0001\u0004\n\u0005\r2!A\u0002+va2,'\u0007\u0005\u0002&M5\t\u0001!\u0003\u0002(\u0019\tAA)\u001b:F]R\u0014\u0018\u0010\u0005\u0003*]A\"S\"\u0001\u0016\u000b\u0005-b\u0013aB7vi\u0006\u0014G.\u001a\u0006\u0003[\u0019\t!bY8mY\u0016\u001cG/[8o\u0013\ty#FA\u0004ICNDW*\u00199\u0011\u0005E\"dBA\u00113\u0013\t\u0019d!\u0001\u0004Qe\u0016$WMZ\u0005\u0003kY\u0012aa\u0015;sS:<'BA\u001a\u0007\u0011!A\u0004\u0001#A!B\u0013\u0001\u0013\u0001\u0002=%c\u0001B\u0001B\u000f\u0001\t\u0006\u0004%\taO\u0001\u0005e>|G/F\u0001%\u0011!i\u0004\u0001#A!B\u0013!\u0013!\u0002:p_R\u0004\u0003\u0002C \u0001\u0011\u000b\u0007I\u0011\u0001!\u0002\u000f\u0005dG\u000eR5sgV\t\u0001\u0006\u0003\u0005C\u0001!\u0005\t\u0015)\u0003)\u0003!\tG\u000e\u001c#jeN\u0004\u0003\"\u0002#\u0001\t\u0003)\u0015\u0001C5uKJ\fGo\u001c:\u0016\u0003\u0019\u00032a\u0012&N\u001d\t\t\u0003*\u0003\u0002J\r\u00059\u0001/Y2lC\u001e,\u0017BA&M\u0005!IE/\u001a:bi>\u0014(BA%\u0007!\t)c*\u0003\u0002P\u0019\t)QI\u001c;ss\")\u0011\u000b\u0001C\u0001%\u0006!a.Y7f+\u0005\u0019\u0006C\u0001+X\u001b\u0005)&B\u0001,\u0014\u0003\u0011a\u0017M\\4\n\u0005U*\u0006\"B-\u0001\t\u0003\u0011\u0016\u0001\u00029bi\"DQa\u0017\u0001\u0005\u0002q\u000bQ!\u001b8qkR,\u0012!\u0018\t\u0003#yK!a\u0018\n\u0003\u001f\u0019KG.Z%oaV$8\u000b\u001e:fC6DQ!\u0019\u0001\u0005\u0002\t\fA\u0002\\1ti6{G-\u001b4jK\u0012,\u0012a\u0019\t\u0003C\u0011L!!\u001a\u0004\u0003\t1{gn\u001a\u0005\u0006O\u0002!\t\u0005[\u0001\u000bg&TXm\u00149uS>tW#A5\u0011\u0007\u0005RG.\u0003\u0002l\r\t!1k\\7f!\t\tS.\u0003\u0002o\r\t\u0019\u0011J\u001c;\t\u000bA\u0004A\u0011I9\u0002\u0011\r\fg.R9vC2$\"A];\u0011\u0005\u0005\u001a\u0018B\u0001;\u0007\u0005\u001d\u0011un\u001c7fC:DQA^8A\u0002]\fQa\u001c;iKJ\u0004\"!\t=\n\u0005e4!aA!os\")1\u0010\u0001C!y\u0006A\u0001.Y:i\u0007>$W\rF\u0001m\u0011\u0015q\b\u0001\"\u0011\u0000\u0003\u0019)\u0017/^1mgR\u0019!/!\u0001\t\r\u0005\rQ\u00101\u0001x\u0003\u0011!\b.\u0019;")
public final class FileZipArchive
extends ZipArchive {
    private Tuple2<ZipArchive.DirEntry, HashMap<String, ZipArchive.DirEntry>> x$1;
    private ZipArchive.DirEntry root;
    private HashMap<String, ZipArchive.DirEntry> allDirs;
    private volatile byte bitmap$0;

    private Tuple2 x$1$lzycompute() {
        FileZipArchive fileZipArchive = this;
        synchronized (fileZipArchive) {
            if ((byte)(this.bitmap$0 & 1) == 0) {
                ZipArchive.DirEntry root = new ZipArchive.DirEntry(this, "/");
                Tuple2[] tuple2Array = new Tuple2[1];
                String string2 = Predef$.MODULE$.ArrowAssoc("/");
                Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
                tuple2Array[0] = new Tuple2<String, ZipArchive.DirEntry>(string2, root);
                HashMap dirs2 = (HashMap)HashMap$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array));
                ZipFile zipFile = this.liftedTree1$1();
                Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
                while (enumeration.hasMoreElements()) {
                    Object object;
                    ZipEntry zipEntry = enumeration.nextElement();
                    ZipArchive.DirEntry dir = this.getDir(dirs2, zipEntry);
                    if (zipEntry.isDirectory()) {
                        object = dir;
                        continue;
                    }
                    public class Scala_reflect_io_FileZipArchive$FileEntry$1
                    extends ZipArchive.Entry {
                        public final /* synthetic */ FileZipArchive $outer;
                        private final ZipFile zipFile$1;
                        private final ZipEntry zipEntry$1;

                        public ZipFile getArchive() {
                            return this.zipFile$1;
                        }

                        public long lastModified() {
                            return this.zipEntry$1.getTime();
                        }

                        public InputStream input() {
                            return this.getArchive().getInputStream(this.zipEntry$1);
                        }

                        public Some<Object> sizeOption() {
                            return new Some<Object>(BoxesRunTime.boxToInteger((int)this.zipEntry$1.getSize()));
                        }

                        public /* synthetic */ FileZipArchive scala$reflect$io$FileZipArchive$FileEntry$$$outer() {
                            return this.$outer;
                        }

                        public Scala_reflect_io_FileZipArchive$FileEntry$1(FileZipArchive $outer, ZipFile zipFile$1, ZipEntry zipEntry$1) {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.zipFile$1 = zipFile$1;
                            this.zipEntry$1 = zipEntry$1;
                            super($outer, zipEntry$1.getName());
                        }
                    }
                    Scala_reflect_io_FileZipArchive$FileEntry$1 f = new Scala_reflect_io_FileZipArchive$FileEntry$1(this, zipFile, zipEntry);
                    dir.entries().update(f.name(), f);
                    object = BoxedUnit.UNIT;
                }
                Tuple2<ZipArchive.DirEntry, HashMap> tuple2 = new Tuple2<ZipArchive.DirEntry, HashMap>(root, dirs2);
                Tuple2<ZipArchive.DirEntry, HashMap> tuple22 = new Tuple2<ZipArchive.DirEntry, HashMap>(tuple2._1(), tuple2._2());
                this.x$1 = tuple22;
                this.bitmap$0 = (byte)(this.bitmap$0 | 1);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.x$1;
        }
    }

    private ZipArchive.DirEntry root$lzycompute() {
        FileZipArchive fileZipArchive = this;
        synchronized (fileZipArchive) {
            if ((byte)(this.bitmap$0 & 2) == 0) {
                this.root = (ZipArchive.DirEntry)this.x$1()._1();
                this.bitmap$0 = (byte)(this.bitmap$0 | 2);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.root;
        }
    }

    private HashMap allDirs$lzycompute() {
        FileZipArchive fileZipArchive = this;
        synchronized (fileZipArchive) {
            if ((byte)(this.bitmap$0 & 4) == 0) {
                this.allDirs = (HashMap)this.x$1()._2();
                this.bitmap$0 = (byte)(this.bitmap$0 | 4);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.allDirs;
        }
    }

    private /* synthetic */ Tuple2 x$1() {
        return (byte)(this.bitmap$0 & 1) == 0 ? this.x$1$lzycompute() : this.x$1;
    }

    public ZipArchive.DirEntry root() {
        return (byte)(this.bitmap$0 & 2) == 0 ? this.root$lzycompute() : this.root;
    }

    public HashMap<String, ZipArchive.DirEntry> allDirs() {
        return (byte)(this.bitmap$0 & 4) == 0 ? this.allDirs$lzycompute() : this.allDirs;
    }

    @Override
    public Iterator<ZipArchive.Entry> iterator() {
        return this.root().iterator();
    }

    @Override
    public String name() {
        return super.file().getName();
    }

    @Override
    public String path() {
        return super.file().getPath();
    }

    @Override
    public FileInputStream input() {
        return File$.MODULE$.apply(Path$.MODULE$.jfile2path(super.file()), Codec$.MODULE$.fallbackSystemCodec()).inputStream();
    }

    @Override
    public long lastModified() {
        return super.file().lastModified();
    }

    public Some<Object> sizeOption() {
        return new Some<Object>(BoxesRunTime.boxToInteger((int)super.file().length()));
    }

    @Override
    public boolean canEqual(Object other) {
        return other instanceof FileZipArchive;
    }

    public int hashCode() {
        return super.file().hashCode();
    }

    @Override
    public boolean equals(Object that) {
        boolean bl;
        if (that instanceof FileZipArchive) {
            FileZipArchive fileZipArchive = (FileZipArchive)that;
            File file = super.file().getAbsoluteFile();
            File file2 = fileZipArchive.file().getAbsoluteFile();
            bl = !(file != null ? !((Object)file).equals(file2) : file2 != null);
        } else {
            bl = false;
        }
        return bl;
    }

    private final ZipFile liftedTree1$1() {
        try {
            return new ZipFile(super.file());
        }
        catch (IOException iOException) {
            throw new IOException(new StringBuilder().append((Object)"Error accessing ").append((Object)super.file().getPath()).toString(), iOException);
        }
    }

    public FileZipArchive(File file) {
        super(file);
    }
}

