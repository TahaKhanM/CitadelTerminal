/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Product1;
import scala.runtime.BoxesRunTime;

public abstract class Product1$class {
    public static int productArity(Product1 $this) {
        return 1;
    }

    public static Object productElement(Product1 $this, int n) throws IndexOutOfBoundsException {
        switch (n) {
            default: {
                throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
            }
            case 0: 
        }
        return $this._1();
    }

    public static double _1$mcD$sp(Product1 $this) {
        return BoxesRunTime.unboxToDouble($this._1());
    }

    public static int _1$mcI$sp(Product1 $this) {
        return BoxesRunTime.unboxToInt($this._1());
    }

    public static long _1$mcJ$sp(Product1 $this) {
        return BoxesRunTime.unboxToLong($this._1());
    }

    public static void $init$(Product1 $this) {
    }
}

