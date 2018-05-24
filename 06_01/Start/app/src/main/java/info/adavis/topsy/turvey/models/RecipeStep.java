package info.adavis.topsy.turvey.models;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

// Make RecipeStep class extend RealmObject
public class RecipeStep extends RealmObject
{
    // Define our primary key using Realm's @PrimaryKey annotation
    // By doing this, Real will automatically create an index on this column
    // and ensure it's contents are unique.
    // By assigning the field to "UUID.randomUUID().toString()", we ensure
    // that we always have a unique String
    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    private int stepNumber;

    private String instruction;

    public RecipeStep()
    {
    }

    public RecipeStep(int stepNumber, String instruction)
    {
        this.stepNumber = stepNumber;
        this.instruction = instruction;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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

    @Override
    public String toString()
    {
        return "RecipeStep{" +
                "id='" + id + '\'' +
                ", stepNumber=" + stepNumber +
                ", instruction='" + instruction + '\'' +
                '}';
    }
}
