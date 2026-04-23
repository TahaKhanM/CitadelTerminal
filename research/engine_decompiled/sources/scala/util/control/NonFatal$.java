/*
 * Decompiled with CFR 0.152.
 */
package scala.util.control;

import scala.None$;
import scala.Option;
import scala.Some;
import scala.util.control.ControlThrowable;

public final class NonFatal$ {
    public static final NonFatal$ MODULE$;

    static {
        new NonFatal$();
    }

    public boolean apply(Throwable t) {
        boolean bl = t instanceof VirtualMachineError ? true : (t instanceof ThreadDeath ? true : (t instanceof InterruptedException ? true : (t instanceof LinkageError ? true : t instanceof ControlThrowable)));
        boolean bl2 = !bl;
        return bl2;
    }

    public Option<Throwable> unapply(Throwable t) {
        return this.apply(t) ? new Some<Throwable>(t) : None$.MODULE$;
    }

    private NonFatal$() {
        MODULE$ = this;
    }
}

