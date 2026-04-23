/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Stream;
import scala.io.Codec$;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;
import scala.reflect.io.Directory;
import scala.reflect.io.File;
import scala.reflect.io.FileOperationException;
import scala.reflect.io.IOStats$;
import scala.reflect.io.Path;
import scala.runtime.Nothing$;
import scala.util.Random$;

public final class Path$ {
    public static final Path$ MODULE$;

    static {
        new Path$();
    }

    public boolean isExtensionJarOrZip(java.io.File jfile) {
        return this.isExtensionJarOrZip(jfile.getName());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isExtensionJarOrZip(String name) {
        String ext;
        String string2 = ext = this.extension(name);
        if (string2 != null) {
            if (string2.equals("jar")) return true;
        }
        String string3 = ext;
        if (string3 == null) return false;
        if (!string3.equals("zip")) return false;
        return true;
    }

    public String extension(String name) {
        int i;
        for (i = name.length() - 1; i >= 0 && name.charAt(i) != '.'; --i) {
        }
        return i < 0 ? "" : name.substring(i + 1).toLowerCase();
    }

    public Path string2path(String s2) {
        return this.apply(s2);
    }

    public Path jfile2path(java.io.File jfile) {
        return this.apply(jfile);
    }

    public Iterator<Directory> onlyDirs(Iterator<Path> xs) {
        return xs.filter((Function1<Path, Object>)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Path x$1) {
                return x$1.isDirectory();
            }
        })).map(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Directory apply(Path x$2) {
                return x$2.toDirectory();
            }
        });
    }

    public List<Directory> onlyDirs(List<Path> xs) {
        return ((List)xs.filter((Function1)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Path x$3) {
                return x$3.isDirectory();
            }
        }))).map(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Directory apply(Path x$4) {
                return x$4.toDirectory();
            }
        }, List$.MODULE$.canBuildFrom());
    }

    public Iterator<File> onlyFiles(Iterator<Path> xs) {
        return xs.filter((Function1<Path, Object>)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Path x$5) {
                return x$5.isFile();
            }
        })).map(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final File apply(Path x$6) {
                return x$6.toFile();
            }
        });
    }

    public List<Path> roots() {
        return Predef$.MODULE$.refArrayOps((Object[])java.io.File.listRoots()).toList().map(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Path apply(java.io.File jfile) {
                return Path$.MODULE$.apply(jfile);
            }
        }, List$.MODULE$.canBuildFrom());
    }

    public Path apply(String path) {
        return this.apply(new java.io.File(path));
    }

    public Path apply(java.io.File jfile) {
        Path path;
        try {
            path = this.isFile$1(jfile) ? new File(jfile, Codec$.MODULE$.fallbackSystemCodec()) : (this.isDirectory$1(jfile) ? new Directory(jfile) : new Path(jfile));
        }
        catch (SecurityException securityException) {
            path = new Path(jfile);
        }
        return path;
    }

    public String randomPrefix() {
        return ((Stream)Random$.MODULE$.alphanumeric().take(6)).mkString("");
    }

    public Nothing$ fail(String msg) {
        throw new FileOperationException(msg);
    }

    private final boolean isFile$1(java.io.File jfile$1) {
        if (Statistics$.MODULE$.canEnable()) {
            Statistics.Counter counter = IOStats$.MODULE$.fileIsFileCount();
            if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                counter.value_$eq(counter.value() + 1);
            }
        }
        return jfile$1.isFile();
    }

    private final boolean isDirectory$1(java.io.File jfile$1) {
        if (Statistics$.MODULE$.canEnable()) {
            Statistics.Counter counter = IOStats$.MODULE$.fileIsDirectoryCount();
            if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                counter.value_$eq(counter.value() + 1);
            }
        }
        return jfile$1.isDirectory();
    }

    private Path$() {
        MODULE$ = this;
    }
}

