/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.message;

import java.util.NoSuchElementException;
import org.apache.http.HeaderIterator;
import org.apache.http.ParseException;
import org.apache.http.TokenIterator;
import org.apache.http.util.Args;

public class BasicTokenIterator
implements TokenIterator {
    public static final String HTTP_SEPARATORS = " ,;=()<>@:\\\"/[]?{}\t";
    protected final HeaderIterator headerIt;
    protected String currentHeader;
    protected String currentToken;
    protected int searchPos;

    public BasicTokenIterator(HeaderIterator headerIterator) {
        this.headerIt = Args.notNull(headerIterator, "Header iterator");
        this.searchPos = this.findNext(-1);
    }

    @Override
    public boolean hasNext() {
        return this.currentToken != null;
    }

    @Override
    public String nextToken() throws NoSuchElementException, ParseException {
        if (this.currentToken == null) {
            throw new NoSuchElementException("Iteration already finished.");
        }
        String result2 = this.currentToken;
        this.searchPos = this.findNext(this.searchPos);
        return result2;
    }

    @Override
    public final Object next() throws NoSuchElementException, ParseException {
        return this.nextToken();
    }

    @Override
    public final void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Removing tokens is not supported.");
    }

    protected int findNext(int pos) throws ParseException {
        int from2 = pos;
        if (from2 < 0) {
            if (!this.headerIt.hasNext()) {
                return -1;
            }
            this.currentHeader = this.headerIt.nextHeader().getValue();
            from2 = 0;
        } else {
            from2 = this.findTokenSeparator(from2);
        }
        int start = this.findTokenStart(from2);
        if (start < 0) {
            this.currentToken = null;
            return -1;
        }
        int end = this.findTokenEnd(start);
        this.currentToken = this.createToken(this.currentHeader, start, end);
        return end;
    }

    protected String createToken(String value, int start, int end) {
        return value.substring(start, end);
    }

    protected int findTokenStart(int pos) {
        int from2 = Args.notNegative(pos, "Search position");
        boolean found = false;
        while (!found && this.currentHeader != null) {
            int to2 = this.currentHeader.length();
            while (!found && from2 < to2) {
                char ch = this.currentHeader.charAt(from2);
                if (this.isTokenSeparator(ch) || this.isWhitespace(ch)) {
                    ++from2;
                    continue;
                }
                if (this.isTokenChar(this.currentHeader.charAt(from2))) {
                    found = true;
                    continue;
                }
                throw new ParseException("Invalid character before token (pos " + from2 + "): " + this.currentHeader);
            }
            if (found) continue;
            if (this.headerIt.hasNext()) {
                this.currentHeader = this.headerIt.nextHeader().getValue();
                from2 = 0;
                continue;
            }
            this.currentHeader = null;
        }
        return found ? from2 : -1;
    }

    protected int findTokenSeparator(int pos) {
        int from2 = Args.notNegative(pos, "Search position");
        boolean found = false;
        int to2 = this.currentHeader.length();
        while (!found && from2 < to2) {
            char ch = this.currentHeader.charAt(from2);
            if (this.isTokenSeparator(ch)) {
                found = true;
                continue;
            }
            if (this.isWhitespace(ch)) {
                ++from2;
                continue;
            }
            if (this.isTokenChar(ch)) {
                throw new ParseException("Tokens without separator (pos " + from2 + "): " + this.currentHeader);
            }
            throw new ParseException("Invalid character after token (pos " + from2 + "): " + this.currentHeader);
        }
        return from2;
    }

    protected int findTokenEnd(int from2) {
        int end;
        Args.notNegative(from2, "Search position");
        int to2 = this.currentHeader.length();
        for (end = from2 + 1; end < to2 && this.isTokenChar(this.currentHeader.charAt(end)); ++end) {
        }
        return end;
    }

    protected boolean isTokenSeparator(char ch) {
        return ch == ',';
    }

    protected boolean isWhitespace(char ch) {
        return ch == '\t' || Character.isSpaceChar(ch);
    }

    protected boolean isTokenChar(char ch) {
        if (Character.isLetterOrDigit(ch)) {
            return true;
        }
        if (Character.isISOControl(ch)) {
            return false;
        }
        return !this.isHttpSeparator(ch);
    }

    protected boolean isHttpSeparator(char ch) {
        return HTTP_SEPARATORS.indexOf(ch) >= 0;
    }
}

