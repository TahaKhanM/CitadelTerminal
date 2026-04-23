/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Function1;
import scala.MatchError;
import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.util.StripMarginInterpolator;

public abstract class StripMarginInterpolator$class {
    public static final String sm(StripMarginInterpolator $this, Seq args) {
        List list2;
        block4: {
            List list3;
            block3: {
                block2: {
                    list2 = $this.stringContext().parts().toList();
                    if (!(list2 instanceof $colon$colon)) break block2;
                    $colon$colon $colon$colon = ($colon$colon)list2;
                    String string2 = (String)$colon$colon.head();
                    Predef$ predef$ = Predef$.MODULE$;
                    String string3 = new StringOps(string2).stripMargin();
                    list3 = $colon$colon.tl$1().map(new Serializable($this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ StripMarginInterpolator $outer;

                        public final String apply(String s2) {
                            return StripMarginInterpolator$class.stripTrailingPart$1(this.$outer, s2);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, List$.MODULE$.canBuildFrom()).$colon$colon(string3);
                    break block3;
                }
                if (!((Object)Nil$.MODULE$).equals(list2)) break block4;
                list3 = Nil$.MODULE$;
            }
            return new StringContext(list3).raw(args);
        }
        throw new MatchError(list2);
    }

    public static final boolean isLineBreak$1(StripMarginInterpolator $this, char c) {
        return c == '\n' || c == '\f';
    }

    public static final String stripTrailingPart$1(StripMarginInterpolator $this, String s2) {
        Predef$ predef$ = Predef$.MODULE$;
        Tuple2<String, String> tuple2 = new StringOps(s2).span((Function1<Object, Object>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ StripMarginInterpolator $outer;

            public final boolean apply(char c) {
                return !StripMarginInterpolator$class.isLineBreak$1(this.$outer, c);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
        if (tuple2 != null) {
            Tuple2<String, String> tuple22 = new Tuple2<String, String>(tuple2._1(), tuple2._2());
            String pre = tuple22._1();
            String post = tuple22._2();
            Predef$ predef$2 = Predef$.MODULE$;
            return new StringBuilder().append((Object)pre).append((Object)new StringOps(post).stripMargin()).toString();
        }
        throw new MatchError(tuple2);
    }

    public static void $init$(StripMarginInterpolator $this) {
    }
}

