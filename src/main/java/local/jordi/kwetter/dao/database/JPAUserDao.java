package local.jordi.kwetter.dao.database;

import local.jordi.kwetter.dao.IUserDao;
import local.jordi.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class JPAUserDao implements IUserDao
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean userWithNameExists(String name)
    {
        return (entityManager.find(User.class, name) != null);
    }

    @Override
    public User Create(User user)
    {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User Get(long id)
    {
        return entityManager.find(User.class, id);
    }

    @Override
    public User Update(User user)
    {
        return entityManager.merge(user);
    }

    @Override
    public void Delete(User user)
    {
        entityManager.remove(user);
    }
}
