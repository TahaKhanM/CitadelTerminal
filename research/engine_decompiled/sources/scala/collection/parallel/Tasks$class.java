/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.collection.generic.Growable;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuffer$;
import scala.collection.parallel.Tasks;

public abstract class Tasks$class {
    public static ArrayBuffer debuglog(Tasks $this, String s2) {
        Tasks tasks = $this;
        synchronized (tasks) {
            Growable growable = $this.debugMessages().$plus$eq((Object)s2);
            // ** MonitorExit[$this] (shouldn't be in output)
            return (ArrayBuffer)growable;
        }
    }

    public static void $init$(Tasks $this) {
        $this.scala$collection$parallel$Tasks$_setter_$debugMessages_$eq((ArrayBuffer)ArrayBuffer$.MODULE$.apply(Nil$.MODULE$));
    }
}

