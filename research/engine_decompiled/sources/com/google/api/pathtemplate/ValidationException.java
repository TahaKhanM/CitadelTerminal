/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.pathtemplate;

import java.util.Stack;

public class ValidationException
extends IllegalArgumentException {
    private static ThreadLocal<Stack<Supplier<String>>> contextLocal = new ThreadLocal();

    public static void pushCurrentThreadValidationContext(Supplier<String> supplier) {
        Stack<Supplier<String>> stack = contextLocal.get();
        if (stack == null) {
            stack = new Stack();
            contextLocal.set(stack);
        }
        stack.push(supplier);
    }

    public static void pushCurrentThreadValidationContext(final String context) {
        ValidationException.pushCurrentThreadValidationContext(new Supplier<String>(){

            @Override
            public String get() {
                return context;
            }
        });
    }

    public static void popCurrentThreadValidationContext() {
        Stack<Supplier<String>> stack = contextLocal.get();
        if (stack != null) {
            stack.pop();
        }
    }

    public ValidationException(String format2, Object ... args) {
        super(ValidationException.message(contextLocal.get(), format2, args));
    }

    private static String message(Stack<Supplier<String>> context, String format2, Object ... args) {
        if (context == null || context.isEmpty()) {
            return String.format(format2, args);
        }
        StringBuilder result2 = new StringBuilder();
        for (Supplier supplier : context) {
            result2.append((String)supplier.get() + ": ");
        }
        return result2.toString() + String.format(format2, args);
    }

    public static interface Supplier<T> {
        public T get();
    }
}

