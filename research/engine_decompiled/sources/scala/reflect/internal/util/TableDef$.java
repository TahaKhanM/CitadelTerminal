/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.collection.Seq;
import scala.reflect.internal.util.TableDef;

public final class TableDef$ {
    public static final TableDef$ MODULE$;

    static {
        new TableDef$();
    }

    public <T> TableDef<T> apply(Seq<TableDef.Column<T>> cols) {
        return new TableDef<T>(cols);
    }

    private TableDef$() {
        MODULE$ = this;
    }
}

