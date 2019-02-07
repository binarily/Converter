package pl.sammensprog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Context {
    //document: what we're converting from
    public String document = "";
    //result: what we're converting to
    public String result = "";

    //Constructors
    private Context(){

    }

    //fromFile: creates new context with document loaded from file
    static Context fromFile(String path) throws IOException {
        Context result = new Context();
        result.document = new String(Files.readAllBytes(Paths.get(path)));
        return result;
    }

    //fromString: creates new context with document coming from String
    static Context fromString(String document){
        Context result = new Context();
        result.document = document;
        return result;
    }


}
