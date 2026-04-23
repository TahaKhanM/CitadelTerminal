/*
 * Decompiled with CFR 0.152.
 */
package towersocket;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import towersocket.Connection;
import towersocket.exception.TowerSocketException;

public interface ConnectionBuilder {
    public <T> void addReceiver(String var1, Consumer<T> var2);

    default public <P, T> void addReceiverPinned(String name, BiConsumer<P, T> func) {
        this.addReceiver(name, obj -> func.accept(this.pinned(), obj));
    }

    public <T, R> void addResponder(String var1, Function<T, CompletableFuture<R>> var2);

    default public <T, R> void addResponderSync(String name, Function<T, R> func) {
        this.addResponder(name, obj -> CompletableFuture.completedFuture(func.apply(obj)));
    }

    default public <P, T, R> void addResponderPinned(String name, BiFunction<P, T, CompletableFuture<R>> func) {
        this.addResponder(name, obj -> (CompletableFuture)func.apply(this.pinned(), obj));
    }

    default public <P, T, R> void addResponderPinnedSync(String name, BiFunction<P, T, R> func) {
        this.addResponder(name, obj -> CompletableFuture.completedFuture(func.apply(this.pinned(), obj)));
    }

    public void addDisconnectHandler(Consumer<String> var1);

    default public <P> void addDisconnectHandlerPinned(BiConsumer<P, String> handler) {
        this.addDisconnectHandler(reason -> handler.accept((Object)this.pinned(), (String)reason));
    }

    public void addErrorHandler(Consumer<TowerSocketException> var1);

    default public <P> void addErrorHandlerPinned(BiConsumer<P, TowerSocketException> handler) {
        this.addErrorHandler(e -> handler.accept((Object)this.pinned(), (TowerSocketException)e));
    }

    public void pin(Object var1);

    public <T> T pinned();

    public void finish(Consumer<Connection> var1, Consumer<Connection> var2);
}

