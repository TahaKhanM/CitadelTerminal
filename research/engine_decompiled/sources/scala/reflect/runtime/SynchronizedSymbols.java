/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import java.util.concurrent.atomic.AtomicInteger;
import scala.Function0;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Names;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.Position;
import scala.reflect.runtime.ThreadLocalStorage;

@ScalaSignature(bytes="\u0006\u0001\r%fAC\u0001\u0003!\u0003\r\t\u0001\u0002\u0005\u0004$\n\u00192+\u001f8dQJ|g.\u001b>fINKXNY8mg*\u00111\u0001B\u0001\beVtG/[7f\u0015\t)a!A\u0004sK\u001adWm\u0019;\u000b\u0003\u001d\tQa]2bY\u0006\u001c2\u0001A\u0005\u000e!\tQ1\"D\u0001\u0007\u0013\taaA\u0001\u0004B]f\u0014VM\u001a\t\u0003\u001dEi\u0011a\u0004\u0006\u0003!\u0011\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003%=\u0011qaU=nE>d7\u000fC\u0003\u0015\u0001\u0011\u0005a#\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u00059\u0002C\u0001\u0006\u0019\u0013\tIbA\u0001\u0003V]&$\b\u0002C\u000e\u0001\u0011\u000b\u0007I\u0011\u0002\u000f\u0002\u0013\u0005$x.\\5d\u0013\u0012\u001cX#A\u000f\u0011\u0005y9S\"A\u0010\u000b\u0005\u0001\n\u0013AB1u_6L7M\u0003\u0002#G\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005\u0011*\u0013\u0001B;uS2T\u0011AJ\u0001\u0005U\u00064\u0018-\u0003\u0002)?\ti\u0011\t^8nS\u000eLe\u000e^3hKJD\u0001B\u000b\u0001\t\u0002\u0003\u0006K!H\u0001\u000bCR|W.[2JIN\u0004\u0003\"\u0002\u0017\u0001\t#j\u0013A\u00028fqRLE\rF\u0001/!\tQq&\u0003\u00021\r\t\u0019\u0011J\u001c;\t\u0011I\u0002\u0001R1A\u0005\nq\tA#\u0019;p[&\u001cW\t_5ti\u0016tG/[1m\u0013\u0012\u001c\b\u0002\u0003\u001b\u0001\u0011\u0003\u0005\u000b\u0015B\u000f\u0002+\u0005$x.\\5d\u000bbL7\u000f^3oi&\fG.\u00133tA!)a\u0007\u0001C)[\u0005\tb.\u001a=u\u000bbL7\u000f^3oi&\fG.\u00133\t\u0011a\u0002\u0001R1A\u0005\ne\nqb\u0018:fGV\u00148/[8o)\u0006\u0014G.Z\u000b\u0002uA\u00191\bP \u000e\u0003\u0001I!!\u0010 \u0003%QC'/Z1e\u0019>\u001c\u0017\r\\*u_J\fw-Z\u0005\u0003{\t\u0001B\u0001Q#H]5\t\u0011I\u0003\u0002C\u0007\u0006I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003\t\u001a\t!bY8mY\u0016\u001cG/[8o\u0013\t1\u0015IA\u0002NCB\u0004\"a\u000f%\n\u0005%\u000b\"AB*z[\n|G\u000e\u0003\u0005L\u0001!\u0005\t\u0015)\u0003;\u0003Ay&/Z2veNLwN\u001c+bE2,\u0007\u0005C\u0003N\u0001\u0011\u0005c*\u0001\bsK\u000e,(o]5p]R\u000b'\r\\3\u0016\u0003}BQ\u0001\u0015\u0001\u0005BE\u000b!C]3dkJ\u001c\u0018n\u001c8UC\ndWm\u0018\u0013fcR\u0011qC\u0015\u0005\u0006'>\u0003\raP\u0001\u0006m\u0006dW/\u001a\u0005\u0006+\u0002!\tEV\u0001\u0015G>tg.Z2u\u001b>$W\u000f\\3U_\u000ec\u0017m]:\u0015\u0007]SF\f\u0005\u0002<1&\u0011\u0011,\u0005\u0002\r\u001b>$W\u000f\\3Ts6\u0014w\u000e\u001c\u0005\u00067R\u0003\raV\u0001\u0002[\")Q\f\u0016a\u0001=\u0006YQn\u001c3vY\u0016\u001cE.Y:t!\tYt,\u0003\u0002a#\tY1\t\\1tgNKXNY8m\u0011\u0015\u0011\u0007\u0001\"\u0011d\u0003EqWm\u001e$sK\u0016$VM]7Ts6\u0014w\u000e\u001c\u000b\u0006I\u001etWO\u001f\t\u0003w\u0015L!AZ\t\u0003\u001d\u0019\u0013X-\u001a+fe6\u001c\u00160\u001c2pY\")\u0001.\u0019a\u0001S\u0006!a.Y7f!\tY$.\u0003\u0002lY\nAA+\u001a:n\u001d\u0006lW-\u0003\u0002n\u001f\t)a*Y7fg\"11+\u0019CA\u0002=\u00042A\u00039s\u0013\t\thA\u0001\u0005=Eft\u0017-\\3?!\tQ1/\u0003\u0002u\r\t\u0019\u0011I\\=\t\u000fY\f\u0007\u0013!a\u0001o\u0006)a\r\\1hgB\u0011!\u0002_\u0005\u0003s\u001a\u0011A\u0001T8oO\"910\u0019I\u0001\u0002\u0004a\u0018AB8sS\u001eLg\u000eE\u0002~\u0003\u0003q!A\u0003@\n\u0005}4\u0011A\u0002)sK\u0012,g-\u0003\u0003\u0002\u0004\u0005\u0015!AB*ue&twM\u0003\u0002\u0000\r!9\u0011\u0011\u0002\u0001\u0005B\u0005-\u0011!\u00058fo\u001a\u0013X-\u001a+za\u0016\u001c\u00160\u001c2pYRA\u0011QBA\n\u00037\ti\u0002E\u0002<\u0003\u001fI1!!\u0005\u0012\u000591%/Z3UsB,7+_7c_2Dq\u0001[A\u0004\u0001\u0004\t)\u0002E\u0002<\u0003/I1!!\u0007m\u0005!!\u0016\u0010]3OC6,\u0007\u0002\u0003<\u0002\bA\u0005\t\u0019A<\t\u0011m\f9\u0001%AA\u0002qDq!!\t\u0001\t#\n\u0019#\u0001\u0007nC.,gj\\*z[\n|G.\u0006\u0002\u0002&A\u00191(a\n\n\u0007\u0005%\u0012C\u0001\u0005O_NKXNY8m\r%\ti\u0003\u0001I\u0001\u0004\u0003\tyC\u0001\nTs:\u001c\u0007N]8oSj,GmU=nE>d7cAA\u0016\u000f\"1A#a\u000b\u0005\u0002YA\u0001\"!\u000e\u0002,\u0011\u0005\u0013qG\u0001\rSN$\u0006N]3bIN\fg-\u001a\u000b\u0005\u0003s\ty\u0004E\u0002\u000b\u0003wI1!!\u0010\u0007\u0005\u001d\u0011un\u001c7fC:D\u0001\"!\u0011\u00024\u0001\u0007\u00111I\u0001\baV\u0014\bo\\:f!\rY\u0014QI\u0005\u0004\u0003\u000f\n\"!C*z[\n|Gn\u00149t\u0011%\tY%a\u000b!B\u0013\tI$\u0001\u0007`S:LG/[1mSj,G\r\u000b\u0003\u0002J\u0005=\u0003c\u0001\u0006\u0002R%\u0019\u00111\u000b\u0004\u0003\u0011Y|G.\u0019;jY\u0016D\u0001\"a\u0016\u0002,\u0001\u0006Ka^\u0001\u0014?&t\u0017\u000e^5bY&T\u0018\r^5p]6\u000b7o\u001b\u0015\u0005\u0003+\ny\u0005\u0003\u0005\u0002^\u0005-B\u0011IA0\u0003Ii\u0017M]6GY\u0006<7oQ8na2,G/\u001a3\u0015\t\u0005\u0005\u00141M\u0007\u0003\u0003WAq!!\u001a\u0002\\\u0001\u0007q/\u0001\u0003nCN\\\u0007\u0002CA5\u0003W!\t%a\u001b\u0002!5\f'o[!mY\u000e{W\u000e\u001d7fi\u0016$GCAA1\u0011!\ty'a\u000b\u0005\u0002\u0005E\u0014AH4jYNKhn\u00195s_:L'0\u001a3JM:{G\u000f\u00165sK\u0006$7/\u00194f+\u0011\t\u0019(!\u001f\u0015\t\u0005U\u0014Q\u0011\t\u0005\u0003o\nI\b\u0004\u0001\u0005\u0011\u0005m\u0014Q\u000eb\u0001\u0003{\u0012\u0011\u0001V\t\u0004\u0003\u007f\u0012\bc\u0001\u0006\u0002\u0002&\u0019\u00111\u0011\u0004\u0003\u000f9{G\u000f[5oO\"I\u0011qQA7\t\u0003\u0007\u0011\u0011R\u0001\u0005E>$\u0017\u0010\u0005\u0003\u000ba\u0006U\u0004\u0002CAG\u0003W!\t%a$\u0002\u000fY\fG.\u001b3U_V\u0011\u0011\u0011\u0013\t\u0004w\u0005M\u0015\u0002BAK\u0003/\u0013a\u0001U3sS>$\u0017bAAM\u001f\tY1+_7c_2$\u0016M\u00197f\u0011!\ti*a\u000b\u0005B\u0005}\u0015\u0001B5oM>,\"!!)\u0011\u0007m\n\u0019+\u0003\u0003\u0002&\u0006\u001d&\u0001\u0002+za\u0016L1!!+\u0010\u0005\u0015!\u0016\u0010]3t\u0011!\ti+a\u000b\u0005B\u0005}\u0015a\u0002:bo&sgm\u001c\u0005\t\u0003c\u000bY\u0003\"\u0011\u0002 \u0006iA/\u001f9f'&<g.\u0019;ve\u0016D\u0001\"!.\u0002,\u0011\u0005\u0013qW\u0001\u0010if\u0004XmU5h]\u0006$XO]3J]R!\u0011\u0011UA]\u0011!\tY,a-A\u0002\u0005\u0005\u0016\u0001B:ji\u0016D\u0001\"a0\u0002,\u0011\u0005\u0013\u0011Y\u0001\u000bif\u0004X\rU1sC6\u001cXCAAb!\u0015\t)-a3H\u001d\rQ\u0011qY\u0005\u0004\u0003\u00134\u0011a\u00029bG.\fw-Z\u0005\u0005\u0003\u001b\fyM\u0001\u0003MSN$(bAAe\r!A\u00111[A\u0016\t\u0003\n\t-\u0001\tv]N\fg-\u001a+za\u0016\u0004\u0016M]1ng\"A\u0011q[A\u0016\t#\nI.\u0001\rde\u0016\fG/Z!cgR\u0014\u0018m\u0019;UsB,7+_7c_2$\u0002\"a7\u0002b\u0006\r\u0018\u0011\u001f\t\u0004w\u0005u\u0017bAAp#\t\u0011\u0012IY:ue\u0006\u001cG\u000fV=qKNKXNY8m\u0011\u001dA\u0017Q\u001ba\u0001\u0003+A\u0001\"!:\u0002V\u0002\u0007\u0011q]\u0001\u0004a>\u001c\bcA\u001e\u0002j&!\u00111^Aw\u0005!\u0001vn]5uS>t\u0017bAAx\u001f\tI\u0001k\\:ji&|gn\u001d\u0005\b\u0003g\f)\u000e1\u0001x\u0003!qWm\u001e$mC\u001e\u001c\b\u0002CA|\u0003W!\t&!?\u0002+\r\u0014X-\u0019;f\u00032L\u0017m\u001d+za\u0016\u001c\u00160\u001c2pYRA\u00111 B\u0001\u0005\u0007\u0011)\u0001E\u0002<\u0003{L1!a@\u0012\u0005=\tE.[1t)f\u0004XmU=nE>d\u0007b\u00025\u0002v\u0002\u0007\u0011Q\u0003\u0005\t\u0003K\f)\u00101\u0001\u0002h\"9\u00111_A{\u0001\u00049\b\u0002\u0003B\u0005\u0003W!\tFa\u0003\u0002-\r\u0014X-\u0019;f)f\u0004XmU6pY\u0016l7+_7c_2$\"B!\u0004\u0003\u0014\tU!q\u0003B\r!\rY$qB\u0005\u0004\u0005#\t\"A\u0003+za\u0016\u001c6n\u001c7f[\"9\u0001Na\u0002A\u0002\u0005U\u0001BB>\u0003\b\u0001\u0007\u0011\u0002\u0003\u0005\u0002f\n\u001d\u0001\u0019AAt\u0011\u001d\t\u0019Pa\u0002A\u0002]D\u0001B!\b\u0002,\u0011E#qD\u0001\u0012GJ,\u0017\r^3DY\u0006\u001c8oU=nE>dGc\u00020\u0003\"\t\r\"Q\u0005\u0005\bQ\nm\u0001\u0019AA\u000b\u0011!\t)Oa\u0007A\u0002\u0005\u001d\bbBAz\u00057\u0001\ra\u001e\u0005\t\u0005S\tY\u0003\"\u0015\u0003,\u000592M]3bi\u0016lu\u000eZ;mK\u000ec\u0017m]:Ts6\u0014w\u000e\u001c\u000b\t\u0005[\u0011\u0019D!\u000e\u00038A\u00191Ha\f\n\u0007\tE\u0012CA\tN_\u0012,H.Z\"mCN\u001c8+_7c_2Dq\u0001\u001bB\u0014\u0001\u0004\t)\u0002\u0003\u0005\u0002f\n\u001d\u0002\u0019AAt\u0011\u001d\t\u0019Pa\nA\u0002]D\u0001Ba\u000f\u0002,\u0011E#QH\u0001\u0019GJ,\u0017\r^3QC\u000e\\\u0017mZ3DY\u0006\u001c8oU=nE>dG\u0003\u0003B \u0005\u000b\u00129E!\u0013\u0011\u0007m\u0012\t%C\u0002\u0003DE\u0011!\u0003U1dW\u0006<Wm\u00117bgN\u001c\u00160\u001c2pY\"9\u0001N!\u000fA\u0002\u0005U\u0001\u0002CAs\u0005s\u0001\r!a:\t\u000f\u0005M(\u0011\ba\u0001o\"A!QJA\u0016\t#\u0012y%A\u000ede\u0016\fG/\u001a*fM&tW-\\3oi\u000ec\u0017m]:Ts6\u0014w\u000e\u001c\u000b\u0007\u0005#\u00129F!\u0017\u0011\u0007m\u0012\u0019&C\u0002\u0003VE\u0011QCU3gS:,W.\u001a8u\u00072\f7o]*z[\n|G\u000e\u0003\u0005\u0002f\n-\u0003\u0019AAt\u0011\u001d\t\u0019Pa\u0013A\u0002]D\u0001B!\u0018\u0002,\u0011E#qL\u0001\u0016GJ,\u0017\r^3J[Bd7\t\\1tgNKXNY8m)\u001dq&\u0011\rB2\u0005KBq\u0001\u001bB.\u0001\u0004\t)\u0002\u0003\u0005\u0002f\nm\u0003\u0019AAt\u0011\u001d\t\u0019Pa\u0017A\u0002]D\u0001B!\u001b\u0002,\u0011E#1N\u0001\u001fGJ,\u0017\r^3QC\u000e\\\u0017mZ3PE*,7\r^\"mCN\u001c8+_7c_2$bA!\u001c\u0003t\tU\u0004cA\u001e\u0003p%\u0019!\u0011O\t\u00031A\u000b7m[1hK>\u0013'.Z2u\u00072\f7o]*z[\n|G\u000e\u0003\u0005\u0002f\n\u001d\u0004\u0019AAt\u0011\u001d\t\u0019Pa\u001aA\u0002]D\u0001B!\u001f\u0002,\u0011E#1P\u0001\u0013GJ,\u0017\r^3NKRDw\u000eZ*z[\n|G\u000e\u0006\u0005\u0003~\t\r%Q\u0011BD!\rY$qP\u0005\u0004\u0005\u0003\u000b\"\u0001D'fi\"|GmU=nE>d\u0007B\u00025\u0003x\u0001\u0007\u0011\u000e\u0003\u0005\u0002f\n]\u0004\u0019AAt\u0011\u001d\t\u0019Pa\u001eA\u0002]D\u0001Ba#\u0002,\u0011E#QR\u0001\u0013GJ,\u0017\r^3N_\u0012,H.Z*z[\n|G\u000eF\u0004X\u0005\u001f\u0013\tJa%\t\r!\u0014I\t1\u0001j\u0011!\t)O!#A\u0002\u0005\u001d\bbBAz\u0005\u0013\u0003\ra\u001e\u0005\t\u0005/\u000bY\u0003\"\u0015\u0003\u001a\u0006\u00192M]3bi\u0016\u0004\u0016mY6bO\u0016\u001c\u00160\u001c2pYR9qKa'\u0003\u001e\n}\u0005B\u00025\u0003\u0016\u0002\u0007\u0011\u000e\u0003\u0005\u0002f\nU\u0005\u0019AAt\u0011\u001d\t\u0019P!&A\u0002]D\u0001Ba)\u0002,\u0011E#QU\u0001\u001bGJ,\u0017\r^3WC2,X\rU1sC6,G/\u001a:Ts6\u0014w\u000e\u001c\u000b\t\u0005O\u0013iLa0\u0003BJ1!\u0011\u0016BW\u0005g3qAa+\u0003\"\u0002\u00119K\u0001\u0007=e\u00164\u0017N\\3nK:$h\bE\u0002<\u0005_K1A!-\u0012\u0005)!VM]7Ts6\u0014w\u000e\u001c\t\u0004w\tUf!\u0003B\\\u0001A\u0005\u0019\u0013\u0001B]\u0005Y\u0019\u0016P\\2ie>t\u0017N_3e)\u0016\u0014XnU=nE>d7#\u0002B[\u000f\nm\u0006cA\u001e\u0002,!1\u0001N!)A\u0002%D\u0001\"!:\u0003\"\u0002\u0007\u0011q\u001d\u0005\b\u0003g\u0014\t\u000b1\u0001x\u0011!\u0011)-a\u000b\u0005R\t\u001d\u0017aF2sK\u0006$XMV1mk\u0016lU-\u001c2feNKXNY8m)!\u0011IM!4\u0003P\nE'C\u0002Bf\u0005[\u0013\u0019LB\u0004\u0003,\n\r\u0007A!3\t\r!\u0014\u0019\r1\u0001j\u0011!\t)Oa1A\u0002\u0005\u001d\bbBAz\u0005\u0007\u0004\ra\u001e\u0005\u0010\u0005+\fY\u0003%A\u0002\u0002\u0003%I!a$\u0003X\u0006i1/\u001e9fe\u00122\u0018\r\\5e)>L1!!$I\u0011=\u0011Y.a\u000b\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0002 \nu\u0017AC:va\u0016\u0014H%\u001b8g_&\u0019\u0011Q\u0014%\t\u001f\t\u0005\u00181\u0006I\u0001\u0004\u0003\u0005I\u0011BAP\u0005G\fQb];qKJ$#/Y<J]\u001a|\u0017bAAW\u0011\"y!q]A\u0016!\u0003\r\t\u0011!C\u0005\u0003?\u0013I/A\ntkB,'\u000f\n;za\u0016\u001c\u0016n\u001a8biV\u0014X-\u0003\u0003\u00022\n-\u0018b\u0001Bw#\t!2+_7c_2\u001cuN\u001c;fqR\f\u0005/[%na2DqB!=\u0002,A\u0005\u0019\u0011!A\u0005\n\tM(q_\u0001\u0016gV\u0004XM\u001d\u0013usB,7+[4oCR,(/Z%o)\u0011\t\tK!>\t\u0011\u0005m&q\u001ea\u0001\u0003CKA!!.\u0003l\"y!1`A\u0016!\u0003\r\t\u0011!C\u0005\u0003\u0003\u0014i0\u0001\ttkB,'\u000f\n;za\u0016\u0004\u0016M]1ng&\u0019\u0011q\u0018%\t\u001f\r\u0005\u00111\u0006I\u0001\u0004\u0003\u0005I\u0011BAa\u0007\u0007\tac];qKJ$SO\\:bM\u0016$\u0016\u0010]3QCJ\fWn]\u0005\u0004\u0003'De!CB\u0004\u0001A\u0005\u0019\u0011AB\u0005\u0005a\u0019\u0016P\\2ie>t\u0017N_3e\u001b\u0016$\bn\u001c3Ts6\u0014w\u000e\\\n\u0007\u0007\u000b\u0011iHa-\t\rQ\u0019)\u0001\"\u0001\u0017\u0011-\u0019ya!\u0002\t\u0006\u0004%Ia!\u0005\u0002%QL\b/Z!t\u001b\u0016l'-\u001a:PM2{7m[\u000b\u0003\u0007'\u0001Ba!\u0006\u0004\u001c5\u00111q\u0003\u0006\u0004\u00073)\u0013\u0001\u00027b]\u001eLAa!\b\u0004\u0018\t1qJ\u00196fGRD1b!\t\u0004\u0006!\u0005\t\u0015)\u0003\u0004\u0014\u0005\u0019B/\u001f9f\u0003NlU-\u001c2fe>3Gj\\2lA!A1QEB\u0003\t\u0003\u001a9#\u0001\busB,\u0017i]'f[\n,'o\u00144\u0015\t\u0005\u00056\u0011\u0006\u0005\t\u0007W\u0019\u0019\u00031\u0001\u0002\"\u0006\u0019\u0001O]3\t\u001f\r=2Q\u0001I\u0001\u0004\u0003\u0005I\u0011BB\u0019\u0007k\tAc];qKJ$C/\u001f9f\u0003NlU-\u001c2fe>3G\u0003BAQ\u0007gA\u0001ba\u000b\u0004.\u0001\u0007\u0011\u0011U\u0005\u0005\u0007K\u0011yHB\u0005\u0004:\u0001\u0001\n1%\u0001\u0004<\tA2+\u001f8dQJ|g.\u001b>fI6{G-\u001e7f'fl'm\u001c7\u0014\u000b\r]rKa-\u0007\u0013\r}\u0002\u0001%A\u0002\u0002\r\u0005#AF*z]\u000eD'o\u001c8ju\u0016$G+\u001f9f'fl'm\u001c7\u0014\r\ru21\tB^!\rY4QI\u0005\u0004\u0007\u000f\n\"A\u0003+za\u0016\u001c\u00160\u001c2pY\"1Ac!\u0010\u0005\u0002YA1b!\u0014\u0004>!\u0015\r\u0011\"\u0003\u0004\u0012\u00059A\u000f]3M_\u000e\\\u0007bCB)\u0007{A\t\u0011)Q\u0005\u0007'\t\u0001\u0002\u001e9f\u0019>\u001c7\u000e\t\u0005\t\u0007+\u001ai\u0004\"\u0011\u0002 \u0006QA\u000f]3`IQLW.Z:\t\u001f\re3Q\bI\u0001\u0004\u0003\u0005I\u0011BAP\u00077\n\u0001c];qKJ$C\u000f]3`IQLW.Z:\n\t\rU3Q\t\u0004\n\u0007?\u0002\u0001\u0013aI\u0001\u0007C\u0012qcU=oG\"\u0014xN\\5{K\u0012\u001cE.Y:t'fl'm\u001c7\u0014\u000b\rucla\u0019\u0011\u0007m\u001aiDB\u0005\u0004h\u0001\u0001\n1%\u0001\u0004j\ti2+\u001f8dQJ|g.\u001b>fI6{G-\u001e7f\u00072\f7o]*z[\n|Gn\u0005\u0004\u0004f\t521\u000e\t\u0004w\ru\u0003\"CB8\u0001E\u0005I\u0011IB9\u0003mqWm\u001e$sK\u0016$VM]7Ts6\u0014w\u000e\u001c\u0013eK\u001a\fW\u000f\u001c;%gU\u001111\u000f\u0016\u0004o\u000eU4FAB<!\u0011\u0019Iha!\u000e\u0005\rm$\u0002BB?\u0007\u007f\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\r\u0005e!\u0001\u0006b]:|G/\u0019;j_:LAa!\"\u0004|\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\t\u0013\r%\u0005!%A\u0005\u0002\r-\u0015a\u00078fo\u001a\u0013X-\u001a+fe6\u001c\u00160\u001c2pY\u0012\"WMZ1vYR$C'\u0006\u0002\u0004\u000e*\u001aAp!\u001e\t\u0013\rE\u0005!%A\u0005B\rE\u0014a\u00078fo\u001a\u0013X-\u001a+za\u0016\u001c\u00160\u001c2pY\u0012\"WMZ1vYR$#\u0007C\u0005\u0004\u0016\u0002\t\n\u0011\"\u0001\u0004\f\u0006Yb.Z<Ge\u0016,G+\u001f9f'fl'm\u001c7%I\u00164\u0017-\u001e7uIMBab!'\u0001!\u0003\r\t\u0011!C\u0005\u00077\u001b\t+\u0001\u000etkB,'\u000fJ2p]:,7\r^'pIVdW\rV8DY\u0006\u001c8\u000fF\u0003X\u0007;\u001by\n\u0003\u0004\\\u0007/\u0003\ra\u0016\u0005\u0007;\u000e]\u0005\u0019\u00010\n\u0005U\u000b\u0002\u0003BBS\u0007Ok\u0011AA\u0005\u0004\u00033\u0013\u0001")
public interface SynchronizedSymbols
extends Symbols {
    public /* synthetic */ Symbols.ModuleSymbol scala$reflect$runtime$SynchronizedSymbols$$super$connectModuleToClass(Symbols.ModuleSymbol var1, Symbols.ClassSymbol var2);

    public AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicIds();

    @Override
    public int nextId();

    public AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds();

    @Override
    public int nextExistentialId();

    public ThreadLocalStorage.ThreadLocalStorage<Map<Symbols.Symbol, Object>> scala$reflect$runtime$SynchronizedSymbols$$_recursionTable();

    @Override
    public Map<Symbols.Symbol, Object> recursionTable();

    @Override
    public void recursionTable_$eq(Map<Symbols.Symbol, Object> var1);

    @Override
    public Symbols.ModuleSymbol connectModuleToClass(Symbols.ModuleSymbol var1, Symbols.ClassSymbol var2);

    @Override
    public Symbols.FreeTermSymbol newFreeTermSymbol(Names.TermName var1, Function0<Object> var2, long var3, String var5);

    @Override
    public long newFreeTermSymbol$default$3();

    public String newFreeTermSymbol$default$4();

    @Override
    public Symbols.FreeTypeSymbol newFreeTypeSymbol(Names.TypeName var1, long var2, String var4);

    @Override
    public long newFreeTypeSymbol$default$2();

    public String newFreeTypeSymbol$default$3();

    @Override
    public Symbols.NoSymbol makeNoSymbol();

    public interface SynchronizedSymbol {
        public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized();

        public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(boolean var1);

        public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask();

        public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(long var1);

        public /* synthetic */ int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo();

        public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info();

        public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo();

        public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature();

        public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(Types.Type var1);

        public /* synthetic */ List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams();

        public /* synthetic */ List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams();

        public boolean isThreadsafe(Symbols.SymbolOps var1);

        public SynchronizedSymbol markFlagsCompleted(long var1);

        public SynchronizedSymbol markAllCompleted();

        public <T> T gilSynchronizedIfNotThreadsafe(Function0<T> var1);

        public int validTo();

        public Types.Type info();

        public Types.Type rawInfo();

        public Types.Type typeSignature();

        public Types.Type typeSignatureIn(Types.Type var1);

        public List<Symbols.Symbol> typeParams();

        public List<Symbols.Symbol> unsafeTypeParams();

        public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(Names.TypeName var1, Position var2, long var3);

        public Symbols.AliasTypeSymbol createAliasTypeSymbol(Names.TypeName var1, Position var2, long var3);

        public Symbols.TypeSkolem createTypeSkolemSymbol(Names.TypeName var1, Object var2, Position var3, long var4);

        public Symbols.ClassSymbol createClassSymbol(Names.TypeName var1, Position var2, long var3);

        public Symbols.ModuleClassSymbol createModuleClassSymbol(Names.TypeName var1, Position var2, long var3);

        public Symbols.PackageClassSymbol createPackageClassSymbol(Names.TypeName var1, Position var2, long var3);

        public Symbols.RefinementClassSymbol createRefinementClassSymbol(Position var1, long var2);

        public Symbols.ClassSymbol createImplClassSymbol(Names.TypeName var1, Position var2, long var3);

        public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(Position var1, long var2);

        public Symbols.MethodSymbol createMethodSymbol(Names.TermName var1, Position var2, long var3);

        public Symbols.ModuleSymbol createModuleSymbol(Names.TermName var1, Position var2, long var3);

        public Symbols.ModuleSymbol createPackageSymbol(Names.TermName var1, Position var2, long var3);

        public Symbols.TermSymbol createValueParameterSymbol(Names.TermName var1, Position var2, long var3);

        public Symbols.TermSymbol createValueMemberSymbol(Names.TermName var1, Position var2, long var3);

        public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
    }

    public interface SynchronizedTermSymbol
    extends SynchronizedSymbol {
    }

    public interface SynchronizedTypeSymbol
    extends SynchronizedSymbol {
        public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$super$tpe_$times();

        public Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock();

        public Types.Type tpe_$times();

        public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$$outer();
    }

    public interface SynchronizedClassSymbol
    extends SynchronizedTypeSymbol {
    }

    public interface SynchronizedMethodSymbol
    extends SynchronizedTermSymbol {
        public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$super$typeAsMemberOf(Types.Type var1);

        public Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$typeAsMemberOfLock();

        public Types.Type typeAsMemberOf(Types.Type var1);

        public /* synthetic */ SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedMethodSymbol$$$outer();
    }

    public interface SynchronizedModuleSymbol
    extends SynchronizedTermSymbol {
    }

    public interface SynchronizedModuleClassSymbol
    extends SynchronizedClassSymbol {
    }
}

