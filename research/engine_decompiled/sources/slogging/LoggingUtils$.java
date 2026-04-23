/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.MatchError;
import scala.Predef$;
import scala.collection.Seq;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ClassTag$;
import scala.runtime.BoxesRunTime;

public final class LoggingUtils$ {
    public static final LoggingUtils$ MODULE$;

    static {
        new LoggingUtils$();
    }

    public String argsBracketFormat(String msg, Seq<Object> args) {
        return args.isEmpty() ? msg : this.loop$1("", new StringOps(Predef$.MODULE$.augmentString(msg)).toList(), args);
    }

    public String argsStringFormat(String msg, Seq<Object> args) {
        return String.format(msg, (Object[])args.toArray(ClassTag$.MODULE$.Object()));
    }

    private final String loop$1(String acc, List chars, Seq args) {
        List list2;
        block11: {
            String string2;
            block10: {
                String string3;
                while (true) {
                    List xs;
                    if (chars.isEmpty()) {
                        string2 = acc;
                        break block10;
                    }
                    if (args.isEmpty()) {
                        string2 = new StringBuilder().append((Object)acc).append((Object)chars.mkString()).toString();
                        break block10;
                    }
                    boolean bl = false;
                    $colon$colon $colon$colon = null;
                    list2 = chars;
                    if (list2 instanceof $colon$colon) {
                        bl = true;
                        $colon$colon = ($colon$colon)list2;
                        char c = BoxesRunTime.unboxToChar($colon$colon.head());
                        List list3 = $colon$colon.tl$1();
                        if ('{' == c && list3 instanceof $colon$colon) {
                            $colon$colon $colon$colon2 = ($colon$colon)list3;
                            char c2 = BoxesRunTime.unboxToChar($colon$colon2.head());
                            List xs2 = $colon$colon2.tl$1();
                            if ('}' == c2) {
                                String string4 = new StringBuilder().append((Object)acc).append(args.head()).toString();
                                args = (Seq)args.tail();
                                chars = xs2;
                                acc = string4;
                                continue;
                            }
                        }
                    }
                    if (!bl) break;
                    char a = BoxesRunTime.unboxToChar($colon$colon.head());
                    chars = xs = $colon$colon.tl$1();
                    acc = new StringBuilder().append((Object)acc).append(BoxesRunTime.boxToCharacter(a)).toString();
                }
                if (!((Object)Nil$.MODULE$).equals(list2)) break block11;
                string2 = string3 = acc;
            }
            return string2;
        }
        throw new MatchError(list2);
    }

    private LoggingUtils$() {
        MODULE$ = this;
    }
}

