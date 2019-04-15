package local.jordi.kwetter.dao.jpa;

import local.jordi.kwetter.dao.IUserDao;
import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class JPAUserDao extends AbstractJPADao<User> implements IUserDao
{
    @Override
    public boolean userWithNameExists(String name)
    {
        Query namedQuery = entityManager.createNamedQuery("User.getUserByName");
        namedQuery.setParameter("name", name);
        return (long) namedQuery.getSingleResult() > 0;
    }

    @Override
    public List<User> findByPartialName(String tag)
    {
        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.findByPartialName", User.class);
        namedQuery.setParameter("tag", tag);
        return namedQuery.getResultList();
    }

    @Override
    public List<Tweet> getTweets(long id)
    {
        TypedQuery<Tweet> namedQuery = entityManager.createNamedQuery("User.getTweets", Tweet.class);
        namedQuery.setParameter("id", id);
        return namedQuery.getResultList();
    }

    @Override
    public List<Tweet> GetFeed(long id)
    {
        TypedQuery<Tweet> namedQuery = entityManager.createNamedQuery("User.getFeed", Tweet.class);
        namedQuery.setParameter("id", id);
        return namedQuery.getResultList();
    }
}
