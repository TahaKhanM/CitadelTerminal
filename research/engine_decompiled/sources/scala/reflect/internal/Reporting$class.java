/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.internal.FatalError;
import scala.reflect.internal.Positions;
import scala.reflect.internal.Reporting;
import scala.reflect.internal.util.Position;
import scala.runtime.Nothing$;

public abstract class Reporting$class {
    public static String supplementTyperState(Reporting $this, String errorMessage2) {
        return errorMessage2;
    }

    public static String supplementErrorMessage(Reporting $this, String errorMessage2) {
        return $this.currentRun().reporting().supplementErrorMessage(errorMessage2);
    }

    public static void inform(Reporting $this, String msg) {
        $this.inform(((Positions)((Object)$this)).NoPosition(), msg);
    }

    public static void warning(Reporting $this, String msg) {
        $this.warning(((Positions)((Object)$this)).NoPosition(), msg);
    }

    public static void globalError(Reporting $this, String msg) {
        $this.globalError(((Positions)((Object)$this)).NoPosition(), msg);
    }

    public static Nothing$ abort(Reporting $this, String msg) {
        String augmented = $this.supplementErrorMessage(msg);
        $this.globalError(augmented);
        throw new FatalError(augmented);
    }

    public static void inform(Reporting $this, Position pos, String msg) {
        $this.reporter().echo(pos, msg);
    }

    public static void warning(Reporting $this, Position pos, String msg) {
        $this.reporter().warning(pos, msg);
    }

    public static void globalError(Reporting $this, Position pos, String msg) {
        $this.reporter().error(pos, msg);
    }

    public static void $init$(Reporting $this) {
    }
}

