package local.jordi.kwetter.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MockDomainObjectTest
{
    @Test
    public void setAndGetIdTest()
    {
        long SET_ID = 42;

        MockDomainObject mockDomainObject = new MockDomainObject();

        mockDomainObject.setDomainId(SET_ID);
        long obtainedId = mockDomainObject.getDomainId();

        Assertions.assertEquals(SET_ID, obtainedId, "The set tweetId is not equal to the obtained tweetId");
    }
}
