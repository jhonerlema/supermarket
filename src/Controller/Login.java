package Controller;

import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private Employee employee;
    private boolean loggedIn;

    public Login(String email, String password, Database database) {

        String Sql = "SELECT * FROM employees WHERE Email = ?  AND Password = ?" ;
        try {
            Connection connection = database.getConnection();
            PreparedStatement ps = connection.prepareStatement(Sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            loggedIn = rs.next();

            if (loggedIn) {
                int ID = rs.getInt("ID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String phoneNumber = rs.getString("PhoneNumber");
                String address = rs.getString("Address");
                double salary = rs.getDouble("Salary");
                int department = rs.getInt("Department");

                Employee e = switch (department) {
                    case 0 -> new Admin();
                    case 1 -> new Cashier();
                    case 2 -> new Storekeeper();
                    default -> {
                        System.out.println("Invalid department selected");
                        yield new Cashier();
                    }
                };

                e.setID(ID);
                e.setFirstName(firstName);
                e.setLastName(lastName);
                e.setEmail(email);
                e.setAddress(address);
                e.setPassword(password);
                e.setSalary(salary);
                e.setPhoneNumber(phoneNumber);
                this.employee = e;
            }
            rs.close();
            ps.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public Employee getUser() {
        return employee;
    }
}
