/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Annotations;
import scala.reflect.api.Constants;
import scala.reflect.api.Internals;
import scala.reflect.api.Names;
import scala.reflect.api.Scopes;
import scala.reflect.api.Symbols;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;

@ScalaSignature(bytes="\u0006\u0001\u0019\u001dc!C\u0001\u0003!\u0003\r\t!\u0003D \u0005\u0015!\u0016\u0010]3t\u0015\t\u0019A!A\u0002ba&T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\r\u001b\u00051\u0011BA\u0007\u0007\u0005\u0019\te.\u001f*fM\")q\u0002\u0001C\u0001!\u00051A%\u001b8ji\u0012\"\u0012!\u0005\t\u0003\u0017II!a\u0005\u0004\u0003\tUs\u0017\u000e\u001e\u0003\u0006+\u0001\u0011\tA\u0006\u0002\u0005)f\u0004X-\u0005\u0002\u00185A\u00111\u0002G\u0005\u00033\u0019\u0011AAT;mYJ\u00191DC\u000f\u0007\tq\u0001\u0001A\u0007\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0003=}i\u0011\u0001\u0001\u0004\u0006A\u0001\t\t!\t\u0002\b)f\u0004X-\u00119j'\ty\"\u0002C\u0003$?\u0011\u0005A%\u0001\u0004=S:LGO\u0010\u000b\u0002;!)ae\bD\u0001O\u0005QA/\u001a:n'fl'm\u001c7\u0016\u0003!\u0002\"AH\u0015\n\u0005)Z#AB*z[\n|G.\u0003\u0002-\u0005\t91+_7c_2\u001c\b\"\u0002\u0018 \r\u00039\u0013A\u0003;za\u0016\u001c\u00160\u001c2pY\")\u0001g\bD\u0001c\u0005YA-Z2mCJ\fG/[8o)\tA#\u0007C\u00034_\u0001\u0007A'\u0001\u0003oC6,\u0007C\u0001\u00106\u0013\t1tG\u0001\u0003OC6,\u0017B\u0001\u001d\u0003\u0005\u0015q\u0015-\\3tQ\u0011y#(P \u0011\u0005-Y\u0014B\u0001\u001f\u0007\u0005)!W\r\u001d:fG\u0006$X\rZ\u0011\u0002}\u0005\u0011Rk]3!A\u0012,7\r\u001c1!S:\u001cH/Z1eC\u0005\u0001\u0015A\u0002\u001a/cEr\u0003\u0007C\u0003C?\u0019\u00051)\u0001\u0003eK\u000edGC\u0001\u0015E\u0011\u0015\u0019\u0014\t1\u00015\u0011\u00151uD\"\u0001H\u00031!Wm\u00197be\u0006$\u0018n\u001c8t+\u0005A\u0005C\u0001\u0010J\u0013\tQ5JA\u0006NK6\u0014WM]*d_B,\u0017B\u0001'\u0003\u0005\u0019\u00196m\u001c9fg\"\"QI\u000f(@C\u0005y\u0015aE+tK\u0002\u0002G-Z2mg\u0002\u0004\u0013N\\:uK\u0006$\u0007\"B) \r\u00039\u0015!\u00023fG2\u001c\b\"B* \r\u0003!\u0016AB7f[\n,'\u000f\u0006\u0002)+\")1G\u0015a\u0001i!)qk\bD\u0001\u000f\u00069Q.Z7cKJ\u001c\b\"B- \r\u0003Q\u0016!C2p[B\fg.[8o+\u0005Y\u0006C\u0001\u0010\u0015\u0011\u0015ivD\"\u0001_\u00035!\u0018m[3t)f\u0004X-\u0011:hgV\tq\f\u0005\u0002\fA&\u0011\u0011M\u0002\u0002\b\u0005>|G.Z1o\u0011\u0015\u0019wD\"\u0001[\u0003=!\u0018\u0010]3D_:\u001cHO];di>\u0014\b\"B3 \r\u0003Q\u0016!\u00038pe6\fG.\u001b>fQ\u0011!'hZ \"\u0003!\fA%V:fA\u0001$W-\u00197jCN\u0004\u0007e\u001c:!A\u0016$\u0018-\u0012=qC:$\u0007\rI5ogR,\u0017\r\u001a\u0005\u0006U~1\tAW\u0001\nKR\fW\t\u001f9b]\u0012DQ\u0001\\\u0010\u0007\u00025\f\u0001\u0003\n7fgN$3m\u001c7p]\u0012bWm]:\u0015\u0005}s\u0007\"B8l\u0001\u0004Y\u0016\u0001\u0002;iCRDQ!]\u0010\u0007\u0002I\fQc^3bW~#C.Z:tI\r|Gn\u001c8%Y\u0016\u001c8\u000f\u0006\u0002`g\")q\u000e\u001da\u00017\")Qo\bD\u0001m\u0006aA%Z9%G>dwN\u001c\u0013fcR\u0011ql\u001e\u0005\u0006_R\u0004\ra\u0017\u0005\u0006s~1\tA_\u0001\fE\u0006\u001cXm\u00117bgN,7/F\u0001|!\rax\u0010\u000b\b\u0003\u0017uL!A \u0004\u0002\u000fA\f7m[1hK&!\u0011\u0011AA\u0002\u0005\u0011a\u0015n\u001d;\u000b\u0005y4\u0001bBA\u0004?\u0019\u0005\u0011\u0011B\u0001\tE\u0006\u001cX\rV=qKR\u00191,a\u0003\t\u000f\u00055\u0011Q\u0001a\u0001Q\u0005)1\r\\1{u\"9\u0011\u0011C\u0010\u0007\u0002\u0005M\u0011AC1t'\u0016,gN\u0012:p[R)1,!\u0006\u0002\u001a!9\u0011qCA\b\u0001\u0004Y\u0016a\u00019sK\"9\u0011QBA\b\u0001\u0004A\u0003BBA\u000f?\u0019\u0005!,A\u0004fe\u0006\u001cXO]3\t\r\u0005\u0005rD\"\u0001[\u0003\u00159\u0018\u000eZ3o\u0011\u0019\t)c\bD\u00015\u00069A-Z1mS\u0006\u001c\bbBA\u0015?\u0019\u0005\u00111F\u0001\tif\u0004X-\u0011:hgV\u0011\u0011Q\u0006\t\u0004y~\\\u0006bBA\u0019?\u0019\u0005\u00111G\u0001\ba\u0006\u0014\u0018-\\:t+\t\t)\u0004E\u0002}\u007fnDc!a\f;\u0003sy\u0014EAA\u001e\u0003a)6/\u001a\u0011aa\u0006\u0014\u0018-\u001c'jgR\u001c\b\rI5ogR,\u0017\r\u001a\u0005\b\u0003\u007fyb\u0011AA\u001a\u0003)\u0001\u0018M]1n\u0019&\u001cHo\u001d\u0005\u0007\u0003\u0007zb\u0011\u0001>\u0002\u0015QL\b/\u001a)be\u0006l7\u000f\u0003\u0004\u0002H}1\tAW\u0001\u000be\u0016\u001cX\u000f\u001c;UsB,\u0007BBA&?\u0019\u0005!,A\bgS:\fGNU3tk2$H+\u001f9f\u0011\u001d\tye\bD\u0001\u0003#\naa\u001c:FYN,GcA.\u0002T!I\u0011QKA'\t\u0003\u0007\u0011qK\u0001\u0004C2$\b\u0003B\u0006\u0002ZmK1!a\u0017\u0007\u0005!a$-\u001f8b[\u0016t\u0004bBA0?\u0019\u0005\u0011\u0011M\u0001\u0012gV\u00147\u000f^5ukR,7+_7c_2\u001cH#B.\u0002d\u0005\u001d\u0004bBA3\u0003;\u0002\ra_\u0001\u0005MJ|W\u000eC\u0004\u0002j\u0005u\u0003\u0019A>\u0002\u0005Q|\u0007bBA7?\u0019\u0005\u0011qN\u0001\u0010gV\u00147\u000f^5ukR,G+\u001f9fgR)1,!\u001d\u0002t!9\u0011QMA6\u0001\u0004Y\b\u0002CA5\u0003W\u0002\r!!\f\t\u000f\u0005]tD\"\u0001\u0002z\u0005\u0019Q.\u00199\u0015\u0007m\u000bY\b\u0003\u0005\u0002~\u0005U\u0004\u0019AA@\u0003\u00051\u0007#B\u0006\u0002\u0002n[\u0016bAAB\r\tIa)\u001e8di&|g.\r\u0005\b\u0003\u000f{b\u0011AAE\u0003\u001d1wN]3bG\"$2!EAF\u0011!\ti(!\"A\u0002\u00055\u0005#B\u0006\u0002\u0002n\u000b\u0002bBAI?\u0019\u0005\u00111S\u0001\u0005M&tG\r\u0006\u0003\u0002\u0016\u0006m\u0005\u0003B\u0006\u0002\u0018nK1!!'\u0007\u0005\u0019y\u0005\u000f^5p]\"A\u0011QTAH\u0001\u0004\ty*A\u0001q!\u0015Y\u0011\u0011Q.`\u0011\u001d\t\u0019k\bD\u0001\u0003K\u000ba!\u001a=jgR\u001cHcA0\u0002(\"A\u0011QTAQ\u0001\u0004\ty\nC\u0004\u0002,~1\t!!,\u0002\u0011\r|g\u000e^1j]N$2aXAX\u0011\u001d\t\t,!+A\u0002!\n1a]=n\u0011!\t)\f\u0001b\u0001\u000e\u0003Q\u0016A\u0002(p)f\u0004X\r\u0003\u0005\u0002:\u0002\u0011\rQ\"\u0001[\u0003!qu\u000e\u0015:fM&DHaBA_\u0001\t\u0005\u0011q\u0018\u0002\u000e'&tw\r\\3u_:$\u0016\u0010]3\u0012\u0007]\t\tME\u0003\u0002D\u0006\u00157LB\u0003\u001d\u0001\u0001\t\t\rE\u0002\u001f\u0003\u000f4\u0011\"!3\u0001!\u0003\r\n!a3\u0003!MKgn\u001a7fi>tG+\u001f9f\u0003BL7cAAd\u0015\u00119\u0011q\u001a\u0001\u0003\u0002\u0005E'\u0001\u0003+iSN$\u0016\u0010]3\u0012\u0007]\t\u0019N\u0005\u0004\u0002V\u0006]\u00171\u001d\u0004\u00069\u0001\u0001\u00111\u001b\t\u0004=\u0005egaCAn\u0001A\u0005\u0019\u0013AAo\u0003C\u00141\u0002\u00165jgRK\b/Z!qSN\u0019\u0011\u0011\\\u000f\t\u000f\u0005E\u0016\u0011\u001cD\u0001OA\u0019a$!4\u0011\u0007y\tY\fC\u0005\u0002h\u0002\u0011\rQ\"\u0001\u0002j\u0006AA\u000b[5t)f\u0004X-\u0006\u0002\u0002lB\u0019a$!<\u0007\u000f\u0005=\b!!\u0001\u0002r\n\tB\u000b[5t)f\u0004X-\u0012=ue\u0006\u001cGo\u001c:\u0014\u0007\u00055(\u0002C\u0004$\u0003[$\t!!>\u0015\u0005\u0005-\b\u0002CA}\u0003[4\t!a?\u0002\u000fUt\u0017\r\u001d9msR!\u0011Q`A\u0000!\u0011Y\u0011q\u0013\u0015\t\u0011\t\u0005\u0011q\u001fa\u0001\u0003C\f1\u0001\u001e9f\u0011!\u0011)!!<\u0005\u0002\t\u001d\u0011!B1qa2LH\u0003\u0002B\u0005\u00053!2a\u0017B\u0006\u0011!\u0011iAa\u0001A\u0004\t=\u0011!\u0002;pW\u0016t\u0007c\u0001\u0010\u0003\u0012%!!1\u0003B\u000b\u0005-\u0019u.\u001c9biR{7.\u001a8\n\u0007\t]!AA\u0005J]R,'O\\1mg\"9\u0011\u0011\u0017B\u0002\u0001\u0004A\u0003F\u0002B\u0002u\tuq(\t\u0002\u0003 \u0005yRk]3!A&tG/\u001a:oC2tC\u000f[5t)f\u0004X\r\u0019\u0011j]N$X-\u00193\u0005\u000f\t\r\u0002A!\u0001\u0003&\tQ1+\u001b8hY\u0016$\u0016\u0010]3\u0012\u0007]\u00119C\u0005\u0004\u0003*\t-\u00121\u001d\u0004\u00069\u0001\u0001!q\u0005\t\u0004=\t5ba\u0003B\u0018\u0001A\u0005\u0019\u0013\u0001B\u0019\u0005o\u0011QbU5oO2,G+\u001f9f\u0003BL7c\u0001B\u0017;!9\u0011q\u0003B\u0017\r\u0003Q\u0006bBAY\u0005[1\ta\n\t\u0004=\t\u0005\u0002\"\u0003B\u001e\u0001\t\u0007i\u0011\u0001B\u001f\u0003)\u0019\u0016N\\4mKRK\b/Z\u000b\u0003\u0005\u007f\u00012A\bB!\r\u001d\u0011\u0019\u0005AA\u0001\u0005\u000b\u00121cU5oO2,G+\u001f9f\u000bb$(/Y2u_J\u001c2A!\u0011\u000b\u0011\u001d\u0019#\u0011\tC\u0001\u0005\u0013\"\"Aa\u0010\t\u0011\u0005e(\u0011\tD\u0001\u0005\u001b\"BAa\u0014\u0003XA)1\"a&\u0003RA)1Ba\u0015\\Q%\u0019!Q\u000b\u0004\u0003\rQ+\b\u000f\\33\u0011!\u0011\tAa\u0013A\u0002\t]\u0002\u0002\u0003B\u0003\u0005\u0003\"\tAa\u0017\u0015\r\tu#\u0011\rB2)\rY&q\f\u0005\t\u0005\u001b\u0011I\u0006q\u0001\u0003\u0010!9\u0011q\u0003B-\u0001\u0004Y\u0006bBAY\u00053\u0002\r\u0001\u000b\u0015\u0007\u00053R$qM \"\u0005\t%\u0014!P+tK\u0002\u00027\t\\1tgNKXNY8m]QD\u0017n\u001d)sK\u001aL\u0007\u0010\u0019\u0011pe\u0002\u0002\u0017N\u001c;fe:\fGNL:j]\u001edW\rV=qK\u0002\u0004\u0013N\\:uK\u0006$Ga\u0002B7\u0001\t\u0005!q\u000e\u0002\n'V\u0004XM\u001d+za\u0016\f2a\u0006B9%\u0019\u0011\u0019H!\u001e\u0002d\u001a)A\u0004\u0001\u0001\u0003rA\u0019aDa\u001e\u0007\u0017\te\u0004\u0001%A\u0012\u0002\tm$Q\u0011\u0002\r'V\u0004XM\u001d+za\u0016\f\u0005/[\n\u0004\u0005oj\u0002b\u0002B@\u0005o2\tAW\u0001\bi\"L7\u000f\u001e9f\u0011\u001d\u0011\u0019Ia\u001e\u0007\u0002i\u000b\u0001b];qKJ$\b/\u001a\t\u0004=\t-\u0004\"\u0003BE\u0001\t\u0007i\u0011\u0001BF\u0003%\u0019V\u000f]3s)f\u0004X-\u0006\u0002\u0003\u000eB\u0019aDa$\u0007\u000f\tE\u0005!!\u0001\u0003\u0014\n\u00112+\u001e9feRK\b/Z#yiJ\f7\r^8s'\r\u0011yI\u0003\u0005\bG\t=E\u0011\u0001BL)\t\u0011i\t\u0003\u0005\u0002z\n=e\u0011\u0001BN)\u0011\u0011iJ!)\u0011\u000b-\t9Ja(\u0011\u000b-\u0011\u0019fW.\t\u0011\t\u0005!\u0011\u0014a\u0001\u0005\u000bC\u0001B!\u0002\u0003\u0010\u0012\u0005!Q\u0015\u000b\u0007\u0005O\u0013YK!,\u0015\u0007m\u0013I\u000b\u0003\u0005\u0003\u000e\t\r\u00069\u0001B\b\u0011\u001d\u0011yHa)A\u0002mCqAa!\u0003$\u0002\u00071\f\u000b\u0004\u0003$j\u0012\tlP\u0011\u0003\u0005g\u000bQ(V:fA\u0001\u001cE.Y:t'fl'm\u001c7/gV\u0004XM\u001d)sK\u001aL\u0007\u0010\u0019\u0011pe\u0002\u0002\u0017N\u001c;fe:\fGNL:va\u0016\u0014H+\u001f9fA\u0002Jgn\u001d;fC\u0012$qAa.\u0001\u0005\u0003\u0011IL\u0001\u0007D_:\u001cH/\u00198u)f\u0004X-E\u0002\u0018\u0005w\u0013bA!0\u0003@\u0006\rh!\u0002\u000f\u0001\u0001\tm\u0006c\u0001\u0010\u0003B\u001aY!1\u0019\u0001\u0011\u0002G\u0005!Q\u0019Bl\u0005=\u0019uN\\:uC:$H+\u001f9f\u0003BL7c\u0001Ba;!A!\u0011\u001aBa\r\u0003\u0011Y-A\u0003wC2,X-\u0006\u0002\u0003NB\u0019aDa4\n\t\tE'1\u001b\u0002\t\u0007>t7\u000f^1oi&\u0019!Q\u001b\u0002\u0003\u0013\r{gn\u001d;b]R\u001c\bc\u0001\u0010\u00036\"I!1\u001c\u0001C\u0002\u001b\u0005!Q\\\u0001\r\u0007>t7\u000f^1oiRK\b/Z\u000b\u0003\u0005?\u00042A\bBq\r\u001d\u0011\u0019\u000fAA\u0001\u0005K\u0014QcQ8ogR\fg\u000e\u001e+za\u0016,\u0005\u0010\u001e:bGR|'oE\u0002\u0003b*Aqa\tBq\t\u0003\u0011I\u000f\u0006\u0002\u0003`\"A\u0011\u0011 Bq\r\u0003\u0011i\u000f\u0006\u0003\u0003p\nE\b#B\u0006\u0002\u0018\n5\u0007\u0002\u0003B\u0001\u0005W\u0004\rAa6\t\u0011\t\u0015!\u0011\u001dC\u0001\u0005k$BAa>\u0003|R!!q\u001bB}\u0011!\u0011iAa=A\u0004\t=\u0001\u0002\u0003Be\u0005g\u0004\rA!4)\r\tM(Ha@@C\t\u0019\t!\u0001\u001aVg\u0016\u0004\u0003M^1mk\u0016tC\u000f]3aA=\u0014\b\u0005Y5oi\u0016\u0014h.\u00197/G>t7\u000f^1oiRK\b/\u001a1!S:\u001cH/Z1e\t\u001d\u0019)\u0001\u0001B\u0001\u0007\u000f\u0011q\u0001V=qKJ+g-E\u0002\u0018\u0007\u0013\u0011Raa\u0003\u0004\u000em3Q\u0001\b\u0001\u0001\u0007\u0013\u00012AHB\b\r-\u0019\t\u0002\u0001I\u0001$\u0003\u0019\u0019b!\b\u0003\u0015QK\b/\u001a*fM\u0006\u0003\u0018nE\u0002\u0004\u0010uAq!a\u0006\u0004\u0010\u0019\u0005!\fC\u0004\u00022\u000e=a\u0011A\u0014\t\u0011\rm1q\u0002D\u0001\u0003W\tA!\u0019:hgB\u0019ada\u0001\t\u0013\r\u0005\u0002A1A\u0007\u0002\r\r\u0012a\u0002+za\u0016\u0014VMZ\u000b\u0003\u0007K\u00012AHB\u0014\r\u001d\u0019I\u0003AA\u0001\u0007W\u0011\u0001\u0003V=qKJ+g-\u0012=ue\u0006\u001cGo\u001c:\u0014\u0007\r\u001d\"\u0002C\u0004$\u0007O!\taa\f\u0015\u0005\r\u0015\u0002\u0002CA}\u0007O1\taa\r\u0015\t\rU2Q\b\t\u0006\u0017\u0005]5q\u0007\t\b\u0017\re2\fKA\u0017\u0013\r\u0019YD\u0002\u0002\u0007)V\u0004H.Z\u001a\t\u0011\t\u00051\u0011\u0007a\u0001\u0007;A\u0001B!\u0002\u0004(\u0011\u00051\u0011\t\u000b\t\u0007\u0007\u001a9e!\u0013\u0004LQ\u00191l!\u0012\t\u0011\t51q\ba\u0002\u0005\u001fAq!a\u0006\u0004@\u0001\u00071\fC\u0004\u00022\u000e}\u0002\u0019\u0001\u0015\t\u0011\rm1q\ba\u0001\u0003[Acaa\u0010;\u0007\u001fz\u0014EAB)\u0003y)6/\u001a\u0011aS:$XM\u001d8bY:\"\u0018\u0010]3SK\u001a\u0004\u0007%\u001b8ti\u0016\fG\rB\u0004\u0004V\u0001\u0011\taa\u0016\u0003\u0019\r{W\u000e]8v]\u0012$\u0016\u0010]3\u0012\u0007]\u0019IFE\u0003\u0004\\\ru3LB\u0003\u001d\u0001\u0001\u0019I\u0006E\u0002\u001f\u0007?2\u0011b!\u0019\u0001!\u0003\r\naa\u0019\u0003\u001f\r{W\u000e]8v]\u0012$\u0016\u0010]3Ba&\u001c2aa\u0018\u000b\t\u001d\u00199\u0007\u0001B\u0001\u0007S\u00121BU3gS:,G\rV=qKF\u0019qca\u001b\u0013\r\r54qNB@\r\u0015a\u0002\u0001AB6!\rq2\u0011\u000f\u0004\f\u0007g\u0002\u0001\u0013aI\u0001\u0007k\u001aiH\u0001\bSK\u001aLg.\u001a3UsB,\u0017\t]5\u0014\u0007\rET\u0004\u0003\u0005\u0004z\rEd\u0011AA\u0016\u0003\u001d\u0001\u0018M]3oiNDa!UB9\r\u00039\u0005c\u0001\u0010\u0004fA\u0019ada\u0015\t\u0013\r\r\u0005A1A\u0007\u0002\r\u0015\u0015a\u0003*fM&tW\r\u001a+za\u0016,\"aa\"\u0011\u0007y\u0019IIB\u0004\u0004\f\u0002\t\ta!$\u0003)I+g-\u001b8fIRK\b/Z#yiJ\f7\r^8s'\r\u0019II\u0003\u0005\bG\r%E\u0011ABI)\t\u00199\t\u0003\u0005\u0002z\u000e%e\u0011ABK)\u0011\u00199j!)\u0011\u000b-\t9j!'\u0011\u000f-\u0011\u0019&!\f\u0004\u001cB\u0019ad!(\n\u0007\r}5JA\u0003TG>\u0004X\r\u0003\u0005\u0003\u0002\rM\u0005\u0019AB?\u0011!\u0011)a!#\u0005\u0002\r\u0015FCBBT\u0007W\u001bi\u000b\u0006\u0003\u0004~\r%\u0006\u0002\u0003B\u0007\u0007G\u0003\u001dAa\u0004\t\u0011\re41\u0015a\u0001\u0003[Aq!UBR\u0001\u0004\u0019Y\n\u000b\u0004\u0004$j\u001a\tlP\u0011\u0003\u0007g\u000b!%V:fA\u0001Lg\u000e^3s]\u0006dgF]3gS:,G\rV=qK\u0002\u0004\u0013N\\:uK\u0006$\u0007\u0002\u0003B\u0003\u0007\u0013#\taa.\u0015\u0011\re6QXB`\u0007\u0003$Ba! \u0004<\"A!QBB[\u0001\b\u0011y\u0001\u0003\u0005\u0004z\rU\u0006\u0019AA\u0017\u0011\u001d\t6Q\u0017a\u0001\u00077Cq!!\u0004\u00046\u0002\u0007\u0001\u0006\u000b\u0004\u00046j\u001a\tl\u0010\u0003\b\u0007\u000f\u0004!\u0011ABe\u00055\u0019E.Y:t\u0013:4w\u000eV=qKF\u0019qca3\u0013\r\r57qZB@\r\u0015a\u0002\u0001ABf!\rq2\u0011\u001b\u0004\f\u0007'\u0004\u0001\u0013aI\u0001\u0007+\u001ciN\u0001\tDY\u0006\u001c8/\u00138g_RK\b/Z!qSN\u00191\u0011[\u000f\t\u0011\re4\u0011\u001bD\u0001\u0003WAa!UBi\r\u00039\u0005B\u0002\u0018\u0004R\u001a\u0005q\u0005E\u0002\u001f\u0007\u000bD\u0011b!9\u0001\u0005\u00045\taa9\u0002\u001b\rc\u0017m]:J]\u001a|G+\u001f9f+\t\u0019)\u000fE\u0002\u001f\u0007O4qa!;\u0001\u0003\u0003\u0019YO\u0001\fDY\u0006\u001c8/\u00138g_RK\b/Z#yiJ\f7\r^8s'\r\u00199O\u0003\u0005\bG\r\u001dH\u0011ABx)\t\u0019)\u000f\u0003\u0005\u0002z\u000e\u001dh\u0011ABz)\u0011\u0019)p!?\u0011\u000b-\t9ja>\u0011\u0011-\u0019I$!\f\u0004\u001c\"B\u0001B!\u0001\u0004r\u0002\u00071Q\u001c\u0005\t\u0005\u000b\u00199\u000f\"\u0001\u0004~RA1q C\u0002\t\u000b!9\u0001\u0006\u0003\u0004^\u0012\u0005\u0001\u0002\u0003B\u0007\u0007w\u0004\u001dAa\u0004\t\u0011\re41 a\u0001\u0003[Aq!UB~\u0001\u0004\u0019Y\n\u0003\u0004/\u0007w\u0004\r\u0001\u000b\u0015\u0007\u0007wTD1B \"\u0005\u00115\u0011\u0001J+tK\u0002\u0002\u0017N\u001c;fe:\fGNL2mCN\u001c\u0018J\u001c4p)f\u0004X\r\u0019\u0011j]N$X-\u00193\u0005\u000f\u0011E\u0001A!\u0001\u0005\u0014\tQQ*\u001a;i_\u0012$\u0016\u0010]3\u0012\u0007]!)BE\u0003\u0005\u0018\u0011e1LB\u0003\u001d\u0001\u0001!)\u0002E\u0002\u001f\t711\u0002\"\b\u0001!\u0003\r\n\u0001b\b\u0005(\tiQ*\u001a;i_\u0012$\u0016\u0010]3Ba&\u001c2\u0001b\u0007\u001e\u0011\u001d!\u0019\u0003b\u0007\u0007\u0002i\fa\u0001]1sC6\u001c\bbBA$\t71\tA\u0017\t\u0004=\u0011=\u0001\"\u0003C\u0016\u0001\t\u0007i\u0011\u0001C\u0017\u0003)iU\r\u001e5pIRK\b/Z\u000b\u0003\t_\u00012A\bC\u0019\r\u001d!\u0019\u0004AA\u0001\tk\u00111#T3uQ>$G+\u001f9f\u000bb$(/Y2u_J\u001c2\u0001\"\r\u000b\u0011\u001d\u0019C\u0011\u0007C\u0001\ts!\"\u0001b\f\t\u0011\u0005eH\u0011\u0007D\u0001\t{!B\u0001b\u0010\u0005DA)1\"a&\u0005BA)1Ba\u0015|7\"A!\u0011\u0001C\u001e\u0001\u0004!9\u0003\u0003\u0005\u0003\u0006\u0011EB\u0011\u0001C$)\u0019!I\u0005\"\u0014\u0005PQ!Aq\u0005C&\u0011!\u0011i\u0001\"\u0012A\u0004\t=\u0001b\u0002C\u0012\t\u000b\u0002\ra\u001f\u0005\b\u0003\u000f\")\u00051\u0001\\Q\u0019!)E\u000fC*\u007f\u0005\u0012AQK\u0001\"+N,\u0007\u0005Y5oi\u0016\u0014h.\u00197/[\u0016$\bn\u001c3UsB,\u0007\rI5ogR,\u0017\r\u001a\u0003\b\t3\u0002!\u0011\u0001C.\u0005EqU\u000f\u001c7beflU\r\u001e5pIRK\b/Z\t\u0004/\u0011u##\u0002C0\tCZf!\u0002\u000f\u0001\u0001\u0011u\u0003c\u0001\u0010\u0005d\u0019YAQ\r\u0001\u0011\u0002G\u0005Aq\rC6\u0005QqU\u000f\u001c7beflU\r\u001e5pIRK\b/Z!qSN\u0019A1M\u000f\t\u000f\u0005\u001dC1\rD\u00015B\u0019a\u0004b\u0016\t\u0013\u0011=\u0004A1A\u0007\u0002\u0011E\u0014!\u0005(vY2\f'/_'fi\"|G\rV=qKV\u0011A1\u000f\t\u0004=\u0011Uda\u0002C<\u0001\u0005\u0005A\u0011\u0010\u0002\u001b\u001dVdG.\u0019:z\u001b\u0016$\bn\u001c3UsB,W\t\u001f;sC\u000e$xN]\n\u0004\tkR\u0001bB\u0012\u0005v\u0011\u0005AQ\u0010\u000b\u0003\tgB\u0001\"!?\u0005v\u0019\u0005A\u0011\u0011\u000b\u0005\u0003+#\u0019\t\u0003\u0005\u0003\u0002\u0011}\u0004\u0019\u0001C6\u0011!\u0011)\u0001\"\u001e\u0005\u0002\u0011\u001dE\u0003\u0002CE\t\u001b#B\u0001b\u001b\u0005\f\"A!Q\u0002CC\u0001\b\u0011y\u0001C\u0004\u0002H\u0011\u0015\u0005\u0019A.)\r\u0011\u0015%\b\"%@C\t!\u0019*\u0001\u0015Vg\u0016\u0004\u0003-\u001b8uKJt\u0017\r\u001c\u0018ok2d\u0017M]=NKRDw\u000e\u001a+za\u0016\u0004\u0007%\u001b8ti\u0016\fG\rB\u0004\u0005\u0018\u0002\u0011\t\u0001\"'\u0003\u0011A{G.\u001f+za\u0016\f2a\u0006CN%\u0015!i\nb(\\\r\u0015a\u0002\u0001\u0001CN!\rqB\u0011\u0015\u0004\f\tG\u0003\u0001\u0013aI\u0001\tK#YKA\u0006Q_2LH+\u001f9f\u0003BL7c\u0001CQ;!9\u00111\tCQ\r\u0003Q\bbBA$\tC3\tA\u0017\t\u0004=\u0011U\u0005\"\u0003CX\u0001\t\u0007i\u0011\u0001CY\u0003!\u0001v\u000e\\=UsB,WC\u0001CZ!\rqBQ\u0017\u0004\b\to\u0003\u0011\u0011\u0001C]\u0005E\u0001v\u000e\\=UsB,W\t\u001f;sC\u000e$xN]\n\u0004\tkS\u0001bB\u0012\u00056\u0012\u0005AQ\u0018\u000b\u0003\tgC\u0001\"!?\u00056\u001a\u0005A\u0011\u0019\u000b\u0005\t\u007f!\u0019\r\u0003\u0005\u0003\u0002\u0011}\u0006\u0019\u0001CV\u0011!\u0011)\u0001\".\u0005\u0002\u0011\u001dGC\u0002Ce\t\u001b$y\r\u0006\u0003\u0005,\u0012-\u0007\u0002\u0003B\u0007\t\u000b\u0004\u001dAa\u0004\t\u000f\u0005\rCQ\u0019a\u0001w\"9\u0011q\tCc\u0001\u0004Y\u0006F\u0002Ccu\u0011Mw(\t\u0002\u0005V\u0006yRk]3!A&tG/\u001a:oC2t\u0003o\u001c7z)f\u0004X\r\u0019\u0011j]N$X-\u00193\u0005\u000f\u0011e\u0007A!\u0001\u0005\\\nyQ\t_5ti\u0016tG/[1m)f\u0004X-E\u0002\u0018\t;\u0014R\u0001b8\u0005bn3Q\u0001\b\u0001\u0001\t;\u00042A\bCr\r-!)\u000f\u0001I\u0001$\u0003!9\u000f\"=\u0003%\u0015C\u0018n\u001d;f]RL\u0017\r\u001c+za\u0016\f\u0005/[\n\u0004\tGl\u0002b\u0002Cv\tG4\tA_\u0001\u000bcV\fg\u000e^5gS\u0016$\u0007b\u0002Cx\tG4\tAW\u0001\u000bk:$WM\u001d7zS:<\u0007c\u0001\u0010\u0005X\"IAQ\u001f\u0001C\u0002\u001b\u0005Aq_\u0001\u0010\u000bbL7\u000f^3oi&\fG\u000eV=qKV\u0011A\u0011 \t\u0004=\u0011mha\u0002C\u007f\u0001\u0005\u0005Aq \u0002\u0019\u000bbL7\u000f^3oi&\fG\u000eV=qK\u0016CHO]1di>\u00148c\u0001C~\u0015!91\u0005b?\u0005\u0002\u0015\rAC\u0001C}\u0011!\tI\u0010b?\u0007\u0002\u0015\u001dA\u0003\u0002C \u000b\u0013A\u0001B!\u0001\u0006\u0006\u0001\u0007A\u0011\u001f\u0005\t\u0005\u000b!Y\u0010\"\u0001\u0006\u000eQ1QqBC\n\u000b+!B\u0001\"=\u0006\u0012!A!QBC\u0006\u0001\b\u0011y\u0001C\u0004\u0005l\u0016-\u0001\u0019A>\t\u000f\u0011=X1\u0002a\u00017\"2Q1\u0002\u001e\u0006\u001a}\n#!b\u0007\u0002MU\u001bX\r\t1j]R,'O\\1m]\u0015D\u0018n\u001d;f]RL\u0017\r\u001c+za\u0016\u0004\u0007%\u001b8ti\u0016\fG\rB\u0004\u0006 \u0001\u0011\t!\"\t\u0003\u001b\u0005sgn\u001c;bi\u0016$G+\u001f9f#\r9R1\u0005\n\u0006\u000bK)9c\u0017\u0004\u00069\u0001\u0001Q1\u0005\t\u0004=\u0015%baCC\u0016\u0001A\u0005\u0019\u0013AC\u0017\u000b\u0007\u0012\u0001#\u00118o_R\fG/\u001a3UsB,\u0017\t]5\u0014\u0007\u0015%R\u0004\u0003\u0005\u00062\u0015%b\u0011AC\u001a\u0003-\tgN\\8uCRLwN\\:\u0016\u0005\u0015U\u0002\u0003\u0002?\u0000\u000bo\u00012AHC\u001d\u0013\u0011)Y$\"\u0010\u0003\u0015\u0005sgn\u001c;bi&|g.C\u0002\u0006@\t\u00111\"\u00118o_R\fG/[8og\"9Aq^C\u0015\r\u0003Q\u0006c\u0001\u0010\u0006\u001e!IQq\t\u0001C\u0002\u001b\u0005Q\u0011J\u0001\u000e\u0003:tw\u000e^1uK\u0012$\u0016\u0010]3\u0016\u0005\u0015-\u0003c\u0001\u0010\u0006N\u00199Qq\n\u0001\u0002\u0002\u0015E#AF!o]>$\u0018\r^3e)f\u0004X-\u0012=ue\u0006\u001cGo\u001c:\u0014\u0007\u00155#\u0002C\u0004$\u000b\u001b\"\t!\"\u0016\u0015\u0005\u0015-\u0003\u0002CA}\u000b\u001b2\t!\"\u0017\u0015\t\u0015mSq\f\t\u0006\u0017\u0005]UQ\f\t\u0007\u0017\tMSQG.\t\u0011\t\u0005Qq\u000ba\u0001\u000b\u0007B\u0001B!\u0002\u0006N\u0011\u0005Q1\r\u000b\u0007\u000bK*I'b\u001b\u0015\t\u0015\rSq\r\u0005\t\u0005\u001b)\t\u0007q\u0001\u0003\u0010!AQ\u0011GC1\u0001\u0004))\u0004C\u0004\u0005p\u0016\u0005\u0004\u0019A.)\r\u0015\u0005$(b\u001c@C\t)\t(\u0001\u0013Vg\u0016\u0004\u0003-\u001b8uKJt\u0017\r\u001c\u0018b]:|G/\u0019;fIRK\b/\u001a1!S:\u001cH/Z1e\t\u001d))\b\u0001B\u0001\u000bo\u0012!\u0002V=qK\n{WO\u001c3t#\r9R\u0011\u0010\n\u0006\u000bw*ih\u0017\u0004\u00069\u0001\u0001Q\u0011\u0010\t\u0004=\u0015}daCCA\u0001A\u0005\u0019\u0013ACB\u000b\u001b\u0013Q\u0002V=qK\n{WO\u001c3t\u0003BL7cAC@;!9QqQC@\r\u0003Q\u0016A\u00017p\u0011\u001d)Y)b \u0007\u0002i\u000b!\u0001[5\u0011\u0007y)\u0019\bC\u0005\u0006\u0012\u0002\u0011\rQ\"\u0001\u0006\u0014\u0006QA+\u001f9f\u0005>,h\u000eZ:\u0016\u0005\u0015U\u0005c\u0001\u0010\u0006\u0018\u001a9Q\u0011\u0014\u0001\u0002\u0002\u0015m%a\u0005+za\u0016\u0014u.\u001e8eg\u0016CHO]1di>\u00148cACL\u0015!91%b&\u0005\u0002\u0015}ECACK\u0011!\tI0b&\u0007\u0002\u0015\rF\u0003\u0002BO\u000bKC\u0001B!\u0001\u0006\"\u0002\u0007QQ\u0012\u0005\t\u0005\u000b)9\n\"\u0001\u0006*R1Q1VCX\u000bc#B!\"$\u0006.\"A!QBCT\u0001\b\u0011y\u0001C\u0004\u0006\b\u0016\u001d\u0006\u0019A.\t\u000f\u0015-Uq\u0015a\u00017\"2Qq\u0015\u001e\u00066~\n#!b.\u0002CU\u001bX\r\t1j]R,'O\\1m]QL\b/\u001a\"pk:$7\u000f\u0019\u0011j]N$X-\u00193\t\u0011\u0015m\u0006A1A\u0007\u0002i\u000bAbV5mI\u000e\f'\u000f\u001a+za\u0016$q!b0\u0001\u0005\u0003)\tMA\nC_VtG-\u001a3XS2$7-\u0019:e)f\u0004X-E\u0002\u0018\u000b\u0007\u0014R!\"2\u0006Hn3Q\u0001\b\u0001\u0001\u000b\u0007\u00042AHCe\r-)Y\r\u0001I\u0001$\u0003)i-\"6\u0003-\t{WO\u001c3fI^KG\u000eZ2be\u0012$\u0016\u0010]3Ba&\u001c2!\"3\u001e\u0011!)\t.\"3\u0007\u0002\u0015M\u0017A\u00022pk:$7/\u0006\u0002\u0006\u000eB\u0019a$\"0\t\u0013\u0015e\u0007A1A\u0007\u0002\u0015m\u0017a\u0005\"pk:$W\rZ,jY\u0012\u001c\u0017M\u001d3UsB,WCACo!\rqRq\u001c\u0004\b\u000bC\u0004\u0011\u0011ACr\u0005q\u0011u.\u001e8eK\u0012<\u0016\u000e\u001c3dCJ$G+\u001f9f\u000bb$(/Y2u_J\u001c2!b8\u000b\u0011\u001d\u0019Sq\u001cC\u0001\u000bO$\"!\"8\t\u0011\u0005eXq\u001cD\u0001\u000bW$B!\"<\u0006pB)1\"a&\u0006\u000e\"A!\u0011ACu\u0001\u0004))\u000e\u0003\u0005\u0003\u0006\u0015}G\u0011ACz)\u0011))0\"?\u0015\t\u0015UWq\u001f\u0005\t\u0005\u001b)\t\u0010q\u0001\u0003\u0010!AQ\u0011[Cy\u0001\u0004)i\t\u000b\u0004\u0006rj*ipP\u0011\u0003\u000b\u007f\f!&V:fA\u0001Lg\u000e^3s]\u0006dgFY8v]\u0012,GmV5mI\u000e\f'\u000f\u001a+za\u0016\u0004\u0007%\u001b8ti\u0016\fG\rC\u0004\u0007\u0004\u00011\tA\"\u0002\u0002\u00071,(\rF\u0002\\\r\u000fA\u0001B\"\u0003\u0007\u0002\u0001\u0007\u0011QF\u0001\u0003qNDqA\"\u0004\u0001\r\u00031y!A\u0002hY\n$2a\u0017D\t\u0011!1\u0019Bb\u0003A\u0002\u00055\u0012A\u0001;t\u0011\u001d19\u0002\u0001D\u0001\r3\t1\"\u00199qY&,G\rV=qKR)1Lb\u0007\u0007 !9aQ\u0004D\u000b\u0001\u0004Y\u0016!\u0002;zG>t\u0007\u0002CB\u000e\r+\u0001\r!!\f\t\u000f\u0019]\u0001A\"\u0001\u0007$Q)1L\"\n\u0007(!9aQ\u0004D\u0011\u0001\u0004Y\u0006\u0002CB\u000e\rC\u0001\rA\"\u000b\u0011\t-1YcW\u0005\u0004\r[1!A\u0003\u001fsKB,\u0017\r^3e}!9aq\u0003\u0001\u0007\u0002\u0019EB#B.\u00074\u0019U\u0002bBAY\r_\u0001\r\u0001\u000b\u0005\t\u000771y\u00031\u0001\u0002.!9aq\u0003\u0001\u0007\u0002\u0019eB#B.\u0007<\u0019u\u0002bBAY\ro\u0001\r\u0001\u000b\u0005\t\u0007719\u00041\u0001\u0007*A!a\u0011\tD\"\u001b\u0005\u0011\u0011b\u0001D#\u0005\tAQK\\5wKJ\u001cX\r")
public interface Types {
    public TypeApi NoType();

    public TypeApi NoPrefix();

    public ThisTypeExtractor ThisType();

    public SingleTypeExtractor SingleType();

    public SuperTypeExtractor SuperType();

    public ConstantTypeExtractor ConstantType();

    public TypeRefExtractor TypeRef();

    public RefinedTypeExtractor RefinedType();

    public ClassInfoTypeExtractor ClassInfoType();

    public MethodTypeExtractor MethodType();

    public NullaryMethodTypeExtractor NullaryMethodType();

    public PolyTypeExtractor PolyType();

    public ExistentialTypeExtractor ExistentialType();

    public AnnotatedTypeExtractor AnnotatedType();

    public TypeBoundsExtractor TypeBounds();

    public TypeApi WildcardType();

    public BoundedWildcardTypeExtractor BoundedWildcardType();

    public TypeApi lub(List<TypeApi> var1);

    public TypeApi glb(List<TypeApi> var1);

    public TypeApi appliedType(TypeApi var1, List<TypeApi> var2);

    public TypeApi appliedType(TypeApi var1, Seq<TypeApi> var2);

    public TypeApi appliedType(Symbols.SymbolApi var1, List<TypeApi> var2);

    public TypeApi appliedType(Symbols.SymbolApi var1, Seq<TypeApi> var2);

    public static abstract class TypeApi {
        public final /* synthetic */ Universe $outer;

        public abstract Symbols.SymbolApi termSymbol();

        public abstract Symbols.SymbolApi typeSymbol();

        public abstract Symbols.SymbolApi declaration(Names.NameApi var1);

        public abstract Symbols.SymbolApi decl(Names.NameApi var1);

        public abstract Scopes.MemberScopeApi declarations();

        public abstract Scopes.MemberScopeApi decls();

        public abstract Symbols.SymbolApi member(Names.NameApi var1);

        public abstract Scopes.MemberScopeApi members();

        public abstract TypeApi companion();

        public abstract boolean takesTypeArgs();

        public abstract TypeApi typeConstructor();

        public abstract TypeApi normalize();

        public abstract TypeApi etaExpand();

        public abstract boolean $less$colon$less(TypeApi var1);

        public abstract boolean weak_$less$colon$less(TypeApi var1);

        public abstract boolean $eq$colon$eq(TypeApi var1);

        public abstract List<Symbols.SymbolApi> baseClasses();

        public abstract TypeApi baseType(Symbols.SymbolApi var1);

        public abstract TypeApi asSeenFrom(TypeApi var1, Symbols.SymbolApi var2);

        public abstract TypeApi erasure();

        public abstract TypeApi widen();

        public abstract TypeApi dealias();

        public abstract List<TypeApi> typeArgs();

        public abstract List<List<Symbols.SymbolApi>> paramss();

        public abstract List<List<Symbols.SymbolApi>> paramLists();

        public abstract List<Symbols.SymbolApi> typeParams();

        public abstract TypeApi resultType();

        public abstract TypeApi finalResultType();

        public abstract TypeApi orElse(Function0<TypeApi> var1);

        public abstract TypeApi substituteSymbols(List<Symbols.SymbolApi> var1, List<Symbols.SymbolApi> var2);

        public abstract TypeApi substituteTypes(List<Symbols.SymbolApi> var1, List<TypeApi> var2);

        public abstract TypeApi map(Function1<TypeApi, TypeApi> var1);

        public abstract void foreach(Function1<TypeApi, BoxedUnit> var1);

        public abstract Option<TypeApi> find(Function1<TypeApi, Object> var1);

        public abstract boolean exists(Function1<TypeApi, Object> var1);

        public abstract boolean contains(Symbols.SymbolApi var1);

        public /* synthetic */ Universe scala$reflect$api$Types$TypeApi$$$outer() {
            return this.$outer;
        }

        public TypeApi(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public interface TypeRefApi {
        public TypeApi pre();

        public Symbols.SymbolApi sym();

        public List<TypeApi> args();
    }

    public interface ThisTypeApi {
        public Symbols.SymbolApi sym();
    }

    public interface PolyTypeApi {
        public List<Symbols.SymbolApi> typeParams();

        public TypeApi resultType();
    }

    public interface SuperTypeApi {
        public TypeApi thistpe();

        public TypeApi supertpe();
    }

    public interface SingleTypeApi {
        public TypeApi pre();

        public Symbols.SymbolApi sym();
    }

    public interface MethodTypeApi {
        public List<Symbols.SymbolApi> params();

        public TypeApi resultType();
    }

    public interface TypeBoundsApi {
        public TypeApi lo();

        public TypeApi hi();
    }

    public interface RefinedTypeApi {
        public List<TypeApi> parents();

        public Scopes.MemberScopeApi decls();
    }

    public interface ConstantTypeApi {
        public Constants.ConstantApi value();
    }

    public interface CompoundTypeApi {
    }

    public static abstract class TypeRefExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract Option<Tuple3<TypeApi, Symbols.SymbolApi, List<TypeApi>>> unapply(TypeRefApi var1);

        public TypeApi apply(TypeApi pre, Symbols.SymbolApi sym, List<TypeApi> args, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$TypeRefExtractor$$$outer().internal().typeRef(pre, sym, args);
        }

        public /* synthetic */ Universe scala$reflect$api$Types$TypeRefExtractor$$$outer() {
            return this.$outer;
        }

        public TypeRefExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public interface SingletonTypeApi {
    }

    public interface ClassInfoTypeApi {
        public List<TypeApi> parents();

        public Scopes.MemberScopeApi decls();

        public Symbols.SymbolApi typeSymbol();
    }

    public interface AnnotatedTypeApi {
        public List<Annotations.AnnotationApi> annotations();

        public TypeApi underlying();
    }

    public static abstract class ThisTypeExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract Option<Symbols.SymbolApi> unapply(ThisTypeApi var1);

        public TypeApi apply(Symbols.SymbolApi sym, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$ThisTypeExtractor$$$outer().internal().thisType(sym);
        }

        public /* synthetic */ Universe scala$reflect$api$Types$ThisTypeExtractor$$$outer() {
            return this.$outer;
        }

        public ThisTypeExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static abstract class PolyTypeExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract Option<Tuple2<List<Symbols.SymbolApi>, TypeApi>> unapply(PolyTypeApi var1);

        public PolyTypeApi apply(List<Symbols.SymbolApi> typeParams2, TypeApi resultType, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$PolyTypeExtractor$$$outer().internal().polyType(typeParams2, resultType);
        }

        public /* synthetic */ Universe scala$reflect$api$Types$PolyTypeExtractor$$$outer() {
            return this.$outer;
        }

        public PolyTypeExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static abstract class SuperTypeExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract Option<Tuple2<TypeApi, TypeApi>> unapply(SuperTypeApi var1);

        public TypeApi apply(TypeApi thistpe, TypeApi supertpe, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$SuperTypeExtractor$$$outer().internal().superType(thistpe, supertpe);
        }

        public /* synthetic */ Universe scala$reflect$api$Types$SuperTypeExtractor$$$outer() {
            return this.$outer;
        }

        public SuperTypeExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public interface ExistentialTypeApi {
        public List<Symbols.SymbolApi> quantified();

        public TypeApi underlying();
    }

    public static abstract class SingleTypeExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract Option<Tuple2<TypeApi, Symbols.SymbolApi>> unapply(SingleTypeApi var1);

        public TypeApi apply(TypeApi pre, Symbols.SymbolApi sym, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$SingleTypeExtractor$$$outer().internal().singleType(pre, sym);
        }

        public /* synthetic */ Universe scala$reflect$api$Types$SingleTypeExtractor$$$outer() {
            return this.$outer;
        }

        public SingleTypeExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static abstract class MethodTypeExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract Option<Tuple2<List<Symbols.SymbolApi>, TypeApi>> unapply(MethodTypeApi var1);

        public MethodTypeApi apply(List<Symbols.SymbolApi> params2, TypeApi resultType, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$MethodTypeExtractor$$$outer().internal().methodType(params2, resultType);
        }

        public /* synthetic */ Universe scala$reflect$api$Types$MethodTypeExtractor$$$outer() {
            return this.$outer;
        }

        public MethodTypeExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static abstract class TypeBoundsExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract Option<Tuple2<TypeApi, TypeApi>> unapply(TypeBoundsApi var1);

        public TypeBoundsApi apply(TypeApi lo, TypeApi hi, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$TypeBoundsExtractor$$$outer().internal().typeBounds(lo, hi);
        }

        public /* synthetic */ Universe scala$reflect$api$Types$TypeBoundsExtractor$$$outer() {
            return this.$outer;
        }

        public TypeBoundsExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static abstract class RefinedTypeExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract Option<Tuple2<List<TypeApi>, Scopes.ScopeApi>> unapply(RefinedTypeApi var1);

        public RefinedTypeApi apply(List<TypeApi> parents2, Scopes.ScopeApi decls, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$RefinedTypeExtractor$$$outer().internal().refinedType(parents2, decls);
        }

        public RefinedTypeApi apply(List<TypeApi> parents2, Scopes.ScopeApi decls, Symbols.SymbolApi clazz, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$RefinedTypeExtractor$$$outer().internal().refinedType(parents2, decls, clazz);
        }

        public /* synthetic */ Universe scala$reflect$api$Types$RefinedTypeExtractor$$$outer() {
            return this.$outer;
        }

        public RefinedTypeExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public interface NullaryMethodTypeApi {
        public TypeApi resultType();
    }

    public static abstract class ConstantTypeExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract Option<Constants.ConstantApi> unapply(ConstantTypeApi var1);

        public ConstantTypeApi apply(Constants.ConstantApi value, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$ConstantTypeExtractor$$$outer().internal().constantType(value);
        }

        public /* synthetic */ Universe scala$reflect$api$Types$ConstantTypeExtractor$$$outer() {
            return this.$outer;
        }

        public ConstantTypeExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static abstract class ClassInfoTypeExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract Option<Tuple3<List<TypeApi>, Scopes.ScopeApi, Symbols.SymbolApi>> unapply(ClassInfoTypeApi var1);

        public ClassInfoTypeApi apply(List<TypeApi> parents2, Scopes.ScopeApi decls, Symbols.SymbolApi typeSymbol2, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$ClassInfoTypeExtractor$$$outer().internal().classInfoType(parents2, decls, typeSymbol2);
        }

        public /* synthetic */ Universe scala$reflect$api$Types$ClassInfoTypeExtractor$$$outer() {
            return this.$outer;
        }

        public ClassInfoTypeExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static abstract class AnnotatedTypeExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract Option<Tuple2<List<Annotations.AnnotationApi>, TypeApi>> unapply(AnnotatedTypeApi var1);

        public AnnotatedTypeApi apply(List<Annotations.AnnotationApi> annotations, TypeApi underlying, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$AnnotatedTypeExtractor$$$outer().internal().annotatedType(annotations, underlying);
        }

        public /* synthetic */ Universe scala$reflect$api$Types$AnnotatedTypeExtractor$$$outer() {
            return this.$outer;
        }

        public AnnotatedTypeExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public interface BoundedWildcardTypeApi {
        public TypeBoundsApi bounds();
    }

    public static abstract class ExistentialTypeExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract Option<Tuple2<List<Symbols.SymbolApi>, TypeApi>> unapply(ExistentialTypeApi var1);

        public ExistentialTypeApi apply(List<Symbols.SymbolApi> quantified, TypeApi underlying, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$ExistentialTypeExtractor$$$outer().internal().existentialType(quantified, underlying);
        }

        public /* synthetic */ Universe scala$reflect$api$Types$ExistentialTypeExtractor$$$outer() {
            return this.$outer;
        }

        public ExistentialTypeExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static abstract class NullaryMethodTypeExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract Option<TypeApi> unapply(NullaryMethodTypeApi var1);

        public NullaryMethodTypeApi apply(TypeApi resultType, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$NullaryMethodTypeExtractor$$$outer().internal().nullaryMethodType(resultType);
        }

        public /* synthetic */ Universe scala$reflect$api$Types$NullaryMethodTypeExtractor$$$outer() {
            return this.$outer;
        }

        public NullaryMethodTypeExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static abstract class BoundedWildcardTypeExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract Option<TypeBoundsApi> unapply(BoundedWildcardTypeApi var1);

        public BoundedWildcardTypeApi apply(TypeBoundsApi bounds, Internals.CompatToken token) {
            return this.scala$reflect$api$Types$BoundedWildcardTypeExtractor$$$outer().internal().boundedWildcardType(bounds);
        }

        public /* synthetic */ Universe scala$reflect$api$Types$BoundedWildcardTypeExtractor$$$outer() {
            return this.$outer;
        }

        public BoundedWildcardTypeExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }
}

