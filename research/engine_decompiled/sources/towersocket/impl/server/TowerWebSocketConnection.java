/*
 * Decompiled with CFR 0.152.
 */
package towersocket.impl.server;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.java_websocket.WebSocket;
import towersocket.Connection;
import towersocket.ConnectionBuilder;
import towersocket.Getter;
import towersocket.Greeter;
import towersocket.NewConnection;
import towersocket.Sender;
import towersocket.Serializer;
import towersocket.exception.InternalException;
import towersocket.exception.ProtocolFailure;
import towersocket.exception.TowerSocketException;
import towersocket.exception.UserException;
import towersocket.impl.ConnectionCommon;
import towersocket.impl.Transmission;
import towersocket.impl.TransmissionBuilder;
import towersocket.util.WorkerThreads;

public class TowerWebSocketConnection
implements ConnectionBuilder,
Connection {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock read = this.lock.readLock();
    private final ReentrantReadWriteLock.WriteLock write = this.lock.writeLock();
    private final WorkerThreads<Void> pool;
    private final Serializer serializer;
    private final WebSocket wsocket;
    private Greeter greeter;
    private final WorkerThreads<AtomicBoolean> taskQueue;
    private volatile LifetimeStatus status = LifetimeStatus.Uninitialized;
    private Map<String, Integer> remoteReceivers = null;
    private Map<String, Integer> remoteResponders = null;
    private Map<String, Consumer<Object>> localReceivers = new HashMap<String, Consumer<Object>>();
    private Map<String, Function<Object, CompletableFuture<Object>>> localResponders = new HashMap<String, Function<Object, CompletableFuture<Object>>>();
    private ConnectionCommon common = null;
    private List<Consumer<String>> disconnectHandlers = new ArrayList<Consumer<String>>();
    private List<Consumer<TowerSocketException>> errorHandlers = new ArrayList<Consumer<TowerSocketException>>();
    private volatile Object pinned = null;

    public TowerWebSocketConnection(WorkerThreads<Void> pool, Serializer serializer, WebSocket wsocket, Greeter greeter) {
        this.pool = pool;
        this.serializer = serializer;
        this.wsocket = wsocket;
        this.greeter = greeter;
        this.taskQueue = new WorkerThreads<AtomicBoolean>("connection worker", 1, new AtomicBoolean(true), AtomicBoolean::get);
    }

    private void handleException(TowerSocketException e) {
        this.taskQueue.exec(() -> {
            for (Consumer<TowerSocketException> handler : this.errorHandlers) {
                try {
                    handler.accept(e);
                }
                catch (RuntimeException userException) {
                    System.err.println("User exception in user exception handler");
                    userException.printStackTrace();
                }
            }
        });
    }

    public void receive(byte[] messageBytes) {
        this.taskQueue.exec(() -> {
            try {
                this.receiveSync(messageBytes);
            }
            catch (InternalException | ProtocolFailure | UserException e) {
                this.close();
                this.handleException((TowerSocketException)((Object)e));
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void receiveSync(byte[] messageBytes) throws ProtocolFailure, UserException, InternalException {
        Transmission message = Transmission.parse(messageBytes);
        message.debug();
        if (this.status == LifetimeStatus.Uninitialized) {
            if (message.header == 110) {
                this.write.lock();
                try {
                    Optional<Consumer<ConnectionBuilder>> initializer;
                    byte[] messageBin;
                    JsonArray structuredMessage;
                    try {
                        structuredMessage = (JsonArray)Jsoner.deserialize(new String(message.body, "UTF-8"));
                    }
                    catch (UnsupportedEncodingException e) {
                        throw new InternalException("UTF-8 not supported", e);
                    }
                    catch (JsonException e) {
                        throw new ProtocolFailure("Cannot des new connection message", e);
                    }
                    try {
                        this.remoteReceivers = structuredMessage.getMap(0).entrySet().stream().collect(Collectors.toMap(entry -> (String)entry.getKey(), entry -> ((Number)entry.getValue()).intValue()));
                        this.remoteResponders = structuredMessage.getMap(1).entrySet().stream().collect(Collectors.toMap(entry -> (String)entry.getKey(), entry -> ((Number)entry.getValue()).intValue()));
                    }
                    catch (ClassCastException | IndexOutOfBoundsException e) {
                        throw new ProtocolFailure("Cannot parse remote handlers", e);
                    }
                    try {
                        messageBin = URLDecoder.decode(structuredMessage.get(2).toString(), StandardCharsets.UTF_8).getBytes();
                    }
                    catch (IllegalArgumentException e) {
                        throw new ProtocolFailure("Cannot URI-decode handshake message", e);
                    }
                    Object handshakeMessage = this.serializer.des(messageBin);
                    NewConnection newConnection = new NewConnection(this.wsocket.getRemoteSocketAddress(), handshakeMessage);
                    try {
                        initializer = this.greeter.greet(newConnection);
                    }
                    catch (RuntimeException e) {
                        throw new UserException(e);
                    }
                    initializer.ifPresentOrElse(connectionBuilderConsumer -> {
                        try {
                            connectionBuilderConsumer.accept(this);
                            if (this.status != LifetimeStatus.Open) {
                                this.handleException(new UserException("Initializer failed to call finish before exiting"));
                                this.close();
                            }
                        }
                        catch (RuntimeException e) {
                            this.handleException(new UserException("User exception in connection builder", e));
                            this.close();
                        }
                    }, () -> {
                        System.out.println("Greeter initializer not present, closing socket");
                        TransmissionBuilder dat = new TransmissionBuilder(114);
                        try {
                            dat.write("greeter doesn't like you".getBytes(StandardCharsets.UTF_8));
                        }
                        catch (IOException e) {
                            throw new InternalException("Unexpected IO exception", e);
                        }
                        dat.send(this.wsocket, this.pool);
                        this.wsocket.close();
                    });
                }
                finally {
                    this.write.unlock();
                }
            }
            throw new RuntimeException("invalid header: " + message.header);
        }
        if (this.status == LifetimeStatus.Open) {
            this.common.receive(message);
        } else {
            throw new ProtocolFailure("received message during invalid lifetime");
        }
    }

    public void onDisconnect(String reason) {
        this.taskQueue.exec(() -> {
            this.write.lock();
            if (this.status != LifetimeStatus.Closed) {
                for (Consumer<String> handler : this.disconnectHandlers) {
                    try {
                        handler.accept(reason);
                    }
                    catch (RuntimeException e) {
                        this.handleException(new UserException(""));
                    }
                }
                this.status = LifetimeStatus.Closed;
            }
            this.write.unlock();
        });
    }

    @Override
    public <T> void addReceiver(String name, Consumer<T> func) {
        this.write.lock();
        this.localReceivers.put(name, func);
        this.write.unlock();
    }

    @Override
    public <T, R> void addResponder(String name, Function<T, CompletableFuture<R>> func) {
        this.write.lock();
        this.localResponders.put(name, func);
        this.write.unlock();
    }

    @Override
    public void addDisconnectHandler(Consumer<String> handler) {
        this.write.lock();
        this.disconnectHandlers.add(handler);
        this.write.unlock();
    }

    @Override
    public void addErrorHandler(Consumer<TowerSocketException> handler) {
        this.write.lock();
        this.errorHandlers.add(handler);
        this.write.unlock();
    }

    @Override
    public void pin(Object obj) {
        this.pinned = obj;
    }

    @Override
    public <T> T pinned() {
        return (T)this.pinned;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void finish(Consumer<Connection> finalize, Consumer<Connection> run2) {
        try {
            this.write.lock();
            ArrayList<String> receiverKeys = new ArrayList<String>(this.localReceivers.keySet());
            HashMap<String, Integer> receiverKeyLookup = new HashMap<String, Integer>();
            for (int i = 0; i < receiverKeys.size(); ++i) {
                receiverKeyLookup.put((String)receiverKeys.get(i), i + 33);
            }
            ArrayList<String> responderKeys = new ArrayList<String>(this.localResponders.keySet());
            HashMap<String, Integer> responderKeyLookup = new HashMap<String, Integer>();
            for (int i = 0; i < responderKeys.size(); ++i) {
                responderKeyLookup.put((String)responderKeys.get(i), i + 33);
            }
            ArrayList<Consumer<Object>> localReceiverArray = new ArrayList<Consumer<Object>>();
            for (String string2 : receiverKeys) {
                localReceiverArray.add(this.localReceivers.get(string2));
            }
            ArrayList<Function<Object, CompletableFuture<Object>>> localResponderArray = new ArrayList<Function<Object, CompletableFuture<Object>>>();
            for (String responderKey : responderKeys) {
                localResponderArray.add(this.localResponders.get(responderKey));
            }
            this.localReceivers = null;
            this.localResponders = null;
            this.greeter = null;
            byte[] byArray = Jsoner.serialize(new Object[]{receiverKeyLookup, responderKeyLookup}).getBytes(StandardCharsets.UTF_8);
            TransmissionBuilder toSend = new TransmissionBuilder(97);
            try {
                toSend.write(byArray);
            }
            catch (IOException e) {
                throw new InternalException("Unexpected IO exception in transmission builder", e);
            }
            toSend.send(this.wsocket, this.pool);
            this.common = new ConnectionCommon(this::handleException, this.pool, this.serializer, this.wsocket, this.remoteReceivers, this.remoteResponders, localReceiverArray, localResponderArray);
            try {
                finalize.accept(this);
            }
            catch (RuntimeException e) {
                this.handleException(new UserException("User exception in finalize", e));
            }
            this.status = LifetimeStatus.Open;
            try {
                run2.accept(this);
            }
            catch (RuntimeException e) {
                this.handleException(new UserException("User exception in run", e));
            }
        }
        catch (InternalException e) {
            this.handleException(e);
            this.close();
        }
        finally {
            this.write.unlock();
        }
    }

    @Override
    public <T> Sender<T> getSender(String key) throws NoSuchElementException {
        this.read.lock();
        try {
            Sender sender = this.common.getSender(key);
            return sender;
        }
        finally {
            this.read.unlock();
        }
    }

    @Override
    public <T, R> Getter<T, R> getGetter(String key) throws NoSuchElementException {
        this.read.lock();
        try {
            Getter getter2 = this.common.getGetter(key);
            return getter2;
        }
        finally {
            this.read.unlock();
        }
    }

    @Override
    public void close() {
        this.taskQueue.exec(() -> {
            this.write.lock();
            try {
                this.wsocket.close();
                this.status = LifetimeStatus.Closed;
            }
            finally {
                this.write.unlock();
            }
        });
        System.out.println("CLOSING TASK QUEUE For connection, tasks left: " + this.taskQueue.tasksLeftCount());
        ((AtomicBoolean)this.taskQueue.conditionDat).set(false);
        System.out.println("FINISH CLOSING TASK QUEUE!");
    }

    static enum LifetimeStatus {
        Uninitialized,
        Initializing,
        Open,
        Closed;

    }
}

