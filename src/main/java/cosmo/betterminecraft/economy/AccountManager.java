package cosmo.betterminecraft.economy;

import cosmo.betterminecraft.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.UUID;

public class AccountManager {
    private File accDir = Core.getInstance().getAccountDirectory();

    public boolean createAccount(OfflinePlayer player) {
        UUID uuid = player.getUniqueId();
        File f = new File(Core.getInstance().getAccountDirectory(), uuid + ".yml");
        if (!f.exists()) {
            FileConfiguration c = YamlConfiguration.loadConfiguration(f);
            c.set("player.name", player.getName());
            c.set("player.uuid", player.getUniqueId());
            c.set("player.bal", 0);
            try {
                c.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean createAccount(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();
        File f = new File(Core.getInstance().getAccountDirectory(), uuid + ".yml");
        if (!f.exists()) {
            FileConfiguration c = YamlConfiguration.loadConfiguration(f);
            c.set("player.name", player.getName());
            c.set("player.uuid", player.getUniqueId());
            c.set("player.bal", 0.0D);
            try {
                c.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public double getBalance(OfflinePlayer player) {
        UUID uuid = player.getUniqueId();
        File f = new File(Core.getInstance().getAccountDirectory(), uuid + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        return c.getDouble("player.bal");
    }

    public double getBalance(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();
        File f = new File(Core.getInstance().getAccountDirectory(), uuid + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        return c.getDouble("player.bal");
    }

    public double getBalanceByUUID(UUID uuid) {
        File f = new File(Core.getInstance().getAccountDirectory(), uuid + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        return c.getDouble("player.bal");
    }

    public boolean hasAmount(UUID uuid, double amount) {
        double balance = getBalanceByUUID(uuid);
        if (balance < amount) {
            return false;
        } else {
            return true;
        }
    }


    public String format(double amount) {
        amount = getMoneyRounded(amount);

        String suffix = " ";

        if (amount == 1.0D) {
            suffix = suffix + Core.getInstance().getCurrencyNameSingular();
        } else {
            suffix = suffix + Core.getInstance().getCurrencyNamePlural();
        }
        if (suffix.equalsIgnoreCase(" ")) {
            suffix = "";
        }

        return String.valueOf(amount) + suffix;

    }

    public double getMoneyRounded(double amount) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");

        String formattedAmount = twoDForm.format(amount);

        formattedAmount = formattedAmount.replace(",", ".");

        return Double.valueOf(formattedAmount).doubleValue();
    }
}
