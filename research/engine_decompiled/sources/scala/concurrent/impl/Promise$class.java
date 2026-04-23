/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.impl;

import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.collection.mutable.StringBuilder;
import scala.concurrent.impl.Promise;

public abstract class Promise$class {
    public static Promise future(Promise $this) {
        return $this;
    }

    public static String toString(Promise $this) {
        Option option;
        block4: {
            String string2;
            block3: {
                block2: {
                    option = $this.value();
                    if (!(option instanceof Some)) break block2;
                    Some some = (Some)option;
                    string2 = new StringBuilder().append((Object)"Future(").append(some.x()).append((Object)")").toString();
                    break block3;
                }
                if (!None$.MODULE$.equals(option)) break block4;
                string2 = "Future(<not completed>)";
            }
            return string2;
        }
        throw new MatchError(option);
    }

    public static void $init$(Promise $this) {
    }
}

