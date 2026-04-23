/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.Function1;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.collection.immutable.Set;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.macros.NonemptyAttachments;
import scala.reflect.package$;

@ScalaSignature(bytes="\u0006\u0001}4Q!\u0001\u0002\u0002\u0002%\u00111\"\u0011;uC\u000eDW.\u001a8ug*\u00111\u0001B\u0001\u0007[\u0006\u001c'o\\:\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u000b!\tYA\"D\u0001\u0007\u0013\tiaA\u0001\u0004B]f\u0014VM\u001a\u0005\u0006\u001f\u0001!\t\u0001E\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003E\u0001\"A\u0005\u0001\u000e\u0003\t!Q\u0001\u0006\u0001\u0003\u0002U\u00111\u0001U8t#\t1\u0012\u0004\u0005\u0002\f/%\u0011\u0001D\u0002\u0002\u0005\u001dVdG\u000e\u0005\u0002\f5%\u00111D\u0002\u0002\u0004\u0003:L\b\"B\u000f\u0001\r\u0003q\u0012a\u00019pgV\tq\u0004\u0005\u0002!'5\t\u0001\u0001C\u0003#\u0001\u0019\u00051%A\u0004xSRD\u0007k\\:\u0015\u0005\u0011B#CA\u0013\u0012\r\u00111\u0003\u0001\u0001\u0013\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0006\tQ)\u0003e\b\u0005\u0006S\u0005\u0002\raH\u0001\u0007]\u0016<\bk\\:\t\u000b-\u0002A\u0011\u0001\u0017\u0002\u0007\u0005dG.F\u0001.!\rq\u0013'\u0007\b\u0003\u0017=J!\u0001\r\u0004\u0002\rA\u0013X\rZ3g\u0013\t\u00114GA\u0002TKRT!\u0001\r\u0004\t\u000bU\u0002A\u0011\u0002\u001c\u0002\u00155\fGo\u00195fgR\u000bw-\u0006\u00028\tR\u0011\u0001H\u0013\u000b\u0003sq\u0002\"a\u0003\u001e\n\u0005m2!a\u0002\"p_2,\u0017M\u001c\u0005\b{Q\n\t\u0011q\u0001?\u0003))g/\u001b3f]\u000e,G%\r\t\u0004\u007f\u0001\u0013U\"\u0001\u0003\n\u0005\u0005#!\u0001C\"mCN\u001cH+Y4\u0011\u0005\r#E\u0002\u0001\u0003\u0006\u000bR\u0012\rA\u0012\u0002\u0002)F\u0011q)\u0007\t\u0003\u0017!K!!\u0013\u0004\u0003\u000f9{G\u000f[5oO\")1\n\u000ea\u00013\u0005)A-\u0019;v[\")Q\n\u0001C\u0001\u001d\u0006\u0019q-\u001a;\u0016\u0005=#FC\u0001)V!\rY\u0011kU\u0005\u0003%\u001a\u0011aa\u00149uS>t\u0007CA\"U\t\u0015)EJ1\u0001G\u0011\u001d1F*!AA\u0004]\u000b!\"\u001a<jI\u0016t7-\u001a\u00133!\ry\u0004i\u0015\u0005\u00063\u0002!\tAW\u0001\tG>tG/Y5ogV\u00111\f\u0019\u000b\u0003sqCq!\u0018-\u0002\u0002\u0003\u000fa,\u0001\u0006fm&$WM\\2fIM\u00022a\u0010!`!\t\u0019\u0005\rB\u0003F1\n\u0007a\tC\u0003c\u0001\u0011\u00051-\u0001\u0004va\u0012\fG/Z\u000b\u0003I6$\"!\u001a8\u0015\u0005\u0019L'CA4\u0012\r\u00111\u0003\u0001\u00014\u0006\tQ9\u0007e\b\u0005\bU\u0006\f\t\u0011q\u0001l\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0004\u007f\u0001c\u0007CA\"n\t\u0015)\u0015M1\u0001G\u0011\u0015y\u0017\r1\u0001m\u0003)\tG\u000f^1dQ6,g\u000e\u001e\u0005\u0006c\u0002!\tA]\u0001\u0007e\u0016lwN^3\u0016\u0005M\\HC\u0001;x%\t)\u0018C\u0002\u0003'\u0001\u0001!X\u0001\u0002\u000bvA}Aq\u0001\u001f9\u0002\u0002\u0003\u000f\u00110\u0001\u0006fm&$WM\\2fIU\u00022a\u0010!{!\t\u00195\u0010B\u0003Fa\n\u0007a\tC\u0003~\u0001\u0011\u0005a0A\u0004jg\u0016k\u0007\u000f^=\u0016\u0003e\u0002")
public abstract class Attachments {
    public abstract Object pos();

    public abstract Attachments withPos(Object var1);

    public Set<Object> all() {
        return Predef$.MODULE$.Set().empty();
    }

    public <T> boolean scala$reflect$macros$Attachments$$matchesTag(Object datum, ClassTag<T> evidence$1) {
        return package$.MODULE$.classTag(evidence$1).runtimeClass().isInstance(datum);
    }

    public <T> Option<T> get(ClassTag<T> evidence$2) {
        return this.all().find((Function1<Object, Object>)((Object)new Serializable(this, evidence$2){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Attachments $outer;
            private final ClassTag evidence$2$1;

            public final boolean apply(Object datum) {
                return this.$outer.scala$reflect$macros$Attachments$$matchesTag(datum, this.evidence$2$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$2$1 = evidence$2$1;
            }
        }));
    }

    public <T> boolean contains(ClassTag<T> evidence$3) {
        return !this.isEmpty() && this.all().exists((Function1<Object, Object>)((Object)new Serializable(this, evidence$3){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Attachments $outer;
            private final ClassTag evidence$3$1;

            public final boolean apply(Object datum) {
                return this.$outer.scala$reflect$macros$Attachments$$matchesTag(datum, this.evidence$3$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$3$1 = evidence$3$1;
            }
        }));
    }

    public <T> Attachments update(T attachment, ClassTag<T> evidence$4) {
        return new NonemptyAttachments<Object>(this.pos(), (Set)this.remove(evidence$4).all().$plus(attachment));
    }

    public <T> Attachments remove(ClassTag<T> evidence$5) {
        Set newAll = (Set)this.all().filterNot((Function1<Object, Object>)((Object)new Serializable(this, evidence$5){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Attachments $outer;
            private final ClassTag evidence$5$1;

            public final boolean apply(Object datum) {
                return this.$outer.scala$reflect$macros$Attachments$$matchesTag(datum, this.evidence$5$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.evidence$5$1 = evidence$5$1;
            }
        }));
        return newAll.isEmpty() ? (Attachments)this.pos() : new NonemptyAttachments<Object>(this.pos(), newAll);
    }

    public boolean isEmpty() {
        return true;
    }
}

