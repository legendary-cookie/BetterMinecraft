package cosmo.betterminecraft.event;

import cosmo.betterminecraft.Core;
import cosmo.betterminecraft.player.PlayerWrapper;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerServerEvents implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Core.players.put(e.getPlayer(), new PlayerWrapper(e.getPlayer()));
        e.getPlayer().sendTitle(ChatColor.GOLD + "Welcome!", ChatColor.GOLD + "We are setting everything up for you!", 1, 10, 1);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Core.players.remove(e.getPlayer());
    }
}
