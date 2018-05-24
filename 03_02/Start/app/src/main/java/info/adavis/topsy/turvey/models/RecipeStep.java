package info.adavis.topsy.turvey.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

// Add @Entity annotation
// specifying the Room db table name
@Entity(tableName = "recipe_steps",
        primaryKeys = { "step_number", "recipe_id"}, // Identify primary keys for this table
        indices = {@Index("recipe_id")}, // Speed up our queries when using particular fields
        foreignKeys = @ForeignKey(entity = Recipe.class, // Add parent entity
        parentColumns = "id", // Add the primary key column of parent entity
        childColumns = "recipe_id")) // Add the primary key column of child entity
public class RecipeStep
{
    // Add @ColumnInfo annotation to have control over
    // the columns used in the database
    @ColumnInfo(name = "recipe_id")
    private long recipeId;

    // Add @ColumnInfo annotation to have control over
    // the columns used in the database
    @ColumnInfo(name = "step_number")
    private int stepNumber;

    private String instruction;

    public RecipeStep (int stepNumber, String instruction)
    {
        this.stepNumber = stepNumber;
        this.instruction = instruction;
    }

    public long getRecipeId()
    {
        return recipeId;
    }

    public void setRecipeId(long recipeId)
    {
        this.recipeId = recipeId;
    }

    public int getStepNumber()
    {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber)
    {
        this.stepNumber = stepNumber;
    }

    public String getInstruction()
    {
        return instruction;
    }

    public void setInstruction(String instruction)
    {
        this.instruction = instruction;
    }
}
