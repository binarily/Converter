package pl.sammensprog;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;

class Settings {

    //globalSettings: conversionSettings, as read from JSON file. Defines base selectors and translators for both sides
    GlobalSettings globalSettings;
    //specificSettings: a list of settings for each object considered
    List<ObjectSettings> specificSettings;

    //Builders
    SelectorBuilder selectorBuilder = null;
    TranslatorBuilder translatorBuilder = null;

    //readFromJSON: creates Settings class from JSON file
    static Settings readFromJSON(String descriptionFile) throws FileNotFoundException {
        Gson gson = new Gson();
        Settings result = gson.fromJson(new FileReader(descriptionFile), Settings.class);
        result.selectorBuilder = SelectorBuilderSelector.from(result.globalSettings.selector);
        result.translatorBuilder = TranslatorBuilderSelector.from(result.globalSettings.translator);
        return result;
    }
}
