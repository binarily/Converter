package pl.sammensprog.Translation;

import java.util.HashMap;

public class TranslatorBuilderSelector {

    private static HashMap<String, TranslatorBuilder> selections = new HashMap<>();
    public static TranslatorBuilder from(String name){
        return selections.getOrDefault(name, null);
    }

    public static void register(String name, TranslatorBuilder builder){
        selections.put(name, builder);
    }
}
