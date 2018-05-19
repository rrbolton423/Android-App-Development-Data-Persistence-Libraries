package info.adavis.topsy.turvey;

import info.adavis.topsy.turvey.models.RecipeFields;
import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

// Migration class must implement the RealmMigration interface
class Migration implements RealmMigration
{
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion)
    {
        // Get the Realm Schema
        RealmSchema schema = realm.getSchema();

        // If the oldVersion is 1...
        if (oldVersion == 1)
        {
            // Grab the Recipe schema
            RealmObjectSchema recipeSchema = schema.get("Recipe");

            // Add a field to the Recipe schema
            recipeSchema.addField(RecipeFields.NUMBER_OF_STARS, Integer.class)
                    .transform(new RealmObjectSchema.Function()
                    {
                        @Override
                        public void apply(DynamicRealmObject obj)
                        {
                            // Set the old recipes to have 5 stars
                            obj.set(RecipeFields.NUMBER_OF_STARS, 5);
                        }
                    });
        }
    }
}
