package info.adavis.topsy.turvey.models;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

// Have entity class extend "RealObject"
public class Recipe extends RealmObject
{
    // Define our primary key using Realm's @PrimaryKey annotation
    // By doing this, Real will automatically create an index on this column
    // and ensure it's contents are unique.
    // By assigning the field to "UUID.randomUUID().toString()", we ensure
    // that we always have a unique String
    @PrimaryKey
    private String id = UUID.randomUUID().toString();

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
