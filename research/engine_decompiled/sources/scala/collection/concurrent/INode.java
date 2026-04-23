/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.concurrent;

import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.collection.concurrent.BasicNode;
import scala.collection.concurrent.CNode;
import scala.collection.concurrent.CNode$;
import scala.collection.concurrent.FailedNode;
import scala.collection.concurrent.Gen;
import scala.collection.concurrent.INode$;
import scala.collection.concurrent.INodeBase;
import scala.collection.concurrent.LNode;
import scala.collection.concurrent.MainNode;
import scala.collection.concurrent.SNode;
import scala.collection.concurrent.TNode;
import scala.collection.concurrent.TrieMap;
import scala.collection.immutable.StringOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001\u0005]g!B\u0001\u0003\u0005\u0011A!!B%O_\u0012,'BA\u0002\u0005\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003\u000b\u0019\t!bY8mY\u0016\u001cG/[8o\u0015\u00059\u0011!B:dC2\fWcA\u0005\u00119M\u0011\u0001A\u0003\t\u0005\u00171q1$D\u0001\u0003\u0013\ti!AA\u0005J\u001d>$WMQ1tKB\u0011q\u0002\u0005\u0007\u0001\t\u0015\t\u0002A1\u0001\u0014\u0005\u0005Y5\u0001A\t\u0003)a\u0001\"!\u0006\f\u000e\u0003\u0019I!a\u0006\u0004\u0003\u000f9{G\u000f[5oOB\u0011Q#G\u0005\u00035\u0019\u00111!\u00118z!\tyA\u0004B\u0003\u001e\u0001\t\u00071CA\u0001W\u0011!y\u0002A!A!\u0002\u0013\u0001\u0013A\u00012o!\u0011Y\u0011ED\u000e\n\u0005\t\u0012!\u0001C'bS:tu\u000eZ3\t\u0011\u0011\u0002!\u0011!Q\u0001\n\u0015\n\u0011a\u001a\t\u0003\u0017\u0019J!a\n\u0002\u0003\u0007\u001d+g\u000eC\u0003*\u0001\u0011\u0005!&\u0001\u0004=S:LGO\u0010\u000b\u0004W1j\u0003\u0003B\u0006\u0001\u001dmAQa\b\u0015A\u0002\u0001BQ\u0001\n\u0015A\u0002\u0015BQ!\u000b\u0001\u0005\u0002=\"\"a\u000b\u0019\t\u000b\u0011r\u0003\u0019A\u0013\t\u000bI\u0002A\u0011A\u001a\u0002\u000b]\u0013\u0016\nV#\u0015\u0005Q:\u0004CA\u000b6\u0013\t1dA\u0001\u0003V]&$\b\"\u0002\u001d2\u0001\u0004\u0001\u0013\u0001\u00028wC2DQA\u000f\u0001\u0005\u0002m\n1aQ!T)\rat(\u0011\t\u0003+uJ!A\u0010\u0004\u0003\u000f\t{w\u000e\\3b]\")\u0001)\u000fa\u0001A\u0005\u0019q\u000e\u001c3\t\u000b\tK\u0004\u0019\u0001\u0011\u0002\u00039DQ\u0001\u0012\u0001\u0005\u0002\u0015\u000b\u0001bZ2bgJ+\u0017\r\u001a\u000b\u0003A\u0019CQaR\"A\u0002!\u000b!a\u0019;\u0011\t-IebG\u0005\u0003\u0015\n\u0011q\u0001\u0016:jK6\u000b\u0007\u000fC\u0003M\u0001\u0011\u0005Q*A\u0005H\u0007\u0006\u001bvLU#B\tR\u0011\u0001E\u0014\u0005\u0006\u000f.\u0003\r\u0001\u0013\u0005\u0006!\u0002!I!U\u0001\u000e\u000f\u000e\u000b5kX\"p[BdW\r^3\u0015\u0007\u0001\u0012F\u000bC\u0003T\u001f\u0002\u0007\u0001%A\u0001n\u0011\u00159u\n1\u0001IQ\tye\u000b\u0005\u0002X56\t\u0001L\u0003\u0002Z\r\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005mC&a\u0002;bS2\u0014Xm\u0019\u0005\u0006;\u0002!\tAX\u0001\u0005\u000f\u000e\u000b5\u000b\u0006\u0003=?\u0002\f\u0007\"\u0002!]\u0001\u0004\u0001\u0003\"\u0002\"]\u0001\u0004\u0001\u0003\"B$]\u0001\u0004A\u0005\"B2\u0001\t\u0013!\u0017!B3rk\u0006dG\u0003\u0002\u001ffO&DQA\u001a2A\u00029\t!a[\u0019\t\u000b!\u0014\u0007\u0019\u0001\b\u0002\u0005-\u0014\u0004\"B$c\u0001\u0004A\u0005\"B6\u0001\t\u0013a\u0017!B5o_\u0012,GCA\u0016n\u0011\u0015q'\u000e1\u0001!\u0003\t\u0019g\u000eC\u0003q\u0001\u0011\u0005\u0011/A\u0005d_BLHk\\$f]R\u00191F\u001d;\t\u000bM|\u0007\u0019A\u0013\u0002\t9<WM\u001c\u0005\u0006\u000f>\u0004\r\u0001\u0013\u0005\u0006m\u0002!\ta^\u0001\u000be\u0016\u001cw,\u001b8tKJ$H\u0003\u0004\u001fyur\f\u0019!a\u0002\u0002\f\u0005=\u0001\"B=v\u0001\u0004q\u0011!A6\t\u000bm,\b\u0019A\u000e\u0002\u0003YDQ!`;A\u0002y\f!\u0001[2\u0011\u0005Uy\u0018bAA\u0001\r\t\u0019\u0011J\u001c;\t\r\u0005\u0015Q\u000f1\u0001\u007f\u0003\raWM\u001e\u0005\u0007\u0003\u0013)\b\u0019A\u0016\u0002\rA\f'/\u001a8u\u0011\u0019\ti!\u001ea\u0001K\u0005A1\u000f^1si\u001e,g\u000eC\u0003Hk\u0002\u0007\u0001\n\u000b\u0002v-\"9\u0011Q\u0003\u0001\u0005\u0002\u0005]\u0011\u0001\u0004:fG~Kgn]3si&4GCEA\r\u0003?\t\t#a\t\u0002&\u0005=\u0012\u0011GA\u001a\u0003k\u0001B!FA\u000e7%\u0019\u0011Q\u0004\u0004\u0003\r=\u0003H/[8o\u0011\u0019I\u00181\u0003a\u0001\u001d!110a\u0005A\u0002mAa!`A\n\u0001\u0004q\b\u0002CA\u0014\u0003'\u0001\r!!\u000b\u0002\t\r|g\u000e\u001a\t\u0004+\u0005-\u0012bAA\u0017\r\t1\u0011I\\=SK\u001aDq!!\u0002\u0002\u0014\u0001\u0007a\u0010C\u0004\u0002\n\u0005M\u0001\u0019A\u0016\t\u000f\u00055\u00111\u0003a\u0001K!1q)a\u0005A\u0002!C3!a\u0005W\u0011\u001d\tY\u0004\u0001C\u0001\u0003{\t!B]3d?2|wn[;q)9\tI#a\u0010\u0002B\u0005\r\u0013QIA$\u0003\u0013Ba!_A\u001d\u0001\u0004q\u0001BB?\u0002:\u0001\u0007a\u0010C\u0004\u0002\u0006\u0005e\u0002\u0019\u0001@\t\u000f\u0005%\u0011\u0011\ba\u0001W!9\u0011QBA\u001d\u0001\u0004)\u0003BB$\u0002:\u0001\u0007\u0001\nK\u0002\u0002:YCq!a\u0014\u0001\t\u0003\t\t&\u0001\u0006sK\u000e|&/Z7pm\u0016$\u0002#!\u0007\u0002T\u0005U\u0013qKA-\u00037\ni&a\u0018\t\re\fi\u00051\u0001\u000f\u0011\u0019Y\u0018Q\na\u00017!1Q0!\u0014A\u0002yDq!!\u0002\u0002N\u0001\u0007a\u0010C\u0004\u0002\n\u00055\u0003\u0019A\u0016\t\u000f\u00055\u0011Q\na\u0001K!1q)!\u0014A\u0002!Cq!a\u0019\u0001\t\u0013\t)'A\u0003dY\u0016\fg\u000eF\u00045\u0003O\nY'!\u001c\t\u000f\u0005%\u0014\u0011\ra\u0001W\u0005\u0011a\u000e\u001a\u0005\u0007\u000f\u0006\u0005\u0004\u0019\u0001%\t\u000f\u0005\u0015\u0011\u0011\ra\u0001}\"9\u0011\u0011\u000f\u0001\u0005\u0002\u0005M\u0014aC5t\u001dVdG.\u00138pI\u0016$2\u0001PA;\u0011\u00199\u0015q\u000ea\u0001\u0011\"9\u0011\u0011\u0010\u0001\u0005\u0002\u0005m\u0014AC2bG\",GmU5{KR\u0019a0! \t\r\u001d\u000b9\b1\u0001I\u0011\u001d\t\t\t\u0001C\u0001\u0003\u0007\u000baa\u001d;sS:<G\u0003BAC\u0003'\u0003B!a\"\u0002\u000e:\u0019Q#!#\n\u0007\u0005-e!\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003\u001f\u000b\tJ\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003\u00173\u0001bBA\u0003\u0003\u007f\u0002\rA`\u0004\t\u0003/\u0013\u0001\u0012\u0001\u0002\u0002\u001a\u0006)\u0011JT8eKB\u00191\"a'\u0007\u000f\u0005\u0011\u0001\u0012\u0001\u0002\u0002\u001eN!\u00111TA\u0015\u0011\u001dI\u00131\u0014C\u0001\u0003C#\"!!'\t\u0015\u0005\u0015\u00161\u0014b\u0001\n\u0003\t9+A\u0006L\u000bf{\u0006KU#T\u000b:#VCAAU!\u0011\tY+!.\u000e\u0005\u00055&\u0002BAX\u0003c\u000bA\u0001\\1oO*\u0011\u00111W\u0001\u0005U\u00064\u0018-\u0003\u0003\u00028\u00065&AB(cU\u0016\u001cG\u000fC\u0005\u0002<\u0006m\u0005\u0015!\u0003\u0002*\u0006a1*R-`!J+5+\u0012(UA!Q\u0011qXAN\u0005\u0004%\t!a*\u0002\u0015-+\u0015lX!C'\u0016sE\u000bC\u0005\u0002D\u0006m\u0005\u0015!\u0003\u0002*\u0006Y1*R-`\u0003\n\u001bVI\u0014+!\u0011!\t9-a'\u0005\u0002\u0005%\u0017a\u00038foJ{w\u000e\u001e(pI\u0016,b!a3\u0002R\u0006UWCAAg!\u0019Y\u0001!a4\u0002TB\u0019q\"!5\u0005\rE\t)M1\u0001\u0014!\ry\u0011Q\u001b\u0003\u0007;\u0005\u0015'\u0019A\n")
public final class INode<K, V>
extends INodeBase<K, V> {
    public static <K, V> INode<K, V> newRootNode() {
        return INode$.MODULE$.newRootNode();
    }

    public static Object KEY_ABSENT() {
        return INode$.MODULE$.KEY_ABSENT();
    }

    public static Object KEY_PRESENT() {
        return INode$.MODULE$.KEY_PRESENT();
    }

    public void WRITE(MainNode<K, V> nval) {
        INodeBase.updater.set(this, nval);
    }

    public boolean CAS(MainNode<K, V> old, MainNode<K, V> n) {
        return INodeBase.updater.compareAndSet(this, old, n);
    }

    public MainNode<K, V> gcasRead(TrieMap<K, V> ct) {
        return this.GCAS_READ(ct);
    }

    public MainNode<K, V> GCAS_READ(TrieMap<K, V> ct) {
        MainNode<K, V> m = this.mainnode;
        MainNode prevval = m.prev;
        return prevval == null ? m : this.GCAS_Complete(m, ct);
    }

    /*
     * WARNING - void declaration
     */
    private MainNode<K, V> GCAS_Complete(MainNode<K, V> m, TrieMap<K, V> ct) {
        void var6_5;
        while (true) {
            block9: {
                MainNode mainNode;
                block4: {
                    MainNode mainNode2;
                    block6: {
                        INode<K, V> ctr;
                        MainNode prev;
                        block7: {
                            block8: {
                                block5: {
                                    block3: {
                                        if (m != null) break block3;
                                        mainNode = null;
                                        break block4;
                                    }
                                    prev = m.prev;
                                    ctr = ct.readRoot(true);
                                    if (prev != null) break block5;
                                    mainNode2 = m;
                                    break block6;
                                }
                                if (!(prev instanceof FailedNode)) break block7;
                                FailedNode failedNode = (FailedNode)prev;
                                if (!this.CAS(m, failedNode.prev)) break block8;
                                mainNode2 = failedNode.prev;
                                break block6;
                            }
                            m = this.mainnode;
                            continue;
                        }
                        if (prev == null) break;
                        if (ctr.gen != this.gen || !ct.nonReadOnly()) break block9;
                        if (!m.CAS_PREV(prev, null)) continue;
                        mainNode2 = m;
                    }
                    mainNode = mainNode2;
                }
                return mainNode;
            }
            m.CAS_PREV(var6_5, new FailedNode(var6_5));
            m = this.mainnode;
        }
        throw new MatchError(var6_5);
    }

    public boolean GCAS(MainNode<K, V> old, MainNode<K, V> n, TrieMap<K, V> ct) {
        boolean bl;
        n.WRITE_PREV(old);
        if (this.CAS(old, n)) {
            this.GCAS_Complete(n, ct);
            bl = n.prev == null;
        } else {
            bl = false;
        }
        return bl;
    }

    private boolean equal(K k1, K k2, TrieMap<K, V> ct) {
        return ct.equality().equiv(k1, k2);
    }

    /*
     * WARNING - void declaration
     */
    private INode<K, V> inode(MainNode<K, V> cn) {
        void var2_2;
        INode<K, V> nin = new INode<K, V>(this.gen);
        nin.WRITE(cn);
        return var2_2;
    }

    /*
     * WARNING - void declaration
     */
    public INode<K, V> copyToGen(Gen ngen, TrieMap<K, V> ct) {
        void var3_3;
        INode<K, V> nin = new INode<K, V>(ngen);
        MainNode<K, V> main2 = this.GCAS_READ(ct);
        nin.WRITE(main2);
        return var3_3;
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean rec_insert(K k, V v, int hc, int lev, INode<K, V> parent, Gen startgen, TrieMap<K, V> ct) {
        MainNode<K, V> m;
        while ((m = this_.GCAS_READ(ct)) instanceof CNode) {
            boolean bl;
            CNode<K, V> cNode = (CNode<K, V>)m;
            int idx = hc >>> lev & 0x1F;
            int flag = 1 << idx;
            int bmp = cNode.bitmap();
            int mask = flag - 1;
            int pos = Integer.bitCount(bmp & mask);
            if ((bmp & flag) != 0) {
                boolean bl2;
                BasicNode basicNode = cNode.array()[pos];
                if (basicNode instanceof INode) {
                    INode iNode = (INode)basicNode;
                    if (startgen == iNode.gen) {
                        parent = this_;
                        lev += 5;
                        INode this_ = iNode;
                        continue;
                    }
                    if (this_.GCAS(cNode, cNode.renewed(startgen, ct), ct)) continue;
                    return false;
                }
                if (!(basicNode instanceof SNode)) throw new MatchError(basicNode);
                SNode sNode = (SNode)basicNode;
                if (sNode.hc() == hc && this_.equal(sNode.k(), k, ct)) {
                    bl2 = this_.GCAS(cNode, cNode.updatedAt(pos, new SNode<K, V>(k, v, hc), this_.gen), ct);
                } else {
                    CNode<K, V> rn = cNode.gen() == this_.gen ? cNode : cNode.renewed(this_.gen, ct);
                    CNode<K, V> nn = rn.updatedAt(pos, this_.inode(CNode$.MODULE$.dual(sNode, sNode.hc(), new SNode<K, V>(k, v, hc), hc, lev + 5, this_.gen)), this_.gen);
                    bl2 = this_.GCAS(cNode, nn, ct);
                }
                boolean bl3 = bl2;
                bl = bl3;
                return bl;
            } else {
                CNode<K, V> rn = cNode.gen() == this_.gen ? cNode : cNode.renewed(this_.gen, ct);
                CNode<K, V> ncnode = rn.insertedAt(pos, flag, new SNode<K, V>(k, v, hc), this_.gen);
                bl = this_.GCAS(cNode, ncnode, ct);
            }
            return bl;
        }
        if (m instanceof TNode) {
            this_.clean(parent, ct, lev - 5);
            return false;
        }
        if (!(m instanceof LNode)) void var25_16;
        throw new MatchError(var25_16);
        LNode lNode = (LNode)m;
        LNode<K, V> nn = lNode.inserted(k, v);
        return this_.GCAS(lNode, nn, ct);
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Option<V> rec_insertif(K k, V v, int hc, Object cond, int lev, INode<K, V> parent, Gen startgen, TrieMap<K, V> ct) {
        None$ none$;
        MainNode<K, V> m;
        while ((m = this_.GCAS_READ(ct)) instanceof CNode) {
            None$ none$2;
            Object object;
            None$ none$3;
            CNode<K, V> cNode = (CNode<K, V>)m;
            int idx = hc >>> lev & 0x1F;
            int flag = 1 << idx;
            int bmp = cNode.bitmap();
            int mask = flag - 1;
            int pos = Integer.bitCount(bmp & mask);
            if ((bmp & flag) != 0) {
                void var21_26;
                BasicNode basicNode = cNode.array()[pos];
                if (basicNode instanceof INode) {
                    INode iNode = (INode)basicNode;
                    if (startgen == iNode.gen) {
                        parent = this_;
                        lev += 5;
                        INode this_ = iNode;
                        continue;
                    }
                    if (this_.GCAS(cNode, cNode.renewed(startgen, ct), ct)) continue;
                    return null;
                }
                if (!(basicNode instanceof SNode)) throw new MatchError(basicNode);
                SNode sNode = (SNode)basicNode;
                if (cond == null) {
                    Option option;
                    if (sNode.hc() == hc && this_.equal(sNode.k(), k, ct)) {
                        option = this_.GCAS(cNode, cNode.updatedAt(pos, new SNode<K, V>(k, v, hc), this_.gen), ct) ? new Some(sNode.v()) : null;
                    } else {
                        CNode<K, V> rn = cNode.gen() == this_.gen ? cNode : cNode.renewed(this_.gen, ct);
                        CNode<K, V> nn = rn.updatedAt(pos, this_.inode(CNode$.MODULE$.dual(sNode, sNode.hc(), new SNode<K, V>(k, v, hc), hc, lev + 5, this_.gen)), this_.gen);
                        option = this_.GCAS(cNode, nn, ct) ? None$.MODULE$ : null;
                    }
                    Some some = option;
                } else {
                    Object object2 = INode$.MODULE$.KEY_ABSENT();
                    if (object2 == cond ? true : (object2 == null ? false : (object2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)object2, cond) : (object2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)object2, cond) : object2.equals(cond))))) {
                        Option option;
                        if (sNode.hc() == hc && this_.equal(sNode.k(), k, ct)) {
                            option = new Some(sNode.v());
                        } else {
                            CNode<K, V> rn = cNode.gen() == this_.gen ? cNode : cNode.renewed(this_.gen, ct);
                            CNode<K, V> nn = rn.updatedAt(pos, this_.inode(CNode$.MODULE$.dual(sNode, sNode.hc(), new SNode<K, V>(k, v, hc), hc, lev + 5, this_.gen)), this_.gen);
                            option = this_.GCAS(cNode, nn, ct) ? None$.MODULE$ : null;
                        }
                        Some some = option;
                    } else {
                        Object object3 = INode$.MODULE$.KEY_PRESENT();
                        if (object3 == cond ? true : (object3 == null ? false : (object3 instanceof Number ? BoxesRunTime.equalsNumObject((Number)object3, cond) : (object3 instanceof Character ? BoxesRunTime.equalsCharObject((Character)object3, cond) : object3.equals(cond))))) {
                            None$ none$4 = sNode.hc() == hc && this_.equal(sNode.k(), k, ct) ? (this_.GCAS(cNode, cNode.updatedAt(pos, new SNode<K, V>(k, v, hc), this_.gen), ct) ? new Some(sNode.v()) : null) : None$.MODULE$;
                        } else {
                            Object v2;
                            None$ none$5 = sNode.hc() == hc && this_.equal(sNode.k(), k, ct) && ((v2 = sNode.v()) == cond ? true : (v2 == null ? false : (v2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)v2, cond) : (v2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)v2, cond) : v2.equals(cond))))) ? (this_.GCAS(cNode, cNode.updatedAt(pos, new SNode<K, V>(k, v, hc), this_.gen), ct) ? new Some(sNode.v()) : null) : None$.MODULE$;
                        }
                    }
                }
                Object var22_18 = var21_26;
                none$3 = var22_18;
                return none$3;
            }
            boolean bl = cond == null ? true : ((object = INode$.MODULE$.KEY_ABSENT()) == cond ? true : (object == null ? false : (object instanceof Number ? BoxesRunTime.equalsNumObject((Number)object, cond) : (object instanceof Character ? BoxesRunTime.equalsCharObject((Character)object, cond) : object.equals(cond)))));
            if (bl) {
                CNode<K, V> rn = cNode.gen() == this_.gen ? cNode : cNode.renewed(this_.gen, ct);
                CNode<K, V> ncnode = rn.insertedAt(pos, flag, new SNode<K, V>(k, v, hc), this_.gen);
                if (!this_.GCAS(cNode, ncnode, ct)) return null;
                None$ none$6 = None$.MODULE$;
                none$2 = none$6;
            } else {
                Object object4 = INode$.MODULE$.KEY_PRESENT();
                none$2 = (object4 == cond ? true : (object4 == null ? false : (object4 instanceof Number ? BoxesRunTime.equalsNumObject((Number)object4, cond) : (object4 instanceof Character ? BoxesRunTime.equalsCharObject((Character)object4, cond) : object4.equals(cond))))) ? None$.MODULE$ : None$.MODULE$;
            }
            none$3 = none$2;
            return none$3;
        }
        if (m instanceof TNode) {
            this_.clean(parent, ct, lev - 5);
            return null;
        }
        if (!(m instanceof LNode)) void var48_13;
        throw new MatchError(var48_13);
        LNode lNode = (LNode)m;
        if (cond == null) {
            None$ optv = lNode.get(k);
            if (!this_.insertln$1(k, v, ct, lNode)) return null;
            None$ none$7 = optv;
            none$ = none$7;
            return none$;
        } else {
            Object object = INode$.MODULE$.KEY_ABSENT();
            if (object == cond ? true : (object == null ? false : (object instanceof Number ? BoxesRunTime.equalsNumObject((Number)object, cond) : (object instanceof Character ? BoxesRunTime.equalsCharObject((Character)object, cond) : object.equals(cond))))) {
                None$ none$8;
                None$ none$9 = lNode.get(k);
                if (None$.MODULE$.equals(none$9)) {
                    if (!this_.insertln$1(k, v, ct, lNode)) return null;
                    None$ none$10 = None$.MODULE$;
                    none$8 = none$10;
                } else {
                    none$8 = none$9;
                }
                none$ = none$8;
                return none$;
            } else {
                Object object5 = INode$.MODULE$.KEY_PRESENT();
                if (object5 == cond ? true : (object5 == null ? false : (object5 instanceof Number ? BoxesRunTime.equalsNumObject((Number)object5, cond) : (object5 instanceof Character ? BoxesRunTime.equalsCharObject((Character)object5, cond) : object5.equals(cond))))) {
                    void var39_50;
                    Option option = lNode.get(k);
                    if (option instanceof Some) {
                        Some some = (Some)option;
                        Some some2 = this_.insertln$1(k, v, ct, lNode) ? new Some(some.x()) : null;
                    } else {
                        if (!None$.MODULE$.equals(option)) throw new MatchError(option);
                        None$ none$11 = None$.MODULE$;
                    }
                    none$ = var39_50;
                    return none$;
                } else {
                    void var45_56;
                    Some some;
                    Object a;
                    Option option = lNode.get(k);
                    if (option instanceof Some && ((a = (some = (Some)option).x()) == cond ? true : (a == null ? false : (a instanceof Number ? BoxesRunTime.equalsNumObject((Number)a, cond) : (a instanceof Character ? BoxesRunTime.equalsCharObject((Character)a, cond) : a.equals(cond)))))) {
                        Some<Object> some3 = this_.insertln$1(k, v, ct, lNode) ? new Some<Object>(cond) : null;
                    } else {
                        None$ none$12 = None$.MODULE$;
                    }
                    none$ = var45_56;
                }
            }
        }
        return none$;
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Object rec_lookup(K k, int hc, int lev, INode<K, V> parent, Gen startgen, TrieMap<K, V> ct) {
        MainNode<K, V> m;
        while ((m = this_.GCAS_READ(ct)) instanceof CNode) {
            Object object;
            CNode cNode = (CNode)m;
            int idx = hc >>> lev & 0x1F;
            int flag = 1 << idx;
            int bmp = cNode.bitmap();
            if ((bmp & flag) == 0) {
                return null;
            }
            int pos = bmp == -1 ? idx : Integer.bitCount(bmp & flag - 1);
            BasicNode sub = cNode.array()[pos];
            if (sub instanceof INode) {
                INode iNode = (INode)sub;
                if (ct.isReadOnly() || startgen == iNode.gen) {
                    parent = this_;
                    lev += 5;
                    INode this_ = iNode;
                    continue;
                }
                if (this_.GCAS(cNode, cNode.renewed(startgen, ct), ct)) continue;
                object = INodeBase.RESTART;
            } else {
                if (!(sub instanceof SNode)) void var16_13;
                throw new MatchError(var16_13);
                SNode sNode = (SNode)sub;
                if (sNode.hc() != hc) return null;
                Object k2 = sNode.k();
                if (!ct.equality().equiv(k2, k)) return null;
                Object v = sNode.v();
                object = v;
            }
            Object object2 = object;
            return object2;
        }
        if (m instanceof TNode) {
            TNode tNode = (TNode)m;
            return this_.cleanReadOnly$1(tNode, k, hc, lev, parent, ct);
        }
        if (!(m instanceof LNode)) void var23_14;
        throw new MatchError(var23_14);
        LNode lNode = (LNode)m;
        Predef$.less.colon.less less2 = Predef$.MODULE$.$conforms();
        Option option = lNode.listmap().get(k);
        Serializable serializable = new Serializable(option, less2){
            public static final long serialVersionUID = 0L;
            public final Predef$.less.colon.less ev$1;

            public final A1 apply() {
                return (A1)this.ev$1.apply(null);
            }
            {
                this.ev$1 = ev$1;
            }
        };
        return option.isEmpty() ? serializable.ev$1.apply(null) : option.get();
    }

    /*
     * WARNING - void declaration
     */
    public Option<V> rec_remove(K k, V v, int hc, int lev, INode<K, V> parent, Gen startgen, TrieMap<K, V> ct) {
        void var30_8;
        block16: {
            None$ none$;
            block14: {
                Option option;
                MainNode<K, V> m;
                block15: {
                    block8: {
                        BasicNode sub;
                        block13: {
                            None$ none$2;
                            block10: {
                                Option option2;
                                block12: {
                                    MainNode ncn;
                                    Object v2;
                                    int pos;
                                    int flag;
                                    CNode cNode;
                                    block11: {
                                        int bmp;
                                        block9: {
                                            m = this.GCAS_READ(ct);
                                            if (!(m instanceof CNode)) break block8;
                                            cNode = (CNode)m;
                                            int idx = hc >>> lev & 0x1F;
                                            bmp = cNode.bitmap();
                                            if ((bmp & (flag = 1 << idx)) != 0) break block9;
                                            none$2 = None$.MODULE$;
                                            break block10;
                                        }
                                        pos = Integer.bitCount(bmp & flag - 1);
                                        sub = cNode.array()[pos];
                                        if (!(sub instanceof INode)) break block11;
                                        INode iNode = (INode)sub;
                                        option2 = startgen == iNode.gen ? iNode.rec_remove(k, v, hc, lev + 5, this, startgen, ct) : (this.GCAS(cNode, cNode.renewed(startgen, ct), ct) ? this.rec_remove(k, v, hc, lev, parent, startgen, ct) : null);
                                        break block12;
                                    }
                                    if (!(sub instanceof SNode)) break block13;
                                    SNode sNode = (SNode)sub;
                                    option2 = sNode.hc() == hc && this.equal(sNode.k(), k, ct) && (v == null || ((v2 = sNode.v()) == v ? true : (v2 == null ? false : (v2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)v2, v) : (v2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)v2, v) : v2.equals(v)))))) ? (this.GCAS(cNode, ncn = cNode.removedAt(pos, flag, this.gen).toContracted(lev), ct) ? new Some(sNode.v()) : null) : None$.MODULE$;
                                }
                                None$ none$3 = option2;
                                None$ none$4 = None$.MODULE$;
                                if (none$3 != null && none$3.equals(none$4) || option2 == null) {
                                    none$2 = option2;
                                } else {
                                    MainNode<K, V> n;
                                    if (parent != null && (n = this.GCAS_READ(ct)) instanceof TNode) {
                                        this.cleanParent$1(n, hc, lev, parent, startgen, ct);
                                    }
                                    none$2 = option2;
                                }
                            }
                            none$ = none$2;
                            break block14;
                        }
                        throw new MatchError(sub);
                    }
                    if (!(m instanceof TNode)) break block15;
                    this.clean(parent, ct, lev - 5);
                    none$ = null;
                    break block14;
                }
                if (!(m instanceof LNode)) break block16;
                LNode lNode = (LNode)m;
                if (v == null) {
                    Option optv = lNode.get(k);
                    MainNode<K, V> nn = lNode.removed(k, ct);
                    option = this.GCAS(lNode, nn, ct) ? optv : null;
                } else {
                    MainNode<K, V> nn;
                    Some some;
                    Object a;
                    Option option3 = lNode.get(k);
                    Option option4 = option3 instanceof Some && ((a = (some = (Some)option3).x()) == v ? true : (a == null ? false : (a instanceof Number ? BoxesRunTime.equalsNumObject((Number)a, v) : (a instanceof Character ? BoxesRunTime.equalsCharObject((Character)a, v) : a.equals(v))))) ? (this.GCAS(lNode, nn = lNode.removed(k, ct), ct) ? some : null) : None$.MODULE$;
                    option = option4;
                }
                none$ = option;
            }
            return none$;
        }
        throw new MatchError(var30_8);
    }

    private void clean(INode<K, V> nd, TrieMap<K, V> ct, int lev) {
        block0: {
            MainNode<K, V> m = nd.GCAS_READ(ct);
            if (!(m instanceof CNode)) break block0;
            CNode cNode = (CNode)m;
            nd.GCAS(cNode, cNode.toCompressed(ct, lev, this.gen), ct);
        }
    }

    public boolean isNullInode(TrieMap<K, V> ct) {
        return this.GCAS_READ(ct) == null;
    }

    public int cachedSize(TrieMap<K, V> ct) {
        MainNode<K, V> m = this.GCAS_READ(ct);
        return m.cachedSize(ct);
    }

    @Override
    public String string(int lev) {
        String string2;
        Predef$ predef$ = Predef$.MODULE$;
        StringOps stringOps = new StringOps("%sINode -> %s");
        Object[] objectArray = new Object[2];
        Predef$ predef$2 = Predef$.MODULE$;
        objectArray[0] = new StringOps("  ").$times(lev);
        MainNode mainNode = this.mainnode;
        if (mainNode == null) {
            string2 = "<null>";
        } else if (mainNode instanceof TNode) {
            TNode tNode = (TNode)mainNode;
            Predef$ predef$3 = Predef$.MODULE$;
            string2 = new StringOps("TNode(%s, %s, %d, !)").format(Predef$.MODULE$.genericWrapArray(new Object[]{tNode.k(), tNode.v(), BoxesRunTime.boxToInteger(tNode.hc())}));
        } else if (mainNode instanceof CNode) {
            CNode cNode = (CNode)mainNode;
            string2 = cNode.string(lev);
        } else if (mainNode instanceof LNode) {
            LNode lNode = (LNode)mainNode;
            string2 = lNode.string(lev);
        } else {
            Predef$ predef$4 = Predef$.MODULE$;
            string2 = new StringOps("<elem: %s>").format(Predef$.MODULE$.genericWrapArray(new Object[]{mainNode}));
        }
        objectArray[1] = string2;
        return stringOps.format(Predef$.MODULE$.genericWrapArray(objectArray));
    }

    private final boolean insertln$1(Object k$1, Object v$1, TrieMap ct$1, LNode x4$1) {
        LNode<Object, Object> nn = x4$1.inserted(k$1, v$1);
        return this.GCAS(x4$1, nn, ct$1);
    }

    private final Object cleanReadOnly$1(TNode tn, Object k$2, int hc$1, int lev$1, INode parent$1, TrieMap ct$2) {
        Object object;
        if (ct$2.nonReadOnly()) {
            this.clean(parent$1, ct$2, lev$1 - 5);
            object = INodeBase.RESTART;
        } else {
            Object k;
            object = tn.hc() == hc$1 && ((k = tn.k()) == k$2 ? true : (k == null ? false : (k instanceof Number ? BoxesRunTime.equalsNumObject((Number)k, k$2) : (k instanceof Character ? BoxesRunTime.equalsCharObject((Character)k, k$2) : k.equals(k$2))))) ? tn.v() : null;
        }
        return object;
    }

    private final void cleanParent$1(Object nonlive, int hc$2, int lev$2, INode parent$2, Gen startgen$1, TrieMap ct$3) {
        MainNode<K, V> pm;
        while ((pm = parent$2.GCAS_READ(ct$3)) instanceof CNode) {
            int flag;
            CNode cNode = (CNode)pm;
            int idx = hc$2 >>> lev$2 - 5 & 0x1F;
            int bmp = cNode.bitmap();
            if ((bmp & (flag = 1 << idx)) == 0) {
                break;
            }
            int pos = Integer.bitCount(bmp & flag - 1);
            BasicNode sub = cNode.array()[pos];
            if (sub != this) break;
            if (nonlive instanceof TNode) {
                TNode tNode = (TNode)nonlive;
                MainNode ncn = cNode.updatedAt(pos, tNode.copyUntombed(), this.gen).toContracted(lev$2 - 5);
                if (parent$2.GCAS(cNode, ncn, ct$3)) {
                } else {
                    Gen gen = ct$3.readRoot((boolean)ct$3.readRoot$default$1()).gen;
                    if (!(gen == null ? startgen$1 != null : !gen.equals(startgen$1))) continue;
                }
                break;
            }
            throw new MatchError(nonlive);
        }
    }

    public INode(MainNode<K, V> bn, Gen g) {
        super(g);
        this.WRITE(bn);
    }

    public INode(Gen g) {
        this(null, g);
    }
}

