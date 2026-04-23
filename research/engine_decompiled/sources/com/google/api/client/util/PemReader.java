/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.util;

import com.google.api.client.util.Base64;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Beta
public final class PemReader {
    private static final Pattern BEGIN_PATTERN = Pattern.compile("-----BEGIN ([A-Z ]+)-----");
    private static final Pattern END_PATTERN = Pattern.compile("-----END ([A-Z ]+)-----");
    private BufferedReader reader;

    public PemReader(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    public Section readNextSection() throws IOException {
        return this.readNextSection(null);
    }

    public Section readNextSection(String titleToLookFor) throws IOException {
        String title = null;
        StringBuilder keyBuilder = null;
        while (true) {
            Matcher m;
            String line;
            if ((line = this.reader.readLine()) == null) {
                Preconditions.checkArgument(title == null, "missing end tag (%s)", title);
                return null;
            }
            if (keyBuilder == null) {
                m = BEGIN_PATTERN.matcher(line);
                if (!m.matches()) continue;
                String curTitle = m.group(1);
                if (titleToLookFor != null && !curTitle.equals(titleToLookFor)) continue;
                keyBuilder = new StringBuilder();
                title = curTitle;
                continue;
            }
            m = END_PATTERN.matcher(line);
            if (m.matches()) {
                String endTitle = m.group(1);
                Preconditions.checkArgument(endTitle.equals(title), "end tag (%s) doesn't match begin tag (%s)", endTitle, title);
                return new Section(title, Base64.decodeBase64(keyBuilder.toString()));
            }
            keyBuilder.append(line);
        }
    }

    public static Section readFirstSectionAndClose(Reader reader) throws IOException {
        return PemReader.readFirstSectionAndClose(reader, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Section readFirstSectionAndClose(Reader reader, String titleToLookFor) throws IOException {
        PemReader pemReader = new PemReader(reader);
        try {
            Section section = pemReader.readNextSection(titleToLookFor);
            return section;
        }
        finally {
            pemReader.close();
        }
    }

    public void close() throws IOException {
        this.reader.close();
    }

    public static final class Section {
        private final String title;
        private final byte[] base64decodedBytes;

        Section(String title, byte[] base64decodedBytes) {
            this.title = Preconditions.checkNotNull(title);
            this.base64decodedBytes = Preconditions.checkNotNull(base64decodedBytes);
        }

        public String getTitle() {
            return this.title;
        }

        public byte[] getBase64DecodedBytes() {
            return this.base64decodedBytes;
        }
    }
}

