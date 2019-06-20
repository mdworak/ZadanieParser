package source;

import parser.FileParser;

import java.io.IOException;

public interface FileSource {
    void readFile(String Path, FileParser fileParser) throws IOException;

}
