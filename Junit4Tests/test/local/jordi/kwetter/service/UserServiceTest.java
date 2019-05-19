package local.jordi.kwetter.service;

import local.jordi.kwetter.dao.IUserDao;
import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest
{
    private static String username;
    private static String password;
    private static String userBiography;
    private static String userWebsite;
    private static long id;

    private static String username2;
    private static String password2;
    private static String userBiography2;
    private static String userWebsite2;
    private static long id2;

    private static String testTweetText;
    private static String testTweetText2;

    private static User user;
    private static User managedUser;
    private static User user2;
    private static User managedUser2;

    @Mock
    private IUserDao userDao;

    @InjectMocks
    private UserService userService;

    @Before
    public void BeforeEach()
    {
        username = "TestUsername";
        password = "TestPassword";
        userBiography = "TestBiography";
        userWebsite = "TestWebsite";
        id = 1;


        username2 = "OtherUsername";
        password2 = "OtherPassword";
        userBiography2 = "OtherBiography";
        userWebsite2 = "OtherWebsite";
        id2 = 2;

        testTweetText = "Test tweet text";
        testTweetText2 = "Another tweet test text";

        user = new User(username, password, userBiography, userWebsite);
        managedUser = new User(username, password, userBiography, userWebsite);
        managedUser.setDomainId(id);

        user2 = new User(username2, password2, userBiography2, userWebsite2);
        managedUser2 = new User(username2, password2, userBiography2, userWebsite2);
        managedUser2.setDomainId(id2);

        when(userDao.Get(managedUser.getDomainId())).thenReturn(managedUser);
        when(userDao.Get(managedUser2.getDomainId())).thenReturn(managedUser2);
    }

    @Test
    public void CreateUserTest()
    {
        when(userDao.userWithNameExists(username)).thenReturn(false);
        when(userDao.Create(user)).thenReturn(managedUser);

        User createdUser = userService.Create(user);

        Assert.assertEquals(1, createdUser.getDomainId());
    }

    @Test
    public void FollowUserTest()
    {
        boolean followSucceeded = userService.Follow(managedUser, managedUser2);
        boolean isUserFollowingUser2 = managedUser.getFollowing().contains(managedUser2);
        boolean doesUser2HaveUserAsFollower = managedUser2.getFollowers().contains(managedUser);

        Assert.assertTrue("User should be able to follow User2 however the method returned false", followSucceeded);
        Assert.assertTrue("User is not following user2 while method returned true", isUserFollowingUser2);
        Assert.assertTrue("User2 does not have user as follower while user is following user2", doesUser2HaveUserAsFollower);
    }

    @Test
    public void UnFollowUserTest()
    {
        userService.Follow(managedUser, managedUser2);

        userService.UnFollow(managedUser, managedUser2);
        boolean isUserFollowingUser2 = managedUser.getFollowing().contains(managedUser2);
        boolean doesUser2HaveUserAsFollower = managedUser2.getFollowers().contains(managedUser);

        Assert.assertFalse("User is following user2 while the unfollow method succeeded", isUserFollowingUser2);
        Assert.assertFalse("User2 has user as follower while user is not following user2", doesUser2HaveUserAsFollower);
    }

    @Test
    public void GetLatest10TweetsOfUser()
    {
        Prepare11UserTweets(managedUser);

        List<Tweet> tweets = userService.Get10LatestTweets(managedUser);

        Assert.assertEquals("Another number than 10 tweets was returned while 10 were set", 10, tweets.size());

        for (Tweet tweet : tweets)
        {
            Assert.assertNotEquals("", tweet.getText(), testTweetText);
        }
    }

    private void Prepare11UserTweets(User u)
    {
        List<Tweet> tweets = u.getTweets();
        Tweet errorTweet = new Tweet(testTweetText, managedUser);
        errorTweet.setDomainId(0);
        u.addTweet(errorTweet);

        for (int i = 1; i <= 10; i++)
        {
            Tweet tweet = new Tweet(testTweetText2, managedUser);
            tweet.setDomainId(i);
            tweets.add(tweet);
        }
    }
}
