/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.internal.Types;

public abstract class Types$UntouchableTypeVar$class {
    public static boolean untouchable(Types.UntouchableTypeVar $this) {
        return true;
    }

    public static boolean isGround(Types.UntouchableTypeVar $this) {
        return true;
    }

    public static boolean registerTypeEquality(Types.UntouchableTypeVar $this, Types.Type tp, boolean typeVarLHS) {
        Types.TypeVar typeVar;
        boolean bl = tp instanceof Types.TypeVar && !(typeVar = (Types.TypeVar)tp).untouchable() ? typeVar.registerTypeEquality((Types.Type)((Object)$this), !typeVarLHS) : $this.scala$reflect$internal$Types$UntouchableTypeVar$$super$registerTypeEquality(tp, typeVarLHS);
        return bl;
    }

    public static boolean registerBound(Types.UntouchableTypeVar $this, Types.Type tp, boolean isLowerBound, boolean isNumericBound) {
        Types.TypeVar typeVar;
        boolean bl = tp instanceof Types.TypeVar && !(typeVar = (Types.TypeVar)tp).untouchable() ? typeVar.registerBound((Types.Type)((Object)$this), !isLowerBound, isNumericBound) : $this.scala$reflect$internal$Types$UntouchableTypeVar$$super$registerBound(tp, isLowerBound, isNumericBound);
        return bl;
    }

    public static boolean registerBound$default$3(Types.UntouchableTypeVar $this) {
        return false;
    }

    public static void $init$(Types.UntouchableTypeVar $this) {
    }
}

