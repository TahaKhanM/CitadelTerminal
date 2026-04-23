/*
 * Decompiled with CFR 0.152.
 */
package scala.ref;

import scala.Option;
import scala.Proxy;
import scala.ref.Reference;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u000113q!\u0001\u0002\u0011\u0002\u0007\u0005qA\u0001\tSK\u001a,'/\u001a8dK^\u0013\u0018\r\u001d9fe*\u00111\u0001B\u0001\u0004e\u00164'\"A\u0003\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011\u0001bE\n\u0005\u0001%i\u0011\u0004\u0005\u0002\u000b\u00175\tA!\u0003\u0002\r\t\t1\u0011I\\=SK\u001a\u00042AD\b\u0012\u001b\u0005\u0011\u0011B\u0001\t\u0003\u0005%\u0011VMZ3sK:\u001cW\r\u0005\u0002\u0013'1\u0001AA\u0002\u000b\u0001\t\u000b\u0007QCA\u0001U#\t1\u0012\u0002\u0005\u0002\u000b/%\u0011\u0001\u0004\u0002\u0002\b\u001d>$\b.\u001b8h!\tQ!$\u0003\u0002\u001c\t\t)\u0001K]8ys\")Q\u0004\u0001C\u0001=\u00051A%\u001b8ji\u0012\"\u0012a\b\t\u0003\u0015\u0001J!!\t\u0003\u0003\tUs\u0017\u000e\u001e\u0005\bG\u0001\u0011\rQ\"\u0001%\u0003))h\u000eZ3sYfLgnZ\u000b\u0002KA\u0012ae\f\t\u0004O5rS\"\u0001\u0015\u000b\u0005\rI#B\u0001\u0016,\u0003\u0011a\u0017M\\4\u000b\u00031\nAA[1wC&\u0011\u0001\u0003\u000b\t\u0003%=\"\u0011\u0002\r\u0012\u0002\u0002\u0003\u0005)\u0011A\u0019\u0003\u0007}#\u0013'\u0005\u0002\u0017#!)1\u0007\u0001C!i\u0005\u0019q-\u001a;\u0016\u0003U\u00022A\u0003\u001c\u0012\u0013\t9DA\u0001\u0004PaRLwN\u001c\u0005\u0006s\u0001!\tAO\u0001\u0006CB\u0004H.\u001f\u000b\u0002#!)A\b\u0001C\u0001=\u0005)1\r\\3be\")a\b\u0001C\u0001\u007f\u00059QM\\9vKV,G#\u0001!\u0011\u0005)\t\u0015B\u0001\"\u0005\u0005\u001d\u0011un\u001c7fC:DQ\u0001\u0012\u0001\u0005\u0002}\n!\"[:F]F,X-^3e\u0011\u00151\u0005\u0001\"\u0001H\u0003\u0011\u0019X\r\u001c4\u0016\u0003!\u0003$!S&\u0011\u0007\u001dj#\n\u0005\u0002\u0013\u0017\u0012I\u0001'RA\u0001\u0002\u0003\u0015\t!\r")
public interface ReferenceWrapper<T>
extends Reference<T>,
Proxy {
    public java.lang.ref.Reference<? extends T> underlying();

    @Override
    public Option<T> get();

    @Override
    public T apply();

    @Override
    public void clear();

    @Override
    public boolean enqueue();

    @Override
    public boolean isEnqueued();

    @Override
    public java.lang.ref.Reference<? extends T> self();
}

