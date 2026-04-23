/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Definitions;
import scala.reflect.internal.tpe.TypeMaps;

public abstract class TypeMaps$KeepOnlyTypeConstraints$class {
    public static boolean keepAnnotation(TypeMaps.KeepOnlyTypeConstraints $this, AnnotationInfos.AnnotationInfo annot) {
        return annot.matches(((Definitions)((Object)$this.scala$reflect$internal$tpe$TypeMaps$KeepOnlyTypeConstraints$$$outer())).definitions().TypeConstraintClass());
    }

    public static void $init$(TypeMaps.KeepOnlyTypeConstraints $this) {
    }
}

