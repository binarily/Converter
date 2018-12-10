package pl.sammensprog;

import java.util.HashMap;

public class DefinedObject {
    private HashMap<String, String> values;
    private HashMap<String, Assertion[]> settings;
    public DefinedObject(HashMap<String, Assertion[]> settings, HashMap<String, String> values){
        this.settings = settings;
        this.values = values;
        validate();
    };

    private void validate() throws IllegalArgumentException {
        for(String key: settings.keySet()){
            Assertion[] assertions = settings.get(key);
            String value = values.get(key);
            for(Assertion assertion: assertions){
                if(!assertion.assertFor(value))
                    throw new AssertionError("One of validation assertions was not met.");
            }
        }
    }

}
