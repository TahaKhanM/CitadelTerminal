/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import java.io.PrintStream;
import scala.MatchError;
import scala.Option;
import slogging.LoggerTemplate;
import slogging.MessageLevel;
import slogging.MessageLevel$debug$;
import slogging.MessageLevel$error$;
import slogging.MessageLevel$info$;
import slogging.MessageLevel$trace$;
import slogging.MessageLevel$warn$;
import slogging.PrintLoggerFactory$;

public final class PrintLogger$
extends LoggerTemplate {
    public static final PrintLogger$ MODULE$;

    static {
        new PrintLogger$();
    }

    @Override
    public final void logMessage(MessageLevel level, String name, String message, Option<Throwable> cause) {
        MessageLevel messageLevel;
        block7: {
            PrintStream printStream;
            block3: {
                block6: {
                    block5: {
                        block4: {
                            block2: {
                                messageLevel = level;
                                if (!MessageLevel$error$.MODULE$.equals(messageLevel)) break block2;
                                printStream = PrintLoggerFactory$.MODULE$.errorStream();
                                break block3;
                            }
                            if (!MessageLevel$warn$.MODULE$.equals(messageLevel)) break block4;
                            printStream = PrintLoggerFactory$.MODULE$.warnStream();
                            break block3;
                        }
                        if (!MessageLevel$info$.MODULE$.equals(messageLevel)) break block5;
                        printStream = PrintLoggerFactory$.MODULE$.infoStream();
                        break block3;
                    }
                    if (!MessageLevel$debug$.MODULE$.equals(messageLevel)) break block6;
                    printStream = PrintLoggerFactory$.MODULE$.debugStream();
                    break block3;
                }
                if (!MessageLevel$trace$.MODULE$.equals(messageLevel)) break block7;
                printStream = PrintLoggerFactory$.MODULE$.traceStream();
            }
            printStream.println(PrintLoggerFactory$.MODULE$.formatter().formatMessage(level, name, message, cause));
            return;
        }
        throw new MatchError(messageLevel);
    }

    private PrintLogger$() {
        MODULE$ = this;
    }
}

