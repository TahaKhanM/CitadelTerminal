/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.transform;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.transform.PostErasure$elimErasedValueType$;

@ScalaSignature(bytes="\u0006\u0001\u00113q!\u0001\u0002\u0011\u0002\u0007\u00051BA\u0006Q_N$XI]1tkJ,'BA\u0002\u0005\u0003%!(/\u00198tM>\u0014XN\u0003\u0002\u0006\r\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\b\u0011\u00059!/\u001a4mK\u000e$(\"A\u0005\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001\u0001\u0004\t\u0003\u001b9i\u0011\u0001C\u0005\u0003\u001f!\u0011a!\u00118z%\u00164\u0007\"B\t\u0001\t\u0003\u0011\u0012A\u0002\u0013j]&$H\u0005F\u0001\u0014!\tiA#\u0003\u0002\u0016\u0011\t!QK\\5u\u0011\u001d9\u0002A1A\u0007\u0002a\taa\u001a7pE\u0006dW#A\r\u0011\u0005iYR\"\u0001\u0003\n\u0005q!!aC*z[\n|G\u000eV1cY\u0016<QA\b\u0001\t\u0002}\t1#\u001a7j[\u0016\u0013\u0018m]3e-\u0006dW/\u001a+za\u0016\u0004\"\u0001I\u0011\u000e\u0003\u00011QA\t\u0001\t\u0002\r\u00121#\u001a7j[\u0016\u0013\u0018m]3e-\u0006dW/\u001a+za\u0016\u001c\"!\t\u0013\u0011\u0005\u00152cB\u0001\u0011\u0017\u0013\t9\u0003FA\u0004UsB,W*\u00199\n\u0005%R#\u0001\u0003+za\u0016l\u0015\r]:\u000b\u0005-\"\u0011a\u0001;qK\")Q&\tC\u0001]\u00051A(\u001b8jiz\"\u0012a\b\u0005\u0006a\u0005\"\t!M\u0001\u0006CB\u0004H.\u001f\u000b\u0003e]\u0002\"!J\u001a\n\u0005Q*$\u0001\u0002+za\u0016L!A\u000e\u0003\u0003\u000bQK\b/Z:\t\u000baz\u0003\u0019\u0001\u001a\u0002\u0005Q\u0004\b\"\u0002\u001e\u0001\t\u0003Y\u0014!\u0004;sC:\u001chm\u001c:n\u0013:4w\u000eF\u00023y\rCQ!P\u001dA\u0002y\n1a]=n!\t)s(\u0003\u0002A\u0003\n11+_7c_2L!A\u0011\u0003\u0003\u000fMKXNY8mg\")\u0001(\u000fa\u0001e\u0001")
public interface PostErasure {
    public SymbolTable global();

    public PostErasure$elimErasedValueType$ elimErasedValueType();

    public Types.Type transformInfo(Symbols.Symbol var1, Types.Type var2);
}

