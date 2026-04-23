/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.Exprs$Expr$;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeTags;
import scala.reflect.api.TypeTags$TypeTag$;
import scala.reflect.api.TypeTags$WeakTypeTag$;
import scala.reflect.api.Types;

@ScalaSignature(bytes="\u0006\u0001\u0005-g!C\u0001\u0003!\u0003\r\t!CAd\u0005\u001d\tE.[1tKNT!a\u0001\u0003\u0002\r5\f7M]8t\u0015\t)a!A\u0004sK\u001adWm\u0019;\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\u0015A\u00111\u0002D\u0007\u0002\r%\u0011QB\u0002\u0002\u0007\u0003:L(+\u001a4\t\u000b=\u0001A\u0011\u0001\t\u0002\r\u0011Jg.\u001b;%)\u0005\t\u0002CA\u0006\u0013\u0013\t\u0019bA\u0001\u0003V]&$X\u0001B\u000b\u0001\u0001Y\u0011aaU=nE>d\u0007CA\f \u001d\tA\u0012$D\u0001\u0001\u0013\tQ2$\u0001\u0005v]&4XM]:f\u0013\taRDA\u0004D_:$X\r\u001f;\u000b\u0005y\u0011\u0011\u0001\u00032mC\u000e\\'m\u001c=\n\u0005U\u0001\u0013BA\u0011#\u0005\u001d\u0019\u00160\u001c2pYNT!a\t\u0003\u0002\u0007\u0005\u0004\u0018.\u0002\u0003&\u0001\u00011#\u0001\u0002+za\u0016\u0004\"aF\u0014\n\u0005\u0015B\u0013BA\u0015#\u0005\u0015!\u0016\u0010]3t\u000b\u0011Y\u0003\u0001\u0001\u0017\u0003\t9\u000bW.\u001a\t\u0003/5J!a\u000b\u0018\n\u0005=\u0012#!\u0002(b[\u0016\u001cX\u0001B\u0019\u0001\u0001I\u0012\u0001\u0002V3s[:\u000bW.\u001a\t\u0003/MJ!!\r\u0018\u0006\tU\u0002\u0001A\u000e\u0002\t)f\u0004XMT1nKB\u0011qcN\u0005\u0003k9*A!\u000f\u0001\u0001u\t!AK]3f!\t92(\u0003\u0002:y%\u0011QH\t\u0002\u0006)J,Wm]\u0003\u0005\u007f\u0001\u0001\u0001I\u0001\u0005Q_NLG/[8o!\t9\u0012)\u0003\u0002@\u0005&\u00111I\t\u0002\n!>\u001c\u0018\u000e^5p]N,A!\u0012\u0001\u0001\r\n)1kY8qKB\u0011qcR\u0005\u0003\u000b\"K!!\u0013\u0012\u0003\rM\u001bw\u000e]3t\u000b\u0011Y\u0005\u0001\u0001'\u0003\u00135{G-\u001b4jKJ\u001c\bCA\fN\u0013\tYE(\u0002\u0003P\u0001\u0001\u0001&a\u0001*v]B\u0011q#U\u0005\u0003\u001fJK!a\u0015\u0002\u0003\u0011Us\u0017N^3sg\u0016DCAT+Y5B\u00111BV\u0005\u0003/\u001a\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3eC\u0005I\u0016\u0001W2/K:\u001cGn\\:j]\u001e$&/Z3.gRLH.\u001a\u0011B!&\u001b\b%\u0019:fA9|w\u000f\t3faJ,7-\u0019;fIn\u00023m\u001c8tk2$\b\u0005\u001e5fAM\u001c\u0017\r\\1e_\u000e\u0004cm\u001c:![>\u0014X\rI5oM>\u0014X.\u0019;j_:\f\u0013aW\u0001\u0007e9\n\u0014G\f\u0019\u0006\tu\u0003\u0001A\u0018\u0002\u0010\u0007>l\u0007/\u001b7bi&|g.\u00168jiB\u0011qcX\u0005\u0003;JCC\u0001X+Y5\u0016!!\r\u0001\u0001d\u0005\u0011)\u0005\u0010\u001d:\u0016\u0005\u0011T\u0007cA\ffQ&\u0011!MZ\u0005\u0003O\n\u0012Q!\u0012=qeN\u0004\"!\u001b6\r\u0001\u001111.\u0019CC\u00021\u0014\u0011\u0001V\t\u0003[B\u0004\"a\u00038\n\u0005=4!a\u0002(pi\"Lgn\u001a\t\u0003\u0017EL!A\u001d\u0004\u0003\u0007\u0005s\u0017\u0010C\u0004u\u0001\t\u0007I\u0011A;\u0002\t\u0015C\bO]\u000b\u0002m:\u0011qc^\u0005\u0003i\u001aDa!\u001f\u0001!\u0002\u00131\u0018!B#yaJ\u0004\u0003\"\u0002;\u0001\r\u0003YXc\u0001?\u0002\u0002Q\u0019Q0a\u0007\u0015\u0007y\f\u0019\u0001E\u0002\u0019C~\u00042![A\u0001\t\u0015Y'P1\u0001m\u0011%\t)A_A\u0001\u0002\b\t9!\u0001\u0006fm&$WM\\2fIE\u0002B\u0001GA\u0005\u007f\u00161\u00111\u0002\u0001\u0001\u0003\u001b\u00111bV3bWRK\b/\u001a+bOV!\u0011qBA\r!\u00159\u0012\u0011CA\f\u0013\u0011\tY!a\u0005\n\u0007\u0005U!E\u0001\u0005UsB,G+Y4t!\rI\u0017\u0011\u0004\u0003\u0007W\u0006%!\u0019\u00017\t\u000f\u0005u!\u00101\u0001\u0002 \u0005!AO]3f!\tA\u0002(\u0002\u0004\u0002$\u0001\u0001\u0011Q\u0005\u0002\b)f\u0004X\rV1h+\u0011\t9#!\f\u0011\u000b]\tI#a\u000b\n\t\u0005\r\u00121\u0003\t\u0004S\u00065BAB6\u0002\"\t\u0007A\u000eC\u0005\u00022\u0001\u0011\r\u0011\"\u0001\u00024\u0005Yq+Z1l)f\u0004X\rV1h+\t\t)DD\u0002\u0018\u0003oIA!!\r\u0002\u0014!A\u00111\b\u0001!\u0002\u0013\t)$\u0001\u0007XK\u0006\\G+\u001f9f)\u0006<\u0007\u0005C\u0005\u0002@\u0001\u0011\r\u0011\"\u0001\u0002B\u00059A+\u001f9f)\u0006<WCAA\"\u001d\r9\u0012QI\u0005\u0005\u0003\u007f\t\u0019\u0002\u0003\u0005\u0002J\u0001\u0001\u000b\u0011BA\"\u0003!!\u0016\u0010]3UC\u001e\u0004\u0003bBA\u0019\u0001\u0019\u0005\u0011QJ\u000b\u0005\u0003\u001f\n)\u0006\u0006\u0003\u0002R\u0005]\u0003#\u0002\r\u0002\n\u0005M\u0003cA5\u0002V\u001111.a\u0013C\u00021D\u0001\"!\u0017\u0002L\u0001\u0007\u00111L\u0001\u0004iB,\u0007C\u0001\r%\u0011\u001d\ty\u0004\u0001D\u0001\u0003?*B!!\u0019\u0002hQ!\u00111MA5!\u0015A\u0012\u0011EA3!\rI\u0017q\r\u0003\u0007W\u0006u#\u0019\u00017\t\u0011\u0005e\u0013Q\fa\u0001\u00037Bq!!\u001c\u0001\t\u0003\ty'A\u0006xK\u0006\\G+\u001f9f)\u0006<W\u0003BA9\u0003o\"B!a\u001d\u0002zA)\u0001$!\u0003\u0002vA\u0019\u0011.a\u001e\u0005\r-\fYG1\u0001m\u0011!\tY(a\u001bA\u0004\u0005M\u0014!B1ui\u0006<\u0007bBA@\u0001\u0011\u0005\u0011\u0011Q\u0001\bif\u0004X\rV1h+\u0011\t\u0019)!#\u0015\t\u0005\u0015\u00151\u0012\t\u00061\u0005\u0005\u0012q\u0011\t\u0004S\u0006%EAB6\u0002~\t\u0007A\u000e\u0003\u0005\u0002\u000e\u0006u\u00049AAC\u0003\u0011!H/Y4\t\u000f\u0005E\u0005\u0001\"\u0001\u0002\u0014\u0006Qq/Z1l)f\u0004Xm\u00144\u0016\t\u0005U\u0015Q\u0014\u000b\u0005\u00037\n9\n\u0003\u0005\u0002|\u0005=\u00059AAM!\u0015A\u0012\u0011BAN!\rI\u0017Q\u0014\u0003\u0007W\u0006=%\u0019\u00017\t\u000f\u0005\u0005\u0006\u0001\"\u0001\u0002$\u00061A/\u001f9f\u001f\u001a,B!!*\u0002.R!\u00111LAT\u0011!\ti)a(A\u0004\u0005%\u0006#\u0002\r\u0002\"\u0005-\u0006cA5\u0002.\u001211.a(C\u00021Dq!!-\u0001\t\u0003\t\u0019,\u0001\u0005ts6\u0014w\u000e\\(g+\u0011\t),!2\u0015\t\u0005]\u0016Q\u0018\t\u0004/\u0005e\u0016bAA^A\tQA+\u001f9f'fl'm\u001c7\t\u0015\u0005}\u0016qVA\u0001\u0002\b\t\t-\u0001\u0006fm&$WM\\2fII\u0002R\u0001GA\u0005\u0003\u0007\u00042![Ac\t\u0019Y\u0017q\u0016b\u0001YB\u0019\u0011\u0011Z\u000e\u000e\u0003u\u0001")
public interface Aliases {
    public void scala$reflect$macros$Aliases$_setter_$Expr_$eq(Exprs$Expr$ var1);

    public void scala$reflect$macros$Aliases$_setter_$WeakTypeTag_$eq(TypeTags$WeakTypeTag$ var1);

    public void scala$reflect$macros$Aliases$_setter_$TypeTag_$eq(TypeTags$TypeTag$ var1);

    public Exprs$Expr$ Expr();

    public <T> Exprs.Expr<T> Expr(Trees.TreeApi var1, TypeTags.WeakTypeTag<T> var2);

    public TypeTags$WeakTypeTag$ WeakTypeTag();

    public TypeTags$TypeTag$ TypeTag();

    public <T> TypeTags.WeakTypeTag<T> WeakTypeTag(Types.TypeApi var1);

    public <T> TypeTags.TypeTag<T> TypeTag(Types.TypeApi var1);

    public <T> TypeTags.WeakTypeTag<T> weakTypeTag(TypeTags.WeakTypeTag<T> var1);

    public <T> TypeTags.TypeTag<T> typeTag(TypeTags.TypeTag<T> var1);

    public <T> Types.TypeApi weakTypeOf(TypeTags.WeakTypeTag<T> var1);

    public <T> Types.TypeApi typeOf(TypeTags.TypeTag<T> var1);

    public <T> Symbols.TypeSymbolApi symbolOf(TypeTags.WeakTypeTag<T> var1);
}

