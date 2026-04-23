/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import java.util.Date;
import scala.Option;
import scala.Predef$;
import scala.StringContext;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import slogging.MessageLevel;

@ScalaSignature(bytes="\u0006\u0001Y4q!\u0001\u0002\u0011\u0002G\u0005QA\u0001\tNKN\u001c\u0018mZ3G_Jl\u0017\r\u001e;fe*\t1!\u0001\u0005tY><w-\u001b8h\u0007\u0001\u0019\"\u0001\u0001\u0004\u0011\u0005\u001dQQ\"\u0001\u0005\u000b\u0003%\tQa]2bY\u0006L!a\u0003\u0005\u0003\r\u0005s\u0017PU3g\u0011\u0015i\u0001A\"\u0001\u000f\u000351wN]7bi6+7o]1hKR)qB\u0006\u000f\u001fAA\u0011\u0001c\u0005\b\u0003\u000fEI!A\u0005\u0005\u0002\rA\u0013X\rZ3g\u0013\t!RC\u0001\u0004TiJLgn\u001a\u0006\u0003%!AQa\u0006\u0007A\u0002a\tQ\u0001\\3wK2\u0004\"!\u0007\u000e\u000e\u0003\tI!a\u0007\u0002\u0003\u00195+7o]1hK2+g/\u001a7\t\u000bua\u0001\u0019A\b\u0002\t9\fW.\u001a\u0005\u0006?1\u0001\raD\u0001\u0004[N<\u0007\"B\u0011\r\u0001\u0004\u0011\u0013!B2bkN,\u0007cA\u0004$K%\u0011A\u0005\u0003\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005\u0019rcBA\u0014-\u001d\tA3&D\u0001*\u0015\tQC!\u0001\u0004=e>|GOP\u0005\u0002\u0013%\u0011Q\u0006C\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0003GA\u0005UQJ|w/\u00192mK*\u0011Q\u0006C\u0004\u0006e\tA\taM\u0001\u0011\u001b\u0016\u001c8/Y4f\r>\u0014X.\u0019;uKJ\u0004\"!\u0007\u001b\u0007\u000b\u0005\u0011\u0001\u0012A\u001b\u0014\u0005Q2\u0001\"B\u001c5\t\u0003A\u0014A\u0002\u001fj]&$h\bF\u00014\r\u0015QD'!\u0001<\u0005=\u0001&/\u001a4jq\u001a{'/\\1ui\u0016\u00148cA\u001d\u0007yA\u0011\u0011\u0004\u0001\u0005\u0006oe\"\tA\u0010\u000b\u0002\u007fA\u0011\u0001)O\u0007\u0002i!)Q\"\u000fC#\u0005R)qb\u0011#F\r\")q#\u0011a\u00011!)Q$\u0011a\u0001\u001f!)q$\u0011a\u0001\u001f!)\u0011%\u0011a\u0001E!\u0012\u0011\t\u0013\t\u0003\u000f%K!A\u0013\u0005\u0003\r%tG.\u001b8f\u0011\u0015a\u0015H\"\u0001N\u000311wN]7biB\u0013XMZ5y)\ryaj\u0014\u0005\u0006/-\u0003\r\u0001\u0007\u0005\u0006;-\u0003\ra\u0004\u0004\u0005#R\u0012!K\u0001\fEK\u001a\fW\u000f\u001c;Qe\u00164\u0017\u000e\u001f$pe6\fG\u000f^3s'\t\u0001v\b\u0003\u0005U!\n\u0005\t\u0015!\u0003V\u0003)\u0001(/\u001b8u\u0019\u00164X\r\u001c\t\u0003\u000fYK!a\u0016\u0005\u0003\u000f\t{w\u000e\\3b]\"A\u0011\f\u0015B\u0001B\u0003%Q+A\u0005qe&tGOT1nK\"A1\f\u0015B\u0001B\u0003%Q+\u0001\bqe&tG\u000fV5nKN$\u0018-\u001c9\t\u000b]\u0002F\u0011A/\u0015\ty{\u0006-\u0019\t\u0003\u0001BCQ\u0001\u0016/A\u0002UCQ!\u0017/A\u0002UCQa\u0017/A\u0002UCQa\u0019)\u0005\n\u0011\fAbZ3u)&lWm\u001d;b[B,\u0012!\u001a\t\u0003M.l\u0011a\u001a\u0006\u0003Q&\fA\u0001\\1oO*\t!.\u0001\u0003kCZ\f\u0017B\u0001\u000bhQ\t\u0011\u0007\nC\u0003M!\u0012\u0005c\u000eF\u0002\u0010_BDQaF7A\u0002aAQ!H7A\u0002=A\u0001B\u001d\u001b\t\u0006\u0004%\ta]\u0001\bI\u00164\u0017-\u001e7u+\u0005a\u0004\u0002C;5\u0011\u0003\u0005\u000b\u0015\u0002\u001f\u0002\u0011\u0011,g-Y;mi\u0002\u0002")
public interface MessageFormatter {
    public String formatMessage(MessageLevel var1, String var2, String var3, Option<Throwable> var4);

    public static abstract class PrefixFormatter
    implements MessageFormatter {
        @Override
        public final String formatMessage(MessageLevel level, String name, String msg, Option<Throwable> cause) {
            return cause.isDefined() ? new StringBuilder().append((Object)this.formatPrefix(level, name)).append((Object)msg).append((Object)"\n").append((Object)cause.get().toString()).toString() : new StringBuilder().append((Object)this.formatPrefix(level, name)).append((Object)msg).toString();
        }

        public abstract String formatPrefix(MessageLevel var1, String var2);
    }

    public static final class DefaultPrefixFormatter
    extends PrefixFormatter {
        private final boolean printName;
        private final boolean printTimestamp;

        private String getTimestamp() {
            return new Date().toString();
        }

        @Override
        public String formatPrefix(MessageLevel level, String name) {
            return this.printTimestamp ? (this.printName ? new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"[", ", ", ", ", "] "})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.getTimestamp(), level, name})) : new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"[", ", ", "] "})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.getTimestamp(), level}))) : (this.printName ? new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"[", ", ", "] "})).s(Predef$.MODULE$.genericWrapArray(new Object[]{level, name})) : new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"[", "] "})).s(Predef$.MODULE$.genericWrapArray(new Object[]{level})));
        }

        public DefaultPrefixFormatter(boolean printLevel, boolean printName, boolean printTimestamp) {
            this.printName = printName;
            this.printTimestamp = printTimestamp;
        }
    }
}

