/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.collection.Seq;
import scala.collection.immutable.StringOps$;
import scala.runtime.BoxedUnit;

public final class StringContext$
implements Serializable {
    public static final StringContext$ MODULE$;

    static {
        new StringContext$();
    }

    public String treatEscapes(String str) {
        return this.treatEscapes0(str, false);
    }

    public String processEscapes(String str) {
        return this.treatEscapes0(str, true);
    }

    private String treatEscapes0(String str, boolean strict) {
        String string2;
        int len = str.length();
        int n = str.indexOf(92);
        switch (n) {
            default: {
                string2 = this.replace$1(n, str, strict, len);
                break;
            }
            case -1: {
                string2 = str;
            }
        }
        return string2;
    }

    public StringContext apply(Seq<String> parts) {
        return new StringContext(parts);
    }

    public Option<Seq<String>> unapplySeq(StringContext x$0) {
        return x$0 == null ? None$.MODULE$ : new Some<Seq<String>>(x$0.parts());
    }

    private Object readResolve() {
        return MODULE$;
    }

    private final String loop$1(int i, int next2, String str$1, boolean strict$1, int len$1, StringBuilder b$1) {
        while (next2 >= 0) {
            char c;
            java.io.Serializable serializable = next2 > i ? b$1.append(str$1, i, next2) : BoxedUnit.UNIT;
            int idx = next2 + 1;
            if (idx >= len$1) {
                throw new StringContext.InvalidEscapeException(str$1, next2);
            }
            Predef$ predef$ = Predef$.MODULE$;
            char c2 = StringOps$.MODULE$.apply$extension(str$1, idx);
            switch (c2) {
                default: {
                    if ('0' <= c2 && c2 <= '7') {
                        if (strict$1) {
                            throw new StringContext.InvalidEscapeException(str$1, next2);
                        }
                        Predef$ predef$2 = Predef$.MODULE$;
                        char leadch = StringOps$.MODULE$.apply$extension(str$1, idx);
                        int oct = leadch - 48;
                        if (++idx < len$1) {
                            Predef$ predef$3 = Predef$.MODULE$;
                            if ('0' <= StringOps$.MODULE$.apply$extension(str$1, idx)) {
                                Predef$ predef$4 = Predef$.MODULE$;
                                if (StringOps$.MODULE$.apply$extension(str$1, idx) <= '7') {
                                    Predef$ predef$5 = Predef$.MODULE$;
                                    oct = oct * 8 + StringOps$.MODULE$.apply$extension(str$1, idx) - 48;
                                    if (++idx < len$1 && leadch <= '3') {
                                        Predef$ predef$6 = Predef$.MODULE$;
                                        if ('0' <= StringOps$.MODULE$.apply$extension(str$1, idx)) {
                                            Predef$ predef$7 = Predef$.MODULE$;
                                            if (StringOps$.MODULE$.apply$extension(str$1, idx) <= '7') {
                                                Predef$ predef$8 = Predef$.MODULE$;
                                                oct = oct * 8 + StringOps$.MODULE$.apply$extension(str$1, idx) - 48;
                                                ++idx;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        --idx;
                        c = (char)oct;
                        break;
                    }
                    throw new StringContext.InvalidEscapeException(str$1, next2);
                }
                case '\\': {
                    c = '\\';
                    break;
                }
                case '\'': {
                    c = '\'';
                    break;
                }
                case '\"': {
                    c = '\"';
                    break;
                }
                case 'r': {
                    c = '\r';
                    break;
                }
                case 'f': {
                    c = '\f';
                    break;
                }
                case 'n': {
                    c = '\n';
                    break;
                }
                case 't': {
                    c = '\t';
                    break;
                }
                case 'b': {
                    c = '\b';
                }
            }
            char c3 = c;
            b$1.append(c3);
            next2 = str$1.indexOf(92, ++idx);
            i = idx;
        }
        java.io.Serializable serializable = i < len$1 ? b$1.append(str$1, i, len$1) : BoxedUnit.UNIT;
        return b$1.toString();
    }

    private final String replace$1(int first, String str$1, boolean strict$1, int len$1) {
        StringBuilder b = new StringBuilder();
        return this.loop$1(0, first, str$1, strict$1, len$1, b);
    }

    private StringContext$() {
        MODULE$ = this;
    }
}

