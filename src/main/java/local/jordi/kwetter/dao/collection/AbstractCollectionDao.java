package local.jordi.kwetter.dao.collection;

import local.jordi.kwetter.dao.IDao;
import local.jordi.kwetter.domain.IDomainObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractCollectionDao <T extends IDomainObject> implements IDao<T>
{
    AtomicLong atomicLong;
    List<T> objectList;

    public AbstractCollectionDao()
    {
        atomicLong = new AtomicLong(1);
        objectList = new ArrayList<>();
    }

    @Override
    public T Create(T t)
    {
        t.setId(atomicLong.getAndIncrement());
        objectList.add(t);
        return t;
    }

    @Override
    public T Get(long id)
    {
        for (T t : objectList)
        {
            if (t.getId() == id)
            {
                return t;
            }
        }

        return null;
    }

    @Override
    public T Update(T t)
    {
        for (int i = 0; i < objectList.size(); i++)
        {
            if (objectList.get(i).getId() == t.getId())
            {
                objectList.set(i, t);
                return t;
            }
        }

        return Create(t);
    }

    @Override
    public void Delete(T t)
    {
        objectList.remove(t);
    }
}
