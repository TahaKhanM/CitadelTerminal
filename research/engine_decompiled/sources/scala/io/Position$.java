/*
 * Decompiled with CFR 0.152.
 */
package scala.io;

import scala.collection.mutable.StringBuilder;
import scala.io.Position;
import scala.runtime.BoxesRunTime;

public final class Position$
extends Position {
    public static final Position$ MODULE$;

    static {
        new Position$();
    }

    @Override
    public void checkInput(int line, int column) {
        if (line < 0) {
            throw new IllegalArgumentException(new StringBuilder().append(line).append((Object)" < 0").toString());
        }
        if (line == 0 && column != 0) {
            throw new IllegalArgumentException(new StringBuilder().append(line).append((Object)",").append(BoxesRunTime.boxToInteger(column)).append((Object)" not allowed").toString());
        }
        if (column < 0) {
            throw new IllegalArgumentException(new StringBuilder().append(line).append((Object)",").append(BoxesRunTime.boxToInteger(column)).append((Object)" not allowed").toString());
        }
    }

    private Position$() {
        MODULE$ = this;
    }
}

