/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.Reporter;
import scala.reflect.internal.ReporterImpl$ERROR$;
import scala.reflect.internal.ReporterImpl$INFO$;
import scala.reflect.internal.ReporterImpl$WARNING$;

@ScalaSignature(bytes="\u0006\u0001\u00014Q!\u0001\u0002\u0002\u0002%\u0011ABU3q_J$XM]%na2T!a\u0001\u0003\u0002\u0011%tG/\u001a:oC2T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\r\u001b\u0005\u0011\u0011BA\u0007\u0003\u0005!\u0011V\r]8si\u0016\u0014\b\"B\b\u0001\t\u0003\u0001\u0012A\u0002\u001fj]&$h\bF\u0001\u0012!\tY\u0001A\u0002\u0003\u0014\u0001\u0001!\"\u0001C*fm\u0016\u0014\u0018\u000e^=\u0014\u0005I)\u0002C\u0001\f\u0018\u001b\u00051\u0011B\u0001\r\u0007\u0005\u0019\te.\u001f*fM\"A!D\u0005BC\u0002\u0013\u00051$\u0001\u0002jIV\tA\u0004\u0005\u0002\u0017;%\u0011aD\u0002\u0002\u0004\u0013:$\b\u0002\u0003\u0011\u0013\u0005\u0003\u0005\u000b\u0011\u0002\u000f\u0002\u0007%$\u0007\u0005\u0003\u0005#%\t\u0005\t\u0015!\u0003$\u0003\u0011q\u0017-\\3\u0011\u0005\u0011:cB\u0001\f&\u0013\t1c!\u0001\u0004Qe\u0016$WMZ\u0005\u0003Q%\u0012aa\u0015;sS:<'B\u0001\u0014\u0007\u0011\u0015y!\u0003\"\u0001,)\ta\u0003\u0007\u0006\u0002._A\u0011aFE\u0007\u0002\u0001!)!E\u000ba\u0001G!)!D\u000ba\u00019!9!G\u0005a\u0001\n\u0003Y\u0012!B2pk:$\bb\u0002\u001b\u0013\u0001\u0004%\t!N\u0001\nG>,h\u000e^0%KF$\"AN\u001d\u0011\u0005Y9\u0014B\u0001\u001d\u0007\u0005\u0011)f.\u001b;\t\u000fi\u001a\u0014\u0011!a\u00019\u0005\u0019\u0001\u0010J\u0019\t\rq\u0012\u0002\u0015)\u0003\u001d\u0003\u0019\u0019w.\u001e8uA!)aH\u0005C!\u007f\u0005AAo\\*ue&tw\rF\u0001$\u000f\u0015\t\u0005\u0001#\u0001C\u0003\u0011IeJR(\u0011\u00059\u001ae!\u0002#\u0001\u0011\u0003)%\u0001B%O\r>\u001b\"aQ\u0017\t\u000b=\u0019E\u0011A$\u0015\u0003\t;Q!\u0013\u0001\t\u0002)\u000bqaV!S\u001d&su\t\u0005\u0002/\u0017\u001a)A\n\u0001E\u0001\u001b\n9q+\u0011*O\u0013:;5CA&.\u0011\u0015y1\n\"\u0001P)\u0005Qu!B)\u0001\u0011\u0003\u0011\u0016!B#S%>\u0013\u0006C\u0001\u0018T\r\u0015!\u0006\u0001#\u0001V\u0005\u0015)%KU(S'\t\u0019V\u0006C\u0003\u0010'\u0012\u0005q\u000bF\u0001S\u0011\u0015\u0011\u0004\u0001\"\u0001Z)\ta\"\fC\u0003\\1\u0002\u0007Q&\u0001\u0005tKZ,'/\u001b;z\u0011\u0015i\u0006\u0001\"\u0001_\u0003)\u0011Xm]3u\u0007>,h\u000e\u001e\u000b\u0003m}CQa\u0017/A\u00025\u0002")
public abstract class ReporterImpl
extends Reporter {
    private volatile ReporterImpl$INFO$ INFO$module;
    private volatile ReporterImpl$WARNING$ WARNING$module;
    private volatile ReporterImpl$ERROR$ ERROR$module;

    private ReporterImpl$INFO$ INFO$lzycompute() {
        ReporterImpl reporterImpl = this;
        synchronized (reporterImpl) {
            if (this.INFO$module == null) {
                this.INFO$module = new ReporterImpl$INFO$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.INFO$module;
        }
    }

    private ReporterImpl$WARNING$ WARNING$lzycompute() {
        ReporterImpl reporterImpl = this;
        synchronized (reporterImpl) {
            if (this.WARNING$module == null) {
                this.WARNING$module = new ReporterImpl$WARNING$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.WARNING$module;
        }
    }

    private ReporterImpl$ERROR$ ERROR$lzycompute() {
        ReporterImpl reporterImpl = this;
        synchronized (reporterImpl) {
            if (this.ERROR$module == null) {
                this.ERROR$module = new ReporterImpl$ERROR$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ERROR$module;
        }
    }

    @Override
    public ReporterImpl$INFO$ INFO() {
        return this.INFO$module == null ? this.INFO$lzycompute() : this.INFO$module;
    }

    @Override
    public ReporterImpl$WARNING$ WARNING() {
        return this.WARNING$module == null ? this.WARNING$lzycompute() : this.WARNING$module;
    }

    @Override
    public ReporterImpl$ERROR$ ERROR() {
        return this.ERROR$module == null ? this.ERROR$lzycompute() : this.ERROR$module;
    }

    public int count(Severity severity) {
        return severity.count();
    }

    public void resetCount(Severity severity) {
        severity.count_$eq(0);
    }

    public class Severity {
        private final int id;
        private final String name;
        private int count;
        public final /* synthetic */ ReporterImpl $outer;

        public int id() {
            return this.id;
        }

        public int count() {
            return this.count;
        }

        public void count_$eq(int x$1) {
            this.count = x$1;
        }

        public String toString() {
            return this.name;
        }

        public /* synthetic */ ReporterImpl scala$reflect$internal$ReporterImpl$Severity$$$outer() {
            return this.$outer;
        }

        public Severity(ReporterImpl $outer, int id, String name) {
            this.id = id;
            this.name = name;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this.count = 0;
        }
    }
}

