package pl.sammensprog;

import java.util.HashMap;

class ObjectSettings {
    //forSelectorFrom: settings for selector from original file
    HashMap<String, String> forSelectorFrom;
    //forTranslatorFrom: settings for translator from original file
    HashMap<String, String> forTranslatorFrom;

    //forDefinedObject: mostly comparators to check if everything is correct
    HashMap<String, Assertion[]> forDefinedObject;

    //forTranslatorTo: settings for translator to result file
    HashMap<String, String> forTranslatorTo;
    //forSelectorTo: settings for selector to result file
    HashMap<String, String> forSelectorTo;

}
