package local.jordi.kwetter.domain;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class UserTest
{
    private static String validName;
    private static String validPassword;
    private static String validBiography;
    private static String validWebsite;

    private static String validName2;
    private static String validPassword2;
    private static String validBiography2;
    private static String validWebsite2;

    private static String invalidName;
    private static String invalidPassword;

    private static User testUser1;
    private static User testUser2;

    @BeforeAll
    public static void beforeAll()
    {
        validName = "TestUser";
        validPassword = "TestPassword";
        validBiography = "TestBiography";
        validWebsite = "TestWebsite";

        validName2 = "OtherUser";
        validPassword2 = "OtherPassword";
        validBiography2 = "OtherBiography";
        validWebsite2 = "OtherWebsite";

        invalidName = "";
        invalidPassword = "";
    }

    @BeforeEach
    public void beforeEach()
    {
        testUser1 = new User(validName, validPassword, validBiography, validWebsite);
        testUser2 = new User(validName2, validPassword2, validBiography2, validWebsite2);
    }

    @Test
    public void CreateValidUser()
    {
        new User(validName, validPassword, validBiography, validWebsite);
    }

    @Test
    public void CreateValidUser2()
    {
        new User(validName, validBiography, validWebsite);
    }

    @Test
    public void CreateInvalidUser()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(invalidName, validPassword, validBiography, validWebsite), "User should not be allowed to be created with an invalid name");
    }

    @Test
    public void CreateInvalidUser2()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(validName, invalidPassword, validBiography, validWebsite), "User should not be allowed to be created with an invalid password");
    }

    @Test
    public void CreateInvalidUser3()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(invalidName, validBiography, validWebsite), "User should not be allowed to be created with an invalid name");
    }

    @Test
    public void CreateInvalidUser4()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(null, validPassword, validBiography, validWebsite), "User should not be allowed to be created with null as name");
    }

    @Test
    public void CreateInvalidUser5()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(validName, invalidPassword, validBiography, validWebsite), "User should not be allowed to be created with null as password");
    }

    @Test
    public void CreateInvalidUser6()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(null, validBiography, validWebsite), "User should not be allowed to be created with null as name");
    }

    @Test
    public void Getters() throws Exception
    {
        Assertions.assertEquals(validName, testUser1.getName(), "Name is not equal to what it has been set to");
        Assertions.assertEquals(validPassword, testUser1.getPassword(), "Password is not equal to what it has been set to");
        Assertions.assertEquals(validBiography, testUser1.getBiography(), "Biography is not equal to what it has been set to");
        Assertions.assertEquals(validWebsite, testUser1.getWebsite(), "Website is not equal to what it has been set to");
    }

    @Test
    public void Setters() throws Exception
    {
        testUser1.setName(validName2);
        testUser1.setPassword(validPassword2);
        testUser1.setBiography(validBiography2);
        testUser1.setWebsite(validWebsite2);

        Assertions.assertThrows(IllegalArgumentException.class, () -> testUser1.setName(invalidName));
        Assertions.assertThrows(IllegalArgumentException.class, () -> testUser1.setPassword(invalidPassword));

        Assertions.assertEquals(validName2, testUser1.getName(), "Name is not equal to what it has been set to");
        Assertions.assertEquals(validPassword2, testUser1.getPassword(), "Password is not equal to what it has been set to");
        Assertions.assertEquals(validBiography2, testUser1.getBiography(), "Biography is not equal to what it has been set to");
        Assertions.assertEquals(validWebsite2, testUser1.getWebsite(), "Website is not equal to what it has been set to");
    }

    @Test
    public void ValidFollowUser()
    {
        boolean expectedTrueResult = testUser1.follow(testUser2);
        Set<User> user1Following = testUser1.getFollowing();
        Set<User> user2Followers = testUser2.getFollowers();

        Assertions.assertTrue(expectedTrueResult, "User1 should now follow User2 but the method returned false");

        if (!user1Following.contains(testUser2))
        {
            Assertions.fail("User1 is not following User2 while the method returned true");
        }

        if (!user2Followers.contains(testUser1))
        {
            Assertions.fail("User2 does not have User1 as follower while the method returned true");
        }
    }

    @Test
    public void InvalidFollowUser()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> testUser1.follow(testUser1), "User should not be able to follow him/herself");
        Set<User> user1Following = testUser1.getFollowing();
        Set<User> user1Followers = testUser1.getFollowers();

        if (user1Following.contains(testUser1))
        {
            Assertions.fail("User1 is following itself while the method returned false");
        }

        if (user1Followers.contains(testUser1))
        {
            Assertions.fail("User1 has itself as follower while the method returned false");
        }
    }

    @Test
    public void InvalidFollowUser2()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> testUser1.follow(null), "User should not be able to follow null");
        Set<User> user1Following = testUser1.getFollowing();
        Set<User> user1Followers = testUser1.getFollowers();

        if (user1Following.contains(testUser1))
        {
            Assertions.fail("User1 is following itself while the method returned false");
        }

        if (user1Followers.contains(testUser1))
        {
            Assertions.fail("User1 has itself as follower while the method returned false");
        }
    }

    @Test
    public void ValidUnFollowUser()
    {
        testUser1.follow(testUser2);
        testUser1.unFollow(testUser2);

        Set<User> user1Following = testUser1.getFollowing();
        Set<User> user2Followers = testUser2.getFollowers();

        if (user1Following.contains(testUser2))
        {
            Assertions.fail("User1 is still following User2 while he has UnFollowed User2");
        }

        if (user2Followers.contains(testUser1))
        {
            Assertions.fail("User2 has User1 as follower while User1 has UnFollowed User2");
        }
    }
}
