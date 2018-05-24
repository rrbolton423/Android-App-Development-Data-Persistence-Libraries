package info.adavis.topsy.turvey.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import info.adavis.topsy.turvey.models.RecipeStep;

@Dao
public interface RecipeStepDao {

    @Insert
    void insertAll(List<RecipeStep> steps);

    // This query method finds a particular RecipeStep in the
    // Room db by it's recipeId
    @Query("SELECT * FROM recipe_steps WHERE recipe_id = :recipeId")
    List<RecipeStep> getAllRecipeStepsByRecipeId(long recipeId);
}
