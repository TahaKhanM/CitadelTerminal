/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import java.io.PrintStream;
import slogging.MessageFormatter;
import slogging.MessageFormatter$;
import slogging.PrintLogger$;
import slogging.UnderlyingLogger;
import slogging.UnderlyingLoggerFactory;
import slogging.UnderlyingLoggerFactory$class;

public final class PrintLoggerFactory$
implements UnderlyingLoggerFactory {
    public static final PrintLoggerFactory$ MODULE$;
    private PrintStream slogging$PrintLoggerFactory$$_errorStream;
    private PrintStream slogging$PrintLoggerFactory$$_warnStream;
    private PrintStream slogging$PrintLoggerFactory$$_infoStream;
    private PrintStream slogging$PrintLoggerFactory$$_debugStream;
    private PrintStream slogging$PrintLoggerFactory$$_traceStream;
    private MessageFormatter slogging$PrintLoggerFactory$$_formatter;

    static {
        new PrintLoggerFactory$();
    }

    @Override
    public UnderlyingLoggerFactory apply() {
        return UnderlyingLoggerFactory$class.apply(this);
    }

    public PrintStream slogging$PrintLoggerFactory$$_errorStream() {
        return this.slogging$PrintLoggerFactory$$_errorStream;
    }

    private void slogging$PrintLoggerFactory$$_errorStream_$eq(PrintStream x$1) {
        this.slogging$PrintLoggerFactory$$_errorStream = x$1;
    }

    public PrintStream slogging$PrintLoggerFactory$$_warnStream() {
        return this.slogging$PrintLoggerFactory$$_warnStream;
    }

    private void slogging$PrintLoggerFactory$$_warnStream_$eq(PrintStream x$1) {
        this.slogging$PrintLoggerFactory$$_warnStream = x$1;
    }

    public PrintStream slogging$PrintLoggerFactory$$_infoStream() {
        return this.slogging$PrintLoggerFactory$$_infoStream;
    }

    private void slogging$PrintLoggerFactory$$_infoStream_$eq(PrintStream x$1) {
        this.slogging$PrintLoggerFactory$$_infoStream = x$1;
    }

    public PrintStream slogging$PrintLoggerFactory$$_debugStream() {
        return this.slogging$PrintLoggerFactory$$_debugStream;
    }

    private void slogging$PrintLoggerFactory$$_debugStream_$eq(PrintStream x$1) {
        this.slogging$PrintLoggerFactory$$_debugStream = x$1;
    }

    public PrintStream slogging$PrintLoggerFactory$$_traceStream() {
        return this.slogging$PrintLoggerFactory$$_traceStream;
    }

    private void slogging$PrintLoggerFactory$$_traceStream_$eq(PrintStream x$1) {
        this.slogging$PrintLoggerFactory$$_traceStream = x$1;
    }

    public MessageFormatter slogging$PrintLoggerFactory$$_formatter() {
        return this.slogging$PrintLoggerFactory$$_formatter;
    }

    private void slogging$PrintLoggerFactory$$_formatter_$eq(MessageFormatter x$1) {
        this.slogging$PrintLoggerFactory$$_formatter = x$1;
    }

    public final PrintStream errorStream() {
        return this.slogging$PrintLoggerFactory$$_errorStream();
    }

    public final synchronized void errorStream_$eq(PrintStream s2) {
        this.slogging$PrintLoggerFactory$$_errorStream_$eq(s2);
    }

    public final PrintStream warnStream() {
        return this.slogging$PrintLoggerFactory$$_warnStream();
    }

    public final synchronized void warnStream_$eq(PrintStream s2) {
        this.slogging$PrintLoggerFactory$$_warnStream_$eq(s2);
    }

    public final PrintStream infoStream() {
        return this.slogging$PrintLoggerFactory$$_infoStream();
    }

    public final synchronized void infoStream_$eq(PrintStream s2) {
        this.slogging$PrintLoggerFactory$$_infoStream_$eq(s2);
    }

    public final PrintStream debugStream() {
        return this.slogging$PrintLoggerFactory$$_debugStream();
    }

    public final synchronized void debugStream_$eq(PrintStream s2) {
        this.slogging$PrintLoggerFactory$$_debugStream_$eq(s2);
    }

    public final PrintStream traceStream() {
        return this.slogging$PrintLoggerFactory$$_traceStream();
    }

    public final synchronized void traceStream_$eq(PrintStream s2) {
        this.slogging$PrintLoggerFactory$$_traceStream_$eq(s2);
    }

    public final MessageFormatter formatter() {
        return this.slogging$PrintLoggerFactory$$_formatter();
    }

    public final synchronized void formatter_$eq(MessageFormatter f) {
        this.slogging$PrintLoggerFactory$$_formatter_$eq(f);
    }

    @Override
    public UnderlyingLogger getUnderlyingLogger(String name) {
        return PrintLogger$.MODULE$;
    }

    private PrintLoggerFactory$() {
        MODULE$ = this;
        UnderlyingLoggerFactory$class.$init$(this);
        this.slogging$PrintLoggerFactory$$_errorStream = System.err;
        this.slogging$PrintLoggerFactory$$_warnStream = System.err;
        this.slogging$PrintLoggerFactory$$_infoStream = System.err;
        this.slogging$PrintLoggerFactory$$_debugStream = System.err;
        this.slogging$PrintLoggerFactory$$_traceStream = System.err;
        this.slogging$PrintLoggerFactory$$_formatter = MessageFormatter$.MODULE$.default();
    }
}

