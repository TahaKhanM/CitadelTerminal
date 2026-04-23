/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.net.URL;
import scala.io.Codec$;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.File;
import scala.reflect.io.File$;
import scala.reflect.io.Path;
import scala.reflect.io.Path$;
import scala.reflect.io.PlainFile;
import scala.reflect.io.ZipArchive$;

public final class AbstractFile$ {
    public static final AbstractFile$ MODULE$;

    static {
        new AbstractFile$();
    }

    public AbstractFile getFile(String path) {
        return this.getFile(File$.MODULE$.apply(Path$.MODULE$.string2path(path), Codec$.MODULE$.fallbackSystemCodec()));
    }

    public AbstractFile getFile(Path path) {
        return this.getFile(path.toFile());
    }

    public AbstractFile getFile(File file) {
        return file.isFile() ? new PlainFile(file) : null;
    }

    public AbstractFile getDirectory(Path path) {
        return this.getDirectory(path.toFile());
    }

    public AbstractFile getDirectory(File file) {
        return file.isDirectory() ? new PlainFile(file) : (file.isFile() && Path$.MODULE$.isExtensionJarOrZip(file.jfile()) ? ZipArchive$.MODULE$.fromFile(file) : null);
    }

    public AbstractFile getURL(URL url) {
        java.io.File f;
        String string2 = url.getProtocol();
        return string2 != null && string2.equals("file") ? ((f = new java.io.File(url.getPath())).isDirectory() ? this.getDirectory(Path$.MODULE$.jfile2path(f)) : this.getFile(Path$.MODULE$.jfile2path(f))) : null;
    }

    public AbstractFile getResources(URL url) {
        return ZipArchive$.MODULE$.fromManifestURL(url);
    }

    private AbstractFile$() {
        MODULE$ = this;
    }
}

