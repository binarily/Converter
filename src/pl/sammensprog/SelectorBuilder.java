package pl.sammensprog;

import java.util.HashMap;

public abstract class SelectorBuilder {
    abstract Selector build(Context context, HashMap<String, String> settings);
}
