/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Product2;
import scala.runtime.BoxesRunTime;

public abstract class Product2$class {
    public static int productArity(Product2 $this) {
        return 2;
    }

    public static Object productElement(Product2 $this, int n) throws IndexOutOfBoundsException {
        Object object;
        switch (n) {
            default: {
                throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
            }
            case 1: {
                object = $this._2();
                break;
            }
            case 0: {
                object = $this._1();
            }
        }
        return object;
    }

    public static double _1$mcD$sp(Product2 $this) {
        return BoxesRunTime.unboxToDouble($this._1());
    }

    public static int _1$mcI$sp(Product2 $this) {
        return BoxesRunTime.unboxToInt($this._1());
    }

    public static long _1$mcJ$sp(Product2 $this) {
        return BoxesRunTime.unboxToLong($this._1());
    }

    public static double _2$mcD$sp(Product2 $this) {
        return BoxesRunTime.unboxToDouble($this._2());
    }

    public static int _2$mcI$sp(Product2 $this) {
        return BoxesRunTime.unboxToInt($this._2());
    }

    public static long _2$mcJ$sp(Product2 $this) {
        return BoxesRunTime.unboxToLong($this._2());
    }

    public static void $init$(Product2 $this) {
    }
}

