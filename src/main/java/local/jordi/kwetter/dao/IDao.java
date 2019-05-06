package local.jordi.kwetter.dao;

import local.jordi.kwetter.domain.IDomainObject;

public interface IDao <T extends IDomainObject>
{
    T Create(T t);
    T Get(long id);
    T Update(T t);
    void Delete(T t);
}
