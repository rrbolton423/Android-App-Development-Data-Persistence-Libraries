package info.adavis.topsy.turvey.models;

public class Recipe
{
    private String id;

    private String name;

    private String description;

    private int imageResourceId;

    public Recipe()
    {
    }

    public Recipe(String name, String description, int imageResourceId)
    {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
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

    @Override
    public String toString()
    {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageResourceId=" + imageResourceId +
                '}';
    }
}
