/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Collection;
import java.util.HashSet;
import java.util.OptionalInt;
import java.util.Set;

public class ServerConfig {
    public final Duration startSplay;
    public final int maxSimultaneousGames;
    public final boolean disableAlgoSecurity;
    public final boolean printIOErrors;
    public final boolean redirectOut;
    public final boolean algoLocalOutput;
    public final Duration downloadTimeLimit;
    public final String overrideAlgoPath;
    public final OptionalInt overridePort;
    public final String sslKeystore;
    public final String sslPassword;
    public final Set<String> findAlgoEntrypointExclude;
    public final boolean enableSlotting;

    public ServerConfig(String configFile) throws Exception {
        JsonObject data = (JsonObject)Jsoner.deserialize(new String(Files.readAllBytes(Paths.get(configFile, new String[0]))));
        this.startSplay = Duration.ofMillis(data.getInteger(Jsoner.mintJsonKey("startSplayMillis", null)).intValue());
        this.maxSimultaneousGames = data.getInteger(Jsoner.mintJsonKey("maxSimultaneousGames", null));
        this.disableAlgoSecurity = data.getBoolean(Jsoner.mintJsonKey("disableAlgoSecurity", null));
        this.printIOErrors = data.getBoolean(Jsoner.mintJsonKey("printIOErrors", null));
        this.redirectOut = data.getBoolean(Jsoner.mintJsonKey("redirectOut", null));
        this.algoLocalOutput = data.getBoolean(Jsoner.mintJsonKey("algoLocalOutput", null));
        this.downloadTimeLimit = Duration.ofSeconds(data.getLong(Jsoner.mintJsonKey("downloadTimeLimitSeconds", null)));
        this.overrideAlgoPath = data.getString(Jsoner.mintJsonKey("overrideAlgoPath", null));
        int overridePort = data.getInteger(Jsoner.mintJsonKey("overridePort", null));
        this.overridePort = overridePort > 0 ? OptionalInt.of(overridePort) : OptionalInt.empty();
        this.sslKeystore = data.getString(Jsoner.mintJsonKey("sslKeystore", null));
        this.sslPassword = data.getString(Jsoner.mintJsonKey("sslPassword", null));
        this.findAlgoEntrypointExclude = new HashSet<String>();
        Object collection = data.getCollection(Jsoner.mintJsonKey("findAlgoEntrypointExclude", null));
        this.findAlgoEntrypointExclude.addAll((Collection<String>)collection);
        this.enableSlotting = data.getBoolean(Jsoner.mintJsonKey("enableSlotting", null));
    }
}

