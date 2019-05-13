package local.jordi.kwetter.boundary.websocket.decode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import local.jordi.kwetter.domain.Tweet;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class TweetDecoder implements Decoder.Text<Tweet>
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
    public boolean willDecode(String s)
    {
        try {
            Tweet tweet = gson.fromJson(s, Tweet.class);
            return tweet != null;
        }
        catch (Exception ignored)
        {
            return false;
        }
    }

    @Override
    public Tweet decode(String s) throws DecodeException
    {
        return gson.fromJson(s, Tweet.class);
    }
}
