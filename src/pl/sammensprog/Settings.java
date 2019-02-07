package pl.sammensprog;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.sammensprog.Objects.Assertion;
import pl.sammensprog.Objects.AssertionDeserializer;
import pl.sammensprog.Objects.AssertionSerializer;
import pl.sammensprog.Selection.SelectorBuilder;
import pl.sammensprog.Selection.SelectorBuilderSelector;
import pl.sammensprog.Translation.TranslatorBuilder;
import pl.sammensprog.Translation.TranslatorBuilderSelector;

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

    //Constructors
    static Settings fromFile(String descriptionFile) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Assertion.class, new AssertionSerializer())
                .registerTypeAdapter(Assertion.class, new AssertionDeserializer())
                .create();
        Settings result = gson.fromJson(new FileReader(descriptionFile), Settings.class);
        result.setUpBuilders();
        return result;
    }

    static Settings fromString(String description){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Assertion.class, new AssertionSerializer())
                .registerTypeAdapter(Assertion.class, new AssertionDeserializer())
                .create();
        Settings result = gson.fromJson(description, Settings.class);
        result.setUpBuilders();
        return result;
    }

    //Helpers
    private void setUpBuilders(){
        this.selectorBuilderFrom = SelectorBuilderSelector.from(this.globalSettings.selectorFrom);
        this.translatorBuilderFrom = TranslatorBuilderSelector.from(this.globalSettings.translatorFrom);
        this.translatorBuilderTo = TranslatorBuilderSelector.from(this.globalSettings.translatorTo);
        this.selectorBuilderTo = SelectorBuilderSelector.from(this.globalSettings.selectorTo);
    }

}
