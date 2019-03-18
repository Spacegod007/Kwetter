package local.jordi.kwetter.boundary.rest;

import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;
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

    @DELETE
    @Path("{id}")
    public Response deleteTweet(@PathParam("id") long id)
    {
        Tweet tweet = tweetService.Get(id);
        tweetService.Remove(tweet);
        return ResourceHelper.GenerateResponse();
    }
}
