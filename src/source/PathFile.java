package source;

import parser.FileParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PathFile implements FileSource {
    @Override
    public void readFile(String path, FileParser fileParser) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        fileParser.getNodes(bufferedReader);
    }

}
