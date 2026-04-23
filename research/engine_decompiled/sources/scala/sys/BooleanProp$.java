/*
 * Decompiled with CFR 0.152.
 */
package scala.sys;

import scala.Function1;
import scala.Serializable;
import scala.sys.BooleanProp;

public final class BooleanProp$ {
    public static final BooleanProp$ MODULE$;

    static {
        new BooleanProp$();
    }

    public <T> BooleanProp valueIsTrue(String key) {
        return new BooleanProp.BooleanPropImpl(key, (Function1<String, Object>)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final boolean apply(String x$1) {
                String string2 = x$1.toLowerCase();
                return string2 != null && string2.equals("true");
            }
        }));
    }

    public <T> BooleanProp keyExists(String key) {
        return new BooleanProp.BooleanPropImpl(key, (Function1<String, Object>)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final boolean apply(String s2) {
                String string2 = s2;
                return string2 != null && string2.equals("") || s2.equalsIgnoreCase("true");
            }
        }));
    }

    public BooleanProp constant(String key, boolean isOn) {
        return new BooleanProp.ConstantImpl(key, isOn);
    }

    public boolean booleanPropAsBoolean(BooleanProp b) {
        return b.value();
    }

    private BooleanProp$() {
        MODULE$ = this;
    }
}

