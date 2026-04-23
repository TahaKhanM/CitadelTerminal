/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent;

import java.util.concurrent.TimeoutException;
import scala.Serializable;
import scala.concurrent.AwaitPermission$;
import scala.concurrent.Awaitable;
import scala.concurrent.BlockContext$;
import scala.concurrent.duration.Duration;
import scala.concurrent.package$;

public final class Await$ {
    public static final Await$ MODULE$;

    static {
        new Await$();
    }

    public <T> Awaitable<T> ready(Awaitable<T> awaitable, Duration atMost) throws TimeoutException, InterruptedException {
        Serializable serializable = new Serializable(awaitable, atMost){
            public static final long serialVersionUID = 0L;
            private final Awaitable awaitable$2;
            private final Duration atMost$2;

            public final Awaitable<T> apply() {
                return this.awaitable$2.ready(this.atMost$2, AwaitPermission$.MODULE$);
            }
            {
                this.awaitable$2 = awaitable$2;
                this.atMost$2 = atMost$2;
            }
        };
        package$ package$2 = package$.MODULE$;
        return (Awaitable)BlockContext$.MODULE$.current().blockOn(serializable, AwaitPermission$.MODULE$);
    }

    public <T> T result(Awaitable<T> awaitable, Duration atMost) throws Exception {
        Serializable serializable = new Serializable(awaitable, atMost){
            public static final long serialVersionUID = 0L;
            private final Awaitable awaitable$1;
            private final Duration atMost$1;

            public final T apply() {
                return this.awaitable$1.result(this.atMost$1, AwaitPermission$.MODULE$);
            }
            {
                this.awaitable$1 = awaitable$1;
                this.atMost$1 = atMost$1;
            }
        };
        package$ package$2 = package$.MODULE$;
        return BlockContext$.MODULE$.current().blockOn(serializable, AwaitPermission$.MODULE$);
    }

    private Await$() {
        MODULE$ = this;
    }
}

