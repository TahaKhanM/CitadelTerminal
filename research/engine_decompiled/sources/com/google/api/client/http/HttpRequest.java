/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http;

import com.google.api.client.http.BackOffPolicy;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpEncoding;
import com.google.api.client.http.HttpEncodingStreamingContent;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpIOExceptionHandler;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpResponseInterceptor;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.util.Beta;
import com.google.api.client.util.IOUtils;
import com.google.api.client.util.LoggingStreamingContent;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import com.google.api.client.util.StreamingContent;
import com.google.api.client.util.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class HttpRequest {
    public static final String VERSION = "1.24.1";
    public static final String USER_AGENT_SUFFIX = "Google-HTTP-Java-Client/1.24.1 (gzip)";
    public static final int DEFAULT_NUMBER_OF_RETRIES = 10;
    private HttpExecuteInterceptor executeInterceptor;
    private HttpHeaders headers = new HttpHeaders();
    private HttpHeaders responseHeaders = new HttpHeaders();
    private int numRetries = 10;
    private int contentLoggingLimit = 16384;
    private boolean loggingEnabled = true;
    private boolean curlLoggingEnabled = true;
    private HttpContent content;
    private final HttpTransport transport;
    private String requestMethod;
    private GenericUrl url;
    private int connectTimeout = 20000;
    private int readTimeout = 20000;
    private HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler;
    @Beta
    private HttpIOExceptionHandler ioExceptionHandler;
    private HttpResponseInterceptor responseInterceptor;
    private ObjectParser objectParser;
    private HttpEncoding encoding;
    @Deprecated
    @Beta
    private BackOffPolicy backOffPolicy;
    private boolean followRedirects = true;
    private boolean throwExceptionOnExecuteError = true;
    @Deprecated
    @Beta
    private boolean retryOnExecuteIOException = false;
    private boolean suppressUserAgentSuffix;
    private Sleeper sleeper = Sleeper.DEFAULT;

    HttpRequest(HttpTransport transport, String requestMethod) {
        this.transport = transport;
        this.setRequestMethod(requestMethod);
    }

    public HttpTransport getTransport() {
        return this.transport;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public HttpRequest setRequestMethod(String requestMethod) {
        Preconditions.checkArgument(requestMethod == null || HttpMediaType.matchesToken(requestMethod));
        this.requestMethod = requestMethod;
        return this;
    }

    public GenericUrl getUrl() {
        return this.url;
    }

    public HttpRequest setUrl(GenericUrl url) {
        this.url = Preconditions.checkNotNull(url);
        return this;
    }

    public HttpContent getContent() {
        return this.content;
    }

    public HttpRequest setContent(HttpContent content) {
        this.content = content;
        return this;
    }

    public HttpEncoding getEncoding() {
        return this.encoding;
    }

    public HttpRequest setEncoding(HttpEncoding encoding) {
        this.encoding = encoding;
        return this;
    }

    @Deprecated
    @Beta
    public BackOffPolicy getBackOffPolicy() {
        return this.backOffPolicy;
    }

    @Deprecated
    @Beta
    public HttpRequest setBackOffPolicy(BackOffPolicy backOffPolicy) {
        this.backOffPolicy = backOffPolicy;
        return this;
    }

    public int getContentLoggingLimit() {
        return this.contentLoggingLimit;
    }

    public HttpRequest setContentLoggingLimit(int contentLoggingLimit) {
        Preconditions.checkArgument(contentLoggingLimit >= 0, "The content logging limit must be non-negative.");
        this.contentLoggingLimit = contentLoggingLimit;
        return this;
    }

    public boolean isLoggingEnabled() {
        return this.loggingEnabled;
    }

    public HttpRequest setLoggingEnabled(boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
        return this;
    }

    public boolean isCurlLoggingEnabled() {
        return this.curlLoggingEnabled;
    }

    public HttpRequest setCurlLoggingEnabled(boolean curlLoggingEnabled) {
        this.curlLoggingEnabled = curlLoggingEnabled;
        return this;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public HttpRequest setConnectTimeout(int connectTimeout) {
        Preconditions.checkArgument(connectTimeout >= 0);
        this.connectTimeout = connectTimeout;
        return this;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public HttpRequest setReadTimeout(int readTimeout) {
        Preconditions.checkArgument(readTimeout >= 0);
        this.readTimeout = readTimeout;
        return this;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    public HttpRequest setHeaders(HttpHeaders headers) {
        this.headers = Preconditions.checkNotNull(headers);
        return this;
    }

    public HttpHeaders getResponseHeaders() {
        return this.responseHeaders;
    }

    public HttpRequest setResponseHeaders(HttpHeaders responseHeaders) {
        this.responseHeaders = Preconditions.checkNotNull(responseHeaders);
        return this;
    }

    public HttpExecuteInterceptor getInterceptor() {
        return this.executeInterceptor;
    }

    public HttpRequest setInterceptor(HttpExecuteInterceptor interceptor) {
        this.executeInterceptor = interceptor;
        return this;
    }

    public HttpUnsuccessfulResponseHandler getUnsuccessfulResponseHandler() {
        return this.unsuccessfulResponseHandler;
    }

    public HttpRequest setUnsuccessfulResponseHandler(HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler) {
        this.unsuccessfulResponseHandler = unsuccessfulResponseHandler;
        return this;
    }

    @Beta
    public HttpIOExceptionHandler getIOExceptionHandler() {
        return this.ioExceptionHandler;
    }

    @Beta
    public HttpRequest setIOExceptionHandler(HttpIOExceptionHandler ioExceptionHandler) {
        this.ioExceptionHandler = ioExceptionHandler;
        return this;
    }

    public HttpResponseInterceptor getResponseInterceptor() {
        return this.responseInterceptor;
    }

    public HttpRequest setResponseInterceptor(HttpResponseInterceptor responseInterceptor) {
        this.responseInterceptor = responseInterceptor;
        return this;
    }

    public int getNumberOfRetries() {
        return this.numRetries;
    }

    public HttpRequest setNumberOfRetries(int numRetries) {
        Preconditions.checkArgument(numRetries >= 0);
        this.numRetries = numRetries;
        return this;
    }

    public HttpRequest setParser(ObjectParser parser) {
        this.objectParser = parser;
        return this;
    }

    public final ObjectParser getParser() {
        return this.objectParser;
    }

    public boolean getFollowRedirects() {
        return this.followRedirects;
    }

    public HttpRequest setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
        return this;
    }

    public boolean getThrowExceptionOnExecuteError() {
        return this.throwExceptionOnExecuteError;
    }

    public HttpRequest setThrowExceptionOnExecuteError(boolean throwExceptionOnExecuteError) {
        this.throwExceptionOnExecuteError = throwExceptionOnExecuteError;
        return this;
    }

    @Deprecated
    @Beta
    public boolean getRetryOnExecuteIOException() {
        return this.retryOnExecuteIOException;
    }

    @Deprecated
    @Beta
    public HttpRequest setRetryOnExecuteIOException(boolean retryOnExecuteIOException) {
        this.retryOnExecuteIOException = retryOnExecuteIOException;
        return this;
    }

    public boolean getSuppressUserAgentSuffix() {
        return this.suppressUserAgentSuffix;
    }

    public HttpRequest setSuppressUserAgentSuffix(boolean suppressUserAgentSuffix) {
        this.suppressUserAgentSuffix = suppressUserAgentSuffix;
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public HttpResponse execute() throws IOException {
        IOException executeException;
        boolean retryRequest = false;
        Preconditions.checkArgument(this.numRetries >= 0);
        int retriesRemaining = this.numRetries;
        if (this.backOffPolicy != null) {
            this.backOffPolicy.reset();
        }
        HttpResponse response = null;
        Preconditions.checkNotNull(this.requestMethod);
        Preconditions.checkNotNull(this.url);
        do {
            block51: {
                StreamingContent streamingContent;
                boolean contentRetrySupported;
                if (response != null) {
                    response.ignore();
                }
                response = null;
                executeException = null;
                if (this.executeInterceptor != null) {
                    this.executeInterceptor.intercept(this);
                }
                String urlString = this.url.build();
                LowLevelHttpRequest lowLevelHttpRequest = this.transport.buildRequest(this.requestMethod, urlString);
                Logger logger = HttpTransport.LOGGER;
                boolean loggable = this.loggingEnabled && logger.isLoggable(Level.CONFIG);
                StringBuilder logbuf = null;
                StringBuilder curlbuf = null;
                if (loggable) {
                    logbuf = new StringBuilder();
                    logbuf.append("-------------- REQUEST  --------------").append(StringUtils.LINE_SEPARATOR);
                    logbuf.append(this.requestMethod).append(' ').append(urlString).append(StringUtils.LINE_SEPARATOR);
                    if (this.curlLoggingEnabled) {
                        curlbuf = new StringBuilder("curl -v --compressed");
                        if (!this.requestMethod.equals("GET")) {
                            curlbuf.append(" -X ").append(this.requestMethod);
                        }
                    }
                }
                String originalUserAgent = this.headers.getUserAgent();
                if (!this.suppressUserAgentSuffix) {
                    if (originalUserAgent == null) {
                        this.headers.setUserAgent(USER_AGENT_SUFFIX);
                    } else {
                        this.headers.setUserAgent(originalUserAgent + " " + USER_AGENT_SUFFIX);
                    }
                }
                HttpHeaders.serializeHeaders(this.headers, logbuf, curlbuf, logger, lowLevelHttpRequest);
                if (!this.suppressUserAgentSuffix) {
                    this.headers.setUserAgent(originalUserAgent);
                }
                boolean bl = contentRetrySupported = (streamingContent = this.content) == null || this.content.retrySupported();
                if (streamingContent != null) {
                    long contentLength;
                    String contentEncoding;
                    String contentType = this.content.getType();
                    if (loggable) {
                        streamingContent = new LoggingStreamingContent(streamingContent, HttpTransport.LOGGER, Level.CONFIG, this.contentLoggingLimit);
                    }
                    if (this.encoding == null) {
                        contentEncoding = null;
                        contentLength = this.content.getLength();
                    } else {
                        contentEncoding = this.encoding.getName();
                        streamingContent = new HttpEncodingStreamingContent(streamingContent, this.encoding);
                        long l = contentLength = contentRetrySupported ? IOUtils.computeLength(streamingContent) : -1L;
                    }
                    if (loggable) {
                        String header;
                        if (contentType != null) {
                            header = "Content-Type: " + contentType;
                            logbuf.append(header).append(StringUtils.LINE_SEPARATOR);
                            if (curlbuf != null) {
                                curlbuf.append(" -H '" + header + "'");
                            }
                        }
                        if (contentEncoding != null) {
                            header = "Content-Encoding: " + contentEncoding;
                            logbuf.append(header).append(StringUtils.LINE_SEPARATOR);
                            if (curlbuf != null) {
                                curlbuf.append(" -H '" + header + "'");
                            }
                        }
                        if (contentLength >= 0L) {
                            header = "Content-Length: " + contentLength;
                            logbuf.append(header).append(StringUtils.LINE_SEPARATOR);
                        }
                    }
                    if (curlbuf != null) {
                        curlbuf.append(" -d '@-'");
                    }
                    lowLevelHttpRequest.setContentType(contentType);
                    lowLevelHttpRequest.setContentEncoding(contentEncoding);
                    lowLevelHttpRequest.setContentLength(contentLength);
                    lowLevelHttpRequest.setStreamingContent(streamingContent);
                }
                if (loggable) {
                    logger.config(logbuf.toString());
                    if (curlbuf != null) {
                        curlbuf.append(" -- '");
                        curlbuf.append(urlString.replaceAll("'", "'\"'\"'"));
                        curlbuf.append("'");
                        if (streamingContent != null) {
                            curlbuf.append(" << $$$");
                        }
                        logger.config(curlbuf.toString());
                    }
                }
                retryRequest = contentRetrySupported && retriesRemaining > 0;
                lowLevelHttpRequest.setTimeout(this.connectTimeout, this.readTimeout);
                try {
                    LowLevelHttpResponse lowLevelHttpResponse = lowLevelHttpRequest.execute();
                    boolean responseConstructed = false;
                    try {
                        response = new HttpResponse(this, lowLevelHttpResponse);
                        responseConstructed = true;
                    }
                    finally {
                        InputStream lowLevelContent;
                        if (!responseConstructed && (lowLevelContent = lowLevelHttpResponse.getContent()) != null) {
                            lowLevelContent.close();
                        }
                    }
                }
                catch (IOException e) {
                    if (!(this.retryOnExecuteIOException || this.ioExceptionHandler != null && this.ioExceptionHandler.handleIOException(this, retryRequest))) {
                        throw e;
                    }
                    executeException = e;
                    if (!loggable) break block51;
                    logger.log(Level.WARNING, "exception thrown while executing request", e);
                }
            }
            boolean responseProcessed = false;
            try {
                if (response != null && !response.isSuccessStatusCode()) {
                    boolean errorHandled = false;
                    if (this.unsuccessfulResponseHandler != null) {
                        errorHandled = this.unsuccessfulResponseHandler.handleResponse(this, response, retryRequest);
                    }
                    if (!errorHandled) {
                        long backOffTime;
                        if (this.handleRedirect(response.getStatusCode(), response.getHeaders())) {
                            errorHandled = true;
                        } else if (retryRequest && this.backOffPolicy != null && this.backOffPolicy.isBackOffRequired(response.getStatusCode()) && (backOffTime = this.backOffPolicy.getNextBackOffMillis()) != -1L) {
                            try {
                                this.sleeper.sleep(backOffTime);
                            }
                            catch (InterruptedException interruptedException) {
                                // empty catch block
                            }
                            errorHandled = true;
                        }
                    }
                    if (retryRequest &= errorHandled) {
                        response.ignore();
                    }
                } else {
                    retryRequest &= response == null;
                }
                --retriesRemaining;
                responseProcessed = true;
            }
            finally {
                if (response != null && !responseProcessed) {
                    response.disconnect();
                }
            }
        } while (retryRequest);
        if (response == null) {
            throw executeException;
        }
        if (this.responseInterceptor != null) {
            this.responseInterceptor.interceptResponse(response);
        }
        if (this.throwExceptionOnExecuteError && !response.isSuccessStatusCode()) {
            try {
                throw new HttpResponseException(response);
            }
            catch (Throwable throwable) {
                response.disconnect();
                throw throwable;
            }
        }
        return response;
    }

    @Beta
    public Future<HttpResponse> executeAsync(Executor executor) {
        FutureTask<HttpResponse> future = new FutureTask<HttpResponse>(new Callable<HttpResponse>(){

            @Override
            public HttpResponse call() throws Exception {
                return HttpRequest.this.execute();
            }
        });
        executor.execute(future);
        return future;
    }

    @Beta
    public Future<HttpResponse> executeAsync() {
        return this.executeAsync(Executors.newSingleThreadExecutor());
    }

    public boolean handleRedirect(int statusCode, HttpHeaders responseHeaders) {
        String redirectLocation = responseHeaders.getLocation();
        if (this.getFollowRedirects() && HttpStatusCodes.isRedirect(statusCode) && redirectLocation != null) {
            this.setUrl(new GenericUrl(this.url.toURL(redirectLocation)));
            if (statusCode == 303) {
                this.setRequestMethod("GET");
                this.setContent(null);
            }
            this.headers.setAuthorization((String)null);
            this.headers.setIfMatch(null);
            this.headers.setIfNoneMatch(null);
            this.headers.setIfModifiedSince(null);
            this.headers.setIfUnmodifiedSince(null);
            this.headers.setIfRange(null);
            return true;
        }
        return false;
    }

    public Sleeper getSleeper() {
        return this.sleeper;
    }

    public HttpRequest setSleeper(Sleeper sleeper) {
        this.sleeper = Preconditions.checkNotNull(sleeper);
        return this;
    }
}

