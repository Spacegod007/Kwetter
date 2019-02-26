package local.jordi.kwetter.boundary.rest;

import local.jordi.kwetter.domain.User;
import local.jordi.kwetter.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

@Stateless
@Path("users")
public class UserResource
{
    @Inject
    private UserService userService;

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") long id)
    {
        return userService.GetUser(id);
    }

    @GET
    @Path("test")
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
        return userService.CreateUser(user);
    }

    @POST
    @Path("update")
    public User updateUser(User user)
    {
        return userService.UpdateUser(user);
    }

    @PUT
    @Path("follow/{id}")
    public boolean FollowUser(@PathParam("id") long id, User user)
    {
        User followUser = userService.GetUser(id);
        return userService.Follow(user, followUser);
    }

    @PUT
    @Path("unfollow/{id}")
    public void UnFollowUser(@PathParam("id") long id, User user)
    {
        User followedUser = userService.GetUser(id);
        userService.UnFollow(user, followedUser);
    }

    @DELETE
    @Path("{id}")
    public void removeUser(@PathParam("id") long id)
    {
        User user = userService.GetUser(id);
        userService.removeUser(user);
    }
}
