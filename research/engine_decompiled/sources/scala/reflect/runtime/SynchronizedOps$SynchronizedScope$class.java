/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Function0;
import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.Symbols;
import scala.reflect.runtime.SymbolTable;
import scala.reflect.runtime.SynchronizedOps;
import scala.runtime.BoxesRunTime;

public abstract class SynchronizedOps$SynchronizedScope$class {
    public static Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock(SynchronizedOps.SynchronizedScope $this) {
        return new Object();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Object syncLockSynchronized(SynchronizedOps.SynchronizedScope $this, Function0 body2) {
        Object r;
        if (((SymbolTable)$this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$$outer()).isCompilerUniverse()) {
            r = body2.apply();
            return r;
        }
        Object object = $this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock();
        synchronized (object) {
            Object r2 = body2.apply();
            // MONITOREXIT @DISABLED, blocks:[0, 1] lbl8 : MonitorExitStatement: MONITOREXIT : object
            r = r2;
            return r;
        }
    }

    public static boolean isEmpty(SynchronizedOps.SynchronizedScope $this) {
        return BoxesRunTime.unboxToBoolean($this.syncLockSynchronized(new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SynchronizedOps.SynchronizedScope $outer;

            public final boolean apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$isEmpty();
            }

            public boolean apply$mcZ$sp() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$isEmpty();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    public static int size(SynchronizedOps.SynchronizedScope $this) {
        return BoxesRunTime.unboxToInt($this.syncLockSynchronized(new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SynchronizedOps.SynchronizedScope $outer;

            public final int apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$size();
            }

            public int apply$mcI$sp() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$size();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    public static Symbols.Symbol enter(SynchronizedOps.SynchronizedScope $this, Symbols.Symbol sym) {
        return (Symbols.Symbol)$this.syncLockSynchronized(new Serializable($this, sym){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedOps.SynchronizedScope $outer;
            private final Symbols.Symbol sym$1;

            public final T apply() {
                return (T)this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$enter(this.sym$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.sym$1 = sym$1;
            }
        });
    }

    public static void rehash(SynchronizedOps.SynchronizedScope $this, Symbols.Symbol sym, Names.Name newname) {
        $this.syncLockSynchronized(new Serializable($this, sym, newname){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SynchronizedOps.SynchronizedScope $outer;
            public final Symbols.Symbol sym$2;
            public final Names.Name newname$1;

            public final void apply() {
                this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$rehash(this.sym$2, this.newname$1);
            }

            public void apply$mcV$sp() {
                this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$rehash(this.sym$2, this.newname$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.sym$2 = sym$2;
                this.newname$1 = newname$1;
            }
        });
    }

    public static void unlink(SynchronizedOps.SynchronizedScope $this, Scopes.ScopeEntry e) {
        $this.syncLockSynchronized(new Serializable($this, e){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SynchronizedOps.SynchronizedScope $outer;
            public final Scopes.ScopeEntry e$1;

            public final void apply() {
                this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(this.e$1);
            }

            public void apply$mcV$sp() {
                this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(this.e$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.e$1 = e$1;
            }
        });
    }

    public static void unlink(SynchronizedOps.SynchronizedScope $this, Symbols.Symbol sym) {
        $this.syncLockSynchronized(new Serializable($this, sym){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SynchronizedOps.SynchronizedScope $outer;
            public final Symbols.Symbol sym$3;

            public final void apply() {
                this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(this.sym$3);
            }

            public void apply$mcV$sp() {
                this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(this.sym$3);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.sym$3 = sym$3;
            }
        });
    }

    public static Iterator lookupAll(SynchronizedOps.SynchronizedScope $this, Names.Name name) {
        return $this.syncLockSynchronized(new Serializable($this, name){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedOps.SynchronizedScope $outer;
            private final Names.Name name$1;

            public final Iterator<Symbols.Symbol> apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupAll(this.name$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.name$1 = name$1;
            }
        });
    }

    public static Scopes.ScopeEntry lookupEntry(SynchronizedOps.SynchronizedScope $this, Names.Name name) {
        return $this.syncLockSynchronized(new Serializable($this, name){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedOps.SynchronizedScope $outer;
            private final Names.Name name$2;

            public final Scopes.ScopeEntry apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupEntry(this.name$2);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.name$2 = name$2;
            }
        });
    }

    public static Scopes.ScopeEntry lookupNextEntry(SynchronizedOps.SynchronizedScope $this, Scopes.ScopeEntry entry) {
        return $this.syncLockSynchronized(new Serializable($this, entry){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedOps.SynchronizedScope $outer;
            private final Scopes.ScopeEntry entry$1;

            public final Scopes.ScopeEntry apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupNextEntry(this.entry$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.entry$1 = entry$1;
            }
        });
    }

    public static List toList(SynchronizedOps.SynchronizedScope $this) {
        return $this.syncLockSynchronized(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedOps.SynchronizedScope $outer;

            public final List<Symbols.Symbol> apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$toList();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static void $init$(SynchronizedOps.SynchronizedScope $this) {
    }
}

