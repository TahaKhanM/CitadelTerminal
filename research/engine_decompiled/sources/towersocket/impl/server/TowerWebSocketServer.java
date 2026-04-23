/*
 * Decompiled with CFR 0.152.
 */
package towersocket.impl.server;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.server.WebSocketServer;
import towersocket.Greeter;
import towersocket.Serializer;
import towersocket.impl.server.TowerWebSocketConnection;
import towersocket.util.WorkerThreads;

public class TowerWebSocketServer
extends WebSocketServer {
    private final WorkerThreads<Void> ioPool;
    private final Serializer serializer;
    private final Greeter greeter;

    public TowerWebSocketServer(WorkerThreads<Void> ioPool, Serializer serializer, int port, Greeter greeter) {
        super(new InetSocketAddress("0.0.0.0", port), 1);
        this.setConnectionLostTimeout(7);
        this.ioPool = ioPool;
        this.serializer = serializer;
        this.greeter = greeter;
    }

    @Override
    public void onStart() {
    }

    @Override
    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket conn, Draft draft, ClientHandshake request) throws InvalidDataException {
        System.out.println("Got ws handshake " + new Date().toString());
        return super.onWebsocketHandshakeReceivedAsServer(conn, draft, request);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("onOpen " + new Date().toString());
        TowerWebSocketConnection connection = new TowerWebSocketConnection(this.ioPool, this.serializer, conn, this.greeter);
        System.out.println("Created conn");
        conn.setAttachment(connection);
        System.out.println("Created setAttachment");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote2) {
        System.out.println("onClose " + reason + " " + new Date().toString());
        ((TowerWebSocketConnection)conn.getAttachment()).onDisconnect(reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        try {
            ((TowerWebSocketConnection)conn.getAttachment()).receive(message.getBytes(StandardCharsets.UTF_8));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.println("onError");
        ex.printStackTrace();
    }
}

