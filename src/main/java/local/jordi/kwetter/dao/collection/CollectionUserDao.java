package local.jordi.kwetter.dao.collection;

import local.jordi.kwetter.dao.IUserDao;
import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;

import java.util.ArrayList;
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
}
