package local.jordi.kwetter.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class UserInformation implements IDomainObject, Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    protected String name;

    protected String password;
    protected String biography;
    protected String website;

    public UserInformation(String name, String biography, String website)
    {
        setName(name);
        setBiography(biography);
        setWebsite(website);
    }

    public UserInformation(String name, String password, String biography, String website)
    {
        this(name, biography, website);
        setPassword(password);
    }

    public UserInformation()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if (name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        if (password == null || password.isEmpty())
        {
            throw new IllegalArgumentException("password cannot be null or empty");
        }
        this.password = password;
    }

    public String getBiography()
    {
        return biography;
    }

    public void setBiography(String biography)
    {
        this.biography = biography;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    @Override
    public void setId(long id)
    {
        this.id = id;
    }

    @Override
    public long getId()
    {
        return id;
    }
}
