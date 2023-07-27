package net.evolium.evodiscord.discord.events;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.evolium.evodiscord.EvoDiscord;
import net.evolium.evodiscord.discord.EvoBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class CommandEvent extends ListenerAdapter {

    private EvoDiscord main = EvoDiscord.getInstance();

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (!event.getChannel().getId().equals(main.getChannel().getId())) {
            event.replyEmbeds(EvoBuilder.createEmbed(Color.ORANGE, "Vous ne pouvez pas faire cette action dans ce channel")).queue();
            return;
        }

        if (event.getName().equals("stop")) {
            Bukkit.shutdown();
            return;
        }

        if (event.getName().equals("list")) {
            if (Bukkit.getOnlinePlayers().isEmpty()) {
                event.replyEmbeds(EvoBuilder.createEmbed(Color.ORANGE, "Aucun joueur n'est sur le serveur pour le moment")).queue();
                return;
            }

            if (Bukkit.getOnlinePlayers().size() > 10) {
                event.replyEmbeds(EvoBuilder.createEmbed(Color.ORANGE, "Il y a plus de 10 joueurs connéctés sur le serveur", "je ne suis pas en capacité d'afficher tous leur nom")).queue();
                return;
            }

            StringBuilder sb = new StringBuilder();

            for (Player pl : Bukkit.getOnlinePlayers()) {
                sb.append("- ").append(pl.getDisplayName()).append("\n");
            }

            event.replyEmbeds(EvoBuilder.createEmbed(Color.GREEN, "Liste des joueurs connéctés", sb.toString())).queue();
            return;
        }

        if(event.getName().equals("minecraft")) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.DARK_PURPLE + "(discord) " + ChatColor.LIGHT_PURPLE + event.getUser().getName() + ": &r" + event.getOption("message").getAsString()));
            event.reply("**" + event.getUser().getName() + "**: " + event.getOption("message").getAsString()).queue();
        }
    }
}
