package local.jordi.kwetter.service;

import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;

import java.util.List;

public interface IUserService extends IService<User>
{
    List<Tweet> Get10LatestTweets(User user);

    boolean Follow(User user, User followUser);

    void UnFollow(User user, User followedUser);

    List<User> Find(String tag);

    List<Tweet> GetTweets(User user);
}
