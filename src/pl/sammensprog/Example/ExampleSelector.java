package pl.sammensprog.Example;

import pl.sammensprog.Context;
import pl.sammensprog.Selector;
import pl.sammensprog.TextRange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ExampleSelector implements Selector {

    private Context context;
    private  HashMap<String, String> settings;
    private  ArrayList<String> list;
    @Override
    public void initialize(Context context, HashMap<String, String> settings) {
        this.context = context;
        this.settings = settings;
        list = (ArrayList<String>)Arrays.asList(context.document.split("\n"));
        list.removeIf(x->!x.startsWith(settings.get("id")));
    }

    @Override
    public TextRange findInTextAt(int index) {
        int start = context.document.indexOf(list.get(index));
        return new TextRange(start, start+list.get(index).length());
    }

    @Override
    public ArrayList<String> findAll() {
        return list;
    }

    @Override
    public void insertAll(List<String> elements) {
        StringBuilder sb = new StringBuilder();
        for(String element: elements){
            String result = settings.get("id")+ " "+element+"\n";
            sb.append(result);
        }
        //TODO: adding to result in context
    }
}
