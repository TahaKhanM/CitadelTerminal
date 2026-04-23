/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.collection.generic.GenericClassTagCompanion;
import scala.collection.generic.GenericClassTagTraversableTemplate;
import scala.collection.mutable.Builder;
import scala.reflect.ClassTag;

public abstract class GenericClassTagTraversableTemplate$class {
    public static Builder genericClassTagBuilder(GenericClassTagTraversableTemplate $this, ClassTag tag) {
        return $this.classTagCompanion().newBuilder(tag);
    }

    public static GenericClassTagCompanion classManifestCompanion(GenericClassTagTraversableTemplate $this) {
        return $this.classTagCompanion();
    }

    public static Builder genericClassManifestBuilder(GenericClassTagTraversableTemplate $this, ClassTag manifest) {
        return $this.genericClassTagBuilder(manifest);
    }

    public static void $init$(GenericClassTagTraversableTemplate $this) {
    }
}

