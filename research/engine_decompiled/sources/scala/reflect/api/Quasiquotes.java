/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Quasiquotes$Quasiquote$cq$;
import scala.reflect.api.Quasiquotes$Quasiquote$fq$;
import scala.reflect.api.Quasiquotes$Quasiquote$pq$;
import scala.reflect.api.Quasiquotes$Quasiquote$q$;
import scala.reflect.api.Quasiquotes$Quasiquote$tq$;
import scala.reflect.api.Universe;

@ScalaSignature(bytes="\u0006\u0001M4\u0001\"\u0001\u0002\u0011\u0002\u0007\u0005\u0011b\u001c\u0002\f#V\f7/[9v_R,7O\u0003\u0002\u0004\t\u0005\u0019\u0011\r]5\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u000b!\tYA\"D\u0001\u0007\u0013\tiaA\u0001\u0004B]f\u0014VM\u001a\u0005\u0006\u001f\u0001!\t\u0001E\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003E\u0001\"a\u0003\n\n\u0005M1!\u0001B+oSR4A!\u0006\u0001\u0002-\tQ\u0011+^1tSF,x\u000e^3\u0014\u0005QQ\u0001\u0002\u0003\r\u0015\u0005\u0003\u0005\u000b\u0011B\r\u0002\u0007\r$\b\u0010\u0005\u0002\f5%\u00111D\u0002\u0002\u000e'R\u0014\u0018N\\4D_:$X\r\u001f;\t\u000bu!B\u0011\u0001\u0010\u0002\rqJg.\u001b;?)\ty\u0012\u0005\u0005\u0002!)5\t\u0001\u0001C\u0003\u00199\u0001\u0007\u0011DB\u0004$)A\u0005\u0019\u0011\u0003\u0013\u0003\u0007\u0005\u0004\u0018n\u0005\u0002#\u0015!)qB\tC\u0001!!1qE\tB\u0005\u0002!\nQ!\u00199qYf,\"!\u000b\u001c\u0015\u0005)z\u0003C\u0001\u0011,\u0013\taSF\u0001\u0003Ue\u0016,\u0017B\u0001\u0018\u0003\u0005\u0015!&/Z3t\u0011\u0015\u0001d\u00051\u00012\u0003\u0011\t'oZ:\u0011\u0007-\u0011D'\u0003\u00024\r\tQAH]3qK\u0006$X\r\u001a \u0011\u0005U2D\u0002\u0001\u0003\u0006o\u0019\u0012\r\u0001\u000f\u0002\u0002\u0003F\u0011\u0011(\u000f\t\u0003\u0017iJ!a\u000f\u0004\u0003\u0007\u0005s\u0017\u0010\u0003\u0004>E\t%\tAP\u0001\bk:\f\u0007\u000f\u001d7z)\tIt\bC\u0003Ay\u0001\u0007\u0011(A\u0005tGJ,H/\u001b8fK\u001e)!\t\u0006E\u0001\u0007\u0006\t\u0011\u000f\u0005\u0002E\u000b6\tACB\u0003G)!\u0005qIA\u0001r'\r)%\u0002\u0013\t\u0003\t\nBQ!H#\u0005\u0002)#\u0012aQ\u0004\u0006\u0019RA\t!T\u0001\u0003iF\u0004\"\u0001\u0012(\u0007\u000b=#\u0002\u0012\u0001)\u0003\u0005Q\f8c\u0001(\u000b\u0011\")QD\u0014C\u0001%R\tQjB\u0003U)!\u0005Q+\u0001\u0002dcB\u0011AI\u0016\u0004\u0006/RA\t\u0001\u0017\u0002\u0003GF\u001c2A\u0016\u0006I\u0011\u0015ib\u000b\"\u0001[)\u0005)v!\u0002/\u0015\u0011\u0003i\u0016A\u00019r!\t!eLB\u0003`)!\u0005\u0001M\u0001\u0002qcN\u0019aL\u0003%\t\u000buqF\u0011\u00012\u0015\u0003u;Q\u0001\u001a\u000b\t\u0002\u0015\f!AZ9\u0011\u0005\u00113g!B4\u0015\u0011\u0003A'A\u00014r'\r1'\u0002\u0013\u0005\u0006;\u0019$\tA\u001b\u000b\u0002K\"9A\u000eAA\u0001\n\u0007i\u0017AC)vCNL\u0017/^8uKR\u0011qD\u001c\u0005\u00061-\u0004\r!\u0007\t\u0003aFl\u0011AA\u0005\u0003e\n\u0011\u0001\"\u00168jm\u0016\u00148/\u001a")
public interface Quasiquotes {
    public Quasiquote Quasiquote(StringContext var1);

    public static class Quasiquote {
        private volatile Quasiquotes$Quasiquote$q$ q$module;
        private volatile Quasiquotes$Quasiquote$tq$ tq$module;
        private volatile Quasiquotes$Quasiquote$cq$ cq$module;
        private volatile Quasiquotes$Quasiquote$pq$ pq$module;
        private volatile Quasiquotes$Quasiquote$fq$ fq$module;
        public final /* synthetic */ Universe $outer;

        private Quasiquotes$Quasiquote$q$ q$lzycompute() {
            Quasiquote quasiquote = this;
            synchronized (quasiquote) {
                if (this.q$module == null) {
                    this.q$module = new Quasiquotes$Quasiquote$q$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.q$module;
            }
        }

        private Quasiquotes$Quasiquote$tq$ tq$lzycompute() {
            Quasiquote quasiquote = this;
            synchronized (quasiquote) {
                if (this.tq$module == null) {
                    this.tq$module = new Quasiquotes$Quasiquote$tq$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.tq$module;
            }
        }

        private Quasiquotes$Quasiquote$cq$ cq$lzycompute() {
            Quasiquote quasiquote = this;
            synchronized (quasiquote) {
                if (this.cq$module == null) {
                    this.cq$module = new Quasiquotes$Quasiquote$cq$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.cq$module;
            }
        }

        private Quasiquotes$Quasiquote$pq$ pq$lzycompute() {
            Quasiquote quasiquote = this;
            synchronized (quasiquote) {
                if (this.pq$module == null) {
                    this.pq$module = new Quasiquotes$Quasiquote$pq$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.pq$module;
            }
        }

        private Quasiquotes$Quasiquote$fq$ fq$lzycompute() {
            Quasiquote quasiquote = this;
            synchronized (quasiquote) {
                if (this.fq$module == null) {
                    this.fq$module = new Quasiquotes$Quasiquote$fq$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.fq$module;
            }
        }

        public Quasiquotes$Quasiquote$q$ q() {
            return this.q$module == null ? this.q$lzycompute() : this.q$module;
        }

        public Quasiquotes$Quasiquote$tq$ tq() {
            return this.tq$module == null ? this.tq$lzycompute() : this.tq$module;
        }

        public Quasiquotes$Quasiquote$cq$ cq() {
            return this.cq$module == null ? this.cq$lzycompute() : this.cq$module;
        }

        public Quasiquotes$Quasiquote$pq$ pq() {
            return this.pq$module == null ? this.pq$lzycompute() : this.pq$module;
        }

        public Quasiquotes$Quasiquote$fq$ fq() {
            return this.fq$module == null ? this.fq$lzycompute() : this.fq$module;
        }

        public /* synthetic */ Universe scala$reflect$api$Quasiquotes$Quasiquote$$$outer() {
            return this.$outer;
        }

        public Quasiquote(Universe $outer, StringContext ctx) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }

        public interface api {
            public /* synthetic */ Quasiquote scala$reflect$api$Quasiquotes$Quasiquote$api$$$outer();
        }
    }
}

