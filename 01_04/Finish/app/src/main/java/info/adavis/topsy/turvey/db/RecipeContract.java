package info.adavis.topsy.turvey.db;

import android.provider.BaseColumns;

final class RecipeContract
{
    private RecipeContract() {}

    static final String CREATE_RECIPE_ENTRY_TABLE =
            "CREATE TABLE " + RecipeEntry.TABLE_NAME +
                    " ( " +
                    RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RecipeEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                    RecipeEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                    RecipeEntry.COLUMN_IMAGE_RESOURCE_ID + " INTEGER NOT NULL, " +
                    "UNIQUE ( " + RecipeEntry._ID + ") ON CONFLICT REPLACE )";

    public static class RecipeEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "recipe";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGE_RESOURCE_ID = "image_resource_id";
    }

    public static class RecipeStepEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "recipe_step";
        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_STEP_NUMBER = "step_number";
        public static final String COLUMN_INSTRUCTION = "instruction";
    }

}
