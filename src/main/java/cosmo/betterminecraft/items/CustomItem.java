package cosmo.betterminecraft.items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public interface CustomItem {
    /**
     * @return Build the item and return the ItemStack
     */
    ItemStack create();

    /**
     * @return Returns a ShapedRecipe to add it to the game. Use Bukkit.addRecipe(ShapedRecipe recipe)
     */
    ShapedRecipe createRecipe(ItemStack item);
}
