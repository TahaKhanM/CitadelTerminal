/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.StatusCode;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public class WatchdogTimeoutException
extends ApiException {
    private static final long serialVersionUID = -777463630112442086L;
    public static final StatusCode LOCAL_ABORTED_STATUS_CODE = new StatusCode(){

        @Override
        public StatusCode.Code getCode() {
            return StatusCode.Code.ABORTED;
        }

        @Override
        public Object getTransportCode() {
            return null;
        }
    };

    WatchdogTimeoutException(String message, boolean retry) {
        super(message, null, LOCAL_ABORTED_STATUS_CODE, retry);
    }
}

