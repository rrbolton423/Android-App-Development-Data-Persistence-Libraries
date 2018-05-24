package info.adavis.topsy.turvey.models;

import java.util.List;

public class Recipe
{
    private Long _id;

    private String name;

    private String description;

    private int imageResourceId;

    private List<RecipeStep> steps;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    // Add new field to Recipe model
    private Integer numberOfStars;

    public Recipe ()
    {
    }

    public Recipe (String name, String description, int imageResourceId)
    {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public long getId()
    {
        return _id;
    }

    public void setId(long id)
    {
        this._id = id;
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

    public Integer getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) {
        this.numberOfStars = numberOfStars;
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
