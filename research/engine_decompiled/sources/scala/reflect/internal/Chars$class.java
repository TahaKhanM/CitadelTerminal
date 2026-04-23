/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Array$;
import scala.Predef$;
import scala.collection.immutable.Set;
import scala.reflect.ClassTag$;
import scala.reflect.internal.Chars;
import scala.runtime.BoxesRunTime;

public abstract class Chars$class {
    public static int digit2int(Chars $this, char ch, int base) {
        int num = ch <= '9' ? ch - 48 : ('a' <= ch && ch <= 'z' ? ch - 97 + 10 : ('A' <= ch && ch <= 'Z' ? ch - 65 + 10 : -1));
        return 0 <= num && num < base ? num : -1;
    }

    public static String char2uescape(Chars $this, char c) {
        $this.scala$reflect$internal$Chars$$char2uescapeArray()[2] = Chars$class.hexChar$1($this, c >> 12);
        $this.scala$reflect$internal$Chars$$char2uescapeArray()[3] = Chars$class.hexChar$1($this, (c >> 8) % 16);
        $this.scala$reflect$internal$Chars$$char2uescapeArray()[4] = Chars$class.hexChar$1($this, (c >> 4) % 16);
        $this.scala$reflect$internal$Chars$$char2uescapeArray()[5] = Chars$class.hexChar$1($this, c % 16);
        return new String($this.scala$reflect$internal$Chars$$char2uescapeArray());
    }

    public static boolean isLineBreakChar(Chars $this, char c) {
        boolean bl;
        switch (c) {
            default: {
                bl = false;
                break;
            }
            case '\n': 
            case '\f': 
            case '\r': 
            case '\u001a': {
                bl = true;
            }
        }
        return bl;
    }

    public static boolean isWhitespace(Chars $this, char c) {
        return c == ' ' || c == '\t' || c == '\r';
    }

    public static boolean isVarPart(Chars $this, char c) {
        return '0' <= c && c <= '9' || 'A' <= c && c <= 'Z' || 'a' <= c && c <= 'z';
    }

    public static boolean isIdentifierStart(Chars $this, char c) {
        return c == '_' || c == '$' || Character.isUnicodeIdentifierStart(c);
    }

    public static boolean isIdentifierPart(Chars $this, char c) {
        return c == '$' || Character.isUnicodeIdentifierPart(c);
    }

    public static boolean isSpecial(Chars $this, char c) {
        int chtp = Character.getType(c);
        return chtp == 25 || chtp == 28;
    }

    public static boolean isScalaLetter(Chars $this, char ch) {
        return $this.scala$reflect$internal$Chars$$letterGroups().apply(BoxesRunTime.boxToByte((byte)Character.getType(ch))) || $this.scala$reflect$internal$Chars$$otherLetters().apply(BoxesRunTime.boxToCharacter(ch));
    }

    public static boolean isOperatorPart(Chars $this, char c) {
        boolean bl;
        switch (c) {
            default: {
                bl = $this.isSpecial(c);
                break;
            }
            case '!': 
            case '#': 
            case '%': 
            case '&': 
            case '*': 
            case '+': 
            case '-': 
            case '/': 
            case ':': 
            case '<': 
            case '=': 
            case '>': 
            case '?': 
            case '@': 
            case '\\': 
            case '^': 
            case '|': 
            case '~': {
                bl = true;
            }
        }
        return bl;
    }

    private static final char hexChar$1(Chars $this, int ch) {
        return (char)((ch < 10 ? 48 : 55) + ch);
    }

    public static void $init$(Chars $this) {
        $this.scala$reflect$internal$Chars$_setter_$scala$reflect$internal$Chars$$char2uescapeArray_$eq((char[])Array$.MODULE$.apply(Predef$.MODULE$.wrapCharArray(new char[]{'\\', 'u', '\u0000', '\u0000', '\u0000', '\u0000'}), ClassTag$.MODULE$.Char()));
        $this.scala$reflect$internal$Chars$_setter_$scala$reflect$internal$Chars$$otherLetters_$eq((Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapCharArray(new char[]{'$', '_'})));
        $this.scala$reflect$internal$Chars$_setter_$scala$reflect$internal$Chars$$letterGroups_$eq((Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapByteArray(new byte[]{2, 1, 5, 3, 10})));
    }
}

