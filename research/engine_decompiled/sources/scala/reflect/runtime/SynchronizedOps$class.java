/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Function0;
import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.internal.BaseTypeSeqs;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.runtime.SymbolTable;
import scala.reflect.runtime.SynchronizedOps;
import scala.reflect.runtime.SynchronizedOps$;
import scala.reflect.runtime.SynchronizedOps$SynchronizedBaseTypeSeq$class;
import scala.reflect.runtime.SynchronizedOps$SynchronizedScope$class;

public abstract class SynchronizedOps$class {
    public static boolean synchronizeNames(SymbolTable $this) {
        return true;
    }

    public static BaseTypeSeqs.BaseTypeSeq newBaseTypeSeq(SymbolTable $this, List parents2, Types.Type[] elems) {
        return Predef$.MODULE$.refArrayOps((Object[])elems).exists(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Types.Type x$1) {
                return x$1 instanceof Types.RefinedType;
            }
        }) ? new SynchronizedOps.SynchronizedBaseTypeSeq($this, parents2, elems){
            private final /* synthetic */ SymbolTable $outer;
            private final int maxDepth;
            private volatile boolean bitmap$0;

            private int maxDepth$lzycompute() {
                SynchronizedOps$.anon.3 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.maxDepth = SynchronizedOps$SynchronizedBaseTypeSeq$class.maxDepth(this);
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.maxDepth;
                }
            }

            public int maxDepth() {
                return this.bitmap$0 ? this.maxDepth : this.maxDepth$lzycompute();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$apply(int i) {
                return super.apply(i);
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$rawElem(int i) {
                return super.rawElem(i);
            }

            public /* synthetic */ Symbols.Symbol scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$typeSymbol(int i) {
                return super.typeSymbol(i);
            }

            public /* synthetic */ List scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toList() {
                return super.toList();
            }

            public /* synthetic */ BaseTypeSeqs.BaseTypeSeq scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$copy(Types.Type head2, int offset) {
                return super.copy(head2, offset);
            }

            public /* synthetic */ BaseTypeSeqs.BaseTypeSeq scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$map(Function1 f) {
                return super.map(f);
            }

            public /* synthetic */ boolean scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$exists(Function1 p) {
                return super.exists(p);
            }

            public /* synthetic */ int scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$maxDepthOfElems() {
                return super.maxDepthOfElems();
            }

            public /* synthetic */ String scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toString() {
                return super.toString();
            }

            public Types.Type apply(int i) {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.apply(this, i);
            }

            public Types.Type rawElem(int i) {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.rawElem(this, i);
            }

            public Symbols.Symbol typeSymbol(int i) {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.typeSymbol(this, i);
            }

            public List<Types.Type> toList() {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.toList(this);
            }

            public BaseTypeSeqs.BaseTypeSeq copy(Types.Type head2, int offset) {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.copy(this, head2, offset);
            }

            public BaseTypeSeqs.BaseTypeSeq map(Function1<Types.Type, Types.Type> f) {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.map(this, f);
            }

            public boolean exists(Function1<Types.Type, Object> p) {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.exists(this, p);
            }

            public String toString() {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.toString(this);
            }

            public BaseTypeSeqs.BaseTypeSeq lateMap(Function1<Types.Type, Types.Type> f) {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.lateMap(this, f);
            }

            public /* synthetic */ SynchronizedOps scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer), parents$1, elems$1);
                SynchronizedOps$SynchronizedBaseTypeSeq$class.$init$(this);
            }
        } : new BaseTypeSeqs.BaseTypeSeq((scala.reflect.internal.SymbolTable)((Object)$this), parents2, elems);
    }

    public static SynchronizedOps.SynchronizedScope newScope(SymbolTable $this) {
        return new SynchronizedOps.SynchronizedScope($this){
            private final /* synthetic */ SymbolTable $outer;
            private final Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock;
            private volatile boolean bitmap$0;

            private Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock$lzycompute() {
                SynchronizedOps$.anon.1 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock = SynchronizedOps$SynchronizedScope$class.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock(this);
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock;
                }
            }

            public Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock() {
                return this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock : this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock$lzycompute();
            }

            public /* synthetic */ boolean scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$isEmpty() {
                return super.isEmpty();
            }

            public /* synthetic */ int scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$size() {
                return super.size();
            }

            public /* synthetic */ Symbols.Symbol scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$enter(Symbols.Symbol sym) {
                return super.enter(sym);
            }

            public /* synthetic */ void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$rehash(Symbols.Symbol sym, Names.Name newname) {
                super.rehash(sym, newname);
            }

            public /* synthetic */ void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(Scopes.ScopeEntry e) {
                super.unlink(e);
            }

            public /* synthetic */ void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(Symbols.Symbol sym) {
                super.unlink(sym);
            }

            public /* synthetic */ Iterator scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupAll(Names.Name name) {
                return super.lookupAll(name);
            }

            public /* synthetic */ Scopes.ScopeEntry scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupEntry(Names.Name name) {
                return super.lookupEntry(name);
            }

            public /* synthetic */ Scopes.ScopeEntry scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupNextEntry(Scopes.ScopeEntry entry) {
                return super.lookupNextEntry(entry);
            }

            public /* synthetic */ List scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$toList() {
                return super.toList();
            }

            public <T> T syncLockSynchronized(Function0<T> body2) {
                return (T)SynchronizedOps$SynchronizedScope$class.syncLockSynchronized(this, body2);
            }

            public boolean isEmpty() {
                return SynchronizedOps$SynchronizedScope$class.isEmpty(this);
            }

            public int size() {
                return SynchronizedOps$SynchronizedScope$class.size(this);
            }

            public <T extends Symbols.Symbol> T enter(T sym) {
                return (T)SynchronizedOps$SynchronizedScope$class.enter(this, sym);
            }

            public void rehash(Symbols.Symbol sym, Names.Name newname) {
                SynchronizedOps$SynchronizedScope$class.rehash(this, sym, newname);
            }

            public void unlink(Scopes.ScopeEntry e) {
                SynchronizedOps$SynchronizedScope$class.unlink((SynchronizedOps.SynchronizedScope)this, e);
            }

            public void unlink(Symbols.Symbol sym) {
                SynchronizedOps$SynchronizedScope$class.unlink((SynchronizedOps.SynchronizedScope)this, sym);
            }

            public Iterator<Symbols.Symbol> lookupAll(Names.Name name) {
                return SynchronizedOps$SynchronizedScope$class.lookupAll(this, name);
            }

            public Scopes.ScopeEntry lookupEntry(Names.Name name) {
                return SynchronizedOps$SynchronizedScope$class.lookupEntry(this, name);
            }

            public Scopes.ScopeEntry lookupNextEntry(Scopes.ScopeEntry entry) {
                return SynchronizedOps$SynchronizedScope$class.lookupNextEntry(this, entry);
            }

            public List<Symbols.Symbol> toList() {
                return SynchronizedOps$SynchronizedScope$class.toList(this);
            }

            public /* synthetic */ SynchronizedOps scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer));
                SynchronizedOps$SynchronizedScope$class.$init$(this);
            }
        };
    }

    public static void $init$(SymbolTable $this) {
    }
}

