/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.tpe.TypeMaps;

public abstract class TypeMaps$AnnotationFilter$class {
    public static AnnotationInfos.AnnotationInfo mapOver(TypeMaps.AnnotationFilter $this, AnnotationInfos.AnnotationInfo annot) {
        return $this.keepAnnotation(annot) ? $this.scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$super$mapOver(annot) : ((AnnotationInfos)((Object)$this.scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$$outer())).UnmappableAnnotation();
    }

    public static void $init$(TypeMaps.AnnotationFilter $this) {
    }
}

