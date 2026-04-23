/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.collection.parallel.Combiner;
import scala.collection.parallel.TaskSupport;
import scala.collection.parallel.package$;

public abstract class Combiner$class {
    public static TaskSupport combinerTaskSupport(Combiner $this) {
        TaskSupport taskSupport;
        TaskSupport cts = $this._combinerTaskSupport();
        if (cts == null) {
            $this._combinerTaskSupport_$eq(package$.MODULE$.defaultTaskSupport());
            taskSupport = package$.MODULE$.defaultTaskSupport();
        } else {
            taskSupport = cts;
        }
        return taskSupport;
    }

    public static void combinerTaskSupport_$eq(Combiner $this, TaskSupport cts) {
        $this._combinerTaskSupport_$eq(cts);
    }

    public static boolean canBeShared(Combiner $this) {
        return false;
    }

    public static Object resultWithTaskSupport(Combiner $this) {
        Object res = $this.result();
        return package$.MODULE$.setTaskSupport(res, $this.combinerTaskSupport());
    }

    public static void $init$(Combiner $this) {
        $this._combinerTaskSupport_$eq(package$.MODULE$.defaultTaskSupport());
    }
}

