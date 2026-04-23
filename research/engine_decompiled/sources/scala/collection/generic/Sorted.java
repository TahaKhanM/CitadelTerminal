/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Option;
import scala.collection.Iterator;
import scala.collection.SortedSet;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001I4q!\u0001\u0002\u0011\u0002\u0007\u0005\u0011B\u0001\u0004T_J$X\r\u001a\u0006\u0003\u0007\u0011\tqaZ3oKJL7M\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001!F\u0002\u000bC9\u001a\"\u0001A\u0006\u0011\u00051iQ\"\u0001\u0004\n\u000591!AB!osJ+g\rC\u0003\u0011\u0001\u0011\u0005\u0011#\u0001\u0004%S:LG\u000f\n\u000b\u0002%A\u0011AbE\u0005\u0003)\u0019\u0011A!\u00168ji\")a\u0003\u0001D\u0001/\u0005AqN\u001d3fe&tw-F\u0001\u0019!\rIBd\b\b\u0003\u0019iI!a\u0007\u0004\u0002\u000fA\f7m[1hK&\u0011QD\b\u0002\t\u001fJ$WM]5oO*\u00111D\u0002\t\u0003A\u0005b\u0001\u0001B\u0003#\u0001\t\u00071EA\u0001L#\t!s\u0005\u0005\u0002\rK%\u0011aE\u0002\u0002\b\u001d>$\b.\u001b8h!\ta\u0001&\u0003\u0002*\r\t\u0019\u0011I\\=\t\u000b-\u0002a\u0011\u0003\u0017\u0002\tI,\u0007O]\u000b\u0002[A\u0011\u0001E\f\u0003\u0007_\u0001!)\u0019\u0001\u0019\u0003\tQC\u0017n]\t\u0003IE\u0002BA\r\u0001 [5\t!\u0001C\u00035\u0001\u0019\u0005Q'\u0001\u0004lKf\u001cV\r^\u000b\u0002mA\u0019q\u0007O\u0010\u000e\u0003\u0011I!!\u000f\u0003\u0003\u0013M{'\u000f^3e'\u0016$\b\"B\u001e\u0001\r\u0003a\u0014\u0001\u00034jeN$8*Z=\u0016\u0003}AQA\u0010\u0001\u0007\u0002q\nq\u0001\\1ti.+\u0017\u0010C\u0003A\u0001\u0011\u0005\u0011)A\u0004d_6\u0004\u0018M]3\u0015\u0007\t+u\t\u0005\u0002\r\u0007&\u0011AI\u0002\u0002\u0004\u0013:$\b\"\u0002$@\u0001\u0004y\u0012AA61\u0011\u0015Au\b1\u0001 \u0003\tY\u0017\u0007C\u0003K\u0001\u0019\u00051*A\u0005sC:<W-S7qYR\u0019Q\u0006T)\t\u000b5K\u0005\u0019\u0001(\u0002\t\u0019\u0014x.\u001c\t\u0004\u0019={\u0012B\u0001)\u0007\u0005\u0019y\u0005\u000f^5p]\")!+\u0013a\u0001\u001d\u0006)QO\u001c;jY\")Q\n\u0001C\u0001)R\u0011Q&\u0016\u0005\u0006\u001bN\u0003\ra\b\u0005\u0006%\u0002!\ta\u0016\u000b\u0003[aCQA\u0015,A\u0002}AQA\u0017\u0001\u0005\u0002m\u000bQA]1oO\u0016$2!\f/^\u0011\u0015i\u0015\f1\u0001 \u0011\u0015\u0011\u0016\f1\u0001 \u0011\u0015y\u0006\u0001\"\u0001a\u0003\t!x\u000e\u0006\u0002.C\")qL\u0018a\u0001?!)1\r\u0001D\u0001I\u0006\u00012.Z=t\u0013R,'/\u0019;pe\u001a\u0013x.\u001c\u000b\u0003K\"\u00042a\u000e4 \u0013\t9GA\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0011\u0015I'\r1\u0001 \u0003\u0015\u0019H/\u0019:u\u0011\u0015Y\u0007\u0001\"\u0005m\u0003\u0019A\u0017m]!mYR\u0011Q\u000e\u001d\t\u0003\u00199L!a\u001c\u0004\u0003\u000f\t{w\u000e\\3b]\")\u0011O\u001ba\u0001K\u0006\t!\u000e")
public interface Sorted<K, This extends Sorted<K, This>> {
    public Ordering<K> ordering();

    public This repr();

    public SortedSet<K> keySet();

    public K firstKey();

    public K lastKey();

    public int compare(K var1, K var2);

    public This rangeImpl(Option<K> var1, Option<K> var2);

    public This from(K var1);

    public This until(K var1);

    public This range(K var1, K var2);

    public This to(K var1);

    public Iterator<K> keysIteratorFrom(K var1);

    public boolean hasAll(Iterator<K> var1);
}

