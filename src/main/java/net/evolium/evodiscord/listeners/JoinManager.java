package net.evolium.evodiscord.listeners;

import net.evolium.evodiscord.EvoDiscord;
import net.evolium.evodiscord.discord.EvoBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;

public class JoinManager implements Listener {

    EvoDiscord main = EvoDiscord.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player pl = e.getPlayer();

        EvoBuilder.sendEmbed(Color.CYAN ,pl.getDisplayName() + " vient de rejoindre le serveur");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player pl = e.getPlayer();

        EvoBuilder.sendEmbed(Color.BLUE, pl.getDisplayName() + " vient de quitter le serveur");
    }
}
