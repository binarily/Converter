package pl.sammensprog;

class SelectorBuilderSelector {
    static SelectorBuilder from(String name){
        switch(name){
            case "CSVLineSelector":
                //return CSVSelectorBuilder();
                return null;
            default:
                return null;
        }
    }
}
