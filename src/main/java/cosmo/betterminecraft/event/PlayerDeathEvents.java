package cosmo.betterminecraft.event;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class PlayerDeathEvents implements Listener {

    @EventHandler
    public void onPlayerDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setDeathSound(Sound.ENTITY_ENDERMAN_DEATH);
        }
    }

}
