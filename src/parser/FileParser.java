package parser;

import entities.Contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface FileParser {

    void parseFile(List<String> nodeList);
    void getNodes(BufferedReader bufferedReader) throws IOException;
    List<Contact> getContactList(List<String> nodeList);
}
