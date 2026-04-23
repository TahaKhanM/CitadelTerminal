/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.regex.PatternSyntaxException;
import scala.Array$;
import scala.Predef$;
import scala.Serializable;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.TraversableOnce;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.immutable.StringLike;
import scala.collection.immutable.StringOps;
import scala.collection.immutable.WrappedString;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.math.ScalaNumber;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichChar$;
import scala.runtime.RichInt$;
import scala.util.matching.Regex;
import scala.util.matching.Regex$;

public abstract class StringLike$class {
    public static char apply(StringLike $this, int n) {
        return $this.toString().charAt(n);
    }

    public static int length(StringLike $this) {
        return $this.toString().length();
    }

    public static String mkString(StringLike $this) {
        return $this.toString();
    }

    public static Object slice(StringLike $this, int from2, int until2) {
        Object object;
        Predef$ predef$ = Predef$.MODULE$;
        int start = RichInt$.MODULE$.max$extension(from2, 0);
        Predef$ predef$2 = Predef$.MODULE$;
        int end = RichInt$.MODULE$.min$extension(until2, $this.length());
        if (start >= end) {
            object = $this.newBuilder().result();
        } else {
            String string2 = $this.toString().substring(start, end);
            Predef$ predef$3 = Predef$.MODULE$;
            object = ((Builder)$this.newBuilder().$plus$plus$eq(new StringOps(string2))).result();
        }
        return object;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String $times(StringLike $this, int n) {
        StringBuilder buf = new StringBuilder();
        Predef$ predef$ = Predef$.MODULE$;
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(0, n, 1);
        if (range2.isEmpty()) return buf.toString();
        int i1 = range2.start();
        while (true) {
            buf.append($this.toString());
            if (i1 == range2.lastElement()) {
                return buf.toString();
            }
            i1 += range2.step();
        }
    }

    public static int compare(StringLike $this, String other) {
        return $this.toString().compareTo(other);
    }

    public static boolean scala$collection$immutable$StringLike$$isLineBreak(StringLike $this, char c) {
        return c == '\n' || c == '\f';
    }

    public static String stripLineEnd(StringLike $this) {
        char last2;
        int len = $this.toString().length();
        return len == 0 ? $this.toString() : (StringLike$class.scala$collection$immutable$StringLike$$isLineBreak($this, last2 = $this.apply(len - 1)) ? $this.toString().substring(0, last2 == '\n' && len >= 2 && $this.apply(len - 2) == '\r' ? len - 2 : len - 1) : $this.toString());
    }

    public static Iterator linesWithSeparators(StringLike $this) {
        return new AbstractIterator<String>($this){
            private final String str;
            private final int len;
            private int index;
            private final /* synthetic */ StringLike $outer;

            private String str() {
                return this.str;
            }

            private int len() {
                return this.len;
            }

            private int index() {
                return this.index;
            }

            private void index_$eq(int x$1) {
                this.index = x$1;
            }

            public boolean hasNext() {
                return this.index() < this.len();
            }

            public String next() {
                if (this.index() >= this.len()) {
                    throw new NoSuchElementException("next on empty iterator");
                }
                int start = this.index();
                while (this.index() < this.len() && !StringLike$class.scala$collection$immutable$StringLike$$isLineBreak(this.$outer, this.$outer.apply(this.index()))) {
                    this.index_$eq(this.index() + 1);
                }
                this.index_$eq(this.index() + 1);
                int n = this.index();
                Predef$ predef$ = Predef$.MODULE$;
                return this.str().substring(start, RichInt$.MODULE$.min$extension(n, this.len()));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.str = $outer.toString();
                this.len = this.str().length();
                this.index = 0;
            }
        };
    }

    public static Iterator lines(StringLike $this) {
        return $this.linesWithSeparators().map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final String apply(String line) {
                return new WrappedString(line).stripLineEnd();
            }
        });
    }

    public static Iterator linesIterator(StringLike $this) {
        return $this.linesWithSeparators().map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final String apply(String line) {
                return new WrappedString(line).stripLineEnd();
            }
        });
    }

    public static String capitalize(StringLike $this) {
        String string2;
        if ($this.toString() == null) {
            string2 = null;
        } else if ($this.toString().length() == 0) {
            string2 = "";
        } else {
            char c = $this.toString().charAt(0);
            Predef$ predef$ = Predef$.MODULE$;
            if (RichChar$.MODULE$.isUpper$extension(c)) {
                string2 = $this.toString();
            } else {
                char[] chars = $this.toString().toCharArray();
                char c2 = chars[0];
                Predef$ predef$2 = Predef$.MODULE$;
                chars[0] = RichChar$.MODULE$.toUpper$extension(c2);
                string2 = new String(chars);
            }
        }
        return string2;
    }

    public static String stripPrefix(StringLike $this, String prefix) {
        return $this.toString().startsWith(prefix) ? $this.toString().substring(prefix.length()) : $this.toString();
    }

    public static String stripSuffix(StringLike $this, String suffix) {
        return $this.toString().endsWith(suffix) ? $this.toString().substring(0, $this.toString().length() - suffix.length()) : $this.toString();
    }

    public static String replaceAllLiterally(StringLike $this, String literal, String replacement) {
        String arg1 = Regex$.MODULE$.quote(literal);
        String arg2 = Regex$.MODULE$.quoteReplacement(replacement);
        return $this.toString().replaceAll(arg1, arg2);
    }

    public static String stripMargin(StringLike $this, char marginChar) {
        StringBuilder buf = new StringBuilder();
        $this.linesWithSeparators().foreach(new Serializable($this, buf, marginChar){
            public static final long serialVersionUID = 0L;
            private final StringBuilder buf$2;
            private final char marginChar$1;

            public final StringBuilder apply(String line) {
                int index;
                int len = line.length();
                for (index = 0; index < len && line.charAt(index) <= ' '; ++index) {
                }
                return this.buf$2.append(index < len && line.charAt(index) == this.marginChar$1 ? line.substring(index + 1) : line);
            }
            {
                this.buf$2 = buf$2;
                this.marginChar$1 = (char)marginChar$1;
            }
        });
        return buf.toString();
    }

    public static String stripMargin(StringLike $this) {
        return $this.stripMargin('|');
    }

    public static String scala$collection$immutable$StringLike$$escape(StringLike $this, char ch) {
        return new StringBuilder().append((Object)"\\Q").append(BoxesRunTime.boxToCharacter(ch)).append((Object)"\\E").toString();
    }

    public static String[] split(StringLike $this, char separator) {
        String[] stringArray;
        String thisString = $this.toString();
        int pos = thisString.indexOf(separator);
        if (pos != -1) {
            ArrayBuilder.ofRef res = new ArrayBuilder.ofRef(ClassTag$.MODULE$.apply(String.class));
            int prev = 0;
            do {
                res.$plus$eq(thisString.substring(prev, pos));
            } while ((pos = thisString.indexOf(separator, prev = pos + 1)) != -1);
            Object object = prev != thisString.length() ? res.$plus$eq(thisString.substring(prev, thisString.length())) : BoxedUnit.UNIT;
            String[] initialResult = (String[])res.result();
            for (pos = initialResult.length; pos > 0 && initialResult[pos - 1].isEmpty(); --pos) {
            }
            if (pos != initialResult.length) {
                String[] trimmed = new String[pos];
                Array$.MODULE$.copy(initialResult, 0, trimmed, 0, pos);
                stringArray = trimmed;
            } else {
                stringArray = initialResult;
            }
        } else {
            stringArray = (String[])((Object[])new String[]{thisString});
        }
        return stringArray;
    }

    public static String[] split(StringLike $this, char[] separators) throws PatternSyntaxException {
        String re = new StringBuilder().append((Object)Predef$.MODULE$.charArrayOps(separators).foldLeft("[", new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StringLike $outer;

            public final String apply(String x$1, char x$2) {
                return new StringBuilder().append((Object)x$1).append((Object)StringLike$class.scala$collection$immutable$StringLike$$escape(this.$outer, x$2)).toString();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        })).append((Object)"]").toString();
        return $this.toString().split(re);
    }

    public static Regex r(StringLike $this) {
        return $this.r(Nil$.MODULE$);
    }

    public static Regex r(StringLike $this, Seq groupNames) {
        return new Regex($this.toString(), (Seq<String>)groupNames);
    }

    public static boolean toBoolean(StringLike $this) {
        return StringLike$class.parseBoolean($this, $this.toString());
    }

    public static byte toByte(StringLike $this) {
        return Byte.parseByte($this.toString());
    }

    public static short toShort(StringLike $this) {
        return Short.parseShort($this.toString());
    }

    public static int toInt(StringLike $this) {
        return Integer.parseInt($this.toString());
    }

    public static long toLong(StringLike $this) {
        return Long.parseLong($this.toString());
    }

    public static float toFloat(StringLike $this) {
        return Float.parseFloat($this.toString());
    }

    public static double toDouble(StringLike $this) {
        return Double.parseDouble($this.toString());
    }

    private static boolean parseBoolean(StringLike $this, String s2) {
        block6: {
            boolean bl;
            block5: {
                String string2;
                block4: {
                    if (s2 == null) {
                        throw new IllegalArgumentException("For input string: \"null\"");
                    }
                    string2 = s2.toLowerCase();
                    if (!"true".equals(string2)) break block4;
                    bl = true;
                    break block5;
                }
                if (!"false".equals(string2)) break block6;
                bl = false;
            }
            return bl;
        }
        throw new IllegalArgumentException(new StringBuilder().append((Object)"For input string: \"").append((Object)s2).append((Object)"\"").toString());
    }

    public static Object toArray(StringLike $this, ClassTag evidence$1) {
        return $this.toString().toCharArray();
    }

    public static Object scala$collection$immutable$StringLike$$unwrapArg(StringLike $this, Object arg) {
        Object object;
        if (arg instanceof ScalaNumber) {
            ScalaNumber scalaNumber = (ScalaNumber)arg;
            object = scalaNumber.underlying();
        } else {
            object = arg;
        }
        return object;
    }

    public static String format(StringLike $this, Seq args) {
        return String.format($this.toString(), (Object[])((TraversableOnce)args.map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StringLike $outer;

            public final Object apply(Object arg) {
                return StringLike$class.scala$collection$immutable$StringLike$$unwrapArg(this.$outer, arg);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, Seq$.MODULE$.canBuildFrom())).toArray(ClassTag$.MODULE$.AnyRef()));
    }

    public static String formatLocal(StringLike $this, Locale l, Seq args) {
        return String.format(l, $this.toString(), (Object[])((TraversableOnce)args.map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StringLike $outer;

            public final Object apply(Object arg) {
                return StringLike$class.scala$collection$immutable$StringLike$$unwrapArg(this.$outer, arg);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, Seq$.MODULE$.canBuildFrom())).toArray(ClassTag$.MODULE$.AnyRef()));
    }

    public static void $init$(StringLike $this) {
    }
}

