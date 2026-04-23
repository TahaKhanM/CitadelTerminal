/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.collection.immutable.Set;
import scala.reflect.internal.Chars;
import scala.reflect.internal.Chars$class;

public final class Chars$
implements Chars {
    public static final Chars$ MODULE$;
    private final Set<Object> scala$reflect$internal$Chars$$otherLetters;
    private final Set<Object> scala$reflect$internal$Chars$$letterGroups;
    private final char[] scala$reflect$internal$Chars$$char2uescapeArray;

    static {
        new Chars$();
    }

    @Override
    public final char LF() {
        return '\n';
    }

    @Override
    public final char FF() {
        return '\f';
    }

    @Override
    public final char CR() {
        return '\r';
    }

    @Override
    public final char SU() {
        return '\u001a';
    }

    @Override
    public final Set<Object> scala$reflect$internal$Chars$$otherLetters() {
        return this.scala$reflect$internal$Chars$$otherLetters;
    }

    @Override
    public final Set<Object> scala$reflect$internal$Chars$$letterGroups() {
        return this.scala$reflect$internal$Chars$$letterGroups;
    }

    @Override
    public char[] scala$reflect$internal$Chars$$char2uescapeArray() {
        return this.scala$reflect$internal$Chars$$char2uescapeArray;
    }

    @Override
    public void scala$reflect$internal$Chars$_setter_$scala$reflect$internal$Chars$$char2uescapeArray_$eq(char[] x$1) {
        this.scala$reflect$internal$Chars$$char2uescapeArray = x$1;
    }

    @Override
    public final void scala$reflect$internal$Chars$_setter_$scala$reflect$internal$Chars$$otherLetters_$eq(Set x$1) {
        this.scala$reflect$internal$Chars$$otherLetters = x$1;
    }

    @Override
    public final void scala$reflect$internal$Chars$_setter_$scala$reflect$internal$Chars$$letterGroups_$eq(Set x$1) {
        this.scala$reflect$internal$Chars$$letterGroups = x$1;
    }

    @Override
    public int digit2int(char ch, int base) {
        return Chars$class.digit2int(this, ch, base);
    }

    @Override
    public String char2uescape(char c) {
        return Chars$class.char2uescape(this, c);
    }

    @Override
    public boolean isLineBreakChar(char c) {
        return Chars$class.isLineBreakChar(this, c);
    }

    @Override
    public boolean isWhitespace(char c) {
        return Chars$class.isWhitespace(this, c);
    }

    @Override
    public boolean isVarPart(char c) {
        return Chars$class.isVarPart(this, c);
    }

    @Override
    public boolean isIdentifierStart(char c) {
        return Chars$class.isIdentifierStart(this, c);
    }

    @Override
    public boolean isIdentifierPart(char c) {
        return Chars$class.isIdentifierPart(this, c);
    }

    @Override
    public boolean isSpecial(char c) {
        return Chars$class.isSpecial(this, c);
    }

    @Override
    public boolean isScalaLetter(char ch) {
        return Chars$class.isScalaLetter(this, ch);
    }

    @Override
    public boolean isOperatorPart(char c) {
        return Chars$class.isOperatorPart(this, c);
    }

    private Chars$() {
        MODULE$ = this;
        Chars$class.$init$(this);
    }
}

