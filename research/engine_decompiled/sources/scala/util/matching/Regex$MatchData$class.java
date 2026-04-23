/*
 * Decompiled with CFR 0.152.
 */
package scala.util.matching;

import java.util.NoSuchElementException;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.MapLike;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;
import scala.util.matching.Regex;

public abstract class Regex$MatchData$class {
    public static String matched(Regex.MatchData $this) {
        return $this.start() >= 0 ? ((Object)$this.source().subSequence($this.start(), $this.end())).toString() : null;
    }

    public static String group(Regex.MatchData $this, int i) {
        return $this.start(i) >= 0 ? ((Object)$this.source().subSequence($this.start(i), $this.end(i))).toString() : null;
    }

    public static List subgroups(Regex.MatchData $this) {
        Predef$ predef$ = Predef$.MODULE$;
        return RichInt$.MODULE$.to$extension0(1, $this.groupCount()).toList().map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Regex.MatchData $outer;

            public final String apply(int i) {
                return this.$outer.group(i);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, List$.MODULE$.canBuildFrom());
    }

    public static CharSequence before(Regex.MatchData $this) {
        return $this.start() >= 0 ? $this.source().subSequence(0, $this.start()) : null;
    }

    public static CharSequence before(Regex.MatchData $this, int i) {
        return $this.start(i) >= 0 ? $this.source().subSequence(0, $this.start(i)) : null;
    }

    public static CharSequence after(Regex.MatchData $this) {
        return $this.end() >= 0 ? $this.source().subSequence($this.end(), $this.source().length()) : null;
    }

    public static CharSequence after(Regex.MatchData $this, int i) {
        return $this.end(i) >= 0 ? $this.source().subSequence($this.end(i), $this.source().length()) : null;
    }

    public static Map scala$util$matching$Regex$MatchData$$nameToIndex(Regex.MatchData $this) {
        return ((MapLike)Predef$.MODULE$.Map().apply(Nil$.MODULE$)).$plus$plus($this.groupNames().toList().$colon$colon("").zipWithIndex(List$.MODULE$.canBuildFrom()));
    }

    public static String group(Regex.MatchData $this, String id) {
        Option option = $this.scala$util$matching$Regex$MatchData$$nameToIndex().get(id);
        if (None$.MODULE$.equals(option)) {
            throw new NoSuchElementException(new StringBuilder().append((Object)"group name ").append((Object)id).append((Object)" not defined").toString());
        }
        if (option instanceof Some) {
            Some some = (Some)option;
            return $this.group(BoxesRunTime.unboxToInt(some.x()));
        }
        throw new MatchError(option);
    }

    public static String toString(Regex.MatchData $this) {
        return $this.matched();
    }

    public static void $init$(Regex.MatchData $this) {
    }
}

