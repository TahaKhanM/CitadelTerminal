/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.Deadline;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.RetryPolicy;
import io.grpc.internal.ServiceConfigUtil;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

final class ServiceConfigInterceptor
implements ClientInterceptor {
    private static final Logger logger = Logger.getLogger(ServiceConfigInterceptor.class.getName());
    @VisibleForTesting
    final AtomicReference<Map<String, MethodInfo>> serviceMethodMap = new AtomicReference();
    @VisibleForTesting
    final AtomicReference<Map<String, MethodInfo>> serviceMap = new AtomicReference();
    private final boolean retryEnabled;
    private final int maxRetryAttemptsLimit;
    private volatile boolean nameResolveComplete;
    static final CallOptions.Key<RetryPolicy.Provider> RETRY_POLICY_KEY = CallOptions.Key.create("internal-retry-policy");

    ServiceConfigInterceptor(boolean retryEnabled, int maxRetryAttemptsLimit) {
        this.retryEnabled = retryEnabled;
        this.maxRetryAttemptsLimit = maxRetryAttemptsLimit;
    }

    void handleUpdate(@Nonnull Map<String, Object> serviceConfig) {
        HashMap<String, MethodInfo> newServiceMethodConfigs = new HashMap<String, MethodInfo>();
        HashMap<String, MethodInfo> newServiceConfigs = new HashMap<String, MethodInfo>();
        List<Map<String, Object>> methodConfigs = ServiceConfigUtil.getMethodConfigFromServiceConfig(serviceConfig);
        if (methodConfigs == null) {
            logger.log(Level.FINE, "No method configs found, skipping");
            this.nameResolveComplete = true;
            return;
        }
        for (Map<String, Object> methodConfig : methodConfigs) {
            MethodInfo info2 = new MethodInfo(methodConfig, this.retryEnabled, this.maxRetryAttemptsLimit);
            List<Map<String, Object>> nameList = ServiceConfigUtil.getNameListFromMethodConfig(methodConfig);
            Preconditions.checkArgument(nameList != null && !nameList.isEmpty(), "no names in method config %s", methodConfig);
            for (Map<String, Object> name : nameList) {
                String serviceName = ServiceConfigUtil.getServiceFromName(name);
                Preconditions.checkArgument(!Strings.isNullOrEmpty(serviceName), "missing service name");
                String methodName = ServiceConfigUtil.getMethodFromName(name);
                if (Strings.isNullOrEmpty(methodName)) {
                    Preconditions.checkArgument(!newServiceConfigs.containsKey(serviceName), "Duplicate service %s", (Object)serviceName);
                    newServiceConfigs.put(serviceName, info2);
                    continue;
                }
                String fullMethodName = MethodDescriptor.generateFullMethodName(serviceName, methodName);
                Preconditions.checkArgument(!newServiceMethodConfigs.containsKey(fullMethodName), "Duplicate method name %s", (Object)fullMethodName);
                newServiceMethodConfigs.put(fullMethodName, info2);
            }
        }
        this.serviceMethodMap.set(Collections.unmodifiableMap(newServiceMethodConfigs));
        this.serviceMap.set(Collections.unmodifiableMap(newServiceConfigs));
        this.nameResolveComplete = true;
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(final MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next2) {
        Integer existingLimit;
        MethodInfo info2;
        if (this.retryEnabled) {
            if (this.nameResolveComplete) {
                final RetryPolicy retryPolicy = this.getRetryPolicyFromConfig(method);
                final class ImmediateRetryPolicyProvider
                implements RetryPolicy.Provider {
                    ImmediateRetryPolicyProvider() {
                    }

                    @Override
                    public RetryPolicy get() {
                        return retryPolicy;
                    }
                }
                callOptions = callOptions.withOption(RETRY_POLICY_KEY, new ImmediateRetryPolicyProvider());
            } else {
                final class DelayedRetryPolicyProvider
                implements RetryPolicy.Provider {
                    DelayedRetryPolicyProvider() {
                    }

                    @Override
                    public RetryPolicy get() {
                        if (!ServiceConfigInterceptor.this.nameResolveComplete) {
                            return RetryPolicy.DEFAULT;
                        }
                        return ServiceConfigInterceptor.this.getRetryPolicyFromConfig(method);
                    }
                }
                callOptions = callOptions.withOption(RETRY_POLICY_KEY, new DelayedRetryPolicyProvider());
            }
        }
        if ((info2 = this.getMethodInfo(method)) == null) {
            return next2.newCall(method, callOptions);
        }
        if (info2.timeoutNanos != null) {
            Deadline newDeadline = Deadline.after(info2.timeoutNanos, TimeUnit.NANOSECONDS);
            Deadline existingDeadline = callOptions.getDeadline();
            if (existingDeadline == null || newDeadline.compareTo(existingDeadline) < 0) {
                callOptions = callOptions.withDeadline(newDeadline);
            }
        }
        if (info2.waitForReady != null) {
            CallOptions callOptions2 = callOptions = info2.waitForReady != false ? callOptions.withWaitForReady() : callOptions.withoutWaitForReady();
        }
        if (info2.maxInboundMessageSize != null) {
            existingLimit = callOptions.getMaxInboundMessageSize();
            callOptions = existingLimit != null ? callOptions.withMaxInboundMessageSize(Math.min(existingLimit, info2.maxInboundMessageSize)) : callOptions.withMaxInboundMessageSize(info2.maxInboundMessageSize);
        }
        if (info2.maxOutboundMessageSize != null) {
            existingLimit = callOptions.getMaxOutboundMessageSize();
            callOptions = existingLimit != null ? callOptions.withMaxOutboundMessageSize(Math.min(existingLimit, info2.maxOutboundMessageSize)) : callOptions.withMaxOutboundMessageSize(info2.maxOutboundMessageSize);
        }
        return next2.newCall(method, callOptions);
    }

    @CheckForNull
    private MethodInfo getMethodInfo(MethodDescriptor<?, ?> method) {
        Map<String, MethodInfo> localServiceMap;
        Map<String, MethodInfo> localServiceMethodMap = this.serviceMethodMap.get();
        MethodInfo info2 = null;
        if (localServiceMethodMap != null) {
            info2 = localServiceMethodMap.get(method.getFullMethodName());
        }
        if (info2 == null && (localServiceMap = this.serviceMap.get()) != null) {
            info2 = localServiceMap.get(MethodDescriptor.extractFullServiceName(method.getFullMethodName()));
        }
        return info2;
    }

    @VisibleForTesting
    RetryPolicy getRetryPolicyFromConfig(MethodDescriptor<?, ?> method) {
        MethodInfo info2 = this.getMethodInfo(method);
        if (info2 == null || info2.retryPolicy == null) {
            return RetryPolicy.DEFAULT;
        }
        return info2.retryPolicy;
    }

    static final class MethodInfo {
        final Long timeoutNanos;
        final Boolean waitForReady;
        final Integer maxInboundMessageSize;
        final Integer maxOutboundMessageSize;
        final RetryPolicy retryPolicy;

        MethodInfo(Map<String, Object> methodConfig, boolean retryEnabled, int maxRetryAttemptsLimit) {
            this.timeoutNanos = ServiceConfigUtil.getTimeoutFromMethodConfig(methodConfig);
            this.waitForReady = ServiceConfigUtil.getWaitForReadyFromMethodConfig(methodConfig);
            this.maxInboundMessageSize = ServiceConfigUtil.getMaxResponseMessageBytesFromMethodConfig(methodConfig);
            if (this.maxInboundMessageSize != null) {
                Preconditions.checkArgument(this.maxInboundMessageSize >= 0, "maxInboundMessageSize %s exceeds bounds", (Object)this.maxInboundMessageSize);
            }
            this.maxOutboundMessageSize = ServiceConfigUtil.getMaxRequestMessageBytesFromMethodConfig(methodConfig);
            if (this.maxOutboundMessageSize != null) {
                Preconditions.checkArgument(this.maxOutboundMessageSize >= 0, "maxOutboundMessageSize %s exceeds bounds", (Object)this.maxOutboundMessageSize);
            }
            Map<String, Object> policy = retryEnabled ? ServiceConfigUtil.getRetryPolicyFromMethodConfig(methodConfig) : null;
            this.retryPolicy = policy == null ? RetryPolicy.DEFAULT : MethodInfo.retryPolicy(policy, maxRetryAttemptsLimit);
        }

        public int hashCode() {
            return Objects.hashCode(this.timeoutNanos, this.waitForReady, this.maxInboundMessageSize, this.maxOutboundMessageSize, this.retryPolicy);
        }

        public boolean equals(Object other) {
            if (!(other instanceof MethodInfo)) {
                return false;
            }
            MethodInfo that = (MethodInfo)other;
            return Objects.equal(this.timeoutNanos, that.timeoutNanos) && Objects.equal(this.waitForReady, that.waitForReady) && Objects.equal(this.maxInboundMessageSize, that.maxInboundMessageSize) && Objects.equal(this.maxOutboundMessageSize, that.maxOutboundMessageSize) && Objects.equal(this.retryPolicy, that.retryPolicy);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("timeoutNanos", this.timeoutNanos).add("waitForReady", this.waitForReady).add("maxInboundMessageSize", this.maxInboundMessageSize).add("maxOutboundMessageSize", this.maxOutboundMessageSize).add("retryPolicy", this.retryPolicy).toString();
        }

        private static RetryPolicy retryPolicy(Map<String, Object> retryPolicy, int maxAttemptsLimit) {
            int maxAttempts = Preconditions.checkNotNull(ServiceConfigUtil.getMaxAttemptsFromRetryPolicy(retryPolicy), "maxAttempts cannot be empty");
            Preconditions.checkArgument(maxAttempts >= 2, "maxAttempts must be greater than 1: %s", maxAttempts);
            maxAttempts = Math.min(maxAttempts, maxAttemptsLimit);
            long initialBackoffNanos = Preconditions.checkNotNull(ServiceConfigUtil.getInitialBackoffNanosFromRetryPolicy(retryPolicy), "initialBackoff cannot be empty");
            Preconditions.checkArgument(initialBackoffNanos > 0L, "initialBackoffNanos must be greater than 0: %s", initialBackoffNanos);
            long maxBackoffNanos = Preconditions.checkNotNull(ServiceConfigUtil.getMaxBackoffNanosFromRetryPolicy(retryPolicy), "maxBackoff cannot be empty");
            Preconditions.checkArgument(maxBackoffNanos > 0L, "maxBackoff must be greater than 0: %s", maxBackoffNanos);
            double backoffMultiplier = Preconditions.checkNotNull(ServiceConfigUtil.getBackoffMultiplierFromRetryPolicy(retryPolicy), "backoffMultiplier cannot be empty");
            Preconditions.checkArgument(backoffMultiplier > 0.0, "backoffMultiplier must be greater than 0: %s", (Object)backoffMultiplier);
            List<String> rawCodes = ServiceConfigUtil.getRetryableStatusCodesFromRetryPolicy(retryPolicy);
            Preconditions.checkNotNull(rawCodes, "rawCodes must be present");
            Preconditions.checkArgument(!rawCodes.isEmpty(), "rawCodes can't be empty");
            EnumSet<Status.Code> codes = EnumSet.noneOf(Status.Code.class);
            for (String rawCode : rawCodes) {
                codes.add(Status.Code.valueOf(rawCode));
            }
            Set<Status.Code> retryableStatusCodes = Collections.unmodifiableSet(codes);
            return new RetryPolicy(maxAttempts, initialBackoffNanos, maxBackoffNanos, backoffMultiplier, retryableStatusCodes);
        }
    }
}

