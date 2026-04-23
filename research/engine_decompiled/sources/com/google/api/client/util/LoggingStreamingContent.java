/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.LoggingOutputStream;
import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LoggingStreamingContent
implements StreamingContent {
    private final StreamingContent content;
    private final int contentLoggingLimit;
    private final Level loggingLevel;
    private final Logger logger;

    public LoggingStreamingContent(StreamingContent content, Logger logger, Level loggingLevel, int contentLoggingLimit) {
        this.content = content;
        this.logger = logger;
        this.loggingLevel = loggingLevel;
        this.contentLoggingLimit = contentLoggingLimit;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void writeTo(OutputStream out) throws IOException {
        LoggingOutputStream loggableOutputStream = new LoggingOutputStream(out, this.logger, this.loggingLevel, this.contentLoggingLimit);
        try {
            this.content.writeTo(loggableOutputStream);
        }
        finally {
            loggableOutputStream.getLogStream().close();
        }
        out.flush();
    }
}

