package info.adavis.topsy.turvey.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

// Add @Entity annotation for a Room db table
@Entity
public class Recipe
{
    // Add @PrimaryKey annotation,
    // and allow auto generation of unique IDS
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;

    private String description;

    // Add @ColumnInfo annotation to have control over
    // the columns used in the database
    @ColumnInfo(name = "image_resource_id")
    private int imageResourceId;

    // No room support for list fields, add @Ignore annotation
    @Ignore
    private List<RecipeStep> steps;

    public Recipe ()
    {
    }

    public Recipe (String name, String description, int imageResourceId)
    {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public long getId ()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getImageResourceId()
    {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId)
    {
        this.imageResourceId = imageResourceId;
    }

    public List<RecipeStep> getSteps()
    {
        return steps;
    }

    public void setSteps(List<RecipeStep> steps)
    {
        this.steps = steps;
    }

    @Override
    public String toString ()
    {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageResourceId=" + imageResourceId +
                ", steps=" + steps +
                '}';
    }
}
