package pl.sammensprog.Selection;

import pl.sammensprog.Context;
import pl.sammensprog.TextRange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Selector: how to select objects/how to insert them back
public interface Selector {
    //initialize: from global context, find/insert elements using settings
    void initialize(Context context, HashMap<String, String> settings);

    //For use in GUI
    //isType: checks whether provided document can be converted by a selector
    static boolean isType(String document){return false;}
    //findInTextAt: finds elements matching selector, then returns a starting and ending position of element at given index
    TextRange findInTextAt(int index);

    //findAll: finds and returns elements from context.document matching selector for further translation
    ArrayList<String> findAll();
    //insertAll: inserts all provided elements to context.document
    void insertAll(List<String> elements);
}
