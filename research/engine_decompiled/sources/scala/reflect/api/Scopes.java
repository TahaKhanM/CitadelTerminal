/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.collection.Iterable;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Symbols;

@ScalaSignature(bytes="\u0006\u0001\t3\u0001\"\u0001\u0002\u0011\u0002\u0007\u0005\u0011B\u0010\u0002\u0007'\u000e|\u0007/Z:\u000b\u0005\r!\u0011aA1qS*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\f7\u0001A\n\u0003\u0001)\u0001\"a\u0003\u0007\u000e\u0003\u0019I!!\u0004\u0004\u0003\r\u0005s\u0017PU3g\u0011\u0015y\u0001\u0001\"\u0001\u0011\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0003\u0005\u0002\f%%\u00111C\u0002\u0002\u0005+:LG\u000fB\u0003\u0016\u0001\t\u0005aCA\u0003TG>\u0004X-\u0005\u0002\u00185A\u00111\u0002G\u0005\u00033\u0019\u0011AAT;mYJ\u00191DC\u000f\u0007\tq\u0001\u0001A\u0007\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0003=}i\u0011\u0001\u0001\u0004\bA\u0001\u0001\n1%\u0001\"\u0005!\u00196m\u001c9f\u0003BL7cA\u0010\u000bEA\u00191EJ\u0015\u000f\u0005-!\u0013BA\u0013\u0007\u0003\u001d\u0001\u0018mY6bO\u0016L!a\n\u0015\u0003\u0011%#XM]1cY\u0016T!!\n\u0004\u0011\u0005yQ\u0013BA\u0016-\u0005\u0019\u0019\u00160\u001c2pY&\u0011QF\u0001\u0002\b'fl'm\u001c7t\t\u0015y\u0003A!\u00011\u0005-iU-\u001c2feN\u001bw\u000e]3\u0012\u0005]\t$\u0003\u0002\u001a\u000bgu2A\u0001\b\u0001\u0001cA\u0011a\u0004\u000e\u0004\bk\u0001\u0001\n1%\u00017\u00059iU-\u001c2feN\u001bw\u000e]3Ba&\u001c2\u0001\u000e\u0006\u001e\u0011\u0015ADG\"\u0001:\u0003\u0019\u0019xN\u001d;fIV\t!\bE\u0002$w%J!\u0001\u0010\u0015\u0003\t1K7\u000f\u001e\t\u0003=Q\u0001\"a\u0010!\u000e\u0003\tI!!\u0011\u0002\u0003\u0011Us\u0017N^3sg\u0016\u0004")
public interface Scopes {

    public interface ScopeApi
    extends Iterable<Symbols.SymbolApi> {
    }

    public interface MemberScopeApi
    extends ScopeApi {
        public List<Symbols.SymbolApi> sorted();
    }
}

