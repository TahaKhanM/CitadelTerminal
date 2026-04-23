/*
 * Decompiled with CFR 0.152.
 */
package towersocket.impl.client;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import towersocket.ClientConnectionBuilder;
import towersocket.Connection;
import towersocket.Getter;
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

public class TowerWebSocketClient
extends WebSocketClient
implements ClientConnectionBuilder,
Connection {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock read = this.lock.readLock();
    private final ReentrantReadWriteLock.WriteLock write = this.lock.writeLock();
    private final WorkerThreads<Void> ioPool;
    private final Serializer serializer;
    private final WorkerThreads<AtomicBoolean> taskQueue;
    private LifetimeStatus status = LifetimeStatus.Uninitialized;
    private Object handshakeMessage;
    private Consumer<ClientConnectionBuilder> builder;
    private List<Consumer<String>> disconnectHandlers = new ArrayList<Consumer<String>>();
    private List<Consumer<String>> failureHandlers = new ArrayList<Consumer<String>>();
    private List<Consumer<TowerSocketException>> errorHandlers = new ArrayList<Consumer<TowerSocketException>>();
    private Map<String, Consumer<Object>> localReceivers = new HashMap<String, Consumer<Object>>();
    private Map<String, Function<Object, CompletableFuture<Object>>> localResponders = new HashMap<String, Function<Object, CompletableFuture<Object>>>();
    private List<Consumer<Object>> localReceiverArray;
    private List<Function<Object, CompletableFuture<Object>>> localResponderArray;
    private Consumer<Connection> finalize;
    private Consumer<Connection> run;
    private ConnectionCommon common;
    private volatile Object pinned;

    public TowerWebSocketClient(WorkerThreads<Void> ioPool, Serializer serializer, URI address, Object handshakeMessage, Consumer<ClientConnectionBuilder> builder) {
        super(address);
        this.ioPool = ioPool;
        this.serializer = serializer;
        this.handshakeMessage = handshakeMessage;
        this.builder = builder;
        this.taskQueue = new WorkerThreads<AtomicBoolean>("client worker", 1, new AtomicBoolean(true), AtomicBoolean::get);
    }

    private void handleException(TowerSocketException e, boolean critical) {
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
            if (critical) {
                this.close();
                this.runFailureHandlers("Critical exception");
            }
        });
    }

    private void runFailureHandlers(String message) {
        for (Consumer<String> handler : this.failureHandlers) {
            try {
                handler.accept(message);
            }
            catch (RuntimeException userException) {
                System.err.println("User exception in user failure handler");
                userException.printStackTrace();
            }
        }
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        this.taskQueue.exec(() -> {
            this.write.lock();
            assert (this.status == LifetimeStatus.Uninitialized);
            this.status = LifetimeStatus.Initializing;
            this.write.unlock();
            try {
                this.builder.accept(this);
            }
            catch (RuntimeException e) {
                this.handleException(new UserException("User exception in initializer", e), true);
            }
        });
    }

    @Override
    public void onMessage(String messageStr) {
        this.taskQueue.exec(() -> {
            try {
                this.onMessageSync(messageStr);
            }
            catch (InternalException | ProtocolFailure e) {
                this.handleException((TowerSocketException)((Object)e), false);
            }
        });
    }

    private void onMessageSync(String messageStr) throws ProtocolFailure, InternalException {
        Transmission message = Transmission.parse(messageStr.getBytes(StandardCharsets.UTF_8));
        message.debug();
        if (this.status == LifetimeStatus.Pending) {
            if (message.header == 97) {
                Map<String, Integer> remoteResponders;
                Map<String, Integer> remoteReceivers;
                JsonArray structuredMessage;
                this.write.lock();
                try {
                    structuredMessage = (JsonArray)Jsoner.deserialize(new String(message.body, "UTF-8"));
                }
                catch (UnsupportedEncodingException e2) {
                    this.handleException(new InternalException("System does not support UTF-8", e2), true);
                    return;
                }
                catch (JsonException e3) {
                    this.handleException(new ProtocolFailure("Cannot des accept handshake", e3), true);
                    return;
                }
                try {
                    remoteReceivers = structuredMessage.getMap(0).entrySet().stream().collect(Collectors.toMap(entry -> (String)entry.getKey(), entry -> ((Number)entry.getValue()).intValue()));
                    remoteResponders = structuredMessage.getMap(1).entrySet().stream().collect(Collectors.toMap(entry -> (String)entry.getKey(), entry -> ((Number)entry.getValue()).intValue()));
                }
                catch (ClassCastException | IndexOutOfBoundsException e4) {
                    this.handleException(new ProtocolFailure("Cannot parse remote handlers", e4), true);
                    return;
                }
                this.common = new ConnectionCommon(e -> this.handleException((TowerSocketException)e, false), this.ioPool, this.serializer, this, remoteReceivers, remoteResponders, this.localReceiverArray, this.localResponderArray);
                this.localReceiverArray = null;
                this.localResponderArray = null;
                try {
                    this.finalize.accept(this);
                }
                catch (RuntimeException e5) {
                    this.handleException(new UserException("User exception in finalize", e5), false);
                }
                this.status = LifetimeStatus.Open;
                this.write.unlock();
                try {
                    this.run.accept(this);
                }
                catch (RuntimeException e6) {
                    this.handleException(new UserException("User exception in run", e6), false);
                }
            } else if (message.header == 114) {
                this.write.lock();
                String rejectMessage = "uninitialized";
                try {
                    rejectMessage = new String(message.body, "UTF-8");
                }
                catch (UnsupportedEncodingException e7) {
                    this.handleException(new InternalException("Unexpected exception", e7), false);
                }
                this.status = LifetimeStatus.Closed;
                this.close();
                this.write.unlock();
                this.runFailureHandlers(rejectMessage);
            }
        } else if (this.status == LifetimeStatus.Open) {
            this.common.receive(message);
        } else {
            throw new ProtocolFailure("Received message during invalid lifetime");
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote2) {
        if (this.status == LifetimeStatus.Closed) {
            return;
        }
        this.taskQueue.exec(() -> {
            this.write.lock();
            try {
                if (this.status == LifetimeStatus.Open) {
                    for (Consumer<String> handler : this.disconnectHandlers) {
                        try {
                            handler.accept(reason);
                        }
                        catch (RuntimeException userException) {
                            System.err.println("User exception in user exception handler");
                            userException.printStackTrace();
                        }
                    }
                } else if (this.status != LifetimeStatus.Closed) {
                    this.runFailureHandlers(reason);
                }
                this.status = LifetimeStatus.Closed;
            }
            finally {
                this.write.unlock();
            }
        });
        ((AtomicBoolean)this.taskQueue.conditionDat).set(false);
    }

    @Override
    public void onError(Exception ex) {
        this.taskQueue.exec(() -> ex.printStackTrace());
    }

    @Override
    public <T> void addReceiver(String name, Consumer<T> func) {
        this.write.lock();
        assert (this.status == LifetimeStatus.Initializing);
        this.localReceivers.put(name, func);
        this.write.unlock();
    }

    @Override
    public <T, R> void addResponder(String name, Function<T, CompletableFuture<R>> func) {
        this.write.lock();
        assert (this.status == LifetimeStatus.Initializing);
        this.localResponders.put(name, func);
        this.write.unlock();
    }

    @Override
    public void addDisconnectHandler(Consumer<String> handler) {
        this.write.lock();
        assert (this.status == LifetimeStatus.Initializing || this.status == LifetimeStatus.Open);
        this.disconnectHandlers.add(handler);
        this.write.unlock();
    }

    @Override
    public void addFailureHandler(Consumer<String> handler) {
        this.write.lock();
        assert (this.status == LifetimeStatus.Initializing);
        this.failureHandlers.add(handler);
        this.write.unlock();
    }

    @Override
    public void addErrorHandler(Consumer<TowerSocketException> handler) {
        this.write.lock();
        assert (this.status == LifetimeStatus.Initializing);
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
        this.write.lock();
        try {
            assert (this.status == LifetimeStatus.Initializing);
            this.finalize = finalize;
            this.run = run2;
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
            this.localReceiverArray = new ArrayList<Consumer<Object>>();
            for (String receiverKey : receiverKeys) {
                this.localReceiverArray.add(this.localReceivers.get(receiverKey));
            }
            this.localResponderArray = new ArrayList<Function<Object, CompletableFuture<Object>>>();
            for (String responderKey : responderKeys) {
                this.localResponderArray.add(this.localResponders.get(responderKey));
            }
            this.localReceivers = null;
            this.localResponders = null;
            ArrayList<Object> toSendStructured = new ArrayList<Object>();
            toSendStructured.add(receiverKeyLookup);
            toSendStructured.add(responderKeyLookup);
            String handshakeMessageSerialized = Jsoner.serialize(this.handshakeMessage);
            String handshakeMessageSanitized = URLEncoder.encode(handshakeMessageSerialized, StandardCharsets.UTF_8).replaceAll("\\+", "%20").replaceAll("\\%21", "!").replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\%7E", "~");
            toSendStructured.add(handshakeMessageSanitized);
            byte[] toSendBin = Jsoner.serialize(toSendStructured).getBytes(StandardCharsets.UTF_8);
            TransmissionBuilder toSend = new TransmissionBuilder(110);
            try {
                toSend.write(toSendBin);
            }
            catch (IOException e) {
                throw new RuntimeException("Unexpected IO exception", e);
            }
            toSend.send(this, this.ioPool);
            toSend.debug();
            this.status = LifetimeStatus.Pending;
        }
        finally {
            this.write.unlock();
        }
    }

    @Override
    public <T> Sender<T> getSender(String key) throws NoSuchElementException {
        this.read.lock();
        try {
            assert (this.status == LifetimeStatus.Open);
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
            assert (this.status == LifetimeStatus.Open);
            Getter getter2 = this.common.getGetter(key);
            return getter2;
        }
        finally {
            this.read.unlock();
        }
    }

    static enum LifetimeStatus {
        Uninitialized,
        Initializing,
        Pending,
        Open,
        Closed;

    }
}

