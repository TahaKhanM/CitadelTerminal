/*
 * Decompiled with CFR 0.152.
 */
package scala.ref;

import scala.None$;
import scala.Option;
import scala.Some;
import scala.ref.Reference;

public abstract class Reference$class {
    public static String toString(Reference $this) {
        Option option = $this.get();
        Option option2 = option.isEmpty() ? None$.MODULE$ : new Some<String>(option.get().toString());
        return option2.isEmpty() ? "<deleted>" : option2.get();
    }

    public static void $init$(Reference $this) {
    }
}

