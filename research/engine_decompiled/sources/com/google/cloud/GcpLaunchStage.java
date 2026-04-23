/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class GcpLaunchStage {
    private GcpLaunchStage() {
    }

    @Retention(value=RetentionPolicy.RUNTIME)
    public static @interface Deprecated {
    }

    @Retention(value=RetentionPolicy.RUNTIME)
    public static @interface Beta {
    }

    @Retention(value=RetentionPolicy.RUNTIME)
    public static @interface Alpha {
    }

    @Retention(value=RetentionPolicy.RUNTIME)
    public static @interface EarlyAccess {
    }
}

