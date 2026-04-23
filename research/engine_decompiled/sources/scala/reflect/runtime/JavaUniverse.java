/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import scala.Console$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WeakHashMap;
import scala.ref.WeakReference;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Internals;
import scala.reflect.api.Internals$InternalApi$class;
import scala.reflect.api.JavaUniverse$class;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.BaseTypeSeqs;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Depth;
import scala.reflect.internal.Internals;
import scala.reflect.internal.Internals$SymbolTableInternal$class;
import scala.reflect.internal.Names;
import scala.reflect.internal.Phase;
import scala.reflect.internal.Reporter;
import scala.reflect.internal.ReporterImpl;
import scala.reflect.internal.Reporting;
import scala.reflect.internal.Reporting$RunReporting$class;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SomePhase$;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Symbols$class;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$class;
import scala.reflect.internal.tpe.CommonOwners;
import scala.reflect.internal.tpe.TypeComparers;
import scala.reflect.internal.tpe.TypeConstraints;
import scala.reflect.internal.util.FreshNameCreator;
import scala.reflect.internal.util.Position;
import scala.reflect.macros.Attachments;
import scala.reflect.macros.Universe;
import scala.reflect.macros.Universe$MacroInternalApi$class;
import scala.reflect.package$;
import scala.reflect.runtime.Gil$class;
import scala.reflect.runtime.JavaMirrors;
import scala.reflect.runtime.JavaMirrors$class;
import scala.reflect.runtime.JavaUniverse$;
import scala.reflect.runtime.JavaUniverse$treeInfo$;
import scala.reflect.runtime.JavaUniverseForce;
import scala.reflect.runtime.JavaUniverseForce$class;
import scala.reflect.runtime.ReflectSetup;
import scala.reflect.runtime.ReflectSetup$class;
import scala.reflect.runtime.Settings;
import scala.reflect.runtime.SymbolLoaders;
import scala.reflect.runtime.SymbolLoaders$class;
import scala.reflect.runtime.SymbolTable;
import scala.reflect.runtime.SymbolTable$class;
import scala.reflect.runtime.SynchronizedOps;
import scala.reflect.runtime.SynchronizedOps$class;
import scala.reflect.runtime.SynchronizedSymbols$class;
import scala.reflect.runtime.SynchronizedTypes$class;
import scala.reflect.runtime.ThreadLocalStorage;
import scala.reflect.runtime.ThreadLocalStorage$class;
import scala.reflect.runtime.TwoWayCaches$class;

@ScalaSignature(bytes="\u0006\u0001\u0005}c\u0001B\u0001\u0003\u0001%\u0011ABS1wCVs\u0017N^3sg\u0016T!a\u0001\u0003\u0002\u000fI,h\u000e^5nK*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\f7\u0001A\n\u0006\u0001)\u0001Bc\u0006\t\u0003\u00179i\u0011\u0001\u0004\u0006\u0003\u001b\u0011\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003\u001f1\u00111bU=nE>dG+\u00192mKB\u0011\u0011CE\u0007\u0002\u0005%\u00111C\u0001\u0002\u0012\u0015\u00064\u0018-\u00168jm\u0016\u00148/\u001a$pe\u000e,\u0007CA\t\u0016\u0013\t1\"A\u0001\u0007SK\u001adWm\u0019;TKR,\b\u000f\u0005\u0002\u00121%\u0011qB\u0001\u0005\u00065\u0001!\taG\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003q\u0001\"!\u0005\u0001\t\u000by\u0001A\u0011A\u0010\u0002\u0019AL7m\u001b7feBC\u0017m]3\u0016\u0003\u0001r!aC\u0011\n\u0005\tb\u0011!C*p[\u0016\u0004\u0006.Y:f\u0011\u0015!\u0003\u0001\"\u0001 \u00031)'/Y:ve\u0016\u0004\u0006.Y:f\u0011!1\u0003\u0001#b\u0001\n\u00039\u0013\u0001C:fiRLgnZ:\u0016\u0003!\u0002\"!E\u0015\n\u0005)\u0012!\u0001C*fiRLgnZ:\t\u00111\u0002\u0001\u0012!Q!\n!\n\u0011b]3ui&twm\u001d\u0011\t\u000f9\u0002!\u0019!C\u0005_\u0005I\u0011n\u001d'pO\u001eLgnZ\u000b\u0002aA\u0011\u0011GM\u0007\u0002\r%\u00111G\u0002\u0002\b\u0005>|G.Z1o\u0011\u0019)\u0004\u0001)A\u0005a\u0005Q\u0011n\u001d'pO\u001eLgn\u001a\u0011\t\u000b]\u0002A\u0011\u0001\u001d\u0002\u00071|w\r\u0006\u0002:yA\u0011\u0011GO\u0005\u0003w\u0019\u0011A!\u00168ji\"1QH\u000eCA\u0002y\n1!\\:h!\r\tt(Q\u0005\u0003\u0001\u001a\u0011\u0001\u0002\u00102z]\u0006lWM\u0010\t\u0003c\tK!a\u0011\u0004\u0003\r\u0005s\u0017PU3g\u0011\u0015)\u0005\u0001\"\u0011G\u0003!\u0011X\r]8si\u0016\u0014X#A$\u0011\u0005-A\u0015BA%\r\u0005!\u0011V\r]8si\u0016\u0014\b\"B&\u0001\t\u0003a\u0015AC2veJ,g\u000e\u001e*v]V\tQJE\u0002O\u0003B3Aa\u0014&\u0001\u001b\naAH]3gS:,W.\u001a8u}A\u0011\u0011KU\u0007\u0002\u0001%\u00111\u000b\u0016\u0002\r%Vt'+\u001a9peRLgnZ\u0005\u0003+2\u0011\u0011BU3q_J$\u0018N\\4\u0007\t]\u0003\u0001\u0001\u0017\u0002\u0010!\u0016\u0014(+\u001e8SKB|'\u000f^5oON\u0011a+\u0017\t\u0003#jK!a\u0017+\u0003'A+'OU;o%\u0016\u0004xN\u001d;j]\u001e\u0014\u0015m]3\t\u000bi1F\u0011A/\u0015\u0003y\u0003\"!\u0015,\t\u000b\u00014F\u0011A1\u0002%\u0011,\u0007O]3dCRLwN\\,be:Lgn\u001a\u000b\u0004s\tL\u0007\"B2`\u0001\u0004!\u0017a\u00019pgB\u0011\u0011+Z\u0005\u0003M\u001e\u0014\u0001\u0002U8tSRLwN\\\u0005\u0003Q2\u0011\u0011\u0002U8tSRLwN\\:\t\u000buz\u0006\u0019\u00016\u0011\u0005-tgBA\u0019m\u0013\tig!\u0001\u0004Qe\u0016$WMZ\u0005\u0003_B\u0014aa\u0015;sS:<'BA7\u0007\u0011\u0015\u0011\b\u0001\"\u0005t\u0003=\u0001VM\u001d*v]J+\u0007o\u001c:uS:<W#\u00010\u0006\tU\u0004\u0001A\u001e\u0002\u000b)J,WmQ8qS\u0016\u0014\bCA)x\u0013\tA\u0018PA\u000bJ]R,'O\\1m)J,WmQ8qS\u0016\u0014x\n]:\n\u0005id!!\u0002+sK\u0016\u001c\bb\u0002?\u0001\u0005\u0004%\u0019!`\u0001\u000e)J,WmQ8qS\u0016\u0014H+Y4\u0016\u0003y\u0004Ra`A\u0001\u0003\u000bi\u0011\u0001B\u0005\u0004\u0003\u0007!!\u0001C\"mCN\u001cH+Y4\u0011\u0005E#\bbBA\u0005\u0001\u0001\u0006IA`\u0001\u000f)J,WmQ8qS\u0016\u0014H+Y4!\u0011\u001d\ti\u0001\u0001C\u0001\u0003\u001f\t1C\\3x'R\u0014\u0018n\u0019;Ue\u0016,7i\u001c9jKJ,\"!!\u0002\t\u000f\u0005M\u0001\u0001\"\u0001\u0002\u0010\u0005\tb.Z<MCjLHK]3f\u0007>\u0004\u0018.\u001a:\t\u000f\u0005]\u0001\u0001\"\u0001\u0002\u001a\u000592-\u001e:sK:$hI]3tQ:\u000bW.Z\"sK\u0006$xN]\u000b\u0003\u00037\u0001B!!\b\u0002$5\u0011\u0011q\u0004\u0006\u0004\u0003Ca\u0011\u0001B;uS2LA!!\n\u0002 \t\u0001bI]3tQ:\u000bW.Z\"sK\u0006$xN\u001d\u0005\n\u001b\u0001A)\u0019!C!\u0003S)\"!a\u000b\u0011\u0007E\u000bi#\u0003\u0003\u00020\u0005E\"\u0001C%oi\u0016\u0014h.\u00197\n\u0007\u0005MBBA\u0005J]R,'O\\1mg\"Q\u0011q\u0007\u0001\t\u0002\u0003\u0006K!a\u000b\u0002\u0013%tG/\u001a:oC2\u0004saBA\u001e\u0001!\u0005\u0011QH\u0001\tiJ,W-\u00138g_B\u0019\u0011+a\u0010\u0007\u000f\u0005\u0005\u0003\u0001#\u0001\u0002D\tAAO]3f\u0013:4wn\u0005\u0003\u0002@\u0005\u0015\u0003cA\u0006\u0002H%\u0019\u0011\u0011\n\u0007\u0003\u0011Q\u0013X-Z%oM>D!\"!\u0014\u0002@\t\u0007I\u0011AA(\u0003\u00199Gn\u001c2bYV\t\u0011\u000bC\u0006\u0002T\u0005}B\u0011!A!\u0002\u0013\t\u0016aB4m_\n\fG\u000e\t\u0005\b5\u0005}B\u0011AA,)\t\ti\u0004C\u0004\u0002\\\u0001!\t!!\u0018\u0002\t%t\u0017\u000e\u001e\u000b\u0002s\u0001")
public class JavaUniverse
extends scala.reflect.internal.SymbolTable
implements JavaUniverseForce,
ReflectSetup,
SymbolTable {
    private Settings settings;
    private final boolean isLogging;
    private final ClassTag<Trees.InternalTreeCopierOps> TreeCopierTag;
    private Universe.MacroInternalApi internal;
    private volatile JavaUniverse$treeInfo$ treeInfo$module;
    private final ReentrantLock scala$reflect$runtime$Gil$$gil;
    private final Object scala$reflect$runtime$SynchronizedTypes$$uniqueLock;
    private final WeakHashMap<Types.Type, java.lang.ref.WeakReference<Types.Type>> scala$reflect$runtime$SynchronizedTypes$$uniques;
    private final ThreadLocalStorage.ThreadLocalStorage<Object> scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel;
    private final ThreadLocalStorage.ThreadLocalStorage<TypeConstraints.UndoLog> scala$reflect$runtime$SynchronizedTypes$$_undoLog;
    private final ThreadLocalStorage.ThreadLocalStorage<WeakHashMap<List<Types.Type>, WeakReference<Types.Type>>> scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness;
    private final ThreadLocalStorage.ThreadLocalStorage<Object> scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions;
    private final ThreadLocalStorage.ThreadLocalStorage<HashSet<TypeComparers.SubTypePair>> scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes;
    private final ThreadLocalStorage.ThreadLocalStorage<Object> scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions;
    private final ThreadLocalStorage.ThreadLocalStorage<HashSet<Types.Type>> scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes;
    private final ThreadLocalStorage.ThreadLocalStorage<HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type>> scala$reflect$runtime$SynchronizedTypes$$_lubResults;
    private final ThreadLocalStorage.ThreadLocalStorage<HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type>> scala$reflect$runtime$SynchronizedTypes$$_glbResults;
    private final ThreadLocalStorage.ThreadLocalStorage<String> scala$reflect$runtime$SynchronizedTypes$$_indent;
    private final ThreadLocalStorage.ThreadLocalStorage<Object> scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions;
    private final ThreadLocalStorage.ThreadLocalStorage<HashSet<Types.Type>> scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects;
    private final AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicIds;
    private final AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds;
    private final ThreadLocalStorage.ThreadLocalStorage<Map<Symbols.Symbol, Object>> scala$reflect$runtime$SynchronizedSymbols$$_recursionTable;
    private final WeakHashMap<ClassLoader, WeakReference<JavaMirrors.JavaMirror>> scala$reflect$runtime$JavaMirrors$$mirrors;
    private final ClassTag<JavaMirrors.JavaMirror> MirrorTag;
    private final JavaMirrors.JavaMirror rootMirror;
    private final ClassTag<Class<?>> RuntimeClassTag;
    private final Phase[] phaseWithId;
    private final int currentRunId;
    private volatile int bitmap$0;

    private Settings settings$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 1) == 0) {
                this.settings = new Settings();
                this.bitmap$0 |= 1;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.settings;
        }
    }

    private Universe.MacroInternalApi internal$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 2) == 0) {
                this.internal = new Internals.SymbolTableInternal(this){
                    private final /* synthetic */ JavaUniverse $outer;
                    private final Internals.ReificationSupportApi reificationSupport;
                    private final Universe.TreeGen gen;
                    private final Universe.MacroInternalApi.MacroDecoratorApi decorators;
                    private volatile byte bitmap$0;

                    private Internals.ReificationSupportApi reificationSupport$lzycompute() {
                        $anon$1 var1_1 = this;
                        synchronized (var1_1) {
                            if ((byte)(this.bitmap$0 & 1) == 0) {
                                this.reificationSupport = Internals$SymbolTableInternal$class.reificationSupport(this);
                                this.bitmap$0 = (byte)(this.bitmap$0 | 1);
                            }
                            // ** MonitorExit[this] (shouldn't be in output)
                            return this.reificationSupport;
                        }
                    }

                    public Internals.ReificationSupportApi reificationSupport() {
                        return (byte)(this.bitmap$0 & 1) == 0 ? this.reificationSupport$lzycompute() : this.reificationSupport;
                    }

                    private Universe.TreeGen gen$lzycompute() {
                        $anon$1 var1_1 = this;
                        synchronized (var1_1) {
                            if ((byte)(this.bitmap$0 & 2) == 0) {
                                this.gen = Internals$SymbolTableInternal$class.gen(this);
                                this.bitmap$0 = (byte)(this.bitmap$0 | 2);
                            }
                            // ** MonitorExit[this] (shouldn't be in output)
                            return this.gen;
                        }
                    }

                    public Universe.TreeGen gen() {
                        return (byte)(this.bitmap$0 & 2) == 0 ? this.gen$lzycompute() : this.gen;
                    }

                    private Universe.MacroInternalApi.MacroDecoratorApi decorators$lzycompute() {
                        $anon$1 var1_1 = this;
                        synchronized (var1_1) {
                            if ((byte)(this.bitmap$0 & 4) == 0) {
                                this.decorators = Internals$SymbolTableInternal$class.decorators(this);
                                this.bitmap$0 = (byte)(this.bitmap$0 | 4);
                            }
                            // ** MonitorExit[this] (shouldn't be in output)
                            return this.decorators;
                        }
                    }

                    public Universe.MacroInternalApi.MacroDecoratorApi decorators() {
                        return (byte)(this.bitmap$0 & 4) == 0 ? this.decorators$lzycompute() : this.decorators;
                    }

                    public Internals.Importer createImporter(scala.reflect.api.Universe from0) {
                        return Internals$SymbolTableInternal$class.createImporter(this, from0);
                    }

                    public Scopes.Scope newScopeWith(Seq<Symbols.Symbol> elems) {
                        return Internals$SymbolTableInternal$class.newScopeWith(this, elems);
                    }

                    public Scopes.Scope enter(Scopes.Scope scope, Symbols.Symbol sym) {
                        return Internals$SymbolTableInternal$class.enter(this, scope, sym);
                    }

                    public Scopes.Scope unlink(Scopes.Scope scope, Symbols.Symbol sym) {
                        return Internals$SymbolTableInternal$class.unlink(this, scope, sym);
                    }

                    public List<Symbols.FreeTermSymbol> freeTerms(Trees.Tree tree) {
                        return Internals$SymbolTableInternal$class.freeTerms(this, tree);
                    }

                    public List<Symbols.FreeTypeSymbol> freeTypes(Trees.Tree tree) {
                        return Internals$SymbolTableInternal$class.freeTypes(this, tree);
                    }

                    public Trees.Tree substituteSymbols(Trees.Tree tree, List<Symbols.Symbol> from2, List<Symbols.Symbol> to2) {
                        return Internals$SymbolTableInternal$class.substituteSymbols(this, tree, from2, to2);
                    }

                    public Trees.Tree substituteTypes(Trees.Tree tree, List<Symbols.Symbol> from2, List<Types.Type> to2) {
                        return Internals$SymbolTableInternal$class.substituteTypes(this, tree, from2, to2);
                    }

                    public Trees.Tree substituteThis(Trees.Tree tree, Symbols.Symbol clazz, Trees.Tree to2) {
                        return Internals$SymbolTableInternal$class.substituteThis(this, tree, clazz, to2);
                    }

                    public Attachments attachments(Trees.Tree tree) {
                        return Internals$SymbolTableInternal$class.attachments((Internals.SymbolTableInternal)this, tree);
                    }

                    public <T> Trees.Tree updateAttachment(Trees.Tree tree, T attachment, ClassTag<T> evidence$1) {
                        return Internals$SymbolTableInternal$class.updateAttachment((Internals.SymbolTableInternal)this, tree, attachment, evidence$1);
                    }

                    public <T> Trees.Tree removeAttachment(Trees.Tree tree, ClassTag<T> evidence$2) {
                        return Internals$SymbolTableInternal$class.removeAttachment((Internals.SymbolTableInternal)this, tree, evidence$2);
                    }

                    public Trees.Tree setPos(Trees.Tree tree, Position newpos) {
                        return Internals$SymbolTableInternal$class.setPos(this, tree, newpos);
                    }

                    public Trees.Tree setType(Trees.Tree tree, Types.Type tp) {
                        return Internals$SymbolTableInternal$class.setType(this, tree, tp);
                    }

                    public Trees.Tree defineType(Trees.Tree tree, Types.Type tp) {
                        return Internals$SymbolTableInternal$class.defineType(this, tree, tp);
                    }

                    public Trees.Tree setSymbol(Trees.Tree tree, Symbols.Symbol sym) {
                        return Internals$SymbolTableInternal$class.setSymbol(this, tree, sym);
                    }

                    public Trees.TypeTree setOriginal(Trees.TypeTree tt, Trees.Tree tree) {
                        return Internals$SymbolTableInternal$class.setOriginal(this, tt, tree);
                    }

                    public void captureVariable(Symbols.Symbol vble) {
                        Internals$SymbolTableInternal$class.captureVariable(this, vble);
                    }

                    public Trees.Tree referenceCapturedVariable(Symbols.Symbol vble) {
                        return Internals$SymbolTableInternal$class.referenceCapturedVariable(this, vble);
                    }

                    public Types.Type capturedVariableType(Symbols.Symbol vble) {
                        return Internals$SymbolTableInternal$class.capturedVariableType(this, vble);
                    }

                    public Trees.ClassDef classDef(Symbols.Symbol sym, Trees.Template impl) {
                        return Internals$SymbolTableInternal$class.classDef(this, sym, impl);
                    }

                    public Trees.ModuleDef moduleDef(Symbols.Symbol sym, Trees.Template impl) {
                        return Internals$SymbolTableInternal$class.moduleDef(this, sym, impl);
                    }

                    public Trees.ValDef valDef(Symbols.Symbol sym, Trees.Tree rhs) {
                        return Internals$SymbolTableInternal$class.valDef(this, sym, rhs);
                    }

                    public Trees.ValDef valDef(Symbols.Symbol sym) {
                        return Internals$SymbolTableInternal$class.valDef(this, sym);
                    }

                    public Trees.DefDef defDef(Symbols.Symbol sym, Trees.Modifiers mods, List<List<Trees.ValDef>> vparamss, Trees.Tree rhs) {
                        return Internals$SymbolTableInternal$class.defDef(this, sym, mods, vparamss, rhs);
                    }

                    public Trees.DefDef defDef(Symbols.Symbol sym, List<List<Trees.ValDef>> vparamss, Trees.Tree rhs) {
                        return Internals$SymbolTableInternal$class.defDef((Internals.SymbolTableInternal)this, sym, vparamss, rhs);
                    }

                    public Trees.DefDef defDef(Symbols.Symbol sym, Trees.Modifiers mods, Trees.Tree rhs) {
                        return Internals$SymbolTableInternal$class.defDef((Internals.SymbolTableInternal)this, sym, mods, rhs);
                    }

                    public Trees.DefDef defDef(Symbols.Symbol sym, Trees.Tree rhs) {
                        return Internals$SymbolTableInternal$class.defDef((Internals.SymbolTableInternal)this, sym, rhs);
                    }

                    public Trees.DefDef defDef(Symbols.Symbol sym, Function1<List<List<Symbols.Symbol>>, Trees.Tree> rhs) {
                        return Internals$SymbolTableInternal$class.defDef((Internals.SymbolTableInternal)this, sym, rhs);
                    }

                    public Trees.TypeDef typeDef(Symbols.Symbol sym, Trees.Tree rhs) {
                        return Internals$SymbolTableInternal$class.typeDef(this, sym, rhs);
                    }

                    public Trees.TypeDef typeDef(Symbols.Symbol sym) {
                        return Internals$SymbolTableInternal$class.typeDef(this, sym);
                    }

                    public Trees.LabelDef labelDef(Symbols.Symbol sym, List<Symbols.Symbol> params2, Trees.Tree rhs) {
                        return Internals$SymbolTableInternal$class.labelDef(this, sym, params2, rhs);
                    }

                    public Trees.Tree changeOwner(Trees.Tree tree, Symbols.Symbol prev, Symbols.Symbol next2) {
                        return Internals$SymbolTableInternal$class.changeOwner(this, tree, prev, next2);
                    }

                    public boolean isFreeTerm(Symbols.Symbol symbol) {
                        return Internals$SymbolTableInternal$class.isFreeTerm(this, symbol);
                    }

                    public Symbols.FreeTermSymbol asFreeTerm(Symbols.Symbol symbol) {
                        return Internals$SymbolTableInternal$class.asFreeTerm(this, symbol);
                    }

                    public boolean isFreeType(Symbols.Symbol symbol) {
                        return Internals$SymbolTableInternal$class.isFreeType(this, symbol);
                    }

                    public Symbols.FreeTypeSymbol asFreeType(Symbols.Symbol symbol) {
                        return Internals$SymbolTableInternal$class.asFreeType(this, symbol);
                    }

                    public Symbols.TermSymbol newTermSymbol(Symbols.Symbol symbol, Names.TermName name, Position pos, long flags) {
                        return Internals$SymbolTableInternal$class.newTermSymbol(this, symbol, name, pos, flags);
                    }

                    public Tuple2<Symbols.ModuleSymbol, Symbols.ClassSymbol> newModuleAndClassSymbol(Symbols.Symbol symbol, Names.Name name, Position pos, long flags) {
                        return Internals$SymbolTableInternal$class.newModuleAndClassSymbol(this, symbol, name, pos, flags);
                    }

                    public Symbols.MethodSymbol newMethodSymbol(Symbols.Symbol symbol, Names.TermName name, Position pos, long flags) {
                        return Internals$SymbolTableInternal$class.newMethodSymbol(this, symbol, name, pos, flags);
                    }

                    public Symbols.TypeSymbol newTypeSymbol(Symbols.Symbol symbol, Names.TypeName name, Position pos, long flags) {
                        return Internals$SymbolTableInternal$class.newTypeSymbol(this, symbol, name, pos, flags);
                    }

                    public Symbols.ClassSymbol newClassSymbol(Symbols.Symbol symbol, Names.TypeName name, Position pos, long flags) {
                        return Internals$SymbolTableInternal$class.newClassSymbol(this, symbol, name, pos, flags);
                    }

                    public Symbols.FreeTermSymbol newFreeTerm(String name, Function0<Object> value, long flags, String origin) {
                        return Internals$SymbolTableInternal$class.newFreeTerm(this, name, value, flags, origin);
                    }

                    public Symbols.FreeTypeSymbol newFreeType(String name, long flags, String origin) {
                        return Internals$SymbolTableInternal$class.newFreeType(this, name, flags, origin);
                    }

                    public boolean isErroneous(Symbols.Symbol symbol) {
                        return Internals$SymbolTableInternal$class.isErroneous(this, symbol);
                    }

                    public boolean isSkolem(Symbols.Symbol symbol) {
                        return Internals$SymbolTableInternal$class.isSkolem(this, symbol);
                    }

                    public Symbols.Symbol deSkolemize(Symbols.Symbol symbol) {
                        return Internals$SymbolTableInternal$class.deSkolemize(this, symbol);
                    }

                    public Symbols.Symbol initialize(Symbols.Symbol symbol) {
                        return Internals$SymbolTableInternal$class.initialize(this, symbol);
                    }

                    public Symbols.Symbol fullyInitialize(Symbols.Symbol symbol) {
                        return Internals$SymbolTableInternal$class.fullyInitialize((Internals.SymbolTableInternal)this, symbol);
                    }

                    public Types.Type fullyInitialize(Types.Type tp) {
                        return Internals$SymbolTableInternal$class.fullyInitialize((Internals.SymbolTableInternal)this, tp);
                    }

                    public Scopes.Scope fullyInitialize(Scopes.Scope scope) {
                        return Internals$SymbolTableInternal$class.fullyInitialize((Internals.SymbolTableInternal)this, scope);
                    }

                    public long flags(Symbols.Symbol symbol) {
                        return Internals$SymbolTableInternal$class.flags(this, symbol);
                    }

                    public Attachments attachments(Symbols.Symbol symbol) {
                        return Internals$SymbolTableInternal$class.attachments((Internals.SymbolTableInternal)this, symbol);
                    }

                    public <T> Symbols.Symbol updateAttachment(Symbols.Symbol symbol, T attachment, ClassTag<T> evidence$3) {
                        return Internals$SymbolTableInternal$class.updateAttachment((Internals.SymbolTableInternal)this, symbol, attachment, evidence$3);
                    }

                    public <T> Symbols.Symbol removeAttachment(Symbols.Symbol symbol, ClassTag<T> evidence$4) {
                        return Internals$SymbolTableInternal$class.removeAttachment((Internals.SymbolTableInternal)this, symbol, evidence$4);
                    }

                    public Symbols.Symbol setOwner(Symbols.Symbol symbol, Symbols.Symbol newowner) {
                        return Internals$SymbolTableInternal$class.setOwner(this, symbol, newowner);
                    }

                    public Symbols.Symbol setInfo(Symbols.Symbol symbol, Types.Type tpe) {
                        return Internals$SymbolTableInternal$class.setInfo(this, symbol, tpe);
                    }

                    public Symbols.Symbol setAnnotations(Symbols.Symbol symbol, Seq<AnnotationInfos.AnnotationInfo> annots) {
                        return Internals$SymbolTableInternal$class.setAnnotations(this, symbol, annots);
                    }

                    public Symbols.Symbol setName(Symbols.Symbol symbol, Names.Name name) {
                        return Internals$SymbolTableInternal$class.setName(this, symbol, name);
                    }

                    public Symbols.Symbol setPrivateWithin(Symbols.Symbol symbol, Symbols.Symbol sym) {
                        return Internals$SymbolTableInternal$class.setPrivateWithin(this, symbol, sym);
                    }

                    public Symbols.Symbol setFlag(Symbols.Symbol symbol, long flags) {
                        return Internals$SymbolTableInternal$class.setFlag(this, symbol, flags);
                    }

                    public Symbols.Symbol resetFlag(Symbols.Symbol symbol, long flags) {
                        return Internals$SymbolTableInternal$class.resetFlag(this, symbol, flags);
                    }

                    public Types.Type thisType(Symbols.Symbol sym) {
                        return Internals$SymbolTableInternal$class.thisType(this, sym);
                    }

                    public Types.Type singleType(Types.Type pre, Symbols.Symbol sym) {
                        return Internals$SymbolTableInternal$class.singleType(this, pre, sym);
                    }

                    public Types.Type superType(Types.Type thistpe, Types.Type supertpe) {
                        return Internals$SymbolTableInternal$class.superType(this, thistpe, supertpe);
                    }

                    public Types.ConstantType constantType(Constants.Constant value) {
                        return Internals$SymbolTableInternal$class.constantType(this, value);
                    }

                    public Types.Type typeRef(Types.Type pre, Symbols.Symbol sym, List<Types.Type> args) {
                        return Internals$SymbolTableInternal$class.typeRef(this, pre, sym, args);
                    }

                    public Types.RefinedType refinedType(List<Types.Type> parents2, Scopes.Scope decls) {
                        return Internals$SymbolTableInternal$class.refinedType((Internals.SymbolTableInternal)this, parents2, decls);
                    }

                    public Types.RefinedType refinedType(List<Types.Type> parents2, Scopes.Scope decls, Symbols.Symbol clazz) {
                        return Internals$SymbolTableInternal$class.refinedType((Internals.SymbolTableInternal)this, parents2, decls, clazz);
                    }

                    public Types.Type refinedType(List<Types.Type> parents2, Symbols.Symbol owner2) {
                        return Internals$SymbolTableInternal$class.refinedType((Internals.SymbolTableInternal)this, parents2, owner2);
                    }

                    public Types.Type refinedType(List<Types.Type> parents2, Symbols.Symbol owner2, Scopes.Scope decls) {
                        return Internals$SymbolTableInternal$class.refinedType((Internals.SymbolTableInternal)this, parents2, owner2, decls);
                    }

                    public Types.Type refinedType(List<Types.Type> parents2, Symbols.Symbol owner2, Scopes.Scope decls, Position pos) {
                        return Internals$SymbolTableInternal$class.refinedType(this, parents2, owner2, decls, pos);
                    }

                    public Types.Type intersectionType(List<Types.Type> tps) {
                        return Internals$SymbolTableInternal$class.intersectionType(this, tps);
                    }

                    public Types.Type intersectionType(List<Types.Type> tps, Symbols.Symbol owner2) {
                        return Internals$SymbolTableInternal$class.intersectionType(this, tps, owner2);
                    }

                    public Types.ClassInfoType classInfoType(List<Types.Type> parents2, Scopes.Scope decls, Symbols.Symbol typeSymbol2) {
                        return Internals$SymbolTableInternal$class.classInfoType(this, parents2, decls, typeSymbol2);
                    }

                    public Types.MethodType methodType(List<Symbols.Symbol> params2, Types.Type resultType) {
                        return Internals$SymbolTableInternal$class.methodType(this, params2, resultType);
                    }

                    public Types.NullaryMethodType nullaryMethodType(Types.Type resultType) {
                        return Internals$SymbolTableInternal$class.nullaryMethodType(this, resultType);
                    }

                    public Types.PolyType polyType(List<Symbols.Symbol> typeParams2, Types.Type resultType) {
                        return Internals$SymbolTableInternal$class.polyType(this, typeParams2, resultType);
                    }

                    public Types.ExistentialType existentialType(List<Symbols.Symbol> quantified, Types.Type underlying) {
                        return Internals$SymbolTableInternal$class.existentialType(this, quantified, underlying);
                    }

                    public Types.Type existentialAbstraction(List<Symbols.Symbol> tparams2, Types.Type tpe0) {
                        return Internals$SymbolTableInternal$class.existentialAbstraction(this, tparams2, tpe0);
                    }

                    public Types.AnnotatedType annotatedType(List<AnnotationInfos.AnnotationInfo> annotations, Types.Type underlying) {
                        return Internals$SymbolTableInternal$class.annotatedType(this, annotations, underlying);
                    }

                    public Types.TypeBounds typeBounds(Types.Type lo, Types.Type hi) {
                        return Internals$SymbolTableInternal$class.typeBounds(this, lo, hi);
                    }

                    public Types.BoundedWildcardType boundedWildcardType(Types.TypeBounds bounds) {
                        return Internals$SymbolTableInternal$class.boundedWildcardType(this, bounds);
                    }

                    public Option<List<Trees.Tree>> subpatterns(Trees.Tree tree) {
                        return Internals$SymbolTableInternal$class.subpatterns(this, tree);
                    }

                    public Position newTermSymbol$default$3() {
                        return Internals$SymbolTableInternal$class.newTermSymbol$default$3(this);
                    }

                    public long newTermSymbol$default$4() {
                        return Internals$SymbolTableInternal$class.newTermSymbol$default$4(this);
                    }

                    public Position newModuleAndClassSymbol$default$3() {
                        return Internals$SymbolTableInternal$class.newModuleAndClassSymbol$default$3(this);
                    }

                    public long newModuleAndClassSymbol$default$4() {
                        return Internals$SymbolTableInternal$class.newModuleAndClassSymbol$default$4(this);
                    }

                    public Position newMethodSymbol$default$3() {
                        return Internals$SymbolTableInternal$class.newMethodSymbol$default$3(this);
                    }

                    public long newMethodSymbol$default$4() {
                        return Internals$SymbolTableInternal$class.newMethodSymbol$default$4(this);
                    }

                    public Position newTypeSymbol$default$3() {
                        return Internals$SymbolTableInternal$class.newTypeSymbol$default$3(this);
                    }

                    public long newTypeSymbol$default$4() {
                        return Internals$SymbolTableInternal$class.newTypeSymbol$default$4(this);
                    }

                    public Position newClassSymbol$default$3() {
                        return Internals$SymbolTableInternal$class.newClassSymbol$default$3(this);
                    }

                    public long newClassSymbol$default$4() {
                        return Internals$SymbolTableInternal$class.newClassSymbol$default$4(this);
                    }

                    public long newFreeTerm$default$3() {
                        return Internals$SymbolTableInternal$class.newFreeTerm$default$3(this);
                    }

                    public String newFreeTerm$default$4() {
                        return Internals$SymbolTableInternal$class.newFreeTerm$default$4(this);
                    }

                    public long newFreeType$default$2() {
                        return Internals$SymbolTableInternal$class.newFreeType$default$2(this);
                    }

                    public String newFreeType$default$3() {
                        return Internals$SymbolTableInternal$class.newFreeType$default$3(this);
                    }

                    public <T> Manifest<T> typeTagToManifest(Object mirror0, TypeTags.TypeTag<T> tag, ClassTag<T> evidence$1) {
                        JavaMirrors.JavaMirror mirror = (JavaMirrors.JavaMirror)mirror0;
                        Class<?> runtimeClass = mirror.runtimeClass((Types.Type)tag.in(mirror).tpe());
                        return package$.MODULE$.Manifest().classType(runtimeClass);
                    }

                    public <T> TypeTags.TypeTag<T> manifestToTypeTag(Object mirror0, Manifest<T> manifest) {
                        return this.$outer.TypeTag().apply((JavaMirrors.JavaMirror)mirror0, new TypeCreator(this, manifest){
                            private final Manifest manifest$1;

                            public <U extends scala.reflect.api.Universe> Types.TypeApi apply(Mirror<U> mirror) {
                                Types.TypeApi typeApi;
                                U u = mirror.universe();
                                if (u instanceof JavaUniverse) {
                                    Types.TypeApi tpe;
                                    Types.TypeApi typeApi2;
                                    JavaUniverse javaUniverse = (JavaUniverse)u;
                                    JavaMirrors.JavaMirror jm = (JavaMirrors.JavaMirror)mirror;
                                    Symbols.ClassSymbol sym = jm.classSymbol(this.manifest$1.runtimeClass());
                                    if (this.manifest$1.typeArguments().isEmpty()) {
                                        typeApi2 = sym.toType();
                                    } else {
                                        List<?> tags = this.manifest$1.typeArguments().map(new Serializable(this, jm, javaUniverse){
                                            public static final long serialVersionUID = 0L;
                                            private final JavaMirrors.JavaMirror jm$1;
                                            private final JavaUniverse x2$1;

                                            public final TypeTags.TypeTag<?> apply(Manifest<?> targ) {
                                                return this.x2$1.internal().manifestToTypeTag(this.jm$1, targ);
                                            }
                                            {
                                                this.jm$1 = jm$1;
                                                this.x2$1 = x2$1;
                                            }
                                        }, List$.MODULE$.canBuildFrom());
                                        typeApi2 = javaUniverse.appliedType((Types.Type)sym.toTypeConstructor(), tags.map(new Serializable(this, jm){
                                            public static final long serialVersionUID = 0L;
                                            private final JavaMirrors.JavaMirror jm$1;

                                            public final Types.Type apply(TypeTags.TypeTag<?> x$1) {
                                                return (Types.Type)x$1.in(this.jm$1).tpe();
                                            }
                                            {
                                                this.jm$1 = jm$1;
                                            }
                                        }, List$.MODULE$.canBuildFrom()));
                                    }
                                    typeApi = tpe = typeApi2;
                                } else {
                                    typeApi = u.internal().manifestToTypeTag(mirror, this.manifest$1).in(mirror).tpe();
                                }
                                return typeApi;
                            }
                            {
                                this.manifest$1 = manifest$1;
                            }
                        });
                    }

                    public /* synthetic */ Internals scala$reflect$internal$Internals$SymbolTableInternal$$$outer() {
                        return this.$outer;
                    }

                    public /* synthetic */ Universe scala$reflect$macros$Universe$MacroInternalApi$$$outer() {
                        return this.$outer;
                    }

                    public /* synthetic */ scala.reflect.api.Internals scala$reflect$api$Internals$InternalApi$$$outer() {
                        return this.$outer;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        Internals$InternalApi$class.$init$(this);
                        Universe$MacroInternalApi$class.$init$(this);
                        Internals$SymbolTableInternal$class.$init$(this);
                    }
                };
                this.bitmap$0 |= 2;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.internal;
        }
    }

    private JavaUniverse$treeInfo$ treeInfo$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if (this.treeInfo$module == null) {
                this.treeInfo$module = new JavaUniverse$treeInfo$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.treeInfo$module;
        }
    }

    @Override
    public void info(Function0<String> msg) {
        SymbolTable$class.info(this, msg);
    }

    @Override
    public void debugInfo(Function0<String> msg) {
        SymbolTable$class.debugInfo(this, msg);
    }

    @Override
    public boolean isCompilerUniverse() {
        return SymbolTable$class.isCompilerUniverse(this);
    }

    @Override
    public final <T> ThreadLocalStorage.ThreadLocalStorage<T> mkThreadLocalStorage(Function0<T> x) {
        return ThreadLocalStorage$class.mkThreadLocalStorage(this, x);
    }

    private ReentrantLock scala$reflect$runtime$Gil$$gil$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 4) == 0) {
                this.scala$reflect$runtime$Gil$$gil = Gil$class.scala$reflect$runtime$Gil$$gil(this);
                this.bitmap$0 |= 4;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$Gil$$gil;
        }
    }

    @Override
    public ReentrantLock scala$reflect$runtime$Gil$$gil() {
        return (this.bitmap$0 & 4) == 0 ? this.scala$reflect$runtime$Gil$$gil$lzycompute() : this.scala$reflect$runtime$Gil$$gil;
    }

    @Override
    public final <T> T gilSynchronized(Function0<T> body2) {
        return (T)Gil$class.gilSynchronized(this, body2);
    }

    @Override
    public boolean synchronizeNames() {
        return SynchronizedOps$class.synchronizeNames(this);
    }

    @Override
    public BaseTypeSeqs.BaseTypeSeq newBaseTypeSeq(List<Types.Type> parents2, Types.Type[] elems) {
        return SynchronizedOps$class.newBaseTypeSeq(this, parents2, elems);
    }

    @Override
    public SynchronizedOps.SynchronizedScope newScope() {
        return SynchronizedOps$class.newScope(this);
    }

    private Object scala$reflect$runtime$SynchronizedTypes$$uniqueLock$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 8) == 0) {
                this.scala$reflect$runtime$SynchronizedTypes$$uniqueLock = SynchronizedTypes$class.scala$reflect$runtime$SynchronizedTypes$$uniqueLock(this);
                this.bitmap$0 |= 8;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedTypes$$uniqueLock;
        }
    }

    @Override
    public Object scala$reflect$runtime$SynchronizedTypes$$uniqueLock() {
        return (this.bitmap$0 & 8) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$uniqueLock$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$uniqueLock;
    }

    @Override
    public WeakHashMap<Types.Type, java.lang.ref.WeakReference<Types.Type>> scala$reflect$runtime$SynchronizedTypes$$uniques() {
        return this.scala$reflect$runtime$SynchronizedTypes$$uniques;
    }

    private ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x10) == 0) {
                this.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel = SynchronizedTypes$class.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel(this);
                this.bitmap$0 |= 0x10;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel;
        }
    }

    @Override
    public ThreadLocalStorage.ThreadLocalStorage<Object> scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel() {
        return (this.bitmap$0 & 0x10) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel;
    }

    private ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_undoLog$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x20) == 0) {
                this.scala$reflect$runtime$SynchronizedTypes$$_undoLog = SynchronizedTypes$class.scala$reflect$runtime$SynchronizedTypes$$_undoLog(this);
                this.bitmap$0 |= 0x20;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedTypes$$_undoLog;
        }
    }

    @Override
    public ThreadLocalStorage.ThreadLocalStorage<TypeConstraints.UndoLog> scala$reflect$runtime$SynchronizedTypes$$_undoLog() {
        return (this.bitmap$0 & 0x20) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_undoLog$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_undoLog;
    }

    private ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x40) == 0) {
                this.scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness = SynchronizedTypes$class.scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness(this);
                this.bitmap$0 |= 0x40;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness;
        }
    }

    @Override
    public ThreadLocalStorage.ThreadLocalStorage<WeakHashMap<List<Types.Type>, WeakReference<Types.Type>>> scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness() {
        return (this.bitmap$0 & 0x40) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness;
    }

    private ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x80) == 0) {
                this.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions = SynchronizedTypes$class.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions(this);
                this.bitmap$0 |= 0x80;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions;
        }
    }

    @Override
    public ThreadLocalStorage.ThreadLocalStorage<Object> scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions() {
        return (this.bitmap$0 & 0x80) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions;
    }

    private ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x100) == 0) {
                this.scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes = SynchronizedTypes$class.scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes(this);
                this.bitmap$0 |= 0x100;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes;
        }
    }

    @Override
    public ThreadLocalStorage.ThreadLocalStorage<HashSet<TypeComparers.SubTypePair>> scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes() {
        return (this.bitmap$0 & 0x100) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes;
    }

    private ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x200) == 0) {
                this.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions = SynchronizedTypes$class.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions(this);
                this.bitmap$0 |= 0x200;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions;
        }
    }

    @Override
    public ThreadLocalStorage.ThreadLocalStorage<Object> scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions() {
        return (this.bitmap$0 & 0x200) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions;
    }

    private ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x400) == 0) {
                this.scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes = SynchronizedTypes$class.scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes(this);
                this.bitmap$0 |= 0x400;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes;
        }
    }

    @Override
    public ThreadLocalStorage.ThreadLocalStorage<HashSet<Types.Type>> scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes() {
        return (this.bitmap$0 & 0x400) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes;
    }

    private ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_lubResults$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x800) == 0) {
                this.scala$reflect$runtime$SynchronizedTypes$$_lubResults = SynchronizedTypes$class.scala$reflect$runtime$SynchronizedTypes$$_lubResults(this);
                this.bitmap$0 |= 0x800;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedTypes$$_lubResults;
        }
    }

    @Override
    public ThreadLocalStorage.ThreadLocalStorage<HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type>> scala$reflect$runtime$SynchronizedTypes$$_lubResults() {
        return (this.bitmap$0 & 0x800) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_lubResults$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_lubResults;
    }

    private ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_glbResults$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x1000) == 0) {
                this.scala$reflect$runtime$SynchronizedTypes$$_glbResults = SynchronizedTypes$class.scala$reflect$runtime$SynchronizedTypes$$_glbResults(this);
                this.bitmap$0 |= 0x1000;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedTypes$$_glbResults;
        }
    }

    @Override
    public ThreadLocalStorage.ThreadLocalStorage<HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type>> scala$reflect$runtime$SynchronizedTypes$$_glbResults() {
        return (this.bitmap$0 & 0x1000) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_glbResults$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_glbResults;
    }

    private ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_indent$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x2000) == 0) {
                this.scala$reflect$runtime$SynchronizedTypes$$_indent = SynchronizedTypes$class.scala$reflect$runtime$SynchronizedTypes$$_indent(this);
                this.bitmap$0 |= 0x2000;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedTypes$$_indent;
        }
    }

    @Override
    public ThreadLocalStorage.ThreadLocalStorage<String> scala$reflect$runtime$SynchronizedTypes$$_indent() {
        return (this.bitmap$0 & 0x2000) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_indent$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_indent;
    }

    private ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x4000) == 0) {
                this.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions = SynchronizedTypes$class.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions(this);
                this.bitmap$0 |= 0x4000;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions;
        }
    }

    @Override
    public ThreadLocalStorage.ThreadLocalStorage<Object> scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions() {
        return (this.bitmap$0 & 0x4000) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions;
    }

    private ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x8000) == 0) {
                this.scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects = SynchronizedTypes$class.scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects(this);
                this.bitmap$0 |= 0x8000;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects;
        }
    }

    @Override
    public ThreadLocalStorage.ThreadLocalStorage<HashSet<Types.Type>> scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects() {
        return (this.bitmap$0 & 0x8000) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects;
    }

    @Override
    public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedTypes$$super$unique(Types.Type tp) {
        return Types$class.unique(this, tp);
    }

    @Override
    public /* synthetic */ void scala$reflect$runtime$SynchronizedTypes$$super$defineUnderlyingOfSingleType(Types.SingleType tpe) {
        Types$class.defineUnderlyingOfSingleType(this, tpe);
    }

    @Override
    public /* synthetic */ void scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfCompoundType(Types.CompoundType tpe) {
        Types$class.defineBaseTypeSeqOfCompoundType(this, tpe);
    }

    @Override
    public /* synthetic */ void scala$reflect$runtime$SynchronizedTypes$$super$defineBaseClassesOfCompoundType(Types.CompoundType tpe) {
        Types$class.defineBaseClassesOfCompoundType(this, tpe);
    }

    @Override
    public /* synthetic */ void scala$reflect$runtime$SynchronizedTypes$$super$defineParentsOfTypeRef(Types.TypeRef tpe) {
        Types$class.defineParentsOfTypeRef(this, tpe);
    }

    @Override
    public /* synthetic */ void scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfTypeRef(Types.TypeRef tpe) {
        Types$class.defineBaseTypeSeqOfTypeRef(this, tpe);
    }

    @Override
    public void scala$reflect$runtime$SynchronizedTypes$_setter_$scala$reflect$runtime$SynchronizedTypes$$uniques_$eq(WeakHashMap x$1) {
        this.scala$reflect$runtime$SynchronizedTypes$$uniques = x$1;
    }

    @Override
    public CommonOwners.CommonOwnerMap commonOwnerMap() {
        return SynchronizedTypes$class.commonOwnerMap(this);
    }

    @Override
    public <T extends Types.Type> T unique(T tp) {
        return (T)SynchronizedTypes$class.unique(this, tp);
    }

    @Override
    public int skolemizationLevel() {
        return SynchronizedTypes$class.skolemizationLevel(this);
    }

    @Override
    public void skolemizationLevel_$eq(int value) {
        SynchronizedTypes$class.skolemizationLevel_$eq(this, value);
    }

    @Override
    public TypeConstraints.UndoLog undoLog() {
        return SynchronizedTypes$class.undoLog(this);
    }

    @Override
    public WeakHashMap<List<Types.Type>, WeakReference<Types.Type>> intersectionWitness() {
        return SynchronizedTypes$class.intersectionWitness(this);
    }

    @Override
    public int subsametypeRecursions() {
        return SynchronizedTypes$class.subsametypeRecursions(this);
    }

    @Override
    public void subsametypeRecursions_$eq(int value) {
        SynchronizedTypes$class.subsametypeRecursions_$eq(this, value);
    }

    @Override
    public HashSet<TypeComparers.SubTypePair> pendingSubTypes() {
        return SynchronizedTypes$class.pendingSubTypes(this);
    }

    @Override
    public int basetypeRecursions() {
        return SynchronizedTypes$class.basetypeRecursions(this);
    }

    @Override
    public void basetypeRecursions_$eq(int value) {
        SynchronizedTypes$class.basetypeRecursions_$eq(this, value);
    }

    @Override
    public HashSet<Types.Type> pendingBaseTypes() {
        return SynchronizedTypes$class.pendingBaseTypes(this);
    }

    @Override
    public HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> lubResults() {
        return SynchronizedTypes$class.lubResults(this);
    }

    @Override
    public HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> glbResults() {
        return SynchronizedTypes$class.glbResults(this);
    }

    @Override
    public String indent() {
        return SynchronizedTypes$class.indent(this);
    }

    @Override
    public void indent_$eq(String value) {
        SynchronizedTypes$class.indent_$eq(this, value);
    }

    @Override
    public int toStringRecursions() {
        return SynchronizedTypes$class.toStringRecursions(this);
    }

    @Override
    public void toStringRecursions_$eq(int value) {
        SynchronizedTypes$class.toStringRecursions_$eq(this, value);
    }

    @Override
    public HashSet<Types.Type> toStringSubjects() {
        return SynchronizedTypes$class.toStringSubjects(this);
    }

    @Override
    public void defineUnderlyingOfSingleType(Types.SingleType tpe) {
        SynchronizedTypes$class.defineUnderlyingOfSingleType(this, tpe);
    }

    @Override
    public void defineBaseTypeSeqOfCompoundType(Types.CompoundType tpe) {
        SynchronizedTypes$class.defineBaseTypeSeqOfCompoundType(this, tpe);
    }

    @Override
    public void defineBaseClassesOfCompoundType(Types.CompoundType tpe) {
        SynchronizedTypes$class.defineBaseClassesOfCompoundType(this, tpe);
    }

    @Override
    public void defineParentsOfTypeRef(Types.TypeRef tpe) {
        SynchronizedTypes$class.defineParentsOfTypeRef(this, tpe);
    }

    @Override
    public void defineBaseTypeSeqOfTypeRef(Types.TypeRef tpe) {
        SynchronizedTypes$class.defineBaseTypeSeqOfTypeRef(this, tpe);
    }

    private AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicIds$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x10000) == 0) {
                this.scala$reflect$runtime$SynchronizedSymbols$$atomicIds = SynchronizedSymbols$class.scala$reflect$runtime$SynchronizedSymbols$$atomicIds(this);
                this.bitmap$0 |= 0x10000;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedSymbols$$atomicIds;
        }
    }

    @Override
    public AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicIds() {
        return (this.bitmap$0 & 0x10000) == 0 ? this.scala$reflect$runtime$SynchronizedSymbols$$atomicIds$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$$atomicIds;
    }

    private AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x20000) == 0) {
                this.scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds = SynchronizedSymbols$class.scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds(this);
                this.bitmap$0 |= 0x20000;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds;
        }
    }

    @Override
    public AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds() {
        return (this.bitmap$0 & 0x20000) == 0 ? this.scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds;
    }

    private ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedSymbols$$_recursionTable$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x40000) == 0) {
                this.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable = SynchronizedSymbols$class.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable(this);
                this.bitmap$0 |= 0x40000;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable;
        }
    }

    @Override
    public ThreadLocalStorage.ThreadLocalStorage<Map<Symbols.Symbol, Object>> scala$reflect$runtime$SynchronizedSymbols$$_recursionTable() {
        return (this.bitmap$0 & 0x40000) == 0 ? this.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable;
    }

    @Override
    public /* synthetic */ Symbols.ModuleSymbol scala$reflect$runtime$SynchronizedSymbols$$super$connectModuleToClass(Symbols.ModuleSymbol m, Symbols.ClassSymbol moduleClass) {
        return Symbols$class.connectModuleToClass(this, m, moduleClass);
    }

    @Override
    public int nextId() {
        return SynchronizedSymbols$class.nextId(this);
    }

    @Override
    public int nextExistentialId() {
        return SynchronizedSymbols$class.nextExistentialId(this);
    }

    @Override
    public Map<Symbols.Symbol, Object> recursionTable() {
        return SynchronizedSymbols$class.recursionTable(this);
    }

    @Override
    public void recursionTable_$eq(Map<Symbols.Symbol, Object> value) {
        SynchronizedSymbols$class.recursionTable_$eq(this, value);
    }

    @Override
    public Symbols.ModuleSymbol connectModuleToClass(Symbols.ModuleSymbol m, Symbols.ClassSymbol moduleClass) {
        return SynchronizedSymbols$class.connectModuleToClass(this, m, moduleClass);
    }

    @Override
    public Symbols.FreeTermSymbol newFreeTermSymbol(Names.TermName name, Function0<Object> value, long flags, String origin) {
        return SynchronizedSymbols$class.newFreeTermSymbol(this, name, value, flags, origin);
    }

    @Override
    public Symbols.FreeTypeSymbol newFreeTypeSymbol(Names.TypeName name, long flags, String origin) {
        return SynchronizedSymbols$class.newFreeTypeSymbol(this, name, flags, origin);
    }

    @Override
    public Symbols.NoSymbol makeNoSymbol() {
        return SynchronizedSymbols$class.makeNoSymbol(this);
    }

    @Override
    public long newFreeTermSymbol$default$3() {
        return SynchronizedSymbols$class.newFreeTermSymbol$default$3(this);
    }

    @Override
    public String newFreeTermSymbol$default$4() {
        return SynchronizedSymbols$class.newFreeTermSymbol$default$4(this);
    }

    @Override
    public long newFreeTypeSymbol$default$2() {
        return SynchronizedSymbols$class.newFreeTypeSymbol$default$2(this);
    }

    @Override
    public String newFreeTypeSymbol$default$3() {
        return SynchronizedSymbols$class.newFreeTypeSymbol$default$3(this);
    }

    @Override
    public Tuple2<Symbols.ClassSymbol, Symbols.ModuleSymbol> initAndEnterClassAndModule(Symbols.Symbol owner2, Names.TypeName name, Function2<Symbols.Symbol, Symbols.Symbol, Types.LazyType> completer) {
        return SymbolLoaders$class.initAndEnterClassAndModule(this, owner2, name, completer);
    }

    @Override
    public void setAllInfos(Symbols.Symbol clazz, Symbols.Symbol module, Types.Type info2) {
        SymbolLoaders$class.setAllInfos(this, clazz, module, info2);
    }

    @Override
    public void initClassAndModule(Symbols.Symbol clazz, Symbols.Symbol module, Types.LazyType completer) {
        SymbolLoaders$class.initClassAndModule(this, clazz, module, completer);
    }

    @Override
    public void validateClassInfo(Types.ClassInfoType tp) {
        SymbolLoaders$class.validateClassInfo(this, tp);
    }

    @Override
    public SymbolLoaders.PackageScope newPackageScope(Symbols.Symbol pkgClass) {
        return SymbolLoaders$class.newPackageScope(this, pkgClass);
    }

    @Override
    public Scopes.Scope scopeTransform(Symbols.Symbol owner2, Function0<Scopes.Scope> op) {
        return SymbolLoaders$class.scopeTransform(this, owner2, op);
    }

    private WeakHashMap scala$reflect$runtime$JavaMirrors$$mirrors$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x80000) == 0) {
                this.scala$reflect$runtime$JavaMirrors$$mirrors = JavaMirrors$class.scala$reflect$runtime$JavaMirrors$$mirrors(this);
                this.bitmap$0 |= 0x80000;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$runtime$JavaMirrors$$mirrors;
        }
    }

    @Override
    public WeakHashMap<ClassLoader, WeakReference<JavaMirrors.JavaMirror>> scala$reflect$runtime$JavaMirrors$$mirrors() {
        return (this.bitmap$0 & 0x80000) == 0 ? this.scala$reflect$runtime$JavaMirrors$$mirrors$lzycompute() : this.scala$reflect$runtime$JavaMirrors$$mirrors;
    }

    @Override
    public ClassTag<JavaMirrors.JavaMirror> MirrorTag() {
        return this.MirrorTag;
    }

    private JavaMirrors.JavaMirror rootMirror$lzycompute() {
        JavaUniverse javaUniverse = this;
        synchronized (javaUniverse) {
            if ((this.bitmap$0 & 0x100000) == 0) {
                this.rootMirror = JavaMirrors$class.rootMirror(this);
                this.bitmap$0 |= 0x100000;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.rootMirror;
        }
    }

    @Override
    public JavaMirrors.JavaMirror rootMirror() {
        return (this.bitmap$0 & 0x100000) == 0 ? this.rootMirror$lzycompute() : this.rootMirror;
    }

    @Override
    public /* synthetic */ Symbols.Symbol scala$reflect$runtime$JavaMirrors$$super$missingHook(Symbols.Symbol owner2, Names.Name name) {
        return super.missingHook(owner2, name);
    }

    @Override
    public void scala$reflect$runtime$JavaMirrors$_setter_$MirrorTag_$eq(ClassTag x$1) {
        this.MirrorTag = x$1;
    }

    @Override
    public ClassLoader rootClassLoader() {
        return JavaMirrors$class.rootClassLoader(this);
    }

    @Override
    public JavaMirrors.JavaMirror runtimeMirror(ClassLoader cl) {
        return JavaMirrors$class.runtimeMirror(this, cl);
    }

    @Override
    public JavaMirrors.JavaMirror mirrorThatLoaded(Symbols.Symbol sym) {
        return JavaMirrors$class.mirrorThatLoaded(this, sym);
    }

    @Override
    public Symbols.Symbol missingHook(Symbols.Symbol owner2, Names.Name name) {
        return JavaMirrors$class.missingHook(this, owner2, name);
    }

    @Override
    public ClassTag<Class<?>> RuntimeClassTag() {
        return this.RuntimeClassTag;
    }

    @Override
    public void scala$reflect$api$JavaUniverse$_setter_$RuntimeClassTag_$eq(ClassTag x$1) {
        this.RuntimeClassTag = x$1;
    }

    @Override
    public Phase[] phaseWithId() {
        return this.phaseWithId;
    }

    @Override
    public int currentRunId() {
        return this.currentRunId;
    }

    @Override
    public void scala$reflect$runtime$ReflectSetup$_setter_$phaseWithId_$eq(Phase[] x$1) {
        this.phaseWithId = x$1;
    }

    @Override
    public void scala$reflect$runtime$ReflectSetup$_setter_$currentRunId_$eq(int x$1) {
        this.currentRunId = x$1;
    }

    @Override
    public void force() {
        JavaUniverseForce$class.force(this);
    }

    @Override
    public SomePhase$ picklerPhase() {
        return SomePhase$.MODULE$;
    }

    @Override
    public SomePhase$ erasurePhase() {
        return SomePhase$.MODULE$;
    }

    @Override
    public Settings settings() {
        return (this.bitmap$0 & 1) == 0 ? this.settings$lzycompute() : this.settings;
    }

    private boolean isLogging() {
        return this.isLogging;
    }

    @Override
    public void log(Function0<Object> msg) {
        if (this.isLogging()) {
            Console$.MODULE$.err().println(new StringBuilder().append((Object)"[reflect] ").append(msg.apply()).toString());
        }
    }

    @Override
    public Reporter reporter() {
        return new ReporterImpl(this){
            private final /* synthetic */ JavaUniverse $outer;

            public void info0(Position pos, String msg, ReporterImpl.Severity severity, boolean force) {
                this.$outer.log((Function0<Object>)((Object)new Serializable(this, msg){
                    public static final long serialVersionUID = 0L;
                    private final String msg$1;

                    public final String apply() {
                        return this.msg$1;
                    }
                    {
                        this.msg$1 = msg$1;
                    }
                }));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
    }

    @Override
    public Reporting.RunReporting currentRun() {
        return new Reporting.RunReporting(this){
            private final /* synthetic */ JavaUniverse $outer;
            private final Reporting.PerRunReportingBase reporting;

            public Reporting.PerRunReportingBase reporting() {
                return this.reporting;
            }

            public void scala$reflect$internal$Reporting$RunReporting$_setter_$reporting_$eq(Reporting.PerRunReportingBase x$1) {
                this.reporting = x$1;
            }

            public /* synthetic */ Reporting scala$reflect$internal$Reporting$RunReporting$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                Reporting$RunReporting$class.$init$(this);
            }
        };
    }

    @Override
    public PerRunReporting PerRunReporting() {
        return new PerRunReporting(this);
    }

    public ClassTag<Trees.InternalTreeCopierOps> TreeCopierTag() {
        return this.TreeCopierTag;
    }

    @Override
    public Trees.InternalTreeCopierOps newStrictTreeCopier() {
        return new Trees.StrictTreeCopier(this);
    }

    @Override
    public Trees.InternalTreeCopierOps newLazyTreeCopier() {
        return new Trees.LazyTreeCopier(this);
    }

    @Override
    public FreshNameCreator currentFreshNameCreator() {
        return this.globalFreshNameCreator();
    }

    @Override
    public Universe.MacroInternalApi internal() {
        return (this.bitmap$0 & 2) == 0 ? this.internal$lzycompute() : this.internal;
    }

    @Override
    public JavaUniverse$treeInfo$ treeInfo() {
        return this.treeInfo$module == null ? this.treeInfo$lzycompute() : this.treeInfo$module;
    }

    public void init() {
        this.definitions().init();
        this.force();
    }

    public JavaUniverse() {
        JavaUniverseForce$class.$init$(this);
        ReflectSetup$class.$init$(this);
        JavaUniverse$class.$init$(this);
        TwoWayCaches$class.$init$(this);
        JavaMirrors$class.$init$(this);
        SymbolLoaders$class.$init$(this);
        SynchronizedSymbols$class.$init$(this);
        SynchronizedTypes$class.$init$(this);
        SynchronizedOps$class.$init$(this);
        Gil$class.$init$(this);
        ThreadLocalStorage$class.$init$(this);
        SymbolTable$class.$init$(this);
        this.isLogging = scala.sys.package$.MODULE$.props().contains("scala.debug.reflect");
        this.TreeCopierTag = ClassTag$.MODULE$.apply(Trees.InternalTreeCopierOps.class);
        this.init();
    }

    public class PerRunReporting
    extends Reporting.PerRunReportingBase {
        @Override
        public void deprecationWarning(Position pos, String msg) {
            this.scala$reflect$runtime$JavaUniverse$PerRunReporting$$$outer().reporter().warning(pos, msg);
        }

        public /* synthetic */ JavaUniverse scala$reflect$runtime$JavaUniverse$PerRunReporting$$$outer() {
            return (JavaUniverse)this.$outer;
        }

        public PerRunReporting(JavaUniverse $outer) {
        }
    }
}

