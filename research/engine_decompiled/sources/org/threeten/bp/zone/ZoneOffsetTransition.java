/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.zone;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.zone.Ser;

public final class ZoneOffsetTransition
implements Comparable<ZoneOffsetTransition>,
Serializable {
    private static final long serialVersionUID = -6946044323557704546L;
    private final LocalDateTime transition;
    private final ZoneOffset offsetBefore;
    private final ZoneOffset offsetAfter;

    public static ZoneOffsetTransition of(LocalDateTime transition, ZoneOffset offsetBefore, ZoneOffset offsetAfter) {
        Jdk8Methods.requireNonNull(transition, "transition");
        Jdk8Methods.requireNonNull(offsetBefore, "offsetBefore");
        Jdk8Methods.requireNonNull(offsetAfter, "offsetAfter");
        if (offsetBefore.equals(offsetAfter)) {
            throw new IllegalArgumentException("Offsets must not be equal");
        }
        if (transition.getNano() != 0) {
            throw new IllegalArgumentException("Nano-of-second must be zero");
        }
        return new ZoneOffsetTransition(transition, offsetBefore, offsetAfter);
    }

    ZoneOffsetTransition(LocalDateTime transition, ZoneOffset offsetBefore, ZoneOffset offsetAfter) {
        this.transition = transition;
        this.offsetBefore = offsetBefore;
        this.offsetAfter = offsetAfter;
    }

    ZoneOffsetTransition(long epochSecond, ZoneOffset offsetBefore, ZoneOffset offsetAfter) {
        this.transition = LocalDateTime.ofEpochSecond(epochSecond, 0, offsetBefore);
        this.offsetBefore = offsetBefore;
        this.offsetAfter = offsetAfter;
    }

    private Object writeReplace() {
        return new Ser(2, this);
    }

    void writeExternal(DataOutput out) throws IOException {
        Ser.writeEpochSec(this.toEpochSecond(), out);
        Ser.writeOffset(this.offsetBefore, out);
        Ser.writeOffset(this.offsetAfter, out);
    }

    static ZoneOffsetTransition readExternal(DataInput in) throws IOException {
        ZoneOffset after;
        long epochSecond = Ser.readEpochSec(in);
        ZoneOffset before = Ser.readOffset(in);
        if (before.equals(after = Ser.readOffset(in))) {
            throw new IllegalArgumentException("Offsets must not be equal");
        }
        return new ZoneOffsetTransition(epochSecond, before, after);
    }

    public Instant getInstant() {
        return this.transition.toInstant(this.offsetBefore);
    }

    public long toEpochSecond() {
        return this.transition.toEpochSecond(this.offsetBefore);
    }

    public LocalDateTime getDateTimeBefore() {
        return this.transition;
    }

    public LocalDateTime getDateTimeAfter() {
        return this.transition.plusSeconds(this.getDurationSeconds());
    }

    public ZoneOffset getOffsetBefore() {
        return this.offsetBefore;
    }

    public ZoneOffset getOffsetAfter() {
        return this.offsetAfter;
    }

    public Duration getDuration() {
        return Duration.ofSeconds(this.getDurationSeconds());
    }

    private int getDurationSeconds() {
        return this.getOffsetAfter().getTotalSeconds() - this.getOffsetBefore().getTotalSeconds();
    }

    public boolean isGap() {
        return this.getOffsetAfter().getTotalSeconds() > this.getOffsetBefore().getTotalSeconds();
    }

    public boolean isOverlap() {
        return this.getOffsetAfter().getTotalSeconds() < this.getOffsetBefore().getTotalSeconds();
    }

    public boolean isValidOffset(ZoneOffset offset) {
        return this.isGap() ? false : this.getOffsetBefore().equals(offset) || this.getOffsetAfter().equals(offset);
    }

    List<ZoneOffset> getValidOffsets() {
        if (this.isGap()) {
            return Collections.emptyList();
        }
        return Arrays.asList(this.getOffsetBefore(), this.getOffsetAfter());
    }

    @Override
    public int compareTo(ZoneOffsetTransition transition) {
        return this.getInstant().compareTo(transition.getInstant());
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ZoneOffsetTransition) {
            ZoneOffsetTransition d = (ZoneOffsetTransition)other;
            return this.transition.equals(d.transition) && this.offsetBefore.equals(d.offsetBefore) && this.offsetAfter.equals(d.offsetAfter);
        }
        return false;
    }

    public int hashCode() {
        return this.transition.hashCode() ^ this.offsetBefore.hashCode() ^ Integer.rotateLeft(this.offsetAfter.hashCode(), 16);
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("Transition[").append(this.isGap() ? "Gap" : "Overlap").append(" at ").append(this.transition).append(this.offsetBefore).append(" to ").append(this.offsetAfter).append(']');
        return buf.toString();
    }
}

