package info.adavis.topsy.turvey.db;

import android.util.Log;

import java.util.List;

import info.adavis.topsy.turvey.models.Recipe;
import info.adavis.topsy.turvey.models.RecipeFields;
import io.realm.Realm;

public class TopsyTurveyDataSource
{
    private static final String TAG = TopsyTurveyDataSource.class.getSimpleName();

    // Declare Realm instance
    private Realm realm;

    public void open()
    {

        // Get an instance of Realm
        realm = Realm.getDefaultInstance();

        // Log open message
        Log.d( TAG, "open: database opened" );
    }

    public void close()
    {

        // Close the Realm instance
        realm.close();

        // Log close message
        Log.d( TAG, "close: database closed" );
    }

    public void createRecipe (final Recipe recipe)
    {

        // Execute a Realm transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                // Call the realm's insert() method and add the
                // recipe to the database
                realm.insert(recipe);
            }
        });

        // Log id
        Log.d(TAG, "createRecipe: the id: " + recipe.getId());
    }

    public List<Recipe> getAllRecipes()
    {
        // Retrieve all of the recipe records from our Realm instance
//        return realm.where(Recipe.class).findAll();

        // Retrieve all of the recipe records that HAVE steps
        // from our Realm instance
//        return realm.where(Recipe.class).isNotEmpty(RecipeFields.STEPS.$).findAll();

        // Retrieve all of the recipe records that HAVE steps, or a Recipe
        // that name contains "ie"
        // from our Realm instance
        return realm.where(Recipe.class).isNotEmpty(RecipeFields.STEPS.$)
                .or()
                .contains(RecipeFields.NAME, "ie")
                .findAll();
    }

}