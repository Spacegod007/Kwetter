package local.jordi.kwetter.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MockDomainObjectTest
{
    @Test
    public void Test()
    {
        long SET_ID = 42;

        MockDomainObject mockDomainObject = new MockDomainObject();

        mockDomainObject.setId(SET_ID);
        long obtainedId = mockDomainObject.getId();

        Assertions.assertEquals(SET_ID, obtainedId, "The set id is not equal to the obtained id");
    }
}
