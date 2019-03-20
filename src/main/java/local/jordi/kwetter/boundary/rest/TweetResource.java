package local.jordi.kwetter.boundary.rest;

import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.service.ITweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Stateless
@Path("tweets")
public class TweetResource
{
    @Inject
    private ITweetService tweetService;

    @GET
    @Path("{id}")
    public Response getTweet(@PathParam("id") long id)
    {
        Tweet result = tweetService.Get(id);
        return ResourceHelper.GenerateResponse(result);
    }

    @GET
    @Path("{id}/replies")
    public Response getTweetReactions(@PathParam("id") long id)
    {
        Tweet tweet = tweetService.Get(id);
        return ResourceHelper.GenerateResponse(tweet.getReactions());
    }

    @GET
    @Path("{id}/replyTo")
    public Response getTweetResponseTo(@PathParam("id") long id)
    {
        Tweet tweet = tweetService.Get(id);
        return ResourceHelper.GenerateResponse(tweet.getRepsonseTo());
    }

    @GET
    @Path("{id}/author")
    public Response getTweetAuthor(@PathParam("id") long id)
    {
        Tweet tweet = tweetService.Get(id);
        return ResourceHelper.GenerateResponse(tweet.getAuthor());
    }

    @POST
    public Response sendTweet(Tweet tweet)
    {
        tweetService.SendTweet(tweet);
        return ResourceHelper.GenerateResponse();
    }

    @PUT
    @Path("{id}/reaction")
    public Response sendReaction(@PathParam("id") long id, Tweet reaction)
    {
        Tweet tweet = tweetService.Get(id);
        tweetService.SendReaction(tweet, reaction);
        return ResourceHelper.GenerateResponse();
    }

    @PUT
    @Path("{id}")
    public Response updateTweet(@PathParam("id") long id, Tweet tweet)
    {
        Tweet result = tweetService.Update(tweet);
        return ResourceHelper.GenerateResponse(result);
    }

    @DELETE
    @Path("{id}")
    public Response deleteTweet(@PathParam("id") long id)
    {
        Tweet tweet = tweetService.Get(id);
        tweetService.Remove(tweet);
        return ResourceHelper.GenerateResponse();
    }
}
