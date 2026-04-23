/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.Array$;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Symbol;
import scala.Tuple10;
import scala.Tuple11;
import scala.Tuple12;
import scala.Tuple13;
import scala.Tuple14;
import scala.Tuple15;
import scala.Tuple16;
import scala.Tuple17;
import scala.Tuple18;
import scala.Tuple19;
import scala.Tuple2;
import scala.Tuple20;
import scala.Tuple21;
import scala.Tuple22;
import scala.Tuple3;
import scala.Tuple4;
import scala.Tuple5;
import scala.Tuple6;
import scala.Tuple7;
import scala.Tuple8;
import scala.Tuple9;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Vector;
import scala.collection.immutable.Vector$;
import scala.reflect.api.Constants;
import scala.reflect.api.ImplicitTags;
import scala.reflect.api.Internals;
import scala.reflect.api.Liftables;
import scala.reflect.api.Liftables$Liftable$;
import scala.reflect.api.Names;
import scala.reflect.api.StandardLiftables;
import scala.reflect.api.StandardLiftables$StandardLiftableInstances$;
import scala.reflect.api.Trees;
import scala.reflect.api.Types;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

public abstract class StandardLiftables$StandardLiftableInstances$class {
    public static Trees.TreeApi scala$reflect$api$StandardLiftables$StandardLiftableInstances$$lift(StandardLiftables.StandardLiftableInstances $this, Object value, Liftables.Liftable evidence$1) {
        Predef$ predef$ = Predef$.MODULE$;
        return evidence$1.apply(value);
    }

    public static Trees.TreeApi scala$reflect$api$StandardLiftables$StandardLiftableInstances$$selectScala(StandardLiftables.StandardLiftableInstances $this, Seq names) {
        return ((TraversableOnce)names.tail()).foldLeft(((Internals)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().ScalaDot().apply((Names.NameApi)names.head()), new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;

            public final Trees.SelectApi apply(Trees.TreeApi x$1, Names.NameApi x$2) {
                return ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Select().apply(x$1, x$2);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Trees.ApplyApi scala$reflect$api$StandardLiftables$StandardLiftableInstances$$callScala(StandardLiftables.StandardLiftableInstances $this, Seq names, List args) {
        return ((Trees)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Apply().apply(StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$selectScala($this, names), args);
    }

    public static Trees.ApplyApi scala$reflect$api$StandardLiftables$StandardLiftableInstances$$callCollection(StandardLiftables.StandardLiftableInstances $this, Names.NameApi name, List args) {
        return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$callScala($this, Predef$.MODULE$.wrapRefArray((Object[])new Names.NameApi[]{(Names.NameApi)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().collection()), (Names.NameApi)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().immutable()), name}), args);
    }

    private static Liftables.Liftable liftAsLiteral(StandardLiftables.StandardLiftableInstances $this) {
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;

            public final Trees.LiteralApi apply(T v) {
                return ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Literal().apply(((Constants)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Constant().apply(v));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new Liftables.Liftable<T>(liftables$Liftable$, (Function1)((Object)serializable)){
            private final Function1 f$1;

            public Trees.TreeApi apply(T value) {
                return (Trees.TreeApi)this.f$1.apply(value);
            }
            {
                this.f$1 = f$1;
            }
        };
    }

    public static Liftables.Liftable liftByte(StandardLiftables.StandardLiftableInstances $this) {
        return StandardLiftables$StandardLiftableInstances$class.liftAsLiteral($this);
    }

    public static Liftables.Liftable liftShort(StandardLiftables.StandardLiftableInstances $this) {
        return StandardLiftables$StandardLiftableInstances$class.liftAsLiteral($this);
    }

    public static Liftables.Liftable liftChar(StandardLiftables.StandardLiftableInstances $this) {
        return StandardLiftables$StandardLiftableInstances$class.liftAsLiteral($this);
    }

    public static Liftables.Liftable liftInt(StandardLiftables.StandardLiftableInstances $this) {
        return StandardLiftables$StandardLiftableInstances$class.liftAsLiteral($this);
    }

    public static Liftables.Liftable liftLong(StandardLiftables.StandardLiftableInstances $this) {
        return StandardLiftables$StandardLiftableInstances$class.liftAsLiteral($this);
    }

    public static Liftables.Liftable liftFloat(StandardLiftables.StandardLiftableInstances $this) {
        return StandardLiftables$StandardLiftableInstances$class.liftAsLiteral($this);
    }

    public static Liftables.Liftable liftDouble(StandardLiftables.StandardLiftableInstances $this) {
        return StandardLiftables$StandardLiftableInstances$class.liftAsLiteral($this);
    }

    public static Liftables.Liftable liftBoolean(StandardLiftables.StandardLiftableInstances $this) {
        return StandardLiftables$StandardLiftableInstances$class.liftAsLiteral($this);
    }

    public static Liftables.Liftable liftUnit(StandardLiftables.StandardLiftableInstances $this) {
        return StandardLiftables$StandardLiftableInstances$class.liftAsLiteral($this);
    }

    public static Liftables.Liftable liftString(StandardLiftables.StandardLiftableInstances $this) {
        return StandardLiftables$StandardLiftableInstances$class.liftAsLiteral($this);
    }

    public static Liftables.Liftable liftScalaSymbol(StandardLiftables.StandardLiftableInstances $this) {
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;

            public final Trees.ApplyApi apply(Symbol v) {
                Trees.LiteralApi literalApi = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Literal().apply(((Constants)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Constant().apply(v.name()));
                return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$callScala(this.$outer, Predef$.MODULE$.wrapRefArray((Object[])new Names.NameApi[]{(Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().Symbol())}), Nil$.MODULE$.$colon$colon(literalApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTree(StandardLiftables.StandardLiftableInstances $this) {
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final T apply(T x) {
                return (T)((Trees.TreeApi)Predef$.MODULE$.identity(x));
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftName(StandardLiftables.StandardLiftableInstances $this) {
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;

            public final Trees.IdentApi apply(T name) {
                return ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Ident().apply((Names.NameApi)name);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftExpr(StandardLiftables.StandardLiftableInstances $this) {
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Trees.TreeApi apply(T expr) {
                return expr.tree();
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftType(StandardLiftables.StandardLiftableInstances $this) {
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;

            public final Trees.TypeTreeApi apply(T tpe) {
                return ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).TypeTree((Types.TypeApi)tpe);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTypeTag(StandardLiftables.StandardLiftableInstances $this) {
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;

            public final Trees.TypeTreeApi apply(T ttag) {
                return ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).TypeTree(ttag.tpe());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftConstant(StandardLiftables.StandardLiftableInstances $this) {
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;

            public final Trees.LiteralApi apply(T t) {
                return ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Literal().apply((Constants.ConstantApi)t);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftArray(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable evidence$2) {
        Serializable serializable = new Serializable($this, evidence$2){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            public final Liftables.Liftable evidence$2$1;

            public final Trees.ApplyApi apply(Object arr) {
                return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$callScala(this.$outer, Predef$.MODULE$.wrapRefArray((Object[])new Names.NameApi[]{(Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().Array())}), Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.genericArrayOps(arr).map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ StandardLiftables$StandardLiftableInstances$.anonfun.liftArray.1 $outer;

                    public final Trees.TreeApi apply(T x$4) {
                        return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$lift(this.$outer.$outer, x$4, this.$outer.evidence$2$1);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, Array$.MODULE$.canBuildFrom(((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).TreeTag()))).toList());
            }

            public /* synthetic */ StandardLiftables.StandardLiftableInstances scala$reflect$api$StandardLiftables$StandardLiftableInstances$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$2$1 = evidence$2$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftVector(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable evidence$3) {
        Serializable serializable = new Serializable($this, evidence$3){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            public final Liftables.Liftable evidence$3$1;

            public final Trees.ApplyApi apply(Vector<T> vect) {
                return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$callCollection(this.$outer, (Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().Vector()), ((TraversableOnce)vect.map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ StandardLiftables$StandardLiftableInstances$.anonfun.liftVector.1 $outer;

                    public final Trees.TreeApi apply(T x$5) {
                        return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$lift(this.$outer.$outer, x$5, this.$outer.evidence$3$1);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, Vector$.MODULE$.canBuildFrom())).toList());
            }

            public /* synthetic */ StandardLiftables.StandardLiftableInstances scala$reflect$api$StandardLiftables$StandardLiftableInstances$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$3$1 = evidence$3$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftList(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable evidence$4) {
        Serializable serializable = new Serializable($this, evidence$4){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            public final Liftables.Liftable evidence$4$1;

            public final Trees.ApplyApi apply(List<T> lst) {
                return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$callCollection(this.$outer, (Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().List()), lst.map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ StandardLiftables$StandardLiftableInstances$.anonfun.liftList.1 $outer;

                    public final Trees.TreeApi apply(T x$6) {
                        return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$lift(this.$outer.$outer, x$6, this.$outer.evidence$4$1);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, List$.MODULE$.canBuildFrom()));
            }

            public /* synthetic */ StandardLiftables.StandardLiftableInstances scala$reflect$api$StandardLiftables$StandardLiftableInstances$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$4$1 = evidence$4$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftNil(StandardLiftables.StandardLiftableInstances $this) {
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;

            public final Trees.TreeApi apply(Nil$ x$7) {
                return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$selectScala(this.$outer, Predef$.MODULE$.wrapRefArray((Object[])new Names.NameApi[]{(Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().collection()), (Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().immutable()), (Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().Nil())}));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftMap(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable evidence$5, Liftables.Liftable evidence$6) {
        Serializable serializable = new Serializable($this, evidence$5, evidence$6){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            public final Liftables.Liftable evidence$5$1;
            public final Liftables.Liftable evidence$6$1;

            public final Trees.ApplyApi apply(Map<K, V> m) {
                return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$callCollection(this.$outer, (Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().Map()), m.toList().map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ StandardLiftables$StandardLiftableInstances$.anonfun.liftMap.1 $outer;

                    public final Trees.TreeApi apply(Tuple2<K, V> x$8) {
                        return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$lift(this.$outer.$outer, x$8, this.$outer.$outer.liftTuple2(this.$outer.evidence$5$1, this.$outer.evidence$6$1));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, List$.MODULE$.canBuildFrom()));
            }

            public /* synthetic */ StandardLiftables.StandardLiftableInstances scala$reflect$api$StandardLiftables$StandardLiftableInstances$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$5$1 = evidence$5$1;
                this.evidence$6$1 = evidence$6$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftSet(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable evidence$7) {
        Serializable serializable = new Serializable($this, evidence$7){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            public final Liftables.Liftable evidence$7$1;

            public final Trees.ApplyApi apply(Set<T> s2) {
                return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$callCollection(this.$outer, (Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().Set()), s2.toList().map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ StandardLiftables$StandardLiftableInstances$.anonfun.liftSet.1 $outer;

                    public final Trees.TreeApi apply(T x$9) {
                        return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$lift(this.$outer.$outer, x$9, this.$outer.evidence$7$1);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, List$.MODULE$.canBuildFrom()));
            }

            public /* synthetic */ StandardLiftables.StandardLiftableInstances scala$reflect$api$StandardLiftables$StandardLiftableInstances$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$7$1 = evidence$7$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftSome(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable evidence$8) {
        Serializable serializable = new Serializable($this, evidence$8){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable evidence$8$1;

            public final Trees.TreeApi apply(Some<T> x0$1) {
                if (x0$1 != null) {
                    Trees.TreeApi treeApi = StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$lift(this.$outer, x0$1.x(), this.evidence$8$1);
                    return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$callScala(this.$outer, Predef$.MODULE$.wrapRefArray((Object[])new Names.NameApi[]{(Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().Some())}), Nil$.MODULE$.$colon$colon(treeApi));
                }
                throw new MatchError(x0$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$8$1 = evidence$8$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftNone(StandardLiftables.StandardLiftableInstances $this) {
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;

            public final Trees.TreeApi apply(None$ x$11) {
                return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$selectScala(this.$outer, Predef$.MODULE$.wrapRefArray((Object[])new Names.NameApi[]{(Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().None())}));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftOption(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable evidence$9) {
        Serializable serializable = new Serializable($this, evidence$9){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable evidence$9$1;

            public final Trees.TreeApi apply(Option<T> x0$2) {
                block4: {
                    Trees.TreeApi treeApi;
                    block3: {
                        block2: {
                            if (!(x0$2 instanceof Some)) break block2;
                            Some some = (Some)x0$2;
                            treeApi = StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$lift(this.$outer, some, this.$outer.liftSome(this.evidence$9$1));
                            break block3;
                        }
                        if (x0$2 != None$.MODULE$) break block4;
                        None$ none$ = (None$)x0$2;
                        treeApi = StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$lift(this.$outer, none$, this.$outer.liftNone());
                    }
                    return treeApi;
                }
                throw new MatchError(x0$2);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$9$1 = evidence$9$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftLeft(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable evidence$10) {
        Serializable serializable = new Serializable($this, evidence$10){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable evidence$10$1;

            public final Trees.TreeApi apply(Left<L, R> x0$3) {
                if (x0$3 != null) {
                    Trees.TreeApi treeApi = StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$lift(this.$outer, x0$3.a(), this.evidence$10$1);
                    return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$callScala(this.$outer, Predef$.MODULE$.wrapRefArray((Object[])new Names.NameApi[]{(Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().util()), (Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().Left())}), Nil$.MODULE$.$colon$colon(treeApi));
                }
                throw new MatchError(x0$3);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$10$1 = evidence$10$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftRight(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable evidence$11) {
        Serializable serializable = new Serializable($this, evidence$11){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable evidence$11$1;

            public final Trees.TreeApi apply(Right<L, R> x0$4) {
                if (x0$4 != null) {
                    Trees.TreeApi treeApi = StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$lift(this.$outer, x0$4.b(), this.evidence$11$1);
                    return StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$callScala(this.$outer, Predef$.MODULE$.wrapRefArray((Object[])new Names.NameApi[]{(Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().util()), (Names.NameApi)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().Right())}), Nil$.MODULE$.$colon$colon(treeApi));
                }
                throw new MatchError(x0$4);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$11$1 = evidence$11$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftEither(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable evidence$12, Liftables.Liftable evidence$13) {
        Serializable serializable = new Serializable($this, evidence$12, evidence$13){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable evidence$12$1;
            private final Liftables.Liftable evidence$13$1;

            public final Trees.TreeApi apply(Either<L, R> x0$5) {
                block4: {
                    Trees.TreeApi treeApi;
                    block3: {
                        block2: {
                            if (!(x0$5 instanceof Left)) break block2;
                            Left left = (Left)x0$5;
                            treeApi = StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$lift(this.$outer, left, this.$outer.liftLeft(this.evidence$12$1));
                            break block3;
                        }
                        if (!(x0$5 instanceof Right)) break block4;
                        Right right = (Right)x0$5;
                        treeApi = StandardLiftables$StandardLiftableInstances$class.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$lift(this.$outer, right, this.$outer.liftRight(this.evidence$13$1));
                    }
                    return treeApi;
                }
                throw new MatchError(x0$5);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$12$1 = evidence$12$1;
                this.evidence$13$1 = evidence$13$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple2(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2) {
        Serializable serializable = new Serializable($this, liftT1, liftT2){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$1;
            private final Liftables.Liftable liftT2$1;

            public final Trees.TreeApi apply(Tuple2<T1, T2> t) {
                Trees.TreeApi treeApi = this.liftT1$1.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$1.apply(t._2());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$1 = liftT1$1;
                this.liftT2$1 = liftT2$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple3(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$2;
            private final Liftables.Liftable liftT2$2;
            private final Liftables.Liftable liftT3$1;

            public final Trees.TreeApi apply(Tuple3<T1, T2, T3> t) {
                Trees.TreeApi treeApi = this.liftT1$2.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$2.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$1.apply(t._3());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$2 = liftT1$2;
                this.liftT2$2 = liftT2$2;
                this.liftT3$1 = liftT3$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple4(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$3;
            private final Liftables.Liftable liftT2$3;
            private final Liftables.Liftable liftT3$2;
            private final Liftables.Liftable liftT4$1;

            public final Trees.TreeApi apply(Tuple4<T1, T2, T3, T4> t) {
                Trees.TreeApi treeApi = this.liftT1$3.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$3.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$2.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$1.apply(t._4());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$3 = liftT1$3;
                this.liftT2$3 = liftT2$3;
                this.liftT3$2 = liftT3$2;
                this.liftT4$1 = liftT4$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple5(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$4;
            private final Liftables.Liftable liftT2$4;
            private final Liftables.Liftable liftT3$3;
            private final Liftables.Liftable liftT4$2;
            private final Liftables.Liftable liftT5$1;

            public final Trees.TreeApi apply(Tuple5<T1, T2, T3, T4, T5> t) {
                Trees.TreeApi treeApi = this.liftT1$4.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$4.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$3.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$2.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$1.apply(t._5());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$4 = liftT1$4;
                this.liftT2$4 = liftT2$4;
                this.liftT3$3 = liftT3$3;
                this.liftT4$2 = liftT4$2;
                this.liftT5$1 = liftT5$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple6(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$5;
            private final Liftables.Liftable liftT2$5;
            private final Liftables.Liftable liftT3$4;
            private final Liftables.Liftable liftT4$3;
            private final Liftables.Liftable liftT5$2;
            private final Liftables.Liftable liftT6$1;

            public final Trees.TreeApi apply(Tuple6<T1, T2, T3, T4, T5, T6> t) {
                Trees.TreeApi treeApi = this.liftT1$5.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$5.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$4.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$3.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$2.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$1.apply(t._6());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$5 = liftT1$5;
                this.liftT2$5 = liftT2$5;
                this.liftT3$4 = liftT3$4;
                this.liftT4$3 = liftT4$3;
                this.liftT5$2 = liftT5$2;
                this.liftT6$1 = liftT6$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple7(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$6;
            private final Liftables.Liftable liftT2$6;
            private final Liftables.Liftable liftT3$5;
            private final Liftables.Liftable liftT4$4;
            private final Liftables.Liftable liftT5$3;
            private final Liftables.Liftable liftT6$2;
            private final Liftables.Liftable liftT7$1;

            public final Trees.TreeApi apply(Tuple7<T1, T2, T3, T4, T5, T6, T7> t) {
                Trees.TreeApi treeApi = this.liftT1$6.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$6.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$5.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$4.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$3.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$2.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$1.apply(t._7());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$6 = liftT1$6;
                this.liftT2$6 = liftT2$6;
                this.liftT3$5 = liftT3$5;
                this.liftT4$4 = liftT4$4;
                this.liftT5$3 = liftT5$3;
                this.liftT6$2 = liftT6$2;
                this.liftT7$1 = liftT7$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple8(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$7;
            private final Liftables.Liftable liftT2$7;
            private final Liftables.Liftable liftT3$6;
            private final Liftables.Liftable liftT4$5;
            private final Liftables.Liftable liftT5$4;
            private final Liftables.Liftable liftT6$3;
            private final Liftables.Liftable liftT7$2;
            private final Liftables.Liftable liftT8$1;

            public final Trees.TreeApi apply(Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> t) {
                Trees.TreeApi treeApi = this.liftT1$7.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$7.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$6.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$5.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$4.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$3.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$2.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$1.apply(t._8());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$7 = liftT1$7;
                this.liftT2$7 = liftT2$7;
                this.liftT3$6 = liftT3$6;
                this.liftT4$5 = liftT4$5;
                this.liftT5$4 = liftT5$4;
                this.liftT6$3 = liftT6$3;
                this.liftT7$2 = liftT7$2;
                this.liftT8$1 = liftT8$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple9(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8, Liftables.Liftable liftT9) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$8;
            private final Liftables.Liftable liftT2$8;
            private final Liftables.Liftable liftT3$7;
            private final Liftables.Liftable liftT4$6;
            private final Liftables.Liftable liftT5$5;
            private final Liftables.Liftable liftT6$4;
            private final Liftables.Liftable liftT7$3;
            private final Liftables.Liftable liftT8$2;
            private final Liftables.Liftable liftT9$1;

            public final Trees.TreeApi apply(Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> t) {
                Trees.TreeApi treeApi = this.liftT1$8.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$8.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$7.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$6.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$5.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$4.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$3.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$2.apply(t._8());
                Trees.TreeApi treeApi9 = this.liftT9$1.apply(t._9());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi9).$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$8 = liftT1$8;
                this.liftT2$8 = liftT2$8;
                this.liftT3$7 = liftT3$7;
                this.liftT4$6 = liftT4$6;
                this.liftT5$5 = liftT5$5;
                this.liftT6$4 = liftT6$4;
                this.liftT7$3 = liftT7$3;
                this.liftT8$2 = liftT8$2;
                this.liftT9$1 = liftT9$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple10(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8, Liftables.Liftable liftT9, Liftables.Liftable liftT10) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$9;
            private final Liftables.Liftable liftT2$9;
            private final Liftables.Liftable liftT3$8;
            private final Liftables.Liftable liftT4$7;
            private final Liftables.Liftable liftT5$6;
            private final Liftables.Liftable liftT6$5;
            private final Liftables.Liftable liftT7$4;
            private final Liftables.Liftable liftT8$3;
            private final Liftables.Liftable liftT9$2;
            private final Liftables.Liftable liftT10$1;

            public final Trees.TreeApi apply(Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> t) {
                Trees.TreeApi treeApi = this.liftT1$9.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$9.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$8.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$7.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$6.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$5.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$4.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$3.apply(t._8());
                Trees.TreeApi treeApi9 = this.liftT9$2.apply(t._9());
                Trees.TreeApi treeApi10 = this.liftT10$1.apply(t._10());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi10).$colon$colon(treeApi9).$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$9 = liftT1$9;
                this.liftT2$9 = liftT2$9;
                this.liftT3$8 = liftT3$8;
                this.liftT4$7 = liftT4$7;
                this.liftT5$6 = liftT5$6;
                this.liftT6$5 = liftT6$5;
                this.liftT7$4 = liftT7$4;
                this.liftT8$3 = liftT8$3;
                this.liftT9$2 = liftT9$2;
                this.liftT10$1 = liftT10$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple11(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8, Liftables.Liftable liftT9, Liftables.Liftable liftT10, Liftables.Liftable liftT11) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$10;
            private final Liftables.Liftable liftT2$10;
            private final Liftables.Liftable liftT3$9;
            private final Liftables.Liftable liftT4$8;
            private final Liftables.Liftable liftT5$7;
            private final Liftables.Liftable liftT6$6;
            private final Liftables.Liftable liftT7$5;
            private final Liftables.Liftable liftT8$4;
            private final Liftables.Liftable liftT9$3;
            private final Liftables.Liftable liftT10$2;
            private final Liftables.Liftable liftT11$1;

            public final Trees.TreeApi apply(Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> t) {
                Trees.TreeApi treeApi = this.liftT1$10.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$10.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$9.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$8.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$7.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$6.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$5.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$4.apply(t._8());
                Trees.TreeApi treeApi9 = this.liftT9$3.apply(t._9());
                Trees.TreeApi treeApi10 = this.liftT10$2.apply(t._10());
                Trees.TreeApi treeApi11 = this.liftT11$1.apply(t._11());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi11).$colon$colon(treeApi10).$colon$colon(treeApi9).$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$10 = liftT1$10;
                this.liftT2$10 = liftT2$10;
                this.liftT3$9 = liftT3$9;
                this.liftT4$8 = liftT4$8;
                this.liftT5$7 = liftT5$7;
                this.liftT6$6 = liftT6$6;
                this.liftT7$5 = liftT7$5;
                this.liftT8$4 = liftT8$4;
                this.liftT9$3 = liftT9$3;
                this.liftT10$2 = liftT10$2;
                this.liftT11$1 = liftT11$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple12(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8, Liftables.Liftable liftT9, Liftables.Liftable liftT10, Liftables.Liftable liftT11, Liftables.Liftable liftT12) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$11;
            private final Liftables.Liftable liftT2$11;
            private final Liftables.Liftable liftT3$10;
            private final Liftables.Liftable liftT4$9;
            private final Liftables.Liftable liftT5$8;
            private final Liftables.Liftable liftT6$7;
            private final Liftables.Liftable liftT7$6;
            private final Liftables.Liftable liftT8$5;
            private final Liftables.Liftable liftT9$4;
            private final Liftables.Liftable liftT10$3;
            private final Liftables.Liftable liftT11$2;
            private final Liftables.Liftable liftT12$1;

            public final Trees.TreeApi apply(Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> t) {
                Trees.TreeApi treeApi = this.liftT1$11.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$11.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$10.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$9.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$8.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$7.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$6.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$5.apply(t._8());
                Trees.TreeApi treeApi9 = this.liftT9$4.apply(t._9());
                Trees.TreeApi treeApi10 = this.liftT10$3.apply(t._10());
                Trees.TreeApi treeApi11 = this.liftT11$2.apply(t._11());
                Trees.TreeApi treeApi12 = this.liftT12$1.apply(t._12());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi12).$colon$colon(treeApi11).$colon$colon(treeApi10).$colon$colon(treeApi9).$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$11 = liftT1$11;
                this.liftT2$11 = liftT2$11;
                this.liftT3$10 = liftT3$10;
                this.liftT4$9 = liftT4$9;
                this.liftT5$8 = liftT5$8;
                this.liftT6$7 = liftT6$7;
                this.liftT7$6 = liftT7$6;
                this.liftT8$5 = liftT8$5;
                this.liftT9$4 = liftT9$4;
                this.liftT10$3 = liftT10$3;
                this.liftT11$2 = liftT11$2;
                this.liftT12$1 = liftT12$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple13(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8, Liftables.Liftable liftT9, Liftables.Liftable liftT10, Liftables.Liftable liftT11, Liftables.Liftable liftT12, Liftables.Liftable liftT13) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$12;
            private final Liftables.Liftable liftT2$12;
            private final Liftables.Liftable liftT3$11;
            private final Liftables.Liftable liftT4$10;
            private final Liftables.Liftable liftT5$9;
            private final Liftables.Liftable liftT6$8;
            private final Liftables.Liftable liftT7$7;
            private final Liftables.Liftable liftT8$6;
            private final Liftables.Liftable liftT9$5;
            private final Liftables.Liftable liftT10$4;
            private final Liftables.Liftable liftT11$3;
            private final Liftables.Liftable liftT12$2;
            private final Liftables.Liftable liftT13$1;

            public final Trees.TreeApi apply(Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> t) {
                Trees.TreeApi treeApi = this.liftT1$12.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$12.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$11.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$10.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$9.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$8.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$7.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$6.apply(t._8());
                Trees.TreeApi treeApi9 = this.liftT9$5.apply(t._9());
                Trees.TreeApi treeApi10 = this.liftT10$4.apply(t._10());
                Trees.TreeApi treeApi11 = this.liftT11$3.apply(t._11());
                Trees.TreeApi treeApi12 = this.liftT12$2.apply(t._12());
                Trees.TreeApi treeApi13 = this.liftT13$1.apply(t._13());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi13).$colon$colon(treeApi12).$colon$colon(treeApi11).$colon$colon(treeApi10).$colon$colon(treeApi9).$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$12 = liftT1$12;
                this.liftT2$12 = liftT2$12;
                this.liftT3$11 = liftT3$11;
                this.liftT4$10 = liftT4$10;
                this.liftT5$9 = liftT5$9;
                this.liftT6$8 = liftT6$8;
                this.liftT7$7 = liftT7$7;
                this.liftT8$6 = liftT8$6;
                this.liftT9$5 = liftT9$5;
                this.liftT10$4 = liftT10$4;
                this.liftT11$3 = liftT11$3;
                this.liftT12$2 = liftT12$2;
                this.liftT13$1 = liftT13$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple14(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8, Liftables.Liftable liftT9, Liftables.Liftable liftT10, Liftables.Liftable liftT11, Liftables.Liftable liftT12, Liftables.Liftable liftT13, Liftables.Liftable liftT14) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$13;
            private final Liftables.Liftable liftT2$13;
            private final Liftables.Liftable liftT3$12;
            private final Liftables.Liftable liftT4$11;
            private final Liftables.Liftable liftT5$10;
            private final Liftables.Liftable liftT6$9;
            private final Liftables.Liftable liftT7$8;
            private final Liftables.Liftable liftT8$7;
            private final Liftables.Liftable liftT9$6;
            private final Liftables.Liftable liftT10$5;
            private final Liftables.Liftable liftT11$4;
            private final Liftables.Liftable liftT12$3;
            private final Liftables.Liftable liftT13$2;
            private final Liftables.Liftable liftT14$1;

            public final Trees.TreeApi apply(Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> t) {
                Trees.TreeApi treeApi = this.liftT1$13.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$13.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$12.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$11.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$10.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$9.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$8.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$7.apply(t._8());
                Trees.TreeApi treeApi9 = this.liftT9$6.apply(t._9());
                Trees.TreeApi treeApi10 = this.liftT10$5.apply(t._10());
                Trees.TreeApi treeApi11 = this.liftT11$4.apply(t._11());
                Trees.TreeApi treeApi12 = this.liftT12$3.apply(t._12());
                Trees.TreeApi treeApi13 = this.liftT13$2.apply(t._13());
                Trees.TreeApi treeApi14 = this.liftT14$1.apply(t._14());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi14).$colon$colon(treeApi13).$colon$colon(treeApi12).$colon$colon(treeApi11).$colon$colon(treeApi10).$colon$colon(treeApi9).$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$13 = liftT1$13;
                this.liftT2$13 = liftT2$13;
                this.liftT3$12 = liftT3$12;
                this.liftT4$11 = liftT4$11;
                this.liftT5$10 = liftT5$10;
                this.liftT6$9 = liftT6$9;
                this.liftT7$8 = liftT7$8;
                this.liftT8$7 = liftT8$7;
                this.liftT9$6 = liftT9$6;
                this.liftT10$5 = liftT10$5;
                this.liftT11$4 = liftT11$4;
                this.liftT12$3 = liftT12$3;
                this.liftT13$2 = liftT13$2;
                this.liftT14$1 = liftT14$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple15(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8, Liftables.Liftable liftT9, Liftables.Liftable liftT10, Liftables.Liftable liftT11, Liftables.Liftable liftT12, Liftables.Liftable liftT13, Liftables.Liftable liftT14, Liftables.Liftable liftT15) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$14;
            private final Liftables.Liftable liftT2$14;
            private final Liftables.Liftable liftT3$13;
            private final Liftables.Liftable liftT4$12;
            private final Liftables.Liftable liftT5$11;
            private final Liftables.Liftable liftT6$10;
            private final Liftables.Liftable liftT7$9;
            private final Liftables.Liftable liftT8$8;
            private final Liftables.Liftable liftT9$7;
            private final Liftables.Liftable liftT10$6;
            private final Liftables.Liftable liftT11$5;
            private final Liftables.Liftable liftT12$4;
            private final Liftables.Liftable liftT13$3;
            private final Liftables.Liftable liftT14$2;
            private final Liftables.Liftable liftT15$1;

            public final Trees.TreeApi apply(Tuple15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> t) {
                Trees.TreeApi treeApi = this.liftT1$14.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$14.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$13.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$12.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$11.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$10.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$9.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$8.apply(t._8());
                Trees.TreeApi treeApi9 = this.liftT9$7.apply(t._9());
                Trees.TreeApi treeApi10 = this.liftT10$6.apply(t._10());
                Trees.TreeApi treeApi11 = this.liftT11$5.apply(t._11());
                Trees.TreeApi treeApi12 = this.liftT12$4.apply(t._12());
                Trees.TreeApi treeApi13 = this.liftT13$3.apply(t._13());
                Trees.TreeApi treeApi14 = this.liftT14$2.apply(t._14());
                Trees.TreeApi treeApi15 = this.liftT15$1.apply(t._15());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi15).$colon$colon(treeApi14).$colon$colon(treeApi13).$colon$colon(treeApi12).$colon$colon(treeApi11).$colon$colon(treeApi10).$colon$colon(treeApi9).$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$14 = liftT1$14;
                this.liftT2$14 = liftT2$14;
                this.liftT3$13 = liftT3$13;
                this.liftT4$12 = liftT4$12;
                this.liftT5$11 = liftT5$11;
                this.liftT6$10 = liftT6$10;
                this.liftT7$9 = liftT7$9;
                this.liftT8$8 = liftT8$8;
                this.liftT9$7 = liftT9$7;
                this.liftT10$6 = liftT10$6;
                this.liftT11$5 = liftT11$5;
                this.liftT12$4 = liftT12$4;
                this.liftT13$3 = liftT13$3;
                this.liftT14$2 = liftT14$2;
                this.liftT15$1 = liftT15$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple16(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8, Liftables.Liftable liftT9, Liftables.Liftable liftT10, Liftables.Liftable liftT11, Liftables.Liftable liftT12, Liftables.Liftable liftT13, Liftables.Liftable liftT14, Liftables.Liftable liftT15, Liftables.Liftable liftT16) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15, liftT16){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$15;
            private final Liftables.Liftable liftT2$15;
            private final Liftables.Liftable liftT3$14;
            private final Liftables.Liftable liftT4$13;
            private final Liftables.Liftable liftT5$12;
            private final Liftables.Liftable liftT6$11;
            private final Liftables.Liftable liftT7$10;
            private final Liftables.Liftable liftT8$9;
            private final Liftables.Liftable liftT9$8;
            private final Liftables.Liftable liftT10$7;
            private final Liftables.Liftable liftT11$6;
            private final Liftables.Liftable liftT12$5;
            private final Liftables.Liftable liftT13$4;
            private final Liftables.Liftable liftT14$3;
            private final Liftables.Liftable liftT15$2;
            private final Liftables.Liftable liftT16$1;

            public final Trees.TreeApi apply(Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> t) {
                Trees.TreeApi treeApi = this.liftT1$15.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$15.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$14.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$13.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$12.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$11.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$10.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$9.apply(t._8());
                Trees.TreeApi treeApi9 = this.liftT9$8.apply(t._9());
                Trees.TreeApi treeApi10 = this.liftT10$7.apply(t._10());
                Trees.TreeApi treeApi11 = this.liftT11$6.apply(t._11());
                Trees.TreeApi treeApi12 = this.liftT12$5.apply(t._12());
                Trees.TreeApi treeApi13 = this.liftT13$4.apply(t._13());
                Trees.TreeApi treeApi14 = this.liftT14$3.apply(t._14());
                Trees.TreeApi treeApi15 = this.liftT15$2.apply(t._15());
                Trees.TreeApi treeApi16 = this.liftT16$1.apply(t._16());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi16).$colon$colon(treeApi15).$colon$colon(treeApi14).$colon$colon(treeApi13).$colon$colon(treeApi12).$colon$colon(treeApi11).$colon$colon(treeApi10).$colon$colon(treeApi9).$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$15 = liftT1$15;
                this.liftT2$15 = liftT2$15;
                this.liftT3$14 = liftT3$14;
                this.liftT4$13 = liftT4$13;
                this.liftT5$12 = liftT5$12;
                this.liftT6$11 = liftT6$11;
                this.liftT7$10 = liftT7$10;
                this.liftT8$9 = liftT8$9;
                this.liftT9$8 = liftT9$8;
                this.liftT10$7 = liftT10$7;
                this.liftT11$6 = liftT11$6;
                this.liftT12$5 = liftT12$5;
                this.liftT13$4 = liftT13$4;
                this.liftT14$3 = liftT14$3;
                this.liftT15$2 = liftT15$2;
                this.liftT16$1 = liftT16$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple17(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8, Liftables.Liftable liftT9, Liftables.Liftable liftT10, Liftables.Liftable liftT11, Liftables.Liftable liftT12, Liftables.Liftable liftT13, Liftables.Liftable liftT14, Liftables.Liftable liftT15, Liftables.Liftable liftT16, Liftables.Liftable liftT17) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15, liftT16, liftT17){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$16;
            private final Liftables.Liftable liftT2$16;
            private final Liftables.Liftable liftT3$15;
            private final Liftables.Liftable liftT4$14;
            private final Liftables.Liftable liftT5$13;
            private final Liftables.Liftable liftT6$12;
            private final Liftables.Liftable liftT7$11;
            private final Liftables.Liftable liftT8$10;
            private final Liftables.Liftable liftT9$9;
            private final Liftables.Liftable liftT10$8;
            private final Liftables.Liftable liftT11$7;
            private final Liftables.Liftable liftT12$6;
            private final Liftables.Liftable liftT13$5;
            private final Liftables.Liftable liftT14$4;
            private final Liftables.Liftable liftT15$3;
            private final Liftables.Liftable liftT16$2;
            private final Liftables.Liftable liftT17$1;

            public final Trees.TreeApi apply(Tuple17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> t) {
                Trees.TreeApi treeApi = this.liftT1$16.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$16.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$15.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$14.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$13.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$12.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$11.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$10.apply(t._8());
                Trees.TreeApi treeApi9 = this.liftT9$9.apply(t._9());
                Trees.TreeApi treeApi10 = this.liftT10$8.apply(t._10());
                Trees.TreeApi treeApi11 = this.liftT11$7.apply(t._11());
                Trees.TreeApi treeApi12 = this.liftT12$6.apply(t._12());
                Trees.TreeApi treeApi13 = this.liftT13$5.apply(t._13());
                Trees.TreeApi treeApi14 = this.liftT14$4.apply(t._14());
                Trees.TreeApi treeApi15 = this.liftT15$3.apply(t._15());
                Trees.TreeApi treeApi16 = this.liftT16$2.apply(t._16());
                Trees.TreeApi treeApi17 = this.liftT17$1.apply(t._17());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi17).$colon$colon(treeApi16).$colon$colon(treeApi15).$colon$colon(treeApi14).$colon$colon(treeApi13).$colon$colon(treeApi12).$colon$colon(treeApi11).$colon$colon(treeApi10).$colon$colon(treeApi9).$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$16 = liftT1$16;
                this.liftT2$16 = liftT2$16;
                this.liftT3$15 = liftT3$15;
                this.liftT4$14 = liftT4$14;
                this.liftT5$13 = liftT5$13;
                this.liftT6$12 = liftT6$12;
                this.liftT7$11 = liftT7$11;
                this.liftT8$10 = liftT8$10;
                this.liftT9$9 = liftT9$9;
                this.liftT10$8 = liftT10$8;
                this.liftT11$7 = liftT11$7;
                this.liftT12$6 = liftT12$6;
                this.liftT13$5 = liftT13$5;
                this.liftT14$4 = liftT14$4;
                this.liftT15$3 = liftT15$3;
                this.liftT16$2 = liftT16$2;
                this.liftT17$1 = liftT17$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple18(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8, Liftables.Liftable liftT9, Liftables.Liftable liftT10, Liftables.Liftable liftT11, Liftables.Liftable liftT12, Liftables.Liftable liftT13, Liftables.Liftable liftT14, Liftables.Liftable liftT15, Liftables.Liftable liftT16, Liftables.Liftable liftT17, Liftables.Liftable liftT18) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15, liftT16, liftT17, liftT18){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$17;
            private final Liftables.Liftable liftT2$17;
            private final Liftables.Liftable liftT3$16;
            private final Liftables.Liftable liftT4$15;
            private final Liftables.Liftable liftT5$14;
            private final Liftables.Liftable liftT6$13;
            private final Liftables.Liftable liftT7$12;
            private final Liftables.Liftable liftT8$11;
            private final Liftables.Liftable liftT9$10;
            private final Liftables.Liftable liftT10$9;
            private final Liftables.Liftable liftT11$8;
            private final Liftables.Liftable liftT12$7;
            private final Liftables.Liftable liftT13$6;
            private final Liftables.Liftable liftT14$5;
            private final Liftables.Liftable liftT15$4;
            private final Liftables.Liftable liftT16$3;
            private final Liftables.Liftable liftT17$2;
            private final Liftables.Liftable liftT18$1;

            public final Trees.TreeApi apply(Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> t) {
                Trees.TreeApi treeApi = this.liftT1$17.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$17.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$16.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$15.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$14.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$13.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$12.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$11.apply(t._8());
                Trees.TreeApi treeApi9 = this.liftT9$10.apply(t._9());
                Trees.TreeApi treeApi10 = this.liftT10$9.apply(t._10());
                Trees.TreeApi treeApi11 = this.liftT11$8.apply(t._11());
                Trees.TreeApi treeApi12 = this.liftT12$7.apply(t._12());
                Trees.TreeApi treeApi13 = this.liftT13$6.apply(t._13());
                Trees.TreeApi treeApi14 = this.liftT14$5.apply(t._14());
                Trees.TreeApi treeApi15 = this.liftT15$4.apply(t._15());
                Trees.TreeApi treeApi16 = this.liftT16$3.apply(t._16());
                Trees.TreeApi treeApi17 = this.liftT17$2.apply(t._17());
                Trees.TreeApi treeApi18 = this.liftT18$1.apply(t._18());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi18).$colon$colon(treeApi17).$colon$colon(treeApi16).$colon$colon(treeApi15).$colon$colon(treeApi14).$colon$colon(treeApi13).$colon$colon(treeApi12).$colon$colon(treeApi11).$colon$colon(treeApi10).$colon$colon(treeApi9).$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$17 = liftT1$17;
                this.liftT2$17 = liftT2$17;
                this.liftT3$16 = liftT3$16;
                this.liftT4$15 = liftT4$15;
                this.liftT5$14 = liftT5$14;
                this.liftT6$13 = liftT6$13;
                this.liftT7$12 = liftT7$12;
                this.liftT8$11 = liftT8$11;
                this.liftT9$10 = liftT9$10;
                this.liftT10$9 = liftT10$9;
                this.liftT11$8 = liftT11$8;
                this.liftT12$7 = liftT12$7;
                this.liftT13$6 = liftT13$6;
                this.liftT14$5 = liftT14$5;
                this.liftT15$4 = liftT15$4;
                this.liftT16$3 = liftT16$3;
                this.liftT17$2 = liftT17$2;
                this.liftT18$1 = liftT18$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple19(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8, Liftables.Liftable liftT9, Liftables.Liftable liftT10, Liftables.Liftable liftT11, Liftables.Liftable liftT12, Liftables.Liftable liftT13, Liftables.Liftable liftT14, Liftables.Liftable liftT15, Liftables.Liftable liftT16, Liftables.Liftable liftT17, Liftables.Liftable liftT18, Liftables.Liftable liftT19) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15, liftT16, liftT17, liftT18, liftT19){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$18;
            private final Liftables.Liftable liftT2$18;
            private final Liftables.Liftable liftT3$17;
            private final Liftables.Liftable liftT4$16;
            private final Liftables.Liftable liftT5$15;
            private final Liftables.Liftable liftT6$14;
            private final Liftables.Liftable liftT7$13;
            private final Liftables.Liftable liftT8$12;
            private final Liftables.Liftable liftT9$11;
            private final Liftables.Liftable liftT10$10;
            private final Liftables.Liftable liftT11$9;
            private final Liftables.Liftable liftT12$8;
            private final Liftables.Liftable liftT13$7;
            private final Liftables.Liftable liftT14$6;
            private final Liftables.Liftable liftT15$5;
            private final Liftables.Liftable liftT16$4;
            private final Liftables.Liftable liftT17$3;
            private final Liftables.Liftable liftT18$2;
            private final Liftables.Liftable liftT19$1;

            public final Trees.TreeApi apply(Tuple19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> t) {
                Trees.TreeApi treeApi = this.liftT1$18.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$18.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$17.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$16.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$15.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$14.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$13.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$12.apply(t._8());
                Trees.TreeApi treeApi9 = this.liftT9$11.apply(t._9());
                Trees.TreeApi treeApi10 = this.liftT10$10.apply(t._10());
                Trees.TreeApi treeApi11 = this.liftT11$9.apply(t._11());
                Trees.TreeApi treeApi12 = this.liftT12$8.apply(t._12());
                Trees.TreeApi treeApi13 = this.liftT13$7.apply(t._13());
                Trees.TreeApi treeApi14 = this.liftT14$6.apply(t._14());
                Trees.TreeApi treeApi15 = this.liftT15$5.apply(t._15());
                Trees.TreeApi treeApi16 = this.liftT16$4.apply(t._16());
                Trees.TreeApi treeApi17 = this.liftT17$3.apply(t._17());
                Trees.TreeApi treeApi18 = this.liftT18$2.apply(t._18());
                Trees.TreeApi treeApi19 = this.liftT19$1.apply(t._19());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi19).$colon$colon(treeApi18).$colon$colon(treeApi17).$colon$colon(treeApi16).$colon$colon(treeApi15).$colon$colon(treeApi14).$colon$colon(treeApi13).$colon$colon(treeApi12).$colon$colon(treeApi11).$colon$colon(treeApi10).$colon$colon(treeApi9).$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$18 = liftT1$18;
                this.liftT2$18 = liftT2$18;
                this.liftT3$17 = liftT3$17;
                this.liftT4$16 = liftT4$16;
                this.liftT5$15 = liftT5$15;
                this.liftT6$14 = liftT6$14;
                this.liftT7$13 = liftT7$13;
                this.liftT8$12 = liftT8$12;
                this.liftT9$11 = liftT9$11;
                this.liftT10$10 = liftT10$10;
                this.liftT11$9 = liftT11$9;
                this.liftT12$8 = liftT12$8;
                this.liftT13$7 = liftT13$7;
                this.liftT14$6 = liftT14$6;
                this.liftT15$5 = liftT15$5;
                this.liftT16$4 = liftT16$4;
                this.liftT17$3 = liftT17$3;
                this.liftT18$2 = liftT18$2;
                this.liftT19$1 = liftT19$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple20(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8, Liftables.Liftable liftT9, Liftables.Liftable liftT10, Liftables.Liftable liftT11, Liftables.Liftable liftT12, Liftables.Liftable liftT13, Liftables.Liftable liftT14, Liftables.Liftable liftT15, Liftables.Liftable liftT16, Liftables.Liftable liftT17, Liftables.Liftable liftT18, Liftables.Liftable liftT19, Liftables.Liftable liftT20) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15, liftT16, liftT17, liftT18, liftT19, liftT20){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$19;
            private final Liftables.Liftable liftT2$19;
            private final Liftables.Liftable liftT3$18;
            private final Liftables.Liftable liftT4$17;
            private final Liftables.Liftable liftT5$16;
            private final Liftables.Liftable liftT6$15;
            private final Liftables.Liftable liftT7$14;
            private final Liftables.Liftable liftT8$13;
            private final Liftables.Liftable liftT9$12;
            private final Liftables.Liftable liftT10$11;
            private final Liftables.Liftable liftT11$10;
            private final Liftables.Liftable liftT12$9;
            private final Liftables.Liftable liftT13$8;
            private final Liftables.Liftable liftT14$7;
            private final Liftables.Liftable liftT15$6;
            private final Liftables.Liftable liftT16$5;
            private final Liftables.Liftable liftT17$4;
            private final Liftables.Liftable liftT18$3;
            private final Liftables.Liftable liftT19$2;
            private final Liftables.Liftable liftT20$1;

            public final Trees.TreeApi apply(Tuple20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> t) {
                Trees.TreeApi treeApi = this.liftT1$19.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$19.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$18.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$17.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$16.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$15.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$14.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$13.apply(t._8());
                Trees.TreeApi treeApi9 = this.liftT9$12.apply(t._9());
                Trees.TreeApi treeApi10 = this.liftT10$11.apply(t._10());
                Trees.TreeApi treeApi11 = this.liftT11$10.apply(t._11());
                Trees.TreeApi treeApi12 = this.liftT12$9.apply(t._12());
                Trees.TreeApi treeApi13 = this.liftT13$8.apply(t._13());
                Trees.TreeApi treeApi14 = this.liftT14$7.apply(t._14());
                Trees.TreeApi treeApi15 = this.liftT15$6.apply(t._15());
                Trees.TreeApi treeApi16 = this.liftT16$5.apply(t._16());
                Trees.TreeApi treeApi17 = this.liftT17$4.apply(t._17());
                Trees.TreeApi treeApi18 = this.liftT18$3.apply(t._18());
                Trees.TreeApi treeApi19 = this.liftT19$2.apply(t._19());
                Trees.TreeApi treeApi20 = this.liftT20$1.apply(t._20());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi20).$colon$colon(treeApi19).$colon$colon(treeApi18).$colon$colon(treeApi17).$colon$colon(treeApi16).$colon$colon(treeApi15).$colon$colon(treeApi14).$colon$colon(treeApi13).$colon$colon(treeApi12).$colon$colon(treeApi11).$colon$colon(treeApi10).$colon$colon(treeApi9).$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$19 = liftT1$19;
                this.liftT2$19 = liftT2$19;
                this.liftT3$18 = liftT3$18;
                this.liftT4$17 = liftT4$17;
                this.liftT5$16 = liftT5$16;
                this.liftT6$15 = liftT6$15;
                this.liftT7$14 = liftT7$14;
                this.liftT8$13 = liftT8$13;
                this.liftT9$12 = liftT9$12;
                this.liftT10$11 = liftT10$11;
                this.liftT11$10 = liftT11$10;
                this.liftT12$9 = liftT12$9;
                this.liftT13$8 = liftT13$8;
                this.liftT14$7 = liftT14$7;
                this.liftT15$6 = liftT15$6;
                this.liftT16$5 = liftT16$5;
                this.liftT17$4 = liftT17$4;
                this.liftT18$3 = liftT18$3;
                this.liftT19$2 = liftT19$2;
                this.liftT20$1 = liftT20$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple21(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8, Liftables.Liftable liftT9, Liftables.Liftable liftT10, Liftables.Liftable liftT11, Liftables.Liftable liftT12, Liftables.Liftable liftT13, Liftables.Liftable liftT14, Liftables.Liftable liftT15, Liftables.Liftable liftT16, Liftables.Liftable liftT17, Liftables.Liftable liftT18, Liftables.Liftable liftT19, Liftables.Liftable liftT20, Liftables.Liftable liftT21) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15, liftT16, liftT17, liftT18, liftT19, liftT20, liftT21){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$20;
            private final Liftables.Liftable liftT2$20;
            private final Liftables.Liftable liftT3$19;
            private final Liftables.Liftable liftT4$18;
            private final Liftables.Liftable liftT5$17;
            private final Liftables.Liftable liftT6$16;
            private final Liftables.Liftable liftT7$15;
            private final Liftables.Liftable liftT8$14;
            private final Liftables.Liftable liftT9$13;
            private final Liftables.Liftable liftT10$12;
            private final Liftables.Liftable liftT11$11;
            private final Liftables.Liftable liftT12$10;
            private final Liftables.Liftable liftT13$9;
            private final Liftables.Liftable liftT14$8;
            private final Liftables.Liftable liftT15$7;
            private final Liftables.Liftable liftT16$6;
            private final Liftables.Liftable liftT17$5;
            private final Liftables.Liftable liftT18$4;
            private final Liftables.Liftable liftT19$3;
            private final Liftables.Liftable liftT20$2;
            private final Liftables.Liftable liftT21$1;

            public final Trees.TreeApi apply(Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> t) {
                Trees.TreeApi treeApi = this.liftT1$20.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$20.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$19.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$18.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$17.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$16.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$15.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$14.apply(t._8());
                Trees.TreeApi treeApi9 = this.liftT9$13.apply(t._9());
                Trees.TreeApi treeApi10 = this.liftT10$12.apply(t._10());
                Trees.TreeApi treeApi11 = this.liftT11$11.apply(t._11());
                Trees.TreeApi treeApi12 = this.liftT12$10.apply(t._12());
                Trees.TreeApi treeApi13 = this.liftT13$9.apply(t._13());
                Trees.TreeApi treeApi14 = this.liftT14$8.apply(t._14());
                Trees.TreeApi treeApi15 = this.liftT15$7.apply(t._15());
                Trees.TreeApi treeApi16 = this.liftT16$6.apply(t._16());
                Trees.TreeApi treeApi17 = this.liftT17$5.apply(t._17());
                Trees.TreeApi treeApi18 = this.liftT18$4.apply(t._18());
                Trees.TreeApi treeApi19 = this.liftT19$3.apply(t._19());
                Trees.TreeApi treeApi20 = this.liftT20$2.apply(t._20());
                Trees.TreeApi treeApi21 = this.liftT21$1.apply(t._21());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi21).$colon$colon(treeApi20).$colon$colon(treeApi19).$colon$colon(treeApi18).$colon$colon(treeApi17).$colon$colon(treeApi16).$colon$colon(treeApi15).$colon$colon(treeApi14).$colon$colon(treeApi13).$colon$colon(treeApi12).$colon$colon(treeApi11).$colon$colon(treeApi10).$colon$colon(treeApi9).$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$20 = liftT1$20;
                this.liftT2$20 = liftT2$20;
                this.liftT3$19 = liftT3$19;
                this.liftT4$18 = liftT4$18;
                this.liftT5$17 = liftT5$17;
                this.liftT6$16 = liftT6$16;
                this.liftT7$15 = liftT7$15;
                this.liftT8$14 = liftT8$14;
                this.liftT9$13 = liftT9$13;
                this.liftT10$12 = liftT10$12;
                this.liftT11$11 = liftT11$11;
                this.liftT12$10 = liftT12$10;
                this.liftT13$9 = liftT13$9;
                this.liftT14$8 = liftT14$8;
                this.liftT15$7 = liftT15$7;
                this.liftT16$6 = liftT16$6;
                this.liftT17$5 = liftT17$5;
                this.liftT18$4 = liftT18$4;
                this.liftT19$3 = liftT19$3;
                this.liftT20$2 = liftT20$2;
                this.liftT21$1 = liftT21$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static Liftables.Liftable liftTuple22(StandardLiftables.StandardLiftableInstances $this, Liftables.Liftable liftT1, Liftables.Liftable liftT2, Liftables.Liftable liftT3, Liftables.Liftable liftT4, Liftables.Liftable liftT5, Liftables.Liftable liftT6, Liftables.Liftable liftT7, Liftables.Liftable liftT8, Liftables.Liftable liftT9, Liftables.Liftable liftT10, Liftables.Liftable liftT11, Liftables.Liftable liftT12, Liftables.Liftable liftT13, Liftables.Liftable liftT14, Liftables.Liftable liftT15, Liftables.Liftable liftT16, Liftables.Liftable liftT17, Liftables.Liftable liftT18, Liftables.Liftable liftT19, Liftables.Liftable liftT20, Liftables.Liftable liftT21, Liftables.Liftable liftT22) {
        Serializable serializable = new Serializable($this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15, liftT16, liftT17, liftT18, liftT19, liftT20, liftT21, liftT22){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardLiftableInstances $outer;
            private final Liftables.Liftable liftT1$21;
            private final Liftables.Liftable liftT2$21;
            private final Liftables.Liftable liftT3$20;
            private final Liftables.Liftable liftT4$19;
            private final Liftables.Liftable liftT5$18;
            private final Liftables.Liftable liftT6$17;
            private final Liftables.Liftable liftT7$16;
            private final Liftables.Liftable liftT8$15;
            private final Liftables.Liftable liftT9$14;
            private final Liftables.Liftable liftT10$13;
            private final Liftables.Liftable liftT11$12;
            private final Liftables.Liftable liftT12$11;
            private final Liftables.Liftable liftT13$10;
            private final Liftables.Liftable liftT14$9;
            private final Liftables.Liftable liftT15$8;
            private final Liftables.Liftable liftT16$7;
            private final Liftables.Liftable liftT17$6;
            private final Liftables.Liftable liftT18$5;
            private final Liftables.Liftable liftT19$4;
            private final Liftables.Liftable liftT20$3;
            private final Liftables.Liftable liftT21$2;
            private final Liftables.Liftable liftT22$1;

            public final Trees.TreeApi apply(Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> t) {
                Trees.TreeApi treeApi = this.liftT1$21.apply(t._1());
                Trees.TreeApi treeApi2 = this.liftT2$21.apply(t._2());
                Trees.TreeApi treeApi3 = this.liftT3$20.apply(t._3());
                Trees.TreeApi treeApi4 = this.liftT4$19.apply(t._4());
                Trees.TreeApi treeApi5 = this.liftT5$18.apply(t._5());
                Trees.TreeApi treeApi6 = this.liftT6$17.apply(t._6());
                Trees.TreeApi treeApi7 = this.liftT7$16.apply(t._7());
                Trees.TreeApi treeApi8 = this.liftT8$15.apply(t._8());
                Trees.TreeApi treeApi9 = this.liftT9$14.apply(t._9());
                Trees.TreeApi treeApi10 = this.liftT10$13.apply(t._10());
                Trees.TreeApi treeApi11 = this.liftT11$12.apply(t._11());
                Trees.TreeApi treeApi12 = this.liftT12$11.apply(t._12());
                Trees.TreeApi treeApi13 = this.liftT13$10.apply(t._13());
                Trees.TreeApi treeApi14 = this.liftT14$9.apply(t._14());
                Trees.TreeApi treeApi15 = this.liftT15$8.apply(t._15());
                Trees.TreeApi treeApi16 = this.liftT16$7.apply(t._16());
                Trees.TreeApi treeApi17 = this.liftT17$6.apply(t._17());
                Trees.TreeApi treeApi18 = this.liftT18$5.apply(t._18());
                Trees.TreeApi treeApi19 = this.liftT19$4.apply(t._19());
                Trees.TreeApi treeApi20 = this.liftT20$3.apply(t._20());
                Trees.TreeApi treeApi21 = this.liftT21$2.apply(t._21());
                Trees.TreeApi treeApi22 = this.liftT22$1.apply(t._22());
                return ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().apply(Nil$.MODULE$.$colon$colon(treeApi22).$colon$colon(treeApi21).$colon$colon(treeApi20).$colon$colon(treeApi19).$colon$colon(treeApi18).$colon$colon(treeApi17).$colon$colon(treeApi16).$colon$colon(treeApi15).$colon$colon(treeApi14).$colon$colon(treeApi13).$colon$colon(treeApi12).$colon$colon(treeApi11).$colon$colon(treeApi10).$colon$colon(treeApi9).$colon$colon(treeApi8).$colon$colon(treeApi7).$colon$colon(treeApi6).$colon$colon(treeApi5).$colon$colon(treeApi4).$colon$colon(treeApi3).$colon$colon(treeApi2).$colon$colon(treeApi));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.liftT1$21 = liftT1$21;
                this.liftT2$21 = liftT2$21;
                this.liftT3$20 = liftT3$20;
                this.liftT4$19 = liftT4$19;
                this.liftT5$18 = liftT5$18;
                this.liftT6$17 = liftT6$17;
                this.liftT7$16 = liftT7$16;
                this.liftT8$15 = liftT8$15;
                this.liftT9$14 = liftT9$14;
                this.liftT10$13 = liftT10$13;
                this.liftT11$12 = liftT11$12;
                this.liftT12$11 = liftT12$11;
                this.liftT13$10 = liftT13$10;
                this.liftT14$9 = liftT14$9;
                this.liftT15$8 = liftT15$8;
                this.liftT16$7 = liftT16$7;
                this.liftT17$6 = liftT17$6;
                this.liftT18$5 = liftT18$5;
                this.liftT19$4 = liftT19$4;
                this.liftT20$3 = liftT20$3;
                this.liftT21$2 = liftT21$2;
                this.liftT22$1 = liftT22$1;
            }
        };
        Liftables$Liftable$ liftables$Liftable$ = ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer())).Liftable();
        return new /* invalid duplicate definition of identical inner class */;
    }

    public static void $init$(StandardLiftables.StandardLiftableInstances $this) {
    }
}

