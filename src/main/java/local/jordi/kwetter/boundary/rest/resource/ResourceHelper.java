package local.jordi.kwetter.boundary.rest.resource;

import local.jordi.kwetter.boundary.rest.serialize.JsonTranslator;
import local.jordi.kwetter.domain.Tweet;
import local.jordi.kwetter.domain.User;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ResourceHelper
{

    private ResourceHelper()
    {
    }

    static Response GenerateResponse(Object object)
    {
        if (object != null)
        {
            return Response.ok(object).build();
        }
        return Response.noContent().build();
    }

    static Response GenerateResponse(boolean operationSucceeded)
    {
        if (operationSucceeded)
        {
            return Response.ok().build();
        }
        return Response.notModified().build();
    }

    static Response GenerateResponse()
    {
        return Response.ok().build();
    }

    static Response GenerateErrorResponse()
    {
        return Response.serverError().build();
    }

    static  <T> T JsonToObject(JsonObject jsonObject, Class<T> type)
    {
        return new JsonTranslator().JsonToObject(jsonObject, type);
    }

    static void addUserLinks(User user)
    {
        String baseUrl = "/Kwetter/api/users";

        user.add(linkTo(UserResource.class).slash(baseUrl).slash(user.getDomainId()).withSelfRel().withType("get"));
        user.add(linkTo(UserResource.class).slash(baseUrl).slash(user.getDomainId()).slash("followers").withRel("followers").withType("get"));
        user.add(linkTo(UserResource.class).slash(baseUrl).slash(user.getDomainId()).slash("following").withRel("following").withType("get"));
        user.add(linkTo(UserResource.class).slash(baseUrl).slash(user.getDomainId()).slash("latesttweets").withRel("latesttweets").withType("get"));
        user.add(linkTo(UserResource.class).slash(baseUrl).slash(user.getDomainId()).slash("tweets").withRel("tweets").withType("get"));
        user.add(linkTo(UserResource.class).slash(baseUrl).slash(user.getDomainId()).slash("feed").withRel("feed").withType("get"));
    }

    public static void addTweetLinks(Tweet tweet)
    {
        String baseUrl = "/Kwetter/api/tweets";

        tweet.add(linkTo(TweetResource.class).slash(baseUrl).slash(tweet.getDomainId()).withSelfRel().withType("get"));
        tweet.add(linkTo(TweetResource.class).slash(baseUrl).slash(tweet.getDomainId()).slash("replies").withRel("replies").withType("get"));
        tweet.add(linkTo(TweetResource.class).slash(baseUrl).slash(tweet.getDomainId()).slash("replyto").withRel("replyto").withType("get"));
    }
}
