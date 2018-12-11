package pl.sammensprog;

import java.util.*;

public interface Translator {
    //initialize: use given settings
    void initialize(HashMap<String, String> settings, HashMap<String, Assertion> definedObjectSettings);

    //extractData: turns text into DefinedObject
    ArrayList<DefinedObject> extractData(List<String> selectedData);
    //insertData: turns DefinedObject into formatted text
    ArrayList<String> insertData(List<DefinedObject> objects);

    //findElementInObject: from non-translated element at id, find a given property (e.g. column number, XPath)
    TextRange findPropertyInObject(int id, String property);
}
