/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.BetaApi;
import com.google.api.gax.retrying.ResultRetryAlgorithm;
import com.google.api.gax.retrying.TimedAttemptSettings;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;

@BetaApi
public final class ExceptionHandler
implements ResultRetryAlgorithm<Object>,
Serializable {
    private static final long serialVersionUID = -2460707015779532919L;
    private static final ExceptionHandler DEFAULT_INSTANCE = ExceptionHandler.newBuilder().retryOn(Exception.class).abortOn(RuntimeException.class).build();
    private final ImmutableList<Interceptor> interceptors;
    private final ImmutableSet<Class<? extends Exception>> retriableExceptions;
    private final ImmutableSet<Class<? extends Exception>> nonRetriableExceptions;
    private final Set<RetryInfo> retryInfo = Sets.newHashSet();

    private ExceptionHandler(Builder builder) {
        this.interceptors = builder.interceptors.build();
        this.retriableExceptions = builder.retriableExceptions.build();
        this.nonRetriableExceptions = builder.nonRetriableExceptions.build();
        Preconditions.checkArgument(Sets.intersection(this.retriableExceptions, this.nonRetriableExceptions).isEmpty(), "Same exception was found in both retryable and non-retryable sets");
        for (Class clazz : this.retriableExceptions) {
            ExceptionHandler.addRetryInfo(new RetryInfo(clazz, Interceptor.RetryResult.RETRY), this.retryInfo);
        }
        for (Class clazz : this.nonRetriableExceptions) {
            ExceptionHandler.addRetryInfo(new RetryInfo(clazz, Interceptor.RetryResult.NO_RETRY), this.retryInfo);
        }
    }

    private static void addRetryInfo(RetryInfo retryInfo, Set<RetryInfo> dest) {
        for (RetryInfo current : dest) {
            if (current.exception.isAssignableFrom(retryInfo.exception)) {
                ExceptionHandler.addRetryInfo(retryInfo, current.children);
                return;
            }
            if (!retryInfo.exception.isAssignableFrom(current.exception)) continue;
            retryInfo.children.add(current);
        }
        dest.removeAll(retryInfo.children);
        dest.add(retryInfo);
    }

    private static RetryInfo findMostSpecificRetryInfo(Set<RetryInfo> retryInfo, Class<? extends Exception> exception) {
        for (RetryInfo current : retryInfo) {
            if (!current.exception.isAssignableFrom(exception)) continue;
            RetryInfo match = ExceptionHandler.findMostSpecificRetryInfo(current.children, exception);
            return match == null ? current : match;
        }
        return null;
    }

    private static Method getCallableMethod(Class<?> clazz) {
        try {
            return clazz.getDeclaredMethod("call", new Class[0]);
        }
        catch (NoSuchMethodException e) {
            return ExceptionHandler.getCallableMethod(clazz.getSuperclass());
        }
        catch (SecurityException e) {
            throw new IllegalStateException("Unexpected exception", e);
        }
    }

    void verifyCaller(Callable<?> callable) {
        Method callMethod = ExceptionHandler.getCallableMethod(callable.getClass());
        for (Class<?> exceptionOrError : callMethod.getExceptionTypes()) {
            Preconditions.checkArgument(Exception.class.isAssignableFrom(exceptionOrError), "Callable method exceptions must be derived from Exception");
            Class<?> exception = exceptionOrError;
            Preconditions.checkArgument(ExceptionHandler.findMostSpecificRetryInfo(this.retryInfo, exception) != null, "Declared exception '" + exception + "' is not covered by exception handler");
        }
    }

    @Override
    public boolean shouldRetry(Throwable prevThrowable, Object prevResponse) {
        if (!(prevThrowable instanceof Exception)) {
            return false;
        }
        Exception ex = (Exception)prevThrowable;
        for (Interceptor interceptor : this.interceptors) {
            Interceptor.RetryResult retryResult = Preconditions.checkNotNull(interceptor.beforeEval(ex));
            if (retryResult == Interceptor.RetryResult.CONTINUE_EVALUATION) continue;
            return retryResult == Interceptor.RetryResult.RETRY;
        }
        RetryInfo retryInfo = ExceptionHandler.findMostSpecificRetryInfo(this.retryInfo, ex.getClass());
        Interceptor.RetryResult retryResult = retryInfo == null ? Interceptor.RetryResult.NO_RETRY : retryInfo.retry;
        for (Interceptor interceptor : this.interceptors) {
            Interceptor.RetryResult interceptorRetry = Preconditions.checkNotNull(interceptor.afterEval(ex, retryResult));
            if (interceptorRetry == Interceptor.RetryResult.CONTINUE_EVALUATION) continue;
            retryResult = interceptorRetry;
        }
        return retryResult == Interceptor.RetryResult.RETRY;
    }

    @Override
    public TimedAttemptSettings createNextAttempt(Throwable prevThrowable, Object prevResponse, TimedAttemptSettings prevSettings) {
        return null;
    }

    public int hashCode() {
        return Objects.hash(this.interceptors, this.retriableExceptions, this.nonRetriableExceptions, this.retryInfo);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExceptionHandler)) {
            return false;
        }
        ExceptionHandler other = (ExceptionHandler)obj;
        return Objects.equals(this.interceptors, other.interceptors) && Objects.equals(this.retriableExceptions, other.retriableExceptions) && Objects.equals(this.nonRetriableExceptions, other.nonRetriableExceptions) && Objects.equals(this.retryInfo, other.retryInfo);
    }

    public static ExceptionHandler getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @VisibleForTesting
    static final class RetryInfo
    implements Serializable {
        private static final long serialVersionUID = -4264634837841455974L;
        private final Class<? extends Exception> exception;
        private final Interceptor.RetryResult retry;
        private final Set<RetryInfo> children = Sets.newHashSet();

        RetryInfo(Class<? extends Exception> exception, Interceptor.RetryResult retry) {
            this.exception = Preconditions.checkNotNull(exception);
            this.retry = Preconditions.checkNotNull(retry);
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RetryInfo)) {
                return false;
            }
            return ((RetryInfo)obj).exception.equals(this.exception);
        }
    }

    public static class Builder {
        private final ImmutableList.Builder<Interceptor> interceptors = ImmutableList.builder();
        private final ImmutableSet.Builder<Class<? extends Exception>> retriableExceptions = ImmutableSet.builder();
        private final ImmutableSet.Builder<Class<? extends Exception>> nonRetriableExceptions = ImmutableSet.builder();

        private Builder() {
        }

        public Builder addInterceptors(Interceptor ... interceptors) {
            for (Interceptor interceptor : interceptors) {
                this.interceptors.add((Object)interceptor);
            }
            return this;
        }

        @SafeVarargs
        public final Builder retryOn(Class<? extends Exception> ... exceptions2) {
            for (Class<? extends Exception> exception : exceptions2) {
                this.retriableExceptions.add((Object)Preconditions.checkNotNull(exception));
            }
            return this;
        }

        @SafeVarargs
        public final Builder abortOn(Class<? extends Exception> ... exceptions2) {
            for (Class<? extends Exception> exception : exceptions2) {
                this.nonRetriableExceptions.add((Object)Preconditions.checkNotNull(exception));
            }
            return this;
        }

        public ExceptionHandler build() {
            return new ExceptionHandler(this);
        }
    }

    public static interface Interceptor
    extends Serializable {
        public RetryResult beforeEval(Exception var1);

        public RetryResult afterEval(Exception var1, RetryResult var2);

        public static enum RetryResult {
            NO_RETRY,
            RETRY,
            CONTINUE_EVALUATION;

        }
    }
}

