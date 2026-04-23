/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Product6;
import scala.runtime.BoxesRunTime;

public abstract class Product6$class {
    public static int productArity(Product6 $this) {
        return 6;
    }

    public static Object productElement(Product6 $this, int n) throws IndexOutOfBoundsException {
        Object object;
        switch (n) {
            default: {
                throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
            }
            case 5: {
                object = $this._6();
                break;
            }
            case 4: {
                object = $this._5();
                break;
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

    public static void $init$(Product6 $this) {
    }
}

