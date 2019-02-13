package pl.sammensprog.Tools.CSV;

import pl.sammensprog.Objects.DefinedObject;
import pl.sammensprog.Translation.Translator;
import java.util.ArrayList;
import pl.sammensprog.Objects.Assertion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class CSVTranslator implements Translator {
    private HashMap<String, String> settings;
    private String delimeter, key;

    //PRIVATE EXTRA METHODS

    private ArrayList<String> split(String str, String delimeter){
        String[] splited = str.split(delimeter);
        return new ArrayList<>(Arrays.asList(splited));
    }

    private String join(String sep, ArrayList<String> values){
        StringBuilder result = new StringBuilder();
        for (String v : values) {
            result.append(v);
            result.append(sep);
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
        ArrayList<DefinedObject> result = new ArrayList<>();
        for (String record : selectedData){
            HashMap<String, String> values = new HashMap<>();
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
        ArrayList<String> result = new ArrayList<>();

        for (DefinedObject definedObject : objects){
            ArrayList<String> pulled = new ArrayList<>();
            for(int i=0;i<settings.size();i++){
                pulled.add("");
            }
            for (Map.Entry<String, String> entry : this.settings.entrySet()) {

                try{
                    int index = Integer.valueOf(entry.getValue());
                    String name = entry.getKey();
                    pulled.set(index, definedObject.get(name));
                } catch(NumberFormatException ignored){
                }

            }
            result.add(join(delimeter, pulled));
        }
        return result;
    }
}
