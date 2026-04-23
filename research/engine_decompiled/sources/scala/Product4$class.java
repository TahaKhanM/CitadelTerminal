/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Product4;
import scala.runtime.BoxesRunTime;

public abstract class Product4$class {
    public static int productArity(Product4 $this) {
        return 4;
    }

    public static Object productElement(Product4 $this, int n) throws IndexOutOfBoundsException {
        Object object;
        switch (n) {
            default: {
                throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
            }
            case 3: {
                object = $this._4();
                break;
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

    public static void $init$(Product4 $this) {
    }
}

