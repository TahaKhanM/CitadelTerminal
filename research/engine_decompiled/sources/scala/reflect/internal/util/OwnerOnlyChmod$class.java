/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import java.io.File;
import java.io.FileOutputStream;
import scala.Array$;
import scala.collection.immutable.Nil$;
import scala.reflect.ClassTag$;
import scala.reflect.internal.util.OwnerOnlyChmod;

public abstract class OwnerOnlyChmod$class {
    /*
     * WARNING - void declaration
     */
    public static final void chmodAndWrite(OwnerOnlyChmod $this, File file, byte[] contents) {
        file.delete();
        FileOutputStream fos = new FileOutputStream(file);
        fos.close();
        $this.chmod(file);
        FileOutputStream fos2 = new FileOutputStream(file);
        try {
            fos2.write(contents);
        }
        catch (Throwable throwable) {
            void var4_4;
            var4_4.close();
            throw throwable;
        }
        fos2.close();
    }

    public static final void chmodOrCreateEmpty(OwnerOnlyChmod $this, File file) {
        if (file.exists()) {
            $this.chmod(file);
        } else {
            $this.chmodAndWrite(file, (byte[])Array$.MODULE$.apply(Nil$.MODULE$, ClassTag$.MODULE$.Byte()));
        }
    }

    public static void $init$(OwnerOnlyChmod $this) {
    }
}

