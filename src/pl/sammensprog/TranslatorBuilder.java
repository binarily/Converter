package pl.sammensprog;

import java.util.Comparator;
import java.util.HashMap;

abstract class TranslatorBuilder {
    abstract Translator build(HashMap<String, String> settings, HashMap<String, Assertion[]> definedObjectSettings);
}
