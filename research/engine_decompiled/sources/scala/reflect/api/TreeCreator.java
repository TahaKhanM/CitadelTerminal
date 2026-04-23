/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.Serializable;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Mirror;
import scala.reflect.api.Trees;
import scala.reflect.api.Universe;

@ScalaSignature(bytes="\u0006\u0001U2Q!\u0001\u0002\u0002\u0002%\u00111\u0002\u0016:fK\u000e\u0013X-\u0019;pe*\u00111\u0001B\u0001\u0004CBL'BA\u0003\u0007\u0003\u001d\u0011XM\u001a7fGRT\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001'\r\u0001!B\u0004\t\u0003\u00171i\u0011AB\u0005\u0003\u001b\u0019\u0011a!\u00118z%\u00164\u0007CA\u0006\u0010\u0013\t\u0001bA\u0001\u0007TKJL\u0017\r\\5{C\ndW\rC\u0003\u0013\u0001\u0011\u00051#\u0001\u0004=S:LGO\u0010\u000b\u0002)A\u0011Q\u0003A\u0007\u0002\u0005!)q\u0003\u0001D\u00011\u0005)\u0011\r\u001d9msV\u0011\u0011$\b\u000b\u00035A\u0002\"a\u0007\u0017\u0011\u0005qiB\u0002\u0001\u0003\u0006=Y\u0011\ra\b\u0002\u0002+F\u0011\u0001e\t\t\u0003\u0017\u0005J!A\t\u0004\u0003\u000f9{G\u000f[5oOJ\u0019AEJ\u0015\u0007\t\u0015\u0002\u0001a\t\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0003+\u001dJ!\u0001\u000b\u0002\u0003\u0011Us\u0017N^3sg\u0016\u0004\"a\u0003\u0016\n\u0005-2!!C*j]\u001edW\r^8o\u0013\ticF\u0001\u0003Ue\u0016,\u0017BA\u0018\u0003\u0005\u0015!&/Z3t\u0011\u0015\td\u00031\u00013\u0003\u0005i\u0007cA\u000b47%\u0011AG\u0001\u0002\u0007\u001b&\u0014(o\u001c:")
public abstract class TreeCreator
implements Serializable {
    public abstract <U extends Universe> Trees.TreeApi apply(Mirror<U> var1);
}

