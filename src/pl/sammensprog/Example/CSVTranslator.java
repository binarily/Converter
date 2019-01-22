package pl.sammensprog.Example;

import pl.sammensprog.DefinedObject;
import pl.sammensprog.TextRange;
import pl.sammensprog.Translator;
import java.util.ArrayList;
import pl.sammensprog.Assertion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class CSVTranslator implements Translator {
    HashMap<String, String> settings;
    String delimeter, key;

    //PRIVATE EXTRA METHODS

    private ArrayList<String> split(String str, String delimeter){
        String[] splited = str.split(delimeter);
        return new ArrayList<String>(Arrays.asList(splited));
    }

    private String join(String sep, ArrayList<String> values){
        String result = "";
        for (String v : values) {
            result += v;
            result += sep;
        }
        return result.substring(0, result.length() - 1);
    }


    @java.lang.Override
    public void initialize(HashMap<String, String> settings, HashMap<String, Assertion[]> definedObjectSettings) {
        this.settings = settings;
        this.delimeter = settings.get("delimeter");
        this.key = settings.get("id");
    }


    @java.lang.Override
    public ArrayList<DefinedObject> extractData(List<String> selectedData) {
        ArrayList<DefinedObject> result = new ArrayList<DefinedObject>();
        for (String record : selectedData){
            HashMap<String, String> values = new HashMap<String, String>();
            ArrayList<String> recordValues = split(record, delimeter);
            for (String attribute : this.settings.keySet()) {
                if(attribute.equals("delimeter"))
                    continue;
                int index = Integer.valueOf(settings.get(attribute));
                values.put(attribute, recordValues.get(index));
            }
            result.add(new DefinedObject(null,values));
        }
        return result;
    }

    @java.lang.Override
    public ArrayList<String> insertData(List<DefinedObject> objects) {
        ArrayList<String> result = new ArrayList<String>();

        for (DefinedObject definedObject : objects){
            ArrayList<String> pulled = new ArrayList<String>();
            for(int i=0;i<settings.size();i++){
                pulled.add("");
            }
            for (Map.Entry<String, String> entry : this.settings.entrySet()) {

                try{
                    int index = Integer.valueOf(entry.getValue());
                    String name = entry.getKey();
                    pulled.set(index, definedObject.get(name));
                } catch(NumberFormatException e){
                    continue;
                }

            }
            result.add(join(delimeter, pulled));
        }
        return result;
    }

    @java.lang.Override
    public TextRange findPropertyInObject(int id, String property) {
        return null;
    }
}
