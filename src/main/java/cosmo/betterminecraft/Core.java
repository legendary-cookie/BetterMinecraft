package cosmo.betterminecraft;

import cosmo.Cosmotil.*;
import cosmo.betterminecraft.commands.GetHealthCommand;
import cosmo.betterminecraft.commands.KillTestCommand;
import cosmo.betterminecraft.event.PlayerDamageListener;
import cosmo.betterminecraft.event.PlayerDeathEvents;
import cosmo.betterminecraft.event.PlayerServerEvents;
import cosmo.betterminecraft.player.PlayerWrapper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Core extends JavaPlugin {
    public static Map<Player, PlayerWrapper> players = new HashMap<>();
    private PluginManager pm = getServer().getPluginManager();
    public FileConfiguration config;
    private static Core instance;

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        Cosmotil.helloWorld();
    }

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        this.config = getConfig();
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
