package net.evolium.evodiscord.discord.events;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.evolium.evodiscord.EvoDiscord;
import net.evolium.evodiscord.discord.EvoBuilder;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ReadyEvent extends ListenerAdapter {

    private EvoDiscord main = EvoDiscord.getInstance();

    @Override
    public void onReady(@NotNull net.dv8tion.jda.api.events.session.ReadyEvent event) {
        main.setChannel(main.getBotInstance().getTextChannelById("1131932591726006292"));

        // Register commands
        main.getBotInstance().getGuildById("993168179188736050").updateCommands().addCommands(
                Commands.slash("stop", "Stop the server")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.NICKNAME_CHANGE)),
                Commands.slash("list", "Send back the list of all player that are on the server right now")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.NICKNAME_CHANGE)),
                Commands.slash("minecraft", "Allow you to send message to the minecraft server")
                        .addOption(OptionType.STRING, "message", "The message to send.", true, true)
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.NICKNAME_CHANGE))

        ).queue();

        EvoBuilder.sendEmbed(Color.GREEN, "Le serveur vient d'être lancé !");

        if (!main.getServer().getPluginManager().isPluginEnabled("EvoPlugin")) {
            EvoBuilder.sendEmbed(Color.YELLOW, "EvoPlugin crashed", "L'EvoPlugin s'est mal lancé ou n'a pas été mis sur le serveur !");
        }
    }
}
