/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.reflect.io.FileOperationException;
import scala.runtime.AbstractFunction1;

public final class FileOperationException$
extends AbstractFunction1<String, FileOperationException>
implements Serializable {
    public static final FileOperationException$ MODULE$;

    static {
        new FileOperationException$();
    }

    @Override
    public final String toString() {
        return "FileOperationException";
    }

    @Override
    public FileOperationException apply(String msg) {
        return new FileOperationException(msg);
    }

    public Option<String> unapply(FileOperationException x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<String>(x$0.msg());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private FileOperationException$() {
        MODULE$ = this;
    }
}

