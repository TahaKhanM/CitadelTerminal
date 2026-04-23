/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Depth;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.settings.MutableSettings;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u0005egAC\u0001\u0003!\u0003\r\t\u0001\u0002\u0006\u0002T\n9q\t\u001c2Mk\n\u001c(BA\u0002\u0005\u0003\r!\b/\u001a\u0006\u0003\u000b\u0019\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u000f!\tqA]3gY\u0016\u001cGOC\u0001\n\u0003\u0015\u00198-\u00197b'\t\u00011\u0002\u0005\u0002\r\u001b5\t\u0001\"\u0003\u0002\u000f\u0011\t1\u0011I\\=SK\u001aDQ\u0001\u0005\u0001\u0005\u0002I\ta\u0001J5oSR$3\u0001\u0001\u000b\u0002'A\u0011A\u0002F\u0005\u0003+!\u0011A!\u00168ji\"9q\u0003\u0001b\u0001\n\u001bA\u0012!\u00039sS:$H*\u001e2t+\u0005I\u0002C\u0001\u0007\u001b\u0013\tY\u0002BA\u0004C_>dW-\u00198\t\ru\u0001\u0001\u0015!\u0004\u001a\u0003)\u0001(/\u001b8u\u0019V\u00147\u000f\t\u0005\b?\u0001\u0011\r\u0011\"\u0004!\u0003=\u0019HO]5di&sg-\u001a:f]\u000e,W#A\u0011\u0011\u0005\tB\u0003CA\u0012'\u001b\u0005!#BA\u0013\u0005\u0003!\u0019X\r\u001e;j]\u001e\u001c\u0018BA\u0014%\u0005=iU\u000f^1cY\u0016\u001cV\r\u001e;j]\u001e\u001c\u0018BA\u0015'\u00059\u0011un\u001c7fC:\u001cV\r\u001e;j]\u001eDaa\u000b\u0001!\u0002\u001b\t\u0013\u0001E:ue&\u001cG/\u00138gKJ,gnY3!\u0011\u001di\u0003A1A\u0005\u000e9\n!B^3sS\u001aLH*\u001e2t+\u0005ys\"\u0001\u0019\u001a\u0003\u0005AaA\r\u0001!\u0002\u001by\u0013a\u0003<fe&4\u0017\u0010T;cg\u0002BQ\u0001\u000e\u0001\u0005\nU\na\u0002\u001d:j]RdUOY'biJL\u0007\u0010F\u0002\u0014m1CQaN\u001aA\u0002a\naA\u0019;t\u001b\u0006\u0004\b\u0003B\u001d=\u007f\u0015s!\u0001\u0004\u001e\n\u0005mB\u0011A\u0002)sK\u0012,g-\u0003\u0002>}\t\u0019Q*\u00199\u000b\u0005mB\u0001C\u0001!B\u001b\u0005\u0001\u0011B\u0001\"D\u0005\u0011!\u0016\u0010]3\n\u0005\u0011#!!\u0002+za\u0016\u001c\bc\u0001$J\u007f9\u0011AbR\u0005\u0003\u0011\"\tq\u0001]1dW\u0006<W-\u0003\u0002K\u0017\n!A*[:u\u0015\tA\u0005\u0002C\u0003Ng\u0001\u0007a*A\u0003eKB$\b\u000e\u0005\u0002P!6\tA!\u0003\u0002R\t\t)A)\u001a9uQ\")1\u000b\u0001C\u0001)\u0006\u0019b-\u001b8e%\u0016\u001cWO]:jm\u0016\u0014u.\u001e8egR\u0011QK\u0018\t\u0004\r&3\u0006\u0003\u0002\u0007X3fK!\u0001\u0017\u0005\u0003\rQ+\b\u000f\\33!\t\u0001%,\u0003\u0002\\9\n11+_7c_2L!!\u0018\u0003\u0003\u000fMKXNY8mg\")qL\u0015a\u0001\u000b\u0006\u0011Ao\u001d\u0005\u0006C\u0002!IAY\u0001\u001bo&dGNV5pY\u0006$XMU3dkJ\u001c\u0018N^3C_VtGm\u001d\u000b\u00053\r,g\rC\u0003eA\u0002\u0007q(\u0001\u0002ua\")q\f\u0019a\u0001\u000b\")q\r\u0019a\u0001\u000b\u0006IAo]#mS6\u001cVO\u0019\u0005\u0006S\u0002!\tA[\u0001\bYV\u0014G*[:u)\r)5\u000e\u001c\u0005\u0006?\"\u0004\r!\u0012\u0005\u0006\u001b\"\u0004\rA\u0014\u0005\u0006]\u0002!Ia\\\u0001\u0007[&t7+_7\u0015\u0005e\u0003\b\"B9n\u0001\u0004)\u0015a\u0001;qg\")1\u000f\u0001C\u0001i\u0006i1\u000f]1o]&tw\rV=qKN$\"!R;\t\u000b}\u0013\b\u0019A#\t\u000b]\u0004A\u0011\u0002=\u0002\u0013\u0015d\u0017.\\*va\u0016\u0014HCA#z\u0011\u0015yf\u000f1\u0001F\u0011\u0015Y\b\u0001\"\u0003}\u0003\u001d)G.[7Tk\n$2!R?\u007f\u0011\u0015y&\u00101\u0001F\u0011\u0015i%\u00101\u0001O\u0011\u001d\t\t\u0001\u0001C\u0005\u0003\u0007\tAd\u001d;sSB,\u00050[:uK:$\u0018.\u00197t\u0003:$G+\u001f9f-\u0006\u00148\u000f\u0006\u0003\u0002\u0006\u0005%\u0001#\u0002\u0007X\u000b\u0006\u001d\u0001c\u0001$J3\")ql a\u0001\u000b\"9\u0011Q\u0002\u0001\u0005\u0002\u0005=\u0011\u0001E:b[\u0016<V-Y6Mk\n\f5\u000fT;c)\rI\u0012\u0011\u0003\u0005\u0007c\u0006-\u0001\u0019A#\t\u000f\u0005U\u0001\u0001\"\u0001\u0002\u0018\u00059q/Z1l\u0019V\u0014GcA \u0002\u001a!1\u0011/a\u0005A\u0002\u0015Cq!!\b\u0001\t\u0003\ty\"\u0001\u0006ok6,'/[2Mk\n$2aPA\u0011\u0011\u0019y\u00161\u0004a\u0001\u000b\"I\u0011Q\u0005\u0001C\u0002\u0013%\u0011qE\u0001\f?2,(MU3tk2$8/\u0006\u0002\u0002*A9\u00111FA\u001b\u0003syTBAA\u0017\u0015\u0011\ty#!\r\u0002\u000f5,H/\u00192mK*\u0019\u00111\u0007\u0005\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u00028\u00055\"a\u0002%bg\"l\u0015\r\u001d\t\u0005\u0019]sU\t\u0003\u0005\u0002>\u0001\u0001\u000b\u0011BA\u0015\u00031yF.\u001e2SKN,H\u000e^:!\u0011\u001d\t\t\u0005\u0001C\u0001\u0003O\t!\u0002\\;c%\u0016\u001cX\u000f\u001c;t\u0011%\t)\u0005\u0001b\u0001\n\u0013\t9#A\u0006`O2\u0014'+Z:vYR\u001c\b\u0002CA%\u0001\u0001\u0006I!!\u000b\u0002\u0019};GN\u0019*fgVdGo\u001d\u0011\t\u000f\u00055\u0003\u0001\"\u0001\u0002(\u0005Qq\r\u001c2SKN,H\u000e^:\t\u000f\u0005E\u0003\u0001\"\u0001\u0002T\u0005\u0019A.\u001e2\u0015\u0007}\n)\u0006\u0003\u0004`\u0003\u001f\u0002\r!\u0012\u0005\t\u0003#\u0002A\u0011\u0003\u0003\u0002ZQ)q(a\u0017\u0002^!1q,a\u0016A\u0002\u0015Ca!TA,\u0001\u0004q\u0005\"CA1\u0001\t\u0007I\u0011AA2\u0003)9EN\u0019$bS2,(/Z\u000b\u0003\u0003K\u0002B!a\u001a\u0002r5\u0011\u0011\u0011\u000e\u0006\u0005\u0003W\ni'\u0001\u0003mC:<'BAA8\u0003\u0011Q\u0017M^1\n\t\u0005M\u0014\u0011\u000e\u0002\n)\"\u0014xn^1cY\u0016D\u0001\"a\u001e\u0001A\u0003%\u0011QM\u0001\f\u000f2\u0014g)Y5mkJ,\u0007\u0005C\u0005\u0002|\u0001\u0001\r\u0011\"\u0003\u0002~\u0005qq\r\\8cC2<EN\u0019#faRDW#\u0001(\t\u0013\u0005\u0005\u0005\u00011A\u0005\n\u0005\r\u0015AE4m_\n\fGn\u00127c\t\u0016\u0004H\u000f[0%KF$2aEAC\u0011%\t9)a \u0002\u0002\u0003\u0007a*A\u0002yIEBq!a#\u0001A\u0003&a*A\bhY>\u0014\u0017\r\\$mE\u0012+\u0007\u000f\u001e5!\u0011%\ty\t\u0001b\u0001\n\u001b\ti(\u0001\bhY>\u0014\u0017\r\\$mE2KW.\u001b;\t\u000f\u0005M\u0005\u0001)A\u0007\u001d\u0006yq\r\\8cC2<EN\u0019'j[&$\b\u0005C\u0004\u0002\u0018\u0002!\t!!'\u0002\u0007\u001dd'\rF\u0002@\u00037CaaXAK\u0001\u0004)\u0005\u0002CAL\u0001\u0011EA!a(\u0015\u000b}\n\t+a)\t\r}\u000bi\n1\u0001F\u0011\u0019i\u0015Q\u0014a\u0001\u001d\"9\u0011q\u0015\u0001\u0005\u0012\u0005%\u0016aB4mE:{'/\u001c\u000b\u0006\u007f\u0005-\u0016Q\u0016\u0005\u0007?\u0006\u0015\u0006\u0019A#\t\r5\u000b)\u000b1\u0001O\u0011\u001d\t\t\f\u0001C\u0005\u0003g\u000ba\"\\1uG\"Lgn\u001a\"pk:$7\u000f\u0006\u0004\u00026\u0006]\u0016\u0011\u0018\t\u0004\r&+\u0005BB9\u00020\u0002\u0007Q\t\u0003\u0005\u0002<\u0006=\u0006\u0019AA\u0004\u0003\u001d!\b/\u0019:b[NDq!a0\u0001\t\u0013\t\t-A\tnCR\u001c\u0007.\u001b8h\u0013:\u001cH\u000fV=qKN$R!RAb\u0003\u000bDa!]A_\u0001\u0004)\u0005\u0002CA^\u0003{\u0003\r!a\u0002\t\u000f\u0005%\u0007\u0001\"\u0003\u0002L\u0006\u0001R.\u0019;dQ&twMU3tif\u0004Xm\u001d\u000b\u0006\u000b\u00065\u0017q\u001a\u0005\u0007c\u0006\u001d\u0007\u0019A#\t\u000f\u0005E\u0017q\u0019a\u0001\u000b\u0006\u0019\u0001\u000f^:\u0011\u0007=\u000b).C\u0002\u0002X\u0012\u00111bU=nE>dG+\u00192mK\u0002")
public interface GlbLubs {
    public void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$printLubs_$eq(boolean var1);

    public void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$strictInference_$eq(MutableSettings.SettingValue var1);

    public void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$_lubResults_$eq(HashMap var1);

    public void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$_glbResults_$eq(HashMap var1);

    public void scala$reflect$internal$tpe$GlbLubs$_setter_$GlbFailure_$eq(Throwable var1);

    public void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit_$eq(int var1);

    public boolean scala$reflect$internal$tpe$GlbLubs$$printLubs();

    public MutableSettings.SettingValue scala$reflect$internal$tpe$GlbLubs$$strictInference();

    public boolean scala$reflect$internal$tpe$GlbLubs$$verifyLubs();

    public List<Tuple2<Symbols.Symbol, Symbols.Symbol>> findRecursiveBounds(List<Types.Type> var1);

    public List<Types.Type> lubList(List<Types.Type> var1, int var2);

    public List<Types.Type> spanningTypes(List<Types.Type> var1);

    public boolean sameWeakLubAsLub(List<Types.Type> var1);

    public Types.Type weakLub(List<Types.Type> var1);

    public Types.Type numericLub(List<Types.Type> var1);

    public HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> scala$reflect$internal$tpe$GlbLubs$$_lubResults();

    public HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> lubResults();

    public HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> scala$reflect$internal$tpe$GlbLubs$$_glbResults();

    public HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> glbResults();

    public Types.Type lub(List<Types.Type> var1);

    public Types.Type lub(List<Types.Type> var1, int var2);

    public Throwable GlbFailure();

    public int scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth();

    @TraitSetter
    public void scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth_$eq(int var1);

    public int scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit();

    public Types.Type glb(List<Types.Type> var1);

    public Types.Type glb(List<Types.Type> var1, int var2);

    public Types.Type glbNorm(List<Types.Type> var1, int var2);
}

