package pl.sammensprog.Tools.CSV;

import pl.sammensprog.Context;
import pl.sammensprog.Selection.Selector;
import pl.sammensprog.Selection.SelectorBuilder;

import java.util.HashMap;

public class CSVSelectorBuilder extends SelectorBuilder {

    @Override
    public Selector build(Context context, HashMap<String, String> settings) {
        CSVSelector result = new CSVSelector();
        result.initialize(context, settings);
        return result;
    }
}
