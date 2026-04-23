/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Function0;
import scala.Serializable;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.reflect.internal.Flags$;
import scala.reflect.internal.Names;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Symbols$ImplClassSymbol$class;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.Position;
import scala.reflect.runtime.Gil;
import scala.reflect.runtime.SymbolTable;
import scala.reflect.runtime.SynchronizedSymbols;
import scala.reflect.runtime.SynchronizedSymbols$SynchronizedMethodSymbol$class;
import scala.reflect.runtime.SynchronizedSymbols$SynchronizedSymbol$;
import scala.reflect.runtime.SynchronizedSymbols$SynchronizedTypeSymbol$class;
import scala.runtime.BoxesRunTime;

public abstract class SynchronizedSymbols$SynchronizedSymbol$class {
    public static boolean isThreadsafe(SynchronizedSymbols.SynchronizedSymbol $this, Symbols.SymbolOps purpose) {
        return ((SymbolTable)$this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()).isCompilerUniverse() ? false : ($this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() ? true : purpose.isFlagRelated() && ($this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() & purpose.mask() & Flags$.MODULE$.TopLevelPickledFlags()) == 0L);
    }

    public static SynchronizedSymbols.SynchronizedSymbol markFlagsCompleted(SynchronizedSymbols.SynchronizedSymbol $this, long mask) {
        $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq($this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() & (mask ^ 0xFFFFFFFFFFFFFFFFL));
        return $this;
    }

    public static SynchronizedSymbols.SynchronizedSymbol markAllCompleted(SynchronizedSymbols.SynchronizedSymbol $this) {
        $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(0L);
        $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(true);
        return $this;
    }

    public static Object gilSynchronizedIfNotThreadsafe(SynchronizedSymbols.SynchronizedSymbol $this, Function0 body2) {
        return ((Gil)((Object)$this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer())).gilSynchronized(body2);
    }

    public static int validTo(SynchronizedSymbols.SynchronizedSymbol $this) {
        return BoxesRunTime.unboxToInt($this.gilSynchronizedIfNotThreadsafe(new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;

            public final int apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo();
            }

            public int apply$mcI$sp() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    public static Types.Type info(SynchronizedSymbols.SynchronizedSymbol $this) {
        return $this.gilSynchronizedIfNotThreadsafe(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;

            public final Types.Type apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Types.Type rawInfo(SynchronizedSymbols.SynchronizedSymbol $this) {
        return $this.gilSynchronizedIfNotThreadsafe(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;

            public final Types.Type apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Types.Type typeSignature(SynchronizedSymbols.SynchronizedSymbol $this) {
        return $this.gilSynchronizedIfNotThreadsafe(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;

            public final Types.Type apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Types.Type typeSignatureIn(SynchronizedSymbols.SynchronizedSymbol $this, Types.Type site) {
        return $this.gilSynchronizedIfNotThreadsafe(new Serializable($this, site){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;
            private final Types.Type site$1;

            public final Types.Type apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(this.site$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.site$1 = site$1;
            }
        });
    }

    public static List typeParams(SynchronizedSymbols.SynchronizedSymbol $this) {
        return $this.gilSynchronizedIfNotThreadsafe(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;

            public final List<Symbols.Symbol> apply() {
                List<Symbols.Symbol> list2;
                if (((SymbolTable)this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()).isCompilerUniverse()) {
                    list2 = this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams();
                } else if (((Symbols.Symbol)((Object)this.$outer)).isMonomorphicType()) {
                    list2 = Nil$.MODULE$;
                } else {
                    if (this.$outer.validTo() == 0) {
                        this.$outer.rawInfo().load((Symbols.Symbol)((Object)this.$outer));
                    }
                    if (this.$outer.validTo() == 0) {
                        this.$outer.rawInfo().load((Symbols.Symbol)((Object)this.$outer));
                    }
                    list2 = this.$outer.rawInfo().typeParams();
                }
                return list2;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static List unsafeTypeParams(SynchronizedSymbols.SynchronizedSymbol $this) {
        return $this.gilSynchronizedIfNotThreadsafe(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;

            public final List<Symbols.Symbol> apply() {
                return ((SymbolTable)this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()).isCompilerUniverse() ? this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() : (((Symbols.Symbol)((Object)this.$outer)).isMonomorphicType() ? Nil$.MODULE$ : this.$outer.rawInfo().typeParams());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Symbols.AbstractTypeSymbol createAbstractTypeSymbol(SynchronizedSymbols.SynchronizedSymbol $this, Names.TypeName name, Position pos, long newFlags) {
        return (Symbols.AbstractTypeSymbol)((Symbols.Symbol)((Object)new SynchronizedSymbols.SynchronizedTypeSymbol($this, name, pos){
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;
            private final Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
                SynchronizedSymbols$SynchronizedSymbol$.anon.11 var1_1 = this;
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
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()), (Symbols.Symbol)((Object)$outer), pos$1, name$1);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
                SynchronizedSymbols$SynchronizedTypeSymbol$class.$init$(this);
            }
        })).initFlags(newFlags);
    }

    public static Symbols.AliasTypeSymbol createAliasTypeSymbol(SynchronizedSymbols.SynchronizedSymbol $this, Names.TypeName name, Position pos, long newFlags) {
        return (Symbols.AliasTypeSymbol)((Symbols.Symbol)((Object)new SynchronizedSymbols.SynchronizedTypeSymbol($this, name, pos){
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;
            private final Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
                SynchronizedSymbols$SynchronizedSymbol$.anon.12 var1_1 = this;
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
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()), (Symbols.Symbol)((Object)$outer), pos$2, name$2);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
                SynchronizedSymbols$SynchronizedTypeSymbol$class.$init$(this);
            }
        })).initFlags(newFlags);
    }

    public static Symbols.TypeSkolem createTypeSkolemSymbol(SynchronizedSymbols.SynchronizedSymbol $this, Names.TypeName name, Object origin, Position pos, long newFlags) {
        return (Symbols.TypeSkolem)((Symbols.Symbol)((Object)new SynchronizedSymbols.SynchronizedTypeSymbol($this, name, origin, pos){
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;
            private final Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
                SynchronizedSymbols$SynchronizedSymbol$.anon.13 var1_1 = this;
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
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()), (Symbols.Symbol)((Object)$outer), pos$3, name$3, origin$1);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
                SynchronizedSymbols$SynchronizedTypeSymbol$class.$init$(this);
            }
        })).initFlags(newFlags);
    }

    public static Symbols.ClassSymbol createClassSymbol(SynchronizedSymbols.SynchronizedSymbol $this, Names.TypeName name, Position pos, long newFlags) {
        return (Symbols.ClassSymbol)((Symbols.Symbol)((Object)new SynchronizedSymbols.SynchronizedClassSymbol($this, name, pos){
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;
            private final Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
                SynchronizedSymbols$SynchronizedSymbol$.anon.8 var1_1 = this;
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
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()), (Symbols.Symbol)((Object)$outer), pos$4, name$4);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
                SynchronizedSymbols$SynchronizedTypeSymbol$class.$init$(this);
            }
        })).initFlags(newFlags);
    }

    public static Symbols.ModuleClassSymbol createModuleClassSymbol(SynchronizedSymbols.SynchronizedSymbol $this, Names.TypeName name, Position pos, long newFlags) {
        return (Symbols.ModuleClassSymbol)((Symbols.Symbol)((Object)new SynchronizedSymbols.SynchronizedModuleClassSymbol($this, name, pos){
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;
            private final Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
                SynchronizedSymbols$SynchronizedSymbol$.anon.2 var1_1 = this;
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
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()), (Symbols.Symbol)((Object)$outer), pos$5, name$5);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
                SynchronizedSymbols$SynchronizedTypeSymbol$class.$init$(this);
            }
        })).initFlags(newFlags);
    }

    public static Symbols.PackageClassSymbol createPackageClassSymbol(SynchronizedSymbols.SynchronizedSymbol $this, Names.TypeName name, Position pos, long newFlags) {
        return (Symbols.PackageClassSymbol)((Symbols.Symbol)((Object)new SynchronizedSymbols.SynchronizedModuleClassSymbol($this, name, pos){
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;
            private final Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
                SynchronizedSymbols$SynchronizedSymbol$.anon.1 var1_1 = this;
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
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()), (Symbols.Symbol)((Object)$outer), pos$6, name$6);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
                SynchronizedSymbols$SynchronizedTypeSymbol$class.$init$(this);
            }
        })).initFlags(newFlags);
    }

    public static Symbols.RefinementClassSymbol createRefinementClassSymbol(SynchronizedSymbols.SynchronizedSymbol $this, Position pos, long newFlags) {
        return (Symbols.RefinementClassSymbol)((Symbols.Symbol)((Object)new SynchronizedSymbols.SynchronizedClassSymbol($this, pos){
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;
            private final Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
                SynchronizedSymbols$SynchronizedSymbol$.anon.5 var1_1 = this;
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
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()), (Symbols.Symbol)((Object)$outer), pos$7);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
                SynchronizedSymbols$SynchronizedTypeSymbol$class.$init$(this);
            }
        })).initFlags(newFlags);
    }

    public static Symbols.ClassSymbol createImplClassSymbol(SynchronizedSymbols.SynchronizedSymbol $this, Names.TypeName name, Position pos, long newFlags) {
        return (Symbols.ClassSymbol)((Symbols.Symbol)((Object)new Symbols.ImplClassSymbol($this, name, pos){
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;
            private final Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
                SynchronizedSymbols$SynchronizedSymbol$.anon.6 var1_1 = this;
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

            public Symbols.Symbol sourceModule() {
                return Symbols$ImplClassSymbol$class.sourceModule(this);
            }

            public Types.Type typeOfThis() {
                return Symbols$ImplClassSymbol$class.typeOfThis(this);
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$$outer() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public /* synthetic */ Symbols scala$reflect$internal$Symbols$ImplClassSymbol$$$outer() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()), (Symbols.Symbol)((Object)$outer), pos$8, name$7);
                Symbols$ImplClassSymbol$class.$init$(this);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
                SynchronizedSymbols$SynchronizedTypeSymbol$class.$init$(this);
            }
        })).initFlags(newFlags);
    }

    public static Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(SynchronizedSymbols.SynchronizedSymbol $this, Position pos, long newFlags) {
        return (Symbols.PackageObjectClassSymbol)((Symbols.Symbol)((Object)new SynchronizedSymbols.SynchronizedClassSymbol($this, pos){
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;
            private final Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
                SynchronizedSymbols$SynchronizedSymbol$.anon.3 var1_1 = this;
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
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()), (Symbols.Symbol)((Object)$outer), pos$9);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
                SynchronizedSymbols$SynchronizedTypeSymbol$class.$init$(this);
            }
        })).initFlags(newFlags);
    }

    public static Symbols.MethodSymbol createMethodSymbol(SynchronizedSymbols.SynchronizedSymbol $this, Names.TermName name, Position pos, long newFlags) {
        return (Symbols.MethodSymbol)((Symbols.Symbol)((Object)new SynchronizedSymbols.SynchronizedMethodSymbol($this, name, pos){
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;
            private final Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$typeAsMemberOfLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$typeAsMemberOfLock$lzycompute() {
                SynchronizedSymbols$SynchronizedSymbol$.anon.9 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$typeAsMemberOfLock = SynchronizedSymbols$SynchronizedMethodSymbol$class.scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$typeAsMemberOfLock(this);
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$typeAsMemberOfLock;
                }
            }

            public Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$typeAsMemberOfLock() {
                return this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$typeAsMemberOfLock : this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$typeAsMemberOfLock$lzycompute();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$super$typeAsMemberOf(Types.Type pre) {
                return super.typeAsMemberOf(pre);
            }

            public Types.Type typeAsMemberOf(Types.Type pre) {
                return SynchronizedSymbols$SynchronizedMethodSymbol$class.typeAsMemberOf(this, pre);
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

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$$outer() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()), (Symbols.Symbol)((Object)$outer), pos$10, name$8);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
                SynchronizedSymbols$SynchronizedMethodSymbol$class.$init$(this);
            }
        })).initFlags(newFlags);
    }

    public static Symbols.ModuleSymbol createModuleSymbol(SynchronizedSymbols.SynchronizedSymbol $this, Names.TermName name, Position pos, long newFlags) {
        return (Symbols.ModuleSymbol)((Symbols.Symbol)((Object)new SynchronizedSymbols.SynchronizedTermSymbol($this, name, pos){
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;
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
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()), (Symbols.Symbol)((Object)$outer), pos$11, name$9);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
            }
        })).initFlags(newFlags);
    }

    public static Symbols.ModuleSymbol createPackageSymbol(SynchronizedSymbols.SynchronizedSymbol $this, Names.TermName name, Position pos, long newFlags) {
        return $this.createModuleSymbol(name, pos, newFlags);
    }

    public static Symbols.TermSymbol createValueParameterSymbol(SynchronizedSymbols.SynchronizedSymbol $this, Names.TermName name, Position pos, long newFlags) {
        return (Symbols.TermSymbol)((Symbols.Symbol)((Object)new SynchronizedSymbols.SynchronizedTermSymbol($this, name, pos){
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;
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
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()), (Symbols.Symbol)((Object)$outer), pos$12, name$10);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
            }
        })).initFlags(newFlags);
    }

    public static Symbols.TermSymbol createValueMemberSymbol(SynchronizedSymbols.SynchronizedSymbol $this, Names.TermName name, Position pos, long newFlags) {
        return (Symbols.TermSymbol)((Symbols.Symbol)((Object)new SynchronizedSymbols.SynchronizedTermSymbol($this, name, pos){
            private final /* synthetic */ SynchronizedSymbols.SynchronizedSymbol $outer;
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
                return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((scala.reflect.internal.SymbolTable)((Object)$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()), (Symbols.Symbol)((Object)$outer), pos$13, name$11);
                SynchronizedSymbols$SynchronizedSymbol$class.$init$(this);
            }
        })).initFlags(newFlags);
    }

    public static void $init$(SynchronizedSymbols.SynchronizedSymbol $this) {
        $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(false);
        $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(Flags$.MODULE$.TopLevelPickledFlags());
    }
}

