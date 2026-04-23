/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Function0;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WeakHashMap;
import scala.ref.WeakReference;
import scala.ref.WeakReference$;
import scala.reflect.ClassTag$;
import scala.reflect.internal.Definitions;
import scala.reflect.internal.Mirrors;
import scala.reflect.internal.Names;
import scala.reflect.internal.Reporting;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.StdNames;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.runtime.JavaMirrors;
import scala.reflect.runtime.SymbolLoaders;
import scala.reflect.runtime.SymbolTable;
import scala.reflect.runtime.SynchronizedSymbols;
import scala.runtime.BoxesRunTime;

public abstract class JavaMirrors$class {
    public static WeakHashMap scala$reflect$runtime$JavaMirrors$$mirrors(SymbolTable $this) {
        return new WeakHashMap();
    }

    /*
     * WARNING - void declaration
     */
    public static JavaMirrors.JavaMirror scala$reflect$runtime$JavaMirrors$$createMirror(SymbolTable $this, Symbols.Symbol owner2, ClassLoader cl) {
        void var3_3;
        JavaMirrors.JavaMirror jm = new JavaMirrors.JavaMirror($this, owner2, cl);
        $this.scala$reflect$runtime$JavaMirrors$$mirrors().update(cl, new WeakReference<JavaMirrors.JavaMirror>(jm));
        jm.init();
        return var3_3;
    }

    public static JavaMirrors.JavaMirror rootMirror(SymbolTable $this) {
        return JavaMirrors$class.scala$reflect$runtime$JavaMirrors$$createMirror($this, $this.NoSymbol(), $this.rootClassLoader());
    }

    public static ClassLoader rootClassLoader(SymbolTable $this) {
        return $this.getClass().getClassLoader();
    }

    public static JavaMirrors.JavaMirror runtimeMirror(SymbolTable $this, ClassLoader cl) {
        return $this.gilSynchronized(new Serializable($this, cl){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final ClassLoader cl$1;

            public final JavaMirrors.JavaMirror apply() {
                Some some;
                Option<T> option;
                Option<B> option2 = this.$outer.scala$reflect$runtime$JavaMirrors$$mirrors().get(this.cl$1);
                JavaMirrors.JavaMirror javaMirror = option2 instanceof Some && !(option = WeakReference$.MODULE$.unapply((WeakReference)(some = (Some)option2).x())).isEmpty() ? (JavaMirrors.JavaMirror)option.get() : JavaMirrors$class.scala$reflect$runtime$JavaMirrors$$createMirror(this.$outer, this.$outer.rootMirror().RootClass(), this.cl$1);
                return javaMirror;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.cl$1 = cl$1;
            }
        });
    }

    public static void validateClassInfo(SymbolTable $this, Types.ClassInfoType tp) {
        Predef$.MODULE$.assert(!tp.typeSymbol().isPackageClass() || tp.decls() instanceof SymbolLoaders.PackageScope);
    }

    public static SymbolLoaders.PackageScope newPackageScope(SymbolTable $this, Symbols.Symbol pkgClass) {
        return new SymbolLoaders.PackageScope($this, pkgClass);
    }

    public static Scopes.Scope scopeTransform(SymbolTable $this, Symbols.Symbol owner2, Function0 op) {
        return owner2.isPackageClass() ? owner2.info().decls() : (Scopes.Scope)op.apply();
    }

    public static JavaMirrors.JavaMirror mirrorThatLoaded(SymbolTable $this, Symbols.Symbol sym) {
        Symbols.Symbol symbol = sym.enclosingRootClass();
        if (symbol instanceof Mirrors.RootSymbol) {
            Mirrors.RootSymbol rootSymbol = (Mirrors.RootSymbol)((Object)symbol);
            return (JavaMirrors.JavaMirror)rootSymbol.mirror();
        }
        throw ((Reporting)((Object)$this)).abort(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", ".enclosingRootClass = ", ", which is not a RootSymbol"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{sym, sym.enclosingRootClass()})));
    }

    public static Symbols.Symbol missingHook(SymbolTable $this, Symbols.Symbol owner2, Names.Name name) {
        if (owner2.hasPackageFlag()) {
            JavaMirrors.JavaMirror mirror = $this.mirrorThatLoaded(owner2);
            if (owner2.isRootSymbol() && mirror.tryJavaClass(name.toString()).isDefined()) {
                return ((SynchronizedSymbols.SynchronizedSymbol)((Object)mirror.EmptyPackageClass())).info().decl(name);
            }
            if (name.isTermName() && !owner2.isEmptyPackageClass()) {
                return mirror.scala$reflect$runtime$JavaMirrors$$makeScalaPackage(owner2.isRootSymbol() ? name.toString() : new StringBuilder().append((Object)owner2.fullName()).append((Object)".").append(name).toString());
            }
            Names.Name name2 = name;
            Names.Name name3 = ((StdNames)((Object)$this)).tpnme().AnyRef();
            if (!(name2 != null ? !name2.equals(name3) : name3 != null) && owner2.owner().isRoot()) {
                Names.Name name4 = owner2.name();
                Names.TypeName typeName = ((StdNames)((Object)$this)).tpnme().scala_();
                if (!(name4 != null ? !name4.equals(typeName) : typeName != null)) {
                    return ((Definitions)((Object)$this)).definitions().AnyRefClass();
                }
            }
        }
        $this.info((Function0<String>)((Object)new Serializable($this, owner2, name){
            public static final long serialVersionUID = 0L;
            private final Symbols.Symbol owner$5;
            private final Names.Name name$3;

            public final String apply() {
                return new StringBuilder().append((Object)"*** missing: ").append(this.name$3).append((Object)"/").append(BoxesRunTime.boxToBoolean(this.name$3.isTermName())).append((Object)"/").append(this.owner$5).append((Object)"/").append(BoxesRunTime.boxToBoolean(this.owner$5.hasPackageFlag())).append((Object)"/").append(this.owner$5.info().decls().getClass()).toString();
            }
            {
                this.owner$5 = owner$5;
                this.name$3 = name$3;
            }
        }));
        return $this.scala$reflect$runtime$JavaMirrors$$super$missingHook(owner2, name);
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$runtime$JavaMirrors$_setter_$MirrorTag_$eq(ClassTag$.MODULE$.apply(JavaMirrors.JavaMirror.class));
    }
}

