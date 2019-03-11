package local.jordi.kwetter.service;

import local.jordi.kwetter.domain.IDomainObject;

public interface IService<T extends IDomainObject>
{
    T Get(long id);
    T Get(T t);
    T Create(T t);
    T Update(T t);
    void Remove(T t);

}
