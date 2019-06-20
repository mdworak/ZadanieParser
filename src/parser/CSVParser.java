package parser;

import database.InsertCustomer;
import entities.Contact;
import entities.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser implements FileParser {
    @Override
    public void parseFile(List<String> nodeList) {
        Customer customer = new Customer(nodeList.get(0),nodeList.get(1),nodeList.get(2),nodeList.get(3),getContactList(nodeList));
        InsertCustomer insertCustomer = new InsertCustomer();
        insertCustomer.addCustomer(customer);
        customer.showCustomerInfo();
    }

    @Override
    public void getNodes(BufferedReader bufferedReader) throws IOException {
        String textLine = bufferedReader.readLine();
        do {
            List<String> nodeList = Arrays.asList(textLine.split(","));
            parseFile(nodeList);
            textLine = bufferedReader.readLine();

        } while(textLine != null);
    }
    @Override
    public List<Contact> getContactList(List<String> nodeList){
        List<String> contactList = nodeList.subList(4, nodeList.size());
        List<Contact> contactValue = new ArrayList<Contact>();
        for(String contact: contactList)
            contactValue.add(new Contact(Contact.getContactString(contact),contact));

        return  contactValue;
    }



}

