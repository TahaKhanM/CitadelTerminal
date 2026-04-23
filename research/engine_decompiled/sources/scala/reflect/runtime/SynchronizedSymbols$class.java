/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import java.util.concurrent.atomic.AtomicInteger;
import scala.Function0;
import scala.Serializable;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;
import scala.reflect.internal.Names;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.Position;
import scala.reflect.runtime.SymbolTable;
import scala.reflect.runtime.SynchronizedSymbols;
import scala.reflect.runtime.SynchronizedSymbols$;
import scala.reflect.runtime.SynchronizedSymbols$SynchronizedSymbol$class;
import scala.reflect.runtime.SynchronizedSymbols$SynchronizedTypeSymbol$class;
import scala.reflect.runtime.ThreadLocalStorage;

public abstract class SynchronizedSymbols$class {
    public static AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicIds(SymbolTable $this) {
        return new AtomicInteger(0);
    }

    public static int nextId(SymbolTable $this) {
        return $this.scala$reflect$runtime$SynchronizedSymbols$$atomicIds().incrementAndGet();
    }

    public static AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds(SymbolTable $this) {
        return new AtomicInteger(0);
    }

    public static int nextExistentialId(SymbolTable $this) {
        return $this.scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds().incrementAndGet();
    }

    public static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedSymbols$$_recursionTable(SymbolTable $this) {
        return $this.mkThreadLocalStorage(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Map<Symbols.Symbol, Object> apply() {
                return Map$.MODULE$.empty();
            }
        });
    }

    public static Map recursionTable(SymbolTable $this) {
        return $this.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable().get();
    }

    public static void recursionTable_$eq(SymbolTable $this, Map value) {
        $this.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable().set(value);
    }

    public static Symbols.ModuleSymbol connectModuleToClass(SymbolTable $this, Symbols.ModuleSymbol m, Symbols.ClassSymbol moduleClass) {
        return $this.gilSynchronized(new Serializable($this, m, moduleClass){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final Symbols.ModuleSymbol m$1;
            private final Symbols.ClassSymbol moduleClass$1;

            public final Symbols.ModuleSymbol apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$$super$connectModuleToClass(this.m$1, this.moduleClass$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.m$1 = m$1;
                this.moduleClass$1 = moduleClass$1;
            }
        });
    }

    public static Symbols.FreeTermSymbol newFreeTermSymbol(SymbolTable $this, Names.TermName name, Function0 value, long flags, String origin) {
        return (Symbols.FreeTermSymbol)((Symbols.Symbol)((Object)new SynchronizedSymbols.SynchronizedTermSymbol($this, name, value, origin){
            private final /* synthetic */ SymbolTable $outer;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;

            public /* synthetic */ int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
                return super.validTo();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
                return super.info();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
                return super.rawInfo();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
                return super.typeSignature();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(Types.Type site) {
                return super.typeSignatureIn(site);
            }

            public /* synthetic */ List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
                return super.typeParams();
            }

            public /* synthetic */ List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
                return super.unsafeTypeParams();
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
                return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(boolean x$1) {
                this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
                return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(long x$1) {
                this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            public boolean isThreadsafe(Symbols.SymbolOps purpose) {
                return SynchronizedSymbols$SynchronizedSymbol$class.isThreadsafe(this, purpose);
            }

            public SynchronizedSymbols.SynchronizedSymbol markFlagsCompleted(long mask) {
                return SynchronizedSymbols$SynchronizedSymbol$class.markFlagsCompleted(this, mask);
            }

            public SynchronizedSymbols.SynchronizedSymbol markAllCompleted() {
                return SynchronizedSymbols$SynchronizedSymbol$class.markAllCompleted(this);
            }

            public <T> T gilSynchronizedIfNotThreadsafe(Function0<T> body2) {
                return (T)SynchronizedSymbols$SynchronizedSymbol$class.gilSynchronizedIfNotThreadsafe(this, body2);
            }

            public int validTo() {
                return SynchronizedSymbols$SynchronizedSymbol$class.validTo(this);
            }

            public Types.Type info() {
                return SynchronizedSymbols$SynchronizedSymbol$class.info(this);
            }

            public Types.Type rawInfo() {
                return SynchronizedSymbols$SynchronizedSymbol$class.rawInfo(this);
            }

            public Types.Type typeSignature() {
                return SynchronizedSymbols$SynchronizedSymbol$class.typeSignature(this);
            }

            public Types.Type typeSignatureIn(Types.Type site) {
                return SynchronizedSymbols$SynchronizedSymbol$class.typeSignatureIn(this, site);
            }

            public List<Symbols.Symbol> typeParams() {
                return SynchronizedSymbols$SynchronizedSymbol$class.typeParams(this);
            }

            public List<Symbols.Symbol> unsafeTypeParams() {
                return SynchronizedSymbols$SynchronizedSymbol$class.unsafeTypeParams(this);
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createAbstractTypeSymbol(this, name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createAliasTypeSymbol(this, name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(Names.TypeName name, Object origin, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createTypeSkolemSymbol(this, name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createClassSymbol(this, name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createModuleClassSymbol(this, name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createPackageClassSymbol(this, name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createRefinementClassSymbol(this, pos, newFlags);
            }

            public Symbols.ClassSymbol createImplClassSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createImplClassSymbol(this, name, pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createPackageObjectClassSymbol(this, pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createMethodSymbol(this, name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createModuleSymbol(this, name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createPackageSymbol(this, name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createValueParameterSymbol(this, name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createValueMemberSymbol(this, name, pos, newFlags);
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer), name$12, value$1, origin$2);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
            }
        })).initFlags(flags);
    }

    public static long newFreeTermSymbol$default$3(SymbolTable $this) {
        return 0L;
    }

    public static String newFreeTermSymbol$default$4(SymbolTable $this) {
        return null;
    }

    public static Symbols.FreeTypeSymbol newFreeTypeSymbol(SymbolTable $this, Names.TypeName name, long flags, String origin) {
        return (Symbols.FreeTypeSymbol)((Symbols.Symbol)((Object)new SynchronizedSymbols.SynchronizedTypeSymbol($this, name, origin){
            private final /* synthetic */ SymbolTable $outer;
            private final Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
                SynchronizedSymbols$.anon.4 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock = SynchronizedSymbols$SynchronizedTypeSymbol$class.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock(this);
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
                }
            }

            public Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock() {
                return this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock : this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$super$tpe_$times() {
                return super.tpe_$times();
            }

            public Types.Type tpe_$times() {
                return SynchronizedSymbols$SynchronizedTypeSymbol$class.tpe_$times(this);
            }

            public /* synthetic */ int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
                return super.validTo();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
                return super.info();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
                return super.rawInfo();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
                return super.typeSignature();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(Types.Type site) {
                return super.typeSignatureIn(site);
            }

            public /* synthetic */ List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
                return super.typeParams();
            }

            public /* synthetic */ List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
                return super.unsafeTypeParams();
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
                return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(boolean x$1) {
                this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
                return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(long x$1) {
                this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            public boolean isThreadsafe(Symbols.SymbolOps purpose) {
                return SynchronizedSymbols$SynchronizedSymbol$class.isThreadsafe(this, purpose);
            }

            public SynchronizedSymbols.SynchronizedSymbol markFlagsCompleted(long mask) {
                return SynchronizedSymbols$SynchronizedSymbol$class.markFlagsCompleted(this, mask);
            }

            public SynchronizedSymbols.SynchronizedSymbol markAllCompleted() {
                return SynchronizedSymbols$SynchronizedSymbol$class.markAllCompleted(this);
            }

            public <T> T gilSynchronizedIfNotThreadsafe(Function0<T> body2) {
                return (T)SynchronizedSymbols$SynchronizedSymbol$class.gilSynchronizedIfNotThreadsafe(this, body2);
            }

            public int validTo() {
                return SynchronizedSymbols$SynchronizedSymbol$class.validTo(this);
            }

            public Types.Type info() {
                return SynchronizedSymbols$SynchronizedSymbol$class.info(this);
            }

            public Types.Type rawInfo() {
                return SynchronizedSymbols$SynchronizedSymbol$class.rawInfo(this);
            }

            public Types.Type typeSignature() {
                return SynchronizedSymbols$SynchronizedSymbol$class.typeSignature(this);
            }

            public Types.Type typeSignatureIn(Types.Type site) {
                return SynchronizedSymbols$SynchronizedSymbol$class.typeSignatureIn(this, site);
            }

            public List<Symbols.Symbol> typeParams() {
                return SynchronizedSymbols$SynchronizedSymbol$class.typeParams(this);
            }

            public List<Symbols.Symbol> unsafeTypeParams() {
                return SynchronizedSymbols$SynchronizedSymbol$class.unsafeTypeParams(this);
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createAbstractTypeSymbol(this, name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createAliasTypeSymbol(this, name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(Names.TypeName name, Object origin, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createTypeSkolemSymbol(this, name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createClassSymbol(this, name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createModuleClassSymbol(this, name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createPackageClassSymbol(this, name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createRefinementClassSymbol(this, pos, newFlags);
            }

            public Symbols.ClassSymbol createImplClassSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createImplClassSymbol(this, name, pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createPackageObjectClassSymbol(this, pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createMethodSymbol(this, name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createModuleSymbol(this, name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createPackageSymbol(this, name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createValueParameterSymbol(this, name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createValueMemberSymbol(this, name, pos, newFlags);
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$$outer() {
                return this.$outer;
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer), name$13, origin$3);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
                SynchronizedSymbols$SynchronizedTypeSymbol$class.$init$(this);
            }
        })).initFlags(flags);
    }

    public static long newFreeTypeSymbol$default$2(SymbolTable $this) {
        return 0L;
    }

    public static String newFreeTypeSymbol$default$3(SymbolTable $this) {
        return null;
    }

    public static Symbols.NoSymbol makeNoSymbol(SymbolTable $this) {
        return new SynchronizedSymbols.SynchronizedSymbol($this){
            private final /* synthetic */ SymbolTable $outer;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;

            public /* synthetic */ int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
                return super.validTo();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
                return super.info();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
                return super.rawInfo();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
                return super.typeSignature();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(Types.Type site) {
                return super.typeSignatureIn(site);
            }

            public /* synthetic */ List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
                return super.typeParams();
            }

            public /* synthetic */ List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
                return super.unsafeTypeParams();
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
                return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(boolean x$1) {
                this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
                return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(long x$1) {
                this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            public boolean isThreadsafe(Symbols.SymbolOps purpose) {
                return SynchronizedSymbols$SynchronizedSymbol$class.isThreadsafe(this, purpose);
            }

            public SynchronizedSymbols.SynchronizedSymbol markFlagsCompleted(long mask) {
                return SynchronizedSymbols$SynchronizedSymbol$class.markFlagsCompleted(this, mask);
            }

            public SynchronizedSymbols.SynchronizedSymbol markAllCompleted() {
                return SynchronizedSymbols$SynchronizedSymbol$class.markAllCompleted(this);
            }

            public <T> T gilSynchronizedIfNotThreadsafe(Function0<T> body2) {
                return (T)SynchronizedSymbols$SynchronizedSymbol$class.gilSynchronizedIfNotThreadsafe(this, body2);
            }

            public int validTo() {
                return SynchronizedSymbols$SynchronizedSymbol$class.validTo(this);
            }

            public Types.Type info() {
                return SynchronizedSymbols$SynchronizedSymbol$class.info(this);
            }

            public Types.Type rawInfo() {
                return SynchronizedSymbols$SynchronizedSymbol$class.rawInfo(this);
            }

            public Types.Type typeSignature() {
                return SynchronizedSymbols$SynchronizedSymbol$class.typeSignature(this);
            }

            public Types.Type typeSignatureIn(Types.Type site) {
                return SynchronizedSymbols$SynchronizedSymbol$class.typeSignatureIn(this, site);
            }

            public List<Symbols.Symbol> typeParams() {
                return SynchronizedSymbols$SynchronizedSymbol$class.typeParams(this);
            }

            public List<Symbols.Symbol> unsafeTypeParams() {
                return SynchronizedSymbols$SynchronizedSymbol$class.unsafeTypeParams(this);
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createAbstractTypeSymbol(this, name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createAliasTypeSymbol(this, name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(Names.TypeName name, Object origin, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createTypeSkolemSymbol(this, name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createClassSymbol(this, name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createModuleClassSymbol(this, name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createPackageClassSymbol(this, name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createRefinementClassSymbol(this, pos, newFlags);
            }

            public Symbols.ClassSymbol createImplClassSymbol(Names.TypeName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createImplClassSymbol(this, name, pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createPackageObjectClassSymbol(this, pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createMethodSymbol(this, name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createModuleSymbol(this, name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createPackageSymbol(this, name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createValueParameterSymbol(this, name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(Names.TermName name, Position pos, long newFlags) {
                return SynchronizedSymbols$SynchronizedSymbol$class.createValueMemberSymbol(this, name, pos, newFlags);
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer));
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
            }
        };
    }

    public static void $init$(SymbolTable $this) {
    }
}

