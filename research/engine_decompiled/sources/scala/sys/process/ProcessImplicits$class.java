/*
 * Decompiled with CFR 0.152.
 */
package scala.sys.process;

import java.io.File;
import java.net.URL;
import scala.Function1;
import scala.collection.Seq;
import scala.sys.process.Process$;
import scala.sys.process.ProcessBuilder;
import scala.sys.process.ProcessCreation$class;
import scala.sys.process.ProcessImplicits;

public abstract class ProcessImplicits$class {
    public static Seq buildersToProcess(ProcessImplicits $this, Seq builders, Function1 convert) {
        return ProcessCreation$class.applySeq(Process$.MODULE$, builders, convert);
    }

    public static ProcessBuilder builderToProcess(ProcessImplicits $this, java.lang.ProcessBuilder builder) {
        return Process$.MODULE$.apply(builder);
    }

    public static ProcessBuilder.FileBuilder fileToProcess(ProcessImplicits $this, File file) {
        return Process$.MODULE$.apply(file);
    }

    public static ProcessBuilder.URLBuilder urlToProcess(ProcessImplicits $this, URL url) {
        return Process$.MODULE$.apply(url);
    }

    public static ProcessBuilder stringToProcess(ProcessImplicits $this, String command) {
        return Process$.MODULE$.apply(command);
    }

    public static ProcessBuilder stringSeqToProcess(ProcessImplicits $this, Seq command) {
        return Process$.MODULE$.apply(command);
    }

    public static void $init$(ProcessImplicits $this) {
    }
}

