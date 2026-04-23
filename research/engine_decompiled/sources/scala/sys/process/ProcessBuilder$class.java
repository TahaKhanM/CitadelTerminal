/*
 * Decompiled with CFR 0.152.
 */
package scala.sys.process;

import scala.collection.immutable.Stream;
import scala.sys.process.ProcessBuilder;
import scala.sys.process.ProcessLogger;

public abstract class ProcessBuilder$class {
    public static Stream lines(ProcessBuilder $this) {
        return $this.lineStream();
    }

    public static Stream lines(ProcessBuilder $this, ProcessLogger log) {
        return $this.lineStream(log);
    }

    public static Stream lines_$bang(ProcessBuilder $this) {
        return $this.lineStream_$bang();
    }

    public static Stream lines_$bang(ProcessBuilder $this, ProcessLogger log) {
        return $this.lineStream_$bang(log);
    }

    public static void $init$(ProcessBuilder $this) {
    }
}

