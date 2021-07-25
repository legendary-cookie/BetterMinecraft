package cosmo.betterminecraft.commands.impl;

import cosmo.betterminecraft.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MoneyGuiCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("moneygui")) {
            Player player = (Player) sender;
            Core.getInstance().getGuiInstances().getMoneyGui().openInventory(player);
            return true;
        }
        return false;
    }
}
