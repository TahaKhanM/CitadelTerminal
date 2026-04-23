/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Internals;
import scala.reflect.api.Universe;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.Position;
import scala.reflect.macros.Attachments;
import scala.reflect.macros.Universe;

@ScalaSignature(bytes="\u0006\u0001\u00195e!C\u0001\u0003!\u0003\r\t!\u0003DC\u0005%Ie\u000e^3s]\u0006d7O\u0003\u0002\u0004\t\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0019\u0001A\u0003\b\u0011\u0005-aQ\"\u0001\u0004\n\u000551!AB!osJ+g\r\u0005\u0002\u0010%5\t\u0001C\u0003\u0002\u0012\t\u0005\u0019\u0011\r]5\n\u0005\u0005\u0001\u0002\"\u0002\u000b\u0001\t\u0003)\u0012A\u0002\u0013j]&$H\u0005F\u0001\u0017!\tYq#\u0003\u0002\u0019\r\t!QK\\5u\u000b\u0011Q\u0002\u0001A\u000e\u0003\u0011%sG/\u001a:oC2\u0004\"\u0001H\u000f\u000e\u0003\u0001I!AH\u0010\u0003!5\u000b7M]8J]R,'O\\1m\u0003BL\u0017B\u0001\u0011\"\u0005!)f.\u001b<feN,'B\u0001\u0012\u0005\u0003\u0019i\u0017m\u0019:pg\"A1\u0001\u0001EC\u0002\u0013\u0005A%F\u0001&!\ta\u0012\u0004\u0003\u0005(\u0001!\u0005\t\u0015)\u0003&\u0003%Ig\u000e^3s]\u0006d\u0007%\u0002\u0003*\u0001\u0001Q#AB\"p[B\fG\u000f\u0005\u0002\u001dW%\u0011Af\b\u0002\u000f\u001b\u0006\u001c'o\\\"p[B\fG/\u00119j\u0011!q\u0003\u0001#b\u0001\n\u0003y\u0013AB2p[B\fG/F\u00011!\ta\u0002\u0006\u0003\u00053\u0001!\u0005\t\u0015)\u00031\u0003\u001d\u0019w.\u001c9bi\u00022q\u0001\u000e\u0001\u0011\u0002\u0007\u0005QGA\nTs6\u0014w\u000e\u001c+bE2,\u0017J\u001c;fe:\fGnE\u00024\u0015mAQ\u0001F\u001a\u0005\u0002UA\u0001\u0002O\u001a\t\u0006\u0004%\t!O\u0001\u0013e\u0016Lg-[2bi&|gnU;qa>\u0014H/F\u0001;!\ta2(\u0003\u0002=%\t)\"+Z5gS\u000e\fG/[8o'V\u0004\bo\u001c:u\u0003BL\u0007\u0002\u0003 4\u0011\u0003\u0005\u000b\u0015\u0002\u001e\u0002'I,\u0017NZ5dCRLwN\\*vaB|'\u000f\u001e\u0011\t\u000b\u0001\u001bD\u0011A!\u0002\u001d\r\u0014X-\u0019;f\u00136\u0004xN\u001d;feR\u0011!)\u0014\n\u0003\u0007\u00163A\u0001R\u001a\u0001\u0005\naAH]3gS:,W.\u001a8u}A\u0011ADR\u0005\u0003\u000fJ\u0011\u0001\"S7q_J$XM\u001d\u0005\b\u0013\u000e\u0013\rQ\"\u0011K\u0003\u00111'o\\7\u0016\u0003-s!\u0001T'\r\u0001!)aj\u0010a\u0001\u001f\u0006)aM]8naA\u0011q\u0002U\u0005\u0003AAAQAU\u001a\u0005\u0002M\u000bAB\\3x'\u000e|\u0007/Z,ji\"$\"\u0001V-\u0011\u0005q)\u0016B\u0001,X\u0005\u0015\u00196m\u001c9f\u0013\tA&A\u0001\u0004TG>\u0004Xm\u001d\u0005\u00065F\u0003\raW\u0001\u0006K2,Wn\u001d\t\u0004\u0017qs\u0016BA/\u0007\u0005)a$/\u001a9fCR,GM\u0010\t\u00039}K!\u0001Y1\u0003\rMKXNY8m\u0013\t\u0011'AA\u0004Ts6\u0014w\u000e\\:\t\u000b\u0011\u001cD\u0011A3\u0002\u000b\u0015tG/\u001a:\u0015\u0007\u0019<\u0017N\u0004\u0002MO\")\u0001n\u0019a\u0001)\u0006)1oY8qK\")!n\u0019a\u0001=\u0006\u00191/_7\t\u000b1\u001cD\u0011A7\u0002\rUtG.\u001b8l)\rqw\u000e\u001d\b\u0003\u0019>DQ\u0001[6A\u0002QCQA[6A\u0002yCQA]\u001a\u0005\u0002M\f\u0011B\u001a:fKR+'/\\:\u0015\u0005Qt\bcA;yw:\u00111B^\u0005\u0003o\u001a\tq\u0001]1dW\u0006<W-\u0003\u0002zu\n!A*[:u\u0015\t9h\u0001\u0005\u0002\u001dy&\u0011Q0\u0019\u0002\u000f\rJ,W\rV3s[NKXNY8m\u0011\u0019y\u0018\u000f1\u0001\u0002\u0002\u0005!AO]3f!\ra\u00121A\u0005\u0005\u0003\u000b\t9A\u0001\u0003Ue\u0016,\u0017bAA\u0005\u0005\t)AK]3fg\"9\u0011QB\u001a\u0005\u0002\u0005=\u0011!\u00034sK\u0016$\u0016\u0010]3t)\u0011\t\t\"!\u0007\u0011\tUD\u00181\u0003\t\u00049\u0005U\u0011bAA\fC\nqaI]3f)f\u0004XmU=nE>d\u0007bB@\u0002\f\u0001\u0007\u0011\u0011\u0001\u0005\b\u0003;\u0019D\u0011AA\u0010\u0003E\u0019XOY:uSR,H/Z*z[\n|Gn\u001d\u000b\t\u0003\u0003\t\t#a\t\u0002(!9q0a\u0007A\u0002\u0005\u0005\u0001bB%\u0002\u001c\u0001\u0007\u0011Q\u0005\t\u0004kbt\u0006\u0002CA\u0015\u00037\u0001\r!!\n\u0002\u0005Q|\u0007bBA\u0017g\u0011\u0005\u0011qF\u0001\u0010gV\u00147\u000f^5ukR,G+\u001f9fgRA\u0011\u0011AA\u0019\u0003g\t)\u0004C\u0004\u0000\u0003W\u0001\r!!\u0001\t\u000f%\u000bY\u00031\u0001\u0002&!A\u0011\u0011FA\u0016\u0001\u0004\t9\u0004\u0005\u0003vq\u0006e\u0002c\u0001\u000f\u0002<%!\u0011QHA \u0005\u0011!\u0016\u0010]3\n\u0007\u0005\u0005#AA\u0003UsB,7\u000fC\u0004\u0002FM\"\t!a\u0012\u0002\u001dM,(m\u001d;jiV$X\r\u00165jgRA\u0011\u0011AA%\u0003\u0017\ny\u0005C\u0004\u0000\u0003\u0007\u0002\r!!\u0001\t\u000f\u00055\u00131\ta\u0001=\u0006)1\r\\1{u\"A\u0011\u0011FA\"\u0001\u0004\t\t\u0001C\u0004\u0002TM\"\t!!\u0016\u0002\u0017\u0005$H/Y2i[\u0016tGo\u001d\u000b\u0005\u0003/\n\tH\u0005\u0003\u0002Z\u0005mc!\u0002#4\u0001\u0005]\u0003\u0003BA/\u0003?j\u0011!I\u0005\u0004\u0003C\n#aC!ui\u0006\u001c\u0007.\\3oiN,q!!\u001a\u0002Z\u0001\n9GA\u0002Q_N\u00042\u0001HA5\u0013\u0011\tY'!\u001c\u0003\u0011A{7/\u001b;j_:L1!a\u001c\u0003\u0005%\u0001vn]5uS>t7\u000fC\u0004\u0000\u0003#\u0002\r!!\u0001\t\u000f\u0005U4\u0007\"\u0001\u0002x\u0005\u0001R\u000f\u001d3bi\u0016\fE\u000f^1dQ6,g\u000e^\u000b\u0005\u0003s\ny\t\u0006\u0004\u0002|\u0005}\u0014\u0011\u0015\u000b\u0005\u0003{\n\tID\u0002M\u0003\u007fBqa`A:\u0001\u0004\t\t\u0001\u0003\u0006\u0002\u0004\u0006M\u0014\u0011!a\u0002\u0003\u000b\u000b!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019\t9)!#\u0002\u000e6\tA!C\u0002\u0002\f\u0012\u0011\u0001b\u00117bgN$\u0016m\u001a\t\u0004\u0019\u0006=E\u0001CAI\u0003g\u0012\r!a%\u0003\u0003Q\u000bB!!&\u0002\u001cB\u00191\"a&\n\u0007\u0005eeAA\u0004O_RD\u0017N\\4\u0011\u0007-\ti*C\u0002\u0002 \u001a\u00111!\u00118z\u0011!\t\u0019+a\u001dA\u0002\u00055\u0015AC1ui\u0006\u001c\u0007.\\3oi\"9\u0011qU\u001a\u0005\u0002\u0005%\u0016\u0001\u0005:f[>4X-\u0011;uC\u000eDW.\u001a8u+\u0011\tY+a/\u0015\t\u00055\u0016\u0011\u0017\u000b\u0005\u0003_\u000b\u0019LD\u0002M\u0003cCqa`AS\u0001\u0004\t\t\u0001\u0003\u0006\u00026\u0006\u0015\u0016\u0011!a\u0002\u0003o\u000b!\"\u001a<jI\u0016t7-\u001a\u00133!\u0019\t9)!#\u0002:B\u0019A*a/\u0005\u0011\u0005E\u0015Q\u0015b\u0001\u0003'Cq!a04\t\u0003\t\t-\u0001\u0004tKR\u0004vn\u001d\u000b\u0007\u0003\u0007\f)-a2\u000f\u00071\u000b)\rC\u0004\u0000\u0003{\u0003\r!!\u0001\t\u0011\u0005%\u0017Q\u0018a\u0001\u0003O\naA\\3xa>\u001c\bbBAgg\u0011\u0005\u0011qZ\u0001\bg\u0016$H+\u001f9f)\u0019\t\t.a5\u0002V:\u0019A*a5\t\u000f}\fY\r1\u0001\u0002\u0002!A\u0011q[Af\u0001\u0004\tI$\u0001\u0002ua\"9\u00111\\\u001a\u0005\u0002\u0005u\u0017A\u00033fM&tW\rV=qKR1\u0011q\\Aq\u0003Gt1\u0001TAq\u0011\u001dy\u0018\u0011\u001ca\u0001\u0003\u0003A\u0001\"a6\u0002Z\u0002\u0007\u0011\u0011\b\u0005\b\u0003O\u001cD\u0011AAu\u0003%\u0019X\r^*z[\n|G\u000e\u0006\u0004\u0002l\u00065\u0018q\u001e\b\u0004\u0019\u00065\bbB@\u0002f\u0002\u0007\u0011\u0011\u0001\u0005\u0007U\u0006\u0015\b\u0019\u00010\t\u000f\u0005M8\u0007\"\u0001\u0002v\u0006Y1/\u001a;Pe&<\u0017N\\1m)\u0019\t90!@\u0003\u0002A\u0019A$!?\n\t\u0005m\u0018q\u0001\u0002\t)f\u0004X\r\u0016:fK\"A\u0011q`Ay\u0001\u0004\t90\u0001\u0002ui\"9q0!=A\u0002\u0005\u0005\u0001b\u0002B\u0003g\u0011\u0005!qA\u0001\u0010G\u0006\u0004H/\u001e:f-\u0006\u0014\u0018.\u00192mKR\u0019aC!\u0003\t\u000f\t-!1\u0001a\u0001=\u0006!aO\u00197f\u0011\u001d\u0011ya\rC\u0001\u0005#\t\u0011D]3gKJ,gnY3DCB$XO]3e-\u0006\u0014\u0018.\u00192mKR!\u0011\u0011\u0001B\n\u0011\u001d\u0011YA!\u0004A\u0002yCqAa\u00064\t\u0003\u0011I\"\u0001\u000bdCB$XO]3e-\u0006\u0014\u0018.\u00192mKRK\b/\u001a\u000b\u0005\u0003s\u0011Y\u0002C\u0004\u0003\f\tU\u0001\u0019\u00010\t\u000f\t}1\u0007\"\u0001\u0003\"\u0005A1\r\\1tg\u0012+g\r\u0006\u0004\u0003$\t%\"1\u0006\t\u00049\t\u0015\u0012\u0002\u0002B\u0014\u0003\u000f\u0011\u0001b\u00117bgN$UM\u001a\u0005\u0007U\nu\u0001\u0019\u00010\t\u0011\t5\"Q\u0004a\u0001\u0005_\tA![7qYB\u0019AD!\r\n\t\tM\u0012q\u0001\u0002\t)\u0016l\u0007\u000f\\1uK\"9!qG\u001a\u0005\u0002\te\u0012!C7pIVdW\rR3g)\u0019\u0011YD!\u0011\u0003DA\u0019AD!\u0010\n\t\t}\u0012q\u0001\u0002\n\u001b>$W\u000f\\3EK\u001aDaA\u001bB\u001b\u0001\u0004q\u0006\u0002\u0003B\u0017\u0005k\u0001\rAa\f\t\u000f\t\u001d3\u0007\"\u0001\u0003J\u00051a/\u00197EK\u001a$bAa\u0013\u0003R\tM\u0003c\u0001\u000f\u0003N%!!qJA\u0004\u0005\u00191\u0016\r\u001c#fM\"1!N!\u0012A\u0002yC\u0001B!\u0016\u0003F\u0001\u0007\u0011\u0011A\u0001\u0004e\"\u001c\bb\u0002B$g\u0011\u0005!\u0011\f\u000b\u0005\u0005\u0017\u0012Y\u0006\u0003\u0004k\u0005/\u0002\rA\u0018\u0005\b\u0005?\u001aD\u0011\u0001B1\u0003\u0019!WM\u001a#fMRQ!1\rB5\u0005W\u0012)H! \u0011\u0007q\u0011)'\u0003\u0003\u0003h\u0005\u001d!A\u0002#fM\u0012+g\r\u0003\u0004k\u0005;\u0002\rA\u0018\u0005\t\u0005[\u0012i\u00061\u0001\u0003p\u0005!Qn\u001c3t!\ra\"\u0011O\u0005\u0005\u0005g\n9AA\u0005N_\u0012Lg-[3sg\"A!q\u000fB/\u0001\u0004\u0011I(\u0001\u0005wa\u0006\u0014\u0018-\\:t!\u0011)\bPa\u001f\u0011\tUD(1\n\u0005\t\u0005+\u0012i\u00061\u0001\u0002\u0002!9!qL\u001a\u0005\u0002\t\u0005E\u0003\u0003B2\u0005\u0007\u0013)Ia\"\t\r)\u0014y\b1\u0001_\u0011!\u00119Ha A\u0002\te\u0004\u0002\u0003B+\u0005\u007f\u0002\r!!\u0001\t\u000f\t}3\u0007\"\u0001\u0003\fRA!1\rBG\u0005\u001f\u0013\t\n\u0003\u0004k\u0005\u0013\u0003\rA\u0018\u0005\t\u0005[\u0012I\t1\u0001\u0003p!A!Q\u000bBE\u0001\u0004\t\t\u0001C\u0004\u0003`M\"\tA!&\u0015\r\t\r$q\u0013BM\u0011\u0019Q'1\u0013a\u0001=\"A!Q\u000bBJ\u0001\u0004\t\t\u0001C\u0004\u0003`M\"\tA!(\u0015\r\t\r$q\u0014BQ\u0011\u0019Q'1\u0014a\u0001=\"A!Q\u000bBN\u0001\u0004\u0011\u0019\u000bE\u0004\f\u0005K\u0013I+!\u0001\n\u0007\t\u001dfAA\u0005Gk:\u001cG/[8ocA!Q\u000f_A\u0013\u0011\u001d\u0011ik\rC\u0001\u0005_\u000bq\u0001^=qK\u0012+g\r\u0006\u0004\u00032\n]&\u0011\u0018\t\u00049\tM\u0016\u0002\u0002B[\u0003\u000f\u0011q\u0001V=qK\u0012+g\r\u0003\u0004k\u0005W\u0003\rA\u0018\u0005\t\u0005+\u0012Y\u000b1\u0001\u0002\u0002!9!QV\u001a\u0005\u0002\tuF\u0003\u0002BY\u0005\u007fCaA\u001bB^\u0001\u0004q\u0006b\u0002Bbg\u0011\u0005!QY\u0001\tY\u0006\u0014W\r\u001c#fMRA!q\u0019Bg\u0005\u001f\u0014\u0019\u000eE\u0002\u001d\u0005\u0013LAAa3\u0002\b\tAA*\u00192fY\u0012+g\r\u0003\u0004k\u0005\u0003\u0004\rA\u0018\u0005\t\u0005#\u0014\t\r1\u0001\u0002&\u00051\u0001/\u0019:b[ND\u0001B!\u0016\u0003B\u0002\u0007\u0011\u0011\u0001\u0005\b\u0005/\u001cD\u0011\u0001Bm\u0003-\u0019\u0007.\u00198hK>;h.\u001a:\u0015\u0011\tm'Q\u001cBp\u0005Gt1\u0001\u0014Bo\u0011\u001dy(Q\u001ba\u0001\u0003\u0003AqA!9\u0003V\u0002\u0007a,\u0001\u0003qe\u00164\bb\u0002Bs\u0005+\u0004\rAX\u0001\u0005]\u0016DH\u000f\u0003\u0006\u0003jNB)\u0019!C\u0001\u0005W\f1aZ3o+\t\u0011iOE\u0003\u0003p*\u0011)P\u0002\u0004E\u0005c\u0004!Q\u001e\u0005\u000b\u0005g\u0004\u0001\u0012!Q!\n\t5\u0018A\u0003;sK\u0016\u0014U/\u001b7eAA\u0019ADa>\n\u0007\texDA\u0004Ue\u0016,w)\u001a8\t\u0015\tu8\u0007#A!B\u0013\u0011i/\u0001\u0003hK:\u0004\u0003bBB\u0001g\u0011\u000511A\u0001\u000bSN4%/Z3UKJlG\u0003BB\u0003\u0007\u0017\u00012aCB\u0004\u0013\r\u0019IA\u0002\u0002\b\u0005>|G.Z1o\u0011\u001d\u0019iAa@A\u0002y\u000baa]=nE>d\u0007bBB\tg\u0011\u000511C\u0001\u000bCN4%/Z3UKJlGcA>\u0004\u0016!91QBB\b\u0001\u0004q\u0006bBB\rg\u0011\u000511D\u0001\u000bSN4%/Z3UsB,G\u0003BB\u0003\u0007;Aqa!\u0004\u0004\u0018\u0001\u0007a\fC\u0004\u0004\"M\"\taa\t\u0002\u0015\u0005\u001chI]3f)f\u0004X\r\u0006\u0003\u0002\u0014\r\u0015\u0002bBB\u0007\u0007?\u0001\rA\u0018\u0005\b\u0007S\u0019D\u0011AB\u0016\u00035qWm\u001e+fe6\u001c\u00160\u001c2pYRQ1QFB\u001a\u0007k\u0019\u0019ea\u0012\u0011\u0007q\u0019y#C\u0002\u00042\u0005\u0014!\u0002V3s[NKXNY8m\u0011\u001d\u0019iaa\nA\u0002yC\u0001ba\u000e\u0004(\u0001\u00071\u0011H\u0001\u0005]\u0006lW\rE\u0002\u001d\u0007wIAa!\u0010\u0004@\tAA+\u001a:n\u001d\u0006lW-C\u0002\u0004B\t\u0011QAT1nKND!b!\u0012\u0004(A\u0005\t\u0019AA4\u0003\r\u0001xn\u001d\u0005\u000b\u0007\u0013\u001a9\u0003%AA\u0002\r-\u0013!\u00024mC\u001e\u001c\bc\u0001\u000f\u0004N%!1qJB)\u0005\u001d1E.Y4TKRL1aa\u0015\u0003\u0005!1E.Y4TKR\u001c\bbBB,g\u0011\u00051\u0011L\u0001\u0018]\u0016<Xj\u001c3vY\u0016\fe\u000eZ\"mCN\u001c8+_7c_2$\"ba\u0017\u0004n\r=4qOB=!\u001dY1QLB1\u0007OJ1aa\u0018\u0007\u0005\u0019!V\u000f\u001d7feA\u0019Ada\u0019\n\u0007\r\u0015\u0014M\u0001\u0007N_\u0012,H.Z*z[\n|G\u000eE\u0002\u001d\u0007SJ1aa\u001bb\u0005-\u0019E.Y:t'fl'm\u001c7\t\u000f\r51Q\u000ba\u0001=\"A1qGB+\u0001\u0004\u0019\t\bE\u0002\u001d\u0007gJAa!\u001e\u0004@\t!a*Y7f\u0011)\u0019)e!\u0016\u0011\u0002\u0003\u0007\u0011q\r\u0005\u000b\u0007\u0013\u001a)\u0006%AA\u0002\r-\u0003bBB?g\u0011\u00051qP\u0001\u0010]\u0016<X*\u001a;i_\u0012\u001c\u00160\u001c2pYRQ1\u0011QBD\u0007\u0013\u001bYi!$\u0011\u0007q\u0019\u0019)C\u0002\u0004\u0006\u0006\u0014A\"T3uQ>$7+_7c_2Dqa!\u0004\u0004|\u0001\u0007a\f\u0003\u0005\u00048\rm\u0004\u0019AB\u001d\u0011)\u0019)ea\u001f\u0011\u0002\u0003\u0007\u0011q\r\u0005\u000b\u0007\u0013\u001aY\b%AA\u0002\r-\u0003bBBIg\u0011\u000511S\u0001\u000e]\u0016<H+\u001f9f'fl'm\u001c7\u0015\u0015\rU51TBO\u0007K\u001b9\u000bE\u0002\u001d\u0007/K1a!'b\u0005)!\u0016\u0010]3Ts6\u0014w\u000e\u001c\u0005\b\u0007\u001b\u0019y\t1\u0001_\u0011!\u00199da$A\u0002\r}\u0005c\u0001\u000f\u0004\"&!11UB \u0005!!\u0016\u0010]3OC6,\u0007BCB#\u0007\u001f\u0003\n\u00111\u0001\u0002h!Q1\u0011JBH!\u0003\u0005\raa\u0013\t\u000f\r-6\u0007\"\u0001\u0004.\u0006qa.Z<DY\u0006\u001c8oU=nE>dGCCB4\u0007_\u001b\tla-\u00046\"91QBBU\u0001\u0004q\u0006\u0002CB\u001c\u0007S\u0003\raa(\t\u0015\r\u00153\u0011\u0016I\u0001\u0002\u0004\t9\u0007\u0003\u0006\u0004J\r%\u0006\u0013!a\u0001\u0007\u0017Bqa!/4\t\u0003\u0019Y,A\u0006oK^4%/Z3UKJlG#C>\u0004>\u000e57q[Bm\u0011!\u00199da.A\u0002\r}\u0006\u0003BBa\u0007\u000ft1aCBb\u0013\r\u0019)MB\u0001\u0007!J,G-\u001a4\n\t\r%71\u001a\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\r\u0015g\u0001C\u0005\u0004P\u000e]F\u00111\u0001\u0004R\u0006)a/\u00197vKB)1ba5\u0002\u001c&\u00191Q\u001b\u0004\u0003\u0011q\u0012\u0017P\\1nKzB!b!\u0013\u00048B\u0005\t\u0019AB&\u0011)\u0019Yna.\u0011\u0002\u0003\u00071qX\u0001\u0007_JLw-\u001b8\t\u000f\r}7\u0007\"\u0001\u0004b\u0006Ya.Z<Ge\u0016,G+\u001f9f)!\t\u0019ba9\u0004f\u000e\u001d\b\u0002CB\u001c\u0007;\u0004\raa0\t\u0015\r%3Q\u001cI\u0001\u0002\u0004\u0019Y\u0005\u0003\u0006\u0004\\\u000eu\u0007\u0013!a\u0001\u0007\u007fCqaa;4\t\u0003\u0019i/A\u0006jg\u0016\u0013(o\u001c8f_V\u001cH\u0003BB\u0003\u0007_Dqa!\u0004\u0004j\u0002\u0007a\fC\u0004\u0004tN\"\ta!>\u0002\u0011%\u001c8k[8mK6$Ba!\u0002\u0004x\"91QBBy\u0001\u0004q\u0006bBB~g\u0011\u00051Q`\u0001\fI\u0016\u001c6n\u001c7f[&TX\rF\u0002_\u0007\u007fDqa!\u0004\u0004z\u0002\u0007a\fC\u0004\u0005\u0004M\"\t\u0001\"\u0002\u0002\u0015%t\u0017\u000e^5bY&TX\r\u0006\u0003\u0005\b\u0011%ab\u0001'\u0005\n!91Q\u0002C\u0001\u0001\u0004q\u0006b\u0002C\u0007g\u0011\u0005AqB\u0001\u0010MVdG._%oSRL\u0017\r\\5{KR!A\u0011\u0003C\n\u001d\raE1\u0003\u0005\b\u0007\u001b!Y\u00011\u0001_\u0011\u001d!ia\rC\u0001\t/!B\u0001\"\u0007\u0005\u001c9\u0019A\nb\u0007\t\u0011\u0005]GQ\u0003a\u0001\u0003sAq\u0001\"\u00044\t\u0003!y\u0002\u0006\u0003\u0005\"\u0011\rbb\u0001'\u0005$!1\u0001\u000e\"\bA\u0002QCqa!\u00134\t\u0003!9\u0003\u0006\u0003\u0004L\u0011%\u0002bBB\u0007\tK\u0001\rA\u0018\u0005\b\u0003'\u001aD\u0011\u0001C\u0017)\u0011!y\u0003\"\u000e\u0013\t\u0011E\u00121\f\u0004\u0006\tN\u0002AqF\u0003\b\u0003K\"\t\u0004IA4\u0011\u001d\u0019i\u0001b\u000bA\u0002yCq!!\u001e4\t\u0003!I$\u0006\u0003\u0005<\u0011-CC\u0002C\u001f\t\u0003\"i\u0005\u0006\u0003\u0005@\u0011\rcb\u0001'\u0005B!91Q\u0002C\u001c\u0001\u0004q\u0006B\u0003C#\to\t\t\u0011q\u0001\u0005H\u0005QQM^5eK:\u001cW\rJ\u001a\u0011\r\u0005\u001d\u0015\u0011\u0012C%!\raE1\n\u0003\t\u0003##9D1\u0001\u0002\u0014\"A\u00111\u0015C\u001c\u0001\u0004!I\u0005C\u0004\u0002(N\"\t\u0001\"\u0015\u0016\t\u0011MC1\r\u000b\u0005\t+\"I\u0006\u0006\u0003\u0005X\u0011mcb\u0001'\u0005Z!91Q\u0002C(\u0001\u0004q\u0006B\u0003C/\t\u001f\n\t\u0011q\u0001\u0005`\u0005QQM^5eK:\u001cW\r\n\u001b\u0011\r\u0005\u001d\u0015\u0011\u0012C1!\raE1\r\u0003\t\u0003##yE1\u0001\u0002\u0014\"9AqM\u001a\u0005\u0002\u0011%\u0014\u0001C:fi>;h.\u001a:\u0015\r\u0011-DQ\u000eC8\u001d\raEQ\u000e\u0005\b\u0007\u001b!)\u00071\u0001_\u0011\u001d!\t\b\"\u001aA\u0002y\u000b\u0001B\\3x_^tWM\u001d\u0005\b\tk\u001aD\u0011\u0001C<\u0003\u001d\u0019X\r^%oM>$b\u0001\"\u001f\u0005|\u0011udb\u0001'\u0005|!91Q\u0002C:\u0001\u0004q\u0006\u0002\u0003C@\tg\u0002\r!!\u000f\u0002\u0007Q\u0004X\rC\u0004\u0005\u0004N\"\t\u0001\"\"\u0002\u001dM,G/\u00118o_R\fG/[8ogR1Aq\u0011CE\t\u0017s1\u0001\u0014CE\u0011\u001d\u0019i\u0001\"!A\u0002yC\u0001\u0002\"$\u0005\u0002\u0002\u0007AqR\u0001\u0007C:tw\u000e^:\u0011\t-aF\u0011\u0013\t\u00049\u0011M\u0015\u0002\u0002CK\t/\u0013!\"\u00118o_R\fG/[8o\u0013\r!IJ\u0001\u0002\u0010\u0003:tw\u000e^1uS>t\u0017J\u001c4pg\"9AQT\u001a\u0005\u0002\u0011}\u0015aB:fi:\u000bW.\u001a\u000b\u0007\tC#\u0019\u000b\"*\u000f\u00071#\u0019\u000bC\u0004\u0004\u000e\u0011m\u0005\u0019\u00010\t\u0011\r]B1\u0014a\u0001\u0007cBq\u0001\"+4\t\u0003!Y+\u0001\ttKR\u0004&/\u001b<bi\u0016<\u0016\u000e\u001e5j]R1AQ\u0016CX\tcs1\u0001\u0014CX\u0011\u001d\u0019i\u0001b*A\u0002yCaA\u001bCT\u0001\u0004q\u0006b\u0002C[g\u0011\u0005AqW\u0001\bg\u0016$h\t\\1h)\u0019!I\fb/\u0005>:\u0019A\nb/\t\u000f\r5A1\u0017a\u0001=\"A1\u0011\nCZ\u0001\u0004\u0019Y\u0005C\u0004\u0005BN\"\t\u0001b1\u0002\u0013I,7/\u001a;GY\u0006<GC\u0002Cc\t\u000f$IMD\u0002M\t\u000fDqa!\u0004\u0005@\u0002\u0007a\f\u0003\u0005\u0004J\u0011}\u0006\u0019AB&\u0011\u001d!im\rC\u0001\t\u001f\f\u0001\u0002\u001e5jgRK\b/\u001a\u000b\u0005\u0003s!\t\u000e\u0003\u0004k\t\u0017\u0004\rA\u0018\u0005\b\t+\u001cD\u0011\u0001Cl\u0003)\u0019\u0018N\\4mKRK\b/\u001a\u000b\u0007\u0003s!I\u000e\"8\t\u0011\u0011mG1\u001ba\u0001\u0003s\t1\u0001\u001d:f\u0011\u0019QG1\u001ba\u0001=\"9A\u0011]\u001a\u0005\u0002\u0011\r\u0018!C:va\u0016\u0014H+\u001f9f)\u0019\tI\u0004\":\u0005j\"AAq\u001dCp\u0001\u0004\tI$A\u0004uQ&\u001cH\u000f]3\t\u0011\u0011-Hq\u001ca\u0001\u0003s\t\u0001b];qKJ$\b/\u001a\u0005\b\t_\u001cD\u0011\u0001Cy\u00031\u0019wN\\:uC:$H+\u001f9f)\u0011!\u0019\u0010\"?\u0011\u0007q!)0\u0003\u0003\u0005x\u0006}\"\u0001D\"p]N$\u0018M\u001c;UsB,\u0007\u0002CBh\t[\u0004\r\u0001b?\u0011\u0007q!i0\u0003\u0003\u0005\u0000\u0016\u0005!\u0001C\"p]N$\u0018M\u001c;\n\u0007\u0015\r!AA\u0005D_:\u001cH/\u00198ug\"9QqA\u001a\u0005\u0002\u0015%\u0011a\u0002;za\u0016\u0014VM\u001a\u000b\t\u0003s)Y!\"\u0004\u0006\u0010!AA1\\C\u0003\u0001\u0004\tI\u0004\u0003\u0004k\u000b\u000b\u0001\rA\u0018\u0005\t\u000b#))\u00011\u0001\u00028\u0005!\u0011M]4t\u0011\u001d))b\rC\u0001\u000b/\t1B]3gS:,G\rV=qKR1Q\u0011DC\u0010\u000bG\u00012\u0001HC\u000e\u0013\u0011)i\"a\u0010\u0003\u0017I+g-\u001b8fIRK\b/\u001a\u0005\t\u000bC)\u0019\u00021\u0001\u00028\u00059\u0001/\u0019:f]R\u001c\bbBC\u0013\u000b'\u0001\r\u0001V\u0001\u0006I\u0016\u001cGn\u001d\u0005\b\u000b+\u0019D\u0011AC\u0015)!)I\"b\u000b\u0006.\u0015=\u0002\u0002CC\u0011\u000bO\u0001\r!a\u000e\t\u000f\u0015\u0015Rq\u0005a\u0001)\"9\u0011QJC\u0014\u0001\u0004q\u0006bBC\u000bg\u0011\u0005Q1\u0007\u000b\u0007\u0003s))$b\u000e\t\u0011\u0015\u0005R\u0011\u0007a\u0001\u0003oAq!\"\u000f\u00062\u0001\u0007a,A\u0003po:,'\u000fC\u0004\u0006\u0016M\"\t!\"\u0010\u0015\u0011\u0005eRqHC!\u000b\u0007B\u0001\"\"\t\u0006<\u0001\u0007\u0011q\u0007\u0005\b\u000bs)Y\u00041\u0001_\u0011\u001d))#b\u000fA\u0002QCq!\"\u00064\t\u0003)9\u0005\u0006\u0006\u0002:\u0015%S1JC'\u000b\u001fB\u0001\"\"\t\u0006F\u0001\u0007\u0011q\u0007\u0005\b\u000bs))\u00051\u0001_\u0011\u001d))#\"\u0012A\u0002QC\u0001b!\u0012\u0006F\u0001\u0007\u0011q\r\u0005\b\u000b'\u001aD\u0011AC+\u0003AIg\u000e^3sg\u0016\u001cG/[8o)f\u0004X\r\u0006\u0003\u0002:\u0015]\u0003\u0002CC-\u000b#\u0002\r!a\u000e\u0002\u0007Q\u00048\u000fC\u0004\u0006TM\"\t!\"\u0018\u0015\r\u0005eRqLC1\u0011!)I&b\u0017A\u0002\u0005]\u0002bBC\u001d\u000b7\u0002\rA\u0018\u0005\b\u000bK\u001aD\u0011AC4\u00035\u0019G.Y:t\u0013:4w\u000eV=qKRAQ\u0011NC8\u000bc*\u0019\bE\u0002\u001d\u000bWJA!\"\u001c\u0002@\ti1\t\\1tg&sgm\u001c+za\u0016D\u0001\"\"\t\u0006d\u0001\u0007\u0011q\u0007\u0005\b\u000bK)\u0019\u00071\u0001U\u0011\u001d))(b\u0019A\u0002y\u000b!\u0002^=qKNKXNY8m\u0011\u001d)Ih\rC\u0001\u000bw\n!\"\\3uQ>$G+\u001f9f)\u0019)i(b!\u0006\u0006B\u0019A$b \n\t\u0015\u0005\u0015q\b\u0002\u000b\u001b\u0016$\bn\u001c3UsB,\u0007\u0002\u0003Bi\u000bo\u0002\r!!\n\t\u0011\u0015\u001dUq\u000fa\u0001\u0003s\t!B]3tk2$H+\u001f9f\u0011\u001d)Yi\rC\u0001\u000b\u001b\u000b\u0011C\\;mY\u0006\u0014\u00180T3uQ>$G+\u001f9f)\u0011)y)\"&\u0011\u0007q)\t*\u0003\u0003\u0006\u0014\u0006}\"!\u0005(vY2\f'/_'fi\"|G\rV=qK\"AQqQCE\u0001\u0004\tI\u0004C\u0004\u0006\u001aN\"\t!b'\u0002\u0011A|G.\u001f+za\u0016$b!\"(\u0006$\u0016\u001d\u0006c\u0001\u000f\u0006 &!Q\u0011UA \u0005!\u0001v\u000e\\=UsB,\u0007\u0002CCS\u000b/\u0003\r!!\n\u0002\u0015QL\b/\u001a)be\u0006l7\u000f\u0003\u0005\u0006\b\u0016]\u0005\u0019AA\u001d\u0011\u001d)Yk\rC\u0001\u000b[\u000bq\"\u001a=jgR,g\u000e^5bYRK\b/\u001a\u000b\u0007\u000b_+),\"/\u0011\u0007q)\t,\u0003\u0003\u00064\u0006}\"aD#ySN$XM\u001c;jC2$\u0016\u0010]3\t\u0011\u0015]V\u0011\u0016a\u0001\u0003K\t!\"];b]RLg-[3e\u0011!)Y,\"+A\u0002\u0005e\u0012AC;oI\u0016\u0014H._5oO\"9QqX\u001a\u0005\u0002\u0015\u0005\u0017AF3ySN$XM\u001c;jC2\f%m\u001d;sC\u000e$\u0018n\u001c8\u0015\r\u0005eR1YCd\u0011!))-\"0A\u0002\u0005\u0015\u0012a\u0002;qCJ\fWn\u001d\u0005\t\u000b\u0013,i\f1\u0001\u0002:\u0005!A\u000f]31\u0011\u001d)im\rC\u0001\u000b\u001f\fQ\"\u00198o_R\fG/\u001a3UsB,GCBCi\u000b/,i\u000eE\u0002\u001d\u000b'LA!\"6\u0002@\ti\u0011I\u001c8pi\u0006$X\r\u001a+za\u0016D\u0001\"\"7\u0006L\u0002\u0007Q1\\\u0001\fC:tw\u000e^1uS>t7\u000f\u0005\u0003vq\u0012E\u0005\u0002CC^\u000b\u0017\u0004\r!!\u000f\t\u000f\u0015\u00058\u0007\"\u0001\u0006d\u0006QA/\u001f9f\u0005>,h\u000eZ:\u0015\r\u0015\u0015X1^Cx!\raRq]\u0005\u0005\u000bS\fyD\u0001\u0006UsB,'i\\;oIND\u0001\"\"<\u0006`\u0002\u0007\u0011\u0011H\u0001\u0003Y>D\u0001\"\"=\u0006`\u0002\u0007\u0011\u0011H\u0001\u0003Q&Dq!\">4\t\u0003)90A\nc_VtG-\u001a3XS2$7-\u0019:e)f\u0004X\r\u0006\u0003\u0006z\u0016}\bc\u0001\u000f\u0006|&!QQ`A \u0005M\u0011u.\u001e8eK\u0012<\u0016\u000e\u001c3dCJ$G+\u001f9f\u0011!1\t!b=A\u0002\u0015\u0015\u0018A\u00022pk:$7\u000fC\u0004\u0007\u0006M\"\tAb\u0002\u0002\u0017M,(\r]1ui\u0016\u0014hn\u001d\u000b\u0005\r\u00131\t\u0002E\u0003\f\r\u00171y!C\u0002\u0007\u000e\u0019\u0011aa\u00149uS>t\u0007\u0003B;y\u0003\u0003Aqa D\u0002\u0001\u0004\t\t!\u0002\u0004\u0007\u0016M\u0002aq\u0003\u0002\u000b\t\u0016\u001cwN]1u_J\u001c\b\u0003\u0002D\r\r7i\u0011aM\u0005\u0004\r;i\"!E'bGJ|G)Z2pe\u0006$xN]!qS\"Qa\u0011E\u001a\t\u0006\u0004%\tAb\t\u0002\u0015\u0011,7m\u001c:bi>\u00148/\u0006\u0002\u0007&A!a\u0011\u0004D\n\u0011)1Ic\rE\u0001B\u0003&aQE\u0001\fI\u0016\u001cwN]1u_J\u001c\b\u0005C\u0005\u0007.M\n\n\u0011\"\u0011\u00070\u00059b.Z<UKJl7+_7c_2$C-\u001a4bk2$HeM\u000b\u0003\rcQC!a\u001a\u00074-\u0012aQ\u0007\t\u0005\ro1\t%\u0004\u0002\u0007:)!a1\bD\u001f\u0003%)hn\u00195fG.,GMC\u0002\u0007@\u0019\t!\"\u00198o_R\fG/[8o\u0013\u00111\u0019E\"\u000f\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rC\u0005\u0007HM\n\n\u0011\"\u0011\u0007J\u00059b.Z<UKJl7+_7c_2$C-\u001a4bk2$H\u0005N\u000b\u0003\r\u0017RCaa\u0013\u00074!IaqJ\u001a\u0012\u0002\u0013\u0005cqF\u0001\"]\u0016<Xj\u001c3vY\u0016\fe\u000eZ\"mCN\u001c8+_7c_2$C-\u001a4bk2$He\r\u0005\n\r'\u001a\u0014\u0013!C!\r\u0013\n\u0011E\\3x\u001b>$W\u000f\\3B]\u0012\u001cE.Y:t'fl'm\u001c7%I\u00164\u0017-\u001e7uIQB\u0011Bb\u00164#\u0003%\tEb\f\u000239,w/T3uQ>$7+_7c_2$C-\u001a4bk2$He\r\u0005\n\r7\u001a\u0014\u0013!C!\r\u0013\n\u0011D\\3x\u001b\u0016$\bn\u001c3Ts6\u0014w\u000e\u001c\u0013eK\u001a\fW\u000f\u001c;%i!IaqL\u001a\u0012\u0002\u0013\u0005cqF\u0001\u0018]\u0016<H+\u001f9f'fl'm\u001c7%I\u00164\u0017-\u001e7uIMB\u0011Bb\u00194#\u0003%\tE\"\u0013\u0002/9,w\u000fV=qKNKXNY8mI\u0011,g-Y;mi\u0012\"\u0004\"\u0003D4gE\u0005I\u0011\tD\u0018\u0003aqWm^\"mCN\u001c8+_7c_2$C-\u001a4bk2$He\r\u0005\n\rW\u001a\u0014\u0013!C!\r\u0013\n\u0001D\\3x\u00072\f7o]*z[\n|G\u000e\n3fM\u0006,H\u000e\u001e\u00135\u0011%1ygMI\u0001\n\u00032I%A\u000boK^4%/Z3UKJlG\u0005Z3gCVdG\u000fJ\u001a\t\u0013\u0019M4'%A\u0005B\u0019U\u0014!\u00068fo\u001a\u0013X-\u001a+fe6$C-\u001a4bk2$H\u0005N\u000b\u0003\roRCaa0\u00074!Ia1P\u001a\u0012\u0002\u0013\u0005c\u0011J\u0001\u0016]\u0016<hI]3f)f\u0004X\r\n3fM\u0006,H\u000e\u001e\u00133\u0011%1yhMI\u0001\n\u00032)(A\u000boK^4%/Z3UsB,G\u0005Z3gCVdG\u000fJ\u001a\t\u0015\u0019\r\u0005\u0001#b\u0001\n\u0003\u0011Y/A\u0005ue\u0016,')^5mIB!aq\u0011DE\u001b\u0005\u0011\u0011b\u0001DF\u0005\tY1+_7c_2$\u0016M\u00197f\u0001")
public interface Internals
extends scala.reflect.api.Internals {
    @Override
    public Universe.MacroInternalApi internal();

    @Override
    public Universe.MacroCompatApi compat();

    public Universe.TreeGen treeBuild();

    public interface SymbolTableInternal
    extends Universe.MacroInternalApi {
        @Override
        public Internals.ReificationSupportApi reificationSupport();

        @Override
        public Internals.Importer createImporter(Universe var1);

        public Scopes.Scope newScopeWith(Seq<Symbols.Symbol> var1);

        public Scopes.Scope enter(Scopes.Scope var1, Symbols.Symbol var2);

        public Scopes.Scope unlink(Scopes.Scope var1, Symbols.Symbol var2);

        public List<Symbols.FreeTermSymbol> freeTerms(Trees.Tree var1);

        public List<Symbols.FreeTypeSymbol> freeTypes(Trees.Tree var1);

        public Trees.Tree substituteSymbols(Trees.Tree var1, List<Symbols.Symbol> var2, List<Symbols.Symbol> var3);

        public Trees.Tree substituteTypes(Trees.Tree var1, List<Symbols.Symbol> var2, List<Types.Type> var3);

        public Trees.Tree substituteThis(Trees.Tree var1, Symbols.Symbol var2, Trees.Tree var3);

        public Attachments attachments(Trees.Tree var1);

        public <T> Trees.Tree updateAttachment(Trees.Tree var1, T var2, ClassTag<T> var3);

        public <T> Trees.Tree removeAttachment(Trees.Tree var1, ClassTag<T> var2);

        public Trees.Tree setPos(Trees.Tree var1, Position var2);

        public Trees.Tree setType(Trees.Tree var1, Types.Type var2);

        public Trees.Tree defineType(Trees.Tree var1, Types.Type var2);

        public Trees.Tree setSymbol(Trees.Tree var1, Symbols.Symbol var2);

        public Trees.TypeTree setOriginal(Trees.TypeTree var1, Trees.Tree var2);

        public void captureVariable(Symbols.Symbol var1);

        public Trees.Tree referenceCapturedVariable(Symbols.Symbol var1);

        public Types.Type capturedVariableType(Symbols.Symbol var1);

        public Trees.ClassDef classDef(Symbols.Symbol var1, Trees.Template var2);

        public Trees.ModuleDef moduleDef(Symbols.Symbol var1, Trees.Template var2);

        public Trees.ValDef valDef(Symbols.Symbol var1, Trees.Tree var2);

        public Trees.ValDef valDef(Symbols.Symbol var1);

        public Trees.DefDef defDef(Symbols.Symbol var1, Trees.Modifiers var2, List<List<Trees.ValDef>> var3, Trees.Tree var4);

        public Trees.DefDef defDef(Symbols.Symbol var1, List<List<Trees.ValDef>> var2, Trees.Tree var3);

        public Trees.DefDef defDef(Symbols.Symbol var1, Trees.Modifiers var2, Trees.Tree var3);

        public Trees.DefDef defDef(Symbols.Symbol var1, Trees.Tree var2);

        public Trees.DefDef defDef(Symbols.Symbol var1, Function1<List<List<Symbols.Symbol>>, Trees.Tree> var2);

        public Trees.TypeDef typeDef(Symbols.Symbol var1, Trees.Tree var2);

        public Trees.TypeDef typeDef(Symbols.Symbol var1);

        public Trees.LabelDef labelDef(Symbols.Symbol var1, List<Symbols.Symbol> var2, Trees.Tree var3);

        public Trees.Tree changeOwner(Trees.Tree var1, Symbols.Symbol var2, Symbols.Symbol var3);

        @Override
        public Universe.TreeGen gen();

        public boolean isFreeTerm(Symbols.Symbol var1);

        public Symbols.FreeTermSymbol asFreeTerm(Symbols.Symbol var1);

        public boolean isFreeType(Symbols.Symbol var1);

        public Symbols.FreeTypeSymbol asFreeType(Symbols.Symbol var1);

        public Symbols.TermSymbol newTermSymbol(Symbols.Symbol var1, Names.TermName var2, Position var3, long var4);

        @Override
        public Position newTermSymbol$default$3();

        public long newTermSymbol$default$4();

        public Tuple2<Symbols.ModuleSymbol, Symbols.ClassSymbol> newModuleAndClassSymbol(Symbols.Symbol var1, Names.Name var2, Position var3, long var4);

        @Override
        public Position newModuleAndClassSymbol$default$3();

        public long newModuleAndClassSymbol$default$4();

        public Symbols.MethodSymbol newMethodSymbol(Symbols.Symbol var1, Names.TermName var2, Position var3, long var4);

        @Override
        public Position newMethodSymbol$default$3();

        public long newMethodSymbol$default$4();

        public Symbols.TypeSymbol newTypeSymbol(Symbols.Symbol var1, Names.TypeName var2, Position var3, long var4);

        @Override
        public Position newTypeSymbol$default$3();

        public long newTypeSymbol$default$4();

        public Symbols.ClassSymbol newClassSymbol(Symbols.Symbol var1, Names.TypeName var2, Position var3, long var4);

        @Override
        public Position newClassSymbol$default$3();

        public long newClassSymbol$default$4();

        public Symbols.FreeTermSymbol newFreeTerm(String var1, Function0<Object> var2, long var3, String var5);

        public long newFreeTerm$default$3();

        @Override
        public String newFreeTerm$default$4();

        public Symbols.FreeTypeSymbol newFreeType(String var1, long var2, String var4);

        public long newFreeType$default$2();

        @Override
        public String newFreeType$default$3();

        public boolean isErroneous(Symbols.Symbol var1);

        public boolean isSkolem(Symbols.Symbol var1);

        public Symbols.Symbol deSkolemize(Symbols.Symbol var1);

        public Symbols.Symbol initialize(Symbols.Symbol var1);

        public Symbols.Symbol fullyInitialize(Symbols.Symbol var1);

        public Types.Type fullyInitialize(Types.Type var1);

        public Scopes.Scope fullyInitialize(Scopes.Scope var1);

        public long flags(Symbols.Symbol var1);

        public Attachments attachments(Symbols.Symbol var1);

        public <T> Symbols.Symbol updateAttachment(Symbols.Symbol var1, T var2, ClassTag<T> var3);

        public <T> Symbols.Symbol removeAttachment(Symbols.Symbol var1, ClassTag<T> var2);

        public Symbols.Symbol setOwner(Symbols.Symbol var1, Symbols.Symbol var2);

        public Symbols.Symbol setInfo(Symbols.Symbol var1, Types.Type var2);

        public Symbols.Symbol setAnnotations(Symbols.Symbol var1, Seq<AnnotationInfos.AnnotationInfo> var2);

        public Symbols.Symbol setName(Symbols.Symbol var1, Names.Name var2);

        public Symbols.Symbol setPrivateWithin(Symbols.Symbol var1, Symbols.Symbol var2);

        public Symbols.Symbol setFlag(Symbols.Symbol var1, long var2);

        public Symbols.Symbol resetFlag(Symbols.Symbol var1, long var2);

        public Types.Type thisType(Symbols.Symbol var1);

        public Types.Type singleType(Types.Type var1, Symbols.Symbol var2);

        public Types.Type superType(Types.Type var1, Types.Type var2);

        public Types.ConstantType constantType(Constants.Constant var1);

        public Types.Type typeRef(Types.Type var1, Symbols.Symbol var2, List<Types.Type> var3);

        public Types.RefinedType refinedType(List<Types.Type> var1, Scopes.Scope var2);

        public Types.RefinedType refinedType(List<Types.Type> var1, Scopes.Scope var2, Symbols.Symbol var3);

        public Types.Type refinedType(List<Types.Type> var1, Symbols.Symbol var2);

        public Types.Type refinedType(List<Types.Type> var1, Symbols.Symbol var2, Scopes.Scope var3);

        public Types.Type refinedType(List<Types.Type> var1, Symbols.Symbol var2, Scopes.Scope var3, Position var4);

        public Types.Type intersectionType(List<Types.Type> var1);

        public Types.Type intersectionType(List<Types.Type> var1, Symbols.Symbol var2);

        public Types.ClassInfoType classInfoType(List<Types.Type> var1, Scopes.Scope var2, Symbols.Symbol var3);

        public Types.MethodType methodType(List<Symbols.Symbol> var1, Types.Type var2);

        public Types.NullaryMethodType nullaryMethodType(Types.Type var1);

        public Types.PolyType polyType(List<Symbols.Symbol> var1, Types.Type var2);

        public Types.ExistentialType existentialType(List<Symbols.Symbol> var1, Types.Type var2);

        public Types.Type existentialAbstraction(List<Symbols.Symbol> var1, Types.Type var2);

        public Types.AnnotatedType annotatedType(List<AnnotationInfos.AnnotationInfo> var1, Types.Type var2);

        public Types.TypeBounds typeBounds(Types.Type var1, Types.Type var2);

        public Types.BoundedWildcardType boundedWildcardType(Types.TypeBounds var1);

        public Option<List<Trees.Tree>> subpatterns(Trees.Tree var1);

        @Override
        public Universe.MacroInternalApi.MacroDecoratorApi decorators();

        public /* synthetic */ Internals scala$reflect$internal$Internals$SymbolTableInternal$$$outer();
    }
}

