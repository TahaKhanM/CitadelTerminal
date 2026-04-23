/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.IOException;
import java.net.URL;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.File;
import scala.reflect.io.FileZipArchive;
import scala.reflect.io.ManifestResources;
import scala.reflect.io.URLZipArchive;

public final class ZipArchive$ {
    public static final ZipArchive$ MODULE$;

    static {
        new ZipArchive$();
    }

    public FileZipArchive fromFile(File file) {
        return this.fromFile(file.jfile());
    }

    public FileZipArchive fromFile(java.io.File file) {
        FileZipArchive fileZipArchive;
        try {
            fileZipArchive = new FileZipArchive(file);
        }
        catch (IOException iOException) {
            fileZipArchive = null;
        }
        return fileZipArchive;
    }

    public URLZipArchive fromURL(URL url) {
        return new URLZipArchive(url);
    }

    public AbstractFile fromManifestURL(URL url) {
        return new ManifestResources(url);
    }

    public String scala$reflect$io$ZipArchive$$dirName(String path) {
        return this.splitPath(path, true);
    }

    public String scala$reflect$io$ZipArchive$$baseName(String path) {
        return this.splitPath(path, false);
    }

    private String splitPath(String path0, boolean front) {
        boolean isDir = path0.charAt(path0.length() - 1) == '/';
        String path = isDir ? path0.substring(0, path0.length() - 1) : path0;
        int idx = path.lastIndexOf(47);
        return idx < 0 ? (front ? "/" : path) : (front ? path.substring(0, idx + 1) : path.substring(idx + 1));
    }

    private ZipArchive$() {
        MODULE$ = this;
    }
}

