/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.BetaApi;
import com.google.api.gax.retrying.RetrySettings;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import org.threeten.bp.Duration;

@BetaApi
public class RetryOption
implements Serializable {
    private static final long serialVersionUID = 3622837212525370224L;
    private final OptionType type;
    private final Object value;

    private RetryOption(OptionType type, Object value) {
        this.type = Preconditions.checkNotNull(type);
        this.value = Preconditions.checkNotNull(value);
    }

    public static RetryOption totalTimeout(Duration totalTimeout) {
        return new RetryOption(OptionType.TOTAL_TIMEOUT, totalTimeout);
    }

    public static RetryOption initialRetryDelay(Duration initialRetryDelay) {
        return new RetryOption(OptionType.INITIAL_RETRY_DELAY, initialRetryDelay);
    }

    public static RetryOption retryDelayMultiplier(double retryDelayMultiplier) {
        return new RetryOption(OptionType.RETRY_DELAY_MULTIPLIER, retryDelayMultiplier);
    }

    public static RetryOption maxRetryDelay(Duration maxRetryDelay) {
        return new RetryOption(OptionType.MAX_RETRY_DELAY, maxRetryDelay);
    }

    public static RetryOption maxAttempts(int maxAttempts) {
        return new RetryOption(OptionType.MAX_ATTEMPTS, maxAttempts);
    }

    public static RetryOption jittered(boolean jittered) {
        return new RetryOption(OptionType.JITTERED, jittered);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        RetryOption that = (RetryOption)o;
        if (this.type != that.type) {
            return false;
        }
        return this.value.equals(that.value);
    }

    public int hashCode() {
        int result2 = this.type.hashCode();
        result2 = 31 * result2 + this.value.hashCode();
        return result2;
    }

    public static RetrySettings mergeToSettings(RetrySettings settings, RetryOption ... options) {
        if (options.length <= 0) {
            return settings;
        }
        RetrySettings.Builder builder = settings.toBuilder();
        block8: for (RetryOption option : options) {
            switch (option.type) {
                case TOTAL_TIMEOUT: {
                    builder.setTotalTimeout((Duration)option.value);
                    continue block8;
                }
                case INITIAL_RETRY_DELAY: {
                    builder.setInitialRetryDelay((Duration)option.value);
                    continue block8;
                }
                case RETRY_DELAY_MULTIPLIER: {
                    builder.setRetryDelayMultiplier((Double)option.value);
                    continue block8;
                }
                case MAX_RETRY_DELAY: {
                    builder.setMaxRetryDelay((Duration)option.value);
                    continue block8;
                }
                case MAX_ATTEMPTS: {
                    builder.setMaxAttempts((Integer)option.value);
                    continue block8;
                }
                case JITTERED: {
                    builder.setJittered((Boolean)option.value);
                    continue block8;
                }
                default: {
                    throw new IllegalArgumentException("Unknown option type: " + (Object)((Object)option.type));
                }
            }
        }
        return builder.build();
    }

    private static enum OptionType {
        TOTAL_TIMEOUT,
        INITIAL_RETRY_DELAY,
        RETRY_DELAY_MULTIPLIER,
        MAX_RETRY_DELAY,
        MAX_ATTEMPTS,
        JITTERED;

    }
}

