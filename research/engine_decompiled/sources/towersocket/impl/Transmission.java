/*
 * Decompiled with CFR 0.152.
 */
package towersocket.impl;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import towersocket.Constants;
import towersocket.exception.InternalException;
import towersocket.exception.ProtocolFailure;

public class Transmission {
    public final int header;
    public final byte[] body;

    private Transmission(int header, byte[] body2) {
        this.header = header;
        this.body = body2;
    }

    public static Transmission parse(byte[] message) throws ProtocolFailure, InternalException {
        if (message.length == 0) {
            throw new ProtocolFailure("transmission of length 0");
        }
        DataInputStream data = new DataInputStream(new ByteArrayInputStream(message));
        try {
            int header = data.read();
            switch (header) {
                case 97: 
                case 103: 
                case 109: 
                case 110: 
                case 112: 
                case 114: {
                    break;
                }
                default: {
                    throw new ProtocolFailure("Invalid message header: " + header);
                }
            }
            return new Transmission(header, data.readAllBytes());
        }
        catch (IOException e) {
            throw new InternalException("unexpected IO exception", e);
        }
    }

    public void debug() {
        if (Constants.DEBUG) {
            System.out.println("<<< " + (char)this.header + " : " + new String(this.body));
        }
    }

    public DataInputStream asStream() {
        return new DataInputStream(new ByteArrayInputStream(this.body));
    }
}

