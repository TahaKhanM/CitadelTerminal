/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import scala.Predef$;
import scala.Predef$any2stringadd$;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Types;
import scala.reflect.internal.tpe.TypeComparers$SubTypePair$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\r\u0005b!C\u0001\u0003!\u0003\r\taCB\u000e\u00055!\u0016\u0010]3D_6\u0004\u0018M]3sg*\u00111\u0001B\u0001\u0004iB,'BA\u0003\u0007\u0003!Ig\u000e^3s]\u0006d'BA\u0004\t\u0003\u001d\u0011XM\u001a7fGRT\u0011!C\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001A\u0002\u0005\u0002\u000e\u001d5\t\u0001\"\u0003\u0002\u0010\u0011\t1\u0011I\\=SK\u001aDQ!\u0005\u0001\u0005\u0002I\ta\u0001J5oSR$C#A\n\u0011\u00055!\u0012BA\u000b\t\u0005\u0011)f.\u001b;\t\u000f]\u0001!\u0019!C\u00071\u0005YBj\\4QK:$\u0017N\\4Tk\n$\u0016\u0010]3t)\"\u0014Xm\u001d5pY\u0012,\u0012!G\b\u00025u\t!\u0007\u0003\u0004\u001d\u0001\u0001\u0006i!G\u0001\u001d\u0019><\u0007+\u001a8eS:<7+\u001e2UsB,7\u000f\u00165sKNDw\u000e\u001c3!\u0011\u001dq\u0002A1A\u0005\n}\t\u0001c\u00189f]\u0012LgnZ*vERK\b/Z:\u0016\u0003\u0001\u00022!\t\u0014)\u001b\u0005\u0011#BA\u0012%\u0003\u001diW\u000f^1cY\u0016T!!\n\u0005\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002(E\t9\u0001*Y:i'\u0016$\bCA\u0015+\u001b\u0005\u0001a\u0001B\u0016\u0001\u00052\u00121bU;c)f\u0004X\rU1jeN!!\u0006D\u00171!\tia&\u0003\u00020\u0011\t9\u0001K]8ek\u000e$\bCA\u00072\u0013\t\u0011\u0004B\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0003\u00055U\tU\r\u0011\"\u00016\u0003\r!\b/M\u000b\u0002mA\u0011\u0011fN\u0005\u0003qe\u0012A\u0001V=qK&\u0011!\b\u0002\u0002\u0006)f\u0004Xm\u001d\u0005\ty)\u0012\t\u0012)A\u0005m\u0005!A\u000f]\u0019!\u0011!q$F!f\u0001\n\u0003)\u0014a\u0001;qe!A\u0001I\u000bB\tB\u0003%a'\u0001\u0003uaJ\u0002\u0003\"\u0002\"+\t\u0003\u0019\u0015A\u0002\u001fj]&$h\bF\u0002)\t\u0016CQ\u0001N!A\u0002YBQAP!A\u0002YBQa\u0012\u0016\u0005B!\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u0013B\u0011!jT\u0007\u0002\u0017*\u0011A*T\u0001\u0005Y\u0006twMC\u0001O\u0003\u0011Q\u0017M^1\n\u0005A[%AB*ue&tw\rC\u0004SU\u0005\u0005I\u0011A*\u0002\t\r|\u0007/\u001f\u000b\u0004QQ+\u0006b\u0002\u001bR!\u0003\u0005\rA\u000e\u0005\b}E\u0003\n\u00111\u00017\u0011\u001d9&&%A\u0005\u0002a\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001ZU\t1$lK\u0001\\!\ta\u0016-D\u0001^\u0015\tqv,A\u0005v]\u000eDWmY6fI*\u0011\u0001\rC\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00012^\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0005\bI*\n\n\u0011\"\u0001Y\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIIBqA\u001a\u0016\u0002\u0002\u0013\u0005s-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002\u0013\"9\u0011NKA\u0001\n\u0003Q\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A6\u0011\u00055a\u0017BA7\t\u0005\rIe\u000e\u001e\u0005\b_*\n\t\u0011\"\u0001q\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"!\u001d;\u0011\u00055\u0011\u0018BA:\t\u0005\r\te.\u001f\u0005\bk:\f\t\u00111\u0001l\u0003\rAH%\r\u0005\bo*\n\t\u0011\"\u0011y\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A=\u0011\u0007i\\\u0018/D\u0001%\u0013\taHE\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0011\u001dq(&!A\u0005\u0002}\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0003\t9\u0001E\u0002\u000e\u0003\u0007I1!!\u0002\t\u0005\u001d\u0011un\u001c7fC:Dq!^?\u0002\u0002\u0003\u0007\u0011\u000fC\u0005\u0002\f)\n\t\u0011\"\u0011\u0002\u000e\u0005A\u0001.Y:i\u0007>$W\rF\u0001l\u0011%\t\tBKA\u0001\n\u0003\n\u0019\"\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u0003\t)\u0002\u0003\u0005v\u0003\u001f\t\t\u00111\u0001r\u0011\u001d\tI\u0002\u0001Q\u0001\n\u0001\n\u0011c\u00189f]\u0012LgnZ*vERK\b/Z:!\u0011\u0019\ti\u0002\u0001C\u0001?\u0005y\u0001/\u001a8eS:<7+\u001e2UsB,7oB\u0005\u0002\"\u0001\t\t\u0011#\u0001\u0002$\u0005Y1+\u001e2UsB,\u0007+Y5s!\rI\u0013Q\u0005\u0004\tW\u0001\t\t\u0011#\u0001\u0002(M)\u0011QEA\u0015aA9\u00111FA\u0019mYBSBAA\u0017\u0015\r\ty\u0003C\u0001\beVtG/[7f\u0013\u0011\t\u0019$!\f\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007C\u0004C\u0003K!\t!a\u000e\u0015\u0005\u0005\r\u0002\u0002C$\u0002&\u0005\u0005IQ\t%\t\u0015\u0005u\u0012QEA\u0001\n\u0003\u000by$A\u0003baBd\u0017\u0010F\u0003)\u0003\u0003\n\u0019\u0005\u0003\u00045\u0003w\u0001\rA\u000e\u0005\u0007}\u0005m\u0002\u0019\u0001\u001c\t\u0015\u0005\u001d\u0013QEA\u0001\n\u0003\u000bI%A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005-\u0013q\u000b\t\u0006\u001b\u00055\u0013\u0011K\u0005\u0004\u0003\u001fB!AB(qi&|g\u000eE\u0003\u000e\u0003'2d'C\u0002\u0002V!\u0011a\u0001V;qY\u0016\u0014\u0004\"CA-\u0003\u000b\n\t\u00111\u0001)\u0003\rAH\u0005\r\u0005\t\u0003;\u0002\u0001\u0019!C\u0005U\u00061rl];cg\u0006lW\r^=qKJ+7-\u001e:tS>t7\u000fC\u0005\u0002b\u0001\u0001\r\u0011\"\u0003\u0002d\u0005Qrl];cg\u0006lW\r^=qKJ+7-\u001e:tS>t7o\u0018\u0013fcR\u00191#!\u001a\t\u0011U\fy&!AA\u0002-Dq!!\u001b\u0001A\u0003&1.A\f`gV\u00147/Y7fif\u0004XMU3dkJ\u001c\u0018n\u001c8tA!1\u0011Q\u000e\u0001\u0005\u0002)\fQc];cg\u0006lW\r^=qKJ+7-\u001e:tS>t7\u000fC\u0004\u0002r\u0001!\t!a\u001d\u00023M,(m]1nKRL\b/\u001a*fGV\u00148/[8og~#S-\u001d\u000b\u0004'\u0005U\u0004bBA<\u0003_\u0002\ra[\u0001\u0006m\u0006dW/\u001a\u0005\b\u0003w\u0002A\u0011BA?\u0003-I7/\u00168jM&\f'\r\\3\u0015\r\u0005\u0005\u0011qPAB\u0011\u001d\t\t)!\u001fA\u0002Y\nA\u0001\u001d:fc!9\u0011QQA=\u0001\u00041\u0014\u0001\u00029sKJBq!!#\u0001\t\u0013\tY)A\fjgN\u000bW.Z*qK\u000eL\u0017\r\\5{K\u0012\u001c6n\u001c7f[RQ\u0011\u0011AAG\u00037\u000by*!)\t\u0011\u0005=\u0015q\u0011a\u0001\u0003#\u000bAa]=ncA\u0019\u0011&a%\n\t\u0005U\u0015q\u0013\u0002\u0007'fl'm\u001c7\n\u0007\u0005eEAA\u0004Ts6\u0014w\u000e\\:\t\u0011\u0005u\u0015q\u0011a\u0001\u0003#\u000bAa]=ne!9\u0011\u0011QAD\u0001\u00041\u0004bBAC\u0003\u000f\u0003\rA\u000e\u0005\b\u0003K\u0003A\u0011BAT\u0003!I7oU;c!J,G\u0003CA\u0001\u0003S\u000bY+!,\t\u000f\u0005\u0005\u00151\u0015a\u0001m!9\u0011QQAR\u0001\u00041\u0004\u0002CAX\u0003G\u0003\r!!%\u0002\u0007MLX\u000eC\u0004\u00024\u0002!I!!.\u0002)\u0015\fX/\u00197Ts6\u001c\u0018I\u001c3Qe\u00164\u0017\u000e_3t))\t\t!a.\u0002:\u0006m\u0016Q\u0018\u0005\t\u0003\u001f\u000b\t\f1\u0001\u0002\u0012\"9\u0011\u0011QAY\u0001\u00041\u0004\u0002CAO\u0003c\u0003\r!!%\t\u000f\u0005\u0015\u0015\u0011\u0017a\u0001m!9\u0011\u0011\u0019\u0001\u0005\u0002\u0005\r\u0017aD5t\t&4g-\u001a:f]R$\u0016\u0010]3\u0015\r\u0005\u0005\u0011QYAd\u0011\u0019!\u0014q\u0018a\u0001m!1a(a0A\u0002YBq!a3\u0001\t\u0003\ti-\u0001\u000ejg\u0012KgMZ3sK:$H+\u001f9f\u0007>t7\u000f\u001e:vGR|'\u000f\u0006\u0004\u0002\u0002\u0005=\u0017\u0011\u001b\u0005\u0007i\u0005%\u0007\u0019\u0001\u001c\t\ry\nI\r1\u00017\u0011\u001d\t)\u000e\u0001C\u0005\u0003/\fQ#[:TC6,G+\u001f9f\u0007>t7\u000f\u001e:vGR|'\u000f\u0006\u0004\u0002\u0002\u0005e\u00171\u001d\u0005\t\u00037\f\u0019\u000e1\u0001\u0002^\u0006\u0019AO]\u0019\u0011\u0007%\ny.C\u0002\u0002bf\u0012q\u0001V=qKJ+g\r\u0003\u0005\u0002f\u0006M\u0007\u0019AAo\u0003\r!(O\r\u0005\b\u0003+\u0004A\u0011BAu)\u0019\t\t!a;\u0002n\"1A'a:A\u0002YBaAPAt\u0001\u00041\u0004bBAy\u0001\u0011\u0005\u00111_\u0001\u000bSN\u001c\u0016-\\3UsB,GCBA\u0001\u0003k\f9\u0010\u0003\u00045\u0003_\u0004\rA\u000e\u0005\u0007}\u0005=\b\u0019\u0001\u001c\t\u000f\u0005m\b\u0001\"\u0003\u0002~\u0006\u00112/Y7f\u0003:tw\u000e^1uK\u0012$\u0016\u0010]3t)\u0019\t\t!a@\u0003\u0002!1A'!?A\u0002YBaAPA}\u0001\u00041\u0004b\u0002B\u0003\u0001\u0011%!qA\u0001\fSN\u001c\u0016-\\3UsB,\u0017\u0007\u0006\u0004\u0002\u0002\t%!1\u0002\u0005\u0007i\t\r\u0001\u0019\u0001\u001c\t\ry\u0012\u0019\u00011\u00017\u0011\u001d\u0011y\u0001\u0001C\u0005\u0005#\tQ\"[:TC6,\u0007j\u0013+za\u0016\u001cHCBA\u0001\u0005'\u0011)\u0002\u0003\u00045\u0005\u001b\u0001\rA\u000e\u0005\u0007}\t5\u0001\u0019\u0001\u001c\t\u000f\te\u0001\u0001\"\u0003\u0003\u001c\u0005i\u0011n]*b[\u0016$\u0016\u0010]3SK\u001a$b!!\u0001\u0003\u001e\t}\u0001\u0002CAn\u0005/\u0001\r!!8\t\u0011\u0005\u0015(q\u0003a\u0001\u0003;DqAa\t\u0001\t\u0013\u0011)#A\njgN\u000bW.Z*j]\u001edW\r^8o)f\u0004X\r\u0006\u0004\u0002\u0002\t\u001d\"q\u0006\u0005\bi\t\u0005\u0002\u0019\u0001B\u0015!\rI#1F\u0005\u0004\u0005[I$!D*j]\u001edW\r^8o)f\u0004X\rC\u0004?\u0005C\u0001\rA!\u000b\t\u000f\tM\u0002\u0001\"\u0003\u00036\u0005\u0001\u0012n]*b[\u0016lU\r\u001e5pIRK\b/\u001a\u000b\u0007\u0003\u0003\u00119D!\u0011\t\u0011\te\"\u0011\u0007a\u0001\u0005w\t1!\u001c;2!\rI#QH\u0005\u0004\u0005\u007fI$AC'fi\"|G\rV=qK\"A!1\tB\u0019\u0001\u0004\u0011Y$A\u0002niJBqAa\u0012\u0001\t\u0013\u0011I%\u0001\rfcV\fG\u000eV=qKB\u000b'/Y7t\u0003:$'+Z:vYR$\"\"!\u0001\u0003L\tu#\u0011\rB3\u0011!\u0011iE!\u0012A\u0002\t=\u0013\u0001\u0003;qCJ\fWn]\u0019\u0011\r\tE#qKAI\u001d\ri!1K\u0005\u0004\u0005+B\u0011a\u00029bG.\fw-Z\u0005\u0005\u00053\u0012YF\u0001\u0003MSN$(b\u0001B+\u0011!9!q\fB#\u0001\u00041\u0014\u0001\u0002:fgFB\u0001Ba\u0019\u0003F\u0001\u0007!qJ\u0001\tiB\f'/Y7te!9!q\rB#\u0001\u00041\u0014\u0001\u0002:fgJBqAa\u001b\u0001\t\u0013\u0011i'A\u0014nKRDw\u000e\u001a%jO\",'o\u0014:eKJ$\u0016\u0010]3QCJ\fWn]*b[\u00164\u0016M]5b]\u000e,GCBA\u0001\u0005_\u0012\t\b\u0003\u0005\u0002\u0010\n%\u0004\u0019AAI\u0011!\tiJ!\u001bA\u0002\u0005E\u0005b\u0002B;\u0001\u0011%!qO\u0001'[\u0016$\bn\u001c3IS\u001eDWM](sI\u0016\u0014H+\u001f9f!\u0006\u0014\u0018-\\:Tk\n4\u0016M]5b]\u000e,GCBA\u0001\u0005s\u0012i\b\u0003\u0005\u0003|\tM\u0004\u0019AAI\u0003\rawn\u001e\u0005\t\u0005\u007f\u0012\u0019\b1\u0001\u0002\u0012\u0006!\u0001.[4i\u0011\u001d\u0011\u0019\t\u0001C\u0001\u0005\u000b\u000b1\"[:TC6,G+\u001f9feQ1\u0011\u0011\u0001BD\u0005\u0013Ca\u0001\u000eBA\u0001\u00041\u0004B\u0002 \u0003\u0002\u0002\u0007a\u0007C\u0004\u0003\u000e\u0002!\tAa$\u0002\u0013%\u001c8+\u001e2UsB,G\u0003CA\u0001\u0005#\u0013\u0019J!&\t\rQ\u0012Y\t1\u00017\u0011\u0019q$1\u0012a\u0001m!Q!q\u0013BF!\u0003\u0005\rA!'\u0002\u000b\u0011,\u0007\u000f\u001e5\u0011\t\tm%QT\u0007\u0002\t%\u0019!q\u0014\u0003\u0003\u000b\u0011+\u0007\u000f\u001e5\t\u000f\t\r\u0006\u0001\"\u0003\u0003&\u0006!B/\u001f9f%\u0016d\u0017\r^5p]B\u0013Xm\u00115fG.$bAa*\u00034\nU\u0006\u0003\u0002BU\u0005_k!Aa+\u000b\u0007\t5F!\u0001\u0003vi&d\u0017\u0002\u0002BY\u0005W\u0013\u0001\u0002\u0016:j'R\fG/\u001a\u0005\u0007i\t\u0005\u0006\u0019\u0001\u001c\t\ry\u0012\t\u000b1\u00017\u0011\u001d\u0011I\f\u0001C\u0005\u0005w\u000b!\"[:Tk\n$\u0016\u0010]32)!\t\tA!0\u0003@\n\u0005\u0007B\u0002\u001b\u00038\u0002\u0007a\u0007\u0003\u0004?\u0005o\u0003\rA\u000e\u0005\t\u0005/\u00139\f1\u0001\u0003\u001a\"9!Q\u0019\u0001\u0005\n\t\u001d\u0017!D5t!>d\u0017pU;c)f\u0004X\r\u0006\u0004\u0002\u0002\t%'\u0011\u001b\u0005\bi\t\r\u0007\u0019\u0001Bf!\rI#QZ\u0005\u0004\u0005\u001fL$\u0001\u0003)pYf$\u0016\u0010]3\t\u000fy\u0012\u0019\r1\u0001\u0003L\"9!Q\u001b\u0001\u0005\n\t]\u0017!F5t)\"L7/\u00118e'V\u0004XM]*vERL\b/\u001a\u000b\u0007\u0003\u0003\u0011INa7\t\rQ\u0012\u0019\u000e1\u00017\u0011\u0019q$1\u001ba\u0001m!9!q\u001c\u0001\u0005\u0002\t\u0005\u0018aC5t\u0011.\u001bVO\u0019+za\u0016$\u0002\"!\u0001\u0003d\n\u0015(q\u001d\u0005\u0007i\tu\u0007\u0019\u0001\u001c\t\ry\u0012i\u000e1\u00017\u0011!\u00119J!8A\u0002\te\u0005b\u0002Bv\u0001\u0011%!Q^\u0001\u000bSN\u001cVO\u0019+za\u0016\u0014D\u0003CA\u0001\u0005_\u0014\tPa=\t\rQ\u0012I\u000f1\u00017\u0011\u0019q$\u0011\u001ea\u0001m!A!q\u0013Bu\u0001\u0004\u0011I\nC\u0004\u0003x\u0002!\tA!?\u0002\u001b%\u001cx+Z1l'V\u0014G+\u001f9f)\u0019\t\tAa?\u0003~\"1AG!>A\u0002YBaA\u0010B{\u0001\u00041\u0004bBB\u0001\u0001\u0011\u000511A\u0001\u0011SNtU/\\3sS\u000e\u001cVO\u0019+za\u0016$b!!\u0001\u0004\u0006\r\u001d\u0001B\u0002\u001b\u0003\u0000\u0002\u0007a\u0007\u0003\u0004?\u0005\u007f\u0004\rA\u000e\u0005\b\u0007\u0017\u0001A\u0011BB\u0007\u0003I\u0001(/[7ji&4XMQ1tK\u000ec\u0017m]:\u0015\t\u0005E5q\u0002\u0005\b\u0007#\u0019I\u00011\u00017\u0003\t!\b\u000fC\u0005\u0004\u0016\u0001\t\n\u0011\"\u0001\u0004\u0018\u0005\u0019\u0012n]*vERK\b/\u001a\u0013eK\u001a\fW\u000f\u001c;%gU\u00111\u0011\u0004\u0016\u0004\u00053S\u0006\u0003\u0002BN\u0007;I1aa\b\u0005\u0005-\u0019\u00160\u001c2pYR\u000b'\r\\3")
public interface TypeComparers {
    public void scala$reflect$internal$tpe$TypeComparers$_setter_$scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes_$eq(HashSet var1);

    public int scala$reflect$internal$tpe$TypeComparers$$LogPendingSubTypesThreshold();

    public HashSet<SubTypePair> scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes();

    public HashSet<SubTypePair> pendingSubTypes();

    public TypeComparers$SubTypePair$ SubTypePair();

    public int scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions();

    @TraitSetter
    public void scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions_$eq(int var1);

    public int subsametypeRecursions();

    public void subsametypeRecursions_$eq(int var1);

    public boolean isDifferentType(Types.Type var1, Types.Type var2);

    public boolean isDifferentTypeConstructor(Types.Type var1, Types.Type var2);

    public boolean isSameType(Types.Type var1, Types.Type var2);

    public boolean isSameType2(Types.Type var1, Types.Type var2);

    public boolean isSubType(Types.Type var1, Types.Type var2, int var3);

    public int isSubType$default$3();

    public boolean isHKSubType(Types.Type var1, Types.Type var2, int var3);

    public boolean isWeakSubType(Types.Type var1, Types.Type var2);

    public boolean isNumericSubType(Types.Type var1, Types.Type var2);

    public final class SubTypePair
    implements Product,
    Serializable {
        private final Types.Type tp1;
        private final Types.Type tp2;
        private final /* synthetic */ SymbolTable $outer;

        public Types.Type tp1() {
            return this.tp1;
        }

        public Types.Type tp2() {
            return this.tp2;
        }

        public String toString() {
            return new StringBuilder().append((Object)Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(this.tp1()), " <:<? ")).append(this.tp2()).toString();
        }

        public SubTypePair copy(Types.Type tp1, Types.Type tp2) {
            return new SubTypePair(this.$outer, tp1, tp2);
        }

        public Types.Type copy$default$1() {
            return this.tp1();
        }

        public Types.Type copy$default$2() {
            return this.tp2();
        }

        @Override
        public String productPrefix() {
            return "SubTypePair";
        }

        @Override
        public int productArity() {
            return 2;
        }

        @Override
        public Object productElement(int x$1) {
            Types.Type type;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 1: {
                    type = this.tp2();
                    break;
                }
                case 0: {
                    type = this.tp1();
                }
            }
            return type;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof SubTypePair;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$._hashCode(this);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            if (!(x$1 instanceof SubTypePair)) return false;
            boolean bl = true;
            if (!bl) return false;
            SubTypePair subTypePair = (SubTypePair)x$1;
            Types.Type type = this.tp1();
            Types.Type type2 = subTypePair.tp1();
            if (type == null) {
                if (type2 != null) {
                    return false;
                }
            } else if (!type.equals(type2)) return false;
            Types.Type type3 = this.tp2();
            Types.Type type4 = subTypePair.tp2();
            if (type3 == null) {
                if (type4 == null) return true;
                return false;
            } else {
                if (!type3.equals(type4)) return false;
                return true;
            }
        }

        public SubTypePair(SymbolTable $outer, Types.Type tp1, Types.Type tp2) {
            this.tp1 = tp1;
            this.tp2 = tp2;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }
}

