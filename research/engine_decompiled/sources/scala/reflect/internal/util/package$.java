/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.collection.AbstractTraversable;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.util.package;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;
import scala.runtime.RichChar$;

public final class package$ {
    public static final package$ MODULE$;
    private final List<List<Nothing$>> ListOfNil;

    static {
        new package$();
    }

    public List<List<Nothing$>> ListOfNil() {
        return this.ListOfNil;
    }

    public boolean andFalse(BoxedUnit body2) {
        return false;
    }

    private String shortenName(String name) {
        String string2 = name;
        if (string2 != null && string2.equals("")) {
            return "";
        }
        Predef$ predef$ = Predef$.MODULE$;
        List segments2 = Predef$.MODULE$.refArrayOps((Object[])new StringOps(name).split('$')).toList();
        String last2 = (String)segments2.last();
        return last2.length() == 0 ? ((AbstractTraversable)segments2.takeRight(2)).mkString("$") : last2;
    }

    public String shortClassOfInstance(Object x) {
        return this.shortClass(x.getClass());
    }

    public String shortClass(Class<?> clazz) {
        String string2;
        String string3 = clazz.getName();
        Predef$ predef$ = Predef$.MODULE$;
        String name = (String)Predef$.MODULE$.refArrayOps((Object[])new StringOps(string3).split('.')).last();
        if (this.isModule$1(name)) {
            Predef$ predef$2 = Predef$.MODULE$;
            string2 = new StringBuilder().append((Object)((String)Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])new StringOps(name).split('$')).filterNot(new Serializable(){
                public static final long serialVersionUID = 0L;

                public final boolean apply(String x$3) {
                    String string2 = x$3;
                    return string2 != null && string2.equals("");
                }
            })).last())).append((Object)"$").toString();
        } else if (this.isAnon$1(name)) {
            Class<?> clazz2 = clazz.getSuperclass();
            string2 = ((TraversableOnce)Predef$.MODULE$.refArrayOps((Object[])clazz.getInterfaces()).toList().$colon$colon(clazz2).map(new Serializable(){
                public static final long serialVersionUID = 0L;

                public final String apply(Class<?> c) {
                    return package$.MODULE$.shortClass(c);
                }
            }, List$.MODULE$.canBuildFrom())).mkString(" with ");
        } else {
            string2 = this.shortenName(name);
        }
        return string2;
    }

    public package.StringContextStripMarginOps StringContextStripMarginOps(StringContext stringContext) {
        return new package.StringContextStripMarginOps(stringContext);
    }

    private final boolean isModule$1(String name$1) {
        return name$1.endsWith("$");
    }

    private final boolean isAnon$1(String name$1) {
        Predef$ predef$ = Predef$.MODULE$;
        String string2 = (String)Predef$.MODULE$.refArrayOps((Object[])new StringOps(name$1).split('$')).last();
        Predef$ predef$2 = Predef$.MODULE$;
        return new StringOps(string2).forall((Function1<Object, Object>)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final boolean apply(char x$2) {
                Predef$ predef$ = Predef$.MODULE$;
                return RichChar$.MODULE$.isDigit$extension(x$2);
            }
        }));
    }

    private package$() {
        MODULE$ = this;
        Nil$ nil$ = Nil$.MODULE$;
        this.ListOfNil = Nil$.MODULE$.$colon$colon(nil$);
    }
}

