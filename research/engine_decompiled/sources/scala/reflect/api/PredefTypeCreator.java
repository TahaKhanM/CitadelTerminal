/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

@ScalaSignature(bytes="\u0006\u0001\u00153A!\u0001\u0002\u0005\u0013\t\t\u0002K]3eK\u001a$\u0016\u0010]3De\u0016\fGo\u001c:\u000b\u0005\r!\u0011aA1qS*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\f7\u0001A\u000b\u0003\u0015}\u0019\"\u0001A\u0006\u0011\u00051iQ\"\u0001\u0002\n\u00059\u0011!a\u0003+za\u0016\u001c%/Z1u_JD\u0001\u0002\u0005\u0001\u0003\u0002\u0003\u0006I!E\u0001\u0007G>\u0004\u00180\u00138\u0011\tI\u0019R\u0003G\u0007\u0002\r%\u0011AC\u0002\u0002\n\rVt7\r^5p]F\u0002\"\u0001\u0004\f\n\u0005]\u0011!\u0001C+oSZ,'o]3\u0011\u0007UIR$\u0003\u0002\u001b7\t9A+\u001f9f)\u0006<\u0017B\u0001\u000f\u0003\u0005!!\u0016\u0010]3UC\u001e\u001c\bC\u0001\u0010 \u0019\u0001!Q\u0001\t\u0001C\u0002\u0005\u0012\u0011\u0001V\t\u0003E\u0015\u0002\"AE\u0012\n\u0005\u00112!a\u0002(pi\"Lgn\u001a\t\u0003%\u0019J!a\n\u0004\u0003\u0007\u0005s\u0017\u0010C\u0003*\u0001\u0011\u0005!&\u0001\u0004=S:LGO\u0010\u000b\u0003W1\u00022\u0001\u0004\u0001\u001e\u0011\u0015\u0001\u0002\u00061\u0001\u0012\u0011\u0015q\u0003\u0001\"\u00010\u0003\u0015\t\u0007\u000f\u001d7z+\t\u00014\u0007\u0006\u00022\u0001B\u0011!\u0007\u0010\t\u0003=M\"Q\u0001N\u0017C\u0002U\u0012\u0011!V\t\u0003EY\u00122aN\u000b:\r\u0011A\u0004\u0001\u0001\u001c\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0005IQ\u0014BA\u001e\u0007\u0005%\u0019\u0016N\\4mKR|g.\u0003\u0002>}\t!A+\u001f9f\u0013\ty$AA\u0003UsB,7\u000fC\u0003B[\u0001\u0007!)A\u0001n!\ra1IM\u0005\u0003\t\n\u0011a!T5se>\u0014\b")
public class PredefTypeCreator<T>
extends TypeCreator {
    private final Function1<Universe, TypeTags.TypeTag<T>> copyIn;

    @Override
    public <U extends Universe> Types.TypeApi apply(Mirror<U> m) {
        return this.copyIn.apply((Universe)m.universe()).tpe();
    }

    public PredefTypeCreator(Function1<Universe, TypeTags.TypeTag<T>> copyIn) {
        this.copyIn = copyIn;
    }
}

