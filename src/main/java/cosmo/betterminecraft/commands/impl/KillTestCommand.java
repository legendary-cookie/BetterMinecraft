package cosmo.betterminecraft.commands.impl;

import cosmo.betterminecraft.Core;
import cosmo.betterminecraft.player.PlayerWrapper;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class KillTestCommand extends BukkitCommand {

    public KillTestCommand(@NotNull String name) {
        super(name);
        this.description = "Test the killing thing";
        this.usageMessage = "/killtest";
    }

    /**
     * Executes the command, returning its success
     *
     * @param sender       Source object which is executing this command
     * @param commandLabel The alias of the command used
     * @param args         All arguments passed to the command, split via ' '
     * @return true if the command was successful, otherwise false
     */
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {

        Player player = (Player) sender;
        PlayerWrapper pw = Core.players.get(player);
        player.damage(pw.getHealth());
        return true;
    }
}
