/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent;

import scala.Function0;
import scala.concurrent.BlockContext;
import scala.concurrent.BlockContext$DefaultBlockContext$;

public final class BlockContext$ {
    public static final BlockContext$ MODULE$;
    private final ThreadLocal<BlockContext> contextLocal;

    static {
        new BlockContext$();
    }

    private ThreadLocal<BlockContext> contextLocal() {
        return this.contextLocal;
    }

    public BlockContext current() {
        BlockContext blockContext;
        BlockContext blockContext2 = this.contextLocal().get();
        if (blockContext2 == null) {
            Thread thread = Thread.currentThread();
            BlockContext blockContext3 = thread instanceof BlockContext ? (BlockContext)((Object)thread) : BlockContext$DefaultBlockContext$.MODULE$;
            blockContext = blockContext3;
        } else {
            blockContext = blockContext2;
        }
        return blockContext;
    }

    /*
     * WARNING - void declaration
     */
    public <T> T withBlockContext(BlockContext blockContext, Function0<T> body2) {
        T t;
        BlockContext old = this.contextLocal().get();
        try {
            this.contextLocal().set(blockContext);
            t = body2.apply();
            this.contextLocal().set(old);
        }
        catch (Throwable throwable) {
            void var3_3;
            this.contextLocal().set((BlockContext)var3_3);
            throw throwable;
        }
        return t;
    }

    private BlockContext$() {
        MODULE$ = this;
        this.contextLocal = new ThreadLocal();
    }
}

