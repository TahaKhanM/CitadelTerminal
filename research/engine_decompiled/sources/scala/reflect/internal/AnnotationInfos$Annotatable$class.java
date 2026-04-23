/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Definitions;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;

public abstract class AnnotationInfos$Annotatable$class {
    public static List staticAnnotations(AnnotationInfos.Annotatable $this) {
        return (List)$this.annotations().filter((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(AnnotationInfos.AnnotationInfo x$1) {
                return x$1.isStatic();
            }
        }));
    }

    public static List throwsAnnotations(AnnotationInfos.Annotatable $this) {
        return $this.annotations().collect(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ AnnotationInfos.Annotatable $outer;

            public final <A1 extends AnnotationInfos.AnnotationInfo, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                Option<Symbols.Symbol> option = this.$outer.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer().ThrownException().unapply(x1);
                Symbols.Symbol symbol = option.isEmpty() ? function1.apply(x1) : option.get();
                return (B1)symbol;
            }

            public final boolean isDefinedAt(AnnotationInfos.AnnotationInfo x1) {
                Option<Symbols.Symbol> option = this.$outer.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer().ThrownException().unapply(x1);
                boolean bl = !option.isEmpty();
                return bl;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, List$.MODULE$.canBuildFrom());
    }

    public static Object addThrowsAnnotation(AnnotationInfos.Annotatable $this, Symbols.Symbol throwableSym) {
        Types.Type type;
        if (throwableSym.isMonomorphicType()) {
            type = throwableSym.tpe();
        } else {
            ((SymbolTable)$this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer()).debuglog((Function0<String>)((Object)new Serializable($this, throwableSym){
                public static final long serialVersionUID = 0L;
                private final Symbols.Symbol throwableSym$1;

                public final String apply() {
                    return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Encountered polymorphic exception `", "` while parsing class file."})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.throwableSym$1.fullName()}));
                }
                {
                    this.throwableSym$1 = throwableSym$1;
                }
            }));
            type = ((Types)((Object)$this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer())).existentialAbstraction(throwableSym.typeParams(), throwableSym.tpe());
        }
        Types.Type throwableTpe = type;
        return $this.withAnnotation($this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer().AnnotationInfo().apply(((Types)((Object)$this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer())).appliedType((Symbols.Symbol)((Definitions)((Object)$this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer())).definitions().ThrowsClass(), Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{throwableTpe})), (List<Trees.Tree>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.Literal[]{new Trees.Literal((SymbolTable)$this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer(), new Constants.Constant((SymbolTable)$this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer(), throwableTpe))})), Nil$.MODULE$));
    }

    public static boolean hasAnnotation(AnnotationInfos.Annotatable $this, Symbols.Symbol cls) {
        return AnnotationInfos$Annotatable$class.dropOtherAnnotations($this, $this.annotations(), cls) != Nil$.MODULE$;
    }

    public static Option getAnnotation(AnnotationInfos.Annotatable $this, Symbols.Symbol cls) {
        Option option;
        List list2 = AnnotationInfos$Annotatable$class.dropOtherAnnotations($this, $this.annotations(), cls);
        if (list2 instanceof $colon$colon) {
            $colon$colon $colon$colon = ($colon$colon)list2;
            option = new Some($colon$colon.head());
        } else {
            option = None$.MODULE$;
        }
        return option;
    }

    public static Object removeAnnotation(AnnotationInfos.Annotatable $this, Symbols.Symbol cls) {
        return $this.filterAnnotations((Function1<AnnotationInfos.AnnotationInfo, Object>)((Object)new Serializable($this, cls){
            public static final long serialVersionUID = 0L;
            private final Symbols.Symbol cls$1;

            public final boolean apply(AnnotationInfos.AnnotationInfo ann) {
                return !ann.matches(this.cls$1);
            }
            {
                this.cls$1 = cls$1;
            }
        }));
    }

    public static final Object withAnnotation(AnnotationInfos.Annotatable $this, AnnotationInfos.AnnotationInfo annot) {
        return $this.withAnnotations((List<AnnotationInfos.AnnotationInfo>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new AnnotationInfos.AnnotationInfo[]{annot})));
    }

    private static List dropOtherAnnotations(AnnotationInfos.Annotatable $this, List anns, Symbols.Symbol cls) {
        block6: {
            List list2;
            block5: {
                while (anns instanceof $colon$colon) {
                    $colon$colon $colon$colon = ($colon$colon)anns;
                    if (((AnnotationInfos.AnnotationInfo)$colon$colon.head()).matches(cls)) {
                        list2 = anns;
                        break block5;
                    }
                    anns = $colon$colon.tl$1();
                }
                if (!((Object)Nil$.MODULE$).equals(anns)) break block6;
                list2 = Nil$.MODULE$;
            }
            return list2;
        }
        throw new MatchError(anns);
    }

    public static void $init$(AnnotationInfos.Annotatable $this) {
    }
}

