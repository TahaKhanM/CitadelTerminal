/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.SourceFile;

@ScalaSignature(bytes="\u0006\u0001\u0005Eg!C\u0001\u0003!\u0003\r\tA\u0001\u0006G\u0005QIe\u000e^3s]\u0006d\u0007k\\:ji&|g.S7qY*\u00111\u0001B\u0001\u0005kRLGN\u0003\u0002\u0006\r\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\b\u0011\u00059!/\u001a4mK\u000e$(\"A\u0005\u0002\u000bM\u001c\u0017\r\\1\u0014\u0005\u0001Y\u0001C\u0001\u0007\u000e\u001b\u0005A\u0011B\u0001\b\t\u0005\u0019\te.\u001f*fM\")\u0001\u0003\u0001C\u0001%\u00051A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u0014!\taA#\u0003\u0002\u0016\u0011\t!QK\\5u\u0011\u00159\u0002A\"\u0001\u0019\u0003%I7\u000fR3gS:,G-F\u0001\u001a!\ta!$\u0003\u0002\u001c\u0011\t9!i\\8mK\u0006t\u0007\"B\u000f\u0001\r\u0003A\u0012aB5t%\u0006tw-\u001a\u0005\u0006?\u00011\t\u0001I\u0001\u0007g>,(oY3\u0016\u0003\u0005\u0002\"AI\u0012\u000e\u0003\tI!\u0001\n\u0002\u0003\u0015M{WO]2f\r&dW\rC\u0003'\u0001\u0019\u0005q%A\u0003ti\u0006\u0014H/F\u0001)!\ta\u0011&\u0003\u0002+\u0011\t\u0019\u0011J\u001c;\t\u000b1\u0002a\u0011A\u0014\u0002\u000bA|\u0017N\u001c;\t\u000b9\u0002a\u0011A\u0014\u0002\u0007\u0015tG\rC\u00031\u0001\u0011\u0005\u0011'A\u0007gS:\fG\u000eU8tSRLwN\\\u000b\u0002eA\u00111\u0007N\u0007\u0002\u0001%\u0011QG\u000e\u0002\u0004!>\u001c\u0018BA\u001c\u0003\u0005!\u0001vn]5uS>t\u0007\"B\u001d\u0001\t\u0003A\u0012!D5t)J\fgn\u001d9be\u0016tG\u000fC\u0003<\u0001\u0011\u0005\u0001$\u0001\u0005jg>3gm]3u\u0011\u0015i\u0004\u0001\"\u0001\u0019\u00035I7o\u00149bcV,'+\u00198hK\")q\b\u0001C\u0001\u0001\u0006Y\u0001o\\5oi>\u0013X\t\\:f)\tA\u0013\tC\u0003C}\u0001\u0007\u0001&A\u0002bYRDQ\u0001\u0012\u0001\u0005\u0002\u0015\u000bq\"\\1lKR\u0013\u0018M\\:qCJ,g\u000e^\u000b\u0002\rB\u0011!E\u000e\u0005\u0006\u0011\u0002!\t!S\u0001\no&$\bn\u0015;beR$\"A\u0012&\t\u000b\u0019:\u0005\u0019\u0001\u0015\t\u000b1\u0003A\u0011A'\u0002\u0013]LG\u000f\u001b)pS:$HC\u0001$O\u0011\u0015a3\n1\u0001)\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0003\u001d9\u0018\u000e\u001e5F]\u0012$\"A\u0012*\t\u000b9z\u0005\u0019\u0001\u0015\t\u000bQ\u0003A\u0011A+\u0002\u0015]LG\u000f[*pkJ\u001cW\r\u0006\u0002G-\")qd\u0015a\u0001C!)\u0001\f\u0001C\u00013\u0006Iq/\u001b;i'\"Lg\r\u001e\u000b\u0003\rjCQaW,A\u0002!\nQa\u001d5jMRDQ!\u0018\u0001\u0005\u0002\u0015\u000b!BZ8dkN\u001cF/\u0019:u\u0011\u0015y\u0006\u0001\"\u0001F\u0003\u00151wnY;t\u0011\u0015\t\u0007\u0001\"\u0001F\u0003!1wnY;t\u000b:$\u0007\"B2\u0001\t\u0003!\u0017\u0001\u0002\u0013cCJ$2AR3h\u0011\u00151'\r1\u0001G\u0003\u0011!\b.\u0019;\t\u000b!\u0014\u0007\u0019A5\u0002\u000bA|7/Z:\u0011\u00071Qg)\u0003\u0002l\u0011\tQAH]3qK\u0006$X\r\u001a \t\u000b\r\u0004A\u0011A7\u0015\u0005\u0019s\u0007\"\u00024m\u0001\u00041\u0005\"\u00029\u0001\t\u0003\t\u0018a\u0001\u0013vaR\u0011aI\u001d\u0005\u0006Y=\u0004\r\u0001\u000b\u0005\u0006i\u0002!\t!^\u0001\bI\t\f'\u000fJ;q)\t1e\u000fC\u0003gg\u0002\u0007a\tC\u0003y\u0001\u0011\u0005\u00110A\u0004%kB$#-\u0019:\u0015\u0005\u0019S\b\"\u00024x\u0001\u00041\u0005\"\u0002?\u0001\t\u0003i\u0018!B;oS>tGC\u0001$\u007f\u0011\u0015y8\u00101\u0001G\u0003\r\u0001xn\u001d\u0005\b\u0003\u0007\u0001A\u0011AA\u0003\u0003!Ign\u00197vI\u0016\u001cHcA\r\u0002\b!1q0!\u0001A\u0002\u0019Cq!a\u0003\u0001\t\u0003\ti!\u0001\tqe>\u0004XM\u001d7z\u0013:\u001cG.\u001e3fgR\u0019\u0011$a\u0004\t\r}\fI\u00011\u0001G\u0011\u001d\t\u0019\u0002\u0001C\u0001\u0003+\t\u0001\u0002\u001d:fG\u0016$Wm\u001d\u000b\u00043\u0005]\u0001BB@\u0002\u0012\u0001\u0007a\tC\u0004\u0002\u001c\u0001!\t!!\b\u0002!A\u0014x\u000e]3sYf\u0004&/Z2fI\u0016\u001cHcA\r\u0002 !1q0!\u0007A\u0002\u0019Cq!a\t\u0001\t\u0003\t)#A\u0005tC6,'+\u00198hKR\u0019\u0011$a\n\t\r}\f\t\u00031\u0001G\u0011\u001d\tY\u0003\u0001C\u0001\u0003[\t\u0001b\u001c<fe2\f\u0007o\u001d\u000b\u00043\u0005=\u0002BB@\u0002*\u0001\u0007a\t\u0003\u0004\u00024\u0001!\taJ\u0001\u0005Y&tW\r\u0003\u0004\u00028\u0001!\taJ\u0001\u0007G>dW/\u001c8\t\u000f\u0005m\u0002\u0001\"\u0001\u0002>\u0005YA.\u001b8f\u0007>tG/\u001a8u+\t\ty\u0004\u0005\u0003\u0002B\u0005\u001dcb\u0001\u0007\u0002D%\u0019\u0011Q\t\u0005\u0002\rA\u0013X\rZ3g\u0013\u0011\tI%a\u0013\u0003\rM#(/\u001b8h\u0015\r\t)\u0005\u0003\u0005\b\u0003\u001f\u0002A\u0011AA\u001f\u0003%a\u0017N\\3DCJ,G\u000fC\u0004\u0002T\u0001!\t!!\u0010\u0002\u00131Lg.Z\"be\u0006$\b\u0006CA)\u0003/\ni&!\u0019\u0011\u00071\tI&C\u0002\u0002\\!\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3eC\t\ty&A\bvg\u0016\u0004\u0003\r\\5oK\u000e\u000b'/\u001a;aC\t\t\u0019'\u0001\u00043]E\nd\u0006\r\u0005\b\u0003O\u0002A\u0011AA5\u0003%\u0019\bn\\<FeJ|'\u000f\u0006\u0003\u0002@\u0005-\u0004\u0002CA7\u0003K\u0002\r!a\u0010\u0002\u00075\u001cx\rC\u0004\u0002r\u0001!\t!!\u0010\u0002\u0013MDwn\u001e#fEV<\u0007bBA;\u0001\u0011\u0005\u0011QH\u0001\u0005g\"|w\u000fC\u0004\u0002z\u0001!I!a\u001f\u0002\u0011\u0005\u001cxJ\u001a4tKR$2ARA?\u0011\u0019a\u0013q\u000fa\u0001Q!9\u0011\u0011\u0011\u0001\u0005\n\u0005\r\u0015!C2paf\u0014\u0016M\\4f)%1\u0015QQAD\u0003\u0013\u000bY\t\u0003\u0005 \u0003\u007f\u0002\n\u00111\u0001\"\u0011!1\u0013q\u0010I\u0001\u0002\u0004A\u0003\u0002\u0003\u0017\u0002\u0000A\u0005\t\u0019\u0001\u0015\t\u00119\ny\b%AA\u0002!Bq!a$\u0001\t\u0013\t\t*A\bdC2\u001cW\u000f\\1uK\u000e{G.^7o)\u0005A\u0003BBAK\u0001\u0011%\u0001$A\u0005iCN\u001cv.\u001e:dK\"9\u0011\u0011\u0014\u0001\u0005\n\u0005m\u0015A\u00032pi\"\u0014\u0016M\\4fgR\u0019\u0011$!(\t\r\u0019\f9\n1\u0001G\u0011\u001d\t\t\u000b\u0001C\u0005\u0003G\u000b1BY8uQ\u0012+g-\u001b8fIR\u0019\u0011$!*\t\r\u0019\fy\n1\u0001G\u0011%\tI\u000bAI\u0001\n\u0013\tY+A\nd_BL(+\u00198hK\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002.*\u001a\u0011%a,,\u0005\u0005E\u0006\u0003BAZ\u0003{k!!!.\u000b\t\u0005]\u0016\u0011X\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a/\t\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u007f\u000b)LA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016D\u0011\"a1\u0001#\u0003%I!!2\u0002'\r|\u0007/\u001f*b]\u001e,G\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005\u001d'f\u0001\u0015\u00020\"I\u00111\u001a\u0001\u0012\u0002\u0013%\u0011QY\u0001\u0014G>\u0004\u0018PU1oO\u0016$C-\u001a4bk2$He\r\u0005\n\u0003\u001f\u0004\u0011\u0013!C\u0005\u0003\u000b\f1cY8qsJ\u000bgnZ3%I\u00164\u0017-\u001e7uIQ\u0002")
public interface InternalPositionImpl {
    public boolean isDefined();

    public boolean isRange();

    public SourceFile source();

    public int start();

    public int point();

    public int end();

    public Position finalPosition();

    public boolean isTransparent();

    public boolean isOffset();

    public boolean isOpaqueRange();

    public int pointOrElse(int var1);

    public Position makeTransparent();

    public Position withStart(int var1);

    public Position withPoint(int var1);

    public Position withEnd(int var1);

    public Position withSource(SourceFile var1);

    public Position withShift(int var1);

    public Position focusStart();

    public Position focus();

    public Position focusEnd();

    public Position $bar(Position var1, Seq<Position> var2);

    public Position $bar(Position var1);

    public Position $up(int var1);

    public Position $bar$up(Position var1);

    public Position $up$bar(Position var1);

    public Position union(Position var1);

    public boolean includes(Position var1);

    public boolean properlyIncludes(Position var1);

    public boolean precedes(Position var1);

    public boolean properlyPrecedes(Position var1);

    public boolean sameRange(Position var1);

    public boolean overlaps(Position var1);

    public int line();

    public int column();

    public String lineContent();

    public String lineCaret();

    public String lineCarat();

    public String showError(String var1);

    public String showDebug();

    public String show();
}

