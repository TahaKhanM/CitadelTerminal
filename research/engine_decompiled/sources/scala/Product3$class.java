/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Product3;
import scala.runtime.BoxesRunTime;

public abstract class Product3$class {
    public static int productArity(Product3 $this) {
        return 3;
    }

    public static Object productElement(Product3 $this, int n) throws IndexOutOfBoundsException {
        Object object;
        switch (n) {
            default: {
                throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
            }
            case 2: {
                object = $this._3();
                break;
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

    public static void $init$(Product3 $this) {
    }
}

