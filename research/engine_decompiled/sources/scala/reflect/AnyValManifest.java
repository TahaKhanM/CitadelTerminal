/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect;

import scala.Option;
import scala.collection.immutable.List;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.WrappedArray;
import scala.reflect.ClassManifestDeprecatedApis$class;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$class;
import scala.reflect.Manifest;
import scala.reflect.Manifest$class;
import scala.reflect.ScalaSignature;
import scala.reflect.package$;
import scala.runtime.BoxedUnit;

@ScalaSignature(bytes="\u0006\u0001\t4Q!\u0001\u0002\u0002\u0002\u001d\u0011a\"\u00118z-\u0006dW*\u00198jM\u0016\u001cHO\u0003\u0002\u0004\t\u00059!/\u001a4mK\u000e$(\"A\u0003\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011\u0001bE\n\u0005\u0001%iA\u0004\u0005\u0002\u000b\u00175\tA!\u0003\u0002\r\t\t1\u0011I\\=SK\u001a\u00042AD\b\u0012\u001b\u0005\u0011\u0011B\u0001\t\u0003\u0005!i\u0015M\\5gKN$\bC\u0001\n\u0014\u0019\u0001!Q\u0001\u0006\u0001C\u0002U\u0011\u0011\u0001V\t\u0003-e\u0001\"AC\f\n\u0005a!!a\u0002(pi\"Lgn\u001a\t\u0003\u0015iI!a\u0007\u0003\u0003\r\u0005s\u0017PV1m!\tQQ$\u0003\u0002\u001f\t\t1Q)];bYND\u0001\u0002\t\u0001\u0003\u0006\u0004%\t%I\u0001\ti>\u001cFO]5oOV\t!\u0005\u0005\u0002$M9\u0011!\u0002J\u0005\u0003K\u0011\ta\u0001\u0015:fI\u00164\u0017BA\u0014)\u0005\u0019\u0019FO]5oO*\u0011Q\u0005\u0002\u0005\tU\u0001\u0011\t\u0011)A\u0005E\u0005IAo\\*ue&tw\r\t\u0005\u0006Y\u0001!\t!L\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00059z\u0003c\u0001\b\u0001#!)\u0001e\u000ba\u0001E!)\u0011\u0007\u0001C!e\u0005\u0001B\u0005\\3tg\u0012\u001aw\u000e\\8oI1,7o\u001d\u000b\u0003gY\u0002\"A\u0003\u001b\n\u0005U\"!a\u0002\"p_2,\u0017M\u001c\u0005\u0006oA\u0002\r\u0001O\u0001\u0005i\"\fG\u000f\r\u0002:\u0003B\u0019!(\u0010!\u000f\u00059Y\u0014B\u0001\u001f\u0003\u0003\u001d\u0001\u0018mY6bO\u0016L!AP \u0003\u001b\rc\u0017m]:NC:Lg-Z:u\u0015\ta$\u0001\u0005\u0002\u0013\u0003\u0012I!INA\u0001\u0002\u0003\u0015\ta\u0011\u0002\u0004?\u0012\u0012\u0014C\u0001\fE!\tQQ)\u0003\u0002G\t\t\u0019\u0011I\\=\t\u000b!\u0003A\u0011I%\u0002\u0011\r\fg.R9vC2$\"a\r&\t\u000b-;\u0005\u0019\u0001#\u0002\u000b=$\b.\u001a:\t\u000b5\u0003A\u0011\t(\u0002\r\u0015\fX/\u00197t)\t\u0019t\nC\u00038\u0019\u0002\u0007A\tC\u0004R\u0001\t\u0007I\u0011\t*\u0002\u0011!\f7\u000f[\"pI\u0016,\u0012a\u0015\t\u0003\u0015QK!!\u0016\u0003\u0003\u0007%sG\u000f\u0003\u0004X\u0001\u0001\u0006IaU\u0001\nQ\u0006\u001c\bnQ8eK\u0002B#AV-\u0011\u0005)Q\u0016BA.\u0005\u0005%!(/\u00198tS\u0016tG\u000f\u000b\u0003\u0001;\u0002\f\u0007C\u0001\u0006_\u0013\tyFA\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t\u0011\u0001")
public abstract class AnyValManifest<T>
implements Manifest<T> {
    public static final long serialVersionUID = 1L;
    private final String toString;
    private final transient int hashCode;

    @Override
    public List<Manifest<?>> typeArguments() {
        return Manifest$class.typeArguments(this);
    }

    @Override
    public Manifest<Object> arrayManifest() {
        return Manifest$class.arrayManifest(this);
    }

    @Override
    public ClassTag<Object> wrap() {
        return ClassTag$class.wrap(this);
    }

    @Override
    public Object newArray(int len) {
        return ClassTag$class.newArray(this, len);
    }

    @Override
    public Option<T> unapply(Object x) {
        return ClassTag$class.unapply((ClassTag)this, x);
    }

    @Override
    public Option<T> unapply(byte x) {
        return ClassTag$class.unapply((ClassTag)this, x);
    }

    @Override
    public Option<T> unapply(short x) {
        return ClassTag$class.unapply((ClassTag)this, x);
    }

    @Override
    public Option<T> unapply(char x) {
        return ClassTag$class.unapply((ClassTag)this, x);
    }

    @Override
    public Option<T> unapply(int x) {
        return ClassTag$class.unapply((ClassTag)this, x);
    }

    @Override
    public Option<T> unapply(long x) {
        return ClassTag$class.unapply((ClassTag)this, x);
    }

    @Override
    public Option<T> unapply(float x) {
        return ClassTag$class.unapply((ClassTag)this, x);
    }

    @Override
    public Option<T> unapply(double x) {
        return ClassTag$class.unapply((ClassTag)this, x);
    }

    @Override
    public Option<T> unapply(boolean x) {
        return ClassTag$class.unapply((ClassTag)this, x);
    }

    @Override
    public Option<T> unapply(BoxedUnit x) {
        return ClassTag$class.unapply((ClassTag)this, x);
    }

    @Override
    public Class<?> erasure() {
        return ClassManifestDeprecatedApis$class.erasure(this);
    }

    @Override
    public boolean $greater$colon$greater(ClassTag<?> that) {
        return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
    }

    @Override
    public <T> Class<Object> arrayClass(Class<?> tp) {
        return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
    }

    @Override
    public Object[] newArray2(int len) {
        return ClassManifestDeprecatedApis$class.newArray2(this, len);
    }

    @Override
    public Object[][] newArray3(int len) {
        return ClassManifestDeprecatedApis$class.newArray3(this, len);
    }

    @Override
    public Object[][][] newArray4(int len) {
        return ClassManifestDeprecatedApis$class.newArray4(this, len);
    }

    @Override
    public Object[][][][] newArray5(int len) {
        return ClassManifestDeprecatedApis$class.newArray5(this, len);
    }

    @Override
    public WrappedArray<T> newWrappedArray(int len) {
        return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
    }

    @Override
    public ArrayBuilder<T> newArrayBuilder() {
        return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
    }

    @Override
    public String argString() {
        return ClassManifestDeprecatedApis$class.argString(this);
    }

    @Override
    public String toString() {
        return this.toString;
    }

    @Override
    public boolean $less$colon$less(ClassTag<?> that) {
        return that == this || that == package$.MODULE$.Manifest().Any() || that == package$.MODULE$.Manifest().AnyVal();
    }

    @Override
    public boolean canEqual(Object other) {
        boolean bl = other instanceof AnyValManifest;
        return bl;
    }

    @Override
    public boolean equals(Object that) {
        return this == that;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    public AnyValManifest(String toString2) {
        this.toString = toString2;
        ClassManifestDeprecatedApis$class.$init$(this);
        ClassTag$class.$init$(this);
        Manifest$class.$init$(this);
        this.hashCode = System.identityHashCode(this);
    }
}

