package local.jordi.kwetter.service;

import local.jordi.kwetter.dao.ITweetDao;
import local.jordi.kwetter.dao.IUserDao;
import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService
{
    @Inject
    private IUserDao userDao;

    @Inject
    private ITweetDao tweetDao;

    public User CreateUser(User user)
    {
        if (!userDao.userWithNameExists(user.getName()))
        {
            return userDao.Create(user);
        }

        throw new IllegalArgumentException("A user with that username already exists");
    }

    public User UpdateUser(User user)
    {
        return userDao.Update(user);
    }

    public void SendTweet(User user, Tweet tweet)
    {
        Tweet createdTweet = tweetDao.Create(tweet);
        User managedUser = GetManagedUser(user);
        managedUser.addTweet(createdTweet);
    }

    public List<Tweet> Get10LatestTweets(User user)
    {
        User managedUser = GetManagedUser(user);
        return managedUser.getLatest10Tweets();
    }

    public boolean Follow(User user, User followUser)
    {
        User managedUser = GetManagedUser(user);
        User managedFollowUser = GetManagedUser(followUser);

        return managedUser.follow(managedFollowUser);
    }

    public void UnFollow(User user, User followedUser)
    {
        User managedUser = GetManagedUser(user);
        User managedFollowedUser = GetManagedUser(followedUser);

        managedUser.unFollow(managedFollowedUser);
    }

    private User GetManagedUser(User user)
    {
        User obtainedUser = userDao.Get(user.getId());

        if (obtainedUser != null)
        {
            return obtainedUser;
        }

        throw new IllegalArgumentException("Invalid user");
    }

    public User GetUser(long id)
    {
        return userDao.Get(id);
    }

    public void removeUser(User user)
    {
        userDao.Delete(user);
    }
}
