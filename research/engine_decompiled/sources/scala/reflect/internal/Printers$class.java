/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import java.io.OutputStream;
import java.io.PrintWriter;
import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.Flags$;
import scala.reflect.internal.Names;
import scala.reflect.internal.Printers;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.util.Position;
import scala.runtime.BoxedUnit;
import scala.runtime.RichInt$;

public abstract class Printers$class {
    /*
     * Enabled aggressive block sorting
     */
    public static String quotedName(SymbolTable $this, Names.Name name, boolean decode) {
        String string2;
        String s2 = decode ? name.decode() : name.toString();
        Names.TermName term = name.toTermName();
        if ($this.nme().keywords().apply(term)) {
            Names.TermName termName = term;
            Names.TermName termName2 = $this.nme().USCOREkw();
            if (termName == null ? termName2 != null : !termName.equals(termName2)) {
                Predef$ predef$ = Predef$.MODULE$;
                string2 = new StringOps("`%s`").format(Predef$.MODULE$.genericWrapArray(new Object[]{s2}));
                return string2;
            }
        }
        string2 = s2;
        return string2;
    }

    public static String quotedName(SymbolTable $this, Names.Name name) {
        return $this.quotedName(name, false);
    }

    public static String quotedName(SymbolTable $this, String name) {
        return $this.quotedName($this.newTermName(name), false);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String symNameInternal(SymbolTable $this, Trees.Tree tree, Names.Name name, boolean decoded) {
        String string2;
        Symbols.Symbol sym;
        block6: {
            block5: {
                sym = tree.symbol();
                if (sym == null) break block5;
                Symbols.Symbol symbol = sym;
                Symbols.NoSymbol noSymbol = $this.NoSymbol();
                if (symbol != null ? !symbol.equals(noSymbol) : noSymbol != null) break block6;
            }
            string2 = Printers$class.qname$1($this, name, decoded);
            return string2;
        }
        if (sym.isErroneous()) {
            string2 = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"<", ": error>"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{Printers$class.qname$1($this, name, decoded)}));
            return string2;
        }
        if (sym.isMixinConstructor()) {
            string2 = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"/*", "*/", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{Printers$class.qowner$1($this, decoded, sym), Printers$class.qsymbol$1($this, sym)}));
            return string2;
        }
        string2 = Printers$class.qsymbol$1($this, sym);
        return string2;
    }

    public static String decodedSymName(SymbolTable $this, Trees.Tree tree, Names.Name name) {
        return Printers$class.symNameInternal($this, tree, name, true);
    }

    public static String symName(SymbolTable $this, Trees.Tree tree, Names.Name name) {
        return Printers$class.symNameInternal($this, tree, name, false);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String backquotedPath(SymbolTable $this, Trees.Tree t) {
        boolean bl = false;
        Trees.Select select = null;
        if (t instanceof Trees.Select) {
            bl = true;
            select = (Trees.Select)t;
            if (select.name().isTermName()) {
                return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", ".", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{$this.backquotedPath(select.qualifier()), $this.symName(t, select.name())}));
            }
        }
        if (bl && select.name().isTypeName()) {
            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", "#", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{$this.backquotedPath(select.qualifier()), $this.symName(t, select.name())}));
        }
        if (!(t instanceof Trees.Ident)) return t.toString();
        Trees.Ident ident = (Trees.Ident)t;
        return $this.symName(t, ident.name());
    }

    public static void xprintTree(SymbolTable $this, Printers.TreePrinter treePrinter, Trees.Tree tree) {
        treePrinter.print(Predef$.MODULE$.genericWrapArray(new Object[]{new StringBuilder().append((Object)tree.productPrefix()).append((Object)tree.productIterator().mkString("(", ", ", ")")).toString()}));
    }

    public static Printers.TreePrinter newCodePrinter(SymbolTable $this, PrintWriter writer, Trees.Tree tree, boolean printRootPkg) {
        return new Printers.CodePrinter($this, writer, printRootPkg);
    }

    public static Printers.TreePrinter newTreePrinter(SymbolTable $this, PrintWriter writer) {
        return new Printers.TreePrinter($this, writer);
    }

    public static Printers.TreePrinter newTreePrinter(SymbolTable $this, OutputStream stream) {
        return $this.newTreePrinter(new PrintWriter(stream));
    }

    public static Printers.TreePrinter newTreePrinter(SymbolTable $this) {
        return $this.newTreePrinter(new PrintWriter($this.ConsoleWriter()));
    }

    public static Printers.RawTreePrinter newRawTreePrinter(SymbolTable $this, PrintWriter writer) {
        return new Printers.RawTreePrinter($this, writer);
    }

    public static String show(SymbolTable $this, Names.Name name) {
        String string2;
        Names.Name name2 = $this.tpnme().WILDCARD();
        if (!(name2 != null ? !name2.equals(name) : name != null)) {
            string2 = "typeNames.WILDCARD";
        } else {
            Names.Name name3 = $this.tpnme().EMPTY();
            if (!(name3 != null ? !name3.equals(name) : name != null)) {
                string2 = "typeNames.EMPTY";
            } else {
                Names.Name name4 = $this.tpnme().ERROR();
                if (!(name4 != null ? !name4.equals(name) : name != null)) {
                    string2 = "typeNames.ERROR";
                } else {
                    Names.Name name5 = $this.tpnme().PACKAGE();
                    if (!(name5 != null ? !name5.equals(name) : name != null)) {
                        string2 = "typeNames.PACKAGE";
                    } else {
                        Names.TypeName typeName = $this.tpnme().WILDCARD_STAR();
                        if (!(typeName != null ? !typeName.equals(name) : name != null)) {
                            string2 = "typeNames.WILDCARD_STAR";
                        } else {
                            Names.Name name6 = $this.nme().WILDCARD();
                            if (!(name6 != null ? !name6.equals(name) : name != null)) {
                                string2 = "termNames.WILDCARD";
                            } else {
                                Names.Name name7 = $this.nme().EMPTY();
                                if (!(name7 != null ? !name7.equals(name) : name != null)) {
                                    string2 = "termNames.EMPTY";
                                } else {
                                    Names.Name name8 = $this.nme().ERROR();
                                    if (!(name8 != null ? !name8.equals(name) : name != null)) {
                                        string2 = "termNames.ERROR";
                                    } else {
                                        Names.Name name9 = $this.nme().PACKAGE();
                                        if (!(name9 != null ? !name9.equals(name) : name != null)) {
                                            string2 = "termNames.PACKAGE";
                                        } else {
                                            Names.TermName termName = $this.nme().CONSTRUCTOR();
                                            if (!(termName != null ? !termName.equals(name) : name != null)) {
                                                string2 = "termNames.CONSTRUCTOR";
                                            } else {
                                                Names.TermName termName2 = $this.nme().ROOTPKG();
                                                if (!(termName2 != null ? !termName2.equals(name) : name != null)) {
                                                    string2 = "termNames.ROOTPKG";
                                                } else {
                                                    String prefix = name.isTermName() ? "TermName(\"" : "TypeName(\"";
                                                    string2 = new StringBuilder().append((Object)prefix).append((Object)name.toString()).append((Object)"\")").toString();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return string2;
    }

    public static String show(SymbolTable $this, long flags) {
        String string2;
        if (flags == $this.NoFlags()) {
            string2 = $this.nme().NoFlags().toString();
        } else {
            ListBuffer s_flags = new ListBuffer();
            Predef$ predef$ = Predef$.MODULE$;
            RichInt$.MODULE$.to$extension0(0, 63).withFilter(new Serializable($this, flags){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ SymbolTable $outer;
                public final long flags$1;

                public final boolean apply(int i) {
                    return Printers$class.hasFlag$1(this.$outer, this.flags$1, 1L << i);
                }

                public boolean apply$mcZI$sp(int i) {
                    return Printers$class.hasFlag$1(this.$outer, this.flags$1, 1L << i);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.flags$1 = flags$1;
                }
            }).foreach(new Serializable($this, s_flags){
                public static final long serialVersionUID = 0L;
                private final ListBuffer s_flags$1;

                public final ListBuffer<String> apply(int i) {
                    return this.s_flags$1.$plus$eq(Flags$.MODULE$.flagToString(1L << i).replace("<", "").replace(">", "").toUpperCase());
                }
                {
                    this.s_flags$1 = s_flags$1;
                }
            });
            string2 = s_flags.mkString(" | ");
        }
        return string2;
    }

    public static String show(SymbolTable $this, Position position) {
        return position.show();
    }

    public static String showDecl(SymbolTable $this, Symbols.Symbol sym) {
        Object object = $this.isCompilerUniverse() ? BoxedUnit.UNIT : $this.definitions().fullyInitializeSymbol(sym);
        return sym.defString();
    }

    private static final String qname$1(SymbolTable $this, Names.Name name$1, boolean decoded$1) {
        return $this.quotedName($this.AnyNameOps(name$1).dropLocal(), decoded$1);
    }

    private static final String qowner$1(SymbolTable $this, boolean decoded$1, Symbols.Symbol sym$1) {
        return $this.quotedName($this.AnyNameOps(sym$1.owner().name()).dropLocal(), decoded$1);
    }

    private static final String qsymbol$1(SymbolTable $this, Symbols.Symbol sym$1) {
        return $this.quotedName(sym$1.nameString());
    }

    public static final boolean hasFlag$1(SymbolTable $this, long left, long right) {
        return (left & right) != 0L;
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$internal$Printers$_setter_$scala$reflect$internal$Printers$$footnoteIndex_$eq(new Printers.FootnoteIndex($this));
    }
}

