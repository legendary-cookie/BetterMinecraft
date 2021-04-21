package cosmo.betterminecraft.scoreboard;

import cosmo.betterminecraft.Core;
import cosmo.betterminecraft.database.BankDb;
import cosmo.betterminecraft.player.PlayerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardStuff {
    public static void updateScoreboard(Player p) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        final Scoreboard board = manager.getNewScoreboard();
        final Objective objective = board.registerNewObjective("cosmo", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GOLD + "BetterMinecraft");
        PlayerWrapper pw = Core.players.get(p);
        BankDb bank = Core.getInstance().getBankDatabase();
        Score score = objective.getScore(ChatColor.BOLD + "Health");
        Score score1 = objective.getScore(String.valueOf(pw.getHealth()));
        Score score2 = objective.getScore(ChatColor.BOLD + "Money");
        Score score3 = objective.getScore("Bank: " + bank.getPlayerBankBalance(p.getUniqueId()));
        Score score4 = objective.getScore("Purse: " + bank.getPlayerPurse(p.getUniqueId()));
        score.setScore(10);
        score1.setScore(9);
        score2.setScore(8);
        score3.setScore(7);
        score4.setScore(6);
        p.setScoreboard(board);
    }
}
