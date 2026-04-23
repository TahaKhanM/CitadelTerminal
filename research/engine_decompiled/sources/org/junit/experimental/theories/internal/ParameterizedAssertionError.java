/*
 * Decompiled with CFR 0.152.
 */
package org.junit.experimental.theories.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ParameterizedAssertionError
extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ParameterizedAssertionError(Throwable targetException, String methodName, Object ... params2) {
        super(String.format("%s(%s)", methodName, ParameterizedAssertionError.join(", ", params2)), targetException);
    }

    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }

    public static String join(String delimiter, Object ... params2) {
        return ParameterizedAssertionError.join(delimiter, Arrays.asList(params2));
    }

    public static String join(String delimiter, Collection<Object> values) {
        StringBuffer buffer = new StringBuffer();
        Iterator<Object> iter2 = values.iterator();
        while (iter2.hasNext()) {
            Object next2 = iter2.next();
            buffer.append(ParameterizedAssertionError.stringValueOf(next2));
            if (!iter2.hasNext()) continue;
            buffer.append(delimiter);
        }
        return buffer.toString();
    }

    private static String stringValueOf(Object next2) {
        try {
            return String.valueOf(next2);
        }
        catch (Throwable e) {
            return "[toString failed]";
        }
    }
}

