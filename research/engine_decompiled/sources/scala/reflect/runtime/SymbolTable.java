/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Function0;
import scala.reflect.ScalaSignature;
import scala.reflect.runtime.Gil;
import scala.reflect.runtime.JavaMirrors;
import scala.reflect.runtime.SymbolLoaders;
import scala.reflect.runtime.SynchronizedOps;
import scala.reflect.runtime.ThreadLocalStorage;

@ScalaSignature(bytes="\u0006\u0001}2\u0001\"\u0001\u0002\u0011\u0002\u0007\u0005a\u0001\u0003\u0002\f'fl'm\u001c7UC\ndWM\u0003\u0002\u0004\t\u00059!/\u001e8uS6,'BA\u0003\u0007\u0003\u001d\u0011XM\u001a7fGRT\u0011aB\u0001\u0006g\u000e\fG.Y\n\b\u0001%q!#\u0006\r\u001c!\tQQ\"D\u0001\f\u0015\taA!\u0001\u0005j]R,'O\\1m\u0013\t\t1\u0002\u0005\u0002\u0010!5\t!!\u0003\u0002\u0012\u0005\tY!*\u0019<b\u001b&\u0014(o\u001c:t!\ty1#\u0003\u0002\u0015\u0005\ti1+_7c_2du.\u00193feN\u0004\"a\u0004\f\n\u0005]\u0011!aD*z]\u000eD'o\u001c8ju\u0016$w\n]:\u0011\u0005=I\u0012B\u0001\u000e\u0003\u0005\r9\u0015\u000e\u001c\t\u0003\u001fqI!!\b\u0002\u0003%QC'/Z1e\u0019>\u001c\u0017\r\\*u_J\fw-\u001a\u0005\u0006?\u0001!\t!I\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t!\u0005\u0005\u0002$I5\ta!\u0003\u0002&\r\t!QK\\5u\u0011\u00159\u0003\u0001\"\u0001)\u0003\u0011IgNZ8\u0015\u0005\tJ\u0003B\u0002\u0016'\t\u0003\u00071&A\u0002ng\u001e\u00042a\t\u0017/\u0013\ticA\u0001\u0005=Eft\u0017-\\3?!\ty#G\u0004\u0002$a%\u0011\u0011GB\u0001\u0007!J,G-\u001a4\n\u0005M\"$AB*ue&twM\u0003\u00022\r!)a\u0007\u0001C\u0001o\u0005IA-\u001a2vO&sgm\u001c\u000b\u0003EaBaAK\u001b\u0005\u0002\u0004Y\u0003\"\u0002\u001e\u0001\t\u0003Z\u0014AE5t\u0007>l\u0007/\u001b7feVs\u0017N^3sg\u0016,\u0012\u0001\u0010\t\u0003GuJ!A\u0010\u0004\u0003\u000f\t{w\u000e\\3b]\u0002")
public interface SymbolTable
extends JavaMirrors,
SymbolLoaders,
SynchronizedOps,
Gil,
ThreadLocalStorage {
    public void info(Function0<String> var1);

    public void debugInfo(Function0<String> var1);

    public boolean isCompilerUniverse();
}

