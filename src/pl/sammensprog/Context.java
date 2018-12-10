package pl.sammensprog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Context {
    //document: what we're converting from
    public String document = "";
    //result: what we're converting to
    public String result = "";
    private Context(){

    }

    //loadFile: loads the document, clears last result
    static Context fromFile(String path) throws IOException {
        Context result = new Context();
        result.result = "";
        result.document = new String(Files.readAllBytes(Paths.get(path)));
        return result;
    }

    //writeResult: writes result to provided location
    void writeResult(String path) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(path)) {
            out.println(result);
        }
    }

}
