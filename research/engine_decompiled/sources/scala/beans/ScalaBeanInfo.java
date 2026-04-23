/*
 * Decompiled with CFR 0.152.
 */
package scala.beans;

import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;
import scala.Array$;
import scala.Predef$;
import scala.Serializable;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001q3Q!\u0001\u0002\u0002\u0002\u001d\u0011QbU2bY\u0006\u0014U-\u00198J]\u001a|'BA\u0002\u0005\u0003\u0015\u0011W-\u00198t\u0015\u0005)\u0011!B:dC2\f7\u0001A\n\u0003\u0001!\u0001\"!C\u0007\u000e\u0003)Q!aA\u0006\u000b\u00031\tAA[1wC&\u0011aB\u0003\u0002\u000f'&l\u0007\u000f\\3CK\u0006t\u0017J\u001c4p\u0011!\u0001\u0002A!A!\u0002\u0013\t\u0012!B2mCjT\bG\u0001\n\u001b!\r\u0019b\u0003G\u0007\u0002))\u0011QcC\u0001\u0005Y\u0006tw-\u0003\u0002\u0018)\t)1\t\\1tgB\u0011\u0011D\u0007\u0007\u0001\t%Yr\"!A\u0001\u0002\u000b\u0005ADA\u0002`IE\n\"!H\u0011\u0011\u0005yyR\"\u0001\u0003\n\u0005\u0001\"!a\u0002(pi\"Lgn\u001a\t\u0003=\tJ!a\t\u0003\u0003\u0007\u0005s\u0017\u0010\u0003\u0005&\u0001\t\u0005\t\u0015!\u0003'\u0003\u0015\u0001(o\u001c9t!\rqr%K\u0005\u0003Q\u0011\u0011Q!\u0011:sCf\u0004\"AK\u0017\u000f\u0005yY\u0013B\u0001\u0017\u0005\u0003\u0019\u0001&/\u001a3fM&\u0011af\f\u0002\u0007'R\u0014\u0018N\\4\u000b\u00051\"\u0001\u0002C\u0019\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0014\u0002\u000f5,G\u000f[8eg\")1\u0007\u0001C\u0001i\u00051A(\u001b8jiz\"B!N\u001c={A\u0011a\u0007A\u0007\u0002\u0005!)\u0001C\ra\u0001qA\u0012\u0011h\u000f\t\u0004'YQ\u0004CA\r<\t%Yr'!A\u0001\u0002\u000b\u0005A\u0004C\u0003&e\u0001\u0007a\u0005C\u00032e\u0001\u0007a\u0005C\u0004@\u0001\t\u0007I\u0011\u0002!\u0002\u0005A$W#A!\u0011\u0007y9#\t\u0005\u0002\n\u0007&\u0011AI\u0003\u0002\u0013!J|\u0007/\u001a:us\u0012+7o\u0019:jaR|'\u000f\u0003\u0004G\u0001\u0001\u0006I!Q\u0001\u0004a\u0012\u0004\u0003b\u0002%\u0001\u0005\u0004%I!S\u0001\u0003[\u0012,\u0012A\u0013\t\u0004=\u001dZ\u0005CA\u0005M\u0013\ti%B\u0001\tNKRDw\u000e\u001a#fg\u000e\u0014\u0018\u000e\u001d;pe\"1q\n\u0001Q\u0001\n)\u000b1!\u001c3!\u0011\u0015\t\u0006\u0001\"\u0011S\u0003Y9W\r\u001e)s_B,'\u000f^=EKN\u001c'/\u001b9u_J\u001cH#A!\t\u000bQ\u0003A\u0011I+\u0002)\u001d,G/T3uQ>$G)Z:de&\u0004Ho\u001c:t)\u0005Q\u0005\"B,\u0001\t\u0013A\u0016\u0001B5oSR$\u0012!\u0017\t\u0003=iK!a\u0017\u0003\u0003\tUs\u0017\u000e\u001e")
public abstract class ScalaBeanInfo
extends SimpleBeanInfo {
    private final Class<?> clazz;
    private final String[] props;
    public final String[] scala$beans$ScalaBeanInfo$$methods;
    private final PropertyDescriptor[] pd;
    private final MethodDescriptor[] md;

    private PropertyDescriptor[] pd() {
        return this.pd;
    }

    private MethodDescriptor[] md() {
        return this.md;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return this.pd();
    }

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        return this.md();
    }

    private void init() {
        for (int i = 0; i < this.props.length; i += 3) {
            this.pd()[i / 3] = new PropertyDescriptor(this.props[i], this.clazz, this.props[i + 1], this.props[i + 2]);
        }
    }

    public ScalaBeanInfo(Class<?> clazz, String[] props, String[] methods) {
        this.clazz = clazz;
        this.props = props;
        this.scala$beans$ScalaBeanInfo$$methods = methods;
        this.pd = new PropertyDescriptor[props.length / 3];
        this.md = (MethodDescriptor[])Predef$.MODULE$.refArrayOps((Object[])clazz.getMethods()).withFilter(new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ScalaBeanInfo $outer;

            public final boolean apply(Method m) {
                return Predef$.MODULE$.refArrayOps((Object[])this.$outer.scala$beans$ScalaBeanInfo$$methods).exists(new Serializable(this, m){
                    public static final long serialVersionUID = 0L;
                    private final Method m$1;

                    public final boolean apply(String x$1) {
                        String string2 = x$1;
                        String string3 = this.m$1.getName();
                        return !(string2 != null ? !string2.equals(string3) : string3 != null);
                    }
                    {
                        this.m$1 = m$1;
                    }
                });
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }).map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final MethodDescriptor apply(Method m) {
                return new MethodDescriptor(m);
            }
        }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(MethodDescriptor.class)));
        this.init();
    }
}

