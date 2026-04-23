/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import java.io.OutputStream;
import java.io.PrintWriter;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Product;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.Tuple5;
import scala.Tuple6;
import scala.collection.AbstractIterable;
import scala.collection.AbstractTraversable;
import scala.collection.GenTraversable;
import scala.collection.IterableLike;
import scala.collection.Iterator;
import scala.collection.LinearSeqOptimized;
import scala.collection.Seq;
import scala.collection.SeqLike;
import scala.collection.TraversableOnce;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.ArrayOps;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.Map;
import scala.collection.mutable.Map$;
import scala.collection.mutable.SortedSet;
import scala.collection.mutable.SortedSet$;
import scala.collection.mutable.Stack;
import scala.collection.mutable.Stack$;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WeakHashMap;
import scala.collection.mutable.WeakHashMap$;
import scala.compat.Platform$;
import scala.math.Ordering$Int$;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.Mirror;
import scala.reflect.api.Printers;
import scala.reflect.api.Printers$TreePrinter$class;
import scala.reflect.api.TypeTags;
import scala.reflect.internal.Chars$;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Flags$;
import scala.reflect.internal.Names;
import scala.reflect.internal.Printers$CodePrinter$;
import scala.reflect.internal.Printers$CodePrinter$$anonfun$scala$reflect$internal$Printers$CodePrinter$;
import scala.reflect.internal.Printers$CodePrinter$EmptyTypeTree$;
import scala.reflect.internal.Printers$ConsoleWriter$;
import scala.reflect.internal.Printers$RawTreePrinter$;
import scala.reflect.internal.Printers$TreePrinter$;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.Position;
import scala.reflect.package$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.TraitSetter;
import scala.util.Properties$;

@ScalaSignature(bytes="\u0006\u0001\u001dUh!C\u0001\u0003!\u0003\r\t!CDw\u0005!\u0001&/\u001b8uKJ\u001c(BA\u0002\u0005\u0003!Ig\u000e^3s]\u0006d'BA\u0003\u0007\u0003\u001d\u0011XM\u001a7fGRT\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001'\r\u0001!B\u0004\t\u0003\u00171i\u0011AB\u0005\u0003\u001b\u0019\u0011a!\u00118z%\u00164\u0007CA\b\u0013\u001b\u0005\u0001\"BA\t\u0005\u0003\r\t\u0007/[\u0005\u0003\u0003AAQ\u0001\u0006\u0001\u0005\u0002U\ta\u0001J5oSR$C#\u0001\f\u0011\u0005-9\u0012B\u0001\r\u0007\u0005\u0011)f.\u001b;\t\u000bi\u0001A\u0011A\u000e\u0002\u0015E,x\u000e^3e\u001d\u0006lW\rF\u0002\u001dG-\u0002\"!\b\u0011\u000f\u0005-q\u0012BA\u0010\u0007\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011E\t\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005}1\u0001\"\u0002\u0013\u001a\u0001\u0004)\u0013\u0001\u00028b[\u0016\u0004\"AJ\u0014\u000e\u0003\u0001I!\u0001K\u0015\u0003\t9\u000bW.Z\u0005\u0003U\t\u0011QAT1nKNDQ\u0001L\rA\u00025\na\u0001Z3d_\u0012,\u0007CA\u0006/\u0013\tycAA\u0004C_>dW-\u00198\t\u000bi\u0001A\u0011A\u0019\u0015\u0005q\u0011\u0004\"\u0002\u00131\u0001\u0004)\u0003\"\u0002\u000e\u0001\t\u0003!DC\u0001\u000f6\u0011\u0015!3\u00071\u0001\u001d\u0011\u00159\u0004\u0001\"\u00039\u0003=\u0019\u00180\u001c(b[\u0016Le\u000e^3s]\u0006dG\u0003\u0002\u000f:\u0001\u0006CQA\u000f\u001cA\u0002m\nA\u0001\u001e:fKB\u0011a\u0005P\u0005\u0003{y\u0012A\u0001\u0016:fK&\u0011qH\u0001\u0002\u0006)J,Wm\u001d\u0005\u0006IY\u0002\r!\n\u0005\u0006\u0005Z\u0002\r!L\u0001\bI\u0016\u001cw\u000eZ3e\u0011\u0015!\u0005\u0001\"\u0001F\u00039!WmY8eK\u0012\u001c\u00160\u001c(b[\u0016$2\u0001\b$H\u0011\u0015Q4\t1\u0001<\u0011\u0015!3\t1\u0001&\u0011\u0015I\u0005\u0001\"\u0001K\u0003\u001d\u0019\u00180\u001c(b[\u0016$2\u0001H&M\u0011\u0015Q\u0004\n1\u0001<\u0011\u0015!\u0003\n1\u0001&\u0011\u0015q\u0005\u0001\"\u0001P\u00039\u0011\u0017mY6rk>$X\r\u001a)bi\"$\"\u0001\b)\t\u000bEk\u0005\u0019A\u001e\u0002\u0003Q4Aa\u0015\u0001\u0001)\nYAK]3f!JLg\u000e^3s'\r\u0011&\"\u0016\t\u0003MYK!a\u0015\n\t\u0011a\u0013&\u0011!Q\u0001\ne\u000b1a\\;u!\tQv,D\u0001\\\u0015\taV,\u0001\u0002j_*\ta,\u0001\u0003kCZ\f\u0017B\u00011\\\u0005-\u0001&/\u001b8u/JLG/\u001a:\t\u000b\t\u0014F\u0011A2\u0002\rqJg.\u001b;?)\t!W\r\u0005\u0002'%\")\u0001,\u0019a\u00013\"9qM\u0015a\u0001\n#A\u0017\u0001D5oI\u0016tG/T1sO&tW#A5\u0011\u0005-Q\u0017BA6\u0007\u0005\rIe\u000e\u001e\u0005\b[J\u0003\r\u0011\"\u0005o\u0003AIg\u000eZ3oi6\u000b'oZ5o?\u0012*\u0017\u000f\u0006\u0002\u0017_\"9\u0001\u000f\\A\u0001\u0002\u0004I\u0017a\u0001=%c!1!O\u0015Q!\n%\fQ\"\u001b8eK:$X*\u0019:hS:\u0004\u0003b\u0002;S\u0005\u0004%\t\u0002[\u0001\u000bS:$WM\u001c;Ti\u0016\u0004\bB\u0002<SA\u0003%\u0011.A\u0006j]\u0012,g\u000e^*uKB\u0004\u0003b\u0002=S\u0001\u0004%\t\"_\u0001\rS:$WM\u001c;TiJLgnZ\u000b\u0002uB\u00111P`\u0007\u0002y*\u0011Q0X\u0001\u0005Y\u0006tw-\u0003\u0002\"y\"I\u0011\u0011\u0001*A\u0002\u0013E\u00111A\u0001\u0011S:$WM\u001c;TiJLgnZ0%KF$2AFA\u0003\u0011\u001d\u0001x0!AA\u0002iDq!!\u0003SA\u0003&!0A\u0007j]\u0012,g\u000e^*ue&tw\r\t\u0005\u0007\u0003\u001b\u0011F\u0011A\u000b\u0002\r%tG-\u001a8u\u0011\u0019\t\tB\u0015C\u0001+\u00051QO\u001c3f]RDq!!\u0006S\t\u0003\t9\"A\u0007qe&tG\u000fU8tSRLwN\u001c\u000b\u0004-\u0005e\u0001B\u0002\u001e\u0002\u0014\u0001\u00071\bC\u0004\u0002\u001eI#\t\"a\b\u0002\u001dA\u0014\u0018N\u001c;UsB,7/\u00138g_R\u0019a#!\t\t\ri\nY\u00021\u0001<\u0011\u0019\t)C\u0015C\u0001+\u00059\u0001O]5oi2t\u0007bBA\u0015%\u0012\u0005\u00111F\u0001\taJLg\u000e^*fcV!\u0011QFA&)\u0011\ty#!\u0018\u0015\t\u0005E\u0012Q\b\u000b\u0004-\u0005M\u0002\"CA\u001b\u0003O!\t\u0019AA\u001c\u0003!\u0001(/\u001b8ug\u0016\u0004\b\u0003B\u0006\u0002:YI1!a\u000f\u0007\u0005!a$-\u001f8b[\u0016t\u0004\u0002CA \u0003O\u0001\r!!\u0011\u0002\u0013A\u0014\u0018N\u001c;fY\u0016l\u0007CB\u0006\u0002D\u0005\u001dc#C\u0002\u0002F\u0019\u0011\u0011BR;oGRLwN\\\u0019\u0011\t\u0005%\u00131\n\u0007\u0001\t!\ti%a\nC\u0002\u0005=#!A1\u0012\t\u0005E\u0013q\u000b\t\u0004\u0017\u0005M\u0013bAA+\r\t9aj\u001c;iS:<\u0007cA\u0006\u0002Z%\u0019\u00111\f\u0004\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002`\u0005\u001d\u0002\u0019AA1\u0003\ta7\u000f\u0005\u0004\u0002d\u0005%\u0014q\t\b\u0004\u0017\u0005\u0015\u0014bAA4\r\u00059\u0001/Y2lC\u001e,\u0017\u0002BA6\u0003[\u0012A\u0001T5ti*\u0019\u0011q\r\u0004\t\u000f\u0005E$\u000b\"\u0001\u0002t\u0005Y\u0001O]5oi\u000e{G.^7o)%1\u0012QOA>\u0003\u007f\n\u0019\t\u0003\u0005\u0002x\u0005=\u0004\u0019AA=\u0003\t!8\u000fE\u0003\u0002d\u0005%4\bC\u0004\u0002~\u0005=\u0004\u0019\u0001\u000f\u0002\u000bM$\u0018M\u001d;\t\u000f\u0005\u0005\u0015q\u000ea\u00019\u0005\u00191/\u001a9\t\u000f\u0005\u0015\u0015q\u000ea\u00019\u0005\u0019QM\u001c3\t\u000f\u0005%%\u000b\"\u0001\u0002\f\u0006A\u0001O]5oiJ{w\u000fF\u0005\u0017\u0003\u001b\u000by)!%\u0002\u0014\"A\u0011qOAD\u0001\u0004\tI\bC\u0004\u0002~\u0005\u001d\u0005\u0019\u0001\u000f\t\u000f\u0005\u0005\u0015q\u0011a\u00019!9\u0011QQAD\u0001\u0004a\u0002bBAE%\u0012\u0005\u0011q\u0013\u000b\u0006-\u0005e\u00151\u0014\u0005\t\u0003o\n)\n1\u0001\u0002z!9\u0011\u0011QAK\u0001\u0004a\u0002bBAP%\u0012\u0005\u0011\u0011U\u0001\u0010aJLg\u000e\u001e+za\u0016\u0004\u0016M]1ngR\u0019a#a)\t\u0011\u0005]\u0014Q\u0014a\u0001\u0003K\u0003b!a\u0019\u0002j\u0005\u001d\u0006c\u0001\u0014\u0002*&\u0019\u00111\u0016 \u0003\u000fQK\b/\u001a#fM\"9\u0011q\u0016*\u0005\u0002\u0005E\u0016\u0001\u00059sS:$H*\u00192fYB\u000b'/Y7t)\r1\u00121\u0017\u0005\t\u0003k\u000bi\u000b1\u0001\u00028\u0006\u0011\u0001o\u001d\t\u0007\u0003G\nI'!/\u0011\u0007\u0019\nY,C\u0002\u0002>z\u0012Q!\u00133f]RDq!!1S\t\u0003\t\u0019-A\bqe&tG\u000fT1cK2\u0004\u0016M]1n)\r1\u0012Q\u0019\u0005\t\u0003\u000f\fy\f1\u0001\u0002:\u0006\t\u0001\u000fC\u0004\u0002LJ#\t\"!4\u0002\u0019A\f'/\u001a8uQ\u0016\u001c\u0018N_3\u0015\u0011\u0005=\u0017Q[Am\u0003;$2AFAi\u0011%\t\u0019.!3\u0005\u0002\u0004\t9$\u0001\u0003c_\u0012L\b\"CAl\u0003\u0013\u0004\n\u00111\u0001.\u0003%\u0019wN\u001c3ji&|g\u000eC\u0005\u0002\\\u0006%\u0007\u0013!a\u00019\u0005!q\u000e]3o\u0011%\ty.!3\u0011\u0002\u0003\u0007A$A\u0003dY>\u001cX\rC\u0005\u0002dJ\u0013\r\u0011\"\u0005\u0002f\u0006\u00012m\\7nK:$8OU3rk&\u0014X\rZ\u000b\u0002[!9\u0011\u0011\u001e*!\u0002\u0013i\u0013!E2p[6,g\u000e^:SKF,\u0018N]3eA!9\u0011Q\u001e*\u0005\u0012\u0005=\u0018aB2p[6,g\u000e\u001e\u000b\u0004-\u0005E\b\"CAj\u0003W$\t\u0019AA\u001c\u0011\u001d\t)P\u0015C\t\u0003o\f\u0011\u0004\u001d:j]RLU\u000e\u001d7jG&$\u0018J\u001c)be\u0006l7\u000fT5tiR\u0019a#!?\t\u0011\u0005m\u00181\u001fa\u0001\u0003{\f1A\u001e3t!\u0019\t\u0019'!\u001b\u0002\u0000B\u0019aE!\u0001\n\u0007\t\raH\u0001\u0004WC2$UM\u001a\u0005\b\u0005\u000f\u0011F\u0011\u0001B\u0005\u0003A\u0001(/\u001b8u-\u0006dW/\u001a)be\u0006l7\u000fF\u0003\u0017\u0005\u0017\u0011i\u0001\u0003\u0005\u0002x\t\u0015\u0001\u0019AA\u007f\u0011%\u0011yA!\u0002\u0011\u0002\u0003\u0007Q&A\u0007j]B\u000b'/\u001a8uQ\u0016\u001cXm\u001d\u0005\b\u0005'\u0011F\u0011\u0001B\u000b\u0003)\u0001(/\u001b8u!\u0006\u0014\u0018-\u001c\u000b\u0004-\t]\u0001B\u0002\u001e\u0003\u0012\u0001\u00071\bC\u0004\u0003\u001cI#\tA!\b\u0002\u0015A\u0014\u0018N\u001c;CY>\u001c7\u000eF\u0002\u0017\u0005?AaA\u000fB\r\u0001\u0004Y\u0004b\u0002B\u0012%\u0012%!QE\u0001\u0006gflgI\\\u000b\u0005\u0005O\u0011Y\u0003\u0006\u0005\u0003*\t=\"\u0011\u0007B!!\u0011\tIEa\u000b\u0005\u0011\t5\"\u0011\u0005b\u0001\u0003\u001f\u0012\u0011\u0001\u0016\u0005\u0007u\t\u0005\u0002\u0019A\u001e\t\u0011\tM\"\u0011\u0005a\u0001\u0005k\t\u0011A\u001a\t\b\u0017\u0005\r#q\u0007B\u0015!\r1#\u0011H\u0005\u0005\u0005w\u0011iD\u0001\u0004Ts6\u0014w\u000e\\\u0005\u0004\u0005\u007f\u0011!aB*z[\n|Gn\u001d\u0005\n\u0005\u0007\u0012\t\u0003\"a\u0001\u0005\u000b\naa\u001c:FYN,\u0007#B\u0006\u0002:\t%\u0002b\u0002B%%\u0012%!1J\u0001\u0006S\u001a\u001c\u00160\u001c\u000b\u0006[\t5#q\n\u0005\u0007u\t\u001d\u0003\u0019A\u001e\t\u0011\u0005\u001d'q\ta\u0001\u0005#\u0002baCA\"\u0005oi\u0003b\u0002B+%\u0012\u0005!qK\u0001\taJLg\u000e^(qiR)aC!\u0017\u0003^!9!1\fB*\u0001\u0004a\u0012A\u00029sK\u001aL\u0007\u0010\u0003\u0004;\u0005'\u0002\ra\u000f\u0005\b\u0005C\u0012F\u0011\u0001B2\u00039\u0001(/\u001b8u\u001b>$\u0017NZ5feN$RA\u0006B3\u0005OBaA\u000fB0\u0001\u0004Y\u0004\u0002\u0003B5\u0005?\u0002\rAa\u001b\u0002\t5|Gm\u001d\t\u0004M\t5\u0014b\u0001B8}\tIQj\u001c3jM&,'o\u001d\u0005\b\u0005g\u0012F\u0011\u0001B;\u0003)\u0001(/\u001b8u\r2\fwm\u001d\u000b\u0006-\t]$\u0011\u0011\u0005\t\u0005s\u0012\t\b1\u0001\u0003|\u0005)a\r\\1hgB\u00191B! \n\u0007\t}dA\u0001\u0003M_:<\u0007b\u0002BB\u0005c\u0002\r\u0001H\u0001\u000eaJLg/\u0019;f/&$\b.\u001b8\t\u000f\t\u001d%\u000b\"\u0001\u0003\n\u0006\u0001\u0002O]5oi\u0006sgn\u001c;bi&|gn\u001d\u000b\u0004-\t-\u0005b\u0002\u001e\u0003\u0006\u0002\u0007!Q\u0012\t\u0004M\t=\u0015b\u0001BI}\tIQ*Z7cKJ$UM\u001a\u0005\n\u0005+\u0013\u0006\u0019!C\u0005\u0005/\u000bAbY;se\u0016tGoT<oKJ,\"Aa\u000e\t\u0013\tm%\u000b1A\u0005\n\tu\u0015\u0001E2veJ,g\u000e^(x]\u0016\u0014x\fJ3r)\r1\"q\u0014\u0005\na\ne\u0015\u0011!a\u0001\u0005oA\u0001Ba)SA\u0003&!qG\u0001\u000eGV\u0014(/\u001a8u\u001f^tWM\u001d\u0011\t\u0013\t\u001d&\u000b1A\u0005\n\t%\u0016\u0001D:fY\u0016\u001cGo\u001c:UsB,WC\u0001BV!\r1#QV\u0005\u0005\u0005_\u0013\tL\u0001\u0003UsB,\u0017b\u0001BZ\u0005\t)A+\u001f9fg\"I!q\u0017*A\u0002\u0013%!\u0011X\u0001\u0011g\u0016dWm\u0019;peRK\b/Z0%KF$2A\u0006B^\u0011%\u0001(QWA\u0001\u0002\u0004\u0011Y\u000b\u0003\u0005\u0003@J\u0003\u000b\u0015\u0002BV\u00035\u0019X\r\\3di>\u0014H+\u001f9fA!9!1\u0019*\u0005\u0012\t\u0015\u0017a\u00049sS:$\b+Y2lC\u001e,G)\u001a4\u0015\u000bY\u00119Ma4\t\u000fi\u0012\t\r1\u0001\u0003JB\u0019aEa3\n\u0007\t5gH\u0001\u0006QC\u000e\\\u0017mZ3EK\u001aDqA!5\u0003B\u0002\u0007A$A\u0005tKB\f'/\u0019;pe\"9!Q\u001b*\u0005\u0012\t]\u0017a\u00039sS:$h+\u00197EK\u001a$bA!7\u0003f\n\u001dH\u0003\u0002Bn\u0005C$2A\u0006Bo\u0011%\u0011yNa5\u0005\u0002\u0004\t9$\u0001\u0005qe&tGO\u00155t\u0011%\u0011\u0019Oa5\u0005\u0002\u0004\t9$\u0001\nqe&tG\u000fV=qKNKwM\\1ukJ,\u0007b\u0002\u001e\u0003T\u0002\u0007\u0011q \u0005\n\u0005S\u0014\u0019\u000e\"a\u0001\u0005W\f!B]3tk2$h*Y7f!\u0011Y\u0011\u0011\b\u000f\t\u000f\t=(\u000b\"\u0005\u0003r\u0006Y\u0001O]5oi\u0012+g\rR3g)\u0019\u0011\u0019Pa?\u0004\u0004Q!!Q\u001fB})\r1\"q\u001f\u0005\n\u0005?\u0014i\u000f\"a\u0001\u0003oA\u0011Ba9\u0003n\u0012\u0005\r!a\u000e\t\u000fi\u0012i\u000f1\u0001\u0003~B\u0019aEa@\n\u0007\r\u0005aH\u0001\u0004EK\u001a$UM\u001a\u0005\n\u0005S\u0014i\u000f\"a\u0001\u0005WDqaa\u0002S\t#\u0019I!\u0001\u0007qe&tG\u000fV=qK\u0012+g\rF\u0003\u0017\u0007\u0017\u0019i\u0001C\u0004;\u0007\u000b\u0001\r!a*\t\u0013\t%8Q\u0001CA\u0002\t-\bbBB\t%\u0012E11C\u0001\faJLg\u000e^%na>\u0014H\u000fF\u0003\u0017\u0007+\u0019i\u0002C\u0004;\u0007\u001f\u0001\raa\u0006\u0011\u0007\u0019\u001aI\"C\u0002\u0004\u001cy\u0012a!S7q_J$\b\"CB\u0010\u0007\u001f!\t\u0019\u0001Bv\u0003%\u0011Xm]*fY\u0016\u001cG\u000fC\u0004\u0004$I#\tb!\n\u0002\u0019A\u0014\u0018N\u001c;DCN,G)\u001a4\u0015\u0007Y\u00199\u0003C\u0004;\u0007C\u0001\ra!\u000b\u0011\u0007\u0019\u001aY#C\u0002\u0004.y\u0012qaQ1tK\u0012+g\rC\u0004\u00042I#\tba\r\u0002\u001bA\u0014\u0018N\u001c;Gk:\u001cG/[8o)\u0011\u0019)d!\u000f\u0015\u0007Y\u00199\u0004C\u0005\u0003\b\r=B\u00111\u0001\u00028!9!ha\fA\u0002\rm\u0002c\u0001\u0014\u0004>%\u00191q\b \u0003\u0011\u0019+hn\u0019;j_:Dqaa\u0011S\t#\u0019)%\u0001\u0006qe&tGoU;qKJ$rAFB$\u0007\u001f\u001a\t\u0006C\u0004;\u0007\u0003\u0002\ra!\u0013\u0011\u0007\u0019\u001aY%C\u0002\u0004Ny\u0012QaU;qKJD\u0011B!;\u0004B\u0011\u0005\rAa;\t\u0013\rM3\u0011\tI\u0001\u0002\u0004i\u0013aC2iK\u000e\\7+_7c_2Dqaa\u0016S\t#\u0019I&A\u0005qe&tG\u000f\u00165jgR)aca\u0017\u0004d!9!h!\u0016A\u0002\ru\u0003c\u0001\u0014\u0004`%\u00191\u0011\r \u0003\tQC\u0017n\u001d\u0005\n\u0005S\u001c)\u0006\"a\u0001\u0005WDqAa\u0007S\t#\u00199\u0007F\u0003\u0017\u0007S\u001ai\u0007\u0003\u0005\u0004l\r\u0015\u0004\u0019AA=\u0003\u0015\u0019H/\u0019;t\u0011\u001d\u0019yg!\u001aA\u0002m\nA!\u001a=qe\"911\u000f*\u0005\u0002\rU\u0014!\u00039sS:$HK]3f)\r12q\u000f\u0005\u0007u\rE\u0004\u0019A\u001e\t\u000f\rm$\u000b\"\u0001\u0004~\u0005)\u0001O]5oiR\u0019aca \t\u0011\r\u00055\u0011\u0010a\u0001\u0007\u0007\u000bA!\u0019:hgB)1b!\"\u0002X%\u00191q\u0011\u0004\u0003\u0015q\u0012X\r]3bi\u0016$g\bC\u0005\u0004\fJ\u000b\n\u0011\"\u0005\u0004\u000e\u00061\u0002/\u0019:f]RDWm]5{K\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0004\u0010*\u001aQf!%,\u0005\rM\u0005\u0003BBK\u0007?k!aa&\u000b\t\re51T\u0001\nk:\u001c\u0007.Z2lK\u0012T1a!(\u0007\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0007C\u001b9JA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016D\u0011b!*S#\u0003%\tba*\u0002-A\f'/\u001a8uQ\u0016\u001c\u0018N_3%I\u00164\u0017-\u001e7uII*\"a!++\u0007q\u0019\t\nC\u0005\u0004.J\u000b\n\u0011\"\u0005\u0004(\u00061\u0002/\u0019:f]RDWm]5{K\u0012\"WMZ1vYR$3\u0007C\u0005\u00042J\u000b\n\u0011\"\u0001\u0004\u000e\u0006Q\u0002O]5oiZ\u000bG.^3QCJ\fWn\u001d\u0013eK\u001a\fW\u000f\u001c;%e!I1Q\u0017*\u0012\u0002\u0013E1QR\u0001\u0015aJLg\u000e^*va\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0007\r\re\u0006\u0001AB^\u0005-\u0019u\u000eZ3Qe&tG/\u001a:\u0014\u0007\r]F\rC\u0005Y\u0007o\u0013\t\u0011)A\u00053\"Q1\u0011YB\\\u0005\u0003\u0005\u000b\u0011B\u0017\u0002\u0019A\u0014\u0018N\u001c;S_>$\bk[4\t\u000f\t\u001c9\f\"\u0001\u0004FR11qYBe\u0007\u0017\u00042AJB\\\u0011\u0019A61\u0019a\u00013\"91\u0011YBb\u0001\u0004i\u0003BCBh\u0007o\u0013\r\u0011\"\u0005\u0004R\u0006a\u0001/\u0019:f]R\u001c8\u000b^1dWV\u001111\u001b\t\u0006\u0007+\u001cynO\u0007\u0003\u0007/TAa!7\u0004\\\u00069Q.\u001e;bE2,'bABo\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\r\u00058q\u001b\u0002\u0006'R\f7m\u001b\u0005\n\u0007K\u001c9\f)A\u0005\u0007'\fQ\u0002]1sK:$8o\u0015;bG.\u0004\u0003\u0002CBu\u0007o#\tba;\u0002\u0017\r,(O]3oiR\u0013X-Z\u000b\u0003\u0007[\u0004BaCBxw%\u00191\u0011\u001f\u0004\u0003\r=\u0003H/[8o\u0011!\u0019)pa.\u0005\u0012\r-\u0018!D2veJ,g\u000e\u001e)be\u0016tG\u000f\u0003\u0005\u0004z\u000e]F\u0011CB~\u0003-\u0001(/\u001b8uK\u0012t\u0015-\\3\u0015\u000bi\u001cipa@\t\r\u0011\u001a9\u00101\u0001&\u0011!\u00115q\u001fI\u0001\u0002\u0004i\u0003\u0002\u0003C\u0002\u0007o#\t\u0002\"\u0002\u0002+%\u001c\u0018J\u001c;MSR<\u0016\u000e\u001e5EK\u000e|G-\u001a3PaR)Q\u0006b\u0002\u0005\f!9A\u0011\u0002C\u0001\u0001\u0004Y\u0014\u0001B9vC2Da\u0001\nC\u0001\u0001\u0004)\u0003BCAr\u0007o\u0013\r\u0011\"\u0015\u0002f\"A\u0011\u0011^B\\A\u0003%Q\u0006\u0003\u0005\u0005\u0014\r]F\u0011\u0003C\u000b\u0003AqW-\u001a3t!\u0006\u0014XM\u001c;iKN,7\u000f\u0006\u0003\u0005\u0018\u0011UBcD\u0017\u0005\u001a\u0011uA\u0011\u0005C\u0013\tS!i\u0003\"\r\t\u0013\u0011mA\u0011\u0003I\u0001\u0002\u0004i\u0013\u0001C5og&$W-\u00134\t\u0013\u0011}A\u0011\u0003I\u0001\u0002\u0004i\u0013aC5og&$W-T1uG\"D\u0011\u0002b\t\u0005\u0012A\u0005\t\u0019A\u0017\u0002\u0013%t7/\u001b3f)JL\b\"\u0003C\u0014\t#\u0001\n\u00111\u0001.\u0003=Ign]5eK\u0006sgn\u001c;bi\u0016$\u0007\"\u0003C\u0016\t#\u0001\n\u00111\u0001.\u0003-Ign]5eK\ncwnY6\t\u0013\u0011=B\u0011\u0003I\u0001\u0002\u0004i\u0013AD5og&$W\rT1cK2$UM\u001a\u0005\n\tg!\t\u0002%AA\u00025\nA\"\u001b8tS\u0012,\u0017i]:jO:Dq\u0001b\u000e\u0005\u0012\u0001\u00071(\u0001\u0004qCJ,g\u000e\u001e\u0005\t\tw\u00199\f\"\u0005\u0005>\u0005i1\r[3dW\u001a{'O\u00117b].$2A\u001fC \u0011\u001d!\t\u0005\"\u000fA\u00025\nAaY8oI\"AAQIB\\\t#!9%\u0001\u000bcY\u0006t7NR8s\u001fB,'/\u0019;pe:\u000bW.\u001a\u000b\u0004u\u0012%\u0003B\u0002\u0013\u0005D\u0001\u0007Q\u0005\u0003\u0005\u0005N\r]F\u0011\u0003C(\u00031\u0011G.\u00198l\r>\u0014h*Y7f)\rQH\u0011\u000b\u0005\u0007I\u0011-\u0003\u0019A\u0013\t\u0011\u0011U3q\u0017C\t\t/\nQB]3t_24XmU3mK\u000e$Hc\u0001\u000f\u0005Z!1\u0011\u000bb\u0015A\u0002m:\u0001\u0002\"\u0018\u00048\"\u0005AqL\u0001\u000e\u000b6\u0004H/\u001f+za\u0016$&/Z3\u0011\t\u0011\u0005D1M\u0007\u0003\u0007o3\u0001\u0002\"\u001a\u00048\"\u0005Aq\r\u0002\u000e\u000b6\u0004H/\u001f+za\u0016$&/Z3\u0014\u0007\u0011\r$\u0002C\u0004c\tG\"\t\u0001b\u001b\u0015\u0005\u0011}\u0003\u0002\u0003C8\tG\"\t\u0001\"\u001d\u0002\u000fUt\u0017\r\u001d9msR\u0019Q\u0006b\u001d\t\u0011\u0011UDQ\u000ea\u0001\to\n!\u0001\u001e;\u0011\u0007\u0019\"I(C\u0002\u0005|y\u0012\u0001\u0002V=qKR\u0013X-\u001a\u0005\t\t\u007f\u001a9\f\"\u0005\u0005\u0002\u0006Y\u0011n]#naRLHK]3f)\riC1\u0011\u0005\u0007u\u0011u\u0004\u0019A\u001e\t\u0011\u0011\u001d5q\u0017C\t\t\u0013\u000b\u0011c\u001c:jO&t\u0017\r\u001c+za\u0016$&/Z3t)\u0011!Y\t\"&\u0011\u000b\u00115E1S\u001e\u000e\u0005\u0011=%\u0002\u0002CI\u00077\f\u0011\"[7nkR\f'\r\\3\n\t\u0005-Dq\u0012\u0005\t\t/#)\t1\u0001\u0002z\u0005)AO]3fg\"QA1TB\\\u0005\u0004%\t\u0001\"(\u0002\u001d\u0011,g-Y;mi\u000ec\u0017m]:fgV\u0011Aq\u0014\t\u0007\t\u001b#\u0019\n\")\u0011\u0007\u0019\"\u0019+C\u0002\u0005&&\u0012\u0001\u0002V=qK:\u000bW.\u001a\u0005\n\tS\u001b9\f)A\u0005\t?\u000bq\u0002Z3gCVdGo\u00117bgN,7\u000f\t\u0005\u000b\t[\u001b9L1A\u0005\u0002\u0011u\u0015\u0001\u00063fM\u0006,H\u000e\u001e+sC&$8OR8s\u0007\u0006\u001cX\rC\u0005\u00052\u000e]\u0006\u0015!\u0003\u0005 \u0006)B-\u001a4bk2$HK]1jiN4uN]\"bg\u0016\u0004\u0003\u0002\u0003C[\u0007o#\t\u0002b.\u00025I,Wn\u001c<f\t\u00164\u0017-\u001e7u)f\u0004Xm\u001d$s_6d\u0015n\u001d;\u0015\t\u0011eFq\u0019\u000b\u0005\tw#\u0019\r\u0006\u0003\u0002z\u0011u\u0006\u0002\u0003C`\tg\u0003\r\u0001\"1\u0002\u001dQ\u0014\u0018-\u001b;t)>\u0014V-\\8wKB)\u00111MA5K!QAQ\u0019CZ!\u0003\u0005\r\u0001\"1\u0002\u001f\rd\u0017m]:fgR{'+Z7pm\u0016D\u0001\u0002b&\u00054\u0002\u0007\u0011\u0011\u0010\u0005\t\t\u0017\u001c9\f\"\u0005\u0005N\u0006a\"/Z7pm\u0016$UMZ1vYR\u001cE.Y:tKN4%o\\7MSN$HC\u0002CF\t\u001f$\t\u000e\u0003\u0005\u0005\u0018\u0012%\u0007\u0019AA=\u0011)!)\r\"3\u0011\u0002\u0003\u0007A\u0011\u0019\u0005\t\t+\u001c9\f\"\u0005\u0005X\u0006\t2/\u001f8uQ\u0016$\u0018n\u0019+p%\u0016lwN^3\u0015\u00075\"I\u000e\u0003\u0004;\t'\u0004\ra\u000f\u0005\t\u0005+\u001a9\f\"\u0011\u0005^R)a\u0003b8\u0005b\"9!1\fCn\u0001\u0004a\u0002B\u0002\u001e\u0005\\\u0002\u00071\b\u0003\u0005\u0002r\r]F\u0011\tCs)%1Bq\u001dCu\tW$i\u000f\u0003\u0005\u0002x\u0011\r\b\u0019AA=\u0011\u001d\ti\bb9A\u0002qAq!!!\u0005d\u0002\u0007A\u0004C\u0004\u0002\u0006\u0012\r\b\u0019\u0001\u000f\t\u0011\tM4q\u0017C\u0001\tc$RA\u0006Cz\tkD\u0001B!\u001b\u0005p\u0002\u0007!1\u000e\u0005\n\to$y\u000f%AA\u00025\n\u0001\u0003\u001d:j[\u0006\u0014\u0018p\u0011;peB\u000b'/Y7\t\u0011\t\u00054q\u0017C!\tw$RA\u0006C\u007f\t\u007fDaA\u000fC}\u0001\u0004Y\u0004\u0002\u0003B5\ts\u0004\rAa\u001b\t\u0011\t\u00054q\u0017C\u0001\u000b\u0007!RAFC\u0003\u000b\u000fA\u0001B!\u001b\u0006\u0002\u0001\u0007!1\u000e\u0005\b\to,\t\u00011\u0001.\u0011!\u0011\u0019ba.\u0005\u0002\u0015-A#\u0002\f\u0006\u000e\u0015=\u0001B\u0002\u001e\u0006\n\u0001\u00071\bC\u0004\u0005x\u0016%\u0001\u0019A\u0017\t\u0011\tM1q\u0017C!\u000b'!2AFC\u000b\u0011\u0019QT\u0011\u0003a\u0001w!AQ\u0011DB\\\t#)Y\"\u0001\u0006qe&tG/\u0011:hgN$2AFC\u000f\u0011!)y\"b\u0006A\u0002\u0015\u0005\u0012!B1sON\u001c\bCBA2\u0003S\nI\b\u0003\u0005\u0003\b\u000e]F\u0011IC\u0013)\r1Rq\u0005\u0005\bu\u0015\r\u0002\u0019\u0001BG\u0011!)Yca.\u0005\u0012\u00155\u0012A\u00039sS:$\u0018I\u001c8piR\u0019a#b\f\t\ri*I\u00031\u0001<\u0011!\u0019\u0019ha.\u0005B\u0015MBc\u0001\f\u00066!1!(\"\rA\u0002mB\u0001\"\"\u000f\u00048\u0012\u0005Q1H\u0001\u0014aJ|7-Z:t)J,W\r\u0015:j]RLgn\u001a\u000b\u0004-\u0015u\u0002B\u0002\u001e\u00068\u0001\u00071\b\u0003\u0006\u0006B\r]\u0016\u0013!C\t\u0007\u001b\u000bQ\u0003\u001d:j]R,GMT1nK\u0012\"WMZ1vYR$#\u0007\u0003\u0006\u0006F\r]\u0016\u0013!C\t\u000b\u000f\n!D\\3fIN\u0004\u0016M]3oi\",7/Z:%I\u00164\u0017-\u001e7uII\"Baa$\u0006J!9AqGC\"\u0001\u0004Y\u0004BCC'\u0007o\u000b\n\u0011\"\u0005\u0006P\u0005Qb.Z3egB\u000b'/\u001a8uQ\u0016\u001cXm\u001d\u0013eK\u001a\fW\u000f\u001c;%gQ!1qRC)\u0011\u001d!9$b\u0013A\u0002mB!\"\"\u0016\u00048F\u0005I\u0011CC,\u0003iqW-\u001a3t!\u0006\u0014XM\u001c;iKN,7\u000f\n3fM\u0006,H\u000e\u001e\u00135)\u0011\u0019y)\"\u0017\t\u000f\u0011]R1\u000ba\u0001w!QQQLB\\#\u0003%\t\"b\u0018\u000259,W\rZ:QCJ,g\u000e\u001e5fg\u0016\u001cH\u0005Z3gCVdG\u000fJ\u001b\u0015\t\r=U\u0011\r\u0005\b\to)Y\u00061\u0001<\u0011)))ga.\u0012\u0002\u0013EQqM\u0001\u001b]\u0016,Gm\u001d)be\u0016tG\u000f[3tKN$C-\u001a4bk2$HE\u000e\u000b\u0005\u0007\u001f+I\u0007C\u0004\u00058\u0015\r\u0004\u0019A\u001e\t\u0015\u001554qWI\u0001\n#)y'\u0001\u000eoK\u0016$7\u000fU1sK:$\b.Z:fg\u0012\"WMZ1vYR$s\u0007\u0006\u0003\u0004\u0010\u0016E\u0004b\u0002C\u001c\u000bW\u0002\ra\u000f\u0005\u000b\u000bk\u001a9,%A\u0005\u0012\u0015]\u0014A\u00078fK\u0012\u001c\b+\u0019:f]RDWm]3tI\u0011,g-Y;mi\u0012BD\u0003BBH\u000bsBq\u0001b\u000e\u0006t\u0001\u00071\b\u0003\u0006\u0006~\r]\u0016\u0013!C\t\u000b\u007f\nAE]3n_Z,G)\u001a4bk2$H+\u001f9fg\u001a\u0013x.\u001c'jgR$C-\u001a4bk2$HE\r\u000b\u0005\u000b\u0003+\u0019I\u000b\u0003\u0005B\u000eE\u0005\u0002\u0003CL\u000bw\u0002\r!!\u001f\t\u0015\u0015\u001d5qWI\u0001\n#)I)\u0001\u0014sK6|g/\u001a#fM\u0006,H\u000e^\"mCN\u001cXm\u001d$s_6d\u0015n\u001d;%I\u00164\u0017-\u001e7uII*\"!\"!\t\u0015\u001555qWI\u0001\n\u0003\u0019i)\u0001\u000bqe&tGO\u00127bON$C-\u001a4bk2$HE\r\u0005\b\u000b#\u0003A\u0011ACJ\u0003)A\bO]5oiR\u0013X-\u001a\u000b\u0006-\u0015UU\u0011\u0014\u0005\b\u000b/+y\t1\u0001e\u0003-!(/Z3Qe&tG/\u001a:\t\ri*y\t1\u0001<\u0011\u001d)i\n\u0001C\u0001\u000b?\u000baB\\3x\u0007>$W\r\u0015:j]R,'\u000fF\u0004e\u000bC+)+b*\t\u000f\u0015\rV1\u0014a\u00013\u00061qO]5uKJDaAOCN\u0001\u0004Y\u0004bBBa\u000b7\u0003\r!\f\u0005\b\u000bW\u0003A\u0011ACW\u00039qWm\u001e+sK\u0016\u0004&/\u001b8uKJ$2\u0001ZCX\u0011\u001d)\u0019+\"+A\u0002eCq!b+\u0001\t\u0003)\u0019\fF\u0002e\u000bkC\u0001\"b.\u00062\u0002\u0007Q\u0011X\u0001\u0007gR\u0014X-Y7\u0011\u0007i+Y,C\u0002\u0006>n\u0013AbT;uaV$8\u000b\u001e:fC6Dq!b+\u0001\t\u0003)\t\rF\u0001e\u000f\u001d))\r\u0001E\u0001\u000b\u000f\fQbQ8og>dWm\u0016:ji\u0016\u0014\bc\u0001\u0014\u0006J\u001a9Q1\u001a\u0001\t\u0002\u00155'!D\"p]N|G.Z,sSR,'o\u0005\u0003\u0006J\u0016=\u0007c\u0001.\u0006R&\u0019Q1[.\u0003\r]\u0013\u0018\u000e^3s\u0011\u001d\u0011W\u0011\u001aC\u0001\u000b/$\"!b2\t\u0011\u0015mW\u0011\u001aC!\u000b;\fQa\u001e:ji\u0016$2AFCp\u0011\u001d)\t/\"7A\u0002q\t1a\u001d;s\u0011!)Y.\"3\u0005\u0002\u0015\u0015Hc\u0002\f\u0006h\u0016]X1 \u0005\t\u000bS,\u0019\u000f1\u0001\u0006l\u0006!1MY;g!\u0015YQQ^Cy\u0013\r)yO\u0002\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004\u0017\u0015M\u0018bAC{\r\t!1\t[1s\u0011\u001d)I0b9A\u0002%\f1a\u001c4g\u0011\u001d)i0b9A\u0002%\f1\u0001\\3o\u0011\u001d\ty.\"3\u0005\u0002UAqAb\u0001\u0006J\u0012\u0005Q#A\u0003gYV\u001c\b\u000eC\u0004\u0007\b\u0001!\tA\"\u0003\u0002#9,wOU1x)J,W\r\u0015:j]R,'\u000f\u0006\u0003\u0007\f\u001dM\u0006c\u0001\u0014\u0007\u000e\u00191aq\u0002\u0001\u0001\r#\u0011aBU1x)J,W\r\u0015:j]R,'o\u0005\u0003\u0007\u000e))\u0006\"\u0003-\u0007\u000e\t\u0005\t\u0015!\u0003Z\u0011\u001d\u0011gQ\u0002C\u0001\r/!BAb\u0003\u0007\u001a!1\u0001L\"\u0006A\u0002eC\u0011B\"\b\u0007\u000e\u0001\u0007I\u0011\u00025\u0002\u000b\u0011,\u0007\u000f\u001e5\t\u0015\u0019\u0005bQ\u0002a\u0001\n\u00131\u0019#A\u0005eKB$\bn\u0018\u0013fcR\u0019aC\"\n\t\u0011A4y\"!AA\u0002%D\u0001B\"\u000b\u0007\u000e\u0001\u0006K![\u0001\u0007I\u0016\u0004H\u000f\u001b\u0011\t\u0015\u00195bQ\u0002a\u0001\n\u0013\t)/A\u000bqe&tG\u000fV=qKNLeNR8pi:|G/Z:\t\u0015\u0019EbQ\u0002a\u0001\n\u00131\u0019$A\rqe&tG\u000fV=qKNLeNR8pi:|G/Z:`I\u0015\fHc\u0001\f\u00076!A\u0001Ob\f\u0002\u0002\u0003\u0007Q\u0006\u0003\u0005\u0007:\u00195\u0001\u0015)\u0003.\u0003Y\u0001(/\u001b8u)f\u0004Xm]%o\r>|GO\\8uKN\u0004\u0003B\u0003D\u001f\r\u001b\u0001\r\u0011\"\u0003\u0002f\u0006\t\u0002O]5oi&twMR8pi:|G/Z:\t\u0015\u0019\u0005cQ\u0002a\u0001\n\u00131\u0019%A\u000bqe&tG/\u001b8h\r>|GO\\8uKN|F%Z9\u0015\u0007Y1)\u0005\u0003\u0005q\r\u007f\t\t\u00111\u0001.\u0011!1IE\"\u0004!B\u0013i\u0013A\u00059sS:$\u0018N\\4G_>$hn\u001c;fg\u0002B!B\"\u0014\u0007\u000e\t\u0007I\u0011\u0002D(\u0003%1wn\u001c;o_R,7/\u0006\u0002\u0007RA!a1\u000bDk\u001d\r1cQ\u000b\u0005\n\r/\u0002!\u0019!C\u0005\r3\nQBZ8pi:|G/Z%oI\u0016DXC\u0001D.!\r1cQ\f\u0004\u0007\r?\u0002AA\"\u0019\u0003\u001b\u0019{w\u000e\u001e8pi\u0016Le\u000eZ3y'\r1iF\u0003\u0005\bE\u001auC\u0011\u0001D3)\t1Y\u0006\u0003\u0006\u0007j\u0019u#\u0019!C\u0005\rW\nQ!\u001b8eKb,\"A\"\u001c\u0011\u0011\rUgq\u000eD:\r\u000bKAA\"\u001d\u0004X\n\u0019Q*\u001991\t\u0019UdQ\u0010\t\u0006w\u001a]d1P\u0005\u0004\rsb(!B\"mCN\u001c\b\u0003BA%\r{\"ABb \u0007\u0002\u0006\u0005\t\u0011!B\u0001\u0003\u001f\u00121a\u0018\u00132\u0011%1\u0019I\"\u0018!\u0002\u00131i'\u0001\u0004j]\u0012,\u0007\u0010\t\t\b\u0007+49)a\u0016j\u0013\u00111Iia6\u0003\u0017]+\u0017m\u001b%bg\"l\u0015\r\u001d\u0005\t\r\u001b3i\u0006\"\u0003\u0007\u0010\u0006Q1\r\\1tg&sG-\u001a=\u0016\t\u0019Ee\u0011\u0015\u000b\u0005\r\u000b3\u0019\n\u0003\u0006\u0007\u0016\u001a-\u0015\u0011!a\u0002\r/\u000b!\"\u001a<jI\u0016t7-\u001a\u00132!\u00191IJb'\u0007 6\tA!C\u0002\u0007\u001e\u0012\u0011\u0001b\u00117bgN$\u0016m\u001a\t\u0005\u0003\u00132\t\u000b\u0002\u0005\u0003.\u0019-%\u0019AA(\u0011)1)K\"\u0018C\u0002\u0013%aqU\u0001\tG>,h\u000e^3sgV\u0011a\u0011\u0016\t\b\u0007+4yGb+ja\u00111iK\"-\u0011\u000bm49Hb,\u0011\t\u0005%c\u0011\u0017\u0003\r\rg3),!A\u0001\u0002\u000b\u0005\u0011q\n\u0002\u0004?\u0012\u0012\u0004\"\u0003D\\\r;\u0002\u000b\u0011\u0002DU\u0003%\u0019w.\u001e8uKJ\u001c\b\u0005\u0003\u0005\u0007<\u001auC\u0011\u0002D_\u0003-qW\r\u001f;D_VtG/\u001a:\u0016\t\u0019}f\u0011\u001a\u000b\u0004S\u001a\u0005\u0007B\u0003Db\rs\u000b\t\u0011q\u0001\u0007F\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\r\u0019ee1\u0014Dd!\u0011\tIE\"3\u0005\u0011\t5b\u0011\u0018b\u0001\u0003\u001fB\u0001B\"4\u0007^\u0011\u0005aqZ\u0001\f[.4un\u001c;o_R,7\u000f\u0006\u0002\u0007RB!a1\u001bDk\u001b\t1iFB\u0004\u0007X\u001au\u0003A\"7\u0003\u0013\u0019{w\u000e\u001e8pi\u0016\u001c8c\u0001Dk\u0015!9!M\"6\u0005\u0002\u0019=\u0007B\u0003D'\r+\u0014\r\u0011\"\u0003\u0007`V\u0011a\u0011\u001d\t\t\u0007+4yGb9\u0007rB\"aQ\u001dDu!\u0015Yhq\u000fDt!\u0011\tIE\";\u0005\u0019\u0019-hQ^A\u0001\u0002\u0003\u0015\t!a\u0014\u0003\u0007}#3\u0007C\u0005\u0007p\u001aU\u0007\u0015!\u0003\u0007b\u0006Qam\\8u]>$Xm\u001d\u0011\u0011\u000b\rUg1_5\n\t\u0019U8q\u001b\u0002\n'>\u0014H/\u001a3TKRD\u0001B\"?\u0007V\u0012%a1`\u0001\u000fG2\f7o\u001d$p_Rtw\u000e^3t+\u00111ipb\u0002\u0015\t\u0019Ehq \u0005\u000b\u000f\u0003190!AA\u0004\u001d\r\u0011AC3wS\u0012,gnY3%gA1a\u0011\u0014DN\u000f\u000b\u0001B!!\u0013\b\b\u0011A!Q\u0006D|\u0005\u0004\ty\u0005\u0003\u0005\b\f\u0019UG\u0011AD\u0007\u0003\r\u0001X\u000f^\u000b\u0005\u000f\u001f9Y\u0002\u0006\u0003\b\u0012\u001duAcA5\b\u0014!QqQCD\u0005\u0003\u0003\u0005\u001dab\u0006\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007\u0005\u0004\u0007\u001a\u001amu\u0011\u0004\t\u0005\u0003\u0013:Y\u0002\u0002\u0005\u0003.\u001d%!\u0019AA(\u0011!9yb\"\u0003A\u0002\u001de\u0011aA1os\"Aq1\u0005Dk\t\u00039)#A\u0002hKR,Bab\n\b:Q!q\u0011FD\u0019!\u0019\t\u0019'!\u001b\b,A11b\"\fj\u0003/J1ab\f\u0007\u0005\u0019!V\u000f\u001d7fe!Qq1GD\u0011\u0003\u0003\u0005\u001da\"\u000e\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007\u0005\u0004\u0007\u001a\u001amuq\u0007\t\u0005\u0003\u0013:I\u0004\u0002\u0005\u0003.\u001d\u0005\"\u0019AA(\u0011!\u0019YH\"6\u0005\u0002\u001duR\u0003BD \u000f\u0017\"Ba\"\u0011\bNQ\u0019acb\u0011\t\u0015\u001d\u0015s1HA\u0001\u0002\b99%\u0001\u0006fm&$WM\\2fIY\u0002bA\"'\u0007\u001c\u001e%\u0003\u0003BA%\u000f\u0017\"\u0001B!\f\b<\t\u0007\u0011q\n\u0005\b\u000f\u001f:Y\u00041\u0001V\u0003\u001d\u0001(/\u001b8uKJD\u0011Bb<\u0007\u000e\u0001\u0006IA\"\u0015\t\u0011\rmdQ\u0002C\u0001\u000f+\"2AFD,\u0011!\u0019\tib\u0015A\u0002\r\r\u0005\u0002CD.\r\u001b!\ta\"\u0018\u0002\u0019A\u0014\u0018N\u001c;Qe>$Wo\u0019;\u0015\u0013Y9yfb\u001a\bn\u001dE\u0004\u0002CAd\u000f3\u0002\ra\"\u0019\u0011\u0007-9\u0019'C\u0002\bf\u0019\u0011q\u0001\u0015:pIV\u001cG\u000f\u0003\u0006\bj\u001de\u0003\u0013!a\u0001\u000fW\n\u0001\u0002\u001d:fC6\u0014G.\u001a\t\u0007\u0017\u0005\rs\u0011\r\f\t\u0015\u0005Mw\u0011\fI\u0001\u0002\u00049y\u0007\u0005\u0004\f\u0003\u0007\n9F\u0006\u0005\u000b\u000fg:I\u0006%AA\u0002\u001d-\u0014!\u00039pgR\fWN\u00197f\u0011!99H\"\u0004\u0005\u0002\u001de\u0014!\u00049sS:$\u0018\n^3sC\ndW\rF\u0005\u0017\u000fw:Iib#\b\u000e\"AqQPD;\u0001\u00049y(\u0001\u0005ji\u0016\u0014\u0018M\u00197fa\u00119\ti\"\"\u0011\r\u0005\r\u0014\u0011NDB!\u0011\tIe\"\"\u0005\u0019\u001d\u001du1PA\u0001\u0002\u0003\u0015\t!a\u0014\u0003\u0007}#c\u0007\u0003\u0006\bj\u001dU\u0004\u0013\"a\u0001\u0003oA!\"a5\bvA\u0005\t\u0019AD8\u0011)9\u0019h\"\u001e\u0011\n\u0003\u0007\u0011q\u0007\u0005\u000b\u000f#3i!%A\u0005\u0002\u001dM\u0015A\u00069sS:$\bK]8ek\u000e$H\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u001dU%\u0006BD6\u0007#C!b\"'\u0007\u000eE\u0005I\u0011ADN\u0003Y\u0001(/\u001b8u!J|G-^2uI\u0011,g-Y;mi\u0012\u001aTCADOU\u00119yg!%\t\u0015\u001d\u0005fQBI\u0001\n\u00039\u0019*\u0001\fqe&tG\u000f\u0015:pIV\u001cG\u000f\n3fM\u0006,H\u000e\u001e\u00135\u0011)9)K\"\u0004\u0012\u0002\u0013\u0005qqU\u0001\u0018aJLg\u000e^%uKJ\f'\r\\3%I\u00164\u0017-\u001e7uII*\"a\"++\u0007Y\u0019\t\n\u0003\u0006\b.\u001a5\u0011\u0013!C\u0001\u000f7\u000bq\u0003\u001d:j]RLE/\u001a:bE2,G\u0005Z3gCVdG\u000fJ\u001a\t\u0015\u001dEfQBI\u0001\n\u000399+A\fqe&tG/\u0013;fe\u0006\u0014G.\u001a\u0013eK\u001a\fW\u000f\u001c;%i!9Q1\u0015D\u0003\u0001\u0004I\u0006\u0002CD\\\u0001\u0001\u0006IAb\u0017\u0002\u001d\u0019|w\u000e\u001e8pi\u0016Le\u000eZ3yA!9q1\u0018\u0001\u0005\u0002\u001du\u0016\u0001B:i_^$2\u0001HD`\u0011\u0019!s\u0011\u0018a\u0001K!9q1\u0018\u0001\u0005\u0002\u001d\rGc\u0001\u000f\bF\"A!\u0011PDa\u0001\u000499\rE\u0002'\u000f\u0013LAab3\bN\n9a\t\\1h'\u0016$\u0018bADh\u0005\tAa\t\\1h'\u0016$8\u000fC\u0004\b<\u0002!\tab5\u0015\u0007q9)\u000e\u0003\u0005\bX\u001eE\u0007\u0019ADm\u0003!\u0001xn]5uS>t\u0007c\u0001\u0014\b\\&!qQ\\Dp\u0005!\u0001vn]5uS>t\u0017bADq\u0005\tI\u0001k\\:ji&|gn\u001d\u0005\b\u000fK\u0004A\u0011ADt\u0003!\u0019\bn\\<EK\u000edGc\u0001\u000f\bj\"Aq1^Dr\u0001\u0004\u00119$A\u0002ts6\u0004Bab<\br6\t!!C\u0002\bt\n\u00111bU=nE>dG+\u00192mK\u0002")
public interface Printers
extends scala.reflect.api.Printers {
    public void scala$reflect$internal$Printers$_setter_$scala$reflect$internal$Printers$$footnoteIndex_$eq(FootnoteIndex var1);

    public String quotedName(Names.Name var1, boolean var2);

    public String quotedName(Names.Name var1);

    public String quotedName(String var1);

    public String decodedSymName(Trees.Tree var1, Names.Name var2);

    public String symName(Trees.Tree var1, Names.Name var2);

    public String backquotedPath(Trees.Tree var1);

    public void xprintTree(TreePrinter var1, Trees.Tree var2);

    public TreePrinter newCodePrinter(PrintWriter var1, Trees.Tree var2, boolean var3);

    @Override
    public TreePrinter newTreePrinter(PrintWriter var1);

    public TreePrinter newTreePrinter(OutputStream var1);

    public TreePrinter newTreePrinter();

    public Printers$ConsoleWriter$ ConsoleWriter();

    @Override
    public RawTreePrinter newRawTreePrinter(PrintWriter var1);

    public FootnoteIndex scala$reflect$internal$Printers$$footnoteIndex();

    public String show(Names.Name var1);

    public String show(long var1);

    public String show(Position var1);

    public String showDecl(Symbols.Symbol var1);

    public class TreePrinter
    implements Printers.TreePrinter {
        public final PrintWriter scala$reflect$internal$Printers$TreePrinter$$out;
        private int indentMargin;
        private final int indentStep;
        private String indentString;
        private final boolean commentsRequired;
        private Symbols.Symbol currentOwner;
        private Types.Type selectorType;
        public final /* synthetic */ SymbolTable $outer;
        private boolean printTypes;
        private boolean printIds;
        private boolean printOwners;
        private boolean printKinds;
        private boolean printMirrors;
        private boolean printPositions;

        @Override
        public boolean printTypes() {
            return this.printTypes;
        }

        @Override
        @TraitSetter
        public void printTypes_$eq(boolean x$1) {
            this.printTypes = x$1;
        }

        @Override
        public boolean printIds() {
            return this.printIds;
        }

        @Override
        @TraitSetter
        public void printIds_$eq(boolean x$1) {
            this.printIds = x$1;
        }

        @Override
        public boolean printOwners() {
            return this.printOwners;
        }

        @Override
        @TraitSetter
        public void printOwners_$eq(boolean x$1) {
            this.printOwners = x$1;
        }

        @Override
        public boolean printKinds() {
            return this.printKinds;
        }

        @Override
        @TraitSetter
        public void printKinds_$eq(boolean x$1) {
            this.printKinds = x$1;
        }

        @Override
        public boolean printMirrors() {
            return this.printMirrors;
        }

        @Override
        @TraitSetter
        public void printMirrors_$eq(boolean x$1) {
            this.printMirrors = x$1;
        }

        @Override
        public boolean printPositions() {
            return this.printPositions;
        }

        @Override
        @TraitSetter
        public void printPositions_$eq(boolean x$1) {
            this.printPositions = x$1;
        }

        @Override
        public Printers.TreePrinter withTypes() {
            return Printers$TreePrinter$class.withTypes(this);
        }

        @Override
        public Printers.TreePrinter withoutTypes() {
            return Printers$TreePrinter$class.withoutTypes(this);
        }

        @Override
        public Printers.TreePrinter withIds() {
            return Printers$TreePrinter$class.withIds(this);
        }

        @Override
        public Printers.TreePrinter withoutIds() {
            return Printers$TreePrinter$class.withoutIds(this);
        }

        @Override
        public Printers.TreePrinter withOwners() {
            return Printers$TreePrinter$class.withOwners(this);
        }

        @Override
        public Printers.TreePrinter withoutOwners() {
            return Printers$TreePrinter$class.withoutOwners(this);
        }

        @Override
        public Printers.TreePrinter withKinds() {
            return Printers$TreePrinter$class.withKinds(this);
        }

        @Override
        public Printers.TreePrinter withoutKinds() {
            return Printers$TreePrinter$class.withoutKinds(this);
        }

        @Override
        public Printers.TreePrinter withMirrors() {
            return Printers$TreePrinter$class.withMirrors(this);
        }

        @Override
        public Printers.TreePrinter withoutMirrors() {
            return Printers$TreePrinter$class.withoutMirrors(this);
        }

        @Override
        public Printers.TreePrinter withPositions() {
            return Printers$TreePrinter$class.withPositions(this);
        }

        @Override
        public Printers.TreePrinter withoutPositions() {
            return Printers$TreePrinter$class.withoutPositions(this);
        }

        public int indentMargin() {
            return this.indentMargin;
        }

        public void indentMargin_$eq(int x$1) {
            this.indentMargin = x$1;
        }

        public int indentStep() {
            return this.indentStep;
        }

        public String indentString() {
            return this.indentString;
        }

        public void indentString_$eq(String x$1) {
            this.indentString = x$1;
        }

        public void indent() {
            this.indentMargin_$eq(this.indentMargin() + this.indentStep());
        }

        public void undent() {
            this.indentMargin_$eq(this.indentMargin() - this.indentStep());
        }

        public void printPosition(Trees.Tree tree) {
            if (this.printPositions()) {
                this.comment((Function0<BoxedUnit>)((Object)new Serializable(this, tree){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TreePrinter $outer;
                    private final Trees.Tree tree$1;

                    public final void apply() {
                        this.apply$mcV$sp();
                    }

                    public void apply$mcV$sp() {
                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.tree$1.pos().show()}));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.tree$1 = tree$1;
                    }
                }));
            }
        }

        public void printTypesInfo(Trees.Tree tree) {
            if (this.printTypes() && tree.isTerm() && tree.canHaveAttrs()) {
                this.comment((Function0<BoxedUnit>)((Object)new Serializable(this, tree){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TreePrinter $outer;
                    private final Trees.Tree tree$2;

                    public final void apply() {
                        this.apply$mcV$sp();
                    }

                    public void apply$mcV$sp() {
                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"{", this.tree$2.tpe() == null ? "<null>" : this.tree$2.tpe().toString(), "}"}));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.tree$2 = tree$2;
                    }
                }));
            }
        }

        public void println() {
            this.scala$reflect$internal$Printers$TreePrinter$$out.println();
            while (this.indentMargin() > this.indentString().length()) {
                this.indentString_$eq(new StringBuilder().append((Object)this.indentString()).append((Object)this.indentString()).toString());
            }
            if (this.indentMargin() > 0) {
                this.scala$reflect$internal$Printers$TreePrinter$$out.write(this.indentString(), 0, this.indentMargin());
            }
        }

        public <a> void printSeq(List<a> ls, Function1<a, BoxedUnit> printelem, Function0<BoxedUnit> printsep) {
            block5: {
                block3: {
                    block4: {
                        block2: {
                            Some<List<a>> some = List$.MODULE$.unapplySeq(ls);
                            if (some.isEmpty() || some.get() == null || ((LinearSeqOptimized)some.get()).lengthCompare(0) != 0) break block2;
                            break block3;
                        }
                        Some<List<a>> some = List$.MODULE$.unapplySeq(ls);
                        if (some.isEmpty() || some.get() == null || ((LinearSeqOptimized)some.get()).lengthCompare(1) != 0) break block4;
                        Object x = ((LinearSeqOptimized)some.get()).apply(0);
                        printelem.apply(x);
                        break block3;
                    }
                    if (!(ls instanceof $colon$colon)) break block5;
                    $colon$colon $colon$colon = ($colon$colon)ls;
                    printelem.apply($colon$colon.head());
                    printsep.apply$mcV$sp();
                    this.printSeq($colon$colon.tl$1(), printelem, printsep);
                }
                return;
            }
            throw new MatchError(ls);
        }

        public void printColumn(List<Trees.Tree> ts, String start, String sep, String end) {
            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{start}));
            this.indent();
            this.println();
            this.printSeq((List)ts, (Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TreePrinter $outer;

                public final void apply(Trees.Tree x$1) {
                    this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{x$1}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }), (Function0<BoxedUnit>)((Object)new Serializable(this, sep){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TreePrinter $outer;
                private final String sep$1;

                public final void apply() {
                    this.apply$mcV$sp();
                }

                public void apply$mcV$sp() {
                    this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.sep$1}));
                    this.$outer.println();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.sep$1 = sep$1;
                }
            }));
            this.undent();
            this.println();
            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{end}));
        }

        public void printRow(List<Trees.Tree> ts, String start, String sep, String end) {
            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{start}));
            this.printSeq((List)ts, (Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TreePrinter $outer;

                public final void apply(Trees.Tree x$2) {
                    this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{x$2}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }), (Function0<BoxedUnit>)((Object)new Serializable(this, sep){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TreePrinter $outer;
                private final String sep$2;

                public final void apply() {
                    this.apply$mcV$sp();
                }

                public void apply$mcV$sp() {
                    this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.sep$2}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.sep$2 = sep$2;
                }
            }));
            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{end}));
        }

        public void printRow(List<Trees.Tree> ts, String sep) {
            this.printRow(ts, "", sep, "");
        }

        public void printTypeParams(List<Trees.TypeDef> ts) {
            if (ts.nonEmpty()) {
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"["}));
                this.printSeq((List)ts, (Function1)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TreePrinter $outer;

                    public final void apply(Trees.TypeDef t) {
                        this.$outer.printAnnotations(t);
                        if (t.mods().hasFlag(131072L)) {
                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"-"}));
                        } else if (t.mods().hasFlag(65536L)) {
                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"+"}));
                        }
                        this.$outer.printParam(t);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }), (Function0<BoxedUnit>)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TreePrinter $outer;

                    public final void apply() {
                        this.apply$mcV$sp();
                    }

                    public void apply$mcV$sp() {
                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{", "}));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }));
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"]"}));
            }
        }

        public void printLabelParams(List<Trees.Ident> ps) {
            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"("}));
            this.printSeq((List)ps, (Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TreePrinter $outer;

                public final void apply(Trees.Ident p) {
                    this.$outer.printLabelParam(p);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }), (Function0<BoxedUnit>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TreePrinter $outer;

                public final void apply() {
                    this.apply$mcV$sp();
                }

                public void apply$mcV$sp() {
                    this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{", "}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{")"}));
        }

        public void printLabelParam(Trees.Ident p) {
            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(p, p.name())}));
            this.printOpt(": ", new Trees.TypeTree(this.scala$reflect$internal$Printers$TreePrinter$$$outer()).setType(p.tpe()));
        }

        public void parenthesize(boolean condition, String open, String close, Function0<BoxedUnit> body2) {
            if (condition) {
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{open}));
            }
            body2.apply$mcV$sp();
            if (condition) {
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{close}));
            }
        }

        public boolean parenthesize$default$1() {
            return true;
        }

        public String parenthesize$default$2() {
            return "(";
        }

        public String parenthesize$default$3() {
            return ")";
        }

        public boolean commentsRequired() {
            return this.commentsRequired;
        }

        public void comment(Function0<BoxedUnit> body2) {
            this.parenthesize(this.commentsRequired(), "/*", "*/", body2);
        }

        public void printImplicitInParamsList(List<Trees.ValDef> vds) {
            if (vds.nonEmpty()) {
                this.printFlags(vds.head().mods().flags() & 0x200L, "");
            }
        }

        public void printValueParams(List<Trees.ValDef> ts, boolean inParentheses) {
            this.parenthesize(inParentheses, this.parenthesize$default$2(), this.parenthesize$default$3(), (Function0<BoxedUnit>)((Object)new Serializable(this, ts){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ TreePrinter $outer;
                private final List ts$1;

                public final void apply() {
                    this.apply$mcV$sp();
                }

                public void apply$mcV$sp() {
                    this.$outer.printImplicitInParamsList(this.ts$1);
                    this.$outer.printSeq(this.ts$1, new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ TreePrinter$$anonfun$printValueParams$1 $outer;

                        public final void apply(Trees.Tree tree) {
                            this.$outer.$outer.printParam(tree);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, (Function0<BoxedUnit>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ TreePrinter$$anonfun$printValueParams$1 $outer;

                        public final void apply() {
                            this.apply$mcV$sp();
                        }

                        public void apply$mcV$sp() {
                            this.$outer.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{", "}));
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                }

                public /* synthetic */ TreePrinter scala$reflect$internal$Printers$TreePrinter$$anonfun$$$outer() {
                    return this.$outer;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.ts$1 = ts$1;
                }
            }));
        }

        public void printParam(Trees.Tree tree) {
            block4: {
                block3: {
                    block2: {
                        if (!(tree instanceof Trees.ValDef)) break block2;
                        Trees.ValDef valDef = (Trees.ValDef)tree;
                        this.printPosition(tree);
                        this.printAnnotations(valDef);
                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, valDef.name())}));
                        this.printOpt(": ", valDef.tpt());
                        this.printOpt(" = ", valDef.rhs());
                        break block3;
                    }
                    if (!(tree instanceof Trees.TypeDef)) break block4;
                    Trees.TypeDef typeDef = (Trees.TypeDef)tree;
                    this.printPosition(tree);
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, typeDef.name())}));
                    this.printTypeParams(typeDef.tparams());
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{typeDef.rhs()}));
                }
                return;
            }
            throw new MatchError(tree);
        }

        public void printBlock(Trees.Tree tree) {
            if (tree instanceof Trees.Block) {
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{tree}));
            } else {
                this.printColumn((List<Trees.Tree>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.Tree[]{tree})), "{", ";", "}");
            }
        }

        private <T> T symFn(Trees.Tree tree, Function1<Symbols.Symbol, T> f, Function0<T> orElse) {
            boolean bl;
            Symbols.Symbol symbol = tree.symbol();
            if (symbol == null) {
                bl = true;
            } else {
                Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Printers$TreePrinter$$$outer().NoSymbol();
                bl = !(noSymbol != null ? !noSymbol.equals(symbol) : symbol != null);
            }
            T t = bl ? orElse.apply() : f.apply(symbol);
            return t;
        }

        private boolean ifSym(Trees.Tree tree, Function1<Symbols.Symbol, Object> p) {
            return BoxesRunTime.unboxToBoolean(this.symFn(tree, (Function1)p, (Function0)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply() {
                    return false;
                }

                public boolean apply$mcZ$sp() {
                    return false;
                }
            })));
        }

        public void printOpt(String prefix, Trees.Tree tree) {
            if (tree.nonEmpty()) {
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{prefix, tree}));
            }
        }

        public void printModifiers(Trees.Tree tree, Trees.Modifiers mods) {
            Symbols.Symbol symbol = tree.symbol();
            Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Printers$TreePrinter$$$outer().NoSymbol();
            Symbols.Symbol symbol2 = tree.symbol();
            Symbols.NoSymbol noSymbol2 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().NoSymbol();
            this.printFlags(!(symbol != null ? !symbol.equals(noSymbol) : noSymbol != null) ? mods.flags() : tree.symbol().flags(), String.valueOf(!(symbol2 != null ? !symbol2.equals(noSymbol2) : noSymbol2 != null) ? mods.privateWithin() : (tree.symbol().hasAccessBoundary() ? tree.symbol().privateWithin().name() : "")));
        }

        public void printFlags(long flags, String privateWithin) {
            String s2;
            MutableSettings.SettingValue settingValue = this.scala$reflect$internal$Printers$TreePrinter$$$outer().settings().debug();
            MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
            long mask = BoxesRunTime.unboxToBoolean(settingValue.value()) ? -1L : 251791349157423L;
            String string2 = s2 = Flags$.MODULE$.flagsToString(flags & mask, privateWithin);
            if (string2 == null || !string2.equals("")) {
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)s2).append((Object)" ").toString()}));
            }
        }

        public void printAnnotations(Trees.MemberDef tree) {
            List list2 = tree.symbol().annotations();
            List<Object> list3 = ((Object)Nil$.MODULE$).equals(list2) ? tree.mods().annotations() : list2;
            List list4 = list3;
            while (!((AbstractIterable)list4).isEmpty()) {
                Object a = ((AbstractIterable)list4).head();
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"@", " "})).s(Predef$.MODULE$.genericWrapArray(new Object[]{a}))}));
                list4 = (List)list4.tail();
            }
        }

        private Symbols.Symbol currentOwner() {
            return this.currentOwner;
        }

        private void currentOwner_$eq(Symbols.Symbol x$1) {
            this.currentOwner = x$1;
        }

        private Types.Type selectorType() {
            return this.selectorType;
        }

        private void selectorType_$eq(Types.Type x$1) {
            this.selectorType = x$1;
        }

        public void printPackageDef(Trees.PackageDef tree, String separator) {
            if (tree != null) {
                Tuple2<Trees.RefTree, List<Trees.Tree>> tuple2 = new Tuple2<Trees.RefTree, List<Trees.Tree>>(tree.pid(), tree.stats());
                Trees.RefTree packaged = tuple2._1();
                List<Trees.Tree> stats = tuple2._2();
                this.printAnnotations(tree);
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"package ", packaged}));
                this.printColumn(stats, " {", separator, "}");
                return;
            }
            throw new MatchError(tree);
        }

        public void printValDef(Trees.ValDef tree, Function0<String> resultName, Function0<BoxedUnit> printTypeSignature, Function0<BoxedUnit> printRhs) {
            if (tree != null) {
                Tuple4<Trees.Modifiers, Names.TermName, Trees.Tree, Trees.Tree> tuple4 = new Tuple4<Trees.Modifiers, Names.TermName, Trees.Tree, Trees.Tree>(tree.mods(), tree.name(), tree.tpt(), tree.rhs());
                Trees.Modifiers mods = tuple4._1();
                tuple4._2();
                tuple4._3();
                tuple4._4();
                this.printAnnotations(tree);
                this.printModifiers(tree, mods);
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{mods.isMutable() ? "var " : "val ", resultName.apply()}));
                printTypeSignature.apply$mcV$sp();
                printRhs.apply$mcV$sp();
                return;
            }
            throw new MatchError(tree);
        }

        public void printDefDef(Trees.DefDef tree, Function0<String> resultName, Function0<BoxedUnit> printTypeSignature, Function0<BoxedUnit> printRhs) {
            if (tree != null) {
                Tuple6<Trees.Modifiers, Names.TermName, List<Trees.TypeDef>, List<List<Trees.ValDef>>, Trees.Tree, Trees.Tree> tuple6 = new Tuple6<Trees.Modifiers, Names.TermName, List<Trees.TypeDef>, List<List<Trees.ValDef>>, Trees.Tree, Trees.Tree>(tree.mods(), tree.name(), tree.tparams(), tree.vparamss(), tree.tpt(), tree.rhs());
                Trees.Modifiers mods = tuple6._1();
                tuple6._2();
                List<Trees.TypeDef> tparams2 = tuple6._3();
                List vparamss = tuple6._4();
                tuple6._5();
                tuple6._6();
                this.printAnnotations(tree);
                this.printModifiers(tree, mods);
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)"def ").append((Object)resultName.apply()).toString()}));
                this.printTypeParams(tparams2);
                List list2 = vparamss;
                while (!((AbstractIterable)list2).isEmpty()) {
                    List list3 = (List)((AbstractIterable)list2).head();
                    this.printValueParams(list3, this.printValueParams$default$2());
                    list2 = (List)list2.tail();
                }
                printTypeSignature.apply$mcV$sp();
                printRhs.apply$mcV$sp();
                return;
            }
            throw new MatchError(tree);
        }

        public void printTypeDef(Trees.TypeDef tree, Function0<String> resultName) {
            if (tree != null) {
                Tuple4<Trees.Modifiers, Names.TypeName, List<Trees.TypeDef>, Trees.Tree> tuple4 = new Tuple4<Trees.Modifiers, Names.TypeName, List<Trees.TypeDef>, Trees.Tree>(tree.mods(), tree.name(), tree.tparams(), tree.rhs());
                Trees.Modifiers mods = tuple4._1();
                tuple4._2();
                List<Trees.TypeDef> tparams2 = tuple4._3();
                Trees.Tree rhs = tuple4._4();
                if (mods.hasFlag(8208L)) {
                    this.printAnnotations(tree);
                    this.printModifiers(tree, mods);
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"type "}));
                    this.printParam(tree);
                } else {
                    this.printAnnotations(tree);
                    this.printModifiers(tree, mods);
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)"type ").append((Object)resultName.apply()).toString()}));
                    this.printTypeParams(tparams2);
                    this.printOpt(" = ", rhs);
                }
                return;
            }
            throw new MatchError(tree);
        }

        public void printImport(Trees.Import tree, Function0<String> resSelect) {
            if (tree != null) {
                Tuple2<Trees.Tree, List<Trees.ImportSelector>> tuple2 = new Tuple2<Trees.Tree, List<Trees.ImportSelector>>(tree.expr(), tree.selectors());
                tuple2._1();
                List<Trees.ImportSelector> selectors = tuple2._2();
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"import ", resSelect.apply(), "."}));
                Some<List<Trees.ImportSelector>> some = List$.MODULE$.unapplySeq(selectors);
                if (!some.isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(1) == 0) {
                    Trees.ImportSelector s2 = (Trees.ImportSelector)((LinearSeqOptimized)some.get()).apply(0);
                    if (this.isNotRename$1(s2)) {
                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$Printers$TreePrinter$$selectorToString$1(s2)}));
                    } else {
                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"{", this.scala$reflect$internal$Printers$TreePrinter$$selectorToString$1(s2), "}"}));
                    }
                } else {
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{((TraversableOnce)selectors.map(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ TreePrinter $outer;

                        public final String apply(Trees.ImportSelector s2) {
                            return this.$outer.scala$reflect$internal$Printers$TreePrinter$$selectorToString$1(s2);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, List$.MODULE$.canBuildFrom())).mkString("{", ", ", "}")}));
                }
                return;
            }
            throw new MatchError(tree);
        }

        public void printCaseDef(Trees.CaseDef tree) {
            if (tree != null) {
                Tuple3<Trees.Tree, Trees.Tree, Trees.Tree> tuple3 = new Tuple3<Trees.Tree, Trees.Tree, Trees.Tree>(tree.pat(), tree.guard(), tree.body());
                Trees.Tree pat = tuple3._1();
                Trees.Tree guard = tuple3._2();
                Trees.Tree body2 = tuple3._3();
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"case "}));
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{pat}));
                this.printOpt(" if ", guard);
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{" => ", body2}));
                return;
            }
            throw new MatchError(tree);
        }

        public void printFunction(Trees.Function tree, Function0<BoxedUnit> printValueParams2) {
            if (tree != null) {
                Tuple2<List<Trees.ValDef>, Trees.Tree> tuple2 = new Tuple2<List<Trees.ValDef>, Trees.Tree>(tree.vparams(), tree.body());
                tuple2._1();
                Trees.Tree body2 = tuple2._2();
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"("}));
                printValueParams2.apply$mcV$sp();
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{" => ", body2, ")"}));
                if (this.printIds() && tree.symbol() != null) {
                    this.comment((Function0<BoxedUnit>)((Object)new Serializable(this, tree){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ TreePrinter $outer;
                        private final Trees.Function tree$3;

                        public final void apply() {
                            this.apply$mcV$sp();
                        }

                        public void apply$mcV$sp() {
                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)"#").append(BoxesRunTime.boxToInteger(this.tree$3.symbol().id())).toString()}));
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.tree$3 = tree$3;
                        }
                    }));
                }
                if (this.printOwners() && tree.symbol() != null) {
                    this.comment((Function0<BoxedUnit>)((Object)new Serializable(this, tree){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ TreePrinter $outer;
                        private final Trees.Function tree$3;

                        public final void apply() {
                            this.apply$mcV$sp();
                        }

                        public void apply$mcV$sp() {
                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)"@").append(BoxesRunTime.boxToInteger(this.tree$3.symbol().owner().id())).toString()}));
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.tree$3 = tree$3;
                        }
                    }));
                }
                return;
            }
            throw new MatchError(tree);
        }

        public void printSuper(Trees.Super tree, Function0<String> resultName, boolean checkSymbol) {
            block4: {
                Names.TypeName mix;
                block6: {
                    block5: {
                        if (tree == null || !(tree.qual() instanceof Trees.This)) break block4;
                        Trees.This this_ = (Trees.This)tree.qual();
                        Tuple2<Names.TypeName, Names.TypeName> tuple2 = new Tuple2<Names.TypeName, Names.TypeName>(this_.qual(), tree.mix());
                        Names.TypeName qual = tuple2._1();
                        mix = tuple2._2();
                        if (qual.nonEmpty()) break block5;
                        if (!checkSymbol) break block6;
                        Symbols.Symbol symbol = tree.symbol();
                        Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Printers$TreePrinter$$$outer().NoSymbol();
                        if (!(symbol == null ? noSymbol != null : !symbol.equals(noSymbol))) break block6;
                    }
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)resultName.apply()).append((Object)".").toString()}));
                }
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"super"}));
                if (mix.nonEmpty()) {
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"[", "]"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{mix}))}));
                }
                return;
            }
            throw new MatchError(tree);
        }

        public void printThis(Trees.This tree, Function0<String> resultName) {
            if (tree != null) {
                Names.TypeName typeName = tree.qual();
                if (typeName.nonEmpty()) {
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)resultName.apply()).append((Object)".").toString()}));
                }
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"this"}));
                return;
            }
            throw new MatchError(tree);
        }

        public void printBlock(List<Trees.Tree> stats, Trees.Tree expr) {
            this.printColumn(((List)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.Tree[]{expr}))).$colon$colon$colon(stats), "{", ";", "}");
        }

        /*
         * Unable to fully structure code
         */
        public void printTree(Trees.Tree tree) {
            block33: {
                block70: {
                    block69: {
                        block68: {
                            block67: {
                                block66: {
                                    block65: {
                                        block64: {
                                            block63: {
                                                block62: {
                                                    block61: {
                                                        block60: {
                                                            block59: {
                                                                block58: {
                                                                    block57: {
                                                                        block56: {
                                                                            block55: {
                                                                                block54: {
                                                                                    block53: {
                                                                                        block52: {
                                                                                            block51: {
                                                                                                block50: {
                                                                                                    block49: {
                                                                                                        block48: {
                                                                                                            block47: {
                                                                                                                block46: {
                                                                                                                    block45: {
                                                                                                                        block44: {
                                                                                                                            block43: {
                                                                                                                                block42: {
                                                                                                                                    block41: {
                                                                                                                                        block40: {
                                                                                                                                            block39: {
                                                                                                                                                block38: {
                                                                                                                                                    block37: {
                                                                                                                                                        block36: {
                                                                                                                                                            block35: {
                                                                                                                                                                block34: {
                                                                                                                                                                    block32: {
                                                                                                                                                                        var37_2 = false;
                                                                                                                                                                        var38_3 = null;
                                                                                                                                                                        var43_4 = false;
                                                                                                                                                                        var44_5 = null;
                                                                                                                                                                        if (!this.scala$reflect$internal$Printers$TreePrinter$$$outer().EmptyTree().equals(tree)) break block32;
                                                                                                                                                                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"<empty>"}));
                                                                                                                                                                        break block33;
                                                                                                                                                                    }
                                                                                                                                                                    if (!(tree instanceof Trees.ClassDef)) break block34;
                                                                                                                                                                    var3_6 = (Trees.ClassDef)tree;
                                                                                                                                                                    this.printAnnotations(var3_6);
                                                                                                                                                                    this.printModifiers(tree, var3_6.mods());
                                                                                                                                                                    word = var3_6.mods().isTrait() != false ? "trait" : (this.ifSym(tree, (Function1<Symbols.Symbol, Object>)new Serializable(this){
                                                                                                                                                                        public static final long serialVersionUID = 0L;

                                                                                                                                                                        public final boolean apply(Symbols.Symbol x$13) {
                                                                                                                                                                            return x$13.isModuleClass();
                                                                                                                                                                        }
                                                                                                                                                                    }) != false ? "object" : "class");
                                                                                                                                                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{word, " ", this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, var3_6.name())}));
                                                                                                                                                                    this.printTypeParams(var3_6.tparams());
                                                                                                                                                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var3_6.mods().isDeferred() != false ? " <: " : " extends ", var3_6.impl()}));
                                                                                                                                                                    break block33;
                                                                                                                                                                }
                                                                                                                                                                if (!(tree instanceof Trees.PackageDef)) break block35;
                                                                                                                                                                var4_8 = (Trees.PackageDef)tree;
                                                                                                                                                                this.printPackageDef(var4_8, ";");
                                                                                                                                                                break block33;
                                                                                                                                                            }
                                                                                                                                                            if (!(tree instanceof Trees.ModuleDef)) break block36;
                                                                                                                                                            var5_9 = (Trees.ModuleDef)tree;
                                                                                                                                                            this.printAnnotations(var5_9);
                                                                                                                                                            this.printModifiers(tree, var5_9.mods());
                                                                                                                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)"object ").append((Object)this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, var5_9.name())).toString(), " extends ", var5_9.impl()}));
                                                                                                                                                            break block33;
                                                                                                                                                        }
                                                                                                                                                        if (!(tree instanceof Trees.ValDef)) break block37;
                                                                                                                                                        var6_10 = (Trees.ValDef)tree;
                                                                                                                                                        this.printValDef(var6_10, (Function0<String>)new Serializable(this, tree, var6_10){
                                                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                                                            private final /* synthetic */ TreePrinter $outer;
                                                                                                                                                            private final Trees.Tree tree$4;
                                                                                                                                                            private final Trees.ValDef x5$1;

                                                                                                                                                            public final String apply() {
                                                                                                                                                                return this.$outer.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(this.tree$4, this.x5$1.name());
                                                                                                                                                            }
                                                                                                                                                            {
                                                                                                                                                                if ($outer == null) {
                                                                                                                                                                    throw null;
                                                                                                                                                                }
                                                                                                                                                                this.$outer = $outer;
                                                                                                                                                                this.tree$4 = tree$4;
                                                                                                                                                                this.x5$1 = x5$1;
                                                                                                                                                            }
                                                                                                                                                        }, (Function0<BoxedUnit>)new Serializable(this, var6_10){
                                                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                                                            public final /* synthetic */ TreePrinter $outer;
                                                                                                                                                            public final Trees.ValDef x5$1;

                                                                                                                                                            public final void apply() {
                                                                                                                                                                this.$outer.printOpt(": ", this.x5$1.tpt());
                                                                                                                                                            }

                                                                                                                                                            public void apply$mcV$sp() {
                                                                                                                                                                this.$outer.printOpt(": ", this.x5$1.tpt());
                                                                                                                                                            }
                                                                                                                                                            {
                                                                                                                                                                if ($outer == null) {
                                                                                                                                                                    throw null;
                                                                                                                                                                }
                                                                                                                                                                this.$outer = $outer;
                                                                                                                                                                this.x5$1 = x5$1;
                                                                                                                                                            }
                                                                                                                                                        }, (Function0<BoxedUnit>)new Serializable(this, var6_10){
                                                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                                                            private final /* synthetic */ TreePrinter $outer;
                                                                                                                                                            private final Trees.ValDef x5$1;

                                                                                                                                                            public final void apply() {
                                                                                                                                                                this.apply$mcV$sp();
                                                                                                                                                            }

                                                                                                                                                            public void apply$mcV$sp() {
                                                                                                                                                                if (!this.x5$1.mods().isDeferred()) {
                                                                                                                                                                    this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{" = ", this.x5$1.rhs().isEmpty() ? "_" : this.x5$1.rhs()}));
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                            {
                                                                                                                                                                if ($outer == null) {
                                                                                                                                                                    throw null;
                                                                                                                                                                }
                                                                                                                                                                this.$outer = $outer;
                                                                                                                                                                this.x5$1 = x5$1;
                                                                                                                                                            }
                                                                                                                                                        });
                                                                                                                                                        break block33;
                                                                                                                                                    }
                                                                                                                                                    if (!(tree instanceof Trees.DefDef)) break block38;
                                                                                                                                                    var7_11 = (Trees.DefDef)tree;
                                                                                                                                                    this.printDefDef(var7_11, (Function0<String>)new Serializable(this, tree, var7_11){
                                                                                                                                                        public static final long serialVersionUID = 0L;
                                                                                                                                                        private final /* synthetic */ TreePrinter $outer;
                                                                                                                                                        private final Trees.Tree tree$4;
                                                                                                                                                        private final Trees.DefDef x6$1;

                                                                                                                                                        public final String apply() {
                                                                                                                                                            return this.$outer.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(this.tree$4, this.x6$1.name());
                                                                                                                                                        }
                                                                                                                                                        {
                                                                                                                                                            if ($outer == null) {
                                                                                                                                                                throw null;
                                                                                                                                                            }
                                                                                                                                                            this.$outer = $outer;
                                                                                                                                                            this.tree$4 = tree$4;
                                                                                                                                                            this.x6$1 = x6$1;
                                                                                                                                                        }
                                                                                                                                                    }, (Function0<BoxedUnit>)new Serializable(this, var7_11){
                                                                                                                                                        public static final long serialVersionUID = 0L;
                                                                                                                                                        public final /* synthetic */ TreePrinter $outer;
                                                                                                                                                        public final Trees.DefDef x6$1;

                                                                                                                                                        public final void apply() {
                                                                                                                                                            this.$outer.printOpt(": ", this.x6$1.tpt());
                                                                                                                                                        }

                                                                                                                                                        public void apply$mcV$sp() {
                                                                                                                                                            this.$outer.printOpt(": ", this.x6$1.tpt());
                                                                                                                                                        }
                                                                                                                                                        {
                                                                                                                                                            if ($outer == null) {
                                                                                                                                                                throw null;
                                                                                                                                                            }
                                                                                                                                                            this.$outer = $outer;
                                                                                                                                                            this.x6$1 = x6$1;
                                                                                                                                                        }
                                                                                                                                                    }, (Function0<BoxedUnit>)new Serializable(this, var7_11){
                                                                                                                                                        public static final long serialVersionUID = 0L;
                                                                                                                                                        public final /* synthetic */ TreePrinter $outer;
                                                                                                                                                        public final Trees.DefDef x6$1;

                                                                                                                                                        public final void apply() {
                                                                                                                                                            this.$outer.printOpt(" = ", this.x6$1.rhs());
                                                                                                                                                        }

                                                                                                                                                        public void apply$mcV$sp() {
                                                                                                                                                            this.$outer.printOpt(" = ", this.x6$1.rhs());
                                                                                                                                                        }
                                                                                                                                                        {
                                                                                                                                                            if ($outer == null) {
                                                                                                                                                                throw null;
                                                                                                                                                            }
                                                                                                                                                            this.$outer = $outer;
                                                                                                                                                            this.x6$1 = x6$1;
                                                                                                                                                        }
                                                                                                                                                    });
                                                                                                                                                    break block33;
                                                                                                                                                }
                                                                                                                                                if (!(tree instanceof Trees.TypeDef)) break block39;
                                                                                                                                                var8_12 = (Trees.TypeDef)tree;
                                                                                                                                                this.printTypeDef(var8_12, (Function0<String>)new Serializable(this, tree, var8_12){
                                                                                                                                                    public static final long serialVersionUID = 0L;
                                                                                                                                                    private final /* synthetic */ TreePrinter $outer;
                                                                                                                                                    private final Trees.Tree tree$4;
                                                                                                                                                    private final Trees.TypeDef x7$1;

                                                                                                                                                    public final String apply() {
                                                                                                                                                        return this.$outer.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(this.tree$4, this.x7$1.name());
                                                                                                                                                    }
                                                                                                                                                    {
                                                                                                                                                        if ($outer == null) {
                                                                                                                                                            throw null;
                                                                                                                                                        }
                                                                                                                                                        this.$outer = $outer;
                                                                                                                                                        this.tree$4 = tree$4;
                                                                                                                                                        this.x7$1 = x7$1;
                                                                                                                                                    }
                                                                                                                                                });
                                                                                                                                                break block33;
                                                                                                                                            }
                                                                                                                                            if (!(tree instanceof Trees.LabelDef)) break block40;
                                                                                                                                            var9_13 = (Trees.LabelDef)tree;
                                                                                                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, var9_13.name())}));
                                                                                                                                            this.printLabelParams(var9_13.params());
                                                                                                                                            this.printBlock(var9_13.rhs());
                                                                                                                                            break block33;
                                                                                                                                        }
                                                                                                                                        if (!(tree instanceof Trees.Import)) break block41;
                                                                                                                                        var10_14 = (Trees.Import)tree;
                                                                                                                                        this.printImport(var10_14, (Function0<String>)new Serializable(this, var10_14){
                                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                                            private final /* synthetic */ TreePrinter $outer;
                                                                                                                                            private final Trees.Import x9$1;

                                                                                                                                            public final String apply() {
                                                                                                                                                return this.$outer.scala$reflect$internal$Printers$TreePrinter$$$outer().backquotedPath(this.x9$1.expr());
                                                                                                                                            }
                                                                                                                                            {
                                                                                                                                                if ($outer == null) {
                                                                                                                                                    throw null;
                                                                                                                                                }
                                                                                                                                                this.$outer = $outer;
                                                                                                                                                this.x9$1 = x9$1;
                                                                                                                                            }
                                                                                                                                        });
                                                                                                                                        break block33;
                                                                                                                                    }
                                                                                                                                    if (!(tree instanceof Trees.Template)) break block42;
                                                                                                                                    var13_15 = (Trees.Template)tree;
                                                                                                                                    currentOwner1 = this.currentOwner();
                                                                                                                                    v0 = tree.symbol();
                                                                                                                                    var11_17 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().NoSymbol();
                                                                                                                                    if (v0 == null ? var11_17 != null : v0.equals(var11_17) == false) {
                                                                                                                                        this.currentOwner_$eq(tree.symbol().owner());
                                                                                                                                    }
                                                                                                                                    this.printRow(var13_15.parents(), " with ");
                                                                                                                                    if (var13_15.body().nonEmpty()) {
                                                                                                                                        v1 = var13_15.self().name();
                                                                                                                                        var12_18 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().nme().WILDCARD();
                                                                                                                                        if (!(v1 != null ? v1.equals(var12_18) == false : var12_18 != null)) {
                                                                                                                                            if (var13_15.self().tpt().nonEmpty()) {
                                                                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{" { _ : ", var13_15.self().tpt(), " => "}));
                                                                                                                                            } else {
                                                                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{" {"}));
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{" { ", var13_15.self().name()}));
                                                                                                                                            this.printOpt(": ", var13_15.self().tpt());
                                                                                                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{" => "}));
                                                                                                                                        }
                                                                                                                                        this.printColumn(var13_15.body(), "", ";", "}");
                                                                                                                                    }
                                                                                                                                    this.currentOwner_$eq(currentOwner1);
                                                                                                                                    break block33;
                                                                                                                                }
                                                                                                                                if (!(tree instanceof Trees.Block)) break block43;
                                                                                                                                var15_19 = (Trees.Block)tree;
                                                                                                                                this.printBlock(var15_19.stats(), var15_19.expr());
                                                                                                                                break block33;
                                                                                                                            }
                                                                                                                            if (!(tree instanceof Trees.Match)) break block44;
                                                                                                                            var16_20 = (Trees.Match)tree;
                                                                                                                            selectorType1 = this.selectorType();
                                                                                                                            this.selectorType_$eq(var16_20.selector().tpe());
                                                                                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var16_20.selector()}));
                                                                                                                            this.printColumn(var16_20.cases(), " match {", "", "}");
                                                                                                                            this.selectorType_$eq(selectorType1);
                                                                                                                            break block33;
                                                                                                                        }
                                                                                                                        if (!(tree instanceof Trees.CaseDef)) break block45;
                                                                                                                        var18_22 = (Trees.CaseDef)tree;
                                                                                                                        this.printCaseDef(var18_22);
                                                                                                                        break block33;
                                                                                                                    }
                                                                                                                    if (!(tree instanceof Trees.Alternative)) break block46;
                                                                                                                    var19_23 = (Trees.Alternative)tree;
                                                                                                                    this.printRow(var19_23.trees(), "(", "| ", ")");
                                                                                                                    break block33;
                                                                                                                }
                                                                                                                if (!(tree instanceof Trees.Star)) break block47;
                                                                                                                var20_24 = (Trees.Star)tree;
                                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"(", var20_24.elem(), ")*"}));
                                                                                                                break block33;
                                                                                                            }
                                                                                                            if (!(tree instanceof Trees.Bind)) break block48;
                                                                                                            var21_25 = (Trees.Bind)tree;
                                                                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"(", this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, var21_25.name()), " @ ", var21_25.body(), ")"}));
                                                                                                            break block33;
                                                                                                        }
                                                                                                        if (!(tree instanceof Trees.UnApply)) break block49;
                                                                                                        var22_26 = (Trees.UnApply)tree;
                                                                                                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var22_26.fun(), " <unapply> "}));
                                                                                                        this.printRow(var22_26.args(), "(", ", ", ")");
                                                                                                        break block33;
                                                                                                    }
                                                                                                    if (!(tree instanceof Trees.ArrayValue)) break block50;
                                                                                                    var23_27 = (Trees.ArrayValue)tree;
                                                                                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"Array[", var23_27.elemtpt()}));
                                                                                                    this.printRow(var23_27.elems(), "]{", ", ", "}");
                                                                                                    break block33;
                                                                                                }
                                                                                                if (!(tree instanceof Trees.Function)) break block51;
                                                                                                var24_28 = (Trees.Function)tree;
                                                                                                this.printFunction(var24_28, (Function0<BoxedUnit>)new Serializable(this, var24_28){
                                                                                                    public static final long serialVersionUID = 0L;
                                                                                                    private final /* synthetic */ TreePrinter $outer;
                                                                                                    private final Trees.Function x20$1;

                                                                                                    public final void apply() {
                                                                                                        this.apply$mcV$sp();
                                                                                                    }

                                                                                                    public void apply$mcV$sp() {
                                                                                                        this.$outer.printValueParams(this.x20$1.vparams(), this.$outer.printValueParams$default$2());
                                                                                                    }
                                                                                                    {
                                                                                                        if ($outer == null) {
                                                                                                            throw null;
                                                                                                        }
                                                                                                        this.$outer = $outer;
                                                                                                        this.x20$1 = x20$1;
                                                                                                    }
                                                                                                });
                                                                                                break block33;
                                                                                            }
                                                                                            if (!(tree instanceof Trees.Assign)) break block52;
                                                                                            var25_29 = (Trees.Assign)tree;
                                                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var25_29.lhs(), " = ", var25_29.rhs()}));
                                                                                            break block33;
                                                                                        }
                                                                                        if (!(tree instanceof Trees.AssignOrNamedArg)) break block53;
                                                                                        var26_30 = (Trees.AssignOrNamedArg)tree;
                                                                                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var26_30.lhs(), " = ", var26_30.rhs()}));
                                                                                        break block33;
                                                                                    }
                                                                                    if (!(tree instanceof Trees.If)) break block54;
                                                                                    var27_31 = (Trees.If)tree;
                                                                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"if (", var27_31.cond(), ")"}));
                                                                                    this.indent();
                                                                                    this.println();
                                                                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var27_31.thenp()}));
                                                                                    this.undent();
                                                                                    if (var27_31.elsep().nonEmpty()) {
                                                                                        this.println();
                                                                                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"else"}));
                                                                                        this.indent();
                                                                                        this.println();
                                                                                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var27_31.elsep()}));
                                                                                        this.undent();
                                                                                    }
                                                                                    break block33;
                                                                                }
                                                                                if (!(tree instanceof Trees.Return)) break block55;
                                                                                var28_32 = (Trees.Return)tree;
                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"return ", var28_32.expr()}));
                                                                                break block33;
                                                                            }
                                                                            if (!(tree instanceof Trees.Try)) break block56;
                                                                            var29_33 = (Trees.Try)tree;
                                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"try "}));
                                                                            this.printBlock(var29_33.block());
                                                                            if (var29_33.catches().nonEmpty()) {
                                                                                this.printColumn(var29_33.catches(), " catch {", "", "}");
                                                                            }
                                                                            this.printOpt(" finally ", var29_33.finalizer());
                                                                            break block33;
                                                                        }
                                                                        if (!(tree instanceof Trees.Throw)) break block57;
                                                                        var30_34 = (Trees.Throw)tree;
                                                                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"throw ", var30_34.expr()}));
                                                                        break block33;
                                                                    }
                                                                    if (!(tree instanceof Trees.New)) break block58;
                                                                    var31_35 = (Trees.New)tree;
                                                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"new ", var31_35.tpt()}));
                                                                    break block33;
                                                                }
                                                                if (!(tree instanceof Trees.Typed)) break block59;
                                                                var32_36 = (Trees.Typed)tree;
                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"(", var32_36.expr(), ": ", var32_36.tpt(), ")"}));
                                                                break block33;
                                                            }
                                                            if (!(tree instanceof Trees.TypeApply)) break block60;
                                                            var33_37 = (Trees.TypeApply)tree;
                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var33_37.fun()}));
                                                            this.printRow(var33_37.args(), "[", ", ", "]");
                                                            break block33;
                                                        }
                                                        if (!(tree instanceof Trees.Apply)) break block61;
                                                        var34_38 = (Trees.Apply)tree;
                                                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var34_38.fun()}));
                                                        this.printRow(var34_38.args(), "(", ", ", ")");
                                                        break block33;
                                                    }
                                                    if (!(tree instanceof Trees.ApplyDynamic)) break block62;
                                                    var35_39 = (Trees.ApplyDynamic)tree;
                                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"<apply-dynamic>(", var35_39.qual(), "#", tree.symbol().nameString()}));
                                                    this.printRow(var35_39.args(), ", (", ", ", "))");
                                                    break block33;
                                                }
                                                if (!(tree instanceof Trees.Super)) break block63;
                                                var37_2 = true;
                                                var38_3 = (Trees.Super)tree;
                                                if (!(var38_3.qual() instanceof Trees.This)) break block63;
                                                var36_40 = (Trees.This)var38_3.qual();
                                                this.printSuper(var38_3, (Function0<String>)new Serializable(this, tree, var36_40){
                                                    public static final long serialVersionUID = 0L;
                                                    private final /* synthetic */ TreePrinter $outer;
                                                    private final Trees.Tree tree$4;
                                                    private final Trees.This x34$1;

                                                    public final String apply() {
                                                        return this.$outer.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(this.tree$4, this.x34$1.qual());
                                                    }
                                                    {
                                                        if ($outer == null) {
                                                            throw null;
                                                        }
                                                        this.$outer = $outer;
                                                        this.tree$4 = tree$4;
                                                        this.x34$1 = x34$1;
                                                    }
                                                }, this.printSuper$default$3());
                                                break block33;
                                            }
                                            if (!var37_2) break block64;
                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var38_3.qual(), ".super"}));
                                            if (var38_3.mix().nonEmpty()) {
                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)"[").append(var38_3.mix()).append((Object)"]").toString()}));
                                            }
                                            break block33;
                                        }
                                        if (!(tree instanceof Trees.This)) break block65;
                                        var39_41 = (Trees.This)tree;
                                        this.printThis(var39_41, (Function0<String>)new Serializable(this, tree, var39_41){
                                            public static final long serialVersionUID = 0L;
                                            private final /* synthetic */ TreePrinter $outer;
                                            private final Trees.Tree tree$4;
                                            private final Trees.This x36$1;

                                            public final String apply() {
                                                return this.$outer.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(this.tree$4, this.x36$1.qual());
                                            }
                                            {
                                                if ($outer == null) {
                                                    throw null;
                                                }
                                                this.$outer = $outer;
                                                this.tree$4 = tree$4;
                                                this.x36$1 = x36$1;
                                            }
                                        });
                                        break block33;
                                    }
                                    if (!(tree instanceof Trees.Select)) break block66;
                                    var43_4 = true;
                                    var44_5 = (Trees.Select)tree;
                                    if (!(var44_5.qualifier() instanceof Trees.New)) break block66;
                                    var42_42 = (Trees.New)var44_5.qualifier();
                                    var41_43 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().settings().debug();
                                    var40_44 = MutableSettings$.MODULE$;
                                    if (BoxesRunTime.unboxToBoolean(var41_43.value())) break block66;
                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var42_42}));
                                    break block33;
                                }
                                if (!var43_4) break block67;
                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$Printers$TreePrinter$$$outer().backquotedPath(var44_5.qualifier()), ".", this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, var44_5.name())}));
                                break block33;
                            }
                            if (!(tree instanceof Trees.Ident)) break block68;
                            var45_45 = (Trees.Ident)tree;
                            str = this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, var45_45.name());
                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var45_45.isBackquoted() != false ? new StringBuilder().append((Object)"`").append((Object)str).append((Object)"`").toString() : str}));
                            break block33;
                        }
                        if (!(tree instanceof Trees.Literal)) break block69;
                        var47_47 = (Trees.Literal)tree;
                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var47_47.value().escapedStringValue()}));
                        break block33;
                    }
                    if (!(tree instanceof Trees.TypeTree)) break block70;
                    var48_48 = (Trees.TypeTree)tree;
                    if (tree.tpe() == null || this.printPositions() && var48_48.original() != null) {
                        if (var48_48.original() == null) {
                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"<type ?>"}));
                        } else {
                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"<type: ", var48_48.original(), ">"}));
                        }
                    } else if (tree.tpe().typeSymbol() != null && tree.tpe().typeSymbol().isAnonymousClass()) {
                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{tree.tpe().typeSymbol().toString()}));
                    } else {
                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{tree.tpe().toString()}));
                    }
                    break block33;
                }
                if (!(tree instanceof Trees.Annotated) || !((var51_49 = (Trees.Annotated)tree).annot() instanceof Trees.Apply) || !((var52_50 = (Trees.Apply)var51_49.annot()).fun() instanceof Trees.Select) || !((var49_51 = (Trees.Select)var52_50.fun()).qualifier() instanceof Trees.New)) ** GOTO lbl-1000
                var53_52 = (Trees.New)var49_51.qualifier();
                v2 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().nme().CONSTRUCTOR();
                var50_53 = var49_51.name();
                if (!(v2 != null ? v2.equals(var50_53) == false : var50_53 != null)) {
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var51_49.arg(), var51_49.arg().isType() != false ? " " : ": "}));
                    this.printAnnot$1(var52_50, var53_52);
                } else if (tree instanceof Trees.SingletonTypeTree) {
                    var54_54 = (Trees.SingletonTypeTree)tree;
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var54_54.ref(), ".type"}));
                } else if (tree instanceof Trees.SelectFromTypeTree) {
                    var55_55 = (Trees.SelectFromTypeTree)tree;
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var55_55.qualifier(), "#", this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, var55_55.name())}));
                } else if (tree instanceof Trees.CompoundTypeTree) {
                    var56_56 = (Trees.CompoundTypeTree)tree;
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var56_56.templ()}));
                } else if (tree instanceof Trees.AppliedTypeTree) {
                    var57_57 = (Trees.AppliedTypeTree)tree;
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var57_57.tpt()}));
                    this.printRow(var57_57.args(), "[", ", ", "]");
                } else if (tree instanceof Trees.TypeBoundsTree) {
                    var58_58 = (Trees.TypeBoundsTree)tree;
                    if (var58_58.lo().tpe() == null || !var58_58.lo().tpe().$eq$colon$eq(this.scala$reflect$internal$Printers$TreePrinter$$$outer().definitions().NothingTpe())) {
                        this.printOpt(" >: ", var58_58.lo());
                    }
                    if (var58_58.hi().tpe() != null && var58_58.hi().tpe().$eq$colon$eq(this.scala$reflect$internal$Printers$TreePrinter$$$outer().definitions().AnyTpe())) {
                    } else {
                        this.printOpt(" <: ", var58_58.hi());
                    }
                } else if (tree instanceof Trees.ExistentialTypeTree) {
                    var59_59 = (Trees.ExistentialTypeTree)tree;
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var59_59.tpt()}));
                    this.printColumn(var59_59.whereClauses(), " forSome { ", ";", "}");
                } else {
                    this.scala$reflect$internal$Printers$TreePrinter$$$outer().xprintTree(this, tree);
                }
            }
            this.printTypesInfo(tree);
        }

        @Override
        public void print(Seq<Object> args) {
            args.foreach(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TreePrinter $outer;

                public final void apply(Object x0$1) {
                    if (x0$1 instanceof Trees.Tree && ((Trees.Tree)x0$1).scala$reflect$internal$Trees$Tree$$$outer() == this.$outer.scala$reflect$internal$Printers$TreePrinter$$$outer()) {
                        Trees.Tree tree = (Trees.Tree)x0$1;
                        this.$outer.printPosition(tree);
                        this.$outer.printTree(tree);
                    } else if (x0$1 instanceof Names.Name && ((Names.Name)x0$1).scala$reflect$internal$Names$Name$$$outer() == this.$outer.scala$reflect$internal$Printers$TreePrinter$$$outer()) {
                        Names.Name name = (Names.Name)x0$1;
                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.$outer.scala$reflect$internal$Printers$TreePrinter$$$outer().quotedName(name)}));
                    } else {
                        this.$outer.scala$reflect$internal$Printers$TreePrinter$$out.print(x0$1 == null ? "null" : x0$1.toString());
                    }
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
        }

        public boolean printValueParams$default$2() {
            return true;
        }

        public boolean printSuper$default$3() {
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Printers$TreePrinter$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ scala.reflect.api.Printers scala$reflect$api$Printers$TreePrinter$$$outer() {
            return this.scala$reflect$internal$Printers$TreePrinter$$$outer();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private final boolean isNotRename$1(Trees.ImportSelector s2) {
            Names.Name name = s2.name();
            Names.Name name2 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().nme().WILDCARD();
            if (name == null) {
                if (name2 == null) return true;
            } else if (name.equals(name2)) return true;
            Names.Name name3 = s2.name();
            Names.Name name4 = s2.rename();
            if (name3 != null) {
                if (!name3.equals(name4)) return false;
                return true;
            }
            if (name4 == null) return true;
            return false;
        }

        public final String scala$reflect$internal$Printers$TreePrinter$$selectorToString$1(Trees.ImportSelector s2) {
            String from2 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().quotedName(s2.name());
            return this.isNotRename$1(s2) ? from2 : new StringBuilder().append((Object)from2).append((Object)"=>").append((Object)this.scala$reflect$internal$Printers$TreePrinter$$$outer().quotedName(s2.rename())).toString();
        }

        private final Trees.Tree patConstr$1(Trees.Tree pat) {
            while (pat instanceof Trees.Apply) {
                Trees.Apply apply2 = (Trees.Apply)pat;
                pat = apply2.fun();
            }
            return pat;
        }

        private final void printAnnot$1(Trees.Apply x43$1, Trees.New x48$1) {
            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"@", x48$1.tpt()}));
            if (x43$1.args().nonEmpty()) {
                this.printRow(x43$1.args(), "(", ",", ")");
            }
        }

        public TreePrinter(SymbolTable $outer, PrintWriter out) {
            this.scala$reflect$internal$Printers$TreePrinter$$out = out;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Printers$TreePrinter$class.$init$(this);
            this.indentMargin = 0;
            this.indentStep = 2;
            this.indentString = "                                        ";
            this.printTypes_$eq(BoxesRunTime.unboxToBoolean($outer.settings().printtypes().value()));
            this.printIds_$eq(BoxesRunTime.unboxToBoolean($outer.settings().uniqid().value()));
            this.printOwners_$eq(BoxesRunTime.unboxToBoolean($outer.settings().Yshowsymowners().value()));
            this.printKinds_$eq(BoxesRunTime.unboxToBoolean($outer.settings().Yshowsymkinds().value()));
            this.printMirrors_$eq(false);
            this.printPositions_$eq(BoxesRunTime.unboxToBoolean($outer.settings().Xprintpos().value()));
            this.commentsRequired = false;
            this.currentOwner = $outer.NoSymbol();
            this.selectorType = $outer.NoType();
        }
    }

    public class CodePrinter
    extends TreePrinter {
        public final boolean scala$reflect$internal$Printers$CodePrinter$$printRootPkg;
        private final Stack<Trees.Tree> parentsStack;
        private final boolean commentsRequired;
        private final List<Names.TypeName> defaultClasses;
        private final List<Names.TypeName> defaultTraitsForCase;
        private volatile Printers$CodePrinter$EmptyTypeTree$ EmptyTypeTree$module;

        private Printers$CodePrinter$EmptyTypeTree$ EmptyTypeTree$lzycompute() {
            CodePrinter codePrinter = this;
            synchronized (codePrinter) {
                if (this.EmptyTypeTree$module == null) {
                    this.EmptyTypeTree$module = new Printers$CodePrinter$EmptyTypeTree$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.EmptyTypeTree$module;
            }
        }

        public Stack<Trees.Tree> parentsStack() {
            return this.parentsStack;
        }

        public Option<Trees.Tree> currentTree() {
            return this.parentsStack().nonEmpty() ? new Some<Trees.Tree>(this.parentsStack().top()) : None$.MODULE$;
        }

        public Option<Trees.Tree> currentParent() {
            return this.parentsStack().length() > 1 ? new Some<Trees.Tree>(this.parentsStack().apply(1)) : None$.MODULE$;
        }

        public String printedName(Names.Name name, boolean decoded) {
            String decName = name.decoded();
            Serializable isDot = new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(char x) {
                    return x == '.';
                }
            };
            GenTraversable brackets = List$.MODULE$.apply(Predef$.MODULE$.wrapCharArray(new char[]{'[', ']', '(', ')', '{', '}'}));
            Names.Name name2 = name;
            Names.TermName termName = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().CONSTRUCTOR();
            return !(name2 != null ? !name2.equals(termName) : termName != null) ? "this" : this.addBackquotes$1(this.scala$reflect$internal$Printers$CodePrinter$$$outer().quotedName(name, decoded), name, decoded, decName, '\\', (Function1)((Object)isDot), (List)brackets);
        }

        public boolean printedName$default$2() {
            return true;
        }

        public boolean isIntLitWithDecodedOp(Trees.Tree qual, Names.Name name) {
            Trees.Literal literal;
            boolean bl = qual instanceof Trees.Literal && (literal = (Trees.Literal)qual).value() != null && literal.value().value() instanceof Integer;
            return bl && name.isOperatorName();
        }

        @Override
        public boolean commentsRequired() {
            return this.commentsRequired;
        }

        public boolean needsParentheses(Trees.Tree parent, boolean insideIf, boolean insideMatch, boolean insideTry, boolean insideAnnotated, boolean insideBlock, boolean insideLabelDef, boolean insideAssign) {
            boolean bl = parent instanceof Trees.If ? insideIf : (parent instanceof Trees.Match ? insideMatch : (parent instanceof Trees.Try ? insideTry : (parent instanceof Trees.Annotated ? insideAnnotated : (parent instanceof Trees.Block ? insideBlock : (parent instanceof Trees.LabelDef ? insideLabelDef : (parent instanceof Trees.Assign ? insideAssign : false))))));
            return bl;
        }

        public boolean needsParentheses$default$2(Trees.Tree parent) {
            return true;
        }

        public boolean needsParentheses$default$3(Trees.Tree parent) {
            return true;
        }

        public boolean needsParentheses$default$4(Trees.Tree parent) {
            return true;
        }

        public boolean needsParentheses$default$5(Trees.Tree parent) {
            return true;
        }

        public boolean needsParentheses$default$6(Trees.Tree parent) {
            return true;
        }

        public boolean needsParentheses$default$7(Trees.Tree parent) {
            return true;
        }

        public boolean needsParentheses$default$8(Trees.Tree parent) {
            return true;
        }

        public String checkForBlank(boolean cond) {
            return cond ? " " : "";
        }

        public String blankForOperatorName(Names.Name name) {
            return this.checkForBlank(name.isOperatorName());
        }

        public String blankForName(Names.Name name) {
            return this.checkForBlank(name.isOperatorName() || name.endsWith("_"));
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public String resolveSelect(Trees.Tree t) {
            boolean bl = false;
            Trees.Select select = null;
            if (t instanceof Trees.Select) {
                boolean x$43;
                boolean x$42;
                boolean x$41;
                boolean x$40;
                boolean x$39;
                boolean x$38;
                Trees.Tree x$36;
                bl = true;
                select = (Trees.Select)t;
                if (select.name().isTermName() && this.needsParentheses(x$36 = select.qualifier(), x$38 = this.needsParentheses$default$2(x$36), x$39 = this.needsParentheses$default$3(x$36), x$40 = this.needsParentheses$default$4(x$36), x$41 = this.needsParentheses$default$5(x$36), x$42 = this.needsParentheses$default$6(x$36), false, x$43 = this.needsParentheses$default$8(x$36)) || this.isIntLitWithDecodedOp(select.qualifier(), select.name())) {
                    return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"(", ").", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.resolveSelect(select.qualifier()), this.printedName(select.name(), this.printedName$default$2())}));
                }
            }
            if (bl && select.name().isTermName()) {
                return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", ".", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.resolveSelect(select.qualifier()), this.printedName(select.name(), this.printedName$default$2())}));
            }
            if (bl && select.name().isTypeName()) {
                return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", "#", "%", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.resolveSelect(select.qualifier()), this.blankForOperatorName(select.name()), this.printedName(select.name(), this.printedName$default$2())}));
            }
            if (!(t instanceof Trees.Ident)) return this.scala$reflect$internal$Printers$CodePrinter$$$outer().render(t, (Function1<PrintWriter, Printers.TreePrinter>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ CodePrinter $outer;

                public final CodePrinter apply(PrintWriter x$14) {
                    return new CodePrinter(this.$outer.scala$reflect$internal$Printers$CodePrinter$$$outer(), x$14, this.$outer.scala$reflect$internal$Printers$CodePrinter$$printRootPkg);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }), this.scala$reflect$internal$Printers$CodePrinter$$$outer().render$default$3(), this.scala$reflect$internal$Printers$CodePrinter$$$outer().render$default$4(), this.scala$reflect$internal$Printers$CodePrinter$$$outer().render$default$5(), this.scala$reflect$internal$Printers$CodePrinter$$$outer().render$default$6(), this.scala$reflect$internal$Printers$CodePrinter$$$outer().render$default$7(), this.scala$reflect$internal$Printers$CodePrinter$$$outer().render$default$8());
            Trees.Ident ident = (Trees.Ident)t;
            return this.printedName(ident.name(), this.printedName$default$2());
        }

        public Printers$CodePrinter$EmptyTypeTree$ EmptyTypeTree() {
            return this.EmptyTypeTree$module == null ? this.EmptyTypeTree$lzycompute() : this.EmptyTypeTree$module;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean isEmptyTree(Trees.Tree tree) {
            if (((Object)this.scala$reflect$internal$Printers$CodePrinter$$$outer().EmptyTree()).equals(tree)) {
                return true;
            }
            if (!(tree instanceof Trees.TypeTree)) return false;
            Trees.TypeTree typeTree = (Trees.TypeTree)tree;
            if (!this.EmptyTypeTree().unapply(typeTree)) return false;
            return true;
        }

        public List<Trees.Tree> originalTypeTrees(List<Trees.Tree> trees) {
            return ((List)trees.filter((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ CodePrinter $outer;

                public final boolean apply(Trees.Tree x$15) {
                    return !this.$outer.isEmptyTree(x$15);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }))).map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final Trees.Tree apply(Trees.Tree x0$2) {
                    Trees.TypeTree typeTree;
                    Trees.Tree tree = x0$2 instanceof Trees.TypeTree && (typeTree = (Trees.TypeTree)x0$2).original() != null ? typeTree.original() : x0$2;
                    return tree;
                }
            }, List$.MODULE$.canBuildFrom());
        }

        public List<Names.TypeName> defaultClasses() {
            return this.defaultClasses;
        }

        public List<Names.TypeName> defaultTraitsForCase() {
            return this.defaultTraitsForCase;
        }

        public List<Trees.Tree> removeDefaultTypesFromList(List<Trees.Tree> trees, List<Names.Name> classesToRemove, List<Names.Name> traitsToRemove) {
            return this.removeDefaultTraitsFromList$1(this.removeDefaultClassesFromList(trees, classesToRemove), traitsToRemove);
        }

        public List<Names.Name> removeDefaultTypesFromList$default$2(List<Trees.Tree> trees) {
            return this.defaultClasses();
        }

        public List<Trees.Tree> removeDefaultClassesFromList(List<Trees.Tree> trees, List<Names.Name> classesToRemove) {
            return (List)this.originalTypeTrees(trees).filter((Function1)((Object)new Serializable(this, classesToRemove){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ CodePrinter $outer;
                private final List classesToRemove$1;

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                public final boolean apply(Trees.Tree x0$3) {
                    Trees.Select select;
                    if (x0$3 instanceof Trees.Select && (select = (Trees.Select)x0$3).qualifier() instanceof Trees.Ident) {
                        Trees.Ident ident = (Trees.Ident)select.qualifier();
                        if (!this.classesToRemove$1.contains(select.name())) return true;
                        Names.Name name = ident.name();
                        Names.TermName termName = this.$outer.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().scala_();
                        if (name != null) {
                            if (!name.equals(termName)) return true;
                            return false;
                        }
                        if (termName == null) return false;
                        return true;
                    }
                    if (!(x0$3 instanceof Trees.TypeTree)) return true;
                    Trees.TypeTree typeTree = (Trees.TypeTree)x0$3;
                    if (typeTree.tpe() == null) return true;
                    if (!this.classesToRemove$1.contains(this.$outer.scala$reflect$internal$Printers$CodePrinter$$$outer().newTypeName(typeTree.tpe().toString()))) return true;
                    return false;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.classesToRemove$1 = classesToRemove$1;
                }
            }));
        }

        public List<Names.Name> removeDefaultClassesFromList$default$2() {
            return this.defaultClasses();
        }

        public boolean syntheticToRemove(Trees.Tree tree) {
            Trees.MemberDef memberDef;
            boolean bl = tree instanceof Trees.ValDef ? true : tree instanceof Trees.TypeDef;
            boolean bl2 = bl ? false : tree instanceof Trees.MemberDef && (memberDef = (Trees.MemberDef)tree).mods().isSynthetic();
            return bl2;
        }

        @Override
        public void printOpt(String prefix, Trees.Tree tree) {
            if (!this.isEmptyTree(tree)) {
                super.printOpt(prefix, tree);
            }
        }

        @Override
        public void printColumn(List<Trees.Tree> ts, String start, String sep, String end) {
            super.printColumn((List)ts.filter((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ CodePrinter $outer;

                public final boolean apply(Trees.Tree x$16) {
                    return !this.$outer.syntheticToRemove(x$16);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            })), start, sep, end);
        }

        public void printFlags(Trees.Modifiers mods, boolean primaryCtorParam) {
            String s2;
            long mask = primaryCtorParam ? 2148009007L : 0x8008042FL | 0x200L;
            String string2 = s2 = mods.flagString(mask);
            if (string2 == null || !string2.equals("")) {
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " "})).s(Predef$.MODULE$.genericWrapArray(new Object[]{s2}))}));
            }
            if (mods.isCase()) {
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)mods.flagBitsToString(2048L)).append((Object)" ").toString()}));
            }
            if (mods.isAbstractOverride()) {
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"abstract override "}));
            }
        }

        public boolean printFlags$default$2() {
            return false;
        }

        @Override
        public void printModifiers(Trees.Tree tree, Trees.Modifiers mods) {
            this.printModifiers(mods, false);
        }

        public void printModifiers(Trees.Modifiers mods, boolean primaryCtorParam) {
            if (this.currentParent().isEmpty() || this.modsAccepted$1()) {
                this.printFlags(mods, primaryCtorParam);
            } else {
                GenTraversable genTraversable = List$.MODULE$.apply(Predef$.MODULE$.wrapLongArray(new long[]{512L, 2048L, 0x80000000L, 1024L}));
                while (!((AbstractIterable)genTraversable).isEmpty()) {
                    long l = BoxesRunTime.unboxToLong(((AbstractIterable)genTraversable).head());
                    if (mods.hasFlag(l)) {
                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " "})).s(Predef$.MODULE$.genericWrapArray(new Object[]{mods.flagBitsToString(l)}))}));
                    }
                    genTraversable = (List)((AbstractTraversable)genTraversable).tail();
                }
            }
        }

        public void printParam(Trees.Tree tree, boolean primaryCtorParam) {
            if (tree instanceof Trees.ValDef) {
                boolean hideCaseCtorMods;
                Trees.ValDef valDef = (Trees.ValDef)tree;
                this.printPosition(tree);
                this.printAnnotations(valDef);
                boolean mutableOrOverride = valDef.mods().isOverride() || valDef.mods().isMutable();
                boolean hideCtorMods = valDef.mods().isParamAccessor() && valDef.mods().isPrivateLocal() && !mutableOrOverride;
                boolean bl = hideCaseCtorMods = valDef.mods().isCaseAccessor() && valDef.mods().isPublic() && !mutableOrOverride;
                if (primaryCtorParam && !hideCtorMods && !hideCaseCtorMods) {
                    this.printModifiers(valDef.mods(), primaryCtorParam);
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{valDef.mods().isMutable() ? "var " : "val "}));
                }
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.printedName(valDef.name(), this.printedName$default$2()), this.blankForName(valDef.name())}));
                this.printOpt(": ", valDef.tpt());
                this.printOpt(" = ", valDef.rhs());
            } else if (tree instanceof Trees.TypeDef) {
                Trees.TypeDef typeDef = (Trees.TypeDef)tree;
                this.printPosition(tree);
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.printedName(typeDef.name(), this.printedName$default$2())}));
                this.printTypeParams(typeDef.tparams());
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{typeDef.rhs()}));
            } else {
                super.printParam(tree);
            }
        }

        @Override
        public void printParam(Trees.Tree tree) {
            this.printParam(tree, false);
        }

        public void printArgss(List<List<Trees.Tree>> argss) {
            List list2 = argss;
            while (!((AbstractIterable)list2).isEmpty()) {
                List list3 = (List)((AbstractIterable)list2).head();
                if (!list3.isEmpty() || argss.size() != 1) {
                    this.printRow(list3, "(", ", ", ")");
                }
                list2 = (List)list2.tail();
            }
        }

        @Override
        public void printAnnotations(Trees.MemberDef tree) {
            List annots;
            List list2 = annots = tree.mods().annotations();
            while (!((AbstractIterable)list2).isEmpty()) {
                Trees.Tree tree2 = (Trees.Tree)((AbstractIterable)list2).head();
                this.printAnnot(tree2);
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{" "}));
                list2 = (List)list2.tail();
            }
        }

        public void printAnnot(Trees.Tree tree) {
            Option<Tuple3<Trees.Tree, List<Trees.Tree>, List<List<Trees.Tree>>>> option = this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().Applied().unapply(tree);
            if (option.isEmpty()) {
                super.printTree(tree);
            } else {
                Trees.Select select;
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"@"}));
                Trees.Tree tree2 = option.get()._1();
                if (tree2 instanceof Trees.Select && (select = (Trees.Select)tree2).qualifier() instanceof Trees.New) {
                    Trees.New new_ = (Trees.New)select.qualifier();
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new_.tpt()}));
                }
                this.printArgss(option.get()._3());
            }
        }

        @Override
        public void printTree(Trees.Tree tree) {
            this.parentsStack().push(tree);
            try {
                this.processTreePrinting(tree);
                this.printTypesInfo(tree);
                return;
            }
            finally {
                this.parentsStack().pop();
            }
        }

        /*
         * Unable to fully structure code
         * Could not resolve type clashes
         */
        public void processTreePrinting(Trees.Tree tree) {
            block94: {
                block135: {
                    block134: {
                        block133: {
                            block132: {
                                block131: {
                                    block130: {
                                        block129: {
                                            block125: {
                                                block127: {
                                                    block128: {
                                                        block126: {
                                                            block124: {
                                                                block123: {
                                                                    block119: {
                                                                        block122: {
                                                                            block121: {
                                                                                block120: {
                                                                                    block118: {
                                                                                        block117: {
                                                                                            block116: {
                                                                                                block115: {
                                                                                                    block114: {
                                                                                                        block113: {
                                                                                                            block108: {
                                                                                                                block109: {
                                                                                                                    block110: {
                                                                                                                        block107: {
                                                                                                                            block103: {
                                                                                                                                block104: {
                                                                                                                                    block105: {
                                                                                                                                        block106: {
                                                                                                                                            block102: {
                                                                                                                                                block101: {
                                                                                                                                                    block100: {
                                                                                                                                                        block98: {
                                                                                                                                                            block99: {
                                                                                                                                                                block97: {
                                                                                                                                                                    block96: {
                                                                                                                                                                        block95: {
                                                                                                                                                                            var157_2 = false;
                                                                                                                                                                            var166_3 = null;
                                                                                                                                                                            if (!this.syntheticToRemove(tree)) break block95;
                                                                                                                                                                            break block94;
                                                                                                                                                                        }
                                                                                                                                                                        if (!(tree instanceof Trees.ClassDef)) break block96;
                                                                                                                                                                        var19_4 = (Trees.ClassDef)tree;
                                                                                                                                                                        if (var19_4.mods().isJavaDefined()) {
                                                                                                                                                                            super.printTree(var19_4);
                                                                                                                                                                        }
                                                                                                                                                                        this.printAnnotations(var19_4);
                                                                                                                                                                        if (var19_4.mods().isTrait()) {
                                                                                                                                                                            this.printModifiers(tree, var19_4.mods().$amp$tilde(8L));
                                                                                                                                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"trait ", this.printedName(var19_4.name(), this.printedName$default$2())}));
                                                                                                                                                                            this.printTypeParams(var19_4.tparams());
                                                                                                                                                                            var2_5 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().build().SyntacticTraitDef().unapply(tree);
                                                                                                                                                                            if (var2_5.isEmpty()) {
                                                                                                                                                                                throw new MatchError(tree);
                                                                                                                                                                            }
                                                                                                                                                                            v0 = var3_6 = var2_5.get()._5();
                                                                                                                                                                        } else {
                                                                                                                                                                            this.printModifiers(tree, var19_4.mods());
                                                                                                                                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"class ", this.printedName(var19_4.name(), this.printedName$default$2())}));
                                                                                                                                                                            this.printTypeParams(var19_4.tparams());
                                                                                                                                                                            var4_7 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().build().SyntacticClassDef().unapply(var19_4);
                                                                                                                                                                            if (var4_7.isEmpty()) {
                                                                                                                                                                                throw new MatchError(var19_4);
                                                                                                                                                                            }
                                                                                                                                                                            var5_8 = new Tuple6<Trees.Modifiers, List<List<Trees.ValDef>>, List<Trees.Tree>, List<Trees.Tree>, Trees.ValDef, List<Trees.Tree>>(var4_7.get()._4(), var4_7.get()._5(), var4_7.get()._6(), var4_7.get()._7(), var4_7.get()._8(), var4_7.get()._9());
                                                                                                                                                                            ctorMods = var5_8._1();
                                                                                                                                                                            vparamss = var5_8._2();
                                                                                                                                                                            var5_8._3();
                                                                                                                                                                            parents = var5_8._4();
                                                                                                                                                                            var5_8._5();
                                                                                                                                                                            var5_8._6();
                                                                                                                                                                            if (ctorMods.hasFlag(524293L) || ctorMods.hasAccessBoundary()) {
                                                                                                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{" "}));
                                                                                                                                                                                this.printModifiers(ctorMods, false);
                                                                                                                                                                            }
                                                                                                                                                                            if ((var8_12 = Nil$.MODULE$.equals(vparamss) != false ? true : (var6_13 = List$.MODULE$.unapplySeq(vparamss)).isEmpty() == false && var6_13.get() != null && ((LinearSeqOptimized)var6_13.get()).lengthCompare(1) == 0 && Nil$.MODULE$.equals(var7_14 = (List)((LinearSeqOptimized)var6_13.get()).apply(0)) != false) && !var19_4.mods().isCase() && !ctorMods.hasFlag(524293L)) {
                                                                                                                                                                            } else {
                                                                                                                                                                                var13_15 = vparamss;
                                                                                                                                                                                while (!var13_15.isEmpty()) {
                                                                                                                                                                                    var12_16 = (List)var13_15.head();
                                                                                                                                                                                    this.scala$reflect$internal$Printers$CodePrinter$$printConstrParams$1(var12_16);
                                                                                                                                                                                    var13_15 = (List)var13_15.tail();
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                            v0 = parents;
                                                                                                                                                                        }
                                                                                                                                                                        clParents = v0;
                                                                                                                                                                        x$45 = this.removeDefaultTypesFromList$default$2(clParents);
                                                                                                                                                                        x$46 /* !! */  = var19_4.mods().hasFlag(2048L) != false ? this.defaultTraitsForCase() : Nil$.MODULE$;
                                                                                                                                                                        printedParents = this.removeDefaultTypesFromList(clParents, x$45, x$46 /* !! */ );
                                                                                                                                                                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var19_4.mods().isDeferred() != false ? "<: " : (printedParents.nonEmpty() != false ? " extends " : ""), var19_4.impl()}));
                                                                                                                                                                        break block94;
                                                                                                                                                                    }
                                                                                                                                                                    if (!(tree instanceof Trees.PackageDef)) break block97;
                                                                                                                                                                    var23_21 = (Trees.PackageDef)tree;
                                                                                                                                                                    var20_22 = var23_21.pid();
                                                                                                                                                                    if (!(var20_22 instanceof Trees.Ident)) ** GOTO lbl-1000
                                                                                                                                                                    var21_23 = (Trees.Ident)var20_22;
                                                                                                                                                                    v1 = var21_23.name();
                                                                                                                                                                    var22_24 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().EMPTY_PACKAGE_NAME();
                                                                                                                                                                    if (!(v1 != null ? v1.equals(var22_24) == false : var22_24 != null)) {
                                                                                                                                                                        this.printSeq(var23_21.stats(), new Serializable(this){
                                                                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                                                                            private final /* synthetic */ CodePrinter $outer;

                                                                                                                                                                            public final void apply(Trees.Tree x$20) {
                                                                                                                                                                                this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{x$20}));
                                                                                                                                                                            }
                                                                                                                                                                            {
                                                                                                                                                                                if ($outer == null) {
                                                                                                                                                                                    throw null;
                                                                                                                                                                                }
                                                                                                                                                                                this.$outer = $outer;
                                                                                                                                                                            }
                                                                                                                                                                        }, (Function0<BoxedUnit>)new Serializable(this){
                                                                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                                                                            public final /* synthetic */ CodePrinter $outer;

                                                                                                                                                                            public final void apply() {
                                                                                                                                                                                this.$outer.println();
                                                                                                                                                                                this.$outer.println();
                                                                                                                                                                            }

                                                                                                                                                                            public void apply$mcV$sp() {
                                                                                                                                                                                this.$outer.println();
                                                                                                                                                                                this.$outer.println();
                                                                                                                                                                            }
                                                                                                                                                                            {
                                                                                                                                                                                if ($outer == null) {
                                                                                                                                                                                    throw null;
                                                                                                                                                                                }
                                                                                                                                                                                this.$outer = $outer;
                                                                                                                                                                            }
                                                                                                                                                                        });
                                                                                                                                                                    } else lbl-1000:
                                                                                                                                                                    // 2 sources

                                                                                                                                                                    {
                                                                                                                                                                        this.printPackageDef(var23_21, Properties$.MODULE$.lineSeparator());
                                                                                                                                                                    }
                                                                                                                                                                    break block94;
                                                                                                                                                                }
                                                                                                                                                                if (!(tree instanceof Trees.ModuleDef)) break block98;
                                                                                                                                                                var27_25 = (Trees.ModuleDef)tree;
                                                                                                                                                                this.printAnnotations(var27_25);
                                                                                                                                                                this.printModifiers(tree, var27_25.mods());
                                                                                                                                                                var28_26 = var27_25.impl();
                                                                                                                                                                if (var28_26 == null) break block99;
                                                                                                                                                                var24_27 = new Tuple3<List<Trees.Tree>, Trees.ValDef, List<Trees.Tree>>(var28_26.parents(), var28_26.self(), var28_26.body());
                                                                                                                                                                parents = var24_27._1();
                                                                                                                                                                var24_27._2();
                                                                                                                                                                var24_27._3();
                                                                                                                                                                parWithoutAnyRef = this.removeDefaultClassesFromList(parents, this.removeDefaultClassesFromList$default$2());
                                                                                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)"object ").append((Object)this.printedName(var27_25.name(), this.printedName$default$2())).toString(), parWithoutAnyRef.nonEmpty() != false ? " extends " : "", var27_25.impl()}));
                                                                                                                                                                break block94;
                                                                                                                                                            }
                                                                                                                                                            throw new MatchError(var28_26);
                                                                                                                                                        }
                                                                                                                                                        if (!(tree instanceof Trees.ValDef)) break block100;
                                                                                                                                                        var29_30 = (Trees.ValDef)tree;
                                                                                                                                                        this.printValDef(var29_30, (Function0<String>)new Serializable(this, var29_30){
                                                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                                                            private final /* synthetic */ CodePrinter $outer;
                                                                                                                                                            private final Trees.ValDef x5$2;

                                                                                                                                                            public final String apply() {
                                                                                                                                                                return this.$outer.printedName(this.x5$2.name(), this.$outer.printedName$default$2());
                                                                                                                                                            }
                                                                                                                                                            {
                                                                                                                                                                if ($outer == null) {
                                                                                                                                                                    throw null;
                                                                                                                                                                }
                                                                                                                                                                this.$outer = $outer;
                                                                                                                                                                this.x5$2 = x5$2;
                                                                                                                                                            }
                                                                                                                                                        }, (Function0<BoxedUnit>)new Serializable(this, var29_30){
                                                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                                                            private final /* synthetic */ CodePrinter $outer;
                                                                                                                                                            private final Trees.ValDef x5$2;

                                                                                                                                                            public final void apply() {
                                                                                                                                                                this.apply$mcV$sp();
                                                                                                                                                            }

                                                                                                                                                            public void apply$mcV$sp() {
                                                                                                                                                                this.$outer.printOpt(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", ": "})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.$outer.blankForName(this.x5$2.name())})), this.x5$2.tpt());
                                                                                                                                                            }
                                                                                                                                                            {
                                                                                                                                                                if ($outer == null) {
                                                                                                                                                                    throw null;
                                                                                                                                                                }
                                                                                                                                                                this.$outer = $outer;
                                                                                                                                                                this.x5$2 = x5$2;
                                                                                                                                                            }
                                                                                                                                                        }, (Function0<BoxedUnit>)new Serializable(this, var29_30){
                                                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                                                            private final /* synthetic */ CodePrinter $outer;
                                                                                                                                                            private final Trees.ValDef x5$2;

                                                                                                                                                            public final void apply() {
                                                                                                                                                                this.apply$mcV$sp();
                                                                                                                                                            }

                                                                                                                                                            public void apply$mcV$sp() {
                                                                                                                                                                if (!this.x5$2.mods().isDeferred()) {
                                                                                                                                                                    this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{" = ", this.x5$2.rhs().isEmpty() ? "_" : this.x5$2.rhs()}));
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                            {
                                                                                                                                                                if ($outer == null) {
                                                                                                                                                                    throw null;
                                                                                                                                                                }
                                                                                                                                                                this.$outer = $outer;
                                                                                                                                                                this.x5$2 = x5$2;
                                                                                                                                                            }
                                                                                                                                                        });
                                                                                                                                                        break block94;
                                                                                                                                                    }
                                                                                                                                                    if (!(tree instanceof Trees.DefDef)) break block101;
                                                                                                                                                    var30_31 = (Trees.DefDef)tree;
                                                                                                                                                    this.printDefDef(var30_31, (Function0<String>)new Serializable(this, var30_31){
                                                                                                                                                        public static final long serialVersionUID = 0L;
                                                                                                                                                        private final /* synthetic */ CodePrinter $outer;
                                                                                                                                                        private final Trees.DefDef x6$2;

                                                                                                                                                        public final String apply() {
                                                                                                                                                            return this.$outer.printedName(this.x6$2.name(), this.$outer.printedName$default$2());
                                                                                                                                                        }
                                                                                                                                                        {
                                                                                                                                                            if ($outer == null) {
                                                                                                                                                                throw null;
                                                                                                                                                            }
                                                                                                                                                            this.$outer = $outer;
                                                                                                                                                            this.x6$2 = x6$2;
                                                                                                                                                        }
                                                                                                                                                    }, (Function0<BoxedUnit>)new Serializable(this, var30_31){
                                                                                                                                                        public static final long serialVersionUID = 0L;
                                                                                                                                                        private final /* synthetic */ CodePrinter $outer;
                                                                                                                                                        private final Trees.DefDef x6$2;

                                                                                                                                                        public final void apply() {
                                                                                                                                                            this.apply$mcV$sp();
                                                                                                                                                        }

                                                                                                                                                        public void apply$mcV$sp() {
                                                                                                                                                            if (this.x6$2.tparams().isEmpty() && (this.x6$2.vparamss().isEmpty() || ((SeqLike)this.x6$2.vparamss().apply(0)).isEmpty())) {
                                                                                                                                                                this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.$outer.blankForName(this.x6$2.name())}));
                                                                                                                                                            }
                                                                                                                                                            this.$outer.printOpt(": ", this.x6$2.tpt());
                                                                                                                                                        }
                                                                                                                                                        {
                                                                                                                                                            if ($outer == null) {
                                                                                                                                                                throw null;
                                                                                                                                                            }
                                                                                                                                                            this.$outer = $outer;
                                                                                                                                                            this.x6$2 = x6$2;
                                                                                                                                                        }
                                                                                                                                                    }, (Function0<BoxedUnit>)new Serializable(this, var30_31){
                                                                                                                                                        public static final long serialVersionUID = 0L;
                                                                                                                                                        private final /* synthetic */ CodePrinter $outer;
                                                                                                                                                        private final Trees.DefDef x6$2;

                                                                                                                                                        public final void apply() {
                                                                                                                                                            this.apply$mcV$sp();
                                                                                                                                                        }

                                                                                                                                                        public void apply$mcV$sp() {
                                                                                                                                                            this.$outer.printOpt(new StringBuilder().append((Object)" = ").append((Object)(this.x6$2.mods().isMacro() ? "macro " : "")).toString(), this.x6$2.rhs());
                                                                                                                                                        }
                                                                                                                                                        {
                                                                                                                                                            if ($outer == null) {
                                                                                                                                                                throw null;
                                                                                                                                                            }
                                                                                                                                                            this.$outer = $outer;
                                                                                                                                                            this.x6$2 = x6$2;
                                                                                                                                                        }
                                                                                                                                                    });
                                                                                                                                                    break block94;
                                                                                                                                                }
                                                                                                                                                if (!(tree instanceof Trees.TypeDef)) break block102;
                                                                                                                                                var31_32 = (Trees.TypeDef)tree;
                                                                                                                                                this.printTypeDef(var31_32, (Function0<String>)new Serializable(this, var31_32){
                                                                                                                                                    public static final long serialVersionUID = 0L;
                                                                                                                                                    private final /* synthetic */ CodePrinter $outer;
                                                                                                                                                    private final Trees.TypeDef x7$2;

                                                                                                                                                    public final String apply() {
                                                                                                                                                        return this.$outer.printedName(this.x7$2.name(), this.$outer.printedName$default$2());
                                                                                                                                                    }
                                                                                                                                                    {
                                                                                                                                                        if ($outer == null) {
                                                                                                                                                            throw null;
                                                                                                                                                        }
                                                                                                                                                        this.$outer = $outer;
                                                                                                                                                        this.x7$2 = x7$2;
                                                                                                                                                    }
                                                                                                                                                });
                                                                                                                                                break block94;
                                                                                                                                            }
                                                                                                                                            if (!(tree instanceof Trees.LabelDef)) break block103;
                                                                                                                                            var46_33 = (Trees.LabelDef)tree;
                                                                                                                                            if (!var46_33.name().startsWith(this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().WHILE_PREFIX(), 0)) break block104;
                                                                                                                                            var39_34 = var46_33.rhs();
                                                                                                                                            if (!(var39_34 instanceof Trees.If)) break block105;
                                                                                                                                            var32_35 = (Trees.If)var39_34;
                                                                                                                                            var33_36 = new Tuple3<Trees.Tree, Trees.Tree, Trees.Tree>(var32_35.cond(), var32_35.thenp(), var32_35.elsep());
                                                                                                                                            cond = var33_36._1();
                                                                                                                                            thenp = var33_36._2();
                                                                                                                                            var33_36._3();
                                                                                                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"while (", cond, ") "}));
                                                                                                                                            if (!(thenp instanceof Trees.Block)) break block106;
                                                                                                                                            var35_39 = (Trees.Block)thenp;
                                                                                                                                            var36_40 = new Tuple2<List<Trees.Tree>, Trees.Tree>(var35_39.stats(), var35_39.expr());
                                                                                                                                            list = var36_40._1();
                                                                                                                                            var36_40._2();
                                                                                                                                            this.printColumn(list, "", ";", "");
                                                                                                                                            break block94;
                                                                                                                                        }
                                                                                                                                        throw new MatchError(var38_38);
                                                                                                                                    }
                                                                                                                                    throw new MatchError(var39_34);
                                                                                                                                }
                                                                                                                                if (!var46_33.name().startsWith(this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().DO_WHILE_PREFIX(), 0)) ** GOTO lbl158
                                                                                                                                var45_42 = var46_33.rhs();
                                                                                                                                if (var45_42 instanceof Trees.Block && (var40_43 = (Trees.Block)var45_42).expr() instanceof Trees.If) {
                                                                                                                                    var41_44 = (Trees.If)var40_43.expr();
                                                                                                                                    var42_45 = new Tuple5<List<Trees.Tree>, Trees.If, Trees.Tree, Trees.Tree, Trees.Tree>(var40_43.stats(), var41_44, var41_44.cond(), var41_44.thenp(), var41_44.elsep());
                                                                                                                                    bodyList = var42_45._1();
                                                                                                                                    var42_45._2();
                                                                                                                                    cond = var42_45._3();
                                                                                                                                    var42_45._4();
                                                                                                                                    var42_45._5();
                                                                                                                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"do "}));
                                                                                                                                    this.printColumn(bodyList, "", ";", "");
                                                                                                                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{" while (", cond, ") "}));
                                                                                                                                } else {
                                                                                                                                    throw new MatchError(var45_42);
lbl158:
                                                                                                                                    // 1 sources

                                                                                                                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.printedName(var46_33.name(), this.printedName$default$2())}));
                                                                                                                                    this.printLabelParams(var46_33.params());
                                                                                                                                    this.printBlock(var46_33.rhs());
                                                                                                                                }
                                                                                                                                break block94;
                                                                                                                            }
                                                                                                                            if (!(tree instanceof Trees.Import)) break block107;
                                                                                                                            var47_48 = (Trees.Import)tree;
                                                                                                                            this.printImport(var47_48, (Function0<String>)new Serializable(this, var47_48){
                                                                                                                                public static final long serialVersionUID = 0L;
                                                                                                                                private final /* synthetic */ CodePrinter $outer;
                                                                                                                                private final Trees.Import x9$2;

                                                                                                                                public final String apply() {
                                                                                                                                    return this.$outer.resolveSelect(this.x9$2.expr());
                                                                                                                                }
                                                                                                                                {
                                                                                                                                    if ($outer == null) {
                                                                                                                                        throw null;
                                                                                                                                    }
                                                                                                                                    this.$outer = $outer;
                                                                                                                                    this.x9$2 = x9$2;
                                                                                                                                }
                                                                                                                            });
                                                                                                                            break block94;
                                                                                                                        }
                                                                                                                        if (!(tree instanceof Trees.Template)) break block108;
                                                                                                                        var87_49 = (Trees.Template)tree;
                                                                                                                        body = this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().untypecheckedTemplBody(var87_49);
                                                                                                                        var48_51 = this.currentParent();
                                                                                                                        if (!var48_51.isEmpty()) {
                                                                                                                            var50_52 = var48_51.get();
                                                                                                                            if (var50_52 instanceof Trees.CompoundTypeTree) {
                                                                                                                                var55_53 = var87_49.parents();
                                                                                                                            } else if (var50_52 instanceof Trees.ClassDef && (x21 = (Trees.ClassDef)var50_52).mods().isCase()) {
                                                                                                                                x$471 = var87_49.parents();
                                                                                                                                x$481 = this.removeDefaultTypesFromList$default$2(x$471);
                                                                                                                                x$491 = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Names.TypeName[]{this.scala$reflect$internal$Printers$CodePrinter$$$outer().tpnme().Product(), this.scala$reflect$internal$Printers$CodePrinter$$$outer().tpnme().Serializable()}));
                                                                                                                                var55_53 = this.removeDefaultTypesFromList(x$471, x$481, (List<Names.Name>)x$491);
                                                                                                                            } else {
                                                                                                                                var55_53 = this.removeDefaultClassesFromList(var87_49.parents(), this.removeDefaultClassesFromList$default$2());
                                                                                                                            }
                                                                                                                            v2 = new Some<List<Trees.Tree>>(var55_53);
                                                                                                                            v3 = v2;
                                                                                                                        } else {
                                                                                                                            v3 = None$.MODULE$;
                                                                                                                        }
                                                                                                                        var56_58 = v3;
                                                                                                                        printedParents = (List)(v3.isEmpty() == false ? var56_58.get() : var87_49.parents());
                                                                                                                        primaryCtr = this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().firstConstructor(body);
                                                                                                                        if (primaryCtr instanceof Trees.DefDef && (var58_61 = (Trees.DefDef)primaryCtr).rhs() instanceof Trees.Block) {
                                                                                                                            var61_62 = (Trees.Block)var58_61.rhs();
                                                                                                                            var59_63 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().preSuperFields(var61_62.stats());
                                                                                                                            earlyDefs = ((List)body.filter((Function1)new Serializable(this){
                                                                                                                                public static final long serialVersionUID = 0L;
                                                                                                                                private final /* synthetic */ CodePrinter $outer;

                                                                                                                                public final boolean apply(Trees.Tree x0$6) {
                                                                                                                                    boolean bl;
                                                                                                                                    if (x0$6 instanceof Trees.TypeDef) {
                                                                                                                                        Trees.TypeDef typeDef = (Trees.TypeDef)x0$6;
                                                                                                                                        bl = this.$outer.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().isEarlyDef(typeDef);
                                                                                                                                    } else {
                                                                                                                                        bl = false;
                                                                                                                                    }
                                                                                                                                    return bl;
                                                                                                                                }
                                                                                                                                {
                                                                                                                                    if ($outer == null) {
                                                                                                                                        throw null;
                                                                                                                                    }
                                                                                                                                    this.$outer = $outer;
                                                                                                                                }
                                                                                                                            })).$colon$colon$colon(var59_63);
                                                                                                                            if (earlyDefs.nonEmpty()) {
                                                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"{"}));
                                                                                                                                this.printColumn(earlyDefs, "", ";", "");
                                                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)"} ").append((Object)(printedParents.nonEmpty() != false ? "with " : "")).toString()}));
                                                                                                                            }
                                                                                                                            var65_65 /* !! */  = var61_62.stats().collectFirst(new Serializable(this){
                                                                                                                                public static final long serialVersionUID = 0L;

                                                                                                                                public final <A1 extends Trees.Tree, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                                                                                                                                    Object object;
                                                                                                                                    if (x1 instanceof Trees.Apply) {
                                                                                                                                        Trees.Apply apply2 = (Trees.Apply)x1;
                                                                                                                                        object = apply2;
                                                                                                                                    } else {
                                                                                                                                        object = function1.apply(x1);
                                                                                                                                    }
                                                                                                                                    return object;
                                                                                                                                }

                                                                                                                                public final boolean isDefinedAt(Trees.Tree x1) {
                                                                                                                                    boolean bl = x1 instanceof Trees.Apply;
                                                                                                                                    return bl;
                                                                                                                                }
                                                                                                                            });
                                                                                                                        } else {
                                                                                                                            var65_65 /* !! */  = None$.MODULE$;
                                                                                                                        }
                                                                                                                        if (!printedParents.nonEmpty()) break block109;
                                                                                                                        if (!(printedParents instanceof $colon$colon)) break block110;
                                                                                                                        var62_66 = ($colon$colon)printedParents;
                                                                                                                        var63_67 = new Tuple2<B, List<B>>(var62_66.head(), var62_66.tl$1());
                                                                                                                        clParent = (Trees.Tree)var63_67._1();
                                                                                                                        traits = var63_67._2();
                                                                                                                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{clParent}));
                                                                                                                        if (!(var65_65 /* !! */  instanceof Some)) ** GOTO lbl-1000
                                                                                                                        var66_70 = (Some)var65_65 /* !! */ ;
                                                                                                                        var68_71 = (Trees.Tree)var66_70.x();
                                                                                                                        var67_72 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().Applied();
                                                                                                                        var69_73 = var67_72.unapply(var67_72.$outer.dissectApplied(var68_71));
                                                                                                                        if (!var69_73.isEmpty()) {
                                                                                                                            var70_74 /* !! */  = var69_73.get()._3();
                                                                                                                        } else lbl-1000:
                                                                                                                        // 2 sources

                                                                                                                        {
                                                                                                                            var70_74 /* !! */  = Nil$.MODULE$;
                                                                                                                        }
                                                                                                                        this.printArgss(var70_74 /* !! */ );
                                                                                                                        if (traits.nonEmpty()) {
                                                                                                                            this.printRow(traits, " with ", " with ", "");
                                                                                                                        }
                                                                                                                        break block109;
                                                                                                                    }
                                                                                                                    throw new MatchError(printedParents);
                                                                                                                }
                                                                                                                var74_75 = (List)body.filter((Function1)new Serializable(this){
                                                                                                                    public static final long serialVersionUID = 0L;
                                                                                                                    private final /* synthetic */ CodePrinter $outer;

                                                                                                                    public final boolean apply(Trees.Tree x0$7) {
                                                                                                                        boolean bl;
                                                                                                                        if (x0$7 instanceof Trees.ValDef) {
                                                                                                                            Trees.ValDef valDef = (Trees.ValDef)x0$7;
                                                                                                                            bl = !valDef.mods().isParamAccessor() && !this.$outer.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().isEarlyValDef(valDef);
                                                                                                                        } else if (x0$7 instanceof Trees.DefDef) {
                                                                                                                            Trees.DefDef defDef = (Trees.DefDef)x0$7;
                                                                                                                            Names.TermName termName = defDef.name();
                                                                                                                            Names.TermName termName2 = this.$outer.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().MIXIN_CONSTRUCTOR();
                                                                                                                            bl = termName != null ? !termName.equals(termName2) : termName2 != null;
                                                                                                                        } else if (x0$7 instanceof Trees.TypeDef) {
                                                                                                                            Trees.TypeDef typeDef = (Trees.TypeDef)x0$7;
                                                                                                                            bl = !this.$outer.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().isEarlyDef(typeDef);
                                                                                                                        } else {
                                                                                                                            bl = !((Object)this.$outer.scala$reflect$internal$Printers$CodePrinter$$$outer().EmptyTree()).equals(x0$7);
                                                                                                                        }
                                                                                                                        return bl;
                                                                                                                    }
                                                                                                                    {
                                                                                                                        if ($outer == null) {
                                                                                                                            throw null;
                                                                                                                        }
                                                                                                                        this.$outer = $outer;
                                                                                                                    }
                                                                                                                });
                                                                                                                var89_76 = new ListBuffer<A>();
                                                                                                                var90_77 = var74_75;
                                                                                                                while (true) {
                                                                                                                    block112: {
                                                                                                                        block111: {
                                                                                                                            if (var90_77.isEmpty()) break block111;
                                                                                                                            var76_78 = (Trees.Tree)var90_77.head();
                                                                                                                            if (var76_78 instanceof Trees.DefDef) {
                                                                                                                                x22 = (Trees.DefDef)var76_78;
                                                                                                                                v4 = x22.name();
                                                                                                                                var78_80 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().CONSTRUCTOR();
                                                                                                                                var79_81 = v4 != null ? v4.equals(var78_80) == false : var78_80 != null;
                                                                                                                            } else {
                                                                                                                                var79_81 = true;
                                                                                                                            }
                                                                                                                            if (var79_81) break block112;
                                                                                                                        }
                                                                                                                        var80_82 = new Tuple2<List<A>, List>(var89_76.toList(), var90_77);
                                                                                                                        var81_83 = new Tuple2<List<A>, List>(var80_82._1(), var80_82._2());
                                                                                                                        left = var81_83._1();
                                                                                                                        right = var81_83._2();
                                                                                                                        modBody = right.drop(1).$colon$colon$colon(left);
                                                                                                                        if (!modBody.isEmpty()) ** GOTO lbl-1000
                                                                                                                        v5 = var87_49.self();
                                                                                                                        var84_87 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().noSelfType();
                                                                                                                        if (!(v5 == null ? var84_87 != null : v5.equals(var84_87) == false) || var87_49.self().isEmpty()) {
                                                                                                                            v6 = false;
                                                                                                                        } else lbl-1000:
                                                                                                                        // 2 sources

                                                                                                                        {
                                                                                                                            v6 = showBody = true;
                                                                                                                        }
                                                                                                                        if (showBody) {
                                                                                                                            v7 = var87_49.self().name();
                                                                                                                            var86_89 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().WILDCARD();
                                                                                                                            if (!(v7 != null ? v7.equals(var86_89) == false : var86_89 != null)) {
                                                                                                                                if (var87_49.self().tpt().nonEmpty()) {
                                                                                                                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{" { _ : ", var87_49.self().tpt(), " =>"}));
                                                                                                                                } else {
                                                                                                                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{" {"}));
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{" { ", var87_49.self().name()}));
                                                                                                                                this.printOpt(": ", var87_49.self().tpt());
                                                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{" =>"}));
                                                                                                                            }
                                                                                                                            this.printColumn(modBody, "", ";", "}");
                                                                                                                        }
                                                                                                                        break block94;
                                                                                                                    }
                                                                                                                    var89_76.$plus$eq((Object)var90_77.head());
                                                                                                                    var90_77 = (List)var90_77.tail();
                                                                                                                }
                                                                                                            }
                                                                                                            if (!(tree instanceof Trees.Block)) break block113;
                                                                                                            var91_90 = (Trees.Block)tree;
                                                                                                            this.printBlock(this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().untypecheckedBlockBody(var91_90), var91_90.expr());
                                                                                                            break block94;
                                                                                                        }
                                                                                                        if (!(tree instanceof Trees.Match)) break block114;
                                                                                                        var102_91 = (Trees.Match)tree;
                                                                                                        x$50 = var102_91.selector();
                                                                                                        x$52 = this.needsParentheses$default$2(x$50);
                                                                                                        x$53 = this.needsParentheses$default$3(x$50);
                                                                                                        x$54 = this.needsParentheses$default$4(x$50);
                                                                                                        x$55 = this.needsParentheses$default$5(x$50);
                                                                                                        x$56 = this.needsParentheses$default$6(x$50);
                                                                                                        x$57 = this.needsParentheses$default$8(x$50);
                                                                                                        printParentheses = this.needsParentheses(x$50, x$52, x$53, x$54, x$55, x$56, false, x$57);
                                                                                                        if (!(tree instanceof Trees.Match)) ** GOTO lbl-1000
                                                                                                        var99_100 = (Trees.Match)tree;
                                                                                                        if (this.scala$reflect$internal$Printers$CodePrinter$$$outer().EmptyTree().equals(var99_100.selector())) {
                                                                                                            this.printColumn(var102_91.cases(), "{", "", "}");
                                                                                                        } else lbl-1000:
                                                                                                        // 2 sources

                                                                                                        {
                                                                                                            var101_101 = new Serializable(this, printParentheses, var102_91){
                                                                                                                public static final long serialVersionUID = 0L;
                                                                                                                public final /* synthetic */ CodePrinter $outer;
                                                                                                                public final boolean printParentheses$1;
                                                                                                                public final Trees.Match x13$1;

                                                                                                                public final void apply() {
                                                                                                                    this.apply$mcV$sp();
                                                                                                                }

                                                                                                                public void apply$mcV$sp() {
                                                                                                                    this.$outer.parenthesize(this.printParentheses$1, this.$outer.parenthesize$default$2(), this.$outer.parenthesize$default$3(), (Function0<BoxedUnit>)((Object)new Serializable(this){
                                                                                                                        public static final long serialVersionUID = 0L;
                                                                                                                        private final /* synthetic */ CodePrinter$$anonfun$processTreePrinting$6 $outer;

                                                                                                                        public final void apply() {
                                                                                                                            this.apply$mcV$sp();
                                                                                                                        }

                                                                                                                        public void apply$mcV$sp() {
                                                                                                                            this.$outer.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.$outer.x13$1.selector()}));
                                                                                                                        }
                                                                                                                        {
                                                                                                                            if ($outer == null) {
                                                                                                                                throw null;
                                                                                                                            }
                                                                                                                            this.$outer = $outer;
                                                                                                                        }
                                                                                                                    }));
                                                                                                                    this.$outer.printColumn(this.x13$1.cases(), " match {", "", "}");
                                                                                                                }

                                                                                                                public /* synthetic */ CodePrinter scala$reflect$internal$Printers$CodePrinter$$anonfun$$$outer() {
                                                                                                                    return this.$outer;
                                                                                                                }
                                                                                                                {
                                                                                                                    if ($outer == null) {
                                                                                                                        throw null;
                                                                                                                    }
                                                                                                                    this.$outer = $outer;
                                                                                                                    this.printParentheses$1 = printParentheses$1;
                                                                                                                    this.x13$1 = x13$1;
                                                                                                                }
                                                                                                            };
                                                                                                            if (this.parentsStack().nonEmpty() && ((IterableLike)this.parentsStack().tail()).exists(new Serializable(this){
                                                                                                                public static final long serialVersionUID = 0L;

                                                                                                                public final boolean apply(Trees.Tree x$29) {
                                                                                                                    return x$29 instanceof Trees.Match;
                                                                                                                }
                                                                                                            })) {
                                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"("}));
                                                                                                                this.parenthesize(printParentheses, this.parenthesize$default$2(), this.parenthesize$default$3(), (Function0<BoxedUnit>)new /* invalid duplicate definition of identical inner class */);
                                                                                                                this.printColumn(var102_91.cases(), " match {", "", "}");
                                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{")"}));
                                                                                                            } else {
                                                                                                                this.parenthesize(printParentheses, this.parenthesize$default$2(), this.parenthesize$default$3(), (Function0<BoxedUnit>)new /* invalid duplicate definition of identical inner class */);
                                                                                                                this.printColumn(var102_91.cases(), " match {", "", "}");
                                                                                                            }
                                                                                                        }
                                                                                                        break block94;
                                                                                                    }
                                                                                                    if (!(tree instanceof Trees.CaseDef)) break block115;
                                                                                                    var103_102 = (Trees.CaseDef)tree;
                                                                                                    this.printCaseDef(var103_102);
                                                                                                    break block94;
                                                                                                }
                                                                                                if (!(tree instanceof Trees.Star)) break block116;
                                                                                                var104_103 = (Trees.Star)tree;
                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var104_103.elem(), "*"}));
                                                                                                break block94;
                                                                                            }
                                                                                            if (!(tree instanceof Trees.Bind)) break block117;
                                                                                            var106_104 = (Trees.Bind)tree;
                                                                                            v8 = var106_104.body();
                                                                                            var105_105 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().EmptyTree();
                                                                                            if (!(v8 != null ? v8.equals(var105_105) == false : var105_105 != null)) {
                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"(", this.printedName(var106_104.name(), this.printedName$default$2()), ")"}));
                                                                                            } else if (var106_104.body().exists((Function1<Trees.Tree, Object>)new Serializable(this){
                                                                                                public static final long serialVersionUID = 0L;

                                                                                                public final boolean apply(Trees.Tree x$30) {
                                                                                                    return x$30 instanceof Trees.Star;
                                                                                                }
                                                                                            })) {
                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.printedName(var106_104.name(), this.printedName$default$2()), " @ ", var106_104.body()}));
                                                                                            } else {
                                                                                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"(", this.printedName(var106_104.name(), this.printedName$default$2()), " @ ", var106_104.body(), ")"}));
                                                                                            }
                                                                                            break block94;
                                                                                        }
                                                                                        if (!(tree instanceof Trees.Function)) break block118;
                                                                                        var110_106 = (Trees.Function)tree;
                                                                                        var107_107 = var110_106.vparams();
                                                                                        var109_109 = var107_107 instanceof $colon$colon != false ? ((Trees.ValDef)(var108_108 = ($colon$colon)var107_107).head()).mods().isImplicit() == false : true;
                                                                                        this.printFunction(var110_106, (Function0<BoxedUnit>)new Serializable(this, var109_109, var110_106){
                                                                                            public static final long serialVersionUID = 0L;
                                                                                            public final /* synthetic */ CodePrinter $outer;
                                                                                            public final boolean printParentheses$2;
                                                                                            public final Trees.Function x17$1;

                                                                                            public final void apply() {
                                                                                                this.$outer.printValueParams(this.x17$1.vparams(), this.printParentheses$2);
                                                                                            }

                                                                                            public void apply$mcV$sp() {
                                                                                                this.$outer.printValueParams(this.x17$1.vparams(), this.printParentheses$2);
                                                                                            }
                                                                                            {
                                                                                                if ($outer == null) {
                                                                                                    throw null;
                                                                                                }
                                                                                                this.$outer = $outer;
                                                                                                this.printParentheses$2 = printParentheses$2;
                                                                                                this.x17$1 = x17$1;
                                                                                            }
                                                                                        });
                                                                                        break block94;
                                                                                    }
                                                                                    if (!(tree instanceof Trees.Typed)) break block119;
                                                                                    var118_110 = (Trees.Typed)tree;
                                                                                    var115_111 = var118_110.tpt();
                                                                                    if (!this.scala$reflect$internal$Printers$CodePrinter$$$outer().EmptyTree().equals(var115_111)) break block120;
                                                                                    var112_112 = true;
                                                                                    break block121;
                                                                                }
                                                                                if (!(var115_111 instanceof Trees.TypeTree)) ** GOTO lbl-1000
                                                                                var111_113 = (Trees.TypeTree)var115_111;
                                                                                if (this.EmptyTypeTree().unapply(var111_113)) {
                                                                                    var112_112 = true;
                                                                                } else lbl-1000:
                                                                                // 2 sources

                                                                                {
                                                                                    var112_112 = false;
                                                                                }
                                                                            }
                                                                            if (!var112_112) break block122;
                                                                            this.printTp$1(var118_110);
                                                                            break block94;
                                                                        }
                                                                        if (!(var115_111 instanceof Trees.Annotated)) ** GOTO lbl-1000
                                                                        var113_114 = (Trees.Annotated)var115_111;
                                                                        if (var118_110.expr() != null && var113_114.arg() != null && var118_110.expr().equalsStructure(var113_114.arg())) {
                                                                            this.printTp$1(var118_110);
                                                                        } else if (var115_111 instanceof Trees.TypeTree && (var114_115 = (Trees.TypeTree)var115_111).original() instanceof Trees.Annotated) {
                                                                            this.printTp$1(var118_110);
                                                                        } else if (var115_111 instanceof Trees.Function && !(var116_117 = List$.MODULE$.unapplySeq((var117_116 = (Trees.Function)var115_111).vparams())).isEmpty() && var116_117.get() != null && ((LinearSeqOptimized)var116_117.get()).lengthCompare(0) == 0 && this.scala$reflect$internal$Printers$CodePrinter$$$outer().EmptyTree().equals(var117_116.body())) {
                                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"(", var118_110.expr(), " _)"}));
                                                                        } else {
                                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"((", var118_110.expr(), "): ", var118_110.tpt(), ")"}));
                                                                        }
                                                                        break block94;
                                                                    }
                                                                    if (!(tree instanceof Trees.TypeApply)) break block123;
                                                                    var119_118 = (Trees.TypeApply)tree;
                                                                    if (var119_118.args().exists((Function1<Trees.Tree, Object>)new Serializable(this){
                                                                        public static final long serialVersionUID = 0L;
                                                                        private final /* synthetic */ CodePrinter $outer;

                                                                        public final boolean apply(Trees.Tree x$31) {
                                                                            return this.$outer.isEmptyTree(x$31);
                                                                        }
                                                                        {
                                                                            if ($outer == null) {
                                                                                throw null;
                                                                            }
                                                                            this.$outer = $outer;
                                                                        }
                                                                    })) {
                                                                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var119_118.fun()}));
                                                                    } else {
                                                                        super.printTree(tree);
                                                                    }
                                                                    break block94;
                                                                }
                                                                if (!(tree instanceof Trees.Apply)) break block124;
                                                                var140_119 = (Trees.Apply)tree;
                                                                var131_120 = false;
                                                                var132_121 = null;
                                                                if (!(tree instanceof Trees.Apply)) ** GOTO lbl-1000
                                                                var131_120 = true;
                                                                var132_121 = (Trees.Apply)tree;
                                                                if (!(var132_121.fun() instanceof Trees.Block) || (var120_123 = List$.MODULE$.unapplySeq((var128_122 = (Trees.Block)var132_121.fun()).stats())).isEmpty() || var120_123.get() == null || ((LinearSeqOptimized)var120_123.get()).lengthCompare(1) != 0 || !((sVD = (Trees.Tree)((LinearSeqOptimized)var120_123.get()).apply(0)) instanceof Trees.ValDef)) ** GOTO lbl-1000
                                                                var125_125 = (Trees.ValDef)sVD;
                                                                if (!(var128_122.expr() instanceof Trees.Apply) || !((var129_126 = (Trees.Apply)var128_122.expr()).fun() instanceof Trees.Select)) ** GOTO lbl-1000
                                                                var124_127 = (Trees.Select)var129_126.fun();
                                                                var122_128 = List$.MODULE$.unapplySeq(var129_126.args());
                                                                if (var122_128.isEmpty() || var122_128.get() == null || ((LinearSeqOptimized)var122_128.get()).lengthCompare(1) != 0 || !((var123_129 = (Trees.Tree)((LinearSeqOptimized)var122_128.get()).apply(0)) instanceof Trees.Ident)) ** GOTO lbl-1000
                                                                var126_130 = (Trees.Ident)var123_129;
                                                                if (!var125_125.mods().isSynthetic() || !this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().isLeftAssoc(var124_127.name())) ** GOTO lbl-1000
                                                                v9 = var125_125.name();
                                                                var127_131 = var126_130.name();
                                                                if (!(v9 != null ? v9.equals(var127_131) == false : var127_131 != null)) {
                                                                    printBlock = new Trees.Block(this.scala$reflect$internal$Printers$CodePrinter$$$outer(), var128_122.stats(), new Trees.Apply(this.scala$reflect$internal$Printers$CodePrinter$$$outer(), var129_126, var132_121.args()));
                                                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{printBlock}));
                                                                } else if (var131_120 && this.needsParentheses(x$58 = var132_121.fun(), x$60 = this.needsParentheses$default$2(x$58), x$61 = this.needsParentheses$default$3(x$58), x$62 = this.needsParentheses$default$4(x$58), false, x$63 = this.needsParentheses$default$6(x$58), x$64 = this.needsParentheses$default$7(x$58), x$65 = this.needsParentheses$default$8(x$58))) {
                                                                    this.parenthesize(this.parenthesize$default$1(), this.parenthesize$default$2(), this.parenthesize$default$3(), (Function0<BoxedUnit>)new Serializable(this, var140_119){
                                                                        public static final long serialVersionUID = 0L;
                                                                        private final /* synthetic */ CodePrinter $outer;
                                                                        private final Trees.Apply x20$2;

                                                                        public final void apply() {
                                                                            this.apply$mcV$sp();
                                                                        }

                                                                        public void apply$mcV$sp() {
                                                                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.x20$2.fun()}));
                                                                        }
                                                                        {
                                                                            if ($outer == null) {
                                                                                throw null;
                                                                            }
                                                                            this.$outer = $outer;
                                                                            this.x20$2 = x20$2;
                                                                        }
                                                                    });
                                                                    this.printRow(var140_119.args(), "(", ", ", ")");
                                                                } else {
                                                                    super.printTree(tree);
                                                                }
                                                                break block94;
                                                            }
                                                            if (!(tree instanceof Trees.UnApply)) break block125;
                                                            var150_140 = (Trees.UnApply)tree;
                                                            var141_141 = var150_140.fun();
                                                            var149_142 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().Unapplied().unapply(var141_141);
                                                            if (!var149_142.isEmpty()) break block126;
                                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var150_140.fun()}));
                                                            break block127;
                                                        }
                                                        var144_143 = var149_142.get();
                                                        if (!(var144_143 instanceof Trees.Select)) break block128;
                                                        var143_144 = (Trees.Select)var144_143;
                                                        v10 = var143_144.name();
                                                        var142_145 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().unapply();
                                                        if (v10 != null ? v10.equals(var142_145) == false : var142_145 != null) break block128;
                                                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var143_144.qualifier()}));
                                                        break block127;
                                                    }
                                                    if (!(var144_143 instanceof Trees.TypeApply) || !((var145_146 = (Trees.TypeApply)var144_143).fun() instanceof Trees.Select)) ** GOTO lbl-1000
                                                    var148_147 = (Trees.Select)var145_146.fun();
                                                    v11 = var148_147.name();
                                                    var146_148 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().unapply();
                                                    if (!(v11 == null ? var146_148 != null : v11.equals(var146_148) == false)) ** GOTO lbl-1000
                                                    v12 = var148_147.name();
                                                    var147_149 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().unapplySeq();
                                                    if (!(v12 != null ? v12.equals(var147_149) == false : var147_149 != null)) lbl-1000:
                                                    // 2 sources

                                                    {
                                                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var148_147.qualifier()}));
                                                    } else lbl-1000:
                                                    // 2 sources

                                                    {
                                                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var149_142.get()}));
                                                    }
                                                }
                                                this.printRow(var150_140.args(), "(", ", ", ")");
                                                break block94;
                                            }
                                            if (!(tree instanceof Trees.Super) || !((var151_150 = (Trees.Super)tree).qual() instanceof Trees.This)) break block129;
                                            var152_151 = (Trees.This)var151_150.qual();
                                            this.printSuper(var151_150, (Function0<String>)new Serializable(this, var152_151){
                                                public static final long serialVersionUID = 0L;
                                                private final /* synthetic */ CodePrinter $outer;
                                                private final Trees.This x24$1;

                                                public final String apply() {
                                                    return this.$outer.printedName(this.x24$1.qual(), this.$outer.printedName$default$2());
                                                }
                                                {
                                                    if ($outer == null) {
                                                        throw null;
                                                    }
                                                    this.$outer = $outer;
                                                    this.x24$1 = x24$1;
                                                }
                                            }, false);
                                            break block94;
                                        }
                                        if (!(tree instanceof Trees.This)) break block130;
                                        var153_152 = (Trees.This)tree;
                                        if (tree.hasExistingSymbol() && tree.symbol().hasPackageFlag()) {
                                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{tree.symbol().fullName('.')}));
                                        } else {
                                            this.printThis(var153_152, (Function0<String>)new Serializable(this, var153_152){
                                                public static final long serialVersionUID = 0L;
                                                private final /* synthetic */ CodePrinter $outer;
                                                private final Trees.This x25$1;

                                                public final String apply() {
                                                    return this.$outer.printedName(this.x25$1.qual(), this.$outer.printedName$default$2());
                                                }
                                                {
                                                    if ($outer == null) {
                                                        throw null;
                                                    }
                                                    this.$outer = $outer;
                                                    this.x25$1 = x25$1;
                                                }
                                            });
                                        }
                                        break block94;
                                    }
                                    if (!(tree instanceof Trees.Select)) break block131;
                                    var157_2 = true;
                                    var166_3 = (Trees.Select)tree;
                                    if (!(var166_3.qualifier() instanceof Trees.This)) break block131;
                                    v13 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().CONSTRUCTOR();
                                    var154_153 = var166_3.name();
                                    if (v13 != null ? v13.equals(var154_153) == false : var154_153 != null) break block131;
                                    var155_154 = var166_3.name();
                                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.printedName(var155_154, this.printedName$default$2())}));
                                    break block94;
                                }
                                if (!var157_2 || !(var166_3.qualifier() instanceof Trees.New)) break block132;
                                var156_155 = (Trees.New)var166_3.qualifier();
                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var156_155}));
                                break block94;
                            }
                            if (!var157_2) break block133;
                            if (this.scala$reflect$internal$Printers$CodePrinter$$printRootPkg && this.checkRootPackage$1(tree)) {
                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", "."})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.printedName(this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().ROOTPKG(), this.printedName$default$2())}))}));
                            }
                            v14 = printParentheses = this.needsParentheses(x$66 = var166_3.qualifier(), x$68 = this.needsParentheses$default$2(x$66), x$69 = this.needsParentheses$default$3(x$66), x$70 = this.needsParentheses$default$4(x$66), false, x$71 = this.needsParentheses$default$6(x$66), x$72 = this.needsParentheses$default$7(x$66), x$73 = this.needsParentheses$default$8(x$66)) != false || this.isIntLitWithDecodedOp(var166_3.qualifier(), var166_3.name()) != false;
                            if (printParentheses) {
                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"(", this.resolveSelect(var166_3.qualifier()), ").", this.printedName(var166_3.name(), this.printedName$default$2())}));
                            } else {
                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.resolveSelect(var166_3.qualifier()), ".", this.printedName(var166_3.name(), this.printedName$default$2())}));
                            }
                            break block94;
                        }
                        if (!(tree instanceof Trees.Ident)) break block134;
                        var168_164 = (Trees.Ident)tree;
                        if (var168_164.name().nonEmpty()) {
                            v15 = var168_164.name();
                            var167_165 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().dollarScope();
                            if (!(v15 != null ? v15.equals(var167_165) == false : var167_165 != null)) {
                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"scala.xml.", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().TopScope()}))}));
                            } else {
                                str = this.printedName(var168_164.name(), this.printedName$default$2());
                                strIsBackquoted = str.startsWith("`") != false && str.endsWith("`") != false;
                                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var168_164.isBackquoted() != false && strIsBackquoted == false ? new StringBuilder().append((Object)"`").append((Object)str).append((Object)"`").toString() : str}));
                            }
                        } else {
                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{""}));
                        }
                        break block94;
                    }
                    if (!(tree instanceof Trees.Literal)) break block135;
                    var179_168 = (Trees.Literal)tree;
                    var171_169 = var179_168.value();
                    if (var171_169 == null || !(var171_169.value() instanceof String)) ** GOTO lbl-1000
                    strValue = var179_168.value().stringValue();
                    var172_171 = Predef$.MODULE$;
                    if (!new StringOps(strValue).contains(BoxesRunTime.boxToCharacter('\n')) || strValue.contains("\"\"\"")) ** GOTO lbl-1000
                    var173_172 = Predef$.MODULE$;
                    if (new StringOps(strValue).size() > 1) {
                        v16 = true;
                    } else lbl-1000:
                    // 2 sources

                    {
                        v16 = false;
                    }
                    if (v16) {
                        var176_173 = var179_168.value().stringValue().split(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToCharacter('\n')})));
                        var175_174 = Predef$.MODULE$;
                        splitValue = new ArrayOps.ofRef<Object>(var176_173).toList();
                        multilineStringValue = var179_168.value().stringValue().endsWith(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToCharacter('\n')}))) != false ? splitValue.$colon$plus("", List$.MODULE$.canBuildFrom()) : splitValue;
                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"\"\"\""}));
                        this.printSeq(multilineStringValue, new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ CodePrinter $outer;

                            public final void apply(String x$32) {
                                this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{x$32}));
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }, (Function0<BoxedUnit>)new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ CodePrinter $outer;

                            public final void apply() {
                                this.apply$mcV$sp();
                            }

                            public void apply$mcV$sp() {
                                this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToCharacter('\n')}));
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        });
                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"\"\"\""}));
                    } else lbl-1000:
                    // 2 sources

                    {
                        printValue = new StringBuilder().append((Object)var179_168.value().escapedStringValue()).append((Object)(var179_168.value().value() instanceof Float != false ? "F" : "")).toString();
                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{printValue}));
                    }
                    break block94;
                }
                if (tree instanceof Trees.Annotated) {
                    var190_178 = (Trees.Annotated)tree;
                    x$74 = var190_178.arg();
                    x$75 = this.needsParentheses$default$2(x$74);
                    x$76 = this.needsParentheses$default$3(x$74);
                    x$77 = this.needsParentheses$default$4(x$74);
                    x$78 = this.needsParentheses$default$5(x$74);
                    x$79 = this.needsParentheses$default$6(x$74);
                    x$80 = this.needsParentheses$default$7(x$74);
                    x$81 = this.needsParentheses$default$8(x$74);
                    printParentheses = this.needsParentheses(x$74, x$75, x$76, x$77, x$78, x$79, x$80, x$81);
                    this.parenthesize(printParentheses, this.parenthesize$default$2(), this.parenthesize$default$3(), (Function0<BoxedUnit>)new Serializable(this, var190_178){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ CodePrinter $outer;
                        private final Trees.Annotated x34$2;

                        public final void apply() {
                            this.apply$mcV$sp();
                        }

                        public void apply$mcV$sp() {
                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.x34$2.arg()}));
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.x34$2 = x34$2;
                        }
                    });
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var190_178.arg().isType() != false ? " " : ": "}));
                    this.printAnnot(var190_178.annot());
                } else if (tree instanceof Trees.SelectFromTypeTree) {
                    var191_188 = (Trees.SelectFromTypeTree)tree;
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"(", var191_188.qualifier(), ")#", this.blankForOperatorName(var191_188.name()), this.printedName(var191_188.name(), this.printedName$default$2())}));
                } else if (tree instanceof Trees.TypeTree) {
                    var192_189 = (Trees.TypeTree)tree;
                    if (this.isEmptyTree(var192_189)) {
                    } else {
                        original = var192_189.original();
                        if (original == null) {
                            super.printTree(tree);
                        } else {
                            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{original}));
                        }
                    }
                } else if (tree instanceof Trees.AppliedTypeTree) {
                    var195_191 = (Trees.AppliedTypeTree)tree;
                    containsByNameTypeParam = var195_191.args().exists((Function1<Trees.Tree, Object>)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ CodePrinter $outer;

                        public final boolean apply(Trees.Tree tpt) {
                            return this.$outer.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().isByNameParamType(tpt);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    });
                    if (containsByNameTypeParam) {
                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"("}));
                        this.printRow((List)var195_191.args().init(), "(", ", ", ")");
                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{" => ", var195_191.args().last(), ")"}));
                    } else if (this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().isRepeatedParamType(tree) && var195_191.args().nonEmpty()) {
                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{var195_191.args().apply(0), "*"}));
                    } else if (this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().isByNameParamType(tree)) {
                        this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"=> ", var195_191.args().isEmpty() != false ? "()" : var195_191.args().apply(0)}));
                    } else {
                        super.printTree(tree);
                    }
                } else if (tree instanceof Trees.ExistentialTypeTree) {
                    var196_193 = (Trees.ExistentialTypeTree)tree;
                    this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"(", var196_193.tpt()}));
                    this.printColumn(var196_193.whereClauses(), " forSome { ", ";", "})");
                } else if (this.scala$reflect$internal$Printers$CodePrinter$$$outer().EmptyTree().equals(tree)) {
                } else {
                    super.printTree(tree);
                }
            }
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Printers$CodePrinter$$$outer() {
            return this.$outer;
        }

        /*
         * Enabled aggressive block sorting
         */
        private final String addBackquotes$1(String s2, Names.Name name$2, boolean decoded$2, String decName$1, char bslash$1, Function1 isDot$1, List brackets$1) {
            String string2;
            block2: {
                block3: {
                    if (!decoded$2) break block2;
                    Predef$ predef$ = Predef$.MODULE$;
                    if (new StringOps(decName$1).exists((Function1<Object, Object>)((Object)new Serializable(this, isDot$1, brackets$1){
                        public static final long serialVersionUID = 0L;
                        private final Function1 isDot$1;
                        private final List brackets$1;

                        public final boolean apply(char ch) {
                            return this.brackets$1.contains(BoxesRunTime.boxToCharacter(ch)) || Chars$.MODULE$.isWhitespace(ch) || BoxesRunTime.unboxToBoolean(this.isDot$1.apply(BoxesRunTime.boxToCharacter(ch)));
                        }
                        {
                            this.isDot$1 = isDot$1;
                            this.brackets$1 = brackets$1;
                        }
                    }))) break block3;
                    if (!name$2.isOperatorName()) break block2;
                    Predef$ predef$2 = Predef$.MODULE$;
                    if (!new StringOps(decName$1).exists((Function1<Object, Object>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final boolean apply(char c) {
                            return Chars$.MODULE$.isOperatorPart(c);
                        }
                    }))) break block2;
                    Predef$ predef$3 = Predef$.MODULE$;
                    if (!new StringOps(decName$1).exists((Function1<Object, Object>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final boolean apply(char ch) {
                            return Chars$.MODULE$.isScalaLetter(ch);
                        }
                    }))) break block2;
                    Predef$ predef$4 = Predef$.MODULE$;
                    if (new StringOps(decName$1).contains(BoxesRunTime.boxToCharacter(bslash$1))) break block2;
                }
                string2 = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"`", "`"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{s2}));
                return string2;
            }
            string2 = s2;
            return string2;
        }

        private final List removeDefaultTraitsFromList$1(List trees, List traitsToRemove) {
            List list2;
            block3: {
                while (true) {
                    Trees.Select select;
                    if (((Object)Nil$.MODULE$).equals(trees)) {
                        list2 = trees;
                        break block3;
                    }
                    Option option = scala.package$.MODULE$.$colon$plus().unapply(trees);
                    if (option.isEmpty()) {
                        throw new MatchError(trees);
                    }
                    Trees.Tree tree = (Trees.Tree)option.get()._2();
                    if (!(tree instanceof Trees.Select) || !((select = (Trees.Select)tree).qualifier() instanceof Trees.Ident)) break;
                    Trees.Ident ident = (Trees.Ident)select.qualifier();
                    if (!traitsToRemove.contains(select.name())) break;
                    Names.Name name = ident.name();
                    Names.TermName termName = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().scala_();
                    if (name != null ? !name.equals(termName) : termName != null) break;
                    trees = option.get()._1();
                }
                list2 = trees;
            }
            return list2;
        }

        private final boolean modsAccepted$1() {
            return ((List)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Option[]{this.currentTree(), this.currentParent()}))).exists(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Option<Trees.Tree> x$17) {
                    Option option;
                    Serializable serializable = new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final boolean apply(Trees.Tree x0$4) {
                            boolean bl = x0$4 instanceof Trees.ClassDef ? true : (x0$4 instanceof Trees.ModuleDef ? true : (x0$4 instanceof Trees.Template ? true : x0$4 instanceof Trees.PackageDef));
                            boolean bl2 = bl;
                            return bl2;
                        }
                    };
                    if (!x$17.isEmpty()) {
                        Trees.Tree tree = x$17.get();
                        Some<Boolean> some = new Some<Boolean>(BoxesRunTime.boxToBoolean(serializable.apply(tree)));
                        option = some;
                    } else {
                        option = None$.MODULE$;
                    }
                    Option option2 = option;
                    return BoxesRunTime.unboxToBoolean(!option.isEmpty() ? option2.get() : BoxesRunTime.boxToBoolean(false));
                }
            });
        }

        public final void scala$reflect$internal$Printers$CodePrinter$$printConstrParams$1(List ts) {
            this.parenthesize(this.parenthesize$default$1(), this.parenthesize$default$2(), this.parenthesize$default$3(), (Function0<BoxedUnit>)((Object)new Serializable(this, ts){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ CodePrinter $outer;
                private final List ts$2;

                public final void apply() {
                    this.apply$mcV$sp();
                }

                public void apply$mcV$sp() {
                    this.$outer.printImplicitInParamsList(this.ts$2);
                    this.$outer.printSeq(this.ts$2, new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ CodePrinter$$anonfun$scala$reflect$internal$Printers$CodePrinter$$printConstrParams$1$1 $outer;

                        public final void apply(Trees.ValDef x$19) {
                            this.$outer.$outer.printParam(x$19, true);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, (Function0<BoxedUnit>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ CodePrinter$$anonfun$scala$reflect$internal$Printers$CodePrinter$$printConstrParams$1$1 $outer;

                        public final void apply() {
                            this.apply$mcV$sp();
                        }

                        public void apply$mcV$sp() {
                            this.$outer.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{", "}));
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                }

                public /* synthetic */ CodePrinter scala$reflect$internal$Printers$CodePrinter$$anonfun$$$outer() {
                    return this.$outer;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.ts$2 = ts$2;
                }
            }));
        }

        private final void insertBraces$1(Function0 body2) {
            if (this.parentsStack().nonEmpty() && ((IterableLike)this.parentsStack().tail()).exists(new /* invalid duplicate definition of identical inner class */)) {
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"("}));
                body2.apply$mcV$sp();
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{")"}));
            } else {
                body2.apply$mcV$sp();
            }
        }

        private final void printTp$1(Trees.Typed x18$1) {
            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"(", x18$1.tpt(), ")"}));
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private final boolean checkRootPackage$1(Trees.Tree tr) {
            boolean bl;
            while (true) {
                Some some;
                Option<Trees.Tree> option;
                if ((option = this.currentParent()) instanceof Some && (some = (Some)option).x() instanceof Trees.PackageDef) {
                    return false;
                }
                boolean bl2 = true;
                if (!bl2) return false;
                if (!(tr instanceof Trees.Select)) break;
                Trees.Select select = (Trees.Select)tr;
                tr = select.qualifier();
            }
            if (tr instanceof Trees.Ident) {
                bl = true;
            } else {
                if (!(tr instanceof Trees.This)) return false;
                bl = true;
            }
            if (!bl) return false;
            Symbols.Symbol sym = tr.symbol();
            if (!tr.hasExistingSymbol()) return false;
            if (!sym.hasPackageFlag()) return false;
            Names.Name name = sym.name();
            Names.TermName termName = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().ROOTPKG();
            if (name == null) {
                if (termName == null) return false;
                return true;
            } else if (name.equals(termName)) return false;
            return true;
        }

        public CodePrinter(SymbolTable $outer, PrintWriter out, boolean printRootPkg) {
            this.scala$reflect$internal$Printers$CodePrinter$$printRootPkg = printRootPkg;
            super($outer, out);
            this.parentsStack = (Stack)Stack$.MODULE$.apply(Nil$.MODULE$);
            this.commentsRequired = true;
            this.defaultClasses = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Names.TypeName[]{(Names.TypeName)$outer.tpnme().AnyRef(), $outer.tpnme().Object()}));
            this.defaultTraitsForCase = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Names.TypeName[]{$outer.tpnme().Product(), $outer.tpnme().Serializable()}));
        }
    }

    public static class FootnoteIndex {
        private final Map<Class<?>, WeakHashMap<Object, Object>> index;
        private final Map<Class<?>, Object> counters;
        public final /* synthetic */ SymbolTable $outer;

        private Map<Class<?>, WeakHashMap<Object, Object>> index() {
            return this.index;
        }

        public <T> WeakHashMap<Object, Object> scala$reflect$internal$Printers$FootnoteIndex$$classIndex(ClassTag<T> evidence$1) {
            return this.index().getOrElseUpdate(package$.MODULE$.classTag(evidence$1).runtimeClass(), (Function0<WeakHashMap<Object, Object>>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final WeakHashMap<Object, Object> apply() {
                    return (WeakHashMap)WeakHashMap$.MODULE$.apply(Nil$.MODULE$);
                }
            }));
        }

        private Map<Class<?>, Object> counters() {
            return this.counters;
        }

        public <T> int scala$reflect$internal$Printers$FootnoteIndex$$nextCounter(ClassTag<T> evidence$2) {
            Class<?> clazz = package$.MODULE$.classTag(evidence$2).runtimeClass();
            this.counters().getOrElseUpdate(clazz, (Function0<Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply() {
                    return 0;
                }

                public int apply$mcI$sp() {
                    return 0;
                }
            }));
            this.counters().update(clazz, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.counters().apply(clazz)) + 1));
            return BoxesRunTime.unboxToInt(this.counters().apply(clazz));
        }

        public Footnotes mkFootnotes() {
            return new Footnotes();
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Printers$FootnoteIndex$$$outer() {
            return this.$outer;
        }

        public FootnoteIndex(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this.index = (Map)Map$.MODULE$.apply(Nil$.MODULE$);
            this.counters = (Map)Map$.MODULE$.apply(Nil$.MODULE$);
        }

        public class Footnotes {
            private final Map<Class<?>, SortedSet<Object>> footnotes;

            private Map<Class<?>, SortedSet<Object>> footnotes() {
                return this.footnotes;
            }

            private <T> SortedSet<Object> classFootnotes(ClassTag<T> evidence$3) {
                return this.footnotes().getOrElseUpdate(package$.MODULE$.classTag(evidence$3).runtimeClass(), (Function0<SortedSet<Object>>)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final SortedSet<Object> apply() {
                        return (SortedSet)SortedSet$.MODULE$.apply(Nil$.MODULE$, Ordering$Int$.MODULE$);
                    }
                }));
            }

            /*
             * WARNING - void declaration
             */
            public <T> int put(T any, ClassTag<T> evidence$4) {
                void var3_3;
                int index = BoxesRunTime.unboxToInt(this.scala$reflect$internal$Printers$FootnoteIndex$Footnotes$$$outer().scala$reflect$internal$Printers$FootnoteIndex$$classIndex(evidence$4).getOrElseUpdate(any, (Function0<Object>)((Object)new Serializable(this, evidence$4){
                    public static final long serialVersionUID = 0L;
                    public final /* synthetic */ Footnotes $outer;
                    public final ClassTag evidence$4$1;

                    public final int apply() {
                        return this.$outer.scala$reflect$internal$Printers$FootnoteIndex$Footnotes$$$outer().scala$reflect$internal$Printers$FootnoteIndex$$nextCounter(this.evidence$4$1);
                    }

                    public int apply$mcI$sp() {
                        return this.$outer.scala$reflect$internal$Printers$FootnoteIndex$Footnotes$$$outer().scala$reflect$internal$Printers$FootnoteIndex$$nextCounter(this.evidence$4$1);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.evidence$4$1 = evidence$4$1;
                    }
                })));
                this.classFootnotes(evidence$4).$plus$eq(BoxesRunTime.boxToInteger(index));
                return (int)var3_3;
            }

            public <T> List<Tuple2<Object, Object>> get(ClassTag<T> evidence$5) {
                return this.classFootnotes(evidence$5).toList().map(new Serializable(this, evidence$5){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Footnotes $outer;
                    private final ClassTag evidence$5$1;

                    public final Tuple2<Object, Object> apply(int fi) {
                        return new Tuple2<Object, Object>(BoxesRunTime.boxToInteger(fi), this.$outer.scala$reflect$internal$Printers$FootnoteIndex$Footnotes$$$outer().scala$reflect$internal$Printers$FootnoteIndex$$classIndex(this.evidence$5$1).find((Function1<Object, Object>)((Object)new Serializable(this, fi){
                            public static final long serialVersionUID = 0L;
                            private final int fi$1;

                            public final boolean apply(Tuple2<Object, Object> x0$9) {
                                if (x0$9 != null) {
                                    return x0$9._2$mcI$sp() == this.fi$1;
                                }
                                throw new MatchError(x0$9);
                            }
                            {
                                this.fi$1 = fi$1;
                            }
                        })).get()._1());
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.evidence$5$1 = evidence$5$1;
                    }
                }, List$.MODULE$.canBuildFrom());
            }

            public <T> void print(Printers.TreePrinter printer, ClassTag<T> evidence$6) {
                List<Tuple2<Object, Object>> footnotes = this.get(evidence$6);
                if (footnotes.nonEmpty()) {
                    printer.print(Predef$.MODULE$.genericWrapArray(new Object[]{Platform$.MODULE$.EOL()}));
                    List list2 = footnotes.zipWithIndex(List$.MODULE$.canBuildFrom());
                    while (!((AbstractIterable)list2).isEmpty()) {
                        Tuple2 tuple2 = (Tuple2)((AbstractIterable)list2).head();
                        if (tuple2 != null && tuple2._1() != null) {
                            printer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"[", BoxesRunTime.boxToInteger(((Tuple2)tuple2._1())._1$mcI$sp()), "] ", ((Tuple2)tuple2._1())._2()}));
                            if (tuple2._2$mcI$sp() < footnotes.length() - 1) {
                                printer.print(Predef$.MODULE$.genericWrapArray(new Object[]{Platform$.MODULE$.EOL()}));
                            }
                            list2 = (List)list2.tail();
                            continue;
                        }
                        throw new MatchError(tuple2);
                    }
                }
            }

            public /* synthetic */ FootnoteIndex scala$reflect$internal$Printers$FootnoteIndex$Footnotes$$$outer() {
                return FootnoteIndex.this;
            }

            public Footnotes() {
                if (FootnoteIndex.this == null) {
                    throw null;
                }
                this.footnotes = (Map)Map$.MODULE$.apply(Nil$.MODULE$);
            }
        }
    }

    public class RawTreePrinter
    implements Printers.TreePrinter {
        public final PrintWriter scala$reflect$internal$Printers$RawTreePrinter$$out;
        private int depth;
        private boolean scala$reflect$internal$Printers$RawTreePrinter$$printTypesInFootnotes;
        private boolean scala$reflect$internal$Printers$RawTreePrinter$$printingFootnotes;
        private final FootnoteIndex.Footnotes scala$reflect$internal$Printers$RawTreePrinter$$footnotes;
        public final /* synthetic */ SymbolTable $outer;
        private boolean printTypes;
        private boolean printIds;
        private boolean printOwners;
        private boolean printKinds;
        private boolean printMirrors;
        private boolean printPositions;

        @Override
        public boolean printTypes() {
            return this.printTypes;
        }

        @Override
        @TraitSetter
        public void printTypes_$eq(boolean x$1) {
            this.printTypes = x$1;
        }

        @Override
        public boolean printIds() {
            return this.printIds;
        }

        @Override
        @TraitSetter
        public void printIds_$eq(boolean x$1) {
            this.printIds = x$1;
        }

        @Override
        public boolean printOwners() {
            return this.printOwners;
        }

        @Override
        @TraitSetter
        public void printOwners_$eq(boolean x$1) {
            this.printOwners = x$1;
        }

        @Override
        public boolean printKinds() {
            return this.printKinds;
        }

        @Override
        @TraitSetter
        public void printKinds_$eq(boolean x$1) {
            this.printKinds = x$1;
        }

        @Override
        public boolean printMirrors() {
            return this.printMirrors;
        }

        @Override
        @TraitSetter
        public void printMirrors_$eq(boolean x$1) {
            this.printMirrors = x$1;
        }

        @Override
        public boolean printPositions() {
            return this.printPositions;
        }

        @Override
        @TraitSetter
        public void printPositions_$eq(boolean x$1) {
            this.printPositions = x$1;
        }

        @Override
        public Printers.TreePrinter withTypes() {
            return Printers$TreePrinter$class.withTypes(this);
        }

        @Override
        public Printers.TreePrinter withoutTypes() {
            return Printers$TreePrinter$class.withoutTypes(this);
        }

        @Override
        public Printers.TreePrinter withIds() {
            return Printers$TreePrinter$class.withIds(this);
        }

        @Override
        public Printers.TreePrinter withoutIds() {
            return Printers$TreePrinter$class.withoutIds(this);
        }

        @Override
        public Printers.TreePrinter withOwners() {
            return Printers$TreePrinter$class.withOwners(this);
        }

        @Override
        public Printers.TreePrinter withoutOwners() {
            return Printers$TreePrinter$class.withoutOwners(this);
        }

        @Override
        public Printers.TreePrinter withKinds() {
            return Printers$TreePrinter$class.withKinds(this);
        }

        @Override
        public Printers.TreePrinter withoutKinds() {
            return Printers$TreePrinter$class.withoutKinds(this);
        }

        @Override
        public Printers.TreePrinter withMirrors() {
            return Printers$TreePrinter$class.withMirrors(this);
        }

        @Override
        public Printers.TreePrinter withoutMirrors() {
            return Printers$TreePrinter$class.withoutMirrors(this);
        }

        @Override
        public Printers.TreePrinter withPositions() {
            return Printers$TreePrinter$class.withPositions(this);
        }

        @Override
        public Printers.TreePrinter withoutPositions() {
            return Printers$TreePrinter$class.withoutPositions(this);
        }

        private int depth() {
            return this.depth;
        }

        private void depth_$eq(int x$1) {
            this.depth = x$1;
        }

        public boolean scala$reflect$internal$Printers$RawTreePrinter$$printTypesInFootnotes() {
            return this.scala$reflect$internal$Printers$RawTreePrinter$$printTypesInFootnotes;
        }

        private void scala$reflect$internal$Printers$RawTreePrinter$$printTypesInFootnotes_$eq(boolean x$1) {
            this.scala$reflect$internal$Printers$RawTreePrinter$$printTypesInFootnotes = x$1;
        }

        public boolean scala$reflect$internal$Printers$RawTreePrinter$$printingFootnotes() {
            return this.scala$reflect$internal$Printers$RawTreePrinter$$printingFootnotes;
        }

        private void scala$reflect$internal$Printers$RawTreePrinter$$printingFootnotes_$eq(boolean x$1) {
            this.scala$reflect$internal$Printers$RawTreePrinter$$printingFootnotes = x$1;
        }

        public FootnoteIndex.Footnotes scala$reflect$internal$Printers$RawTreePrinter$$footnotes() {
            return this.scala$reflect$internal$Printers$RawTreePrinter$$footnotes;
        }

        @Override
        public void print(Seq<Object> args) {
            if (this.depth() == 0 && args.length() == 1 && args.apply(false) != null && args.apply(false) instanceof Types.Type) {
                this.scala$reflect$internal$Printers$RawTreePrinter$$printTypesInFootnotes_$eq(false);
            }
            this.depth_$eq(this.depth() + 1);
            args.foreach(new Serializable(this){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ RawTreePrinter $outer;

                public final void apply(Object x0$11) {
                    block30: {
                        block39: {
                            Trees.Modifiers modifiers;
                            block41: {
                                block40: {
                                    block38: {
                                        boolean defer;
                                        block37: {
                                            block36: {
                                                block35: {
                                                    Symbols.Symbol symbol;
                                                    block34: {
                                                        block33: {
                                                            block32: {
                                                                block31: {
                                                                    block29: {
                                                                        if (!(x0$11 instanceof Exprs.Expr) || ((Exprs.Expr)x0$11).scala$reflect$api$Exprs$Expr$$$outer() != this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) break block29;
                                                                        Exprs.Expr expr = (Exprs.Expr)x0$11;
                                                                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"Expr"}));
                                                                        if (this.$outer.printTypes()) {
                                                                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{expr.staticType()}));
                                                                        }
                                                                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"("}));
                                                                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{expr.tree()}));
                                                                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{")"}));
                                                                        break block30;
                                                                    }
                                                                    if (!((Object)this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().EmptyTree()).equals(x0$11)) break block31;
                                                                    this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"EmptyTree"}));
                                                                    break block30;
                                                                }
                                                                if (!((Object)this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().noSelfType()).equals(x0$11)) break block32;
                                                                this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"noSelfType"}));
                                                                break block30;
                                                            }
                                                            if (!((Object)this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().pendingSuperCall()).equals(x0$11)) break block33;
                                                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"pendingSuperCall"}));
                                                            break block30;
                                                        }
                                                        if (!(x0$11 instanceof Trees.Tree) || ((Trees.Tree)x0$11).scala$reflect$internal$Trees$Tree$$$outer() != this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) break block34;
                                                        Trees.Tree tree = (Trees.Tree)x0$11;
                                                        boolean isError = this.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$hasSymbolField$1(tree) && tree.symbol().name().string_$eq$eq(this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().nme().ERROR());
                                                        this.$outer.printProduct(tree, (Function1<Product, BoxedUnit>)((Object)new Serializable(this, tree){
                                                            public static final long serialVersionUID = 0L;
                                                            private final /* synthetic */ RawTreePrinter$$anonfun$print$3 $outer;
                                                            private final Trees.Tree x6$3;

                                                            public final void apply(Product x$33) {
                                                                if (this.$outer.$outer.printPositions()) {
                                                                    this.$outer.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.x6$3.pos().show()}));
                                                                }
                                                                this.$outer.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.x6$3.productPrefix()}));
                                                                if (this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$$outer().printTypes() && this.x6$3.tpe() != null) {
                                                                    this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$$outer().print(Predef$.MODULE$.genericWrapArray(new Object[]{this.x6$3.tpe()}));
                                                                }
                                                            }
                                                            {
                                                                if ($outer == null) {
                                                                    throw null;
                                                                }
                                                                this.$outer = $outer;
                                                                this.x6$3 = x6$3;
                                                            }
                                                        }), (Function1<Object, BoxedUnit>)((Object)new Serializable(this, isError, tree){
                                                            public static final long serialVersionUID = 0L;
                                                            private final /* synthetic */ RawTreePrinter$$anonfun$print$3 $outer;
                                                            private final boolean isError$1;
                                                            private final Trees.Tree x6$3;

                                                            /*
                                                             * Enabled aggressive block sorting
                                                             */
                                                            public final void apply(Object x0$12) {
                                                                boolean bl = false;
                                                                Constants.Constant constant = null;
                                                                if (x0$12 instanceof Names.Name && ((Names.Name)x0$12).scala$reflect$internal$Names$Name$$$outer() == this.$outer.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
                                                                    Names.Name name = (Names.Name)x0$12;
                                                                    if (this.isError$1) {
                                                                        if (this.isError$1) {
                                                                            this.$outer.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"<"}));
                                                                        }
                                                                        this.$outer.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{name}));
                                                                        if (!this.isError$1) return;
                                                                        this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$$outer().print(Predef$.MODULE$.genericWrapArray(new Object[]{": error>"}));
                                                                        return;
                                                                    }
                                                                    if (!this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$hasSymbolField$1(this.x6$3)) {
                                                                        this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$$outer().print(Predef$.MODULE$.genericWrapArray(new Object[]{name}));
                                                                        return;
                                                                    }
                                                                    Trees.Tree tree = this.x6$3;
                                                                    if (tree instanceof Trees.RefTree) {
                                                                        Trees.RefTree refTree = (Trees.RefTree)((Object)tree);
                                                                        Names.Name name2 = this.x6$3.symbol().name();
                                                                        Names.Name name3 = refTree.name();
                                                                        if (!(name2 != null ? !name2.equals(name3) : name3 != null)) {
                                                                            this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$$outer().print(Predef$.MODULE$.genericWrapArray(new Object[]{this.x6$3.symbol()}));
                                                                            return;
                                                                        }
                                                                        this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$$outer().print(Predef$.MODULE$.genericWrapArray(new Object[]{"[", this.x6$3.symbol(), " aka ", refTree.name(), "]"}));
                                                                        return;
                                                                    }
                                                                    if (tree instanceof Trees.DefTree) {
                                                                        this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$$outer().print(Predef$.MODULE$.genericWrapArray(new Object[]{this.x6$3.symbol()}));
                                                                        return;
                                                                    }
                                                                    this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$$outer().print(Predef$.MODULE$.genericWrapArray(new Object[]{this.x6$3.symbol().name()}));
                                                                    return;
                                                                }
                                                                if (x0$12 instanceof Constants.Constant && ((Constants.Constant)x0$12).scala$reflect$internal$Constants$Constant$$$outer() == this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$$outer().scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
                                                                    bl = true;
                                                                    constant = (Constants.Constant)x0$12;
                                                                    if (constant.value() instanceof String) {
                                                                        String string2 = (String)constant.value();
                                                                        this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$$outer().print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)"Constant(\"").append((Object)string2).append((Object)"\")").toString()}));
                                                                        return;
                                                                    }
                                                                }
                                                                if (bl && constant.value() == null) {
                                                                    this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$$outer().print(Predef$.MODULE$.genericWrapArray(new Object[]{"Constant(null)"}));
                                                                    return;
                                                                }
                                                                if (bl) {
                                                                    this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$$outer().print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)"Constant(").append(constant.value()).append((Object)")").toString()}));
                                                                    return;
                                                                }
                                                                this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$$outer().print(Predef$.MODULE$.genericWrapArray(new Object[]{x0$12}));
                                                            }
                                                            {
                                                                if ($outer == null) {
                                                                    throw null;
                                                                }
                                                                this.$outer = $outer;
                                                                this.isError$1 = isError$1;
                                                                this.x6$3 = x6$3;
                                                            }
                                                        }), (Function1<Product, BoxedUnit>)((Object)new Serializable(this){
                                                            public static final long serialVersionUID = 0L;
                                                            private final /* synthetic */ RawTreePrinter$$anonfun$print$3 $outer;

                                                            public final void apply(Product x0$13) {
                                                                block0: {
                                                                    Trees.TypeTree typeTree;
                                                                    if (!(x0$13 instanceof Trees.TypeTree) || ((Trees.TypeTree)x0$13).scala$reflect$internal$Trees$TypeTree$$$outer() != this.$outer.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer() || (typeTree = (Trees.TypeTree)x0$13).original() == null) break block0;
                                                                    this.$outer.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{".setOriginal(", typeTree.original(), ")"}));
                                                                }
                                                            }
                                                            {
                                                                if ($outer == null) {
                                                                    throw null;
                                                                }
                                                                this.$outer = $outer;
                                                            }
                                                        }));
                                                        break block30;
                                                    }
                                                    if (!(x0$11 instanceof Symbols.Symbol) || ((Symbols.Symbol)x0$11).scala$reflect$internal$Symbols$Symbol$$$outer() != this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) break block35;
                                                    Symbols.Symbol symbol2 = symbol = (Symbols.Symbol)x0$11;
                                                    Symbols.NoSymbol noSymbol = this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().NoSymbol();
                                                    if (!(symbol2 != null ? !symbol2.equals(noSymbol) : noSymbol != null)) {
                                                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"NoSymbol"}));
                                                    } else if (symbol.isStatic() && (symbol.isClass() || symbol.isModule())) {
                                                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{symbol.fullName()}));
                                                    } else {
                                                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{symbol.name()}));
                                                    }
                                                    if (this.$outer.printIds()) {
                                                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"#", BoxesRunTime.boxToInteger(symbol.id())}));
                                                    }
                                                    if (this.$outer.printOwners()) {
                                                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"@", BoxesRunTime.boxToInteger(symbol.owner().id())}));
                                                    }
                                                    if (this.$outer.printKinds()) {
                                                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"#", symbol.abbreviatedKindString()}));
                                                    }
                                                    if (this.$outer.printMirrors()) {
                                                        this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"%M", BoxesRunTime.boxToInteger(this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$footnotes().put(this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().mirrorThatLoaded(symbol), ClassTag$.MODULE$.apply(Mirror.class)))}));
                                                    }
                                                    break block30;
                                                }
                                                if (!(x0$11 instanceof TypeTags.TypeTag) || ((TypeTags.TypeTag)x0$11).scala$reflect$api$TypeTags$TypeTag$$$outer() != this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) break block36;
                                                TypeTags.TypeTag typeTag = (TypeTags.TypeTag)x0$11;
                                                this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"TypeTag(", typeTag.tpe(), ")"}));
                                                break block30;
                                            }
                                            if (!(x0$11 instanceof TypeTags.WeakTypeTag) || ((TypeTags.WeakTypeTag)x0$11).scala$reflect$api$TypeTags$WeakTypeTag$$$outer() != this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) break block37;
                                            TypeTags.WeakTypeTag weakTypeTag = (TypeTags.WeakTypeTag)x0$11;
                                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"WeakTypeTag(", weakTypeTag.tpe(), ")"}));
                                            break block30;
                                        }
                                        if (!(x0$11 instanceof Types.Type) || ((Types.Type)x0$11).scala$reflect$internal$Types$Type$$$outer() != this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) break block38;
                                        Types.Type type = (Types.Type)x0$11;
                                        boolean bl = defer = this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$printTypesInFootnotes() && !this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$printingFootnotes();
                                        if (defer) {
                                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"[", BoxesRunTime.boxToInteger(this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$footnotes().put(type, this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().TypeTagg())), "]"}));
                                        } else if (this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().NoType().equals(type)) {
                                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"NoType"}));
                                        } else if (this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().NoPrefix().equals(type)) {
                                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"NoPrefix"}));
                                        } else {
                                            this.$outer.printProduct((Product)((Object)type), this.$outer.printProduct$default$2(), this.$outer.printProduct$default$3(), this.$outer.printProduct$default$4());
                                        }
                                        break block30;
                                    }
                                    if (!(x0$11 instanceof Trees.Modifiers) || ((Trees.Modifiers)x0$11).scala$reflect$internal$Trees$Modifiers$$$outer() != this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) break block39;
                                    modifiers = (Trees.Modifiers)x0$11;
                                    this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"Modifiers("}));
                                    if (modifiers.flags() != this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().NoFlags()) break block40;
                                    Names.Name name = modifiers.privateWithin();
                                    Names.Name name2 = this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().tpnme().EMPTY();
                                    if (!(name != null ? !name.equals(name2) : name2 != null) && !modifiers.annotations().nonEmpty()) break block41;
                                }
                                this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().show(modifiers.flags())}));
                            }
                            Names.Name name = modifiers.privateWithin();
                            Names.Name name3 = this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().tpnme().EMPTY();
                            if ((name != null ? !name.equals(name3) : name3 != null) || modifiers.annotations().nonEmpty()) {
                                this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{", "}));
                                this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{modifiers.privateWithin()}));
                            }
                            if (modifiers.annotations().nonEmpty()) {
                                this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{", "}));
                                this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{modifiers.annotations()}));
                            }
                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{")"}));
                            break block30;
                        }
                        if (x0$11 instanceof Names.Name && ((Names.Name)x0$11).scala$reflect$internal$Names$Name$$$outer() == this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
                            Names.Name name = (Names.Name)x0$11;
                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().show(name)}));
                        } else if (x0$11 instanceof Scopes.Scope && ((Scopes.Scope)x0$11).scala$reflect$internal$Scopes$Scope$$$outer() == this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
                            Scopes.Scope scope = (Scopes.Scope)x0$11;
                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"Scope"}));
                            this.$outer.printIterable(scope.toList(), (Function0<BoxedUnit>)((Object)new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                public final /* synthetic */ RawTreePrinter$$anonfun$print$3 $outer;

                                public final void apply() {
                                    this.$outer.$outer.printIterable$default$2();
                                }

                                public void apply$mcV$sp() {
                                    this.$outer.$outer.printIterable$default$2();
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            }), this.$outer.printIterable$default$3(), (Function0<BoxedUnit>)((Object)new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                public final /* synthetic */ RawTreePrinter$$anonfun$print$3 $outer;

                                public final void apply() {
                                    this.$outer.$outer.printIterable$default$4();
                                }

                                public void apply$mcV$sp() {
                                    this.$outer.$outer.printIterable$default$4();
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            }));
                        } else if (x0$11 instanceof List) {
                            List list2 = (List)x0$11;
                            this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{"List"}));
                            this.$outer.printIterable(list2, (Function0<BoxedUnit>)((Object)new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                public final /* synthetic */ RawTreePrinter$$anonfun$print$3 $outer;

                                public final void apply() {
                                    this.$outer.$outer.printIterable$default$2();
                                }

                                public void apply$mcV$sp() {
                                    this.$outer.$outer.printIterable$default$2();
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            }), this.$outer.printIterable$default$3(), (Function0<BoxedUnit>)((Object)new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                public final /* synthetic */ RawTreePrinter$$anonfun$print$3 $outer;

                                public final void apply() {
                                    this.$outer.$outer.printIterable$default$4();
                                }

                                public void apply$mcV$sp() {
                                    this.$outer.$outer.printIterable$default$4();
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            }));
                        } else if (x0$11 instanceof Product) {
                            Product product2 = (Product)x0$11;
                            this.$outer.printProduct(product2, this.$outer.printProduct$default$2(), this.$outer.printProduct$default$3(), this.$outer.printProduct$default$4());
                        } else {
                            this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$out.print(x0$11);
                        }
                    }
                }

                public /* synthetic */ RawTreePrinter scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$$outer() {
                    return this.$outer;
                }

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                public final boolean scala$reflect$internal$Printers$RawTreePrinter$$anonfun$$hasSymbolField$1(Trees.Tree x6$3) {
                    if (!x6$3.hasSymbolField()) return false;
                    Symbols.Symbol symbol = x6$3.symbol();
                    Symbols.NoSymbol noSymbol = this.$outer.scala$reflect$internal$Printers$RawTreePrinter$$$outer().NoSymbol();
                    if (symbol == null) {
                        if (noSymbol == null) return false;
                        return true;
                    } else if (symbol.equals(noSymbol)) return false;
                    return true;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
            this.depth_$eq(this.depth() - 1);
            if (this.depth() == 0 && !this.scala$reflect$internal$Printers$RawTreePrinter$$printingFootnotes()) {
                this.scala$reflect$internal$Printers$RawTreePrinter$$printingFootnotes_$eq(true);
                this.scala$reflect$internal$Printers$RawTreePrinter$$footnotes().print(this, this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().TypeTagg());
                this.scala$reflect$internal$Printers$RawTreePrinter$$footnotes().print(this, ClassTag$.MODULE$.apply(Mirror.class));
                this.scala$reflect$internal$Printers$RawTreePrinter$$printingFootnotes_$eq(false);
            }
        }

        public void printProduct(Product p, Function1<Product, BoxedUnit> preamble, Function1<Object, BoxedUnit> body2, Function1<Product, BoxedUnit> postamble) {
            preamble.apply(p);
            List x$82 = p.productIterator().toList();
            Serializable x$84 = new Serializable(this){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ RawTreePrinter $outer;

                public final void apply() {
                    this.$outer.printIterable$default$2();
                }

                public void apply$mcV$sp() {
                    this.$outer.printIterable$default$2();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
            Serializable x$85 = new Serializable(this){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ RawTreePrinter $outer;

                public final void apply() {
                    this.$outer.printIterable$default$4();
                }

                public void apply$mcV$sp() {
                    this.$outer.printIterable$default$4();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
            this.printIterable(x$82, (Function0<BoxedUnit>)((Object)x$84), body2, (Function0<BoxedUnit>)((Object)x$85));
            postamble.apply(p);
        }

        public Function1<Product, BoxedUnit> printProduct$default$2() {
            return new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ RawTreePrinter $outer;

                public final void apply(Product p) {
                    this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{p.productPrefix()}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
        }

        public Function1<Object, BoxedUnit> printProduct$default$3() {
            return new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ RawTreePrinter $outer;

                public final void apply(Object x$34) {
                    this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{x$34}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
        }

        public Function1<Product, BoxedUnit> printProduct$default$4() {
            return new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ RawTreePrinter $outer;

                public final void apply(Product p) {
                    this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{""}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
        }

        public void printIterable(List<?> iterable, Function0<BoxedUnit> preamble, Function1<Object, BoxedUnit> body2, Function0<BoxedUnit> postamble) {
            preamble.apply$mcV$sp();
            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{"("}));
            Iterator<?> it = iterable.iterator();
            while (it.hasNext()) {
                body2.apply(it.next());
                this.print(Predef$.MODULE$.genericWrapArray(new Object[]{it.hasNext() ? ", " : ""}));
            }
            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{")"}));
            postamble.apply$mcV$sp();
        }

        public void printIterable$default$2() {
            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{""}));
        }

        public Function1<Object, BoxedUnit> printIterable$default$3() {
            return new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ RawTreePrinter $outer;

                public final void apply(Object x$35) {
                    this.$outer.print(Predef$.MODULE$.genericWrapArray(new Object[]{x$35}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
        }

        public void printIterable$default$4() {
            this.print(Predef$.MODULE$.genericWrapArray(new Object[]{""}));
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Printers$RawTreePrinter$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ scala.reflect.api.Printers scala$reflect$api$Printers$TreePrinter$$$outer() {
            return this.scala$reflect$internal$Printers$RawTreePrinter$$$outer();
        }

        public RawTreePrinter(SymbolTable $outer, PrintWriter out) {
            this.scala$reflect$internal$Printers$RawTreePrinter$$out = out;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Printers$TreePrinter$class.$init$(this);
            this.depth = 0;
            this.scala$reflect$internal$Printers$RawTreePrinter$$printTypesInFootnotes = true;
            this.scala$reflect$internal$Printers$RawTreePrinter$$printingFootnotes = false;
            this.scala$reflect$internal$Printers$RawTreePrinter$$footnotes = $outer.scala$reflect$internal$Printers$$footnoteIndex().mkFootnotes();
        }
    }
}

