/*
 * Decompiled with CFR 0.152.
 */
package scala.sys.process;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.mutable.StringBuilder;
import scala.concurrent.SyncVar;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.sys.package$;
import scala.sys.process.BasicIO$;
import scala.sys.process.Process;
import scala.sys.process.Process$;
import scala.sys.process.ProcessBuilder;
import scala.sys.process.ProcessIO;
import scala.sys.process.ProcessImpl$CompoundProcess$;
import scala.sys.process.ProcessImpl$Future$;
import scala.sys.process.ProcessImpl$Spawn$;
import scala.sys.process.processInternal$;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(bytes="\u0006\u0001\t]hAC\u0001\u0003!\u0003\r\tA\u0001\u0005\u0003r\nY\u0001K]8dKN\u001c\u0018*\u001c9m\u0015\t\u0019A!A\u0004qe>\u001cWm]:\u000b\u0005\u00151\u0011aA:zg*\tq!A\u0003tG\u0006d\u0017m\u0005\u0002\u0001\u0013A\u0011!bC\u0007\u0002\r%\u0011AB\u0002\u0002\u0007\u0003:L(+\u001a4\t\u000b9\u0001A\u0011\u0001\t\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012!\u0005\t\u0003\u0015II!a\u0005\u0004\u0003\tUs\u0017\u000e^\u0004\u0007+\u0001A\tA\u0001\f\u0002\u000bM\u0003\u0018m\u001e8\u0011\u0005]AR\"\u0001\u0001\u0007\re\u0001\u0001\u0012\u0001\u0002\u001b\u0005\u0015\u0019\u0006/Y<o'\tA\u0012\u0002C\u0003\u001d1\u0011\u0005Q$\u0001\u0004=S:LGO\u0010\u000b\u0002-!)q\u0004\u0007C\u0001A\u0005)\u0011\r\u001d9msR\u0011\u0011%\u000b\t\u0003E\u001dj\u0011a\t\u0006\u0003I\u0015\nA\u0001\\1oO*\ta%\u0001\u0003kCZ\f\u0017B\u0001\u0015$\u0005\u0019!\u0006N]3bI\"1!F\bCA\u0002-\n\u0011A\u001a\t\u0004\u00151\n\u0012BA\u0017\u0007\u0005!a$-\u001f8b[\u0016t\u0004\"B\u0010\u0019\t\u0003yCcA\u00111c!1!F\fCA\u0002-BQA\r\u0018A\u0002M\na\u0001Z1f[>t\u0007C\u0001\u00065\u0013\t)dAA\u0004C_>dW-\u00198\b\r]\u0002\u0001\u0012\u0001\u00029\u0003\u00191U\u000f^;sKB\u0011q#\u000f\u0004\u0007u\u0001A\tAA\u001e\u0003\r\u0019+H/\u001e:f'\tI\u0014\u0002C\u0003\u001ds\u0011\u0005Q\bF\u00019\u0011\u0015y\u0012\b\"\u0001@+\t\u0001e\t\u0006\u0002B\u001fB\u0019!B\u0011#\n\u0005\r3!!\u0003$v]\u000e$\u0018n\u001c81!\t)e\t\u0004\u0001\u0005\u000b\u001ds$\u0019\u0001%\u0003\u0003Q\u000b\"!\u0013'\u0011\u0005)Q\u0015BA&\u0007\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"AC'\n\u000593!aA!os\"1!F\u0010CA\u0002A\u00032A\u0003\u0017E\r\u0015\u0011\u0006\u0001\u0001\u0002T\u0005)\te\u000e\u001a)s_\u000e,7o]\n\u0003#R\u0003\"aF+\u0007\u000bY\u0003\u0001AA,\u0003#M+\u0017/^3oi&\fG\u000e\u0015:pG\u0016\u001c8o\u0005\u0002V1B\u0011q#\u0017\u0004\u00075\u0002\t\tAA.\u0003\u001f\r{W\u000e]8v]\u0012\u0004&o\\2fgN\u001c\"!\u0017/\u0011\u0005]ifA\u00020\u0001\u0003\u0003\u0011qL\u0001\u0007CCNL7\r\u0015:pG\u0016\u001c8oE\u0002^\u0013\u0001\u0004\"!\u00192\u000e\u0003\tI!a\u0019\u0002\u0003\u000fA\u0013xnY3tg\")A$\u0018C\u0001KR\tA\fC\u0003h;\u001a\u0005\u0001#A\u0003ti\u0006\u0014H\u000fC\u0003\u001d3\u0012\u0005\u0011\u000eF\u0001Y\u0011\u0015Y\u0017\f\"\u0001\u0011\u0003\u001d!Wm\u001d;s_fDQ!\\-\u0005\u00029\f\u0011\"\u001a=jiZ\u000bG.^3\u0015\u0003=\u0004\"A\u00039\n\u0005E4!aA%oi\")q-\u0017C\u0001!!QA/\u0017I\u0001\u0012\u000f\u0007K\u0011B;\u0002\u0007a$C'F\u0001w!\u0011Qq/_?\n\u0005a4!A\u0002+va2,'\u0007E\u0002\u000b\u0005j\u00042AC>p\u0013\tahA\u0001\u0004PaRLwN\u001c\t\u0004\u0015\t\u000b\u0002\u0002C@Z\u0011\u0003\u0005\u000b\u0015\u0002<\u0002\ta$C\u0007\t\u0005\u000b\u0003\u0007I\u0006R1A\u0005\u0012\u0005\u0015\u0011\u0001D4fi\u0016C\u0018\u000e\u001e,bYV,W#A=\t\u0013\u0005%\u0011\f#A!B\u0013I\u0018!D4fi\u0016C\u0018\u000e\u001e,bYV,\u0007\u0005\u0003\u0006\u0002\u000eeC)\u0019!C\t\u0003\u001f\t\u0011\u0002Z3tiJ|\u00170\u001a:\u0016\u0003uD\u0011\"a\u0005Z\u0011\u0003\u0005\u000b\u0015B?\u0002\u0015\u0011,7\u000f\u001e:ps\u0016\u0014\b\u0005\u0003\u0005\u0002\u0018e\u0003k\u0011CA\r\u0003=\u0011XO\\!oI\u0016C\u0018\u000e\u001e,bYV,G#\u0001>\t\u0011\u0005u\u0011\f)C\t\u0003?\t\u0001C];o\u0013:$XM\u001d:vaRL'\r\\3\u0016\t\u0005\u0005\u0012\u0011\u0006\u000b\u0005\u0003G\ty\u0003\u0006\u0003\u0002&\u0005-\u0002\u0003\u0002\u0006|\u0003O\u00012!RA\u0015\t\u00199\u00151\u0004b\u0001\u0011\"A\u0011QFA\u000e\t\u0003\u00071&A\u0006eKN$(o\\=J[Bd\u0007\"CA\u0019\u00037!\t\u0019AA\u001a\u0003\u0019\t7\r^5p]B!!\u0002LA\u0014\u0011)\t9$\u0016B\u0001B\u0003%\u0011\u0011H\u0001\u0002CB\u0019\u0011-a\u000f\n\u0007\u0005u\"A\u0001\bQe>\u001cWm]:Ck&dG-\u001a:\t\u0015\u0005\u0005SK!A!\u0002\u0013\tI$A\u0001c\u0011)\t)%\u0016B\u0001B\u0003%\u0011qI\u0001\u0003S>\u00042!YA%\u0013\r\tYE\u0001\u0002\n!J|7-Z:t\u0013>C!\"a\u0014V\u0005\u0003\u0005\u000b\u0011BA)\u0003U)g/\u00197vCR,7+Z2p]\u0012\u0004&o\\2fgN\u0004RACA*_NJ1!!\u0016\u0007\u0005%1UO\\2uS>t\u0017\u0007\u0003\u0004\u001d+\u0012\u0005\u0011\u0011\f\u000b\n)\u0006m\u0013QLA0\u0003CB\u0001\"a\u000e\u0002X\u0001\u0007\u0011\u0011\b\u0005\t\u0003\u0003\n9\u00061\u0001\u0002:!A\u0011QIA,\u0001\u0004\t9\u0005\u0003\u0005\u0002P\u0005]\u0003\u0019AA)\u0011!\t9\"\u0016Q\u0005R\u0005e\u0001BCA\u001c#\n\u0005\t\u0015!\u0003\u0002:!Q\u0011\u0011I)\u0003\u0002\u0003\u0006I!!\u000f\t\u0015\u0005\u0015\u0013K!A!\u0002\u0013\t9\u0005\u0003\u0004\u001d#\u0012\u0005\u0011Q\u000e\u000b\t\u0003_\n\t(a\u001d\u0002vA\u0011q#\u0015\u0005\t\u0003o\tY\u00071\u0001\u0002:!A\u0011\u0011IA6\u0001\u0004\tI\u0004\u0003\u0005\u0002F\u0005-\u0004\u0019AA$\r\u001d\tI\b\u0001\u0001\u0003\u0003w\u0012\u0011b\u0014:Qe>\u001cWm]:\u0014\u0007\u0005]D\u000bC\u0006\u00028\u0005]$\u0011!Q\u0001\n\u0005e\u0002bCA!\u0003o\u0012\t\u0011)A\u0005\u0003sA1\"!\u0012\u0002x\t\u0005\t\u0015!\u0003\u0002H!9A$a\u001e\u0005\u0002\u0005\u0015E\u0003CAD\u0003\u0013\u000bY)!$\u0011\u0007]\t9\b\u0003\u0005\u00028\u0005\r\u0005\u0019AA\u001d\u0011!\t\t%a!A\u0002\u0005e\u0002\u0002CA#\u0003\u0007\u0003\r!a\u0012\u0007\u000f\u0005E\u0005\u0001\u0001\u0002\u0002\u0014\ny\u0001K]8dKN\u001c8+Z9vK:\u001cWmE\u0002\u0002\u0010RC1\"a\u000e\u0002\u0010\n\u0005\t\u0015!\u0003\u0002:!Y\u0011\u0011IAH\u0005\u0003\u0005\u000b\u0011BA\u001d\u0011-\t)%a$\u0003\u0002\u0003\u0006I!a\u0012\t\u000fq\ty\t\"\u0001\u0002\u001eRA\u0011qTAQ\u0003G\u000b)\u000bE\u0002\u0018\u0003\u001fC\u0001\"a\u000e\u0002\u001c\u0002\u0007\u0011\u0011\b\u0005\t\u0003\u0003\nY\n1\u0001\u0002:!A\u0011QIAN\u0001\u0004\t9EB\u0004\u0002*\u0002\u0001!!a+\u0003\u001dAK\u0007/\u001a3Qe>\u001cWm]:fgN\u0019\u0011q\u0015-\t\u0017\u0005]\u0012q\u0015B\u0001B\u0003%\u0011\u0011\b\u0005\f\u0003\u0003\n9K!A!\u0002\u0013\tI\u0004C\u0006\u00024\u0006\u001d&\u0011!Q\u0001\n\u0005\u001d\u0013!\u00033fM\u0006,H\u000e^%P\u0011)\t9,a*\u0003\u0002\u0003\u0006IaM\u0001\bi>,%O]8s\u0011\u001da\u0012q\u0015C\u0001\u0003w#\"\"!0\u0002@\u0006\u0005\u00171YAc!\r9\u0012q\u0015\u0005\t\u0003o\tI\f1\u0001\u0002:!A\u0011\u0011IA]\u0001\u0004\tI\u0004\u0003\u0005\u00024\u0006e\u0006\u0019AA$\u0011\u001d\t9,!/A\u0002MB\u0011\"a\u0006\u0002(\u0002&\t&!\u0007\u0007\u0011\u0005-\u0007!!\u0001\u0003\u0003\u001b\u0014!\u0002U5qKRC'/Z1e'\r\tI-\t\u0005\u000b\u0003#\fIM!A!\u0002\u0013\u0019\u0014AB5t'&t7\u000eC\u0006\u0002V\u0006%'\u0011!Q\u0001\n\u0005]\u0017a\u00027bE\u0016dgI\u001c\t\u0005\u0015\t\u000bI\u000e\u0005\u0003\u0002\\\u0006\u0005hb\u0001\u0006\u0002^&\u0019\u0011q\u001c\u0004\u0002\rA\u0013X\rZ3g\u0013\u0011\t\u0019/!:\u0003\rM#(/\u001b8h\u0015\r\tyN\u0002\u0005\b9\u0005%G\u0011AAu)\u0019\tY/!<\u0002pB\u0019q#!3\t\u000f\u0005E\u0017q\u001da\u0001g!A\u0011Q[At\u0001\u0004\t9\u000eC\u0004\u0002t\u0006%g\u0011\u0001\t\u0002\u0007I,h\u000eC\u0005\u0002x\u0006%G\u0011\u0001\u0002\u0002z\u00069!/\u001e8m_>\u0004H#B\t\u0002|\n5\u0001\u0002CA\u007f\u0003k\u0004\r!a@\u0002\u0007M\u00148\r\u0005\u0003\u0003\u0002\t\u001dabA1\u0003\u0004%\u0019!Q\u0001\u0002\u0002\u001fA\u0014xnY3tg&sG/\u001a:oC2LAA!\u0003\u0003\f\tY\u0011J\u001c9viN#(/Z1n\u0015\r\u0011)A\u0001\u0005\t\u0005\u001f\t)\u00101\u0001\u0003\u0012\u0005\u0019Am\u001d;\u0011\t\t\u0005!1C\u0005\u0005\u0005+\u0011YA\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW\u000e\u0003\u0005\u0003\u001a\u0005%G\u0011\u0002B\u000e\u0003%Iw\u000eS1oI2,'\u000fF\u0002\u0012\u0005;A\u0001Ba\b\u0003\u0018\u0001\u0007!\u0011E\u0001\u0002KB!!\u0011\u0001B\u0012\u0013\u0011\u0011)Ca\u0003\u0003\u0017%{U\t_2faRLwN\u001c\u0004\b\u0005S\u0001\u0001A\u0001B\u0016\u0005)\u0001\u0016\u000e]3T_V\u00148-Z\n\u0005\u0005O\tY\u000fC\u0006\u00030\t\u001d\"\u0011!Q\u0001\n\tE\u0012!D2veJ,g\u000e^*pkJ\u001cW\r\u0005\u0004\u0003\u0002\tM\"qG\u0005\u0005\u0005k\u0011YAA\u0004Ts:\u001cg+\u0019:\u0011\t)Y\u0018q \u0005\f\u0005w\u00119C!A!\u0002\u0013\u0011i$\u0001\u0003qSB,\u0007\u0003\u0002B \u0005\u0007j!A!\u0011\u000b\u0007\u0005\u0015S%\u0003\u0003\u0003F\t\u0005#!\u0005)ja\u0016$w*\u001e;qkR\u001cFO]3b[\"Y!\u0011\nB\u0014\u0005\u0003%\u000b\u0011\u0002B&\u0003\u0015a\u0017MY3m!\u0011QA&!7\t\u000fq\u00119\u0003\"\u0001\u0003PQA!\u0011\u000bB*\u0005+\u00129\u0006E\u0002\u0018\u0005OA\u0001Ba\f\u0003N\u0001\u0007!\u0011\u0007\u0005\t\u0005w\u0011i\u00051\u0001\u0003>!I!\u0011\nB'\t\u0003\u0007!1\n\u0005\b\u0003g\u00149\u0003\"\u0012\u0011\r\u001d\u0011i\u0006\u0001\u0001\u0003\u0005?\u0012\u0001\u0002U5qKNKgn[\n\u0005\u00057\nY\u000fC\u0006\u0003<\tm#\u0011!Q\u0001\n\t\r\u0004\u0003\u0002B \u0005KJAAa\u001a\u0003B\t\u0001\u0002+\u001b9fI&s\u0007/\u001e;TiJ,\u0017-\u001c\u0005\f\u0005W\u0012YF!A!\u0002\u0013\u0011i'A\u0006dkJ\u0014XM\u001c;TS:\\\u0007C\u0002B\u0001\u0005g\u0011y\u0007\u0005\u0003\u000bw\nE\u0001b\u0003B%\u00057\u0012\t\u0011*A\u0005\u0005\u0017Bq\u0001\bB.\t\u0003\u0011)\b\u0006\u0005\u0003x\te$1\u0010B?!\r9\"1\f\u0005\t\u0005w\u0011\u0019\b1\u0001\u0003d!A!1\u000eB:\u0001\u0004\u0011i\u0007C\u0005\u0003J\tMD\u00111\u0001\u0003L!9\u00111\u001fB.\t\u000b\u0002ba\u0002BB\u0001\u0001\u0011!Q\u0011\u0002\r\tVlW.\u001f)s_\u000e,7o]\n\u0005\u0005\u0003K\u0001\rC\u0006\u00022\t\u0005%\u0011!S\u0001\n\t%\u0005c\u0001\u0006-_\"9AD!!\u0005\u0002\t5E\u0003\u0002BH\u0005#\u00032a\u0006BA\u0011%\t\tDa#\u0005\u0002\u0004\u0011I\tC\u0005\u0003\u0016\n\u0005\u0005\u0015!\u0003\u0003\u0018\u0006AQ\r_5u\u0007>$W\rE\u0002\u000b\u0005>Da!\u001cBA\t\u0003r\u0007BB6\u0003\u0002\u0012\u0005\u0003CB\u0004\u0003 \u0002\u0001!A!)\u0003\u001bMKW\u000e\u001d7f!J|7-Z:t'\u0011\u0011i*\u00031\t\u0017\t\u0015&Q\u0014B\u0001B\u0003%!qU\u0001\u0002aB!!\u0011\u0001BU\u0013\u0011\u0011YKa\u0003\u0003\u0011)\u0003&o\\2fgND!Ba,\u0003\u001e\n\u0005\t\u0015!\u0003\"\u0003-Ig\u000e];u)\"\u0014X-\u00193\t\u0017\tM&Q\u0014B\u0001B\u0003%!QW\u0001\u000e_V$\b/\u001e;UQJ,\u0017\rZ:\u0011\u000b\t]&QX\u0011\u000f\u0007)\u0011I,C\u0002\u0003<\u001a\tq\u0001]1dW\u0006<W-\u0003\u0003\u0003@\n\u0005'\u0001\u0002'jgRT1Aa/\u0007\u0011\u001da\"Q\u0014C\u0001\u0005\u000b$\u0002Ba2\u0003J\n-'Q\u001a\t\u0004/\tu\u0005\u0002\u0003BS\u0005\u0007\u0004\rAa*\t\u000f\t=&1\u0019a\u0001C!A!1\u0017Bb\u0001\u0004\u0011)\f\u0003\u0004n\u0005;#\tE\u001c\u0005\u0007W\nuE\u0011\t\t\u0007\u000f\tU\u0007A\u0001\u0002\u0003X\niA\u000b\u001b:fC\u0012\u0004&o\\2fgN\u001cBAa5\nA\"Q!1\u001cBj\u0005\u0003\u0005\u000b\u0011B\u0011\u0002\rQD'/Z1e\u0011-\u0011yNa5\u0003\u0002\u0003\u0006IA!9\u0002\u000fM,8mY3tgB)!\u0011\u0001B\u001ag!9ADa5\u0005\u0002\t\u0015HC\u0002Bt\u0005S\u0014Y\u000fE\u0002\u0018\u0005'DqAa7\u0003d\u0002\u0007\u0011\u0005\u0003\u0005\u0003`\n\r\b\u0019\u0001Bq\u0011\u0019i'1\u001bC!]\"11Na5\u0005BAq1!\u0019Bz\u0013\r\u0011)PA\u0001\b!J|7-Z:t\u0001")
public interface ProcessImpl {
    public ProcessImpl$Spawn$ Spawn();

    public ProcessImpl$Future$ Future();

    public class PipeSink
    extends PipeThread {
        private final PipedInputStream pipe;
        private final SyncVar<Option<OutputStream>> currentSink;

        @Override
        public final void run() {
            while (true) {
                Option<OutputStream> option;
                if ((option = this.currentSink.get()) instanceof Some) {
                    Some some = (Some)option;
                    this.runloop(this.pipe, (OutputStream)some.x());
                    continue;
                }
                if (None$.MODULE$.equals(option)) {
                    this.currentSink.unset();
                    return;
                }
                throw new MatchError(option);
                finally {
                    this.currentSink.unset();
                    continue;
                }
                break;
            }
        }

        public /* synthetic */ Process$ scala$sys$process$ProcessImpl$PipeSink$$$outer() {
            return this.$outer;
        }

        public PipeSink(Process$ $outer, PipedInputStream pipe, SyncVar<Option<OutputStream>> currentSink, Function0<String> label) {
            this.pipe = pipe;
            this.currentSink = currentSink;
            super($outer, true, label);
        }
    }

    public class OrProcess
    extends SequentialProcess {
        public /* synthetic */ Process$ scala$sys$process$ProcessImpl$OrProcess$$$outer() {
            return this.$outer;
        }

        public OrProcess(Process$ $outer, ProcessBuilder a, ProcessBuilder b, ProcessIO io) {
            super($outer, a, b, io, (Function1<Object, Object>)((Object)new Serializable($outer){
                public static final long serialVersionUID = 0L;

                public final boolean apply(int x$2) {
                    return this.apply$mcZI$sp(x$2);
                }

                public boolean apply$mcZI$sp(int x$2) {
                    return x$2 != 0;
                }
            }));
        }
    }

    public class AndProcess
    extends SequentialProcess {
        public /* synthetic */ Process$ scala$sys$process$ProcessImpl$AndProcess$$$outer() {
            return this.$outer;
        }

        public AndProcess(Process$ $outer, ProcessBuilder a, ProcessBuilder b, ProcessIO io) {
            super($outer, a, b, io, (Function1<Object, Object>)((Object)new Serializable($outer){
                public static final long serialVersionUID = 0L;

                public final boolean apply(int x$1) {
                    return this.apply$mcZI$sp(x$1);
                }

                public boolean apply$mcZI$sp(int x$1) {
                    return x$1 == 0;
                }
            }));
        }
    }

    public abstract class PipeThread
    extends Thread {
        private final boolean isSink;
        private final Function0<String> labelFn;
        public final /* synthetic */ Process$ $outer;

        @Override
        public abstract void run();

        /*
         * Unable to fully structure code
         * Could not resolve type clashes
         */
        public void runloop(InputStream src, OutputStream dst) {
            block8: {
                block7: {
                    try {
                        BasicIO$.MODULE$.transferFully(src, dst);
                    }
                    catch (Throwable var8_3) {
                        var4_4 = new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ PipeThread $outer;

                            public final void apply(IOException e) {
                                this.$outer.scala$sys$process$ProcessImpl$PipeThread$$ioHandler(e);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        };
                        var3_5 = processInternal$.MODULE$;
                        catchExpr$2 = new Serializable((Function1)var4_4){
                            public static final long serialVersionUID = 0L;
                            private final Function1 handler$2;

                            public final <A1 extends Throwable, B1> B1 applyOrElse(A1 x2, Function1<A1, B1> function1) {
                                Object object;
                                if (x2 instanceof IOException) {
                                    IOException iOException = (IOException)x2;
                                    object = this.handler$2.apply(iOException);
                                } else {
                                    object = function1.apply(x2);
                                }
                                return object;
                            }

                            public final boolean isDefinedAt(Throwable x2) {
                                boolean bl = x2 instanceof IOException;
                                return bl;
                            }
                            {
                                this.handler$2 = handler$2;
                            }
                        };
                        var5_7 = var8_3;
                        var6_8 = var5_7 instanceof IOException != false;
                        if (!var6_8) break block7;
                        catchExpr$2.apply(var8_3);
                    }
                    BasicIO$.MODULE$.close(this.isSink != false ? dst : src);
                    return;
                }
                try {
                    var9_9 = var8_3;
                    break block8;
                }
                catch (Throwable var9_10) {
                    v0 = BasicIO$.MODULE$;
                    if (this.isSink) ** GOTO lbl-1000
                }
                ** GOTO lbl-1000
            }
            v0 = BasicIO$.MODULE$;
            if (this.isSink) lbl-1000:
            // 2 sources

            {
                v1 /* !! */  = dst;
            } else lbl-1000:
            // 2 sources

            {
                v1 /* !! */  = src;
            }
            v0.close(v1 /* !! */ );
            throw var9_9;
        }

        public void scala$sys$process$ProcessImpl$PipeThread$$ioHandler(IOException e) {
            Predef$.MODULE$.println(new StringBuilder().append((Object)"I/O error ").append((Object)e.getMessage()).append((Object)" for process: ").append((Object)this.labelFn.apply()).toString());
            e.printStackTrace();
        }

        public /* synthetic */ Process$ scala$sys$process$ProcessImpl$PipeThread$$$outer() {
            return this.$outer;
        }

        public PipeThread(Process$ $outer, boolean isSink, Function0<String> labelFn) {
            this.isSink = isSink;
            this.labelFn = labelFn;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public class PipeSource
    extends PipeThread {
        private final SyncVar<Option<InputStream>> currentSource;
        private final PipedOutputStream pipe;

        @Override
        public final void run() {
            while (true) {
                Option<InputStream> option;
                if ((option = this.currentSource.get()) instanceof Some) {
                    Some some = (Some)option;
                    this.runloop((InputStream)some.x(), this.pipe);
                    continue;
                }
                if (None$.MODULE$.equals(option)) {
                    this.currentSource.unset();
                    BasicIO$.MODULE$.close(this.pipe);
                    return;
                }
                throw new MatchError(option);
                finally {
                    this.currentSource.unset();
                    continue;
                }
                break;
            }
        }

        public /* synthetic */ Process$ scala$sys$process$ProcessImpl$PipeSource$$$outer() {
            return this.$outer;
        }

        public PipeSource(Process$ $outer, SyncVar<Option<InputStream>> currentSource, PipedOutputStream pipe, Function0<String> label) {
            this.currentSource = currentSource;
            this.pipe = pipe;
            super($outer, false, label);
        }
    }

    public static abstract class BasicProcess
    implements Process {
        public final /* synthetic */ Process$ $outer;

        public abstract void start();

        public /* synthetic */ Process$ scala$sys$process$ProcessImpl$BasicProcess$$$outer() {
            return this.$outer;
        }

        public BasicProcess(Process$ $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static class DummyProcess
    implements Process {
        private final Function0<Object> exitCode;
        public final /* synthetic */ Process$ $outer;

        @Override
        public int exitValue() {
            return this.exitCode.apply$mcI$sp();
        }

        @Override
        public void destroy() {
        }

        public /* synthetic */ Process$ scala$sys$process$ProcessImpl$DummyProcess$$$outer() {
            return this.$outer;
        }

        public DummyProcess(Process$ $outer, Function0<Object> action) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this.exitCode = $outer.Future().apply(action);
        }
    }

    public class SimpleProcess
    implements Process {
        private final java.lang.Process p;
        private final Thread inputThread;
        private final List<Thread> outputThreads;
        public final /* synthetic */ Process$ $outer;

        @Override
        public int exitValue() {
            this.p.waitFor();
            List these1 = this.outputThreads;
            while (true) {
                if (these1.isEmpty()) {
                    return this.p.exitValue();
                }
                these1.head().join();
                these1 = (List)these1.tail();
            }
            finally {
                this.inputThread.interrupt();
            }
        }

        @Override
        public void destroy() {
            try {
                List list2;
                List these1 = list2 = this.outputThreads;
                while (true) {
                    Thread thread;
                    if (these1.isEmpty()) {
                        this.p.destroy();
                        return;
                    }
                    Thread thread2 = thread = these1.head();
                    thread2.interrupt();
                    these1 = (List)these1.tail();
                }
            }
            finally {
                this.inputThread.interrupt();
            }
        }

        public /* synthetic */ Process$ scala$sys$process$ProcessImpl$SimpleProcess$$$outer() {
            return this.$outer;
        }

        public SimpleProcess(Process$ $outer, java.lang.Process p, Thread inputThread, List<Thread> outputThreads) {
            this.p = p;
            this.inputThread = inputThread;
            this.outputThreads = outputThreads;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public final class ThreadProcess
    implements Process {
        private final Thread thread;
        private final SyncVar<Object> success;

        @Override
        public int exitValue() {
            this.thread.join();
            return BoxesRunTime.unboxToBoolean(this.success.get()) ? 0 : 1;
        }

        @Override
        public void destroy() {
            this.thread.interrupt();
        }

        public ThreadProcess(Process$ $outer, Thread thread, SyncVar<Object> success) {
            this.thread = thread;
            this.success = success;
        }
    }

    public class PipedProcesses
    extends CompoundProcess {
        public final ProcessBuilder scala$sys$process$ProcessImpl$PipedProcesses$$a;
        public final ProcessBuilder scala$sys$process$ProcessImpl$PipedProcesses$$b;
        private final ProcessIO defaultIO;
        private final boolean toError;

        /*
         * WARNING - void declaration
         */
        @Override
        public Option<Object> runAndExitValue() {
            Option<Object> option;
            SyncVar<Option<InputStream>> currentSource = new SyncVar<Option<InputStream>>();
            PipedOutputStream pipeOut = new PipedOutputStream();
            PipeSource source = new PipeSource(this.scala$sys$process$ProcessImpl$PipedProcesses$$$outer(), currentSource, pipeOut, (Function0<String>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ PipedProcesses $outer;

                public final String apply() {
                    return this.$outer.scala$sys$process$ProcessImpl$PipedProcesses$$a.toString();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
            source.start();
            PipedInputStream pipeIn = new PipedInputStream(pipeOut);
            SyncVar<Option<OutputStream>> currentSink = new SyncVar<Option<OutputStream>>();
            PipeSink sink = new PipeSink(this.scala$sys$process$ProcessImpl$PipedProcesses$$$outer(), pipeIn, currentSink, (Function0<String>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ PipedProcesses $outer;

                public final String apply() {
                    return this.$outer.scala$sys$process$ProcessImpl$PipedProcesses$$b.toString();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
            sink.start();
            ProcessIO firstIO = this.toError ? this.defaultIO.withError((Function1<InputStream, BoxedUnit>)((Object)new Serializable(this, currentSource){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ PipedProcesses $outer;
                private final SyncVar currentSource$1;

                public final void apply(InputStream fromOutput) {
                    this.$outer.scala$sys$process$ProcessImpl$PipedProcesses$$handleOutOrError$1(fromOutput, this.currentSource$1);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.currentSource$1 = currentSource$1;
                }
            })) : this.defaultIO.withOutput((Function1<InputStream, BoxedUnit>)((Object)new Serializable(this, currentSource){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ PipedProcesses $outer;
                private final SyncVar currentSource$1;

                public final void apply(InputStream fromOutput) {
                    this.$outer.scala$sys$process$ProcessImpl$PipedProcesses$$handleOutOrError$1(fromOutput, this.currentSource$1);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.currentSource$1 = currentSource$1;
                }
            }));
            ProcessIO secondIO = this.defaultIO.withInput((Function1<OutputStream, BoxedUnit>)((Object)new Serializable(this, currentSink){
                public static final long serialVersionUID = 0L;
                private final SyncVar currentSink$1;

                public final void apply(OutputStream toInput) {
                    this.currentSink$1.put(new Some<OutputStream>(toInput));
                }
                {
                    this.currentSink$1 = currentSink$1;
                }
            }));
            Process second = this.scala$sys$process$ProcessImpl$PipedProcesses$$b.run(secondIO);
            Process first = this.scala$sys$process$ProcessImpl$PipedProcesses$$a.run(firstIO);
            try {
                option = this.runInterruptible(new Serializable(this, currentSource, currentSink, second, first){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ PipedProcesses $outer;
                    private final SyncVar currentSource$1;
                    private final SyncVar currentSink$1;
                    private final Process second$2;
                    private final Process first$2;

                    public final int apply() {
                        return this.apply$mcI$sp();
                    }

                    public int apply$mcI$sp() {
                        int exit1 = this.first$2.exitValue();
                        this.currentSource$1.put(None$.MODULE$);
                        this.currentSink$1.put(None$.MODULE$);
                        int exit2 = this.second$2.exitValue();
                        return this.$outer.scala$sys$process$ProcessImpl$PipedProcesses$$b.hasExitValue() ? exit2 : exit1;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.currentSource$1 = currentSource$1;
                        this.currentSink$1 = currentSink$1;
                        this.second$2 = second$2;
                        this.first$2 = first$2;
                    }
                }, (Function0<BoxedUnit>)((Object)new Serializable(this, second, first){
                    public static final long serialVersionUID = 0L;
                    public final Process second$2;
                    public final Process first$2;

                    public final void apply() {
                        this.first$2.destroy();
                        this.second$2.destroy();
                    }

                    public void apply$mcV$sp() {
                        this.first$2.destroy();
                        this.second$2.destroy();
                    }
                    {
                        this.second$2 = second$2;
                        this.first$2 = first$2;
                    }
                }));
                BasicIO$.MODULE$.close(pipeIn);
                BasicIO$.MODULE$.close(pipeOut);
            }
            catch (Throwable throwable) {
                void var2_2;
                void var4_4;
                BasicIO$.MODULE$.close((Closeable)var4_4);
                BasicIO$.MODULE$.close((Closeable)var2_2);
                throw throwable;
            }
            return option;
        }

        public /* synthetic */ Process$ scala$sys$process$ProcessImpl$PipedProcesses$$$outer() {
            return this.$outer;
        }

        public final void scala$sys$process$ProcessImpl$PipedProcesses$$handleOutOrError$1(InputStream fromOutput, SyncVar currentSource$1) {
            currentSource$1.put(new Some<InputStream>(fromOutput));
        }

        public PipedProcesses(Process$ $outer, ProcessBuilder a, ProcessBuilder b, ProcessIO defaultIO, boolean toError) {
            this.scala$sys$process$ProcessImpl$PipedProcesses$$a = a;
            this.scala$sys$process$ProcessImpl$PipedProcesses$$b = b;
            this.defaultIO = defaultIO;
            this.toError = toError;
            super($outer);
        }
    }

    public class ProcessSequence
    extends SequentialProcess {
        public /* synthetic */ Process$ scala$sys$process$ProcessImpl$ProcessSequence$$$outer() {
            return this.$outer;
        }

        public ProcessSequence(Process$ $outer, ProcessBuilder a, ProcessBuilder b, ProcessIO io) {
            super($outer, a, b, io, (Function1<Object, Object>)((Object)new Serializable($outer){
                public static final long serialVersionUID = 0L;

                public final boolean apply(int x$3) {
                    return true;
                }

                public boolean apply$mcZI$sp(int x$3) {
                    return true;
                }
            }));
        }
    }

    public abstract class CompoundProcess
    extends BasicProcess {
        private Tuple2<Function0<Option<Object>>, Function0<BoxedUnit>> x$4;
        private Function0<Option<Object>> getExitValue;
        private Function0<BoxedUnit> destroyer;
        private volatile byte bitmap$0;

        /*
         * WARNING - void declaration
         */
        private Tuple2 x$4$lzycompute() {
            CompoundProcess compoundProcess = this;
            synchronized (compoundProcess) {
                if ((byte)(this.bitmap$0 & 1) == 0) {
                    void var10_5;
                    SyncVar<None$> code = new SyncVar<None$>();
                    code.set(None$.MODULE$);
                    Serializable serializable = new Serializable(this, code){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ CompoundProcess $outer;
                        public final SyncVar code$1;

                        public final void apply() {
                            this.code$1.set(this.$outer.runAndExitValue());
                        }

                        public void apply$mcV$sp() {
                            this.code$1.set(this.$outer.runAndExitValue());
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.code$1 = code$1;
                        }
                    };
                    ProcessImpl$Spawn$ processImpl$Spawn$ = this.scala$sys$process$ProcessImpl$CompoundProcess$$$outer().Spawn();
                    Thread thread1 = new Thread(processImpl$Spawn$, (Function0)((Object)serializable)){
                        private final Function0 f$1;

                        public void run() {
                            this.f$1.apply$mcV$sp();
                        }
                        {
                            this.f$1 = f$1;
                        }
                    };
                    thread1.setDaemon(false);
                    thread1.start();
                    Serializable serializable2 = new Serializable(this, code, (Thread)var10_5){
                        public static final long serialVersionUID = 0L;
                        private final SyncVar code$1;
                        private final Thread thread$1;

                        public final Option<Object> apply() {
                            this.thread$1.join();
                            return (Option)this.code$1.get();
                        }
                        {
                            this.code$1 = code$1;
                            this.thread$1 = thread$1;
                        }
                    };
                    ProcessImpl$Future$ processImpl$Future$ = this.scala$sys$process$ProcessImpl$CompoundProcess$$$outer().Future();
                    SyncVar result1 = new SyncVar();
                    Serializable serializable3 = new Serializable(processImpl$Future$, (Function0)((Object)serializable2), result1){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ ProcessImpl$Future$ $outer;
                        public final Function0 f$2;
                        public final SyncVar result$1;

                        public final void apply() {
                            this.$outer.scala$sys$process$ProcessImpl$Future$$run$1(this.f$2, this.result$1);
                        }

                        public void apply$mcV$sp() {
                            this.$outer.scala$sys$process$ProcessImpl$Future$$run$1(this.f$2, this.result$1);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.f$2 = f$2;
                            this.result$1 = result$1;
                        }
                    };
                    ProcessImpl$Spawn$ processImpl$Spawn$2 = processImpl$Future$.$outer.Spawn();
                    Thread thread11 = new /* invalid duplicate definition of identical inner class */;
                    thread11.setDaemon(false);
                    thread11.start();
                    Tuple2<Future$$anonfun$apply$4, CompoundProcess$$anonfun$2> tuple2 = new Tuple2<Future$$anonfun$apply$4, CompoundProcess$$anonfun$2>(new Serializable(processImpl$Future$, result1){
                        public static final long serialVersionUID = 0L;
                        private final SyncVar result$1;

                        public final T apply() {
                            Either either2 = (Either)this.result$1.get();
                            if (either2 instanceof Right) {
                                Right right = (Right)either2;
                                return (T)right.b();
                            }
                            if (either2 instanceof Left) {
                                Left left = (Left)either2;
                                throw (Throwable)left.a();
                            }
                            throw new MatchError(either2);
                        }
                        {
                            this.result$1 = result$1;
                        }
                    }, new Serializable(this, (Thread)var10_5){
                        public static final long serialVersionUID = 0L;
                        public final Thread thread$1;

                        public final void apply() {
                            this.thread$1.interrupt();
                        }

                        public void apply$mcV$sp() {
                            this.thread$1.interrupt();
                        }
                        {
                            this.thread$1 = thread$1;
                        }
                    });
                    Tuple2<Future$$anonfun$apply$4, CompoundProcess$$anonfun$2> tuple22 = new Tuple2<Future$$anonfun$apply$4, CompoundProcess$$anonfun$2>(tuple2._1(), tuple2._2());
                    this.x$4 = tuple22;
                    this.bitmap$0 = (byte)(this.bitmap$0 | 1);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.x$4;
            }
        }

        private Function0 getExitValue$lzycompute() {
            CompoundProcess compoundProcess = this;
            synchronized (compoundProcess) {
                if ((byte)(this.bitmap$0 & 2) == 0) {
                    this.getExitValue = (Function0)this.x$4()._1();
                    this.bitmap$0 = (byte)(this.bitmap$0 | 2);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.getExitValue;
            }
        }

        private Function0 destroyer$lzycompute() {
            CompoundProcess compoundProcess = this;
            synchronized (compoundProcess) {
                if ((byte)(this.bitmap$0 & 4) == 0) {
                    this.destroyer = (Function0)this.x$4()._2();
                    this.bitmap$0 = (byte)(this.bitmap$0 | 4);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.destroyer;
            }
        }

        @Override
        public void destroy() {
            this.destroyer().apply$mcV$sp();
        }

        @Override
        public int exitValue() {
            Option<Object> option = this.getExitValue().apply();
            if (option.isEmpty()) {
                package$ package$2 = package$.MODULE$;
                throw new RuntimeException("No exit code: process destroyed.");
            }
            return BoxesRunTime.unboxToInt(option.get());
        }

        @Override
        public void start() {
            this.getExitValue();
        }

        private /* synthetic */ Tuple2 x$4() {
            return (byte)(this.bitmap$0 & 1) == 0 ? this.x$4$lzycompute() : this.x$4;
        }

        public Function0<Option<Object>> getExitValue() {
            return (byte)(this.bitmap$0 & 2) == 0 ? this.getExitValue$lzycompute() : this.getExitValue;
        }

        public Function0<BoxedUnit> destroyer() {
            return (byte)(this.bitmap$0 & 4) == 0 ? this.destroyer$lzycompute() : this.destroyer;
        }

        public abstract Option<Object> runAndExitValue();

        public <T> Option<T> runInterruptible(Function0<T> action, Function0<BoxedUnit> destroyImpl) {
            Throwable throwable2;
            block2: {
                Option option;
                try {
                    option = new Some<T>(action.apply());
                }
                catch (Throwable throwable2) {
                    Serializable serializable = new Serializable(this, destroyImpl){
                        public static final long serialVersionUID = 0L;
                        private final Function0 destroyImpl$1;

                        public final None$ apply() {
                            this.destroyImpl$1.apply$mcV$sp();
                            return None$.MODULE$;
                        }
                        {
                            this.destroyImpl$1 = destroyImpl$1;
                        }
                    };
                    processInternal$ processInternal$2 = processInternal$.MODULE$;
                    Serializable catchExpr$1 = new Serializable((Function0)((Object)serializable)){
                        public static final long serialVersionUID = 0L;
                        private final Function0 handler$1;

                        public final <A1 extends Throwable, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                            Object object = x1 instanceof InterruptedException ? this.handler$1.apply() : function1.apply(x1);
                            return (B1)object;
                        }

                        public final boolean isDefinedAt(Throwable x1) {
                            boolean bl = x1 instanceof InterruptedException;
                            return bl;
                        }
                        {
                            this.handler$1 = handler$1;
                        }
                    };
                    Throwable throwable3 = throwable2;
                    boolean bl = throwable3 instanceof InterruptedException;
                    if (!bl) break block2;
                    option = (Option)catchExpr$1.apply(throwable2);
                }
                return option;
            }
            throw throwable2;
        }

        public /* synthetic */ Process$ scala$sys$process$ProcessImpl$CompoundProcess$$$outer() {
            return this.$outer;
        }

        public CompoundProcess(Process$ $outer) {
            super($outer);
        }
    }

    public class SequentialProcess
    extends CompoundProcess {
        private final ProcessBuilder a;
        public final ProcessBuilder scala$sys$process$ProcessImpl$SequentialProcess$$b;
        public final ProcessIO scala$sys$process$ProcessImpl$SequentialProcess$$io;
        public final Function1<Object, Object> scala$sys$process$ProcessImpl$SequentialProcess$$evaluateSecondProcess;

        @Override
        public Option<Object> runAndExitValue() {
            Option option;
            Process first = this.a.run(this.scala$sys$process$ProcessImpl$SequentialProcess$$io);
            Serializable serializable = new Serializable(this){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ SequentialProcess $outer;

                public final Option<Object> apply(int codeA) {
                    Option option;
                    if (this.$outer.scala$sys$process$ProcessImpl$SequentialProcess$$evaluateSecondProcess.apply$mcZI$sp(codeA)) {
                        Process second = this.$outer.scala$sys$process$ProcessImpl$SequentialProcess$$b.run(this.$outer.scala$sys$process$ProcessImpl$SequentialProcess$$io);
                        option = this.$outer.runInterruptible(new Serializable(this, second){
                            public static final long serialVersionUID = 0L;
                            public final Process second$1;

                            public final int apply() {
                                return this.second$1.exitValue();
                            }

                            public int apply$mcI$sp() {
                                return this.second$1.exitValue();
                            }
                            {
                                this.second$1 = second$1;
                            }
                        }, (Function0<BoxedUnit>)((Object)new Serializable(this, second){
                            public static final long serialVersionUID = 0L;
                            public final Process second$1;

                            public final void apply() {
                                this.second$1.destroy();
                            }

                            public void apply$mcV$sp() {
                                this.second$1.destroy();
                            }
                            {
                                this.second$1 = second$1;
                            }
                        }));
                    } else {
                        option = new Some<Object>(BoxesRunTime.boxToInteger(codeA));
                    }
                    return option;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            };
            Option<Object> option2 = this.runInterruptible(new Serializable(this, first){
                public static final long serialVersionUID = 0L;
                public final Process first$1;

                public final int apply() {
                    return this.first$1.exitValue();
                }

                public int apply$mcI$sp() {
                    return this.first$1.exitValue();
                }
                {
                    this.first$1 = first$1;
                }
            }, (Function0<BoxedUnit>)((Object)new Serializable(this, first){
                public static final long serialVersionUID = 0L;
                public final Process first$1;

                public final void apply() {
                    this.first$1.destroy();
                }

                public void apply$mcV$sp() {
                    this.first$1.destroy();
                }
                {
                    this.first$1 = first$1;
                }
            }));
            if (option2.isEmpty()) {
                option = None$.MODULE$;
            } else {
                Option option3;
                int n = BoxesRunTime.unboxToInt(option2.get());
                if (this.scala$sys$process$ProcessImpl$SequentialProcess$$evaluateSecondProcess.apply$mcZI$sp(n)) {
                    Process second1 = this.scala$sys$process$ProcessImpl$SequentialProcess$$b.run(this.scala$sys$process$ProcessImpl$SequentialProcess$$io);
                    option3 = this.runInterruptible(new /* invalid duplicate definition of identical inner class */, (Function0<BoxedUnit>)((Object)new /* invalid duplicate definition of identical inner class */));
                } else {
                    option3 = new Some<Integer>(BoxesRunTime.boxToInteger(n));
                }
                option = option3;
            }
            return option;
        }

        public /* synthetic */ Process$ scala$sys$process$ProcessImpl$SequentialProcess$$$outer() {
            return this.$outer;
        }

        public SequentialProcess(Process$ $outer, ProcessBuilder a, ProcessBuilder b, ProcessIO io, Function1<Object, Object> evaluateSecondProcess) {
            this.a = a;
            this.scala$sys$process$ProcessImpl$SequentialProcess$$b = b;
            this.scala$sys$process$ProcessImpl$SequentialProcess$$io = io;
            this.scala$sys$process$ProcessImpl$SequentialProcess$$evaluateSecondProcess = evaluateSecondProcess;
            super($outer);
        }
    }
}

