/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.testing.util;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Beta
public class LogRecordingHandler
extends Handler {
    private final List<LogRecord> records = Lists.newArrayList();

    @Override
    public void publish(LogRecord record) {
        this.records.add(record);
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }

    public List<String> messages() {
        ArrayList<String> result2 = Lists.newArrayList();
        for (LogRecord record : this.records) {
            result2.add(record.getMessage());
        }
        return result2;
    }
}

