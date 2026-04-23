/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Function0;
import scala.Function2;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.AbstractTraversable;
import scala.collection.GenTraversable;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.Scopes$EmptyScope$;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.NoPosition$;
import scala.reflect.runtime.SymbolLoaders;
import scala.reflect.runtime.SymbolTable;
import scala.runtime.BoxedUnit;

public abstract class SymbolLoaders$class {
    public static Tuple2 initAndEnterClassAndModule(SymbolTable $this, Symbols.Symbol owner2, Names.TypeName name, Function2 completer) {
        Object object;
        Serializable serializable = new Serializable($this, name){
            public static final long serialVersionUID = 0L;
            public final Names.TypeName name$2;

            public final Names.TypeName apply() {
                return this.name$2;
            }
            {
                this.name$2 = name$2;
            }
        };
        boolean bl = !name.toString().endsWith("[]");
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(serializable.name$2).toString());
        }
        NoPosition$ noPosition$ = owner2.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition();
        Symbols.ClassSymbol clazz = owner2.newClassSymbol(name, noPosition$, 0L);
        Symbols.ModuleSymbol module = owner2.newModule(name.toTermName(), owner2.scala$reflect$internal$Symbols$Symbol$$$outer().NoPosition(), 0L);
        Scopes.Scope scope = owner2.info().decls();
        Scopes$EmptyScope$ scopes$EmptyScope$ = ((Scopes)((Object)$this)).EmptyScope();
        if (!(scope != null ? !scope.equals(scopes$EmptyScope$) : scopes$EmptyScope$ != null)) {
            object = BoxedUnit.UNIT;
        } else {
            owner2.info().decls().enter(clazz);
            object = owner2.info().decls().enter(module);
        }
        $this.initClassAndModule(clazz, module, (Types.LazyType)completer.apply(clazz, module));
        return new Tuple2<Symbols.ClassSymbol, Symbols.ModuleSymbol>(clazz, module);
    }

    public static void setAllInfos(SymbolTable $this, Symbols.Symbol clazz, Symbols.Symbol module, Types.Type info2) {
        Serializable serializable = new Serializable($this, info2){
            public static final long serialVersionUID = 0L;
            public final Types.Type info$1;

            public final Symbols.Symbol apply(Symbols.Symbol x$1) {
                return x$1.setInfo(this.info$1);
            }
            {
                this.info$1 = info$1;
            }
        };
        GenTraversable genTraversable = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{clazz, module, module.moduleClass()}));
        while (!((AbstractIterable)genTraversable).isEmpty()) {
            ((Symbols.Symbol)((AbstractIterable)genTraversable).head()).setInfo(serializable.info$1);
            genTraversable = (List)((AbstractTraversable)genTraversable).tail();
        }
    }

    public static void initClassAndModule(SymbolTable $this, Symbols.Symbol clazz, Symbols.Symbol module, Types.LazyType completer) {
        $this.setAllInfos(clazz, module, completer);
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

    public static void $init$(SymbolTable $this) {
    }
}

