package local.jordi.kwetter.domain;

public interface IDomainObject
{
    long id = Long.MIN_VALUE;

    void setId(long id);
    long getId();
}
