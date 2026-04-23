/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.units;

public enum UnitMask {
    P1WalkersGetHit,
    P1TowersGetHit,
    TargetBothOfP2sUnits,
    TargetP2sWalkers,
    TargetP2sTowers,
    P2WalkersGetHit,
    P2TowersGetHit,
    TargetBothOfP1sUnits,
    TargetP1sWalkers,
    TargetP1sTowers;

    public int b = 1 << this.ordinal();
}

