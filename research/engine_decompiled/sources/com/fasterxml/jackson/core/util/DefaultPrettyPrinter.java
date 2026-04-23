/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.util.Instantiatable;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class DefaultPrettyPrinter
implements PrettyPrinter,
Instantiatable<DefaultPrettyPrinter>,
Serializable {
    private static final long serialVersionUID = -5512586643324525213L;
    public static final SerializedString DEFAULT_ROOT_VALUE_SEPARATOR = new SerializedString(" ");
    protected Indenter _arrayIndenter = FixedSpaceIndenter.instance;
    protected Indenter _objectIndenter = Lf2SpacesIndenter.instance;
    protected final SerializableString _rootSeparator;
    protected boolean _spacesInObjectEntries = true;
    protected transient int _nesting = 0;

    public DefaultPrettyPrinter() {
        this(DEFAULT_ROOT_VALUE_SEPARATOR);
    }

    public DefaultPrettyPrinter(String rootSeparator) {
        this(rootSeparator == null ? null : new SerializedString(rootSeparator));
    }

    public DefaultPrettyPrinter(SerializableString rootSeparator) {
        this._rootSeparator = rootSeparator;
    }

    public DefaultPrettyPrinter(DefaultPrettyPrinter base) {
        this(base, base._rootSeparator);
    }

    public DefaultPrettyPrinter(DefaultPrettyPrinter base, SerializableString rootSeparator) {
        this._arrayIndenter = base._arrayIndenter;
        this._objectIndenter = base._objectIndenter;
        this._spacesInObjectEntries = base._spacesInObjectEntries;
        this._nesting = base._nesting;
        this._rootSeparator = rootSeparator;
    }

    public DefaultPrettyPrinter withRootSeparator(SerializableString rootSeparator) {
        if (this._rootSeparator == rootSeparator || rootSeparator != null && rootSeparator.equals(this._rootSeparator)) {
            return this;
        }
        return new DefaultPrettyPrinter(this, rootSeparator);
    }

    public void indentArraysWith(Indenter i) {
        this._arrayIndenter = i == null ? NopIndenter.instance : i;
    }

    public void indentObjectsWith(Indenter i) {
        this._objectIndenter = i == null ? NopIndenter.instance : i;
    }

    public void spacesInObjectEntries(boolean b) {
        this._spacesInObjectEntries = b;
    }

    @Override
    public DefaultPrettyPrinter createInstance() {
        return new DefaultPrettyPrinter(this);
    }

    @Override
    public void writeRootValueSeparator(JsonGenerator jg) throws IOException, JsonGenerationException {
        if (this._rootSeparator != null) {
            jg.writeRaw(this._rootSeparator);
        }
    }

    @Override
    public void writeStartObject(JsonGenerator jg) throws IOException, JsonGenerationException {
        jg.writeRaw('{');
        if (!this._objectIndenter.isInline()) {
            ++this._nesting;
        }
    }

    @Override
    public void beforeObjectEntries(JsonGenerator jg) throws IOException, JsonGenerationException {
        this._objectIndenter.writeIndentation(jg, this._nesting);
    }

    @Override
    public void writeObjectFieldValueSeparator(JsonGenerator jg) throws IOException, JsonGenerationException {
        if (this._spacesInObjectEntries) {
            jg.writeRaw(" : ");
        } else {
            jg.writeRaw(':');
        }
    }

    @Override
    public void writeObjectEntrySeparator(JsonGenerator jg) throws IOException, JsonGenerationException {
        jg.writeRaw(',');
        this._objectIndenter.writeIndentation(jg, this._nesting);
    }

    @Override
    public void writeEndObject(JsonGenerator jg, int nrOfEntries) throws IOException, JsonGenerationException {
        if (!this._objectIndenter.isInline()) {
            --this._nesting;
        }
        if (nrOfEntries > 0) {
            this._objectIndenter.writeIndentation(jg, this._nesting);
        } else {
            jg.writeRaw(' ');
        }
        jg.writeRaw('}');
    }

    @Override
    public void writeStartArray(JsonGenerator jg) throws IOException, JsonGenerationException {
        if (!this._arrayIndenter.isInline()) {
            ++this._nesting;
        }
        jg.writeRaw('[');
    }

    @Override
    public void beforeArrayValues(JsonGenerator jg) throws IOException, JsonGenerationException {
        this._arrayIndenter.writeIndentation(jg, this._nesting);
    }

    @Override
    public void writeArrayValueSeparator(JsonGenerator jg) throws IOException, JsonGenerationException {
        jg.writeRaw(',');
        this._arrayIndenter.writeIndentation(jg, this._nesting);
    }

    @Override
    public void writeEndArray(JsonGenerator jg, int nrOfValues) throws IOException, JsonGenerationException {
        if (!this._arrayIndenter.isInline()) {
            --this._nesting;
        }
        if (nrOfValues > 0) {
            this._arrayIndenter.writeIndentation(jg, this._nesting);
        } else {
            jg.writeRaw(' ');
        }
        jg.writeRaw(']');
    }

    public static class Lf2SpacesIndenter
    implements Indenter,
    Serializable {
        private static final long serialVersionUID = 1L;
        public static Lf2SpacesIndenter instance = new Lf2SpacesIndenter();
        static final String SYSTEM_LINE_SEPARATOR;
        static final int SPACE_COUNT = 64;
        static final char[] SPACES;

        public boolean isInline() {
            return false;
        }

        public void writeIndentation(JsonGenerator jg, int level) throws IOException, JsonGenerationException {
            jg.writeRaw(SYSTEM_LINE_SEPARATOR);
            if (level > 0) {
                level += level;
                while (level > 64) {
                    jg.writeRaw(SPACES, 0, 64);
                    level -= SPACES.length;
                }
                jg.writeRaw(SPACES, 0, level);
            }
        }

        static {
            String lf = null;
            try {
                lf = System.getProperty("line.separator");
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            SYSTEM_LINE_SEPARATOR = lf == null ? "\n" : lf;
            SPACES = new char[64];
            Arrays.fill(SPACES, ' ');
        }
    }

    public static class FixedSpaceIndenter
    implements Indenter,
    Serializable {
        private static final long serialVersionUID = 1L;
        public static FixedSpaceIndenter instance = new FixedSpaceIndenter();

        public void writeIndentation(JsonGenerator jg, int level) throws IOException, JsonGenerationException {
            jg.writeRaw(' ');
        }

        public boolean isInline() {
            return true;
        }
    }

    public static class NopIndenter
    implements Indenter,
    Serializable {
        private static final long serialVersionUID = 1L;
        public static NopIndenter instance = new NopIndenter();

        public void writeIndentation(JsonGenerator jg, int level) {
        }

        public boolean isInline() {
            return true;
        }
    }

    public static interface Indenter {
        public void writeIndentation(JsonGenerator var1, int var2) throws IOException, JsonGenerationException;

        public boolean isInline();
    }
}

