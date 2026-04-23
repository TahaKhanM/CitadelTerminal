/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;

public final class CharsToNameCanonicalizer {
    public static final int HASH_MULT = 33;
    protected static final int DEFAULT_TABLE_SIZE = 64;
    protected static final int MAX_TABLE_SIZE = 65536;
    static final int MAX_ENTRIES_FOR_REUSE = 12000;
    static final int MAX_COLL_CHAIN_LENGTH = 255;
    static final int MAX_COLL_CHAIN_FOR_REUSE = 63;
    static final CharsToNameCanonicalizer sBootstrapSymbolTable = new CharsToNameCanonicalizer();
    protected CharsToNameCanonicalizer _parent;
    private final int _hashSeed;
    protected final boolean _intern;
    protected final boolean _canonicalize;
    protected String[] _symbols;
    protected Bucket[] _buckets;
    protected int _size;
    protected int _sizeThreshold;
    protected int _indexMask;
    protected int _longestCollisionList;
    protected boolean _dirty;

    public static CharsToNameCanonicalizer createRoot() {
        long now = System.currentTimeMillis();
        int seed = (int)now + (int)(now >>> 32) | 1;
        return CharsToNameCanonicalizer.createRoot(seed);
    }

    protected static CharsToNameCanonicalizer createRoot(int hashSeed) {
        return sBootstrapSymbolTable.makeOrphan(hashSeed);
    }

    private CharsToNameCanonicalizer() {
        this._canonicalize = true;
        this._intern = true;
        this._dirty = true;
        this._hashSeed = 0;
        this._longestCollisionList = 0;
        this.initTables(64);
    }

    private void initTables(int initialSize) {
        this._symbols = new String[initialSize];
        this._buckets = new Bucket[initialSize >> 1];
        this._indexMask = initialSize - 1;
        this._size = 0;
        this._longestCollisionList = 0;
        this._sizeThreshold = CharsToNameCanonicalizer._thresholdSize(initialSize);
    }

    private static int _thresholdSize(int hashAreaSize) {
        return hashAreaSize - (hashAreaSize >> 2);
    }

    private CharsToNameCanonicalizer(CharsToNameCanonicalizer parent, boolean canonicalize, boolean intern, String[] symbols, Bucket[] buckets, int size2, int hashSeed, int longestColl) {
        this._parent = parent;
        this._canonicalize = canonicalize;
        this._intern = intern;
        this._symbols = symbols;
        this._buckets = buckets;
        this._size = size2;
        this._hashSeed = hashSeed;
        int arrayLen = symbols.length;
        this._sizeThreshold = CharsToNameCanonicalizer._thresholdSize(arrayLen);
        this._indexMask = arrayLen - 1;
        this._longestCollisionList = longestColl;
        this._dirty = false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CharsToNameCanonicalizer makeChild(boolean canonicalize, boolean intern) {
        int longestCollisionList;
        int hashSeed;
        int size2;
        Bucket[] buckets;
        String[] symbols;
        CharsToNameCanonicalizer charsToNameCanonicalizer = this;
        synchronized (charsToNameCanonicalizer) {
            symbols = this._symbols;
            buckets = this._buckets;
            size2 = this._size;
            hashSeed = this._hashSeed;
            longestCollisionList = this._longestCollisionList;
        }
        return new CharsToNameCanonicalizer(this, canonicalize, intern, symbols, buckets, size2, hashSeed, longestCollisionList);
    }

    private CharsToNameCanonicalizer makeOrphan(int seed) {
        return new CharsToNameCanonicalizer(null, true, true, this._symbols, this._buckets, this._size, seed, this._longestCollisionList);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void mergeChild(CharsToNameCanonicalizer child) {
        if (child.size() > 12000 || child._longestCollisionList > 63) {
            CharsToNameCanonicalizer charsToNameCanonicalizer = this;
            synchronized (charsToNameCanonicalizer) {
                this.initTables(64);
                this._dirty = false;
            }
        }
        if (child.size() <= this.size()) {
            return;
        }
        CharsToNameCanonicalizer charsToNameCanonicalizer = this;
        synchronized (charsToNameCanonicalizer) {
            this._symbols = child._symbols;
            this._buckets = child._buckets;
            this._size = child._size;
            this._sizeThreshold = child._sizeThreshold;
            this._indexMask = child._indexMask;
            this._longestCollisionList = child._longestCollisionList;
            this._dirty = false;
        }
    }

    public void release() {
        if (!this.maybeDirty()) {
            return;
        }
        if (this._parent != null) {
            this._parent.mergeChild(this);
            this._dirty = false;
        }
    }

    public int size() {
        return this._size;
    }

    public int bucketCount() {
        return this._symbols.length;
    }

    public boolean maybeDirty() {
        return this._dirty;
    }

    public int hashSeed() {
        return this._hashSeed;
    }

    public int collisionCount() {
        int count2 = 0;
        for (Bucket bucket : this._buckets) {
            if (bucket == null) continue;
            count2 += bucket.length();
        }
        return count2;
    }

    public int maxCollisionLength() {
        return this._longestCollisionList;
    }

    public String findSymbol(char[] buffer, int start, int len, int h) {
        if (len < 1) {
            return "";
        }
        if (!this._canonicalize) {
            return new String(buffer, start, len);
        }
        int index = this._hashToIndex(h);
        String sym = this._symbols[index];
        if (sym != null) {
            Bucket b;
            if (sym.length() == len) {
                int i = 0;
                while (sym.charAt(i) == buffer[start + i] && ++i < len) {
                }
                if (i == len) {
                    return sym;
                }
            }
            if ((b = this._buckets[index >> 1]) != null && (sym = b.find(buffer, start, len)) != null) {
                return sym;
            }
        }
        if (!this._dirty) {
            this.copyArrays();
            this._dirty = true;
        } else if (this._size >= this._sizeThreshold) {
            this.rehash();
            index = this._hashToIndex(this.calcHash(buffer, start, len));
        }
        String newSymbol = new String(buffer, start, len);
        if (this._intern) {
            newSymbol = InternCache.instance.intern(newSymbol);
        }
        ++this._size;
        if (this._symbols[index] == null) {
            this._symbols[index] = newSymbol;
        } else {
            Bucket newB;
            int bix = index >> 1;
            this._buckets[bix] = newB = new Bucket(newSymbol, this._buckets[bix]);
            this._longestCollisionList = Math.max(newB.length(), this._longestCollisionList);
            if (this._longestCollisionList > 255) {
                this.reportTooManyCollisions(255);
            }
        }
        return newSymbol;
    }

    public int _hashToIndex(int rawHash) {
        rawHash += rawHash >>> 15;
        return rawHash & this._indexMask;
    }

    public int calcHash(char[] buffer, int start, int len) {
        int hash = this._hashSeed;
        for (int i = 0; i < len; ++i) {
            hash = hash * 33 + buffer[i];
        }
        return hash == 0 ? 1 : hash;
    }

    public int calcHash(String key) {
        int len = key.length();
        int hash = this._hashSeed;
        for (int i = 0; i < len; ++i) {
            hash = hash * 33 + key.charAt(i);
        }
        return hash == 0 ? 1 : hash;
    }

    private void copyArrays() {
        String[] oldSyms = this._symbols;
        int size2 = oldSyms.length;
        this._symbols = new String[size2];
        System.arraycopy(oldSyms, 0, this._symbols, 0, size2);
        Bucket[] oldBuckets = this._buckets;
        size2 = oldBuckets.length;
        this._buckets = new Bucket[size2];
        System.arraycopy(oldBuckets, 0, this._buckets, 0, size2);
    }

    private void rehash() {
        int i;
        int size2 = this._symbols.length;
        int newSize = size2 + size2;
        if (newSize > 65536) {
            this._size = 0;
            Arrays.fill(this._symbols, null);
            Arrays.fill(this._buckets, null);
            this._dirty = true;
            return;
        }
        String[] oldSyms = this._symbols;
        Bucket[] oldBuckets = this._buckets;
        this._symbols = new String[newSize];
        this._buckets = new Bucket[newSize >> 1];
        this._indexMask = newSize - 1;
        this._sizeThreshold = CharsToNameCanonicalizer._thresholdSize(newSize);
        int count2 = 0;
        int maxColl = 0;
        for (i = 0; i < size2; ++i) {
            Bucket newB;
            String symbol = oldSyms[i];
            if (symbol == null) continue;
            ++count2;
            int index = this._hashToIndex(this.calcHash(symbol));
            if (this._symbols[index] == null) {
                this._symbols[index] = symbol;
                continue;
            }
            int bix = index >> 1;
            this._buckets[bix] = newB = new Bucket(symbol, this._buckets[bix]);
            maxColl = Math.max(maxColl, newB.length());
        }
        size2 >>= 1;
        for (i = 0; i < size2; ++i) {
            for (Bucket b = oldBuckets[i]; b != null; b = b.getNext()) {
                Bucket newB;
                ++count2;
                String symbol = b.getSymbol();
                int index = this._hashToIndex(this.calcHash(symbol));
                if (this._symbols[index] == null) {
                    this._symbols[index] = symbol;
                    continue;
                }
                int bix = index >> 1;
                this._buckets[bix] = newB = new Bucket(symbol, this._buckets[bix]);
                maxColl = Math.max(maxColl, newB.length());
            }
        }
        this._longestCollisionList = maxColl;
        if (count2 != this._size) {
            throw new Error("Internal error on SymbolTable.rehash(): had " + this._size + " entries; now have " + count2 + ".");
        }
    }

    protected void reportTooManyCollisions(int maxLen) {
        throw new IllegalStateException("Longest collision chain in symbol table (of size " + this._size + ") now exceeds maximum, " + maxLen + " -- suspect a DoS attack based on hash collisions");
    }

    static final class Bucket {
        private final String _symbol;
        private final Bucket _next;
        private final int _length;

        public Bucket(String symbol, Bucket next2) {
            this._symbol = symbol;
            this._next = next2;
            this._length = next2 == null ? 1 : next2._length + 1;
        }

        public String getSymbol() {
            return this._symbol;
        }

        public Bucket getNext() {
            return this._next;
        }

        public int length() {
            return this._length;
        }

        public String find(char[] buf, int start, int len) {
            String sym = this._symbol;
            Bucket b = this._next;
            while (true) {
                if (sym.length() == len) {
                    int i = 0;
                    while (sym.charAt(i) == buf[start + i] && ++i < len) {
                    }
                    if (i == len) {
                        return sym;
                    }
                }
                if (b == null) break;
                sym = b.getSymbol();
                b = b.getNext();
            }
            return null;
        }
    }
}

