/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.collection.parallel.AdaptiveWorkStealingForkJoinTasks;
import scala.collection.parallel.Task;

public abstract class AdaptiveWorkStealingForkJoinTasks$class {
    public static AdaptiveWorkStealingForkJoinTasks.WrappedTask newWrappedTask(AdaptiveWorkStealingForkJoinTasks $this, Task b) {
        return new AdaptiveWorkStealingForkJoinTasks.WrappedTask($this, b);
    }

    public static void $init$(AdaptiveWorkStealingForkJoinTasks $this) {
    }
}

