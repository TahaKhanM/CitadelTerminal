/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.transform;

import scala.MatchError;
import scala.Serializable;
import scala.Tuple3;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.tpe.TypeMaps;
import scala.reflect.internal.transform.Erasure$GenericArray$;
import scala.reflect.internal.transform.Erasure$boxingErasure$;
import scala.reflect.internal.transform.Erasure$javaErasure$;
import scala.reflect.internal.transform.Erasure$scalaErasure$;
import scala.reflect.internal.transform.Erasure$specialScalaErasure$;
import scala.reflect.internal.transform.Erasure$verifiedJavaErasure$;

@ScalaSignature(bytes="\u0006\u0001\u0005mgaB\u0001\u0003!\u0003\r\ta\u0003\u0002\b\u000bJ\f7/\u001e:f\u0015\t\u0019A!A\u0005ue\u0006t7OZ8s[*\u0011QAB\u0001\tS:$XM\u001d8bY*\u0011q\u0001C\u0001\be\u00164G.Z2u\u0015\u0005I\u0011!B:dC2\f7\u0001A\n\u0003\u00011\u0001\"!\u0004\b\u000e\u0003!I!a\u0004\u0005\u0003\r\u0005s\u0017PU3g\u0011\u0015\t\u0002\u0001\"\u0001\u0013\u0003\u0019!\u0013N\\5uIQ\t1\u0003\u0005\u0002\u000e)%\u0011Q\u0003\u0003\u0002\u0005+:LG\u000fC\u0004\u0018\u0001\t\u0007i\u0011\u0001\r\u0002\r\u001ddwNY1m+\u0005I\u0002C\u0001\u000e\u001c\u001b\u0005!\u0011B\u0001\u000f\u0005\u0005-\u0019\u00160\u001c2pYR\u000b'\r\\3\b\u000by\u0001\u0001\u0012A\u0010\u0002\u0019\u001d+g.\u001a:jG\u0006\u0013(/Y=\u0011\u0005\u0001\nS\"\u0001\u0001\u0007\u000b\t\u0002\u0001\u0012A\u0012\u0003\u0019\u001d+g.\u001a:jG\u0006\u0013(/Y=\u0014\u0005\u0005b\u0001\"B\u0013\"\t\u00031\u0013A\u0002\u001fj]&$h\bF\u0001 \u0011\u0015A\u0013\u0005\"\u0003*\u0003-9WM\\3sS\u000e\u001cuN]3\u0015\u0005)\u0002\u0004CA\u0016-\u001d\t\u0001c#\u0003\u0002.]\t!A+\u001f9f\u0013\tyCAA\u0003UsB,7\u000fC\u00032O\u0001\u0007!&\u0001\u0002ua\")1'\tC\u0001i\u00059QO\\1qa2LHCA\u001b?!\ria\u0007O\u0005\u0003o!\u0011aa\u00149uS>t\u0007\u0003B\u0007:w)J!A\u000f\u0005\u0003\rQ+\b\u000f\\33!\tiA(\u0003\u0002>\u0011\t\u0019\u0011J\u001c;\t\u000bE\u0012\u0004\u0019\u0001\u0016\t\u000b\u0001\u0003A\u0011C!\u00025Ut'm\\;oI\u0016$w)\u001a8fe&\u001c\u0017I\u001d:bs2+g/\u001a7\u0015\u0005m\u0012\u0005\"B\u0019@\u0001\u0004Q\u0003\"\u0002#\u0001\t#)\u0015\u0001\u0005:fE&tG-\u00138oKJ\u001cE.Y:t)\rQc\t\u0013\u0005\u0006\u000f\u000e\u0003\rAK\u0001\u0004aJ,\u0007\"B%D\u0001\u0004Q\u0015aA2mgB\u00111fS\u0005\u0003\u00196\u0013aaU=nE>d\u0017B\u0001(\u0005\u0005\u001d\u0019\u00160\u001c2pYNDQ\u0001\u0015\u0001\u0005\u0002E\u000b1#\u001a:bg\u0016$g+\u00197vK\u000ec\u0017m]:Be\u001e$\"A\u000b*\t\u000bM{\u0005\u0019\u0001+\u0002\tQ\u0014XM\u001a\t\u0003WUK!A\u0016\u0018\u0003\u000fQK\b/\u001a*fM\")\u0001\f\u0001C\u00013\u00061b/\u00197vK\u000ec\u0017m]:JgB\u000b'/Y7fiJL7\r\u0006\u0002[;B\u0011QbW\u0005\u00039\"\u0011qAQ8pY\u0016\fg\u000eC\u0003_/\u0002\u0007!*A\u0003dY\u0006T(PB\u0003a\u0001\u0005\u0005\u0011M\u0001\u0006Fe\u0006\u001cXO]3NCB\u001c\"a\u00182\u0011\u0005-\u001a\u0017B\u00013f\u0005\u001d!\u0016\u0010]3NCBL!AZ4\u0003\u0011QK\b/Z'baNT!\u0001\u001b\u0003\u0002\u0007Q\u0004X\rC\u0003&?\u0012\u0005!\u000eF\u0001l!\t\u0001s\fC\u0003n?\u001a\u0005a.\u0001\u0007nKJ<W\rU1sK:$8\u000f\u0006\u0002+_\")\u0001\u000f\u001ca\u0001c\u00069\u0001/\u0019:f]R\u001c\bc\u0001:vU9\u0011Qb]\u0005\u0003i\"\tq\u0001]1dW\u0006<W-\u0003\u0002wo\n!A*[:u\u0015\t!\b\u0002C\u0003z?\u0012\u0005!0A\nfe\u0006\u001cXMT8s[\u0006d7\t\\1tgJ+g\r\u0006\u0002+w\")1\u000b\u001fa\u0001)\")Qp\u0018C\t}\u0006IRM]1tK\u0012+'/\u001b<fIZ\u000bG.^3DY\u0006\u001c8OU3g)\tQs\u0010C\u0003Ty\u0002\u0007A\u000bC\u0004\u0002\u0004}#\t!!\u0002\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007)\n9\u0001\u0003\u00042\u0003\u0003\u0001\rA\u000b\u0005\b\u0003\u0017yF\u0011AA\u0007\u00031\t\u0007\u000f\u001d7z\u0013:\f%O]1z)\rQ\u0013q\u0002\u0005\u0007c\u0005%\u0001\u0019\u0001\u0016\t\u000f\u0005M\u0001\u0001\"\u0005\u0002\u0016\u0005\tb/\u001a:jMfT\u0015M^1Fe\u0006\u001cXO]3\u0016\u0003iCq!!\u0007\u0001\t\u0003\tY\"A\u0004fe\u0006\u001cXO]3\u0015\u0007-\fi\u0002C\u0004\u0002 \u0005]\u0001\u0019\u0001&\u0002\u0007MLX\u000eC\u0004\u0002$\u0001!\t!!\n\u0002\u001dM\u0004XmY5bY\u0016\u0013\u0018m];sKR!\u0011qEA\u0016)\rQ\u0013\u0011\u0006\u0005\u0007c\u0005\u0005\u0002\u0019\u0001\u0016\t\u000f\u0005}\u0011\u0011\u0005a\u0001\u0015\"9\u0011q\u0006\u0001\u0005\u0002\u0005E\u0012!G:qK\u000eL\u0017\r\\\"p]N$(/^2u_J,%/Y:ve\u0016$RAKA\u001a\u0003kAaAXA\u0017\u0001\u0004Q\u0005B\u00025\u0002.\u0001\u0007!F\u0002\u0004\u0002:\u0001\u0001\u00111\b\u0002\u0010'\u000e\fG.Y#sCN,(/Z'baN\u0019\u0011qG6\t\u000f\u0015\n9\u0004\"\u0001\u0002@Q\u0011\u0011\u0011\t\t\u0004A\u0005]\u0002bB7\u00028\u0011\u0005\u0011Q\t\u000b\u0004U\u0005\u001d\u0003B\u00029\u0002D\u0001\u0007\u0011O\u0002\u0004\u0002L\u0001\u0001\u0011Q\n\u0002\u000f\u0015\u00064\u0018-\u0012:bgV\u0014X-T1q'\r\tIe\u001b\u0005\bK\u0005%C\u0011AA))\t\t\u0019\u0006E\u0002!\u0003\u0013Bq!\\A%\t\u0003\t9\u0006F\u0002+\u00033Ba\u0001]A+\u0001\u0004\t\bbB?\u0002J\u0011E\u0013Q\f\u000b\u0004U\u0005}\u0003BB*\u0002\\\u0001\u0007AkB\u0004\u0002d\u0001A\t!!\u001a\u0002\u0019M\u001c\u0017\r\\1Fe\u0006\u001cXO]3\u0011\u0007\u0001\n9GB\u0004\u0002j\u0001A\t!a\u001b\u0003\u0019M\u001c\u0017\r\\1Fe\u0006\u001cXO]3\u0014\t\u0005\u001d\u0014\u0011\t\u0005\bK\u0005\u001dD\u0011AA8)\t\t)gB\u0004\u0002t\u0001A\t!!\u001e\u0002'M\u0004XmY5bYN\u001b\u0017\r\\1Fe\u0006\u001cXO]3\u0011\u0007\u0001\n9HB\u0004\u0002z\u0001A\t!a\u001f\u0003'M\u0004XmY5bYN\u001b\u0017\r\\1Fe\u0006\u001cXO]3\u0014\t\u0005]\u0014\u0011\t\u0005\bK\u0005]D\u0011AA@)\t\t)\bC\u0004~\u0003o\"\t%a!\u0015\u0007)\n)\t\u0003\u0004T\u0003\u0003\u0003\r\u0001V\u0004\b\u0003\u0013\u0003\u0001\u0012AAF\u0003-Q\u0017M^1Fe\u0006\u001cXO]3\u0011\u0007\u0001\niIB\u0004\u0002\u0010\u0002A\t!!%\u0003\u0017)\fg/Y#sCN,(/Z\n\u0005\u0003\u001b\u000b\u0019\u0006C\u0004&\u0003\u001b#\t!!&\u0015\u0005\u0005-uaBAM\u0001!\u0005\u00111T\u0001\u0014m\u0016\u0014\u0018NZ5fI*\u000bg/Y#sCN,(/\u001a\t\u0004A\u0005ueaBAP\u0001!\u0005\u0011\u0011\u0015\u0002\u0014m\u0016\u0014\u0018NZ5fI*\u000bg/Y#sCN,(/Z\n\u0005\u0003;\u000b\u0019\u0006C\u0004&\u0003;#\t!!*\u0015\u0005\u0005m\u0005\u0002CA\u0002\u0003;#\t%!+\u0015\u0007)\nY\u000b\u0003\u00042\u0003O\u0003\rAK\u0004\b\u0003_\u0003\u0001\u0012AAY\u00035\u0011w\u000e_5oO\u0016\u0013\u0018m];sKB\u0019\u0001%a-\u0007\u000f\u0005U\u0006\u0001#\u0001\u00028\ni!m\u001c=j]\u001e,%/Y:ve\u0016\u001cB!a-\u0002B!9Q%a-\u0005\u0002\u0005mFCAAY\u0011\u001dI\u00181\u0017C!\u0003\u007f#2AKAa\u0011\u0019\u0019\u0016Q\u0018a\u0001)\"9Q0a-\u0005B\u0005\u0015Gc\u0001\u0016\u0002H\"11+a1A\u0002QCq!a3\u0001\t\u0003\ti-A\u000bj]R,'o]3di&|g\u000eR8nS:\fGo\u001c:\u0015\u0007)\ny\r\u0003\u0004q\u0003\u0013\u0004\r!\u001d\u0005\b\u0003'\u0004A\u0011AAk\u00035!(/\u00198tM>\u0014X.\u00138g_R)!&a6\u0002Z\"9\u0011qDAi\u0001\u0004Q\u0005BB\u0019\u0002R\u0002\u0007!\u0006")
public interface Erasure {
    public SymbolTable global();

    public Erasure$GenericArray$ GenericArray();

    public int unboundedGenericArrayLevel(Types.Type var1);

    public Types.Type rebindInnerClass(Types.Type var1, Symbols.Symbol var2);

    public Types.Type erasedValueClassArg(Types.TypeRef var1);

    public boolean valueClassIsParametric(Symbols.Symbol var1);

    public boolean verifyJavaErasure();

    public ErasureMap erasure(Symbols.Symbol var1);

    public Types.Type specialErasure(Symbols.Symbol var1, Types.Type var2);

    public Types.Type specialConstructorErasure(Symbols.Symbol var1, Types.Type var2);

    public Erasure$scalaErasure$ scalaErasure();

    public Erasure$specialScalaErasure$ specialScalaErasure();

    public Erasure$javaErasure$ javaErasure();

    public Erasure$verifiedJavaErasure$ verifiedJavaErasure();

    public Erasure$boxingErasure$ boxingErasure();

    public Types.Type intersectionDominator(List<Types.Type> var1);

    public Types.Type transformInfo(Symbols.Symbol var1, Types.Type var2);

    public abstract class ErasureMap
    extends TypeMaps.TypeMap {
        public abstract Types.Type mergeParents(List<Types.Type> var1);

        public Types.Type eraseNormalClassRef(Types.TypeRef tref) {
            if (tref != null) {
                Tuple3<Types.Type, Symbols.Symbol, List<Types.Type>> tuple3 = new Tuple3<Types.Type, Symbols.Symbol, List<Types.Type>>(tref.pre(), tref.sym(), tref.args());
                Types.Type pre = tuple3._1();
                Symbols.Symbol clazz = tuple3._2();
                List<Types.Type> args = tuple3._3();
                Types.Type pre1 = this.apply(this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().rebindInnerClass(pre, clazz));
                Nil$ args1 = Nil$.MODULE$;
                return pre == pre1 && args == args1 ? tref : this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().typeRef(pre1, clazz, args1);
            }
            throw new MatchError(tref);
        }

        public Types.Type eraseDerivedValueClassRef(Types.TypeRef tref) {
            return this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().erasedValueClassArg(tref);
        }

        /*
         * Unable to fully structure code
         * Could not resolve type clashes
         */
        @Override
        public Types.Type apply(Types.Type tp) {
            block19: {
                block22: {
                    block24: {
                        block23: {
                            block21: {
                                block20: {
                                    block18: {
                                        if (!(tp instanceof Types.ConstantType)) break block18;
                                        var20_2 = tp;
                                        break block19;
                                    }
                                    if (!(tp instanceof Types.ThisType) || !(var2_3 = (Types.ThisType)tp).sym().isPackageClass()) break block20;
                                    var20_2 = tp;
                                    break block19;
                                }
                                if (!(tp instanceof Types.SubType)) break block21;
                                var3_4 = (Types.SubType)tp;
                                var20_2 = this.apply(var3_4.supertype());
                                break block19;
                            }
                            if (!(tp instanceof Types.TypeRef)) break block22;
                            var9_5 = (Types.TypeRef)tp;
                            v0 = var9_5.sym();
                            var4_6 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().ArrayClass();
                            if (v0 != null ? v0.equals(var4_6) == false : var4_6 != null) break block23;
                            v1 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().unboundedGenericArrayLevel(tp) == 1 ? this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().ObjectTpe() : (var9_5.args().head().typeSymbol().isBottomClass() ? this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().arrayType(this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().ObjectTpe()) : this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().typeRef(this.apply(var9_5.pre()), var9_5.sym(), var9_5.args().map(new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                private final /* synthetic */ ErasureMap $outer;

                                public final Types.Type apply(Types.Type tp) {
                                    return this.$outer.applyInArray(tp);
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            }, List$.MODULE$.canBuildFrom())));
                            break block24;
                        }
                        v2 = var9_5.sym();
                        var5_7 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().AnyClass();
                        if (!(v2 == null ? var5_7 != null : v2.equals(var5_7) == false)) ** GOTO lbl-1000
                        v3 = var9_5.sym();
                        var6_8 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().AnyValClass();
                        if (!(v3 == null ? var6_8 != null : v3.equals(var6_8) == false)) ** GOTO lbl-1000
                        v4 = var9_5.sym();
                        var7_9 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().SingletonClass();
                        if (!(v4 != null ? v4.equals(var7_9) == false : var7_9 != null)) lbl-1000:
                        // 3 sources

                        {
                            v1 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().ObjectTpe();
                        } else {
                            v5 = var9_5.sym();
                            var8_10 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().UnitClass();
                            v1 = !(v5 != null ? v5.equals(var8_10) == false : var8_10 != null) ? this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().BoxedUnitTpe() : (var9_5.sym().isRefinementClass() != false ? this.apply(this.mergeParents(tp.parents())) : (var9_5.sym().isDerivedValueClass() != false ? this.eraseDerivedValueClassRef(var9_5) : (var9_5.sym().isClass() != false ? this.eraseNormalClassRef(var9_5) : this.apply(var9_5.sym().info().asSeenFrom(var9_5.pre(), var9_5.sym().owner())))));
                        }
                    }
                    var20_2 = v1;
                    break block19;
                }
                if (tp instanceof Types.PolyType) {
                    var10_11 = (Types.PolyType)tp;
                    var20_2 = this.apply(var10_11.resultType());
                } else if (tp instanceof Types.ExistentialType) {
                    var11_12 = (Types.ExistentialType)tp;
                    var20_2 = this.apply(var11_12.underlying());
                } else if (tp instanceof Types.MethodType) {
                    var13_13 = (Types.MethodType)tp;
                    v6 = var13_13.resultType().typeSymbol();
                    var12_14 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().UnitClass();
                    var20_2 = new Types.MethodType(this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global(), this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().cloneSymbolsAndModify(var13_13.params(), this), !(v6 != null ? v6.equals(var12_14) == false : var12_14 != null) ? this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().UnitTpe() : this.apply(var13_13.resultType(var13_13.paramTypes())));
                } else if (tp instanceof Types.RefinedType) {
                    var14_15 = (Types.RefinedType)tp;
                    var20_2 = this.apply(this.mergeParents(var14_15.parents()));
                } else if (tp instanceof Types.AnnotatedType) {
                    var15_16 = (Types.AnnotatedType)tp;
                    var20_2 = this.apply(var15_16.underlying());
                } else if (tp instanceof Types.ClassInfoType) {
                    var19_17 = (Types.ClassInfoType)tp;
                    v7 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global();
                    v8 = var19_17.typeSymbol();
                    var16_18 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().ObjectClass();
                    if (!(v8 == null ? var16_18 != null : v8.equals(var16_18) == false) || this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().isPrimitiveValueClass(var19_17.typeSymbol())) {
                        v9 /* !! */  = Nil$.MODULE$;
                    } else {
                        v10 = var19_17.typeSymbol();
                        var17_19 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().ArrayClass();
                        if (!(v10 != null ? v10.equals(var17_19) == false : var17_19 != null)) {
                            var18_20 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().ObjectTpe();
                            v9 /* !! */  = Nil$.MODULE$.$colon$colon(var18_20);
                        } else {
                            v9 /* !! */  = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().removeLaterObjects(var19_17.parents().map(this, List$.MODULE$.canBuildFrom()));
                        }
                    }
                    var20_2 = new Types.ClassInfoType(v7, v9 /* !! */ , var19_17.decls(), var19_17.typeSymbol());
                } else {
                    var20_2 = this.mapOver(tp);
                }
            }
            return var20_2;
        }

        public Types.Type applyInArray(Types.Type tp) {
            Types.TypeRef typeRef;
            Types.Type type = tp instanceof Types.TypeRef && (typeRef = (Types.TypeRef)tp).sym().isDerivedValueClass() ? this.eraseNormalClassRef(typeRef) : this.apply(tp);
            return type;
        }

        public /* synthetic */ Erasure scala$reflect$internal$transform$Erasure$ErasureMap$$$outer() {
            return Erasure.this;
        }

        public ErasureMap() {
            if (Erasure.this == null) {
                throw null;
            }
            super(Erasure.this.global());
        }
    }

    public class JavaErasureMap
    extends ErasureMap {
        @Override
        public Types.Type mergeParents(List<Types.Type> parents2) {
            return parents2.isEmpty() ? this.scala$reflect$internal$transform$Erasure$JavaErasureMap$$$outer().global().definitions().ObjectTpe() : parents2.head();
        }

        @Override
        public Types.Type eraseDerivedValueClassRef(Types.TypeRef tref) {
            return this.eraseNormalClassRef(tref);
        }

        public /* synthetic */ Erasure scala$reflect$internal$transform$Erasure$JavaErasureMap$$$outer() {
            return this.$outer;
        }

        public JavaErasureMap(Erasure $outer) {
        }
    }

    public class ScalaErasureMap
    extends ErasureMap {
        @Override
        public Types.Type mergeParents(List<Types.Type> parents2) {
            return this.scala$reflect$internal$transform$Erasure$ScalaErasureMap$$$outer().intersectionDominator(parents2);
        }

        public /* synthetic */ Erasure scala$reflect$internal$transform$Erasure$ScalaErasureMap$$$outer() {
            return this.$outer;
        }

        public ScalaErasureMap(Erasure $outer) {
        }
    }
}

