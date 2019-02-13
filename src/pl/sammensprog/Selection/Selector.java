package pl.sammensprog.Selection;

import pl.sammensprog.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Selector: how to select objects/how to insert them back
public interface Selector {
    //initialize: from global context, find/insert elements using settings
    void initialize(Context context, HashMap<String, String> settings);

    //findAll: finds and returns elements from context.document matching selector for further translation
    ArrayList<String> findAll();
    //insertAll: inserts all provided elements to context.document
    void insertAll(List<String> elements);

    //reinitialize: reinitialize static data (for caching) when e.g. Context changes
    void reinitialize();
}
