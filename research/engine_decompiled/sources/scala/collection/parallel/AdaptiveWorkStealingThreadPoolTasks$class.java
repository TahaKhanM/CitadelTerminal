/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.collection.parallel.AdaptiveWorkStealingThreadPoolTasks;
import scala.collection.parallel.Task;

public abstract class AdaptiveWorkStealingThreadPoolTasks$class {
    public static AdaptiveWorkStealingThreadPoolTasks.WrappedTask newWrappedTask(AdaptiveWorkStealingThreadPoolTasks $this, Task b) {
        return new AdaptiveWorkStealingThreadPoolTasks.WrappedTask($this, b);
    }

    public static void $init$(AdaptiveWorkStealingThreadPoolTasks $this) {
    }
}

