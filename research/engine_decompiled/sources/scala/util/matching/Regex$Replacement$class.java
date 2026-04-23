/*
 * Decompiled with CFR 0.152.
 */
package scala.util.matching;

import java.util.regex.Matcher;
import scala.util.matching.Regex;

public abstract class Regex$Replacement$class {
    public static String replaced(Regex.Replacement $this) {
        StringBuffer newsb = new StringBuffer($this.scala$util$matching$Regex$Replacement$$sb());
        $this.matcher().appendTail(newsb);
        return newsb.toString();
    }

    public static Matcher replace(Regex.Replacement $this, String rs) {
        return $this.matcher().appendReplacement($this.scala$util$matching$Regex$Replacement$$sb(), rs);
    }

    public static void $init$(Regex.Replacement $this) {
        $this.scala$util$matching$Regex$Replacement$_setter_$scala$util$matching$Regex$Replacement$$sb_$eq(new StringBuffer());
    }
}

