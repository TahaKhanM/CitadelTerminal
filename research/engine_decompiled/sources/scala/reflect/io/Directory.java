/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import scala.Function1;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.collection.Iterator;
import scala.io.Codec$;
import scala.package$;
import scala.reflect.ScalaSignature;
import scala.reflect.io.Directory$;
import scala.reflect.io.File;
import scala.reflect.io.Path;
import scala.reflect.io.Path$;

@ScalaSignature(bytes="\u0006\u0001\u0005-r!B\u0001\u0003\u0011\u0003I\u0011!\u0003#je\u0016\u001cGo\u001c:z\u0015\t\u0019A!\u0001\u0002j_*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\f7\u0001\u0001\t\u0003\u0015-i\u0011A\u0001\u0004\u0006\u0019\tA\t!\u0004\u0002\n\t&\u0014Xm\u0019;pef\u001c\"a\u0003\b\u0011\u0005=\u0001R\"\u0001\u0004\n\u0005E1!AB!osJ+g\rC\u0003\u0014\u0017\u0011\u0005A#\u0001\u0004=S:LGO\u0010\u000b\u0002\u0013!)ac\u0003C\u0005/\u0005ian\u001c:nC2L'0\u001a)bi\"$\"\u0001\u00078\u0011\u0007=I2$\u0003\u0002\u001b\r\t!1k\\7f!\tQAD\u0002\u0003\r\u0005\u0001i2C\u0001\u000f\u001f!\tQq$\u0003\u0002!\u0005\t!\u0001+\u0019;i\u0011%\u0011CD!A!\u0002\u0013\u0019#&A\u0003kM&dW\r\u0005\u0002%Q5\tQE\u0003\u0002\u0004M)\tq%\u0001\u0003kCZ\f\u0017BA\u0015&\u0005\u00111\u0015\u000e\\3\n\u0005\tz\u0002\"B\n\u001d\t\u0003aCCA\u000e.\u0011\u0015\u00113\u00061\u0001$\u0011\u0015yC\u0004\"\u00111\u0003)!x.\u00112t_2,H/Z\u000b\u00027!)!\u0007\bC!a\u0005YAo\u001c#je\u0016\u001cGo\u001c:z\u0011\u0015!D\u0004\"\u00116\u0003\u0019!xNR5mKV\ta\u0007\u0005\u0002\u000bo%\u0011\u0011F\u0001\u0005\u0006sq!\t\u0005M\u0001\n]>\u0014X.\u00197ju\u0016DQa\u000f\u000f\u0005\u0002q\nA\u0001\\5tiV\tQ\bE\u0002?\u0003zq!aD \n\u0005\u00013\u0011a\u00029bG.\fw-Z\u0005\u0003\u0005\u000e\u0013\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003\u0001\u001aAQ!\u0012\u000f\u0005\u0002\u0019\u000bA\u0001Z5sgV\tq\tE\u0002?\u0003nAQ!\u0013\u000f\u0005\u0002)\u000bQAZ5mKN,\u0012a\u0013\t\u0004}\u00053\u0004\"B'\u001d\t\u0003r\u0015AC<bY.4\u0015\u000e\u001c;feR\u0011Qh\u0014\u0005\u0006!2\u0003\r!U\u0001\u0005G>tG\r\u0005\u0003\u0010%z!\u0016BA*\u0007\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002\u0010+&\u0011aK\u0002\u0002\b\u0005>|G.Z1o\u0011\u0015AF\u0004\"\u0001K\u0003%!W-\u001a9GS2,7\u000fC\u0003[9\u0011\u00051,\u0001\u0005eK\u0016\u0004H*[:u)\tiD\fC\u0004^3B\u0005\t\u0019\u00010\u0002\u000b\u0011,\u0007\u000f\u001e5\u0011\u0005=y\u0016B\u00011\u0007\u0005\rIe\u000e\u001e\u0005\bEr\t\n\u0011\"\u0001d\u0003I!W-\u001a9MSN$H\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\u0011T#AX3,\u0003\u0019\u0004\"a\u001a7\u000e\u0003!T!!\u001b6\u0002\u0013Ut7\r[3dW\u0016$'BA6\u0007\u0003)\tgN\\8uCRLwN\\\u0005\u0003[\"\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0011\u0015yW\u00031\u0001q\u0003\u0005\u0019\bCA9u\u001d\ty!/\u0003\u0002t\r\u00051\u0001K]3eK\u001aL!!\u001e<\u0003\rM#(/\u001b8h\u0015\t\u0019h\u0001C\u0003y\u0017\u0011\u0005\u00110A\u0004DkJ\u0014XM\u001c;\u0016\u0003i\u00042aD>\u001c\u0013\tahA\u0001\u0004PaRLwN\u001c\u0005\u0006}.!\ta`\u0001\u0006CB\u0004H.\u001f\u000b\u00047\u0005\u0005\u0001BBA\u0002{\u0002\u0007a$\u0001\u0003qCRD\u0007bBA\u0004\u0017\u0011\u0005\u0011\u0011B\u0001\t[\u0006\\W\rV3naR91$a\u0003\u0002\u0010\u0005M\u0001\"CA\u0007\u0003\u000b\u0001\n\u00111\u0001q\u0003\u0019\u0001(/\u001a4jq\"I\u0011\u0011CA\u0003!\u0003\u0005\r\u0001]\u0001\u0007gV4g-\u001b=\t\u0013\u0005U\u0011Q\u0001I\u0001\u0002\u0004\u0019\u0013a\u00013je\"I\u0011\u0011D\u0006\u0012\u0002\u0013\u0005\u00111D\u0001\u0013[\u0006\\W\rV3na\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002\u001e)\u0012\u0001/\u001a\u0005\n\u0003CY\u0011\u0013!C\u0001\u00037\t!#\\1lKR+W\u000e\u001d\u0013eK\u001a\fW\u000f\u001c;%e!I\u0011QE\u0006\u0012\u0002\u0013\u0005\u0011qE\u0001\u0013[\u0006\\W\rV3na\u0012\"WMZ1vYR$3'\u0006\u0002\u0002*)\u00121%\u001a")
public class Directory
extends Path {
    public static java.io.File makeTemp$default$3() {
        return Directory$.MODULE$.makeTemp$default$3();
    }

    public static String makeTemp$default$2() {
        return Directory$.MODULE$.makeTemp$default$2();
    }

    public static String makeTemp$default$1() {
        return Directory$.MODULE$.makeTemp$default$1();
    }

    public static Directory makeTemp(String string2, String string3, java.io.File file) {
        return Directory$.MODULE$.makeTemp(string2, string3, file);
    }

    public static Directory apply(Path path) {
        return Directory$.MODULE$.apply(path);
    }

    public static Option<Directory> Current() {
        return Directory$.MODULE$.Current();
    }

    @Override
    public Directory toAbsolute() {
        return this.isAbsolute() ? this : super.toAbsolute().toDirectory();
    }

    @Override
    public Directory toDirectory() {
        return this;
    }

    @Override
    public File toFile() {
        return new File(super.jfile(), Codec$.MODULE$.fallbackSystemCodec());
    }

    @Override
    public Directory normalize() {
        return super.normalize().toDirectory();
    }

    public Iterator<Path> list() {
        java.io.File[] fileArray = super.jfile().listFiles();
        Iterator<Object> iterator2 = fileArray == null ? package$.MODULE$.Iterator().empty() : Predef$.MODULE$.refArrayOps((Object[])fileArray).iterator().map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final Path apply(java.io.File jfile) {
                return Path$.MODULE$.apply(jfile);
            }
        });
        return iterator2;
    }

    public Iterator<Directory> dirs() {
        return this.list().collect(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final <A1 extends Path, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                Object object;
                if (x1 instanceof Directory) {
                    Directory directory = (Directory)x1;
                    object = directory;
                } else {
                    object = function1.apply(x1);
                }
                return object;
            }

            public final boolean isDefinedAt(Path x1) {
                boolean bl = x1 instanceof Directory;
                return bl;
            }
        });
    }

    public Iterator<File> files() {
        return this.list().collect(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final <A1 extends Path, B1> B1 applyOrElse(A1 x2, Function1<A1, B1> function1) {
                Object object;
                if (x2 instanceof File) {
                    File file = (File)x2;
                    object = file;
                } else {
                    object = function1.apply(x2);
                }
                return object;
            }

            public final boolean isDefinedAt(Path x2) {
                boolean bl = x2 instanceof File;
                return bl;
            }
        });
    }

    @Override
    public Iterator<Path> walkFilter(Function1<Path, Object> cond) {
        return this.list().filter(cond).flatMap(new Serializable(this, cond){
            public static final long serialVersionUID = 0L;
            private final Function1 cond$1;

            public final Iterator<Path> apply(Path x$1) {
                return x$1.walkFilter(this.cond$1);
            }
            {
                this.cond$1 = cond$1;
            }
        });
    }

    public Iterator<File> deepFiles() {
        return Path$.MODULE$.onlyFiles(this.deepList(this.deepList$default$1()));
    }

    public Iterator<Path> deepList(int depth) {
        return depth < 0 ? this.list().$plus$plus(new Serializable(this, depth){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Directory $outer;
            public final int depth$1;

            public final Iterator<Path> apply() {
                return this.$outer.dirs().flatMap(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ $anonfun$deepList$1 $outer;

                    public final Iterator<Path> apply(Directory x$2) {
                        return x$2.deepList(this.$outer.depth$1);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.depth$1 = depth$1;
            }
        }) : (depth == 0 ? package$.MODULE$.Iterator().empty() : this.list().$plus$plus(new Serializable(this, depth){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Directory $outer;
            public final int depth$1;

            public final Iterator<Path> apply() {
                return this.$outer.dirs().flatMap(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ $anonfun$deepList$2 $outer;

                    public final Iterator<Path> apply(Directory x$3) {
                        return x$3.deepList(this.$outer.depth$1 - 1);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.depth$1 = depth$1;
            }
        }));
    }

    public int deepList$default$1() {
        return -1;
    }

    public Directory(java.io.File jfile) {
        super(jfile);
    }
}

