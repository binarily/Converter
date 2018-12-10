package pl.sammensprog;

public class TextRange {
    //Both values are inclusive, i.e. range contains characters at positions start and end
    public int start;
    public int end;
    public TextRange(int start, int end){
        this.start = start;
        this.end = end;
    }
}
