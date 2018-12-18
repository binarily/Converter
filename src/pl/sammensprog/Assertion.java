package pl.sammensprog;

import java.util.HashMap;

public interface Assertion {

    HashMap<String, String> settings = new HashMap<>();
    void initialize(HashMap<String, String> settings);
    boolean assertFor(String string);
}
