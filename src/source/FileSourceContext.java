package source;


import parser.FileParser;

import java.io.IOException;

public class FileSourceContext {

    private FileSource fileSource;
    public void set(FileSource fileSource){
        this.fileSource=fileSource;
    }

    public void parseFile(String path, FileParser fileParser) throws IOException {
        fileSource.readFile(path,fileParser);
    }


}
