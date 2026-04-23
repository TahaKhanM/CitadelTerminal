/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect;

import scala.None$;
import scala.Option;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WrappedArray;
import scala.reflect.AnyValManifest;
import scala.reflect.ClassManifestDeprecatedApis$class;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$class;
import scala.reflect.Manifest;
import scala.reflect.Manifest$class;
import scala.reflect.ManifestFactory$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;
import scala.runtime.Null$;

@ScalaSignature(bytes="\u0006\u0001\rex!B\u0001\u0003\u0011\u00039\u0011aD'b]&4Wm\u001d;GC\u000e$xN]=\u000b\u0005\r!\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000b\u0005)1oY1mC\u000e\u0001\u0001C\u0001\u0005\n\u001b\u0005\u0011a!\u0002\u0006\u0003\u0011\u0003Y!aD'b]&4Wm\u001d;GC\u000e$xN]=\u0014\u0005%a\u0001CA\u0007\u000f\u001b\u0005!\u0011BA\b\u0005\u0005\u0019\te.\u001f*fM\")\u0011#\u0003C\u0001%\u00051A(\u001b8jiz\"\u0012a\u0002\u0005\u0006)%!\t!F\u0001\u000fm\u0006dW/Z'b]&4Wm\u001d;t+\u00051\u0002cA\f\u001b;9\u0011Q\u0002G\u0005\u00033\u0011\tq\u0001]1dW\u0006<W-\u0003\u0002\u001c9\t!A*[:u\u0015\tIB\u0001\r\u0002\u001fGA\u0019\u0001bH\u0011\n\u0005\u0001\u0012!AD!osZ\u000bG.T1oS\u001a,7\u000f\u001e\t\u0003E\rb\u0001\u0001B\u0005%'\u0005\u0005\t\u0011!B\u0001K\t\u0019q\fJ\u001a\u0012\u0005\u0019J\u0003CA\u0007(\u0013\tACAA\u0004O_RD\u0017N\\4\u0011\u00055Q\u0013BA\u0016\u0005\u0005\r\te.\u001f\u0005\b[%\u0011\r\u0011\"\u0001/\u0003\u0011\u0011\u0015\u0010^3\u0016\u0003=\u00022\u0001C\u00101!\ti\u0011'\u0003\u00023\t\t!!)\u001f;f\u0011\u0019!\u0014\u0002)A\u0005_\u0005)!)\u001f;fA!9a'\u0003b\u0001\n\u00039\u0014!B*i_J$X#\u0001\u001d\u0011\u0007!y\u0012\b\u0005\u0002\u000eu%\u00111\b\u0002\u0002\u0006'\"|'\u000f\u001e\u0005\u0007{%\u0001\u000b\u0011\u0002\u001d\u0002\rMCwN\u001d;!\u0011\u001dy\u0014B1A\u0005\u0002\u0001\u000bAa\u00115beV\t\u0011\tE\u0002\t?\t\u0003\"!D\"\n\u0005\u0011#!\u0001B\"iCJDaAR\u0005!\u0002\u0013\t\u0015!B\"iCJ\u0004\u0003b\u0002%\n\u0005\u0004%\t!S\u0001\u0004\u0013:$X#\u0001&\u0011\u0007!y2\n\u0005\u0002\u000e\u0019&\u0011Q\n\u0002\u0002\u0004\u0013:$\bBB(\nA\u0003%!*\u0001\u0003J]R\u0004\u0003bB)\n\u0005\u0004%\tAU\u0001\u0005\u0019>tw-F\u0001T!\rAq\u0004\u0016\t\u0003\u001bUK!A\u0016\u0003\u0003\t1{gn\u001a\u0005\u00071&\u0001\u000b\u0011B*\u0002\u000b1{gn\u001a\u0011\t\u000fiK!\u0019!C\u00017\u0006)a\t\\8biV\tA\fE\u0002\t?u\u0003\"!\u00040\n\u0005}#!!\u0002$m_\u0006$\bBB1\nA\u0003%A,\u0001\u0004GY>\fG\u000f\t\u0005\bG&\u0011\r\u0011\"\u0001e\u0003\u0019!u.\u001e2mKV\tQ\rE\u0002\t?\u0019\u0004\"!D4\n\u0005!$!A\u0002#pk\ndW\r\u0003\u0004k\u0013\u0001\u0006I!Z\u0001\b\t>,(\r\\3!\u0011\u001da\u0017B1A\u0005\u00025\fqAQ8pY\u0016\fg.F\u0001o!\rAqd\u001c\t\u0003\u001bAL!!\u001d\u0003\u0003\u000f\t{w\u000e\\3b]\"11/\u0003Q\u0001\n9\f\u0001BQ8pY\u0016\fg\u000e\t\u0005\bk&\u0011\r\u0011\"\u0001w\u0003\u0011)f.\u001b;\u0016\u0003]\u00042\u0001C\u0010y!\ti\u00110\u0003\u0002{\t\t!QK\\5u\u0011\u0019a\u0018\u0002)A\u0005o\u0006)QK\\5uA!9a0\u0003b\u0001\n\u0013y\u0018AC(cU\u0016\u001cG\u000fV-Q\u000bV\u0011\u0011\u0011\u0001\t\u0007\u0003\u0007\ti!!\u0005\u000e\u0005\u0005\u0015!\u0002BA\u0004\u0003\u0013\tA\u0001\\1oO*\u0011\u00111B\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\u0010\u0005\u0015!!B\"mCN\u001c\b\u0003BA\u0002\u0003'IA!!\u0006\u0002\u0006\t1qJ\u00196fGRD\u0001\"!\u0007\nA\u0003%\u0011\u0011A\u0001\f\u001f\nTWm\u0019;U3B+\u0005\u0005C\u0005\u0002\u001e%\u0011\r\u0011\"\u0003\u0002 \u0005Yaj\u001c;iS:<G+\u0017)F+\t\t\t\u0003\u0005\u0004\u0002\u0004\u00055\u00111\u0005\t\u0005\u0003K\tY#\u0004\u0002\u0002()\u0019\u0011\u0011\u0006\u0003\u0002\u000fI,h\u000e^5nK&!\u0011QFA\u0014\u0005!qu\u000e\u001e5j]\u001e$\u0003\u0002CA\u0019\u0013\u0001\u0006I!!\t\u0002\u00199{G\u000f[5oORK\u0006+\u0012\u0011\t\u0013\u0005U\u0012B1A\u0005\n\u0005]\u0012\u0001\u0003(vY2$\u0016\fU#\u0016\u0005\u0005e\u0002CBA\u0002\u0003\u001b\tY\u0004\u0005\u0003\u0002&\u0005u\u0012\u0002BA \u0003O\u0011QAT;mY\u0012B\u0001\"a\u0011\nA\u0003%\u0011\u0011H\u0001\n\u001dVdG\u000eV-Q\u000b\u0002B\u0011\"a\u0012\n\u0005\u0004%\t!!\u0013\u0002\u0007\u0005s\u00170\u0006\u0002\u0002LA!\u0001\"!\u0014*\u0013\r\tyE\u0001\u0002\t\u001b\u0006t\u0017NZ3ti\"A\u00111K\u0005!\u0002\u0013\tY%\u0001\u0003B]f\u0004\u0003\"CA,\u0013\t\u0007I\u0011AA-\u0003\u0019y%M[3diV\u0011\u00111\f\t\u0006\u0011\u00055\u0013\u0011\u0003\u0005\t\u0003?J\u0001\u0015!\u0003\u0002\\\u00059qJ\u00196fGR\u0004\u0003\"CA2\u0013\t\u0007I\u0011AA3\u0003\u0019\te.\u001f*fMV\u0011\u0011q\r\t\u0005\u0011\u00055C\u0002\u0003\u0005\u0002l%\u0001\u000b\u0011BA4\u0003\u001d\te.\u001f*fM\u0002B\u0011\"a\u001c\n\u0005\u0004%\t!!\u001d\u0002\r\u0005s\u0017PV1m+\t\t\u0019\bE\u0003\t\u0003\u001b\n)\bE\u0002\u000e\u0003oJ1!!\u001f\u0005\u0005\u0019\te.\u001f,bY\"A\u0011QP\u0005!\u0002\u0013\t\u0019(A\u0004B]f4\u0016\r\u001c\u0011\t\u0013\u0005\u0005\u0015B1A\u0005\u0002\u0005\r\u0015\u0001\u0002(vY2,\"!!\"\u0011\u000b!\ti%a\"\u0011\u00075\tI)C\u0002\u0002\f\u0012\u0011AAT;mY\"A\u0011qR\u0005!\u0002\u0013\t))A\u0003Ok2d\u0007\u0005C\u0005\u0002\u0014&\u0011\r\u0011\"\u0001\u0002\u0016\u00069aj\u001c;iS:<WCAAL!\u0011A\u0011Q\n\u0014\t\u0011\u0005m\u0015\u0002)A\u0005\u0003/\u000b\u0001BT8uQ&tw\r\t\u0004\u0007\u0003?KA!!)\u0003+MKgn\u001a7fi>tG+\u001f9f\u001b\u0006t\u0017NZ3tiV!\u00111UAU'\u0015\ti\nDAS!\u0015A\u0011QJAT!\r\u0011\u0013\u0011\u0016\u0003\t\u0003W\u000biJ1\u0001\u0002.\n\tA+\u0005\u0002'\u0019!Q\u0011\u0011WAO\u0005\u0003\u0005\u000b\u0011\u0002\u0007\u0002\u000bY\fG.^3\t\u000fE\ti\n\"\u0001\u00026R!\u0011qWA^!\u0019\tI,!(\u0002(6\t\u0011\u0002C\u0004\u00022\u0006M\u0006\u0019\u0001\u0007\t\u0017\u0005}\u0016Q\u0014EC\u0002\u0013\u0005\u0011\u0011Y\u0001\reVtG/[7f\u00072\f7o]\u000b\u0003\u0003\u0007\u0004D!!2\u0002JB1\u00111AA\u0007\u0003\u000f\u00042AIAe\t1\tY-!4\u0002\u0002\u0003\u0005)\u0011AAW\u0005\ty\u0004\u0007C\u0006\u0002P\u0006u\u0005\u0012!Q!\n\u0005\r\u0017!\u0004:v]RLW.Z\"mCN\u001c\b\u0005C\u0006\u0002T\u0006u\u0005R1A\u0005B\u0005U\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0016\u0005\u0005]\u0007\u0003BA\u0002\u00033LA!a7\u0002\u0006\t11\u000b\u001e:j]\u001eD1\"a8\u0002\u001e\"\u0005\t\u0015)\u0003\u0002X\u0006IAo\\*ue&tw\r\t\u0005\b\u0003GLA\u0011AAs\u0003)\u0019\u0018N\\4mKRK\b/Z\u000b\u0005\u0003O\fi\u000f\u0006\u0003\u0002j\u0006=\b#\u0002\u0005\u0002N\u0005-\bc\u0001\u0012\u0002n\u0012A\u00111VAq\u0005\u0004\ti\u000bC\u0004\u00022\u0006\u0005\b\u0019\u0001\u0007\t\u000f\u0005M\u0018\u0002\"\u0001\u0002v\u0006I1\r\\1tgRK\b/Z\u000b\u0005\u0003o\fi\u0010\u0006\u0003\u0002z\u0006}\b#\u0002\u0005\u0002N\u0005m\bc\u0001\u0012\u0002~\u00129\u00111VAy\u0005\u0004)\u0003\u0002\u0003B\u0001\u0003c\u0004\rAa\u0001\u0002\u000b\rd\u0017M\u001f>1\t\t\u0015!1\u0003\t\u0007\u0005\u000f\u0011iA!\u0005\u000f\u00075\u0011I!C\u0002\u0003\f\u0011\ta\u0001\u0015:fI\u00164\u0017\u0002BA\b\u0005\u001fQ1Aa\u0003\u0005!\r\u0011#1\u0003\u0003\f\u0005+\ty0!A\u0001\u0002\u000b\u0005QE\u0001\u0003`IE\u0002\u0004bBAz\u0013\u0011\u0005!\u0011D\u000b\u0005\u00057\u0011\t\u0003\u0006\u0005\u0003\u001e\t\r\"q\u0005B\u001b!\u0015A\u0011Q\nB\u0010!\r\u0011#\u0011\u0005\u0003\b\u0003W\u00139B1\u0001&\u0011!\u0011\tAa\u0006A\u0002\t\u0015\u0002C\u0002B\u0004\u0005\u001b\u0011y\u0002\u0003\u0005\u0003*\t]\u0001\u0019\u0001B\u0016\u0003\u0011\t'oZ\u00191\t\t5\"\u0011\u0007\t\u0006\u0011\u00055#q\u0006\t\u0004E\tEBa\u0003B\u001a\u0005O\t\t\u0011!A\u0003\u0002\u0015\u0012Aa\u0018\u00132c!A!q\u0007B\f\u0001\u0004\u0011I$\u0001\u0003be\u001e\u001c\b#B\u0007\u0003<\t}\u0012b\u0001B\u001f\t\tQAH]3qK\u0006$X\r\u001a 1\t\t\u0005#Q\t\t\u0006\u0011\u00055#1\t\t\u0004E\t\u0015Ca\u0003B$\u0005\u0013\n\t\u0011!A\u0003\u0002\u0015\u0012Aa\u0018\u00132e!A!q\u0007B\f\u0001\u0004\u0011I\u0004C\u0004\u0002t&!\tA!\u0014\u0016\t\t=#Q\u000b\u000b\t\u0005#\u00129F!\u001a\u0003rA)\u0001\"!\u0014\u0003TA\u0019!E!\u0016\u0005\u000f\u0005-&1\nb\u0001K!A!\u0011\fB&\u0001\u0004\u0011Y&\u0001\u0004qe\u00164\u0017\u000e\u001f\u0019\u0005\u0005;\u0012\t\u0007E\u0003\t\u0003\u001b\u0012y\u0006E\u0002#\u0005C\"1Ba\u0019\u0003X\u0005\u0005\t\u0011!B\u0001K\t!q\fJ\u00194\u0011!\u0011\tAa\u0013A\u0002\t\u001d\u0004\u0007\u0002B5\u0005[\u0002bAa\u0002\u0003\u000e\t-\u0004c\u0001\u0012\u0003n\u0011Y!q\u000eB3\u0003\u0003\u0005\tQ!\u0001&\u0005\u0011yF%\r\u001b\t\u0011\t]\"1\na\u0001\u0005g\u0002R!\u0004B\u001e\u0005k\u0002DAa\u001e\u0003|A)\u0001\"!\u0014\u0003zA\u0019!Ea\u001f\u0005\u0017\tu$\u0011OA\u0001\u0002\u0003\u0015\t!\n\u0002\u0005?\u0012\nTGB\u0004\u0003\u0002&\tIAa!\u0003\u001fAC\u0017M\u001c;p[6\u000bg.\u001b4fgR,BA!\"\u0004\u0010M!!q\u0010BD!\u0019\tIL!#\u0004\u000e\u00191!1R\u0005\u0005\u0005\u001b\u0013\u0011c\u00117bgN$\u0016\u0010]3NC:Lg-Z:u+\u0011\u0011yI!&\u0014\u000b\t%EB!%\u0011\u000b!\tiEa%\u0011\u0007\t\u0012)\nB\u0004\u0002,\n%%\u0019A\u0013\t\u0017\te#\u0011\u0012B\u0001B\u0003%!\u0011\u0014\t\u0006\u001b\tm%qT\u0005\u0004\u0005;#!AB(qi&|g\u000e\r\u0003\u0003\"\n\u0015\u0006#\u0002\u0005\u0002N\t\r\u0006c\u0001\u0012\u0003&\u0012Y!q\u0015BL\u0003\u0003\u0005\tQ!\u0001&\u0005\u0011yF%M\u001c\t\u0017\u0005}&\u0011\u0012BC\u0002\u0013\u0005!1V\u000b\u0003\u0005[\u0003DAa,\u00034B1!q\u0001B\u0007\u0005c\u00032A\tBZ\t-\u0011)La.\u0002\u0002\u0003\u0005)\u0011A\u0013\u0003\t}#\u0013\u0007\u000f\u0005\f\u0003\u001f\u0014II!A!\u0002\u0013\u0011I\f\r\u0003\u0003<\n}\u0006C\u0002B\u0004\u0005\u001b\u0011i\fE\u0002#\u0005\u007f#1B!.\u00038\u0006\u0005\t\u0011!B\u0001K!Y!1\u0019BE\u0005\u000b\u0007I\u0011\tBc\u00035!\u0018\u0010]3Be\u001e,X.\u001a8ugV\u0011!q\u0019\t\u0005/i\u0011I\r\r\u0003\u0003L\n=\u0007#\u0002\u0005\u0002N\t5\u0007c\u0001\u0012\u0003P\u0012Y!\u0011\u001bBj\u0003\u0003\u0005\tQ!\u0001&\u0005\u0011yF%M\u001d\t\u0017\tU'\u0011\u0012B\u0001B\u0003%!q[\u0001\u000fif\u0004X-\u0011:hk6,g\u000e^:!!\u00119\"D!71\t\tm'q\u001c\t\u0006\u0011\u00055#Q\u001c\t\u0004E\t}Ga\u0003Bi\u0005'\f\t\u0011!A\u0003\u0002\u0015Bq!\u0005BE\t\u0003\u0011\u0019\u000f\u0006\u0005\u0003f\n\u001d(1\u001fB\u007f!\u0019\tIL!#\u0003\u0014\"A!\u0011\fBq\u0001\u0004\u0011I\u000fE\u0003\u000e\u00057\u0013Y\u000f\r\u0003\u0003n\nE\b#\u0002\u0005\u0002N\t=\bc\u0001\u0012\u0003r\u0012Y!q\u0015Bt\u0003\u0003\u0005\tQ!\u0001&\u0011!\tyL!9A\u0002\tU\b\u0007\u0002B|\u0005w\u0004bAa\u0002\u0003\u000e\te\bc\u0001\u0012\u0003|\u0012Y!Q\u0017Bz\u0003\u0003\u0005\tQ!\u0001&\u0011!\u0011\u0019M!9A\u0002\t}\b\u0003B\f\u001b\u0007\u0003\u0001Daa\u0001\u0004\bA)\u0001\"!\u0014\u0004\u0006A\u0019!ea\u0002\u0005\u0017\tE'Q`A\u0001\u0002\u0003\u0015\t!\n\u0005\t\u0003'\u0014I\t\"\u0011\u0004\fQ\u0011\u0011q\u001b\t\u0004E\r=AaBAV\u0005\u007f\u0012\r!\n\u0005\u000e\u0007'\u0011yH!A!\u0002\u0013\u0019)B!+\u0002\u001b}\u0013XO\u001c;j[\u0016\u001cE.Y:ta\u0011\u00199ba\u0007\u0011\r\t\u001d!QBB\r!\r\u001131\u0004\u0003\f\u0007;\u0019\t\"!A\u0001\u0002\u000b\u0005QE\u0001\u0003`IE2\u0004bCAj\u0005\u007f\u0012)\u0019!C!\u0007C)\"aa\t\u0011\t\t\u001d1QE\u0005\u0005\u00037\u0014y\u0001C\u0006\u0002`\n}$\u0011!Q\u0001\n\r\r\u0002bB\t\u0003\u0000\u0011\u000511\u0006\u000b\u0007\u0007[\u0019yc!\u000f\u0011\r\u0005e&qPB\u0007\u0011!\u0019\u0019b!\u000bA\u0002\rE\u0002\u0007BB\u001a\u0007o\u0001bAa\u0002\u0003\u000e\rU\u0002c\u0001\u0012\u00048\u0011Y1QDB\u0018\u0003\u0003\u0005\tQ!\u0001&\u0011!\t\u0019n!\u000bA\u0002\r\r\u0002\u0002CB\u001f\u0005\u007f\"\tea\u0010\u0002\r\u0015\fX/\u00197t)\ry7\u0011\t\u0005\b\u0007\u0007\u001aY\u00041\u0001*\u0003\u0011!\b.\u0019;\t\u0015\r\u001d#q\u0010b\u0001\n\u0003\u001aI%\u0001\u0005iCND7i\u001c3f+\u0005Y\u0005\u0002CB'\u0005\u007f\u0002\u000b\u0011B&\u0002\u0013!\f7\u000f[\"pI\u0016\u0004\u0003\u0006BB&\u0007#\u00022!DB*\u0013\r\u0019)\u0006\u0002\u0002\niJ\fgn]5f]RDqa!\u0017\n\t\u0003\u0019Y&A\u0005beJ\f\u0017\u0010V=qKV!1QLB5)\u0011\u0019yfa\u001b\u0011\u000b!\tie!\u0019\u0011\u000b5\u0019\u0019ga\u001a\n\u0007\r\u0015DAA\u0003BeJ\f\u0017\u0010E\u0002#\u0007S\"q!a+\u0004X\t\u0007Q\u0005\u0003\u0005\u0004n\r]\u0003\u0019AB8\u0003\r\t'o\u001a\u0019\u0005\u0007c\u001a)\bE\u0003\t\u0003\u001b\u001a\u0019\bE\u0002#\u0007k\"1ba\u001e\u0004l\u0005\u0005\t\u0011!B\u0001K\t!q\f\n\u001a1\u0011\u001d\u0019Y(\u0003C\u0001\u0007{\nA\"\u00192tiJ\f7\r\u001e+za\u0016,Baa \u0004\u0006RQ1\u0011QBD\u0007'\u001b9j!*\u0011\u000b!\tiea!\u0011\u0007\t\u001a)\tB\u0004\u0002,\u000ee$\u0019A\u0013\t\u0011\te3\u0011\u0010a\u0001\u0007\u0013\u0003Daa#\u0004\u0010B)\u0001\"!\u0014\u0004\u000eB\u0019!ea$\u0005\u0017\rE5qQA\u0001\u0002\u0003\u0015\t!\n\u0002\u0005?\u0012\u0012\u0014\u0007\u0003\u0005\u0004\u0016\u000ee\u0004\u0019AB\u0012\u0003\u0011q\u0017-\\3\t\u0011\re5\u0011\u0010a\u0001\u00077\u000b!\"\u001e9qKJ\u0014u.\u001e8ea\u0011\u0019ij!)\u0011\r\t\u001d!QBBP!\r\u00113\u0011\u0015\u0003\f\u0007G\u001b9*!A\u0001\u0002\u000b\u0005QE\u0001\u0003`II\u0012\u0004\u0002\u0003B\u001c\u0007s\u0002\raa*\u0011\u000b5\u0011Yd!+1\t\r-6q\u0016\t\u0006\u0011\u000553Q\u0016\t\u0004E\r=FaCBY\u0007K\u000b\t\u0011!A\u0003\u0002\u0015\u0012Aa\u0018\u00133g!91QW\u0005\u0005\u0002\r]\u0016\u0001D<jY\u0012\u001c\u0017M\u001d3UsB,W\u0003BB]\u0007\u007f#baa/\u0004B\u000e=\u0007#\u0002\u0005\u0002N\ru\u0006c\u0001\u0012\u0004@\u00129\u00111VBZ\u0005\u0004)\u0003\u0002CBb\u0007g\u0003\ra!2\u0002\u00151|w/\u001a:C_VtG\r\r\u0003\u0004H\u000e-\u0007#\u0002\u0005\u0002N\r%\u0007c\u0001\u0012\u0004L\u0012Y1QZBa\u0003\u0003\u0005\tQ!\u0001&\u0005\u0011yFE\r\u001b\t\u0011\re51\u0017a\u0001\u0007#\u0004Daa5\u0004XB)\u0001\"!\u0014\u0004VB\u0019!ea6\u0005\u0017\re7qZA\u0001\u0002\u0003\u0015\t!\n\u0002\u0005?\u0012\u0012T\u0007C\u0004\u0004^&!\taa8\u0002!%tG/\u001a:tK\u000e$\u0018n\u001c8UsB,W\u0003BBq\u0007O$Baa9\u0004jB)\u0001\"!\u0014\u0004fB\u0019!ea:\u0005\u000f\u0005-61\u001cb\u0001K!A11^Bn\u0001\u0004\u0019i/A\u0004qCJ,g\u000e^:\u0011\u000b5\u0011Yda<1\t\rE8Q\u001f\t\u0006\u0011\u0005531\u001f\t\u0004E\rUHaCB|\u0007S\f\t\u0011!A\u0003\u0002\u0015\u0012Aa\u0018\u00133m\u0001")
public final class ManifestFactory {
    public static <T> Manifest<T> intersectionType(Seq<Manifest<?>> seq) {
        return ManifestFactory$.MODULE$.intersectionType(seq);
    }

    public static <T> Manifest<T> wildcardType(Manifest<?> manifest, Manifest<?> manifest2) {
        return ManifestFactory$.MODULE$.wildcardType(manifest, manifest2);
    }

    public static <T> Manifest<T> abstractType(Manifest<?> manifest, String string2, Class<?> clazz, Seq<Manifest<?>> seq) {
        return ManifestFactory$.MODULE$.abstractType(manifest, string2, clazz, seq);
    }

    public static <T> Manifest<Object> arrayType(Manifest<?> manifest) {
        return ManifestFactory$.MODULE$.arrayType(manifest);
    }

    public static <T> Manifest<T> classType(Manifest<?> manifest, Class<?> clazz, Seq<Manifest<?>> seq) {
        return ManifestFactory$.MODULE$.classType(manifest, clazz, seq);
    }

    public static <T> Manifest<T> classType(Class<T> clazz, Manifest<?> manifest, Seq<Manifest<?>> seq) {
        return ManifestFactory$.MODULE$.classType(clazz, manifest, seq);
    }

    public static <T> Manifest<T> classType(Class<?> clazz) {
        return ManifestFactory$.MODULE$.classType(clazz);
    }

    public static <T> Manifest<T> singleType(Object object) {
        return ManifestFactory$.MODULE$.singleType(object);
    }

    public static Manifest<Nothing$> Nothing() {
        return ManifestFactory$.MODULE$.Nothing();
    }

    public static Manifest<Null$> Null() {
        return ManifestFactory$.MODULE$.Null();
    }

    public static Manifest<Object> AnyVal() {
        return ManifestFactory$.MODULE$.AnyVal();
    }

    public static Manifest<Object> AnyRef() {
        return ManifestFactory$.MODULE$.AnyRef();
    }

    public static Manifest<Object> Object() {
        return ManifestFactory$.MODULE$.Object();
    }

    public static Manifest<Object> Any() {
        return ManifestFactory$.MODULE$.Any();
    }

    public static AnyValManifest<BoxedUnit> Unit() {
        return ManifestFactory$.MODULE$.Unit();
    }

    public static AnyValManifest<Object> Boolean() {
        return ManifestFactory$.MODULE$.Boolean();
    }

    public static AnyValManifest<Object> Double() {
        return ManifestFactory$.MODULE$.Double();
    }

    public static AnyValManifest<Object> Float() {
        return ManifestFactory$.MODULE$.Float();
    }

    public static AnyValManifest<Object> Long() {
        return ManifestFactory$.MODULE$.Long();
    }

    public static AnyValManifest<Object> Int() {
        return ManifestFactory$.MODULE$.Int();
    }

    public static AnyValManifest<Object> Char() {
        return ManifestFactory$.MODULE$.Char();
    }

    public static AnyValManifest<Object> Short() {
        return ManifestFactory$.MODULE$.Short();
    }

    public static AnyValManifest<Object> Byte() {
        return ManifestFactory$.MODULE$.Byte();
    }

    public static List<AnyValManifest<?>> valueManifests() {
        return ManifestFactory$.MODULE$.valueManifests();
    }

    public static abstract class PhantomManifest<T>
    extends ClassTypeManifest<T> {
        private final String toString;
        private final transient int hashCode;

        @Override
        public String toString() {
            return this.toString;
        }

        @Override
        public boolean equals(Object that) {
            return this == that;
        }

        @Override
        public int hashCode() {
            return this.hashCode;
        }

        public PhantomManifest(Class<?> _runtimeClass, String toString2) {
            this.toString = toString2;
            super(None$.MODULE$, _runtimeClass, Nil$.MODULE$);
            this.hashCode = System.identityHashCode(this);
        }
    }

    public static class ClassTypeManifest<T>
    implements Manifest<T> {
        private final Option<Manifest<?>> prefix;
        private final Class<?> runtimeClass;
        private final List<Manifest<?>> typeArguments;

        @Override
        public Manifest<Object> arrayManifest() {
            return Manifest$class.arrayManifest(this);
        }

        @Override
        public boolean canEqual(Object that) {
            return Manifest$class.canEqual(this, that);
        }

        @Override
        public boolean equals(Object that) {
            return Manifest$class.equals(this, that);
        }

        @Override
        public int hashCode() {
            return Manifest$class.hashCode(this);
        }

        @Override
        public ClassTag<Object> wrap() {
            return ClassTag$class.wrap(this);
        }

        @Override
        public Object newArray(int len) {
            return ClassTag$class.newArray(this, len);
        }

        @Override
        public Option<T> unapply(Object x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(byte x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(short x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(char x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(int x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(long x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(float x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(double x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(boolean x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(BoxedUnit x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Class<?> erasure() {
            return ClassManifestDeprecatedApis$class.erasure(this);
        }

        @Override
        public boolean $less$colon$less(ClassTag<?> that) {
            return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
        }

        @Override
        public boolean $greater$colon$greater(ClassTag<?> that) {
            return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
        }

        @Override
        public <T> Class<Object> arrayClass(Class<?> tp) {
            return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
        }

        @Override
        public Object[] newArray2(int len) {
            return ClassManifestDeprecatedApis$class.newArray2(this, len);
        }

        @Override
        public Object[][] newArray3(int len) {
            return ClassManifestDeprecatedApis$class.newArray3(this, len);
        }

        @Override
        public Object[][][] newArray4(int len) {
            return ClassManifestDeprecatedApis$class.newArray4(this, len);
        }

        @Override
        public Object[][][][] newArray5(int len) {
            return ClassManifestDeprecatedApis$class.newArray5(this, len);
        }

        @Override
        public WrappedArray<T> newWrappedArray(int len) {
            return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
        }

        @Override
        public ArrayBuilder<T> newArrayBuilder() {
            return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
        }

        @Override
        public String argString() {
            return ClassManifestDeprecatedApis$class.argString(this);
        }

        @Override
        public Class<?> runtimeClass() {
            return this.runtimeClass;
        }

        @Override
        public List<Manifest<?>> typeArguments() {
            return this.typeArguments;
        }

        @Override
        public String toString() {
            return new StringBuilder().append((Object)(this.prefix.isEmpty() ? "" : new StringBuilder().append((Object)((ClassTag)this.prefix.get()).toString()).append((Object)"#").toString())).append((Object)(this.runtimeClass().isArray() ? "Array" : this.runtimeClass().getName())).append((Object)this.argString()).toString();
        }

        public ClassTypeManifest(Option<Manifest<?>> prefix, Class<?> runtimeClass, List<Manifest<?>> typeArguments) {
            this.prefix = prefix;
            this.runtimeClass = runtimeClass;
            this.typeArguments = typeArguments;
            ClassManifestDeprecatedApis$class.$init$(this);
            ClassTag$class.$init$(this);
            Manifest$class.$init$(this);
        }
    }

    public static class SingletonTypeManifest<T>
    implements Manifest<T> {
        private final Object value;
        private Class<?> runtimeClass;
        private String toString;
        private volatile byte bitmap$0;

        private Class runtimeClass$lzycompute() {
            SingletonTypeManifest singletonTypeManifest = this;
            synchronized (singletonTypeManifest) {
                if ((byte)(this.bitmap$0 & 1) == 0) {
                    this.runtimeClass = this.value.getClass();
                    this.bitmap$0 = (byte)(this.bitmap$0 | 1);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.runtimeClass;
            }
        }

        private String toString$lzycompute() {
            SingletonTypeManifest singletonTypeManifest = this;
            synchronized (singletonTypeManifest) {
                if ((byte)(this.bitmap$0 & 2) == 0) {
                    this.toString = new StringBuilder().append((Object)this.value.toString()).append((Object)".type").toString();
                    this.bitmap$0 = (byte)(this.bitmap$0 | 2);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.toString;
            }
        }

        @Override
        public List<Manifest<?>> typeArguments() {
            return Manifest$class.typeArguments(this);
        }

        @Override
        public Manifest<T[]> arrayManifest() {
            return Manifest$class.arrayManifest(this);
        }

        @Override
        public boolean canEqual(Object that) {
            return Manifest$class.canEqual(this, that);
        }

        @Override
        public boolean equals(Object that) {
            return Manifest$class.equals(this, that);
        }

        @Override
        public int hashCode() {
            return Manifest$class.hashCode(this);
        }

        @Override
        public ClassTag<T[]> wrap() {
            return ClassTag$class.wrap(this);
        }

        @Override
        public Object newArray(int len) {
            return ClassTag$class.newArray(this, len);
        }

        @Override
        public Option<T> unapply(Object x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(byte x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(short x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(char x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(int x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(long x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(float x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(double x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(boolean x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Option<T> unapply(BoxedUnit x) {
            return ClassTag$class.unapply((ClassTag)this, x);
        }

        @Override
        public Class<?> erasure() {
            return ClassManifestDeprecatedApis$class.erasure(this);
        }

        @Override
        public boolean $less$colon$less(ClassTag<?> that) {
            return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
        }

        @Override
        public boolean $greater$colon$greater(ClassTag<?> that) {
            return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
        }

        @Override
        public <T> Class<Object> arrayClass(Class<?> tp) {
            return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
        }

        @Override
        public Object[] newArray2(int len) {
            return ClassManifestDeprecatedApis$class.newArray2(this, len);
        }

        @Override
        public Object[][] newArray3(int len) {
            return ClassManifestDeprecatedApis$class.newArray3(this, len);
        }

        @Override
        public Object[][][] newArray4(int len) {
            return ClassManifestDeprecatedApis$class.newArray4(this, len);
        }

        @Override
        public Object[][][][] newArray5(int len) {
            return ClassManifestDeprecatedApis$class.newArray5(this, len);
        }

        @Override
        public WrappedArray<T> newWrappedArray(int len) {
            return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
        }

        @Override
        public ArrayBuilder<T> newArrayBuilder() {
            return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
        }

        @Override
        public String argString() {
            return ClassManifestDeprecatedApis$class.argString(this);
        }

        @Override
        public Class<?> runtimeClass() {
            return (byte)(this.bitmap$0 & 1) == 0 ? this.runtimeClass$lzycompute() : this.runtimeClass;
        }

        @Override
        public String toString() {
            return (byte)(this.bitmap$0 & 2) == 0 ? this.toString$lzycompute() : this.toString;
        }

        public SingletonTypeManifest(Object value) {
            this.value = value;
            ClassManifestDeprecatedApis$class.$init$(this);
            ClassTag$class.$init$(this);
            Manifest$class.$init$(this);
        }
    }
}

