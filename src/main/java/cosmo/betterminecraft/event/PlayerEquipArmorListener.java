package cosmo.betterminecraft.event;

import cosmo.betterminecraft.event.custom.ArmorEquipEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerEquipArmorListener implements Listener {
    @EventHandler
    public void onEquipArmor(ArmorEquipEvent event) {
        event.getPlayer().sendRawMessage("fy");
    }
}
