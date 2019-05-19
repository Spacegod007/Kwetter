package local.jordi.kwetter.service;

import local.jordi.kwetter.dao.ITweetDao;
import local.jordi.kwetter.domain.Tweet;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TweetService implements ITweetService
{
    @Inject
    private IUserService userService;

    @Inject
    private ITweetDao tweetDao;

    public TweetService()
    {

    }

    @Override
    public Tweet Get(long id)
    {
        Tweet tweet = tweetDao.Get(id);

        if (tweet != null)
        {
            return tweet;
        }

        throw new IllegalArgumentException("Invalid tweet tweetId was supplied");
    }

    @Override
    public Tweet Get(Tweet tweet)
    {
        return Get(tweet.getDomainId());
    }

    @Override
    public Tweet Create(Tweet tweet)
    {
        return tweetDao.Create(tweet);
    }

    @Override
    public Tweet Update(Tweet tweet)
    {
        return tweetDao.Update(tweet);
    }

    @Override
    public void Remove(Tweet tweet)
    {
        tweetDao.Delete(tweet);
    }

    @Override
    public void SendReaction(Tweet tweet, Tweet reaction)
    {
        Tweet obtainedTweet = Get(tweet);
        Tweet createdReaction = tweetDao.Create(reaction);

        obtainedTweet.addReaction(createdReaction);
    }
}
