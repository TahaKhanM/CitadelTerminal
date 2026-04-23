/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Internals;
import scala.reflect.internal.StdAttachments$BackquotedIdentifierAttachment$;
import scala.reflect.internal.StdAttachments$CompoundTypeTreeOriginalAttachment$;
import scala.reflect.internal.StdAttachments$ForAttachment$;
import scala.reflect.internal.StdAttachments$KnownDirectSubclassesCalled$;
import scala.reflect.internal.StdAttachments$SubpatternsAttachment$;
import scala.reflect.internal.StdAttachments$SyntheticUnitAttachment$;
import scala.reflect.internal.StdAttachments$TypeParamVarargsAttachment$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.Position;
import scala.reflect.macros.Attachments;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\r5e!C\u0001\u0003!\u0003\r\t!CBC\u00059\u0019F\u000fZ!ui\u0006\u001c\u0007.\\3oiNT!a\u0001\u0003\u0002\u0011%tG/\u001a:oC2T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\r\u001b\u00051\u0011BA\u0007\u0007\u0005\u0019\te.\u001f*fM\")q\u0002\u0001C\u0001!\u00051A%\u001b8ji\u0012\"\u0012!\u0005\t\u0003\u0017II!a\u0005\u0004\u0003\tUs\u0017\u000e\u001e\u0004\b+\u0001\u0001\n1!\u0001\u0017\u0005)\tE\u000f^1dQ\u0006\u0014G.Z\n\u0003))AQa\u0004\u000b\u0005\u0002AAq!\u0007\u000bA\u0002\u0013E!$\u0001\u0004sC^\fG\u000f^\u000b\u00027I\u0011AD\b\u0004\u0005;Q\u00011D\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0002 E5\t\u0001E\u0003\u0002\"\t\u00051Q.Y2s_NL!a\t\u0011\u0003\u0017\u0005#H/Y2i[\u0016tGo]\u0003\u0005Kq\u0001cEA\u0002Q_N\u0004\"a\n\u0015\u000e\u0003\u0001I!!\u000b\u0016\u0003\u0011A{7/\u001b;j_:L!a\u000b\u0002\u0003\u0013A{7/\u001b;j_:\u001c\bbB\u0017\u0015\u0001\u0004%\tBL\u0001\u000be\u0006<\u0018\r\u001e;`I\u0015\fHCA\t0\u0011\u001d\u0001D&!AA\u0002m\t1\u0001\u001f\u00132\u0011\u0019\u0011D\u0003)Q\u00057\u00059!/Y<biR\u0004\u0003\"\u0002\u001b\u0015\t\u0003Q\u0012aC1ui\u0006\u001c\u0007.\\3oiNDQA\u000e\u000b\u0005\u0002]\nab]3u\u0003R$\u0018m\u00195nK:$8\u000f\u0006\u00029s5\tA\u0003C\u00035k\u0001\u0007!H\u0005\u0002<=\u0019!Q\u0004\u0006\u0001;\u000b\u0011)3\b\t\u0014\t\u000by\"B\u0011A \u0002!U\u0004H-\u0019;f\u0003R$\u0018m\u00195nK:$XC\u0001!K)\t\t5\u000b\u0006\u00029\u0005\"91)PA\u0001\u0002\b!\u0015AC3wS\u0012,gnY3%cA\u0019QI\u0012%\u000e\u0003\u0011I!a\u0012\u0003\u0003\u0011\rc\u0017m]:UC\u001e\u0004\"!\u0013&\r\u0001\u0011)1*\u0010b\u0001\u0019\n\tA+\u0005\u0002N!B\u00111BT\u0005\u0003\u001f\u001a\u0011qAT8uQ&tw\r\u0005\u0002\f#&\u0011!K\u0002\u0002\u0004\u0003:L\b\"\u0002+>\u0001\u0004A\u0015AC1ui\u0006\u001c\u0007.\\3oi\")a\u000b\u0006C\u0001/\u0006\u0001\"/Z7pm\u0016\fE\u000f^1dQ6,g\u000e^\u000b\u00031v#\"\u0001O-\t\u000fi+\u0016\u0011!a\u00027\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u0007\u00153E\f\u0005\u0002J;\u0012)1*\u0016b\u0001\u0019\")q\f\u0006C\u0001A\u0006i\u0001.Y:BiR\f7\r[7f]R,\"!Y5\u0015\u0005\t,\u0007CA\u0006d\u0013\t!gAA\u0004C_>dW-\u00198\t\u000f\u0019t\u0016\u0011!a\u0002O\u0006QQM^5eK:\u001cW\rJ\u001a\u0011\u0007\u00153\u0005\u000e\u0005\u0002JS\u0012)1J\u0018b\u0001\u0019\")1\u000e\u0006C\u0001Y\u0006\u0019\u0001o\\:\u0016\u0003\u0019BQA\u001c\u000b\u0005\u0002=\fq\u0001]8t?\u0012*\u0017\u000f\u0006\u0002\u0012a\")1.\u001ca\u0001M!)!\u000f\u0006C\u0001g\u000611/\u001a;Q_N$\"\u0001\u000f;\t\u000bU\f\b\u0019\u0001\u0014\u0002\r9,w\u000f]8t\r\u001d9\b\u0001%A\u0012\u0002a\u0014A#S7q_J$\u0018M\u00197f\u0003R$\u0018m\u00195nK:$8C\u0001<\u000b\u0011\u0015QhO\"\u0001|\u0003AIW\u000e]8si\u0006#H/Y2i[\u0016tG\u000f\u0006\u0002}{6\ta\u000fC\u0003\u007fs\u0002\u0007q0\u0001\u0005j[B|'\u000f^3s!\r9\u0013\u0011A\u0005\u0005\u0003\u0007\t)A\u0001\u0005J[B|'\u000f^3s\u0013\u0011\t9!!\u0003\u0003\u0013%sG/\u001a:oC2\u001c(bAA\u0006\t\u0005\u0019\u0011\r]5\u0007\u0013\u0005=\u0001\u0001%A\u0002\u0002\u0005E!a\u0004)mC&t\u0017\t\u001e;bG\"lWM\u001c;\u0014\u000b\u00055!\"a\u0005\u0011\u0005\u001d2\bBB\b\u0002\u000e\u0011\u0005\u0001\u0003C\u0004{\u0003\u001b!\t!!\u0007\u0015\t\u0005m\u0011QD\u0007\u0003\u0003\u001bAaA`A\f\u0001\u0004yhABA\u0011\u0001\u0001\u000b\u0019C\u0001\u0012D_6\u0004x.\u001e8e)f\u0004X\r\u0016:fK>\u0013\u0018nZ5oC2\fE\u000f^1dQ6,g\u000e^\n\b\u0003?Q\u0011QEA\u0016!\rY\u0011qE\u0005\u0004\u0003S1!a\u0002)s_\u0012,8\r\u001e\t\u0004\u0017\u00055\u0012bAA\u0018\r\ta1+\u001a:jC2L'0\u00192mK\"Y\u00111GA\u0010\u0005+\u0007I\u0011AA\u001b\u0003\u001d\u0001\u0018M]3oiN,\"!a\u000e\u0011\r\u0005e\u0012qHA#\u001d\rY\u00111H\u0005\u0004\u0003{1\u0011a\u00029bG.\fw-Z\u0005\u0005\u0003\u0003\n\u0019E\u0001\u0003MSN$(bAA\u001f\rA\u0019q%a\u0012\n\t\u0005%\u00131\n\u0002\u0005)J,W-C\u0002\u0002N\t\u0011Q\u0001\u0016:fKND1\"!\u0015\u0002 \tE\t\u0015!\u0003\u00028\u0005A\u0001/\u0019:f]R\u001c\b\u0005C\u0006\u0002V\u0005}!Q3A\u0005\u0002\u0005U\u0012!B:uCR\u001c\bbCA-\u0003?\u0011\t\u0012)A\u0005\u0003o\taa\u001d;biN\u0004\u0003\u0002CA/\u0003?!\t!a\u0018\u0002\rqJg.\u001b;?)\u0019\t\t'a\u0019\u0002fA\u0019q%a\b\t\u0011\u0005M\u00121\fa\u0001\u0003oA\u0001\"!\u0016\u0002\\\u0001\u0007\u0011q\u0007\u0005\u000b\u0003S\ny\"!A\u0005\u0002\u0005-\u0014\u0001B2paf$b!!\u0019\u0002n\u0005=\u0004BCA\u001a\u0003O\u0002\n\u00111\u0001\u00028!Q\u0011QKA4!\u0003\u0005\r!a\u000e\t\u0015\u0005M\u0014qDI\u0001\n\u0003\t)(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005]$\u0006BA\u001c\u0003sZ#!a\u001f\u0011\t\u0005u\u0014qQ\u0007\u0003\u0003\u007fRA!!!\u0002\u0004\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003\u000b3\u0011AC1o]>$\u0018\r^5p]&!\u0011\u0011RA@\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0005\u000b\u0003\u001b\u000by\"%A\u0005\u0002\u0005U\u0014AD2paf$C-\u001a4bk2$HE\r\u0005\u000b\u0003#\u000by\"!A\u0005B\u0005M\u0015!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002\u0016B!\u0011qSAQ\u001b\t\tIJ\u0003\u0003\u0002\u001c\u0006u\u0015\u0001\u00027b]\u001eT!!a(\u0002\t)\fg/Y\u0005\u0005\u0003G\u000bIJ\u0001\u0004TiJLgn\u001a\u0005\u000b\u0003O\u000by\"!A\u0005\u0002\u0005%\u0016\u0001\u00049s_\u0012,8\r^!sSRLXCAAV!\rY\u0011QV\u0005\u0004\u0003_3!aA%oi\"Q\u00111WA\u0010\u0003\u0003%\t!!.\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019\u0001+a.\t\u0013A\n\t,!AA\u0002\u0005-\u0006BCA^\u0003?\t\t\u0011\"\u0011\u0002>\u0006y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002@B)\u0011\u0011YAd!6\u0011\u00111\u0019\u0006\u0004\u0003\u000b4\u0011AC2pY2,7\r^5p]&!\u0011\u0011ZAb\u0005!IE/\u001a:bi>\u0014\bBCAg\u0003?\t\t\u0011\"\u0001\u0002P\u0006A1-\u00198FcV\fG\u000eF\u0002c\u0003#D\u0001\u0002MAf\u0003\u0003\u0005\r\u0001\u0015\u0005\u000b\u0003+\fy\"!A\u0005B\u0005]\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005-\u0006BCAn\u0003?\t\t\u0011\"\u0011\u0002^\u0006AAo\\*ue&tw\r\u0006\u0002\u0002\u0016\"Q\u0011\u0011]A\u0010\u0003\u0003%\t%a9\u0002\r\u0015\fX/\u00197t)\r\u0011\u0017Q\u001d\u0005\ta\u0005}\u0017\u0011!a\u0001!\u001eI\u0011\u0011\u001e\u0001\u0002\u0002#\u0005\u00111^\u0001#\u0007>l\u0007o\\;oIRK\b/\u001a+sK\u0016|%/[4j]\u0006d\u0017\t\u001e;bG\"lWM\u001c;\u0011\u0007\u001d\niOB\u0005\u0002\"\u0001\t\t\u0011#\u0001\u0002pN1\u0011Q^Ay\u0003W\u0001\"\"a=\u0002z\u0006]\u0012qGA1\u001b\t\t)PC\u0002\u0002x\u001a\tqA];oi&lW-\u0003\u0003\u0002|\u0006U(!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oe!A\u0011QLAw\t\u0003\ty\u0010\u0006\u0002\u0002l\"Q\u00111\\Aw\u0003\u0003%)%!8\t\u0015\t\u0015\u0011Q^A\u0001\n\u0003\u00139!A\u0003baBd\u0017\u0010\u0006\u0004\u0002b\t%!1\u0002\u0005\t\u0003g\u0011\u0019\u00011\u0001\u00028!A\u0011Q\u000bB\u0002\u0001\u0004\t9\u0004\u0003\u0006\u0003\u0010\u00055\u0018\u0011!CA\u0005#\tq!\u001e8baBd\u0017\u0010\u0006\u0003\u0003\u0014\t}\u0001#B\u0006\u0003\u0016\te\u0011b\u0001B\f\r\t1q\n\u001d;j_:\u0004ra\u0003B\u000e\u0003o\t9$C\u0002\u0003\u001e\u0019\u0011a\u0001V;qY\u0016\u0014\u0004B\u0003B\u0011\u0005\u001b\t\t\u00111\u0001\u0002b\u0005\u0019\u0001\u0010\n\u0019\b\u000f\t\u0015\u0002\u0001#!\u0003(\u0005q\")Y2lcV|G/\u001a3JI\u0016tG/\u001b4jKJ\fE\u000f^1dQ6,g\u000e\u001e\t\u0004O\t%ba\u0002B\u0016\u0001!\u0005%Q\u0006\u0002\u001f\u0005\u0006\u001c7.];pi\u0016$\u0017\nZ3oi&4\u0017.\u001a:BiR\f7\r[7f]R\u001c\u0012B!\u000b\u000b\u0005_\t)#a\u000b\u0011\u0007\u001d\ni\u0001\u0003\u0005\u0002^\t%B\u0011\u0001B\u001a)\t\u00119\u0003\u0003\u0006\u0002\u0012\n%\u0012\u0011!C!\u0003'C!\"a*\u0003*\u0005\u0005I\u0011AAU\u0011)\t\u0019L!\u000b\u0002\u0002\u0013\u0005!1\b\u000b\u0004!\nu\u0002\"\u0003\u0019\u0003:\u0005\u0005\t\u0019AAV\u0011)\tYL!\u000b\u0002\u0002\u0013\u0005\u0013Q\u0018\u0005\u000b\u0003\u001b\u0014I#!A\u0005\u0002\t\rCc\u00012\u0003F!A\u0001G!\u0011\u0002\u0002\u0003\u0007\u0001\u000b\u0003\u0006\u0002V\n%\u0012\u0011!C!\u0003/D!\"a7\u0003*\u0005\u0005I\u0011IAo\u000f\u001d\u0011i\u0005\u0001EA\u0005\u001f\nQBR8s\u0003R$\u0018m\u00195nK:$\bcA\u0014\u0003R\u00199!1\u000b\u0001\t\u0002\nU#!\u0004$pe\u0006#H/Y2i[\u0016tGoE\u0005\u0003R)\u0011y#!\n\u0002,!A\u0011Q\fB)\t\u0003\u0011I\u0006\u0006\u0002\u0003P!Q\u0011\u0011\u0013B)\u0003\u0003%\t%a%\t\u0015\u0005\u001d&\u0011KA\u0001\n\u0003\tI\u000b\u0003\u0006\u00024\nE\u0013\u0011!C\u0001\u0005C\"2\u0001\u0015B2\u0011%\u0001$qLA\u0001\u0002\u0004\tY\u000b\u0003\u0006\u0002<\nE\u0013\u0011!C!\u0003{C!\"!4\u0003R\u0005\u0005I\u0011\u0001B5)\r\u0011'1\u000e\u0005\ta\t\u001d\u0014\u0011!a\u0001!\"Q\u0011Q\u001bB)\u0003\u0003%\t%a6\t\u0015\u0005m'\u0011KA\u0001\n\u0003\ninB\u0004\u0003t\u0001A\tI!\u001e\u0002/MKh\u000e\u001e5fi&\u001cWK\\5u\u0003R$\u0018m\u00195nK:$\bcA\u0014\u0003x\u00199!\u0011\u0010\u0001\t\u0002\nm$aF*z]RDW\r^5d+:LG/\u0011;uC\u000eDW.\u001a8u'%\u00119H\u0003B\u0018\u0003K\tY\u0003\u0003\u0005\u0002^\t]D\u0011\u0001B@)\t\u0011)\b\u0003\u0006\u0002\u0012\n]\u0014\u0011!C!\u0003'C!\"a*\u0003x\u0005\u0005I\u0011AAU\u0011)\t\u0019La\u001e\u0002\u0002\u0013\u0005!q\u0011\u000b\u0004!\n%\u0005\"\u0003\u0019\u0003\u0006\u0006\u0005\t\u0019AAV\u0011)\tYLa\u001e\u0002\u0002\u0013\u0005\u0013Q\u0018\u0005\u000b\u0003\u001b\u00149(!A\u0005\u0002\t=Ec\u00012\u0003\u0012\"A\u0001G!$\u0002\u0002\u0003\u0007\u0001\u000b\u0003\u0006\u0002V\n]\u0014\u0011!C!\u0003/D!\"a7\u0003x\u0005\u0005I\u0011IAo\r\u0019\u0011I\n\u0001!\u0003\u001c\n)2+\u001e2qCR$XM\u001d8t\u0003R$\u0018m\u00195nK:$8c\u0002BL\u0015\u0005\u0015\u00121\u0006\u0005\f\u0005?\u00139J!f\u0001\n\u0003\t)$\u0001\u0005qCR$XM\u001d8t\u0011-\u0011\u0019Ka&\u0003\u0012\u0003\u0006I!a\u000e\u0002\u0013A\fG\u000f^3s]N\u0004\u0003\u0002CA/\u0005/#\tAa*\u0015\t\t%&1\u0016\t\u0004O\t]\u0005\u0002\u0003BP\u0005K\u0003\r!a\u000e\t\u0015\u0005%$qSA\u0001\n\u0003\u0011y\u000b\u0006\u0003\u0003*\nE\u0006B\u0003BP\u0005[\u0003\n\u00111\u0001\u00028!Q\u00111\u000fBL#\u0003%\t!!\u001e\t\u0015\u0005E%qSA\u0001\n\u0003\n\u0019\n\u0003\u0006\u0002(\n]\u0015\u0011!C\u0001\u0003SC!\"a-\u0003\u0018\u0006\u0005I\u0011\u0001B^)\r\u0001&Q\u0018\u0005\na\te\u0016\u0011!a\u0001\u0003WC!\"a/\u0003\u0018\u0006\u0005I\u0011IA_\u0011)\tiMa&\u0002\u0002\u0013\u0005!1\u0019\u000b\u0004E\n\u0015\u0007\u0002\u0003\u0019\u0003B\u0006\u0005\t\u0019\u0001)\t\u0015\u0005U'qSA\u0001\n\u0003\n9\u000e\u0003\u0006\u0002\\\n]\u0015\u0011!C!\u0003;D!\"!9\u0003\u0018\u0006\u0005I\u0011\tBg)\r\u0011'q\u001a\u0005\ta\t-\u0017\u0011!a\u0001!\u001eI!1\u001b\u0001\u0002\u0002#\u0005!Q[\u0001\u0016'V\u0014\u0007/\u0019;uKJt7/\u0011;uC\u000eDW.\u001a8u!\r9#q\u001b\u0004\n\u00053\u0003\u0011\u0011!E\u0001\u00053\u001cbAa6\u0003\\\u0006-\u0002\u0003CAz\u0005;\f9D!+\n\t\t}\u0017Q\u001f\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0002CA/\u0005/$\tAa9\u0015\u0005\tU\u0007BCAn\u0005/\f\t\u0011\"\u0012\u0002^\"Q!Q\u0001Bl\u0003\u0003%\tI!;\u0015\t\t%&1\u001e\u0005\t\u0005?\u00139\u000f1\u0001\u00028!Q!q\u0002Bl\u0003\u0003%\tIa<\u0015\t\tE(1\u001f\t\u0006\u0017\tU\u0011q\u0007\u0005\u000b\u0005C\u0011i/!AA\u0002\t%va\u0002B|\u0001!\u0005%\u0011`\u0001\u001c\u0017:|wO\u001c#je\u0016\u001cGoU;cG2\f7o]3t\u0007\u0006dG.\u001a3\u0011\u0007\u001d\u0012YPB\u0004\u0003~\u0002A\tIa@\u00037-swn\u001e8ESJ,7\r^*vE\u000ed\u0017m]:fg\u000e\u000bG\u000e\\3e'%\u0011YP\u0003B\u0018\u0003K\tY\u0003\u0003\u0005\u0002^\tmH\u0011AB\u0002)\t\u0011I\u0010\u0003\u0006\u0002\u0012\nm\u0018\u0011!C!\u0003'C!\"a*\u0003|\u0006\u0005I\u0011AAU\u0011)\t\u0019La?\u0002\u0002\u0013\u000511\u0002\u000b\u0004!\u000e5\u0001\"\u0003\u0019\u0004\n\u0005\u0005\t\u0019AAV\u0011)\tYLa?\u0002\u0002\u0013\u0005\u0013Q\u0018\u0005\u000b\u0003\u001b\u0014Y0!A\u0005\u0002\rMAc\u00012\u0004\u0016!A\u0001g!\u0005\u0002\u0002\u0003\u0007\u0001\u000b\u0003\u0006\u0002V\nm\u0018\u0011!C!\u0003/D!\"a7\u0003|\u0006\u0005I\u0011IAo\r\u0019\u0019i\u0002\u0001!\u0004 \tQB+\u001f9f!\u0006\u0014\u0018-\u001c,be\u0006\u0014xm]!ui\u0006\u001c\u0007.\\3oiN911\u0004\u0006\u0002&\u0005-\u0002bCB\u0012\u00077\u0011)\u001a!C\u0001\u0007K\tA\u0002^=qKB\u000b'/Y7SK\u001a,\"aa\n\u0011\u0007\u001d\u001aI#\u0003\u0003\u0004,\r5\"\u0001\u0002+za\u0016L1aa\f\u0003\u0005\u0015!\u0016\u0010]3t\u0011-\u0019\u0019da\u0007\u0003\u0012\u0003\u0006Iaa\n\u0002\u001bQL\b/\u001a)be\u0006l'+\u001a4!\u0011!\tifa\u0007\u0005\u0002\r]B\u0003BB\u001d\u0007w\u00012aJB\u000e\u0011!\u0019\u0019c!\u000eA\u0002\r\u001d\u0002BCA5\u00077\t\t\u0011\"\u0001\u0004@Q!1\u0011HB!\u0011)\u0019\u0019c!\u0010\u0011\u0002\u0003\u00071q\u0005\u0005\u000b\u0003g\u001aY\"%A\u0005\u0002\r\u0015SCAB$U\u0011\u00199#!\u001f\t\u0015\u0005E51DA\u0001\n\u0003\n\u0019\n\u0003\u0006\u0002(\u000em\u0011\u0011!C\u0001\u0003SC!\"a-\u0004\u001c\u0005\u0005I\u0011AB()\r\u00016\u0011\u000b\u0005\na\r5\u0013\u0011!a\u0001\u0003WC!\"a/\u0004\u001c\u0005\u0005I\u0011IA_\u0011)\tima\u0007\u0002\u0002\u0013\u00051q\u000b\u000b\u0004E\u000ee\u0003\u0002\u0003\u0019\u0004V\u0005\u0005\t\u0019\u0001)\t\u0015\u0005U71DA\u0001\n\u0003\n9\u000e\u0003\u0006\u0002\\\u000em\u0011\u0011!C!\u0003;D!\"!9\u0004\u001c\u0005\u0005I\u0011IB1)\r\u001171\r\u0005\ta\r}\u0013\u0011!a\u0001!\u001eI1q\r\u0001\u0002\u0002#\u00051\u0011N\u0001\u001b)f\u0004X\rU1sC64\u0016M]1sON\fE\u000f^1dQ6,g\u000e\u001e\t\u0004O\r-d!CB\u000f\u0001\u0005\u0005\t\u0012AB7'\u0019\u0019Yga\u001c\u0002,AA\u00111\u001fBo\u0007O\u0019I\u0004\u0003\u0005\u0002^\r-D\u0011AB:)\t\u0019I\u0007\u0003\u0006\u0002\\\u000e-\u0014\u0011!C#\u0003;D!B!\u0002\u0004l\u0005\u0005I\u0011QB=)\u0011\u0019Ida\u001f\t\u0011\r\r2q\u000fa\u0001\u0007OA!Ba\u0004\u0004l\u0005\u0005I\u0011QB@)\u0011\u0019\tia!\u0011\u000b-\u0011)ba\n\t\u0015\t\u00052QPA\u0001\u0002\u0004\u0019I\u0004\u0005\u0003\u0004\b\u000e%U\"\u0001\u0002\n\u0007\r-%AA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007")
public interface StdAttachments {
    public StdAttachments$CompoundTypeTreeOriginalAttachment$ CompoundTypeTreeOriginalAttachment();

    public StdAttachments$BackquotedIdentifierAttachment$ BackquotedIdentifierAttachment();

    public StdAttachments$ForAttachment$ ForAttachment();

    public StdAttachments$SyntheticUnitAttachment$ SyntheticUnitAttachment();

    public StdAttachments$SubpatternsAttachment$ SubpatternsAttachment();

    public StdAttachments$KnownDirectSubclassesCalled$ KnownDirectSubclassesCalled();

    public StdAttachments$TypeParamVarargsAttachment$ TypeParamVarargsAttachment();

    public interface Attachable {
        public Attachments rawatt();

        @TraitSetter
        public void rawatt_$eq(Attachments var1);

        public Attachments attachments();

        public Attachable setAttachments(Attachments var1);

        public <T> Attachable updateAttachment(T var1, ClassTag<T> var2);

        public <T> Attachable removeAttachment(ClassTag<T> var1);

        public <T> boolean hasAttachment(ClassTag<T> var1);

        public Position pos();

        public void pos_$eq(Position var1);

        public Attachable setPos(Position var1);

        public /* synthetic */ StdAttachments scala$reflect$internal$StdAttachments$Attachable$$$outer();
    }

    public interface PlainAttachment
    extends ImportableAttachment {
        @Override
        public PlainAttachment importAttachment(Internals.Importer var1);

        public /* synthetic */ StdAttachments scala$reflect$internal$StdAttachments$PlainAttachment$$$outer();
    }

    public interface ImportableAttachment {
        public ImportableAttachment importAttachment(Internals.Importer var1);
    }

    public class SubpatternsAttachment
    implements Product,
    Serializable {
        private final List<Trees.Tree> patterns;
        public final /* synthetic */ SymbolTable $outer;

        public List<Trees.Tree> patterns() {
            return this.patterns;
        }

        public SubpatternsAttachment copy(List<Trees.Tree> patterns) {
            return new SubpatternsAttachment(this.scala$reflect$internal$StdAttachments$SubpatternsAttachment$$$outer(), patterns);
        }

        public List<Trees.Tree> copy$default$1() {
            return this.patterns();
        }

        @Override
        public String productPrefix() {
            return "SubpatternsAttachment";
        }

        @Override
        public int productArity() {
            return 1;
        }

        @Override
        public Object productElement(int x$1) {
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 0: 
            }
            return this.patterns();
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof SubpatternsAttachment;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$._hashCode(this);
        }

        public String toString() {
            return ScalaRunTime$.MODULE$._toString(this);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            if (!(x$1 instanceof SubpatternsAttachment)) return false;
            if (((SubpatternsAttachment)x$1).scala$reflect$internal$StdAttachments$SubpatternsAttachment$$$outer() != this.scala$reflect$internal$StdAttachments$SubpatternsAttachment$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            SubpatternsAttachment subpatternsAttachment = (SubpatternsAttachment)x$1;
            List<Trees.Tree> list2 = this.patterns();
            List<Trees.Tree> list3 = subpatternsAttachment.patterns();
            if (list2 == null) {
                if (list3 != null) {
                    return false;
                }
            } else if (!((Object)list2).equals(list3)) return false;
            if (!subpatternsAttachment.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$StdAttachments$SubpatternsAttachment$$$outer() {
            return this.$outer;
        }

        public SubpatternsAttachment(SymbolTable $outer, List<Trees.Tree> patterns) {
            this.patterns = patterns;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }

    public class TypeParamVarargsAttachment
    implements Product,
    Serializable {
        private final Types.Type typeParamRef;
        public final /* synthetic */ SymbolTable $outer;

        public Types.Type typeParamRef() {
            return this.typeParamRef;
        }

        public TypeParamVarargsAttachment copy(Types.Type typeParamRef) {
            return new TypeParamVarargsAttachment(this.scala$reflect$internal$StdAttachments$TypeParamVarargsAttachment$$$outer(), typeParamRef);
        }

        public Types.Type copy$default$1() {
            return this.typeParamRef();
        }

        @Override
        public String productPrefix() {
            return "TypeParamVarargsAttachment";
        }

        @Override
        public int productArity() {
            return 1;
        }

        @Override
        public Object productElement(int x$1) {
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 0: 
            }
            return this.typeParamRef();
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof TypeParamVarargsAttachment;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$._hashCode(this);
        }

        public String toString() {
            return ScalaRunTime$.MODULE$._toString(this);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            if (!(x$1 instanceof TypeParamVarargsAttachment)) return false;
            if (((TypeParamVarargsAttachment)x$1).scala$reflect$internal$StdAttachments$TypeParamVarargsAttachment$$$outer() != this.scala$reflect$internal$StdAttachments$TypeParamVarargsAttachment$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            TypeParamVarargsAttachment typeParamVarargsAttachment = (TypeParamVarargsAttachment)x$1;
            Types.Type type = this.typeParamRef();
            Types.Type type2 = typeParamVarargsAttachment.typeParamRef();
            if (type == null) {
                if (type2 != null) {
                    return false;
                }
            } else if (!type.equals(type2)) return false;
            if (!typeParamVarargsAttachment.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$StdAttachments$TypeParamVarargsAttachment$$$outer() {
            return this.$outer;
        }

        public TypeParamVarargsAttachment(SymbolTable $outer, Types.Type typeParamRef) {
            this.typeParamRef = typeParamRef;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }

    public class CompoundTypeTreeOriginalAttachment
    implements Product,
    Serializable {
        private final List<Trees.Tree> parents;
        private final List<Trees.Tree> stats;
        public final /* synthetic */ SymbolTable $outer;

        public List<Trees.Tree> parents() {
            return this.parents;
        }

        public List<Trees.Tree> stats() {
            return this.stats;
        }

        public CompoundTypeTreeOriginalAttachment copy(List<Trees.Tree> parents2, List<Trees.Tree> stats) {
            return new CompoundTypeTreeOriginalAttachment(this.scala$reflect$internal$StdAttachments$CompoundTypeTreeOriginalAttachment$$$outer(), parents2, stats);
        }

        public List<Trees.Tree> copy$default$1() {
            return this.parents();
        }

        public List<Trees.Tree> copy$default$2() {
            return this.stats();
        }

        @Override
        public String productPrefix() {
            return "CompoundTypeTreeOriginalAttachment";
        }

        @Override
        public int productArity() {
            return 2;
        }

        @Override
        public Object productElement(int x$1) {
            List<Trees.Tree> list2;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 1: {
                    list2 = this.stats();
                    break;
                }
                case 0: {
                    list2 = this.parents();
                }
            }
            return list2;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof CompoundTypeTreeOriginalAttachment;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$._hashCode(this);
        }

        public String toString() {
            return ScalaRunTime$.MODULE$._toString(this);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            if (!(x$1 instanceof CompoundTypeTreeOriginalAttachment)) return false;
            if (((CompoundTypeTreeOriginalAttachment)x$1).scala$reflect$internal$StdAttachments$CompoundTypeTreeOriginalAttachment$$$outer() != this.scala$reflect$internal$StdAttachments$CompoundTypeTreeOriginalAttachment$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            CompoundTypeTreeOriginalAttachment compoundTypeTreeOriginalAttachment = (CompoundTypeTreeOriginalAttachment)x$1;
            List<Trees.Tree> list2 = this.parents();
            List<Trees.Tree> list3 = compoundTypeTreeOriginalAttachment.parents();
            if (list2 == null) {
                if (list3 != null) {
                    return false;
                }
            } else if (!((Object)list2).equals(list3)) return false;
            List<Trees.Tree> list4 = this.stats();
            List<Trees.Tree> list5 = compoundTypeTreeOriginalAttachment.stats();
            if (list4 == null) {
                if (list5 != null) {
                    return false;
                }
            } else if (!((Object)list4).equals(list5)) return false;
            if (!compoundTypeTreeOriginalAttachment.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$StdAttachments$CompoundTypeTreeOriginalAttachment$$$outer() {
            return this.$outer;
        }

        public CompoundTypeTreeOriginalAttachment(SymbolTable $outer, List<Trees.Tree> parents2, List<Trees.Tree> stats) {
            this.parents = parents2;
            this.stats = stats;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }
}

