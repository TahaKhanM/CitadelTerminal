/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.NioAclChmodReflective$;
import scala.reflect.internal.util.OwnerOnlyChmod;
import scala.reflect.internal.util.OwnerOnlyChmod$class;

@ScalaSignature(bytes="\u0006\u0001\u0005-u!B\u0001\u0003\u0011\u0003Y\u0011!\u0006(j_\u0006\u001bGn\u00115n_\u0012\u0014VM\u001a7fGRLg/\u001a\u0006\u0003\u0007\u0011\tA!\u001e;jY*\u0011QAB\u0001\tS:$XM\u001d8bY*\u0011q\u0001C\u0001\be\u00164G.Z2u\u0015\u0005I\u0011!B:dC2\f7\u0001\u0001\t\u0003\u00195i\u0011A\u0001\u0004\u0006\u001d\tA\ta\u0004\u0002\u0016\u001d&|\u0017i\u00197DQ6|GMU3gY\u0016\u001cG/\u001b<f'\ti\u0001\u0003\u0005\u0002\u0012%5\t\u0001\"\u0003\u0002\u0014\u0011\t1\u0011I\\=SK\u001aDQ!F\u0007\u0005\u0002Y\ta\u0001P5oSRtD#A\u0006\t\u000fai!\u0019!C\u00013\u0005Ya-\u001b7f?R|\u0007+\u0019;i+\u0005Q\u0002CA\u000e\"\u001b\u0005a\"BA\u0004\u001e\u0015\tqr$\u0001\u0003mC:<'\"\u0001\u0011\u0002\t)\fg/Y\u0005\u0003Eq\u0011a!T3uQ>$\u0007B\u0002\u0013\u000eA\u0003%!$\u0001\u0007gS2,w\f^8QCRD\u0007\u0005C\u0004'\u001b\t\u0007I\u0011A\u0014\u0002\u000b\u0019LG.Z:\u0016\u0003!\u0002$!K\u0018\u0011\u0007)ZS&D\u0001\u001e\u0013\taSDA\u0003DY\u0006\u001c8\u000f\u0005\u0002/_1\u0001A!\u0003\u00192\u0003\u0003\u0005\tQ!\u00014\u0005\ty\u0004\u0007\u0003\u00043\u001b\u0001\u0006I\u0001K\u0001\u0007M&dWm\u001d\u0011\u0012\u0005Q:\u0004CA\t6\u0013\t1\u0004BA\u0004O_RD\u0017N\\4\u0011\u0005EA\u0014BA\u001d\t\u0005\r\te.\u001f\u0005\bw5\u0011\r\u0011\"\u0001=\u0003)\u0001\u0018\r\u001e5`G2\f7o]\u000b\u0002{A\u0012a\b\u0011\t\u0004U-z\u0004C\u0001\u0018A\t%\u0001\u0014)!A\u0001\u0002\u000b\u00051\u0007\u0003\u0004C\u001b\u0001\u0006I!P\u0001\fa\u0006$\bnX2mCN\u001c\b\u0005C\u0004E\u001b\t\u0007I\u0011A\r\u0002)\u001d,GOR5mK\u0006#HO]5ckR,g+[3x\u0011\u00191U\u0002)A\u00055\u0005)r-\u001a;GS2,\u0017\t\u001e;sS\n,H/\u001a,jK^\u0004\u0003b\u0002%\u000e\u0005\u0004%\t!S\u0001\u0015Y&t7n\u00149uS>tW)\u001c9us\u0006\u0013(/Y=\u0016\u0003)\u0003\"AK&\n\u00051k\"AB(cU\u0016\u001cG\u000f\u0003\u0004O\u001b\u0001\u0006IAS\u0001\u0016Y&t7n\u00149uS>tW)\u001c9us\u0006\u0013(/Y=!\u0011\u001d\u0001VB1A\u0005\u0002E\u000b!$Y2m\r&dW-\u0011;ue&\u0014W\u000f^3WS\u0016<xl\u00197bgN,\u0012A\u0015\u0019\u0003'V\u00032AK\u0016U!\tqS\u000bB\u00051-\u0006\u0005\t\u0011!B\u0001g!1q+\u0004Q\u0001\nI\u000b1$Y2m\r&dW-\u0011;ue&\u0014W\u000f^3WS\u0016<xl\u00197bgN\u0004\u0003bB-\u000e\u0005\u0004%\tAW\u0001\u000fC\u000edWI\u001c;ss~\u001bG.Y:t+\u0005Y\u0006G\u0001/_!\rQ3&\u0018\t\u0003]y#\u0011\u0002M0\u0002\u0002\u0003\u0005)\u0011A\u001a\t\r\u0001l\u0001\u0015!\u0003\\\u0003=\t7\r\\#oiJLxl\u00197bgN\u0004\u0003b\u00022\u000e\u0005\u0004%\taY\u0001\u0016C\u000edWI\u001c;ss\n+\u0018\u000e\u001c3fe~\u001bG.Y:t+\u0005!\u0007GA3h!\rQ3F\u001a\t\u0003]\u001d$\u0011\u0002\r5\u0002\u0002\u0003\u0005)\u0011A\u001a\t\r%l\u0001\u0015!\u0003e\u0003Y\t7\r\\#oiJL()^5mI\u0016\u0014xl\u00197bgN\u0004\u0003bB6\u000e\u0005\u0004%\t!G\u0001\u000b]\u0016<()^5mI\u0016\u0014\bBB7\u000eA\u0003%!$A\u0006oK^\u0014U/\u001b7eKJ\u0004\u0003bB8\u000e\u0005\u0004%\t!G\u0001\u0016C\u000edWI\u001c;ss\n+\u0018\u000e\u001c3fe~\u0013W/\u001b7e\u0011\u0019\tX\u0002)A\u00055\u00051\u0012m\u00197F]R\u0014\u0018PQ;jY\u0012,'o\u00182vS2$\u0007\u0005C\u0004t\u001b\t\u0007I\u0011\u0001;\u0002'U\u001cXM\u001d)sS:\u001c\u0017\u000e\u001d7f?\u000ed\u0017m]:\u0016\u0003U\u0004$A\u001e=\u0011\u0007)Zs\u000f\u0005\u0002/q\u0012I\u0001'_A\u0001\u0002\u0003\u0015\ta\r\u0005\u0007u6\u0001\u000b\u0011B;\u0002)U\u001cXM\u001d)sS:\u001c\u0017\u000e\u001d7f?\u000ed\u0017m]:!\u0011\u001daXB1A\u0005\u0002e\tAb]3u!JLgnY5qC2DaA`\u0007!\u0002\u0013Q\u0012!D:fiB\u0013\u0018N\\2ja\u0006d\u0007\u0005\u0003\u0005\u0002\u00025\u0011\r\u0011\"\u0001\u001a\u00039\u0019X\r\u001e)fe6L7o]5p]NDq!!\u0002\u000eA\u0003%!$A\btKR\u0004VM]7jgNLwN\\:!\u0011%\tI!\u0004b\u0001\n\u0003\tY!\u0001\nbG2,e\u000e\u001e:z)f\u0004XmX2mCN\u001cXCAA\u0007a\u0011\ty!a\u0005\u0011\t)Z\u0013\u0011\u0003\t\u0004]\u0005MAA\u0003\u0019\u0002\u0016\u0005\u0005\t\u0011!B\u0001g!A\u0011qC\u0007!\u0002\u0013\ti!A\nbG2,e\u000e\u001e:z)f\u0004XmX2mCN\u001c\b\u0005\u0003\u0005\u0002\u001c5\u0011\r\u0011\"\u0001\u001a\u0003\u001d\u0019X\r\u001e+za\u0016Dq!a\b\u000eA\u0003%!$\u0001\u0005tKR$\u0016\u0010]3!\u0011%\t\u0019#\u0004b\u0001\n\u0003\t)#\u0001\rbG2,e\u000e\u001e:z!\u0016\u0014X.[:tS>twl\u00197bgN,\"!a\n1\t\u0005%\u0012Q\u0006\t\u0005U-\nY\u0003E\u0002/\u0003[!!\u0002MA\u0018\u0003\u0003\u0005\tQ!\u00014\u0011!\t\t$\u0004Q\u0001\n\u0005\u001d\u0012!G1dY\u0016sGO]=QKJl\u0017n]:j_:|6\r\\1tg\u0002B\u0001\"!\u000e\u000e\u0005\u0004%\t!G\u0001\u0019C\u000edWI\u001c;ssB+'/\\5tg&|gNV1mk\u0016\u001c\bbBA\u001d\u001b\u0001\u0006IAG\u0001\u001aC\u000edWI\u001c;ssB+'/\\5tg&|gNV1mk\u0016\u001c\b\u0005C\u0005\u0002>5\u0011\r\u0011\"\u0001\u0002@\u0005\u0011\u0012m\u00197F]R\u0014\u0018\u0010V=qK~\u000bE\nT(X+\t\t\t\u0005E\u0002\u001c\u0003\u0007J1!!\u0012\u001d\u0005\u00151\u0015.\u001a7e\u0011!\tI%\u0004Q\u0001\n\u0005\u0005\u0013aE1dY\u0016sGO]=UsB,w,\u0011'M\u001f^\u0003c!\u0002\b\u0003\u0005\u000553#BA&!\u0005=\u0003c\u0001\u0007\u0002R%\u0019\u00111\u000b\u0002\u0003\u001d=;h.\u001a:P]2L8\t[7pI\"9Q#a\u0013\u0005\u0002\u0005]CCAA-!\ra\u00111\n\u0005\t\u0003;\nY\u0005\"\u0001\u0002`\u0005)1\r[7pIR!\u0011\u0011MA4!\r\t\u00121M\u0005\u0004\u0003KB!\u0001B+oSRD\u0001\"!\u001b\u0002\\\u0001\u0007\u00111N\u0001\u0005M&dW\r\u0005\u0003\u0002n\u0005MTBAA8\u0015\r\t\thH\u0001\u0003S>LA!!\u001e\u0002p\t!a)\u001b7f\u0011!\tI(a\u0013\u0005\n\u0005m\u0014\u0001B1dYN$B!! \u0002\bB)\u0011qPAB\u00156\u0011\u0011\u0011\u0011\u0006\u0003\u0007}IA!!\"\u0002\u0002\n!A*[:u\u0011\u001d\tI)a\u001eA\u0002)\u000bQa\\<oKJ\u0004")
public final class NioAclChmodReflective
implements OwnerOnlyChmod {
    public static Field aclEntryType_ALLOW() {
        return NioAclChmodReflective$.MODULE$.aclEntryType_ALLOW();
    }

    public static Method aclEntryPermissionValues() {
        return NioAclChmodReflective$.MODULE$.aclEntryPermissionValues();
    }

    public static Class<?> aclEntryPermission_class() {
        return NioAclChmodReflective$.MODULE$.aclEntryPermission_class();
    }

    public static Method setType() {
        return NioAclChmodReflective$.MODULE$.setType();
    }

    public static Class<?> aclEntryType_class() {
        return NioAclChmodReflective$.MODULE$.aclEntryType_class();
    }

    public static Method setPermissions() {
        return NioAclChmodReflective$.MODULE$.setPermissions();
    }

    public static Method setPrincipal() {
        return NioAclChmodReflective$.MODULE$.setPrincipal();
    }

    public static Class<?> userPrinciple_class() {
        return NioAclChmodReflective$.MODULE$.userPrinciple_class();
    }

    public static Method aclEntryBuilder_build() {
        return NioAclChmodReflective$.MODULE$.aclEntryBuilder_build();
    }

    public static Method newBuilder() {
        return NioAclChmodReflective$.MODULE$.newBuilder();
    }

    public static Class<?> aclEntryBuilder_class() {
        return NioAclChmodReflective$.MODULE$.aclEntryBuilder_class();
    }

    public static Class<?> aclEntry_class() {
        return NioAclChmodReflective$.MODULE$.aclEntry_class();
    }

    public static Class<?> aclFileAttributeView_class() {
        return NioAclChmodReflective$.MODULE$.aclFileAttributeView_class();
    }

    public static Object linkOptionEmptyArray() {
        return NioAclChmodReflective$.MODULE$.linkOptionEmptyArray();
    }

    public static Method getFileAttributeView() {
        return NioAclChmodReflective$.MODULE$.getFileAttributeView();
    }

    public static Class<?> path_class() {
        return NioAclChmodReflective$.MODULE$.path_class();
    }

    public static Class<?> files() {
        return NioAclChmodReflective$.MODULE$.files();
    }

    public static Method file_toPath() {
        return NioAclChmodReflective$.MODULE$.file_toPath();
    }

    @Override
    public final void chmodAndWrite(File file, byte[] contents) {
        OwnerOnlyChmod$class.chmodAndWrite(this, file, contents);
    }

    @Override
    public final void chmodOrCreateEmpty(File file) {
        OwnerOnlyChmod$class.chmodOrCreateEmpty(this, file);
    }

    @Override
    public void chmod(File file) {
        Object path = NioAclChmodReflective$.MODULE$.file_toPath().invoke((Object)file, new Object[0]);
        Object view = NioAclChmodReflective$.MODULE$.getFileAttributeView().invoke(null, path, NioAclChmodReflective$.MODULE$.aclFileAttributeView_class(), NioAclChmodReflective$.MODULE$.linkOptionEmptyArray());
        Method setAcl = NioAclChmodReflective$.MODULE$.aclFileAttributeView_class().getMethod("setAcl", List.class);
        Method getOwner = NioAclChmodReflective$.MODULE$.aclFileAttributeView_class().getMethod("getOwner", new Class[0]);
        Object owner2 = getOwner.invoke(view, new Object[0]);
        setAcl.invoke(view, this.acls(owner2));
    }

    private List<Object> acls(Object owner2) {
        Object builder = NioAclChmodReflective$.MODULE$.newBuilder().invoke(null, new Object[0]);
        NioAclChmodReflective$.MODULE$.setPrincipal().invoke(builder, owner2);
        NioAclChmodReflective$.MODULE$.setPermissions().invoke(builder, NioAclChmodReflective$.MODULE$.aclEntryPermissionValues().invoke(null, new Object[0]));
        NioAclChmodReflective$.MODULE$.setType().invoke(builder, NioAclChmodReflective$.MODULE$.aclEntryType_ALLOW().get(null));
        return Collections.singletonList(NioAclChmodReflective$.MODULE$.aclEntryBuilder_build().invoke(builder, new Object[0]));
    }

    public NioAclChmodReflective() {
        OwnerOnlyChmod$class.$init$(this);
    }
}

