/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import java.util.Arrays;
import scala.Function0;
import scala.Function1;
import scala.Serializable;
import scala.collection.GenMap;
import scala.collection.Seq;
import scala.collection.SeqLike;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.reflect.ClassTag$;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;

public abstract class Scopes$class {
    /*
     * WARNING - void declaration
     */
    public static Scopes.ScopeEntry scala$reflect$internal$Scopes$$newScopeEntry(SymbolTable $this, Symbols.Symbol sym, Scopes.Scope owner2) {
        void var3_3;
        Scopes.ScopeEntry e = new Scopes.ScopeEntry($this, sym, owner2);
        e.next_$eq(owner2.elems());
        owner2.elems_$eq(e);
        return var3_3;
    }

    public static Scopes.Scope newScope(SymbolTable $this) {
        return new Scopes.Scope($this);
    }

    public static Scopes.Scope newFindMemberScope(SymbolTable $this) {
        return new Scopes.Scope($this){

            public List<Symbols.Symbol> sorted() {
                List<Symbols.Symbol> members = this.toList();
                List owners = (List)((SeqLike)members.map(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final Symbols.Symbol apply(Symbols.Symbol x$5) {
                        return x$5.owner();
                    }
                }, List$.MODULE$.canBuildFrom())).distinct();
                GenMap grouped2 = members.groupBy((Function1)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final Symbols.Symbol apply(Symbols.Symbol x$6) {
                        return x$6.owner();
                    }
                }));
                return owners.flatMap(new Serializable(this, (Map)grouped2){
                    public static final long serialVersionUID = 0L;
                    private final Map grouped$1;

                    public final List<Symbols.Symbol> apply(Symbols.Symbol owner2) {
                        return ((List)this.grouped$1.apply(owner2)).reverse();
                    }
                    {
                        this.grouped$1 = grouped$1;
                    }
                }, List$.MODULE$.canBuildFrom());
            }
        };
    }

    /*
     * WARNING - void declaration
     */
    public static final Scopes.Scope newNestedScope(SymbolTable $this, Scopes.Scope outer) {
        void var2_2;
        Scopes.Scope nested = $this.newScope();
        nested.elems_$eq(outer.elems());
        nested.scala$reflect$internal$Scopes$$nestinglevel_$eq(outer.scala$reflect$internal$Scopes$$nestinglevel() + 1);
        if (outer.scala$reflect$internal$Scopes$$hashtable() != null) {
            nested.scala$reflect$internal$Scopes$$hashtable_$eq((Scopes.ScopeEntry[])Arrays.copyOf((Object[])outer.scala$reflect$internal$Scopes$$hashtable(), outer.scala$reflect$internal$Scopes$$hashtable().length));
        }
        return var2_2;
    }

    /*
     * WARNING - void declaration
     */
    public static Scopes.Scope newScopeWith(SymbolTable $this, Seq elems) {
        void var2_2;
        Scopes.Scope scope = $this.newScope();
        elems.foreach(new Serializable($this, scope){
            public static final long serialVersionUID = 0L;
            private final Scopes.Scope scope$1;

            public final Symbols.Symbol apply(Symbols.Symbol sym) {
                return this.scope$1.enter(sym);
            }
            {
                this.scope$1 = scope$1;
            }
        });
        return var2_2;
    }

    public static Scopes.Scope newPackageScope(SymbolTable $this, Symbols.Symbol pkgClass) {
        return $this.newScope();
    }

    public static Scopes.Scope scopeTransform(SymbolTable $this, Symbols.Symbol owner2, Function0 op) {
        return (Scopes.Scope)op.apply();
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$internal$Scopes$_setter_$ScopeTag_$eq(ClassTag$.MODULE$.apply(Scopes.Scope.class));
        $this.scala$reflect$internal$Scopes$_setter_$MemberScopeTag_$eq(ClassTag$.MODULE$.apply(Scopes.Scope.class));
    }
}

