/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.collection.mutable.StringBuilder;
import scala.compat.Platform$;
import scala.io.Codec$;
import scala.math.package$;
import scala.reflect.ClassTag$;
import scala.reflect.internal.Names;
import scala.runtime.BoxesRunTime;

public abstract class Names$class {
    public static boolean synchronizeNames(Names $this) {
        return false;
    }

    public static int scala$reflect$internal$Names$$hashValue(Names $this, char[] cs, int offset, int len) {
        return len > 0 ? len * 68921 + cs[offset] * 1681 + cs[offset + len - 1] * 41 + cs[offset + (len >> 1)] : 0;
    }

    private static boolean equals(Names $this, int index, char[] cs, int offset, int len) {
        int i;
        for (i = 0; i < len && $this.chrs()[index + i] == cs[offset + i]; ++i) {
        }
        return i == len;
    }

    private static void enterChars(Names $this, char[] cs, int offset, int len) {
        for (int i = 0; i < len; ++i) {
            if ($this.scala$reflect$internal$Names$$nc() + i == $this.chrs().length) {
                char[] newchrs = new char[$this.chrs().length * 2];
                int n = $this.chrs().length;
                char[] cArray = $this.chrs();
                Platform$ platform$ = Platform$.MODULE$;
                System.arraycopy(cArray, 0, newchrs, 0, n);
                $this.chrs_$eq(newchrs);
            }
            $this.chrs()[$this.scala$reflect$internal$Names$$nc() + i] = cs[offset + i];
        }
        if (len == 0) {
            $this.scala$reflect$internal$Names$$nc_$eq($this.scala$reflect$internal$Names$$nc() + 1);
        } else {
            $this.scala$reflect$internal$Names$$nc_$eq($this.scala$reflect$internal$Names$$nc() + len);
        }
    }

    public static final Names.TermName newTermName(Names $this, char[] cs, int offset, int len) {
        return $this.newTermName(cs, offset, len, null);
    }

    public static final Names.TermName newTermName(Names $this, char[] cs) {
        return $this.newTermName(cs, 0, cs.length);
    }

    public static final Names.TypeName newTypeName(Names $this, char[] cs) {
        return $this.newTypeName(cs, 0, cs.length);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static final Names.TermName newTermName(Names $this, char[] cs, int offset, int len0, String cachedString) {
        Names.TermName termName;
        if (!$this.synchronizeNames()) {
            termName = Names$class.body$1($this, cs, offset, len0, cachedString);
            return termName;
        }
        Object object = $this.scala$reflect$internal$Names$$nameLock();
        synchronized (object) {
            Names.TermName termName2 = Names$class.body$1($this, cs, offset, len0, cachedString);
            // MONITOREXIT @DISABLED, blocks:[0, 1] lbl8 : MonitorExitStatement: MONITOREXIT : object
            termName = termName2;
            return termName;
        }
    }

    public static final Names.TypeName newTypeName(Names $this, char[] cs, int offset, int len, String cachedString) {
        return $this.newTermName(cs, offset, len, cachedString).toTypeName();
    }

    public static Names.TermName newTermName(Names $this, String s2) {
        return $this.newTermName(s2.toCharArray(), 0, s2.length(), null);
    }

    public static Names.TypeName newTypeName(Names $this, String s2) {
        return $this.newTermName(s2).toTypeName();
    }

    public static final Names.TermName newTermName(Names $this, byte[] bs, int offset, int len) {
        char[] chars = Codec$.MODULE$.fromUTF8(bs, offset, len);
        return $this.newTermName(chars, 0, chars.length);
    }

    public static final Names.TermName newTermNameCached(Names $this, String s2) {
        return $this.newTermName(s2.toCharArray(), 0, s2.length(), s2);
    }

    public static final Names.TypeName newTypeNameCached(Names $this, String s2) {
        return $this.newTypeName(s2.toCharArray(), 0, s2.length(), s2);
    }

    public static final Names.TypeName newTypeName(Names $this, char[] cs, int offset, int len) {
        return $this.newTermName(cs, offset, len, null).toTypeName();
    }

    public static final Names.TypeName newTypeName(Names $this, byte[] bs, int offset, int len) {
        return $this.newTermName(bs, offset, len).toTypeName();
    }

    public static final Names.TypeName lookupTypeName(Names $this, char[] cs) {
        Names.TypeName typeName;
        int hash = Names$class.scala$reflect$internal$Names$$hashValue($this, cs, 0, cs.length) & Short.MAX_VALUE;
        for (typeName = $this.scala$reflect$internal$Names$$typeHashtable()[hash]; !(typeName == null || typeName.length() == cs.length && Names$class.equals($this, typeName.start(), cs, 0, cs.length)); typeName = typeName.next()) {
        }
        Serializable serializable = new Serializable($this, cs){
            public static final long serialVersionUID = 0L;
            public final char[] cs$2;

            public final String apply() {
                return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"TypeName ", " not yet created."})).s(Predef$.MODULE$.genericWrapArray(new Object[]{new String(this.cs$2)}));
            }
            {
                this.cs$2 = cs$2;
            }
        };
        boolean bl = typeName != null;
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"TypeName ", " not yet created."})).s(Predef$.MODULE$.genericWrapArray(new Object[]{new String(serializable.cs$2)}))).toString());
        }
        return typeName;
    }

    public static Names.NameOps AnyNameOps(Names $this, Names.Name name) {
        return new Names.NameOps<Names.Name>($this, name);
    }

    public static Names.NameOps TermNameOps(Names $this, Names.TermName name) {
        return new Names.NameOps<Names.TermName>($this, name);
    }

    public static Names.NameOps TypeNameOps(Names $this, Names.TypeName name) {
        return new Names.NameOps<Names.TypeName>($this, name);
    }

    private static final Names.TermName body$1(Names $this, char[] cs$1, int offset$1, int len0$1, String cachedString$1) {
        Names.TermName termName;
        Names.TermName n;
        Serializable serializable = new Serializable($this, offset$1){
            public static final long serialVersionUID = 0L;
            public final int offset$1;

            public final String apply() {
                return new StringBuilder().append((Object)"offset must be non-negative, got ").append(BoxesRunTime.boxToInteger(this.offset$1)).toString();
            }
            {
                this.offset$1 = offset$1;
            }
        };
        boolean bl = offset$1 >= 0;
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append((Object)new StringBuilder().append((Object)"offset must be non-negative, got ").append(BoxesRunTime.boxToInteger(serializable.offset$1)).toString()).toString());
        }
        int len = package$.MODULE$.max(len0$1, 0);
        int h = Names$class.scala$reflect$internal$Names$$hashValue($this, cs$1, offset$1, len) & Short.MAX_VALUE;
        for (n = $this.scala$reflect$internal$Names$$termHashtable()[h]; !(n == null || n.length() == len && Names$class.equals($this, n.start(), cs$1, offset$1, len)); n = n.next()) {
        }
        if (n != null) {
            termName = n;
        } else {
            int startIndex;
            if (cs$1 == $this.chrs()) {
                startIndex = offset$1;
            } else {
                startIndex = $this.scala$reflect$internal$Names$$nc();
                Names$class.enterChars($this, cs$1, offset$1, len);
            }
            Names.TermName next2 = $this.scala$reflect$internal$Names$$termHashtable()[h];
            Names.TermName termName2 = cachedString$1 != null ? new Names.TermName_S($this, startIndex, len, next2, cachedString$1) : new Names.TermName_R($this, startIndex, len, next2);
            $this.scala$reflect$internal$Names$$termHashtable()[h] = termName2;
            termName = termName2;
        }
        return termName;
    }

    public static void $init$(Names $this) {
        $this.scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$nameLock_$eq(new Object());
        $this.chrs_$eq(new char[131072]);
        $this.scala$reflect$internal$Names$$nc_$eq(0);
        $this.scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$termHashtable_$eq(new Names.TermName[32768]);
        $this.scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$typeHashtable_$eq(new Names.TypeName[32768]);
        $this.scala$reflect$internal$Names$_setter_$NameTag_$eq(ClassTag$.MODULE$.apply(Names.Name.class));
        $this.scala$reflect$internal$Names$_setter_$TermNameTag_$eq(ClassTag$.MODULE$.apply(Names.TermName.class));
        $this.scala$reflect$internal$Names$_setter_$TypeNameTag_$eq(ClassTag$.MODULE$.apply(Names.TypeName.class));
    }
}

