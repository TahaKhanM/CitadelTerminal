/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Predef$any2stringadd$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Set$;
import scala.collection.mutable.MutableList;
import scala.collection.mutable.MutableList$;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WeakHashMap;
import scala.ref.WeakReference;
import scala.ref.WeakReference$;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Internals;
import scala.reflect.api.Symbols;
import scala.reflect.api.Universe;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Importers$StandardImporter$;
import scala.reflect.internal.Importers$StandardImporter$reverse$;
import scala.reflect.internal.Mirrors;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.StdAttachments;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$NoType$;
import scala.reflect.internal.tpe.TypeConstraints;
import scala.reflect.internal.util.NoPosition$;
import scala.reflect.internal.util.Position;
import scala.runtime.BoxedUnit;

@ScalaSignature(bytes="\u0006\u0001\r%a\u0001C\u0001\u0003!\u0003\r\t!\u0003\u001c\u0003\u0013%k\u0007o\u001c:uKJ\u001c(BA\u0002\u0005\u0003!Ig\u000e^3s]\u0006d'BA\u0003\u0007\u0003\u001d\u0011XM\u001a7fGRT\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001!\u0002\u0005\u0002\f\u00195\ta!\u0003\u0002\u000e\r\t1\u0011I\\=SK\u001aDQa\u0004\u0001\u0005\u0002A\ta\u0001J5oSR$C#A\t\u0011\u0005-\u0011\u0012BA\n\u0007\u0005\u0011)f.\u001b;\t\u000bU\u0001A\u0011\t\f\u0002\u00155\\\u0017*\u001c9peR,'\u000f\u0006\u0002\u0018OI\u0011\u0001D\u0007\u0004\u00053\u0001\u0001qC\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0002\u001c95\t\u0001!\u0003\u0002\u001e=\tA\u0011*\u001c9peR,'/\u0003\u0002 A\tI\u0011J\u001c;fe:\fGn\u001d\u0006\u0003C\u0011\t1!\u00199j\u0011\u001d\u0019\u0003D1A\u0007B\u0011\nAA\u001a:p[V\tQE\u0004\u0002'O1\u0001\u0001\"\u0002\u0015\u0015\u0001\u0004I\u0013!\u00024s_6\u0004\u0004C\u0001\u0016,\u001b\u0005\u0001\u0013B\u0001\u0017!\u0005!)f.\u001b<feN,g!\u0002\u0018\u0001\u0003\u0003y#\u0001E*uC:$\u0017M\u001d3J[B|'\u000f^3s'\ri#B\u0007\u0005\u0006c5\"\tAM\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003M\u0002\"aG\u0017\t\u000f\rj#\u0019!D\u0001kU\ta\u0007\u0005\u00028q5\t!!\u0003\u0002:\u0005\tY1+_7c_2$\u0016M\u00197f\u0011!YT\u0006#b\u0001\n#a\u0014AB:z[6\u000b\u0007/F\u0001>!\u0011qt\b\u001c:\u000e\u000352A\u0001Q\u0017\t\u0003\n)1)Y2iKV\u0019!\tT-\u0014\u0005}\u001a\u0005\u0003\u0002#J\u0017Jk\u0011!\u0012\u0006\u0003\r\u001e\u000bq!\\;uC\ndWM\u0003\u0002I\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005)+%aC,fC.D\u0015m\u001d5NCB\u0004\"A\n'\u0005\u000b5{$\u0019\u0001(\u0003\u0003-\u000b\"a\u0014\u0006\u0011\u0005-\u0001\u0016BA)\u0007\u0005\u001dqu\u000e\u001e5j]\u001e\u00042a\u0015,Y\u001b\u0005!&BA+\u0007\u0003\r\u0011XMZ\u0005\u0003/R\u0013QbV3bWJ+g-\u001a:f]\u000e,\u0007C\u0001\u0014Z\t\u0015QvH1\u0001O\u0005\u00051\u0006\"B\u0019@\t\u0003aF#A/\u0011\tyz4\n\u0017\u0005\u0006?~\"\t\u0001Y\u0001\bo\u0016\f7nR3u)\t\tG\rE\u0002\fEbK!a\u0019\u0004\u0003\r=\u0003H/[8o\u0011\u0015)g\f1\u0001L\u0003\rYW-\u001f\u0005\u0006O~\"\t\u0001[\u0001\u000bo\u0016\f7.\u00169eCR,GcA\tjU\")QM\u001aa\u0001\u0017\")1N\u001aa\u00011\u0006)a/\u00197vKB\u0011QN\u001c\b\u0003}QJ!a\u001c9\u0003\rMKXNY8m\u0013\t\t(AA\u0004Ts6\u0014w\u000e\\:\u0011\u0005mq\u0007\u0002\u0003;.\u0011\u0003\u0005\u000b\u0015B\u001f\u0002\u000fMLX.T1qA!Aa/\fEC\u0002\u0013Eq/\u0001\u0004ua\u0016l\u0015\r]\u000b\u0002qB!ahP=\u007f!\ti'0\u0003\u0002|y\n!A+\u001f9f\u0013\ti(AA\u0003UsB,7\u000f\u0005\u0002\u001cu\"I\u0011\u0011A\u0017\t\u0002\u0003\u0006K\u0001_\u0001\biB,W*\u00199!\u0011%\t)!\fa\u0001\n\u0003\t9!A\u0006qK:$\u0017N\\4Ts6\u001cXCAA\u0005!\rY\u00111B\u0005\u0004\u0003\u001b1!aA%oi\"I\u0011\u0011C\u0017A\u0002\u0013\u0005\u00111C\u0001\u0010a\u0016tG-\u001b8h'fl7o\u0018\u0013fcR\u0019\u0011#!\u0006\t\u0015\u0005]\u0011qBA\u0001\u0002\u0004\tI!A\u0002yIEB\u0001\"a\u0007.A\u0003&\u0011\u0011B\u0001\ra\u0016tG-\u001b8h'fl7\u000f\t\u0005\n\u0003?i\u0003\u0019!C\u0001\u0003\u000f\t1\u0002]3oI&tw\r\u00169fg\"I\u00111E\u0017A\u0002\u0013\u0005\u0011QE\u0001\u0010a\u0016tG-\u001b8h)B,7o\u0018\u0013fcR\u0019\u0011#a\n\t\u0015\u0005]\u0011\u0011EA\u0001\u0002\u0004\tI\u0001\u0003\u0005\u0002,5\u0002\u000b\u0015BA\u0005\u00031\u0001XM\u001c3j]\u001e$\u0006/Z:!\u0011)\ty#\fEC\u0002\u0013\u0005\u0011\u0011G\u0001\u0007M&DX\u000f]:\u0016\u0005\u0005M\u0002#\u0002#\u00026\u0005e\u0012bAA\u001c\u000b\nYQ*\u001e;bE2,G*[:u!\u0011Y\u00111H\t\n\u0007\u0005ubAA\u0005Gk:\u001cG/[8oa!Q\u0011\u0011I\u0017\t\u0002\u0003\u0006K!a\r\u0002\u000f\u0019L\u00070\u001e9tA!9\u0011QI\u0017\u0005\u0002\u0005\u001d\u0013\u0001C1eI\u001aK\u00070\u001e9\u0015\u0007E\tI\u0005C\u0005\u0002L\u0005\rC\u00111\u0001\u0002N\u0005)a-\u001b=vaB!1\"a\u0014\u0012\u0013\r\t\tF\u0002\u0002\ty\tLh.Y7f}!1\u0011QK\u0017\u0005\u0002A\t\u0001\u0002\u001e:z\r&DX\u000f]\u0004\b\u00033j\u0003\u0012AA.\u0003\u001d\u0011XM^3sg\u0016\u00042APA/\r\u001d\ty&\fE\u0001\u0003C\u0012qA]3wKJ\u001cXm\u0005\u0003\u0002^\u0005\r\u0004CA7.\u0011\u001d\t\u0014Q\fC\u0001\u0003O\"\"!a\u0017\t\u0013\r\niF1A\u0005\u0002\u0005-T#A\u000e\t\u0011\u0005=\u0014Q\fQ\u0001\nm\tQA\u001a:p[\u0002Bq!a\u001d.\t#\t)(\u0001\rsK\u000e\u0014X-\u0019;fINKXNY8m\u0007>l\u0007\u000f\\3uKJ$b!a\u001e\u0002\b\u0006-%CBA=\u0003w\n\tI\u0002\u0004\u001a\u0003c\u0002\u0011q\u000f\t\u00047\u0005u\u0014bAA@y\naA*\u0019>z!>d\u0017\u0010V=qKB\u00191$a!\n\u0007\u0005\u0015EPA\u000bGY\u0006<\u0017i\u001a8pgRL7mQ8na2,G/\u001a:\t\u000f\u0005%\u0015\u0011\u000fa\u0001e\u0006\u0011Q.\u001f\u0005\b\u0003\u001b\u000b\t\b1\u0001m\u0003\u0015!\b.Z5s\u0011\u001d\t\t*\fC\t\u0003'\u000baB]3de\u0016\fG/Z*z[\n|G\u000eF\u0002s\u0003+Cq!!$\u0002\u0010\u0002\u0007A\u000eC\u0004\u0002\u001a6\"\t!a'\u0002\u0019%l\u0007o\u001c:u'fl'm\u001c7\u0015\u0007I\fi\nC\u0004\u0002 \u0006]\u0005\u0019\u00017\u0002\rQDW-\u001b:1\u0011\u001d\t\u0019+\fC\u0001\u0003K\u000bAB]3de\u0016\fG/\u001a+za\u0016$2A`AT\u0011\u001d\ti)!)A\u0002eDq!a+.\t\u0003\ti+\u0001\u0006j[B|'\u000f\u001e+za\u0016$2A`AX\u0011\u001d\ti)!+A\u0002eDq!a-.\t\u0003\t),\u0001\fsK\u000e\u0014X-\u0019;fIR\u0013X-Z\"p[BdW\r^3s)\u0015\t\u0012qWAb\u0011!\ti)!-A\u0002\u0005e\u0006cA7\u0002<&!\u0011QXA`\u0005\u0011!&/Z3\n\u0007\u0005\u0005'AA\u0003Ue\u0016,7\u000f\u0003\u0005\u0002\n\u0006E\u0006\u0019AAc!\rY\u00121\u0018\u0005\b\u0003\u0013lC\u0011AAf\u00031\u0011Xm\u0019:fCR,GK]3f)\u0011\t)-!4\t\u0011\u00055\u0015q\u0019a\u0001\u0003sCq!!5.\t\u0003\t\u0019.\u0001\u0006j[B|'\u000f\u001e+sK\u0016$B!!2\u0002V\"A\u0011QRAh\u0001\u0004\tI\fC\u0004\u0002Z6\"\t!a7\u0002#%l\u0007o\u001c:u\u0003R$\u0018m\u00195nK:$8\u000f\u0006\u0003\u0002^\u0006E\bCBAp\u0003K\fYOD\u0002\f\u0003CL1!a9\u0007\u0003\u0019\u0001&/\u001a3fM&!\u0011q]Au\u0005\r\u0019V\r\u001e\u0006\u0004\u0003G4\u0001cA\u0006\u0002n&\u0019\u0011q\u001e\u0004\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002t\u0006]\u0007\u0019AAo\u0003-\tG\u000f^1dQ6,g\u000e^:\t\u000f\u0005]X\u0006\"\u0001\u0002z\u0006!\u0012.\u001c9peR\feN\\8uCRLwN\\%oM>$B!a?\u0003\u0006A\u00191$!@\n\t\u0005}(\u0011\u0001\u0002\u000f\u0003:tw\u000e^1uS>t\u0017J\u001c4p\u0013\r\u0011\u0019A\u0001\u0002\u0010\u0003:tw\u000e^1uS>t\u0017J\u001c4pg\"A!qAA{\u0001\u0004\u0011I!A\u0002b]:\u00042!\\A\u007f\u0011\u001d\u0011i!\fC\u0001\u0005\u001f\ta\"[7q_J$\u0018I\u001c8pi\u0006\u0013x\r\u0006\u0003\u0003\u0012\t]\u0001cA\u000e\u0003\u0014%!!Q\u0003B\u0001\u0005E\u0019E.Y:tM&dW-\u00118o_R\f%o\u001a\u0005\t\u00053\u0011Y\u00011\u0001\u0003\u001c\u0005\u0019\u0011M]4\u0011\u00075\u0014\u0019\u0002C\u0004\u0003 5\"\tA!\t\u0002\u001d%l\u0007o\u001c:u!>\u001c\u0018\u000e^5p]R!!1\u0005B\u0017!\rY\"QE\u0005\u0005\u0005O\u0011IC\u0001\u0005Q_NLG/[8o\u0013\r\u0011YC\u0001\u0002\n!>\u001c\u0018\u000e^5p]ND\u0001\"!$\u0003\u001e\u0001\u0007!q\u0006\t\u0004[\n\u0015\u0002b\u0002B\u001a[\u0011\u0005!QG\u0001\fS6\u0004xN\u001d;TG>\u0004X\r\u0006\u0003\u00038\t\u0005\u0003cA\u000e\u0003:%!!1\bB\u001f\u0005\u0015\u00196m\u001c9f\u0013\r\u0011yD\u0001\u0002\u0007'\u000e|\u0007/Z:\t\u0011\t\r#\u0011\u0007a\u0001\u0005\u000b\nQ\u0001Z3dYN\u00042!\u001cB\u001d\u0011\u001d\u0011I%\fC\u0001\u0005\u0017\n!\"[7q_J$h*Y7f)\u0011\u0011iEa\u0016\u0011\u0007m\u0011y%\u0003\u0003\u0003R\tM#\u0001\u0002(b[\u0016L1A!\u0016\u0003\u0005\u0015q\u0015-\\3t\u0011!\u0011IFa\u0012A\u0002\tm\u0013\u0001\u00028b[\u0016\u00042!\u001cB(\u0011\u001d\u0011y&\fC\u0001\u0005C\nq\"[7q_J$Xj\u001c3jM&,'o\u001d\u000b\u0005\u0005G\u0012I\u0007E\u0002\u001c\u0005KJAAa\u001a\u0002@\nIQj\u001c3jM&,'o\u001d\u0005\t\u0005W\u0012i\u00061\u0001\u0003n\u0005!Qn\u001c3t!\ri'Q\r\u0005\b\u0005cjC\u0011\u0001B:\u0003QIW\u000e]8si&k\u0007o\u001c:u'\u0016dWm\u0019;peR!!Q\u000fB>!\rY\"qO\u0005\u0005\u0005s\nyL\u0001\bJ[B|'\u000f^*fY\u0016\u001cGo\u001c:\t\u0011\tu$q\u000ea\u0001\u0005\u007f\n1a]3m!\ri'q\u000f\u0005\b\u0005\u0007kC\u0011\u0001BC\u00031IW\u000e]8siZ\u000bG\u000eR3g)\u0011\u00119I!$\u0011\u0007m\u0011I)\u0003\u0003\u0003\f\u0006}&A\u0002,bY\u0012+g\r\u0003\u0005\u0003\u0010\n\u0005\u0005\u0019\u0001BI\u0003\u0011!(/Z3\u0011\u00075\u0014I\tC\u0004\u0003\u00166\"\tAa&\u0002\u001b%l\u0007o\u001c:u)f\u0004X\rR3g)\u0011\u0011IJa(\u0011\u0007m\u0011Y*\u0003\u0003\u0003\u001e\u0006}&a\u0002+za\u0016$UM\u001a\u0005\t\u0005\u001f\u0013\u0019\n1\u0001\u0003\"B\u0019QNa'\t\u000f\t\u0015V\u0006\"\u0001\u0003(\u0006y\u0011.\u001c9peRlU-\u001c2fe\u0012+g\r\u0006\u0003\u0003*\n=\u0006cA\u000e\u0003,&!!QVA`\u0005%iU-\u001c2fe\u0012+g\r\u0003\u0005\u0003\u0010\n\r\u0006\u0019\u0001BY!\ri'1\u0016\u0005\b\u0005kkC\u0011\u0001B\\\u00039IW\u000e]8siR+W\u000e\u001d7bi\u0016$BA!/\u0003@B\u00191Da/\n\t\tu\u0016q\u0018\u0002\t)\u0016l\u0007\u000f\\1uK\"A!q\u0012BZ\u0001\u0004\u0011\t\rE\u0002n\u0005wCqA!2.\t\u0003\u00119-A\u0007j[B|'\u000f\u001e*fMR\u0013X-\u001a\u000b\u0005\u0005\u0013\u0014y\rE\u0002\u001c\u0005\u0017LAA!4\u0002@\n9!+\u001a4Ue\u0016,\u0007\u0002\u0003BH\u0005\u0007\u0004\rA!5\u0011\u00075\u0014Y\rC\u0004\u0003V6\"\tAa6\u0002\u0017%l\u0007o\u001c:u\u0013\u0012,g\u000e\u001e\u000b\u0005\u00053\u0014y\u000eE\u0002\u001c\u00057LAA!8\u0002@\n)\u0011\nZ3oi\"A!q\u0012Bj\u0001\u0004\u0011\t\u000fE\u0002n\u00057DqA!:.\t\u0003\u00119/A\u0007j[B|'\u000f^\"bg\u0016$UM\u001a\u000b\u0005\u0005S\u0014y\u000fE\u0002\u001c\u0005WLAA!<\u0002@\n91)Y:f\t\u00164\u0007\u0002\u0003BH\u0005G\u0004\rA!=\u0011\u00075\u0014Y\u000fC\u0004\u0003v6\"\tAa>\u0002\u001d%l\u0007o\u001c:u\u0007>t7\u000f^1oiR!!\u0011`B\u0002!\rY\"1`\u0005\u0005\u0005{\u0014yP\u0001\u0005D_:\u001cH/\u00198u\u0013\r\u0019\tA\u0001\u0002\n\u0007>t7\u000f^1oiND\u0001b!\u0002\u0003t\u0002\u00071qA\u0001\tG>t7\u000f^1oiB\u0019QNa?")
public interface Importers {
    public Internals.Importer mkImporter(Universe var1);

    public static abstract class StandardImporter
    implements Internals.Importer {
        private Cache<Symbols.Symbol, Symbols.Symbol> symMap;
        private Cache<Types.Type, Types.Type> tpeMap;
        private int pendingSyms;
        private int pendingTpes;
        private MutableList<Function0<BoxedUnit>> fixups;
        private volatile Importers$StandardImporter$reverse$ reverse$module;
        public final /* synthetic */ SymbolTable $outer;
        private volatile byte bitmap$0;

        private Cache symMap$lzycompute() {
            StandardImporter standardImporter = this;
            synchronized (standardImporter) {
                if ((byte)(this.bitmap$0 & 1) == 0) {
                    this.symMap = new Cache();
                    this.bitmap$0 = (byte)(this.bitmap$0 | 1);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.symMap;
            }
        }

        private Cache tpeMap$lzycompute() {
            StandardImporter standardImporter = this;
            synchronized (standardImporter) {
                if ((byte)(this.bitmap$0 & 2) == 0) {
                    this.tpeMap = new Cache();
                    this.bitmap$0 = (byte)(this.bitmap$0 | 2);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.tpeMap;
            }
        }

        private MutableList fixups$lzycompute() {
            StandardImporter standardImporter = this;
            synchronized (standardImporter) {
                if ((byte)(this.bitmap$0 & 4) == 0) {
                    this.fixups = (MutableList)MutableList$.MODULE$.apply(Nil$.MODULE$);
                    this.bitmap$0 = (byte)(this.bitmap$0 | 4);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.fixups;
            }
        }

        private Importers$StandardImporter$reverse$ reverse$lzycompute() {
            StandardImporter standardImporter = this;
            synchronized (standardImporter) {
                if (this.reverse$module == null) {
                    this.reverse$module = new Importers$StandardImporter$reverse$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.reverse$module;
            }
        }

        @Override
        public abstract SymbolTable from();

        public Cache<Symbols.Symbol, Symbols.Symbol> symMap() {
            return (byte)(this.bitmap$0 & 1) == 0 ? this.symMap$lzycompute() : this.symMap;
        }

        public Cache<Types.Type, Types.Type> tpeMap() {
            return (byte)(this.bitmap$0 & 2) == 0 ? this.tpeMap$lzycompute() : this.tpeMap;
        }

        public int pendingSyms() {
            return this.pendingSyms;
        }

        public void pendingSyms_$eq(int x$1) {
            this.pendingSyms = x$1;
        }

        public int pendingTpes() {
            return this.pendingTpes;
        }

        public void pendingTpes_$eq(int x$1) {
            this.pendingTpes = x$1;
        }

        public MutableList<Function0<BoxedUnit>> fixups() {
            return (byte)(this.bitmap$0 & 4) == 0 ? this.fixups$lzycompute() : this.fixups;
        }

        public void addFixup(Function0<BoxedUnit> fixup) {
            this.fixups().$plus$eq((Object)fixup);
        }

        public void tryFixup() {
            if (this.pendingSyms() == 0 && this.pendingTpes() == 0) {
                List fixups = this.fixups().toList();
                this.fixups().clear();
                List list2 = fixups;
                while (!((AbstractIterable)list2).isEmpty()) {
                    ((Function0)((AbstractIterable)list2).head()).apply$mcV$sp();
                    list2 = (List)list2.tail();
                }
            }
        }

        @Override
        public Importers$StandardImporter$reverse$ reverse() {
            return this.reverse$module == null ? this.reverse$lzycompute() : this.reverse$module;
        }

        public Types.LazyPolyType recreatedSymbolCompleter(Symbols.Symbol my, Symbols.Symbol their) {
            try {
                my.setFlag(0x8000000000L);
                List mytypeParams = their.typeParams().map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ StandardImporter $outer;

                    public final Symbols.Symbol apply(Symbols.Symbol their0) {
                        return this.$outer.importSymbol(their0);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, List$.MODULE$.canBuildFrom());
                return new Types.FlagAgnosticCompleter(this, their, mytypeParams){
                    private final /* synthetic */ StandardImporter $outer;
                    private final Symbols.Symbol their$1;
                    private final List mytypeParams$1;

                    public void complete(Symbols.Symbol my) {
                        Types.Type type;
                        Types.Type type2 = this.their$1.info();
                        if (type2 instanceof Types.PolyType) {
                            Types.PolyType polyType = (Types.PolyType)type2;
                            type = polyType.resultType();
                        } else {
                            type = type2;
                        }
                        my.setInfo(this.$outer.scala$reflect$internal$Importers$StandardImporter$$$outer().GenPolyType().apply(this.mytypeParams$1, this.$outer.importType(type)));
                        my.setAnnotations(this.their$1.annotations().map(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ StandardImporter$$anon$1 $outer;

                            public final AnnotationInfos.AnnotationInfo apply(AnnotationInfos.AnnotationInfo ann) {
                                return this.$outer.scala$reflect$internal$Importers$StandardImporter$$anon$$$outer().importAnnotationInfo(ann);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }, List$.MODULE$.canBuildFrom()));
                        this.$outer.scala$reflect$internal$Importers$StandardImporter$$$outer().markAllCompleted(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{my}));
                    }

                    public /* synthetic */ StandardImporter scala$reflect$internal$Importers$StandardImporter$$anon$$$outer() {
                        return this.$outer;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.their$1 = their$1;
                        this.mytypeParams$1 = mytypeParams$1;
                        super($outer.scala$reflect$internal$Importers$StandardImporter$$$outer(), mytypeParams$1);
                    }
                };
            }
            finally {
                my.resetFlag(0x8000000000L);
            }
        }

        public Symbols.Symbol recreateSymbol(Symbols.Symbol their) {
            block18: {
                Symbols.Symbol symbol;
                block6: {
                    long myflags;
                    Names.Name myname;
                    Position mypos;
                    Symbols.Symbol myowner;
                    block17: {
                        Object object;
                        block16: {
                            block11: {
                                Object object2;
                                block15: {
                                    StdAttachments.Attachable attachable;
                                    block13: {
                                        block14: {
                                            block12: {
                                                block10: {
                                                    block9: {
                                                        block8: {
                                                            block7: {
                                                                block5: {
                                                                    myowner = this.importSymbol(their.owner());
                                                                    mypos = this.importPosition(their.pos());
                                                                    myname = this.importName(their.name());
                                                                    myflags = their.flags();
                                                                    if (!(their instanceof Symbols.MethodSymbol)) break block5;
                                                                    Symbols.MethodSymbol methodSymbol = (Symbols.MethodSymbol)their;
                                                                    symbol = this.linkReferenced$1(myowner.newMethod(myname.toTermName(), mypos, myflags), methodSymbol, (Function1)((Object)new Serializable(this){
                                                                        public static final long serialVersionUID = 0L;
                                                                        private final /* synthetic */ StandardImporter $outer;

                                                                        public final Symbols.Symbol apply(Symbols.Symbol their0) {
                                                                            return this.$outer.importSymbol(their0);
                                                                        }
                                                                        {
                                                                            if ($outer == null) {
                                                                                throw null;
                                                                            }
                                                                            this.$outer = $outer;
                                                                        }
                                                                    }));
                                                                    break block6;
                                                                }
                                                                if (!(their instanceof Symbols.ModuleSymbol)) break block7;
                                                                Symbols.ModuleSymbol moduleSymbol = (Symbols.ModuleSymbol)their;
                                                                Symbols.Symbol ret = this.linkReferenced$1(myowner.newModuleSymbol(myname.toTermName(), mypos, myflags), moduleSymbol, (Function1)((Object)new Serializable(this){
                                                                    public static final long serialVersionUID = 0L;
                                                                    private final /* synthetic */ StandardImporter $outer;

                                                                    public final Symbols.Symbol apply(Symbols.Symbol their0) {
                                                                        return this.$outer.importSymbol(their0);
                                                                    }
                                                                    {
                                                                        if ($outer == null) {
                                                                            throw null;
                                                                        }
                                                                        this.$outer = $outer;
                                                                    }
                                                                }));
                                                                ret.associatedFile_$eq(moduleSymbol.associatedFile());
                                                                symbol = ret;
                                                                break block6;
                                                            }
                                                            if (!(their instanceof Symbols.FreeTermSymbol)) break block8;
                                                            Symbols.FreeTermSymbol freeTermSymbol = (Symbols.FreeTermSymbol)their;
                                                            symbol = this.scala$reflect$internal$Importers$StandardImporter$$$outer().newFreeTermSymbol(myname.toTermName(), (Function0<Object>)((Object)new Serializable(this, freeTermSymbol){
                                                                public static final long serialVersionUID = 0L;
                                                                private final Symbols.FreeTermSymbol x4$1;

                                                                public final Object apply() {
                                                                    return this.x4$1.value();
                                                                }
                                                                {
                                                                    this.x4$1 = x4$1;
                                                                }
                                                            }), freeTermSymbol.flags(), freeTermSymbol.origin()).setInfo(this.importType(freeTermSymbol.info()));
                                                            break block6;
                                                        }
                                                        if (!(their instanceof Symbols.FreeTypeSymbol)) break block9;
                                                        Symbols.FreeTypeSymbol freeTypeSymbol = (Symbols.FreeTypeSymbol)their;
                                                        symbol = this.scala$reflect$internal$Importers$StandardImporter$$$outer().newFreeTypeSymbol(myname.toTypeName(), freeTypeSymbol.flags(), freeTypeSymbol.origin());
                                                        break block6;
                                                    }
                                                    if (!(their instanceof Symbols.TermSymbol)) break block10;
                                                    Symbols.TermSymbol termSymbol = (Symbols.TermSymbol)their;
                                                    symbol = this.linkReferenced$1(myowner.newValue(myname.toTermName(), mypos, myflags), termSymbol, (Function1)((Object)new Serializable(this){
                                                        public static final long serialVersionUID = 0L;
                                                        private final /* synthetic */ StandardImporter $outer;

                                                        public final Symbols.Symbol apply(Symbols.Symbol their0) {
                                                            return this.$outer.importSymbol(their0);
                                                        }
                                                        {
                                                            if ($outer == null) {
                                                                throw null;
                                                            }
                                                            this.$outer = $outer;
                                                        }
                                                    }));
                                                    break block6;
                                                }
                                                if (!(their instanceof Symbols.TypeSkolem)) break block11;
                                                Symbols.TypeSkolem typeSkolem = (Symbols.TypeSkolem)their;
                                                object2 = typeSkolem.unpackLocation();
                                                if (object2 != null) break block12;
                                                attachable = null;
                                                break block13;
                                            }
                                            if (!(object2 instanceof Trees.Tree) || ((Trees.Tree)object2).scala$reflect$internal$Trees$Tree$$$outer() != this.from()) break block14;
                                            Trees.Tree tree = (Trees.Tree)object2;
                                            attachable = this.importTree(tree);
                                            break block13;
                                        }
                                        if (!(object2 instanceof Symbols.Symbol) || ((Symbols.Symbol)object2).scala$reflect$internal$Symbols$Symbol$$$outer() != this.from()) break block15;
                                        Symbols.Symbol symbol2 = (Symbols.Symbol)object2;
                                        attachable = this.importSymbol(symbol2);
                                    }
                                    symbol = myowner.newTypeSkolemSymbol(myname.toTypeName(), attachable, mypos, myflags);
                                    break block6;
                                }
                                throw new MatchError(object2);
                            }
                            if (!(their instanceof Symbols.ModuleClassSymbol)) break block16;
                            Symbols.ModuleClassSymbol moduleClassSymbol = (Symbols.ModuleClassSymbol)their;
                            Symbols.ModuleClassSymbol my = myowner.newModuleClass(myname.toTypeName(), mypos, myflags);
                            this.symMap().weakUpdate(moduleClassSymbol, my);
                            my.sourceModule_$eq(this.importSymbol(moduleClassSymbol.sourceModule()));
                            symbol = my;
                            break block6;
                        }
                        if (!(their instanceof Symbols.ClassSymbol)) break block17;
                        Symbols.ClassSymbol classSymbol = (Symbols.ClassSymbol)their;
                        Symbols.ClassSymbol my = myowner.newClassSymbol(myname.toTypeName(), mypos, myflags);
                        this.symMap().weakUpdate(classSymbol, my);
                        Symbols.Symbol symbol3 = classSymbol.thisSym();
                        if (!(symbol3 != null ? !symbol3.equals(classSymbol) : classSymbol != null)) {
                            object = BoxedUnit.UNIT;
                        } else {
                            my.typeOfThis_$eq(this.importType(classSymbol.typeOfThis()));
                            object = my.thisSym().setName(this.importName(classSymbol.thisSym().name()));
                        }
                        my.associatedFile_$eq(classSymbol.associatedFile());
                        symbol = my;
                        break block6;
                    }
                    if (!(their instanceof Symbols.TypeSymbol)) break block18;
                    symbol = myowner.newTypeSymbol(myname.toTypeName(), mypos, myflags);
                }
                this.symMap().weakUpdate(their, symbol);
                this.scala$reflect$internal$Importers$StandardImporter$$$outer().markFlagsCompleted(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{symbol}), -1L);
                return symbol.setInfo(this.recreatedSymbolCompleter(symbol, their));
            }
            throw new MatchError(their);
        }

        /*
         * WARNING - void declaration
         */
        public Symbols.Symbol importSymbol(Symbols.Symbol their0) {
            Option<Symbols.Symbol> option;
            block7: {
                Symbols.Symbol symbol;
                block6: {
                    void var3_5;
                    block5: {
                        option = this.symMap().weakGet(their0);
                        if (!(option instanceof Some)) break block5;
                        Some some = (Some)option;
                        symbol = (Symbols.Symbol)some.x();
                        break block6;
                    }
                    if (!None$.MODULE$.equals(option)) break block7;
                    this.pendingSyms_$eq(this.pendingSyms() + 1);
                    Symbols.Symbol result2 = this.recreateOrRelink$1(their0);
                    this.symMap().weakUpdate(their0, result2);
                    symbol = var3_5;
                }
                return symbol;
            }
            throw new MatchError(option);
            finally {
                this.pendingSyms_$eq(this.pendingSyms() - 1);
                this.tryFixup();
            }
        }

        public Types.Type recreateType(Types.Type their) {
            block25: {
                Types.Type type;
                block3: {
                    block24: {
                        block23: {
                            block22: {
                                block21: {
                                    block20: {
                                        block19: {
                                            block18: {
                                                block17: {
                                                    block16: {
                                                        block15: {
                                                            block14: {
                                                                block13: {
                                                                    block12: {
                                                                        block11: {
                                                                            block10: {
                                                                                block9: {
                                                                                    Types.ConstantType constantType;
                                                                                    block8: {
                                                                                        block7: {
                                                                                            block6: {
                                                                                                block5: {
                                                                                                    block4: {
                                                                                                        block2: {
                                                                                                            if (!(their instanceof Types.TypeRef)) break block2;
                                                                                                            Types.TypeRef typeRef = (Types.TypeRef)their;
                                                                                                            type = this.scala$reflect$internal$Importers$StandardImporter$$$outer().TypeRef().apply(this.importType(typeRef.pre()), this.importSymbol(typeRef.sym()), typeRef.args().map(new Serializable(this){
                                                                                                                public static final long serialVersionUID = 0L;
                                                                                                                private final /* synthetic */ StandardImporter $outer;

                                                                                                                public final Types.Type apply(Types.Type their) {
                                                                                                                    return this.$outer.importType(their);
                                                                                                                }
                                                                                                                {
                                                                                                                    if ($outer == null) {
                                                                                                                        throw null;
                                                                                                                    }
                                                                                                                    this.$outer = $outer;
                                                                                                                }
                                                                                                            }, List$.MODULE$.canBuildFrom()));
                                                                                                            break block3;
                                                                                                        }
                                                                                                        if (!(their instanceof Types.ThisType)) break block4;
                                                                                                        Types.ThisType thisType = (Types.ThisType)their;
                                                                                                        type = this.scala$reflect$internal$Importers$StandardImporter$$$outer().ThisType().apply(this.importSymbol(thisType.sym()));
                                                                                                        break block3;
                                                                                                    }
                                                                                                    if (!(their instanceof Types.SingleType)) break block5;
                                                                                                    Types.SingleType singleType = (Types.SingleType)their;
                                                                                                    type = this.scala$reflect$internal$Importers$StandardImporter$$$outer().SingleType().apply(this.importType(singleType.pre()), this.importSymbol(singleType.sym()));
                                                                                                    break block3;
                                                                                                }
                                                                                                if (!(their instanceof Types.MethodType)) break block6;
                                                                                                Types.MethodType methodType = (Types.MethodType)their;
                                                                                                type = new Types.MethodType(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), methodType.params().map(new Serializable(this){
                                                                                                    public static final long serialVersionUID = 0L;
                                                                                                    private final /* synthetic */ StandardImporter $outer;

                                                                                                    public final Symbols.Symbol apply(Symbols.Symbol their0) {
                                                                                                        return this.$outer.importSymbol(their0);
                                                                                                    }
                                                                                                    {
                                                                                                        if ($outer == null) {
                                                                                                            throw null;
                                                                                                        }
                                                                                                        this.$outer = $outer;
                                                                                                    }
                                                                                                }, List$.MODULE$.canBuildFrom()), this.importType(methodType.resultType()));
                                                                                                break block3;
                                                                                            }
                                                                                            if (!(their instanceof Types.PolyType)) break block7;
                                                                                            Types.PolyType polyType = (Types.PolyType)their;
                                                                                            type = new Types.PolyType(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), polyType.typeParams().map(new Serializable(this){
                                                                                                public static final long serialVersionUID = 0L;
                                                                                                private final /* synthetic */ StandardImporter $outer;

                                                                                                public final Symbols.Symbol apply(Symbols.Symbol their0) {
                                                                                                    return this.$outer.importSymbol(their0);
                                                                                                }
                                                                                                {
                                                                                                    if ($outer == null) {
                                                                                                        throw null;
                                                                                                    }
                                                                                                    this.$outer = $outer;
                                                                                                }
                                                                                            }, List$.MODULE$.canBuildFrom()), this.importType(polyType.resultType()));
                                                                                            break block3;
                                                                                        }
                                                                                        if (!(their instanceof Types.NullaryMethodType)) break block8;
                                                                                        Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)their;
                                                                                        type = new Types.NullaryMethodType(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importType(nullaryMethodType.resultType()));
                                                                                        break block3;
                                                                                    }
                                                                                    if (!(their instanceof Types.ConstantType) || (constantType = (Types.ConstantType)their).value() == null) break block9;
                                                                                    type = this.scala$reflect$internal$Importers$StandardImporter$$$outer().ConstantType().apply(this.importConstant(constantType.value()));
                                                                                    break block3;
                                                                                }
                                                                                if (!(their instanceof Types.SuperType)) break block10;
                                                                                Types.SuperType superType = (Types.SuperType)their;
                                                                                type = this.scala$reflect$internal$Importers$StandardImporter$$$outer().SuperType().apply(this.importType(superType.thistpe()), this.importType(superType.supertpe()));
                                                                                break block3;
                                                                            }
                                                                            if (!(their instanceof Types.TypeBounds)) break block11;
                                                                            Types.TypeBounds typeBounds = (Types.TypeBounds)their;
                                                                            type = this.scala$reflect$internal$Importers$StandardImporter$$$outer().TypeBounds().apply(this.importType(typeBounds.lo()), this.importType(typeBounds.hi()));
                                                                            break block3;
                                                                        }
                                                                        if (!(their instanceof Types.BoundedWildcardType)) break block12;
                                                                        Types.BoundedWildcardType boundedWildcardType = (Types.BoundedWildcardType)their;
                                                                        type = new Types.BoundedWildcardType(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), (Types.TypeBounds)this.importType(boundedWildcardType.bounds()));
                                                                        break block3;
                                                                    }
                                                                    if (!(their instanceof Types.ClassInfoType)) break block13;
                                                                    Types.ClassInfoType classInfoType = (Types.ClassInfoType)their;
                                                                    Symbols.Symbol myclazz = this.importSymbol(classInfoType.typeSymbol());
                                                                    Scopes.Scope myscope = myclazz.isPackageClass() ? this.scala$reflect$internal$Importers$StandardImporter$$$outer().newPackageScope(myclazz) : this.scala$reflect$internal$Importers$StandardImporter$$$outer().newScope();
                                                                    Types.ClassInfoType myclazzTpe = new Types.ClassInfoType(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), classInfoType.parents().map(new Serializable(this){
                                                                        public static final long serialVersionUID = 0L;
                                                                        private final /* synthetic */ StandardImporter $outer;

                                                                        public final Types.Type apply(Types.Type their) {
                                                                            return this.$outer.importType(their);
                                                                        }
                                                                        {
                                                                            if ($outer == null) {
                                                                                throw null;
                                                                            }
                                                                            this.$outer = $outer;
                                                                        }
                                                                    }, List$.MODULE$.canBuildFrom()), myscope, myclazz);
                                                                    myclazz.setInfo(this.scala$reflect$internal$Importers$StandardImporter$$$outer().GenPolyType().apply(myclazz.typeParams(), myclazzTpe));
                                                                    classInfoType.decls().foreach(new Serializable(this){
                                                                        public static final long serialVersionUID = 0L;
                                                                        private final /* synthetic */ StandardImporter $outer;

                                                                        public final Symbols.Symbol apply(Symbols.Symbol their0) {
                                                                            return this.$outer.importSymbol(their0);
                                                                        }
                                                                        {
                                                                            if ($outer == null) {
                                                                                throw null;
                                                                            }
                                                                            this.$outer = $outer;
                                                                        }
                                                                    });
                                                                    type = myclazzTpe;
                                                                    break block3;
                                                                }
                                                                if (!(their instanceof Types.RefinedType)) break block14;
                                                                Types.RefinedType refinedType = (Types.RefinedType)their;
                                                                type = this.scala$reflect$internal$Importers$StandardImporter$$$outer().RefinedType().apply(refinedType.parents().map(new Serializable(this){
                                                                    public static final long serialVersionUID = 0L;
                                                                    private final /* synthetic */ StandardImporter $outer;

                                                                    public final Types.Type apply(Types.Type their) {
                                                                        return this.$outer.importType(their);
                                                                    }
                                                                    {
                                                                        if ($outer == null) {
                                                                            throw null;
                                                                        }
                                                                        this.$outer = $outer;
                                                                    }
                                                                }, List$.MODULE$.canBuildFrom()), this.importScope(refinedType.decls()), this.importSymbol(their.typeSymbol()));
                                                                break block3;
                                                            }
                                                            if (!(their instanceof Types.ExistentialType)) break block15;
                                                            Types.ExistentialType existentialType = (Types.ExistentialType)their;
                                                            type = this.scala$reflect$internal$Importers$StandardImporter$$$outer().newExistentialType(existentialType.quantified().map(new Serializable(this){
                                                                public static final long serialVersionUID = 0L;
                                                                private final /* synthetic */ StandardImporter $outer;

                                                                public final Symbols.Symbol apply(Symbols.Symbol their0) {
                                                                    return this.$outer.importSymbol(their0);
                                                                }
                                                                {
                                                                    if ($outer == null) {
                                                                        throw null;
                                                                    }
                                                                    this.$outer = $outer;
                                                                }
                                                            }, List$.MODULE$.canBuildFrom()), this.importType(existentialType.underlying()));
                                                            break block3;
                                                        }
                                                        if (!(their instanceof Types.OverloadedType)) break block16;
                                                        Types.OverloadedType overloadedType = (Types.OverloadedType)their;
                                                        type = new Types.OverloadedType(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importType(overloadedType.pre()), overloadedType.alternatives().map(new Serializable(this){
                                                            public static final long serialVersionUID = 0L;
                                                            private final /* synthetic */ StandardImporter $outer;

                                                            public final Symbols.Symbol apply(Symbols.Symbol their0) {
                                                                return this.$outer.importSymbol(their0);
                                                            }
                                                            {
                                                                if ($outer == null) {
                                                                    throw null;
                                                                }
                                                                this.$outer = $outer;
                                                            }
                                                        }, List$.MODULE$.canBuildFrom()));
                                                        break block3;
                                                    }
                                                    if (!(their instanceof Types.ImportType)) break block17;
                                                    Types.ImportType importType = (Types.ImportType)their;
                                                    type = new Types.ImportType(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(importType.expr()));
                                                    break block3;
                                                }
                                                if (!(their instanceof Types.AntiPolyType)) break block18;
                                                Types.AntiPolyType antiPolyType = (Types.AntiPolyType)their;
                                                type = new Types.AntiPolyType(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importType(antiPolyType.pre()), antiPolyType.targs().map(new Serializable(this){
                                                    public static final long serialVersionUID = 0L;
                                                    private final /* synthetic */ StandardImporter $outer;

                                                    public final Types.Type apply(Types.Type their) {
                                                        return this.$outer.importType(their);
                                                    }
                                                    {
                                                        if ($outer == null) {
                                                            throw null;
                                                        }
                                                        this.$outer = $outer;
                                                    }
                                                }, List$.MODULE$.canBuildFrom()));
                                                break block3;
                                            }
                                            if (!(their instanceof Types.TypeVar)) break block19;
                                            Types.TypeVar typeVar = (Types.TypeVar)their;
                                            TypeConstraints.TypeConstraint myconstr = new TypeConstraints.TypeConstraint(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), typeVar.constr().loBounds().map(new Serializable(this){
                                                public static final long serialVersionUID = 0L;
                                                private final /* synthetic */ StandardImporter $outer;

                                                public final Types.Type apply(Types.Type their) {
                                                    return this.$outer.importType(their);
                                                }
                                                {
                                                    if ($outer == null) {
                                                        throw null;
                                                    }
                                                    this.$outer = $outer;
                                                }
                                            }, List$.MODULE$.canBuildFrom()), typeVar.constr().hiBounds().map(new Serializable(this){
                                                public static final long serialVersionUID = 0L;
                                                private final /* synthetic */ StandardImporter $outer;

                                                public final Types.Type apply(Types.Type their) {
                                                    return this.$outer.importType(their);
                                                }
                                                {
                                                    if ($outer == null) {
                                                        throw null;
                                                    }
                                                    this.$outer = $outer;
                                                }
                                            }, List$.MODULE$.canBuildFrom()));
                                            myconstr.inst_$eq(this.importType(typeVar.constr().inst()));
                                            type = this.scala$reflect$internal$Importers$StandardImporter$$$outer().TypeVar().apply(this.importType(typeVar.origin()), myconstr, typeVar.typeArgs().map(new Serializable(this){
                                                public static final long serialVersionUID = 0L;
                                                private final /* synthetic */ StandardImporter $outer;

                                                public final Types.Type apply(Types.Type their) {
                                                    return this.$outer.importType(their);
                                                }
                                                {
                                                    if ($outer == null) {
                                                        throw null;
                                                    }
                                                    this.$outer = $outer;
                                                }
                                            }, List$.MODULE$.canBuildFrom()), typeVar.params().map(new Serializable(this){
                                                public static final long serialVersionUID = 0L;
                                                private final /* synthetic */ StandardImporter $outer;

                                                public final Symbols.Symbol apply(Symbols.Symbol their0) {
                                                    return this.$outer.importSymbol(their0);
                                                }
                                                {
                                                    if ($outer == null) {
                                                        throw null;
                                                    }
                                                    this.$outer = $outer;
                                                }
                                            }, List$.MODULE$.canBuildFrom()));
                                            break block3;
                                        }
                                        if (!(their instanceof Types.AnnotatedType)) break block20;
                                        Types.AnnotatedType annotatedType = (Types.AnnotatedType)their;
                                        type = new Types.AnnotatedType(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), annotatedType.annotations().map(new Serializable(this){
                                            public static final long serialVersionUID = 0L;
                                            private final /* synthetic */ StandardImporter $outer;

                                            public final AnnotationInfos.AnnotationInfo apply(AnnotationInfos.AnnotationInfo ann) {
                                                return this.$outer.importAnnotationInfo(ann);
                                            }
                                            {
                                                if ($outer == null) {
                                                    throw null;
                                                }
                                                this.$outer = $outer;
                                            }
                                        }, List$.MODULE$.canBuildFrom()), this.importType(annotatedType.underlying()));
                                        break block3;
                                    }
                                    if (!this.from().ErrorType().equals(their)) break block21;
                                    type = this.scala$reflect$internal$Importers$StandardImporter$$$outer().ErrorType();
                                    break block3;
                                }
                                if (!this.from().WildcardType().equals(their)) break block22;
                                type = this.scala$reflect$internal$Importers$StandardImporter$$$outer().WildcardType();
                                break block3;
                            }
                            if (!this.from().NoType().equals(their)) break block23;
                            type = this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoType();
                            break block3;
                        }
                        if (!this.from().NoPrefix().equals(their)) break block24;
                        type = this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoPrefix();
                        break block3;
                    }
                    if (their != null) break block25;
                    type = null;
                }
                return type;
            }
            throw new MatchError(their);
        }

        public Types.Type importType(Types.Type their) {
            Option<Types.Type> option;
            block7: {
                Types.Type type;
                block6: {
                    block5: {
                        option = this.tpeMap().weakGet(their);
                        if (!(option instanceof Some)) break block5;
                        Some some = (Some)option;
                        type = (Types.Type)some.x();
                        break block6;
                    }
                    if (!None$.MODULE$.equals(option)) break block7;
                    this.pendingTpes_$eq(this.pendingTpes() + 1);
                    Types.Type result2 = this.recreateType(their);
                    this.tpeMap().weakUpdate(their, result2);
                    type = result2;
                }
                return type;
            }
            throw new MatchError(option);
            finally {
                this.pendingTpes_$eq(this.pendingTpes() - 1);
                this.tryFixup();
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public void recreatedTreeCompleter(Trees.Tree their, Trees.Tree my) {
            if (!their.canHaveAttrs()) return;
            if (my.hasSymbolField()) {
                my.symbol_$eq(this.importSymbol(their.symbol()));
            }
            my.pos_$eq(this.importPosition(their.pos()));
            Tuple2<Trees.Tree, Trees.Tree> tuple2 = new Tuple2<Trees.Tree, Trees.Tree>(their, my);
            if (tuple2._1() instanceof Trees.TypeTree) {
                Trees.TypeTree typeTree = (Trees.TypeTree)tuple2._1();
                if (tuple2._2() instanceof Trees.TypeTree) {
                    Trees.TypeTree typeTree2 = (Trees.TypeTree)tuple2._2();
                    if (typeTree.wasEmpty()) {
                        typeTree2.defineType(this.importType(typeTree.tpe()));
                        return;
                    }
                    typeTree2.setType(this.importType(typeTree.tpe()));
                    return;
                }
            }
            my.setType(this.importType(their.tpe()));
        }

        public Trees.Tree recreateTree(Trees.Tree their) {
            block50: {
                Trees.Tree tree;
                block3: {
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
                                                            Trees.Literal literal;
                                                            block38: {
                                                                Trees.Tree tree2;
                                                                block39: {
                                                                    block37: {
                                                                        block36: {
                                                                            block35: {
                                                                                block34: {
                                                                                    block33: {
                                                                                        block32: {
                                                                                            block31: {
                                                                                                block30: {
                                                                                                    block29: {
                                                                                                        block28: {
                                                                                                            block27: {
                                                                                                                block26: {
                                                                                                                    block25: {
                                                                                                                        block24: {
                                                                                                                            block23: {
                                                                                                                                block22: {
                                                                                                                                    block21: {
                                                                                                                                        block20: {
                                                                                                                                            block19: {
                                                                                                                                                block18: {
                                                                                                                                                    block17: {
                                                                                                                                                        block16: {
                                                                                                                                                            block15: {
                                                                                                                                                                block14: {
                                                                                                                                                                    block13: {
                                                                                                                                                                        block12: {
                                                                                                                                                                            block11: {
                                                                                                                                                                                block10: {
                                                                                                                                                                                    block9: {
                                                                                                                                                                                        block8: {
                                                                                                                                                                                            block7: {
                                                                                                                                                                                                block6: {
                                                                                                                                                                                                    block5: {
                                                                                                                                                                                                        block4: {
                                                                                                                                                                                                            block2: {
                                                                                                                                                                                                                if (!(their instanceof Trees.ClassDef)) break block2;
                                                                                                                                                                                                                Trees.ClassDef classDef = (Trees.ClassDef)their;
                                                                                                                                                                                                                tree = new Trees.ClassDef(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importModifiers(classDef.mods()), this.importName(classDef.name()).toTypeName(), classDef.tparams().map(new Serializable(this){
                                                                                                                                                                                                                    public static final long serialVersionUID = 0L;
                                                                                                                                                                                                                    private final /* synthetic */ StandardImporter $outer;

                                                                                                                                                                                                                    public final Trees.TypeDef apply(Trees.TypeDef tree) {
                                                                                                                                                                                                                        return this.$outer.importTypeDef(tree);
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                    {
                                                                                                                                                                                                                        if ($outer == null) {
                                                                                                                                                                                                                            throw null;
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                        this.$outer = $outer;
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }, List$.MODULE$.canBuildFrom()), this.importTemplate(classDef.impl()));
                                                                                                                                                                                                                break block3;
                                                                                                                                                                                                            }
                                                                                                                                                                                                            if (!(their instanceof Trees.PackageDef)) break block4;
                                                                                                                                                                                                            Trees.PackageDef packageDef = (Trees.PackageDef)their;
                                                                                                                                                                                                            tree = new Trees.PackageDef(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importRefTree(packageDef.pid()), packageDef.stats().map(new Serializable(this){
                                                                                                                                                                                                                public static final long serialVersionUID = 0L;
                                                                                                                                                                                                                private final /* synthetic */ StandardImporter $outer;

                                                                                                                                                                                                                public final Trees.Tree apply(Trees.Tree their) {
                                                                                                                                                                                                                    return this.$outer.importTree(their);
                                                                                                                                                                                                                }
                                                                                                                                                                                                                {
                                                                                                                                                                                                                    if ($outer == null) {
                                                                                                                                                                                                                        throw null;
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                    this.$outer = $outer;
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }, List$.MODULE$.canBuildFrom()));
                                                                                                                                                                                                            break block3;
                                                                                                                                                                                                        }
                                                                                                                                                                                                        if (!(their instanceof Trees.ModuleDef)) break block5;
                                                                                                                                                                                                        Trees.ModuleDef moduleDef = (Trees.ModuleDef)their;
                                                                                                                                                                                                        tree = new Trees.ModuleDef(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importModifiers(moduleDef.mods()), this.importName(moduleDef.name()).toTermName(), this.importTemplate(moduleDef.impl()));
                                                                                                                                                                                                        break block3;
                                                                                                                                                                                                    }
                                                                                                                                                                                                    if (!((Object)this.from().noSelfType()).equals(their)) break block6;
                                                                                                                                                                                                    tree = this.scala$reflect$internal$Importers$StandardImporter$$$outer().noSelfType();
                                                                                                                                                                                                    break block3;
                                                                                                                                                                                                }
                                                                                                                                                                                                if (!((Object)this.from().pendingSuperCall()).equals(their)) break block7;
                                                                                                                                                                                                tree = this.scala$reflect$internal$Importers$StandardImporter$$$outer().pendingSuperCall();
                                                                                                                                                                                                break block3;
                                                                                                                                                                                            }
                                                                                                                                                                                            if (!(their instanceof Trees.ValDef)) break block8;
                                                                                                                                                                                            Trees.ValDef valDef = (Trees.ValDef)their;
                                                                                                                                                                                            tree = new Trees.ValDef(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importModifiers(valDef.mods()), this.importName(valDef.name()).toTermName(), this.importTree(valDef.tpt()), this.importTree(valDef.rhs()));
                                                                                                                                                                                            break block3;
                                                                                                                                                                                        }
                                                                                                                                                                                        if (!(their instanceof Trees.DefDef)) break block9;
                                                                                                                                                                                        Trees.DefDef defDef = (Trees.DefDef)their;
                                                                                                                                                                                        tree = new Trees.DefDef(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importModifiers(defDef.mods()), this.importName(defDef.name()).toTermName(), defDef.tparams().map(new Serializable(this){
                                                                                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                                                                                            private final /* synthetic */ StandardImporter $outer;

                                                                                                                                                                                            public final Trees.TypeDef apply(Trees.TypeDef tree) {
                                                                                                                                                                                                return this.$outer.importTypeDef(tree);
                                                                                                                                                                                            }
                                                                                                                                                                                            {
                                                                                                                                                                                                if ($outer == null) {
                                                                                                                                                                                                    throw null;
                                                                                                                                                                                                }
                                                                                                                                                                                                this.$outer = $outer;
                                                                                                                                                                                            }
                                                                                                                                                                                        }, List$.MODULE$.canBuildFrom()), this.scala$reflect$internal$Importers$StandardImporter$$$outer().mmap(defDef.vparamss(), new Serializable(this){
                                                                                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                                                                                            private final /* synthetic */ StandardImporter $outer;

                                                                                                                                                                                            public final Trees.ValDef apply(Trees.ValDef tree) {
                                                                                                                                                                                                return this.$outer.importValDef(tree);
                                                                                                                                                                                            }
                                                                                                                                                                                            {
                                                                                                                                                                                                if ($outer == null) {
                                                                                                                                                                                                    throw null;
                                                                                                                                                                                                }
                                                                                                                                                                                                this.$outer = $outer;
                                                                                                                                                                                            }
                                                                                                                                                                                        }), this.importTree(defDef.tpt()), this.importTree(defDef.rhs()));
                                                                                                                                                                                        break block3;
                                                                                                                                                                                    }
                                                                                                                                                                                    if (!(their instanceof Trees.TypeDef)) break block10;
                                                                                                                                                                                    Trees.TypeDef typeDef = (Trees.TypeDef)their;
                                                                                                                                                                                    tree = new Trees.TypeDef(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importModifiers(typeDef.mods()), this.importName(typeDef.name()).toTypeName(), typeDef.tparams().map(new Serializable(this){
                                                                                                                                                                                        public static final long serialVersionUID = 0L;
                                                                                                                                                                                        private final /* synthetic */ StandardImporter $outer;

                                                                                                                                                                                        public final Trees.TypeDef apply(Trees.TypeDef tree) {
                                                                                                                                                                                            return this.$outer.importTypeDef(tree);
                                                                                                                                                                                        }
                                                                                                                                                                                        {
                                                                                                                                                                                            if ($outer == null) {
                                                                                                                                                                                                throw null;
                                                                                                                                                                                            }
                                                                                                                                                                                            this.$outer = $outer;
                                                                                                                                                                                        }
                                                                                                                                                                                    }, List$.MODULE$.canBuildFrom()), this.importTree(typeDef.rhs()));
                                                                                                                                                                                    break block3;
                                                                                                                                                                                }
                                                                                                                                                                                if (!(their instanceof Trees.LabelDef)) break block11;
                                                                                                                                                                                Trees.LabelDef labelDef = (Trees.LabelDef)their;
                                                                                                                                                                                tree = new Trees.LabelDef(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importName(labelDef.name()).toTermName(), labelDef.params().map(new Serializable(this){
                                                                                                                                                                                    public static final long serialVersionUID = 0L;
                                                                                                                                                                                    private final /* synthetic */ StandardImporter $outer;

                                                                                                                                                                                    public final Trees.Ident apply(Trees.Ident tree) {
                                                                                                                                                                                        return this.$outer.importIdent(tree);
                                                                                                                                                                                    }
                                                                                                                                                                                    {
                                                                                                                                                                                        if ($outer == null) {
                                                                                                                                                                                            throw null;
                                                                                                                                                                                        }
                                                                                                                                                                                        this.$outer = $outer;
                                                                                                                                                                                    }
                                                                                                                                                                                }, List$.MODULE$.canBuildFrom()), this.importTree(labelDef.rhs()));
                                                                                                                                                                                break block3;
                                                                                                                                                                            }
                                                                                                                                                                            if (!(their instanceof Trees.Import)) break block12;
                                                                                                                                                                            Trees.Import import_ = (Trees.Import)their;
                                                                                                                                                                            tree = new Trees.Import(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(import_.expr()), import_.selectors().map(new Serializable(this){
                                                                                                                                                                                public static final long serialVersionUID = 0L;
                                                                                                                                                                                private final /* synthetic */ StandardImporter $outer;

                                                                                                                                                                                public final Trees.ImportSelector apply(Trees.ImportSelector sel) {
                                                                                                                                                                                    return this.$outer.importImportSelector(sel);
                                                                                                                                                                                }
                                                                                                                                                                                {
                                                                                                                                                                                    if ($outer == null) {
                                                                                                                                                                                        throw null;
                                                                                                                                                                                    }
                                                                                                                                                                                    this.$outer = $outer;
                                                                                                                                                                                }
                                                                                                                                                                            }, List$.MODULE$.canBuildFrom()));
                                                                                                                                                                            break block3;
                                                                                                                                                                        }
                                                                                                                                                                        if (!(their instanceof Trees.Template)) break block13;
                                                                                                                                                                        Trees.Template template = (Trees.Template)their;
                                                                                                                                                                        tree = new Trees.Template(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), template.parents().map(new Serializable(this){
                                                                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                                                                            private final /* synthetic */ StandardImporter $outer;

                                                                                                                                                                            public final Trees.Tree apply(Trees.Tree their) {
                                                                                                                                                                                return this.$outer.importTree(their);
                                                                                                                                                                            }
                                                                                                                                                                            {
                                                                                                                                                                                if ($outer == null) {
                                                                                                                                                                                    throw null;
                                                                                                                                                                                }
                                                                                                                                                                                this.$outer = $outer;
                                                                                                                                                                            }
                                                                                                                                                                        }, List$.MODULE$.canBuildFrom()), this.importValDef(template.self()), template.body().map(new Serializable(this){
                                                                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                                                                            private final /* synthetic */ StandardImporter $outer;

                                                                                                                                                                            public final Trees.Tree apply(Trees.Tree their) {
                                                                                                                                                                                return this.$outer.importTree(their);
                                                                                                                                                                            }
                                                                                                                                                                            {
                                                                                                                                                                                if ($outer == null) {
                                                                                                                                                                                    throw null;
                                                                                                                                                                                }
                                                                                                                                                                                this.$outer = $outer;
                                                                                                                                                                            }
                                                                                                                                                                        }, List$.MODULE$.canBuildFrom()));
                                                                                                                                                                        break block3;
                                                                                                                                                                    }
                                                                                                                                                                    if (!(their instanceof Trees.Block)) break block14;
                                                                                                                                                                    Trees.Block block = (Trees.Block)their;
                                                                                                                                                                    tree = new Trees.Block(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), block.stats().map(new Serializable(this){
                                                                                                                                                                        public static final long serialVersionUID = 0L;
                                                                                                                                                                        private final /* synthetic */ StandardImporter $outer;

                                                                                                                                                                        public final Trees.Tree apply(Trees.Tree their) {
                                                                                                                                                                            return this.$outer.importTree(their);
                                                                                                                                                                        }
                                                                                                                                                                        {
                                                                                                                                                                            if ($outer == null) {
                                                                                                                                                                                throw null;
                                                                                                                                                                            }
                                                                                                                                                                            this.$outer = $outer;
                                                                                                                                                                        }
                                                                                                                                                                    }, List$.MODULE$.canBuildFrom()), this.importTree(block.expr()));
                                                                                                                                                                    break block3;
                                                                                                                                                                }
                                                                                                                                                                if (!(their instanceof Trees.CaseDef)) break block15;
                                                                                                                                                                Trees.CaseDef caseDef = (Trees.CaseDef)their;
                                                                                                                                                                tree = new Trees.CaseDef(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(caseDef.pat()), this.importTree(caseDef.guard()), this.importTree(caseDef.body()));
                                                                                                                                                                break block3;
                                                                                                                                                            }
                                                                                                                                                            if (!(their instanceof Trees.Alternative)) break block16;
                                                                                                                                                            Trees.Alternative alternative = (Trees.Alternative)their;
                                                                                                                                                            tree = new Trees.Alternative(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), alternative.trees().map(new Serializable(this){
                                                                                                                                                                public static final long serialVersionUID = 0L;
                                                                                                                                                                private final /* synthetic */ StandardImporter $outer;

                                                                                                                                                                public final Trees.Tree apply(Trees.Tree their) {
                                                                                                                                                                    return this.$outer.importTree(their);
                                                                                                                                                                }
                                                                                                                                                                {
                                                                                                                                                                    if ($outer == null) {
                                                                                                                                                                        throw null;
                                                                                                                                                                    }
                                                                                                                                                                    this.$outer = $outer;
                                                                                                                                                                }
                                                                                                                                                            }, List$.MODULE$.canBuildFrom()));
                                                                                                                                                            break block3;
                                                                                                                                                        }
                                                                                                                                                        if (!(their instanceof Trees.Star)) break block17;
                                                                                                                                                        Trees.Star star = (Trees.Star)their;
                                                                                                                                                        tree = new Trees.Star(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(star.elem()));
                                                                                                                                                        break block3;
                                                                                                                                                    }
                                                                                                                                                    if (!(their instanceof Trees.Bind)) break block18;
                                                                                                                                                    Trees.Bind bind = (Trees.Bind)their;
                                                                                                                                                    tree = new Trees.Bind(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importName(bind.name()), this.importTree(bind.body()));
                                                                                                                                                    break block3;
                                                                                                                                                }
                                                                                                                                                if (!(their instanceof Trees.UnApply)) break block19;
                                                                                                                                                Trees.UnApply unApply = (Trees.UnApply)their;
                                                                                                                                                tree = new Trees.UnApply(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(unApply.fun()), unApply.args().map(new Serializable(this){
                                                                                                                                                    public static final long serialVersionUID = 0L;
                                                                                                                                                    private final /* synthetic */ StandardImporter $outer;

                                                                                                                                                    public final Trees.Tree apply(Trees.Tree their) {
                                                                                                                                                        return this.$outer.importTree(their);
                                                                                                                                                    }
                                                                                                                                                    {
                                                                                                                                                        if ($outer == null) {
                                                                                                                                                            throw null;
                                                                                                                                                        }
                                                                                                                                                        this.$outer = $outer;
                                                                                                                                                    }
                                                                                                                                                }, List$.MODULE$.canBuildFrom()));
                                                                                                                                                break block3;
                                                                                                                                            }
                                                                                                                                            if (!(their instanceof Trees.ArrayValue)) break block20;
                                                                                                                                            Trees.ArrayValue arrayValue = (Trees.ArrayValue)their;
                                                                                                                                            tree = new Trees.ArrayValue(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(arrayValue.elemtpt()), arrayValue.elems().map(new Serializable(this){
                                                                                                                                                public static final long serialVersionUID = 0L;
                                                                                                                                                private final /* synthetic */ StandardImporter $outer;

                                                                                                                                                public final Trees.Tree apply(Trees.Tree their) {
                                                                                                                                                    return this.$outer.importTree(their);
                                                                                                                                                }
                                                                                                                                                {
                                                                                                                                                    if ($outer == null) {
                                                                                                                                                        throw null;
                                                                                                                                                    }
                                                                                                                                                    this.$outer = $outer;
                                                                                                                                                }
                                                                                                                                            }, List$.MODULE$.canBuildFrom()));
                                                                                                                                            break block3;
                                                                                                                                        }
                                                                                                                                        if (!(their instanceof Trees.Function)) break block21;
                                                                                                                                        Trees.Function function = (Trees.Function)their;
                                                                                                                                        tree = new Trees.Function(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), function.vparams().map(new Serializable(this){
                                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                                            private final /* synthetic */ StandardImporter $outer;

                                                                                                                                            public final Trees.ValDef apply(Trees.ValDef tree) {
                                                                                                                                                return this.$outer.importValDef(tree);
                                                                                                                                            }
                                                                                                                                            {
                                                                                                                                                if ($outer == null) {
                                                                                                                                                    throw null;
                                                                                                                                                }
                                                                                                                                                this.$outer = $outer;
                                                                                                                                            }
                                                                                                                                        }, List$.MODULE$.canBuildFrom()), this.importTree(function.body()));
                                                                                                                                        break block3;
                                                                                                                                    }
                                                                                                                                    if (!(their instanceof Trees.Assign)) break block22;
                                                                                                                                    Trees.Assign assign = (Trees.Assign)their;
                                                                                                                                    tree = new Trees.Assign(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(assign.lhs()), this.importTree(assign.rhs()));
                                                                                                                                    break block3;
                                                                                                                                }
                                                                                                                                if (!(their instanceof Trees.AssignOrNamedArg)) break block23;
                                                                                                                                Trees.AssignOrNamedArg assignOrNamedArg = (Trees.AssignOrNamedArg)their;
                                                                                                                                tree = new Trees.AssignOrNamedArg(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(assignOrNamedArg.lhs()), this.importTree(assignOrNamedArg.rhs()));
                                                                                                                                break block3;
                                                                                                                            }
                                                                                                                            if (!(their instanceof Trees.If)) break block24;
                                                                                                                            Trees.If if_ = (Trees.If)their;
                                                                                                                            tree = new Trees.If(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(if_.cond()), this.importTree(if_.thenp()), this.importTree(if_.elsep()));
                                                                                                                            break block3;
                                                                                                                        }
                                                                                                                        if (!(their instanceof Trees.Match)) break block25;
                                                                                                                        Trees.Match match = (Trees.Match)their;
                                                                                                                        tree = new Trees.Match(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(match.selector()), match.cases().map(new Serializable(this){
                                                                                                                            public static final long serialVersionUID = 0L;
                                                                                                                            private final /* synthetic */ StandardImporter $outer;

                                                                                                                            public final Trees.CaseDef apply(Trees.CaseDef tree) {
                                                                                                                                return this.$outer.importCaseDef(tree);
                                                                                                                            }
                                                                                                                            {
                                                                                                                                if ($outer == null) {
                                                                                                                                    throw null;
                                                                                                                                }
                                                                                                                                this.$outer = $outer;
                                                                                                                            }
                                                                                                                        }, List$.MODULE$.canBuildFrom()));
                                                                                                                        break block3;
                                                                                                                    }
                                                                                                                    if (!(their instanceof Trees.Return)) break block26;
                                                                                                                    Trees.Return return_ = (Trees.Return)their;
                                                                                                                    tree = new Trees.Return(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(return_.expr()));
                                                                                                                    break block3;
                                                                                                                }
                                                                                                                if (!(their instanceof Trees.Try)) break block27;
                                                                                                                Trees.Try try_ = (Trees.Try)their;
                                                                                                                tree = new Trees.Try(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(try_.block()), try_.catches().map(new Serializable(this){
                                                                                                                    public static final long serialVersionUID = 0L;
                                                                                                                    private final /* synthetic */ StandardImporter $outer;

                                                                                                                    public final Trees.CaseDef apply(Trees.CaseDef tree) {
                                                                                                                        return this.$outer.importCaseDef(tree);
                                                                                                                    }
                                                                                                                    {
                                                                                                                        if ($outer == null) {
                                                                                                                            throw null;
                                                                                                                        }
                                                                                                                        this.$outer = $outer;
                                                                                                                    }
                                                                                                                }, List$.MODULE$.canBuildFrom()), this.importTree(try_.finalizer()));
                                                                                                                break block3;
                                                                                                            }
                                                                                                            if (!(their instanceof Trees.Throw)) break block28;
                                                                                                            Trees.Throw throw_ = (Trees.Throw)their;
                                                                                                            tree = new Trees.Throw(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(throw_.expr()));
                                                                                                            break block3;
                                                                                                        }
                                                                                                        if (!(their instanceof Trees.New)) break block29;
                                                                                                        Trees.New new_ = (Trees.New)their;
                                                                                                        tree = new Trees.New(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(new_.tpt()));
                                                                                                        break block3;
                                                                                                    }
                                                                                                    if (!(their instanceof Trees.Typed)) break block30;
                                                                                                    Trees.Typed typed = (Trees.Typed)their;
                                                                                                    tree = new Trees.Typed(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(typed.expr()), this.importTree(typed.tpt()));
                                                                                                    break block3;
                                                                                                }
                                                                                                if (!(their instanceof Trees.TypeApply)) break block31;
                                                                                                Trees.TypeApply typeApply = (Trees.TypeApply)their;
                                                                                                tree = new Trees.TypeApply(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(typeApply.fun()), typeApply.args().map(new Serializable(this){
                                                                                                    public static final long serialVersionUID = 0L;
                                                                                                    private final /* synthetic */ StandardImporter $outer;

                                                                                                    public final Trees.Tree apply(Trees.Tree their) {
                                                                                                        return this.$outer.importTree(their);
                                                                                                    }
                                                                                                    {
                                                                                                        if ($outer == null) {
                                                                                                            throw null;
                                                                                                        }
                                                                                                        this.$outer = $outer;
                                                                                                    }
                                                                                                }, List$.MODULE$.canBuildFrom()));
                                                                                                break block3;
                                                                                            }
                                                                                            if (!(their instanceof Trees.Apply)) break block32;
                                                                                            Trees.Apply apply2 = (Trees.Apply)their;
                                                                                            Trees.Apply apply3 = their instanceof Trees.ApplyToImplicitArgs ? new Trees.ApplyToImplicitArgs(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(apply2.fun()), apply2.args().map(new Serializable(this){
                                                                                                public static final long serialVersionUID = 0L;
                                                                                                private final /* synthetic */ StandardImporter $outer;

                                                                                                public final Trees.Tree apply(Trees.Tree their) {
                                                                                                    return this.$outer.importTree(their);
                                                                                                }
                                                                                                {
                                                                                                    if ($outer == null) {
                                                                                                        throw null;
                                                                                                    }
                                                                                                    this.$outer = $outer;
                                                                                                }
                                                                                            }, List$.MODULE$.canBuildFrom())) : (their instanceof Trees.ApplyImplicitView ? new Trees.ApplyImplicitView(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(apply2.fun()), apply2.args().map(new Serializable(this){
                                                                                                public static final long serialVersionUID = 0L;
                                                                                                private final /* synthetic */ StandardImporter $outer;

                                                                                                public final Trees.Tree apply(Trees.Tree their) {
                                                                                                    return this.$outer.importTree(their);
                                                                                                }
                                                                                                {
                                                                                                    if ($outer == null) {
                                                                                                        throw null;
                                                                                                    }
                                                                                                    this.$outer = $outer;
                                                                                                }
                                                                                            }, List$.MODULE$.canBuildFrom())) : new Trees.Apply(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(apply2.fun()), apply2.args().map(new Serializable(this){
                                                                                                public static final long serialVersionUID = 0L;
                                                                                                private final /* synthetic */ StandardImporter $outer;

                                                                                                public final Trees.Tree apply(Trees.Tree their) {
                                                                                                    return this.$outer.importTree(their);
                                                                                                }
                                                                                                {
                                                                                                    if ($outer == null) {
                                                                                                        throw null;
                                                                                                    }
                                                                                                    this.$outer = $outer;
                                                                                                }
                                                                                            }, List$.MODULE$.canBuildFrom())));
                                                                                            tree = apply3;
                                                                                            break block3;
                                                                                        }
                                                                                        if (!(their instanceof Trees.ApplyDynamic)) break block33;
                                                                                        Trees.ApplyDynamic applyDynamic = (Trees.ApplyDynamic)their;
                                                                                        tree = new Trees.ApplyDynamic(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(applyDynamic.qual()), applyDynamic.args().map(new Serializable(this){
                                                                                            public static final long serialVersionUID = 0L;
                                                                                            private final /* synthetic */ StandardImporter $outer;

                                                                                            public final Trees.Tree apply(Trees.Tree their) {
                                                                                                return this.$outer.importTree(their);
                                                                                            }
                                                                                            {
                                                                                                if ($outer == null) {
                                                                                                    throw null;
                                                                                                }
                                                                                                this.$outer = $outer;
                                                                                            }
                                                                                        }, List$.MODULE$.canBuildFrom()));
                                                                                        break block3;
                                                                                    }
                                                                                    if (!(their instanceof Trees.Super)) break block34;
                                                                                    Trees.Super super_ = (Trees.Super)their;
                                                                                    tree = new Trees.Super(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(super_.qual()), this.importName(super_.mix()).toTypeName());
                                                                                    break block3;
                                                                                }
                                                                                if (!(their instanceof Trees.This)) break block35;
                                                                                Trees.This this_ = (Trees.This)their;
                                                                                tree = new Trees.This(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importName(this_.qual()).toTypeName());
                                                                                break block3;
                                                                            }
                                                                            if (!(their instanceof Trees.Select)) break block36;
                                                                            Trees.Select select = (Trees.Select)their;
                                                                            tree = new Trees.Select(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(select.qualifier()), this.importName(select.name()));
                                                                            break block3;
                                                                        }
                                                                        if (!(their instanceof Trees.Ident)) break block37;
                                                                        Trees.Ident ident = (Trees.Ident)their;
                                                                        tree = new Trees.Ident(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importName(ident.name()));
                                                                        break block3;
                                                                    }
                                                                    if (!(their instanceof Trees.ReferenceToBoxed)) break block38;
                                                                    Trees.ReferenceToBoxed referenceToBoxed = (Trees.ReferenceToBoxed)their;
                                                                    tree2 = this.importTree(referenceToBoxed.ident());
                                                                    if (!(tree2 instanceof Trees.Ident)) break block39;
                                                                    Trees.Ident ident = (Trees.Ident)tree2;
                                                                    tree = new Trees.ReferenceToBoxed(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), ident);
                                                                    break block3;
                                                                }
                                                                throw new MatchError(tree2);
                                                            }
                                                            if (!(their instanceof Trees.Literal) || (literal = (Trees.Literal)their).value() == null) break block40;
                                                            tree = new Trees.Literal(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importConstant(literal.value()));
                                                            break block3;
                                                        }
                                                        if (!(their instanceof Trees.TypeTree)) break block41;
                                                        Trees.TypeTree typeTree = (Trees.TypeTree)their;
                                                        Trees.TypeTree mytt = new Trees.TypeTree(this.scala$reflect$internal$Importers$StandardImporter$$$outer());
                                                        java.io.Serializable serializable = typeTree.original() == null ? BoxedUnit.UNIT : mytt.setOriginal(this.importTree(typeTree.original()));
                                                        tree = mytt;
                                                        break block3;
                                                    }
                                                    if (!(their instanceof Trees.Annotated)) break block42;
                                                    Trees.Annotated annotated = (Trees.Annotated)their;
                                                    tree = new Trees.Annotated(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(annotated.annot()), this.importTree(annotated.arg()));
                                                    break block3;
                                                }
                                                if (!(their instanceof Trees.SingletonTypeTree)) break block43;
                                                Trees.SingletonTypeTree singletonTypeTree = (Trees.SingletonTypeTree)their;
                                                tree = new Trees.SingletonTypeTree(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(singletonTypeTree.ref()));
                                                break block3;
                                            }
                                            if (!(their instanceof Trees.SelectFromTypeTree)) break block44;
                                            Trees.SelectFromTypeTree selectFromTypeTree = (Trees.SelectFromTypeTree)their;
                                            tree = new Trees.SelectFromTypeTree(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(selectFromTypeTree.qualifier()), this.importName(selectFromTypeTree.name()).toTypeName());
                                            break block3;
                                        }
                                        if (!(their instanceof Trees.CompoundTypeTree)) break block45;
                                        Trees.CompoundTypeTree compoundTypeTree = (Trees.CompoundTypeTree)their;
                                        tree = new Trees.CompoundTypeTree(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTemplate(compoundTypeTree.templ()));
                                        break block3;
                                    }
                                    if (!(their instanceof Trees.AppliedTypeTree)) break block46;
                                    Trees.AppliedTypeTree appliedTypeTree = (Trees.AppliedTypeTree)their;
                                    tree = new Trees.AppliedTypeTree(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(appliedTypeTree.tpt()), appliedTypeTree.args().map(new Serializable(this){
                                        public static final long serialVersionUID = 0L;
                                        private final /* synthetic */ StandardImporter $outer;

                                        public final Trees.Tree apply(Trees.Tree their) {
                                            return this.$outer.importTree(their);
                                        }
                                        {
                                            if ($outer == null) {
                                                throw null;
                                            }
                                            this.$outer = $outer;
                                        }
                                    }, List$.MODULE$.canBuildFrom()));
                                    break block3;
                                }
                                if (!(their instanceof Trees.TypeBoundsTree)) break block47;
                                Trees.TypeBoundsTree typeBoundsTree = (Trees.TypeBoundsTree)their;
                                tree = new Trees.TypeBoundsTree(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(typeBoundsTree.lo()), this.importTree(typeBoundsTree.hi()));
                                break block3;
                            }
                            if (!(their instanceof Trees.ExistentialTypeTree)) break block48;
                            Trees.ExistentialTypeTree existentialTypeTree = (Trees.ExistentialTypeTree)their;
                            tree = new Trees.ExistentialTypeTree(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importTree(existentialTypeTree.tpt()), existentialTypeTree.whereClauses().map(new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                private final /* synthetic */ StandardImporter $outer;

                                public final Trees.MemberDef apply(Trees.MemberDef tree) {
                                    return this.$outer.importMemberDef(tree);
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            }, List$.MODULE$.canBuildFrom()));
                            break block3;
                        }
                        if (!((Object)this.from().EmptyTree()).equals(their)) break block49;
                        tree = this.scala$reflect$internal$Importers$StandardImporter$$$outer().EmptyTree();
                        break block3;
                    }
                    if (their != null) break block50;
                    tree = null;
                }
                return tree;
            }
            throw new MatchError(their);
        }

        /*
         * WARNING - void declaration
         */
        public Trees.Tree importTree(Trees.Tree their) {
            void var2_2;
            Object object;
            Trees.Tree my = this.recreateTree(their);
            if (my == null) {
                object = BoxedUnit.UNIT;
            } else {
                this.addFixup((Function0<BoxedUnit>)((Object)new Serializable(this, their, my){
                    public static final long serialVersionUID = 0L;
                    public final /* synthetic */ StandardImporter $outer;
                    public final Trees.Tree their$2;
                    public final Trees.Tree my$1;

                    public final void apply() {
                        this.$outer.recreatedTreeCompleter(this.their$2, this.my$1);
                    }

                    public void apply$mcV$sp() {
                        this.$outer.recreatedTreeCompleter(this.their$2, this.my$1);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.their$2 = their$2;
                        this.my$1 = my$1;
                    }
                }));
                this.tryFixup();
                Position position = their.pos();
                NoPosition$ noPosition$ = this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoPosition();
                object = !(position != null ? !position.equals(noPosition$) : noPosition$ != null) ? BoxedUnit.UNIT : my.setPos(this.importPosition(their.pos()));
            }
            this.importAttachments(their.attachments().all()).foreach(new Serializable(this, my){
                public static final long serialVersionUID = 0L;
                private final Trees.Tree my$1;

                public final Trees.Tree apply(Object x$7) {
                    return (Trees.Tree)this.my$1.updateAttachment(x$7, ClassTag$.MODULE$.Any());
                }
                {
                    this.my$1 = my$1;
                }
            });
            return var2_2;
        }

        public Set<Object> importAttachments(Set<Object> attachments) {
            return attachments.collect(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ StandardImporter $outer;

                public final <A1, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                    Object object;
                    if (x1 instanceof StdAttachments.ImportableAttachment) {
                        StdAttachments.ImportableAttachment importableAttachment = (StdAttachments.ImportableAttachment)x1;
                        object = importableAttachment.importAttachment(this.$outer);
                    } else {
                        object = function1.apply(x1);
                    }
                    return object;
                }

                public final boolean isDefinedAt(Object x1) {
                    boolean bl = x1 instanceof StdAttachments.ImportableAttachment;
                    return bl;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Set$.MODULE$.canBuildFrom());
        }

        public AnnotationInfos.AnnotationInfo importAnnotationInfo(AnnotationInfos.AnnotationInfo ann) {
            Types.Type atp1 = this.importType(ann.atp());
            List<Trees.Tree> args1 = ann.args().map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ StandardImporter $outer;

                public final Trees.Tree apply(Trees.Tree their) {
                    return this.$outer.importTree(their);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
            List<Tuple2<Names.Name, AnnotationInfos.ClassfileAnnotArg>> assocs1 = ann.assocs().map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ StandardImporter $outer;

                public final Tuple2<Names.Name, AnnotationInfos.ClassfileAnnotArg> apply(Tuple2<Names.Name, AnnotationInfos.ClassfileAnnotArg> x0$1) {
                    if (x0$1 != null) {
                        return new Tuple2<Names.Name, AnnotationInfos.ClassfileAnnotArg>(this.$outer.importName(x0$1._1()), this.$outer.importAnnotArg(x0$1._2()));
                    }
                    throw new MatchError(x0$1);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
            Trees.Tree original1 = this.importTree(ann.original());
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().AnnotationInfo().apply(atp1, args1, assocs1).setOriginal(original1);
        }

        public AnnotationInfos.ClassfileAnnotArg importAnnotArg(AnnotationInfos.ClassfileAnnotArg arg) {
            block7: {
                AnnotationInfos.ClassfileAnnotArg classfileAnnotArg;
                block3: {
                    block6: {
                        block5: {
                            block4: {
                                block2: {
                                    AnnotationInfos.LiteralAnnotArg literalAnnotArg;
                                    if (!(arg instanceof AnnotationInfos.LiteralAnnotArg) || (literalAnnotArg = (AnnotationInfos.LiteralAnnotArg)arg).const() == null) break block2;
                                    classfileAnnotArg = new AnnotationInfos.LiteralAnnotArg(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importConstant(literalAnnotArg.const()));
                                    break block3;
                                }
                                if (!(arg instanceof AnnotationInfos.ArrayAnnotArg)) break block4;
                                AnnotationInfos.ArrayAnnotArg arrayAnnotArg = (AnnotationInfos.ArrayAnnotArg)arg;
                                classfileAnnotArg = new AnnotationInfos.ArrayAnnotArg(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), (AnnotationInfos.ClassfileAnnotArg[])Predef$.MODULE$.refArrayOps((Object[])arrayAnnotArg.args()).map(new Serializable(this){
                                    public static final long serialVersionUID = 0L;
                                    private final /* synthetic */ StandardImporter $outer;

                                    public final AnnotationInfos.ClassfileAnnotArg apply(AnnotationInfos.ClassfileAnnotArg arg) {
                                        return this.$outer.importAnnotArg(arg);
                                    }
                                    {
                                        if ($outer == null) {
                                            throw null;
                                        }
                                        this.$outer = $outer;
                                    }
                                }, Array$.MODULE$.canBuildFrom(this.scala$reflect$internal$Importers$StandardImporter$$$outer().JavaArgumentTag())));
                                break block3;
                            }
                            if (!(arg instanceof AnnotationInfos.ScalaSigBytes)) break block5;
                            AnnotationInfos.ScalaSigBytes scalaSigBytes = (AnnotationInfos.ScalaSigBytes)arg;
                            classfileAnnotArg = new AnnotationInfos.ScalaSigBytes(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), scalaSigBytes.bytes());
                            break block3;
                        }
                        if (!(arg instanceof AnnotationInfos.NestedAnnotArg)) break block6;
                        AnnotationInfos.NestedAnnotArg nestedAnnotArg = (AnnotationInfos.NestedAnnotArg)arg;
                        classfileAnnotArg = new AnnotationInfos.NestedAnnotArg(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importAnnotationInfo(nestedAnnotArg.annInfo()));
                        break block3;
                    }
                    if (!this.from().UnmappableAnnotArg().equals(arg)) break block7;
                    classfileAnnotArg = this.scala$reflect$internal$Importers$StandardImporter$$$outer().UnmappableAnnotArg();
                }
                return classfileAnnotArg;
            }
            throw new MatchError(arg);
        }

        public Position importPosition(Position their) {
            return their;
        }

        public Scopes.Scope importScope(Scopes.Scope decls) {
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().newScopeWith(decls.toList().map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ StandardImporter $outer;

                public final Symbols.Symbol apply(Symbols.Symbol their0) {
                    return this.$outer.importSymbol(their0);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom()));
        }

        public Names.Name importName(Names.Name name) {
            return name.isTypeName() ? this.scala$reflect$internal$Importers$StandardImporter$$$outer().newTypeName(name.toString()) : this.scala$reflect$internal$Importers$StandardImporter$$$outer().newTermName(name.toString());
        }

        public Trees.Modifiers importModifiers(Trees.Modifiers mods) {
            return new Trees.Modifiers(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), mods.flags(), this.importName(mods.privateWithin()), mods.annotations().map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ StandardImporter $outer;

                public final Trees.Tree apply(Trees.Tree their) {
                    return this.$outer.importTree(their);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom()));
        }

        public Trees.ImportSelector importImportSelector(Trees.ImportSelector sel) {
            return new Trees.ImportSelector(this.scala$reflect$internal$Importers$StandardImporter$$$outer(), this.importName(sel.name()), sel.namePos(), sel.rename() == null ? null : this.importName(sel.rename()), sel.renamePos());
        }

        public Trees.ValDef importValDef(Trees.ValDef tree) {
            return (Trees.ValDef)this.importTree(tree);
        }

        public Trees.TypeDef importTypeDef(Trees.TypeDef tree) {
            return (Trees.TypeDef)this.importTree(tree);
        }

        public Trees.MemberDef importMemberDef(Trees.MemberDef tree) {
            return (Trees.MemberDef)this.importTree(tree);
        }

        public Trees.Template importTemplate(Trees.Template tree) {
            return (Trees.Template)this.importTree(tree);
        }

        public Trees.RefTree importRefTree(Trees.RefTree tree) {
            return (Trees.RefTree)((Object)this.importTree((Trees.Tree)((Object)tree)));
        }

        public Trees.Ident importIdent(Trees.Ident tree) {
            return (Trees.Ident)this.importTree(tree);
        }

        public Trees.CaseDef importCaseDef(Trees.CaseDef tree) {
            return (Trees.CaseDef)this.importTree(tree);
        }

        public Constants.Constant importConstant(Constants.Constant constant) {
            Object object;
            SymbolTable symbolTable = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            int n = constant.tag();
            switch (n) {
                default: {
                    object = constant.value();
                    break;
                }
                case 13: {
                    object = this.importSymbol((Symbols.Symbol)constant.value());
                    break;
                }
                case 12: {
                    object = this.importType((Types.Type)constant.value());
                }
            }
            return new Constants.Constant(symbolTable, object);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Importers$StandardImporter$$$outer() {
            return this.$outer;
        }

        private final Symbols.Symbol linkReferenced$1(Symbols.TermSymbol my, Symbols.TermSymbol their, Function1 op) {
            this.symMap().weakUpdate(their, my);
            my.referenced_$eq((Symbols.Symbol)op.apply(their.referenced()));
            return my;
        }

        public final Symbols.Symbol scala$reflect$internal$Importers$StandardImporter$$cachedRecreateSymbol$1(Symbols.Symbol their) {
            Symbols.Symbol symbol;
            Option<Symbols.Symbol> option = this.symMap().weakGet(their);
            if (option instanceof Some) {
                Some some = (Some)option;
                symbol = (Symbols.Symbol)some.x();
            } else {
                symbol = this.recreateSymbol(their);
            }
            return symbol;
        }

        private final Symbols.Symbol disambiguate$1(Symbols.Symbol my, Symbols.Symbol their$3) {
            Symbols.SymbolApi symbolApi;
            if (their$3.isMethod()) {
                Symbols.Symbol localCopy = this.scala$reflect$internal$Importers$StandardImporter$$cachedRecreateSymbol$1(their$3);
                symbolApi = my.filter((Function1)((Object)new Serializable(this, localCopy){
                    public static final long serialVersionUID = 0L;
                    private final Symbols.Symbol localCopy$1;

                    public final boolean apply(Symbols.Symbol x$4) {
                        return x$4.tpe().matches(this.localCopy$1.tpe());
                    }
                    {
                        this.localCopy$1 = localCopy$1;
                    }
                }));
            } else {
                symbolApi = my.filter((Function1)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final boolean apply(Symbols.Symbol x$5) {
                        return !x$5.isMethod();
                    }
                }));
            }
            Symbols.SymbolApi result2 = symbolApi;
            Serializable serializable = new Serializable(this, their$3, (Symbols.Symbol)result2){
                public static final long serialVersionUID = 0L;
                public final Symbols.Symbol their$3;
                public final Symbols.Symbol result$1;

                public final String apply() {
                    return new StringBuilder().append((Object)"import failure: cannot determine unique overloaded method alternative from\n ").append((Object)((TraversableOnce)this.result$1.alternatives().map(new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final String apply(Symbols.Symbol x$6) {
                            return x$6.defString();
                        }
                    }, List$.MODULE$.canBuildFrom())).mkString("\n")).append((Object)"\n that matches ").append(this.their$3).append((Object)":").append(this.their$3.tpe()).toString();
                }
                {
                    this.their$3 = their$3;
                    this.result$1 = result$1;
                }
            };
            boolean bl = !((Symbols.Symbol)result2).isOverloaded();
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringBuilder().append((Object)"import failure: cannot determine unique overloaded method alternative from\n ").append((Object)((TraversableOnce)((Symbols.Symbol)result2).alternatives().map(new /* invalid duplicate definition of identical inner class */, List$.MODULE$.canBuildFrom())).mkString("\n")).append((Object)"\n that matches ").append(their$3).append((Object)":").append(their$3.tpe_$times()).toString()).toString());
            }
            return result2;
        }

        private final Symbols.Symbol recreateOrRelink$1(Symbols.Symbol their0$1) {
            Symbols.Symbol symbol;
            if (their0$1 == null) {
                symbol = null;
            } else {
                Symbols.Symbol symbol2 = their0$1;
                Symbols.NoSymbol noSymbol = this.from().NoSymbol();
                if (!(symbol2 != null ? !symbol2.equals(noSymbol) : noSymbol != null)) {
                    symbol = this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoSymbol();
                } else if (their0$1.isRoot()) {
                    symbol = ((Mirrors.RootsBase)this.scala$reflect$internal$Importers$StandardImporter$$$outer().rootMirror()).RootClass();
                } else {
                    Symbols.Symbol myexisting;
                    Symbols.Symbol symbol3;
                    Types$NoType$ myscope;
                    Symbols.Symbol theirexisting;
                    boolean isModuleClass = their0$1.isModuleClass();
                    boolean isTparam = their0$1.isTypeParameter() && their0$1.paramPos() >= 0;
                    boolean isOverloaded = their0$1.isOverloaded();
                    Types$NoType$ theirscope = their0$1.owner().isClass() && !their0$1.owner().isRefinementClass() ? their0$1.owner().info() : this.from().NoType();
                    Symbols.Symbol symbol4 = theirexisting = isModuleClass ? theirscope.decl(their0$1.name()).moduleClass() : theirscope.decl(their0$1.name());
                    if (!theirexisting.exists()) {
                        theirscope = this.from().NoType();
                    }
                    Names.Name myname = this.importName(their0$1.name());
                    Symbols.Symbol myowner = this.importSymbol(their0$1.owner());
                    Types$NoType$ types$NoType$ = theirscope;
                    Types$NoType$ types$NoType$2 = this.from().NoType();
                    Types.Type type = !(types$NoType$ == null ? types$NoType$2 != null : !types$NoType$.equals(types$NoType$2)) || myowner.hasFlag(0x8000000000L) ? this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoType() : (myscope = myowner.info());
                    if (isModuleClass) {
                        symbol3 = this.importSymbol(their0$1.sourceModule()).moduleClass();
                    } else if (isTparam) {
                        symbol3 = myowner.hasFlag(0x8000000000L) ? this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoSymbol() : myowner.typeParams().apply(their0$1.paramPos());
                    } else if (isOverloaded) {
                        symbol3 = myowner.newOverloaded(myowner.thisType(), their0$1.alternatives().map(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ StandardImporter $outer;

                            public final Symbols.Symbol apply(Symbols.Symbol their0) {
                                return this.$outer.importSymbol(their0);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }, List$.MODULE$.canBuildFrom()));
                    } else {
                        Types$NoType$ types$NoType$3 = myscope;
                        Types$NoType$ types$NoType$4 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoType();
                        Symbols.Symbol myexisting2 = !(types$NoType$3 != null ? !types$NoType$3.equals(types$NoType$4) : types$NoType$4 != null) ? this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoSymbol() : myscope.decl(myname);
                        symbol3 = myexisting = myexisting2.isOverloaded() ? this.disambiguate$1(myexisting2, their0$1) : myexisting2;
                    }
                    if (myexisting != myexisting.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                        symbol = myexisting;
                    } else {
                        Object object;
                        Symbols.Symbol my1 = this.scala$reflect$internal$Importers$StandardImporter$$cachedRecreateSymbol$1(their0$1);
                        Types$NoType$ types$NoType$5 = myscope;
                        Types$NoType$ types$NoType$6 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoType();
                        if (!(types$NoType$5 != null ? !types$NoType$5.equals(types$NoType$6) : types$NoType$6 != null)) {
                            object = BoxedUnit.UNIT;
                        } else {
                            Symbols.Symbol symbol5 = myscope.decls().lookup(myname);
                            Symbols.NoSymbol noSymbol2 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoSymbol();
                            boolean bl = !(symbol5 != null ? !symbol5.equals(noSymbol2) : noSymbol2 != null);
                            Predef$ predef$ = Predef$.MODULE$;
                            if (!bl) {
                                Names.Name name = myname;
                                Predef$ predef$2 = Predef$.MODULE$;
                                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringBuilder().append((Object)Predef$any2stringadd$.MODULE$.$plus$extension(name, " ")).append(myscope.decl(myname)).append((Object)" ").append(myexisting).toString()).toString());
                            }
                            object = myscope.decls().enter(my1);
                        }
                        symbol = my1;
                    }
                }
            }
            return symbol;
        }

        public StandardImporter(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this.pendingSyms = 0;
            this.pendingTpes = 0;
        }

        public class Cache<K, V>
        extends WeakHashMap<K, WeakReference<V>> {
            public Option<V> weakGet(K key) {
                Option option;
                Option option2 = this.get(key);
                if (!option2.isEmpty()) {
                    WeakReference weakReference = (WeakReference)option2.get();
                    option = WeakReference$.MODULE$.unapply(weakReference);
                } else {
                    option = None$.MODULE$;
                }
                return option;
            }

            public void weakUpdate(K key, V value) {
                this.update(key, WeakReference$.MODULE$.apply(value));
            }

            public /* synthetic */ StandardImporter scala$reflect$internal$Importers$StandardImporter$Cache$$$outer() {
                return StandardImporter.this;
            }

            public Cache() {
                if (StandardImporter.this == null) {
                    throw null;
                }
            }
        }
    }
}

