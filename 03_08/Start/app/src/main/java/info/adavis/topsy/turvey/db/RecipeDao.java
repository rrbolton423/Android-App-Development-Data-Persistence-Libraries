package info.adavis.topsy.turvey.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import info.adavis.topsy.turvey.models.Recipe;

@Dao
public interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createRecipe(Recipe recipe);

    // This query method returns a list of Recipes
    @Query("SELECT * FROM recipe")
    List<Recipe> getAllRecipes();
}
