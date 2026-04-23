/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Position;
import scala.reflect.api.Trees;
import scala.reflect.api.Types;
import scala.reflect.macros.TypecheckException$;
import scala.reflect.macros.blackbox.Context;

@ScalaSignature(bytes="\u0006\u0001\u0005-e\u0001C\u0001\u0003!\u0003\r\t!\u0003\u0010\u0003\rQK\b/\u001a:t\u0015\t\u0019A!\u0001\u0004nC\u000e\u0014xn\u001d\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-aQ\"\u0001\u0004\n\u000551!AB!osJ+g\rC\u0003\u0010\u0001\u0011\u0005\u0001#\u0001\u0004%S:LG\u000f\n\u000b\u0002#A\u00111BE\u0005\u0003'\u0019\u0011A!\u00168ji\")Q\u0003\u0001D\u0001-\u0005Qq\u000e]3o\u001b\u0006\u001c'o\\:\u0016\u0003]\u00012\u0001G\u000e\u001f\u001d\tY\u0011$\u0003\u0002\u001b\r\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u000f\u001e\u0005\u0011a\u0015n\u001d;\u000b\u0005i1\u0001CA\u0010#\u001b\u0005\u0001#BA\u0011\u0003\u0003!\u0011G.Y2lE>D\u0018BA\u0012!\u0005\u001d\u0019uN\u001c;fqR$Q!\n\u0001\u0003\u0002\u0019\u0012Q\u0002V=qK\u000eDWmY6N_\u0012,\u0017CA\u0014+!\tY\u0001&\u0003\u0002*\r\t9aj\u001c;iS:<\u0007CA\u0006,\u0013\tacAA\u0002B]fDqA\f\u0001C\u0002\u001b\u0005q&\u0001\u0005U\u000bJkUn\u001c3f+\u0005\u0001\u0004CA\u0019%\u001b\u0005\u0001\u0001bB\u001a\u0001\u0005\u00045\taL\u0001\t)f\u0003V)\\8eK\"9Q\u0007\u0001b\u0001\u000e\u0003y\u0013a\u0003)B)R+%KT7pI\u0016,Aa\u000e\u0001\u0001q\t\u0011B+\u001f9fG\",7m[#yG\u0016\u0004H/[8o!\tI$(D\u0001\u0003\u0013\t9$\u0001C\u0004=\u0001\t\u0007I\u0011A\u001f\u0002%QK\b/Z2iK\u000e\\W\t_2faRLwN\\\u000b\u0002}9\u0011q\b\u0013\b\u0003\u0001\u001es!!\u0011$\u000f\u0005\t+U\"A\"\u000b\u0005\u0011C\u0011A\u0002\u001fs_>$h(C\u0001\b\u0013\t)a!\u0003\u0002\u0004\t%\u0011AH\u0001\u0005\u0007\u0015\u0002\u0001\u000b\u0011\u0002 \u0002'QK\b/Z2iK\u000e\\W\t_2faRLwN\u001c\u0011\t\u000b1\u0003A\u0011A'\u0002\u0013QL\b/Z\"iK\u000e\\GC\u0002(T+j{\u0016\r\u0005\u00022\u001f&\u0011\u0001+\u0015\u0002\u0005)J,W-\u0003\u0002S\u0005\t9\u0011\t\\5bg\u0016\u001c\b\"\u0002+L\u0001\u0004q\u0015\u0001\u0002;sK\u0016DqAV&\u0011\u0002\u0003\u0007q+\u0001\u0002qiB\u0011\u0011\u0007W\u0005\u00033F\u0013A\u0001V=qK\"91l\u0013I\u0001\u0002\u0004a\u0016AB:jY\u0016tG\u000f\u0005\u0002\f;&\u0011aL\u0002\u0002\b\u0005>|G.Z1o\u0011\u001d\u00017\n%AA\u0002q\u000b\u0011d^5uQ&k\u0007\u000f\\5dSR4\u0016.Z<t\t&\u001c\u0018M\u00197fI\"9!m\u0013I\u0001\u0002\u0004a\u0016AE<ji\"l\u0015m\u0019:pg\u0012K7/\u00192mK\u0012DCa\u00133hSB\u00111\"Z\u0005\u0003M\u001a\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3eC\u0005A\u0017!G+tK\u0002\u00027M\f;za\u0016\u001c\u0007.Z2lA\u0002Jgn\u001d;fC\u0012\f\u0013A[\u0001\u0007e9\n\u0014G\f\u0019\t\u000b1\u0004a\u0011A7\u0002\u0013QL\b/Z2iK\u000e\\Gc\u0002(o_F\u00148\u000f\u001e\u0005\u0006).\u0004\rA\u0014\u0005\ba.\u0004\n\u00111\u00011\u0003\u0011iw\u000eZ3\t\u000fY[\u0007\u0013!a\u0001/\"91l\u001bI\u0001\u0002\u0004a\u0006b\u00021l!\u0003\u0005\r\u0001\u0018\u0005\bE.\u0004\n\u00111\u0001]\u0011\u00151\bA\"\u0001x\u0003IIgNZ3s\u00136\u0004H.[2jiZ\u000bG.^3\u0015\u000b9C\u0018P_>\t\u000bY+\b\u0019A,\t\u000fm+\b\u0013!a\u00019\"9!-\u001eI\u0001\u0002\u0004a\u0006b\u0002?v!\u0003\u0005\r!`\u0001\u0004a>\u001c\bCA\u0019\u007f\u0013\ty\u0018K\u0001\u0005Q_NLG/[8o\u0011\u001d\t\u0019\u0001\u0001D\u0001\u0003\u000b\t\u0011#\u001b8gKJLU\u000e\u001d7jG&$h+[3x)5q\u0015qAA\u0005\u0003\u001b\t\t\"a\u0005\u0002\u0016!1A+!\u0001A\u00029Cq!a\u0003\u0002\u0002\u0001\u0007q+\u0001\u0003ge>l\u0007bBA\b\u0003\u0003\u0001\raV\u0001\u0003i>D\u0001bWA\u0001!\u0003\u0005\r\u0001\u0018\u0005\tE\u0006\u0005\u0001\u0013!a\u00019\"AA0!\u0001\u0011\u0002\u0003\u0007Q\u0010C\u0004\u0002\u001a\u00011\t!a\u0007\u0002\u001fI,7/\u001a;M_\u000e\fG.\u0011;ueN$2ATA\u000f\u0011\u0019!\u0016q\u0003a\u0001\u001d\"2\u0011q\u00033\u0002\"%\f#!a\t\u00027U\u001bX\r\t1d]UtG/\u001f9fG\",7m\u001b1!S:\u001cH/Z1e\u0011\u001d\t9\u0003\u0001D\u0001\u0003S\t1\"\u001e8usB,7\r[3dWR\u0019a*a\u000b\t\rQ\u000b)\u00031\u0001O\u0011%\ty\u0003AI\u0001\n\u0003\t\t$A\nusB,7\t[3dW\u0012\"WMZ1vYR$#'\u0006\u0002\u00024)\u001aq+!\u000e,\u0005\u0005]\u0002\u0003BA\u001d\u0003\u0007j!!a\u000f\u000b\t\u0005u\u0012qH\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u0011\u0007\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u000b\nYDA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016D\u0011\"!\u0013\u0001#\u0003%\t!a\u0013\u0002'QL\b/Z\"iK\u000e\\G\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u00055#f\u0001/\u00026!I\u0011\u0011\u000b\u0001\u0012\u0002\u0013\u0005\u00111J\u0001\u0014if\u0004Xm\u00115fG.$C-\u001a4bk2$H\u0005\u000e\u0005\n\u0003+\u0002\u0011\u0013!C\u0001\u0003\u0017\n1\u0003^=qK\u000eCWmY6%I\u00164\u0017-\u001e7uIUB\u0011\"!\u0017\u0001#\u0003%\t!a\u0017\u0002'QL\b/Z2iK\u000e\\G\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005u#f\u0001\u0019\u00026!I\u0011\u0011\r\u0001\u0012\u0002\u0013\u0005\u0011\u0011G\u0001\u0014if\u0004Xm\u00195fG.$C-\u001a4bk2$He\r\u0005\n\u0003K\u0002\u0011\u0013!C\u0001\u0003\u0017\n1\u0003^=qK\u000eDWmY6%I\u00164\u0017-\u001e7uIQB\u0011\"!\u001b\u0001#\u0003%\t!a\u0013\u0002'QL\b/Z2iK\u000e\\G\u0005Z3gCVdG\u000fJ\u001b\t\u0013\u00055\u0004!%A\u0005\u0002\u0005-\u0013a\u0005;za\u0016\u001c\u0007.Z2lI\u0011,g-Y;mi\u00122\u0004\"CA9\u0001E\u0005I\u0011AA&\u0003qIgNZ3s\u00136\u0004H.[2jiZ\u000bG.^3%I\u00164\u0017-\u001e7uIIB\u0011\"!\u001e\u0001#\u0003%\t!a\u0013\u00029%tg-\u001a:J[Bd\u0017nY5u-\u0006dW/\u001a\u0013eK\u001a\fW\u000f\u001c;%g!I\u0011\u0011\u0010\u0001\u0012\u0002\u0013\u0005\u00111P\u0001\u001dS:4WM]%na2L7-\u001b;WC2,X\r\n3fM\u0006,H\u000e\u001e\u00135+\t\tiHK\u0002~\u0003kA\u0011\"!!\u0001#\u0003%\t!a\u0013\u00027%tg-\u001a:J[Bd\u0017nY5u-&,w\u000f\n3fM\u0006,H\u000e\u001e\u00135\u0011%\t)\tAI\u0001\n\u0003\tY%A\u000ej]\u001a,'/S7qY&\u001c\u0017\u000e\u001e,jK^$C-\u001a4bk2$H%\u000e\u0005\n\u0003\u0013\u0003\u0011\u0013!C\u0001\u0003w\n1$\u001b8gKJLU\u000e\u001d7jG&$h+[3xI\u0011,g-Y;mi\u00122\u0004")
public interface Typers {
    public void scala$reflect$macros$Typers$_setter_$TypecheckException_$eq(TypecheckException$ var1);

    public List<Context> openMacros();

    public Object TERMmode();

    public Object TYPEmode();

    public Object PATTERNmode();

    public TypecheckException$ TypecheckException();

    public Trees.TreeApi typeCheck(Trees.TreeApi var1, Types.TypeApi var2, boolean var3, boolean var4, boolean var5);

    public Types.TypeApi typeCheck$default$2();

    public boolean typeCheck$default$3();

    public boolean typeCheck$default$4();

    public boolean typeCheck$default$5();

    public Trees.TreeApi typecheck(Trees.TreeApi var1, Object var2, Types.TypeApi var3, boolean var4, boolean var5, boolean var6);

    public Object typecheck$default$2();

    public Types.TypeApi typecheck$default$3();

    public boolean typecheck$default$4();

    public boolean typecheck$default$5();

    public boolean typecheck$default$6();

    public Trees.TreeApi inferImplicitValue(Types.TypeApi var1, boolean var2, boolean var3, Position var4);

    public boolean inferImplicitValue$default$2();

    public boolean inferImplicitValue$default$3();

    public Position inferImplicitValue$default$4();

    public Trees.TreeApi inferImplicitView(Trees.TreeApi var1, Types.TypeApi var2, Types.TypeApi var3, boolean var4, boolean var5, Position var6);

    public boolean inferImplicitView$default$4();

    public boolean inferImplicitView$default$5();

    public Position inferImplicitView$default$6();

    public Trees.TreeApi resetLocalAttrs(Trees.TreeApi var1);

    public Trees.TreeApi untypecheck(Trees.TreeApi var1);
}

