/*
 * Decompiled with CFR 0.152.
 */
package scala.util.matching;

import java.util.regex.Matcher;
import scala.util.matching.UnanchoredRegex;

public abstract class UnanchoredRegex$class {
    public static boolean runMatcher(UnanchoredRegex $this, Matcher m) {
        return m.find();
    }

    public static UnanchoredRegex unanchored(UnanchoredRegex $this) {
        return $this;
    }

    public static void $init$(UnanchoredRegex $this) {
    }
}

