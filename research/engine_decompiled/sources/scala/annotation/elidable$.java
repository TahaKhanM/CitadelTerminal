/*
 * Decompiled with CFR 0.152.
 */
package scala.annotation;

import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.runtime.BoxesRunTime;

public final class elidable$ {
    public static final elidable$ MODULE$;
    private final int ALL;
    private final int FINEST;
    private final int FINER;
    private final int FINE;
    private final int CONFIG;
    private final int INFO;
    private final int WARNING;
    private final int SEVERE;
    private final int OFF;
    private final int MAXIMUM;
    private final int MINIMUM;
    private final int ASSERTION;
    private final Map<String, Object> byName;

    static {
        new elidable$();
    }

    public final int ALL() {
        return Integer.MIN_VALUE;
    }

    public final int FINEST() {
        return 300;
    }

    public final int FINER() {
        return 400;
    }

    public final int FINE() {
        return 500;
    }

    public final int CONFIG() {
        return 700;
    }

    public final int INFO() {
        return 800;
    }

    public final int WARNING() {
        return 900;
    }

    public final int SEVERE() {
        return 1000;
    }

    public final int OFF() {
        return Integer.MAX_VALUE;
    }

    public final int MAXIMUM() {
        return Integer.MAX_VALUE;
    }

    public final int MINIMUM() {
        return Integer.MIN_VALUE;
    }

    public final int ASSERTION() {
        return 2000;
    }

    public Map<String, Object> byName() {
        return this.byName;
    }

    private elidable$() {
        MODULE$ = this;
        Tuple2[] tuple2Array = new Tuple2[12];
        Integer n = BoxesRunTime.boxToInteger(300);
        String string2 = Predef$.MODULE$.ArrowAssoc("FINEST");
        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[0] = new Tuple2<String, Integer>(string2, n);
        Integer n2 = BoxesRunTime.boxToInteger(400);
        String string3 = Predef$.MODULE$.ArrowAssoc("FINER");
        Predef$ArrowAssoc$ predef$ArrowAssoc$2 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[1] = new Tuple2<String, Integer>(string3, n2);
        Integer n3 = BoxesRunTime.boxToInteger(500);
        String string4 = Predef$.MODULE$.ArrowAssoc("FINE");
        Predef$ArrowAssoc$ predef$ArrowAssoc$3 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[2] = new Tuple2<String, Integer>(string4, n3);
        Integer n4 = BoxesRunTime.boxToInteger(700);
        String string5 = Predef$.MODULE$.ArrowAssoc("CONFIG");
        Predef$ArrowAssoc$ predef$ArrowAssoc$4 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[3] = new Tuple2<String, Integer>(string5, n4);
        Integer n5 = BoxesRunTime.boxToInteger(800);
        String string6 = Predef$.MODULE$.ArrowAssoc("INFO");
        Predef$ArrowAssoc$ predef$ArrowAssoc$5 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[4] = new Tuple2<String, Integer>(string6, n5);
        Integer n6 = BoxesRunTime.boxToInteger(900);
        String string7 = Predef$.MODULE$.ArrowAssoc("WARNING");
        Predef$ArrowAssoc$ predef$ArrowAssoc$6 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[5] = new Tuple2<String, Integer>(string7, n6);
        Integer n7 = BoxesRunTime.boxToInteger(1000);
        String string8 = Predef$.MODULE$.ArrowAssoc("SEVERE");
        Predef$ArrowAssoc$ predef$ArrowAssoc$7 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[6] = new Tuple2<String, Integer>(string8, n7);
        Integer n8 = BoxesRunTime.boxToInteger(2000);
        String string9 = Predef$.MODULE$.ArrowAssoc("ASSERTION");
        Predef$ArrowAssoc$ predef$ArrowAssoc$8 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[7] = new Tuple2<String, Integer>(string9, n8);
        Integer n9 = BoxesRunTime.boxToInteger(Integer.MIN_VALUE);
        String string10 = Predef$.MODULE$.ArrowAssoc("ALL");
        Predef$ArrowAssoc$ predef$ArrowAssoc$9 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[8] = new Tuple2<String, Integer>(string10, n9);
        Integer n10 = BoxesRunTime.boxToInteger(Integer.MAX_VALUE);
        String string11 = Predef$.MODULE$.ArrowAssoc("OFF");
        Predef$ArrowAssoc$ predef$ArrowAssoc$10 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[9] = new Tuple2<String, Integer>(string11, n10);
        Integer n11 = BoxesRunTime.boxToInteger(Integer.MAX_VALUE);
        String string12 = Predef$.MODULE$.ArrowAssoc("MAXIMUM");
        Predef$ArrowAssoc$ predef$ArrowAssoc$11 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[10] = new Tuple2<String, Integer>(string12, n11);
        Integer n12 = BoxesRunTime.boxToInteger(Integer.MIN_VALUE);
        String string13 = Predef$.MODULE$.ArrowAssoc("MINIMUM");
        Predef$ArrowAssoc$ predef$ArrowAssoc$12 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[11] = new Tuple2<String, Integer>(string13, n12);
        this.byName = (Map)Predef$.MODULE$.Map().apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array));
    }
}

