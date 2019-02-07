package pl.sammensprog.Tools.CSV;

import pl.sammensprog.Objects.Assertion;
import pl.sammensprog.Translation.Translator;
import pl.sammensprog.Translation.TranslatorBuilder;

import java.util.HashMap;

public class CSVTranslatorBuilder extends TranslatorBuilder {
    @Override
    public Translator build(HashMap<String, String> settings, HashMap<String, Assertion[]> definedObjectSettings) {
        CSVTranslator result = new CSVTranslator();
        result.initialize(settings,definedObjectSettings);
        return result;

    }
}
