package local.jordi.kwetter.dao.jpa;

import local.jordi.kwetter.dao.IUserDao;
import local.jordi.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.persistence.Query;

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
}
