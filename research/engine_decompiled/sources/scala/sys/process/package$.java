/*
 * Decompiled with CFR 0.152.
 */
package scala.sys.process;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.net.URL;
import scala.Function1;
import scala.collection.JavaConversions$;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.sys.process.ProcessBuilder;
import scala.sys.process.ProcessImplicits;
import scala.sys.process.ProcessImplicits$class;

public final class package$
implements ProcessImplicits {
    public static final package$ MODULE$;

    static {
        new package$();
    }

    @Override
    public <T> Seq<ProcessBuilder.Source> buildersToProcess(Seq<T> builders, Function1<T, ProcessBuilder.Source> convert) {
        return ProcessImplicits$class.buildersToProcess(this, builders, convert);
    }

    @Override
    public ProcessBuilder builderToProcess(java.lang.ProcessBuilder builder) {
        return ProcessImplicits$class.builderToProcess(this, builder);
    }

    @Override
    public ProcessBuilder.FileBuilder fileToProcess(File file) {
        return ProcessImplicits$class.fileToProcess(this, file);
    }

    @Override
    public ProcessBuilder.URLBuilder urlToProcess(URL url) {
        return ProcessImplicits$class.urlToProcess(this, url);
    }

    @Override
    public ProcessBuilder stringToProcess(String command) {
        return ProcessImplicits$class.stringToProcess(this, command);
    }

    @Override
    public ProcessBuilder stringSeqToProcess(Seq<String> command) {
        return ProcessImplicits$class.stringSeqToProcess(this, command);
    }

    public List<String> javaVmArguments() {
        return JavaConversions$.MODULE$.asScalaBuffer(ManagementFactory.getRuntimeMXBean().getInputArguments()).toList();
    }

    public InputStream stdin() {
        return System.in;
    }

    public PrintStream stdout() {
        return System.out;
    }

    public PrintStream stderr() {
        return System.err;
    }

    private package$() {
        MODULE$ = this;
        ProcessImplicits$class.$init$(this);
    }
}

