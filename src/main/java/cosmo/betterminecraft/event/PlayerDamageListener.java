package cosmo.betterminecraft.event;

import cosmo.betterminecraft.Core;
import cosmo.betterminecraft.player.PlayerWrapper;
import net.kyori.adventure.sound.Sound;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamage(EntityDamageEvent event) {
        double damage = event.getDamage();
        if (!(event.getEntity() instanceof Player)) return;
        Player p = (Player) event.getEntity();
        PlayerWrapper pw = Core.players.get(p);
        pw.setHealth(pw.getHealth() - damage);
        event.setCancelled(true);
        if (pw.getHealth() <= 0) {
            pw.tp(p.getBedSpawnLocation());
            pw.playDeathSound();
            pw.setHealth(pw.getMaxHealth());
            return;
        }
        p.setHealth(20.0D);
    }
}