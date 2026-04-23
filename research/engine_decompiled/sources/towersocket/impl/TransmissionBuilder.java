/*
 * Decompiled with CFR 0.152.
 */
package towersocket.impl;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.java_websocket.WebSocket;
import towersocket.Constants;
import towersocket.exception.InternalException;
import towersocket.util.WorkerThreads;

public class TransmissionBuilder
extends DataOutputStream {
    public TransmissionBuilder(int header) {
        super(new ByteArrayOutputStream());
        try {
            this.write(header);
        }
        catch (IOException e) {
            throw new InternalException("Unexpected IO exception", e);
        }
    }

    public void debug() {
        if (Constants.DEBUG) {
            try {
                System.out.println(">>> " + new String(((ByteArrayOutputStream)this.out).toByteArray(), "UTF-8"));
            }
            catch (UnsupportedEncodingException e) {
                throw new InternalException("Unexpected exception", e);
            }
        }
    }

    public void send(WebSocket wsocket, WorkerThreads<?> exec2) throws InternalException {
        String text;
        byte[] bytes2 = ((ByteArrayOutputStream)this.out).toByteArray();
        try {
            text = new String(bytes2, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            throw new InternalException("Platform does not support UTF-8", e);
        }
        wsocket.send(text);
    }
}

