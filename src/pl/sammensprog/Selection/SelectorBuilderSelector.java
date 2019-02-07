package pl.sammensprog.Selection;

import java.util.HashMap;

public class SelectorBuilderSelector {
    private static HashMap<String, SelectorBuilder> selections = new HashMap<>();
    public static SelectorBuilder from(String name){
        return selections.getOrDefault(name, null);
    }

    public static void register(String name, SelectorBuilder builder){
        selections.put(name, builder);
    }
}
