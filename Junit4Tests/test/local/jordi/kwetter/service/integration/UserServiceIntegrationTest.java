package local.jordi.kwetter.service.integration;

import local.jordi.kwetter.dao.IUserDao;
import local.jordi.kwetter.dao.collection.CollectionUserDao;
import local.jordi.kwetter.domain.User;
import local.jordi.kwetter.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceIntegrationTest
{
    private static String username;
    private static String password;
    private static String biography;
    private static String website;

    private static User user;

    @Spy
    private IUserDao userDao = new CollectionUserDao();

    @InjectMocks
    private UserService userService;

    @Before
    public void BeforeEach()
    {
        username = "username";
        password = "password";
        biography = "biography";
        website = "website";

        user = new User(username, password, biography, website);
    }

    @Test
    public void CreateUserTest()
    {
        long expectedId = 1;

        User managedUser = userService.Create(user);

        long id = managedUser.getId();
        User obtainedUser = userService.Get(id);

        Assert.assertEquals("The id was not set correctly while creating the user", expectedId, id);
        Assert.assertNotNull("The user found with the id was null while it should've contained the created user", obtainedUser);
    }

    @Test
    public void GetUserTest()
    {
        User managedUser = userService.Create(UserServiceIntegrationTest.user);
        long id = managedUser.getId();

        User obtainedUser = userService.Get(id);

        Assert.assertEquals("The obtained user is not the same as the created user", managedUser, obtainedUser);
    }

    @Test
    public void UpdateUserTest()
    {
        String updatedName = "updated name";
        User createdUser = userService.Create(user);
        long id = createdUser.getId();
        createdUser.setName(updatedName);

        User updatedUser = userService.Update(user);

        Assert.assertEquals("While updating the user, the id should not have changed, however it did", id, updatedUser.getId());
        Assert.assertEquals("The name of the user was not updated while this was changed before calling the update method", updatedName, updatedUser.getName());
    }

    @Test
    public void RemoveUserTest()
    {
        User createduser = userService.Create(user);
        long createduserId = createduser.getId();

        userService.Remove(createduser);

        User removedUser = userDao.Get(createduserId);

        Assert.assertNull("an id was found while the user was removed, this suggests the user was not actually removed", removedUser);
    }
}
