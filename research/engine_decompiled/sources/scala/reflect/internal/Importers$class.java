/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Predef$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.api.Internals;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.reflect.internal.Importers;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.Position;

public abstract class Importers$class {
    public static Internals.Importer mkImporter(SymbolTable $this, Universe from0) {
        Internals.Importer importer;
        if ($this == from0) {
            importer = new Internals.Importer($this, from0){
                private final Universe from;
                private final Internals.Importer reverse;

                public Universe from() {
                    return this.from;
                }

                public Internals.Importer reverse() {
                    return this.reverse;
                }

                public Symbols.Symbol importSymbol(Symbols.SymbolApi their) {
                    return (Symbols.Symbol)their;
                }

                public Types.Type importType(Types.TypeApi their) {
                    return (Types.Type)their;
                }

                public Trees.Tree importTree(Trees.TreeApi their) {
                    return (Trees.Tree)their;
                }

                public Position importPosition(scala.reflect.api.Position their) {
                    return (Position)their;
                }
                {
                    this.from = from0$1;
                    this.reverse = this;
                }
            };
        } else {
            boolean bl = from0 instanceof SymbolTable;
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)"`from` should be an instance of scala.reflect.internal.SymbolTable").toString());
            }
            importer = new Importers.StandardImporter($this, from0){
                private final SymbolTable from;

                public SymbolTable from() {
                    return this.from;
                }
                {
                    this.from = (SymbolTable)from0$1;
                }
            };
        }
        return importer;
    }

    public static void $init$(SymbolTable $this) {
    }
}

