/*
 * Decompiled with CFR 0.152.
 */
package scala.sys.process;

import java.io.File;
import scala.Function0;
import scala.sys.process.ProcessBuilder;
import scala.sys.process.ProcessBuilder$;
import scala.sys.process.ProcessBuilderImpl;

public abstract class ProcessBuilder$Source$class {
    public static ProcessBuilder $hash$greater(ProcessBuilder.Source $this, File f) {
        return ProcessBuilder$Source$class.toFile($this, f, false);
    }

    public static ProcessBuilder $hash$greater$greater(ProcessBuilder.Source $this, File f) {
        return ProcessBuilder$Source$class.toFile($this, f, true);
    }

    public static ProcessBuilder $hash$greater(ProcessBuilder.Source $this, Function0 out) {
        return $this.$hash$greater(new ProcessBuilderImpl.OStreamBuilder(ProcessBuilder$.MODULE$, out, "<output stream>"));
    }

    public static ProcessBuilder $hash$greater(ProcessBuilder.Source $this, ProcessBuilder b) {
        return new ProcessBuilderImpl.PipedBuilder(ProcessBuilder$.MODULE$, $this.toSource(), b, false);
    }

    public static ProcessBuilder cat(ProcessBuilder.Source $this) {
        return $this.toSource();
    }

    private static ProcessBuilder toFile(ProcessBuilder.Source $this, File f, boolean append2) {
        return $this.$hash$greater(new ProcessBuilderImpl.FileOutput(ProcessBuilder$.MODULE$, f, append2));
    }

    public static void $init$(ProcessBuilder.Source $this) {
    }
}

