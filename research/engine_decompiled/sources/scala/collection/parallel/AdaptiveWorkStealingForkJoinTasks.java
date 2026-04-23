/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Serializable;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.parallel.AdaptiveWorkStealingTasks;
import scala.collection.parallel.AdaptiveWorkStealingTasks$WrappedTask$class;
import scala.collection.parallel.ForkJoinTasks;
import scala.collection.parallel.ForkJoinTasks$WrappedTask$class;
import scala.collection.parallel.Task;
import scala.collection.parallel.Tasks;
import scala.collection.parallel.Tasks$WrappedTask$class;
import scala.concurrent.forkjoin.RecursiveAction;
import scala.reflect.ScalaSignature;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001u3q!\u0001\u0002\u0011\u0002\u0007\u0005\u0011BA\u0011BI\u0006\u0004H/\u001b<f/>\u00148n\u0015;fC2Lgn\u001a$pe.Tu.\u001b8UCN\\7O\u0003\u0002\u0004\t\u0005A\u0001/\u0019:bY2,GN\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001a\u0005\u0003\u0001\u00159\u0011\u0002CA\u0006\r\u001b\u00051\u0011BA\u0007\u0007\u0005\u0019\te.\u001f*fMB\u0011q\u0002E\u0007\u0002\u0005%\u0011\u0011C\u0001\u0002\u000e\r>\u00148NS8j]R\u000b7o[:\u0011\u0005=\u0019\u0012B\u0001\u000b\u0003\u0005e\tE-\u00199uSZ,wk\u001c:l'R,\u0017\r\\5oOR\u000b7o[:\t\u000bY\u0001A\u0011A\f\u0002\r\u0011Jg.\u001b;%)\u0005A\u0002CA\u0006\u001a\u0013\tQbA\u0001\u0003V]&$h\u0001\u0002\u000f\u0001\u0001u\u00111b\u0016:baB,G\rV1tWV\u0019a\u0004\f\u001c\u0014\tmyr\u0005\u000f\t\u0003A\u0015j\u0011!\t\u0006\u0003E\r\n\u0001BZ8sW*|\u0017N\u001c\u0006\u0003I\u0019\t!bY8oGV\u0014(/\u001a8u\u0013\t1\u0013EA\bSK\u000e,(o]5wK\u0006\u001bG/[8o!\u0011A\u0013FK\u001b\u000e\u0003\u0001I!\u0001\b\t\u0011\u0005-bC\u0002\u0001\u0003\u0006[m\u0011\rA\f\u0002\u0002%F\u0011qF\r\t\u0003\u0017AJ!!\r\u0004\u0003\u000f9{G\u000f[5oOB\u00111bM\u0005\u0003i\u0019\u00111!\u00118z!\tYc\u0007B\u000387\t\u0007aF\u0001\u0002UaB!\u0001&\u000f\u00166\u0013\ta2\u0003\u0003\u0005<7\t\u0015\r\u0011\"\u0001=\u0003\u0011\u0011w\u000eZ=\u0016\u0003u\u0002Ba\u0004 +k%\u0011qH\u0001\u0002\u0005)\u0006\u001c8\u000e\u0003\u0005B7\t\u0005\t\u0015!\u0003>\u0003\u0015\u0011w\u000eZ=!\u0011\u0015\u00195\u0004\"\u0001E\u0003\u0019a\u0014N\\5u}Q\u0011QI\u0012\t\u0005QmQS\u0007C\u0003<\u0005\u0002\u0007Q\bC\u0003I7\u0011\u0005\u0011*A\u0003ta2LG/F\u0001K!\rYe\n\u000f\b\u0003\u00171K!!\u0014\u0004\u0002\u000fA\f7m[1hK&\u0011q\n\u0015\u0002\u0004'\u0016\f(BA'\u0007\u0011\u0015\u0011\u0006\u0001\"\u0001T\u00039qWm^,sCB\u0004X\r\u001a+bg.,2\u0001V,Z)\t)&\f\u0005\u0003)7YC\u0006CA\u0016X\t\u0015i\u0013K1\u0001/!\tY\u0013\fB\u00038#\n\u0007a\u0006C\u0003\\#\u0002\u0007A,A\u0001c!\u0011yaH\u0016-")
public interface AdaptiveWorkStealingForkJoinTasks
extends ForkJoinTasks,
AdaptiveWorkStealingTasks {
    public <R, Tp> WrappedTask<R, Tp> newWrappedTask(Task<R, Tp> var1);

    public class WrappedTask<R, Tp>
    extends RecursiveAction
    implements ForkJoinTasks.WrappedTask<R, Tp>,
    AdaptiveWorkStealingTasks.WrappedTask<R, Tp> {
        private final Task<R, Tp> body;
        public final /* synthetic */ AdaptiveWorkStealingForkJoinTasks $outer;
        private volatile AdaptiveWorkStealingTasks.WrappedTask<Object, Object> next;
        private volatile boolean shouldWaitFor;

        @Override
        public AdaptiveWorkStealingTasks.WrappedTask<R, Tp> next() {
            return this.next;
        }

        @Override
        @TraitSetter
        public void next_$eq(AdaptiveWorkStealingTasks.WrappedTask<R, Tp> x$1) {
            this.next = x$1;
        }

        @Override
        public boolean shouldWaitFor() {
            return this.shouldWaitFor;
        }

        @Override
        @TraitSetter
        public void shouldWaitFor_$eq(boolean x$1) {
            this.shouldWaitFor = x$1;
        }

        @Override
        public void compute() {
            AdaptiveWorkStealingTasks$WrappedTask$class.compute(this);
        }

        @Override
        public void internal() {
            AdaptiveWorkStealingTasks$WrappedTask$class.internal(this);
        }

        @Override
        public AdaptiveWorkStealingTasks.WrappedTask<R, Tp> spawnSubtasks() {
            return AdaptiveWorkStealingTasks$WrappedTask$class.spawnSubtasks(this);
        }

        @Override
        public void printChain() {
            AdaptiveWorkStealingTasks$WrappedTask$class.printChain(this);
        }

        @Override
        public void start() {
            ForkJoinTasks$WrappedTask$class.start(this);
        }

        @Override
        public void sync() {
            ForkJoinTasks$WrappedTask$class.sync(this);
        }

        @Override
        public boolean tryCancel() {
            return ForkJoinTasks$WrappedTask$class.tryCancel(this);
        }

        @Override
        public void release() {
            Tasks$WrappedTask$class.release(this);
        }

        @Override
        public Task<R, Tp> body() {
            return this.body;
        }

        @Override
        public Seq<AdaptiveWorkStealingTasks.WrappedTask<R, Tp>> split() {
            return this.body().split().map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ WrappedTask $outer;

                public final WrappedTask<R, Tp> apply(Task<R, Tp> b) {
                    return this.$outer.scala$collection$parallel$AdaptiveWorkStealingForkJoinTasks$WrappedTask$$$outer().newWrappedTask(b);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        public /* synthetic */ AdaptiveWorkStealingForkJoinTasks scala$collection$parallel$AdaptiveWorkStealingForkJoinTasks$WrappedTask$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ AdaptiveWorkStealingTasks scala$collection$parallel$AdaptiveWorkStealingTasks$WrappedTask$$$outer() {
            return this.scala$collection$parallel$AdaptiveWorkStealingForkJoinTasks$WrappedTask$$$outer();
        }

        @Override
        public /* synthetic */ ForkJoinTasks scala$collection$parallel$ForkJoinTasks$WrappedTask$$$outer() {
            return this.scala$collection$parallel$AdaptiveWorkStealingForkJoinTasks$WrappedTask$$$outer();
        }

        @Override
        public /* synthetic */ Tasks scala$collection$parallel$Tasks$WrappedTask$$$outer() {
            return this.scala$collection$parallel$AdaptiveWorkStealingForkJoinTasks$WrappedTask$$$outer();
        }

        public WrappedTask(AdaptiveWorkStealingForkJoinTasks $outer, Task<R, Tp> body2) {
            this.body = body2;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Tasks$WrappedTask$class.$init$(this);
            ForkJoinTasks$WrappedTask$class.$init$(this);
            AdaptiveWorkStealingTasks$WrappedTask$class.$init$(this);
        }
    }
}

