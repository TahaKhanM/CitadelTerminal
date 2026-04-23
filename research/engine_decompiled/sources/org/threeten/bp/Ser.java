/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;
import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.MonthDay;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.OffsetTime;
import org.threeten.bp.Year;
import org.threeten.bp.YearMonth;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.ZoneRegion;
import org.threeten.bp.ZonedDateTime;

final class Ser
implements Externalizable {
    private static final long serialVersionUID = -7683839454370182990L;
    static final byte DURATION_TYPE = 1;
    static final byte INSTANT_TYPE = 2;
    static final byte LOCAL_DATE_TYPE = 3;
    static final byte LOCAL_DATE_TIME_TYPE = 4;
    static final byte LOCAL_TIME_TYPE = 5;
    static final byte ZONED_DATE_TIME_TYPE = 6;
    static final byte ZONE_REGION_TYPE = 7;
    static final byte ZONE_OFFSET_TYPE = 8;
    static final byte MONTH_DAY_TYPE = 64;
    static final byte OFFSET_TIME_TYPE = 66;
    static final byte YEAR_TYPE = 67;
    static final byte YEAR_MONTH_TYPE = 68;
    static final byte OFFSET_DATE_TIME_TYPE = 69;
    private byte type;
    private Object object;

    public Ser() {
    }

    Ser(byte type, Object object) {
        this.type = type;
        this.object = object;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        Ser.writeInternal(this.type, this.object, out);
    }

    static void writeInternal(byte type, Object object, DataOutput out) throws IOException {
        out.writeByte(type);
        switch (type) {
            case 1: {
                ((Duration)object).writeExternal(out);
                break;
            }
            case 2: {
                ((Instant)object).writeExternal(out);
                break;
            }
            case 3: {
                ((LocalDate)object).writeExternal(out);
                break;
            }
            case 4: {
                ((LocalDateTime)object).writeExternal(out);
                break;
            }
            case 5: {
                ((LocalTime)object).writeExternal(out);
                break;
            }
            case 64: {
                ((MonthDay)object).writeExternal(out);
                break;
            }
            case 69: {
                ((OffsetDateTime)object).writeExternal(out);
                break;
            }
            case 66: {
                ((OffsetTime)object).writeExternal(out);
                break;
            }
            case 68: {
                ((YearMonth)object).writeExternal(out);
                break;
            }
            case 67: {
                ((Year)object).writeExternal(out);
                break;
            }
            case 7: {
                ((ZoneRegion)object).writeExternal(out);
                break;
            }
            case 8: {
                ((ZoneOffset)object).writeExternal(out);
                break;
            }
            case 6: {
                ((ZonedDateTime)object).writeExternal(out);
                break;
            }
            default: {
                throw new InvalidClassException("Unknown serialized type");
            }
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.type = in.readByte();
        this.object = Ser.readInternal(this.type, in);
    }

    static Object read(DataInput in) throws IOException {
        byte type = in.readByte();
        return Ser.readInternal(type, in);
    }

    private static Object readInternal(byte type, DataInput in) throws IOException {
        switch (type) {
            case 1: {
                return Duration.readExternal(in);
            }
            case 2: {
                return Instant.readExternal(in);
            }
            case 3: {
                return LocalDate.readExternal(in);
            }
            case 4: {
                return LocalDateTime.readExternal(in);
            }
            case 5: {
                return LocalTime.readExternal(in);
            }
            case 64: {
                return MonthDay.readExternal(in);
            }
            case 69: {
                return OffsetDateTime.readExternal(in);
            }
            case 66: {
                return OffsetTime.readExternal(in);
            }
            case 67: {
                return Year.readExternal(in);
            }
            case 68: {
                return YearMonth.readExternal(in);
            }
            case 6: {
                return ZonedDateTime.readExternal(in);
            }
            case 8: {
                return ZoneOffset.readExternal(in);
            }
            case 7: {
                return ZoneRegion.readExternal(in);
            }
        }
        throw new StreamCorruptedException("Unknown serialized type");
    }

    private Object readResolve() {
        return this.object;
    }
}

