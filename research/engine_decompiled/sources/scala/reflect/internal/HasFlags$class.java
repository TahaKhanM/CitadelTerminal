/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import java.io.Serializable;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.Flags$;
import scala.reflect.internal.HasFlags;
import scala.runtime.BoxedUnit;

public abstract class HasFlags$class {
    public static boolean hasNoFlags(HasFlags $this, long mask) {
        return !$this.hasFlag(mask);
    }

    public static String flagString(HasFlags $this) {
        return $this.flagString($this.flagMask());
    }

    public static String flagString(HasFlags $this, long mask) {
        return $this.calculateFlagString($this.flags() & mask);
    }

    public static long flagMask(HasFlags $this) {
        return -1L;
    }

    public static String resolveOverloadedFlag(HasFlags $this, long flag) {
        return Flags$.MODULE$.flagToString(flag);
    }

    public static boolean hasAbstractFlag(HasFlags $this) {
        return $this.hasFlag(8L);
    }

    public static boolean hasAccessorFlag(HasFlags $this) {
        return $this.hasFlag(0x8000000L);
    }

    public static boolean hasDefault(HasFlags $this) {
        return $this.hasFlag(0x2000000L) && $this.hasFlag(8256L);
    }

    public static boolean hasJavaEnumFlag(HasFlags $this) {
        return $this.hasFlag(0x1000000000000L);
    }

    public static boolean hasJavaAnnotationFlag(HasFlags $this) {
        return $this.hasFlag(0x2000000000000L);
    }

    public static boolean hasLocalFlag(HasFlags $this) {
        return $this.hasFlag(524288L);
    }

    public static boolean isLocalToThis(HasFlags $this) {
        return $this.hasFlag(524288L);
    }

    public static boolean hasModuleFlag(HasFlags $this) {
        return $this.hasFlag(256L);
    }

    public static boolean hasPackageFlag(HasFlags $this) {
        return $this.hasFlag(16384L);
    }

    public static boolean hasStableFlag(HasFlags $this) {
        return $this.hasFlag(0x400000L);
    }

    public static boolean hasStaticFlag(HasFlags $this) {
        return $this.hasFlag(0x800000L);
    }

    public static boolean isAbstractOverride(HasFlags $this) {
        return $this.hasFlag(262144L);
    }

    public static boolean isAnyOverride(HasFlags $this) {
        return $this.hasFlag(262146L);
    }

    public static boolean isCase(HasFlags $this) {
        return $this.hasFlag(2048L);
    }

    public static boolean isCaseAccessor(HasFlags $this) {
        return $this.hasFlag(0x1000000L);
    }

    public static boolean isDeferred(HasFlags $this) {
        return $this.hasFlag(16L);
    }

    public static boolean isFinal(HasFlags $this) {
        return $this.hasFlag(32L);
    }

    public static boolean isArtifact(HasFlags $this) {
        return $this.hasFlag(0x400000000000L);
    }

    public static boolean isImplicit(HasFlags $this) {
        return $this.hasFlag(512L);
    }

    public static boolean isInterface(HasFlags $this) {
        return $this.hasFlag(128L);
    }

    public static boolean isJavaDefined(HasFlags $this) {
        return $this.hasFlag(0x100000L);
    }

    public static boolean isLabel(HasFlags $this) {
        return $this.hasAllFlags(131136L) && !$this.hasAccessorFlag();
    }

    public static boolean isLazy(HasFlags $this) {
        return $this.hasFlag(0x80000000L);
    }

    public static boolean isLifted(HasFlags $this) {
        return $this.hasFlag(0x400000000L);
    }

    public static boolean isMacro(HasFlags $this) {
        return $this.hasFlag(32768L);
    }

    public static boolean isMutable(HasFlags $this) {
        return $this.hasFlag(4096L);
    }

    public static boolean isOverride(HasFlags $this) {
        return $this.hasFlag(2L);
    }

    public static boolean isParamAccessor(HasFlags $this) {
        return $this.hasFlag(0x20000000L);
    }

    public static boolean isPrivate(HasFlags $this) {
        return $this.hasFlag(4L);
    }

    public static boolean isPackage(HasFlags $this) {
        return $this.hasFlag(16384L);
    }

    public static boolean isPrivateLocal(HasFlags $this) {
        return $this.hasAllFlags(524292L);
    }

    public static boolean isProtected(HasFlags $this) {
        return $this.hasFlag(1L);
    }

    public static boolean isProtectedLocal(HasFlags $this) {
        return $this.hasAllFlags(524289L);
    }

    public static boolean isPublic(HasFlags $this) {
        return $this.hasNoFlags(5L) && !$this.hasAccessBoundary();
    }

    public static boolean isSealed(HasFlags $this) {
        return $this.hasFlag(1024L);
    }

    public static boolean isSpecialized(HasFlags $this) {
        return $this.hasFlag(0x10000000000L);
    }

    public static boolean isSuperAccessor(HasFlags $this) {
        return $this.hasFlag(0x10000000L);
    }

    public static boolean isSynthetic(HasFlags $this) {
        return $this.hasFlag(0x200000L);
    }

    public static boolean isTrait(HasFlags $this) {
        return $this.hasFlag(0x2000000L) && !$this.hasFlag(8192L);
    }

    public static boolean isDeferredOrJavaDefault(HasFlags $this) {
        return $this.hasFlag(0x800000000010L);
    }

    public static boolean isDeferredNotJavaDefault(HasFlags $this) {
        return $this.isDeferred() && !$this.hasFlag(0x800000000000L);
    }

    public static String flagBitsToString(HasFlags $this, long bits2) {
        String string2;
        if (bits2 == 0L) {
            string2 = "";
        } else {
            StringBuilder sb = null;
            for (int i = 0; i <= 62; ++i) {
                Serializable serializable;
                long flag = Flags$.MODULE$.rawFlagPickledOrder()[i];
                if ((bits2 & flag) != 0L) {
                    String s2 = $this.resolveOverloadedFlag(flag);
                    if (s2.length() > 0) {
                        if (sb == null) {
                            sb = new StringBuilder().append(s2);
                            serializable = BoxedUnit.UNIT;
                            continue;
                        }
                        if (sb.length() == 0) {
                            serializable = sb.append(s2);
                            continue;
                        }
                        serializable = sb.append(" ").append(s2);
                        continue;
                    }
                    serializable = BoxedUnit.UNIT;
                    continue;
                }
                serializable = BoxedUnit.UNIT;
            }
            string2 = sb == null ? "" : sb.toString();
        }
        return string2;
    }

    public static String accessString(HasFlags $this) {
        String pw;
        String string2 = pw = $this.hasAccessBoundary() ? $this.privateWithin().toString() : "";
        return string2 != null && string2.equals("") ? ($this.hasAllFlags(524292L) ? "private[this]" : ($this.hasAllFlags(524289L) ? "protected[this]" : ($this.hasFlag(4L) ? "private" : ($this.hasFlag(1L) ? "protected" : "")))) : ($this.hasFlag(1L) ? new StringBuilder().append((Object)"protected[").append((Object)pw).append((Object)"]").toString() : new StringBuilder().append((Object)"private[").append((Object)pw).append((Object)"]").toString());
    }

    public static String calculateFlagString(HasFlags $this, long basis) {
        String string2;
        String access = $this.accessString();
        String nonAccess = $this.flagBitsToString(basis & (long)(~524293));
        String string3 = access;
        if (string3 != null && string3.equals("")) {
            string2 = nonAccess;
        } else {
            String string4 = nonAccess;
            string2 = string4 != null && string4.equals("") ? access : new StringBuilder().append((Object)nonAccess).append((Object)" ").append((Object)access).toString();
        }
        return string2;
    }

    public static boolean isParameter(HasFlags $this) {
        return $this.hasFlag(8192L);
    }

    public static void $init$(HasFlags $this) {
    }
}

