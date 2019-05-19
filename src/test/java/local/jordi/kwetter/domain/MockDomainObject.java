package local.jordi.kwetter.domain;

public class MockDomainObject implements IDomainObject
{
    private long id;

    @Override
    public void setDomainId(long id)
    {
        this.id = id;
    }

    @Override
    public long getDomainId()
    {
        return id;
    }
}
