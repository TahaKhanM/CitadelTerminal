/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractSeq;
import scala.collection.LinearSeqOptimized;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Names;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.TreeInfo$;
import scala.reflect.internal.TreeInfo$Applied$;
import scala.reflect.internal.TreeInfo$DynamicApplication$;
import scala.reflect.internal.TreeInfo$DynamicApplicationNamed$;
import scala.reflect.internal.TreeInfo$DynamicUpdate$;
import scala.reflect.internal.TreeInfo$IsFalse$;
import scala.reflect.internal.TreeInfo$IsTrue$;
import scala.reflect.internal.TreeInfo$MacroImplReference$;
import scala.reflect.internal.TreeInfo$StripCast$;
import scala.reflect.internal.TreeInfo$Unapplied$;
import scala.reflect.internal.TreeInfo$WildcardStarArg$;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Trees$EmptyTree$;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$NoPrefix$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001\u0011=h!B\u0001\u0003\u0003\u0003I!\u0001\u0003+sK\u0016LeNZ8\u000b\u0005\r!\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u000b!\tYA\"D\u0001\u0007\u0013\tiaA\u0001\u0004B]f\u0014VM\u001a\u0005\u0006\u001f\u0001!\t\u0001E\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003E\u0001\"A\u0005\u0001\u000e\u0003\tAq\u0001\u0006\u0001C\u0002\u001b\u0005Q#\u0001\u0004hY>\u0014\u0017\r\\\u000b\u0002-A\u0011!cF\u0005\u00031\t\u00111bU=nE>dG+\u00192mK\")!\u0004\u0001C\u00017\u00051\u0012n\u001d#fG2\f'/\u0019;j_:|%\u000fV=qK\u0012+g\r\u0006\u0002\u001d?A\u00111\"H\u0005\u0003=\u0019\u0011qAQ8pY\u0016\fg\u000eC\u0003!3\u0001\u0007\u0011%\u0001\u0003ue\u0016,\u0007C\u0001\u0012%\u001d\t\u00193#D\u0001\u0001\u0013\t)cE\u0001\u0003Ue\u0016,\u0017BA\u0014\u0003\u0005\u0015!&/Z3t\u0011\u0015I\u0003\u0001\"\u0001+\u0003EI7/\u00138uKJ4\u0017mY3NK6\u0014WM\u001d\u000b\u00039-BQ\u0001\t\u0015A\u0002\u0005BQ!\f\u0001\u0005\u00029\n\u0001$[:D_:\u001cHO];di>\u0014x+\u001b;i\t\u00164\u0017-\u001e7u)\tar\u0006C\u00031Y\u0001\u0007\u0011%A\u0001u\u0011\u0015\u0011\u0004\u0001\"\u00014\u0003%I7\u000fU;sK\u0012+g\r\u0006\u0002\u001di!)\u0001%\ra\u0001C!)a\u0007\u0001C\u0001o\u00051\u0011n\u001d)bi\"$2\u0001\b\u001d:\u0011\u0015\u0001S\u00071\u0001\"\u0011\u0015QT\u00071\u0001\u001d\u00035\tG\u000e\\8x->d\u0017\r^5mK\")A\b\u0001C\u0001{\u0005\u0011\u0012n]*uC\ndW-\u00133f]RLg-[3s)\rabh\u0010\u0005\u0006Am\u0002\r!\t\u0005\u0006um\u0002\r\u0001\b\u0005\u0006\u0003\u0002!IAQ\u0001\u0006gflwj\u001b\u000b\u00039\rCQ\u0001\u0012!A\u0002\u0015\u000b1a]=n!\t\u0011c)\u0003\u0002H\u0011\n11+_7c_2L!!\u0013\u0002\u0003\u000fMKXNY8mg\")1\n\u0001C\u0005\u0019\u00061A/\u001f9f\u001f.$\"\u0001H'\t\u000b9S\u0005\u0019A(\u0002\u0005Q\u0004\bC\u0001\u0012Q\u0013\t\t&K\u0001\u0003UsB,\u0017BA*\u0003\u0005\u0015!\u0016\u0010]3t\u0011\u0015)\u0006\u0001\"\u0001W\u0003AI7o\u0015;bE2,W*Z7cKJ|e\r\u0006\u0003\u001d/bK\u0006\"\u0002#U\u0001\u0004)\u0005\"\u0002\u0011U\u0001\u0004\t\u0003\"\u0002\u001eU\u0001\u0004a\u0002\"B.\u0001\t\u0013a\u0016!D5t'R\f'\r\\3JI\u0016tG\u000fF\u0002\u001d;\u0006DQ\u0001\t.A\u0002y\u0003\"AI0\n\u0005\u00014#!B%eK:$\b\"\u0002\u001e[\u0001\u0004a\u0002\"B2\u0001\t\u0003!\u0017a\u00045bgZ{G.\u0019;jY\u0016$\u0016\u0010]3\u0015\u0005q)\u0007\"\u0002\u0011c\u0001\u0004\t\u0003\"B4\u0001\t\u0003A\u0017aE1e[&$8\u000fV=qKN+G.Z2uS>tGC\u0001\u000fj\u0011\u0015\u0001c\r1\u0001\"\u0011\u0015Y\u0007\u0001\"\u0001m\u0003eI7o\u0015;bE2,\u0017\nZ3oi&4\u0017.\u001a:QCR$XM\u001d8\u0015\u0005qi\u0007\"\u0002\u0011k\u0001\u0004\t\u0003\"B8\u0001\t\u0003\u0001\u0018AF5t#V\fG.\u001b4jKJ\u001c\u0016MZ3U_\u0016c\u0017\u000eZ3\u0015\u0005q\t\b\"\u0002\u0011o\u0001\u0004\t\u0003\"B:\u0001\t\u0003!\u0018AE5t\u000bb\u0004(oU1gKR{\u0017J\u001c7j]\u0016$\"\u0001H;\t\u000b\u0001\u0012\b\u0019A\u0011\t\u000b]\u0004A\u0011\u0001=\u00029%\u001c\b+\u001e:f\u000bb\u0004(OR8s/\u0006\u0014h.\u001b8h!V\u0014\bo\\:fgR\u0011A$\u001f\u0005\u0006AY\u0004\r!\t\u0005\u0006w\u0002!\t\u0001`\u0001\u0017[\u0006\u0004X*\u001a;i_\u0012\u0004\u0016M]1ng\u0006sG-\u0011:hgV\u0019Q0!\u0005\u0015\u000by\fi#a\r\u0015\u0007}\f\u0019\u0003\u0005\u0004\u0002\u0002\u0005\u001d\u0011Q\u0002\b\u0004\u0017\u0005\r\u0011bAA\u0003\r\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\u0005\u0003\u0017\u0011A\u0001T5ti*\u0019\u0011Q\u0001\u0004\u0011\t\u0005=\u0011\u0011\u0003\u0007\u0001\t\u001d\t\u0019B\u001fb\u0001\u0003+\u0011\u0011AU\t\u0005\u0003/\ti\u0002E\u0002\f\u00033I1!a\u0007\u0007\u0005\u001dqu\u000e\u001e5j]\u001e\u00042aCA\u0010\u0013\r\t\tC\u0002\u0002\u0004\u0003:L\bbBA\u0013u\u0002\u0007\u0011qE\u0001\u0002MB91\"!\u000bFC\u00055\u0011bAA\u0016\r\tIa)\u001e8di&|gN\r\u0005\b\u0003_Q\b\u0019AA\u0019\u0003\u0019\u0001\u0018M]1ngB)\u0011\u0011AA\u0004\u000b\"9\u0011Q\u0007>A\u0002\u0005]\u0012\u0001B1sON\u0004R!!\u0001\u0002\b\u0005Bq!a\u000f\u0001\t\u0003\ti$\u0001\rg_J,\u0017m\u00195NKRDw\u000e\u001a)be\u0006l\u0017I\u001c3Be\u001e$b!a\u0010\u0002L\u00055Cc\u0001\u000f\u0002B!A\u0011QEA\u001d\u0001\u0004\t\u0019\u0005E\u0004\f\u0003S)\u0015%!\u0012\u0011\u0007-\t9%C\u0002\u0002J\u0019\u0011A!\u00168ji\"A\u0011qFA\u001d\u0001\u0004\t\t\u0004\u0003\u0005\u00026\u0005e\u0002\u0019AA\u001c\u0011\u001d\t\t\u0006\u0001C\u0001\u0003'\na\"\\1z\u0005\u00164\u0016M]$fiR,'\u000fF\u0002\u001d\u0003+Ba\u0001RA(\u0001\u0004)\u0005bBA-\u0001\u0011\u0005\u00111L\u0001\u0013SN4\u0016M]5bE2,wJ]$fiR,'\u000fF\u0002\u001d\u0003;Ba\u0001IA,\u0001\u0004\t\u0003bBA1\u0001\u0011\u0005\u00111M\u0001\u0010SN$UMZ1vYR<U\r\u001e;feR\u0019A$!\u001a\t\r\u0001\ny\u00061\u0001\"\u0011\u001d\tI\u0007\u0001C\u0001\u0003W\n\u0001#[:TK247i\u001c8tiJ\u001c\u0015\r\u001c7\u0015\u0007q\ti\u0007\u0003\u0004!\u0003O\u0002\r!\t\u0005\b\u0003c\u0002A\u0011AA:\u0003EI7oU;qKJ\u001cuN\\:ue\u000e\u000bG\u000e\u001c\u000b\u00049\u0005U\u0004B\u0002\u0011\u0002p\u0001\u0007\u0011\u0005C\u0004\u0002z\u0001!\t!a\u001f\u0002)M$(/\u001b9OC6,G-\u00119qYf\u0014En\\2l)\r\t\u0013Q\u0010\u0005\u0007A\u0005]\u0004\u0019A\u0011\t\u000f\u0005\u0005\u0005\u0001\"\u0001\u0002\u0004\u0006I1\u000f\u001e:ja\u000e\u000b7\u000f\u001e\u000b\u0004C\u0005\u0015\u0005B\u0002\u0011\u0002\u0000\u0001\u0007\u0011eB\u0004\u0002\n\u0002A\t!a#\u0002\u0013M#(/\u001b9DCN$\bcA\u0012\u0002\u000e\u001a9\u0011q\u0012\u0001\t\u0002\u0005E%!C*ue&\u00048)Y:u'\r\tiI\u0003\u0005\b\u001f\u00055E\u0011AAK)\t\tY\t\u0003\u0005\u0002\u001a\u00065E\u0011AAN\u0003\u001d)h.\u00199qYf$B!!(\u0002$B!1\"a(\"\u0013\r\t\tK\u0002\u0002\u0005'>lW\r\u0003\u0004!\u0003/\u0003\r!\t\u0005\b\u0003O\u0003A\u0011AAU\u0003]I7oU3mM>\u00138+\u001e9fe\u000e{gn\u001d;s\u0007\u0006dG\u000eF\u0002\u001d\u0003WCa\u0001IAS\u0001\u0004\t\u0003bBAX\u0001\u0011\u0005\u0011\u0011W\u0001\u0011SN4\u0016M\u001d)biR,'O\u001c#fKB$2\u0001HAZ\u0011\u0019\u0001\u0013Q\u0016a\u0001C!9\u0011q\u0017\u0001\u0005\u0002\u0005e\u0016\u0001D5t-\u0006\u0014\b+\u0019;uKJtGc\u0001\u000f\u0002<\"9\u0011QXA[\u0001\u0004\t\u0013a\u00019bi\"A\u0011\u0011\u0019\u0001\u0005\u0002\t\t\u0019-A\u000beKR,7\r\u001e+za\u0016\u001c\u0007.Z2lK\u0012$&/Z3\u0015\u0007q\t)\r\u0003\u0004!\u0003\u007f\u0003\r!\t\u0005\t\u0003\u0013\u0004A\u0011\u0001\u0002\u0002L\u00061RO\u001c;za\u0016\u001c\u0007.Z2lK\u0012$V-\u001c9m\u0005>$\u0017\u0010\u0006\u0003\u0002N\u0006m\u0007#BAh\u00033\fSBAAi\u0015\u0011\t\u0019.!6\u0002\u0013%lW.\u001e;bE2,'bAAl\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005%\u0011\u0011\u001b\u0005\t\u0003;\f9\r1\u0001\u0002`\u0006)A/Z7qYB\u0019!%!9\n\u0007\u0005\rhE\u0001\u0005UK6\u0004H.\u0019;f\u0011!\t9\u000f\u0001C\u0001\u0005\u0005%\u0018AF;oif\u0004Xm\u00195fG.,GM\u00117pG.\u0014u\u000eZ=\u0015\t\u00055\u00171\u001e\u0005\t\u0003[\f)\u000f1\u0001\u0002p\u0006)!\r\\8dWB\u0019!%!=\n\u0007\u0005MhEA\u0003CY>\u001c7\u000e\u0003\u0005\u0002x\u0002!\tAAA}\u0003U)h\u000e^=qK\u000eDWmY6fIR\u0013X-\u001a\"pIf$b!!4\u0002|\u0006u\bB\u0002\u0011\u0002v\u0002\u0007\u0011\u0005\u0003\u0005\u0002\u0000\u0006U\b\u0019AA\u001c\u0003\u0015!(m\u001c3z\u0011\u001d\u0011\u0019\u0001\u0001C\u0001\u0005\u000b\t\u0001CZ5sgR\u001cuN\\:ueV\u001cGo\u001c:\u0015\u0007\u0005\u00129\u0001\u0003\u0005\u0003\n\t\u0005\u0001\u0019AA\u001c\u0003\u0015\u0019H/\u0019;t\u0011\u001d\u0011i\u0001\u0001C\u0001\u0005\u001f\tACZ5sgR\u001cuN\\:ueV\u001cGo\u001c:Be\u001e\u001cH\u0003BA\u001c\u0005#A\u0001B!\u0003\u0003\f\u0001\u0007\u0011q\u0007\u0005\b\u0005+\u0001A\u0011\u0001B\f\u00039\u0001(/Z*va\u0016\u0014h)[3mIN$BA!\u0007\u0003\"A1\u0011\u0011AA\u0004\u00057\u00012A\tB\u000f\u0013\r\u0011yB\n\u0002\u0007-\u0006dG)\u001a4\t\u0011\t%!1\u0003a\u0001\u0003oAqA!\n\u0001\t\u0003\u00119#\u0001\riCN,f\u000e^=qK\u0012\u0004&/Z*va\u0016\u0014h)[3mIN$2\u0001\bB\u0015\u0011!\u0011IAa\tA\u0002\u0005]\u0002b\u0002B\u0017\u0001\u0011\u0005!qF\u0001\u000bSN,\u0015M\u001d7z\t\u00164Gc\u0001\u000f\u00032!1\u0001Ea\u000bA\u0002\u0005BqA!\u000e\u0001\t\u0003\u00119$A\u0007jg\u0016\u000b'\u000f\\=WC2$UM\u001a\u000b\u00049\te\u0002B\u0002\u0011\u00034\u0001\u0007\u0011\u0005C\u0004\u0003>\u0001!\tAa\u0010\u0002'%\u001c(+\u001a9fCR,G\rU1sC6$\u0016\u0010]3\u0015\u0007q\u0011\t\u0005C\u0004\u0003D\tm\u0002\u0019A\u0011\u0002\u0007Q\u0004H\u000fC\u0004\u0003H\u0001!\tA!\u0013\u0002#%\u001c()\u001f(b[\u0016\u0004\u0016M]1n)f\u0004X\rF\u0002\u001d\u0005\u0017BqAa\u0011\u0003F\u0001\u0007\u0011\u0005C\u0004\u0003P\u0001!\tA!\u0015\u00023\u0005\u001c8/[4o[\u0016tG\u000fV8NCf\u0014WMT1nK\u0012\f%o\u001a\u000b\u0004C\tM\u0003B\u0002\u0011\u0003N\u0001\u0007\u0011\u0005C\u0004\u0003X\u0001!\tA!\u0017\u0002\u0017%\u001cH*\u001a4u\u0003N\u001cxn\u0019\u000b\u00049\tm\u0003\u0002\u0003B/\u0005+\u0002\rAa\u0018\u0002\u0011=\u0004XM]1u_J\u00042A\tB1\u0013\u0011\u0011\u0019G!\u001a\u0003\t9\u000bW.Z\u0005\u0004\u0005O\u0012!!\u0002(b[\u0016\u001c\bb\u0002B6\u0001\u0011\u0005!QN\u0001\u0013SN\u001cv/\u001b;dQ\u0006sgn\u001c;bi&|g\u000eF\u0002\u001d\u0005_BqA!\u001d\u0003j\u0001\u0007q*A\u0002ua\u0016DqA!\u001e\u0001\t\u0003\u00119(\u0001\u0007nCf\u0014U\rV=qKB\u000bG\u000fF\u0002\u001d\u0005sBa\u0001\tB:\u0001\u0004\t\u0003b\u0002B?\u0001\u0011\u0005!qP\u0001\u0012SN<\u0016\u000e\u001c3dCJ$7\u000b^1s\u0003J<Gc\u0001\u000f\u0003\u0002\"1\u0001Ea\u001fA\u0002\u0005:qA!\"\u0001\u0011\u0003\u00119)A\bXS2$7-\u0019:e'R\f'/\u0011:h!\r\u0019#\u0011\u0012\u0004\b\u0005\u0017\u0003\u0001\u0012\u0001BG\u0005=9\u0016\u000e\u001c3dCJ$7\u000b^1s\u0003J<7c\u0001BE\u0015!9qB!#\u0005\u0002\tEEC\u0001BD\u0011!\tIJ!#\u0005\u0002\tUE\u0003\u0002BL\u0005;\u0003Ba\u0003BMC%\u0019!1\u0014\u0004\u0003\r=\u0003H/[8o\u0011\u0019\u0001#1\u0013a\u0001C!9!\u0011\u0015\u0001\u0005\u0002\t\r\u0016A\u0004;za\u0016\u0004\u0016M]1nKR,'o\u001d\u000b\u0005\u0005K\u0013i\u000b\u0005\u0004\u0002\u0002\u0005\u001d!q\u0015\t\u0004E\t%\u0016b\u0001BVM\t9A+\u001f9f\t\u00164\u0007B\u0002\u0011\u0003 \u0002\u0007\u0011\u0005C\u0004\u00032\u0002!\tAa-\u0002+%\u001cx+\u001b7eG\u0006\u0014Hm\u0015;be\u0006\u0013x\rT5tiR\u0019AD!.\t\u0011\t]&q\u0016a\u0001\u0003o\tQ\u0001\u001e:fKNDqAa/\u0001\t\u0003\u0011i,A\u0007jg^KG\u000eZ2be\u0012\f%o\u001a\u000b\u00049\t}\u0006B\u0002\u0011\u0003:\u0002\u0007\u0011\u0005C\u0004\u0003D\u0002!\tA!2\u0002%%\u001cx+\u001b7eG\u0006\u0014Hm\u0015;beRK\b/\u001a\u000b\u00049\t\u001d\u0007B\u0002\u0011\u0003B\u0002\u0007\u0011\u0005C\u0004\u0003L\u0002!\tA!4\u0002\u001b%\u001cH)\u001a4bk2$8)Y:f)\ra\"q\u001a\u0005\t\u0005#\u0014I\r1\u0001\u0003T\u0006!1\rZ3g!\r\u0011#Q[\u0005\u0004\u0005/4#aB\"bg\u0016$UM\u001a\u0005\b\u00057\u0004A\u0011\u0002Bo\u0003-A\u0017m\u001d(p'fl'm\u001c7\u0015\u0007q\u0011y\u000e\u0003\u00041\u00053\u0004\r!\t\u0005\b\u0005G\u0004A\u0011\u0001Bs\u0003YI7oU=oi\",G/[2EK\u001a\fW\u000f\u001c;DCN,Gc\u0001\u000f\u0003h\"A!\u0011\u001bBq\u0001\u0004\u0011\u0019\u000eC\u0004\u0003l\u0002!\tA!<\u0002!\r\fGo\u00195fgRC'o\\<bE2,Gc\u0001\u000f\u0003p\"A!\u0011\u001bBu\u0001\u0004\u0011\u0019\u000eC\u0004\u0003t\u0002!\tA!>\u0002\u001f%\u001c8+\u001f8uQ\u0016$\u0018nY\"bg\u0016$2\u0001\bB|\u0011!\u0011\tN!=A\u0002\tM\u0007b\u0002B~\u0001\u0011\u0005!Q`\u0001\fSN\u001c\u0015\r^2i\u0007\u0006\u001cX\rF\u0002\u001d\u0005\u007fD\u0001B!5\u0003z\u0002\u0007!1\u001b\u0005\b\u0007\u0007\u0001A\u0011BB\u0003\u0003EI7oU5na2,G\u000b\u001b:po\u0006\u0014G.\u001a\u000b\u00049\r\u001d\u0001B\u0002(\u0004\u0002\u0001\u0007q\nC\u0004\u0004\f\u0001!\ta!\u0004\u0002\u001b%\u001cx)^1sI\u0016$7)Y:f)\ra2q\u0002\u0005\t\u0005#\u001cI\u00011\u0001\u0003T\"911\u0003\u0001\u0005\u0002\rU\u0011\u0001E5t'\u0016\fX/\u001a8dKZ\u000bG.^3e)\ra2q\u0003\u0005\u0007A\rE\u0001\u0019A\u0011\t\u000f\rm\u0001\u0001\"\u0001\u0004\u001e\u00051QO\u001c2j]\u0012$2!IB\u0010\u0011\u001d\u0019\tc!\u0007A\u0002\u0005\n\u0011\u0001\u001f\u0005\b\u0007K\u0001A\u0011AB\u0014\u0003\u0019I7o\u0015;beR\u0019Ad!\u000b\t\u000f\r\u000521\u0005a\u0001C!91Q\u0006\u0001\u0005\u0002\r=\u0012!F3gM\u0016\u001cG/\u001b<f!\u0006$H/\u001a:o\u0003JLG/\u001f\u000b\u0005\u0007c\u00199\u0004E\u0002\f\u0007gI1a!\u000e\u0007\u0005\rIe\u000e\u001e\u0005\t\u0003k\u0019Y\u00031\u0001\u00028!911\b\u0001\u0005\u0002\ru\u0012\u0001\u00064mCR$XM\\3e!\u0006$H/\u001a:o\u0003J<7\u000f\u0006\u0003\u00028\r}\u0002\u0002CA\u001b\u0007s\u0001\r!a\u000e\t\u0013\r\r\u0003A1A\u0005\u0006\r\u0015\u0013\u0001E*Z\u001dRCulQ!T\u000b~3E*Q$T+\t\u00199e\u0004\u0002\u0004Ju\u0019\u0001\u0005\u0003\u0001\t\u0011\r5\u0003\u0001)A\u0007\u0007\u000f\n\u0011cU-O)\"{6)Q*F?\u001ac\u0015iR*!\u0011\u001d\u0019\t\u0006\u0001C\u0001\u0007'\n\u0011#[:Ts:$\bnQ1tKNKXNY8m)\ra2Q\u000b\u0005\u0007\t\u000e=\u0003\u0019A#\t\u000f\re\u0003\u0001\"\u0001\u0004\\\u0005\u0011\u0002.Y:Ts:$\bnQ1tKNKXNY8m)\ra2Q\f\u0005\u0007a\r]\u0003\u0019A\u0011\t\u000f\r\u0005\u0004\u0001\"\u0001\u0004d\u0005Q\u0011n\u001d+sC&$(+\u001a4\u0015\u0007q\u0019)\u0007\u0003\u0004!\u0007?\u0002\r!\t\u0004\u0007\u0007S\u0002\u0001aa\u001b\u0003\u000f\u0005\u0003\b\u000f\\5fIN\u00191q\r\u0006\t\u0015\u0001\u001a9G!b\u0001\n\u0003\u0019y'F\u0001\"\u0011)\u0019\u0019ha\u001a\u0003\u0002\u0003\u0006I!I\u0001\u0006iJ,W\r\t\u0005\b\u001f\r\u001dD\u0011AB<)\u0011\u0019Iha\u001f\u0011\u0007\r\u001a9\u0007\u0003\u0004!\u0007k\u0002\r!\t\u0005\t\u0007\u007f\u001a9\u0007\"\u0001\u0004p\u000511-\u00197mK\u0016D\u0001ba!\u0004h\u0011\u00051qN\u0001\u0005G>\u0014X\r\u0003\u0005\u0004\b\u000e\u001dD\u0011ABE\u0003\u0015!\u0018M]4t+\t\t9\u0004\u0003\u0005\u0004\u000e\u000e\u001dD\u0011ABH\u0003\u0015\t'oZ:t+\t\u0019\t\n\u0005\u0004\u0002\u0002\u0005\u001d\u0011q\u0007\u0005\b\u0007+\u0003A\u0011ABL\u00039!\u0017n]:fGR\f\u0005\u000f\u001d7jK\u0012$Ba!\u001f\u0004\u001a\"1\u0001ea%A\u0002\u0005:qa!(\u0001\u0011\u0003\u0019y*A\u0004BaBd\u0017.\u001a3\u0011\u0007\r\u001a\tKB\u0004\u0004j\u0001A\taa)\u0014\u0007\r\u0005&\u0002C\u0004\u0010\u0007C#\taa*\u0015\u0005\r}\u0005\u0002CBV\u0007C#\ta!,\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\re4q\u0016\u0005\u0007A\r%\u0006\u0019A\u0011\t\u0011\u0005e5\u0011\u0015C\u0001\u0007g#Ba!.\u0004>B)1B!'\u00048BA1b!/\"\u0003o\u0019\t*C\u0002\u0004<\u001a\u0011a\u0001V;qY\u0016\u001c\u0004\u0002CB`\u0007c\u0003\ra!\u001f\u0002\u000f\u0005\u0004\b\u000f\\5fI\"A\u0011\u0011TBQ\t\u0003\u0019\u0019\r\u0006\u0003\u00046\u000e\u0015\u0007B\u0002\u0011\u0004B\u0002\u0007\u0011\u0005C\u0004\u0004J\u0002!\taa3\u00023\u0019L'o\u001d;EK\u001aLg.Z:DY\u0006\u001c8o\u0014:PE*,7\r\u001e\u000b\u00069\r57q\u001a\u0005\t\u0005o\u001b9\r1\u0001\u00028!A1\u0011[Bd\u0001\u0004\u0011y&\u0001\u0003oC6,waBBk\u0001!\u00051q[\u0001\n+:\f\u0007\u000f\u001d7jK\u0012\u00042aIBm\r\u001d\u0019Y\u000e\u0001E\u0001\u0007;\u0014\u0011\"\u00168baBd\u0017.\u001a3\u0014\u0007\re'\u0002C\u0004\u0010\u00073$\ta!9\u0015\u0005\r]\u0007\u0002CAM\u00073$\ta!:\u0015\t\t]5q\u001d\u0005\u0007A\r\r\b\u0019A\u0011\t\u000f\r-\b\u0001\"\u0001\u0004n\u0006)bn\u001c)sK\u0012,g-S7q_J$hi\u001c:V]&$Hc\u0001\u000f\u0004p\"91\u0011_Bu\u0001\u0004\t\u0013\u0001\u00022pIfDqa!>\u0001\t\u0003\u001990\u0001\u0007jg\u0006\u00137\u000fV=qK\u0012+g\rF\u0002\u001d\u0007sDa\u0001IBz\u0001\u0004\t\u0003bBB\u007f\u0001\u0011\u00051q`\u0001\u000fSN\fE.[1t)f\u0004X\rR3g)\raB\u0011\u0001\u0005\u0007A\rm\b\u0019A\u0011\u0007\u000f\u0011\u0015\u0001!!\u0001\u0005\b\t\u00012+Z3UQJ|Wo\u001a5CY>\u001c7n]\u000b\u0005\t\u0013!\u0019bE\u0002\u0005\u0004)Aqa\u0004C\u0002\t\u0003!i\u0001\u0006\u0002\u0005\u0010A)1\u0005b\u0001\u0005\u0012A!\u0011q\u0002C\n\t!!)\u0002b\u0001C\u0002\u0005U!!\u0001+\t\u0011\u0011eA1\u0001D\t\t7\t1\"\u001e8baBd\u00170S7qYR!A\u0011\u0003C\u000f\u0011\u001d\u0019\t\u0003b\u0006A\u0002\u0005B\u0001\"!'\u0005\u0004\u0011\u0005A\u0011\u0005\u000b\u0005\t#!\u0019\u0003C\u0004\u0004\"\u0011}\u0001\u0019A\u0011\b\u000f\u0011\u001d\u0002\u0001#\u0001\u0005*\u00051\u0011j\u001d+sk\u0016\u00042a\tC\u0016\r\u001d!i\u0003\u0001E\u0001\t_\u0011a!S:UeV,7\u0003\u0002C\u0016\tc\u0001Ba\tC\u00029!9q\u0002b\u000b\u0005\u0002\u0011UBC\u0001C\u0015\u0011!!I\u0002b\u000b\u0005\u0012\u0011eBc\u0001\u000f\u0005<!91\u0011\u0005C\u001c\u0001\u0004\tsa\u0002C \u0001!\u0005A\u0011I\u0001\b\u0013N4\u0015\r\\:f!\r\u0019C1\t\u0004\b\t\u000b\u0002\u0001\u0012\u0001C$\u0005\u001dI5OR1mg\u0016\u001cB\u0001b\u0011\u00052!9q\u0002b\u0011\u0005\u0002\u0011-CC\u0001C!\u0011!!I\u0002b\u0011\u0005\u0012\u0011=Cc\u0001\u000f\u0005R!91\u0011\u0005C'\u0001\u0004\t\u0003b\u0002C+\u0001\u0011\u0005AqK\u0001\u0013SN\f\u0005\u000f\u001d7z\tft\u0017-\\5d\u001d\u0006lW\rF\u0002\u001d\t3B\u0001b!5\u0005T\u0001\u0007!q\f\u0004\u0007\t;\u0002\u0001\u0001b\u0018\u00037\u0011Kh.Y7jG\u0006\u0003\b\u000f\\5dCRLwN\\#yiJ\f7\r^8s'\r!YF\u0003\u0005\f\tG\"YF!A!\u0002\u0013!)'\u0001\u0005oC6,G+Z:u!\u0019YAq\rB09%\u0019A\u0011\u000e\u0004\u0003\u0013\u0019+hn\u0019;j_:\f\u0004bB\b\u0005\\\u0011\u0005AQ\u000e\u000b\u0005\t_\"\t\bE\u0002$\t7B\u0001\u0002b\u0019\u0005l\u0001\u0007AQ\r\u0005\t\u00033#Y\u0006\"\u0001\u0005vQ!Aq\u000fC@!\u0015Y!\u0011\u0014C=!\u0019YA1P\u0011\u0002\u001e%\u0019AQ\u0010\u0004\u0003\rQ+\b\u000f\\33\u0011\u0019\u0001C1\u000fa\u0001C\u001d9A1\u0011\u0001\t\u0002\u0011\u0015\u0015!\u0004#z]\u0006l\u0017nY+qI\u0006$X\rE\u0002$\t\u000f3q\u0001\"#\u0001\u0011\u0003!YIA\u0007Es:\fW.[2Va\u0012\fG/Z\n\u0005\t\u000f#y\u0007C\u0004\u0010\t\u000f#\t\u0001b$\u0015\u0005\u0011\u0015ua\u0002CJ\u0001!\u0005AQS\u0001\u0013\tft\u0017-\\5d\u0003B\u0004H.[2bi&|g\u000eE\u0002$\t/3q\u0001\"'\u0001\u0011\u0003!YJ\u0001\nEs:\fW.[2BaBd\u0017nY1uS>t7\u0003\u0002CL\t_Bqa\u0004CL\t\u0003!y\n\u0006\u0002\u0005\u0016\u001e9A1\u0015\u0001\t\u0002\u0011\u0015\u0016a\u0006#z]\u0006l\u0017nY!qa2L7-\u0019;j_:t\u0015-\\3e!\r\u0019Cq\u0015\u0004\b\tS\u0003\u0001\u0012\u0001CV\u0005]!\u0015P\\1nS\u000e\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8OC6,Gm\u0005\u0003\u0005(\u0012=\u0004bB\b\u0005(\u0012\u0005Aq\u0016\u000b\u0003\tK;q\u0001b-\u0001\u0011\u0003!),\u0001\nNC\u000e\u0014x.S7qYJ+g-\u001a:f]\u000e,\u0007cA\u0012\u00058\u001a9A\u0011\u0018\u0001\t\u0002\u0011m&AE'bGJ|\u0017*\u001c9m%\u00164WM]3oG\u0016\u001c2\u0001b.\u000b\u0011\u001dyAq\u0017C\u0001\t\u007f#\"\u0001\".\t\u0011\u0011\rGq\u0017C\u0005\t\u000b\fqA]3g!\u0006\u0014H\u000fF\u0002\"\t\u000fDa\u0001\tCa\u0001\u0004\t\u0003\u0002CAM\to#\t\u0001b3\u0015\t\u00115GQ\u001b\t\u0006\u0017\teEq\u001a\t\n\u0017\u0011EG\u0004H#F\u0003oI1\u0001b5\u0007\u0005\u0019!V\u000f\u001d7fk!1\u0001\u0005\"3A\u0002\u0005Bq\u0001\"7\u0001\t\u0003!Y.A\njg:+H\u000e\\1ss&sgo\\2bi&|g\u000eF\u0002\u001d\t;Da\u0001\tCl\u0001\u0004\t\u0003b\u0002Cq\u0001\u0011\u0005A1]\u0001\u0013SNl\u0015m\u0019:p\u0003B\u0004H.[2bi&|g\u000eF\u0002\u001d\tKDa\u0001\tCp\u0001\u0004\t\u0003b\u0002Cu\u0001\u0011\u0005A1^\u0001\u001aSNl\u0015m\u0019:p\u0003B\u0004H.[2bi&|gn\u0014:CY>\u001c7\u000eF\u0002\u001d\t[Da\u0001\tCt\u0001\u0004\t\u0003")
public abstract class TreeInfo {
    private final int SYNTH_CASE_FLAGS;
    private volatile TreeInfo$StripCast$ StripCast$module;
    private volatile TreeInfo$WildcardStarArg$ WildcardStarArg$module;
    private volatile TreeInfo$Applied$ Applied$module;
    private volatile TreeInfo$Unapplied$ Unapplied$module;
    private volatile TreeInfo$IsTrue$ IsTrue$module;
    private volatile TreeInfo$IsFalse$ IsFalse$module;
    private volatile TreeInfo$DynamicUpdate$ DynamicUpdate$module;
    private volatile TreeInfo$DynamicApplication$ DynamicApplication$module;
    private volatile TreeInfo$DynamicApplicationNamed$ DynamicApplicationNamed$module;
    private volatile TreeInfo$MacroImplReference$ MacroImplReference$module;

    private TreeInfo$StripCast$ StripCast$lzycompute() {
        TreeInfo treeInfo = this;
        synchronized (treeInfo) {
            if (this.StripCast$module == null) {
                this.StripCast$module = new TreeInfo$StripCast$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.StripCast$module;
        }
    }

    private TreeInfo$WildcardStarArg$ WildcardStarArg$lzycompute() {
        TreeInfo treeInfo = this;
        synchronized (treeInfo) {
            if (this.WildcardStarArg$module == null) {
                this.WildcardStarArg$module = new TreeInfo$WildcardStarArg$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.WildcardStarArg$module;
        }
    }

    private TreeInfo$Applied$ Applied$lzycompute() {
        TreeInfo treeInfo = this;
        synchronized (treeInfo) {
            if (this.Applied$module == null) {
                this.Applied$module = new TreeInfo$Applied$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Applied$module;
        }
    }

    private TreeInfo$Unapplied$ Unapplied$lzycompute() {
        TreeInfo treeInfo = this;
        synchronized (treeInfo) {
            if (this.Unapplied$module == null) {
                this.Unapplied$module = new TreeInfo$Unapplied$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Unapplied$module;
        }
    }

    private TreeInfo$IsTrue$ IsTrue$lzycompute() {
        TreeInfo treeInfo = this;
        synchronized (treeInfo) {
            if (this.IsTrue$module == null) {
                this.IsTrue$module = new TreeInfo$IsTrue$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.IsTrue$module;
        }
    }

    private TreeInfo$IsFalse$ IsFalse$lzycompute() {
        TreeInfo treeInfo = this;
        synchronized (treeInfo) {
            if (this.IsFalse$module == null) {
                this.IsFalse$module = new TreeInfo$IsFalse$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.IsFalse$module;
        }
    }

    private TreeInfo$DynamicUpdate$ DynamicUpdate$lzycompute() {
        TreeInfo treeInfo = this;
        synchronized (treeInfo) {
            if (this.DynamicUpdate$module == null) {
                this.DynamicUpdate$module = new TreeInfo$DynamicUpdate$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.DynamicUpdate$module;
        }
    }

    private TreeInfo$DynamicApplication$ DynamicApplication$lzycompute() {
        TreeInfo treeInfo = this;
        synchronized (treeInfo) {
            if (this.DynamicApplication$module == null) {
                this.DynamicApplication$module = new TreeInfo$DynamicApplication$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.DynamicApplication$module;
        }
    }

    private TreeInfo$DynamicApplicationNamed$ DynamicApplicationNamed$lzycompute() {
        TreeInfo treeInfo = this;
        synchronized (treeInfo) {
            if (this.DynamicApplicationNamed$module == null) {
                this.DynamicApplicationNamed$module = new TreeInfo$DynamicApplicationNamed$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.DynamicApplicationNamed$module;
        }
    }

    private TreeInfo$MacroImplReference$ MacroImplReference$lzycompute() {
        TreeInfo treeInfo = this;
        synchronized (treeInfo) {
            if (this.MacroImplReference$module == null) {
                this.MacroImplReference$module = new TreeInfo$MacroImplReference$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.MacroImplReference$module;
        }
    }

    public abstract SymbolTable global();

    public boolean isDeclarationOrTypeDef(Trees.Tree tree) {
        Trees.ValOrDefDef valOrDefDef;
        boolean bl = tree instanceof Trees.ValOrDefDef ? (valOrDefDef = (Trees.ValOrDefDef)tree).rhs() == this.global().EmptyTree() : tree instanceof Trees.TypeDef;
        return bl;
    }

    public boolean isInterfaceMember(Trees.Tree tree) {
        boolean bl;
        if (((Object)this.global().EmptyTree()).equals(tree)) {
            bl = true;
        } else if (tree instanceof Trees.Import) {
            bl = true;
        } else if (tree instanceof Trees.TypeDef) {
            bl = true;
        } else if (tree instanceof Trees.DefDef) {
            Trees.DefDef defDef = (Trees.DefDef)tree;
            bl = defDef.mods().isDeferred();
        } else if (tree instanceof Trees.ValDef) {
            Trees.ValDef valDef = (Trees.ValDef)tree;
            bl = valDef.mods().isDeferred();
        } else {
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isConstructorWithDefault(Trees.Tree t) {
        if (!(t instanceof Trees.DefDef)) return false;
        Trees.DefDef defDef = (Trees.DefDef)t;
        Names.TermName termName = this.global().nme().CONSTRUCTOR();
        Names.TermName termName2 = defDef.name();
        if (termName != null) {
            if (!termName.equals(termName2)) return false;
            return this.global().mexists(defDef.vparamss(), new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Trees.ValDef x$1) {
                    return x$1.mods().hasDefault();
                }
            });
        }
        if (termName2 == null) return this.global().mexists(defDef.vparamss(), new /* invalid duplicate definition of identical inner class */);
        return false;
    }

    public boolean isPureDef(Trees.Tree tree) {
        Trees.ValDef valDef;
        boolean bl = ((Object)this.global().EmptyTree()).equals(tree) ? true : (tree instanceof Trees.ClassDef ? true : (tree instanceof Trees.TypeDef ? true : (tree instanceof Trees.Import ? true : tree instanceof Trees.DefDef)));
        boolean bl2 = bl ? true : (tree instanceof Trees.ValDef ? !(valDef = (Trees.ValDef)tree).mods().isMutable() && this.isExprSafeToInline(valDef.rhs()) : false);
        return bl2;
    }

    public boolean isPath(Trees.Tree tree, boolean allowVolatile) {
        boolean bl;
        boolean bl2 = ((Object)this.global().EmptyTree()).equals(tree) ? true : tree instanceof Trees.Literal;
        if (bl2) {
            bl = true;
        } else {
            boolean bl3 = tree instanceof Trees.This ? true : tree instanceof Trees.Super;
            bl = bl3 ? this.symOk(tree.symbol()) : this.isStableIdentifier(tree, allowVolatile);
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isStableIdentifier(Trees.Tree tree, boolean allowVolatile) {
        if (tree instanceof Trees.Ident) {
            Trees.Ident ident = (Trees.Ident)tree;
            return this.isStableIdent(ident, allowVolatile);
        }
        if (tree instanceof Trees.Select) {
            Trees.Select select = (Trees.Select)tree;
            if (!this.isStableMemberOf(tree.symbol(), select.qualifier(), allowVolatile)) return false;
            if (!this.isPath(select.qualifier(), allowVolatile)) return false;
            return true;
        }
        if (!(tree instanceof Trees.Apply)) return false;
        Trees.Apply apply2 = (Trees.Apply)tree;
        if (!(apply2.fun() instanceof Trees.Select)) return false;
        Trees.Select select = (Trees.Select)apply2.fun();
        if (!(select.qualifier() instanceof Trees.Ident)) return false;
        Trees.Ident ident = (Trees.Ident)select.qualifier();
        Names.TermName termName = this.global().nme().apply();
        Names.Name name = select.name();
        if (termName == null) {
            if (name != null) {
                return false;
            }
        } else if (!termName.equals(name)) return false;
        if (!ident.symbol().name().endsWith(this.global().nme().REIFY_FREE_VALUE_SUFFIX())) return false;
        if (!ident.symbol().hasStableFlag()) return false;
        if (!this.isPath(ident, allowVolatile)) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean symOk(Symbols.Symbol sym) {
        if (sym == null) return false;
        if (sym.isError()) return false;
        Symbols.Symbol symbol = sym;
        Symbols.NoSymbol noSymbol = this.global().NoSymbol();
        if (symbol != null) {
            if (!symbol.equals(noSymbol)) return true;
            return false;
        }
        if (noSymbol == null) return false;
        return true;
    }

    private boolean typeOk(Types.Type tp) {
        return tp != null && !tp.isError();
    }

    public boolean isStableMemberOf(Symbols.Symbol sym, Trees.Tree tree, boolean allowVolatile) {
        return !(!this.symOk(sym) || sym.isTerm() && (!sym.isStable() || !allowVolatile && sym.hasVolatileType()) || !this.typeOk(tree.tpe()) || !allowVolatile && this.hasVolatileType(tree) || this.global().definitions().isByNameParamType(tree.tpe()));
    }

    private boolean isStableIdent(Trees.Ident tree, boolean allowVolatile) {
        return this.symOk(tree.symbol()) && tree.symbol().isStable() && !this.global().definitions().isByNameParamType(tree.tpe()) && (allowVolatile || !tree.symbol().hasVolatileType());
    }

    public boolean hasVolatileType(Trees.Tree tree) {
        return this.symOk(tree.symbol()) && tree.tpe().isVolatile() && !tree.symbol().hasAnnotation(this.global().definitions().uncheckedStableClass());
    }

    public boolean admitsTypeSelection(Trees.Tree tree) {
        return this.isPath(tree, false);
    }

    public boolean isStableIdentifierPattern(Trees.Tree tree) {
        return this.isStableIdentifier(tree, true);
    }

    public boolean isQualifierSafeToElide(Trees.Tree tree) {
        return this.isExprSafeToInline(tree);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isExprSafeToInline(Trees.Tree tree) {
        Some<List<Trees.Tree>> some;
        boolean bl = false;
        Trees.Select select = null;
        boolean bl2 = false;
        Trees.Apply apply2 = null;
        if (((Object)this.global().EmptyTree()).equals(tree)) {
            return true;
        }
        if (tree instanceof Trees.This) {
            return true;
        }
        if (tree instanceof Trees.Super) {
            return true;
        }
        if (tree instanceof Trees.Literal) {
            return true;
        }
        boolean bl3 = false;
        if (bl3) {
            return true;
        }
        if (tree instanceof Trees.Ident) {
            return tree.symbol().isStable();
        }
        if (tree instanceof Trees.Select) {
            bl = true;
            select = (Trees.Select)tree;
            if (select.qualifier() instanceof Trees.Literal) {
                Trees.Literal literal = (Trees.Literal)select.qualifier();
                if (!literal.value().isAnyVal()) return false;
                Symbols.Symbol symbol = literal.value().tpe().member(select.name());
                Symbols.NoSymbol noSymbol = this.global().NoSymbol();
                if (symbol == null) {
                    if (noSymbol == null) return false;
                    return true;
                } else if (symbol.equals(noSymbol)) return false;
                return true;
            }
        }
        if (bl) {
            if (!tree.symbol().isStable()) return false;
            if (!this.isExprSafeToInline(select.qualifier())) return false;
            return true;
        }
        if (tree instanceof Trees.TypeApply) {
            Trees.TypeApply typeApply = (Trees.TypeApply)tree;
            return this.isExprSafeToInline(typeApply.fun());
        }
        if (tree instanceof Trees.Apply) {
            Trees.Select select2;
            bl2 = true;
            apply2 = (Trees.Apply)tree;
            if (apply2.fun() instanceof Trees.Select && (select2 = (Trees.Select)apply2.fun()).qualifier() instanceof Trees.Ident) {
                Trees.Ident ident = (Trees.Ident)select2.qualifier();
                Names.TermName termName = this.global().nme().apply();
                Names.Name name = select2.name();
                if (!(termName != null ? !termName.equals(name) : name != null) && ident.symbol().name().endsWith(this.global().nme().REIFY_FREE_VALUE_SUFFIX())) {
                    if (!ident.symbol().hasStableFlag()) return false;
                    if (!this.isExprSafeToInline(ident)) return false;
                    return true;
                }
            }
        }
        if (bl2 && !(some = List$.MODULE$.unapplySeq(apply2.args())).isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(0) == 0) {
            if (apply2.fun().symbol() == null) return false;
            if (!apply2.fun().symbol().isMethod()) return false;
            if (apply2.fun().symbol().isLazy()) return false;
            if (!this.isExprSafeToInline(apply2.fun())) return false;
            return true;
        }
        if (tree instanceof Trees.Typed) {
            Trees.Typed typed = (Trees.Typed)tree;
            return this.isExprSafeToInline(typed.expr());
        }
        if (!(tree instanceof Trees.Block)) return false;
        Trees.Block block = (Trees.Block)tree;
        if (!block.stats().forall((Function1<Trees.Tree, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TreeInfo $outer;

            public final boolean apply(Trees.Tree tree) {
                return this.$outer.isPureDef(tree);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }))) return false;
        if (!this.isExprSafeToInline(block.expr())) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isPureExprForWarningPurposes(Trees.Tree tree) {
        boolean bl;
        Trees.Literal literal;
        if (tree instanceof Trees.Typed) {
            Trees.Typed typed = (Trees.Typed)tree;
            return this.isPureExprForWarningPurposes(typed.expr());
        }
        if (((Object)this.global().EmptyTree()).equals(tree)) {
            return false;
        }
        if (tree instanceof Trees.Literal && (literal = (Trees.Literal)tree).value() != null) {
            BoxedUnit boxedUnit = BoxedUnit.UNIT;
            Object object = literal.value().value();
            if (boxedUnit == null) {
                if (object == null) return false;
            } else if (((Object)boxedUnit).equals(object)) {
                return false;
            }
        }
        if (bl = false) {
            return false;
        }
        if (tree.isErrorTyped()) return false;
        if (!this.isExprSafeToInline(tree)) {
            if (!this.isWarnableRefTree$1(tree)) return false;
        }
        if (!this.isWarnableSymbol$1(tree)) return false;
        return true;
    }

    public <R> List<R> mapMethodParamsAndArgs(List<Symbols.Symbol> params2, List<Trees.Tree> args, Function2<Symbols.Symbol, Trees.Tree, R> f) {
        Builder b = List$.MODULE$.newBuilder();
        this.foreachMethodParamAndArg(params2, args, (Function2<Symbols.Symbol, Trees.Tree, BoxedUnit>)((Object)new Serializable(this, f, b){
            public static final long serialVersionUID = 0L;
            private final Function2 f$1;
            private final Builder b$1;

            public final void apply(Symbols.Symbol param2, Trees.Tree arg) {
                this.b$1.$plus$eq(this.f$1.apply(param2, arg));
            }
            {
                this.f$1 = f$1;
                this.b$1 = b$1;
            }
        }));
        return b.result();
    }

    public boolean foreachMethodParamAndArg(List<Symbols.Symbol> params2, List<Trees.Tree> args, Function2<Symbols.Symbol, Trees.Tree, BoxedUnit> f) {
        block11: {
            block10: {
                java.io.Serializable serializable;
                int alen;
                int plen;
                block9: {
                    plen = params2.length();
                    if (plen != (alen = args.length())) break block9;
                    this.global().foreach2(params2, args, f);
                    serializable = BoxedUnit.UNIT;
                    break block10;
                }
                if (params2.isEmpty()) {
                    return this.fail$1(params2, args);
                }
                if (!this.global().definitions().isVarArgsList(params2)) break block11;
                int plenInit = plen - 1;
                if (alen == plenInit) {
                    if (alen == 0) {
                        serializable = Nil$.MODULE$;
                    } else {
                        this.global().foreach2((List)params2.init(), args, f);
                        serializable = BoxedUnit.UNIT;
                    }
                } else {
                    if (alen < plenInit) {
                        return this.fail$1(params2, args);
                    }
                    this.global().foreach2((List)params2.init(), args.take(plenInit), f);
                    Object remainingArgs = args.drop(plenInit);
                    this.global().foreach2((List)List$.MODULE$.fill(((AbstractSeq)remainingArgs).size(), new Serializable(this, params2){
                        public static final long serialVersionUID = 0L;
                        private final List params$1;

                        public final Symbols.Symbol apply() {
                            return (Symbols.Symbol)this.params$1.last();
                        }
                        {
                            this.params$1 = params$1;
                        }
                    }), remainingArgs, f);
                    serializable = BoxedUnit.UNIT;
                }
            }
            return true;
        }
        return this.fail$1(params2, args);
    }

    public boolean mayBeVarGetter(Symbols.Symbol sym) {
        Types.MethodType methodType;
        Types.PolyType polyType;
        Types.Type type = sym.info();
        boolean bl = type instanceof Types.NullaryMethodType ? sym.owner().isClass() && !sym.isStable() : (type instanceof Types.PolyType && (polyType = (Types.PolyType)type).resultType() instanceof Types.NullaryMethodType ? sym.owner().isClass() && !sym.isStable() : (type instanceof Types.MethodType ? (methodType = (Types.MethodType)type).isImplicit() && sym.owner().isClass() && !sym.isStable() : false));
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isVariableOrGetter(Trees.Tree tree) {
        if (tree instanceof Trees.Ident) {
            return this.isVar$1(tree);
        }
        if (tree instanceof Trees.Select) {
            if (this.isVar$1(tree)) return true;
            if (!this.isGetter$1(tree)) return false;
            return true;
        }
        Option<Tuple3<Trees.Tree, List<Trees.Tree>, List<List<Trees.Tree>>>> option = this.Applied().unapply(tree);
        if (option.isEmpty()) return false;
        if (!(option.get()._1() instanceof Trees.Select)) return false;
        Trees.Select select = (Trees.Select)option.get()._1();
        Names.TermName termName = this.global().nme().apply();
        Names.Name name = select.name();
        if (termName == null) {
            if (name != null) {
                return false;
            }
        } else if (!termName.equals(name)) return false;
        Symbols.Symbol symbol = select.qualifier().tpe().member(this.global().nme().update());
        Symbols.NoSymbol noSymbol = this.global().NoSymbol();
        if (symbol != null) {
            if (!symbol.equals(noSymbol)) return true;
            return false;
        }
        if (noSymbol == null) return false;
        return true;
    }

    public boolean isDefaultGetter(Trees.Tree tree) {
        return tree.symbol() != null && tree.symbol().isDefaultGetter();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isSelfConstrCall(Trees.Tree tree) {
        Trees.Tree tree2 = this.dissectApplied(tree).core();
        if (tree2 instanceof Trees.Ident) {
            Trees.Ident ident = (Trees.Ident)tree2;
            Names.TermName termName = this.global().nme().CONSTRUCTOR();
            Names.Name name = ident.name();
            if (termName == null) {
                if (name == null) return true;
            } else if (termName.equals(name)) {
                return true;
            }
        }
        if (!(tree2 instanceof Trees.Select)) return false;
        Trees.Select select = (Trees.Select)tree2;
        if (!(select.qualifier() instanceof Trees.This)) return false;
        Names.TermName termName = this.global().nme().CONSTRUCTOR();
        Names.Name name = select.name();
        if (termName != null) {
            if (!termName.equals(name)) return false;
            return true;
        }
        if (name == null) return true;
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isSuperConstrCall(Trees.Tree tree) {
        Trees.Tree tree2 = this.dissectApplied(tree).core();
        if (!(tree2 instanceof Trees.Select)) return false;
        Trees.Select select = (Trees.Select)tree2;
        if (!(select.qualifier() instanceof Trees.Super)) return false;
        Names.TermName termName = this.global().nme().CONSTRUCTOR();
        Names.Name name = select.name();
        if (termName != null) {
            if (!termName.equals(name)) return false;
            return true;
        }
        if (name == null) return true;
        return false;
    }

    public Trees.Tree stripNamedApplyBlock(Trees.Tree tree) {
        Trees.Block block;
        Trees.Tree tree2 = tree instanceof Trees.Block && (block = (Trees.Block)tree).stats().forall((Function1<Trees.Tree, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Trees.Tree x$2) {
                return x$2 instanceof Trees.ValDef;
            }
        })) ? block.expr() : tree;
        return tree2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Trees.Tree stripCast(Trees.Tree tree) {
        Trees.TypeApply typeApply;
        if (tree instanceof Trees.TypeApply && (typeApply = (Trees.TypeApply)tree).fun() instanceof Trees.Select) {
            Trees.Select select = (Trees.Select)typeApply.fun();
            if (this.global().definitions().isCastSymbol(select.symbol())) {
                return this.stripCast(select.qualifier());
            }
        }
        if (!(tree instanceof Trees.Apply)) return tree;
        Trees.Apply apply2 = (Trees.Apply)tree;
        if (!(apply2.fun() instanceof Trees.TypeApply)) return tree;
        Trees.TypeApply typeApply2 = (Trees.TypeApply)apply2.fun();
        if (!(typeApply2.fun() instanceof Trees.Select)) return tree;
        Trees.Select select = (Trees.Select)typeApply2.fun();
        if (!((Object)Nil$.MODULE$).equals(apply2.args())) return tree;
        if (!this.global().definitions().isCastSymbol(select.symbol())) return tree;
        return this.stripCast(select.qualifier());
    }

    public TreeInfo$StripCast$ StripCast() {
        return this.StripCast$module == null ? this.StripCast$lzycompute() : this.StripCast$module;
    }

    public boolean isSelfOrSuperConstrCall(Trees.Tree tree) {
        Trees.Tree tree1 = this.stripNamedApplyBlock(tree);
        return this.isSelfConstrCall(tree1) || this.isSuperConstrCall(tree1);
    }

    public boolean isVarPatternDeep(Trees.Tree tree) {
        boolean bl = tree instanceof Trees.Ident ? true : this.isVarPatternDeep0$1(tree);
        return bl;
    }

    public boolean isVarPattern(Trees.Tree pat) {
        Trees.Ident ident;
        boolean bl = pat instanceof Trees.Ident ? !(ident = (Trees.Ident)pat).isBackquoted() && this.global().nme().isVariableName(ident.name()) : false;
        return bl;
    }

    public boolean detectTypecheckedTree(Trees.Tree tree) {
        return tree.hasExistingSymbol() || tree.exists((Function1<Trees.Tree, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Trees.Tree x0$5) {
                boolean bl;
                if (x0$5 instanceof Trees.DefDef) {
                    Trees.DefDef defDef = (Trees.DefDef)x0$5;
                    bl = defDef.mods().hasAccessorFlag() || defDef.mods().isSynthetic();
                } else if (x0$5 instanceof Trees.MemberDef) {
                    Trees.MemberDef memberDef = (Trees.MemberDef)x0$5;
                    bl = memberDef.hasExistingSymbol();
                } else {
                    bl = false;
                }
                return bl;
            }
        }));
    }

    public List<Trees.Tree> untypecheckedTemplBody(Trees.Template templ) {
        return this.untypecheckedTreeBody(templ, templ.body());
    }

    public List<Trees.Tree> untypecheckedBlockBody(Trees.Block block) {
        return this.untypecheckedTreeBody(block, block.stats());
    }

    public List<Trees.Tree> untypecheckedTreeBody(Trees.Tree tree, List<Trees.Tree> tbody) {
        return this.detectTypecheckedTree(tree) ? this.recoverBody$1(this.filterBody$1(tbody, tbody), tbody) : tbody;
    }

    public Trees.Tree firstConstructor(List<Trees.Tree> stats) {
        Option<Trees.Tree> option = stats.find((Function1<Trees.Tree, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TreeInfo $outer;

            public final boolean apply(Trees.Tree x0$6) {
                boolean bl;
                if (x0$6 instanceof Trees.DefDef) {
                    Trees.DefDef defDef = (Trees.DefDef)x0$6;
                    bl = this.$outer.global().nme().isConstructorName(defDef.name());
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
        }));
        return !option.isEmpty() ? option.get() : this.global().EmptyTree();
    }

    public List<Trees.Tree> firstConstructorArgs(List<Trees.Tree> stats) {
        List list2;
        Trees.DefDef defDef;
        Trees.Tree tree = this.firstConstructor(stats);
        if (tree instanceof Trees.DefDef && (defDef = (Trees.DefDef)tree).vparamss() instanceof $colon$colon) {
            $colon$colon $colon$colon = ($colon$colon)defDef.vparamss();
            list2 = (List)$colon$colon.head();
        } else {
            list2 = Nil$.MODULE$;
        }
        return list2;
    }

    public List<Trees.ValDef> preSuperFields(List<Trees.Tree> stats) {
        return stats.collect(new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TreeInfo $outer;

            public final <A1 extends Trees.Tree, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                Trees.ValDef valDef;
                Object object = x1 instanceof Trees.ValDef && this.$outer.isEarlyValDef(valDef = (Trees.ValDef)x1) ? valDef : function1.apply(x1);
                return object;
            }

            public final boolean isDefinedAt(Trees.Tree x1) {
                Trees.ValDef valDef;
                boolean bl = x1 instanceof Trees.ValDef && this.$outer.isEarlyValDef(valDef = (Trees.ValDef)x1);
                return bl;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, List$.MODULE$.canBuildFrom());
    }

    public boolean hasUntypedPreSuperFields(List<Trees.Tree> stats) {
        return this.preSuperFields(stats).exists((Function1<Trees.ValDef, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Trees.ValDef x$4) {
                return x$4.tpt().isEmpty();
            }
        }));
    }

    public boolean isEarlyDef(Trees.Tree tree) {
        boolean bl;
        if (tree instanceof Trees.TypeDef) {
            Trees.TypeDef typeDef = (Trees.TypeDef)tree;
            bl = typeDef.mods().hasFlag(0x2000000000L);
        } else if (tree instanceof Trees.ValDef) {
            Trees.ValDef valDef = (Trees.ValDef)tree;
            bl = valDef.mods().hasFlag(0x2000000000L);
        } else {
            bl = false;
        }
        return bl;
    }

    public boolean isEarlyValDef(Trees.Tree tree) {
        boolean bl;
        if (tree instanceof Trees.ValDef) {
            Trees.ValDef valDef = (Trees.ValDef)tree;
            bl = valDef.mods().hasFlag(0x2000000000L);
        } else {
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isRepeatedParamType(Trees.Tree tpt) {
        boolean bl = false;
        Trees.AppliedTypeTree appliedTypeTree = null;
        if (tpt instanceof Trees.TypeTree) {
            return this.global().definitions().isRepeatedParamType(tpt.tpe());
        }
        if (tpt instanceof Trees.AppliedTypeTree) {
            bl = true;
            appliedTypeTree = (Trees.AppliedTypeTree)tpt;
            if (appliedTypeTree.tpt() instanceof Trees.Select) {
                Trees.Select select = (Trees.Select)appliedTypeTree.tpt();
                Names.TypeName typeName = this.global().tpnme().REPEATED_PARAM_CLASS_NAME();
                Names.Name name = select.name();
                if (typeName == null) {
                    if (name == null) return true;
                } else if (typeName.equals(name)) {
                    return true;
                }
            }
        }
        if (!bl) return false;
        if (!(appliedTypeTree.tpt() instanceof Trees.Select)) return false;
        Trees.Select select = (Trees.Select)appliedTypeTree.tpt();
        Names.TypeName typeName = this.global().tpnme().JAVA_REPEATED_PARAM_CLASS_NAME();
        Names.Name name = select.name();
        if (typeName != null) {
            if (!typeName.equals(name)) return false;
            return true;
        }
        if (name == null) return true;
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isByNameParamType(Trees.Tree tpt) {
        if (tpt instanceof Trees.TypeTree) {
            return this.global().definitions().isByNameParamType(tpt.tpe());
        }
        if (!(tpt instanceof Trees.AppliedTypeTree)) return false;
        Trees.AppliedTypeTree appliedTypeTree = (Trees.AppliedTypeTree)tpt;
        if (!(appliedTypeTree.tpt() instanceof Trees.Select)) return false;
        Trees.Select select = (Trees.Select)appliedTypeTree.tpt();
        Names.TypeName typeName = this.global().tpnme().BYNAME_PARAM_CLASS_NAME();
        Names.Name name = select.name();
        if (typeName != null) {
            if (!typeName.equals(name)) return false;
            return true;
        }
        if (name == null) return true;
        return false;
    }

    public Trees.Tree assignmentToMaybeNamedArg(Trees.Tree tree) {
        Trees.Tree tree2;
        Trees.Assign assign;
        if (tree instanceof Trees.Assign && (assign = (Trees.Assign)tree).lhs() instanceof Trees.Ident) {
            Trees.Ident ident = (Trees.Ident)assign.lhs();
            tree2 = this.global().atPos(assign.pos(), new Trees.AssignOrNamedArg(this.global(), ident, assign.rhs()));
        } else {
            tree2 = tree;
        }
        return tree2;
    }

    public boolean isLeftAssoc(Names.Name operator) {
        return operator.nonEmpty() && operator.endChar() != ':';
    }

    public boolean isSwitchAnnotation(Types.Type tpe) {
        return tpe.hasAnnotation(this.global().definitions().SwitchClass());
    }

    public boolean mayBeTypePat(Trees.Tree tree) {
        boolean bl;
        Trees.CompoundTypeTree compoundTypeTree;
        if (tree instanceof Trees.CompoundTypeTree && (compoundTypeTree = (Trees.CompoundTypeTree)tree).templ() != null && ((Object)Nil$.MODULE$).equals(compoundTypeTree.templ().body())) {
            bl = compoundTypeTree.templ().parents().exists((Function1<Trees.Tree, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TreeInfo $outer;

                public final boolean apply(Trees.Tree tree) {
                    return this.$outer.mayBeTypePat(tree);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        } else if (tree instanceof Trees.Annotated) {
            Trees.Annotated annotated = (Trees.Annotated)tree;
            bl = this.mayBeTypePat(annotated.arg());
        } else if (tree instanceof Trees.AppliedTypeTree) {
            Trees.AppliedTypeTree appliedTypeTree = (Trees.AppliedTypeTree)tree;
            bl = this.mayBeTypePat(appliedTypeTree.tpt()) || appliedTypeTree.args().exists((Function1<Trees.Tree, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Trees.Tree x$5) {
                    return x$5 instanceof Trees.Bind;
                }
            }));
        } else if (tree instanceof Trees.SelectFromTypeTree) {
            Trees.SelectFromTypeTree selectFromTypeTree = (Trees.SelectFromTypeTree)tree;
            bl = this.mayBeTypePat(selectFromTypeTree.qualifier());
        } else {
            bl = false;
        }
        return bl;
    }

    public boolean isWildcardStarArg(Trees.Tree tree) {
        Option<Trees.Tree> option = this.WildcardStarArg().unapply(tree);
        boolean bl = !option.isEmpty();
        return bl;
    }

    public TreeInfo$WildcardStarArg$ WildcardStarArg() {
        return this.WildcardStarArg$module == null ? this.WildcardStarArg$lzycompute() : this.WildcardStarArg$module;
    }

    public List<Trees.TypeDef> typeParameters(Trees.Tree tree) {
        List list2;
        if (tree instanceof Trees.DefDef) {
            Trees.DefDef defDef = (Trees.DefDef)tree;
            list2 = defDef.tparams();
        } else if (tree instanceof Trees.ClassDef) {
            Trees.ClassDef classDef = (Trees.ClassDef)tree;
            list2 = classDef.tparams();
        } else if (tree instanceof Trees.TypeDef) {
            Trees.TypeDef typeDef = (Trees.TypeDef)tree;
            list2 = typeDef.tparams();
        } else {
            list2 = Nil$.MODULE$;
        }
        return list2;
    }

    public boolean isWildcardStarArgList(List<Trees.Tree> trees) {
        return trees.nonEmpty() && this.isWildcardStarArg(trees.last());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isWildcardArg(Trees.Tree tree) {
        Trees.Tree tree2 = this.unbind(tree);
        if (!(tree2 instanceof Trees.Ident)) return false;
        Trees.Ident ident = (Trees.Ident)tree2;
        Names.Name name = this.global().nme().WILDCARD();
        Names.Name name2 = ident.name();
        if (name != null) {
            if (!name.equals(name2)) return false;
            return true;
        }
        if (name2 == null) return true;
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isWildcardStarType(Trees.Tree tree) {
        if (!(tree instanceof Trees.Ident)) return false;
        Trees.Ident ident = (Trees.Ident)tree;
        Names.TypeName typeName = this.global().tpnme().WILDCARD_STAR();
        Names.Name name = ident.name();
        if (typeName != null) {
            if (!typeName.equals(name)) return false;
            return true;
        }
        if (name == null) return true;
        return false;
    }

    public boolean isDefaultCase(Trees.CaseDef cdef) {
        boolean bl = cdef != null && ((Object)this.global().EmptyTree()).equals(cdef.guard()) ? this.isWildcardArg(cdef.pat()) : false;
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean hasNoSymbol(Trees.Tree t) {
        if (t.symbol() == null) return true;
        Symbols.Symbol symbol = t.symbol();
        Symbols.NoSymbol noSymbol = this.global().NoSymbol();
        if (symbol != null) {
            if (!symbol.equals(noSymbol)) return false;
            return true;
        }
        if (noSymbol == null) return true;
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isSyntheticDefaultCase(Trees.CaseDef cdef) {
        if (cdef == null) return false;
        if (!(cdef.pat() instanceof Trees.Bind)) return false;
        Trees.Bind bind = (Trees.Bind)cdef.pat();
        Names.TermName termName = this.global().nme().DEFAULT_CASE();
        Names.Name name = bind.name();
        if (termName == null) {
            if (name != null) {
                return false;
            }
        } else if (!termName.equals(name)) return false;
        if (!((Object)this.global().EmptyTree()).equals(cdef.guard())) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean catchesThrowable(Trees.CaseDef cdef) {
        if (!cdef.guard().isEmpty()) return false;
        boolean bl = false;
        Trees.Ident ident = null;
        Trees.Tree tree = this.unbind(cdef.pat());
        if (tree instanceof Trees.Ident) {
            bl = true;
            ident = (Trees.Ident)tree;
            Names.Name name = this.global().nme().WILDCARD();
            Names.Name name2 = ident.name();
            if (name == null) {
                if (name2 == null) return true;
            } else if (name.equals(name2)) {
                return true;
            }
        }
        if (!bl) return false;
        boolean bl2 = this.hasNoSymbol(ident);
        if (!bl2) return false;
        return true;
    }

    public boolean isSyntheticCase(Trees.CaseDef cdef) {
        return cdef.pat().exists((Function1<Trees.Tree, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Trees.Tree x0$7) {
                boolean bl;
                if (x0$7 instanceof Trees.DefTree) {
                    Trees.DefTree defTree = (Trees.DefTree)x0$7;
                    bl = defTree.symbol().isSynthetic();
                } else {
                    bl = false;
                }
                return bl;
            }
        }));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isCatchCase(Trees.CaseDef cdef) {
        Trees.Typed typed;
        if (cdef != null && cdef.pat() instanceof Trees.Typed && (typed = (Trees.Typed)cdef.pat()).expr() instanceof Trees.Ident) {
            Trees.Ident ident = (Trees.Ident)typed.expr();
            Names.Name name = this.global().nme().WILDCARD();
            Names.Name name2 = ident.name();
            if (!(name != null ? !name.equals(name2) : name2 != null) && ((Object)this.global().EmptyTree()).equals(cdef.guard())) {
                return this.isSimpleThrowable(typed.tpt().tpe());
            }
        }
        if (cdef == null) return this.isDefaultCase(cdef);
        if (!(cdef.pat() instanceof Trees.Bind)) return this.isDefaultCase(cdef);
        Trees.Bind bind = (Trees.Bind)cdef.pat();
        if (!(bind.body() instanceof Trees.Typed)) return this.isDefaultCase(cdef);
        Trees.Typed typed2 = (Trees.Typed)bind.body();
        if (!(typed2.expr() instanceof Trees.Ident)) return this.isDefaultCase(cdef);
        Trees.Ident ident = (Trees.Ident)typed2.expr();
        Names.Name name = this.global().nme().WILDCARD();
        Names.Name name3 = ident.name();
        if (name == null) {
            if (name3 != null) {
                return this.isDefaultCase(cdef);
            }
        } else if (!name.equals(name3)) return this.isDefaultCase(cdef);
        if (!((Object)this.global().EmptyTree()).equals(cdef.guard())) return this.isDefaultCase(cdef);
        return this.isSimpleThrowable(typed2.tpt().tpe());
    }

    private boolean isSimpleThrowable(Types.Type tp) {
        boolean bl;
        if (tp instanceof Types.TypeRef) {
            Types.TypeRef typeRef = (Types.TypeRef)tp;
            Types.Type type = typeRef.pre();
            Types$NoPrefix$ types$NoPrefix$ = this.global().NoPrefix();
            bl = !((type == null ? types$NoPrefix$ != null : !type.equals(types$NoPrefix$)) && !typeRef.pre().widen().typeSymbol().isStatic() || !typeRef.sym().isNonBottomSubClass(this.global().definitions().ThrowableClass()) || typeRef.sym().isTrait());
        } else {
            bl = false;
        }
        return bl;
    }

    public boolean isGuardedCase(Trees.CaseDef cdef) {
        Trees.Tree tree = cdef.guard();
        Trees$EmptyTree$ trees$EmptyTree$ = this.global().EmptyTree();
        return tree != null ? !((Object)tree).equals(trees$EmptyTree$) : trees$EmptyTree$ != null;
    }

    public boolean isSequenceValued(Trees.Tree tree) {
        boolean bl;
        Trees.Tree tree2 = this.unbind(tree);
        if (tree2 instanceof Trees.Alternative) {
            Trees.Alternative alternative = (Trees.Alternative)tree2;
            bl = alternative.trees().exists((Function1<Trees.Tree, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TreeInfo $outer;

                public final boolean apply(Trees.Tree tree) {
                    return this.$outer.isSequenceValued(tree);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        } else {
            boolean bl2 = tree2 instanceof Trees.ArrayValue ? true : tree2 instanceof Trees.Star;
            bl = bl2;
        }
        return bl;
    }

    public Trees.Tree unbind(Trees.Tree x) {
        Trees.Tree tree;
        if (x instanceof Trees.Bind) {
            Trees.Bind bind = (Trees.Bind)x;
            tree = this.unbind(bind.body());
        } else {
            tree = x;
        }
        return tree;
    }

    public boolean isStar(Trees.Tree x) {
        Trees.Tree tree = this.unbind(x);
        boolean bl = tree instanceof Trees.Star;
        return bl;
    }

    public int effectivePatternArity(List<Trees.Tree> args) {
        return this.flattenedPatternArgs(args).length();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public List<Trees.Tree> flattenedPatternArgs(List<Trees.Tree> args) {
        List<Trees.Tree> list2 = args.map(new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TreeInfo $outer;

            public final Trees.Tree apply(Trees.Tree x) {
                return this.$outer.unbind(x);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, List$.MODULE$.canBuildFrom());
        if (!(list2 instanceof $colon$colon)) return list2;
        $colon$colon $colon$colon = ($colon$colon)list2;
        Option<List<Trees.Tree>> option = this.global().build().SyntacticTuple().unapply((Trees.Tree)$colon$colon.head());
        if (option.isEmpty()) return list2;
        if (!((Object)Nil$.MODULE$).equals($colon$colon.tl$1())) return list2;
        return option.get();
    }

    public final int SYNTH_CASE_FLAGS() {
        return 0x200800;
    }

    public boolean isSynthCaseSymbol(Symbols.Symbol sym) {
        return sym.hasAllFlags(0x200800L);
    }

    public boolean hasSynthCaseSymbol(Trees.Tree t) {
        return t.symbol() != null && this.isSynthCaseSymbol(t.symbol());
    }

    public boolean isTraitRef(Trees.Tree tree) {
        Symbols.Symbol sym = tree.tpe() == null ? null : tree.tpe().typeSymbol();
        return sym != null && sym.initialize().isTrait();
    }

    public Applied dissectApplied(Trees.Tree tree) {
        return new Applied(this, tree);
    }

    public TreeInfo$Applied$ Applied() {
        return this.Applied$module == null ? this.Applied$lzycompute() : this.Applied$module;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean firstDefinesClassOrObject(List<Trees.Tree> trees, Names.Name name) {
        boolean bl = false;
        $colon$colon $colon$colon = null;
        if (trees instanceof $colon$colon) {
            bl = true;
            $colon$colon = ($colon$colon)trees;
            if ($colon$colon.head() instanceof Trees.Import) {
                return this.firstDefinesClassOrObject($colon$colon.tl$1(), name);
            }
        }
        if (bl && $colon$colon.head() instanceof Trees.Annotated) {
            Trees.Annotated annotated = (Trees.Annotated)$colon$colon.head();
            return this.firstDefinesClassOrObject((List<Trees.Tree>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.Tree[]{annotated.arg()})), name);
        }
        if (bl && $colon$colon.head() instanceof Trees.ModuleDef) {
            Trees.ModuleDef moduleDef = (Trees.ModuleDef)$colon$colon.head();
            Names.Name name2 = name;
            Names.TermName termName = moduleDef.name();
            if (name2 == null) {
                if (termName == null) return true;
            } else if (name2.equals(termName)) {
                return true;
            }
        }
        if (!bl) return false;
        if (!($colon$colon.head() instanceof Trees.ClassDef)) return false;
        Trees.ClassDef classDef = (Trees.ClassDef)$colon$colon.head();
        Names.Name name3 = name;
        Names.TypeName typeName = classDef.name();
        if (name3 != null) {
            if (!name3.equals(typeName)) return false;
            return true;
        }
        if (typeName == null) return true;
        return false;
    }

    public TreeInfo$Unapplied$ Unapplied() {
        return this.Unapplied$module == null ? this.Unapplied$lzycompute() : this.Unapplied$module;
    }

    public boolean noPredefImportForUnit(Trees.Tree body2) {
        return this.isUnitInScala$1(body2, this.global().nme().Predef()) || this.scala$reflect$internal$TreeInfo$$isLeadingPredefImport$1(body2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isAbsTypeDef(Trees.Tree tree) {
        boolean bl = false;
        Trees.TypeDef typeDef = null;
        if (tree instanceof Trees.TypeDef) {
            bl = true;
            typeDef = (Trees.TypeDef)tree;
            if (typeDef.rhs() instanceof Trees.TypeBoundsTree) {
                return true;
            }
        }
        if (!bl) return false;
        return typeDef.rhs().tpe() instanceof Types.TypeBounds;
    }

    public boolean isAliasTypeDef(Trees.Tree tree) {
        boolean bl = tree instanceof Trees.TypeDef ? !this.isAbsTypeDef(tree) : false;
        return bl;
    }

    public TreeInfo$IsTrue$ IsTrue() {
        return this.IsTrue$module == null ? this.IsTrue$lzycompute() : this.IsTrue$module;
    }

    public TreeInfo$IsFalse$ IsFalse() {
        return this.IsFalse$module == null ? this.IsFalse$lzycompute() : this.IsFalse$module;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isApplyDynamicName(Names.Name name) {
        Names.Name name2 = name;
        Names.TermName termName = this.global().nme().updateDynamic();
        if (name2 == null) {
            if (termName == null) return true;
        } else if (name2.equals(termName)) return true;
        Names.Name name3 = name;
        Names.TermName termName2 = this.global().nme().selectDynamic();
        if (name3 == null) {
            if (termName2 == null) return true;
        } else if (name3.equals(termName2)) return true;
        Names.Name name4 = name;
        Names.TermName termName3 = this.global().nme().applyDynamic();
        if (name4 == null) {
            if (termName3 == null) return true;
        } else if (name4.equals(termName3)) return true;
        Names.Name name5 = name;
        Names.TermName termName4 = this.global().nme().applyDynamicNamed();
        if (name5 != null) {
            if (!name5.equals(termName4)) return false;
            return true;
        }
        if (termName4 == null) return true;
        return false;
    }

    public TreeInfo$DynamicUpdate$ DynamicUpdate() {
        return this.DynamicUpdate$module == null ? this.DynamicUpdate$lzycompute() : this.DynamicUpdate$module;
    }

    public TreeInfo$DynamicApplication$ DynamicApplication() {
        return this.DynamicApplication$module == null ? this.DynamicApplication$lzycompute() : this.DynamicApplication$module;
    }

    public TreeInfo$DynamicApplicationNamed$ DynamicApplicationNamed() {
        return this.DynamicApplicationNamed$module == null ? this.DynamicApplicationNamed$lzycompute() : this.DynamicApplicationNamed$module;
    }

    public TreeInfo$MacroImplReference$ MacroImplReference() {
        return this.MacroImplReference$module == null ? this.MacroImplReference$lzycompute() : this.MacroImplReference$module;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isNullaryInvocation(Trees.Tree tree) {
        if (tree.symbol() == null) return false;
        if (!tree.symbol().isMethod()) return false;
        if (!(tree instanceof Trees.TypeApply)) {
            if (!(tree instanceof Trees.RefTree)) return false;
            return true;
        }
        Trees.TypeApply typeApply = (Trees.TypeApply)tree;
        boolean bl = this.isNullaryInvocation(typeApply.fun());
        if (!bl) return false;
        return true;
    }

    public boolean isMacroApplication(Trees.Tree tree) {
        Symbols.Symbol sym;
        return !tree.isDef() && (sym = tree.symbol()) != null && sym.isTermMacro() && !sym.isErroneous();
    }

    public boolean isMacroApplicationOrBlock(Trees.Tree tree) {
        boolean bl;
        if (tree instanceof Trees.Block) {
            Trees.Block block = (Trees.Block)tree;
            bl = this.isMacroApplicationOrBlock(block.expr());
        } else {
            bl = this.isMacroApplication(tree);
        }
        return bl;
    }

    private final boolean isWarnableRefTree$1(Trees.Tree tree$1) {
        Trees.RefTree refTree;
        boolean bl = tree$1 instanceof Trees.RefTree ? this.isExprSafeToInline((refTree = (Trees.RefTree)((Object)tree$1)).qualifier()) && ((Trees.SymTree)((Object)refTree)).symbol() != null && ((Trees.SymTree)((Object)refTree)).symbol().isAccessor() : false;
        return bl;
    }

    private final boolean isWarnableSymbol$1(Trees.Tree tree$1) {
        boolean bl;
        Symbols.Symbol sym = tree$1.symbol();
        if (sym != null && (sym.isModule() || sym.isLazy() || this.global().definitions().isByNameParamType(sym.tpe_$times()))) {
            this.global().debuglog((Function0<String>)((Object)new Serializable(this, tree$1){
                public static final long serialVersionUID = 0L;
                private final Trees.Tree tree$1;

                public final String apply() {
                    return new StringBuilder().append((Object)"'Pure' but side-effecting expression in statement position: ").append(this.tree$1).toString();
                }
                {
                    this.tree$1 = tree$1;
                }
            }));
            bl = false;
        } else {
            bl = true;
        }
        return bl;
    }

    private final boolean fail$1(List params$1, List args$1) {
        this.global().devWarning((Function0<String>)((Object)new Serializable(this, params$1, args$1){
            public static final long serialVersionUID = 0L;
            private final List params$1;
            private final List args$1;

            public final String apply() {
                String string2 = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"|Mismatch trying to zip method parameters and argument list:\n            |  params = ", "\n            |    args = ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.params$1, this.args$1}));
                Predef$ predef$ = Predef$.MODULE$;
                return new StringOps(string2).stripMargin();
            }
            {
                this.params$1 = params$1;
                this.args$1 = args$1;
            }
        }));
        return false;
    }

    private final Symbols.Symbol sym$1(Trees.Tree tree$2) {
        return tree$2.symbol();
    }

    private final boolean isVar$1(Trees.Tree tree$2) {
        return this.sym$1(tree$2).isVariable();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean isGetter$1(Trees.Tree tree$2) {
        if (!this.mayBeVarGetter(this.sym$1(tree$2))) return false;
        Symbols.Symbol symbol = this.sym$1(tree$2).owner().info().member(this.sym$1(tree$2).setterName());
        Symbols.NoSymbol noSymbol = this.global().NoSymbol();
        if (symbol == null) {
            if (noSymbol == null) return false;
            return true;
        } else if (symbol.equals(noSymbol)) return false;
        return true;
    }

    private final boolean isVarPatternDeep0$1(Trees.Tree tree) {
        while (tree instanceof Trees.Bind) {
            Trees.Bind bind = (Trees.Bind)tree;
            tree = bind.body();
        }
        boolean bl = tree instanceof Trees.Ident ? this.isVarPattern(tree) : false;
        return bl;
    }

    private final List filterBody$1(List body2, List tbody$1) {
        return (List)body2.filter((Function1)((Object)new Serializable(this, tbody$1){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ TreeInfo $outer;
            private final List tbody$1;

            public final boolean apply(Trees.Tree x0$1) {
                Trees.MemberDef memberDef;
                Trees.DefDef defDef;
                boolean bl = x0$1 instanceof Trees.ValDef ? true : x0$1 instanceof Trees.TypeDef;
                boolean bl2 = bl ? true : (x0$1 instanceof Trees.DefDef && (defDef = (Trees.DefDef)x0$1).mods().hasAccessorFlag() ? !this.$outer.global().nme().isSetterName(defDef.name()) && !this.tbody$1.exists(new Serializable(this, defDef){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ $anonfun$filterBody$1$1 $outer;
                    private final Trees.DefDef x4$1;

                    public final boolean apply(Trees.Tree x0$2) {
                        boolean bl;
                        if (x0$2 instanceof Trees.ValDef) {
                            Trees.ValDef valDef = (Trees.ValDef)x0$2;
                            Names.TermName termName = this.x4$1.name();
                            Names.TermName termName2 = this.$outer.$outer.global().TermNameOps(valDef.name()).dropLocal();
                            bl = !(termName != null ? !termName.equals(termName2) : termName2 != null);
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
                        this.x4$1 = x4$1;
                    }
                }) : (x0$1 instanceof Trees.MemberDef ? !(memberDef = (Trees.MemberDef)x0$1).mods().isSynthetic() : true));
                return bl2;
            }

            public /* synthetic */ TreeInfo scala$reflect$internal$TreeInfo$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tbody$1 = tbody$1;
            }
        }));
    }

    public final Trees.Tree scala$reflect$internal$TreeInfo$$lazyValDefRhs$1(Trees.Tree body2) {
        Trees.Tree tree;
        Trees.Tree tree2;
        Trees.Block block;
        Some<List<Trees.Tree>> some;
        if (body2 instanceof Trees.Block && !(some = List$.MODULE$.unapplySeq((block = (Trees.Block)body2).stats())).isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(1) == 0 && (tree2 = (Trees.Tree)((LinearSeqOptimized)some.get()).apply(0)) instanceof Trees.Assign) {
            Trees.Assign assign = (Trees.Assign)tree2;
            tree = assign.rhs();
        } else {
            tree = body2;
        }
        return tree;
    }

    private final List recoverBody$1(List body2, List tbody$1) {
        return body2.map(new Serializable(this, tbody$1){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ TreeInfo $outer;
            private final List tbody$1;

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final Trees.Tree apply(Trees.Tree x0$3) {
                if (x0$3 instanceof Trees.ValDef) {
                    Trees.ValDef valDef = (Trees.ValDef)x0$3;
                    if (this.$outer.global().nme().isLocalName(valDef.name())) {
                        Option option;
                        Serializable serializable = new Serializable(this, valDef){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ $anonfun$recoverBody$1$1 $outer;
                            private final Trees.ValDef x2$1;

                            public final Trees.ValDef apply(Trees.Tree dd) {
                                if (dd instanceof Trees.DefDef) {
                                    Trees.DefDef defDef = (Trees.DefDef)dd;
                                    Tuple3<Trees.Modifiers, Names.TermName, Trees.Tree> tuple3 = new Tuple3<Trees.Modifiers, Names.TermName, Trees.Tree>(defDef.mods(), defDef.name(), defDef.rhs());
                                    Trees.Modifiers dmods = tuple3._1();
                                    Names.TermName dname = tuple3._2();
                                    Trees.Tree drhs = tuple3._3();
                                    Trees.Modifiers vdMods = this.x2$1.mods().$amp$tilde(524293L).$bar(dmods.$amp(524293L).flags());
                                    Trees.Tree vdRhs = this.x2$1.mods().isLazy() ? this.$outer.$outer.scala$reflect$internal$TreeInfo$$lazyValDefRhs$1(drhs) : this.x2$1.rhs();
                                    Trees.ValDef x$8 = this.x2$1;
                                    Trees.Tree x$12 = this.$outer.$outer.global().copyValDef$default$4(x$8);
                                    return this.$outer.$outer.global().copyValDef(x$8, vdMods, dname, x$12, vdRhs);
                                }
                                throw new MatchError(dd);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                                this.x2$1 = x2$1;
                            }
                        };
                        Option<Trees.Tree> option2 = this.tbody$1.find(new Serializable(this, valDef){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ $anonfun$recoverBody$1$1 $outer;
                            private final Trees.ValDef x2$1;

                            public final boolean apply(Trees.Tree x0$4) {
                                boolean bl;
                                if (x0$4 instanceof Trees.DefDef) {
                                    Trees.DefDef defDef = (Trees.DefDef)x0$4;
                                    Names.TermName termName = defDef.name();
                                    Names.TermName termName2 = this.$outer.$outer.global().TermNameOps(this.x2$1.name()).dropLocal();
                                    bl = !(termName != null ? !termName.equals(termName2) : termName2 != null);
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
                                this.x2$1 = x2$1;
                            }
                        });
                        if (!option2.isEmpty()) {
                            Trees.Tree tree = option2.get();
                            Some<Trees.ValDef> some = new Some<Trees.ValDef>(serializable.apply(tree));
                            option = some;
                        } else {
                            option = None$.MODULE$;
                        }
                        Option option3 = option;
                        return (Trees.Tree)(!option.isEmpty() ? option3.get() : valDef);
                    }
                }
                if (!(x0$3 instanceof Trees.DefDef)) return x0$3;
                Trees.DefDef defDef = (Trees.DefDef)x0$3;
                if (!defDef.mods().hasAccessorFlag()) return x0$3;
                Trees.Modifiers vdMods = (defDef.mods().hasStableFlag() ? defDef.mods().$amp$tilde(0x400000L) : defDef.mods().$bar(4096)).$amp$tilde(0x8000000L);
                return new Trees.ValDef(this.$outer.global(), vdMods, defDef.name(), defDef.tpt(), defDef.rhs());
            }

            public /* synthetic */ TreeInfo scala$reflect$internal$TreeInfo$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tbody$1 = tbody$1;
            }
        }, List$.MODULE$.canBuildFrom());
    }

    public final boolean scala$reflect$internal$TreeInfo$$isLeadingPredefImport$1(Trees.Tree defn) {
        boolean bl;
        if (defn instanceof Trees.PackageDef) {
            Trees.PackageDef packageDef = (Trees.PackageDef)defn;
            bl = packageDef.stats().exists((Function1<Trees.Tree, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TreeInfo $outer;

                public final boolean apply(Trees.Tree defn) {
                    return this.$outer.scala$reflect$internal$TreeInfo$$isLeadingPredefImport$1(defn);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        } else if (defn instanceof Trees.Import) {
            Trees.Import import_ = (Trees.Import)defn;
            bl = this.global().isReferenceToPredef(import_.expr());
        } else {
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean isUnitInScala$1(Trees.Tree tree, Names.Name name) {
        if (!(tree instanceof Trees.PackageDef)) return false;
        Trees.PackageDef packageDef = (Trees.PackageDef)tree;
        if (!(packageDef.pid() instanceof Trees.Ident)) return false;
        Trees.Ident ident = (Trees.Ident)packageDef.pid();
        Names.TermName termName = this.global().nme().scala_();
        Names.Name name2 = ident.name();
        if (termName != null) {
            if (!termName.equals(name2)) return false;
            return this.firstDefinesClassOrObject(packageDef.stats(), name);
        }
        if (name2 == null) return this.firstDefinesClassOrObject(packageDef.stats(), name);
        return false;
    }

    public class Applied {
        private final Trees.Tree tree;
        public final /* synthetic */ TreeInfo $outer;

        public Trees.Tree tree() {
            return this.tree;
        }

        public Trees.Tree callee() {
            return this.loop$1(this.tree());
        }

        public Trees.Tree core() {
            Trees.Tree tree;
            Trees.Tree tree2 = this.callee();
            if (tree2 instanceof Trees.TypeApply) {
                Trees.TypeApply typeApply = (Trees.TypeApply)tree2;
                tree = typeApply.fun();
            } else if (tree2 instanceof Trees.AppliedTypeTree) {
                Trees.AppliedTypeTree appliedTypeTree = (Trees.AppliedTypeTree)tree2;
                tree = appliedTypeTree.tpt();
            } else {
                tree = tree2;
            }
            return tree;
        }

        public List<Trees.Tree> targs() {
            List list2;
            Trees.Tree tree = this.callee();
            if (tree instanceof Trees.TypeApply) {
                Trees.TypeApply typeApply = (Trees.TypeApply)tree;
                list2 = typeApply.args();
            } else if (tree instanceof Trees.AppliedTypeTree) {
                Trees.AppliedTypeTree appliedTypeTree = (Trees.AppliedTypeTree)tree;
                list2 = appliedTypeTree.args();
            } else {
                list2 = Nil$.MODULE$;
            }
            return list2;
        }

        public List<List<Trees.Tree>> argss() {
            return this.loop$2(this.tree());
        }

        public /* synthetic */ TreeInfo scala$reflect$internal$TreeInfo$Applied$$$outer() {
            return this.$outer;
        }

        private final Trees.Tree loop$1(Trees.Tree tree) {
            while (tree instanceof Trees.Apply) {
                Trees.Apply apply2 = (Trees.Apply)tree;
                tree = apply2.fun();
            }
            return tree;
        }

        private final List loop$2(Trees.Tree tree) {
            List list2;
            if (tree instanceof Trees.Apply) {
                Trees.Apply apply2 = (Trees.Apply)tree;
                list2 = this.loop$2(apply2.fun()).$colon$plus(apply2.args(), List$.MODULE$.canBuildFrom());
            } else {
                list2 = Nil$.MODULE$;
            }
            return list2;
        }

        public Applied(TreeInfo $outer, Trees.Tree tree) {
            this.tree = tree;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public abstract class SeeThroughBlocks<T> {
        public abstract T unapplyImpl(Trees.Tree var1);

        public T unapply(Trees.Tree x) {
            Trees.Block block;
            T t = x instanceof Trees.Block && ((Object)Nil$.MODULE$).equals((block = (Trees.Block)x).stats()) ? this.unapply(block.expr()) : this.unapplyImpl(x);
            return t;
        }

        public /* synthetic */ TreeInfo scala$reflect$internal$TreeInfo$SeeThroughBlocks$$$outer() {
            return TreeInfo.this;
        }

        public SeeThroughBlocks() {
            if (TreeInfo.this == null) {
                throw null;
            }
        }
    }

    public class DynamicApplicationExtractor {
        private final Function1<Names.Name, Object> nameTest;
        public final /* synthetic */ TreeInfo $outer;

        /*
         * WARNING - void declaration
         * Enabled aggressive block sorting
         */
        public Option<Tuple2<Trees.Tree, Object>> unapply(Trees.Tree tree) {
            void var17_13;
            boolean bl = false;
            Trees.Apply apply2 = null;
            if (tree instanceof Trees.Apply) {
                Trees.TypeApply typeApply;
                bl = true;
                apply2 = (Trees.Apply)tree;
                if (apply2.fun() instanceof Trees.TypeApply && (typeApply = (Trees.TypeApply)apply2.fun()).fun() instanceof Trees.Select) {
                    Trees.Literal literal;
                    Trees.Tree tree2;
                    Trees.Select select = (Trees.Select)typeApply.fun();
                    Some<List<Trees.Tree>> some = List$.MODULE$.unapplySeq(apply2.args());
                    if (!some.isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(1) == 0 && (tree2 = (Trees.Tree)((LinearSeqOptimized)some.get()).apply(0)) instanceof Trees.Literal && (literal = (Trees.Literal)tree2).value() != null && BoxesRunTime.unboxToBoolean(this.nameTest.apply(select.name()))) {
                        Some<Tuple2<Trees.Tree, Object>> some2 = new Some<Tuple2<Trees.Tree, Object>>(new Tuple2<Trees.Tree, Object>(select.qualifier(), literal.value().value()));
                        return var17_13;
                    }
                }
            }
            if (bl && apply2.fun() instanceof Trees.Select) {
                Trees.Literal literal;
                Trees.Tree tree3;
                Trees.Select select = (Trees.Select)apply2.fun();
                Some<List<Trees.Tree>> some = List$.MODULE$.unapplySeq(apply2.args());
                if (!some.isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(1) == 0 && (tree3 = (Trees.Tree)((LinearSeqOptimized)some.get()).apply(0)) instanceof Trees.Literal && (literal = (Trees.Literal)tree3).value() != null && BoxesRunTime.unboxToBoolean(this.nameTest.apply(select.name()))) {
                    Some<Tuple2<Trees.Tree, Object>> some3 = new Some<Tuple2<Trees.Tree, Object>>(new Tuple2<Trees.Tree, Object>(select.qualifier(), literal.value().value()));
                    return var17_13;
                }
            }
            if (bl && apply2.fun() instanceof Trees.Ident) {
                Trees.Literal literal;
                Trees.Tree tree4;
                Trees.Ident ident = (Trees.Ident)apply2.fun();
                Some<List<Trees.Tree>> some = List$.MODULE$.unapplySeq(apply2.args());
                if (!some.isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(1) == 0 && (tree4 = (Trees.Tree)((LinearSeqOptimized)some.get()).apply(0)) instanceof Trees.Literal && (literal = (Trees.Literal)tree4).value() != null && BoxesRunTime.unboxToBoolean(this.nameTest.apply(ident.name()))) {
                    Some<Tuple2<Trees$EmptyTree$, Object>> some4 = new Some<Tuple2<Trees$EmptyTree$, Object>>(new Tuple2<Trees$EmptyTree$, Object>(this.scala$reflect$internal$TreeInfo$DynamicApplicationExtractor$$$outer().global().EmptyTree(), literal.value().value()));
                    return var17_13;
                }
            }
            None$ none$ = None$.MODULE$;
            return var17_13;
        }

        public /* synthetic */ TreeInfo scala$reflect$internal$TreeInfo$DynamicApplicationExtractor$$$outer() {
            return this.$outer;
        }

        public DynamicApplicationExtractor(TreeInfo $outer, Function1<Names.Name, Object> nameTest) {
            this.nameTest = nameTest;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }
}

