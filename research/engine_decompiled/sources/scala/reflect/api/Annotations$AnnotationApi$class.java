/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.reflect.api.Annotations;
import scala.reflect.api.Trees;

public abstract class Annotations$AnnotationApi$class {
    public static Trees.TreeApi tree(Annotations.AnnotationApi $this) {
        return $this.scala$reflect$api$Annotations$AnnotationApi$$$outer().annotationToTree($this);
    }

    public static void $init$(Annotations.AnnotationApi $this) {
    }
}

