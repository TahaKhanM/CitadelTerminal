/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game;

import com.c1games.terminal.game.player.Player;
import java.util.Map;
import java.util.Optional;

public interface TowerGame {
    public void run(Map<String, Object> var1, Player var2, Player var3, Optional<String> var4) throws InterruptedException;
}

