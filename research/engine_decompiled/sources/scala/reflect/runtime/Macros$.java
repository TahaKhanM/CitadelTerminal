/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Predef$;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.reflect.api.Exprs;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.macros.blackbox.Context;

public final class Macros$ {
    public static final Macros$ MODULE$;

    static {
        new Macros$();
    }

    public Exprs.Expr<JavaUniverse.JavaMirror> currentMirror(Context c) {
        Trees.TreeApi runtimeClass = c.reifyEnclosingRuntimeClass();
        if (runtimeClass.isEmpty()) {
            throw c.abort(c.enclosingPosition(), "call site does not have an enclosing class");
        }
        Trees.SelectApi scalaPackage = c.universe().Select().apply(c.universe().Ident().apply((Names.NameApi)((Object)c.universe().TermName().apply("_root_"))), (Names.NameApi)((Object)c.universe().TermName().apply("scala")));
        Trees.SelectApi runtimeUniverse = c.universe().Select().apply(c.universe().Select().apply(c.universe().Select().apply(scalaPackage, (Names.NameApi)((Object)c.universe().TermName().apply("reflect"))), (Names.NameApi)((Object)c.universe().TermName().apply("runtime"))), (Names.NameApi)((Object)c.universe().TermName().apply("universe")));
        Trees.ApplyApi currentMirror = c.universe().Apply().apply(c.universe().Select().apply(runtimeUniverse, (Names.NameApi)((Object)c.universe().TermName().apply("runtimeMirror"))), (List<Trees.TreeApi>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.SelectApi[]{c.universe().Select().apply(runtimeClass, (Names.NameApi)((Object)c.universe().TermName().apply("getClassLoader")))})));
        return c.Expr(currentMirror, c.WeakTypeTag().Nothing());
    }

    private Macros$() {
        MODULE$ = this;
    }
}

