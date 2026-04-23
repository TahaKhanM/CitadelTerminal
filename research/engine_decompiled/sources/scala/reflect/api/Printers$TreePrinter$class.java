/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.reflect.api.Printers;

public abstract class Printers$TreePrinter$class {
    public static Printers.TreePrinter withTypes(Printers.TreePrinter $this) {
        $this.printTypes_$eq(true);
        return $this;
    }

    public static Printers.TreePrinter withoutTypes(Printers.TreePrinter $this) {
        $this.printTypes_$eq(false);
        return $this;
    }

    public static Printers.TreePrinter withIds(Printers.TreePrinter $this) {
        $this.printIds_$eq(true);
        return $this;
    }

    public static Printers.TreePrinter withoutIds(Printers.TreePrinter $this) {
        $this.printIds_$eq(false);
        return $this;
    }

    public static Printers.TreePrinter withOwners(Printers.TreePrinter $this) {
        $this.printOwners_$eq(true);
        return $this;
    }

    public static Printers.TreePrinter withoutOwners(Printers.TreePrinter $this) {
        $this.printOwners_$eq(false);
        return $this;
    }

    public static Printers.TreePrinter withKinds(Printers.TreePrinter $this) {
        $this.printKinds_$eq(true);
        return $this;
    }

    public static Printers.TreePrinter withoutKinds(Printers.TreePrinter $this) {
        $this.printKinds_$eq(false);
        return $this;
    }

    public static Printers.TreePrinter withMirrors(Printers.TreePrinter $this) {
        $this.printMirrors_$eq(true);
        return $this;
    }

    public static Printers.TreePrinter withoutMirrors(Printers.TreePrinter $this) {
        $this.printMirrors_$eq(false);
        return $this;
    }

    public static Printers.TreePrinter withPositions(Printers.TreePrinter $this) {
        $this.printPositions_$eq(true);
        return $this;
    }

    public static Printers.TreePrinter withoutPositions(Printers.TreePrinter $this) {
        $this.printPositions_$eq(false);
        return $this;
    }

    public static void $init$(Printers.TreePrinter $this) {
        $this.printTypes_$eq(false);
        $this.printIds_$eq(false);
        $this.printOwners_$eq(false);
        $this.printKinds_$eq(false);
        $this.printMirrors_$eq(false);
        $this.printPositions_$eq(false);
    }
}

