/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import java.io.File;
import java.io.IOException;
import scala.Function2;
import scala.Serializable;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.util.OwnerOnlyChmod;
import scala.reflect.internal.util.OwnerOnlyChmod$class;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

public final class Java6UnixChmod$
implements OwnerOnlyChmod {
    public static final Java6UnixChmod$ MODULE$;

    static {
        new Java6UnixChmod$();
    }

    @Override
    public final void chmodAndWrite(File file, byte[] contents) {
        OwnerOnlyChmod$class.chmodAndWrite(this, file, contents);
    }

    @Override
    public final void chmodOrCreateEmpty(File file) {
        OwnerOnlyChmod$class.chmodOrCreateEmpty(this, file);
    }

    @Override
    public void chmod(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                this.clearAndSetOwnerOnly$1((Function2)((Object)new Serializable(file){
                    public static final long serialVersionUID = 0L;
                    private final File file$1;

                    public final boolean apply(boolean x$1, boolean x$2) {
                        return this.file$1.setExecutable(x$1, x$2);
                    }
                    {
                        this.file$1 = file$1;
                    }
                }), file);
            }
            this.clearAndSetOwnerOnly$1((Function2)((Object)new Serializable(file){
                public static final long serialVersionUID = 0L;
                private final File file$1;

                public final boolean apply(boolean x$1, boolean x$2) {
                    return this.file$1.setReadable(x$1, x$2);
                }
                {
                    this.file$1 = file$1;
                }
            }), file);
            this.clearAndSetOwnerOnly$1((Function2)((Object)new Serializable(file){
                public static final long serialVersionUID = 0L;
                private final File file$1;

                public final boolean apply(boolean x$1, boolean x$2) {
                    return this.file$1.setWritable(x$1, x$2);
                }
                {
                    this.file$1 = file$1;
                }
            }), file);
        }
    }

    private final Nothing$ fail$1(File file$1) {
        throw new IOException(new StringBuilder().append((Object)"Unable to modify permissions of ").append(file$1).toString());
    }

    private final void clearAndSetOwnerOnly$1(Function2 f, File file$1) {
        if (BoxesRunTime.unboxToBoolean(f.apply(BoxesRunTime.boxToBoolean(false), BoxesRunTime.boxToBoolean(false)))) {
            if (BoxesRunTime.unboxToBoolean(f.apply(BoxesRunTime.boxToBoolean(true), BoxesRunTime.boxToBoolean(true)))) {
                return;
            }
            throw this.fail$1(file$1);
        }
        throw this.fail$1(file$1);
    }

    private Java6UnixChmod$() {
        MODULE$ = this;
        OwnerOnlyChmod$class.$init$(this);
    }
}

