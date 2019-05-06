package local.jordi.kwetter.dao;

import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;

import java.util.List;

public interface IUserDao extends IDao<User>, IAuthenticateDao
{
    boolean userWithNameExists(String name);
    List<User> findByPartialName(String tag);

    List<Tweet> getTweets(long id);

    List<Tweet> GetFeed(long id);
}
