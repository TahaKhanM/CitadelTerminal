/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Names;

@ScalaSignature(bytes="\u0006\u0001A4\u0001\"\u0001\u0002\u0011\u0002\u0007\u0005\u0011\u0002\u001c\u0002\u000e'R\fg\u000eZ1sI:\u000bW.Z:\u000b\u0005\r!\u0011aA1qS*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\f7\u0001A\n\u0003\u0001)\u0001\"a\u0003\u0007\u000e\u0003\u0019I!!\u0004\u0004\u0003\r\u0005s\u0017PU3g\u0011\u0015y\u0001\u0001\"\u0001\u0011\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0003\u0005\u0002\f%%\u00111C\u0002\u0002\u0005+:LG\u000fC\u0004\u0016\u0001\t\u0007i\u0011\u0001\f\u0002\u00079lW-F\u0001\u0018!\tA\u0012$D\u0001\u0001\r\u001dQ\u0002\u0001%A\u0012\u0002m\u0011A\u0002V3s[:\u000bW.Z:Ba&\u001c2!\u0007\u0006\u001d!\tARDB\u0004\u001f\u0001A\u0005\u0019\u0013A\u0010\u0003\u00119\u000bW.Z:Ba&\u001c\"!\b\u0006\u0005\u000b\u0005j\"\u0011\u0001\u0012\u0003\u00119\u000bW.\u001a+za\u0016\f\"a\t\u0014\u0011\u0005-!\u0013BA\u0013\u0007\u0005\u0011qU\u000f\u001c7\u0011\u0005a9\u0013B\u0001\u0015*\u0005\u0011q\u0015-\\3\n\u0005)\u0012!!\u0002(b[\u0016\u001c\bb\u0002\u0017\u001e\u0005\u00045\t!L\u0001\t/&cEiQ!S\tV\ta\u0006\u0005\u00020A5\tQ\u0004C\u00042;\t\u0007i\u0011A\u0017\u0002\u000b\u0015k\u0005\u000bV-\t\u000fMj\"\u0019!D\u0001[\u0005)QI\u0015*P%\"9Q'\bb\u0001\u000e\u0003i\u0013a\u0002)B\u0007.\u000bu)R\u0003\u0005Ce\u0001q\u0007\u0005\u0002\u0019q%\u0011\u0011(\u000b\u0002\t)\u0016\u0014XNT1nK\"91(\u0007b\u0001\u000e\u0003a\u0014aC\"P\u001dN#&+V\"U\u001fJ+\u0012!\u0010\t\u0003}Yj\u0011!\u0007\u0005\b\u0001f\u0011\rQ\"\u0001=\u0003\u001d\u0011vj\u0014+Q\u0017\u001eCqAQ\rC\u0002\u001b\u0005A(\u0001\nF\u001bB#\u0016l\u0018)B\u0007.\u000bu)R0O\u00036+\u0005b\u0002#\u001a\u0005\u00045\t!R\u0001\u0014\u0019>\u001b\u0015\tT0T+\u001a3\u0015\nW0T)JKejR\u000b\u0002\rB\u0011qI\u0013\b\u0003\u0017!K!!\u0013\u0004\u0002\rA\u0013X\rZ3g\u0013\tYEJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0013\u001aAC\u0001\u0006(R'B\u00111bT\u0005\u0003!\u001a\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3eC\u0005\u0011\u0016aF+tK\u0002\u0002G/\u001a:n\u001d\u0006lWm\u001d1!S:\u001cH/Z1eC\u0005!\u0016A\u0002\u001a/cEr\u0003\u0007C\u0004W\u0001\t\u0007i\u0011\u0001\f\u0002\u0013Q,'/\u001c(b[\u0016\u001c\bb\u0002-\u0001\u0005\u00045\t!W\u0001\u0006iBtW.Z\u000b\u00025B\u0011\u0001d\u0017\u0004\b9\u0002\u0001\n1%\u0001^\u00051!\u0016\u0010]3OC6,7/\u00119j'\rY&\u0002H\u0003\u0005Cm\u0003q\f\u0005\u0002\u0019A&\u0011\u0011-\u000b\u0002\t)f\u0004XMT1nK\"91m\u0017b\u0001\u000e\u0003!\u0017!D,J\u0019\u0012\u001b\u0015I\u0015#`'R\u000b%+F\u0001f!\t1g,D\u0001\\Q\u00119f\n[*\"\u0003%\fq#V:fA\u0001$\u0018\u0010]3OC6,7\u000f\u0019\u0011j]N$X-\u00193\t\u000f-\u0004!\u0019!D\u00013\u0006IA/\u001f9f\u001d\u0006lWm\u001d\t\u0003[:l\u0011AA\u0005\u0003_\n\u0011\u0001\"\u00168jm\u0016\u00148/\u001a")
public interface StandardNames {
    public TermNamesApi nme();

    public TermNamesApi termNames();

    public TypeNamesApi tpnme();

    public TypeNamesApi typeNames();

    public interface NamesApi {
        public Names.NameApi WILDCARD();

        public Names.NameApi EMPTY();

        public Names.NameApi ERROR();

        public Names.NameApi PACKAGE();
    }

    public interface TermNamesApi
    extends NamesApi {
        public Names.TermNameApi CONSTRUCTOR();

        public Names.TermNameApi ROOTPKG();

        public Names.TermNameApi EMPTY_PACKAGE_NAME();

        public String LOCAL_SUFFIX_STRING();
    }

    public interface TypeNamesApi
    extends NamesApi {
        public Names.TypeNameApi WILDCARD_STAR();
    }
}

