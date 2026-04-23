/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground;

import com.c1games.terminal.Terminal;
import com.c1games.terminal.playground.ServerConfig;
import com.c1games.terminal.playground.connection.GameConnection;
import com.c1games.terminal.playground.player.factory.PlayerMetaFactory;
import com.c1games.terminal.playground.poller.SingleThreadedPoller;
import com.c1games.terminal.playground.scheduler.FixedConcurrencyProcessScheduler;
import com.c1games.terminal.playground.serializer.LazyDes;
import com.c1games.terminal.util.ManagerDownloader;
import com.c1games.terminal.util.SessionBufferer;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import towersocket.ConnectionBuilder;
import towersocket.Greeter;
import towersocket.NewConnection;

public class ClientGreeter
implements Greeter {
    private final ServerConfig serverConfig;
    private final PlayerMetaFactory playerMetaFactory;
    private final SessionBufferer sessionBufferer;

    public ClientGreeter(ServerConfig serverConfig) throws IOException {
        this.serverConfig = serverConfig;
        Gson gson = new Gson();
        FixedConcurrencyProcessScheduler scheduler = new FixedConcurrencyProcessScheduler(Runtime.getRuntime().availableProcessors());
        SingleThreadedPoller poller = new SingleThreadedPoller(10L);
        this.playerMetaFactory = new PlayerMetaFactory(serverConfig, scheduler, poller);
        this.sessionBufferer = new SessionBufferer(serverConfig.startSplay, serverConfig.maxSimultaneousGames);
    }

    @Override
    public Optional<Consumer<ConnectionBuilder>> greet(NewConnection newConnection) {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Connection started time: " + dateFormat.format(dateNow));
        try {
            JsonObject request = (JsonObject)((LazyDes)newConnection.handshakeMessage).getObj();
            String player1 = request.getString(Jsoner.mintJsonKey("player_1", null));
            String player2 = request.getString(Jsoner.mintJsonKey("player_2", null));
            String startString = request.getString(Jsoner.mintJsonKey("start_string", null));
            String matchName = request.getString(Jsoner.mintJsonKey("match_name", null));
            String configId = request.getString(Jsoner.mintJsonKey("config_id", null));
            String configType = request.getString(Jsoner.mintJsonKey("config_type", ""));
            String configDescriptor = configType + configId;
            Map<String, Object> currentSettings = new HashMap();
            if (configId != null && configId != "") {
                try {
                    File config = new File("./configs/config_" + configDescriptor + ".json");
                    if (!config.exists()) {
                        config = Terminal.managerDownloader.getConfig(configId, configType, "config_" + configDescriptor + ".json");
                    } else {
                        System.out.println("Using cached config " + configDescriptor);
                    }
                    String currentConfig = new String(Files.readAllBytes(config.toPath()));
                    System.out.println("Using config: " + currentConfig);
                    Gson gson = new Gson();
                    currentSettings = (Map)gson.fromJson(currentConfig, new TypeToken<Map<String, Object>>(){}.getType());
                }
                catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Could not download config: " + configDescriptor + " using default config");
                    return Optional.empty();
                }
            }
            try {
                System.out.println("Getting config variant");
                ManagerDownloader.LatestVariant variant = ManagerDownloader.getLatestVariant();
                String configName = "config_built-in" + variant.config + ".json";
                File config = new File("./configs/" + configName);
                if (!config.exists()) {
                    config = Terminal.managerDownloader.getConfig("" + variant.config, "built-in", configName);
                } else {
                    System.out.println("Using cached config " + configName);
                }
                String currentConfig = new String(Files.readAllBytes(config.toPath()));
                System.out.println("Using config latest variant: " + currentConfig);
                Gson gson = new Gson();
                currentSettings = (Map)gson.fromJson(currentConfig, new TypeToken<Map<String, Object>>(){}.getType());
            }
            catch (IOException e) {
                e.printStackTrace();
                System.err.println("Could not download config: " + configDescriptor + " using default config");
                return Optional.empty();
            }
            boolean printPlayerErrors = (Boolean)((Map)currentSettings.get("debug")).get("printBotErrors");
            boolean oldConfigP1 = request.getBooleanOrDefault(Jsoner.mintJsonKey("p1_use_old_config", false));
            boolean oldConfigP2 = request.getBooleanOrDefault(Jsoner.mintJsonKey("p2_use_old_config", false));
            if (oldConfigP1) {
                currentSettings.put("seasonCompatibilityModeP1", 4.0);
                System.out.println("P1 Running in Compatibility Mode");
            }
            if (oldConfigP2) {
                currentSettings.put("seasonCompatibilityModeP2", 4.0);
                System.out.println("P2 Running in Compatibility Mode");
            }
            GameConnection connection = new GameConnection(this.serverConfig, this.playerMetaFactory.make(player1, 1, printPlayerErrors), this.playerMetaFactory.make(player2, 2, printPlayerErrors), currentSettings, Optional.ofNullable(startString), this.sessionBufferer, matchName);
            connection.setDebugName(request.getStringOrDefault(Jsoner.mintJsonKey("game_name", "null")));
            return Optional.of(connection);
        }
        catch (Exception e) {
            System.out.println("Exception in greeter");
            e.printStackTrace();
            return Optional.empty();
        }
    }
}

