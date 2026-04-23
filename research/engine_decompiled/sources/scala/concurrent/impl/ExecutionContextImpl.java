/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Predef$;
import scala.collection.immutable.StringOps;
import scala.concurrent.BlockContext;
import scala.concurrent.CanAwait;
import scala.concurrent.ExecutionContext;
import scala.concurrent.ExecutionContext$class;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.forkjoin.ForkJoinPool;
import scala.concurrent.forkjoin.ForkJoinTask;
import scala.concurrent.forkjoin.ForkJoinWorkerThread;
import scala.concurrent.impl.ExecutionContextImpl$;
import scala.math.package$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ObjectRef;
import scala.runtime.RichDouble$;
import scala.util.control.NonFatal$;

@ScalaSignature(bytes="\u0006\u0001\u00055f!B\u0001\u0003\u0001\u0019A!\u0001F#yK\u000e,H/[8o\u0007>tG/\u001a=u\u00136\u0004HN\u0003\u0002\u0004\t\u0005!\u0011.\u001c9m\u0015\t)a!\u0001\u0006d_:\u001cWO\u001d:f]RT\u0011aB\u0001\u0006g\u000e\fG.Y\n\u0004\u0001%i\u0001C\u0001\u0006\f\u001b\u00051\u0011B\u0001\u0007\u0007\u0005\u0019\te.\u001f*fMB\u0011abD\u0007\u0002\t%\u0011\u0001\u0003\u0002\u0002\u0019\u000bb,7-\u001e;j_:\u001cuN\u001c;fqR,\u00050Z2vi>\u0014\b\u0002\u0003\n\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u000b\u0002\u0005\u0015\u001c8\u0001\u0001\t\u0003+mi\u0011A\u0006\u0006\u0003\u000b]Q!\u0001G\r\u0002\tU$\u0018\u000e\u001c\u0006\u00025\u0005!!.\u0019<b\u0013\tabC\u0001\u0005Fq\u0016\u001cW\u000f^8s\u0011!q\u0002A!A!\u0002\u0013y\u0012\u0001\u0003:fa>\u0014H/\u001a:\u0011\t)\u0001#EL\u0005\u0003C\u0019\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005\rZcB\u0001\u0013*\u001d\t)\u0003&D\u0001'\u0015\t93#\u0001\u0004=e>|GOP\u0005\u0002\u000f%\u0011!FB\u0001\ba\u0006\u001c7.Y4f\u0013\taSFA\u0005UQJ|w/\u00192mK*\u0011!F\u0002\t\u0003\u0015=J!\u0001\r\u0004\u0003\tUs\u0017\u000e\u001e\u0005\u0007e\u0001!\tAA\u001a\u0002\rqJg.\u001b;?)\r!dg\u000e\t\u0003k\u0001i\u0011A\u0001\u0005\u0006%E\u0002\r\u0001\u0006\u0005\u0006=E\u0002\ra\b\u0005\u0007s\u0001\u0001\u000b\u0011\u0002\u001e\u00021Ut7-Y;hQR,\u0005pY3qi&|g\u000eS1oI2,'\u000f\u0005\u0002<\u0003:\u0011AhP\u0007\u0002{)\u0011a(G\u0001\u0005Y\u0006tw-\u0003\u0002A{\u00051A\u000b\u001b:fC\u0012L!AQ\"\u00031Us7-Y;hQR,\u0005pY3qi&|g\u000eS1oI2,'O\u0003\u0002A{!9Q\t\u0001b\u0001\n\u00031\u0015\u0001C3yK\u000e,Ho\u001c:\u0016\u0003QAa\u0001\u0013\u0001!\u0002\u0013!\u0012!C3yK\u000e,Ho\u001c:!\r\u0011Q\u0005\u0001A&\u0003)\u0011+g-Y;miRC'/Z1e\r\u0006\u001cGo\u001c:z'\u0011IEj\u0014*\u0011\u0005qj\u0015B\u0001(>\u0005\u0019y%M[3diB\u0011Q\u0003U\u0005\u0003#Z\u0011Q\u0002\u00165sK\u0006$g)Y2u_JL\bCA*Z\u001d\t!v+D\u0001V\u0015\t1F!\u0001\u0005g_J\\'n\\5o\u0013\tAV+\u0001\u0007G_J\\'j\\5o!>|G.\u0003\u0002[7\nYbi\u001c:l\u0015>LgnV8sW\u0016\u0014H\u000b\u001b:fC\u00124\u0015m\u0019;pefT!\u0001W+\t\u0011uK%\u0011!Q\u0001\ny\u000b\u0001\u0002Z1f[>t\u0017n\u0019\t\u0003\u0015}K!\u0001\u0019\u0004\u0003\u000f\t{w\u000e\\3b]\")!'\u0013C\u0001ER\u00111-\u001a\t\u0003I&k\u0011\u0001\u0001\u0005\u0006;\u0006\u0004\rA\u0018\u0005\u0006O&#\t\u0001[\u0001\u0005o&\u0014X-\u0006\u0002jYR\u0011!.\u001e\t\u0003W2d\u0001\u0001B\u0003nM\n\u0007aNA\u0001U#\ty'\u000f\u0005\u0002\u000ba&\u0011\u0011O\u0002\u0002\b\u001d>$\b.\u001b8h!\ta4/\u0003\u0002u{\t1A\u000b\u001b:fC\u0012DQA\u001e4A\u0002)\fa\u0001\u001e5sK\u0006$\u0007\"\u0002=J\t\u0003I\u0018!\u00038foRC'/Z1e)\t\u0011(\u0010C\u0003|o\u0002\u0007A0\u0001\u0005sk:t\u0017M\u00197f!\taT0\u0003\u0002\u007f{\tA!+\u001e8oC\ndW\r\u0003\u0004y\u0013\u0012\u0005\u0011\u0011\u0001\u000b\u0005\u0003\u0007\tI\u0001E\u0002U\u0003\u000bI1!a\u0002V\u0005Q1uN]6K_&twk\u001c:lKJ$\u0006N]3bI\"9\u00111B@A\u0002\u00055\u0011a\u00014kaB\u0019A+a\u0004\n\u0007\u0005EQK\u0001\u0007G_J\\'j\\5o!>|G\u000eC\u0004\u0002\u0016\u0001!\t!a\u0006\u0002+\r\u0014X-\u0019;f\u000bb,7-\u001e;peN+'O^5dKV\u0011\u0011\u0011\u0004\t\u0004+\u0005m\u0011bAA\u000f-\tyQ\t_3dkR|'oU3sm&\u001cW\rC\u0004\u0002\"\u0001!\t!a\t\u0002\u000f\u0015DXmY;uKR\u0019a&!\n\t\rm\fy\u00021\u0001}\u0011\u001d\tI\u0003\u0001C\u0001\u0003W\tQB]3q_J$h)Y5mkJ,Gc\u0001\u0018\u0002.!9\u0011qFA\u0014\u0001\u0004\u0011\u0013!\u0001;\b\u0011\u0005M\"\u0001#\u0001\u0005\u0003k\tA#\u0012=fGV$\u0018n\u001c8D_:$X\r\u001f;J[Bd\u0007cA\u001b\u00028\u00199\u0011A\u0001E\u0001\t\u0005e2cAA\u001c\u0013!9!'a\u000e\u0005\u0002\u0005uBCAA\u001b\r\u001d\t\t%a\u000e\u0003\u0003\u0007\u00121#\u00113baR,GMR8sW*{\u0017N\u001c+bg.\u001cB!a\u0010\u0002FA!A+a\u0012/\u0013\r\tI%\u0016\u0002\r\r>\u00148NS8j]R\u000b7o\u001b\u0005\nw\u0006}\"\u0011!Q\u0001\nqDqAMA \t\u0003\ty\u0005\u0006\u0003\u0002R\u0005U\u0003\u0003BA*\u0003\u007fi!!a\u000e\t\rm\fi\u00051\u0001}\u0011!\tI&a\u0010\u0005F\u0005m\u0013\u0001D:fiJ\u000bwOU3tk2$Hc\u0001\u0018\u0002^!9\u0011qLA,\u0001\u0004q\u0013!A;\t\u0011\u0005\r\u0014q\bC#\u0003K\nAbZ3u%\u0006<(+Z:vYR$\u0012A\f\u0005\t\u0003S\ny\u0004\"\u0012\u0002l\u0005!Q\r_3d)\u0005q\u0006\u0002CA8\u0003o!\t!!\u001d\u0002\u0019\u0019\u0014x.\\#yK\u000e,Ho\u001c:\u0015\u000bQ\n\u0019(a\u001e\t\u000f\u0005U\u0014Q\u000ea\u0001)\u0005\tQ\r\u0003\u0005\u001f\u0003[\u0002\n\u00111\u0001 \u0011!\tY(a\u000e\u0005\u0002\u0005u\u0014a\u00054s_6,\u00050Z2vi>\u00148+\u001a:wS\u000e,GCBA@\u0003\u0017\u000biIE\u0003\u0002\u0002R\n)IB\u0004\u0002\u0004\u0006]\u0002!a \u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u00079\t9)C\u0002\u0002\n\u0012\u0011q$\u0012=fGV$\u0018n\u001c8D_:$X\r\u001f;Fq\u0016\u001cW\u000f^8s'\u0016\u0014h/[2f\u0011\u001d\u0011\u0012\u0011\u0010a\u0001\u00033A\u0001BHA=!\u0003\u0005\ra\b\u0005\u000b\u0003#\u000b9$%A\u0005\u0002\u0005M\u0015A\u00064s_6,\u00050Z2vi>\u0014H\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005U%fA\u0010\u0002\u0018.\u0012\u0011\u0011\u0014\t\u0005\u00037\u000b)+\u0004\u0002\u0002\u001e*!\u0011qTAQ\u0003%)hn\u00195fG.,GMC\u0002\u0002$\u001a\t!\"\u00198o_R\fG/[8o\u0013\u0011\t9+!(\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r\u0003\u0006\u0002,\u0006]\u0012\u0013!C\u0001\u0003'\u000bQD\u001a:p[\u0016CXmY;u_J\u001cVM\u001d<jG\u0016$C-\u001a4bk2$HE\r")
public class ExecutionContextImpl
implements ExecutionContextExecutor {
    public final Function1<Throwable, BoxedUnit> scala$concurrent$impl$ExecutionContextImpl$$reporter;
    public final Thread.UncaughtExceptionHandler scala$concurrent$impl$ExecutionContextImpl$$uncaughtExceptionHandler;
    private final Executor executor;

    public static Function1<Throwable, BoxedUnit> fromExecutorService$default$2() {
        return ExecutionContextImpl$.MODULE$.fromExecutorService$default$2();
    }

    public static Function1<Throwable, BoxedUnit> fromExecutor$default$2() {
        return ExecutionContextImpl$.MODULE$.fromExecutor$default$2();
    }

    public static ExecutionContextImpl fromExecutorService(ExecutorService executorService, Function1<Throwable, BoxedUnit> function1) {
        return ExecutionContextImpl$.MODULE$.fromExecutorService(executorService, function1);
    }

    public static ExecutionContextImpl fromExecutor(Executor executor, Function1<Throwable, BoxedUnit> function1) {
        return ExecutionContextImpl$.MODULE$.fromExecutor(executor, function1);
    }

    @Override
    public ExecutionContext prepare() {
        return ExecutionContext$class.prepare(this);
    }

    public Executor executor() {
        return this.executor;
    }

    /*
     * WARNING - void declaration
     */
    public ExecutorService createExecutorService() {
        ForkJoinPool forkJoinPool;
        int desiredParallelism = this.range$1(this.getInt$1("scala.concurrent.context.minThreads", "1"), this.getInt$1("scala.concurrent.context.numThreads", "x1"), this.getInt$1("scala.concurrent.context.maxThreads", "x1"));
        DefaultThreadFactory threadFactory = new DefaultThreadFactory(this, true);
        try {
            forkJoinPool = new ForkJoinPool(desiredParallelism, threadFactory, this.scala$concurrent$impl$ExecutionContextImpl$$uncaughtExceptionHandler, true);
        }
        catch (Throwable throwable) {
            void var5_5;
            Option<Throwable> option = NonFatal$.MODULE$.unapply(throwable);
            if (option.isEmpty()) {
                throw throwable;
            }
            System.err.println("Failed to create ForkJoinPool for the default ExecutionContext, falling back to ThreadPoolExecutor");
            option.get().printStackTrace(System.err);
            ThreadPoolExecutor exec2 = new ThreadPoolExecutor(desiredParallelism, desiredParallelism, 5L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(), threadFactory);
            exec2.allowCoreThreadTimeOut(true);
            forkJoinPool = var5_5;
        }
        return forkJoinPool;
    }

    @Override
    public void execute(Runnable runnable) {
        Executor executor = this.executor();
        if (executor instanceof ForkJoinPool) {
            ForkJoinWorkerThread forkJoinWorkerThread;
            ForkJoinTask forkJoinTask;
            ForkJoinPool forkJoinPool = (ForkJoinPool)executor;
            ForkJoinTask forkJoinTask2 = runnable instanceof ForkJoinTask ? (forkJoinTask = (ForkJoinTask)((Object)runnable)) : new AdaptedForkJoinTask(runnable);
            Thread thread = Thread.currentThread();
            if (thread instanceof ForkJoinWorkerThread && (forkJoinWorkerThread = (ForkJoinWorkerThread)thread).getPool() == forkJoinPool) {
                forkJoinTask2.fork();
            } else {
                forkJoinPool.execute(forkJoinTask2);
            }
        } else {
            executor.execute(runnable);
        }
    }

    @Override
    public void reportFailure(Throwable t) {
        this.scala$concurrent$impl$ExecutionContextImpl$$reporter.apply(t);
    }

    private final int getInt$1(String name, String string2) {
        int n;
        String string3;
        String string4;
        try {
            string4 = System.getProperty(name, string2);
        }
        catch (SecurityException securityException) {
            string4 = string3 = string2;
        }
        if (string3.charAt(0) == 'x') {
            String string5 = string3.substring(1);
            Predef$ predef$ = Predef$.MODULE$;
            double d = (double)Runtime.getRuntime().availableProcessors() * new StringOps(string5).toDouble();
            Predef$ predef$2 = Predef$.MODULE$;
            n = (int)RichDouble$.MODULE$.ceil$extension(d);
        } else {
            Predef$ predef$ = Predef$.MODULE$;
            n = new StringOps(string3).toInt();
        }
        return n;
    }

    private final int range$1(int floor, int desired, int ceiling) {
        return package$.MODULE$.min(package$.MODULE$.max(floor, desired), ceiling);
    }

    public ExecutionContextImpl(Executor es, Function1<Throwable, BoxedUnit> reporter) {
        this.scala$concurrent$impl$ExecutionContextImpl$$reporter = reporter;
        ExecutionContext$class.$init$(this);
        this.scala$concurrent$impl$ExecutionContextImpl$$uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler(this){
            private final /* synthetic */ ExecutionContextImpl $outer;

            public void uncaughtException(Thread thread, Throwable cause) {
                this.$outer.scala$concurrent$impl$ExecutionContextImpl$$reporter.apply(cause);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
        Executor executor = es == null ? this.createExecutorService() : es;
        this.executor = executor;
    }

    public static final class AdaptedForkJoinTask
    extends ForkJoinTask<BoxedUnit> {
        private final Runnable runnable;

        @Override
        public final void setRawResult(BoxedUnit u) {
        }

        @Override
        public final void getRawResult() {
        }

        @Override
        public final boolean exec() {
            try {
                this.runnable.run();
                return true;
            }
            catch (Throwable throwable) {
                Thread t = Thread.currentThread();
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler = t.getUncaughtExceptionHandler();
                if (uncaughtExceptionHandler == null) {
                } else {
                    uncaughtExceptionHandler.uncaughtException(t, throwable);
                }
                throw throwable;
            }
        }

        public AdaptedForkJoinTask(Runnable runnable) {
            this.runnable = runnable;
        }
    }

    public class DefaultThreadFactory
    implements ThreadFactory,
    ForkJoinPool.ForkJoinWorkerThreadFactory {
        private final boolean daemonic;
        public final /* synthetic */ ExecutionContextImpl $outer;

        public <T extends Thread> T wire(T thread) {
            thread.setDaemon(this.daemonic);
            thread.setUncaughtExceptionHandler(this.scala$concurrent$impl$ExecutionContextImpl$DefaultThreadFactory$$$outer().scala$concurrent$impl$ExecutionContextImpl$$uncaughtExceptionHandler);
            return thread;
        }

        @Override
        public Thread newThread(Runnable runnable) {
            return this.wire(new Thread(runnable));
        }

        @Override
        public ForkJoinWorkerThread newThread(ForkJoinPool fjp) {
            return this.wire(new BlockContext(this, fjp){

                public <T> T blockOn(Function0<T> thunk, CanAwait permission) {
                    ObjectRef<Object> result2 = ObjectRef.create(null);
                    ForkJoinPool.managedBlock(new ForkJoinPool.ManagedBlocker(this, thunk, result2){
                        private volatile boolean isdone;
                        private final Function0 thunk$1;
                        private final ObjectRef result$1;

                        private boolean isdone() {
                            return this.isdone;
                        }

                        private void isdone_$eq(boolean x$1) {
                            this.isdone = x$1;
                        }

                        public boolean block() {
                            try {
                                this.result$1.elem = this.thunk$1.apply();
                                return true;
                            }
                            finally {
                                this.isdone_$eq(true);
                            }
                        }

                        public boolean isReleasable() {
                            return this.isdone();
                        }
                        {
                            this.thunk$1 = thunk$1;
                            this.result$1 = result$1;
                            this.isdone = false;
                        }
                    });
                    return result2.elem;
                }
            });
        }

        public /* synthetic */ ExecutionContextImpl scala$concurrent$impl$ExecutionContextImpl$DefaultThreadFactory$$$outer() {
            return this.$outer;
        }

        public DefaultThreadFactory(ExecutionContextImpl $outer, boolean daemonic) {
            this.daemonic = daemonic;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }
}

