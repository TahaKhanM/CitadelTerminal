/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.ChronoLocalDateTimeImpl;
import org.threeten.bp.chrono.ChronoPeriod;
import org.threeten.bp.chrono.ChronoPeriodImpl;
import org.threeten.bp.chrono.ChronoZonedDateTime;
import org.threeten.bp.chrono.ChronoZonedDateTimeImpl;
import org.threeten.bp.chrono.Era;
import org.threeten.bp.chrono.HijrahChronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.chrono.JapaneseChronology;
import org.threeten.bp.chrono.MinguoChronology;
import org.threeten.bp.chrono.Ser;
import org.threeten.bp.chrono.ThaiBuddhistChronology;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;

public abstract class Chronology
implements Comparable<Chronology> {
    public static final TemporalQuery<Chronology> FROM = new TemporalQuery<Chronology>(){

        @Override
        public Chronology queryFrom(TemporalAccessor temporal) {
            return Chronology.from(temporal);
        }
    };
    private static final ConcurrentHashMap<String, Chronology> CHRONOS_BY_ID = new ConcurrentHashMap();
    private static final ConcurrentHashMap<String, Chronology> CHRONOS_BY_TYPE = new ConcurrentHashMap();
    private static final Method LOCALE_METHOD;

    public static Chronology from(TemporalAccessor temporal) {
        Jdk8Methods.requireNonNull(temporal, "temporal");
        Chronology obj = temporal.query(TemporalQueries.chronology());
        return obj != null ? obj : IsoChronology.INSTANCE;
    }

    public static Chronology ofLocale(Locale locale) {
        Chronology.init();
        Jdk8Methods.requireNonNull(locale, "locale");
        String type = "iso";
        if (LOCALE_METHOD != null) {
            try {
                type = (String)LOCALE_METHOD.invoke((Object)locale, "ca");
            }
            catch (IllegalArgumentException ex) {
            }
            catch (IllegalAccessException ex) {
            }
            catch (InvocationTargetException ex) {}
        } else if (locale.equals(JapaneseChronology.LOCALE)) {
            type = "japanese";
        }
        if (type == null || "iso".equals(type) || "iso8601".equals(type)) {
            return IsoChronology.INSTANCE;
        }
        Chronology chrono = CHRONOS_BY_TYPE.get(type);
        if (chrono == null) {
            throw new DateTimeException("Unknown calendar system: " + type);
        }
        return chrono;
    }

    public static Chronology of(String id) {
        Chronology.init();
        Chronology chrono = CHRONOS_BY_ID.get(id);
        if (chrono != null) {
            return chrono;
        }
        chrono = CHRONOS_BY_TYPE.get(id);
        if (chrono != null) {
            return chrono;
        }
        throw new DateTimeException("Unknown chronology: " + id);
    }

    public static Set<Chronology> getAvailableChronologies() {
        Chronology.init();
        return new HashSet<Chronology>(CHRONOS_BY_ID.values());
    }

    private static void init() {
        if (CHRONOS_BY_ID.isEmpty()) {
            Chronology.register(IsoChronology.INSTANCE);
            Chronology.register(ThaiBuddhistChronology.INSTANCE);
            Chronology.register(MinguoChronology.INSTANCE);
            Chronology.register(JapaneseChronology.INSTANCE);
            Chronology.register(HijrahChronology.INSTANCE);
            CHRONOS_BY_ID.putIfAbsent("Hijrah", HijrahChronology.INSTANCE);
            CHRONOS_BY_TYPE.putIfAbsent("islamic", HijrahChronology.INSTANCE);
            ServiceLoader<Chronology> loader = ServiceLoader.load(Chronology.class, Chronology.class.getClassLoader());
            for (Chronology chrono : loader) {
                CHRONOS_BY_ID.putIfAbsent(chrono.getId(), chrono);
                String type = chrono.getCalendarType();
                if (type == null) continue;
                CHRONOS_BY_TYPE.putIfAbsent(type, chrono);
            }
        }
    }

    private static void register(Chronology chrono) {
        CHRONOS_BY_ID.putIfAbsent(chrono.getId(), chrono);
        String type = chrono.getCalendarType();
        if (type != null) {
            CHRONOS_BY_TYPE.putIfAbsent(type, chrono);
        }
    }

    protected Chronology() {
    }

    <D extends ChronoLocalDate> D ensureChronoLocalDate(Temporal temporal) {
        ChronoLocalDate other = (ChronoLocalDate)temporal;
        if (!this.equals(other.getChronology())) {
            throw new ClassCastException("Chrono mismatch, expected: " + this.getId() + ", actual: " + other.getChronology().getId());
        }
        return (D)other;
    }

    <D extends ChronoLocalDate> ChronoLocalDateTimeImpl<D> ensureChronoLocalDateTime(Temporal temporal) {
        ChronoLocalDateTimeImpl other = (ChronoLocalDateTimeImpl)temporal;
        if (!this.equals(((ChronoLocalDate)other.toLocalDate()).getChronology())) {
            throw new ClassCastException("Chrono mismatch, required: " + this.getId() + ", supplied: " + ((ChronoLocalDate)other.toLocalDate()).getChronology().getId());
        }
        return other;
    }

    <D extends ChronoLocalDate> ChronoZonedDateTimeImpl<D> ensureChronoZonedDateTime(Temporal temporal) {
        ChronoZonedDateTimeImpl other = (ChronoZonedDateTimeImpl)temporal;
        if (!this.equals(((ChronoLocalDate)other.toLocalDate()).getChronology())) {
            throw new ClassCastException("Chrono mismatch, required: " + this.getId() + ", supplied: " + ((ChronoLocalDate)other.toLocalDate()).getChronology().getId());
        }
        return other;
    }

    public abstract String getId();

    public abstract String getCalendarType();

    public ChronoLocalDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
        return this.date(this.prolepticYear(era, yearOfEra), month, dayOfMonth);
    }

    public abstract ChronoLocalDate date(int var1, int var2, int var3);

    public ChronoLocalDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
        return this.dateYearDay(this.prolepticYear(era, yearOfEra), dayOfYear);
    }

    public abstract ChronoLocalDate dateYearDay(int var1, int var2);

    public abstract ChronoLocalDate dateEpochDay(long var1);

    public abstract ChronoLocalDate date(TemporalAccessor var1);

    public ChronoLocalDate dateNow() {
        return this.dateNow(Clock.systemDefaultZone());
    }

    public ChronoLocalDate dateNow(ZoneId zone) {
        return this.dateNow(Clock.system(zone));
    }

    public ChronoLocalDate dateNow(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        return this.date(LocalDate.now(clock));
    }

    public ChronoLocalDateTime<?> localDateTime(TemporalAccessor temporal) {
        try {
            ChronoLocalDate date = this.date(temporal);
            return date.atTime(LocalTime.from(temporal));
        }
        catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain ChronoLocalDateTime from TemporalAccessor: " + temporal.getClass(), ex);
        }
    }

    public ChronoZonedDateTime<?> zonedDateTime(TemporalAccessor temporal) {
        try {
            ZoneId zone = ZoneId.from(temporal);
            try {
                Instant instant = Instant.from(temporal);
                return this.zonedDateTime(instant, zone);
            }
            catch (DateTimeException ex1) {
                ChronoLocalDateTime<?> cldt = this.localDateTime(temporal);
                ChronoLocalDateTimeImpl cldtImpl = this.ensureChronoLocalDateTime(cldt);
                return ChronoZonedDateTimeImpl.ofBest(cldtImpl, zone, null);
            }
        }
        catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain ChronoZonedDateTime from TemporalAccessor: " + temporal.getClass(), ex);
        }
    }

    public ChronoZonedDateTime<?> zonedDateTime(Instant instant, ZoneId zone) {
        ChronoZonedDateTimeImpl result2 = ChronoZonedDateTimeImpl.ofInstant(this, instant, zone);
        return result2;
    }

    public ChronoPeriod period(int years, int months, int days) {
        return new ChronoPeriodImpl(this, years, months, days);
    }

    public abstract boolean isLeapYear(long var1);

    public abstract int prolepticYear(Era var1, int var2);

    public abstract Era eraOf(int var1);

    public abstract List<Era> eras();

    public abstract ValueRange range(ChronoField var1);

    public String getDisplayName(TextStyle style, Locale locale) {
        return new DateTimeFormatterBuilder().appendChronologyText(style).toFormatter(locale).format(new DefaultInterfaceTemporalAccessor(){

            @Override
            public boolean isSupported(TemporalField field2) {
                return false;
            }

            @Override
            public long getLong(TemporalField field2) {
                throw new UnsupportedTemporalTypeException("Unsupported field: " + field2);
            }

            @Override
            public <R> R query(TemporalQuery<R> query) {
                if (query == TemporalQueries.chronology()) {
                    return (R)Chronology.this;
                }
                return super.query(query);
            }
        });
    }

    public abstract ChronoLocalDate resolveDate(Map<TemporalField, Long> var1, ResolverStyle var2);

    void updateResolveMap(Map<TemporalField, Long> fieldValues, ChronoField field2, long value) {
        Long current = fieldValues.get(field2);
        if (current != null && current != value) {
            throw new DateTimeException("Invalid state, field: " + field2 + " " + current + " conflicts with " + field2 + " " + value);
        }
        fieldValues.put(field2, value);
    }

    @Override
    public int compareTo(Chronology other) {
        return this.getId().compareTo(other.getId());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Chronology) {
            return this.compareTo((Chronology)obj) == 0;
        }
        return false;
    }

    public int hashCode() {
        return this.getClass().hashCode() ^ this.getId().hashCode();
    }

    public String toString() {
        return this.getId();
    }

    private Object writeReplace() {
        return new Ser(11, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    void writeExternal(DataOutput out) throws IOException {
        out.writeUTF(this.getId());
    }

    static Chronology readExternal(DataInput in) throws IOException {
        String id = in.readUTF();
        return Chronology.of(id);
    }

    static {
        Method method = null;
        try {
            method = Locale.class.getMethod("getUnicodeLocaleType", String.class);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        LOCALE_METHOD = method;
    }
}

