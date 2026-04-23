/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.grpc.ResponseMetadataHandler;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import io.grpc.CallOptions;
import io.grpc.Metadata;
import java.util.Collections;
import java.util.Map;

class CallOptionsUtil {
    private static final CallOptions.Key<Map<Metadata.Key<String>, String>> DYNAMIC_HEADERS_CALL_OPTION_KEY = CallOptions.Key.createWithDefault("gax_dynamic_headers", Collections.emptyMap());
    static Metadata.Key<String> REQUEST_PARAMS_HEADER_KEY = Metadata.Key.of("x-goog-request-params", Metadata.ASCII_STRING_MARSHALLER);
    private static final CallOptions.Key<ResponseMetadataHandler> METADATA_HANDLER_CALL_OPTION_KEY = CallOptions.Key.createWithDefault("gax_metadata_handler", null);

    private CallOptionsUtil() {
    }

    static CallOptions putRequestParamsDynamicHeaderOption(CallOptions callOptions, String requestParams) {
        if (callOptions == null || requestParams.isEmpty()) {
            return callOptions;
        }
        Map<Metadata.Key<String>, String> dynamicHeadersOption = callOptions.getOption(DYNAMIC_HEADERS_CALL_OPTION_KEY);
        dynamicHeadersOption = ImmutableMap.builder().putAll(dynamicHeadersOption).put(REQUEST_PARAMS_HEADER_KEY, requestParams).build();
        return callOptions.withOption(DYNAMIC_HEADERS_CALL_OPTION_KEY, dynamicHeadersOption);
    }

    static Map<Metadata.Key<String>, String> getDynamicHeadersOption(CallOptions callOptions) {
        return callOptions.getOption(DYNAMIC_HEADERS_CALL_OPTION_KEY);
    }

    static CallOptions putMetadataHandlerOption(CallOptions callOptions, ResponseMetadataHandler handler) {
        Preconditions.checkNotNull(callOptions);
        Preconditions.checkNotNull(handler);
        return callOptions.withOption(METADATA_HANDLER_CALL_OPTION_KEY, handler);
    }

    public static ResponseMetadataHandler getMetadataHandlerOption(CallOptions callOptions) {
        return callOptions.getOption(METADATA_HANDLER_CALL_OPTION_KEY);
    }
}

