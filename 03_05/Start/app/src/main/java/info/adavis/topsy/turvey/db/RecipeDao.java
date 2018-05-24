package info.adavis.topsy.turvey.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import info.adavis.topsy.turvey.models.Recipe;

@Dao
public interface RecipeDao {

    // This method inserts our recipes
    // Provide the @Insert annotation, and a conflict strategy (replace)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createRecipe(Recipe recipe);
}
