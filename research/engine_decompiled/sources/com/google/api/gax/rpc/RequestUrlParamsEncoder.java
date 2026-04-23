/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.InternalApi;
import com.google.api.gax.rpc.RequestParamsEncoder;
import com.google.api.gax.rpc.RequestParamsExtractor;
import com.google.common.base.Preconditions;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@InternalApi(value="For use by transport-specific implementations")
public class RequestUrlParamsEncoder<RequestT>
implements RequestParamsEncoder<RequestT> {
    private static final String STR_ENCODING = "UTF-8";
    private final RequestParamsExtractor<RequestT> paramsExtractor;
    private final boolean validateExtractedParameters;

    public RequestUrlParamsEncoder(RequestParamsExtractor<RequestT> paramsExtractor, boolean validateExtractedParameters) {
        this.paramsExtractor = Preconditions.checkNotNull(paramsExtractor);
        this.validateExtractedParameters = validateExtractedParameters;
    }

    @Override
    public String encode(RequestT request) {
        Map<String, String> params2 = this.paramsExtractor.extract(request);
        if (params2.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params2.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            String name = entry.getKey();
            String value = entry.getValue();
            if (name == null) {
                throw new IllegalArgumentException("Request parameter name cannot be null");
            }
            if (value == null) continue;
            if (!this.isValid(name, value)) {
                throw new IllegalArgumentException("Invalid url-encoded request parameter name-value pair: " + name + "=" + value);
            }
            sb.append(name).append("=").append(value);
        }
        return sb.toString();
    }

    private boolean isValid(String name, String value) {
        try {
            return !this.validateExtractedParameters || name.equals(URLEncoder.encode(URLDecoder.decode(name, STR_ENCODING), STR_ENCODING)) && value.equals(URLEncoder.encode(URLDecoder.decode(value, STR_ENCODING), STR_ENCODING));
        }
        catch (UnsupportedEncodingException e) {
            return false;
        }
    }
}

