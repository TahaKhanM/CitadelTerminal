/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.HeaderProvider;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

@BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
public class NoHeaderProvider
implements HeaderProvider,
Serializable {
    private static final long serialVersionUID = 7323717933589691233L;

    @Override
    public Map<String, String> getHeaders() {
        return Collections.emptyMap();
    }
}

