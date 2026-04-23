/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import scala.Array$;
import scala.Function0;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap$;
import scala.reflect.ScalaSignature;
import scala.reflect.io.Streamable;
import scala.reflect.io.Streamable$;
import scala.reflect.io.Streamable$Bytes$class;
import scala.reflect.io.URLZipArchive;
import scala.reflect.io.ZipArchive;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;

@ScalaSignature(bytes="\u0006\u0001y3A!\u0001\u0002\u0003\u0013\tiQK\u0015'[SB\f%o\u00195jm\u0016T!a\u0001\u0003\u0002\u0005%|'BA\u0003\u0007\u0003\u001d\u0011XM\u001a7fGRT\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001!\u0002\u0005\u0002\f\u00195\t!!\u0003\u0002\u000e\u0005\tQ!,\u001b9Be\u000eD\u0017N^3\t\u0011=\u0001!Q1A\u0005\u0002A\t1!\u001e:m+\u0005\t\u0002C\u0001\n\u0018\u001b\u0005\u0019\"B\u0001\u000b\u0016\u0003\rqW\r\u001e\u0006\u0002-\u0005!!.\u0019<b\u0013\tA2CA\u0002V%2C\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I!E\u0001\u0005kJd\u0007\u0005C\u0003\u001d\u0001\u0011\u0005Q$\u0001\u0004=S:LGO\u0010\u000b\u0003=}\u0001\"a\u0003\u0001\t\u000b=Y\u0002\u0019A\t\t\u000b\u0005\u0002A\u0011\u0001\u0012\u0002\u0011%$XM]1u_J,\u0012a\t\t\u0004I!ZcBA\u0013'\u001b\u00051\u0011BA\u0014\u0007\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000b\u0016\u0003\u0011%#XM]1u_JT!a\n\u0004\u0011\u00051jS\"\u0001\u0001\n\u00059b!!B#oiJL\b\"\u0002\u0019\u0001\t\u0003\t\u0014\u0001\u00028b[\u0016,\u0012A\r\t\u0003gYj\u0011\u0001\u000e\u0006\u0003kU\tA\u0001\\1oO&\u0011q\u0007\u000e\u0002\u0007'R\u0014\u0018N\\4\t\u000be\u0002A\u0011A\u0019\u0002\tA\fG\u000f\u001b\u0005\u0006w\u0001!\t\u0001P\u0001\u0006S:\u0004X\u000f^\u000b\u0002{A\u0011a\bQ\u0007\u0002\u007f)\u00111!F\u0005\u0003\u0003~\u00121\"\u00138qkR\u001cFO]3b[\")1\t\u0001C\u0001\t\u0006aA.Y:u\u001b>$\u0017NZ5fIV\tQ\t\u0005\u0002&\r&\u0011qI\u0002\u0002\u0005\u0019>tw\rC\u0003J\u0001\u0011\u0005#*\u0001\u0005dC:,\u0015/^1m)\tYe\n\u0005\u0002&\u0019&\u0011QJ\u0002\u0002\b\u0005>|G.Z1o\u0011\u0015y\u0005\n1\u0001Q\u0003\u0015yG\u000f[3s!\t)\u0013+\u0003\u0002S\r\t\u0019\u0011I\\=\t\u000bQ\u0003A\u0011I+\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012A\u0016\t\u0003K]K!\u0001\u0017\u0004\u0003\u0007%sG\u000fC\u0003[\u0001\u0011\u00053,\u0001\u0004fcV\fGn\u001d\u000b\u0003\u0017rCQ!X-A\u0002A\u000bA\u0001\u001e5bi\u0002")
public final class URLZipArchive
extends ZipArchive {
    private final URL url;

    public URL url() {
        return this.url;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public Iterator<ZipArchive.Entry> iterator() {
        Iterator<ZipArchive.Entry> iterator2;
        ZipArchive.DirEntry root = new ZipArchive.DirEntry(this, "/");
        Tuple2[] tuple2Array = new Tuple2[1];
        Predef$ predef$ = Predef$.MODULE$;
        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[0] = new Tuple2<String, ZipArchive.DirEntry>("/", root);
        HashMap dirs2 = (HashMap)HashMap$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array));
        Serializable serializable = new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ URLZipArchive $outer;

            public final InputStream apply() {
                return this.$outer.input();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
        Streamable$ streamable$ = Streamable$.MODULE$;
        ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(Streamable$Bytes$class.toByteArray(new Streamable.Bytes((Function0)((Object)serializable)){
            private final Function0 is$1;

            public long length() {
                return Streamable$Bytes$class.length(this);
            }

            public BufferedInputStream bufferedInput() {
                return Streamable$Bytes$class.bufferedInput(this);
            }

            public Iterator<Object> bytes() {
                return Streamable$Bytes$class.bytes(this);
            }

            public Iterator<Object> bytesAsInts() {
                return Streamable$Bytes$class.bytesAsInts(this);
            }

            public byte[] toByteArray() {
                return Streamable$Bytes$class.toByteArray(this);
            }

            public InputStream inputStream() {
                return (InputStream)this.is$1.apply();
            }
            {
                this.is$1 = is$1;
                Streamable$Bytes$class.$init$(this);
            }
        })));
        this.loop$1(dirs2, in);
        try {
            iterator2 = root.iterator();
            dirs2.clear();
        }
        catch (Throwable throwable) {
            void var7_4;
            var7_4.clear();
            throw throwable;
        }
        return iterator2;
    }

    @Override
    public String name() {
        return this.url().getFile();
    }

    @Override
    public String path() {
        return this.url().getPath();
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
        return other instanceof URLZipArchive;
    }

    public int hashCode() {
        return this.url().hashCode();
    }

    @Override
    public boolean equals(Object that) {
        boolean bl;
        if (that instanceof URLZipArchive) {
            URLZipArchive uRLZipArchive = (URLZipArchive)that;
            URL uRL = this.url();
            URL uRL2 = uRLZipArchive.url();
            bl = !(uRL != null ? !((Object)uRL).equals(uRL2) : uRL2 != null);
        } else {
            bl = false;
        }
        return bl;
    }

    /*
     * WARNING - void declaration
     */
    private final void loop$1(HashMap dirs$1, ZipInputStream in$1) {
        while (true) {
            Object object;
            void var3_3;
            ZipEntry zipEntry;
            if ((zipEntry = in$1.getNextEntry()) == null) {
                return;
            }
            ZipArchive.DirEntry dir = this.getDir(dirs$1, (ZipEntry)var3_3);
            if (var3_3.isDirectory()) {
                object = dir;
            } else {
                public class Scala_reflect_io_URLZipArchive$EmptyFileEntry$1
                extends ZipArchive.Entry {
                    public final /* synthetic */ URLZipArchive $outer;

                    public byte[] toByteArray() {
                        return null;
                    }

                    public Some<Object> sizeOption() {
                        return new Some<Object>(BoxesRunTime.boxToInteger(0));
                    }

                    public /* synthetic */ URLZipArchive scala$reflect$io$URLZipArchive$EmptyFileEntry$$$outer() {
                        return this.$outer;
                    }

                    public Scala_reflect_io_URLZipArchive$EmptyFileEntry$1(URLZipArchive $outer, ZipEntry zipEntry$2) {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        super($outer, zipEntry$2.getName());
                    }
                }
                public class Scala_reflect_io_URLZipArchive$FileEntry$2
                extends ZipArchive.Entry {
                    private final byte[] toByteArray;
                    public final /* synthetic */ URLZipArchive $outer;
                    private final ZipInputStream in$1;
                    private final ZipEntry zipEntry$2;

                    public byte[] toByteArray() {
                        return this.toByteArray;
                    }

                    public Some<Object> sizeOption() {
                        return new Some<Object>(BoxesRunTime.boxToInteger((int)this.zipEntry$2.getSize()));
                    }

                    public /* synthetic */ URLZipArchive scala$reflect$io$URLZipArchive$FileEntry$$$outer() {
                        return this.$outer;
                    }

                    private final void loop$2(int len$1, byte[] arr$1, IntRef offset$1) {
                        block2: {
                            BoxedUnit boxedUnit;
                            while (offset$1.elem < len$1) {
                                int read = this.in$1.read(arr$1, offset$1.elem, len$1 - offset$1.elem);
                                if (read >= 0) {
                                    offset$1.elem += read;
                                    continue;
                                }
                                boxedUnit = BoxedUnit.UNIT;
                                break block2;
                            }
                            boxedUnit = BoxedUnit.UNIT;
                        }
                    }

                    public Scala_reflect_io_URLZipArchive$FileEntry$2(URLZipArchive $outer, ZipInputStream in$1, ZipEntry zipEntry$2) {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.in$1 = in$1;
                        this.zipEntry$2 = zipEntry$2;
                        super($outer, zipEntry$2.getName());
                        int len = (int)zipEntry$2.getSize();
                        byte[] arr = len == 0 ? Array$.MODULE$.emptyByteArray() : new byte[len];
                        IntRef offset = IntRef.create(0);
                        this.loop$2(len, arr, offset);
                        if (offset.elem == arr.length) {
                            this.toByteArray = arr;
                            return;
                        }
                        Predef$ predef$ = Predef$.MODULE$;
                        throw new IOException(new StringOps("Input stream truncated: read %d of %d bytes").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(offset.elem), BoxesRunTime.boxToInteger(len)})));
                    }
                }
                ZipArchive.Entry f = var3_3.getSize() == 0L ? new Scala_reflect_io_URLZipArchive$EmptyFileEntry$1(this, (ZipEntry)var3_3) : new Scala_reflect_io_URLZipArchive$FileEntry$2(this, in$1, (ZipEntry)var3_3);
                dir.entries().update(f.name(), f);
                object = BoxedUnit.UNIT;
            }
            in$1.closeEntry();
        }
    }

    public URLZipArchive(URL url) {
        this.url = url;
        super(null);
    }
}

