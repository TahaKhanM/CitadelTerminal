/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.MatchError;
import scala.Predef$;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Constants;
import scala.reflect.internal.Constants$Constant$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichChar$;
import scala.runtime.ScalaRunTime$;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(bytes="\u0006\u0001\tMg!C\u0001\u0003!\u0003\r\t!\u0003Bf\u0005%\u0019uN\\:uC:$8O\u0003\u0002\u0004\t\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0019\u0001A\u0003\b\u0011\u0005-aQ\"\u0001\u0004\n\u000551!AB!osJ+g\r\u0005\u0002\u0010%5\t\u0001C\u0003\u0002\u0012\t\u0005\u0019\u0011\r]5\n\u0005\u0005\u0001\u0002\"\u0002\u000b\u0001\t\u0003)\u0012A\u0002\u0013j]&$H\u0005F\u0001\u0017!\tYq#\u0003\u0002\u0019\r\t!QK\\5u\u0011\u001dQ\u0002A1A\u0005\u0006m\tQAT8UC\u001e,\u0012\u0001H\b\u0002;u\t\u0001\u0001\u0003\u0004 \u0001\u0001\u0006i\u0001H\u0001\u0007\u001d>$\u0016m\u001a\u0011\t\u000f\u0005\u0002!\u0019!C\u0003E\u00059QK\\5u)\u0006<W#A\u0012\u0010\u0003\u0011j\u0012!\u0001\u0005\u0007M\u0001\u0001\u000bQB\u0012\u0002\u0011Us\u0017\u000e\u001e+bO\u0002Bq\u0001\u000b\u0001C\u0002\u0013\u0015\u0011&\u0001\u0006C_>dW-\u00198UC\u001e,\u0012AK\b\u0002Wu\t!\u0001\u0003\u0004.\u0001\u0001\u0006iAK\u0001\f\u0005>|G.Z1o)\u0006<\u0007\u0005C\u00040\u0001\t\u0007IQ\u0001\u0019\u0002\u000f\tKH/\u001a+bOV\t\u0011gD\u00013;\u0005\u0019\u0001B\u0002\u001b\u0001A\u00035\u0011'\u0001\u0005CsR,G+Y4!\u0011\u001d1\u0004A1A\u0005\u0006]\n\u0001b\u00155peR$\u0016mZ\u000b\u0002q=\t\u0011(H\u0001\u0005\u0011\u0019Y\u0004\u0001)A\u0007q\u0005I1\u000b[8siR\u000bw\r\t\u0005\b{\u0001\u0011\r\u0011\"\u0002?\u0003\u001d\u0019\u0005.\u0019:UC\u001e,\u0012aP\b\u0002\u0001v\tQ\u0001\u0003\u0004C\u0001\u0001\u0006iaP\u0001\t\u0007\"\f'\u000fV1hA!9A\t\u0001b\u0001\n\u000b)\u0015AB%oiR\u000bw-F\u0001G\u001f\u00059U$\u0001\u0004\t\r%\u0003\u0001\u0015!\u0004G\u0003\u001dIe\u000e\u001e+bO\u0002Bqa\u0013\u0001C\u0002\u0013\u0015A*A\u0004M_:<G+Y4\u0016\u00035{\u0011AT\u000f\u0002\u000f!1\u0001\u000b\u0001Q\u0001\u000e5\u000b\u0001\u0002T8oOR\u000bw\r\t\u0005\b%\u0002\u0011\r\u0011\"\u0002T\u0003!1En\\1u)\u0006<W#\u0001+\u0010\u0003Uk\u0012\u0001\u0003\u0005\u0007/\u0002\u0001\u000bQ\u0002+\u0002\u0013\u0019cw.\u0019;UC\u001e\u0004\u0003bB-\u0001\u0005\u0004%)AW\u0001\n\t>,(\r\\3UC\u001e,\u0012aW\b\u00029v\t\u0011\u0002\u0003\u0004_\u0001\u0001\u0006iaW\u0001\u000b\t>,(\r\\3UC\u001e\u0004\u0003b\u00021\u0001\u0005\u0004%)!Y\u0001\n'R\u0014\u0018N\\4UC\u001e,\u0012AY\b\u0002Gv\t!\u0002\u0003\u0004f\u0001\u0001\u0006iAY\u0001\u000b'R\u0014\u0018N\\4UC\u001e\u0004\u0003bB4\u0001\u0005\u0004%)\u0001[\u0001\b\u001dVdG\u000eV1h+\u0005Iw\"\u00016\u001e\u0003-Aa\u0001\u001c\u0001!\u0002\u001bI\u0017\u0001\u0003(vY2$\u0016m\u001a\u0011\t\u000f9\u0004!\u0019!C\u0003_\u0006A1\t\\1{uR\u000bw-F\u0001q\u001f\u0005\tX$\u0001\u0007\t\rM\u0004\u0001\u0015!\u0004q\u0003%\u0019E.\u0019>{)\u0006<\u0007\u0005C\u0004v\u0001\t\u0007IQ\u0001<\u0002\u000f\u0015sW/\u001c+bOV\tqoD\u0001y;\u0005i\u0001B\u0002>\u0001A\u00035q/\u0001\u0005F]VlG+Y4!\r\u0011a\b\u0001Q?\u0003\u0011\r{gn\u001d;b]R\u001cba\u001f@\u0002\u0006\u0005-\u0001cA@\u0002\u00025\t\u0001!C\u0002\u0002\u0004I\u00111bQ8ogR\fg\u000e^!qSB\u00191\"a\u0002\n\u0007\u0005%aAA\u0004Qe>$Wo\u0019;\u0011\u0007-\ti!C\u0002\u0002\u0010\u0019\u0011AbU3sS\u0006d\u0017N_1cY\u0016D!\"a\u0005|\u0005+\u0007I\u0011AA\u000b\u0003\u00151\u0018\r\\;f+\t\t9\u0002E\u0002\f\u00033I1!a\u0007\u0007\u0005\r\te.\u001f\u0005\u000b\u0003?Y(\u0011#Q\u0001\n\u0005]\u0011A\u0002<bYV,\u0007\u0005C\u0004\u0002$m$\t!!\n\u0002\rqJg.\u001b;?)\u0011\t9#!\u000b\u0011\u0005}\\\b\u0002CA\n\u0003C\u0001\r!a\u0006\t\u0013\u000552P1A\u0005\u0002\u0005=\u0012a\u0001;bOV\u0011\u0011\u0011\u0007\t\u0004\u0017\u0005M\u0012bAA\u001b\r\t\u0019\u0011J\u001c;\t\u0011\u0005e2\u0010)A\u0005\u0003c\tA\u0001^1hA!9\u0011QH>\u0005\u0002\u0005}\u0012aC5t\u0005f$XMU1oO\u0016,\"!!\u0011\u0011\u0007-\t\u0019%C\u0002\u0002F\u0019\u0011qAQ8pY\u0016\fg\u000eC\u0004\u0002Jm$\t!a\u0010\u0002\u0019%\u001c8\u000b[8siJ\u000bgnZ3\t\u000f\u000553\u0010\"\u0001\u0002@\u0005Y\u0011n]\"iCJ\u0014\u0016M\\4f\u0011\u001d\t\tf\u001fC\u0001\u0003\u007f\t!\"[:J]R\u0014\u0016M\\4f\u0011\u001d\t)f\u001fC\u0001\u0003\u007f\t1\"[:M_:<'+\u00198hK\"9\u0011\u0011L>\u0005\u0002\u0005}\u0012\u0001D5t\r2|\u0017\r\u001e*b]\u001e,\u0007bBA/w\u0012\u0005\u0011qH\u0001\nSNtU/\\3sS\u000eDq!!\u0019|\t\u0003\ty$A\bjg:{g.\u00168ji\u0006s\u0017PV1m\u0011\u001d\t)g\u001fC\u0001\u0003\u007f\t\u0001\"[:B]f4\u0016\r\u001c\u0005\b\u0003SZH\u0011AA6\u0003\r!\b/Z\u000b\u0003\u0003[\u00022a`A8\u0013\u0011\t\t(a\u001d\u0003\tQK\b/Z\u0005\u0004\u0003k\u0012!!\u0002+za\u0016\u001c\bbBA=w\u0012\u0005\u00131P\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u0005\u0013Q\u0010\u0005\t\u0003\u007f\n9\b1\u0001\u0002\u0018\u0005)q\u000e\u001e5fe\"9\u00111Q>\u0005\u0002\u0005}\u0012!B5t\u001d\u0006t\u0005bBADw\u0012\u0005\u0011qH\u0001\rE>|G.Z1o-\u0006dW/\u001a\u0005\b\u0003\u0017[H\u0011AAG\u0003%\u0011\u0017\u0010^3WC2,X-\u0006\u0002\u0002\u0010B\u00191\"!%\n\u0007\u0005MeA\u0001\u0003CsR,\u0007bBALw\u0012\u0005\u0011\u0011T\u0001\u000bg\"|'\u000f\u001e,bYV,WCAAN!\rY\u0011QT\u0005\u0004\u0003?3!!B*i_J$\bbBARw\u0012\u0005\u0011QU\u0001\nG\"\f'OV1mk\u0016,\"!a*\u0011\u0007-\tI+C\u0002\u0002,\u001a\u0011Aa\u00115be\"9\u0011qV>\u0005\u0002\u0005=\u0012\u0001C5oiZ\u000bG.^3\t\u000f\u0005M6\u0010\"\u0001\u00026\u0006IAn\u001c8h-\u0006dW/Z\u000b\u0003\u0003o\u00032aCA]\u0013\r\tYL\u0002\u0002\u0005\u0019>tw\rC\u0004\u0002@n$\t!!1\u0002\u0015\u0019dw.\u0019;WC2,X-\u0006\u0002\u0002DB\u00191\"!2\n\u0007\u0005\u001dgAA\u0003GY>\fG\u000fC\u0004\u0002Ln$\t!!4\u0002\u0017\u0011|WO\u00197f-\u0006dW/Z\u000b\u0003\u0003\u001f\u00042aCAi\u0013\r\t\u0019N\u0002\u0002\u0007\t>,(\r\\3\t\u000f\u0005]7\u0010\"\u0001\u0002Z\u0006I1m\u001c8wKJ$Hk\u001c\u000b\u0005\u0003O\tY\u000e\u0003\u0005\u0002^\u0006U\u0007\u0019AA7\u0003\t\u0001H\u000fC\u0004\u0002bn$\t!a9\u0002\u0017M$(/\u001b8h-\u0006dW/Z\u000b\u0003\u0003K\u0004B!a:\u0002n:\u00191\"!;\n\u0007\u0005-h!\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003_\f\tP\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003W4\u0001bBA{w\u0012\u0005\u0011q_\u0001\fKN\u001c\u0017\r]3e\u0007\"\f'\u000f\u0006\u0003\u0002f\u0006e\b\u0002CA~\u0003g\u0004\r!a*\u0002\u0005\rD\u0007\u0006BAz\u0003\u007f\u0004BA!\u0001\u0003\b5\u0011!1\u0001\u0006\u0004\u0005\u000b1\u0011AC1o]>$\u0018\r^5p]&!!\u0011\u0002B\u0002\u0005\u0019\u0019x/\u001b;dQ\"9!QB>\u0005\u0002\u0005\r\u0018AE3tG\u0006\u0004X\rZ*ue&twMV1mk\u0016DqA!\u0005|\t\u0003\tY'A\u0005usB,g+\u00197vK\"9!QC>\u0005\u0002\t]\u0011aC:z[\n|GNV1mk\u0016,\"A!\u0007\u0011\u0007}\u0014Y\"\u0003\u0003\u0003\u001e\t}!AB*z[\n|G.C\u0002\u0003\"\t\u0011qaU=nE>d7\u000fC\u0004\u0003&m$I!!\u0006\u0002\u001d\u0015\fX/\u00197ICNDg+\u00197vK\"9!\u0011F>\u0005B\t-\u0012\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005E\u0002\"\u0003B\u0018w\u0006\u0005I\u0011\u0001B\u0019\u0003\u0011\u0019w\u000e]=\u0015\t\u0005\u001d\"1\u0007\u0005\u000b\u0003'\u0011i\u0003%AA\u0002\u0005]\u0001\"\u0003B\u001cwF\u0005I\u0011\u0001B\u001d\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"Aa\u000f+\t\u0005]!QH\u0016\u0003\u0005\u007f\u0001BA!\u0011\u0003H5\u0011!1\t\u0006\u0005\u0005\u000b\u0012\u0019!A\u0005v]\u000eDWmY6fI&!!\u0011\nB\"\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0005\n\u0005\u001bZ\u0018\u0011!C!\u0005\u001f\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXC\u0001B)!\u0011\u0011\u0019F!\u0018\u000e\u0005\tU#\u0002\u0002B,\u00053\nA\u0001\\1oO*\u0011!1L\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002p\nU\u0003\"\u0003B1w\u0006\u0005I\u0011AA\u0018\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u0011%\u0011)g_A\u0001\n\u0003\u00119'\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005]!\u0011\u000e\u0005\u000b\u0005W\u0012\u0019'!AA\u0002\u0005E\u0012a\u0001=%c!I!qN>\u0002\u0002\u0013\u0005#\u0011O\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011!1\u000f\t\u0007\u0005k\u0012Y(a\u0006\u000e\u0005\t]$b\u0001B=\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\tu$q\u000f\u0002\t\u0013R,'/\u0019;pe\"I!\u0011Q>\u0002\u0002\u0013\u0005!1Q\u0001\tG\u0006tW)];bYR!\u0011\u0011\tBC\u0011)\u0011YGa \u0002\u0002\u0003\u0007\u0011q\u0003\u0005\n\u0005\u0013[\u0018\u0011!C!\u0005\u0017\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005#:qAa$\u0001\u0011\u0003\u0011\t*\u0001\u0005D_:\u001cH/\u00198u!\ry(1\u0013\u0004\u0007y\u0002A\tA!&\u0014\r\tM%qSA\u0006!\ry(\u0011T\u0005\u0004\u00057\u0013\"!E\"p]N$\u0018M\u001c;FqR\u0014\u0018m\u0019;pe\"A\u00111\u0005BJ\t\u0003\u0011y\n\u0006\u0002\u0003\u0012\"Q!1\u0015BJ\u0003\u0003%\tI!*\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0005\u001d\"q\u0015\u0005\t\u0003'\u0011\t\u000b1\u0001\u0002\u0018!Q!1\u0016BJ\u0003\u0003%\tI!,\u0002\u000fUt\u0017\r\u001d9msR!!q\u0016B[!\u0015Y!\u0011WA\f\u0013\r\u0011\u0019L\u0002\u0002\u0007\u001fB$\u0018n\u001c8\t\u0015\t]&\u0011VA\u0001\u0002\u0004\t9#A\u0002yIAB\u0011Ba/\u0001\u0005\u0004%\u0019A!0\u0002\u0017\r{gn\u001d;b]R$\u0016mZ\u000b\u0003\u0005\u007f\u0003bA!1\u0003D\u0006\u001dR\"\u0001\u0003\n\u0007\t\u0015GA\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011!\u0011I\r\u0001Q\u0001\n\t}\u0016\u0001D\"p]N$\u0018M\u001c;UC\u001e\u0004\u0003\u0003\u0002Bg\u0005\u001fl\u0011AA\u0005\u0004\u0005#\u0014!aC*z[\n|G\u000eV1cY\u0016\u0004")
public interface Constants
extends scala.reflect.api.Constants {
    public void scala$reflect$internal$Constants$_setter_$ConstantTag_$eq(ClassTag var1);

    public int NoTag();

    public int UnitTag();

    public int BooleanTag();

    public int ByteTag();

    public int ShortTag();

    public int CharTag();

    public int IntTag();

    public int LongTag();

    public int FloatTag();

    public int DoubleTag();

    public int StringTag();

    public int NullTag();

    public int ClazzTag();

    public int EnumTag();

    @Override
    public Constants$Constant$ Constant();

    public ClassTag<Constant> ConstantTag();

    public class Constant
    extends Constants.ConstantApi
    implements Product,
    Serializable {
        private final Object value;
        private final int tag;

        @Override
        public Object value() {
            return this.value;
        }

        public int tag() {
            return this.tag;
        }

        public boolean isByteRange() {
            return this.isIntRange() && -128 <= this.intValue() && this.intValue() <= 127;
        }

        public boolean isShortRange() {
            return this.isIntRange() && Short.MIN_VALUE <= this.intValue() && this.intValue() <= Short.MAX_VALUE;
        }

        public boolean isCharRange() {
            return this.isIntRange() && 0 <= this.intValue() && this.intValue() <= 65535;
        }

        public boolean isIntRange() {
            return 3 <= this.tag() && this.tag() <= 6;
        }

        public boolean isLongRange() {
            return 3 <= this.tag() && this.tag() <= 7;
        }

        public boolean isFloatRange() {
            return 3 <= this.tag() && this.tag() <= 8;
        }

        public boolean isNumeric() {
            return 3 <= this.tag() && this.tag() <= 9;
        }

        public boolean isNonUnitAnyVal() {
            return 2 <= this.tag() && this.tag() <= 9;
        }

        public boolean isAnyVal() {
            return 1 <= this.tag() && this.tag() <= 9;
        }

        @Override
        public Types.Type tpe() {
            Types.Type type;
            int n = this.tag();
            switch (n) {
                default: {
                    throw new MatchError(BoxesRunTime.boxToInteger(n));
                }
                case 13: {
                    type = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().EnumType(this.symbolValue());
                    break;
                }
                case 12: {
                    type = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().ClassType(this.typeValue());
                    break;
                }
                case 11: {
                    type = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().NullTpe();
                    break;
                }
                case 10: {
                    type = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().StringTpe();
                    break;
                }
                case 9: {
                    type = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().DoubleTpe();
                    break;
                }
                case 8: {
                    type = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().FloatTpe();
                    break;
                }
                case 7: {
                    type = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().LongTpe();
                    break;
                }
                case 6: {
                    type = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().IntTpe();
                    break;
                }
                case 5: {
                    type = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().CharTpe();
                    break;
                }
                case 4: {
                    type = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().ShortTpe();
                    break;
                }
                case 3: {
                    type = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().ByteTpe();
                    break;
                }
                case 2: {
                    type = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().BooleanTpe();
                    break;
                }
                case 1: {
                    type = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().UnitTpe();
                }
            }
            return type;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Constant)) return false;
            if (((Constant)other).scala$reflect$internal$Constants$Constant$$$outer() != this.scala$reflect$internal$Constants$Constant$$$outer()) return false;
            Constant constant = (Constant)other;
            if (this.tag() != constant.tag()) return false;
            Object object = constant.equalHashValue();
            Object object2 = this.equalHashValue();
            if (object2 == object) return true;
            if (object2 == null) return false;
            boolean bl = !(object2 instanceof Number) ? (!(object2 instanceof Character) ? object2.equals(object) : BoxesRunTime.equalsCharObject((Character)object2, object)) : BoxesRunTime.equalsNumObject((Number)object2, object);
            if (!bl) return false;
            return true;
        }

        public boolean isNaN() {
            boolean bl;
            Object object = this.value();
            if (object instanceof Float) {
                float f = BoxesRunTime.unboxToFloat(object);
                bl = Predef$.MODULE$.float2Float(f).isNaN();
            } else if (object instanceof Double) {
                double d = BoxesRunTime.unboxToDouble(object);
                bl = Predef$.MODULE$.double2Double(d).isNaN();
            } else {
                bl = false;
            }
            return bl;
        }

        public boolean booleanValue() {
            if (this.tag() == 2) {
                return BoxesRunTime.unboxToBoolean(this.value());
            }
            throw new Error(new StringBuilder().append((Object)"value ").append(this.value()).append((Object)" is not a boolean").toString());
        }

        public byte byteValue() {
            byte by2;
            int n = this.tag();
            switch (n) {
                default: {
                    throw new Error(new StringBuilder().append((Object)"value ").append(this.value()).append((Object)" is not a Byte").toString());
                }
                case 9: {
                    by2 = (byte)BoxesRunTime.unboxToDouble(this.value());
                    break;
                }
                case 8: {
                    by2 = (byte)BoxesRunTime.unboxToFloat(this.value());
                    break;
                }
                case 7: {
                    by2 = (byte)BoxesRunTime.unboxToLong(this.value());
                    break;
                }
                case 6: {
                    by2 = (byte)BoxesRunTime.unboxToInt(this.value());
                    break;
                }
                case 5: {
                    by2 = (byte)BoxesRunTime.unboxToChar(this.value());
                    break;
                }
                case 4: {
                    by2 = (byte)BoxesRunTime.unboxToShort(this.value());
                    break;
                }
                case 3: {
                    by2 = BoxesRunTime.unboxToByte(this.value());
                }
            }
            return by2;
        }

        public short shortValue() {
            short s2;
            int n = this.tag();
            switch (n) {
                default: {
                    throw new Error(new StringBuilder().append((Object)"value ").append(this.value()).append((Object)" is not a Short").toString());
                }
                case 9: {
                    s2 = (short)BoxesRunTime.unboxToDouble(this.value());
                    break;
                }
                case 8: {
                    s2 = (short)BoxesRunTime.unboxToFloat(this.value());
                    break;
                }
                case 7: {
                    s2 = (short)BoxesRunTime.unboxToLong(this.value());
                    break;
                }
                case 6: {
                    s2 = (short)BoxesRunTime.unboxToInt(this.value());
                    break;
                }
                case 5: {
                    s2 = (short)BoxesRunTime.unboxToChar(this.value());
                    break;
                }
                case 4: {
                    s2 = BoxesRunTime.unboxToShort(this.value());
                    break;
                }
                case 3: {
                    s2 = BoxesRunTime.unboxToByte(this.value());
                }
            }
            return s2;
        }

        public char charValue() {
            char c;
            int n = this.tag();
            switch (n) {
                default: {
                    throw new Error(new StringBuilder().append((Object)"value ").append(this.value()).append((Object)" is not a Char").toString());
                }
                case 9: {
                    c = (char)BoxesRunTime.unboxToDouble(this.value());
                    break;
                }
                case 8: {
                    c = (char)BoxesRunTime.unboxToFloat(this.value());
                    break;
                }
                case 7: {
                    c = (char)BoxesRunTime.unboxToLong(this.value());
                    break;
                }
                case 6: {
                    c = (char)BoxesRunTime.unboxToInt(this.value());
                    break;
                }
                case 5: {
                    c = BoxesRunTime.unboxToChar(this.value());
                    break;
                }
                case 4: {
                    c = (char)BoxesRunTime.unboxToShort(this.value());
                    break;
                }
                case 3: {
                    c = (char)BoxesRunTime.unboxToByte(this.value());
                }
            }
            return c;
        }

        public int intValue() {
            int n;
            int n2 = this.tag();
            switch (n2) {
                default: {
                    throw new Error(new StringBuilder().append((Object)"value ").append(this.value()).append((Object)" is not an Int").toString());
                }
                case 9: {
                    n = (int)BoxesRunTime.unboxToDouble(this.value());
                    break;
                }
                case 8: {
                    n = (int)BoxesRunTime.unboxToFloat(this.value());
                    break;
                }
                case 7: {
                    n = (int)BoxesRunTime.unboxToLong(this.value());
                    break;
                }
                case 6: {
                    n = BoxesRunTime.unboxToInt(this.value());
                    break;
                }
                case 5: {
                    n = BoxesRunTime.unboxToChar(this.value());
                    break;
                }
                case 4: {
                    n = BoxesRunTime.unboxToShort(this.value());
                    break;
                }
                case 3: {
                    n = BoxesRunTime.unboxToByte(this.value());
                }
            }
            return n;
        }

        public long longValue() {
            long l;
            int n = this.tag();
            switch (n) {
                default: {
                    throw new Error(new StringBuilder().append((Object)"value ").append(this.value()).append((Object)" is not a Long").toString());
                }
                case 9: {
                    l = (long)BoxesRunTime.unboxToDouble(this.value());
                    break;
                }
                case 8: {
                    l = (long)BoxesRunTime.unboxToFloat(this.value());
                    break;
                }
                case 7: {
                    l = BoxesRunTime.unboxToLong(this.value());
                    break;
                }
                case 6: {
                    l = BoxesRunTime.unboxToInt(this.value());
                    break;
                }
                case 5: {
                    l = BoxesRunTime.unboxToChar(this.value());
                    break;
                }
                case 4: {
                    l = BoxesRunTime.unboxToShort(this.value());
                    break;
                }
                case 3: {
                    l = BoxesRunTime.unboxToByte(this.value());
                }
            }
            return l;
        }

        public float floatValue() {
            float f;
            int n = this.tag();
            switch (n) {
                default: {
                    throw new Error(new StringBuilder().append((Object)"value ").append(this.value()).append((Object)" is not a Float").toString());
                }
                case 9: {
                    f = (float)BoxesRunTime.unboxToDouble(this.value());
                    break;
                }
                case 8: {
                    f = BoxesRunTime.unboxToFloat(this.value());
                    break;
                }
                case 7: {
                    f = BoxesRunTime.unboxToLong(this.value());
                    break;
                }
                case 6: {
                    f = BoxesRunTime.unboxToInt(this.value());
                    break;
                }
                case 5: {
                    f = BoxesRunTime.unboxToChar(this.value());
                    break;
                }
                case 4: {
                    f = BoxesRunTime.unboxToShort(this.value());
                    break;
                }
                case 3: {
                    f = BoxesRunTime.unboxToByte(this.value());
                }
            }
            return f;
        }

        public double doubleValue() {
            double d;
            int n = this.tag();
            switch (n) {
                default: {
                    throw new Error(new StringBuilder().append((Object)"value ").append(this.value()).append((Object)" is not a Double").toString());
                }
                case 9: {
                    d = BoxesRunTime.unboxToDouble(this.value());
                    break;
                }
                case 8: {
                    d = BoxesRunTime.unboxToFloat(this.value());
                    break;
                }
                case 7: {
                    d = BoxesRunTime.unboxToLong(this.value());
                    break;
                }
                case 6: {
                    d = BoxesRunTime.unboxToInt(this.value());
                    break;
                }
                case 5: {
                    d = BoxesRunTime.unboxToChar(this.value());
                    break;
                }
                case 4: {
                    d = BoxesRunTime.unboxToShort(this.value());
                    break;
                }
                case 3: {
                    d = BoxesRunTime.unboxToByte(this.value());
                }
            }
            return d;
        }

        public Constant convertTo(Types.Type pt) {
            Constant constant;
            Symbols.Symbol target;
            Symbols.Symbol symbol = target = pt.typeSymbol();
            Symbols.Symbol symbol2 = this.tpe().typeSymbol();
            if (!(symbol != null ? !symbol.equals(symbol2) : symbol2 != null)) {
                constant = this;
            } else {
                Symbols.Symbol symbol3 = target;
                Symbols.ClassSymbol classSymbol = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().ByteClass();
                if (!(symbol3 != null ? !symbol3.equals(classSymbol) : classSymbol != null) && this.isByteRange()) {
                    constant = new Constant(this.scala$reflect$internal$Constants$Constant$$$outer(), BoxesRunTime.boxToByte(this.byteValue()));
                } else {
                    Symbols.Symbol symbol4 = target;
                    Symbols.ClassSymbol classSymbol2 = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().ShortClass();
                    if (!(symbol4 != null ? !symbol4.equals(classSymbol2) : classSymbol2 != null) && this.isShortRange()) {
                        constant = new Constant(this.scala$reflect$internal$Constants$Constant$$$outer(), BoxesRunTime.boxToShort(this.shortValue()));
                    } else {
                        Symbols.Symbol symbol5 = target;
                        Symbols.ClassSymbol classSymbol3 = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().CharClass();
                        if (!(symbol5 != null ? !symbol5.equals(classSymbol3) : classSymbol3 != null) && this.isCharRange()) {
                            constant = new Constant(this.scala$reflect$internal$Constants$Constant$$$outer(), BoxesRunTime.boxToCharacter(this.charValue()));
                        } else {
                            Symbols.Symbol symbol6 = target;
                            Symbols.ClassSymbol classSymbol4 = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().IntClass();
                            if (!(symbol6 != null ? !symbol6.equals(classSymbol4) : classSymbol4 != null) && this.isIntRange()) {
                                constant = new Constant(this.scala$reflect$internal$Constants$Constant$$$outer(), BoxesRunTime.boxToInteger(this.intValue()));
                            } else {
                                Symbols.Symbol symbol7 = target;
                                Symbols.ClassSymbol classSymbol5 = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().LongClass();
                                if (!(symbol7 != null ? !symbol7.equals(classSymbol5) : classSymbol5 != null) && this.isLongRange()) {
                                    constant = new Constant(this.scala$reflect$internal$Constants$Constant$$$outer(), BoxesRunTime.boxToLong(this.longValue()));
                                } else {
                                    Symbols.Symbol symbol8 = target;
                                    Symbols.ClassSymbol classSymbol6 = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().FloatClass();
                                    if (!(symbol8 != null ? !symbol8.equals(classSymbol6) : classSymbol6 != null) && this.isFloatRange()) {
                                        constant = new Constant(this.scala$reflect$internal$Constants$Constant$$$outer(), BoxesRunTime.boxToFloat(this.floatValue()));
                                    } else {
                                        Symbols.Symbol symbol9 = target;
                                        Symbols.ClassSymbol classSymbol7 = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().DoubleClass();
                                        constant = !(symbol9 != null ? !symbol9.equals(classSymbol7) : classSymbol7 != null) && this.isNumeric() ? new Constant(this.scala$reflect$internal$Constants$Constant$$$outer(), BoxesRunTime.boxToDouble(this.doubleValue())) : null;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return constant;
        }

        public String stringValue() {
            return this.value() == null ? "null" : (this.tag() == 12 ? this.scala$reflect$internal$Constants$Constant$$$outer().definitions().signature(this.typeValue()) : this.value().toString());
        }

        public String escapedChar(char ch) {
            String string2;
            switch (ch) {
                default: {
                    Predef$ predef$ = Predef$.MODULE$;
                    if (RichChar$.MODULE$.isControl$extension(ch)) {
                        string2 = new StringBuilder().append((Object)"\\0").append((Object)Integer.toOctalString(ch)).toString();
                        break;
                    }
                    string2 = String.valueOf(ch);
                    break;
                }
                case '\\': {
                    string2 = "\\\\";
                    break;
                }
                case '\'': {
                    string2 = "\\'";
                    break;
                }
                case '\"': {
                    string2 = "\\\"";
                    break;
                }
                case '\r': {
                    string2 = "\\r";
                    break;
                }
                case '\f': {
                    string2 = "\\f";
                    break;
                }
                case '\n': {
                    string2 = "\\n";
                    break;
                }
                case '\t': {
                    string2 = "\\t";
                    break;
                }
                case '\b': {
                    string2 = "\\b";
                }
            }
            return string2;
        }

        public String escapedStringValue() {
            String string2;
            int n = this.tag();
            switch (n) {
                default: {
                    string2 = String.valueOf(this.value());
                    break;
                }
                case 13: {
                    string2 = this.symbolValue().name().toString();
                    break;
                }
                case 7: {
                    string2 = new StringBuilder().append((Object)((Object)BoxesRunTime.boxToLong(this.longValue())).toString()).append((Object)"L").toString();
                    break;
                }
                case 5: {
                    string2 = new StringBuilder().append((Object)"'").append((Object)this.escapedChar(this.charValue())).append((Object)"'").toString();
                    break;
                }
                case 12: {
                    String string3;
                    Types.Type type = this.typeValue();
                    if (type instanceof Types.ErasedValueType) {
                        Types.ErasedValueType erasedValueType = (Types.ErasedValueType)type;
                        string3 = this.show$1(erasedValueType.valueClazz().tpe_$times());
                    } else {
                        string3 = this.show$1(this.typeValue());
                    }
                    string2 = string3;
                    break;
                }
                case 10: {
                    string2 = new StringBuilder().append((Object)"\"").append((Object)this.escape$1(this.stringValue())).append((Object)"\"").toString();
                    break;
                }
                case 11: {
                    string2 = "null";
                }
            }
            return string2;
        }

        public Types.Type typeValue() {
            return (Types.Type)this.value();
        }

        public Symbols.Symbol symbolValue() {
            return (Symbols.Symbol)this.value();
        }

        private Object equalHashValue() {
            Object object;
            Object object2 = this.value();
            if (object2 instanceof Float) {
                float f = BoxesRunTime.unboxToFloat(object2);
                object = BoxesRunTime.boxToInteger(Float.floatToRawIntBits(f));
            } else if (object2 instanceof Double) {
                double d = BoxesRunTime.unboxToDouble(object2);
                object = BoxesRunTime.boxToLong(Double.doubleToRawLongBits(d));
            } else {
                object = object2;
            }
            return object;
        }

        public int hashCode() {
            int h = MurmurHash3$.MODULE$.mix(17, this.tag());
            h = MurmurHash3$.MODULE$.mix(h, ScalaRunTime$.MODULE$.hash(this.equalHashValue()));
            return MurmurHash3$.MODULE$.finalizeHash(h, 2);
        }

        public Constant copy(Object value) {
            return new Constant(this.scala$reflect$internal$Constants$Constant$$$outer(), value);
        }

        public Object copy$default$1() {
            return this.value();
        }

        @Override
        public String productPrefix() {
            return "Constant";
        }

        @Override
        public int productArity() {
            return 1;
        }

        @Override
        public Object productElement(int x$1) {
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 0: 
            }
            return this.value();
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof Constant;
        }

        public String toString() {
            return ScalaRunTime$.MODULE$._toString(this);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Constants$Constant$$$outer() {
            return (SymbolTable)this.$outer;
        }

        private final String escape$1(String text) {
            Predef$ predef$ = Predef$.MODULE$;
            return new StringOps(text).flatMap(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Constant $outer;

                public final String apply(char ch) {
                    String string2 = this.$outer.escapedChar(ch);
                    Predef$ predef$ = Predef$.MODULE$;
                    return string2;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Predef$.MODULE$.StringCanBuildFrom());
        }

        private final String show$1(Types.Type tpe) {
            return new StringBuilder().append((Object)"classOf[").append((Object)this.scala$reflect$internal$Constants$Constant$$$outer().definitions().signature(tpe)).append((Object)"]").toString();
        }

        public Constant(SymbolTable $outer, Object value) {
            block15: {
                int n;
                block3: {
                    block14: {
                        block13: {
                            block12: {
                                block11: {
                                    block10: {
                                        block9: {
                                            block8: {
                                                block7: {
                                                    block6: {
                                                        block5: {
                                                            block4: {
                                                                block2: {
                                                                    this.value = value;
                                                                    super($outer);
                                                                    Product$class.$init$(this);
                                                                    if (value != null) break block2;
                                                                    n = 11;
                                                                    break block3;
                                                                }
                                                                if (!(value instanceof BoxedUnit)) break block4;
                                                                n = 1;
                                                                break block3;
                                                            }
                                                            if (!(value instanceof Boolean)) break block5;
                                                            n = 2;
                                                            break block3;
                                                        }
                                                        if (!(value instanceof Byte)) break block6;
                                                        n = 3;
                                                        break block3;
                                                    }
                                                    if (!(value instanceof Short)) break block7;
                                                    n = 4;
                                                    break block3;
                                                }
                                                if (!(value instanceof Integer)) break block8;
                                                n = 6;
                                                break block3;
                                            }
                                            if (!(value instanceof Long)) break block9;
                                            n = 7;
                                            break block3;
                                        }
                                        if (!(value instanceof Float)) break block10;
                                        n = 8;
                                        break block3;
                                    }
                                    if (!(value instanceof Double)) break block11;
                                    n = 9;
                                    break block3;
                                }
                                if (!(value instanceof String)) break block12;
                                n = 10;
                                break block3;
                            }
                            if (!(value instanceof Character)) break block13;
                            n = 5;
                            break block3;
                        }
                        if (!(value instanceof Types.Type) || ((Types.Type)value).scala$reflect$internal$Types$Type$$$outer() != $outer) break block14;
                        n = 12;
                        break block3;
                    }
                    if (!(value instanceof Symbols.Symbol) || ((Symbols.Symbol)value).scala$reflect$internal$Symbols$Symbol$$$outer() != $outer) break block15;
                    n = 13;
                }
                this.tag = n;
                return;
            }
            throw new Error(new StringBuilder().append((Object)"bad constant value: ").append(value).append((Object)" of class ").append(value.getClass()).toString());
        }
    }
}

