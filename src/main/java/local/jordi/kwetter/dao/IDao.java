package local.jordi.kwetter.dao;

import local.jordi.kwetter.domain.AbstractDomainObject;

public interface IDao <T extends AbstractDomainObject>
{
    T Create(T t);
    T Get(long id);
    T Update(T t);
    void Delete(T t);
}
