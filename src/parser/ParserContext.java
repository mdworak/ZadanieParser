package parser;

public class ParserContext {
    private FileParser fileParser;

    public void set(FileParser fileParser){
        this.fileParser=fileParser;
    }

    public FileParser getFileParser(){
       return fileParser;
    }
}
