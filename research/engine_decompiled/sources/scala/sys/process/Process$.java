/*
 * Decompiled with CFR 0.152.
 */
package scala.sys.process;

import java.io.File;
import java.net.URL;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.Seq;
import scala.sys.process.ProcessBuilder;
import scala.sys.process.ProcessCreation;
import scala.sys.process.ProcessCreation$class;
import scala.sys.process.ProcessImpl;
import scala.sys.process.ProcessImpl$Future$;
import scala.sys.process.ProcessImpl$Spawn$;
import scala.sys.process.ProcessImpl$class;

public final class Process$
implements ProcessImpl,
ProcessCreation {
    public static final Process$ MODULE$;
    private volatile ProcessImpl$Spawn$ Spawn$module;
    private volatile ProcessImpl$Future$ Future$module;

    static {
        new Process$();
    }

    @Override
    public ProcessBuilder apply(String command) {
        return ProcessCreation$class.apply((ProcessCreation)this, command);
    }

    @Override
    public ProcessBuilder apply(Seq<String> command) {
        return ProcessCreation$class.apply((ProcessCreation)this, command);
    }

    @Override
    public ProcessBuilder apply(String command, Seq<String> arguments) {
        return ProcessCreation$class.apply((ProcessCreation)this, command, arguments);
    }

    @Override
    public ProcessBuilder apply(String command, File cwd, Seq<Tuple2<String, String>> extraEnv) {
        return ProcessCreation$class.apply((ProcessCreation)this, command, cwd, extraEnv);
    }

    @Override
    public ProcessBuilder apply(Seq<String> command, File cwd, Seq<Tuple2<String, String>> extraEnv) {
        return ProcessCreation$class.apply((ProcessCreation)this, command, cwd, extraEnv);
    }

    @Override
    public ProcessBuilder apply(String command, Option<File> cwd, Seq<Tuple2<String, String>> extraEnv) {
        return ProcessCreation$class.apply((ProcessCreation)this, command, cwd, extraEnv);
    }

    @Override
    public ProcessBuilder apply(Seq<String> command, Option<File> cwd, Seq<Tuple2<String, String>> extraEnv) {
        return ProcessCreation$class.apply((ProcessCreation)this, command, cwd, extraEnv);
    }

    @Override
    public ProcessBuilder apply(java.lang.ProcessBuilder builder) {
        return ProcessCreation$class.apply((ProcessCreation)this, builder);
    }

    @Override
    public ProcessBuilder.FileBuilder apply(File file) {
        return ProcessCreation$class.apply((ProcessCreation)this, file);
    }

    @Override
    public ProcessBuilder.URLBuilder apply(URL url) {
        return ProcessCreation$class.apply((ProcessCreation)this, url);
    }

    @Override
    public ProcessBuilder apply(boolean value) {
        return ProcessCreation$class.apply((ProcessCreation)this, value);
    }

    @Override
    public ProcessBuilder apply(String name, Function0<Object> exitValue) {
        return ProcessCreation$class.apply((ProcessCreation)this, name, exitValue);
    }

    @Override
    public <T> Seq<ProcessBuilder.Source> applySeq(Seq<T> builders, Function1<T, ProcessBuilder.Source> convert) {
        return ProcessCreation$class.applySeq(this, builders, convert);
    }

    @Override
    public ProcessBuilder cat(ProcessBuilder.Source file, Seq<ProcessBuilder.Source> files2) {
        return ProcessCreation$class.cat(this, file, files2);
    }

    @Override
    public ProcessBuilder cat(Seq<ProcessBuilder.Source> files2) {
        return ProcessCreation$class.cat(this, files2);
    }

    private ProcessImpl$Spawn$ Spawn$lzycompute() {
        Process$ process$ = this;
        synchronized (process$) {
            if (this.Spawn$module == null) {
                this.Spawn$module = new ProcessImpl$Spawn$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Spawn$module;
        }
    }

    @Override
    public ProcessImpl$Spawn$ Spawn() {
        return this.Spawn$module == null ? this.Spawn$lzycompute() : this.Spawn$module;
    }

    private ProcessImpl$Future$ Future$lzycompute() {
        Process$ process$ = this;
        synchronized (process$) {
            if (this.Future$module == null) {
                this.Future$module = new ProcessImpl$Future$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Future$module;
        }
    }

    @Override
    public ProcessImpl$Future$ Future() {
        return this.Future$module == null ? this.Future$lzycompute() : this.Future$module;
    }

    private Process$() {
        MODULE$ = this;
        ProcessImpl$class.$init$(this);
        ProcessCreation$class.$init$(this);
    }
}

