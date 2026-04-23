/*
 * Decompiled with CFR 0.152.
 */
package towersocket;

import java.util.Optional;
import java.util.function.Consumer;
import towersocket.ConnectionBuilder;
import towersocket.NewConnection;

public interface Greeter {
    public Optional<Consumer<ConnectionBuilder>> greet(NewConnection var1);
}

