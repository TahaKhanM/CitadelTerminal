/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import java.util.concurrent.ThreadPoolExecutor;
import scala.collection.parallel.ThreadPoolTasks;
import scala.collection.parallel.ThreadPoolTasks$class;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public abstract class ThreadPoolTasks$WrappedTask$class {
    public static void start(ThreadPoolTasks.WrappedTask $this) {
        ThreadPoolTasks.WrappedTask wrappedTask = $this;
        synchronized (wrappedTask) {
            Throwable throwable2;
            ThreadPoolExecutor threadPoolExecutor = $this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer().executor();
            synchronized (threadPoolExecutor) {
                try {
                    ThreadPoolTasks$class.scala$collection$parallel$ThreadPoolTasks$$incrTasks($this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer());
                    $this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer().executor().submit($this);
                    // MONITOREXIT @DISABLED, blocks:[0, 1, 3, 4] lbl10 : MonitorExitStatement: MONITOREXIT : var2_2
                    // ** MonitorExit[$this] (shouldn't be in output)
                    return;
                }
                catch (Throwable throwable2) {
                }
            }
            throw throwable2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void sync(ThreadPoolTasks.WrappedTask $this) {
        ThreadPoolTasks.WrappedTask wrappedTask = $this;
        synchronized (wrappedTask) {
            Throwable throwable2;
            ThreadPoolExecutor threadPoolExecutor = $this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer().executor();
            synchronized (threadPoolExecutor) {
                try {
                    BoxedUnit boxedUnit;
                    int coresize = $this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer().executor().getCorePoolSize();
                    if (coresize < $this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer().totaltasks()) {
                        $this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer().executor().setCorePoolSize(coresize + 1);
                        boxedUnit = BoxedUnit.UNIT;
                    } else {
                        boxedUnit = BoxedUnit.UNIT;
                    }
                }
                catch (Throwable throwable2) {
                }
            }
            throw throwable2;
            while (true) {
                if ($this.completed()) {
                    // ** MonitorExit[$this] (shouldn't be in output)
                    return;
                }
                $this.wait();
            }
        }
    }

    public static boolean tryCancel(ThreadPoolTasks.WrappedTask $this) {
        ThreadPoolTasks.WrappedTask wrappedTask = $this;
        synchronized (wrappedTask) {
            Boolean bl;
            if ($this.owned()) {
                bl = BoxesRunTime.boxToBoolean(false);
            } else {
                $this.owned_$eq(true);
                bl = BoxesRunTime.boxToBoolean(true);
            }
            Boolean bl2 = bl;
            // ** MonitorExit[$this] (shouldn't be in output)
            return BoxesRunTime.unboxToBoolean(bl2);
        }
    }

    public static void run(ThreadPoolTasks.WrappedTask $this) {
        boolean isOkToRun = false;
        ThreadPoolTasks.WrappedTask wrappedTask = $this;
        synchronized (wrappedTask) {
            block5: {
                BoxedUnit boxedUnit;
                if ($this.owned()) {
                    boxedUnit = BoxedUnit.UNIT;
                } else {
                    $this.owned_$eq(true);
                    isOkToRun = true;
                    boxedUnit = BoxedUnit.UNIT;
                }
                // ** MonitorExit[$this] (shouldn't be in output)
                if (!isOkToRun) break block5;
                $this.compute();
            }
            return;
        }
    }

    /*
     * Loose catch block
     */
    public static void release(ThreadPoolTasks.WrappedTask $this) {
        ThreadPoolTasks.WrappedTask wrappedTask = $this;
        synchronized (wrappedTask) {
            Throwable throwable2;
            $this.completed_$eq(true);
            ThreadPoolExecutor threadPoolExecutor = $this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer().executor();
            synchronized (threadPoolExecutor) {
                ThreadPoolTasks$class.scala$collection$parallel$ThreadPoolTasks$$decrTasks($this.scala$collection$parallel$ThreadPoolTasks$WrappedTask$$$outer());
                // MONITOREXIT @DISABLED, blocks:[0, 1, 3, 4] lbl10 : MonitorExitStatement: MONITOREXIT : var2_2
                $this.notifyAll();
            }
            // ** MonitorExit[$this] (shouldn't be in output)
            return;
            {
                catch (Throwable throwable2) {
                }
            }
            throw throwable2;
        }
    }

    public static void $init$(ThreadPoolTasks.WrappedTask $this) {
        $this.owned_$eq(false);
        $this.completed_$eq(false);
    }
}

