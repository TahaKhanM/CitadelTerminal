/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.collection.generic.CanBuildFrom;
import scala.collection.mutable.Builder;
import scala.runtime.Nothing$;

public final class package$ {
    public static final package$ MODULE$;

    static {
        new package$();
    }

    public <From, T, To> CanBuildFrom<From, T, To> breakOut(CanBuildFrom<Nothing$, T, To> b) {
        return new CanBuildFrom<From, T, To>(b){
            private final CanBuildFrom b$1;

            public Builder<T, To> apply(From from2) {
                return this.b$1.apply();
            }

            public Builder<T, To> apply() {
                return this.b$1.apply();
            }
            {
                this.b$1 = b$1;
            }
        };
    }

    private package$() {
        MODULE$ = this;
    }
}

