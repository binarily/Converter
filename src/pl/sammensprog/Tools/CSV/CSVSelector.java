package pl.sammensprog.Tools.CSV;

import pl.sammensprog.Context;
import pl.sammensprog.Selection.Selector;
import pl.sammensprog.TextRange;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class CSVSelector implements Selector {
    private Context context;
    private String delimeter, key;

    @java.lang.Override
    public void initialize(Context context, HashMap<String, String> settings) {
        this.context = context;
        this.key = settings.get("id");
        this.delimeter = settings.get("delimeter");
    }

    @java.lang.Override
    public TextRange findInTextAt(int index) {
        return null;
    }

    @java.lang.Override
    public ArrayList<String> findAll() {
        String[] lines = context.document.split("\r\n");
        ArrayList<String> result = new ArrayList<>();
        for (String line : lines) {
            if (line.startsWith(this.key + this.delimeter))
                result.add(line);
        }
        return result;
    }

    @java.lang.Override
    public void insertAll(List<String> elements) {
        StringBuilder sb = new StringBuilder();
        for (String element : elements) {
            String result = key + element + "\r\n";
            sb.append(result);
        }
        context.result += sb.toString();
    }
}