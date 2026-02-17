package Controller;

import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadAllEmployees {

    private final ArrayList<Employee> employees;

    public ReadAllEmployees(Database database) {
        employees = new ArrayList<>();

        String sql = "SELECT * FROM employees ;";

        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int ID = rs.getInt("ID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                String password = rs.getString("Password");
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
                e.setSalary(salary);
                e.setPhoneNumber(phoneNumber);
                e.setEmail(email);
                e.setPassword(password);
                employees.add(e);

            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void print() {
        for (Employee e : employees) {
            e.print();
        }
        System.out.println("----------------------------------------");
    }
}
