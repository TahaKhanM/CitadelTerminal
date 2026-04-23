/*
 * Decompiled with CFR 0.152.
 */
package towersocket.impl;

import java.util.concurrent.ThreadLocalRandom;

public class RandID {
    public static String randID(int length) {
        StringBuilder builder = new StringBuilder(length);
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i < length; ++i) {
            builder.append((char)rand.nextInt(33, 126));
        }
        return builder.toString();
    }
}

