package pl.sammensprog.Example;

import pl.sammensprog.Assertion;

import java.util.HashMap;

public class CSVAssertion implements Assertion {
    HashMap<String, String> settings;

    @Override
    public void initialize(HashMap<String, String> settings) {
        this.settings = settings;
    }

    @Override
    public boolean assertFor(String string) {
        return true;
    }
}
