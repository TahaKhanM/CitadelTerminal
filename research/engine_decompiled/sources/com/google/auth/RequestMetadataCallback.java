/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth;

import java.util.List;
import java.util.Map;

public interface RequestMetadataCallback {
    public void onSuccess(Map<String, List<String>> var1);

    public void onFailure(Throwable var1);
}

