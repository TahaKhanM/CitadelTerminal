/*
 * Decompiled with CFR 0.152.
 */
package net.lingala.zip4j.crypto.PBKDF2;

import net.lingala.zip4j.crypto.PBKDF2.BinTools;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Parameters;

class PBKDF2HexFormatter {
    PBKDF2HexFormatter() {
    }

    public boolean fromString(PBKDF2Parameters p, String s2) {
        if (p == null || s2 == null) {
            return true;
        }
        String[] p123 = s2.split(":");
        if (p123 == null || p123.length != 3) {
            return true;
        }
        byte[] salt = BinTools.hex2bin(p123[0]);
        int iterationCount = Integer.parseInt(p123[1]);
        byte[] bDK = BinTools.hex2bin(p123[2]);
        p.setSalt(salt);
        p.setIterationCount(iterationCount);
        p.setDerivedKey(bDK);
        return false;
    }

    public String toString(PBKDF2Parameters p) {
        String s2 = BinTools.bin2hex(p.getSalt()) + ":" + String.valueOf(p.getIterationCount()) + ":" + BinTools.bin2hex(p.getDerivedKey());
        return s2;
    }
}

