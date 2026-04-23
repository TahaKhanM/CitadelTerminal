/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Position;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u000113\u0001\"\u0001\u0002\u0011\u0002G\u0005\u0011B\u0012\u0002\n\rJ|g\u000e^#oINT!a\u0001\u0003\u0002\r5\f7M]8t\u0015\t)a!A\u0004sK\u001adWm\u0019;\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\u0015A\u00111\u0002D\u0007\u0002\r%\u0011QB\u0002\u0002\u0007\u0003:L(+\u001a4\t\u000b=\u0001a\u0011\u0001\t\u0002\t\u0015\u001c\u0007n\u001c\u000b\u0004#Qa\u0002CA\u0006\u0013\u0013\t\u0019bA\u0001\u0003V]&$\b\"B\u000b\u000f\u0001\u00041\u0012a\u00019pgB\u0011q\u0003G\u0007\u0002\u0001%\u0011\u0011D\u0007\u0002\t!>\u001c\u0018\u000e^5p]&\u00111D\u0001\u0002\b\u00032L\u0017m]3t\u0011\u0015ib\u00021\u0001\u001f\u0003\ri7o\u001a\t\u0003?\tr!a\u0003\u0011\n\u0005\u00052\u0011A\u0002)sK\u0012,g-\u0003\u0002$I\t11\u000b\u001e:j]\u001eT!!\t\u0004\t\u000b\u0019\u0002a\u0011A\u0014\u0002\t%tgm\u001c\u000b\u0005#!J#\u0006C\u0003\u0016K\u0001\u0007a\u0003C\u0003\u001eK\u0001\u0007a\u0004C\u0003,K\u0001\u0007A&A\u0003g_J\u001cW\r\u0005\u0002\f[%\u0011aF\u0002\u0002\b\u0005>|G.Z1o\u0011\u0015\u0001\u0004A\"\u00012\u0003-A\u0017m],be:LgnZ:\u0016\u00031BQa\r\u0001\u0007\u0002Q\nqa^1s]&tw\rF\u0002\u0012kYBQ!\u0006\u001aA\u0002YAQ!\b\u001aA\u0002yAQ\u0001\u000f\u0001\u0007\u0002E\n\u0011\u0002[1t\u000bJ\u0014xN]:\t\u000bi\u0002a\u0011A\u001e\u0002\u000b\u0015\u0014(o\u001c:\u0015\u0007EaT\bC\u0003\u0016s\u0001\u0007a\u0003C\u0003\u001es\u0001\u0007a\u0004C\u0003@\u0001\u0019\u0005\u0001)A\u0003bE>\u0014H\u000fF\u0002B\t\u0016\u0003\"a\u0003\"\n\u0005\r3!a\u0002(pi\"Lgn\u001a\u0005\u0006+y\u0002\rA\u0006\u0005\u0006;y\u0002\rA\b\t\u0003\u000f*k\u0011\u0001\u0013\u0006\u0003\u0013\n\t\u0001B\u00197bG.\u0014w\u000e_\u0005\u0003\u0017\"\u0013qaQ8oi\u0016DH\u000f")
public interface FrontEnds {
    public void echo(Position var1, String var2);

    public void info(Position var1, String var2, boolean var3);

    public boolean hasWarnings();

    public void warning(Position var1, String var2);

    public boolean hasErrors();

    public void error(Position var1, String var2);

    public Nothing$ abort(Position var1, String var2);
}

