/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.HashSet$;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Trees;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.Variances$VarianceValidator$ValidateVarianceMap$;
import scala.runtime.BoxedUnit;

@ScalaSignature(bytes="\u0006\u0001\u0005=f!C\u0001\u0003!\u0003\r\t!CAU\u0005%1\u0016M]5b]\u000e,7O\u0003\u0002\u0004\t\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\u0003\t\u0003\u00171i\u0011AB\u0005\u0003\u001b\u0019\u0011a!\u00118z%\u00164\u0007\"B\b\u0001\t\u0003\u0001\u0012A\u0002\u0013j]&$H\u0005F\u0001\u0012!\tY!#\u0003\u0002\u0014\r\t!QK\\5u\r\u0011)\u0002\u0001\u0001\f\u0003#Y\u000b'/[1oG\u00164\u0016\r\\5eCR|'o\u0005\u0002\u0015/A\u0011\u0001$G\u0007\u0002\u0001%\u0011!d\u0007\u0002\n)J\fg/\u001a:tKJL!\u0001H\u000f\u0003\u000bQ\u0013X-Z:\u000b\u0005y!\u0011aA1qS\")\u0001\u0005\u0006C\u0001C\u00051A(\u001b8jiz\"\u0012A\t\t\u00031QAq\u0001\n\u000bC\u0002\u0013%Q%A\u0007fg\u000e\f\u0007/\u001a3M_\u000e\fGn]\u000b\u0002MA\u0019q\u0005\f\u0018\u000e\u0003!R!!\u000b\u0016\u0002\u000f5,H/\u00192mK*\u00111FB\u0001\u000bG>dG.Z2uS>t\u0017BA\u0017)\u0005\u001dA\u0015m\u001d5TKR\u0004\"\u0001G\u0018\n\u0005A\n$AB*z[\n|G.\u0003\u00023\u0005\t91+_7c_2\u001c\bB\u0002\u001b\u0015A\u0003%a%\u0001\bfg\u000e\f\u0007/\u001a3M_\u000e\fGn\u001d\u0011\t\u0019Y\"B\u0011!A\u0003\u0002\u0003\u0007I\u0011B\u001c\u0002\u0001N\u001c\u0017\r\\1%e\u00164G.Z2uI%tG/\u001a:oC2$c+\u0019:jC:\u001cWm\u001d\u0013WCJL\u0017M\\2f-\u0006d\u0017\u000eZ1u_J$C%\u001b8SK\u001aLg.Z7f]R,\u0012\u0001\u000f\t\u0003\u0017eJ!A\u000f\u0004\u0003\u000f\t{w\u000e\\3b]\"aA\b\u0006C\u0001\u0002\u000b\u0005\t\u0019!C\u0005{\u0005!5oY1mC\u0012\u0012XM\u001a7fGR$\u0013N\u001c;fe:\fG\u000e\n,be&\fgnY3tIY\u000b'/[1oG\u00164\u0016\r\\5eCR|'\u000f\n\u0013j]J+g-\u001b8f[\u0016tGo\u0018\u0013fcR\u0011\u0011C\u0010\u0005\b\u007fm\n\t\u00111\u00019\u0003\rAH%\r\u0005\n\u0003R\u0011\t\u0011!Q!\na\n\u0011i]2bY\u0006$#/\u001a4mK\u000e$H%\u001b8uKJt\u0017\r\u001c\u0013WCJL\u0017M\\2fg\u00122\u0016M]5b]\u000e,g+\u00197jI\u0006$xN\u001d\u0013%S:\u0014VMZ5oK6,g\u000e\u001e\u0011\t\u000b\r#B\u0011\u0002#\u0002!]LG\u000f[5o%\u00164\u0017N\\3nK:$HCA#K!\tAb)\u0003\u0002H\u0011\n!A+\u001f9f\u0013\tI%AA\u0003UsB,7\u000f\u0003\u0004L\u0005\u0012\u0005\r\u0001T\u0001\u0005E>$\u0017\u0010E\u0002\f\u001b\u0016K!A\u0014\u0004\u0003\u0011q\u0012\u0017P\\1nKzB#A\u0011)\u0011\u0005-\t\u0016B\u0001*\u0007\u0005\u0019Ig\u000e\\5oK\")A\u000b\u0006C\u0003+\u0006q1\r[3dW\u001a{'/R:dCB,GcA\tW1\")qk\u0015a\u0001]\u0005\u00191/_7\t\u000be\u001b\u0006\u0019\u0001\u0018\u0002\tMLG/\u001a\u0015\u0003'n\u0003\"\u0001X0\u000e\u0003uS!A\u0018\u0004\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002a;\n9A/Y5me\u0016\u001c\u0007\"\u00022\u0015\t#\u0019\u0017AE5tgV,g+\u0019:jC:\u001cW-\u0012:s_J$B!\u00053gO\")Q-\u0019a\u0001]\u0005!!-Y:f\u0011\u00159\u0016\r1\u0001/\u0011\u0015A\u0017\r1\u0001j\u0003!\u0011X-];je\u0016$\u0007C\u00016l\u001b\u0005\u0011\u0011B\u00017\u0003\u0005!1\u0016M]5b]\u000e,\u0007\"\u00028\u0015\t\u0003y\u0017AC:i_VdGM\u00127jaR\u0019\u0001\b]9\t\u000b]k\u0007\u0019\u0001\u0018\t\u000bIl\u0007\u0019\u0001\u0018\u0002\tQ4\u0018M\u001d\u0005\u0006iR!\t!^\u0001\fSNdunY1m\u001f:d\u0017\u0010\u0006\u00029m\")qk\u001da\u0001]\u001d)\u0001\u0010\u0006E\u0005s\u0006\u0019b+\u00197jI\u0006$XMV1sS\u0006t7-Z'baB\u0011!p_\u0007\u0002)\u0019)A\u0010\u0006E\u0005{\n\u0019b+\u00197jI\u0006$XMV1sS\u0006t7-Z'baN\u00111P \t\u00031}LA!!\u0001\u0002\u0004\t9A+\u001f9f\u001b\u0006\u0004\u0018\u0002BA\u0003\u0003\u000f\u0011\u0001\u0002V=qK6\u000b\u0007o\u001d\u0006\u0004\u0003\u0013\u0011\u0011a\u0001;qK\"1\u0001e\u001fC\u0001\u0003\u001b!\u0012!\u001f\u0005\u000bKn\u0004\r\u00111A\u0005\n\u0005EQ#\u0001\u0018\t\u0017\u0005U1\u00101AA\u0002\u0013%\u0011qC\u0001\tE\u0006\u001cXm\u0018\u0013fcR\u0019\u0011#!\u0007\t\u0011}\n\u0019\"!AA\u00029Bq!!\b|A\u0003&a&A\u0003cCN,\u0007\u0005C\u0004\u0002\"m$\t!a\t\u0002!I,G.\u0019;jm\u00164\u0016M]5b]\u000e,GcA5\u0002&!1!/a\bA\u00029Bq!!\u000b|\t\u0003\tY#A\njgVs7\r[3dW\u0016$g+\u0019:jC:\u001cW\rF\u00029\u0003[Aq!a\f\u0002(\u0001\u0007Q)\u0001\u0002ua\"9\u00111G>\u0005\n\u0005U\u0012!F2iK\u000e\\g+\u0019:jC:\u001cWm\u00144Ts6\u0014w\u000e\u001c\u000b\u0004#\u0005]\u0002BB,\u00022\u0001\u0007a\u0006C\u0004\u0002<m$\t%!\u0010\u0002\u000f5\f\u0007o\u0014<feR!\u0011qHA%!\rA\u0012\u0011I\u0005\u0005\u0003\u0007\n)EA\u0003TG>\u0004X-C\u0002\u0002H\t\u0011aaU2pa\u0016\u001c\b\u0002CA&\u0003s\u0001\r!a\u0010\u0002\u000b\u0011,7\r\\:\t\u000f\u0005=3\u0010\"\u0003\u0002R\u0005q!/Z:vYR$\u0016\u0010]3P]2LHc\u0001\u001d\u0002T!9\u0011qFA'\u0001\u0004)\u0005bBA,w\u0012\u0005\u0011\u0011L\u0001\u0006CB\u0004H.\u001f\u000b\u0004\u000b\u0006m\u0003bBA\u0018\u0003+\u0002\r!\u0012\u0005\b\u0003?ZH\u0011AA1\u0003I1\u0018\r\\5eCR,G)\u001a4j]&$\u0018n\u001c8\u0015\u0007E\t\u0019\u0007\u0003\u0004f\u0003;\u0002\rA\f\u0005\b\u0003O\"B\u0011BA5\u0003A1\u0018\r\\5eCR,g+\u0019:jC:\u001cW\rF\u0002\u0012\u0003WBa!ZA3\u0001\u0004q\u0003bBA8)\u0011\u0005\u0013\u0011O\u0001\tiJ\fg/\u001a:tKR\u0019\u0011#a\u001d\t\u0011\u0005U\u0014Q\u000ea\u0001\u0003o\nA\u0001\u001e:fKB\u0019\u0001$!\u001f\n\t\u0005m\u0014Q\u0010\u0002\u0005)J,W-\u0003\u0002\u001d\u0005!9\u0011\u0011\u0011\u0001\u0005\u0002\u0005\r\u0015a\u0004<be&\fgnY3J]RK\b/Z:\u0015\t\u0005\u0015\u00151\u0012\u000b\u0004S\u0006\u001d\u0005bBAE\u0003\u007f\u0002\rAL\u0001\u0007iB\f'/Y7\t\u0011\u00055\u0015q\u0010a\u0001\u0003\u001f\u000b1\u0001\u001e9t!\u0015\t\t*a&F\u001d\rY\u00111S\u0005\u0004\u0003+3\u0011a\u00029bG.\fw-Z\u0005\u0005\u00033\u000bYJ\u0001\u0003MSN$(bAAK\r!9\u0011q\u0014\u0001\u0005\u0002\u0005\u0005\u0016A\u0004<be&\fgnY3J]RK\b/\u001a\u000b\u0005\u0003G\u000b9\u000bF\u0002j\u0003KCq!!#\u0002\u001e\u0002\u0007a\u0006C\u0004\u00020\u0005u\u0005\u0019A#\u0011\u0007)\fY+C\u0002\u0002.\n\u00111bU=nE>dG+\u00192mK\u0002")
public interface Variances {
    public int varianceInTypes(List<Types.Type> var1, Symbols.Symbol var2);

    public int varianceInType(Types.Type var1, Symbols.Symbol var2);

    public static class VarianceValidator
    extends Trees.Traverser {
        private final HashSet<Symbols.Symbol> escapedLocals;
        private boolean scala$reflect$internal$Variances$VarianceValidator$$inRefinement;
        private volatile Variances$VarianceValidator$ValidateVarianceMap$ ValidateVarianceMap$module;
        public final /* synthetic */ SymbolTable $outer;

        private Variances$VarianceValidator$ValidateVarianceMap$ ValidateVarianceMap$lzycompute() {
            VarianceValidator varianceValidator = this;
            synchronized (varianceValidator) {
                if (this.ValidateVarianceMap$module == null) {
                    this.ValidateVarianceMap$module = new Variances$VarianceValidator$ValidateVarianceMap$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ValidateVarianceMap$module;
            }
        }

        private HashSet<Symbols.Symbol> escapedLocals() {
            return this.escapedLocals;
        }

        public boolean scala$reflect$internal$Variances$VarianceValidator$$inRefinement() {
            return this.scala$reflect$internal$Variances$VarianceValidator$$inRefinement;
        }

        public void scala$reflect$internal$Variances$VarianceValidator$$inRefinement_$eq(boolean x$1) {
            this.scala$reflect$internal$Variances$VarianceValidator$$inRefinement = x$1;
        }

        /*
         * WARNING - void declaration
         */
        public Types.Type scala$reflect$internal$Variances$VarianceValidator$$withinRefinement(Function0<Types.Type> body2) {
            Types.Type type;
            boolean saved = this.scala$reflect$internal$Variances$VarianceValidator$$inRefinement();
            this.scala$reflect$internal$Variances$VarianceValidator$$inRefinement_$eq(true);
            try {
                type = body2.apply();
                this.scala$reflect$internal$Variances$VarianceValidator$$inRefinement_$eq(saved);
            }
            catch (Throwable throwable) {
                void var2_2;
                this.scala$reflect$internal$Variances$VarianceValidator$$inRefinement_$eq((boolean)var2_2);
                throw throwable;
            }
            return type;
        }

        public final void checkForEscape(Symbols.Symbol sym, Symbols.Symbol site) {
            block2: {
                BoxedUnit boxedUnit;
                while (true) {
                    block4: {
                        block3: {
                            Symbols.Symbol symbol = site;
                            Symbols.Symbol symbol2 = sym.owner();
                            if (!(symbol == null ? symbol2 != null : !symbol.equals(symbol2))) break block3;
                            Symbols.Symbol symbol3 = site;
                            Symbols.Symbol symbol4 = sym.owner().moduleClass();
                            if ((symbol3 == null ? symbol4 != null : !symbol3.equals(symbol4)) && !site.hasPackageFlag()) break block4;
                        }
                        boxedUnit = BoxedUnit.UNIT;
                        break block2;
                    }
                    if (!site.isTerm() && !site.isPrivateLocal()) break;
                    site = site.owner();
                }
                this.escapedLocals().$plus$eq((Object)sym);
                boxedUnit = BoxedUnit.UNIT;
            }
        }

        public void issueVarianceError(Symbols.Symbol base, Symbols.Symbol sym, int required) {
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean shouldFlip(Symbols.Symbol sym, Symbols.Symbol tvar) {
            if (!sym.isParameter()) return false;
            if (!tvar.isTypeParameterOrSkolem()) return true;
            if (!sym.isTypeParameterOrSkolem()) return true;
            Symbols.Symbol symbol = tvar.owner();
            Symbols.Symbol symbol2 = sym.owner();
            if (symbol == null) {
                if (symbol2 == null) return false;
                return true;
            } else if (symbol.equals(symbol2)) return false;
            return true;
        }

        public boolean isLocalOnly(Symbols.Symbol sym) {
            return !sym.owner().isClass() || sym.isTerm() && (sym.isLocalToThis() || sym.isSuperAccessor()) && !this.escapedLocals().apply(sym);
        }

        private Variances$VarianceValidator$ValidateVarianceMap$ ValidateVarianceMap() {
            return this.ValidateVarianceMap$module == null ? this.ValidateVarianceMap$lzycompute() : this.ValidateVarianceMap$module;
        }

        private void validateVariance(Symbols.Symbol base) {
            this.ValidateVarianceMap().validateDefinition(base);
        }

        public void traverse(Trees.Tree tree) {
            block1: {
                block7: {
                    Trees.TypeTree typeTree;
                    block6: {
                        block5: {
                            block4: {
                                block3: {
                                    block2: {
                                        block0: {
                                            if (!(tree instanceof Trees.MemberDef) || !this.skip$1(tree)) break block0;
                                            this.scala$reflect$internal$Variances$VarianceValidator$$$outer().debuglog((Function0<String>)((Object)new Serializable(this, tree){
                                                public static final long serialVersionUID = 0L;
                                                private final /* synthetic */ VarianceValidator $outer;
                                                private final Trees.Tree tree$1;

                                                public final String apply() {
                                                    return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Skipping variance check of ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.$outer.scala$reflect$internal$Variances$VarianceValidator$$sym$2(this.tree$1).defString()}));
                                                }
                                                {
                                                    if ($outer == null) {
                                                        throw null;
                                                    }
                                                    this.$outer = $outer;
                                                    this.tree$1 = tree$1;
                                                }
                                            }));
                                            break block1;
                                        }
                                        boolean bl = tree instanceof Trees.ClassDef ? true : tree instanceof Trees.TypeDef;
                                        if (!bl) break block2;
                                        this.validateVariance(this.scala$reflect$internal$Variances$VarianceValidator$$sym$2(tree));
                                        super.traverse(tree);
                                        break block1;
                                    }
                                    if (!(tree instanceof Trees.ValDef)) break block3;
                                    this.validateVariance(this.scala$reflect$internal$Variances$VarianceValidator$$sym$2(tree));
                                    break block1;
                                }
                                if (!(tree instanceof Trees.DefDef)) break block4;
                                Trees.DefDef defDef = (Trees.DefDef)tree;
                                this.validateVariance(this.scala$reflect$internal$Variances$VarianceValidator$$sym$2(tree));
                                this.traverseTrees(defDef.tparams());
                                this.traverseTreess(defDef.vparamss());
                                break block1;
                            }
                            if (!(tree instanceof Trees.Template)) break block5;
                            super.traverse(tree);
                            break block1;
                        }
                        if (!(tree instanceof Trees.CompoundTypeTree)) break block6;
                        super.traverse(tree);
                        break block1;
                    }
                    if (!(tree instanceof Trees.TypeTree) || (typeTree = (Trees.TypeTree)tree).original() == null) break block7;
                    super.traverse(typeTree.original());
                    break block1;
                }
                if (!(tree instanceof Trees.TypTree)) break block1;
                Trees.TypTree typTree = (Trees.TypTree)((Object)tree);
                super.traverse(typTree);
            }
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Variances$VarianceValidator$$$outer() {
            return this.$outer;
        }

        public final Symbols.Symbol scala$reflect$internal$Variances$VarianceValidator$$sym$2(Trees.Tree tree$1) {
            return tree$1.symbol();
        }

        private final boolean skip$1(Trees.Tree tree$1) {
            Symbols.Symbol symbol = this.scala$reflect$internal$Variances$VarianceValidator$$sym$2(tree$1);
            Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Variances$VarianceValidator$$$outer().NoSymbol();
            return !(symbol == null ? noSymbol != null : !symbol.equals(noSymbol)) || this.scala$reflect$internal$Variances$VarianceValidator$$sym$2(tree$1).isLocalToThis() || this.scala$reflect$internal$Variances$VarianceValidator$$sym$2(tree$1).owner().isConstructor() || this.scala$reflect$internal$Variances$VarianceValidator$$sym$2(tree$1).owner().isCaseApplyOrUnapply();
        }

        public VarianceValidator(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            super($outer);
            this.escapedLocals = (HashSet)HashSet$.MODULE$.apply(Nil$.MODULE$);
            this.scala$reflect$internal$Variances$VarianceValidator$$inRefinement = false;
        }
    }
}

