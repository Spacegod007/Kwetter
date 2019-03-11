package local.jordi.kwetter.boundary.rest;

import local.jordi.kwetter.domain.User;
import local.jordi.kwetter.service.IUserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("users")
public class UserResource
{
    @Inject
    private IUserService userService;

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") long id)
    {
        return userService.Get(id);
    }

    @GET
    @Path("test")
    @Produces({MediaType.APPLICATION_JSON})
    public User userTest()
    {
        User user = new User("Jordi", "Bio", "WEB");
        User followedUser = new User("followed by Jordi", "bio", "web");
        User followingUser = new User("follows Jordi", "bio", "web");
        user.follow(followedUser);
        followingUser.follow(user);
        return user;
    }

    @POST
    public User registerUser(User user)
    {
        return userService.Create(user);
    }

    @POST
    @Path("update")
    public User updateUser(User user)
    {
        return userService.Update(user);
    }

    @PUT
    @Path("follow/{id}")
    public boolean FollowUser(@PathParam("id") long id, User user)
    {
        User followUser = userService.Get(id);
        return userService.Follow(user, followUser);
    }

    @PUT
    @Path("unfollow/{id}")
    public void UnFollowUser(@PathParam("id") long id, User user)
    {
        User followedUser = userService.Get(id);
        userService.UnFollow(user, followedUser);
    }

    @DELETE
    @Path("{id}")
    public void removeUser(@PathParam("id") long id)
    {
        User user = userService.Get(id);
        userService.Remove(user);
    }
}
