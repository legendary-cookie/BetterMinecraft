package cosmo.betterminecraft.player;

import cosmo.betterminecraft.Core;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlayerWrapper {
    // Create a variable to store the player
    private Player player;

    // Constructor to pass on the player object
    public PlayerWrapper(Player player) {
        // Store the player object
        this.player = player;
        this.health = getMaxHealth();
        this.maxHealth = calculateMaxHealth();
    }

    // Health
    private double maxHealth;
    private double health;
    private double baseHealth = Core.getInstance().config.getDouble("health.base");

    private int armor;

    private double calculateMaxHealth() {
        return baseHealth;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void playDeathSound() {
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_HURT, 1, 0);
    }

    // Getter to get the player object
    public Player getPlayer() {
        return player;
    }

    // easier tp
    public void tp(Location location) {
        player.teleport(location);
    }


}