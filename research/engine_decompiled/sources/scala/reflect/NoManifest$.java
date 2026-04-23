/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect;

import scala.reflect.OptManifest;
import scala.runtime.Nothing$;

public final class NoManifest$
implements OptManifest<Nothing$> {
    public static final NoManifest$ MODULE$;

    static {
        new NoManifest$();
    }

    public String toString() {
        return "<?>";
    }

    private Object readResolve() {
        return MODULE$;
    }

    private NoManifest$() {
        MODULE$ = this;
    }
}

