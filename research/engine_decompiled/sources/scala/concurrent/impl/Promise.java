/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.impl;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import scala.Function1;
import scala.Function1$class;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.StringBuilder;
import scala.concurrent.CanAwait;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.Future$InternalCallbackExecutor$;
import scala.concurrent.Future$class;
import scala.concurrent.Promise$class;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.Duration$;
import scala.concurrent.duration.FiniteDuration;
import scala.concurrent.impl.AbstractPromise;
import scala.concurrent.impl.CallbackRunnable;
import scala.concurrent.impl.Promise$;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.Try;

@ScalaSignature(bytes="\u0006\u0001\tEe\u0001C\u0001\u0003!\u0003\r\t\u0001\u0002\u0005\u0003\u000fA\u0013x.\\5tK*\u00111\u0001B\u0001\u0005S6\u0004HN\u0003\u0002\u0006\r\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0003\u001d\tQa]2bY\u0006,\"!C\n\u0014\t\u0001Qa\"\b\t\u0003\u00171i\u0011AB\u0005\u0003\u001b\u0019\u0011a!\u00118z%\u00164\u0007cA\b\u0011#5\tA!\u0003\u0002\u0002\tA\u0011!c\u0005\u0007\u0001\t\u0015!\u0002A1\u0001\u0017\u0005\u0005!6\u0001A\t\u0003/i\u0001\"a\u0003\r\n\u0005e1!a\u0002(pi\"Lgn\u001a\t\u0003\u0017mI!\u0001\b\u0004\u0003\u0007\u0005s\u0017\u0010E\u0002\u0010=EI!a\b\u0003\u0003\r\u0019+H/\u001e:f\u0011\u0015\t\u0003\u0001\"\u0001#\u0003\u0019!\u0013N\\5uIQ\t1\u0005\u0005\u0002\fI%\u0011QE\u0002\u0002\u0005+:LG\u000fC\u0003(\u0001\u0011\u0005\u0001&\u0001\u0004gkR,(/Z\u000b\u0002S5\t\u0001\u0001C\u0003,\u0001\u0011\u0005C&\u0001\u0005u_N#(/\u001b8h)\u0005i\u0003C\u0001\u00182\u001d\tYq&\u0003\u00021\r\u00051\u0001K]3eK\u001aL!AM\u001a\u0003\rM#(/\u001b8h\u0015\t\u0001da\u0002\u00046\u0005!\u0005AAN\u0001\b!J|W.[:f!\t9\u0004(D\u0001\u0003\r\u0019\t!\u0001#\u0001\u0005sM\u0011\u0001H\u0003\u0005\u0006wa\"\t\u0001P\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003YBQA\u0010\u001d\u0005\n}\n!B]3t_24X\r\u0016:z+\t\u0001\u0005\n\u0006\u0002B\u0013B\u0019!)R$\u000e\u0003\rS!\u0001\u0012\u0004\u0002\tU$\u0018\u000e\\\u0005\u0003\r\u000e\u00131\u0001\u0016:z!\t\u0011\u0002\nB\u0003\u0015{\t\u0007a\u0003C\u0003K{\u0001\u0007\u0011)\u0001\u0004t_V\u00148-\u001a\u0005\u0006\u0019b\"I!T\u0001\te\u0016\u001cx\u000e\u001c<feV\u0011a*\u0015\u000b\u0003\u001fJ\u00032AQ#Q!\t\u0011\u0012\u000bB\u0003\u0015\u0017\n\u0007a\u0003C\u0003T\u0017\u0002\u0007A+A\u0005uQJ|w/\u00192mKB\u0011Q+\u0018\b\u0003-ns!a\u0016.\u000e\u0003aS!!W\u000b\u0002\rq\u0012xn\u001c;?\u0013\u00059\u0011B\u0001/\u0007\u0003\u001d\u0001\u0018mY6bO\u0016L!AX0\u0003\u0013QC'o\\<bE2,'B\u0001/\u0007\r\u0011\t\u0007H\u00022\u0003\u001f\r{W\u000e\u001d7fi&|g\u000eT1uG\",\"aY:\u0014\u0007\u0001$g\u000e\u0005\u0002fY6\taM\u0003\u0002hQ\u0006)An\\2lg*\u0011Q!\u001b\u0006\u0003\t*T\u0011a[\u0001\u0005U\u00064\u0018-\u0003\u0002nM\nQ\u0012IY:ue\u0006\u001cG/U;fk\u0016$7+\u001f8dQJ|g.\u001b>feB!1b\\9$\u0013\t\u0001hAA\u0005Gk:\u001cG/[8ocA\u0019!)\u0012:\u0011\u0005I\u0019H!\u0002\u000ba\u0005\u00041\u0002\"B\u001ea\t\u0003)H#\u0001<\u0011\u0007]\u0004'/D\u00019\u0011\u0015I\b\r\"\u0015{\u0003A!(/_!dcVL'/Z*iCJ,G\r\u0006\u0002|}B\u00111\u0002`\u0005\u0003{\u001a\u00111!\u00138u\u0011\u0015y\b\u00101\u0001|\u0003\u001dIwM\\8sK\u0012Dq!a\u0001a\t#\n)!\u0001\tuef\u0014V\r\\3bg\u0016\u001c\u0006.\u0019:fIR!\u0011qAA\u0007!\rY\u0011\u0011B\u0005\u0004\u0003\u00171!a\u0002\"p_2,\u0017M\u001c\u0005\b\u0003\u001f\t\t\u00011\u0001|\u0003\u0019IwM\\8sK\"9\u00111\u00031\u0005B\u0005U\u0011!B1qa2LHcA\u0012\u0002\u0018!1q0!\u0005A\u0002E4\u0001\"a\u00079\u0001\u0005u\u0011\u0011\u0007\u0002\u000f\t\u00164\u0017-\u001e7u!J|W.[:f+\u0011\ty\"a\u000b\u0014\r\u0005e\u0011\u0011EA\u0014!\r9\u00141E\u0005\u0004\u0003K\u0011!aD!cgR\u0014\u0018m\u0019;Qe>l\u0017n]3\u0011\t]\u0002\u0011\u0011\u0006\t\u0004%\u0005-BA\u0002\u000b\u0002\u001a\t\u0007a\u0003C\u0004<\u00033!\t!a\f\u0015\u0005\u0005E\u0002#B<\u0002\u001a\u0005%\u0002\u0002CA\u001b\u00033!I!a\f\u0002\u001d\r|W\u000e\u001d:fgN,GMU8pi\"\"\u00111GA\u001d!\u0011\tY$!\u0011\u000e\u0005\u0005u\"bAA \r\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\r\u0013Q\b\u0002\bi\u0006LGN]3d\u0011!\t9%!\u0007\u0005\n\u0005%\u0013\u0001\u0002:p_R,\"!!\r)\t\u0005\u0015\u0013\u0011\b\u0005\t\u0003\u001f\nI\u0002\"\u0006\u0002R\u0005AAO]=Bo\u0006LG\u000f\u0006\u0003\u0002\b\u0005M\u0003\u0002CA+\u0003\u001b\u0002\r!a\u0016\u0002\r\u0005$Xj\\:u!\u0011\tI&a\u0018\u000e\u0005\u0005m#bAA/\t\u0005AA-\u001e:bi&|g.\u0003\u0003\u0002b\u0005m#\u0001\u0003#ve\u0006$\u0018n\u001c8\t\u0011\u0005\u0015\u0014\u0011\u0004C\u0001\u0003O\nQA]3bIf$B!!\u001b\u0002xQ!\u00111NA7\u001b\t\tI\u0002\u0003\u0005\u0002p\u0005\r\u00049AA9\u0003\u0019\u0001XM]7jiB\u0019q\"a\u001d\n\u0007\u0005UDA\u0001\u0005DC:\fu/Y5u\u0011!\t)&a\u0019A\u0002\u0005]\u0003FBA2\u0003w\n9\tE\u0003\f\u0003{\n\t)C\u0002\u0002\u0000\u0019\u0011a\u0001\u001e5s_^\u001c\bcA+\u0002\u0004&\u0019\u0011QQ0\u0003)%sG/\u001a:skB$X\rZ#yG\u0016\u0004H/[8oG\t\t\t\t\u000b\u0004\u0002d\u0005-\u0015Q\u0014\t\u0006\u0017\u0005u\u0014Q\u0012\t\u0005\u0003\u001f\u000b9J\u0004\u0003\u0002\u0012\u0006Ueb\u0001,\u0002\u0014&\u0011QAB\u0005\u00039\u0012IA!!'\u0002\u001c\n\u0001B+[7f_V$X\t_2faRLwN\u001c\u0006\u00039\u0012\u0019#!!$\t\u0011\u0005\u0005\u0016\u0011\u0004C\u0001\u0003G\u000baA]3tk2$H\u0003BAS\u0003S#B!!\u000b\u0002(\"A\u0011qNAP\u0001\b\t\t\b\u0003\u0005\u0002V\u0005}\u0005\u0019AA,Q\u0019\ty*!,\u00026B)1\"! \u00020B\u0019Q+!-\n\u0007\u0005MvLA\u0005Fq\u000e,\u0007\u000f^5p]\u000e\u0012\u0011q\u0016\u0005\t\u0003s\u000bI\u0002\"\u0001\u0002<\u0006)a/\u00197vKV\u0011\u0011Q\u0018\t\u0006\u0017\u0005}\u00161Y\u0005\u0004\u0003\u00034!AB(qi&|g\u000e\u0005\u0003C\u000b\u0006%\u0002\u0002CAd\u00033!I!a/\u0002\rY\fG.^31Q\u0011\t)-!\u000f\t\u0011\u00055\u0017\u0011\u0004C!\u0003\u001f\f1\"[:D_6\u0004H.\u001a;fIV\u0011\u0011q\u0001\u0005\t\u0003'\fI\u0002\"\u0003\u0002P\u0006a\u0011n]\"p[BdW\r^3ea!\"\u0011\u0011[A\u001d\u0011!\tI.!\u0007\u0005\u0002\u0005m\u0017a\u0003;ss\u000e{W\u000e\u001d7fi\u0016$B!a\u0002\u0002^\"A\u0011\u0011XAl\u0001\u0004\t\u0019\r\u0003\u0005\u0002b\u0006eA\u0011BAr\u0003i!(/_\"p[BdW\r^3B]\u0012<U\r\u001e'jgR,g.\u001a:t)\u0011\t)/!=\u0011\u000bU\u000b9/a;\n\u0007\u0005%xL\u0001\u0003MSN$\b#B\u001c\u0002n\u0006%\u0012bAAx\u0005\t\u00012)\u00197mE\u0006\u001c7NU;o]\u0006\u0014G.\u001a\u0005\t\u0003g\fy\u000e1\u0001\u0002D\u0006\ta\u000f\u000b\u0003\u0002`\u0006e\u0002\u0002CA}\u00033!\t!a?\u0002\u0015=t7i\\7qY\u0016$X-\u0006\u0003\u0002~\nMA\u0003BA\u0000\u0005\u0017!2a\tB\u0001\u0011!\u0011\u0019!a>A\u0004\t\u0015\u0011\u0001C3yK\u000e,Ho\u001c:\u0011\u0007=\u00119!C\u0002\u0003\n\u0011\u0011\u0001#\u0012=fGV$\u0018n\u001c8D_:$X\r\u001f;\t\u0011\t5\u0011q\u001fa\u0001\u0005\u001f\tAAZ;oGB11b\\Ab\u0005#\u00012A\u0005B\n\t\u001d\u0011)\"a>C\u0002Y\u0011\u0011!\u0016\u0005\t\u00053\tI\u0002\"\u0003\u0003\u001c\u0005)B-[:qCR\u001c\u0007n\u0014:BI\u0012\u001c\u0015\r\u001c7cC\u000e\\GcA\u0012\u0003\u001e!A!q\u0004B\f\u0001\u0004\tY/\u0001\u0005sk:t\u0017M\u00197fQ\u0011\u00119\"!\u000f\t\u0013\t\u0015\u0012\u0011\u0004C\u000b\t\t\u001d\u0012A\u00037j].\u0014vn\u001c;PMR\u00191E!\u000b\t\u0011\t-\"1\u0005a\u0001\u0003c\ta\u0001^1sO\u0016$\b\u0002\u0003B\u0018\u00033!IA!\r\u0002\t1Lgn\u001b\u000b\u0004G\tM\u0002\u0002\u0003B\u0016\u0005[\u0001\r!!\r)\t\t5\u0012\u0011\b\u0004\u0007\u0005sA$Aa\u000f\u0003\u0017-+\u0007\u000f\u001e)s_6L7/Z\u000b\u0005\u0005{\u0011\u0019eE\u0003\u00038)\u0011y\u0004\u0005\u00038\u0001\t\u0005\u0003c\u0001\n\u0003D\u00111ACa\u000eC\u0002YA1Ba\u0012\u00038\t\u0005\t\u0015!\u0003\u0003J\u0005i1/\u001e9qY&,GMV1mk\u0016\u0004BAQ#\u0003B!91Ha\u000e\u0005\u0002\t5C\u0003\u0002B(\u0005#\u0002Ra\u001eB\u001c\u0005\u0003B\u0001Ba\u0012\u0003L\u0001\u0007!\u0011\n\u0005\u000b\u0003s\u00139D1A\u0005\u0002\tUSC\u0001B,!\u0015Y!\u0011\fB%\u0013\r\u0011YF\u0002\u0002\u0005'>lW\rC\u0005\u0003`\t]\u0002\u0015!\u0003\u0003X\u00051a/\u00197vK\u0002B\u0001\"!4\u00038\u0011\u0005\u0013q\u001a\u0005\t\u00033\u00149\u0004\"\u0001\u0003fQ!\u0011q\u0001B4\u0011!\tILa\u0019A\u0002\t%\u0003\u0002CA}\u0005o!\tAa\u001b\u0016\t\t5$\u0011\u0010\u000b\u0005\u0005_\u0012\u0019\bF\u0002$\u0005cB\u0001Ba\u0001\u0003j\u0001\u000f!Q\u0001\u0005\t\u0005\u001b\u0011I\u00071\u0001\u0003vA11b\u001cB%\u0005o\u00022A\u0005B=\t\u001d\u0011)B!\u001bC\u0002YA\u0001\"!\u001a\u00038\u0011\u0005!Q\u0010\u000b\u0005\u0005\u007f\u0012)\t\u0006\u0003\u0003\u0002\n\rUB\u0001B\u001c\u0011!\tyGa\u001fA\u0004\u0005E\u0004\u0002CA+\u0005w\u0002\r!a\u0016\t\u0011\u0005\u0005&q\u0007C\u0001\u0005\u0013#BAa#\u0003\u0010R!!\u0011\tBG\u0011!\tyGa\"A\u0004\u0005E\u0004\u0002CA+\u0005\u000f\u0003\r!a\u0016")
public interface Promise<T>
extends scala.concurrent.Promise<T>,
Future<T> {
    @Override
    public Promise<T> future();

    public String toString();

    public static final class KeptPromise<T>
    implements Promise<T> {
        private final Some<Try<T>> value;

        @Override
        public Promise<T> future() {
            return scala.concurrent.impl.Promise$class.future(this);
        }

        @Override
        public String toString() {
            return scala.concurrent.impl.Promise$class.toString(this);
        }

        @Override
        public <U> void onSuccess(PartialFunction<T, U> pf, ExecutionContext executor) {
            Future$class.onSuccess(this, pf, executor);
        }

        @Override
        public <U> void onFailure(PartialFunction<Throwable, U> pf, ExecutionContext executor) {
            Future$class.onFailure(this, pf, executor);
        }

        @Override
        public Future<Throwable> failed() {
            return Future$class.failed(this);
        }

        @Override
        public <U> void foreach(Function1<T, U> f, ExecutionContext executor) {
            Future$class.foreach(this, f, executor);
        }

        @Override
        public <S> Future<S> transform(Function1<T, S> s2, Function1<Throwable, Throwable> f, ExecutionContext executor) {
            return Future$class.transform(this, s2, f, executor);
        }

        @Override
        public <S> Future<S> map(Function1<T, S> f, ExecutionContext executor) {
            return Future$class.map(this, f, executor);
        }

        @Override
        public <S> Future<S> flatMap(Function1<T, Future<S>> f, ExecutionContext executor) {
            return Future$class.flatMap(this, f, executor);
        }

        @Override
        public Future<T> filter(Function1<T, Object> p, ExecutionContext executor) {
            return Future$class.filter(this, p, executor);
        }

        @Override
        public final Future<T> withFilter(Function1<T, Object> p, ExecutionContext executor) {
            return Future$class.withFilter(this, p, executor);
        }

        @Override
        public <S> Future<S> collect(PartialFunction<T, S> pf, ExecutionContext executor) {
            return Future$class.collect(this, pf, executor);
        }

        @Override
        public <U> Future<U> recover(PartialFunction<Throwable, U> pf, ExecutionContext executor) {
            return Future$class.recover(this, pf, executor);
        }

        @Override
        public <U> Future<U> recoverWith(PartialFunction<Throwable, Future<U>> pf, ExecutionContext executor) {
            return Future$class.recoverWith(this, pf, executor);
        }

        @Override
        public <U> Future<Tuple2<T, U>> zip(Future<U> that) {
            return Future$class.zip(this, that);
        }

        @Override
        public <U> Future<U> fallbackTo(Future<U> that) {
            return Future$class.fallbackTo(this, that);
        }

        @Override
        public <S> Future<S> mapTo(ClassTag<S> tag) {
            return Future$class.mapTo(this, tag);
        }

        @Override
        public <U> Future<T> andThen(PartialFunction<Try<T>, U> pf, ExecutionContext executor) {
            return Future$class.andThen(this, pf, executor);
        }

        @Override
        public scala.concurrent.Promise<T> complete(Try<T> result2) {
            return Promise$class.complete(this, result2);
        }

        @Override
        public final scala.concurrent.Promise<T> completeWith(Future<T> other) {
            return Promise$class.completeWith(this, other);
        }

        @Override
        public final scala.concurrent.Promise<T> tryCompleteWith(Future<T> other) {
            return Promise$class.tryCompleteWith(this, other);
        }

        @Override
        public scala.concurrent.Promise<T> success(T value) {
            return Promise$class.success(this, value);
        }

        @Override
        public boolean trySuccess(T value) {
            return Promise$class.trySuccess(this, value);
        }

        @Override
        public scala.concurrent.Promise<T> failure(Throwable cause) {
            return Promise$class.failure(this, cause);
        }

        @Override
        public boolean tryFailure(Throwable cause) {
            return Promise$class.tryFailure(this, cause);
        }

        @Override
        public Some<Try<T>> value() {
            return this.value;
        }

        @Override
        public boolean isCompleted() {
            return true;
        }

        @Override
        public boolean tryComplete(Try<T> value) {
            return false;
        }

        @Override
        public <U> void onComplete(Function1<Try<T>, U> func, ExecutionContext executor) {
            Try completedAs = (Try)((Some)this.value()).get();
            ExecutionContext preparedEC = executor.prepare();
            new CallbackRunnable(preparedEC, func).executeWithValue(completedAs);
        }

        @Override
        public KeptPromise<T> ready(Duration atMost, CanAwait permit) {
            return this;
        }

        @Override
        public T result(Duration atMost, CanAwait permit) {
            return ((Try)((Some)this.value()).get()).get();
        }

        public KeptPromise(Try<T> suppliedValue) {
            Promise$class.$init$(this);
            Future$class.$init$(this);
            scala.concurrent.impl.Promise$class.$init$(this);
            this.value = new Some<Try<T>>(Promise$.MODULE$.scala$concurrent$impl$Promise$$resolveTry(suppliedValue));
        }
    }

    public static class DefaultPromise<T>
    extends AbstractPromise
    implements Promise<T> {
        @Override
        public Promise<T> future() {
            return scala.concurrent.impl.Promise$class.future(this);
        }

        @Override
        public String toString() {
            return scala.concurrent.impl.Promise$class.toString(this);
        }

        @Override
        public <U> void onSuccess(PartialFunction<T, U> pf, ExecutionContext executor) {
            Future$class.onSuccess(this, pf, executor);
        }

        @Override
        public <U> void onFailure(PartialFunction<Throwable, U> pf, ExecutionContext executor) {
            Future$class.onFailure(this, pf, executor);
        }

        @Override
        public Future<Throwable> failed() {
            return Future$class.failed(this);
        }

        @Override
        public <U> void foreach(Function1<T, U> f, ExecutionContext executor) {
            Future$class.foreach(this, f, executor);
        }

        @Override
        public <S> Future<S> transform(Function1<T, S> s2, Function1<Throwable, Throwable> f, ExecutionContext executor) {
            return Future$class.transform(this, s2, f, executor);
        }

        @Override
        public <S> Future<S> map(Function1<T, S> f, ExecutionContext executor) {
            return Future$class.map(this, f, executor);
        }

        @Override
        public <S> Future<S> flatMap(Function1<T, Future<S>> f, ExecutionContext executor) {
            return Future$class.flatMap(this, f, executor);
        }

        @Override
        public Future<T> filter(Function1<T, Object> p, ExecutionContext executor) {
            return Future$class.filter(this, p, executor);
        }

        @Override
        public final Future<T> withFilter(Function1<T, Object> p, ExecutionContext executor) {
            return Future$class.withFilter(this, p, executor);
        }

        @Override
        public <S> Future<S> collect(PartialFunction<T, S> pf, ExecutionContext executor) {
            return Future$class.collect(this, pf, executor);
        }

        @Override
        public <U> Future<U> recover(PartialFunction<Throwable, U> pf, ExecutionContext executor) {
            return Future$class.recover(this, pf, executor);
        }

        @Override
        public <U> Future<U> recoverWith(PartialFunction<Throwable, Future<U>> pf, ExecutionContext executor) {
            return Future$class.recoverWith(this, pf, executor);
        }

        @Override
        public <U> Future<Tuple2<T, U>> zip(Future<U> that) {
            return Future$class.zip(this, that);
        }

        @Override
        public <U> Future<U> fallbackTo(Future<U> that) {
            return Future$class.fallbackTo(this, that);
        }

        @Override
        public <S> Future<S> mapTo(ClassTag<S> tag) {
            return Future$class.mapTo(this, tag);
        }

        @Override
        public <U> Future<T> andThen(PartialFunction<Try<T>, U> pf, ExecutionContext executor) {
            return Future$class.andThen(this, pf, executor);
        }

        @Override
        public scala.concurrent.Promise<T> complete(Try<T> result2) {
            return Promise$class.complete(this, result2);
        }

        @Override
        public final scala.concurrent.Promise<T> completeWith(Future<T> other) {
            return Promise$class.completeWith(this, other);
        }

        @Override
        public final scala.concurrent.Promise<T> tryCompleteWith(Future<T> other) {
            return Promise$class.tryCompleteWith(this, other);
        }

        @Override
        public scala.concurrent.Promise<T> success(T value) {
            return Promise$class.success(this, value);
        }

        @Override
        public boolean trySuccess(T value) {
            return Promise$class.trySuccess(this, value);
        }

        @Override
        public scala.concurrent.Promise<T> failure(Throwable cause) {
            return Promise$class.failure(this, cause);
        }

        @Override
        public boolean tryFailure(Throwable cause) {
            return Promise$class.tryFailure(this, cause);
        }

        private DefaultPromise<T> compressedRoot() {
            DefaultPromise<T> defaultPromise;
            block3: {
                Object object;
                while ((object = this.getState()) instanceof DefaultPromise) {
                    DefaultPromise<T> defaultPromise2;
                    DefaultPromise defaultPromise3 = (DefaultPromise)object;
                    DefaultPromise<T> target = defaultPromise3.root();
                    if (defaultPromise3 == target) {
                        defaultPromise2 = target;
                    } else {
                        if (!this.updateState(defaultPromise3, target)) continue;
                        defaultPromise2 = target;
                    }
                    defaultPromise = defaultPromise2;
                    break block3;
                }
                defaultPromise = this;
            }
            return defaultPromise;
        }

        private DefaultPromise<T> root() {
            Object object;
            while ((object = this_.getState()) instanceof DefaultPromise) {
                DefaultPromise defaultPromise;
                DefaultPromise this_ = defaultPromise = (DefaultPromise)object;
            }
            return this_;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public final boolean tryAwait(Duration atMost) {
            if (this.isCompleted()) {
                return true;
            }
            if (atMost == Duration$.MODULE$.Undefined()) {
                throw new IllegalArgumentException("cannot wait for Undefined period");
            }
            Duration.Infinite infinite = Duration$.MODULE$.Inf();
            if (!(infinite != null ? !infinite.equals(atMost) : atMost != null)) {
                CompletionLatch l = new CompletionLatch();
                this.onComplete(l, Future$InternalCallbackExecutor$.MODULE$);
                l.acquireSharedInterruptibly(1);
            } else {
                Duration.Infinite infinite2 = Duration$.MODULE$.MinusInf();
                if (!(infinite2 != null ? !infinite2.equals(atMost) : atMost != null)) {
                } else {
                    if (!(atMost instanceof FiniteDuration)) throw new MatchError(atMost);
                    FiniteDuration finiteDuration = (FiniteDuration)atMost;
                    if (finiteDuration.$greater(Duration$.MODULE$.Zero())) {
                        CompletionLatch l = new CompletionLatch();
                        this.onComplete(l, Future$InternalCallbackExecutor$.MODULE$);
                        l.tryAcquireSharedNanos(1, finiteDuration.toNanos());
                    }
                }
            }
            boolean bl = this.isCompleted();
            return bl;
        }

        @Override
        public DefaultPromise<T> ready(Duration atMost, CanAwait permit) throws TimeoutException, InterruptedException {
            if (this.tryAwait(atMost)) {
                return this;
            }
            throw new TimeoutException(new StringBuilder().append((Object)"Futures timed out after [").append(atMost).append((Object)"]").toString());
        }

        @Override
        public T result(Duration atMost, CanAwait permit) throws Exception {
            return ((DefaultPromise)this.ready(atMost, permit)).value().get().get();
        }

        @Override
        public Option<Try<T>> value() {
            return this.value0();
        }

        private Option<Try<T>> value0() {
            Option option;
            block2: {
                while (true) {
                    Object object;
                    if ((object = this_.getState()) instanceof Try) {
                        Try try_ = (Try)object;
                        option = new Some<Try>(try_);
                        break block2;
                    }
                    if (!(object instanceof DefaultPromise)) break;
                    DefaultPromise<T> this_ = this_.compressedRoot();
                }
                option = None$.MODULE$;
            }
            return option;
        }

        @Override
        public boolean isCompleted() {
            return this.isCompleted0();
        }

        private boolean isCompleted0() {
            boolean bl;
            block2: {
                while (true) {
                    Object object;
                    if ((object = this_.getState()) instanceof Try) {
                        bl = true;
                        break block2;
                    }
                    if (!(object instanceof DefaultPromise)) break;
                    DefaultPromise<T> this_ = this_.compressedRoot();
                }
                bl = false;
            }
            return bl;
        }

        /*
         * WARNING - void declaration
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean tryComplete(Try<T> value) {
            Try<T> resolved = Promise$.MODULE$.scala$concurrent$impl$Promise$$resolveTry(value);
            List list2 = this.tryCompleteAndGetListeners(resolved);
            if (list2 == null) {
                return false;
            }
            if (list2.isEmpty()) {
                return true;
            }
            List these1 = list2;
            while (!these1.isEmpty()) {
                void var6_2;
                these1.head().executeWithValue((Try<T>)var6_2);
                these1 = (List)these1.tail();
            }
            return true;
        }

        private List<CallbackRunnable<T>> tryCompleteAndGetListeners(Try<T> v) {
            List list2;
            block2: {
                while (true) {
                    Object object;
                    if ((object = this_.getState()) instanceof List) {
                        List list3 = (List)object;
                        if (!this_.updateState(list3, v)) continue;
                        list2 = list3;
                        break block2;
                    }
                    if (!(object instanceof DefaultPromise)) break;
                    DefaultPromise<T> this_ = this_.compressedRoot();
                }
                list2 = null;
            }
            return list2;
        }

        @Override
        public <U> void onComplete(Function1<Try<T>, U> func, ExecutionContext executor) {
            ExecutionContext preparedEC = executor.prepare();
            CallbackRunnable runnable = new CallbackRunnable(preparedEC, func);
            this.scala$concurrent$impl$Promise$DefaultPromise$$dispatchOrAddCallback(runnable);
        }

        public void scala$concurrent$impl$Promise$DefaultPromise$$dispatchOrAddCallback(CallbackRunnable<T> runnable) {
            Object object;
            block4: {
                while (true) {
                    if ((object = this_.getState()) instanceof Try) {
                        Try try_ = (Try)object;
                        runnable.executeWithValue(try_);
                        break;
                    }
                    if (object instanceof DefaultPromise) {
                        DefaultPromise<T> this_ = this_.compressedRoot();
                        continue;
                    }
                    if (!(object instanceof List)) break block4;
                    List list2 = (List)object;
                    if (this_.updateState(list2, list2.$colon$colon(runnable))) break;
                }
                return;
            }
            throw new MatchError(object);
        }

        public final void linkRootOf(DefaultPromise<T> target) {
            this.link(super.compressedRoot());
        }

        /*
         * Enabled aggressive block sorting
         */
        private void link(DefaultPromise<T> target) {
            BoxedUnit boxedUnit;
            block8: {
                List list2;
                while (true) {
                    if (this_ == target) {
                        boxedUnit = BoxedUnit.UNIT;
                        return;
                    }
                    Object object = this_.getState();
                    if (object instanceof Try) {
                        Try try_ = (Try)object;
                        if (!target.tryComplete(try_)) throw new IllegalStateException("Cannot link completed promises together");
                        break block8;
                    }
                    if (object instanceof DefaultPromise) {
                        DefaultPromise<T> this_ = this_.compressedRoot();
                        continue;
                    }
                    if (!(object instanceof List)) throw new MatchError(object);
                    list2 = (List)object;
                    if (this_.updateState(list2, target)) break;
                }
                if (list2.isEmpty()) {
                } else {
                    List these1 = list2;
                    while (true) {
                        if (these1.isEmpty()) {
                            break;
                        }
                        CallbackRunnable callbackRunnable = (CallbackRunnable)these1.head();
                        target.scala$concurrent$impl$Promise$DefaultPromise$$dispatchOrAddCallback(callbackRunnable);
                        these1 = (List)these1.tail();
                    }
                }
            }
            boxedUnit = BoxedUnit.UNIT;
        }

        public DefaultPromise() {
            Promise$class.$init$(this);
            Future$class.$init$(this);
            scala.concurrent.impl.Promise$class.$init$(this);
            this.updateState(null, Nil$.MODULE$);
        }
    }

    public static final class CompletionLatch<T>
    extends AbstractQueuedSynchronizer
    implements Function1<Try<T>, BoxedUnit> {
        @Override
        public boolean apply$mcZD$sp(double v1) {
            return Function1$class.apply$mcZD$sp(this, v1);
        }

        @Override
        public double apply$mcDD$sp(double v1) {
            return Function1$class.apply$mcDD$sp(this, v1);
        }

        @Override
        public float apply$mcFD$sp(double v1) {
            return Function1$class.apply$mcFD$sp(this, v1);
        }

        @Override
        public int apply$mcID$sp(double v1) {
            return Function1$class.apply$mcID$sp(this, v1);
        }

        @Override
        public long apply$mcJD$sp(double v1) {
            return Function1$class.apply$mcJD$sp(this, v1);
        }

        @Override
        public void apply$mcVD$sp(double v1) {
            Function1$class.apply$mcVD$sp(this, v1);
        }

        @Override
        public boolean apply$mcZF$sp(float v1) {
            return Function1$class.apply$mcZF$sp(this, v1);
        }

        @Override
        public double apply$mcDF$sp(float v1) {
            return Function1$class.apply$mcDF$sp(this, v1);
        }

        @Override
        public float apply$mcFF$sp(float v1) {
            return Function1$class.apply$mcFF$sp(this, v1);
        }

        @Override
        public int apply$mcIF$sp(float v1) {
            return Function1$class.apply$mcIF$sp(this, v1);
        }

        @Override
        public long apply$mcJF$sp(float v1) {
            return Function1$class.apply$mcJF$sp(this, v1);
        }

        @Override
        public void apply$mcVF$sp(float v1) {
            Function1$class.apply$mcVF$sp(this, v1);
        }

        @Override
        public boolean apply$mcZI$sp(int v1) {
            return Function1$class.apply$mcZI$sp(this, v1);
        }

        @Override
        public double apply$mcDI$sp(int v1) {
            return Function1$class.apply$mcDI$sp(this, v1);
        }

        @Override
        public float apply$mcFI$sp(int v1) {
            return Function1$class.apply$mcFI$sp(this, v1);
        }

        @Override
        public int apply$mcII$sp(int v1) {
            return Function1$class.apply$mcII$sp(this, v1);
        }

        @Override
        public long apply$mcJI$sp(int v1) {
            return Function1$class.apply$mcJI$sp(this, v1);
        }

        @Override
        public void apply$mcVI$sp(int v1) {
            Function1$class.apply$mcVI$sp(this, v1);
        }

        @Override
        public boolean apply$mcZJ$sp(long v1) {
            return Function1$class.apply$mcZJ$sp(this, v1);
        }

        @Override
        public double apply$mcDJ$sp(long v1) {
            return Function1$class.apply$mcDJ$sp(this, v1);
        }

        @Override
        public float apply$mcFJ$sp(long v1) {
            return Function1$class.apply$mcFJ$sp(this, v1);
        }

        @Override
        public int apply$mcIJ$sp(long v1) {
            return Function1$class.apply$mcIJ$sp(this, v1);
        }

        @Override
        public long apply$mcJJ$sp(long v1) {
            return Function1$class.apply$mcJJ$sp(this, v1);
        }

        @Override
        public void apply$mcVJ$sp(long v1) {
            Function1$class.apply$mcVJ$sp(this, v1);
        }

        @Override
        public <A> Function1<A, BoxedUnit> compose(Function1<A, Try<T>> g) {
            return Function1$class.compose(this, g);
        }

        @Override
        public <A> Function1<Try<T>, A> andThen(Function1<BoxedUnit, A> g) {
            return Function1$class.andThen(this, g);
        }

        @Override
        public String toString() {
            return Function1$class.toString(this);
        }

        @Override
        public int tryAcquireShared(int ignored) {
            return this.getState() != 0 ? 1 : -1;
        }

        @Override
        public boolean tryReleaseShared(int ignore) {
            this.setState(1);
            return true;
        }

        @Override
        public void apply(Try<T> ignored) {
            this.releaseShared(1);
        }

        public CompletionLatch() {
            Function1$class.$init$(this);
        }
    }
}

