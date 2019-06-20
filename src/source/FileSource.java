package source;

import parser.FileParser;
import java.io.IOException;

public interface FileSource {

    void getFile(String Path, FileParser fileParser) throws IOException;

}
