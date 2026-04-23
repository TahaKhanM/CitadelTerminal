/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.StringContext;
import scala.reflect.api.Quasiquotes;
import scala.reflect.api.Universe;

public abstract class Quasiquotes$class {
    public static Quasiquotes.Quasiquote Quasiquote(Universe $this, StringContext ctx) {
        return new Quasiquotes.Quasiquote($this, ctx);
    }

    public static void $init$(Universe $this) {
    }
}

