/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.AbstractSeq;
import scala.collection.CustomParallelizable;
import scala.collection.CustomParallelizable$class;
import scala.collection.GenSeqLike$class;
import scala.collection.IndexedSeq;
import scala.collection.IndexedSeq$class;
import scala.collection.IndexedSeqLike$class;
import scala.collection.Iterator;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Range$;
import scala.collection.immutable.Seq$class;
import scala.collection.immutable.StringOps;
import scala.collection.immutable.Traversable$class;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParRange;
import scala.math.Numeric;
import scala.math.Numeric$IntIsIntegral$;
import scala.math.Ordering;
import scala.math.Ordering$Int$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u0011%c\u0001B\u0001\u0003\u0001%\u0011QAU1oO\u0016T!a\u0001\u0003\u0002\u0013%lW.\u001e;bE2,'BA\u0003\u0007\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011#\u0002\u0001\u000b%Y\u0001\u0003cA\u0006\r\u001d5\tA!\u0003\u0002\u000e\t\tY\u0011IY:ue\u0006\u001cGoU3r!\ty\u0001#D\u0001\u0007\u0013\t\tbAA\u0002J]R\u00042a\u0005\u000b\u000f\u001b\u0005\u0011\u0011BA\u000b\u0003\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\t\u0005\u0017]q\u0011$\u0003\u0002\u0019\t\t!2)^:u_6\u0004\u0016M]1mY\u0016d\u0017N_1cY\u0016\u0004\"A\u0007\u0010\u000e\u0003mQ!a\u0001\u000f\u000b\u0005u!\u0011\u0001\u00039be\u0006dG.\u001a7\n\u0005}Y\"\u0001\u0003)beJ\u000bgnZ3\u0011\u0005=\t\u0013B\u0001\u0012\u0007\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011!!\u0003A!b\u0001\n\u0003)\u0013!B:uCJ$X#\u0001\b\t\u0011\u001d\u0002!\u0011!Q\u0001\n9\taa\u001d;beR\u0004\u0003\u0002C\u0015\u0001\u0005\u000b\u0007I\u0011A\u0013\u0002\u0007\u0015tG\r\u0003\u0005,\u0001\t\u0005\t\u0015!\u0003\u000f\u0003\u0011)g\u000e\u001a\u0011\t\u00115\u0002!Q1A\u0005\u0002\u0015\nAa\u001d;fa\"Aq\u0006\u0001B\u0001B\u0003%a\"A\u0003ti\u0016\u0004\b\u0005C\u00032\u0001\u0011\u0005!'\u0001\u0004=S:LGO\u0010\u000b\u0005gQ*d\u0007\u0005\u0002\u0014\u0001!)A\u0005\ra\u0001\u001d!)\u0011\u0006\ra\u0001\u001d!)Q\u0006\ra\u0001\u001d!)\u0001\b\u0001C!s\u0005\u0019\u0001/\u0019:\u0016\u0003eAQa\u000f\u0001\u0005\nq\n1aZ1q+\u0005i\u0004CA\b?\u0013\tydA\u0001\u0003M_:<\u0007\"B!\u0001\t\u0013\u0011\u0015aB5t\u000bb\f7\r^\u000b\u0002\u0007B\u0011q\u0002R\u0005\u0003\u000b\u001a\u0011qAQ8pY\u0016\fg\u000eC\u0003H\u0001\u0011%!)A\u0004iCN\u001cF/\u001e2\t\u000b%\u0003A\u0011\u0002\u001f\u0002\u00151|gn\u001a'f]\u001e$\b\u000eC\u0004L\u0001\t\u0007IQ\t\"\u0002\u000f%\u001cX)\u001c9us\"1Q\n\u0001Q\u0001\u000e\r\u000b\u0001\"[:F[B$\u0018\u0010\t\u0005\b\u001f\u0002\u0011\r\u0011\"\u0002&\u0003AqW/\u001c*b]\u001e,W\t\\3nK:$8\u000f\u000b\u0003O#R3\u0006CA\bS\u0013\t\u0019fA\u0001\u0006eKB\u0014XmY1uK\u0012\f\u0013!V\u00018)\"L7\u000fI7fi\"|G\rI<jY2\u0004#-\u001a\u0011nC\u0012,\u0007\u0005\u001d:jm\u0006$X\r\f\u0011vg\u0016\u0004\u0003\r\\3oORD\u0007\rI5ogR,\u0017\r\u001a\u0018\"\u0003]\u000bAA\r\u00182c!1\u0011\f\u0001Q\u0001\u000e9\t\u0011C\\;n%\u0006tw-Z#mK6,g\u000e^:!\u0011\u001dY\u0006A1A\u0005\u0006\u0015\n1\u0002\\1ti\u0016cW-\\3oi\"\"!,U/WC\u0005q\u0016!\u000e+iSN\u0004S.\u001a;i_\u0012\u0004s/\u001b7mA\t,\u0007%\\1eK\u0002\u0002(/\u001b<bi\u0016d\u0003%^:fA\u0001d\u0017m\u001d;aA%t7\u000f^3bI:Ba\u0001\u0019\u0001!\u0002\u001bq\u0011\u0001\u00047bgR,E.Z7f]R\u0004\u0003b\u00022\u0001\u0005\u0004%)!J\u0001\u0010i\u0016\u0014X.\u001b8bY\u0016cW-\\3oi\"\"\u0011-\u00153WC\u0005)\u0017!\t+iSN\u0004S.\u001a;i_\u0012\u0004s/\u001b7mA\t,\u0007%\\1eK\u0002\u0002(/\u001b<bi\u0016t\u0003BB4\u0001A\u00035a\"\u0001\tuKJl\u0017N\\1m\u000b2,W.\u001a8uA!)\u0011\u000e\u0001C!K\u0005!A.Y:u\u0011\u0015Y\u0007\u0001\"\u0011&\u0003\u0011AW-\u00193\t\u000b5\u0004A\u0011\t8\u0002\u00075Lg.\u0006\u0002pwR\u0011a\u0002\u001d\u0005\u0006c2\u0004\u001dA]\u0001\u0004_J$\u0007cA:ws:\u0011q\u0002^\u0005\u0003k\u001a\tq\u0001]1dW\u0006<W-\u0003\u0002xq\nAqJ\u001d3fe&twM\u0003\u0002v\rA\u0011!p\u001f\u0007\u0001\t\u0015aHN1\u0001~\u0005\t\t\u0015'\u0005\u0002\u000f}B\u0011qb`\u0005\u0004\u0003\u00031!aA!os\"9\u0011Q\u0001\u0001\u0005B\u0005\u001d\u0011aA7bqV!\u0011\u0011BA\t)\rq\u00111\u0002\u0005\bc\u0006\r\u00019AA\u0007!\u0011\u0019h/a\u0004\u0011\u0007i\f\t\u0002\u0002\u0004}\u0003\u0007\u0011\r! \u0005\b\u0003+\u0001A\u0011CA\f\u0003\u0011\u0019w\u000e]=\u0015\u000fM\nI\"a\u0007\u0002\u001e!1A%a\u0005A\u00029Aa!KA\n\u0001\u0004q\u0001BB\u0017\u0002\u0014\u0001\u0007a\u0002C\u0004\u0002\"\u0001!\t!a\t\u0002\u0005\tLHcA\u001a\u0002&!1Q&a\bA\u00029Aa!!\u000b\u0001\t\u0003\u0011\u0015aC5t\u0013:\u001cG.^:jm\u0016Da!!\f\u0001\t\u0003*\u0013\u0001B:ju\u0016Da!!\r\u0001\t\u0003*\u0013A\u00027f]\u001e$\b\u000eC\u0004\u00026\u0001!I!a\u000e\u0002\u0017\u0011,7o\u0019:jaRLwN\\\u000b\u0003\u0003s\u0001B!a\u000f\u0002B9\u0019q\"!\u0010\n\u0007\u0005}b!\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003\u0007\n)E\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003\u007f1\u0001bBA%\u0001\u0011%\u00111J\u0001\u0005M\u0006LG\u000e\u0006\u0002\u0002NA\u0019q\"a\u0014\n\u0007\u0005EcAA\u0004O_RD\u0017N\\4\t\u000f\u0005U\u0003\u0001\"\u0003\u0002X\u0005\tb/\u00197jI\u0006$X-T1y\u0019\u0016tw\r\u001e5\u0015\u0005\u0005e\u0003cA\b\u0002\\%\u0019\u0011Q\f\u0004\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003C\u0002AQAA2\u0003\u0015\t\u0007\u000f\u001d7z)\rq\u0011Q\r\u0005\b\u0003O\ny\u00061\u0001\u000f\u0003\rIG\r\u001f\u0005\b\u0003W\u0002AQIA7\u0003\u001d1wN]3bG\",B!a\u001c\u0002~Q!\u0011\u0011LA9\u0011!\t\u0019(!\u001bA\u0002\u0005U\u0014!\u00014\u0011\r=\t9HDA>\u0013\r\tIH\u0002\u0002\n\rVt7\r^5p]F\u00022A_A?\t1\ty(!\u001b!\u0002\u0003\u0005)\u0019AAA\u0005\u0005)\u0016cAA'}\"2\u0011QPAC\u0003\u0017\u00032aDAD\u0013\r\tII\u0002\u0002\fgB,7-[1mSj,G-M\u0004%\u0003\u001b\u000by)!%\u000f\u0007=\ty)C\u0002\u0002\u0012\u001a\tA!\u00168ji\"\"\u0011\u0011NAK!\ry\u0011qS\u0005\u0004\u000333!AB5oY&tW\rC\u0004\u0002\u001e\u0002!)%a(\u0002\tQ\f7.\u001a\u000b\u0004g\u0005\u0005\u0006bBAR\u00037\u0003\rAD\u0001\u0002]\"9\u0011q\u0015\u0001\u0005F\u0005%\u0016\u0001\u00023s_B$2aMAV\u0011\u001d\t\u0019+!*A\u00029Aq!a,\u0001\t\u000b\n\t,\u0001\u0003j]&$X#A\u001a\t\u000f\u0005U\u0006\u0001\"\u0012\u00022\u0006!A/Y5m\u0011\u001d\tI\f\u0001C\u0005\u0003w\u000bA\"\u0019:h)\u0006\\Wm\u00165jY\u0016$2!PA_\u0011!\ty,a.A\u0002\u0005\u0005\u0017!\u00019\u0011\u000b=\t9HD\"\t\u000f\u0005\u0015\u0007\u0001\"\u0003\u0002H\u0006qAn\\2bi&|g.\u00114uKJtEc\u0001\b\u0002J\"9\u00111UAb\u0001\u0004q\u0001bBAg\u0001\u0011%\u0011qZ\u0001\u000e]\u0016<X)\u001c9usJ\u000bgnZ3\u0015\u0007M\n\t\u000eC\u0004\u0002T\u0006-\u0007\u0019\u0001\b\u0002\u000bY\fG.^3\t\u000f\u0005]\u0007\u0001\"\u0012\u0002Z\u0006IA/Y6f/\"LG.\u001a\u000b\u0004g\u0005m\u0007\u0002CA`\u0003+\u0004\r!!1\t\u000f\u0005}\u0007\u0001\"\u0012\u0002b\u0006IAM]8q/\"LG.\u001a\u000b\u0004g\u0005\r\b\u0002CA`\u0003;\u0004\r!!1\t\u000f\u0005\u001d\b\u0001\"\u0012\u0002j\u0006!1\u000f]1o)\u0011\tY/!=\u0011\u000b=\tioM\u001a\n\u0007\u0005=hA\u0001\u0004UkBdWM\r\u0005\t\u0003\u007f\u000b)\u000f1\u0001\u0002B\"9\u0011Q\u001f\u0001\u0005F\u0005]\u0018aB:qY&$\u0018\t\u001e\u000b\u0005\u0003W\fI\u0010C\u0004\u0002$\u0006M\b\u0019\u0001\b\t\u000f\u0005u\b\u0001\"\u0012\u0002\u0000\u0006IA/Y6f%&<\u0007\u000e\u001e\u000b\u0004g\t\u0005\u0001bBAR\u0003w\u0004\rA\u0004\u0005\b\u0005\u000b\u0001AQ\tB\u0004\u0003%!'o\u001c9SS\u001eDG\u000fF\u00024\u0005\u0013Aq!a)\u0003\u0004\u0001\u0007a\u0002C\u0004\u0003\u000e\u0001!)%!-\u0002\u000fI,g/\u001a:tK\"9!\u0011\u0003\u0001\u0005\u0002\u0005E\u0016!C5oG2,8/\u001b<f\u0011\u001d\u0011)\u0002\u0001C\u0003\u0005/\t\u0001bY8oi\u0006Lgn\u001d\u000b\u0004\u0007\ne\u0001b\u0002B\u000e\u0005'\u0001\rAD\u0001\u0002q\"9!q\u0004\u0001\u0005F\t\u0005\u0012aA:v[V!!1\u0005B\u0019)\rq!Q\u0005\u0005\t\u0005O\u0011i\u0002q\u0001\u0003*\u0005\u0019a.^7\u0011\u000bM\u0014YCa\f\n\u0007\t5\u0002PA\u0004Ok6,'/[2\u0011\u0007i\u0014\t\u0004B\u0004\u00034\tu!\u0019A?\u0003\u0003\tCqAa\u000e\u0001\t\u0003\n\t,\u0001\u0006u_&#XM]1cY\u0016DqAa\u000f\u0001\t\u0003\n\t,A\u0003u_N+\u0017\u000fC\u0004\u0003@\u0001!\tE!\u0011\u0002\r\u0015\fX/\u00197t)\r\u0019%1\t\u0005\b\u0005\u000b\u0012i\u00041\u0001\u007f\u0003\u0015yG\u000f[3s\u0011\u001d\u0011I\u0005\u0001C!\u0005\u0017\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003sAs\u0001\u0001B(\u0005+\u0012I\u0006E\u0002\u0010\u0005#J1Aa\u0015\u0007\u0005U!W\r\u001d:fG\u0006$X\rZ%oQ\u0016\u0014\u0018\u000e^1oG\u0016\f#Aa\u0016\u0002\tRCW\rI5na2,W.\u001a8uCRLwN\u001c\u0011eKR\f\u0017\u000e\\:!_\u001a\u0004#+\u00198hK\u0002j\u0017m[3tA%t\u0007.\u001a:ji&tw\r\t4s_6\u0004\u0013\u000e\u001e\u0011v]^L7/\u001a\u0018\"\u0005\tm\u0013A\u0002\u001a/cEr\u0003\u0007K\u0004\u0001\u0005?\n\u0019N!\u001a\u0011\u0007=\u0011\t'C\u0002\u0003d\u0019\u0011\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u001f\u0011%\\<\u0015VV\u0016e59qA!\u001b\u0003\u0011\u0003\u0011Y'A\u0003SC:<W\rE\u0002\u0014\u0005[2a!\u0001\u0002\t\u0002\t=4#\u0002B7\u0005c\u0002\u0003cA\b\u0003t%\u0019!Q\u000f\u0004\u0003\r\u0005s\u0017PU3g\u0011\u001d\t$Q\u000eC\u0001\u0005s\"\"Aa\u001b\t\u0015\tu$Q\u000eb\u0001\n\u0003\u0011Q%A\u0005N\u0003b{\u0006KU%O)\"A!\u0011\u0011B7A\u0003%a\"\u0001\u0006N\u0003b{\u0006KU%O)\u0002B\u0001B!\"\u0003n\u0011\u0005!qQ\u0001\u0006G>,h\u000e\u001e\u000b\n\u001d\t%%1\u0012BG\u0005\u001fCa\u0001\nBB\u0001\u0004q\u0001BB\u0015\u0003\u0004\u0002\u0007a\u0002\u0003\u0004.\u0005\u0007\u0003\rA\u0004\u0005\b\u0003S\u0011\u0019\t1\u0001D\u0011!\u0011)I!\u001c\u0005\u0002\tMEc\u0002\b\u0003\u0016\n]%\u0011\u0014\u0005\u0007I\tE\u0005\u0019\u0001\b\t\r%\u0012\t\n1\u0001\u000f\u0011\u0019i#\u0011\u0013a\u0001\u001d\u00199!Q\u0014B7\u0001\t}%!C%oG2,8/\u001b<f'\r\u0011Yj\r\u0005\u000bI\tm%\u0011!Q\u0001\n9\u0019\u0003BC\u0015\u0003\u001c\n\u0005\t\u0015!\u0003\u000fQ!QQFa'\u0003\u0002\u0003\u0006IA\u0004\u0017\t\u000fE\u0012Y\n\"\u0001\u0003*RA!1\u0016BX\u0005c\u0013\u0019\f\u0005\u0003\u0003.\nmUB\u0001B7\u0011\u0019!#q\u0015a\u0001\u001d!1\u0011Fa*A\u00029Aa!\fBT\u0001\u0004q\u0001bBA\u0015\u00057#\tE\u0011\u0005\t\u0003+\u0011Y\n\"\u0015\u0003:R91Ga/\u0003>\n}\u0006B\u0002\u0013\u00038\u0002\u0007a\u0002\u0003\u0004*\u0005o\u0003\rA\u0004\u0005\u0007[\t]\u0006\u0019\u0001\b\t\u0011\u0005\u0005$Q\u000eC\u0001\u0005\u0007$ra\rBc\u0005\u000f\u0014I\r\u0003\u0004%\u0005\u0003\u0004\rA\u0004\u0005\u0007S\t\u0005\u0007\u0019\u0001\b\t\r5\u0012\t\r1\u0001\u000f\u0011!\t\tG!\u001c\u0005\u0002\t5G#B\u001a\u0003P\nE\u0007B\u0002\u0013\u0003L\u0002\u0007a\u0002\u0003\u0004*\u0005\u0017\u0004\rA\u0004\u0005\t\u0005#\u0011i\u0007\"\u0001\u0003VRA!q\u001bBn\u0005;\u0014y\u000e\u0005\u0003\u0003Z\nmebA\n\u0003h!1AEa5A\u00029Aa!\u000bBj\u0001\u0004q\u0001BB\u0017\u0003T\u0002\u0007a\u0002\u0003\u0005\u0003\u0012\t5D\u0011\u0001Br)\u0019\u00119N!:\u0003h\"1AE!9A\u00029Aa!\u000bBq\u0001\u0004qq\u0001\u0003Bv\u0005[B\tA!<\u0002\r\tKw-\u00138u!\u0011\u0011iKa<\u0007\u0011\tE(Q\u000eE\u0001\u0005g\u0014aAQ5h\u0013:$8\u0003\u0002Bx\u0005cBq!\rBx\t\u0003\u00119\u0010\u0006\u0002\u0003n\"A\u0011\u0011\rBx\t\u0003\u0011Y\u0010\u0006\u0005\u0003~\u000e=1\u0011CB\n!\u0019\u0011yp!\u0002\u0004\f9\u00191c!\u0001\n\u0007\r\r!!\u0001\u0007Ok6,'/[2SC:<W-\u0003\u0003\u0004\b\r%!!C#yG2,8/\u001b<f\u0015\r\u0019\u0019A\u0001\t\u0004g\u000e5\u0011b\u0001Byq\"9AE!?A\u0002\r-\u0001bB\u0015\u0003z\u0002\u000711\u0002\u0005\b[\te\b\u0019AB\u0006\u0011!\u0011\tBa<\u0005\u0002\r]A\u0003CB\r\u0007;\u0019yb!\t\u0011\r\t}81DB\u0006\u0013\u0011\u0011ij!\u0003\t\u000f\u0011\u001a)\u00021\u0001\u0004\f!9\u0011f!\u0006A\u0002\r-\u0001bB\u0017\u0004\u0016\u0001\u000711B\u0004\t\u0007K\u0011i\u0007#\u0001\u0004(\u0005!Aj\u001c8h!\u0011\u0011ik!\u000b\u0007\u000f}\u0012i\u0007#\u0001\u0004,M!1\u0011\u0006B9\u0011\u001d\t4\u0011\u0006C\u0001\u0007_!\"aa\n\t\u0011\u0005\u00054\u0011\u0006C\u0001\u0007g!\u0002b!\u000e\u00048\re21\b\t\u0006\u0005\u007f\u001c)!\u0010\u0005\u0007I\rE\u0002\u0019A\u001f\t\r%\u001a\t\u00041\u0001>\u0011\u0019i3\u0011\u0007a\u0001{!A!\u0011CB\u0015\t\u0003\u0019y\u0004\u0006\u0005\u0004B\r\r3QIB$!\u0015\u0011ypa\u0007>\u0011\u0019!3Q\ba\u0001{!1\u0011f!\u0010A\u0002uBa!LB\u001f\u0001\u0004it\u0001CB&\u0005[B\ta!\u0014\u0002\u0015\tKw\rR3dS6\fG\u000e\u0005\u0003\u0003.\u000e=c\u0001CB)\u0005[B\taa\u0015\u0003\u0015\tKw\rR3dS6\fGn\u0005\u0003\u0004P\tE\u0004bB\u0019\u0004P\u0011\u00051q\u000b\u000b\u0003\u0007\u001bB!ba\u0017\u0004P\t\u0007I1AB/\u0003A\u0011\u0017n\u001a#fG\u0006\u001b\u0018J\u001c;fOJ\fG.\u0006\u0002\u0004`9!1\u0011MB=\u001d\u0011\u0019\u0019ga\u001d\u000f\t\r\u00154q\u000e\b\u0005\u0007O\u001ai'\u0004\u0002\u0004j)\u001911\u000e\u0005\u0002\rq\u0012xn\u001c;?\u0013\u00059\u0011bAB9\r\u0005!Q.\u0019;i\u0013\u0011\u0019)ha\u001e\u0002\u000f9+X.\u001a:jG*\u00191\u0011\u000f\u0004\n\t\rm4QP\u0001\u0017\u0005&<G)Z2j[\u0006d\u0017i]%g\u0013:$Xm\u001a:bY*!1QOB<\u0011%\u0019\tia\u0014!\u0002\u0013\u0019y&A\tcS\u001e$UmY!t\u0013:$Xm\u001a:bY\u0002B\u0001\"!\u0019\u0004P\u0011\u00051Q\u0011\u000b\t\u0007\u000f\u001biia$\u0004\u0012B1!q`B\u0003\u0007\u0013\u00032a]BF\u0013\r\u0019\t\u0006\u001f\u0005\bI\r\r\u0005\u0019ABE\u0011\u001dI31\u0011a\u0001\u0007\u0013Cq!LBB\u0001\u0004\u0019I\t\u0003\u0005\u0003\u0012\r=C\u0011ABK)!\u00199j!'\u0004\u001c\u000eu\u0005C\u0002B\u0000\u00077\u0019I\tC\u0004%\u0007'\u0003\ra!#\t\u000f%\u001a\u0019\n1\u0001\u0004\n\"9Qfa%A\u0002\r%u\u0001CBQ\u0005[B\taa)\u0002\r\u0011{WO\u00197f!\u0011\u0011ik!*\u0007\u0011\r\u001d&Q\u000eE\u0001\u0007S\u0013a\u0001R8vE2,7\u0003BBS\u0005cBq!MBS\t\u0003\u0019i\u000b\u0006\u0002\u0004$\"Q11LBS\u0005\u0004%\u0019a!\u0018\t\u0013\r\u00055Q\u0015Q\u0001\n\r}\u0003BCB[\u0007K\u0013\r\u0011b\u0001\u00048\u0006\u0001Bm\\;cY\u0016\f5/\u00138uK\u001e\u0014\u0018\r\\\u000b\u0003\u0007ssAa!\u0019\u0004<&!1QXB?\u0003I!u.\u001e2mK\u0006\u001b\u0018JZ%oi\u0016<'/\u00197\t\u0013\r\u00057Q\u0015Q\u0001\n\re\u0016!\u00053pk\ndW-Q:J]R,wM]1mA!A1QYBS\t\u0003\u00199-\u0001\u0003u_\n#E\u0003BBE\u0007\u0013D\u0001Ba\u0007\u0004D\u0002\u000711\u001a\t\u0004\u001f\r5\u0017bABT\r!A\u0011\u0011MBS\t\u0003\u0019\t\u000e\u0006\u0005\u0004T\u000ee71\\Bo!\u0015\u00192Q[Bf\u0013\r\u00199N\u0001\u0002\r\u001dVlWM]5d%\u0006tw-\u001a\u0005\bI\r=\u0007\u0019ABf\u0011\u001dI3q\u001aa\u0001\u0007\u0017Dq!LBh\u0001\u0004\u0019Y\r\u0003\u0005\u0003\u0012\r\u0015F\u0011ABq)!\u0019\u0019na9\u0004f\u000e\u001d\bb\u0002\u0013\u0004`\u0002\u000711\u001a\u0005\bS\r}\u0007\u0019ABf\u0011\u001di3q\u001ca\u0001\u0007\u00174qaa;\u0003n\u0001\u0019iOA\u0004QCJ$\u0018.\u00197\u0016\r\r=8q_B\u007f'\u0011\u0019IO!\u001d\t\u0017\u0005M4\u0011\u001eB\u0001B\u0003%11\u001f\t\b\u001f\u0005]4Q_B~!\rQ8q\u001f\u0003\t\u0007s\u001cIO1\u0001\u0002\u0002\n\tA\u000bE\u0002{\u0007{$\u0001\"a \u0004j\n\u0007\u0011\u0011\u0011\u0005\bc\r%H\u0011\u0001C\u0001)\u0011!\u0019\u0001\"\u0002\u0011\u0011\t56\u0011^B{\u0007wD\u0001\"a\u001d\u0004\u0000\u0002\u000711\u001f\u0005\t\u0003C\u0019I\u000f\"\u0001\u0005\nQ!11 C\u0006\u0011!\u0011Y\u0002b\u0002A\u0002\rUx\u0001\u0003C\b\u0005[B\t\u0001\"\u0005\u0002\u0007%sG\u000f\u0005\u0003\u0003.\u0012MaaB\t\u0003n!\u0005AQC\n\u0005\t'\u0011\t\bC\u00042\t'!\t\u0001\"\u0007\u0015\u0005\u0011E\u0001\u0002CA1\t'!\t\u0001\"\b\u0015\u0011\u0011}A\u0011\u0005C\u0012\tK\u0001RAa@\u0004\u00069Aa\u0001\nC\u000e\u0001\u0004q\u0001BB\u0015\u0005\u001c\u0001\u0007a\u0002\u0003\u0004.\t7\u0001\rA\u0004\u0005\t\u0005#!\u0019\u0002\"\u0001\u0005*QAA1\u0006C\u0017\t_!\t\u0004E\u0003\u0003\u0000\u000ema\u0002\u0003\u0004%\tO\u0001\rA\u0004\u0005\u0007S\u0011\u001d\u0002\u0019\u0001\b\t\r5\"9\u00031\u0001\u000f\u0011)!)D!\u001c\u0002\u0002\u0013%AqG\u0001\fe\u0016\fGMU3t_24X\r\u0006\u0002\u0005:A!A1\bC#\u001b\t!iD\u0003\u0003\u0005@\u0011\u0005\u0013\u0001\u00027b]\u001eT!\u0001b\u0011\u0002\t)\fg/Y\u0005\u0005\t\u000f\"iD\u0001\u0004PE*,7\r\u001e")
public class Range
extends AbstractSeq<Object>
implements scala.collection.immutable.IndexedSeq<Object>,
CustomParallelizable<Object, ParRange>,
Serializable {
    public static final long serialVersionUID = 7618862778670199309L;
    private final int start;
    private final int end;
    private final int step;
    private final boolean isEmpty;
    private final int numRangeElements;
    private final int lastElement;
    private final int terminalElement;

    @Override
    public Combiner<Object, ParRange> parCombiner() {
        return CustomParallelizable$class.parCombiner(this);
    }

    @Override
    public GenericCompanion<scala.collection.immutable.IndexedSeq> companion() {
        return scala.collection.immutable.IndexedSeq$class.companion(this);
    }

    @Override
    public scala.collection.immutable.IndexedSeq<Object> toIndexedSeq() {
        return scala.collection.immutable.IndexedSeq$class.toIndexedSeq(this);
    }

    @Override
    public scala.collection.immutable.IndexedSeq<Object> seq() {
        return scala.collection.immutable.IndexedSeq$class.seq(this);
    }

    @Override
    public int hashCode() {
        return IndexedSeqLike$class.hashCode(this);
    }

    @Override
    public IndexedSeq<Object> thisCollection() {
        return IndexedSeqLike$class.thisCollection(this);
    }

    @Override
    public IndexedSeq toCollection(Object repr) {
        return IndexedSeqLike$class.toCollection(this, repr);
    }

    @Override
    public Iterator<Object> iterator() {
        return IndexedSeqLike$class.iterator(this);
    }

    @Override
    public <A1> Buffer<A1> toBuffer() {
        return IndexedSeqLike$class.toBuffer(this);
    }

    public int start() {
        return this.start;
    }

    public int end() {
        return this.end;
    }

    public int step() {
        return this.step;
    }

    @Override
    public ParRange par() {
        return new ParRange(this);
    }

    private long gap() {
        return (long)this.end() - (long)this.start();
    }

    private boolean isExact() {
        return this.gap() % (long)this.step() == 0L;
    }

    private boolean hasStub() {
        return this.isInclusive() || !this.isExact();
    }

    private long longLength() {
        return this.gap() / (long)this.step() + (long)(this.hasStub() ? 1 : 0);
    }

    @Override
    public final boolean isEmpty() {
        return this.isEmpty;
    }

    public final int numRangeElements() {
        return this.numRangeElements;
    }

    public final int lastElement() {
        return this.lastElement;
    }

    public final int terminalElement() {
        return this.terminalElement;
    }

    @Override
    public int last() {
        return this.isEmpty() ? BoxesRunTime.unboxToInt(Nil$.MODULE$.last()) : this.lastElement();
    }

    @Override
    public int head() {
        if (this.isEmpty()) {
            throw Nil$.MODULE$.head();
        }
        return this.start();
    }

    @Override
    public <A1> int min(Ordering<A1> ord) {
        return ord == Ordering$Int$.MODULE$ ? (this.step() > 0 ? this.head() : this.last()) : BoxesRunTime.unboxToInt(TraversableOnce$class.min(this, ord));
    }

    @Override
    public <A1> int max(Ordering<A1> ord) {
        return ord == Ordering$Int$.MODULE$ ? (this.step() > 0 ? this.last() : this.head()) : BoxesRunTime.unboxToInt(TraversableOnce$class.max(this, ord));
    }

    public Range copy(int start, int end, int step) {
        return new Range(start, end, step);
    }

    public Range by(int step) {
        return this.copy(this.start(), this.end(), step);
    }

    public boolean isInclusive() {
        return false;
    }

    @Override
    public int size() {
        return this.length();
    }

    @Override
    public int length() {
        if (this.numRangeElements() < 0) {
            throw this.fail();
        }
        return this.numRangeElements();
    }

    private String description() {
        Predef$ predef$ = Predef$.MODULE$;
        return new StringOps("%d %s %d by %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.start()), this.isInclusive() ? "to" : "until", BoxesRunTime.boxToInteger(this.end()), BoxesRunTime.boxToInteger(this.step())}));
    }

    private Nothing$ fail() {
        throw new IllegalArgumentException(new StringBuilder().append((Object)this.description()).append((Object)": seqs cannot contain more than Int.MaxValue elements.").toString());
    }

    public void scala$collection$immutable$Range$$validateMaxLength() {
        if (this.numRangeElements() < 0) {
            throw this.fail();
        }
    }

    @Override
    public final int apply(int idx) {
        return this.apply$mcII$sp(idx);
    }

    @Override
    public final <U> void foreach(Function1<Object, U> f) {
        if (this.isEmpty()) {
            return;
        }
        int i = this.start();
        while (true) {
            f.apply(BoxesRunTime.boxToInteger(i));
            if (i == this.lastElement()) {
                return;
            }
            i += this.step();
        }
    }

    @Override
    public final Range take(int n) {
        return n <= 0 || this.isEmpty() ? this.newEmptyRange(this.start()) : (n >= this.numRangeElements() && this.numRangeElements() >= 0 ? this : new Inclusive(this.start(), this.locationAfterN(n - 1), this.step()));
    }

    @Override
    public final Range drop(int n) {
        return n <= 0 || this.isEmpty() ? this : (n >= this.numRangeElements() && this.numRangeElements() >= 0 ? this.newEmptyRange(this.end()) : this.copy(this.locationAfterN(n), this.end(), this.step()));
    }

    @Override
    public final Range init() {
        Object object = this.isEmpty() ? Nil$.MODULE$.init() : BoxedUnit.UNIT;
        return this.dropRight(1);
    }

    @Override
    public final Range tail() {
        Object object = this.isEmpty() ? Nil$.MODULE$.tail() : BoxedUnit.UNIT;
        return this.drop(1);
    }

    private long argTakeWhile(Function1<Object, Object> p) {
        long l;
        if (this.isEmpty()) {
            l = this.start();
        } else {
            int current;
            int stop = this.last();
            for (current = this.start(); current != stop && p.apply$mcZI$sp(current); current += this.step()) {
            }
            l = current == stop && p.apply$mcZI$sp(current) ? (long)current + (long)this.step() : (long)current;
        }
        return l;
    }

    private int locationAfterN(int n) {
        return this.start() + this.step() * n;
    }

    private Range newEmptyRange(int value) {
        return new Range(value, value, this.step());
    }

    @Override
    public final Range takeWhile(Function1<Object, Object> p) {
        int x;
        long stop = this.argTakeWhile(p);
        return stop == (long)this.start() ? this.newEmptyRange(this.start()) : ((x = (int)(stop - (long)this.step())) == this.last() ? this : new Inclusive(this.start(), x, this.step()));
    }

    @Override
    public final Range dropWhile(Function1<Object, Object> p) {
        int x;
        long stop = this.argTakeWhile(p);
        return stop == (long)this.start() ? this : ((x = (int)(stop - (long)this.step())) == this.last() ? this.newEmptyRange(this.last()) : new Inclusive(x + this.step(), this.last(), this.step()));
    }

    @Override
    public final Tuple2<Range, Range> span(Function1<Object, Object> p) {
        int x;
        long border = this.argTakeWhile(p);
        return border == (long)this.start() ? new Tuple2<Range, Range>(this.newEmptyRange(this.start()), this) : ((x = (int)(border - (long)this.step())) == this.last() ? new Tuple2<Range, Range>(this, this.newEmptyRange(this.last())) : new Tuple2<Inclusive, Inclusive>(new Inclusive(this.start(), x, this.step()), new Inclusive(x + this.step(), this.last(), this.step())));
    }

    @Override
    public final Tuple2<Range, Range> splitAt(int n) {
        return new Tuple2<Range, Range>(this.take(n), this.drop(n));
    }

    @Override
    public final Range takeRight(int n) {
        Range range2;
        if (n <= 0) {
            range2 = this.newEmptyRange(this.start());
        } else if (this.numRangeElements() >= 0) {
            range2 = this.drop(this.numRangeElements() - n);
        } else {
            int y = this.last();
            long x = (long)y - (long)this.step() * (long)(n - 1);
            range2 = this.step() > 0 && x < (long)this.start() || this.step() < 0 && x > (long)this.start() ? this : new Inclusive((int)x, y, this.step());
        }
        return range2;
    }

    @Override
    public final Range dropRight(int n) {
        Range range2;
        if (n <= 0) {
            range2 = this;
        } else if (this.numRangeElements() >= 0) {
            range2 = this.take(this.numRangeElements() - n);
        } else {
            int y = this.last() - this.step() * n;
            range2 = this.step() > 0 && y < this.start() || this.step() < 0 && y > this.start() ? this.newEmptyRange(this.start()) : new Inclusive(this.start(), y, this.step());
        }
        return range2;
    }

    @Override
    public final Range reverse() {
        return this.isEmpty() ? this : new Inclusive(this.last(), this.start(), -this.step());
    }

    public Range inclusive() {
        return this.isInclusive() ? this : new Inclusive(this.start(), this.end(), this.step());
    }

    @Override
    public final boolean contains(int x) {
        return x == this.end() && !this.isInclusive() ? false : (this.step() > 0 ? (x < this.start() || x > this.end() ? false : this.step() == 1 || (x - this.start()) % this.step() == 0) : (x < this.end() || x > this.start() ? false : this.step() == -1 || (x - this.start()) % this.step() == 0));
    }

    @Override
    public final <B> int sum(Numeric<B> num) {
        block7: {
            int n;
            block6: {
                block5: {
                    if (num != Numeric$IntIsIntegral$.MODULE$) break block5;
                    n = this.isEmpty() ? 0 : (this.numRangeElements() == 1 ? this.head() : (int)((long)this.numRangeElements() * ((long)this.head() + (long)this.last()) / 2L));
                    break block6;
                }
                if (!this.isEmpty()) break block7;
                n = num.toInt(num.zero());
            }
            return n;
        }
        Object acc = num.zero();
        int i = this.head();
        while (true) {
            acc = num.plus(acc, BoxesRunTime.boxToInteger(i));
            if (i == this.lastElement()) {
                return num.toInt(acc);
            }
            i += this.step();
        }
    }

    @Override
    public Range toIterable() {
        return this;
    }

    @Override
    public Range toSeq() {
        return this;
    }

    @Override
    public boolean equals(Object other) {
        int l0;
        Range range2;
        boolean bl = other instanceof Range ? (range2 = (Range)other).canEqual(this) && (this.isEmpty() ? range2.isEmpty() : range2.nonEmpty() && this.start() == range2.start() && (l0 = this.last()) == range2.last() && (this.start() == l0 || this.step() == range2.step())) : GenSeqLike$class.equals(this, other);
        return bl;
    }

    @Override
    public String toString() {
        String endStr = this.numRangeElements() > Range$.MODULE$.MAX_PRINT() || !this.isEmpty() && this.numRangeElements() < 0 ? ", ... )" : ")";
        return this.take(Range$.MODULE$.MAX_PRINT()).mkString("Range(", ", ", endStr);
    }

    public final void foreach$mVc$sp(Function1<Object, BoxedUnit> f) {
        if (this.isEmpty()) {
            return;
        }
        int i = this.start();
        while (true) {
            f.apply$mcVI$sp(i);
            if (i == this.lastElement()) {
                return;
            }
            i += this.step();
        }
    }

    @Override
    public int apply$mcII$sp(int idx) {
        this.scala$collection$immutable$Range$$validateMaxLength();
        if (idx < 0 || idx >= this.numRangeElements()) {
            throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(idx)).toString());
        }
        return this.start() + this.step() * idx;
    }

    public Range(int start, int end, int step) {
        int n;
        long len;
        this.start = start;
        this.end = end;
        this.step = step;
        Traversable$class.$init$(this);
        Iterable$class.$init$(this);
        Seq$class.$init$(this);
        IndexedSeqLike$class.$init$(this);
        IndexedSeq$class.$init$(this);
        scala.collection.immutable.IndexedSeq$class.$init$(this);
        CustomParallelizable$class.$init$(this);
        boolean bl = this.isEmpty = start > end && step > 0 || start < end && step < 0 || start == end && !this.isInclusive();
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0.");
        }
        int n2 = this.isEmpty() ? 0 : (this.numRangeElements = (len = this.longLength()) > Integer.MAX_VALUE ? -1 : (int)len);
        if (this.isEmpty()) {
            n = start - step;
        } else {
            switch (step) {
                default: {
                    int remainder = (int)(this.gap() % (long)step);
                    if (remainder != 0) {
                        n = end - remainder;
                        break;
                    }
                    if (this.isInclusive()) {
                        n = end;
                        break;
                    }
                    n = end - step;
                    break;
                }
                case -1: {
                    if (this.isInclusive()) {
                        n = end;
                        break;
                    }
                    n = end + 1;
                    break;
                }
                case 1: {
                    n = this.isInclusive() ? end : end - 1;
                }
            }
        }
        this.lastElement = n;
        this.terminalElement = this.lastElement() + step;
    }

    public static class Partial<T, U> {
        private final Function1<T, U> f;

        public U by(T x) {
            return this.f.apply(x);
        }

        public Partial(Function1<T, U> f) {
            this.f = f;
        }
    }

    public static class Inclusive
    extends Range {
        @Override
        public boolean isInclusive() {
            return true;
        }

        @Override
        public Range copy(int start, int end, int step) {
            return new Inclusive(start, end, step);
        }

        public Inclusive(int start, int end, int step) {
            super(start, end, step);
        }
    }
}

