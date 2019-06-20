import parser.CSVParser;
import parser.FileParser;
import parser.ParserContext;
import parser.XMLParser;
import source.FileSourceContext;
import source.PathFile;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ParserContext parserContext = new ParserContext();
        parserContext.set(new XMLParser("person","contacts"));

        FileSourceContext fileSourceContext = new FileSourceContext();

        fileSourceContext.set(new PathFile());
        fileSourceContext.parseFile("daneXML.xml",parserContext.getFileParser());

        parserContext.set(new CSVParser());
        fileSourceContext.parseFile("daneCSV.txt",parserContext.getFileParser());

    }
}
