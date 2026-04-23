/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.transform;

import scala.Function1;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.ListBuffer$;
import scala.reflect.ClassTag$;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.HasFlags;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.StdAttachments;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.tpe.TypeMaps;
import scala.reflect.internal.transform.UnCurry;
import scala.reflect.internal.transform.UnCurry$;
import scala.reflect.internal.transform.UnCurry$$anon$1$;
import scala.runtime.BoxedUnit;
import scala.runtime.ObjectRef;

public abstract class UnCurry$class {
    public static Types.Type scala$reflect$internal$transform$UnCurry$$expandAlias(UnCurry $this, Types.Type tp) {
        return tp.isHigherKinded() ? tp : tp.normalize();
    }

    public static Symbols.Symbol scala$reflect$internal$transform$UnCurry$$varargForwarderSym(UnCurry $this, Symbols.Symbol currentClass, Symbols.Symbol origSym, Types.Type newInfo) {
        Symbols.Symbol forwSym = origSym.cloneSymbol(currentClass, 0x80000200000L | origSym.flags() & (long)(~16), origSym.name().toTermName()).withoutAnnotations();
        List isRepeated = ((List)origSym.info().paramss().flatten((Function1)Predef$.MODULE$.$conforms())).map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ UnCurry $outer;

            public final boolean apply(Symbols.Symbol sym) {
                return this.$outer.global().definitions().isRepeatedParamType(sym.tpe());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, List$.MODULE$.canBuildFrom());
        newInfo.paramss().head();
        $this.global().foreach2((List)forwSym.paramss().flatten((Function1)Predef$.MODULE$.$conforms()), isRepeated, new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ UnCurry $outer;

            public final void apply(Symbols.Symbol p, boolean isRep) {
                if (isRep) {
                    p.setInfo(UnCurry$class.toArrayType$1(this.$outer, p.info(), p));
                }
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
        origSym.updateAttachment(new UnCurry.VarargsSymbolAttachment($this, forwSym), ClassTag$.MODULE$.apply(UnCurry.VarargsSymbolAttachment.class));
        return forwSym;
    }

    public static Types.Type transformInfo(UnCurry $this, Symbols.Symbol sym, Types.Type tp) {
        return sym.isType() ? $this.scala$reflect$internal$transform$UnCurry$$uncurryType().apply(tp) : $this.uncurry().apply(tp);
    }

    public static final Types.Type toArrayType$1(UnCurry $this, Types.Type tp, Symbols.Symbol newParam) {
        Types.Type type;
        Types.Type arg = $this.global().definitions().elementType($this.global().definitions().SeqClass(), tp);
        if (arg.typeSymbol().isTypeParameterOrSkolem() && !arg.$less$colon$less($this.global().definitions().AnyRefTpe())) {
            newParam.updateAttachment(new StdAttachments.TypeParamVarargsAttachment($this.global(), arg), ClassTag$.MODULE$.apply(StdAttachments.TypeParamVarargsAttachment.class));
            type = $this.global().definitions().ObjectTpe();
        } else {
            type = arg;
        }
        Types.Type elem = type;
        return $this.global().definitions().arrayType(elem);
    }

    public static void $init$(UnCurry $this) {
        $this.scala$reflect$internal$transform$UnCurry$_setter_$uncurry_$eq(new TypeMaps.TypeMap($this){
            private final /* synthetic */ UnCurry $outer;

            public Types.Type apply(Types.Type tp0) {
                while (true) {
                    $colon$colon $colon$colon;
                    Types.ExistentialType existentialType;
                    Types.Type tp = UnCurry$class.scala$reflect$internal$transform$UnCurry$$expandAlias(this.$outer, tp0);
                    boolean bl = false;
                    ObjectRef<Object> objectRef = ObjectRef.create(null);
                    if (tp instanceof Types.MethodType) {
                        bl = true;
                        objectRef.elem = (Types.MethodType)tp;
                        if (((Types.MethodType)objectRef.elem).resultType() instanceof Types.MethodType) {
                            Types.MethodType methodType = (Types.MethodType)((Types.MethodType)objectRef.elem).resultType();
                            TypeMaps.TypeMap packSymbolsMap = new TypeMaps.TypeMap(this, objectRef){
                                private final /* synthetic */ UnCurry$.anon.2 $outer;
                                private final ObjectRef x2$1;

                                public Types.Type apply(Types.Type tp) {
                                    return this.$outer.scala$reflect$internal$transform$UnCurry$$anon$$$outer().global().packSymbols(((Types.MethodType)this.x2$1.elem).params(), tp, this.$outer.scala$reflect$internal$transform$UnCurry$$anon$$$outer().global().packSymbols$default$3());
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                    this.x2$1 = x2$1;
                                    super($outer.scala$reflect$internal$transform$UnCurry$$anon$$$outer().global());
                                }
                            };
                            List<Symbols.Symbol> existentiallyAbstractedParam1s = packSymbolsMap.mapOver(methodType.params());
                            Types.Type substitutedResult = methodType.resultType().substSym(methodType.params(), existentiallyAbstractedParam1s);
                            List<Symbols.Symbol> list2 = ((Types.MethodType)objectRef.elem).params();
                            tp0 = new Types.MethodType(this.$outer.global(), existentiallyAbstractedParam1s.$colon$colon$colon(list2), substitutedResult);
                            continue;
                        }
                    }
                    if (bl && ((Types.MethodType)objectRef.elem).resultType() instanceof Types.ExistentialType && (existentialType = (Types.ExistentialType)((Types.MethodType)objectRef.elem).resultType()).underlying() instanceof Types.MethodType) {
                        throw this.$outer.global().abort("unexpected curried method types with intervening existential");
                    }
                    if (bl && ((Types.MethodType)objectRef.elem).params() instanceof $colon$colon && ((HasFlags)($colon$colon = ($colon$colon)((Types.MethodType)objectRef.elem).params()).head()).isImplicit()) {
                        Symbols.Symbol symbol = ((Symbols.Symbol)$colon$colon.head()).cloneSymbol().resetFlag(512L);
                        tp0 = new Types.MethodType(this.$outer.global(), $colon$colon.tl$1().$colon$colon(symbol), ((Types.MethodType)objectRef.elem).resultType());
                        continue;
                    }
                    if (tp instanceof Types.NullaryMethodType) {
                        Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tp;
                        tp0 = new Types.MethodType(this.$outer.global(), Nil$.MODULE$, nullaryMethodType.resultType());
                        continue;
                    }
                    Option<Types.Type> option = this.$outer.DesugaredParameterType().unapply(tp);
                    if (option.isEmpty()) {
                        return UnCurry$class.scala$reflect$internal$transform$UnCurry$$expandAlias(this.$outer, this.mapOver(tp));
                    }
                    tp0 = option.get();
                }
            }

            public /* synthetic */ UnCurry scala$reflect$internal$transform$UnCurry$$anon$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super($outer.global());
            }
        });
        $this.scala$reflect$internal$transform$UnCurry$_setter_$scala$reflect$internal$transform$UnCurry$$uncurryType_$eq(new TypeMaps.TypeMap($this){
            private final /* synthetic */ UnCurry $outer;

            public Types.Type apply(Types.Type tp0) {
                Types.Type type;
                Types.ClassInfoType classInfoType;
                Types.Type tp = UnCurry$class.scala$reflect$internal$transform$UnCurry$$expandAlias(this.$outer, tp0);
                if (tp instanceof Types.ClassInfoType && !(classInfoType = (Types.ClassInfoType)tp).typeSymbol().isJavaDefined()) {
                    Types.Type type2;
                    List<Types.Type> parents1 = classInfoType.parents().mapConserve(this.$outer.uncurry());
                    ListBuffer varargOverloads = (ListBuffer)ListBuffer$.MODULE$.empty();
                    classInfoType.decls().withFilter((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ UnCurry$.anon.1 $outer;

                        public final boolean apply(Symbols.Symbol decl) {
                            return decl.annotations().exists((Function1<AnnotationInfos.AnnotationInfo, Object>)((Object)new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                private final /* synthetic */ UnCurry$$anon$1$.anonfun.apply.1 $outer;

                                public final boolean apply(AnnotationInfos.AnnotationInfo x$3) {
                                    Symbols.Symbol symbol = x$3.symbol();
                                    Symbols.ClassSymbol classSymbol = this.$outer.$outer.scala$reflect$internal$transform$UnCurry$$anon$$$outer().global().definitions().VarargsClass();
                                    return !(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null);
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            }));
                        }

                        public /* synthetic */ UnCurry$.anon.1 scala$reflect$internal$transform$UnCurry$$anon$$anonfun$$$outer() {
                            return this.$outer;
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    })).foreach(new Serializable(this, varargOverloads, classInfoType){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ UnCurry$.anon.1 $outer;
                        private final ListBuffer varargOverloads$1;
                        private final Types.ClassInfoType x2$2;

                        public final Object apply(Symbols.Symbol decl) {
                            Object object;
                            if (this.$outer.scala$reflect$internal$transform$UnCurry$$anon$$$outer().global().mexists(decl.paramss(), new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                private final /* synthetic */ UnCurry$$anon$1$.anonfun.apply.3 $outer;

                                public final boolean apply(Symbols.Symbol sym) {
                                    return this.$outer.$outer.scala$reflect$internal$transform$UnCurry$$anon$$$outer().global().definitions().isRepeatedParamType(sym.tpe());
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            })) {
                                Serializable serializable = new Serializable(this, decl){
                                    public static final long serialVersionUID = 0L;
                                    private final Symbols.Symbol decl$1;

                                    public final Types.Type apply() {
                                        return this.decl$1.info();
                                    }
                                    {
                                        this.decl$1 = decl$1;
                                    }
                                };
                                object = this.varargOverloads$1.$plus$eq(UnCurry$class.scala$reflect$internal$transform$UnCurry$$varargForwarderSym(this.$outer.scala$reflect$internal$transform$UnCurry$$anon$$$outer(), this.x2$2.typeSymbol(), decl, this.$outer.scala$reflect$internal$transform$UnCurry$$anon$$$outer().global().enteringPhase(this.$outer.scala$reflect$internal$transform$UnCurry$$anon$$$outer().global().phase().next(), serializable)));
                            } else {
                                object = BoxedUnit.UNIT;
                            }
                            return object;
                        }

                        public /* synthetic */ UnCurry$.anon.1 scala$reflect$internal$transform$UnCurry$$anon$$anonfun$$$outer() {
                            return this.$outer;
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.varargOverloads$1 = varargOverloads$1;
                            this.x2$2 = x2$2;
                        }
                    });
                    if (parents1 == classInfoType.parents() && varargOverloads.isEmpty()) {
                        type2 = tp;
                    } else {
                        Scopes.Scope newDecls = classInfoType.decls().cloneScope();
                        varargOverloads.foreach(new Serializable(this, newDecls){
                            public static final long serialVersionUID = 0L;
                            private final Scopes.Scope newDecls$1;

                            public final Symbols.Symbol apply(Symbols.Symbol sym) {
                                return this.newDecls$1.enter(sym);
                            }
                            {
                                this.newDecls$1 = newDecls$1;
                            }
                        });
                        type2 = new Types.ClassInfoType(this.$outer.global(), parents1, newDecls, classInfoType.typeSymbol());
                    }
                    type = type2;
                } else {
                    type = tp instanceof Types.PolyType ? this.mapOver(tp) : tp;
                }
                return type;
            }

            public /* synthetic */ UnCurry scala$reflect$internal$transform$UnCurry$$anon$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super($outer.global());
            }
        });
    }
}

