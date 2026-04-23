/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.convert;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.mutable.Buffer;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\u0005UfaB\u0001\u0003!\u0003\r\t!\u0003\u0002\u000b/J\f\u0007/Q:KCZ\f'BA\u0002\u0005\u0003\u001d\u0019wN\u001c<feRT!!\u0002\u0004\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-aQ\"\u0001\u0004\n\u000551!AB!osJ+g\rC\u0003\u0010\u0001\u0011\u0005\u0001#\u0001\u0004%S:LG\u000f\n\u000b\u0002#A\u00111BE\u0005\u0003'\u0019\u0011A!\u00168ji\")Q\u0003\u0001C\u0002-\u0005q\u0011m\u001d&bm\u0006LE/\u001a:bi>\u0014XCA\f#)\tA2\u0006E\u0002\u001a=\u0001j\u0011A\u0007\u0006\u00037q\tA!\u001e;jY*\tQ$\u0001\u0003kCZ\f\u0017BA\u0010\u001b\u0005!IE/\u001a:bi>\u0014\bCA\u0011#\u0019\u0001!Qa\t\u000bC\u0002\u0011\u0012\u0011!Q\t\u0003K!\u0002\"a\u0003\u0014\n\u0005\u001d2!a\u0002(pi\"Lgn\u001a\t\u0003\u0017%J!A\u000b\u0004\u0003\u0007\u0005s\u0017\u0010C\u0003-)\u0001\u0007Q&\u0001\u0002jiB\u0019af\f\u0011\u000e\u0003\u0011I!a\b\u0003\t\u000bE\u0002A1\u0001\u001a\u0002#\u0005\u001c(*\u0019<b\u000b:,X.\u001a:bi&|g.\u0006\u00024qQ\u0011A'\u000f\t\u00043U:\u0014B\u0001\u001c\u001b\u0005-)e.^7fe\u0006$\u0018n\u001c8\u0011\u0005\u0005BD!B\u00121\u0005\u0004!\u0003\"\u0002\u00171\u0001\u0004Q\u0004c\u0001\u00180o!)A\b\u0001C\u0002{\u0005q\u0011m\u001d&bm\u0006LE/\u001a:bE2,WC\u0001 G)\tyt\tE\u0002A\u0007\u0016k\u0011!\u0011\u0006\u0003\u0005r\tA\u0001\\1oO&\u0011A)\u0011\u0002\t\u0013R,'/\u00192mKB\u0011\u0011E\u0012\u0003\u0006Gm\u0012\r\u0001\n\u0005\u0006\u0011n\u0002\r!S\u0001\u0002SB\u0019aFS#\n\u0005\u0011#\u0001\"\u0002'\u0001\t\u0007i\u0015\u0001E1t\u0015\u00064\u0018mQ8mY\u0016\u001cG/[8o+\tq5\u000b\u0006\u0002P)B\u0019\u0011\u0004\u0015*\n\u0005ES\"AC\"pY2,7\r^5p]B\u0011\u0011e\u0015\u0003\u0006G-\u0013\r\u0001\n\u0005\u0006Y-\u0003\r!\u0016\t\u0004])\u0013\u0006\"B,\u0001\t\u0007A\u0016\u0001\u00052vM\u001a,'/Q:KCZ\fG*[:u+\tIf\f\u0006\u0002[?B\u0019\u0011dW/\n\u0005qS\"\u0001\u0002'jgR\u0004\"!\t0\u0005\u000b\r2&\u0019\u0001\u0013\t\u000b\u00014\u0006\u0019A1\u0002\u0003\t\u00042AY3^\u001b\u0005\u0019'B\u00013\u0005\u0003\u001diW\u000f^1cY\u0016L!AZ2\u0003\r\t+hMZ3s\u0011\u0015A\u0007\u0001b\u0001j\u0003QiW\u000f^1cY\u0016\u001cV-]!t\u0015\u00064\u0018\rT5tiV\u0011!.\u001c\u000b\u0003W:\u00042!G.m!\t\tS\u000eB\u0003$O\n\u0007A\u0005C\u0003pO\u0002\u0007\u0001/A\u0002tKF\u00042AY9m\u0013\t\u00118MA\u0002TKFDQ\u0001\u001e\u0001\u0005\u0004U\fQb]3r\u0003NT\u0015M^1MSN$XC\u0001<z)\t9(\u0010E\u0002\u001a7b\u0004\"!I=\u0005\u000b\r\u001a(\u0019\u0001\u0013\t\u000b=\u001c\b\u0019A>\u0011\u00079b\b0\u0003\u0002s\t!)a\u0010\u0001C\u0002\u007f\u0006\u0019R.\u001e;bE2,7+\u001a;Bg*\u000bg/Y*fiV!\u0011\u0011AA\u0006)\u0011\t\u0019!!\u0004\u0011\u000be\t)!!\u0003\n\u0007\u0005\u001d!DA\u0002TKR\u00042!IA\u0006\t\u0015\u0019SP1\u0001%\u0011\u001d\ty! a\u0001\u0003#\t\u0011a\u001d\t\u0006E\u0006M\u0011\u0011B\u0005\u0004\u0003\u000f\u0019\u0007bBA\f\u0001\u0011\r\u0011\u0011D\u0001\rg\u0016$\u0018i\u001d&bm\u0006\u001cV\r^\u000b\u0005\u00037\t\t\u0003\u0006\u0003\u0002\u001e\u0005\r\u0002#B\r\u0002\u0006\u0005}\u0001cA\u0011\u0002\"\u001111%!\u0006C\u0002\u0011B\u0001\"a\u0004\u0002\u0016\u0001\u0007\u0011Q\u0005\t\u0006]\u0005\u001d\u0012qD\u0005\u0004\u0003\u000f!\u0001bBA\u0016\u0001\u0011\r\u0011QF\u0001\u0014[V$\u0018M\u00197f\u001b\u0006\u0004\u0018i\u001d&bm\u0006l\u0015\r]\u000b\u0007\u0003_\tI$!\u0010\u0015\t\u0005E\u0012\u0011\t\t\b3\u0005M\u0012qGA\u001e\u0013\r\t)D\u0007\u0002\u0004\u001b\u0006\u0004\bcA\u0011\u0002:\u001111%!\u000bC\u0002\u0011\u00022!IA\u001f\t\u001d\ty$!\u000bC\u0002\u0011\u0012\u0011A\u0011\u0005\t\u0003\u0007\nI\u00031\u0001\u0002F\u0005\tQ\u000eE\u0004c\u0003\u000f\n9$a\u000f\n\u0007\u0005U2\rC\u0004\u0002L\u0001!\u0019!!\u0014\u0002!\u0005\u001c(*\u0019<b\t&\u001cG/[8oCJLXCBA(\u00033\ni\u0006\u0006\u0003\u0002R\u0005}\u0003cB\r\u0002T\u0005]\u00131L\u0005\u0004\u0003+R\"A\u0003#jGRLwN\\1ssB\u0019\u0011%!\u0017\u0005\r\r\nIE1\u0001%!\r\t\u0013Q\f\u0003\b\u0003\u007f\tIE1\u0001%\u0011!\t\u0019%!\u0013A\u0002\u0005\u0005\u0004c\u00022\u0002H\u0005]\u00131\f\u0005\b\u0003K\u0002A1AA4\u00031i\u0017\r]!t\u0015\u00064\u0018-T1q+\u0019\tI'a\u001c\u0002tQ!\u00111NA;!\u001dI\u00121GA7\u0003c\u00022!IA8\t\u0019\u0019\u00131\rb\u0001IA\u0019\u0011%a\u001d\u0005\u000f\u0005}\u00121\rb\u0001I!A\u00111IA2\u0001\u0004\t9\bE\u0004/\u0003s\ni'!\u001d\n\u0007\u0005UB\u0001C\u0004\u0002~\u0001!\u0019!a \u0002-5\f\u0007/Q:KCZ\f7i\u001c8dkJ\u0014XM\u001c;NCB,b!!!\u0002\u0012\u0006UE\u0003BAB\u0003/\u0003\u0002\"!\"\u0002\f\u0006=\u00151S\u0007\u0003\u0003\u000fS1!!#\u001b\u0003)\u0019wN\\2veJ,g\u000e^\u0005\u0005\u0003\u001b\u000b9IA\u0007D_:\u001cWO\u001d:f]Rl\u0015\r\u001d\t\u0004C\u0005EEAB\u0012\u0002|\t\u0007A\u0005E\u0002\"\u0003+#q!a\u0010\u0002|\t\u0007A\u0005\u0003\u0005\u0002D\u0005m\u0004\u0019AAM!!\tY*a(\u0002\u0010\u0006MUBAAO\u0015\r\tI\tB\u0005\u0005\u0003k\tijB\u0004\u0002$\nA\t!!*\u0002\u0015]\u0013\u0018\r]!t\u0015\u00064\u0018\r\u0005\u0003\u0002(\u0006%V\"\u0001\u0002\u0007\r\u0005\u0011\u0001\u0012AAV'\u0015\tIKCAW!\r\t9\u000b\u0001\u0005\t\u0003c\u000bI\u000b\"\u0001\u00024\u00061A(\u001b8jiz\"\"!!*")
public interface WrapAsJava {
    public <A> java.util.Iterator<A> asJavaIterator(Iterator<A> var1);

    public <A> Enumeration<A> asJavaEnumeration(Iterator<A> var1);

    public <A> java.lang.Iterable<A> asJavaIterable(Iterable<A> var1);

    public <A> Collection<A> asJavaCollection(Iterable<A> var1);

    public <A> List<A> bufferAsJavaList(Buffer<A> var1);

    public <A> List<A> mutableSeqAsJavaList(scala.collection.mutable.Seq<A> var1);

    public <A> List<A> seqAsJavaList(Seq<A> var1);

    public <A> java.util.Set<A> mutableSetAsJavaSet(scala.collection.mutable.Set<A> var1);

    public <A> java.util.Set<A> setAsJavaSet(Set<A> var1);

    public <A, B> java.util.Map<A, B> mutableMapAsJavaMap(scala.collection.mutable.Map<A, B> var1);

    public <A, B> Dictionary<A, B> asJavaDictionary(scala.collection.mutable.Map<A, B> var1);

    public <A, B> java.util.Map<A, B> mapAsJavaMap(Map<A, B> var1);

    public <A, B> ConcurrentMap<A, B> mapAsJavaConcurrentMap(scala.collection.concurrent.Map<A, B> var1);
}

