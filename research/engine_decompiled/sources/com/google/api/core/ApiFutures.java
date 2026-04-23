/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.core;

import com.google.api.core.AbstractApiFuture;
import com.google.api.core.ApiAsyncFunction;
import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutureToListenableFuture;
import com.google.api.core.ListenableFutureToApiFuture;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public final class ApiFutures {
    private ApiFutures() {
    }

    @Deprecated
    public static <V> void addCallback(ApiFuture<V> future, ApiFutureCallback<? super V> callback) {
        ApiFutures.addCallback(future, callback, MoreExecutors.directExecutor());
    }

    public static <V> void addCallback(ApiFuture<V> future, final ApiFutureCallback<? super V> callback, Executor executor) {
        Futures.addCallback(ApiFutures.listenableFutureForApiFuture(future), new FutureCallback<V>(){

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }

            @Override
            public void onSuccess(V v) {
                callback.onSuccess(v);
            }
        }, executor);
    }

    @Deprecated
    public static <V, X extends Throwable> ApiFuture<V> catching(ApiFuture<? extends V> input2, Class<X> exceptionType, ApiFunction<? super X, ? extends V> callback) {
        return ApiFutures.catching(input2, exceptionType, callback, MoreExecutors.directExecutor());
    }

    public static <V, X extends Throwable> ApiFuture<V> catching(ApiFuture<? extends V> input2, Class<X> exceptionType, ApiFunction<? super X, ? extends V> callback, Executor executor) {
        ListenableFuture<? extends V> catchingFuture = Futures.catching(ApiFutures.listenableFutureForApiFuture(input2), exceptionType, new GaxFunctionToGuavaFunction<X, V>(callback), MoreExecutors.directExecutor());
        return new ListenableFutureToApiFuture<V>(catchingFuture);
    }

    public static <V> ApiFuture<V> immediateFuture(V value) {
        return new ListenableFutureToApiFuture<V>(Futures.immediateFuture(value));
    }

    public static <V> ApiFuture<V> immediateFailedFuture(Throwable throwable) {
        return new ListenableFutureToApiFuture(Futures.immediateFailedFuture(throwable));
    }

    public static <V> ApiFuture<V> immediateCancelledFuture() {
        return new ListenableFutureToApiFuture(Futures.immediateCancelledFuture());
    }

    @Deprecated
    public static <V, X> ApiFuture<X> transform(ApiFuture<? extends V> input2, ApiFunction<? super V, ? extends X> function) {
        return ApiFutures.transform(input2, function, MoreExecutors.directExecutor());
    }

    public static <V, X> ApiFuture<X> transform(ApiFuture<? extends V> input2, ApiFunction<? super V, ? extends X> function, Executor executor) {
        return new ListenableFutureToApiFuture<X>(Futures.transform(ApiFutures.listenableFutureForApiFuture(input2), new GaxFunctionToGuavaFunction<V, X>(function), executor));
    }

    public static <V> ApiFuture<List<V>> allAsList(Iterable<? extends ApiFuture<? extends V>> futures) {
        return new ListenableFutureToApiFuture(Futures.allAsList(Iterables.transform(futures, new Function<ApiFuture<? extends V>, ListenableFuture<? extends V>>(){

            @Override
            public ListenableFuture<? extends V> apply(ApiFuture<? extends V> apiFuture) {
                return ApiFutures.listenableFutureForApiFuture(apiFuture);
            }
        })));
    }

    @Deprecated
    public static <I, O> ApiFuture<O> transformAsync(ApiFuture<I> input2, ApiAsyncFunction<I, O> function) {
        return ApiFutures.transformAsync(input2, function, MoreExecutors.directExecutor());
    }

    public static <I, O> ApiFuture<O> transformAsync(ApiFuture<I> input2, final ApiAsyncFunction<I, O> function, Executor executor) {
        ListenableFuture<I> listenableInput = ApiFutures.listenableFutureForApiFuture(input2);
        ListenableFuture listenableOutput = Futures.transformAsync(listenableInput, new AsyncFunction<I, O>(){

            @Override
            public ListenableFuture<O> apply(I input2) throws Exception {
                return ApiFutures.listenableFutureForApiFuture(function.apply(input2));
            }
        }, executor);
        return new ListenableFutureToApiFuture(listenableOutput);
    }

    private static <V> ListenableFuture<V> listenableFutureForApiFuture(ApiFuture<V> apiFuture) {
        ListenableFuture listenableFuture = apiFuture instanceof AbstractApiFuture ? ((AbstractApiFuture)apiFuture).getInternalListenableFuture() : new ApiFutureToListenableFuture<V>(apiFuture);
        return listenableFuture;
    }

    private static class GaxFunctionToGuavaFunction<X, V>
    implements Function<X, V> {
        private ApiFunction<? super X, ? extends V> f;

        public GaxFunctionToGuavaFunction(ApiFunction<? super X, ? extends V> f) {
            this.f = f;
        }

        @Override
        @Nullable
        public V apply(@Nullable X input2) {
            return this.f.apply(input2);
        }
    }
}

