package info.adavis.topsy.turvey.models;

import java.util.List;

import nl.qbusict.cupboard.annotation.Ignore;

public class Recipe
{
    // Change _id field type to "Long" and reference
    // name to be "_id"
    private Long _id;

    private String name;

    private String description;

    private int imageResourceId;

    // Use @Ignore annotation to prevent cupboard from processing
    // the recipe steps field/ Support for list fields are not available
    // out of the box for cupboard
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

    public long get_id()
    {
        return _id;
    }

    public void set_id(long _id)
    {
        this._id = _id;
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

    public List<RecipeStep> getSteps ()
    {
        return steps;
    }

    public void setSteps (List<RecipeStep> steps)
    {
        this.steps = steps;
    }

    @Override
    public String toString ()
    {
        return "Recipe{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageResourceId=" + imageResourceId +
                '}';
    }
}
