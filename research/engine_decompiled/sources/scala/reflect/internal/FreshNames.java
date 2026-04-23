/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Some;
import scala.StringContext;
import scala.collection.LinearSeqOptimized;
import scala.collection.immutable.List;
import scala.collection.immutable.StringOps;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.FreshNames$FreshNameExtractor$;
import scala.reflect.internal.Names;
import scala.reflect.internal.util.FreshNameCreator;
import scala.util.matching.Regex;
import scala.util.matching.Regex$;

@ScalaSignature(bytes="\u0006\u0001\u0005\ra\u0001C\u0001\u0003!\u0003\r\t!\u0003<\u0003\u0015\u0019\u0013Xm\u001d5OC6,7O\u0003\u0002\u0004\t\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\u0003\t\u0003\u00171i\u0011AB\u0005\u0003\u001b\u0019\u0011a!\u00118z%\u00164\u0007\"B\b\u0001\t\u0003\u0001\u0012A\u0002\u0013j]&$H\u0005F\u0001\u0012!\tY!#\u0003\u0002\u0014\r\t!QK\\5u\u0011\u001d)\u0002A1A\u0005\u0002Y\tac\u001a7pE\u0006dgI]3tQ:\u000bW.Z\"sK\u0006$xN]\u000b\u0002/A\u0011\u0001dG\u0007\u00023)\u0011!DA\u0001\u0005kRLG.\u0003\u0002\u001d3\t\u0001bI]3tQ:\u000bW.Z\"sK\u0006$xN\u001d\u0005\u0007=\u0001\u0001\u000b\u0011B\f\u0002/\u001ddwNY1m\rJ,7\u000f\u001b(b[\u0016\u001c%/Z1u_J\u0004\u0003\"\u0002\u0011\u0001\r\u00031\u0012aF2veJ,g\u000e\u001e$sKNDg*Y7f\u0007J,\u0017\r^8s\u0011\u0015\u0011\u0003\u0001\"\u0001$\u000351'/Z:i)\u0016\u0014XNT1nKR\u0011A%\f\u000b\u0003K-\u0002\"AJ\u0014\u000e\u0003\u0001I!\u0001K\u0015\u0003\u0011Q+'/\u001c(b[\u0016L!A\u000b\u0002\u0003\u000b9\u000bW.Z:\t\u000b1\n\u00039A\f\u0002\u000f\r\u0014X-\u0019;pe\"9a&\tI\u0001\u0002\u0004y\u0013A\u00029sK\u001aL\u0007\u0010\u0005\u00021g9\u00111\"M\u0005\u0003e\u0019\ta\u0001\u0015:fI\u00164\u0017B\u0001\u001b6\u0005\u0019\u0019FO]5oO*\u0011!G\u0002\u0005\u0006o\u0001!\t\u0001O\u0001\u000eMJ,7\u000f\u001b+za\u0016t\u0015-\\3\u0015\u0005erDC\u0001\u001e>!\t13(\u0003\u0002=S\tAA+\u001f9f\u001d\u0006lW\rC\u0003-m\u0001\u000fq\u0003C\u0003/m\u0001\u0007qF\u0002\u0003A\u0001\u0001\t%A\u0005$sKNDg*Y7f\u000bb$(/Y2u_J\u001c\"a\u0010\u0006\t\u0011\r{$\u0011!Q\u0001\n=\nQb\u0019:fCR|'\u000f\u0015:fM&D\b\"B#@\t\u00031\u0015A\u0002\u001fj]&$h\b\u0006\u0002H\u0011B\u0011ae\u0010\u0005\b\u0007\u0012\u0003\n\u00111\u00010\u0011\u001dQuH1A\u0005\u0002-\u000bAB\u001a:fg\"d\u0017PT1nK\u0012,\u0012\u0001\u0014\t\u0003\u001bFk\u0011A\u0014\u0006\u0003\u001fB\u000b\u0001\"\\1uG\"Lgn\u001a\u0006\u00035\u0019I!A\u0015(\u0003\u000bI+w-\u001a=\t\rQ{\u0004\u0015!\u0003M\u000351'/Z:iYft\u0015-\\3eA!)ak\u0010C\u0001/\u00069QO\\1qa2LHC\u0001-\\!\rY\u0011lL\u0005\u00035\u001a\u0011aa\u00149uS>t\u0007\"\u0002/V\u0001\u0004i\u0016\u0001\u00028b[\u0016\u0004\"A\n0\n\u0005}K#\u0001\u0002(b[\u0016<q!\u0019\u0001\u0002\u0002#\u0005!-\u0001\nGe\u0016\u001c\bNT1nK\u0016CHO]1di>\u0014\bC\u0001\u0014d\r\u001d\u0001\u0005!!A\t\u0002\u0011\u001c\"a\u0019\u0006\t\u000b\u0015\u001bG\u0011\u00014\u0015\u0003\tDq\u0001[2\u0012\u0002\u0013\u0005\u0011.A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%M\u000b\u0002U*\u0012qf[\u0016\u0002YB\u0011QN]\u0007\u0002]*\u0011q\u000e]\u0001\nk:\u001c\u0007.Z2lK\u0012T!!\u001d\u0004\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002t]\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\t\u000fU\u0004\u0011\u0013!C\u0001S\u00069bM]3tQR+'/\u001c(b[\u0016$C-\u001a4bk2$H%\r\n\u0004of\\h\u0001\u0002=\u0001\u0001Y\u0014A\u0002\u0010:fM&tW-\\3oiz\u0002\"A\u001f\u0001\u000e\u0003\t\u00112\u0001`?\u007f\r\u0011A\b\u0001A>\u0011\u0005iL\u0003C\u0001>\u0000\u0013\r\t\tA\u0001\u0002\t'R$g*Y7fg\u0002")
public interface FreshNames {
    public void scala$reflect$internal$FreshNames$_setter_$globalFreshNameCreator_$eq(FreshNameCreator var1);

    public FreshNameCreator globalFreshNameCreator();

    public FreshNameCreator currentFreshNameCreator();

    public Names.TermName freshTermName(String var1, FreshNameCreator var2);

    public String freshTermName$default$1();

    public Names.TypeName freshTypeName(String var1, FreshNameCreator var2);

    public FreshNames$FreshNameExtractor$ FreshNameExtractor();

    public class FreshNameExtractor {
        private final Regex freshlyNamed;

        public Regex freshlyNamed() {
            return this.freshlyNamed;
        }

        public Option<String> unapply(Names.Name name) {
            Option option;
            String string2 = name.toString();
            Option<List<String>> option2 = this.freshlyNamed().unapplySeq(string2);
            if (!option2.isEmpty() && option2.get() != null && ((LinearSeqOptimized)option2.get()).lengthCompare(1) == 0) {
                String prefix = (String)((LinearSeqOptimized)option2.get()).apply(0);
                option = new Some<String>(prefix);
            } else {
                option = None$.MODULE$;
            }
            return option;
        }

        public /* synthetic */ FreshNames scala$reflect$internal$FreshNames$FreshNameExtractor$$$outer() {
            return FreshNames.this;
        }

        public FreshNameExtractor(String creatorPrefix) {
            if (FreshNames.this == null) {
                throw null;
            }
            String pre = creatorPrefix.isEmpty() ? "" : Regex$.MODULE$.quote(creatorPrefix);
            String string2 = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", "(.*?)\\\\d*"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{pre}));
            Predef$ predef$ = Predef$.MODULE$;
            this.freshlyNamed = new StringOps(string2).r();
        }
    }
}

