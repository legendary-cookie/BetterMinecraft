package cosmo.betterminecraft;

import cosmo.Cosmotil.Cosmotil;
import cosmo.Cosmotil.database.MariaDB;
import cosmo.betterminecraft.commands.CommandRegistry;
import cosmo.betterminecraft.commands.impl.GetBankBalanceCommand;
import cosmo.betterminecraft.commands.impl.GetHealthCommand;
import cosmo.betterminecraft.commands.impl.GiveCustomItemCommand;
import cosmo.betterminecraft.commands.impl.MoneyGuiCommand;
import cosmo.betterminecraft.database.BankDb;
import cosmo.betterminecraft.event.*;
import cosmo.betterminecraft.event.custom.ArmorListener;
import cosmo.betterminecraft.event.custom.DispenserArmorListener;
import cosmo.betterminecraft.gui.GuiInstances;
import cosmo.betterminecraft.items.InfernoSword;
import cosmo.betterminecraft.items.REU;
import cosmo.betterminecraft.player.PlayerWrapper;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The Plugin's core class
 * <p>
 * Everything should get setup here
 *
 * @author Vento
 * @since 0.1.0
 */
public class Core extends JavaPlugin {
    // PlayerWrapper to access health and so on
    public static Map<Player, PlayerWrapper> players = new HashMap<>();
    // The PluginManager, used for registering events
    private final PluginManager pm = getServer().getPluginManager();
    // The config file
    public FileConfiguration config;
    // Instance of this class, for access to public functions
    @Getter
    private static Core instance;
    // Instances for accessing guis
    @Getter
    private GuiInstances guiInstances;
    // Manager
    private REU reu;
    // Logger
    private static final Logger log = Logger.getLogger("Minecraft");
    // DB stuff
    private final MariaDB db = new MariaDB("db", "3306", "betterminecraft", "root", "secret");
    private final BankDb bank = new BankDb(db);
    // Command Registry
    private final CommandRegistry commandRegistry = new CommandRegistry();

    /**
     * Executed on load
     *
     * @author Vento
     * @since 0.1.0
     */
    @Override
    public void onLoad() {
        Cosmotil.helloWorld();
    }

    /**
     * Executed on enable
     * Here everything gets set up.
     *
     * @author Vento
     * @since 0.1.0
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
        pm.registerEvents(new EconomyListener(), this);
        pm.registerEvents(new PlayerEquipArmorListener(), this);
        pm.registerEvents(new ArmorListener(getConfig().getStringList("blocked")), this);
        try {
            Class.forName("org.bukkit.event.block.BlockDispenseArmorEvent");
            getServer().getPluginManager().registerEvents(new
                    DispenserArmorListener(), this);
        } catch (Exception ignored) {
        }
        // Register commands
        commandRegistry.init();
        ////this.getCommand("killtest").setExecutor(new KillTestCommand());
        this.getCommand("gethealth").setExecutor(new GetHealthCommand());
        this.getCommand("ic").setExecutor(new GiveCustomItemCommand());
        this.getCommand("balance").setExecutor(new GetBankBalanceCommand());
        this.getCommand("moneygui").setExecutor(new MoneyGuiCommand());
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
     * @author Vento
     * @since 0.1.0
     */
    public REU getReu() {
        return reu;
    }


    /**
     * Unregister everything
     *
     * @author Vento
     * @since 0.1.0
     */
    @Override
    public void onDisable() {
        HandlerList.unregisterAll();
        reu.unregisterRecipes();
        // Disconnect from the database
        db.disconnect();
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    /**
     * @author Vento
     * @since 0.1.0
     */
    public String getCurrencyNamePlural() {
        return "coins";
    }

    /**
     * @author Vento
     * @since 0.1.0
     */
    public String getCurrencyNameSingular() {
        return "coin";
    }

    /**
     * @author Vento
     * @since 0.1.0
     */
    public MariaDB getDatabase() {
        return db;
    }

    /**
     * @author Vento
     * @since 0.1.0
     */
    public BankDb getBankDatabase() {
        return bank;
    }
}
