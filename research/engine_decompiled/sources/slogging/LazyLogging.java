/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.reflect.ScalaSignature;
import slogging.LoggerHolder;
import slogging.UnderlyingLogger;

@ScalaSignature(bytes="\u0006\u0001y1q!\u0001\u0002\u0011\u0002\u0007\u0005QAA\u0006MCjLHj\\4hS:<'\"A\u0002\u0002\u0011MdwnZ4j]\u001e\u001c\u0001aE\u0002\u0001\r1\u0001\"a\u0002\u0006\u000e\u0003!Q\u0011!C\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0017!\u0011a!\u00118z%\u00164\u0007CA\u0007\u000f\u001b\u0005\u0011\u0011BA\b\u0003\u00051aunZ4fe\"{G\u000eZ3s\u0011\u0015\t\u0002\u0001\"\u0001\u0013\u0003\u0019!\u0013N\\5uIQ\t1\u0003\u0005\u0002\b)%\u0011Q\u0003\u0003\u0002\u0005+:LG\u000f\u0003\u0005\u0018\u0001!\u0015\r\u0011\"\u0005\u0019\u0003\u0019awnZ4feV\t\u0011\u0004\u0005\u0002\u000e5%\u00111D\u0001\u0002\u0007\u0019><w-\u001a:\t\u0011u\u0001\u0001\u0012!Q!\ne\tq\u0001\\8hO\u0016\u0014\b\u0005")
public interface LazyLogging
extends LoggerHolder {
    @Override
    public UnderlyingLogger logger();
}

