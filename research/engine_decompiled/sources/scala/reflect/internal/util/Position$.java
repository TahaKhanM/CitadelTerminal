/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Predef$;
import scala.StringContext;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.util.NoPosition$;
import scala.reflect.internal.util.NoSourceFile$;
import scala.reflect.internal.util.OffsetPosition;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.RangePosition;
import scala.reflect.internal.util.SourceFile;
import scala.reflect.internal.util.TransparentPosition;

public final class Position$ {
    public static final Position$ MODULE$;
    private final int tabInc;

    static {
        new Position$();
    }

    public int tabInc() {
        return this.tabInc;
    }

    private <T extends Position> T validate(T pos) {
        if (pos.isRange()) {
            boolean bl = pos.start() <= pos.end();
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"bad position: ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{pos.show()}))).toString());
            }
        }
        return pos;
    }

    public String formatMessage(Position posIn, String msg, boolean shortenFile) {
        Position pos = posIn == null ? NoPosition$.MODULE$ : posIn;
        SourceFile sourceFile = pos.source();
        String string2 = NoSourceFile$.MODULE$.equals(sourceFile) ? "" : (shortenFile ? new StringBuilder().append((Object)sourceFile.file().name()).append((Object)":").toString() : new StringBuilder().append((Object)sourceFile.file().path()).append((Object)":").toString());
        return new StringBuilder().append((Object)string2).append((Object)pos.showError(msg)).toString();
    }

    public Position offset(SourceFile source, int point) {
        return this.validate(new OffsetPosition(source, point));
    }

    public Position range(SourceFile source, int start, int point, int end) {
        return this.validate(new RangePosition(source, start, point, end));
    }

    public Position transparent(SourceFile source, int start, int point, int end) {
        return this.validate(new TransparentPosition(source, start, point, end));
    }

    private Position$() {
        MODULE$ = this;
        this.tabInc = 8;
    }
}

