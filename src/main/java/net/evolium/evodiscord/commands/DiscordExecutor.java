package net.evolium.evodiscord.commands;

import net.evolium.evodiscord.discord.EvoBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DiscordExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player pl)) {
            return true;
        }

        if (args.length == 0) {
            return false;
        }

        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(" ").append(arg);
        }

        EvoBuilder.sendDiscordMessage("**" + pl.getDisplayName() + "**: " + message);
        Bukkit.broadcastMessage((ChatColor.translateAlternateColorCodes('&', ChatColor.DARK_GREEN + "(minecraft) " + "&a" + pl.getDisplayName() + ": &r" + message)));
        pl.playSound(pl, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);

        return true;
    }
}
