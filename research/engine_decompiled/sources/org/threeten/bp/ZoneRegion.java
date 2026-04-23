/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.regex.Pattern;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Ser;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.zone.ZoneRules;
import org.threeten.bp.zone.ZoneRulesException;
import org.threeten.bp.zone.ZoneRulesProvider;

final class ZoneRegion
extends ZoneId
implements Serializable {
    private static final long serialVersionUID = 8386373296231747096L;
    private static final Pattern PATTERN = Pattern.compile("[A-Za-z][A-Za-z0-9~/._+-]+");
    private final String id;
    private final transient ZoneRules rules;

    private static ZoneRegion ofLenient(String zoneId) {
        if (zoneId.equals("Z") || zoneId.startsWith("+") || zoneId.startsWith("-")) {
            throw new DateTimeException("Invalid ID for region-based ZoneId, invalid format: " + zoneId);
        }
        if (zoneId.equals("UTC") || zoneId.equals("GMT") || zoneId.equals("UT")) {
            return new ZoneRegion(zoneId, ZoneOffset.UTC.getRules());
        }
        if (zoneId.startsWith("UTC+") || zoneId.startsWith("GMT+") || zoneId.startsWith("UTC-") || zoneId.startsWith("GMT-")) {
            ZoneOffset offset = ZoneOffset.of(zoneId.substring(3));
            if (offset.getTotalSeconds() == 0) {
                return new ZoneRegion(zoneId.substring(0, 3), offset.getRules());
            }
            return new ZoneRegion(zoneId.substring(0, 3) + offset.getId(), offset.getRules());
        }
        if (zoneId.startsWith("UT+") || zoneId.startsWith("UT-")) {
            ZoneOffset offset = ZoneOffset.of(zoneId.substring(2));
            if (offset.getTotalSeconds() == 0) {
                return new ZoneRegion("UT", offset.getRules());
            }
            return new ZoneRegion("UT" + offset.getId(), offset.getRules());
        }
        return ZoneRegion.ofId(zoneId, false);
    }

    static ZoneRegion ofId(String zoneId, boolean checkAvailable) {
        ZoneRules rules;
        block4: {
            Jdk8Methods.requireNonNull(zoneId, "zoneId");
            if (zoneId.length() < 2 || !PATTERN.matcher(zoneId).matches()) {
                throw new DateTimeException("Invalid ID for region-based ZoneId, invalid format: " + zoneId);
            }
            rules = null;
            try {
                rules = ZoneRulesProvider.getRules(zoneId, true);
            }
            catch (ZoneRulesException ex) {
                if (zoneId.equals("GMT0")) {
                    rules = ZoneOffset.UTC.getRules();
                }
                if (!checkAvailable) break block4;
                throw ex;
            }
        }
        return new ZoneRegion(zoneId, rules);
    }

    ZoneRegion(String id, ZoneRules rules) {
        this.id = id;
        this.rules = rules;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public ZoneRules getRules() {
        return this.rules != null ? this.rules : ZoneRulesProvider.getRules(this.id, false);
    }

    private Object writeReplace() {
        return new Ser(7, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override
    void write(DataOutput out) throws IOException {
        out.writeByte(7);
        this.writeExternal(out);
    }

    void writeExternal(DataOutput out) throws IOException {
        out.writeUTF(this.id);
    }

    static ZoneId readExternal(DataInput in) throws IOException {
        String id = in.readUTF();
        return ZoneRegion.ofLenient(id);
    }
}

