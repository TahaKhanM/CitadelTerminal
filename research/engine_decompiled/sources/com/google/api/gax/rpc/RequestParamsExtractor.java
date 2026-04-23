/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.InternalApi;
import java.util.Map;

@InternalApi(value="For use by transport-specific implementations")
public interface RequestParamsExtractor<RequestT> {
    public Map<String, String> extract(RequestT var1);
}

