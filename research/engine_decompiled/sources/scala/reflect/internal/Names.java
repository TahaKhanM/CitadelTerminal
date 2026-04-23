/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function1;
import scala.Predef$;
import scala.StringContext;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.mutable.StringBuilder;
import scala.compat.Platform$;
import scala.reflect.ClassTag;
import scala.reflect.NameTransformer$;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Names;
import scala.reflect.internal.Names$TermName$;
import scala.reflect.internal.Names$TypeName$;
import scala.reflect.internal.Names$class;
import scala.runtime.BoxesRunTime;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u0015-haB\u0001\u0003!\u0003\r\t!\u0003\u0002\u0006\u001d\u0006lWm\u001d\u0006\u0003\u0007\u0011\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u00192\u0001\u0001\u0006\u000f!\tYA\"D\u0001\u0007\u0013\tiaA\u0001\u0004B]f\u0014VM\u001a\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0003#\u0011\t1!\u00199j\u0013\t\t\u0001\u0003C\u0003\u0015\u0001\u0011\u0005Q#\u0001\u0004%S:LG\u000f\n\u000b\u0002-A\u00111bF\u0005\u00031\u0019\u0011A!\u00168ji\"9!\u0004\u0001b\u0001\n\u001bY\u0012!\u0003%B'\"{6+\u0013.F+\u0005ar\"A\u000f\u001e\u0007\u0001\u0001\t\u0001\u0003\u0004 \u0001\u0001\u0006i\u0001H\u0001\u000b\u0011\u0006\u001b\u0006jX*J5\u0016\u0003\u0003bB\u0011\u0001\u0005\u0004%iAI\u0001\n\u0011\u0006\u001b\u0006jX'B'.+\u0012aI\b\u0002Iu\u0011qp@\u0005\u0007M\u0001\u0001\u000bQB\u0012\u0002\u0015!\u000b5\u000bS0N\u0003N[\u0005\u0005C\u0004)\u0001\t\u0007IQB\u0015\u0002\u00139\u000bU*R0T\u0013j+U#\u0001\u0016\u0010\u0003-j2A\u0001\u0001\u0001\u0011\u0019i\u0003\u0001)A\u0007U\u0005Qa*Q'F?NK%,\u0012\u0011\t\u000f=\u0002!\u0019!C\u0003a\u0005Ia.Y7f\t\u0016\u0014WoZ\u000b\u0002c=\t!'G\u0001\u0001\u0011\u0019!\u0004\u0001)A\u0007c\u0005Qa.Y7f\t\u0016\u0014Wo\u001a\u0011\t\u000bY\u0002A\u0011C\u001c\u0002!MLhn\u00195s_:L'0\u001a(b[\u0016\u001cX#\u0001\u001d\u0011\u0005-I\u0014B\u0001\u001e\u0007\u0005\u001d\u0011un\u001c7fC:Dq\u0001\u0010\u0001C\u0002\u0013%Q(\u0001\u0005oC6,Gj\\2l+\u0005q\u0004CA E\u001b\u0005\u0001%BA!C\u0003\u0011a\u0017M\\4\u000b\u0003\r\u000bAA[1wC&\u0011Q\t\u0011\u0002\u0007\u001f\nTWm\u0019;\t\r\u001d\u0003\u0001\u0015!\u0003?\u0003%q\u0017-\\3M_\u000e\\\u0007\u0005C\u0004J\u0001\u0001\u0007I\u0011\u0001&\u0002\t\rD'o]\u000b\u0002\u0017B\u00191\u0002\u0014(\n\u000553!!B!se\u0006L\bCA\u0006P\u0013\t\u0001fA\u0001\u0003DQ\u0006\u0014\bb\u0002*\u0001\u0001\u0004%\taU\u0001\tG\"\u00148o\u0018\u0013fcR\u0011a\u0003\u0016\u0005\b+F\u000b\t\u00111\u0001L\u0003\rAH%\r\u0005\u0007/\u0002\u0001\u000b\u0015B&\u0002\u000b\rD'o\u001d\u0011\t\u000fe\u0003\u0001\u0019!C\u00055\u0006\u0011anY\u000b\u00027B\u00111\u0002X\u0005\u0003;\u001a\u00111!\u00138u\u0011\u001dy\u0006\u00011A\u0005\n\u0001\faA\\2`I\u0015\fHC\u0001\fb\u0011\u001d)f,!AA\u0002mCaa\u0019\u0001!B\u0013Y\u0016a\u00018dA!9Q\r\u0001b\u0001\n\u00131\u0017!\u0004;fe6D\u0015m\u001d5uC\ndW-F\u0001h!\rYA\n\u001b\t\u0003S*l\u0011\u0001\u0001\u0004\u0006W\u0002\t\t\u0003\u001c\u0002\t)\u0016\u0014XNT1nKN!!.\\BP!\tIgNB\u0003p\u0001\u0005\u0005\u0002O\u0001\u0003OC6,7C\u00018r!\tI'/\u0003\u0002t%\t9a*Y7f\u0003BL\u0007\u0002C;o\u0005\u000b\u0007I\u0011\u0003.\u0002\u000b%tG-\u001a=\t\u0011]t'\u0011!Q\u0001\nm\u000ba!\u001b8eKb\u0004\u0003\u0002C=o\u0005\u000b\u0007I\u0011\u0003.\u0002\u00071,g\u000e\u0003\u0005|]\n\u0005\t\u0015!\u0003\\\u0003\u0011aWM\u001c\u0011\t\u000butG\u0011\u0001@\u0002\rqJg.\u001b;?)\u0011iw0!\u0001\t\u000bUd\b\u0019A.\t\u000bed\b\u0019A.\u0005\u000f\u0005\u0015aN!\u0001\u0002\b\taA\u000b[5t\u001d\u0006lW\rV=qKF\u0019\u0011\u0011B7\u0011\u0007-\tY!C\u0002\u0002\u000e\u0019\u0011AAT;mY\"A\u0011\u0011\u00038!\u000e#\t\u0019\"\u0001\u0005uQ&\u001ch*Y7f+\t\t)\u0002\u0005\u0003\u0002\u0018\u0005\rQ\"\u00018\t\r\u0005ma\u000e\"\u0001[\u0003\u0015\u0019H/\u0019:u\u0011\u001d\tyB\u001cD\u0001\u0003C\tAA\\3yiV\u0011\u00111\u0005\n\u0006\u0003Ki\u0017Q\u0003\u0004\u0007\u0003Oq\u0007!a\t\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \t\r\u0005-b\u000e\"\u0002[\u0003\u0019aWM\\4uQ\"1\u0011q\u00068\u0005\u0006]\nq![:F[B$\u0018\u0010\u0003\u0004\u000249$)aN\u0001\t]>tW)\u001c9us\"9\u0011q\u00078\u0007\u0002\u0005e\u0012\u0001\u00038b[\u0016\\\u0015N\u001c3\u0016\u0005\u0005m\u0002\u0003BA\u001f\u0003\u0007r1aCA \u0013\r\t\tEB\u0001\u0007!J,G-\u001a4\n\t\u0005\u0015\u0013q\t\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005\u0005c\u0001\u0003\u0004\u0002L94\taN\u0001\u000bSN$VM]7OC6,\u0007BBA(]\u001a\u0005q'\u0001\u0006jgRK\b/\u001a(b[\u0016Dq!a\u0015o\r\u0003\t)&\u0001\u0006u_R+'/\u001c(b[\u0016,\u0012\u0001\u001b\u0005\b\u00033rg\u0011AA.\u0003)!x\u000eV=qK:\u000bW.Z\u000b\u0003\u0003;\u00022![A0\r\u001d\t\t\u0007AA\u0011\u0003G\u0012\u0001\u0002V=qK:\u000bW.Z\n\u0006\u0003?j\u0017Q\r\t\u0004S\u0006\u001d\u0014bAA5%\tYA+\u001f9f\u001d\u0006lW-\u00119j\u0011-\ti'a\u0018\u0003\u0002\u0003\u0006Ia\u0017;\u0002\r%tG-\u001a=1\u0011-\t\t(a\u0018\u0003\u0002\u0003\u0006Ia\u0017=\u0002\t1,g\u000e\r\u0005\f\u0003?\tyF!b\u0001\n\u0003\tY\u0006C\u0006\u0002x\u0005}#\u0011!Q\u0001\n\u0005u\u0013!\u00028fqR\u0004\u0003bB?\u0002`\u0011\u0005\u00111\u0010\u000b\t\u0003;\ni(a \u0002\u0002\"9\u0011QNA=\u0001\u0004Y\u0006bBA9\u0003s\u0002\ra\u0017\u0005\t\u0003?\tI\b1\u0001\u0002^\u00159\u0011QAA0\u0001\u0005u\u0003\"CA\t\u0003?\u0002K\u0011CA.\u0011\u001d\tY%a\u0018\u0005\u0002]Bq!a\u0014\u0002`\u0011\u0005q\u0007\u0003\u0005\u0002T\u0005}C\u0011AA+\u0011!\tI&a\u0018\u0005\u0002\u0005m\u0003\u0002CAI\u0003?\"\t!a%\u0002\u000f9,wOT1nKR!\u0011QLAK\u0011!\t9*a$A\u0002\u0005m\u0012aA:ue\"A\u00111TA0\t\u0003\t)&A\u0007d_6\u0004\u0018M\\5p]:\u000bW.\u001a\u0005\t\u0003?\u000by\u0006\"\u0001\u0002\"\u000691/\u001e2OC6,GCBA/\u0003G\u000b9\u000bC\u0004\u0002&\u0006u\u0005\u0019A.\u0002\t\u0019\u0014x.\u001c\u0005\b\u0003S\u000bi\n1\u0001\\\u0003\t!x\u000e\u0003\u0005\u00028\u0005}C\u0011AAW+\t\ty\u000bE\u0002@\u0003cK1!!\u0012A\u0011!\t),a\u0018\u0005B\u0005e\u0012A\u00023fG>$W-\u000b\u0004\u0002`\u0005e\u0016\u0011\u001c\u0004\u0007\u0003w\u0003a!!0\u0003\u0015QK\b/\u001a(b[\u0016|&k\u0005\u0003\u0002:\u0006u\u0003BCA7\u0003s\u0013\t\u0011)A\u00057\"Q\u0011\u0011OA]\u0005\u0003\u0005\u000b\u0011B.\t\u001b\u0005\u0015\u0017\u0011\u0018B\u0001B\u0003%\u0011QLA:\u0003\u0015qW\r\u001f;1\u0011\u001di\u0018\u0011\u0018C\u0001\u0003\u0013$\u0002\"a3\u0002N\u0006=\u0017\u0011\u001b\t\u0004S\u0006e\u0006bBA7\u0003\u000f\u0004\ra\u0017\u0005\b\u0003c\n9\r1\u0001\\\u0011!\t)-a2A\u0002\u0005u\u0003\u0002CAk\u0003s#\t%a6\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a,\u0007\r\u0005m\u0007ABAo\u0005)!\u0016\u0010]3OC6,wlU\n\u0005\u00033\fi\u0006\u0003\u0006\u0002n\u0005e'\u0011!Q\u0001\nmC!\"!\u001d\u0002Z\n\u0005\t\u0015!\u0003\\\u00115\t)-!7\u0003\u0002\u0003\u0006I!!\u0018\u0002t!Y\u0011Q[Am\u0005\u000b\u0007I\u0011IA\u001d\u0011-\tI/!7\u0003\u0002\u0003\u0006I!a\u000f\u0002\u0013Q|7\u000b\u001e:j]\u001e\u0004\u0003bB?\u0002Z\u0012\u0005\u0011Q\u001e\u000b\u000b\u0003_\f\t0a=\u0002v\u0006]\bcA5\u0002Z\"9\u0011QNAv\u0001\u0004Y\u0006bBA9\u0003W\u0004\ra\u0017\u0005\t\u0003\u000b\fY\u000f1\u0001\u0002^!A\u0011Q[Av\u0001\u0004\tY\u0004\u0003\u0005\u0002\u0012\u0006eG\u0011IA~)\u0011\ti&!@\t\u0011\u0005]\u0015\u0011 a\u0001\u0003wAq!a'o\r\u0003\u0011\t!F\u0001n\u0011\u001d\u0011)A\u001cC\u0001\u0005\u000f\t\u0011BY8uQ:\u000bW.Z:\u0016\u0005\t%\u0001#\u0002B\u0006\u0005#igbA\u0006\u0003\u000e%\u0019!q\u0002\u0004\u0002\u000fA\f7m[1hK&!!1\u0003B\u000b\u0005\u0011a\u0015n\u001d;\u000b\u0007\t=a\u0001C\u0004\u0002 :4\tA!\u0007\u0015\r\tm!q\u0004B\u0011%\u0015\u0011i\"\\A\u000b\r\u0019\t9C\u001c\u0001\u0003\u001c!9\u0011Q\u0015B\f\u0001\u0004Y\u0006bBAU\u0005/\u0001\ra\u0017\u0005\b\u0003#sg\u0011\u0001B\u0013)\u0011\u00119Ca\u000b\u0013\u000b\t%R.!\u0006\u0007\r\u0005\u001db\u000e\u0001B\u0014\u0011!\t9Ja\tA\u0002\u0005m\u0002b\u0002B\u0018]\u0012\u0005!\u0011G\u0001\b[\u0006\u0004h*Y7f)\u0011\u0011\u0019Da\u000e\u0013\u000b\tUR.!\u0006\u0007\r\u0005\u001db\u000e\u0001B\u001a\u0011!\u0011ID!\fA\u0002\tm\u0012!\u00014\u0011\u000f-\u0011i$a\u000f\u0002<%\u0019!q\b\u0004\u0003\u0013\u0019+hn\u0019;j_:\f\u0004b\u0002B\"]\u0012\u0015!QI\u0001\nG>\u0004\u0018p\u00115beN$RA\u0006B$\u0005\u0017BqA!\u0013\u0003B\u0001\u00071*\u0001\u0002dg\"9!Q\nB!\u0001\u0004Y\u0016AB8gMN,G\u000f\u0003\u0004\u0003R9$)AS\u0001\bi>\u001c\u0005.\u0019:t\u0011\u001d\u0011)F\u001cC#\u0005/\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u00027\"9!1\f8\u0005\u0002\tu\u0013!D:ue&twm\u0018\u0013fc\u0012*\u0017\u000fF\u00029\u0005?BqA!\u0019\u0003Z\u0001\u0007Q.\u0001\u0003uQ\u0006$\bb\u0002B.]\u0012\u0005!Q\r\u000b\u0004q\t\u001d\u0004\u0002\u0003B1\u0005G\u0002\r!a\u000f\t\u000f\t-d\u000e\"\u0002\u0003n\u000511\r[1s\u0003R$2A\u0014B8\u0011\u001d\u0011\tH!\u001bA\u0002m\u000b\u0011!\u001b\u0005\b\u0005krGQ\u0001B<\u0003\r\u0001xn\u001d\u000b\u00047\ne\u0004b\u0002B>\u0005g\u0002\rAT\u0001\u0002G\"9!Q\u000f8\u0005\u0006\t}DcA.\u0003\u0002\"A!1\u0011B?\u0001\u0004\tY$A\u0001t\u0011\u001d\u0011)H\u001cC\u0003\u0005\u000f#Ra\u0017BE\u0005\u0017CqAa\u001f\u0003\u0006\u0002\u0007a\nC\u0004\u0002\u001c\t\u0015\u0005\u0019A.\t\u000f\tUd\u000e\"\u0002\u0003\u0010R)1L!%\u0003\u0014\"A!1\u0011BG\u0001\u0004\tY\u0004C\u0004\u0002\u001c\t5\u0005\u0019A.\t\u000f\t]e\u000e\"\u0002\u0003\u001a\u00069A.Y:u!>\u001cHcA.\u0003\u001c\"9!1\u0010BK\u0001\u0004q\u0005b\u0002BL]\u0012\u0015!q\u0014\u000b\u00067\n\u0005&1\u0015\u0005\b\u0005w\u0012i\n1\u0001O\u0011\u001d\tYB!(A\u0002mCqAa*o\t\u000b\u0011I+\u0001\u0006ti\u0006\u0014Ho],ji\"$2\u0001\u000fBV\u0011\u001d\u0011iK!*A\u00025\fa\u0001\u001d:fM&D\bb\u0002BT]\u0012\u0015!\u0011\u0017\u000b\u0006q\tM&Q\u0017\u0005\b\u0005[\u0013y\u000b1\u0001n\u0011\u001d\tYBa,A\u0002mCqAa*o\t\u000b\u0011I\fF\u00039\u0005w\u0013i\f\u0003\u0005\u0003.\n]\u0006\u0019AA\u001e\u0011\u001d\tYBa.A\u0002mCqA!1o\t\u000b\u0011\u0019-\u0001\u0005f]\u0012\u001cx+\u001b;i)\rA$Q\u0019\u0005\b\u0005\u000f\u0014y\f1\u0001n\u0003\u0019\u0019XO\u001a4jq\"9!\u0011\u00198\u0005\u0006\t-G#\u0002\u001d\u0003N\n=\u0007b\u0002Bd\u0005\u0013\u0004\r!\u001c\u0005\b\u0005#\u0014I\r1\u0001\\\u0003\r)g\u000e\u001a\u0005\b\u0005\u0003tGQ\u0001Bk)\u0015A$q\u001bBm\u0011!\u00119Ma5A\u0002\u0005m\u0002b\u0002Bi\u0005'\u0004\ra\u0017\u0005\b\u0005;tGQ\u0001Bp\u00031\u0019wN\u001c;bS:\u001ch*Y7f)\rA$\u0011\u001d\u0005\t\u0005G\u0014Y\u000e1\u0001\u0002<\u000591/\u001e2oC6,\u0007b\u0002Bo]\u0012\u0015!q\u001d\u000b\u0004q\t%\bb\u0002Br\u0005K\u0004\r!\u001c\u0005\b\u0005[tGQ\u0001Bx\u00031\u0019wN\u001c;bS:\u001c8\t[1s)\rA$\u0011\u001f\u0005\b\u0005g\u0014Y\u000f1\u0001O\u0003\t\u0019\u0007\u000eC\u0004\u0003x:$)A!?\u0002\u0013M$\u0018M\u001d;DQ\u0006\u0014X#\u0001(\t\u000f\tuh\u000e\"\u0002\u0003z\u00069QM\u001c3DQ\u0006\u0014\bb\u0002BT]\u0012\u00151\u0011\u0001\u000b\u0004q\r\r\u0001bBB\u0003\u0005\u007f\u0004\rAT\u0001\u0005G\"\f'\u000fC\u0004\u0003(:$)a!\u0003\u0015\u0007a\u001aY\u0001\u0003\u0005\u0004\u000e\r\u001d\u0001\u0019AA\u001e\u0003\u0011q\u0017-\\3\t\u000f\t\u0005g\u000e\"\u0002\u0004\u0012Q\u0019\u0001ha\u0005\t\u000f\r\u00151q\u0002a\u0001\u001d\"9!\u0011\u00198\u0005\u0006\r]Ac\u0001\u001d\u0004\u001a!A1QBB\u000b\u0001\u0004\tY\u0004C\u0004\u0004\u001e9$Iaa\b\u0002\u0015\u0019L\u00070\u00138eKb|e\rF\u0002\\\u0007CAqaa\t\u0004\u001c\u0001\u00071,A\u0002jIbDqaa\no\t\u0003\u0019I#A\u0004j]\u0012,\u0007p\u00144\u0015\u0007m\u001bY\u0003C\u0004\u0003t\u000e\u0015\u0002\u0019\u0001(\t\u000f\r\u001db\u000e\"\u0001\u00040Q)1l!\r\u00044!9!1_B\u0017\u0001\u0004q\u0005bBB\u001b\u0007[\u0001\raW\u0001\nMJ|W.\u00138eKbDqaa\no\t\u0003\u0019I\u0004F\u0002\\\u0007wA\u0001Ba!\u00048\u0001\u0007\u00111\b\u0005\b\u0007\u007fqG\u0011AB!\u0003-a\u0017m\u001d;J]\u0012,\u0007p\u00144\u0015\u0007m\u001b\u0019\u0005C\u0004\u0003t\u000eu\u0002\u0019\u0001(\t\u000f\r}b\u000e\"\u0001\u0004HQ\u00191l!\u0013\t\u0011\t\r5Q\ta\u0001\u0003wAqa!\u0014o\t\u0003\u0019y%A\u0004sKBd\u0017mY3\u0015\u000b5\u001c\tfa\u0015\t\u000f\u0005\u001561\na\u0001\u001d\"9\u0011\u0011VB&\u0001\u0004q\u0005bBB,]\u0012\u0005\u0011\u0011H\u0001\bI\u0016\u001cw\u000eZ3e\u0011\u001d\u0019YF\u001cC\u0001\u0003s\tq!\u001a8d_\u0012,G\rC\u0004\u0004`9$\t!a\u0005\u0002\u0017\u0015t7m\u001c3fI:\u000bW.\u001a\u0005\b\u0007GrG\u0011AA\n\u0003\u0019)gnY8eK\"9\u0011Q\u00178\u0005\u0002\u0005e\u0002bBB5]\u0012\u000511N\u0001\u0007CB\u0004XM\u001c3\u0015\t\t\u001d2Q\u000e\u0005\b\u0005g\u001c9\u00071\u0001O\u0011\u001d\u0019IG\u001cC\u0001\u0007c\"BAa\n\u0004t!A!qYB8\u0001\u0004\tY\u0004C\u0004\u0004j9$\taa\u001e\u0015\t\t\u001d2\u0011\u0010\u0005\b\u0005\u000f\u001c)\b1\u0001n\u0011\u001d\u0019IG\u001cC\u0001\u0007{\"bAa\n\u0004\u0000\r\r\u0005bBBA\u0007w\u0002\rAT\u0001\ng\u0016\u0004\u0018M]1u_JDqAa2\u0004|\u0001\u0007Q\u000eC\u0004\u0004\b:$\ta!#\u0002\u000fA\u0014X\r]3oIR!!qEBF\u0011!\u0011ik!\"A\u0002\u0005m\u0002bBBH]\u0012\u0005\u00111C\u0001\fI\u0016\u001cw\u000eZ3e\u001d\u0006lW\r\u0003\u0004\u0004\u0014:$\taN\u0001\u000fSN|\u0005/\u001a:bi>\u0014h*Y7f\u0011\u001d\u00199J\u001cC\u0001\u0003s\t!\u0002\\8oON#(/\u001b8h\u0011\u001d\u0019YJ\u001cC\u0001\u0003[\u000b1\u0002Z3ck\u001e\u001cFO]5oO&\"aN[A0!\rI7\u0011U\u0005\u0004\u0007G\u0013\"a\u0003+fe6t\u0015-\\3Ba&D!\"!\u001ck\u0005\u0003\u0005\u000b\u0011B.u\u0011)\t\tH\u001bB\u0001B\u0003%1\f\u001f\u0005\u000b\u0003?Q'Q1A\u0005\u0002\u0005U\u0003\"CA<U\n\u0005\t\u0015!\u0003i\u0011\u0019i(\u000e\"\u0001\u00040R9\u0001n!-\u00044\u000eU\u0006bBA7\u0007[\u0003\ra\u0017\u0005\b\u0003c\u001ai\u000b1\u0001\\\u0011\u001d\tyb!,A\u0002!,Q!!\u0002k\u0001!D\u0001\"!\u0005kA\u0013E\u0011Q\u000b\u0005\u0007\u0003\u0017RG\u0011A\u001c\t\r\u0005=#\u000e\"\u00018\u0011\u001d\t\u0019F\u001bC\u0001\u0003+Bq!!\u0017k\t\u0003\tY\u0006C\u0004\u0002\u0012*$\ta!2\u0015\u0007!\u001c9\r\u0003\u0005\u0002\u0018\u000e\r\u0007\u0019AA\u001e\u0011\u001d\tYJ\u001bC\u0001\u00037Bq!a(k\t\u0003\u0019i\rF\u0003i\u0007\u001f\u001c\t\u000eC\u0004\u0002&\u000e-\u0007\u0019A.\t\u000f\u0005%61\u001aa\u00017\"9\u0011q\u00076\u0005\u0002\u00055\u0006bBBlU\u001aE1\u0011\\\u0001\u0014GJ,\u0017\r^3D_6\u0004\u0018M\\5p]:\u000bW.\u001a\u000b\u0005\u0003;\u001aY\u000e\u0003\u0005\u0002 \rU\u0007\u0019AA/S\u0015Q7q\\B\u0000\r\u0019\u0019\t\u000f\u0001\u0004\u0004d\nQA+\u001a:n\u001d\u0006lWm\u0018*\u0014\u0007\r}\u0007\u000e\u0003\u0006\u0002n\r}'\u0011!Q\u0001\nmC!\"!\u001d\u0004`\n\u0005\t\u0015!\u0003\\\u00111\t)ma8\u0003\u0002\u0003\u0006I\u0001[BU\u0011\u001di8q\u001cC\u0001\u0007[$\u0002ba<\u0004r\u000eM8Q\u001f\t\u0004S\u000e}\u0007bBA7\u0007W\u0004\ra\u0017\u0005\b\u0003c\u001aY\u000f1\u0001\\\u0011\u001d\t)ma;A\u0002!D\u0001ba6\u0004`\u0012E1\u0011 \u000b\u0005\u0003;\u001aY\u0010\u0003\u0005\u0002 \r]\b\u0019AA/\u0011!\t)na8\u0005B\u0005]gA\u0002C\u0001\u0001\u0019!\u0019A\u0001\u0006UKJlg*Y7f?N\u001b2aa@i\u0011)\tiga@\u0003\u0002\u0003\u0006Ia\u0017\u0005\u000b\u0003c\u001ayP!A!\u0002\u0013Y\u0006\u0002DAc\u0007\u007f\u0014\t\u0011)A\u0005Q\u000e%\u0006bCAk\u0007\u007f\u0014)\u0019!C!\u0003sA1\"!;\u0004\u0000\n\u0005\t\u0015!\u0003\u0002<!9Qpa@\u0005\u0002\u0011EAC\u0003C\n\t+!9\u0002\"\u0007\u0005\u001cA\u0019\u0011na@\t\u000f\u00055Dq\u0002a\u00017\"9\u0011\u0011\u000fC\b\u0001\u0004Y\u0006bBAc\t\u001f\u0001\r\u0001\u001b\u0005\t\u0003+$y\u00011\u0001\u0002<!A1q[B\u0000\t#!y\u0002\u0006\u0003\u0002^\u0011\u0005\u0002\u0002CA\u0010\t;\u0001\r!!\u0018\t\u0011\u0005E5q C!\tK!2\u0001\u001bC\u0014\u0011!\t9\nb\tA\u0002\u0005m\u0002b\u0002C\u0016\u0001\u0001\u0006IaZ\u0001\u000fi\u0016\u0014X\u000eS1tQR\f'\r\\3!\u0011%!y\u0003\u0001b\u0001\n\u0013!\t$A\u0007usB,\u0007*Y:ii\u0006\u0014G.Z\u000b\u0003\tg\u0001Ba\u0003'\u0002^!AAq\u0007\u0001!\u0002\u0013!\u0019$\u0001\busB,\u0007*Y:ii\u0006\u0014G.\u001a\u0011\t\u000f\u0011m\u0002\u0001\"\u0003\u0005>\u0005I\u0001.Y:i-\u0006dW/\u001a\u000b\b7\u0012}B\u0011\tC\"\u0011\u001d\u0011I\u0005\"\u000fA\u0002-CqA!\u0014\u0005:\u0001\u00071\f\u0003\u0004z\ts\u0001\ra\u0017\u0005\b\t\u000f\u0002A\u0011\u0002C%\u0003\u0019)\u0017/^1mgRI\u0001\bb\u0013\u0005N\u0011=C\u0011\u000b\u0005\u0007k\u0012\u0015\u0003\u0019A.\t\u000f\t%CQ\ta\u0001\u0017\"9!Q\nC#\u0001\u0004Y\u0006BB=\u0005F\u0001\u00071\fC\u0004\u0005V\u0001!I\u0001b\u0016\u0002\u0015\u0015tG/\u001a:DQ\u0006\u00148\u000fF\u0004\u0017\t3\"Y\u0006\"\u0018\t\u000f\t%C1\u000ba\u0001\u0017\"9!Q\nC*\u0001\u0004Y\u0006BB=\u0005T\u0001\u00071\fC\u0004\u0005b\u0001!)\u0001b\u0019\u0002\u00179,w\u000fV3s[:\u000bW.\u001a\u000b\bQ\u0012\u0015Dq\rC5\u0011\u001d\u0011I\u0005b\u0018A\u0002-CqA!\u0014\u0005`\u0001\u00071\f\u0003\u0004z\t?\u0002\ra\u0017\u0005\b\tC\u0002AQ\u0001C7)\rAGq\u000e\u0005\b\u0005\u0013\"Y\u00071\u0001L\u0011\u001d!\u0019\b\u0001C\u0003\tk\n1B\\3x)f\u0004XMT1nKR!\u0011Q\fC<\u0011\u001d\u0011I\u0005\"\u001dA\u0002-Cq\u0001\"\u0019\u0001\t\u000b!Y\bF\u0005i\t{\"y\b\"!\u0005\u0004\"9!\u0011\nC=\u0001\u0004Y\u0005b\u0002B'\ts\u0002\ra\u0017\u0005\b\u0003c\"I\b1\u0001\\\u0011!!)\t\"\u001fA\u0002\u0005m\u0012\u0001D2bG\",Gm\u0015;sS:<\u0007b\u0002C:\u0001\u0011\u0015A\u0011\u0012\u000b\u000b\u0003;\"Y\t\"$\u0005\u0010\u0012E\u0005b\u0002B%\t\u000f\u0003\ra\u0013\u0005\b\u0005\u001b\"9\t1\u0001\\\u0011\u0019IHq\u0011a\u00017\"AAQ\u0011CD\u0001\u0004\tY\u0004C\u0004\u0005b\u0001!\t\u0001\"&\u0015\u0007!$9\n\u0003\u0005\u0003\u0004\u0012M\u0005\u0019AA\u001eQ!!\u0019\nb'\u0005\"\u0012\u0015\u0006cA\u0006\u0005\u001e&\u0019Aq\u0014\u0004\u0003)\u0011,\u0007O]3dCR,Gm\u0014<feJLG-\u001b8hC\t!\u0019+\u0001\u001eU_\u0002\u001a\u0018P\\2ie>t\u0017N_3-AU\u001cX\r\t1pm\u0016\u0014(/\u001b3fA\u0011,g\rI:z]\u000eD'o\u001c8ju\u0016t\u0015-\\3tAu\u0002CO];fA\u0006\u0012AqU\u0001\u0007e9\n\u0014G\f\u0019\t\u000f\u0011M\u0004\u0001\"\u0001\u0005,R!\u0011Q\fCW\u0011!\u0011\u0019\t\"+A\u0002\u0005m\u0002\u0006\u0003CU\t7#\t\u000b\"*\t\u000f\u0011\u0005\u0004\u0001\"\u0002\u00054R9\u0001\u000e\".\u0005B\u0012\r\u0007\u0002\u0003C\\\tc\u0003\r\u0001\"/\u0002\u0005\t\u001c\b\u0003B\u0006M\tw\u00032a\u0003C_\u0013\r!yL\u0002\u0002\u0005\u0005f$X\rC\u0004\u0003N\u0011E\u0006\u0019A.\t\re$\t\f1\u0001\\\u0011\u001d!9\r\u0001C\u0003\t\u0013\f\u0011C\\3x)\u0016\u0014XNT1nK\u000e\u000b7\r[3e)\rAG1\u001a\u0005\t\u0005\u0007#)\r1\u0001\u0002<!9Aq\u001a\u0001\u0005\u0006\u0011E\u0017!\u00058foRK\b/\u001a(b[\u0016\u001c\u0015m\u00195fIR!\u0011Q\fCj\u0011!\u0011\u0019\t\"4A\u0002\u0005m\u0002b\u0002C:\u0001\u0011\u0015Aq\u001b\u000b\t\u0003;\"I\u000eb7\u0005^\"9!\u0011\nCk\u0001\u0004Y\u0005b\u0002B'\t+\u0004\ra\u0017\u0005\u0007s\u0012U\u0007\u0019A.\t\u000f\u0011M\u0004\u0001\"\u0002\u0005bRA\u0011Q\fCr\tK$9\u000f\u0003\u0005\u00058\u0012}\u0007\u0019\u0001C]\u0011\u001d\u0011i\u0005b8A\u0002mCa!\u001fCp\u0001\u0004Y\u0006b\u0002Cv\u0001\u0011\u0015AQ^\u0001\u000fY>|7.\u001e9UsB,g*Y7f)\u0011\ti\u0006b<\t\u000f\t%C\u0011\u001ea\u0001\u0017\"9A1\u001f\u0001\u0005\u0004\u0011U\u0018AC!os:\u000bW.Z(qgR!Aq_C1!\u0011IG\u0011`7\u0007\r\u0011m\bA\u0001C\u007f\u0005\u001dq\u0015-\\3PaN,B\u0001b@\u0006\bM\u0019A\u0011 \u0006\t\u0017\r5A\u0011 B\u0001B\u0003%Q1\u0001\t\u0005\u000b\u000b)9\u0001\u0004\u0001\u0005\u0011\u0015%A\u0011 b\u0001\u000b\u0017\u0011\u0011\u0001V\t\u0004\u000b\u001bi\u0007cA\u0006\u0006\u0010%\u0019Q\u0011\u0003\u0004\u0003\u000f9{G\u000f[5oO\"9Q\u0010\"?\u0005\u0002\u0015UA\u0003BC\f\u000b3\u0001R!\u001bC}\u000b\u0007A\u0001b!\u0004\u0006\u0014\u0001\u0007Q1\u0001\u0005\t\u000b;!I\u0010\"\u0001\u0006 \u0005Y1\u000f\u001e:jaN+hMZ5y)\u0011)\u0019!\"\t\t\u0011\t\u001dW1\u0004a\u0001\u0003wA\u0001\"\"\b\u0005z\u0012\u0005QQ\u0005\u000b\u0005\u000b\u0007)9\u0003C\u0004\u0003H\u0016\r\u0002\u0019A7\t\u0011\u0015-B\u0011 C\u0001\u000b[\tA\u0001^1lKR!Q1AC\u0018\u0011\u001d)\t$\"\u000bA\u0002m\u000b\u0011A\u001c\u0005\t\u000bk!I\u0010\"\u0001\u00068\u0005!AM]8q)\u0011)\u0019!\"\u000f\t\u000f\u0015ER1\u0007a\u00017\"AQQ\bC}\t\u0003)y$A\u0005ee>\u0004(+[4iiR!Q1AC!\u0011\u001d)\t$b\u000fA\u0002mC\u0001\"\"\u0012\u0005z\u0012\u0005\u0011QK\u0001\nIJ|\u0007\u000fT8dC2D\u0001\"\"\u0013\u0005z\u0012\u0005\u0011QK\u0001\u000bIJ|\u0007oU3ui\u0016\u0014\b\u0002CC'\ts$\t!b\u0014\u0002\u0015\u0011\u0014x\u000e]'pIVdW-\u0006\u0002\u0006\u0004!AQ1\u000bC}\t\u0003\t)&A\u0005m_\u000e\fGNT1nK\"AQq\u000bC}\t\u0003\t)&\u0001\u0006tKR$XM\u001d(b[\u0016D\u0001\"b\u0017\u0005z\u0012\u0005\u0011QK\u0001\u000bO\u0016$H/\u001a:OC6,\u0007\u0002CC0\ts$I!!\u0016\u00021\u0011\u0014x\u000e\u001d+sC&$8+\u001a;uKJ\u001cV\r]1sCR|'\u000fC\u0004\u0004\u000e\u0011E\b\u0019A7\t\u000f\u0015\u0015\u0004\u0001b\u0001\u0006h\u0005YA+\u001a:n\u001d\u0006lWm\u00149t)\u0011)I'b\u001b\u0011\t%$I\u0010\u001b\u0005\b\u0007\u001b)\u0019\u00071\u0001i\u0011\u001d)y\u0007\u0001C\u0002\u000bc\n1\u0002V=qK:\u000bW.Z(qgR!Q1OC;!\u0015IG\u0011`A/\u0011!\u0019i!\"\u001cA\u0002\u0005u\u0003\"CC=\u0001\t\u0007I1AC>\u0003\u001dq\u0015-\\3UC\u001e,\"!\" \u0011\u000b\u0015}T\u0011Q7\u000e\u0003\u0011I1!b!\u0005\u0005!\u0019E.Y:t)\u0006<\u0007\u0002CCD\u0001\u0001\u0006I!\" \u0002\u00119\u000bW.\u001a+bO\u0002B\u0011\"b#\u0001\u0005\u0004%\u0019!\"$\u0002\u0017Q+'/\u001c(b[\u0016$\u0016mZ\u000b\u0003\u000b\u001f\u0003R!b \u0006\u0002\"D\u0001\"b%\u0001A\u0003%QqR\u0001\r)\u0016\u0014XNT1nKR\u000bw\rI\u0004\b\u000b/\u0003\u0001\u0012ACM\u0003!!VM]7OC6,\u0007cA5\u0006\u001c\u001a11\u000e\u0001E\u0001\u000b;\u001bB!b'\u0006 B\u0019\u0011.\")\n\u0007\u0015\r&CA\tUKJlg*Y7f\u000bb$(/Y2u_JDq!`CN\t\u0003)9\u000b\u0006\u0002\u0006\u001a\"AQ1VCN\t\u0003)i+A\u0003baBd\u0017\u0010F\u0002i\u000b_C\u0001Ba!\u0006*\u0002\u0007\u00111\b\u0005\t\u000bg+Y\n\"\u0001\u00066\u00069QO\\1qa2LH\u0003BC\\\u000b{\u0003RaCC]\u0003wI1!b/\u0007\u0005\u0019y\u0005\u000f^5p]\"91QBCY\u0001\u0004A\u0007\"CCa\u0001\t\u0007I1ACb\u0003-!\u0016\u0010]3OC6,G+Y4\u0016\u0005\u0015\u0015\u0007CBC@\u000b\u0003\u000bi\u0006\u0003\u0005\u0006J\u0002\u0001\u000b\u0011BCc\u00031!\u0016\u0010]3OC6,G+Y4!\u000f\u001d)i\r\u0001E\u0001\u000b\u001f\f\u0001\u0002V=qK:\u000bW.\u001a\t\u0004S\u0016EgaBA1\u0001!\u0005Q1[\n\u0005\u000b#,)\u000eE\u0002j\u000b/L1!\"7\u0013\u0005E!\u0016\u0010]3OC6,W\t\u001f;sC\u000e$xN\u001d\u0005\b{\u0016EG\u0011ACo)\t)y\r\u0003\u0005\u0006,\u0016EG\u0011ACq)\u0011\ti&b9\t\u0011\t\rUq\u001ca\u0001\u0003wA\u0001\"b-\u0006R\u0012\u0005Qq\u001d\u000b\u0005\u000bo+I\u000f\u0003\u0005\u0004\u000e\u0015\u0015\b\u0019AA/\u0001")
public interface Names
extends scala.reflect.api.Names {
    public void scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$nameLock_$eq(Object var1);

    public void scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$termHashtable_$eq(TermName[] var1);

    public void scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$typeHashtable_$eq(TypeName[] var1);

    public void scala$reflect$internal$Names$_setter_$NameTag_$eq(ClassTag var1);

    public void scala$reflect$internal$Names$_setter_$TermNameTag_$eq(ClassTag var1);

    public void scala$reflect$internal$Names$_setter_$TypeNameTag_$eq(ClassTag var1);

    public int scala$reflect$internal$Names$$HASH_SIZE();

    public int scala$reflect$internal$Names$$HASH_MASK();

    public int scala$reflect$internal$Names$$NAME_SIZE();

    public boolean nameDebug();

    public boolean synchronizeNames();

    public Object scala$reflect$internal$Names$$nameLock();

    public char[] chrs();

    @TraitSetter
    public void chrs_$eq(char[] var1);

    public int scala$reflect$internal$Names$$nc();

    @TraitSetter
    public void scala$reflect$internal$Names$$nc_$eq(int var1);

    public TermName[] scala$reflect$internal$Names$$termHashtable();

    public TypeName[] scala$reflect$internal$Names$$typeHashtable();

    public TermName newTermName(char[] var1, int var2, int var3);

    public TermName newTermName(char[] var1);

    public TypeName newTypeName(char[] var1);

    public TermName newTermName(char[] var1, int var2, int var3, String var4);

    public TypeName newTypeName(char[] var1, int var2, int var3, String var4);

    @Override
    public TermName newTermName(String var1);

    @Override
    public TypeName newTypeName(String var1);

    public TermName newTermName(byte[] var1, int var2, int var3);

    public TermName newTermNameCached(String var1);

    public TypeName newTypeNameCached(String var1);

    public TypeName newTypeName(char[] var1, int var2, int var3);

    public TypeName newTypeName(byte[] var1, int var2, int var3);

    public TypeName lookupTypeName(char[] var1);

    public NameOps<Name> AnyNameOps(Name var1);

    public NameOps<TermName> TermNameOps(TermName var1);

    public NameOps<TypeName> TypeNameOps(TypeName var1);

    public ClassTag<Name> NameTag();

    public ClassTag<TermName> TermNameTag();

    @Override
    public Names$TermName$ TermName();

    public ClassTag<TypeName> TypeNameTag();

    @Override
    public Names$TypeName$ TypeName();

    public abstract class Name
    extends Names.NameApi {
        private final int index;
        private final int len;

        public int index() {
            return this.index;
        }

        public int len() {
            return this.len;
        }

        public abstract Name thisName();

        public int start() {
            return this.index();
        }

        public abstract Name next();

        public final int length() {
            return this.len();
        }

        public final boolean isEmpty() {
            return this.length() == 0;
        }

        public final boolean nonEmpty() {
            return !this.isEmpty();
        }

        public abstract String nameKind();

        @Override
        public abstract boolean isTermName();

        @Override
        public abstract boolean isTypeName();

        @Override
        public abstract TermName toTermName();

        @Override
        public abstract TypeName toTypeName();

        public abstract Name companionName();

        public List<Name> bothNames() {
            return List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Name[]{this.toTermName(), this.toTypeName()}));
        }

        public abstract Name subName(int var1, int var2);

        public abstract Name newName(String var1);

        public Name mapName(Function1<String, String> f) {
            return this.newName(f.apply(this.toString()));
        }

        public final void copyChars(char[] cs, int offset) {
            int n = this.len();
            int n2 = this.index();
            char[] cArray = this.scala$reflect$internal$Names$Name$$$outer().chrs();
            Platform$ platform$ = Platform$.MODULE$;
            System.arraycopy(cArray, n2, cs, offset, n);
        }

        /*
         * WARNING - void declaration
         */
        public final char[] toChars() {
            void var1_1;
            char[] cs = new char[this.len()];
            this.copyChars(cs, 0);
            return var1_1;
        }

        public final int hashCode() {
            return this.index();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean string_$eq$eq(Name that) {
            if (that == null) return false;
            String string2 = this.toString();
            String string3 = that.toString();
            if (string2 != null) {
                if (!string2.equals(string3)) return false;
                return true;
            }
            if (string3 == null) return true;
            return false;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean string_$eq$eq(String that) {
            if (that == null) return false;
            String string2 = this.toString();
            if (string2 != null) {
                if (!string2.equals(that)) return false;
                return true;
            }
            if (that == null) return true;
            return false;
        }

        public final char charAt(int i) {
            return this.scala$reflect$internal$Names$Name$$$outer().chrs()[this.index() + i];
        }

        public final int pos(char c) {
            return this.pos(c, 0);
        }

        public final int pos(String s2) {
            return this.pos(s2, 0);
        }

        /*
         * WARNING - void declaration
         */
        public final int pos(char c, int start) {
            void var3_3;
            for (int i = start; i < this.len() && this.scala$reflect$internal$Names$Name$$$outer().chrs()[this.index() + i] != c; ++i) {
            }
            return (int)var3_3;
        }

        public final int pos(String s2, int start) {
            int i = this.pos(s2.charAt(0), start);
            while (i + s2.length() <= this.len()) {
                int j = 1;
                while (s2.charAt(j) == this.scala$reflect$internal$Names$Name$$$outer().chrs()[this.index() + i + j]) {
                    if (++j != s2.length()) continue;
                    return i;
                }
                i = this.pos(s2.charAt(0), i + 1);
            }
            return this.len();
        }

        public final int lastPos(char c) {
            return this.lastPos(c, this.len() - 1);
        }

        /*
         * WARNING - void declaration
         */
        public final int lastPos(char c, int start) {
            void var3_3;
            for (int i = start; i >= 0 && this.scala$reflect$internal$Names$Name$$$outer().chrs()[this.index() + i] != c; --i) {
            }
            return (int)var3_3;
        }

        public final boolean startsWith(Name prefix) {
            return this.startsWith(prefix, 0);
        }

        public final boolean startsWith(Name prefix, int start) {
            int i;
            for (i = 0; i < prefix.length() && start + i < this.len() && this.scala$reflect$internal$Names$Name$$$outer().chrs()[this.index() + start + i] == this.scala$reflect$internal$Names$Name$$$outer().chrs()[prefix.start() + i]; ++i) {
            }
            return i == prefix.length();
        }

        public final boolean startsWith(String prefix, int start) {
            int i;
            for (i = 0; i < prefix.length() && start + i < this.len() && this.scala$reflect$internal$Names$Name$$$outer().chrs()[this.index() + start + i] == prefix.charAt(i); ++i) {
            }
            return i == prefix.length();
        }

        public final boolean endsWith(Name suffix) {
            return this.endsWith(suffix, this.len());
        }

        public final boolean endsWith(Name suffix, int end) {
            int i;
            for (i = 1; i <= suffix.length() && i <= end && this.scala$reflect$internal$Names$Name$$$outer().chrs()[this.index() + end - i] == this.scala$reflect$internal$Names$Name$$$outer().chrs()[suffix.start() + suffix.length() - i]; ++i) {
            }
            return i > suffix.length();
        }

        public final boolean endsWith(String suffix, int end) {
            int i;
            for (i = 1; i <= suffix.length() && i <= end && this.scala$reflect$internal$Names$Name$$$outer().chrs()[this.index() + end - i] == suffix.charAt(suffix.length() - i); ++i) {
            }
            return i > suffix.length();
        }

        public final boolean containsName(String subname) {
            return this.containsName(this.scala$reflect$internal$Names$Name$$$outer().newTermName(subname));
        }

        public final boolean containsName(Name subname) {
            int start;
            int last2 = this.len() - subname.length();
            for (start = 0; start <= last2 && !this.startsWith(subname, start); ++start) {
            }
            return start <= last2;
        }

        public final boolean containsChar(char ch) {
            int max2 = this.index() + this.len();
            for (int i = this.index(); i < max2; ++i) {
                if (this.scala$reflect$internal$Names$Name$$$outer().chrs()[i] != ch) continue;
                return true;
            }
            return false;
        }

        public final char startChar() {
            return this.charAt(0);
        }

        public final char endChar() {
            return this.charAt(this.len() - 1);
        }

        public final boolean startsWith(char c) {
            return this.len() > 0 && this.startChar() == c;
        }

        public final boolean startsWith(String name) {
            return this.startsWith(name, 0);
        }

        public final boolean endsWith(char c) {
            return this.len() > 0 && this.endChar() == c;
        }

        public final boolean endsWith(String name) {
            return this.endsWith(name, this.len());
        }

        private int fixIndexOf(int idx) {
            return idx == this.length() ? -1 : idx;
        }

        public int indexOf(char ch) {
            return this.fixIndexOf(this.pos(ch));
        }

        public int indexOf(char ch, int fromIndex) {
            return this.fixIndexOf(this.pos(ch, fromIndex));
        }

        public int indexOf(String s2) {
            return this.fixIndexOf(this.pos(s2));
        }

        public int lastIndexOf(char ch) {
            return this.lastPos(ch);
        }

        public int lastIndexOf(String s2) {
            return this.toString().lastIndexOf(s2);
        }

        public Name replace(char from2, char to2) {
            char[] cs = new char[this.len()];
            for (int i = 0; i < this.len(); ++i) {
                char ch = this.charAt(i);
                cs[i] = ch == from2 ? to2 : ch;
            }
            return this.scala$reflect$internal$Names$Name$$$outer().newTermName(cs, 0, this.len());
        }

        @Override
        public String decoded() {
            return this.decode();
        }

        @Override
        public String encoded() {
            return String.valueOf(this.encode());
        }

        @Override
        public Name encodedName() {
            return this.encode();
        }

        public Name encode() {
            String res;
            String str = this.toString();
            String string2 = res = NameTransformer$.MODULE$.encode(str);
            return !(string2 != null ? !string2.equals(str) : str != null) ? this.thisName() : this.newName(res);
        }

        public String decode() {
            String string2;
            if (this.containsChar('$')) {
                String res;
                String str = this.toString();
                String string3 = res = NameTransformer$.MODULE$.decode(str);
                string2 = !(string3 != null ? !string3.equals(str) : str != null) ? str : res;
            } else {
                string2 = this.toString();
            }
            return string2;
        }

        public Name append(char ch) {
            return this.newName(new StringBuilder().append((Object)this.toString()).append(BoxesRunTime.boxToCharacter(ch)).toString());
        }

        public Name append(String suffix) {
            return this.newName(new StringBuilder().append((Object)this.toString()).append((Object)suffix).toString());
        }

        public Name append(Name suffix) {
            return this.newName(new StringBuilder().append((Object)this.toString()).append(suffix).toString());
        }

        public Name append(char separator, Name suffix) {
            return this.newName(new StringBuilder().append((Object)this.toString()).append(BoxesRunTime.boxToCharacter(separator)).append(suffix).toString());
        }

        public Name prepend(String prefix) {
            return this.newName(new StringBuilder().append((Object)"").append((Object)prefix).append(this).toString());
        }

        @Override
        public Name decodedName() {
            return this.newName(this.decode());
        }

        public boolean isOperatorName() {
            String string2 = this.decode();
            String string3 = this.toString();
            return string2 != null ? !string2.equals(string3) : string3 != null;
        }

        public String longString() {
            return new StringBuilder().append((Object)this.nameKind()).append((Object)" ").append((Object)this.decode()).toString();
        }

        public String debugString() {
            String s2 = this.decode();
            return this.isTypeName() ? new StringBuilder().append((Object)s2).append((Object)"!").toString() : s2;
        }

        public /* synthetic */ Names scala$reflect$internal$Names$Name$$$outer() {
            return (Names)this.$outer;
        }

        public Name(Names $outer, int index, int len) {
            this.index = index;
            this.len = len;
        }
    }

    public final class NameOps<T extends Name> {
        public final T name;
        private final /* synthetic */ Names $outer;

        public T stripSuffix(String suffix) {
            return ((Name)this.name).endsWith(suffix) ? this.dropRight(suffix.length()) : this.name;
        }

        public T stripSuffix(Name suffix) {
            return ((Name)this.name).endsWith(suffix) ? this.dropRight(suffix.length()) : this.name;
        }

        public T take(int n) {
            return (T)((Name)this.name).subName(0, n);
        }

        public T drop(int n) {
            return (T)((Name)this.name).subName(n, ((Name)this.name).length());
        }

        public T dropRight(int n) {
            return (T)((Name)this.name).subName(0, ((Name)this.name).length() - n);
        }

        public TermName dropLocal() {
            return this.$outer.TermNameOps(((Name)this.name).toTermName()).stripSuffix(NameTransformer$.MODULE$.LOCAL_SUFFIX_STRING());
        }

        public TermName dropSetter() {
            return this.$outer.TermNameOps(((Name)this.name).toTermName()).stripSuffix(NameTransformer$.MODULE$.SETTER_SUFFIX_STRING());
        }

        public T dropModule() {
            return this.stripSuffix(NameTransformer$.MODULE$.MODULE_SUFFIX_STRING());
        }

        public TermName localName() {
            return (TermName)this.getterName().append(NameTransformer$.MODULE$.LOCAL_SUFFIX_STRING());
        }

        public TermName setterName() {
            return (TermName)this.getterName().append(NameTransformer$.MODULE$.SETTER_SUFFIX_STRING());
        }

        public TermName getterName() {
            return this.$outer.TermNameOps(this.$outer.TermNameOps(this.dropTraitSetterSeparator()).dropSetter()).dropLocal();
        }

        private TermName dropTraitSetterSeparator() {
            TermName termName;
            int n = ((Name)this.name).indexOf(NameTransformer$.MODULE$.TRAIT_SETTER_SEPARATOR_STRING());
            switch (n) {
                default: {
                    termName = this.$outer.TermNameOps(this.$outer.TermNameOps(((Name)this.name).toTermName()).drop(n)).drop(NameTransformer$.MODULE$.TRAIT_SETTER_SEPARATOR_STRING().length());
                    break;
                }
                case -1: {
                    termName = ((Name)this.name).toTermName();
                }
            }
            return termName;
        }

        public NameOps(Names $outer, T name) {
            this.name = name;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public abstract class TermName
    extends Name
    implements Names.TermNameApi {
        private final TermName next;

        @Override
        public TermName next() {
            return this.next;
        }

        @Override
        public TermName thisName() {
            return this;
        }

        @Override
        public boolean isTermName() {
            return true;
        }

        @Override
        public boolean isTypeName() {
            return false;
        }

        @Override
        public TermName toTermName() {
            return this;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public TypeName toTypeName() {
            TypeName typeName;
            if (!this.scala$reflect$internal$Names$TermName$$$outer().synchronizeNames()) {
                typeName = this.body$2();
                return typeName;
            }
            Object object = this.scala$reflect$internal$Names$TermName$$$outer().scala$reflect$internal$Names$$nameLock();
            synchronized (object) {
                TypeName typeName2 = this.body$2();
                // MONITOREXIT @DISABLED, blocks:[0, 1] lbl8 : MonitorExitStatement: MONITOREXIT : object
                typeName = typeName2;
                return typeName;
            }
        }

        @Override
        public TermName newName(String str) {
            return this.scala$reflect$internal$Names$TermName$$$outer().newTermName(str);
        }

        @Override
        public TypeName companionName() {
            return this.toTypeName();
        }

        @Override
        public TermName subName(int from2, int to2) {
            return this.scala$reflect$internal$Names$TermName$$$outer().newTermName(this.scala$reflect$internal$Names$TermName$$$outer().chrs(), this.start() + from2, to2 - from2);
        }

        @Override
        public String nameKind() {
            return "term";
        }

        public abstract TypeName createCompanionName(TypeName var1);

        public /* synthetic */ Names scala$reflect$internal$Names$TermName$$$outer() {
            return (Names)this.$outer;
        }

        private final TypeName body$2() {
            TypeName typeName;
            TypeName n;
            int h = Names$class.scala$reflect$internal$Names$$hashValue(this.scala$reflect$internal$Names$TermName$$$outer(), this.scala$reflect$internal$Names$TermName$$$outer().chrs(), this.index(), this.len()) & Short.MAX_VALUE;
            for (n = this.scala$reflect$internal$Names$TermName$$$outer().scala$reflect$internal$Names$$typeHashtable()[h]; n != null && n.start() != this.index(); n = n.next()) {
            }
            if (n != null) {
                typeName = n;
            } else {
                TypeName typeName2;
                TypeName next2 = this.scala$reflect$internal$Names$TermName$$$outer().scala$reflect$internal$Names$$typeHashtable()[h];
                this.scala$reflect$internal$Names$TermName$$$outer().scala$reflect$internal$Names$$typeHashtable()[h] = typeName2 = this.createCompanionName(next2);
                typeName = typeName2;
            }
            return typeName;
        }

        public TermName(Names $outer, int index0, int len0, TermName next2) {
            this.next = next2;
            super($outer, index0, len0);
        }
    }

    public abstract class TypeName
    extends Name
    implements Names.TypeNameApi {
        private final TypeName next;

        @Override
        public TypeName next() {
            return this.next;
        }

        @Override
        public TypeName thisName() {
            return this;
        }

        @Override
        public boolean isTermName() {
            return false;
        }

        @Override
        public boolean isTypeName() {
            return true;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public TermName toTermName() {
            TermName termName;
            if (!this.scala$reflect$internal$Names$TypeName$$$outer().synchronizeNames()) {
                termName = this.body$3();
                return termName;
            }
            Object object = this.scala$reflect$internal$Names$TypeName$$$outer().scala$reflect$internal$Names$$nameLock();
            synchronized (object) {
                TermName termName2 = this.body$3();
                // MONITOREXIT @DISABLED, blocks:[0, 1] lbl8 : MonitorExitStatement: MONITOREXIT : object
                termName = termName2;
                return termName;
            }
        }

        @Override
        public TypeName toTypeName() {
            return this;
        }

        @Override
        public TypeName newName(String str) {
            return this.scala$reflect$internal$Names$TypeName$$$outer().newTypeName(str);
        }

        @Override
        public TermName companionName() {
            return this.toTermName();
        }

        @Override
        public TypeName subName(int from2, int to2) {
            return this.scala$reflect$internal$Names$TypeName$$$outer().newTypeName(this.scala$reflect$internal$Names$TypeName$$$outer().chrs(), this.start() + from2, to2 - from2);
        }

        @Override
        public String nameKind() {
            return "type";
        }

        @Override
        public String decode() {
            return super.decode();
        }

        public /* synthetic */ Names scala$reflect$internal$Names$TypeName$$$outer() {
            return (Names)this.$outer;
        }

        private final TermName body$3() {
            TermName n;
            int h = Names$class.scala$reflect$internal$Names$$hashValue((Names)this.$outer, ((Names)this.$outer).chrs(), this.index(), this.len()) & Short.MAX_VALUE;
            for (n = ((Names)this.$outer).scala$reflect$internal$Names$$termHashtable()[h]; n != null && n.start() != this.index(); n = n.next()) {
            }
            boolean bl = n != null;
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"TypeName ", " is missing its correspondent"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this}))).toString());
            }
            return n;
        }

        public TypeName(Names $outer, int index0, int len0, TypeName next2) {
            this.next = next2;
            super($outer, index0, len0);
        }
    }

    public final class TermName_S
    extends TermName {
        private final String toString;

        public String toString() {
            return this.toString;
        }

        @Override
        public TypeName createCompanionName(TypeName next2) {
            return new TypeName_S(this.scala$reflect$internal$Names$TermName_S$$$outer(), this.index(), this.len(), next2, this.toString());
        }

        @Override
        public TermName newName(String str) {
            return this.scala$reflect$internal$Names$TermName_S$$$outer().newTermNameCached(str);
        }

        public /* synthetic */ Names scala$reflect$internal$Names$TermName_S$$$outer() {
            return (Names)this.$outer;
        }

        public TermName_S(Names $outer, int index0, int len0, TermName next0, String toString2) {
            this.toString = toString2;
            super($outer, index0, len0, next0);
        }
    }

    public final class TypeName_S
    extends TypeName {
        private final String toString;

        public String toString() {
            return this.toString;
        }

        @Override
        public TypeName newName(String str) {
            return this.scala$reflect$internal$Names$TypeName_S$$$outer().newTypeNameCached(str);
        }

        public /* synthetic */ Names scala$reflect$internal$Names$TypeName_S$$$outer() {
            return (Names)this.$outer;
        }

        public TypeName_S(Names $outer, int index0, int len0, TypeName next0, String toString2) {
            this.toString = toString2;
            super($outer, index0, len0, next0);
        }
    }

    public final class TermName_R
    extends TermName {
        @Override
        public TypeName createCompanionName(TypeName next2) {
            return new TypeName_R(this.scala$reflect$internal$Names$TermName_R$$$outer(), this.index(), this.len(), next2);
        }

        public String toString() {
            return new String(this.scala$reflect$internal$Names$TermName_R$$$outer().chrs(), this.index(), this.len());
        }

        public /* synthetic */ Names scala$reflect$internal$Names$TermName_R$$$outer() {
            return (Names)this.$outer;
        }

        public TermName_R(Names $outer, int index0, int len0, TermName next0) {
            super($outer, index0, len0, next0);
        }
    }

    public final class TypeName_R
    extends TypeName {
        public String toString() {
            return new String(this.scala$reflect$internal$Names$TypeName_R$$$outer().chrs(), this.index(), this.len());
        }

        public /* synthetic */ Names scala$reflect$internal$Names$TypeName_R$$$outer() {
            return (Names)this.$outer;
        }

        public TypeName_R(Names $outer, int index0, int len0, TypeName next0) {
            super($outer, index0, len0, next0);
        }
    }
}

