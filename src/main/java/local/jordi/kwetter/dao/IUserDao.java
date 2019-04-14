package local.jordi.kwetter.dao;

import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;

import java.util.List;

public interface IUserDao extends IDao<User>
{
    boolean userWithNameExists(String name);
    List<User> findByPartialName(String tag);

    List<Tweet> getLatest10Tweets(long id);

    List<Tweet> getTweets(long id);
}
