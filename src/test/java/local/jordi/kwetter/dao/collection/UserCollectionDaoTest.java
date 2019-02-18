package local.jordi.kwetter.dao.collection;

import local.jordi.kwetter.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserCollectionDaoTest
{
    private static String name1;
    private static String name2;
    private static String biography1;
    private static String biography2;
    private static String website1;
    private static String website2;

    private User testUser1;
    private User testUser2;

    private CollectionUserDao collectionUserDao;

    @BeforeAll
    public static void BeforeAll()
    {
        name1 = "testName1";
        name2 = "testName2";

        biography1 = "testBiography1";
        biography2 = "testBiography2";

        website1 = "testWebsite1";
        website2 = "testWebsite2";
    }

    @BeforeEach
    public void BeforeEach()
    {
        testUser1 = new User(name1, biography1, website1);
        testUser2 = new User(name2, biography2, website2);

        collectionUserDao = new CollectionUserDao();
    }

    @Test
    public void CreateObjectTest()
    {
        long INITIAL_ID = collectionUserDao.atomicLong.get();

        User createdUser = collectionUserDao.Create(testUser1);

        long createdUserId = createdUser.getId();
        int collectionSize = collectionUserDao.objectList.size();

        Assertions.assertEquals(INITIAL_ID, createdUserId, "Id was not set correctly");
        Assertions.assertEquals(1, collectionSize, "User was not added to collection");
    }

    @Test
    public void GetObjectTest()
    {
        User createdUser = collectionUserDao.Create(testUser1);

        long createdUserId = createdUser.getId();

        User obtainedUser = collectionUserDao.Get(createdUserId);

        Assertions.assertSame(createdUser, obtainedUser, "The created user is not the same object as the obtained user");
    }

    @Test
    public void UpdateObjectTest()
    {
        String CHANGED_NAME = "ChangedName";
        User createdUser1 = collectionUserDao.Create(testUser1);
        User createdUser2 = collectionUserDao.Create(testUser2);
        createdUser1.setName(CHANGED_NAME);

        User updatedUser = collectionUserDao.Update(createdUser1);

        String updatedUserName = updatedUser.getName();

        Assertions.assertEquals(CHANGED_NAME, updatedUserName, "While the name was changed it has not been updated in the data-source");
        Assertions.assertNotEquals(CHANGED_NAME, createdUser2.getName(), "The name of the not selected user has been changed");
    }

    @Test
    public void DeleteObjectTest()
    {
        boolean EXPECTED_CONTAINS_DELETED_OBJECT_RESULT = false;
        boolean EXPECTED_CONTAINS_NOT_DELETED_OBJECT_RESULT = true;

        User createdUser1 = collectionUserDao.Create(testUser1);
        User createdUser2 = collectionUserDao.Create(testUser2);

        collectionUserDao.Delete(createdUser1);

        boolean containsRemovedObject = collectionUserDao.objectList.contains(createdUser1);
        boolean containsNotRemovedObject = collectionUserDao.objectList.contains(createdUser2);

        Assertions.assertEquals(EXPECTED_CONTAINS_DELETED_OBJECT_RESULT, containsRemovedObject, "The removed object is still available in the database");
        Assertions.assertEquals(EXPECTED_CONTAINS_NOT_DELETED_OBJECT_RESULT, containsNotRemovedObject, "The object that should not have been removed has been removed from the database");
    }
}
