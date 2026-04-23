/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.reflect.ScalaSignature;
import slogging.LogLevel$DEBUG$;
import slogging.LogLevel$ERROR$;
import slogging.LogLevel$INFO$;
import slogging.LogLevel$TRACE$;
import slogging.LogLevel$WARN$;
import slogging.LoggerConfig$;
import slogging.UnderlyingLogger;

@ScalaSignature(bytes="\u0006\u0001)2Q!\u0001\u0002\u0002\u0002\u0015\u0011\u0001$\u00112tiJ\f7\r^+oI\u0016\u0014H._5oO2{wmZ3s\u0015\u0005\u0019\u0011\u0001C:m_\u001e<\u0017N\\4\u0004\u0001M\u0019\u0001A\u0002\u0007\u0011\u0005\u001dQQ\"\u0001\u0005\u000b\u0003%\tQa]2bY\u0006L!a\u0003\u0005\u0003\r\u0005s\u0017PU3g!\tia\"D\u0001\u0003\u0013\ty!A\u0001\tV]\u0012,'\u000f\\=j]\u001edunZ4fe\")\u0011\u0003\u0001C\u0001%\u00051A(\u001b8jiz\"\u0012a\u0005\t\u0003\u001b\u0001AQ!\u0006\u0001\u0005\u0006Y\ta\"[:FeJ|'/\u00128bE2,G-F\u0001\u0018!\t9\u0001$\u0003\u0002\u001a\u0011\t9!i\\8mK\u0006t\u0007F\u0001\u000b\u001c!\t9A$\u0003\u0002\u001e\u0011\t1\u0011N\u001c7j]\u0016DQa\b\u0001\u0005\u0006Y\tQ\"[:XCJtWI\\1cY\u0016$\u0007F\u0001\u0010\u001c\u0011\u0015\u0011\u0003\u0001\"\u0002\u0017\u00035I7/\u00138g_\u0016s\u0017M\u00197fI\"\u0012\u0011e\u0007\u0005\u0006K\u0001!)AF\u0001\u000fSN$UMY;h\u000b:\f'\r\\3eQ\t!3\u0004C\u0003)\u0001\u0011\u0015a#\u0001\bjgR\u0013\u0018mY3F]\u0006\u0014G.\u001a3)\u0005\u001dZ\u0002")
public abstract class AbstractUnderlyingLogger
implements UnderlyingLogger {
    @Override
    public final boolean isErrorEnabled() {
        return LoggerConfig$.MODULE$.level().$greater$eq(LogLevel$ERROR$.MODULE$);
    }

    @Override
    public final boolean isWarnEnabled() {
        return LoggerConfig$.MODULE$.level().$greater$eq(LogLevel$WARN$.MODULE$);
    }

    @Override
    public final boolean isInfoEnabled() {
        return LoggerConfig$.MODULE$.level().$greater$eq(LogLevel$INFO$.MODULE$);
    }

    @Override
    public final boolean isDebugEnabled() {
        return LoggerConfig$.MODULE$.level().$greater$eq(LogLevel$DEBUG$.MODULE$);
    }

    @Override
    public final boolean isTraceEnabled() {
        return LoggerConfig$.MODULE$.level().$greater$eq(LogLevel$TRACE$.MODULE$);
    }
}

