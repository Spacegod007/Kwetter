package local.jordi.kwetter.service;

import local.jordi.kwetter.dao.IUserDao;
import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService implements IUserService
{
    @Inject
    private IUserDao userDao;

    public UserService()
    {
    }

    @Override
    public User Get(long id)
    {
        User user = userDao.Get(id);

        if (user != null)
        {
            return user;
        }

        throw new IllegalArgumentException("Invalid user id was supplied");
    }

    @Override
    public User Get(User user)
    {
        return Get(user.getId());
    }

    @Override
    public User Create(User user)
    {
        if (!userDao.userWithNameExists(user.getName()))
        {
            return userDao.Create(user);
        }

        throw new IllegalArgumentException("A user with that username already exists");
    }

    @Override
    public User Update(User user)
    {
        return userDao.Update(user);
    }

    @Override
    public void Remove(User user)
    {
        userDao.Delete(user);
    }

    @Override
    public List<Tweet> Get10LatestTweets(User user)
    {
        List<Tweet> tweets = userDao.getTweets(user.getId());
        return tweets.size() < 10 ? tweets : tweets.subList(0, 10);
    }

    @Override
    public boolean Follow(User user, User followUser)
    {
        User managedUser = Get(user);
        User managedFollowUser = Get(followUser);

        return managedUser.follow(managedFollowUser);
    }

    @Override
    public void UnFollow(User user, User followedUser)
    {
        User managedUser = Get(user);
        User managedFollowedUser = Get(followedUser);

        managedUser.unFollow(managedFollowedUser);
    }

    @Override
    public List<User> Find(String tag)
    {
        return userDao.findByPartialName(tag);
    }

    @Override
    public List<Tweet> GetTweets(User user)
    {
        return userDao.getTweets(user.getId());
    }

    @Override
    public List<Tweet> GetFeed(User user)
    {
        return userDao.GetFeed(user.getId());
    }
}
