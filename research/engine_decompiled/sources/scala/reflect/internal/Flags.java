/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Array$;
import scala.Function1;
import scala.Function1$class;
import scala.Function1$mcJJ$sp;
import scala.Function1$mcJJ$sp$class;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple2$mcJJ$sp;
import scala.collection.AbstractTraversable;
import scala.collection.GenTraversableOnce;
import scala.collection.TraversableOnce;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.IndexedSeq$;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Range$;
import scala.collection.mutable.ArrayOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.ModifierFlags;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001\u0011\u0015h\u0001B\u0001\u0003\u0001%\u0011QA\u00127bONT!a\u0001\u0003\u0002\u0011%tG/\u001a:oC2T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\r\u001b\u0005\u0011\u0011BA\u0007\u0003\u00055iu\u000eZ5gS\u0016\u0014h\t\\1hg\")q\u0002\u0001C\u0001!\u00051A(\u001b8jiz\"\u0012!\u0005\t\u0003\u0017\u0001Aqa\u0005\u0001C\u0002\u0013\u0015A#\u0001\u0004N\u000bRCu\nR\u000b\u0002+=\ta#H\u0001A\u0011\u0019A\u0002\u0001)A\u0007+\u00059Q*\u0012+I\u001f\u0012\u0003\u0003b\u0002\u000e\u0001\u0005\u0004%)aG\u0001\u0007\u001b>#U\u000bT#\u0016\u0003qy\u0011!H\u000f\u0003\u0003\u0001Aaa\b\u0001!\u0002\u001ba\u0012aB'P\tVcU\t\t\u0005\bC\u0001\u0011\r\u0011\"\u0002#\u0003\u001d\u0001\u0016iQ&B\u000f\u0016+\u0012aI\b\u0002Iu\u0011\u0001\t\u0001\u0005\u0007M\u0001\u0001\u000bQB\u0012\u0002\u0011A\u000b5iS!H\u000b\u0002Bq\u0001\u000b\u0001C\u0002\u0013\u0015\u0011&\u0001\u0005D\u0003B#VKU#E+\u0005Qs\"A\u0016\u001e\u0007\u0005\u0001\u0001\u0001\u0003\u0004.\u0001\u0001\u0006iAK\u0001\n\u0007\u0006\u0003F+\u0016*F\t\u0002Bqa\f\u0001C\u0002\u0013\u0015\u0001'A\u0003M\u0003\n+E*F\u00012\u001f\u0005\u0011Td\u0001\u0002\u0001\u0001!1A\u0007\u0001Q\u0001\u000eE\na\u0001T!C\u000b2\u0003\u0003b\u0002\u001c\u0001\u0005\u0004%)\u0001M\u0001\u000e\u0013:\u001buJT*U%V\u001bEk\u0014*\t\ra\u0002\u0001\u0015!\u00042\u00039IejQ(O'R\u0013Vk\u0011+P%\u0002BqA\u000f\u0001C\u0002\u0013\u00151(A\u0005T3:#\u0006*\u0012+J\u0007V\tAhD\u0001>;\r\u0001\u0003\u0001\u0001\u0005\u0007\u007f\u0001\u0001\u000bQ\u0002\u001f\u0002\u0015MKf\n\u0016%F)&\u001b\u0005\u0005C\u0004B\u0001\t\u0007IQ\u0001\"\u0002\rM#\u0016I\u0011'F+\u0005\u0019u\"\u0001#\u001e\u0007\u0001\u0003\u0001\u0001\u0003\u0004G\u0001\u0001\u0006iaQ\u0001\b'R\u000b%\tT#!\u0011\u001dA\u0005A1A\u0005\u0006%\u000baA\u0011*J\t\u001e+U#\u0001&\u0010\u0003-kB\u0001\u0002\u0001\u0001\u0001!1Q\n\u0001Q\u0001\u000e)\u000bqA\u0011*J\t\u001e+\u0005\u0005C\u0004P\u0001\t\u0007IQ\u0001)\u0002\u0011\u0005\u001b5)R*T\u001fJ+\u0012!U\b\u0002%v!\u0001\u0002\u0001\u0001\u0001\u0011\u0019!\u0006\u0001)A\u0007#\u0006I\u0011iQ\"F'N{%\u000b\t\u0005\b-\u0002\u0011\r\u0011\"\u0002X\u00035\u0019V\u000bU#S\u0003\u000e\u001bUiU*P%V\t\u0001lD\u0001Z;\u0011\u0001\u0002\u0001\u0001\u0001\t\rm\u0003\u0001\u0015!\u0004Y\u00039\u0019V\u000bU#S\u0003\u000e\u001bUiU*P%\u0002Bq!\u0018\u0001C\u0002\u0013\u0015a,A\u0005N\u001f\u0012+F*\u0012,B%V\tqlD\u0001a;\u0011\u0001\u0005\u0001\u0001\u0001\t\r\t\u0004\u0001\u0015!\u0004`\u0003)iu\nR+M\u000bZ\u000b%\u000b\t\u0005\bI\u0002\u0011\r\u0011\"\u0002f\u0003!I5kX#S%>\u0013V#\u00014\u0010\u0003\u001dtR!\u0001\u0001\u0001\u0001\u0001Aa!\u001b\u0001!\u0002\u001b1\u0017!C%T?\u0016\u0013&k\u0014*!\u0011\u001dY\u0007A1A\u0005\u00061\f!b\u0014,F%2{\u0015\tR#E+\u0005iw\"\u00018\u001f\u000b\t\u0001\u0001\u0001\u0001\u0001\t\rA\u0004\u0001\u0015!\u0004n\u0003-ye+\u0012*M\u001f\u0006#U\t\u0012\u0011\t\u000fI\u0004!\u0019!C\u0003g\u00061A*\u0013$U\u000b\u0012+\u0012\u0001^\b\u0002kz)A\u0001\u0001\u0001\u0001\u0001!1q\u000f\u0001Q\u0001\u000eQ\fq\u0001T%G)\u0016#\u0005\u0005C\u0004z\u0001\t\u0007IQ\u0001>\u0002\u000f5K\u0005,\u0012#J\u001dV\t1pD\u0001}=\u0015A\u0001\u0001\u0001\u0001\u0001\u0011\u0019q\b\u0001)A\u0007w\u0006AQ*\u0013-F\t&s\u0005\u0005\u0003\u0005\u0002\u0002\u0001\u0011\r\u0011\"\u0002{\u0003-)\u0005,S*U\u000b:#\u0016*\u0011'\t\u000f\u0005\u0015\u0001\u0001)A\u0007w\u0006aQ\tW%T)\u0016sE+S!MA!I\u0011\u0011\u0002\u0001C\u0002\u0013\u0015\u00111B\u0001\r\u000bb\u0003\u0016I\u0014#F\t:\u000bU*R\u000b\u0003\u0003\u001by!!a\u0004\u001f\u000bA\u0001\u0001\u0001\u0001\u0001\t\u0011\u0005M\u0001\u0001)A\u0007\u0003\u001b\tQ\"\u0012-Q\u0003:#U\t\u0012(B\u001b\u0016\u0003\u0003\"CA\f\u0001\t\u0007IQAA\r\u0003%IU\n\u0015'D\u0019\u0006\u001b6+\u0006\u0002\u0002\u001c=\u0011\u0011Q\u0004\u0010\u0006A\u0001\u0001\u0001\u0001\u0001\u0005\t\u0003C\u0001\u0001\u0015!\u0004\u0002\u001c\u0005Q\u0011*\u0014)M\u00072\u000b5k\u0015\u0011\t\u0013\u0005\u0015\u0002A1A\u0005\u0006\u0005\u001d\u0012A\u0003+S\u0003:\u001bvL\u0012'B\u000fV\u0011\u0011\u0011F\b\u0003\u0003WqR\u0001\u0011\u0001\u0001\u0001\u0001A\u0001\"a\f\u0001A\u00035\u0011\u0011F\u0001\f)J\u000bejU0G\u0019\u0006;\u0005\u0005C\u0005\u00024\u0001\u0011\r\u0011\"\u0002\u00026\u00051AjT\"L\u000b\u0012+\"!a\u000e\u0010\u0005\u0005ebD\u0002\u0001\u0001\u0002\u0001\u0001\u0001\u0001\u0003\u0005\u0002>\u0001\u0001\u000bQBA\u001c\u0003\u001daujQ&F\t\u0002B\u0011\"!\u0011\u0001\u0005\u0004%)!a\u0011\u0002\u0017M\u0003ViQ%B\u0019&SV\tR\u000b\u0003\u0003\u000bz!!a\u0012\u001f\r\u0005\u0001\u0001\u0001\u0001\u0001\u0001\u0011!\tY\u0005\u0001Q\u0001\u000e\u0005\u0015\u0013\u0001D*Q\u000b\u000eK\u0015\tT%[\u000b\u0012\u0003\u0003\"CA(\u0001\t\u0007IQAA)\u0003\u001d1&IU%E\u000f\u0016+\"!a\u0015\u0010\u0005\u0005UcD\u0002\u0003\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0005\u0002Z\u0001\u0001\u000bQBA*\u0003!1&IU%E\u000f\u0016\u0003\u0003\"CA/\u0001\t\u0007IQAA0\u0003\u001d1\u0016IU!S\u000fN+\"!!\u0019\u0010\u0005\u0005\rdD\u0002\u0005\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0005\u0002h\u0001\u0001\u000bQBA1\u0003!1\u0016IU!S\u000fN\u0003\u0003\"CA6\u0001\t\u0007IQAA7\u00031!&+S#E\u0007>{5*\u0013(H+\t\tyg\u0004\u0002\u0002ry1\u0001\u0003\u0001\u0001\u0001\u0001\u0001A\u0001\"!\u001e\u0001A\u00035\u0011qN\u0001\u000e)JKU\tR\"P\u001f.Kej\u0012\u0011\t\u0013\u0005e\u0004A1A\u0005\u0006\u0005m\u0014\u0001D*Z\u001d\u000eC%k\u0014(J5\u0016#UCAA?\u001f\t\tyH\b\u0004!\u0001\u0001\u0001\u0001\u0001\u0001\u0005\t\u0003\u0007\u0003\u0001\u0015!\u0004\u0002~\u0005i1+\u0017(D\u0011J{e*\u0013.F\t\u0002B\u0011\"a\"\u0001\u0005\u0004%)!!#\u0002\u0019%s\u0017\u000e^5bY\u001ac\u0017mZ:\u0016\u0005\u0005-uBAAG=\u001d9q\u0000\u0000\u0000\u0000\u0000\u0000H\u0001\"!%\u0001A\u00035\u00111R\u0001\u000e\u0013:LG/[1m\r2\fwm\u001d\u0011\t\u0013\u0005U\u0005A1A\u0005\u0006\u0005]\u0015!\u0003'bi\u00164E.Y4t+\t\tIj\u0004\u0002\u0002\u001czA\u0001\u0001?\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0005\u0002 \u0002\u0001\u000bQBAM\u0003)a\u0015\r^3GY\u0006<7\u000f\t\u0005\n\u0003G\u0003!\u0019!C\u0003\u0003K\u000b\u0011\"\u00118uS\u001ac\u0017mZ:\u0016\u0005\u0005\u001dvBAAU=!9\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002CAW\u0001\u0001\u0006i!a*\u0002\u0015\u0005sG/\u001b$mC\u001e\u001c\b\u0005C\u0005\u00022\u0002\u0011\r\u0011\"\u0002\u00024\u0006IA*\u0019;f'\"Lg\r^\u000b\u0003\u0003k{!!a.\u001e\u0003=B\u0001\"a/\u0001A\u00035\u0011QW\u0001\u000b\u0019\u0006$Xm\u00155jMR\u0004\u0003\"CA`\u0001\t\u0007IQAAa\u0003%\te\u000e^5TQ&4G/\u0006\u0002\u0002D>\u0011\u0011QY\u000f\u0002q!A\u0011\u0011\u001a\u0001!\u0002\u001b\t\u0019-\u0001\u0006B]RL7\u000b[5gi\u0002B\u0011\"!4\u0001\u0005\u0004%\t!a4\u0002'=3XM\u001d7pC\u0012,GM\u00127bONl\u0015m]6\u0016\u0005\u0005E\u0007\u0003BAj\u0003+l\u0011AB\u0005\u0004\u0003/4!\u0001\u0002'p]\u001eD\u0001\"a7\u0001A\u0003%\u0011\u0011[\u0001\u0015\u001fZ,'\u000f\\8bI\u0016$g\t\\1hg6\u000b7o\u001b\u0011\t\u0013\u0005}\u0007A1A\u0005\u0006\u0005=\u0017\u0001\u00047bi\u0016$UIR#S%\u0016#\u0005\u0002CAr\u0001\u0001\u0006i!!5\u0002\u001b1\fG/\u001a#F\r\u0016\u0013&+\u0012#!\u0011%\t9\u000f\u0001b\u0001\n\u000b\ty-A\u0005mCR,g)\u0013(B\u0019\"A\u00111\u001e\u0001!\u0002\u001b\t\t.\u0001\u0006mCR,g)\u0013(B\u0019\u0002B\u0011\"a<\u0001\u0005\u0004%)!a4\u0002\u001b1\fG/Z%O)\u0016\u0013f)Q\"F\u0011!\t\u0019\u0010\u0001Q\u0001\u000e\u0005E\u0017A\u00047bi\u0016Le\nV#S\r\u0006\u001bU\t\t\u0005\n\u0003o\u0004!\u0019!C\u0003\u0003\u001f\f!\u0002\\1uK6+E\u000bS(E\u0011!\tY\u0010\u0001Q\u0001\u000e\u0005E\u0017a\u00037bi\u0016lU\t\u0016%P\t\u0002B\u0011\"a@\u0001\u0005\u0004%)!a4\u0002\u00151\fG/Z'P\tVcU\t\u0003\u0005\u0003\u0004\u0001\u0001\u000bQBAi\u0003-a\u0017\r^3N\u001f\u0012+F*\u0012\u0011\t\u0013\t\u001d\u0001A1A\u0005\u0006\u0005=\u0017a\u00038pi>3VI\u0015*J\t\u0016C\u0001Ba\u0003\u0001A\u00035\u0011\u0011[\u0001\r]>$xJV#S%&#U\t\t\u0005\n\u0005\u001f\u0001!\u0019!C\u0003\u0003\u001f\f!B\\8u!JKe+\u0011+F\u0011!\u0011\u0019\u0002\u0001Q\u0001\u000e\u0005E\u0017a\u00038piB\u0013\u0016JV!U\u000b\u0002B\u0011Ba\u0006\u0001\u0005\u0004%)!a4\u0002\u00199|G\u000f\u0015*P)\u0016\u001bE+\u0012#\t\u0011\tm\u0001\u0001)A\u0007\u0003#\fQB\\8u!J{E+R\"U\u000b\u0012\u0003\u0003\"\u0003B\u0010\u0001\t\u0007IQ\u0001B\u0011\u0003!\tE\u000e\u001c$mC\u001e\u001cXC\u0001B\u0012\u001f\t\u0011)CH\u0001\u0000\u0012!\u0011I\u0003\u0001Q\u0001\u000e\t\r\u0012!C!mY\u001ac\u0017mZ:!\u0011%\u0011i\u0003\u0001b\u0001\n\u000b\u0011y#A\u000bU_BdUM^3m\u0007J,\u0017\r^5p]\u001ac\u0017mZ:\u0016\u0005\tErB\u0001B\u001a;\r\u0001\u0012\t\t\u0005\t\u0005o\u0001\u0001\u0015!\u0004\u00032\u00051Bk\u001c9MKZ,Gn\u0011:fCRLwN\u001c$mC\u001e\u001c\b\u0005C\u0005\u0003<\u0001\u0011\r\u0011\"\u0002\u00030\u0005a\u0001+Y2lC\u001e,g\t\\1hg\"A!q\b\u0001!\u0002\u001b\u0011\t$A\u0007QC\u000e\\\u0017mZ3GY\u0006<7\u000f\t\u0005\t\u0005\u0007\u0002!\u0019!C\u00037\u0005YQj\u001c3vY\u00164E.Y4t\u0011\u001d\u00119\u0005\u0001Q\u0001\u000eq\tA\"T8ek2,g\t\\1hg\u0002B\u0011Ba\u0013\u0001\u0005\u0004%)A!\u0014\u0002\u001b\u0015C\b\u000f\\5dSR4E.Y4t+\t\u0011ye\u0004\u0002\u0003Ry9\u0001\u0001!\u0001\u0001\n9y\u0003\u0002\u0003B+\u0001\u0001\u0006iAa\u0014\u0002\u001d\u0015C\b\u000f\\5dSR4E.Y4tA!I!\u0011\f\u0001C\u0002\u0013\u0015!1L\u0001\f\u0005JLGmZ3GY\u0006<7/\u0006\u0002\u0003^=\u0011!q\f\u0010\u0007\t\u0001!\u0001\u0001\u0001\u0001\t\u0011\t\r\u0004\u0001)A\u0007\u0005;\nAB\u0011:jI\u001e,g\t\\1hg\u0002B\u0011Ba\u001a\u0001\u0005\u0004%)A!\u001b\u0002+\t\u0013\u0018\u000eZ4f\u0003:$\u0007K]5wCR,g\t\\1hgV\u0011!1N\b\u0003\u0005[rb\u0001\u0002\u0001\u0005\u0001\u0001!\u0001\u0002\u0003B9\u0001\u0001\u0006iAa\u001b\u0002-\t\u0013\u0018\u000eZ4f\u0003:$\u0007K]5wCR,g\t\\1hg\u0002B\u0011B!\u001e\u0001\u0005\u0004%)Aa\u001e\u0002\u001dA\u0013\u0018N\u001c;bE2,g\t\\1hgV\u0011!\u0011P\b\u0003\u0005wrr\u0001As\u0001{3xy\u0006\u0003\u0005\u0003\u0000\u0001\u0001\u000bQ\u0002B=\u0003=\u0001&/\u001b8uC\ndWM\u00127bON\u0004\u0003\"\u0003BB\u0001\t\u0007IQ\u0001BC\u0003)1\u0015.\u001a7e\r2\fwm]\u000b\u0003\u0005\u000f{!A!#\u001f\u000b\u0001\n\u000b\u0011\u0005\u0011\t\u0011\t5\u0005\u0001)A\u0007\u0005\u000f\u000b1BR5fY\u00124E.Y4tA!I!\u0011\u0013\u0001C\u0002\u0013\u0015\u0011qZ\u0001\f\u000f\u0016$H/\u001a:GY\u0006<7\u000f\u0003\u0005\u0003\u0016\u0002\u0001\u000bQBAi\u000319U\r\u001e;fe\u001ac\u0017mZ:!\u0011%\u0011I\n\u0001b\u0001\n\u000b\ty-A\u0006TKR$XM\u001d$mC\u001e\u001c\b\u0002\u0003BO\u0001\u0001\u0006i!!5\u0002\u0019M+G\u000f^3s\r2\fwm\u001d\u0011\t\u0013\t\u0005\u0006A1A\u0005\u0006\t\r\u0016A\u0005#fM\u0006,H\u000e^$fiR,'O\u00127bON,\"A!*\u0010\u0005\t\u001dV\u0004\u0002\u0011\u0001\u0001\u0015B\u0001Ba+\u0001A\u00035!QU\u0001\u0014\t\u00164\u0017-\u001e7u\u000f\u0016$H/\u001a:GY\u0006<7\u000f\t\u0005\n\u0005_\u0003!\u0019!C\u0003\u0005c\u000b1CV1mk\u0016\u0004\u0016M]1nKR,'O\u00127bON,\"Aa-\u0010\u0005\tUV\u0004\u0002\u0002b\u0005\u0001A\u0001B!/\u0001A\u00035!1W\u0001\u0015-\u0006dW/\u001a)be\u0006lW\r^3s\r2\fwm\u001d\u0011\t\u0013\tu\u0006A1A\u0005\u0006\t}\u0016!\u0005\"fC:\u0004&o\u001c9feRLh\t\\1hgV\u0011!\u0011Y\b\u0003\u0005\u0007lB\u0001\u0001A\u0001%!A!q\u0019\u0001!\u0002\u001b\u0011\t-\u0001\nCK\u0006t\u0007K]8qKJ$\u0018P\u00127bON\u0004\u0003\"\u0003Bf\u0001\t\u0007IQ\u0001Bg\u000351\u0016M]5b]\u000e,g\t\\1hgV\u0011!qZ\b\u0003\u0005#l2a\u0001\u0001\u0001\u0011!\u0011)\u000e\u0001Q\u0001\u000e\t=\u0017A\u0004,be&\fgnY3GY\u0006<7\u000f\t\u0005\n\u00053\u0004!\u0019!C\u0003\u00057\f1bQ8ogR\u0014h\t\\1hgV\u0011!Q\\\b\u0003\u0005?l2\u0001\u0005\u0001\u0001\u0011!\u0011\u0019\u000f\u0001Q\u0001\u000e\tu\u0017\u0001D\"p]N$(O\u00127bON\u0004\u0003\"\u0003Bt\u0001\t\u0007IQ\u0001Bu\u0003Iiu\u000eZ;mKR{7\t\\1tg\u001ac\u0017mZ:\u0016\u0005\t-xB\u0001Bw;\rA\u0014*\n\u0005\t\u0005c\u0004\u0001\u0015!\u0004\u0003l\u0006\u0019Rj\u001c3vY\u0016$vn\u00117bgN4E.Y4tA!I!Q\u001f\u0001C\u0002\u0013\u0015!q_\u0001\u0010\r2\fwm\u001d(piBK7m\u001b7fIV\u0011!\u0011`\b\u0003\u0005wtb\u0001Ed\u0001\u0001\u0001\u0001\u0001\u0002\u0003B\u0000\u0001\u0001\u0006iA!?\u0002!\u0019c\u0017mZ:O_R\u0004\u0016nY6mK\u0012\u0004\u0003\"CB\u0002\u0001\t\u0007IQAAh\u00031\u0001\u0016nY6mK\u00124E.Y4t\u0011!\u00199\u0001\u0001Q\u0001\u000e\u0005E\u0017!\u0004)jG.dW\r\u001a$mC\u001e\u001c\b\u0005C\u0005\u0004\f\u0001\u0011\r\u0011\"\u0002\u0002P\u0006!Bk\u001c9MKZ,G\u000eU5dW2,GM\u00127bOND\u0001ba\u0004\u0001A\u00035\u0011\u0011[\u0001\u0016)>\u0004H*\u001a<fYBK7m\u001b7fI\u001ac\u0017mZ:!\u0011\u001d\u0019\u0019\u0002\u0001C\u0001\u0007+\t\u0011\u0004]1sC64E.Y4t)>$UMZ1vYR<U\r\u001e;feR!\u0011\u0011[B\f\u0011!\u0019Ib!\u0005A\u0002\u0005E\u0017A\u00039be\u0006lg\t\\1hg\"91Q\u0004\u0001\u0005\u0002\r}\u0011aC4fiR,'O\u00127bON$B!!5\u0004\"!A11EB\u000e\u0001\u0004\t\t.\u0001\u0006gS\u0016dGM\u00127bONDqaa\n\u0001\t\u0003\u0019I#A\u0006tKR$XM\u001d$mC\u001e\u001cH\u0003BAi\u0007WA\u0001ba\t\u0004&\u0001\u0007\u0011\u0011\u001b\u0005\n\u0007_\u0001!\u0019!C\u0007\u0007c\tA\"S'Q\u0019&\u001b\u0015\nV0Q\u00172+\"aa\r\u0010\u0005\rUR$A\u0001\t\u0011\re\u0002\u0001)A\u0007\u0007g\tQ\"S'Q\u0019&\u001b\u0015\nV0Q\u00172\u0003\u0003\"CB\u001f\u0001\t\u0007IQBB \u0003%1\u0015JT!M?B[E*\u0006\u0002\u0004B=\u001111I\u000f\u0002\u0005!A1q\t\u0001!\u0002\u001b\u0019\t%\u0001\u0006G\u0013:\u000bEj\u0018)L\u0019\u0002B\u0011ba\u0013\u0001\u0005\u0004%ia!\u0014\u0002\u0017A\u0013\u0016JV!U\u000b~\u00036\nT\u000b\u0003\u0007\u001fz!a!\u0015\u001e\u0003\u0011A\u0001b!\u0016\u0001A\u000351qJ\u0001\r!JKe+\u0011+F?B[E\n\t\u0005\n\u00073\u0002!\u0019!C\u0007\u00077\nQ\u0002\u0015*P)\u0016\u001bE+\u0012#`!.cUCAB/\u001f\t\u0019y&H\u0001\t\u0011!\u0019\u0019\u0007\u0001Q\u0001\u000e\ru\u0013A\u0004)S\u001fR+5\tV#E?B[E\n\t\u0005\n\u0007O\u0002!\u0019!C\u0007\u0007S\n!bU#B\u0019\u0016#u\fU&M+\t\u0019Yg\u0004\u0002\u0004nu\t\u0001\u0003\u0003\u0005\u0004r\u0001\u0001\u000bQBB6\u0003-\u0019V)\u0011'F\t~\u00036\n\u0014\u0011\t\u0013\rU\u0004A1A\u0005\u000e\r]\u0014\u0001D(W\u000bJ\u0013\u0016\nR#`!.cUCAB=\u001f\t\u0019Y(H\u0001!\u0011!\u0019y\b\u0001Q\u0001\u000e\re\u0014!D(W\u000bJ\u0013\u0016\nR#`!.c\u0005\u0005\u0003\u0005\u0004\u0004\u0002\u0011\r\u0011\"\u0004\u0015\u0003!\u0019\u0015iU#`!.c\u0005bBBD\u0001\u0001\u0006i!F\u0001\n\u0007\u0006\u001bVi\u0018)L\u0019\u0002B\u0011ba#\u0001\u0005\u0004%ia!$\u0002\u0019\u0005\u00135\u000b\u0016*B\u0007R{\u0006k\u0013'\u0016\u0005\r=uBABI;\t\u0001\u0001\u0011\u0003\u0005\u0004\u0016\u0002\u0001\u000bQBBH\u00035\t%i\u0015+S\u0003\u000e#v\fU&MA!A1\u0011\u0014\u0001C\u0002\u001351$\u0001\u0007E\u000b\u001a+%KU#E?B[E\nC\u0004\u0004\u001e\u0002\u0001\u000bQ\u0002\u000f\u0002\u001b\u0011+e)\u0012*S\u000b\u0012{\u0006k\u0013'!\u0011%\u0019\t\u000b\u0001b\u0001\n\u001b\u0019\u0019+\u0001\u0006N\u000bRCu\nR0Q\u00172+\"a!*\u0010\u0005\r\u001dVD\u0001\u0002\u0001\u0011!\u0019Y\u000b\u0001Q\u0001\u000e\r\u0015\u0016aC'F)\"{Ei\u0018)L\u0019\u0002B\u0011ba,\u0001\u0005\u0004%ia!-\u0002\u00155{E)\u0016'F?B[E*\u0006\u0002\u00044>\u00111QW\u000f\u0003\t\u0001A\u0001b!/\u0001A\u0003511W\u0001\f\u001b>#U\u000bT#`!.c\u0005\u0005C\u0005\u0004>\u0002\u0011\r\u0011\"\u0004\u0004@\u0006i\u0011J\u0014+F%\u001a\u000b5)R0Q\u00172+\"a!1\u0010\u0005\r\rWD\u0001\u0005\u0001\u0011!\u00199\r\u0001Q\u0001\u000e\r\u0005\u0017AD%O)\u0016\u0013f)Q\"F?B[E\n\t\u0005\n\u0007\u0017\u0004!\u0019!C\u0007\u0007\u001b\f\u0001\u0002U&M?6\u000b5kS\u000b\u0003\u0007\u001f|!a!5\u001e\u0005=y\u0010\u0002CBk\u0001\u0001\u0006iaa4\u0002\u0013A[EjX'B'.\u0003\u0003bBBm\u0001\u0011%11\\\u0001\u0019e\u0006<\b+[2lY\u0016$7i\u001c:sKN\u0004xN\u001c3f]\u000e,WCABo!\u0019\t\u0019na8\u0004d&\u00191\u0011\u001d\u0004\u0003\u000b\u0005\u0013(/Y=\u0011\u0011\u0005M7Q]Ai\u0003#L1aa:\u0007\u0005\u0019!V\u000f\u001d7fe!I11\u001e\u0001C\u0002\u0013%1Q^\u0001\u000f[\u0006\u0004\b/\u001a3SC^4E.Y4t+\t\u0019y\u000f\u0005\u0004\u0002T\u000e}\u0017\u0011\u001b\u0005\t\u0007g\u0004\u0001\u0015!\u0003\u0004p\u0006yQ.\u00199qK\u0012\u0014\u0016m\u001e$mC\u001e\u001c\b\u0005C\u0005\u0004x\u0002\u0011\r\u0011\"\u0003\u0004n\u0006\u0011R.\u00199qK\u0012\u0004\u0016nY6mK\u00124E.Y4t\u0011!\u0019Y\u0010\u0001Q\u0001\n\r=\u0018aE7baB,G\rU5dW2,GM\u00127bON\u0004cABB\u0000\u0001\u0011!\tA\u0001\u0005NCB4E.Y4t'\u0019\u0019i\u0010b\u0001\u0005\nA!\u00111\u001bC\u0003\u0013\r!9A\u0002\u0002\u0007\u0003:L(+\u001a4\u0011\u0011\u0005MG1BAi\u0003#L1\u0001\"\u0004\u0007\u0005%1UO\\2uS>t\u0017\u0007C\u0006\u0005\u0012\ru(\u0011!Q\u0001\n\r=\u0018\u0001\u00024s_6D1\u0002\"\u0006\u0004~\n\u0005\t\u0015!\u0003\u0004p\u0006\u0011Ao\u001c\u0005\b\u001f\ruH\u0011\u0001C\r)\u0019!Y\u0002b\b\u0005\"A!AQDB\u007f\u001b\u0005\u0001\u0001\u0002\u0003C\t\t/\u0001\raa<\t\u0011\u0011UAq\u0003a\u0001\u0007_D!\u0002\"\n\u0004~\n\u0007I\u0011AAh\u0003\u001d1'o\\7TKRD\u0011\u0002\"\u000b\u0004~\u0002\u0006I!!5\u0002\u0011\u0019\u0014x.\\*fi\u0002B\u0001\u0002\"\f\u0004~\u0012\u0005AqF\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u0003#$\t\u0004\u0003\u0005\u00054\u0011-\u0002\u0019AAi\u0003\u00151G.Y4t\u0011%!9\u0004\u0001b\u0001\n\u0003!I$A\tsC^$v\u000eU5dW2,GM\u00127bON,\"\u0001\"\u0003\t\u0011\u0011u\u0002\u0001)A\u0005\t\u0013\t!C]1x)>\u0004\u0016nY6mK\u00124E.Y4tA!IA\u0011\t\u0001C\u0002\u0013\u0005A\u0011H\u0001\u0012a&\u001c7\u000e\\3e)>\u0014\u0016m\u001e$mC\u001e\u001c\b\u0002\u0003C#\u0001\u0001\u0006I\u0001\"\u0003\u0002%AL7m\u001b7fIR{'+Y<GY\u0006<7\u000f\t\u0005\b\t\u0013\u0002A\u0011\tC&\u000311G.Y4U_N#(/\u001b8h)\u0011!i\u0005b\u0017\u0011\t\u0011=CQ\u000b\b\u0005\u0003'$\t&C\u0002\u0005T\u0019\ta\u0001\u0015:fI\u00164\u0017\u0002\u0002C,\t3\u0012aa\u0015;sS:<'b\u0001C*\r!AAQ\fC$\u0001\u0004\t\t.\u0001\u0003gY\u0006<\u0007\u0006\u0002C$\tC\u0002B\u0001b\u0019\u0005j5\u0011AQ\r\u0006\u0004\tO2\u0011AC1o]>$\u0018\r^5p]&!A1\u000eC3\u0005\u0019\u0019x/\u001b;dQ\"9Aq\u000e\u0001\u0005\n\u0011E\u0014\u0001D1dG\u0016\u001c8o\u0015;sS:<GC\u0002C:\t\u0003#\u0019\t\u0005\u0003\u0005v\u0011}TB\u0001C<\u0015\u0011!I\bb\u001f\u0002\t1\fgn\u001a\u0006\u0003\t{\nAA[1wC&!Aq\u000bC<\u0011!!\u0019\u0004\"\u001cA\u0002\u0005E\u0007\u0002\u0003CC\t[\u0002\r\u0001\"\u0014\u0002\u001bA\u0014\u0018N^1uK^KG\u000f[5o\u0011!!I\t\u0001C\u0001\r\u0011-\u0015!\u00044mC\u001e\u001cHk\\*ue&tw\r\u0006\u0004\u0005N\u00115Eq\u0012\u0005\t\tg!9\t1\u0001\u0002R\"AAQ\u0011CD\u0001\u0004!i\u0005\u000b\u0005\u0005\b\u0012ME\u0011\u0014CO!\u0011\t\u0019\u000e\"&\n\u0007\u0011]eA\u0001\u0006eKB\u0014XmY1uK\u0012\f#\u0001b'\u0002UU\u001bX\r\t4mC\u001e\u001cFO]5oO\u0002zg\u000e\t;iK\u00022G.Y4.G\u0006\u0014(/_5oO\u0002jW-\u001c2fe\u0006\u0012AqT\u0001\u0007e9\n\u0004G\f\u0019\t\u0011\u0011%\u0005\u0001\"\u0001\u0007\tG#B\u0001\"\u0014\u0005&\"AA1\u0007CQ\u0001\u0004\t\t\u000e\u000b\u0005\u0005\"\u0012ME\u0011\u0014CO\u0011%!Y\u000b\u0001b\u0001\n\u000b!i+\u0001\bNCb\u0014\u0015\u000e\u001e)pg&$\u0018n\u001c8\u0016\u0005\u0011=vB\u0001CY;\u0005q\u0004\u0002\u0003C[\u0001\u0001\u0006i\u0001b,\u0002\u001f5\u000b\u0007PQ5u!>\u001c\u0018\u000e^5p]\u0002B\u0011\u0002\"/\u0001\u0005\u0004%)\u0001b/\u0002!AL7m\u001b7fI2K7\u000f^(sI\u0016\u0014XC\u0001C_!\u0019!y\f\"2\u0002R:!\u00111\u001bCa\u0013\r!\u0019MB\u0001\ba\u0006\u001c7.Y4f\u0013\u0011!9\r\"3\u0003\t1K7\u000f\u001e\u0006\u0004\t\u00074\u0001\u0002\u0003Cg\u0001\u0001\u0006i\u0001\"0\u0002#AL7m\u001b7fI2K7\u000f^(sI\u0016\u0014\b\u0005C\u0005\u0005R\u0002\u0011\r\u0011\"\u0002\u0004n\u0006\u0019\"/Y<GY\u0006<\u0007+[2lY\u0016$wJ\u001d3fe\"AAQ\u001b\u0001!\u0002\u001b\u0019y/\u0001\u000bsC^4E.Y4QS\u000e\\G.\u001a3Pe\u0012,'\u000fI\u0004\b\t3\u0014\u0001\u0012\u0001Cn\u0003\u00151E.Y4t!\rYAQ\u001c\u0004\u0007\u0003\tA\t\u0001b8\u0014\u0007\u0011u\u0017\u0003C\u0004\u0010\t;$\t\u0001b9\u0015\u0005\u0011m\u0007")
public class Flags
extends ModifierFlags {
    private final int METHOD;
    private final int MODULE;
    private final int PACKAGE;
    private final int CAPTURED;
    private final int LABEL;
    private final int INCONSTRUCTOR;
    private final int SYNTHETIC;
    private final int STABLE;
    private final int BRIDGE;
    private final int ACCESSOR;
    private final int SUPERACCESSOR;
    private final int MODULEVAR;
    private final long IS_ERROR;
    private final long OVERLOADED;
    private final long LIFTED;
    private final long MIXEDIN;
    private final long EXISTENTIAL;
    private final long EXPANDEDNAME;
    private final long IMPLCLASS;
    private final long TRANS_FLAG;
    private final long LOCKED;
    private final long SPECIALIZED;
    private final long VBRIDGE;
    private final long VARARGS;
    private final long TRIEDCOOKING;
    private final long SYNCHRONIZED;
    private final long InitialFlags;
    private final long LateFlags;
    private final long AntiFlags;
    private final int LateShift;
    private final int AntiShift;
    private final long OverloadedFlagsMask;
    private final long lateDEFERRED = 16L << 47;
    private final long lateFINAL = 32L << 47;
    private final long lateINTERFACE = 128L << 47;
    private final long lateMETHOD = 64L << 47;
    private final long lateMODULE = 256L << 47;
    private final long notOVERRIDE = 2L << 56;
    private final long notPRIVATE = 4L << 56;
    private final long notPROTECTED = 1L << 56;
    private final long AllFlags;
    private final int TopLevelCreationFlags;
    private final int PackageFlags;
    private final int ModuleFlags;
    private final long ExplicitFlags;
    private final long BridgeFlags;
    private final long BridgeAndPrivateFlags;
    private final long PrintableFlags;
    private final long FieldFlags;
    private final long GetterFlags = 0x2000001000L ^ 0xFFFFFFFFFFFFFFFFL;
    private final long SetterFlags = 0x2001401200L ^ 0xFFFFFFFFFFFFFFFFL;
    private final int DefaultGetterFlags;
    private final int ValueParameterFlags;
    private final int BeanPropertyFlags;
    private final int VarianceFlags;
    private final int ConstrFlags;
    private final int ModuleToClassFlags;
    private final long FlagsNotPickled;
    private final long PickledFlags;
    private final long TopLevelPickledFlags;
    private final int IMPLICIT_PKL;
    private final int FINAL_PKL;
    private final int PRIVATE_PKL;
    private final int PROTECTED_PKL;
    private final int SEALED_PKL;
    private final int OVERRIDE_PKL;
    private final int CASE_PKL;
    private final int ABSTRACT_PKL;
    private final int DEFERRED_PKL;
    private final int METHOD_PKL;
    private final int MODULE_PKL;
    private final int INTERFACE_PKL;
    private final int PKL_MASK;
    private final long[] mappedRawFlags;
    private final long[] mappedPickledFlags;
    private final Function1<Object, Object> rawToPickledFlags;
    private final Function1<Object, Object> pickledToRawFlags;
    private final int MaxBitPosition;
    private final List<Object> pickledListOrder;
    private final long[] rawFlagPickledOrder;

    public final int METHOD() {
        return 64;
    }

    public final int MODULE() {
        return 256;
    }

    public final int PACKAGE() {
        return 16384;
    }

    public final int CAPTURED() {
        return 65536;
    }

    public final int LABEL() {
        return 131072;
    }

    public final int INCONSTRUCTOR() {
        return 131072;
    }

    public final int SYNTHETIC() {
        return 0x200000;
    }

    public final int STABLE() {
        return 0x400000;
    }

    public final int BRIDGE() {
        return 0x4000000;
    }

    public final int ACCESSOR() {
        return 0x8000000;
    }

    public final int SUPERACCESSOR() {
        return 0x10000000;
    }

    public final int MODULEVAR() {
        return 0x40000000;
    }

    public final long IS_ERROR() {
        return 0x100000000L;
    }

    public final long OVERLOADED() {
        return 0x200000000L;
    }

    public final long LIFTED() {
        return 0x400000000L;
    }

    public final long MIXEDIN() {
        return 0x800000000L;
    }

    public final long EXISTENTIAL() {
        return 0x800000000L;
    }

    public final long EXPANDEDNAME() {
        return 0x1000000000L;
    }

    public final long IMPLCLASS() {
        return 0x2000000000L;
    }

    public final long TRANS_FLAG() {
        return 0x4000000000L;
    }

    public final long LOCKED() {
        return 0x8000000000L;
    }

    public final long SPECIALIZED() {
        return 0x10000000000L;
    }

    public final long VBRIDGE() {
        return 0x40000000000L;
    }

    public final long VARARGS() {
        return 0x80000000000L;
    }

    public final long TRIEDCOOKING() {
        return 0x100000000000L;
    }

    public final long SYNCHRONIZED() {
        return 0x200000000000L;
    }

    public final long InitialFlags() {
        return 0x7FFFFFFFFFFFFL;
    }

    public final long LateFlags() {
        return 0xF8000000000000L;
    }

    public final long AntiFlags() {
        return 0x700000000000000L;
    }

    public final int LateShift() {
        return 47;
    }

    public final int AntiShift() {
        return 56;
    }

    public long OverloadedFlagsMask() {
        return this.OverloadedFlagsMask;
    }

    public final long lateDEFERRED() {
        return this.lateDEFERRED;
    }

    public final long lateFINAL() {
        return this.lateFINAL;
    }

    public final long lateINTERFACE() {
        return this.lateINTERFACE;
    }

    public final long lateMETHOD() {
        return this.lateMETHOD;
    }

    public final long lateMODULE() {
        return this.lateMODULE;
    }

    public final long notOVERRIDE() {
        return this.notOVERRIDE;
    }

    public final long notPRIVATE() {
        return this.notPRIVATE;
    }

    public final long notPROTECTED() {
        return this.notPROTECTED;
    }

    public final long AllFlags() {
        return -1L;
    }

    public final int TopLevelCreationFlags() {
        return 1065248;
    }

    public final int PackageFlags() {
        return 1065248;
    }

    public final int ModuleFlags() {
        return 256;
    }

    public final long ExplicitFlags() {
        return 140739636104751L;
    }

    public final long BridgeFlags() {
        return 0x40004000000L;
    }

    public final long BridgeAndPrivateFlags() {
        return 0x40004000004L;
    }

    public final long PrintableFlags() {
        return 251791349157423L;
    }

    public final long FieldFlags() {
        return 140148477984L;
    }

    public final long GetterFlags() {
        return this.GetterFlags;
    }

    public final long SetterFlags() {
        return this.SetterFlags;
    }

    public final int DefaultGetterFlags() {
        return 0x20000025;
    }

    public final int ValueParameterFlags() {
        return 39911936;
    }

    public final int BeanPropertyFlags() {
        return 8388626;
    }

    public final int VarianceFlags() {
        return 196608;
    }

    public final int ConstrFlags() {
        return 0x100000;
    }

    public final int ModuleToClassFlags() {
        return 3688741;
    }

    public final long FlagsNotPickled() {
        return 18446884536320L;
    }

    public final long PickledFlags() {
        return this.PickledFlags;
    }

    public final long TopLevelPickledFlags() {
        return this.TopLevelPickledFlags;
    }

    public long paramFlagsToDefaultGetter(long paramFlags) {
        return paramFlags & 0x20000025L | 0x200000L | 0x40L | 0x2000000L;
    }

    public long getterFlags(long fieldFlags) {
        return 0x8000000L + ((fieldFlags & 0x1000L) != 0L ? fieldFlags & (long)(~4096) & (0x2000000000L ^ 0xFFFFFFFFFFFFFFFFL) : fieldFlags & (0x2000000000L ^ 0xFFFFFFFFFFFFFFFFL) | 0x400000L);
    }

    public long setterFlags(long fieldFlags) {
        return this.getterFlags(fieldFlags) & (long)(~0x400000) & (long)(~0x1000000);
    }

    private final int IMPLICIT_PKL() {
        return 1;
    }

    private final int FINAL_PKL() {
        return 2;
    }

    private final int PRIVATE_PKL() {
        return 4;
    }

    private final int PROTECTED_PKL() {
        return 8;
    }

    private final int SEALED_PKL() {
        return 16;
    }

    private final int OVERRIDE_PKL() {
        return 32;
    }

    private final int CASE_PKL() {
        return 64;
    }

    private final int ABSTRACT_PKL() {
        return 128;
    }

    private final int DEFERRED_PKL() {
        return 256;
    }

    private final int METHOD_PKL() {
        return 512;
    }

    private final int MODULE_PKL() {
        return 1024;
    }

    private final int INTERFACE_PKL() {
        return 2048;
    }

    private final int PKL_MASK() {
        return 4095;
    }

    private Tuple2<Object, Object>[] rawPickledCorrespondence() {
        return (Tuple2[])((Object[])new Tuple2[]{new Tuple2$mcJJ$sp(64L, 512L), new Tuple2$mcJJ$sp(4L, 4L), new Tuple2$mcJJ$sp(32L, 2L), new Tuple2$mcJJ$sp(1L, 8L), new Tuple2$mcJJ$sp(2048L, 64L), new Tuple2$mcJJ$sp(16L, 256L), new Tuple2$mcJJ$sp(256L, 1024L), new Tuple2$mcJJ$sp(2L, 32L), new Tuple2$mcJJ$sp(128L, 2048L), new Tuple2$mcJJ$sp(512L, 1L), new Tuple2$mcJJ$sp(1024L, 16L), new Tuple2$mcJJ$sp(8L, 128L)});
    }

    private long[] mappedRawFlags() {
        return this.mappedRawFlags;
    }

    private long[] mappedPickledFlags() {
        return this.mappedPickledFlags;
    }

    public Function1<Object, Object> rawToPickledFlags() {
        return this.rawToPickledFlags;
    }

    public Function1<Object, Object> pickledToRawFlags() {
        return this.pickledToRawFlags;
    }

    @Override
    public String flagToString(long flag) {
        String string2 = 1L == flag ? "protected" : (2L == flag ? "override" : (4L == flag ? "private" : (8L == flag ? "abstract" : (16L == flag ? "<deferred>" : (32L == flag ? "final" : (64L == flag ? "<method>" : (128L == flag ? "<interface>" : (256L == flag ? "<module>" : (512L == flag ? "implicit" : (1024L == flag ? "sealed" : (2048L == flag ? "case" : (4096L == flag ? "<mutable>" : (8192L == flag ? "<param>" : (16384L == flag ? "<package>" : (32768L == flag ? "<macro>" : (65536L == flag ? "<bynameparam/captured/covariant>" : (131072L == flag ? "<contravariant/inconstructor/label>" : (262144L == flag ? "absoverride" : (524288L == flag ? "<local>" : (0x100000L == flag ? "<java>" : (0x200000L == flag ? "<synthetic>" : (0x400000L == flag ? "<stable>" : (0x800000L == flag ? "<static>" : (0x1000000L == flag ? "<caseaccessor>" : (0x2000000L == flag ? "<defaultparam/trait>" : (0x4000000L == flag ? "<bridge>" : (0x8000000L == flag ? "<accessor>" : (0x10000000L == flag ? "<superaccessor>" : (0x20000000L == flag ? "<paramaccessor>" : (0x40000000L == flag ? "<modulevar>" : (0x80000000L == flag ? "lazy" : (0x100000000L == flag ? "<is_error>" : (0x200000000L == flag ? "<overloaded>" : (0x400000000L == flag ? "<lifted>" : (0x800000000L == flag ? "<existential/mixedin>" : (0x1000000000L == flag ? "<expandedname>" : (0x2000000000L == flag ? "<implclass/presuper>" : (0x4000000000L == flag ? "<trans_flag>" : (0x8000000000L == flag ? "<locked>" : (0x10000000000L == flag ? "<specialized>" : (0x20000000000L == flag ? "<defaultinit>" : (0x40000000000L == flag ? "<vbridge>" : (0x80000000000L == flag ? "<varargs>" : (0x100000000000L == flag ? "<triedcooking>" : (0x200000000000L == flag ? "<synchronized>" : (0x400000000000L == flag ? "<artifact>" : (0x800000000000L == flag ? "<defaultmethod>" : (0x1000000000000L == flag ? "<enum>" : (0x2000000000000L == flag ? "<annotation>" : (0x4000000000000L == flag ? "" : (this.lateDEFERRED() == flag ? "<latedeferred>" : (this.lateFINAL() == flag ? "<latefinal>" : (this.lateMETHOD() == flag ? "<latemethod>" : (this.lateINTERFACE() == flag ? "<lateinterface>" : (this.lateMODULE() == flag ? "<latemodule>" : (this.notPROTECTED() == flag ? "<notprotected>" : (this.notOVERRIDE() == flag ? "<notoverride>" : (this.notPRIVATE() == flag ? "<notprivate>" : (0x800000000000000L == flag ? "" : (0x1000000000000000L == flag ? "" : (0x2000000000000000L == flag ? "" : (0x4000000000000000L == flag ? "" : (Long.MIN_VALUE == flag ? "" : "")))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))));
        return string2;
    }

    private String accessString(long flags, String privateWithin) {
        String string2 = privateWithin;
        return string2 != null && string2.equals("") ? ((flags & 0x80004L) == 524292L ? "private[this]" : ((flags & 0x80001L) == 524289L ? "protected[this]" : ((flags & 4L) != 0L ? "private" : ((flags & 1L) != 0L ? "protected" : "")))) : ((flags & 1L) != 0L ? new StringBuilder().append((Object)"protected[").append((Object)privateWithin).append((Object)"]").toString() : new StringBuilder().append((Object)"private[").append((Object)privateWithin).append((Object)"]").toString());
    }

    public String flagsToString(long flags, String privateWithin) {
        String access = this.accessString(flags, privateWithin);
        String nonAccess = this.flagsToString(flags & (long)(~524293));
        return ((TraversableOnce)((AbstractTraversable)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{nonAccess, access}))).filterNot((Function1)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(String x$6) {
                String string2 = x$6;
                return string2 != null && string2.equals("");
            }
        }))).mkString(" ");
    }

    public String flagsToString(long flags) {
        String string2;
        if (flags == 0L) {
            string2 = "";
        } else {
            StringBuilder sb = null;
            for (int i = 0; i <= 62; ++i) {
                java.io.Serializable serializable;
                long mask = this.rawFlagPickledOrder()[i];
                if ((flags & mask) != 0L) {
                    String s2 = this.flagToString(mask);
                    if (s2.length() > 0) {
                        if (sb == null) {
                            sb = new StringBuilder().append(s2);
                            serializable = BoxedUnit.UNIT;
                            continue;
                        }
                        if (sb.length() == 0) {
                            serializable = sb.append(s2);
                            continue;
                        }
                        serializable = sb.append(" ").append(s2);
                        continue;
                    }
                    serializable = BoxedUnit.UNIT;
                    continue;
                }
                serializable = BoxedUnit.UNIT;
            }
            string2 = sb == null ? "" : sb.toString();
        }
        return string2;
    }

    public final int MaxBitPosition() {
        return 62;
    }

    public final List<Object> pickledListOrder() {
        return this.pickledListOrder;
    }

    public final long[] rawFlagPickledOrder() {
        return this.rawFlagPickledOrder;
    }

    public Flags() {
        this.OverloadedFlagsMask = 171832442880L;
        boolean bl = (this.OverloadedFlagsMask() & 0x10C700000000L) == 0L;
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)this.flagsToString(this.OverloadedFlagsMask() & 0x10C700000000L)).toString());
        }
        this.PickledFlags = 0x7FFFFFFFFFFFFL & (0x10C700000000L ^ 0xFFFFFFFFFFFFFFFFL) | this.notPRIVATE();
        this.TopLevelPickledFlags = this.PickledFlags() & (0x800006140L ^ 0xFFFFFFFFFFFFFFFFL);
        Object[] objectArray = this.rawPickledCorrespondence();
        Predef$ predef$2 = Predef$.MODULE$;
        this.mappedRawFlags = (long[])new ArrayOps.ofRef<Object>(objectArray).map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final long apply(Tuple2<Object, Object> x$1) {
                return x$1._1$mcJ$sp();
            }
        }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.Long()));
        Object[] objectArray2 = this.rawPickledCorrespondence();
        Predef$ predef$3 = Predef$.MODULE$;
        this.mappedPickledFlags = (long[])new ArrayOps.ofRef<Object>(objectArray2).map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final long apply(Tuple2<Object, Object> x$2) {
                return x$2._2$mcJ$sp();
            }
        }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.Long()));
        this.rawToPickledFlags = new MapFlags(this, this.mappedRawFlags(), this.mappedPickledFlags());
        this.pickledToRawFlags = new MapFlags(this, this.mappedPickledFlags(), this.mappedRawFlags());
        Predef$ predef$4 = Predef$.MODULE$;
        IndexedSeq<Object> all = Range$.MODULE$.inclusive(0, 62).map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final long apply(int x$7) {
                return 1L << x$7;
            }

            public long apply$mcJI$sp(int x$7) {
                return 1L << x$7;
            }
        }, IndexedSeq$.MODULE$.canBuildFrom());
        long[] lArray = this.mappedRawFlags();
        Predef$ predef$5 = Predef$.MODULE$;
        long[] front = (long[])new ArrayOps.ofLong(lArray).map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final long apply(long x$8) {
                return x$8;
            }

            public long apply$mcJJ$sp(long x$8) {
                return x$8;
            }
        }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.Long()));
        Predef$ predef$6 = Predef$.MODULE$;
        this.pickledListOrder = new ArrayOps.ofLong(front).toList().$plus$plus((GenTraversableOnce)all.filterNot((Function1<Object, Object>)((Object)new Serializable(this, front){
            public static final long serialVersionUID = 0L;
            private final long[] front$1;

            public final boolean apply(long x$9) {
                return this.apply$mcZJ$sp(x$9);
            }

            public boolean apply$mcZJ$sp(long x$9) {
                return Predef$.MODULE$.longArrayOps(this.front$1).contains(BoxesRunTime.boxToLong(x$9));
            }
            {
                this.front$1 = front$1;
            }
        })), List$.MODULE$.canBuildFrom());
        this.rawFlagPickledOrder = (long[])this.pickledListOrder().toArray(ClassTag$.MODULE$.Long());
    }

    public class MapFlags
    implements Function1$mcJJ$sp {
        public final long[] scala$reflect$internal$Flags$MapFlags$$from;
        public final long[] scala$reflect$internal$Flags$MapFlags$$to;
        private final long fromSet;
        public final /* synthetic */ Flags $outer;

        @Override
        public boolean apply$mcZD$sp(double v1) {
            return Function1$class.apply$mcZD$sp(this, v1);
        }

        @Override
        public double apply$mcDD$sp(double v1) {
            return Function1$class.apply$mcDD$sp(this, v1);
        }

        @Override
        public float apply$mcFD$sp(double v1) {
            return Function1$class.apply$mcFD$sp(this, v1);
        }

        @Override
        public int apply$mcID$sp(double v1) {
            return Function1$class.apply$mcID$sp(this, v1);
        }

        @Override
        public long apply$mcJD$sp(double v1) {
            return Function1$class.apply$mcJD$sp(this, v1);
        }

        @Override
        public void apply$mcVD$sp(double v1) {
            Function1$class.apply$mcVD$sp(this, v1);
        }

        @Override
        public boolean apply$mcZF$sp(float v1) {
            return Function1$class.apply$mcZF$sp(this, v1);
        }

        @Override
        public double apply$mcDF$sp(float v1) {
            return Function1$class.apply$mcDF$sp(this, v1);
        }

        @Override
        public float apply$mcFF$sp(float v1) {
            return Function1$class.apply$mcFF$sp(this, v1);
        }

        @Override
        public int apply$mcIF$sp(float v1) {
            return Function1$class.apply$mcIF$sp(this, v1);
        }

        @Override
        public long apply$mcJF$sp(float v1) {
            return Function1$class.apply$mcJF$sp(this, v1);
        }

        @Override
        public void apply$mcVF$sp(float v1) {
            Function1$class.apply$mcVF$sp(this, v1);
        }

        @Override
        public boolean apply$mcZI$sp(int v1) {
            return Function1$class.apply$mcZI$sp(this, v1);
        }

        @Override
        public double apply$mcDI$sp(int v1) {
            return Function1$class.apply$mcDI$sp(this, v1);
        }

        @Override
        public float apply$mcFI$sp(int v1) {
            return Function1$class.apply$mcFI$sp(this, v1);
        }

        @Override
        public int apply$mcII$sp(int v1) {
            return Function1$class.apply$mcII$sp(this, v1);
        }

        @Override
        public long apply$mcJI$sp(int v1) {
            return Function1$class.apply$mcJI$sp(this, v1);
        }

        @Override
        public void apply$mcVI$sp(int v1) {
            Function1$class.apply$mcVI$sp(this, v1);
        }

        @Override
        public boolean apply$mcZJ$sp(long v1) {
            return Function1$class.apply$mcZJ$sp(this, v1);
        }

        @Override
        public double apply$mcDJ$sp(long v1) {
            return Function1$class.apply$mcDJ$sp(this, v1);
        }

        @Override
        public float apply$mcFJ$sp(long v1) {
            return Function1$class.apply$mcFJ$sp(this, v1);
        }

        @Override
        public int apply$mcIJ$sp(long v1) {
            return Function1$class.apply$mcIJ$sp(this, v1);
        }

        @Override
        public void apply$mcVJ$sp(long v1) {
            Function1$class.apply$mcVJ$sp(this, v1);
        }

        @Override
        public <A> Function1<A, Object> compose(Function1<A, Object> g) {
            return Function1$class.compose(this, g);
        }

        @Override
        public <A> Function1<Object, A> andThen(Function1<Object, A> g) {
            return Function1$class.andThen(this, g);
        }

        @Override
        public String toString() {
            return Function1$class.toString(this);
        }

        public long fromSet() {
            return this.fromSet;
        }

        @Override
        public long apply(long flags) {
            return this.apply$mcJJ$sp(flags);
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public long apply$mcJJ$sp(long flags) {
            void var3_2;
            long result2 = flags & (this.fromSet() ^ 0xFFFFFFFFFFFFFFFFL);
            long tobeMapped = flags & this.fromSet();
            int i = 0;
            while (tobeMapped != 0L) {
                if ((tobeMapped & this.scala$reflect$internal$Flags$MapFlags$$from[i]) != 0L) {
                    result2 |= this.scala$reflect$internal$Flags$MapFlags$$to[i];
                    tobeMapped &= this.scala$reflect$internal$Flags$MapFlags$$from[i] ^ 0xFFFFFFFFFFFFFFFFL;
                }
                ++i;
            }
            return (long)var3_2;
        }

        public /* synthetic */ Flags scala$reflect$internal$Flags$MapFlags$$$outer() {
            return this.$outer;
        }

        public MapFlags(Flags $outer, long[] from2, long[] to2) {
            this.scala$reflect$internal$Flags$MapFlags$$from = from2;
            this.scala$reflect$internal$Flags$MapFlags$$to = to2;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Function1$class.$init$(this);
            Function1$mcJJ$sp$class.$init$(this);
            this.fromSet = BoxesRunTime.unboxToLong(Predef$.MODULE$.longArrayOps(from2).$div$colon(BoxesRunTime.boxToLong(0L), new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final long apply(long x$4, long x$5) {
                    return x$4 | x$5;
                }

                public long apply$mcJJJ$sp(long x$4, long x$5) {
                    return x$4 | x$5;
                }
            }));
        }
    }
}

