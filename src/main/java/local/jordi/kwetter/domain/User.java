package local.jordi.kwetter.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User extends UserInformation implements Serializable
{
    @OneToMany
    private List<Tweet> tweets;

    @ManyToMany(targetEntity = User.class, mappedBy = "followers")
    private Set<UserInformation> following;

    @ManyToMany(targetEntity = User.class)
    private Set<UserInformation> followers;

    /**
     * Constructs the object
     * @param name The name of the user
     * @param password The password of the user
     * @param biography The biography of the user
     * @param website The website of the user
     */
    public User(String name, String password, String biography, String website)
    {
        super(name, password, biography, website);

        tweets = new ArrayList<>();
        following = new HashSet<>();
        followers = new HashSet<>();
    }

    /**
     * Constructs the object
     * @param name The name of the user
     * @param biography The biography of the user
     * @param website The website of the user
     */
    public User(String name, String biography, String website)
    {
        super(name, biography, website);

        tweets = new ArrayList<>();
        following = new HashSet<>();
        followers = new HashSet<>();
    }

    public User()
    {
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

    public List<Tweet> getLatest10Tweets()
    {
        List<Tweet> latestTweets = new ArrayList<>();

        int amountOfItemsToGet = 10;

        if (tweets.size() < amountOfItemsToGet)
        {
            amountOfItemsToGet = amountOfItemsToGet + (tweets.size() - amountOfItemsToGet);
        }

        for (int i = tweets.size() - 1; i > tweets.size() - (amountOfItemsToGet + 1); i--)
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

    public Set<UserInformation> getFollowing()
    {
        return new HashSet<>(following);
    }

    public Set<UserInformation> getFollowers()
    {
        return new HashSet<>(followers);
    }
}
