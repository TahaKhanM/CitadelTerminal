/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Function0;
import scala.Function1;
import scala.None$;
import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.parallel.FutureTasks$$anonfun$scala$collection$parallel$FutureTasks$;
import scala.collection.parallel.Task;
import scala.collection.parallel.Tasks;
import scala.collection.parallel.Tasks$class;
import scala.concurrent.Await$;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration$;
import scala.concurrent.impl.Future;
import scala.concurrent.impl.Future$;
import scala.math.package$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(bytes="\u0006\u0001\u00014Q!\u0001\u0002\u0003\u0005!\u00111BR;ukJ,G+Y:lg*\u00111\u0001B\u0001\ta\u0006\u0014\u0018\r\u001c7fY*\u0011QAB\u0001\u000bG>dG.Z2uS>t'\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0014\u0007\u0001IQ\u0002\u0005\u0002\u000b\u00175\ta!\u0003\u0002\r\r\t1\u0011I\\=SK\u001a\u0004\"AD\b\u000e\u0003\tI!\u0001\u0005\u0002\u0003\u000bQ\u000b7o[:\t\u0011I\u0001!\u0011!Q\u0001\nQ\t\u0001\"\u001a=fGV$xN]\u0002\u0001!\t)\u0002$D\u0001\u0017\u0015\t9b!\u0001\u0006d_:\u001cWO\u001d:f]RL!!\u0007\f\u0003!\u0015CXmY;uS>t7i\u001c8uKb$\b\"B\u000e\u0001\t\u0003a\u0012A\u0002\u001fj]&$h\b\u0006\u0002\u001e=A\u0011a\u0002\u0001\u0005\u0006%i\u0001\r\u0001\u0006\u0005\bA\u0001\u0011\r\u0011\"\u0003\"\u0003!i\u0017\r\u001f3faRDW#\u0001\u0012\u0011\u0005)\u0019\u0013B\u0001\u0013\u0007\u0005\rIe\u000e\u001e\u0005\u0007M\u0001\u0001\u000b\u0011\u0002\u0012\u0002\u00135\f\u0007\u0010Z3qi\"\u0004\u0003b\u0002\u0015\u0001\u0005\u0004%\t!K\u0001\fK:4\u0018N]8o[\u0016tG/F\u0001\u0015\u0011\u0019Y\u0003\u0001)A\u0005)\u0005aQM\u001c<je>tW.\u001a8uA!)Q\u0006\u0001C\u0005]\u0005!Q\r_3d+\ryS\u0007\u0012\u000b\u0003ay\u00022!F\u00194\u0013\t\u0011dC\u0001\u0004GkR,(/\u001a\t\u0003iUb\u0001\u0001B\u00037Y\t\u0007qGA\u0001S#\tA4\b\u0005\u0002\u000bs%\u0011!H\u0002\u0002\b\u001d>$\b.\u001b8h!\tQA(\u0003\u0002>\r\t\u0019\u0011I\\=\t\u000b}b\u0003\u0019\u0001!\u0002\u0019Q|\u0007\u000fT3wK2$\u0016m]6\u0011\t9\t5gQ\u0005\u0003\u0005\n\u0011A\u0001V1tWB\u0011A\u0007\u0012\u0003\u0006\u000b2\u0012\ra\u000e\u0002\u0003)BDQa\u0012\u0001\u0005\u0002!\u000bq!\u001a=fGV$X-F\u0002J\u001dN#\"AS(\u0011\u0007)YU*\u0003\u0002M\r\tIa)\u001e8di&|g\u000e\r\t\u0003i9#QA\u000e$C\u0002]BQ\u0001\u0015$A\u0002E\u000bA\u0001^1tWB!a\"Q'S!\t!4\u000bB\u0003F\r\n\u0007q\u0007C\u0003V\u0001\u0011\u0005a+\u0001\u000bfq\u0016\u001cW\u000f^3B]\u0012<\u0016-\u001b;SKN,H\u000e^\u000b\u0004/fkFC\u0001-[!\t!\u0014\fB\u00037)\n\u0007q\u0007C\u0003Q)\u0002\u00071\f\u0005\u0003\u000f\u0003bc\u0006C\u0001\u001b^\t\u0015)EK1\u00018\u0011\u0015y\u0006\u0001\"\u0001\"\u0003A\u0001\u0018M]1mY\u0016d\u0017n]7MKZ,G\u000e")
public final class FutureTasks
implements Tasks {
    private final int maxdepth;
    private final ExecutionContext environment;
    private final ArrayBuffer<String> debugMessages;

    @Override
    public ArrayBuffer<String> debugMessages() {
        return this.debugMessages;
    }

    @Override
    public void scala$collection$parallel$Tasks$_setter_$debugMessages_$eq(ArrayBuffer x$1) {
        this.debugMessages = x$1;
    }

    @Override
    public ArrayBuffer<String> debuglog(String s2) {
        return Tasks$class.debuglog(this, s2);
    }

    private int maxdepth() {
        return this.maxdepth;
    }

    @Override
    public ExecutionContext environment() {
        return this.environment;
    }

    private <R, Tp> Future<R> exec(Task<R, Tp> topLevelTask) {
        ExecutionContext ec = this.environment();
        return this.scala$collection$parallel$FutureTasks$$compute$1(topLevelTask, 0, ec).map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final R apply(Task<R, Tp> t) {
                t.forwardThrowable();
                return t.result();
            }
        }, ec);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public <R, Tp> Function0<R> execute(Task<R, Tp> task) {
        void var3_3;
        Future<R> future = this.exec(task);
        Serializable callback = new Serializable(this, future){
            public static final long serialVersionUID = 0L;
            private final Future future$1;

            public final R apply() {
                return (R)Await$.MODULE$.result(this.future$1, Duration$.MODULE$.Inf());
            }
            {
                this.future$1 = future$1;
            }
        };
        return var3_3;
    }

    @Override
    public <R, Tp> R executeAndWaitResult(Task<R, Tp> task) {
        return this.execute(task).apply();
    }

    @Override
    public int parallelismLevel() {
        return Runtime.getRuntime().availableProcessors();
    }

    public final Future scala$collection$parallel$FutureTasks$$compute$1(Task task, int depth, ExecutionContext ec$1) {
        Future future;
        if (task.shouldSplitFurther() && depth < this.maxdepth()) {
            Seq subtasks = task.split();
            Iterator subfutures = subtasks.iterator().map(new Serializable(this, ec$1, depth){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ FutureTasks $outer;
                private final ExecutionContext ec$1;
                private final int depth$1;

                public final Future<Task<R, Tp>> apply(Task<R, Tp> subtask) {
                    return this.$outer.scala$collection$parallel$FutureTasks$$compute$1(subtask, this.depth$1 + 1, this.ec$1);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.ec$1 = ec$1;
                    this.depth$1 = depth$1;
                }
            });
            future = ((Future)subfutures.reduceLeft(new Serializable(this, ec$1){
                public static final long serialVersionUID = 0L;
                public final ExecutionContext ec$1;

                public final Future<Task<R, Tp>> apply(Future<Task<R, Tp>> firstFuture, Future<Task<R, Tp>> nextFuture) {
                    return firstFuture.flatMap(new Serializable(this, nextFuture){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ $anonfun$scala$collection$parallel$FutureTasks$$compute$1$2 $outer;
                        private final Future nextFuture$1;

                        public final Future<Task<R, Tp>> apply(Task<R, Tp> firstTask) {
                            return this.nextFuture$1.map(new Serializable(this, firstTask){
                                public static final long serialVersionUID = 0L;
                                private final Task firstTask$1;

                                public final Task<R, Tp> apply(Task<R, Tp> nextTask) {
                                    this.firstTask$1.tryMerge(nextTask.repr());
                                    return this.firstTask$1;
                                }
                                {
                                    this.firstTask$1 = firstTask$1;
                                }
                            }, this.$outer.ec$1);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.nextFuture$1 = nextFuture$1;
                        }
                    }, this.ec$1);
                }
                {
                    this.ec$1 = ec$1;
                }
            })).andThen(new Serializable(this, task){
                public static final long serialVersionUID = 0L;
                private final Task task$1;

                public final <A1 extends Try<Task<R, Tp>>, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                    Object object;
                    if (x1 instanceof Success) {
                        Success success = (Success)x1;
                        this.task$1.throwable_$eq(((Task)success.value()).throwable());
                        this.task$1.result_$eq(((Task)success.value()).result());
                        object = BoxedUnit.UNIT;
                    } else if (x1 instanceof Failure) {
                        Failure failure = (Failure)x1;
                        this.task$1.throwable_$eq(failure.exception());
                        object = BoxedUnit.UNIT;
                    } else {
                        object = function1.apply(x1);
                    }
                    return object;
                }

                public final boolean isDefinedAt(Try<Task<R, Tp>> x1) {
                    boolean bl = x1 instanceof Success ? true : x1 instanceof Failure;
                    return bl;
                }
                {
                    this.task$1 = task$1;
                }
            }, ec$1);
        } else {
            Serializable serializable = new Serializable(this, task){
                public static final long serialVersionUID = 0L;
                private final Task task$1;

                public final Task<R, Tp> apply() {
                    this.task$1.tryLeaf(None$.MODULE$);
                    return this.task$1;
                }
                {
                    this.task$1 = task$1;
                }
            };
            scala.concurrent.Future$ future$ = scala.concurrent.Future$.MODULE$;
            Future$ future$2 = Future$.MODULE$;
            Future.PromiseCompletingRunnable runnable1 = new Future.PromiseCompletingRunnable(serializable);
            ec$1.prepare().execute(runnable1);
            future = runnable1.promise().future();
        }
        return future;
    }

    public FutureTasks(ExecutionContext executor) {
        Tasks$class.$init$(this);
        this.maxdepth = (int)(package$.MODULE$.log(this.parallelismLevel()) / package$.MODULE$.log(2.0) + 1.0);
        this.environment = executor;
    }
}

