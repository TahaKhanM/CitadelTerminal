/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import java.io.ObjectStreamException;
import scala.Equals;
import scala.Function1;
import scala.Serializable;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Mirror;
import scala.reflect.api.SerializedTypeTag;
import scala.reflect.api.Symbols;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags$TypeTag$;
import scala.reflect.api.TypeTags$TypeTag$class;
import scala.reflect.api.TypeTags$WeakTypeTag$;
import scala.reflect.api.TypeTags$WeakTypeTag$class;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

@ScalaSignature(bytes="\u0006\u0001\u0011ec\u0001C\u0001\u0003!\u0003\r\t!\u0003\u001d\u0003\u0011QK\b/\u001a+bONT!a\u0001\u0003\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\u0003\t\u0003\u00171i\u0011AB\u0005\u0003\u001b\u0019\u0011a!\u00118z%\u00164\u0007\"B\b\u0001\t\u0003\u0001\u0012A\u0002\u0013j]&$H\u0005F\u0001\u0012!\tY!#\u0003\u0002\u0014\r\t!QK\\5u\r\u001d)\u0002\u0001%A\u0002\u0002Y\u00111bV3bWRK\b/\u001a+bOV\u0011q\u0003Q\n\u0005))A2\u0004\u0005\u0002\f3%\u0011!D\u0002\u0002\u0007\u000bF,\u0018\r\\:\u0011\u0005-a\u0012BA\u000f\u0007\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011\u0015yA\u0003\"\u0001\u0011\u0011\u001d\u0001CC1A\u0007\u0002\u0005\na!\\5se>\u0014X#\u0001\u0012\u0011\u0005\r\"S\"\u0001\u0001\n\u0005\u00152#AB'jeJ|'/\u0003\u0002(\u0005\t9Q*\u001b:s_J\u001c\b\"B\u0015\u0015\r\u0003Q\u0013AA5o+\tYs\u0006\u0006\u0002-\rB\u0019Q\u0006F \u0011\u00059zC\u0002\u0001\u0003\u0006a!\u0012\r!\r\u0002\u0002+F\u0011!'\u000e\t\u0003\u0017MJ!\u0001\u000e\u0004\u0003\u000f9{G\u000f[5oOJ\u0019a\u0007\u000f\u001f\u0007\t]\"\u0002!\u000e\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0003sij\u0011AA\u0005\u0003w\t\u0011\u0001\"\u00168jm\u0016\u00148/\u001a\t\u0003\u0017uJ!A\u0010\u0004\u0003\u0013MKgn\u001a7fi>t\u0007C\u0001\u0018A\t\u0015\tEC1\u0001C\u0005\u0005!\u0016C\u0001\u001aD!\tYA)\u0003\u0002F\r\t\u0019\u0011I\\=\t\u000b\u001dC\u0003\u0019\u0001%\u0002\u0017=$\b.\u001a:NSJ\u0014xN\u001d\t\u0004s%k\u0013BA\u0013\u0003\u0011\u0015YEC\"\u0001M\u0003\r!\b/Z\u000b\u0002\u001bB\u00111ET\u0005\u0003\u001fB\u0013A\u0001V=qK&\u0011\u0011K\u0001\u0002\u0006)f\u0004Xm\u001d\u0005\u0006'R!\t\u0005V\u0001\tG\u0006tW)];bYR\u0011Q\u000b\u0017\t\u0003\u0017YK!a\u0016\u0004\u0003\u000f\t{w\u000e\\3b]\")\u0011L\u0015a\u0001\u0007\u0006\t\u0001\u0010C\u0003\\)\u0011\u0005C,\u0001\u0004fcV\fGn\u001d\u000b\u0003+vCQ!\u0017.A\u0002\rCQa\u0018\u000b\u0005B\u0001\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002CB\u00111BY\u0005\u0003G\u001a\u00111!\u00138u\u0011\u0015)G\u0003\"\u0011g\u0003!!xn\u0015;sS:<G#A4\u0011\u0005!lW\"A5\u000b\u0005)\\\u0017\u0001\u00027b]\u001eT\u0011\u0001\\\u0001\u0005U\u00064\u0018-\u0003\u0002oS\n11\u000b\u001e:j]\u001eD3\u0001\u00069w!\t\tH/D\u0001s\u0015\t\u0019h!\u0001\u0006b]:|G/\u0019;j_:L!!\u001e:\u0003!%l\u0007\u000f\\5dSRtu\u000e\u001e$pk:$\u0017%A<\u0002C9{\u0007eV3bWRK\b/\u001a+bO\u0002\ng/Y5mC\ndW\r\t4pe\u0002\"3\u0010V?\b\u000be\u0004\u0001\u0012\u0001>\u0002\u0017]+\u0017m\u001b+za\u0016$\u0016m\u001a\t\u0003Gm4Q!\u0006\u0001\t\u0002q\u001c2a\u001f\u0006\u001c\u0011\u0015q8\u0010\"\u0001\u0000\u0003\u0019a\u0014N\\5u}Q\t!\u0010C\u0005\u0002\u0004m\u0014\r\u0011\"\u0001\u0002\u0006\u0005!!)\u001f;f+\t\t9\u0001\u0005\u0003$)\u0005%\u0001cA\u0006\u0002\f%\u0019\u0011Q\u0002\u0004\u0003\t\tKH/\u001a\u0005\t\u0003#Y\b\u0015!\u0003\u0002\b\u0005)!)\u001f;fA!I\u0011QC>C\u0002\u0013\u0005\u0011qC\u0001\u0006'\"|'\u000f^\u000b\u0003\u00033\u0001Ba\t\u000b\u0002\u001cA\u00191\"!\b\n\u0007\u0005}aAA\u0003TQ>\u0014H\u000f\u0003\u0005\u0002$m\u0004\u000b\u0011BA\r\u0003\u0019\u0019\u0006n\u001c:uA!I\u0011qE>C\u0002\u0013\u0005\u0011\u0011F\u0001\u0005\u0007\"\f'/\u0006\u0002\u0002,A!1\u0005FA\u0017!\rY\u0011qF\u0005\u0004\u0003c1!\u0001B\"iCJD\u0001\"!\u000e|A\u0003%\u00111F\u0001\u0006\u0007\"\f'\u000f\t\u0005\n\u0003sY(\u0019!C\u0001\u0003w\t1!\u00138u+\t\ti\u0004E\u0002$)\u0005D\u0001\"!\u0011|A\u0003%\u0011QH\u0001\u0005\u0013:$\b\u0005C\u0005\u0002Fm\u0014\r\u0011\"\u0001\u0002H\u0005!Aj\u001c8h+\t\tI\u0005\u0005\u0003$)\u0005-\u0003cA\u0006\u0002N%\u0019\u0011q\n\u0004\u0003\t1{gn\u001a\u0005\t\u0003'Z\b\u0015!\u0003\u0002J\u0005)Aj\u001c8hA!I\u0011qK>C\u0002\u0013\u0005\u0011\u0011L\u0001\u0006\r2|\u0017\r^\u000b\u0003\u00037\u0002Ba\t\u000b\u0002^A\u00191\"a\u0018\n\u0007\u0005\u0005dAA\u0003GY>\fG\u000f\u0003\u0005\u0002fm\u0004\u000b\u0011BA.\u0003\u00191En\\1uA!I\u0011\u0011N>C\u0002\u0013\u0005\u00111N\u0001\u0007\t>,(\r\\3\u0016\u0005\u00055\u0004\u0003B\u0012\u0015\u0003_\u00022aCA9\u0013\r\t\u0019H\u0002\u0002\u0007\t>,(\r\\3\t\u0011\u0005]4\u0010)A\u0005\u0003[\nq\u0001R8vE2,\u0007\u0005C\u0005\u0002|m\u0014\r\u0011\"\u0001\u0002~\u00059!i\\8mK\u0006tWCAA@!\r\u0019C#\u0016\u0005\t\u0003\u0007[\b\u0015!\u0003\u0002\u0000\u0005A!i\\8mK\u0006t\u0007\u0005C\u0005\u0002\bn\u0014\r\u0011\"\u0001\u0002\n\u0006!QK\\5u+\t\tY\tE\u0002$)EA\u0001\"a$|A\u0003%\u00111R\u0001\u0006+:LG\u000f\t\u0005\n\u0003'[(\u0019!C\u0001\u0003+\u000b1!\u00118z+\t\t9\nE\u0002$)\rC\u0001\"a'|A\u0003%\u0011qS\u0001\u0005\u0003:L\b\u0005C\u0005\u0002 n\u0014\r\u0011\"\u0001\u0002\"\u00061\u0011I\\=WC2,\"!a)\u0011\t\r\"\u0012Q\u0015\t\u0004\u0017\u0005\u001d\u0016bAAU\r\t1\u0011I\\=WC2D\u0001\"!,|A\u0003%\u00111U\u0001\b\u0003:Lh+\u00197!\u0011%\t\tl\u001fb\u0001\n\u0003\t\u0019,\u0001\u0004B]f\u0014VMZ\u000b\u0003\u0003k\u00032a\t\u000b\u000b\u0011!\tIl\u001fQ\u0001\n\u0005U\u0016aB!osJ+g\r\t\u0005\n\u0003{[(\u0019!C\u0001\u0003\u007f\u000baa\u00142kK\u000e$XCAAa!\u0011\u0019C#a1\u0011\u0007!\f)-C\u0002\u0002H&\u0014aa\u00142kK\u000e$\b\u0002CAfw\u0002\u0006I!!1\u0002\u000f=\u0013'.Z2uA!I\u0011qZ>C\u0002\u0013\u0005\u0011\u0011[\u0001\b\u001d>$\b.\u001b8h+\t\t\u0019\u000eE\u0002$)IB\u0001\"a6|A\u0003%\u00111[\u0001\t\u001d>$\b.\u001b8hA!I\u00111\\>C\u0002\u0013\u0005\u0011Q\\\u0001\u0005\u001dVdG.\u0006\u0002\u0002`B!1\u0005FAq!\rY\u00111]\u0005\u0004\u0003K4!\u0001\u0002(vY2D\u0001\"!;|A\u0003%\u0011q\\\u0001\u0006\u001dVdG\u000e\t\u0005\b\u0003[\\H\u0011AAx\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\t\t0a>\u0015\r\u0005M\u0018\u0011`A\u0000!\u0011\u0019C#!>\u0011\u00079\n9\u0010\u0002\u0004B\u0003W\u0014\rA\u0011\u0005\t\u0003w\fY\u000f1\u0001\u0002~\u00069Q.\u001b:s_J\f\u0004cA\u001dJG!A!\u0011AAv\u0001\u0004\u0011\u0019!A\u0003ua\u0016\u001c\u0017\u0007E\u0002:\u0005\u000bI1Aa\u0002\u0003\u0005-!\u0016\u0010]3De\u0016\fGo\u001c:\t\u000f\t-1\u0010\"\u0001\u0003\u000e\u00059QO\\1qa2LX\u0003\u0002B\b\u0005?!BA!\u0005\u0003\u0018A!1Ba\u0005N\u0013\r\u0011)B\u0002\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\te!\u0011\u0002a\u0001\u00057\tA\u0001\u001e;bOB!1\u0005\u0006B\u000f!\rq#q\u0004\u0003\u0007\u0003\n%!\u0019\u0001\"\u0007\r\t\r\u0002\u0001\u0002B\u0013\u0005=9V-Y6UsB,G+Y4J[BdW\u0003\u0002B\u0014\u0005[\u0019RA!\t\u000b\u0005S\u0001Ba\t\u000b\u0003,A\u0019aF!\f\u0005\r\u0005\u0013\tC1\u0001C\u0011%\u0001#\u0011\u0005BC\u0002\u0013\u0005\u0011\u0005\u0003\u0006\u00034\t\u0005\"\u0011!Q\u0001\n\t\nq!\\5se>\u0014\b\u0005C\u0006\u00038\t\u0005\"Q1A\u0005\u0002\te\u0012\u0001\u0002;qK\u000e,\"Aa\u0001\t\u0017\tu\"\u0011\u0005B\u0001B\u0003%!1A\u0001\u0006iB,7\r\t\u0005\b}\n\u0005B\u0011\u0001B!)\u0019\u0011\u0019E!\u0012\u0003HA)1E!\t\u0003,!1\u0001Ea\u0010A\u0002\tB\u0001Ba\u000e\u0003@\u0001\u0007!1\u0001\u0005\n\u0017\n\u0005\u0002R1A\u0005\u00021C!B!\u0014\u0003\"!\u0005\t\u0015)\u0003N\u0003\u0011!\b/\u001a\u0011\t\u000f%\u0012\t\u0003\"\u0001\u0003RU!!1\u000bB-)\u0011\u0011)F!\u0019\u0011\u000b\t]CCa\u000b\u0011\u00079\u0012I\u0006B\u00041\u0005\u001f\u0012\rAa\u0017\u0012\u0007I\u0012iF\u0005\u0003\u0003`abdAB\u001c\u0003\"\u0001\u0011i\u0006C\u0004H\u0005\u001f\u0002\rAa\u0019\u0011\teJ%q\u000b\u0005\t\u0005O\u0012\t\u0003\"\u0003\u0003j\u0005aqO]5uKJ+\u0007\u000f\\1dKR\t!\u0002\u000b\u0004\u0003f\t5$q\u0010\t\u0006\u0017\t=$1O\u0005\u0004\u0005c2!A\u0002;ie><8\u000f\u0005\u0003\u0003v\tmTB\u0001B<\u0015\r\u0011Ih[\u0001\u0003S>LAA! \u0003x\t)rJ\u00196fGR\u001cFO]3b[\u0016C8-\u001a9uS>t7E\u0001B:\r%\u0011\u0019\t\u0001I\u0001\u0004\u0003\u0011)IA\u0004UsB,G+Y4\u0016\t\t\u001d%QR\n\b\u0005\u0003S!\u0011\u0012\r\u001c!\u0011\u0019CCa#\u0011\u00079\u0012i\t\u0002\u0004B\u0005\u0003\u0013\rA\u0011\u0005\u0007\u001f\t\u0005E\u0011\u0001\t\t\u000f%\u0012\tI\"\u0011\u0003\u0014V!!Q\u0013BN)\u0011\u00119Ja)\u0011\r\te%\u0011\u0011BF!\rq#1\u0014\u0003\ba\tE%\u0019\u0001BO#\r\u0011$q\u0014\n\u0005\u0005CCDH\u0002\u00048\u0005\u0003\u0003!q\u0014\u0005\b\u000f\nE\u0005\u0019\u0001BS!\u0011I\u0014J!'\t\u000fM\u0013\t\t\"\u0011\u0003*R\u0019QKa+\t\re\u00139\u000b1\u0001D\u0011\u001dY&\u0011\u0011C!\u0005_#2!\u0016BY\u0011\u0019I&Q\u0016a\u0001\u0007\"1qL!!\u0005B\u0001Da!\u001aBA\t\u00032\u0007&\u0002BAa\ne\u0016E\u0001B^\u0003uqu\u000e\t+za\u0016$\u0016m\u001a\u0011bm\u0006LG.\u00192mK\u00022wN\u001d\u0011%wRkxa\u0002B`\u0001!\u0005!\u0011Y\u0001\b)f\u0004X\rV1h!\r\u0019#1\u0019\u0004\b\u0005\u0007\u0003\u0001\u0012\u0001Bc'\u0011\u0011\u0019MC\u000e\t\u000fy\u0014\u0019\r\"\u0001\u0003JR\u0011!\u0011\u0019\u0005\u000b\u0003\u0007\u0011\u0019M1A\u0005\u0002\t5WC\u0001Bh!\u0015\u0019#\u0011QA\u0005\u0011%\t\tBa1!\u0002\u0013\u0011y\r\u0003\u0006\u0002\u0016\t\r'\u0019!C\u0001\u0005+,\"Aa6\u0011\u000b\r\u0012\t)a\u0007\t\u0013\u0005\r\"1\u0019Q\u0001\n\t]\u0007BCA\u0014\u0005\u0007\u0014\r\u0011\"\u0001\u0003^V\u0011!q\u001c\t\u0006G\t\u0005\u0015Q\u0006\u0005\n\u0003k\u0011\u0019\r)A\u0005\u0005?D!\"!\u000f\u0003D\n\u0007I\u0011\u0001Bs+\t\u00119\u000f\u0005\u0003$\u0005\u0003\u000b\u0007\"CA!\u0005\u0007\u0004\u000b\u0011\u0002Bt\u0011)\t)Ea1C\u0002\u0013\u0005!Q^\u000b\u0003\u0005_\u0004Ra\tBA\u0003\u0017B\u0011\"a\u0015\u0003D\u0002\u0006IAa<\t\u0015\u0005]#1\u0019b\u0001\n\u0003\u0011)0\u0006\u0002\u0003xB)1E!!\u0002^!I\u0011Q\rBbA\u0003%!q\u001f\u0005\u000b\u0003S\u0012\u0019M1A\u0005\u0002\tuXC\u0001B\u0000!\u0015\u0019#\u0011QA8\u0011%\t9Ha1!\u0002\u0013\u0011y\u0010\u0003\u0006\u0002|\t\r'\u0019!C\u0001\u0007\u000b)\"aa\u0002\u0011\t\r\u0012\t)\u0016\u0005\n\u0003\u0007\u0013\u0019\r)A\u0005\u0007\u000fA!\"a\"\u0003D\n\u0007I\u0011AB\u0007+\t\u0019y\u0001\u0005\u0003$\u0005\u0003\u000b\u0002\"CAH\u0005\u0007\u0004\u000b\u0011BB\b\u0011)\t\u0019Ja1C\u0002\u0013\u00051QC\u000b\u0003\u0007/\u0001Ba\tBA\u0007\"I\u00111\u0014BbA\u0003%1q\u0003\u0005\u000b\u0003?\u0013\u0019M1A\u0005\u0002\ruQCAB\u0010!\u0015\u0019#\u0011QAS\u0011%\tiKa1!\u0002\u0013\u0019y\u0002\u0003\u0006\u00022\n\r'\u0019!C\u0001\u0007K)\"aa\n\u0011\t\r\u0012\tI\u0003\u0005\n\u0003s\u0013\u0019\r)A\u0005\u0007OA!\"!0\u0003D\n\u0007I\u0011AB\u0017+\t\u0019y\u0003E\u0003$\u0005\u0003\u000b\u0019\rC\u0005\u0002L\n\r\u0007\u0015!\u0003\u00040!Q\u0011q\u001aBb\u0005\u0004%\ta!\u000e\u0016\u0005\r]\u0002\u0003B\u0012\u0003\u0002JB\u0011\"a6\u0003D\u0002\u0006Iaa\u000e\t\u0015\u0005m'1\u0019b\u0001\n\u0003\u0019i$\u0006\u0002\u0004@A)1E!!\u0002b\"I\u0011\u0011\u001eBbA\u0003%1q\b\u0005\t\u0003[\u0014\u0019\r\"\u0001\u0004FU!1qIB')\u0019\u0019Iea\u0014\u0004RA)1E!!\u0004LA\u0019af!\u0014\u0005\r\u0005\u001b\u0019E1\u0001C\u0011!\tYpa\u0011A\u0002\u0005u\b\u0002\u0003B\u0001\u0007\u0007\u0002\rAa\u0001\t\u0011\t-!1\u0019C\u0001\u0007+*Baa\u0016\u0004`Q!!\u0011CB-\u0011!\u0011Iba\u0015A\u0002\rm\u0003#B\u0012\u0003\u0002\u000eu\u0003c\u0001\u0018\u0004`\u00111\u0011ia\u0015C\u0002\t3aaa\u0019\u0001\t\r\u0015$a\u0003+za\u0016$\u0016mZ%na2,Baa\u001a\u0004nM11\u0011MB5\u0007_\u0002Ra\tB\u0011\u0007W\u00022ALB7\t\u0019\t5\u0011\rb\u0001\u0005B)1E!!\u0004l!Y\u0001e!\u0019\u0003\u0002\u0003\u0006IA\tB\u0018\u00115\u00119d!\u0019\u0003\u0002\u0003\u0006IAa\u0001\u00036!9ap!\u0019\u0005\u0002\r]DCBB=\u0007w\u001ai\bE\u0003$\u0007C\u001aY\u0007\u0003\u0004!\u0007k\u0002\rA\t\u0005\t\u0005o\u0019)\b1\u0001\u0003\u0004!9\u0011f!\u0019\u0005B\r\u0005U\u0003BBB\u0007\u0013#Ba!\"\u0004\u0012B11q\u0011BA\u0007W\u00022ALBE\t\u001d\u00014q\u0010b\u0001\u0007\u0017\u000b2AMBG%\u0011\u0019y\t\u000f\u001f\u0007\r]\u001a\t\u0007ABG\u0011\u001d95q\u0010a\u0001\u0007'\u0003B!O%\u0004\b\"A!qMB1\t\u0013\u0011I\u0007\u000b\u0004\u0004\u0016\n5$q\u0010\u0004\u0007\u00077\u0003Aa!(\u0003#A\u0013X\rZ3g)f\u0004Xm\u0011:fCR|'/\u0006\u0003\u0004 \u000e=6\u0003BBM\u0005\u0007A1ba)\u0004\u001a\n\u0005\t\u0015!\u0003\u0004&\u000611m\u001c9z\u0013:\u0004baCBTq\r-\u0016bABU\r\tIa)\u001e8di&|g.\r\t\u0006q\t\u00055Q\u0016\t\u0004]\r=FAB!\u0004\u001a\n\u0007!\tC\u0004\u007f\u00073#\taa-\u0015\t\rU6q\u0017\t\u0006G\re5Q\u0016\u0005\t\u0007G\u001b\t\f1\u0001\u0004&\"A\u0011Q^BM\t\u0003\u0019Y,\u0006\u0003\u0004>\u000e\rG\u0003BB`\u0007\u0017\u00042a!1O!\rq31\u0019\u0003\ba\re&\u0019ABc#\r\u00114q\u0019\n\u0005\u0007\u0013DDH\u0002\u00048\u00073\u00031q\u0019\u0005\t\u0007\u001b\u001cI\f1\u0001\u0004P\u0006\tQ\u000e\u0005\u0003:\u0013\u000e\u0005gABBj\u0001\u0011\u0019)NA\u0007Qe\u0016$WM\u001a+za\u0016$\u0016mZ\u000b\u0005\u0007/\u001cin\u0005\u0003\u0004R\u000ee\u0007#B\u0012\u0004b\rm\u0007c\u0001\u0018\u0004^\u00121\u0011i!5C\u0002\tC!b!9\u0004R\n\u0005\t\u0015!\u0003N\u0003\u0011yF\u000f]3\t\u0017\r\r6\u0011\u001bB\u0001B\u0003%1Q\u001d\t\u0007\u0017\r\u001d\u0006ha:\u0011\u000ba\u0012\tia7\t\u000fy\u001c\t\u000e\"\u0001\u0004lR11Q^Bx\u0007c\u0004RaIBi\u00077Dqa!9\u0004j\u0002\u0007Q\n\u0003\u0005\u0004$\u000e%\b\u0019ABs\u0011%Y5\u0011\u001bEC\u0002\u0013\u0005C\n\u0003\u0006\u0003N\rE\u0007\u0012!Q!\n5C\u0001Ba\u001a\u0004R\u0012%!\u0011\u000e\u0015\u0007\u0007o\u0014iGa \t\u000f\ru\b\u0001\"\u0001\u0004\u0000\u0006Yq/Z1l)f\u0004X\rV1h+\u0011!\t\u0001b\u0002\u0015\t\u0011\rA\u0011\u0002\t\u0005GQ!)\u0001E\u0002/\t\u000f!a!QB~\u0005\u0004\u0011\u0005\u0002\u0003C\u0006\u0007w\u0004\u001d\u0001b\u0001\u0002\u000b\u0005$H/Y4\t\u000f\u0011=\u0001\u0001\"\u0001\u0005\u0012\u00059A/\u001f9f)\u0006<W\u0003\u0002C\n\t3!B\u0001\"\u0006\u0005\u001cA)1E!!\u0005\u0018A\u0019a\u0006\"\u0007\u0005\r\u0005#iA1\u0001C\u0011!\u0011I\u0002\"\u0004A\u0004\u0011U\u0001b\u0002C\u0010\u0001\u0011\u0005A\u0011E\u0001\u000bo\u0016\f7\u000eV=qK>3W\u0003\u0002C\u0012\tW!2!\u0014C\u0013\u0011!!Y\u0001\"\bA\u0004\u0011\u001d\u0002\u0003B\u0012\u0015\tS\u00012A\fC\u0016\t\u0019\tEQ\u0004b\u0001\u0005\"9Aq\u0006\u0001\u0005\u0002\u0011E\u0012A\u0002;za\u0016|e-\u0006\u0003\u00054\u0011mBcA'\u00056!A!\u0011\u0004C\u0017\u0001\b!9\u0004E\u0003$\u0005\u0003#I\u0004E\u0002/\tw!a!\u0011C\u0017\u0005\u0004\u0011\u0005b\u0002C \u0001\u0019\u0005A\u0011I\u0001\tgfl'm\u001c7PMV!A1\tC,)\u0011!)\u0005b\u0014\u0011\u0007\r\"9%\u0003\u0003\u0005J\u0011-#A\u0003+za\u0016\u001c\u00160\u001c2pY&\u0019AQ\n\u0002\u0003\u000fMKXNY8mg\"QA\u0011\u000bC\u001f\u0003\u0003\u0005\u001d\u0001b\u0015\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0003$)\u0011U\u0003c\u0001\u0018\u0005X\u00111\u0011\t\"\u0010C\u0002\t\u0003")
public interface TypeTags {
    public TypeTags$WeakTypeTag$ WeakTypeTag();

    public TypeTags$TypeTag$ TypeTag();

    public <T> WeakTypeTag<T> weakTypeTag(WeakTypeTag<T> var1);

    public <T> TypeTag<T> typeTag(TypeTag<T> var1);

    public <T> Types.TypeApi weakTypeOf(WeakTypeTag<T> var1);

    public <T> Types.TypeApi typeOf(TypeTag<T> var1);

    public <T> Symbols.TypeSymbolApi symbolOf(WeakTypeTag<T> var1);

    public interface TypeTag<T>
    extends WeakTypeTag<T> {
        @Override
        public <U extends Universe> TypeTag<T> in(Mirror<U> var1);

        @Override
        public boolean canEqual(Object var1);

        @Override
        public boolean equals(Object var1);

        @Override
        public int hashCode();

        @Override
        public String toString();

        public /* synthetic */ TypeTags scala$reflect$api$TypeTags$TypeTag$$$outer();
    }

    public interface WeakTypeTag<T>
    extends Equals,
    Serializable {
        public Mirror mirror();

        public <U extends Universe> WeakTypeTag<T> in(Mirror<U> var1);

        public Types.TypeApi tpe();

        @Override
        public boolean canEqual(Object var1);

        @Override
        public boolean equals(Object var1);

        public int hashCode();

        public String toString();

        public /* synthetic */ TypeTags scala$reflect$api$TypeTags$WeakTypeTag$$$outer();
    }

    public class TypeTagImpl<T>
    extends WeakTypeTagImpl<T>
    implements TypeTag<T> {
        @Override
        public boolean canEqual(Object x) {
            return TypeTags$TypeTag$class.canEqual(this, x);
        }

        @Override
        public boolean equals(Object x) {
            return TypeTags$TypeTag$class.equals(this, x);
        }

        @Override
        public int hashCode() {
            return TypeTags$TypeTag$class.hashCode(this);
        }

        @Override
        public String toString() {
            return TypeTags$TypeTag$class.toString(this);
        }

        @Override
        public <U extends Universe> TypeTag<T> in(Mirror<U> otherMirror) {
            return ((Universe)otherMirror.universe()).TypeTag().apply(otherMirror, super.tpec());
        }

        private Object writeReplace() throws ObjectStreamException {
            return new SerializedTypeTag(super.tpec(), true);
        }

        public /* synthetic */ Universe scala$reflect$api$TypeTags$TypeTagImpl$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ TypeTags scala$reflect$api$TypeTags$TypeTag$$$outer() {
            return this.scala$reflect$api$TypeTags$TypeTagImpl$$$outer();
        }

        public TypeTagImpl(Universe $outer, Mirror mirror, TypeCreator tpec) {
            super($outer, mirror, tpec);
            TypeTags$TypeTag$class.$init$(this);
        }
    }

    public class PredefTypeTag<T>
    extends TypeTagImpl<T> {
        private final Types.TypeApi _tpe;
        private Types.TypeApi tpe;
        private volatile boolean bitmap$0;

        private Types.TypeApi tpe$lzycompute() {
            PredefTypeTag predefTypeTag = this;
            synchronized (predefTypeTag) {
                if (!this.bitmap$0) {
                    this.tpe = this._tpe;
                    this.bitmap$0 = true;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                this._tpe = null;
                return this.tpe;
            }
        }

        @Override
        public Types.TypeApi tpe() {
            return this.bitmap$0 ? this.tpe : this.tpe$lzycompute();
        }

        private Object writeReplace() throws ObjectStreamException {
            return new SerializedTypeTag(this.tpec(), true);
        }

        public /* synthetic */ Universe scala$reflect$api$TypeTags$PredefTypeTag$$$outer() {
            return this.$outer;
        }

        public PredefTypeTag(Universe $outer, Types.TypeApi _tpe, Function1<Universe, TypeTag<T>> copyIn) {
            this._tpe = _tpe;
            super($outer, $outer.rootMirror(), new scala.reflect.api.PredefTypeCreator<T>(copyIn));
        }
    }

    public class WeakTypeTagImpl<T>
    implements WeakTypeTag<T> {
        private final Mirror mirror;
        private final TypeCreator tpec;
        private Types.TypeApi tpe;
        public final /* synthetic */ Universe $outer;
        private volatile boolean bitmap$0;

        private Types.TypeApi tpe$lzycompute() {
            WeakTypeTagImpl weakTypeTagImpl = this;
            synchronized (weakTypeTagImpl) {
                if (!this.bitmap$0) {
                    this.tpe = this.tpec().apply(this.mirror());
                    this.bitmap$0 = true;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.tpe;
            }
        }

        @Override
        public boolean canEqual(Object x) {
            return TypeTags$WeakTypeTag$class.canEqual(this, x);
        }

        @Override
        public boolean equals(Object x) {
            return TypeTags$WeakTypeTag$class.equals(this, x);
        }

        @Override
        public int hashCode() {
            return TypeTags$WeakTypeTag$class.hashCode(this);
        }

        @Override
        public String toString() {
            return TypeTags$WeakTypeTag$class.toString(this);
        }

        @Override
        public Mirror mirror() {
            return this.mirror;
        }

        public TypeCreator tpec() {
            return this.tpec;
        }

        @Override
        public Types.TypeApi tpe() {
            return this.bitmap$0 ? this.tpe : this.tpe$lzycompute();
        }

        @Override
        public <U extends Universe> WeakTypeTag<T> in(Mirror<U> otherMirror) {
            return ((Universe)otherMirror.universe()).WeakTypeTag().apply(otherMirror, this.tpec());
        }

        private Object writeReplace() throws ObjectStreamException {
            return new SerializedTypeTag(this.tpec(), false);
        }

        public /* synthetic */ Universe scala$reflect$api$TypeTags$WeakTypeTagImpl$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ TypeTags scala$reflect$api$TypeTags$WeakTypeTag$$$outer() {
            return this.scala$reflect$api$TypeTags$WeakTypeTagImpl$$$outer();
        }

        public WeakTypeTagImpl(Universe $outer, Mirror mirror, TypeCreator tpec) {
            this.mirror = mirror;
            this.tpec = tpec;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            TypeTags$WeakTypeTag$class.$init$(this);
        }
    }

    public class PredefTypeCreator<T>
    extends TypeCreator {
        private final Function1<Universe, TypeTag<T>> copyIn;
        public final /* synthetic */ Universe $outer;

        @Override
        public <U extends Universe> Types.TypeApi apply(Mirror<U> m) {
            return this.copyIn.apply((Universe)m.universe()).tpe();
        }

        public /* synthetic */ Universe scala$reflect$api$TypeTags$PredefTypeCreator$$$outer() {
            return this.$outer;
        }

        public PredefTypeCreator(Universe $outer, Function1<Universe, TypeTag<T>> copyIn) {
            this.copyIn = copyIn;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }
}

