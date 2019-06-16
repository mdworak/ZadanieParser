package parser;
import entities.Contact;
import entities.Customer;
import source.pathFile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class xmlParser implements parserFile {
    @Override
    public void parseFile(List<String> nodeList) {
        List<String> contactList = getContactList(nodeList);
        List<Contact> contactValue = new ArrayList<Contact>();
        for(String contact: contactList)
            contactValue.add(new Contact(getNodeValue(contact,2),getNodeValue(contact,1)));
        Customer customer = new Customer(getNodeByName(nodeList,"name"),getNodeByName(nodeList,"surname"),getNodeByName(nodeList,"age"),getNodeByName(nodeList,"city"),contactValue);
        customer.showCustomerInfo();
    }

    String getNodeValue(String node,Integer mode){ //1-getNodeText, 2-getNodeName
        Pattern pattern = (mode.equals(1))?Pattern.compile("<.*>(.+?)</.*>"):Pattern.compile("<(.+?)>.*</.*>");
        Matcher matcher = pattern.matcher(node);
        String outputText ="";
        while (matcher.find())
            outputText = matcher.group(1);
        return outputText;
    }

    String getNodeByName(List<String> nodeList, String tagName){
        List<String> result = nodeList.stream()
                .filter(line -> line.contains(tagName))
                .collect(Collectors.toList());
        return (result.size()>0)?getNodeValue(result.get(0),1): "";
    }
    List<String> getContactList(List<String> nodeList){
        String contactStartTag = pathFile.buildTag(1,"contacts");
        Integer contactStartIndex= nodeList.indexOf(contactStartTag);
        return nodeList.subList(contactStartIndex+1, nodeList.size()-1);
    }


}
