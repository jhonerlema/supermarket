package Controller;

import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadEmployee {

    public  Employee readEmployee(int ID, Database database) {

        String sql = "SELECT * FROM employees WHERE ID = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, ID);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    int department = rs.getInt("Department");

                    Employee e = switch (department) {
                        case 0 -> e = new Admin();
                        case 1 -> e = new Cashier();
                        case 2 -> e = new Storekeeper();
                        default -> {
                            System.out.println("Invalid department selected");
                            yield new Cashier();
                        }
                    };

                    e.setID(ID);
                    e.setFirstName(rs.getString("FirstName"));
                    e.setLastName(rs.getString("LastName"));
                    e.setEmail(rs.getString("Email"));
                    e.setAddress(rs.getString("Address"));
                    e.setSalary(rs.getDouble("Salary"));
                    e.setPhoneNumber(rs.getString("PhoneNumber"));
                    e.setPassword(rs.getString("Password"));

                    return e;

                } else {
                    System.out.println("Employee not found");
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

