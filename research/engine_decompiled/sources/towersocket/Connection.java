/*
 * Decompiled with CFR 0.152.
 */
package towersocket;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import towersocket.Getter;
import towersocket.Sender;
import towersocket.exception.TowerSocketException;

public interface Connection {
    public <T> Sender<T> getSender(String var1) throws NoSuchElementException;

    public <T, R> Getter<T, R> getGetter(String var1) throws NoSuchElementException;

    public void addDisconnectHandler(Consumer<String> var1);

    public void addErrorHandler(Consumer<TowerSocketException> var1);

    public void pin(Object var1);

    public <T> T pinned();

    public void close();
}

