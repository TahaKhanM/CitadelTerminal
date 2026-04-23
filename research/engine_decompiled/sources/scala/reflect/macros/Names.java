/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Names;

@ScalaSignature(bytes="\u0006\u0001%3\u0001\"\u0001\u0002\u0011\u0002G\u0005\u0011b\u0011\u0002\u0006\u001d\u0006lWm\u001d\u0006\u0003\u0007\u0011\ta!\\1de>\u001c(BA\u0003\u0007\u0003\u001d\u0011XM\u001a7fGRT\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001!\u0002\u0005\u0002\f\u00195\ta!\u0003\u0002\u000e\r\t1\u0011I\\=SK\u001aDQa\u0004\u0001\u0007\u0002A\tQA\u001a:fg\"$\u0012!\u0005\t\u0003%Uq!aC\n\n\u0005Q1\u0011A\u0002)sK\u0012,g-\u0003\u0002\u0017/\t11\u000b\u001e:j]\u001eT!\u0001\u0006\u0004)\t9IBD\b\t\u0003\u0017iI!a\u0007\u0004\u0003\u0015\u0011,\u0007O]3dCR,G-I\u0001\u001e\u0003U)6/\u001a\u0011ge\u0016\u001c\bNT1nK\u0002Jgn\u001d;fC\u0012\f\u0013aH\u0001\u0007e9\n\u0014G\f\u0019\t\u000b=\u0001a\u0011A\u0011\u0015\u0005E\u0011\u0003\"B\u0012!\u0001\u0004\t\u0012\u0001\u00028b[\u0016DC\u0001I\r\u001d=!)q\u0002\u0001D\u0001MU\u0011qE\u000b\u000b\u0003QY\u0002\"!\u000b\u0016\r\u0001\u0011)1&\nb\u0001Y\tAa*Y7f)f\u0004X-\u0005\u0002.aA\u00111BL\u0005\u0003_\u0019\u0011qAT8uQ&tw\r\u0005\u00022e5\t\u0001!\u0003\u00024i\t!a*Y7f\u0013\t)$AA\u0004BY&\f7/Z:\t\u000b\r*\u0003\u0019\u0001\u0015)\t\u0015JBD\b\u0005\u0006s\u00011\t\u0001E\u0001\nMJ,7\u000f\u001b(b[\u0016DQ!\u000f\u0001\u0007\u0002m\"\"!\u0005\u001f\t\u000b\rR\u0004\u0019A\t\t\u000be\u0002a\u0011\u0001 \u0016\u0005}\nEC\u0001!C!\tI\u0013\tB\u0003,{\t\u0007A\u0006C\u0003${\u0001\u0007\u0001\t\u0005\u0002E\u000f6\tQI\u0003\u0002G\u0005\u0005A!\r\\1dW\n|\u00070\u0003\u0002I\u000b\n91i\u001c8uKb$\b")
public interface Names {
    public String fresh();

    public String fresh(String var1);

    public <NameType extends Names.NameApi> NameType fresh(NameType var1);

    public String freshName();

    public String freshName(String var1);

    public <NameType extends Names.NameApi> NameType freshName(NameType var1);
}

