/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.settings;

import scala.Option;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.settings.AbsSettings;
import scala.reflect.internal.settings.AbsSettings$class;
import scala.reflect.internal.settings.MutableSettings$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u0005]d!B\u0001\u0003\u0003\u0003Y!aD'vi\u0006\u0014G.Z*fiRLgnZ:\u000b\u0005\r!\u0011\u0001C:fiRLgnZ:\u000b\u0005\u00151\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u001dA\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u0013\u0005)1oY1mC\u000e\u00011c\u0001\u0001\r!A\u0011QBD\u0007\u0002\u0011%\u0011q\u0002\u0003\u0002\u0007\u0003:L(+\u001a4\u0011\u0005E\u0011R\"\u0001\u0002\n\u0005M\u0011!aC!cgN+G\u000f^5oONDQ!\u0006\u0001\u0005\u0002Y\ta\u0001P5oSRtD#A\f\u0011\u0005E\u0001A!B\r\u0001\u0005\u0003Q\"aB*fiRLgnZ\t\u00037y\u0001\"!\u0004\u000f\n\u0005uA!a\u0002(pi\"Lgn\u001a\t\u0003?\u0001j\u0011\u0001\u0001\u0004\bC\u0001\u0001\n1!\u0001#\u00051\u0019V\r\u001e;j]\u001e4\u0016\r\\;f'\r\u0001Cb\t\t\u0003?\u0011J!!\n\n\u0003\u001f\u0005\u00137oU3ui&twMV1mk\u0016DQa\n\u0011\u0005\u0002!\na\u0001J5oSR$C#A\u0015\u0011\u00055Q\u0013BA\u0016\t\u0005\u0011)f.\u001b;\t\u000f5\u0002\u0003\u0019!D\t]\u0005\ta/F\u00010!\t\u0001\u0014'D\u0001!\u0013\t\u0011DEA\u0001U\u0011\u001d!\u0004\u00051A\u0007\u0012U\nQA^0%KF$\"!\u000b\u001c\t\u000f]\u001a\u0014\u0011!a\u0001_\u0005\u0019\u0001\u0010J\u0019\t\u000fe\u0002\u0003\u0019!C\tu\u0005I1/\u001a;CsV\u001bXM]\u000b\u0002wA\u0011Q\u0002P\u0005\u0003{!\u0011qAQ8pY\u0016\fg\u000eC\u0004@A\u0001\u0007I\u0011\u0003!\u0002\u001bM,GOQ=Vg\u0016\u0014x\fJ3r)\tI\u0013\tC\u00048}\u0005\u0005\t\u0019A\u001e\t\r\r\u0003\u0003\u0015)\u0003<\u0003)\u0019X\r\u001e\"z+N,'\u000f\t\u0005\u0006\u000b\u0002\"\t\u0001K\u0001\fa>\u001cHoU3u\u0011>|7\u000eC\u0003HA\u0011\u0005!(A\u0005jg\u0012+g-Y;mi\")\u0011\n\tC\u0001u\u0005Y\u0011n]*fi\nKXk]3s\u0011\u0015Y\u0005\u0005\"\u0001/\u0003\u00151\u0018\r\\;f\u0011\u0015i\u0005\u0005\"\u0001O\u0003%1\u0018\r\\;f?\u0012*\u0017\u000f\u0006\u0002*\u001f\")\u0001\u000b\u0014a\u0001_\u0005\u0019\u0011M]4\t\u000bI\u0003C\u0011A*\u0002\u001dY\fG.^3TKR\u0014\u00150V:feV\tA\u000bE\u0002\u000e+>J!A\u0016\u0005\u0003\r=\u0003H/[8o\t\u0015A\u0006A!\u0001Z\u00059\u0011un\u001c7fC:\u001cV\r\u001e;j]\u001e\f\"a\u0007.\u0013\u0005mkf\u0001\u0002/\u0001\u0001i\u0013A\u0002\u0010:fM&tW-\\3oiz\u0002\"a\b\r\u0006\tIZ\u0006e\u000f\u0003\u0006A\u0002\u0011\t!\u0019\u0002\u000b\u0013:$8+\u001a;uS:<\u0017CA\u000ec%\t\u0019WL\u0002\u0003]\u0001\u0001\u0011W\u0001\u0002\u001adA\u0015\u0004\"!\u00044\n\u0005\u001dD!aA%oi\u0012)\u0011\u000e\u0001B\u0001U\n\u0011R*\u001e7uSN#(/\u001b8h'\u0016$H/\u001b8h#\tY2N\u0005\u0002m;\u001a!A\f\u0001\u0001l\u000b\u0011\u0011D\u000e\t8\u0011\u0007=\u0014XO\u0004\u0002\u000ea&\u0011\u0011\u000fC\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0019HO\u0001\u0003MSN$(BA9\t!\t1\u0018P\u0004\u0002\u000eo&\u0011\u0001\u0010C\u0001\u0007!J,G-\u001a4\n\u0005i\\(AB*ue&twM\u0003\u0002y\u0011!)Q\u0010\u0001D\u0001}\u0006i\u0001,\u001a=qKJLW.\u001a8uC2,\u0012a \t\u0003?]Ca!a\u0001\u0001\r\u0003q\u0018!\u0003-gk2dG*\u001e2t\u0011\u0019\t9\u0001\u0001D\u0001}\u0006\t\u0002L\\8QCRl\u0017\r^!oC2L8/[:\t\r\u0005-\u0001A\"\u0001\u007f\u0003%A\u0006O]5oiB|7\u000f\u0003\u0004\u0002\u0010\u00011\tA`\u0001\u0010gR\u0014\u0018n\u0019;J]\u001a,'/\u001a8dK\"1\u00111\u0003\u0001\u0007\u0002y\f\u0011\"\u00179pg\u0012,'-^4\t\r\u0005]\u0001A\"\u0001\u007f\u0003%I&/\u00198hKB|7\u000f\u0003\u0004\u0002\u001c\u00011\tA`\u0001\u000f3NDwn^:z[><h.\u001a:t\u0011\u0019\ty\u0002\u0001D\u0001}\u0006i\u0011l\u001d5poNLXn[5oINDa!a\t\u0001\r\u0003q\u0018a\u00032sK\u0006\\7)_2mKNDa!a\n\u0001\r\u0003q\u0018!\u00023fEV<\u0007BBA\u0016\u0001\u0019\u0005a0A\u0005eKZ,Gn\u001c9fe\"1\u0011q\u0006\u0001\u0007\u0002y\fA\"\u001a=qY\u0006Lg\u000e^=qKNDa!a\r\u0001\r\u0003q\u0018aD8wKJ\u0014\u0018\u000eZ3PE*,7\r^:\t\r\u0005]\u0002A\"\u0001\u007f\u0003)\u0001(/\u001b8uif\u0004Xm\u001d\u0005\u0007\u0003w\u0001a\u0011\u0001@\u0002\rUt\u0017.]5e\u0011\u0019\ty\u0004\u0001D\u0001}\u00069a/\u001a:c_N,\u0007BBA\"\u0001\u0019\u0005a0A\nZa\u0006\u0014H/[1m+:Lg-[2bi&|g\u000eC\u0004\u0002H\u00011\t!!\u0013\u0002\u0015e\u0013XmY;sg&|g.\u0006\u0002\u0002LA\u0011qd\u0018\u0005\b\u0003\u001f\u0002a\u0011AA%\u0003Ai\u0017\r_\"mCN\u001ch-\u001b7f\u001d\u0006lW\r\u0003\u0004\u0002T\u00011\tAO\u0001\u000bSN\u001c6-\u00197beE\ntaBA,\u0005!\u0005\u0011\u0011L\u0001\u0010\u001bV$\u0018M\u00197f'\u0016$H/\u001b8hgB\u0019\u0011#a\u0017\u0007\r\u0005\u0011\u0001\u0012AA/'\r\tY\u0006\u0004\u0005\b+\u0005mC\u0011AA1)\t\tI\u0006\u0003\u0005\u0002f\u0005mC1AA4\u0003]\u0011XM\u001a7fGR\u001cV\r\u001e;j]\u001e$vNQ8pY\u0016\fg\u000eF\u0002<\u0003SB\u0001\"a\u001b\u0002d\u0001\u0007\u0011QN\u0001\u0002gB\u0011qc\u0016\u0015\u0005\u0003G\n\t\bE\u0002\u000e\u0003gJ1!!\u001e\t\u0005\u0019Ig\u000e\\5oK\u0002")
public abstract class MutableSettings
implements AbsSettings {
    public static boolean reflectSettingToBoolean(SettingValue settingValue) {
        return MutableSettings$.MODULE$.reflectSettingToBoolean(settingValue);
    }

    public abstract SettingValue Xexperimental();

    public abstract SettingValue XfullLubs();

    public abstract SettingValue XnoPatmatAnalysis();

    public abstract SettingValue Xprintpos();

    public abstract SettingValue strictInference();

    public abstract SettingValue Yposdebug();

    public abstract SettingValue Yrangepos();

    public abstract SettingValue Yshowsymowners();

    public abstract SettingValue Yshowsymkinds();

    public abstract SettingValue breakCycles();

    public abstract SettingValue debug();

    public abstract SettingValue developer();

    public abstract SettingValue explaintypes();

    public abstract SettingValue overrideObjects();

    public abstract SettingValue printtypes();

    public abstract SettingValue uniqid();

    public abstract SettingValue verbose();

    public abstract SettingValue YpartialUnification();

    public abstract SettingValue Yrecursion();

    public abstract SettingValue maxClassfileName();

    public abstract boolean isScala211();

    public MutableSettings() {
        AbsSettings$class.$init$(this);
    }

    public interface SettingValue
    extends AbsSettings.AbsSettingValue {
        public Object v();

        public void v_$eq(Object var1);

        public boolean setByUser();

        @TraitSetter
        public void setByUser_$eq(boolean var1);

        public void postSetHook();

        @Override
        public boolean isDefault();

        public boolean isSetByUser();

        @Override
        public Object value();

        public void value_$eq(Object var1);

        public Option<Object> valueSetByUser();

        public /* synthetic */ MutableSettings scala$reflect$internal$settings$MutableSettings$SettingValue$$$outer();
    }
}

