package pl.sammensprog.Translation;

import pl.sammensprog.Objects.Assertion;
import pl.sammensprog.Objects.DefinedObject;

import java.util.*;

public interface Translator {
    //initialize: use given settings
    void initialize(HashMap<String, String> settings, HashMap<String, Assertion[]> definedObjectSettings);

    //extractData: turns text into DefinedObject
    ArrayList<DefinedObject> extractData(List<String> selectedData);
    //insertData: turns DefinedObject into formatted text
    ArrayList<String> insertData(List<DefinedObject> objects);

}
