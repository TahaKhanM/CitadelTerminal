/*
 * Decompiled with CFR 0.152.
 */
package towersocket.serializers;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import towersocket.Serializer;
import towersocket.exception.ProtocolFailure;

public class SimpleJsonSerializer
implements Serializer {
    @Override
    public byte[] ser(Object obj) throws ProtocolFailure {
        try {
            return Jsoner.serialize(obj).getBytes(StandardCharsets.UTF_8);
        }
        catch (IllegalArgumentException e) {
            throw new ProtocolFailure("Ser fail", e);
        }
    }

    @Override
    public Object des(byte[] bin) throws ProtocolFailure {
        try {
            return Jsoner.deserialize(new String(bin, "UTF-8"));
        }
        catch (JsonException | IOException e) {
            throw new ProtocolFailure("Des fail", e);
        }
    }
}

