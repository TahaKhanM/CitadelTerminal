/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.GenTraversable;
import scala.collection.Seq;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.StringBuilder;
import scala.compat.Platform$;
import scala.reflect.internal.util.StringOps;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.RichChar$;

public abstract class StringOps$class {
    public static Seq oempty(StringOps $this, Seq xs) {
        return (Seq)xs.filterNot(new Serializable($this){
            public static final long serialVersionUID = 0L;

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public final boolean apply(String x) {
                if (x == null) return true;
                String string2 = x;
                if (string2 == null) return false;
                if (!string2.equals("")) return false;
                return true;
            }
        });
    }

    public static String ojoin(StringOps $this, Seq xs) {
        return $this.oempty(xs).mkString(" ");
    }

    public static String longestCommonPrefix(StringOps $this, List xs) {
        $colon$colon $colon$colon;
        String string2 = ((Object)Nil$.MODULE$).equals(xs) ? "" : (xs instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon = ($colon$colon)xs).tl$1()) ? (String)$colon$colon.head() : StringOps$class.lcp$1($this, xs));
        return string2;
    }

    public static String trimTrailingSpace(StringOps $this, String s2) {
        int end;
        for (end = s2.length(); end > 0; --end) {
            char c = s2.charAt(end - 1);
            Predef$ predef$ = Predef$.MODULE$;
            if (!RichChar$.MODULE$.isWhitespace$extension(c)) break;
        }
        return end == s2.length() ? s2 : s2.substring(0, end);
    }

    public static String trimAllTrailingSpace(StringOps $this, String s2) {
        Predef$ predef$ = Predef$.MODULE$;
        return new scala.collection.immutable.StringOps(s2).lines().map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StringOps $outer;

            public final String apply(String s2) {
                return this.$outer.trimTrailingSpace(s2);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }).mkString(Platform$.MODULE$.EOL());
    }

    public static List decompose(StringOps $this, String str, char sep) {
        return StringOps$class.ws$1($this, 0, str, sep);
    }

    public static List words(StringOps $this, String str) {
        return $this.decompose(str, ' ');
    }

    public static Option splitWhere(StringOps $this, String str, Function1 f, boolean doDropIndex) {
        Predef$ predef$ = Predef$.MODULE$;
        return $this.splitAt(str, new scala.collection.immutable.StringOps(str).indexWhere(f), doDropIndex);
    }

    public static boolean splitWhere$default$3(StringOps $this) {
        return false;
    }

    public static Option splitAt(StringOps $this, String str, int idx, boolean doDropIndex) {
        Option option;
        if (idx == -1) {
            option = None$.MODULE$;
        } else {
            Predef$ predef$ = Predef$.MODULE$;
            Predef$ predef$2 = Predef$.MODULE$;
            option = new Some<Tuple2<Object, Object>>(new Tuple2<Object, Object>(new scala.collection.immutable.StringOps(str).take(idx), new scala.collection.immutable.StringOps(str).drop(doDropIndex ? idx + 1 : idx)));
        }
        return option;
    }

    public static boolean splitAt$default$3(StringOps $this) {
        return false;
    }

    public static String countElementsAsString(StringOps $this, int n, String elements) {
        String string2;
        switch (n) {
            default: {
                string2 = new StringBuilder().append((Object)"").append(BoxesRunTime.boxToInteger(n)).append((Object)" ").append((Object)elements).append((Object)"s").toString();
                break;
            }
            case 4: {
                string2 = new StringBuilder().append((Object)"four ").append((Object)elements).append((Object)"s").toString();
                break;
            }
            case 3: {
                string2 = new StringBuilder().append((Object)"three ").append((Object)elements).append((Object)"s").toString();
                break;
            }
            case 2: {
                string2 = new StringBuilder().append((Object)"two ").append((Object)elements).append((Object)"s").toString();
                break;
            }
            case 1: {
                string2 = new StringBuilder().append((Object)"one ").append((Object)elements).toString();
                break;
            }
            case 0: {
                string2 = new StringBuilder().append((Object)"no ").append((Object)elements).append((Object)"s").toString();
            }
        }
        return string2;
    }

    public static String countAsString(StringOps $this, int n) {
        String string2;
        switch (n) {
            default: {
                string2 = String.valueOf(BoxesRunTime.boxToInteger(n));
                break;
            }
            case 4: {
                string2 = "four";
                break;
            }
            case 3: {
                string2 = "three";
                break;
            }
            case 2: {
                string2 = "two";
                break;
            }
            case 1: {
                string2 = "one";
                break;
            }
            case 0: {
                string2 = "none";
            }
        }
        return string2;
    }

    private static final String lcp$1(StringOps $this, List ss) {
        if (ss instanceof $colon$colon) {
            $colon$colon $colon$colon = ($colon$colon)ss;
            Tuple2 tuple2 = new Tuple2($colon$colon.head(), $colon$colon.tl$1());
            String w = (String)tuple2._1();
            List ws = tuple2._2();
            String string2 = w;
            return string2 != null && string2.equals("") ? "" : (ws.exists(new Serializable($this, w){
                public static final long serialVersionUID = 0L;
                private final String w$1;

                public final boolean apply(String s2) {
                    String string2 = s2;
                    return string2 != null && string2.equals("") || s2.charAt(0) != this.w$1.charAt(0);
                }
                {
                    this.w$1 = w$1;
                }
            }) ? "" : new StringBuilder().append((Object)w.substring(0, 1)).append((Object)StringOps$class.lcp$1($this, ss.map(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final String apply(String x$2) {
                    return x$2.substring(1);
                }
            }, List$.MODULE$.canBuildFrom()))).toString());
        }
        throw new MatchError(ss);
    }

    private static final List ws$1(StringOps $this, int start, String str$1, char sep$1) {
        GenTraversable<Nothing$> genTraversable;
        block4: {
            while (true) {
                if (start == str$1.length()) {
                    genTraversable = Nil$.MODULE$;
                    break block4;
                }
                if (str$1.charAt(start) != sep$1) break;
                ++start;
            }
            int end = str$1.indexOf(sep$1, start);
            if (end < 0) {
                genTraversable = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{str$1.substring(start)}));
            } else {
                String string2 = str$1.substring(start, end);
                genTraversable = StringOps$class.ws$1($this, end + 1, str$1, sep$1).$colon$colon(string2);
            }
        }
        return genTraversable;
    }

    public static void $init$(StringOps $this) {
    }
}

