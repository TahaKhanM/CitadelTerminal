/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.reflect.internal.util.Java6UnixChmod$;
import scala.reflect.internal.util.NioAclChmodReflective;
import scala.reflect.internal.util.NoOpOwnerOnlyChmod$;
import scala.reflect.internal.util.OwnerOnlyChmod;
import scala.util.Properties$;

public final class OwnerOnlyChmod$ {
    public static final OwnerOnlyChmod$ MODULE$;

    static {
        new OwnerOnlyChmod$();
    }

    public OwnerOnlyChmod apply() {
        return Properties$.MODULE$.isWin() ? (Properties$.MODULE$.isJavaAtLeast("7") ? new NioAclChmodReflective() : NoOpOwnerOnlyChmod$.MODULE$) : Java6UnixChmod$.MODULE$;
    }

    private OwnerOnlyChmod$() {
        MODULE$ = this;
    }
}

