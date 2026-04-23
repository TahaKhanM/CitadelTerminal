/*
 * Decompiled with CFR 0.152.
 */
package com.github.cliftonlabs.json_simple;

import com.github.cliftonlabs.json_simple.Jsonable;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class JsonArray
extends ArrayList<Object>
implements Jsonable {
    private static final long serialVersionUID = 1L;

    public JsonArray() {
    }

    public JsonArray(Collection<?> collection) {
        super(collection);
    }

    public <T> void asCollection(Collection<T> destination) {
        for (Object o : this) {
            destination.add(o);
        }
    }

    public BigDecimal getBigDecimal(int index) {
        Object returnable = this.get(index);
        if (!(returnable instanceof BigDecimal)) {
            if (returnable instanceof Number) {
                returnable = new BigDecimal(returnable.toString());
            } else if (returnable instanceof String) {
                returnable = new BigDecimal((String)returnable);
            }
        }
        return (BigDecimal)returnable;
    }

    public Boolean getBoolean(int index) {
        Object returnable = this.get(index);
        if (returnable instanceof String) {
            returnable = Boolean.valueOf((String)returnable);
        }
        return (Boolean)returnable;
    }

    public Byte getByte(int index) {
        Object returnable = this.get(index);
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).byteValue();
    }

    public <T extends Collection<?>> T getCollection(int index) {
        return (T)((Collection)this.get(index));
    }

    public Double getDouble(int index) {
        Object returnable = this.get(index);
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).doubleValue();
    }

    public Float getFloat(int index) {
        Object returnable = this.get(index);
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return Float.valueOf(((Number)returnable).floatValue());
    }

    public Integer getInteger(int index) {
        Object returnable = this.get(index);
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).intValue();
    }

    public Long getLong(int index) {
        Object returnable = this.get(index);
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).longValue();
    }

    public <T extends Map<?, ?>> T getMap(int index) {
        return (T)((Map)this.get(index));
    }

    public Short getShort(int index) {
        Object returnable = this.get(index);
        if (returnable == null) {
            return null;
        }
        if (returnable instanceof String) {
            returnable = new BigDecimal((String)returnable);
        }
        return ((Number)returnable).shortValue();
    }

    public String getString(int index) {
        Object returnable = this.get(index);
        if (returnable instanceof Boolean) {
            returnable = returnable.toString();
        } else if (returnable instanceof Number) {
            returnable = returnable.toString();
        }
        return (String)returnable;
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
        boolean isFirstElement = true;
        Iterator elements = this.iterator();
        writable.write(91);
        while (elements.hasNext()) {
            if (isFirstElement) {
                isFirstElement = false;
            } else {
                writable.write(44);
            }
            writable.write(Jsoner.serialize(elements.next()));
        }
        writable.write(93);
    }
}

