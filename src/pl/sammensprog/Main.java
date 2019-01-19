package pl.sammensprog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.sammensprog.Example.ExampleAssertion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if(args.length == 0) {
            ExampleProcedure();
            return;
        }
        //Ignore the rest for now
	    //Create a context and settings
        Context context;
        Settings settings;
        try {
            context = Context.fromFile(args[0]);
        } catch (IOException e) {
            System.out.println("Could not read original file.");
            e.printStackTrace();
            return;
        }
        try {
            settings = Settings.readFromJSON(args[2]);
        } catch (FileNotFoundException e) {
            System.out.println("Could not read description file.");
            e.printStackTrace();
            return;
        }

        //Convert for every object listed in context
        for(ObjectSettings setting: settings.specificSettings){

            //Create proper translators and selectors
            Selector selectorFrom = settings.selectorBuilderFrom.build(context, setting.forSelectorFrom);
            Translator translatorFrom = settings.translatorBuilderFrom.build(setting.forTranslatorFrom, setting.forDefinedObject);

            ArrayList<DefinedObject> objects;

            Translator translatorTo = settings.translatorBuilderFrom.build(setting.forTranslatorTo, setting.forDefinedObject);
            Selector selectorTo = settings.selectorBuilderFrom.build(context, setting.forSelectorTo);

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
        }
    }

    private static void ExampleProcedure(){
        GlobalSettings gs = new GlobalSettings();
        gs.selectorFrom="ExampleSelector";
        gs.translatorFrom = "ExampleTranslator";
        gs.translatorTo = "ExampleTranslator";
        gs.selectorTo="ExampleSelector";

        ObjectSettings os = new ObjectSettings();
        os.forSelectorFrom = new HashMap<String, String>(){{put("id", "5");}};
        os.forSelectorTo = new HashMap<String, String>(){{put("id", "7");}};
        os.forTranslatorFrom = new HashMap<String, String>(){{put("separator", " ");}};
        os.forTranslatorTo = new HashMap<String, String>(){{put("separator", " ");}};
        os.forDefinedObject = new HashMap<String, Assertion[]>(){{put("data", new Assertion[]{new ExampleAssertion()});}};

        Settings st = new Settings();
        st.specificSettings = Arrays.asList(os);
        st.globalSettings=gs;

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Assertion.class, new AssertionSerializer())
                .registerTypeAdapter(Assertion.class, new AssertionDeserializer())
                .create();
        String res = gson.toJson(st);
        System.out.println(res);
        Settings newSettings = gson.fromJson(res, Settings.class);

    }
}
