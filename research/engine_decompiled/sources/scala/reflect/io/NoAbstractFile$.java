/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import scala.Array$;
import scala.Predef$;
import scala.collection.Iterator;
import scala.collection.immutable.Nil$;
import scala.package$;
import scala.reflect.ClassTag$;
import scala.reflect.io.AbstractFile;

public final class NoAbstractFile$
extends AbstractFile {
    public static final NoAbstractFile$ MODULE$;

    static {
        new NoAbstractFile$();
    }

    @Override
    public AbstractFile absolute() {
        return this;
    }

    @Override
    public AbstractFile container() {
        return this;
    }

    @Override
    public void create() {
        throw Predef$.MODULE$.$qmark$qmark$qmark();
    }

    @Override
    public void delete() {
        throw Predef$.MODULE$.$qmark$qmark$qmark();
    }

    @Override
    public File file() {
        return null;
    }

    @Override
    public InputStream input() {
        return null;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isVirtual() {
        return true;
    }

    @Override
    public Iterator<AbstractFile> iterator() {
        return package$.MODULE$.Iterator().empty();
    }

    @Override
    public long lastModified() {
        return 0L;
    }

    @Override
    public AbstractFile lookupName(String name, boolean directory) {
        return null;
    }

    @Override
    public AbstractFile lookupNameUnchecked(String name, boolean directory) {
        return null;
    }

    @Override
    public String name() {
        return "";
    }

    @Override
    public OutputStream output() {
        return null;
    }

    @Override
    public String path() {
        return "";
    }

    @Override
    public byte[] toByteArray() {
        return (byte[])Array$.MODULE$.apply(Nil$.MODULE$, ClassTag$.MODULE$.Byte());
    }

    @Override
    public String toString() {
        return "<no file>";
    }

    private NoAbstractFile$() {
        MODULE$ = this;
    }
}

