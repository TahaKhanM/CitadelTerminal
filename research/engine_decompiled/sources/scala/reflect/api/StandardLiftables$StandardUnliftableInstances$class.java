/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.Function1;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Symbol;
import scala.Symbol$;
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
import scala.collection.LinearSeqOptimized;
import scala.collection.Seq;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.api.Constants;
import scala.reflect.api.ImplicitTags;
import scala.reflect.api.Internals;
import scala.reflect.api.Liftables;
import scala.reflect.api.Names;
import scala.reflect.api.StandardLiftables;
import scala.reflect.api.Trees;
import scala.runtime.BoxedUnit;

public abstract class StandardLiftables$StandardUnliftableInstances$class {
    private static Liftables.Unliftable unliftPrimitive(StandardLiftables.StandardUnliftableInstances $this, ClassTag evidence$14, ClassTag evidence$15) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, evidence$14, evidence$15){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final ClassTag evidence$14$1;
            private final ClassTag evidence$15$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                void var13_15;
                block2: {
                    Option<Object> option;
                    block3: {
                        Option<Constants.ConstantApi> option2;
                        Option<Constants.ConstantApi> option3;
                        Option<Trees.LiteralApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).LiteralTag().unapply(x1);
                        if (option4.isEmpty() || (option3 = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Literal().unapply(option4.get())).isEmpty() || (option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).ConstantTag().unapply(option3.get())).isEmpty() || (option = ((Constants)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Constant().unapply(option2.get())).isEmpty()) break block2;
                        Class<?> clazz = option.get().getClass();
                        ClassTag classTag = this.evidence$15$1;
                        Predef$ predef$ = Predef$.MODULE$;
                        Class<?> clazz2 = classTag.runtimeClass();
                        if (!(clazz == null ? clazz2 != null : !clazz.equals(clazz2))) break block3;
                        Class<?> clazz3 = option.get().getClass();
                        ClassTag classTag2 = this.evidence$14$1;
                        Predef$ predef$2 = Predef$.MODULE$;
                        Class<?> clazz4 = classTag2.runtimeClass();
                        if (clazz3 != null ? !clazz3.equals(clazz4) : clazz4 != null) break block2;
                    }
                    Object object = option.get();
                    return var13_15;
                }
                B1 B1 = function1.apply(x1);
                return var13_15;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x1) {
                Option<Trees.LiteralApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).LiteralTag().unapply(x1);
                if (option.isEmpty()) return false;
                Option<Constants.ConstantApi> option2 = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Literal().unapply(option.get());
                if (option2.isEmpty()) return false;
                Option<Constants.ConstantApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).ConstantTag().unapply(option2.get());
                if (option3.isEmpty()) return false;
                Option<Object> option4 = ((Constants)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Constant().unapply(option3.get());
                if (option4.isEmpty()) return false;
                Class<?> clazz = option4.get().getClass();
                ClassTag classTag = this.evidence$15$1;
                Predef$ predef$ = Predef$.MODULE$;
                Class<?> clazz2 = classTag.runtimeClass();
                if (clazz == null) {
                    if (clazz2 == null) return true;
                } else if (clazz.equals(clazz2)) return true;
                Class<?> clazz3 = option4.get().getClass();
                ClassTag classTag2 = this.evidence$14$1;
                Predef$ predef$2 = Predef$.MODULE$;
                Class<?> clazz4 = classTag2.runtimeClass();
                if (clazz3 != null) {
                    if (!clazz3.equals(clazz4)) return false;
                    return true;
                }
                if (clazz4 == null) return true;
                return false;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$14$1 = evidence$14$1;
                this.evidence$15$1 = evidence$15$1;
            }
        });
    }

    public static Liftables.Unliftable unliftByte(StandardLiftables.StandardUnliftableInstances $this) {
        return StandardLiftables$StandardUnliftableInstances$class.unliftPrimitive($this, ClassTag$.MODULE$.Byte(), ClassTag$.MODULE$.apply(Byte.class));
    }

    public static Liftables.Unliftable unliftShort(StandardLiftables.StandardUnliftableInstances $this) {
        return StandardLiftables$StandardUnliftableInstances$class.unliftPrimitive($this, ClassTag$.MODULE$.Short(), ClassTag$.MODULE$.apply(Short.class));
    }

    public static Liftables.Unliftable unliftChar(StandardLiftables.StandardUnliftableInstances $this) {
        return StandardLiftables$StandardUnliftableInstances$class.unliftPrimitive($this, ClassTag$.MODULE$.Char(), ClassTag$.MODULE$.apply(Character.class));
    }

    public static Liftables.Unliftable unliftInt(StandardLiftables.StandardUnliftableInstances $this) {
        return StandardLiftables$StandardUnliftableInstances$class.unliftPrimitive($this, ClassTag$.MODULE$.Int(), ClassTag$.MODULE$.apply(Integer.class));
    }

    public static Liftables.Unliftable unliftLong(StandardLiftables.StandardUnliftableInstances $this) {
        return StandardLiftables$StandardUnliftableInstances$class.unliftPrimitive($this, ClassTag$.MODULE$.Long(), ClassTag$.MODULE$.apply(Long.class));
    }

    public static Liftables.Unliftable unliftFloat(StandardLiftables.StandardUnliftableInstances $this) {
        return StandardLiftables$StandardUnliftableInstances$class.unliftPrimitive($this, ClassTag$.MODULE$.Float(), ClassTag$.MODULE$.apply(Float.class));
    }

    public static Liftables.Unliftable unliftDouble(StandardLiftables.StandardUnliftableInstances $this) {
        return StandardLiftables$StandardUnliftableInstances$class.unliftPrimitive($this, ClassTag$.MODULE$.Double(), ClassTag$.MODULE$.apply(Double.class));
    }

    public static Liftables.Unliftable unliftBoolean(StandardLiftables.StandardUnliftableInstances $this) {
        return StandardLiftables$StandardUnliftableInstances$class.unliftPrimitive($this, ClassTag$.MODULE$.Boolean(), ClassTag$.MODULE$.apply(Boolean.class));
    }

    public static Liftables.Unliftable unliftUnit(StandardLiftables.StandardUnliftableInstances $this) {
        return StandardLiftables$StandardUnliftableInstances$class.unliftPrimitive($this, ClassTag$.MODULE$.Unit(), ClassTag$.MODULE$.apply(BoxedUnit.class));
    }

    public static Liftables.Unliftable unliftString(StandardLiftables.StandardUnliftableInstances $this) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;

            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x2, Function1<A1, B1> function1) {
                Object object;
                Option<Object> option;
                Option<Constants.ConstantApi> option2;
                Option<Constants.ConstantApi> option3;
                Option<Trees.LiteralApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).LiteralTag().unapply(x2);
                if (!(option4.isEmpty() || (option3 = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Literal().unapply(option4.get())).isEmpty() || (option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).ConstantTag().unapply(option3.get())).isEmpty() || (option = ((Constants)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Constant().unapply(option2.get())).isEmpty() || !(option.get() instanceof String))) {
                    String string2 = (String)option.get();
                    object = string2;
                } else {
                    object = function1.apply(x2);
                }
                return object;
            }

            public final boolean isDefinedAt(Trees.TreeApi x2) {
                Option<Object> option;
                Option<Constants.ConstantApi> option2;
                Option<Constants.ConstantApi> option3;
                Option<Trees.LiteralApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).LiteralTag().unapply(x2);
                boolean bl = !option4.isEmpty() && !(option3 = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Literal().unapply(option4.get())).isEmpty() && !(option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).ConstantTag().unapply(option3.get())).isEmpty() && !(option = ((Constants)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Constant().unapply(option2.get())).isEmpty() && option.get() instanceof String;
                return bl;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Liftables.Unliftable unliftScalaSymbol(StandardLiftables.StandardUnliftableInstances $this) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x3, Function1<A1, B1> function1) {
                void var15_17;
                Option<Names.NameApi> option;
                Option<Trees.TreeApi> option2;
                Option<Tuple2<Trees.TreeApi, List<Trees.TreeApi>>> option3;
                Option<Trees.ApplyApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).ApplyTag().unapply(x3);
                if (!(option4.isEmpty() || (option3 = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Apply().unapply(option4.get())).isEmpty() || (option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(option3.get()._1())).isEmpty() || (option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().ScalaDot().unapply(option2.get())).isEmpty())) {
                    Names.TermNameApi termNameApi = this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().Symbol();
                    Names.NameApi nameApi = option.get();
                    if (!(termNameApi != null ? !termNameApi.equals(nameApi) : nameApi != null)) {
                        Some<Seq> some = List$.MODULE$.unapplySeq((Seq)option3.get()._2());
                        if (!some.isEmpty() && some.get() != null && ((LinearSeqOptimized)((Object)some.get())).lengthCompare(1) == 0) {
                            Option<Object> option5;
                            Option<Constants.ConstantApi> option6;
                            Option<Constants.ConstantApi> option7;
                            A a = ((LinearSeqOptimized)((Object)some.get())).apply(0);
                            Option<Trees.LiteralApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).LiteralTag().unapply(a);
                            if (!(option8.isEmpty() || (option7 = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Literal().unapply(option8.get())).isEmpty() || (option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).ConstantTag().unapply(option7.get())).isEmpty() || (option5 = ((Constants)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Constant().unapply(option6.get())).isEmpty() || !(option5.get() instanceof String))) {
                                String string2 = (String)option5.get();
                                Symbol symbol = Symbol$.MODULE$.apply(string2);
                                return var15_17;
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x3);
                return var15_17;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x3) {
                Some<Seq> some;
                Option<Trees.ApplyApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).ApplyTag().unapply(x3);
                if (option.isEmpty()) return false;
                Option<Tuple2<Trees.TreeApi, List<Trees.TreeApi>>> option2 = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Apply().unapply(option.get());
                if (option2.isEmpty()) return false;
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(option2.get()._1());
                if (option3.isEmpty()) return false;
                Option<Names.NameApi> option4 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().ScalaDot().unapply(option3.get());
                if (option4.isEmpty()) return false;
                Names.TermNameApi termNameApi = this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().Symbol();
                Names.NameApi nameApi = option4.get();
                if (termNameApi == null) {
                    if (nameApi != null) {
                        return false;
                    }
                } else if (!termNameApi.equals(nameApi)) return false;
                if ((some = List$.MODULE$.unapplySeq((Seq)option2.get()._2())).isEmpty()) return false;
                if (some.get() == null) return false;
                if (((LinearSeqOptimized)((Object)some.get())).lengthCompare(1) != 0) return false;
                A a = ((LinearSeqOptimized)((Object)some.get())).apply(0);
                Option<Trees.LiteralApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).LiteralTag().unapply(a);
                if (option5.isEmpty()) return false;
                Option<Constants.ConstantApi> option6 = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Literal().unapply(option5.get());
                if (option6.isEmpty()) return false;
                Option<Constants.ConstantApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).ConstantTag().unapply(option6.get());
                if (option7.isEmpty()) return false;
                Option<Object> option8 = ((Constants)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Constant().unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!(option8.get() instanceof String)) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Liftables.Unliftable unliftName(StandardLiftables.StandardUnliftableInstances $this, ClassTag evidence$16) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, evidence$16){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final ClassTag evidence$16$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x4, Function1<A1, B1> function1) {
                Option<Names.NameApi> option;
                Option<Trees.IdentApi> option2;
                Option<T> option3;
                Option<Tuple2<Names.NameApi, Trees.TreeApi>> option4;
                void var12_9;
                Option<T> option5;
                Option<Names.NameApi> option6;
                Option<Trees.IdentApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).IdentTag().unapply(x4);
                if (!(option7.isEmpty() || (option6 = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Ident().unapply(option7.get())).isEmpty() || (option5 = this.evidence$16$1.unapply(option6.get())).isEmpty() || option5.get() == null)) {
                    Names.NameApi nameApi = option6.get();
                    return var12_9;
                }
                Option<Trees.BindApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).BindTag().unapply(x4);
                if (!(option8.isEmpty() || (option4 = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Bind().unapply(option8.get())).isEmpty() || (option3 = this.evidence$16$1.unapply(option4.get()._1())).isEmpty() || option3.get() == null || (option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).IdentTag().unapply(option4.get()._2())).isEmpty() || (option = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Ident().unapply(option2.get())).isEmpty())) {
                    Names.TermNameApi termNameApi = this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().WILDCARD();
                    Names.NameApi nameApi = option.get();
                    if (!(termNameApi != null ? !termNameApi.equals(nameApi) : nameApi != null)) {
                        Names.NameApi nameApi2 = option4.get()._1();
                        return var12_9;
                    }
                }
                B1 B1 = function1.apply(x4);
                return var12_9;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x4) {
                Option<T> option;
                Option<Names.NameApi> option2;
                Option<Trees.IdentApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).IdentTag().unapply(x4);
                if (!(option3.isEmpty() || (option2 = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Ident().unapply(option3.get())).isEmpty() || (option = this.evidence$16$1.unapply(option2.get())).isEmpty() || option.get() == null)) {
                    return true;
                }
                Option<Trees.BindApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).BindTag().unapply(x4);
                if (option4.isEmpty()) return false;
                Option<Tuple2<Names.NameApi, Trees.TreeApi>> option5 = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Bind().unapply(option4.get());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.evidence$16$1.unapply(option5.get()._1());
                if (option6.isEmpty()) return false;
                if (option6.get() == null) return false;
                Option<Trees.IdentApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).IdentTag().unapply(option5.get()._2());
                if (option7.isEmpty()) return false;
                Option<Names.NameApi> option8 = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Ident().unapply(option7.get());
                if (option8.isEmpty()) return false;
                Names.TermNameApi termNameApi = this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer().scala$reflect$api$StandardLiftables$$stdnme().WILDCARD();
                Names.NameApi nameApi = option8.get();
                if (termNameApi != null) {
                    if (!termNameApi.equals(nameApi)) return false;
                    return true;
                }
                if (nameApi == null) return true;
                return false;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$16$1 = evidence$16$1;
            }
        });
    }

    public static Liftables.Unliftable unliftType(StandardLiftables.StandardUnliftableInstances $this) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;

            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x5, Function1<A1, B1> function1) {
                Option<Trees.TypeTreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TypeTreeTag().unapply(x5);
                Object object = !option.isEmpty() && option.get() != null && x5.tpe() != null ? x5.tpe() : function1.apply(x5);
                return object;
            }

            public final boolean isDefinedAt(Trees.TreeApi x5) {
                Option<Trees.TypeTreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TypeTreeTag().unapply(x5);
                boolean bl = !option.isEmpty() && option.get() != null && x5.tpe() != null;
                return bl;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Liftables.Unliftable unliftConstant(StandardLiftables.StandardUnliftableInstances $this) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;

            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x6, Function1<A1, B1> function1) {
                Option<Constants.ConstantApi> option;
                Option<Trees.LiteralApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).LiteralTag().unapply(x6);
                Object object = option2.isEmpty() || (option = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Literal().unapply(option2.get())).isEmpty() ? function1.apply(x6) : option.get();
                return object;
            }

            public final boolean isDefinedAt(Trees.TreeApi x6) {
                Option<Constants.ConstantApi> option;
                Option<Trees.LiteralApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).LiteralTag().unapply(x6);
                boolean bl = !option2.isEmpty() && !(option = ((Trees)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Literal().unapply(option2.get())).isEmpty();
                return bl;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple2(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$1;
            private final Liftables.Unliftable UnliftT2$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x7, Function1<A1, B1> function1) {
                void var11_13;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x7);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$1.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$1.unapply(option6.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon2.tl$1())) {
                            Tuple2<T, T> tuple2 = new Tuple2<T, T>(option3.get(), option5.get());
                            return var11_13;
                        }
                    }
                }
                B1 B1 = function1.apply(x7);
                return var11_13;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x7) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x7);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$1.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$1.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon2.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$1 = UnliftT1$1;
                this.UnliftT2$1 = UnliftT2$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple3(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$2;
            private final Liftables.Unliftable UnliftT2$2;
            private final Liftables.Unliftable UnliftT3$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x8, Function1<A1, B1> function1) {
                void var14_16;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x8);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$2.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$2.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$1.unapply(option8.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon3.tl$1())) {
                                Tuple3<T, T, T> tuple3 = new Tuple3<T, T, T>(option3.get(), option5.get(), option7.get());
                                return var14_16;
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x8);
                return var14_16;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x8) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x8);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$2.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$2.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$1.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon3.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$2 = UnliftT1$2;
                this.UnliftT2$2 = UnliftT2$2;
                this.UnliftT3$1 = UnliftT3$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple4(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$3;
            private final Liftables.Unliftable UnliftT2$3;
            private final Liftables.Unliftable UnliftT3$2;
            private final Liftables.Unliftable UnliftT4$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x9, Function1<A1, B1> function1) {
                void var17_19;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x9);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$3.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$3.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$2.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$1.unapply(option10.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon4.tl$1())) {
                                    Tuple4<T, T, T, T> tuple4 = new Tuple4<T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get());
                                    return var17_19;
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x9);
                return var17_19;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x9) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x9);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$3.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$3.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$2.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$1.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon4.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$3 = UnliftT1$3;
                this.UnliftT2$3 = UnliftT2$3;
                this.UnliftT3$2 = UnliftT3$2;
                this.UnliftT4$1 = UnliftT4$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple5(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$4;
            private final Liftables.Unliftable UnliftT2$4;
            private final Liftables.Unliftable UnliftT3$3;
            private final Liftables.Unliftable UnliftT4$2;
            private final Liftables.Unliftable UnliftT5$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x10, Function1<A1, B1> function1) {
                void var20_22;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x10);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$4.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$4.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$3.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$2.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$1.unapply(option12.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon5.tl$1())) {
                                        Tuple5<T, T, T, T, T> tuple5 = new Tuple5<T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get());
                                        return var20_22;
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x10);
                return var20_22;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x10) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x10);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$4.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$4.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$3.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$2.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$1.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon5.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$4 = UnliftT1$4;
                this.UnliftT2$4 = UnliftT2$4;
                this.UnliftT3$3 = UnliftT3$3;
                this.UnliftT4$2 = UnliftT4$2;
                this.UnliftT5$1 = UnliftT5$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple6(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$5;
            private final Liftables.Unliftable UnliftT2$5;
            private final Liftables.Unliftable UnliftT3$4;
            private final Liftables.Unliftable UnliftT4$3;
            private final Liftables.Unliftable UnliftT5$2;
            private final Liftables.Unliftable UnliftT6$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x11, Function1<A1, B1> function1) {
                void var23_25;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x11);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$5.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$5.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$4.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$3.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$2.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$1.unapply(option14.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon6.tl$1())) {
                                            Tuple6<T, T, T, T, T, T> tuple6 = new Tuple6<T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get());
                                            return var23_25;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x11);
                return var23_25;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x11) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x11);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$5.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$5.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$4.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$3.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$2.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$1.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon6.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$5 = UnliftT1$5;
                this.UnliftT2$5 = UnliftT2$5;
                this.UnliftT3$4 = UnliftT3$4;
                this.UnliftT4$3 = UnliftT4$3;
                this.UnliftT5$2 = UnliftT5$2;
                this.UnliftT6$1 = UnliftT6$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple7(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$6;
            private final Liftables.Unliftable UnliftT2$6;
            private final Liftables.Unliftable UnliftT3$5;
            private final Liftables.Unliftable UnliftT4$4;
            private final Liftables.Unliftable UnliftT5$3;
            private final Liftables.Unliftable UnliftT6$2;
            private final Liftables.Unliftable UnliftT7$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x12, Function1<A1, B1> function1) {
                void var26_28;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x12);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$6.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$6.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$5.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$4.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$3.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$2.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$1.unapply(option16.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon7.tl$1())) {
                                                Tuple7<T, T, T, T, T, T, T> tuple7 = new Tuple7<T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get());
                                                return var26_28;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x12);
                return var26_28;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x12) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x12);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$6.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$6.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$5.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$4.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$3.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$2.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$1.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon7.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$6 = UnliftT1$6;
                this.UnliftT2$6 = UnliftT2$6;
                this.UnliftT3$5 = UnliftT3$5;
                this.UnliftT4$4 = UnliftT4$4;
                this.UnliftT5$3 = UnliftT5$3;
                this.UnliftT6$2 = UnliftT6$2;
                this.UnliftT7$1 = UnliftT7$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple8(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$7;
            private final Liftables.Unliftable UnliftT2$7;
            private final Liftables.Unliftable UnliftT3$6;
            private final Liftables.Unliftable UnliftT4$5;
            private final Liftables.Unliftable UnliftT5$4;
            private final Liftables.Unliftable UnliftT6$3;
            private final Liftables.Unliftable UnliftT7$2;
            private final Liftables.Unliftable UnliftT8$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x13, Function1<A1, B1> function1) {
                void var29_31;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x13);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$7.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$7.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$6.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$5.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$4.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$3.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$2.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$1.unapply(option18.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon8.tl$1())) {
                                                    Tuple8<T, T, T, T, T, T, T, T> tuple8 = new Tuple8<T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get());
                                                    return var29_31;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x13);
                return var29_31;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x13) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x13);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$7.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$7.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$6.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$5.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$4.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$3.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$2.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$1.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon8.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$7 = UnliftT1$7;
                this.UnliftT2$7 = UnliftT2$7;
                this.UnliftT3$6 = UnliftT3$6;
                this.UnliftT4$5 = UnliftT4$5;
                this.UnliftT5$4 = UnliftT5$4;
                this.UnliftT6$3 = UnliftT6$3;
                this.UnliftT7$2 = UnliftT7$2;
                this.UnliftT8$1 = UnliftT8$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple9(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8, Liftables.Unliftable UnliftT9) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$8;
            private final Liftables.Unliftable UnliftT2$8;
            private final Liftables.Unliftable UnliftT3$7;
            private final Liftables.Unliftable UnliftT4$6;
            private final Liftables.Unliftable UnliftT5$5;
            private final Liftables.Unliftable UnliftT6$4;
            private final Liftables.Unliftable UnliftT7$3;
            private final Liftables.Unliftable UnliftT8$2;
            private final Liftables.Unliftable UnliftT9$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x14, Function1<A1, B1> function1) {
                void var32_34;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x14);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$8.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$8.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$7.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$6.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$5.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$4.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$3.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$2.unapply(option18.get())).isEmpty() && $colon$colon8.tl$1() instanceof $colon$colon) {
                                                    Option<T> option19;
                                                    $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                                                    Option<Trees.TreeApi> option20 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                                                    if (!option20.isEmpty() && !(option19 = this.UnliftT9$1.unapply(option20.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon9.tl$1())) {
                                                        Tuple9<T, T, T, T, T, T, T, T, T> tuple9 = new Tuple9<T, T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get(), option19.get());
                                                        return var32_34;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x14);
                return var32_34;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x14) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x14);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$8.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$8.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$7.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$6.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$5.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$4.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$3.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$2.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!($colon$colon8.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                Option<Trees.TreeApi> option19 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                if (option19.isEmpty()) return false;
                Option<T> option20 = this.UnliftT9$1.unapply(option19.get());
                if (option20.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon9.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$8 = UnliftT1$8;
                this.UnliftT2$8 = UnliftT2$8;
                this.UnliftT3$7 = UnliftT3$7;
                this.UnliftT4$6 = UnliftT4$6;
                this.UnliftT5$5 = UnliftT5$5;
                this.UnliftT6$4 = UnliftT6$4;
                this.UnliftT7$3 = UnliftT7$3;
                this.UnliftT8$2 = UnliftT8$2;
                this.UnliftT9$1 = UnliftT9$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple10(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8, Liftables.Unliftable UnliftT9, Liftables.Unliftable UnliftT10) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$9;
            private final Liftables.Unliftable UnliftT2$9;
            private final Liftables.Unliftable UnliftT3$8;
            private final Liftables.Unliftable UnliftT4$7;
            private final Liftables.Unliftable UnliftT5$6;
            private final Liftables.Unliftable UnliftT6$5;
            private final Liftables.Unliftable UnliftT7$4;
            private final Liftables.Unliftable UnliftT8$3;
            private final Liftables.Unliftable UnliftT9$2;
            private final Liftables.Unliftable UnliftT10$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x15, Function1<A1, B1> function1) {
                void var35_37;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x15);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$9.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$9.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$8.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$7.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$6.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$5.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$4.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$3.unapply(option18.get())).isEmpty() && $colon$colon8.tl$1() instanceof $colon$colon) {
                                                    Option<T> option19;
                                                    $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                                                    Option<Trees.TreeApi> option20 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                                                    if (!option20.isEmpty() && !(option19 = this.UnliftT9$2.unapply(option20.get())).isEmpty() && $colon$colon9.tl$1() instanceof $colon$colon) {
                                                        Option<T> option21;
                                                        $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                                                        Option<Trees.TreeApi> option22 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                                                        if (!option22.isEmpty() && !(option21 = this.UnliftT10$1.unapply(option22.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon10.tl$1())) {
                                                            Tuple10<T, T, T, T, T, T, T, T, T, T> tuple10 = new Tuple10<T, T, T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get(), option19.get(), option21.get());
                                                            return var35_37;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x15);
                return var35_37;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x15) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x15);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$9.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$9.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$8.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$7.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$6.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$5.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$4.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$3.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!($colon$colon8.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                Option<Trees.TreeApi> option19 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                if (option19.isEmpty()) return false;
                Option<T> option20 = this.UnliftT9$2.unapply(option19.get());
                if (option20.isEmpty()) return false;
                if (!($colon$colon9.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                Option<Trees.TreeApi> option21 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                if (option21.isEmpty()) return false;
                Option<T> option22 = this.UnliftT10$1.unapply(option21.get());
                if (option22.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon10.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$9 = UnliftT1$9;
                this.UnliftT2$9 = UnliftT2$9;
                this.UnliftT3$8 = UnliftT3$8;
                this.UnliftT4$7 = UnliftT4$7;
                this.UnliftT5$6 = UnliftT5$6;
                this.UnliftT6$5 = UnliftT6$5;
                this.UnliftT7$4 = UnliftT7$4;
                this.UnliftT8$3 = UnliftT8$3;
                this.UnliftT9$2 = UnliftT9$2;
                this.UnliftT10$1 = UnliftT10$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple11(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8, Liftables.Unliftable UnliftT9, Liftables.Unliftable UnliftT10, Liftables.Unliftable UnliftT11) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$10;
            private final Liftables.Unliftable UnliftT2$10;
            private final Liftables.Unliftable UnliftT3$9;
            private final Liftables.Unliftable UnliftT4$8;
            private final Liftables.Unliftable UnliftT5$7;
            private final Liftables.Unliftable UnliftT6$6;
            private final Liftables.Unliftable UnliftT7$5;
            private final Liftables.Unliftable UnliftT8$4;
            private final Liftables.Unliftable UnliftT9$3;
            private final Liftables.Unliftable UnliftT10$2;
            private final Liftables.Unliftable UnliftT11$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x16, Function1<A1, B1> function1) {
                void var38_40;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x16);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$10.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$10.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$9.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$8.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$7.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$6.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$5.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$4.unapply(option18.get())).isEmpty() && $colon$colon8.tl$1() instanceof $colon$colon) {
                                                    Option<T> option19;
                                                    $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                                                    Option<Trees.TreeApi> option20 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                                                    if (!option20.isEmpty() && !(option19 = this.UnliftT9$3.unapply(option20.get())).isEmpty() && $colon$colon9.tl$1() instanceof $colon$colon) {
                                                        Option<T> option21;
                                                        $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                                                        Option<Trees.TreeApi> option22 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                                                        if (!option22.isEmpty() && !(option21 = this.UnliftT10$2.unapply(option22.get())).isEmpty() && $colon$colon10.tl$1() instanceof $colon$colon) {
                                                            Option<T> option23;
                                                            $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                                                            Option<Trees.TreeApi> option24 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                                                            if (!option24.isEmpty() && !(option23 = this.UnliftT11$1.unapply(option24.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon11.tl$1())) {
                                                                Tuple11<T, T, T, T, T, T, T, T, T, T, T> tuple11 = new Tuple11<T, T, T, T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get(), option19.get(), option21.get(), option23.get());
                                                                return var38_40;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x16);
                return var38_40;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x16) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x16);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$10.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$10.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$9.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$8.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$7.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$6.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$5.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$4.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!($colon$colon8.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                Option<Trees.TreeApi> option19 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                if (option19.isEmpty()) return false;
                Option<T> option20 = this.UnliftT9$3.unapply(option19.get());
                if (option20.isEmpty()) return false;
                if (!($colon$colon9.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                Option<Trees.TreeApi> option21 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                if (option21.isEmpty()) return false;
                Option<T> option22 = this.UnliftT10$2.unapply(option21.get());
                if (option22.isEmpty()) return false;
                if (!($colon$colon10.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                Option<Trees.TreeApi> option23 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                if (option23.isEmpty()) return false;
                Option<T> option24 = this.UnliftT11$1.unapply(option23.get());
                if (option24.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon11.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$10 = UnliftT1$10;
                this.UnliftT2$10 = UnliftT2$10;
                this.UnliftT3$9 = UnliftT3$9;
                this.UnliftT4$8 = UnliftT4$8;
                this.UnliftT5$7 = UnliftT5$7;
                this.UnliftT6$6 = UnliftT6$6;
                this.UnliftT7$5 = UnliftT7$5;
                this.UnliftT8$4 = UnliftT8$4;
                this.UnliftT9$3 = UnliftT9$3;
                this.UnliftT10$2 = UnliftT10$2;
                this.UnliftT11$1 = UnliftT11$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple12(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8, Liftables.Unliftable UnliftT9, Liftables.Unliftable UnliftT10, Liftables.Unliftable UnliftT11, Liftables.Unliftable UnliftT12) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$11;
            private final Liftables.Unliftable UnliftT2$11;
            private final Liftables.Unliftable UnliftT3$10;
            private final Liftables.Unliftable UnliftT4$9;
            private final Liftables.Unliftable UnliftT5$8;
            private final Liftables.Unliftable UnliftT6$7;
            private final Liftables.Unliftable UnliftT7$6;
            private final Liftables.Unliftable UnliftT8$5;
            private final Liftables.Unliftable UnliftT9$4;
            private final Liftables.Unliftable UnliftT10$3;
            private final Liftables.Unliftable UnliftT11$2;
            private final Liftables.Unliftable UnliftT12$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x17, Function1<A1, B1> function1) {
                void var41_43;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x17);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$11.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$11.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$10.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$9.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$8.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$7.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$6.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$5.unapply(option18.get())).isEmpty() && $colon$colon8.tl$1() instanceof $colon$colon) {
                                                    Option<T> option19;
                                                    $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                                                    Option<Trees.TreeApi> option20 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                                                    if (!option20.isEmpty() && !(option19 = this.UnliftT9$4.unapply(option20.get())).isEmpty() && $colon$colon9.tl$1() instanceof $colon$colon) {
                                                        Option<T> option21;
                                                        $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                                                        Option<Trees.TreeApi> option22 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                                                        if (!option22.isEmpty() && !(option21 = this.UnliftT10$3.unapply(option22.get())).isEmpty() && $colon$colon10.tl$1() instanceof $colon$colon) {
                                                            Option<T> option23;
                                                            $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                                                            Option<Trees.TreeApi> option24 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                                                            if (!option24.isEmpty() && !(option23 = this.UnliftT11$2.unapply(option24.get())).isEmpty() && $colon$colon11.tl$1() instanceof $colon$colon) {
                                                                Option<T> option25;
                                                                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                                                                Option<Trees.TreeApi> option26 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                                                                if (!option26.isEmpty() && !(option25 = this.UnliftT12$1.unapply(option26.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon12.tl$1())) {
                                                                    Tuple12<T, T, T, T, T, T, T, T, T, T, T, T> tuple12 = new Tuple12<T, T, T, T, T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get(), option19.get(), option21.get(), option23.get(), option25.get());
                                                                    return var41_43;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x17);
                return var41_43;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x17) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x17);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$11.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$11.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$10.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$9.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$8.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$7.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$6.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$5.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!($colon$colon8.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                Option<Trees.TreeApi> option19 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                if (option19.isEmpty()) return false;
                Option<T> option20 = this.UnliftT9$4.unapply(option19.get());
                if (option20.isEmpty()) return false;
                if (!($colon$colon9.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                Option<Trees.TreeApi> option21 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                if (option21.isEmpty()) return false;
                Option<T> option22 = this.UnliftT10$3.unapply(option21.get());
                if (option22.isEmpty()) return false;
                if (!($colon$colon10.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                Option<Trees.TreeApi> option23 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                if (option23.isEmpty()) return false;
                Option<T> option24 = this.UnliftT11$2.unapply(option23.get());
                if (option24.isEmpty()) return false;
                if (!($colon$colon11.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                Option<Trees.TreeApi> option25 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                if (option25.isEmpty()) return false;
                Option<T> option26 = this.UnliftT12$1.unapply(option25.get());
                if (option26.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon12.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$11 = UnliftT1$11;
                this.UnliftT2$11 = UnliftT2$11;
                this.UnliftT3$10 = UnliftT3$10;
                this.UnliftT4$9 = UnliftT4$9;
                this.UnliftT5$8 = UnliftT5$8;
                this.UnliftT6$7 = UnliftT6$7;
                this.UnliftT7$6 = UnliftT7$6;
                this.UnliftT8$5 = UnliftT8$5;
                this.UnliftT9$4 = UnliftT9$4;
                this.UnliftT10$3 = UnliftT10$3;
                this.UnliftT11$2 = UnliftT11$2;
                this.UnliftT12$1 = UnliftT12$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple13(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8, Liftables.Unliftable UnliftT9, Liftables.Unliftable UnliftT10, Liftables.Unliftable UnliftT11, Liftables.Unliftable UnliftT12, Liftables.Unliftable UnliftT13) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$12;
            private final Liftables.Unliftable UnliftT2$12;
            private final Liftables.Unliftable UnliftT3$11;
            private final Liftables.Unliftable UnliftT4$10;
            private final Liftables.Unliftable UnliftT5$9;
            private final Liftables.Unliftable UnliftT6$8;
            private final Liftables.Unliftable UnliftT7$7;
            private final Liftables.Unliftable UnliftT8$6;
            private final Liftables.Unliftable UnliftT9$5;
            private final Liftables.Unliftable UnliftT10$4;
            private final Liftables.Unliftable UnliftT11$3;
            private final Liftables.Unliftable UnliftT12$2;
            private final Liftables.Unliftable UnliftT13$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x18, Function1<A1, B1> function1) {
                void var44_46;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x18);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$12.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$12.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$11.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$10.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$9.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$8.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$7.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$6.unapply(option18.get())).isEmpty() && $colon$colon8.tl$1() instanceof $colon$colon) {
                                                    Option<T> option19;
                                                    $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                                                    Option<Trees.TreeApi> option20 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                                                    if (!option20.isEmpty() && !(option19 = this.UnliftT9$5.unapply(option20.get())).isEmpty() && $colon$colon9.tl$1() instanceof $colon$colon) {
                                                        Option<T> option21;
                                                        $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                                                        Option<Trees.TreeApi> option22 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                                                        if (!option22.isEmpty() && !(option21 = this.UnliftT10$4.unapply(option22.get())).isEmpty() && $colon$colon10.tl$1() instanceof $colon$colon) {
                                                            Option<T> option23;
                                                            $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                                                            Option<Trees.TreeApi> option24 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                                                            if (!option24.isEmpty() && !(option23 = this.UnliftT11$3.unapply(option24.get())).isEmpty() && $colon$colon11.tl$1() instanceof $colon$colon) {
                                                                Option<T> option25;
                                                                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                                                                Option<Trees.TreeApi> option26 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                                                                if (!option26.isEmpty() && !(option25 = this.UnliftT12$2.unapply(option26.get())).isEmpty() && $colon$colon12.tl$1() instanceof $colon$colon) {
                                                                    Option<T> option27;
                                                                    $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                                                                    Option<Trees.TreeApi> option28 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                                                                    if (!option28.isEmpty() && !(option27 = this.UnliftT13$1.unapply(option28.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon13.tl$1())) {
                                                                        Tuple13<T, T, T, T, T, T, T, T, T, T, T, T, T> tuple13 = new Tuple13<T, T, T, T, T, T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get(), option19.get(), option21.get(), option23.get(), option25.get(), option27.get());
                                                                        return var44_46;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x18);
                return var44_46;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x18) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x18);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$12.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$12.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$11.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$10.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$9.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$8.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$7.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$6.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!($colon$colon8.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                Option<Trees.TreeApi> option19 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                if (option19.isEmpty()) return false;
                Option<T> option20 = this.UnliftT9$5.unapply(option19.get());
                if (option20.isEmpty()) return false;
                if (!($colon$colon9.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                Option<Trees.TreeApi> option21 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                if (option21.isEmpty()) return false;
                Option<T> option22 = this.UnliftT10$4.unapply(option21.get());
                if (option22.isEmpty()) return false;
                if (!($colon$colon10.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                Option<Trees.TreeApi> option23 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                if (option23.isEmpty()) return false;
                Option<T> option24 = this.UnliftT11$3.unapply(option23.get());
                if (option24.isEmpty()) return false;
                if (!($colon$colon11.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                Option<Trees.TreeApi> option25 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                if (option25.isEmpty()) return false;
                Option<T> option26 = this.UnliftT12$2.unapply(option25.get());
                if (option26.isEmpty()) return false;
                if (!($colon$colon12.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                Option<Trees.TreeApi> option27 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                if (option27.isEmpty()) return false;
                Option<T> option28 = this.UnliftT13$1.unapply(option27.get());
                if (option28.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon13.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$12 = UnliftT1$12;
                this.UnliftT2$12 = UnliftT2$12;
                this.UnliftT3$11 = UnliftT3$11;
                this.UnliftT4$10 = UnliftT4$10;
                this.UnliftT5$9 = UnliftT5$9;
                this.UnliftT6$8 = UnliftT6$8;
                this.UnliftT7$7 = UnliftT7$7;
                this.UnliftT8$6 = UnliftT8$6;
                this.UnliftT9$5 = UnliftT9$5;
                this.UnliftT10$4 = UnliftT10$4;
                this.UnliftT11$3 = UnliftT11$3;
                this.UnliftT12$2 = UnliftT12$2;
                this.UnliftT13$1 = UnliftT13$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple14(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8, Liftables.Unliftable UnliftT9, Liftables.Unliftable UnliftT10, Liftables.Unliftable UnliftT11, Liftables.Unliftable UnliftT12, Liftables.Unliftable UnliftT13, Liftables.Unliftable UnliftT14) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$13;
            private final Liftables.Unliftable UnliftT2$13;
            private final Liftables.Unliftable UnliftT3$12;
            private final Liftables.Unliftable UnliftT4$11;
            private final Liftables.Unliftable UnliftT5$10;
            private final Liftables.Unliftable UnliftT6$9;
            private final Liftables.Unliftable UnliftT7$8;
            private final Liftables.Unliftable UnliftT8$7;
            private final Liftables.Unliftable UnliftT9$6;
            private final Liftables.Unliftable UnliftT10$5;
            private final Liftables.Unliftable UnliftT11$4;
            private final Liftables.Unliftable UnliftT12$3;
            private final Liftables.Unliftable UnliftT13$2;
            private final Liftables.Unliftable UnliftT14$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x19, Function1<A1, B1> function1) {
                void var47_49;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x19);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$13.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$13.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$12.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$11.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$10.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$9.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$8.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$7.unapply(option18.get())).isEmpty() && $colon$colon8.tl$1() instanceof $colon$colon) {
                                                    Option<T> option19;
                                                    $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                                                    Option<Trees.TreeApi> option20 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                                                    if (!option20.isEmpty() && !(option19 = this.UnliftT9$6.unapply(option20.get())).isEmpty() && $colon$colon9.tl$1() instanceof $colon$colon) {
                                                        Option<T> option21;
                                                        $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                                                        Option<Trees.TreeApi> option22 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                                                        if (!option22.isEmpty() && !(option21 = this.UnliftT10$5.unapply(option22.get())).isEmpty() && $colon$colon10.tl$1() instanceof $colon$colon) {
                                                            Option<T> option23;
                                                            $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                                                            Option<Trees.TreeApi> option24 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                                                            if (!option24.isEmpty() && !(option23 = this.UnliftT11$4.unapply(option24.get())).isEmpty() && $colon$colon11.tl$1() instanceof $colon$colon) {
                                                                Option<T> option25;
                                                                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                                                                Option<Trees.TreeApi> option26 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                                                                if (!option26.isEmpty() && !(option25 = this.UnliftT12$3.unapply(option26.get())).isEmpty() && $colon$colon12.tl$1() instanceof $colon$colon) {
                                                                    Option<T> option27;
                                                                    $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                                                                    Option<Trees.TreeApi> option28 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                                                                    if (!option28.isEmpty() && !(option27 = this.UnliftT13$2.unapply(option28.get())).isEmpty() && $colon$colon13.tl$1() instanceof $colon$colon) {
                                                                        Option<T> option29;
                                                                        $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                                                                        Option<Trees.TreeApi> option30 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                                                                        if (!option30.isEmpty() && !(option29 = this.UnliftT14$1.unapply(option30.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon14.tl$1())) {
                                                                            Tuple14<T, T, T, T, T, T, T, T, T, T, T, T, T, T> tuple14 = new Tuple14<T, T, T, T, T, T, T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get(), option19.get(), option21.get(), option23.get(), option25.get(), option27.get(), option29.get());
                                                                            return var47_49;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x19);
                return var47_49;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x19) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x19);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$13.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$13.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$12.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$11.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$10.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$9.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$8.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$7.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!($colon$colon8.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                Option<Trees.TreeApi> option19 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                if (option19.isEmpty()) return false;
                Option<T> option20 = this.UnliftT9$6.unapply(option19.get());
                if (option20.isEmpty()) return false;
                if (!($colon$colon9.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                Option<Trees.TreeApi> option21 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                if (option21.isEmpty()) return false;
                Option<T> option22 = this.UnliftT10$5.unapply(option21.get());
                if (option22.isEmpty()) return false;
                if (!($colon$colon10.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                Option<Trees.TreeApi> option23 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                if (option23.isEmpty()) return false;
                Option<T> option24 = this.UnliftT11$4.unapply(option23.get());
                if (option24.isEmpty()) return false;
                if (!($colon$colon11.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                Option<Trees.TreeApi> option25 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                if (option25.isEmpty()) return false;
                Option<T> option26 = this.UnliftT12$3.unapply(option25.get());
                if (option26.isEmpty()) return false;
                if (!($colon$colon12.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                Option<Trees.TreeApi> option27 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                if (option27.isEmpty()) return false;
                Option<T> option28 = this.UnliftT13$2.unapply(option27.get());
                if (option28.isEmpty()) return false;
                if (!($colon$colon13.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                Option<Trees.TreeApi> option29 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                if (option29.isEmpty()) return false;
                Option<T> option30 = this.UnliftT14$1.unapply(option29.get());
                if (option30.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon14.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$13 = UnliftT1$13;
                this.UnliftT2$13 = UnliftT2$13;
                this.UnliftT3$12 = UnliftT3$12;
                this.UnliftT4$11 = UnliftT4$11;
                this.UnliftT5$10 = UnliftT5$10;
                this.UnliftT6$9 = UnliftT6$9;
                this.UnliftT7$8 = UnliftT7$8;
                this.UnliftT8$7 = UnliftT8$7;
                this.UnliftT9$6 = UnliftT9$6;
                this.UnliftT10$5 = UnliftT10$5;
                this.UnliftT11$4 = UnliftT11$4;
                this.UnliftT12$3 = UnliftT12$3;
                this.UnliftT13$2 = UnliftT13$2;
                this.UnliftT14$1 = UnliftT14$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple15(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8, Liftables.Unliftable UnliftT9, Liftables.Unliftable UnliftT10, Liftables.Unliftable UnliftT11, Liftables.Unliftable UnliftT12, Liftables.Unliftable UnliftT13, Liftables.Unliftable UnliftT14, Liftables.Unliftable UnliftT15) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$14;
            private final Liftables.Unliftable UnliftT2$14;
            private final Liftables.Unliftable UnliftT3$13;
            private final Liftables.Unliftable UnliftT4$12;
            private final Liftables.Unliftable UnliftT5$11;
            private final Liftables.Unliftable UnliftT6$10;
            private final Liftables.Unliftable UnliftT7$9;
            private final Liftables.Unliftable UnliftT8$8;
            private final Liftables.Unliftable UnliftT9$7;
            private final Liftables.Unliftable UnliftT10$6;
            private final Liftables.Unliftable UnliftT11$5;
            private final Liftables.Unliftable UnliftT12$4;
            private final Liftables.Unliftable UnliftT13$3;
            private final Liftables.Unliftable UnliftT14$2;
            private final Liftables.Unliftable UnliftT15$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x20, Function1<A1, B1> function1) {
                void var50_52;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x20);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$14.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$14.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$13.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$12.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$11.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$10.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$9.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$8.unapply(option18.get())).isEmpty() && $colon$colon8.tl$1() instanceof $colon$colon) {
                                                    Option<T> option19;
                                                    $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                                                    Option<Trees.TreeApi> option20 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                                                    if (!option20.isEmpty() && !(option19 = this.UnliftT9$7.unapply(option20.get())).isEmpty() && $colon$colon9.tl$1() instanceof $colon$colon) {
                                                        Option<T> option21;
                                                        $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                                                        Option<Trees.TreeApi> option22 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                                                        if (!option22.isEmpty() && !(option21 = this.UnliftT10$6.unapply(option22.get())).isEmpty() && $colon$colon10.tl$1() instanceof $colon$colon) {
                                                            Option<T> option23;
                                                            $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                                                            Option<Trees.TreeApi> option24 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                                                            if (!option24.isEmpty() && !(option23 = this.UnliftT11$5.unapply(option24.get())).isEmpty() && $colon$colon11.tl$1() instanceof $colon$colon) {
                                                                Option<T> option25;
                                                                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                                                                Option<Trees.TreeApi> option26 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                                                                if (!option26.isEmpty() && !(option25 = this.UnliftT12$4.unapply(option26.get())).isEmpty() && $colon$colon12.tl$1() instanceof $colon$colon) {
                                                                    Option<T> option27;
                                                                    $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                                                                    Option<Trees.TreeApi> option28 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                                                                    if (!option28.isEmpty() && !(option27 = this.UnliftT13$3.unapply(option28.get())).isEmpty() && $colon$colon13.tl$1() instanceof $colon$colon) {
                                                                        Option<T> option29;
                                                                        $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                                                                        Option<Trees.TreeApi> option30 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                                                                        if (!option30.isEmpty() && !(option29 = this.UnliftT14$2.unapply(option30.get())).isEmpty() && $colon$colon14.tl$1() instanceof $colon$colon) {
                                                                            Option<T> option31;
                                                                            $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                                                                            Option<Trees.TreeApi> option32 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                                                                            if (!option32.isEmpty() && !(option31 = this.UnliftT15$1.unapply(option32.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon15.tl$1())) {
                                                                                Tuple15<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T> tuple15 = new Tuple15<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get(), option19.get(), option21.get(), option23.get(), option25.get(), option27.get(), option29.get(), option31.get());
                                                                                return var50_52;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x20);
                return var50_52;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x20) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x20);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$14.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$14.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$13.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$12.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$11.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$10.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$9.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$8.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!($colon$colon8.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                Option<Trees.TreeApi> option19 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                if (option19.isEmpty()) return false;
                Option<T> option20 = this.UnliftT9$7.unapply(option19.get());
                if (option20.isEmpty()) return false;
                if (!($colon$colon9.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                Option<Trees.TreeApi> option21 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                if (option21.isEmpty()) return false;
                Option<T> option22 = this.UnliftT10$6.unapply(option21.get());
                if (option22.isEmpty()) return false;
                if (!($colon$colon10.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                Option<Trees.TreeApi> option23 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                if (option23.isEmpty()) return false;
                Option<T> option24 = this.UnliftT11$5.unapply(option23.get());
                if (option24.isEmpty()) return false;
                if (!($colon$colon11.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                Option<Trees.TreeApi> option25 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                if (option25.isEmpty()) return false;
                Option<T> option26 = this.UnliftT12$4.unapply(option25.get());
                if (option26.isEmpty()) return false;
                if (!($colon$colon12.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                Option<Trees.TreeApi> option27 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                if (option27.isEmpty()) return false;
                Option<T> option28 = this.UnliftT13$3.unapply(option27.get());
                if (option28.isEmpty()) return false;
                if (!($colon$colon13.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                Option<Trees.TreeApi> option29 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                if (option29.isEmpty()) return false;
                Option<T> option30 = this.UnliftT14$2.unapply(option29.get());
                if (option30.isEmpty()) return false;
                if (!($colon$colon14.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                Option<Trees.TreeApi> option31 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                if (option31.isEmpty()) return false;
                Option<T> option32 = this.UnliftT15$1.unapply(option31.get());
                if (option32.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon15.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$14 = UnliftT1$14;
                this.UnliftT2$14 = UnliftT2$14;
                this.UnliftT3$13 = UnliftT3$13;
                this.UnliftT4$12 = UnliftT4$12;
                this.UnliftT5$11 = UnliftT5$11;
                this.UnliftT6$10 = UnliftT6$10;
                this.UnliftT7$9 = UnliftT7$9;
                this.UnliftT8$8 = UnliftT8$8;
                this.UnliftT9$7 = UnliftT9$7;
                this.UnliftT10$6 = UnliftT10$6;
                this.UnliftT11$5 = UnliftT11$5;
                this.UnliftT12$4 = UnliftT12$4;
                this.UnliftT13$3 = UnliftT13$3;
                this.UnliftT14$2 = UnliftT14$2;
                this.UnliftT15$1 = UnliftT15$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple16(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8, Liftables.Unliftable UnliftT9, Liftables.Unliftable UnliftT10, Liftables.Unliftable UnliftT11, Liftables.Unliftable UnliftT12, Liftables.Unliftable UnliftT13, Liftables.Unliftable UnliftT14, Liftables.Unliftable UnliftT15, Liftables.Unliftable UnliftT16) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15, UnliftT16){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$15;
            private final Liftables.Unliftable UnliftT2$15;
            private final Liftables.Unliftable UnliftT3$14;
            private final Liftables.Unliftable UnliftT4$13;
            private final Liftables.Unliftable UnliftT5$12;
            private final Liftables.Unliftable UnliftT6$11;
            private final Liftables.Unliftable UnliftT7$10;
            private final Liftables.Unliftable UnliftT8$9;
            private final Liftables.Unliftable UnliftT9$8;
            private final Liftables.Unliftable UnliftT10$7;
            private final Liftables.Unliftable UnliftT11$6;
            private final Liftables.Unliftable UnliftT12$5;
            private final Liftables.Unliftable UnliftT13$4;
            private final Liftables.Unliftable UnliftT14$3;
            private final Liftables.Unliftable UnliftT15$2;
            private final Liftables.Unliftable UnliftT16$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x21, Function1<A1, B1> function1) {
                void var53_55;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x21);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$15.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$15.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$14.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$13.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$12.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$11.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$10.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$9.unapply(option18.get())).isEmpty() && $colon$colon8.tl$1() instanceof $colon$colon) {
                                                    Option<T> option19;
                                                    $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                                                    Option<Trees.TreeApi> option20 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                                                    if (!option20.isEmpty() && !(option19 = this.UnliftT9$8.unapply(option20.get())).isEmpty() && $colon$colon9.tl$1() instanceof $colon$colon) {
                                                        Option<T> option21;
                                                        $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                                                        Option<Trees.TreeApi> option22 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                                                        if (!option22.isEmpty() && !(option21 = this.UnliftT10$7.unapply(option22.get())).isEmpty() && $colon$colon10.tl$1() instanceof $colon$colon) {
                                                            Option<T> option23;
                                                            $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                                                            Option<Trees.TreeApi> option24 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                                                            if (!option24.isEmpty() && !(option23 = this.UnliftT11$6.unapply(option24.get())).isEmpty() && $colon$colon11.tl$1() instanceof $colon$colon) {
                                                                Option<T> option25;
                                                                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                                                                Option<Trees.TreeApi> option26 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                                                                if (!option26.isEmpty() && !(option25 = this.UnliftT12$5.unapply(option26.get())).isEmpty() && $colon$colon12.tl$1() instanceof $colon$colon) {
                                                                    Option<T> option27;
                                                                    $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                                                                    Option<Trees.TreeApi> option28 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                                                                    if (!option28.isEmpty() && !(option27 = this.UnliftT13$4.unapply(option28.get())).isEmpty() && $colon$colon13.tl$1() instanceof $colon$colon) {
                                                                        Option<T> option29;
                                                                        $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                                                                        Option<Trees.TreeApi> option30 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                                                                        if (!option30.isEmpty() && !(option29 = this.UnliftT14$3.unapply(option30.get())).isEmpty() && $colon$colon14.tl$1() instanceof $colon$colon) {
                                                                            Option<T> option31;
                                                                            $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                                                                            Option<Trees.TreeApi> option32 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                                                                            if (!option32.isEmpty() && !(option31 = this.UnliftT15$2.unapply(option32.get())).isEmpty() && $colon$colon15.tl$1() instanceof $colon$colon) {
                                                                                Option<T> option33;
                                                                                $colon$colon $colon$colon16 = ($colon$colon)$colon$colon15.tl$1();
                                                                                Option<Trees.TreeApi> option34 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon16.head());
                                                                                if (!option34.isEmpty() && !(option33 = this.UnliftT16$1.unapply(option34.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon16.tl$1())) {
                                                                                    Tuple16<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T> tuple16 = new Tuple16<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get(), option19.get(), option21.get(), option23.get(), option25.get(), option27.get(), option29.get(), option31.get(), option33.get());
                                                                                    return var53_55;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x21);
                return var53_55;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x21) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x21);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$15.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$15.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$14.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$13.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$12.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$11.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$10.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$9.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!($colon$colon8.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                Option<Trees.TreeApi> option19 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                if (option19.isEmpty()) return false;
                Option<T> option20 = this.UnliftT9$8.unapply(option19.get());
                if (option20.isEmpty()) return false;
                if (!($colon$colon9.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                Option<Trees.TreeApi> option21 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                if (option21.isEmpty()) return false;
                Option<T> option22 = this.UnliftT10$7.unapply(option21.get());
                if (option22.isEmpty()) return false;
                if (!($colon$colon10.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                Option<Trees.TreeApi> option23 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                if (option23.isEmpty()) return false;
                Option<T> option24 = this.UnliftT11$6.unapply(option23.get());
                if (option24.isEmpty()) return false;
                if (!($colon$colon11.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                Option<Trees.TreeApi> option25 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                if (option25.isEmpty()) return false;
                Option<T> option26 = this.UnliftT12$5.unapply(option25.get());
                if (option26.isEmpty()) return false;
                if (!($colon$colon12.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                Option<Trees.TreeApi> option27 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                if (option27.isEmpty()) return false;
                Option<T> option28 = this.UnliftT13$4.unapply(option27.get());
                if (option28.isEmpty()) return false;
                if (!($colon$colon13.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                Option<Trees.TreeApi> option29 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                if (option29.isEmpty()) return false;
                Option<T> option30 = this.UnliftT14$3.unapply(option29.get());
                if (option30.isEmpty()) return false;
                if (!($colon$colon14.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                Option<Trees.TreeApi> option31 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                if (option31.isEmpty()) return false;
                Option<T> option32 = this.UnliftT15$2.unapply(option31.get());
                if (option32.isEmpty()) return false;
                if (!($colon$colon15.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon16 = ($colon$colon)$colon$colon15.tl$1();
                Option<Trees.TreeApi> option33 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon16.head());
                if (option33.isEmpty()) return false;
                Option<T> option34 = this.UnliftT16$1.unapply(option33.get());
                if (option34.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon16.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$15 = UnliftT1$15;
                this.UnliftT2$15 = UnliftT2$15;
                this.UnliftT3$14 = UnliftT3$14;
                this.UnliftT4$13 = UnliftT4$13;
                this.UnliftT5$12 = UnliftT5$12;
                this.UnliftT6$11 = UnliftT6$11;
                this.UnliftT7$10 = UnliftT7$10;
                this.UnliftT8$9 = UnliftT8$9;
                this.UnliftT9$8 = UnliftT9$8;
                this.UnliftT10$7 = UnliftT10$7;
                this.UnliftT11$6 = UnliftT11$6;
                this.UnliftT12$5 = UnliftT12$5;
                this.UnliftT13$4 = UnliftT13$4;
                this.UnliftT14$3 = UnliftT14$3;
                this.UnliftT15$2 = UnliftT15$2;
                this.UnliftT16$1 = UnliftT16$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple17(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8, Liftables.Unliftable UnliftT9, Liftables.Unliftable UnliftT10, Liftables.Unliftable UnliftT11, Liftables.Unliftable UnliftT12, Liftables.Unliftable UnliftT13, Liftables.Unliftable UnliftT14, Liftables.Unliftable UnliftT15, Liftables.Unliftable UnliftT16, Liftables.Unliftable UnliftT17) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15, UnliftT16, UnliftT17){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$16;
            private final Liftables.Unliftable UnliftT2$16;
            private final Liftables.Unliftable UnliftT3$15;
            private final Liftables.Unliftable UnliftT4$14;
            private final Liftables.Unliftable UnliftT5$13;
            private final Liftables.Unliftable UnliftT6$12;
            private final Liftables.Unliftable UnliftT7$11;
            private final Liftables.Unliftable UnliftT8$10;
            private final Liftables.Unliftable UnliftT9$9;
            private final Liftables.Unliftable UnliftT10$8;
            private final Liftables.Unliftable UnliftT11$7;
            private final Liftables.Unliftable UnliftT12$6;
            private final Liftables.Unliftable UnliftT13$5;
            private final Liftables.Unliftable UnliftT14$4;
            private final Liftables.Unliftable UnliftT15$3;
            private final Liftables.Unliftable UnliftT16$2;
            private final Liftables.Unliftable UnliftT17$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x22, Function1<A1, B1> function1) {
                void var56_58;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x22);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$16.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$16.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$15.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$14.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$13.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$12.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$11.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$10.unapply(option18.get())).isEmpty() && $colon$colon8.tl$1() instanceof $colon$colon) {
                                                    Option<T> option19;
                                                    $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                                                    Option<Trees.TreeApi> option20 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                                                    if (!option20.isEmpty() && !(option19 = this.UnliftT9$9.unapply(option20.get())).isEmpty() && $colon$colon9.tl$1() instanceof $colon$colon) {
                                                        Option<T> option21;
                                                        $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                                                        Option<Trees.TreeApi> option22 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                                                        if (!option22.isEmpty() && !(option21 = this.UnliftT10$8.unapply(option22.get())).isEmpty() && $colon$colon10.tl$1() instanceof $colon$colon) {
                                                            Option<T> option23;
                                                            $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                                                            Option<Trees.TreeApi> option24 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                                                            if (!option24.isEmpty() && !(option23 = this.UnliftT11$7.unapply(option24.get())).isEmpty() && $colon$colon11.tl$1() instanceof $colon$colon) {
                                                                Option<T> option25;
                                                                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                                                                Option<Trees.TreeApi> option26 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                                                                if (!option26.isEmpty() && !(option25 = this.UnliftT12$6.unapply(option26.get())).isEmpty() && $colon$colon12.tl$1() instanceof $colon$colon) {
                                                                    Option<T> option27;
                                                                    $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                                                                    Option<Trees.TreeApi> option28 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                                                                    if (!option28.isEmpty() && !(option27 = this.UnliftT13$5.unapply(option28.get())).isEmpty() && $colon$colon13.tl$1() instanceof $colon$colon) {
                                                                        Option<T> option29;
                                                                        $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                                                                        Option<Trees.TreeApi> option30 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                                                                        if (!option30.isEmpty() && !(option29 = this.UnliftT14$4.unapply(option30.get())).isEmpty() && $colon$colon14.tl$1() instanceof $colon$colon) {
                                                                            Option<T> option31;
                                                                            $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                                                                            Option<Trees.TreeApi> option32 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                                                                            if (!option32.isEmpty() && !(option31 = this.UnliftT15$3.unapply(option32.get())).isEmpty() && $colon$colon15.tl$1() instanceof $colon$colon) {
                                                                                Option<T> option33;
                                                                                $colon$colon $colon$colon16 = ($colon$colon)$colon$colon15.tl$1();
                                                                                Option<Trees.TreeApi> option34 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon16.head());
                                                                                if (!option34.isEmpty() && !(option33 = this.UnliftT16$2.unapply(option34.get())).isEmpty() && $colon$colon16.tl$1() instanceof $colon$colon) {
                                                                                    Option<T> option35;
                                                                                    $colon$colon $colon$colon17 = ($colon$colon)$colon$colon16.tl$1();
                                                                                    Option<Trees.TreeApi> option36 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon17.head());
                                                                                    if (!option36.isEmpty() && !(option35 = this.UnliftT17$1.unapply(option36.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon17.tl$1())) {
                                                                                        Tuple17<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T> tuple17 = new Tuple17<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get(), option19.get(), option21.get(), option23.get(), option25.get(), option27.get(), option29.get(), option31.get(), option33.get(), option35.get());
                                                                                        return var56_58;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x22);
                return var56_58;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x22) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x22);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$16.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$16.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$15.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$14.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$13.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$12.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$11.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$10.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!($colon$colon8.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                Option<Trees.TreeApi> option19 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                if (option19.isEmpty()) return false;
                Option<T> option20 = this.UnliftT9$9.unapply(option19.get());
                if (option20.isEmpty()) return false;
                if (!($colon$colon9.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                Option<Trees.TreeApi> option21 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                if (option21.isEmpty()) return false;
                Option<T> option22 = this.UnliftT10$8.unapply(option21.get());
                if (option22.isEmpty()) return false;
                if (!($colon$colon10.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                Option<Trees.TreeApi> option23 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                if (option23.isEmpty()) return false;
                Option<T> option24 = this.UnliftT11$7.unapply(option23.get());
                if (option24.isEmpty()) return false;
                if (!($colon$colon11.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                Option<Trees.TreeApi> option25 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                if (option25.isEmpty()) return false;
                Option<T> option26 = this.UnliftT12$6.unapply(option25.get());
                if (option26.isEmpty()) return false;
                if (!($colon$colon12.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                Option<Trees.TreeApi> option27 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                if (option27.isEmpty()) return false;
                Option<T> option28 = this.UnliftT13$5.unapply(option27.get());
                if (option28.isEmpty()) return false;
                if (!($colon$colon13.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                Option<Trees.TreeApi> option29 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                if (option29.isEmpty()) return false;
                Option<T> option30 = this.UnliftT14$4.unapply(option29.get());
                if (option30.isEmpty()) return false;
                if (!($colon$colon14.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                Option<Trees.TreeApi> option31 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                if (option31.isEmpty()) return false;
                Option<T> option32 = this.UnliftT15$3.unapply(option31.get());
                if (option32.isEmpty()) return false;
                if (!($colon$colon15.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon16 = ($colon$colon)$colon$colon15.tl$1();
                Option<Trees.TreeApi> option33 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon16.head());
                if (option33.isEmpty()) return false;
                Option<T> option34 = this.UnliftT16$2.unapply(option33.get());
                if (option34.isEmpty()) return false;
                if (!($colon$colon16.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon17 = ($colon$colon)$colon$colon16.tl$1();
                Option<Trees.TreeApi> option35 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon17.head());
                if (option35.isEmpty()) return false;
                Option<T> option36 = this.UnliftT17$1.unapply(option35.get());
                if (option36.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon17.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$16 = UnliftT1$16;
                this.UnliftT2$16 = UnliftT2$16;
                this.UnliftT3$15 = UnliftT3$15;
                this.UnliftT4$14 = UnliftT4$14;
                this.UnliftT5$13 = UnliftT5$13;
                this.UnliftT6$12 = UnliftT6$12;
                this.UnliftT7$11 = UnliftT7$11;
                this.UnliftT8$10 = UnliftT8$10;
                this.UnliftT9$9 = UnliftT9$9;
                this.UnliftT10$8 = UnliftT10$8;
                this.UnliftT11$7 = UnliftT11$7;
                this.UnliftT12$6 = UnliftT12$6;
                this.UnliftT13$5 = UnliftT13$5;
                this.UnliftT14$4 = UnliftT14$4;
                this.UnliftT15$3 = UnliftT15$3;
                this.UnliftT16$2 = UnliftT16$2;
                this.UnliftT17$1 = UnliftT17$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple18(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8, Liftables.Unliftable UnliftT9, Liftables.Unliftable UnliftT10, Liftables.Unliftable UnliftT11, Liftables.Unliftable UnliftT12, Liftables.Unliftable UnliftT13, Liftables.Unliftable UnliftT14, Liftables.Unliftable UnliftT15, Liftables.Unliftable UnliftT16, Liftables.Unliftable UnliftT17, Liftables.Unliftable UnliftT18) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15, UnliftT16, UnliftT17, UnliftT18){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$17;
            private final Liftables.Unliftable UnliftT2$17;
            private final Liftables.Unliftable UnliftT3$16;
            private final Liftables.Unliftable UnliftT4$15;
            private final Liftables.Unliftable UnliftT5$14;
            private final Liftables.Unliftable UnliftT6$13;
            private final Liftables.Unliftable UnliftT7$12;
            private final Liftables.Unliftable UnliftT8$11;
            private final Liftables.Unliftable UnliftT9$10;
            private final Liftables.Unliftable UnliftT10$9;
            private final Liftables.Unliftable UnliftT11$8;
            private final Liftables.Unliftable UnliftT12$7;
            private final Liftables.Unliftable UnliftT13$6;
            private final Liftables.Unliftable UnliftT14$5;
            private final Liftables.Unliftable UnliftT15$4;
            private final Liftables.Unliftable UnliftT16$3;
            private final Liftables.Unliftable UnliftT17$2;
            private final Liftables.Unliftable UnliftT18$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x23, Function1<A1, B1> function1) {
                void var59_61;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x23);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$17.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$17.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$16.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$15.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$14.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$13.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$12.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$11.unapply(option18.get())).isEmpty() && $colon$colon8.tl$1() instanceof $colon$colon) {
                                                    Option<T> option19;
                                                    $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                                                    Option<Trees.TreeApi> option20 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                                                    if (!option20.isEmpty() && !(option19 = this.UnliftT9$10.unapply(option20.get())).isEmpty() && $colon$colon9.tl$1() instanceof $colon$colon) {
                                                        Option<T> option21;
                                                        $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                                                        Option<Trees.TreeApi> option22 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                                                        if (!option22.isEmpty() && !(option21 = this.UnliftT10$9.unapply(option22.get())).isEmpty() && $colon$colon10.tl$1() instanceof $colon$colon) {
                                                            Option<T> option23;
                                                            $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                                                            Option<Trees.TreeApi> option24 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                                                            if (!option24.isEmpty() && !(option23 = this.UnliftT11$8.unapply(option24.get())).isEmpty() && $colon$colon11.tl$1() instanceof $colon$colon) {
                                                                Option<T> option25;
                                                                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                                                                Option<Trees.TreeApi> option26 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                                                                if (!option26.isEmpty() && !(option25 = this.UnliftT12$7.unapply(option26.get())).isEmpty() && $colon$colon12.tl$1() instanceof $colon$colon) {
                                                                    Option<T> option27;
                                                                    $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                                                                    Option<Trees.TreeApi> option28 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                                                                    if (!option28.isEmpty() && !(option27 = this.UnliftT13$6.unapply(option28.get())).isEmpty() && $colon$colon13.tl$1() instanceof $colon$colon) {
                                                                        Option<T> option29;
                                                                        $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                                                                        Option<Trees.TreeApi> option30 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                                                                        if (!option30.isEmpty() && !(option29 = this.UnliftT14$5.unapply(option30.get())).isEmpty() && $colon$colon14.tl$1() instanceof $colon$colon) {
                                                                            Option<T> option31;
                                                                            $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                                                                            Option<Trees.TreeApi> option32 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                                                                            if (!option32.isEmpty() && !(option31 = this.UnliftT15$4.unapply(option32.get())).isEmpty() && $colon$colon15.tl$1() instanceof $colon$colon) {
                                                                                Option<T> option33;
                                                                                $colon$colon $colon$colon16 = ($colon$colon)$colon$colon15.tl$1();
                                                                                Option<Trees.TreeApi> option34 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon16.head());
                                                                                if (!option34.isEmpty() && !(option33 = this.UnliftT16$3.unapply(option34.get())).isEmpty() && $colon$colon16.tl$1() instanceof $colon$colon) {
                                                                                    Option<T> option35;
                                                                                    $colon$colon $colon$colon17 = ($colon$colon)$colon$colon16.tl$1();
                                                                                    Option<Trees.TreeApi> option36 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon17.head());
                                                                                    if (!option36.isEmpty() && !(option35 = this.UnliftT17$2.unapply(option36.get())).isEmpty() && $colon$colon17.tl$1() instanceof $colon$colon) {
                                                                                        Option<T> option37;
                                                                                        $colon$colon $colon$colon18 = ($colon$colon)$colon$colon17.tl$1();
                                                                                        Option<Trees.TreeApi> option38 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon18.head());
                                                                                        if (!option38.isEmpty() && !(option37 = this.UnliftT18$1.unapply(option38.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon18.tl$1())) {
                                                                                            Tuple18<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T> tuple18 = new Tuple18<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get(), option19.get(), option21.get(), option23.get(), option25.get(), option27.get(), option29.get(), option31.get(), option33.get(), option35.get(), option37.get());
                                                                                            return var59_61;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x23);
                return var59_61;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x23) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x23);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$17.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$17.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$16.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$15.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$14.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$13.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$12.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$11.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!($colon$colon8.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                Option<Trees.TreeApi> option19 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                if (option19.isEmpty()) return false;
                Option<T> option20 = this.UnliftT9$10.unapply(option19.get());
                if (option20.isEmpty()) return false;
                if (!($colon$colon9.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                Option<Trees.TreeApi> option21 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                if (option21.isEmpty()) return false;
                Option<T> option22 = this.UnliftT10$9.unapply(option21.get());
                if (option22.isEmpty()) return false;
                if (!($colon$colon10.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                Option<Trees.TreeApi> option23 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                if (option23.isEmpty()) return false;
                Option<T> option24 = this.UnliftT11$8.unapply(option23.get());
                if (option24.isEmpty()) return false;
                if (!($colon$colon11.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                Option<Trees.TreeApi> option25 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                if (option25.isEmpty()) return false;
                Option<T> option26 = this.UnliftT12$7.unapply(option25.get());
                if (option26.isEmpty()) return false;
                if (!($colon$colon12.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                Option<Trees.TreeApi> option27 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                if (option27.isEmpty()) return false;
                Option<T> option28 = this.UnliftT13$6.unapply(option27.get());
                if (option28.isEmpty()) return false;
                if (!($colon$colon13.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                Option<Trees.TreeApi> option29 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                if (option29.isEmpty()) return false;
                Option<T> option30 = this.UnliftT14$5.unapply(option29.get());
                if (option30.isEmpty()) return false;
                if (!($colon$colon14.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                Option<Trees.TreeApi> option31 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                if (option31.isEmpty()) return false;
                Option<T> option32 = this.UnliftT15$4.unapply(option31.get());
                if (option32.isEmpty()) return false;
                if (!($colon$colon15.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon16 = ($colon$colon)$colon$colon15.tl$1();
                Option<Trees.TreeApi> option33 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon16.head());
                if (option33.isEmpty()) return false;
                Option<T> option34 = this.UnliftT16$3.unapply(option33.get());
                if (option34.isEmpty()) return false;
                if (!($colon$colon16.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon17 = ($colon$colon)$colon$colon16.tl$1();
                Option<Trees.TreeApi> option35 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon17.head());
                if (option35.isEmpty()) return false;
                Option<T> option36 = this.UnliftT17$2.unapply(option35.get());
                if (option36.isEmpty()) return false;
                if (!($colon$colon17.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon18 = ($colon$colon)$colon$colon17.tl$1();
                Option<Trees.TreeApi> option37 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon18.head());
                if (option37.isEmpty()) return false;
                Option<T> option38 = this.UnliftT18$1.unapply(option37.get());
                if (option38.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon18.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$17 = UnliftT1$17;
                this.UnliftT2$17 = UnliftT2$17;
                this.UnliftT3$16 = UnliftT3$16;
                this.UnliftT4$15 = UnliftT4$15;
                this.UnliftT5$14 = UnliftT5$14;
                this.UnliftT6$13 = UnliftT6$13;
                this.UnliftT7$12 = UnliftT7$12;
                this.UnliftT8$11 = UnliftT8$11;
                this.UnliftT9$10 = UnliftT9$10;
                this.UnliftT10$9 = UnliftT10$9;
                this.UnliftT11$8 = UnliftT11$8;
                this.UnliftT12$7 = UnliftT12$7;
                this.UnliftT13$6 = UnliftT13$6;
                this.UnliftT14$5 = UnliftT14$5;
                this.UnliftT15$4 = UnliftT15$4;
                this.UnliftT16$3 = UnliftT16$3;
                this.UnliftT17$2 = UnliftT17$2;
                this.UnliftT18$1 = UnliftT18$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple19(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8, Liftables.Unliftable UnliftT9, Liftables.Unliftable UnliftT10, Liftables.Unliftable UnliftT11, Liftables.Unliftable UnliftT12, Liftables.Unliftable UnliftT13, Liftables.Unliftable UnliftT14, Liftables.Unliftable UnliftT15, Liftables.Unliftable UnliftT16, Liftables.Unliftable UnliftT17, Liftables.Unliftable UnliftT18, Liftables.Unliftable UnliftT19) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15, UnliftT16, UnliftT17, UnliftT18, UnliftT19){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$18;
            private final Liftables.Unliftable UnliftT2$18;
            private final Liftables.Unliftable UnliftT3$17;
            private final Liftables.Unliftable UnliftT4$16;
            private final Liftables.Unliftable UnliftT5$15;
            private final Liftables.Unliftable UnliftT6$14;
            private final Liftables.Unliftable UnliftT7$13;
            private final Liftables.Unliftable UnliftT8$12;
            private final Liftables.Unliftable UnliftT9$11;
            private final Liftables.Unliftable UnliftT10$10;
            private final Liftables.Unliftable UnliftT11$9;
            private final Liftables.Unliftable UnliftT12$8;
            private final Liftables.Unliftable UnliftT13$7;
            private final Liftables.Unliftable UnliftT14$6;
            private final Liftables.Unliftable UnliftT15$5;
            private final Liftables.Unliftable UnliftT16$4;
            private final Liftables.Unliftable UnliftT17$3;
            private final Liftables.Unliftable UnliftT18$2;
            private final Liftables.Unliftable UnliftT19$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x24, Function1<A1, B1> function1) {
                void var62_64;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x24);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$18.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$18.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$17.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$16.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$15.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$14.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$13.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$12.unapply(option18.get())).isEmpty() && $colon$colon8.tl$1() instanceof $colon$colon) {
                                                    Option<T> option19;
                                                    $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                                                    Option<Trees.TreeApi> option20 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                                                    if (!option20.isEmpty() && !(option19 = this.UnliftT9$11.unapply(option20.get())).isEmpty() && $colon$colon9.tl$1() instanceof $colon$colon) {
                                                        Option<T> option21;
                                                        $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                                                        Option<Trees.TreeApi> option22 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                                                        if (!option22.isEmpty() && !(option21 = this.UnliftT10$10.unapply(option22.get())).isEmpty() && $colon$colon10.tl$1() instanceof $colon$colon) {
                                                            Option<T> option23;
                                                            $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                                                            Option<Trees.TreeApi> option24 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                                                            if (!option24.isEmpty() && !(option23 = this.UnliftT11$9.unapply(option24.get())).isEmpty() && $colon$colon11.tl$1() instanceof $colon$colon) {
                                                                Option<T> option25;
                                                                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                                                                Option<Trees.TreeApi> option26 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                                                                if (!option26.isEmpty() && !(option25 = this.UnliftT12$8.unapply(option26.get())).isEmpty() && $colon$colon12.tl$1() instanceof $colon$colon) {
                                                                    Option<T> option27;
                                                                    $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                                                                    Option<Trees.TreeApi> option28 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                                                                    if (!option28.isEmpty() && !(option27 = this.UnliftT13$7.unapply(option28.get())).isEmpty() && $colon$colon13.tl$1() instanceof $colon$colon) {
                                                                        Option<T> option29;
                                                                        $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                                                                        Option<Trees.TreeApi> option30 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                                                                        if (!option30.isEmpty() && !(option29 = this.UnliftT14$6.unapply(option30.get())).isEmpty() && $colon$colon14.tl$1() instanceof $colon$colon) {
                                                                            Option<T> option31;
                                                                            $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                                                                            Option<Trees.TreeApi> option32 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                                                                            if (!option32.isEmpty() && !(option31 = this.UnliftT15$5.unapply(option32.get())).isEmpty() && $colon$colon15.tl$1() instanceof $colon$colon) {
                                                                                Option<T> option33;
                                                                                $colon$colon $colon$colon16 = ($colon$colon)$colon$colon15.tl$1();
                                                                                Option<Trees.TreeApi> option34 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon16.head());
                                                                                if (!option34.isEmpty() && !(option33 = this.UnliftT16$4.unapply(option34.get())).isEmpty() && $colon$colon16.tl$1() instanceof $colon$colon) {
                                                                                    Option<T> option35;
                                                                                    $colon$colon $colon$colon17 = ($colon$colon)$colon$colon16.tl$1();
                                                                                    Option<Trees.TreeApi> option36 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon17.head());
                                                                                    if (!option36.isEmpty() && !(option35 = this.UnliftT17$3.unapply(option36.get())).isEmpty() && $colon$colon17.tl$1() instanceof $colon$colon) {
                                                                                        Option<T> option37;
                                                                                        $colon$colon $colon$colon18 = ($colon$colon)$colon$colon17.tl$1();
                                                                                        Option<Trees.TreeApi> option38 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon18.head());
                                                                                        if (!option38.isEmpty() && !(option37 = this.UnliftT18$2.unapply(option38.get())).isEmpty() && $colon$colon18.tl$1() instanceof $colon$colon) {
                                                                                            Option<T> option39;
                                                                                            $colon$colon $colon$colon19 = ($colon$colon)$colon$colon18.tl$1();
                                                                                            Option<Trees.TreeApi> option40 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon19.head());
                                                                                            if (!option40.isEmpty() && !(option39 = this.UnliftT19$1.unapply(option40.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon19.tl$1())) {
                                                                                                Tuple19<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T> tuple19 = new Tuple19<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get(), option19.get(), option21.get(), option23.get(), option25.get(), option27.get(), option29.get(), option31.get(), option33.get(), option35.get(), option37.get(), option39.get());
                                                                                                return var62_64;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x24);
                return var62_64;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x24) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x24);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$18.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$18.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$17.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$16.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$15.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$14.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$13.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$12.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!($colon$colon8.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                Option<Trees.TreeApi> option19 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                if (option19.isEmpty()) return false;
                Option<T> option20 = this.UnliftT9$11.unapply(option19.get());
                if (option20.isEmpty()) return false;
                if (!($colon$colon9.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                Option<Trees.TreeApi> option21 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                if (option21.isEmpty()) return false;
                Option<T> option22 = this.UnliftT10$10.unapply(option21.get());
                if (option22.isEmpty()) return false;
                if (!($colon$colon10.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                Option<Trees.TreeApi> option23 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                if (option23.isEmpty()) return false;
                Option<T> option24 = this.UnliftT11$9.unapply(option23.get());
                if (option24.isEmpty()) return false;
                if (!($colon$colon11.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                Option<Trees.TreeApi> option25 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                if (option25.isEmpty()) return false;
                Option<T> option26 = this.UnliftT12$8.unapply(option25.get());
                if (option26.isEmpty()) return false;
                if (!($colon$colon12.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                Option<Trees.TreeApi> option27 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                if (option27.isEmpty()) return false;
                Option<T> option28 = this.UnliftT13$7.unapply(option27.get());
                if (option28.isEmpty()) return false;
                if (!($colon$colon13.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                Option<Trees.TreeApi> option29 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                if (option29.isEmpty()) return false;
                Option<T> option30 = this.UnliftT14$6.unapply(option29.get());
                if (option30.isEmpty()) return false;
                if (!($colon$colon14.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                Option<Trees.TreeApi> option31 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                if (option31.isEmpty()) return false;
                Option<T> option32 = this.UnliftT15$5.unapply(option31.get());
                if (option32.isEmpty()) return false;
                if (!($colon$colon15.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon16 = ($colon$colon)$colon$colon15.tl$1();
                Option<Trees.TreeApi> option33 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon16.head());
                if (option33.isEmpty()) return false;
                Option<T> option34 = this.UnliftT16$4.unapply(option33.get());
                if (option34.isEmpty()) return false;
                if (!($colon$colon16.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon17 = ($colon$colon)$colon$colon16.tl$1();
                Option<Trees.TreeApi> option35 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon17.head());
                if (option35.isEmpty()) return false;
                Option<T> option36 = this.UnliftT17$3.unapply(option35.get());
                if (option36.isEmpty()) return false;
                if (!($colon$colon17.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon18 = ($colon$colon)$colon$colon17.tl$1();
                Option<Trees.TreeApi> option37 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon18.head());
                if (option37.isEmpty()) return false;
                Option<T> option38 = this.UnliftT18$2.unapply(option37.get());
                if (option38.isEmpty()) return false;
                if (!($colon$colon18.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon19 = ($colon$colon)$colon$colon18.tl$1();
                Option<Trees.TreeApi> option39 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon19.head());
                if (option39.isEmpty()) return false;
                Option<T> option40 = this.UnliftT19$1.unapply(option39.get());
                if (option40.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon19.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$18 = UnliftT1$18;
                this.UnliftT2$18 = UnliftT2$18;
                this.UnliftT3$17 = UnliftT3$17;
                this.UnliftT4$16 = UnliftT4$16;
                this.UnliftT5$15 = UnliftT5$15;
                this.UnliftT6$14 = UnliftT6$14;
                this.UnliftT7$13 = UnliftT7$13;
                this.UnliftT8$12 = UnliftT8$12;
                this.UnliftT9$11 = UnliftT9$11;
                this.UnliftT10$10 = UnliftT10$10;
                this.UnliftT11$9 = UnliftT11$9;
                this.UnliftT12$8 = UnliftT12$8;
                this.UnliftT13$7 = UnliftT13$7;
                this.UnliftT14$6 = UnliftT14$6;
                this.UnliftT15$5 = UnliftT15$5;
                this.UnliftT16$4 = UnliftT16$4;
                this.UnliftT17$3 = UnliftT17$3;
                this.UnliftT18$2 = UnliftT18$2;
                this.UnliftT19$1 = UnliftT19$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple20(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8, Liftables.Unliftable UnliftT9, Liftables.Unliftable UnliftT10, Liftables.Unliftable UnliftT11, Liftables.Unliftable UnliftT12, Liftables.Unliftable UnliftT13, Liftables.Unliftable UnliftT14, Liftables.Unliftable UnliftT15, Liftables.Unliftable UnliftT16, Liftables.Unliftable UnliftT17, Liftables.Unliftable UnliftT18, Liftables.Unliftable UnliftT19, Liftables.Unliftable UnliftT20) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15, UnliftT16, UnliftT17, UnliftT18, UnliftT19, UnliftT20){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$19;
            private final Liftables.Unliftable UnliftT2$19;
            private final Liftables.Unliftable UnliftT3$18;
            private final Liftables.Unliftable UnliftT4$17;
            private final Liftables.Unliftable UnliftT5$16;
            private final Liftables.Unliftable UnliftT6$15;
            private final Liftables.Unliftable UnliftT7$14;
            private final Liftables.Unliftable UnliftT8$13;
            private final Liftables.Unliftable UnliftT9$12;
            private final Liftables.Unliftable UnliftT10$11;
            private final Liftables.Unliftable UnliftT11$10;
            private final Liftables.Unliftable UnliftT12$9;
            private final Liftables.Unliftable UnliftT13$8;
            private final Liftables.Unliftable UnliftT14$7;
            private final Liftables.Unliftable UnliftT15$6;
            private final Liftables.Unliftable UnliftT16$5;
            private final Liftables.Unliftable UnliftT17$4;
            private final Liftables.Unliftable UnliftT18$3;
            private final Liftables.Unliftable UnliftT19$2;
            private final Liftables.Unliftable UnliftT20$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x25, Function1<A1, B1> function1) {
                void var65_67;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x25);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$19.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$19.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$18.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$17.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$16.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$15.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$14.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$13.unapply(option18.get())).isEmpty() && $colon$colon8.tl$1() instanceof $colon$colon) {
                                                    Option<T> option19;
                                                    $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                                                    Option<Trees.TreeApi> option20 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                                                    if (!option20.isEmpty() && !(option19 = this.UnliftT9$12.unapply(option20.get())).isEmpty() && $colon$colon9.tl$1() instanceof $colon$colon) {
                                                        Option<T> option21;
                                                        $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                                                        Option<Trees.TreeApi> option22 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                                                        if (!option22.isEmpty() && !(option21 = this.UnliftT10$11.unapply(option22.get())).isEmpty() && $colon$colon10.tl$1() instanceof $colon$colon) {
                                                            Option<T> option23;
                                                            $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                                                            Option<Trees.TreeApi> option24 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                                                            if (!option24.isEmpty() && !(option23 = this.UnliftT11$10.unapply(option24.get())).isEmpty() && $colon$colon11.tl$1() instanceof $colon$colon) {
                                                                Option<T> option25;
                                                                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                                                                Option<Trees.TreeApi> option26 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                                                                if (!option26.isEmpty() && !(option25 = this.UnliftT12$9.unapply(option26.get())).isEmpty() && $colon$colon12.tl$1() instanceof $colon$colon) {
                                                                    Option<T> option27;
                                                                    $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                                                                    Option<Trees.TreeApi> option28 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                                                                    if (!option28.isEmpty() && !(option27 = this.UnliftT13$8.unapply(option28.get())).isEmpty() && $colon$colon13.tl$1() instanceof $colon$colon) {
                                                                        Option<T> option29;
                                                                        $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                                                                        Option<Trees.TreeApi> option30 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                                                                        if (!option30.isEmpty() && !(option29 = this.UnliftT14$7.unapply(option30.get())).isEmpty() && $colon$colon14.tl$1() instanceof $colon$colon) {
                                                                            Option<T> option31;
                                                                            $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                                                                            Option<Trees.TreeApi> option32 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                                                                            if (!option32.isEmpty() && !(option31 = this.UnliftT15$6.unapply(option32.get())).isEmpty() && $colon$colon15.tl$1() instanceof $colon$colon) {
                                                                                Option<T> option33;
                                                                                $colon$colon $colon$colon16 = ($colon$colon)$colon$colon15.tl$1();
                                                                                Option<Trees.TreeApi> option34 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon16.head());
                                                                                if (!option34.isEmpty() && !(option33 = this.UnliftT16$5.unapply(option34.get())).isEmpty() && $colon$colon16.tl$1() instanceof $colon$colon) {
                                                                                    Option<T> option35;
                                                                                    $colon$colon $colon$colon17 = ($colon$colon)$colon$colon16.tl$1();
                                                                                    Option<Trees.TreeApi> option36 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon17.head());
                                                                                    if (!option36.isEmpty() && !(option35 = this.UnliftT17$4.unapply(option36.get())).isEmpty() && $colon$colon17.tl$1() instanceof $colon$colon) {
                                                                                        Option<T> option37;
                                                                                        $colon$colon $colon$colon18 = ($colon$colon)$colon$colon17.tl$1();
                                                                                        Option<Trees.TreeApi> option38 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon18.head());
                                                                                        if (!option38.isEmpty() && !(option37 = this.UnliftT18$3.unapply(option38.get())).isEmpty() && $colon$colon18.tl$1() instanceof $colon$colon) {
                                                                                            Option<T> option39;
                                                                                            $colon$colon $colon$colon19 = ($colon$colon)$colon$colon18.tl$1();
                                                                                            Option<Trees.TreeApi> option40 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon19.head());
                                                                                            if (!option40.isEmpty() && !(option39 = this.UnliftT19$2.unapply(option40.get())).isEmpty() && $colon$colon19.tl$1() instanceof $colon$colon) {
                                                                                                Option<T> option41;
                                                                                                $colon$colon $colon$colon20 = ($colon$colon)$colon$colon19.tl$1();
                                                                                                Option<Trees.TreeApi> option42 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon20.head());
                                                                                                if (!option42.isEmpty() && !(option41 = this.UnliftT20$1.unapply(option42.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon20.tl$1())) {
                                                                                                    Tuple20<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T> tuple20 = new Tuple20<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get(), option19.get(), option21.get(), option23.get(), option25.get(), option27.get(), option29.get(), option31.get(), option33.get(), option35.get(), option37.get(), option39.get(), option41.get());
                                                                                                    return var65_67;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x25);
                return var65_67;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x25) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x25);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$19.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$19.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$18.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$17.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$16.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$15.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$14.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$13.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!($colon$colon8.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                Option<Trees.TreeApi> option19 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                if (option19.isEmpty()) return false;
                Option<T> option20 = this.UnliftT9$12.unapply(option19.get());
                if (option20.isEmpty()) return false;
                if (!($colon$colon9.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                Option<Trees.TreeApi> option21 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                if (option21.isEmpty()) return false;
                Option<T> option22 = this.UnliftT10$11.unapply(option21.get());
                if (option22.isEmpty()) return false;
                if (!($colon$colon10.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                Option<Trees.TreeApi> option23 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                if (option23.isEmpty()) return false;
                Option<T> option24 = this.UnliftT11$10.unapply(option23.get());
                if (option24.isEmpty()) return false;
                if (!($colon$colon11.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                Option<Trees.TreeApi> option25 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                if (option25.isEmpty()) return false;
                Option<T> option26 = this.UnliftT12$9.unapply(option25.get());
                if (option26.isEmpty()) return false;
                if (!($colon$colon12.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                Option<Trees.TreeApi> option27 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                if (option27.isEmpty()) return false;
                Option<T> option28 = this.UnliftT13$8.unapply(option27.get());
                if (option28.isEmpty()) return false;
                if (!($colon$colon13.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                Option<Trees.TreeApi> option29 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                if (option29.isEmpty()) return false;
                Option<T> option30 = this.UnliftT14$7.unapply(option29.get());
                if (option30.isEmpty()) return false;
                if (!($colon$colon14.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                Option<Trees.TreeApi> option31 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                if (option31.isEmpty()) return false;
                Option<T> option32 = this.UnliftT15$6.unapply(option31.get());
                if (option32.isEmpty()) return false;
                if (!($colon$colon15.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon16 = ($colon$colon)$colon$colon15.tl$1();
                Option<Trees.TreeApi> option33 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon16.head());
                if (option33.isEmpty()) return false;
                Option<T> option34 = this.UnliftT16$5.unapply(option33.get());
                if (option34.isEmpty()) return false;
                if (!($colon$colon16.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon17 = ($colon$colon)$colon$colon16.tl$1();
                Option<Trees.TreeApi> option35 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon17.head());
                if (option35.isEmpty()) return false;
                Option<T> option36 = this.UnliftT17$4.unapply(option35.get());
                if (option36.isEmpty()) return false;
                if (!($colon$colon17.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon18 = ($colon$colon)$colon$colon17.tl$1();
                Option<Trees.TreeApi> option37 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon18.head());
                if (option37.isEmpty()) return false;
                Option<T> option38 = this.UnliftT18$3.unapply(option37.get());
                if (option38.isEmpty()) return false;
                if (!($colon$colon18.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon19 = ($colon$colon)$colon$colon18.tl$1();
                Option<Trees.TreeApi> option39 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon19.head());
                if (option39.isEmpty()) return false;
                Option<T> option40 = this.UnliftT19$2.unapply(option39.get());
                if (option40.isEmpty()) return false;
                if (!($colon$colon19.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon20 = ($colon$colon)$colon$colon19.tl$1();
                Option<Trees.TreeApi> option41 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon20.head());
                if (option41.isEmpty()) return false;
                Option<T> option42 = this.UnliftT20$1.unapply(option41.get());
                if (option42.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon20.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$19 = UnliftT1$19;
                this.UnliftT2$19 = UnliftT2$19;
                this.UnliftT3$18 = UnliftT3$18;
                this.UnliftT4$17 = UnliftT4$17;
                this.UnliftT5$16 = UnliftT5$16;
                this.UnliftT6$15 = UnliftT6$15;
                this.UnliftT7$14 = UnliftT7$14;
                this.UnliftT8$13 = UnliftT8$13;
                this.UnliftT9$12 = UnliftT9$12;
                this.UnliftT10$11 = UnliftT10$11;
                this.UnliftT11$10 = UnliftT11$10;
                this.UnliftT12$9 = UnliftT12$9;
                this.UnliftT13$8 = UnliftT13$8;
                this.UnliftT14$7 = UnliftT14$7;
                this.UnliftT15$6 = UnliftT15$6;
                this.UnliftT16$5 = UnliftT16$5;
                this.UnliftT17$4 = UnliftT17$4;
                this.UnliftT18$3 = UnliftT18$3;
                this.UnliftT19$2 = UnliftT19$2;
                this.UnliftT20$1 = UnliftT20$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple21(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8, Liftables.Unliftable UnliftT9, Liftables.Unliftable UnliftT10, Liftables.Unliftable UnliftT11, Liftables.Unliftable UnliftT12, Liftables.Unliftable UnliftT13, Liftables.Unliftable UnliftT14, Liftables.Unliftable UnliftT15, Liftables.Unliftable UnliftT16, Liftables.Unliftable UnliftT17, Liftables.Unliftable UnliftT18, Liftables.Unliftable UnliftT19, Liftables.Unliftable UnliftT20, Liftables.Unliftable UnliftT21) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15, UnliftT16, UnliftT17, UnliftT18, UnliftT19, UnliftT20, UnliftT21){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$20;
            private final Liftables.Unliftable UnliftT2$20;
            private final Liftables.Unliftable UnliftT3$19;
            private final Liftables.Unliftable UnliftT4$18;
            private final Liftables.Unliftable UnliftT5$17;
            private final Liftables.Unliftable UnliftT6$16;
            private final Liftables.Unliftable UnliftT7$15;
            private final Liftables.Unliftable UnliftT8$14;
            private final Liftables.Unliftable UnliftT9$13;
            private final Liftables.Unliftable UnliftT10$12;
            private final Liftables.Unliftable UnliftT11$11;
            private final Liftables.Unliftable UnliftT12$10;
            private final Liftables.Unliftable UnliftT13$9;
            private final Liftables.Unliftable UnliftT14$8;
            private final Liftables.Unliftable UnliftT15$7;
            private final Liftables.Unliftable UnliftT16$6;
            private final Liftables.Unliftable UnliftT17$5;
            private final Liftables.Unliftable UnliftT18$4;
            private final Liftables.Unliftable UnliftT19$3;
            private final Liftables.Unliftable UnliftT20$2;
            private final Liftables.Unliftable UnliftT21$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x26, Function1<A1, B1> function1) {
                void var68_70;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x26);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$20.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$20.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$19.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$18.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$17.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$16.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$15.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$14.unapply(option18.get())).isEmpty() && $colon$colon8.tl$1() instanceof $colon$colon) {
                                                    Option<T> option19;
                                                    $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                                                    Option<Trees.TreeApi> option20 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                                                    if (!option20.isEmpty() && !(option19 = this.UnliftT9$13.unapply(option20.get())).isEmpty() && $colon$colon9.tl$1() instanceof $colon$colon) {
                                                        Option<T> option21;
                                                        $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                                                        Option<Trees.TreeApi> option22 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                                                        if (!option22.isEmpty() && !(option21 = this.UnliftT10$12.unapply(option22.get())).isEmpty() && $colon$colon10.tl$1() instanceof $colon$colon) {
                                                            Option<T> option23;
                                                            $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                                                            Option<Trees.TreeApi> option24 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                                                            if (!option24.isEmpty() && !(option23 = this.UnliftT11$11.unapply(option24.get())).isEmpty() && $colon$colon11.tl$1() instanceof $colon$colon) {
                                                                Option<T> option25;
                                                                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                                                                Option<Trees.TreeApi> option26 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                                                                if (!option26.isEmpty() && !(option25 = this.UnliftT12$10.unapply(option26.get())).isEmpty() && $colon$colon12.tl$1() instanceof $colon$colon) {
                                                                    Option<T> option27;
                                                                    $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                                                                    Option<Trees.TreeApi> option28 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                                                                    if (!option28.isEmpty() && !(option27 = this.UnliftT13$9.unapply(option28.get())).isEmpty() && $colon$colon13.tl$1() instanceof $colon$colon) {
                                                                        Option<T> option29;
                                                                        $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                                                                        Option<Trees.TreeApi> option30 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                                                                        if (!option30.isEmpty() && !(option29 = this.UnliftT14$8.unapply(option30.get())).isEmpty() && $colon$colon14.tl$1() instanceof $colon$colon) {
                                                                            Option<T> option31;
                                                                            $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                                                                            Option<Trees.TreeApi> option32 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                                                                            if (!option32.isEmpty() && !(option31 = this.UnliftT15$7.unapply(option32.get())).isEmpty() && $colon$colon15.tl$1() instanceof $colon$colon) {
                                                                                Option<T> option33;
                                                                                $colon$colon $colon$colon16 = ($colon$colon)$colon$colon15.tl$1();
                                                                                Option<Trees.TreeApi> option34 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon16.head());
                                                                                if (!option34.isEmpty() && !(option33 = this.UnliftT16$6.unapply(option34.get())).isEmpty() && $colon$colon16.tl$1() instanceof $colon$colon) {
                                                                                    Option<T> option35;
                                                                                    $colon$colon $colon$colon17 = ($colon$colon)$colon$colon16.tl$1();
                                                                                    Option<Trees.TreeApi> option36 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon17.head());
                                                                                    if (!option36.isEmpty() && !(option35 = this.UnliftT17$5.unapply(option36.get())).isEmpty() && $colon$colon17.tl$1() instanceof $colon$colon) {
                                                                                        Option<T> option37;
                                                                                        $colon$colon $colon$colon18 = ($colon$colon)$colon$colon17.tl$1();
                                                                                        Option<Trees.TreeApi> option38 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon18.head());
                                                                                        if (!option38.isEmpty() && !(option37 = this.UnliftT18$4.unapply(option38.get())).isEmpty() && $colon$colon18.tl$1() instanceof $colon$colon) {
                                                                                            Option<T> option39;
                                                                                            $colon$colon $colon$colon19 = ($colon$colon)$colon$colon18.tl$1();
                                                                                            Option<Trees.TreeApi> option40 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon19.head());
                                                                                            if (!option40.isEmpty() && !(option39 = this.UnliftT19$3.unapply(option40.get())).isEmpty() && $colon$colon19.tl$1() instanceof $colon$colon) {
                                                                                                Option<T> option41;
                                                                                                $colon$colon $colon$colon20 = ($colon$colon)$colon$colon19.tl$1();
                                                                                                Option<Trees.TreeApi> option42 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon20.head());
                                                                                                if (!option42.isEmpty() && !(option41 = this.UnliftT20$2.unapply(option42.get())).isEmpty() && $colon$colon20.tl$1() instanceof $colon$colon) {
                                                                                                    Option<T> option43;
                                                                                                    $colon$colon $colon$colon21 = ($colon$colon)$colon$colon20.tl$1();
                                                                                                    Option<Trees.TreeApi> option44 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon21.head());
                                                                                                    if (!option44.isEmpty() && !(option43 = this.UnliftT21$1.unapply(option44.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon21.tl$1())) {
                                                                                                        Tuple21<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T> tuple21 = new Tuple21<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get(), option19.get(), option21.get(), option23.get(), option25.get(), option27.get(), option29.get(), option31.get(), option33.get(), option35.get(), option37.get(), option39.get(), option41.get(), option43.get());
                                                                                                        return var68_70;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x26);
                return var68_70;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x26) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x26);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$20.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$20.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$19.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$18.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$17.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$16.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$15.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$14.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!($colon$colon8.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                Option<Trees.TreeApi> option19 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                if (option19.isEmpty()) return false;
                Option<T> option20 = this.UnliftT9$13.unapply(option19.get());
                if (option20.isEmpty()) return false;
                if (!($colon$colon9.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                Option<Trees.TreeApi> option21 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                if (option21.isEmpty()) return false;
                Option<T> option22 = this.UnliftT10$12.unapply(option21.get());
                if (option22.isEmpty()) return false;
                if (!($colon$colon10.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                Option<Trees.TreeApi> option23 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                if (option23.isEmpty()) return false;
                Option<T> option24 = this.UnliftT11$11.unapply(option23.get());
                if (option24.isEmpty()) return false;
                if (!($colon$colon11.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                Option<Trees.TreeApi> option25 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                if (option25.isEmpty()) return false;
                Option<T> option26 = this.UnliftT12$10.unapply(option25.get());
                if (option26.isEmpty()) return false;
                if (!($colon$colon12.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                Option<Trees.TreeApi> option27 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                if (option27.isEmpty()) return false;
                Option<T> option28 = this.UnliftT13$9.unapply(option27.get());
                if (option28.isEmpty()) return false;
                if (!($colon$colon13.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                Option<Trees.TreeApi> option29 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                if (option29.isEmpty()) return false;
                Option<T> option30 = this.UnliftT14$8.unapply(option29.get());
                if (option30.isEmpty()) return false;
                if (!($colon$colon14.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                Option<Trees.TreeApi> option31 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                if (option31.isEmpty()) return false;
                Option<T> option32 = this.UnliftT15$7.unapply(option31.get());
                if (option32.isEmpty()) return false;
                if (!($colon$colon15.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon16 = ($colon$colon)$colon$colon15.tl$1();
                Option<Trees.TreeApi> option33 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon16.head());
                if (option33.isEmpty()) return false;
                Option<T> option34 = this.UnliftT16$6.unapply(option33.get());
                if (option34.isEmpty()) return false;
                if (!($colon$colon16.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon17 = ($colon$colon)$colon$colon16.tl$1();
                Option<Trees.TreeApi> option35 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon17.head());
                if (option35.isEmpty()) return false;
                Option<T> option36 = this.UnliftT17$5.unapply(option35.get());
                if (option36.isEmpty()) return false;
                if (!($colon$colon17.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon18 = ($colon$colon)$colon$colon17.tl$1();
                Option<Trees.TreeApi> option37 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon18.head());
                if (option37.isEmpty()) return false;
                Option<T> option38 = this.UnliftT18$4.unapply(option37.get());
                if (option38.isEmpty()) return false;
                if (!($colon$colon18.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon19 = ($colon$colon)$colon$colon18.tl$1();
                Option<Trees.TreeApi> option39 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon19.head());
                if (option39.isEmpty()) return false;
                Option<T> option40 = this.UnliftT19$3.unapply(option39.get());
                if (option40.isEmpty()) return false;
                if (!($colon$colon19.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon20 = ($colon$colon)$colon$colon19.tl$1();
                Option<Trees.TreeApi> option41 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon20.head());
                if (option41.isEmpty()) return false;
                Option<T> option42 = this.UnliftT20$2.unapply(option41.get());
                if (option42.isEmpty()) return false;
                if (!($colon$colon20.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon21 = ($colon$colon)$colon$colon20.tl$1();
                Option<Trees.TreeApi> option43 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon21.head());
                if (option43.isEmpty()) return false;
                Option<T> option44 = this.UnliftT21$1.unapply(option43.get());
                if (option44.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon21.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$20 = UnliftT1$20;
                this.UnliftT2$20 = UnliftT2$20;
                this.UnliftT3$19 = UnliftT3$19;
                this.UnliftT4$18 = UnliftT4$18;
                this.UnliftT5$17 = UnliftT5$17;
                this.UnliftT6$16 = UnliftT6$16;
                this.UnliftT7$15 = UnliftT7$15;
                this.UnliftT8$14 = UnliftT8$14;
                this.UnliftT9$13 = UnliftT9$13;
                this.UnliftT10$12 = UnliftT10$12;
                this.UnliftT11$11 = UnliftT11$11;
                this.UnliftT12$10 = UnliftT12$10;
                this.UnliftT13$9 = UnliftT13$9;
                this.UnliftT14$8 = UnliftT14$8;
                this.UnliftT15$7 = UnliftT15$7;
                this.UnliftT16$6 = UnliftT16$6;
                this.UnliftT17$5 = UnliftT17$5;
                this.UnliftT18$4 = UnliftT18$4;
                this.UnliftT19$3 = UnliftT19$3;
                this.UnliftT20$2 = UnliftT20$2;
                this.UnliftT21$1 = UnliftT21$1;
            }
        });
    }

    public static Liftables.Unliftable unliftTuple22(StandardLiftables.StandardUnliftableInstances $this, Liftables.Unliftable UnliftT1, Liftables.Unliftable UnliftT2, Liftables.Unliftable UnliftT3, Liftables.Unliftable UnliftT4, Liftables.Unliftable UnliftT5, Liftables.Unliftable UnliftT6, Liftables.Unliftable UnliftT7, Liftables.Unliftable UnliftT8, Liftables.Unliftable UnliftT9, Liftables.Unliftable UnliftT10, Liftables.Unliftable UnliftT11, Liftables.Unliftable UnliftT12, Liftables.Unliftable UnliftT13, Liftables.Unliftable UnliftT14, Liftables.Unliftable UnliftT15, Liftables.Unliftable UnliftT16, Liftables.Unliftable UnliftT17, Liftables.Unliftable UnliftT18, Liftables.Unliftable UnliftT19, Liftables.Unliftable UnliftT20, Liftables.Unliftable UnliftT21, Liftables.Unliftable UnliftT22) {
        return ((Liftables)((Object)$this.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).Unliftable().apply(new Serializable($this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15, UnliftT16, UnliftT17, UnliftT18, UnliftT19, UnliftT20, UnliftT21, UnliftT22){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StandardLiftables.StandardUnliftableInstances $outer;
            private final Liftables.Unliftable UnliftT1$21;
            private final Liftables.Unliftable UnliftT2$21;
            private final Liftables.Unliftable UnliftT3$20;
            private final Liftables.Unliftable UnliftT4$19;
            private final Liftables.Unliftable UnliftT5$18;
            private final Liftables.Unliftable UnliftT6$17;
            private final Liftables.Unliftable UnliftT7$16;
            private final Liftables.Unliftable UnliftT8$15;
            private final Liftables.Unliftable UnliftT9$14;
            private final Liftables.Unliftable UnliftT10$13;
            private final Liftables.Unliftable UnliftT11$12;
            private final Liftables.Unliftable UnliftT12$11;
            private final Liftables.Unliftable UnliftT13$10;
            private final Liftables.Unliftable UnliftT14$9;
            private final Liftables.Unliftable UnliftT15$8;
            private final Liftables.Unliftable UnliftT16$7;
            private final Liftables.Unliftable UnliftT17$6;
            private final Liftables.Unliftable UnliftT18$5;
            private final Liftables.Unliftable UnliftT19$4;
            private final Liftables.Unliftable UnliftT20$3;
            private final Liftables.Unliftable UnliftT21$2;
            private final Liftables.Unliftable UnliftT22$1;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public final <A1 extends Trees.TreeApi, B1> B1 applyOrElse(A1 x27, Function1<A1, B1> function1) {
                void var71_73;
                Option<List<Trees.TreeApi>> option;
                Option<Trees.TreeApi> option2 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x27);
                if (!option2.isEmpty() && !(option = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option2.get())).isEmpty() && option.get() instanceof $colon$colon) {
                    Option<T> option3;
                    $colon$colon $colon$colon = ($colon$colon)option.get();
                    Option<Trees.TreeApi> option4 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                    if (!option4.isEmpty() && !(option3 = this.UnliftT1$21.unapply(option4.get())).isEmpty() && $colon$colon.tl$1() instanceof $colon$colon) {
                        Option<T> option5;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Trees.TreeApi> option6 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                        if (!option6.isEmpty() && !(option5 = this.UnliftT2$21.unapply(option6.get())).isEmpty() && $colon$colon2.tl$1() instanceof $colon$colon) {
                            Option<T> option7;
                            $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                            Option<Trees.TreeApi> option8 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                            if (!option8.isEmpty() && !(option7 = this.UnliftT3$20.unapply(option8.get())).isEmpty() && $colon$colon3.tl$1() instanceof $colon$colon) {
                                Option<T> option9;
                                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                                Option<Trees.TreeApi> option10 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                                if (!option10.isEmpty() && !(option9 = this.UnliftT4$19.unapply(option10.get())).isEmpty() && $colon$colon4.tl$1() instanceof $colon$colon) {
                                    Option<T> option11;
                                    $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                                    Option<Trees.TreeApi> option12 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                                    if (!option12.isEmpty() && !(option11 = this.UnliftT5$18.unapply(option12.get())).isEmpty() && $colon$colon5.tl$1() instanceof $colon$colon) {
                                        Option<T> option13;
                                        $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                                        Option<Trees.TreeApi> option14 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                                        if (!option14.isEmpty() && !(option13 = this.UnliftT6$17.unapply(option14.get())).isEmpty() && $colon$colon6.tl$1() instanceof $colon$colon) {
                                            Option<T> option15;
                                            $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                                            Option<Trees.TreeApi> option16 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                                            if (!option16.isEmpty() && !(option15 = this.UnliftT7$16.unapply(option16.get())).isEmpty() && $colon$colon7.tl$1() instanceof $colon$colon) {
                                                Option<T> option17;
                                                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                                                Option<Trees.TreeApi> option18 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                                                if (!option18.isEmpty() && !(option17 = this.UnliftT8$15.unapply(option18.get())).isEmpty() && $colon$colon8.tl$1() instanceof $colon$colon) {
                                                    Option<T> option19;
                                                    $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                                                    Option<Trees.TreeApi> option20 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                                                    if (!option20.isEmpty() && !(option19 = this.UnliftT9$14.unapply(option20.get())).isEmpty() && $colon$colon9.tl$1() instanceof $colon$colon) {
                                                        Option<T> option21;
                                                        $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                                                        Option<Trees.TreeApi> option22 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                                                        if (!option22.isEmpty() && !(option21 = this.UnliftT10$13.unapply(option22.get())).isEmpty() && $colon$colon10.tl$1() instanceof $colon$colon) {
                                                            Option<T> option23;
                                                            $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                                                            Option<Trees.TreeApi> option24 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                                                            if (!option24.isEmpty() && !(option23 = this.UnliftT11$12.unapply(option24.get())).isEmpty() && $colon$colon11.tl$1() instanceof $colon$colon) {
                                                                Option<T> option25;
                                                                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                                                                Option<Trees.TreeApi> option26 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                                                                if (!option26.isEmpty() && !(option25 = this.UnliftT12$11.unapply(option26.get())).isEmpty() && $colon$colon12.tl$1() instanceof $colon$colon) {
                                                                    Option<T> option27;
                                                                    $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                                                                    Option<Trees.TreeApi> option28 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                                                                    if (!option28.isEmpty() && !(option27 = this.UnliftT13$10.unapply(option28.get())).isEmpty() && $colon$colon13.tl$1() instanceof $colon$colon) {
                                                                        Option<T> option29;
                                                                        $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                                                                        Option<Trees.TreeApi> option30 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                                                                        if (!option30.isEmpty() && !(option29 = this.UnliftT14$9.unapply(option30.get())).isEmpty() && $colon$colon14.tl$1() instanceof $colon$colon) {
                                                                            Option<T> option31;
                                                                            $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                                                                            Option<Trees.TreeApi> option32 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                                                                            if (!option32.isEmpty() && !(option31 = this.UnliftT15$8.unapply(option32.get())).isEmpty() && $colon$colon15.tl$1() instanceof $colon$colon) {
                                                                                Option<T> option33;
                                                                                $colon$colon $colon$colon16 = ($colon$colon)$colon$colon15.tl$1();
                                                                                Option<Trees.TreeApi> option34 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon16.head());
                                                                                if (!option34.isEmpty() && !(option33 = this.UnliftT16$7.unapply(option34.get())).isEmpty() && $colon$colon16.tl$1() instanceof $colon$colon) {
                                                                                    Option<T> option35;
                                                                                    $colon$colon $colon$colon17 = ($colon$colon)$colon$colon16.tl$1();
                                                                                    Option<Trees.TreeApi> option36 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon17.head());
                                                                                    if (!option36.isEmpty() && !(option35 = this.UnliftT17$6.unapply(option36.get())).isEmpty() && $colon$colon17.tl$1() instanceof $colon$colon) {
                                                                                        Option<T> option37;
                                                                                        $colon$colon $colon$colon18 = ($colon$colon)$colon$colon17.tl$1();
                                                                                        Option<Trees.TreeApi> option38 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon18.head());
                                                                                        if (!option38.isEmpty() && !(option37 = this.UnliftT18$5.unapply(option38.get())).isEmpty() && $colon$colon18.tl$1() instanceof $colon$colon) {
                                                                                            Option<T> option39;
                                                                                            $colon$colon $colon$colon19 = ($colon$colon)$colon$colon18.tl$1();
                                                                                            Option<Trees.TreeApi> option40 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon19.head());
                                                                                            if (!option40.isEmpty() && !(option39 = this.UnliftT19$4.unapply(option40.get())).isEmpty() && $colon$colon19.tl$1() instanceof $colon$colon) {
                                                                                                Option<T> option41;
                                                                                                $colon$colon $colon$colon20 = ($colon$colon)$colon$colon19.tl$1();
                                                                                                Option<Trees.TreeApi> option42 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon20.head());
                                                                                                if (!option42.isEmpty() && !(option41 = this.UnliftT20$3.unapply(option42.get())).isEmpty() && $colon$colon20.tl$1() instanceof $colon$colon) {
                                                                                                    Option<T> option43;
                                                                                                    $colon$colon $colon$colon21 = ($colon$colon)$colon$colon20.tl$1();
                                                                                                    Option<Trees.TreeApi> option44 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon21.head());
                                                                                                    if (!option44.isEmpty() && !(option43 = this.UnliftT21$2.unapply(option44.get())).isEmpty() && $colon$colon21.tl$1() instanceof $colon$colon) {
                                                                                                        Option<T> option45;
                                                                                                        $colon$colon $colon$colon22 = ($colon$colon)$colon$colon21.tl$1();
                                                                                                        Option<Trees.TreeApi> option46 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon22.head());
                                                                                                        if (!option46.isEmpty() && !(option45 = this.UnliftT22$1.unapply(option46.get())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon22.tl$1())) {
                                                                                                            Tuple22<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T> tuple22 = new Tuple22<T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T>(option3.get(), option5.get(), option7.get(), option9.get(), option11.get(), option13.get(), option15.get(), option17.get(), option19.get(), option21.get(), option23.get(), option25.get(), option27.get(), option29.get(), option31.get(), option33.get(), option35.get(), option37.get(), option39.get(), option41.get(), option43.get(), option45.get());
                                                                                                            return var71_73;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                B1 B1 = function1.apply(x27);
                return var71_73;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean isDefinedAt(Trees.TreeApi x27) {
                Option<Trees.TreeApi> option = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply(x27);
                if (option.isEmpty()) return false;
                Option<List<Trees.TreeApi>> option2 = ((Internals)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).internal().reificationSupport().SyntacticTuple().unapply(option.get());
                if (option2.isEmpty()) return false;
                if (!(option2.get() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon = ($colon$colon)option2.get();
                Option<Trees.TreeApi> option3 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon.head());
                if (option3.isEmpty()) return false;
                Option<T> option4 = this.UnliftT1$21.unapply(option3.get());
                if (option4.isEmpty()) return false;
                if (!($colon$colon.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                Option<Trees.TreeApi> option5 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon2.head());
                if (option5.isEmpty()) return false;
                Option<T> option6 = this.UnliftT2$21.unapply(option5.get());
                if (option6.isEmpty()) return false;
                if (!($colon$colon2.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon3 = ($colon$colon)$colon$colon2.tl$1();
                Option<Trees.TreeApi> option7 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon3.head());
                if (option7.isEmpty()) return false;
                Option<T> option8 = this.UnliftT3$20.unapply(option7.get());
                if (option8.isEmpty()) return false;
                if (!($colon$colon3.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon4 = ($colon$colon)$colon$colon3.tl$1();
                Option<Trees.TreeApi> option9 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon4.head());
                if (option9.isEmpty()) return false;
                Option<T> option10 = this.UnliftT4$19.unapply(option9.get());
                if (option10.isEmpty()) return false;
                if (!($colon$colon4.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon5 = ($colon$colon)$colon$colon4.tl$1();
                Option<Trees.TreeApi> option11 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon5.head());
                if (option11.isEmpty()) return false;
                Option<T> option12 = this.UnliftT5$18.unapply(option11.get());
                if (option12.isEmpty()) return false;
                if (!($colon$colon5.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon6 = ($colon$colon)$colon$colon5.tl$1();
                Option<Trees.TreeApi> option13 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon6.head());
                if (option13.isEmpty()) return false;
                Option<T> option14 = this.UnliftT6$17.unapply(option13.get());
                if (option14.isEmpty()) return false;
                if (!($colon$colon6.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon7 = ($colon$colon)$colon$colon6.tl$1();
                Option<Trees.TreeApi> option15 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon7.head());
                if (option15.isEmpty()) return false;
                Option<T> option16 = this.UnliftT7$16.unapply(option15.get());
                if (option16.isEmpty()) return false;
                if (!($colon$colon7.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon8 = ($colon$colon)$colon$colon7.tl$1();
                Option<Trees.TreeApi> option17 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon8.head());
                if (option17.isEmpty()) return false;
                Option<T> option18 = this.UnliftT8$15.unapply(option17.get());
                if (option18.isEmpty()) return false;
                if (!($colon$colon8.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon9 = ($colon$colon)$colon$colon8.tl$1();
                Option<Trees.TreeApi> option19 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon9.head());
                if (option19.isEmpty()) return false;
                Option<T> option20 = this.UnliftT9$14.unapply(option19.get());
                if (option20.isEmpty()) return false;
                if (!($colon$colon9.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon10 = ($colon$colon)$colon$colon9.tl$1();
                Option<Trees.TreeApi> option21 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon10.head());
                if (option21.isEmpty()) return false;
                Option<T> option22 = this.UnliftT10$13.unapply(option21.get());
                if (option22.isEmpty()) return false;
                if (!($colon$colon10.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon11 = ($colon$colon)$colon$colon10.tl$1();
                Option<Trees.TreeApi> option23 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon11.head());
                if (option23.isEmpty()) return false;
                Option<T> option24 = this.UnliftT11$12.unapply(option23.get());
                if (option24.isEmpty()) return false;
                if (!($colon$colon11.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon12 = ($colon$colon)$colon$colon11.tl$1();
                Option<Trees.TreeApi> option25 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon12.head());
                if (option25.isEmpty()) return false;
                Option<T> option26 = this.UnliftT12$11.unapply(option25.get());
                if (option26.isEmpty()) return false;
                if (!($colon$colon12.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon13 = ($colon$colon)$colon$colon12.tl$1();
                Option<Trees.TreeApi> option27 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon13.head());
                if (option27.isEmpty()) return false;
                Option<T> option28 = this.UnliftT13$10.unapply(option27.get());
                if (option28.isEmpty()) return false;
                if (!($colon$colon13.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon14 = ($colon$colon)$colon$colon13.tl$1();
                Option<Trees.TreeApi> option29 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon14.head());
                if (option29.isEmpty()) return false;
                Option<T> option30 = this.UnliftT14$9.unapply(option29.get());
                if (option30.isEmpty()) return false;
                if (!($colon$colon14.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon15 = ($colon$colon)$colon$colon14.tl$1();
                Option<Trees.TreeApi> option31 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon15.head());
                if (option31.isEmpty()) return false;
                Option<T> option32 = this.UnliftT15$8.unapply(option31.get());
                if (option32.isEmpty()) return false;
                if (!($colon$colon15.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon16 = ($colon$colon)$colon$colon15.tl$1();
                Option<Trees.TreeApi> option33 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon16.head());
                if (option33.isEmpty()) return false;
                Option<T> option34 = this.UnliftT16$7.unapply(option33.get());
                if (option34.isEmpty()) return false;
                if (!($colon$colon16.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon17 = ($colon$colon)$colon$colon16.tl$1();
                Option<Trees.TreeApi> option35 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon17.head());
                if (option35.isEmpty()) return false;
                Option<T> option36 = this.UnliftT17$6.unapply(option35.get());
                if (option36.isEmpty()) return false;
                if (!($colon$colon17.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon18 = ($colon$colon)$colon$colon17.tl$1();
                Option<Trees.TreeApi> option37 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon18.head());
                if (option37.isEmpty()) return false;
                Option<T> option38 = this.UnliftT18$5.unapply(option37.get());
                if (option38.isEmpty()) return false;
                if (!($colon$colon18.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon19 = ($colon$colon)$colon$colon18.tl$1();
                Option<Trees.TreeApi> option39 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon19.head());
                if (option39.isEmpty()) return false;
                Option<T> option40 = this.UnliftT19$4.unapply(option39.get());
                if (option40.isEmpty()) return false;
                if (!($colon$colon19.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon20 = ($colon$colon)$colon$colon19.tl$1();
                Option<Trees.TreeApi> option41 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon20.head());
                if (option41.isEmpty()) return false;
                Option<T> option42 = this.UnliftT20$3.unapply(option41.get());
                if (option42.isEmpty()) return false;
                if (!($colon$colon20.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon21 = ($colon$colon)$colon$colon20.tl$1();
                Option<Trees.TreeApi> option43 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon21.head());
                if (option43.isEmpty()) return false;
                Option<T> option44 = this.UnliftT21$2.unapply(option43.get());
                if (option44.isEmpty()) return false;
                if (!($colon$colon21.tl$1() instanceof $colon$colon)) return false;
                $colon$colon $colon$colon22 = ($colon$colon)$colon$colon21.tl$1();
                Option<Trees.TreeApi> option45 = ((ImplicitTags)((Object)this.$outer.scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer())).TreeTag().unapply($colon$colon22.head());
                if (option45.isEmpty()) return false;
                Option<T> option46 = this.UnliftT22$1.unapply(option45.get());
                if (option46.isEmpty()) return false;
                if (!((Object)Nil$.MODULE$).equals($colon$colon22.tl$1())) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.UnliftT1$21 = UnliftT1$21;
                this.UnliftT2$21 = UnliftT2$21;
                this.UnliftT3$20 = UnliftT3$20;
                this.UnliftT4$19 = UnliftT4$19;
                this.UnliftT5$18 = UnliftT5$18;
                this.UnliftT6$17 = UnliftT6$17;
                this.UnliftT7$16 = UnliftT7$16;
                this.UnliftT8$15 = UnliftT8$15;
                this.UnliftT9$14 = UnliftT9$14;
                this.UnliftT10$13 = UnliftT10$13;
                this.UnliftT11$12 = UnliftT11$12;
                this.UnliftT12$11 = UnliftT12$11;
                this.UnliftT13$10 = UnliftT13$10;
                this.UnliftT14$9 = UnliftT14$9;
                this.UnliftT15$8 = UnliftT15$8;
                this.UnliftT16$7 = UnliftT16$7;
                this.UnliftT17$6 = UnliftT17$6;
                this.UnliftT18$5 = UnliftT18$5;
                this.UnliftT19$4 = UnliftT19$4;
                this.UnliftT20$3 = UnliftT20$3;
                this.UnliftT21$2 = UnliftT21$2;
                this.UnliftT22$1 = UnliftT22$1;
            }
        });
    }

    public static void $init$(StandardLiftables.StandardUnliftableInstances $this) {
    }
}

