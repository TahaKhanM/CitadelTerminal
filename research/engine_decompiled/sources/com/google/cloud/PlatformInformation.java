/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.InternalApi;

@InternalApi(value="This class should only be used within google-cloud-java")
public final class PlatformInformation {
    public static final String GAE_RUNTIME = System.getenv("GAE_RUNTIME");

    private PlatformInformation() {
    }

    public static boolean isOnGAEStandard7() {
        return "java7".equals(GAE_RUNTIME);
    }

    public static boolean isOnGAEStandard8() {
        return "java8".equals(GAE_RUNTIME);
    }
}

