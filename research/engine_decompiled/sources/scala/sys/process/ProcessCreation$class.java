/*
 * Decompiled with CFR 0.152.
 */
package scala.sys.process;

import java.io.File;
import java.net.URL;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.TraversableOnce;
import scala.reflect.ClassTag$;
import scala.runtime.BoxesRunTime;
import scala.sys.process.ProcessBuilder;
import scala.sys.process.ProcessBuilder$;
import scala.sys.process.ProcessBuilderImpl;
import scala.sys.process.ProcessCreation;

public abstract class ProcessCreation$class {
    public static ProcessBuilder apply(ProcessCreation $this, String command) {
        return $this.apply(command, (Option<File>)None$.MODULE$, Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[0]));
    }

    public static ProcessBuilder apply(ProcessCreation $this, Seq command) {
        return $this.apply((Seq<String>)command, (Option<File>)None$.MODULE$, Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[0]));
    }

    public static ProcessBuilder apply(ProcessCreation $this, String command, Seq arguments) {
        return $this.apply(arguments.$plus$colon(command, Seq$.MODULE$.canBuildFrom()), (Option<File>)None$.MODULE$, Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[0]));
    }

    public static ProcessBuilder apply(ProcessCreation $this, String command, File cwd, Seq extraEnv) {
        return $this.apply(command, new Some<File>(cwd), (Seq<Tuple2<String, String>>)extraEnv);
    }

    public static ProcessBuilder apply(ProcessCreation $this, Seq command, File cwd, Seq extraEnv) {
        return $this.apply((Seq<String>)command, new Some<File>(cwd), (Seq<Tuple2<String, String>>)extraEnv);
    }

    public static ProcessBuilder apply(ProcessCreation $this, String command, Option cwd, Seq extraEnv) {
        return $this.apply(Predef$.MODULE$.wrapRefArray((Object[])command.split("\\s+")), (Option<File>)cwd, (Seq<Tuple2<String, String>>)extraEnv);
    }

    public static ProcessBuilder apply(ProcessCreation $this, Seq command, Option cwd, Seq extraEnv) {
        java.lang.ProcessBuilder jpb = new java.lang.ProcessBuilder((String[])command.toArray(ClassTag$.MODULE$.apply(String.class)));
        Serializable serializable = new Serializable($this, jpb){
            public static final long serialVersionUID = 0L;
            public final java.lang.ProcessBuilder jpb$1;

            public final java.lang.ProcessBuilder apply(File x$2) {
                return this.jpb$1.directory(x$2);
            }
            {
                this.jpb$1 = jpb$1;
            }
        };
        if (!cwd.isEmpty()) {
            File file = (File)cwd.get();
            serializable.jpb$1.directory(file);
        }
        extraEnv.foreach(new Serializable($this, jpb){
            public static final long serialVersionUID = 0L;
            private final java.lang.ProcessBuilder jpb$1;

            public final String apply(Tuple2<String, String> x0$1) {
                if (x0$1 != null) {
                    return this.jpb$1.environment().put(x0$1._1(), x0$1._2());
                }
                throw new MatchError(x0$1);
            }
            {
                this.jpb$1 = jpb$1;
            }
        });
        return $this.apply(jpb);
    }

    public static ProcessBuilder apply(ProcessCreation $this, java.lang.ProcessBuilder builder) {
        return new ProcessBuilderImpl.Simple(ProcessBuilder$.MODULE$, builder);
    }

    public static ProcessBuilder.FileBuilder apply(ProcessCreation $this, File file) {
        return new ProcessBuilderImpl.FileImpl(ProcessBuilder$.MODULE$, file);
    }

    public static ProcessBuilder.URLBuilder apply(ProcessCreation $this, URL url) {
        return new ProcessBuilderImpl.URLImpl(ProcessBuilder$.MODULE$, url);
    }

    public static ProcessBuilder apply(ProcessCreation $this, boolean value) {
        return $this.apply(((Object)BoxesRunTime.boxToBoolean(value)).toString(), (Function0<Object>)((Object)new Serializable($this, value){
            public static final long serialVersionUID = 0L;
            private final boolean value$1;

            public final int apply() {
                return this.apply$mcI$sp();
            }

            public int apply$mcI$sp() {
                return this.value$1 ? 0 : 1;
            }
            {
                this.value$1 = value$1;
            }
        }));
    }

    public static ProcessBuilder apply(ProcessCreation $this, String name, Function0 exitValue) {
        return new ProcessBuilderImpl.Dummy(ProcessBuilder$.MODULE$, name, exitValue);
    }

    public static Seq applySeq(ProcessCreation $this, Seq builders, Function1 convert) {
        return builders.map(convert, Seq$.MODULE$.canBuildFrom());
    }

    public static ProcessBuilder cat(ProcessCreation $this, ProcessBuilder.Source file, Seq files2) {
        return $this.cat(files2.$plus$colon(file, Seq$.MODULE$.canBuildFrom()));
    }

    public static ProcessBuilder cat(ProcessCreation $this, Seq files2) {
        Predef$.MODULE$.require(files2.nonEmpty());
        return ((TraversableOnce)files2.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final ProcessBuilder apply(ProcessBuilder.Source x$4) {
                return x$4.cat();
            }
        }, Seq$.MODULE$.canBuildFrom())).reduceLeft(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final ProcessBuilder apply(ProcessBuilder x$5, ProcessBuilder x$6) {
                return x$5.$hash$amp$amp(x$6);
            }
        });
    }

    public static void $init$(ProcessCreation $this) {
    }
}

