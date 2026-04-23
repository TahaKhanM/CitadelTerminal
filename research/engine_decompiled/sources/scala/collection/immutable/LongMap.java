/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.immutable.AbstractMap;
import scala.collection.immutable.List;
import scala.collection.immutable.LongMap$;
import scala.collection.immutable.LongMap$Nil$;
import scala.collection.immutable.LongMapEntryIterator;
import scala.collection.immutable.LongMapKeyIterator;
import scala.collection.immutable.LongMapUtils$;
import scala.collection.immutable.LongMapValueIterator;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;
import scala.sys.package$;

@ScalaSignature(bytes="\u0006\u0001\u0011\rt!B\u0001\u0003\u0011\u0003I\u0011a\u0002'p]\u001el\u0015\r\u001d\u0006\u0003\u0007\u0011\t\u0011\"[7nkR\f'\r\\3\u000b\u0005\u00151\u0011AC2pY2,7\r^5p]*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u0005)YQ\"\u0001\u0002\u0007\u000b1\u0011\u0001\u0012A\u0007\u0003\u000f1{gnZ'baN\u00111B\u0004\t\u0003\u001fAi\u0011AB\u0005\u0003#\u0019\u0011a!\u00118z%\u00164\u0007\"B\n\f\t\u0003!\u0012A\u0002\u001fj]&$h\bF\u0001\n\u0011\u001512\u0002b\u0001\u0018\u00031\u0019\u0017M\u001c\"vS2$gI]8n+\u0015A2qTBT+\u0005I\"c\u0001\u000e\u000f9\u0019!1$\u0006\u0001\u001a\u00051a$/\u001a4j]\u0016lWM\u001c;?!\u001di\u0002EIBR\u0007Wk\u0011A\b\u0006\u0003?\u0011\tqaZ3oKJL7-\u0003\u0002\"=\ta1)\u00198Ck&dGM\u0012:p[B!!bIBO\r\u0015a!!!\t%+\t)Cg\u0005\u0003$Mu\u0002\u0005\u0003\u0002\u0006(SIJ!\u0001\u000b\u0002\u0003\u0017\u0005\u00137\u000f\u001e:bGRl\u0015\r\u001d\t\u0003U5r!AC\u0016\n\u00051\u0012\u0011\u0001\u0004'p]\u001el\u0015\r]+uS2\u001c\u0018B\u0001\u00180\u0005\u0011auN\\4\n\u00059\u0002$BA\u0019\u001f\u00035\u0011\u0015\u000e^(qKJ\fG/[8ogB\u00111\u0007\u000e\u0007\u0001\t\u0019)4\u0005\"b\u0001m\t\tA+\u0005\u00028uA\u0011q\u0002O\u0005\u0003s\u0019\u0011qAT8uQ&tw\r\u0005\u0002\u0010w%\u0011AH\u0002\u0002\u0004\u0003:L\b\u0003\u0002\u0006?SIJ!a\u0010\u0002\u0003\u00075\u000b\u0007\u000fE\u0003\u000b\u0003&\u00124)\u0003\u0002C\u0005\t9Q*\u00199MS.,\u0007c\u0001\u0006$e!)1c\tC\u0001\u000bR\t1\tC\u0003HG\u0011\u0005\u0003*A\u0003f[B$\u00180F\u0001D\u0011\u0015Q5\u0005\"\u0011L\u0003\u0019!x\u000eT5tiV\tA\nE\u0002\u000b\u001b>K!A\u0014\u0002\u0003\t1K7\u000f\u001e\t\u0005\u001fAK#'\u0003\u0002R\r\t1A+\u001e9mKJBQaU\u0012\u0005\u0002Q\u000b\u0001\"\u001b;fe\u0006$xN]\u000b\u0002+B\u0019akV(\u000e\u0003\u0011I!\u0001\u0017\u0003\u0003\u0011%#XM]1u_JDQAW\u0012\u0005Fm\u000bqAZ8sK\u0006\u001c\u0007.\u0006\u0002]MR\u0011Q\f\u0019\t\u0003\u001fyK!a\u0018\u0004\u0003\tUs\u0017\u000e\u001e\u0005\u0006Cf\u0003\rAY\u0001\u0002MB!qbY(f\u0013\t!gAA\u0005Gk:\u001cG/[8ocA\u00111G\u001a\u0003\u0006Of\u0013\rA\u000e\u0002\u0002+\")\u0011n\tC!U\u0006a1.Z=t\u0013R,'/\u0019;peV\t1\u000eE\u0002W/&BQ!\\\u0012\u0005\u00069\f!BZ8sK\u0006\u001c\u0007nS3z)\tiv\u000eC\u0003bY\u0002\u0007\u0001\u000f\u0005\u0003\u0010G&j\u0006\"\u0002:$\t\u0003\u001a\u0018A\u0004<bYV,7/\u0013;fe\u0006$xN]\u000b\u0002iB\u0019ak\u0016\u001a\t\u000bY\u001cCQA<\u0002\u0019\u0019|'/Z1dQZ\u000bG.^3\u0015\u0005uC\b\"B1v\u0001\u0004I\b\u0003B\bdeuCQa_\u0012\u0005Bq\fAb\u001d;sS:<\u0007K]3gSb,\u0012! \t\u0004}\u0006\u001dQ\"A@\u000b\t\u0005\u0005\u00111A\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u0006\u0005!!.\u0019<b\u0013\r\tIa \u0002\u0007'R\u0014\u0018N\\4\t\u000f\u000551\u0005\"\u0011\u0002\u0010\u00059\u0011n]#naRLXCAA\t!\ry\u00111C\u0005\u0004\u0003+1!a\u0002\"p_2,\u0017M\u001c\u0005\b\u00033\u0019C\u0011IA\u000e\u0003\u00191\u0017\u000e\u001c;feR\u00191)!\b\t\u000f\u0005\f9\u00021\u0001\u0002 A)qbY(\u0002\u0012!9\u00111E\u0012\u0005\u0002\u0005\u0015\u0012!\u0003;sC:\u001chm\u001c:n+\u0011\t9#!\f\u0015\t\u0005%\u0012\u0011\u0007\t\u0005\u0015\r\nY\u0003E\u00024\u0003[!q!a\f\u0002\"\t\u0007aGA\u0001T\u0011\u001d\t\u0017\u0011\u0005a\u0001\u0003g\u0001raDA\u001bSI\nY#C\u0002\u00028\u0019\u0011\u0011BR;oGRLwN\u001c\u001a\t\u000f\u0005m2\u0005\"\u0012\u0002>\u0005!1/\u001b>f+\t\ty\u0004E\u0002\u0010\u0003\u0003J1!a\u0011\u0007\u0005\rIe\u000e\u001e\u0005\b\u0003\u000f\u001aCQAA%\u0003\r9W\r\u001e\u000b\u0005\u0003\u0017\n\t\u0006\u0005\u0003\u0010\u0003\u001b\u0012\u0014bAA(\r\t1q\n\u001d;j_:Dq!a\u0015\u0002F\u0001\u0007\u0011&A\u0002lKfDq!a\u0016$\t\u000b\nI&A\u0005hKR|%/\u00127tKV!\u00111LA0)\u0019\ti&a\u0019\u0002fA\u00191'a\u0018\u0005\u0011\u0005=\u0012Q\u000bb\u0001\u0003C\n\"A\r\u001e\t\u000f\u0005M\u0013Q\u000ba\u0001S!I\u0011qMA+\t\u0003\u0007\u0011\u0011N\u0001\bI\u00164\u0017-\u001e7u!\u0015y\u00111NA/\u0013\r\tiG\u0002\u0002\ty\tLh.Y7f}!9\u0011\u0011O\u0012\u0005F\u0005M\u0014!B1qa2LHc\u0001\u001a\u0002v!9\u00111KA8\u0001\u0004I\u0003bBA=G\u0011\u0005\u00111P\u0001\u0006IAdWo]\u000b\u0005\u0003{\n\u0019\t\u0006\u0003\u0002\u0000\u0005\u0015\u0005\u0003\u0002\u0006$\u0003\u0003\u00032aMAB\t!\ty#a\u001eC\u0002\u0005\u0005\u0004\u0002CAD\u0003o\u0002\r!!#\u0002\u0005-4\b#B\bQS\u0005\u0005\u0005bBAGG\u0011\u0005\u0013qR\u0001\bkB$\u0017\r^3e+\u0011\t\t*a&\u0015\r\u0005M\u0015\u0011TAN!\u0011Q1%!&\u0011\u0007M\n9\n\u0002\u0005\u00020\u0005-%\u0019AA1\u0011\u001d\t\u0019&a#A\u0002%B\u0001\"!(\u0002\f\u0002\u0007\u0011QS\u0001\u0006m\u0006dW/\u001a\u0005\b\u0003C\u001bC\u0011AAR\u0003))\b\u000fZ1uK^KG\u000f[\u000b\u0005\u0003K\u000bY\u000b\u0006\u0005\u0002(\u00065\u0016qVAY!\u0011Q1%!+\u0011\u0007M\nY\u000b\u0002\u0005\u00020\u0005}%\u0019AA1\u0011\u001d\t\u0019&a(A\u0002%B\u0001\"!(\u0002 \u0002\u0007\u0011\u0011\u0016\u0005\bC\u0006}\u0005\u0019AAZ!!y\u0011Q\u0007\u001a\u0002*\u0006%\u0006bBA\\G\u0011\u0005\u0011\u0011X\u0001\u0007I5Lg.^:\u0015\u0007\r\u000bY\fC\u0004\u0002T\u0005U\u0006\u0019A\u0015\t\u000f\u0005}6\u0005\"\u0001\u0002B\u0006qQn\u001c3jMf|%OU3n_Z,W\u0003BAb\u0003\u0013$B!!2\u0002LB!!bIAd!\r\u0019\u0014\u0011\u001a\u0003\b\u0003_\tiL1\u00017\u0011\u001d\t\u0017Q\u0018a\u0001\u0003\u001b\u0004raDA\u001bSI\ny\rE\u0003\u0010\u0003\u001b\n9\rC\u0004\u0002T\u000e\"\t!!6\u0002\u0013Ut\u0017n\u001c8XSRDW\u0003BAl\u0003;$b!!7\u0002`\u0006\r\b\u0003\u0002\u0006$\u00037\u00042aMAo\t!\ty#!5C\u0002\u0005\u0005\u0004\u0002CAq\u0003#\u0004\r!!7\u0002\tQD\u0017\r\u001e\u0005\bC\u0006E\u0007\u0019AAs!)y\u0011q]\u0015\u0002\\\u0006m\u00171\\\u0005\u0004\u0003S4!!\u0003$v]\u000e$\u0018n\u001c84\u0011\u001d\tio\tC\u0001\u0003_\f\u0001#\u001b8uKJ\u001cXm\u0019;j_:<\u0016\u000e\u001e5\u0016\r\u0005E(\u0011AA|)\u0019\t\u00190a?\u0003\u0004A!!bIA{!\r\u0019\u0014q\u001f\u0003\b\u0003s\fYO1\u00017\u0005\u0005\u0011\u0006\u0002CAq\u0003W\u0004\r!!@\u0011\t)\u0019\u0013q \t\u0004g\t\u0005AaBA\u0018\u0003W\u0014\rA\u000e\u0005\bC\u0006-\b\u0019\u0001B\u0003!%y\u0011q]\u00153\u0003\u007f\f)\u0010C\u0004\u0003\n\r\"\tAa\u0003\u0002\u0019%tG/\u001a:tK\u000e$\u0018n\u001c8\u0016\t\t5!Q\u0003\u000b\u0004\u0007\n=\u0001\u0002CAq\u0005\u000f\u0001\rA!\u0005\u0011\t)\u0019#1\u0003\t\u0004g\tUAaBA}\u0005\u000f\u0011\rA\u000e\u0005\b\u00053\u0019C\u0011\u0001B\u000e\u0003)!\u0003\u000f\\;tIAdWo]\u000b\u0005\u0005;\u0011\u0019\u0003\u0006\u0003\u0003 \t\u0015\u0002\u0003\u0002\u0006$\u0005C\u00012a\rB\u0012\t!\tyCa\u0006C\u0002\u0005\u0005\u0004\u0002CAq\u0005/\u0001\rAa\b\t\u000f\t%2\u0005\"\u0002\u0003,\u0005Aa-\u001b:ti.+\u00170F\u0001*Q\u0011\u00119Ca\f\u0011\t\tE\"qG\u0007\u0003\u0005gQ1A!\u000e\u0007\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005s\u0011\u0019DA\u0004uC&d'/Z2\t\u000f\tu2\u0005\"\u0002\u0003,\u00059A.Y:u\u0017\u0016L\b\u0006\u0002B\u001e\u0005_Isa\tB\"\u0007\u0013\u00199DB\u0004\u0003F-\u0001%Aa\u0012\u0003\u0007\tKg.\u0006\u0003\u0003J\t=3\u0003\u0003B\"\u0005\u0017\u0012\tFa\u0016\u0011\t)\u0019#Q\n\t\u0004g\t=CaB\u001b\u0003D\u0011\u0015\rA\u000e\t\u0004\u001f\tM\u0013b\u0001B+\r\t9\u0001K]8ek\u000e$\bcA\b\u0003Z%\u0019!1\f\u0004\u0003\u0019M+'/[1mSj\f'\r\\3\t\u0017\t}#1\tBK\u0002\u0013\u0005!1F\u0001\u0007aJ,g-\u001b=\t\u0015\t\r$1\tB\tB\u0003%\u0011&A\u0004qe\u00164\u0017\u000e\u001f\u0011\t\u0017\t\u001d$1\tBK\u0002\u0013\u0005!1F\u0001\u0005[\u0006\u001c8\u000e\u0003\u0006\u0003l\t\r#\u0011#Q\u0001\n%\nQ!\\1tW\u0002B1Ba\u001c\u0003D\tU\r\u0011\"\u0001\u0003r\u0005!A.\u001a4u+\t\u0011Y\u0005C\u0006\u0003v\t\r#\u0011#Q\u0001\n\t-\u0013!\u00027fMR\u0004\u0003b\u0003B=\u0005\u0007\u0012)\u001a!C\u0001\u0005c\nQA]5hQRD1B! \u0003D\tE\t\u0015!\u0003\u0003L\u00051!/[4ii\u0002Bqa\u0005B\"\t\u0003\u0011\t\t\u0006\u0006\u0003\u0004\n\u001d%\u0011\u0012BF\u0005\u001b\u0003bA!\"\u0003D\t5S\"A\u0006\t\u000f\t}#q\u0010a\u0001S!9!q\rB@\u0001\u0004I\u0003\u0002\u0003B8\u0005\u007f\u0002\rAa\u0013\t\u0011\te$q\u0010a\u0001\u0005\u0017B\u0001B!%\u0003D\u0011\u0005!1S\u0001\u0004E&tW\u0003\u0002BK\u00057#bAa&\u0003\u001e\n}\u0005\u0003\u0002\u0006$\u00053\u00032a\rBN\t\u001d\tyCa$C\u0002YB\u0001Ba\u001c\u0003\u0010\u0002\u0007!q\u0013\u0005\t\u0005s\u0012y\t1\u0001\u0003\u0018\"Q!1\u0015B\"\u0003\u0003%\tA!*\u0002\t\r|\u0007/_\u000b\u0005\u0005O\u0013i\u000b\u0006\u0006\u0003*\n=&\u0011\u0017BZ\u0005o\u0003bA!\"\u0003D\t-\u0006cA\u001a\u0003.\u00121QG!)C\u0002YB\u0011Ba\u0018\u0003\"B\u0005\t\u0019A\u0015\t\u0013\t\u001d$\u0011\u0015I\u0001\u0002\u0004I\u0003B\u0003B8\u0005C\u0003\n\u00111\u0001\u00036B!!b\tBV\u0011)\u0011IH!)\u0011\u0002\u0003\u0007!Q\u0017\u0005\u000b\u0005w\u0013\u0019%%A\u0005\u0002\tu\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0005\u0005\u007f\u0013\t.\u0006\u0002\u0003B*\u001a\u0011Fa1,\u0005\t\u0015\u0007\u0003\u0002Bd\u0005\u001bl!A!3\u000b\t\t-'1G\u0001\nk:\u001c\u0007.Z2lK\u0012LAAa4\u0003J\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\rU\u0012IL1\u00017\u0011)\u0011)Na\u0011\u0012\u0002\u0013\u0005!q[\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0011\u0011yL!7\u0005\rU\u0012\u0019N1\u00017\u0011)\u0011iNa\u0011\u0012\u0002\u0013\u0005!q\\\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\u0011\u0011\tO!:\u0016\u0005\t\r(\u0006\u0002B&\u0005\u0007$a!\u000eBn\u0005\u00041\u0004B\u0003Bu\u0005\u0007\n\n\u0011\"\u0001\u0003l\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\"T\u0003\u0002Bq\u0005[$a!\u000eBt\u0005\u00041\u0004\"\u0003By\u0005\u0007\n\t\u0011\"\u0011}\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jq\"Q!Q\u001fB\"\u0003\u0003%\t!!\u0010\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\t\u0015\te(1IA\u0001\n\u0003\u0011Y0\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007i\u0012i\u0010\u0003\u0006\u0003\u0000\n]\u0018\u0011!a\u0001\u0003\u007f\t1\u0001\u001f\u00132\u0011)\u0019\u0019Aa\u0011\u0002\u0002\u0013\u00053QA\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u00111q\u0001\t\u0004-^Sd\u0001CB\u0006\u0017!\u0005%a!\u0004\u0003\u00079KGn\u0005\u0005\u0004\n\r=!\u0011\u000bB,!\rQ1e\u000e\u0005\b'\r%A\u0011AB\n)\t\u0019)\u0002\u0005\u0003\u0003\u0006\u000e%\u0001\u0002CB\r\u0007\u0013!\tea\u0007\u0002\r\u0015\fX/\u00197t)\u0011\t\tb!\b\t\u000f\u0005\u00058q\u0003a\u0001u!I!\u0011_B\u0005\u0003\u0003%\t\u0005 \u0005\u000b\u0005k\u001cI!!A\u0005\u0002\u0005u\u0002B\u0003B}\u0007\u0013\t\t\u0011\"\u0001\u0004&Q\u0019!ha\n\t\u0015\t}81EA\u0001\u0002\u0004\ty\u0004\u0003\u0006\u0004\u0004\r%\u0011\u0011!C!\u0007\u000bA!b!\f\u0004\n\u0005\u0005I\u0011BB\u0018\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0005\rE\u0002c\u0001@\u00044%\u00191QG@\u0003\r=\u0013'.Z2u\r\u001d\u0019Id\u0003!\u0003\u0007w\u00111\u0001V5q+\u0011\u0019ida\u0011\u0014\u0011\r]2q\bB)\u0005/\u0002BAC\u0012\u0004BA\u00191ga\u0011\u0005\u000fU\u001a9\u0004\"b\u0001m!Y\u00111KB\u001c\u0005+\u0007I\u0011\u0001B\u0016\u0011)\u0019Iea\u000e\u0003\u0012\u0003\u0006I!K\u0001\u0005W\u0016L\b\u0005C\u0006\u0002\u001e\u000e]\"Q3A\u0005\u0002\r5SCAB!\u0011-\u0019\tfa\u000e\u0003\u0012\u0003\u0006Ia!\u0011\u0002\rY\fG.^3!\u0011\u001d\u00192q\u0007C\u0001\u0007+\"baa\u0016\u0004Z\rm\u0003C\u0002BC\u0007o\u0019\t\u0005C\u0004\u0002T\rM\u0003\u0019A\u0015\t\u0011\u0005u51\u000ba\u0001\u0007\u0003B\u0001ba\u0018\u00048\u0011\u00051\u0011M\u0001\no&$\bNV1mk\u0016,Baa\u0019\u0004lQ!1QMB7!\u0019\u00199ga\u000e\u0004j9\u0011!\u0002\u0001\t\u0004g\r-DaBA\u0018\u0007;\u0012\rA\u000e\u0005\t\u0007_\u001ai\u00061\u0001\u0004j\u0005\t1\u000f\u0003\u0006\u0003$\u000e]\u0012\u0011!C\u0001\u0007g*Ba!\u001e\u0004|Q11qOB?\u0007\u007f\u0002bA!\"\u00048\re\u0004cA\u001a\u0004|\u00111Qg!\u001dC\u0002YB\u0011\"a\u0015\u0004rA\u0005\t\u0019A\u0015\t\u0015\u0005u5\u0011\u000fI\u0001\u0002\u0004\u0019I\b\u0003\u0006\u0003<\u000e]\u0012\u0013!C\u0001\u0007\u0007+BAa0\u0004\u0006\u00121Qg!!C\u0002YB!B!6\u00048E\u0005I\u0011ABE+\u0011\u0019Yia$\u0016\u0005\r5%\u0006BB!\u0005\u0007$a!NBD\u0005\u00041\u0004\"\u0003By\u0007o\t\t\u0011\"\u0011}\u0011)\u0011)pa\u000e\u0002\u0002\u0013\u0005\u0011Q\b\u0005\u000b\u0005s\u001c9$!A\u0005\u0002\r]Ec\u0001\u001e\u0004\u001a\"Q!q`BK\u0003\u0003\u0005\r!a\u0010\t\u0015\r\r1qGA\u0001\n\u0003\u001a)\u0001E\u00024\u0007?#aa!)\u0016\u0005\u00041$!A!\u0011\u000b=\u0001\u0016f!*\u0011\u0007M\u001a9\u000b\u0002\u0004\u0004*V\u0011\rA\u000e\u0002\u0002\u0005B!!bIBS\u0011\u001995\u0002\"\u0001\u00040V!1\u0011WB\\+\t\u0019\u0019\f\u0005\u0003\u000bG\rU\u0006cA\u001a\u00048\u00121Qg!,C\u0002YBqaa/\f\t\u0003\u0019i,A\u0005tS:<G.\u001a;p]V!1qXBc)\u0019\u0019\tma2\u0004JB!!bIBb!\r\u00194Q\u0019\u0003\u0007k\re&\u0019\u0001\u001c\t\u000f\u0005M3\u0011\u0018a\u0001S!A\u0011QTB]\u0001\u0004\u0019\u0019\rC\u0004\u0002r-!\ta!4\u0016\t\r=7Q\u001b\u000b\u0005\u0007#\u001c9\u000e\u0005\u0003\u000bG\rM\u0007cA\u001a\u0004V\u00121Qga3C\u0002YB\u0001b!7\u0004L\u0002\u000711\\\u0001\u0006K2,Wn\u001d\t\u0006\u001f\ru7\u0011]\u0005\u0004\u0007?4!A\u0003\u001fsKB,\u0017\r^3e}A)q\u0002U\u0015\u0004T\u001eA1Q]\u0006\t\u0002\n\u0019)\"A\u0002OS2<!b!;\f\u0003\u0003E\tAABv\u0003\r!\u0016\u000e\u001d\t\u0005\u0005\u000b\u001biO\u0002\u0006\u0004:-\t\t\u0011#\u0001\u0003\u0007_\u001cRa!<\u000f\u0005/BqaEBw\t\u0003\u0019\u0019\u0010\u0006\u0002\u0004l\"Q1q_Bw\u0003\u0003%)e!?\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012! \u0005\u000b\u0003c\u001ai/!A\u0005\u0002\u000euX\u0003BB\u0000\t\u000b!b\u0001\"\u0001\u0005\b\u0011%\u0001C\u0002BC\u0007o!\u0019\u0001E\u00024\t\u000b!a!NB~\u0005\u00041\u0004bBA*\u0007w\u0004\r!\u000b\u0005\t\u0003;\u001bY\u00101\u0001\u0005\u0004!QAQBBw\u0003\u0003%\t\tb\u0004\u0002\u000fUt\u0017\r\u001d9msV!A\u0011\u0003C\r)\u0011!\u0019\u0002b\u0007\u0011\u000b=\ti\u0005\"\u0006\u0011\u000b=\u0001\u0016\u0006b\u0006\u0011\u0007M\"I\u0002\u0002\u00046\t\u0017\u0011\rA\u000e\u0005\u000b\t;!Y!!AA\u0002\u0011}\u0011a\u0001=%aA1!QQB\u001c\t/A!b!\f\u0004n\u0006\u0005I\u0011BB\u0018\u000f)!)cCA\u0001\u0012\u0003\u0011AqE\u0001\u0004\u0005&t\u0007\u0003\u0002BC\tS1!B!\u0012\f\u0003\u0003E\tA\u0001C\u0016'\u0015!IC\u0004B,\u0011\u001d\u0019B\u0011\u0006C\u0001\t_!\"\u0001b\n\t\u0015\r]H\u0011FA\u0001\n\u000b\u001aI\u0010\u0003\u0006\u0002r\u0011%\u0012\u0011!CA\tk)B\u0001b\u000e\u0005>QQA\u0011\bC \t\u0003\"\u0019\u0005b\u0012\u0011\r\t\u0015%1\tC\u001e!\r\u0019DQ\b\u0003\u0007k\u0011M\"\u0019\u0001\u001c\t\u000f\t}C1\u0007a\u0001S!9!q\rC\u001a\u0001\u0004I\u0003\u0002\u0003B8\tg\u0001\r\u0001\"\u0012\u0011\t)\u0019C1\b\u0005\t\u0005s\"\u0019\u00041\u0001\u0005F!QAQ\u0002C\u0015\u0003\u0003%\t\tb\u0013\u0016\t\u00115C1\f\u000b\u0005\t\u001f\"i\u0006E\u0003\u0010\u0003\u001b\"\t\u0006E\u0005\u0010\t'J\u0013\u0006b\u0016\u0005X%\u0019AQ\u000b\u0004\u0003\rQ+\b\u000f\\35!\u0011Q1\u0005\"\u0017\u0011\u0007M\"Y\u0006\u0002\u00046\t\u0013\u0012\rA\u000e\u0005\u000b\t;!I%!AA\u0002\u0011}\u0003C\u0002BC\u0005\u0007\"I\u0006\u0003\u0006\u0004.\u0011%\u0012\u0011!C\u0005\u0007_\u0001")
public abstract class LongMap<T>
extends AbstractMap<Object, T> {
    public static <T> LongMap<T> singleton(long l, T t) {
        return LongMap$.MODULE$.singleton(l, t);
    }

    public static <A, B> Object canBuildFrom() {
        return LongMap$.MODULE$.canBuildFrom();
    }

    @Override
    public LongMap<T> empty() {
        return LongMap$Nil$.MODULE$;
    }

    @Override
    public List<Tuple2<Object, T>> toList() {
        ListBuffer buffer = new ListBuffer();
        this.foreach((Function1)((Object)new Serializable(this, buffer){
            public static final long serialVersionUID = 0L;
            private final ListBuffer buffer$1;

            public final ListBuffer<Tuple2<Object, T>> apply(Tuple2<Object, T> x$1) {
                return this.buffer$1.$plus$eq(x$1);
            }
            {
                this.buffer$1 = buffer$1;
            }
        }));
        return buffer.toList();
    }

    @Override
    public Iterator<Tuple2<Object, T>> iterator() {
        Iterator<Nothing$> iterator2 = ((Object)LongMap$Nil$.MODULE$).equals(this) ? Iterator$.MODULE$.empty() : new LongMapEntryIterator(this);
        return iterator2;
    }

    @Override
    public final <U> void foreach(Function1<Tuple2<Object, T>, U> f) {
        block5: {
            block4: {
                block3: {
                    while (this_ instanceof Bin) {
                        Bin bin = (Bin)this_;
                        bin.left().foreach(f);
                        LongMap this_ = bin.right();
                    }
                    if (!(this_ instanceof Tip)) break block3;
                    Tip tip = (Tip)this_;
                    f.apply(new Tuple2(BoxesRunTime.boxToLong(tip.key()), tip.value()));
                    break block4;
                }
                if (!((Object)LongMap$Nil$.MODULE$).equals(this_)) break block5;
            }
            return;
        }
        throw new MatchError(this_);
    }

    @Override
    public Iterator<Object> keysIterator() {
        Iterator<Object> iterator2 = ((Object)LongMap$Nil$.MODULE$).equals(this) ? Iterator$.MODULE$.empty() : new LongMapKeyIterator(this);
        return iterator2;
    }

    public final void foreachKey(Function1<Object, BoxedUnit> f) {
        block5: {
            block4: {
                block3: {
                    while (this_ instanceof Bin) {
                        Bin bin = (Bin)this_;
                        bin.left().foreachKey(f);
                        LongMap this_ = bin.right();
                    }
                    if (!(this_ instanceof Tip)) break block3;
                    Tip tip = (Tip)this_;
                    f.apply$mcVJ$sp(tip.key());
                    break block4;
                }
                if (!((Object)LongMap$Nil$.MODULE$).equals(this_)) break block5;
            }
            return;
        }
        throw new MatchError(this_);
    }

    @Override
    public Iterator<T> valuesIterator() {
        Iterator<Nothing$> iterator2 = ((Object)LongMap$Nil$.MODULE$).equals(this) ? Iterator$.MODULE$.empty() : new LongMapValueIterator<Nothing$>(this);
        return iterator2;
    }

    public final void foreachValue(Function1<T, BoxedUnit> f) {
        block5: {
            block4: {
                block3: {
                    while (this_ instanceof Bin) {
                        Bin bin = (Bin)this_;
                        bin.left().foreachValue(f);
                        LongMap this_ = bin.right();
                    }
                    if (!(this_ instanceof Tip)) break block3;
                    Tip tip = (Tip)this_;
                    f.apply(tip.value());
                    break block4;
                }
                if (!((Object)LongMap$Nil$.MODULE$).equals(this_)) break block5;
            }
            return;
        }
        throw new MatchError(this_);
    }

    @Override
    public String stringPrefix() {
        return "LongMap";
    }

    @Override
    public boolean isEmpty() {
        LongMap$Nil$ longMap$Nil$ = LongMap$Nil$.MODULE$;
        return ((Object)this).equals(longMap$Nil$);
    }

    @Override
    public LongMap<T> filter(Function1<Tuple2<Object, T>, Object> f) {
        block5: {
            LongMap longMap;
            block3: {
                block4: {
                    block2: {
                        if (!(this instanceof Bin)) break block2;
                        Bin bin = (Bin)this;
                        Tuple2<Object, Object> tuple2 = new Tuple2<Object, Object>(bin.left().filter((Function1)f), bin.right().filter((Function1)f));
                        Tuple2<Object, Object> tuple22 = new Tuple2<Object, Object>(tuple2._1(), tuple2._2());
                        LongMap newleft = (LongMap)tuple22._1();
                        LongMap newright = (LongMap)tuple22._2();
                        longMap = bin.left() == newleft && bin.right() == newright ? this : LongMapUtils$.MODULE$.bin(bin.prefix(), bin.mask(), newleft, newright);
                        break block3;
                    }
                    if (!(this instanceof Tip)) break block4;
                    Tip tip = (Tip)this;
                    longMap = BoxesRunTime.unboxToBoolean(f.apply(new Tuple2(BoxesRunTime.boxToLong(tip.key()), tip.value()))) ? this : LongMap$Nil$.MODULE$;
                    break block3;
                }
                if (!((Object)LongMap$Nil$.MODULE$).equals(this)) break block5;
                longMap = LongMap$Nil$.MODULE$;
            }
            return longMap;
        }
        throw new MatchError(this);
    }

    public <S> LongMap<S> transform(Function2<Object, T, S> f) {
        block5: {
            LongMap longMap;
            block3: {
                block4: {
                    block2: {
                        if (!(this instanceof Bin)) break block2;
                        Bin bin = (Bin)this;
                        longMap = bin.bin(bin.left().transform(f), bin.right().transform(f));
                        break block3;
                    }
                    if (!(this instanceof Tip)) break block4;
                    Tip tip = (Tip)this;
                    longMap = tip.withValue(f.apply(BoxesRunTime.boxToLong(tip.key()), tip.value()));
                    break block3;
                }
                if (!((Object)LongMap$Nil$.MODULE$).equals(this)) break block5;
                longMap = LongMap$Nil$.MODULE$;
            }
            return longMap;
        }
        throw new MatchError(this);
    }

    @Override
    public final int size() {
        block5: {
            int n;
            block3: {
                block4: {
                    block2: {
                        if (!((Object)LongMap$Nil$.MODULE$).equals(this)) break block2;
                        n = 0;
                        break block3;
                    }
                    if (!(this instanceof Tip)) break block4;
                    n = 1;
                    break block3;
                }
                if (!(this instanceof Bin)) break block5;
                Bin bin = (Bin)this;
                n = bin.left().size() + bin.right().size();
            }
            return n;
        }
        throw new MatchError(this);
    }

    @Override
    public final Option<T> get(long key) {
        block7: {
            None$ none$;
            block6: {
                block5: {
                    while (this_ instanceof Bin) {
                        LongMap this_;
                        Bin bin = (Bin)this_;
                        if (LongMapUtils$.MODULE$.zero(key, bin.mask())) {
                            this_ = bin.left();
                            continue;
                        }
                        this_ = bin.right();
                    }
                    if (!(this_ instanceof Tip)) break block5;
                    Tip tip = (Tip)this_;
                    none$ = key == tip.key() ? new Some(tip.value()) : None$.MODULE$;
                    break block6;
                }
                if (!((Object)LongMap$Nil$.MODULE$).equals(this_)) break block7;
                none$ = None$.MODULE$;
            }
            return none$;
        }
        throw new MatchError(this_);
    }

    @Override
    public final <S> S getOrElse(long key, Function0<S> function0) {
        while (true) {
            LongMap this_;
            block7: {
                Object object;
                block6: {
                    block5: {
                        if (!((Object)LongMap$Nil$.MODULE$).equals(this_)) break block5;
                        object = function0.apply();
                        break block6;
                    }
                    if (!(this_ instanceof Tip)) break block7;
                    Tip tip = (Tip)this_;
                    object = key == tip.key() ? tip.value() : function0.apply();
                }
                return object;
            }
            if (!(this_ instanceof Bin)) break;
            Bin bin = (Bin)this_;
            if (LongMapUtils$.MODULE$.zero(key, bin.mask())) {
                this_ = bin.left();
                continue;
            }
            this_ = bin.right();
        }
        throw new MatchError(this_);
    }

    @Override
    public final T apply(long key) {
        while (this_ instanceof Bin) {
            LongMap this_;
            Bin bin = (Bin)this_;
            if (LongMapUtils$.MODULE$.zero(key, bin.mask())) {
                this_ = bin.left();
                continue;
            }
            this_ = bin.right();
        }
        if (this_ instanceof Tip) {
            Tip tip = (Tip)this_;
            if (key == tip.key()) {
                return tip.value();
            }
            throw package$.MODULE$.error("Key not found");
        }
        if (((Object)LongMap$Nil$.MODULE$).equals(this_)) {
            throw package$.MODULE$.error("key not found");
        }
        throw new MatchError(this_);
    }

    public <S> LongMap<S> $plus(Tuple2<Object, S> kv) {
        return this.updated(kv._1$mcJ$sp(), kv._2());
    }

    public <S> LongMap<S> updated(long key, S value) {
        block6: {
            LongMap longMap;
            block4: {
                block5: {
                    block3: {
                        if (!(this instanceof Bin)) break block3;
                        Bin bin = (Bin)this;
                        longMap = LongMapUtils$.MODULE$.hasMatch(key, bin.prefix(), bin.mask()) ? (LongMapUtils$.MODULE$.zero(key, bin.mask()) ? new Bin<S>(bin.prefix(), bin.mask(), bin.left().updated(key, value), bin.right()) : new Bin(bin.prefix(), bin.mask(), bin.left(), bin.right().updated(key, value))) : LongMapUtils$.MODULE$.join(key, new Tip<S>(key, value), bin.prefix(), this);
                        break block4;
                    }
                    if (!(this instanceof Tip)) break block5;
                    Tip tip = (Tip)this;
                    longMap = key == tip.key() ? new Tip<S>(key, value) : LongMapUtils$.MODULE$.join(key, new Tip<S>(key, value), tip.key(), this);
                    break block4;
                }
                if (!((Object)LongMap$Nil$.MODULE$).equals(this)) break block6;
                longMap = new Tip<S>(key, value);
            }
            return longMap;
        }
        throw new MatchError(this);
    }

    public <S> LongMap<S> updateWith(long key, S value, Function2<T, S, S> f) {
        block6: {
            LongMap longMap;
            block4: {
                block5: {
                    block3: {
                        if (!(this instanceof Bin)) break block3;
                        Bin bin = (Bin)this;
                        longMap = LongMapUtils$.MODULE$.hasMatch(key, bin.prefix(), bin.mask()) ? (LongMapUtils$.MODULE$.zero(key, bin.mask()) ? new Bin<S>(bin.prefix(), bin.mask(), bin.left().updateWith(key, value, f), bin.right()) : new Bin(bin.prefix(), bin.mask(), bin.left(), bin.right().updateWith(key, value, f))) : LongMapUtils$.MODULE$.join(key, new Tip<S>(key, value), bin.prefix(), this);
                        break block4;
                    }
                    if (!(this instanceof Tip)) break block5;
                    Tip tip = (Tip)this;
                    longMap = key == tip.key() ? new Tip<S>(key, f.apply(tip.value(), value)) : LongMapUtils$.MODULE$.join(key, new Tip<S>(key, value), tip.key(), this);
                    break block4;
                }
                if (!((Object)LongMap$Nil$.MODULE$).equals(this)) break block6;
                longMap = new Tip<S>(key, value);
            }
            return longMap;
        }
        throw new MatchError(this);
    }

    @Override
    public LongMap<T> $minus(long key) {
        block6: {
            LongMap longMap;
            block4: {
                block5: {
                    block3: {
                        if (!(this instanceof Bin)) break block3;
                        Bin bin = (Bin)this;
                        longMap = LongMapUtils$.MODULE$.hasMatch(key, bin.prefix(), bin.mask()) ? (LongMapUtils$.MODULE$.zero(key, bin.mask()) ? LongMapUtils$.MODULE$.bin(bin.prefix(), bin.mask(), bin.left().$minus(key), bin.right()) : LongMapUtils$.MODULE$.bin(bin.prefix(), bin.mask(), bin.left(), bin.right().$minus(key))) : this;
                        break block4;
                    }
                    if (!(this instanceof Tip)) break block5;
                    Tip tip = (Tip)this;
                    longMap = key == tip.key() ? LongMap$Nil$.MODULE$ : this;
                    break block4;
                }
                if (!((Object)LongMap$Nil$.MODULE$).equals(this)) break block6;
                longMap = LongMap$Nil$.MODULE$;
            }
            return longMap;
        }
        throw new MatchError(this);
    }

    public <S> LongMap<S> modifyOrRemove(Function2<Object, T, Option<S>> f) {
        block8: {
            LongMap longMap;
            block3: {
                block4: {
                    Option<S> option;
                    block7: {
                        LongMap longMap2;
                        block6: {
                            Tip tip;
                            block5: {
                                block2: {
                                    if (!(this instanceof Bin)) break block2;
                                    Bin bin = (Bin)((Object)this);
                                    LongMap<S> newleft = bin.left().modifyOrRemove(f);
                                    LongMap<S> newright = bin.right().modifyOrRemove(f);
                                    longMap = bin.left() == newleft && bin.right() == newright ? this : LongMapUtils$.MODULE$.bin(bin.prefix(), bin.mask(), newleft, newright);
                                    break block3;
                                }
                                if (!(this instanceof Tip)) break block4;
                                tip = this;
                                option = f.apply(BoxesRunTime.boxToLong(tip.key()), tip.value());
                                if (!None$.MODULE$.equals(option)) break block5;
                                longMap2 = LongMap$Nil$.MODULE$;
                                break block6;
                            }
                            if (!(option instanceof Some)) break block7;
                            Some some = (Some)option;
                            longMap2 = tip.value() == some.x() ? this : new Tip(tip.key(), some.x());
                        }
                        longMap = longMap2;
                        break block3;
                    }
                    throw new MatchError(option);
                }
                if (!((Object)LongMap$Nil$.MODULE$).equals(this)) break block8;
                longMap = LongMap$Nil$.MODULE$;
            }
            return longMap;
        }
        throw new MatchError(this);
    }

    public <S> LongMap<S> unionWith(LongMap<S> that, Function3<Object, S, S, S> f) {
        Tuple2<LongMap, LongMap<S>> tuple2;
        block8: {
            LongMap longMap;
            block4: {
                block7: {
                    block6: {
                        block5: {
                            block3: {
                                tuple2 = new Tuple2<LongMap, LongMap<S>>(this, that);
                                if (!(tuple2._1() instanceof Bin)) break block3;
                                Bin bin = (Bin)tuple2._1();
                                if (!(tuple2._2() instanceof Bin)) break block3;
                                Bin bin2 = (Bin)tuple2._2();
                                longMap = LongMapUtils$.MODULE$.shorter(bin.mask(), bin2.mask()) ? (LongMapUtils$.MODULE$.hasMatch(bin2.prefix(), bin.prefix(), bin.mask()) ? (LongMapUtils$.MODULE$.zero(bin2.prefix(), bin.mask()) ? new Bin<S>(bin.prefix(), bin.mask(), bin.left().unionWith(bin2, f), bin.right()) : new Bin(bin.prefix(), bin.mask(), bin.left(), bin.right().unionWith(bin2, f))) : LongMapUtils$.MODULE$.join(bin.prefix(), this, bin2.prefix(), bin2)) : (LongMapUtils$.MODULE$.shorter(bin2.mask(), bin.mask()) ? (LongMapUtils$.MODULE$.hasMatch(bin.prefix(), bin2.prefix(), bin2.mask()) ? (LongMapUtils$.MODULE$.zero(bin.prefix(), bin2.mask()) ? new Bin(bin2.prefix(), bin2.mask(), this.unionWith(bin2.left(), f), bin2.right()) : new Bin(bin2.prefix(), bin2.mask(), bin2.left(), this.unionWith(bin2.right(), f))) : LongMapUtils$.MODULE$.join(bin.prefix(), this, bin2.prefix(), bin2)) : (bin.prefix() == bin2.prefix() ? new Bin(bin.prefix(), bin.mask(), bin.left().unionWith(bin2.left(), f), bin.right().unionWith(bin2.right(), f)) : LongMapUtils$.MODULE$.join(bin.prefix(), this, bin2.prefix(), bin2)));
                                break block4;
                            }
                            if (!(tuple2._1() instanceof Tip)) break block5;
                            Tip tip = (Tip)tuple2._1();
                            longMap = tuple2._2().updateWith(tip.key(), (S)tip.value(), (Function2<T, S, S>)new Serializable(this, f, tip){
                                public static final long serialVersionUID = 0L;
                                private final Function3 f$1;
                                private final Tip x6$1;

                                public final S apply(S x, S y) {
                                    return (S)this.f$1.apply(BoxesRunTime.boxToLong(this.x6$1.key()), y, x);
                                }
                                {
                                    void var3_3;
                                    this.f$1 = f$1;
                                    this.x6$1 = var3_3;
                                }
                            });
                            break block4;
                        }
                        if (!(tuple2._2() instanceof Tip)) break block6;
                        Tip tip = (Tip)tuple2._2();
                        longMap = tuple2._1().updateWith(tip.key(), (S)tip.value(), (Function2<T, S, S>)((Object)new Serializable(this, f, tip){
                            public static final long serialVersionUID = 0L;
                            private final Function3 f$1;
                            private final Tip x8$1;

                            public final S apply(T x, S y) {
                                return (S)this.f$1.apply(BoxesRunTime.boxToLong(this.x8$1.key()), x, y);
                            }
                            {
                                void var3_3;
                                this.f$1 = f$1;
                                this.x8$1 = var3_3;
                            }
                        }));
                        break block4;
                    }
                    if (!((Object)LongMap$Nil$.MODULE$).equals(tuple2._1())) break block7;
                    longMap = tuple2._2();
                    break block4;
                }
                if (!((Object)LongMap$Nil$.MODULE$).equals(tuple2._2())) break block8;
                longMap = tuple2._1();
            }
            return longMap;
        }
        throw new MatchError(tuple2);
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public <S, R> LongMap<R> intersectionWith(LongMap<S> that, Function3<Object, T, S, R> f) {
        void var12_17;
        Tuple2<LongMap, LongMap<S>> tuple2 = new Tuple2<LongMap, LongMap<S>>(this, that);
        if (tuple2._1() instanceof Bin) {
            Bin bin = (Bin)tuple2._1();
            if (tuple2._2() instanceof Bin) {
                Bin bin2 = (Bin)tuple2._2();
                return LongMapUtils$.MODULE$.shorter(bin.mask(), bin2.mask()) ? (LongMapUtils$.MODULE$.hasMatch(bin2.prefix(), bin.prefix(), bin.mask()) ? (LongMapUtils$.MODULE$.zero(bin2.prefix(), bin.mask()) ? bin.left().intersectionWith(bin2, f) : bin.right().intersectionWith(bin2, f)) : LongMap$Nil$.MODULE$) : (bin.mask() == bin2.mask() ? LongMapUtils$.MODULE$.bin(bin.prefix(), bin.mask(), bin.left().intersectionWith(bin2.left(), f), bin.right().intersectionWith(bin2.right(), f)) : (LongMapUtils$.MODULE$.hasMatch(bin.prefix(), bin2.prefix(), bin2.mask()) ? (LongMapUtils$.MODULE$.zero(bin.prefix(), bin2.mask()) ? this.intersectionWith(bin2.left(), f) : this.intersectionWith(bin2.right(), f)) : LongMap$Nil$.MODULE$));
            }
        }
        if (tuple2._1() instanceof Tip) {
            void var7_11;
            Tip tip = (Tip)tuple2._1();
            Option<S> option = tuple2._2().get(tip.key());
            if (None$.MODULE$.equals(option)) {
                LongMap$Nil$ longMap$Nil$ = LongMap$Nil$.MODULE$;
                return var7_11;
            } else {
                if (!(option instanceof Some)) throw new MatchError(option);
                Some some = (Some)option;
                Tip<R> tip2 = new Tip<R>(tip.key(), f.apply(BoxesRunTime.boxToLong(tip.key()), tip.value(), some.x()));
            }
            return var7_11;
        }
        if (!(tuple2._2() instanceof Tip)) return LongMap$Nil$.MODULE$;
        Tip tip = (Tip)tuple2._2();
        Option<T> option = this.get(tip.key());
        if (None$.MODULE$.equals(option)) {
            LongMap$Nil$ longMap$Nil$ = LongMap$Nil$.MODULE$;
            return var12_17;
        } else {
            if (!(option instanceof Some)) throw new MatchError(option);
            Some some = (Some)option;
            Tip<R> tip3 = new Tip<R>(tip.key(), f.apply(BoxesRunTime.boxToLong(tip.key()), some.x(), tip.value()));
        }
        return var12_17;
    }

    public <R> LongMap<T> intersection(LongMap<R> that) {
        return this.intersectionWith((LongMap)that, (Function3)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final T apply(long key, T value, R value2) {
                return value;
            }
        }));
    }

    public <S> LongMap<S> $plus$plus(LongMap<S> that) {
        return this.unionWith(that, (Function3<Object, S, S, S>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final S apply(long key, S x, S y) {
                return y;
            }
        }));
    }

    public final long firstKey() {
        while (this_ instanceof Bin) {
            Bin bin = (Bin)this_;
            LongMap this_ = bin.left();
        }
        if (this_ instanceof Tip) {
            Tip tip = (Tip)this_;
            return tip.key();
        }
        if (((Object)LongMap$Nil$.MODULE$).equals(this_)) {
            throw package$.MODULE$.error("Empty set");
        }
        throw new MatchError(this_);
    }

    public final long lastKey() {
        while (this_ instanceof Bin) {
            Bin bin = (Bin)this_;
            LongMap this_ = bin.right();
        }
        if (this_ instanceof Tip) {
            Tip tip = (Tip)this_;
            return tip.key();
        }
        if (((Object)LongMap$Nil$.MODULE$).equals(this_)) {
            throw package$.MODULE$.error("Empty set");
        }
        throw new MatchError(this_);
    }

    public static class Bin<T>
    extends LongMap<T>
    implements Product,
    Serializable {
        private final long prefix;
        private final long mask;
        private final LongMap<T> left;
        private final LongMap<T> right;

        public long prefix() {
            return this.prefix;
        }

        public long mask() {
            return this.mask;
        }

        public LongMap<T> left() {
            return this.left;
        }

        public LongMap<T> right() {
            return this.right;
        }

        public <S> LongMap<S> bin(LongMap<S> left, LongMap<S> right) {
            return this.left() == left && this.right() == right ? this : new Bin<S>(this.prefix(), this.mask(), left, right);
        }

        public <T> Bin<T> copy(long prefix, long mask, LongMap<T> left, LongMap<T> right) {
            return new Bin<T>(prefix, mask, left, right);
        }

        public <T> long copy$default$1() {
            return this.prefix();
        }

        public <T> long copy$default$2() {
            return this.mask();
        }

        public <T> LongMap<T> copy$default$3() {
            return this.left();
        }

        public <T> LongMap<T> copy$default$4() {
            return this.right();
        }

        @Override
        public String productPrefix() {
            return "Bin";
        }

        @Override
        public int productArity() {
            return 4;
        }

        @Override
        public Object productElement(int x$1) {
            Object object;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 3: {
                    object = this.right();
                    break;
                }
                case 2: {
                    object = this.left();
                    break;
                }
                case 1: {
                    object = BoxesRunTime.boxToLong(this.mask());
                    break;
                }
                case 0: {
                    object = BoxesRunTime.boxToLong(this.prefix());
                }
            }
            return object;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        public Bin(long prefix, long mask, LongMap<T> left, LongMap<T> right) {
            this.prefix = prefix;
            this.mask = mask;
            this.left = left;
            this.right = right;
            Product$class.$init$(this);
        }
    }

    public static class Tip<T>
    extends LongMap<T>
    implements Product,
    Serializable {
        private final long key;
        private final T value;

        public long key() {
            return this.key;
        }

        public T value() {
            return this.value;
        }

        public <S> Tip<S> withValue(S s2) {
            return s2 == this.value() ? this : new Tip<S>(this.key(), s2);
        }

        public <T> Tip<T> copy(long key, T value) {
            return new Tip<T>(key, value);
        }

        public <T> long copy$default$1() {
            return this.key();
        }

        public <T> T copy$default$2() {
            return this.value();
        }

        @Override
        public String productPrefix() {
            return "Tip";
        }

        @Override
        public int productArity() {
            return 2;
        }

        @Override
        public Object productElement(int x$1) {
            Object object;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 1: {
                    object = this.value();
                    break;
                }
                case 0: {
                    object = BoxesRunTime.boxToLong(this.key());
                }
            }
            return object;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        public Tip(long key, T value) {
            this.key = key;
            this.value = value;
            Product$class.$init$(this);
        }
    }
}

