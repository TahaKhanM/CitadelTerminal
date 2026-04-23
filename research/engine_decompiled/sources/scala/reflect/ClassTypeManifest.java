/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect;

import scala.Option;
import scala.collection.immutable.List;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WrappedArray;
import scala.reflect.ClassManifestDeprecatedApis$class;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$class;
import scala.reflect.OptManifest;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(bytes="\u0006\u0001E4A!\u0001\u0002\u0005\u000f\t\t2\t\\1tgRK\b/Z'b]&4Wm\u001d;\u000b\u0005\r!\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000b\u0005)1oY1mC\u000e\u0001QC\u0001\u0005\u0018'\r\u0001\u0011\"\u0004\t\u0003\u0015-i\u0011\u0001B\u0005\u0003\u0019\u0011\u0011a!\u00118z%\u00164\u0007c\u0001\b\u0013+9\u0011q\u0002E\u0007\u0002\u0005%\u0011\u0011CA\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0019BCA\u0007DY\u0006\u001c8/T1oS\u001a,7\u000f\u001e\u0006\u0003#\t\u0001\"AF\f\r\u0001\u0011)\u0001\u0004\u0001b\u00013\t\tA+\u0005\u0002\u001b;A\u0011!bG\u0005\u00039\u0011\u0011qAT8uQ&tw\r\u0005\u0002\u000b=%\u0011q\u0004\u0002\u0002\u0004\u0003:L\b\u0002C\u0011\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0012\u0002\rA\u0014XMZ5y!\rQ1%J\u0005\u0003I\u0011\u0011aa\u00149uS>t\u0007G\u0001\u0014+!\ryq%K\u0005\u0003Q\t\u00111b\u00149u\u001b\u0006t\u0017NZ3tiB\u0011aC\u000b\u0003\nW\u0001\n\t\u0011!A\u0003\u0002e\u0011Aa\u0018\u00133o!AQ\u0006\u0001BC\u0002\u0013\u0005a&\u0001\u0007sk:$\u0018.\\3DY\u0006\u001c8/F\u00010a\t\u0001\u0014\bE\u00022maj\u0011A\r\u0006\u0003gQ\nA\u0001\\1oO*\tQ'\u0001\u0003kCZ\f\u0017BA\u001c3\u0005\u0015\u0019E.Y:t!\t1\u0012\bB\u0005;w\u0005\u0005\t\u0011!B\u00013\t!q\f\n\u001a9\u0011!a\u0004A!A!\u0002\u0013i\u0014!\u0004:v]RLW.Z\"mCN\u001c\b\u0005\r\u0002?\u0001B\u0019\u0011GN \u0011\u0005Y\u0001E!\u0003\u001e<\u0003\u0003\u0005\tQ!\u0001\u001a\u0011!\u0011\u0005A!b\u0001\n\u0003\u001a\u0015!\u0004;za\u0016\f%oZ;nK:$8/F\u0001E!\r)uI\u0013\b\u0003\u0015\u0019K!!\u0005\u0003\n\u0005!K%\u0001\u0002'jgRT!!\u0005\u00031\u0005-k\u0005cA\b(\u0019B\u0011a#\u0014\u0003\n\u001d>\u000b\t\u0011!A\u0003\u0002e\u0011Aa\u0018\u00133s!A\u0001\u000b\u0001B\u0001B\u0003%\u0011+\u0001\busB,\u0017I]4v[\u0016tGo\u001d\u0011\u0011\u0007\u0015;%\u000b\r\u0002T+B\u0019qb\n+\u0011\u0005Y)F!\u0003(P\u0003\u0003\u0005\tQ!\u0001\u001a\u0011\u00159\u0006\u0001\"\u0001Y\u0003\u0019a\u0014N\\5u}Q!\u0011L\u00171f!\ry\u0001!\u0006\u0005\u0006CY\u0003\ra\u0017\t\u0004\u0015\rb\u0006GA/`!\ryqE\u0018\t\u0003-}#\u0011b\u000b.\u0002\u0002\u0003\u0005)\u0011A\r\t\u000b52\u0006\u0019A11\u0005\t$\u0007cA\u00197GB\u0011a\u0003\u001a\u0003\nu\u0001\f\t\u0011!A\u0003\u0002eAQA\u0011,A\u0002\u0019\u00042!R$ha\tA'\u000eE\u0002\u0010O%\u0004\"A\u00066\u0005\u00139+\u0017\u0011!A\u0001\u0006\u0003I\u0002\"\u00027\u0001\t\u0003j\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u00039\u0004\"!M8\n\u0005A\u0014$AB*ue&tw\r")
public class ClassTypeManifest<T>
implements ClassTag<T> {
    private final Option<OptManifest<?>> prefix;
    private final Class<?> runtimeClass;
    private final List<OptManifest<?>> typeArguments;

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
    public boolean canEqual(Object x) {
        return ClassTag$class.canEqual(this, x);
    }

    @Override
    public boolean equals(Object x) {
        return ClassTag$class.equals(this, x);
    }

    @Override
    public int hashCode() {
        return ClassTag$class.hashCode(this);
    }

    @Override
    public Class<?> erasure() {
        return ClassManifestDeprecatedApis$class.erasure(this);
    }

    @Override
    public boolean $less$colon$less(ClassTag<?> that) {
        return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
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
    public ClassTag<Object> arrayManifest() {
        return ClassManifestDeprecatedApis$class.arrayManifest(this);
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
    public Class<?> runtimeClass() {
        return this.runtimeClass;
    }

    @Override
    public List<OptManifest<?>> typeArguments() {
        return this.typeArguments;
    }

    @Override
    public String toString() {
        return new StringBuilder().append((Object)(this.prefix.isEmpty() ? "" : new StringBuilder().append((Object)this.prefix.get().toString()).append((Object)"#").toString())).append((Object)(this.runtimeClass().isArray() ? "Array" : this.runtimeClass().getName())).append((Object)this.argString()).toString();
    }

    public ClassTypeManifest(Option<OptManifest<?>> prefix, Class<?> runtimeClass, List<OptManifest<?>> typeArguments) {
        this.prefix = prefix;
        this.runtimeClass = runtimeClass;
        this.typeArguments = typeArguments;
        ClassManifestDeprecatedApis$class.$init$(this);
        ClassTag$class.$init$(this);
    }
}

