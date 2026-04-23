/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.ClassTag;
import scala.reflect.internal.Positions;
import scala.reflect.internal.StdAttachments;
import scala.reflect.internal.util.Position;
import scala.reflect.macros.Attachments;

public abstract class StdAttachments$Attachable$class {
    public static Attachments attachments(StdAttachments.Attachable $this) {
        return $this.rawatt();
    }

    public static StdAttachments.Attachable setAttachments(StdAttachments.Attachable $this, Attachments attachments) {
        $this.rawatt_$eq(attachments);
        return $this;
    }

    public static StdAttachments.Attachable updateAttachment(StdAttachments.Attachable $this, Object attachment, ClassTag evidence$1) {
        $this.rawatt_$eq($this.rawatt().update(attachment, evidence$1));
        return $this;
    }

    public static StdAttachments.Attachable removeAttachment(StdAttachments.Attachable $this, ClassTag evidence$2) {
        $this.rawatt_$eq($this.rawatt().remove(evidence$2));
        return $this;
    }

    public static boolean hasAttachment(StdAttachments.Attachable $this, ClassTag evidence$3) {
        return $this.rawatt().contains(evidence$3);
    }

    public static Position pos(StdAttachments.Attachable $this) {
        return (Position)$this.rawatt().pos();
    }

    public static void pos_$eq(StdAttachments.Attachable $this, Position pos) {
        $this.rawatt_$eq($this.rawatt().withPos(pos));
    }

    public static StdAttachments.Attachable setPos(StdAttachments.Attachable $this, Position newpos) {
        $this.pos_$eq(newpos);
        return $this;
    }

    public static void $init$(StdAttachments.Attachable $this) {
        $this.rawatt_$eq(((Positions)((Object)$this.scala$reflect$internal$StdAttachments$Attachable$$$outer())).NoPosition());
    }
}

