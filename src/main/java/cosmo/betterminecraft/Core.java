package cosmo.betterminecraft;

import cosmo.Cosmotil.Cosmotil;
import cosmo.Cosmotil.database.MariaDB;
import cosmo.betterminecraft.commands.GetHealthCommand;
import cosmo.betterminecraft.commands.GiveCustomItemCommand;
import cosmo.betterminecraft.commands.KillTestCommand;
import cosmo.betterminecraft.database.BankDb;
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
import java.util.logging.Logger;

public class Core extends JavaPlugin {
    // PlayerWrapper to access health and so on
    public static Map<Player, PlayerWrapper> players = new HashMap<>();
    // The PluginManager, used for registering events
    private PluginManager pm = getServer().getPluginManager();
    // The config file
    public FileConfiguration config;
    // Instance of this class, for access to public functions
    private static Core instance;
    // Instances for accessing guis
    private GuiInstances guiInstances;
    // Manager
    private REU reu;
    // Logger
    private static final Logger log = Logger.getLogger("Minecraft");
    // DB stuff
    private MariaDB db = new MariaDB("db", "3306", "betterminecraft", "root", "secret");
    private BankDb bank;

    /**
     * @return Instance of the Core class
     */
    public static Core getInstance() {
        return instance;
    }


    /**
     * Executed on load
     */
    @Override
    public void onLoad() {
        Cosmotil.helloWorld();
    }

    /**
     * Executed on enable
     * Here everything gets set up.
     */
    @Override
    public void onEnable() {
        // Create an instance for easy access
        instance = this;
        // Config
        this.saveDefaultConfig();
        this.config = getConfig();
        // Connect to the Database
        db.connect();
        bank.setupTables();
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


    /**
     * Returns The manager for items and recipes
     *
     * @return instance of REU
     */
    public REU getReu() {
        return reu;
    }


    /**
     * @return instance of the guiInstances
     */
    public GuiInstances getGuiInstances() {
        return guiInstances;
    }

    /**
     * Unregister everything
     */
    @Override
    public void onDisable() {
        HandlerList.unregisterAll();
        reu.unregisterRecipes();
        // Disconnect from the database
        db.disconnect();
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }


    public String getCurrencyNamePlural() {
        return "coins";
    }

    public String getCurrencyNameSingular() {
        return "coin";
    }

    public MariaDB getDatabase() {
        return db;
    }
}
