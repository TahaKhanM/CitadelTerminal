/*
 * Decompiled with CFR 0.152.
 */
package towersocket;

import java.util.concurrent.CompletableFuture;

public interface Getter<T, R> {
    public CompletableFuture<R> get(T var1);
}

