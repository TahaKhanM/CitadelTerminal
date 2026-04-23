/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import java.io.PrintWriter;
import java.io.StringWriter;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.reflect.api.Names;
import scala.reflect.api.Position;
import scala.reflect.api.Printers;
import scala.reflect.api.Trees;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;

public abstract class Printers$class {
    public static String render(Universe $this, Object what, Function1 mkPrinter, Printers.BooleanFlag printTypes, Printers.BooleanFlag printIds, Printers.BooleanFlag printOwners, Printers.BooleanFlag printKinds, Printers.BooleanFlag printMirrors, Printers.BooleanFlag printPositions) {
        StringWriter buffer = new StringWriter();
        PrintWriter writer = new PrintWriter(buffer);
        Printers.TreePrinter printer = (Printers.TreePrinter)mkPrinter.apply(writer);
        Serializable serializable = new Serializable($this, printer){
            public static final long serialVersionUID = 0L;
            public final Printers.TreePrinter printer$1;

            public final Printers.TreePrinter apply(boolean printTypes) {
                return printTypes ? this.printer$1.withTypes() : this.printer$1.withoutTypes();
            }
            {
                this.printer$1 = printer$1;
            }
        };
        Option<Object> option = printTypes.value();
        Option option2 = !option.isEmpty() ? new Some<Printers.TreePrinter>(BoxesRunTime.unboxToBoolean(option.get()) ? serializable.printer$1.withTypes() : serializable.printer$1.withoutTypes()) : None$.MODULE$;
        Serializable serializable2 = new Serializable($this, printer){
            public static final long serialVersionUID = 0L;
            public final Printers.TreePrinter printer$1;

            public final Printers.TreePrinter apply(boolean printIds) {
                return printIds ? this.printer$1.withIds() : this.printer$1.withoutIds();
            }
            {
                this.printer$1 = printer$1;
            }
        };
        Option<Object> option3 = printIds.value();
        Option option4 = !option3.isEmpty() ? new Some<Printers.TreePrinter>(BoxesRunTime.unboxToBoolean(option3.get()) ? serializable2.printer$1.withIds() : serializable2.printer$1.withoutIds()) : None$.MODULE$;
        Serializable serializable3 = new Serializable($this, printer){
            public static final long serialVersionUID = 0L;
            public final Printers.TreePrinter printer$1;

            public final Printers.TreePrinter apply(boolean printOwners) {
                return printOwners ? this.printer$1.withOwners() : this.printer$1.withoutOwners();
            }
            {
                this.printer$1 = printer$1;
            }
        };
        Option<Object> option5 = printOwners.value();
        Option option6 = !option5.isEmpty() ? new Some<Printers.TreePrinter>(BoxesRunTime.unboxToBoolean(option5.get()) ? serializable3.printer$1.withOwners() : serializable3.printer$1.withoutOwners()) : None$.MODULE$;
        Serializable serializable4 = new Serializable($this, printer){
            public static final long serialVersionUID = 0L;
            public final Printers.TreePrinter printer$1;

            public final Printers.TreePrinter apply(boolean printKinds) {
                return printKinds ? this.printer$1.withKinds() : this.printer$1.withoutKinds();
            }
            {
                this.printer$1 = printer$1;
            }
        };
        Option<Object> option7 = printKinds.value();
        Option option8 = !option7.isEmpty() ? new Some<Printers.TreePrinter>(BoxesRunTime.unboxToBoolean(option7.get()) ? serializable4.printer$1.withKinds() : serializable4.printer$1.withoutKinds()) : None$.MODULE$;
        Serializable serializable5 = new Serializable($this, printer){
            public static final long serialVersionUID = 0L;
            public final Printers.TreePrinter printer$1;

            public final Printers.TreePrinter apply(boolean printMirrors) {
                return printMirrors ? this.printer$1.withMirrors() : this.printer$1.withoutMirrors();
            }
            {
                this.printer$1 = printer$1;
            }
        };
        Option<Object> option9 = printMirrors.value();
        Option option10 = !option9.isEmpty() ? new Some<Printers.TreePrinter>(BoxesRunTime.unboxToBoolean(option9.get()) ? serializable5.printer$1.withMirrors() : serializable5.printer$1.withoutMirrors()) : None$.MODULE$;
        Serializable serializable6 = new Serializable($this, printer){
            public static final long serialVersionUID = 0L;
            public final Printers.TreePrinter printer$1;

            public final Printers.TreePrinter apply(boolean printPositions) {
                return printPositions ? this.printer$1.withPositions() : this.printer$1.withoutPositions();
            }
            {
                this.printer$1 = printer$1;
            }
        };
        Option<Object> option11 = printPositions.value();
        Option option12 = !option11.isEmpty() ? new Some<Printers.TreePrinter>(BoxesRunTime.unboxToBoolean(option11.get()) ? serializable6.printer$1.withPositions() : serializable6.printer$1.withoutPositions()) : None$.MODULE$;
        printer.print(Predef$.MODULE$.genericWrapArray(new Object[]{what}));
        writer.flush();
        return buffer.toString();
    }

    public static Printers.BooleanFlag render$default$3(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag render$default$4(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag render$default$5(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag render$default$6(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag render$default$7(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag render$default$8(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static String treeToString(Universe $this, Trees.TreeApi tree) {
        return $this.show(tree, $this.show$default$2(), $this.show$default$3(), $this.show$default$4(), $this.show$default$5(), $this.show$default$6(), $this.show$default$7());
    }

    public static String show(Universe $this, Object any, Printers.BooleanFlag printTypes, Printers.BooleanFlag printIds, Printers.BooleanFlag printOwners, Printers.BooleanFlag printKinds, Printers.BooleanFlag printMirrors, Printers.BooleanFlag printPositions) {
        return $this.render(any, (Function1<PrintWriter, Printers.TreePrinter>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Universe $outer;

            public final Printers.TreePrinter apply(PrintWriter x$1) {
                return this.$outer.newTreePrinter(x$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }), printTypes, printIds, printOwners, printKinds, printMirrors, printPositions);
    }

    public static String showCode(Universe $this, Trees.TreeApi tree, Printers.BooleanFlag printTypes, Printers.BooleanFlag printIds, Printers.BooleanFlag printOwners, Printers.BooleanFlag printPositions, boolean printRootPkg) {
        return $this.render(tree, (Function1<PrintWriter, Printers.TreePrinter>)((Object)new Serializable($this, tree, printRootPkg){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Universe $outer;
            private final Trees.TreeApi tree$1;
            private final boolean printRootPkg$1;

            public final Printers.TreePrinter apply(PrintWriter x$2) {
                return this.$outer.newCodePrinter(x$2, this.tree$1, this.printRootPkg$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tree$1 = tree$1;
                this.printRootPkg$1 = printRootPkg$1;
            }
        }), printTypes, printIds, printOwners, $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$), $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$), printPositions);
    }

    public static String showRaw(Universe $this, Object any, Printers.BooleanFlag printTypes, Printers.BooleanFlag printIds, Printers.BooleanFlag printOwners, Printers.BooleanFlag printKinds, Printers.BooleanFlag printMirrors, Printers.BooleanFlag printPositions) {
        return $this.render(any, (Function1<PrintWriter, Printers.TreePrinter>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Universe $outer;

            public final Printers.TreePrinter apply(PrintWriter x$3) {
                return this.$outer.newRawTreePrinter(x$3);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }), printTypes, printIds, printOwners, printKinds, printMirrors, printPositions);
    }

    public static String showRaw(Universe $this, Names.NameApi name) {
        return name.toString();
    }

    public static Printers.BooleanFlag show$default$2(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag show$default$3(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag show$default$4(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag show$default$5(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag show$default$6(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag show$default$7(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag showCode$default$2(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag showCode$default$3(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag showCode$default$4(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag showCode$default$5(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static boolean showCode$default$6(Universe $this) {
        return false;
    }

    public static String showRaw(Universe $this, Object flags) {
        return flags.toString();
    }

    public static String showRaw(Universe $this, Position position) {
        return position.toString();
    }

    public static Printers.BooleanFlag showRaw$default$2(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag showRaw$default$3(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag showRaw$default$4(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag showRaw$default$5(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag showRaw$default$6(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static Printers.BooleanFlag showRaw$default$7(Universe $this) {
        return $this.BooleanFlag().optionToBooleanFlag(None$.MODULE$);
    }

    public static void $init$(Universe $this) {
    }
}

