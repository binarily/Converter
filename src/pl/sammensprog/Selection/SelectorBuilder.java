package pl.sammensprog.Selection;

import pl.sammensprog.Context;

import java.util.HashMap;

public abstract class SelectorBuilder {
    public abstract Selector build(Context context, HashMap<String, String> settings);
}
