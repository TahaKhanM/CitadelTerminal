/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.concurrent;

import scala.collection.concurrent.BasicNode;
import scala.collection.concurrent.CNode;
import scala.collection.concurrent.Gen;
import scala.collection.concurrent.INode;
import scala.collection.concurrent.LNode;
import scala.collection.concurrent.MainNode;
import scala.collection.concurrent.SNode;

public final class CNode$ {
    public static final CNode$ MODULE$;

    static {
        new CNode$();
    }

    public <K, V> MainNode<K, V> dual(SNode<K, V> x, int xhc, SNode<K, V> y, int yhc, int lev, Gen gen) {
        MainNode mainNode;
        if (lev < 35) {
            int xidx = xhc >>> lev & 0x1F;
            int yidx = yhc >>> lev & 0x1F;
            int bmp = 1 << xidx | 1 << yidx;
            if (xidx == yidx) {
                INode subinode = new INode(gen);
                subinode.mainnode = this.dual(x, xhc, y, yhc, lev + 5, gen);
                mainNode = new CNode(bmp, (BasicNode[])((Object[])new BasicNode[]{subinode}), gen);
            } else {
                mainNode = xidx < yidx ? new CNode(bmp, (BasicNode[])((Object[])new BasicNode[]{x, y}), gen) : new CNode(bmp, (BasicNode[])((Object[])new BasicNode[]{y, x}), gen);
            }
        } else {
            mainNode = new LNode<K, V>(x.k(), x.v(), y.k(), y.v());
        }
        return mainNode;
    }

    private CNode$() {
        MODULE$ = this;
    }
}

