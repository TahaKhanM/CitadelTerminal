/*
 * Decompiled with CFR 0.152.
 */
package scala;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import scala.DeprecatedConsole;
import scala.Function0;
import scala.Predef$;
import scala.collection.Seq;
import scala.collection.immutable.StringOps;
import scala.io.AnsiColor;
import scala.io.AnsiColor$class;
import scala.util.DynamicVariable;

public final class Console$
extends DeprecatedConsole
implements AnsiColor {
    public static final Console$ MODULE$;
    private final DynamicVariable<PrintStream> outVar;
    private final DynamicVariable<PrintStream> errVar;
    private final DynamicVariable<BufferedReader> inVar;

    static {
        new Console$();
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

    private DynamicVariable<PrintStream> outVar() {
        return this.outVar;
    }

    private DynamicVariable<PrintStream> errVar() {
        return this.errVar;
    }

    private DynamicVariable<BufferedReader> inVar() {
        return this.inVar;
    }

    @Override
    public void setOutDirect(PrintStream out) {
        this.outVar().value_$eq(out);
    }

    @Override
    public void setErrDirect(PrintStream err) {
        this.errVar().value_$eq(err);
    }

    @Override
    public void setInDirect(BufferedReader in) {
        this.inVar().value_$eq(in);
    }

    public PrintStream out() {
        return this.outVar().value();
    }

    public PrintStream err() {
        return this.errVar().value();
    }

    public BufferedReader in() {
        return this.inVar().value();
    }

    public <T> T withOut(PrintStream out, Function0<T> thunk) {
        return this.outVar().withValue(out, thunk);
    }

    public <T> T withOut(OutputStream out, Function0<T> thunk) {
        PrintStream printStream = new PrintStream(out);
        return this.outVar().withValue(printStream, thunk);
    }

    public <T> T withErr(PrintStream err, Function0<T> thunk) {
        return this.errVar().withValue(err, thunk);
    }

    public <T> T withErr(OutputStream err, Function0<T> thunk) {
        PrintStream printStream = new PrintStream(err);
        return this.errVar().withValue(printStream, thunk);
    }

    public <T> T withIn(Reader reader, Function0<T> thunk) {
        return this.inVar().withValue(new BufferedReader(reader), thunk);
    }

    public <T> T withIn(InputStream in, Function0<T> thunk) {
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        return this.inVar().withValue(new BufferedReader(inputStreamReader), thunk);
    }

    public void print(Object obj) {
        this.out().print(obj == null ? "null" : obj.toString());
    }

    public void flush() {
        this.out().flush();
    }

    public void println() {
        this.out().println();
    }

    public void println(Object x) {
        this.out().println(x);
    }

    public void printf(String text, Seq<Object> args) {
        Predef$ predef$ = Predef$.MODULE$;
        this.out().print(new StringOps(text).format(args));
    }

    private Console$() {
        MODULE$ = this;
        AnsiColor$class.$init$(this);
        this.outVar = new DynamicVariable<PrintStream>(System.out);
        this.errVar = new DynamicVariable<PrintStream>(System.err);
        this.inVar = new DynamicVariable<BufferedReader>(new BufferedReader(new InputStreamReader(System.in)));
    }
}

