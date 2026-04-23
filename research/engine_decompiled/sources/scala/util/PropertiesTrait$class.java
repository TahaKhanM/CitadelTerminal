/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import scala.Console$;
import scala.Function0;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.List$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WrappedArray;
import scala.runtime.BoxesRunTime;
import scala.util.PropertiesTrait;

public abstract class PropertiesTrait$class {
    /*
     * WARNING - void declaration
     */
    public static Properties scalaProps(PropertiesTrait $this) {
        void var1_1;
        Properties props = new Properties();
        InputStream stream = $this.pickJarBasedOn().getResourceAsStream($this.propFilename());
        if (stream != null) {
            PropertiesTrait$class.quietlyDispose($this, (Function0)((Object)new Serializable($this, props, stream){
                public static final long serialVersionUID = 0L;
                public final Properties props$1;
                public final InputStream stream$1;

                public final void apply() {
                    this.props$1.load(this.stream$1);
                }

                public void apply$mcV$sp() {
                    this.props$1.load(this.stream$1);
                }
                {
                    this.props$1 = props$1;
                    this.stream$1 = stream$1;
                }
            }), (Function0)((Object)new Serializable($this, stream){
                public static final long serialVersionUID = 0L;
                public final InputStream stream$1;

                public final void apply() {
                    this.stream$1.close();
                }

                public void apply$mcV$sp() {
                    this.stream$1.close();
                }
                {
                    this.stream$1 = stream$1;
                }
            }));
        }
        return var1_1;
    }

    private static void quietlyDispose(PropertiesTrait $this, Function0 action, Function0 disposal) {
        try {
            action.apply$mcV$sp();
        }
        catch (Throwable throwable) {
            try {
                disposal.apply$mcV$sp();
            }
            catch (IOException iOException) {}
            throw throwable;
        }
        try {
            disposal.apply$mcV$sp();
        }
        catch (IOException iOException) {}
    }

    public static boolean propIsSet(PropertiesTrait $this, String name) {
        return System.getProperty(name) != null;
    }

    public static boolean propIsSetTo(PropertiesTrait $this, String name, String value) {
        String string2 = $this.propOrNull(name);
        return !(string2 != null ? !string2.equals(value) : value != null);
    }

    public static String propOrElse(PropertiesTrait $this, String name, String alt) {
        return System.getProperty(name, alt);
    }

    public static String propOrEmpty(PropertiesTrait $this, String name) {
        return $this.propOrElse(name, "");
    }

    public static String propOrNull(PropertiesTrait $this, String name) {
        return $this.propOrElse(name, null);
    }

    public static Option propOrNone(PropertiesTrait $this, String name) {
        return Option$.MODULE$.apply($this.propOrNull(name));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean propOrFalse(PropertiesTrait $this, String name) {
        Option<String> option = $this.propOrNone(name);
        if (option.isEmpty()) return false;
        String string2 = option.get();
        WrappedArray<Object> wrappedArray = Predef$.MODULE$.wrapRefArray((Object[])new String[]{"yes", "on", "true"});
        List$ list$ = List$.MODULE$;
        if (!wrappedArray.toList().contains(string2.toLowerCase())) return false;
        return true;
    }

    public static String setProp(PropertiesTrait $this, String name, String value) {
        return System.setProperty(name, value);
    }

    public static String clearProp(PropertiesTrait $this, String name) {
        return System.clearProperty(name);
    }

    public static String envOrElse(PropertiesTrait $this, String name, String alt) {
        Serializable serializable = new Serializable($this, alt){
            public static final long serialVersionUID = 0L;
            public final String alt$1;

            public final String apply() {
                return this.alt$1;
            }
            {
                this.alt$1 = alt$1;
            }
        };
        Option<String> option = Option$.MODULE$.apply(System.getenv(name));
        return option.isEmpty() ? serializable.alt$1 : option.get();
    }

    public static Option envOrNone(PropertiesTrait $this, String name) {
        return Option$.MODULE$.apply(System.getenv(name));
    }

    public static Option envOrSome(PropertiesTrait $this, String name, Option alt) {
        Serializable serializable = new Serializable($this, alt){
            public static final long serialVersionUID = 0L;
            public final Option alt$2;

            public final Option<String> apply() {
                return this.alt$2;
            }
            {
                this.alt$2 = alt$2;
            }
        };
        Option option = $this.envOrNone(name);
        return option.isEmpty() ? serializable.alt$2 : option;
    }

    public static String scalaPropOrElse(PropertiesTrait $this, String name, String alt) {
        Serializable serializable = new Serializable($this, alt){
            public static final long serialVersionUID = 0L;
            public final String alt$3;

            public final String apply() {
                return this.alt$3;
            }
            {
                this.alt$3 = alt$3;
            }
        };
        Option<String> option = $this.scalaPropOrNone(name);
        return option.isEmpty() ? serializable.alt$3 : option.get();
    }

    public static String scalaPropOrEmpty(PropertiesTrait $this, String name) {
        return $this.scalaPropOrElse(name, "");
    }

    public static Option scalaPropOrNone(PropertiesTrait $this, String name) {
        Option<String> option = Option$.MODULE$.apply($this.scalaProps().getProperty(name));
        return option.isEmpty() ? $this.propOrNone(new StringBuilder().append((Object)"scala.").append((Object)name).toString()) : option;
    }

    public static String versionNumberString(PropertiesTrait $this) {
        return $this.scalaPropOrEmpty("version.number");
    }

    public static String sourceEncoding(PropertiesTrait $this) {
        return $this.scalaPropOrElse("file.encoding", "UTF-8");
    }

    public static String sourceReader(PropertiesTrait $this) {
        return $this.scalaPropOrElse("source.reader", "scala.tools.nsc.io.SourceReader");
    }

    public static String encodingString(PropertiesTrait $this) {
        return $this.propOrElse("file.encoding", "UTF-8");
    }

    public static String lineSeparator(PropertiesTrait $this) {
        return $this.propOrElse("line.separator", "\n");
    }

    public static String javaClassPath(PropertiesTrait $this) {
        return $this.propOrEmpty("java.class.path");
    }

    public static String javaHome(PropertiesTrait $this) {
        return $this.propOrEmpty("java.home");
    }

    public static String javaVendor(PropertiesTrait $this) {
        return $this.propOrEmpty("java.vendor");
    }

    public static String javaVersion(PropertiesTrait $this) {
        return $this.propOrEmpty("java.version");
    }

    public static String javaVmInfo(PropertiesTrait $this) {
        return $this.propOrEmpty("java.vm.info");
    }

    public static String javaVmName(PropertiesTrait $this) {
        return $this.propOrEmpty("java.vm.name");
    }

    public static String javaVmVendor(PropertiesTrait $this) {
        return $this.propOrEmpty("java.vm.vendor");
    }

    public static String javaVmVersion(PropertiesTrait $this) {
        return $this.propOrEmpty("java.vm.version");
    }

    public static String javaSpecVersion(PropertiesTrait $this) {
        return $this.propOrEmpty("java.specification.version");
    }

    public static String javaSpecVendor(PropertiesTrait $this) {
        return $this.propOrEmpty("java.specification.vendor");
    }

    public static String javaSpecName(PropertiesTrait $this) {
        return $this.propOrEmpty("java.specification.name");
    }

    public static String osName(PropertiesTrait $this) {
        return $this.propOrEmpty("os.name");
    }

    public static String scalaHome(PropertiesTrait $this) {
        return $this.propOrEmpty("scala.home");
    }

    public static String tmpDir(PropertiesTrait $this) {
        return $this.propOrEmpty("java.io.tmpdir");
    }

    public static String userDir(PropertiesTrait $this) {
        return $this.propOrEmpty("user.dir");
    }

    public static String userHome(PropertiesTrait $this) {
        return $this.propOrEmpty("user.home");
    }

    public static String userName(PropertiesTrait $this) {
        return $this.propOrEmpty("user.name");
    }

    public static boolean isWin(PropertiesTrait $this) {
        return $this.osName().startsWith("Windows");
    }

    public static boolean isMac(PropertiesTrait $this) {
        return $this.osName().startsWith("Mac OS X");
    }

    public static boolean isAvian(PropertiesTrait $this) {
        return $this.javaVmName().contains("Avian");
    }

    public static String jdkHome(PropertiesTrait $this) {
        return $this.envOrElse("JDK_HOME", $this.envOrElse("JAVA_HOME", $this.javaHome()));
    }

    public static String scala$util$PropertiesTrait$$versionFor(PropertiesTrait $this, String command) {
        String arg$macro$2 = $this.versionString();
        String arg$macro$3 = $this.copyrightString();
        return new StringOps("Scala %s %s -- %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{command, arg$macro$2, arg$macro$3}));
    }

    public static String versionMsg(PropertiesTrait $this) {
        return PropertiesTrait$class.scala$util$PropertiesTrait$$versionFor($this, $this.propCategory());
    }

    public static String scalaCmd(PropertiesTrait $this) {
        return $this.isWin() ? "scala.bat" : "scala";
    }

    public static String scalacCmd(PropertiesTrait $this) {
        return $this.isWin() ? "scalac.bat" : "scalac";
    }

    public static boolean isJavaAtLeast(PropertiesTrait $this, String version) {
        int n = PropertiesTrait$class.compareVersions$1($this, $this.javaSpecVersion(), version, 0);
        switch (n) {
            default: {
                return n >= 0;
            }
            case -2: 
        }
        throw new NumberFormatException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Not a version: ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{version})));
    }

    public static void main(PropertiesTrait $this, String[] args) {
        PrintWriter writer = new PrintWriter(Console$.MODULE$.err(), true);
        writer.println($this.versionMsg());
    }

    private static final Tuple2 versionOf$1(PropertiesTrait $this, String s2, int depth) {
        Tuple2<Integer, String> tuple2;
        int n = s2.indexOf(46);
        switch (n) {
            case -1: {
                int n2;
                if (s2.isEmpty()) {
                    n2 = depth == 0 ? -2 : 0;
                } else {
                    Predef$ predef$ = Predef$.MODULE$;
                    n2 = new StringOps(s2).toInt();
                }
                int n3 = n2;
                tuple2 = new Tuple2<Integer, String>(BoxesRunTime.boxToInteger(n3), "");
                break;
            }
            case 1: {
                if (depth == 0 && s2.charAt(0) == '1') {
                    String r0 = s2.substring(2);
                    Tuple2 tuple22 = PropertiesTrait$class.versionOf$1($this, r0, 1);
                    if (tuple22 != null) {
                        Tuple2 tuple23 = new Tuple2(BoxesRunTime.boxToInteger(tuple22._1$mcI$sp()), tuple22._2());
                        int v = tuple23._1$mcI$sp();
                        String r = (String)tuple23._2();
                        int n4 = v > 8 || r0.isEmpty() ? -2 : v;
                        tuple2 = new Tuple2<Integer, String>(BoxesRunTime.boxToInteger(n4), r);
                        break;
                    }
                    throw new MatchError(tuple22);
                }
            }
            default: {
                int n5;
                String r = s2.substring(n + 1);
                if (depth < 2 && r.isEmpty()) {
                    n5 = -2;
                } else {
                    String string2 = s2.substring(0, n);
                    Predef$ predef$ = Predef$.MODULE$;
                    n5 = new StringOps(string2).toInt();
                }
                int n6 = n5;
                tuple2 = new Tuple2<Integer, String>(BoxesRunTime.boxToInteger(n6), r);
                break;
            }
            case 0: {
                tuple2 = new Tuple2<Integer, String>(BoxesRunTime.boxToInteger(-2), s2.substring(1));
            }
        }
        return tuple2;
    }

    private static final int compareVersions$1(PropertiesTrait $this, String s2, String v, int depth) {
        Tuple2 tuple2;
        block3: {
            Tuple2 tuple22;
            while (true) {
                String vrest;
                String srest;
                block8: {
                    int n;
                    block5: {
                        int vn;
                        int sn;
                        block7: {
                            block6: {
                                block4: {
                                    if (depth < 3) break block4;
                                    n = 0;
                                    break block5;
                                }
                                tuple2 = PropertiesTrait$class.versionOf$1($this, s2, depth);
                                if (tuple2 == null) break block3;
                                Tuple2 tuple23 = new Tuple2(BoxesRunTime.boxToInteger(tuple2._1$mcI$sp()), tuple2._2());
                                sn = tuple23._1$mcI$sp();
                                srest = (String)tuple23._2();
                                tuple22 = PropertiesTrait$class.versionOf$1($this, v, depth);
                                if (tuple22 == null) break;
                                Tuple2 tuple24 = new Tuple2(BoxesRunTime.boxToInteger(tuple22._1$mcI$sp()), tuple22._2());
                                vn = tuple24._1$mcI$sp();
                                vrest = (String)tuple24._2();
                                if (vn >= 0) break block6;
                                n = -2;
                                break block5;
                            }
                            if (sn >= vn) break block7;
                            n = -1;
                            break block5;
                        }
                        if (sn <= vn) break block8;
                        n = 1;
                    }
                    return n;
                }
                ++depth;
                v = vrest;
                s2 = srest;
            }
            throw new MatchError(tuple22);
        }
        throw new MatchError(tuple2);
    }

    public static void $init$(PropertiesTrait $this) {
        $this.scala$util$PropertiesTrait$_setter_$propFilename_$eq(new StringBuilder().append((Object)"/").append((Object)$this.propCategory()).append((Object)".properties").toString());
        Serializable serializable = new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(String v) {
                return !v.endsWith("-SNAPSHOT");
            }
        };
        Option<String> option = $this.scalaPropOrNone("maven.version.number");
        $this.scala$util$PropertiesTrait$_setter_$releaseVersion_$eq(new Option.WithFilter(option, serializable).map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final String apply(String v) {
                return v;
            }
        }));
        Serializable serializable2 = new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(String v) {
                return v.endsWith("-SNAPSHOT");
            }
        };
        Option<String> option2 = $this.scalaPropOrNone("maven.version.number");
        $this.scala$util$PropertiesTrait$_setter_$developmentVersion_$eq(new Option.WithFilter(option2, serializable2).flatMap(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ PropertiesTrait $outer;

            public final Option<String> apply(String v) {
                Option<String> option = this.$outer.scalaPropOrNone("version.number");
                return option.isEmpty() ? None$.MODULE$ : new Some<String>(option.get());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
        $this.scala$util$PropertiesTrait$_setter_$versionString_$eq(new StringBuilder().append((Object)"version ").append((Object)$this.scalaPropOrElse("version.number", "(unknown)")).toString());
        $this.scala$util$PropertiesTrait$_setter_$copyrightString_$eq($this.scalaPropOrElse("copyright.string", "Copyright 2002-2017, LAMP/EPFL and Lightbend, Inc."));
    }
}

