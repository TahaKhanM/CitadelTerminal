/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.zone;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.zone.Ser;
import org.threeten.bp.zone.ZoneOffsetTransition;
import org.threeten.bp.zone.ZoneOffsetTransitionRule;
import org.threeten.bp.zone.ZoneRules;

final class StandardZoneRules
extends ZoneRules
implements Serializable {
    private static final long serialVersionUID = 3044319355680032515L;
    private static final int LAST_CACHED_YEAR = 2100;
    private final long[] standardTransitions;
    private final ZoneOffset[] standardOffsets;
    private final long[] savingsInstantTransitions;
    private final LocalDateTime[] savingsLocalTransitions;
    private final ZoneOffset[] wallOffsets;
    private final ZoneOffsetTransitionRule[] lastRules;
    private final ConcurrentMap<Integer, ZoneOffsetTransition[]> lastRulesCache = new ConcurrentHashMap<Integer, ZoneOffsetTransition[]>();

    StandardZoneRules(ZoneOffset baseStandardOffset, ZoneOffset baseWallOffset, List<ZoneOffsetTransition> standardOffsetTransitionList, List<ZoneOffsetTransition> transitionList, List<ZoneOffsetTransitionRule> lastRules) {
        this.standardTransitions = new long[standardOffsetTransitionList.size()];
        this.standardOffsets = new ZoneOffset[standardOffsetTransitionList.size() + 1];
        this.standardOffsets[0] = baseStandardOffset;
        for (int i = 0; i < standardOffsetTransitionList.size(); ++i) {
            this.standardTransitions[i] = standardOffsetTransitionList.get(i).toEpochSecond();
            this.standardOffsets[i + 1] = standardOffsetTransitionList.get(i).getOffsetAfter();
        }
        ArrayList<LocalDateTime> localTransitionList = new ArrayList<LocalDateTime>();
        ArrayList<ZoneOffset> localTransitionOffsetList = new ArrayList<ZoneOffset>();
        localTransitionOffsetList.add(baseWallOffset);
        for (ZoneOffsetTransition trans : transitionList) {
            if (trans.isGap()) {
                localTransitionList.add(trans.getDateTimeBefore());
                localTransitionList.add(trans.getDateTimeAfter());
            } else {
                localTransitionList.add(trans.getDateTimeAfter());
                localTransitionList.add(trans.getDateTimeBefore());
            }
            localTransitionOffsetList.add(trans.getOffsetAfter());
        }
        this.savingsLocalTransitions = localTransitionList.toArray(new LocalDateTime[localTransitionList.size()]);
        this.wallOffsets = localTransitionOffsetList.toArray(new ZoneOffset[localTransitionOffsetList.size()]);
        this.savingsInstantTransitions = new long[transitionList.size()];
        for (int i = 0; i < transitionList.size(); ++i) {
            this.savingsInstantTransitions[i] = transitionList.get(i).getInstant().getEpochSecond();
        }
        if (lastRules.size() > 15) {
            throw new IllegalArgumentException("Too many transition rules");
        }
        this.lastRules = lastRules.toArray(new ZoneOffsetTransitionRule[lastRules.size()]);
    }

    private StandardZoneRules(long[] standardTransitions, ZoneOffset[] standardOffsets, long[] savingsInstantTransitions, ZoneOffset[] wallOffsets, ZoneOffsetTransitionRule[] lastRules) {
        this.standardTransitions = standardTransitions;
        this.standardOffsets = standardOffsets;
        this.savingsInstantTransitions = savingsInstantTransitions;
        this.wallOffsets = wallOffsets;
        this.lastRules = lastRules;
        ArrayList<LocalDateTime> localTransitionList = new ArrayList<LocalDateTime>();
        for (int i = 0; i < savingsInstantTransitions.length; ++i) {
            ZoneOffset before = wallOffsets[i];
            ZoneOffset after = wallOffsets[i + 1];
            ZoneOffsetTransition trans = new ZoneOffsetTransition(savingsInstantTransitions[i], before, after);
            if (trans.isGap()) {
                localTransitionList.add(trans.getDateTimeBefore());
                localTransitionList.add(trans.getDateTimeAfter());
                continue;
            }
            localTransitionList.add(trans.getDateTimeAfter());
            localTransitionList.add(trans.getDateTimeBefore());
        }
        this.savingsLocalTransitions = localTransitionList.toArray(new LocalDateTime[localTransitionList.size()]);
    }

    private Object writeReplace() {
        return new Ser(1, this);
    }

    void writeExternal(DataOutput out) throws IOException {
        out.writeInt(this.standardTransitions.length);
        for (long trans : this.standardTransitions) {
            Ser.writeEpochSec(trans, out);
        }
        for (ZoneOffset offset : this.standardOffsets) {
            Ser.writeOffset(offset, out);
        }
        out.writeInt(this.savingsInstantTransitions.length);
        for (long trans : this.savingsInstantTransitions) {
            Ser.writeEpochSec(trans, out);
        }
        for (ZoneOffset offset : this.wallOffsets) {
            Ser.writeOffset(offset, out);
        }
        out.writeByte(this.lastRules.length);
        for (ZoneOffsetTransitionRule rule : this.lastRules) {
            rule.writeExternal(out);
        }
    }

    static StandardZoneRules readExternal(DataInput in) throws IOException, ClassNotFoundException {
        int stdSize = in.readInt();
        long[] stdTrans = new long[stdSize];
        for (int i = 0; i < stdSize; ++i) {
            stdTrans[i] = Ser.readEpochSec(in);
        }
        ZoneOffset[] stdOffsets = new ZoneOffset[stdSize + 1];
        for (int i = 0; i < stdOffsets.length; ++i) {
            stdOffsets[i] = Ser.readOffset(in);
        }
        int savSize = in.readInt();
        long[] savTrans = new long[savSize];
        for (int i = 0; i < savSize; ++i) {
            savTrans[i] = Ser.readEpochSec(in);
        }
        ZoneOffset[] savOffsets = new ZoneOffset[savSize + 1];
        for (int i = 0; i < savOffsets.length; ++i) {
            savOffsets[i] = Ser.readOffset(in);
        }
        int ruleSize = in.readByte();
        ZoneOffsetTransitionRule[] rules = new ZoneOffsetTransitionRule[ruleSize];
        for (int i = 0; i < ruleSize; ++i) {
            rules[i] = ZoneOffsetTransitionRule.readExternal(in);
        }
        return new StandardZoneRules(stdTrans, stdOffsets, savTrans, savOffsets, rules);
    }

    @Override
    public boolean isFixedOffset() {
        return this.savingsInstantTransitions.length == 0;
    }

    @Override
    public ZoneOffset getOffset(Instant instant) {
        long epochSec = instant.getEpochSecond();
        if (this.lastRules.length > 0 && epochSec > this.savingsInstantTransitions[this.savingsInstantTransitions.length - 1]) {
            int year = this.findYear(epochSec, this.wallOffsets[this.wallOffsets.length - 1]);
            ZoneOffsetTransition[] transArray = this.findTransitionArray(year);
            ZoneOffsetTransition trans = null;
            for (int i = 0; i < transArray.length; ++i) {
                trans = transArray[i];
                if (epochSec >= trans.toEpochSecond()) continue;
                return trans.getOffsetBefore();
            }
            return trans.getOffsetAfter();
        }
        int index = Arrays.binarySearch(this.savingsInstantTransitions, epochSec);
        if (index < 0) {
            index = -index - 2;
        }
        return this.wallOffsets[index + 1];
    }

    @Override
    public ZoneOffset getOffset(LocalDateTime localDateTime) {
        Object info2 = this.getOffsetInfo(localDateTime);
        if (info2 instanceof ZoneOffsetTransition) {
            return ((ZoneOffsetTransition)info2).getOffsetBefore();
        }
        return (ZoneOffset)info2;
    }

    @Override
    public List<ZoneOffset> getValidOffsets(LocalDateTime localDateTime) {
        Object info2 = this.getOffsetInfo(localDateTime);
        if (info2 instanceof ZoneOffsetTransition) {
            return ((ZoneOffsetTransition)info2).getValidOffsets();
        }
        return Collections.singletonList((ZoneOffset)info2);
    }

    @Override
    public ZoneOffsetTransition getTransition(LocalDateTime localDateTime) {
        Object info2 = this.getOffsetInfo(localDateTime);
        return info2 instanceof ZoneOffsetTransition ? (ZoneOffsetTransition)info2 : null;
    }

    private Object getOffsetInfo(LocalDateTime dt) {
        if (this.lastRules.length > 0 && dt.isAfter(this.savingsLocalTransitions[this.savingsLocalTransitions.length - 1])) {
            ZoneOffsetTransition[] transArray = this.findTransitionArray(dt.getYear());
            Object info2 = null;
            for (ZoneOffsetTransition trans : transArray) {
                info2 = this.findOffsetInfo(dt, trans);
                if (!(info2 instanceof ZoneOffsetTransition) && !info2.equals(trans.getOffsetBefore())) continue;
                return info2;
            }
            return info2;
        }
        int index = Arrays.binarySearch(this.savingsLocalTransitions, dt);
        if (index == -1) {
            return this.wallOffsets[0];
        }
        if (index < 0) {
            index = -index - 2;
        } else if (index < this.savingsLocalTransitions.length - 1 && this.savingsLocalTransitions[index].equals(this.savingsLocalTransitions[index + 1])) {
            ++index;
        }
        if ((index & 1) == 0) {
            LocalDateTime dtBefore = this.savingsLocalTransitions[index];
            LocalDateTime dtAfter = this.savingsLocalTransitions[index + 1];
            ZoneOffset offsetBefore = this.wallOffsets[index / 2];
            ZoneOffset offsetAfter = this.wallOffsets[index / 2 + 1];
            if (offsetAfter.getTotalSeconds() > offsetBefore.getTotalSeconds()) {
                return new ZoneOffsetTransition(dtBefore, offsetBefore, offsetAfter);
            }
            return new ZoneOffsetTransition(dtAfter, offsetBefore, offsetAfter);
        }
        return this.wallOffsets[index / 2 + 1];
    }

    private Object findOffsetInfo(LocalDateTime dt, ZoneOffsetTransition trans) {
        LocalDateTime localTransition = trans.getDateTimeBefore();
        if (trans.isGap()) {
            if (dt.isBefore(localTransition)) {
                return trans.getOffsetBefore();
            }
            if (dt.isBefore(trans.getDateTimeAfter())) {
                return trans;
            }
            return trans.getOffsetAfter();
        }
        if (!dt.isBefore(localTransition)) {
            return trans.getOffsetAfter();
        }
        if (dt.isBefore(trans.getDateTimeAfter())) {
            return trans.getOffsetBefore();
        }
        return trans;
    }

    @Override
    public boolean isValidOffset(LocalDateTime localDateTime, ZoneOffset offset) {
        return this.getValidOffsets(localDateTime).contains(offset);
    }

    private ZoneOffsetTransition[] findTransitionArray(int year) {
        Integer yearObj = year;
        ZoneOffsetTransition[] transArray = (ZoneOffsetTransition[])this.lastRulesCache.get(yearObj);
        if (transArray != null) {
            return transArray;
        }
        ZoneOffsetTransitionRule[] ruleArray = this.lastRules;
        transArray = new ZoneOffsetTransition[ruleArray.length];
        for (int i = 0; i < ruleArray.length; ++i) {
            transArray[i] = ruleArray[i].createTransition(year);
        }
        if (year < 2100) {
            this.lastRulesCache.putIfAbsent(yearObj, transArray);
        }
        return transArray;
    }

    @Override
    public ZoneOffset getStandardOffset(Instant instant) {
        long epochSec = instant.getEpochSecond();
        int index = Arrays.binarySearch(this.standardTransitions, epochSec);
        if (index < 0) {
            index = -index - 2;
        }
        return this.standardOffsets[index + 1];
    }

    @Override
    public Duration getDaylightSavings(Instant instant) {
        ZoneOffset standardOffset = this.getStandardOffset(instant);
        ZoneOffset actualOffset = this.getOffset(instant);
        return Duration.ofSeconds(actualOffset.getTotalSeconds() - standardOffset.getTotalSeconds());
    }

    @Override
    public boolean isDaylightSavings(Instant instant) {
        return !this.getStandardOffset(instant).equals(this.getOffset(instant));
    }

    @Override
    public ZoneOffsetTransition nextTransition(Instant instant) {
        if (this.savingsInstantTransitions.length == 0) {
            return null;
        }
        long epochSec = instant.getEpochSecond();
        if (epochSec >= this.savingsInstantTransitions[this.savingsInstantTransitions.length - 1]) {
            ZoneOffsetTransition[] transArray;
            if (this.lastRules.length == 0) {
                return null;
            }
            int year = this.findYear(epochSec, this.wallOffsets[this.wallOffsets.length - 1]);
            for (ZoneOffsetTransition trans : transArray = this.findTransitionArray(year)) {
                if (epochSec >= trans.toEpochSecond()) continue;
                return trans;
            }
            if (year < 999999999) {
                transArray = this.findTransitionArray(year + 1);
                return transArray[0];
            }
            return null;
        }
        int index = Arrays.binarySearch(this.savingsInstantTransitions, epochSec);
        index = index < 0 ? -index - 1 : ++index;
        return new ZoneOffsetTransition(this.savingsInstantTransitions[index], this.wallOffsets[index], this.wallOffsets[index + 1]);
    }

    @Override
    public ZoneOffsetTransition previousTransition(Instant instant) {
        int index;
        if (this.savingsInstantTransitions.length == 0) {
            return null;
        }
        long epochSec = instant.getEpochSecond();
        if (instant.getNano() > 0 && epochSec < Long.MAX_VALUE) {
            ++epochSec;
        }
        long lastHistoric = this.savingsInstantTransitions[this.savingsInstantTransitions.length - 1];
        if (this.lastRules.length > 0 && epochSec > lastHistoric) {
            ZoneOffset lastHistoricOffset = this.wallOffsets[this.wallOffsets.length - 1];
            int year = this.findYear(epochSec, lastHistoricOffset);
            ZoneOffsetTransition[] transArray = this.findTransitionArray(year);
            for (int i = transArray.length - 1; i >= 0; --i) {
                if (epochSec <= transArray[i].toEpochSecond()) continue;
                return transArray[i];
            }
            int lastHistoricYear = this.findYear(lastHistoric, lastHistoricOffset);
            if (--year > lastHistoricYear) {
                transArray = this.findTransitionArray(year);
                return transArray[transArray.length - 1];
            }
        }
        if ((index = Arrays.binarySearch(this.savingsInstantTransitions, epochSec)) < 0) {
            index = -index - 1;
        }
        if (index <= 0) {
            return null;
        }
        return new ZoneOffsetTransition(this.savingsInstantTransitions[index - 1], this.wallOffsets[index - 1], this.wallOffsets[index]);
    }

    private int findYear(long epochSecond, ZoneOffset offset) {
        long localSecond = epochSecond + (long)offset.getTotalSeconds();
        long localEpochDay = Jdk8Methods.floorDiv(localSecond, 86400L);
        return LocalDate.ofEpochDay(localEpochDay).getYear();
    }

    @Override
    public List<ZoneOffsetTransition> getTransitions() {
        ArrayList<ZoneOffsetTransition> list2 = new ArrayList<ZoneOffsetTransition>();
        for (int i = 0; i < this.savingsInstantTransitions.length; ++i) {
            list2.add(new ZoneOffsetTransition(this.savingsInstantTransitions[i], this.wallOffsets[i], this.wallOffsets[i + 1]));
        }
        return Collections.unmodifiableList(list2);
    }

    @Override
    public List<ZoneOffsetTransitionRule> getTransitionRules() {
        return Collections.unmodifiableList(Arrays.asList(this.lastRules));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof StandardZoneRules) {
            StandardZoneRules other = (StandardZoneRules)obj;
            return Arrays.equals(this.standardTransitions, other.standardTransitions) && Arrays.equals(this.standardOffsets, other.standardOffsets) && Arrays.equals(this.savingsInstantTransitions, other.savingsInstantTransitions) && Arrays.equals(this.wallOffsets, other.wallOffsets) && Arrays.equals(this.lastRules, other.lastRules);
        }
        if (obj instanceof ZoneRules.Fixed) {
            return this.isFixedOffset() && this.getOffset(Instant.EPOCH).equals(((ZoneRules.Fixed)obj).getOffset(Instant.EPOCH));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.standardTransitions) ^ Arrays.hashCode(this.standardOffsets) ^ Arrays.hashCode(this.savingsInstantTransitions) ^ Arrays.hashCode(this.wallOffsets) ^ Arrays.hashCode(this.lastRules);
    }

    public String toString() {
        return "StandardZoneRules[currentStandardOffset=" + this.standardOffsets[this.standardOffsets.length - 1] + "]";
    }
}

