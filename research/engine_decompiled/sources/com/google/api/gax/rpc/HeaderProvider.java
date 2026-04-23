/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import java.util.Map;

@BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
public interface HeaderProvider {
    public Map<String, String> getHeaders();
}

