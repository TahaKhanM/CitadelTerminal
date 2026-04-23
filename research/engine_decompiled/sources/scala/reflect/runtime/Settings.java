/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Option;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$SettingValue$class;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u00055e!B\u0001\u0003\u0001\u0011A!\u0001C*fiRLgnZ:\u000b\u0005\r!\u0011a\u0002:v]RLW.\u001a\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b'\t\u0001\u0011\u0002\u0005\u0002\u000b\u001f5\t1B\u0003\u0002\r\u001b\u0005A1/\u001a;uS:<7O\u0003\u0002\u000f\t\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002\u0011\u0017\tyQ*\u001e;bE2,7+\u001a;uS:<7\u000fC\u0003\u0013\u0001\u0011\u0005A#\u0001\u0004=S:LGOP\u0002\u0001)\u0005)\u0002C\u0001\f\u0001\u001b\u0005\u0011aa\u0002\r\u0001!\u0003\r\n!\u0007\u0002\b'\u0016$H/\u001b8h'\r9\"D\b\t\u00037qi\u0011AB\u0005\u0003;\u0019\u0011a!\u00118z%\u00164\u0007CA\u0010!\u001b\u0005\u0001\u0011BA\u0011\u0010\u00051\u0019V\r\u001e;j]\u001e4\u0016\r\\;f\r\u0011\u0019\u0003\u0001\u0001\u0013\u0003\u001d\t{w\u000e\\3b]N+G\u000f^5oON\u0019!EG\u0013\u0011\u0005}9\u0002\u0002C\u0014#\u0005\u0003\u0005\u000b\u0011\u0002\u0015\u0002\u0003a\u0004\"aG\u0015\n\u0005)2!a\u0002\"p_2,\u0017M\u001c\u0005\u0006%\t\"\t\u0001\f\u000b\u0003[9\u0002\"a\b\u0012\t\u000b\u001dZ\u0003\u0019\u0001\u0015\u0006\tA\u0012\u0003\u0001\u000b\u0002\u0002)\"9!G\ta\u0001\n#\u0019\u0014!\u0001<\u0016\u0003!Bq!\u000e\u0012A\u0002\u0013Ea'A\u0003w?\u0012*\u0017\u000f\u0006\u00028uA\u00111\u0004O\u0005\u0003s\u0019\u0011A!\u00168ji\"91\bNA\u0001\u0002\u0004A\u0013a\u0001=%c!1QH\tQ!\n!\n!A\u001e\u0011\t\u000b}\u0012C\u0011I\u001a\u0002\u000bY\fG.^3\u0007\t\u0005\u0003\u0001A\u0011\u0002\u000b\u0013:$8+\u001a;uS:<7c\u0001!\u001bK!Aq\u0005\u0011B\u0001B\u0003%A\t\u0005\u0002\u001c\u000b&\u0011aI\u0002\u0002\u0004\u0013:$\b\"\u0002\nA\t\u0003AECA%K!\ty\u0002\tC\u0003(\u000f\u0002\u0007A)\u0002\u00031\u0001\u0002!\u0005b\u0002\u001aA\u0001\u0004%\t\"T\u000b\u0002\t\"9Q\u0007\u0011a\u0001\n#yECA\u001cQ\u0011\u001dYd*!AA\u0002\u0011Ca!\u0010!!B\u0013!\u0005\"B A\t\u0003je\u0001\u0002+\u0001\u0001U\u0013!#T;mi&\u001cFO]5oON+G\u000f^5oON\u00191KG\u0013\t\u0011]\u001b&\u0011!Q\u0001\na\u000b!\u0001_:\u0011\u0007ecvL\u0004\u0002\u001c5&\u00111LB\u0001\ba\u0006\u001c7.Y4f\u0013\tifL\u0001\u0003MSN$(BA.\u0007!\t\u00017M\u0004\u0002\u001cC&\u0011!MB\u0001\u0007!J,G-\u001a4\n\u0005\u0011,'AB*ue&twM\u0003\u0002c\r!)!c\u0015C\u0001OR\u0011\u0001.\u001b\t\u0003?MCQa\u00164A\u0002a+A\u0001M*\u00011\"9!g\u0015a\u0001\n#aW#\u0001-\t\u000fU\u001a\u0006\u0019!C\t]R\u0011qg\u001c\u0005\bw5\f\t\u00111\u0001Y\u0011\u0019i4\u000b)Q\u00051\")qh\u0015C!Y\"91\u000f\u0001b\u0001\n\u0003!\u0018!\u0004-fqB,'/[7f]R\fG.F\u0001.\u0011\u00191\b\u0001)A\u0005[\u0005q\u0001,\u001a=qKJLW.\u001a8uC2\u0004\u0003b\u0002=\u0001\u0005\u0004%\t\u0001^\u0001\n1\u001a,H\u000e\u001c'vENDaA\u001f\u0001!\u0002\u0013i\u0013A\u0003-gk2dG*\u001e2tA!9A\u0010\u0001b\u0001\n\u0003!\u0018!\u0005-o_B\u000bG/\\1u\u0003:\fG._:jg\"1a\u0010\u0001Q\u0001\n5\n!\u0003\u00178p!\u0006$X.\u0019;B]\u0006d\u0017p]5tA!A\u0011\u0011\u0001\u0001C\u0002\u0013\u0005A/A\btiJL7\r^%oM\u0016\u0014XM\\2f\u0011\u001d\t)\u0001\u0001Q\u0001\n5\n\u0001c\u001d;sS\u000e$\u0018J\u001c4fe\u0016t7-\u001a\u0011\t\u0011\u0005%\u0001A1A\u0005\u0002Q\f\u0011\u0002\u00179sS:$\bo\\:\t\u000f\u00055\u0001\u0001)A\u0005[\u0005Q\u0001\f\u001d:j]R\u0004xn\u001d\u0011\t\u0011\u0005E\u0001A1A\u0005\u0002Q\f\u0011\"\u00179pg\u0012,'-^4\t\u000f\u0005U\u0001\u0001)A\u0005[\u0005Q\u0011\f]8tI\u0016\u0014Wo\u001a\u0011\t\u0011\u0005e\u0001A1A\u0005\u0002Q\f\u0011\"\u0017:b]\u001e,\u0007o\\:\t\u000f\u0005u\u0001\u0001)A\u0005[\u0005Q\u0011L]1oO\u0016\u0004xn\u001d\u0011\t\u0011\u0005\u0005\u0002A1A\u0005\u0002Q\fa\"W:i_^\u001c\u00180\\8x]\u0016\u00148\u000fC\u0004\u0002&\u0001\u0001\u000b\u0011B\u0017\u0002\u001fe\u001b\bn\\<ts6|wO\\3sg\u0002B\u0001\"!\u000b\u0001\u0005\u0004%\t\u0001^\u0001\u000e3NDwn^:z[.Lg\u000eZ:\t\u000f\u00055\u0002\u0001)A\u0005[\u0005q\u0011l\u001d5poNLXn[5oIN\u0004\u0003\u0002CA\u0019\u0001\t\u0007I\u0011\u0001;\u0002\u0017\t\u0014X-Y6Ds\u000edWm\u001d\u0005\b\u0003k\u0001\u0001\u0015!\u0003.\u00031\u0011'/Z1l\u0007f\u001cG.Z:!\u0011!\tI\u0004\u0001b\u0001\n\u0003!\u0018!\u00023fEV<\u0007bBA\u001f\u0001\u0001\u0006I!L\u0001\u0007I\u0016\u0014Wo\u001a\u0011\t\u0011\u0005\u0005\u0003A1A\u0005\u0002Q\f\u0011\u0002Z3wK2|\u0007/\u001a:\t\u000f\u0005\u0015\u0003\u0001)A\u0005[\u0005QA-\u001a<fY>\u0004XM\u001d\u0011\t\u0011\u0005%\u0003A1A\u0005\u0002Q\fA\"\u001a=qY\u0006Lg\u000e^=qKNDq!!\u0014\u0001A\u0003%Q&A\u0007fqBd\u0017-\u001b8usB,7\u000f\t\u0005\t\u0003#\u0002!\u0019!C\u0001i\u0006yqN^3se&$Wm\u00142kK\u000e$8\u000fC\u0004\u0002V\u0001\u0001\u000b\u0011B\u0017\u0002!=4XM\u001d:jI\u0016|%M[3diN\u0004\u0003\u0002CA-\u0001\t\u0007I\u0011\u0001;\u0002\u0015A\u0014\u0018N\u001c;usB,7\u000fC\u0004\u0002^\u0001\u0001\u000b\u0011B\u0017\u0002\u0017A\u0014\u0018N\u001c;usB,7\u000f\t\u0005\t\u0003C\u0002!\u0019!C\u0001i\u00061QO\\5rS\u0012Dq!!\u001a\u0001A\u0003%Q&A\u0004v]&\f\u0018\u000e\u001a\u0011\t\u0011\u0005%\u0004A1A\u0005\u0002Q\fqA^3sE>\u001cX\rC\u0004\u0002n\u0001\u0001\u000b\u0011B\u0017\u0002\u0011Y,'OY8tK\u0002B\u0001\"!\u001d\u0001\u0005\u0004%\t\u0001^\u0001\u00143B\f'\u000f^5bYVs\u0017NZ5dCRLwN\u001c\u0005\b\u0003k\u0002\u0001\u0015!\u0003.\u0003QI\u0006/\u0019:uS\u0006dWK\\5gS\u000e\fG/[8oA!I\u0011\u0011\u0010\u0001C\u0002\u0013\u0005\u00111P\u0001\u000b3J,7-\u001e:tS>tW#A%\t\u000f\u0005}\u0004\u0001)A\u0005\u0013\u0006Y\u0011L]3dkJ\u001c\u0018n\u001c8!\u0011%\t\u0019\t\u0001b\u0001\n\u0003\tY(\u0001\tnCb\u001cE.Y:tM&dWMT1nK\"9\u0011q\u0011\u0001!\u0002\u0013I\u0015!E7bq\u000ec\u0017m]:gS2,g*Y7fA!1\u00111\u0012\u0001\u0005\u0002M\n!\"[:TG\u0006d\u0017MM\u00192\u0001")
public class Settings
extends MutableSettings {
    private final BooleanSetting Xexperimental = new BooleanSetting(false);
    private final BooleanSetting XfullLubs = new BooleanSetting(false);
    private final BooleanSetting XnoPatmatAnalysis = new BooleanSetting(false);
    private final BooleanSetting strictInference = new BooleanSetting(false);
    private final BooleanSetting Xprintpos = new BooleanSetting(false);
    private final BooleanSetting Yposdebug = new BooleanSetting(false);
    private final BooleanSetting Yrangepos = new BooleanSetting(false);
    private final BooleanSetting Yshowsymowners = new BooleanSetting(false);
    private final BooleanSetting Yshowsymkinds = new BooleanSetting(false);
    private final BooleanSetting breakCycles = new BooleanSetting(false);
    private final BooleanSetting debug = new BooleanSetting(false);
    private final BooleanSetting developer = new BooleanSetting(false);
    private final BooleanSetting explaintypes = new BooleanSetting(false);
    private final BooleanSetting overrideObjects = new BooleanSetting(false);
    private final BooleanSetting printtypes = new BooleanSetting(false);
    private final BooleanSetting uniqid = new BooleanSetting(false);
    private final BooleanSetting verbose = new BooleanSetting(false);
    private final BooleanSetting YpartialUnification = new BooleanSetting(false);
    private final IntSetting Yrecursion = new IntSetting(0);
    private final IntSetting maxClassfileName = new IntSetting(255);

    @Override
    public BooleanSetting Xexperimental() {
        return this.Xexperimental;
    }

    @Override
    public BooleanSetting XfullLubs() {
        return this.XfullLubs;
    }

    @Override
    public BooleanSetting XnoPatmatAnalysis() {
        return this.XnoPatmatAnalysis;
    }

    @Override
    public BooleanSetting strictInference() {
        return this.strictInference;
    }

    @Override
    public BooleanSetting Xprintpos() {
        return this.Xprintpos;
    }

    @Override
    public BooleanSetting Yposdebug() {
        return this.Yposdebug;
    }

    @Override
    public BooleanSetting Yrangepos() {
        return this.Yrangepos;
    }

    @Override
    public BooleanSetting Yshowsymowners() {
        return this.Yshowsymowners;
    }

    @Override
    public BooleanSetting Yshowsymkinds() {
        return this.Yshowsymkinds;
    }

    @Override
    public BooleanSetting breakCycles() {
        return this.breakCycles;
    }

    @Override
    public BooleanSetting debug() {
        return this.debug;
    }

    @Override
    public BooleanSetting developer() {
        return this.developer;
    }

    @Override
    public BooleanSetting explaintypes() {
        return this.explaintypes;
    }

    @Override
    public BooleanSetting overrideObjects() {
        return this.overrideObjects;
    }

    @Override
    public BooleanSetting printtypes() {
        return this.printtypes;
    }

    @Override
    public BooleanSetting uniqid() {
        return this.uniqid;
    }

    @Override
    public BooleanSetting verbose() {
        return this.verbose;
    }

    @Override
    public BooleanSetting YpartialUnification() {
        return this.YpartialUnification;
    }

    @Override
    public IntSetting Yrecursion() {
        return this.Yrecursion;
    }

    @Override
    public IntSetting maxClassfileName() {
        return this.maxClassfileName;
    }

    @Override
    public boolean isScala211() {
        return true;
    }

    public interface Setting
    extends MutableSettings.SettingValue {
    }

    public class IntSetting
    implements Setting {
        private int v;
        private boolean setByUser;

        @Override
        public boolean setByUser() {
            return this.setByUser;
        }

        @Override
        @TraitSetter
        public void setByUser_$eq(boolean x$1) {
            this.setByUser = x$1;
        }

        @Override
        public void postSetHook() {
            MutableSettings$SettingValue$class.postSetHook(this);
        }

        @Override
        public boolean isDefault() {
            return MutableSettings$SettingValue$class.isDefault(this);
        }

        @Override
        public boolean isSetByUser() {
            return MutableSettings$SettingValue$class.isSetByUser(this);
        }

        @Override
        public void value_$eq(Object arg) {
            MutableSettings$SettingValue$class.value_$eq(this, arg);
        }

        @Override
        public Option<Object> valueSetByUser() {
            return MutableSettings$SettingValue$class.valueSetByUser(this);
        }

        public int v() {
            return this.v;
        }

        public void v_$eq(int x$1) {
            this.v = x$1;
        }

        public int value() {
            return this.v();
        }

        public /* synthetic */ Settings scala$reflect$runtime$Settings$IntSetting$$$outer() {
            return Settings.this;
        }

        @Override
        public /* synthetic */ MutableSettings scala$reflect$internal$settings$MutableSettings$SettingValue$$$outer() {
            return this.scala$reflect$runtime$Settings$IntSetting$$$outer();
        }

        public IntSetting(int x) {
            if (Settings.this == null) {
                throw null;
            }
            MutableSettings$SettingValue$class.$init$(this);
            this.v = x;
        }
    }

    public class BooleanSetting
    implements Setting {
        private boolean v;
        private boolean setByUser;

        @Override
        public boolean setByUser() {
            return this.setByUser;
        }

        @Override
        @TraitSetter
        public void setByUser_$eq(boolean x$1) {
            this.setByUser = x$1;
        }

        @Override
        public void postSetHook() {
            MutableSettings$SettingValue$class.postSetHook(this);
        }

        @Override
        public boolean isDefault() {
            return MutableSettings$SettingValue$class.isDefault(this);
        }

        @Override
        public boolean isSetByUser() {
            return MutableSettings$SettingValue$class.isSetByUser(this);
        }

        @Override
        public void value_$eq(Object arg) {
            MutableSettings$SettingValue$class.value_$eq(this, arg);
        }

        @Override
        public Option<Object> valueSetByUser() {
            return MutableSettings$SettingValue$class.valueSetByUser(this);
        }

        public boolean v() {
            return this.v;
        }

        public void v_$eq(boolean x$1) {
            this.v = x$1;
        }

        public boolean value() {
            return this.v();
        }

        public /* synthetic */ Settings scala$reflect$runtime$Settings$BooleanSetting$$$outer() {
            return Settings.this;
        }

        @Override
        public /* synthetic */ MutableSettings scala$reflect$internal$settings$MutableSettings$SettingValue$$$outer() {
            return this.scala$reflect$runtime$Settings$BooleanSetting$$$outer();
        }

        public BooleanSetting(boolean x) {
            if (Settings.this == null) {
                throw null;
            }
            MutableSettings$SettingValue$class.$init$(this);
            this.v = x;
        }
    }

    public class MultiStringSetting
    implements Setting {
        private List<String> v;
        private boolean setByUser;

        @Override
        public boolean setByUser() {
            return this.setByUser;
        }

        @Override
        @TraitSetter
        public void setByUser_$eq(boolean x$1) {
            this.setByUser = x$1;
        }

        @Override
        public void postSetHook() {
            MutableSettings$SettingValue$class.postSetHook(this);
        }

        @Override
        public boolean isDefault() {
            return MutableSettings$SettingValue$class.isDefault(this);
        }

        @Override
        public boolean isSetByUser() {
            return MutableSettings$SettingValue$class.isSetByUser(this);
        }

        @Override
        public void value_$eq(Object arg) {
            MutableSettings$SettingValue$class.value_$eq(this, arg);
        }

        @Override
        public Option<Object> valueSetByUser() {
            return MutableSettings$SettingValue$class.valueSetByUser(this);
        }

        @Override
        public List<String> v() {
            return this.v;
        }

        public void v_$eq(List<String> x$1) {
            this.v = x$1;
        }

        @Override
        public List<String> value() {
            return this.v();
        }

        public /* synthetic */ Settings scala$reflect$runtime$Settings$MultiStringSetting$$$outer() {
            return Settings.this;
        }

        @Override
        public /* synthetic */ MutableSettings scala$reflect$internal$settings$MutableSettings$SettingValue$$$outer() {
            return this.scala$reflect$runtime$Settings$MultiStringSetting$$$outer();
        }

        public MultiStringSetting(List<String> xs) {
            if (Settings.this == null) {
                throw null;
            }
            MutableSettings$SettingValue$class.$init$(this);
            this.v = xs;
        }
    }
}

