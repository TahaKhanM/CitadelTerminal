/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.collection.Seq;
import slogging.UnderlyingLogger;

public final class NullLogger$
implements UnderlyingLogger {
    public static final NullLogger$ MODULE$;
    private final boolean isErrorEnabled;
    private final boolean isWarnEnabled;
    private final boolean isInfoEnabled;
    private final boolean isDebugEnabled;
    private final boolean isTraceEnabled;

    static {
        new NullLogger$();
    }

    @Override
    public boolean isErrorEnabled() {
        return this.isErrorEnabled;
    }

    @Override
    public boolean isWarnEnabled() {
        return this.isWarnEnabled;
    }

    @Override
    public boolean isInfoEnabled() {
        return this.isInfoEnabled;
    }

    @Override
    public boolean isDebugEnabled() {
        return this.isDebugEnabled;
    }

    @Override
    public boolean isTraceEnabled() {
        return this.isTraceEnabled;
    }

    @Override
    public void error(String source, String message) {
    }

    @Override
    public void error(String source, String message, Throwable cause) {
    }

    @Override
    public void error(String source, String message, Seq<Object> args) {
    }

    @Override
    public void warn(String source, String message) {
    }

    @Override
    public void warn(String source, String message, Throwable cause) {
    }

    @Override
    public void warn(String source, String message, Seq<Object> args) {
    }

    @Override
    public void info(String source, String message) {
    }

    @Override
    public void info(String source, String message, Throwable cause) {
    }

    @Override
    public void info(String source, String message, Seq<Object> args) {
    }

    @Override
    public void debug(String source, String message) {
    }

    @Override
    public void debug(String source, String message, Throwable cause) {
    }

    @Override
    public void debug(String source, String message, Seq<Object> args) {
    }

    @Override
    public void trace(String source, String message) {
    }

    @Override
    public void trace(String source, String message, Throwable cause) {
    }

    @Override
    public void trace(String source, String message, Seq<Object> args) {
    }

    private NullLogger$() {
        MODULE$ = this;
        this.isErrorEnabled = false;
        this.isWarnEnabled = false;
        this.isInfoEnabled = false;
        this.isDebugEnabled = false;
        this.isTraceEnabled = false;
    }
}

