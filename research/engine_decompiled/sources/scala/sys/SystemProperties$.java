/*
 * Decompiled with CFR 0.152.
 */
package scala.sys;

import scala.Function0;
import scala.Serializable;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.Map;
import scala.collection.mutable.Map$;
import scala.sys.BooleanProp;
import scala.sys.BooleanProp$;
import scala.sys.Prop;
import scala.sys.SystemProperties;

public final class SystemProperties$ {
    public static final SystemProperties$ MODULE$;
    private Map<String, String> propertyHelp;
    private BooleanProp headless;
    private BooleanProp preferIPv4Stack;
    private BooleanProp preferIPv6Addresses;
    private BooleanProp noTraceSupression;
    private volatile byte bitmap$0;

    static {
        new SystemProperties$();
    }

    private Map propertyHelp$lzycompute() {
        SystemProperties$ systemProperties$ = this;
        synchronized (systemProperties$) {
            if ((byte)(this.bitmap$0 & 1) == 0) {
                this.propertyHelp = (Map)Map$.MODULE$.apply(Nil$.MODULE$);
                this.bitmap$0 = (byte)(this.bitmap$0 | 1);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.propertyHelp;
        }
    }

    private BooleanProp headless$lzycompute() {
        SystemProperties$ systemProperties$ = this;
        synchronized (systemProperties$) {
            if ((byte)(this.bitmap$0 & 2) == 0) {
                this.headless = this.bool("java.awt.headless", "system should not utilize a display device");
                this.bitmap$0 = (byte)(this.bitmap$0 | 2);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.headless;
        }
    }

    private BooleanProp preferIPv4Stack$lzycompute() {
        SystemProperties$ systemProperties$ = this;
        synchronized (systemProperties$) {
            if ((byte)(this.bitmap$0 & 4) == 0) {
                this.preferIPv4Stack = this.bool("java.net.preferIPv4Stack", "system should prefer IPv4 sockets");
                this.bitmap$0 = (byte)(this.bitmap$0 | 4);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.preferIPv4Stack;
        }
    }

    private BooleanProp preferIPv6Addresses$lzycompute() {
        SystemProperties$ systemProperties$ = this;
        synchronized (systemProperties$) {
            if ((byte)(this.bitmap$0 & 8) == 0) {
                this.preferIPv6Addresses = this.bool("java.net.preferIPv6Addresses", "system should prefer IPv6 addresses");
                this.bitmap$0 = (byte)(this.bitmap$0 | 8);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.preferIPv6Addresses;
        }
    }

    private BooleanProp noTraceSupression$lzycompute() {
        SystemProperties$ systemProperties$ = this;
        synchronized (systemProperties$) {
            if ((byte)(this.bitmap$0 & 0x10) == 0) {
                this.noTraceSupression = this.bool("scala.control.noTraceSuppression", "scala should not suppress any stack trace creation");
                this.bitmap$0 = (byte)(this.bitmap$0 | 0x10);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.noTraceSupression;
        }
    }

    public synchronized <T> T exclusively(Function0<T> body2) {
        return body2.apply();
    }

    public SystemProperties$ systemPropertiesToCompanion(SystemProperties p) {
        return this;
    }

    private Map<String, String> propertyHelp() {
        return (byte)(this.bitmap$0 & 1) == 0 ? this.propertyHelp$lzycompute() : this.propertyHelp;
    }

    private <P extends Prop<?>> P addHelp(P p, String helpText) {
        this.propertyHelp().update(p.key(), helpText);
        return p;
    }

    private BooleanProp bool(String key, String helpText) {
        return this.addHelp(key.startsWith("java.") ? BooleanProp$.MODULE$.valueIsTrue(key) : BooleanProp$.MODULE$.keyExists(key), helpText);
    }

    public String help(String key) {
        return this.propertyHelp().getOrElse(key, new Serializable(){
            public static final long serialVersionUID = 0L;

            public final String apply() {
                return "";
            }
        });
    }

    public BooleanProp headless() {
        return (byte)(this.bitmap$0 & 2) == 0 ? this.headless$lzycompute() : this.headless;
    }

    public BooleanProp preferIPv4Stack() {
        return (byte)(this.bitmap$0 & 4) == 0 ? this.preferIPv4Stack$lzycompute() : this.preferIPv4Stack;
    }

    public BooleanProp preferIPv6Addresses() {
        return (byte)(this.bitmap$0 & 8) == 0 ? this.preferIPv6Addresses$lzycompute() : this.preferIPv6Addresses;
    }

    public BooleanProp noTraceSupression() {
        return (byte)(this.bitmap$0 & 0x10) == 0 ? this.noTraceSupression$lzycompute() : this.noTraceSupression;
    }

    private SystemProperties$() {
        MODULE$ = this;
    }
}

