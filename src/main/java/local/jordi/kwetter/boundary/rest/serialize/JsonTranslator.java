package local.jordi.kwetter.boundary.rest.serialize;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.json.JsonObject;

public class JsonTranslator
{
    private Gson gson;

    public JsonTranslator()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    public <T> T JsonToObject(JsonObject jsonObject, Class<T> type)
    {
        return gson.fromJson(jsonObject.toString(), type);
    }

    public <T> String ObjectToJson(T object)
    {
        return gson.toJson(object);
    }
}
