package pl.sammensprog;

class TranslatorBuilderSelector {
    static TranslatorBuilder from(String name){
        switch(name){
            case "CSVTranslator":
                //return CSVTranslatorBuilder();
                return null;
            default:
                return null;
        }
    }
}
