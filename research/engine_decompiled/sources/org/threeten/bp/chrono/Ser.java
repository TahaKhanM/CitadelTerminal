/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.chrono;

import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;
import org.threeten.bp.chrono.ChronoLocalDateTimeImpl;
import org.threeten.bp.chrono.ChronoZonedDateTimeImpl;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.HijrahDate;
import org.threeten.bp.chrono.HijrahEra;
import org.threeten.bp.chrono.JapaneseDate;
import org.threeten.bp.chrono.JapaneseEra;
import org.threeten.bp.chrono.MinguoDate;
import org.threeten.bp.chrono.MinguoEra;
import org.threeten.bp.chrono.ThaiBuddhistDate;
import org.threeten.bp.chrono.ThaiBuddhistEra;

final class Ser
implements Externalizable {
    private static final long serialVersionUID = 7857518227608961174L;
    static final byte JAPANESE_DATE_TYPE = 1;
    static final byte JAPANESE_ERA_TYPE = 2;
    static final byte HIJRAH_DATE_TYPE = 3;
    static final byte HIJRAH_ERA_TYPE = 4;
    static final byte MINGUO_DATE_TYPE = 5;
    static final byte MINGUO_ERA_TYPE = 6;
    static final byte THAIBUDDHIST_DATE_TYPE = 7;
    static final byte THAIBUDDHIST_ERA_TYPE = 8;
    static final byte CHRONO_TYPE = 11;
    static final byte CHRONO_LOCALDATETIME_TYPE = 12;
    static final byte CHRONO_ZONEDDATETIME_TYPE = 13;
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

    private static void writeInternal(byte type, Object object, ObjectOutput out) throws IOException {
        out.writeByte(type);
        switch (type) {
            case 1: {
                ((JapaneseDate)object).writeExternal(out);
                break;
            }
            case 2: {
                ((JapaneseEra)object).writeExternal(out);
                break;
            }
            case 3: {
                ((HijrahDate)object).writeExternal(out);
                break;
            }
            case 4: {
                ((HijrahEra)object).writeExternal(out);
                break;
            }
            case 5: {
                ((MinguoDate)object).writeExternal(out);
                break;
            }
            case 6: {
                ((MinguoEra)object).writeExternal(out);
                break;
            }
            case 7: {
                ((ThaiBuddhistDate)object).writeExternal(out);
                break;
            }
            case 8: {
                ((ThaiBuddhistEra)object).writeExternal(out);
                break;
            }
            case 11: {
                ((Chronology)object).writeExternal(out);
                break;
            }
            case 12: {
                ((ChronoLocalDateTimeImpl)object).writeExternal(out);
                break;
            }
            case 13: {
                ((ChronoZonedDateTimeImpl)object).writeExternal(out);
                break;
            }
            default: {
                throw new InvalidClassException("Unknown serialized type");
            }
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.type = in.readByte();
        this.object = Ser.readInternal(this.type, in);
    }

    static Object read(ObjectInput in) throws IOException, ClassNotFoundException {
        byte type = in.readByte();
        return Ser.readInternal(type, in);
    }

    private static Object readInternal(byte type, ObjectInput in) throws IOException, ClassNotFoundException {
        switch (type) {
            case 1: {
                return JapaneseDate.readExternal(in);
            }
            case 2: {
                return JapaneseEra.readExternal(in);
            }
            case 3: {
                return HijrahDate.readExternal(in);
            }
            case 4: {
                return HijrahEra.readExternal(in);
            }
            case 5: {
                return MinguoDate.readExternal(in);
            }
            case 6: {
                return MinguoEra.readExternal(in);
            }
            case 7: {
                return ThaiBuddhistDate.readExternal(in);
            }
            case 8: {
                return ThaiBuddhistEra.readExternal(in);
            }
            case 11: {
                return Chronology.readExternal(in);
            }
            case 12: {
                return ChronoLocalDateTimeImpl.readExternal(in);
            }
            case 13: {
                return ChronoZonedDateTimeImpl.readExternal(in);
            }
        }
        throw new StreamCorruptedException("Unknown serialized type");
    }

    private Object readResolve() {
        return this.object;
    }
}

