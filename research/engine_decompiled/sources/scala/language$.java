/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.languageFeature;
import scala.languageFeature$dynamics$;
import scala.languageFeature$existentials$;
import scala.languageFeature$higherKinds$;
import scala.languageFeature$implicitConversions$;
import scala.languageFeature$postfixOps$;
import scala.languageFeature$reflectiveCalls$;

public final class language$ {
    public static final language$ MODULE$;
    private languageFeature.dynamics dynamics;
    private languageFeature.postfixOps postfixOps;
    private languageFeature.reflectiveCalls reflectiveCalls;
    private languageFeature.implicitConversions implicitConversions;
    private languageFeature.higherKinds higherKinds;
    private languageFeature.existentials existentials;
    private volatile byte bitmap$0;

    static {
        new language$();
    }

    private languageFeature.dynamics dynamics$lzycompute() {
        language$ language$2 = this;
        synchronized (language$2) {
            if ((byte)(this.bitmap$0 & 1) == 0) {
                this.dynamics = languageFeature$dynamics$.MODULE$;
                this.bitmap$0 = (byte)(this.bitmap$0 | 1);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.dynamics;
        }
    }

    private languageFeature.postfixOps postfixOps$lzycompute() {
        language$ language$2 = this;
        synchronized (language$2) {
            if ((byte)(this.bitmap$0 & 2) == 0) {
                this.postfixOps = languageFeature$postfixOps$.MODULE$;
                this.bitmap$0 = (byte)(this.bitmap$0 | 2);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.postfixOps;
        }
    }

    private languageFeature.reflectiveCalls reflectiveCalls$lzycompute() {
        language$ language$2 = this;
        synchronized (language$2) {
            if ((byte)(this.bitmap$0 & 4) == 0) {
                this.reflectiveCalls = languageFeature$reflectiveCalls$.MODULE$;
                this.bitmap$0 = (byte)(this.bitmap$0 | 4);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.reflectiveCalls;
        }
    }

    private languageFeature.implicitConversions implicitConversions$lzycompute() {
        language$ language$2 = this;
        synchronized (language$2) {
            if ((byte)(this.bitmap$0 & 8) == 0) {
                this.implicitConversions = languageFeature$implicitConversions$.MODULE$;
                this.bitmap$0 = (byte)(this.bitmap$0 | 8);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.implicitConversions;
        }
    }

    private languageFeature.higherKinds higherKinds$lzycompute() {
        language$ language$2 = this;
        synchronized (language$2) {
            if ((byte)(this.bitmap$0 & 0x10) == 0) {
                this.higherKinds = languageFeature$higherKinds$.MODULE$;
                this.bitmap$0 = (byte)(this.bitmap$0 | 0x10);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.higherKinds;
        }
    }

    private languageFeature.existentials existentials$lzycompute() {
        language$ language$2 = this;
        synchronized (language$2) {
            if ((byte)(this.bitmap$0 & 0x20) == 0) {
                this.existentials = languageFeature$existentials$.MODULE$;
                this.bitmap$0 = (byte)(this.bitmap$0 | 0x20);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.existentials;
        }
    }

    public languageFeature.dynamics dynamics() {
        return (byte)(this.bitmap$0 & 1) == 0 ? this.dynamics$lzycompute() : this.dynamics;
    }

    public languageFeature.postfixOps postfixOps() {
        return (byte)(this.bitmap$0 & 2) == 0 ? this.postfixOps$lzycompute() : this.postfixOps;
    }

    public languageFeature.reflectiveCalls reflectiveCalls() {
        return (byte)(this.bitmap$0 & 4) == 0 ? this.reflectiveCalls$lzycompute() : this.reflectiveCalls;
    }

    public languageFeature.implicitConversions implicitConversions() {
        return (byte)(this.bitmap$0 & 8) == 0 ? this.implicitConversions$lzycompute() : this.implicitConversions;
    }

    public languageFeature.higherKinds higherKinds() {
        return (byte)(this.bitmap$0 & 0x10) == 0 ? this.higherKinds$lzycompute() : this.higherKinds;
    }

    public languageFeature.existentials existentials() {
        return (byte)(this.bitmap$0 & 0x20) == 0 ? this.existentials$lzycompute() : this.existentials;
    }

    private language$() {
        MODULE$ = this;
    }
}

