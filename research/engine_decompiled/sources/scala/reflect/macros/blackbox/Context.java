/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros.blackbox;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.Mirror;
import scala.reflect.macros.Aliases;
import scala.reflect.macros.Enclosures;
import scala.reflect.macros.Evals;
import scala.reflect.macros.ExprUtils;
import scala.reflect.macros.FrontEnds;
import scala.reflect.macros.Infrastructure;
import scala.reflect.macros.Internals;
import scala.reflect.macros.Names;
import scala.reflect.macros.Parsers;
import scala.reflect.macros.Reifiers;
import scala.reflect.macros.Typers;
import scala.reflect.macros.Universe;

@ScalaSignature(bytes="\u0006\u0001Q3q!\u0001\u0002\u0011\u0002G\u00051BA\u0004D_:$X\r\u001f;\u000b\u0005\r!\u0011\u0001\u00032mC\u000e\\'m\u001c=\u000b\u0005\u00151\u0011AB7bGJ|7O\u0003\u0002\b\u0011\u00059!/\u001a4mK\u000e$(\"A\u0005\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001Mi\u0001\u0001\u0004\t\u0015/ii\u0002e\t\u0014*Y=\u0002\"!\u0004\b\u000e\u0003!I!a\u0004\u0005\u0003\r\u0005s\u0017PU3g!\t\t\"#D\u0001\u0005\u0013\t\u0019BAA\u0004BY&\f7/Z:\u0011\u0005E)\u0012B\u0001\f\u0005\u0005))en\u00197pgV\u0014Xm\u001d\t\u0003#aI!!\u0007\u0003\u0003\u000b9\u000bW.Z:\u0011\u0005EY\u0012B\u0001\u000f\u0005\u0005!\u0011V-\u001b4jKJ\u001c\bCA\t\u001f\u0013\tyBAA\u0005Ge>tG/\u00128egB\u0011\u0011#I\u0005\u0003E\u0011\u0011a\"\u00138ge\u0006\u001cHO];diV\u0014X\r\u0005\u0002\u0012I%\u0011Q\u0005\u0002\u0002\u0007)f\u0004XM]:\u0011\u0005E9\u0013B\u0001\u0015\u0005\u0005\u001d\u0001\u0016M]:feN\u0004\"!\u0005\u0016\n\u0005-\"!!B#wC2\u001c\bCA\t.\u0013\tqCAA\u0005FqB\u0014X\u000b^5mgB\u0011\u0011\u0003M\u0005\u0003c\u0011\u0011\u0011\"\u00138uKJt\u0017\r\\:\t\u000fM\u0002!\u0019!D\u0001i\u0005AQO\\5wKJ\u001cX-F\u00016!\t\tb'\u0003\u00028\t\tAQK\\5wKJ\u001cX\rC\u0004:\u0001\t\u0007i\u0011\u0001\u001e\u0002\r5L'O]8s+\u0005Y\u0004C\u0001\u001f?\u001d\ti$'D\u0001\u0001\u0013\ty\u0004I\u0001\u0004NSJ\u0014xN]\u0005\u0003\u0003\n\u0013q!T5se>\u00148O\u0003\u0002D\r\u0005\u0019\u0011\r]5\u0005\u000b\u0015\u0003!\u0011\u0001$\u0003\u0015A\u0013XMZ5y)f\u0004X-\u0005\u0002H\u0015B\u0011Q\u0002S\u0005\u0003\u0013\"\u0011qAT8uQ&tw\r\u0005\u0002\u000e\u0017&\u0011A\n\u0003\u0002\u0004\u0003:L\bb\u0002(\u0001\u0005\u00045\taT\u0001\u0007aJ,g-\u001b=\u0016\u0003A\u00032!P)T\u0013\t\u0011&C\u0001\u0003FqB\u0014\bCA\u001fE\u0001")
public interface Context
extends Aliases,
Enclosures,
Names,
Reifiers,
FrontEnds,
Infrastructure,
Typers,
Parsers,
Evals,
ExprUtils,
Internals {
    public Universe universe();

    public Mirror mirror();

    public Exprs.Expr<Object> prefix();
}

