/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import java.net.URL;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001M2\u0001\"\u0001\u0002\u0011\u0002G\u0005\u0011\"\f\u0002\u000f\u0013:4'/Y:ueV\u001cG/\u001e:f\u0015\t\u0019A!\u0001\u0004nC\u000e\u0014xn\u001d\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-aQ\"\u0001\u0004\n\u000551!AB!osJ+g\rC\u0003\u0010\u0001\u0019\u0005\u0001#\u0001\u0005tKR$\u0018N\\4t+\u0005\t\u0002c\u0001\n\u001619\u00111bE\u0005\u0003)\u0019\tq\u0001]1dW\u0006<W-\u0003\u0002\u0017/\t!A*[:u\u0015\t!b\u0001\u0005\u0002\u001a99\u00111BG\u0005\u00037\u0019\ta\u0001\u0015:fI\u00164\u0017BA\u000f\u001f\u0005\u0019\u0019FO]5oO*\u00111D\u0002\u0005\u0006A\u00011\t\u0001E\u0001\u0011G>l\u0007/\u001b7feN+G\u000f^5oONDQA\t\u0001\u0007\u0002\r\n\u0011b\u00197bgN\u0004\u0016\r\u001e5\u0016\u0003\u0011\u00022AE\u000b&!\t13&D\u0001(\u0015\tA\u0013&A\u0002oKRT\u0011AK\u0001\u0005U\u00064\u0018-\u0003\u0002-O\t\u0019QK\u0015'\u0011\u00059\nT\"A\u0018\u000b\u0005A\u0012\u0011\u0001\u00032mC\u000e\\'m\u001c=\n\u0005Iz#aB\"p]R,\u0007\u0010\u001e")
public interface Infrastructure {
    public List<String> settings();

    public List<String> compilerSettings();

    public List<URL> classPath();
}

