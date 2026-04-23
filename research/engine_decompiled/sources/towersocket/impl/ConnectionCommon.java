/*
 * Decompiled with CFR 0.152.
 */
package towersocket.impl;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import org.java_websocket.WebSocket;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import towersocket.Getter;
import towersocket.Sender;
import towersocket.Serializer;
import towersocket.exception.InternalException;
import towersocket.exception.ProtocolFailure;
import towersocket.exception.TowerSocketException;
import towersocket.exception.UserException;
import towersocket.impl.RandID;
import towersocket.impl.Transmission;
import towersocket.impl.TransmissionBuilder;
import towersocket.util.WorkerThreads;

public class ConnectionCommon {
    private final Consumer<TowerSocketException> onError;
    private final WorkerThreads<Void> ioPool;
    private final Serializer serializer;
    private final WebSocket wsocket;
    private final Map<String, Integer> remoteReceivers;
    private final Map<String, Integer> remoteResponders;
    private final List<Consumer<Object>> localReceiverArray;
    private final List<Function<Object, CompletableFuture<Object>>> localResponderArray;
    private final ConcurrentHashMap<String, CompletableFuture<Object>> requests = new ConcurrentHashMap();

    public ConnectionCommon(Consumer<TowerSocketException> onError, WorkerThreads<Void> ioPool, Serializer serializer, WebSocket wsocket, Map<String, Integer> remoteReceivers, Map<String, Integer> remoteResponders, List<Consumer<Object>> localReceiverArray, List<Function<Object, CompletableFuture<Object>>> localResponderArray) {
        this.onError = onError;
        this.ioPool = ioPool;
        this.serializer = serializer;
        this.wsocket = wsocket;
        this.remoteReceivers = remoteReceivers;
        this.remoteResponders = remoteResponders;
        this.localReceiverArray = localReceiverArray;
        this.localResponderArray = localResponderArray;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void receive(Transmission message) throws ProtocolFailure, InternalException {
        byte[] body2;
        String requestID;
        if (message.header == 109) {
            byte[] body3;
            int keyCode;
            DataInputStream data = message.asStream();
            try {
                keyCode = data.read() - 33;
                body3 = data.readAllBytes();
            }
            catch (IOException e2) {
                throw new InternalException("Unexpected IO exception", e2);
            }
            if (keyCode < 0 || keyCode >= this.localReceiverArray.size()) throw new ProtocolFailure("Invalid message key");
            Object obj = this.serializer.des(body3);
            this.localReceiverArray.get(keyCode).accept(obj);
            return;
        }
        if (message.header == 103) {
            byte[] body4;
            byte[] requestIDBin;
            int keyCode;
            DataInputStream data = message.asStream();
            try {
                keyCode = data.read() - 33;
                requestIDBin = new byte[]{data.readByte(), data.readByte(), data.readByte(), data.readByte()};
                body4 = data.readAllBytes();
            }
            catch (IOException e3) {
                throw new InternalException("Unexpected IO exception", e3);
            }
            if (keyCode < 0 || keyCode >= this.localResponderArray.size()) throw new ProtocolFailure("Invalid request key code " + keyCode);
            Object obj = this.serializer.des(body4);
            CompletableFuture<Object> resultFuture = this.localResponderArray.get(keyCode).apply(obj);
            ((CompletableFuture)resultFuture.thenAccept(result2 -> {
                byte[] resultBin;
                try {
                    resultBin = this.serializer.ser(result2);
                }
                catch (ProtocolFailure e) {
                    this.onError.accept(e);
                    return;
                }
                TransmissionBuilder toSend = new TransmissionBuilder(112);
                try {
                    toSend.write(requestIDBin);
                    toSend.write(resultBin);
                }
                catch (IOException e) {
                    this.onError.accept(new InternalException("Unexpected IO exception in transmission builder", e));
                    return;
                }
                toSend.debug();
                toSend.send(this.wsocket, this.ioPool);
            })).exceptionally(e -> {
                this.onError.accept(new UserException("User exception in responder", (Throwable)e));
                return null;
            });
            return;
        }
        if (message.header != 112) throw new ProtocolFailure("Received invalid message type");
        DataInputStream data = message.asStream();
        try {
            byte[] requestIDBin = new byte[]{data.readByte(), data.readByte(), data.readByte(), data.readByte()};
            requestID = new String(requestIDBin, "UTF-8");
            body2 = data.readAllBytes();
        }
        catch (IOException e4) {
            throw new InternalException("Unexpected IO exception", e4);
        }
        Object obj = this.serializer.des(body2);
        CompletableFuture<Object> future = this.requests.remove(requestID);
        future.complete(obj);
    }

    public <T> Sender<T> getSender(String key) throws NoSuchElementException {
        if (this.remoteReceivers.containsKey(key)) {
            int keyCode = this.remoteReceivers.get(key);
            return obj -> {
                try {
                    byte[] bin = this.serializer.ser(obj);
                    TransmissionBuilder toSend = new TransmissionBuilder(109);
                    toSend.write(keyCode);
                    toSend.write(bin);
                    toSend.debug();
                    toSend.send(this.wsocket, this.ioPool);
                }
                catch (IOException | ProtocolFailure e) {
                    this.onError.accept(new InternalException("Unexpected IO exception in transmission builder", e));
                }
                catch (WebsocketNotConnectedException e) {
                }
                catch (Exception e) {
                    System.err.println("Unexpected sender exception");
                    e.printStackTrace();
                }
            };
        }
        throw new NoSuchElementException();
    }

    public <T, R> Getter<T, R> getGetter(String key) throws NoSuchElementException {
        if (this.remoteResponders.containsKey(key)) {
            int keyCode = this.remoteResponders.get(key);
            return obj -> {
                CompletableFuture future = new CompletableFuture();
                String requestID = RandID.randID(4);
                this.requests.put(requestID, future);
                try {
                    byte[] bin = this.serializer.ser(obj);
                    TransmissionBuilder toSend = new TransmissionBuilder(103);
                    toSend.writeByte(keyCode);
                    toSend.write(requestID.getBytes(StandardCharsets.UTF_8));
                    toSend.write(bin);
                    toSend.debug();
                    toSend.send(this.wsocket, this.ioPool);
                }
                catch (IOException | ProtocolFailure e) {
                    this.onError.accept(new InternalException("Unexpected IO exception in transmission builder", e));
                }
                catch (WebsocketNotConnectedException e) {
                    future.completeExceptionally(e);
                }
                catch (Exception e) {
                    System.err.println("Unexpected sender exception");
                    e.printStackTrace();
                }
                return future;
            };
        }
        throw new NoSuchElementException();
    }
}

