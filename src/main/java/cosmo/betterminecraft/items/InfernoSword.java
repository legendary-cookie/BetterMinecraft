package cosmo.betterminecraft.items;

import cosmo.betterminecraft.Core;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class InfernoSword implements CustomItem {

    @Override
    public ItemStack create() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§cInferno Sword"));
        meta.addEnchant(Enchantment.DAMAGE_ALL, 6, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public ShapedRecipe createRecipe(ItemStack item) {
        // create a NamespacedKey for your recipe
        NamespacedKey key = new NamespacedKey(Core.getInstance(), "emerald_sword");
        // Create our custom recipe variable
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        // Here we will set the places. E and S can represent anything, and the letters can be anything. Beware; this is case sensitive.
        recipe.shape(" B ", " B ", " S ");
        // Set what the letters represent.
        // E = Emerald, S = Stick
        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('S', Material.STICK);
        // Return the recipe
        return recipe;
    }
}
