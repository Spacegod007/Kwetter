package local.jordi.kwetter.boundary.rest.resource;

import local.jordi.kwetter.boundary.rest.security.RequiresJWT;
import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;
import local.jordi.kwetter.service.IUserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Stateless
@Path("users")
public class UserResource
{
    @Inject
    private IUserService userService;

    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") long id)
    {
        try
        {
            User result = userService.Get(id);

            return ResourceHelper.GenerateResponse(result);
        }
        catch (Exception e)
        {
            return ResourceHelper.GenerateResponse(null);
        }
    }

    @GET
    @Path("search/{tag}")
    public Response findUserByName(@PathParam("tag") String tag)
    {
        List<User> find = userService.Find(tag);
        return ResourceHelper.GenerateResponse(find);
    }

    @GET
    @Path("{id}/followers")
    public Response getUserFollowers(@PathParam("id") long id)
    {
        User user = userService.Get(id);
        return ResourceHelper.GenerateResponse(user.getFollowers());
    }

    @GET
    @Path("{id}/following")
    public Response getUserFollowing(@PathParam("id") long id)
    {
        User user = userService.Get(id);
        return ResourceHelper.GenerateResponse(user.getFollowing());
    }

    @GET
    @Path("{id}/latesttweets")
    public Response getLatestTweets(@PathParam("id") long id)
    {
        User user = userService.Get(id);
        List<Tweet> tweets = userService.Get10LatestTweets(user);
        return ResourceHelper.GenerateResponse(tweets);
    }

    @GET
    @Path("{id}/tweets")
    public Response getUserTweets(@PathParam("id") long id)
    {
        User user = userService.Get(id);
        List<Tweet> tweets = userService.GetTweets(user);
        return ResourceHelper.GenerateResponse(tweets);
    }

    @GET
    @RequiresJWT
    @Path("{id}/feed")
    public Response getUserFeed(@PathParam("id") long id)
    {
        User user = userService.Get(id);
        List<Tweet> tweets = userService.GetFeed(user);
        return ResourceHelper.GenerateResponse(tweets);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(JsonObject jsonObject)
    {
        User user = ResourceHelper.JsonToObject(jsonObject, User.class);
        User result = userService.Create(user);
        ResourceHelper.addUserLinks(result);

        return ResourceHelper.GenerateResponse(result);
    }

    @PUT
    @RequiresJWT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") long id, User user)
    {
        User result = userService.Update(user);
        return ResourceHelper.GenerateResponse(result);
    }

    @PUT
    @RequiresJWT
    @Path("{id}/follow/{followId}")
    public Response followUser(@PathParam("id") long id, @PathParam("followId") long followId)
    {
        User user = userService.Get(id);
        User followUser = userService.Get(followId);

        boolean operationSucceeded = userService.Follow(user, followUser);

        return ResourceHelper.GenerateResponse(operationSucceeded);
    }

    @PUT
    @RequiresJWT
    @Path("{id}/unfollow/{followId}")
    public Response unFollowUser(@PathParam("id") long id, @PathParam("followId") long followId)
    {
        User user = userService.Get(id);
        User followedUser = userService.Get(followId);

        userService.UnFollow(user, followedUser);

        return ResourceHelper.GenerateResponse();
    }

    @DELETE
    @RequiresJWT
    @Path("{id}")
    public Response removeUser(@PathParam("id") long id)
    {
        User user = userService.Get(id);
        userService.Remove(user);
        return ResourceHelper.GenerateResponse();
    }
}
