package local.jordi.kwetter.service;

import local.jordi.kwetter.dao.ITweetDao;
import local.jordi.kwetter.domain.Tweet;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TweetService
{
    @Inject
    private ITweetDao tweetDao;

    public Tweet UpdateTweet(Tweet tweet)
    {
        return tweetDao.Update(tweet);
    }

    public void SendReaction(Tweet tweet, Tweet reaction)
    {
        Tweet createdReaction = tweetDao.Create(reaction);
        Tweet obtainedTweet = GetTweet(tweet);

        if (obtainedTweet != null)
        {
            obtainedTweet.addReaction(createdReaction);
        }
        else
        {
            throw new IllegalArgumentException("Something went wrong while creating a reaction");
        }
    }

    public Tweet GetTweet(Tweet tweet)
    {
        Tweet obtainedTweet = tweetDao.Get(tweet.getId());
        if (obtainedTweet != null)
        {
            return obtainedTweet;
        }

        throw new IllegalArgumentException("Invalid tweetId was supplied");
    }
}
