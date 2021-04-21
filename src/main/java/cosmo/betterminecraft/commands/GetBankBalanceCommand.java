package cosmo.betterminecraft.commands;

import cosmo.betterminecraft.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetBankBalanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("balance")) {
            Player player = (Player) sender;
            int bal = Core.getInstance().getBankDatabase().getPlayerBankBalance(player.getUniqueId());
            if (bal == -1) {
                sender.sendMessage("You don't have any " + Core.getInstance().getCurrencyNamePlural() + " in your bank account!");
            } else if (bal == 1) {
                sender.sendMessage("You have one " + Core.getInstance().getCurrencyNameSingular());
            } else if (bal > 1) {
                sender.sendMessage("You have " + bal + " " + Core.getInstance().getCurrencyNamePlural());
            }
            return true;
        }
        return false;
    }
}
