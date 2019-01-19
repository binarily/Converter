package pl.sammensprog;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class Settings {

    //globalSettings: conversionSettings, as read from JSON file. Defines base selectors and translators for both sides
    GlobalSettings globalSettings;
    //specificSettings: a list of settings for each object considered
    List<ObjectSettings> specificSettings;

    //Builders
    SelectorBuilder selectorBuilderFrom = null;
    TranslatorBuilder translatorBuilderFrom = null;
    TranslatorBuilder translatorBuilderTo = null;
    SelectorBuilder selectorBuilderTo = null;

    //readFromJSON: creates Settings class from JSON file
    static Settings readFromJSON(String descriptionFile) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Assertion.class, new AssertionSerializer())
                .registerTypeAdapter(Assertion.class, new AssertionDeserializer())
                .create();
        Settings result = gson.fromJson(new FileReader(descriptionFile), Settings.class);
        result.selectorBuilderFrom = SelectorBuilderSelector.from(result.globalSettings.selectorFrom);
        result.translatorBuilderFrom = TranslatorBuilderSelector.from(result.globalSettings.translatorFrom);
        result.translatorBuilderTo = TranslatorBuilderSelector.from(result.globalSettings.translatorTo);
        result.selectorBuilderTo = SelectorBuilderSelector.from(result.globalSettings.selectorFrom);

        return result;
    }
}
