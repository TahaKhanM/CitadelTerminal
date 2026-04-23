/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Position;
import scala.reflect.api.Trees;

@ScalaSignature(bytes="\u0006\u0001-3\u0001\"\u0001\u0002\u0011\u0002G\u0005\u0011\u0002\u0013\u0002\n!>\u001c\u0018\u000e^5p]NT!a\u0001\u0003\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\u0003\t\u0003\u00171i\u0011AB\u0005\u0003\u001b\u0019\u0011a!\u00118z%\u00164G!B\b\u0001\u0005\u0003\u0001\"\u0001\u0003)pg&$\u0018n\u001c8\u0012\u0005E!\u0002CA\u0006\u0013\u0013\t\u0019bA\u0001\u0003Ok2d'cA\u000b\u000b/\u0019!a\u0003\u0001\u0001\u0015\u00051a$/\u001a4j]\u0016lWM\u001c;?!\tA\u0012$D\u0001\u0003\u0013\ty!!\u0002\u0003\u001c+\u0001b\"a\u0001)pgB\u0011QDD\u0007\u0002\u0001!9q\u0004\u0001b\u0001\u000e\u0003\u0001\u0013A\u0003(p!>\u001c\u0018\u000e^5p]V\tA\u0004C\u0003#\u0001\u0019\u00051%A\u0003biB{7/\u0006\u0002%QQ\u0011Q%\u000e\u000b\u0003MM\u0002\"a\n\u0015\r\u0001\u0011)\u0011&\tb\u0001U\t\tA+\u0005\u0002,]A\u00111\u0002L\u0005\u0003[\u0019\u0011qAT8uQ&tw\r\u0005\u0002\u001e_%\u0011\u0001'\r\u0002\u0005)J,W-\u0003\u00023\u0005\t)AK]3fg\")A'\ta\u0001M\u0005!AO]3f\u0011\u00151\u0014\u00051\u0001\u001d\u0003\r\u0001xn\u001d\u0005\u0006q\u00011\t!O\u0001\foJ\f\u0007\u000f]5oOB{7\u000fF\u0002\u001duqBQaO\u001cA\u0002q\tq\u0001Z3gCVdG\u000fC\u0003>o\u0001\u0007a(A\u0003ue\u0016,7\u000fE\u0002@\u0005:r!a\u0003!\n\u0005\u00053\u0011a\u00029bG.\fw-Z\u0005\u0003\u0007\u0012\u0013A\u0001T5ti*\u0011\u0011I\u0002\u0005\u0006q\u00011\tA\u0012\u000b\u00039\u001dCQ!P#A\u0002y\u0002\"\u0001G%\n\u0005)\u0013!\u0001C+oSZ,'o]3")
public interface Positions {
    public Position NoPosition();

    public <T extends Trees.TreeApi> T atPos(Position var1, T var2);

    public Position wrappingPos(Position var1, List<Trees.TreeApi> var2);

    public Position wrappingPos(List<Trees.TreeApi> var1);
}

