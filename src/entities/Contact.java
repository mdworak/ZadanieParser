package entities;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    public Contact(String contact, String value) {
        this.contact = contact;
        this.type = setType();
        this.value = value;
    }

    private String contact;
    private String value;
    private Integer type;
    final Map<String, Integer> contactMap = new HashMap<String, Integer>() {{
        put("email", 1);
        put("phone", 2);
        put("jabber", 3);
    }};

    public Integer setType() {
        return contactMap.get(this.contact) == null ? 0 : contactMap.get(this.contact);
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

    public static String getContactString(String value) {
        String valueTrim = value.replaceAll("\\s+", "");
        Map<String, Boolean> contactString = new HashMap<String, Boolean>() {{
            put("email", checkContantValue("^(.+)@(.+)$", valueTrim));
            put("phone", checkContantValue("\\d{9}", valueTrim));
            put("jabber", checkContantValue("jbr*", valueTrim));
        }};
        String output = contactString.entrySet().stream()
                .filter(e -> e.getValue().equals(true))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("unknown");
        return output;
    }

    public static Boolean checkContantValue(String regex, String value) {
        Pattern pattern = (Pattern.compile(regex));
        Matcher matcher = pattern.matcher(value.trim());
        return matcher.matches();
    }
}
