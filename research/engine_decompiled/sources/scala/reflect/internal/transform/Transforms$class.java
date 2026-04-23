/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.transform;

import scala.Function0;
import scala.Serializable;
import scala.collection.immutable.List;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.tpe.TypeMaps;
import scala.reflect.internal.transform.Erasure;
import scala.reflect.internal.transform.Erasure$GenericArray$;
import scala.reflect.internal.transform.Erasure$boxingErasure$;
import scala.reflect.internal.transform.Erasure$class;
import scala.reflect.internal.transform.Erasure$javaErasure$;
import scala.reflect.internal.transform.Erasure$scalaErasure$;
import scala.reflect.internal.transform.Erasure$specialScalaErasure$;
import scala.reflect.internal.transform.Erasure$verifiedJavaErasure$;
import scala.reflect.internal.transform.PostErasure;
import scala.reflect.internal.transform.PostErasure$class;
import scala.reflect.internal.transform.PostErasure$elimErasedValueType$;
import scala.reflect.internal.transform.RefChecks;
import scala.reflect.internal.transform.RefChecks$class;
import scala.reflect.internal.transform.Transforms;
import scala.reflect.internal.transform.Transforms$$anonfun$1$;
import scala.reflect.internal.transform.Transforms$$anonfun$2$;
import scala.reflect.internal.transform.Transforms$$anonfun$3$;
import scala.reflect.internal.transform.Transforms$$anonfun$4$;
import scala.reflect.internal.transform.UnCurry;
import scala.reflect.internal.transform.UnCurry$DesugaredParameterType$;
import scala.reflect.internal.transform.UnCurry$VarargsSymbolAttachment$;
import scala.reflect.internal.transform.UnCurry$class;

public abstract class Transforms$class {
    public static RefChecks refChecks(SymbolTable $this) {
        return (RefChecks)$this.scala$reflect$internal$transform$Transforms$$refChecksLazy().force();
    }

    public static UnCurry uncurry(SymbolTable $this) {
        return (UnCurry)$this.scala$reflect$internal$transform$Transforms$$uncurryLazy().force();
    }

    public static Erasure erasure(SymbolTable $this) {
        return (Erasure)$this.scala$reflect$internal$transform$Transforms$$erasureLazy().force();
    }

    public static PostErasure postErasure(SymbolTable $this) {
        return (PostErasure)$this.scala$reflect$internal$transform$Transforms$$postErasureLazy().force();
    }

    public static Types.Type transformedType(SymbolTable $this, Symbols.Symbol sym) {
        return $this.postErasure().transformInfo(sym, $this.erasure().transformInfo(sym, $this.uncurry().transformInfo(sym, $this.refChecks().transformInfo(sym, sym.info()))));
    }

    public static Types.Type transformedType(SymbolTable $this, Types.Type tpe) {
        return $this.postErasure().elimErasedValueType().apply($this.erasure().scalaErasure().apply($this.uncurry().uncurry().apply(tpe)));
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$refChecksLazy_$eq(new Transforms.Lazy<Transforms$$anonfun$1$.anon.4>($this, (Function0<Transforms$$anonfun$1$.anon.4>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;

            public final Transforms$$anonfun$1$.anon.4 apply() {
                return new RefChecks(this){
                    private final SymbolTable global;

                    public Types.Type transformInfo(Symbols.Symbol sym, Types.Type tp) {
                        return RefChecks$class.transformInfo(this, sym, tp);
                    }

                    public SymbolTable global() {
                        return this.global;
                    }
                    {
                        SymbolTable global;
                        this.global = global = $outer.$outer;
                        RefChecks$class.$init$(this);
                    }
                };
            }

            public /* synthetic */ SymbolTable scala$reflect$internal$transform$Transforms$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        })));
        $this.scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$uncurryLazy_$eq(new Transforms.Lazy<Transforms$$anonfun$2$.anon.3>($this, (Function0<Transforms$$anonfun$2$.anon.3>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;

            public final Transforms$$anonfun$2$.anon.3 apply() {
                return new UnCurry(this){
                    private final SymbolTable global;
                    private final TypeMaps.TypeMap uncurry;
                    private final TypeMaps.TypeMap scala$reflect$internal$transform$UnCurry$$uncurryType;
                    private volatile UnCurry$VarargsSymbolAttachment$ VarargsSymbolAttachment$module;
                    private volatile UnCurry$DesugaredParameterType$ DesugaredParameterType$module;

                    private UnCurry$VarargsSymbolAttachment$ VarargsSymbolAttachment$lzycompute() {
                        Transforms$$anonfun$2$.anon.3 var1_1 = this;
                        synchronized (var1_1) {
                            if (this.VarargsSymbolAttachment$module == null) {
                                this.VarargsSymbolAttachment$module = new UnCurry$VarargsSymbolAttachment$(this);
                            }
                            // ** MonitorExit[this] (shouldn't be in output)
                            return this.VarargsSymbolAttachment$module;
                        }
                    }

                    public UnCurry$VarargsSymbolAttachment$ VarargsSymbolAttachment() {
                        return this.VarargsSymbolAttachment$module == null ? this.VarargsSymbolAttachment$lzycompute() : this.VarargsSymbolAttachment$module;
                    }

                    public TypeMaps.TypeMap uncurry() {
                        return this.uncurry;
                    }

                    private UnCurry$DesugaredParameterType$ DesugaredParameterType$lzycompute() {
                        Transforms$$anonfun$2$.anon.3 var1_1 = this;
                        synchronized (var1_1) {
                            if (this.DesugaredParameterType$module == null) {
                                this.DesugaredParameterType$module = new UnCurry$DesugaredParameterType$(this);
                            }
                            // ** MonitorExit[this] (shouldn't be in output)
                            return this.DesugaredParameterType$module;
                        }
                    }

                    public UnCurry$DesugaredParameterType$ DesugaredParameterType() {
                        return this.DesugaredParameterType$module == null ? this.DesugaredParameterType$lzycompute() : this.DesugaredParameterType$module;
                    }

                    public TypeMaps.TypeMap scala$reflect$internal$transform$UnCurry$$uncurryType() {
                        return this.scala$reflect$internal$transform$UnCurry$$uncurryType;
                    }

                    public void scala$reflect$internal$transform$UnCurry$_setter_$uncurry_$eq(TypeMaps.TypeMap x$1) {
                        this.uncurry = x$1;
                    }

                    public void scala$reflect$internal$transform$UnCurry$_setter_$scala$reflect$internal$transform$UnCurry$$uncurryType_$eq(TypeMaps.TypeMap x$1) {
                        this.scala$reflect$internal$transform$UnCurry$$uncurryType = x$1;
                    }

                    public Types.Type transformInfo(Symbols.Symbol sym, Types.Type tp) {
                        return UnCurry$class.transformInfo(this, sym, tp);
                    }

                    public SymbolTable global() {
                        return this.global;
                    }
                    {
                        SymbolTable global;
                        this.global = global = $outer.$outer;
                        UnCurry$class.$init$(this);
                    }
                };
            }

            public /* synthetic */ SymbolTable scala$reflect$internal$transform$Transforms$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        })));
        $this.scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$erasureLazy_$eq(new Transforms.Lazy<Transforms$$anonfun$3$.anon.2>($this, (Function0<Transforms$$anonfun$3$.anon.2>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;

            public final Transforms$$anonfun$3$.anon.2 apply() {
                return new Erasure(this){
                    private final SymbolTable global;
                    private volatile Erasure$GenericArray$ GenericArray$module;
                    private volatile Erasure$scalaErasure$ scalaErasure$module;
                    private volatile Erasure$specialScalaErasure$ specialScalaErasure$module;
                    private volatile Erasure$javaErasure$ javaErasure$module;
                    private volatile Erasure$verifiedJavaErasure$ verifiedJavaErasure$module;
                    private volatile Erasure$boxingErasure$ boxingErasure$module;

                    private Erasure$GenericArray$ GenericArray$lzycompute() {
                        Transforms$$anonfun$3$.anon.2 var1_1 = this;
                        synchronized (var1_1) {
                            if (this.GenericArray$module == null) {
                                this.GenericArray$module = new Erasure$GenericArray$(this);
                            }
                            // ** MonitorExit[this] (shouldn't be in output)
                            return this.GenericArray$module;
                        }
                    }

                    public Erasure$GenericArray$ GenericArray() {
                        return this.GenericArray$module == null ? this.GenericArray$lzycompute() : this.GenericArray$module;
                    }

                    private Erasure$scalaErasure$ scalaErasure$lzycompute() {
                        Transforms$$anonfun$3$.anon.2 var1_1 = this;
                        synchronized (var1_1) {
                            if (this.scalaErasure$module == null) {
                                this.scalaErasure$module = new Erasure$scalaErasure$(this);
                            }
                            // ** MonitorExit[this] (shouldn't be in output)
                            return this.scalaErasure$module;
                        }
                    }

                    public Erasure$scalaErasure$ scalaErasure() {
                        return this.scalaErasure$module == null ? this.scalaErasure$lzycompute() : this.scalaErasure$module;
                    }

                    private Erasure$specialScalaErasure$ specialScalaErasure$lzycompute() {
                        Transforms$$anonfun$3$.anon.2 var1_1 = this;
                        synchronized (var1_1) {
                            if (this.specialScalaErasure$module == null) {
                                this.specialScalaErasure$module = new Erasure$specialScalaErasure$(this);
                            }
                            // ** MonitorExit[this] (shouldn't be in output)
                            return this.specialScalaErasure$module;
                        }
                    }

                    public Erasure$specialScalaErasure$ specialScalaErasure() {
                        return this.specialScalaErasure$module == null ? this.specialScalaErasure$lzycompute() : this.specialScalaErasure$module;
                    }

                    private Erasure$javaErasure$ javaErasure$lzycompute() {
                        Transforms$$anonfun$3$.anon.2 var1_1 = this;
                        synchronized (var1_1) {
                            if (this.javaErasure$module == null) {
                                this.javaErasure$module = new Erasure$javaErasure$(this);
                            }
                            // ** MonitorExit[this] (shouldn't be in output)
                            return this.javaErasure$module;
                        }
                    }

                    public Erasure$javaErasure$ javaErasure() {
                        return this.javaErasure$module == null ? this.javaErasure$lzycompute() : this.javaErasure$module;
                    }

                    private Erasure$verifiedJavaErasure$ verifiedJavaErasure$lzycompute() {
                        Transforms$$anonfun$3$.anon.2 var1_1 = this;
                        synchronized (var1_1) {
                            if (this.verifiedJavaErasure$module == null) {
                                this.verifiedJavaErasure$module = new Erasure$verifiedJavaErasure$(this);
                            }
                            // ** MonitorExit[this] (shouldn't be in output)
                            return this.verifiedJavaErasure$module;
                        }
                    }

                    public Erasure$verifiedJavaErasure$ verifiedJavaErasure() {
                        return this.verifiedJavaErasure$module == null ? this.verifiedJavaErasure$lzycompute() : this.verifiedJavaErasure$module;
                    }

                    private Erasure$boxingErasure$ boxingErasure$lzycompute() {
                        Transforms$$anonfun$3$.anon.2 var1_1 = this;
                        synchronized (var1_1) {
                            if (this.boxingErasure$module == null) {
                                this.boxingErasure$module = new Erasure$boxingErasure$(this);
                            }
                            // ** MonitorExit[this] (shouldn't be in output)
                            return this.boxingErasure$module;
                        }
                    }

                    public Erasure$boxingErasure$ boxingErasure() {
                        return this.boxingErasure$module == null ? this.boxingErasure$lzycompute() : this.boxingErasure$module;
                    }

                    public int unboundedGenericArrayLevel(Types.Type tp) {
                        return Erasure$class.unboundedGenericArrayLevel(this, tp);
                    }

                    public Types.Type rebindInnerClass(Types.Type pre, Symbols.Symbol cls) {
                        return Erasure$class.rebindInnerClass(this, pre, cls);
                    }

                    public Types.Type erasedValueClassArg(Types.TypeRef tref) {
                        return Erasure$class.erasedValueClassArg(this, tref);
                    }

                    public boolean valueClassIsParametric(Symbols.Symbol clazz) {
                        return Erasure$class.valueClassIsParametric(this, clazz);
                    }

                    public boolean verifyJavaErasure() {
                        return Erasure$class.verifyJavaErasure(this);
                    }

                    public Erasure.ErasureMap erasure(Symbols.Symbol sym) {
                        return Erasure$class.erasure(this, sym);
                    }

                    public Types.Type specialErasure(Symbols.Symbol sym, Types.Type tp) {
                        return Erasure$class.specialErasure(this, sym, tp);
                    }

                    public Types.Type specialConstructorErasure(Symbols.Symbol clazz, Types.Type tpe) {
                        return Erasure$class.specialConstructorErasure(this, clazz, tpe);
                    }

                    public Types.Type intersectionDominator(List<Types.Type> parents2) {
                        return Erasure$class.intersectionDominator(this, parents2);
                    }

                    public Types.Type transformInfo(Symbols.Symbol sym, Types.Type tp) {
                        return Erasure$class.transformInfo(this, sym, tp);
                    }

                    public SymbolTable global() {
                        return this.global;
                    }
                    {
                        SymbolTable global;
                        this.global = global = $outer.$outer;
                        Erasure$class.$init$(this);
                    }
                };
            }

            public /* synthetic */ SymbolTable scala$reflect$internal$transform$Transforms$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        })));
        $this.scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$postErasureLazy_$eq(new Transforms.Lazy<Transforms$$anonfun$4$.anon.1>($this, (Function0<Transforms$$anonfun$4$.anon.1>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;

            public final Transforms$$anonfun$4$.anon.1 apply() {
                return new PostErasure(this){
                    private final SymbolTable global;
                    private volatile PostErasure$elimErasedValueType$ elimErasedValueType$module;

                    private PostErasure$elimErasedValueType$ elimErasedValueType$lzycompute() {
                        Transforms$$anonfun$4$.anon.1 var1_1 = this;
                        synchronized (var1_1) {
                            if (this.elimErasedValueType$module == null) {
                                this.elimErasedValueType$module = new PostErasure$elimErasedValueType$(this);
                            }
                            // ** MonitorExit[this] (shouldn't be in output)
                            return this.elimErasedValueType$module;
                        }
                    }

                    public PostErasure$elimErasedValueType$ elimErasedValueType() {
                        return this.elimErasedValueType$module == null ? this.elimErasedValueType$lzycompute() : this.elimErasedValueType$module;
                    }

                    public Types.Type transformInfo(Symbols.Symbol sym, Types.Type tp) {
                        return PostErasure$class.transformInfo(this, sym, tp);
                    }

                    public SymbolTable global() {
                        return this.global;
                    }
                    {
                        SymbolTable global;
                        this.global = global = $outer.$outer;
                        PostErasure$class.$init$(this);
                    }
                };
            }

            public /* synthetic */ SymbolTable scala$reflect$internal$transform$Transforms$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        })));
    }
}

