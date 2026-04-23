/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.Function1;
import scala.Function1$class;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Symbols;
import scala.reflect.api.Types;

@ScalaSignature(bytes="\u0006\u0001\u0005\u001df!C\u0001\u0003!\u0003\r\t!CAP\u0005M\u0019F/\u00198eCJ$G)\u001a4j]&$\u0018n\u001c8t\u0015\t\u0019A!A\u0002ba&T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\r\u001b\u00051\u0011BA\u0007\u0007\u0005\u0019\te.\u001f*fM\")q\u0002\u0001C\u0001!\u00051A%\u001b8ji\u0012\"\u0012!\u0005\t\u0003\u0017II!a\u0005\u0004\u0003\tUs\u0017\u000e\u001e\u0005\b+\u0001\u0011\rQ\"\u0001\u0017\u0003-!WMZ5oSRLwN\\:\u0016\u0003]\u0001\"\u0001G\r\u000e\u0003\u00011qA\u0007\u0001\u0011\u0002\u0007\u00051D\u0001\bEK\u001aLg.\u001b;j_:\u001c\u0018\t]5\u0014\u0007eQA\u0004\u0005\u0002\u0019;\u00199a\u0004\u0001I\u0001$\u0003y\"!D*uC:$\u0017M\u001d3UsB,7o\u0005\u0002\u001e\u0015!9\u0011%\bb\u0001\u000e\u0003\u0011\u0013aB+oSR$\u0006/Z\u000b\u0002GA\u0011\u0001\u0004J\u0005\u0003K\u0019\u0012A\u0001V=qK&\u0011qE\u0001\u0002\u0006)f\u0004Xm\u001d\u0005\bSu\u0011\rQ\"\u0001#\u0003\u001d\u0011\u0015\u0010^3Ua\u0016DqaK\u000fC\u0002\u001b\u0005!%\u0001\u0005TQ>\u0014H\u000f\u00169f\u0011\u001diSD1A\u0007\u0002\t\nqa\u00115beR\u0003X\rC\u00040;\t\u0007i\u0011\u0001\u0012\u0002\r%sG\u000f\u00169f\u0011\u001d\tTD1A\u0007\u0002\t\nq\u0001T8oOR\u0003X\rC\u00044;\t\u0007i\u0011\u0001\u0012\u0002\u0011\u0019cw.\u0019;Ua\u0016Dq!N\u000fC\u0002\u001b\u0005!%A\u0005E_V\u0014G.\u001a+qK\"9q'\bb\u0001\u000e\u0003\u0011\u0013A\u0003\"p_2,\u0017M\u001c+qK\"9\u0011(\bb\u0001\u000e\u0003\u0011\u0013AB!osR\u0003X\rC\u0004<;\t\u0007i\u0011\u0001\u0012\u0002\u0013\u0005s\u0017PV1m)B,\u0007bB\u001f\u001e\u0005\u00045\tAI\u0001\n\u0003:L(+\u001a4Ua\u0016DqaP\u000fC\u0002\u001b\u0005!%A\u0005PE*,7\r\u001e+qK\"9\u0011)\bb\u0001\u000e\u0003\u0011\u0013A\u0003(pi\"Lgn\u001a+qK\"91)\bb\u0001\u000e\u0003\u0011\u0013a\u0002(vY2$\u0006/\u001a\u0005\u0006\u001fe!\t\u0001\u0005\u0005\u0006\rf1\taR\u0001\u0012'\u000e\fG.\u0019)bG.\fw-Z\"mCN\u001cX#\u0001%\u0011\u0005aI\u0015B\u0001&L\u0005-\u0019E.Y:t'fl'm\u001c7\n\u00051\u0013!aB*z[\n|Gn\u001d\u0005\u0006\u001df1\taT\u0001\r'\u000e\fG.\u0019)bG.\fw-Z\u000b\u0002!B\u0011\u0001$U\u0005\u0003%.\u0013A\"T8ek2,7+_7c_2DQ\u0001V\r\u0007\u0002\u001d\u000b\u0001\"\u00118z\u00072\f7o\u001d\u0005\u0006-f1\taR\u0001\f\u0003:Lh+\u00197DY\u0006\u001c8\u000fC\u0003Y3\u0019\u0005q)A\u0006PE*,7\r^\"mCN\u001c\b\"\u0002.\u001a\r\u0003Y\u0016aC!osJ+gm\u00117bgN,\u0012\u0001\u0018\t\u00031uK!AX&\u0003\u0015QK\b/Z*z[\n|G\u000eC\u0003a3\u0019\u0005q)A\u0005Ok2d7\t\\1tg\")!-\u0007D\u0001\u000f\u0006aaj\u001c;iS:<7\t\\1tg\")A-\u0007D\u0001\u000f\u0006IQK\\5u\u00072\f7o\u001d\u0005\u0006Mf1\taR\u0001\n\u0005f$Xm\u00117bgNDQ\u0001[\r\u0007\u0002\u001d\u000b!b\u00155peR\u001cE.Y:t\u0011\u0015Q\u0017D\"\u0001H\u0003%\u0019\u0005.\u0019:DY\u0006\u001c8\u000fC\u0003m3\u0019\u0005q)\u0001\u0005J]R\u001cE.Y:t\u0011\u0015q\u0017D\"\u0001H\u0003%auN\\4DY\u0006\u001c8\u000fC\u0003q3\u0019\u0005q)\u0001\u0006GY>\fGo\u00117bgNDQA]\r\u0007\u0002\u001d\u000b1\u0002R8vE2,7\t\\1tg\")A/\u0007D\u0001\u000f\u0006a!i\\8mK\u0006t7\t\\1tg\")a/\u0007D\u0001\u000f\u0006Y1\u000b\u001e:j]\u001e\u001cE.Y:t\u0011\u0015A\u0018D\"\u0001H\u0003)\u0019E.Y:t\u00072\f7o\u001d\u0005\u0006uf1\taR\u0001\u000b\u0003J\u0014\u0018-_\"mCN\u001c\b\"\u0002?\u001a\r\u00039\u0015!\u0003'jgR\u001cE.Y:t\u0011\u0015q\u0018D\"\u0001P\u00031\u0001&/\u001a3fM6{G-\u001e7f\u0011\u0019\t\t!\u0007D\u0001\u000f\u0006!\"*\u0019<b\u0019\u0006tw\rU1dW\u0006<Wm\u00117bgNDa!!\u0002\u001a\r\u0003y\u0015a\u0004&bm\u0006d\u0015M\\4QC\u000e\\\u0017mZ3\t\r\u0005%\u0011D\"\u0001P\u0003-\t%O]1z\u001b>$W\u000f\\3\t\u000f\u00055\u0011D\"\u0001\u0002\u0010\u0005Y\u0012I\u001d:bs6{G-\u001e7f?>4XM\u001d7pC\u0012,G-\u00119qYf,\"!!\u0005\u0011\u0007a\t\u0019\"C\u0002\u0002\u0016-\u0013!\u0002V3s[NKXNY8m\u0011\u001d\tI\"\u0007D\u0001\u0003\u001f\t1\"\u0011:sCf|\u0016\r\u001d9ms\"9\u0011QD\r\u0007\u0002\u0005=\u0011aC!se\u0006Lxl\u00197p]\u0016Dq!!\t\u001a\r\u0003\ty!\u0001\u0007BeJ\f\u0017p\u00187f]\u001e$\b\u000eC\u0004\u0002&e1\t!a\u0004\u0002\u0019\u0005\u0013(/Y=`kB$\u0017\r^3\t\r\u0005%\u0012D\"\u0001H\u0003A\u0011\u0015PT1nKB\u000b'/Y7DY\u0006\u001c8\u000f\u0003\u0004\u0002.e1\taR\u0001\u0017\u0015\u00064\u0018MU3qK\u0006$X\r\u001a)be\u0006l7\t\\1tg\"1\u0011\u0011G\r\u0007\u0002\u001d\u000b!CU3qK\u0006$X\r\u001a)be\u0006l7\t\\1tg\"1\u0011QG\r\u0007\u0002=\u000b!\u0002T5ti6{G-\u001e7f\u0011\u001d\tI$\u0007D\u0001\u0003\u001f\t!\u0002T5ti~\u000b\u0007\u000f\u001d7z\u0011\u0019\ti$\u0007D\u0001\u001f\u0006Ia*\u001b7N_\u0012,H.\u001a\u0005\u0007\u0003\u0003Jb\u0011A$\u0002\u0017=\u0003H/[8o\u00072\f7o\u001d\u0005\u0007\u0003\u000bJb\u0011A(\u0002\u00159{g.Z'pIVdW\r\u0003\u0004\u0002Je1\taT\u0001\u000b'>lW-T8ek2,gaBA'3\u0005\u0005\u0011q\n\u0002\u0011-\u0006\u0014\u0018I]5us\u000ec\u0017m]:Ba&\u001cR!a\u0013\u000b\u0003#\u0002raCA*\u0003/\ni&C\u0002\u0002V\u0019\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0007-\tI&C\u0002\u0002\\\u0019\u00111!\u00138u!\rA\u0012qL\u0005\u0004\u0003CZ%AB*z[\n|G\u000e\u0003\u0005\u0002f\u0005-C\u0011AA4\u0003\u0019a\u0014N\\5u}Q\u0011\u0011\u0011\u000e\t\u0005\u0003W\nY%D\u0001\u001a\u0011!\ty'a\u0013\u0007\u0002\u0005E\u0014aA:fcV\u0011\u00111\u000f\t\u0006\u0003k\nY\b\u0013\b\u0004\u0017\u0005]\u0014bAA=\r\u00059\u0001/Y2lC\u001e,\u0017\u0002BA?\u0003\u007f\u00121aU3r\u0015\r\tIH\u0002\u0005\b\u0003\u0007Kb\u0011AAC\u00031\u0001&o\u001c3vGR\u001cE.Y:t+\t\tI\u0007C\u0004\u0002\nf1\t!!\"\u0002\u001b\u0019+hn\u0019;j_:\u001cE.Y:t\u0011\u001d\ti)\u0007D\u0001\u0003\u000b\u000b!\u0002V;qY\u0016\u001cE.Y:t\u0011\u001d\t\t*\u0007D\u0001\u0003'\u000b!dU2bY\u0006\u0004&/[7ji&4XMV1mk\u0016\u001cE.Y:tKN,\"!!&\u0011\u000b\u0005U\u0014q\u0013%\n\t\u0005e\u0015q\u0010\u0002\u0005\u0019&\u001cH\u000fC\u0004\u0002\u001ef1\t!a%\u00021M\u001b\u0017\r\\1Ok6,'/[2WC2,Xm\u00117bgN,7\u000f\u0005\u0003\u0002\"\u0006\rV\"\u0001\u0002\n\u0007\u0005\u0015&A\u0001\u0005V]&4XM]:f\u0001")
public interface StandardDefinitions {
    public DefinitionsApi definitions();

    public interface StandardTypes {
        public Types.TypeApi UnitTpe();

        public Types.TypeApi ByteTpe();

        public Types.TypeApi ShortTpe();

        public Types.TypeApi CharTpe();

        public Types.TypeApi IntTpe();

        public Types.TypeApi LongTpe();

        public Types.TypeApi FloatTpe();

        public Types.TypeApi DoubleTpe();

        public Types.TypeApi BooleanTpe();

        public Types.TypeApi AnyTpe();

        public Types.TypeApi AnyValTpe();

        public Types.TypeApi AnyRefTpe();

        public Types.TypeApi ObjectTpe();

        public Types.TypeApi NothingTpe();

        public Types.TypeApi NullTpe();
    }

    public interface DefinitionsApi
    extends StandardTypes {
        public Symbols.ClassSymbolApi ScalaPackageClass();

        public Symbols.ModuleSymbolApi ScalaPackage();

        public Symbols.ClassSymbolApi AnyClass();

        public Symbols.ClassSymbolApi AnyValClass();

        public Symbols.ClassSymbolApi ObjectClass();

        public Symbols.TypeSymbolApi AnyRefClass();

        public Symbols.ClassSymbolApi NullClass();

        public Symbols.ClassSymbolApi NothingClass();

        public Symbols.ClassSymbolApi UnitClass();

        public Symbols.ClassSymbolApi ByteClass();

        public Symbols.ClassSymbolApi ShortClass();

        public Symbols.ClassSymbolApi CharClass();

        public Symbols.ClassSymbolApi IntClass();

        public Symbols.ClassSymbolApi LongClass();

        public Symbols.ClassSymbolApi FloatClass();

        public Symbols.ClassSymbolApi DoubleClass();

        public Symbols.ClassSymbolApi BooleanClass();

        public Symbols.ClassSymbolApi StringClass();

        public Symbols.ClassSymbolApi ClassClass();

        public Symbols.ClassSymbolApi ArrayClass();

        public Symbols.ClassSymbolApi ListClass();

        public Symbols.ModuleSymbolApi PredefModule();

        public Symbols.ClassSymbolApi JavaLangPackageClass();

        public Symbols.ModuleSymbolApi JavaLangPackage();

        public Symbols.ModuleSymbolApi ArrayModule();

        public Symbols.TermSymbolApi ArrayModule_overloadedApply();

        public Symbols.TermSymbolApi Array_apply();

        public Symbols.TermSymbolApi Array_clone();

        public Symbols.TermSymbolApi Array_length();

        public Symbols.TermSymbolApi Array_update();

        public Symbols.ClassSymbolApi ByNameParamClass();

        public Symbols.ClassSymbolApi JavaRepeatedParamClass();

        public Symbols.ClassSymbolApi RepeatedParamClass();

        public Symbols.ModuleSymbolApi ListModule();

        public Symbols.TermSymbolApi List_apply();

        public Symbols.ModuleSymbolApi NilModule();

        public Symbols.ClassSymbolApi OptionClass();

        public Symbols.ModuleSymbolApi NoneModule();

        public Symbols.ModuleSymbolApi SomeModule();

        public VarArityClassApi ProductClass();

        public VarArityClassApi FunctionClass();

        public VarArityClassApi TupleClass();

        public List<Symbols.ClassSymbolApi> ScalaPrimitiveValueClasses();

        public List<Symbols.ClassSymbolApi> ScalaNumericValueClasses();

        public /* synthetic */ StandardDefinitions scala$reflect$api$StandardDefinitions$DefinitionsApi$$$outer();

        public abstract class VarArityClassApi
        implements Function1<Object, Symbols.SymbolApi> {
            @Override
            public boolean apply$mcZD$sp(double v1) {
                return Function1$class.apply$mcZD$sp(this, v1);
            }

            @Override
            public double apply$mcDD$sp(double v1) {
                return Function1$class.apply$mcDD$sp(this, v1);
            }

            @Override
            public float apply$mcFD$sp(double v1) {
                return Function1$class.apply$mcFD$sp(this, v1);
            }

            @Override
            public int apply$mcID$sp(double v1) {
                return Function1$class.apply$mcID$sp(this, v1);
            }

            @Override
            public long apply$mcJD$sp(double v1) {
                return Function1$class.apply$mcJD$sp(this, v1);
            }

            @Override
            public void apply$mcVD$sp(double v1) {
                Function1$class.apply$mcVD$sp(this, v1);
            }

            @Override
            public boolean apply$mcZF$sp(float v1) {
                return Function1$class.apply$mcZF$sp(this, v1);
            }

            @Override
            public double apply$mcDF$sp(float v1) {
                return Function1$class.apply$mcDF$sp(this, v1);
            }

            @Override
            public float apply$mcFF$sp(float v1) {
                return Function1$class.apply$mcFF$sp(this, v1);
            }

            @Override
            public int apply$mcIF$sp(float v1) {
                return Function1$class.apply$mcIF$sp(this, v1);
            }

            @Override
            public long apply$mcJF$sp(float v1) {
                return Function1$class.apply$mcJF$sp(this, v1);
            }

            @Override
            public void apply$mcVF$sp(float v1) {
                Function1$class.apply$mcVF$sp(this, v1);
            }

            @Override
            public boolean apply$mcZI$sp(int v1) {
                return Function1$class.apply$mcZI$sp(this, v1);
            }

            @Override
            public double apply$mcDI$sp(int v1) {
                return Function1$class.apply$mcDI$sp(this, v1);
            }

            @Override
            public float apply$mcFI$sp(int v1) {
                return Function1$class.apply$mcFI$sp(this, v1);
            }

            @Override
            public int apply$mcII$sp(int v1) {
                return Function1$class.apply$mcII$sp(this, v1);
            }

            @Override
            public long apply$mcJI$sp(int v1) {
                return Function1$class.apply$mcJI$sp(this, v1);
            }

            @Override
            public void apply$mcVI$sp(int v1) {
                Function1$class.apply$mcVI$sp(this, v1);
            }

            @Override
            public boolean apply$mcZJ$sp(long v1) {
                return Function1$class.apply$mcZJ$sp(this, v1);
            }

            @Override
            public double apply$mcDJ$sp(long v1) {
                return Function1$class.apply$mcDJ$sp(this, v1);
            }

            @Override
            public float apply$mcFJ$sp(long v1) {
                return Function1$class.apply$mcFJ$sp(this, v1);
            }

            @Override
            public int apply$mcIJ$sp(long v1) {
                return Function1$class.apply$mcIJ$sp(this, v1);
            }

            @Override
            public long apply$mcJJ$sp(long v1) {
                return Function1$class.apply$mcJJ$sp(this, v1);
            }

            @Override
            public void apply$mcVJ$sp(long v1) {
                Function1$class.apply$mcVJ$sp(this, v1);
            }

            @Override
            public <A> Function1<A, Symbols.SymbolApi> compose(Function1<A, Object> g) {
                return Function1$class.compose(this, g);
            }

            @Override
            public <A> Function1<Object, A> andThen(Function1<Symbols.SymbolApi, A> g) {
                return Function1$class.andThen(this, g);
            }

            @Override
            public String toString() {
                return Function1$class.toString(this);
            }

            public abstract Seq<Symbols.ClassSymbolApi> seq();

            public /* synthetic */ DefinitionsApi scala$reflect$api$StandardDefinitions$DefinitionsApi$VarArityClassApi$$$outer() {
                return DefinitionsApi.this;
            }

            public VarArityClassApi() {
                if (DefinitionsApi.this == null) {
                    throw null;
                }
                Function1$class.$init$(this);
            }
        }
    }
}

