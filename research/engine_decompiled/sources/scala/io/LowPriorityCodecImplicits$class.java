/*
 * Decompiled with CFR 0.152.
 */
package scala.io;

import scala.io.Codec;
import scala.io.Codec$;

public abstract class LowPriorityCodecImplicits$class {
    public static Codec fallbackSystemCodec(Codec$ $this) {
        return $this.defaultCharsetCodec();
    }

    public static void $init$(Codec$ $this) {
    }
}

