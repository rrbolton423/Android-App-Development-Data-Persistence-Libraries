package info.adavis.topsy.turvey.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import java.util.List;

import info.adavis.topsy.turvey.models.RecipeStep;

@Dao
public interface RecipeStepDao {

    // This method inserts our list recipe steps
    // Provide the @Insert annotation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RecipeStep> steps);
}
