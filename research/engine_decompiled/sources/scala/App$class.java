/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.App;
import scala.Console$;
import scala.Function0;
import scala.Function1;
import scala.Serializable;
import scala.collection.generic.TraversableForwarder$class;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.StringBuilder;
import scala.compat.Platform$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Properties$;
import scala.util.PropertiesTrait$class;

public abstract class App$class {
    public static String[] args(App $this) {
        return $this.scala$App$$_args();
    }

    public static void delayedInit(App $this, Function0 body2) {
        $this.scala$App$$initCode().$plus$eq((Object)body2);
    }

    public static void main(App $this, String[] args) {
        $this.scala$App$$_args_$eq(args);
        TraversableForwarder$class.foreach($this.scala$App$$initCode(), (Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final void apply(Function0<BoxedUnit> proc) {
                proc.apply$mcV$sp();
            }
        }));
        if (PropertiesTrait$class.propIsSet(Properties$.MODULE$, "scala.time")) {
            Platform$ platform$ = Platform$.MODULE$;
            long total2 = System.currentTimeMillis() - $this.executionStart();
            String string2 = new StringBuilder().append((Object)"[total ").append(BoxesRunTime.boxToLong(total2)).append((Object)"ms]").toString();
            Console$.MODULE$.out().println((Object)string2);
        }
    }

    public static void $init$(App $this) {
        Platform$ platform$ = Platform$.MODULE$;
        $this.scala$App$_setter_$executionStart_$eq(System.currentTimeMillis());
        $this.scala$App$_setter_$scala$App$$initCode_$eq(new ListBuffer());
    }
}

