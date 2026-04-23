/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function1;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.mutable.StringBuilder;
import scala.reflect.api.Mirrors;
import scala.reflect.internal.Definitions;
import scala.reflect.internal.Mirrors;
import scala.reflect.internal.Names;
import scala.reflect.internal.Reporting;
import scala.reflect.internal.StdNames;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.Collections;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.util.Properties$;

public abstract class Definitions$ValueClassDefinitions$class {
    public static Nothing$ scala$reflect$internal$Definitions$$catastrophicFailure(Definitions.DefinitionsClass $this) {
        return ((Reporting)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).abort(new StringBuilder().append((Object)"Could not find value classes! This is a catastrophic failure.  scala ").append((Object)Properties$.MODULE$.versionString()).toString());
    }

    private static Symbols.ClassSymbol valueClassSymbol(Definitions.DefinitionsClass $this, Names.TypeName name) {
        Symbols.Symbol symbol = $this.getMember($this.ScalaPackageClass(), name);
        if (symbol instanceof Symbols.ClassSymbol) {
            Symbols.ClassSymbol classSymbol = (Symbols.ClassSymbol)symbol;
            return classSymbol;
        }
        throw $this.scala$reflect$internal$Definitions$$catastrophicFailure();
    }

    public static Map scala$reflect$internal$Definitions$$classesMap(Definitions.DefinitionsClass $this, Function1 f) {
        return Definitions$ValueClassDefinitions$class.symbolsMap($this, $this.ScalaValueClassesNoUnit(), f);
    }

    private static Map symbolsMap(Definitions.DefinitionsClass $this, List syms, Function1 f) {
        return ((Collections)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).mapFrom(syms, new Serializable($this, f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$1;

            public final T apply(Symbols.Symbol x) {
                return (T)this.f$1.apply(x.name());
            }
            {
                this.f$1 = f$1;
            }
        });
    }

    private static Map symbolsMapFilt(Definitions.DefinitionsClass $this, List syms, Function1 p, Function1 f) {
        return Definitions$ValueClassDefinitions$class.symbolsMap($this, (List)syms.filter((Function1)((Object)new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final Function1 p$1;

            public final boolean apply(Symbols.Symbol x) {
                return BoxesRunTime.unboxToBoolean(this.p$1.apply(x.name()));
            }
            {
                this.p$1 = p$1;
            }
        })), f);
    }

    public static Names.TypeName scala$reflect$internal$Definitions$ValueClassDefinitions$$boxedName(Definitions.DefinitionsClass $this, Names.Name name) {
        return (Names.TypeName)((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).sn().Boxed().apply(name.toTypeName());
    }

    public static Map abbrvTag(Definitions.DefinitionsClass $this) {
        return Definitions$ValueClassDefinitions$class.symbolsMap($this, $this.ScalaValueClasses(), $this.scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToTag()).withDefaultValue(BoxesRunTime.boxToCharacter('L'));
    }

    public static Map numericWeight(Definitions.DefinitionsClass $this) {
        return Definitions$ValueClassDefinitions$class.symbolsMapFilt($this, $this.ScalaValueClasses(), $this.scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToWeight().keySet(), $this.scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToWeight());
    }

    public static Map boxedModule(Definitions.DefinitionsClass $this) {
        return $this.scala$reflect$internal$Definitions$$classesMap(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Definitions.DefinitionsClass $outer;

            public final Symbols.ModuleSymbol apply(Names.Name x) {
                return ((Mirrors.RootsBase)((Mirrors)((Object)this.$outer.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).rootMirror()).getModuleByName(Definitions$ValueClassDefinitions$class.scala$reflect$internal$Definitions$ValueClassDefinitions$$boxedName(this.$outer, x));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Map boxedClass(Definitions.DefinitionsClass $this) {
        return $this.scala$reflect$internal$Definitions$$classesMap(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Definitions.DefinitionsClass $outer;

            public final Symbols.ClassSymbol apply(Names.Name x) {
                return ((Mirrors.RootsBase)((Mirrors)((Object)this.$outer.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).rootMirror()).getClassByName(Definitions$ValueClassDefinitions$class.scala$reflect$internal$Definitions$ValueClassDefinitions$$boxedName(this.$outer, x));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Map refClass(Definitions.DefinitionsClass $this) {
        return $this.scala$reflect$internal$Definitions$$classesMap(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Definitions.DefinitionsClass $outer;

            public final Symbols.ClassSymbol apply(Names.Name x) {
                return ((Mirrors.RootsBase)((Mirrors)((Object)this.$outer.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).rootMirror()).getRequiredClass(new StringBuilder().append((Object)"scala.runtime.").append(x).append((Object)"Ref").toString());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static Map volatileRefClass(Definitions.DefinitionsClass $this) {
        return $this.scala$reflect$internal$Definitions$$classesMap(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Definitions.DefinitionsClass $outer;

            public final Symbols.ClassSymbol apply(Names.Name x) {
                return ((Mirrors.RootsBase)((Mirrors)((Object)this.$outer.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).rootMirror()).getRequiredClass(new StringBuilder().append((Object)"scala.runtime.Volatile").append(x).append((Object)"Ref").toString());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static boolean isNumericSubClass(Definitions.DefinitionsClass $this, Symbols.Symbol sub, Symbols.Symbol sup) {
        return $this.numericWeight().contains(sub) && $this.numericWeight().contains(sup) && BoxesRunTime.unboxToInt($this.numericWeight().apply(sup)) % BoxesRunTime.unboxToInt($this.numericWeight().apply(sub)) == 0;
    }

    public static boolean isNumericValueClass(Definitions.DefinitionsClass $this, Symbols.Symbol sym) {
        return $this.ScalaNumericValueClasses().contains(sym);
    }

    public static boolean isGetClass(Definitions.DefinitionsClass $this, Symbols.Symbol sym) {
        Names.Name name = sym.name();
        Names.TermName termName = ((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).nme().getClass_();
        return !(name != null ? !name.equals(termName) : termName != null) && $this.getClassMethods().apply(sym);
    }

    public static Symbols.ClassSymbol UnitClass(Definitions.DefinitionsClass $this) {
        return Definitions$ValueClassDefinitions$class.valueClassSymbol($this, (Names.TypeName)((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Unit());
    }

    public static Symbols.ClassSymbol ByteClass(Definitions.DefinitionsClass $this) {
        return Definitions$ValueClassDefinitions$class.valueClassSymbol($this, (Names.TypeName)((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Byte());
    }

    public static Symbols.ClassSymbol ShortClass(Definitions.DefinitionsClass $this) {
        return Definitions$ValueClassDefinitions$class.valueClassSymbol($this, (Names.TypeName)((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Short());
    }

    public static Symbols.ClassSymbol CharClass(Definitions.DefinitionsClass $this) {
        return Definitions$ValueClassDefinitions$class.valueClassSymbol($this, (Names.TypeName)((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Char());
    }

    public static Symbols.ClassSymbol IntClass(Definitions.DefinitionsClass $this) {
        return Definitions$ValueClassDefinitions$class.valueClassSymbol($this, (Names.TypeName)((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Int());
    }

    public static Symbols.ClassSymbol LongClass(Definitions.DefinitionsClass $this) {
        return Definitions$ValueClassDefinitions$class.valueClassSymbol($this, (Names.TypeName)((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Long());
    }

    public static Symbols.ClassSymbol FloatClass(Definitions.DefinitionsClass $this) {
        return Definitions$ValueClassDefinitions$class.valueClassSymbol($this, (Names.TypeName)((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Float());
    }

    public static Symbols.ClassSymbol DoubleClass(Definitions.DefinitionsClass $this) {
        return Definitions$ValueClassDefinitions$class.valueClassSymbol($this, (Names.TypeName)((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Double());
    }

    public static Symbols.ClassSymbol BooleanClass(Definitions.DefinitionsClass $this) {
        return Definitions$ValueClassDefinitions$class.valueClassSymbol($this, (Names.TypeName)((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Boolean());
    }

    public static Symbols.TermSymbol Boolean_and(Definitions.DefinitionsClass $this) {
        return $this.getMemberMethod($this.BooleanClass(), ((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).nme().ZAND());
    }

    public static Symbols.TermSymbol Boolean_or(Definitions.DefinitionsClass $this) {
        return $this.getMemberMethod($this.BooleanClass(), ((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).nme().ZOR());
    }

    public static Symbols.TermSymbol Boolean_not(Definitions.DefinitionsClass $this) {
        return $this.getMemberMethod($this.BooleanClass(), ((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).nme().UNARY_$bang());
    }

    public static Types.Type UnitTpe(Definitions.DefinitionsClass $this) {
        return $this.UnitClass().tpe();
    }

    public static Types.Type ByteTpe(Definitions.DefinitionsClass $this) {
        return $this.ByteClass().tpe();
    }

    public static Types.Type ShortTpe(Definitions.DefinitionsClass $this) {
        return $this.ShortClass().tpe();
    }

    public static Types.Type CharTpe(Definitions.DefinitionsClass $this) {
        return $this.CharClass().tpe();
    }

    public static Types.Type IntTpe(Definitions.DefinitionsClass $this) {
        return $this.IntClass().tpe();
    }

    public static Types.Type LongTpe(Definitions.DefinitionsClass $this) {
        return $this.LongClass().tpe();
    }

    public static Types.Type FloatTpe(Definitions.DefinitionsClass $this) {
        return $this.FloatClass().tpe();
    }

    public static Types.Type DoubleTpe(Definitions.DefinitionsClass $this) {
        return $this.DoubleClass().tpe();
    }

    public static Types.Type BooleanTpe(Definitions.DefinitionsClass $this) {
        return $this.BooleanClass().tpe();
    }

    public static List ScalaNumericValueClasses(Definitions.DefinitionsClass $this) {
        return (List)$this.ScalaValueClasses().filterNot((Function1)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{$this.UnitClass(), $this.BooleanClass()})));
    }

    public static List ScalaValueClassesNoUnit(Definitions.DefinitionsClass $this) {
        return (List)$this.ScalaValueClasses().filterNot((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Definitions.DefinitionsClass $outer;

            public final boolean apply(Symbols.ClassSymbol x$1) {
                return x$1 == this.$outer.UnitClass();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    public static List ScalaValueClasses(Definitions.DefinitionsClass $this) {
        return List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.ClassSymbol[]{$this.UnitClass(), $this.BooleanClass(), $this.ByteClass(), $this.ShortClass(), $this.CharClass(), $this.IntClass(), $this.LongClass(), $this.FloatClass(), $this.DoubleClass()}));
    }

    public static List ScalaPrimitiveValueClasses(Definitions.DefinitionsClass $this) {
        return $this.ScalaValueClasses();
    }

    public static Types.Type underlyingOfValueClass(Definitions.DefinitionsClass $this, Symbols.Symbol clazz) {
        return clazz.derivedValueClassUnbox().tpe().resultType();
    }

    public static void $init$(Definitions.DefinitionsClass $this) {
        Tuple2[] tuple2Array = new Tuple2[7];
        Integer n = BoxesRunTime.boxToInteger(2);
        Names.Name name = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Byte());
        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[0] = new Tuple2<Names.Name, Integer>(name, n);
        Integer n2 = BoxesRunTime.boxToInteger(3);
        Names.Name name2 = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Char());
        Predef$ArrowAssoc$ predef$ArrowAssoc$2 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[1] = new Tuple2<Names.Name, Integer>(name2, n2);
        Integer n3 = BoxesRunTime.boxToInteger(4);
        Names.Name name3 = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Short());
        Predef$ArrowAssoc$ predef$ArrowAssoc$3 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[2] = new Tuple2<Names.Name, Integer>(name3, n3);
        Integer n4 = BoxesRunTime.boxToInteger(12);
        Names.Name name4 = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Int());
        Predef$ArrowAssoc$ predef$ArrowAssoc$4 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[3] = new Tuple2<Names.Name, Integer>(name4, n4);
        Integer n5 = BoxesRunTime.boxToInteger(24);
        Names.Name name5 = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Long());
        Predef$ArrowAssoc$ predef$ArrowAssoc$5 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[4] = new Tuple2<Names.Name, Integer>(name5, n5);
        Integer n6 = BoxesRunTime.boxToInteger(48);
        Names.Name name6 = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Float());
        Predef$ArrowAssoc$ predef$ArrowAssoc$6 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[5] = new Tuple2<Names.Name, Integer>(name6, n6);
        Integer n7 = BoxesRunTime.boxToInteger(96);
        Names.Name name7 = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Double());
        Predef$ArrowAssoc$ predef$ArrowAssoc$7 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[6] = new Tuple2<Names.Name, Integer>(name7, n7);
        $this.scala$reflect$internal$Definitions$ValueClassDefinitions$_setter_$scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToWeight_$eq((Map)Predef$.MODULE$.Map().apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array)));
        Tuple2[] tuple2Array2 = new Tuple2[9];
        Character c = BoxesRunTime.boxToCharacter('B');
        Names.Name name8 = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Byte());
        Predef$ArrowAssoc$ predef$ArrowAssoc$8 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array2[0] = new Tuple2<Names.Name, Character>(name8, c);
        Character c2 = BoxesRunTime.boxToCharacter('C');
        Names.Name name9 = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Char());
        Predef$ArrowAssoc$ predef$ArrowAssoc$9 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array2[1] = new Tuple2<Names.Name, Character>(name9, c2);
        Character c3 = BoxesRunTime.boxToCharacter('S');
        Names.Name name10 = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Short());
        Predef$ArrowAssoc$ predef$ArrowAssoc$10 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array2[2] = new Tuple2<Names.Name, Character>(name10, c3);
        Character c4 = BoxesRunTime.boxToCharacter('I');
        Names.Name name11 = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Int());
        Predef$ArrowAssoc$ predef$ArrowAssoc$11 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array2[3] = new Tuple2<Names.Name, Character>(name11, c4);
        Character c5 = BoxesRunTime.boxToCharacter('J');
        Names.Name name12 = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Long());
        Predef$ArrowAssoc$ predef$ArrowAssoc$12 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array2[4] = new Tuple2<Names.Name, Character>(name12, c5);
        Character c6 = BoxesRunTime.boxToCharacter('F');
        Names.Name name13 = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Float());
        Predef$ArrowAssoc$ predef$ArrowAssoc$13 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array2[5] = new Tuple2<Names.Name, Character>(name13, c6);
        Character c7 = BoxesRunTime.boxToCharacter('D');
        Names.Name name14 = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Double());
        Predef$ArrowAssoc$ predef$ArrowAssoc$14 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array2[6] = new Tuple2<Names.Name, Character>(name14, c7);
        Character c8 = BoxesRunTime.boxToCharacter('Z');
        Names.Name name15 = Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Boolean());
        Predef$ArrowAssoc$ predef$ArrowAssoc$15 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array2[7] = new Tuple2<Names.Name, Character>(name15, c8);
        tuple2Array2[8] = Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(((StdNames)((Object)$this.scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer())).tpnme().Unit()), BoxesRunTime.boxToCharacter('V'));
        $this.scala$reflect$internal$Definitions$ValueClassDefinitions$_setter_$scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToTag_$eq((Map)Predef$.MODULE$.Map().apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array2)));
    }
}

