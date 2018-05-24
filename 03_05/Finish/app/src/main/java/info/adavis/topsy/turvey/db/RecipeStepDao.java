package info.adavis.topsy.turvey.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import java.util.List;

import info.adavis.topsy.turvey.models.RecipeStep;

@Dao
public interface RecipeStepDao {

    @Insert
    void insertAll(List<RecipeStep> steps);

}
