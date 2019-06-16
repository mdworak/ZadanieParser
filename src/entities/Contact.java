package entities;


import java.util.HashMap;
import java.util.Map;

public class Contact {
    public Contact(String contact,String value) {
        this.contact = contact;
        this.type=setType();
        this.value=value;
    }
    private String contact;
    private String value;
    private Integer type;
    static final Map<String, Integer > contactMap = new HashMap<String, Integer>() {{
        put("email",1);
        put("phone",2);
        put("jabber",3);
    }};

    public Integer setType(){
        return contactMap.get(this.contact)==null?0:contactMap.get(this.contact);
    }

    public Integer getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String getContact() {
        return contact;
    }
}
