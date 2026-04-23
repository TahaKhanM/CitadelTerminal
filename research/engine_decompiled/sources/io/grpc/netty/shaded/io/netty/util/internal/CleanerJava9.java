/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.util.internal;

import io.grpc.netty.shaded.io.netty.util.internal.Cleaner;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent0;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

final class CleanerJava9
implements Cleaner {
    private static final InternalLogger logger;
    private static final Method INVOKE_CLEANER;

    CleanerJava9() {
    }

    static boolean isSupported() {
        return INVOKE_CLEANER != null;
    }

    @Override
    public void freeDirectBuffer(ByteBuffer buffer) {
        try {
            INVOKE_CLEANER.invoke((Object)PlatformDependent0.UNSAFE, buffer);
        }
        catch (Throwable cause) {
            PlatformDependent0.throwException(cause);
        }
    }

    static {
        Throwable error2;
        Method method;
        logger = InternalLoggerFactory.getInstance(CleanerJava9.class);
        if (PlatformDependent0.hasUnsafe()) {
            Object maybeInvokeMethod;
            ByteBuffer buffer = ByteBuffer.allocateDirect(1);
            try {
                Method m = PlatformDependent0.UNSAFE.getClass().getDeclaredMethod("invokeCleaner", ByteBuffer.class);
                m.invoke((Object)PlatformDependent0.UNSAFE, buffer);
                maybeInvokeMethod = m;
            }
            catch (NoSuchMethodException e) {
                maybeInvokeMethod = e;
            }
            catch (InvocationTargetException e) {
                maybeInvokeMethod = e;
            }
            catch (IllegalAccessException e) {
                maybeInvokeMethod = e;
            }
            if (maybeInvokeMethod instanceof Throwable) {
                method = null;
                error2 = (Throwable)maybeInvokeMethod;
            } else {
                method = (Method)maybeInvokeMethod;
                error2 = null;
            }
        } else {
            method = null;
            error2 = new UnsupportedOperationException("sun.misc.Unsafe unavailable");
        }
        if (error2 == null) {
            logger.debug("java.nio.ByteBuffer.cleaner(): available");
        } else {
            logger.debug("java.nio.ByteBuffer.cleaner(): unavailable", error2);
        }
        INVOKE_CLEANER = method;
    }
}

