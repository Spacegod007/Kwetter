package local.jordi.kwetter.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements IDomainObject
{
    //@Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String password;
    private String biography;
    private String website;

    private List<Tweet> tweets;
    private Set<User> following;
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

    /**
     * Lets this user follow the given user
     * @param user The user to follow
     * @return Returns True if the user is now following the given user otherwise False
     */
    public boolean follow(User user)
    {
        if (this == user || user == null)
        {
            return false;
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
    public long getId()
    {
        return id;
    }

    @Override
    public void setId(long id)
    {
        this.id = id;
    }

    public String getPassword()
    {
        return password;
    }

    void setPassword(String password)
    {
        if (password == null || password.isEmpty())
        {
            throw new IllegalArgumentException("password cannot be null or empty");
        }
        this.password = password;
    }

    public List<Tweet> getTweets()
    {
        return tweets;
    }

    public List<Tweet> getLatest10Tweets()
    {
        List<Tweet> latestTweets = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            latestTweets.add(tweets.get(i));
        }

        return latestTweets;
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
}
