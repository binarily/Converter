package pl.sammensprog.Objects;

import com.google.gson.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashMap;

public class AssertionDeserializer implements JsonDeserializer<Assertion> {
    private static HashMap<String, Assertion> builders = new HashMap<>();

    public static void register(String name, Assertion builder){
        builders.put(name, builder);
    }

    @Override
    public Assertion deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Assertion result = builders.getOrDefault(jsonElement.getAsJsonObject().get("name").getAsString(), null);
        if(result==null){
            throw new JsonParseException("Assertion not found: may not have been registered");
        }
        HashMap<String, String> settings;
        try {
            settings = jsonDeserializationContext
                    .deserialize(jsonElement.getAsJsonObject().get("settings").getAsJsonObject(), HashMap.class);
        } catch (JsonSyntaxException e){
            e.printStackTrace();
            settings = new HashMap<>();
        }
        result.initialize(settings);
        return result;
    }
}
