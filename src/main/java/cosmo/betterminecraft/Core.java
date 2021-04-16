package cosmo.betterminecraft;

import cosmo.betterminecraft.commands.GetHealthCommand;
import cosmo.betterminecraft.commands.KillTestCommand;
import cosmo.betterminecraft.event.PlayerDamageListener;
import cosmo.betterminecraft.event.PlayerDeathEvents;
import cosmo.betterminecraft.event.PlayerServerEvents;
import cosmo.betterminecraft.player.PlayerWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Core extends JavaPlugin {
    public static Map<Player, PlayerWrapper> players = new HashMap<>();
    private PluginManager pm = getServer().getPluginManager();

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
        if (getServer().getOnlinePlayers().size() > 0) {
            for (Player play : getServer().getOnlinePlayers()) {
                players.put(play, new PlayerWrapper(play));
            }
        }
        pm.registerEvents(new PlayerServerEvents(), this);
        pm.registerEvents(new PlayerDamageListener(), this);
        pm.registerEvents(new PlayerDeathEvents(), this);
        this.getCommand("killtest").setExecutor(new KillTestCommand());
        this.getCommand("gethealth").setExecutor(new GetHealthCommand());
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll();
    }
}
