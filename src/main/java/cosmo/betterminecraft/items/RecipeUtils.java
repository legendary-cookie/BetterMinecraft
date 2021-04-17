package cosmo.betterminecraft.items;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;

public class RecipeUtils {
    private ArrayList<ShapedRecipe> recipes = new ArrayList<>();

    public void registerRecipe(Recipe recipe) {
        Bukkit.addRecipe(recipe);
    }


    public void unregisterRecipes() {
        recipes.forEach(shapedRecipe -> {
            Bukkit.removeRecipe(shapedRecipe.getKey());
        });
    }
}
