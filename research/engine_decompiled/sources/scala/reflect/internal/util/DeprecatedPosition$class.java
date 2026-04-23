/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.None$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.SourceFile;
import scala.runtime.BoxesRunTime;

public abstract class DeprecatedPosition$class {
    public static Option offset(Position $this) {
        return $this.isDefined() ? new Some<Integer>(BoxesRunTime.boxToInteger($this.point())) : None$.MODULE$;
    }

    public static Position toSingleLine(Position $this) {
        return $this;
    }

    public static int safeLine(Position $this) {
        return $this.line();
    }

    public static String dbgString(Position $this) {
        return $this.showDebug();
    }

    public static Position inUltimateSource(Position $this, SourceFile source) {
        return source.positionInUltimateSource($this);
    }

    public static Tuple2 lineWithCarat(Position $this, int maxWidth2) {
        return new Tuple2<String, String>("", "");
    }

    public static Position withSource(Position $this, SourceFile source, int shift) {
        return $this.withSource(source).withShift(shift);
    }

    public static int startOrPoint(Position $this) {
        return $this.isRange() ? $this.start() : $this.point();
    }

    public static int endOrPoint(Position $this) {
        return $this.isRange() ? $this.end() : $this.point();
    }

    public static void $init$(Position $this) {
    }
}

