package cosmo.betterminecraft.economy;

import cosmo.betterminecraft.Core;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.List;

// https://www.spigotmc.org/threads/vault-economy-provider.366757/
public class BetterEconomy implements Economy {
    @Override
    public boolean isEnabled() {
        return Core.getInstance().isEnabled();
    }

    @Override
    public String getName() {
        return "BetterEconomy";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return -1;
    }

    @Override
    public String format(double amount) {
        return Core.getInstance().getAccountManager().format(amount);
    }

    @Override
    public String currencyNamePlural() {
        return Core.getInstance().getCurrencyNamePlural();
    }

    @Override
    public String currencyNameSingular() {
        return Core.getInstance().getCurrencyNameSingular();
    }

    @Override
    public boolean hasAccount(String playerName) {
        return Core.getInstance().getAccountManager().createAccount(playerName);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return Core.getInstance().getAccountManager().createAccount(player);
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return Core.getInstance().getAccountManager().createAccount(playerName);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return Core.getInstance().getAccountManager().createAccount(player);
    }

    @Override
    public double getBalance(String playerName) {
        return Core.getInstance().getAccountManager().getBalance(playerName);
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        return Core.getInstance().getAccountManager().getBalance(player);
    }

    @Override
    public double getBalance(String playerName, String world) {
        return Core.getInstance().getAccountManager().getBalance(playerName);
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        return Core.getInstance().getAccountManager().getBalance(player);
    }

    @Override
    public boolean has(String playerName, double amount) {
        return Core.getInstance().getAccountManager().hasAmount(Bukkit.getPlayerUniqueId(playerName), amount);
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        return Core.getInstance().getAccountManager().hasAmount(player.getUniqueId(), amount);
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return Core.getInstance().getAccountManager().hasAmount(Bukkit.getPlayerUniqueId(playerName), amount);
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return Core.getInstance().getAccountManager().hasAmount(player.getUniqueId(), amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);

    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);

    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);

    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);

    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);

    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, (String) null);
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return true;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return true;
    }
}
