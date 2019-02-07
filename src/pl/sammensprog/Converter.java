package pl.sammensprog;

import pl.sammensprog.Objects.DefinedObject;
import pl.sammensprog.Selection.Selector;
import pl.sammensprog.Translation.Translator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Converter {
    private Context context = null;
    private Settings settings = null;

    //Constructors
    private Converter(){}

    static Converter fromStrings(String document, String settings)  {
        Converter result = new Converter();
        result.context = Context.fromString(document);
        result.settings = Settings.fromString(settings);
        return result;
    }

    static Converter fromFiles(String documentPath, String settingsPath) throws IOException {
        Converter result = new Converter();
        result.context = Context.fromFile(documentPath);
        result.settings = Settings.fromFile(settingsPath);
        return result;
    }

    //File change methods: for conversion of multiple files
    public void changeDocument(String newDocument){
        this.context = Context.fromString(newDocument);
    }

    public void changeFile(String newPath) throws IOException {
        this.context = Context.fromFile(newPath);
    }

    //Result extraction methods
    public void writeToFile(String path) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(path)) {
            out.println(this.context.result);
        }
    }

    public String writeToString(){
        return this.context.result;
    }

    public void convert() throws InstantiationException {
        if(this.context == null || this.settings == null){
            throw new InstantiationException("Converter has not been initialized.");
        }
        //Convert for every object listed in context
        for(ObjectSettings setting: this.settings.specificSettings){

            //Create proper translators and selectors
            Selector selectorFrom = this.settings.selectorBuilderFrom.build(this.context, setting.forSelectorFrom);
            Translator translatorFrom = this.settings.translatorBuilderFrom.build(setting.forTranslatorFrom, setting.forDefinedObject);


            Translator translatorTo = this.settings.translatorBuilderTo.build(setting.forTranslatorTo, setting.forDefinedObject);
            Selector selectorTo = this.settings.selectorBuilderTo.build(this.context, setting.forSelectorTo);

            //Actual conversion
            List<String> matchingElements = selectorFrom.findAll();
            ArrayList<DefinedObject> objects = translatorFrom.extractData(matchingElements);
            List<String> translatedElements = translatorTo.insertData(objects);
            selectorTo.insertAll(translatedElements);

        }
    }
}
