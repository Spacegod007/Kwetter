package local.jordi.kwetter.boundary.websocket;

import local.jordi.kwetter.boundary.rest.resource.ResourceHelper;
import local.jordi.kwetter.boundary.websocket.decode.TweetDecoder;
import local.jordi.kwetter.boundary.websocket.encode.TweetEncoder;
import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;
import local.jordi.kwetter.service.ITweetService;
import local.jordi.kwetter.service.IUserService;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(value = "/tweetsocket",
                encoders = TweetEncoder.class,
                decoders = TweetDecoder.class)
public class SocketEndpoint
{
    private static final Logger LOGGER = Logger.getLogger(SocketEndpoint.class.getName());

    private static final String SESSION_USER_ID = "currentuserid";

    @Inject
    private ITweetService tweetService;

    @Inject
    private IUserService userService;

    @OnOpen
    public void onOpen(Session session)
    {
        LOGGER.log(Level.INFO, "opened session with tweetId: " + session.getId());
    }

    @OnMessage
    public void onTweetMessage(Session session, Tweet tweet) throws IOException, EncodeException
    {
        User user = getSessionUser(session, tweet.getAuthor().getDomainId());
        Tweet createdTweet = tweetService.Create(tweet);
        ResourceHelper.addTweetLinks(createdTweet);

        sendUpdateToFollowers(session.getOpenSessions(), user, createdTweet);
        session.getBasicRemote().sendObject(createdTweet);
    }

    @OnError
    public void onError(Session session, Throwable error) throws Throwable
    {
        LOGGER.log(Level.INFO, error.getMessage());
    }

    @OnClose
    public void onClose(Session session)
    {
        LOGGER.log(Level.INFO, "closed session with tweetId: " + session.getId());
    }

    private void sendUpdateToFollowers(Set<Session> openSessions, User user, Tweet createdTweet)
    {
        for (Session openSession : openSessions)
        {
            if (openSession.isOpen())
            {
                Map<String, Object> userProperties = openSession.getUserProperties();

                if (checkIfUserIsFollower(userProperties, user))
                {
                    sendUpdateToFollower(openSession, createdTweet);
                }
            }
        }
    }

    private boolean checkIfUserIsFollower(Map<String, Object> userProperties, User user)
    {
        if (userProperties.containsKey(SESSION_USER_ID))
        {
            long sessionUserId = (long) userProperties.getOrDefault(SESSION_USER_ID, -1);
            if (sessionUserId == -1) return false;

            for (User follower : user.getFollowers())
            {
                if (follower.getDomainId() == sessionUserId)
                {
                    return true;
                }
            }
        }

        return false;
    }

    private void sendUpdateToFollower(Session openSession, Tweet createdTweet)
    {
        if (openSession.isOpen())
        {
            try
            {
                openSession.getBasicRemote().sendObject(createdTweet);
            } catch (IOException | EncodeException e)
            {
                e.printStackTrace();
            }
        }
    }

    private User getSessionUser(Session session, long userId)
    {
        Map<String, Object> userProperties = session.getUserProperties();

        if (!userProperties.containsKey(SESSION_USER_ID))
        {
            userProperties.put(SESSION_USER_ID, userId);
        }

        long sessionUserId = (long) userProperties.get(SESSION_USER_ID);
        return userService.Get(sessionUserId);
    }
}
