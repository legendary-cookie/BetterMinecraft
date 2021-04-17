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

    public void registerRecipe(Recipe recipe) {
        Bukkit.addRecipe(recipe);
    }

    public void registerItem(String key, ItemStack item) {
        items.put(key, item);
    }


    public void unregisterRecipes() {
        recipes.forEach(shapedRecipe -> {
            Bukkit.removeRecipe(shapedRecipe.getKey());
        });
    }
}
