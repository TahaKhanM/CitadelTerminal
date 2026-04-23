/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;

@ScalaSignature(bytes="\u0006\u0001A3\u0001\"\u0001\u0002\u0011\u0002\u0007\u0005\u0011\u0002\u0014\u0002\u0012\u0007\u0006\u0004H/\u001e:fIZ\u000b'/[1cY\u0016\u001c(BA\u0002\u0005\u0003!Ig\u000e^3s]\u0006d'BA\u0003\u0007\u0003\u001d\u0011XM\u001a7fGRT\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001!\u0002\u0005\u0002\f\u00195\ta!\u0003\u0002\u000e\r\t1\u0011I\\=SK\u001aDQa\u0004\u0001\u0005\u0002A\ta\u0001J5oSR$C#A\t\u0011\u0005-\u0011\u0012BA\n\u0007\u0005\u0011)f.\u001b;\t\u000bU\u0001A\u0011\u0001\f\u0002\u001f\r\f\u0007\u000f^;sKZ\u000b'/[1cY\u0016$\"!E\f\t\u000ba!\u0002\u0019A\r\u0002\tY\u0014G.\u001a\t\u00035mi\u0011\u0001A\u0005\u00039u\u0011aaU=nE>d\u0017B\u0001\u0010\u0003\u0005\u001d\u0019\u00160\u001c2pYNDQ\u0001\t\u0001\u0005\u0002\u0005\n\u0011D]3gKJ,gnY3DCB$XO]3e-\u0006\u0014\u0018.\u00192mKR\u0011!e\n\t\u00035\rJ!\u0001J\u0013\u0003\tQ\u0013X-Z\u0005\u0003M\t\u0011Q\u0001\u0016:fKNDQ\u0001G\u0010A\u0002eAQ!\u000b\u0001\u0005\u0002)\nAcY1qiV\u0014X\r\u001a,be&\f'\r\\3UsB,GCA\u00161!\tQB&\u0003\u0002.]\t!A+\u001f9f\u0013\ty#AA\u0003UsB,7\u000fC\u0003\u0019Q\u0001\u0007\u0011\u0004C\u0003*\u0001\u0011\u0005!\u0007\u0006\u0003,gQ2\u0004\"\u0002\r2\u0001\u0004I\u0002bB\u001b2!\u0003\u0005\raK\u0001\u0004iB,\u0007bB\u001c2!\u0003\u0005\r\u0001O\u0001\fKJ\f7/\u001a3UsB,7\u000f\u0005\u0002\fs%\u0011!H\u0002\u0002\b\u0005>|G.Z1o\u0011\u001da\u0004!%A\u0005\u0002u\nadY1qiV\u0014X\r\u001a,be&\f'\r\\3UsB,G\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003yR#aK ,\u0003\u0001\u0003\"!\u0011$\u000e\u0003\tS!a\u0011#\u0002\u0013Ut7\r[3dW\u0016$'BA#\u0007\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u000f\n\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0011\u001dI\u0005!%A\u0005\u0002)\u000badY1qiV\u0014X\r\u001a,be&\f'\r\\3UsB,G\u0005Z3gCVdG\u000fJ\u001a\u0016\u0003-S#\u0001O \u0011\u00055sU\"\u0001\u0002\n\u0005=\u0013!aC*z[\n|G\u000eV1cY\u0016\u0004")
public interface CapturedVariables {
    public void captureVariable(Symbols.Symbol var1);

    public Trees.Tree referenceCapturedVariable(Symbols.Symbol var1);

    public Types.Type capturedVariableType(Symbols.Symbol var1);

    public Types.Type capturedVariableType(Symbols.Symbol var1, Types.Type var2, boolean var3);

    public Types.Type capturedVariableType$default$2();

    public boolean capturedVariableType$default$3();
}

