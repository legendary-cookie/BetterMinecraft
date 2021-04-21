package cosmo.betterminecraft.event.custom;


import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class EconomyUpdateEvent extends Event {
    private Player player;
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public EconomyUpdateEvent(Player player) {
        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public Player getPlayer() {
        return player;
    }

}
