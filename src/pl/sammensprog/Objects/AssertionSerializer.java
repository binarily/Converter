package pl.sammensprog.Objects;

import com.google.gson.*;

import java.lang.reflect.Type;

public class AssertionSerializer implements JsonSerializer<Assertion> {

    @Override
    public JsonElement serialize(Assertion assertion, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", assertion.getClass().getName());
        jsonObject.add("settings", jsonSerializationContext.serialize(assertion.settings));
        return jsonObject;
    }


}
