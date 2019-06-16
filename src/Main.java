import parser.parserFile;
import parser.xmlParser;
import source.fileSource;
import source.pathFile;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        parserFile parserXML = new xmlParser();
        fileSource fileSource = new pathFile();
        fileSource.getFile("daneXML.xml","person",parserXML);

    }
}
