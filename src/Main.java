import parser.CSVParser;
import parser.FileParser;
import parser.XMLParser;
import source.FileSource;
import source.PathFile;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FileSource fileSource = new PathFile();
        FileParser parserXML = new XMLParser("person","contacts");
        fileSource.getFile("daneXML.xml",parserXML);
        FileParser parserCSV = new CSVParser();
        fileSource.getFile("daneCSV.txt",parserCSV);
    }
}
