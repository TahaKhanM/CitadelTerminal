/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Product10;
import scala.runtime.BoxesRunTime;

public abstract class Product10$class {
    public static int productArity(Product10 $this) {
        return 10;
    }

    public static Object productElement(Product10 $this, int n) throws IndexOutOfBoundsException {
        Object object;
        switch (n) {
            default: {
                throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(n)).toString());
            }
            case 9: {
                object = $this._10();
                break;
            }
            case 8: {
                object = $this._9();
                break;
            }
            case 7: {
                object = $this._8();
                break;
            }
            case 6: {
                object = $this._7();
                break;
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

    public static void $init$(Product10 $this) {
    }
}

