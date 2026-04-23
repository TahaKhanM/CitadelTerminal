/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.math.Ordering;

public abstract class Ordering$OptionOrdering$class {
    public static int compare(Ordering.OptionOrdering $this, Option x, Option y) {
        Tuple2<Option, Option> tuple2;
        block6: {
            int n;
            block3: {
                block5: {
                    block4: {
                        block2: {
                            tuple2 = new Tuple2<Option, Option>(x, y);
                            if (!None$.MODULE$.equals(tuple2._1()) || !None$.MODULE$.equals(tuple2._2())) break block2;
                            n = 0;
                            break block3;
                        }
                        if (!None$.MODULE$.equals(tuple2._1())) break block4;
                        n = -1;
                        break block3;
                    }
                    if (!None$.MODULE$.equals(tuple2._2())) break block5;
                    n = 1;
                    break block3;
                }
                if (!(tuple2._1() instanceof Some)) break block6;
                Some some = (Some)tuple2._1();
                if (!(tuple2._2() instanceof Some)) break block6;
                Some some2 = (Some)tuple2._2();
                n = $this.optionOrdering().compare(some.x(), some2.x());
            }
            return n;
        }
        throw new MatchError(tuple2);
    }

    public static void $init$(Ordering.OptionOrdering $this) {
    }
}

