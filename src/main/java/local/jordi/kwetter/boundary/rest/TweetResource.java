package local.jordi.kwetter.boundary.rest;

import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;
import local.jordi.kwetter.service.TweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

@Stateless
@Path("tweets")
public class TweetResource
{
    @Inject
    private TweetService tweetService;

    @GET
    public Tweet tweetTest()
    {
        return new Tweet("T", new User("a", "b", "c", "d"));
    }

    @GET
    @Path("{id}")
    public Tweet getTweet(@PathParam("id") long id)
    {
        return tweetService.GetTweet(id);
    }

    @PUT
    @Path("reaction/{id}")
    public void sendReaction(@PathParam("id") long id, Tweet reaction)
    {
        Tweet tweet = tweetService.GetTweet(id);
        tweetService.SendReaction(tweet, reaction);
    }

    @DELETE
    @Path("{id}")
    public void deleteTweet(@PathParam("id") long id)
    {
        Tweet tweet = tweetService.GetTweet(id);
        tweetService.DeleteTweet(tweet);
    }
}
