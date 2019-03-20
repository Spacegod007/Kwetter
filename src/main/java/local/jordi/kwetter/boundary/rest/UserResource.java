package local.jordi.kwetter.boundary.rest;

import local.jordi.kwetter.domain.User;
import local.jordi.kwetter.service.IUserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("users")
public class UserResource
{
    @Inject
    private IUserService userService;

    @GET
    public Response getAllUsers()
    {
        List<User> users = new ArrayList<>();
        for(int i = 1; i < 5; i++)
        {
            users.add(userService.Get(i));
        }
        return ResourceHelper.GenerateResponse(users);
    }

    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") long id)
    {
        User result = userService.Get(id);
        return ResourceHelper.GenerateResponse(result);
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
        return ResourceHelper.GenerateResponse(user.getLatest10Tweets());
    }

    @GET
    @Path("{id}/tweets")
    public Response getUserTweets(@PathParam("id") long id)
    {
        User user = userService.Get(id);
        return ResourceHelper.GenerateResponse(user.getTweets());
    }

    @POST
    public Response registerUser(User user)
    {
        User result = userService.Create(user);
        return ResourceHelper.GenerateResponse(result);
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") long id, User user)
    {
        User result = userService.Update(user);
        return ResourceHelper.GenerateResponse(result);
    }

    @PUT
    @Path("{id}/follow/{followId}")
    public Response followUser(@PathParam("id") long id, @PathParam("followId") long followId)
    {
        User user = userService.Get(id);
        User followUser = userService.Get(followId);

        boolean operationSucceeded = userService.Follow(user, followUser);

        return ResourceHelper.GenerateResponse(operationSucceeded);
    }

    @PUT
    @Path("{id}/unfollow/{followId}")
    public Response unFollowUser(@PathParam("id") long id, @PathParam("followId") long followId)
    {
        User user = userService.Get(id);
        User followedUser = userService.Get(followId);

        userService.UnFollow(user, followedUser);

        return ResourceHelper.GenerateResponse();
    }

    @DELETE
    @Path("{id}")
    public Response removeUser(@PathParam("id") long id)
    {
        User user = userService.Get(id);
        userService.Remove(user);
        return ResourceHelper.GenerateResponse();
    }
}
