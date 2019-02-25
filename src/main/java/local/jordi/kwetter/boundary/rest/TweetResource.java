package local.jordi.kwetter.boundary.rest;

import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("tweets")
public class TweetResource
{
    @Inject
    private TweetResource tweetResource;

    @GET
    public Tweet tweets()
    {
        return new Tweet("T", new User("a", "b", "c", "d"));
    }
}
