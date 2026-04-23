/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import slogging.LazyLogging;
import slogging.LoggerFactory$;
import slogging.UnderlyingLogger;

public abstract class LazyLogging$class {
    public static UnderlyingLogger logger(LazyLogging $this) {
        return LoggerFactory$.MODULE$.getLogger($this.loggerName());
    }

    public static void $init$(LazyLogging $this) {
    }
}

