package source;

import parser.parserFile;
import java.io.IOException;

public interface fileSource {

    void getFile(String Path, String mainTag, parserFile parserFile) throws IOException;
}
