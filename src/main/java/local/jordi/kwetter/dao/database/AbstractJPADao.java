//package local.jordi.kwetter.dao.database;
//
//import local.jordi.kwetter.dao.IDao;
//import local.jordi.kwetter.domain.IDomainObject;
//
////import javax.ejb.Stateless;
////import javax.persistence.EntityManager;
////import javax.persistence.PersistenceContext;
////import java.lang.reflect.ParameterizedType;
//
//public abstract class AbstractJPADao <T extends IDomainObject> implements IDao<T>
//{
////    private Class<T> entityclass;
////
////    AbstractJPADao()
////    {
////        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
////        entityclass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
////    }
////
////    @PersistenceContext
////    EntityManager entityManager;
////
////    @Override
////    public T Create(T t)
////    {
////        entityManager.persist(t);
////        return t;
////    }
////
////    @Override
////    public T Get(long id)
////    {
////        return entityManager.find(entityclass, id);
////    }
////
////    @Override
////    public T Update(T t)
////    {
////        return entityManager.merge(t);
////    }
////
////    @Override
////    public void Delete(T t)
////    {
////        entityManager.remove(t);
////    }
//}
