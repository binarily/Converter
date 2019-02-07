package pl.sammensprog;

import pl.sammensprog.Tools.CSV.CSVSelectorBuilder;
import pl.sammensprog.Tools.CSV.CSVTranslatorBuilder;
import pl.sammensprog.Selection.SelectorBuilderSelector;
import pl.sammensprog.Translation.TranslatorBuilderSelector;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Converter converter;
        //Register all applicable builders
        SelectorBuilderSelector.register("CSVSelector", new CSVSelectorBuilder());
        TranslatorBuilderSelector.register("CSVTranslator", new CSVTranslatorBuilder());

        //Create a converter
        try{
            converter = Converter.fromFiles(args[0], args[2]);
        } catch ( IOException e) {
            e.printStackTrace();
            return;
        }

        //Convert the file
        try {
            converter.convert();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return;
        }

        //With file prepared, write it to provided location
        try {
            converter.writeToFile(args[1]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }

}
