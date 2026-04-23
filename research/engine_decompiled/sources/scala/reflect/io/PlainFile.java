/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.collection.Iterator;
import scala.package$;
import scala.reflect.ScalaSignature;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.Directory;
import scala.reflect.io.File;
import scala.reflect.io.Path;
import scala.reflect.io.Path$;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001\u0005ua\u0001B\u0001\u0003\u0001%\u0011\u0011\u0002\u00157bS:4\u0015\u000e\\3\u000b\u0005\r!\u0011AA5p\u0015\t)a!A\u0004sK\u001adWm\u0019;\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\u0015A\u00111\u0002D\u0007\u0002\u0005%\u0011QB\u0001\u0002\r\u0003\n\u001cHO]1di\u001aKG.\u001a\u0005\t\u001f\u0001\u0011)\u0019!C\u0001!\u0005Iq-\u001b<f]B\u000bG\u000f[\u000b\u0002#A\u00111BE\u0005\u0003'\t\u0011A\u0001U1uQ\"AQ\u0003\u0001B\u0001B\u0003%\u0011#\u0001\u0006hSZ,g\u000eU1uQ\u0002BQa\u0006\u0001\u0005\u0002a\ta\u0001P5oSRtDCA\r\u001b!\tY\u0001\u0001C\u0003\u0010-\u0001\u0007\u0011\u0003C\u0004\u001d\u0001\t\u0007I\u0011A\u000f\u0002\t\u0019LG.Z\u000b\u0002=A\u0011qdI\u0007\u0002A)\u00111!\t\u0006\u0002E\u0005!!.\u0019<b\u0013\t!\u0003E\u0001\u0003GS2,\u0007B\u0002\u0014\u0001A\u0003%a$A\u0003gS2,\u0007\u0005C\u0003)\u0001\u0011\u0005\u0013&\u0001\tv]\u0012,'\u000f\\=j]\u001e\u001cv.\u001e:dKV\t!\u0006E\u0002,Yei\u0011AB\u0005\u0003[\u0019\u0011AaU8nK\"9q\u0006\u0001b\u0001\n\u0013\u0001\u0012!\u00024qCRD\u0007BB\u0019\u0001A\u0003%\u0011#\u0001\u0004ga\u0006$\b\u000e\t\u0005\u0006g\u0001!\t\u0001N\u0001\u0005]\u0006lW-F\u00016!\t1\u0014H\u0004\u0002,o%\u0011\u0001HB\u0001\u0007!J,G-\u001a4\n\u0005iZ$AB*ue&twM\u0003\u00029\r!)Q\b\u0001C\u0001i\u0005!\u0001/\u0019;i\u0011\u0015y\u0004\u0001\"\u0001A\u0003!\t'm]8mkR,W#A\r\t\u000b\t\u0003A\u0011I\"\u0002\u0013\r|g\u000e^1j]\u0016\u0014X#\u0001\u0006\t\u000b\u0015\u0003A\u0011\t$\u0002\u000b%t\u0007/\u001e;\u0016\u0003\u001d\u0003\"a\b%\n\u0005%\u0003#a\u0004$jY\u0016Le\u000e];u'R\u0014X-Y7\t\u000b-\u0003A\u0011\t'\u0002\r=,H\u000f];u+\u0005i\u0005CA\u0010O\u0013\ty\u0005E\u0001\tGS2,w*\u001e;qkR\u001cFO]3b[\")\u0011\u000b\u0001C!%\u0006Q1/\u001b>f\u001fB$\u0018n\u001c8\u0016\u0003M\u00032a\u000b\u0017U!\tYS+\u0003\u0002W\r\t\u0019\u0011J\u001c;\t\u000ba\u0003A\u0011I-\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!\u000e\u0005\u00067\u0002!\t\u0005X\u0001\tQ\u0006\u001c\bnQ8eKR\tA\u000bC\u0003_\u0001\u0011\u0005s,\u0001\u0004fcV\fGn\u001d\u000b\u0003A\u000e\u0004\"aK1\n\u0005\t4!a\u0002\"p_2,\u0017M\u001c\u0005\u0006Iv\u0003\r!Z\u0001\u0005i\"\fG\u000f\u0005\u0002,M&\u0011qM\u0002\u0002\u0004\u0003:L\b\"B5\u0001\t\u0003Q\u0017aC5t\t&\u0014Xm\u0019;pef,\u0012\u0001\u0019\u0005\u0006Y\u0002!\t!\\\u0001\rY\u0006\u001cH/T8eS\u001aLW\rZ\u000b\u0002]B\u00111f\\\u0005\u0003a\u001a\u0011A\u0001T8oO\")!\u000f\u0001C\u0001g\u0006A\u0011\u000e^3sCR|'/F\u0001u!\r)\bP\u0003\b\u0003WYL!a\u001e\u0004\u0002\u000fA\f7m[1hK&\u0011\u0011P\u001f\u0002\t\u0013R,'/\u0019;pe*\u0011qO\u0002\u0005\u0006y\u0002!\t!`\u0001\u000bY>|7.\u001e9OC6,Gc\u0001\u0006\u007f\u007f\")1g\u001fa\u0001k!1\u0011\u0011A>A\u0002\u0001\f\u0011\u0002Z5sK\u000e$xN]=\t\u000f\u0005\u0015\u0001\u0001\"\u0001\u0002\b\u000511M]3bi\u0016$\"!!\u0003\u0011\u0007-\nY!C\u0002\u0002\u000e\u0019\u0011A!\u00168ji\"9\u0011\u0011\u0003\u0001\u0005\u0002\u0005\u001d\u0011A\u00023fY\u0016$X\rC\u0004\u0002\u0016\u0001!\t!a\u0006\u0002'1|wn[;q\u001d\u0006lW-\u00168dQ\u0016\u001c7.\u001a3\u0015\u000b)\tI\"a\u0007\t\rM\n\u0019\u00021\u00016\u0011\u001d\t\t!a\u0005A\u0002\u0001\u0004")
public class PlainFile
extends AbstractFile {
    private final Path givenPath;
    private final java.io.File file;
    private final Path fpath;

    public Path givenPath() {
        return this.givenPath;
    }

    @Override
    public java.io.File file() {
        return this.file;
    }

    public Some<PlainFile> underlyingSource() {
        return new Some<PlainFile>(this);
    }

    private Path fpath() {
        return this.fpath;
    }

    @Override
    public String name() {
        return this.givenPath().name();
    }

    @Override
    public String path() {
        return this.givenPath().path();
    }

    @Override
    public PlainFile absolute() {
        return new PlainFile(this.givenPath().toAbsolute());
    }

    @Override
    public AbstractFile container() {
        return new PlainFile(this.givenPath().parent());
    }

    @Override
    public FileInputStream input() {
        return this.givenPath().toFile().inputStream();
    }

    @Override
    public FileOutputStream output() {
        File qual$1 = this.givenPath().toFile();
        boolean x$3 = qual$1.outputStream$default$1();
        return qual$1.outputStream(x$3);
    }

    public Some<Object> sizeOption() {
        return new Some<Object>(BoxesRunTime.boxToInteger((int)this.givenPath().length()));
    }

    @Override
    public String toString() {
        return this.path();
    }

    public int hashCode() {
        return this.fpath().hashCode();
    }

    @Override
    public boolean equals(Object that) {
        boolean bl;
        if (that instanceof PlainFile) {
            PlainFile plainFile = (PlainFile)that;
            Path path = this.fpath();
            Path path2 = plainFile.fpath();
            bl = !(path != null ? !((Object)path).equals(path2) : path2 != null);
        } else {
            bl = false;
        }
        return bl;
    }

    @Override
    public boolean isDirectory() {
        return this.givenPath().isDirectory();
    }

    @Override
    public long lastModified() {
        return this.givenPath().lastModified();
    }

    @Override
    public Iterator<AbstractFile> iterator() {
        return this.isDirectory() ? this.givenPath().toDirectory().list().filter((Function1<Path, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ PlainFile $outer;

            public final boolean apply(Path path) {
                return this.$outer.scala$reflect$io$PlainFile$$existsFast$1(path);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        })).map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final PlainFile apply(Path x$2) {
                return new PlainFile(x$2);
            }
        }) : package$.MODULE$.Iterator().empty();
    }

    @Override
    public AbstractFile lookupName(String name, boolean directory) {
        Path child = this.givenPath().$div(Path$.MODULE$.string2path(name));
        return child.isDirectory() && directory || child.isFile() && !directory ? new PlainFile(child) : null;
    }

    @Override
    public void create() {
        if (!this.exists()) {
            this.givenPath().createFile(this.givenPath().createFile$default$1());
        }
    }

    @Override
    public void delete() {
        if (this.givenPath().isFile()) {
            this.givenPath().delete();
        } else if (this.givenPath().isDirectory()) {
            this.givenPath().toDirectory().deleteRecursively();
        }
    }

    @Override
    public AbstractFile lookupNameUnchecked(String name, boolean directory) {
        return new PlainFile(this.givenPath().$div(Path$.MODULE$.string2path(name)));
    }

    public final boolean scala$reflect$io$PlainFile$$existsFast$1(Path path) {
        boolean bl = path instanceof Directory ? true : path instanceof File;
        boolean bl2 = bl ? true : path.exists();
        return bl2;
    }

    public PlainFile(Path givenPath) {
        this.givenPath = givenPath;
        Predef$.MODULE$.assert(this.path() != null);
        this.file = givenPath.jfile();
        this.fpath = givenPath.toAbsolute();
    }
}

