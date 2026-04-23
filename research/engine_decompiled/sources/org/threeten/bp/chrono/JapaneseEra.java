/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDate;
import org.threeten.bp.chrono.JapaneseChronology;
import org.threeten.bp.chrono.Ser;
import org.threeten.bp.jdk8.DefaultInterfaceEra;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.ValueRange;

public final class JapaneseEra
extends DefaultInterfaceEra
implements Serializable {
    static final int ERA_OFFSET = 2;
    public static final JapaneseEra MEIJI = new JapaneseEra(-1, LocalDate.of(1868, 9, 8), "Meiji");
    public static final JapaneseEra TAISHO = new JapaneseEra(0, LocalDate.of(1912, 7, 30), "Taisho");
    public static final JapaneseEra SHOWA = new JapaneseEra(1, LocalDate.of(1926, 12, 25), "Showa");
    public static final JapaneseEra HEISEI = new JapaneseEra(2, LocalDate.of(1989, 1, 8), "Heisei");
    private static final int ADDITIONAL_VALUE = 3;
    private static final long serialVersionUID = 1466499369062886794L;
    private static final AtomicReference<JapaneseEra[]> KNOWN_ERAS;
    private final int eraValue;
    private final transient LocalDate since;
    private final transient String name;

    private JapaneseEra(int eraValue, LocalDate since, String name) {
        this.eraValue = eraValue;
        this.since = since;
        this.name = name;
    }

    private Object readResolve() throws ObjectStreamException {
        try {
            return JapaneseEra.of(this.eraValue);
        }
        catch (DateTimeException e) {
            InvalidObjectException ex = new InvalidObjectException("Invalid era");
            ex.initCause(e);
            throw ex;
        }
    }

    public static JapaneseEra registerEra(LocalDate since, String name) {
        JapaneseEra[] known = KNOWN_ERAS.get();
        if (known.length > 4) {
            throw new DateTimeException("Only one additional Japanese era can be added");
        }
        Jdk8Methods.requireNonNull(since, "since");
        Jdk8Methods.requireNonNull(name, "name");
        if (!since.isAfter(JapaneseEra.HEISEI.since)) {
            throw new DateTimeException("Invalid since date for additional Japanese era, must be after Heisei");
        }
        JapaneseEra era = new JapaneseEra(3, since, name);
        JapaneseEra[] newArray = Arrays.copyOf(known, 5);
        newArray[4] = era;
        if (!KNOWN_ERAS.compareAndSet(known, newArray)) {
            throw new DateTimeException("Only one additional Japanese era can be added");
        }
        return era;
    }

    public static JapaneseEra of(int japaneseEra) {
        JapaneseEra[] known = KNOWN_ERAS.get();
        if (japaneseEra < JapaneseEra.MEIJI.eraValue || japaneseEra > known[known.length - 1].eraValue) {
            throw new DateTimeException("japaneseEra is invalid");
        }
        return known[JapaneseEra.ordinal(japaneseEra)];
    }

    public static JapaneseEra valueOf(String japaneseEra) {
        JapaneseEra[] known;
        Jdk8Methods.requireNonNull(japaneseEra, "japaneseEra");
        for (JapaneseEra era : known = KNOWN_ERAS.get()) {
            if (!japaneseEra.equals(era.name)) continue;
            return era;
        }
        throw new IllegalArgumentException("Era not found: " + japaneseEra);
    }

    public static JapaneseEra[] values() {
        JapaneseEra[] known = KNOWN_ERAS.get();
        return Arrays.copyOf(known, known.length);
    }

    static JapaneseEra from(LocalDate date) {
        if (date.isBefore(JapaneseEra.MEIJI.since)) {
            throw new DateTimeException("Date too early: " + date);
        }
        JapaneseEra[] known = KNOWN_ERAS.get();
        for (int i = known.length - 1; i >= 0; --i) {
            JapaneseEra era = known[i];
            if (date.compareTo(era.since) < 0) continue;
            return era;
        }
        return null;
    }

    private static int ordinal(int eraValue) {
        return eraValue + 1;
    }

    LocalDate startDate() {
        return this.since;
    }

    LocalDate endDate() {
        JapaneseEra[] eras;
        int ordinal = JapaneseEra.ordinal(this.eraValue);
        if (ordinal >= (eras = JapaneseEra.values()).length - 1) {
            return LocalDate.MAX;
        }
        return eras[ordinal + 1].startDate().minusDays(1L);
    }

    @Override
    public int getValue() {
        return this.eraValue;
    }

    @Override
    public ValueRange range(TemporalField field2) {
        if (field2 == ChronoField.ERA) {
            return JapaneseChronology.INSTANCE.range(ChronoField.ERA);
        }
        return super.range(field2);
    }

    public String toString() {
        return this.name;
    }

    private Object writeReplace() {
        return new Ser(2, this);
    }

    void writeExternal(DataOutput out) throws IOException {
        out.writeByte(this.getValue());
    }

    static JapaneseEra readExternal(DataInput in) throws IOException {
        byte eraValue = in.readByte();
        return JapaneseEra.of(eraValue);
    }

    static {
        JapaneseEra[] array = new JapaneseEra[]{MEIJI, TAISHO, SHOWA, HEISEI};
        KNOWN_ERAS = new AtomicReference<JapaneseEra[]>(array);
    }
}

