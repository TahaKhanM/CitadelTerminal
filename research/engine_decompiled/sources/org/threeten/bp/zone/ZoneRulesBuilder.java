/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.zone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Month;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.zone.StandardZoneRules;
import org.threeten.bp.zone.ZoneOffsetTransition;
import org.threeten.bp.zone.ZoneOffsetTransitionRule;
import org.threeten.bp.zone.ZoneRules;

class ZoneRulesBuilder {
    private List<TZWindow> windowList = new ArrayList<TZWindow>();
    private Map<Object, Object> deduplicateMap;

    public ZoneRulesBuilder addWindow(ZoneOffset standardOffset, LocalDateTime until2, ZoneOffsetTransitionRule.TimeDefinition untilDefinition) {
        Jdk8Methods.requireNonNull(standardOffset, "standardOffset");
        Jdk8Methods.requireNonNull(until2, "until");
        Jdk8Methods.requireNonNull(untilDefinition, "untilDefinition");
        TZWindow window = new TZWindow(standardOffset, until2, untilDefinition);
        if (this.windowList.size() > 0) {
            TZWindow previous = this.windowList.get(this.windowList.size() - 1);
            window.validateWindowOrder(previous);
        }
        this.windowList.add(window);
        return this;
    }

    public ZoneRulesBuilder addWindowForever(ZoneOffset standardOffset) {
        return this.addWindow(standardOffset, LocalDateTime.MAX, ZoneOffsetTransitionRule.TimeDefinition.WALL);
    }

    public ZoneRulesBuilder setFixedSavingsToWindow(int fixedSavingAmountSecs) {
        if (this.windowList.isEmpty()) {
            throw new IllegalStateException("Must add a window before setting the fixed savings");
        }
        TZWindow window = this.windowList.get(this.windowList.size() - 1);
        window.setFixedSavings(fixedSavingAmountSecs);
        return this;
    }

    public ZoneRulesBuilder addRuleToWindow(LocalDateTime transitionDateTime, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int savingAmountSecs) {
        Jdk8Methods.requireNonNull(transitionDateTime, "transitionDateTime");
        return this.addRuleToWindow(transitionDateTime.getYear(), transitionDateTime.getYear(), transitionDateTime.getMonth(), transitionDateTime.getDayOfMonth(), null, transitionDateTime.toLocalTime(), false, timeDefinition, savingAmountSecs);
    }

    public ZoneRulesBuilder addRuleToWindow(int year, Month month, int dayOfMonthIndicator, LocalTime time, boolean timeEndOfDay, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int savingAmountSecs) {
        return this.addRuleToWindow(year, year, month, dayOfMonthIndicator, null, time, timeEndOfDay, timeDefinition, savingAmountSecs);
    }

    public ZoneRulesBuilder addRuleToWindow(int startYear, int endYear, Month month, int dayOfMonthIndicator, DayOfWeek dayOfWeek, LocalTime time, boolean timeEndOfDay, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int savingAmountSecs) {
        Jdk8Methods.requireNonNull(month, "month");
        Jdk8Methods.requireNonNull(time, "time");
        Jdk8Methods.requireNonNull(timeDefinition, "timeDefinition");
        ChronoField.YEAR.checkValidValue(startYear);
        ChronoField.YEAR.checkValidValue(endYear);
        if (dayOfMonthIndicator < -28 || dayOfMonthIndicator > 31 || dayOfMonthIndicator == 0) {
            throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        }
        if (timeEndOfDay && !time.equals(LocalTime.MIDNIGHT)) {
            throw new IllegalArgumentException("Time must be midnight when end of day flag is true");
        }
        if (this.windowList.isEmpty()) {
            throw new IllegalStateException("Must add a window before adding a rule");
        }
        TZWindow window = this.windowList.get(this.windowList.size() - 1);
        window.addRule(startYear, endYear, month, dayOfMonthIndicator, dayOfWeek, time, timeEndOfDay, timeDefinition, savingAmountSecs);
        return this;
    }

    public ZoneRules toRules(String zoneId) {
        return this.toRules(zoneId, new HashMap<Object, Object>());
    }

    ZoneRules toRules(String zoneId, Map<Object, Object> deduplicateMap) {
        Jdk8Methods.requireNonNull(zoneId, "zoneId");
        this.deduplicateMap = deduplicateMap;
        if (this.windowList.isEmpty()) {
            throw new IllegalStateException("No windows have been added to the builder");
        }
        ArrayList<ZoneOffsetTransition> standardTransitionList = new ArrayList<ZoneOffsetTransition>(4);
        ArrayList<ZoneOffsetTransition> transitionList = new ArrayList<ZoneOffsetTransition>(256);
        ArrayList<ZoneOffsetTransitionRule> lastTransitionRuleList = new ArrayList<ZoneOffsetTransitionRule>(2);
        TZWindow firstWindow = this.windowList.get(0);
        ZoneOffset loopStandardOffset = firstWindow.standardOffset;
        int loopSavings = 0;
        if (firstWindow.fixedSavingAmountSecs != null) {
            loopSavings = firstWindow.fixedSavingAmountSecs;
        }
        ZoneOffset firstWallOffset = this.deduplicate(ZoneOffset.ofTotalSeconds(loopStandardOffset.getTotalSeconds() + loopSavings));
        LocalDateTime loopWindowStart = this.deduplicate(LocalDateTime.of(-999999999, 1, 1, 0, 0));
        ZoneOffset loopWindowOffset = firstWallOffset;
        for (TZWindow window : this.windowList) {
            ZoneOffset effectiveWallOffset;
            window.tidy(loopWindowStart.getYear());
            Integer effectiveSavings = window.fixedSavingAmountSecs;
            if (effectiveSavings == null) {
                TZRule rule;
                ZoneOffsetTransition trans;
                effectiveSavings = 0;
                Iterator i$ = window.ruleList.iterator();
                while (i$.hasNext() && (trans = (rule = (TZRule)i$.next()).toTransition(loopStandardOffset, loopSavings)).toEpochSecond() <= loopWindowStart.toEpochSecond(loopWindowOffset)) {
                    effectiveSavings = rule.savingAmountSecs;
                }
            }
            if (!loopStandardOffset.equals(window.standardOffset)) {
                standardTransitionList.add(this.deduplicate(new ZoneOffsetTransition(LocalDateTime.ofEpochSecond(loopWindowStart.toEpochSecond(loopWindowOffset), 0, loopStandardOffset), loopStandardOffset, window.standardOffset)));
                loopStandardOffset = this.deduplicate(window.standardOffset);
            }
            if (!loopWindowOffset.equals(effectiveWallOffset = this.deduplicate(ZoneOffset.ofTotalSeconds(loopStandardOffset.getTotalSeconds() + effectiveSavings)))) {
                ZoneOffsetTransition trans = this.deduplicate(new ZoneOffsetTransition(loopWindowStart, loopWindowOffset, effectiveWallOffset));
                transitionList.add(trans);
            }
            loopSavings = effectiveSavings;
            for (TZRule rule : window.ruleList) {
                ZoneOffsetTransition trans = this.deduplicate(rule.toTransition(loopStandardOffset, loopSavings));
                if (trans.toEpochSecond() < loopWindowStart.toEpochSecond(loopWindowOffset) || trans.toEpochSecond() >= window.createDateTimeEpochSecond(loopSavings) || trans.getOffsetBefore().equals(trans.getOffsetAfter())) continue;
                transitionList.add(trans);
                loopSavings = rule.savingAmountSecs;
            }
            for (TZRule lastRule : window.lastRuleList) {
                ZoneOffsetTransitionRule transitionRule = this.deduplicate(lastRule.toTransitionRule(loopStandardOffset, loopSavings));
                lastTransitionRuleList.add(transitionRule);
                loopSavings = lastRule.savingAmountSecs;
            }
            loopWindowOffset = this.deduplicate(window.createWallOffset(loopSavings));
            loopWindowStart = this.deduplicate(LocalDateTime.ofEpochSecond(window.createDateTimeEpochSecond(loopSavings), 0, loopWindowOffset));
        }
        return new StandardZoneRules(firstWindow.standardOffset, firstWallOffset, standardTransitionList, transitionList, lastTransitionRuleList);
    }

    <T> T deduplicate(T object) {
        if (!this.deduplicateMap.containsKey(object)) {
            this.deduplicateMap.put(object, object);
        }
        return (T)this.deduplicateMap.get(object);
    }

    class TZRule
    implements Comparable<TZRule> {
        private int year;
        private Month month;
        private int dayOfMonthIndicator;
        private DayOfWeek dayOfWeek;
        private LocalTime time;
        private boolean timeEndOfDay;
        private ZoneOffsetTransitionRule.TimeDefinition timeDefinition;
        private int savingAmountSecs;

        TZRule(int year, Month month, int dayOfMonthIndicator, DayOfWeek dayOfWeek, LocalTime time, boolean timeEndOfDay, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int savingAfterSecs) {
            this.year = year;
            this.month = month;
            this.dayOfMonthIndicator = dayOfMonthIndicator;
            this.dayOfWeek = dayOfWeek;
            this.time = time;
            this.timeEndOfDay = timeEndOfDay;
            this.timeDefinition = timeDefinition;
            this.savingAmountSecs = savingAfterSecs;
        }

        ZoneOffsetTransition toTransition(ZoneOffset standardOffset, int savingsBeforeSecs) {
            LocalDate date = this.toLocalDate();
            date = ZoneRulesBuilder.this.deduplicate(date);
            LocalDateTime ldt = ZoneRulesBuilder.this.deduplicate(LocalDateTime.of(date, this.time));
            ZoneOffset wallOffset = ZoneRulesBuilder.this.deduplicate(ZoneOffset.ofTotalSeconds(standardOffset.getTotalSeconds() + savingsBeforeSecs));
            LocalDateTime dt = ZoneRulesBuilder.this.deduplicate(this.timeDefinition.createDateTime(ldt, standardOffset, wallOffset));
            ZoneOffset offsetAfter = ZoneRulesBuilder.this.deduplicate(ZoneOffset.ofTotalSeconds(standardOffset.getTotalSeconds() + this.savingAmountSecs));
            return new ZoneOffsetTransition(dt, wallOffset, offsetAfter);
        }

        ZoneOffsetTransitionRule toTransitionRule(ZoneOffset standardOffset, int savingsBeforeSecs) {
            if (this.dayOfMonthIndicator < 0 && this.month != Month.FEBRUARY) {
                this.dayOfMonthIndicator = this.month.maxLength() - 6;
            }
            if (this.timeEndOfDay && this.dayOfMonthIndicator > 0 && !(this.dayOfMonthIndicator == 28 && this.month == Month.FEBRUARY)) {
                LocalDate date = LocalDate.of(2004, this.month, this.dayOfMonthIndicator).plusDays(1L);
                this.month = date.getMonth();
                this.dayOfMonthIndicator = date.getDayOfMonth();
                if (this.dayOfWeek != null) {
                    this.dayOfWeek = this.dayOfWeek.plus(1L);
                }
                this.timeEndOfDay = false;
            }
            ZoneOffsetTransition trans = this.toTransition(standardOffset, savingsBeforeSecs);
            return new ZoneOffsetTransitionRule(this.month, this.dayOfMonthIndicator, this.dayOfWeek, this.time, this.timeEndOfDay, this.timeDefinition, standardOffset, trans.getOffsetBefore(), trans.getOffsetAfter());
        }

        @Override
        public int compareTo(TZRule other) {
            int cmp = this.year - other.year;
            int n = cmp = cmp == 0 ? this.month.compareTo(other.month) : cmp;
            if (cmp == 0) {
                LocalDate thisDate = this.toLocalDate();
                LocalDate otherDate = other.toLocalDate();
                cmp = thisDate.compareTo(otherDate);
            }
            cmp = cmp == 0 ? this.time.compareTo(other.time) : cmp;
            return cmp;
        }

        private LocalDate toLocalDate() {
            LocalDate date;
            if (this.dayOfMonthIndicator < 0) {
                int monthLen = this.month.length(IsoChronology.INSTANCE.isLeapYear(this.year));
                date = LocalDate.of(this.year, this.month, monthLen + 1 + this.dayOfMonthIndicator);
                if (this.dayOfWeek != null) {
                    date = date.with(TemporalAdjusters.previousOrSame(this.dayOfWeek));
                }
            } else {
                date = LocalDate.of(this.year, this.month, this.dayOfMonthIndicator);
                if (this.dayOfWeek != null) {
                    date = date.with(TemporalAdjusters.nextOrSame(this.dayOfWeek));
                }
            }
            if (this.timeEndOfDay) {
                date = date.plusDays(1L);
            }
            return date;
        }
    }

    class TZWindow {
        private final ZoneOffset standardOffset;
        private final LocalDateTime windowEnd;
        private final ZoneOffsetTransitionRule.TimeDefinition timeDefinition;
        private Integer fixedSavingAmountSecs;
        private List<TZRule> ruleList = new ArrayList<TZRule>();
        private int maxLastRuleStartYear = -999999999;
        private List<TZRule> lastRuleList = new ArrayList<TZRule>();

        TZWindow(ZoneOffset standardOffset, LocalDateTime windowEnd, ZoneOffsetTransitionRule.TimeDefinition timeDefinition) {
            this.windowEnd = windowEnd;
            this.timeDefinition = timeDefinition;
            this.standardOffset = standardOffset;
        }

        void setFixedSavings(int fixedSavingAmount) {
            if (this.ruleList.size() > 0 || this.lastRuleList.size() > 0) {
                throw new IllegalStateException("Window has DST rules, so cannot have fixed savings");
            }
            this.fixedSavingAmountSecs = fixedSavingAmount;
        }

        void addRule(int startYear, int endYear, Month month, int dayOfMonthIndicator, DayOfWeek dayOfWeek, LocalTime time, boolean timeEndOfDay, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int savingAmountSecs) {
            if (this.fixedSavingAmountSecs != null) {
                throw new IllegalStateException("Window has a fixed DST saving, so cannot have DST rules");
            }
            if (this.ruleList.size() >= 2000) {
                throw new IllegalStateException("Window has reached the maximum number of allowed rules");
            }
            boolean lastRule = false;
            if (endYear == 999999999) {
                lastRule = true;
                endYear = startYear;
            }
            for (int year = startYear; year <= endYear; ++year) {
                TZRule rule = new TZRule(year, month, dayOfMonthIndicator, dayOfWeek, time, timeEndOfDay, timeDefinition, savingAmountSecs);
                if (lastRule) {
                    this.lastRuleList.add(rule);
                    this.maxLastRuleStartYear = Math.max(startYear, this.maxLastRuleStartYear);
                    continue;
                }
                this.ruleList.add(rule);
            }
        }

        void validateWindowOrder(TZWindow previous) {
            if (this.windowEnd.isBefore(previous.windowEnd)) {
                throw new IllegalStateException("Windows must be added in date-time order: " + this.windowEnd + " < " + previous.windowEnd);
            }
        }

        void tidy(int windowStartYear) {
            if (this.lastRuleList.size() == 1) {
                throw new IllegalStateException("Cannot have only one rule defined as being forever");
            }
            if (this.windowEnd.equals(LocalDateTime.MAX)) {
                this.maxLastRuleStartYear = Math.max(this.maxLastRuleStartYear, windowStartYear) + 1;
                for (TZRule lastRule : this.lastRuleList) {
                    this.addRule(lastRule.year, this.maxLastRuleStartYear, lastRule.month, lastRule.dayOfMonthIndicator, lastRule.dayOfWeek, lastRule.time, lastRule.timeEndOfDay, lastRule.timeDefinition, lastRule.savingAmountSecs);
                    lastRule.year = this.maxLastRuleStartYear + 1;
                }
                if (this.maxLastRuleStartYear == 999999999) {
                    this.lastRuleList.clear();
                } else {
                    ++this.maxLastRuleStartYear;
                }
            } else {
                int endYear = this.windowEnd.getYear();
                for (TZRule lastRule : this.lastRuleList) {
                    this.addRule(lastRule.year, endYear + 1, lastRule.month, lastRule.dayOfMonthIndicator, lastRule.dayOfWeek, lastRule.time, lastRule.timeEndOfDay, lastRule.timeDefinition, lastRule.savingAmountSecs);
                }
                this.lastRuleList.clear();
                this.maxLastRuleStartYear = 999999999;
            }
            Collections.sort(this.ruleList);
            Collections.sort(this.lastRuleList);
            if (this.ruleList.size() == 0 && this.fixedSavingAmountSecs == null) {
                this.fixedSavingAmountSecs = 0;
            }
        }

        boolean isSingleWindowStandardOffset() {
            return this.windowEnd.equals(LocalDateTime.MAX) && this.timeDefinition == ZoneOffsetTransitionRule.TimeDefinition.WALL && this.fixedSavingAmountSecs == null && this.lastRuleList.isEmpty() && this.ruleList.isEmpty();
        }

        ZoneOffset createWallOffset(int savingsSecs) {
            return ZoneOffset.ofTotalSeconds(this.standardOffset.getTotalSeconds() + savingsSecs);
        }

        long createDateTimeEpochSecond(int savingsSecs) {
            ZoneOffset wallOffset = this.createWallOffset(savingsSecs);
            LocalDateTime ldt = this.timeDefinition.createDateTime(this.windowEnd, this.standardOffset, wallOffset);
            return ldt.toEpochSecond(wallOffset);
        }
    }
}

