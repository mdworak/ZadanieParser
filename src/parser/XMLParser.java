package parser;

import database.InsertCustomer;
import entities.Contact;
import entities.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class XMLParser implements FileParser {
    private String mainTag;
    private String contactTag;

    public XMLParser(String mainTag, String contactTag) {
        this.mainTag = mainTag;
        this.contactTag = contactTag;
    }

    @Override
    public void parseFile(List<String> nodeList) {
        List<Contact> contactValue = getContactList(nodeList);
        Customer customer = new Customer(getNodeByName(nodeList, "name"), getNodeByName(nodeList, "surname"), getNodeByName(nodeList, "age"), getNodeByName(nodeList, "city"), contactValue);
        InsertCustomer insertCustomer = new InsertCustomer();
        insertCustomer.addCustomer(customer);
        customer.showCustomerInfo();
    }

    @Override
    public void getNodes(BufferedReader bufferedReader) throws IOException {
        String startTag = buildTag(1, this.mainTag);
        String endTag = buildTag(2, this.mainTag);
        boolean isEndTag = false;
        boolean isStartTag = false;
        boolean canAdd = false;
        String textLine = bufferedReader.readLine();
        List<String> nodeList = new ArrayList<String>();
        do {
            isStartTag = textLine.toLowerCase().contains(startTag.toLowerCase());
            isEndTag = textLine.toLowerCase().contains(endTag.toLowerCase());
            if (isEndTag) {
                parseFile(nodeList);
                canAdd = false;
                nodeList = new ArrayList<String>();
            }
            if (canAdd)
                nodeList.add(textLine.trim());
            if (isStartTag)
                canAdd = true;
            textLine = bufferedReader.readLine();

        } while (textLine != null);
        bufferedReader.close();
    }

    @Override
    public List<Contact> getContactList(List<String> nodeList) {
        String contactStartTag = buildTag(1, contactTag);
        Integer contactStartIndex = nodeList.indexOf(contactStartTag);
        List<String> contactList = nodeList.subList(contactStartIndex + 1, nodeList.size() - 1);
        List<Contact> contactValue = new ArrayList<Contact>();
        for (String contact : contactList)
            contactValue.add(new Contact(getNodeValue(contact, 2), getNodeValue(contact, 1)));
        return contactValue;
    }

    public static String buildTag(Integer mode, String mainTag) { //1-startTag, 2-endTag
        StringBuilder startTagBuilder = new StringBuilder();
        startTagBuilder.append((mode == 1) ? "<" : "</");
        startTagBuilder.append(mainTag);
        startTagBuilder.append(">");
        return startTagBuilder.toString();
    }

    private String getNodeValue(String node, Integer mode) { //1-getNodeText, 2-getNodeName
        Pattern pattern = (mode.equals(1)) ? Pattern.compile("<.*>(.+?)</.*>") : Pattern.compile("<(.+?)>.*</.*>");
        Matcher matcher = pattern.matcher(node);
        String outputText = "";
        while (matcher.find())
            outputText = matcher.group(1);
        return outputText;
    }

    private String getNodeByName(List<String> nodeList, String tagName) {
        List<String> result = nodeList.stream()
                .filter(line -> line.contains(tagName))
                .collect(Collectors.toList());
        return (result.size() > 0) ? getNodeValue(result.get(0), 1) : "";
    }


}
