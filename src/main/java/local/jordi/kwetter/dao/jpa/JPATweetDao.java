package local.jordi.kwetter.dao.jpa;

import local.jordi.kwetter.dao.ITweetDao;
import local.jordi.kwetter.domain.Tweet;

import javax.ejb.Stateless;

@Stateless
public class JPATweetDao extends AbstractJPADao<Tweet> implements ITweetDao
{
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Tweet Create(Tweet tweet)
//    {
//        entityManager.persist(tweet);
//        return tweet;
//    }
//
//    @Override
//    public Tweet Get(long id)
//    {
//        return entityManager.find(Tweet.class, id);
//    }
//
//    @Override
//    public Tweet Update(Tweet tweet)
//    {
//        return entityManager.merge(tweet);
//    }
//
//    @Override
//    public void Delete(Tweet tweet)
//    {
//        entityManager.remove(tweet);
//    }
}
