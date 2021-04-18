package cosmo.betterminecraft.items;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class REU {
    private ArrayList<ShapedRecipe> recipes = new ArrayList<>();
    public Map<String, ItemStack> items = new HashMap<>();

    /**
     * Registers an recipe
     *
     * @param recipe
     */
    public void registerRecipe(Recipe recipe) {
        recipes.add((ShapedRecipe) recipe);
        Bukkit.addRecipe(recipe);
    }

    /**
     * Register an customitem into the map (for example to use with the itemgui)
     *
     * @param key
     * @param item
     */
    public void registerItem(String key, ItemStack item) {
        items.put(key, item);
    }


    public void unregisterRecipes() {
        recipes.forEach(shapedRecipe -> {
            Bukkit.removeRecipe(shapedRecipe.getKey());
        });
    }
}
