/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import scala.Function3;
import scala.Predef$;
import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.immutable.Iterable;
import scala.collection.immutable.PagedSeq;
import scala.collection.mutable.StringBuilder;
import scala.io.Source;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.runtime.BooleanRef;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

public final class PagedSeq$ {
    public static final PagedSeq$ MODULE$;
    private final int UndeterminedEnd;

    static {
        new PagedSeq$();
    }

    public final int UndeterminedEnd() {
        return Integer.MAX_VALUE;
    }

    public <T> PagedSeq<T> fromIterator(Iterator<T> source, ClassTag<T> evidence$1) {
        return new PagedSeq<T>((Function3<Object, Object, Object, Object>)((Object)new Serializable(source){
            public static final long serialVersionUID = 0L;
            private final Iterator source$1;

            public final int apply(Object data, int start, int len) {
                int i;
                for (i = 0; i < len && this.source$1.hasNext(); ++i) {
                    ScalaRunTime$.MODULE$.array_update(data, start + i, this.source$1.next());
                }
                return i == 0 ? -1 : i;
            }
            {
                this.source$1 = source$1;
            }
        }), evidence$1);
    }

    public <T> PagedSeq<T> fromIterable(Iterable<T> source, ClassTag<T> evidence$2) {
        return this.fromIterator(source.iterator(), evidence$2);
    }

    public PagedSeq<Object> fromStrings(Iterator<String> source) {
        ObjectRef<String> current = ObjectRef.create("");
        return new PagedSeq<Object>((Function3<Object, Object, Object, Object>)((Object)new Serializable(source, current){
            public static final long serialVersionUID = 0L;
            private final Iterator source$2;
            private final ObjectRef current$1;

            public final int apply(char[] x$1, int x$2, int x$3) {
                return PagedSeq$.MODULE$.scala$collection$immutable$PagedSeq$$more$1(x$1, x$2, x$3, this.source$2, this.current$1);
            }
            {
                this.source$2 = source$2;
                this.current$1 = current$1;
            }
        }), ClassTag$.MODULE$.Char());
    }

    public PagedSeq<Object> fromStrings(Iterable<String> source) {
        return this.fromStrings(source.iterator());
    }

    public PagedSeq<Object> fromLines(Iterator<String> source) {
        BooleanRef isFirst = BooleanRef.create(true);
        return this.fromStrings(source.map(new Serializable(isFirst){
            public static final long serialVersionUID = 0L;
            private final BooleanRef isFirst$1;

            public final String apply(String line) {
                String string2;
                if (this.isFirst$1.elem) {
                    this.isFirst$1.elem = false;
                    string2 = line;
                } else {
                    string2 = new StringBuilder().append((Object)"\n").append((Object)line).toString();
                }
                return string2;
            }
            {
                this.isFirst$1 = isFirst$1;
            }
        }));
    }

    public PagedSeq<Object> fromLines(Iterable<String> source) {
        return this.fromLines(source.iterator());
    }

    public PagedSeq<Object> fromReader(Reader source) {
        return new PagedSeq<Object>((Function3<Object, Object, Object, Object>)((Object)new Serializable(source){
            public static final long serialVersionUID = 0L;
            private final Reader source$3;

            public final int apply(char[] x$4, int x$5, int x$6) {
                return this.source$3.read(x$4, x$5, x$6);
            }
            {
                this.source$3 = source$3;
            }
        }), ClassTag$.MODULE$.Char());
    }

    public PagedSeq<Object> fromFile(File source) {
        return this.fromReader(new FileReader(source));
    }

    public PagedSeq<Object> fromFile(String source) {
        return this.fromFile(new File(source));
    }

    public PagedSeq<Object> fromSource(Source source) {
        return this.fromLines(source.getLines());
    }

    public final int scala$collection$immutable$PagedSeq$$more$1(char[] data, int start, int len, Iterator source$2, ObjectRef current$1) {
        int n;
        block4: {
            while (true) {
                if (((String)current$1.elem).length() != 0) {
                    int n2 = ((String)current$1.elem).length();
                    Predef$ predef$ = Predef$.MODULE$;
                    int cnt = RichInt$.MODULE$.min$extension(n2, len);
                    ((String)current$1.elem).getChars(0, cnt, data, start);
                    current$1.elem = ((String)current$1.elem).substring(cnt);
                    if (cnt == len) {
                        n = cnt;
                    } else {
                        int n3 = this.scala$collection$immutable$PagedSeq$$more$1(data, start + cnt, len - cnt, source$2, current$1);
                        Predef$ predef$2 = Predef$.MODULE$;
                        n = RichInt$.MODULE$.max$extension(n3, 0) + cnt;
                    }
                    break block4;
                }
                if (!source$2.hasNext()) break;
                current$1.elem = (String)source$2.next();
            }
            n = -1;
        }
        return n;
    }

    private PagedSeq$() {
        MODULE$ = this;
    }
}

