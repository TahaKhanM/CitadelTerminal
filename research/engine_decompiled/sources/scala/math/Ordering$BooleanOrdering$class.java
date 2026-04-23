/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import scala.Tuple2;
import scala.Tuple2$mcZZ$sp;
import scala.math.Ordering;

public abstract class Ordering$BooleanOrdering$class {
    public static int compare(Ordering.BooleanOrdering $this, boolean x, boolean y) {
        Tuple2$mcZZ$sp tuple2$mcZZ$sp = new Tuple2$mcZZ$sp(x, y);
        int n = false == ((Tuple2)tuple2$mcZZ$sp)._1$mcZ$sp() && true == ((Tuple2)tuple2$mcZZ$sp)._2$mcZ$sp() ? -1 : (true == ((Tuple2)tuple2$mcZZ$sp)._1$mcZ$sp() && false == ((Tuple2)tuple2$mcZZ$sp)._2$mcZ$sp() ? 1 : 0);
        return n;
    }

    public static void $init$(Ordering.BooleanOrdering $this) {
    }
}

