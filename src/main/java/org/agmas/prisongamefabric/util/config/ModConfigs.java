package org.agmas.prisongamefabric.util.config;

import com.mojang.datafixers.util.Pair;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static String DISCORDTOKEN;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of("pbf_config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("discord.token", "ENTERHERE"), "Discord Token");
    }

    private static void assignConfigs() {
        DISCORDTOKEN = CONFIG.getOrDefault("discord.token", "Nothing");

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}