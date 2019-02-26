package local.jordi.kwetter.domain;

import javax.persistence.*;
import java.io.Serializable;

public interface IDomainObject extends Serializable
{
    void setId(long id);
    long getId();
}
