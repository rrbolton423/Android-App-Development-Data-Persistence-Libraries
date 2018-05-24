package info.adavis.topsy.turvey.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import info.adavis.topsy.turvey.models.Recipe;
import io.reactivex.Flowable;

@Dao
public interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createRecipe(Recipe recipe);

    // Return a RxJava Flowable list of Recipes
    // to listen for updates
    @Query("SELECT * FROM recipe")
    Flowable<List<Recipe>> getAllRecipes();
}
