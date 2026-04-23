/*
 * Decompiled with CFR 0.152.
 */
package towersocket;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import java.util.function.Consumer;
import javax.net.ssl.SSLContext;
import towersocket.ClientConnectionBuilder;
import towersocket.Greeter;

public interface TowerSocket {
    public void openPort(int var1, Greeter var2, Optional<SSLContext> var3) throws IOException;

    default public void openPort(int port, Greeter greeter) throws IOException {
        this.openPort(port, greeter, Optional.empty());
    }

    public void connect(URI var1, Object var2, Consumer<ClientConnectionBuilder> var3, Optional<SSLContext> var4);

    default public void connect(URI address, Object handshakeMessage, Consumer<ClientConnectionBuilder> onSuccess2) {
        this.connect(address, handshakeMessage, onSuccess2, Optional.empty());
    }
}

