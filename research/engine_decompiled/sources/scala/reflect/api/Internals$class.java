/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.reflect.api.Internals;
import scala.reflect.api.Universe;

public abstract class Internals$class {
    public static Internals.Importer mkImporter(Universe $this, Universe from0) {
        return $this.internal().createImporter(from0);
    }

    public static void $init$(Universe $this) {
    }
}

