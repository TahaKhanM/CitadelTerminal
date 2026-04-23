/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class DemuxInputStream
extends InputStream {
    private final InheritableThreadLocal<InputStream> m_streams = new InheritableThreadLocal();

    public InputStream bindStream(InputStream input2) {
        InputStream oldValue = (InputStream)this.m_streams.get();
        this.m_streams.set(input2);
        return oldValue;
    }

    @Override
    public void close() throws IOException {
        InputStream input2 = (InputStream)this.m_streams.get();
        if (null != input2) {
            input2.close();
        }
    }

    @Override
    public int read() throws IOException {
        InputStream input2 = (InputStream)this.m_streams.get();
        if (null != input2) {
            return input2.read();
        }
        return -1;
    }
}

