/*
 * Decompiled with CFR 0.152.
 */
package towersocket.impl;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import java.util.function.Consumer;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import org.java_websocket.server.DefaultSSLWebSocketServerFactory;
import towersocket.ClientConnectionBuilder;
import towersocket.Greeter;
import towersocket.Serializer;
import towersocket.TowerSocket;
import towersocket.impl.client.TowerWebSocketClient;
import towersocket.impl.server.TowerWebSocketServer;
import towersocket.util.WorkerThreads;

public class TowerSocketImpl
implements TowerSocket {
    private final WorkerThreads<Void> ioPool = new WorkerThreads<Void>("Server io pool", Runtime.getRuntime().availableProcessors(), null, nil -> true);
    private final Serializer serializer;

    public TowerSocketImpl(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public void openPort(int port, Greeter greeter, Optional<SSLContext> secure) throws IOException {
        TowerWebSocketServer wsserver = new TowerWebSocketServer(this.ioPool, this.serializer, port, greeter);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                wsserver.stop();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }));
        secure.ifPresent(context -> wsserver.setWebSocketFactory(new DefaultSSLWebSocketServerFactory((SSLContext)context)));
        wsserver.start();
    }

    @Override
    public void connect(URI address, Object handshakeMessage, Consumer<ClientConnectionBuilder> onSuccess2, Optional<SSLContext> secure) {
        TowerWebSocketClient wsclient = new TowerWebSocketClient(this.ioPool, this.serializer, address, handshakeMessage, onSuccess2);
        secure.ifPresent(sslContext -> {
            try {
                SSLSocketFactory factory = SSLContext.getDefault().getSocketFactory();
                wsclient.setSocket(factory.createSocket());
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        wsclient.connect();
    }
}

