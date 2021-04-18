package cosmo.betterminecraft.scoreboard;

import cosmo.betterminecraft.Core;
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
        Score score = objective.getScore("** Health ** ");
        score.setScore(10);
        Score score1 = objective.getScore(String.valueOf(Core.players.get(p).getHealth()));
        score1.setScore(9);
        p.setScoreboard(board);
    }
}
