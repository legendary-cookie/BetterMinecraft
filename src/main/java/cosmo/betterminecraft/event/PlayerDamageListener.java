package cosmo.betterminecraft.event;

import cosmo.betterminecraft.Core;
import cosmo.betterminecraft.player.PlayerWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamage(EntityDamageEvent event) {
        double damage = event.getDamage();
        event.setDamage(0.0D);
        if (!(event.getEntity() instanceof Player)) return;
        Player p = (Player) event.getEntity();
        PlayerWrapper pw = Core.players.get(p);
        pw.setHealth(pw.getHealth() - damage);
        if (pw.getHealth() <= 0) {
            p.setHealth(0.0D);
            pw.setHealth(pw.getMaxHealth());
            return;
        }
        p.setHealth(20.0D);
    }
}
