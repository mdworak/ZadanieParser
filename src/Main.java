import parser.CSVParser;
import parser.ParserContext;
import parser.XMLParser;
import source.FileSourceContext;
import source.PathFile;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FileSourceContext fileSourceContext = new FileSourceContext();

        ParserContext parserContext = new ParserContext();
        parserContext.set(new XMLParser("person", "contacts"));

        fileSourceContext.set(new PathFile());
        fileSourceContext.parseFile("daneXML.xml", parserContext.getFileParser());

        parserContext.set(new CSVParser());
        fileSourceContext.parseFile("daneCSV.txt", parserContext.getFileParser());

    }
}
