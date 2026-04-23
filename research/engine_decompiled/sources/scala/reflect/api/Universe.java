/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import java.io.PrintWriter;
import scala.Function1;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Annotations;
import scala.reflect.api.Annotations$class;
import scala.reflect.api.Constants;
import scala.reflect.api.Constants$class;
import scala.reflect.api.Exprs;
import scala.reflect.api.Exprs$Expr$;
import scala.reflect.api.Exprs$class;
import scala.reflect.api.FlagSets;
import scala.reflect.api.FlagSets$class;
import scala.reflect.api.ImplicitTags;
import scala.reflect.api.Internals;
import scala.reflect.api.Internals$class;
import scala.reflect.api.Liftables;
import scala.reflect.api.Liftables$Liftable$;
import scala.reflect.api.Liftables$Unliftable$;
import scala.reflect.api.Liftables$class;
import scala.reflect.api.Mirrors;
import scala.reflect.api.Mirrors$class;
import scala.reflect.api.Names;
import scala.reflect.api.Names$class;
import scala.reflect.api.Position;
import scala.reflect.api.Positions;
import scala.reflect.api.Printers;
import scala.reflect.api.Printers$BooleanFlag$;
import scala.reflect.api.Printers$class;
import scala.reflect.api.Quasiquotes;
import scala.reflect.api.Quasiquotes$class;
import scala.reflect.api.Scopes;
import scala.reflect.api.Scopes$class;
import scala.reflect.api.StandardDefinitions;
import scala.reflect.api.StandardDefinitions$class;
import scala.reflect.api.StandardLiftables;
import scala.reflect.api.StandardLiftables$class;
import scala.reflect.api.StandardLiftables$stdnme$;
import scala.reflect.api.StandardNames;
import scala.reflect.api.StandardNames$class;
import scala.reflect.api.Symbols;
import scala.reflect.api.Symbols$class;
import scala.reflect.api.Trees;
import scala.reflect.api.Trees$class;
import scala.reflect.api.TypeTags;
import scala.reflect.api.TypeTags$TypeTag$;
import scala.reflect.api.TypeTags$WeakTypeTag$;
import scala.reflect.api.TypeTags$class;
import scala.reflect.api.Types;
import scala.reflect.api.Types$class;

@ScalaSignature(bytes="\u0006\u0001\u00114Q!\u0001\u0002\u0002\u0002%\u0011\u0001\"\u00168jm\u0016\u00148/\u001a\u0006\u0003\u0007\u0011\t1!\u00199j\u0015\t)a!A\u0004sK\u001adWm\u0019;\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001a\u0005\f\u0001\u00159\u0011R\u0003G\u000e\u001fC\u0011:#&\f\u00194mebtHQ#I!\tYA\"D\u0001\u0007\u0013\tiaA\u0001\u0004B]f\u0014VM\u001a\t\u0003\u001fAi\u0011AA\u0005\u0003#\t\u0011qaU=nE>d7\u000f\u0005\u0002\u0010'%\u0011AC\u0001\u0002\u0006)f\u0004Xm\u001d\t\u0003\u001fYI!a\u0006\u0002\u0003\u0011\u0019c\u0017mZ*fiN\u0004\"aD\r\n\u0005i\u0011!AB*d_B,7\u000f\u0005\u0002\u00109%\u0011QD\u0001\u0002\u0006\u001d\u0006lWm\u001d\t\u0003\u001f}I!\u0001\t\u0002\u0003\u000bQ\u0013X-Z:\u0011\u0005=\u0011\u0013BA\u0012\u0003\u0005%\u0019uN\\:uC:$8\u000f\u0005\u0002\u0010K%\u0011aE\u0001\u0002\f\u0003:tw\u000e^1uS>t7\u000f\u0005\u0002\u0010Q%\u0011\u0011F\u0001\u0002\n!>\u001c\u0018\u000e^5p]N\u0004\"aD\u0016\n\u00051\u0012!!B#yaJ\u001c\bCA\b/\u0013\ty#A\u0001\u0005UsB,G+Y4t!\ty\u0011'\u0003\u00023\u0005\ta\u0011*\u001c9mS\u000eLG\u000fV1hgB\u0011q\u0002N\u0005\u0003k\t\u00111c\u0015;b]\u0012\f'\u000f\u001a#fM&t\u0017\u000e^5p]N\u0004\"aD\u001c\n\u0005a\u0012!!D*uC:$\u0017M\u001d3OC6,7\u000f\u0005\u0002\u0010u%\u00111H\u0001\u0002\u0012'R\fg\u000eZ1sI2Kg\r^1cY\u0016\u001c\bCA\b>\u0013\tq$AA\u0004NSJ\u0014xN]:\u0011\u0005=\u0001\u0015BA!\u0003\u0005!\u0001&/\u001b8uKJ\u001c\bCA\bD\u0013\t!%AA\u0005MS\u001a$\u0018M\u00197fgB\u0011qBR\u0005\u0003\u000f\n\u00111\"U;bg&\fXo\u001c;fgB\u0011q\"S\u0005\u0003\u0015\n\u0011\u0011\"\u00138uKJt\u0017\r\\:\t\u000b1\u0003A\u0011A'\u0002\rqJg.\u001b;?)\u0005q\u0005CA\b\u0001\u0011\u0019\u0001\u0006A!C\u0001#\u0006)!/Z5gsV\u0011!+\u0017\u000b\u0003'\n\u00042\u0001V+X\u001b\u0005\u0001\u0011B\u0001,,\u0005\u0011)\u0005\u0010\u001d:\u0011\u0005aKF\u0002\u0001\u0003\u00065>\u0013\ra\u0017\u0002\u0002)F\u0011Al\u0018\t\u0003\u0017uK!A\u0018\u0004\u0003\u000f9{G\u000f[5oOB\u00111\u0002Y\u0005\u0003C\u001a\u00111!\u00118z\u0011\u0015\u0019w\n1\u0001X\u0003\u0011)\u0007\u0010\u001d:")
public abstract class Universe
implements Symbols,
Types,
FlagSets,
Scopes,
Names,
Trees,
Constants,
Annotations,
Positions,
Exprs,
TypeTags,
ImplicitTags,
StandardDefinitions,
StandardNames,
StandardLiftables,
Mirrors,
Printers,
Liftables,
Quasiquotes,
Internals {
    private final Trees.TreeCopierOps treeCopy;
    private final Trees.ModifiersApi NoMods;
    private volatile Liftables$Liftable$ Liftable$module;
    private volatile Liftables$Unliftable$ Unliftable$module;
    private volatile Printers$BooleanFlag$ BooleanFlag$module;
    private volatile StandardLiftables$stdnme$ scala$reflect$api$StandardLiftables$$stdnme$module;
    private volatile TypeTags$WeakTypeTag$ WeakTypeTag$module;
    private volatile TypeTags$TypeTag$ TypeTag$module;
    private volatile Exprs$Expr$ Expr$module;
    private volatile boolean bitmap$0;

    @Override
    public Internals.Importer mkImporter(Universe from0) {
        return Internals$class.mkImporter(this, from0);
    }

    @Override
    public Quasiquotes.Quasiquote Quasiquote(StringContext ctx) {
        return Quasiquotes$class.Quasiquote(this, ctx);
    }

    private Liftables$Liftable$ Liftable$lzycompute() {
        Universe universe = this;
        synchronized (universe) {
            if (this.Liftable$module == null) {
                this.Liftable$module = new Liftables$Liftable$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Liftable$module;
        }
    }

    @Override
    public Liftables$Liftable$ Liftable() {
        return this.Liftable$module == null ? this.Liftable$lzycompute() : this.Liftable$module;
    }

    private Liftables$Unliftable$ Unliftable$lzycompute() {
        Universe universe = this;
        synchronized (universe) {
            if (this.Unliftable$module == null) {
                this.Unliftable$module = new Liftables$Unliftable$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Unliftable$module;
        }
    }

    @Override
    public Liftables$Unliftable$ Unliftable() {
        return this.Unliftable$module == null ? this.Unliftable$lzycompute() : this.Unliftable$module;
    }

    private Printers$BooleanFlag$ BooleanFlag$lzycompute() {
        Universe universe = this;
        synchronized (universe) {
            if (this.BooleanFlag$module == null) {
                this.BooleanFlag$module = new Printers$BooleanFlag$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.BooleanFlag$module;
        }
    }

    @Override
    public Printers$BooleanFlag$ BooleanFlag() {
        return this.BooleanFlag$module == null ? this.BooleanFlag$lzycompute() : this.BooleanFlag$module;
    }

    @Override
    public String render(Object what, Function1<PrintWriter, Printers.TreePrinter> mkPrinter, Printers.BooleanFlag printTypes, Printers.BooleanFlag printIds, Printers.BooleanFlag printOwners, Printers.BooleanFlag printKinds, Printers.BooleanFlag printMirrors, Printers.BooleanFlag printPositions) {
        return Printers$class.render(this, what, mkPrinter, printTypes, printIds, printOwners, printKinds, printMirrors, printPositions);
    }

    @Override
    public String treeToString(Trees.TreeApi tree) {
        return Printers$class.treeToString(this, tree);
    }

    @Override
    public String show(Object any, Printers.BooleanFlag printTypes, Printers.BooleanFlag printIds, Printers.BooleanFlag printOwners, Printers.BooleanFlag printKinds, Printers.BooleanFlag printMirrors, Printers.BooleanFlag printPositions) {
        return Printers$class.show(this, any, printTypes, printIds, printOwners, printKinds, printMirrors, printPositions);
    }

    @Override
    public String showCode(Trees.TreeApi tree, Printers.BooleanFlag printTypes, Printers.BooleanFlag printIds, Printers.BooleanFlag printOwners, Printers.BooleanFlag printPositions, boolean printRootPkg) {
        return Printers$class.showCode(this, tree, printTypes, printIds, printOwners, printPositions, printRootPkg);
    }

    @Override
    public String showRaw(Object any, Printers.BooleanFlag printTypes, Printers.BooleanFlag printIds, Printers.BooleanFlag printOwners, Printers.BooleanFlag printKinds, Printers.BooleanFlag printMirrors, Printers.BooleanFlag printPositions) {
        return Printers$class.showRaw(this, any, printTypes, printIds, printOwners, printKinds, printMirrors, printPositions);
    }

    @Override
    public String showRaw(Names.NameApi name) {
        return Printers$class.showRaw(this, name);
    }

    @Override
    public String showRaw(Object flags) {
        return Printers$class.showRaw(this, flags);
    }

    @Override
    public String showRaw(Position position) {
        return Printers$class.showRaw(this, position);
    }

    @Override
    public Printers.BooleanFlag render$default$3() {
        return Printers$class.render$default$3(this);
    }

    @Override
    public Printers.BooleanFlag render$default$4() {
        return Printers$class.render$default$4(this);
    }

    @Override
    public Printers.BooleanFlag render$default$5() {
        return Printers$class.render$default$5(this);
    }

    @Override
    public Printers.BooleanFlag render$default$6() {
        return Printers$class.render$default$6(this);
    }

    @Override
    public Printers.BooleanFlag render$default$7() {
        return Printers$class.render$default$7(this);
    }

    @Override
    public Printers.BooleanFlag render$default$8() {
        return Printers$class.render$default$8(this);
    }

    @Override
    public Printers.BooleanFlag show$default$2() {
        return Printers$class.show$default$2(this);
    }

    @Override
    public Printers.BooleanFlag show$default$3() {
        return Printers$class.show$default$3(this);
    }

    @Override
    public Printers.BooleanFlag show$default$4() {
        return Printers$class.show$default$4(this);
    }

    @Override
    public Printers.BooleanFlag show$default$5() {
        return Printers$class.show$default$5(this);
    }

    @Override
    public Printers.BooleanFlag show$default$6() {
        return Printers$class.show$default$6(this);
    }

    @Override
    public Printers.BooleanFlag show$default$7() {
        return Printers$class.show$default$7(this);
    }

    @Override
    public Printers.BooleanFlag showCode$default$2() {
        return Printers$class.showCode$default$2(this);
    }

    @Override
    public Printers.BooleanFlag showCode$default$3() {
        return Printers$class.showCode$default$3(this);
    }

    @Override
    public Printers.BooleanFlag showCode$default$4() {
        return Printers$class.showCode$default$4(this);
    }

    @Override
    public Printers.BooleanFlag showCode$default$5() {
        return Printers$class.showCode$default$5(this);
    }

    @Override
    public boolean showCode$default$6() {
        return Printers$class.showCode$default$6(this);
    }

    @Override
    public Printers.BooleanFlag showRaw$default$2() {
        return Printers$class.showRaw$default$2(this);
    }

    @Override
    public Printers.BooleanFlag showRaw$default$3() {
        return Printers$class.showRaw$default$3(this);
    }

    @Override
    public Printers.BooleanFlag showRaw$default$4() {
        return Printers$class.showRaw$default$4(this);
    }

    @Override
    public Printers.BooleanFlag showRaw$default$5() {
        return Printers$class.showRaw$default$5(this);
    }

    @Override
    public Printers.BooleanFlag showRaw$default$6() {
        return Printers$class.showRaw$default$6(this);
    }

    @Override
    public Printers.BooleanFlag showRaw$default$7() {
        return Printers$class.showRaw$default$7(this);
    }

    private StandardLiftables$stdnme$ scala$reflect$api$StandardLiftables$$stdnme$lzycompute() {
        Universe universe = this;
        synchronized (universe) {
            if (this.scala$reflect$api$StandardLiftables$$stdnme$module == null) {
                this.scala$reflect$api$StandardLiftables$$stdnme$module = new StandardLiftables$stdnme$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$api$StandardLiftables$$stdnme$module;
        }
    }

    @Override
    public final StandardLiftables$stdnme$ scala$reflect$api$StandardLiftables$$stdnme() {
        return this.scala$reflect$api$StandardLiftables$$stdnme$module == null ? this.scala$reflect$api$StandardLiftables$$stdnme$lzycompute() : this.scala$reflect$api$StandardLiftables$$stdnme$module;
    }

    private TypeTags$WeakTypeTag$ WeakTypeTag$lzycompute() {
        Universe universe = this;
        synchronized (universe) {
            if (this.WeakTypeTag$module == null) {
                this.WeakTypeTag$module = new TypeTags$WeakTypeTag$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.WeakTypeTag$module;
        }
    }

    @Override
    public TypeTags$WeakTypeTag$ WeakTypeTag() {
        return this.WeakTypeTag$module == null ? this.WeakTypeTag$lzycompute() : this.WeakTypeTag$module;
    }

    private TypeTags$TypeTag$ TypeTag$lzycompute() {
        Universe universe = this;
        synchronized (universe) {
            if (this.TypeTag$module == null) {
                this.TypeTag$module = new TypeTags$TypeTag$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.TypeTag$module;
        }
    }

    @Override
    public TypeTags$TypeTag$ TypeTag() {
        return this.TypeTag$module == null ? this.TypeTag$lzycompute() : this.TypeTag$module;
    }

    @Override
    public <T> TypeTags.WeakTypeTag<T> weakTypeTag(TypeTags.WeakTypeTag<T> attag) {
        return TypeTags$class.weakTypeTag(this, attag);
    }

    @Override
    public <T> TypeTags.TypeTag<T> typeTag(TypeTags.TypeTag<T> ttag) {
        return TypeTags$class.typeTag(this, ttag);
    }

    @Override
    public <T> Types.TypeApi weakTypeOf(TypeTags.WeakTypeTag<T> attag) {
        return TypeTags$class.weakTypeOf(this, attag);
    }

    @Override
    public <T> Types.TypeApi typeOf(TypeTags.TypeTag<T> ttag) {
        return TypeTags$class.typeOf(this, ttag);
    }

    private Exprs$Expr$ Expr$lzycompute() {
        Universe universe = this;
        synchronized (universe) {
            if (this.Expr$module == null) {
                this.Expr$module = new Exprs$Expr$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Expr$module;
        }
    }

    @Override
    public Exprs$Expr$ Expr() {
        return this.Expr$module == null ? this.Expr$lzycompute() : this.Expr$module;
    }

    @Override
    public Trees.TreeCopierOps treeCopy() {
        return this.treeCopy;
    }

    private Trees.ModifiersApi NoMods$lzycompute() {
        Universe universe = this;
        synchronized (universe) {
            if (!this.bitmap$0) {
                this.NoMods = Trees$class.NoMods(this);
                this.bitmap$0 = true;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.NoMods;
        }
    }

    @Override
    public Trees.ModifiersApi NoMods() {
        return this.bitmap$0 ? this.NoMods : this.NoMods$lzycompute();
    }

    @Override
    public void scala$reflect$api$Trees$_setter_$treeCopy_$eq(Trees.TreeCopierOps x$1) {
        this.treeCopy = x$1;
    }

    @Override
    public void itraverse(Trees.Traverser traverser, Trees.TreeApi tree) {
        Trees$class.itraverse(this, traverser, tree);
    }

    @Override
    public void xtraverse(Trees.Traverser traverser, Trees.TreeApi tree) {
        Trees$class.xtraverse(this, traverser, tree);
    }

    @Override
    public Trees.TreeApi itransform(Trees.Transformer transformer, Trees.TreeApi tree) {
        return Trees$class.itransform(this, transformer, tree);
    }

    @Override
    public Trees.TreeApi xtransform(Trees.Transformer transformer, Trees.TreeApi tree) {
        return Trees$class.xtransform(this, transformer, tree);
    }

    @Override
    public Trees.ModifiersApi Modifiers(Object flags, Names.NameApi privateWithin) {
        return Trees$class.Modifiers(this, flags, privateWithin);
    }

    @Override
    public Trees.ModifiersApi Modifiers(Object flags) {
        return Trees$class.Modifiers(this, flags);
    }

    @Override
    public Names.TermNameApi stringToTermName(String s2) {
        return Names$class.stringToTermName(this, s2);
    }

    @Override
    public Names.TypeNameApi stringToTypeName(String s2) {
        return Names$class.stringToTypeName(this, s2);
    }

    public Universe() {
        Symbols$class.$init$(this);
        Types$class.$init$(this);
        FlagSets$class.$init$(this);
        Scopes$class.$init$(this);
        Names$class.$init$(this);
        Trees$class.$init$(this);
        Constants$class.$init$(this);
        Annotations$class.$init$(this);
        Exprs$class.$init$(this);
        TypeTags$class.$init$(this);
        StandardDefinitions$class.$init$(this);
        StandardNames$class.$init$(this);
        StandardLiftables$class.$init$(this);
        Mirrors$class.$init$(this);
        Printers$class.$init$(this);
        Liftables$class.$init$(this);
        Quasiquotes$class.$init$(this);
        Internals$class.$init$(this);
    }
}

