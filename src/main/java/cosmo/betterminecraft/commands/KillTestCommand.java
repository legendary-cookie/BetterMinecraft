package cosmo.betterminecraft.commands;

import cosmo.betterminecraft.Core;
import cosmo.betterminecraft.player.PlayerWrapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class KillTestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("killtest")) {
            Player player = (Player) sender;
            PlayerWrapper pw = Core.players.get(player);
            player.damage(pw.getHealth());
            return true;
        }
        return false;
    }
}
