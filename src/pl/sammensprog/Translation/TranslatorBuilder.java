package pl.sammensprog.Translation;

import pl.sammensprog.Objects.Assertion;

import java.util.HashMap;

public abstract class TranslatorBuilder {
    public abstract Translator build(HashMap<String, String> settings, HashMap<String, Assertion[]> definedObjectSettings);
}
