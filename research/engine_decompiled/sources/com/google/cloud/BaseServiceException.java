/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.InternalApi;
import com.google.cloud.RetryHelper;
import com.google.common.base.MoreObjects;
import java.io.IOException;
import java.io.Serializable;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import javax.net.ssl.SSLHandshakeException;

public class BaseServiceException
extends RuntimeException {
    private static final long serialVersionUID = 759921776378760835L;
    public static final int UNKNOWN_CODE = 0;
    private final int code;
    private final boolean retryable;
    private final String reason;
    private final String location;
    private final String debugInfo;

    @InternalApi
    public static boolean isRetryable(Integer code, String reason, boolean idempotent, Set<Error> retryableErrors) {
        for (Error retryableError : retryableErrors) {
            if (retryableError.getCode() != null && !retryableError.getCode().equals(code) || retryableError.getReason() != null && !retryableError.getReason().equals(reason)) continue;
            return idempotent || retryableError.isRejected();
        }
        return false;
    }

    @InternalApi
    public static boolean isRetryable(boolean idempotent, IOException exception) {
        boolean exceptionIsRetryable = exception instanceof SocketTimeoutException || exception instanceof SocketException || exception instanceof SSLHandshakeException && !(exception.getCause() instanceof CertificateException) || "insufficient data written".equals(exception.getMessage()) || "Error writing request body to server".equals(exception.getMessage());
        return idempotent && exceptionIsRetryable;
    }

    @InternalApi
    public static void translate(RetryHelper.RetryHelperException ex) {
        if (ex.getCause() instanceof BaseServiceException) {
            throw (BaseServiceException)ex.getCause();
        }
    }

    @InternalApi
    public static void translate(ExecutionException ex) {
        if (ex.getCause() instanceof BaseServiceException) {
            throw (BaseServiceException)ex.getCause();
        }
    }

    @InternalApi(value="This class should only be extended within google-cloud-java")
    protected BaseServiceException(ExceptionData exceptionData) {
        super(exceptionData.getMessage(), exceptionData.getCause());
        this.code = exceptionData.getCode();
        this.reason = exceptionData.getReason();
        this.retryable = exceptionData.isRetryable();
        this.location = exceptionData.getLocation();
        this.debugInfo = exceptionData.getDebugInfo();
    }

    public int getCode() {
        return this.code;
    }

    public String getReason() {
        return this.reason;
    }

    public boolean isRetryable() {
        return this.retryable;
    }

    public String getLocation() {
        return this.location;
    }

    @InternalApi
    public String getDebugInfo() {
        return this.debugInfo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BaseServiceException)) {
            return false;
        }
        BaseServiceException other = (BaseServiceException)obj;
        return Objects.equals(this.getCause(), other.getCause()) && Objects.equals(this.getMessage(), other.getMessage()) && this.code == other.code && this.retryable == other.retryable && Objects.equals(this.reason, other.reason) && Objects.equals(this.location, other.location) && Objects.equals(this.debugInfo, other.debugInfo);
    }

    public int hashCode() {
        return Objects.hash(this.getCause(), this.getMessage(), this.code, this.retryable, this.reason, this.location, this.debugInfo);
    }

    @InternalApi
    public static final class Error
    implements Serializable {
        private static final long serialVersionUID = -4019600198652965721L;
        private final Integer code;
        private final String reason;
        private final boolean rejected;

        public Error(Integer code, String reason) {
            this(code, reason, false);
        }

        public Error(Integer code, String reason, boolean rejected) {
            this.code = code;
            this.reason = reason;
            this.rejected = rejected;
        }

        public Integer getCode() {
            return this.code;
        }

        public boolean isRejected() {
            return this.rejected;
        }

        public String getReason() {
            return this.reason;
        }

        @InternalApi
        public boolean isRetryable(boolean idempotent, Set<Error> retryableErrors) {
            return BaseServiceException.isRetryable(this.code, this.reason, idempotent, retryableErrors);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("code", this.code).add("reason", this.reason).add("rejected", this.rejected).toString();
        }

        public int hashCode() {
            return Objects.hash(this.code, this.reason, this.rejected);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Error)) {
                return false;
            }
            Error o = (Error)obj;
            return Objects.equals(this.code, o.code) && Objects.equals(this.reason, o.reason) && Objects.equals(this.rejected, o.rejected);
        }
    }

    @InternalApi
    public static final class ExceptionData
    implements Serializable {
        private static final long serialVersionUID = 2222230861338426753L;
        private final String message;
        private final Throwable cause;
        private final int code;
        private final boolean retryable;
        private final String reason;
        private final String location;
        private final String debugInfo;

        private ExceptionData(String message, Throwable cause, int code, boolean retryable, String reason, String location, String debugInfo) {
            this.message = message;
            this.cause = cause;
            this.code = code;
            this.retryable = retryable;
            this.reason = reason;
            this.location = location;
            this.debugInfo = debugInfo;
        }

        public String getMessage() {
            return this.message;
        }

        public Throwable getCause() {
            return this.cause;
        }

        public int getCode() {
            return this.code;
        }

        public boolean isRetryable() {
            return this.retryable;
        }

        public String getReason() {
            return this.reason;
        }

        public String getLocation() {
            return this.location;
        }

        public String getDebugInfo() {
            return this.debugInfo;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static ExceptionData from(int code, String message, String reason, boolean retryable) {
            return ExceptionData.from(code, message, reason, retryable, null);
        }

        public static ExceptionData from(int code, String message, String reason, boolean retryable, Throwable cause) {
            return ExceptionData.newBuilder().setCode(code).setMessage(message).setReason(reason).setRetryable(retryable).setCause(cause).build();
        }

        @InternalApi
        public static final class Builder {
            private String message;
            private Throwable cause;
            private int code;
            private boolean retryable;
            private String reason;
            private String location;
            private String debugInfo;

            private Builder() {
            }

            public Builder setMessage(String message) {
                this.message = message;
                return this;
            }

            public Builder setCause(Throwable cause) {
                this.cause = cause;
                return this;
            }

            public Builder setCode(int code) {
                this.code = code;
                return this;
            }

            public Builder setRetryable(boolean retryable) {
                this.retryable = retryable;
                return this;
            }

            public Builder setReason(String reason) {
                this.reason = reason;
                return this;
            }

            public Builder setLocation(String location) {
                this.location = location;
                return this;
            }

            public Builder setDebugInfo(String debugInfo) {
                this.debugInfo = debugInfo;
                return this;
            }

            public ExceptionData build() {
                return new ExceptionData(this.message, this.cause, this.code, this.retryable, this.reason, this.location, this.debugInfo);
            }
        }
    }
}

