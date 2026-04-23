/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;
import io.grpc.internal.RetriableStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@VisibleForTesting
public final class ServiceConfigUtil {
    private static final String SERVICE_CONFIG_METHOD_CONFIG_KEY = "methodConfig";
    private static final String SERVICE_CONFIG_LOAD_BALANCING_POLICY_KEY = "loadBalancingPolicy";
    private static final String SERVICE_CONFIG_STICKINESS_METADATA_KEY = "stickinessMetadataKey";
    private static final String METHOD_CONFIG_NAME_KEY = "name";
    private static final String METHOD_CONFIG_TIMEOUT_KEY = "timeout";
    private static final String METHOD_CONFIG_WAIT_FOR_READY_KEY = "waitForReady";
    private static final String METHOD_CONFIG_MAX_REQUEST_MESSAGE_BYTES_KEY = "maxRequestMessageBytes";
    private static final String METHOD_CONFIG_MAX_RESPONSE_MESSAGE_BYTES_KEY = "maxResponseMessageBytes";
    private static final String METHOD_CONFIG_RETRY_POLICY_KEY = "retryPolicy";
    private static final String NAME_SERVICE_KEY = "service";
    private static final String NAME_METHOD_KEY = "method";
    private static final String RETRY_POLICY_MAX_ATTEMPTS_KEY = "maxAttempts";
    private static final String RETRY_POLICY_INITIAL_BACKOFF_KEY = "initialBackoff";
    private static final String RETRY_POLICY_MAX_BACKOFF_KEY = "maxBackoff";
    private static final String RETRY_POLICY_BACKOFF_MULTIPLIER_KEY = "backoffMultiplier";
    private static final String RETRY_POLICY_RETRYABLE_STATUS_CODES_KEY = "retryableStatusCodes";
    private static final long DURATION_SECONDS_MIN = -315576000000L;
    private static final long DURATION_SECONDS_MAX = 315576000000L;
    private static final long NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1L);

    private ServiceConfigUtil() {
    }

    @Nullable
    static RetriableStream.Throttle getThrottlePolicy(@Nullable Map<String, Object> serviceConfig) {
        String retryThrottlingKey = "retryThrottling";
        if (serviceConfig == null || !serviceConfig.containsKey(retryThrottlingKey)) {
            return null;
        }
        Map<String, Object> throttling = ServiceConfigUtil.getObject(serviceConfig, retryThrottlingKey);
        float maxTokens = ServiceConfigUtil.getDouble(throttling, "maxTokens").floatValue();
        float tokenRatio = ServiceConfigUtil.getDouble(throttling, "tokenRatio").floatValue();
        Preconditions.checkState(maxTokens > 0.0f, "maxToken should be greater than zero");
        Preconditions.checkState(tokenRatio > 0.0f, "tokenRatio should be greater than zero");
        return new RetriableStream.Throttle(maxTokens, tokenRatio);
    }

    @Nullable
    static Integer getMaxAttemptsFromRetryPolicy(Map<String, Object> retryPolicy) {
        if (!retryPolicy.containsKey(RETRY_POLICY_MAX_ATTEMPTS_KEY)) {
            return null;
        }
        return ServiceConfigUtil.getDouble(retryPolicy, RETRY_POLICY_MAX_ATTEMPTS_KEY).intValue();
    }

    @Nullable
    static Long getInitialBackoffNanosFromRetryPolicy(Map<String, Object> retryPolicy) {
        if (!retryPolicy.containsKey(RETRY_POLICY_INITIAL_BACKOFF_KEY)) {
            return null;
        }
        String rawInitialBackoff = ServiceConfigUtil.getString(retryPolicy, RETRY_POLICY_INITIAL_BACKOFF_KEY);
        try {
            return ServiceConfigUtil.parseDuration(rawInitialBackoff);
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    static Long getMaxBackoffNanosFromRetryPolicy(Map<String, Object> retryPolicy) {
        if (!retryPolicy.containsKey(RETRY_POLICY_MAX_BACKOFF_KEY)) {
            return null;
        }
        String rawMaxBackoff = ServiceConfigUtil.getString(retryPolicy, RETRY_POLICY_MAX_BACKOFF_KEY);
        try {
            return ServiceConfigUtil.parseDuration(rawMaxBackoff);
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    static Double getBackoffMultiplierFromRetryPolicy(Map<String, Object> retryPolicy) {
        if (!retryPolicy.containsKey(RETRY_POLICY_BACKOFF_MULTIPLIER_KEY)) {
            return null;
        }
        return ServiceConfigUtil.getDouble(retryPolicy, RETRY_POLICY_BACKOFF_MULTIPLIER_KEY);
    }

    @Nullable
    static List<String> getRetryableStatusCodesFromRetryPolicy(Map<String, Object> retryPolicy) {
        if (!retryPolicy.containsKey(RETRY_POLICY_RETRYABLE_STATUS_CODES_KEY)) {
            return null;
        }
        return ServiceConfigUtil.checkStringList(ServiceConfigUtil.getList(retryPolicy, RETRY_POLICY_RETRYABLE_STATUS_CODES_KEY));
    }

    @Nullable
    static String getServiceFromName(Map<String, Object> name) {
        if (!name.containsKey(NAME_SERVICE_KEY)) {
            return null;
        }
        return ServiceConfigUtil.getString(name, NAME_SERVICE_KEY);
    }

    @Nullable
    static String getMethodFromName(Map<String, Object> name) {
        if (!name.containsKey(NAME_METHOD_KEY)) {
            return null;
        }
        return ServiceConfigUtil.getString(name, NAME_METHOD_KEY);
    }

    @Nullable
    static Map<String, Object> getRetryPolicyFromMethodConfig(Map<String, Object> methodConfig) {
        if (!methodConfig.containsKey(METHOD_CONFIG_RETRY_POLICY_KEY)) {
            return null;
        }
        return ServiceConfigUtil.getObject(methodConfig, METHOD_CONFIG_RETRY_POLICY_KEY);
    }

    @Nullable
    static List<Map<String, Object>> getNameListFromMethodConfig(Map<String, Object> methodConfig) {
        if (!methodConfig.containsKey(METHOD_CONFIG_NAME_KEY)) {
            return null;
        }
        return ServiceConfigUtil.checkObjectList(ServiceConfigUtil.getList(methodConfig, METHOD_CONFIG_NAME_KEY));
    }

    @Nullable
    static Long getTimeoutFromMethodConfig(Map<String, Object> methodConfig) {
        if (!methodConfig.containsKey(METHOD_CONFIG_TIMEOUT_KEY)) {
            return null;
        }
        String rawTimeout = ServiceConfigUtil.getString(methodConfig, METHOD_CONFIG_TIMEOUT_KEY);
        try {
            return ServiceConfigUtil.parseDuration(rawTimeout);
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    static Boolean getWaitForReadyFromMethodConfig(Map<String, Object> methodConfig) {
        if (!methodConfig.containsKey(METHOD_CONFIG_WAIT_FOR_READY_KEY)) {
            return null;
        }
        return ServiceConfigUtil.getBoolean(methodConfig, METHOD_CONFIG_WAIT_FOR_READY_KEY);
    }

    @Nullable
    static Integer getMaxRequestMessageBytesFromMethodConfig(Map<String, Object> methodConfig) {
        if (!methodConfig.containsKey(METHOD_CONFIG_MAX_REQUEST_MESSAGE_BYTES_KEY)) {
            return null;
        }
        return ServiceConfigUtil.getDouble(methodConfig, METHOD_CONFIG_MAX_REQUEST_MESSAGE_BYTES_KEY).intValue();
    }

    @Nullable
    static Integer getMaxResponseMessageBytesFromMethodConfig(Map<String, Object> methodConfig) {
        if (!methodConfig.containsKey(METHOD_CONFIG_MAX_RESPONSE_MESSAGE_BYTES_KEY)) {
            return null;
        }
        return ServiceConfigUtil.getDouble(methodConfig, METHOD_CONFIG_MAX_RESPONSE_MESSAGE_BYTES_KEY).intValue();
    }

    @Nullable
    static List<Map<String, Object>> getMethodConfigFromServiceConfig(Map<String, Object> serviceConfig) {
        if (!serviceConfig.containsKey(SERVICE_CONFIG_METHOD_CONFIG_KEY)) {
            return null;
        }
        return ServiceConfigUtil.checkObjectList(ServiceConfigUtil.getList(serviceConfig, SERVICE_CONFIG_METHOD_CONFIG_KEY));
    }

    @Nullable
    @VisibleForTesting
    public static String getLoadBalancingPolicyFromServiceConfig(Map<String, Object> serviceConfig) {
        if (!serviceConfig.containsKey(SERVICE_CONFIG_LOAD_BALANCING_POLICY_KEY)) {
            return null;
        }
        return ServiceConfigUtil.getString(serviceConfig, SERVICE_CONFIG_LOAD_BALANCING_POLICY_KEY);
    }

    @Nullable
    public static String getStickinessMetadataKeyFromServiceConfig(Map<String, Object> serviceConfig) {
        if (!serviceConfig.containsKey(SERVICE_CONFIG_STICKINESS_METADATA_KEY)) {
            return null;
        }
        return ServiceConfigUtil.getString(serviceConfig, SERVICE_CONFIG_STICKINESS_METADATA_KEY);
    }

    static List<Object> getList(Map<String, Object> obj, String key) {
        assert (obj.containsKey(key));
        Object value = Preconditions.checkNotNull(obj.get(key), "no such key %s", (Object)key);
        if (value instanceof List) {
            return (List)value;
        }
        throw new ClassCastException(String.format("value %s for key %s in %s is not List", value, key, obj));
    }

    static Map<String, Object> getObject(Map<String, Object> obj, String key) {
        assert (obj.containsKey(key));
        Object value = Preconditions.checkNotNull(obj.get(key), "no such key %s", (Object)key);
        if (value instanceof Map) {
            return (Map)value;
        }
        throw new ClassCastException(String.format("value %s for key %s in %s is not object", value, key, obj));
    }

    static Double getDouble(Map<String, Object> obj, String key) {
        assert (obj.containsKey(key));
        Object value = Preconditions.checkNotNull(obj.get(key), "no such key %s", (Object)key);
        if (value instanceof Double) {
            return (Double)value;
        }
        throw new ClassCastException(String.format("value %s for key %s in %s is not Double", value, key, obj));
    }

    static String getString(Map<String, Object> obj, String key) {
        assert (obj.containsKey(key));
        Object value = Preconditions.checkNotNull(obj.get(key), "no such key %s", (Object)key);
        if (value instanceof String) {
            return (String)value;
        }
        throw new ClassCastException(String.format("value %s for key %s in %s is not String", value, key, obj));
    }

    static String getString(List<Object> list2, int i) {
        assert (i >= 0 && i < list2.size());
        Object value = Preconditions.checkNotNull(list2.get(i), "idx %s in %s is null", i, list2);
        if (value instanceof String) {
            return (String)value;
        }
        throw new ClassCastException(String.format("value %s for idx %d in %s is not String", value, i, list2));
    }

    static Boolean getBoolean(Map<String, Object> obj, String key) {
        assert (obj.containsKey(key));
        Object value = Preconditions.checkNotNull(obj.get(key), "no such key %s", (Object)key);
        if (value instanceof Boolean) {
            return (Boolean)value;
        }
        throw new ClassCastException(String.format("value %s for key %s in %s is not Boolean", value, key, obj));
    }

    private static List<Map<String, Object>> checkObjectList(List<Object> rawList) {
        for (int i = 0; i < rawList.size(); ++i) {
            if (rawList.get(i) instanceof Map) continue;
            throw new ClassCastException(String.format("value %s for idx %d in %s is not object", rawList.get(i), i, rawList));
        }
        return rawList;
    }

    static List<String> checkStringList(List<Object> rawList) {
        for (int i = 0; i < rawList.size(); ++i) {
            if (rawList.get(i) instanceof String) continue;
            throw new ClassCastException(String.format("value %s for idx %d in %s is not string", rawList.get(i), i, rawList));
        }
        return rawList;
    }

    private static long parseDuration(String value) throws ParseException {
        int nanos;
        if (value.isEmpty() || value.charAt(value.length() - 1) != 's') {
            throw new ParseException("Invalid duration string: " + value, 0);
        }
        boolean negative = false;
        if (value.charAt(0) == '-') {
            negative = true;
            value = value.substring(1);
        }
        String secondValue = value.substring(0, value.length() - 1);
        String nanoValue = "";
        int pointPosition = secondValue.indexOf(46);
        if (pointPosition != -1) {
            nanoValue = secondValue.substring(pointPosition + 1);
            secondValue = secondValue.substring(0, pointPosition);
        }
        long seconds = Long.parseLong(secondValue);
        int n = nanos = nanoValue.isEmpty() ? 0 : ServiceConfigUtil.parseNanos(nanoValue);
        if (seconds < 0L) {
            throw new ParseException("Invalid duration string: " + value, 0);
        }
        if (negative) {
            seconds = -seconds;
            nanos = -nanos;
        }
        try {
            return ServiceConfigUtil.normalizedDuration(seconds, nanos);
        }
        catch (IllegalArgumentException e) {
            throw new ParseException("Duration value is out of range.", 0);
        }
    }

    private static int parseNanos(String value) throws ParseException {
        int result2 = 0;
        for (int i = 0; i < 9; ++i) {
            result2 *= 10;
            if (i >= value.length()) continue;
            if (value.charAt(i) < '0' || value.charAt(i) > '9') {
                throw new ParseException("Invalid nanoseconds.", 0);
            }
            result2 += value.charAt(i) - 48;
        }
        return result2;
    }

    private static long normalizedDuration(long seconds, int nanos) {
        if ((long)nanos <= -NANOS_PER_SECOND || (long)nanos >= NANOS_PER_SECOND) {
            seconds = LongMath.checkedAdd(seconds, (long)nanos / NANOS_PER_SECOND);
            nanos = (int)((long)nanos % NANOS_PER_SECOND);
        }
        if (seconds > 0L && nanos < 0) {
            nanos = (int)((long)nanos + NANOS_PER_SECOND);
            --seconds;
        }
        if (seconds < 0L && nanos > 0) {
            nanos = (int)((long)nanos - NANOS_PER_SECOND);
            ++seconds;
        }
        if (!ServiceConfigUtil.durationIsValid(seconds, nanos)) {
            throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", seconds, nanos));
        }
        return ServiceConfigUtil.saturatedAdd(TimeUnit.SECONDS.toNanos(seconds), nanos);
    }

    private static boolean durationIsValid(long seconds, int nanos) {
        if (seconds < -315576000000L || seconds > 315576000000L) {
            return false;
        }
        if ((long)nanos < -999999999L || (long)nanos >= NANOS_PER_SECOND) {
            return false;
        }
        return seconds >= 0L && nanos >= 0 || seconds <= 0L && nanos <= 0;
    }

    private static long saturatedAdd(long a, long b) {
        long naiveSum;
        if ((a ^ b) < 0L | (a ^ (naiveSum = a + b)) >= 0L) {
            return naiveSum;
        }
        return Long.MAX_VALUE + (naiveSum >>> 63 ^ 1L);
    }
}

