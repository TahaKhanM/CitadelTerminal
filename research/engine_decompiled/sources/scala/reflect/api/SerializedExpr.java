/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import java.io.ObjectStreamException;
import scala.Serializable;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TreeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.runtime.package$;

@ScalaSignature(bytes="\u0006\u0001Y4Q!\u0001\u0002\u0001\r!\u0011abU3sS\u0006d\u0017N_3e\u000bb\u0004(O\u0003\u0002\u0004\t\u0005\u0019\u0011\r]5\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mCN\u0019\u0001!C\u0007\u0011\u0005)YQ\"\u0001\u0004\n\u000511!AB!osJ+g\r\u0005\u0002\u000b\u001d%\u0011qB\u0002\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\t#\u0001\u0011\t\u0019!C\u0001'\u0005)AO]3fG\u000e\u0001Q#\u0001\u000b\u0011\u0005U1R\"\u0001\u0002\n\u0005]\u0011!a\u0003+sK\u0016\u001c%/Z1u_JD\u0001\"\u0007\u0001\u0003\u0002\u0004%\tAG\u0001\niJ,WmY0%KF$\"a\u0007\u0010\u0011\u0005)a\u0012BA\u000f\u0007\u0005\u0011)f.\u001b;\t\u000f}A\u0012\u0011!a\u0001)\u0005\u0019\u0001\u0010J\u0019\t\u0011\u0005\u0002!\u0011!Q!\nQ\ta\u0001\u001e:fK\u000e\u0004\u0003\u0002C\u0012\u0001\u0005\u0003\u0007I\u0011\u0001\u0013\u0002\u0007Q\fw-F\u0001&a\t1s\bE\u0002(sur!\u0001\u000b\u001c\u000f\u0005%\u001adB\u0001\u00162\u001d\tY\u0003G\u0004\u0002-_5\tQF\u0003\u0002/%\u00051AH]8pizJ\u0011aB\u0005\u0003\u000b\u0019I!A\r\u0003\u0002\u000fI,h\u000e^5nK&\u0011A'N\u0001\ba\u0006\u001c7.Y4f\u0015\t\u0011D!\u0003\u00028q\u0005AQO\\5wKJ\u001cXM\u0003\u00025k%\u0011!h\u000f\u0002\f/\u0016\f7\u000eV=qKR\u000bw-\u0003\u0002=\u0005\tAA+\u001f9f)\u0006<7\u000f\u0005\u0002?\u007f1\u0001A!\u0003!B\u0003\u0003\u0005\tQ!\u0001H\u0005\ryF%\u000e\u0005\t\u0005\u0002\u0011\t\u0011)Q\u0005\u0007\u0006!A/Y4!a\t!e\tE\u0002(s\u0015\u0003\"A\u0010$\u0005\u0013\u0001\u000b\u0015\u0011!A\u0001\u0006\u00039\u0015C\u0001%L!\tQ\u0011*\u0003\u0002K\r\t9aj\u001c;iS:<\u0007C\u0001\u0006M\u0013\tieAA\u0002B]fD\u0001b\u0014\u0001\u0003\u0002\u0004%\t\u0001U\u0001\bi\u0006<w\fJ3r)\tY\u0012\u000bC\u0004 \u001d\u0006\u0005\t\u0019\u0001*1\u0005M+\u0006cA\u0014:)B\u0011a(\u0016\u0003\n\u0001\u0006\u000b\t\u0011!A\u0003\u0002\u001dCQa\u0016\u0001\u0005\u0002a\u000ba\u0001P5oSRtDcA-[7B\u0011Q\u0003\u0001\u0005\u0006#Y\u0003\r\u0001\u0006\u0005\u0006GY\u0003\r\u0001\u0018\u0019\u0003;~\u00032aJ\u001d_!\tqt\fB\u0005A7\u0006\u0005\t\u0011!B\u0001\u000f\")\u0011\r\u0001C\u0005E\u0006Y!/Z1e%\u0016\u001cx\u000e\u001c<f)\u0005I\u0001f\u00011e_B\u0019!\"Z4\n\u0005\u00194!A\u0002;ie><8\u000f\u0005\u0002i[6\t\u0011N\u0003\u0002kW\u0006\u0011\u0011n\u001c\u0006\u0002Y\u0006!!.\u0019<b\u0013\tq\u0017NA\u000bPE*,7\r^*ue\u0016\fW.\u0012=dKB$\u0018n\u001c8$\u0003\u001dDC\u0001A9ukB\u0011!B]\u0005\u0003g\u001a\u0011\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0003\u0005\u0001")
public class SerializedExpr
implements Serializable {
    public static final long serialVersionUID = 1L;
    private TreeCreator treec;
    private TypeTags.WeakTypeTag<?> tag;

    public TreeCreator treec() {
        return this.treec;
    }

    public void treec_$eq(TreeCreator x$1) {
        this.treec = x$1;
    }

    public TypeTags.WeakTypeTag<?> tag() {
        return this.tag;
    }

    public void tag_$eq(TypeTags.WeakTypeTag<?> x$1) {
        this.tag = x$1;
    }

    private Object readResolve() throws ObjectStreamException {
        ClassLoader classLoader;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        catch (SecurityException securityException) {
            classLoader = null;
        }
        ClassLoader loader = classLoader;
        JavaUniverse.JavaMirror m = package$.MODULE$.universe().runtimeMirror(loader);
        return ((Exprs)((Object)package$.MODULE$.universe())).Expr().apply((Mirror)((Object)m), this.treec(), this.tag().in((Mirror)((Object)m)));
    }

    public SerializedExpr(TreeCreator treec, TypeTags.WeakTypeTag<?> tag) {
        this.treec = treec;
        this.tag = tag;
    }
}

