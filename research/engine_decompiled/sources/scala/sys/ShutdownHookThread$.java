/*
 * Decompiled with CFR 0.152.
 */
package scala.sys;

import scala.Function0;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.sys.ShutdownHookThread;
import scala.sys.package$;

public final class ShutdownHookThread$ {
    public static final ShutdownHookThread$ MODULE$;
    private int hookNameCount;

    static {
        new ShutdownHookThread$();
    }

    private int hookNameCount() {
        return this.hookNameCount;
    }

    private void hookNameCount_$eq(int x$1) {
        this.hookNameCount = x$1;
    }

    public synchronized String scala$sys$ShutdownHookThread$$hookName() {
        this.hookNameCount_$eq(this.hookNameCount() + 1);
        return new StringBuilder().append((Object)"shutdownHook").append(BoxesRunTime.boxToInteger(this.hookNameCount())).toString();
    }

    /*
     * WARNING - void declaration
     */
    public ShutdownHookThread apply(Function0<BoxedUnit> body2) {
        void var2_2;
        ShutdownHookThread t = new ShutdownHookThread(body2){
            private final Function0 body$1;

            public void run() {
                this.body$1.apply$mcV$sp();
            }
            {
                this.body$1 = body$1;
                super(ShutdownHookThread$.MODULE$.scala$sys$ShutdownHookThread$$hookName());
            }
        };
        package$.MODULE$.runtime().addShutdownHook(t);
        return var2_2;
    }

    private ShutdownHookThread$() {
        MODULE$ = this;
        this.hookNameCount = 0;
    }
}

