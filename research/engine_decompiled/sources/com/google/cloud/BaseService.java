/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.InternalApi;
import com.google.cloud.BaseServiceException;
import com.google.cloud.ExceptionHandler;
import com.google.cloud.Service;
import com.google.cloud.ServiceOptions;

public abstract class BaseService<OptionsT extends ServiceOptions<?, OptionsT>>
implements Service<OptionsT> {
    public static final ExceptionHandler.Interceptor EXCEPTION_HANDLER_INTERCEPTOR = new ExceptionHandler.Interceptor(){
        private static final long serialVersionUID = -8429573486870467828L;

        @Override
        public ExceptionHandler.Interceptor.RetryResult afterEval(Exception exception, ExceptionHandler.Interceptor.RetryResult retryResult) {
            return ExceptionHandler.Interceptor.RetryResult.CONTINUE_EVALUATION;
        }

        @Override
        public ExceptionHandler.Interceptor.RetryResult beforeEval(Exception exception) {
            if (exception instanceof BaseServiceException) {
                boolean retriable = ((BaseServiceException)exception).isRetryable();
                return retriable ? ExceptionHandler.Interceptor.RetryResult.RETRY : ExceptionHandler.Interceptor.RetryResult.CONTINUE_EVALUATION;
            }
            return ExceptionHandler.Interceptor.RetryResult.CONTINUE_EVALUATION;
        }
    };
    public static final ExceptionHandler EXCEPTION_HANDLER = ExceptionHandler.newBuilder().abortOn(RuntimeException.class).addInterceptors(EXCEPTION_HANDLER_INTERCEPTOR).build();
    private final OptionsT options;

    @InternalApi(value="This class should only be extended within google-cloud-java")
    protected BaseService(OptionsT options) {
        this.options = options;
    }

    @Override
    public OptionsT getOptions() {
        return this.options;
    }
}

