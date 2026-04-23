/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Tuple2;
import scala.collection.generic.GenericParMapCompanion;
import scala.collection.parallel.ParMap;
import scala.collection.parallel.ParMap$;
import scala.collection.parallel.mutable.ParHashMap;

public abstract class ParMap$class {
    public static GenericParMapCompanion mapCompanion(ParMap $this) {
        return ParMap$.MODULE$;
    }

    public static ParMap empty(ParMap $this) {
        return new ParHashMap();
    }

    public static String stringPrefix(ParMap $this) {
        return "ParMap";
    }

    public static ParMap updated(ParMap $this, Object key, Object value) {
        return $this.$plus(new Tuple2<Object, Object>(key, value));
    }

    public static void $init$(ParMap $this) {
    }
}

