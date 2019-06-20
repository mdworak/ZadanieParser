package entities;

import java.util.List;

public class Customer {
    private String name;
    private String surname;
    private String age;
    private String city;
    private List<Contact> contact;

    public Customer(String name, String surname, String age, String city, List<Contact> contact) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
        this.contact = contact;
    }

    public void showCustomerInfo() {
        System.out.println("Imie to: " + this.name);
        System.out.println("nazwisko to: " + this.surname);
        System.out.println("wiek to: " + this.age);
        System.out.println("miasto to: " + this.city);
        for (Contact c : contact)
            System.out.println("kontakt to: " + c.getContact() + ", typ to: " + c.getType() + ", a wartość to: " + c.getValue());

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return !age.equals("") ? Integer.parseInt(age) : null;
    }

    public String getCity() {
        return city;
    }

    public List<Contact> getContact() {
        return contact;
    }
}
