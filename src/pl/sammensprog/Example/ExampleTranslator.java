package pl.sammensprog.Example;

import pl.sammensprog.Assertion;
import pl.sammensprog.DefinedObject;
import pl.sammensprog.TextRange;
import pl.sammensprog.Translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExampleTranslator implements Translator {
    private HashMap<String, String> settings;
    private HashMap<String, Assertion[]> definedObjectSettings;

    @Override
    public void initialize(HashMap<String, String> settings, HashMap<String, Assertion[]> definedObjectSettings) {
        this.settings = settings;
        this.definedObjectSettings=definedObjectSettings;
    }

    @Override
    public ArrayList<DefinedObject> extractData(List<String> selectedData) {
        ArrayList<DefinedObject> result = new ArrayList<>();
        for(String s:selectedData){
            String[] data = s.split(settings.get("separator"));
            //nvm
        }
        return null;
    }

    @Override
    public ArrayList<String> insertData(List<DefinedObject> objects) {
        return null;
    }

    @Override
    public TextRange findPropertyInObject(int id, String property) {
        return null;
    }
}
