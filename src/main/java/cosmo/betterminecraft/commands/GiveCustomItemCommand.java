package cosmo.betterminecraft.commands;

import cosmo.betterminecraft.Core;
import cosmo.betterminecraft.gui.CustomItemGui;
import cosmo.betterminecraft.items.REU;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GiveCustomItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("ic")) {
            Player p = (Player) sender;
            Core.getInstance().getGuiInstances().getCig().openInventory(p);
            return true;
        }
        return false;
    }
}
