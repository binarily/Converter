package pl.sammensprog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int main(String[] args) {
	    //Create a context and settings
        Context context;
        Settings settings;
        try {
            context = Context.fromFile(args[0]);
        } catch (IOException e) {
            System.out.println("Could not read original file.");
            e.printStackTrace();
            return -1;
        }
        try {
            settings = Settings.readFromJSON(args[2]);
        } catch (FileNotFoundException e) {
            System.out.println("Could not read description file.");
            e.printStackTrace();
            return -1;
        }

        //Convert for every object listed in context
        for(ObjectSettings setting: settings.specificSettings){

            //Create proper translators and selectors
            Selector selectorFrom = settings.selectorBuilder.build(context, setting.forSelectorFrom);
            Translator translatorFrom = settings.translatorBuilder.build(setting.forTranslatorFrom, setting.forDefinedObject);

            ArrayList<DefinedObject> objects;

            Translator translatorTo = settings.translatorBuilder.build(setting.forTranslatorTo, setting.forDefinedObject);
            Selector selectorTo = settings.selectorBuilder.build(context, setting.forSelectorTo);

            List<String> a = selectorFrom.findAll();
            objects = translatorFrom.extractData(a);
            List<String> b = translatorTo.insertData(objects);
            selectorTo.insertAll(b);

        }
        //With file prepared, write it to provided location
        try {
            context.writeResult(args[1]);
        } catch (FileNotFoundException e) {
            System.out.println("Could not write to directory.");
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
}
