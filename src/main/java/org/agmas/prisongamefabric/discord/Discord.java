package org.agmas.prisongamefabric.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.agmas.prisongamefabric.discord.listeners.ReadyListener;
import org.agmas.prisongamefabric.util.config.ModConfigs;

public class Discord {
    public static void init() {

        JDA jda = JDABuilder.createDefault(ModConfigs.DISCORDTOKEN, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT).addEventListeners(new ReadyListener()).build();

    }
}
