/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u00055e!C\u0001\u0003!\u0003\r\t!CAD\u0005I\teN\\8uCRLwN\\\"iK\u000e\\WM]:\u000b\u0005\r!\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u000b!\tYA\"D\u0001\u0007\u0013\tiaA\u0001\u0004B]f\u0014VM\u001a\u0005\u0006\u001f\u0001!\t\u0001E\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003E\u0001\"a\u0003\n\n\u0005M1!\u0001B+oSR4q!\u0006\u0001\u0011\u0002\u0007\u0005aCA\tB]:|G/\u0019;j_:\u001c\u0005.Z2lKJ\u001c\"\u0001\u0006\u0006\t\u000b=!B\u0011\u0001\t\t\u000be!B\u0011\u0001\u000e\u0002\u0011%\u001c\u0018i\u0019;jm\u0016$\u0012a\u0007\t\u0003\u0017qI!!\b\u0004\u0003\u000f\t{w\u000e\\3b]\")q\u0004\u0006D\u0001A\u0005\u0011\u0012M\u001c8pi\u0006$\u0018n\u001c8t\u0007>tgm\u001c:n)\rY\u0012%\u000b\u0005\u0006Ey\u0001\raI\u0001\u0005iB,\u0017\u0007\u0005\u0002%K5\t\u0001!\u0003\u0002'O\t!A+\u001f9f\u0013\tA#AA\u0003UsB,7\u000fC\u0003+=\u0001\u00071%\u0001\u0003ua\u0016\u0014\u0004\"\u0002\u0017\u0015\t\u0003i\u0013AD1o]>$\u0018\r^5p]NdUO\u0019\u000b\u0004G9\u0002\u0004\"B\u0018,\u0001\u0004\u0019\u0013A\u0001;q\u0011\u0015\t4\u00061\u00013\u0003\t!8\u000fE\u00024m\rr!a\u0003\u001b\n\u0005U2\u0011a\u00029bG.\fw-Z\u0005\u0003oa\u0012A\u0001T5ti*\u0011QG\u0002\u0005\u0006uQ!\taO\u0001\u000fC:tw\u000e^1uS>t7o\u00127c)\r\u0019C(\u0010\u0005\u0006_e\u0002\ra\t\u0005\u0006ce\u0002\rA\r\u0005\u0006\u007fQ!\t\u0001Q\u0001\u0019C\u0012\f\u0007\u000f\u001e\"pk:$7\u000fV8B]:|G/\u0019;j_:\u001cH\u0003B!F\u000f>\u00032a\r\u001cC!\t!3)\u0003\u0002EO\tQA+\u001f9f\u0005>,h\u000eZ:\t\u000b\u0019s\u0004\u0019A!\u0002\r\t|WO\u001c3t\u0011\u0015Ae\b1\u0001J\u0003\u001d!\b/\u0019:b[N\u00042a\r\u001cK!\t!3*\u0003\u0002M\u001b\n11+_7c_2L!A\u0014\u0002\u0003\u000fMKXNY8mg\")\u0001K\u0010a\u0001e\u0005)A/\u0019:hg\")!\u000b\u0006C\u0001'\u0006q\u0011\r\u001a3B]:|G/\u0019;j_:\u001cHcA\u0012U7\")Q+\u0015a\u0001-\u0006!AO]3f!\t!s+\u0003\u0002Y3\n!AK]3f\u0013\tQ&AA\u0003Ue\u0016,7\u000fC\u0003]#\u0002\u00071%A\u0002ua\u0016DC!\u00150bGB\u00111bX\u0005\u0003A\u001a\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3eC\u0005\u0011\u0017!L\"sK\u0006$X\rI1oA\u0005s\u0017\r\\={KJ\u0004F.^4j]\u0002\ng\u000e\u001a\u0011vg\u0016\u0004\u0003\u000f\\;hS:\u001cH+\u001f9fI\u0006\nA-\u0001\u00043]E\u0002d&\r\u0005\u0006MR!\taZ\u0001\u0014G\u0006t\u0017\tZ1qi\u0006sgn\u001c;bi&|gn\u001d\u000b\u00057!Lw\u000eC\u0003VK\u0002\u0007a\u000bC\u0003kK\u0002\u00071.\u0001\u0003n_\u0012,\u0007C\u00017n\u001b\u0005\u0011\u0011B\u00018\u0003\u0005\u0011iu\u000eZ3\t\u000bA,\u0007\u0019A\u0012\u0002\u0005A$\b\u0006B3_e\u000e\f\u0013a]\u00015\u0007J,\u0017\r^3!C:\u0004\u0013I\\1msj,'\u000f\u00157vO&t\u0007%\u00198eAU\u001cX\rI2b]\u0006#\u0017\r\u001d;B]:|G/\u0019;j_:\u001c\b\"B;\u0015\t\u00031\u0018\u0001E1eCB$\u0018I\u001c8pi\u0006$\u0018n\u001c8t)\u00111v\u000f_=\t\u000bU#\b\u0019\u0001,\t\u000b)$\b\u0019A6\t\u000bA$\b\u0019A\u0012)\tQt6pY\u0011\u0002y\u0006\t4I]3bi\u0016\u0004\u0013M\u001c\u0011B]\u0006d\u0017P_3s!2,x-\u001b8!C:$\u0007%^:fA\u0005$\u0017\r\u001d;B]:|G/\u0019;j_:\u001c\b\"\u0002@\u0015\t\u0003y\u0018!E1eCB$H+\u001f9f\u001f\u001a\u0014V\r^;s]R91%!\u0001\u0002\u0004\u0005\u0015\u0001\"B+~\u0001\u00041\u0006\"\u00029~\u0001\u0004\u0019\u0003\u0002CA\u0004{\u0012\u0005\r!!\u0003\u0002\u000f\u0011,g-Y;miB!1\"a\u0003$\u0013\r\tiA\u0002\u0002\ty\tLh.Y7f}!*QPXA\tG\u0006\u0012\u00111C\u0001\u0002d\r\u0013X-\u0019;fA\u0005t\u0007%\u00118bYfTXM\u001d)mk\u001eLg\u000eI1oI\u0002*8/\u001a\u0011qYV<\u0017N\\:UsB,GMU3ukJtg\u0006\t(pi\u0016T\u0004\u0005\u001e5fA\u001d\"(/Z3(A\u0005\u0014x-^7f]R\u0004\u0003.\u001a:fA%\u001c(\u0002\u001e5fA\u001d*\u0007\u0010\u001d:(A=4\u0007%\u0019\u0011SKR,(O\u001c\u0011ue\u0016,7\bI\u0014qYV<\u0017N\\:UsB,GMU3ukJtw\u0005\t;bW\u0016\u001c\b\u0005\u001e5fAI+G/\u001e:oAQ\u0014X-\u001a\u0011jiN,GN\u001a\u0011bg\u0002\n'oZ;nK:$\b\"CA\f\u0001\u0001\u0007I\u0011BA\r\u0003I\tgN\\8uCRLwN\\\"iK\u000e\\WM]:\u0016\u0005\u0005m\u0001\u0003B\u001a7\u0003;\u0001\"\u0001\n\u000b\t\u0013\u0005\u0005\u0002\u00011A\u0005\n\u0005\r\u0012AF1o]>$\u0018\r^5p]\u000eCWmY6feN|F%Z9\u0015\u0007E\t)\u0003\u0003\u0006\u0002(\u0005}\u0011\u0011!a\u0001\u00037\t1\u0001\u001f\u00132\u0011!\tY\u0003\u0001Q!\n\u0005m\u0011aE1o]>$\u0018\r^5p]\u000eCWmY6feN\u0004\u0003bBA\u0018\u0001\u0011\u0005\u0011\u0011G\u0001\u0015C\u0012$\u0017I\u001c8pi\u0006$\u0018n\u001c8DQ\u0016\u001c7.\u001a:\u0015\u0007E\t\u0019\u0004\u0003\u0005\u00026\u00055\u0002\u0019AA\u000f\u0003\u001d\u0019\u0007.Z2lKJDa!!\u000f\u0001\t\u0003\u0001\u0012a\u0007:f[>4X-\u00117m\u0003:tw\u000e^1uS>t7\t[3dW\u0016\u00148\u000f\u0003\u0004 \u0001\u0011\u0005\u0011Q\b\u000b\u00067\u0005}\u00121\t\u0005\b\u0003\u0003\nY\u00041\u0001$\u0003\r!\b/\r\u0005\b\u0003\u000b\nY\u00041\u0001$\u0003\r!\bO\r\u0005\u0007Y\u0001!\t!!\u0013\u0015\u000b\r\nY%!\u0014\t\rq\u000b9\u00051\u0001$\u0011\u0019\t\u0014q\ta\u0001e!1!\b\u0001C\u0001\u0003#\"RaIA*\u0003+Ba\u0001XA(\u0001\u0004\u0019\u0003BB\u0019\u0002P\u0001\u0007!\u0007\u0003\u0004@\u0001\u0011\u0005\u0011\u0011\f\u000b\b\u0003\u0006m\u0013QLA0\u0011\u00191\u0015q\u000ba\u0001\u0003\"1\u0001*a\u0016A\u0002%Ca\u0001UA,\u0001\u0004\u0011\u0004B\u0002*\u0001\t\u0003\t\u0019\u0007F\u0003$\u0003K\n9\u0007\u0003\u0004V\u0003C\u0002\rA\u0016\u0005\u00079\u0006\u0005\u0004\u0019A\u0012\t\r\u0019\u0004A\u0011AA6)\u001dY\u0012QNA8\u0003cBa!VA5\u0001\u00041\u0006B\u00026\u0002j\u0001\u00071\u000e\u0003\u0004q\u0003S\u0002\ra\t\u0005\u0007k\u0002!\t!!\u001e\u0015\u000fY\u000b9(!\u001f\u0002|!1Q+a\u001dA\u0002YCaA[A:\u0001\u0004Y\u0007B\u00029\u0002t\u0001\u00071\u0005\u0003\u0004\u007f\u0001\u0011\u0005\u0011q\u0010\u000b\bG\u0005\u0005\u00151QAC\u0011\u0019)\u0016Q\u0010a\u0001-\"1\u0001/! A\u0002\rB\u0011\"a\u0002\u0002~\u0011\u0005\r!!\u0003\u0011\u00071\fI)C\u0002\u0002\f\n\u00111bU=nE>dG+\u00192mK\u0002")
public interface AnnotationCheckers {
    public List<AnnotationChecker> scala$reflect$internal$AnnotationCheckers$$annotationCheckers();

    @TraitSetter
    public void scala$reflect$internal$AnnotationCheckers$$annotationCheckers_$eq(List<AnnotationChecker> var1);

    public void addAnnotationChecker(AnnotationChecker var1);

    public void removeAllAnnotationCheckers();

    public boolean annotationsConform(Types.Type var1, Types.Type var2);

    public Types.Type annotationsLub(Types.Type var1, List<Types.Type> var2);

    public Types.Type annotationsGlb(Types.Type var1, List<Types.Type> var2);

    public List<Types.TypeBounds> adaptBoundsToAnnotations(List<Types.TypeBounds> var1, List<Symbols.Symbol> var2, List<Types.Type> var3);

    public Types.Type addAnnotations(Trees.Tree var1, Types.Type var2);

    public boolean canAdaptAnnotations(Trees.Tree var1, int var2, Types.Type var3);

    public Trees.Tree adaptAnnotations(Trees.Tree var1, int var2, Types.Type var3);

    public Types.Type adaptTypeOfReturn(Trees.Tree var1, Types.Type var2, Function0<Types.Type> var3);

    public interface AnnotationChecker {
        public boolean isActive();

        public boolean annotationsConform(Types.Type var1, Types.Type var2);

        public Types.Type annotationsLub(Types.Type var1, List<Types.Type> var2);

        public Types.Type annotationsGlb(Types.Type var1, List<Types.Type> var2);

        public List<Types.TypeBounds> adaptBoundsToAnnotations(List<Types.TypeBounds> var1, List<Symbols.Symbol> var2, List<Types.Type> var3);

        public Types.Type addAnnotations(Trees.Tree var1, Types.Type var2);

        public boolean canAdaptAnnotations(Trees.Tree var1, int var2, Types.Type var3);

        public Trees.Tree adaptAnnotations(Trees.Tree var1, int var2, Types.Type var3);

        public Types.Type adaptTypeOfReturn(Trees.Tree var1, Types.Type var2, Function0<Types.Type> var3);

        public /* synthetic */ AnnotationCheckers scala$reflect$internal$AnnotationCheckers$AnnotationChecker$$$outer();
    }
}

