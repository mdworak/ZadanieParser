package database;

import entities.Contact;
import entities.Customer;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class InsertCustomer {
    public InsertCustomer() {
        try (InputStream input = new FileInputStream("./src/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            username=prop.getProperty("mDbUser");
            jdbcUrl=prop.getProperty("mDbHost");
            password =prop.getProperty("mDbPwds");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private String jdbcUrl;
    private String username;
    private String password;

    public void addCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String insertCustomer="INSERT INTO customers_table "
                + "(name, surname, city, age) VALUES (?,?,?,?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = getConnection();
            preparedStatement = connection.prepareStatement(insertCustomer,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, customer.getName());
            preparedStatement.setObject(2, customer.getSurname());
            preparedStatement.setObject(3, customer.getCity());
            preparedStatement.setObject(4, customer.getAge(), java.sql.Types.INTEGER);
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long customerId = generatedKeys.getLong(1);
                    addContact(customer.getContact(),customerId);
                }
                else
                    throw new SQLException("Creating user failed, no ID obtained.");
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public  void addContact(List<Contact> contactList, long customerID) {
        String insertContact = "INSERT INTO contact_table "
                + "(id_customer, type, contact) VALUES (?,?,?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        for (Contact contact : contactList) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection=getConnection();
                preparedStatement = connection.prepareStatement(insertContact, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1, customerID);
                preparedStatement.setObject(2, contact.getType());
                preparedStatement.setObject(3, contact.getValue());
                preparedStatement.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);

    }
}
