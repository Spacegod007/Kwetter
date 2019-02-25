package local.jordi.kwetter.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Tweet extends AbstractDomainObject implements Serializable
{
//    @OneToMany(targetEntity = Tweet.class, mappedBy = "reactions")
    @ManyToOne
    private Tweet responseToTweet;

//    @ManyToOne(targetEntity = List.class)
    @OneToMany
    private List<Tweet> reactions;

    @ManyToOne
    private User author;

    private String text;
    private Date date;

    /**
     * Constructs the object
     * @param text The text of the tweet
     * @param author The author of the tweet
     */
    public Tweet(String text, User author)
    {
        setText(text);
        setAuthor(author);
        this.responseToTweet = null;

        date = new Date();
        reactions = new ArrayList<>();
    }

    /**
     * Constructs the object
     * @param text The text of the tweet
     * @param author The author of the tweet
     * @param responseToTweet The tweet/reaction this tweet is a reaction to
     */
    public Tweet(String text, User author, Tweet responseToTweet)
    {
        this(text, author);
        setResponseToTweet(responseToTweet);
    }

    public Tweet()
    {

    }

    public boolean isReaction()
    {
        return responseToTweet != null;
    }

    public Tweet getRepsonseTo()
    {
        return responseToTweet;
    }

    private void setResponseToTweet(Tweet responseToTweet)
    {
        this.responseToTweet = responseToTweet;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        if (text.length() > 140)
        {
            throw new IllegalArgumentException("The tweet text is too long, maximum length is 140 characters");
        }

        this.text = text;
    }

    public Date getDate()
    {
        return date;
    }

    public User getAuthor()
    {
        return author;
    }

    private void setAuthor(User author)
    {
        if (author == null)
        {
            throw new IllegalArgumentException("author has to be a user object");
        }

        this.author = author;
    }

    public List<Tweet> getReactions()
    {
        return reactions;
    }

    public void addReaction(Tweet tweet)
    {
        if (tweet == this)
        {
            throw new IllegalArgumentException("A tweet cannot be a reaction of itself");
        }
        reactions.add(tweet);
    }

    public void removeReaction(Tweet tweet)
    {
        reactions.remove(tweet);
    }
}
