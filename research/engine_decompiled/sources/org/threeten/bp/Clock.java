/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp;

import java.io.Serializable;
import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.jdk8.Jdk8Methods;

public abstract class Clock {
    public static Clock systemUTC() {
        return new SystemClock(ZoneOffset.UTC);
    }

    public static Clock systemDefaultZone() {
        return new SystemClock(ZoneId.systemDefault());
    }

    public static Clock system(ZoneId zone) {
        Jdk8Methods.requireNonNull(zone, "zone");
        return new SystemClock(zone);
    }

    public static Clock tickSeconds(ZoneId zone) {
        return new TickClock(Clock.system(zone), 1000000000L);
    }

    public static Clock tickMinutes(ZoneId zone) {
        return new TickClock(Clock.system(zone), 60000000000L);
    }

    public static Clock tick(Clock baseClock, Duration tickDuration) {
        Jdk8Methods.requireNonNull(baseClock, "baseClock");
        Jdk8Methods.requireNonNull(tickDuration, "tickDuration");
        if (tickDuration.isNegative()) {
            throw new IllegalArgumentException("Tick duration must not be negative");
        }
        long tickNanos = tickDuration.toNanos();
        if (tickNanos % 1000000L != 0L && 1000000000L % tickNanos != 0L) {
            throw new IllegalArgumentException("Invalid tick duration");
        }
        if (tickNanos <= 1L) {
            return baseClock;
        }
        return new TickClock(baseClock, tickNanos);
    }

    public static Clock fixed(Instant fixedInstant, ZoneId zone) {
        Jdk8Methods.requireNonNull(fixedInstant, "fixedInstant");
        Jdk8Methods.requireNonNull(zone, "zone");
        return new FixedClock(fixedInstant, zone);
    }

    public static Clock offset(Clock baseClock, Duration offsetDuration) {
        Jdk8Methods.requireNonNull(baseClock, "baseClock");
        Jdk8Methods.requireNonNull(offsetDuration, "offsetDuration");
        if (offsetDuration.equals(Duration.ZERO)) {
            return baseClock;
        }
        return new OffsetClock(baseClock, offsetDuration);
    }

    protected Clock() {
    }

    public abstract ZoneId getZone();

    public abstract Clock withZone(ZoneId var1);

    public long millis() {
        return this.instant().toEpochMilli();
    }

    public abstract Instant instant();

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    static final class TickClock
    extends Clock
    implements Serializable {
        private static final long serialVersionUID = 6504659149906368850L;
        private final Clock baseClock;
        private final long tickNanos;

        TickClock(Clock baseClock, long tickNanos) {
            this.baseClock = baseClock;
            this.tickNanos = tickNanos;
        }

        @Override
        public ZoneId getZone() {
            return this.baseClock.getZone();
        }

        @Override
        public Clock withZone(ZoneId zone) {
            if (zone.equals(this.baseClock.getZone())) {
                return this;
            }
            return new TickClock(this.baseClock.withZone(zone), this.tickNanos);
        }

        @Override
        public long millis() {
            long millis = this.baseClock.millis();
            return millis - Jdk8Methods.floorMod(millis, this.tickNanos / 1000000L);
        }

        @Override
        public Instant instant() {
            if (this.tickNanos % 1000000L == 0L) {
                long millis = this.baseClock.millis();
                return Instant.ofEpochMilli(millis - Jdk8Methods.floorMod(millis, this.tickNanos / 1000000L));
            }
            Instant instant = this.baseClock.instant();
            long nanos = instant.getNano();
            long adjust = Jdk8Methods.floorMod(nanos, this.tickNanos);
            return instant.minusNanos(adjust);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof TickClock) {
                TickClock other = (TickClock)obj;
                return this.baseClock.equals(other.baseClock) && this.tickNanos == other.tickNanos;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.baseClock.hashCode() ^ (int)(this.tickNanos ^ this.tickNanos >>> 32);
        }

        public String toString() {
            return "TickClock[" + this.baseClock + "," + Duration.ofNanos(this.tickNanos) + "]";
        }
    }

    static final class OffsetClock
    extends Clock
    implements Serializable {
        private static final long serialVersionUID = 2007484719125426256L;
        private final Clock baseClock;
        private final Duration offset;

        OffsetClock(Clock baseClock, Duration offset) {
            this.baseClock = baseClock;
            this.offset = offset;
        }

        @Override
        public ZoneId getZone() {
            return this.baseClock.getZone();
        }

        @Override
        public Clock withZone(ZoneId zone) {
            if (zone.equals(this.baseClock.getZone())) {
                return this;
            }
            return new OffsetClock(this.baseClock.withZone(zone), this.offset);
        }

        @Override
        public long millis() {
            return Jdk8Methods.safeAdd(this.baseClock.millis(), this.offset.toMillis());
        }

        @Override
        public Instant instant() {
            return this.baseClock.instant().plus(this.offset);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof OffsetClock) {
                OffsetClock other = (OffsetClock)obj;
                return this.baseClock.equals(other.baseClock) && this.offset.equals(other.offset);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.baseClock.hashCode() ^ this.offset.hashCode();
        }

        public String toString() {
            return "OffsetClock[" + this.baseClock + "," + this.offset + "]";
        }
    }

    static final class FixedClock
    extends Clock
    implements Serializable {
        private static final long serialVersionUID = 7430389292664866958L;
        private final Instant instant;
        private final ZoneId zone;

        FixedClock(Instant fixedInstant, ZoneId zone) {
            this.instant = fixedInstant;
            this.zone = zone;
        }

        @Override
        public ZoneId getZone() {
            return this.zone;
        }

        @Override
        public Clock withZone(ZoneId zone) {
            if (zone.equals(this.zone)) {
                return this;
            }
            return new FixedClock(this.instant, zone);
        }

        @Override
        public long millis() {
            return this.instant.toEpochMilli();
        }

        @Override
        public Instant instant() {
            return this.instant;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof FixedClock) {
                FixedClock other = (FixedClock)obj;
                return this.instant.equals(other.instant) && this.zone.equals(other.zone);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.instant.hashCode() ^ this.zone.hashCode();
        }

        public String toString() {
            return "FixedClock[" + this.instant + "," + this.zone + "]";
        }
    }

    static final class SystemClock
    extends Clock
    implements Serializable {
        private static final long serialVersionUID = 6740630888130243051L;
        private final ZoneId zone;

        SystemClock(ZoneId zone) {
            this.zone = zone;
        }

        @Override
        public ZoneId getZone() {
            return this.zone;
        }

        @Override
        public Clock withZone(ZoneId zone) {
            if (zone.equals(this.zone)) {
                return this;
            }
            return new SystemClock(zone);
        }

        @Override
        public long millis() {
            return System.currentTimeMillis();
        }

        @Override
        public Instant instant() {
            return Instant.ofEpochMilli(this.millis());
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof SystemClock) {
                return this.zone.equals(((SystemClock)obj).zone);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.zone.hashCode() + 1;
        }

        public String toString() {
            return "SystemClock[" + this.zone + "]";
        }
    }
}

