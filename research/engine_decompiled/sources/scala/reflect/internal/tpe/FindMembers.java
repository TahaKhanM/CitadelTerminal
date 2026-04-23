/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import java.io.Serializable;
import scala.Predef$;
import scala.Tuple2;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.TypesStats$;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001\tEa!C\u0001\u0003!\u0003\r\ta\u0003B\u0005\u0005-1\u0015N\u001c3NK6\u0014WM]:\u000b\u0005\r!\u0011a\u0001;qK*\u0011QAB\u0001\tS:$XM\u001d8bY*\u0011q\u0001C\u0001\be\u00164G.Z2u\u0015\u0005I\u0011!B:dC2\f7\u0001A\n\u0003\u00011\u0001\"!\u0004\b\u000e\u0003!I!a\u0004\u0005\u0003\r\u0005s\u0017PU3g\u0011\u0015\t\u0002\u0001\"\u0001\u0013\u0003\u0019!\u0013N\\5uIQ\t1\u0003\u0005\u0002\u000e)%\u0011Q\u0003\u0003\u0002\u0005+:LGO\u0002\u0004\u0018\u0001\u0005\u0005A\u0001\u0007\u0002\u000f\r&tG-T3nE\u0016\u0014()Y:f+\tIRg\u0005\u0002\u0017\u0019!A1A\u0006B\u0001B\u0003%1\u0004\u0005\u0002\u001d;5\t\u0001!\u0003\u0002\u001f?\t!A+\u001f9f\u0013\t\u0001CAA\u0003UsB,7\u000f\u0003\u0005#-\t\u0005\t\u0015!\u0003$\u0003\u0011q\u0017-\\3\u0011\u0005q!\u0013BA\u0013'\u0005\u0011q\u0015-\\3\n\u0005\u001d\"!!\u0002(b[\u0016\u001c\b\u0002C\u0015\u0017\u0005\u0003\u0005\u000b\u0011\u0002\u0016\u0002\u001b\u0015D8\r\\;eK\u00124E.Y4t!\ti1&\u0003\u0002-\u0011\t!Aj\u001c8h\u0011!qcC!A!\u0002\u0013Q\u0013!\u0004:fcVL'/\u001a3GY\u0006<7\u000fC\u00031-\u0011\u0005\u0011'\u0001\u0004=S:LGO\u0010\u000b\u0006eyz\u0004)\u0011\t\u00049Y\u0019\u0004C\u0001\u001b6\u0019\u0001!QA\u000e\fC\u0002]\u0012\u0011\u0001V\t\u0003qm\u0002\"!D\u001d\n\u0005iB!a\u0002(pi\"Lgn\u001a\t\u0003\u001bqJ!!\u0010\u0005\u0003\u0007\u0005s\u0017\u0010C\u0003\u0004_\u0001\u00071\u0004C\u0003#_\u0001\u00071\u0005C\u0003*_\u0001\u0007!\u0006C\u0003/_\u0001\u0007!\u0006C\u0004D-\t\u0007I\u0011\u0003#\u0002\u001f%t\u0017\u000e\u001e\"bg\u0016\u001cE.Y:tKN,\u0012!\u0012\t\u0004\r:\u000bfBA$M\u001d\tA5*D\u0001J\u0015\tQ%\"\u0001\u0004=e>|GOP\u0005\u0002\u0013%\u0011Q\nC\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0005K\u0001\u0003MSN$(BA'\t!\ta\"+\u0003\u0002T)\n11+_7c_2L!!\u0016\u0003\u0003\u000fMKXNY8mg\"1qK\u0006Q\u0001\n\u0015\u000b\u0001#\u001b8ji\n\u000b7/Z\"mCN\u001cXm\u001d\u0011\t\re3\u0002\u0015)\u0003R\u00039y6/\u001a7fGR|'o\u00117bgNDQa\u0017\f\u0005\nq\u000bQb]3mK\u000e$xN]\"mCN\u001cX#A)\t\ry3\u0002\u0015)\u0003\u001c\u0003\u0015y6/\u001a7g\u0011\u0015\u0001g\u0003\"\u0005b\u0003\u0011\u0019X\r\u001c4\u0016\u0003mAQa\u0019\f\u0005\u0002\u0011\fQ!\u00199qYf$\u0012a\r\u0005\u0006MZ1\tbZ\u0001\u0007e\u0016\u001cX\u000f\u001c;\u0016\u0003MBQ!\u001b\f\u0005\n\u001d\f!d]3be\u000eD7i\u001c8de\u0016$X\r\u00165f]\u0012+g-\u001a:sK\u0012DQa\u001b\f\u0005\n1\fqb^1mW\n\u000b7/Z\"mCN\u001cXm\u001d\u000b\u0004[B\u0014\bCA\u0007o\u0013\ty\u0007BA\u0004C_>dW-\u00198\t\u000bET\u0007\u0019\u0001\u0016\u0002\u0011I,\u0017/^5sK\u0012DQa\u001d6A\u0002)\n\u0001\"\u001a=dYV$W\r\u001a\u0005\u0006kZ1\tB^\u0001\rg\"|'\u000f^\"je\u000e,\u0018\u000e\u001e\u000b\u0003[^DQ\u0001\u001f;A\u0002E\u000b1a]=n\u0011\u0015QhC\"\u0005|\u00039\tG\rZ'f[\n,'/\u00134OK^$\"a\u0005?\t\u000baL\b\u0019A)\t\u000by4B\u0011B@\u0002#%\u001c\bk\u001c;f]RL\u0017\r\\'f[\n,'\u000fF\u0006n\u0003\u0003\t\u0019!a\u0002\u0002\f\u0005=\u0001\"\u0002=~\u0001\u0004\t\u0006BBA\u0003{\u0002\u0007!&A\u0003gY\u0006<7\u000f\u0003\u0004\u0002\nu\u0004\r!U\u0001\u0006_^tWM\u001d\u0005\u0007\u0003\u001bi\b\u0019A7\u00027M,WM\u001c$jeN$hj\u001c8SK\u001aLg.Z7f]R\u001cE.Y:t\u0011\u0019\t\t\" a\u0001\u000b\u0006\t\"/\u001a4j]\u0016lWM\u001c;QCJ,g\u000e^:\t\u000f\u0005Ua\u0003\"\u0005\u0002\u0018\u0005Y\u0011n\u001d(fo6+WNY3s)\u0015i\u0017\u0011DA\u000f\u0011\u001d\tY\"a\u0005A\u0002E\u000ba!\\3nE\u0016\u0014\bbBA\u0010\u0003'\u0001\r!U\u0001\u0006_RDWM\u001d\u0005\b\u0003G1\u0002\u0015)\u0003\u001c\u0003IyV.Z7cKJ$\u0016\u0010]3IS\u000e\u000b7\r[3\t\u000f\u0005\u001db\u0003)Q\u0005#\u0006)r,\\3nE\u0016\u0014H+\u001f9f\u0011&\u001c\u0015m\u00195f'fl\u0007bBA\u0016-\u0011E\u0011QF\u0001\r[\u0016l'-\u001a:UsB,\u0007*\u001b\u000b\u00047\u0005=\u0002B\u0002=\u0002*\u0001\u0007\u0011\u000bC\u0004\u00024Y!\t\"!\u000e\u0002\u001b5,WNY3s)f\u0004X\rT8x)\rY\u0012q\u0007\u0005\u0007q\u0006E\u0002\u0019A)\t\u000f\u0005mb\u0003\"\u0003\u0002>\u0005\u0019b.\u0019:s_^4uN\u001d$j]\u0012lU-\u001c2feR\u00191$a\u0010\t\u000f\u0005\u0005\u0013\u0011\ba\u00017\u0005\u0011A\u000f\u001d\u0004\u0007\u0003\u0001\u0011a!!\u0012\u0014\t\u0005\r\u0013q\t\t\u00059Y\tI\u0005E\u0002\u001d\u0003\u0017JA!!\u0014\u0002P\t)1kY8qK&\u0019\u0011\u0011\u000b\u0003\u0003\rM\u001bw\u000e]3t\u0011%\u0019\u00111\tB\u0001B\u0003%1\u0004C\u0005*\u0003\u0007\u0012\t\u0011)A\u0005U!Ia&a\u0011\u0003\u0002\u0003\u0006IA\u000b\u0005\ba\u0005\rC\u0011AA.)!\ti&a\u0018\u0002b\u0005\r\u0004c\u0001\u000f\u0002D!11!!\u0017A\u0002mAa!KA-\u0001\u0004Q\u0003B\u0002\u0018\u0002Z\u0001\u0007!\u0006C\u0005\u0002h\u0005\r\u0003\u0015)\u0003\u0002J\u0005iq,\\3nE\u0016\u00148oU2pa\u0016D\u0001\"a\u001b\u0002D\u0011%\u0011QN\u0001\r[\u0016l'-\u001a:t'\u000e|\u0007/Z\u000b\u0003\u0003\u0013Bq!^A\"\t#\t\t\bF\u0002n\u0003gBa\u0001_A8\u0001\u0004\t\u0006b\u00024\u0002D\u0011E\u0011Q\u000e\u0005\bu\u0006\rC\u0011CA=)\r\u0019\u00121\u0010\u0005\u0007q\u0006]\u0004\u0019A)\u0007\u000f\u0005}\u0004A\u0001\u0004\u0002\u0002\nQa)\u001b8e\u001b\u0016l'-\u001a:\u0014\t\u0005u\u00141\u0011\t\u00049Y\t\u0006\"C\u0002\u0002~\t\u0005\t\u0015!\u0003\u001c\u0011%\u0011\u0013Q\u0010B\u0001B\u0003%1\u0005C\u0005*\u0003{\u0012\t\u0011)A\u0005U!Ia&! \u0003\u0002\u0003\u0006IA\u000b\u0005\u000b\u0003\u001f\u000biH!A!\u0002\u0013i\u0017AC:uC\ndWm\u00148ms\"9\u0001'! \u0005\u0002\u0005ME\u0003DAK\u0003/\u000bI*a'\u0002\u001e\u0006}\u0005c\u0001\u000f\u0002~!11!!%A\u0002mAaAIAI\u0001\u0004\u0019\u0003BB\u0015\u0002\u0012\u0002\u0007!\u0006\u0003\u0004/\u0003#\u0003\rA\u000b\u0005\b\u0003\u001f\u000b\t\n1\u0001n\u0011!\t\u0019+! !B\u0013\t\u0016aB7f[\n,'\u000f\r\u0005\t\u0003O\u000bi\b)Q\u0005\u000b\u00069Q.Z7cKJ\u001c\b\"CAV\u0003{\u0002\u000b\u0015BAW\u0003\u0015a\u0017m\u001d;N!\u00111\u0015qV)\n\u0007\u0005E\u0006K\u0001\u0007%G>dwN\u001c\u0013d_2|g\u000e\u0003\u0005\u00026\u0006uD\u0011BA\\\u0003E\u0019G.Z1s\u0003:$\u0017\t\u001a3SKN,H\u000e\u001e\u000b\u0004'\u0005e\u0006B\u0002=\u00024\u0002\u0007\u0011\u000bC\u0004v\u0003{\"\t\"!0\u0015\u00075\fy\f\u0003\u0004y\u0003w\u0003\r!\u0015\u0005\bu\u0006uD\u0011CAb)\r\u0019\u0012Q\u0019\u0005\u0007q\u0006\u0005\u0007\u0019A)\t\u0011\u0005%\u0017Q\u0010Q!\nm\t1bX7f[\n,'\u000f\r+qK\"A\u0011QZA?A\u0013%\u0011-\u0001\u0006nK6\u0014WM\u001d\u0019Ua\u0016D\u0001\"a\r\u0002~\u0011E\u0013\u0011\u001b\u000b\u00047\u0005M\u0007B\u0002=\u0002P\u0002\u0007\u0011\u000b\u0003\u0004g\u0003{\"\t\u0002\u0018\u0004\b\u00033\u0004!\u0001CAn\u0005%A\u0015m]'f[\n,'o\u0005\u0003\u0002X\u0006u\u0007c\u0001\u000f\u0017[\"I1!a6\u0003\u0002\u0003\u0006Ia\u0007\u0005\nE\u0005]'\u0011!Q\u0001\n\rB\u0011\"KAl\u0005\u0003\u0005\u000b\u0011\u0002\u0016\t\u00139\n9N!A!\u0002\u0013Q\u0003b\u0002\u0019\u0002X\u0012\u0005\u0011\u0011\u001e\u000b\u000b\u0003W\fi/a<\u0002r\u0006M\bc\u0001\u000f\u0002X\"11!a:A\u0002mAaAIAt\u0001\u0004\u0019\u0003BB\u0015\u0002h\u0002\u0007!\u0006\u0003\u0004/\u0003O\u0004\rA\u000b\u0005\t\u0003o\f9\u000e)Q\u0005[\u00069qL]3tk2$\bb\u00024\u0002X\u0012E\u00131`\u000b\u0002[\"9Q/a6\u0005\u0012\u0005}HcA7\u0003\u0002!1\u00010!@A\u0002ECqA_Al\t#\u0011)\u0001F\u0002\u0014\u0005\u000fAa\u0001\u001fB\u0002\u0001\u0004\t\u0006\u0003\u0002B\u0006\u0005\u001bi\u0011\u0001B\u0005\u0004\u0005\u001f!!aC*z[\n|G\u000eV1cY\u0016\u0004")
public interface FindMembers {

    public final class HasMember
    extends FindMemberBase<Object> {
        private boolean _result = false;

        @Override
        public boolean result() {
            return this._result;
        }

        @Override
        public boolean shortCircuit(Symbols.Symbol sym) {
            this._result = true;
            return true;
        }

        @Override
        public void addMemberIfNew(Symbols.Symbol sym) {
        }

        public HasMember(SymbolTable $outer, Types.Type tpe, Names.Name name, long excludedFlags, long requiredFlags) {
            super($outer, tpe, name, excludedFlags, requiredFlags);
        }
    }

    public final class FindMember
    extends FindMemberBase<Symbols.Symbol> {
        private final Types.Type tpe;
        private final Names.Name name;
        private final boolean stableOnly;
        private Symbols.Symbol member0;
        private List<Symbols.Symbol> members;
        private $colon$colon<Symbols.Symbol> lastM;
        private Types.Type _member0Tpe;

        private void clearAndAddResult(Symbols.Symbol sym) {
            this.member0 = sym;
            this.members = null;
            this.lastM = null;
        }

        @Override
        public boolean shortCircuit(Symbols.Symbol sym) {
            boolean bl;
            if (this.name.isTypeName() || this.stableOnly && sym.isStable() && !sym.hasVolatileType()) {
                this.clearAndAddResult(sym);
                bl = true;
            } else {
                bl = false;
            }
            return bl;
        }

        @Override
        public void addMemberIfNew(Symbols.Symbol sym) {
            if (this.member0 == this.scala$reflect$internal$tpe$FindMembers$FindMember$$$outer().NoSymbol()) {
                this.member0 = sym;
            } else if (this.members == null) {
                if (this.isNewMember(this.member0, sym)) {
                    this.lastM = new $colon$colon<Symbols.Symbol>(sym, null);
                    Symbols.Symbol symbol = this.member0;
                    this.members = this.lastM.$colon$colon(symbol);
                }
            } else {
                boolean isNew = true;
                for (List ms = this.members; ms != null && isNew; ms = (List)ms.tail()) {
                    Symbols.Symbol member = ms.head();
                    if (this.isNewMember(member, sym)) continue;
                    isNew = false;
                }
                if (isNew) {
                    $colon$colon<Symbols.Symbol> lastM1 = new $colon$colon<Symbols.Symbol>(sym, null);
                    this.lastM.tl_$eq(lastM1);
                    this.lastM = lastM1;
                }
            }
        }

        private Types.Type member0Tpe() {
            Predef$.MODULE$.assert(this.member0 != null);
            if (this._member0Tpe == null) {
                this._member0Tpe = this.self().memberType(this.member0);
            }
            return this._member0Tpe;
        }

        @Override
        public Types.Type memberTypeLow(Symbols.Symbol sym) {
            return sym == this.member0 ? this.member0Tpe() : super.memberTypeLow(sym);
        }

        @Override
        public Symbols.Symbol result() {
            Symbols.Symbol symbol;
            if (this.members == null) {
                Symbols.Symbol symbol2 = this.member0;
                Symbols.NoSymbol noSymbol = this.scala$reflect$internal$tpe$FindMembers$FindMember$$$outer().NoSymbol();
                if (!(symbol2 != null ? !symbol2.equals(noSymbol) : noSymbol != null)) {
                    if (Statistics$.MODULE$.canEnable()) {
                        Statistics.SubCounter subCounter = TypesStats$.MODULE$.noMemberCount();
                        if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && subCounter != null) {
                            subCounter.value_$eq(subCounter.value() + 1);
                        }
                    }
                    symbol = this.scala$reflect$internal$tpe$FindMembers$FindMember$$$outer().NoSymbol();
                } else {
                    symbol = this.member0;
                }
            } else {
                if (Statistics$.MODULE$.canEnable()) {
                    Statistics.SubCounter subCounter = TypesStats$.MODULE$.multMemberCount();
                    if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && subCounter != null) {
                        subCounter.value_$eq(subCounter.value() + 1);
                    }
                }
                this.lastM.tl_$eq(Nil$.MODULE$);
                symbol = this.initBaseClasses().head().newOverloaded(this.tpe, this.members);
            }
            return symbol;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$FindMembers$FindMember$$$outer() {
            return this.$outer;
        }

        public FindMember(SymbolTable $outer, Types.Type tpe, Names.Name name, long excludedFlags, long requiredFlags, boolean stableOnly) {
            this.tpe = tpe;
            this.name = name;
            this.stableOnly = stableOnly;
            super($outer, tpe, name, excludedFlags, requiredFlags);
            this.member0 = $outer.NoSymbol();
            this.members = null;
            this.lastM = null;
            this._member0Tpe = null;
        }
    }

    public final class FindMembers
    extends FindMemberBase<Scopes.Scope> {
        private Scopes.Scope _membersScope = null;

        private Scopes.Scope membersScope() {
            if (this._membersScope == null) {
                this._membersScope = this.scala$reflect$internal$tpe$FindMembers$FindMembers$$$outer().newFindMemberScope();
            }
            return this._membersScope;
        }

        @Override
        public boolean shortCircuit(Symbols.Symbol sym) {
            return false;
        }

        @Override
        public Scopes.Scope result() {
            return this.membersScope();
        }

        @Override
        public void addMemberIfNew(Symbols.Symbol sym) {
            Scopes.Scope members = this.membersScope();
            Scopes.ScopeEntry others = members.lookupEntry(sym.name());
            boolean isNew = true;
            while (others != null && isNew) {
                Symbols.Symbol member = others.sym();
                if (!this.isNewMember(member, sym)) {
                    isNew = false;
                }
                others = members.lookupNextEntry(others);
            }
            if (isNew) {
                members.enter(sym);
            }
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$FindMembers$FindMembers$$$outer() {
            return this.$outer;
        }

        public FindMembers(SymbolTable $outer, Types.Type tpe, long excludedFlags, long requiredFlags) {
            super($outer, tpe, $outer.nme().ANYname(), excludedFlags, requiredFlags);
        }
    }

    public abstract class FindMemberBase<T> {
        private final Types.Type tpe;
        private final Names.Name name;
        private final long excludedFlags;
        private final long requiredFlags;
        private final List<Symbols.Symbol> initBaseClasses;
        private Symbols.Symbol _selectorClass;
        private Types.Type _self;
        private Types.Type _memberTypeHiCache;
        private Symbols.Symbol _memberTypeHiCacheSym;
        public final /* synthetic */ SymbolTable $outer;

        public List<Symbols.Symbol> initBaseClasses() {
            return this.initBaseClasses;
        }

        private Symbols.Symbol selectorClass() {
            if (this._selectorClass == null) {
                Symbols.Symbol symbol;
                Types.Type type = this.tpe;
                if (type instanceof Types.ThisType) {
                    Types.ThisType thisType = (Types.ThisType)type;
                    symbol = thisType.sym();
                } else {
                    symbol = this.initBaseClasses().head();
                }
                this._selectorClass = symbol;
            }
            return this._selectorClass;
        }

        public Types.Type self() {
            if (this._self == null) {
                this._self = this.narrowForFindMember(this.tpe);
            }
            return this._self;
        }

        public T apply() {
            Tuple2<Object, Object> tuple2;
            if (Statistics$.MODULE$.canEnable()) {
                Statistics.Counter counter = TypesStats$.MODULE$.findMemberCount();
                if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                    counter.value_$eq(counter.value() + 1);
                }
            }
            if (Statistics$.MODULE$.canEnable()) {
                Statistics.TimerStack timerStack = TypesStats$.MODULE$.typeOpsStack();
                tuple2 = Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && timerStack != null ? timerStack.push(TypesStats$.MODULE$.findMemberNanos()) : null;
            } else {
                tuple2 = null;
            }
            Tuple2<Object, Object> start = tuple2;
            try {
                return this.searchConcreteThenDeferred();
            }
            finally {
                if (Statistics$.MODULE$.canEnable()) {
                    Statistics.TimerStack timerStack = TypesStats$.MODULE$.typeOpsStack();
                    if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && timerStack != null) {
                        timerStack.pop(start);
                    }
                }
            }
        }

        public abstract T result();

        private T searchConcreteThenDeferred() {
            boolean deferredSeen = this.walkBaseClasses(this.requiredFlags, this.excludedFlags | 0x10L);
            Serializable serializable = deferredSeen ? BoxesRunTime.boxToBoolean(this.walkBaseClasses(this.requiredFlags | 0x10L, this.excludedFlags & ((long)16 ^ 0xFFFFFFFFFFFFFFFFL))) : BoxedUnit.UNIT;
            return this.result();
        }

        /*
         * WARNING - void declaration
         */
        private boolean walkBaseClasses(long required, long excluded) {
            boolean findAll;
            List<Symbols.Symbol> bcs = this.initBaseClasses();
            boolean deferredSeen = false;
            Nil$ refinementParents = Nil$.MODULE$;
            boolean seenFirstNonRefinementClass = false;
            Names.Name name = this.name;
            Names.TermName termName = this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().nme().ANYname();
            boolean bl = !(name != null ? !name.equals(termName) : termName != null) ? true : (findAll = false);
            while (!bcs.isEmpty()) {
                List<Symbols.Symbol> list2;
                boolean bl2;
                void var9_8;
                Scopes.ScopeEntry entry;
                List list3;
                Symbols.Symbol currentBaseClass = (Symbols.Symbol)list3.head();
                Scopes.Scope decls = currentBaseClass.info().decls();
                Scopes.ScopeEntry scopeEntry = entry = var9_8 != false ? decls.elems() : decls.lookupEntry(this.name);
                while (entry != null) {
                    boolean meetsRequirements;
                    Symbols.Symbol sym = entry.sym();
                    long flags = sym.flags();
                    boolean bl3 = meetsRequirements = (flags & required) == required;
                    if (meetsRequirements) {
                        boolean isExcluded;
                        long excl = flags & excluded;
                        boolean bl4 = isExcluded = excl != 0L;
                        if (!isExcluded && this.isPotentialMember(sym, flags, currentBaseClass, bl2, list2)) {
                            if (this.shortCircuit(sym)) {
                                return false;
                            }
                            this.addMemberIfNew(sym);
                        } else if (excl == 16L) {
                            boolean bl5 = true;
                        }
                    }
                    entry = var9_8 != false ? entry.next() : decls.lookupNextEntry(entry);
                }
                if (currentBaseClass.isRefinementClass()) {
                    list2 = list2.$colon$colon$colon(currentBaseClass.parentSymbols());
                } else if (currentBaseClass.isClass()) {
                    bl2 = true;
                }
                list3 = (List)list3.tail();
            }
            return deferredSeen;
        }

        public abstract boolean shortCircuit(Symbols.Symbol var1);

        public abstract void addMemberIfNew(Symbols.Symbol var1);

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private boolean isPotentialMember(Symbols.Symbol sym, long flags, Symbols.Symbol owner2, boolean seenFirstNonRefinementClass, List<Symbols.Symbol> refinementParents) {
            boolean isPrivateLocal;
            boolean isPrivate = (flags & 4L) == 4L;
            boolean bl = isPrivateLocal = (flags & 0x80004L) == 524292L;
            if (isPrivate) {
                if (!this.admitPrivate$1(sym, owner2, seenFirstNonRefinementClass, refinementParents, isPrivateLocal)) return false;
            }
            Names.Name name = sym.name();
            Names.TermName termName = this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().nme().CONSTRUCTOR();
            if (name == null) {
                if (termName != null) {
                    return true;
                }
            } else if (!name.equals(termName)) return true;
            Symbols.Symbol symbol = owner2;
            Symbols.Symbol symbol2 = this.initBaseClasses().head();
            if (symbol == null) {
                if (symbol2 == null) return true;
                return false;
            } else if (symbol.equals(symbol2)) return true;
            return false;
        }

        public boolean isNewMember(Symbols.Symbol member, Symbols.Symbol other) {
            return other != member && (member.owner() == other.owner() || (member.flags() & 4L) != 0L || (other.flags() & 4L) != 0L || !this.memberTypeLow(member).matches(this.memberTypeHi(other)));
        }

        public Types.Type memberTypeHi(Symbols.Symbol sym) {
            if (this._memberTypeHiCacheSym != sym) {
                this._memberTypeHiCache = this.self().memberType(sym);
                this._memberTypeHiCacheSym = sym;
            }
            return this._memberTypeHiCache;
        }

        public Types.Type memberTypeLow(Symbols.Symbol sym) {
            return this.self().memberType(sym);
        }

        private Types.Type narrowForFindMember(Types.Type tp) {
            Types.Type w = tp.widen();
            return tp != w && this.scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer().containsExistential(w) ? w.narrow() : tp.narrow();
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$FindMembers$FindMemberBase$$$outer() {
            return this.$outer;
        }

        private final boolean admitPrivate$1(Symbols.Symbol sym, Symbols.Symbol owner$1, boolean seenFirstNonRefinementClass$1, List refinementParents$1, boolean isPrivateLocal$1) {
            Symbols.Symbol symbol = this.selectorClass();
            return !(symbol == null ? owner$1 != null : !symbol.equals(owner$1)) || !isPrivateLocal$1 && (!seenFirstNonRefinementClass$1 || refinementParents$1.contains(owner$1));
        }

        public FindMemberBase(SymbolTable $outer, Types.Type tpe, Names.Name name, long excludedFlags, long requiredFlags) {
            this.tpe = tpe;
            this.name = name;
            this.excludedFlags = excludedFlags;
            this.requiredFlags = requiredFlags;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this.initBaseClasses = tpe.baseClasses();
            this._selectorClass = null;
            this._self = null;
            this._memberTypeHiCache = null;
            this._memberTypeHiCacheSym = null;
        }
    }
}

