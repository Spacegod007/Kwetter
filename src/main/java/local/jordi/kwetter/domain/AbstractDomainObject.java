package local.jordi.kwetter.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractDomainObject implements Serializable
{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    public AbstractDomainObject()
    {
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getId()
    {
        return id;
    }
}
