/*
 * Decompiled with CFR 0.152.
 */
package scala.io;

import scala.io.AnsiColor;
import scala.io.AnsiColor$class;

public final class AnsiColor$
implements AnsiColor {
    public static final AnsiColor$ MODULE$;

    static {
        new AnsiColor$();
    }

    @Override
    public final String BLACK() {
        return "\u001b[30m";
    }

    @Override
    public final String RED() {
        return "\u001b[31m";
    }

    @Override
    public final String GREEN() {
        return "\u001b[32m";
    }

    @Override
    public final String YELLOW() {
        return "\u001b[33m";
    }

    @Override
    public final String BLUE() {
        return "\u001b[34m";
    }

    @Override
    public final String MAGENTA() {
        return "\u001b[35m";
    }

    @Override
    public final String CYAN() {
        return "\u001b[36m";
    }

    @Override
    public final String WHITE() {
        return "\u001b[37m";
    }

    @Override
    public final String BLACK_B() {
        return "\u001b[40m";
    }

    @Override
    public final String RED_B() {
        return "\u001b[41m";
    }

    @Override
    public final String GREEN_B() {
        return "\u001b[42m";
    }

    @Override
    public final String YELLOW_B() {
        return "\u001b[43m";
    }

    @Override
    public final String BLUE_B() {
        return "\u001b[44m";
    }

    @Override
    public final String MAGENTA_B() {
        return "\u001b[45m";
    }

    @Override
    public final String CYAN_B() {
        return "\u001b[46m";
    }

    @Override
    public final String WHITE_B() {
        return "\u001b[47m";
    }

    @Override
    public final String RESET() {
        return "\u001b[0m";
    }

    @Override
    public final String BOLD() {
        return "\u001b[1m";
    }

    @Override
    public final String UNDERLINED() {
        return "\u001b[4m";
    }

    @Override
    public final String BLINK() {
        return "\u001b[5m";
    }

    @Override
    public final String REVERSED() {
        return "\u001b[7m";
    }

    @Override
    public final String INVISIBLE() {
        return "\u001b[8m";
    }

    private AnsiColor$() {
        MODULE$ = this;
        AnsiColor$class.$init$(this);
    }
}

