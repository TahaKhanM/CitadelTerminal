/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import io.grpc.Context;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ThreadLocalContextStorage
extends Context.Storage {
    private static final Logger log = Logger.getLogger(ThreadLocalContextStorage.class.getName());
    private static final ThreadLocal<Context> localContext = new ThreadLocal();

    ThreadLocalContextStorage() {
    }

    @Override
    public Context doAttach(Context toAttach) {
        Context current = this.current();
        localContext.set(toAttach);
        return current;
    }

    @Override
    public void detach(Context toDetach, Context toRestore) {
        if (this.current() != toDetach) {
            log.log(Level.SEVERE, "Context was not attached when detaching", new Throwable().fillInStackTrace());
        }
        this.doAttach(toRestore);
    }

    @Override
    public Context current() {
        return localContext.get();
    }
}

