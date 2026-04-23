/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Flags$;
import scala.reflect.internal.Names;
import scala.reflect.internal.Required;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.runtime.JavaMirrors;
import scala.reflect.runtime.ReflectionUtils$;
import scala.reflect.runtime.SymbolLoaders$PackageScope$;
import scala.reflect.runtime.SymbolTable;
import scala.reflect.runtime.SynchronizedOps;
import scala.reflect.runtime.SynchronizedOps$SynchronizedScope$class;

@ScalaSignature(bytes="\u0006\u0001\u0005ufAC\u0001\u0003!\u0003\r\t\u0001\u0002\u0005\u00028\ni1+_7c_2du.\u00193feNT!a\u0001\u0003\u0002\u000fI,h\u000e^5nK*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\f7C\u0001\u0001\n!\tQ1\"D\u0001\u0007\u0013\taaA\u0001\u0004B]f\u0014VM\u001a\u0005\u0006\u001d\u0001!\t\u0001E\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t\u0011\u0003\u0005\u0002\u000b%%\u00111C\u0002\u0002\u0005+:LGO\u0002\u0003\u0016\u0001\u00011\"!\u0005+pa\u000ec\u0017m]:D_6\u0004H.\u001a;feN\u0019AcF\u0010\u0011\u0005aIR\"\u0001\u0001\n\u0005iY\"!C*z[2{\u0017\rZ3s\u0013\taRDA\u0006Ts6\u0014w\u000e\u001c+bE2,'B\u0001\u0010\u0005\u0003!Ig\u000e^3s]\u0006d\u0007C\u0001\r!\u0013\t\t#E\u0001\fGY\u0006<\u0017i]:jO:LgnZ\"p[BdW\r^3s\u0013\t\u0019SDA\u0003UsB,7\u000f\u0003\u0005&)\t\u0005\t\u0015!\u0003'\u0003\u0015\u0019G.\u0019>{!\tAr%\u0003\u0002)S\t11+_7c_2L!AK\u000f\u0003\u000fMKXNY8mg\"AA\u0006\u0006B\u0001B\u0003%a%\u0001\u0004n_\u0012,H.\u001a\u0005\u0006]Q!\taL\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007A\n$\u0007\u0005\u0002\u0019)!)Q%\fa\u0001M!)A&\fa\u0001M!)A\u0007\u0006C!k\u0005A1m\\7qY\u0016$X\r\u0006\u0002\u0012m!)qg\ra\u0001M\u0005\u00191/_7\t\u000be\"B\u0011\t\u001e\u0002\t1|\u0017\r\u001a\u000b\u0003#mBQa\u000e\u001dA\u0002\u0019BQ!\u0010\u0001\u0005\u0012y\n!$\u001b8ji\u0006sG-\u00128uKJ\u001cE.Y:t\u0003:$Wj\u001c3vY\u0016$Ba\u0010%K#B!!\u0002\u0011\"F\u0013\t\teA\u0001\u0004UkBdWM\r\t\u00031\rK!\u0001R\u0015\u0003\u0017\rc\u0017m]:Ts6\u0014w\u000e\u001c\t\u00031\u0019K!aR\u0015\u0003\u00195{G-\u001e7f'fl'm\u001c7\t\u000b%c\u0004\u0019\u0001\u0014\u0002\u000b=<h.\u001a:\t\u000b-c\u0004\u0019\u0001'\u0002\t9\fW.\u001a\t\u000315K!AT(\u0003\u0011QK\b/\u001a(b[\u0016L!\u0001U\u000f\u0003\u000b9\u000bW.Z:\t\u000bIc\u0004\u0019A*\u0002\u0013\r|W\u000e\u001d7fi\u0016\u0014\b#\u0002\u0006UM\u00192\u0016BA+\u0007\u0005%1UO\\2uS>t'\u0007\u0005\u0002\u0019/&\u0011\u0001L\t\u0002\t\u0019\u0006T\u0018\u0010V=qK\")!\f\u0001C\t7\u0006Y1/\u001a;BY2LeNZ8t)\u0011\tB,\u00180\t\u000b\u0015J\u0006\u0019\u0001\u0014\t\u000b1J\u0006\u0019\u0001\u0014\t\u000b}K\u0006\u0019\u00011\u0002\t%tgm\u001c\t\u00031\u0005L!A\u0019\u0012\u0003\tQK\b/\u001a\u0005\u0006I\u0002!\t\"Z\u0001\u0013S:LGo\u00117bgN\fe\u000eZ'pIVdW\r\u0006\u0003\u0012M\u001eD\u0007\"B\u0013d\u0001\u00041\u0003\"\u0002\u0017d\u0001\u00041\u0003\"\u0002*d\u0001\u00041f\u0001\u00026\u0001\u0001-\u0014q\u0002T1{sB\u000b7m[1hKRK\b/Z\n\u0004SZc\u0007C\u0001\rn\u0013\tq'EA\u000bGY\u0006<\u0017i\u001a8pgRL7mQ8na2,G/\u001a:\t\u000b9JG\u0011\u00019\u0015\u0003E\u0004\"\u0001G5\t\u000bQJG\u0011I:\u0015\u0005E!\b\"B\u001cs\u0001\u00041c\u0001\u0002<\u0001\u0001]\u0014A\u0002U1dW\u0006<WmU2pa\u0016\u001c2!\u001e=~!\tA\u00120\u0003\u0002{w\n)1kY8qK&\u0011A0\b\u0002\u0007'\u000e|\u0007/Z:\u0011\u0005aq\u0018bA@\u0002\u0002\t\t2+\u001f8dQJ|g.\u001b>fIN\u001bw\u000e]3\n\u0007\u0005\r!AA\bTs:\u001c\u0007N]8oSj,Gm\u00149t\u0011%\t9!\u001eB\u0001B\u0003%a%\u0001\u0005qW\u001e\u001cE.Y:t\u0011\u0019qS\u000f\"\u0001\u0002\fQ!\u0011QBA\b!\tAR\u000fC\u0004\u0002\b\u0005%\u0001\u0019\u0001\u0014\t\u000f\u0005MQ\u000f\"\u0011\u0002\u0016\u0005)QM\u001c;feV!\u0011qCA\u000f)\u0011\tI\"!\u000b\u0011\t\u0005m\u0011Q\u0004\u0007\u0001\t!\ty\"!\u0005C\u0002\u0005\u0005\"!\u0001+\u0012\u0007\u0005\rb\u0005E\u0002\u000b\u0003KI1!a\n\u0007\u0005\u001dqu\u000e\u001e5j]\u001eDqaNA\t\u0001\u0004\tI\u0002C\u0004\u0002.U$\t%a\f\u0002\u0015\u0015tG/\u001a:JM:+w/\u0006\u0003\u00022\u0005UB\u0003BA\u001a\u0003o\u0001B!a\u0007\u00026\u0011A\u0011qDA\u0016\u0005\u0004\t\t\u0003C\u00048\u0003W\u0001\r!a\r\t\u000f\u0005mR\u000f\"\u0011\u0002>\u0005!2/\u001f8d\u0019>\u001c7nU=oG\"\u0014xN\\5{K\u0012,B!a\u0010\u0002DQ!\u0011\u0011IA'!\u0011\tY\"a\u0011\u0005\u0011\u0005}\u0011\u0011\bb\u0001\u0003\u000b\nB!a\t\u0002HA\u0019!\"!\u0013\n\u0007\u0005-cAA\u0002B]fD\u0011\"a\u0014\u0002:\u0011\u0005\r!!\u0015\u0002\t\t|G-\u001f\t\u0006\u0015\u0005M\u0013\u0011I\u0005\u0004\u0003+2!\u0001\u0003\u001fcs:\fW.\u001a \t\u0013\u0005eSO1A\u0005\n\u0005m\u0013!\u00038fO\u0006$\u0018N^3t+\t\ti\u0006\u0005\u0004\u0002`\u0005%\u0014QN\u0007\u0003\u0003CRA!a\u0019\u0002f\u00059Q.\u001e;bE2,'bAA4\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005-\u0014\u0011\r\u0002\b\u0011\u0006\u001c\bnU3u!\rA\u0012qN\u0005\u0004\u0003cz%\u0001\u0002(b[\u0016D\u0001\"!\u001evA\u0003%\u0011QL\u0001\u000b]\u0016<\u0017\r^5wKN\u0004\u0003bBA=k\u0012\u0005\u00131P\u0001\fY>|7.\u001e9F]R\u0014\u0018\u0010\u0006\u0003\u0002~\u0005\r\u0005c\u0001\r\u0002\u0000%\u0019\u0011\u0011Q>\u0003\u0015M\u001bw\u000e]3F]R\u0014\u0018\u0010C\u0004L\u0003o\u0002\r!!\u001c\t\u001d\u0005\u001dU\u000f%A\u0002\u0002\u0003%I!!#\u0002\u000e\u0006\t2/\u001e9fe\u0012bwn\\6va\u0016sGO]=\u0015\t\u0005u\u00141\u0012\u0005\b\u0017\u0006\u0015\u0005\u0019AA7\u0013\r\tIH \u0005\b\u0003#\u0003A\u0011IAJ\u0003E1\u0018\r\\5eCR,7\t\\1tg&sgm\u001c\u000b\u0004#\u0005U\u0005\u0002CAL\u0003\u001f\u0003\r!!'\u0002\u0005Q\u0004\bc\u0001\r\u0002\u001c&\u0019\u0011Q\u0014\u0012\u0003\u001b\rc\u0017m]:J]\u001a|G+\u001f9f\u0011\u001d\t\t\u000b\u0001C!\u0003G\u000bqB\\3x!\u0006\u001c7.Y4f'\u000e|\u0007/\u001a\u000b\u0005\u0003\u001b\t)\u000bC\u0004\u0002\b\u0005}\u0005\u0019\u0001\u0014\t\u000f\u0005%\u0006\u0001\"\u0011\u0002,\u0006q1oY8qKR\u0013\u0018M\\:g_JlG\u0003BAW\u0003k#2\u0001_AX\u0011%\t\t,a*\u0005\u0002\u0004\t\u0019,\u0001\u0002paB!!\"a\u0015y\u0011\u0019I\u0015q\u0015a\u0001MA!\u0011\u0011XA^\u001b\u0005\u0011\u0011B\u0001\u000f\u0003\u0001")
public interface SymbolLoaders {
    public Tuple2<Symbols.ClassSymbol, Symbols.ModuleSymbol> initAndEnterClassAndModule(Symbols.Symbol var1, Names.TypeName var2, Function2<Symbols.Symbol, Symbols.Symbol, Types.LazyType> var3);

    public void setAllInfos(Symbols.Symbol var1, Symbols.Symbol var2, Types.Type var3);

    public void initClassAndModule(Symbols.Symbol var1, Symbols.Symbol var2, Types.LazyType var3);

    public void validateClassInfo(Types.ClassInfoType var1);

    public PackageScope newPackageScope(Symbols.Symbol var1);

    public Scopes.Scope scopeTransform(Symbols.Symbol var1, Function0<Scopes.Scope> var2);

    public class PackageScope
    extends Scopes.Scope
    implements SynchronizedOps.SynchronizedScope {
        public final Symbols.Symbol scala$reflect$runtime$SymbolLoaders$PackageScope$$pkgClass;
        private final HashSet<Names.Name> scala$reflect$runtime$SymbolLoaders$PackageScope$$negatives;
        public final /* synthetic */ SymbolTable $outer;
        private final Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock;
        private volatile boolean bitmap$0;

        private Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock$lzycompute() {
            PackageScope packageScope = this;
            synchronized (packageScope) {
                if (!this.bitmap$0) {
                    this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock = SynchronizedOps$SynchronizedScope$class.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock(this);
                    this.bitmap$0 = true;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock;
            }
        }

        @Override
        public Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock() {
            return this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock : this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock$lzycompute();
        }

        @Override
        public /* synthetic */ boolean scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$isEmpty() {
            return super.isEmpty();
        }

        @Override
        public /* synthetic */ int scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$size() {
            return super.size();
        }

        @Override
        public /* synthetic */ Symbols.Symbol scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$enter(Symbols.Symbol sym) {
            return super.enter(sym);
        }

        @Override
        public /* synthetic */ void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$rehash(Symbols.Symbol sym, Names.Name newname) {
            super.rehash(sym, newname);
        }

        @Override
        public /* synthetic */ void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(Scopes.ScopeEntry e) {
            super.unlink(e);
        }

        @Override
        public /* synthetic */ void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(Symbols.Symbol sym) {
            super.unlink(sym);
        }

        @Override
        public /* synthetic */ Iterator scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupAll(Names.Name name) {
            return super.lookupAll(name);
        }

        @Override
        public /* synthetic */ Scopes.ScopeEntry scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupEntry(Names.Name name) {
            return super.lookupEntry(name);
        }

        @Override
        public /* synthetic */ Scopes.ScopeEntry scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupNextEntry(Scopes.ScopeEntry entry) {
            return super.lookupNextEntry(entry);
        }

        @Override
        public /* synthetic */ List scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$toList() {
            return super.toList();
        }

        @Override
        public boolean isEmpty() {
            return SynchronizedOps$SynchronizedScope$class.isEmpty(this);
        }

        @Override
        public int size() {
            return SynchronizedOps$SynchronizedScope$class.size(this);
        }

        @Override
        public void rehash(Symbols.Symbol sym, Names.Name newname) {
            SynchronizedOps$SynchronizedScope$class.rehash(this, sym, newname);
        }

        @Override
        public void unlink(Scopes.ScopeEntry e) {
            SynchronizedOps$SynchronizedScope$class.unlink((SynchronizedOps.SynchronizedScope)this, e);
        }

        @Override
        public void unlink(Symbols.Symbol sym) {
            SynchronizedOps$SynchronizedScope$class.unlink((SynchronizedOps.SynchronizedScope)this, sym);
        }

        @Override
        public Iterator<Symbols.Symbol> lookupAll(Names.Name name) {
            return SynchronizedOps$SynchronizedScope$class.lookupAll(this, name);
        }

        @Override
        public Scopes.ScopeEntry lookupNextEntry(Scopes.ScopeEntry entry) {
            return SynchronizedOps$SynchronizedScope$class.lookupNextEntry(this, entry);
        }

        @Override
        public List<Symbols.Symbol> toList() {
            return SynchronizedOps$SynchronizedScope$class.toList(this);
        }

        public /* synthetic */ Scopes.ScopeEntry scala$reflect$runtime$SymbolLoaders$PackageScope$$super$lookupEntry(Names.Name name) {
            return SynchronizedOps$SynchronizedScope$class.lookupEntry(this, name);
        }

        @Override
        public <T extends Symbols.Symbol> T enter(T sym) {
            Symbols.Symbol symbol;
            if (this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().isCompilerUniverse()) {
                symbol = SynchronizedOps$SynchronizedScope$class.enter(this, sym);
            } else {
                Scopes.ScopeEntry existing = SynchronizedOps$SynchronizedScope$class.lookupEntry(this, sym.name());
                boolean bl = existing == null || this.eitherIsMethod$1(existing.sym(), sym);
                Predef$ predef$ = Predef$.MODULE$;
                if (!bl) {
                    throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"pkgClass = ", ", sym = ", ", existing = ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.scala$reflect$runtime$SymbolLoaders$PackageScope$$pkgClass, sym, existing}))).toString());
                }
                symbol = SynchronizedOps$SynchronizedScope$class.enter(this, sym);
            }
            return (T)symbol;
        }

        @Override
        public <T extends Symbols.Symbol> T enterIfNew(T sym) {
            Scopes.ScopeEntry existing = SynchronizedOps$SynchronizedScope$class.lookupEntry(this, sym.name());
            return (T)(existing == null ? this.enter(sym) : existing.sym());
        }

        @Override
        public <T> T syncLockSynchronized(Function0<T> body2) {
            return this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().gilSynchronized(body2);
        }

        public HashSet<Names.Name> scala$reflect$runtime$SymbolLoaders$PackageScope$$negatives() {
            return this.scala$reflect$runtime$SymbolLoaders$PackageScope$$negatives;
        }

        @Override
        public Scopes.ScopeEntry lookupEntry(Names.Name name) {
            return this.syncLockSynchronized((Function0)((Object)new Serializable(this, name){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ PackageScope $outer;
                private final Names.Name name$1;

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                public final Scopes.ScopeEntry apply() {
                    Scopes.ScopeEntry scopeEntry;
                    Scopes.ScopeEntry e = this.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$super$lookupEntry(this.name$1);
                    if (e == null) {
                        Tuple2<Symbols.Symbol, Symbols.Symbol> tuple2;
                        if (ReflectionUtils$.MODULE$.scalacShouldntLoadClass(this.name$1)) return null;
                        if (this.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$negatives().contains(this.name$1)) {
                            return null;
                        }
                        String path = this.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$pkgClass.isEmptyPackageClass() ? this.name$1.toString() : new StringBuilder().append((Object)this.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$pkgClass.fullName()).append((Object)".").append(this.name$1).toString();
                        JavaMirrors.JavaMirror currentMirror = this.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().mirrorThatLoaded(this.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$pkgClass);
                        Option<Class<?>> option = currentMirror.tryJavaClass(path);
                        if (option instanceof Some) {
                            Some some = (Some)option;
                            JavaMirrors.JavaMirror loadingMirror = currentMirror.mirrorDefining((Class)some.x());
                            if (loadingMirror == currentMirror) {
                                tuple2 = this.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().initAndEnterClassAndModule(this.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$pkgClass, this.name$1.toTypeName(), (Function2<Symbols.Symbol, Symbols.Symbol, Types.LazyType>)((Object)new Serializable(this){
                                    public static final long serialVersionUID = 0L;
                                    private final /* synthetic */ PackageScope$$anonfun$lookupEntry$1 $outer;

                                    public final TopClassCompleter apply(Symbols.Symbol x$2, Symbols.Symbol x$3) {
                                        return new TopClassCompleter(this.$outer.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer(), x$2, x$3);
                                    }
                                    {
                                        if ($outer == null) {
                                            throw null;
                                        }
                                        this.$outer = $outer;
                                    }
                                }));
                            } else {
                                Symbols.ModuleSymbol origOwner = loadingMirror.packageNameToScala(this.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$pkgClass.fullName());
                                Symbols.Symbol clazz = origOwner.info().decl(this.name$1.toTypeName());
                                Symbols.Symbol module = origOwner.info().decl(this.name$1.toTermName());
                                Symbols.Symbol symbol = clazz;
                                Symbols.NoSymbol noSymbol = this.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().NoSymbol();
                                Predef$.MODULE$.assert(symbol != null ? !symbol.equals(noSymbol) : noSymbol != null);
                                Symbols.Symbol symbol2 = module;
                                Symbols.NoSymbol noSymbol2 = this.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().NoSymbol();
                                Predef$.MODULE$.assert(symbol2 != null ? !symbol2.equals(noSymbol2) : noSymbol2 != null);
                                this.$outer.enterIfNew(clazz);
                                this.$outer.enterIfNew(module);
                                tuple2 = new Tuple2<Symbols.Symbol, Symbols.Symbol>(clazz, module);
                            }
                        } else {
                            this.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().debugInfo((Function0<String>)((Object)new Serializable(this, path){
                                public static final long serialVersionUID = 0L;
                                private final String path$1;

                                public final String apply() {
                                    return new StringBuilder().append((Object)"*** not found : ").append((Object)this.path$1).toString();
                                }
                                {
                                    this.path$1 = path$1;
                                }
                            }));
                            this.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$negatives().$plus$eq((Object)this.name$1);
                            return null;
                        }
                        Tuple2<Symbols.Symbol, Symbols.Symbol> tuple22 = tuple2;
                        if (tuple22 == null) throw new MatchError(tuple22);
                        Symbols.Symbol symbol = tuple22._2();
                        this.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().debugInfo((Function0<String>)((Object)new Serializable(this, symbol){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ PackageScope$$anonfun$lookupEntry$1 $outer;
                            private final Symbols.Symbol module$1;

                            public final String apply() {
                                return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"created ", "/", " in ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.module$1, this.module$1.moduleClass(), this.$outer.$outer.scala$reflect$runtime$SymbolLoaders$PackageScope$$pkgClass}));
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                                this.module$1 = module$1;
                            }
                        }));
                        Scopes.ScopeEntry scopeEntry2 = this.$outer.lookupEntry(this.name$1);
                        scopeEntry = scopeEntry2;
                        return scopeEntry;
                    } else {
                        scopeEntry = e;
                    }
                    return scopeEntry;
                }

                public /* synthetic */ PackageScope scala$reflect$runtime$SymbolLoaders$PackageScope$$anonfun$$$outer() {
                    return this.$outer;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.name$1 = name$1;
                }
            }));
        }

        public /* synthetic */ SymbolTable scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ SynchronizedOps scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$$outer() {
            return this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer();
        }

        private final boolean eitherIsMethod$1(Symbols.Symbol sym1, Symbols.Symbol sym2) {
            return sym1.isMethod() || sym2.isMethod();
        }

        public PackageScope(SymbolTable $outer, Symbols.Symbol pkgClass) {
            this.scala$reflect$runtime$SymbolLoaders$PackageScope$$pkgClass = pkgClass;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            super((scala.reflect.internal.SymbolTable)((Object)$outer));
            SynchronizedOps$SynchronizedScope$class.$init$(this);
            Predef$.MODULE$.assert(pkgClass.isType());
            this.scala$reflect$runtime$SymbolLoaders$PackageScope$$negatives = new HashSet();
        }
    }

    public static class LazyPackageType
    extends Types.LazyType
    implements Types.FlagAgnosticCompleter {
        public final /* synthetic */ SymbolTable $outer;

        @Override
        public void complete(Symbols.Symbol sym) {
            Predef$.MODULE$.assert(sym.isPackageClass());
            ((scala.reflect.internal.SymbolTable)((Object)this.scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer())).slowButSafeEnteringPhaseNotLaterThan(((Required)((Object)this.scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer())).picklerPhase(), new Serializable(this, sym){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ LazyPackageType $outer;
                private final Symbols.Symbol sym$2;

                public final void apply() {
                    this.apply$mcV$sp();
                }

                public void apply$mcV$sp() {
                    this.sym$2.setInfo(new Types.ClassInfoType((scala.reflect.internal.SymbolTable)((Object)this.$outer.scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer()), Nil$.MODULE$, new PackageScope(this.$outer.scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer(), this.sym$2), this.sym$2));
                    ((scala.reflect.internal.SymbolTable)((Object)this.$outer.scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer())).openPackageModule(this.sym$2);
                    this.$outer.scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer().markAllCompleted(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{this.sym$2}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.sym$2 = sym$2;
                }
            });
        }

        public /* synthetic */ SymbolTable scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer() {
            return this.$outer;
        }

        public LazyPackageType(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            super((scala.reflect.internal.SymbolTable)((Object)$outer));
        }
    }

    public class TopClassCompleter
    extends SymbolTable.SymLoader
    implements Types.FlagAssigningCompleter {
        public final Symbols.Symbol scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$clazz;
        public final Symbols.Symbol scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$module;
        public final /* synthetic */ SymbolTable $outer;

        /*
         * Unable to fully structure code
         */
        @Override
        public void complete(Symbols.Symbol sym) {
            this.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$$outer().debugInfo((Function0<String>)new Serializable(this, sym){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TopClassCompleter $outer;
                private final Symbols.Symbol sym$1;

                public final String apply() {
                    return new StringBuilder().append((Object)"completing ").append(this.sym$1).append((Object)"/").append((Object)this.$outer.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$clazz.fullName()).toString();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.sym$1 = sym$1;
                }
            });
            v0 = sym;
            var2_2 = this.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$clazz;
            if (!(v0 == null ? var2_2 != null : v0.equals(var2_2) == false)) ** GOTO lbl-1000
            v1 = sym;
            var3_3 = this.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$module;
            if (!(v1 == null ? var3_3 != null : v1.equals(var3_3) == false)) ** GOTO lbl-1000
            v2 = sym;
            var4_4 = this.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$module.moduleClass();
            if (!(v2 != null ? v2.equals(var4_4) == false : var4_4 != null)) lbl-1000:
            // 3 sources

            {
                v3 = true;
            } else {
                v3 = false;
            }
            Predef$.MODULE$.assert(v3);
            ((scala.reflect.internal.SymbolTable)this.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$$outer()).slowButSafeEnteringPhaseNotLaterThan(((Required)this.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$$outer()).picklerPhase(), new Serializable(this, sym){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TopClassCompleter $outer;
                private final Symbols.Symbol sym$1;

                public final void apply() {
                    this.apply$mcV$sp();
                }

                public void apply$mcV$sp() {
                    JavaMirrors.JavaMirror loadingMirror = this.$outer.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$$outer().mirrorThatLoaded(this.sym$1);
                    Class<?> javaClass = loadingMirror.javaClass(this.$outer.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$clazz.javaClassName());
                    loadingMirror.unpickleClass(this.$outer.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$clazz, this.$outer.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$module, javaClass);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.sym$1 = sym$1;
                }
            });
        }

        @Override
        public void load(Symbols.Symbol sym) {
            this.complete(sym);
        }

        public /* synthetic */ SymbolTable scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$$outer() {
            return this.$outer;
        }

        public TopClassCompleter(SymbolTable $outer, Symbols.Symbol clazz, Symbols.Symbol module) {
            this.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$clazz = clazz;
            this.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$module = module;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            super((scala.reflect.internal.SymbolTable)((Object)$outer));
            $outer.markFlagsCompleted(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{clazz, module}), Flags$.MODULE$.TopLevelPickledFlags() ^ 0xFFFFFFFFFFFFFFFFL);
        }
    }
}

