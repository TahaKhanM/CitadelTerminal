/*
 * Decompiled with CFR 0.152.
 */
package com.github.cliftonlabs.json_simple;

import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.Jsonable;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class JsonObject
extends HashMap<String, Object>
implements Jsonable {
    private static final long serialVersionUID = 2L;

    public JsonObject() {
    }

    public JsonObject(Map<String, ?> map2) {
        super(map2);
    }

    public BigDecimal getBigDecimal(JsonKey key) {
        Object returnable = this.get(key.getKey());
        if (!(returnable instanceof BigDecimal)) {
            if (returnable instanceof Number) {
                returnable = new BigDecimal(returnable.toString());
            } else if (returnable instanceof String) {
                returnable = new BigDecimal((String)returnable);
            }
        }
        return (BigDecimal)returnable;
    }

    public BigDecimal getBigDecimalOrDefault(JsonKey key) {
        Object returnable = this.containsKey(key.getKey()) ? this.get(key.getKey()) : key.getValue();
        if (!(returnable instanceof BigDecimal)) {
            if (returnable instanceof Number) {
                returnable = new BigDecimal(returnable.toString());
            } else if (returnable instanceof String) {
                returnable = new BigDecimal((String)returnable);
            }
        }
        return (BigDecimal)returnable;
    }

    public Boolean getBoolean(JsonKey key) {
        Object returnable = this.get(key.getKey());
        if (returnable instanceof String) {
            returnable = Boolean.valueOf((String)returnable);
        }
        return (Boolean)returnable;
    }

    public Boolean getBooleanOrDefault(JsonKey key) {
        Object returnable = this.containsKey(key.getKey()) ? this.get(key.getKey()) : key.getValue();
        if (returnable instanceof String) {
            returnable = Boolean.valueOf((String)returnable);
        }
        return (Boolean)returnable;
    }

    public Byte getByte(JsonKey key) {
        Object returnable = this.get(key.getKey());
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).byteValue();
    }

    public Byte getByteOrDefault(JsonKey key) {
        Object returnable = this.containsKey(key.getKey()) ? this.get(key.getKey()) : key.getValue();
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).byteValue();
    }

    public <T extends Collection<?>> T getCollection(JsonKey key) {
        return (T)((Collection)this.get(key.getKey()));
    }

    public <T extends Collection<?>> T getCollectionOrDefault(JsonKey key) {
        Object returnable = this.containsKey(key.getKey()) ? this.get(key.getKey()) : key.getValue();
        return (T)((Collection)returnable);
    }

    public Double getDouble(JsonKey key) {
        Object returnable = this.get(key.getKey());
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).doubleValue();
    }

    public Double getDoubleOrDefault(JsonKey key) {
        Object returnable = this.containsKey(key.getKey()) ? this.get(key.getKey()) : key.getValue();
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).doubleValue();
    }

    public Float getFloat(JsonKey key) {
        Object returnable = this.get(key.getKey());
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return Float.valueOf(((Number)returnable).floatValue());
    }

    public Float getFloatOrDefault(JsonKey key) {
        Object returnable = this.containsKey(key.getKey()) ? this.get(key.getKey()) : key.getValue();
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return Float.valueOf(((Number)returnable).floatValue());
    }

    public Integer getInteger(JsonKey key) {
        Object returnable = this.get(key.getKey());
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).intValue();
    }

    public Integer getIntegerOrDefault(JsonKey key) {
        Object returnable = this.containsKey(key.getKey()) ? this.get(key.getKey()) : key.getValue();
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).intValue();
    }

    public Long getLong(JsonKey key) {
        Object returnable = this.get(key.getKey());
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).longValue();
    }

    public Long getLongOrDefault(JsonKey key) {
        Object returnable = this.containsKey(key.getKey()) ? this.get(key.getKey()) : key.getValue();
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).longValue();
    }

    public <T extends Map<?, ?>> T getMap(JsonKey key) {
        return (T)((Map)this.get(key.getKey()));
    }

    public <T extends Map<?, ?>> T getMapOrDefault(JsonKey key) {
        Object returnable = this.containsKey(key.getKey()) ? this.get(key.getKey()) : key.getValue();
        return (T)((Map)returnable);
    }

    public Short getShort(JsonKey key) {
        Object returnable = this.get(key.getKey());
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).shortValue();
    }

    public Short getShortOrDefault(JsonKey key) {
        Object returnable = this.containsKey(key.getKey()) ? this.get(key.getKey()) : key.getValue();
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).shortValue();
    }

    public String getString(JsonKey key) {
        Object returnable = this.get(key.getKey());
        if (returnable instanceof Boolean) {
            returnable = returnable.toString();
        } else if (returnable instanceof Number) {
            returnable = returnable.toString();
        }
        return (String)returnable;
    }

    public String getStringOrDefault(JsonKey key) {
        Object returnable = this.containsKey(key.getKey()) ? this.get(key.getKey()) : key.getValue();
        if (returnable instanceof Boolean) {
            returnable = returnable.toString();
        } else if (returnable instanceof Number) {
            returnable = returnable.toString();
        }
        return (String)returnable;
    }

    public void requireKeys(JsonKey ... keys) {
        HashSet<JsonKey> missing = new HashSet<JsonKey>();
        for (JsonKey k : keys) {
            if (this.containsKey(k.getKey())) continue;
            missing.add(k);
        }
        if (!missing.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (JsonKey k : missing) {
                sb.append(k.getKey()).append(", ");
            }
            sb.setLength(sb.length() - 2);
            String s2 = missing.size() > 1 ? "s" : "";
            throw new NoSuchElementException("A JsonObject is missing required key" + s2 + ": " + sb.toString());
        }
    }

    @Override
    public String toJson() {
        StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return writable.toString();
    }

    @Override
    public void toJson(Writer writable) throws IOException {
        boolean isFirstEntry = true;
        Iterator entries = this.entrySet().iterator();
        writable.write(123);
        while (entries.hasNext()) {
            if (isFirstEntry) {
                isFirstEntry = false;
            } else {
                writable.write(44);
            }
            Map.Entry entry = entries.next();
            writable.write(Jsoner.serialize(entry.getKey()));
            writable.write(58);
            writable.write(Jsoner.serialize(entry.getValue()));
        }
        writable.write(125);
    }
}

