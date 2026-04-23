/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Predef$;
import scala.StringContext;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.ListBuffer;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;

public abstract class Statistics$Quantity$class {
    public static Statistics.Quantity underlying(Statistics.Quantity $this) {
        return $this;
    }

    public static boolean showAt(Statistics.Quantity $this, String phase) {
        return $this.phases().isEmpty() || $this.phases().contains(phase);
    }

    public static String line(Statistics.Quantity $this) {
        String arg$macro$9 = $this.prefix();
        return new StringOps("%-30s: %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{arg$macro$9, $this}));
    }

    public static void $init$(Statistics.Quantity $this) {
        if (Statistics$.MODULE$.enabled()) {
            String string2 = $this.prefix();
            Predef$ predef$ = Predef$.MODULE$;
            if (new StringOps(string2).nonEmpty()) {
                Object[] objectArray = new Object[2];
                Statistics.Quantity quantity = $this.underlying();
                objectArray[0] = !(quantity != null ? !quantity.equals($this) : $this != null) ? "" : $this.underlying().prefix();
                objectArray[1] = $this.prefix();
                String key = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", "/", ""})).s(Predef$.MODULE$.genericWrapArray(objectArray));
                Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$qs().update(key, $this);
            }
        }
        $this.scala$reflect$internal$util$Statistics$Quantity$_setter_$children_$eq(new ListBuffer());
    }
}

