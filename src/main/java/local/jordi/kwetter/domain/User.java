package local.jordi.kwetter.domain;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.getUserByName", query = "SELECT COUNT(u) FROM User AS u WHERE u.name = :name"),
        @NamedQuery(name = "User.findByPartialName", query = "SELECT u FROM User AS u WHERE u.name LIKE CONCAT('%', :tag, '%')"),
        @NamedQuery(name = "User.getTweets", query = "SELECT t FROM Tweet AS t WHERE t.author.id = :id AND t.responseToTweet IS NULL ORDER BY t.date DESC"),
        @NamedQuery(name = "User.getFeed", query = "SELECT t FROM Tweet AS t WHERE (t.author IN (SElECT u FROM User AS u JOIN u.followers f WHERE f.id = :id) OR t.author.id = :id) AND t.responseToTweet IS NULL ORDER BY t.date DESC")
})
public class User implements IDomainObject, Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String biography;
    private String website;

    @JsonbTransient
    private String password;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @JsonbTransient
    private List<Tweet> tweets;

    @ManyToMany(mappedBy = "followers")
    @JsonbTransient
    private Set<User> following;

    @ManyToMany
    @JsonbTransient
    private Set<User> followers;

    /**
     * Constructs the object
     * @param name The name of the user
     * @param password The password of the user
     * @param biography The biography of the user
     * @param website The website of the user
     */
    public User(String name, String password, String biography, String website)
    {
        this(name, biography, website);

        setPassword(password);
    }

    /**
     * Constructs the object
     * @param name The name of the user
     * @param biography The biography of the user
     * @param website The website of the user
     */
    public User(String name, String biography, String website)
    {
        setName(name);
        setBiography(biography);
        setWebsite(website);

        tweets = new ArrayList<>();
        following = new HashSet<>();
        followers = new HashSet<>();
    }

    public User()
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

    /**
     * Lets this user follow the given user
     * @param user The user to follow
     * @return Returns True if the user is now following the given user otherwise False
     */
    public boolean follow(User user)
    {
        if (this == user || user == null)
        {
            throw new IllegalArgumentException("Cannot follow yourself or null");
        }

        if (following.add(user))
        {
            if (user.followers.add(this))
            {
                return true;
            }

            following.remove(user);
        }
        return false;
    }

    /**
     * Stops this user from following the given user
     * @param user The user to stop following
     */
    public void unFollow(User user)
    {
        following.remove(user);
        user.followers.remove(this);
    }

    public List<Tweet> getTweets()
    {
        return tweets;
    }

    public void addTweet(Tweet tweet)
    {
        tweets.add(tweet);
    }

    public void removeTweet(Tweet tweet)
    {
        tweets.remove(tweet);
    }

    public Set<User> getFollowing()
    {
        return following;
    }

    public Set<User> getFollowers()
    {
        return followers;
    }

    @Override
    public long getId()
    {
        return id;
    }

    @Override
    public void setId(long id)
    {
        this.id = id;
    }
}
