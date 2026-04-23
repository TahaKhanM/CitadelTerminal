/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import scala.Function1;
import scala.Serializable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.io.Directory;
import scala.reflect.io.Path;
import scala.reflect.io.PlainFile;

@ScalaSignature(bytes="\u0006\u0001=2A!\u0001\u0002\u0001\u0013\tq\u0001\u000b\\1j]\u0012K'/Z2u_JL(BA\u0002\u0005\u0003\tIwN\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\u0003\t\u0003\u00171i\u0011AA\u0005\u0003\u001b\t\u0011\u0011\u0002\u00157bS:4\u0015\u000e\\3\t\u0013=\u0001!\u0011!Q\u0001\nA\u0019\u0012!C4jm\u0016t\u0007+\u0019;i!\tY\u0011#\u0003\u0002\u0013\u0005\tIA)\u001b:fGR|'/_\u0005\u0003\u001f1AQ!\u0006\u0001\u0005\u0002Y\ta\u0001P5oSRtDCA\f\u0019!\tY\u0001\u0001C\u0003\u0010)\u0001\u0007\u0001\u0003C\u0003\u001b\u0001\u0011\u00053$A\u0006jg\u0012K'/Z2u_JLX#\u0001\u000f\u0011\u0005uqR\"\u0001\u0004\n\u0005}1!a\u0002\"p_2,\u0017M\u001c\u0005\u0006C\u0001!\tEI\u0001\tSR,'/\u0019;peV\t1\u0005E\u0002%O)i\u0011!\n\u0006\u0003M\u0019\t!bY8mY\u0016\u001cG/[8o\u0013\tASE\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0011\u0015Q\u0003\u0001\"\u0011,\u0003\u0019!W\r\\3uKR\tA\u0006\u0005\u0002\u001e[%\u0011aF\u0002\u0002\u0005+:LG\u000f")
public class PlainDirectory
extends PlainFile {
    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public Iterator<PlainFile> iterator() {
        return ((Directory)super.givenPath()).list().filter((Function1<Path, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Path x$1) {
                return x$1.exists();
            }
        })).map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final PlainFile apply(Path x) {
                return new PlainFile(x);
            }
        });
    }

    @Override
    public void delete() {
        ((Directory)super.givenPath()).deleteRecursively();
    }

    public PlainDirectory(Directory givenPath) {
        super(givenPath);
    }
}

