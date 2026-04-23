/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Product;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;

public abstract class Product$class {
    public static Iterator productIterator(Product $this) {
        return new AbstractIterator<Object>($this){
            private int c;
            private final int cmax;
            private final /* synthetic */ Product $outer;

            private int c() {
                return this.c;
            }

            private void c_$eq(int x$1) {
                this.c = x$1;
            }

            private int cmax() {
                return this.cmax;
            }

            public boolean hasNext() {
                return this.c() < this.cmax();
            }

            /*
             * WARNING - void declaration
             */
            public Object next() {
                void var1_1;
                Object result2 = this.$outer.productElement(this.c());
                this.c_$eq(this.c() + 1);
                return var1_1;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.c = 0;
                this.cmax = $outer.productArity();
            }
        };
    }

    public static String productPrefix(Product $this) {
        return "";
    }

    public static void $init$(Product $this) {
    }
}

