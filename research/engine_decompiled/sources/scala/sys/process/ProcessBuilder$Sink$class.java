/*
 * Decompiled with CFR 0.152.
 */
package scala.sys.process;

import java.io.File;
import java.net.URL;
import scala.Function0;
import scala.sys.process.ProcessBuilder;
import scala.sys.process.ProcessBuilder$;
import scala.sys.process.ProcessBuilderImpl;

public abstract class ProcessBuilder$Sink$class {
    public static ProcessBuilder $hash$less(ProcessBuilder.Sink $this, File f) {
        return $this.$hash$less(new ProcessBuilderImpl.FileInput(ProcessBuilder$.MODULE$, f));
    }

    public static ProcessBuilder $hash$less(ProcessBuilder.Sink $this, URL f) {
        return $this.$hash$less(new ProcessBuilderImpl.URLInput(ProcessBuilder$.MODULE$, f));
    }

    public static ProcessBuilder $hash$less(ProcessBuilder.Sink $this, Function0 in) {
        return $this.$hash$less(new ProcessBuilderImpl.IStreamBuilder(ProcessBuilder$.MODULE$, in, "<input stream>"));
    }

    public static ProcessBuilder $hash$less(ProcessBuilder.Sink $this, ProcessBuilder b) {
        return new ProcessBuilderImpl.PipedBuilder(ProcessBuilder$.MODULE$, b, $this.toSink(), false);
    }

    public static void $init$(ProcessBuilder.Sink $this) {
    }
}

