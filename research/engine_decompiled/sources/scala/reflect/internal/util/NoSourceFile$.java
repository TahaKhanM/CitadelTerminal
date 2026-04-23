/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Array$;
import scala.collection.immutable.Nil$;
import scala.reflect.ClassTag$;
import scala.reflect.internal.util.NoFile$;
import scala.reflect.internal.util.SourceFile;

public final class NoSourceFile$
extends SourceFile {
    public static final NoSourceFile$ MODULE$;

    static {
        new NoSourceFile$();
    }

    @Override
    public char[] content() {
        return (char[])Array$.MODULE$.apply(Nil$.MODULE$, ClassTag$.MODULE$.Char());
    }

    @Override
    public NoFile$ file() {
        return NoFile$.MODULE$;
    }

    @Override
    public boolean isLineBreak(int idx) {
        return false;
    }

    @Override
    public boolean isEndOfLine(int idx) {
        return false;
    }

    @Override
    public boolean isSelfContained() {
        return true;
    }

    @Override
    public int length() {
        return -1;
    }

    @Override
    public int offsetToLine(int offset) {
        return -1;
    }

    @Override
    public int lineToOffset(int index) {
        return -1;
    }

    @Override
    public String toString() {
        return "<no source file>";
    }

    private NoSourceFile$() {
        MODULE$ = this;
    }
}

