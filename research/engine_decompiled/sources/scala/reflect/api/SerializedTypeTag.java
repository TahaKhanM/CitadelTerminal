/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import java.io.ObjectStreamException;
import scala.Serializable;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.runtime.package$;

@ScalaSignature(bytes="\u0006\u0001)3Q!\u0001\u0002\u0001\r!\u0011\u0011cU3sS\u0006d\u0017N_3e)f\u0004X\rV1h\u0015\t\u0019A!A\u0002ba&T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017mE\u0002\u0001\u00135\u0001\"AC\u0006\u000e\u0003\u0019I!\u0001\u0004\u0004\u0003\r\u0005s\u0017PU3g!\tQa\"\u0003\u0002\u0010\r\ta1+\u001a:jC2L'0\u00192mK\"A\u0011\u0003\u0001BA\u0002\u0013\u00051#\u0001\u0003ua\u0016\u001c7\u0001A\u000b\u0002)A\u0011QCF\u0007\u0002\u0005%\u0011qC\u0001\u0002\f)f\u0004Xm\u0011:fCR|'\u000f\u0003\u0005\u001a\u0001\t\u0005\r\u0011\"\u0001\u001b\u0003!!\b/Z2`I\u0015\fHCA\u000e\u001f!\tQA$\u0003\u0002\u001e\r\t!QK\\5u\u0011\u001dy\u0002$!AA\u0002Q\t1\u0001\u001f\u00132\u0011!\t\u0003A!A!B\u0013!\u0012!\u0002;qK\u000e\u0004\u0003\u0002C\u0012\u0001\u0005\u0003\u0007I\u0011\u0001\u0013\u0002\u0011\r|gn\u0019:fi\u0016,\u0012!\n\t\u0003\u0015\u0019J!a\n\u0004\u0003\u000f\t{w\u000e\\3b]\"A\u0011\u0006\u0001BA\u0002\u0013\u0005!&\u0001\u0007d_:\u001c'/\u001a;f?\u0012*\u0017\u000f\u0006\u0002\u001cW!9q\u0004KA\u0001\u0002\u0004)\u0003\u0002C\u0017\u0001\u0005\u0003\u0005\u000b\u0015B\u0013\u0002\u0013\r|gn\u0019:fi\u0016\u0004\u0003\"B\u0018\u0001\t\u0003\u0001\u0014A\u0002\u001fj]&$h\bF\u00022eM\u0002\"!\u0006\u0001\t\u000bEq\u0003\u0019\u0001\u000b\t\u000b\rr\u0003\u0019A\u0013\t\u000bU\u0002A\u0011\u0002\u001c\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0002\u0013!\u001aA\u0007O\"\u0011\u0007)I4(\u0003\u0002;\r\t1A\u000f\u001b:poN\u0004\"\u0001P!\u000e\u0003uR!AP \u0002\u0005%|'\"\u0001!\u0002\t)\fg/Y\u0005\u0003\u0005v\u0012Qc\u00142kK\u000e$8\u000b\u001e:fC6,\u0005pY3qi&|gnI\u0001<Q\u0011\u0001Q\tS%\u0011\u0005)1\u0015BA$\u0007\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0002\u0001")
public class SerializedTypeTag
implements Serializable {
    public static final long serialVersionUID = 1L;
    private TypeCreator tpec;
    private boolean concrete;

    public TypeCreator tpec() {
        return this.tpec;
    }

    public void tpec_$eq(TypeCreator x$1) {
        this.tpec = x$1;
    }

    public boolean concrete() {
        return this.concrete;
    }

    public void concrete_$eq(boolean x$1) {
        this.concrete = x$1;
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
        return this.concrete() ? ((TypeTags)((Object)package$.MODULE$.universe())).TypeTag().apply((Mirror)((Object)m), this.tpec()) : ((TypeTags)((Object)package$.MODULE$.universe())).WeakTypeTag().apply((Mirror)((Object)m), this.tpec());
    }

    public SerializedTypeTag(TypeCreator tpec, boolean concrete) {
        this.tpec = tpec;
        this.concrete = concrete;
    }
}

