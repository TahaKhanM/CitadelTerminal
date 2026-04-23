/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.Reporter;
import scala.reflect.internal.util.Position;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u0005]a!C\u0001\u0003!\u0003\r\t!CA\u0007\u0005%\u0011V\r]8si&twM\u0003\u0002\u0004\t\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\u0003\t\u0003\u00171i\u0011AB\u0005\u0003\u001b\u0019\u0011a!\u00118z%\u00164\u0007\"B\b\u0001\t\u0003\u0001\u0012A\u0002\u0013j]&$H\u0005F\u0001\u0012!\tY!#\u0003\u0002\u0014\r\t!QK\\5u\u0011\u0015)\u0002A\"\u0001\u0017\u0003!\u0011X\r]8si\u0016\u0014X#A\f\u0011\u0005aIR\"\u0001\u0002\n\u0005i\u0011!\u0001\u0003*fa>\u0014H/\u001a:\t\u000bq\u0001a\u0011A\u000f\u0002\u0015\r,(O]3oiJ+h.F\u0001\u001f!\ty\u0002%D\u0001\u0001\r\u001d\t\u0003\u0001%A\u0002\u0002\t\u0012ABU;o%\u0016\u0004xN\u001d;j]\u001e\u001c\"\u0001\t\u0006\t\u000b=\u0001C\u0011\u0001\t\t\u000f\u0015\u0002#\u0019!C\u0001M\u0005I!/\u001a9peRLgnZ\u000b\u0002OA\u0011q\u0004\u000b\u0003\u0006S\u0001\u0011\tA\u000b\u0002\u0010!\u0016\u0014(+\u001e8SKB|'\u000f^5oOF\u00111F\f\t\u0003\u00171J!!\f\u0004\u0003\u000f9{G\u000f[5oOB\u0011qd\f\u0004\u0006a\u0001\t\t!\r\u0002\u0014!\u0016\u0014(+\u001e8SKB|'\u000f^5oO\n\u000b7/Z\n\u0003_)AQaM\u0018\u0005\u0002Q\na\u0001P5oSRtD#\u0001\u0018\t\u000bYzc\u0011A\u001c\u0002%\u0011,\u0007O]3dCRLwN\\,be:Lgn\u001a\u000b\u0004#az\u0004\"B\u001d6\u0001\u0004Q\u0014a\u00019pgB\u0011qdO\u0005\u0003yu\u0012\u0001\u0002U8tSRLwN\\\u0005\u0003}\t\u0011\u0011\u0002U8tSRLwN\\:\t\u000b\u0001+\u0004\u0019A!\u0002\u00075\u001cx\r\u0005\u0002C\u000b:\u00111bQ\u0005\u0003\t\u001a\ta\u0001\u0015:fI\u00164\u0017B\u0001$H\u0005\u0019\u0019FO]5oO*\u0011AI\u0002\u0005\u0007\u0013>\u0002\u000b\u0015\u0002&\u0002#M,\b\u000f\u001d7f[\u0016tG/\u001a3FeJ|'\u000f\u0005\u0002\f\u0017&\u0011AJ\u0002\u0002\b\u0005>|G.Z1o\u0011\u0015qu\u0006\"\u0001P\u0003Y\u0019X\u000f\u001d9mK6,g\u000e^#se>\u0014X*Z:tC\u001e,GCA!Q\u0011\u0015\tV\n1\u0001B\u00031)'O]8s\u001b\u0016\u001c8/Y4f\u0011\u0019\u0019\u0006\u0005)A\u0005O\u0005Q!/\u001a9peRLgn\u001a\u0011\t\u000bU\u0003a\u0011\u0003\u0014\u0002\u001fA+'OU;o%\u0016\u0004xN\u001d;j]\u001eDQa\u0016\u0001\u0005\u0002a\u000bAc];qa2,W.\u001a8u)f\u0004XM]*uCR,GCA!Z\u0011\u0015\tf\u000b1\u0001B\u0011\u0015q\u0005\u0001\"\u0001\\)\t\tE\fC\u0003R5\u0002\u0007\u0011\tC\u0003_\u0001\u0011\u0005q,\u0001\u0004j]\u001a|'/\u001c\u000b\u0003#\u0001DQ\u0001Q/A\u0002\u0005CC!\u00182fOB\u00111bY\u0005\u0003I\u001a\u0011A\u0003Z3qe\u0016\u001c\u0017\r^3e\u001fZ,'O]5eS:<\u0017%\u00014\u0002%RC\u0017n\u001d\u0011g_J<\u0018M\u001d3tAQ|\u0007\u0005\u001e5fA\r|'O]3ta>tG-\u001b8hA5,G\u000f[8eA%t\u0007E]3q_J$XM\u001d\u0011.[\u0001zg/\u001a:sS\u0012,\u0007E]3q_J$XM\u001d\u0011j]N$X-\u00193\"\u0003!\faA\r\u00182c9\u0012\u0004\"\u00026\u0001\t\u0003Y\u0017aB<be:Lgn\u001a\u000b\u0003#1DQ\u0001Q5A\u0002\u0005CC!\u001b2fO\")q\u000e\u0001C\u0001a\u0006Yq\r\\8cC2,%O]8s)\t\t\u0012\u000fC\u0003A]\u0002\u0007\u0011\t\u000b\u0003oE\u0016<\u0007\"\u0002;\u0001\t\u0003)\u0018!B1c_J$HCA\u0016w\u0011\u0015\u00015\u000f1\u0001B\u0011\u0015q\u0006\u0001\"\u0001y)\r\t\u0012P\u001f\u0005\u0006s]\u0004\rA\u000f\u0005\u0006\u0001^\u0004\r!\u0011\u0015\u0005o\n,w\rC\u0003k\u0001\u0011\u0005Q\u0010F\u0002\u0012}~DQ!\u000f?A\u0002iBQ\u0001\u0011?A\u0002\u0005CC\u0001 2fO\"1q\u000e\u0001C\u0001\u0003\u000b!R!EA\u0004\u0003\u0013Aa!OA\u0002\u0001\u0004Q\u0004B\u0002!\u0002\u0004\u0001\u0007\u0011\tK\u0003\u0002\u0004\t,wM\u0005\u0004\u0002\u0010\u0005M\u0011Q\u0003\u0004\u0007\u0003#\u0001\u0001!!\u0004\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0005a\u0001\u0001C\u0001\r>\u0001")
public interface Reporting {
    public Reporter reporter();

    public RunReporting currentRun();

    public PerRunReportingBase PerRunReporting();

    public String supplementTyperState(String var1);

    public String supplementErrorMessage(String var1);

    public void inform(String var1);

    public void warning(String var1);

    public void globalError(String var1);

    public Nothing$ abort(String var1);

    public void inform(Position var1, String var2);

    public void warning(Position var1, String var2);

    public void globalError(Position var1, String var2);

    public interface RunReporting {
        public void scala$reflect$internal$Reporting$RunReporting$_setter_$reporting_$eq(PerRunReportingBase var1);

        public PerRunReportingBase reporting();

        public /* synthetic */ Reporting scala$reflect$internal$Reporting$RunReporting$$$outer();
    }

    public abstract class PerRunReportingBase {
        private boolean supplementedError;

        public abstract void deprecationWarning(Position var1, String var2);

        public String supplementErrorMessage(String errorMessage2) {
            String string2;
            if (this.supplementedError) {
                string2 = errorMessage2;
            } else {
                this.supplementedError = true;
                string2 = this.scala$reflect$internal$Reporting$PerRunReportingBase$$$outer().supplementTyperState(errorMessage2);
            }
            return string2;
        }

        public /* synthetic */ Reporting scala$reflect$internal$Reporting$PerRunReportingBase$$$outer() {
            return Reporting.this;
        }

        public PerRunReportingBase() {
            if (Reporting.this == null) {
                throw null;
            }
            this.supplementedError = false;
        }
    }
}

