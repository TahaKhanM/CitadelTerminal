/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import slogging.LoggerFactory$;
import slogging.StrictLogging;

public abstract class StrictLogging$class {
    public static void $init$(StrictLogging $this) {
        $this.slogging$StrictLogging$_setter_$logger_$eq(LoggerFactory$.MODULE$.getLogger($this.loggerName()));
    }
}

