/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import scala.io.Codec;
import scala.io.Codec$;
import scala.reflect.io.File;
import scala.reflect.io.Path;
import scala.reflect.io.Path$;

public final class File$ {
    public static final File$ MODULE$;

    static {
        new File$();
    }

    public String pathSeparator() {
        return java.io.File.pathSeparator;
    }

    public String separator() {
        return java.io.File.separator;
    }

    public File apply(Path path, Codec codec) {
        return new File(path.jfile(), codec);
    }

    public File makeTemp(String prefix, String suffix, java.io.File dir) {
        java.io.File jfile = java.io.File.createTempFile(prefix, suffix, dir);
        jfile.deleteOnExit();
        return this.apply(Path$.MODULE$.jfile2path(jfile), Codec$.MODULE$.fallbackSystemCodec());
    }

    public String makeTemp$default$1() {
        return Path$.MODULE$.randomPrefix();
    }

    public String makeTemp$default$2() {
        return null;
    }

    public java.io.File makeTemp$default$3() {
        return null;
    }

    private File$() {
        MODULE$ = this;
    }
}

