package cosmo.betterminecraft.commands;

import cosmo.betterminecraft.items.REU;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GiveCustomItem implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("ic")) {
            REU reu = new REU();
            if (args.length < 1) {
                sender.sendMessage("You have to specify an item!");
                return true;
            }
            String queriedItem = args[0];
            if (reu.items.containsKey(queriedItem)) {
                ItemStack item = reu.items.get(queriedItem);
                Player player = (Player) sender;
                player.getInventory().addItem(item);
            }
            return true;
        }
        return false;
    }
}
