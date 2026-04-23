/*
 * Decompiled with CFR 0.152.
 */
package towersocket;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import towersocket.ConnectionBuilder;

public interface ClientConnectionBuilder
extends ConnectionBuilder {
    public void addFailureHandler(Consumer<String> var1);

    default public <P> void addFailureHandlerPinned(BiConsumer<P, String> handler) {
        this.addFailureHandler(reason -> handler.accept((Object)this.pinned(), (String)reason));
    }
}

