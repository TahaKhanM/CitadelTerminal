/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.Traversable$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.TraversableView;
import scala.collection.TraversableViewLike$Transformed$class;
import scala.collection.TraversableViewLike$class;
import scala.collection.ViewMkString;
import scala.collection.ViewMkString$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.generic.SliceInterval;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u0011Uh\u0001C\u0001\u0003!\u0003\r\ta\u0002\u0017\u0003'Q\u0013\u0018M^3sg\u0006\u0014G.\u001a,jK^d\u0015n[3\u000b\u0005\r!\u0011AC2pY2,7\r^5p]*\tQ!A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\t!\u0019\"\u0006I\n\u0006\u0001%iA$\f\t\u0003\u0015-i\u0011\u0001B\u0005\u0003\u0019\u0011\u0011a!\u00118z%\u00164\u0007c\u0001\b\u0010#5\t!!\u0003\u0002\u0011\u0005\tYAK]1wKJ\u001c\u0018M\u00197f!\t\u00112\u0003\u0004\u0001\u0005\rQ\u0001AQ1\u0001\u0016\u0005\u0005\t\u0015C\u0001\f\u001a!\tQq#\u0003\u0002\u0019\t\t9aj\u001c;iS:<\u0007C\u0001\u0006\u001b\u0013\tYBAA\u0002B]f\u0004BAD\u000f\u0012?%\u0011aD\u0001\u0002\u0010)J\fg/\u001a:tC\ndW\rT5lKB\u0011!\u0003\t\u0003\u0007C\u0001!)\u0019\u0001\u0012\u0003\tQC\u0017n]\t\u0003-\r\u00122\u0001\n\u0014-\r\u0011)\u0003\u0001A\u0012\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\t99\u0013#K\u0005\u0003Q\t\u0011q\u0002\u0016:bm\u0016\u00148/\u00192mKZKWm\u001e\t\u0003%)\"aa\u000b\u0001\u0005\u0006\u0004)\"\u0001B\"pY2\u0004RA\u0004\u0001\u0012S}\u00012A\u0004\u0018\u0012\u0013\ty#A\u0001\u0007WS\u0016<Xj[*ue&tw\rC\u00032\u0001\u0011\u0005!'\u0001\u0004%S:LG\u000f\n\u000b\u0002gA\u0011!\u0002N\u0005\u0003k\u0011\u0011A!\u00168ji\")q\u0007\u0001D\tq\u0005QQO\u001c3fe2L\u0018N\\4\u0016\u0003%BaA\u000f\u0001!\n#Y\u0014A\u0004<jK^LE-\u001a8uS\u001aLWM]\u000b\u0002yA\u0011Q\b\u0011\b\u0003\u0015yJ!a\u0010\u0003\u0002\rA\u0013X\rZ3g\u0013\t\t%I\u0001\u0004TiJLgn\u001a\u0006\u0003\u007f\u0011Aa\u0001\u0012\u0001!\n#Y\u0014\u0001\u0004<jK^LEm\u0015;sS:<\u0007\"\u0002$\u0001\t\u00039\u0015\u0001\u0004<jK^$vn\u0015;sS:<W#\u0001%\u0011\u0005%sU\"\u0001&\u000b\u0005-c\u0015\u0001\u00027b]\u001eT\u0011!T\u0001\u0005U\u00064\u0018-\u0003\u0002B\u0015\")\u0001\u000b\u0001C!\u000f\u0006a1\u000f\u001e:j]\u001e\u0004&/\u001a4jq\"1!\u000b\u0001Q\u0005RM\u000b!B\\3x\u0005VLG\u000eZ3s+\u0005!\u0006\u0003B+Y#}i\u0011A\u0016\u0006\u0003/\n\tq!\\;uC\ndW-\u0003\u0002Z-\n9!)^5mI\u0016\u0014\b\"B.\u0001\t\u0003a\u0016!\u00024pe\u000e,WcA/k?R\u0011a,\u0019\t\u0003%}#Q\u0001\u0019.C\u0002U\u0011A\u0001\u00165bi\")!M\u0017a\u0002G\u0006\u0011!M\u001a\t\u0006I\u001eL\u0013NX\u0007\u0002K*\u0011aMA\u0001\bO\u0016tWM]5d\u0013\tAWM\u0001\u0007DC:\u0014U/\u001b7e\rJ|W\u000e\u0005\u0002\u0013U\u0012)1N\u0017b\u0001Y\n\t!)\u0005\u0002\u00123\u00191a\u000eAA\u0001\u0005=\u00141#\u00112tiJ\f7\r\u001e+sC:\u001chm\u001c:nK\u0012,\"\u0001]:\u0014\t5L\u0011\u000f\u001e\t\u0004\u001d=\u0011\bC\u0001\nt\t\u0019YW\u000e\"b\u0001+A\u0019QO\u001e:\u000e\u0003\u00011qa\u001e\u0001\u0011\u0002\u0007\u0005\u0001PA\u0006Ue\u0006t7OZ8s[\u0016$WCA=}'\r1\u0018B\u001f\t\u0005\u001d\u001dZ\u0018\u0006\u0005\u0002\u0013y\u001211N\u001eCC\u0002UAQ!\r<\u0005\u0002IBaa <\u0007\u0002\u0005\u0005\u0011a\u00024pe\u0016\f7\r[\u000b\u0005\u0003\u0007\t\t\u0002F\u00024\u0003\u000bAq!a\u0002\u007f\u0001\u0004\tI!A\u0001g!\u0019Q\u00111B>\u0002\u0010%\u0019\u0011Q\u0002\u0003\u0003\u0013\u0019+hn\u0019;j_:\f\u0004c\u0001\n\u0002\u0012\u00111\u00111\u0003@C\u0002U\u0011\u0011!\u0016\u0005\toYD)\u0019!C\u0001q!I\u0011\u0011\u0004<\t\u0002\u0003\u0006K!K\u0001\fk:$WM\u001d7zS:<\u0007\u0005\u0003\u0004Em\u0002&)f\u0012\u0005\b\u0003?1H\u0011IA\u0011\u0003)AW-\u00193PaRLwN\\\u000b\u0003\u0003G\u0001BACA\u0013w&\u0019\u0011q\u0005\u0003\u0003\r=\u0003H/[8o\u0011\u001d\tYC\u001eC!\u0003C\t!\u0002\\1ti>\u0003H/[8o\u0011\u0015\u0001f\u000f\"\u0011H\u0011\u001d\t\tD\u001eC!\u0003g\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u0011\"9\u0011qG7\u0005\u0002\u0005e\u0012A\u0002\u001fj]&$h\b\u0006\u0002\u0002<A\u0019Q/\u001c:\u0007\u0013\u0005}\u0002\u0001%A\u0002\u0002\u0005\u0005#!C#naRLh+[3x'\u0015\ti$CA\"!\r)hO\u0006\u0005\u0007c\u0005uB\u0011\u0001\u001a\t\u0011\u0005%\u0013Q\bC#\u0003\u0017\nq![:F[B$\u00180\u0006\u0002\u0002NA\u0019!\"a\u0014\n\u0007\u0005ECAA\u0004C_>dW-\u00198\t\u000f}\fi\u0004\"\u0012\u0002VU!\u0011qKA0)\r\u0019\u0014\u0011\f\u0005\t\u0003\u000f\t\u0019\u00061\u0001\u0002\\A1!\"a\u0003\u0017\u0003;\u00022AEA0\t\u001d\t\u0019\"a\u0015C\u0002U1\u0011\"a\u0019\u0001!\u0003\r\t!!\u001a\u0003\r\u0019{'oY3e+\u0011\t9'!\u001c\u0014\u000b\u0005\u0005\u0014\"!\u001b\u0011\tU4\u00181\u000e\t\u0004%\u00055DAB6\u0002b\t\u0007Q\u0003\u0003\u00042\u0003C\"\tA\r\u0005\u000b\u0003g\n\tG1Q\u0007\u0012\u0005U\u0014A\u00024pe\u000e,G-\u0006\u0002\u0002xA)a\"!\u001f\u0002l%\u0019\u00111\u0010\u0002\u0003\r\u001d+gnU3r\u0011\u001dy\u0018\u0011\rC\u0001\u0003\u007f*B!!!\u0002\nR\u00191'a!\t\u0011\u0005\u001d\u0011Q\u0010a\u0001\u0003\u000b\u0003rACA\u0006\u0003W\n9\tE\u0002\u0013\u0003\u0013#q!a\u0005\u0002~\t\u0007Q\u0003C\u0004;\u0003C\u0002KQK$\u0007\u0013\u0005=\u0005\u0001%A\u0002\u0002\u0005E%AB*mS\u000e,GmE\u0003\u0002\u000e&\t\u0019\nE\u0002vmFAa!MAG\t\u0003\u0011\u0004BCAM\u0003\u001b\u0013\rU\"\u0005\u0002\u001c\u0006IQM\u001c3q_&tGo]\u000b\u0003\u0003;\u00032\u0001ZAP\u0013\r\t\t+\u001a\u0002\u000e'2L7-Z%oi\u0016\u0014h/\u00197\t\u0013\u0005\u0015\u0016Q\u0012Q\u0005\u0012\u0005\u001d\u0016\u0001\u00024s_6,\"!!+\u0011\u0007)\tY+C\u0002\u0002.\u0012\u00111!\u00138u\u0011%\t\t,!$!\n#\t9+A\u0003v]RLG\u000eC\u0004\u0000\u0003\u001b#\t!!.\u0016\t\u0005]\u0016q\u0018\u000b\u0004g\u0005e\u0006\u0002CA\u0004\u0003g\u0003\r!a/\u0011\r)\tY!EA_!\r\u0011\u0012q\u0018\u0003\b\u0003'\t\u0019L1\u0001\u0016\u0011\u001dQ\u0014Q\u0012Q\u0005V\u001d3\u0011\"!2\u0001!\u0003\r\t!a2\u0003\r5\u000b\u0007\u000f]3e+\u0011\tI-a4\u0014\u000b\u0005\r\u0017\"a3\u0011\tU4\u0018Q\u001a\t\u0004%\u0005=GAB6\u0002D\n\u0007Q\u0003\u0003\u00042\u0003\u0007$\tA\r\u0005\u000b\u0003+\f\u0019M1Q\u0007\u0012\u0005]\u0017aB7baBLgnZ\u000b\u0003\u00033\u0004bACA\u0006#\u00055\u0007bB@\u0002D\u0012\u0005\u0011Q\\\u000b\u0005\u0003?\f9\u000fF\u00024\u0003CD\u0001\"a\u0002\u0002\\\u0002\u0007\u00111\u001d\t\b\u0015\u0005-\u0011QZAs!\r\u0011\u0012q\u001d\u0003\b\u0003'\tYN1\u0001\u0016\u0011\u001dQ\u00141\u0019Q\u0005V\u001d3\u0011\"!<\u0001!\u0003\r\t!a<\u0003\u0015\u0019c\u0017\r^'baB,G-\u0006\u0003\u0002r\u0006]8#BAv\u0013\u0005M\b\u0003B;w\u0003k\u00042AEA|\t\u0019Y\u00171\u001eb\u0001+!1\u0011'a;\u0005\u0002IB!\"!6\u0002l\n\u0007k\u0011CA\u007f+\t\ty\u0010\u0005\u0004\u000b\u0003\u0017\t\"\u0011\u0001\t\u0006\u001d\t\r\u0011Q_\u0005\u0004\u0005\u000b\u0011!AE$f]R\u0013\u0018M^3sg\u0006\u0014G.Z(oG\u0016Dqa`Av\t\u0003\u0011I!\u0006\u0003\u0003\f\tMAcA\u001a\u0003\u000e!A\u0011q\u0001B\u0004\u0001\u0004\u0011y\u0001E\u0004\u000b\u0003\u0017\t)P!\u0005\u0011\u0007I\u0011\u0019\u0002B\u0004\u0002\u0014\t\u001d!\u0019A\u000b\t\u000fi\nY\u000f)C+\u000f\u001aI!\u0011\u0004\u0001\u0011\u0002\u0007\u0005!1\u0004\u0002\t\u0003B\u0004XM\u001c3fIV!!Q\u0004B\u0012'\u0015\u00119\"\u0003B\u0010!\u0011)hO!\t\u0011\u0007I\u0011\u0019\u0003\u0002\u0004l\u0005/\u0011\r\u0001\u001c\u0005\u0007c\t]A\u0011\u0001\u001a\t\u0015\t%\"q\u0003b!\u000e#\u0011Y#\u0001\u0003sKN$XC\u0001B\u0017!\u0015q!q\u0006B\u0011\u0013\r\u0011\tD\u0001\u0002\u000f\u000f\u0016tGK]1wKJ\u001c\u0018M\u00197f\u0011\u001dy(q\u0003C\u0001\u0005k)BAa\u000e\u0003@Q\u00191G!\u000f\t\u0011\u0005\u001d!1\u0007a\u0001\u0005w\u0001rACA\u0006\u0005C\u0011i\u0004E\u0002\u0013\u0005\u007f!q!a\u0005\u00034\t\u0007Q\u0003C\u0004;\u0005/\u0001KQK$\u0007\u0013\t\u0015\u0003\u0001%A\u0002\u0002\t\u001d#\u0001\u0003$jYR,'/\u001a3\u0014\u000b\t\r\u0013\"a%\t\rE\u0012\u0019\u0005\"\u00013\u0011)\u0011iEa\u0011CB\u001bE!qJ\u0001\u0005aJ,G-\u0006\u0002\u0003RA1!\"a\u0003\u0012\u0003\u001bBqa B\"\t\u0003\u0011)&\u0006\u0003\u0003X\t}CcA\u001a\u0003Z!A\u0011q\u0001B*\u0001\u0004\u0011Y\u0006\u0005\u0004\u000b\u0003\u0017\t\"Q\f\t\u0004%\t}CaBA\n\u0005'\u0012\r!\u0006\u0005\bu\t\r\u0003\u0015\"\u0016H\r%\u0011)\u0007\u0001I\u0001\u0004\u0003\u00119G\u0001\u0006UC.,gn\u00165jY\u0016\u001cRAa\u0019\n\u0003'Ca!\rB2\t\u0003\u0011\u0004B\u0003B'\u0005G\u0012\rU\"\u0005\u0003P!9qPa\u0019\u0005\u0002\t=T\u0003\u0002B9\u0005s\"2a\rB:\u0011!\t9A!\u001cA\u0002\tU\u0004C\u0002\u0006\u0002\fE\u00119\bE\u0002\u0013\u0005s\"q!a\u0005\u0003n\t\u0007Q\u0003C\u0004;\u0005G\u0002KQK$\u0007\u0013\t}\u0004\u0001%A\u0002\u0002\t\u0005%\u0001\u0004#s_B\u0004X\rZ,iS2,7#\u0002B?\u0013\u0005M\u0005BB\u0019\u0003~\u0011\u0005!\u0007\u0003\u0006\u0003N\tu$\u0019)D\t\u0005\u001fBqa B?\t\u0003\u0011I)\u0006\u0003\u0003\f\nMEcA\u001a\u0003\u000e\"A\u0011q\u0001BD\u0001\u0004\u0011y\t\u0005\u0004\u000b\u0003\u0017\t\"\u0011\u0013\t\u0004%\tMEaBA\n\u0005\u000f\u0013\r!\u0006\u0005\bu\tu\u0004\u0015\"\u0016H\u0011\u001d\u0011I\n\u0001C!\u00057\u000b!\u0002\n9mkN$\u0003\u000f\\;t+\u0019\u0011iJa+\u0003$R!!q\u0014BW)\u0011\u0011\tK!*\u0011\u0007I\u0011\u0019\u000b\u0002\u0004a\u0005/\u0013\r!\u0006\u0005\bE\n]\u00059\u0001BT!\u001d!wm\bBU\u0005C\u00032A\u0005BV\t\u0019Y'q\u0013b\u0001Y\"A!q\u0016BL\u0001\u0004\u0011\t,\u0001\u0002ygB)aBa\u0001\u0003*\"9!Q\u0017\u0001\u0005B\t]\u0016aA7baV1!\u0011\u0018Bd\u0005\u007f#BAa/\u0003JR!!Q\u0018Ba!\r\u0011\"q\u0018\u0003\u0007A\nM&\u0019A\u000b\t\u000f\t\u0014\u0019\fq\u0001\u0003DB9AmZ\u0010\u0003F\nu\u0006c\u0001\n\u0003H\u001211Na-C\u0002UA\u0001\"a\u0002\u00034\u0002\u0007!1\u001a\t\u0007\u0015\u0005-\u0011C!2\t\u000f\t=\u0007\u0001\"\u0011\u0003R\u000691m\u001c7mK\u000e$XC\u0002Bj\u0005C\u0014I\u000e\u0006\u0003\u0003V\n\rH\u0003\u0002Bl\u00057\u00042A\u0005Bm\t\u0019\u0001'Q\u001ab\u0001+!9!M!4A\u0004\tu\u0007c\u00023h?\t}'q\u001b\t\u0004%\t\u0005HAB6\u0003N\n\u0007Q\u0003\u0003\u0005\u0003f\n5\u0007\u0019\u0001Bt\u0003\t\u0001h\r\u0005\u0004\u000b\u0005S\f\"q\\\u0005\u0004\u0005W$!a\u0004)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8\t\u000f\t=\b\u0001\"\u0011\u0003r\u00069a\r\\1u\u001b\u0006\u0004XC\u0002Bz\u0007\u0003\u0011I\u0010\u0006\u0003\u0003v\u000e\rA\u0003\u0002B|\u0005w\u00042A\u0005B}\t\u0019\u0001'Q\u001eb\u0001+!9!M!<A\u0004\tu\bc\u00023h?\t}(q\u001f\t\u0004%\r\u0005AAB6\u0003n\n\u0007Q\u0003\u0003\u0005\u0002\b\t5\b\u0019AB\u0003!\u0019Q\u00111B\t\u0004\bA)aBa\u0001\u0003\u0000\"911\u0002\u0001\u0005B\r5\u0011a\u00024mCR$XM\\\u000b\u0005\u0007\u001f\u0019)\u0002\u0006\u0003\u0004\u0012\r]\u0001\u0003B;w\u0007'\u00012AEB\u000b\t\u0019Y7\u0011\u0002b\u0001+!A1\u0011DB\u0005\u0001\b\u0019Y\"A\u0007bgR\u0013\u0018M^3sg\u0006\u0014G.\u001a\t\u0007\u0015\u0005-\u0011c!\b\u0011\u000b9\u0011\u0019aa\u0005\t\u0011\r\u0005\u0002\u0001)C\u0006\u0007G\ta!Y:UQ&\u001cHcA\u0010\u0004&!A!qVB\u0010\u0001\u0004\t\u0019\nC\u0004\u0004*\u0001!\tba\u000b\u0002\u00139,wOR8sG\u0016$W\u0003BB\u0017\u0007g!Baa\f\u00046A!QO^B\u0019!\r\u001121\u0007\u0003\u0007W\u000e\u001d\"\u0019A\u000b\t\u0013\t=6q\u0005CA\u0002\r]\u0002#\u0002\u0006\u0004:\ru\u0012bAB\u001e\t\tAAHY=oC6,g\bE\u0003\u000f\u0003s\u001a\t\u0004C\u0004\u0004B\u0001!\tba\u0011\u0002\u00179,w/\u00119qK:$W\rZ\u000b\u0005\u0007\u000b\u001aY\u0005\u0006\u0003\u0004H\r5\u0003\u0003B;w\u0007\u0013\u00022AEB&\t\u0019Y7q\bb\u0001Y\"A1qJB \u0001\u0004\u0019\t&\u0001\u0003uQ\u0006$\b#\u0002\b\u00030\r%\u0003bBB+\u0001\u0011E1qK\u0001\n]\u0016<X*\u00199qK\u0012,Ba!\u0017\u0004`Q!11LB1!\u0011)ho!\u0018\u0011\u0007I\u0019y\u0006\u0002\u0004l\u0007'\u0012\r!\u0006\u0005\t\u0003\u000f\u0019\u0019\u00061\u0001\u0004dA1!\"a\u0003\u0012\u0007;Bqaa\u001a\u0001\t#\u0019I'A\u0007oK^4E.\u0019;NCB\u0004X\rZ\u000b\u0005\u0007W\u001a\t\b\u0006\u0003\u0004n\rM\u0004\u0003B;w\u0007_\u00022AEB9\t\u0019Y7Q\rb\u0001+!A\u0011qAB3\u0001\u0004\u0019)\b\u0005\u0004\u000b\u0003\u0017\t2q\u000f\t\u0006\u001d\t\r1q\u000e\u0005\b\u0007w\u0002A\u0011CB?\u0003-qWm\u001e$jYR,'/\u001a3\u0015\t\u0005M5q\u0010\u0005\t\u0007\u0003\u001bI\b1\u0001\u0003R\u0005\t\u0001\u000fC\u0004\u0004\u0006\u0002!\tba\"\u0002\u00139,wo\u00157jG\u0016$G\u0003BAJ\u0007\u0013C\u0001ba#\u0004\u0004\u0002\u0007\u0011QT\u0001\u000b?\u0016tG\r]8j]R\u001c\bbBBH\u0001\u0011E1\u0011S\u0001\u0010]\u0016<HI]8qa\u0016$w\u000b[5mKR!\u00111SBJ\u0011!\u0019\ti!$A\u0002\tE\u0003bBBL\u0001\u0011E1\u0011T\u0001\u000e]\u0016<H+Y6f]^C\u0017\u000e\\3\u0015\t\u0005M51\u0014\u0005\t\u0007\u0003\u001b)\n1\u0001\u0003R!91q\u0014\u0001\u0005\u0012\r\u0005\u0016\u0001\u00038foR\u000b7.\u001a8\u0015\t\u0005M51\u0015\u0005\t\u0007K\u001bi\n1\u0001\u0002*\u0006\ta\u000eC\u0004\u0004*\u0002!\tba+\u0002\u00159,w\u000f\u0012:paB,G\r\u0006\u0003\u0002\u0014\u000e5\u0006\u0002CBS\u0007O\u0003\r!!+\t\u000f\rE\u0006\u0001\"\u0011\u00044\u00061a-\u001b7uKJ$2aHB[\u0011!\u0019\tia,A\u0002\tE\u0003bBB]\u0001\u0011\u000531X\u0001\u000bo&$\bNR5mi\u0016\u0014HcA\u0010\u0004>\"A1\u0011QB\\\u0001\u0004\u0011\t\u0006C\u0004\u0004B\u0002!\tea1\u0002\u0013A\f'\u000f^5uS>tG\u0003BBc\u0007\u0017\u0004RACBd?}I1a!3\u0005\u0005\u0019!V\u000f\u001d7fe!A1\u0011QB`\u0001\u0004\u0011\t\u0006C\u0004\u0004P\u0002!\te!5\u0002\t%t\u0017\u000e^\u000b\u0002?!91Q\u001b\u0001\u0005B\r]\u0017\u0001\u00023s_B$2aHBm\u0011!\u0019)ka5A\u0002\u0005%\u0006bBBo\u0001\u0011\u00053q\\\u0001\u0005i\u0006\\W\rF\u0002 \u0007CD\u0001b!*\u0004\\\u0002\u0007\u0011\u0011\u0016\u0005\b\u0007K\u0004A\u0011IBt\u0003\u0015\u0019H.[2f)\u0015y2\u0011^Bv\u0011!\t)ka9A\u0002\u0005%\u0006\u0002CAY\u0007G\u0004\r!!+\t\u000f\r=\b\u0001\"\u0011\u0004r\u0006IAM]8q/\"LG.\u001a\u000b\u0004?\rM\b\u0002CBA\u0007[\u0004\rA!\u0015\t\u000f\r]\b\u0001\"\u0011\u0004z\u0006IA/Y6f/\"LG.\u001a\u000b\u0004?\rm\b\u0002CBA\u0007k\u0004\rA!\u0015\t\u000f\r}\b\u0001\"\u0011\u0005\u0002\u0005!1\u000f]1o)\u0011\u0019)\rb\u0001\t\u0011\r\u00055Q a\u0001\u0005#Bq\u0001b\u0002\u0001\t\u0003\"I!A\u0004ta2LG/\u0011;\u0015\t\r\u0015G1\u0002\u0005\t\u0007K#)\u00011\u0001\u0002*\"9Aq\u0002\u0001\u0005B\u0011E\u0011\u0001C:dC:dUM\u001a;\u0016\r\u0011MA1\u0005C\u000e)\u0011!)\u0002b\f\u0015\t\u0011]AQ\u0005\u000b\u0005\t3!i\u0002E\u0002\u0013\t7!a\u0001\u0019C\u0007\u0005\u0004)\u0002b\u00022\u0005\u000e\u0001\u000fAq\u0004\t\bI\u001e|B\u0011\u0005C\r!\r\u0011B1\u0005\u0003\u0007W\u00125!\u0019A\u000b\t\u0011\u0011\u001dBQ\u0002a\u0001\tS\t!a\u001c9\u0011\u0011)!Y\u0003\"\t\u0012\tCI1\u0001\"\f\u0005\u0005%1UO\\2uS>t'\u0007\u0003\u0005\u00052\u00115\u0001\u0019\u0001C\u0011\u0003\u0005Q\bb\u0002C\u001b\u0001\u0011\u0005CqG\u0001\ng\u000e\fgNU5hQR,b\u0001\"\u000f\u0005J\u0011\u0005C\u0003\u0002C\u001e\t\u001f\"B\u0001\"\u0010\u0005LQ!Aq\bC\"!\r\u0011B\u0011\t\u0003\u0007A\u0012M\"\u0019A\u000b\t\u000f\t$\u0019\u0004q\u0001\u0005FA9AmZ\u0010\u0005H\u0011}\u0002c\u0001\n\u0005J\u001111\u000eb\rC\u0002UA\u0001\u0002b\n\u00054\u0001\u0007AQ\n\t\t\u0015\u0011-\u0012\u0003b\u0012\u0005H!AA\u0011\u0007C\u001a\u0001\u0004!9\u0005\u000b\u0005\u00054\u0011MCq\fC2!\u0011!)\u0006b\u0017\u000e\u0005\u0011]#b\u0001C-\t\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0011uCq\u000b\u0002\n[&<'/\u0019;j_:\f#\u0001\"\u0019\u0002QRCW\r\t2fQ\u00064\u0018n\u001c:!_\u001a\u0004\u0003m]2b]JKw\r\u001b;aA!\f7\u000fI2iC:<W\r\u001a\u0018!)\",\u0007\u0005\u001d:fm&|Wo\u001d\u0011cK\"\fg/[8sA\r\fg\u000e\t2fAI,\u0007O]8ek\u000e,G\rI<ji\"\u00043oY1o%&<\u0007\u000e\u001e\u0018sKZ,'o]3/C\t!)'A\u00033]er\u0003\u0007C\u0004\u0005j\u0001!\t\u0005b\u001b\u0002\u000f\u001d\u0014x.\u001e9CsV!AQ\u000eC?)\u0011!y\u0007\"!\u0011\u000f\u0011EDq\u000fC>?5\u0011A1\u000f\u0006\u0004\tk\u0012\u0011!C5n[V$\u0018M\u00197f\u0013\u0011!I\bb\u001d\u0003\u00075\u000b\u0007\u000fE\u0002\u0013\t{\"q\u0001b \u0005h\t\u0007QCA\u0001L\u0011!\t9\u0001b\u001aA\u0002\u0011\r\u0005C\u0002\u0006\u0002\fE!Y\bC\u0004\u0005\b\u0002!\t\u0005\"#\u0002\u000bUt'0\u001b9\u0016\r\u0011-E1\u0013CN)\u0011!i\tb(\u0011\u000f)\u00199\rb$\u0005\u0018B!QO\u001eCI!\r\u0011B1\u0013\u0003\b\t+#)I1\u0001\u0016\u0005\t\t\u0015\u0007\u0005\u0003vm\u0012e\u0005c\u0001\n\u0005\u001c\u00129AQ\u0014CC\u0005\u0004)\"AA!3\u0011!!\t\u000b\"\"A\u0004\u0011\r\u0016AB1t!\u0006L'\u000f\u0005\u0004\u000b\u0003\u0017\tBQ\u0015\t\b\u0015\r\u001dG\u0011\u0013CM\u0011\u001d!I\u000b\u0001C!\tW\u000ba!\u001e8{SB\u001cT\u0003\u0003CW\ts#y\f\"2\u0015\t\u0011=F\u0011\u001a\t\n\u0015\u0011EFQ\u0017C^\t\u0003L1\u0001b-\u0005\u0005\u0019!V\u000f\u001d7fgA!QO\u001eC\\!\r\u0011B\u0011\u0018\u0003\b\t+#9K1\u0001\u0016!\u0011)h\u000f\"0\u0011\u0007I!y\fB\u0004\u0005\u001e\u0012\u001d&\u0019A\u000b\u0011\tU4H1\u0019\t\u0004%\u0011\u0015Ga\u0002Cd\tO\u0013\r!\u0006\u0002\u0003\u0003NB\u0001\u0002b3\u0005(\u0002\u000fAQZ\u0001\tCN$&/\u001b9mKB1!\"a\u0003\u0012\t\u001f\u0004\u0012B\u0003CY\to#i\fb1\t\u000f\u0011M\u0007\u0001\"\u0011\u0005V\u0006Ia-\u001b7uKJtu\u000e\u001e\u000b\u0004?\u0011]\u0007\u0002CBA\t#\u0004\rA!\u0015\t\u000f\u0011m\u0007\u0001\"\u0011\u0005^\u0006)\u0011N\\5ugV\u0011Aq\u001c\t\u0005\u001d\u0011\u0005x$C\u0002\u0005d\n\u0011\u0001\"\u0013;fe\u0006$xN\u001d\u0005\b\tO\u0004A\u0011\tCo\u0003\u0015!\u0018-\u001b7t\u0011\u001d!Y\u000f\u0001C!\u0007#\fA\u0001^1jY\"9\u0011\u0011\u0007\u0001\u0005B\u0005M\u0002B\u0004Cy\u0001A\u0005\u0019\u0011!A\u0005\n\rEG1_\u0001\u000bgV\u0004XM\u001d\u0013uC&d\u0017b\u0001Cv;\u0001")
public interface TraversableViewLike<A, Coll, This extends TraversableView<A, Coll> & TraversableViewLike<A, Coll, This>>
extends Traversable<A>,
ViewMkString<A> {
    public /* synthetic */ TraversableView scala$collection$TraversableViewLike$$super$tail();

    public Coll underlying();

    public String viewIdentifier();

    public String viewIdString();

    public String viewToString();

    @Override
    public String stringPrefix();

    @Override
    public Builder<A, This> newBuilder();

    public <B, That> That force(CanBuildFrom<Coll, B, That> var1);

    @Override
    public <B, That> That $plus$plus(GenTraversableOnce<B> var1, CanBuildFrom<This, B, That> var2);

    @Override
    public <B, That> That map(Function1<A, B> var1, CanBuildFrom<This, B, That> var2);

    @Override
    public <B, That> That collect(PartialFunction<A, B> var1, CanBuildFrom<This, B, That> var2);

    @Override
    public <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> var1, CanBuildFrom<This, B, That> var2);

    @Override
    public <B> Transformed<B> flatten(Function1<A, GenTraversableOnce<B>> var1);

    public <B> Transformed<B> newForced(Function0<GenSeq<B>> var1);

    public <B> Transformed<B> newAppended(GenTraversable<B> var1);

    public <B> Transformed<B> newMapped(Function1<A, B> var1);

    public <B> Transformed<B> newFlatMapped(Function1<A, GenTraversableOnce<B>> var1);

    public Transformed<A> newFiltered(Function1<A, Object> var1);

    public Transformed<A> newSliced(SliceInterval var1);

    public Transformed<A> newDroppedWhile(Function1<A, Object> var1);

    public Transformed<A> newTakenWhile(Function1<A, Object> var1);

    public Transformed<A> newTaken(int var1);

    public Transformed<A> newDropped(int var1);

    @Override
    public This filter(Function1<A, Object> var1);

    public This withFilter(Function1<A, Object> var1);

    @Override
    public Tuple2<This, This> partition(Function1<A, Object> var1);

    @Override
    public This init();

    @Override
    public This drop(int var1);

    @Override
    public This take(int var1);

    @Override
    public This slice(int var1, int var2);

    @Override
    public This dropWhile(Function1<A, Object> var1);

    @Override
    public This takeWhile(Function1<A, Object> var1);

    @Override
    public Tuple2<This, This> span(Function1<A, Object> var1);

    @Override
    public Tuple2<This, This> splitAt(int var1);

    @Override
    public <B, That> That scanLeft(B var1, Function2<B, A, B> var2, CanBuildFrom<This, B, That> var3);

    @Override
    public <B, That> That scanRight(B var1, Function2<A, B, B> var2, CanBuildFrom<This, B, That> var3);

    @Override
    public <K> Map<K, This> groupBy(Function1<A, K> var1);

    @Override
    public <A1, A2> Tuple2<Transformed<A1>, Transformed<A2>> unzip(Function1<A, Tuple2<A1, A2>> var1);

    @Override
    public <A1, A2, A3> Tuple3<Transformed<A1>, Transformed<A2>, Transformed<A3>> unzip3(Function1<A, Tuple3<A1, A2, A3>> var1);

    @Override
    public This filterNot(Function1<A, Object> var1);

    @Override
    public Iterator<This> inits();

    @Override
    public Iterator<This> tails();

    @Override
    public This tail();

    @Override
    public String toString();

    public interface Forced<B>
    extends Transformed<B> {
        public GenSeq<B> forced();

        @Override
        public <U> void foreach(Function1<B, U> var1);

        @Override
        public String viewIdentifier();

        public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Forced$$$outer();
    }

    public interface Sliced
    extends Transformed<A> {
        public SliceInterval endpoints();

        public int from();

        public int until();

        @Override
        public <U> void foreach(Function1<A, U> var1);

        @Override
        public String viewIdentifier();

        public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Sliced$$$outer();
    }

    public interface Mapped<B>
    extends Transformed<B> {
        public Function1<A, B> mapping();

        @Override
        public <U> void foreach(Function1<B, U> var1);

        @Override
        public String viewIdentifier();

        public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Mapped$$$outer();
    }

    public interface Appended<B>
    extends Transformed<B> {
        public GenTraversable<B> rest();

        @Override
        public <U> void foreach(Function1<B, U> var1);

        @Override
        public String viewIdentifier();

        public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Appended$$$outer();
    }

    public interface Filtered
    extends Transformed<A> {
        public Function1<A, Object> pred();

        @Override
        public <U> void foreach(Function1<A, U> var1);

        @Override
        public String viewIdentifier();

        public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Filtered$$$outer();
    }

    public interface EmptyView
    extends Transformed<Nothing$> {
        @Override
        public boolean isEmpty();

        @Override
        public <U> void foreach(Function1<Nothing$, U> var1);

        public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$EmptyView$$$outer();
    }

    public interface FlatMapped<B>
    extends Transformed<B> {
        public Function1<A, GenTraversableOnce<B>> mapping();

        @Override
        public <U> void foreach(Function1<B, U> var1);

        @Override
        public String viewIdentifier();

        public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$FlatMapped$$$outer();
    }

    public interface TakenWhile
    extends Transformed<A> {
        public Function1<A, Object> pred();

        @Override
        public <U> void foreach(Function1<A, U> var1);

        @Override
        public String viewIdentifier();

        public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$TakenWhile$$$outer();
    }

    public interface Transformed<B>
    extends TraversableView<B, Coll> {
        @Override
        public <U> void foreach(Function1<B, U> var1);

        @Override
        public Coll underlying();

        @Override
        public String viewIdString();

        @Override
        public Option<B> headOption();

        @Override
        public Option<B> lastOption();

        @Override
        public String stringPrefix();

        @Override
        public String toString();

        public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Transformed$$$outer();
    }

    public interface DroppedWhile
    extends Transformed<A> {
        public Function1<A, Object> pred();

        @Override
        public <U> void foreach(Function1<A, U> var1);

        @Override
        public String viewIdentifier();

        public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$DroppedWhile$$$outer();
    }

    public abstract class AbstractTransformed<B>
    implements Transformed<B> {
        private final Coll underlying;
        private volatile boolean bitmap$0;

        private Object underlying$lzycompute() {
            AbstractTransformed abstractTransformed = this;
            synchronized (abstractTransformed) {
                if (!this.bitmap$0) {
                    this.underlying = TraversableViewLike$Transformed$class.underlying(this);
                    this.bitmap$0 = true;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.underlying;
            }
        }

        @Override
        public Coll underlying() {
            return this.bitmap$0 ? this.underlying : this.underlying$lzycompute();
        }

        @Override
        public final String viewIdString() {
            return TraversableViewLike$Transformed$class.viewIdString(this);
        }

        @Override
        public Option<B> headOption() {
            return TraversableViewLike$Transformed$class.headOption(this);
        }

        @Override
        public Option<B> lastOption() {
            return TraversableViewLike$Transformed$class.lastOption(this);
        }

        @Override
        public String stringPrefix() {
            return TraversableViewLike$Transformed$class.stringPrefix(this);
        }

        @Override
        public String toString() {
            return TraversableViewLike$Transformed$class.toString(this);
        }

        @Override
        public /* synthetic */ TraversableView scala$collection$TraversableViewLike$$super$tail() {
            return (TraversableView)TraversableLike$class.tail(this);
        }

        @Override
        public String viewIdentifier() {
            return TraversableViewLike$class.viewIdentifier(this);
        }

        @Override
        public String viewToString() {
            return TraversableViewLike$class.viewToString(this);
        }

        @Override
        public Builder<B, TraversableView<B, Coll>> newBuilder() {
            return TraversableViewLike$class.newBuilder(this);
        }

        @Override
        public <B, That> That force(CanBuildFrom<Coll, B, That> bf) {
            return (That)TraversableViewLike$class.force(this, bf);
        }

        @Override
        public <B, That> That $plus$plus(GenTraversableOnce<B> xs, CanBuildFrom<TraversableView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
        }

        @Override
        public <B, That> That map(Function1<B, B> f, CanBuildFrom<TraversableView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.map(this, f, bf);
        }

        @Override
        public <B, That> That collect(PartialFunction<B, B> pf, CanBuildFrom<TraversableView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.collect(this, pf, bf);
        }

        @Override
        public <B, That> That flatMap(Function1<B, GenTraversableOnce<B>> f, CanBuildFrom<TraversableView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.flatMap(this, f, bf);
        }

        @Override
        public <B> Transformed<B> flatten(Function1<B, GenTraversableOnce<B>> asTraversable) {
            return TraversableViewLike$class.flatten(this, asTraversable);
        }

        @Override
        public <B> Transformed<B> newForced(Function0<GenSeq<B>> xs) {
            return TraversableViewLike$class.newForced(this, xs);
        }

        @Override
        public <B> Transformed<B> newAppended(GenTraversable<B> that) {
            return TraversableViewLike$class.newAppended(this, that);
        }

        @Override
        public <B> Transformed<B> newMapped(Function1<B, B> f) {
            return TraversableViewLike$class.newMapped(this, f);
        }

        @Override
        public <B> Transformed<B> newFlatMapped(Function1<B, GenTraversableOnce<B>> f) {
            return TraversableViewLike$class.newFlatMapped(this, f);
        }

        @Override
        public Transformed<B> newFiltered(Function1<B, Object> p) {
            return TraversableViewLike$class.newFiltered(this, p);
        }

        @Override
        public Transformed<B> newSliced(SliceInterval _endpoints) {
            return TraversableViewLike$class.newSliced(this, _endpoints);
        }

        @Override
        public Transformed<B> newDroppedWhile(Function1<B, Object> p) {
            return TraversableViewLike$class.newDroppedWhile(this, p);
        }

        @Override
        public Transformed<B> newTakenWhile(Function1<B, Object> p) {
            return TraversableViewLike$class.newTakenWhile(this, p);
        }

        @Override
        public Transformed<B> newTaken(int n) {
            return TraversableViewLike$class.newTaken(this, n);
        }

        @Override
        public Transformed<B> newDropped(int n) {
            return TraversableViewLike$class.newDropped(this, n);
        }

        @Override
        public TraversableView<B, Coll> filter(Function1<B, Object> p) {
            return TraversableViewLike$class.filter(this, p);
        }

        @Override
        public TraversableView<B, Coll> withFilter(Function1<B, Object> p) {
            return TraversableViewLike$class.withFilter(this, p);
        }

        @Override
        public Tuple2<TraversableView<B, Coll>, TraversableView<B, Coll>> partition(Function1<B, Object> p) {
            return TraversableViewLike$class.partition(this, p);
        }

        @Override
        public TraversableView<B, Coll> init() {
            return TraversableViewLike$class.init(this);
        }

        @Override
        public TraversableView<B, Coll> drop(int n) {
            return TraversableViewLike$class.drop(this, n);
        }

        @Override
        public TraversableView<B, Coll> take(int n) {
            return TraversableViewLike$class.take(this, n);
        }

        @Override
        public TraversableView<B, Coll> slice(int from2, int until2) {
            return TraversableViewLike$class.slice(this, from2, until2);
        }

        @Override
        public TraversableView<B, Coll> dropWhile(Function1<B, Object> p) {
            return TraversableViewLike$class.dropWhile(this, p);
        }

        @Override
        public TraversableView<B, Coll> takeWhile(Function1<B, Object> p) {
            return TraversableViewLike$class.takeWhile(this, p);
        }

        @Override
        public Tuple2<TraversableView<B, Coll>, TraversableView<B, Coll>> span(Function1<B, Object> p) {
            return TraversableViewLike$class.span(this, p);
        }

        @Override
        public Tuple2<TraversableView<B, Coll>, TraversableView<B, Coll>> splitAt(int n) {
            return TraversableViewLike$class.splitAt(this, n);
        }

        @Override
        public <B, That> That scanLeft(B z, Function2<B, B, B> op, CanBuildFrom<TraversableView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
        }

        @Override
        public <B, That> That scanRight(B z, Function2<B, B, B> op, CanBuildFrom<TraversableView<B, Coll>, B, That> bf) {
            return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
        }

        @Override
        public <K> Map<K, TraversableView<B, Coll>> groupBy(Function1<B, K> f) {
            return TraversableViewLike$class.groupBy(this, f);
        }

        @Override
        public <A1, A2> Tuple2<Transformed<A1>, Transformed<A2>> unzip(Function1<B, Tuple2<A1, A2>> asPair) {
            return TraversableViewLike$class.unzip(this, asPair);
        }

        @Override
        public <A1, A2, A3> Tuple3<Transformed<A1>, Transformed<A2>, Transformed<A3>> unzip3(Function1<B, Tuple3<A1, A2, A3>> asTriple) {
            return TraversableViewLike$class.unzip3(this, asTriple);
        }

        @Override
        public TraversableView<B, Coll> filterNot(Function1<B, Object> p) {
            return TraversableViewLike$class.filterNot(this, p);
        }

        @Override
        public Iterator<TraversableView<B, Coll>> inits() {
            return TraversableViewLike$class.inits(this);
        }

        @Override
        public Iterator<TraversableView<B, Coll>> tails() {
            return TraversableViewLike$class.tails(this);
        }

        @Override
        public TraversableView<B, Coll> tail() {
            return TraversableViewLike$class.tail(this);
        }

        @Override
        public Seq<B> thisSeq() {
            return ViewMkString$class.thisSeq(this);
        }

        @Override
        public String mkString() {
            return ViewMkString$class.mkString(this);
        }

        @Override
        public String mkString(String sep) {
            return ViewMkString$class.mkString(this, sep);
        }

        @Override
        public String mkString(String start, String sep, String end) {
            return ViewMkString$class.mkString(this, start, sep, end);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
            return ViewMkString$class.addString(this, b, start, sep, end);
        }

        @Override
        public GenericCompanion<Traversable> companion() {
            return Traversable$class.companion(this);
        }

        @Override
        public Traversable<B> seq() {
            return Traversable$class.seq(this);
        }

        @Override
        public <B> Builder<B, Traversable<B>> genericBuilder() {
            return GenericTraversableTemplate$class.genericBuilder(this);
        }

        @Override
        public GenTraversable transpose(Function1 asTraversable) {
            return GenericTraversableTemplate$class.transpose(this, asTraversable);
        }

        @Override
        public Object repr() {
            return TraversableLike$class.repr(this);
        }

        @Override
        public final boolean isTraversableAgain() {
            return TraversableLike$class.isTraversableAgain(this);
        }

        @Override
        public Traversable<B> thisCollection() {
            return TraversableLike$class.thisCollection(this);
        }

        @Override
        public Traversable toCollection(Object repr) {
            return TraversableLike$class.toCollection(this, repr);
        }

        @Override
        public Combiner<B, ParIterable<B>> parCombiner() {
            return TraversableLike$class.parCombiner(this);
        }

        @Override
        public boolean isEmpty() {
            return TraversableLike$class.isEmpty(this);
        }

        @Override
        public boolean hasDefiniteSize() {
            return TraversableLike$class.hasDefiniteSize(this);
        }

        @Override
        public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<TraversableView<B, Coll>, B, That> bf) {
            return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
        }

        @Override
        public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<TraversableView<B, Coll>, B, That> bf) {
            return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
        }

        @Override
        public boolean forall(Function1<B, Object> p) {
            return TraversableLike$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<B, Object> p) {
            return TraversableLike$class.exists(this, p);
        }

        @Override
        public Option<B> find(Function1<B, Object> p) {
            return TraversableLike$class.find(this, p);
        }

        @Override
        public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<TraversableView<B, Coll>, B, That> cbf) {
            return (That)TraversableLike$class.scan(this, z, op, cbf);
        }

        @Override
        public B head() {
            return (B)TraversableLike$class.head(this);
        }

        @Override
        public B last() {
            return (B)TraversableLike$class.last(this);
        }

        @Override
        public Object sliceWithKnownDelta(int from2, int until2, int delta) {
            return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
        }

        @Override
        public Object sliceWithKnownBound(int from2, int until2) {
            return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
        }

        @Override
        public <B> void copyToArray(Object xs, int start, int len) {
            TraversableLike$class.copyToArray(this, xs, start, len);
        }

        @Override
        public Traversable<B> toTraversable() {
            return TraversableLike$class.toTraversable(this);
        }

        @Override
        public Iterator<B> toIterator() {
            return TraversableLike$class.toIterator(this);
        }

        @Override
        public Stream<B> toStream() {
            return TraversableLike$class.toStream(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, B, Col> cbf) {
            return (Col)TraversableLike$class.to(this, cbf);
        }

        @Override
        public Object view() {
            return TraversableLike$class.view(this);
        }

        @Override
        public TraversableView<B, TraversableView<B, Coll>> view(int from2, int until2) {
            return TraversableLike$class.view(this, from2, until2);
        }

        @Override
        public Parallel par() {
            return Parallelizable$class.par(this);
        }

        @Override
        public List<B> reversed() {
            return TraversableOnce$class.reversed(this);
        }

        @Override
        public int size() {
            return TraversableOnce$class.size(this);
        }

        @Override
        public boolean nonEmpty() {
            return TraversableOnce$class.nonEmpty(this);
        }

        @Override
        public int count(Function1<B, Object> p) {
            return TraversableOnce$class.count(this, p);
        }

        @Override
        public <B> Option<B> collectFirst(PartialFunction<B, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, B, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<B, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, B, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B foldRight(B z, Function2<B, B, B> op) {
            return (B)TraversableOnce$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, B, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> B reduceRight(Function2<B, B, B> op) {
            return (B)TraversableOnce$class.reduceRight(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, B, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<B, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> A1 reduce(Function2<A1, A1, A1> op) {
            return (A1)TraversableOnce$class.reduce(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
            return (A1)TraversableOnce$class.fold(this, z, op);
        }

        @Override
        public <B> B aggregate(Function0<B> z, Function2<B, B, B> seqop, Function2<B, B, B> combop) {
            return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public <B> B sum(Numeric<B> num) {
            return (B)TraversableOnce$class.sum(this, num);
        }

        @Override
        public <B> B product(Numeric<B> num) {
            return (B)TraversableOnce$class.product(this, num);
        }

        @Override
        public <B> B min(Ordering<B> cmp) {
            return (B)TraversableOnce$class.min(this, cmp);
        }

        @Override
        public <B> B max(Ordering<B> cmp) {
            return (B)TraversableOnce$class.max(this, cmp);
        }

        @Override
        public <B> B maxBy(Function1<B, B> f, Ordering<B> cmp) {
            return (B)TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public <B> B minBy(Function1<B, B> f, Ordering<B> cmp) {
            return (B)TraversableOnce$class.minBy(this, f, cmp);
        }

        @Override
        public <B> void copyToBuffer(Buffer<B> dest) {
            TraversableOnce$class.copyToBuffer(this, dest);
        }

        @Override
        public <B> void copyToArray(Object xs, int start) {
            TraversableOnce$class.copyToArray(this, xs, start);
        }

        @Override
        public <B> void copyToArray(Object xs) {
            TraversableOnce$class.copyToArray(this, xs);
        }

        @Override
        public <B> Object toArray(ClassTag<B> evidence$1) {
            return TraversableOnce$class.toArray(this, evidence$1);
        }

        @Override
        public List<B> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public Iterable<B> toIterable() {
            return TraversableOnce$class.toIterable(this);
        }

        @Override
        public Seq<B> toSeq() {
            return TraversableOnce$class.toSeq(this);
        }

        @Override
        public IndexedSeq<B> toIndexedSeq() {
            return TraversableOnce$class.toIndexedSeq(this);
        }

        @Override
        public <B> Buffer<B> toBuffer() {
            return TraversableOnce$class.toBuffer(this);
        }

        @Override
        public <B> Set<B> toSet() {
            return TraversableOnce$class.toSet(this);
        }

        @Override
        public Vector<B> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<B, Tuple2<T, U>> ev) {
            return TraversableOnce$class.toMap(this, ev);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String sep) {
            return TraversableOnce$class.addString(this, b, sep);
        }

        @Override
        public StringBuilder addString(StringBuilder b) {
            return TraversableOnce$class.addString(this, b);
        }

        public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$AbstractTransformed$$$outer() {
            return TraversableViewLike.this;
        }

        @Override
        public /* synthetic */ TraversableViewLike scala$collection$TraversableViewLike$Transformed$$$outer() {
            return this.scala$collection$TraversableViewLike$AbstractTransformed$$$outer();
        }

        public AbstractTransformed() {
            if (TraversableViewLike.this == null) {
                throw null;
            }
            TraversableOnce$class.$init$(this);
            Parallelizable$class.$init$(this);
            TraversableLike$class.$init$(this);
            GenericTraversableTemplate$class.$init$(this);
            GenTraversable$class.$init$(this);
            Traversable$class.$init$(this);
            ViewMkString$class.$init$(this);
            TraversableViewLike$class.$init$(this);
            TraversableViewLike$Transformed$class.$init$(this);
        }
    }
}

