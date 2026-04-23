/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http;

import com.google.api.client.http.HttpEncoding;
import com.google.api.client.util.StreamingContent;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GZipEncoding
implements HttpEncoding {
    public String getName() {
        return "gzip";
    }

    public void encode(StreamingContent content, OutputStream out) throws IOException {
        BufferedOutputStream out2 = new BufferedOutputStream(out){

            public void close() throws IOException {
                try {
                    this.flush();
                }
                catch (IOException iOException) {
                    // empty catch block
                }
            }
        };
        GZIPOutputStream zipper = new GZIPOutputStream(out2);
        content.writeTo(zipper);
        zipper.close();
    }
}

