/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function1;
import scala.MatchError;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;
import scala.math.Ordering;
import scala.math.Ordering$String$;
import scala.reflect.internal.Mode;
import scala.runtime.BoxesRunTime;

public final class Mode$ {
    public static final Mode$ MODULE$;
    private final int NOmode;
    private final int EXPRmode;
    private final int PATTERNmode;
    private final int TYPEmode;
    private final int SCCmode;
    private final int FUNmode;
    private final int POLYmode;
    private final int QUALmode;
    private final int TAPPmode;
    private final int LHSmode;
    private final int BYVALmode;
    private final int TYPEPATmode;
    private final int StickyModes;
    private final int StickyModesForFun;
    private final int MonoQualifierModes;
    private final int PolyQualifierModes;
    private final int OperatorModes;
    private final Map<Mode, String> modeNameMap;

    static {
        new Mode$();
    }

    private int liftIntBitsToMode(int bits2) {
        return this.apply(bits2);
    }

    public int apply(int bits2) {
        return bits2;
    }

    public final int NOmode() {
        return this.NOmode;
    }

    public final int EXPRmode() {
        return this.EXPRmode;
    }

    public final int PATTERNmode() {
        return this.PATTERNmode;
    }

    public final int TYPEmode() {
        return this.TYPEmode;
    }

    public final int SCCmode() {
        return this.SCCmode;
    }

    public final int FUNmode() {
        return this.FUNmode;
    }

    public final int POLYmode() {
        return this.POLYmode;
    }

    public final int QUALmode() {
        return this.QUALmode;
    }

    public final int TAPPmode() {
        return this.TAPPmode;
    }

    public final int LHSmode() {
        return this.LHSmode;
    }

    public final int BYVALmode() {
        return this.BYVALmode;
    }

    public final int TYPEPATmode() {
        return this.TYPEPATmode;
    }

    private int StickyModes() {
        return this.StickyModes;
    }

    private int StickyModesForFun() {
        return this.StickyModesForFun;
    }

    public final int MonoQualifierModes() {
        return this.MonoQualifierModes;
    }

    public final int PolyQualifierModes() {
        return this.PolyQualifierModes;
    }

    public final int OperatorModes() {
        return this.OperatorModes;
    }

    private Map<Mode, String> modeNameMap() {
        return this.modeNameMap;
    }

    public final int $amp$extension(int $this, int other) {
        return $this & other;
    }

    public final int $bar$extension(int $this, int other) {
        return $this | other;
    }

    public final int $amp$tilde$extension(int $this, int other) {
        return $this & ~other;
    }

    public final int onlyTypePat$extension(int $this) {
        return this.$amp$extension($this, this.TYPEPATmode());
    }

    public final int onlySticky$extension(int $this) {
        return this.$amp$extension($this, this.StickyModes());
    }

    public final int forFunMode$extension(int $this) {
        return this.$bar$extension(this.$bar$extension(this.$bar$extension(this.$amp$extension($this, this.StickyModesForFun()), this.FUNmode()), this.POLYmode()), this.BYVALmode());
    }

    public final int forTypeMode$extension(int $this) {
        return this.typingPatternOrTypePat$extension($this) ? this.$bar$extension(this.TYPEmode(), this.TYPEPATmode()) : this.TYPEmode();
    }

    public final boolean inAll$extension(int $this, int required) {
        return this.$amp$extension($this, required) == required;
    }

    public final boolean inAny$extension(int $this, int required) {
        return this.$amp$extension($this, required) != this.NOmode();
    }

    public final boolean inNone$extension(int $this, int prohibited) {
        return this.$amp$extension($this, prohibited) == this.NOmode();
    }

    public final boolean in$extension(int $this, int all, int none) {
        return this.inAll$extension($this, all) && this.inNone$extension($this, none);
    }

    public final int in$default$1$extension(int $this) {
        return this.NOmode();
    }

    public final int in$default$2$extension(int $this) {
        return this.NOmode();
    }

    public final boolean inByValMode$extension(int $this) {
        return this.inAll$extension($this, this.BYVALmode());
    }

    public final boolean inExprMode$extension(int $this) {
        return this.inAll$extension($this, this.EXPRmode());
    }

    public final boolean inFunMode$extension(int $this) {
        return this.inAll$extension($this, this.FUNmode());
    }

    public final boolean inPatternMode$extension(int $this) {
        return this.inAll$extension($this, this.PATTERNmode());
    }

    public final boolean inPolyMode$extension(int $this) {
        return this.inAll$extension($this, this.POLYmode());
    }

    public final boolean inQualMode$extension(int $this) {
        return this.inAll$extension($this, this.QUALmode());
    }

    public final boolean inSccMode$extension(int $this) {
        return this.inAll$extension($this, this.SCCmode());
    }

    public final boolean inTappMode$extension(int $this) {
        return this.inAll$extension($this, this.TAPPmode());
    }

    public final boolean inTypeMode$extension(int $this) {
        return this.inAll$extension($this, this.TYPEmode());
    }

    public final boolean typingExprByValue$extension(int $this) {
        return this.inAll$extension($this, this.$bar$extension(this.EXPRmode(), this.BYVALmode()));
    }

    public final boolean typingExprFun$extension(int $this) {
        return this.inAll$extension($this, this.$bar$extension(this.EXPRmode(), this.FUNmode()));
    }

    public final boolean typingExprNotFun$extension(int $this) {
        return this.in$extension($this, this.EXPRmode(), this.FUNmode());
    }

    public final boolean typingExprNotFunNotLhs$extension(int $this) {
        return this.in$extension($this, this.EXPRmode(), this.$bar$extension(this.FUNmode(), this.LHSmode()));
    }

    public final boolean typingExprNotLhs$extension(int $this) {
        return this.in$extension($this, this.EXPRmode(), this.LHSmode());
    }

    public final boolean typingExprNotValue$extension(int $this) {
        return this.in$extension($this, this.EXPRmode(), this.BYVALmode());
    }

    public final boolean typingMonoExprByValue$extension(int $this) {
        return this.in$extension($this, this.$bar$extension(this.EXPRmode(), this.BYVALmode()), this.POLYmode());
    }

    public final boolean typingConstructorPattern$extension(int $this) {
        return this.inAll$extension($this, this.$bar$extension(this.PATTERNmode(), this.FUNmode()));
    }

    public final boolean typingPatternNotConstructor$extension(int $this) {
        return this.in$extension($this, this.PATTERNmode(), this.FUNmode());
    }

    public final boolean typingPatternOrTypePat$extension(int $this) {
        return this.inAny$extension($this, this.$bar$extension(this.PATTERNmode(), this.TYPEPATmode()));
    }

    public final String toString$extension(int $this) {
        return $this == this.NOmode() ? "NOmode" : ((TraversableOnce)this.modeNameMap().filterKeys((Function1<Mode, Object>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final int $this$1;

            public final boolean apply(int required) {
                return Mode$.MODULE$.inAll$extension(this.$this$1, required);
            }
            {
                this.$this$1 = $this$1;
            }
        })).values().toList().sorted((Ordering)Ordering$String$.MODULE$)).mkString("-");
    }

    public final int hashCode$extension(int $this) {
        return ((Object)BoxesRunTime.boxToInteger($this)).hashCode();
    }

    public final boolean equals$extension(int $this, Object x$1) {
        int n;
        boolean bl = x$1 instanceof Mode;
        return bl && $this == (n = ((Mode)x$1).bits());
    }

    private Mode$() {
        MODULE$ = this;
        this.NOmode = this.liftIntBitsToMode(0);
        this.EXPRmode = this.liftIntBitsToMode(1);
        this.PATTERNmode = this.liftIntBitsToMode(2);
        this.TYPEmode = this.liftIntBitsToMode(4);
        this.SCCmode = this.liftIntBitsToMode(8);
        this.FUNmode = this.liftIntBitsToMode(16);
        this.POLYmode = this.liftIntBitsToMode(32);
        this.QUALmode = this.liftIntBitsToMode(64);
        this.TAPPmode = this.liftIntBitsToMode(128);
        this.LHSmode = this.liftIntBitsToMode(1024);
        this.BYVALmode = this.liftIntBitsToMode(32768);
        this.TYPEPATmode = this.liftIntBitsToMode(65536);
        this.StickyModes = this.$bar$extension(this.$bar$extension(this.EXPRmode(), this.PATTERNmode()), this.TYPEmode());
        this.StickyModesForFun = this.$bar$extension(this.StickyModes(), this.SCCmode());
        this.MonoQualifierModes = this.$bar$extension(this.EXPRmode(), this.QUALmode());
        this.PolyQualifierModes = this.$bar$extension(this.$bar$extension(this.EXPRmode(), this.QUALmode()), this.POLYmode());
        this.OperatorModes = this.$bar$extension(this.$bar$extension(this.$bar$extension(this.EXPRmode(), this.POLYmode()), this.TAPPmode()), this.FUNmode());
        Tuple2[] tuple2Array = new Tuple2[17];
        Integer n = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(1));
        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[0] = new Tuple2<Integer, String>(n, "EXPRmode");
        Integer n2 = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(2));
        Predef$ArrowAssoc$ predef$ArrowAssoc$2 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[1] = new Tuple2<Integer, String>(n2, "PATTERNmode");
        Integer n3 = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(4));
        Predef$ArrowAssoc$ predef$ArrowAssoc$3 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[2] = new Tuple2<Integer, String>(n3, "TYPEmode");
        Integer n4 = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(8));
        Predef$ArrowAssoc$ predef$ArrowAssoc$4 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[3] = new Tuple2<Integer, String>(n4, "SCCmode");
        Integer n5 = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(16));
        Predef$ArrowAssoc$ predef$ArrowAssoc$5 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[4] = new Tuple2<Integer, String>(n5, "FUNmode");
        Integer n6 = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(32));
        Predef$ArrowAssoc$ predef$ArrowAssoc$6 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[5] = new Tuple2<Integer, String>(n6, "POLYmode");
        Integer n7 = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(64));
        Predef$ArrowAssoc$ predef$ArrowAssoc$7 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[6] = new Tuple2<Integer, String>(n7, "QUALmode");
        Integer n8 = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(128));
        Predef$ArrowAssoc$ predef$ArrowAssoc$8 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[7] = new Tuple2<Integer, String>(n8, "TAPPmode");
        Integer n9 = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(256));
        Predef$ArrowAssoc$ predef$ArrowAssoc$9 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[8] = new Tuple2<Integer, String>(n9, "<>");
        Integer n10 = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(512));
        Predef$ArrowAssoc$ predef$ArrowAssoc$10 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[9] = new Tuple2<Integer, String>(n10, "<>");
        Integer n11 = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(1024));
        Predef$ArrowAssoc$ predef$ArrowAssoc$11 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[10] = new Tuple2<Integer, String>(n11, "LHSmode");
        Integer n12 = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(2048));
        Predef$ArrowAssoc$ predef$ArrowAssoc$12 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[11] = new Tuple2<Integer, String>(n12, "<>");
        Integer n13 = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(4096));
        Predef$ArrowAssoc$ predef$ArrowAssoc$13 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[12] = new Tuple2<Integer, String>(n13, "<>");
        Integer n14 = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(8192));
        Predef$ArrowAssoc$ predef$ArrowAssoc$14 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[13] = new Tuple2<Integer, String>(n14, "<>");
        Integer n15 = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(16384));
        Predef$ArrowAssoc$ predef$ArrowAssoc$15 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[14] = new Tuple2<Integer, String>(n15, "<>");
        tuple2Array[15] = Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(32768)), "BYVALmode");
        tuple2Array[16] = Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(65536)), "TYPEPATmode");
        this.modeNameMap = ((TraversableLike)Predef$.MODULE$.Map().apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array))).map(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Tuple2<Mode, String> apply(Tuple2<Object, String> x0$1) {
                if (x0$1 != null) {
                    String string2 = x0$1._2();
                    Mode mode = Predef$.MODULE$.ArrowAssoc(new Mode(Mode$.MODULE$.apply(x0$1._1$mcI$sp())));
                    Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
                    return new Tuple2<Mode, String>(mode, string2);
                }
                throw new MatchError(x0$1);
            }
        }, Map$.MODULE$.canBuildFrom());
    }
}

