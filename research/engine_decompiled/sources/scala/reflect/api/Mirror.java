/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Symbols;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

@ScalaSignature(bytes="\u0006\u0001y4Q!\u0001\u0002\u0002\u0002%\u0011a!T5se>\u0014(BA\u0002\u0005\u0003\r\t\u0007/\u001b\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001)\"A\u0003\f\u0014\u0005\u0001Y\u0001C\u0001\u0007\u000e\u001b\u00051\u0011B\u0001\b\u0007\u0005\u0019\te.\u001f*fM\")\u0001\u0003\u0001C\u0001#\u00051A(\u001b8jiz\"\u0012A\u0005\t\u0004'\u0001!R\"\u0001\u0002\u0011\u0005U1B\u0002\u0001\u0003\u0006/\u0001\u0011\r\u0001\u0007\u0002\u0002+F\u0011\u0011\u0004\b\t\u0003\u0019iI!a\u0007\u0004\u0003\u000f9{G\u000f[5oOJ\u0019Qd\b\u0012\u0007\ty\u0001\u0001\u0001\b\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0003'\u0001J!!\t\u0002\u0003\u0011Us\u0017N^3sg\u0016\u0004\"\u0001D\u0012\n\u0005\u00112!!C*j]\u001edW\r^8o\u0011\u001d1\u0003A1A\u0007\u0002\u001d\n\u0001\"\u001e8jm\u0016\u00148/Z\u000b\u0002)!)\u0011\u0006\u0001D\u0001U\u0005I!k\\8u\u00072\f7o]\u000b\u0002WA\u0011A\u0003L\u0005\u0003[9\u00121b\u00117bgN\u001c\u00160\u001c2pY&\u0011qF\u0001\u0002\b'fl'm\u001c7t\u0011\u0015\t\u0004A\"\u00013\u0003-\u0011vn\u001c;QC\u000e\\\u0017mZ3\u0016\u0003M\u0002\"\u0001\u0006\u001b\n\u0005Ur#\u0001D'pIVdWmU=nE>d\u0007\"B\u001c\u0001\r\u0003Q\u0013!E#naRL\b+Y2lC\u001e,7\t\\1tg\")\u0011\b\u0001D\u0001e\u0005aQ)\u001c9usB\u000b7m[1hK\")1\b\u0001D\u0001y\u0005Y1\u000f^1uS\u000e\u001cE.Y:t)\tYS\bC\u0003?u\u0001\u0007q(\u0001\u0005gk2dg*Y7f!\t\u00015I\u0004\u0002\r\u0003&\u0011!IB\u0001\u0007!J,G-\u001a4\n\u0005\u0011+%AB*ue&twM\u0003\u0002C\r!)q\t\u0001D\u0001\u0011\u0006a1\u000f^1uS\u000elu\u000eZ;mKR\u00111'\u0013\u0005\u0006}\u0019\u0003\ra\u0010\u0005\u0006\u0017\u00021\t\u0001T\u0001\u000egR\fG/[2QC\u000e\\\u0017mZ3\u0015\u0005Mj\u0005\"\u0002 K\u0001\u0004y\u0004\"B(\u0001\t\u0003\u0001\u0016AC<fC.$\u0016\u0010]3PMV\u0011\u0011+\u0019\u000b\u0003%^\u0003\"\u0001F*\n\u0005Q+&\u0001\u0002+za\u0016L!A\u0016\u0002\u0003\u000bQK\b/Z:\t\u000fas\u0015\u0011!a\u00023\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u0007ic\u0006M\u0004\u0002\\K5\t\u0001!\u0003\u0002^=\nYq+Z1l)f\u0004X\rV1h\u0013\ty&A\u0001\u0005UsB,G+Y4t!\t)\u0012\rB\u0003c\u001d\n\u00071MA\u0001U#\tIB\r\u0005\u0002\rK&\u0011aM\u0002\u0002\u0004\u0003:L\b\"\u00025\u0001\t\u0003I\u0017A\u0002;za\u0016|e-\u0006\u0002kcR\u0011!k\u001b\u0005\bY\u001e\f\t\u0011q\u0001n\u0003))g/\u001b3f]\u000e,GE\r\t\u00045:\u0004\u0018BA8_\u0005\u001d!\u0016\u0010]3UC\u001e\u0004\"!F9\u0005\u000b\t<'\u0019A2\t\u000bM\u0004a\u0011\u0001;\u0002\u0011MLXNY8m\u001f\u001a,\"!^?\u0015\u0005YL\bC\u0001\u000bx\u0013\tAhF\u0001\u0006UsB,7+_7c_2DqA\u001f:\u0002\u0002\u0003\u000f10\u0001\u0006fm&$WM\\2fIM\u00022A\u0017/}!\t)R\u0010B\u0003ce\n\u00071\r")
public abstract class Mirror<U extends Universe> {
    public abstract U universe();

    public abstract Symbols.ClassSymbolApi RootClass();

    public abstract Symbols.ModuleSymbolApi RootPackage();

    public abstract Symbols.ClassSymbolApi EmptyPackageClass();

    public abstract Symbols.ModuleSymbolApi EmptyPackage();

    public abstract Symbols.ClassSymbolApi staticClass(String var1);

    public abstract Symbols.ModuleSymbolApi staticModule(String var1);

    public abstract Symbols.ModuleSymbolApi staticPackage(String var1);

    public <T> Types.TypeApi weakTypeOf(TypeTags.WeakTypeTag<T> evidence$1) {
        return ((Universe)this.universe()).weakTypeTag(evidence$1).in(this).tpe();
    }

    public <T> Types.TypeApi typeOf(TypeTags.TypeTag<T> evidence$2) {
        return ((Universe)this.universe()).typeTag(evidence$2).in(this).tpe();
    }

    public abstract <T> Symbols.TypeSymbolApi symbolOf(TypeTags.WeakTypeTag<T> var1);
}

