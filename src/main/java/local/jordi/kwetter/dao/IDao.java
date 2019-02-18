package local.jordi.kwetter.dao;

public interface IDao <T>
{
    T Create(T t);
    T Get(long id);
    T Update(T t);
    void Delete(T t);
}
