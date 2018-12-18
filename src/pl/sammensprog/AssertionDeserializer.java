package pl.sammensprog;

import com.google.gson.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class AssertionDeserializer implements JsonDeserializer<Assertion> {
    @Override
    public Assertion deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Class<?> cl;
        try {
            cl = Class.forName(jsonElement.getAsJsonObject().get("name").getAsString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new JsonParseException("Class not found.");
        }
        Constructor<?> ctor;
        try {
            ctor = cl.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new JsonParseException("Class not found.");
        }
        Object assertion;
        try {
            assertion = ctor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonParseException("Class not found.");
        }
        Assertion result = (Assertion) assertion;
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
