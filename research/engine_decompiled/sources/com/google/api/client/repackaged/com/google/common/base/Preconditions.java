/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import javax.annotation.Nullable;

@GwtCompatible
public final class Preconditions {
    private Preconditions() {
    }

    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean expression, @Nullable Object errorMessage2) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage2));
        }
    }

    public static void checkArgument(boolean expression, @Nullable String errorMessageTemplate, Object ... errorMessageArgs2) {
        if (!expression) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, errorMessageArgs2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, char p1) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1)));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, int p1) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, long p1) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, char p1, char p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1), Character.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, char p1, int p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, char p1, long p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, char p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, int p1, char p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, int p1, int p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, int p1, long p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, int p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, long p1, char p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, long p1, int p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, long p1, long p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, long p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, char p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, int p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, long p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2, @Nullable Object p3) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1, p2, p3));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2, @Nullable Object p3, @Nullable Object p4) {
        if (!b) {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, p1, p2, p3, p4));
        }
    }

    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean expression, @Nullable Object errorMessage2) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage2));
        }
    }

    public static void checkState(boolean expression, @Nullable String errorMessageTemplate, Object ... errorMessageArgs2) {
        if (!expression) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, errorMessageArgs2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, char p1) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1)));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, int p1) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, long p1) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, char p1, char p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1), Character.valueOf(p2)));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, char p1, int p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, char p1, long p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, char p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, int p1, char p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, int p1, int p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, int p1, long p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, int p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, long p1, char p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, long p1, int p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, long p1, long p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, long p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, char p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, int p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, long p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2, @Nullable Object p3) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1, p2, p3));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2, @Nullable Object p3, @Nullable Object p4) {
        if (!b) {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, p1, p2, p3, p4));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage2) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage2));
        }
        return reference;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, @Nullable String errorMessageTemplate, Object ... errorMessageArgs2) {
        if (reference == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, errorMessageArgs2));
        }
        return reference;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, char p1) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1)));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, int p1) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, long p1) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, @Nullable Object p1) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, char p1, char p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1), Character.valueOf(p2)));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, char p1, int p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1), p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, char p1, long p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1), p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, char p1, @Nullable Object p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, Character.valueOf(p1), p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, int p1, char p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, int p1, int p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, int p1, long p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, int p1, @Nullable Object p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, long p1, char p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, long p1, int p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, long p1, long p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, long p1, @Nullable Object p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, char p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, int p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, long p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2, @Nullable Object p3) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1, p2, p3));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2, @Nullable Object p3, @Nullable Object p4) {
        if (obj == null) {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, p1, p2, p3, p4));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int index, int size2) {
        return Preconditions.checkElementIndex(index, size2, "index");
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int index, int size2, @Nullable String desc) {
        if (index < 0 || index >= size2) {
            throw new IndexOutOfBoundsException(Preconditions.badElementIndex(index, size2, desc));
        }
        return index;
    }

    private static String badElementIndex(int index, int size2, String desc) {
        if (index < 0) {
            return Preconditions.format("%s (%s) must not be negative", desc, index);
        }
        if (size2 < 0) {
            throw new IllegalArgumentException("negative size: " + size2);
        }
        return Preconditions.format("%s (%s) must be less than size (%s)", desc, index, size2);
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int index, int size2) {
        return Preconditions.checkPositionIndex(index, size2, "index");
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int index, int size2, @Nullable String desc) {
        if (index < 0 || index > size2) {
            throw new IndexOutOfBoundsException(Preconditions.badPositionIndex(index, size2, desc));
        }
        return index;
    }

    private static String badPositionIndex(int index, int size2, String desc) {
        if (index < 0) {
            return Preconditions.format("%s (%s) must not be negative", desc, index);
        }
        if (size2 < 0) {
            throw new IllegalArgumentException("negative size: " + size2);
        }
        return Preconditions.format("%s (%s) must not be greater than size (%s)", desc, index, size2);
    }

    public static void checkPositionIndexes(int start, int end, int size2) {
        if (start < 0 || end < start || end > size2) {
            throw new IndexOutOfBoundsException(Preconditions.badPositionIndexes(start, end, size2));
        }
    }

    private static String badPositionIndexes(int start, int end, int size2) {
        if (start < 0 || start > size2) {
            return Preconditions.badPositionIndex(start, size2, "start index");
        }
        if (end < 0 || end > size2) {
            return Preconditions.badPositionIndex(end, size2, "end index");
        }
        return Preconditions.format("end index (%s) must not be less than start index (%s)", end, start);
    }

    static String format(String template, Object ... args) {
        int placeholderStart;
        template = String.valueOf(template);
        StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
        int templateStart = 0;
        int i = 0;
        while (i < args.length && (placeholderStart = template.indexOf("%s", templateStart)) != -1) {
            builder.append(template, templateStart, placeholderStart);
            builder.append(args[i++]);
            templateStart = placeholderStart + 2;
        }
        builder.append(template, templateStart, template.length());
        if (i < args.length) {
            builder.append(" [");
            builder.append(args[i++]);
            while (i < args.length) {
                builder.append(", ");
                builder.append(args[i++]);
            }
            builder.append(']');
        }
        return builder.toString();
    }
}

