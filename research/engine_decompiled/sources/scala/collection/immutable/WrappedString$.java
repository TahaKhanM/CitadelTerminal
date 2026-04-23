/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.Serializable;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.WrappedString;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.StringBuilder$;

public final class WrappedString$ {
    public static final WrappedString$ MODULE$;

    static {
        new WrappedString$();
    }

    public CanBuildFrom<WrappedString, Object, WrappedString> canBuildFrom() {
        return new CanBuildFrom<WrappedString, Object, WrappedString>(){

            public Builder<Object, WrappedString> apply(WrappedString from2) {
                return WrappedString$.MODULE$.newBuilder();
            }

            public Builder<Object, WrappedString> apply() {
                return WrappedString$.MODULE$.newBuilder();
            }
        };
    }

    public Builder<Object, WrappedString> newBuilder() {
        StringBuilder$ stringBuilder$ = StringBuilder$.MODULE$;
        return Builder$class.mapResult(new StringBuilder(), (Function1)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final WrappedString apply(String x) {
                return new WrappedString(x);
            }
        }));
    }

    private WrappedString$() {
        MODULE$ = this;
    }
}

