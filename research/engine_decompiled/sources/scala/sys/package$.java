/*
 * Decompiled with CFR 0.152.
 */
package scala.sys;

import scala.Function0;
import scala.Predef$;
import scala.collection.IndexedSeq;
import scala.collection.JavaConverters$;
import scala.collection.MapLike;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;
import scala.sys.ShutdownHookThread;
import scala.sys.ShutdownHookThread$;
import scala.sys.SystemProperties;

public final class package$ {
    public static final package$ MODULE$;

    static {
        new package$();
    }

    public Nothing$ error(String message) {
        throw new RuntimeException(message);
    }

    public Nothing$ exit() {
        return this.exit(0);
    }

    public Nothing$ exit(int status) {
        System.exit(status);
        throw new Throwable();
    }

    public Runtime runtime() {
        return Runtime.getRuntime();
    }

    public SystemProperties props() {
        return new SystemProperties();
    }

    public Map<String, String> env() {
        return (Map)Map$.MODULE$.apply(((MapLike)JavaConverters$.MODULE$.mapAsScalaMapConverter(System.getenv()).asScala()).toSeq());
    }

    public ShutdownHookThread addShutdownHook(Function0<BoxedUnit> body2) {
        return ShutdownHookThread$.MODULE$.apply(body2);
    }

    public IndexedSeq<Thread> allThreads() {
        int num = Thread.activeCount();
        Thread[] tarray = new Thread[num];
        int got = Thread.enumerate(tarray);
        return Predef$.MODULE$.wrapRefArray((Object[])Predef$.MODULE$.refArrayOps((Object[])tarray).take(got));
    }

    private package$() {
        MODULE$ = this;
    }
}

