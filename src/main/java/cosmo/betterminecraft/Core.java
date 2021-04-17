package cosmo.betterminecraft;

import cosmo.Cosmotil.*;
import cosmo.betterminecraft.commands.GetHealthCommand;
import cosmo.betterminecraft.commands.GiveCustomItemCommand;
import cosmo.betterminecraft.commands.KillTestCommand;
import cosmo.betterminecraft.event.PlayerDamageListener;
import cosmo.betterminecraft.event.PlayerDeathEvents;
import cosmo.betterminecraft.event.PlayerServerEvents;
import cosmo.betterminecraft.gui.GuiInstances;
import cosmo.betterminecraft.items.InfernoSword;
import cosmo.betterminecraft.items.REU;
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
    private GuiInstances guiInstances;
    // Manager
    private REU reu;

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        Cosmotil.helloWorld();
    }

    @Override
    public void onEnable() {
        // Create an instance for easy access
        instance = this;
        // Config
        this.saveDefaultConfig();
        this.config = getConfig();
        // Check if players are online, if yes, add them to the Players Map.
        // Used to get a players PlayerWrapper.
        if (getServer().getOnlinePlayers().size() > 0) {
            for (Player play : getServer().getOnlinePlayers()) {
                players.put(play, new PlayerWrapper(play));
            }
        }
        // Register Events here
        pm.registerEvents(new PlayerServerEvents(), this);
        pm.registerEvents(new PlayerDamageListener(), this);
        pm.registerEvents(new PlayerDeathEvents(), this);
        // Register commands here
        this.getCommand("killtest").setExecutor(new KillTestCommand());
        this.getCommand("gethealth").setExecutor(new GetHealthCommand());
        this.getCommand("ic").setExecutor(new GiveCustomItemCommand());
        /* ITEMS */
        reu = new REU();
        // Instances of all items
        InfernoSword infernoSword = new InfernoSword();
        // Register Items
        reu.registerItem("infernosword", infernoSword.create());
        // Register Recipes
        reu.registerRecipe(infernoSword.createRecipe(infernoSword.create()));
        /* GUI */
        this.guiInstances = new GuiInstances();
    }

    public REU getReu() {
        return reu;
    }

    public GuiInstances getGuiInstances() {
        return guiInstances;
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll();
        reu.unregisterRecipes();
    }
}
