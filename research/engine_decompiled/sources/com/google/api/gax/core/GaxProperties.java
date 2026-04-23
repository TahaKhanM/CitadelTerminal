/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import com.google.api.core.InternalApi;

@InternalApi
public class GaxProperties {
    private static final String DEFAULT_VERSION = "";
    private static final String GAX_VERSION = GaxProperties.getLibraryVersion(GaxProperties.class);
    private static final String JAVA_VERSION = GaxProperties.getLibraryVersion(Runtime.class);

    private GaxProperties() {
    }

    public static String getLibraryVersion(Class<?> libraryClass) {
        String version = libraryClass.getPackage().getImplementationVersion();
        return version != null ? version : DEFAULT_VERSION;
    }

    public static String getJavaVersion() {
        return JAVA_VERSION;
    }

    public static String getGaxVersion() {
        return GAX_VERSION;
    }
}

