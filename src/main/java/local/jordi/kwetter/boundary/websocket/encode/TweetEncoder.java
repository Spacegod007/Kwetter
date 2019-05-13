package local.jordi.kwetter.boundary.websocket.encode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import local.jordi.kwetter.domain.Tweet;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class TweetEncoder implements Encoder.Text<Tweet>
{
    private Gson gson;

    @Override
    public void init(EndpointConfig endpointConfig)
    {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    @Override
    public void destroy()
    {

    }

    @Override
    public String encode(Tweet tweet) throws EncodeException
    {
        return gson.toJson(tweet);
    }
}
