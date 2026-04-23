/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\u0005\rbaB\u0001\u0003!\u0003\r\t!\u0003\u0002\u0006\u0007\"\f'o\u001d\u0006\u0003\u0007\u0011\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-aQ\"\u0001\u0004\n\u000551!AB!osJ+g\rC\u0003\u0010\u0001\u0011\u0005\u0001#\u0001\u0004%S:LG\u000f\n\u000b\u0002#A\u00111BE\u0005\u0003'\u0019\u0011A!\u00168ji\"9Q\u0003\u0001b\u0001\n\u000b1\u0012A\u0001'G+\u00059r\"\u0001\r\u001d\u0003)AaA\u0007\u0001!\u0002\u001b9\u0012a\u0001'GA!9A\u0004\u0001b\u0001\n\u000bi\u0012A\u0001$G+\u0005qr\"A\u0010\u001d\u00031Aa!\t\u0001!\u0002\u001bq\u0012a\u0001$GA!91\u0005\u0001b\u0001\n\u000b!\u0013AA\"S+\u0005)s\"\u0001\u0014\u001d\u00035Aa\u0001\u000b\u0001!\u0002\u001b)\u0013aA\"SA!9!\u0006\u0001b\u0001\n\u000bY\u0013AA*V+\u0005as\"A\u0017\u001d\u0003iAaa\f\u0001!\u0002\u001ba\u0013aA*VA!)\u0011\u0007\u0001C\u0001e\u0005IA-[4jiJJg\u000e\u001e\u000b\u0004gYZ\u0004CA\u00065\u0013\t)dAA\u0002J]RDQa\u000e\u0019A\u0002a\n!a\u00195\u0011\u0005-I\u0014B\u0001\u001e\u0007\u0005\u0011\u0019\u0005.\u0019:\t\u000bq\u0002\u0004\u0019A\u001a\u0002\t\t\f7/\u001a\u0005\u0007}\u0001\u0001\u000b\u0011B \u0002#\rD\u0017M\u001d\u001avKN\u001c\u0017\r]3BeJ\f\u0017\u0010E\u0002\f\u0001bJ!!\u0011\u0004\u0003\u000b\u0005\u0013(/Y=\t\u000b\r\u0003A\u0011\u0001#\u0002\u0019\rD\u0017M\u001d\u001avKN\u001c\u0017\r]3\u0015\u0005\u0015c\u0005C\u0001$J\u001d\tYq)\u0003\u0002I\r\u00051\u0001K]3eK\u001aL!AS&\u0003\rM#(/\u001b8h\u0015\tAe\u0001C\u0003N\u0005\u0002\u0007\u0001(A\u0001d\u0011\u0015y\u0005\u0001\"\u0001Q\u0003=I7\u000fT5oK\n\u0013X-Y6DQ\u0006\u0014HCA)U!\tY!+\u0003\u0002T\r\t9!i\\8mK\u0006t\u0007\"B'O\u0001\u0004A\u0004\"\u0002,\u0001\t\u00039\u0016\u0001D5t/\"LG/Z:qC\u000e,GCA)Y\u0011\u0015iU\u000b1\u00019\u0011\u0015Q\u0006\u0001\"\u0001\\\u0003%I7OV1s!\u0006\u0014H\u000f\u0006\u0002R9\")Q*\u0017a\u0001q!)a\f\u0001C\u0001?\u0006\t\u0012n]%eK:$\u0018NZ5feN#\u0018M\u001d;\u0015\u0005E\u0003\u0007\"B'^\u0001\u0004A\u0004\"\u00022\u0001\t\u0003\u0019\u0017\u0001E5t\u0013\u0012,g\u000e^5gS\u0016\u0014\b+\u0019:u)\t\tF\rC\u0003NC\u0002\u0007\u0001\bC\u0003g\u0001\u0011\u0005q-A\u0005jgN\u0003XmY5bYR\u0011\u0011\u000b\u001b\u0005\u0006\u001b\u0016\u0004\r\u0001\u000f\u0005\bU\u0002\u0011\r\u0011\"\u0004l\u00031yG\u000f[3s\u0019\u0016$H/\u001a:t+\u0005a\u0007cA7sq5\taN\u0003\u0002pa\u0006I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003c\u001a\t!bY8mY\u0016\u001cG/[8o\u0013\t\u0019hNA\u0002TKRDa!\u001e\u0001!\u0002\u001ba\u0017!D8uQ\u0016\u0014H*\u001a;uKJ\u001c\b\u0005C\u0004x\u0001\t\u0007IQ\u0002=\u0002\u00191,G\u000f^3s\u000fJ|W\u000f]:\u0016\u0003e\u00042!\u001c:{!\tY10\u0003\u0002}\r\t!!)\u001f;f\u0011\u0019q\b\u0001)A\u0007s\u0006iA.\u001a;uKJ<%o\\;qg\u0002Bq!!\u0001\u0001\t\u0003\t\u0019!A\u0007jgN\u001b\u0017\r\\1MKR$XM\u001d\u000b\u0004#\u0006\u0015\u0001\"B\u001c\u0000\u0001\u0004A\u0004bBA\u0005\u0001\u0011\u0005\u00111B\u0001\u000fSN|\u0005/\u001a:bi>\u0014\b+\u0019:u)\r\t\u0016Q\u0002\u0005\u0007\u001b\u0006\u001d\u0001\u0019\u0001\u001d\b\u000f\u0005E!\u0001#\u0001\u0002\u0014\u0005)1\t[1sgB!\u0011QCA\f\u001b\u0005\u0011aAB\u0001\u0003\u0011\u0003\tIbE\u0003\u0002\u0018)\tY\u0002E\u0002\u0002\u0016\u0001A\u0001\"a\b\u0002\u0018\u0011\u0005\u0011\u0011E\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0005M\u0001")
public interface Chars {
    public char[] scala$reflect$internal$Chars$$char2uescapeArray();

    public void scala$reflect$internal$Chars$_setter_$scala$reflect$internal$Chars$$char2uescapeArray_$eq(char[] var1);

    public void scala$reflect$internal$Chars$_setter_$scala$reflect$internal$Chars$$otherLetters_$eq(Set var1);

    public void scala$reflect$internal$Chars$_setter_$scala$reflect$internal$Chars$$letterGroups_$eq(Set var1);

    public char LF();

    public char FF();

    public char CR();

    public char SU();

    public int digit2int(char var1, int var2);

    public String char2uescape(char var1);

    public boolean isLineBreakChar(char var1);

    public boolean isWhitespace(char var1);

    public boolean isVarPart(char var1);

    public boolean isIdentifierStart(char var1);

    public boolean isIdentifierPart(char var1);

    public boolean isSpecial(char var1);

    public Set<Object> scala$reflect$internal$Chars$$otherLetters();

    public Set<Object> scala$reflect$internal$Chars$$letterGroups();

    public boolean isScalaLetter(char var1);

    public boolean isOperatorPart(char var1);
}

