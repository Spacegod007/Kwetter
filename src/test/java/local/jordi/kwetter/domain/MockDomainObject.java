package local.jordi.kwetter.domain;

public class MockDomainObject extends AbstractDomainObject
{
    private long id;

    @Override
    public void setId(long id)
    {
        this.id = id;
    }

    @Override
    public long getId()
    {
        return id;
    }
}
