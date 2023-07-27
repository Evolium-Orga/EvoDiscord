package net.evolium.evodiscord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.evolium.evodiscord.commands.DiscordExecutor;
import net.evolium.evodiscord.discord.EvoBuilder;
import net.evolium.evodiscord.discord.events.CommandEvent;
import net.evolium.evodiscord.discord.events.ReadyEvent;
import net.evolium.evodiscord.listeners.JoinManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

public class EvoDiscord extends JavaPlugin {

    private static EvoDiscord INSTANCE;

    private TextChannel channel;

    private JDA botInstance;

    @Override
    public void onEnable() {

        INSTANCE = this;

        launchBot();

        setListeners();
        setCommands();
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();

        EvoBuilder.sendEmbed(Color.RED, "Le serveur vient de s'éteindre !");
    }

    private void setListeners() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new JoinManager(), this);
    }

    private void setCommands() {
        getCommand("discord").setExecutor(new DiscordExecutor());
    }

    private void launchBot() {
        botInstance = JDABuilder.createDefault("MTA1MTUxNzEyNDYyMDg2MTUyMQ.GNa4N5.9BFQJbOVAqNuKXpIo621pYSuymRuencfqRmjss")
                .setActivity(Activity.playing("Evolium new MMORPG ! Alpha -> 2024"))
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new ReadyEvent())
                .addEventListeners(new CommandEvent())
                .build();
    }

    public static EvoDiscord getInstance() {
        return INSTANCE;
    }

    public JDA getBotInstance() {
        return botInstance;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public void setChannel(TextChannel channel) {
        this.channel = channel;
    }
}
