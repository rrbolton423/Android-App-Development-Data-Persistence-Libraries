package info.adavis.topsy.turvey.db;

import android.util.Log;

import java.util.List;

import info.adavis.topsy.turvey.models.Recipe;
import info.adavis.topsy.turvey.models.RecipeFields;
import io.realm.Realm;
import io.realm.RealmResults;

public class TopsyTurveyDataSource
{
    private static final String TAG = TopsyTurveyDataSource.class.getSimpleName();

    private Realm realm;

    public void open()
    {
        realm = Realm.getDefaultInstance();

        Log.d( TAG, "open: database opened" );
    }

    public void close()
    {
        realm.close();
        Log.d( TAG, "close: database closed" );
    }

    public void createRecipe (final Recipe recipe)
    {
        realm.executeTransaction(new Realm.Transaction()
        {
            @Override
            public void execute(Realm realm)
            {

                // Use Realm's insertOrUpdate method so the adapter
                // will be aware of changes in the database
                realm.insertOrUpdate(recipe);
            }
        });

        Log.d(TAG, "createRecipe: the id: " + recipe.getId());
    }

    public List<Recipe> getAllRecipes()
    {
        return realm.where(Recipe.class).findAll();
    }

    public void modifyDescription()
    {
        // Get a managed  Recipe
        final Recipe recipe = realm.where(Recipe.class).findFirst();

        // Execute a Realm transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                // Update the description of the managed Recipe
                recipe.setDescription("Wonderful yellow cake!");
            }
        });
    }

    public void deleteRecipe(Recipe recipe) {

        // Get the ID of the managed Realm Recipe object passed into this method
        final Recipe recipeManaged = realm.where(Recipe.class).equalTo(RecipeFields.ID, recipe.getId()).findFirst();

        // Execute a Realm transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                // Delete the Recipe from Realm
                recipeManaged.deleteFromRealm();
            }
        });
    }

    public void deleteAllRecipes() {

        // Get a list of Recipes objects from Realm
        final RealmResults<Recipe> recipes = realm.where(Recipe.class).findAll();

        // Execute a Realm transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                // Delete all Recipes from Realm
                recipes.deleteAllFromRealm();
            }
        });
    }
}