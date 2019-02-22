package pl.sammensprog.Example;

import pl.sammensprog.Assertion;
import pl.sammensprog.Translator;
import pl.sammensprog.TranslatorBuilder;

import java.util.HashMap;

public class CSVTranslatorBuilder extends TranslatorBuilder {
    @Override
    public Translator build(HashMap<String, String> settings, HashMap<String, Assertion[]> definedObjectSettings) {
        CSVTranslator result = new CSVTranslator();
        result.initialize(settings,definedObjectSettings);
        return result;

    }
}
