/*
 * Decompiled with CFR 0.152.
 */
package scala.util.matching;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import scala.Serializable;

public final class Regex$
implements Serializable {
    public static final Regex$ MODULE$;

    static {
        new Regex$();
    }

    public String quote(String text) {
        return Pattern.quote(text);
    }

    public String quoteReplacement(String text) {
        return Matcher.quoteReplacement(text);
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Regex$() {
        MODULE$ = this;
    }
}

