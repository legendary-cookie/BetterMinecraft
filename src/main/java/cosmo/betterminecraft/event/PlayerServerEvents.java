package cosmo.betterminecraft.event;

import cosmo.betterminecraft.Core;
import cosmo.betterminecraft.database.BankDb;
import cosmo.betterminecraft.player.PlayerWrapper;
import cosmo.betterminecraft.scoreboard.ScoreboardStuff;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerServerEvents implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        // Add player to PlayerWrapper map
        Core.players.put(e.getPlayer(), new PlayerWrapper(e.getPlayer()));
        // Send welcome title
        e.getPlayer().sendTitle(ChatColor.GOLD + "Welcome!", ChatColor.GOLD + "We are setting everything up for you!", 5, 100, 5);
        // Set health to max health
        Core.players.get(e.getPlayer()).setHealth(Core.players.get(e.getPlayer()).getMaxHealth());
        // Check if player has bank account, if not, create one
        BankDb bank = Core.getInstance().getBankDatabase();
        UUID uuid = e.getPlayer().getUniqueId();
        bank.createPlayerAccount(uuid);
        // Setup scoreboard
        ScoreboardStuff.updateScoreboard(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Core.players.remove(e.getPlayer());
    }
}
