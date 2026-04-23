/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.jdk8;

public final class Jdk8Methods {
    private Jdk8Methods() {
    }

    public static <T> T requireNonNull(T value) {
        if (value == null) {
            throw new NullPointerException("Value must not be null");
        }
        return value;
    }

    public static <T> T requireNonNull(T value, String parameterName) {
        if (value == null) {
            throw new NullPointerException(parameterName + " must not be null");
        }
        return value;
    }

    public static boolean equals(Object a, Object b) {
        if (a == null) {
            return b == null;
        }
        if (b == null) {
            return false;
        }
        return a.equals(b);
    }

    public static int compareInts(int a, int b) {
        if (a < b) {
            return -1;
        }
        if (a > b) {
            return 1;
        }
        return 0;
    }

    public static int compareLongs(long a, long b) {
        if (a < b) {
            return -1;
        }
        if (a > b) {
            return 1;
        }
        return 0;
    }

    public static int safeAdd(int a, int b) {
        int sum2 = a + b;
        if ((a ^ sum2) < 0 && (a ^ b) >= 0) {
            throw new ArithmeticException("Addition overflows an int: " + a + " + " + b);
        }
        return sum2;
    }

    public static long safeAdd(long a, long b) {
        long sum2 = a + b;
        if ((a ^ sum2) < 0L && (a ^ b) >= 0L) {
            throw new ArithmeticException("Addition overflows a long: " + a + " + " + b);
        }
        return sum2;
    }

    public static int safeSubtract(int a, int b) {
        int result2 = a - b;
        if ((a ^ result2) < 0 && (a ^ b) < 0) {
            throw new ArithmeticException("Subtraction overflows an int: " + a + " - " + b);
        }
        return result2;
    }

    public static long safeSubtract(long a, long b) {
        long result2 = a - b;
        if ((a ^ result2) < 0L && (a ^ b) < 0L) {
            throw new ArithmeticException("Subtraction overflows a long: " + a + " - " + b);
        }
        return result2;
    }

    public static int safeMultiply(int a, int b) {
        long total2 = (long)a * (long)b;
        if (total2 < Integer.MIN_VALUE || total2 > Integer.MAX_VALUE) {
            throw new ArithmeticException("Multiplication overflows an int: " + a + " * " + b);
        }
        return (int)total2;
    }

    public static long safeMultiply(long a, int b) {
        switch (b) {
            case -1: {
                if (a == Long.MIN_VALUE) {
                    throw new ArithmeticException("Multiplication overflows a long: " + a + " * " + b);
                }
                return -a;
            }
            case 0: {
                return 0L;
            }
            case 1: {
                return a;
            }
        }
        long total2 = a * (long)b;
        if (total2 / (long)b != a) {
            throw new ArithmeticException("Multiplication overflows a long: " + a + " * " + b);
        }
        return total2;
    }

    public static long safeMultiply(long a, long b) {
        if (b == 1L) {
            return a;
        }
        if (a == 1L) {
            return b;
        }
        if (a == 0L || b == 0L) {
            return 0L;
        }
        long total2 = a * b;
        if (total2 / b != a || a == Long.MIN_VALUE && b == -1L || b == Long.MIN_VALUE && a == -1L) {
            throw new ArithmeticException("Multiplication overflows a long: " + a + " * " + b);
        }
        return total2;
    }

    public static int safeToInt(long value) {
        if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
            throw new ArithmeticException("Calculation overflows an int: " + value);
        }
        return (int)value;
    }

    public static long floorDiv(long a, long b) {
        return a >= 0L ? a / b : (a + 1L) / b - 1L;
    }

    public static long floorMod(long a, long b) {
        return (a % b + b) % b;
    }

    public static int floorMod(long a, int b) {
        return (int)((a % (long)b + (long)b) % (long)b);
    }

    public static int floorDiv(int a, int b) {
        return a >= 0 ? a / b : (a + 1) / b - 1;
    }

    public static int floorMod(int a, int b) {
        return (a % b + b) % b;
    }
}

