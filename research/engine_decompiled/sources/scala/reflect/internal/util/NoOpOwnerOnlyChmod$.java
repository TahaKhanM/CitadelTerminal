/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import java.io.File;
import scala.reflect.internal.util.OwnerOnlyChmod;
import scala.reflect.internal.util.OwnerOnlyChmod$class;

public final class NoOpOwnerOnlyChmod$
implements OwnerOnlyChmod {
    public static final NoOpOwnerOnlyChmod$ MODULE$;

    static {
        new NoOpOwnerOnlyChmod$();
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
    }

    private NoOpOwnerOnlyChmod$() {
        MODULE$ = this;
        OwnerOnlyChmod$class.$init$(this);
    }
}

