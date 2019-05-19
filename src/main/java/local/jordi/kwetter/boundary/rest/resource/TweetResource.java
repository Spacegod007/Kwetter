package local.jordi.kwetter.boundary.rest.resource;

import local.jordi.kwetter.boundary.rest.security.RequiresJWT;
import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.service.ITweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    @Path("{id}/replyto")
    public Response getTweetResponseTo(@PathParam("id") long id)
    {
        Tweet tweet = tweetService.Get(id);
        return ResourceHelper.GenerateResponse(tweet.getRepsonseTo());
    }

    @POST
    @RequiresJWT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendTweet(JsonObject jsonObject)
    {
        Tweet tweet = ResourceHelper.JsonToObject(jsonObject, Tweet.class);
        Tweet createdTweets = tweetService.Create(tweet);

        ResourceHelper.addTweetLinks(createdTweets);

        return ResourceHelper.GenerateResponse(createdTweets);
    }

    @PUT
    @RequiresJWT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}/reaction")
    public Response sendReaction(@PathParam("id") long id, JsonObject jsonObject)
    {
        Tweet reaction = ResourceHelper.JsonToObject(jsonObject, Tweet.class);

        Tweet tweet = tweetService.Get(id);
        tweetService.SendReaction(tweet, reaction);
        return ResourceHelper.GenerateResponse();
    }

    @PUT
    @RequiresJWT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateTweet(@PathParam("id") long id, JsonObject jsonObject)
    {
        Tweet tweet = ResourceHelper.JsonToObject(jsonObject, Tweet.class);

        Tweet result = tweetService.Update(tweet);
        return ResourceHelper.GenerateResponse(result);
    }

    @DELETE
    @RequiresJWT
    @Path("{id}")
    public Response deleteTweet(@PathParam("id") long id)
    {
        Tweet tweet = tweetService.Get(id);
        tweetService.Remove(tweet);
        return ResourceHelper.GenerateResponse();
    }


}
