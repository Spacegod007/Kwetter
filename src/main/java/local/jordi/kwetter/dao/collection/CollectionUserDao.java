package local.jordi.kwetter.dao.collection;

import local.jordi.kwetter.dao.IUserDao;
import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;

import java.util.List;

public class CollectionUserDao extends AbstractCollectionDao<User> implements IUserDao
{
    @Override
    public boolean userWithNameExists(String name)
    {
        for (User user : objectList)
        {
            if (user.getName().equals(name))
            {
               return true;
            }
        }

        return false;
    }

    @Override
    public List<User> findByPartialName(String tag)
    {
        return null;
    }

    @Override
    public List<Tweet> getTweets(long id)
    {
        return null;
    }

    @Override
    public List<Tweet> GetFeed(long id)
    {
        return null;
    }

    @Override
    public User getUserByName(String name)
    {
        return null;
    }
}
