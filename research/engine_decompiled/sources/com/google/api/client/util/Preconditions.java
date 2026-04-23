/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class Preconditions {
    public static void checkArgument(boolean expression) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkArgument(expression);
    }

    public static void checkArgument(boolean expression, Object errorMessage2) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkArgument(expression, errorMessage2);
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object ... errorMessageArgs2) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkArgument(expression, errorMessageTemplate, errorMessageArgs2);
    }

    public static void checkState(boolean expression) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkState(expression);
    }

    public static void checkState(boolean expression, Object errorMessage2) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkState(expression, errorMessage2);
    }

    public static void checkState(boolean expression, String errorMessageTemplate, Object ... errorMessageArgs2) {
        com.google.api.client.repackaged.com.google.common.base.Preconditions.checkState(expression, errorMessageTemplate, errorMessageArgs2);
    }

    public static <T> T checkNotNull(T reference) {
        return com.google.api.client.repackaged.com.google.common.base.Preconditions.checkNotNull(reference);
    }

    public static <T> T checkNotNull(T reference, Object errorMessage2) {
        return com.google.api.client.repackaged.com.google.common.base.Preconditions.checkNotNull(reference, errorMessage2);
    }

    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object ... errorMessageArgs2) {
        return com.google.api.client.repackaged.com.google.common.base.Preconditions.checkNotNull(reference, errorMessageTemplate, errorMessageArgs2);
    }

    private Preconditions() {
    }
}

