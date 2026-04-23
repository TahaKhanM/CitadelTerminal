/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Option;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.NoPosition$;
import scala.reflect.internal.util.NoSourceFile$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.SourceFile;
import scala.reflect.macros.Attachments;
import scala.runtime.Nothing$;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
@ScalaSignature(bytes="\u0006\u0001I;Q!\u0001\u0002\t\u0002.\t!BT8Q_NLG/[8o\u0015\t\u0019A!\u0001\u0003vi&d'BA\u0003\u0007\u0003!Ig\u000e^3s]\u0006d'BA\u0004\t\u0003\u001d\u0011XM\u001a7fGRT\u0011!C\u0001\u0006g\u000e\fG.Y\u0002\u0001!\taQ\"D\u0001\u0003\r\u0015q!\u0001#!\u0010\u0005)qu\u000eU8tSRLwN\\\n\u0005\u001bA\u0019r\u0003\u0005\u0002\r#%\u0011!C\u0001\u0002\u0012+:$WMZ5oK\u0012\u0004vn]5uS>t\u0007C\u0001\u000b\u0016\u001b\u0005A\u0011B\u0001\f\t\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\u0006\r\n\u0005eA!\u0001D*fe&\fG.\u001b>bE2,\u0007\"B\u000e\u000e\t\u0003a\u0012A\u0002\u001fj]&$h\bF\u0001\f\u0011\u001dqR\"!A\u0005B}\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001\u0011\u0011\u0005\u00052S\"\u0001\u0012\u000b\u0005\r\"\u0013\u0001\u00027b]\u001eT\u0011!J\u0001\u0005U\u00064\u0018-\u0003\u0002(E\t11\u000b\u001e:j]\u001eDq!K\u0007\u0002\u0002\u0013\u0005!&\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001,!\t!B&\u0003\u0002.\u0011\t\u0019\u0011J\u001c;\t\u000f=j\u0011\u0011!C\u0001a\u0005q\u0001O]8ek\u000e$X\t\\3nK:$HCA\u00195!\t!\"'\u0003\u00024\u0011\t\u0019\u0011I\\=\t\u000fUr\u0013\u0011!a\u0001W\u0005\u0019\u0001\u0010J\u0019\t\u000f]j\u0011\u0011!C!q\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001:!\rQT(M\u0007\u0002w)\u0011A\bC\u0001\u000bG>dG.Z2uS>t\u0017B\u0001 <\u0005!IE/\u001a:bi>\u0014\bb\u0002!\u000e\u0003\u0003%\t!Q\u0001\tG\u0006tW)];bYR\u0011!)\u0012\t\u0003)\rK!\u0001\u0012\u0005\u0003\u000f\t{w\u000e\\3b]\"9QgPA\u0001\u0002\u0004\t\u0004bB$\u000e\u0003\u0003%\t\u0005S\u0001\tQ\u0006\u001c\bnQ8eKR\t1\u0006C\u0004K\u001b\u0005\u0005I\u0011I&\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001\t\u0005\b\u001b6\t\t\u0011\"\u0003O\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0003=\u0003\"!\t)\n\u0005E\u0013#AB(cU\u0016\u001cG\u000f")
public final class NoPosition {
    public static String toString() {
        return NoPosition$.MODULE$.toString();
    }

    public static int hashCode() {
        return NoPosition$.MODULE$.hashCode();
    }

    public static boolean canEqual(Object object) {
        return NoPosition$.MODULE$.canEqual(object);
    }

    public static Iterator<Object> productIterator() {
        return NoPosition$.MODULE$.productIterator();
    }

    public static Object productElement(int n) {
        return NoPosition$.MODULE$.productElement(n);
    }

    public static int productArity() {
        return NoPosition$.MODULE$.productArity();
    }

    public static String productPrefix() {
        return NoPosition$.MODULE$.productPrefix();
    }

    public static Nothing$ end() {
        return NoPosition$.MODULE$.end();
    }

    public static Nothing$ point() {
        return NoPosition$.MODULE$.point();
    }

    public static Nothing$ start() {
        return NoPosition$.MODULE$.start();
    }

    public static NoSourceFile$ source() {
        return NoPosition$.MODULE$.source();
    }

    public static boolean isRange() {
        return NoPosition$.MODULE$.isRange();
    }

    public static boolean isDefined() {
        return NoPosition$.MODULE$.isDefined();
    }

    public static String show() {
        return NoPosition$.MODULE$.show();
    }

    public static String showDebug() {
        return NoPosition$.MODULE$.showDebug();
    }

    public static String showError(String string2) {
        return NoPosition$.MODULE$.showError(string2);
    }

    public static String lineCarat() {
        return NoPosition$.MODULE$.lineCarat();
    }

    public static String lineCaret() {
        return NoPosition$.MODULE$.lineCaret();
    }

    public static String lineContent() {
        return NoPosition$.MODULE$.lineContent();
    }

    public static int column() {
        return NoPosition$.MODULE$.column();
    }

    public static int line() {
        return NoPosition$.MODULE$.line();
    }

    public static boolean overlaps(Position position) {
        return NoPosition$.MODULE$.overlaps(position);
    }

    public static boolean sameRange(Position position) {
        return NoPosition$.MODULE$.sameRange(position);
    }

    public static boolean properlyPrecedes(Position position) {
        return NoPosition$.MODULE$.properlyPrecedes(position);
    }

    public static boolean precedes(Position position) {
        return NoPosition$.MODULE$.precedes(position);
    }

    public static boolean properlyIncludes(Position position) {
        return NoPosition$.MODULE$.properlyIncludes(position);
    }

    public static boolean includes(Position position) {
        return NoPosition$.MODULE$.includes(position);
    }

    public static Position union(Position position) {
        return NoPosition$.MODULE$.union(position);
    }

    public static Position $up$bar(Position position) {
        return NoPosition$.MODULE$.$up$bar(position);
    }

    public static Position $bar$up(Position position) {
        return NoPosition$.MODULE$.$bar$up(position);
    }

    public static Position $up(int n) {
        return NoPosition$.MODULE$.$up(n);
    }

    public static Position $bar(Position position) {
        return NoPosition$.MODULE$.$bar(position);
    }

    public static Position $bar(Position position, Seq<Position> seq) {
        return NoPosition$.MODULE$.$bar(position, seq);
    }

    public static Position focusEnd() {
        return NoPosition$.MODULE$.focusEnd();
    }

    public static Position focus() {
        return NoPosition$.MODULE$.focus();
    }

    public static Position focusStart() {
        return NoPosition$.MODULE$.focusStart();
    }

    public static Position withShift(int n) {
        return NoPosition$.MODULE$.withShift(n);
    }

    public static Position withSource(SourceFile sourceFile) {
        return NoPosition$.MODULE$.withSource(sourceFile);
    }

    public static Position withEnd(int n) {
        return NoPosition$.MODULE$.withEnd(n);
    }

    public static Position withPoint(int n) {
        return NoPosition$.MODULE$.withPoint(n);
    }

    public static Position withStart(int n) {
        return NoPosition$.MODULE$.withStart(n);
    }

    public static Position makeTransparent() {
        return NoPosition$.MODULE$.makeTransparent();
    }

    public static int pointOrElse(int n) {
        return NoPosition$.MODULE$.pointOrElse(n);
    }

    public static boolean isOpaqueRange() {
        return NoPosition$.MODULE$.isOpaqueRange();
    }

    public static boolean isOffset() {
        return NoPosition$.MODULE$.isOffset();
    }

    public static boolean isTransparent() {
        return NoPosition$.MODULE$.isTransparent();
    }

    public static Position finalPosition() {
        return NoPosition$.MODULE$.finalPosition();
    }

    public static int endOrPoint() {
        return NoPosition$.MODULE$.endOrPoint();
    }

    public static int startOrPoint() {
        return NoPosition$.MODULE$.startOrPoint();
    }

    public static Position withSource(SourceFile sourceFile, int n) {
        return NoPosition$.MODULE$.withSource(sourceFile, n);
    }

    public static Tuple2<String, String> lineWithCarat(int n) {
        return NoPosition$.MODULE$.lineWithCarat(n);
    }

    public static Position inUltimateSource(SourceFile sourceFile) {
        return NoPosition$.MODULE$.inUltimateSource(sourceFile);
    }

    public static String dbgString() {
        return NoPosition$.MODULE$.dbgString();
    }

    public static int safeLine() {
        return NoPosition$.MODULE$.safeLine();
    }

    public static Position toSingleLine() {
        return NoPosition$.MODULE$.toSingleLine();
    }

    public static Option<Object> offset() {
        return NoPosition$.MODULE$.offset();
    }

    public static int end() {
        return NoPosition$.MODULE$.end();
    }

    public static int point() {
        return NoPosition$.MODULE$.point();
    }

    public static int start() {
        return NoPosition$.MODULE$.start();
    }

    public static SourceFile source() {
        return NoPosition$.MODULE$.source();
    }

    public static Attachments withPos(Position position) {
        return NoPosition$.MODULE$.withPos(position);
    }

    public static Position pos() {
        return NoPosition$.MODULE$.pos();
    }

    public static boolean isEmpty() {
        return NoPosition$.MODULE$.isEmpty();
    }

    public static <T> Attachments remove(ClassTag<T> classTag) {
        return NoPosition$.MODULE$.remove(classTag);
    }

    public static <T> Attachments update(T t, ClassTag<T> classTag) {
        return NoPosition$.MODULE$.update(t, classTag);
    }

    public static <T> boolean contains(ClassTag<T> classTag) {
        return NoPosition$.MODULE$.contains(classTag);
    }

    public static <T> Option<T> get(ClassTag<T> classTag) {
        return NoPosition$.MODULE$.get(classTag);
    }

    public static Set<Object> all() {
        return NoPosition$.MODULE$.all();
    }
}

