/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.reflect.ClassTag;
import scala.reflect.internal.Positions;
import scala.reflect.internal.Required;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.Position;
import scala.reflect.macros.Attachments;
import scala.runtime.BoxesRunTime;

public abstract class Trees$CannotHaveAttrs$class {
    public static boolean canHaveAttrs(Trees.CannotHaveAttrs $this) {
        return false;
    }

    public static Trees.CannotHaveAttrs setPos(Trees.CannotHaveAttrs $this, Position pos) {
        Trees$CannotHaveAttrs$class.requireLegal($this, pos, ((Positions)((Object)$this.scala$reflect$internal$Trees$CannotHaveAttrs$$$outer())).NoPosition(), "pos");
        return $this;
    }

    public static void pos_$eq(Trees.CannotHaveAttrs $this, Position pos) {
        $this.setPos(pos);
    }

    public static Trees.CannotHaveAttrs setType(Trees.CannotHaveAttrs $this, Types.Type t) {
        Trees$CannotHaveAttrs$class.requireLegal($this, t, ((Types)((Object)$this.scala$reflect$internal$Trees$CannotHaveAttrs$$$outer())).NoType(), "tpe");
        return $this;
    }

    public static void tpe_$eq(Trees.CannotHaveAttrs $this, Types.Type t) {
        $this.setType(t);
    }

    public static Trees.CannotHaveAttrs setAttachments(Trees.CannotHaveAttrs $this, Attachments attachments) {
        return Trees$CannotHaveAttrs$class.attachmentWarning($this);
    }

    public static Trees.CannotHaveAttrs updateAttachment(Trees.CannotHaveAttrs $this, Object attachment, ClassTag evidence$1) {
        return Trees$CannotHaveAttrs$class.attachmentWarning($this);
    }

    public static Trees.CannotHaveAttrs removeAttachment(Trees.CannotHaveAttrs $this, ClassTag evidence$2) {
        return Trees$CannotHaveAttrs$class.attachmentWarning($this);
    }

    private static Trees.CannotHaveAttrs attachmentWarning(Trees.CannotHaveAttrs $this) {
        ((SymbolTable)$this.scala$reflect$internal$Trees$CannotHaveAttrs$$$outer()).devWarning((Function0<String>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Trees.CannotHaveAttrs $outer;

            public final String apply() {
                return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Attempt to mutate attachments on ", " ignored"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.$outer.scala$reflect$internal$Trees$CannotHaveAttrs$$$outer()}));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
        return $this;
    }

    private static void requireLegal(Trees.CannotHaveAttrs $this, Object value, Object allowed, String what) {
        if (!(value != allowed ? (value != null ? (!(value instanceof Number) ? (!(value instanceof Character) ? value.equals(allowed) : BoxesRunTime.equalsCharObject((Character)value, allowed)) : BoxesRunTime.equalsNumObject((Number)value, allowed)) : false) : true)) {
            ((SymbolTable)$this.scala$reflect$internal$Trees$CannotHaveAttrs$$$outer()).log((Function0<Object>)((Object)new Serializable($this, allowed, what){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Trees.CannotHaveAttrs $outer;
                private final Object allowed$1;
                private final String what$1;

                public final String apply() {
                    return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"can't set ", " for ", " to value other than ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.what$1, this.$outer.scala$reflect$internal$Trees$CannotHaveAttrs$$$outer(), this.allowed$1}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.allowed$1 = allowed$1;
                    this.what$1 = what$1;
                }
            }));
            MutableSettings.SettingValue settingValue = ((Required)((Object)$this.scala$reflect$internal$Trees$CannotHaveAttrs$$$outer())).settings().debug();
            MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
            if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
                MutableSettings.SettingValue settingValue2 = ((Required)((Object)$this.scala$reflect$internal$Trees$CannotHaveAttrs$$$outer())).settings().developer();
                MutableSettings$ mutableSettings$2 = MutableSettings$.MODULE$;
                if (BoxesRunTime.unboxToBoolean(settingValue2.value())) {
                    new Throwable().printStackTrace();
                }
            }
        }
    }

    public static void $init$(Trees.CannotHaveAttrs $this) {
        $this.scala$reflect$internal$Trees$CannotHaveAttrs$$super$setPos(((Positions)((Object)$this.scala$reflect$internal$Trees$CannotHaveAttrs$$$outer())).NoPosition());
        $this.scala$reflect$internal$Trees$CannotHaveAttrs$$super$setType(((Types)((Object)$this.scala$reflect$internal$Trees$CannotHaveAttrs$$$outer())).NoType());
    }
}

