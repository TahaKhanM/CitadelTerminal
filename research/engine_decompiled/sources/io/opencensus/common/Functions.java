/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.common;

import io.opencensus.common.Function;

public final class Functions {
    private static final Function<Object, Void> RETURN_NULL = new Function<Object, Void>(){

        @Override
        public Void apply(Object ignored) {
            return null;
        }
    };
    private static final Function<Object, Void> THROW_ILLEGAL_ARGUMENT_EXCEPTION = new Function<Object, Void>(){

        @Override
        public Void apply(Object ignored) {
            throw new IllegalArgumentException();
        }
    };
    private static final Function<Object, Void> THROW_ASSERTION_ERROR = new Function<Object, Void>(){

        @Override
        public Void apply(Object ignored) {
            throw new AssertionError();
        }
    };

    private Functions() {
    }

    public static <T> Function<Object, T> returnNull() {
        Function<Object, Void> function = RETURN_NULL;
        return function;
    }

    public static <T> Function<Object, T> returnConstant(final T constant) {
        return new Function<Object, T>(){

            @Override
            public T apply(Object ignored) {
                return constant;
            }
        };
    }

    public static <T> Function<Object, T> throwIllegalArgumentException() {
        Function<Object, Void> function = THROW_ILLEGAL_ARGUMENT_EXCEPTION;
        return function;
    }

    public static <T> Function<Object, T> throwAssertionError() {
        Function<Object, Void> function = THROW_ASSERTION_ERROR;
        return function;
    }
}

