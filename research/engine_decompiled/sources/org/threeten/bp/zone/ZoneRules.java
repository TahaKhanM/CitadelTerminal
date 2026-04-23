/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.zone;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.zone.StandardZoneRules;
import org.threeten.bp.zone.ZoneOffsetTransition;
import org.threeten.bp.zone.ZoneOffsetTransitionRule;

public abstract class ZoneRules {
    public static ZoneRules of(ZoneOffset baseStandardOffset, ZoneOffset baseWallOffset, List<ZoneOffsetTransition> standardOffsetTransitionList, List<ZoneOffsetTransition> transitionList, List<ZoneOffsetTransitionRule> lastRules) {
        Jdk8Methods.requireNonNull(baseStandardOffset, "baseStandardOffset");
        Jdk8Methods.requireNonNull(baseWallOffset, "baseWallOffset");
        Jdk8Methods.requireNonNull(standardOffsetTransitionList, "standardOffsetTransitionList");
        Jdk8Methods.requireNonNull(transitionList, "transitionList");
        Jdk8Methods.requireNonNull(lastRules, "lastRules");
        return new StandardZoneRules(baseStandardOffset, baseWallOffset, standardOffsetTransitionList, transitionList, lastRules);
    }

    public static ZoneRules of(ZoneOffset offset) {
        Jdk8Methods.requireNonNull(offset, "offset");
        return new Fixed(offset);
    }

    ZoneRules() {
    }

    public abstract boolean isFixedOffset();

    public abstract ZoneOffset getOffset(Instant var1);

    public abstract ZoneOffset getOffset(LocalDateTime var1);

    public abstract List<ZoneOffset> getValidOffsets(LocalDateTime var1);

    public abstract ZoneOffsetTransition getTransition(LocalDateTime var1);

    public abstract ZoneOffset getStandardOffset(Instant var1);

    public abstract Duration getDaylightSavings(Instant var1);

    public abstract boolean isDaylightSavings(Instant var1);

    public abstract boolean isValidOffset(LocalDateTime var1, ZoneOffset var2);

    public abstract ZoneOffsetTransition nextTransition(Instant var1);

    public abstract ZoneOffsetTransition previousTransition(Instant var1);

    public abstract List<ZoneOffsetTransition> getTransitions();

    public abstract List<ZoneOffsetTransitionRule> getTransitionRules();

    public abstract boolean equals(Object var1);

    public abstract int hashCode();

    static final class Fixed
    extends ZoneRules
    implements Serializable {
        private static final long serialVersionUID = -8733721350312276297L;
        private final ZoneOffset offset;

        Fixed(ZoneOffset offset) {
            this.offset = offset;
        }

        @Override
        public boolean isFixedOffset() {
            return true;
        }

        @Override
        public ZoneOffset getOffset(Instant instant) {
            return this.offset;
        }

        @Override
        public ZoneOffset getOffset(LocalDateTime localDateTime) {
            return this.offset;
        }

        @Override
        public List<ZoneOffset> getValidOffsets(LocalDateTime localDateTime) {
            return Collections.singletonList(this.offset);
        }

        @Override
        public ZoneOffsetTransition getTransition(LocalDateTime localDateTime) {
            return null;
        }

        @Override
        public boolean isValidOffset(LocalDateTime dateTime, ZoneOffset offset) {
            return this.offset.equals(offset);
        }

        @Override
        public ZoneOffset getStandardOffset(Instant instant) {
            return this.offset;
        }

        @Override
        public Duration getDaylightSavings(Instant instant) {
            return Duration.ZERO;
        }

        @Override
        public boolean isDaylightSavings(Instant instant) {
            return false;
        }

        @Override
        public ZoneOffsetTransition nextTransition(Instant instant) {
            return null;
        }

        @Override
        public ZoneOffsetTransition previousTransition(Instant instant) {
            return null;
        }

        @Override
        public List<ZoneOffsetTransition> getTransitions() {
            return Collections.emptyList();
        }

        @Override
        public List<ZoneOffsetTransitionRule> getTransitionRules() {
            return Collections.emptyList();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Fixed) {
                return this.offset.equals(((Fixed)obj).offset);
            }
            if (obj instanceof StandardZoneRules) {
                StandardZoneRules szr = (StandardZoneRules)obj;
                return szr.isFixedOffset() && this.offset.equals(szr.getOffset(Instant.EPOCH));
            }
            return false;
        }

        @Override
        public int hashCode() {
            return 1 ^ 31 + this.offset.hashCode() ^ 1 ^ 31 + this.offset.hashCode() ^ 1;
        }

        public String toString() {
            return "FixedRules:" + this.offset;
        }
    }
}

