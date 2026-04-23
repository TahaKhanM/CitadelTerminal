/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import io.grpc.Deadline;
import io.grpc.PersistentHashArrayMappedTrie;
import io.grpc.ThreadLocalContextStorage;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

@CheckReturnValue
public class Context {
    private static final Logger log = Logger.getLogger(Context.class.getName());
    private static final PersistentHashArrayMappedTrie<Key<?>, Object> EMPTY_ENTRIES = new PersistentHashArrayMappedTrie();
    static final int CONTEXT_DEPTH_WARN_THRESH = 1000;
    public static final Context ROOT = new Context(null, EMPTY_ENTRIES);
    private static final AtomicReference<Storage> storage = new AtomicReference();
    private ArrayList<ExecutableListener> listeners;
    private CancellationListener parentListener = new ParentListener();
    final CancellableContext cancellableAncestor;
    final PersistentHashArrayMappedTrie<Key<?>, Object> keyValueEntries;
    final int generation;

    static Storage storage() {
        Storage tmp = storage.get();
        if (tmp == null) {
            tmp = Context.createStorage();
        }
        return tmp;
    }

    private static Storage createStorage() {
        try {
            Class<?> clazz = Class.forName("io.grpc.override.ContextStorageOverride");
            Storage newStorage = (Storage)clazz.getConstructor(new Class[0]).newInstance(new Object[0]);
            storage.compareAndSet(null, newStorage);
        }
        catch (ClassNotFoundException e) {
            ThreadLocalContextStorage newStorage = new ThreadLocalContextStorage();
            if (storage.compareAndSet(null, newStorage)) {
                log.log(Level.FINE, "Storage override doesn't exist. Using default", e);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Storage override failed to initialize", e);
        }
        return storage.get();
    }

    public static <T> Key<T> key(String name) {
        return new Key(name);
    }

    public static <T> Key<T> keyWithDefault(String name, T defaultValue) {
        return new Key<T>(name, defaultValue);
    }

    public static Context current() {
        Context current = Context.storage().current();
        if (current == null) {
            return ROOT;
        }
        return current;
    }

    private Context(PersistentHashArrayMappedTrie<Key<?>, Object> keyValueEntries, int generation) {
        this.cancellableAncestor = null;
        this.keyValueEntries = keyValueEntries;
        this.generation = generation;
        Context.validateGeneration(generation);
    }

    private Context(Context parent, PersistentHashArrayMappedTrie<Key<?>, Object> keyValueEntries) {
        this.cancellableAncestor = Context.cancellableAncestor(parent);
        this.keyValueEntries = keyValueEntries;
        this.generation = parent == null ? 0 : parent.generation + 1;
        Context.validateGeneration(this.generation);
    }

    public CancellableContext withCancellation() {
        return new CancellableContext(this);
    }

    public CancellableContext withDeadlineAfter(long duration, TimeUnit unit, ScheduledExecutorService scheduler) {
        return this.withDeadline(Deadline.after(duration, unit), scheduler);
    }

    public CancellableContext withDeadline(Deadline deadline, ScheduledExecutorService scheduler) {
        Context.checkNotNull(deadline, "deadline");
        Context.checkNotNull(scheduler, "scheduler");
        return new CancellableContext(this, deadline, scheduler);
    }

    public <V> Context withValue(Key<V> k1, V v1) {
        PersistentHashArrayMappedTrie<Key<?>, Object> newKeyValueEntries = this.keyValueEntries.put(k1, v1);
        return new Context(this, newKeyValueEntries);
    }

    public <V1, V2> Context withValues(Key<V1> k1, V1 v1, Key<V2> k2, V2 v2) {
        PersistentHashArrayMappedTrie<Key<?>, Object> newKeyValueEntries = this.keyValueEntries.put(k1, v1).put(k2, v2);
        return new Context(this, newKeyValueEntries);
    }

    public <V1, V2, V3> Context withValues(Key<V1> k1, V1 v1, Key<V2> k2, V2 v2, Key<V3> k3, V3 v3) {
        PersistentHashArrayMappedTrie<Key<?>, Object> newKeyValueEntries = this.keyValueEntries.put(k1, v1).put(k2, v2).put(k3, v3);
        return new Context(this, newKeyValueEntries);
    }

    public <V1, V2, V3, V4> Context withValues(Key<V1> k1, V1 v1, Key<V2> k2, V2 v2, Key<V3> k3, V3 v3, Key<V4> k4, V4 v4) {
        PersistentHashArrayMappedTrie<Key<?>, Object> newKeyValueEntries = this.keyValueEntries.put(k1, v1).put(k2, v2).put(k3, v3).put(k4, v4);
        return new Context(this, newKeyValueEntries);
    }

    public Context fork() {
        return new Context(this.keyValueEntries, this.generation + 1);
    }

    boolean canBeCancelled() {
        return this.cancellableAncestor != null;
    }

    public Context attach() {
        Context prev = Context.storage().doAttach(this);
        if (prev == null) {
            return ROOT;
        }
        return prev;
    }

    public void detach(Context toAttach) {
        Context.checkNotNull(toAttach, "toAttach");
        Context.storage().detach(this, toAttach);
    }

    boolean isCurrent() {
        return Context.current() == this;
    }

    public boolean isCancelled() {
        if (this.cancellableAncestor == null) {
            return false;
        }
        return this.cancellableAncestor.isCancelled();
    }

    public Throwable cancellationCause() {
        if (this.cancellableAncestor == null) {
            return null;
        }
        return this.cancellableAncestor.cancellationCause();
    }

    public Deadline getDeadline() {
        if (this.cancellableAncestor == null) {
            return null;
        }
        return this.cancellableAncestor.getDeadline();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addListener(CancellationListener cancellationListener, Executor executor) {
        Context.checkNotNull(cancellationListener, "cancellationListener");
        Context.checkNotNull(executor, "executor");
        if (this.canBeCancelled()) {
            ExecutableListener executableListener = new ExecutableListener(executor, cancellationListener);
            Context context = this;
            synchronized (context) {
                if (this.isCancelled()) {
                    executableListener.deliver();
                } else if (this.listeners == null) {
                    this.listeners = new ArrayList();
                    this.listeners.add(executableListener);
                    if (this.cancellableAncestor != null) {
                        this.cancellableAncestor.addListener(this.parentListener, DirectExecutor.INSTANCE);
                    }
                } else {
                    this.listeners.add(executableListener);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeListener(CancellationListener cancellationListener) {
        if (!this.canBeCancelled()) {
            return;
        }
        Context context = this;
        synchronized (context) {
            if (this.listeners != null) {
                for (int i = this.listeners.size() - 1; i >= 0; --i) {
                    if (this.listeners.get(i).listener != cancellationListener) continue;
                    this.listeners.remove(i);
                    break;
                }
                if (this.listeners.isEmpty()) {
                    if (this.cancellableAncestor != null) {
                        this.cancellableAncestor.removeListener(this.parentListener);
                    }
                    this.listeners = null;
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void notifyAndClearListeners() {
        int i;
        ArrayList<ExecutableListener> tmpListeners;
        if (!this.canBeCancelled()) {
            return;
        }
        Context context = this;
        synchronized (context) {
            if (this.listeners == null) {
                return;
            }
            tmpListeners = this.listeners;
            this.listeners = null;
        }
        for (i = 0; i < tmpListeners.size(); ++i) {
            if (tmpListeners.get(i).listener instanceof ParentListener) continue;
            tmpListeners.get(i).deliver();
        }
        for (i = 0; i < tmpListeners.size(); ++i) {
            if (!(tmpListeners.get(i).listener instanceof ParentListener)) continue;
            tmpListeners.get(i).deliver();
        }
        if (this.cancellableAncestor != null) {
            this.cancellableAncestor.removeListener(this.parentListener);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    int listenerCount() {
        Context context = this;
        synchronized (context) {
            return this.listeners == null ? 0 : this.listeners.size();
        }
    }

    public void run(Runnable r) {
        Context previous = this.attach();
        try {
            r.run();
        }
        finally {
            this.detach(previous);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @CanIgnoreReturnValue
    public <V> V call(Callable<V> c) throws Exception {
        Context previous = this.attach();
        try {
            V v = c.call();
            return v;
        }
        finally {
            this.detach(previous);
        }
    }

    public Runnable wrap(final Runnable r) {
        return new Runnable(){

            @Override
            public void run() {
                Context previous = Context.this.attach();
                try {
                    r.run();
                }
                finally {
                    Context.this.detach(previous);
                }
            }
        };
    }

    public <C> Callable<C> wrap(final Callable<C> c) {
        return new Callable<C>(){

            @Override
            public C call() throws Exception {
                Context previous = Context.this.attach();
                try {
                    Object v = c.call();
                    return v;
                }
                finally {
                    Context.this.detach(previous);
                }
            }
        };
    }

    public Executor fixedContextExecutor(final Executor e) {
        class FixedContextExecutor
        implements Executor {
            FixedContextExecutor() {
            }

            @Override
            public void execute(Runnable r) {
                e.execute(Context.this.wrap(r));
            }
        }
        return new FixedContextExecutor();
    }

    public static Executor currentContextExecutor(Executor e) {
        class CurrentContextExecutor
        implements Executor {
            final /* synthetic */ Executor val$e;

            CurrentContextExecutor(Executor executor) {
                this.val$e = executor;
            }

            @Override
            public void execute(Runnable r) {
                this.val$e.execute(Context.current().wrap(r));
            }
        }
        return new CurrentContextExecutor(e);
    }

    private Object lookup(Key<?> key) {
        return this.keyValueEntries.get(key);
    }

    @CanIgnoreReturnValue
    private static <T> T checkNotNull(T reference, Object errorMessage2) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage2));
        }
        return reference;
    }

    static CancellableContext cancellableAncestor(Context parent) {
        if (parent == null) {
            return null;
        }
        if (parent instanceof CancellableContext) {
            return (CancellableContext)parent;
        }
        return parent.cancellableAncestor;
    }

    private static void validateGeneration(int generation) {
        if (generation == 1000) {
            log.log(Level.SEVERE, "Context ancestry chain length is abnormally long. This suggests an error in application code. Length exceeded: 1000", new Exception());
        }
    }

    static @interface CanIgnoreReturnValue {
    }

    static @interface CheckReturnValue {
    }

    private static enum DirectExecutor implements Executor
    {
        INSTANCE;


        @Override
        public void execute(Runnable command) {
            command.run();
        }

        public String toString() {
            return "Context.DirectExecutor";
        }
    }

    private class ParentListener
    implements CancellationListener {
        private ParentListener() {
        }

        @Override
        public void cancelled(Context context) {
            if (Context.this instanceof CancellableContext) {
                ((CancellableContext)Context.this).cancel(context.cancellationCause());
            } else {
                Context.this.notifyAndClearListeners();
            }
        }
    }

    private class ExecutableListener
    implements Runnable {
        private final Executor executor;
        private final CancellationListener listener;

        private ExecutableListener(Executor executor, CancellationListener listener) {
            this.executor = executor;
            this.listener = listener;
        }

        private void deliver() {
            try {
                this.executor.execute(this);
            }
            catch (Throwable t) {
                log.log(Level.INFO, "Exception notifying context listener", t);
            }
        }

        @Override
        public void run() {
            this.listener.cancelled(Context.this);
        }
    }

    public static abstract class Storage {
        @Deprecated
        public void attach(Context toAttach) {
            throw new UnsupportedOperationException("Deprecated. Do not call.");
        }

        public Context doAttach(Context toAttach) {
            Context current = this.current();
            this.attach(toAttach);
            return current;
        }

        public abstract void detach(Context var1, Context var2);

        public abstract Context current();
    }

    public static final class Key<T> {
        private final String name;
        private final T defaultValue;

        Key(String name) {
            this(name, null);
        }

        Key(String name, T defaultValue) {
            this.name = (String)Context.checkNotNull(name, "name");
            this.defaultValue = defaultValue;
        }

        public T get() {
            return this.get(Context.current());
        }

        public T get(Context context) {
            Object value = context.lookup(this);
            return (T)(value == null ? this.defaultValue : value);
        }

        public String toString() {
            return this.name;
        }
    }

    public static interface CancellationListener {
        public void cancelled(Context var1);
    }

    public static final class CancellableContext
    extends Context
    implements Closeable {
        private final Deadline deadline;
        private final Context uncancellableSurrogate;
        private boolean cancelled;
        private Throwable cancellationCause;
        private ScheduledFuture<?> pendingDeadline;

        private CancellableContext(Context parent) {
            super(parent, parent.keyValueEntries);
            this.deadline = parent.getDeadline();
            this.uncancellableSurrogate = new Context(this, this.keyValueEntries);
        }

        private CancellableContext(Context parent, Deadline deadline, ScheduledExecutorService scheduler) {
            super(parent, parent.keyValueEntries);
            Deadline parentDeadline = parent.getDeadline();
            if (parentDeadline != null && parentDeadline.compareTo(deadline) <= 0) {
                deadline = parentDeadline;
            } else if (!deadline.isExpired()) {
                this.pendingDeadline = deadline.runOnExpiration(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            CancellableContext.this.cancel(new TimeoutException("context timed out"));
                        }
                        catch (Throwable t) {
                            log.log(Level.SEVERE, "Cancel threw an exception, which should not happen", t);
                        }
                    }
                }, scheduler);
            } else {
                this.cancel(new TimeoutException("context timed out"));
            }
            this.deadline = deadline;
            this.uncancellableSurrogate = new Context(this, this.keyValueEntries);
        }

        @Override
        public Context attach() {
            return this.uncancellableSurrogate.attach();
        }

        @Override
        public void detach(Context toAttach) {
            this.uncancellableSurrogate.detach(toAttach);
        }

        @Override
        @Deprecated
        public boolean isCurrent() {
            return this.uncancellableSurrogate.isCurrent();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @CanIgnoreReturnValue
        public boolean cancel(Throwable cause) {
            boolean triggeredCancel = false;
            CancellableContext cancellableContext = this;
            synchronized (cancellableContext) {
                if (!this.cancelled) {
                    this.cancelled = true;
                    if (this.pendingDeadline != null) {
                        this.pendingDeadline.cancel(false);
                        this.pendingDeadline = null;
                    }
                    this.cancellationCause = cause;
                    triggeredCancel = true;
                }
            }
            if (triggeredCancel) {
                this.notifyAndClearListeners();
            }
            return triggeredCancel;
        }

        public void detachAndCancel(Context toAttach, Throwable cause) {
            try {
                this.detach(toAttach);
            }
            finally {
                this.cancel(cause);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isCancelled() {
            CancellableContext cancellableContext = this;
            synchronized (cancellableContext) {
                if (this.cancelled) {
                    return true;
                }
            }
            if (super.isCancelled()) {
                this.cancel(super.cancellationCause());
                return true;
            }
            return false;
        }

        @Override
        public Throwable cancellationCause() {
            if (this.isCancelled()) {
                return this.cancellationCause;
            }
            return null;
        }

        @Override
        public Deadline getDeadline() {
            return this.deadline;
        }

        @Override
        boolean canBeCancelled() {
            return true;
        }

        @Override
        public void close() {
            this.cancel(null);
        }
    }
}

