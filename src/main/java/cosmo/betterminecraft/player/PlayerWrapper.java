package cosmo.betterminecraft.player;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlayerWrapper {
    // Create a variable to store the player
    private Player player;
    private double maxHealth = 100;
    private double health;

    // Constructor to pass on the player object
    public PlayerWrapper(Player player) {
        // Store the player object
        this.player = player;
        this.health = getMaxHealth();
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void playDeathSound() {
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_HURT, 1, 0);
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    // Getter to get the player object
    public Player getPlayer() {
        return player;
    }

    // Random method for example
    public void tp(Location location) {
        player.teleport(location);
    }
}