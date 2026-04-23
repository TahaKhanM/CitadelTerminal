/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.InternalApi;

@InternalApi(value="For use by transport-specific implementations")
public interface RequestParamsEncoder<RequestT> {
    public String encode(RequestT var1);
}

