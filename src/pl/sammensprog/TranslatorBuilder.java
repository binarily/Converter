package pl.sammensprog;

import java.util.HashMap;

public abstract class TranslatorBuilder {
    abstract Translator build(HashMap<String, String> settings, HashMap<String, Assertion[]> definedObjectSettings);
}
