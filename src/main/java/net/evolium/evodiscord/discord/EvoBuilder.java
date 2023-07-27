package net.evolium.evodiscord.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.evolium.evodiscord.EvoDiscord;

import java.awt.*;

public class EvoBuilder {

    private static final EvoDiscord main = EvoDiscord.getInstance();

    public static MessageEmbed createEmbed(Color color, String title, String... desc) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(color);
        eb.setTitle(title);
        if (desc != null && desc.length != 0) {
            eb.setDescription(desc[0] + "\n ");
        }
        eb.setAuthor("EvoDiscord - " + main.getDescription().getVersion());
        eb.setFooter("IP :" + main.getConfig().getString("server_ip"), "https://media.discordapp.net/attachments/674315302745407489/1131514508435787786/logo.png?width=676&height=676");
        return eb.build();
    }

    public static void sendEmbed(Color color, String title, String... desc) {
        MessageEmbed embed;

        if (desc != null) {
            embed = createEmbed(color, title, desc);
        } else {
            embed = createEmbed(color, title);
        }
        main.getChannel().sendMessageEmbeds(embed).queue();
    }

    public static void sendDiscordMessage(String message) {
        main.getChannel().sendMessage(message).queue();
    }
}
