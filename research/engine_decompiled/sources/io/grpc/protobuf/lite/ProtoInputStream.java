/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.protobuf.lite;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import io.grpc.Drainable;
import io.grpc.KnownLength;
import io.grpc.protobuf.lite.ProtoLiteUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nullable;

class ProtoInputStream
extends InputStream
implements Drainable,
KnownLength {
    @Nullable
    private MessageLite message;
    private final Parser<?> parser;
    @Nullable
    private ByteArrayInputStream partial;

    public ProtoInputStream(MessageLite message, Parser<?> parser) {
        this.message = message;
        this.parser = parser;
    }

    @Override
    public int drainTo(OutputStream target) throws IOException {
        int written;
        if (this.message != null) {
            written = this.message.getSerializedSize();
            this.message.writeTo(target);
            this.message = null;
        } else if (this.partial != null) {
            written = (int)ProtoLiteUtils.copy(this.partial, target);
            this.partial = null;
        } else {
            written = 0;
        }
        return written;
    }

    @Override
    public int read() throws IOException {
        if (this.message != null) {
            this.partial = new ByteArrayInputStream(this.message.toByteArray());
            this.message = null;
        }
        if (this.partial != null) {
            return this.partial.read();
        }
        return -1;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.message != null) {
            int size2 = this.message.getSerializedSize();
            if (size2 == 0) {
                this.message = null;
                this.partial = null;
                return -1;
            }
            if (len >= size2) {
                CodedOutputStream stream = CodedOutputStream.newInstance(b, off, size2);
                this.message.writeTo(stream);
                stream.flush();
                stream.checkNoSpaceLeft();
                this.message = null;
                this.partial = null;
                return size2;
            }
            this.partial = new ByteArrayInputStream(this.message.toByteArray());
            this.message = null;
        }
        if (this.partial != null) {
            return this.partial.read(b, off, len);
        }
        return -1;
    }

    @Override
    public int available() throws IOException {
        if (this.message != null) {
            return this.message.getSerializedSize();
        }
        if (this.partial != null) {
            return this.partial.available();
        }
        return 0;
    }

    MessageLite message() {
        if (this.message == null) {
            throw new IllegalStateException("message not available");
        }
        return this.message;
    }

    Parser<?> parser() {
        return this.parser;
    }
}

