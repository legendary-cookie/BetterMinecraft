package cosmo.betterminecraft.event;

import cosmo.betterminecraft.event.custom.EconomyUpdateEvent;
import cosmo.betterminecraft.scoreboard.ScoreboardStuff;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EconomyListener implements Listener {
    @EventHandler
    public void onChange(EconomyUpdateEvent event) {
        ScoreboardStuff.updateScoreboard(event.getPlayer());
    }
}
