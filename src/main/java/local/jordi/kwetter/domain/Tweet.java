package local.jordi.kwetter.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tweet
{
    private long id;
    private long responseToId;

    private String text;
    private Date date;
    private User author;
    private List<Tweet> reactions;

    /**
     * Constructs the object
     * @param text The text of the tweet
     * @param author The author of the tweet
     */
    public Tweet(String text, User author)
    {
        setText(text);
        setAuthor(author);
        this.responseToId = Long.MIN_VALUE;

        date = new Date();
        reactions = new ArrayList<>();
    }

    /**
     * Constructs the object
     * @param text The text of the tweet
     * @param author The author of the tweet
     * @param responseToId The tweet/reaction this tweet is a reaction to
     */
    public Tweet(String text, User author, long responseToId)
    {
        this(text, author);
        setResponseToId(responseToId);
    }

    public long getId()
    {
        return id;
    }

    public boolean isReaction()
    {
        return responseToId != Long.MIN_VALUE;
    }

    public long getResponseToId()
    {
        return responseToId;
    }

    private void setResponseToId(long responseToId)
    {
        this.responseToId = responseToId;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
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
