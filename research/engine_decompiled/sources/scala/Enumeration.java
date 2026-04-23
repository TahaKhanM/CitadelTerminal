/*
 * Decompiled with CFR 0.152.
 */
package scala;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import scala.Enumeration$ValueOrdering$;
import scala.Enumeration$ValueSet$;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Predef$any2stringadd$;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.AbstractSet;
import scala.collection.GenSet;
import scala.collection.GenSetLike$class;
import scala.collection.Iterator;
import scala.collection.SortedSetLike$class;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.Sorted;
import scala.collection.generic.Sorted$class;
import scala.collection.immutable.BitSet;
import scala.collection.immutable.Iterable$class;
import scala.collection.immutable.Set;
import scala.collection.immutable.Set$class;
import scala.collection.immutable.SortedSet;
import scala.collection.immutable.SortedSet$class;
import scala.collection.immutable.StringOps;
import scala.collection.immutable.Traversable$class;
import scala.collection.mutable.Builder;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Map;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParSet;
import scala.math.Ordered;
import scala.math.Ordered$class;
import scala.math.Ordering;
import scala.reflect.NameTransformer$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.util.matching.Regex$;

@ScalaSignature(bytes="\u0006\u0001\t5h!B\u0001\u0003\u0003\u0003)!aC#ok6,'/\u0019;j_:T\u0011aA\u0001\u0006g\u000e\fG.Y\u0002\u0001'\r\u0001aA\u0003\t\u0003\u000f!i\u0011AA\u0005\u0003\u0013\t\u0011a!\u00118z%\u00164\u0007CA\u0004\f\u0013\ta!A\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0003\u0005\u000f\u0001\t\u0005\t\u0015!\u0003\u0010\u0003\u001dIg.\u001b;jC2\u0004\"a\u0002\t\n\u0005E\u0011!aA%oi\")1\u0003\u0001C\u0001)\u00051A(\u001b8jiz\"\"!\u0006\f\u0011\u0005\u001d\u0001\u0001\"\u0002\b\u0013\u0001\u0004y\u0001\"B\n\u0001\t\u0003AB#A\u000b\t\u000bi\u0001A\u0011C\u000e\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0002\r!)Q\u0004\u0001C!=\u0005AAo\\*ue&tw\rF\u0001 !\t\u0001S%D\u0001\"\u0015\t\u00113%\u0001\u0003mC:<'\"\u0001\u0013\u0002\t)\fg/Y\u0005\u0003M\u0005\u0012aa\u0015;sS:<\u0007b\u0002\u0015\u0001\u0005\u0004%I!K\u0001\u0005m6\f\u0007/F\u0001+!\u0011Y\u0003g\u0004\u001a\u000e\u00031R!!\f\u0018\u0002\u000f5,H/\u00192mK*\u0011qFA\u0001\u000bG>dG.Z2uS>t\u0017BA\u0019-\u0005\ri\u0015\r\u001d\t\u0003gQj\u0011\u0001\u0001\u0004\u0006k\u0001\t\tA\u000e\u0002\u0006-\u0006dW/Z\n\u0005i\u00199$\u0002E\u00029wIr!aB\u001d\n\u0005i\u0012\u0011a\u00029bG.\fw-Z\u0005\u0003yu\u0012qa\u0014:eKJ,GM\u0003\u0002;\u0005!)1\u0003\u000eC\u0001\u007fQ\t!\u0007C\u0003Bi\u0019\u0005!)\u0001\u0002jIV\tq\u0002C\u0005Ei\t\u0005\t\u0011)A\u0005+\u0005i2oY1mC\u0012*e.^7fe\u0006$\u0018n\u001c8%I=,H/\u001a:F]Vl\u0007\u0005C\u0003Gi\u0011\u0005s)A\u0004d_6\u0004\u0018M]3\u0015\u0005=A\u0005\"B%F\u0001\u0004\u0011\u0014\u0001\u0002;iCRDQa\u0013\u001b\u0005B1\u000ba!Z9vC2\u001cHCA'Q!\t9a*\u0003\u0002P\u0005\t9!i\\8mK\u0006t\u0007\"B)K\u0001\u0004\u0011\u0016!B8uQ\u0016\u0014\bCA\u0004T\u0013\t!&AA\u0002B]fDQA\u0016\u001b\u0005B]\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u001f!)\u0011\f\u000eC\u00015\u0006)A\u0005\u001d7vgR\u00191,a\u0019\u0011\u0005Mbf\u0001B/\u0001\u0001y\u0013\u0001BV1mk\u0016\u001cV\r^\n\u00069~\u001b\u0017N\u0003\t\u0004A\u0006\u0014T\"\u0001\u0018\n\u0005\tt#aC!cgR\u0014\u0018m\u0019;TKR\u00042\u0001Z43\u001b\u0005)'B\u00014/\u0003%IW.\\;uC\ndW-\u0003\u0002iK\nI1k\u001c:uK\u0012\u001cV\r\u001e\t\u0005A*\u00144,\u0003\u0002l]\ti1k\u001c:uK\u0012\u001cV\r\u001e'jW\u0016D\u0001\"\u001c/\u0003\u0002\u0003\u0006KA\\\u0001\u0006]:LEm\u001d\t\u0003I>L!\u0001]3\u0003\r\tKGoU3u\u0011\u0019\u0019B\f\"\u0001]eR\u00111l\u001d\u0005\u0006[F\u0004\rA\u001c\u0005\u0006kr#\u0019A^\u0001\t_J$WM]5oOV\tq\u000fE\u00029qJJ!!_\u001f\u0003\u0011=\u0013H-\u001a:j]\u001eDQa\u001f/\u0005\u0002q\f\u0011B]1oO\u0016LU\u000e\u001d7\u0015\tmk\u0018Q\u0001\u0005\u0006}j\u0004\ra`\u0001\u0005MJ|W\u000e\u0005\u0003\b\u0003\u0003\u0011\u0014bAA\u0002\u0005\t1q\n\u001d;j_:Da!a\u0002{\u0001\u0004y\u0018!B;oi&d\u0007bBA\u00069\u0012\u0005\u0013QB\u0001\u0006K6\u0004H/_\u000b\u00027\"9\u0011\u0011\u0003/\u0005\u0002\u0005M\u0011\u0001C2p]R\f\u0017N\\:\u0015\u00075\u000b)\u0002C\u0004\u0002\u0018\u0005=\u0001\u0019\u0001\u001a\u0002\u0003YDa!\u0017/\u0005\u0002\u0005mAcA.\u0002\u001e!9\u0011qDA\r\u0001\u0004\u0011\u0014!\u0002<bYV,\u0007bBA\u00129\u0012\u0005\u0011QE\u0001\u0007I5Lg.^:\u0015\u0007m\u000b9\u0003C\u0004\u0002 \u0005\u0005\u0002\u0019\u0001\u001a\t\u000f\u0005-B\f\"\u0001\u0002.\u0005A\u0011\u000e^3sCR|'/\u0006\u0002\u00020A!\u0001-!\r3\u0013\r\t\u0019D\f\u0002\t\u0013R,'/\u0019;pe\"9\u0011q\u0007/\u0005B\u0005e\u0012\u0001E6fsNLE/\u001a:bi>\u0014hI]8n)\u0011\ty#a\u000f\t\u000f\u0005u\u0012Q\u0007a\u0001e\u0005)1\u000f^1si\"9\u0011\u0011\t/\u0005B\u0005\r\u0013\u0001D:ue&tw\r\u0015:fM&DXCAA#!\u0011\t9%!\u0014\u000f\u0007\u001d\tI%C\u0002\u0002L\t\ta\u0001\u0015:fI\u00164\u0017b\u0001\u0014\u0002P)\u0019\u00111\n\u0002\t\u000f\u0005MC\f\"\u0001\u0002V\u0005IAo\u001c\"ji6\u000b7o[\u000b\u0003\u0003/\u0002RaBA-\u0003;J1!a\u0017\u0003\u0005\u0015\t%O]1z!\r9\u0011qL\u0005\u0004\u0003C\u0012!\u0001\u0002'p]\u001eDa!a\u0006Y\u0001\u0004\u0011\u0004\u0002DA4i\t\u0005)\u0019!C\u0001\u0001\u0005%\u0014\u0001H:dC2\fG%\u00128v[\u0016\u0014\u0018\r^5p]\u0012\"s.\u001e;fe\u0016sW/\\\u000b\u0002+!:A'!\u001c\u0002 \u0005M\u0004cA\u0004\u0002p%\u0019\u0011\u0011\u000f\u0002\u0003!M+'/[1m-\u0016\u00148/[8o+&#e\u0004\u00032jy>j\u001f%H)\t\u000f\u0005]\u0004\u0001)A\u0005U\u0005)a/\\1qA!I\u00111\u0010\u0001A\u0002\u0013%\u0011QB\u0001\u0005mN,G\u000fC\u0005\u0002\u0000\u0001\u0001\r\u0011\"\u0003\u0002\u0002\u0006Aao]3u?\u0012*\u0017\u000f\u0006\u0003\u0002\u0004\u0006%\u0005cA\u0004\u0002\u0006&\u0019\u0011q\u0011\u0002\u0003\tUs\u0017\u000e\u001e\u0005\n\u0003\u0017\u000bi(!AA\u0002m\u000b1\u0001\u001f\u00132\u0011\u001d\ty\t\u0001Q!\nm\u000bQA^:fi\u0002BC!!$\u0002\u0014B\u0019q!!&\n\u0007\u0005]%AA\u0005ue\u0006t7/[3oi\"I\u00111\u0014\u0001A\u0002\u0013%\u0011QT\u0001\fmN,G\u000fR3gS:,G-F\u0001N\u0011%\t\t\u000b\u0001a\u0001\n\u0013\t\u0019+A\bwg\u0016$H)\u001a4j]\u0016$w\fJ3r)\u0011\t\u0019)!*\t\u0013\u0005-\u0015qTA\u0001\u0002\u0004i\u0005bBAU\u0001\u0001\u0006K!T\u0001\rmN,G\u000fR3gS:,G\r\t\u0015\u0005\u0003O\u000bi\u000bE\u0002\b\u0003_K1!!-\u0003\u0005!1x\u000e\\1uS2,\u0007\u0006BAT\u0003'C\u0011\"a.\u0001\u0005\u0004%I!!/\u0002\t9l\u0017\r]\u000b\u0003\u0003w\u0003Ra\u000b\u0019\u0010\u0003\u000bB\u0001\"a0\u0001A\u0003%\u00111X\u0001\u0006]6\f\u0007\u000f\t\u0005\b\u0003\u0007\u0004A\u0011AA\u0007\u0003\u00191\u0018\r\\;fg\"A\u0011q\u0019\u0001A\u0002\u0013E!)\u0001\u0004oKb$\u0018\n\u001a\u0005\n\u0003\u0017\u0004\u0001\u0019!C\t\u0003\u001b\f!B\\3yi&#w\fJ3r)\u0011\t\u0019)a4\t\u0013\u0005-\u0015\u0011ZA\u0001\u0002\u0004y\u0001bBAj\u0001\u0001\u0006KaD\u0001\b]\u0016DH/\u00133!\u0011-\t9\u000e\u0001a\u0001\u0002\u0004%\t\"!7\u0002\u00119,\u0007\u0010\u001e(b[\u0016,\"!a7\u0011\u000ba\ni.!\u0012\n\u0007\u0005MR\bC\u0006\u0002b\u0002\u0001\r\u00111A\u0005\u0012\u0005\r\u0018\u0001\u00048fqRt\u0015-\\3`I\u0015\fH\u0003BAB\u0003KD!\"a#\u0002`\u0006\u0005\t\u0019AAn\u0011!\tI\u000f\u0001Q!\n\u0005m\u0017!\u00038fqRt\u0015-\\3!\u0011\u001d\ti\u000f\u0001C\u0005\u0003\u0007\naB\\3yi:\u000bW.Z(s\u001dVdG\u000e\u0003\u0005\u0002r\u0002\u0001\r\u0011\"\u0003C\u0003\u0015!x\u000e]%e\u0011%\t)\u0010\u0001a\u0001\n\u0013\t90A\u0005u_BLEm\u0018\u0013fcR!\u00111QA}\u0011%\tY)a=\u0002\u0002\u0003\u0007q\u0002C\u0004\u0002~\u0002\u0001\u000b\u0015B\b\u0002\rQ|\u0007/\u00133!\u0011!\u0011\t\u0001\u0001a\u0001\n\u0013\u0011\u0015\u0001\u00032piR|W.\u00133\t\u0013\t\u0015\u0001\u00011A\u0005\n\t\u001d\u0011\u0001\u00042piR|W.\u00133`I\u0015\fH\u0003BAB\u0005\u0013A\u0011\"a#\u0003\u0004\u0005\u0005\t\u0019A\b\t\u000f\t5\u0001\u0001)Q\u0005\u001f\u0005I!m\u001c;u_6LE\r\t\u0005\u0007\u0005#\u0001AQ\u0001\"\u0002\u000b5\f\u00070\u00133\t\u000f\tU\u0001\u0001\"\u0002\u0003\u0018\u0005)\u0011\r\u001d9msR\u0019!G!\u0007\t\u000f\tm!1\u0003a\u0001\u001f\u0005\t\u0001\u0010C\u0004\u0003 \u0001!)A!\t\u0002\u0011]LG\u000f\u001b(b[\u0016$2A\rB\u0012\u0011!\u0011)C!\bA\u0002\u0005\u0015\u0013!A:\t\u000f\t%\u0002\u0001\"\u0006\u0003,\u0005)a+\u00197vKV\t!\u0007C\u0004\u0003*\u0001!)Ba\f\u0015\u0007I\u0012\t\u0004C\u0004\u00034\t5\u0002\u0019A\b\u0002\u0003%DqA!\u000b\u0001\t+\u00119\u0004F\u00023\u0005sA\u0001Ba\u000f\u00036\u0001\u0007\u0011QI\u0001\u0005]\u0006lW\rC\u0004\u0003*\u0001!)Ba\u0010\u0015\u000bI\u0012\tEa\u0011\t\u000f\tM\"Q\ba\u0001\u001f!A!1\bB\u001f\u0001\u0004\t)\u0005C\u0004\u0003H\u0001!IA!\u0013\u0002\u001fA|\u0007/\u001e7bi\u0016t\u0015-\\3NCB$\"!a!\t\u000f\t5\u0003\u0001\"\u0003\u0003P\u00051a.Y7f\u001f\u001a$B!!\u0012\u0003R!9!1\u0007B&\u0001\u0004yaA\u0002B+\u0001!\u00119FA\u0002WC2\u001cBAa\u00153\u0015!Q!1\u0007B*\u0005\u0003\u0005\u000b\u0011B\b\t\u0017\tm\"1\u000bB\u0001B\u0003%\u0011Q\t\u0005\b'\tMC\u0011\u0001B0)\u0019\u0011\tGa\u0019\u0003fA\u00191Ga\u0015\t\u000f\tM\"Q\fa\u0001\u001f!A!1\bB/\u0001\u0004\t)\u0005C\u0004\u0014\u0005'\"\tA!\u001b\u0015\t\t\u0005$1\u000e\u0005\b\u0005g\u00119\u00071\u0001\u0010\u0011\u001d\u0019\"1\u000bC\u0001\u0005_\"BA!\u0019\u0003r!A!1\bB7\u0001\u0004\t)\u0005C\u0004\u0014\u0005'\"\tA!\u001e\u0015\u0005\t\u0005\u0004BB!\u0003T\u0011\u0005!\t\u0003\u0004\u001e\u0005'\"\tE\b\u0005\u00075\tMC\u0011C\u000e)\u0011\tM\u0013QNA\u0010\u0005\u007fr\u0002bt5h_'g`~T\u0004\b\u0005\u0007\u0003\u0001\u0012\u0001BC\u000351\u0016\r\\;f\u001fJ$WM]5oOB\u00191Ga\"\u0007\u000f\t%\u0005\u0001#\u0001\u0003\f\nia+\u00197vK>\u0013H-\u001a:j]\u001e\u001cRAa\"\u0003\u000e^\u00042\u0001\tBH\u0013\r\u0011\t*\t\u0002\u0007\u001f\nTWm\u0019;\t\u000fM\u00119\t\"\u0001\u0003\u0016R\u0011!Q\u0011\u0005\b\r\n\u001dE\u0011\u0001BM)\u0015y!1\u0014BO\u0011\u001d\u0011YBa&A\u0002IBqAa(\u0003\u0018\u0002\u0007!'A\u0001z\u000f\u001d\u0011\u0019\u000b\u0001E\u0001\u0005K\u000b\u0001BV1mk\u0016\u001cV\r\u001e\t\u0004g\t\u001dfAB/\u0001\u0011\u0003\u0011Ik\u0005\u0003\u0003(\u001aQ\u0001bB\n\u0003(\u0012\u0005!Q\u0016\u000b\u0003\u0005KC!\"a\u0003\u0003(\n\u0007I\u0011AA\u0007\u0011!\u0011\u0019La*!\u0002\u0013Y\u0016AB3naRL\b\u0005\u0003\u0005\u0003\u0016\t\u001dF\u0011\u0001B\\)\rY&\u0011\u0018\u0005\t\u0005w\u0013)\f1\u0001\u0003>\u0006)Q\r\\3ngB!qAa03\u0013\r\u0011\tM\u0001\u0002\u000byI,\u0007/Z1uK\u0012t\u0004\u0002\u0003Bc\u0005O#\tAa2\u0002\u0017\u0019\u0014x.\u001c\"ji6\u000b7o\u001b\u000b\u00047\n%\u0007\u0002\u0003B^\u0005\u0007\u0004\r!a\u0016\t\u0011\t5'q\u0015C\u0001\u0005\u001f\f!B\\3x\u0005VLG\u000eZ3s+\t\u0011\t\u000eE\u0003,\u0005'\u00144,C\u0002\u0003V2\u0012qAQ;jY\u0012,'\u000f\u0003\u0005\u0003Z\n\u001dF1\u0001Bn\u00031\u0019\u0017M\u001c\"vS2$gI]8n+\t\u0011i\u000eE\u0004\u0003`\n\u00158LM.\u000e\u0005\t\u0005(b\u0001Br]\u00059q-\u001a8fe&\u001c\u0017\u0002\u0002Bt\u0005C\u0014AbQ1o\u0005VLG\u000e\u001a$s_6Ds\u0001AA7\u0003?\u0011YO\b\u0005vA7o\u000eTD-\u000f\u0002")
public abstract class Enumeration
implements Serializable {
    public static final long serialVersionUID = 8476000850333817230L;
    private final Map<Object, Value> scala$Enumeration$$vmap = new HashMap<Object, Value>();
    private transient ValueSet vset = null;
    private volatile transient boolean scala$Enumeration$$vsetDefined = false;
    private final Map<Object, String> scala$Enumeration$$nmap = new HashMap<Object, String>();
    private int nextId;
    private Iterator<String> nextName;
    private int scala$Enumeration$$topId;
    private int scala$Enumeration$$bottomId;
    private volatile Enumeration$ValueOrdering$ ValueOrdering$module;
    private volatile Enumeration$ValueSet$ ValueSet$module;

    private Enumeration$ValueOrdering$ ValueOrdering$lzycompute() {
        Enumeration enumeration = this;
        synchronized (enumeration) {
            if (this.ValueOrdering$module == null) {
                this.ValueOrdering$module = new Enumeration$ValueOrdering$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ValueOrdering$module;
        }
    }

    private Enumeration$ValueSet$ ValueSet$lzycompute() {
        Enumeration enumeration = this;
        synchronized (enumeration) {
            if (this.ValueSet$module == null) {
                this.ValueSet$module = new Enumeration$ValueSet$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ValueSet$module;
        }
    }

    public Object readResolve() {
        return this.getClass().getField(NameTransformer$.MODULE$.MODULE_INSTANCE_NAME()).get(null);
    }

    public String toString() {
        String string2 = this.getClass().getName();
        Predef$ predef$ = Predef$.MODULE$;
        String string3 = new StringOps(string2).stripSuffix(NameTransformer$.MODULE$.MODULE_SUFFIX_STRING());
        Predef$ predef$2 = Predef$.MODULE$;
        return (String)Predef$.MODULE$.refArrayOps((Object[])((String)Predef$.MODULE$.refArrayOps((Object[])new StringOps(string3).split('.')).last()).split(Regex$.MODULE$.quote(NameTransformer$.MODULE$.NAME_JOIN_STRING()))).last();
    }

    public Map<Object, Value> scala$Enumeration$$vmap() {
        return this.scala$Enumeration$$vmap;
    }

    private ValueSet vset() {
        return this.vset;
    }

    private void vset_$eq(ValueSet x$1) {
        this.vset = x$1;
    }

    private boolean scala$Enumeration$$vsetDefined() {
        return this.scala$Enumeration$$vsetDefined;
    }

    public void scala$Enumeration$$vsetDefined_$eq(boolean x$1) {
        this.scala$Enumeration$$vsetDefined = x$1;
    }

    public Map<Object, String> scala$Enumeration$$nmap() {
        return this.scala$Enumeration$$nmap;
    }

    public ValueSet values() {
        if (!this.scala$Enumeration$$vsetDefined()) {
            this.vset_$eq((ValueSet)((Builder)this.ValueSet().newBuilder().$plus$plus$eq(this.scala$Enumeration$$vmap().values())).result());
            this.scala$Enumeration$$vsetDefined_$eq(true);
        }
        return this.vset();
    }

    public int nextId() {
        return this.nextId;
    }

    public void nextId_$eq(int x$1) {
        this.nextId = x$1;
    }

    public Iterator<String> nextName() {
        return this.nextName;
    }

    public void nextName_$eq(Iterator<String> x$1) {
        this.nextName = x$1;
    }

    public String scala$Enumeration$$nextNameOrNull() {
        return this.nextName() != null && this.nextName().hasNext() ? this.nextName().next() : null;
    }

    public int scala$Enumeration$$topId() {
        return this.scala$Enumeration$$topId;
    }

    public void scala$Enumeration$$topId_$eq(int x$1) {
        this.scala$Enumeration$$topId = x$1;
    }

    public int scala$Enumeration$$bottomId() {
        return this.scala$Enumeration$$bottomId;
    }

    public void scala$Enumeration$$bottomId_$eq(int x$1) {
        this.scala$Enumeration$$bottomId = x$1;
    }

    public final int maxId() {
        return this.scala$Enumeration$$topId();
    }

    public final Value apply(int x) {
        return (Value)this.scala$Enumeration$$vmap().apply(BoxesRunTime.boxToInteger(x));
    }

    public final Value withName(String s2) {
        Serializable serializable = new Serializable(this, s2){
            public static final long serialVersionUID = 0L;
            public final String s$1;

            public final Nothing$ apply() {
                throw new NoSuchElementException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"No value found for '", "'"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.s$1})));
            }
            {
                this.s$1 = s$1;
            }
        };
        Option<Value> option = this.values().find(new Serializable(this, s2){
            public static final long serialVersionUID = 0L;
            private final String s$1;

            public final boolean apply(Value x$1) {
                String string2 = x$1.toString();
                String string3 = this.s$1;
                return !(string2 != null ? !string2.equals(string3) : string3 != null);
            }
            {
                this.s$1 = s$1;
            }
        });
        if (option.isEmpty()) {
            throw new NoSuchElementException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"No value found for '", "'"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{serializable.s$1})));
        }
        return option.get();
    }

    public final Value Value() {
        return this.Value(this.nextId());
    }

    public final Value Value(int i) {
        return this.Value(i, this.scala$Enumeration$$nextNameOrNull());
    }

    public final Value Value(String name) {
        return this.Value(this.nextId(), name);
    }

    public final Value Value(int i, String name) {
        return new Val(this, i, name);
    }

    public void scala$Enumeration$$populateNameMap() {
        Field[] fields = this.getClass().getDeclaredFields();
        Method[] methods = (Method[])Predef$.MODULE$.refArrayOps((Object[])this.getClass().getMethods()).filter(new Serializable(this, fields){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Enumeration $outer;
            private final Field[] fields$1;

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean apply(Method m) {
                if (!Predef$.MODULE$.refArrayOps((Object[])m.getParameterTypes()).isEmpty()) return false;
                if (!Value.class.isAssignableFrom(m.getReturnType())) return false;
                Class<?> clazz = m.getDeclaringClass();
                if (clazz != null) {
                    if (clazz.equals(Enumeration.class)) return false;
                }
                if (!this.$outer.scala$Enumeration$$isValDef$1(m, this.fields$1)) return false;
                return true;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.fields$1 = fields$1;
            }
        });
        Predef$.MODULE$.refArrayOps((Object[])methods).foreach(new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Enumeration $outer;

            public final Object apply(Method m) {
                Object object;
                String name = m.getName();
                Value value = (Value)m.invoke((Object)this.$outer, new Object[0]);
                if (value.scala$Enumeration$$outerEnum() == this.$outer) {
                    int id = BoxesRunTime.unboxToInt(Val.class.getMethod("id", new Class[0]).invoke((Object)value, new Object[0]));
                    object = this.$outer.scala$Enumeration$$nmap().$plus$eq(new Tuple2<Integer, String>(BoxesRunTime.boxToInteger(id), name));
                } else {
                    object = BoxedUnit.UNIT;
                }
                return object;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public synchronized String scala$Enumeration$$nameOf(int i) {
        return this.scala$Enumeration$$nmap().getOrElse(BoxesRunTime.boxToInteger(i), new Serializable(this, i){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Enumeration $outer;
            private final int i$1;

            public final String apply() {
                this.$outer.scala$Enumeration$$populateNameMap();
                return (String)this.$outer.scala$Enumeration$$nmap().apply(BoxesRunTime.boxToInteger(this.i$1));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.i$1 = i$1;
            }
        });
    }

    public Enumeration$ValueOrdering$ ValueOrdering() {
        return this.ValueOrdering$module == null ? this.ValueOrdering$lzycompute() : this.ValueOrdering$module;
    }

    public Enumeration$ValueSet$ ValueSet() {
        return this.ValueSet$module == null ? this.ValueSet$lzycompute() : this.ValueSet$module;
    }

    public final boolean scala$Enumeration$$isValDef$1(Method m, Field[] fields$1) {
        return Predef$.MODULE$.refArrayOps((Object[])fields$1).exists(new Serializable(this, m){
            public static final long serialVersionUID = 0L;
            private final Method m$1;

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean apply(Field fd) {
                String string2 = fd.getName();
                String string3 = this.m$1.getName();
                if (string2 == null) {
                    if (string3 != null) {
                        return false;
                    }
                } else if (!string2.equals(string3)) return false;
                Class<?> clazz = fd.getType();
                Class<?> clazz2 = this.m$1.getReturnType();
                if (clazz == null) {
                    if (clazz2 == null) return true;
                    return false;
                } else {
                    if (!clazz.equals(clazz2)) return false;
                    return true;
                }
            }
            {
                this.m$1 = m$1;
            }
        });
    }

    public Enumeration(int initial) {
        this.nextId = initial;
        this.scala$Enumeration$$topId = initial;
        this.scala$Enumeration$$bottomId = initial < 0 ? initial : 0;
    }

    public Enumeration() {
        this(0);
    }

    public class Val
    extends Value {
        public static final long serialVersionUID = -3501153230598116017L;
        public final int scala$Enumeration$Val$$i;
        private final String name;

        @Override
        public int id() {
            return this.scala$Enumeration$Val$$i;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public String toString() {
            String string2;
            if (this.name != null) {
                string2 = this.name;
                return string2;
            }
            try {
                string2 = this.scala$Enumeration$Val$$$outer().scala$Enumeration$$nameOf(this.scala$Enumeration$Val$$i);
                return string2;
            }
            catch (NoSuchElementException noSuchElementException) {
                string2 = new StringBuilder().append((Object)"<Invalid enum: no field for #").append(BoxesRunTime.boxToInteger(this.scala$Enumeration$Val$$i)).append((Object)">").toString();
            }
            return string2;
        }

        public Object readResolve() {
            Enumeration enumeration = (Enumeration)this.scala$Enumeration$Val$$$outer().readResolve();
            return enumeration.scala$Enumeration$$vmap() == null ? this : enumeration.scala$Enumeration$$vmap().apply(BoxesRunTime.boxToInteger(this.scala$Enumeration$Val$$i));
        }

        public /* synthetic */ Enumeration scala$Enumeration$Val$$$outer() {
            return this.$outer;
        }

        public Val(Enumeration $outer, int i, String name) {
            this.scala$Enumeration$Val$$i = i;
            this.name = name;
            boolean bl = !$outer.scala$Enumeration$$vmap().isDefinedAt(BoxesRunTime.boxToInteger(i));
            Predef$ predef$ = Predef$.MODULE$;
            if (bl) {
                $outer.scala$Enumeration$$vmap().update(BoxesRunTime.boxToInteger(i), this);
                $outer.scala$Enumeration$$vsetDefined_$eq(false);
                $outer.nextId_$eq(i + 1);
                if ($outer.nextId() > $outer.scala$Enumeration$$topId()) {
                    $outer.scala$Enumeration$$topId_$eq($outer.nextId());
                }
                if (i < $outer.scala$Enumeration$$bottomId()) {
                    $outer.scala$Enumeration$$bottomId_$eq(i);
                }
                return;
            }
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringBuilder().append((Object)"Duplicate id: ").append(BoxesRunTime.boxToInteger(this.scala$Enumeration$Val$$i)).toString()).toString());
        }

        public Val(Enumeration $outer, int i) {
            this($outer, i, $outer.scala$Enumeration$$nextNameOrNull());
        }

        public Val(Enumeration $outer, String name) {
            this($outer, $outer.nextId(), name);
        }

        public Val(Enumeration $outer) {
            this($outer, $outer.nextId());
        }
    }

    public abstract class Value
    implements Ordered<Value>,
    Serializable {
        public static final long serialVersionUID = 7091335633555234129L;
        private final Enumeration scala$Enumeration$$outerEnum;

        @Override
        public boolean $less(Object that) {
            return Ordered$class.$less(this, that);
        }

        @Override
        public boolean $greater(Object that) {
            return Ordered$class.$greater(this, that);
        }

        @Override
        public boolean $less$eq(Object that) {
            return Ordered$class.$less$eq(this, that);
        }

        @Override
        public boolean $greater$eq(Object that) {
            return Ordered$class.$greater$eq(this, that);
        }

        @Override
        public int compareTo(Object that) {
            return Ordered$class.compareTo(this, that);
        }

        public abstract int id();

        public Enumeration scala$Enumeration$$outerEnum() {
            return this.scala$Enumeration$$outerEnum;
        }

        @Override
        public int compare(Value that) {
            return this.id() < that.id() ? -1 : (this.id() == that.id() ? 0 : 1);
        }

        public boolean equals(Object other) {
            boolean bl;
            if (other instanceof Value) {
                Value value = (Value)other;
                bl = this.scala$Enumeration$$outerEnum() == value.scala$Enumeration$$outerEnum() && this.id() == value.id();
            } else {
                bl = false;
            }
            return bl;
        }

        public int hashCode() {
            return this.id();
        }

        public ValueSet $plus(Value v) {
            return this.scala$Enumeration$Value$$$outer().ValueSet().apply(Predef$.MODULE$.wrapRefArray((Object[])new Value[]{this, v}));
        }

        public /* synthetic */ Enumeration scala$Enumeration$Value$$$outer() {
            return Enumeration.this;
        }

        public Value() {
            if (Enumeration.this == null) {
                throw null;
            }
            Ordered$class.$init$(this);
            this.scala$Enumeration$$outerEnum = Enumeration.this;
        }
    }

    public class ValueSet
    extends AbstractSet<Value>
    implements SortedSet<Value>,
    Serializable {
        private BitSet nnIds;
        public final /* synthetic */ Enumeration $outer;

        @Override
        public /* synthetic */ boolean scala$collection$SortedSetLike$$super$subsetOf(GenSet that) {
            return GenSetLike$class.subsetOf(this, that);
        }

        @Override
        public scala.collection.SortedSet keySet() {
            return SortedSetLike$class.keySet(this);
        }

        @Override
        public Object firstKey() {
            return SortedSetLike$class.firstKey(this);
        }

        @Override
        public Object lastKey() {
            return SortedSetLike$class.lastKey(this);
        }

        @Override
        public scala.collection.SortedSet from(Object from2) {
            return SortedSetLike$class.from(this, from2);
        }

        @Override
        public scala.collection.SortedSet until(Object until2) {
            return SortedSetLike$class.until(this, until2);
        }

        @Override
        public scala.collection.SortedSet range(Object from2, Object until2) {
            return SortedSetLike$class.range(this, from2, until2);
        }

        @Override
        public boolean subsetOf(GenSet<Value> that) {
            return SortedSetLike$class.subsetOf(this, that);
        }

        @Override
        public Iterator iteratorFrom(Object start) {
            return SortedSetLike$class.iteratorFrom(this, start);
        }

        @Override
        public int compare(Object k0, Object k1) {
            return Sorted$class.compare(this, k0, k1);
        }

        @Override
        public Sorted to(Object to2) {
            return Sorted$class.to(this, to2);
        }

        @Override
        public boolean hasAll(Iterator<Value> j) {
            return Sorted$class.hasAll(this, j);
        }

        @Override
        public GenericCompanion<Set> companion() {
            return Set$class.companion(this);
        }

        @Override
        public <B> Set<B> toSet() {
            return Set$class.toSet(this);
        }

        @Override
        public Set<Value> seq() {
            return Set$class.seq(this);
        }

        @Override
        public Combiner<Value, ParSet<Value>> parCombiner() {
            return Set$class.parCombiner(this);
        }

        @Override
        public Ordering<Value> ordering() {
            return this.scala$Enumeration$ValueSet$$$outer().ValueOrdering();
        }

        @Override
        public ValueSet rangeImpl(Option<Value> from2, Option<Value> until2) {
            return new ValueSet(this.scala$Enumeration$ValueSet$$$outer(), (BitSet)this.nnIds.rangeImpl(from2.isEmpty() ? None$.MODULE$ : new Some<Integer>(BoxesRunTime.boxToInteger(from2.get().id() - this.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId())), until2.isEmpty() ? None$.MODULE$ : new Some<Integer>(BoxesRunTime.boxToInteger(until2.get().id() - this.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId()))));
        }

        @Override
        public ValueSet empty() {
            return this.scala$Enumeration$ValueSet$$$outer().ValueSet().empty();
        }

        @Override
        public boolean contains(Value v) {
            return this.nnIds.contains(v.id() - this.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId());
        }

        @Override
        public ValueSet $plus(Value value) {
            return new ValueSet(this.scala$Enumeration$ValueSet$$$outer(), this.nnIds.$plus(value.id() - this.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId()));
        }

        @Override
        public ValueSet $minus(Value value) {
            return new ValueSet(this.scala$Enumeration$ValueSet$$$outer(), this.nnIds.$minus(value.id() - this.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId()));
        }

        @Override
        public Iterator<Value> iterator() {
            return this.nnIds.iterator().map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ValueSet $outer;

                public final Value apply(int id) {
                    return this.$outer.scala$Enumeration$ValueSet$$$outer().apply(this.$outer.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId() + id);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
        }

        @Override
        public Iterator<Value> keysIteratorFrom(Value start) {
            return this.nnIds.keysIteratorFrom(start.id()).map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ValueSet $outer;

                public final Value apply(int id) {
                    return this.$outer.scala$Enumeration$ValueSet$$$outer().apply(this.$outer.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId() + id);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
        }

        @Override
        public String stringPrefix() {
            return Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(this.scala$Enumeration$ValueSet$$$outer()), ".ValueSet");
        }

        public long[] toBitMask() {
            return this.nnIds.toBitMask();
        }

        public /* synthetic */ Enumeration scala$Enumeration$ValueSet$$$outer() {
            return this.$outer;
        }

        public ValueSet(Enumeration $outer, BitSet nnIds) {
            this.nnIds = nnIds;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Traversable$class.$init$(this);
            Iterable$class.$init$(this);
            Set$class.$init$(this);
            Sorted$class.$init$(this);
            SortedSetLike$class.$init$(this);
            scala.collection.SortedSet$class.$init$(this);
            SortedSet$class.$init$(this);
        }
    }
}

