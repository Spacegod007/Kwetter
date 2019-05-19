package local.jordi.kwetter.domain;

import java.io.Serializable;

public interface IDomainObject extends Serializable
{
    void setDomainId(long id);
    long getDomainId();
}
