package local.jordi.kwetter.boundary.rest;

import local.jordi.kwetter.domain.User;
import local.jordi.kwetter.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

@Stateless
@Path("users")
public class UserResource
{
    @Inject
    private UserService userService;

//    @GET
//    @Path("{id}")
//    public User getUser(@PathParam("id") long id)
//    {
//        return userService.GetUser(id);
//    }

    @GET
    @Path("{id}")
    public User userTest(@PathParam("id") long id)
    {
        return new User("Jordi", "Bio", "WEB");
    }

    @POST
    public User registerUser(User user)
    {
        return userService.CreateUser(user);
    }

//    @PUT
    public boolean FollowUser(User user, User followUser)
    {
        return userService.Follow(user, followUser);
    }

//    @PUT
    public void UnFollowUser(User user, User followedUser)
    {
        userService.UnFollow(user, followedUser);
    }
}
