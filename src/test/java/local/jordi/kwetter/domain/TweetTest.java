package local.jordi.kwetter.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TweetTest
{
    private static String tweetText1;
    private static String tweetText2;

    private static User testUser1;
    private static User testUser2;

    private Tweet testTweet1;
    private Tweet testTweet2;

    @BeforeAll
    public static void beforeAll()
    {
        tweetText1 = "testText";
        tweetText2 = "otherText";
    }

    @BeforeEach
    public void beforeEach()
    {
        testUser1 = new User("testUser1", "testBiography", "testWebsite");
        testUser2 = new User("testUser2", "testBiography", "testWebsite");

        testTweet1 = new Tweet(tweetText1, testUser1);
        testTweet2 = new Tweet(tweetText2, testUser2);
    }

    @Test
    public void CreateValidTweet()
    {
        new Tweet(tweetText1, testUser1);
    }

    @Test
    public void CreateValidTweet2()
    {
        new Tweet(tweetText1, testUser1, testTweet2);
    }

    @Test
    public void CreateInvalidTweet()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Tweet(tweetText1, null), "A tweet was created while null was given as author");
    }

    @Test
    public void AddReaction()
    {
        testTweet1.addReaction(testTweet2);

        List<Tweet> reactions = testTweet1.getReactions();

        if (!reactions.contains(testTweet2))
        {
            Assertions.fail("tweet2 was not added as a reaction of tweet1 while it is a reaction to tweet1");
        }
    }

    @Test
    public void RemoveReaction()
    {
        testTweet1.addReaction(testTweet2);
        testTweet1.removeReaction(testTweet2);

        List<Tweet> reactions = testTweet1.getReactions();

        if (reactions.contains(testTweet2))
        {
            Assertions.fail("tweet2 is a reaction to tweet1 while it should have been removed");
        }
    }
}
