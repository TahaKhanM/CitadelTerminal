/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Trees;
import scala.reflect.api.Types;

@ScalaSignature(bytes="\u0006\u000153\u0001\"\u0001\u0002\u0011\u0002\u0007\u0005\u0011b\u0012\u0002\t%\u0016Lg-[3sg*\u00111\u0001B\u0001\u0007[\u0006\u001c'o\\:\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u000b!\tYA\"D\u0001\u0007\u0013\tiaA\u0001\u0004B]f\u0014VM\u001a\u0005\u0006\u001f\u00011\t\u0001E\u0001\ne\u0016Lg-\u001f+sK\u0016$B!E\f\u001a7A\u0011!cE\u0007\u0002\u0001%\u0011A#\u0006\u0002\u0005)J,W-\u0003\u0002\u0017\u0005\t9\u0011\t\\5bg\u0016\u001c\b\"\u0002\r\u000f\u0001\u0004\t\u0012\u0001C;oSZ,'o]3\t\u000biq\u0001\u0019A\t\u0002\r5L'O]8s\u0011\u0015ab\u00021\u0001\u0012\u0003\u0011!(/Z3\t\u000by\u0001a\u0011A\u0010\u0002\u0013I,\u0017NZ=UsB,G#B\t!C\t:\u0003\"\u0002\r\u001e\u0001\u0004\t\u0002\"\u0002\u000e\u001e\u0001\u0004\t\u0002\"B\u0012\u001e\u0001\u0004!\u0013a\u0001;qKB\u0011!#J\u0005\u0003MU\u0011A\u0001V=qK\"9\u0001&\bI\u0001\u0002\u0004I\u0013\u0001C2p]\u000e\u0014X\r^3\u0011\u0005-Q\u0013BA\u0016\u0007\u0005\u001d\u0011un\u001c7fC:DQ!\f\u0001\u0007\u00029\n\u0011C]3jMf\u0014VO\u001c;j[\u0016\u001cE.Y:t)\r\tr\u0006\r\u0005\u0006G1\u0002\r\u0001\n\u0005\bQ1\u0002\n\u00111\u0001*\u0011\u0015\u0011\u0004A\"\u00014\u0003i\u0011X-\u001b4z\u000b:\u001cGn\\:j]\u001e\u0014VO\u001c;j[\u0016\u001cE.Y:t+\u0005\t\u0002\"B\u001b\u0001\r\u00031\u0014aC;oe\u0016Lg-\u001f+sK\u0016$\"!E\u001c\t\u000bq!\u0004\u0019A\t\t\u000fe\u0002\u0011\u0013!C\u0001u\u0005\u0019\"/Z5gsRK\b/\u001a\u0013eK\u001a\fW\u000f\u001c;%iU\t1H\u000b\u0002*y-\nQ\b\u0005\u0002?\u00076\tqH\u0003\u0002A\u0003\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003\u0005\u001a\t!\"\u00198o_R\fG/[8o\u0013\t!uHA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016DqA\u0012\u0001\u0012\u0002\u0013\u0005!(A\u000esK&4\u0017PU;oi&lWm\u00117bgN$C-\u001a4bk2$HE\r\t\u0003\u0011.k\u0011!\u0013\u0006\u0003\u0015\n\t\u0001B\u00197bG.\u0014w\u000e_\u0005\u0003\u0019&\u0013qaQ8oi\u0016DH\u000f")
public interface Reifiers {
    public Trees.TreeApi reifyTree(Trees.TreeApi var1, Trees.TreeApi var2, Trees.TreeApi var3);

    public Trees.TreeApi reifyType(Trees.TreeApi var1, Trees.TreeApi var2, Types.TypeApi var3, boolean var4);

    public boolean reifyType$default$4();

    public Trees.TreeApi reifyRuntimeClass(Types.TypeApi var1, boolean var2);

    public boolean reifyRuntimeClass$default$2();

    public Trees.TreeApi reifyEnclosingRuntimeClass();

    public Trees.TreeApi unreifyTree(Trees.TreeApi var1);
}

